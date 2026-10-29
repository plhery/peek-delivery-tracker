package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelSort
import com.plhery.peekdeliverytracker.core.ParcelStatusFilter
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The deliveries' search and filters on the iPhone the captures were taken on, as the captures show them: the search
 * open, a query typed and its chip, a query nothing matches, the chips of a carrier and an order, and the filters'
 * sheet at its medium and large heights, light and dark, its carriers' menu, and at large text. Then on an iPad side
 * by side, the filters in a popover.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelSearchScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View

    /** The demo's list as the captures show it, with the iPhone's bars, or an iPad's where `top` is 24. */
    private fun show(dark: Boolean = false, fontScale: Float = 1f, top: Int = 62, bottom: Int = 24) {
        val source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = AuthenticationState.Demo, dark = dark, source = source)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            fun px(points: Int) = (points * scale).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(top), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, px(bottom)))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    private fun openSearch() {
        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        compose.waitForIdle()
    }

    private fun type(query: String) {
        compose.onNodeWithTag(SEARCH_FIELD).performTextInput(query)
        compose.waitForIdle()
    }

    private fun openFilters() {
        openSearch()
        compose.onNodeWithTag(FILTERS_BUTTON).performClick()
        compose.waitForIdle()
    }

    private fun choose(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        compose.waitForIdle()
    }

    @Test
    fun theSearchOpen() {
        show()
        openSearch()
        compose.screenshot("search_open_light")
    }

    @Test
    fun theSearchOpenInDark() {
        show(dark = true)
        openSearch()
        compose.screenshot("search_open_dark")
    }

    @Test
    fun aQueryTypedAndItsChip() {
        show()
        openSearch()
        type("gift")
        compose.screenshot("search_typed_light")
    }

    @Test
    fun aQueryNothingMatches() {
        show()
        openSearch()
        type("giftzz")
        compose.screenshot("search_none_light")
    }

    @Test
    fun theChipsOfACarrierAndAnOrder() {
        show()
        openFilters()
        choose(FILTERS_CARRIER)
        choose("$FILTERS_CARRIER.dhl")
        choose("$FILTERS_SORT.${ParcelSort.Carrier.rawValue}")
        compose.onNodeWithTag(FILTERS_DONE).performClick()
        compose.waitForIdle()
        compose.screenshot("search_chips_light")
    }

    @Test
    fun theFiltersAtTheirMediumHeight() {
        show()
        openFilters()
        compose.screenshot("filters_medium_light")
    }

    @Test
    fun theFiltersAtTheirMediumHeightInDark() {
        show(dark = true)
        openFilters()
        compose.screenshot("filters_medium_dark")
    }

    @Test
    fun theFiltersAtTheirLargeHeight() {
        show()
        openFilters()
        expand()
        compose.screenshot("filters_large_light")
    }

    @Test
    fun theFiltersAtTheirLargeHeightInDark() {
        show(dark = true)
        openFilters()
        expand()
        compose.screenshot("filters_large_dark")
    }

    @Test
    fun theCarriersMenu() {
        show()
        openFilters()
        expand()
        // Scrolled as the capture is, the carrier's row halfway down.
        compose.onNode(hasScrollAction() and hasAnyAncestor(hasTestTag(FILTERS_SHEET))).performSemanticsAction(SemanticsActions.ScrollBy) {
            it(0f, 208.5f * view.resources.displayMetrics.density)
        }
        compose.waitForIdle()
        choose(FILTERS_CARRIER)
        compose.screenshot("filters_carrier_menu_light")
    }

    @Test
    fun theFiltersAtLargeText() {
        show(fontScale = 1.35f)
        openFilters()
        expand()
        choose("$FILTERS_STATUS.${ParcelStatusFilter.Active.rawValue}")
        compose.screenshot("filters_large_text2")
    }

    /** An iPad's 1032 × 1376 side by side: the filters in a popover off their button. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun theFiltersInAPopoverSideBySide() {
        show(top = 24, bottom = 24)
        openFilters()
        compose.screenshot("filters_popover_tablet_light")
    }

    /** The sheet dragged up to its large height by its bar. */
    private fun expand() {
        compose.onNodeWithTag(SHEET_PANEL).performTouchInput { swipeUp(top + 40f, top - 600f, durationMillis = 300) }
        compose.waitForIdle()
    }
}
