package com.plhery.peekdeliverytracker.ui.passport

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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
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
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.screenshot
import java.util.UUID
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The passport on the iPhone the captures were taken on (440 × 956 points at three pixels to the point), with its safe
 * area as a phone's bars: the demo's parcels in light and dark (checks-m4/04-passport), an account with none, all the
 * stamps, the cover's and a stamp's popovers, the xxLarge and largest text sizes; the iPad's spread on a tablet both
 * ways (ipad-01, ipad-07), an unfolded foldable, and the foldable standing half open as a book.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class PassportScreenshotTest {
    private val compose = createComposeRule()
    private val fold = WindowLayoutInfoPublisherRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(fold).around(compose)

    private lateinit var view: View
    private var activity: Activity? = null
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** The window's bars in points: the status bar at the top, the gesture bar at the bottom. */
    private class Bars(val top: Int = 62, val bottom: Int = 24)

    private fun show(
        parcels: List<Parcel> = DemoCards.parcels(),
        session: AuthenticationState = AuthenticationState.Demo,
        dark: Boolean = false,
        fontScale: Float = 1f,
        bars: Bars = Bars(),
    ) {
        val source = FakeDeliveries(parcels, session)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            activity = LocalActivity.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = session, dark = dark, source = source)
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
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.onNode(hasContentDescription("Passport") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        settle()
    }

    /** The demo's parcels, as the iPhone's capture shows them (checks-m4/04-passport). */
    @Test
    fun theDemo() {
        show()
        compose.screenshot("passport_phone_light")
    }

    @Test
    fun theDemoInDark() {
        show(dark = true)
        compose.screenshot("passport_phone_dark")
    }

    /** An account with no parcels: none delivered, the first stamp next, waiting for times, no countries. */
    @Test
    fun noParcels() {
        show(emptyList(), reader)
        compose.screenshot("passport_empty_light")
    }

    @Test
    fun noParcelsInDark() {
        show(emptyList(), reader, dark = true)
        compose.screenshot("passport_empty_dark")
    }

    /** All twelve stamps, after "All stamps": three parcels delivered from Switzerland, three stamps earned. */
    @Test
    fun allStamps() {
        show(PassportFixtures.delivered(3), reader)
        tap(PASSPORT_SHOW_ALL)
        compose.screenshot("passport_all_stamps")
    }

    /** The cover's popover (ipad-02): what counts as delivered. */
    @Test
    fun theCoversPopover() {
        show()
        tap(PASSPORT_COVER)
        compose.screenshot("passport_popover_cover")
    }

    /** An unearned stamp's popover, with how far along it is (ipad-04). */
    @Test
    fun aStampsPopover() {
        show()
        tap("$PASSPORT_STAMP.carriers")
        compose.screenshot("passport_popover_stamp")
    }

    /** At xxLarge, three stamps to a row. */
    @Test
    fun extraExtraLargeText() {
        show(fontScale = 1.3f)
        compose.screenshot("passport_text_xxlarge")
    }

    /** At the largest text (ipad-09): two stamps to a row, no seal on the cover, the times one over the other. */
    @Test
    fun theLargestText() {
        show(fontScale = 2f)
        compose.screenshot("passport_text_largest")
    }

    /** The spread on a tablet held upright (ipad-01). */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aTablet() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("passport_tablet_portrait")
    }

    /** The spread on a tablet held sideways (ipad-07). */
    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun aTabletSideways() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("passport_tablet_landscape")
    }

    /** An unfolded foldable, flat: one page, as its window is too narrow for the spread. */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun anUnfoldedFoldable() {
        show(bars = Bars(top = 32, bottom = 24))
        compose.screenshot("passport_foldable")
    }

    /** The foldable standing half open as a book: the cover and the stamps before the fold, the rest after it. */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun aFoldableStandingAsABook() {
        show(bars = Bars(top = 32, bottom = 24))
        val hinge = FoldingFeature(requireNotNull(activity), center = view.width / 2, size = 0, state = State.HALF_OPENED, orientation = Orientation.VERTICAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        settle()
        compose.screenshot("passport_book")
    }
}
