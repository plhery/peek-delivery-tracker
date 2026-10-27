package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import java.security.MessageDigest
import java.time.Instant
import java.util.Base64
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import org.junit.After
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/**
 * NativeVerificationTests.swift. The server is a transport that answers as the Swift tests' actor does, and every wait
 * runs on the test's virtual clock. An `XCTFail` inside a closure is noted and fails the test once it has run, as
 * XCTest records it and lets the test go on.
 */
class NativeVerificationTests {
    private val failures = mutableListOf<String>()

    private fun xctFail(message: String) {
        synchronized(failures) { failures += message }
    }

    private fun <T> xctAssertEqual(actual: T, expected: T, what: String) {
        if (actual != expected) xctFail("$what: $actual is not $expected")
    }

    @After
    fun nothingUnexpectedHappened() {
        assertEquals(emptyList<String>(), failures)
    }

    private val base = "https://peek.example"

    private fun lookup(): HttpRequest =
        HttpRequest(url = base.appendingPath("api/public/parcels"), method = "POST", body = "{\"trackingNumber\":\"SYNTHETIC\"}".toByteArray())

    private val unsupported =
        NativeAttestProvider(supported = { false }, generateKey = { "unused" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) })

    private fun refresh(): HttpRequest = HttpRequest(url = base.appendingPath("api/sync"), method = "POST")

    private val registered: Map<String, NativeAttestRecord>
        get() = mapOf(base to NativeAttestRecord(keyId = "synthetic-key", appId = "TESTTEAM01.com.example.Peek", registered = true))

    private val neverRegisters = NativeAttestProvider(
        supported = { true },
        generateKey = {
            xctFail("Unexpected new key")
            ""
        },
        attest = { _, _ ->
            xctFail("Unexpected registration")
            ByteArray(0)
        },
        assert = { _, _ ->
            xctFail("Unexpected assertion")
            ByteArray(0)
        },
    )

    private fun verification(
        provider: DeviceAttestation,
        present: TurnstilePresenter,
        records: Map<String, NativeAttestRecord> = emptyMap(),
        save: (Map<String, NativeAttestRecord>) -> Unit = {},
    ): NativeVerification = NativeVerification(provider, present, records, save, language = { AppLanguage.En })

    private fun unexpectedCheck(message: String = "Unexpected browser check") = TurnstilePresenter {
        xctFail(message)
        ""
    }

    private fun sha256(text: String): List<Byte> = MessageDigest.getInstance("SHA-256").digest(text.toByteArray()).toList()

    private fun base64(text: String): String = Base64.getEncoder().encodeToString(text.toByteArray())

    @Test
    fun freeBuildExchangesTokenThroughNativeTransportAndReusesProof() = runTest {
        val server = NativeTestServer()
        val verifier = verification(
            unsupported,
            TurnstilePresenter { url ->
                server.present(url)
                "synthetic-turnstile-token"
            },
        )
        val first = verifier.send(lookup(), base, using = server.transport)
        val second = verifier.send(lookup(), base, using = server.transport)
        assertEquals(201, first.statusCode)
        assertEquals(201, second.statusCode)
        val requests = server.requests
        assertEquals(1, requests.count { it.path == "/api/public/verification" })
        assertEquals(1, server.presented.size)
        assertArrayEquals(lookup().body, requests.last().body)
        assertEquals("1", requests.last().header("X-Native-Verification"))
        assertEquals("synthetic-proof", requests.last().header("X-Lookup-Proof"))
    }

    @Test
    fun cancellationDoesNotRetryOrExchangeToken() = runTest {
        val server = NativeTestServer()
        val verifier = verification(unsupported, TurnstilePresenter { throw CancellationException("The check was closed.") })
        try {
            verifier.send(lookup(), base, using = server.transport)
            fail("Expected cancellation")
        } catch (_: CancellationException) {
        }
        val requests = server.requests
        assertEquals(1, requests.count { it.path == "/api/public/parcels" })
        assertFalse(requests.any { it.path == "/api/public/verification" })
    }

    @Test
    fun disabledVerificationAndRateLimitsNeverPresent() = runTest {
        for (status in listOf(201, 429, 503)) {
            val server = NativeTestServer(initialStatus = status)
            val verifier = verification(unsupported, unexpectedCheck("Unexpected verification"))
            val result = verifier.send(lookup(), base, using = server.transport)
            assertEquals(status, result.statusCode)
            assertEquals(1, server.requests.size)
        }
    }

    @Test
    fun backgroundDetectionNeverPresentsWhileTyping() = runTest {
        val server = NativeTestServer()
        val verifier = verification(unsupported, unexpectedCheck("Unexpected check while typing"))
        try {
            verifier.send(lookup(), base, allowPresentation = false, using = server.transport)
            fail("Expected verification requirement")
        } catch (_: NativeVerificationFailed) {
        }
        assertFalse(server.requests.any { it.path == "/api/public/verification" })
    }

    @Test
    fun failedProofDoesNotLoop() = runTest {
        val server = NativeTestServer(rejectProof = true)
        val verifier = verification(unsupported, TurnstilePresenter { "token" })
        try {
            verifier.send(lookup(), base, using = server.transport)
            fail("Expected rejection")
        } catch (_: NativeVerificationFailed) {
        }
        assertEquals(2, server.requests.count { it.path == "/api/public/parcels" })
    }

    @Test
    fun paidBuildRegistersKeyAndSignsExactPayloadWithoutPresenting() = runTest {
        val server = NativeTestServer(paid = true)
        val provider = NativeAttestProvider(
            supported = { true },
            generateKey = { "synthetic-key" },
            attest = { key, hash ->
                xctAssertEqual(key, "synthetic-key", "attested key")
                xctAssertEqual(hash.toList(), sha256("synthetic-attestation-challenge"), "attested hash")
                "synthetic-attestation".toByteArray()
            },
            assert = { key, hash ->
                val request = HttpRequest(
                    url = "https://peek.example/api/public/parcels", method = "POST", body = "{\"trackingNumber\":\"SYNTHETIC\"}".toByteArray(),
                )
                xctAssertEqual(key, "synthetic-key", "asserted key")
                xctAssertEqual(hash.toList(), sha256(NativeVerification.payload(request, "synthetic-assertion-challenge")), "asserted hash")
                "synthetic-assertion".toByteArray()
            },
        )
        val verifier = verification(provider, unexpectedCheck())
        repeat(2) {
            val result = verifier.send(lookup(), base, using = server.transport)
            assertEquals(201, result.statusCode)
        }
        val requests = server.requests
        assertEquals(1, requests.count { it.path == "/api/public/native/verification" && it.method == "POST" })
        assertEquals(base64("synthetic-assertion"), requests.last().header("X-App-Attest-Assertion"))
        assertNull(requests.last().header("X-Lookup-Proof"))
    }

    @Test
    fun invalidKeyAfterReinstallIsRemovedBeforeFallback() = runTest {
        val server = NativeTestServer(paid = true)
        var removed = 0
        var assertions = 0
        val provider = NativeAttestProvider(
            supported = { true },
            generateKey = {
                xctFail("Unexpected new key during backoff")
                "new"
            },
            attest = { _, _ -> ByteArray(0) },
            assert = { _, _ ->
                assertions += 1
                if (assertions == 1) "synthetic-assertion".toByteArray() else throw DeviceAttestation.InvalidKey()
            },
        )
        val verifier = verification(
            provider, TurnstilePresenter { "token" },
            records = mapOf(base to NativeAttestRecord(keyId = "old", appId = "TESTTEAM01.com.example.Peek", registered = true)),
            save = { records ->
                xctAssertEqual(records.isEmpty(), true, "records left")
                removed += 1
            },
        )
        // Populate the configuration, then invalidate the already registered key.
        verifier.send(lookup(), base, using = server.transport)
        val result = verifier.send(lookup(), base, using = server.transport)
        assertEquals(201, result.statusCode)
        assertEquals("Invalid key removed", 1, removed)
    }

    @Test
    fun appleOutageFallsBackAndConcurrentRequestsShareVerification() = runTest {
        val server = NativeTestServer(paid = true)
        val provider = NativeAttestProvider(
            supported = { true }, generateKey = { throw NativeVerificationFailed() }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) },
        )
        val verifier = verification(
            provider,
            TurnstilePresenter { url ->
                server.present(url)
                "token"
            },
        )
        val first = async { verifier.send(lookup(), base, using = server.transport) }
        val second = async { verifier.send(lookup(), base, using = server.transport) }
        val results = awaitAll(first, second)
        assertTrue(results.all { it.statusCode == 201 })
        assertEquals(1, server.presented.size)
        assertEquals(1, server.maximumConcurrent)
    }

    // Signed-in requests

    @Test
    fun signedInRequestSignsItsEmptyBodyWithTheRegisteredKey() = runTest {
        val server = NativeTestServer(paid = true, initialStatus = 202)
        val expected = sha256(NativeVerification.payload(refresh(), "synthetic-assertion-challenge"))
        val provider = NativeAttestProvider(
            supported = { true },
            generateKey = {
                xctFail("Unexpected new key")
                ""
            },
            attest = { _, _ ->
                xctFail("Unexpected registration")
                ByteArray(0)
            },
            assert = { key, hash ->
                xctAssertEqual(key, "synthetic-key", "asserted key")
                xctAssertEqual(hash.toList(), expected, "asserted hash")
                "synthetic-assertion".toByteArray()
            },
        )
        val verifier = verification(provider, unexpectedCheck(), registered, save = { xctFail("Unexpected key change") })
        val result = verifier.sendSignedIn(refresh(), base, using = server.transport)
        assertEquals(201, result.statusCode)
        val sent = server.requests.filter { it.path == "/api/sync" }
        assertEquals(1, sent.size)
        assertEquals("synthetic-key", sent.first().header("X-App-Attest-Key-Id"))
        assertEquals("synthetic-assertion-challenge", sent.first().header("X-App-Attest-Challenge"))
        assertEquals(base64("synthetic-assertion"), sent.first().header("X-App-Attest-Assertion"))
        assertEquals("1", sent.first().header("X-Native-Verification"))
        assertNull(sent.first().body)
    }

    @Test
    fun signedInRequestGoesUnsignedWhenThisInstallationCannotSign() = runTest {
        val unregistered = mapOf(base to NativeAttestRecord(keyId = "synthetic-key", appId = "TESTTEAM01.com.example.Peek", registered = false))
        val otherApp = mapOf(base to NativeAttestRecord(keyId = "synthetic-key", appId = "TESTTEAM01.com.example.Other", registered = true))
        val cases = listOf(
            Triple(unsupported, registered, true), Triple(neverRegisters, emptyMap<String, NativeAttestRecord>(), true), Triple(neverRegisters, unregistered, true),
            Triple(neverRegisters, otherApp, true), Triple(neverRegisters, registered, false),
        )
        for ((provider, records, paid) in cases) {
            val server = NativeTestServer(paid = paid, initialStatus = 202)
            val verifier = verification(provider, unexpectedCheck(), records, save = { xctFail("Unexpected key change") })
            val result = verifier.sendSignedIn(refresh(), base, using = server.transport)
            assertEquals(202, result.statusCode)
            val requests = server.requests
            assertEquals(1, requests.count { it.path == "/api/sync" })
            assertNull(requests.last().header("X-App-Attest-Assertion"))
            assertEquals("1", requests.last().header("X-Native-Verification"))
            assertFalse(requests.any { it.path == "/api/public/native/challenge" })
            assertFalse(requests.any { it.path == "/api/public/native/verification" && it.method == "POST" })
        }
    }

    /** Adapted: the test's virtual clock measures the wait, where Swift reads a ContinuousClock. */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun slowChallengeSendsSignedInRequestUnsignedOnTime() = runTest {
        val server = NativeTestServer(paid = true, initialStatus = 202, challengeDelay = 3.seconds)
        val provider = NativeAttestProvider(supported = { true }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> "late".toByteArray() })
        val verifier = verification(provider, unexpectedCheck(), registered)
        val started = currentTime
        val result = verifier.sendSignedIn(refresh(), base, limit = 200.milliseconds, using = server.transport)
        assertTrue(currentTime - started < 2_000)
        assertEquals(202, result.statusCode)
        assertNull(server.requests.last { it.path == "/api/sync" }.header("X-App-Attest-Assertion"))
    }

    @Test
    fun challengeFailureSendsSignedInRequestsUnsignedAndBacksOff() = runTest {
        val server = NativeTestServer(paid = true, initialStatus = 202, challengeStatus = 503)
        val provider = NativeAttestProvider(
            supported = { true },
            generateKey = { "" },
            attest = { _, _ -> ByteArray(0) },
            assert = { _, _ ->
                xctFail("Unexpected assertion")
                ByteArray(0)
            },
        )
        val verifier = verification(provider, unexpectedCheck(), registered, save = { xctFail("Unexpected key change") })
        repeat(2) {
            val result = verifier.sendSignedIn(refresh(), base, using = server.transport)
            assertEquals(202, result.statusCode)
        }
        val requests = server.requests
        assertEquals(1, requests.count { it.path == "/api/public/native/challenge" })
        assertTrue(requests.filter { it.path == "/api/sync" }.all { it.header("X-App-Attest-Assertion") == null })
    }

    @Test
    fun refusedSignedInRequestIsSignedAgainOnceThenDropsTheKey() = runTest {
        val server = NativeTestServer(paid = true, refuseAssertions = true)
        var assertions = 0
        var removed = 0
        val provider = NativeAttestProvider(
            supported = { true },
            generateKey = {
                xctFail("Unexpected new key")
                ""
            },
            attest = { _, _ -> ByteArray(0) },
            assert = { _, _ ->
                assertions += 1
                "synthetic-assertion-$assertions".toByteArray()
            },
        )
        val verifier = verification(
            provider, unexpectedCheck(), registered,
            save = { records ->
                xctAssertEqual(records.isEmpty(), true, "records left")
                removed += 1
            },
        )
        val result = verifier.sendSignedIn(refresh(), base, using = server.transport)
        assertEquals(403, result.statusCode)
        assertEquals("Refused key removed", 1, removed)
        assertEquals(
            listOf("synthetic-assertion-1", "synthetic-assertion-2").map(::base64),
            server.requests.filter { it.path == "/api/sync" }.mapNotNull { it.header("X-App-Attest-Assertion") },
        )
        assertTrue(server.presented.isEmpty())
    }

    @Test
    fun concurrentSignedInRequestsReachTheServerInSigningOrder() = runTest {
        val server = NativeTestServer(paid = true, initialStatus = 202)
        val provider = NativeAttestProvider(
            supported = { true }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> "synthetic-assertion".toByteArray() },
        )
        val verifier = verification(provider, unexpectedCheck(), registered)
        val first = async { verifier.sendSignedIn(refresh(), base, using = server.transport) }
        val second = async { verifier.sendSignedIn(refresh(), base, using = server.transport) }
        val results = awaitAll(first, second)
        assertEquals(listOf(201, 201), results.map { it.statusCode })
        assertEquals(1, server.maximumConcurrent)
    }
}

/** The Swift tests' `NativeTestServer` actor. Every request takes 10 ms, a challenge `challengeDelay` more. */
private class NativeTestServer(
    private val paid: Boolean = false,
    private val initialStatus: Int = 403,
    private val rejectProof: Boolean = false,
    private val refuseAssertions: Boolean = false,
    private val challengeStatus: Int = 200,
    private val challengeDelay: Duration = Duration.ZERO,
) {
    val requests = mutableListOf<HttpRequest>()
    val presented = mutableListOf<String>()
    var maximumConcurrent = 0
    private var concurrent = 0

    val transport = HttpTransport { send(it) }

    fun present(url: String) {
        presented += url
    }

    private suspend fun send(request: HttpRequest): HttpResponse {
        requests += request
        concurrent += 1
        maximumConcurrent = maxOf(concurrent, maximumConcurrent)
        try {
            delay(10.milliseconds)
            var status = 200
            var body = "{}"
            val headers = mutableMapOf<String, String>()
            when (request.path) {
                "/api/public/native/verification" ->
                    if (request.method == "POST") {
                        status = 204
                    } else {
                        body = """{"appAttestAppId":${if (paid) "\"TESTTEAM01.com.example.Peek\"" else "null"},"turnstile":true}"""
                    }
                "/api/public/native/challenge" -> {
                    delay(challengeDelay)
                    status = challengeStatus
                    val sent = ApiJson.decodeFromString(MapSerializer(String.serializer(), String.serializer()), request.body!!.decodeToString())
                    body = """{"challenge":"synthetic-${sent["purpose"]}-challenge"}"""
                }
                "/api/public/verification" -> body = """{"proof":"synthetic-proof","expiresAt":${Instant.now().plusSeconds(900).toEpochMilli()}}"""
                else -> {
                    val signed = !refuseAssertions && request.header("X-App-Attest-Assertion") != null
                    val proven = !rejectProof && request.header("X-Lookup-Proof") != null
                    status = if (signed || proven) 201 else initialStatus
                    if (status == 403) headers["X-Lookup-Verification"] = "required"
                }
            }
            return HttpResponse(status, headers, body.toByteArray())
        } finally {
            concurrent -= 1
        }
    }
}
