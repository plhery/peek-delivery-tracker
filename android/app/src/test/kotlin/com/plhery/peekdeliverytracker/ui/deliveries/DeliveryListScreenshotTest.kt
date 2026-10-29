package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Activity
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.window.layout.FoldingFeature.Orientation
import androidx.window.layout.FoldingFeature.State
import androidx.window.testing.layout.FoldingFeature
import androidx.window.testing.layout.TestWindowLayoutInfo
import androidx.window.testing.layout.WindowLayoutInfoPublisherRule
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The deliveries' list on the iPhone the captures were taken on (440 × 956 points at three pixels to the point), in
 * the demo at the captures' moment, with the iPhone's safe area as a phone's bars: the status bar 62 tall and the
 * gesture bar under the tabs. Then on a tablet, an unfolded foldable and a phone turned sideways.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class DeliveryListScreenshotTest {
    private val compose = createComposeRule()
    private val fold = WindowLayoutInfoPublisherRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(fold).around(compose)

    private lateinit var view: View
    private lateinit var activity: Activity

    /** The window's bars in points: the status bar at the top, the gesture bar at the bottom, a cutout at the start. */
    private class Bars(val top: Int = 62, val bottom: Int = 24, val cutout: Int = 0)

    /** The demo's list as the captures show it, or `parcels` in `session`. */
    private fun show(
        language: AppLanguage = AppLanguage.En,
        dark: Boolean = false,
        fontScale: Float = 1f,
        parcels: List<Parcel> = DemoCards.parcels(language),
        session: AuthenticationState = AuthenticationState.Demo,
        bars: Bars = Bars(),
    ) {
        val source = FakeDeliveries(parcels, session)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            activity = LocalContext.current as Activity
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = session, language = language, dark = dark, source = source)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            fun px(points: Int) = (points * scale).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(bars.top), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, px(bars.bottom)))
                .setInsets(WindowInsetsCompat.Type.displayCutout(), Insets.of(px(bars.cutout), 0, 0, 0))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    private val list: SemanticsNodeInteraction get() = compose.onNodeWithTag(PARCEL_LIST)

    /** Scrolls the list to its end. */
    private fun scrollToEnd() {
        list.performScrollToKey("foot")
        list.performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, 100_000f) }
        compose.waitForIdle()
    }

    /** Scrolls to the item keyed `key`, then until `node` begins `top` points down the window, as the capture has it. */
    private fun scrollTo(key: String, node: () -> SemanticsNodeInteraction, top: Float) {
        list.performScrollToKey(key)
        compose.waitForIdle()
        val now = node().getBoundsInRoot().top.value
        val scale = view.resources.displayMetrics.density
        list.performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, (now - top) * scale) }
        compose.waitForIdle()
    }

    private fun scrollToThePast(top: Float) = scrollTo("section-Delivered", { compose.onNodeWithText("Past deliveries") }, top)

    private fun openTheArchive() {
        scrollToEnd()
        compose.onNodeWithContentDescription("Archived", substring = true).performClick()
        compose.waitForIdle()
    }

    @Test
    fun theTopOfTheList() {
        show()
        compose.screenshot("list_top_light")
    }

    @Test
    fun theTopOfTheListInDark() {
        show(dark = true)
        compose.screenshot("list_top_dark")
    }

    @Test
    fun theTopOfTheListInGerman() {
        show(AppLanguage.De)
        compose.screenshot("list_top_de")
    }

    @Test
    fun theTopOfTheListInTwiceTheText() {
        show(fontScale = 2f)
        compose.screenshot("list_top_text2")
    }

    @Test
    fun scrolledToThePastDeliveries() {
        show()
        scrollToThePast(PAST_HEADER_TOP)
        compose.screenshot("list_past_light")
    }

    @Test
    fun scrolledToThePastDeliveriesInDark() {
        show(dark = true)
        scrollToThePast(PAST_HEADER_TOP_DARK)
        compose.screenshot("list_past_dark")
    }

    @Test
    fun theBottomWithTheArchiveClosed() {
        show()
        scrollToEnd()
        compose.screenshot("list_bottom_light")
    }

    @Test
    fun theBottomWithTheArchiveClosedInDark() {
        show(dark = true)
        scrollToEnd()
        compose.screenshot("list_bottom_dark")
    }

    @Test
    fun theArchiveJustOpened() {
        show()
        // Opened at the end of the list, which stays where it was as the archive comes under the shelf.
        openTheArchive()
        compose.screenshot("list_archive_open_light")
    }

    @Test
    fun theBottomWithTheArchiveOpen() {
        show()
        openTheArchive()
        scrollToEnd()
        compose.screenshot("list_archive_bottom_light")
    }

    @Test
    fun theSearchShown() {
        show()
        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        compose.waitForIdle()
        compose.screenshot("list_search_light")
    }

    @Test
    fun nothingFollowedYet() {
        show(parcels = emptyList(), session = AuthenticationState.Welcome)
        compose.screenshot("list_empty_light")
    }

    @Test
    fun everyParcelArrived() {
        show(parcels = DemoCards.parcels().filter { it.isDelivered })
        compose.screenshot("list_all_arrived_light")
    }

    /** An iPad's 1032 × 1376: the list beside the first card's parcel, its card ringed. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aTabletShowsTheFirstParcelBesideTheList() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("list_tablet_light")
    }

    /** An unfolded foldable's inner screen in book posture: the list in one half, the parcel in the other. */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun anUnfoldedFoldableInBookPostureShowsAHalfEach() {
        show(bars = Bars(top = 32, bottom = 24))
        val hinge = FoldingFeature(activity, center = view.width / 2, size = 0, state = State.HALF_OPENED, orientation = Orientation.VERTICAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        compose.waitForIdle()
        compose.screenshot("list_foldable_light")
    }

    /** A phone turned sideways, its camera's cutout at the start. */
    @Test
    @Config(qualifiers = "w956dp-h440dp-land-xxhdpi")
    fun aPhoneTurnedSideways() {
        show(bars = Bars(top = 0, bottom = 21, cutout = 62))
        compose.screenshot("list_landscape_light")
    }
}

/** Where the captures show the past deliveries' heading, from the window's top: the dark one is scrolled less far. */
private const val PAST_HEADER_TOP = 579.5f
private const val PAST_HEADER_TOP_DARK = 657.2f
