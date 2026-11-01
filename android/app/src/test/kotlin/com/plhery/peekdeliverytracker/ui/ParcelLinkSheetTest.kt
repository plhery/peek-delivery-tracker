package com.plhery.peekdeliverytracker.ui

import android.content.ClipboardManager
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.isPopup
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.Parcel
import com.plhery.peekdeliverytracker.core.ParcelLinkClaim
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkStopped
import com.plhery.peekdeliverytracker.core.TrackingNumberCopy
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ImportCharges
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackAnswer
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestAsked
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.masked
import com.plhery.peekdeliverytracker.ui.deliveries.ACTION_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.ErrorAlert
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ADD_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_BACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_BUBBLE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_NOT_QUITE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_QUESTION
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_REASON
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WORD
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WORDS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.FakeParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.GlassOverlayLayer
import com.plhery.peekdeliverytracker.ui.deliveries.INLINE_TOAST
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_ADD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_ALREADY
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_ANNOUNCEMENT
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
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_LINK
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_COPY_NUMBER
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_NUMBER
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.sheet.FullScreenCover
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import java.io.IOException
import java.time.Duration
import java.time.Instant
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitCancellation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A parcel's link in its sheet (B-S41-B-S50, A-S05), over the root's state as the app shows it, on the sheet's stores
 * in memory ([FakeParcelLinks]): each phase; each reader's keeping block and what its action asks of the parcel store;
 * the owner key's hand-over, once a presentation; Close and the back held while keeping; a second link in place of the
 * first; refresh, its ticker and its failure; the gifts; the link resumed after sign-in; the list taking what the sheet
 * closed on; the sheet over another sheet and over a cover; TalkBack; the number, hidden or whole, its copy and its
 * selection, the import charges and the quiet note (B-S46, B-S47, B-S76, B-H03, B-G04); the question under the history,
 * on the sheet and on the way back from the carrier's page, and its note's sheet over the link's. On synthetic links.
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
    fun closingTheSheetStopsItsRead() {
        var stopped = false
        show(
            FakeParcelLinks().apply {
                read = { _, _ ->
                    try {
                        awaitCancellation()
                    } finally {
                        stopped = true
                    }
                }
            },
        )
        open()
        assertTrue(exists(LINK_LOADING))
        assertFalse(stopped)
        compose.onNodeWithTag(LINK_CLOSE).performClick()
        settle()
        assertTrue("Close stops the read, as `.task` stops with its view", stopped)
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

    @Test
    fun aLinkThatComesWhileTheSheetGoesLetsTheListTakeWhatItClosedOnFirst() {
        show(
            FakeParcelLinks().apply { read = { id, _ -> if (id == giftId) LinkFixtures.wrappedGift() else LinkFixtures.keepable() } },
            FakeParcelLinks.READER,
        )
        open()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(LINK_ADD).performClick()
        compose.mainClock.advanceTimeByFrame()
        assertNull("Added: the sheet goes", links.links.route)
        // Another link before it has gone brings it back up, as a new presentation.
        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = giftId)) }
        repeat(3) { compose.mainClock.advanceTimeByFrame() }
        assertNull("The list took the parcel added", links.links.arrival)
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(exists(LINK_GIFT_CARD))
        assertTrue(exists(INLINE_TOAST))
        assertTrue(shows(text("link.added")))

        compose.onNodeWithTag(LINK_CLOSE).performClick()
        settle()
        assertNull(links.links.arrival)
        assertNull(inbox.linkAdded.value)
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

    // The number, the charges and the quiet note

    /** A parcel's link, every one of its scans moved `days` earlier. */
    private fun PublicParcelResponse.earlier(days: Long) = copy(
        parcel = parcel.copy(
            trackingEvents = parcel.trackingEvents.map {
                it.copy(occurredAt = DateParser.isoString(checkNotNull(DateParser.date(it.occurredAt)).minus(Duration.ofDays(days))))
            },
        ),
    )

    private fun clipboard(): String? =
        compose.activity.getSystemService(ClipboardManager::class.java).primaryClip?.getItemAt(0)?.text?.toString()

    private fun rootView(): View = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view

    @Test
    fun aHiddenNumberIsReadAsOneMaskedWithTheCarriersSearchPageAndNoCopy() {
        show(FakeParcelLinks())
        open()
        val masked = checkNotNull(LinkFixtures.viewer().parcel.numberHint).masked
        compose.onNode(hasText(text("detail.trackingNumber")) and hasText(masked)).performScrollTo()
        assertFalse(exists(PAGE_NUMBER))
        assertFalse(exists(PAGE_COPY_NUMBER))
        compose.onNodeWithTag(PAGE_CARRIER_LINK).performScrollTo().performClick()
        assertEquals(listOf("parcel-carrier-link"), links.actions.filter { it == "parcel-carrier-link" })
    }

    @Test
    fun aWholeNumberIsWrittenAsItsCarrierWritesItWithItsCopyAndItsCarriersPage() {
        show(FakeParcelLinks(LinkFixtures.keepable()))
        open()
        compose.onNodeWithTag(PAGE_NUMBER, useUnmergedTree = true).performScrollTo()
        val numbers = compose.onAllNodesWithTag(PAGE_NUMBER, useUnmergedTree = true).fetchSemanticsNodes()
            .flatMap { it.config.getOrNull(SemanticsProperties.Text).orEmpty().map { text -> text.text } }
        assertEquals(listOf(CarrierCatalog.format("TESTPARCEL983456", CarrierId.Dpd)), numbers)
        compose.onNodeWithTag(PAGE_COPY_NUMBER).assertContentDescriptionEquals(text("detail.copyTracking"))
        compose.onAllNodesWithTag(PAGE_CARRIER_LINK)[0].performScrollTo().performClick()
        assertEquals(listOf("parcel-carrier-link"), links.actions.filter { it == "parcel-carrier-link" })
    }

    @Test
    fun aLongPressSelectsAWholeNumber() {
        show(FakeParcelLinks(LinkFixtures.keepable()))
        open()
        compose.onNodeWithTag(PAGE_NUMBER, useUnmergedTree = true).performScrollTo()
        assertEquals(0, compose.onAllNodes(isPopup()).fetchSemanticsNodes().size)
        compose.onNodeWithTag(PAGE_NUMBER, useUnmergedTree = true).performTouchInput { longClick() }
        compose.waitForIdle()
        // The selection's two handles.
        assertEquals(2, compose.onAllNodes(isPopup()).fetchSemanticsNodes().size)
    }

    @Test
    fun theCopyPutsTheNumberOnTheClipboardWithTheSuccessTapAndSaysCopiedForTwoSeconds() {
        show(FakeParcelLinks(LinkFixtures.keepable()))
        open()
        val copy = compose.onNodeWithTag(PAGE_COPY_NUMBER).performScrollTo()
        compose.mainClock.autoAdvance = false
        copy.performClick()
        compose.mainClock.advanceTimeBy(100)
        assertEquals(TrackingNumberCopy.text("TESTPARCEL983456", CarrierId.Dpd), clipboard())
        assertEquals(hapticFeedbackConstant(SensoryFeedback.Success), shadowOf(rootView()).lastHapticFeedbackPerformed())
        assertEquals(listOf("parcel-copy-tracking"), links.actions.filter { it == "parcel-copy-tracking" })
        copy.assertContentDescriptionEquals(text("detail.copied"))
        compose.mainClock.advanceTimeBy(1_800)
        copy.assertContentDescriptionEquals(text("detail.copied"))
        compose.mainClock.advanceTimeBy(200)
        copy.assertContentDescriptionEquals(text("detail.copyTracking"))
        compose.mainClock.autoAdvance = true
    }

    @Test
    fun importChargesAreReadAsOneAndAHiddenNumberIsAskedForToPayThemOnline() {
        val charges = ImportCharges(currency = "CHF", customs = 12.0, carrierFee = 18.0, carrierFeeOnline = 9.0)
        val shown = "n5Tp4zKf3VwY"
        fun charged(response: PublicParcelResponse) =
            response.copy(parcel = response.parcel.copy(carrierData = response.parcel.carrierData.copy(importCharges = charges)))
        val links = FakeParcelLinks().apply {
            read = { id, _ ->
                if (id == shown) charged(LinkFixtures.keepable()).let { it.copy(link = it.link.copy(id = shown)) } else charged(LinkFixtures.viewer())
            }
        }
        show(links)
        open()
        val title = text("detail.importCharges")
        val ask = text("detail.importChargesAskNumber")
        compose.onNode(hasText(title) and hasText(ask)).performScrollTo()

        // A number the link shows is there to pay with.
        open(shown)
        compose.onNode(hasText(title)).performScrollTo()
        assertFalse(shows(ask))
    }

    @Test
    fun aParcelThatStoppedMovingIsToldWhyUnlessItIsAWrappedGiftOrItsCarrierIsUnknown() {
        val unknown = "u6Wq5aLg4XzZ"
        val links = FakeParcelLinks().apply {
            read = { id, _ ->
                when (id) {
                    // Its scan in transit, from before it was handed out for delivery.
                    giftId -> LinkFixtures.wrappedGift().let { it.copy(parcel = it.parcel.copy(trackingEvents = it.parcel.trackingEvents.drop(1))) }.earlier(5)
                    unknown -> LinkFixtures.viewer().let { it.copy(link = it.link.copy(id = unknown), parcel = it.parcel.copy(carrier = CarrierId.Unknown)) }.earlier(5)
                    else -> LinkFixtures.viewer().earlier(5)
                }
            }
        }
        val quiet = text("link.quiet", mapOf("carrier" to "")).substringBefore(".")
        show(links)
        open()
        assertTrue(shows(quiet))
        open(giftId)
        check(exists(LINK_SURPRISE))
        assertFalse(shows(quiet))
        open(unknown)
        check(exists(LINK_CARD))
        assertFalse(shows(quiet))
    }

    // The question under the history

    /** The outcomes counted under `action`. */
    private fun counted(action: String): List<DeliveryAnalytics.Outcome?> =
        links.actions.indices.filter { links.actions[it] == action }.map { links.outcomes[it] }

    /** What TalkBack is told in the sheet now. */
    private fun announcement(): String? = compose.onAllNodesWithTag(LINK_ANNOUNCEMENT, useUnmergedTree = true).fetchSemanticsNodes()
        .singleOrNull()?.config?.getOrNull(SemanticsProperties.ContentDescription)?.singleOrNull()

    /** A tap on the part tagged `tag`, scrolled into view. */
    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        compose.waitForIdle()
    }

    /** Whether the part tagged `tag`, or one in it, says `text`. */
    private fun says(tag: String, text: String): Boolean =
        compose.onAllNodes(hasText(text, substring = true) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag)))).fetchSemanticsNodes().isNotEmpty()

    @Test
    fun aParcelsLinkAsksUnderItsHistoryAndYesIsThankedSentForTheLinkAndCounted() {
        show(FakeParcelLinks())
        open()
        compose.onNodeWithTag(FEEDBACK_QUESTION).performScrollTo()
        settle()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(FEEDBACK_YES).performClick()
        compose.mainClock.advanceTimeBy(200)
        assertEquals("Good to know, thanks!", announcement())
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(says(FEEDBACK_BUBBLE, "Good to know, thanks!"))
        val (id, request) = links.feedbackRequests.single()
        assertEquals(viewerId, id)
        assertEquals(ParcelFeedbackAnswer.Right, request.answer)
        assertEquals(ParcelFeedbackRequestAsked.Page, request.asked)
        assertEquals(listOf(DeliveryAnalytics.Outcome.Success), counted("parcel-feedback-right"))
    }

    @Test
    fun aGiftAsItsRecipientSeesItAsksNothing() {
        val opened = "o7Xr6bMh5YaQ"
        show(FakeParcelLinks().apply { read = { id, _ -> if (id == opened) LinkFixtures.openedGift() else LinkFixtures.wrappedGift() } })
        open(giftId)
        check(exists(LINK_GIFT_CARD))
        assertFalse(exists(FEEDBACK_QUESTION) || exists(FEEDBACK_ROW))
        open(opened)
        check(exists(LINK_GIFT_NOTE))
        assertFalse(exists(FEEDBACK_QUESTION) || exists(FEEDBACK_ROW))
    }

    @Test
    fun whatABuildWithoutAServerShowsIsMadeUpAndAsksNothing() {
        show(FakeParcelLinks(tracksWithoutAccount = false))
        open()
        check(exists(LINK_CARD))
        assertFalse(exists(FEEDBACK_QUESTION) || exists(FEEDBACK_ROW))
    }

    @Test
    fun backFromTheCarriersPageTheQuestionAsksAtTheSheetsFootAndYesIsSentAsAskedThere() {
        show(FakeParcelLinks())
        open()
        tap(PAGE_CARRIER_LINK)
        assertEquals(listOf("parcel-carrier-link"), links.actions.filter { it == "parcel-carrier-link" })
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        links.now = links.now.plusSeconds(2)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        compose.waitForIdle()
        assertTrue(exists(FEEDBACK_WORD))
        compose.onNodeWithTag(FEEDBACK_BACK_YES).performClick()
        settle()
        val request = links.feedbackRequests.single().second
        assertEquals(ParcelFeedbackAnswer.Right, request.answer)
        assertEquals(ParcelFeedbackRequestAsked.Back, request.asked)
    }

    @Test
    fun aNotesSheetComesOverTheLinksWhichTalkBackLeavesAndTheBackClosesItFirst() {
        show(FakeParcelLinks())
        open()
        tap(FEEDBACK_NOT_QUITE)
        settle()
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        settle()
        tap(FEEDBACK_ADD_NOTE)
        settle()
        assertTrue(reachable(FEEDBACK_WORDS_SHEET))
        assertFalse(reachable(LINK_CARD))
        assertFalse(reachable(LINK_CLOSE))
        back()
        assertFalse(exists(FEEDBACK_WORDS_SHEET))
        assertTrue(reachable(LINK_CARD))
        assertNotNull(links.links.route)
    }

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
    fun anAlertTheListShowsAfterTheSheetComesOverItAndTheBackClosesItFirst() {
        var failed by mutableStateOf(false)
        show(FakeParcelLinks()) {
            val host = LocalTabOverlay.current!!
            GlassOverlayLayer(host, "failure", if (failed) { { ErrorAlert("The refresh failed") { failed = false } } } else null)
        }
        open()
        compose.runOnUiThread { failed = true }
        settle()
        // On top, it alone reaches TalkBack.
        assertTrue(reachable(ACTION_ERROR))
        assertFalse(reachable(LINK_CARD))
        assertTrue(exists(LINK_CARD))

        back()
        assertFalse(failed)
        assertFalse(exists(ACTION_ERROR))
        assertNotNull("The back closed the alert, not the sheet", links.links.route)
        assertTrue(reachable(LINK_CARD))
        back()
        assertNull(links.links.route)
    }

    @Test
    fun anAlertShownBeforeTheSheetStaysUnderIt() {
        var failed by mutableStateOf(true)
        show(FakeParcelLinks()) {
            val host = LocalTabOverlay.current!!
            GlassOverlayLayer(host, "failure", if (failed) { { ErrorAlert("The refresh failed") { failed = false } } } else null)
        }
        assertTrue(reachable(ACTION_ERROR))
        open()
        assertFalse(reachable(ACTION_ERROR))
        assertTrue(reachable(LINK_CARD))
        back()
        assertNull("The back closed the sheet on top", links.links.route)
        assertTrue(failed)
        assertTrue(reachable(ACTION_ERROR))
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
