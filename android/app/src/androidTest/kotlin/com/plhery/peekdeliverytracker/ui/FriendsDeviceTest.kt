package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityService
import android.content.ClipData
import android.content.ClipboardManager
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendUpdate
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActivity
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_CANCEL_REMOVE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_CONFIRM_REMOVE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_COPY
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_DEMO_SIGN_IN
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_INVITATION_URL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_INVITE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_MANAGE_FRIENDSHIP
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_MANAGE_LINKS
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_NOTICE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_NOTICE_DISMISS
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_NOTICE_OPEN
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_PAGE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_PAGE_BACK
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_PANEL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_POPOVER
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_REMOVE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_REVOKE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SHARING
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SIGN_IN
import com.plhery.peekdeliverytracker.ui.friends.FakeFriendsClient
import com.plhery.peekdeliverytracker.ui.friends.FriendFixtures
import com.plhery.peekdeliverytracker.ui.friends.friendCardTag
import com.plhery.peekdeliverytracker.ui.friends.friendStampTag
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
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
 * The friends tab (A-S20, C-S19 to C-S31) on this device's own window, its bars and density the device's, on fake
 * deliveries and made-up friends, which reach no server: the demo's circle; signed out; a friend's card opening their
 * page and the system's back closing it, and the page's ‹; a stamp's popover; manage friendship and the removal; the
 * accepted notice opening the friend, and its ✕ tapped through the screen dismissing it (A-S04, A-G18); the profile's
 * panel; the demo's invitation; an account's invitation with its link copied and its manage links open; the account
 * button opening Settings. Each saved as friends/<test>.png in the test package's external files, which adb pulls.
 * Under Reduce Motion, as the other device tests are; each test puts back the appearance it found, and the copy the
 * clipboard held.
 */
@RunWith(AndroidJUnit4::class)
class FriendsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val name = TestName()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(name).around(MadePreferences()).around(ZurichTime()).around(compose)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val shots = DeviceShots(compose, "friends")
    private val store = AppearanceStore.of(instrumentation.targetContext)
    private val appearanceBefore = store.appearance.value
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** What the clipboard held before a test copied the link, which it gets back; null where nothing was copied. */
    private var clipBefore: ClipData? = null
    private var copied = false

    @After
    fun putBack() {
        store.set(appearanceBefore)
        if (copied) {
            instrumentation.runOnMainSync {
                val clipboard = compose.activity.getSystemService(ClipboardManager::class.java)
                val before = clipBefore
                if (before != null) clipboard.setPrimaryClip(before) else clipboard.clearPrimaryClip()
            }
        }
    }

    private lateinit var source: FakeDeliveries

    /**
     * The friends tab of [session], its friends as [friends] sets them, edge to edge as the app's window; the deliveries
     * unless [choose].
     */
    private fun show(session: AuthenticationState = AuthenticationState.Demo, choose: Boolean = true, friends: FakeFriendsClient.() -> Unit = {}) {
        store.set(AppAppearance.Light)
        source = FakeDeliveries(DemoCards.parcels(), session)
        source.friendsClient.friends()
        compose.waitsForMaps()
        compose.setContent {
            val dark = appearanceSetting().isDark(isSystemInDarkTheme())
            DisposableEffect(dark) {
                compose.activity.edgeToEdge(dark)
                onDispose {}
            }
            RootHost(state = session, dark = dark, reduceMotion = true, source = source)
        }
        compose.waitForIdle()
        if (!choose) return
        compose.onNode(hasContentDescription("Friends") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        compose.waitUntil(5_000) { exists(if (session == AuthenticationState.Welcome) FRIENDS_SIGN_IN else FRIENDS_SHARING) }
        compose.waitForIdle()
    }

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /**
     * Whether the card of [friend] is drawn: just inside its top edge the window shows its fill, not the page beside
     * it, which shows through the card while it waits to land at alpha 0.
     */
    private fun drawn(friend: UUID): Boolean {
        val card = compose.onNodeWithTag(friendCardTag(friend), useUnmergedTree = true).getUnclippedBoundsInRoot()
        val window = compose.onRoot().captureToImage().asAndroidBitmap()
        val scale = compose.density.density
        val y = ((card.top + 4.dp).value * scale).toInt()
        val inside = window.getPixel(((card.left + card.right).value / 2 * scale).toInt(), y)
        val beside = window.getPixel(((card.left - 6.dp).value * scale).toInt(), y)
        return inside != beside
    }

    /** Whether the part tagged [tag], or something in it, says [text]. */
    private fun says(tag: String, text: String) =
        compose.onAllNodes(hasText(text) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag)))).fetchSemanticsNodes().isNotEmpty()

    /** A finger on what has [tag], scrolled into the window first. */
    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        compose.waitForIdle()
    }

    /** A finger on what has [tag] where it stands, out of any scroll. */
    private fun press(tag: String) {
        compose.onNodeWithTag(tag).performClick()
        compose.waitForIdle()
    }

    private fun shot() {
        shots.take(name.methodName)
    }

    @Test
    fun theDemosCircle() {
        show()
        shot()
    }

    @Test
    fun signedOut() {
        show(AuthenticationState.Welcome)
        shot()
    }

    private val mila = UUID.fromString("11000000-0000-4000-8000-000000000001")
    private val noe = UUID.fromString("22000000-0000-4000-8000-000000000002")

    /** The demo friend's page, out of their card. */
    private fun page(friend: UUID) {
        press(friendCardTag(friend))
        compose.waitUntil(5_000) { exists(FRIENDS_PAGE_BACK) }
        compose.waitForIdle()
    }

    /** A card opens the friend's page, and the system's back closes it; then the page's ‹ does. */
    @Test
    fun aCardOpensThePageAndTheSystemBackClosesIt() {
        show()
        page(noe)
        shots.take("${name.methodName}-open")
        instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
        compose.waitUntil(5_000) { !exists(FRIENDS_PAGE) }
        compose.waitForIdle()
        assertFalse(compose.activity.isFinishing)
        assertTrue(exists(FRIENDS_SHARING))
        shot()
        page(noe)
        press(FRIENDS_PAGE_BACK)
        compose.waitUntil(5_000) { !exists(FRIENDS_PAGE) }
        assertTrue(exists(FRIENDS_SHARING))
    }

    /** Mila's page with The regular's popover over its stamp: how it is earned, and 12 / 25. */
    @Test
    fun aStampsPopover() {
        show()
        page(mila)
        tap(friendStampTag(FriendStamp.TheRegular))
        compose.waitUntil(5_000) { exists(FRIENDS_POPOVER) }
        compose.waitForIdle()
        shot()
    }

    /** Noé's manage friendship opened, the removal asked, and Noé removed: the page closed, the circle without them. */
    @Test
    fun manageFriendshipAndTheRemoval() {
        show()
        page(noe)
        tap(FRIENDS_MANAGE_FRIENDSHIP)
        compose.waitUntil(5_000) { exists(FRIENDS_REMOVE) }
        compose.waitForIdle()
        shots.take("${name.methodName}-manage")
        tap(FRIENDS_REMOVE)
        compose.waitUntil(5_000) { exists(FRIENDS_CONFIRM_REMOVE) }
        compose.onNodeWithTag(FRIENDS_CANCEL_REMOVE).performScrollTo()
        compose.waitForIdle()
        shots.take("${name.methodName}-confirm")
        tap(FRIENDS_CONFIRM_REMOVE)
        compose.waitUntil(5_000) { !exists(FRIENDS_PAGE) }
        compose.waitForIdle()
        assertFalse(exists(friendCardTag(noe)))
        shot()
    }

    /**
     * Someone accepted the account's invitation: the notice over the window, on the deliveries (where the tab bar
     * stands at the top, the notice covers its middle, as on the iPad), which opens them on the friends, landed first.
     */
    @Test
    fun theAcceptedNoticeOpensTheFriend() {
        show(reader, choose = false) { activity = FriendsActivity(updates = listOf(FriendUpdate(FriendFixtures.bo.id, "Bo"))) }
        compose.waitUntil(5_000) { exists(FRIENDS_NOTICE) }
        compose.waitForIdle()
        shots.take("${name.methodName}-notice")
        press(FRIENDS_NOTICE_OPEN)
        compose.waitUntil(5_000) { !exists(FRIENDS_NOTICE) && exists(PeekTab.Friends.screen) }
        compose.waitUntil(5_000) { exists(friendCardTag(FriendFixtures.bo.id)) }
        // Revealed, Bo lands once the circle has scrolled to them, and the focus is let go as the landing goes on.
        compose.waitUntil(5_000) { source.friendsActivity.focusId == null }
        compose.waitForIdle()
        assertTrue("Bo's card drawn", drawn(FriendFixtures.bo.id))
        shot()
    }

    /**
     * A-S04, A-G18: the accepted notice's ✕, a finger on it through the screen, dismisses it where it stands: the
     * service told the friend has been seen, the deliveries left showing.
     */
    @Test
    fun theAcceptedNoticesCrossDismissesIt() {
        show(reader, choose = false) { activity = FriendsActivity(updates = listOf(FriendUpdate(FriendFixtures.bo.id, "Bo"))) }
        compose.waitUntil(5_000) { exists(FRIENDS_NOTICE_DISMISS) }
        compose.waitForIdle()
        ScreenTouches(compose).tap(FRIENDS_NOTICE_DISMISS)
        compose.waitUntil(5_000) { !exists(FRIENDS_NOTICE) }
        compose.waitUntil(5_000) {
            source.friendsClient.requests.any { it.action == FriendsAction.AcknowledgeFriend && it.friendId == FriendFixtures.bo.id }
        }
        compose.waitForIdle()
        assertFalse(exists(PeekTab.Friends.screen))
        shots.take("A-G18-notice-dismissed")
    }

    @Test
    fun theProfilesPanel() {
        show()
        tap(FRIENDS_SHARING)
        compose.waitUntil(5_000) { exists(FRIENDS_PANEL) }
        compose.waitForIdle()
        shot()
    }

    @Test
    fun theDemosInvitation() {
        show()
        tap(FRIENDS_INVITE)
        compose.waitUntil(5_000) { exists(FRIENDS_DEMO_SIGN_IN) }
        compose.waitForIdle()
        shot()
    }

    /** An account's invitation: its link made, copied ("Copied" from then on), and its links to manage open. */
    @Test
    fun anInvitationCopiedWithItsLinksToManage() {
        show(reader) { previous = 2 }
        tap(FRIENDS_INVITE)
        compose.waitUntil(5_000) { exists(FRIENDS_INVITATION_URL) }
        compose.waitForIdle()
        shots.take("${name.methodName}-link")
        instrumentation.runOnMainSync { clipBefore = compose.activity.getSystemService(ClipboardManager::class.java).primaryClip }
        copied = true
        tap(FRIENDS_COPY)
        compose.waitUntil(5_000) { says(FRIENDS_COPY, "Copied") }
        tap(FRIENDS_MANAGE_LINKS)
        compose.waitUntil(5_000) { exists(FRIENDS_REVOKE) }
        compose.waitForIdle()
        shot()
    }

    @Test
    fun theAccountButtonOpensSettings() {
        show(reader)
        compose.onNodeWithTag(FRIENDS_ACCOUNT_BUTTON).performClick()
        compose.waitUntil(5_000) { exists(SETTINGS_SHEET) }
        compose.waitForIdle()
        shot()
    }
}
