package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityService
import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Rect
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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
import kotlin.math.abs
import kotlin.math.min
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
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
 * accepted notice opening the friend, its ✕ tapped through the screen dismissing it, its place under the status bar,
 * and its coming and going at once with animations on (A-S04, A-G18); the profile's panel; the demo's invitation; an
 * account's invitation with its link copied and its manage links open; the account button opening Settings. Each saved
 * as friends/<test>.png in the test package's external files, which adb pulls. Under Reduce Motion, as the other device
 * tests are, but for the notice's coming and going; each test puts back the appearance it found, and the copy the
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
     * unless [choose]. Under Reduce Motion unless [reduceMotion] is false.
     */
    private fun show(
        session: AuthenticationState = AuthenticationState.Demo,
        choose: Boolean = true,
        reduceMotion: Boolean = true,
        friends: FakeFriendsClient.() -> Unit = {},
    ) {
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
            RootHost(state = session, dark = dark, reduceMotion = reduceMotion, source = source)
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

    /**
     * A-S04: the accepted notice stands 16 in from the window's sides, inside its bars and cutout, and 6 under the status
     * bar, as RootView's overlay pads it; where the window has room, it is 520 wide at most, in the middle.
     */
    @Test
    fun theAcceptedNoticeStandsUnderTheStatusBarSixteenInFromTheSides() {
        show(reader, choose = false) { activity = FriendsActivity(updates = listOf(FriendUpdate(FriendFixtures.bo.id, "Bo"))) }
        compose.waitUntil(5_000) { exists(FRIENDS_NOTICE) }
        compose.waitForIdle()
        assertNoticeRests()
        shots.take("A-S04-notice-place")
    }

    /**
     * A-S04: with animations on, the notice comes and goes at once, as the iPhone's does: RootView's overlay declares a
     * move from the top with a fade, but the store changes its notices outside any animation, so SwiftUI never plays
     * it. On a clock the test moves a frame at a time, the notice stands where it rests, drawn in full, on the first
     * frame it shows, as it does a second later; and once its ✕ has taken it off the store, it has gone within two frames.
     */
    @Test
    fun theAcceptedNoticeComesAndGoesAtOnceWithAnimationsOn() {
        show(reader, choose = false, reduceMotion = false)
        assertFalse(exists(FRIENDS_NOTICE))
        compose.mainClock.autoAdvance = false
        source.friendsClient.activity = FriendsActivity(updates = listOf(FriendUpdate(FriendFixtures.bo.id, "Bo")))
        runBlocking { source.friendsActivity.refresh() }
        var frames = 0
        while (!exists(FRIENDS_NOTICE)) {
            assertTrue("No notice after $frames frames", ++frames <= NOTICE_FRAMES)
            nextFrame()
        }
        assertNoticeRests()
        val area = noticeMiddle()
        val first = pixels(area)
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertNoticeRests()
        val later = pixels(area)
        val changed = first.indices.count { apart(first[it], later[it]) > PIXEL_APART }
        assertTrue("$changed of ${first.size} pixels changed after its first frame", changed <= first.size / 500)
        shots.take("A-S04-notice-at-once")

        compose.onNodeWithTag(FRIENDS_NOTICE_DISMISS).performClick()
        var lingering = 0
        frames = 0
        while (exists(FRIENDS_NOTICE)) {
            assertTrue("The notice still there after $frames frames", ++frames <= NOTICE_FRAMES)
            if (source.friendsActivity.updates.isEmpty()) lingering++
            nextFrame()
        }
        assertTrue("Off the store", source.friendsActivity.updates.isEmpty())
        assertTrue("Drawn $lingering frames after it left the store", lingering <= 2)
        compose.mainClock.autoAdvance = true
    }

    /** The notice where RootView's overlay rests it, in this window: 16 in from the safe area, 6 under it, 520 at most. */
    private fun assertNoticeRests() {
        val (left, top, right) = compose.runOnUiThread {
            val view = compose.activity.window.decorView
            val scale = compose.activity.resources.displayMetrics.density
            val sides = WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            val safe = ViewCompat.getRootWindowInsets(view)?.getInsets(sides)
            val start = (safe?.left ?: 0) / scale + NOTICE_SIDE
            val end = (view.width - (safe?.right ?: 0)) / scale - NOTICE_SIDE
            val width = min(NOTICE_WIDTH, end - start)
            val leading = start + (end - start - width) / 2
            Triple(leading, (safe?.top ?: 0) / scale + NOTICE_TOP, leading + width)
        }
        val notice = compose.onNodeWithTag(FRIENDS_NOTICE, useUnmergedTree = true).getUnclippedBoundsInRoot()
        assertEquals("Its leading edge: $notice", left, notice.left.value, 1f)
        assertEquals("Its top: $notice", top, notice.top.value, 1f)
        assertEquals("Its trailing edge: $notice", right, notice.right.value, 1f)
    }

    /** The notice's middle, in the window's pixels: clear of its rounded ends and its line, its words, → and ✕. */
    private fun noticeMiddle(): Rect {
        val notice = compose.onNodeWithTag(FRIENDS_NOTICE, useUnmergedTree = true).getUnclippedBoundsInRoot()
        val scale = compose.density.density
        return Rect(
            ((notice.left + NOTICE_ENDS).value * scale).toInt(), ((notice.top + 2.dp).value * scale).toInt(),
            ((notice.right - NOTICE_ENDS).value * scale).toInt(), ((notice.bottom - 2.dp).value * scale).toInt(),
        )
    }

    /** The window's pixels in [area], row by row. */
    private fun pixels(area: Rect): IntArray {
        val window = compose.onRoot().captureToImage().asAndroidBitmap()
        return IntArray(area.width() * area.height()).also { window.getPixels(it, 0, area.width(), area.left, area.top, area.width(), area.height()) }
    }

    /** How far apart two pixels are, in the channel they differ most in, out of 255. */
    private fun apart(one: Int, other: Int): Int =
        listOf(24, 16, 8, 0).maxOf { shift -> abs((one shr shift and 0xFF) - (other shr shift and 0xFF)) }

    /** One frame of the test's clock, and the UI idle. */
    private fun nextFrame() {
        compose.mainClock.advanceTimeByFrame()
        compose.waitForIdle()
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

    private companion object {
        /** The notice's room from the safe area's sides and top, and its widest, in dp (RootView, FriendAcceptedNotice). */
        const val NOTICE_SIDE = 16f
        const val NOTICE_TOP = 6f
        const val NOTICE_WIDTH = 520f

        /** How far in from the notice's ends its middle starts, past its continuous corners. */
        val NOTICE_ENDS = 36.dp

        /** The most frames the notice takes to come or go, a second's worth, before the test gives up on it. */
        const val NOTICE_FRAMES = 60

        /** How far apart, out of 255, two pixels of the notice may be before they count as changed. */
        const val PIXEL_APART = 64
    }
}
