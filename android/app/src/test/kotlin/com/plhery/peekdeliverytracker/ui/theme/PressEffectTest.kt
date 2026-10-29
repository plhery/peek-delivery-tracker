package com.plhery.peekdeliverytracker.ui.theme

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import kotlin.math.roundToInt

/**
 * The press styles drawn by Android: a finger's press through the indication and away, what a button draws past its
 * bounds, the shade's colour, and a sheet.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PressEffectTest {
    @get:Rule
    val compose = createComposeRule()

    private val grey = Color(0.5f, 0.5f, 0.5f)

    /** The centre pixel of the swatch tagged `tag`, as 0…255 channels. */
    private fun centre(tag: String): IntArray = channels(tag) { bitmap -> bitmap.getPixel(bitmap.width / 2, bitmap.height / 2) }

    /** The pixel `x` and `y` from the top left of the node tagged `tag`, as 0…255 channels. */
    private fun pixel(tag: String, x: Dp, y: Dp): IntArray = channels(tag) { bitmap ->
        val density = compose.density.density
        bitmap.getPixel((x.value * density).roundToInt(), (y.value * density).roundToInt())
    }

    private fun channels(tag: String, read: (Bitmap) -> Int): IntArray {
        // Drawn now in software: Robolectric's hardware capture may return the drawing of a frame before.
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.waitForIdle()
        val bounds = compose.onNodeWithTag(tag).fetchSemanticsNode().boundsInRoot
        val whole = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(Canvas(whole)) }
        val node = Bitmap.createBitmap(whole, bounds.left.toInt(), bounds.top.toInt(), bounds.width.toInt(), bounds.height.toInt())
        val pixel = read(node)
        return intArrayOf(pixel shr 16 and 0xFF, pixel shr 8 and 0xFF, pixel and 0xFF)
    }

    private fun assertChannels(expected: Int, actual: IntArray) {
        for (channel in actual) assertEquals(expected.toDouble(), channel.toDouble(), 1.0)
    }

    @Test
    fun aFingerPressesATactileButtonAndLettingGoRestoresIt() {
        compose.mainClock.autoAdvance = false
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, carrierIdentities = null) {
                Box(Modifier.testTag("frame").background(Color.White).padding(20.dp)) {
                    Box(Modifier.testTag("button").size(80.dp).clickable(interactionSource = null, indication = PressStyle.Tactile) {}.background(Color.Black))
                }
            }
        }
        compose.mainClock.advanceTimeBy(100)
        assertChannels(0, centre("frame"))
        compose.onNodeWithTag("button").performTouchInput { down(center) }
        compose.mainClock.advanceTimeBy(400)
        // Black at 0.94 over white: 255 × 0.06.
        assertChannels(15, centre("frame"))
        compose.onNodeWithTag("button").performTouchInput { up() }
        compose.mainClock.advanceTimeBy(400)
        assertChannels(0, centre("frame"))
    }

    @Test
    fun aButtonTakenAwayWhilePressedComesBackAtRest() {
        compose.mainClock.autoAdvance = false
        val source = MutableInteractionSource()
        var first by mutableStateOf(true)
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, carrierIdentities = null) {
                // The same button moved from one place to another, its style kept: detached, then attached again.
                val button = remember {
                    movableContentOf { Box(Modifier.testTag("button").size(80.dp).indication(source, PressStyle.Tactile).background(Color.Black)) }
                }
                Row {
                    Box(Modifier.testTag("first").background(Color.White).padding(20.dp)) { if (first) button() }
                    Box(Modifier.testTag("second").background(Color.White).padding(20.dp).size(80.dp)) { if (!first) button() }
                }
            }
        }
        compose.mainClock.advanceTimeBy(100)
        compose.runOnIdle { check(source.tryEmit(PressInteraction.Press(Offset.Zero))) }
        compose.mainClock.advanceTimeBy(400)
        assertChannels(15, centre("first"))
        // Moved while pressed, it never hears the press end: it comes back at rest from its first frame.
        compose.runOnIdle {
            first = false
            Snapshot.sendApplyNotifications()
        }
        compose.mainClock.advanceTimeByFrame()
        assertChannels(0, centre("second"))
    }

    @Test
    fun whatAPressedButtonDrawsPastItsBoundsShowsWithIt() {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = true, carrierIdentities = null) {
                Box(Modifier.testTag("frame").background(Color.White).padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 60.dp)) {
                    Box(
                        Modifier
                            .size(40.dp)
                            .pressEffect(PressStyle.Tactile, pressed = true)
                            // A card's shadow, as far down as the iPhone's reach: a band 30 to 40 points below it.
                            .drawBehind { drawRect(Color.Black, Offset(0f, size.height + 30.dp.toPx()), Size(size.width, 10.dp.toPx())) }
                            .background(Color.Black),
                    )
                }
            }
        }
        // Faded with the button: black at 0.94 over white, beside it and below it.
        assertChannels(15, pixel("frame", x = 30.dp, y = 30.dp))
        assertChannels(15, pixel("frame", x = 30.dp, y = 85.dp))
    }

    @Test
    fun theShadeIsAddedToEachChannelAsSwiftUIsBrightnessIs() {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = true, carrierIdentities = null) {
                Row {
                    Box(Modifier.testTag("lift").size(40.dp).pressEffect(PressStyle.ExperimentalLift, pressed = true).background(grey))
                    Box(Modifier.testTag("hero").size(40.dp).pressEffect(PressStyle.FirstOpenHero, pressed = true).background(grey))
                    Box(Modifier.testTag("rest").size(40.dp).pressEffect(PressStyle.ExperimentalLift, pressed = false).background(grey))
                }
            }
        }
        // SwiftUI draws 0.5 grey under brightness(-0.025) as 121 and under -0.02 as 122 (ImageRenderer on a Mac); 0.5
        // is 128 here before the shade, 127.5 there.
        assertChannels(122, centre("lift"))
        assertChannels(123, centre("hero"))
        assertChannels(128, centre("rest"))
    }

    @Test
    fun stylesLight() = shoot(dark = false)

    @Test
    fun stylesDark() = shoot(dark = true)

    private fun shoot(dark: Boolean) {
        compose.setContent {
            PeekTheme(dark = dark, highContrast = false, reduceMotion = false, carrierIdentities = null) {
                Column(
                    Modifier.testTag(SHOT).background(PeekTheme.colors.background).padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    for ((name, style) in listOf(
                        "Tactile" to PressStyle.Tactile,
                        "Tactile 0.94" to PressStyle.tactile(0.94f),
                        "Lift" to PressStyle.ExperimentalLift,
                        "Passport" to PressStyle.Passport,
                        "Hero" to PressStyle.FirstOpenHero,
                    )) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Swatch(name, style, pressed = false)
                            Swatch(name, style, pressed = true)
                        }
                    }
                }
            }
        }
        compose.screenshot("press_" + if (dark) "dark" else "light")
    }
}

@Composable
private fun Swatch(name: String, style: PressStyle, pressed: Boolean) {
    val colors = PeekTheme.colors
    Box(
        Modifier.size(150.dp, 40.dp).pressEffect(style, pressed).clip(ContinuousRoundedShape(Radii.card)).background(colors.accent),
        contentAlignment = Alignment.Center,
    ) {
        Text(if (pressed) "$name, pressed" else name, style = PeekTheme.type.footnote, color = colors.onAccent)
    }
}
