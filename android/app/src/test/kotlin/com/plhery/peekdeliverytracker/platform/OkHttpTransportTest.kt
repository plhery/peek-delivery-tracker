package com.plhery.peekdeliverytracker.platform

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.HttpRequest
import com.plhery.peekdeliverytracker.core.HttpResponse
import com.plhery.peekdeliverytracker.core.identifying
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.RecordedRequest
import okhttp3.OkHttp
import okio.Buffer
import org.junit.After
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/** HttpTransport's rules (APIClient.kt) as OkHttpTransport keeps them, against a real server on this machine. */
@RunWith(AndroidJUnit4::class)
class OkHttpTransportTest {
    private val server = MockWebServer()
    private val client = PeekHttp.client(MemoryCookieJar())
    private val transport = OkHttpTransport(client)

    @Before
    fun start() {
        server.start()
    }

    @After
    fun stop() {
        server.close()
    }

    private fun url(path: String = "/"): String = server.url(path).toString()

    private fun send(request: HttpRequest, through: OkHttpTransport = transport): HttpResponse =
        runBlocking { withTimeout(20_000) { through.data(request) } }

    private fun take(): RecordedRequest = server.takeRequest(5, TimeUnit.SECONDS) ?: error("No request")

    private inline fun <reified T : Throwable> failure(block: () -> Unit): T {
        try {
            block()
        } catch (error: Throwable) {
            if (error is T) return error
            throw AssertionError("Expected ${T::class.simpleName}, got $error", error)
        }
        fail("Expected ${T::class.simpleName}")
        error("unreachable")
    }

    @Test
    fun everyStatusIsAnAnswer() {
        for (code in listOf(200, 204, 304, 401, 404, 410, 429, 500, 503)) {
            server.enqueue(MockResponse.Builder().code(code).apply { if (code != 204 && code != 304) body("""{"status":$code}""") }.build())
            val response = send(HttpRequest(url("/api/status")))
            assertEquals(code, response.statusCode)
            if (code != 204 && code != 304) assertEquals("""{"status":$code}""", response.body.decodeToString())
        }
    }

    @Test
    fun aFieldSentTwiceIsOneValueJoinedByACommaAsCfNetworkJoinsIt() {
        server.enqueue(MockResponse.Builder().addHeader("Retry-After", "12").addHeader("retry-after", "30").addHeader("ETag", "\"v6\"").build())
        val response = send(HttpRequest(url()))
        assertEquals("12, 30", response.header("Retry-After"))
        assertEquals("12, 30", response.header("RETRY-AFTER"))
        assertEquals("\"v6\"", response.header("etag"))
    }

    @Test
    fun headerValuesOutsideAsciiGoAsGiven() {
        server.enqueue(MockResponse.Builder().build())
        send(HttpRequest(url(), headers = mapOf("X-Parcel-Label" to "Zürich → Genève ✓", "Accept" to "application/json")))
        val recorded = take()
        assertEquals("Zürich → Genève ✓", recorded.headers["X-Parcel-Label"])
        assertEquals("application/json", recorded.headers["Accept"])
    }

    @Test
    fun aPostPutOrPatchWithoutABodyGoesWithAnEmptyOne() {
        for (method in listOf("POST", "PUT", "PATCH")) {
            server.enqueue(MockResponse.Builder().code(202).build())
            assertEquals(202, send(HttpRequest(url("/api/sync"), method)).statusCode)
            val recorded = take()
            assertEquals(method, recorded.method)
            assertEquals(0L, recorded.bodySize)
            assertEquals("0", recorded.headers["Content-Length"])
            assertNull(recorded.headers["Content-Type"])
        }
        // A DELETE or GET without one goes without any.
        for (method in listOf("DELETE", "GET")) {
            server.enqueue(MockResponse.Builder().code(204).build())
            send(HttpRequest(url("/api/packages"), method))
            val recorded = take()
            assertEquals(method, recorded.method)
            assertNull(recorded.headers["Content-Length"])
        }
    }

    @Test
    fun aBodyGoesWithTheRequestsOwnContentTypeOrNone() {
        server.enqueue(MockResponse.Builder().build())
        server.enqueue(MockResponse.Builder().build())
        send(HttpRequest(url(), "POST", mapOf("Content-Type" to "application/json"), """{"a":1}""".toByteArray()))
        send(HttpRequest(url(), "POST", body = "plain".toByteArray()))
        // No charset added: the request's own field, as given.
        assertEquals("application/json", take().headers["Content-Type"])
        assertNull(take().headers["Content-Type"])
    }

    @Test
    fun bytesGoAndComeBackExactly() {
        val bytes = ByteArray(256) { it.toByte() }
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse =
                MockResponse.Builder().body(Buffer().write(request.body!!.toByteArray())).build()
        }
        val response = send(HttpRequest(url(), "PUT", body = bytes))
        assertArrayEquals(bytes, take().body!!.toByteArray())
        assertArrayEquals(bytes, response.body)
    }

    @Test
    fun aServerThatStopsAnsweringTimesOutAsAnIOException() {
        server.enqueue(MockResponse.Builder().headersDelay(3, TimeUnit.SECONDS).build())
        val started = System.nanoTime()
        val error = failure<IOException> { send(HttpRequest(url(), timeout = 0.3)) }
        // A timeout, not a cancellation: the iPhone counts URLError.timedOut as an error.
        assertTrue("$error", error is SocketTimeoutException)
        assertTrue(System.nanoTime() - started < TimeUnit.SECONDS.toNanos(3))
    }

    @Test
    fun theTimeoutIsForEachWaitNotTheWholeAnswer() {
        // Ten bytes, one every 100 ms: a second in all, three times the timeout, never a wait as long as it.
        server.enqueue(MockResponse.Builder().body("0123456789").throttleBody(1, 100, TimeUnit.MILLISECONDS).build())
        assertEquals("0123456789", send(HttpRequest(url(), timeout = 0.35)).body.decodeToString())
    }

    @Test
    fun aCancelledCoroutineCancelsItsCall() {
        server.enqueue(MockResponse.Builder().headersDelay(10, TimeUnit.SECONDS).build())
        val started = System.nanoTime()
        val error = runBlocking {
            val call = async(Dispatchers.IO) { transport.data(HttpRequest(url())) }
            take()
            call.cancel()
            runCatching { call.await() }.exceptionOrNull()
        }
        assertTrue("$error", error is CancellationException)
        assertTrue(System.nanoTime() - started < TimeUnit.SECONDS.toNanos(8))
        // OkHttp let go of the call too, well before the server would have answered.
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5)
        while (client.dispatcher.runningCallsCount() > 0 && System.nanoTime() < deadline) Thread.sleep(10)
        assertEquals(0, client.dispatcher.runningCallsCount())
    }

    @Test
    fun aCallCancelledFromOutsideItsCoroutineIsAnIOException() {
        server.enqueue(MockResponse.Builder().headersDelay(10, TimeUnit.SECONDS).build())
        val error = runBlocking {
            val call = async(Dispatchers.IO) { runCatching { transport.data(HttpRequest(url())) } }
            take()
            client.dispatcher.cancelAll()
            call.await().exceptionOrNull()
        }
        assertTrue("$error", error is IOException)
    }

    @Test
    fun noServerIsAnIOException() {
        val gone = url()
        server.close()
        failure<IOException> { send(HttpRequest(gone, timeout = 2.0)) }
    }

    @Test
    fun aRequestOkHttpCannotMakeIsAnIOExceptionNotACrash() {
        failure<IOException> { send(HttpRequest("not a url")) }
        failure<IOException> { send(HttpRequest("ftp://example.invalid/file")) }
        failure<IOException> { send(HttpRequest(url(), "GET", body = "x".toByteArray())) }
        failure<IOException> { send(HttpRequest(url(), headers = mapOf("Bad Name" to "x"))) }
        assertEquals(0, server.requestCount)
    }

    @Test
    fun theLogBlanksTheCredentialsAndHoldsNoBody() {
        val lines = mutableListOf<String>()
        val logged = OkHttpTransport(client) { synchronized(lines) { lines += it } }
        server.enqueue(MockResponse.Builder().addHeader("Set-Cookie", "sb=synthetic-cookie; Path=/").body("synthetic-answer-body").build())
        val secrets = mapOf(
            "Authorization" to "Bearer synthetic-access", "X-Parcel-Key" to "synthetic-owner-key", "X-App-Attest-Key-Id" to "synthetic-key-id",
            "X-App-Attest-Challenge" to "synthetic-challenge", "X-App-Attest-Assertion" to "synthetic-assertion",
            "X-Lookup-Proof" to "synthetic-proof",
        )
        send(HttpRequest(url("/api/lookup"), "POST", secrets + ("Accept" to "application/json"), "synthetic-turnstile-token".toByteArray()), logged)
        val log = synchronized(lines) { lines.joinToString("\n") }
        assertEquals(2, lines.size)
        for (secret in secrets.values + listOf("synthetic-cookie", "synthetic-turnstile-token", "synthetic-answer-body")) {
            assertFalse("$secret in\n$log", secret in log)
        }
        for (name in secrets.keys + "Set-Cookie") assertTrue("$name in\n$log", "$name: ██" in log)
        assertTrue(log, "Accept: application/json" in log)
        assertTrue(log, "POST ${url("/api/lookup")}" in log)
    }

    @Test
    fun cookiesGoAndStayOnlyWhenTheRequestHandlesThem() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse = when (request.url.encodedPath) {
                "/set" -> MockResponse.Builder().addHeader("Set-Cookie", "kept=1; Path=/").build()
                "/set-ignored" -> MockResponse.Builder().addHeader("Set-Cookie", "dropped=1; Path=/").build()
                else -> MockResponse.Builder().build()
            }
        }
        send(HttpRequest(url("/set")))
        send(HttpRequest(url("/with")))
        send(HttpRequest(url("/without"), handlesCookies = false))
        send(HttpRequest(url("/set-ignored"), handlesCookies = false))
        send(HttpRequest(url("/after")))
        val cookies = List(5) { take() }.associate { it.url.encodedPath to it.headers["Cookie"] }
        assertNull(cookies["/set"])
        assertEquals("kept=1", cookies["/with"])
        assertNull(cookies["/without"])
        assertNull(cookies["/set-ignored"])
        assertEquals("kept=1", cookies["/after"])
    }

    @Test
    fun nothingIsAnsweredFromACache() {
        server.enqueue(MockResponse.Builder().addHeader("Cache-Control", "public, max-age=600").addHeader("ETag", "\"one\"").body("one").build())
        server.enqueue(MockResponse.Builder().addHeader("Cache-Control", "public, max-age=600").body("two").build())
        assertEquals("one", send(HttpRequest(url("/api/carriers"))).body.decodeToString())
        assertEquals("two", send(HttpRequest(url("/api/carriers"))).body.decodeToString())
        assertEquals(2, server.requestCount)
        take()
        // Nothing asked of the server but what the request asks: no validator of a cached answer.
        assertNull(take().headers["If-None-Match"])
    }

    @Test
    fun theAppNamesItselfAsNoIPhoneDoes() {
        val agent = PeekHttp.userAgent(7, "16")
        assertEquals("PeekDeliveryTracker/7 okhttp/${OkHttp.VERSION} Android/16", agent)
        // src/server/nativeVerification.ts's NATIVE_USER_AGENT, which asks for App Attest.
        val native = Regex("""^PeekDeliveryTracker/\S+ CFNetwork/\S+ Darwin/\S+$""")
        assertFalse(native.matches(agent))
        assertFalse(native.matches(PeekHttp.userAgent(1)))

        server.enqueue(MockResponse.Builder().build())
        server.enqueue(MockResponse.Builder().build())
        runBlocking {
            val identified = transport.identifying(agent)
            identified.data(HttpRequest(url()))
            identified.data(HttpRequest(url(), headers = mapOf("user-agent" to "Analytics/1")))
        }
        assertEquals(agent, take().headers["User-Agent"])
        assertEquals("Analytics/1", take().headers["User-Agent"])
    }
}
