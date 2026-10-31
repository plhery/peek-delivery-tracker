package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.deliveries.InlineToast
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Glass takes the touches it covers, as a SwiftUI view does where it draws: a tap on a toast's words or on a bar's
 * glass between its buttons doesn't reach the card under it, while what holds the glass still scrolls under a drag.
 */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = "w360dp-h640dp-xhdpi")
class GlassTouchTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun aTapOnTheGlassStaysOnTheGlassAndOneOutsideItsShapeGoesThrough() {
        var under = 0
        content {
            // The glass floats over a card, as a bar or a toast over the list.
            Box(Modifier.fillMaxSize()) {
                Box(Modifier.fillMaxSize().clickable { under++ })
                GlassSurface(CapsuleShape, Modifier.testTag(GLASS).offset(20.dp, 20.dp).size(200.dp, 60.dp)) {}
            }
        }
        compose.onNodeWithTag(GLASS).performTouchInput { click(center) }
        assertEquals(0, under)
        // The capsule's corner lies outside its shape: what is under it takes that tap, as on the iPhone.
        compose.onNodeWithTag(GLASS).performTouchInput { click(Offset(2f, 2f)) }
        assertEquals(1, under)
    }

    @Test
    fun aTapOnTheToastsWordsLeavesTheCardUnderItShutAndItsUndoStillUndoes() {
        var opened = 0
        var undone = 0
        content {
            Box(Modifier.fillMaxSize()) {
                Box(Modifier.fillMaxSize().testTag(CARD).clickable { opened++ })
                InlineToast(
                    "Coffee beans archived",
                    "Undo",
                    "archivebox.fill",
                    Color(0xFF9A7B2F),
                    { undone++ },
                    backdrop = null,
                    modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
                )
            }
        }
        compose.onNodeWithText("Coffee beans archived", useUnmergedTree = true).performClick()
        assertEquals(0, opened)
        assertEquals(0, undone)
        compose.onNodeWithText("Undo", useUnmergedTree = true).performClick()
        assertEquals(1, undone)
        assertEquals(0, opened)
    }

    @Test
    fun aDragOnGlassStillScrollsWhatHoldsIt() {
        val scroll = mutableIntStateOf(0)
        content {
            val state = rememberScrollState()
            scroll.intValue = state.value
            Column(Modifier.fillMaxSize().verticalScroll(state)) {
                GlassSurface(CapsuleShape, Modifier.testTag(GLASS).fillMaxWidth().height(300.dp)) {}
                Spacer(Modifier.height(1200.dp))
            }
        }
        compose.onNodeWithTag(GLASS).performTouchInput { swipeUp() }
        compose.waitForIdle()
        assertTrue("scrolled ${scroll.intValue}", scroll.intValue > 0)
    }

    private fun content(body: @Composable () -> Unit) = compose.setContent {
        PeekTheme(dark = false, highContrast = false, reduceMotion = true, reduceTransparency = false, carrierIdentities = null) { body() }
    }

    private companion object {
        const val GLASS = "glass"
        const val CARD = "card"
    }
}
