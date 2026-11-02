package com.plhery.peekdeliverytracker.ui.friends

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsConfiguration
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendUpdate
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.FriendsActivity
import com.plhery.peekdeliverytracker.core.explanationKey
import com.plhery.peekdeliverytracker.core.titleKey
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.DEMO_EXIT
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A friend's page (C-S22, C-S29, C-S30, C-AN17) and the reveal's landing and notice (C-S31) on made-up friends: the
 * page out of its card and back, the tab bar hidden; the postcard, the metrics, the stamps by text size with all of
 * them or the next three, their popovers and progress; stats kept private; manage friendship, the removal asked first,
 * waiting with no back and closing the page, refused, and the demo's; a friend revealed landing in the middle,
 * acknowledged; the accepted notice opening the friend or dismissed, and under a sheet for TalkBack.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class FriendPageTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private val client get() = source.friendsClient
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    private val owner = object : LifecycleOwner {
        val registry = LifecycleRegistry.createUnsafe(this).apply { currentState = Lifecycle.State.RESUMED }
        override val lifecycle: Lifecycle get() = registry
    }

    private var shownSession by mutableStateOf<AuthenticationState>(reader)

    /** The friends tab of [session] at [fontScale], chosen from the tab bar unless [choose] is false. */
    private fun show(
        session: AuthenticationState = reader,
        fontScale: Float = 1f,
        choose: Boolean = true,
        reduceMotion: Boolean = true,
        friends: FakeFriendsClient.() -> Unit = {},
    ) {
        shownSession = session
        source = FakeDeliveries(DemoCards.parcels(), session)
        client.friends()
        compose.waitsForMaps()
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalLifecycleOwner provides owner) {
                RootHost(state = shownSession, dark = false, reduceMotion = reduceMotion, source = source)
            }
        }
        settle()
        if (choose) choose("Friends")
    }

    private fun choose(label: String) {
        compose.onNode(hasContentDescription(label) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun text(key: String, variables: Map<String, Any> = emptyMap()) = localizer.text(key, variables)

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun shows(text: String) = compose.onAllNodes(hasText(text)).fetchSemanticsNodes().isNotEmpty()

    private fun semantics(tag: String): SemanticsConfiguration = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config

    private fun label(tag: String): String = semantics(tag)[SemanticsProperties.ContentDescription].single()

    private fun words(tag: String): List<String> {
        val words = mutableListOf<String>()
        fun walk(node: SemanticsNode) {
            node.config.getOrNull(SemanticsProperties.Text)?.let { texts -> words += texts.map { it.text } }
            node.children.forEach(::walk)
        }
        walk(compose.onNodeWithTag(tag).fetchSemanticsNode())
        return words
    }

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        settle()
    }

    private fun press(tag: String) {
        compose.onNodeWithTag(tag).performClick()
        settle()
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    private fun cards(): List<String> = compose.onAllNodes(
        SemanticsMatcher("a friend's card") { it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith("friends.card.") == true },
    ).fetchSemanticsNodes().sortedBy { it.boundsInRoot.top }.map { it.config[SemanticsProperties.Text].first().text }

    /** The stamps the page shows, in their order. */
    private fun stamps(): List<FriendStamp> = FriendStamp.entries.filter { exists(friendStampTag(it)) }

    /** What the stamp's popover says, its title then its explanation; null with none open. */
    private fun popover(): List<String>? = compose.onAllNodes(hasTestTag(FRIENDS_POPOVER), useUnmergedTree = true).fetchSemanticsNodes()
        .singleOrNull()?.children?.map { it.config.getOrNull(SemanticsProperties.Text)?.joinToString().orEmpty() }

    private fun tabBarShows(): Boolean = compose.onAllNodes(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).fetchSemanticsNodes().isNotEmpty()

    private fun requests(action: FriendsAction): List<FriendsActionRequest> = client.requests.filter { it.action == action }

    private fun open(friend: FriendCard) = tap(friendCardTag(friend.id))


    /** How many stamps stand in the row of the first one shown. */
    private fun firstRow(): Int {
        fun top(stamp: FriendStamp) = compose.onNodeWithTag(friendStampTag(stamp), useUnmergedTree = true).getUnclippedBoundsInRoot().top
        val shown = stamps()
        return shown.count { top(it) == top(shown.first()) }
    }

    /** Where the friend's card stands from the window's leading edge, as drawn. */
    private fun left(friend: FriendCard) = compose.onNodeWithTag(friendCardTag(friend.id), useUnmergedTree = true).getUnclippedBoundsInRoot().left

    // The page (C-S29) and its zoom (C-AN17).

    @Test
    fun aFriendsPageHidesTheTabBarAndTheCircleAndItsBackGoesBackToThem() {
        show()
        open(FriendFixtures.ada)
        assertTrue(exists(FRIENDS_PAGE))
        assertFalse(tabBarShows())
        // The circle under the page is out of TalkBack's reach; the bar reads its title as a heading, and its back.
        assertEquals(emptyList<String>(), cards())
        assertEquals(listOf(text("passport.title")), words(FRIENDS_PAGE_BAR))
        compose.onNode(hasText(text("passport.title")) and SemanticsMatcher.keyIsDefined(SemanticsProperties.Heading)).assertExists()
        assertEquals(text("friends.title"), label(FRIENDS_PAGE_BACK))
        assertEquals(Role.Button, semantics(FRIENDS_PAGE_BACK)[SemanticsProperties.Role])
        press(FRIENDS_PAGE_BACK)
        assertFalse(exists(FRIENDS_PAGE))
        assertTrue(tabBarShows())
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    @Test
    fun outOfItsCardAndBackIntoItThePageComesAndGoesAndTheTabBarWithIt() {
        show(reduceMotion = false)
        open(FriendFixtures.bo)
        assertTrue(exists(FRIENDS_PAGE))
        assertFalse(tabBarShows())
        back()
        assertFalse(exists(FRIENDS_PAGE))
        // Back in its card, the page leaves the tab bar to come back a little after, as the iPhone's does.
        assertFalse(tabBarShows())
        settle()
        assertTrue(tabBarShows())
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    @Test
    fun thePageShowsThePostcardTheMetricsAndTheStampsEarnedAndTheNextThree() {
        show()
        open(FriendFixtures.ada)
        assertEquals(listOf("Ada", text("friends.arrived")), words(FRIENDS_POSTCARD))
        assertEquals(listOf("8", text("passport.delivered", mapOf("count" to 8)), "2 days", text("passport.average")), words(FRIENDS_METRICS))
        assertTrue(shows(text("passport.stamps")))
        // Earned, First and Express; then the next three to earn, in the stamps' order, four to a row.
        assertEquals(listOf(FriendStamp.First, FriendStamp.Ten, FriendStamp.Connected, FriendStamp.Express, FriendStamp.AcrossBorders), stamps())
        assertEquals(4, firstRow())
        // Each stamp reads as its title alone, a button.
        assertEquals(text(FriendStamp.Ten.titleKey), label(friendStampTag(FriendStamp.Ten)))
        assertEquals(Role.Button, semantics(friendStampTag(FriendStamp.Ten))[SemanticsProperties.Role])
        assertEquals(listOf(text("passport.showAll")), words(FRIENDS_SHOW_ALL))
        tap(FRIENDS_SHOW_ALL)
        assertEquals(FriendStamp.entries, stamps())
        assertEquals(listOf(text("passport.showLess")), words(FRIENDS_SHOW_ALL))
        tap(FRIENDS_SHOW_ALL)
        assertEquals(5, stamps().size)
    }

    @Test
    fun aFriendWithNoDeliveryYetShowsADashAndTheFirstThreeStamps() {
        show()
        open(FriendFixtures.di)
        assertEquals(listOf("Di"), words(FRIENDS_POSTCARD))
        assertEquals(listOf("0", text("passport.delivered", mapOf("count" to 0)), "—", text("passport.average")), words(FRIENDS_METRICS))
        assertEquals(listOf(FriendStamp.First, FriendStamp.Ten, FriendStamp.Connected), stamps())
        assertTrue(exists(FRIENDS_SHOW_ALL))
    }

    @Test
    fun aFriendWhoKeepsTheirStatsPrivateShowsNoneButSaysSo() {
        show()
        open(FriendFixtures.cy)
        assertEquals(listOf("Cy"), words(FRIENDS_POSTCARD))
        assertEquals(listOf(text("friends.privateStats")), words(FRIENDS_PRIVATE_STATS))
        assertFalse(exists(FRIENDS_METRICS))
        assertEquals(emptyList<FriendStamp>(), stamps())
        assertFalse(exists(FRIENDS_SHOW_ALL))
        assertTrue(exists(FRIENDS_MANAGE_FRIENDSHIP))
    }

    @Test
    fun withNineStampsEarnedAllTwelveShowWithoutShowAll() {
        val ev = FriendCard(UUID.fromString("14000000-0000-4000-8000-000000000014"), "Ev", FriendStats(30, 1, FriendStamp.entries.take(9)))
        show { snapshot = FriendFixtures.circle(listOf(ev)) }
        open(ev)
        assertEquals(FriendStamp.entries, stamps())
        assertFalse(exists(FRIENDS_SHOW_ALL))
    }

    @Test
    fun fromTheXxLargeTextTheStampsGoThreeToARow() {
        show(fontScale = 1.3f)
        open(FriendFixtures.ada)
        assertEquals(3, firstRow())
    }

    @Test
    fun atTheAccessibilitySizesTheStampsGoTwoToARow() {
        show(fontScale = 2f)
        open(FriendFixtures.ada)
        assertEquals(2, firstRow())
    }

    // The stamp's popover (C-S30).

    @Test
    fun aStampsPopoverSaysHowItIsEarnedAndUntilThenHowFarAlong() {
        show()
        open(FriendFixtures.ada)
        assertNull(popover())
        tap(friendStampTag(FriendStamp.Ten))
        assertEquals(listOf(text(FriendStamp.Ten.titleKey), text(FriendStamp.Ten.explanationKey) + "\n8 / 10"), popover())
        // The back closes the popover and leaves the page.
        back()
        assertNull(popover())
        assertTrue(exists(FRIENDS_PAGE))
        // Earned, or with no count to reach: how it is earned alone.
        tap(friendStampTag(FriendStamp.First))
        assertEquals(listOf(text(FriendStamp.First.titleKey), text(FriendStamp.First.explanationKey)), popover())
        back()
        tap(friendStampTag(FriendStamp.Connected))
        assertEquals(listOf(text(FriendStamp.Connected.titleKey), text(FriendStamp.Connected.explanationKey)), popover())
    }

    @Test
    fun anUnearnedExpressSaysWithin48Hours() {
        show()
        open(FriendFixtures.bo)
        tap(friendStampTag(FriendStamp.Express))
        assertEquals(text(FriendStamp.Express.explanationKey) + "\n" + text("passport.underTwoDays"), popover()?.last())
    }

    @Test
    fun theProgressCountsUpToItsMarkAndTheExpressSaysWithin48Hours() {
        val within = text("passport.underTwoDays")
        assertEquals("\n1 / 1", progress(FriendStamp.First, earned = false, count = 3, underTwoDays = within))
        assertEquals("\n0 / 1", progress(FriendStamp.First, earned = false, count = 0, underTwoDays = within))
        assertEquals("\n10 / 10", progress(FriendStamp.Ten, earned = false, count = 12, underTwoDays = within))
        assertEquals("\n12 / 25", progress(FriendStamp.TheRegular, earned = false, count = 12, underTwoDays = within))
        assertEquals("\n$within", progress(FriendStamp.Express, earned = false, count = 12, underTwoDays = within))
        assertEquals("", progress(FriendStamp.WorthTheWait, earned = false, count = 12, underTwoDays = within))
        assertEquals("", progress(FriendStamp.TheRegular, earned = true, count = 30, underTwoDays = within))
    }

    // Manage friendship and the removal (C-S29).

    @Test
    fun removingAFriendAsksFirstWaitsWithNoBackThenClosesTheirPage() {
        show()
        open(FriendFixtures.bo)
        assertFalse(exists(FRIENDS_REMOVE))
        assertEquals(listOf(text("friends.manageFriendship")), words(FRIENDS_MANAGE_FRIENDSHIP))
        tap(FRIENDS_MANAGE_FRIENDSHIP)
        assertEquals(listOf(text("friends.remove")), words(FRIENDS_REMOVE))
        tap(FRIENDS_REMOVE)
        assertTrue(shows(text("friends.removeTitle", mapOf("name" to "Bo"))))
        assertTrue(shows(text("friends.removeDetail")))
        assertEquals(listOf(text("friends.remove")), words(FRIENDS_CONFIRM_REMOVE))
        // Cancel goes back to Remove friend.
        tap(FRIENDS_CANCEL_REMOVE)
        assertFalse(exists(FRIENDS_CONFIRM_REMOVE))
        tap(FRIENDS_REMOVE)
        val acting = CompletableDeferred<Unit>()
        client.acting = acting
        client.answer = { FriendsActionResponse(snapshot = FriendFixtures.circle(listOf(FriendFixtures.ada, FriendFixtures.cy, FriendFixtures.di))) }
        tap(FRIENDS_CONFIRM_REMOVE)
        assertEquals(listOf(FriendsActionRequest(FriendsAction.RemoveFriend, friendId = FriendFixtures.bo.id)), requests(FriendsAction.RemoveFriend))
        // While it is done: no ‹, the back taken without closing the page, and Remove friend and Cancel off.
        assertFalse(exists(FRIENDS_PAGE_BACK))
        back()
        assertTrue(exists(FRIENDS_PAGE))
        compose.onNodeWithTag(FRIENDS_CONFIRM_REMOVE).assertIsNotEnabled()
        compose.onNodeWithTag(FRIENDS_CANCEL_REMOVE).assertIsNotEnabled()
        acting.complete(Unit)
        settle()
        assertFalse(exists(FRIENDS_PAGE))
        assertTrue(tabBarShows())
        assertEquals(listOf("Ada", "Cy", "Di"), cards())
    }

    @Test
    fun aRemovalTheServiceRefusesSaysWhyOnThePageAndKeepsIt() {
        show()
        open(FriendFixtures.bo)
        tap(FRIENDS_MANAGE_FRIENDSHIP)
        tap(FRIENDS_REMOVE)
        client.actionFailure = FriendFixtures.unavailable()
        tap(FRIENDS_CONFIRM_REMOVE)
        assertTrue(exists(FRIENDS_PAGE))
        // Said once, on the page: the circle's under it is out of reach.
        assertEquals(text("friends.actionFailed"), words(FRIENDS_ERROR).single())
        assertTrue(exists(FRIENDS_PAGE_BACK))
        compose.onNodeWithTag(FRIENDS_CONFIRM_REMOVE).assertIsEnabled()
    }

    @Test
    fun theDemosFriendIsRemovedFromTheirPageInMemory() {
        show(AuthenticationState.Demo)
        tap(friendCardTag(UUID.fromString("33000000-0000-4000-8000-000000000003")))
        assertEquals(listOf("Sam"), words(FRIENDS_POSTCARD))
        // The demo's bar stands on the page too, under its bar.
        assertEquals(1, compose.onAllNodes(hasTestTag(DEMO_EXIT)).fetchSemanticsNodes().size)
        assertTrue(compose.onNodeWithTag(DEMO_EXIT).getUnclippedBoundsInRoot().top >= compose.onNodeWithTag(FRIENDS_PAGE_BAR).getUnclippedBoundsInRoot().bottom)
        tap(FRIENDS_MANAGE_FRIENDSHIP)
        tap(FRIENDS_REMOVE)
        tap(FRIENDS_CONFIRM_REMOVE)
        assertFalse(exists(FRIENDS_PAGE))
        assertEquals(listOf("Mila", "Noé"), cards())
        assertTrue(client.requests.isEmpty())
    }

    @Test
    fun inTheBackgroundAFriendsPageClosesAndTheCircleLoadsAgainInFront() {
        show()
        open(FriendFixtures.ada)
        compose.runOnUiThread { owner.registry.currentState = Lifecycle.State.CREATED }
        settle()
        assertFalse(exists(FRIENDS_PAGE))
        compose.runOnUiThread { owner.registry.currentState = Lifecycle.State.RESUMED }
        settle()
        assertTrue(tabBarShows())
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    // The reveal's landing (C-S31).

    @Test
    fun aFriendRevealedLandsFromTheRightAndIsAcknowledged() {
        show(reduceMotion = false)
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.di.id) }
        compose.mainClock.advanceTimeBy(90)
        compose.waitForIdle()
        // First, out of sight 100 to the right of its place, until the circle has scrolled to it.
        assertEquals(listOf("Di", "Ada", "Bo", "Cy"), cards())
        assertEquals(100f, (left(FriendFixtures.di) - left(FriendFixtures.ada)).value, 0.5f)
        assertEquals(FriendFixtures.di.id, source.friendsActivity.focusId)
        assertTrue(requests(FriendsAction.AcknowledgeFriend).isEmpty())
        compose.mainClock.advanceTimeBy(1_500)
        compose.waitForIdle()
        assertEquals(0f, (left(FriendFixtures.di) - left(FriendFixtures.ada)).value, 0.5f)
        assertEquals(listOf(FriendFixtures.di.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
        assertNull(source.friendsActivity.focusId)
        compose.mainClock.autoAdvance = true
    }

    @Test
    fun underReduceMotionAFriendRevealedComesInWhereItStands() {
        show()
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.di.id) }
        compose.mainClock.advanceTimeBy(90)
        compose.waitForIdle()
        assertEquals(0f, (left(FriendFixtures.di) - left(FriendFixtures.ada)).value, 0.5f)
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertEquals(listOf(FriendFixtures.di.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
        compose.mainClock.autoAdvance = true
    }

    @Test
    fun aFriendRevealedFromFarDownALongCircleIsBroughtIntoSight() {
        val many = (1..14).map { index ->
            FriendCard(UUID.fromString("2%07d-0000-4000-8000-%012d".format(index, index)), "Friend $index", FriendStats(index, 2, listOf(FriendStamp.First)))
        }
        show { snapshot = FriendFixtures.circle(many) }
        val last = friendCardTag(many.last().id)
        compose.onNodeWithTag(last).performScrollTo()
        settle()
        val window = compose.onRoot().getUnclippedBoundsInRoot()
        compose.runOnUiThread { source.friendsActivity.reveal(many.last().id) }
        settle()
        // It comes first, and the circle scrolls back up to it.
        val card = compose.onNodeWithTag(last, useUnmergedTree = true).getUnclippedBoundsInRoot()
        assertTrue(card.top < compose.onNodeWithTag(friendCardTag(many.first().id), useUnmergedTree = true).getUnclippedBoundsInRoot().top)
        assertTrue("${card.top}", card.top > 0.dp && card.bottom < window.height)
        assertEquals(listOf(many.last().id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
    }

    @Test
    fun eachPresentationLandsItsFriendOnce() {
        show()
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.bo.id) }
        settle()
        assertEquals(listOf("Bo", "Ada", "Cy", "Di"), cards())
        // Landed and let go, Bo stays first while the tab shows, through the minute's loads.
        compose.mainClock.advanceTimeBy(60_000)
        compose.waitForIdle()
        assertEquals(listOf("Bo", "Ada", "Cy", "Di"), cards())
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.cy.id) }
        settle()
        assertEquals(listOf("Cy", "Ada", "Bo", "Di"), cards())
        assertEquals(listOf(FriendFixtures.bo.id, FriendFixtures.cy.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
    }

    // The accepted notice (C-S31).

    private val accepted = FriendsActivity(updates = listOf(FriendUpdate(FriendFixtures.bo.id, "Bo")))

    @Test
    fun theAcceptedNoticeOpensTheFriendOnTheFriendsTabAndAcknowledgesThem() {
        show(choose = false) { activity = accepted }
        assertFalse(exists(PeekTab.Friends.screen))
        assertEquals(listOf(text("friends.invitationAccepted", mapOf("name" to "Bo"))), words(FRIENDS_NOTICE_OPEN))
        assertEquals(Role.Button, semantics(FRIENDS_NOTICE_OPEN)[SemanticsProperties.Role])
        assertEquals(text("friends.dismissUpdate"), label(FRIENDS_NOTICE_DISMISS))
        press(FRIENDS_NOTICE_OPEN)
        assertTrue(exists(PeekTab.Friends.screen))
        assertFalse(exists(FRIENDS_NOTICE))
        assertEquals(listOf("Bo", "Ada", "Cy", "Di"), cards())
        assertEquals(listOf(FriendFixtures.bo.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
        assertNull(source.friendsActivity.focusId)
    }

    @Test
    fun theAcceptedNoticeDismissedIsAcknowledgedAndStaysAway() {
        show(choose = false) { activity = accepted }
        press(FRIENDS_NOTICE_DISMISS)
        assertFalse(exists(FRIENDS_NOTICE))
        assertFalse(exists(PeekTab.Friends.screen))
        assertEquals(listOf(FriendFixtures.bo.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
        // The activity read again as the app comes back to the front still holds Bo: dismissed, the notice stays away.
        compose.runOnUiThread { owner.registry.currentState = Lifecycle.State.CREATED }
        settle()
        compose.runOnUiThread { owner.registry.currentState = Lifecycle.State.RESUMED }
        settle()
        assertFalse(exists(FRIENDS_NOTICE))
    }

    @Test
    fun underASheetTheAcceptedNoticeIsOutOfTalkBacksReach() {
        show { activity = accepted }
        val notice = text("friends.invitationAccepted", mapOf("name" to "Bo"))
        assertTrue(shows(notice))
        tap(FRIENDS_SHARING)
        assertFalse(shows(notice))
        press(FRIENDS_CLOSE)
        assertTrue(shows(notice))
    }

    @Test
    fun theDemoShowsNoNotice() {
        show(AuthenticationState.Demo, choose = false) { activity = accepted }
        assertFalse(exists(FRIENDS_NOTICE))
    }
}
