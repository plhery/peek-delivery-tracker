package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Activity
import android.view.View
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
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
 * The first open on the iPhone the captures were taken on (440 × 956 points at three pixels to the point), a guest
 * with nothing followed, at the captures' moment, with the iPhone's safe area as a phone's bars: the status bar 62
 * tall and the gesture bar at the foot. Under Reduce Motion, so it shows the journey's still step, unless a shot
 * follows the journey. Then a phone on its side, which scrolls, the poster on a tablet and an unfolded foldable, and
 * the spread of a foldable standing half open.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class FirstOpenScreenshotTest {
    private val compose = createComposeRule()
    private val fold = WindowLayoutInfoPublisherRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(fold).around(compose)

    private lateinit var view: View
    private lateinit var activity: Activity

    /** The window's bars in points: the status bar at the top, the gesture bar at the bottom, a cutout at the start. */
    private class Bars(val top: Int = 62, val bottom: Int = 24, val cutout: Int = 0)

    /** The first open, the build tracking without an account unless not [tracks]. */
    private fun show(
        language: AppLanguage = AppLanguage.En,
        dark: Boolean = false,
        fontScale: Float = 1f,
        reduceMotion: Boolean = true,
        tracks: Boolean = true,
        bars: Bars = Bars(),
    ) {
        val source = FakeDeliveries(emptyList(), AuthenticationState.Welcome).apply { tracksWithoutAccount = tracks }
        compose.waitsForMaps()
        // The journey and the carriers move on frame by frame, which the clock gives only when asked.
        if (!reduceMotion) compose.mainClock.autoAdvance = false
        compose.setContent {
            view = LocalView.current
            activity = requireNotNull(LocalActivity.current)
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(language = language, dark = dark, reduceMotion = reduceMotion, source = source)
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

    /** The clock moved on `milliseconds` a frame at a time. */
    private fun advance(milliseconds: Long) {
        repeat(((milliseconds + FRAME - 1) / FRAME).toInt()) {
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
        }
    }

    /** A device standing half open, folded across the middle of the window, upright or level. */
    private fun halfOpen(orientation: Orientation) {
        val center = if (orientation == Orientation.VERTICAL) view.width / 2 else view.height / 2
        val hinge = FoldingFeature(activity, center = center, size = 0, state = State.HALF_OPENED, orientation = orientation)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        compose.waitForIdle()
    }

    @Test
    fun aPhone() {
        show()
        compose.screenshot("first_open_phone_light")
    }

    @Test
    fun aPhoneInDark() {
        show(dark = true)
        compose.screenshot("first_open_phone_dark")
    }

    /** As the iPhone's captures in Russian. */
    @Test
    fun aPhoneInRussian() {
        show(language = AppLanguage.Ru)
        compose.screenshot("first_open_phone_ru")
    }

    /** At the largest text, the first open grows no further than accessibility 1. */
    @Test
    fun aPhoneAtTheLargestText() {
        show(fontScale = 2f)
        compose.screenshot("first_open_phone_largest_text")
    }

    /** A build without a server offers its demo in place of the field. */
    @Test
    fun aBuildWithoutAServer() {
        show(tracks = false)
        compose.screenshot("first_open_phone_demo")
    }

    /** The journey from its first scan: picked up, three days away. */
    @Test
    fun theJourneysFirstScan() {
        show(reduceMotion = false)
        advance(2_000)
        compose.screenshot("first_open_step_1")
    }

    @Test
    fun theJourneysSecondScan() {
        show(reduceMotion = false)
        advance(2_700 + 2_000)
        compose.screenshot("first_open_step_2")
    }

    /** Out for delivery, its alert at the top. */
    @Test
    fun theJourneysFourthScan() {
        show(reduceMotion = false)
        advance(3 * 2_700 + 2_000)
        compose.screenshot("first_open_step_4")
    }

    /** Delivered, its alert at the top and the stamp postmarked. */
    @Test
    fun theJourneysLastScan() {
        show(reduceMotion = false)
        advance(3 * 2_700 + 3_400 + 3_000)
        compose.screenshot("first_open_step_5")
    }

    /** A phone on its side: too short for the picture and the question, it scrolls. */
    @Test
    @Config(qualifiers = "w956dp-h440dp-land-xxhdpi")
    fun aPhoneOnItsSide() {
        show(bars = Bars(top = 0, bottom = 21, cutout = 62))
        compose.screenshot("first_open_landscape")
    }

    /** A small phone at the largest text, no larger than accessibility 1's: too short for the page, it scrolls. */
    @Test
    @Config(qualifiers = "w360dp-h640dp-xxhdpi")
    fun aShortPhoneAtTheLargestText() {
        show(fontScale = 2f, bars = Bars(top = 24, bottom = 24))
        compose.screenshot("first_open_short_phone")
    }

    /** An iPad's 1032 × 1376 standing upright: the poster. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aTabletUpright() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("first_open_tablet_portrait")
    }

    /** An iPad's 1376 × 1032 on its side: the poster, its field and button on one line. */
    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun aTabletOnItsSide() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("first_open_tablet_landscape")
    }

    /** An unfolded foldable's inner screen lying flat: the poster. */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun anUnfoldedFoldable() {
        show(bars = Bars(top = 32, bottom = 24))
        compose.screenshot("first_open_foldable")
    }

    /** The foldable standing half open as a book: the picture before the fold, the question after it. */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun aFoldableStandingAsABook() {
        show(bars = Bars(top = 32, bottom = 24))
        halfOpen(Orientation.VERTICAL)
        compose.screenshot("first_open_spread_book")
    }

    /** The foldable standing half open on a table: the picture above the fold, the question below it. */
    @Test
    @Config(qualifiers = "w841dp-h673dp-land-xxhdpi")
    fun aFoldableStandingOnATable() {
        show(bars = Bars(top = 32, bottom = 24))
        halfOpen(Orientation.HORIZONTAL)
        compose.screenshot("first_open_spread_table")
    }

    private companion object {
        const val FRAME = 16L
    }
}
