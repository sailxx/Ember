package com.sailxx.ember

import java.time.LocalDate
import java.time.LocalTime
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.roundToInt

/** Окно «тепла»: start — вечером (закат), end — утром (рассвет), в минутах от полуночи. */
data class Window(val start: Int, val end: Int)

object Schedule {
    const val DAY = 1440
    /** По солнцу — закат и рассвет по месту; без места, в полярный день или ночь — своё время из настроек. */
    fun window(c: Config, date: LocalDate = LocalDate.now()): Window {
        val own = Window(c.on, c.off)
        if (!c.sun || !c.hasPlace) return own
        val set = Sun.minutes(date, c.lat, c.lon, rising = false) ?: return own
        val rise = Sun.minutes(date, c.lat, c.lon, rising = true) ?: return own
        return Window(set, rise)
    }

    fun nowMinute(): Int = LocalTime.now().let { it.hour * 60 + it.minute }

    /** Сила фильтра 0..1: плавно растёт после заката, держится ночью, плавно гаснет после рассвета. */
    fun level(c: Config, minute: Int, w: Window): Float {
        if (!c.enabled) return 0f
        if (!c.auto) return 1f
        val len = (w.end - w.start).mod(DAY)
        val pos = (minute - w.start).mod(DAY)
        val f = c.fade
        return when {
            pos < len -> if (f == 0) 1f else minOf(1f, pos.toFloat() / f)
            f > 0 && pos < len + f -> 1f - (pos - len).toFloat() / f
            else -> 0f
        }
    }

    fun hhmm(minute: Int) = "%02d:%02d".format(minute / 60, minute % 60)
}

object Tint {
    /** Кельвины → RGB (Tanner Helland), 0..255. */
    fun rgb(kelvin: Int): IntArray {
        val t = kelvin / 100.0
        val r = if (t <= 66) 255.0 else 329.698727446 * (t - 60).pow(-0.1332047592)
        val g = if (t <= 66) 99.4708025861 * ln(t) - 161.1195681661 else 288.1221695283 * (t - 60).pow(-0.0755148492)
        val b = when {
            t >= 66 -> 255.0
            t <= 19 -> 0.0
            else -> 138.5177312231 * ln(t - 10) - 305.0447927307
        }
        return doubleArrayOf(r, g, b).map { it.roundToInt().coerceIn(0, 255) }.toIntArray()
    }

    /** Android 12+ пропускает касания сквозь обычный оверлей, только если его плотность ≤ 0.8. */
    const val MAX_ALPHA = 0.75f
    /** Окно службы спец. возможностей доверенное — ограничение только ради того, чтобы экран не стал чёрным. */
    const val MAX_ALPHA_TRUSTED = 0.9f

    /**
     * Тёплый слой (альфа a) и чёрное затемнение (альфа b) складываются в одно окно:
     * экран·(1−A) + C·A, где A = 1−(1−a)(1−b), C = цвет·a·(1−b)/A.
     */
    fun layer(c: Config, level: Float, max: Float = MAX_ALPHA): Pair<Int, Float> {
        val a = (c.strength / 100f * 0.7f * level).coerceAtMost(max)
        var b = c.dim / 100f * (if (max > MAX_ALPHA) 0.75f else 0.6f) * level
        if (1 - (1 - a) * (1 - b) > max) b = 1 - (1 - max) / (1 - a)
        val alpha = 1 - (1 - a) * (1 - b)
        if (alpha <= 0.005f) return 0 to 0f
        val k = a * (1 - b) / alpha
        val (r, g, bl) = rgb(c.kelvin).map { (it * k).roundToInt() }
        return (0xFF shl 24 or (r shl 16) or (g shl 8) or bl) to alpha
    }
}
