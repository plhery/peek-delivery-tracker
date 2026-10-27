package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackAnswer
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequest
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestApp
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestAsked
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.util.UUID
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/**
 * ParcelFeedbackTests.swift: the question a parcel's page asks its reader, which one, what the device remembers of it,
 * what an answer carries, and where it is sent. Answers say they come from `ios`, as the iPhone's do.
 */
class ParcelFeedbackTests {
    private class Clock {
        var now: Instant = Instant.ofEpochSecond(1_790_000_000)
    }

    /** What a page sent, and what it showed while each answer was on its way. */
    private class Sent {
        val requests = mutableListOf<ParcelFeedbackRequest>()
        val moments = mutableListOf<ParcelFeedbackModel.Moment>()
        val counted = mutableListOf<String>()
        var fails = false
        var model: ParcelFeedbackModel? = null

        val client: ParcelFeedbackClient
            get() = ParcelFeedbackClient { request ->
                requests += request
                model?.let { moments += it.moment }
                if (fails) throw DeliveryApiError.ServiceFailed(500)
            }
    }

    private val linkId = "22222222222A"
    private val scan = "3:2026-10-02T08:00:00Z"
    private val ios = ParcelFeedbackRequestApp.Ios

    /** `AppConfiguration.version` in the iPhone's tests. */
    private val appVersion = "1.1.3"

    private operator fun Instant.plus(seconds: Double): Instant = plusNanos(Math.round(seconds * 1_000_000_000))

    private fun body(request: ParcelFeedbackRequest): String =
        JsonEncoder.deliveryTracker.encode(ParcelFeedbackRequest.serializer(), request).decodeToString()

    private fun parcel(
        carrier: CarrierId = CarrierId.Dhl,
        stage: TrackingStage? = TrackingStage.InTransit,
        sync: SyncStatus = SyncStatus.Ok,
        archived: Boolean = false,
    ): Parcel {
        val id = UUID.randomUUID()
        return Parcel(
            id = id, trackingNumber = "TESTPARCEL123789", label = "", carrier = carrier, createdAt = "2026-10-01T08:00:00Z",
            syncStatus = sync, archivedAt = if (archived) "2026-10-03T08:00:00Z" else null, notificationsMuted = false,
            trackingEvents = stage?.let {
                listOf(TrackingEvent(id = UUID.randomUUID(), packageId = id, stage = it, description = "", occurredAt = "2026-10-02T08:00:00Z"))
            } ?: emptyList(),
        )
    }

    private fun page(
        subject: ParcelFeedbackSubject,
        question: ParcelFeedbackQuestion = ParcelFeedbackQuestion.Found,
        scan: String? = null,
    ): ParcelFeedbackModel.Page = ParcelFeedbackModel.Page(subject, question, scan ?: this.scan, carrier = "DHL", language = AppLanguage.Fr)

    /**
     * A page as one visit holds it: another visit is another model over the same memory. Its answers run in the scope
     * it is visited from, the app's here.
     */
    private fun CoroutineScope.visit(page: ParcelFeedbackModel.Page?, sent: Sent, store: UserDefaults, clock: Clock): ParcelFeedbackModel {
        val model = ParcelFeedbackModel(
            scope = this, memory = ParcelFeedbackMemory(store) { clock.now }, now = { clock.now },
            report = { name, outcome -> sent.counted += "$name ${outcome.rawValue}" },
            review = ReviewPrompt(store, version = appVersion), app = ios,
        )
        sent.model = model
        model.show(page, through = sent.client)
        return model
    }

    /** The reader opens a carrier's page from this one and comes back after a while. */
    private fun comeBack(model: ParcelFeedbackModel, clock: Clock, from: String = "DHL", after: Double = 30.0, busy: Boolean = false) {
        model.visited(from)
        model.left()
        clock.now += after
        model.returned(busy = busy)
    }

    // Which question

    @Test
    fun aParcelACarrierAnswersForIsAskedWhetherItIsRight() {
        assertEquals(ParcelFeedbackQuestion.Found, ParcelFeedbackQuestion(parcel(), showsHistory = true))
        // Still being asked about, with what an earlier answer said on the page.
        assertEquals(ParcelFeedbackQuestion.Found, ParcelFeedbackQuestion(parcel(sync = SyncStatus.Syncing), showsHistory = true))
        // A carrier answered for a parcel nobody named one for.
        assertEquals(ParcelFeedbackQuestion.Found, ParcelFeedbackQuestion(parcel(carrier = CarrierId.Unknown), showsHistory = true))
        // Announced, not scanned yet: the page shows that much.
        assertEquals(ParcelFeedbackQuestion.Found, ParcelFeedbackQuestion(parcel(stage = TrackingStage.Pending), showsHistory = true))
    }

    @Test
    fun aParcelNoCarrierKnowsIsAskedWhoseItIs() {
        assertEquals(ParcelFeedbackQuestion.Unknown, ParcelFeedbackQuestion(parcel(carrier = CarrierId.Unknown, stage = null), showsHistory = false))
        assertEquals(
            ParcelFeedbackQuestion.Unknown,
            ParcelFeedbackQuestion(parcel(carrier = CarrierId.Unknown, stage = TrackingStage.Pending, sync = SyncStatus.Waiting), showsHistory = true),
        )
    }

    @Test
    fun nothingIsAskedOfAnArchivedParcelOrBeforeItsFirstAnswer() {
        assertNull(ParcelFeedbackQuestion(parcel(archived = true), showsHistory = true))
        assertNull(ParcelFeedbackQuestion(parcel(carrier = CarrierId.Unknown, stage = null, archived = true), showsHistory = false))
        for (waiting in listOf(SyncStatus.Pending, SyncStatus.Syncing)) {
            assertNull(ParcelFeedbackQuestion(parcel(stage = null, sync = waiting), showsHistory = true))
            assertNull(ParcelFeedbackQuestion(parcel(carrier = CarrierId.Unknown, stage = TrackingStage.Pending, sync = waiting), showsHistory = true))
        }
        // A carrier the page shows no history for.
        assertNull(ParcelFeedbackQuestion(parcel(stage = null, sync = SyncStatus.Unsupported), showsHistory = false))
    }

    @Test
    fun theScanIsWhatThePageEndsOn() {
        var parcel = this.parcel(stage = null)
        assertEquals("", parcel.feedbackScan)
        parcel = parcel.copy(
            trackingEvents = listOf(
                TrackingEvent(id = UUID.randomUUID(), packageId = parcel.id, stage = TrackingStage.InTransit, description = "", occurredAt = "2026-10-02T08:00:00Z"),
            ),
        )
        assertEquals("1:2026-10-02T08:00:00Z", parcel.feedbackScan)
        parcel = parcel.copy(
            trackingEvents = parcel.trackingEvents +
                TrackingEvent(id = UUID.randomUUID(), packageId = parcel.id, stage = TrackingStage.Delivered, description = "", occurredAt = "2026-10-03T09:30:00Z"),
        )
        assertEquals("2:2026-10-03T09:30:00Z", parcel.feedbackScan)
    }

    // What the device remembers

    @Test
    fun anAnswerKeepsTheWayBackQuietAboutItsScanAndForADay() {
        val store = MemoryDefaults()
        val clock = Clock()
        val memory = ParcelFeedbackMemory(store) { clock.now }
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val next = "4:2026-10-03T09:30:00Z"

        assertTrue(memory.asksOnReturn(subject, scan = scan))
        memory.rememberAnswer(subject, scan = scan)

        assertFalse(memory.asksOnReturn(subject, scan = scan))
        assertTrue("Another parcel was never answered for", memory.asksOnReturn(ParcelFeedbackSubject.Parcel(UUID.randomUUID()), scan = scan))
        clock.now += 2 * 3_600.0
        assertFalse("A new scan the same day is not asked about", memory.asksOnReturn(subject, scan = next))
        clock.now += -2 * 3_600.0 + ParcelFeedbackMemory.quiet - 1
        assertFalse(memory.asksOnReturn(subject, scan = next))
        clock.now += 1.0
        assertTrue("A new scan a day later is", memory.asksOnReturn(subject, scan = next))
        clock.now += 30 * ParcelFeedbackMemory.quiet
        assertFalse("The scan answered for never is again", memory.asksOnReturn(subject, scan = scan))
    }

    @Test
    fun aClockSetBackHidesNoNewScan() {
        val store = MemoryDefaults()
        val clock = Clock()
        val memory = ParcelFeedbackMemory(store) { clock.now }
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        memory.rememberAnswer(subject, scan = scan)

        clock.now += -7 * ParcelFeedbackMemory.quiet

        assertTrue(memory.asksOnReturn(subject, scan = "4:2026-10-03T09:30:00Z"))
        assertFalse(memory.asksOnReturn(subject, scan = scan))
    }

    @Test
    fun theWayBackAsksOncePerScanAndNotAfterAnAnswer() {
        val store = MemoryDefaults()
        val clock = Clock()
        val memory = ParcelFeedbackMemory(store) { clock.now }
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())

        assertTrue(memory.asksOnReturn(subject, scan = scan))
        memory.rememberAskedOnReturn(subject, scan = scan)
        assertFalse(memory.asksOnReturn(subject, scan = scan))

        val next = "4:2026-10-03T09:30:00Z"
        assertTrue(memory.asksOnReturn(subject, scan = next))
        memory.rememberAnswer(subject, scan = next)
        assertFalse(memory.asksOnReturn(subject, scan = next))
        val third = "5:2026-10-04T07:15:00Z"
        assertFalse("Not twice in a day", memory.asksOnReturn(subject, scan = third))
        clock.now += ParcelFeedbackMemory.quiet
        assertFalse("Nor ever about the scan answered for", memory.asksOnReturn(subject, scan = next))
        assertTrue(memory.asksOnReturn(subject, scan = third))
    }

    @Test
    fun aLinkIsRememberedByItsDigestOnly() {
        val store = MemoryDefaults()
        val memory = ParcelFeedbackMemory(store)
        val key = ParcelFeedbackSubject.Link(linkId).memoryKey

        assertEquals(64, key.characterCount)
        assertEquals(ParcelFeedbackSubject.Link(linkId).memoryKey, key)
        assertNotEquals(ParcelFeedbackSubject.Link("22222222222B").memoryKey, key)

        memory.rememberAnswer(ParcelFeedbackSubject.Link(linkId), scan = scan)
        memory.rememberAskedOnReturn(ParcelFeedbackSubject.Link(linkId), scan = scan)
        assertFalse(memory.asksOnReturn(ParcelFeedbackSubject.Link(linkId), scan = scan))
        val kept = store.values.values.filterIsInstance<ByteArray>().first { it.decodeToString().contains(key) }
        assertFalse("The link's id opens its parcel", kept.decodeToString().contains(linkId))

        val id = UUID.randomUUID()
        assertEquals(id.uuidString, ParcelFeedbackSubject.Parcel(id).memoryKey)
    }

    @Test
    fun onlyTheLatestParcelsAreRemembered() {
        val store = MemoryDefaults()
        val clock = Clock()
        val memory = ParcelFeedbackMemory(store) { clock.now }
        val subjects = (0..ParcelFeedbackMemory.limit).map { ParcelFeedbackSubject.Parcel(UUID.randomUUID()) }

        for (subject in subjects) {
            memory.rememberAnswer(subject, scan = scan)
            clock.now += 1.0
        }

        assertTrue("The oldest made room", memory.asksOnReturn(subjects[0], scan = scan))
        assertFalse(memory.asksOnReturn(subjects[1], scan = scan))
        assertFalse(memory.asksOnReturn(subjects[ParcelFeedbackMemory.limit], scan = scan))

        memory.forgetAll()
        assertTrue(memory.asksOnReturn(subjects[1], scan = scan))
    }

    // What an answer carries

    @Test
    fun rightComesAlone() {
        val id = UUID.randomUUID()
        val request = ParcelFeedbackRequest.right(id = id, asked = ParcelFeedbackRequestAsked.Back, language = AppLanguage.De, app = ios)

        assertEquals(
            ParcelFeedbackRequest(id = id, answer = ParcelFeedbackAnswer.Right, asked = ParcelFeedbackRequestAsked.Back, app = ios, locale = NativePushLocale.De),
            request,
        )
        assertEquals("""{"answer":"right","app":"ios","asked":"back","id":"${id.uuidString}","locale":"de"}""", body(request))
    }

    @Test
    fun wrongCarriesItsReasonsInThePagesOrderAndATrimmedNote() {
        val request = ParcelFeedbackRequest.wrong(
            reasons = setOf(ParcelFeedbackReason.Other, ParcelFeedbackReason.Arrived, ParcelFeedbackReason.TimePlace),
            note = "  It came on Monday.\n", asked = ParcelFeedbackRequestAsked.Page, language = AppLanguage.En, app = ios,
        )

        assertEquals(ParcelFeedbackAnswer.Wrong, request.answer)
        assertEquals(listOf(ParcelFeedbackReason.Arrived, ParcelFeedbackReason.TimePlace, ParcelFeedbackReason.Other), request.reasons)
        assertEquals("It came on Monday.", request.note)
        assertNull(request.carrierName)
        assertNull(request.trackingPage)
        val json = Json.parseToJsonElement(body(request)).jsonObject
        assertEquals(listOf("arrived", "time_place", "other"), json.getValue("reasons").jsonArray.map { it.jsonPrimitive.content })

        // Nothing is said with empty words.
        val bare = ParcelFeedbackRequest.wrong(reasons = emptySet(), note = " \n ", asked = ParcelFeedbackRequestAsked.Page, language = AppLanguage.En, app = ios)
        assertNull(bare.reasons)
        assertNull(bare.note)
        val noteOnly = Json.parseToJsonElement(
            body(ParcelFeedbackRequest.wrong(reasons = emptySet(), note = "Two days late", asked = ParcelFeedbackRequestAsked.Back, language = AppLanguage.En, app = ios)),
        ).jsonObject
        assertEquals(setOf("id", "answer", "note", "asked", "app", "locale"), noteOnly.keys)
    }

    @Test
    fun wordsStayWithinWhatAnAnswerMayCarry() {
        val long = "a".repeat(ParcelFeedbackRequest.noteLimit + 20)
        assertEquals(
            ParcelFeedbackRequest.noteLimit,
            ParcelFeedbackRequest.wrong(reasons = emptySet(), note = long, asked = ParcelFeedbackRequestAsked.Page, language = AppLanguage.En, app = ios)
                .note?.characterCount,
        )
        // A flag is two code points: it is kept whole or not at all.
        val flag = "🇨🇭"
        assertEquals("ab", ParcelFeedbackRequest.limited("ab$flag", to = 3))
        assertEquals("ab$flag", ParcelFeedbackRequest.limited("ab$flag", to = 4))
        assertEquals("abc", ParcelFeedbackRequest.limited("abc", to = 3))
        assertNull(ParcelFeedbackRequest.words("  \n", limit = 10))
    }

    // The sheets' fields (ParcelFeedbackViews.swift)

    @Test
    fun aSheetWithNothingButSpaceHasNothingToSend() {
        assertTrue(ParcelFeedbackWordsSheet.empty(emptySet(), note = ""))
        assertTrue(ParcelFeedbackWordsSheet.empty(emptySet(), note = " \n\t\r\n\u00A0\u3000\u2028\u0085"))
        assertFalse(ParcelFeedbackWordsSheet.empty(setOf(ParcelFeedbackReason.Status), note = " "))
        assertFalse(ParcelFeedbackWordsSheet.empty(emptySet(), note = " Lyon "))
        // A zero-width space is space to Foundation; a no-break space too.
        assertTrue(ParcelFeedbackWordsSheet.empty(emptySet(), note = "\u200B\u00A0"))

        assertTrue(ParcelFeedbackCarrierSheet.empty(who = "", page = " \n "))
        assertFalse(ParcelFeedbackCarrierSheet.empty(who = " Example Parcels", page = ""))
        assertFalse(ParcelFeedbackCarrierSheet.empty(who = "", page = "example.com/track"))
    }

    @Test
    fun theNoteSaysHowManyUnicodeScalarsAreLeftNearItsLimit() {
        val english = Localizer(AppLanguage.En)
        assertEquals(ParcelFeedbackRequest.noteLimit, ParcelFeedbackWordsSheet.noteLeft(""))
        assertNull(ParcelFeedbackWordsSheet.noteLeftText("", english))
        // Unicode scalars, as Swift counts `unicodeScalars`, not UTF-16 units nor Characters.
        assertEquals(ParcelFeedbackRequest.noteLimit - 1, ParcelFeedbackWordsSheet.noteLeft("\uD83D\uDC4D"))
        assertEquals(ParcelFeedbackRequest.noteLimit - 5, ParcelFeedbackWordsSheet.noteLeft("\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67"))
        assertEquals(ParcelFeedbackRequest.noteLimit - 2, ParcelFeedbackWordsSheet.noteLeft("e\u0301"))
        assertEquals(ParcelFeedbackRequest.noteLimit - 2, ParcelFeedbackWordsSheet.noteLeft("\r\n"))
        assertEquals("A lone surrogate is U+FFFD", ParcelFeedbackRequest.noteLimit - 1, ParcelFeedbackWordsSheet.noteLeft("\uD800"))

        val warning = ParcelFeedbackRequest.noteLimit - ParcelFeedbackRequest.noteWarning
        assertNull(ParcelFeedbackWordsSheet.noteLeftText("a".repeat(warning - 1), english))
        assertEquals("200 characters left", ParcelFeedbackWordsSheet.noteLeftText("a".repeat(warning), english))
        assertEquals("1 character left", ParcelFeedbackWordsSheet.noteLeftText("a".repeat(ParcelFeedbackRequest.noteLimit - 1), english))
        assertEquals("0 characters left", ParcelFeedbackWordsSheet.noteLeftText("\uD83D\uDC4D".repeat(ParcelFeedbackRequest.noteLimit), english))
    }

    @Test
    fun aFieldKeepsWhatIsTypedWithinItsLimitAsItIsTyped() {
        val limit = ParcelFeedbackRequest.noteLimit
        val full = "a".repeat(limit - 1)
        assertEquals("a".repeat(limit), ParcelFeedbackWordsSheet.note("a".repeat(limit + 1)))
        // Whole Characters while their scalars fit: a thumb fits, a flag or an accented e does not.
        assertEquals("$full\uD83D\uDC4D", ParcelFeedbackWordsSheet.note("$full\uD83D\uDC4D"))
        assertEquals(full, ParcelFeedbackWordsSheet.note("$full\uD83C\uDDE8\uD83C\uDDED"))
        assertEquals(full, ParcelFeedbackWordsSheet.note("${full}e\u0301"))
        // Space stays until the answer is sent.
        assertEquals("  Lyon \n", ParcelFeedbackWordsSheet.note("  Lyon \n"))
        assertEquals("a\uFFFDb", ParcelFeedbackWordsSheet.note("a\uD800b"))

        assertEquals("n".repeat(ParcelFeedbackRequest.carrierNameLimit), ParcelFeedbackCarrierSheet.who("n".repeat(ParcelFeedbackRequest.carrierNameLimit + 1)))
        val thumbs = "\uD83D\uDC4D".repeat(ParcelFeedbackRequest.carrierNameLimit)
        assertEquals("Twice as many UTF-16 units, as many scalars", thumbs, ParcelFeedbackCarrierSheet.who(thumbs))
        assertEquals("p".repeat(ParcelFeedbackRequest.trackingPageLimit), ParcelFeedbackCarrierSheet.page("p".repeat(ParcelFeedbackRequest.trackingPageLimit + 9)))
        assertEquals(" example.com ", ParcelFeedbackCarrierSheet.page(" example.com "))
    }

    @Test
    fun foundElsewhereCarriesANameOrItsPageAsTyped() {
        val request = ParcelFeedbackRequest.foundElsewhere(
            carrierName = " Example Parcels ", trackingPage = " example.com/track?id=TEST ", language = AppLanguage.Pl, app = ios,
        )

        assertEquals(ParcelFeedbackAnswer.FoundElsewhere, request.answer)
        assertEquals("Example Parcels", request.carrierName)
        assertEquals("example.com/track?id=TEST", request.trackingPage)
        assertEquals(ParcelFeedbackRequestAsked.Page, request.asked)
        assertEquals(NativePushLocale.Pl, request.locale)
        val json = Json.parseToJsonElement(body(request)).jsonObject
        assertEquals("found_elsewhere", json.getValue("answer").jsonPrimitive.content)
        assertEquals(setOf("id", "answer", "carrierName", "trackingPage", "asked", "app", "locale"), json.keys)

        val named = ParcelFeedbackRequest.foundElsewhere(carrierName = "n".repeat(200), trackingPage = "", language = AppLanguage.En, app = ios)
        assertEquals(ParcelFeedbackRequest.carrierNameLimit, named.carrierName?.characterCount)
        assertNull(named.trackingPage)
        assertEquals(
            ParcelFeedbackRequest.trackingPageLimit,
            ParcelFeedbackRequest.foundElsewhere(carrierName = "", trackingPage = "p".repeat(600), language = AppLanguage.En, app = ios).trackingPage?.characterCount,
        )
    }

    // Pip asks

    @Test
    fun yesIsShownAtOnceSentAndAskedAgainOnALaterVisit() = runTest {
        val store = MemoryDefaults()
        val clock = Clock()
        val sent = Sent()
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val model = visit(page(subject), sent, store, clock)
        assertEquals(ParcelFeedbackQuestion.Found, model.standing)
        assertEquals(ParcelFeedbackModel.Moment.Ask, model.moment)

        model.right()

        assertEquals("The thanks do not wait for the service", listOf(ParcelFeedbackModel.Moment.Right), sent.moments)
        assertEquals(listOf(ParcelFeedbackAnswer.Right), sent.requests.map { it.answer })
        assertEquals(ParcelFeedbackRequestAsked.Page, sent.requests.first().asked)
        assertEquals(ParcelFeedbackRequestApp.Ios, sent.requests.first().app)
        assertEquals(NativePushLocale.Fr, sent.requests.first().locale)
        assertEquals(listOf("parcel-feedback-right success"), sent.counted)
        assertEquals(ParcelFeedbackModel.Moment.Right, model.moment)
        assertEquals("On this visit the thanks stay to be read", ParcelFeedbackQuestion.Found, model.standing)
        assertNull(model.word)

        // Something may happen that its reader tells only later: Pip asks again on the next visit.
        clock.now += 2 * 3_600.0
        val later = visit(page(subject), Sent(), store, clock)
        assertEquals(ParcelFeedbackQuestion.Found, later.standing)
        assertEquals(ParcelFeedbackModel.Moment.Ask, later.moment)
    }

    @Test
    fun anAnswerThatCouldNotBeSentIsTakenBack() = runTest {
        val store = MemoryDefaults()
        val clock = Clock()
        val sent = Sent()
        sent.fails = true
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val model = visit(page(subject), sent, store, clock)

        model.right()

        assertEquals(listOf(ParcelFeedbackModel.Moment.Right), sent.moments)
        assertEquals(ParcelFeedbackModel.Moment.Ask, model.moment)
        assertEquals(ParcelFeedbackModel.Word.Failed, model.word)
        assertEquals(listOf("parcel-feedback-right error"), sent.counted)
        assertEquals("Nothing was answered", ParcelFeedbackQuestion.Found, visit(page(subject), Sent(), store, clock).standing)

        model.notQuite()
        model.pick(ParcelFeedbackReason.Status)

        assertEquals(listOf(ParcelFeedbackModel.Moment.Right, ParcelFeedbackModel.Moment.Sent), sent.moments)
        assertEquals("The reasons are offered again", ParcelFeedbackModel.Moment.Reasons, model.moment)
        assertEquals(ParcelFeedbackModel.Word.Failed, model.word)
        model.addNote()
        assertNull("There is no answer to add a note to", model.words)
    }

    @Test
    fun notQuiteOffersTheReasonsAndOneTapSendsOne() = runTest {
        val store = MemoryDefaults()
        val clock = Clock()
        val sent = Sent()
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val model = visit(page(subject), sent, store, clock)

        model.notQuite()
        assertEquals(ParcelFeedbackModel.Moment.Reasons, model.moment)
        assertTrue("Nothing is sent before a reason is given", sent.requests.isEmpty())
        assertEquals("Nor is anything answered", ParcelFeedbackQuestion.Found, visit(page(subject), Sent(), store, clock).standing)

        model.pick(ParcelFeedbackReason.Steps)

        assertEquals(listOf(ParcelFeedbackModel.Moment.Sent), sent.moments)
        assertEquals(ParcelFeedbackModel.Moment.Sent, model.moment)
        assertEquals(listOf(ParcelFeedbackAnswer.Wrong), sent.requests.map { it.answer })
        assertEquals(listOf(ParcelFeedbackReason.Steps), sent.requests.first().reasons)
        assertNull(sent.requests.first().note)
        assertEquals(listOf("parcel-feedback-wrong success"), sent.counted)
        assertEquals(ParcelFeedbackQuestion.Found, visit(page(subject), Sent(), store, clock).standing)

        // A note follows under the same id, with the reason given still on.
        model.addNote()
        assertEquals(sent.requests.first().id, model.words?.id)
        assertEquals(setOf(ParcelFeedbackReason.Steps), model.words?.reasons)
        assertEquals(ParcelFeedbackRequestAsked.Page, model.words?.asked)

        val closed = model.sendWords(setOf(ParcelFeedbackReason.Steps, ParcelFeedbackReason.Carrier), note = " It changed hands in Lyon. ")

        assertTrue(closed)
        assertNull(model.words)
        assertEquals("The thanks stay, with nothing more to add", ParcelFeedbackModel.Moment.Noted, model.moment)
        assertEquals(2, sent.requests.size)
        assertEquals(sent.requests[0].id, sent.requests[1].id)
        assertEquals(listOf(ParcelFeedbackReason.Steps, ParcelFeedbackReason.Carrier), sent.requests[1].reasons)
        assertEquals("It changed hands in Lyon.", sent.requests[1].note)
        assertEquals(ParcelFeedbackRequestAsked.Page, sent.requests[1].asked)
        assertEquals("The note is the same answer", listOf("parcel-feedback-wrong success"), sent.counted)
    }

    @Test
    fun aNoteThatCouldNotBeSentKeepsItsSheetOpen() = runTest {
        val store = MemoryDefaults()
        val sent = Sent()
        val model = visit(page(ParcelFeedbackSubject.Parcel(UUID.randomUUID())), sent, store, Clock())
        model.notQuite()
        model.pick(ParcelFeedbackReason.Other)
        model.addNote()
        sent.fails = true

        val closed = model.sendWords(setOf(ParcelFeedbackReason.Other), note = "The box was empty")

        assertFalse(closed)
        assertNotNull(model.words)
        assertEquals(ParcelFeedbackModel.Moment.Sent, model.moment)
        assertFalse(model.sending)
        assertNull("The sheet says it itself", model.word)
        assertEquals(listOf("parcel-feedback-wrong success"), sent.counted)
    }

    /**
     * On iOS an answer runs in its button's `Task`, which nothing cancels. Here it runs in the app's scope: a screen that
     * goes away while an answer is on its way, cancelling its call, leaves the answer to the service.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun leavingThePageWhileAnAnswerIsOnItsWayLeavesItToTheService() = runTest {
        val app = CoroutineScope(SupervisorJob() + StandardTestDispatcher(testScheduler))
        val store = MemoryDefaults()
        val clock = Clock()
        val counted = mutableListOf<String>()
        var reply = CompletableDeferred<Unit>()
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val model = ParcelFeedbackModel(
            scope = app, memory = ParcelFeedbackMemory(store) { clock.now }, now = { clock.now },
            report = { name, outcome -> counted += "$name ${outcome.rawValue}" }, review = ReviewPrompt(store, version = appVersion), app = ios,
        )
        model.show(page(subject), through = ParcelFeedbackClient { reply.await() })

        val screen = launch { model.right() }
        runCurrent()
        assertEquals(ParcelFeedbackModel.Moment.Right, model.moment)
        screen.cancel()
        runCurrent()
        reply.complete(Unit)
        advanceUntilIdle()
        assertEquals(ParcelFeedbackModel.Moment.Right, model.moment)
        assertNull("Not a failure", model.word)
        assertEquals(listOf("parcel-feedback-right success"), counted)
        assertFalse("The answer is remembered", ParcelFeedbackMemory(store) { clock.now }.asksOnReturn(subject, scan = scan))

        // A sheet's send too: the sheet goes with its screen, and the model is ready for the next answer.
        model.show(page(ParcelFeedbackSubject.Parcel(UUID.randomUUID())), through = ParcelFeedbackClient { reply.await() })
        model.wrongOnReturn()
        reply = CompletableDeferred()
        val sheet = launch { model.sendWords(setOf(ParcelFeedbackReason.Status), note = "Not here") }
        runCurrent()
        assertTrue(model.sending)
        sheet.cancel()
        runCurrent()
        reply.complete(Unit)
        advanceUntilIdle()
        assertFalse(model.sending)
        assertNull(model.words)
        assertEquals(ParcelFeedbackModel.Word.BackSent, model.word)
        assertEquals(listOf("parcel-feedback-right success", "parcel-feedback-wrong success"), counted)

        // A call that runs out of time is a failure, as `URLError.timedOut` is on iOS, though it throws a cancellation.
        val late = ParcelFeedbackModel(
            scope = app, memory = ParcelFeedbackMemory(store) { clock.now }, now = { clock.now },
            report = { name, outcome -> counted += "$name ${outcome.rawValue}" }, review = ReviewPrompt(store, version = appVersion), app = ios,
        )
        late.show(page(ParcelFeedbackSubject.Parcel(UUID.randomUUID())), through = ParcelFeedbackClient { withTimeout(10.seconds) { awaitCancellation() } })
        val waiting = launch { late.right() }
        advanceTimeBy(10.seconds)
        advanceUntilIdle()
        assertTrue(waiting.isCompleted)
        assertEquals(ParcelFeedbackModel.Moment.Ask, late.moment)
        assertEquals(ParcelFeedbackModel.Word.Failed, late.word)
        assertEquals("parcel-feedback-right error", counted.last())
    }

    /** Only the send can fail an answer, as on iOS: what the app does once the service has it takes nothing back. */
    @Test
    fun analyticsThatThrowOnceTheServiceHasTheAnswerTakeNothingBack() = runTest {
        val app = CoroutineScope(SupervisorJob() + StandardTestDispatcher(testScheduler))
        val store = MemoryDefaults()
        val clock = Clock()
        val days = ReviewPrompt(store, version = appVersion)
        days.used(at = clock.now.minusSeconds(2 * 86_400))
        days.used(at = clock.now.minusSeconds(86_400))
        val sent = Sent()
        val model = ParcelFeedbackModel(
            scope = app, memory = ParcelFeedbackMemory(store) { clock.now }, now = { clock.now },
            report = { name, outcome ->
                sent.counted += "$name ${outcome.rawValue}"
                throw IllegalStateException("analytics")
            },
            review = ReviewPrompt(store, version = appVersion), app = ios,
        )
        sent.model = model
        model.show(page(ParcelFeedbackSubject.Parcel(UUID.randomUUID())), through = sent.client)

        val thrown = runCatching { model.right() }.exceptionOrNull()

        assertEquals("analytics", thrown?.message)
        assertEquals(1, sent.requests.size)
        assertEquals(ParcelFeedbackModel.Moment.Right, model.moment)
        assertNull(model.word)
        assertEquals("Not counted as a failure too", listOf("parcel-feedback-right success"), sent.counted)
        assertTrue("Nothing went wrong today", ReviewPrompt(store, version = appVersion).isDue(at = clock.now, calm = true))
    }

    // On the way back

    @Test
    fun theWayBackFromACarriersSiteAsksOncePerScan() = runTest {
        val store = MemoryDefaults()
        val clock = Clock()
        val sent = Sent()
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val model = visit(page(subject), sent, store, clock)

        // Back without having left for a carrier's page.
        model.left()
        clock.now += 30.0
        model.returned()
        assertNull(model.word)

        comeBack(model, clock, from = "Swiss Post")
        assertEquals(ParcelFeedbackModel.Word.Back(site = "Swiss Post"), model.word)
        assertEquals(12.seconds, ParcelFeedbackModel.Word.Back(site = "Swiss Post").stay)

        // It leaves by itself; that counts as asked.
        model.wordLeft(ParcelFeedbackModel.Word.Failed)
        assertNotNull("Only the word that stayed its time leaves", model.word)
        model.wordLeft(ParcelFeedbackModel.Word.Back(site = "Swiss Post"))
        assertNull(model.word)
        comeBack(model, clock)
        assertNull(model.word)
        assertNull("Nor on a later visit", comeBackOnALaterVisit(subject, store, clock))
        assertEquals("Pip still asks", ParcelFeedbackQuestion.Found, model.standing)

        // A new scan is asked about once more.
        model.show(page(subject, scan = "4:2026-10-03T09:30:00Z"), through = sent.client)
        comeBack(model, clock)
        assertEquals(ParcelFeedbackModel.Word.Back(site = "DHL"), model.word)
        assertTrue(sent.requests.isEmpty())
    }

    private fun CoroutineScope.comeBackOnALaterVisit(subject: ParcelFeedbackSubject, store: UserDefaults, clock: Clock): ParcelFeedbackModel.Word? {
        val model = visit(page(subject), Sent(), store, clock)
        comeBack(model, clock)
        return model.word
    }

    @Test
    fun theWayBackAsksNothingWhenThereIsNothingToCompare() = runTest {
        val store = MemoryDefaults()
        val clock = Clock()

        // A glance away is not a look at the carrier's page.
        val glance = visit(page(ParcelFeedbackSubject.Parcel(UUID.randomUUID())), Sent(), store, clock)
        comeBack(glance, clock, after = 1.0)
        assertNull(glance.word)
        comeBack(glance, clock)
        assertNotNull("A real visit is still asked about", glance.word)

        // Something else is said or asked over the page: the question waits for another visit.
        val busy = visit(page(ParcelFeedbackSubject.Parcel(UUID.randomUUID())), Sent(), store, clock)
        comeBack(busy, clock, busy = true)
        assertNull(busy.word)
        busy.returned()
        assertNull("The visit was spent", busy.word)
        comeBack(busy, clock)
        assertNotNull(busy.word)

        // A parcel no carrier knows has no site to compare with.
        val unknown = visit(page(ParcelFeedbackSubject.Parcel(UUID.randomUUID()), ParcelFeedbackQuestion.Unknown, scan = ""), Sent(), store, clock)
        comeBack(unknown, clock)
        assertNull(unknown.word)

        // Already answered for this scan, on this visit or within the day.
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val answered = visit(page(subject), Sent(), store, clock)
        answered.right()
        comeBack(answered, clock)
        assertNull(answered.word)
        assertNull(comeBackOnALaterVisit(subject, store, clock))

        // One of the sheets is open.
        val writing = visit(page(ParcelFeedbackSubject.Parcel(UUID.randomUUID())), Sent(), store, clock)
        writing.notQuite()
        writing.pick(ParcelFeedbackReason.Status)
        writing.addNote()
        comeBack(writing, clock)
        assertNull(writing.word)
    }

    @Test
    fun yesOnTheWayBackThanksInBothPlaces() = runTest {
        val store = MemoryDefaults()
        val clock = Clock()
        val sent = Sent()
        val model = visit(page(ParcelFeedbackSubject.Link(linkId)), sent, store, clock)
        comeBack(model, clock)

        model.right(ParcelFeedbackRequestAsked.Back)

        assertEquals(listOf(ParcelFeedbackAnswer.Right), sent.requests.map { it.answer })
        assertEquals(ParcelFeedbackRequestAsked.Back, sent.requests.first().asked)
        assertEquals(ParcelFeedbackModel.Word.BackRight, model.word)
        assertEquals(4.seconds, ParcelFeedbackModel.Word.BackRight.stay)
        assertEquals(ParcelFeedbackModel.Moment.Right, model.moment)
        assertEquals(listOf("parcel-feedback-right success"), sent.counted)
        assertNull("Not asked again on the way back", comeBackOnALaterVisit(ParcelFeedbackSubject.Link(linkId), store, clock))
        assertEquals("Pip still asks", ParcelFeedbackQuestion.Found, visit(page(ParcelFeedbackSubject.Link(linkId)), Sent(), store, clock).standing)
    }

    @Test
    fun noOnTheWayBackOpensTheSheetWithNothingChosen() = runTest {
        val store = MemoryDefaults()
        val clock = Clock()
        val sent = Sent()
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val model = visit(page(subject), sent, store, clock)
        comeBack(model, clock)

        model.wrongOnReturn()

        assertNull(model.word)
        assertEquals(emptySet<ParcelFeedbackReason>(), model.words?.reasons)
        assertEquals(ParcelFeedbackRequestAsked.Back, model.words?.asked)
        assertTrue(sent.requests.isEmpty())

        // Closed without a word: Pip's question stands as it stood.
        model.words = null
        assertEquals(ParcelFeedbackModel.Moment.Ask, model.moment)
        assertEquals(ParcelFeedbackQuestion.Found, visit(page(subject), Sent(), store, clock).standing)

        model.wrongOnReturn()
        val id = model.words?.id
        val closed = model.sendWords(emptySet(), note = "Their site says it was returned")

        assertTrue(closed)
        assertEquals(1, sent.requests.size)
        assertEquals(id, sent.requests.first().id)
        assertEquals(ParcelFeedbackAnswer.Wrong, sent.requests.first().answer)
        assertNull(sent.requests.first().reasons)
        assertEquals("Their site says it was returned", sent.requests.first().note)
        assertEquals(ParcelFeedbackRequestAsked.Back, sent.requests.first().asked)
        assertEquals(ParcelFeedbackModel.Word.BackSent, model.word)
        assertEquals("Pip thanks too, with nothing to add", ParcelFeedbackModel.Moment.Noted, model.moment)
        assertEquals(listOf("parcel-feedback-wrong success"), sent.counted)
    }

    // A parcel no carrier knows

    @Test
    fun notYetIsAnAnswerThatSendsNothing() = runTest {
        val store = MemoryDefaults()
        val clock = Clock()
        val sent = Sent()
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val model = visit(page(subject, ParcelFeedbackQuestion.Unknown, scan = ""), sent, store, clock)
        assertEquals(ParcelFeedbackQuestion.Unknown, model.standing)

        model.notYet()

        assertEquals(ParcelFeedbackModel.Moment.Early, model.moment)
        assertTrue(sent.requests.isEmpty())
        assertTrue(sent.counted.isEmpty())
        // Tomorrow the carrier's site may show it: the row asks again on the next visit.
        val later = visit(page(subject, ParcelFeedbackQuestion.Unknown, scan = ""), Sent(), store, clock)
        assertEquals(ParcelFeedbackQuestion.Unknown, later.standing)
        assertEquals(ParcelFeedbackModel.Moment.Ask, later.moment)
    }

    @Test
    fun namingTheCarrierSendsItAndThanks() = runTest {
        val store = MemoryDefaults()
        val clock = Clock()
        val sent = Sent()
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val model = visit(page(subject, ParcelFeedbackQuestion.Unknown, scan = ""), sent, store, clock)

        model.elsewhere()
        assertTrue(model.namingCarrier)
        // Closed without sending: back to the question.
        model.namingCarrier = false
        assertEquals(ParcelFeedbackModel.Moment.Ask, model.moment)
        assertEquals(ParcelFeedbackQuestion.Unknown, model.standing)
        assertTrue(sent.requests.isEmpty())

        model.elsewhere()
        sent.fails = true
        val refused = model.sendCarrier(name = "Example Parcels", page = "")
        assertFalse(refused)
        assertTrue("What was typed stays to be sent again", model.namingCarrier)
        assertEquals(ParcelFeedbackModel.Moment.Ask, model.moment)
        assertNull(model.word)

        sent.fails = false
        val closed = model.sendCarrier(name = " Example Parcels ", page = "example.com/track")

        assertTrue(closed)
        assertFalse(model.namingCarrier)
        assertEquals(ParcelFeedbackModel.Moment.Named, model.moment)
        assertEquals(ParcelFeedbackAnswer.FoundElsewhere, sent.requests.last().answer)
        assertEquals("Example Parcels", sent.requests.last().carrierName)
        assertEquals("example.com/track", sent.requests.last().trackingPage)
        assertEquals(listOf("parcel-feedback-carrier error", "parcel-feedback-carrier success"), sent.counted)
        // More may be said later: the row stands on the next visit too.
        assertEquals(ParcelFeedbackQuestion.Unknown, visit(page(subject, ParcelFeedbackQuestion.Unknown, scan = ""), Sent(), store, clock).standing)
        assertEquals(
            "Once it is found, Pip asks about what he shows",
            ParcelFeedbackQuestion.Found,
            visit(page(subject, ParcelFeedbackQuestion.Found), Sent(), store, clock).standing,
        )
    }

    // The page

    @Test
    fun anotherParcelOrACarrierFoundStartsOver() = runTest {
        val store = MemoryDefaults()
        val clock = Clock()
        val sent = Sent()
        val subject = ParcelFeedbackSubject.Parcel(UUID.randomUUID())
        val model = visit(page(subject, ParcelFeedbackQuestion.Unknown, scan = ""), sent, store, clock)
        model.notYet()
        assertEquals(ParcelFeedbackQuestion.Unknown, model.standing)

        // A carrier answers while the page is open: the row's word goes, and Pip asks.
        model.show(page(subject, ParcelFeedbackQuestion.Found), through = sent.client)
        assertEquals(ParcelFeedbackModel.Moment.Ask, model.moment)
        assertEquals(ParcelFeedbackQuestion.Found, model.standing)

        // A new scan leaves an answer given on this visit in place.
        model.right()
        model.show(page(subject, ParcelFeedbackQuestion.Found, scan = "5:2026-10-04T07:15:00Z"), through = sent.client)
        assertEquals(ParcelFeedbackModel.Moment.Right, model.moment)
        assertEquals(ParcelFeedbackQuestion.Found, model.standing)

        // Another parcel has its own question, and none of the first one's sheets.
        model.words = ParcelFeedbackModel.Words(id = UUID.randomUUID(), reasons = emptySet(), asked = ParcelFeedbackRequestAsked.Back)
        model.show(page(ParcelFeedbackSubject.Link(linkId)), through = sent.client)
        assertEquals(ParcelFeedbackModel.Moment.Ask, model.moment)
        assertNull(model.words)

        model.show(null, through = null)
        assertNull(model.standing)
        model.right()
        assertEquals("A page that shows no parcel sends nothing", 1, sent.requests.size)
    }

    /** Swift compares Strings by canonical equivalence: a link, scan or site written either way is the same one. */
    @Test
    fun wordsWrittenInAnotherNormalizationAreTheSame() = runTest {
        val sent = Sent()
        val clock = Clock()
        val composed = page(ParcelFeedbackSubject.Link("caf\u00E9"), scan = "1:caf\u00E9")
        val model = visit(composed, sent, MemoryDefaults(), clock)
        model.notQuite()
        model.show(page(ParcelFeedbackSubject.Link("cafe\u0301"), scan = "1:cafe\u0301"), through = sent.client)
        assertEquals(ParcelFeedbackModel.Moment.Reasons, model.moment)
        assertEquals(composed, model.page)
        assertEquals(composed.hashCode(), page(ParcelFeedbackSubject.Link("cafe\u0301"), scan = "1:cafe\u0301").hashCode())

        comeBack(model, clock, from = "Caf\u00E9")
        assertEquals(ParcelFeedbackModel.Word.Back(site = "Caf\u00E9"), model.word)
        model.wordLeft(ParcelFeedbackModel.Word.Back(site = "Cafe\u0301"))
        assertNull(model.word)
    }

    // The service

    @Test
    fun anAnswerThroughALinkCarriesTheOwnerKeyOnlyWhenThisIPhoneHoldsIt() = runTest {
        var status = 204
        val transport = RecordingTransport { HttpResponse(status, body = if (status == 204) ByteArray(0) else """{"error":"Parcel unavailable"}""".toByteArray()) }
        val followed = UUID.randomUUID()
        val key = "k".repeat(43)
        val storage = object : DeviceParcelStorage {
            override fun load() = DeviceParcelStorage.Contents(
                listOf(DeviceParcel(linkId = linkId, label = "", archivedAt = null, parcel = parcel().copy(id = followed), checkedAt = Instant.now().timeIntervalSinceReferenceDate)),
                mapOf(linkId to key),
            )

            override fun save(parcels: List<DeviceParcel>, keys: Map<String, String>) {}
        }
        val configuration = AppConfiguration(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://feedback.test", supabaseUrl = "https://feedback.test",
            supabasePublishableKey = "public", googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true,
            appGroupIdentifier = "feedback.test",
        )
        val verification = NativeVerification(
            NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) }),
            TurnstilePresenter { throw NativeVerificationFailed() }, emptyMap(), {}, language = { AppLanguage.En },
        )
        val device = DeviceParcels(DeviceParcelClient.api(configuration, transport, verification), storage, PostcodeMemory(MemoryDefaults()), this)
        val answer = ParcelFeedbackRequest.wrong(
            reasons = setOf(ParcelFeedbackReason.Status), asked = ParcelFeedbackRequestAsked.Page, language = AppLanguage.It, app = ios,
        )

        device.sendFeedback(answer, id = followed)

        assertEquals(1, transport.requests.size)
        val request = transport.requests.first()
        assertEquals("POST", request.method)
        assertEquals("/api/public/parcels/$linkId/feedback", request.path)
        assertEquals(key, request.header("X-Parcel-Key"))
        assertNull(request.header("Authorization"))
        val sent = Json.parseToJsonElement(request.body?.decodeToString() ?: "").jsonObject
        assertEquals(answer.id.uuidString, sent["id"]?.jsonPrimitive?.content)
        assertEquals("wrong", sent["answer"]?.jsonPrimitive?.content)
        assertEquals(listOf("status"), sent["reasons"]?.jsonArray?.map { it.jsonPrimitive.content })
        assertEquals("ios", sent["app"]?.jsonPrimitive?.content)
        assertEquals("it", sent["locale"]?.jsonPrimitive?.content)
        // The bytes the iPhone sends: its keys sorted.
        assertEquals(
            """{"answer":"wrong","app":"ios","asked":"page","id":"${answer.id.uuidString}","locale":"it","reasons":["status"]}""",
            request.body?.decodeToString(),
        )

        // Someone else's link: no key to send.
        val right = ParcelFeedbackRequest.right(asked = ParcelFeedbackRequestAsked.Back, language = AppLanguage.En, app = ios)
        device.sendFeedback(right, linkId = "33333333333B")
        assertEquals("/api/public/parcels/33333333333B/feedback", transport.requests.last().path)
        assertNull(transport.requests.last().header("X-Parcel-Key"))
        assertEquals("""{"answer":"right","app":"ios","asked":"back","id":"${right.id.uuidString}","locale":"en"}""", transport.requests.last().body?.decodeToString())

        // A parcel this phone does not follow has no link to answer through.
        try {
            device.sendFeedback(answer, id = UUID.randomUUID())
            fail("An unknown parcel was answered for")
        } catch (_: Exception) {
            assertEquals(2, transport.requests.size)
        }

        // A link that is gone is a failure like any other.
        for (gone in listOf(404, 410)) {
            status = gone
            try {
                device.sendFeedback(answer, linkId = linkId)
                fail("$gone was taken for sent")
            } catch (_: Exception) {
            }
        }
    }

    /**
     * `ParcelStore.feedbackClient(for:)` and `feedbackClient(forLink:)` come with the store's port; this keeps the body
     * an account's answer sends and the rule that a demo's pages, or a link's in a build without a server, ask nothing.
     */
    @Test
    fun theStoreSendsThroughTheAccountAndNothingInABuildWithoutAServer() {
        val parcel = this.parcel()
        val answer = ParcelFeedbackRequest.right(asked = ParcelFeedbackRequestAsked.Page, language = AppLanguage.En, app = ios)
        val json = Json.parseToJsonElement(body(answer)).jsonObject
        assertEquals(answer.id.uuidString, json.getValue("id").jsonPrimitive.content)
        assertEquals("right", json.getValue("answer").jsonPrimitive.content)

        // Nor do their pages ask: the demo's stories are not something to judge.
        for (madeUp in listOf(true, false)) {
            val page = ParcelFeedbackModel.Page(
                parcel, subject = ParcelFeedbackSubject.Parcel(parcel.id), showsHistory = true, madeUp = madeUp, carrier = "DHL", language = AppLanguage.En,
            )
            val link = ParcelFeedbackModel.Page(
                parcel, subject = ParcelFeedbackSubject.Link(linkId), showsHistory = true, madeUp = madeUp, carrier = "DHL", language = AppLanguage.En,
            )
            assertEquals(madeUp, page == null)
            assertEquals(madeUp, link == null)
        }
    }

    @Test
    fun aMadeUpParcelIsAskedNothing() = runTest {
        val store = MemoryDefaults()
        val parcel = this@ParcelFeedbackTests.parcel()
        fun page(parcel: Parcel, madeUp: Boolean, showsHistory: Boolean = true): ParcelFeedbackModel.Page? =
            ParcelFeedbackModel.Page(
                parcel, subject = ParcelFeedbackSubject.Parcel(parcel.id), showsHistory = showsHistory, madeUp = madeUp, carrier = "DHL",
                language = AppLanguage.De,
            )

        assertEquals(
            ParcelFeedbackModel.Page(
                ParcelFeedbackSubject.Parcel(parcel.id), ParcelFeedbackQuestion.Found, scan = "1:2026-10-02T08:00:00Z", carrier = "DHL",
                language = AppLanguage.De,
            ),
            page(parcel, madeUp = false),
        )
        assertEquals(ParcelFeedbackQuestion.Unknown, page(this@ParcelFeedbackTests.parcel(carrier = CarrierId.Unknown, stage = null), madeUp = false, showsHistory = false)?.question)
        assertEquals("", page(this@ParcelFeedbackTests.parcel(carrier = CarrierId.Unknown, stage = null), madeUp = false, showsHistory = false)?.scan)
        assertNull(page(this@ParcelFeedbackTests.parcel(archived = true), madeUp = false))

        // The demo's stories: whatever they show, nothing stands on their pages.
        assertNull(page(parcel, madeUp = true))
        assertNull(page(this@ParcelFeedbackTests.parcel(carrier = CarrierId.Unknown, stage = null), madeUp = true, showsHistory = false))
        val sent = Sent()
        val clock = Clock()
        val model = visit(this@ParcelFeedbackTests.page(ParcelFeedbackSubject.Parcel(parcel.id)), sent, store, clock)
        assertEquals(ParcelFeedbackQuestion.Found, model.standing)
        model.show(page(parcel, madeUp = true), through = sent.client)
        assertNull(model.standing)
        comeBack(model, clock)
        assertNull(model.word)
    }

    @Test
    fun answersAreCountedWhereTheyAreGiven() {
        val catalog = AnalyticsCatalog.bundled!!
        for (action in listOf("parcel-feedback-right", "parcel-feedback-wrong", "parcel-feedback-carrier")) {
            assertTrue(action, catalog.actions.contains(action))
            assertFalse("$action is counted where it happens", catalog.operations.any { it.event == action })
        }
        // A note added to an answer goes over the same route: the route itself counts nothing.
        assertNull(catalog.event(path = "/api/packages/43000000-0000-0000-0000-000000000006/feedback", method = "POST", body = null))
    }

    /** The words the question's views say (ParcelFeedbackViews.swift), in every language the app speaks. */
    @Test
    fun theViewsWordsAreInEveryLanguage() {
        val keys = ParcelFeedbackReason.entries.map { it.labelKey } + ParcelFeedbackModel.Moment.entries.mapNotNull { it.thanksKey }
        assertEquals(6 + 5, keys.size)
        for (language in AppLanguage.entries) {
            val localizer = Localizer(language)
            for (key in keys) assertNotEquals("$key in ${language.rawValue}", key, localizer.text(key))
            for (word in listOf(ParcelFeedbackModel.Word.BackRight, ParcelFeedbackModel.Word.BackSent, ParcelFeedbackModel.Word.Failed)) {
                assertFalse("$word in ${language.rawValue}", localizer.text(word).startsWith("feedback."))
            }
            val back = localizer.text(ParcelFeedbackModel.Word.Back(site = "Swiss Post"))
            assertTrue(back, back.contains("Swiss Post") && !back.contains("{{"))
        }
    }
}
