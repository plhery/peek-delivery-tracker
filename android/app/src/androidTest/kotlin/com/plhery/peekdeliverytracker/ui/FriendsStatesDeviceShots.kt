package com.plhery.peekdeliverytracker.ui

import android.app.Instrumentation
import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.graphics.Bitmap
import android.text.InputType
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.FriendInvitationLink
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_CLOSE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_CONFIRM_REMOVE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_ERROR
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_HEADER
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_INVITATION_URL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_INVITE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_JOIN
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_LOADING
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_MANAGE_FRIENDSHIP
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_NICKNAME
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_PAGE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_PAGE_BACK
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_PANEL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_POSTCARD
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_REMOVE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_REPLAY
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_RETRY
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SAVE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SCROLL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SHARE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SHARING
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_UNAVAILABLE_FRIEND
import com.plhery.peekdeliverytracker.ui.friends.FriendFixtures
import com.plhery.peekdeliverytracker.ui.friends.friendCardTag
import com.plhery.peekdeliverytracker.ui.friends.friendStampTag
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_GRABBER
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import java.util.UUID
import java.util.concurrent.atomic.AtomicReference
import kotlin.math.max
import kotlin.math.roundToInt
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The friends tab's remaining states and gestures on this device's own window (C-ST11, C-ST13 to C-ST17, C-G02, C-G03,
 * C-G07 to C-G09, C-G11, C-L09, C-AX12, C-AX13): the circle loading, failing with Retry, without a profile and without
 * friends, and pulled down to load again; the panel and a friend's page closed as another tab is chosen, and the page
 * closed once the circle no longer holds the friend; a friend revealed who is gone; the tab at the largest text; a name
 * cut to 24 scalars, a long link cut in its middle on one line and a long name on a postcard; the invitation's parcel
 * replayed by each tap; the panel thrown down, but not while it saves, and a page that keeps its back while a removal
 * runs; the link selected by a long press; the nickname's Done; and Share handing the link to the system's share sheet.
 * Taps, throws, drags and long presses that the rows are about go through the screen as a finger's do
 * ([ScreenTouches]). Signed in as the synthetic reader@example.com with made-up friends on fake deliveries
 * ([ParcelWalk]), which reach no server; each moment saved as friendstates/<row>-<state>.png, with -dark, and -largest
 * at the largest text (the composition's own font scale 2, read as iOS's accessibility2), in the test package's external
 * files. Each test keeps Zurich's time and the captures' moment, under Reduce Motion.
 */
@RunWith(AndroidJUnit4::class)
class FriendsStatesDeviceShots {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val touches = ScreenTouches(compose)
    private val shots = DeviceShots(compose, FOLDER)
    private val parcels: List<Parcel> = DemoCards.parcels()

    /** The friends' tab in the tab bar. */
    private fun friendsTab(): SemanticsMatcher =
        hasContentDescription(walk.text("friends.title")) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)

    /** The friends tab chosen in the tab bar, once it shows what is tagged [until]. */
    private fun chooseFriends(until: String = FRIENDS_SHARING) {
        compose.onNode(friendsTab()).performClick()
        compose.waitUntil(LONG_MILLIS) { walk.exists(until) }
        walk.settle()
    }

    /** The page of [friend], out of their card. */
    private fun openPage(friend: UUID) {
        walk.tap(friendCardTag(friend))
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_PAGE_BACK) }
        walk.settle()
    }

    /** The panel [from] opens, once it shows what is tagged [until]. */
    private fun openPanel(from: String, until: String) {
        walk.tap(from)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_PANEL) && walk.exists(until) }
        walk.settle()
    }

    private fun closePanel() {
        walk.tap(FRIENDS_CLOSE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FRIENDS_PANEL) }
        walk.settle()
    }

    /** The circle loaded again by the tab's own timer, its minute moved on. */
    private fun reload() {
        compose.mainClock.advanceTimeBy(RELOAD_MILLIS)
        compose.waitForIdle()
    }

    private fun bounds(matcher: SemanticsMatcher): Rect = touches.node(matcher).boundsInWindow

    private fun bounds(tag: String): Rect = bounds(hasTestTag(tag))

    /** How the text [matcher] finds first is laid out. */
    private fun layoutOf(matcher: SemanticsMatcher): TextLayoutResult {
        val node = touches.node(matcher)
        return walk.onUi {
            val laid = mutableListOf<TextLayoutResult>()
            node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(laid)
            laid.single()
        }
    }

    private fun windowHeight(): Float = walk.onUi { compose.activity.window.decorView.height.toFloat() }

    /** The sheet under the finger, thrown down from its drag indicator by half the window. */
    private fun throwDown() {
        touches.fling(touches.point(SHEET_GRABBER), Offset(0f, windowHeight() * 0.5f))
        walk.settle()
    }

    /** Whether a selection's handles show, as a long press on selectable text brings them. */
    private fun selectionShown(): Boolean = compose.onAllNodes(
        SemanticsMatcher("is a selection handle") { node -> node.config.any { it.key.name == "SelectionHandleInfo" } },
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    private fun parcelPicture(): Bitmap = compose.onAllNodes(hasTestTag(FRIENDS_REPLAY)).onFirst().captureToImage().asAndroidBitmap()

    /**
     * C-ST11, C-G03: the circle's first load held shows the spinner; failed, "friends.unavailable" over Retry; retried
     * onto an account without a profile, the intro with Join; then a profile without friends, the empty circle, which a
     * drag down from the circle's top, through the screen, loads again.
     */
    @Test
    fun theCircleLoadsFailsAndComesBackWhenPulled() {
        val held = CompletableDeferred<Unit>()
        walk.show(parcels) {
            friendsClient.loading = held
            friendsClient.failure = FriendFixtures.unavailable()
        }
        chooseFriends(until = FRIENDS_LOADING)
        assertTrue(walk.spins(FRIENDS_LOADING))
        assertFalse(walk.exists(FRIENDS_RETRY))
        walk.shot("C-ST11-loading")

        walk.onUi { walk.source.friendsClient.loading = null }
        held.complete(Unit)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_RETRY) }
        walk.settle()
        assertTrue(walk.shows(FRIENDS_ERROR, walk.text("friends.unavailable")))
        assertFalse(walk.exists(FRIENDS_LOADING))
        walk.shot("C-ST11-error")

        walk.onUi {
            walk.source.friendsClient.failure = null
            walk.source.friendsClient.snapshot = FriendFixtures.circle(emptyList(), profile = null)
        }
        walk.tap(FRIENDS_RETRY)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_JOIN) }
        walk.settle()
        assertFalse(walk.exists(FRIENDS_ERROR))
        assertFalse(walk.exists(FRIENDS_SHARING))
        walk.shot("C-ST11-intro")

        walk.onUi { walk.source.friendsClient.snapshot = FriendFixtures.circle(emptyList()) }
        val loads = walk.onUi { walk.source.friendsClient.loads }
        val circle = bounds(FRIENDS_SCROLL)
        touches.drag(touches.point(FRIENDS_SCROLL, y = 0.25f), Offset(0f, circle.height * 0.6f), steps = PULL_STEPS)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_INVITE) && walk.exists(FRIENDS_SHARING) }
        walk.settle()
        assertTrue("A pull loads the circle again", walk.onUi { walk.source.friendsClient.loads } > loads)
        assertTrue(walk.shows(FRIENDS_SCROLL, walk.text("friends.emptyTitle")))
        assertFalse(walk.exists(FRIENDS_JOIN))
        walk.shot("C-G03-pulled-empty-circle")
    }

    /**
     * C-ST13: another tab chosen while the panel shows (by a parcel's link, which chooses the deliveries, as the panel
     * keeps the tab bar from TalkBack and the finger), and again while a friend's page shows: back on the friends,
     * neither is open.
     */
    @Test
    fun leavingTheTabClosesThePanelAndThePage() {
        val inbox = DeliveriesInbox()
        val listed = parcels.filter { it.archivedAt == null }
        walk.show(parcels, inbox = inbox)
        chooseFriends()

        openPanel(FRIENDS_SHARING, until = FRIENDS_NICKNAME)
        walk.onUi { inbox.open(listed[0].id) }
        compose.waitUntil(LONG_MILLIS) { walk.onUi { inbox.parcel.value } == null && !walk.exists(FRIENDS_PANEL) }
        walk.settle()
        backToFriends()
        assertFalse(walk.exists(FRIENDS_PANEL))
        walk.shot("C-ST13-back-without-the-panel")

        openPage(FriendFixtures.bo.id)
        walk.onUi { inbox.open(listed[1].id) }
        compose.waitUntil(LONG_MILLIS) { walk.onUi { inbox.parcel.value } == null && !walk.exists(FRIENDS_PAGE) }
        walk.settle()
        backToFriends()
        assertFalse(walk.exists(FRIENDS_PAGE))
        walk.shot("C-ST13-back-without-the-page")
    }

    /** The friends tab chosen again from the deliveries, back first to the list where a parcel stands over it. */
    private fun backToFriends() {
        if (walk.stacked() && walk.shown() != null) walk.home()
        chooseFriends()
    }

    /** C-ST15: the circle loaded again without the friend whose page shows closes the page. */
    @Test
    fun aCircleWithoutTheOpenFriendClosesTheirPage() {
        walk.show(parcels)
        chooseFriends()
        openPage(FriendFixtures.bo.id)
        walk.onUi { walk.source.friendsClient.snapshot = FriendFixtures.circle(listOf(FriendFixtures.ada, FriendFixtures.cy, FriendFixtures.di)) }
        reload()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FRIENDS_PAGE) }
        walk.settle()
        assertTrue(walk.exists(FRIENDS_SHARING))
        assertFalse(walk.exists(friendCardTag(FriendFixtures.bo.id)))
        walk.shot("C-ST15-page-closed")
    }

    /** C-ST14: a link to a friend the circle no longer holds chooses the friends tab, which says they are gone. */
    @Test
    fun aFriendRevealedWhoIsGoneIsSaidToBe() {
        walk.show(parcels, inbox = DeliveriesInbox(friend = GONE), until = { walk.exists(FRIENDS_UNAVAILABLE_FRIEND) })
        assertTrue(walk.shows(FRIENDS_UNAVAILABLE_FRIEND, walk.text("friends.friendUnavailable")))
        assertTrue(walk.exists(FRIENDS_SHARING))
        walk.shot("C-ST14-friend-unavailable")
    }

    /**
     * C-ST16, C-AX12, C-AX13: at the largest text the intro's title grows with the title style and its summary wraps
     * rather than being cut; the circle's header puts Invite under its title, across the column; the invitation's panel
     * stands at the large detent in a compact window, its parcel over the sender's name; a friend's stamps go two to a
     * row.
     */
    @Test
    fun theFriendsTabAtTheLargestText() {
        walk.fontScale = 2f
        walk.show(parcels) { friendsClient.snapshot = FriendFixtures.circle(profile = null) }
        chooseFriends(until = FRIENDS_JOIN)
        val type = PeekTypography(DynamicTypeSize.Accessibility2, Density(compose.density.density, 2f))
        val title = layoutOf(hasText(walk.text("friends.title")) and hasAnyAncestor(hasTestTag(FRIENDS_SCROLL)))
        val scaled = type.fixed(type.scaled(26f, IosTextStyle.Title), FontWeight.SemiBold).fontSize.value
        assertEquals("The title grows with the title style", scaled, title.layoutInput.style.fontSize.value, 0.01f)
        assertTrue(scaled > type.fixed(26f).fontSize.value)
        val summary = layoutOf(hasText(walk.text("friends.introSummary")) and hasAnyAncestor(hasTestTag(FRIENDS_SCROLL)))
        assertFalse("The summary wraps, uncut", (0 until summary.lineCount).any(summary::isLineEllipsized) || summary.hasVisualOverflow)
        walk.shot("C-ST16-intro")

        walk.onUi { walk.source.friendsClient.snapshot = FriendFixtures.circle() }
        reload()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_HEADER) }
        walk.settle()
        val header = bounds(FRIENDS_HEADER)
        val circle = bounds(hasText(walk.text("friends.circle")) and hasAnyAncestor(hasTestTag(FRIENDS_HEADER)))
        val invite = bounds(FRIENDS_INVITE)
        assertTrue("Invite under the circle's title: $invite, $circle", invite.top >= circle.bottom - 0.5f)
        assertEquals("Invite across the column", header.width, invite.width, 1f)
        walk.shot("C-ST16-circle")

        openPanel(FRIENDS_INVITE, until = FRIENDS_INVITATION_URL)
        val (regular, largeTop) = walk.onUi {
            val view = compose.activity.window.decorView
            val density = compose.activity.resources.displayMetrics.density
            val insets = ViewCompat.getRootWindowInsets(view)?.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            (view.width / density >= 600f && view.height / density >= 480f) to max(insets?.top?.toFloat() ?: 0f, 10 * density)
        }
        if (!regular) assertEquals("The panel at the large detent", largeTop, bounds(SHEET_PANEL).top, 2f)
        val parcel = bounds(FRIENDS_REPLAY)
        // The sender's name, not the label the parcel draws on its card (cleared from TalkBack, in the test's tree).
        val from = bounds(hasText(walk.text("friends.from")) and hasAnyAncestor(hasTestTag(FRIENDS_PANEL)) and !hasAnyAncestor(hasTestTag(FRIENDS_REPLAY)))
        assertTrue("The parcel over the sender's name: $parcel, $from", parcel.bottom <= from.top + 0.5f)
        walk.shot("C-ST16-invitation")
        closePanel()

        openPage(FriendFixtures.ada.id)
        // Where each stamp stands, not what the window shows of it: on a phone the last rows are below its foot.
        val columns = FriendStamp.entries.filter { walk.exists(friendStampTag(it)) }
            .map { touches.node(hasTestTag(friendStampTag(it))).positionInWindow.x.roundToInt() }.toSet()
        assertEquals("Two stamps to a row", 2, columns.size)
        walk.shot("C-ST16-stamps")
    }

    /**
     * C-ST17: a name typed past 24 scalars is cut to its first 24, an emoji's halves kept together; the long link of a
     * service without short ids stays on one line, cut in its middle; a friend's long name wraps on their postcard
     * rather than being cut.
     */
    @Test
    fun longNamesAndALongLinkKeepTheirPlace() {
        walk.show(parcels) {
            friendsClient.snapshot = FriendFixtures.circle(listOf(konstantina, FriendFixtures.bo))
            friendsClient.answer = { request ->
                if (request.action == FriendsAction.CreateInvite) FriendsActionResponse(inviteCode = LEGACY_CODE) else FriendsActionResponse(snapshot = friendsClient.snapshot)
            }
        }
        chooseFriends()

        openPanel(FRIENDS_SHARING, until = FRIENDS_NICKNAME)
        walk.node(FRIENDS_NICKNAME).performTextReplacement(TOO_LONG)
        compose.waitForIdle()
        assertEquals(CUT, walk.editable(FRIENDS_NICKNAME))
        assertEquals(24, CUT.codePointCount(0, CUT.length))
        walk.shot("C-ST17-nickname-cut")
        closePanel()

        openPanel(FRIENDS_INVITE, until = FRIENDS_INVITATION_URL)
        val link = FriendInvitationLink.url(LEGACY_CODE, null, walk.source.linkBaseUrl)
        val laid = layoutOf(hasTestTag(FRIENDS_INVITATION_URL))
        assertEquals(link, laid.layoutInput.text.text)
        walk.shot("C-ST17-long-link")
        assertEquals("The link on one line", 1, laid.lineCount)
        assertTrue(
            "The link cut: ${laid.getLineEnd(0)} of ${link.length} characters on its line, ${laid.size} in ${laid.layoutInput.constraints}",
            laid.isLineEllipsized(0),
        )
        closePanel()

        openPage(konstantina.id)
        val name = layoutOf(hasText(konstantina.nickname) and hasAnyAncestor(hasTestTag(FRIENDS_POSTCARD)))
        // Each line whole within what the text shows: the paragraph is laid out across the room, wider than the name
        // it holds, which `hasVisualOverflow` counts as overflowing.
        val cut = (0 until name.lineCount).any { name.isLineEllipsized(it) || name.getLineRight(it) > name.size.width + 0.5f } ||
            name.didOverflowHeight
        assertFalse("The name uncut: ${name.lineCount} lines, ${name.size}", cut)
        walk.shot("C-ST17-long-postcard-name")
    }

    /**
     * C-G02: each tap on the invitation's parcel, through the screen, plays its opening again: closed right after the
     * tap, and open as before once played. The test's clock stands still between the tap and the frames it moves on.
     */
    @Test
    fun eachTapOnTheParcelReplaysIt() {
        walk.show(parcels)
        chooseFriends()
        openPanel(FRIENDS_INVITE, until = FRIENDS_INVITATION_URL)
        compose.mainClock.advanceTimeBy(PLAYED_MILLIS)
        compose.waitForIdle()
        val open = parcelPicture()
        compose.mainClock.autoAdvance = false
        try {
            for (tap in 1..2) {
                touches.tap(FRIENDS_REPLAY)
                repeat(3) { compose.mainClock.advanceTimeByFrame() }
                compose.waitForIdle()
                assertFalse("Tap $tap plays the opening again", parcelPicture().sameAs(open))
                if (tap == 1) shots.take("C-G02-replaying")
                compose.mainClock.advanceTimeBy(PLAYED_MILLIS)
                compose.waitForIdle()
                assertTrue("Open again after tap $tap", parcelPicture().sameAs(open))
            }
        } finally {
            compose.mainClock.autoAdvance = true
        }
    }

    /**
     * C-G07: the panel shows its drag indicator and goes when thrown down from it; while its save runs, neither a throw
     * nor the system's back closes it and Close is off, and it closes once saved.
     */
    @Test
    fun thePanelGoesWhenThrownDownButNotWhileItSaves() {
        walk.show(parcels)
        chooseFriends()
        openPanel(FRIENDS_SHARING, until = FRIENDS_NICKNAME)
        assertTrue(walk.exists(SHEET_GRABBER))
        walk.shot("C-G07-panel")
        throwDown()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FRIENDS_PANEL) }

        openPanel(FRIENDS_SHARING, until = FRIENDS_NICKNAME)
        val held = CompletableDeferred<Unit>()
        walk.onUi { walk.source.friendsClient.acting = held }
        walk.tap(FRIENDS_SAVE)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.friends.working } }
        compose.waitForIdle()
        walk.node(FRIENDS_CLOSE).assertIsNotEnabled()
        throwDown()
        assertTrue("A throw leaves the panel saving", walk.exists(FRIENDS_PANEL))
        walk.back()
        assertTrue("The back leaves the panel saving", walk.exists(FRIENDS_PANEL))
        assertFalse(compose.activity.isFinishing)
        walk.shot("C-G07-panel-saving")
        held.complete(Unit)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FRIENDS_PANEL) }
        assertTrue(walk.source.friendsClient.requests.any { it.action == FriendsAction.SaveProfile })
    }

    /**
     * C-G08: while a friend's removal runs their page has no ‹ and the system's back leaves it where it is (the page
     * takes the back without closing); once removed, the page closes.
     */
    @Test
    fun aFriendsPageHasNoBackWhileTheRemovalRuns() {
        walk.show(parcels)
        chooseFriends()
        openPage(FriendFixtures.bo.id)
        walk.tap(FRIENDS_MANAGE_FRIENDSHIP)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_REMOVE) }
        walk.tap(FRIENDS_REMOVE)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_CONFIRM_REMOVE) }
        assertTrue(walk.exists(FRIENDS_PAGE_BACK))
        val held = CompletableDeferred<Unit>()
        walk.onUi { walk.source.friendsClient.acting = held }
        walk.tap(FRIENDS_CONFIRM_REMOVE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FRIENDS_PAGE_BACK) }
        walk.node(FRIENDS_CONFIRM_REMOVE).assertIsNotEnabled()
        walk.back()
        assertTrue("The back leaves the page", walk.exists(FRIENDS_PAGE))
        assertTrue(walk.exists(PeekTab.Friends.screen))
        assertFalse(compose.activity.isFinishing)
        walk.shot("C-G08-removing")
        held.complete(Unit)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FRIENDS_PAGE) }
        assertTrue(walk.source.friendsClient.requests.any { it.action == FriendsAction.RemoveFriend && it.friendId == FriendFixtures.bo.id })
    }

    /** C-G09: a long press on the invitation's link, through the screen, selects it: its handles show. */
    @Test
    fun aLongPressSelectsTheInvitationLink() {
        walk.show(parcels)
        chooseFriends()
        openPanel(FRIENDS_INVITE, until = FRIENDS_INVITATION_URL)
        assertFalse(selectionShown())
        touches.longPress(touches.point(FRIENDS_INVITATION_URL, x = 0.2f))
        compose.waitUntil(LONG_MILLIS) { selectionShown() }
        walk.shot("C-G09-link-selected", alsoDark = false)
    }

    /** C-G11: the nickname asks the keyboard for Done and capitals at its sentences; Done lets go of the field. */
    @Test
    fun theNicknamesDoneLetsGoOfIt() {
        walk.show(parcels)
        chooseFriends()
        openPanel(FRIENDS_SHARING, until = FRIENDS_NICKNAME)
        touches.tap(FRIENDS_NICKNAME)
        walk.node(FRIENDS_NICKNAME).assertIsFocused()
        val info = walk.editorInfo()
        assertEquals(EditorInfo.IME_ACTION_DONE, info.imeOptions and EditorInfo.IME_MASK_ACTION)
        assertTrue(info.inputType and InputType.TYPE_TEXT_FLAG_CAP_SENTENCES != 0)
        walk.awaitKeyboard(required = false)
        walk.shot("C-G11-nickname-typing", alsoDark = false)
        walk.node(FRIENDS_NICKNAME).performImeAction()
        compose.waitForIdle()
        walk.node(FRIENDS_NICKNAME).assertIsNotFocused()
        walk.awaitStill()
        walk.shot("C-G11-nickname-done", alsoDark = false)
    }

    /**
     * C-L09: Share, tapped through the screen, hands the invitation's link as plain text to the system's share sheet,
     * counted as `friend-invite-share` started; the back closes the share sheet and leaves the panel.
     */
    @Test
    fun shareHandsTheLinkToTheShareSheet() {
        val chooser = AtomicReference<Intent?>(null)
        val monitor = object : Instrumentation.ActivityMonitor() {
            override fun onStartActivity(intent: Intent): Instrumentation.ActivityResult? {
                if (intent.action == Intent.ACTION_CHOOSER) chooser.set(intent)
                return null
            }
        }
        instrumentation.addMonitor(monitor)
        try {
            walk.show(parcels)
            chooseFriends()
            openPanel(FRIENDS_INVITE, until = FRIENDS_SHARE)
            touches.tap(FRIENDS_SHARE)
            compose.waitUntil(LONG_MILLIS) { chooser.get() != null }
            val send = IntentCompat.getParcelableExtra(chooser.get()!!, Intent.EXTRA_INTENT, Intent::class.java)
            assertNotNull(send)
            assertEquals(Intent.ACTION_SEND, send!!.action)
            assertEquals("text/plain", send.type)
            val link = FriendInvitationLink.url("code-1", FriendFixtures.previewId(1), walk.source.linkBaseUrl)
            assertEquals(link, send.getStringExtra(Intent.EXTRA_TEXT))
            val counted = walk.onUi { walk.source.actions.indexOf("friend-invite-share") }
            assertTrue(counted >= 0)
            assertEquals(DeliveryAnalytics.Outcome.Started, walk.onUi { walk.source.outcomes[counted] })
            compose.waitUntil(LONG_MILLIS) { !walk.onUi { compose.activity.hasWindowFocus() } }
            instrumentation.uiAutomation.waitForIdle(ParcelWalk.IDLE_MILLIS, LONG_MILLIS)
            shots.take("C-L09-share-sheet", ownWindow = false)
            instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
            compose.waitUntil(LONG_MILLIS) { walk.onUi { compose.activity.hasWindowFocus() } }
            walk.settle()
            assertTrue(walk.exists(FRIENDS_PANEL))
            assertTrue(walk.exists(FRIENDS_INVITATION_URL))
        } finally {
            instrumentation.removeMonitor(monitor)
        }
    }

    private companion object {
        const val FOLDER = "friendstates"

        /** A minute and a second: the tab loads the circle again every minute while it shows. */
        const val RELOAD_MILLIS = 61_000L

        /** Longer than the parcel's opening, its wait and its press's fade. */
        const val PLAYED_MILLIS = 3_000L

        /** A slow pull, a frame a step. */
        const val PULL_STEPS = 30

        /** A friend no circle holds. */
        val GONE: UUID = UUID.fromString("19000000-0000-4000-8000-000000000019")

        /** A made-up friend whose name is as long as a name goes, 24 scalars. */
        val konstantina = FriendCard(
            UUID.fromString("15000000-0000-4000-8000-000000000015"), "Konstantina Featherstone", FriendStats(4, 2, listOf(FriendStamp.First)),
        )

        /** A made-up invitation code, from a service that gives no short id, which makes the long link. */
        const val LEGACY_CODE = "Qa3vT8nL5wZ1pR7k"

        /** 23 letters, a tulip (two UTF-16 halves) as the 24th scalar, then more. */
        const val TOO_LONG = "Wilhelmina Featherstone🌷Blossom"
        const val CUT = "Wilhelmina Featherstone🌷"
    }
}
