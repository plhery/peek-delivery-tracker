package com.plhery.peekdeliverytracker.ui.draw

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.awaitFrame
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import kotlin.math.roundToInt

/**
 * The switch as iOS 26's: nothing drawn past its track, a tap or a drag of its knob across turning it, a drag back
 * leaving it, and its knob stretching to glass under the finger and back.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ToggleTest {
    @get:Rule
    val compose = createComposeRule()

    private val changes = mutableListOf<Boolean>()

    /** The switch, 20 in from the top left of a white frame, turned by what it asks for if it `follows`. */
    private fun show(initially: Boolean, follows: Boolean = true) {
        compose.setContent {
            var on by remember { mutableStateOf(initially) }
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, carrierIdentities = null) {
                Box(Modifier.testTag("frame").background(Color.White).padding(INSET)) {
                    Toggle(on, { changes += it; if (follows) on = it }, Modifier.testTag("switch"))
                }
            }
        }
        compose.mainClock.advanceTimeBy(100)
    }

    private fun px(dp: Dp): Int = (dp.value * compose.density.density).roundToInt()

    private fun picture(): Bitmap {
        compose.awaitFrame()
        return compose.onRoot().captureToImage().asAndroidBitmap()
    }

    /** A point of the switch `x` from its leading end, at its middle, in its pixels. */
    private fun along(x: Dp): Offset = Offset(px(x).toFloat(), px(14.dp).toFloat())

    @Test
    fun noShadowFallsPastTheTrack() {
        // One off, and one on 20 to its right.
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, carrierIdentities = null) {
                Row(Modifier.background(Color.White).padding(INSET), horizontalArrangement = Arrangement.spacedBy(INSET)) {
                    Toggle(false, {})
                    Toggle(true, {})
                }
            }
        }
        val image = picture()
        // Under each knob, from 1 to 6 below the track: the knob's shadow fell there, 3 under the track.
        for (knob in listOf(2.dp + 18.5.dp, 63.dp + INSET + 61.dp - 18.5.dp)) {
            for (below in 1..6) {
                val pixel = image.getPixel(px(INSET + knob), px(INSET + 28.dp + below.dp))
                for (channel in listOf(pixel shr 16 and 0xFF, pixel shr 8 and 0xFF, pixel and 0xFF)) {
                    assertTrue("$knob along, $below below the track: ${Integer.toHexString(pixel)}", channel >= 254)
                }
            }
        }
    }

    @Test
    fun aTapTurnsItAndTalkBackReadsASwitch() {
        show(false)
        compose.onNodeWithTag("switch").assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Switch)).assertIsOff()
        compose.onNodeWithTag("switch").performTouchInput { down(along(20.dp)); up() }
        assertEquals(listOf(true), changes)
        compose.onNodeWithTag("switch").assertIsOn()
    }

    @Test
    fun aDragOfTheKnobAcrossTheMiddleTurnsItAsTheFingerLifts() {
        show(false)
        compose.onNodeWithTag("switch").performTouchInput {
            down(along(20.dp))
            moveTo(along(32.dp))
            moveTo(along(50.dp))
            // On past the track's end, where a tap would be lost.
            moveTo(along(76.dp))
        }
        assertEquals(emptyList<Boolean>(), changes)
        compose.onNodeWithTag("switch").performTouchInput { up() }
        assertEquals(listOf(true), changes)

        // And back, past the middle the other way and the track's leading end.
        compose.onNodeWithTag("switch").performTouchInput {
            down(along(42.dp))
            moveTo(along(30.dp))
            moveTo(along(-12.dp))
            up()
        }
        assertEquals(listOf(true, false), changes)
    }

    @Test
    fun aDragBackBeforeLiftingLeavesIt() {
        show(false)
        compose.onNodeWithTag("switch").performTouchInput {
            down(along(20.dp))
            moveTo(along(50.dp))
            moveTo(along(30.dp))
            moveTo(along(10.dp))
            up()
        }
        assertEquals(emptyList<Boolean>(), changes)
        compose.onNodeWithTag("switch").assertIsOff()
    }

    @Test
    fun aDragStillPastTheMiddleLeavesItWhereItIs() {
        // The knob on, dragged further on: no change, nor the tap the finger began.
        show(true)
        compose.onNodeWithTag("switch").performTouchInput {
            down(along(40.dp))
            moveTo(along(58.dp))
            up()
        }
        assertEquals(emptyList<Boolean>(), changes)
    }

    @Test
    fun underTheFingerTheKnobStretchesTowardTheMiddleAsGlassAndSettlesBackOnLift() {
        // Held on, so the tap the press ends in leaves it where it is.
        show(true, follows = false)
        compose.mainClock.advanceTimeBy(500)
        val rest = knob(picture())
        assertEquals("at rest it starts at 24", 24.0, rest.start, 1.0)
        assertEquals("at rest it is 37 wide", 37.0, rest.width, 1.0)
        assertTrue("at rest it is white", rest.white)

        compose.onNodeWithTag("switch").performTouchInput { down(along(42.dp)) }
        compose.mainClock.advanceTimeBy(500)
        val pressed = knob(picture())
        assertEquals("pressed it still ends at 61", rest.end, pressed.end, 1.0)
        assertEquals("pressed it reaches 7 nearer the middle", 17.0, pressed.start, 1.5)
        assertTrue("pressed it is glass, not white", !pressed.white)

        compose.onNodeWithTag("switch").performTouchInput { up() }
        compose.mainClock.advanceTimeBy(600)
        val after = knob(picture())
        assertEquals(rest.start, after.start, 1.0)
        assertEquals(rest.width, after.width, 1.0)
        assertTrue("let go it is white again", after.white)
        // Lifted where it came down, the press was a tap, asking to turn it.
        assertEquals(listOf(false), changes)
    }

    private class Knob(val start: Double, val end: Double, val white: Boolean) {
        val width get() = end - start
    }

    /** Where along the switch's middle the knob, lighter than the track, starts and ends, in dp from its leading end. */
    private fun knob(image: Bitmap): Knob {
        val y = px(INSET + 14.dp)
        fun luma(pixel: Int) = 0.299 * (pixel shr 16 and 0xFF) + 0.587 * (pixel shr 8 and 0xFF) + 0.114 * (pixel and 0xFF)
        val track = luma(image.getPixel(px(INSET + 4.dp), y))
        // Within the track's ends, past the knob's furthest reach either way.
        val lighter = (px(INSET + 1.dp) until px(INSET + 62.dp)).filter { luma(image.getPixel(it, y)) > track + 20 }
        val white = lighter.any { x -> image.getPixel(x, y).let { (it shr 16 and 0xFF) >= 252 && (it shr 8 and 0xFF) >= 252 && (it and 0xFF) >= 252 } }
        val density = compose.density.density
        return Knob((lighter.first() - px(INSET)) / density.toDouble(), (lighter.last() + 1 - px(INSET)) / density.toDouble(), white)
    }

    private companion object {
        val INSET = 20.dp
    }
}
