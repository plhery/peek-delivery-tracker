package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
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

/** The press styles drawn by Android: a finger's press through the indication, the shade's colour, and a sheet. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PressEffectTest {
    @get:Rule
    val compose = createComposeRule()

    private val grey = Color(0.5f, 0.5f, 0.5f)

    /** The centre pixel of the swatch tagged `tag`, as 0…255 channels. */
    private fun centre(tag: String): IntArray {
        // Robolectric may keep a drawing from before the last frame: draw the window again first.
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.runOnUiThread { view.rootView.invalidate() }
        compose.waitForIdle()
        val bitmap = compose.onNodeWithTag(tag).captureToImage().asAndroidBitmap()
        val pixel = bitmap.getPixel(bitmap.width / 2, bitmap.height / 2)
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
