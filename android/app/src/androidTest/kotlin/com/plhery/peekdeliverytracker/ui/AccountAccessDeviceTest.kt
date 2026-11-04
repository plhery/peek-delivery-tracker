package com.plhery.peekdeliverytracker.ui

import android.content.Intent
import android.net.Uri
import android.view.accessibility.AccessibilityNodeInfo
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.NotificationPreset
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_HEADING
import com.plhery.peekdeliverytracker.ui.arrival.WELCOME_OPEN_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ANNOUNCEMENT
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.INVITATION_BASE
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT_BELL
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT_DISMISS
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT_ENABLE
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_DEMO_SIGN_IN
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_INVITATION_URL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_INVITE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_PANEL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SHARE_ARRIVAL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SHARE_STATS
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SHARING
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ACCOUNT_DATA
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_CLOSE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_EMAIL
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_EMAIL_ERROR
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_UPDATES
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_HOME_PAGE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_WIDGETS
import com.plhery.peekdeliverytracker.ui.settings.appearanceTag
import com.plhery.peekdeliverytracker.ui.settings.presetTag
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import java.io.IOException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What TalkBack receives from the invitation, Settings, the friends tab and the notifications' card (C-AX01 to
 * C-AX17), read on this device's own window from the accessibility tree a service is given ([AccessTree]): the
 * invitation's parcel one button saying what a tap does under its heading; Settings' headings, close, rows and
 * appearance tiles, the chosen one heard selected; the delivery updates' presets the same, the email's failure said
 * through the window's polite live region; the friends' sharing row and its switches hinted with their detail, the
 * reader's invitation link read with its name, the demo's sign-in by its id; the notifications' card headed, its bell
 * saying nothing and its buttons a finger's 44 tall. Signed in as the synthetic reader@example.com on fake deliveries
 * and made-up friends whose stores answer every read in memory; each test keeps Zurich's time and the captures'
 * moment, the appearance it found and the preferences files the fakes write are put back.
 */
@RunWith(AndroidJUnit4::class)
class AccountAccessDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)
    private val tree = AccessTree(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val store = AppearanceStore.of(InstrumentationRegistry.getInstrumentation().targetContext)
    private val appearanceBefore = store.appearance.value

    @After
    fun putBack() {
        store.set(appearanceBefore)
    }

    /** The words read under `node`, its own first. */
    private fun said(node: AccessibilityNodeInfo): List<String> = tree.under(node).map { it.words }.filter(String::isNotEmpty)

    /** The node `id` once it reads `check`. */
    private fun awaitReading(id: String, what: String, check: (AccessibilityNodeInfo) -> Boolean): AccessibilityNodeInfo =
        tree.await("$id $what") { nodes -> nodes.firstOrNull { it.viewIdResourceName == id && check(it) } }

    /** The node `id`, scrolled into the window. */
    private fun shown(id: String): AccessibilityNodeInfo {
        compose.reveal(id)
        return tree.awaitId(id)
    }

    /** Settings, opened from the list's account button. */
    private fun openSettings() {
        walk.tap(ACCOUNT_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SHEET) }
        walk.awaitStill()
    }

    /** The friends tab, chosen from the tab bar. */
    private fun openFriends() {
        compose.onNode(hasContentDescription(walk.text(PeekTab.Friends.copyKey)) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_SHARING) }
        walk.settle()
    }

    // C-AX01, C-AX07, C-AX17: the invitation.

    /**
     * C-AX01, C-AX07, C-AX17: a friend's invitation stands its welcome in the window, the tabs gone from it; the
     * invitation's sentence is a heading, and the parcel one button found as "welcome.openParcel", read
     * "arrival.tapToOpen" with its drawing silent, whose double tap is said as "arrival.openHint". Nothing stops
     * without saying something.
     */
    @Test
    fun theInvitationsParcelIsOneButtonSayingWhatATapDoesUnderItsHeading() {
        val inbox = DeliveriesInbox(invitationLink = { walk.source.invitation.open(it) })
        walk.show(DemoCards.parcels(), inbox = inbox)
        walk.onUi { inbox.follow(Intent(Intent.ACTION_VIEW, Uri.parse(INVITATION_LINK))) }
        compose.waitUntil(LONG_MILLIS) { walk.exists(WELCOME_OPEN_PARCEL) }
        walk.settle()
        val open = awaitReading(WELCOME_OPEN_PARCEL, "on") { it.isEnabled }
        assertEquals(walk.text("arrival.tapToOpen"), open.description)
        assertTrue(open.isButton)
        assertEquals("The parcel's drawing is read", 0, open.childCount)
        assertEquals(walk.text("arrival.openHint"), open.clickLabel)
        val heading = tree.awaitId(INVITATION_HEADING)
        assertTrue(heading.isHeading)
        assertTrue(heading.words.isNotEmpty())
        assertNull("The tabs are read under the invitation", tree.byId(TAB_BAR))
        tree.assertNoBlankStops()
    }

    // C-AX08, C-AX10, C-AX14, C-AX17: Settings.

    /**
     * C-AX10, C-AX14, C-AX17, C-AX08: Settings' title is a heading and each group's title one heading, read as
     * written; its close is the button "common.close" a finger's 44 tall; its rows are found by their ids, each one
     * button or one switch at least 44 tall. Each appearance tile is a button read by its name, the chosen one heard
     * selected and the others not; another chosen takes it.
     */
    @Test
    fun settingsHeadsItsGroupsAndHearsTheChosenAppearance() {
        store.set(AppAppearance.Light)
        walk.show(DemoCards.parcels())
        openSettings()
        val title = walk.text("settings.title")
        tree.await("The heading \"$title\"", read = { tree.within(SETTINGS_SHEET) }) { nodes -> nodes.firstOrNull { it.isHeading && it.words == title } }
        val close = tree.awaitId(SETTINGS_CLOSE)
        assertTrue(close.isButton)
        assertTrue("The close reads ${said(close)}", walk.text("common.close") in said(close))
        close.assertTarget(SETTINGS_CLOSE, across = false)

        for ((key, id) in listOf("native.deliveries" to SETTINGS_DELIVERY_UPDATES, "settings.preferences" to appearanceTag(AppAppearance.Light))) {
            compose.reveal(id)
            val group = walk.text(key)
            val heading = tree.await("The group's heading \"$group\"", read = { tree.within(SETTINGS_SHEET) }) { nodes ->
                nodes.firstOrNull { node -> node.isHeading && said(node).any { it.equals(group, ignoreCase = true) } }
            }
            assertTrue("The group's title isn't read as one", tree.oneElement(heading))
        }
        for (id in listOf(SETTINGS_DELIVERY_UPDATES, SETTINGS_WIDGETS, SETTINGS_ACCOUNT_DATA, SETTINGS_HOME_PAGE)) {
            val row = shown(id)
            assertTrue("$id is neither a button nor a switch", row.isButton || row.isSwitch)
            assertTrue("$id isn't read as one", tree.oneElement(row))
            assertTrue(said(row).isNotEmpty())
            row.assertTarget(id, across = false)
        }

        for (option in AppAppearance.entries) {
            val tile = shown(appearanceTag(option))
            assertEquals(walk.text("native.appearance.${option.rawValue}"), tile.description)
            assertTrue(tile.isButton)
            assertEquals(0, tile.childCount)
            assertEquals("${option.rawValue} heard selected", option == AppAppearance.Light, tile.chosen)
        }
        assertTrue(shown(appearanceTag(AppAppearance.Dark)).click())
        awaitReading(appearanceTag(AppAppearance.Dark), "chosen") { it.chosen }
        assertFalse(checkNotNull(tree.byId(appearanceTag(AppAppearance.Light))).chosen)
        assertEquals(AppAppearance.Dark, store.appearance.value)
    }

    /**
     * C-AX08, C-AX09, C-AX17, A-AX22: the delivery updates' presets are buttons, each one element, the account's heard
     * selected and the others not until chosen; the delivery email is one switch found as "settings.deliveryEmail",
     * whose failure offline is said through the window's live region as "email.setting.failed" and shown under it.
     */
    @Test
    fun deliveryUpdatesHearTheChosenPresetAndSayTheEmailFailed() {
        walk.show(DemoCards.parcels()) { signedInWithEmail(false) }
        openSettings()
        assertTrue(shown(SETTINGS_DELIVERY_UPDATES).click())
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_DELIVERY_EMAIL) }
        walk.awaitStill()
        for (option in NotificationPreset.entries) {
            val row = shown(presetTag(option))
            assertTrue(row.isButton)
            assertTrue("${option.rawValue} isn't read as one", tree.oneElement(row))
            assertEquals("${option.rawValue} heard selected", option == NotificationPreset.Important, row.chosen)
        }
        assertTrue(shown(presetTag(NotificationPreset.All)).click())
        awaitReading(presetTag(NotificationPreset.All), "chosen") { it.chosen }
        assertFalse(checkNotNull(tree.byId(presetTag(NotificationPreset.Important))).chosen)

        val email = shown(SETTINGS_DELIVERY_EMAIL)
        assertTrue(email.isSwitch)
        assertTrue("The email isn't read as one switch", tree.oneElement(email))
        assertFalse(email.ticked)
        walk.onUi { walk.source.failure = IOException("offline") }
        val failed = walk.text("email.setting.failed")
        tree.holdingTheClock {
            assertTrue(email.click())
            tree.awaitSaid(ANNOUNCEMENT, failed)
        }
        compose.reveal(SETTINGS_DELIVERY_EMAIL_ERROR)
        assertEquals(failed, tree.awaitId(SETTINGS_DELIVERY_EMAIL_ERROR).words)
    }

    // C-AX10, C-AX11, C-AX14, C-AX17: the friends tab.

    /**
     * C-AX11, C-AX10: the friends' sharing row is one button read "friends.yourSharing" and "friends.settings"; it
     * opens the profile's panel, whose sharing switches are each one switch read by what they share, the stats with
     * their detail, and whose double tap is said as their detail. Nothing in the tab stops without saying something.
     */
    @Test
    fun theSharingRowIsOneButtonAndItsSwitchesSayTheirDetail() {
        walk.show(DemoCards.parcels())
        openFriends()
        tree.assertNoBlankStops()
        val sharing = tree.awaitId(FRIENDS_SHARING)
        assertEquals("${walk.text("friends.yourSharing")}, ${walk.text("friends.settings")}", sharing.description)
        assertTrue(sharing.isButton)
        assertEquals(0, sharing.childCount)
        assertTrue(sharing.click())
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_PANEL) }
        walk.awaitStill()
        val stats = shown(FRIENDS_SHARE_STATS)
        val summary = walk.text("friends.sharedStatsSummary")
        assertEquals("${walk.text("friends.shareStats")}, $summary", stats.description)
        assertTrue(stats.isSwitch)
        assertEquals(0, stats.childCount)
        assertEquals(summary, stats.clickLabel)
        val arrival = shown(FRIENDS_SHARE_ARRIVAL)
        assertEquals(walk.text("friends.shareArrival"), arrival.description)
        assertTrue(arrival.isSwitch)
        assertEquals(0, arrival.childCount)
        assertEquals(walk.text("friends.shareArrivalDetail"), arrival.clickLabel)
    }

    /**
     * C-AX11, C-AX14, C-AX17: Invite is a button 36 tall, as iOS's pill is; for a reader it makes the link, found as
     * "friends.invitationURL" and read "friends.link" with the whole address after it.
     */
    @Test
    fun aReadersInvitationLinkIsReadWithItsName() {
        walk.show(DemoCards.parcels())
        openFriends()
        val invite = shown(FRIENDS_INVITE)
        assertTrue(invite.isButton)
        invite.assertTarget(FRIENDS_INVITE, side = 36f, across = false)
        assertTrue(invite.click())
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_INVITATION_URL) }
        walk.awaitStill()
        val url = shown(FRIENDS_INVITATION_URL)
        val link = url.text?.toString().orEmpty()
        assertTrue(link, link.startsWith("http"))
        assertEquals("${walk.text("friends.link")}: $link", url.description)
    }

    /** C-AX17: the demo's invitation offers the sign-in as a button found as "friends.demoSignIn". */
    @Test
    fun theDemosInvitationOffersTheSignInByItsId() {
        walk.show(DemoCards.parcels(), session = AuthenticationState.Demo)
        openFriends()
        assertTrue(shown(FRIENDS_INVITE).click())
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_DEMO_SIGN_IN) }
        walk.awaitStill()
        val signIn = shown(FRIENDS_DEMO_SIGN_IN)
        assertTrue(signIn.isButton)
        assertTrue("The sign-in isn't read as one", tree.oneElement(signIn))
        assertTrue(walk.text("welcome.signInInstead") in said(signIn))
    }

    // C-AX07, C-AX10, C-AX14, C-AX17: the notifications' card.

    /**
     * C-AX07, C-AX10, C-AX14, C-AX17: the notifications' card is no stop of its own but holds its parts; its title is a
     * heading, its bell says nothing, and Turn on and Not now are buttons found by their ids, each one element a
     * finger's 44 tall. Turning on failing shows why under the title, found as "notifications.prompt.error".
     */
    @Test
    fun theNotificationsCardIsHeadedItsBellSilentAndItsButtonsFingerSized() {
        walk.show(DemoCards.parcels()) { shouldInviteNotifications.value = true }
        compose.waitUntil(LONG_MILLIS) { walk.exists(NOTIFICATIONS_PROMPT) }
        compose.reveal(NOTIFICATIONS_PROMPT_DISMISS)
        val card = tree.await("The notifications' card", read = { tree.within(NOTIFICATIONS_PROMPT) }) { it.ifEmpty { null } }
        val container = card.first()
        assertFalse("The card is read as one", container.isScreenReaderFocusable || container.isClickable)
        val title = walk.text("notifications.prompt.title")
        assertTrue("The card's title is no heading\n${tree.dump(card)}", card.any { it.isHeading && it.words == title })
        card.firstOrNull { it.viewIdResourceName == NOTIFICATIONS_PROMPT_BELL }?.let { bell ->
            assertTrue("The bell is read", tree.under(bell).all { it.words.isEmpty() && !it.isScreenReaderFocusable && !it.isClickable })
        }
        for ((id, key) in listOf(NOTIFICATIONS_PROMPT_ENABLE to "notifications.enable", NOTIFICATIONS_PROMPT_DISMISS to "onboarding.notifications.notNow")) {
            val button = checkNotNull(card.firstOrNull { it.viewIdResourceName == id }) { "No $id\n${tree.dump(card)}" }
            assertTrue(button.isButton)
            assertTrue("$id isn't read as one", tree.oneElement(button))
            assertTrue(walk.text(key) in said(button))
            button.assertTarget(id, across = false)
        }
        tree.assertNoBlankStops(card)

        walk.onUi { walk.source.enableFailure = IOException("offline") }
        assertTrue(tree.awaitId(NOTIFICATIONS_PROMPT_ENABLE).click())
        walk.settle()
        assertTrue(tree.awaitId(NOTIFICATIONS_PROMPT_ERROR).words.isNotEmpty())
    }

    private companion object {
        /** A made-up invitation of the fakes' service. */
        const val INVITATION_LINK = "$INVITATION_BASE/invite#Qa3vT8nL5wZ1pR7k"
    }
}
