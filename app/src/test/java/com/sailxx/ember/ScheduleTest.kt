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
    fun shortWindowDoesNotJumpToFullStrength() {
        val c = Config(enabled = true, fade = 30)
        for (start in listOf(600, 1435)) {
            val w = Window(start, (start + 10) % Schedule.DAY)
            val before = Schedule.level(c, (start + 9) % Schedule.DAY, w)
            val peak = Schedule.level(c, (start + 10) % Schedule.DAY, w)
            assertTrue(peak - before < 0.04f)
            assertEquals(1f / 3f, peak, 0.0001f)
            assertEquals(1f / 6f, Schedule.level(c, (start + 25) % Schedule.DAY, w), 0.0001f)
            assertEquals(0f, Schedule.level(c, (start + 40) % Schedule.DAY, w))
        }
        assertEquals(0f, Schedule.level(c, 600, Window(600, 600)))
        assertEquals(1f, Schedule.level(c.copy(fade = 0), 605, Window(600, 610)))
    }

    @Test
    fun longWindowFadesOutBeforeNextStart() {
        val c = Config(enabled = true, fade = 30)
        val w = Window(8 * 60, 7 * 60 + 50)
        assertEquals(1f, Schedule.level(c, 7 * 60 + 50, w))
        assertEquals(0.5f, Schedule.level(c, 7 * 60 + 55, w), 0.0001f)
        assertEquals(0.1f, Schedule.level(c, 7 * 60 + 59, w), 0.0001f)
        assertEquals(0f, Schedule.level(c, 8 * 60, w))
        assertEquals(1f / 30f, Schedule.level(c, 8 * 60 + 1, w), 0.0001f)
    }

    @Test
    fun filterStatusMatchesWhetherItDraws() {
        val c = Config(enabled = true, auto = false)
        val w = Window(21 * 60, 7 * 60)
        val minute = 12 * 60
        fun state(config: Config, trusted: Boolean = false, canDraw: Boolean = true) =
            FilterState.current(config, minute, w, trusted, canDraw)

        val disabled = state(c.copy(enabled = false), canDraw = false)
        val missingPermission = state(c, canDraw = false)
        val zeroStrength = state(c.copy(strength = 0, dim = 0))
        val waiting = state(c.copy(auto = true))
        for (inactive in listOf(disabled, missingPermission, zeroStrength, waiting)) {
            assertEquals(0f, inactive.alpha)
        }
        assertEquals(4, setOf(disabled.text, missingPermission.text, zeroStrength.text, waiting.text).size)
        assertTrue(state(c, trusted = true, canDraw = false).alpha > 0f)
        assertTrue(state(c.copy(strength = 0, dim = 50)).alpha > 0f)
    }

    @Test
    fun touchesPassThrough() {
        val (_, alpha) = Tint.layer(Config(strength = 100, dim = 100), 1f)
        assertTrue(alpha <= Tint.MAX_ALPHA + 1e-4f)
    }

    @Test
    fun ownTime() {
        val c = Config(sun = false, on = 22 * 60 + 30, off = 6 * 60, lat = 55.75, lon = 37.62)
        assertEquals(Window(22 * 60 + 30, 6 * 60), Schedule.window(c))
        // По солнцу, но место не задано — тоже своё время
        assertEquals(Window(21 * 60, 7 * 60), Schedule.window(Config()))
    }
}
