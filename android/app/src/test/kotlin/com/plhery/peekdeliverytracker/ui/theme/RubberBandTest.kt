package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipe
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import kotlin.math.abs
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** The iPhone's bounce at the ends of a scroll view, in place of Android's stretch. */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = PHONE)
class RubberBandTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun theBandGivesLessAndLessNeverTheWholeLength() {
        // (1 - 1 / (100 × 0.55 / 1000 + 1)) × 1000.
        assertEquals(52.133f, RubberBandOverscroll.band(100f, 1000), 0.001f)
        assertEquals(-52.133f, RubberBandOverscroll.band(-100f, 1000), 0.001f)
        assertTrue(RubberBandOverscroll.band(1e9f, 1000) < 1000f)
        assertEquals(0f, RubberBandOverscroll.band(100f, 0), 0f)
        for (finger in listOf(1f, 30f, 300f, 3000f, -450f)) {
            assertEquals(finger, RubberBandOverscroll.finger(RubberBandOverscroll.band(finger, 800), 800), abs(finger) * 1e-4f)
        }
    }

    private lateinit var scroll: ScrollState
    private var slop = 0f

    /** A list 200 dp wide and 400 dp (800 px) tall, of 20 rows 100 dp tall, in `around`. */
    private fun list(around: Modifier = Modifier) {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, carrierIdentities = null) {
                slop = LocalViewConfiguration.current.touchSlop
                scroll = rememberScrollState()
                Box(around) {
                    Column(Modifier.testTag("list").size(200.dp, 400.dp).verticalScroll(scroll)) {
                        repeat(20) { Box(Modifier.testTag("row$it").fillMaxWidth().height(100.dp)) }
                    }
                }
            }
        }
        compose.waitForIdle()
    }

    private fun firstRowTop(): Float = compose.onNodeWithTag("row0").fetchSemanticsNode().positionInRoot.y -
        compose.onNodeWithTag("list").fetchSemanticsNode().positionInRoot.y

    @Test
    fun draggedPastTheTopTheListFollowsTheBandThenSpringsBack() {
        list()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag("list").performTouchInput {
            down(center)
            moveBy(Offset(0f, 300f))
        }
        compose.mainClock.advanceTimeByFrame()
        assertEquals(RubberBandOverscroll.band(300f - slop, 800), firstRowTop(), 1f)
        assertEquals(0, scroll.value)

        // Held still, then let go: it springs back to the top.
        compose.onNodeWithTag("list").performTouchInput {
            advanceEventTime(200)
            up()
        }
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertEquals(0f, firstRowTop(), 0.5f)
        assertEquals(0, scroll.value)
    }

    @Test
    fun movingBackTakesBackWhatIsPastTheEndBeforeScrolling() {
        list()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag("list").performTouchInput {
            down(center)
            moveBy(Offset(0f, 300f))
            moveBy(Offset(0f, -300f))
        }
        compose.mainClock.advanceTimeByFrame()
        // The finger is a touch slop above where it started: the list has scrolled that far, and nothing is past its end.
        assertEquals(slop, scroll.value.toFloat(), 1f)
        assertEquals(-scroll.value.toFloat(), firstRowTop(), 0.5f)
        compose.onNodeWithTag("list").performTouchInput { up() }
    }

    /** A frame clock 16 ms a frame that notes something before each frame. */
    private class Frames(private val beforeEach: () -> Unit) : MonotonicFrameClock {
        private var nanos = 0L

        override suspend fun <R> withFrameNanos(onFrame: (Long) -> R): R {
            beforeEach()
            nanos += 16_000_000L
            return onFrame(nanos)
        }
    }

    @Test
    fun aFlingIntoAnEndGoesPastItThenSpringsBack() {
        val effect = RubberBandOverscroll()
        val shown = mutableListOf<Float>()
        // Nothing left to scroll: the content takes none of the fling, and the whole of it reaches the end.
        runBlocking(Frames { shown += effect.offset.y }) { effect.applyToFling(Velocity(0f, 3000f)) { Velocity.Zero } }
        // A critically damped spring of stiffness 100 leaving at 3000 px/s goes furthest at 0.1 s: 3000 × 0.1 / e.
        assertEquals(110.4f, shown.max(), 0.5f)
        assertEquals(Offset.Zero, effect.offset)
        assertTrue(shown.all { it >= 0f })
    }

    @Test
    fun aFlingTheContentTakesWholeLeavesNothingPastTheEnd() {
        val effect = RubberBandOverscroll()
        val shown = mutableListOf<Float>()
        runBlocking(Frames { shown += effect.offset.y }) { effect.applyToFling(Velocity(0f, 3000f)) { it } }
        assertTrue("Shown past the end: ${shown.maxOrNull()}", shown.all { it == 0f })
        assertEquals(Offset.Zero, effect.offset)
    }

    @Test
    fun letGoPastTheTopTheListSpringsBackAndWhatNestsItHearsTheFling() {
        var flings = 0
        val nest = object : NestedScrollConnection {
            override suspend fun onPreFling(available: Velocity): Velocity {
                flings++
                return Velocity.Zero
            }
        }
        list(Modifier.nestedScroll(nest))
        // Pulled down past the top and let go still heading down.
        compose.onNodeWithTag("list").performTouchInput { swipe(center, center + Offset(0f, 300f), 100) }
        compose.waitForIdle()
        assertEquals(1, flings)
        assertEquals(0f, firstRowTop(), 0.5f)
        assertEquals(0, scroll.value)
    }
}
