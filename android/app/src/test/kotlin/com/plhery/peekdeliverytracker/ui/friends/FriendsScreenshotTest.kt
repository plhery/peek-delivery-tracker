package com.plhery.peekdeliverytracker.ui.friends

import android.view.View
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
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
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
 * The friends tab on the iPhone the captures were taken on (440 × 956 points at three pixels to the point), with its
 * safe area as a phone's bars, in light and dark: signed out, the demo's circle, its profile and invitation panels, an
 * account's invitation with its link, an empty circle, the intro without a profile and a circle that failed; the
 * xxLarge and largest text sizes; on a tablet both ways (ipad-01, ipad-09) with a panel as a form (ipad-07), and an
 * unfolded foldable.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class FriendsScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** The window's bars in points: the status bar at the top, the gesture bar at the bottom. */
    private class Bars(val top: Int = 62, val bottom: Int = 24)

    private fun show(
        session: AuthenticationState = AuthenticationState.Demo,
        dark: Boolean = false,
        fontScale: Float = 1f,
        bars: Bars = Bars(),
        friends: FakeFriendsClient.() -> Unit = {},
    ) {
        val source = FakeDeliveries(DemoCards.parcels(), session)
        source.friendsClient.friends()
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
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
        compose.onNode(hasContentDescription("Friends") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        // The sheet up and the parcel opened.
        repeat(2) { settle() }
    }

    /** A guest: what the tab is for, and Sign in. */
    @Test
    fun signedOut() {
        show(AuthenticationState.Welcome)
        compose.screenshot("friends_signed_out_light")
    }

    @Test
    fun signedOutInDark() {
        show(AuthenticationState.Welcome, dark = true)
        compose.screenshot("friends_signed_out_dark")
    }

    /** The demo's circle: what Alex shares, Mila, Noé and Sam (ipad-01). */
    @Test
    fun theDemo() {
        show()
        compose.screenshot("friends_phone_light")
    }

    /** ipad-10. */
    @Test
    fun theDemoInDark() {
        show(dark = true)
        compose.screenshot("friends_phone_dark")
    }

    /** What the demo shares, its panel open (ipad-07). */
    @Test
    fun theProfilePanel() {
        show()
        tap(FRIENDS_SHARING)
        compose.screenshot("friends_profile_light")
    }

    @Test
    fun theProfilePanelInDark() {
        show(dark = true)
        tap(FRIENDS_SHARING)
        compose.screenshot("friends_profile_dark")
    }

    /** The demo's invitation: the parcel from Alex and Sign in instead (ipad-08), at the 590 detent. */
    @Test
    fun theDemosInvitation() {
        show()
        tap(FRIENDS_INVITE)
        compose.screenshot("friends_invite_demo_light")
    }

    @Test
    fun theDemosInvitationInDark() {
        show(dark = true)
        tap(FRIENDS_INVITE)
        compose.screenshot("friends_invite_demo_dark")
    }

    /** An account's invitation with its link made. */
    @Test
    fun anInvitation() {
        show(reader)
        tap(FRIENDS_INVITE)
        compose.screenshot("friends_invite_light")
    }

    @Test
    fun anInvitationInDark() {
        show(reader, dark = true)
        tap(FRIENDS_INVITE)
        compose.screenshot("friends_invite_dark")
    }

    /** A profile without a friend yet. */
    @Test
    fun anEmptyCircle() {
        show(reader) { snapshot = FriendFixtures.circle(emptyList()) }
        compose.screenshot("friends_empty_light")
    }

    @Test
    fun anEmptyCircleInDark() {
        show(reader, dark = true) { snapshot = FriendFixtures.circle(emptyList()) }
        compose.screenshot("friends_empty_dark")
    }

    /** An account without a profile: the intro, which creates one. */
    @Test
    fun theIntro() {
        show(reader) { snapshot = FriendFixtures.circle(emptyList(), profile = null) }
        compose.screenshot("friends_intro_light")
    }

    @Test
    fun theIntroInDark() {
        show(reader, dark = true) { snapshot = FriendFixtures.circle(emptyList(), profile = null) }
        compose.screenshot("friends_intro_dark")
    }

    /** A circle that failed to load: what went wrong, and Retry. */
    @Test
    fun aFailure() {
        show(reader) { failure = FriendFixtures.unavailable() }
        compose.screenshot("friends_error_light")
    }

    @Test
    fun aFailureInDark() {
        show(reader, dark = true) { failure = FriendFixtures.unavailable() }
        compose.screenshot("friends_error_dark")
    }

    /** At xxLarge, the header still on one line. */
    @Test
    fun extraExtraLargeText() {
        show(fontScale = 1.3f)
        compose.screenshot("friends_text_xxlarge")
    }

    /** At the largest text (ipad-12): the header stacked, Invite across the column. */
    @Test
    fun theLargestText() {
        show(dark = true, fontScale = 2f)
        compose.screenshot("friends_text_largest")
    }

    /** The circle on a tablet held upright (ipad-01): no title, the account button in the tab bar's row. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aTablet() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("friends_tablet_portrait")
    }

    /** The circle on a tablet held sideways (ipad-09). */
    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun aTabletSideways() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("friends_tablet_landscape")
    }

    /** The demo's invitation as a form on a tablet (ipad-08). */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aPanelAsAFormOnATablet() {
        show(bars = Bars(top = 24, bottom = 24))
        tap(FRIENDS_INVITE)
        compose.screenshot("friends_tablet_panel")
    }

    /** An unfolded foldable: the circle 680 wide at most in the middle. */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun anUnfoldedFoldable() {
        show(bars = Bars(top = 32, bottom = 24))
        compose.screenshot("friends_foldable")
    }
}
