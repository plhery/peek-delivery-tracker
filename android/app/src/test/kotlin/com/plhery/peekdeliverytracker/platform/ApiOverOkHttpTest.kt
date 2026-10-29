package com.plhery.peekdeliverytracker.platform

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierCatalogRefreshResult
import com.plhery.peekdeliverytracker.core.DeliveryApiClient
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.DeviceAlertAnswer
import com.plhery.peekdeliverytracker.core.DeviceParcelClient
import com.plhery.peekdeliverytracker.core.HttpRequest
import com.plhery.peekdeliverytracker.core.HttpTransport
import com.plhery.peekdeliverytracker.core.NativeAttestProvider
import com.plhery.peekdeliverytracker.core.NativeVerification
import com.plhery.peekdeliverytracker.core.NativeVerificationFailed
import com.plhery.peekdeliverytracker.core.ParcelLinkFailure
import com.plhery.peekdeliverytracker.core.ParcelLinkStopped
import com.plhery.peekdeliverytracker.core.SharedResources
import com.plhery.peekdeliverytracker.core.TurnstilePresenter
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.DeleteParcelAppAlertRequest
import com.plhery.peekdeliverytracker.core.api.NativePushEnvironment
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.ParcelAlertPreset
import com.plhery.peekdeliverytracker.core.api.ParcelAppAlertRequest
import com.plhery.peekdeliverytracker.core.api.ShareParcelRequest
import java.io.File
import java.util.Locale
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.runBlocking
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.RecordedRequest
import okio.Buffer
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith

/**
 * The requests :core's tests check through a recording transport (ParcelLinkTests, ParcelShareTests,
 * CarrierCatalogTests), here as OkHttp sends them: the bytes, fields and cookies a server receives.
 */
@RunWith(AndroidJUnit4::class)
class ApiOverOkHttpTest {
    @get:Rule
    val folder = TemporaryFolder()

    private val server = MockWebServer()
    private var answer: (RecordedRequest) -> MockResponse = { MockResponse.Builder().code(404).build() }
    private val sent = mutableListOf<HttpRequest>()
    private val okHttp = OkHttpTransport(PeekHttp.client(MemoryCookieJar()))

    /** The app's transport, noting each request as :core made it. */
    private val transport = HttpTransport { request ->
        synchronized(sent) { sent += request }
        okHttp.data(request)
    }

    private val linkId = "k7Qm2xHd9RtW"

    private lateinit var configuration: AppConfiguration

    @Before
    fun start() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse = answer(request)
        }
        server.start()
        configuration = AppConfiguration(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = server.url("/").toString().trimEnd('/'), supabaseUrl = null,
            supabasePublishableKey = "", googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true,
            appGroupIdentifier = "links.test",
        )
    }

    @After
    fun stop() {
        server.close()
    }

    private fun json(code: Int, body: String = "", vararg headers: Pair<String, String>): MockResponse =
        MockResponse.Builder().code(code).body(body).apply { for ((name, value) in headers) addHeader(name, value) }.build()

    private fun take(): RecordedRequest = server.takeRequest(5, TimeUnit.SECONDS) ?: error("No request")

    /** No attestation, as on Android, and no browser check asked of the test. */
    private fun verification() = NativeVerification(
        NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) }),
        TurnstilePresenter { throw NativeVerificationFailed() }, emptyMap(), {}, language = { AppLanguage.En },
    )

    private val publicParcel: String by lazy {
        JSONObject(SharedResources.text("ContractFixtures.json")).getJSONObject("publicParcel").toString()
    }

    /** A cookie the server set on the API's host, which no link request may carry. */
    private fun keepACookie() {
        answer = { MockResponse.Builder().addHeader("Set-Cookie", "sb-session=synthetic; Path=/").build() }
        runBlocking { transport.data(HttpRequest(server.url("/auth").toString())) }
        take()
    }

    private fun assertNoCredentials(request: RecordedRequest) {
        for (name in listOf("Authorization", "Cookie", "X-Parcel-Key")) assertNull(name, request.headers[name])
    }

    @Test
    fun theLinkIsReadWithoutSignInCookiesOrAnOwnerKey() = runBlocking {
        keepACookie()
        answer = { json(200, publicParcel) }
        val shown = DeliveryApiClient.publicParcel(linkId, configuration = configuration, transport = transport)
        assertEquals(linkId, shown?.link?.id)
        assertEquals("3456", shown?.parcel?.numberHint?.tail)
        val request = take()
        assertEquals("GET", request.method)
        assertEquals("/api/public/parcels/$linkId", request.url.encodedPath)
        assertEquals("application/json", request.headers["Accept"])
        assertNoCredentials(request)
        assertEquals(15.0, sent.last().timeout, 0.0)

        answer = { json(404, """{"error":"Parcel unavailable"}""") }
        assertNull(DeliveryApiClient.publicParcel(linkId, configuration = configuration, transport = transport))

        answer = { json(410, """{"error":"Sharing stopped"}""") }
        try {
            DeliveryApiClient.publicParcel(linkId, configuration = configuration, transport = transport)
            fail("Expected a stopped link")
        } catch (_: ParcelLinkStopped) {
        }

        answer = { json(429, """{"error":"Too many requests. Try again shortly."}""", "Retry-After" to "12") }
        try {
            DeliveryApiClient.publicParcel(linkId, configuration = configuration, transport = transport)
            fail("Expected a rate limit")
        } catch (error: DeliveryApiError.RateLimited) {
            assertEquals(12.0, error.retryAfter, 0.0)
        }

        for ((code, body) in listOf(503 to "", 200 to "{}")) {
            answer = { json(code, body) }
            try {
                DeliveryApiClient.publicParcel(linkId, configuration = configuration, transport = transport)
                fail("Expected a failure")
            } catch (error: Exception) {
                assertEquals(ParcelLinkFailure.Service, ParcelLinkFailure(error))
            }
        }

        // No server: offline, as URLError reads.
        val gone = configuration.copy(apiBaseUrl = server.url("/").toString().trimEnd('/'))
        server.close()
        try {
            DeliveryApiClient.publicParcel(linkId, configuration = gone, transport = transport)
            fail("Expected no connection")
        } catch (error: Exception) {
            assertEquals(ParcelLinkFailure.Offline, ParcelLinkFailure(error))
        }
    }

    @Test
    fun aHandedOverLinkSendsItsKeyInAHeaderAlone() = runBlocking {
        keepACookie()
        answer = { json(200, publicParcel) }
        val key = "K".repeat(43)
        DeliveryApiClient.publicParcel(linkId, key, configuration = configuration, transport = transport)
        val request = take()
        assertEquals("/api/public/parcels/$linkId", request.url.encodedPath)
        assertNull(request.url.encodedQuery)
        assertEquals(key, request.headers["X-Parcel-Key"])
        assertNull(request.headers["Authorization"])
        assertNull(request.headers["Cookie"])
    }

    @Test
    fun aRefusalIsAskedWithoutSignInOrCookies() = runBlocking {
        keepACookie()
        answer = { json(200, """{"refused":true}""") }
        val question = NumberRefusalRequest(trackingNumber = "SYNTHREFUSAL01", carrier = CarrierId.NzCouriers)
        assertTrue(DeliveryApiClient.numberRefusal(question, configuration = configuration, transport = transport))
        val request = take()
        assertEquals("POST", request.method)
        assertEquals("/api/public/refusal", request.url.encodedPath)
        assertEquals("application/json", request.headers["Content-Type"])
        assertNoCredentials(request)
        // As JSONEncoder.deliveryTracker writes it, keys sorted.
        assertEquals("""{"carrier":"nz-couriers","trackingNumber":"SYNTHREFUSAL01"}""", request.body?.utf8())
    }

    @Test
    fun appAlertsGoWithTheKeyOnlyForTheOwnerAndStopWithTheTokenAlone() = runBlocking {
        keepACookie()
        var code = 204
        answer = { MockResponse.Builder().code(code).build() }
        val client = DeviceParcelClient.api(configuration, transport, verification(), region = { Locale.UK })
        val key = "K".repeat(43)
        val alert = ParcelAppAlertRequest(
            token = "a1b2c3d4", environment = NativePushEnvironment.Production, preset = ParcelAlertPreset.All, locale = NativePushLocale.Fr,
        )

        assertEquals(DeviceAlertAnswer.On, client.setAlert(linkId, key, alert))
        assertEquals(DeviceAlertAnswer.On, client.setAlert(linkId, null, alert))
        val requests = listOf(take(), take())
        assertEquals(listOf(key, null), requests.map { it.headers["X-Parcel-Key"] })
        for (request in requests) {
            assertEquals("PUT", request.method)
            assertEquals("/api/public/parcels/$linkId/app-alerts", request.url.encodedPath)
            assertNull(request.headers["Authorization"])
            assertNull(request.headers["Cookie"])
            assertEquals("application/json", request.headers["Content-Type"])
            assertEquals("""{"environment":"production","locale":"fr","preset":"all","token":"a1b2c3d4"}""", request.body?.utf8())
        }

        for ((status, expected) in listOf(
            404 to DeviceAlertAnswer.Gone, 409 to DeviceAlertAnswer.Full, 410 to DeviceAlertAnswer.Stopped, 503 to DeviceAlertAnswer.Unavailable,
        )) {
            code = status
            assertEquals("$status", expected, client.setAlert(linkId, null, alert))
            take()
        }

        code = 204
        client.removeAlert(linkId, DeleteParcelAppAlertRequest(token = "a1b2c3d4"))
        val removal = take()
        assertEquals("DELETE", removal.method)
        assertEquals("/api/public/parcels/$linkId/app-alerts", removal.url.encodedPath)
        assertNull(removal.headers["X-Parcel-Key"])
        assertNull(removal.headers["Cookie"])
        assertEquals("""{"token":"a1b2c3d4"}""", removal.body?.utf8())
    }

    /** SessionStore's part, signed in with a synthetic token. */
    private val session = object : DeliveryApiClient.Session {
        override val generation: UUID = UUID.randomUUID()
        override val signedIn: Boolean = true

        override suspend fun accessToken(forceRefresh: Boolean): String = "synthetic-access"

        override suspend fun forceSignOut() = fail("Unexpected sign-out")
    }

    private fun accountClient() =
        DeliveryApiClient(configuration, session, transport, verification(), deviceName = "Synthetic phone", region = { Locale.UK })

    @Test
    fun sharingGoesThroughTheAccount() = runBlocking {
        val id = UUID.fromString("43000000-0000-0000-0000-000000000004")
        val path = "/api/packages/${id.toString().uppercase()}/share"
        val shared = JSONObject(SharedResources.text("ContractFixtures.json")).getJSONObject("parcelShare").toString()
        answer = { request ->
            when (request.method) {
                "GET" -> json(200, """{"link":null}""")
                "PUT" -> json(200, shared)
                else -> MockResponse.Builder().code(204).build()
            }
        }
        val client = accountClient()

        assertNull(client.parcelShare(id))
        assertEquals("g8Rn3yJe2SuX", client.shareParcel(id, ShareParcelRequest(showNumber = false, gift = true)).id)
        client.stopSharingParcel(id)

        val requests = List(3) { take() }
        assertEquals(listOf("GET", "PUT", "DELETE"), requests.map { it.method })
        for (request in requests) {
            assertEquals(path, request.url.encodedPath)
            assertEquals("Bearer synthetic-access", request.headers["Authorization"])
            assertEquals("XMLHttpRequest", request.headers["X-Requested-With"])
        }
        // Only what changes, keys sorted.
        assertEquals("""{"gift":true,"showNumber":false}""", requests[1].body?.utf8())
        assertEquals("application/json", requests[1].headers["Content-Type"])
        assertEquals(0L, requests[2].bodySize)
    }

    @Test
    fun aRefreshOfEveryParcelIsAPostWithAnEmptyBody() = runBlocking {
        answer = { request ->
            if (request.url.encodedPath == "/api/sync") {
                json(202, """{"queued":true,"pending":1,"jobIds":["42000000-0000-0000-0000-000000000004"]}""")
            } else {
                json(404)
            }
        }
        assertEquals(listOf(UUID.fromString("42000000-0000-0000-0000-000000000004")), accountClient().queueRefreshAll())
        val request = take()
        assertEquals("POST", request.method)
        assertEquals("/api/sync", request.url.encodedPath)
        assertEquals("0", request.headers["Content-Length"])
        assertEquals(0L, request.bodySize)
        assertNull(request.headers["Content-Type"])
        assertEquals("Bearer synthetic-access", request.headers["Authorization"])
        assertEquals(30.0, sent.last().timeout, 0.0)
    }

    @Test
    fun theCatalogIsFetchedThenRevalidatedWithItsTagPastAnyCache() = runBlocking {
        val bundled = SharedResources.open("CarrierCatalog.json").use { it.readBytes() }
        answer = { request ->
            if (request.headers["If-None-Match"] == "\"synthetic-v1\"") {
                MockResponse.Builder().code(304).addHeader("ETag", "\"synthetic-v1\"").build()
            } else {
                MockResponse.Builder().body(Buffer().write(bundled)).addHeader("ETag", "\"synthetic-v1\"")
                    .addHeader("Cache-Control", "public, max-age=3600").build()
            }
        }
        val cacheFile = CarrierCatalog.cacheFile(folder.newFolder("caches"))
        val first = CarrierCatalog(cacheFile, CarrierCatalogLoader(transport))
        assertEquals(CarrierCatalogRefreshResult.Updated, first.refresh(configuration.apiBaseUrl, force = true))
        assertTrue(cacheFile.exists())
        val fetch = take()
        assertEquals("GET", fetch.method)
        assertEquals("/api/carriers", fetch.url.encodedPath)
        assertEquals("application/json", fetch.headers["Accept"])
        assertNull(fetch.headers["If-None-Match"])
        assertEquals(15.0, sent.last().timeout, 0.0)

        val cached = CarrierCatalog(cacheFile, CarrierCatalogLoader(transport))
        assertEquals(CarrierCatalogRefreshResult.NotModified, cached.refresh(configuration.apiBaseUrl, force = true))
        assertEquals("\"synthetic-v1\"", take().headers["If-None-Match"])
        // A max-age answer is never served again from a cache: each refresh reaches the server.
        assertEquals(CarrierCatalogRefreshResult.Updated, CarrierCatalog(File(folder.root, "none.json"), CarrierCatalogLoader(transport))
            .refresh(configuration.apiBaseUrl, force = true))
        assertFalse(take().headers.names().contains("If-None-Match"))
        assertEquals(3, server.requestCount)
    }
}
