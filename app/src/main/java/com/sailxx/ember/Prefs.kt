package com.sailxx.ember

import android.content.ComponentName
import android.content.Context
import android.content.SharedPreferences
import android.service.quicksettings.TileService
import androidx.annotation.MainThread
import androidx.core.content.edit

data class Config(
    val enabled: Boolean = false,
    val auto: Boolean = true,
    val sun: Boolean = true,      // false — своё время включения и выключения
    val on: Int = 21 * 60,        // минуты от полуночи
    val off: Int = 7 * 60,
    val kelvin: Int = 2700,
    val strength: Int = 60, // 0..100 — насколько плотный тёплый слой
    val dim: Int = 0,       // 0..100 — дополнительное затемнение
    val fade: Int = 30,     // минуты плавного перехода
    val lat: Double = Double.NaN,
    val lon: Double = Double.NaN,
) {
    val hasPlace get() = !lat.isNaN() && !lon.isNaN()
}

object Prefs {
    fun sp(ctx: Context): SharedPreferences = ctx.getSharedPreferences("ember", Context.MODE_PRIVATE)

    fun load(ctx: Context): Config = with(sp(ctx)) {
        Config(
            enabled = getBoolean("enabled", false),
            auto = getBoolean("auto", true),
            sun = getBoolean("sun", true),
            on = getInt("on", 21 * 60),
            off = getInt("off", 7 * 60),
            kelvin = getInt("kelvin", 2700),
            strength = getInt("strength", 60),
            dim = getInt("dim", 0),
            fade = getInt("fade", 30),
            lat = getFloat("lat", Float.NaN).toDouble(),
            lon = getFloat("lon", Float.NaN).toDouble(),
        )
    }

    @MainThread
    fun update(ctx: Context, transform: Config.() -> Config): Config {
        val previous = load(ctx)
        val next = previous.transform()
        if (next == previous) return previous

        sp(ctx).edit {
            putBoolean("enabled", next.enabled)
            putBoolean("auto", next.auto)
            putBoolean("sun", next.sun)
            putInt("on", next.on)
            putInt("off", next.off)
            putInt("kelvin", next.kelvin)
            putInt("strength", next.strength)
            putInt("dim", next.dim)
            putInt("fade", next.fade)
            putFloat("lat", next.lat.toFloat())
            putFloat("lon", next.lon.toFloat())
        }
        if (next.enabled != previous.enabled || (next.enabled && next.kelvin != previous.kelvin)) {
            runCatching { TileService.requestListeningState(ctx, ComponentName(ctx, EmberTile::class.java)) }
        }
        return next
    }
}
