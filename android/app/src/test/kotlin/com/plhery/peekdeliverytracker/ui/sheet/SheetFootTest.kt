package com.plhery.peekdeliverytracker.ui.sheet

import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * A sheet from the window's bottom over a navigation bar of three buttons, 48 tall: what it holds, its button at the
 * foot, stands above the bar, whether it keeps clear of it itself ([sheetBottomInset], its ground going on under the
 * bar) or not (the sheet holds it above the bar), at its half height, fitted to what it holds or at its large height;
 * and none of it is lifted twice.
 */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = PHONE)
class SheetFootTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var view: View

    private fun show(detents: List<SheetDetent>, content: @Composable () -> Unit) {
        compose.setContent {
            view = LocalView.current
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = null) {
                Box(Modifier.fillMaxSize()) {
                    Sheet(visible = true, onDismiss = {}, paneTitle = "Sheet", detents = detents) { content() }
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, (24 * scale).toInt(), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, (BAR * scale).toInt()))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** Where its foot is drawn, the floating sheet drawing what it holds smaller. */
    private fun bottom(tag: String): Float = compose.onNodeWithTag(tag).getBoundsInRoot().bottom.value

    private fun window(): Float = compose.onRoot().getUnclippedBoundsInRoot().bottom.value

    private fun assertTheButtonAboveTheBar() {
        val button = bottom(BUTTON)
        assertTrue("The button's foot at $button, the bar's top at ${window() - BAR}", button <= window() - BAR + 0.5f)
    }

    /** Its ground, as tall as the sheet, the button at its foot, `lift` over it. */
    @Composable
    private fun Ground(lift: Boolean) {
        Box(Modifier.fillMaxSize().testTag(GROUND)) {
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = if (lift) sheetBottomInset() else 0.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag(BUTTON),
            )
        }
    }

    @Test
    fun whatLeavesTheBarToTheSheetStandsAboveIt() {
        show(listOf(SheetDetent.Medium, SheetDetent.Large)) { Ground(lift = false) }
        assertTheButtonAboveTheBar()
        // Its ground ends where it does, the sheet's own under it.
        assertEquals(bottom(BUTTON), bottom(GROUND), 0.5f)
    }

    @Test
    fun whatKeepsClearOfTheBarItselfGoesOnUnderItAndIsNotLiftedTwice() {
        show(listOf(SheetDetent.Medium, SheetDetent.Large)) { Ground(lift = true) }
        assertTheButtonAboveTheBar()
        assertEquals(bottom(SHEET_PANEL), bottom(GROUND), 0.5f)
        // Lifted once: the bar's height over the ground's foot, as the floating sheet draws it smaller.
        val lift = bottom(GROUND) - bottom(BUTTON)
        assertTrue("Lifted $lift", lift in BAR * 0.9f..BAR + 0.5f)
    }

    @Test
    fun aSheetFittedToWhatItHoldsGrowsByTheBar() {
        show(listOf(SheetDetent.Fitted, SheetDetent.Large)) {
            Column {
                Spacer(Modifier.height(200.dp))
                Box(Modifier.fillMaxWidth().height(50.dp).testTag(BUTTON))
            }
        }
        assertTheButtonAboveTheBar()
    }

    @Test
    fun atItsLargeHeightToo() {
        show(listOf(SheetDetent.Large)) { Ground(lift = false) }
        assertTheButtonAboveTheBar()
        assertTrue("The button's foot at ${bottom(BUTTON)}", bottom(BUTTON) >= window() - BAR - 10f)
    }
}

private const val BAR = 48
private const val GROUND = "sheetFoot.test.ground"
private const val BUTTON = "sheetFoot.test.button"
