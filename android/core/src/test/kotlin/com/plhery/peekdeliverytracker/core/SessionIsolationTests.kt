package com.plhery.peekdeliverytracker.core

import java.io.IOException
import java.util.Locale
import java.util.UUID
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/**
 * SessionIsolationTests in ParcelLogicTests.swift: the ones that need the API client alone. Those that need
 * SessionStore or ParcelStore come with their ports.
 */
class SessionIsolationTests {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://session.test", supabaseUrl = "https://session.test", supabasePublishableKey = "public",
        googleAuthEnabled = false, appleAuthEnabled = true, emailOtpEnabled = true, appGroupIdentifier = "session.test",
    )

    /**
     * Adapted: a signed-in `TestSession` stands in for a SessionStore signed in through the test server, and its log
     * for the refresh requests the Swift test's request log would show.
     */
    @Test
    fun appAttestRefusalOfASignedInRequestKeepsTheSession() = runTest {
        val session = TestSession()
        val unsupported = NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) })
        val verification = NativeVerification(
            unsupported, TurnstilePresenter { fail("Unexpected browser check"); "" }, emptyMap(), {}, language = { AppLanguage.En },
        )
        val transport = RecordingTransport {
            jsonResponse(403, """{"error":"Please verify this device and try again."}""", mapOf("X-Lookup-Verification" to "required"))
        }
        val client = DeliveryApiClient(configuration, session, transport, verification, deviceName = "Synthetic phone", region = { Locale.UK })
        try {
            client.queueRefreshAll()
            fail("Expected the refusal")
        } catch (_: NativeVerificationFailed) {
        }
        assertTrue(session.signedIn)
        assertEquals(listOf("/api/sync", "/api/sync"), transport.requests.map { it.path })
        // Nor is the token refreshed: the Swift test's request log holds no request for one.
        assertEquals(listOf("token"), session.log)
    }

    @Test
    fun liveActivityRevocationSurvivesOfflineSignOutAndRestart() = runTest {
        val persistence = MemorySessionPersistence()
        val transport = RecordingTransport { throw IOException("The Internet connection appears to be offline.") }
        val format = ListSerializer(LiveActivityRevocations.Registration.serializer())
        val original = LiveActivityRevocations(configuration, transport, persistence)
        val installationId = UUID.randomUUID()
        val old = original.registration(ownerId = UUID.randomUUID(), installationId = installationId)
        original.queue()
        original.drain()
        val saved = persistence.load()?.let { decodeJson(format, it) }
        assertEquals(true, saved?.firstOrNull()?.pending)

        val restarted = LiveActivityRevocations(configuration, transport, persistence)
        val new = restarted.registration(ownerId = UUID.randomUUID(), installationId = installationId)
        assertNotEquals(old.revocationToken, new.revocationToken)
        transport.handler = { request ->
            assertEquals("/api/live-activities/revoke", request.path)
            assertNull(request.header("Authorization"))
            jsonResponse(200, """{"ok":true}""")
        }
        restarted.drain()
        val remaining = persistence.load()?.let { decodeJson(format, it) }
        assertEquals(listOf(new), remaining)
    }
}
