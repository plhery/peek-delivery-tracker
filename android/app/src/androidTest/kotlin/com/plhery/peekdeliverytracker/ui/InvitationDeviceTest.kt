package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.FriendInvitationStore
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_ACCEPT
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_ACCEPTANCE
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_SHEET
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_STAMP
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_WELCOME
import com.plhery.peekdeliverytracker.ui.arrival.WELCOME_OPEN_PARCEL
import com.plhery.peekdeliverytracker.ui.arrival.signInSession
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.INVITATION_BASE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_NICKNAME
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SAVE
import com.plhery.peekdeliverytracker.ui.friends.FakeFriendsClient
import com.plhery.peekdeliverytracker.ui.friends.FriendFixtures
import com.plhery.peekdeliverytracker.ui.friends.friendCardTag
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.appearanceSetting
import java.util.UUID
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith

/**
 * A friend's invitation received (C-S02, C-S12, C-S13, C-S18) on this device's own window, its bars and density the
 * device's, on fake deliveries and made-up friends and codes, which reach no server: a link to the window standing the
 * welcome over the tabs, the parcel opened into the acceptance, accepted with its receipt's stamp, then the friends
 * with the new friend; signed out, the parcel opening into the sign-in and the system's back shutting it, then closing
 * the invitation; without a profile, the sheet joining and accepting. Each saved as invite/<test>.png in the test
 * package's external files, which adb pulls. Under Reduce Motion, as the other device tests are. The invitation waits
 * in the fakes' memory; each test still puts back the appearance it found and whatever the app's own preferences kept
 * of a pending invitation.
 */
@RunWith(AndroidJUnit4::class)
class InvitationDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val name = TestName()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(name).around(MadePreferences()).around(ZurichTime()).around(compose)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val shots = DeviceShots(compose, "invite")
    private val store = AppearanceStore.of(instrumentation.targetContext)
    private val appearanceBefore = store.appearance.value
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** The app's own preferences, and the invitation they had waiting before the test, if any. */
    private val preferences = PreferencesDefaults.standard(instrumentation.targetContext)
    private val pendingBefore = preferences.data(FriendInvitationStore.STORAGE_KEY)

    @After
    fun putBack() {
        store.set(appearanceBefore)
        val pending = pendingBefore
        if (pending != null) {
            preferences.set(pending, forKey = FriendInvitationStore.STORAGE_KEY)
        } else {
            preferences.removeObject(forKey = FriendInvitationStore.STORAGE_KEY)
        }
    }

    private lateinit var source: FakeDeliveries
    private lateinit var inbox: DeliveriesInbox

    /**
     * The tabs of [session] edge to edge as the app's window, the friends as [friends] sets them, the window's links
     * through an inbox that knows the fakes' invitations; with [signIn] the real session store on a fake auth service.
     */
    private fun show(session: AuthenticationState = reader, signIn: Boolean = false, friends: FakeFriendsClient.() -> Unit = { accepting() }) {
        store.set(AppAppearance.Light)
        source = FakeDeliveries(DemoCards.parcels(), session)
        source.friendsClient.friends()
        inbox = DeliveriesInbox(invitationLink = source.invitation::open)
        compose.waitsForMaps()
        compose.setContent {
            val dark = appearanceSetting().isDark(isSystemInDarkTheme())
            DisposableEffect(dark) {
                compose.activity.edgeToEdge(dark)
                onDispose {}
            }
            val scope = rememberCoroutineScope()
            val sessionStore = if (signIn) remember { signInSession(scope).also { it.showWelcome() } } else null
            RootHost(state = session, dark = dark, reduceMotion = true, source = source, inbox = inbox, session = sessionStore)
        }
        compose.waitUntil(5_000) { exists(TAB_BAR) }
        compose.waitForIdle()
    }

    /** The service answering: the invitation can be accepted, and accepting it makes Sasha a friend. */
    private fun FakeFriendsClient.accepting() {
        answer = { request ->
            when (request.action) {
                FriendsAction.AcceptInvite -> {
                    snapshot = FriendFixtures.circle(listOf(sasha, FriendFixtures.ada), snapshot.profile)
                    FriendsActionResponse(snapshot = snapshot, acceptedFriend = sasha)
                }
                FriendsAction.SaveProfile -> {
                    snapshot = snapshot.copy(profile = FriendFixtures.profile.copy(nickname = request.nickname ?: ""))
                    FriendsActionResponse(snapshot = snapshot)
                }
                else -> FriendsActionResponse(snapshot = snapshot)
            }
        }
    }

    /** The invitation's link to the window, as the trampoline or a browser hands one over; then its welcome. */
    private fun follow() {
        instrumentation.runOnMainSync { inbox.follow(Intent(Intent.ACTION_VIEW, Uri.parse(LINK))) }
        compose.waitUntil(5_000) { exists(WELCOME_OPEN_PARCEL) }
        compose.waitForIdle()
    }

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** A finger on what has [tag] where it stands, then what it brings. */
    private fun press(tag: String, until: String) {
        compose.onNodeWithTag(tag).performClick()
        compose.waitUntil(5_000) { exists(until) }
        compose.waitForIdle()
    }

    private fun back() {
        instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
        compose.waitForIdle()
    }

    /** Accepted: the receipt's stamp landed, taken before the arrival goes on to the friends; then the friends. */
    private fun acceptedThenTheFriends(button: String) {
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(button).performScrollTo().performClick()
        compose.mainClock.advanceTimeBy(250)
        compose.waitForIdle()
        assertTrue(exists(INVITATION_STAMP))
        shots.take("${name.methodName}-receipt")
        compose.mainClock.autoAdvance = true
        compose.waitUntil(5_000) { exists(PeekTab.Friends.screen) && exists(friendCardTag(sasha.id)) }
        compose.waitForIdle()
        assertFalse(source.invitation.isPresenting)
    }

    @Test
    fun aLinkToTheWindowTheWelcomeTheAcceptanceAndFinish() {
        show()
        follow()
        assertFalse(exists(TAB_BAR))
        shots.take("${name.methodName}-welcome")
        press(WELCOME_OPEN_PARCEL, until = INVITATION_ACCEPT)
        shots.take("${name.methodName}-acceptance")
        acceptedThenTheFriends(INVITATION_ACCEPT)
        shots.take(name.methodName)
    }

    @Test
    fun signedOutTheParcelOpensIntoTheSignInAndBackShutsIt() {
        show(AuthenticationState.Welcome, signIn = true)
        follow()
        shots.take("${name.methodName}-welcome")
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performClick()
        compose.waitUntil(5_000) { source.invitation.opened && !exists(INVITATION_WELCOME) }
        compose.waitForIdle()
        shots.take("${name.methodName}-signIn")
        back()
        compose.waitUntil(5_000) { exists(WELCOME_OPEN_PARCEL) }
        assertFalse(source.invitation.opened)
        back()
        compose.waitUntil(5_000) { !source.invitation.isPresenting && exists(TAB_BAR) }
        compose.waitForIdle()
        assertFalse(compose.activity.isFinishing)
        shots.take(name.methodName)
    }

    @Test
    fun withoutAProfileTheSheetJoinsAndAccepts() {
        show { snapshot = FriendFixtures.circle(profile = null); accepting() }
        follow()
        press(WELCOME_OPEN_PARCEL, until = INVITATION_ACCEPTANCE)
        compose.onNodeWithTag(INVITATION_ACCEPT).performScrollTo()
        press(INVITATION_ACCEPT, until = INVITATION_SHEET)
        compose.onNodeWithTag(FRIENDS_NICKNAME).performTextReplacement("Robin")
        compose.waitForIdle()
        shots.take("${name.methodName}-sheet")
        acceptedThenTheFriends(FRIENDS_SAVE)
        shots.take(name.methodName)
    }

    private companion object {
        /** A made-up invitation code, in the 16-character form the service gives. */
        const val LINK = "$INVITATION_BASE/invite#Qa3vT8nL5wZ1pR7k"

        val sasha = FriendCard(UUID.fromString("14000000-0000-4000-8000-000000000014"), "Sasha", FriendStats(5, 3, emptyList()), arrivedThisWeek = false)
    }
}
