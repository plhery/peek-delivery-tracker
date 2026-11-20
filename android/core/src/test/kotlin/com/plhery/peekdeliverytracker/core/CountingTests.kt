package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.InstallSighting
import java.io.IOException
import java.time.Instant
import java.time.OffsetDateTime
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The counting id (counting.ts on the web): when a sighting goes, what it carries and what is kept, then how the
 * analytics send it beside their events.
 */
class CountingTests {
    private val app = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://delivery.example", supabaseUrl = null, supabasePublishableKey = "",
        googleAuthEnabled = false, appGroupIdentifier = "counting.test", appleAuthEnabled = false, emailOtpEnabled = false,
    )
    private val configurationAnswer =
        """{"endpoint":"https://analytics.example/api/send","hostname":"delivery.example","webWebsite":"1802C52E-466B-47E6-AC7F-F5A497655B1B",""" +
            """"iosWebsite":"2802C52E-466B-47E6-AC7F-F5A497655B1B","androidWebsite":"6F0D6C1E-2B7A-4C55-9E21-3A1B5C7D9E0F"}"""
    private val synthetic = "3f0c9a52-6d1e-4b7a-9c2e-5a8f1d0b7e64"
    private val replacement = "0b5e2f4c-81a7-4d3e-b6c9-2e7f5a1d8c03"
    private val uuid = Regex("^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")

    @Test
    fun theFirstSightingMakesAnId() {
        val plan = InstallCounting.plan(null, at("2026-11-19T08:00:00Z"), signedIn = false) { synthetic }!!
        assertEquals(CountingId(synthetic, "2026-11-19"), plan.id)
        assertEquals(CountingId(synthetic, "2026-11-19", sent = "2026-11-19", signedIn = false), plan.counted)
        assertTrue(uuid.matches(InstallCounting.plan(null, at("2026-11-19T08:00:00Z"), signedIn = true)!!.id.id))
    }

    @Test
    fun anIdIsCountedOnceADayAndAgainOnceSignedIn() {
        val now = at("2026-11-19T21:00:00Z")
        val guest = CountingId(synthetic, "2026-03-02", sent = "2026-11-19", signedIn = false)
        assertNull(plan(guest, now, signedIn = false))
        // Signing in the same day counts the same id again.
        val signingIn = plan(guest, now, signedIn = true)!!
        assertEquals(guest, signingIn.id)
        assertEquals(guest.copy(signedIn = true), signingIn.counted)
        // Signed in already, it waits for tomorrow, whether the reader stays or signs out.
        val account = guest.copy(signedIn = true)
        assertNull(plan(account, now, signedIn = true))
        assertNull(plan(account, now, signedIn = false))
        val tomorrow = at("2026-11-20T00:00:00Z")
        assertEquals(CountingId(synthetic, "2026-03-02", "2026-11-20", false), plan(account, tomorrow, signedIn = false)!!.counted)
        assertEquals(account, plan(account, tomorrow, signedIn = true)!!.id)
        // Made today and not counted yet.
        assertNotNull(plan(CountingId(synthetic, "2026-11-19"), now, signedIn = false))
    }

    @Test
    fun theDayIsUtcs() {
        val stored = CountingId(synthetic, "2026-03-02", sent = "2026-11-19", signedIn = false)
        // Evening in New York is already the next UTC day; past midnight in Paris is still this one.
        assertNotNull(plan(stored, OffsetDateTime.parse("2026-11-19T23:30:00-05:00").toInstant(), signedIn = false))
        assertNull(plan(stored, OffsetDateTime.parse("2026-11-20T00:30:00+02:00").toInstant(), signedIn = false))
    }

    @Test
    fun anIdIsReplacedAfterThirteenMonths() {
        val stored = CountingId(synthetic, "2025-10-19", sent = "2026-11-17", signedIn = true)
        // The day before, the same id.
        val before = InstallCounting.plan(stored, at("2026-11-18T23:59:59Z"), signedIn = false) { replacement }!!
        assertEquals(stored, before.id)
        assertEquals(CountingId(synthetic, "2025-10-19", "2026-11-18", false), before.counted)
        val after = InstallCounting.plan(stored, at("2026-11-19T00:00:00Z"), signedIn = false) { replacement }!!
        assertEquals(CountingId(replacement, "2026-11-19"), after.id)
        assertEquals(CountingId(replacement, "2026-11-19", "2026-11-19", false), after.counted)
    }

    @Test
    fun theIdIsKeptAsJson() {
        val defaults = MemoryDefaults()
        InstallCounting.keep(CountingId(synthetic, "2026-11-19"), defaults)
        assertEquals("""{"id":"$synthetic","made":"2026-11-19"}""", defaults.values[InstallCounting.STORAGE_KEY])
        assertEquals(CountingId(synthetic, "2026-11-19"), InstallCounting.read(defaults))
        val counted = CountingId(synthetic, "2026-11-19", "2026-11-19", true)
        InstallCounting.keep(counted, defaults)
        assertEquals(
            """{"id":"$synthetic","made":"2026-11-19","sent":"2026-11-19","signedIn":true}""",
            defaults.values[InstallCounting.STORAGE_KEY],
        )
        assertEquals(counted, InstallCounting.read(defaults))
        // What is no id reads as none, and a new one is made.
        for (value in listOf(
            "", "null", "[]", """{"made":"2026-11-19"}""", """{"id":"short","made":"2026-11-19"}""", """{"id":"$synthetic"}""",
            """{"id":"$synthetic","made":"19/11/2026"}""", """{"id":"$synthetic","made":"2026-02-30"}""", """{"id":7,"made":"2026-11-19"}""",
        )) {
            defaults.set(value, forKey = InstallCounting.STORAGE_KEY)
            assertNull(value, InstallCounting.read(defaults))
        }
    }

    /** The contract's InstallSighting, and nothing else: Android on a phone, a tablet and a Chromebook alike. */
    @Test
    fun theSightingHasExactlyItsFourFields() {
        val sighting = InstallCounting.sighting(CountingId(synthetic, "2026-11-19"), signedIn = true, parcels = 4)
        assertEquals(
            """{"id":"$synthetic","platform":"android","signedIn":true,"parcels":4}""",
            JsonEncoder().encode(InstallSighting.serializer(), sighting).decodeToString(),
        )
    }

    @Test
    fun aScreenViewCountsTheInstallOnItsOwnRequest() = runTest {
        val defaults = MemoryDefaults()
        val transport = server()
        val analytics = analytics(transport, defaults, parcels = { 3 }, platform = { DeliveryAnalytics.Platform.Chromebook })
        analytics.view("front-door", DeliveryAnalytics.Mode.Anonymous)
        analytics.start()
        runCurrent()
        val seen = transport.sightings.single()
        assertEquals("https://delivery.example/api/public/seen", seen.url)
        assertEquals("POST", seen.method)
        assertEquals(5.0, seen.timeout, 0.0)
        assertFalse(seen.handlesCookies)
        assertEquals(mapOf("Content-Type" to "application/json"), seen.headers)
        val body = seen.json
        assertEquals(setOf("id", "platform", "signedIn", "parcels"), body.keys)
        assertTrue(uuid.matches(body["id"]!!.jsonPrimitive.content))
        assertEquals("android", body["platform"]!!.jsonPrimitive.content)
        assertFalse(body["signedIn"]!!.jsonPrimitive.boolean)
        assertEquals(3, body["parcels"]!!.jsonPrimitive.int)
        // The 204 is kept.
        assertEquals(CountingId(body["id"]!!.jsonPrimitive.content, "2026-11-19", "2026-11-19", false), InstallCounting.read(defaults))
        // Its events went to the collector as before, the app open among them.
        assertEquals(2, transport.requests.count { it.url == "https://analytics.example/api/send" })
    }

    @Test
    fun onlyViewsOutsideTheDemoCountOncePerDayAndSignIn() = runTest {
        val defaults = MemoryDefaults()
        var now = at("2026-11-19T08:00:00Z")
        val transport = server()
        val analytics = analytics(transport, defaults, now = { now })
        analytics.start()
        analytics.action("search")
        analytics.view("deliveries", DeliveryAnalytics.Mode.Demo)
        runCurrent()
        // The app open and an action know nothing of the reader, and the demo is nobody's.
        assertEquals(0, transport.sightings.size)
        analytics.view("front-door", DeliveryAnalytics.Mode.Anonymous)
        analytics.view("passport", DeliveryAnalytics.Mode.Anonymous)
        runCurrent()
        analytics.view("deliveries", DeliveryAnalytics.Mode.Account)
        analytics.view("friends", DeliveryAnalytics.Mode.Account)
        runCurrent()
        analytics.view("front-door", DeliveryAnalytics.Mode.Anonymous)
        runCurrent()
        assertEquals(listOf(false, true), transport.sightings.map { it.json["signedIn"]!!.jsonPrimitive.boolean })
        now = at("2026-11-20T08:00:00Z")
        analytics.view("deliveries", DeliveryAnalytics.Mode.Anonymous)
        runCurrent()
        assertEquals(listOf(false, true, false), transport.sightings.map { it.json["signedIn"]!!.jsonPrimitive.boolean })
        assertEquals(1, transport.sightings.map { it.json["id"]!!.jsonPrimitive.content }.toSet().size)
        assertEquals(CountingId(transport.sightings.first().json["id"]!!.jsonPrimitive.content, "2026-11-19", "2026-11-20", false), InstallCounting.read(defaults))
    }

    @Test
    fun aSightingThatFailedIsTriedAgainWithTheSameId() = runTest {
        val defaults = MemoryDefaults()
        val answers = ArrayDeque<() -> HttpResponse>(listOf({ HttpResponse(500) }, { throw IOException("offline") }, { HttpResponse(429) }))
        val transport = server { (answers.removeFirstOrNull() ?: { HttpResponse(204) })() }
        val analytics = analytics(transport, defaults)
        analytics.start()
        for (screen in listOf("front-door", "deliveries", "passport", "friends", "parcel")) {
            analytics.view(screen, DeliveryAnalytics.Mode.Anonymous)
            runCurrent()
        }
        assertEquals(4, transport.sightings.size)
        assertEquals(1, transport.sightings.map { it.json["id"]!!.jsonPrimitive.content }.toSet().size)
        assertEquals("2026-11-19", InstallCounting.read(defaults)!!.sent)
    }

    @Test
    fun oneSightingGoesAtATime() = runTest {
        val defaults = MemoryDefaults()
        val answer = CompletableDeferred<HttpResponse>()
        val transport = server { answer.await() }
        val analytics = analytics(transport, defaults)
        analytics.start()
        analytics.view("front-door", DeliveryAnalytics.Mode.Anonymous)
        runCurrent()
        analytics.view("deliveries", DeliveryAnalytics.Mode.Account)
        runCurrent()
        assertEquals(1, transport.sightings.size)
        // Kept before it went, and counted once answered.
        assertNull(InstallCounting.read(defaults)!!.sent)
        answer.complete(HttpResponse(204))
        runCurrent()
        assertEquals(false, InstallCounting.read(defaults)!!.signedIn)
        analytics.view("friends", DeliveryAnalytics.Mode.Account)
        runCurrent()
        assertEquals(listOf(false, true), transport.sightings.map { it.json["signedIn"]!!.jsonPrimitive.boolean })
    }

    /** An install whose reader turned statistics off before the switch went keeps them off: nothing is counted. */
    @Test
    fun anInstallWhoseReaderTurnedStatisticsOffStaysOff() = runTest {
        val defaults = MemoryDefaults()
        assertTrue(analytics(server(), defaults).enabled)
        defaults.set(false, forKey = DeliveryAnalytics.PREFERENCE_KEY)
        val transport = server()
        val analytics = analytics(transport, defaults)
        assertFalse(analytics.enabled)
        analytics.view("deliveries", DeliveryAnalytics.Mode.Anonymous)
        analytics.start()
        runCurrent()
        assertTrue(transport.requests.isEmpty())
        assertFalse(defaults.hasObject(InstallCounting.STORAGE_KEY))
    }

    @Test
    fun nothingIsCountedWithoutAConfiguration() = runTest {
        val refused = RecordingTransport { request -> if (request.method == "GET") jsonResponse(200, "null") else HttpResponse(204) }
        analytics(refused).apply {
            view("deliveries", DeliveryAnalytics.Mode.Anonymous)
            start()
            view("passport", DeliveryAnalytics.Mode.Account)
        }
        runCurrent()
        assertEquals(1, refused.requests.size)
    }

    private fun at(text: String): Instant = Instant.parse(text)

    private fun plan(stored: CountingId, now: Instant, signedIn: Boolean) = InstallCounting.plan(stored, now, signedIn) { replacement }

    private fun TestScope.analytics(
        transport: HttpTransport,
        defaults: UserDefaults = MemoryDefaults(),
        parcels: () -> Int = { 0 },
        platform: () -> DeliveryAnalytics.Platform = { DeliveryAnalytics.Platform.Android },
        now: () -> Instant = { at("2026-11-19T08:00:00Z") },
    ): DeliveryAnalytics = DeliveryAnalytics(
        app, transport, defaults, backgroundScope, platform, "1.2", { listOf("en") }, deviceParcels = parcels, now = now,
    )

    /** The configuration, the collector's 200, and `seen` for each sighting. */
    private fun server(seen: suspend (HttpRequest) -> HttpResponse = { HttpResponse(204) }) = RecordingTransport { request ->
        when {
            request.method == "GET" -> jsonResponse(200, configurationAnswer)
            request.path == "/api/public/seen" -> seen(request)
            else -> HttpResponse(200)
        }
    }

    private val RecordingTransport.sightings: List<HttpRequest>
        get() = requests.filter { it.path == "/api/public/seen" }

    private val HttpRequest.json: JsonObject
        get() = Json.parseToJsonElement(body!!.decodeToString()).jsonObject
}
