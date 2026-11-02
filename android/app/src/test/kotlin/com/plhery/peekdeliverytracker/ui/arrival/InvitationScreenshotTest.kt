package com.plhery.peekdeliverytracker.ui.arrival

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
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
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponseInvitationState
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.INVITATION_BASE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.friends.FakeFriendsClient
import com.plhery.peekdeliverytracker.ui.friends.FriendFixtures
import com.plhery.peekdeliverytracker.ui.screenshot
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A friend's invitation received on the iPhone the captures were taken on (440 × 956 points at three pixels to the
 * point), with its safe area as a phone's bars, in light and dark: the welcome with its sender, while it is asked and
 * when the link leads nowhere; the sign-in with the invitation; the acceptance with and without a profile, each
 * outcome, the receipt's stamp landed and the profile's sheet; the largest text; a tablet both ways, its sheet as a
 * form; an unfolded foldable. Made-up names and codes.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class InvitationScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = READER_EMAIL))

    /** The window's bars in points: the status bar at the top, the gesture bar at the bottom. */
    private class Bars(val top: Int = 62, val bottom: Int = 24)

    private fun show(
        session: AuthenticationState = AuthenticationState.Welcome,
        dark: Boolean = false,
        fontScale: Float = 1f,
        bars: Bars = Bars(),
        signIn: Boolean = false,
        deliveries: FakeDeliveries.() -> Unit = {},
        friends: FakeFriendsClient.() -> Unit = { accepting() },
    ) {
        val source = FakeDeliveries(DemoCards.parcels(), session)
        source.deliveries()
        source.friendsClient.friends()
        source.invitation.open(LINK)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            // The sign-in's email form, on a fake auth service, where the debug build's would say it's unavailable.
            val scope = rememberCoroutineScope()
            val store = if (signIn) remember { signInSession(scope).also { it.showWelcome() } } else null
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = session, dark = dark, source = source, session = store)
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
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** A tap on what has [tag], then the page or sheet it brings settled. */
    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        repeat(2) { settle() }
    }

    /** The parcel opened: the sign-in, or the acceptance for someone signed in. */
    private fun open() {
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performClick()
        repeat(2) { settle() }
    }

    /** The service answering: the invitation can be accepted, and accepting it makes Sasha a friend. */
    private fun FakeFriendsClient.accepting() {
        answer = { request ->
            when (request.action) {
                FriendsAction.AcceptInvite -> FriendsActionResponse(snapshot = FriendFixtures.circle(listOf(sasha, FriendFixtures.ada)), acceptedFriend = sasha)
                else -> FriendsActionResponse(snapshot = snapshot)
            }
        }
    }

    /** The check answering [state]. */
    private fun FakeFriendsClient.answering(state: FriendsActionResponseInvitationState) {
        answer = { FriendsActionResponse(invitationState = state) }
    }

    /** Accepted, the receipt shown with its stamp landed, before the arrival goes on to the friends. */
    private fun accept() {
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(INVITATION_ACCEPT).performScrollTo().performClick()
        compose.mainClock.advanceTimeBy(250)
        compose.waitForIdle()
    }

    // The welcome (C-S02, C-S12).

    /** The welcome with an invitation from Sasha: the heading, the parcel to open, Tap to open. */
    @Test
    fun theWelcome() {
        show()
        compose.screenshot("invite_welcome_light")
    }

    @Test
    fun theWelcomeInDark() {
        show(dark = true)
        compose.screenshot("invite_welcome_dark")
    }

    /** While the sender is asked: the generic heading and the spinner. */
    @Test
    fun theWelcomeLoading() {
        show(deliveries = { previewing = CompletableDeferred() })
        compose.screenshot("invite_welcome_loading_light")
    }

    @Test
    fun theWelcomeLoadingInDark() {
        show(dark = true, deliveries = { previewing = CompletableDeferred() })
        compose.screenshot("invite_welcome_loading_dark")
    }

    /** A link already used or that can't be read: why, without Retry. */
    @Test
    fun theWelcomeUnavailable() {
        show(deliveries = { inviterFailure = DeliveryApiError.Service("Invitation unavailable") })
        compose.screenshot("invite_welcome_unavailable_light")
    }

    @Test
    fun theWelcomeUnavailableInDark() {
        show(dark = true, deliveries = { inviterFailure = DeliveryApiError.Service("Invitation unavailable") })
        compose.screenshot("invite_welcome_unavailable_dark")
    }

    /** The parcel opened, signed out: the sign-in with the invitation's heading. */
    @Test
    fun theSignIn() {
        show(signIn = true)
        open()
        compose.screenshot("invite_sign_in_light")
    }

    @Test
    fun theSignInInDark() {
        show(dark = true, signIn = true)
        open()
        compose.screenshot("invite_sign_in_dark")
    }

    // The acceptance (C-S13, C-S18).

    /** Signed in with a profile: Sasha's postcard, what the account shares, Accept. */
    @Test
    fun theAcceptance() {
        show(reader)
        open()
        compose.screenshot("invite_acceptance_light")
    }

    @Test
    fun theAcceptanceInDark() {
        show(reader, dark = true)
        open()
        compose.screenshot("invite_acceptance_dark")
    }

    /** Without a profile: Enable friends to accept. */
    @Test
    fun theAcceptanceWithoutAProfile() {
        show(reader, friends = { snapshot = FriendFixtures.circle(profile = null); accepting() })
        open()
        compose.screenshot("invite_acceptance_no_profile_light")
    }

    @Test
    fun theAcceptanceWithoutAProfileInDark() {
        show(reader, dark = true, friends = { snapshot = FriendFixtures.circle(profile = null); accepting() })
        open()
        compose.screenshot("invite_acceptance_no_profile_dark")
    }

    @Test
    fun alreadyAccepted() {
        show(reader, friends = { answering(FriendsActionResponseInvitationState.AlreadyAccepted) })
        open()
        compose.screenshot("invite_already_accepted_light")
    }

    @Test
    fun alreadyAcceptedInDark() {
        show(reader, dark = true, friends = { answering(FriendsActionResponseInvitationState.AlreadyAccepted) })
        open()
        compose.screenshot("invite_already_accepted_dark")
    }

    @Test
    fun alreadyFriends() {
        show(reader, friends = { answering(FriendsActionResponseInvitationState.AlreadyFriends) })
        open()
        compose.screenshot("invite_already_friends_light")
    }

    @Test
    fun alreadyFriendsInDark() {
        show(reader, dark = true, friends = { answering(FriendsActionResponseInvitationState.AlreadyFriends) })
        open()
        compose.screenshot("invite_already_friends_dark")
    }

    /** One's own invitation. */
    @Test
    fun onesOwnInvitation() {
        show(reader, friends = { actionFailure = DeliveryApiError.Service("Cannot accept your own invitation") })
        open()
        compose.screenshot("invite_self_light")
    }

    @Test
    fun onesOwnInvitationInDark() {
        show(reader, dark = true, friends = { actionFailure = DeliveryApiError.Service("Cannot accept your own invitation") })
        open()
        compose.screenshot("invite_self_dark")
    }

    /** Accepted: the parcel opened over Friendship delivered, the receipt's stamp landed on it. */
    @Test
    fun theReceipt() {
        show(reader)
        open()
        accept()
        compose.screenshot("invite_receipt_light")
    }

    @Test
    fun theReceiptInDark() {
        show(reader, dark = true)
        open()
        accept()
        compose.screenshot("invite_receipt_dark")
    }

    /** The profile's sheet, to join and accept, as a page sheet on a phone. */
    @Test
    fun theProfileSheet() {
        show(reader, friends = { snapshot = FriendFixtures.circle(profile = null); accepting() })
        open()
        tap(INVITATION_ACCEPT)
        compose.screenshot("invite_profile_sheet_light")
    }

    @Test
    fun theProfileSheetInDark() {
        show(reader, dark = true, friends = { snapshot = FriendFixtures.circle(profile = null); accepting() })
        open()
        tap(INVITATION_ACCEPT)
        compose.screenshot("invite_profile_sheet_dark")
    }

    // Sizes.

    /** At the largest text the welcome grows and scrolls; the parcel doesn't grow. */
    @Test
    fun theWelcomeAtTheLargestText() {
        show(fontScale = 2f)
        compose.screenshot("invite_welcome_text_largest")
    }

    @Test
    fun theAcceptanceAtTheLargestText() {
        show(reader, dark = true, fontScale = 2f)
        open()
        compose.screenshot("invite_acceptance_text_largest")
    }

    /** On a tablet held upright: the welcome's column 520 wide at most in the middle. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun theWelcomeOnATablet() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("invite_welcome_tablet_portrait")
    }

    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun theWelcomeOnATabletSideways() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("invite_welcome_tablet_landscape")
    }

    /** The profile's sheet as a form over the acceptance on a tablet. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun theProfileSheetOnATablet() {
        show(reader, bars = Bars(top = 24, bottom = 24), friends = { snapshot = FriendFixtures.circle(profile = null); accepting() })
        open()
        tap(INVITATION_ACCEPT)
        compose.screenshot("invite_profile_sheet_tablet_portrait")
    }

    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun theAcceptanceOnATabletSideways() {
        show(reader, bars = Bars(top = 24, bottom = 24))
        open()
        compose.screenshot("invite_acceptance_tablet_landscape")
    }

    /** An unfolded foldable: the welcome's column in the middle. */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun anUnfoldedFoldable() {
        show(bars = Bars(top = 32, bottom = 24))
        compose.screenshot("invite_foldable")
    }

    private companion object {
        /** A made-up invitation code, in the 16-character form the service gives. */
        const val LINK = "$INVITATION_BASE/invite#Qa3vT8nL5wZ1pR7k"

        val sasha = FriendCard(UUID.fromString("14000000-0000-4000-8000-000000000014"), "Sasha", FriendStats(5, 3, emptyList()), arrivedThisWeek = false)
    }
}
