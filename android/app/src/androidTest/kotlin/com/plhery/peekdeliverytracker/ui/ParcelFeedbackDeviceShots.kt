package com.plhery.peekdeliverytracker.ui

import android.text.InputType
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackAnswer
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequest
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestAsked
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ANNOUNCEMENT
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ADD_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_BACK_NO
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_BACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_BUBBLE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_CARRIER_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_CHIP
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_FAILURE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_NOTE_LEFT
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_NOT_QUITE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_NOT_YET
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_PIP
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_PROMISE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_QUESTION
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_REASON
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_SEND
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WHERE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WHO
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WORD
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WORDS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_LINK
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelFeedbackWork
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import java.io.IOException
import java.util.UUID
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

/**
 * The question under a parcel's page on this device's own window (B-S58-B-S65, B-ST33, B-ST34, and B-ST38's note):
 * Pip's question, its reasons and its thanks; the row asked where no carrier was found, early and named; the carrier
 * sheet, who then their page on the address keys, Send on the keyboard sending it, its promise, and the sheet open
 * fully while writing; the words sheet's chips, its note and the counter from 200 left; nothing closing it while it
 * sends; the failure line offline, with what TalkBack hears (the polite live region the deliveries announce through);
 * the question asked on the way back from the carrier's site, each answer and how long its word stays; and the largest
 * text, the words taking Pip's room and the pills wrapping. Each moment saved as stateswalk/<row>-<moment>.png, with
 * -dark, and -largest at the largest text, in the test package's external files, which adb pulls.
 * Signed in on fake deliveries ([FakeDeliveries], [ParcelWalk]) whose stores answer every read in memory: nothing goes
 * to the network, and the carrier's site is kept from opening. Each test keeps Zurich's time and the captures' moment as
 * the JVM's do, under Reduce Motion; the largest text is the composition's own Density at font scale 2, Android's
 * largest step, which the app reads as iOS's accessibility2, not the phone's setting. The zone is put back after each
 * test, and the preferences files the fakes write go once its window has.
 */
@RunWith(AndroidJUnit4::class)
class ParcelFeedbackDeviceShots {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /** A parcel its carrier answers for, with its history: Pip asks about it. */
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    /** A parcel no carrier was found for: the row asks about it. */
    private val lamp: Parcel get() = DemoCards.probable()

    private fun show(prepare: FakeDeliveries.() -> Unit = {}) = walk.show(listOf(gift, lamp), prepare = prepare)

    /** The page of `parcel` from its card, scrolled to its question. */
    private fun openQuestion(parcel: Parcel = gift) {
        walk.open(parcel)
        walk.node(if (walk.exists(FEEDBACK_QUESTION)) FEEDBACK_QUESTION else FEEDBACK_ROW).performScrollTo()
        walk.settle()
    }

    private fun work(parcel: Parcel): ParcelFeedbackWork = checkNotNull(walk.source.feedbacks.of(parcel.id)) { "No question on ${parcel.label}" }

    private fun moment(parcel: Parcel = gift): ParcelFeedbackModel.Moment = walk.onUi { work(parcel).moment }

    private fun word(parcel: Parcel = gift): ParcelFeedbackModel.Word? = walk.onUi { work(parcel).word }

    private fun requests(): List<Pair<UUID, ParcelFeedbackRequest>> = walk.onUi { walk.source.feedbackRequests.toList() }

    /** Whether the word at the page's foot says `text`. */
    private fun says(text: String): Boolean = compose.onAllNodes(
        (hasText(text, substring = true) or hasContentDescription(text, substring = true)) and
            (hasTestTag(FEEDBACK_WORD) or hasAnyAncestor(hasTestTag(FEEDBACK_WORD))),
    ).fetchSemanticsNodes().isNotEmpty()

    private fun selected(reason: ParcelFeedbackReason): Boolean? =
        walk.node(FEEDBACK_CHIP + reason.name).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected)

    /** Not quite, then `reason`, then Add a note: the words sheet with `reason` chosen. */
    private fun openWords(reason: ParcelFeedbackReason) {
        openQuestion()
        walk.tap(FEEDBACK_NOT_QUITE)
        walk.settle()
        walk.tap(FEEDBACK_REASON + reason.name)
        walk.settle()
        walk.tap(FEEDBACK_ADD_NOTE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_WORDS_SHEET) }
        walk.awaitStill()
    }

    /** The carrier's own site, opened from the page's link (kept from the browser), and back two seconds later. */
    private fun visitCarrier() {
        walk.tap(PAGE_CARRIER_LINK)
        compose.waitUntil(LONG_MILLIS) { walk.asked().isNotEmpty() }
        walk.awayAndBack()
    }

    /**
     * `info` as a field that asks for no corrections hands it to the keyboard: the text keys of `variation`, `capitals`
     * the only capitals, no corrections and no suggestions, and `action` on its action key.
     */
    private fun assertKeys(info: EditorInfo, action: Int, variation: Int, capitals: Int) {
        val type = info.inputType
        assertEquals(InputType.TYPE_CLASS_TEXT, type and InputType.TYPE_MASK_CLASS)
        assertEquals(variation, type and InputType.TYPE_MASK_VARIATION)
        assertEquals(capitals, type and CAPITALS)
        assertEquals(0, type and InputType.TYPE_TEXT_FLAG_AUTO_CORRECT)
        assertEquals(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS, type and InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
        assertEquals(action, info.imeOptions and EditorInfo.IME_MASK_ACTION)
    }

    /** B-S58, B-ST33: Pip asks, Not quite offers the six reasons, and one given is sent and thanked in place. */
    @Test
    fun pipAsksNotQuiteOffersTheReasonsAndOneGivenIsThanked() {
        show()
        openQuestion()
        assertTrue(walk.exists(FEEDBACK_PIP))
        assertTrue(walk.shows(FEEDBACK_BUBBLE, "Did I get this one right?"))
        assertTrue(walk.shows(FEEDBACK_YES, "Yes"))
        assertTrue(walk.shows(FEEDBACK_NOT_QUITE, "Not quite"))
        assertFalse(walk.exists(FEEDBACK_ROW))
        walk.shot("B-S58-ask")

        walk.tap(FEEDBACK_NOT_QUITE)
        walk.settle()
        assertTrue(walk.shows(FEEDBACK_BUBBLE, "What’s off?"))
        ParcelFeedbackReason.entries.zip(REASONS).forEach { (reason, words) -> assertTrue(words, walk.shows(FEEDBACK_REASON + reason.name, words)) }
        assertTrue(requests().isEmpty())
        walk.shot("B-ST33-reasons")

        walk.tap(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        walk.settle()
        assertTrue(walk.shows(FEEDBACK_BUBBLE, "Thanks! I’ll look into it."))
        assertTrue(walk.shows(FEEDBACK_ADD_NOTE, "Add a note"))
        assertTrue(walk.heard("Thanks! I’ll look into it."))
        assertEquals(ParcelFeedbackModel.Moment.Sent, moment())
        val (id, given) = requests().single()
        assertEquals(gift.id, id)
        assertEquals(ParcelFeedbackAnswer.Wrong, given.answer)
        assertEquals(listOf(ParcelFeedbackReason.Steps), given.reasons)
        walk.shot("B-ST33-sent")
    }

    /** B-ST33: Yes is sent and thanked in place, and TalkBack hears the thanks. */
    @Test
    fun yesIsThankedInPlaceAndSent() {
        show()
        openQuestion()
        walk.tap(FEEDBACK_YES)
        walk.settle()
        assertTrue(walk.shows(FEEDBACK_BUBBLE, "Good to know, thanks!"))
        assertFalse(walk.exists(FEEDBACK_YES))
        assertEquals(ParcelFeedbackModel.Moment.Right, moment())
        val (id, request) = requests().single()
        assertEquals(gift.id, id)
        assertEquals(ParcelFeedbackAnswer.Right, request.answer)
        assertEquals(ParcelFeedbackRequestAsked.Page, request.asked)
        assertTrue(walk.heard("Good to know, thanks!"))
        walk.shot("B-ST33-right")
    }

    /** B-S59: where no carrier was found the row asks whether the carrier's site shows it; Not yet says it's early. */
    @Test
    fun theRowAsksWhetherTheCarriersSiteShowsItAndNotYetSaysItIsEarly() {
        show()
        openQuestion(lamp)
        assertTrue(walk.exists(FEEDBACK_ROW))
        assertFalse(walk.exists(FEEDBACK_PIP))
        assertTrue(walk.shows(FEEDBACK_ROW, "Does the carrier’s own site show it?"))
        assertTrue(walk.shows(FEEDBACK_YES, "Yes"))
        assertTrue(walk.shows(FEEDBACK_NOT_YET, "Not yet"))
        walk.shot("B-S59-question")

        walk.tap(FEEDBACK_NOT_YET)
        walk.settle()
        assertTrue(walk.shows(FEEDBACK_ROW, "Then it’s early days. Peek keeps asking."))
        assertFalse(walk.exists(FEEDBACK_YES))
        assertEquals(ParcelFeedbackModel.Moment.Early, moment(lamp))
        assertTrue(walk.heard("Then it’s early days. Peek keeps asking."))
        assertTrue(requests().isEmpty())
        walk.shot("B-S59-early")
    }

    /**
     * B-S59, B-S62, B-S63: Yes on the row asks who carries it, the sheet opening fully once its field is written in, on
     * the text keys with Next; then their page on the address keys, whose Send sends both; the row thanks the reader.
     */
    @Test
    fun yesOnTheRowAsksWhoCarriesItThenTheirPageAndTheKeyboardsSendSendsIt() {
        show()
        openQuestion(lamp)
        walk.tap(FEEDBACK_YES)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_CARRIER_SHEET) }
        walk.awaitStill()
        assertTrue(walk.shows(FEEDBACK_CARRIER_SHEET, "Who’s carrying it?"))
        assertTrue(walk.shows(FEEDBACK_PROMISE, "Sent with this parcel’s number. Nothing about you."))
        assertTrue(walk.onUi { walk.deliveries.modal })
        walk.node(FEEDBACK_SEND).assertIsNotEnabled()
        assertFalse(walk.onUi { work(lamp).large })
        walk.shot("B-S62-who")

        walk.node(FEEDBACK_WHO).performClick()
        compose.waitForIdle()
        walk.node(FEEDBACK_WHO).assertIsFocused()
        assertTrue(walk.onUi { work(lamp).large })
        assertKeys(walk.editorInfo(), EditorInfo.IME_ACTION_NEXT, InputType.TYPE_TEXT_VARIATION_NORMAL, InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)
        walk.awaitKeyboard()
        walk.node(FEEDBACK_WHO).performTextReplacement("Example Freight")
        walk.settle()
        walk.shot("B-S63-writing")

        walk.node(FEEDBACK_WHO).performImeAction()
        compose.waitForIdle()
        walk.node(FEEDBACK_WHERE).assertIsFocused()
        assertKeys(walk.editorInfo(), EditorInfo.IME_ACTION_SEND, InputType.TYPE_TEXT_VARIATION_URI, 0)
        walk.node(FEEDBACK_WHERE).performTextReplacement("example.com/track")
        walk.node(FEEDBACK_SEND).assertIsEnabled()
        walk.settle()
        walk.shot("B-S62-page")

        walk.node(FEEDBACK_WHERE).performImeAction()
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FEEDBACK_CARRIER_SHEET) }
        val (id, request) = requests().single()
        assertEquals(lamp.id, id)
        assertEquals(ParcelFeedbackAnswer.FoundElsewhere, request.answer)
        assertEquals("Example Freight", request.carrierName)
        assertEquals("example.com/track", request.trackingPage)
        assertTrue(walk.shows(FEEDBACK_ROW, "Thanks, that helps Peek learn this carrier."))
        assertFalse(walk.onUi { walk.deliveries.modal })
        walk.awaitStill()
        walk.shot("B-S59-named")
    }

    /** B-S61, B-ST38: the words sheet's chips, the reason given chosen; its note's counter from 200 left, and its limit. */
    @Test
    fun theWordsSheetsChipsAndItsNotesCounterFromTwoHundredLeft() {
        show()
        openWords(ParcelFeedbackReason.Status)
        assertEquals(true, selected(ParcelFeedbackReason.Status))
        assertNull(selected(ParcelFeedbackReason.Arrived))
        walk.tap(FEEDBACK_CHIP + ParcelFeedbackReason.Steps.name)
        assertEquals(true, selected(ParcelFeedbackReason.Steps))
        assertTrue(walk.shows(FEEDBACK_PROMISE, "what Chronopost answered"))
        walk.node(FEEDBACK_SEND).assertIsEnabled()
        walk.shot("B-S61-chips")

        walk.node(FEEDBACK_NOTE).performTextReplacement("n".repeat(3_799))
        compose.waitForIdle()
        assertFalse(walk.exists(FEEDBACK_NOTE_LEFT))
        walk.node(FEEDBACK_NOTE).performTextReplacement("n".repeat(3_800))
        compose.waitForIdle()
        assertTrue(walk.shows(FEEDBACK_NOTE_LEFT, "200 characters left"))
        walk.settle()
        walk.awaitKeyboard(required = false)
        walk.shot("B-ST38-note-counter")
        walk.node(FEEDBACK_NOTE).performTextReplacement("n".repeat(4_100))
        compose.waitForIdle()
        assertEquals(4_000, walk.editable(FEEDBACK_NOTE)?.length)
        assertTrue(walk.shows(FEEDBACK_NOTE_LEFT, "0 characters left"))
    }

    /** B-S63: while its words go the sheet spins, and neither its close, the back nor a swipe down sends it away. */
    @Test
    fun whileItsWordsGoNothingClosesTheSheet() {
        show { feedbackSending = CompletableDeferred() }
        openQuestion()
        walk.tap(FEEDBACK_NOT_QUITE)
        walk.settle()
        walk.source.feedbackSending?.complete(Unit)
        walk.tap(FEEDBACK_REASON + ParcelFeedbackReason.Carrier.name)
        walk.settle()
        walk.tap(FEEDBACK_ADD_NOTE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_WORDS_SHEET) }
        walk.source.feedbackSending = CompletableDeferred()
        walk.tap(FEEDBACK_SEND)
        assertTrue(walk.spins(FEEDBACK_SEND))
        assertTrue(walk.shows(FEEDBACK_SEND, "Sending…"))
        walk.node(FEEDBACK_SEND).assertIsNotEnabled()
        walk.node(FEEDBACK_CLOSE).assertIsNotEnabled()
        walk.node(FEEDBACK_CHIP + ParcelFeedbackReason.Carrier.name).assertIsNotEnabled()
        walk.shot("B-S63-sending")

        walk.back()
        assertTrue(walk.exists(FEEDBACK_WORDS_SHEET))
        compose.onAllNodes(hasTestTag(SHEET_PANEL)).onFirst().performTouchInput { swipeDown() }
        walk.settle()
        assertTrue(walk.exists(FEEDBACK_WORDS_SHEET))
        walk.source.feedbackSending?.complete(Unit)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FEEDBACK_WORDS_SHEET) }
        // The reason, then the words with it.
        assertEquals(2, requests().size)
        assertEquals(listOf(ParcelFeedbackReason.Carrier), requests().last().second.reasons)
    }

    /** B-S64: offline, the send fails: the sheet keeps what was typed and says so, which TalkBack hears. */
    @Test
    fun aSendThatFailsOfflineSaysSoAndTalkBackHearsIt() {
        show()
        openWords(ParcelFeedbackReason.Arrived)
        walk.node(FEEDBACK_NOTE).performTextReplacement("It came yesterday")
        walk.source.feedbackFailure = IOException("offline")
        walk.tap(FEEDBACK_SEND)
        walk.settle()
        assertTrue(walk.exists(FEEDBACK_WORDS_SHEET))
        assertTrue(walk.shows(FEEDBACK_FAILURE, "Couldn’t send that. Try again."))
        compose.waitUntil(LONG_MILLIS) { walk.heard("Couldn’t send that. Try again.") }
        // What the deliveries announce is a polite live region, which TalkBack reads as it changes.
        val announcement = compose.onAllNodes(hasTestTag(ANNOUNCEMENT), useUnmergedTree = true).onFirst().fetchSemanticsNode()
        assertEquals(LiveRegionMode.Polite, announcement.config.getOrNull(SemanticsProperties.LiveRegion))
        assertEquals("It came yesterday", walk.editable(FEEDBACK_NOTE))
        assertEquals(true, selected(ParcelFeedbackReason.Arrived))
        walk.awaitKeyboard(required = false)
        walk.shot("B-S64-failed")
    }

    /** B-ST34, B-S60: back from the carrier's site, Pip asks at the page's foot, for twelve seconds. */
    @Test
    fun backFromTheCarriersSitePipAsksAtThePagesFootForTwelveSeconds() {
        show()
        walk.open(gift)
        visitCarrier()
        val back = walk.now()
        walk.advanceTo(back + 100)
        assertEquals(ParcelFeedbackModel.Word.Back("Chronopost"), word())
        assertTrue(says("Does Chronopost say the same?"))
        assertTrue(walk.shows(FEEDBACK_BACK_YES, "Yes"))
        assertTrue(walk.shows(FEEDBACK_BACK_NO, "No"))
        assertTrue(walk.heard("Does Chronopost say the same?"))
        walk.shot("B-ST34-back")
        walk.advanceTo(back + 11_800)
        assertNotNull(word())
        walk.advanceTo(back + 12_300)
        assertNull(word())
        walk.advanceTo(back + 13_300)
        assertFalse(walk.exists(FEEDBACK_WORD))
    }

    /** B-ST34, B-S60: Yes on the way back is sent and thanked at the page's foot for four seconds. */
    @Test
    fun yesOnTheWayBackIsThankedForFourSeconds() {
        show()
        walk.open(gift)
        visitCarrier()
        walk.advanceTo(walk.now() + 100)
        walk.node(FEEDBACK_BACK_YES).performClick()
        val tapped = walk.now()
        walk.advanceTo(tapped + 100)
        assertEquals(ParcelFeedbackModel.Word.BackRight, word())
        assertTrue(says("Thanks, good to know."))
        assertTrue(walk.heard("Thanks, good to know."))
        assertEquals(ParcelFeedbackModel.Moment.Right, moment())
        val request = requests().single().second
        assertEquals(ParcelFeedbackAnswer.Right, request.answer)
        assertEquals(ParcelFeedbackRequestAsked.Back, request.asked)
        walk.shot("B-ST34-back-right")
        walk.advanceTo(tapped + 3_800)
        assertEquals(ParcelFeedbackModel.Word.BackRight, word())
        walk.advanceTo(tapped + 4_300)
        assertNull(word())
    }

    /** B-ST34, B-S60: No on the way back asks what is off, no reason chosen; its words are thanked at the page's foot. */
    @Test
    fun noOnTheWayBackAsksWhatIsOffAndItsWordsAreThanked() {
        show()
        walk.open(gift)
        visitCarrier()
        walk.tap(FEEDBACK_BACK_NO)
        walk.settle()
        assertTrue(walk.exists(FEEDBACK_WORDS_SHEET))
        assertNull(word())
        ParcelFeedbackReason.entries.forEach { assertNull(selected(it)) }
        walk.node(FEEDBACK_SEND).assertIsNotEnabled()
        walk.shot("B-ST34-back-no")

        walk.tap(FEEDBACK_CHIP + ParcelFeedbackReason.Status.name)
        walk.tap(FEEDBACK_SEND)
        val sent = walk.now()
        walk.advanceTo(sent + 100)
        assertEquals(ParcelFeedbackModel.Word.BackSent, word())
        assertTrue(says("Thanks, noted. It helps Peek get this right."))
        walk.advanceTo(sent + 1_100)
        compose.waitForIdle()
        assertFalse(walk.exists(FEEDBACK_WORDS_SHEET))
        assertEquals(ParcelFeedbackModel.Word.BackSent, word())
        walk.shot("B-ST34-back-sent")
        assertEquals(ParcelFeedbackModel.Moment.Noted, moment())
        val request = requests().single().second
        assertEquals(ParcelFeedbackRequestAsked.Back, request.asked)
        assertEquals(listOf(ParcelFeedbackReason.Status), request.reasons)
    }

    /** B-ST34: offline, an answer that could not go is taken back and said at the page's foot for four seconds. */
    @Test
    fun anAnswerThatCouldNotGoOfflineIsSaidAtThePagesFootForFourSeconds() {
        show { feedbackFailure = IOException("offline") }
        openQuestion()
        compose.mainClock.autoAdvance = false
        walk.node(FEEDBACK_YES).performClick()
        val tapped = walk.now()
        walk.advanceTo(tapped + 100)
        assertEquals(ParcelFeedbackModel.Word.Failed, word())
        assertTrue(says("Couldn’t send that. Try again."))
        assertTrue(walk.heard("Couldn’t send that. Try again."))
        assertEquals(ParcelFeedbackModel.Moment.Ask, moment())
        walk.shot("B-ST34-failed")
        walk.advanceTo(tapped + 3_800)
        assertEquals(ParcelFeedbackModel.Word.Failed, word())
        walk.advanceTo(tapped + 4_300)
        assertNull(word())
        walk.advanceTo(tapped + 5_300)
        assertFalse(walk.exists(FEEDBACK_WORD))
        assertTrue(walk.shows(FEEDBACK_BUBBLE, "Did I get this one right?"))
    }

    /** B-S65, B-S61: at the largest text the words take Pip's room, the pills wrap, and the words sheet follows. */
    @Test
    fun atTheLargestTextTheWordsTakePipsRoomAndThePillsWrap() {
        walk.fontScale = 2f
        show()
        openQuestion()
        assertFalse(walk.exists(FEEDBACK_PIP))
        assertTrue(walk.exists(FEEDBACK_BUBBLE))
        walk.shot("B-S65-ask")

        walk.tap(FEEDBACK_NOT_QUITE)
        walk.settle()
        val bubble = walk.node(FEEDBACK_BUBBLE).getUnclippedBoundsInRoot()
        val pills = ParcelFeedbackReason.entries.map { walk.node(FEEDBACK_REASON + it.name).getUnclippedBoundsInRoot() }
        assertTrue("Pills on several lines: $pills", pills.map { it.top.value.toInt() }.distinct().size >= 2)
        pills.forEach {
            assertTrue("$it within the bubble $bubble", it.left >= bubble.left && it.right <= bubble.right)
            assertTrue("$it is 44 tall to the touch at least", (it.bottom - it.top).value >= 43.5f)
        }
        walk.shot("B-S65-reasons")

        walk.tap(FEEDBACK_REASON + ParcelFeedbackReason.Status.name)
        walk.settle()
        walk.tap(FEEDBACK_ADD_NOTE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_WORDS_SHEET) }
        walk.awaitStill()
        assertEquals(true, selected(ParcelFeedbackReason.Status))
        walk.shot("B-S61-words")
    }

    private companion object {
        /** The reasons' words, in [ParcelFeedbackReason]'s order. */
        val REASONS = listOf("It has arrived", "Wrong status", "Missing steps", "Wrong time or place", "Wrong carrier", "Something else")

        const val CAPITALS = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS or InputType.TYPE_TEXT_FLAG_CAP_WORDS or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
    }
}
