package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.security.MessageDigest
import java.security.SecureRandom
import java.time.Instant
import java.util.Base64
import java.util.UUID
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject

// Authentication.swift. The platform's parts are the app's: the Keychain is a `SessionPersistence`,
// ASWebAuthenticationSession a `WebAuthenticating` (Custom Tabs) and ASAuthorizationController an
// `AppleSignInAuthorizing` (Apple's web sign-in); URLSession is the `HttpTransport` every client shares.

@Serializable
data class AuthUser(
    @Serializable(with = UuidSerializer::class) val id: UUID,
    val email: String? = null,
    val isAnonymous: Boolean? = null,
    /** The ways the account signs in. Sessions saved before this was kept have none until they refresh. */
    val identities: List<Identity>? = null,
) {
    @Serializable
    data class Identity(val provider: String, val identityData: IdentityData? = null)

    @Serializable
    data class IdentityData(val sub: String? = null) {
        /** The Apple user ID never goes in a log. */
        override fun toString(): String = "IdentityData(sub=${sub?.let { "<redacted>" }})"
    }

    /** The Apple user IDs the account signs in with. */
    val appleSubjects: List<String>
        get() = (identities ?: emptyList()).filter { it.provider == "apple" }.mapNotNull { it.identityData?.sub }

    /** The email and the Apple user IDs never go in a log, nor do the session and the state that print the user. */
    override fun toString(): String =
        "AuthUser(id=$id, email=${email?.let { "<redacted>" }}, isAnonymous=$isAnonymous, identities=$identities)"
}

/**
 * A session as the auth service answers it (read with `decodeSwiftKeys`, its snake_case keys or the Swift names) and
 * as the Keychain keeps it: the bytes JsonEncoder.deliveryTracker writes, under the Swift model's names, so a session
 * reads alike on either side.
 */
@Serializable
data class AuthSession(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Long,
    val expiresAt: Long? = null,
    val refreshToken: String,
    val user: AuthUser,
) {
    /**
     * `expirationDate.timeIntervalSinceNow`: the seconds left before it expires, in a Date's arithmetic (seconds since
     * 2001 as a Double), which the 60 seconds SessionStore refreshes ahead are compared with.
     */
    fun secondsUntilExpiration(now: Instant): Double {
        // Legacy sessions without an absolute expiry must refresh.
        val expiration = expiresAt?.let { it.toDouble() - SECONDS_FROM_1970_TO_2001 } ?: DISTANT_PAST
        return expiration - now.timeIntervalSinceReferenceDate
    }

    /** The tokens are credentials: they never go in a log. */
    override fun toString(): String =
        "AuthSession(accessToken=<redacted>, tokenType=$tokenType, expiresIn=$expiresIn, expiresAt=$expiresAt, " +
            "refreshToken=<redacted>, user=$user)"
}

private const val SECONDS_FROM_1970_TO_2001 = 978_307_200.0

/** `Date.distantPast.timeIntervalSinceReferenceDate`: the year 1. */
private const val DISTANT_PAST = -63_114_076_800.0

sealed class AuthenticationState {
    data object Loading : AuthenticationState()

    data object Welcome : AuthenticationState()

    data object Demo : AuthenticationState()

    data object Unconfigured : AuthenticationState()

    data object SignedOut : AuthenticationState()

    data class SignedIn(val user: AuthUser) : AuthenticationState()

    val identity: String?
        get() = when (this) {
            is SignedIn -> user.id.uuidString
            Demo -> "demo"
            // Nobody is signed in: the parcels this phone follows itself.
            Welcome -> "device"
            else -> null
        }
}

/**
 * Where a secret is kept on this device: the Keychain on the iPhone (KeychainStore), the app's encrypted storage on
 * Android. The value is held as the bytes JsonEncoder.deliveryTracker writes, and read back with ApiJson.
 *
 * KeychainStore keeps each value as one generic password, under the store's service and the account "session",
 * readable once the phone was first unlocked after a restart and never leaving it
 * (`kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly`). On Android: the bytes encrypted with an AES-GCM key of the
 * Android Keystore, in the app's own credential-encrypted storage, one entry per service, left out of backups and
 * device transfers (`dataExtractionRules`), as a value tied to this device's key would be unreadable elsewhere. The
 * session store's service is "com.plhery.PeekDeliveryTracker.auth".
 *
 * `save` and `delete` are done once they return, as the Keychain's are: SharedPreferences' `commit()` or a file
 * written and synced, never `apply()` or an asynchronous DataStore write. Otherwise a sign-out followed by the app
 * being killed reopens the account at the next launch, and a refreshed session lost that way leaves the refresh token
 * already used, which the auth service then refuses.
 */
interface SessionPersistence {
    /** Throws when the value could not be saved; written to storage once it returns. */
    fun save(data: ByteArray)

    /** Nil when nothing is saved, or when it cannot be read (a Keystore key lost, say): it never throws for that. */
    fun load(): ByteArray?

    /** Never throws: nothing left to delete is no failure. Gone from storage once it returns. */
    fun delete()
}

sealed class AuthenticationError(message: String) : Exception(message) {
    class NotConfigured : AuthenticationError("Authentication is not configured.")
    class InvalidResponse : AuthenticationError("The authentication service returned an invalid response.")
    class MissingSession : AuthenticationError("The sign-in code did not create a session.")
    class OauthCancelled : AuthenticationError("Sign-in was cancelled.")
    class RequestFailed(val status: Int) : AuthenticationError("Sign-in failed ($status).")
    class SecureRequestFailed : AuthenticationError("A secure sign-in request could not be created.")
    class SessionSaveFailed : AuthenticationError("The secure session could not be saved.")
    class OtherAppleAccount : AuthenticationError("This Apple Account doesn't sign in to this Peek account.")
    class Server(val serverMessage: String) : AuthenticationError(serverMessage)
}

/**
 * Combine's `PassthroughSubject<Void, Never>` as SessionStore uses it: `send` calls every sink at once, in the order
 * they were added, on the thread that sends, so a sink sees the store as the change left it. Closing what `sink`
 * returns stops it, as cancelling an AnyCancellable does; nothing stops it otherwise.
 */
class PassthroughSignal {
    private val sinks = CopyOnWriteArrayList<Sink>()

    private class Sink(val receive: () -> Unit)

    fun sink(receive: () -> Unit): AutoCloseable {
        val sink = Sink(receive)
        sinks += sink
        return AutoCloseable { sinks -= sink }
    }

    internal fun send() {
        for (sink in sinks) sink.receive()
    }
}

/**
 * The browser sign-in, ASWebAuthenticationSession on the iPhone: opens `url` and gives back the address the sign-in
 * ends on, the first one in `callbackScheme` (`OAuthFlow.callbackUrl` with its query). The browser shares its cookies
 * with the app's sign-in, as the iPhone's does (`prefersEphemeralWebBrowserSession = false`): someone signed in to
 * Google there is not asked again.
 *
 * On Android a Custom Tab (androidx.browser's `CustomTabsIntent`), or the browser itself when no browser offers Custom
 * Tabs. The address comes back as an intent: an activity whose intent filter takes `callbackScheme` with the host
 * `OAuthFlow.callbackHost` (BROWSABLE and DEFAULT, `singleTop` or a transparent one that finishes at once) hands its
 * `intent.data` to the call under way, which returns it as a string. That host is this call's alone: NativeRoute and
 * the invitation links leave it alone, and the app's other `peekdeliverytracker://` intent filters each name their
 * host, as one with the scheme alone also takes `auth-callback` and Android would offer both activities in a chooser.
 * A custom scheme is no app's own on Android, where another app may claim it too; PKCE makes that harmless here, as
 * the code is worth nothing without the verifier SessionStore keeps.
 * - Closed without the address (the tab dismissed, Back, or the app brought forward from elsewhere while the call is
 *   under way and no address came): throw `AuthenticationError.OauthCancelled`, as Swift maps `.canceledLogin`, which
 *   the sign-in screen shows as no error.
 * - No browser to open it in (`ActivityNotFoundException`): throw `AuthenticationError.InvalidResponse`, as Swift
 *   does when the session cannot start.
 * - One call at a time: an address arriving with no call under way, or a second one, is dropped.
 * - A call whose coroutine is cancelled ends in a CancellationException; the tab may stay open, as the iPhone's
 *   session outlives its task.
 */
fun interface WebAuthenticating {
    suspend fun authenticate(url: String, callbackScheme: String): String
}

object AppleSignInNonce {
    /** The SHA-256 of the nonce, in lowercase hexadecimal: what Apple puts in the identity token. */
    fun digest(nonce: String): String =
        MessageDigest.getInstance("SHA-256").digest(nonce.wellFormed().toByteArray(Charsets.UTF_8))
            .joinToString("") { (it.toInt() and 0xFF).toString(16).padStart(2, '0') }
}

class AppleSignInCredential(
    val identityToken: String,
    /** Single use and valid for five minutes: the server trades it for tokens it can revoke. */
    val authorizationCode: String?,
    /** The Apple user ID, the `sub` of the identity token. */
    val user: String,
) {
    /** The token and the code are credentials: they never go in a log. */
    override fun toString(): String =
        "AppleSignInCredential(identityToken=<redacted>, authorizationCode=${authorizationCode?.let { "<redacted>" }}, user=$user)"
}

/**
 * Sign in with Apple, which ASAuthorizationController asks on the iPhone for the email scope alone (Friends uses a
 * chosen nickname) with `nonce`, the SHA-256 digest `AppleSignInNonce.digest` makes of the nonce SessionStore sends
 * the auth service. It gives the identity token, the authorization code and the Apple user ID; throws
 * `AuthenticationError.OauthCancelled` when the person cancels, `AuthenticationError.InvalidResponse` for an answer
 * without an identity token or while another request is under way, and Apple's own error otherwise.
 *
 * Android has no Apple sign-in of its own: the app implements it with Apple's web sign-in in a Custom Tab, at
 * `https://appleid.apple.com/auth/authorize` with `response_type=code id_token`, `scope=email`,
 * `response_mode=form_post`, `nonce` as given, a `state` of its own to check, the Services ID as `client_id` and an
 * https `redirect_uri` registered with it. The answer is the `code` and `id_token` the browser posts to that address;
 * the user ID is the `sub` of the identity token's payload. What the server needs for it, none of which it has yet:
 * - The Services ID with its return address in Apple's developer account, and a page at that address that takes
 *   Apple's form post and hands `code`, `id_token` and `state` back to the app through a verified https App Link
 *   (`autoVerify`), never the app's custom scheme, which any app may claim: the identity token carries the person's
 *   email and Apple user ID.
 * - The Services ID among the Apple provider's client IDs in Supabase Auth beside the bundle ID, so that the
 *   `id_token` grant SessionStore makes takes a token whose audience is the Services ID.
 * - For account deletion, a code from the web sign-in redeemed as the Services ID with the same `redirect_uri`:
 *   src/server/appleSignIn.ts redeems every code as the bundle ID (`APPLE_SIGN_IN_CLIENT_ID`), which Apple refuses for
 *   it.
 */
fun interface AppleSignInAuthorizing {
    suspend fun credential(nonce: String): AppleSignInCredential
}

/**
 * The account's session, and whether the app opens on the welcome, the demo or an account. The UI calls it from the
 * main thread, as the iPhone's store is `@MainActor`; `stateFlow` publishes `state` as it changes. `scope` is the
 * app's own, on the main thread, which nothing cancels while the app runs (`ProcessLifecycleOwner`'s
 * `lifecycleScope`, on `Dispatchers.Main.immediate`, so that a call already on the main thread goes on at once). A
 * refresh of the session is shared by every request that needs one and runs there, as the iPhone's `Task` does on the
 * main actor.
 *
 * The API client may run on any thread (a push token's renewal, a background job), where Swift's compiler holds every
 * caller to the main actor: what it asks of the store (`accessToken`, `forceSignOut`) moves to the dispatcher of
 * `scope` first, so the store's state only ever changes on that one thread. `state` and `generation` read from any.
 *
 * The app makes one, as the iPhone's app makes its `SessionStore`, and gives it to every client: the session is
 * `persistence` under the service "com.plhery.PeekDeliveryTracker.auth"; `defaults` the standard UserDefaults stand-in,
 * which keeps how the app last opened (`sdt.native.experience.v1`) and the language each account's sign-in emails
 * are written in (`sdt.native.email-language.v1.<user ID>`); `analytics` counts the catalog's actions
 * (`DeliveryAnalytics.shared.action`).
 */
class SessionStore(
    val configuration: AppConfiguration,
    private val defaults: UserDefaults,
    persistence: SessionPersistence,
    private val transport: HttpTransport,
    private val appleSignIn: AppleSignInAuthorizing,
    private val webAuthentication: WebAuthenticating,
    private val scope: CoroutineScope,
    private val analytics: (event: String, outcome: DeliveryAnalytics.Outcome?) -> Unit = { _, _ -> },
    private val now: () -> Instant = Instant::now,
    /** `SecRandomCopyBytes`: that many random bytes, for the PKCE verifier and the Apple nonce. */
    private val random: (count: Int) -> ByteArray = ::secureRandomBytes,
) : DeliveryApiClient.Session {
    private val keychain = persistence

    /** The one thread the store's state changes on: the dispatcher of `scope`. */
    private val storeContext: CoroutineContext =
        requireNotNull(scope.coroutineContext[ContinuationInterceptor]) { "SessionStore's scope needs a dispatcher." }

    private val stateValue = MutableStateFlow<AuthenticationState>(AuthenticationState.Loading)

    /** `$state`. */
    val stateFlow: StateFlow<AuthenticationState> = stateValue.asStateFlow()

    var state: AuthenticationState
        get() = stateValue.value
        private set(value) {
            val oldValue = stateValue.value
            stateValue.value = value
            if (oldValue.identity != value.identity) {
                // A refresh under way is the previous account's, and ends as cancelled: the new one's starts its
                // own. Swift leaves it to be joined (see refreshSession).
                refreshTask = null
                generation = UUID.randomUUID()
                identityChanges.send()
            }
        }

    @Volatile
    override var generation: UUID = UUID.randomUUID()
        private set

    /** Sent once the state, and the generation, are the new identity's. */
    val identityChanges = PassthroughSignal()

    /**
     * True only while `identityChanges` announces the account saved on this device being reopened at launch, as
     * opposed to a sign-in or an account switch.
     */
    var reopeningSavedAccount = false
        private set

    private val experienceKey = "sdt.native.experience.v1"
    private val emailLanguageKey = "sdt.native.email-language.v1"
    private var session: AuthSession? = null

    /** What the refresh under way failed with, nil when it succeeded. */
    private var refreshTask: Deferred<Exception?>? = null

    val user: AuthUser?
        get() = (state as? AuthenticationState.SignedIn)?.user

    val isAuthenticated: Boolean
        get() = state is AuthenticationState.SignedIn || state == AuthenticationState.Demo

    val isDemo: Boolean
        get() = state == AuthenticationState.Demo

    /** Nobody is signed in, and the app shows the parcels this phone follows without an account. */
    val isGuest: Boolean
        get() = state == AuthenticationState.Welcome

    override val signedIn: Boolean
        get() = user != null

    suspend fun bootstrap() {
        val stored = if (configuration.authenticationConfigured) loadSession() else null
        if (stored != null) {
            if (stored.user.isAnonymous == true) {
                clearLocalSession()
                return
            }
            session = stored
            rememberExperience("account")
            reopeningSavedAccount = true
            try {
                state = AuthenticationState.SignedIn(stored.user)
            } finally {
                reopeningSavedAccount = false
            }
            if (stored.secondsUntilExpiration(now()) < 60) {
                // A network outage does not invalidate the saved account or its offline parcels.
                // refreshSession clears credentials only for a definitive refresh rejection.
                tryQuietly { refreshSession() }
            }
            return
        }

        state = when (quietly { defaults.string(forKey = experienceKey) }) {
            "demo" -> AuthenticationState.Demo
            "account" -> if (configuration.authenticationConfigured) AuthenticationState.SignedOut else AuthenticationState.Unconfigured
            else -> AuthenticationState.Welcome
        }
    }

    fun enterDemo() {
        analytics("demo-start", null)
        rememberExperience("demo")
        state = AuthenticationState.Demo
    }

    fun showSignIn() {
        rememberExperience("account")
        state = if (configuration.authenticationConfigured) AuthenticationState.SignedOut else AuthenticationState.Unconfigured
    }

    fun showWelcome() {
        if (isDemo) analytics("demo-exit", null)
        quietly { defaults.removeObject(forKey = experienceKey) }
        state = AuthenticationState.Welcome
    }

    suspend fun sendCode(to: String, language: AppLanguage) {
        var analyticsSucceeded = false
        analytics("sign-in-code-send", DeliveryAnalytics.Outcome.Started)
        try {
            val body = buildJsonObject {
                put("email", to)
                put("create_user", true)
                putJsonObject("data") { put("locale", language.rawValue) }
            }
            authRequest(path = "otp", method = "POST", jsonObject = body, response = EmptyAuthResponse.serializer())
            analyticsSucceeded = true
        } finally {
            analytics("sign-in-code-send", if (analyticsSucceeded) DeliveryAnalytics.Outcome.Success else DeliveryAnalytics.Outcome.Error)
        }
    }

    suspend fun verifyCode(email: String, code: String) {
        var analyticsSucceeded = false
        analytics("sign-in-code-verify", DeliveryAnalytics.Outcome.Started)
        try {
            val generation = generation
            val result = authRequest(
                path = "verify",
                method = "POST",
                jsonObject = buildJsonObject {
                    put("email", email)
                    put("token", code)
                    put("type", "email")
                },
                response = AuthSession.serializer(),
            )
            checkGeneration(generation)
            accept(result)
            analytics("sign-in-complete", DeliveryAnalytics.Outcome.Success)
            analyticsSucceeded = true
        } finally {
            analytics("sign-in-code-verify", if (analyticsSucceeded) DeliveryAnalytics.Outcome.Success else DeliveryAnalytics.Outcome.Error)
        }
    }

    suspend fun signInWithGoogle() {
        var analyticsSucceeded = false
        analytics("sign-in-google", DeliveryAnalytics.Outcome.Started)
        try {
            signInWithOAuth(provider = "google")
            analyticsSucceeded = true
        } finally {
            analytics("sign-in-google", if (analyticsSucceeded) DeliveryAnalytics.Outcome.Success else DeliveryAnalytics.Outcome.Error)
        }
    }

    suspend fun signInWithOidc() {
        if (!configuration.authenticationConfigured || !configuration.oidcAuthEnabled) throw AuthenticationError.NotConfigured()
        var analyticsSucceeded = false
        analytics("sign-in-oidc", DeliveryAnalytics.Outcome.Started)
        try {
            signInWithOAuth(provider = configuration.oidcProvider)
            analyticsSucceeded = true
        } finally {
            analytics("sign-in-oidc", if (analyticsSucceeded) DeliveryAnalytics.Outcome.Success else DeliveryAnalytics.Outcome.Error)
        }
    }

    private suspend fun signInWithOAuth(provider: String) {
        val generation = generation
        val base = configuration.supabaseUrl ?: throw AuthenticationError.NotConfigured()
        val verifier = randomVerifier()
        val challenge = codeChallenge(verifier)
        val authorizationUrl = OAuthFlow.authorizationUrl(baseUrl = base, provider = provider, codeChallenge = challenge)
            ?: throw AuthenticationError.InvalidResponse()
        val callback = webAuthentication.authenticate(authorizationUrl, OAuthFlow.callbackScheme)
        val code = OAuthFlow.authorizationCode(from = callback) ?: throw AuthenticationError.InvalidResponse()
        val result = authRequest(
            path = "token?grant_type=pkce",
            method = "POST",
            jsonObject = buildJsonObject {
                put("auth_code", code)
                put("code_verifier", verifier)
            },
            response = AuthSession.serializer(),
        )
        checkGeneration(generation)
        accept(result)
        analytics("sign-in-complete", DeliveryAnalytics.Outcome.Success)
    }

    suspend fun signInWithApple() {
        if (!configuration.authenticationConfigured || !configuration.appleAuthEnabled) throw AuthenticationError.NotConfigured()
        val generation = generation
        val nonce = randomVerifier()
        analytics("sign-in-apple", DeliveryAnalytics.Outcome.Started)
        try {
            val credential = appleSignIn.credential(nonce = AppleSignInNonce.digest(nonce))
            checkGeneration(generation)
            val result = appleSession(credential, nonce = nonce)
            checkGeneration(generation)
            accept(result)
            analytics("sign-in-apple", DeliveryAnalytics.Outcome.Success)
            analytics("sign-in-complete", DeliveryAnalytics.Outcome.Success)
        } catch (cancelled: AuthenticationError.OauthCancelled) {
            throw cancelled
        } catch (error: Exception) {
            analytics("sign-in-apple", DeliveryAnalytics.Outcome.Error)
            throw error
        }
    }

    /**
     * Before an account that signs in with Apple is deleted, Apple asks for its tokens to be revoked. Signing in with
     * Apple again gives the server a code to revoke them with, and counts as the recent sign-in the deletion needs.
     * Returns nil for an account without Apple.
     */
    suspend fun reauthenticateWithAppleBeforeDeletion(): String? {
        val current = user
        if (configuration.mode != AppConfiguration.Mode.Api || !configuration.appleAuthEnabled || current == null) return null
        if (current.identities == null) accessToken(forceRefresh = true)
        val subjects = user?.appleSubjects
        if (subjects == null || subjects.isEmpty() || user?.id != current.id) return null
        val generation = generation
        val nonce = randomVerifier()
        val credential = appleSignIn.credential(nonce = AppleSignInNonce.digest(nonce))
        checkGeneration(generation)
        // Signing in with another Apple Account would open, or create, a different Peek account.
        if (credential.user !in subjects) throw AuthenticationError.OtherAppleAccount()
        val result = appleSession(credential, nonce = nonce)
        checkGeneration(generation)
        if (result.user.id != current.id) throw AuthenticationError.OtherAppleAccount()
        accept(result)
        val code = credential.authorizationCode
        if (code == null || code.isEmpty()) throw AuthenticationError.InvalidResponse()
        return code
    }

    private suspend fun appleSession(credential: AppleSignInCredential, nonce: String): AuthSession {
        if (credential.identityToken.isEmpty()) throw AuthenticationError.InvalidResponse()
        return authRequest(
            path = "token?grant_type=id_token",
            method = "POST",
            jsonObject = buildJsonObject {
                put("provider", "apple")
                put("id_token", credential.identityToken)
                put("nonce", nonce)
            },
            response = AuthSession.serializer(),
        )
    }

    /** The sign-in email template reads the account language from user metadata. */
    suspend fun saveEmailLanguage(language: AppLanguage) {
        val userId = user?.id
        if (configuration.mode != AppConfiguration.Mode.Api || userId == null) return
        val key = "$emailLanguageKey.${userId.uuidString}"
        if (quietly { defaults.string(forKey = key) } == language.rawValue) return
        // A later launch or language change retries.
        tryQuietly {
            val token = accessToken()
            if (token == null || user?.id != userId) return
            authRequest(
                path = "user",
                method = "PUT",
                jsonObject = buildJsonObject { putJsonObject("data") { put("locale", language.rawValue) } },
                response = EmptyAuthResponse.serializer(),
                bearer = token,
            )
            quietly { defaults.set(language.rawValue, forKey = key) }
        }
    }

    /** On the store's thread, whichever thread asks. */
    override suspend fun accessToken(forceRefresh: Boolean): String? = withContext(storeContext) {
        if (configuration.mode != AppConfiguration.Mode.Api) return@withContext null
        val session = session
        if (user == null || session == null) return@withContext null
        val generation = generation
        if (forceRefresh || session.secondsUntilExpiration(now()) < 60) {
            refreshSession()
        }
        checkGeneration(generation)
        this@SessionStore.session?.accessToken
    }

    suspend fun signOut() {
        analytics("sign-out", null)
        val token = session?.accessToken
        clearLocalSession()
        showWelcome()
        if (token != null) {
            tryQuietly {
                authRequest(
                    path = "logout?scope=local",
                    method = "POST",
                    jsonObject = JsonObject(emptyMap()),
                    response = EmptyAuthResponse.serializer(),
                    bearer = token,
                )
            }
        }
    }

    /**
     * On the store's thread, whichever thread asks, and done before it returns, as Swift's synchronous call is: even
     * for a caller cancelled meanwhile.
     */
    override suspend fun forceSignOut() {
        withContext(storeContext + NonCancellable) { clearLocalSession() }
    }

    private suspend fun refreshSession() {
        refreshTask?.let { task ->
            task.await()?.let { throw it }
            return
        }
        val refreshToken = session?.refreshToken ?: throw AuthenticationError.MissingSession()
        val generation = generation
        val task = scope.async {
            try {
                val refreshed = authRequest(
                    path = "token?grant_type=refresh_token",
                    method = "POST",
                    jsonObject = buildJsonObject { put("refresh_token", refreshToken) },
                    response = AuthSession.serializer(),
                )
                checkGeneration(generation)
                if (refreshed.user.id != session?.user?.id) throw AuthenticationError.MissingSession()
                accept(refreshed)
                null
            } catch (cancellation: CancellationException) {
                throw cancellation
            } catch (error: Exception) {
                error
            }
        }
        refreshTask = task
        try {
            // Swift's `task.value` waits for the refresh even once the caller is cancelled, so what it found is
            // always seen to here.
            withContext(NonCancellable) { task.await() }?.let { throw it }
        } catch (error: Exception) {
            if (this.generation == generation && error is AuthenticationError.MissingSession) {
                clearLocalSession()
            }
            throw error
        } finally {
            // Swift clears it only while the account is unchanged (Authentication.swift:398–399): once the account
            // changes under a refresh, every later one joins that finished task and ends as cancelled, sending
            // nothing, until a sign-out. A deliberate difference: this clears it while it is still this refresh's.
            if (refreshTask === task) refreshTask = null
        }
    }

    private fun accept(next: AuthSession) {
        if (next.user.isAnonymous == true) throw AuthenticationError.MissingSession()
        // Swift traps on an expiry past a 64-bit number; an answer that far out is no session.
        val expiresAt = next.expiresAt ?: try {
            Math.addExact((now().timeIntervalSinceReferenceDate + SECONDS_FROM_1970_TO_2001).toLong(), next.expiresIn)
        } catch (_: ArithmeticException) {
            throw AuthenticationError.InvalidResponse()
        }
        val saved = next.copy(expiresAt = expiresAt)
        try {
            keychain.save(JsonEncoder.deliveryTracker.encode(AuthSession.serializer(), saved))
        } catch (error: Exception) {
            throw error as? AuthenticationError ?: AuthenticationError.SessionSaveFailed()
        }
        session = saved
        rememberExperience("account")
        state = AuthenticationState.SignedIn(next.user)
    }

    private fun clearLocalSession() {
        refreshTask?.cancel()
        refreshTask = null
        session = null
        quietly { keychain.delete() }
        state = if (configuration.authenticationConfigured) AuthenticationState.SignedOut else AuthenticationState.Unconfigured
    }

    private fun rememberExperience(value: String) {
        quietly { defaults.set(value, forKey = experienceKey) }
    }

    /** As KeychainStore's `load`: a session that cannot be read or decoded reads as none. */
    private fun loadSession(): AuthSession? {
        val data = quietly { keychain.load() } ?: return null
        return try {
            decodeSwiftKeys(AuthSession.serializer(), data)
        } catch (_: Exception) {
            null
        }
    }

    private suspend fun <T> authRequest(
        path: String,
        method: String,
        jsonObject: JsonObject,
        response: DeserializationStrategy<T>,
        bearer: String? = null,
    ): T {
        val base = configuration.supabaseUrl ?: throw AuthenticationError.NotConfigured()
        val url = base.resolving("auth/v1/$path") ?: throw AuthenticationError.InvalidResponse()
        val headers = linkedMapOf("Content-Type" to "application/json", "apikey" to configuration.supabasePublishableKey)
        if (bearer != null) headers["Authorization"] = "Bearer $bearer"
        // JSONSerialization's bytes, the members in the order given where Swift's follow its dictionary's hash order.
        val request = HttpRequest(url, method, headers, JsonEncoder().encode(JsonObject.serializer(), jsonObject), timeout = 30.0)
        val http = transport.data(request)
        if (http.statusCode !in 200 until 300) {
            val payload = try {
                decodeSwiftKeys(AuthErrorPayload.serializer(), http.body)
            } catch (_: Exception) {
                null
            }
            // The 2024-01-01 shape's code, else the legacy shape's (see AuthErrorPayload), else an OAuth error's.
            val code = payload?.code?.text ?: payload?.errorCode ?: payload?.error
            if (path == "token?grant_type=refresh_token" && invalidRefresh(http.statusCode, code)) {
                throw AuthenticationError.MissingSession()
            }
            (payload?.errorDescription ?: payload?.message ?: payload?.msg)?.let { throw AuthenticationError.Server(it) }
            throw AuthenticationError.RequestFailed(http.statusCode)
        }
        if (response === EmptyAuthResponse.serializer() && http.body.isEmpty()) {
            @Suppress("UNCHECKED_CAST")
            return EmptyAuthResponse() as T
        }
        return decodeSwiftKeys(response, http.body)
    }

    private fun randomVerifier(): String {
        val bytes = try {
            random(48)
        } catch (_: Exception) {
            throw AuthenticationError.SecureRequestFailed()
        }
        return verifier(bytes)
    }

    internal companion object {
        private fun invalidRefresh(status: Int, code: String?): Boolean {
            if (status == 401 || status == 403) return true
            return status == 400 && (code ?: "") in listOf(
                "invalid_grant", "refresh_token_not_found", "refresh_token_already_used",
                "session_not_found", "session_expired", "user_not_found", "user_banned",
            )
        }

        /** The PKCE verifier, or the Apple nonce, that random bytes make. */
        fun verifier(bytes: ByteArray): String = bytes.base64UrlEncodedString()

        fun codeChallenge(verifier: String): String =
            MessageDigest.getInstance("SHA-256").digest(verifier.toByteArray(Charsets.UTF_8)).base64UrlEncodedString()

        private fun ByteArray.base64UrlEncodedString(): String = Base64.getUrlEncoder().withoutPadding().encodeToString(this)
    }
}

private fun secureRandomBytes(count: Int): ByteArray = ByteArray(count).also { SecureRandom().nextBytes(it) }

/**
 * Swift's `try?` (or an empty `catch`) on the caller's path. The caller's own cancellation goes on, as every catch on
 * its path lets it; any other CancellationException is the account changing under the call (`checkGeneration`, here
 * or in a refresh it shares), which ends here as the Swift ends it.
 */
private suspend inline fun tryQuietly(block: () -> Unit) {
    try {
        block()
    } catch (_: CancellationException) {
        currentCoroutineContext().ensureActive()
    } catch (_: Exception) {
    }
}

/** Swift's `try?` on a platform store, which never stops the caller. */
private inline fun <T> quietly(block: () -> T): T? = try {
    block()
} catch (_: Exception) {
    null
}

/** Read from any JSON document, as Swift's synthesized `init(from:)` of a type without properties never looks inside. */
@Serializable(with = EmptyAuthResponse.Serializer::class)
private class EmptyAuthResponse {
    object Serializer : KSerializer<EmptyAuthResponse> {
        // JsonElement's kind: decodeSwiftKeys hands the document over whole, as it would leave out a class's unknown keys.
        override val descriptor: SerialDescriptor = SerialDescriptor("EmptyAuthResponse", JsonElement.serializer().descriptor)

        override fun deserialize(decoder: Decoder): EmptyAuthResponse {
            if (!(decoder as JsonDecoder).decodeJsonElement().isJson) throw SerializationException("The data isn't JSON.")
            return EmptyAuthResponse()
        }

        // The tree takes any bare word. Swift's scanner takes a number's characters without checking them, as nothing
        // reads it, and nothing else unquoted but true, false and null.
        private val number = Regex("[-0-9][0-9.eE+-]*")

        private val JsonElement.isJson: Boolean
            get() = when (this) {
                is JsonObject -> values.all { it.isJson }
                is JsonArray -> all { it.isJson }
                is JsonPrimitive -> isString || this is JsonNull || content == "true" || content == "false" || number.matches(content)
            }

        override fun serialize(encoder: Encoder, value: EmptyAuthResponse) {
            encoder.encodeSerializableValue(JsonObject.serializer(), JsonObject(emptyMap()))
        }
    }
}

/**
 * An error as the auth service answers it. Neither app sends `X-Supabase-Api-Version`, so it answers in its legacy
 * shape, `{"code":400,"error_code":"…","msg":"…"}`, where `code` is the HTTP status; the 2024-01-01 shape is
 * `{"code":"…","message":"…"}`. Swift's payload takes `code` as a string only (Authentication.swift:591), so a legacy
 * error reads as nothing there (:454): a refused refresh is `requestFailed(400)` and keeps a session that can never
 * refresh again, and a wrong code or a rate limit loses the server's message. A deliberate difference from that iOS
 * bug: both shapes are read here.
 */
@Serializable
private class AuthErrorPayload(
    val code: AuthErrorCode? = null,
    val errorCode: String? = null,
    val error: String? = null,
    val message: String? = null,
    val msg: String? = null,
    val errorDescription: String? = null,
)

/**
 * `code`: the error code, a string, in the 2024-01-01 shape, and the HTTP status, a number, in the legacy one, which
 * reads as no code. Any other value leaves the whole error unreadable, as it is to Swift.
 */
@Serializable(with = AuthErrorCode.Serializer::class)
private class AuthErrorCode(val text: String?) {
    object Serializer : KSerializer<AuthErrorCode> {
        // JsonElement's kind: decodeSwiftKeys hands the value over as it is.
        override val descriptor: SerialDescriptor = SerialDescriptor("AuthErrorCode", JsonElement.serializer().descriptor)

        private val number = Regex("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][+-]?[0-9]+)?")

        override fun deserialize(decoder: Decoder): AuthErrorCode {
            val value = (decoder as JsonDecoder).decodeJsonElement()
            return when {
                value is JsonNull -> AuthErrorCode(null)
                value is JsonPrimitive && value.isString -> AuthErrorCode(value.content)
                value is JsonPrimitive && number.matches(value.content) -> AuthErrorCode(null)
                else -> throw SerializationException("Expected a string or a number.")
            }
        }

        override fun serialize(encoder: Encoder, value: AuthErrorCode) {
            encoder.encodeSerializableValue(JsonElement.serializer(), value.text?.let(::JsonPrimitive) ?: JsonNull)
        }
    }
}
