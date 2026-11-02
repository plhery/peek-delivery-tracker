package com.plhery.peekdeliverytracker.ui.arrival

import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.HttpRequest
import com.plhery.peekdeliverytracker.core.HttpResponse
import com.plhery.peekdeliverytracker.core.HttpTransport
import com.plhery.peekdeliverytracker.core.SessionPersistence
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.core.UserDefaults
import com.plhery.peekdeliverytracker.core.WebAuthenticating
import com.plhery.peekdeliverytracker.platform.NoAppleSignIn
import com.plhery.peekdeliverytracker.platform.NoWebAuthentication
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import java.io.IOException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope

/** The made-up address and code the sign-in tests type. */
const val READER_EMAIL = "reader@example.com"
const val READER_CODE = "123456"

/**
 * The app's configuration for the sign-in tests: a made-up auth service (`https://auth.example.test`) and key, with
 * email codes on, and Google and an OIDC provider where a test asks; [configured] false is the debug build's, with no
 * service, where sign-in is unavailable.
 */
fun signInConfiguration(configured: Boolean = true, google: Boolean = false, oidc: Boolean = false, email: Boolean = true) = AppConfiguration(
    mode = AppConfiguration.Mode.Api,
    apiBaseUrl = "https://peek.example.test/",
    supabaseUrl = if (configured) "https://auth.example.test" else null,
    supabasePublishableKey = if (configured) "synthetic-publishable-key" else "",
    googleAuthEnabled = google,
    appleAuthEnabled = false,
    emailOtpEnabled = email,
    appGroupIdentifier = "group.test.peek",
    oidcProvider = if (oidc) "custom:example-sso" else "",
    oidcName = if (oidc) "Example SSO" else "",
)

/**
 * The auth service as the tests answer it, on no network: each request kept in [requests]; a code sent (`otp`) once
 * [held] lets it through, then [otp]'s answer; a code checked (`verify`) [verify]'s, a session for
 * [READER_EMAIL] with made-up tokens by default, as is a browser's code exchanged (`token?grant_type=pkce`).
 */
class FakeAuthService : HttpTransport {
    /** What the service answers. */
    sealed class Answer {
        data object Accepted : Answer()

        data object Session : Answer()

        /** The service refusing, in Supabase Auth's 2024-01-01 shape; by default as it words a code expired or wrong. */
        data class Refused(val code: String = "otp_expired", val message: String = "Token has expired or is invalid") : Answer()

        /** No connection: the request never reaches it. */
        data object Offline : Answer()
    }

    val requests = mutableListOf<HttpRequest>()
    var otp: Answer = Answer.Accepted
    var verify: Answer = Answer.Session

    /** Holds every answer until it completes, for a test to see the run under way. */
    var held: CompletableDeferred<Unit>? = null

    override suspend fun data(request: HttpRequest): HttpResponse {
        requests += request
        held?.await()
        val answer = when {
            request.url.endsWith("/auth/v1/otp") -> otp
            request.url.endsWith("/auth/v1/verify") -> verify
            request.url.endsWith("/auth/v1/token?grant_type=pkce") -> Answer.Session
            else -> Answer.Refused("not_found", "Not found")
        }
        return when (answer) {
            Answer.Accepted -> HttpResponse(200, emptyMap(), "{}".toByteArray())
            Answer.Session -> HttpResponse(200, emptyMap(), SESSION.toByteArray())
            is Answer.Refused -> HttpResponse(
                403, emptyMap(), """{"code":"${answer.code}","message":"${answer.message}"}""".toByteArray(),
            )
            Answer.Offline -> throw IOException("The Internet connection appears to be offline.")
        }
    }

    /** The JSON body of the request sent to [path] (`otp`, `verify`). */
    fun body(path: String): String? = requests.lastOrNull { it.url.endsWith("/auth/v1/$path") }?.body?.decodeToString()

    private companion object {
        const val SESSION = """{"access_token":"synthetic-access-token","token_type":"bearer","expires_in":3600,""" +
            """"refresh_token":"synthetic-refresh-token","user":{"id":"00000000-0000-4000-8000-000000000001",""" +
            """"email":"$READER_EMAIL"}}"""
    }
}

/** The Keychain in memory. */
class MemorySessionPersistence : SessionPersistence {
    var saved: ByteArray? = null

    override fun save(data: ByteArray) {
        saved = data
    }

    override fun load(): ByteArray? = saved

    override fun delete() {
        saved = null
    }
}

/**
 * The real session store, on [service], the Keychain and [defaults] in memory, its actions counted to [analytics]
 * (event and outcome), the browser's sign-in [webAuthentication]'s; `scope` needs a dispatcher, as the app's does.
 * Nothing reaches a server.
 */
fun signInSession(
    scope: CoroutineScope,
    configuration: AppConfiguration = signInConfiguration(),
    service: HttpTransport = FakeAuthService(),
    analytics: (String, DeliveryAnalytics.Outcome?) -> Unit = { _, _ -> },
    webAuthentication: WebAuthenticating = NoWebAuthentication,
    defaults: UserDefaults = MemoryDefaults(),
) = SessionStore(
    configuration, defaults, MemorySessionPersistence(), service, NoAppleSignIn, webAuthentication, scope, analytics,
)
