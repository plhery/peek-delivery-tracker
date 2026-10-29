package com.plhery.peekdeliverytracker.platform

import android.os.Build
import com.plhery.peekdeliverytracker.core.HttpRequest
import com.plhery.peekdeliverytracker.core.HttpResponse
import com.plhery.peekdeliverytracker.core.HttpTransport
import java.io.IOException
import java.io.InterruptedIOException
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.math.roundToLong
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.Dns
import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

/**
 * URLSession's `data(for:)` over OkHttp, by HttpTransport's rules (APIClient.kt): every status is an answer; a request
 * OkHttp cannot make, a network failure and a timeout are IOExceptions; a cancelled coroutine cancels its call and ends
 * in a CancellationException. Nothing is cached: `client` has no cache, so every request reaches the server.
 *
 * Each request gets its own timeouts, `HttpRequest.timeout` for the name lookup ([TimedDns]) and as connect, read and
 * write timeouts, as URLRequest's `timeoutInterval` limits each wait, the lookup included, and not the whole answer:
 * OkHttp's call timeout, which would, is left off. A request with `handlesCookies` false sends no cookie and keeps none,
 * whatever `client`'s cookie jar holds.
 */
class OkHttpTransport(
    private val client: OkHttpClient,
    /** Where the redacted request and answer lines go (Logcat in a debug build); nowhere when null. */
    private val log: ((String) -> Unit)? = null,
) : HttpTransport {
    private val logging = log?.let { RedactedLog(it) }

    override suspend fun data(request: HttpRequest): HttpResponse {
        val call = client(request).newCall(okRequest(request))
        return suspendCancellableCoroutine { continuation ->
            continuation.invokeOnCancellation { call.cancel() }
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    // Read here, on OkHttp's thread: a slow body never holds the caller's.
                    val answer = try {
                        response.use(::httpResponse)
                    } catch (e: IOException) {
                        continuation.resumeWithException(e)
                        return
                    }
                    continuation.resume(answer)
                }
            })
        }
    }

    private fun client(request: HttpRequest): OkHttpClient {
        val timeout = if (request.timeout > 0) (request.timeout * 1_000).roundToLong().coerceAtLeast(1) else DEFAULT_TIMEOUT_MILLIS
        return client.newBuilder()
            .dns(TimedDns(client.dns, timeout))
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .apply { if (!request.handlesCookies) cookieJar(CookieJar.NO_COOKIES) }
            .apply { logging?.let(::addInterceptor) }
            .build()
    }

    companion object {
        private const val DEFAULT_TIMEOUT_MILLIS = 60_000L

        /** The methods OkHttp makes carry a body: one sent without its own goes with an empty one, as URLSession's does. */
        private val bodyMethods = setOf("POST", "PUT", "PATCH")

        /**
         * The request as OkHttp takes it: header values outside ASCII as given (`addUnsafeNonAscii`), each body without
         * a media type, so the request's own `Content-Type`, or none, goes. What OkHttp refuses (an address it cannot
         * read, a header name it cannot send, a body on a GET) is a request that could not be sent: an IOException.
         */
        fun okRequest(request: HttpRequest): Request {
            val url: HttpUrl = request.url.toHttpUrlOrNull() ?: throw IOException("Unsupported URL")
            try {
                val headers = Headers.Builder()
                for ((name, value) in request.headers) headers.addUnsafeNonAscii(name, value)
                val body = request.body?.toRequestBody(null)
                    ?: if (request.method.uppercase() in bodyMethods) ByteArray(0).toRequestBody(null) else null
                return Request.Builder().url(url).headers(headers.build()).method(request.method, body).build()
            } catch (e: IllegalArgumentException) {
                throw IOException("The request could not be made", e)
            }
        }

        /** A field the server sent more than once is one value, joined by ", ", as CFNetwork joins them. */
        fun httpResponse(response: Response): HttpResponse {
            val headers = LinkedHashMap<String, String>()
            for (name in response.headers.names()) headers[name] = response.headers.values(name).joinToString(", ")
            return HttpResponse(response.code, headers, response.body.bytes())
        }
    }
}

/**
 * `dns`'s lookups, each given `timeoutMillis` as URLSession gives a connection's name lookup the request's timeout:
 * OkHttp's connect timeout starts only once the addresses are known, and the system's lookup may take far longer. A
 * lookup that outlasts it goes on alone on its own thread, and the request fails as a host that could not be found.
 */
internal class TimedDns(private val dns: Dns, private val timeoutMillis: Long) : Dns {
    override fun lookup(hostname: String): List<InetAddress> {
        val lookup = FutureTask { dns.lookup(hostname) }
        lookups.execute(lookup)
        return try {
            lookup.get(timeoutMillis, TimeUnit.MILLISECONDS)
        } catch (e: TimeoutException) {
            lookup.cancel(true)
            throw UnknownHostException("No address for $hostname within $timeoutMillis ms").apply { initCause(e) }
        } catch (e: ExecutionException) {
            when (val cause = e.cause) {
                is IOException -> throw cause
                is RuntimeException -> throw cause
                is Error -> throw cause
                else -> throw UnknownHostException(hostname).apply { initCause(cause) }
            }
        } catch (e: InterruptedException) {
            lookup.cancel(true)
            Thread.currentThread().interrupt()
            throw InterruptedIOException("Interrupted looking up $hostname").apply { initCause(e) }
        }
    }

    /**
     * Equal to any other over the same `dns`, whatever its timeout: OkHttp reuses a connection only for an address with
     * an equal `Dns`, and every request's client has one of these.
     */
    override fun equals(other: Any?): Boolean = other is TimedDns && other.dns == dns

    override fun hashCode(): Int = dns.hashCode()

    private companion object {
        /** A thread for each lookup under way: one the system holds up holds up no other. */
        val lookups: ExecutorService = Executors.newCachedThreadPool { task -> Thread(task, "Peek DNS").apply { isDaemon = true } }
    }
}

/** The OkHttp client the app's requests share: one connection pool, no cache, cookies in `cookies`. */
object PeekHttp {
    fun client(cookies: CookieJar): OkHttpClient = OkHttpClient.Builder().cache(null).cookieJar(cookies).build()

    /**
     * The app's own `User-Agent`, which the server must not take for the iPhone's (`identifying` in APIClient.kt):
     * `PeekDeliveryTracker/<versionCode> okhttp/<version> Android/<release>`.
     */
    fun userAgent(versionCode: Int, release: String = Build.VERSION.RELEASE.orEmpty()): String =
        "PeekDeliveryTracker/$versionCode okhttp/${OkHttp.VERSION} Android/${release.filterNot(Char::isWhitespace)}"
}

/**
 * The cookies the server sets, kept in memory for the process, as HTTPCookieStorage keeps them for URLSession: each
 * replaces one of the same name, domain and path, and goes to every request it matches until it expires.
 */
class MemoryCookieJar : CookieJar {
    private val cookies = mutableListOf<Cookie>()

    @Synchronized
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        for (cookie in cookies) {
            this.cookies.removeAll { it.name == cookie.name && it.domain == cookie.domain && it.path == cookie.path }
            this.cookies += cookie
        }
    }

    @Synchronized
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val now = System.currentTimeMillis()
        cookies.removeAll { it.expiresAt < now }
        return cookies.filter { it.matches(url) }
    }
}

/**
 * One line for each request and answer, their headers with the credentials blanked and never a body: the browser
 * check's token and the push and revocation tokens go in bodies (HttpTransport's rules).
 */
internal class RedactedLog(private val log: (String) -> Unit) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        log("--> ${request.method} ${request.url} ${fields(request.headers)}")
        val started = System.nanoTime()
        val response = try {
            chain.proceed(request)
        } catch (e: IOException) {
            log("<-- failed ${request.method} ${request.url}: ${e.javaClass.simpleName}")
            throw e
        }
        val millis = (System.nanoTime() - started) / 1_000_000
        log("<-- ${response.code} ${request.method} ${request.url} (${millis} ms) ${fields(response.headers)}")
        return response
    }

    companion object {
        /** The credentials HttpTransport names, and the cookies, which are credentials too. */
        val redacted = setOf(
            "Authorization", "X-Parcel-Key", "X-App-Attest-Key-Id", "X-App-Attest-Challenge", "X-App-Attest-Assertion",
            "X-Lookup-Proof", "Cookie", "Set-Cookie",
        )

        fun fields(headers: Headers): String = headers.joinToString(prefix = "[", postfix = "]") { (name, value) ->
            "$name: ${if (redacted.any { it.equals(name, ignoreCase = true) }) "██" else value}"
        }
    }
}
