package com.plhery.peekdeliverytracker.ui

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.SharedContent
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlinx.coroutines.awaitCancellation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The share card at the largest text sizes, as ShareViewController lays it out: its words take the lines they need,
 * Cancel grows with the body's size, Save grows from 52 around its title, and where the column doesn't fit the sheet it
 * scrolls, 16 clear of the sheet's top and of the navigation bar. On a synthetic number.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class ShareCardLayoutTest {
    @get:Rule
    val compose = createComposeRule()

    private val read = SharedContent.Read("Desk lamp", "Your parcel TESTLAMP0001 is on its way")
    private lateinit var view: View

    /** The card, ready, in `language` at `fontScale`, over a phone whose navigation bar is 24 tall. */
    private fun show(language: String = "en", fontScale: Float = 1f) {
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().background(Color.White)) {
                    PeekTheme(dark = false, carrierIdentities = null) {
                        ShareSheet(
                            ExtensionLocalizer(language), read = { awaitCancellation() }, save = { true }, onClosed = {},
                            card = ShareCard(SharePhase.Ready, read),
                        )
                    }
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, (24 * scale).toInt(), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, (NAVIGATION_BAR * scale).toInt()))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag, useUnmergedTree = true).getUnclippedBoundsInRoot()

    private fun layout(tag: String): TextLayoutResult {
        val layouts = mutableListOf<TextLayoutResult>()
        compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config[SemanticsActions.GetTextLayoutResult].action!!.invoke(layouts)
        return layouts.single()
    }

    /** Every word of it shown, none cut short. */
    private fun assertWhole(layout: TextLayoutResult) {
        val last = layout.lineCount - 1
        assertFalse(layout.isLineEllipsized(last))
        assertEquals(layout.layoutInput.text.length, layout.getLineEnd(last))
    }

    private fun points(layout: TextLayoutResult): Float = with(layout.layoutInput.density) { layout.layoutInput.style.fontSize.toDp().value }

    @Test
    @Config(qualifiers = "w320dp-h700dp-xhdpi")
    fun atTheLargestTextTheTitleTakesTheLinesItNeedsAndSaveGrowsAroundItsTitle() {
        show(language = "de", fontScale = 2f)
        val title = layout(SHARE_TITLE)
        assertTrue("One line: ${title.lineCount}", title.lineCount > 1)
        assertWhole(title)

        val save = bounds(SHARE_SAVE)
        val words = compose.onNode(hasText("Sendungsangaben speichern") and hasAnyAncestor(hasTestTag(SHARE_SAVE)), useUnmergedTree = true)
            .getBoundsInRoot()
        assertTrue("$save", save.height > 52.dp)
        assertTrue("$words in $save", words.top >= save.top + 6.5.dp && words.bottom <= save.bottom - 6.5.dp)

        // Cancel's 15 points grown as the body grows, on as many lines as it needs.
        val cancel = layout(SHARE_CLOSE)
        assertTrue("${points(cancel)}", points(cancel) > 20f)
        assertWhole(cancel)
    }

    @Test
    @Config(qualifiers = "w320dp-h700dp-xhdpi")
    fun atTheDefaultSizeSaveIs52TallAndCancel15Points() {
        show()
        assertEquals(52f, bounds(SHARE_SAVE).height.value, 0.5f)
        assertEquals(15f, points(layout(SHARE_CLOSE)), 0.01f)
        assertEquals(1, layout(SHARE_TITLE).lineCount)
    }

    @Test
    @Config(qualifiers = "w740dp-h360dp-xhdpi")
    fun whereTheColumnDoesNotFitItScrollsClearOfTheTopAndOfTheNavigationBar() {
        show(fontScale = 2f)
        val card = bounds(SHARE_CARD)
        // Past the margin, the mark, 76, and 18 under it.
        assertEquals((card.top + CLEAR.dp + 76.dp + 18.dp).value, bounds(SHARE_TITLE).top.value, 0.5f)
        assertTrue("Off the sheet: ${bounds(SHARE_CLOSE)} in $card", bounds(SHARE_CLOSE).bottom > card.bottom)
        compose.onNode(hasScrollAction() and hasAnyAncestor(hasTestTag(SHARE_CARD)), useUnmergedTree = true)
            .performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, 100_000f) }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertEquals((card.bottom - CLEAR.dp).value, bounds(SHARE_CLOSE).bottom.value, 0.5f)
    }

    @Test
    @Config(qualifiers = "w320dp-h700dp-xhdpi")
    fun whereItFitsTheColumnStandsInTheSheetsMiddle() {
        show()
        val card = bounds(SHARE_CARD)
        val title = bounds(SHARE_TITLE)
        val cancel = bounds(SHARE_CLOSE)
        // From the mark's top, 94 over the title, to Cancel's foot.
        val top = title.top - 94.dp
        assertEquals((top - card.top).value, (card.bottom - cancel.bottom).value, 0.5f)
    }

    private companion object {
        const val NAVIGATION_BAR = 24

        /** The margin at either end once it scrolls: the navigation bar's height and 16. */
        const val CLEAR = NAVIGATION_BAR + 16
    }
}
