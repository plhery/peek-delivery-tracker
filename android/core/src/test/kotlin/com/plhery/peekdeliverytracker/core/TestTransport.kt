package com.plhery.peekdeliverytracker.core

import java.security.SecureRandom
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.CoroutineScope

/** The tests' URLProtocol: notes every request, then answers it with `handler`. */
internal class RecordingTransport(var handler: suspend (HttpRequest) -> HttpResponse = { HttpResponse(200) }) : HttpTransport {
    private val sent = mutableListOf<HttpRequest>()

    val requests: List<HttpRequest>
        get() = synchronized(sent) { sent.toList() }

    override suspend fun data(request: HttpRequest): HttpResponse {
        synchronized(sent) { sent += request }
        return handler(request)
    }
}

/** An answer with a JSON body. */
internal fun jsonResponse(status: Int, body: String = "", headers: Map<String, String> = emptyMap()): HttpResponse =
    HttpResponse(status, headers, body.toByteArray())

/** The path of a request's URL, as `request.url?.path` reads it. */
internal val HttpRequest.path: String?
    get() = Url.parse(url)?.path

/**
 * SessionStore's part in the client's requests: signed in with `token`, which a forced refresh replaces with
 * `refreshed`. `log` notes each call, as the differential harness does.
 */
internal class TestSession(
    var token: String? = "synthetic-access",
    var refreshed: String? = "synthetic-refreshed",
) : DeliveryApiClient.Session {
    var user: String? = "synthetic-user"
    var refreshFailure: Exception? = null
    val log = mutableListOf<String>()

    override var generation: UUID = UUID.randomUUID()

    override val signedIn: Boolean
        get() = user != null

    override suspend fun accessToken(forceRefresh: Boolean): String? {
        if (!forceRefresh) {
            log += "token"
            return token
        }
        log += "refresh"
        refreshFailure?.let { throw it }
        return refreshed
    }

    override fun forceSignOut() {
        log += "signOut"
        user = null
        generation = UUID.randomUUID()
    }
}

/** The Keychain in memory: a second reader of the same instance finds what the first saved. */
internal class MemorySessionPersistence : SessionPersistence {
    var data: ByteArray? = null

    override fun save(data: ByteArray) {
        this.data = data
    }

    override fun load(): ByteArray? = data

    override fun delete() {
        data = null
    }
}

/** The Swift tests' StubAppleSignIn: notes the nonce each sign-in asks with, runs `beforeReturning`, then answers `result`. */
internal class StubAppleSignIn : AppleSignInAuthorizing {
    val nonces = mutableListOf<String>()
    var result: Result<AppleSignInCredential> = Result.success(
        AppleSignInCredential(identityToken = "apple-identity-token", authorizationCode = "apple-code", user = "apple-user"),
    )
    var beforeReturning: (() -> Unit)? = null

    override suspend fun credential(nonce: String): AppleSignInCredential {
        nonces += nonce
        beforeReturning?.invoke()
        return result.getOrThrow()
    }
}

/**
 * `SessionStore(configuration:defaults:persistence:transport:appleSignIn:)` as the Swift tests make it. `scope` is where
 * a refresh runs: a test's `backgroundScope`. No test opens the browser sign-in.
 */
internal fun testSessionStore(
    configuration: AppConfiguration,
    scope: CoroutineScope,
    persistence: SessionPersistence = MemorySessionPersistence(),
    // An unexpected request fails the test, as the Swift tests' `XCTFail` in a handler: an Error, which no catch hides.
    transport: HttpTransport = HttpTransport { throw AssertionError("Unexpected request ${it.method} ${it.url}") },
    appleSignIn: AppleSignInAuthorizing = StubAppleSignIn(),
    defaults: UserDefaults = MemoryDefaults(),
    webAuthentication: WebAuthenticating = WebAuthenticating { url, _ -> throw AssertionError("Unexpected browser sign-in at $url") },
    analytics: (String, DeliveryAnalytics.Outcome?) -> Unit = { _, _ -> },
    now: () -> Instant = Instant::now,
    random: (Int) -> ByteArray = { count -> ByteArray(count).also { SecureRandom().nextBytes(it) } },
): SessionStore = SessionStore(configuration, defaults, persistence, transport, appleSignIn, webAuthentication, scope, analytics, now, random)
