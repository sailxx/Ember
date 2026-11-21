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
import android.os.SystemClock
import androidx.annotation.MainThread
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat

/** Координаты для расчёта заката: недавний замер или новый запрос к доступным источникам. */
object Place {
    private const val MAX_LOCATION_AGE_NANOS = 5 * 60 * 1_000_000_000L
    enum class Error { OFF, NO_FIX }

    @MainThread
    @SuppressLint("MissingPermission")
    fun locate(ctx: Context, done: (Location?, Error?) -> Unit): (() -> Unit)? {
        val lm = ctx.getSystemService(LocationManager::class.java)
        if (!LocationManagerCompat.isLocationEnabled(lm)) {
            done(null, Error.OFF)
            return null
        }

        // GPS доступен только с точным разрешением; network часто отключён без сервисов Google — поэтому пробуем всё.
        val fine = ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val all = lm.allProviders
        val providers = buildList {
            if (Build.VERSION.SDK_INT >= 31) add(LocationManager.FUSED_PROVIDER)
            add(LocationManager.NETWORK_PROVIDER)
            if (fine) add(LocationManager.GPS_PROVIDER)
        }.filter { it in all && runCatching { lm.isProviderEnabled(it) }.getOrDefault(false) }

        val last = (providers + LocationManager.PASSIVE_PROVIDER)
            .mapNotNull { runCatching { lm.getLastKnownLocation(it) }.getOrNull() }
            .filter(::isRecent)
            .maxByOrNull { it.elapsedRealtimeNanos }
        if (last != null || providers.isEmpty()) {
            done(last, if (last == null) Error.NO_FIX else null)
            return null
        }

        val main = Handler(Looper.getMainLooper())
        val cancel = CancellationSignal()
        var waiting = providers.size
        val timeout = object : Runnable {
            override fun run() = finish(null)

            fun finish(loc: Location?) {
                if (cancel.isCanceled) return
                main.removeCallbacks(this)
                cancel.cancel()
                done(loc, if (loc == null) Error.NO_FIX else null)
            }
        }
        main.postDelayed(timeout, 30_000)
        providers.forEach { p ->
            runCatching {
                LocationManagerCompat.getCurrentLocation(lm, p, cancel, ContextCompat.getMainExecutor(ctx)) { loc ->
                    waiting--
                    if (loc != null && isRecent(loc)) {
                        timeout.finish(loc)
                    } else if (waiting == 0) {
                        timeout.run()
                    }
                }
            }.onFailure {
                if (--waiting == 0) {
                    main.removeCallbacks(timeout)
                    main.post(timeout)
                }
            }
        }
        return {
            main.removeCallbacks(timeout)
            cancel.cancel()
        }
    }

    private fun isRecent(location: Location): Boolean =
        SystemClock.elapsedRealtimeNanos() - location.elapsedRealtimeNanos in 0..MAX_LOCATION_AGE_NANOS
}
