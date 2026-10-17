package com.sailxx.ember

import android.content.ComponentName
import android.content.Context
import android.content.SharedPreferences
import android.service.quicksettings.TileService

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

    fun save(ctx: Context, c: Config) {
        sp(ctx).edit()
            .putBoolean("enabled", c.enabled)
            .putBoolean("auto", c.auto)
            .putBoolean("sun", c.sun)
            .putInt("on", c.on)
            .putInt("off", c.off)
            .putInt("kelvin", c.kelvin)
            .putInt("strength", c.strength)
            .putInt("dim", c.dim)
            .putInt("fade", c.fade)
            .putFloat("lat", c.lat.toFloat())
            .putFloat("lon", c.lon.toFloat())
            .apply()
        runCatching { TileService.requestListeningState(ctx, ComponentName(ctx, EmberTile::class.java)) }
    }
}
