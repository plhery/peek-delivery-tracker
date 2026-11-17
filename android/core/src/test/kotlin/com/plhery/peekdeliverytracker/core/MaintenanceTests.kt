package com.plhery.peekdeliverytracker.core

import java.io.IOException
import java.net.SocketTimeoutException
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

/** MaintenanceTests in ParcelLogicTests.swift: the server down for maintenance, told from the app refusing or failing. */
class MaintenanceTests {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://peek.example", supabaseUrl = "https://peek.example", supabasePublishableKey = "public",
        googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "peek.example",
    )

    @After
    fun backOnline() {
        Maintenance.deviceOnline = { true }
    }

    private fun answer(status: Int, body: String = ""): Boolean = Maintenance.isServerDown(status, body.toByteArray())

    @Test
    fun aProxysOrCloudflaresErrorTheDatabasesAndTheEdgesAreTheServerDown() {
        for (status in listOf(502, 503, 504, 520, 521, 522, 523, 524, 525, 526, 527, 530)) assertTrue("$status", answer(status))
        assertTrue(answer(502, """{"error":"The delivery database is temporarily unavailable"}"""))
        assertTrue(answer(503, """{"error":"Peek is down for maintenance","maintenance":{"planned":false,"mode":null,"since":null,"backAround":null}}"""))
        assertTrue(answer(503, """{"error":"Supabase authentication is temporarily unavailable"}"""))
        // A 404 the app did not write, as the proxy's while the app starts; never the app's own.
        assertTrue(answer(404, "404 page not found\n"))
        assertFalse(answer(404, """{"error":"Parcel unavailable"}"""))
    }

    @Test
    fun theAppRefusingFailingOrNeverSetUpForSomethingIsNot() {
        for (status in listOf(400, 401, 403, 409, 410, 429, 500)) assertFalse("$status", answer(status, """{"error":"No"}"""))
        assertFalse(answer(503, """{"error":"Push notifications are not configured"}"""))
        assertFalse(answer(503, """{"error":"Amazon Shipping could not be checked. Try again."}"""))
        assertFalse(answer(503, """{"error":"The carrier could not be asked. Try again later."}"""))
        assertFalse(answer(503, """{"error":"Browser verification is temporarily unavailable. Try again shortly."}"""))
    }

    @Test
    fun aRequestWithNoAnswerWhileOnlineIsTheServerDownAndAnOfflinePhoneStaysOffline() = runTest {
        assertEquals(ServerDown(), Maintenance.failure(SocketTimeoutException("timeout")))
        assertEquals(ServerDown(), Maintenance.failure(IOException("Connection reset")))
        val cancelled = CancellationException("Stopped")
        assertSame(cancelled, Maintenance.failure(cancelled))
        val refused = DeliveryApiError.ServiceFailed(500)
        assertSame(refused, Maintenance.failure(refused))

        Maintenance.deviceOnline = { false }
        val offline = IOException("offline")
        assertSame(offline, Maintenance.failure(offline))
    }

    @Test
    fun onlyAnAnnouncedMaintenanceSaysWhenItEnds() {
        assertEquals(
            MaintenanceNotice(planned = true, backAround = DateParser.date("2026-11-17T09:30:00Z")),
            Maintenance.notice("""{"maintenance":{"planned":true,"backAround":"2026-11-17T09:30:00Z"}}""".toByteArray()),
        )
        assertEquals(
            MaintenanceNotice.Unannounced,
            Maintenance.notice("""{"maintenance":{"planned":false,"backAround":"2026-11-17T09:30:00Z"}}""".toByteArray()),
        )
        assertEquals(MaintenanceNotice(planned = true), Maintenance.notice("""{"maintenance":{"planned":true,"backAround":"soon"}}""".toByteArray()))
        assertNull(Maintenance.notice("""{"error":"The delivery database is temporarily unavailable"}""".toByteArray()))
        assertNull(Maintenance.notice("error code: 1033".toByteArray()))
    }

    @Test
    fun aServerDownAnswerCarriesWhatTheEdgeAskedAndAnnounced() {
        val announced = HttpResponse(
            503, mapOf("Retry-After" to "60", "X-Maintenance" to "planned"),
            """{"error":"Peek is down for maintenance","maintenance":{"planned":true,"mode":"soft","since":null,"backAround":"2026-11-17T15:00:00Z"}}""".toByteArray(),
        )
        assertEquals(
            ServerDown(retryAfter = 60.0, notice = MaintenanceNotice(planned = true, backAround = DateParser.date("2026-11-17T15:00:00Z"))),
            Maintenance.serverDown(announced),
        )
        assertEquals(ServerDown(), Maintenance.serverDown(HttpResponse(530, body = "error code: 1033".toByteArray())))
        assertNull(Maintenance.serverDown(HttpResponse(500, body = """{"error":"No"}""".toByteArray())))
    }

    @Test
    fun itIsAskedAgainAsSoonAsTheServerAsksAndAtLeastOnceAMinute() {
        assertEquals(listOf(5.0, 10.0, 20.0, 40.0, 60.0, 60.0), (0..5).map { Maintenance.retryDelay(it) })
        assertEquals(30.0, Maintenance.retryDelay(0, retryAfter = 30.0), 0.0)
        assertEquals(60.0, Maintenance.retryDelay(0, retryAfter = 900.0), 0.0)
    }

    /** `/maintenance.json`, asked without cookies: nothing from a page, or from the app's 404 before the edge is in front. */
    @Test
    fun maintenanceJsonIsReadWithoutCookiesAndNothingFromAPageOrA404() = runTest {
        val answers = mutableListOf<Any>(
            jsonResponse(200, """{"planned":true,"mode":"soft","since":null,"backAround":"2026-11-17T09:30:00Z"}"""),
            jsonResponse(503, """{"error":"x","maintenance":{"planned":false,"mode":null,"since":null,"backAround":null}}"""),
            jsonResponse(404, "<!doctype html><title>Not found</title>"),
            jsonResponse(200, "<!doctype html><title>Peek</title>"),
            IOException("offline"),
        )
        val transport = RecordingTransport {
            val next = answers.removeAt(0)
            if (next is Exception) throw next
            next as HttpResponse
        }
        assertEquals(MaintenanceNotice(planned = true, backAround = DateParser.date("2026-11-17T09:30:00Z")), Maintenance.read(configuration, transport))
        assertEquals(MaintenanceNotice.Unannounced, Maintenance.read(configuration, transport))
        assertNull(Maintenance.read(configuration, transport))
        assertNull(Maintenance.read(configuration, transport))
        assertNull(Maintenance.read(configuration, transport))
        for (request in transport.requests) {
            assertEquals("GET https://peek.example/maintenance.json", "${request.method} ${request.url}")
            assertFalse(request.handlesCookies)
        }
    }

    @Test
    fun theNoticeSaysHowOldTheListIsAndWhenAnAnnouncedMaintenanceEnds() {
        val localizer = Localizer(AppLanguage.En, clockPreference = ClockPreference.TwentyFourHour)
        val now = ZonedDateTime.now(ZoneId.systemDefault()).with(LocalTime.NOON).toInstant()
        val readAt = now.minusSeconds(30 * 60)
        val back = now.plusSeconds(30 * 60)
        assertEquals(
            "Peek is down for a few minutes of maintenance. Your parcels below are from 11:30.",
            localizer.maintenanceText(MaintenanceNotice.Unannounced, readAt = readAt, now = now),
        )
        assertEquals(
            "Scheduled maintenance, back around 12:30. Your parcels below are from 11:30.",
            localizer.maintenanceText(MaintenanceNotice(planned = true, backAround = back), readAt = readAt, now = now),
        )
        assertEquals("Peek is down for a few minutes of maintenance.", localizer.maintenanceText(MaintenanceNotice.Unannounced, readAt = null, now = now))
        assertEquals(
            "Peek is down for a few minutes of maintenance. Your parcels below are from yesterday, 12:00.",
            localizer.maintenanceText(MaintenanceNotice.Unannounced, readAt = now.minusSeconds(86_400), now = now),
        )
        assertEquals("Peek is down for a few minutes of maintenance.", localizer.errorMessage(ServerDown()))
    }
}
