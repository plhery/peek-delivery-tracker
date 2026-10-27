package com.plhery.peekdeliverytracker.core

import java.util.UUID

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
