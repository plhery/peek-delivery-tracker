package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.PipBlinkSchedule
import com.plhery.peekdeliverytracker.core.PipMood
import com.plhery.peekdeliverytracker.core.PipOpening
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
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

    /**
     * A display drawing a frame `hz` times a second on the test's clock, counting the frames asked for. The frames
     * numbered in `late` don't come: their callbacks run at the next, as a frame that runs late does.
     */
    private class Display(private val hz: Long, private val scheduler: TestCoroutineScheduler, private val late: Set<Long> = emptySet()) : MonotonicFrameClock {
        var wakes = 0
            private set
        private var last = 0L

        /** The time of the display's `n`th frame from the test's start, in nanoseconds. */
        fun frame(n: Long): Long = n * 1_000_000_000L / hz

        override suspend fun <R> withFrameNanos(onFrame: (frameTimeNanos: Long) -> R): R {
            var n = next()
            while (n in late) n++
            // The callback runs in the millisecond the frame falls in, with the frame's own time.
            delay(millis(n) - scheduler.currentTime)
            last = n
            wakes++
            return onFrame(frame(n))
        }

        /** The display's next frame from now. */
        fun next(): Long = maxOf(last + 1, scheduler.currentTime * 1_000_000 * hz / 1_000_000_000L + 1)

        /** The millisecond the display's `n`th frame's callback runs in. */
        fun millis(n: Long): Long = (frame(n) + 999_999) / 1_000_000
    }

    /** The test's wall on the test's own clock. */
    private fun TestScope.testWall(): () -> Double = { 800_000_000.0 + testScheduler.currentTime / 1000.0 }

    /** The dates the clock drew in the next `millis`, looked at each ms, and how often it woke for a frame meanwhile. */
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun TestScope.watch(clock: InkPipClock, display: Display, millis: Int): Pair<List<Double>, Int> {
        val wakes = display.wakes
        val dates = mutableListOf<Double>()
        repeat(millis) {
            val last = clock.date
            testScheduler.advanceTimeBy(1)
            testScheduler.runCurrent()
            if (clock.date != last) dates += clock.date
        }
        return Pair(dates, display.wakes - wakes)
    }

    /**
     * Each of `dates` after the first is the display's first frame at its own 30th of a second from the first, or up
     * to 2 ms before it for the display's jitter: none skipped, none twice.
     */
    private fun assertThirtiethsFrom(first: Double, dates: List<Double>, hz: Long, start: Int = 0) {
        for ((index, date) in dates.withIndex()) {
            val since = date - first
            val due = (start + index) / 30.0 - 0.002
            assertTrue("$hz Hz, frame ${start + index} at $since", since > due - 1e-6 && since < due + 1.0 / hz + 1e-6)
        }
    }

    @Test
    fun eagerHeBobsThirtyTimesASecondAtAnyRefreshRateAndSleepsBetween() = runTest {
        for (hz in listOf(48L, 60L, 90L, 120L, 144L)) {
            val display = Display(hz, testScheduler)
            val firstMillis = display.millis(display.next())
            val clock = InkPipClock(PipMood.Eager, reduceMotion = false, testWall())
            val pip = launch(display) { clock.run() }
            val (dates, wakes) = watch(clock, display, 2_000)
            pip.cancel()
            // His first frame is the display's first, on the wall's time.
            assertEquals(800_000_000.0 + firstMillis / 1000.0, dates.first(), 1e-6)
            assertThirtiethsFrom(dates.first(), dates, hz)
            assertTrue("$hz Hz: ${dates.size} frames", dates.size >= 60)
            // Asleep in between: a wake-up a frame where the display's frames fall on the 30ths, two for a frame whose
            // display frame before falls just before it is due, and always fewer than the display's own frames.
            if (hz % 30 == 0L) assertEquals("$hz Hz", dates.size, wakes)
            assertTrue("$hz Hz: $wakes wakes", wakes <= 2 * dates.size && wakes < 2 * hz)
        }
    }

    @Test
    fun aLateFrameDoesNotPushBackTheOnesAfterIt() = runTest {
        // At 60 Hz his 30ths are every other frame from the display's first; his third, its 7th, comes a frame late.
        val display = Display(60, testScheduler, late = setOf(7L))
        val clock = InkPipClock(PipMood.Eager, reduceMotion = false, testWall())
        val pip = launch(display) { clock.run() }
        val (dates, wakes) = watch(clock, display, 1_000)
        pip.cancel()
        assertEquals(0.1 + 1.0 / 60, dates[3] - dates[0], 1e-6)
        assertThirtiethsFrom(dates[0], dates.take(3), 60)
        assertThirtiethsFrom(dates[0], dates.drop(4), 60, start = 4)
        assertEquals(dates.size, wakes)
    }

    @Test
    fun heWakesAtEveryFrameOnlyWhileHisBoxOpens() = runTest {
        val display = Display(125, testScheduler)
        val clock = InkPipClock(PipMood.Eager, reduceMotion = false, testWall())
        var pip = launch(display) { clock.run() }
        watch(clock, display, 500)
        // The news, as rememberInkPipClock passes it on: his clock starts again.
        pip.cancel()
        clock.show(PipMood.Joy, reduceMotion = false)
        pip = launch(display) { clock.run() }
        // Every frame of the display for the second the box takes to open, from his first after the news.
        val (opening, wakes) = watch(clock, display, 1_000)
        assertEquals(125, opening.size)
        assertEquals(125, wakes)
        for ((earlier, later) in opening.zipWithNext()) assertEquals(0.008, later - earlier, 1e-6)
        // Open, he jumps 30 times a second, as eager he bobbed, on the 30ths from his first frame after the news.
        val (jumps, rest) = watch(clock, display, 1_000)
        assertEquals(30, jumps.size)
        assertThirtiethsFrom(opening.first(), jumps, 125, start = 30)
        assertTrue("$rest", rest <= 2 * jumps.size)
        assertTrue(checkNotNull(clock.frame(PipMood.Joy).time) > PipOpening.DURATION)
        pip.cancel()
    }

    @Test
    fun heStartsToMoveAtTheWallsTimeNotWhereHeStopped() = runTest {
        val wall = testWall()
        val display = Display(60, testScheduler)
        // Still when he shows, eager five seconds later: his first frame, before his clock runs, is at the wall's time.
        val clock = InkPipClock(PipMood.Look, reduceMotion = false, wall)
        testScheduler.advanceTimeBy(5_000)
        clock.show(PipMood.Eager, reduceMotion = false)
        assertEquals(wall(), checkNotNull(clock.frame(PipMood.Eager).time), 1e-6)
        // The same when Reduce Motion has held him still for three seconds.
        val pip = launch(display) { clock.run() }
        watch(clock, display, 500)
        pip.cancel()
        clock.show(PipMood.Eager, reduceMotion = true)
        assertNull(clock.frame(PipMood.Eager).time)
        testScheduler.advanceTimeBy(3_000)
        clock.show(PipMood.Eager, reduceMotion = false)
        assertEquals(wall(), checkNotNull(clock.frame(PipMood.Eager).time), 1e-6)
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
        // Open after a second: drawn open, and still jumping from the news, at most a 30th of a second behind.
        compose.mainClock.advanceTimeBy(800)
        frame = clock.frame(PipMood.Joy)
        assertNull(frame.opening)
        assertEquals(PipMood.Look, frame.before)
        assertEquals(times.last() + 0.8 - 1.0 / 60, checkNotNull(frame.time), 1.0 / 60)
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
