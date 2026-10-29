package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestApp
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestAsked
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * ReviewPromptTests.swift: when the app asks for a rating, at a calm moment, from the third day with use, once per
 * version, a month apart at the soonest and never on a day something went wrong; and the "Yes" on a parcel's page that
 * makes the moment.
 */
class ReviewPromptTests {
    private class Clock(var now: Instant)

    private val start = Instant.parse("2026-10-01T09:00:00Z")
    private val saoPaulo = ZoneId.of("America/Sao_Paulo")

    private fun date(text: String): Instant = Instant.parse(text)

    private fun day(days: Int, hours: Double = 0.0): Instant =
        start.plusSeconds(days * 86_400L).plusNanos(Math.round(hours * 3_600 * 1_000_000_000))

    // The rule

    @Test
    fun asksOnTheThirdDayWithUseThenNotAgain() {
        val prompt = ReviewPrompt(MemoryDefaults(), version = "1.0", timeZone = { ZoneOffset.UTC })
        assertFalse(prompt.isDue(at = day(0), calm = true))
        // Days without use do not count.
        assertFalse(prompt.isDue(at = day(5), calm = true))
        assertTrue(prompt.isDue(at = day(9), calm = true))
        prompt.asked(at = day(9))
        assertFalse(prompt.isDue(at = day(10), calm = true))
        assertFalse(prompt.isDue(at = day(90), calm = true))
    }

    @Test
    fun staysDueUntilTheAppAsks() {
        val prompt = ReviewPrompt(MemoryDefaults(), version = "1.0", timeZone = { ZoneOffset.UTC })
        // A day the parcels were in front of the reader counts as much as a good moment.
        prompt.used(at = day(0))
        assertFalse(prompt.isDue(at = day(1), calm = true))
        assertTrue(prompt.isDue(at = day(2), calm = true))
        assertTrue(prompt.isDue(at = day(2, hours = 1.0), calm = true))
        assertTrue(prompt.take(at = day(2, hours = 1.0), calm = true))
        assertFalse(prompt.isDue(at = day(2, hours = 2.0), calm = true))
        assertFalse(prompt.take(at = day(2, hours = 2.0), calm = true))
    }

    @Test
    fun countsTheReadersOwnDays() {
        val store = MemoryDefaults()
        val prompt = ReviewPrompt(store, version = "1.0", timeZone = { saoPaulo })
        // 22:00 on the 1st and 01:00 on the 2nd in São Paulo: two days there, one in UTC.
        prompt.used(at = date("2026-10-02T01:00:00Z"))
        assertFalse(prompt.isDue(at = date("2026-10-02T04:00:00Z"), calm = true))
        // Noon on the 2nd is the same day there.
        assertFalse(prompt.isDue(at = date("2026-10-02T15:00:00Z"), calm = true))
        // Noon and 20:00 on the 3rd: the third day, counted once.
        assertTrue(prompt.isDue(at = date("2026-10-03T15:00:00Z"), calm = true))
        prompt.asked(at = date("2026-10-03T15:00:00Z"))
        assertFalse(prompt.isDue(at = date("2026-10-03T23:00:00Z"), calm = true))
        // 21:00 on 1 November there is 29 days after the ask; 01:00 on the 2nd is 30, though both are the 2nd in UTC.
        val next = ReviewPrompt(store, version = "1.1", timeZone = { saoPaulo })
        assertFalse(next.isDue(at = date("2026-11-02T00:00:00Z"), calm = true))
        assertTrue(next.isDue(at = date("2026-11-02T04:00:00Z"), calm = true))
    }

    @Test
    fun waitsForACalmMoment() {
        val prompt = ReviewPrompt(MemoryDefaults(), version = "1.0", timeZone = { ZoneOffset.UTC })
        for (days in 0 until 2) {
            assertFalse(prompt.isDue(at = day(days), calm = true))
        }
        assertFalse(prompt.isDue(at = day(2), calm = false))
        assertFalse(prompt.take(at = day(2, hours = 1.0), calm = false))
        assertTrue(prompt.isDue(at = day(2, hours = 2.0), calm = true))
    }

    @Test
    fun eachVersionAsksOnceAMonthAfterTheLastAsk() {
        val store = MemoryDefaults()
        val first = ReviewPrompt(store, version = "1.0", timeZone = { ZoneOffset.UTC })
        for (days in 0 until 2) {
            assertFalse(first.isDue(at = day(days), calm = true))
        }
        assertTrue(first.take(at = day(2), calm = true))

        // A new version the next day waits for the month to pass.
        val next = ReviewPrompt(store, version = "1.1", timeZone = { ZoneOffset.UTC })
        assertFalse(next.isDue(at = day(3), calm = true))
        assertFalse(next.isDue(at = day(2 + ReviewPrompt.gap - 1), calm = true))
        assertTrue(next.isDue(at = day(2 + ReviewPrompt.gap), calm = true))
        next.asked(at = day(2 + ReviewPrompt.gap))
        assertFalse(next.isDue(at = day(2 + 3 * ReviewPrompt.gap), calm = true))
    }

    @Test
    fun nothingIsAskedOnADayThatWentWrong() {
        val prompt = ReviewPrompt(MemoryDefaults(), version = "1.0", timeZone = { ZoneOffset.UTC })
        prompt.used(at = day(0))
        prompt.used(at = day(1))
        prompt.noteBadMoment(at = day(2))
        assertFalse(prompt.isDue(at = day(2, hours = 3.0), calm = true))
        assertFalse(prompt.take(at = day(2, hours = 14.0), calm = true))
        // The next day starts afresh.
        assertTrue(prompt.isDue(at = day(3), calm = true))
    }

    // A parcel's page

    @Test
    fun onlyAParcelWithNothingWrongIsCalm() {
        val now = date("2026-10-03T09:00:00Z")
        assertTrue(parcel().untroubled(now = now))
        assertFalse(parcel(carrier = CarrierId.Unknown).untroubled(now = now))
        for (sync in listOf(SyncStatus.Error, SyncStatus.Unsupported, SyncStatus.Waiting)) {
            assertFalse(sync.rawValue, parcel(sync = sync).untroubled(now = now))
        }
        for (stage in listOf(TrackingStage.FailedAttempt, TrackingStage.Exception, TrackingStage.Customs, TrackingStage.ReadyForPickup)) {
            assertFalse(stage.rawValue, parcel(stage = stage).untroubled(now = now))
        }
        // Five days without a scan: stalled.
        assertFalse(parcel().untroubled(now = date("2026-10-07T09:00:00Z")))
        // The page says so of its parcel.
        val delivered = parcel(stage = TrackingStage.Delivered)
        assertEquals(true, page(of = delivered)?.untroubled)
        assertEquals(false, page(of = parcel(stage = TrackingStage.Delivered, sync = SyncStatus.Error))?.untroubled)
    }

    @Test
    fun aYesTheServiceTookOnACalmParcelIsTheMomentToAsk() = runTest {
        val store = MemoryDefaults()
        val clock = Clock(day(2))
        usedTwoDays(store)

        val model = visit(page(), store, clock)
        model.right()
        assertTrue(model.reviewDue)
        assertTrue(model.takeReviewMoment())
        assertFalse(model.reviewDue)
        assertFalse(model.takeReviewMoment())

        // This version has asked: another "Yes" is just a "Yes".
        clock.now = day(3)
        val again = visit(page(), store, clock)
        again.right()
        assertFalse(again.reviewDue)
    }

    @Test
    fun theWayBackAsksOnceItsThanksHasGone() = runTest {
        val store = MemoryDefaults()
        val clock = Clock(day(2))
        usedTwoDays(store)

        val model = visit(page(), store, clock)
        model.right(ParcelFeedbackRequestAsked.Back)
        assertEquals(ParcelFeedbackModel.Word.BackRight, model.word)
        assertTrue(model.reviewDue)
        model.wordLeft(ParcelFeedbackModel.Word.BackRight)
        assertTrue(model.takeReviewMoment())
    }

    @Test
    fun aParcelInTroubleAsksNothing() = runTest {
        val store = MemoryDefaults()
        val clock = Clock(day(2))
        usedTwoDays(store)

        val model = visit(page(untroubled = false), store, clock)
        model.right()
        assertFalse(model.reviewDue)
        // A day that went well otherwise still asks at the next calm "Yes".
        val calm = visit(page(), store, clock)
        calm.right()
        assertTrue(calm.reviewDue)
    }

    @Test
    fun aNotQuiteOrAFailedSendKeepsTheDayFromAsking() = runTest {
        val store = MemoryDefaults()
        val clock = Clock(day(2))
        usedTwoDays(store)

        val off = visit(page(), store, clock)
        off.notQuite()
        val model = visit(page(), store, clock)
        model.right()
        assertFalse(model.reviewDue)

        clock.now = day(3)
        val failing = visit(page(), store, clock, fails = true)
        failing.right()
        assertEquals(ParcelFeedbackModel.Word.Failed, failing.word)
        assertFalse(failing.reviewDue)
        val later = visit(page(), store, clock)
        later.right()
        assertFalse(later.reviewDue)

        clock.now = day(4)
        val elsewhere = visit(page(ParcelFeedbackQuestion.Unknown), store, clock)
        elsewhere.elsewhere()
        val next = visit(page(), store, clock)
        next.right()
        assertFalse(next.reviewDue)

        clock.now = day(5)
        val fine = visit(page(), store, clock)
        fine.right()
        assertTrue(fine.reviewDue)
    }

    @Test
    fun daysAreCountedInTheZoneTheDeviceIsInAtEachUse() {
        var zone: ZoneId = ZoneId.of("Asia/Tokyo")
        val prompt = ReviewPrompt(MemoryDefaults(), version = "1.0", timeZone = { zone })
        val stayed = ReviewPrompt(MemoryDefaults(), version = "1.0", timeZone = { ZoneId.of("Asia/Tokyo") })
        for (each in listOf(prompt, stayed)) {
            each.used(at = date("2026-10-01T03:00:00Z"))
            each.used(at = date("2026-10-02T03:00:00Z"))
            // 23:00 on the 3rd in Tokyo: something went wrong.
            each.noteBadMoment(at = date("2026-10-03T14:00:00Z"))
        }
        // The reader flies to Los Angeles, where it is 19:00 on the 3rd: still the day that went wrong, as on an
        // iPhone, which makes a ReviewPrompt in the device's zone at each use. In Tokyo it was the 4th.
        zone = ZoneId.of("America/Los_Angeles")
        assertFalse(prompt.isDue(at = date("2026-10-04T02:00:00Z"), calm = true))
        assertTrue(stayed.isDue(at = date("2026-10-04T02:00:00Z"), calm = true))
        // 01:00 on the 4th there.
        assertTrue(prompt.isDue(at = date("2026-10-04T08:00:00Z"), calm = true))
    }

    // The ask (the iPhone's `askingForReview` modifier, its StoreKit call a `ReviewRequester` here)

    /** Play's two steps, noted in `calls` with the analytics action: its review info comes after `fetch`, if at all. */
    private class Requester(val calls: MutableList<String>, var fetch: Duration = Duration.ZERO, var prepares: () -> Boolean = { true }) :
        ReviewRequester {
        override suspend fun prepare(): Boolean {
            calls += "prepare"
            delay(fetch)
            return prepares()
        }

        override fun launch() {
            calls += "launch"
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun theScreenAsksAMomentAfterItIsReadyAndCountsTheAsk() = runTest {
        val calls = mutableListOf<String>()
        val requester = Requester(calls)
        var takes = 0
        val take = { takes += 1; true }

        // Not due, or not ready: nothing waits, and nothing is fetched.
        ReviewPrompt.ask(due = false, ready = true, take = take, requester = requester) { calls += it }
        ReviewPrompt.ask(due = true, ready = false, take = take, requester = requester) { calls += it }
        assertEquals(0, takes)
        assertEquals(emptyList<String>(), calls)

        // `take` has the last word.
        ReviewPrompt.ask(due = true, ready = true, take = { takes += 1; false }, requester = requester) { calls += it }
        assertEquals(1, takes)
        assertEquals(listOf("prepare"), calls)

        // Play's review info is fetched while the moment settles: the ask comes no later for it.
        calls.clear()
        requester.fetch = 1.seconds
        val started = currentTime
        ReviewPrompt.ask(due = true, ready = true, take = take, requester = requester) { calls += it }
        assertEquals(ReviewPrompt.settle.inWholeMilliseconds, currentTime - started)
        assertEquals(2, takes)
        assertEquals(listOf("prepare", "launch", "review-ask"), calls)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun aScreenThatStopsBeingReadyWhileTheMomentSettlesAsksNothing() = runTest {
        val calls = mutableListOf<String>()
        var takes = 0
        val take = { takes += 1; true }

        val waiting = launch { ReviewPrompt.ask(due = true, ready = true, take = take, requester = Requester(calls)) { calls += it } }
        advanceTimeBy(ReviewPrompt.settle - 1.milliseconds)
        waiting.cancel()
        advanceUntilIdle()
        assertEquals(0, takes)
        assertEquals(listOf("prepare"), calls)

        // Nor while Play is still fetching, after the moment has settled.
        calls.clear()
        val fetching = launch {
            ReviewPrompt.ask(due = true, ready = true, take = take, requester = Requester(calls, fetch = 5.seconds)) { calls += it }
        }
        advanceTimeBy(3.seconds)
        fetching.cancel()
        advanceUntilIdle()
        assertEquals(0, takes)
        assertEquals(listOf("prepare"), calls)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun aPlayStoreThatCannotShowThePromptKeepsTheVersionsAsk() = runTest {
        val store = MemoryDefaults()
        usedTwoDays(store)
        val prompt = ReviewPrompt(store, version = "1.0.2", timeZone = { ZoneOffset.UTC })
        val take = { prompt.take(at = day(2), calm = true) }
        val calls = mutableListOf<String>()

        // No Play Store, an outdated one, no connection or an app not installed from Play: nothing is taken or counted.
        val failing = Requester(calls, prepares = { false })
        ReviewPrompt.ask(due = true, ready = true, take = take, requester = failing) { calls += it }
        assertEquals(listOf("prepare"), calls)
        assertTrue("The version has not asked", prompt.isDue(at = day(2), calm = true))

        calls.clear()
        val throwing = Requester(calls, fetch = 3.seconds, prepares = { throw IllegalStateException("Play Store not found") })
        val started = currentTime
        ReviewPrompt.ask(due = true, ready = true, take = take, requester = throwing) { calls += it }
        assertEquals(3_000L, currentTime - started)
        assertEquals(listOf("prepare"), calls)
        assertTrue(prompt.isDue(at = day(2), calm = true))

        // Once Play has its review info, the ask is taken and counted, then shown.
        calls.clear()
        ReviewPrompt.ask(due = true, ready = true, take = take, requester = Requester(calls)) { calls += it }
        assertEquals(listOf("prepare", "launch", "review-ask"), calls)
        assertFalse(prompt.isDue(at = day(2), calm = true))
    }

    // Helpers

    @Test
    fun onlySomeonesOwnCurrentParcelsWithNothingOverThemAreSettled() {
        fun settled(
            session: AuthenticationState = AuthenticationState.Welcome,
            hasParcels: Boolean = true,
            loading: Boolean = false,
            errorMessage: String? = null,
            authenticationRequired: Boolean = false,
            usingCachedData: Boolean = false,
            notificationEnableInProgress: Boolean = false,
            linkOpen: Boolean = false,
            invitationShown: Boolean = false,
            asksInDemo: Boolean = false,
        ) = ReviewPrompt.settled(
            session, hasParcels, loading, errorMessage, authenticationRequired, usingCachedData, notificationEnableInProgress, linkOpen,
            invitationShown, asksInDemo,
        )
        assertTrue(settled())
        assertTrue(settled(session = AuthenticationState.SignedIn(AuthUser(UUID.randomUUID())), hasParcels = false))
        // The first screen, and the demo's made-up parcels but for a debug build's forced ask.
        assertFalse(settled(hasParcels = false))
        assertFalse(settled(session = AuthenticationState.Demo))
        assertTrue(settled(session = AuthenticationState.Demo, asksInDemo = true))
        for (session in listOf(AuthenticationState.Loading, AuthenticationState.SignedOut, AuthenticationState.Unconfigured)) {
            assertFalse(settled(session = session))
        }
        assertFalse(settled(loading = true))
        assertFalse(settled(errorMessage = "Offline"))
        assertFalse(settled(authenticationRequired = true))
        assertFalse(settled(usingCachedData = true))
        assertFalse(settled(notificationEnableInProgress = true))
        assertFalse(settled(linkOpen = true))
        assertFalse(settled(invitationShown = true))
    }

    private fun usedTwoDays(store: UserDefaults) {
        val prompt = ReviewPrompt(store, version = "1.0.2", timeZone = { ZoneOffset.UTC })
        prompt.used(at = day(0))
        prompt.used(at = day(1))
    }

    private fun parcel(carrier: CarrierId = CarrierId.Dhl, stage: TrackingStage = TrackingStage.InTransit, sync: SyncStatus = SyncStatus.Ok): Parcel {
        val id = UUID.randomUUID()
        return Parcel(
            id = id, trackingNumber = "TESTPARCEL123789", label = "", carrier = carrier, createdAt = "2026-10-01T08:00:00Z",
            syncStatus = sync, archivedAt = null, notificationsMuted = false,
            trackingEvents = listOf(TrackingEvent(id = UUID.randomUUID(), packageId = id, stage = stage, description = "", occurredAt = "2026-10-02T08:00:00Z")),
        )
    }

    private fun page(of: Parcel): ParcelFeedbackModel.Page? = ParcelFeedbackModel.Page(
        of, subject = ParcelFeedbackSubject.Parcel(of.id), showsHistory = true, madeUp = false, carrier = "DHL", language = AppLanguage.En,
    )

    private fun page(question: ParcelFeedbackQuestion = ParcelFeedbackQuestion.Found, untroubled: Boolean = true): ParcelFeedbackModel.Page =
        ParcelFeedbackModel.Page(
            ParcelFeedbackSubject.Parcel(UUID.randomUUID()), question, scan = "1:2026-10-02T08:00:00Z", carrier = "DHL", language = AppLanguage.En,
            untroubled = untroubled,
        )

    /** A parcel's page as one visit holds it, over the same store as the others, its answers run in the scope given. */
    private fun CoroutineScope.visit(page: ParcelFeedbackModel.Page, store: UserDefaults, clock: Clock, fails: Boolean = false): ParcelFeedbackModel {
        val model = ParcelFeedbackModel(
            scope = this, memory = ParcelFeedbackMemory(store) { clock.now }, now = { clock.now }, report = { _, _ -> },
            review = ReviewPrompt(store, version = "1.0.2", timeZone = { ZoneOffset.UTC }), app = ParcelFeedbackRequestApp.Ios,
        )
        model.show(page, through = ParcelFeedbackClient { if (fails) throw DeliveryApiError.ServiceFailed(500) })
        return model
    }
}
