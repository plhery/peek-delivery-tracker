package com.plhery.peekdeliverytracker.ui.theme

import android.view.KeyEvent
import android.view.View
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.input.InputModeManager
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performMouseInput
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.awaitFrame
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.sheet.RowHighlight
import kotlin.math.abs
import kotlin.math.sqrt
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * What a control shows besides its press (A-G05, Full Keyboard Access): the ring just outside it while the keyboard has
 * its focus, whatever its press style or none, never under a touch, and the pointer's highlight where it asks for it.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ControlHighlightTest {
    @get:Rule
    val compose = createComposeRule()

    private val interactions = MutableInteractionSource()
    private var touched by mutableStateOf(false)
    private lateinit var view: View
    private var accent = Color.Unspecified

    /**
     * A 40-point control in the middle of 100 points of white, pressing with `indication`, or the theme's. While
     * [touched], the controls read the window's input mode as touch, as after a finger on the screen: a test can't put
     * the window back in touch mode, which only a real touch does.
     */
    private fun show(indication: Indication?, themes: Boolean = false) {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, carrierIdentities = null) {
                view = LocalView.current
                accent = Color(LocalResources.current.getColor(android.R.color.system_accent1_600, null))
                val window = LocalInputModeManager.current
                val modes = remember(window) {
                    object : InputModeManager by window {
                        override val inputMode: InputMode get() = if (touched) InputMode.Touch else window.inputMode
                    }
                }
                CompositionLocalProvider(LocalInputModeManager provides modes) {
                    Box(Modifier.size(100.dp).background(Color.White).testTag(AREA), contentAlignment = Alignment.Center) {
                        val control = Modifier.size(40.dp).background(Color.White).testTag(CONTROL)
                        Box(if (themes) control.clickable { } else control.clickable(interactions, indication) { })
                    }
                }
            }
        }
        compose.waitForIdle()
    }

    /** A key pressed and let go on a hardware keyboard, through the window as the system sends it. */
    private fun press(key: Int) {
        val window = view.rootView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, key, 0, 0))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, key, 0, 0))
        }
        compose.waitForIdle()
    }

    /** Tab pressed until the control has the keyboard's focus, from wherever the window has it. */
    private fun tabToTheControl() {
        repeat(3) {
            if (compose.onNodeWithTag(CONTROL).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Focused) == true) return
            press(KeyEvent.KEYCODE_TAB)
        }
        error("Tab never reached the control")
    }

    /** The pixel `dx`, `dy` points from the area's middle, once the window has drawn a frame of it as it is ([awaitFrame]). */
    private fun pixel(dx: Float, dy: Float): Color {
        compose.awaitFrame()
        val map = compose.onNodeWithTag(AREA).captureToImage().toPixelMap()
        val scale = view.resources.displayMetrics.density
        return map[(map.width / 2 + dx * scale).toInt(), (map.height / 2 + dy * scale).toInt()]
    }

    /** 1.5 points past the control's top edge: the middle of the ring. */
    private fun ring(): Color = pixel(0f, -21.5f)

    private fun assertNear(expected: Color, actual: Color, tolerance: Float = 0.04f) {
        val distance = maxOf(abs(expected.red - actual.red), abs(expected.green - actual.green), abs(expected.blue - actual.blue))
        assertTrue("$actual, not $expected", distance < tolerance)
    }

    @Test
    fun aPressStyleRingsItsControlOnlyWhileTheKeyboardHasTheFocus() {
        show(PressStyle.Tactile)
        tabToTheControl()
        assertNear(accent, ring())
        // Inside the control and farther out, nothing.
        assertNear(Color.White, pixel(0f, -17f))
        assertNear(Color.White, pixel(0f, -26f))
        // After a finger on the screen, no ring, even while the focus stays.
        touched = true
        compose.waitForIdle()
        assertNear(Color.White, ring())
    }

    /** The theme's indication rings a square control as the circle it is drawn as: round its corners, not at them. */
    @Test
    fun everyClickableRingsWithTheThemesIndication() {
        show(null, themes = true)
        tabToTheControl()
        assertNear(accent, ring())
        val diagonal = 21.5f / sqrt(2f)
        assertNear(accent, pixel(-diagonal, -diagonal))
        assertNear(Color.White, pixel(-21.5f, -21.5f))
    }

    @Test
    fun aRowsHighlightRingsItsRow() {
        show(RowHighlight.Row)
        tabToTheControl()
        assertNear(accent, ring())
    }

    /**
     * `.hoverEffect(.highlight)`: the quaternary fill over the control while a pointer is over it, where it asks. Told
     * against the control as drawn just before, as the window may shade all it draws a little.
     */
    @Test
    fun aPointerLightsUpAControlThatAsksForIt() {
        show(PressStyle.ExperimentalLift.copy(shape = ContinuousRoundedShape(8.dp), hover = true))
        val before = pixel(0f, 0f)
        compose.onNodeWithTag(CONTROL).performMouseInput { moveTo(center) }
        assertNear(Color(116, 116, 128).copy(alpha = 0.08f).compositeOver(before), pixel(0f, 0f), tolerance = 0.01f)
        compose.onNodeWithTag(CONTROL).performMouseInput { moveTo(Offset(-20f, -20f)) }
        assertNear(before, pixel(0f, 0f), tolerance = 0.01f)
    }

    @Test
    fun aPressStyleThatDoesntAskStaysUnlit() {
        show(PressStyle.ExperimentalLift)
        val before = pixel(0f, 0f)
        compose.onNodeWithTag(CONTROL).performMouseInput { moveTo(center) }
        assertNear(before, pixel(0f, 0f), tolerance = 0.01f)
    }
}

private const val AREA = "area"
private const val CONTROL = "control"
