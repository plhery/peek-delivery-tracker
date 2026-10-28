package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import java.io.IOException
import java.net.SocketTimeoutException
import java.security.MessageDigest
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/**
 * The client's requests and how it reads their answers. Every expected value is what APIClient.swift gave for the same
 * case, compiled into a command-line harness with a recording URL protocol.
 */
class APIClientTests {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://peek.example", supabaseUrl = "https://peek.example", supabasePublishableKey = "public",
        googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "harness.test",
    )
    private val parcelId = UUID.fromString("40000000-0000-0000-0000-000000000001")
    private val analytics = mutableListOf<String>()

    /** The first parcel of the contract's list fixture, as the server sends it. */
    private val parcel: String by lazy {
        Json.parseToJsonElement(SharedResources.text("ContractFixtures.json")).jsonObject["packageList"]!!.jsonObject["packages"]!!.jsonArray[0].toString()
    }

    private val unsupported =
        NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) })

    private fun verification(provider: DeviceAttestation = unsupported, records: Map<String, NativeAttestRecord> = emptyMap()) =
        NativeVerification(provider, TurnstilePresenter { throw NativeVerificationFailed() }, records, {}, language = { AppLanguage.En })

    private fun client(
        transport: HttpTransport,
        session: TestSession,
        verification: NativeVerification = verification(),
        now: () -> Instant = Instant::now,
        region: Locale = Locale.forLanguageTag("de-DE"),
    ) = DeliveryApiClient(
        configuration, session, transport, verification, deviceName = "Synthetic phone", region = { region },
        analytics = { event, outcome -> analytics += "$event ${outcome.rawValue}" }, now = now,
    )

    /** A server that gives its answers in turn, the last one again and again. An exception is a failed request. */
    private fun serving(vararg answers: Any): RecordingTransport {
        val queue = answers.toMutableList()
        return RecordingTransport {
            val next = if (queue.size > 1) queue.removeAt(0) else queue[0]
            if (next is Exception) throw next
            next as HttpResponse
        }
    }

    private fun describe(error: Throwable): String = when (error) {
        is DeliveryApiError.AuthenticationExpired -> "authenticationExpired"
        is DeliveryApiError.DuplicateTracking -> "duplicateTracking(${error.parcelId.uuidString})"
        is DeliveryApiError.InvalidResponse -> "invalidResponse"
        is DeliveryApiError.RefreshFailed -> "refreshFailed"
        is DeliveryApiError.RefreshTimeout -> "refreshTimeout"
        is DeliveryApiError.RateLimited -> "rateLimited(${error.retryAfter})"
        is DeliveryApiError.Service -> "service(${error.serviceMessage})"
        is DeliveryApiError.ServiceFailed -> "serviceFailed(${error.status})"
        is ParcelLinkStopped -> "stopped"
        is NativeVerificationFailed -> "verificationFailed"
        is IOException -> "network"
        // Swift's DecodingError, which quotes nothing of what it read.
        is DecodingError -> "decoding"
        is SerializationException -> "decoding that quotes the answer: ${error.message}"
        else -> "other ${error::class.simpleName}"
    }

    private suspend fun outcome(block: suspend () -> Any?): String = try {
        "ok ${block()}"
    } catch (error: Exception) {
        describe(error)
    }

    private fun hex(data: ByteArray): String = data.joinToString("") { "%02x".format(Locale.ROOT, it) }

    // Requests

    @Test
    fun theAccountsRequestsCarryItsTokenAndTheirBody() = runTest {
        val transport = serving(jsonResponse(200, parcel))
        val session = TestSession()
        val renamed = client(transport, session).rename(parcelId, "New name")

        assertEquals("Coffee beans", renamed.label)
        val request = transport.requests.single()
        assertEquals("PATCH", request.method)
        assertEquals("https://peek.example/api/packages/40000000-0000-0000-0000-000000000001", request.url)
        assertEquals(
            mapOf(
                "Accept" to "application/json", "Authorization" to "Bearer synthetic-access", "Content-Type" to "application/json",
                "X-Requested-With" to "XMLHttpRequest",
            ),
            request.headers,
        )
        assertEquals("""{"label":"New name"}""", request.body?.decodeToString())
        assertEquals(30.0, request.timeout, 0.0)
        assertTrue(request.handlesCookies)
        assertEquals(listOf("token"), session.log)
        assertEquals(listOf("parcel-rename success"), analytics)

        val list = serving(jsonResponse(200, """{"packages":[$parcel]}"""))
        assertEquals(1, client(list, TestSession()).listPackages().size)
        val read = list.requests.single()
        assertEquals("GET", read.method)
        assertEquals("https://peek.example/api/packages?includeArchived=true", read.url)
        assertEquals(
            mapOf("Accept" to "application/json", "Authorization" to "Bearer synthetic-access", "X-Requested-With" to "XMLHttpRequest"),
            read.headers,
        )
        assertNull(read.body)
    }

    @Test
    fun aDetectionGoesUnsignedFromAnInstallationThatCannotSign() = runTest {
        val transport = serving(jsonResponse(200, """{"trackingNumber":"SYNTHETIC123","carrier":"dhl","recognized":["dhl","ups"]}"""))
        val detected = client(transport, TestSession()).detectCarrier("SYNTHETIC123")

        assertEquals(CarrierId("dhl"), detected.carrier)
        val request = transport.requests.single()
        assertEquals("POST", request.method)
        assertEquals("https://peek.example/api/carriers/detect", request.url)
        assertEquals("1", request.header("X-Native-Verification"))
        assertNull(request.header("X-App-Attest-Assertion"))
        assertEquals("""{"lookupCountryHint":"DE","trackingNumber":"SYNTHETIC123"}""", request.body?.decodeToString())
        assertEquals(emptyList<String>(), analytics)
    }

    /** Android-only: the region is the device's, which the app gives, whatever default the app's language sets. */
    @Test
    fun aDetectionSendsTheDevicesRegionWhateverTheAppsLanguage() = runTest {
        val language = Locale.getDefault()
        try {
            Locale.setDefault(Locale.JAPAN)
            val cases = listOf(
                "fr-CH" to """{"lookupCountryHint":"CH","trackingNumber":"SYNTHETIC123"}""",
                "en-US-u-rg-gbzzzz" to """{"lookupCountryHint":"GB","trackingNumber":"SYNTHETIC123"}""",
                "en-001" to """{"trackingNumber":"SYNTHETIC123"}""",
            )
            for ((region, sent) in cases) {
                val transport = serving(jsonResponse(200, """{"trackingNumber":"SYNTHETIC123","carrier":"dhl"}"""))
                client(transport, TestSession(), region = Locale.forLanguageTag(region)).detectCarrier("SYNTHETIC123")
                assertEquals(region, sent, transport.requests.single().body?.decodeToString())
            }
        } finally {
            Locale.setDefault(language)
        }
    }

    @Test
    fun aQueuedCheckIsSignedWithTheRegisteredKey() = runTest {
        var challenges = 0
        val transport = RecordingTransport { request ->
            when (request.path) {
                "/api/public/native/verification" -> jsonResponse(200, """{"appAttestAppId":"TESTTEAM01.com.example.Peek","turnstile":true}""")
                "/api/public/native/challenge" -> {
                    challenges += 1
                    jsonResponse(200, """{"challenge":"synthetic-assertion-$challenges"}""")
                }
                else -> jsonResponse(200, """{"queued":true,"pending":1,"jobIds":["42000000-0000-0000-0000-000000000004"]}""")
            }
        }
        val provider = NativeAttestProvider(
            supported = { true }, generateKey = { "unused" }, attest = { _, _ -> ByteArray(0) },
            assert = { key, hash -> "assertion:$key:${hex(hash)}".toByteArray() },
        )
        val records = mapOf("https://peek.example" to NativeAttestRecord(keyId = "synthetic-key-0", appId = "TESTTEAM01.com.example.Peek", registered = true))
        val queued = client(transport, TestSession(), verification(provider, records)).queueRefreshAll()

        assertEquals(listOf(UUID.fromString("42000000-0000-0000-0000-000000000004")), queued)
        val (config, challenge, sync) = transport.requests
        assertEquals("GET https://peek.example/api/public/native/verification", "${config.method} ${config.url}")
        assertFalse(config.handlesCookies)
        assertEquals(15.0, config.timeout, 0.0)
        assertEquals(mapOf("Accept" to "application/json"), config.headers)
        assertEquals("POST https://peek.example/api/public/native/challenge", "${challenge.method} ${challenge.url}")
        assertEquals("""{"keyId":"synthetic-key-0","purpose":"assertion"}""", challenge.body?.decodeToString())
        assertEquals("POST https://peek.example/api/sync", "${sync.method} ${sync.url}")
        assertEquals(
            mapOf(
                "Accept" to "application/json", "Authorization" to "Bearer synthetic-access", "X-Native-Verification" to "1",
                "X-Requested-With" to "XMLHttpRequest", "X-App-Attest-Key-Id" to "synthetic-key-0", "X-App-Attest-Challenge" to "synthetic-assertion-1",
                "X-App-Attest-Assertion" to
                    "YXNzZXJ0aW9uOnN5bnRoZXRpYy1rZXktMDo3N2E4Zjk0ZTVhYzRmNjA0ZTMzOTM4NDcxZDY1NzcyY2YxMDIxNDcyNGJlYWI1YzdkN2U3Mzg1ZTMzYmZkMTc1",
            ),
            sync.headers,
        )
        assertNull(sync.body)
        assertEquals(listOf("parcels-refresh success"), analytics)
    }

    // Answers

    @Test
    fun errorsReadAsTheIPhoneReadsThem() = runTest {
        val packageId = "40000000-0000-0000-0000-000000000005"
        val cases = listOf(
            jsonResponse(400, """{"error":"Bad request"}""") to "service(Bad request)",
            jsonResponse(400, """{"error":123}""") to "serviceFailed(400)",
            jsonResponse(500) to "serviceFailed(500)",
            jsonResponse(409, """{"error":"Duplicate","packageId":"$packageId"}""") to "duplicateTracking($packageId)",
            jsonResponse(409, """{"error":"Duplicate","packageId":"${packageId.lowercase(Locale.ROOT)}"}""") to "duplicateTracking($packageId)",
            jsonResponse(409, """{"error":"Duplicate","packageId":"not-a-uuid"}""") to "serviceFailed(409)",
            jsonResponse(409, """{"packageId":"$packageId"}""") to "serviceFailed(409)",
            jsonResponse(409, """{"error":"Duplicate"}""") to "service(Duplicate)",
            jsonResponse(429, headers = mapOf("Retry-After" to "30")) to "rateLimited(30.0)",
            jsonResponse(429, """{"error":"Slow down"}""") to "rateLimited(0.0)",
            jsonResponse(403, """{"error":"Please verify"}""", mapOf("X-Lookup-Verification" to "required")) to "verificationFailed",
            SocketTimeoutException("timeout") to "network",
            IOException("offline") to "network",
            HttpResponse(400, body = """{"error":"Bad request"}""".toByteArray(Charsets.UTF_16LE)) to "service(Bad request)",
            jsonResponse(502, "<html>Bad gateway</html>") to "serviceFailed(502)",
            jsonResponse(202, """{"ok":true}""") to "ok kotlin.Unit",
            jsonResponse(304, """{"ok":true}""") to "serviceFailed(304)",
        )
        for ((answer, expected) in cases) {
            analytics.clear()
            val transport = serving(answer)
            val session = TestSession()
            assertEquals(expected, outcome { client(transport, session).archive(parcelId) })
            assertEquals(expected, 1, transport.requests.size)
            assertEquals("DELETE https://peek.example/api/packages/40000000-0000-0000-0000-000000000001", transport.requests[0].let { "${it.method} ${it.url}" })
            assertEquals(expected, listOf("token"), session.log)
            val counted = if (answer is HttpResponse && answer.statusCode == 202) "accepted" else if (expected.startsWith("ok")) "success" else "error"
            assertEquals(expected, listOf("parcel-archive $counted"), analytics)
        }
    }

    @Test
    fun aRefusedTokenIsRefreshedOnceThenTheSessionEnds() = runTest {
        val refused = jsonResponse(401, """{"error":"x"}""")
        val fine = jsonResponse(200, """{"ok":true}""")
        // Answers, the session's tokens, then the outcome, the session's calls and how many requests were sent.
        class Case(val answers: List<HttpResponse>, val session: TestSession, val expected: String, val log: List<String>, val sent: Int)
        val cases = listOf(
            Case(listOf(refused, fine), TestSession(), "ok kotlin.Unit", listOf("token", "refresh"), 2),
            Case(listOf(refused, refused), TestSession(), "authenticationExpired", listOf("token", "refresh", "signOut"), 2),
            Case(listOf(jsonResponse(403, """{"error":"x"}"""), fine), TestSession(), "ok kotlin.Unit", listOf("token", "refresh"), 2),
            // Only "required" asks for the device's check; any other 403 is the session's.
            Case(
                listOf(jsonResponse(403, headers = mapOf("X-Lookup-Verification" to "Required"))), TestSession(), "authenticationExpired",
                listOf("token", "refresh", "signOut"), 2,
            ),
            Case(listOf(refused, fine), TestSession(refreshed = null), "authenticationExpired", listOf("token", "refresh", "signOut"), 1),
            Case(listOf(refused, fine), TestSession().apply { refreshFailure = IOException("offline") }, "network", listOf("token", "refresh"), 1),
            Case(listOf(refused, fine), TestSession().apply { user = null }, "authenticationExpired", emptyList(), 0),
            Case(listOf(refused, fine), TestSession(token = null), "ok kotlin.Unit", listOf("token", "refresh"), 2),
        )
        for ((index, case) in cases.withIndex()) {
            val transport = serving(*case.answers.toTypedArray())
            assertEquals("case $index", case.expected, outcome { client(transport, case.session).archive(parcelId) })
            assertEquals("case $index", case.log, case.session.log)
            assertEquals("case $index", case.sent, transport.requests.size)
        }
        // The second request goes with the refreshed token; one without a token goes without the header.
        val transport = serving(refused, fine)
        client(transport, TestSession()).archive(parcelId)
        assertEquals(listOf("Bearer synthetic-access", "Bearer synthetic-refreshed"), transport.requests.map { it.header("Authorization") })
        val anonymous = serving(refused, fine)
        client(anonymous, TestSession(token = null)).archive(parcelId)
        assertEquals(listOf(null, "Bearer synthetic-refreshed"), anonymous.requests.map { it.header("Authorization") })
    }

    @Test
    fun anAnswerIsReadInEveryEncodingJSONDecoderFinds() = runTest {
        val cases = listOf(
            parcel.toByteArray() to "ok Coffee beans",
            byteArrayOf(0xFF.toByte(), 0xFE.toByte()) + parcel.toByteArray(Charsets.UTF_16LE) to "ok Coffee beans",
            // A lone surrogate is no UTF-16, even in a field the parcel does not have.
            utf16le(parcel.dropLast(1) + ",\"padding\":\"\uD800\"}") to "invalidResponse",
            "{}".toByteArray() to "invalidResponse",
        )
        for ((body, expected) in cases) {
            analytics.clear()
            val transport = serving(HttpResponse(200, body = body))
            assertEquals(expected, outcome { client(transport, TestSession()).rename(parcelId, "New name").label })
            // The request succeeded; only its answer could not be read.
            assertEquals(listOf("parcel-rename success"), analytics)
        }
    }

    /**
     * Each answer goes through the decoder the iPhone reads it with: JSONDecoder.deliveryTracker (keys as
     * convertFromSnakeCase reads them, the first of two alike winning, no number or Bool in quotes) or, for an error,
     * the plain JSONDecoder.
     */
    @Test
    fun answersAreReadWithTheIPhonesDecoders() = runTest {
        val number = "993412345612345678"
        val parcels = listOf(
            parcel.replace("\"tracking_number\"", "\"trackingNumber\"") to "ok $number",
            parcel.replace("\"tracking_number\"", "\"TRACKING_NUMBER\"") to "ok $number",
            parcel.replace("\"tracking_number\"", "\"_tracking_number\"") to "invalidResponse",
            parcel.replace("\"notifications_muted\":false", "\"notifications_muted\":\"false\"") to "invalidResponse",
            parcel.dropLast(1) + ",\"trackingNumber\":\"SYNTHETIC-OTHER\"}" to "ok $number",
            "{\"trackingNumber\":\"SYNTHETIC-OTHER\"," + parcel.drop(1) to "ok SYNTHETIC-OTHER",
        )
        for ((body, expected) in parcels) {
            val transport = serving(jsonResponse(200, body))
            assertEquals(body, expected, outcome { client(transport, TestSession()).rename(parcelId, "New name").trackingNumber })
        }
        val packageId = "40000000-0000-0000-0000-000000000005"
        val errors = listOf(
            jsonResponse(409, """{"error":"Duplicate","package_id":"$packageId"}""") to "service(Duplicate)",
            jsonResponse(409, """{"error":"Duplicate","packageId":"$packageId","package_id":"not-a-uuid"}""") to "duplicateTracking($packageId)",
            jsonResponse(400, """{"Error":"Bad"}""") to "serviceFailed(400)",
        )
        for ((answer, expected) in errors) {
            assertEquals(expected, outcome { client(serving(answer), TestSession()).archive(parcelId) })
        }
        val previews = listOf(
            """{"preview_nickname":"Sam"}""" to "ok Sam",
            """{"previewNickname":"Max","preview_nickname":"Sam"}""" to "ok Max",
            """{"PREVIEW_NICKNAME":"Sam"}""" to "ok Sam",
        )
        for ((body, expected) in previews) {
            assertEquals(expected, outcome { DeliveryApiClient.invitationPreview("SYNTH-CODE", configuration, serving(jsonResponse(200, body))) })
        }
        val question = NumberRefusalRequest(trackingNumber = "SYNTHREFUSAL01", carrier = CarrierId.NzCouriers)
        for (body in listOf("""{"refused":"true"}""", """{"Refused":true}""")) {
            assertEquals("invalidResponse", outcome { DeliveryApiClient.numberRefusal(question, configuration, serving(jsonResponse(200, body))) })
        }
    }

    /** Android-only: an answer that cannot be read leaves no trace of itself in the error, which may reach a log. */
    @Test
    fun anUnreadableAnswerQuotesNoneOfIt() = runTest {
        val secret = "synthetic-secret-value"
        for (body in listOf("""{"previewNickname":"$secret""", """{"previewNickname":["$secret"]}""", """{"previewNickname":"$secret"}x""")) {
            try {
                DeliveryApiClient.invitationPreview("SYNTH-CODE", configuration, serving(jsonResponse(200, body)))
                fail("Expected a decoding error")
            } catch (error: DecodingError) {
                assertEquals("The data couldn’t be read because it isn’t in the correct format.", error.message)
                assertNull(error.cause)
                assertFalse(error.stackTraceToString().contains(secret))
            }
        }
        val token = "f".repeat(64)
        val registration = LiveActivityRevocations.Registration(ownerId = parcelId, installationId = parcelId, revocationToken = token, pending = true)
        assertFalse(registration.toString().contains(token))
        assertTrue(registration.toString().contains("pending=true"))
    }

    /** UTF-16LE of every unit as it is, lone surrogates included, which the JDK's encoder would replace. */
    private fun utf16le(text: String): ByteArray = ByteArray(text.length * 2) { index ->
        val unit = text[index / 2].code
        (if (index % 2 == 0) unit and 0xFF else unit shr 8).toByte()
    }

    // Jobs

    private val jobs = (1..3).map { UUID.fromString("42000000-0000-0000-0000-00000000000$it") }

    /** `/api/sync/jobs` answering each job's next state, the last one again and again; a job with none is left out. */
    private fun jobServer(plan: Map<UUID, List<String>>, first: HttpResponse? = null): RecordingTransport {
        val states = plan.mapValues { it.value.toMutableList() }
        var queued = first
        return RecordingTransport { request ->
            queued?.let {
                queued = null
                return@RecordingTransport it
            }
            val ids = UrlComponents.parse(request.url)?.queryItems?.firstOrNull { it.name == "ids" }?.value.orEmpty().split(",").filter { it.isNotEmpty() }
            val items = mutableListOf<String>()
            for (id in ids) {
                val list = states[UUID.fromString(id)] ?: continue
                when (val state = if (list.size > 1) list.removeAt(0) else list[0]) {
                    "failed" -> items += """{"id":"$id","status":"failed","requestedAt":"2026-07-15T01:00:00Z","error":"Carrier unavailable"}"""
                    "failedSilently" -> items += """{"id":"$id","status":"failed","requestedAt":"2026-07-15T01:00:00Z"}"""
                    "errors" -> items += """{"id":"$id","status":"succeeded","requestedAt":"2026-07-15T01:00:00Z","result":{"checked":1,"errors":1}}"""
                    "limited" -> return@RecordingTransport jsonResponse(429, headers = mapOf("Retry-After" to "0.2"))
                    else -> items += """{"id":"$id","status":"$state","requestedAt":"2026-07-15T01:00:00Z"}"""
                }
            }
            jsonResponse(200, """{"jobs":[${items.joinToString(",")}]}""")
        }
    }

    private fun HttpRequest.jobIds(): List<UUID> =
        UrlComponents.parse(url)!!.queryItems!!.single { it.name == "ids" }.value!!.split(",").map(UUID::fromString)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun jobsArePolledUntilEachHasSucceeded() = runTest {
        val (first, second) = jobs
        // The outcome, then the jobs each poll asked for. Swift asks for them in its Set's order; here they keep theirs.
        val cases = listOf(
            mapOf(first to listOf("running", "succeeded"), second to listOf("succeeded")) to
                ("ok kotlin.Unit" to listOf(listOf(first, second), listOf(first))),
            mapOf(first to listOf("succeeded"), second to listOf("failed")) to ("service(Carrier unavailable)" to listOf(listOf(first, second))),
            mapOf(first to listOf("failedSilently"), second to listOf("succeeded")) to ("refreshFailed" to listOf(listOf(first, second))),
            mapOf(first to listOf("errors"), second to listOf("succeeded")) to ("refreshFailed" to listOf(listOf(first, second))),
            mapOf(second to listOf("succeeded")) to ("invalidResponse" to listOf(listOf(first, second))),
            mapOf(first to listOf("limited", "succeeded"), second to listOf("succeeded")) to
                ("ok kotlin.Unit" to listOf(listOf(first, second), listOf(first, second))),
        )
        for ((plan, expected) in cases) {
            val transport = jobServer(plan)
            val session = TestSession()
            val started = currentTime
            val now = { Instant.ofEpochMilli(currentTime) }
            assertEquals(expected.first, outcome { client(transport, session, now = now).waitForJobs(listOf(first, second)) })
            assertEquals(expected.first, expected.second, transport.requests.map { it.jobIds() })
            assertEquals(expected.first, List(expected.second.size) { "token" }, session.log)
            // A second poll waits a second, which a shorter Retry-After does not shorten.
            assertEquals(expected.first, (expected.second.size - 1) * 1_000L, currentTime - started)
            assertTrue(transport.requests.all { it.method == "GET" && it.url.startsWith("https://peek.example/api/sync/jobs?ids=") })
        }

        val repeated = jobServer(mapOf(first to listOf("succeeded")))
        client(repeated, TestSession()).waitForJobs(listOf(first, first))
        assertEquals(listOf(listOf(first)), repeated.requests.map { it.jobIds() })

        val none = jobServer(emptyMap())
        client(none, TestSession()).waitForJobs(emptyList())
        assertTrue(none.requests.isEmpty())
    }

    @Test
    fun aRefreshQueuesTheParcelsCheckThenWaitsForIt() = runTest {
        val job = jobs[2]
        val transport = jobServer(
            mapOf(job to listOf("succeeded")),
            first = jsonResponse(200, """{"queued":true,"pending":1,"jobIds":["${job.uuidString}"]}"""),
        )
        val session = TestSession()
        client(transport, session).refresh(parcelId)
        assertEquals(
            listOf(
                "POST https://peek.example/api/packages/40000000-0000-0000-0000-000000000001/sync",
                "GET https://peek.example/api/sync/jobs?ids=42000000-0000-0000-0000-000000000003",
            ),
            transport.requests.map { "${it.method} ${it.url}" },
        )
        assertEquals(listOf("token", "token"), session.log)
        assertEquals(listOf("parcel-refresh success"), analytics)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun jobsStillRunningAfterTwoMinutesTimeOut() = runTest {
        val transport = jobServer(mapOf(jobs[0] to listOf("running")))
        val now = { Instant.ofEpochMilli(currentTime) }
        assertEquals("refreshTimeout", outcome { client(transport, TestSession(), now = now).waitForJobs(listOf(jobs[0])) })
        // Polled at 0, 1, 2.5, 4.75, 8.125, 13.1875 and 20.78 seconds, then every ten until two minutes have gone.
        assertEquals(16, transport.requests.size)
        assertEquals(120_000L, currentTime)
    }

    // Requests without an account

    @Test
    fun anInvitationShowsANameOfTwentyFourScalarsAtMost() = runTest {
        val cases = listOf(
            jsonResponse(200, """{"previewNickname":"Sam"}""") to "ok Sam",
            jsonResponse(200, """{"previewNickname":"${"x".repeat(25)}"}""") to "invalidResponse",
            jsonResponse(200, """{"previewNickname":"${"📦".repeat(24)}"}""") to "ok ${"📦".repeat(24)}",
            // Twenty-four characters of two scalars each.
            jsonResponse(200, """{"previewNickname":"${"e\u0301".repeat(24)}"}""") to "invalidResponse",
            jsonResponse(200, "{}") to "invalidResponse",
            jsonResponse(404, """{"error":"x"}""") to "service(Invitation unavailable)",
            jsonResponse(500) to "serviceFailed(500)",
            // As in Swift, an answer that is no JSON throws the decoder's own error.
            jsonResponse(200, "garbage") to "decoding",
        )
        for ((answer, expected) in cases) {
            val transport = serving(answer)
            assertEquals(expected, outcome { DeliveryApiClient.invitationPreview("SYNTH-CODE", configuration, transport) })
            val request = transport.requests.single()
            assertEquals("POST https://peek.example/api/friends/invite-preview", "${request.method} ${request.url}")
            assertEquals(mapOf("Content-Type" to "application/json"), request.headers)
            assertEquals("""{"code":"SYNTH-CODE"}""", request.body?.decodeToString())
            assertEquals(15.0, request.timeout, 0.0)
            assertTrue(request.handlesCookies)
        }
    }

    @Test
    fun aRefusalReadsOnlyItsOwnAnswer() = runTest {
        val question = NumberRefusalRequest(trackingNumber = "SYNTHREFUSAL01", carrier = CarrierId.NzCouriers)
        val cases = listOf(
            jsonResponse(200, """{"refused":true}""") to "ok true",
            jsonResponse(200, """{"refused":false}""") to "ok false",
            jsonResponse(429) to "serviceFailed(429)",
            jsonResponse(200, "garbage") to "invalidResponse",
            jsonResponse(200, """{"refused":null}""") to "invalidResponse",
        )
        for ((answer, expected) in cases) {
            val transport = serving(answer)
            assertEquals(expected, outcome { DeliveryApiClient.numberRefusal(question, configuration, transport) })
            assertEquals(
                mapOf("Accept" to "application/json", "Content-Type" to "application/json"),
                transport.requests.single().headers,
            )
        }
    }

    @Test
    fun aLinksAnswersAndAKeyWithALineBreak() = runTest {
        val cases = listOf(
            jsonResponse(410, """{"error":"Parcel not shared"}""") to "stopped",
            jsonResponse(404, """{"error":"Parcel unavailable"}""") to "ok null",
            jsonResponse(429, headers = mapOf("Retry-After" to "12")) to "rateLimited(12.0)",
            // A date already gone.
            jsonResponse(429, headers = mapOf("Retry-After" to "Mon, 21 Sep 2026 14:14:20 GMT")) to "rateLimited(0.0)",
            jsonResponse(503) to "serviceFailed(503)",
            jsonResponse(200, "{}") to "invalidResponse",
        )
        for ((answer, expected) in cases) {
            val transport = serving(answer)
            assertEquals(expected, outcome { DeliveryApiClient.publicParcel("k7Qm2xHd9RtW", configuration = configuration, transport = transport) })
            val request = transport.requests.single()
            assertEquals("https://peek.example/api/public/parcels/k7Qm2xHd9RtW", request.url)
            assertEquals(mapOf("Accept" to "application/json"), request.headers)
            assertFalse(request.handlesCookies)
            assertEquals(15.0, request.timeout, 0.0)
        }
        // URLRequest never sets a value with a line break; other characters go as they are.
        for ((key, sent) in listOf("a\nb" to null, "a\rb" to null, "kéy" to "kéy")) {
            val transport = serving(jsonResponse(404))
            DeliveryApiClient.publicParcel("k7Qm2xHd9RtW", key, configuration, transport)
            assertEquals(sent, transport.requests.single().header("X-Parcel-Key"))
        }
    }

    @Test
    fun aHeaderIsSetAsURLRequestSetsIt() {
        val request = HttpRequest("https://peek.example", headers = linkedMapOf("X-Broken" to "a\nb", "X-Kept" to "value"))
        assertEquals(mapOf("X-Kept" to "value"), request.headers)
        assertEquals("value", request.header("x-kept"))
        assertEquals("value", request.setting("X-Kept", "line\r\nbreak").header("X-Kept"))
        val replaced = request.setting("x-kept", "new")
        assertEquals(1, replaced.headers.size)
        assertEquals("new", replaced.header("X-KEPT"))
        assertEquals(emptyMap<String, String>(), request.setting("X-KEPT", null).headers)
    }

    // The User-Agent

    @Test
    fun theTransportNamesTheAppUnlessTheRequestDoes() = runTest {
        val transport = RecordingTransport()
        val android = "PeekDeliveryTracker/42 okhttp/4.12.0 Android/16"
        val named = transport.identifying(android)
        named.data(HttpRequest("https://peek.example/api/packages"))
        named.data(HttpRequest("https://peek.example/api/packages", headers = mapOf("user-agent" to "Own/1")))
        assertEquals(listOf(android, "Own/1"), transport.requests.map { it.header("User-Agent") })

        // The iPhone's, which the server reads as the app's (NATIVE_USER_AGENT in src/server/nativeVerification.ts);
        // the Android app's does not match it, so the server never takes it for an iPhone that failed to sign.
        val native = Regex("^PeekDeliveryTracker/\\S+ CFNetwork/\\S+ Darwin/\\S+$")
        val iphone = iosUserAgent(build = "42", cfNetwork = "3826.400.120", darwin = "25.0.0")
        assertEquals("PeekDeliveryTracker/42 CFNetwork/3826.400.120 Darwin/25.0.0", iphone)
        assertTrue(native.matches(iphone))
        assertFalse(native.matches(android))
    }

    // Pure rules

    @Test
    fun retryAfterReadsSecondsAndDatesAsTheIPhoneDoes() {
        // 2026-09-21 14:13:20 UTC, a Monday.
        val now = Instant.ofEpochSecond(1_790_000_000)
        val cases = listOf(
            "12" to "12.0", "1.5" to "1.5", "-3" to "0.0", "abc" to "0.0", null to "0.0", "0x10" to "16.0", "0X10" to "0.0", "inf" to "0.0",
            "nan" to "0.0", "1e3" to "1000.0", "12\u0000x" to "12.0", "-0" to "-0.0", " 12" to "0.0", "12 " to "0.0", "+5" to "5.0", ".5" to "0.5",
            "5." to "5.0", "0x1p4" to "16.0", "0x.8" to "0.5", "1_000" to "0.0", "١٢" to "0.0", "" to "0.0",
            "Mon, 21 Sep 2026 14:14:20 GMT" to "60.0", "Mon, 21 Sep 2026 15:14:20 GMT+1" to "60.0", "Mon, 21 Sep 2026 07:14:20 PDT" to "60.0",
            // The weekday is not checked.
            "Fri, 21 Sep 2026 14:14:20 GMT" to "60.0", "Sun, 06 Sep 2020 12:00:12 GMT" to "0.0",
            // RFC 850 and asctime dates, which DateFormatter's one pattern does not read.
            "Monday, 21-Sep-26 14:14:20 GMT" to "0.0", "Mon Sep 21 14:14:20 2026" to "0.0",
            "mon, 21 sep 2026 14:14:20 gmt" to "60.0", "Mon, 21 Sep 2026 14:14:20 UTC" to "60.0", "Mon, 21 Sep 2026 14:14:20" to "0.0",
            "Mon, 21 Sep 2026 14:14:20.5 GMT" to "0.0", "Mon,  21 Sep 2026 14:14:20 GMT" to "60.0", "Mon, 21 Sep 2026 14:14:20 GMT " to "60.0",
            "Mon, 21 Sep 2026 14:14:20 CEST" to "0.0", "12, 13" to "0.0",
        )
        for ((value, expected) in cases) {
            assertEquals("$value", expected, DeliveryApiClient.retryAfterSeconds(value, now).toString())
        }
    }

    @Test
    fun theLookupCountryIsTheRegionOrItsOverride() {
        val cases = listOf(
            "en-GB" to "GB", "en-UK" to "GB", "en-US-u-rg-gbzzzz" to "GB", "en-US-u-rg-ukzzzz" to null, "en" to null, "en-150" to null,
            "en-EU" to null, "sr-CS" to "RS", "en-XK" to "XK", "fr-CH" to "CH", "en-AQ" to "AQ", "en-u-rg-chzzzz" to "CH",
            "de-DE-u-rg-uszzzz" to "US", "en-US-u-rg-zzzzzz" to null,
        )
        for ((tag, expected) in cases) {
            assertEquals(tag, expected, ParcelLookupCountry.hint(Locale.forLanguageTag(tag)))
        }
    }

    @Test
    fun onlyRequestsThatAskCarriersAreSigned() {
        val id = "40000000-0000-0000-0000-000000000001"
        val cases = listOf(
            "/api/packages" to "POST" to true, "/api/packages" to "GET" to false, "/api/carriers/detect" to "POST" to true,
            "/api/sync" to "POST" to true, "/api/sync" to "post" to false, "/api/sync/" to "POST" to false, "/api/sync/jobs" to "POST" to false,
            "/api/packages/claim" to "POST" to false, "/api/packages/$id/sync" to "POST" to true,
            "/api/packages/${id.lowercase(Locale.ROOT)}/sync" to "POST" to true, "/api/packages/$id/sync/" to "POST" to true,
            "//api/packages/$id//sync" to "POST" to true, "/api/packages/not-a-uuid/sync" to "POST" to false,
            "/api/packages/$id/sync" to "PATCH" to false, "api/packages/$id/sync" to "POST" to true, "/api/packages/$id/sync?x=1" to "POST" to false,
        )
        for ((request, expected) in cases) {
            assertEquals("$request", expected, DeliveryApiClient.signsWithAppAttest(request.first, request.second))
        }
    }

    @Test
    fun anAssertionSignsTheChallengeMethodPathAndBodyHash() {
        val empty = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"
        val cases = listOf(
            HttpRequest("https://peek.example/api/sync", "POST") to "synthetic-assertion-challenge" to
                ("native-request-v1\nsynthetic-assertion-challenge\nPOST\n/api/sync\n$empty" to
                    "440d0d8c02984e4aeaf66043ec0eb036cd481e2a90a1c1826f92ac27bc52b1e9"),
            HttpRequest("https://peek.example/api/public/parcels", "POST", body = """{"trackingNumber":"SYNTHETIC"}""".toByteArray()) to
                "synthetic-assertion-challenge" to
                (
                    "native-request-v1\nsynthetic-assertion-challenge\nPOST\n/api/public/parcels\n" +
                        "bc53d0944c5beeaf316d3a7f4adcc50c1d04a20fbf80bd17e79da6e6a1793fb9" to
                        "00d53fd714f4ed0e5faba66877ae04a93d044097fd2e4c6e9b4fabb6de0cd3d2"
                    ),
            // The path as URL.path reads it: percent escapes decoded, query and fragment left out.
            HttpRequest("https://peek.example/api/caf%C3%A9?a=b#c", body = ByteArray(0)) to "caf\u00e9" to
                ("native-request-v1\ncaf\u00e9\nGET\n/api/caf\u00e9\n$empty" to "ec3e989f8325a24fe745995e79b6a3635d9280972b46b7c30f414575cb05ab8d"),
            HttpRequest("https://peek.example", "DELETE", body = ByteArray(0)) to "synthetic-assertion-challenge" to
                ("native-request-v1\nsynthetic-assertion-challenge\nDELETE\n\n$empty" to "a398765edddbda0f6dad2b813011a71d1ff5d6dc38e2854132a9f9a4a159a156"),
        )
        for ((input, expected) in cases) {
            val payload = NativeVerification.payload(input.first, input.second)
            assertEquals(expected.first, payload)
            assertEquals(expected.second, hex(MessageDigest.getInstance("SHA-256").digest(payload.toByteArray())))
        }
    }

    @Test
    fun aRefusalNeedsTheExactHeader() {
        assertTrue(NativeVerification.needsVerification(HttpResponse(403, mapOf("x-lookup-verification" to "required"))))
        assertFalse(NativeVerification.needsVerification(HttpResponse(403, mapOf("X-Lookup-Verification" to "Required"))))
        assertFalse(NativeVerification.needsVerification(HttpResponse(429, mapOf("X-Lookup-Verification" to "required"))))
        assertFalse(NativeVerification.needsVerification(HttpResponse(403)))
    }

    // Live Activity revocations

    private val revocationFormat = ListSerializer(LiveActivityRevocations.Registration.serializer())

    /** Android-only: a retry in a scope already cancelled starts nothing, and leaves the next one free to. */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun aRetryInAScopeAlreadyCancelledLeavesTheNextOneFree() = runTest {
        val persistence = MemorySessionPersistence()
        val transport = RecordingTransport { jsonResponse(200, """{"ok":true}""") }
        val revocations = LiveActivityRevocations(configuration, transport, persistence)
        revocations.registration(ownerId = UUID.randomUUID(), installationId = UUID.randomUUID())
        revocations.queue()

        revocations.retry(CoroutineScope(Job().apply { cancel() }))
        runCurrent()
        assertEquals(0, transport.requests.size)

        // So does one whose scope ends while it waits to try again.
        transport.handler = { throw IOException("offline") }
        val ending = CoroutineScope(coroutineContext + Job())
        revocations.retry(ending)
        runCurrent()
        assertEquals(1, transport.requests.size)
        ending.cancel()
        runCurrent()

        transport.handler = { jsonResponse(200, """{"ok":true}""") }
        revocations.retry(backgroundScope)
        runCurrent()
        assertEquals(listOf("/api/live-activities/revoke", "/api/live-activities/revoke"), transport.requests.map { it.path })
        assertEquals(emptyList<LiveActivityRevocations.Registration>(), decodeSwiftKeys(revocationFormat, persistence.data!!))
    }

    /** Android-only: registrations made on other threads while a drain sends are all kept. */
    @Test
    fun registrationsMadeWhileADrainRunsAreAllKept() = runBlocking {
        val persistence = MemorySessionPersistence()
        val transport = RecordingTransport { jsonResponse(200, """{"ok":true}""") }
        val revocations = LiveActivityRevocations(configuration, transport, persistence)
        val installation = UUID.randomUUID()
        repeat(100) { revocations.registration(ownerId = UUID.randomUUID(), installationId = installation) }
        revocations.queue()
        val owners = List(200) { UUID.randomUUID() }

        withContext(Dispatchers.Default) {
            launch { revocations.drain() }
            for (chunk in owners.chunked(10)) launch { for (owner in chunk) revocations.registration(ownerId = owner, installationId = installation) }
        }

        assertEquals(100, transport.requests.size)
        val saved = decodeSwiftKeys(revocationFormat, persistence.data!!)
        assertEquals(owners.toSet(), saved.map { it.ownerId }.toSet())
        assertTrue(saved.none { it.pending })
    }

    /** The saved revocations are read as KeychainStore reads them, with JSONDecoder.deliveryTracker. */
    @Test
    fun savedRevocationsAreReadAsTheKeychainStoreReadsThem() = runTest {
        val owner = "40000000-0000-0000-0000-000000000006"
        val installation = "40000000-0000-0000-0000-000000000008"
        val token = "a".repeat(64)
        val cases = listOf(
            """[{"ownerID":"$owner","installationID":"$installation","revocation_token":"$token","pending":true}]""" to 1,
            """[{"owner_id":"$owner","installation_id":"$installation","revocation_token":"$token","pending":true}]""" to 0,
            """[{"ownerID":"$owner","installationID":"$installation","revocationToken":"$token","pending":"true"}]""" to 0,
        )
        for ((saved, sent) in cases) {
            val persistence = MemorySessionPersistence().apply { data = saved.toByteArray() }
            val transport = RecordingTransport { jsonResponse(500) }
            LiveActivityRevocations(configuration, transport, persistence).drain()
            assertEquals(saved, sent, transport.requests.size)
        }
    }
}
