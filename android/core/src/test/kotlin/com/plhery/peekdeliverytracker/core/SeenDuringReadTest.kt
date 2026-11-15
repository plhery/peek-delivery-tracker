package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import com.plhery.peekdeliverytracker.core.api.PackageSeenResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import java.time.Instant
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * An account's parcel opened while a read of the list is in flight (#72): the read lands, so the open page shows the
 * scan it brings, and the parcel stays seen as late as this phone marked it. The phone tells the service which news
 * the page showed, so a scan stored meanwhile stays new until the page shows it. Synthetic numbers.
 */
class SeenDuringReadTest {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://news.test", supabaseUrl = "https://news.test", supabasePublishableKey = "public",
        googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "news.test",
    )
    private val now = Instant.parse("2026-09-08T12:00:00Z")
    private val parcelId = UUID.randomUUID()
    private val accountId = UUID.randomUUID()

    // Moments as the server writes them.
    private val opened = "2026-09-08T09:00:00.250000+00:00"
    private val scanned = "2026-09-08T10:15:30.123456+00:00"
    /** A scan the carrier gave while the app was away, which the read on coming back brings. */
    private val later = "2026-09-08T10:45:00.000000+00:00"
    private val seenNow = "2026-09-08T11:00:00.654321+00:00"

    /** What the server keeps for news shown at [news]: the end of that millisecond (POST /api/packages/{id}/seen). */
    private fun seenAsOf(news: String): String =
        OffsetDateTime.parse(news).truncatedTo(ChronoUnit.MILLIS).plusNanos(999_000).toInstant().toString()

    private inner class Server {
        var newsAt: String? = scanned
        var seenAt: String = opened
        var seenFails = false

        /** The `news_at` each seen call named. */
        val named = mutableListOf<String?>()

        /** When set, a read of the list answers with what the server held when it was asked, once this completes. */
        var readGate: CompletableDeferred<Unit>? = null

        /** When set, the seen call answers once this completes. */
        var seenGate: CompletableDeferred<Unit>? = null

        fun parcel() = Parcel(
            id = parcelId, trackingNumber = "TESTPARCEL123789", label = "Synthetic parcel", carrier = CarrierId.Dhl,
            createdAt = "2026-09-07T08:00:00Z", syncStatus = SyncStatus.Ok, notificationsMuted = false, lastSyncedAt = "2026-09-08T10:46:00Z",
            newsAt = newsAt, seenAt = seenAt,
        )

        suspend fun answer(request: HttpRequest): HttpResponse = when {
            request.path == "/api/packages" && request.method == "GET" -> {
                val list = PackageListResponse(listOf(parcel()))
                val read = jsonResponse(200, JsonEncoder.deliveryTracker.encode(PackageListResponse.serializer(), list).decodeToString())
                readGate?.await()
                read
            }
            request.path == "/api/packages/${parcelId.uuidString}/seen" && request.method == "POST" -> {
                val news = request.body?.let { Json.parseToJsonElement(it.decodeToString()).jsonObject["news_at"]?.jsonPrimitive?.contentOrNull }
                named += news
                seenGate?.await()
                if (seenFails) {
                    jsonResponse(500, """{"error":"unavailable"}""")
                } else {
                    // Never later than now, nor earlier than the parcel was seen already.
                    val asOf = news?.let(::seenAsOf)?.takeIf { DateParser.date(it)!! < DateParser.date(seenNow)!! } ?: seenNow
                    if (DateParser.date(asOf)!! > DateParser.date(seenAt)!!) seenAt = asOf
                    jsonResponse(200, JsonEncoder.deliveryTracker.encode(PackageSeenResponse.serializer(), PackageSeenResponse(seenAt)).decodeToString())
                }
            }
            else -> throw AssertionError("Unexpected request ${request.method} ${request.url}")
        }
    }

    private suspend fun TestScope.signedIn(server: Server): ParcelStore {
        val transport = RecordingTransport()
        val session = testSessionStore(configuration, backgroundScope, transport = transport, now = { now })
        val user = AuthUser(id = accountId, email = "reader@example.com", isAnonymous = false)
        val auth = AuthSession(
            accessToken = "token-${UUID.randomUUID()}", tokenType = "bearer", expiresIn = 3600, expiresAt = now.epochSecond + 3600,
            refreshToken = "refresh", user = user,
        )
        transport.handler = { jsonResponse(200, JsonEncoder.deliveryTracker.encode(AuthSession.serializer(), auth).decodeToString()) }
        session.verifyCode(email = "reader@example.com", code = "123456")
        transport.handler = { server.answer(it) }
        val store = testParcelStore(configuration, session, transport, backgroundScope, MemoryDefaults(), now = { now })
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)
        return store
    }

    private val ParcelStore.shown: Parcel get() = parcels.single { it.id == parcelId }

    /** The phone holds the parcel with a dot for its scan at `scanned`; the carrier has since given one at `later`. */
    private suspend fun TestScope.holding(server: Server): ParcelStore {
        val store = signedIn(server)
        store.load()
        assertTrue("The parcel held has a dot", store.shown.hasNewUpdate)
        server.newsAt = later
        return store
    }

    /**
     * The read is in flight when the parcel is opened, and lands once the server has stored the seen moment. The page
     * then shows the newer scan, still new, and seeing it marks it seen in turn.
     */
    @Test
    fun openingAParcelDuringTheReadKeepsTheReadsNewScan() = runTest {
        val server = Server()
        val store = holding(server)
        val gate = CompletableDeferred<Unit>().also { server.readGate = it }
        val reading = launch { store.load() }
        runCurrent()
        store.markSeen(store.shown)
        assertEquals("The seen call names the news the page showed", listOf<String?>(scanned), server.named)
        gate.complete(Unit)
        reading.join()
        assertEquals("The open page shows the scan the read brought", later, store.shown.newsAt)
        assertEquals("The parcel stays seen at the server's moment, not the read's older one", seenAsOf(scanned), store.shown.seenAt)
        assertTrue("The scan stored while the parcel opened is still new", store.shown.hasNewUpdate)

        store.markSeen(store.shown)
        assertEquals(listOf<String?>(scanned, later), server.named)
        assertEquals(seenAsOf(later), store.shown.seenAt)
        assertFalse(store.shown.hasNewUpdate)
    }

    /**
     * The read lands while the seen call is under way: it keeps the moment the parcel was marked with here rather than
     * the read's older one, so the newer scan it brings is news, and stays news once the server answers.
     */
    @Test
    fun aReadLandingBeforeTheSeenCallAnswersKeepsTheMarkMadeHere() = runTest {
        val server = Server()
        val store = holding(server)
        val gate = CompletableDeferred<Unit>().also { server.seenGate = it }
        val opening = launch { store.markSeen(store.shown) }
        runCurrent()
        store.load()
        assertEquals(later, store.shown.newsAt)
        assertEquals(scanned, store.shown.seenAt)
        assertTrue(store.shown.hasNewUpdate)
        gate.complete(Unit)
        opening.join()
        assertEquals(seenAsOf(scanned), store.shown.seenAt)
        assertTrue(store.shown.hasNewUpdate)

        // A later read with the server's moment leaves nothing marked here.
        store.load()
        assertEquals(seenAsOf(scanned), store.shown.seenAt)
    }

    /** A page that showed older news than the phone holds, seen later already, doesn't move the parcel's seen moment back. */
    @Test
    fun anOlderPageNeverMovesTheSeenMomentBack() = runTest {
        val server = Server()
        val store = holding(server)
        val page = store.shown
        store.load()
        store.markSeen(store.shown)
        assertEquals(seenAsOf(later), store.shown.seenAt)
        server.newsAt = "2026-09-08T10:50:00.000000+00:00"
        store.load()
        assertTrue(store.shown.hasNewUpdate)
        store.markSeen(page)
        assertEquals(seenAsOf(later), store.shown.seenAt)
    }

    /** A seen call that fails says nothing, and the next read brings what the server holds. */
    @Test
    fun aFailedSeenCallLeavesTheNextReadToTheServer() = runTest {
        val server = Server().apply { seenFails = true }
        val store = holding(server)
        store.markSeen(store.shown)
        assertFalse("The dot goes at once", store.shown.hasNewUpdate)
        store.load()
        assertEquals(opened, store.shown.seenAt)
        assertTrue(store.shown.hasNewUpdate)
    }

    /** Control: the same read, without the parcel being opened, brings the new scan. */
    @Test
    fun controlTheSameReadWithoutOpeningBringsTheNewScan() = runTest {
        val server = Server()
        val store = holding(server)
        val gate = CompletableDeferred<Unit>().also { server.readGate = it }
        val reading = launch { store.load() }
        runCurrent()
        gate.complete(Unit)
        reading.join()
        assertEquals(later, store.shown.newsAt)
        assertTrue(store.shown.hasNewUpdate)
    }
}
