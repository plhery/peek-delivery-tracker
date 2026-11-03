package com.plhery.peekdeliverytracker.ui.sheet

import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * What scrolls with fields keeps the focused one above the keyboard, as a SwiftUI `ScrollView` does: a field at the
 * foot of a page that doesn't scroll yet comes up over the room the keyboard makes under the content, though that room
 * is only laid out after the keyboard's height is known.
 */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = PHONE)
class KeyboardScrollTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var view: View
    private var text by mutableStateOf("")

    /** The page: as tall as the window, its field at the foot. */
    private fun show(reduceMotion: Boolean) {
        compose.setContent {
            view = LocalView.current
            PeekTheme(dark = false, highContrast = false, reduceMotion = reduceMotion, reduceTransparency = false, carrierIdentities = null) {
                Box(Modifier.fillMaxSize()) {
                    KeyboardScrollColumn(Modifier.fillMaxSize()) {
                        Spacer(Modifier.height(804.dp))
                        BasicTextField(text, { text = it }, Modifier.fillMaxWidth().height(40.dp).testTag(FIELD))
                    }
                }
            }
        }
        compose.waitForIdle()
    }

    /** The keyboard `height` points tall, as the window's insets tell it. */
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

    private fun assertTheFieldAboveTheKeyboard(keyboard: Int) {
        val window = compose.onRoot().getUnclippedBoundsInRoot().bottom.value
        val field = compose.onNodeWithTag(FIELD).getUnclippedBoundsInRoot().bottom.value
        assertTrue("The field's foot at $field, the keyboard's top at ${window - keyboard}", field <= window - keyboard + 0.5f)
    }

    @Test
    fun theFieldAtTheFootComesUpOverTheRoomTheKeyboardMakes() {
        show(reduceMotion = false)
        compose.onNodeWithTag(FIELD).performClick()
        keyboard(300)
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(1_000)
        assertTheFieldAboveTheKeyboard(300)
    }

    @Test
    fun andWhenTheKeyboardSlidesUpFrameByFrame() {
        show(reduceMotion = false)
        compose.onNodeWithTag(FIELD).performClick()
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        // The keyboard's slide as a device plays it: its inset grows a little each frame, the page scrolling as it goes.
        for (step in 1..20) {
            keyboard(300 * step / 20)
            compose.mainClock.advanceTimeByFrame()
        }
        compose.mainClock.advanceTimeBy(1_000)
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertTheFieldAboveTheKeyboard(300)
    }

    @Test
    fun underReduceMotionToo() {
        show(reduceMotion = true)
        compose.onNodeWithTag(FIELD).performClick()
        keyboard(300)
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(1_000)
        assertTheFieldAboveTheKeyboard(300)
    }
}

private const val FIELD = "keyboardScroll.test.field"
