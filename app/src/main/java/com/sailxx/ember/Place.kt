package com.sailxx.ember

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.CancellationSignal
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat

/** Координаты для расчёта заката: последнее известное место или один свежий замер со всех доступных источников. */
object Place {
    enum class Error { OFF, NO_FIX }

    @SuppressLint("MissingPermission")
    fun locate(ctx: Context, done: (Location?, Error?) -> Unit) {
        val lm = ctx.getSystemService(LocationManager::class.java)
        if (!LocationManagerCompat.isLocationEnabled(lm)) return done(null, Error.OFF)

        // GPS доступен только с точным разрешением; network часто отключён без сервисов Google — поэтому пробуем всё.
        val fine = ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val all = lm.allProviders
        val providers = buildList {
            if (Build.VERSION.SDK_INT >= 31) add(LocationManager.FUSED_PROVIDER)
            add(LocationManager.NETWORK_PROVIDER)
            if (fine) add(LocationManager.GPS_PROVIDER)
        }.filter { it in all && runCatching { lm.isProviderEnabled(it) }.getOrDefault(false) }

        // Для заката хватает точности города, поэтому подходит и старое место.
        val last = (providers + LocationManager.PASSIVE_PROVIDER)
            .mapNotNull { runCatching { lm.getLastKnownLocation(it) }.getOrNull() }
            .maxByOrNull { it.time }
        if (last != null) return done(last, null)
        if (providers.isEmpty()) return done(null, Error.NO_FIX)

        val main = Handler(Looper.getMainLooper())
        val cancel = CancellationSignal()
        var finished = false
        var waiting = providers.size
        fun finish(loc: Location?) {
            if (finished) return
            finished = true
            cancel.cancel()
            done(loc, if (loc == null) Error.NO_FIX else null)
        }
        main.postDelayed({ finish(null) }, 30_000)
        providers.forEach { p ->
            runCatching {
                LocationManagerCompat.getCurrentLocation(lm, p, cancel, ContextCompat.getMainExecutor(ctx)) { loc ->
                    waiting--
                    if (loc != null) finish(loc) else if (waiting == 0) finish(null)
                }
            }.onFailure { if (--waiting == 0) main.post { finish(null) } }
        }
    }
}
