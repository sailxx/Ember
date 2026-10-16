package com.sailxx.ember

import java.time.LocalDate
import java.time.ZoneId
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.tan

/** Восход и закат по алгоритму из Almanac for Computers (NOAA), без сети. */
object Sun {
    private const val ZENITH = 90.833

    /** Минуты от полуночи по местному времени или null, если солнце в этот день не восходит/не заходит. */
    fun minutes(date: LocalDate, lat: Double, lon: Double, rising: Boolean, zone: ZoneId = ZoneId.systemDefault()): Int? {
        val n = date.dayOfYear
        val lngHour = lon / 15
        val t = n + ((if (rising) 6.0 else 18.0) - lngHour) / 24
        val m = 0.9856 * t - 3.289
        val l = norm(m + 1.916 * sinD(m) + 0.020 * sinD(2 * m) + 282.634, 360.0)
        var ra = norm(deg(atan(0.91764 * tanD(l))), 360.0)
        ra += floor(l / 90) * 90 - floor(ra / 90) * 90
        ra /= 15
        val sinDec = 0.39782 * sinD(l)
        val cosDec = cos(asin(sinDec))
        val cosH = (cosD(ZENITH) - sinDec * sinD(lat)) / (cosDec * cosD(lat))
        if (cosH > 1 || cosH < -1) return null
        val h = (if (rising) 360 - deg(acos(cosH)) else deg(acos(cosH))) / 15
        val local = h + ra - 0.06571 * t - 6.622
        val ut = local - lngHour
        val offset = zone.rules.getOffset(date.atTime(12, 0).atZone(zone).toInstant()).totalSeconds / 3600.0
        return (norm(ut + offset, 24.0) * 60).roundToInt() % Schedule.DAY
    }

    private fun norm(v: Double, r: Double) = ((v % r) + r) % r
    private fun deg(rad: Double) = Math.toDegrees(rad)
    private fun sinD(d: Double) = sin(Math.toRadians(d))
    private fun cosD(d: Double) = cos(Math.toRadians(d))
    private fun tanD(d: Double) = tan(Math.toRadians(d))
}
