package com.plhery.peekdeliverytracker.ui.sheet

import android.view.View
import androidx.activity.BackEventCompat
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The sheet on its own, a field in what it holds: what takes it to its large height, a keyboard up for that field and
 * a window as low as a phone on its side; a drag that moves it without composing what it holds again; and the sheet
 * opened again on its way out after the back gesture.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class SheetTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var view: View
    private var visible by mutableStateOf(true)
    private var text by mutableStateOf("")
    private var compositions = 0

    private fun show() {
        compose.setContent {
            view = LocalView.current
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = null) {
                Box(Modifier.fillMaxSize()) {
                    Sheet(visible, onDismiss = { visible = false }, paneTitle = "Sheet") {
                        SideEffect { compositions++ }
                        SheetPage("Sheet", "Done", onConfirm = { visible = false }) {
                            FormSection("Name") {
                                BasicTextField(text, { text = it }, Modifier.testTag(FIELD))
                            }
                            FormSection(null) {
                                repeat(12) { FormLabelRow("Row $it") }
                            }
                        }
                    }
                }
            }
        }
        compose.waitForIdle()
    }

    /** The sheet's top, in points from the window's. */
    private fun top(): Float = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot().top.value

    private fun windowHeight(): Float = compose.onRoot().getUnclippedBoundsInRoot().bottom.value

    /** The keyboard `height` points tall, or gone at 0, as the window's insets tell it. */
    private fun keyboard(height: Int) {
        compose.runOnUiThread {
            val px = (height * view.resources.displayMetrics.density).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.ime(), Insets.of(0, 0, 0, px))
                .setVisible(WindowInsetsCompat.Type.ime(), px > 0)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
    }

    @Test
    fun aKeyboardUpForTheFieldItHoldsTakesItToItsLargeHeight() {
        show()
        assertEquals(windowHeight() * 0.4714f, top(), 2f)
        compose.onNodeWithTag(FIELD).performClick()
        keyboard(336)
        compose.waitForIdle()
        assertEquals(10f, top(), 1f)
    }

    @Test
    @Config(qualifiers = "w844dp-h390dp-land-xhdpi")
    fun onAPhoneOnItsSideItStandsAtItsLargeHeightAttached() {
        show()
        assertEquals(10f, top(), 1f)
        val panel = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot()
        assertEquals(0f, panel.left.value, 0.5f)
        assertEquals(compose.onRoot().getUnclippedBoundsInRoot().right.value, panel.right.value, 0.5f)
    }

    @Test
    fun aDragMovesItWithoutComposingWhatItHoldsAgain() {
        show()
        val before = compositions
        compose.onNodeWithTag(SHEET_PANEL).performTouchInput { swipeUp(top + 30f, top - 500f, durationMillis = 300) }
        compose.waitForIdle()
        assertTrue("At ${top()}", top() < 50f)
        assertTrue("Composed ${compositions - before} times", compositions - before <= 1)
    }

    @Test
    fun openedAgainOnItsWayOutAfterTheBackGestureItComesBackToItsHeight() {
        show()
        val medium = top()
        val back = compose.activity.onBackPressedDispatcher
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread {
            back.dispatchOnBackStarted(BackEventCompat(0f, 400f, 0f, BackEventCompat.EDGE_LEFT))
            back.dispatchOnBackProgressed(BackEventCompat(240f, 400f, 1f, BackEventCompat.EDGE_LEFT))
            back.onBackPressed()
        }
        compose.mainClock.advanceTimeBy(100)
        assertFalse(visible)
        visible = true
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertEquals(medium, top(), 1f)
    }
}

private const val FIELD = "sheet.test.field"
