package com.sailxx.ember

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneId

class ScheduleTest {
    private val moscow = ZoneId.of("Europe/Moscow")

    @Test
    fun moscowOctober() {
        val d = LocalDate.of(2026, 10, 16)
        val rise = Sun.minutes(d, 55.75, 37.62, rising = true, zone = moscow)!!
        val set = Sun.minutes(d, 55.75, 37.62, rising = false, zone = moscow)!!
        // Солнечный полдень в Москве ~12:15, долгота дня ~10 ч 24 мин.
        assertTrue("восход $rise", rise in 6 * 60 + 55..7 * 60 + 10)
        assertTrue("закат $set", set in 17 * 60 + 20..17 * 60 + 35)
    }

    @Test
    fun polarNight() {
        assertEquals(null, Sun.minutes(LocalDate.of(2026, 12, 21), 78.2, 15.6, rising = true, zone = ZoneId.of("Europe/Oslo")))
    }

    @Test
    fun levelFades() {
        val c = Config(enabled = true, fade = 30)
        val w = Window(19 * 60, 7 * 60)
        assertEquals(0f, Schedule.level(c, 12 * 60, w))
        assertEquals(0.5f, Schedule.level(c, 19 * 60 + 15, w))
        assertEquals(1f, Schedule.level(c, 2 * 60, w))
        assertEquals(0.5f, Schedule.level(c, 7 * 60 + 15, w))
        assertEquals(0f, Schedule.level(c.copy(enabled = false), 2 * 60, w))
    }

    @Test
    fun touchesPassThrough() {
        val (_, alpha) = Tint.layer(Config(strength = 100, dim = 100), 1f)
        assertTrue(alpha <= Tint.MAX_ALPHA + 1e-4f)
    }
}
