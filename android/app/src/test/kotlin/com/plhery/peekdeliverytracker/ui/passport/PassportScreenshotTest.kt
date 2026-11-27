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
 * area as a phone's bars: the demo's parcels on each page in light and dark, an account with none, all the stamps, the
 * cover's and a stamp's popovers, the xxLarge and largest text sizes, a narrow phone; the book lying open on a tablet
 * both ways, an unfolded foldable, and the foldable standing half open as a book.
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

    /** The page [index] ([PassportPage]) chosen from its label. */
    private fun page(index: Int) {
        compose.onNodeWithTag("$PASSPORT_PAGE_LABEL.$index").performClick()
        settle()
    }

    /** The demo's parcels: the cover over the Countries page, the Stamps page's edge beside it. */
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

    /** The demo's Stamps page (checks-m4/04-passport's stamps), the Countries page's edge before it. */
    @Test
    fun theDemosStamps() {
        show()
        page(PassportPage.STAMPS)
        compose.screenshot("passport_stamps_light")
    }

    @Test
    fun theDemosStampsInDark() {
        show(dark = true)
        page(PassportPage.STAMPS)
        compose.screenshot("passport_stamps_dark")
    }

    /** The demo's Numbers page: the counts and the times, the busiest month and the carriers. */
    @Test
    fun theDemosNumbers() {
        show()
        page(PassportPage.NUMBERS)
        compose.screenshot("passport_numbers_light")
    }

    @Test
    fun theDemosNumbersInDark() {
        show(dark = true)
        page(PassportPage.NUMBERS)
        compose.screenshot("passport_numbers_dark")
    }

    /** An account with no parcels: none delivered, the Countries page's ghost of a stamp. */
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

    /** All nineteen stamps, after "All stamps": three parcels delivered in Switzerland, four stamps earned. */
    @Test
    fun allStamps() {
        show(PassportFixtures.delivered(3), reader)
        page(PassportPage.STAMPS)
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

    /** An earned stamp's popover (ipad-04): how it is earned, then the day in full and the parcel that earned it. */
    @Test
    fun aStampsPopover() {
        show()
        page(PassportPage.STAMPS)
        tap("$PASSPORT_STAMP.connected")
        compose.screenshot("passport_popover_stamp")
    }

    /** At xxLarge, three stamps to a row. */
    @Test
    fun extraExtraLargeText() {
        show(fontScale = 1.3f)
        page(PassportPage.STAMPS)
        compose.screenshot("passport_text_xxlarge")
    }

    /** A narrow phone's window, 320 wide: two countries to a row, their names wrapping. */
    @Test
    @Config(qualifiers = "w320dp-h640dp-xhdpi")
    fun aNarrowPhone() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("passport_phone_narrow")
    }

    /** A narrow phone's Numbers page: the times shrinking rather than wrap. */
    @Test
    @Config(qualifiers = "w320dp-h640dp-xhdpi")
    fun aNarrowPhonesNumbers() {
        show(bars = Bars(top = 24, bottom = 24))
        page(PassportPage.NUMBERS)
        compose.screenshot("passport_numbers_narrow")
    }

    /** At the largest text (ipad-09): no seal on the cover, the labels and the countries' names wrapping. */
    @Test
    fun theLargestText() {
        show(fontScale = 2f)
        compose.screenshot("passport_text_largest")
    }

    /** The Numbers page at the largest text: one number to a row, each row's value under its name. */
    @Test
    fun theLargestTextsNumbers() {
        show(fontScale = 2f)
        page(PassportPage.NUMBERS)
        compose.screenshot("passport_numbers_largest")
    }

    /** The book lying open on a tablet held upright (ipad-01): the Countries and Stamps pages side by side. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aTablet() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("passport_tablet_portrait")
    }

    /** The book lying open on a tablet held sideways (ipad-07), turned to Numbers: the Stamps and Numbers pages. */
    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun aTabletSideways() {
        show(bars = Bars(top = 24, bottom = 24))
        page(PassportPage.NUMBERS)
        compose.screenshot("passport_tablet_landscape")
    }

    /** An unfolded foldable, flat: one page, as its window is too narrow for the book to lie open. */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun anUnfoldedFoldable() {
        show(bars = Bars(top = 32, bottom = 24))
        compose.screenshot("passport_foldable")
    }

    /** The foldable standing half open as a book: the cover before the fold, the pages after it. */
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
