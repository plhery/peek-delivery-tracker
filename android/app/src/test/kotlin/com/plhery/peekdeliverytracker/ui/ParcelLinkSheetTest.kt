package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.Parcel
import com.plhery.peekdeliverytracker.core.ParcelLinkClaim
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkStopped
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.INLINE_TOAST
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_ADD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_ALREADY
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_FAILED
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_FOLLOW
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_GIFT_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_GIFT_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_LOADING
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_NOTHING
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_NOTHING_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_OPEN_EXISTING
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_REFRESH
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SCROLL
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SIGN_IN
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SURPRISE
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_TRY_AGAIN
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.sheet.FullScreenCover
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import java.io.IOException
import java.time.Duration
import java.time.Instant
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A parcel's link in its sheet (B-S41-B-S50, A-S05), over the root's state as the app shows it, on the sheet's stores
 * in memory ([FakeParcelLinks]): each phase; each reader's keeping block and what its action asks of the parcel store;
 * the owner key's hand-over, once a presentation; Close and the back held while keeping; a second link in place of the
 * first; refresh, its ticker and its failure; the gifts; the link resumed after sign-in; the list taking what the sheet
 * closed on; the sheet over another sheet and over a cover; TalkBack. On synthetic links.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelLinkSheetTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private lateinit var links: FakeParcelLinks
    private val inbox = DeliveriesInbox()
    private val views = mutableListOf<String>()
    private var now: Instant = CAPTURED

    private val viewerId = LinkFixtures.viewer().link.id
    private val giftId = LinkFixtures.wrappedGift().link.id

    private fun text(key: String, values: Map<String, String> = emptyMap()) = localizer.text(key, values)

    /** The root signed in as `state`, its link sheet on `links`, the list and its parcels the demo's. */
    private fun show(
        links: FakeParcelLinks,
        state: AuthenticationState = links.session.value,
        deliveries: (@Composable () -> Unit)? = null,
    ) {
        this.links = links
        links.session.value = state
        compose.setContent {
            RootHost(
                state = state, links = links, inbox = inbox, onView = { views += it }, now = { now }, deliveries = deliveries,
            )
        }
        compose.waitForIdle()
    }

    private fun open(id: String = viewerId, key: String? = null) {
        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = id, key = key)) }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** Whether TalkBack reaches the node tagged `tag`. */
    private fun reachable(tag: String): Boolean = compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty()

    private fun shows(text: String): Boolean = compose.onAllNodesWithText(text, substring = true, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** One heading reads `text`. */
    private fun assertHeading(text: String) =
        assertEquals(text, 1, compose.onAllNodes(isHeading() and hasText(text), useUnmergedTree = true).fetchSemanticsNodes().size)

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    // Phases

    @Test
    fun aLinkShowsASpinnerWhileItIsReadThenItsParcel() {
        val answer = CompletableDeferred<PublicParcelResponse?>()
        show(FakeParcelLinks().apply { read = { _, _ -> answer.await() } })
        open()
        assertTrue(exists(LINK_SHEET))
        assertTrue(exists(LINK_LOADING))
        assertEquals(listOf(viewerId to null), links.reads)

        answer.complete(LinkFixtures.viewer())
        settle()
        assertFalse(exists(LINK_LOADING))
        assertTrue(exists(LINK_CARD))
        // Its title, the status as a heading, and its number as the link hides it.
        assertTrue(shows(text("link.shared")))
        assertHeading(localizer.parcelStatus(Parcel(shared = LinkFixtures.viewer().parcel)))
        assertTrue(shows("••• 3456"))
        assertEquals("One read a presentation", 1, links.reads.size)
    }

    @Test
    fun aLinkGoneOrStoppedShowsNothingWithCloseAndAHeading() {
        show(FakeParcelLinks(answer = null))
        open()
        assertTrue(exists(LINK_NOTHING))
        assertHeading(text("link.gone.title"))
        assertTrue(shows(text("link.gone.body")))
        compose.onNodeWithTag(LINK_NOTHING_CLOSE).performClick()
        settle()
        assertNull(links.links.route)
        assertFalse(exists(LINK_SHEET))

        links.read = { _, _ -> throw ParcelLinkStopped() }
        open()
        assertHeading(text("share.stopped.title"))
        assertTrue(shows(text("share.stopped.body")))
        // A stopped link keeps the sheet to itself: no title.
        assertFalse(shows(text("link.shared")))
    }

    @Test
    fun aReadThatFailsSaysWhyAndTriesAgain() {
        show(FakeParcelLinks().apply { read = { _, _ -> throw IOException("offline") } })
        open()
        assertTrue(exists(LINK_FAILED))
        assertTrue(shows(text("error.connection")))

        links.read = { _, _ -> throw IllegalStateException("broken") }
        compose.onNodeWithTag(LINK_TRY_AGAIN).performClick()
        settle()
        assertEquals(2, links.reads.size)
        assertTrue(shows(text("native.error.serviceFailed")))

        links.read = { _, _ -> LinkFixtures.viewer() }
        compose.onNodeWithTag(LINK_TRY_AGAIN).performClick()
        settle()
        assertTrue(exists(LINK_CARD))
    }

    // Signed in

    @Test
    fun someoneSignedInKeepsTheParcelAndTheListShowsItAdded() {
        show(FakeParcelLinks(answer = LinkFixtures.keepable()), FakeParcelLinks.READER)
        open()
        assertTrue(shows(text("link.addHint")))
        assertFalse(exists(LINK_FOLLOW))
        compose.onNodeWithTag(LINK_ADD).performClick()
        settle()
        assertEquals(listOf(ParcelLinkRoute(id = viewerId)), links.kept)
        // Added: the sheet closes, and the list says so.
        assertNull(links.links.route)
        assertFalse(exists(LINK_SHEET))
        assertTrue(exists(INLINE_TOAST))
        assertTrue(shows(text("link.added")))
        assertNull(inbox.linkAdded.value)
    }

    @Test
    fun aFullAccountOrAFailedKeepSaysSoAndKeepsTheSheet() {
        show(FakeParcelLinks(answer = LinkFixtures.keepable()).apply { keeping = { ParcelLinkClaim.Full } }, FakeParcelLinks.READER)
        open()
        compose.onNodeWithTag(LINK_ADD).performClick()
        settle()
        assertTrue(exists(LINK_SHEET))
        assertTrue(shows(text("linkapp.full")))

        links.keeping = { throw IOException("The keep failed") }
        compose.onNodeWithTag(LINK_ADD).performClick()
        settle()
        assertTrue(shows("The keep failed"))
        assertEquals(2, links.kept.size)
    }

    @Test
    fun aParcelTheAccountFollowsAlreadyOpensOnTheList() {
        val kind = DemoCards.parcel("Kind of Blue").id
        show(FakeParcelLinks(answer = LinkFixtures.keepable()).apply { keeping = { ParcelLinkClaim.Already(kind) } }, FakeParcelLinks.READER)
        open()
        compose.onNodeWithTag(LINK_ADD).performClick()
        settle()
        assertTrue(exists(LINK_ALREADY))
        assertTrue(shows(text("link.already.title")))
        compose.onNodeWithTag(LINK_OPEN_EXISTING).performClick()
        settle()
        assertNull(links.links.route)
        assertEquals(0, links.deviceParcelsShown)
        assertTrue(links.actions.contains("parcel-link-open-existing"))
        assertEquals("parcel", views.last())
    }

    @Test
    fun aLinkThatCannotBeKeptHasNoKeepingBlockForSomeoneSignedIn() {
        show(FakeParcelLinks(answer = LinkFixtures.viewer()), FakeParcelLinks.READER)
        open()
        assertTrue(exists(LINK_CARD))
        assertFalse(exists(LINK_ADD))
        assertFalse(exists(LINK_FOLLOW))
        assertFalse(exists(LINK_SIGN_IN))
    }

    // Signed out

    @Test
    fun someoneSignedOutFollowsTheParcelOnThisPhoneOrSignsInToKeepIt() {
        show(FakeParcelLinks(answer = LinkFixtures.keepable()))
        open()
        assertTrue(shows(text("native.follow.hint")))
        assertTrue(exists(LINK_SIGN_IN))
        assertFalse(exists(LINK_ADD))
        compose.onNodeWithTag(LINK_FOLLOW).performClick()
        settle()
        assertEquals(listOf(ParcelLinkRoute(id = viewerId)), links.followed)
        assertTrue(links.actions.contains("parcel-link-alerts"))
        assertNull(links.links.route)
        assertTrue(shows(text("link.added")))
    }

    @Test
    fun aParcelThisPhoneFollowsAlreadyOpensAmongItsOwn() {
        val kind = DemoCards.parcel("Kind of Blue").id
        show(FakeParcelLinks(answer = LinkFixtures.keepable()).apply { here = kind })
        open()
        assertFalse(exists(LINK_FOLLOW))
        compose.onNodeWithTag(LINK_OPEN_EXISTING).performClick()
        settle()
        assertEquals(1, links.deviceParcelsShown)
        assertNull(links.links.route)
        assertEquals("parcel", views.last())
    }

    @Test
    fun withoutAServerSomeoneSignedOutCanOnlySignInAndWithoutSignInNothingAtAll() {
        show(FakeParcelLinks(answer = LinkFixtures.keepable(), tracksWithoutAccount = false))
        open()
        assertFalse(exists(LINK_FOLLOW))
        compose.onNodeWithTag(LINK_SIGN_IN).performClick()
        settle()
        assertEquals(1, links.signIns)
        assertTrue(links.actions.contains("parcel-link-sign-in"))
        assertNull("The sheet closes for the sign-in", links.links.route)
        assertNotNull(links.links.remembered)
    }

    @Test
    fun withNeitherServerNorSignInThereIsNoKeepingBlock() {
        show(FakeParcelLinks(answer = LinkFixtures.keepable(), tracksWithoutAccount = false, authenticationConfigured = false))
        open()
        assertTrue(exists(LINK_CARD))
        assertFalse(exists(LINK_FOLLOW))
        assertFalse(exists(LINK_SIGN_IN))
    }

    // The owner key

    @Test
    fun aHandedOverLinkIsKeptByItselfOnceAPresentation() {
        show(FakeParcelLinks(answer = LinkFixtures.owner()).apply { keeping = { ParcelLinkClaim.Full } }, FakeParcelLinks.READER)
        open(key = LinkFixtures.KEY)
        assertEquals(listOf(viewerId to LinkFixtures.KEY), links.reads)
        assertEquals(listOf(ParcelLinkRoute(id = viewerId, key = LinkFixtures.KEY)), links.kept)
        // A handed-over parcel is the person's own: no title.
        assertFalse(shows(text("link.shared")))

        compose.onNodeWithTag(LINK_REFRESH).performClick()
        settle()
        assertEquals(2, links.reads.size)
        assertEquals("Not again in the same presentation", 1, links.kept.size)

        open(key = LinkFixtures.KEY)
        assertEquals("Again in a new one", 2, links.kept.size)
    }

    @Test
    fun aHandedOverLinkIsFollowedByItselfOnThisPhoneOnce() {
        show(FakeParcelLinks(answer = LinkFixtures.owner()).apply { following = { _, _ -> ParcelLinkClaim.Already(null) } })
        open(key = LinkFixtures.KEY)
        assertEquals(listOf(ParcelLinkRoute(id = viewerId, key = LinkFixtures.KEY)), links.followed)
        assertTrue(exists(LINK_SHEET))
        compose.onNodeWithTag(LINK_REFRESH).performClick()
        settle()
        assertEquals(1, links.followed.size)
    }

    @Test
    fun aKeyThatIsNotTheOwnersIsNeitherKeptNorFollowedByItself() {
        show(FakeParcelLinks(answer = LinkFixtures.keepable()), FakeParcelLinks.READER)
        open(key = LinkFixtures.KEY)
        assertTrue(exists(LINK_ADD))
        assertEquals(emptyList<ParcelLinkRoute>(), links.kept)
        assertNull("The store drops a key that is no key", links.links.route?.key)
    }

    // Close and the back while keeping

    @Test
    fun whileKeepingCloseIsOffAndTheBackKeepsTheSheet() {
        val claim = CompletableDeferred<ParcelLinkClaim>()
        show(FakeParcelLinks(answer = LinkFixtures.keepable()).apply { keeping = { claim.await() } }, FakeParcelLinks.READER)
        open()
        compose.onNodeWithTag(LINK_CLOSE).assertIsEnabled()
        compose.onNodeWithTag(LINK_ADD).performClick()
        settle()
        compose.onNodeWithTag(LINK_CLOSE).assertIsNotEnabled()
        compose.onNodeWithTag(LINK_ADD).assertIsNotEnabled()
        back()
        assertNotNull(links.links.route)
        assertTrue(exists(LINK_SHEET))

        claim.complete(ParcelLinkClaim.Full)
        settle()
        compose.onNodeWithTag(LINK_CLOSE).assertIsEnabled()
        back()
        assertNull(links.links.route)
        assertFalse(exists(LINK_SHEET))
    }

    // A second link

    @Test
    fun aSecondLinkOpensInTheSheetInPlaceOfTheFirst() {
        show(FakeParcelLinks().apply { read = { id, _ -> if (id == giftId) LinkFixtures.wrappedGift() else LinkFixtures.viewer() } })
        open()
        assertTrue(exists(LINK_CARD))
        open(giftId)
        assertEquals(listOf(viewerId to null, giftId to null), links.reads)
        assertTrue(exists(LINK_GIFT_CARD))
        assertFalse(exists(LINK_CARD))
        assertEquals(1, compose.onAllNodesWithTag(LINK_SHEET).fetchSemanticsNodes().size)
    }

    // Refresh

    @Test
    fun refreshReadsTheLinkAgainAndAFailureStaysUnderTheCard() {
        show(FakeParcelLinks())
        open()
        compose.onNodeWithTag(LINK_REFRESH).performClick()
        settle()
        assertEquals(2, links.reads.size)
        assertFalse(exists(LINK_NOTE))

        links.read = { _, _ -> throw IOException("offline") }
        compose.onNodeWithTag(LINK_SCROLL).performTouchInput { swipeDown() }
        settle()
        assertEquals("A pull reads it again", 3, links.reads.size)
        assertTrue(exists(LINK_CARD))
        assertTrue(exists(LINK_NOTE))
        assertTrue(shows(text("error.connection")))
    }

    @Test
    fun theRefreshButtonSaysWhenTheLinkWasUpdatedAndKeepsItCurrent() {
        show(FakeParcelLinks())
        open()
        val synced = LinkFixtures.viewer().parcel.lastSyncedAt!!
        fun updated() = text("parcel.updated", mapOf("date" to localizer.relativeTime(from = synced, now = now)))
        val first = updated()
        compose.onNodeWithTag(LINK_REFRESH).assert(SemanticsMatcher.expectValue(SemanticsProperties.StateDescription, first))
        assertEquals(listOf(text("detail.checkNow")), compose.onNodeWithTag(LINK_REFRESH).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription))

        now = CAPTURED.plus(Duration.ofMinutes(10))
        compose.mainClock.advanceTimeBy(61_000)
        compose.waitForIdle()
        val later = updated()
        assertFalse(first == later)
        compose.onNodeWithTag(LINK_REFRESH).assert(SemanticsMatcher.expectValue(SemanticsProperties.StateDescription, later))
    }

    // Gifts

    @Test
    fun aGiftOnItsWayIsWrappedWithItsSurpriseAndNoNumberNorKeeping() {
        show(FakeParcelLinks(answer = LinkFixtures.wrappedGift()))
        open(giftId)
        assertTrue(exists(LINK_GIFT_CARD))
        assertTrue(exists(LINK_SURPRISE))
        assertHeading(text("share.gift.headline"))
        assertFalse(shows(text("link.shared")))
        assertFalse(shows("6789"))
        assertFalse(exists(LINK_FOLLOW))
        assertFalse(exists(LINK_SIGN_IN))
        assertFalse(exists(LINK_GIFT_NOTE))
        assertFalse("The words wait for the delivery", shows("Happy birthday"))
    }

    @Test
    fun aGiftDeliveredOpensWithItsNoteAndMayBeFollowed() {
        show(FakeParcelLinks(answer = LinkFixtures.openedGift()))
        open(giftId)
        assertHeading(text("share.gift.here"))
        assertFalse(exists(LINK_SURPRISE))
        // The note reads as one.
        compose.onNodeWithTag(LINK_GIFT_NOTE).assert(hasText("Happy birthday, Alex!", substring = true))
        compose.onNodeWithTag(LINK_GIFT_NOTE).assert(hasText("— Sam", substring = true))
        compose.onNodeWithTag(LINK_GIFT_NOTE).assert(hasText("trail running shoes", substring = true))
        assertTrue(exists(LINK_FOLLOW))
        assertTrue(shows("TESTPARCEL123789"))
    }

    // Sign-in

    @Test
    fun aLinkKeptForSignInOpensAgainOnceSomeoneIsSignedInWithoutItsKey() {
        show(FakeParcelLinks(tracksWithoutAccount = false).apply { read = { _, key -> if (key == null) LinkFixtures.keepable() else LinkFixtures.owner() } })
        open(key = LinkFixtures.KEY)
        assertEquals(emptyList<ParcelLinkRoute>(), links.followed)
        compose.onNodeWithTag(LINK_SIGN_IN).performClick()
        settle()
        assertEquals(1, links.signIns)
        assertFalse(exists(LINK_SHEET))
        assertFalse("The key is never written down", links.defaults.dump().contains(LinkFixtures.KEY))

        compose.runOnUiThread { links.session.value = FakeParcelLinks.READER }
        settle()
        assertEquals(ParcelLinkRoute(id = viewerId), links.links.route)
        assertEquals(viewerId to null, links.reads.last())
        assertTrue(exists(LINK_ADD))
        assertEquals(emptyList<ParcelLinkRoute>(), links.kept)
    }

    // Over what is up

    @Test
    fun theSheetComesOverAnotherSheetAndTakesTheTouchesAndTalkBack() {
        show(FakeParcelLinks()) {
            Sheet(visible = true, onDismiss = {}, paneTitle = "Under") { Box(Modifier.fillMaxSize().testTag(UNDER)) }
        }
        assertTrue(reachable(UNDER))
        open()
        assertFalse(reachable(UNDER))
        compose.onNodeWithTag(LINK_CLOSE).performClick()
        settle()
        assertNull(links.links.route)
        assertTrue(reachable(UNDER))
    }

    @Test
    fun theSheetComesOverACover() {
        show(FakeParcelLinks()) {
            FullScreenCover(visible = true, onDismiss = {}, paneTitle = "Under") { Box(Modifier.fillMaxSize().testTag(UNDER)) }
        }
        assertTrue(reachable(UNDER))
        open()
        assertFalse(reachable(UNDER))
        assertTrue(exists(LINK_CARD))
        compose.onNodeWithTag(LINK_CLOSE).performClick()
        settle()
        assertNull(links.links.route)
        assertTrue(reachable(UNDER))
    }

    @Test
    fun theSheetComesOverTheLaunchScreenAndTheArrivalToo() {
        show(FakeParcelLinks(), AuthenticationState.SignedOut)
        open()
        assertTrue(exists(LINK_CARD))
        assertFalse(reachable(ARRIVAL))
    }

    private companion object {
        const val UNDER = "under"
    }
}
