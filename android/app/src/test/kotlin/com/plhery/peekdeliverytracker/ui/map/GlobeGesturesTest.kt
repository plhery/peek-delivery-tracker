package com.plhery.peekdeliverytracker.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * The globe's gestures read from the touches, as SwiftUI's simultaneous drag and pinch: the pinch follows the two
 * fingers it started with, whatever other fingers do, ends as one of them lifts, and the drag waits for 4 points.
 */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = PHONE)
class GlobeGesturesTest {
    @get:Rule
    val compose = createComposeRule()

    private val drags = mutableListOf<Offset>()
    private val pinches = mutableListOf<Float>()
    private var ends = 0

    private fun show() {
        compose.setContent {
            Box(
                Modifier
                    .fillMaxSize()
                    .testTag(AREA)
                    .pointerInput(Unit) {
                        detectGlobeGestures(pinchOnly = false, onDrag = { drags += it }, onPinch = { scale, _ -> pinches += scale }, onEnd = { ends++ })
                    },
            )
        }
    }

    private val area get() = compose.onNodeWithTag(AREA)

    /** Fingers 1 and 2 down 100 pixels apart about the middle, then spread to 200. */
    private fun pinchToTwice() {
        area.performTouchInput {
            down(1, center - Offset(50f, 0f))
            down(2, center + Offset(50f, 0f))
            updatePointerTo(1, center - Offset(100f, 0f))
            updatePointerTo(2, center + Offset(100f, 0f))
            move()
        }
        assertEquals(2f, pinches.last(), 1e-4f)
    }

    @Test
    fun aThirdFingerLandingChangesNothing() {
        show()
        pinchToTwice()
        val before = pinches.size
        // With a lower id, as Android gives a finger that lands once another has lifted: it comes first in each event.
        area.performTouchInput {
            down(0, center + Offset(0f, 300f))
            moveTo(0, center + Offset(40f, 400f))
        }
        assertTrue("Pinched to ${pinches.drop(before)}", pinches.drop(before).all { it == 2f })
        // The two it started with still pinch.
        area.performTouchInput {
            updatePointerTo(1, center - Offset(150f, 0f))
            updatePointerTo(2, center + Offset(150f, 0f))
            move()
        }
        assertEquals(3f, pinches.last(), 1e-4f)
        assertTrue(drags.isEmpty())
    }

    @Test
    fun oneOfThePairLiftingEndsThePinchWithoutAJump() {
        show()
        pinchToTwice()
        area.performTouchInput { down(3, center + Offset(0f, 300f)) }
        val before = pinches.size
        area.performTouchInput {
            up(1)
            moveTo(2, center + Offset(160f, 80f))
            moveTo(3, center + Offset(60f, 360f))
        }
        // Over: no zoom from the fingers left, and nothing turns until every finger has lifted, as the iPhone's
        // `zooming` holds until the gesture ends.
        assertEquals(before, pinches.size)
        assertTrue(drags.isEmpty())
        assertEquals(0, ends)
        area.performTouchInput {
            up(2)
            up(3)
        }
        assertEquals(1, ends)
    }

    @Test
    fun oneFingerTurnsOnlyOnceItHasGoneFourPoints() {
        show()
        val three = with(compose.density) { 3.dp.toPx() }
        val four = with(compose.density) { 4.dp.toPx() }
        area.performTouchInput {
            down(0, center)
            moveTo(0, center + Offset(three, 0f))
        }
        assertTrue(drags.isEmpty())
        // A finger lifted short of it moved nothing, so nothing frees the map.
        area.performTouchInput { up(0) }
        assertEquals(0, ends)

        area.performTouchInput {
            down(0, center)
            moveTo(0, center + Offset(0f, four))
        }
        assertEquals(Offset(0f, four), drags.single())
        area.performTouchInput { up(0) }
        assertEquals(1, ends)
    }

    private companion object {
        const val AREA = "globe"
    }
}
