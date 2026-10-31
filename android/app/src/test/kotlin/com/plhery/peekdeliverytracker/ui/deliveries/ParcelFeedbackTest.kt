package com.plhery.peekdeliverytracker.ui.deliveries

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.unit.Density
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackAnswer
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestAsked
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentEvent
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import java.io.IOException
import java.time.Duration
import java.util.UUID
import kotlin.math.abs
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The question under a parcel's page (B-S58–B-S65, B-ST33, B-ST34, B-AN22–B-AN30, B-H08): where it asks nothing; Pip's
 * question with each moment's words and controls, and the row asked where no carrier was found; the pills; the words
 * and carrier sheets with their limits, sends, failures and busy state; the question asked on the way back from the
 * carrier's site with its answers and timings; how it comes in, turns and hops, under Reduce Motion and not; the window
 * made again; and what TalkBack hears.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelFeedbackTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    /** What TalkBack was told, in order, through the window's deliveries, made again or not. */
    private val said = mutableListOf<String>()
    private var heard: DeliveriesState? = null
    private var hearing: Job? = null

    /** The account the tests sign in with; the demo asks nothing. */
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 9), email = "reader@example.com"))

    /** A parcel its carrier answers for, with its history: Pip asks about it. */
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    /** A parcel no carrier was found for: the row asks about it. */
    private val lamp: Parcel get() = DemoCards.probable()

    private fun show(
        parcels: List<Parcel> = listOf(gift, lamp),
        session: AuthenticationState = reader,
        fontScale: Float = 1f,
        reduceMotion: Boolean = false,
        recreation: Recreation? = null,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        source = FakeDeliveries(parcels, session).apply(prepare)
        compose.waitsForMaps()
        val content: @Composable () -> Unit = {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides ::hear) {
                RootHost(state = session, source = source, reduceMotion = reduceMotion)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun hear(state: DeliveriesState) {
        deliveries = state
        if (heard === state) return
        heard = state
        hearing?.cancel()
        hearing = source.scope.launch {
            snapshotFlow { state.announcement?.text }.collect { text -> if (!text.isNullOrEmpty()) said += text }
        }
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** The page of `parcel`. */
    private fun open(parcel: Parcel) {
        compose.runOnUiThread { deliveries.navigator.open(parcel.id) }
        settle()
    }

    /** The page of `parcel`, scrolled to its question. */
    private fun openQuestion(parcel: Parcel = gift) {
        open(parcel)
        node(if (exists(FEEDBACK_QUESTION)) FEEDBACK_QUESTION else FEEDBACK_ROW).performScrollTo()
        settle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    /** Whether the part tagged `tag`, or something in it, shows `text`. */
    private fun shows(tag: String, text: String): Boolean =
        compose.onAllNodes(hasText(text, substring = true) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag)))).fetchSemanticsNodes().isNotEmpty()

    /** A tap on the part tagged `tag`, scrolled into view where it scrolls. */
    private fun tap(tag: String) {
        if (compose.onAllNodes(hasTestTag(tag) and hasAnyAncestor(hasScrollAction())).fetchSemanticsNodes().isNotEmpty()) {
            node(tag).performScrollTo()
        }
        node(tag).performClick()
        compose.waitForIdle()
    }

    /** Whether the word at the page's foot says `text`. */
    private fun says(text: String): Boolean = compose.onAllNodes(
        (hasText(text, substring = true) or hasContentDescription(text, substring = true)) and
            (hasTestTag(FEEDBACK_WORD) or hasAnyAncestor(hasTestTag(FEEDBACK_WORD))),
    ).fetchSemanticsNodes().isNotEmpty()

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    /** What TalkBack was last told. */
    private fun announced(): String? = said.lastOrNull()

    private fun editable(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun spins(tag: String): Boolean = compose.onAllNodes(
        hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag))),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    private fun work(parcel: Parcel = gift): ParcelFeedbackWork = source.feedbacks.of(parcel.id)!!

    /** The outcomes counted under `action`. */
    private fun counted(action: String): List<DeliveryAnalytics.Outcome?> =
        source.actions.indices.filter { source.actions[it] == action }.map { source.outcomes[it] }

    /** The window leaves the screen for `seconds` (the browser over it), and comes back. */
    private fun awayAndBack(seconds: Long = 2) {
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        source.now = source.now.plusSeconds(seconds)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        compose.waitForIdle()
    }

    /** The carrier's own site, opened from the page's link, and back after `seconds`. */
    private fun visitCarrier(seconds: Long = 2) {
        node(PAGE_CARRIER_LINK).performScrollTo().performClick()
        compose.waitForIdle()
        awayAndBack(seconds)
    }

    private fun lastHaptic(): Int = shadowOf((compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view).lastHapticFeedbackPerformed()

    // Where it asks nothing.

    @Test
    fun nothingIsAskedOfAnArchivedParcelOneWaitingForItsFirstAnswerOrOneWhoseHistoryThePageDoesNotShow() {
        val manual = CarrierCatalog.shared.definitions.keys.first { it != CarrierId.Unknown && !CarrierCatalog.shared.tracksAutomatically(it) }
        val archived = gift.copy(id = UUID(1, 1), archivedAt = "2026-10-28T10:00:00Z")
        val pending = lamp.copy(id = UUID(1, 2), syncStatus = SyncStatus.Pending)
        val syncing = lamp.copy(id = UUID(1, 3), syncStatus = SyncStatus.Syncing)
        val unsupported = lamp.copy(id = UUID(1, 4), carrier = manual, carrierData = null)
        show(listOf(archived, pending, syncing, unsupported, gift))
        for (parcel in listOf(archived, pending, syncing, unsupported)) {
            open(parcel)
            assertTrue(exists(PAGE_CONTENT))
            assertFalse("${parcel.id} asks nothing", exists(FEEDBACK_QUESTION))
            assertFalse("${parcel.id} asks nothing", exists(FEEDBACK_ROW))
            back()
        }
        // The same page with its carrier's history asks.
        open(gift)
        assertTrue(exists(FEEDBACK_QUESTION))
    }

    @Test
    fun theDemosMadeUpParcelsAskNothing() {
        show(DemoCards.parcels(), session = AuthenticationState.Demo)
        open(gift)
        assertTrue(exists(PAGE_CONTENT))
        assertFalse(exists(FEEDBACK_QUESTION))
    }

    @Test
    fun theSampleAsksNothing() {
        show(emptyList())
        compose.runOnUiThread { deliveries.openSample() }
        settle()
        assertTrue(exists(PAGE_CONTENT))
        assertFalse(exists(FEEDBACK_QUESTION))
        assertFalse(exists(FEEDBACK_ROW))
    }

    // Pip's question.

    @Test
    fun pipAsksWhetherThePageIsRightWithYesAndNotQuite() {
        show()
        openQuestion()
        assertTrue(exists(FEEDBACK_PIP))
        assertTrue(shows(FEEDBACK_BUBBLE, "Did I get this one right?"))
        assertTrue(shows(FEEDBACK_YES, "Yes"))
        assertTrue(shows(FEEDBACK_NOT_QUITE, "Not quite"))
        assertFalse(exists(FEEDBACK_ROW))
        // Too wide together for a phone's bubble, the pills go under the question, 6 apart, each 44 tall to the touch.
        val bubble = node(FEEDBACK_BUBBLE).getUnclippedBoundsInRoot()
        val yes = node(FEEDBACK_YES).getUnclippedBoundsInRoot()
        val notQuite = node(FEEDBACK_NOT_QUITE).getUnclippedBoundsInRoot()
        assertEquals(yes.top.value, notQuite.top.value, 0.5f)
        assertEquals(bubble.left.value + 14f, yes.left.value, 0.5f)
        assertEquals(6f, (notQuite.left - yes.right).value, 0.5f)
        assertEquals(44f, (yes.bottom - yes.top).value, 0.5f)
        assertEquals("8 under the question's line, 8 over the bubble's foot within the touch's 4", bubble.bottom.value - 8f + 4f, yes.bottom.value, 0.5f)
    }

    @Test
    fun yesIsThankedInPlaceSentAndCounted() {
        show()
        openQuestion()
        tap(FEEDBACK_YES)
        settle()
        assertTrue(shows(FEEDBACK_BUBBLE, "Good to know, thanks!"))
        assertFalse(exists(FEEDBACK_YES))
        val (id, request) = source.feedbackRequests.single()
        assertEquals(gift.id, id)
        assertEquals(ParcelFeedbackAnswer.Right, request.answer)
        assertEquals(ParcelFeedbackRequestAsked.Page, request.asked)
        assertEquals(listOf(DeliveryAnalytics.Outcome.Success), counted("parcel-feedback-right"))
        assertEquals("Good to know, thanks!", announced())
        assertNull(work().word)
    }

    @Test
    fun notQuiteOffersTheReasonsAndOneGivenIsThankedWithTheWayToANote() {
        show()
        openQuestion()
        tap(FEEDBACK_NOT_QUITE)
        settle()
        assertTrue(shows(FEEDBACK_BUBBLE, "What’s off?"))
        val reasons = listOf("It has arrived", "Wrong status", "Missing steps", "Wrong time or place", "Wrong carrier", "Something else")
        ParcelFeedbackReason.entries.zip(reasons).forEach { (reason, words) -> assertTrue(words, shows(FEEDBACK_REASON + reason.name, words)) }
        assertTrue(source.feedbackRequests.isEmpty())
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        settle()
        assertTrue(shows(FEEDBACK_BUBBLE, "Thanks! I’ll look into it."))
        assertTrue(shows(FEEDBACK_ADD_NOTE, "Add a note"))
        assertEquals("Thanks! I’ll look into it.", announced())
        val given = source.feedbackRequests.single().second
        assertEquals(ParcelFeedbackAnswer.Wrong, given.answer)
        assertEquals(listOf(ParcelFeedbackReason.Steps), given.reasons)
        assertEquals(listOf(DeliveryAnalytics.Outcome.Success), counted("parcel-feedback-wrong"))
        // The note goes with the same answer, its reason chosen in the sheet, and isn't counted again.
        tap(FEEDBACK_ADD_NOTE)
        settle()
        assertTrue(exists(FEEDBACK_WORDS_SHEET))
        assertEquals(true, node(FEEDBACK_CHIP + ParcelFeedbackReason.Steps.name).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        assertEquals(false, node(FEEDBACK_CHIP + ParcelFeedbackReason.Status.name).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        assertTrue(shows(FEEDBACK_PROMISE, "what Chronopost answered"))
        node(FEEDBACK_NOTE).performTextReplacement("Two scans are missing")
        tap(FEEDBACK_SEND)
        settle()
        assertFalse(exists(FEEDBACK_WORDS_SHEET))
        assertTrue(shows(FEEDBACK_BUBBLE, "Thanks! I’ll look into it."))
        assertFalse(exists(FEEDBACK_ADD_NOTE))
        val noted = source.feedbackRequests.last().second
        assertEquals(given.id, noted.id)
        assertEquals("Two scans are missing", noted.note)
        assertEquals(listOf(DeliveryAnalytics.Outcome.Success), counted("parcel-feedback-wrong"))
    }

    @Test
    fun anAnswerThatCouldNotGoIsTakenBackAndSaidAtThePagesFootForFourSeconds() {
        show { feedbackFailure = IOException("offline") }
        openQuestion()
        compose.mainClock.autoAdvance = false
        node(FEEDBACK_YES).performClick()
        compose.mainClock.advanceTimeBy(100)
        assertEquals(ParcelFeedbackModel.Word.Failed, work().word)
        assertTrue(says("Couldn’t send that. Try again."))
        assertEquals("Couldn’t send that. Try again.", announced())
        assertEquals(ParcelFeedbackModel.Moment.Ask, work().moment)
        assertEquals(listOf(DeliveryAnalytics.Outcome.Error), counted("parcel-feedback-right"))
        compose.mainClock.advanceTimeBy(3_700)
        assertEquals(ParcelFeedbackModel.Word.Failed, work().word)
        compose.mainClock.advanceTimeBy(400)
        assertNull(work().word)
        compose.mainClock.advanceTimeBy(1_000)
        assertFalse(exists(FEEDBACK_WORD))
        assertTrue(shows(FEEDBACK_BUBBLE, "Did I get this one right?"))
    }

    // The row where no carrier was found.

    @Test
    fun theRowAsksWhetherTheCarriersSiteShowsItAndNotYetSaysPeekKeepsAsking() {
        show()
        openQuestion(lamp)
        assertTrue(exists(FEEDBACK_ROW))
        assertFalse(exists(FEEDBACK_PIP))
        assertTrue(shows(FEEDBACK_ROW, "Does the carrier’s own site show it?"))
        assertTrue(shows(FEEDBACK_YES, "Yes"))
        assertTrue(shows(FEEDBACK_NOT_YET, "Not yet"))
        tap(FEEDBACK_NOT_YET)
        settle()
        assertTrue(shows(FEEDBACK_ROW, "Then it’s early days. Peek keeps asking."))
        assertFalse(exists(FEEDBACK_YES))
        assertEquals("Then it’s early days. Peek keeps asking.", announced())
        assertTrue(source.feedbackRequests.isEmpty())
    }

    @Test
    fun yesOnTheRowAsksWhoCarriesItWithinItsLimitsAndTheirPagesSendSendsIt() {
        show()
        openQuestion(lamp)
        tap(FEEDBACK_YES)
        settle()
        assertTrue(exists(FEEDBACK_CARRIER_SHEET))
        assertTrue(shows(FEEDBACK_CARRIER_SHEET, "Who’s carrying it?"))
        assertTrue(shows(FEEDBACK_PROMISE, "Sent with this parcel’s number. Nothing about you."))
        assertTrue(deliveries.modal)
        node(FEEDBACK_SEND).assertIsNotEnabled()
        // Nothing but space is nothing to send, and Send on the page's keyboard sends nothing either.
        node(FEEDBACK_WHO).performTextReplacement("   ")
        node(FEEDBACK_SEND).assertIsNotEnabled()
        node(FEEDBACK_WHERE).performImeAction()
        compose.waitForIdle()
        assertTrue(source.feedbackRequests.isEmpty())
        node(FEEDBACK_WHO).performTextReplacement("Q".repeat(130))
        assertEquals(120, editable(FEEDBACK_WHO)?.length)
        node(FEEDBACK_WHERE).performTextReplacement("https://example.com/" + "t".repeat(600))
        assertEquals(500, editable(FEEDBACK_WHERE)?.length)
        node(FEEDBACK_WHO).performTextReplacement("Example Freight")
        node(FEEDBACK_WHERE).performTextReplacement("example.com/track")
        node(FEEDBACK_SEND).assertIsEnabled()
        node(FEEDBACK_WHERE).performImeAction()
        settle()
        assertFalse(exists(FEEDBACK_CARRIER_SHEET))
        val (id, request) = source.feedbackRequests.single()
        assertEquals(lamp.id, id)
        assertEquals(ParcelFeedbackAnswer.FoundElsewhere, request.answer)
        assertEquals("Example Freight", request.carrierName)
        assertEquals("example.com/track", request.trackingPage)
        assertEquals(listOf(DeliveryAnalytics.Outcome.Success), counted("parcel-feedback-carrier"))
        assertTrue(shows(FEEDBACK_ROW, "Thanks, that helps Peek learn this carrier."))
        assertFalse(deliveries.modal)
    }

    @Test
    fun theCarrierSheetsNextGoesToItsPageAndWritingOpensItFully() {
        show()
        openQuestion(lamp)
        tap(FEEDBACK_YES)
        settle()
        assertFalse(work(lamp).large)
        node(FEEDBACK_WHO).performClick()
        compose.waitForIdle()
        node(FEEDBACK_WHO).assertIsFocused()
        assertTrue(work(lamp).large)
        node(FEEDBACK_WHO).performImeAction()
        compose.waitForIdle()
        node(FEEDBACK_WHERE).assertIsFocused()
        node(FEEDBACK_WHO).assertContentDescriptionEquals("Carrier or shop")
        node(FEEDBACK_WHERE).assertContentDescriptionEquals("Its tracking page, if you have the link")
    }

    // The words sheet.

    @Test
    fun theWordsSheetSendsNothingUntilAReasonOrANoteAndTheChipsTapAsTheyAreChosen() {
        show()
        openQuestion()
        tap(FEEDBACK_NOT_QUITE)
        settle()
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Other.name)
        settle()
        tap(FEEDBACK_ADD_NOTE)
        settle()
        val chip = FEEDBACK_CHIP + ParcelFeedbackReason.Other.name
        tap(chip)
        assertEquals(hapticFeedbackConstant(SensoryFeedback.Selection), lastHaptic())
        assertEquals(false, node(chip).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        node(FEEDBACK_SEND).assertIsNotEnabled()
        node(FEEDBACK_NOTE).performTextReplacement("  ")
        node(FEEDBACK_SEND).assertIsNotEnabled()
        node(FEEDBACK_NOTE).performTextReplacement("The van came twice")
        node(FEEDBACK_SEND).assertIsEnabled()
        node(FEEDBACK_NOTE).performTextReplacement("")
        tap(chip)
        assertEquals(true, node(chip).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        node(FEEDBACK_SEND).assertIsEnabled()
    }

    @Test
    fun theNoteKeepsWithinItsLimitAndSaysWhatItHasLeftOnceTwoHundredOrFewer() {
        show()
        openQuestion()
        tap(FEEDBACK_NOT_QUITE)
        settle()
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Status.name)
        settle()
        tap(FEEDBACK_ADD_NOTE)
        settle()
        node(FEEDBACK_NOTE).performTextReplacement("n".repeat(3_799))
        assertFalse(exists(FEEDBACK_NOTE_LEFT))
        node(FEEDBACK_NOTE).performTextReplacement("n".repeat(3_800))
        assertTrue(shows(FEEDBACK_NOTE_LEFT, "200 characters left"))
        node(FEEDBACK_NOTE).performTextReplacement("n".repeat(4_100))
        assertEquals(4_000, editable(FEEDBACK_NOTE)?.length)
        assertTrue(shows(FEEDBACK_NOTE_LEFT, "0 characters left"))
    }

    @Test
    fun aSendThatFailsKeepsTheSheetWithWhatWasTypedAndSaysSo() {
        show()
        openQuestion()
        tap(FEEDBACK_NOT_QUITE)
        settle()
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Arrived.name)
        settle()
        tap(FEEDBACK_ADD_NOTE)
        settle()
        node(FEEDBACK_NOTE).performTextReplacement("It came yesterday")
        source.feedbackFailure = IOException("offline")
        tap(FEEDBACK_SEND)
        settle()
        assertTrue(exists(FEEDBACK_WORDS_SHEET))
        assertTrue(shows(FEEDBACK_FAILURE, "Couldn’t send that. Try again."))
        assertEquals("Couldn’t send that. Try again.", announced())
        assertEquals("It came yesterday", editable(FEEDBACK_NOTE))
        assertEquals(true, node(FEEDBACK_CHIP + ParcelFeedbackReason.Arrived.name).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        // Said again for another failure.
        tap(FEEDBACK_SEND)
        settle()
        assertEquals(2, said.count { it == "Couldn’t send that. Try again." })
        source.feedbackFailure = null
        tap(FEEDBACK_SEND)
        settle()
        assertFalse(exists(FEEDBACK_WORDS_SHEET))
        assertEquals("It came yesterday", source.feedbackRequests.last().second.note)
    }

    @Test
    fun whileItsWordsGoTheSheetSpinsAndNothingClosesItOrChangesItsChoice() {
        show { feedbackSending = CompletableDeferred() }
        openQuestion()
        tap(FEEDBACK_NOT_QUITE)
        settle()
        source.feedbackSending?.complete(Unit)
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Carrier.name)
        settle()
        tap(FEEDBACK_ADD_NOTE)
        settle()
        source.feedbackSending = CompletableDeferred()
        tap(FEEDBACK_SEND)
        assertTrue(spins(FEEDBACK_SEND))
        assertTrue(shows(FEEDBACK_SEND, "Sending…"))
        node(FEEDBACK_SEND).assertIsNotEnabled()
        node(FEEDBACK_CLOSE).assertIsNotEnabled()
        node(FEEDBACK_CHIP + ParcelFeedbackReason.Carrier.name).assertIsNotEnabled()
        back()
        assertTrue(exists(FEEDBACK_WORDS_SHEET))
        source.feedbackSending?.complete(Unit)
        settle()
        assertFalse(exists(FEEDBACK_WORDS_SHEET))
    }

    @Test
    fun theSheetsHeadingAndCloseAreReadAndCloseLeavesTheAnswerAsItWas() {
        show()
        openQuestion()
        tap(FEEDBACK_NOT_QUITE)
        settle()
        tap(FEEDBACK_REASON + ParcelFeedbackReason.TimePlace.name)
        settle()
        tap(FEEDBACK_ADD_NOTE)
        settle()
        assertTrue(
            compose.onAllNodes(hasText("What’s off?") and androidx.compose.ui.test.isHeading() and hasAnyAncestor(hasTestTag(FEEDBACK_WORDS_SHEET)))
                .fetchSemanticsNodes().isNotEmpty(),
        )
        node(FEEDBACK_CLOSE).assertContentDescriptionEquals("Close")
        tap(FEEDBACK_CLOSE)
        settle()
        assertFalse(exists(FEEDBACK_WORDS_SHEET))
        assertFalse(deliveries.modal)
        assertTrue(shows(FEEDBACK_ADD_NOTE, "Add a note"))
        assertEquals(1, source.feedbackRequests.size)
    }

    // On the way back from the carrier's site.

    @Test
    fun backFromTheCarriersSitePipAsksAtThePagesFootForTwelveSeconds() {
        show()
        open(gift)
        node(PAGE_CARRIER_LINK).performScrollTo().performClick()
        compose.waitForIdle()
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        source.now = source.now.plusSeconds(2)
        compose.mainClock.autoAdvance = false
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        compose.mainClock.advanceTimeBy(100)
        assertEquals(ParcelFeedbackModel.Word.Back("Chronopost"), work().word)
        assertTrue(says("Does Chronopost say the same?"))
        assertTrue(shows(FEEDBACK_BACK_YES, "Yes"))
        assertTrue(shows(FEEDBACK_BACK_NO, "No"))
        assertEquals("Does Chronopost say the same?", announced())
        compose.mainClock.advanceTimeBy(11_700)
        assertNotNull(work().word)
        compose.mainClock.advanceTimeBy(400)
        assertNull(work().word)
        compose.mainClock.advanceTimeBy(1_000)
        assertFalse(exists(FEEDBACK_WORD))
    }

    @Test
    fun yesOnTheWayBackIsThankedAtThePagesFootForFourSecondsAndInPlace() {
        show()
        open(gift)
        visitCarrier()
        compose.mainClock.autoAdvance = false
        node(FEEDBACK_BACK_YES).performClick()
        compose.mainClock.advanceTimeBy(100)
        assertEquals(ParcelFeedbackModel.Word.BackRight, work().word)
        assertTrue(says("Thanks, good to know."))
        assertEquals("Thanks, good to know.", announced())
        assertEquals(ParcelFeedbackModel.Moment.Right, work().moment)
        val request = source.feedbackRequests.single().second
        assertEquals(ParcelFeedbackAnswer.Right, request.answer)
        assertEquals(ParcelFeedbackRequestAsked.Back, request.asked)
        compose.mainClock.advanceTimeBy(3_700)
        assertEquals(ParcelFeedbackModel.Word.BackRight, work().word)
        compose.mainClock.advanceTimeBy(400)
        assertNull(work().word)
    }

    @Test
    fun noOnTheWayBackAsksWhatIsOffAndItsWordsAreThankedAtThePagesFoot() {
        show()
        open(gift)
        visitCarrier()
        tap(FEEDBACK_BACK_NO)
        settle()
        assertNull(work().word)
        assertTrue(exists(FEEDBACK_WORDS_SHEET))
        ParcelFeedbackReason.entries.forEach {
            assertEquals(false, node(FEEDBACK_CHIP + it.name).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        }
        node(FEEDBACK_SEND).assertIsNotEnabled()
        tap(FEEDBACK_CHIP + ParcelFeedbackReason.Status.name)
        tap(FEEDBACK_SEND)
        compose.mainClock.advanceTimeBy(100)
        assertEquals(ParcelFeedbackModel.Word.BackSent, work().word)
        assertTrue(says("Thanks, noted. It helps Peek get this right."))
        settle()
        assertFalse(exists(FEEDBACK_WORDS_SHEET))
        assertEquals(ParcelFeedbackModel.Moment.Noted, work().moment)
        val request = source.feedbackRequests.single().second
        assertEquals(ParcelFeedbackRequestAsked.Back, request.asked)
        assertEquals(listOf(ParcelFeedbackReason.Status), request.reasons)
        assertEquals(listOf(DeliveryAnalytics.Outcome.Success), counted("parcel-feedback-wrong"))
    }

    @Test
    fun theWayBackAsksNothingAfterAMomentAwayWithoutAVisitOrTwiceForTheSameScan() {
        show()
        open(gift)
        // No visit to the carrier's site.
        awayAndBack()
        assertNull(work().word)
        // Back in less than a second and a half.
        visitCarrier(seconds = 1)
        assertNull(work().word)
        visitCarrier()
        assertNotNull(work().word)
        tap(FEEDBACK_BACK_NO)
        settle()
        tap(FEEDBACK_CLOSE)
        settle()
        // Asked once about this scan, answered or not.
        visitCarrier()
        assertNull(work().word)
    }

    @Test
    fun theWayBackWaitsWhileSomethingElseIsOverThePage() {
        show()
        open(gift)
        node(PAGE_CARRIER_LINK).performScrollTo().performClick()
        compose.waitForIdle()
        node(PAGE_SHARE).performScrollTo().performClick()
        settle()
        assertTrue(deliveries.modal)
        awayAndBack()
        assertNull(work().word)
    }

    // The rating a "Yes" may ask for.

    /**
     * The gift out for delivery: nothing about it is wrong whatever the day (`Parcel.untroubled`), so that a "Yes" on it
     * may be the moment to ask for a rating. At customs, it needs its reader's attention.
     */
    private val calm: Parcel get() {
        val latest = gift.currentEvent!!
        return gift.copy(trackingEvents = gift.trackingEvents.map { if (it.id == latest.id) it.copy(stage = TrackingStage.OutForDelivery) else it })
    }

    /** The rating prompt has counted two earlier days with use, as it needs before it asks. */
    private fun usedTwoEarlierDays() {
        source.reviewPrompt.used(source.now.minus(Duration.ofDays(2)))
        source.reviewPrompt.used(source.now.minus(Duration.ofDays(1)))
    }

    @Test
    fun aYesOnACalmParcelAsksForARatingOnceTheMomentHasSettled() {
        show(listOf(calm, lamp))
        usedTwoEarlierDays()
        openQuestion(calm)
        compose.mainClock.autoAdvance = false
        node(FEEDBACK_YES).performClick()
        compose.mainClock.advanceTimeBy(100)
        assertTrue(work().reviewDue)
        // Play's review info is fetched as the moment settles, two seconds, then the prompt shows and the ask counts.
        assertEquals(listOf("prepare"), source.reviewCalls)
        compose.mainClock.advanceTimeBy(1_800)
        assertEquals(listOf("prepare"), source.reviewCalls)
        compose.mainClock.advanceTimeBy(200)
        assertEquals(listOf("prepare", "launch"), source.reviewCalls)
        assertEquals(1, source.actions.count { it == "review-ask" })
        assertFalse(work().reviewDue)
        assertFalse(source.reviewPrompt.isDue(source.now, calm = true))
    }

    @Test
    fun aYesOnTheWayBackAsksForARatingOnceItsThanksHasGone() {
        show(listOf(calm, lamp))
        usedTwoEarlierDays()
        open(calm)
        visitCarrier()
        compose.mainClock.autoAdvance = false
        node(FEEDBACK_BACK_YES).performClick()
        compose.mainClock.advanceTimeBy(3_800)
        assertEquals(ParcelFeedbackModel.Word.BackRight, work().word)
        assertTrue(work().reviewDue)
        assertEquals(emptyList<String>(), source.reviewCalls)
        compose.mainClock.advanceTimeBy(400)
        assertNull(work().word)
        assertEquals(listOf("prepare"), source.reviewCalls)
        compose.mainClock.advanceTimeBy(2_100)
        assertEquals(listOf("prepare", "launch"), source.reviewCalls)
    }

    @Test
    fun theRatingWaitsWhileSomethingIsOverThePage() {
        show(listOf(calm, lamp))
        usedTwoEarlierDays()
        openQuestion(calm)
        tap(FEEDBACK_YES)
        node(PAGE_SHARE).performScrollTo().performClick()
        settle()
        assertTrue(deliveries.modal)
        compose.mainClock.advanceTimeBy(3_000)
        compose.waitForIdle()
        assertFalse("Prepared, never launched: ${source.reviewCalls}", "launch" in source.reviewCalls)
        assertTrue(work().reviewDue)
        back()
        assertFalse(deliveries.modal)
        compose.mainClock.advanceTimeBy(2_100)
        compose.waitForIdle()
        assertEquals("launch", source.reviewCalls.last())
        assertEquals(1, source.actions.count { it == "review-ask" })
    }

    @Test
    fun aYesThatCouldNotGoAsksForNoRating() {
        show(listOf(calm, lamp)) { feedbackFailure = IOException("offline") }
        usedTwoEarlierDays()
        openQuestion(calm)
        tap(FEEDBACK_YES)
        compose.mainClock.advanceTimeBy(3_000)
        compose.waitForIdle()
        assertFalse(work().reviewDue)
        assertEquals(emptyList<String>(), source.reviewCalls)
    }

    // Motion.

    /** The pixels of the window in `area`, as drawn now. */
    private fun pixels(area: Rect): IntArray {
        val root = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        val whole = Bitmap.createBitmap(root.width, root.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { root.draw(Canvas(whole)) }
        val pixels = IntArray(area.width.toInt() * area.height.toInt())
        whole.getPixels(pixels, 0, area.width.toInt(), area.left.toInt(), area.top.toInt(), area.width.toInt(), area.height.toInt())
        return pixels
    }

    /**
     * Where `tag` is drawn in the window, in points, with its layers' scale and offset and whether in view or not:
     * the test's unclipped bounds leave out graphics layers, and the semantics bounds clip to the window.
     */
    private fun drawn(tag: String): Rect {
        val coordinates = node(tag).fetchSemanticsNode().layoutInfo.coordinates
        val box = coordinates.findRootCoordinates().localBoundingBoxOf(coordinates, clipBounds = false)
        val density = compose.density.density
        return Rect(box.left / density, box.top / density, box.right / density, box.bottom / density)
    }

    /** Pip's width as drawn, in or out of view. */
    private fun pipWidth(): Float = drawn(FEEDBACK_PIP).width

    private fun bubbleWidth(): Float = drawn(FEEDBACK_BUBBLE).width

    /** Pip's centre over the question's foot, which his own stands on whatever the bubble's height. */
    private fun pipCentre(): Float = drawn(FEEDBACK_PIP).center.y - drawn(FEEDBACK_QUESTION).bottom

    /** Scrolls the page by `points`, as TalkBack does. */
    private fun scrollPage(points: Float) {
        node(PAGE_CONTENT).performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, points * compose.density.density) }
        compose.waitForIdle()
    }

    @Test
    fun theQuestionComesInOnceAFifthOfItShowsInThePagesScroll() {
        show()
        open(gift)
        val window = compose.onRoot().fetchSemanticsNode().boundsInRoot.bottom / compose.density.density
        val question = node(FEEDBACK_QUESTION).getUnclippedBoundsInRoot()
        val height = (question.bottom - question.top).value
        assertTrue("The question $question starts under the window's foot $window", question.top.value > window)
        assertEquals(45f, pipWidth(), 0.5f)
        // 15 % of it in view: not yet.
        scrollPage(question.top.value - window + height * 0.15f)
        settle()
        assertEquals(45f, pipWidth(), 0.5f)
        // 25 %: it comes in, Pip at his size.
        scrollPage(height * 0.1f)
        settle()
        assertEquals(50f, pipWidth(), 0.5f)
    }

    @Test
    fun underReduceMotionTheQuestionStandsAtOnce() {
        show(reduceMotion = true)
        open(gift)
        assertEquals(50f, pipWidth(), 0.5f)
    }

    @Test
    fun pipComesInOnHisSpringAndTheBubbleGrowsAfterHim() {
        show()
        open(gift)
        compose.mainClock.autoAdvance = false
        node(PAGE_CONTENT).performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, 10_000f) }
        val frames = (1..120).map {
            compose.mainClock.advanceTimeByFrame()
            pipWidth() to bubbleWidth()
        }
        val start = frames.indexOfFirst { it.first > 45.2f }
        assertTrue("Pip comes in", start >= 0)
        // Pip springs past his size (dampingFraction 0.58) while the bubble waits its 0.15 s at 84 %.
        assertTrue("Pip ${frames.maxOf { it.first }} overshoots 50", frames.maxOf { it.first } > 50.3f)
        val waiting = frames.subList(start, start + 7).map { it.second }
        assertTrue("The bubble waits: $waiting", waiting.max() - waiting.min() < 0.3f)
        assertEquals(50f, frames.last().first, 0.05f)
        assertEquals(waiting.first() / 0.84f, frames.last().second, 0.5f)
    }

    /** Pip's centre over the time of his hop after a "Yes", from where he stood. */
    private fun hop(): List<Float> {
        compose.mainClock.autoAdvance = false
        val rest = pipCentre()
        node(FEEDBACK_YES).performClick()
        return (1..60).map {
            compose.mainClock.advanceTimeBy(16)
            pipCentre() - rest
        }
    }

    @Test
    fun pipHopsAsAnAnswerIsTaken() {
        show()
        openQuestion()
        val hop = hop()
        assertEquals("$hop", -9f, hop.min(), 0.6f)
        // Up, down, a small bounce, and still.
        val peak = hop.indexOf(hop.min())
        assertTrue("Peak at frame $peak", peak in 11..15)
        assertEquals(0f, hop.last(), 0.05f)
        assertTrue("The bounce ${hop.subList(peak + 10, 45)}", hop.subList(peak + 10, 45).min() < -1.5f)
    }

    @Test
    fun underReduceMotionPipDoesNotHop() {
        show(reduceMotion = true)
        openQuestion()
        val hop = hop()
        assertTrue("Still: $hop", hop.all { abs(it) < 0.05f })
    }

    /**
     * The pixels of the old words in the bubble's top 40 points, `after` milliseconds after a "Yes": the ink's, as
     * dark as it shows, and not the green of the thanks that replaces them.
     */
    private fun oldWords(reduceMotion: Boolean, vararg after: Long): List<Int> {
        show(reduceMotion = reduceMotion)
        openQuestion()
        val density = compose.density.density
        val bubble = node(FEEDBACK_BUBBLE).fetchSemanticsNode().boundsInRoot
        val area = Rect(bubble.left + 3 * density, bubble.top + 3 * density, bubble.right - 3 * density, bubble.top + 40 * density)
        fun inked(): Int = pixels(area).count {
            val red = android.graphics.Color.red(it)
            val green = android.graphics.Color.green(it)
            val drop = 255 - (0.299 * red + 0.587 * green + 0.114 * android.graphics.Color.blue(it))
            drop > 25 && green - red < 0.12 * drop
        }
        val before = inked()
        compose.mainClock.autoAdvance = false
        node(FEEDBACK_YES).performClick()
        var at = 0L
        return listOf(before) + after.map {
            compose.mainClock.advanceTimeBy(it - at)
            at = it
            inked()
        }
    }

    @Test
    fun whatAnAnswerReplacesIsGoneInEightyMilliseconds() {
        val (before, early, late) = oldWords(reduceMotion = false, 48, 112)
        assertTrue("The words are drawn before: $before", before > 200)
        assertTrue("Still fading at 48 ms: $early", early > 20)
        assertEquals("Gone after 80 ms", 0, late)
    }

    @Test
    fun underReduceMotionWhatAnAnswerReplacesFadesOverAFifthOfASecond() {
        val (before, half, gone) = oldWords(reduceMotion = true, 112, 400)
        assertTrue("The words are drawn before: $before", before > 200)
        assertTrue("Fading at 112 ms: $half", half > 20)
        assertEquals("Gone after 0.2 s", 0, gone)
    }

    // The window made again.

    @Test
    fun theMomentAndTheArrivalLastThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openQuestion()
        tap(FEEDBACK_NOT_QUITE)
        settle()
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        settle()
        val before = said.toList()
        recreation.recreate()
        assertEquals(50f, pipWidth(), 0.5f)
        settle()
        assertTrue(shows(FEEDBACK_BUBBLE, "Thanks! I’ll look into it."))
        assertTrue(exists(FEEDBACK_ADD_NOTE))
        // Nothing is said again.
        assertEquals(before, said)
    }

    @Test
    fun anOpenSheetWithItsWordsAndTheKeyboardLastsThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openQuestion()
        tap(FEEDBACK_NOT_QUITE)
        settle()
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        settle()
        tap(FEEDBACK_ADD_NOTE)
        settle()
        tap(FEEDBACK_CHIP + ParcelFeedbackReason.Status.name)
        node(FEEDBACK_NOTE).performClick()
        node(FEEDBACK_NOTE).performTextReplacement("A scan from Tuesday")
        settle()
        recreation.recreate()
        settle()
        assertTrue(exists(FEEDBACK_WORDS_SHEET))
        assertEquals("A scan from Tuesday", editable(FEEDBACK_NOTE))
        node(FEEDBACK_NOTE).assertIsFocused()
        assertEquals(true, node(FEEDBACK_CHIP + ParcelFeedbackReason.Status.name).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected))
        assertTrue(work().large)
    }

    @Test
    fun aSendUnderWayLastsThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openQuestion(lamp)
        tap(FEEDBACK_YES)
        settle()
        node(FEEDBACK_WHO).performTextReplacement("Example Freight")
        source.feedbackSending = CompletableDeferred()
        tap(FEEDBACK_SEND)
        recreation.recreate()
        settle()
        assertTrue(exists(FEEDBACK_CARRIER_SHEET))
        assertTrue(spins(FEEDBACK_SEND))
        source.feedbackSending?.complete(Unit)
        settle()
        assertFalse(exists(FEEDBACK_CARRIER_SHEET))
        assertTrue(shows(FEEDBACK_ROW, "Thanks, that helps Peek learn this carrier."))
        assertEquals("Example Freight", source.feedbackRequests.single().second.carrierName)
    }

    @Test
    fun aSheetOnItsWayDownAsTheWindowIsMadeAgainLeavesThePageUncovered() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        open(gift)
        visitCarrier()
        tap(FEEDBACK_BACK_NO)
        settle()
        assertTrue(exists(FEEDBACK_WORDS_SHEET))
        compose.mainClock.autoAdvance = false
        node(FEEDBACK_CLOSE).performClick()
        compose.mainClock.advanceTimeBy(64)
        assertNull(work().words)
        assertTrue("Still on its way down", exists(FEEDBACK_WORDS_SHEET))
        assertTrue(deliveries.modal)
        recreation.recreate()
        settle()
        assertFalse(exists(FEEDBACK_WORDS_SHEET))
        assertFalse(deliveries.modal)
        // A new scan, and the way back from the carrier's site asks about it.
        val latest = gift.currentEvent!!
        val scanned = gift.copy(trackingEvents = gift.trackingEvents + latest.copy(id = UUID(2, 1), occurredAt = "2026-10-30T09:00:00Z"))
        source.parcels.value = listOf(scanned, lamp)
        settle()
        visitCarrier()
        assertEquals(ParcelFeedbackModel.Word.Back("Chronopost"), work().word)
    }

    @Test
    fun aPageOpenedAgainAsksAfresh() {
        show()
        openQuestion()
        tap(FEEDBACK_NOT_QUITE)
        settle()
        val first = work()
        back()
        assertNull(source.feedbacks.of(gift.id))
        openQuestion()
        assertNotEquals(first, work())
        assertTrue(shows(FEEDBACK_BUBBLE, "Did I get this one right?"))
    }

    // Large text.

    @Test
    fun atTheAccessibilitySizesTheWordsTakePipsRoomAndThePillsWrap() {
        show(fontScale = 2f)
        openQuestion()
        assertFalse(exists(FEEDBACK_PIP))
        assertTrue(exists(FEEDBACK_BUBBLE))
        tap(FEEDBACK_NOT_QUITE)
        settle()
        val bubble = node(FEEDBACK_BUBBLE).getUnclippedBoundsInRoot()
        val pills = ParcelFeedbackReason.entries.map { node(FEEDBACK_REASON + it.name).getUnclippedBoundsInRoot() }
        assertTrue("Pills on several lines: $pills", pills.map { it.top.value.toInt() }.distinct().size >= 3)
        pills.forEach {
            assertTrue("$it within the bubble $bubble", it.left >= bubble.left && it.right <= bubble.right)
            assertTrue("$it is 44 tall to the touch at least", (it.bottom - it.top).value >= 43.5f)
        }
    }
}
