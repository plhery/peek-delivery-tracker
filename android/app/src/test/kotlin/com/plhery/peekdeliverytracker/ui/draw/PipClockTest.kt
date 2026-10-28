package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.PipBlinkSchedule
import com.plhery.peekdeliverytracker.core.PipMood
import com.plhery.peekdeliverytracker.core.PipOpening
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Pip's clocks on Compose's frame clock, whose frames come every 16 ms: his blink, his bob and his box's opening. */
@RunWith(AndroidJUnit4::class)
class PipClockTest {
    @get:Rule
    val compose = createComposeRule()

    private fun change(write: () -> Unit) = compose.runOnIdle {
        write()
        Snapshot.sendApplyNotifications()
    }

    @Test
    fun theBlinkSleepsBetweenBlinksAndWakesForEachOfTheirFrames() {
        compose.mainClock.autoAdvance = false
        lateinit var clock: PipBlinkClock
        compose.setContent { clock = rememberPipBlinkClock(blinks = true) }
        val seen = mutableListOf<Double>()
        repeat(625) {
            compose.mainClock.advanceTimeByFrame()
            val time = checkNotNull(clock.time)
            if (seen.lastOrNull() != time) seen += time
        }
        // Ten seconds: the times are the schedule's, in its order, from the moment it showed.
        val schedule = PipBlinkSchedule().entries(0.0).takeWhile { it < 10.0 }.toList()
        assertEquals(0.0, seen.first(), 0.0)
        assertTrue(seen.toString(), schedule.containsAll(seen) && seen == seen.sorted())
        // Still until the eyes start to close at 93 % of 4.6 s, then a time for nearly every frame of the blink.
        assertEquals(PipBlinkSchedule().entries(0.0).elementAt(1), seen[1], 0.0)
        val firstBlink = seen.filter { it > 4 && it < 4.7 }
        assertTrue("$firstBlink", firstBlink.size >= 18)
        assertEquals(4.62, firstBlink.last(), 1e-9)
        // Then still again until the next.
        assertEquals((1 + 0.93) * 4.6, seen.first { it > 4.62 }, 0.0)
    }

    @Test
    fun withoutBlinksItHasNoTime() {
        compose.mainClock.autoAdvance = false
        var blinks by mutableStateOf(false)
        lateinit var clock: PipBlinkClock
        compose.setContent { clock = rememberPipBlinkClock(blinks) }
        compose.mainClock.advanceTimeBy(5_000)
        assertNull(clock.time)
        // It starts blinking on the clock that started when it showed: five seconds in, the next blink is at 8.878 s.
        change { blinks = true }
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(compose.mainClock.currentTime / 1000.0 - 1.0, checkNotNull(clock.time), 0.05)
        compose.mainClock.advanceTimeBy(3_000)
        assertTrue("${clock.time}", checkNotNull(clock.time) >= 8.878)
    }

    /** The test's wall: seconds since 2001 as the frame clock counts them, from an arbitrary moment. */
    private val wall = { 800_000_000.0 + compose.mainClock.currentTime / 1000.0 }

    @Test
    fun eagerHeBobsThirtyTimesASecond() {
        compose.mainClock.autoAdvance = false
        lateinit var clock: InkPipClock
        compose.setContent { clock = rememberInkPipClock(PipMood.Eager, reduceMotion = false, wall = wall) }
        val times = mutableListOf<Double>()
        repeat(60) {
            compose.mainClock.advanceTimeByFrame()
            val time = checkNotNull(clock.frame(PipMood.Eager).time)
            if (times.lastOrNull() != time) times += time
        }
        // On the wall's time, every other 16 ms frame.
        assertTrue(times.first() > 800_000_000)
        assertEquals(30, times.size)
        for ((earlier, later) in times.zipWithNext()) assertEquals(0.032, later - earlier, 1e-6)
    }

    @Test
    fun stillHeHasNoTime() {
        compose.mainClock.autoAdvance = false
        lateinit var clock: InkPipClock
        lateinit var still: InkPipClock
        compose.setContent {
            clock = rememberInkPipClock(PipMood.Look, reduceMotion = false, wall = wall)
            still = rememberInkPipClock(PipMood.Eager, reduceMotion = true, wall = wall)
        }
        compose.mainClock.advanceTimeBy(1_000)
        for ((pip, mood) in listOf(clock to PipMood.Look, still to PipMood.Eager)) {
            val frame = pip.frame(mood)
            assertNull(frame.time)
            assertNull(frame.opening)
            assertNull(frame.before)
        }
    }

    @Test
    fun theNewsOpensHisBoxOverASecondAndHeJumpsFromIt() {
        compose.mainClock.autoAdvance = false
        var mood by mutableStateOf(PipMood.Look)
        lateinit var clock: InkPipClock
        compose.setContent { clock = rememberInkPipClock(mood, reduceMotion = false, wall = wall) }
        compose.mainClock.advanceTimeBy(500)
        change { mood = PipMood.Joy }
        compose.mainClock.advanceTimeByFrame()
        // At the news his box is as it was, his face the one he had.
        var frame = clock.frame(PipMood.Joy)
        assertEquals(PipOpening.at(0.0), frame.opening)
        assertEquals(PipMood.Look, frame.before)
        assertEquals(0.0, checkNotNull(frame.time), 0.0)
        // Every frame while it opens, from his first frame after the news.
        val times = (1..30).map {
            compose.mainClock.advanceTimeByFrame()
            checkNotNull(clock.frame(PipMood.Joy).time)
        }
        assertEquals(0.0, times.first(), 0.0)
        for ((earlier, later) in times.zipWithNext()) assertEquals(0.016, later - earlier, 1e-6)
        assertEquals(PipOpening.at(times.last()), clock.frame(PipMood.Joy).opening)
        // Open after a second: drawn open, and still jumping from the news.
        compose.mainClock.advanceTimeBy(800)
        frame = clock.frame(PipMood.Joy)
        assertNull(frame.opening)
        assertEquals(PipMood.Look, frame.before)
        assertEquals(times.last() + 0.8, checkNotNull(frame.time), 1e-6)
        // Any other change ends it.
        change { mood = PipMood.Eager }
        compose.mainClock.advanceTimeByFrame()
        frame = clock.frame(PipMood.Eager)
        assertNull(frame.before)
        assertTrue(checkNotNull(frame.time) > 800_000_000)
    }

    @Test
    fun underReduceMotionTheNewsFindsHisBoxOpen() {
        compose.mainClock.autoAdvance = false
        var mood by mutableStateOf(PipMood.Eager)
        lateinit var clock: InkPipClock
        compose.setContent { clock = rememberInkPipClock(mood, reduceMotion = true, wall = wall) }
        compose.mainClock.advanceTimeBy(500)
        change { mood = PipMood.Joy }
        compose.mainClock.advanceTimeBy(100)
        val frame = clock.frame(PipMood.Joy)
        assertNull(frame.opening)
        assertNull(frame.before)
        assertNull(frame.time)
    }
}
