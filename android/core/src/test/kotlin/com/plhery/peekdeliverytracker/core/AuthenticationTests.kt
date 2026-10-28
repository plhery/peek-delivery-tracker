package com.plhery.peekdeliverytracker.core

import java.io.IOException
import java.time.Instant
import java.util.Collections
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/**
 * Authentication.swift has no tests of its own. These hold what it does with the auth service's requests and answers,
 * its addresses, digests, saved sessions and expiry: each expected value is what Authentication.swift gives for the
 * same input, built with swiftc on macOS for this port. The one exception is a deliberate difference, said where it is
 * tested: what the API client asks from another thread.
 */
class AuthenticationTests {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://auth.example.test", supabaseUrl = "https://auth.example.test", supabasePublishableKey = "pk",
        googleAuthEnabled = true, appleAuthEnabled = true, emailOtpEnabled = true, appGroupIdentifier = "auth.test",
    )
    private val now = Instant.ofEpochSecond(1_900_000_000)
    private val userId = "0B5E6F1A-1111-4222-8333-944455556666"
    private val otherUserId = "0B5E6F1A-1111-4222-8333-944455556667"

    /** The auth service's session, snake_case. */
    private val sessionAnswer = """{"access_token":"tok-a","token_type":"bearer","expires_in":3600,"expires_at":1900003600,"refresh_token":"ref-a",""" +
        """"user":{"id":"0b5e6f1a-1111-4222-8333-944455556666","email":"person@example.test","is_anonymous":false,""" +
        """"identities":[{"provider":"apple","identity_data":{"sub":"apple.sub.1"}}]}}"""

    /** The same session as the Keychain keeps it. */
    private val savedSession = """{"accessToken":"tok-a","expiresAt":1900003600,"expiresIn":3600,"refreshToken":"ref-a","tokenType":"bearer",""" +
        """"user":{"email":"person@example.test","id":"$userId","identities":[{"identityData":{"sub":"apple.sub.1"},"provider":"apple"}],"isAnonymous":false}}"""

    /** Another session the auth service answers, `ref-<access token>` its refresh token. */
    private fun answer(user: String, accessToken: String, expiresAt: Long = 1_900_003_600) =
        """{"access_token":"$accessToken","token_type":"bearer","expires_in":3600,"expires_at":$expiresAt,""" +
            """"refresh_token":"ref-$accessToken","user":{"id":"$user"}}"""

    private val bytes0To47 = ByteArray(48) { it.toByte() }
    private val verifier0To47 = "AAECAwQFBgcICQoLDA0ODxAREhMUFRYXGBkaGxwdHh8gISIjJCUmJygpKissLS4v"

    private fun json(text: String): JsonElement = Json.parseToJsonElement(text)

    private fun HttpRequest.jsonBody(): JsonElement = json(body!!.decodeToString())

    private fun describe(error: Throwable): String = when (error) {
        is AuthenticationError.MissingSession -> "missingSession"
        is AuthenticationError.Server -> "server(${error.serverMessage})"
        is AuthenticationError.RequestFailed -> "requestFailed(${error.status})"
        is DecodingError -> "decoding"
        else -> error.toString()
    }

    private fun TestScope.store(
        transport: HttpTransport,
        persistence: SessionPersistence = MemorySessionPersistence(),
        defaults: UserDefaults = MemoryDefaults(),
        apple: AppleSignInAuthorizing = StubAppleSignIn(),
        web: WebAuthenticating = WebAuthenticating { url, _ -> throw AssertionError("Unexpected browser sign-in at $url") },
        analytics: (String, DeliveryAnalytics.Outcome?) -> Unit = { _, _ -> },
        clock: () -> Instant = { now },
    ) = testSessionStore(
        configuration, backgroundScope, persistence, transport, apple, defaults, web, analytics, now = clock, random = { bytes0To47.copyOf(it) },
    )

    private fun saved(text: String) = MemorySessionPersistence().apply { data = text.toByteArray() }

    /** The refresh token each refresh sent. */
    private fun RecordingTransport.refreshTokens(): List<String> =
        requests.filter { it.url.endsWith("grant_type=refresh_token") }.map { it.jsonBody().jsonObject["refresh_token"]!!.jsonPrimitive.content }

    @Test
    fun everyAuthCallSendsWhatTheSwiftSends() = runTest {
        val transport = RecordingTransport { request ->
            when (request.path) {
                "/auth/v1/otp" -> jsonResponse(200, "{}")
                "/auth/v1/user" -> jsonResponse(200, """{"id":"x"}""")
                "/auth/v1/logout" -> jsonResponse(204)
                else -> jsonResponse(200, sessionAnswer)
            }
        }
        val persistence = MemorySessionPersistence()
        val defaults = MemoryDefaults()
        val apple = StubAppleSignIn()
        apple.result = Result.success(AppleSignInCredential(identityToken = "id.token.synthetic", authorizationCode = "auth-code-1", user = "apple.sub.1"))
        val events = mutableListOf<String>()
        val session = store(transport, persistence, defaults, apple, analytics = { event, outcome -> events += listOfNotNull(event, outcome?.name).joinToString(" ") })

        session.bootstrap()
        assertEquals(AuthenticationState.Welcome, session.state)
        session.sendCode(to = "person@example.test", language = AppLanguage.De)
        session.verifyCode(email = "person@example.test", code = "123456")
        assertEquals(savedSession, persistence.data?.decodeToString())
        assertEquals(mapOf("sdt.native.experience.v1" to "account"), defaults.values)
        session.saveEmailLanguage(AppLanguage.Fr)
        assertEquals("fr", defaults.values["sdt.native.email-language.v1.$userId"])
        session.signInWithApple()
        assertEquals(listOf("90b7f18934aba31fe47bbc8fca3bd060167ded5c6dd8a7f053d75eca83ce35c2"), apple.nonces)
        assertEquals("tok-a", session.accessToken(forceRefresh = true))
        session.signOut()
        assertEquals(AuthenticationState.Welcome, session.state)
        assertNull(persistence.data)
        assertEquals(mapOf("sdt.native.email-language.v1.$userId" to "fr"), defaults.values)

        val requests = transport.requests
        assertEquals(
            listOf(
                "POST https://auth.example.test/auth/v1/otp",
                "POST https://auth.example.test/auth/v1/verify",
                "PUT https://auth.example.test/auth/v1/user",
                "POST https://auth.example.test/auth/v1/token?grant_type=id_token",
                "POST https://auth.example.test/auth/v1/token?grant_type=refresh_token",
                "POST https://auth.example.test/auth/v1/logout?scope=local",
            ),
            requests.map { "${it.method} ${it.url}" },
        )
        // JSONSerialization orders the members as its dictionary hashes them: the members are what count.
        assertEquals(
            listOf(
                json("""{"create_user":true,"email":"person@example.test","data":{"locale":"de"}}"""),
                json("""{"type":"email","email":"person@example.test","token":"123456"}"""),
                json("""{"data":{"locale":"fr"}}"""),
                json("""{"nonce":"$verifier0To47","provider":"apple","id_token":"id.token.synthetic"}"""),
                json("""{"refresh_token":"ref-a"}"""),
                json("{}"),
            ),
            requests.map { it.jsonBody() },
        )
        assertEquals(listOf(null, null, "Bearer tok-a", null, null, "Bearer tok-a"), requests.map { it.header("Authorization") })
        for (request in requests) {
            assertEquals("pk", request.header("apikey"))
            assertEquals("application/json", request.header("Content-Type"))
            assertEquals("2024-01-01", request.header("X-Supabase-Api-Version"))
            // Set in Swift's order: the version after the key, and before the bearer.
            assertEquals(
                listOf("Content-Type", "apikey", "X-Supabase-Api-Version") + listOfNotNull(request.header("Authorization")?.let { "Authorization" }),
                request.headers.keys.toList(),
            )
            assertEquals(30.0, request.timeout, 0.0)
            assertTrue(request.handlesCookies)
        }
        assertEquals(
            listOf(
                "sign-in-code-send Started", "sign-in-code-send Success",
                "sign-in-code-verify Started", "sign-in-complete Success", "sign-in-code-verify Success",
                "sign-in-apple Started", "sign-in-apple Success", "sign-in-complete Success",
                "sign-out",
            ),
            events,
        )
    }

    @Test
    fun googleSignInOpensTheBrowserWithPkceAndTradesTheCode() = runTest {
        val opened = mutableListOf<String>()
        val web = WebAuthenticating { url, scheme ->
            opened += "$url $scheme"
            "peekdeliverytracker://auth-callback?code=c1"
        }
        val transport = RecordingTransport { jsonResponse(200, sessionAnswer) }
        val session = store(transport, web = web)
        session.signInWithGoogle()
        assertEquals(
            listOf(
                "https://auth.example.test/auth/v1/authorize?provider=google&redirect_to=peekdeliverytracker://auth-callback" +
                    "&code_challenge=kLfxiTSrox_ke7yPyjvQYBZ97Vxt2KfwU9deyoPONcI&code_challenge_method=s256 peekdeliverytracker",
            ),
            opened,
        )
        val request = transport.requests.single()
        assertEquals("https://auth.example.test/auth/v1/token?grant_type=pkce", request.url)
        assertEquals(json("""{"auth_code":"c1","code_verifier":"$verifier0To47"}"""), request.jsonBody())
        assertEquals(UUID.fromString(userId), session.user?.id)
    }

    @Test
    fun aBrowserSignInClosedWithoutAnAddressIsCancelledAndSendsNothing() = runTest {
        val session = store(RecordingTransport { throw AssertionError("Unexpected request") }, web = { _, _ -> throw AuthenticationError.OauthCancelled() })
        try {
            session.signInWithGoogle()
            fail("Expected the cancellation")
        } catch (_: AuthenticationError.OauthCancelled) {
        }
        assertNull(session.user)
    }

    @Test
    fun authAddressesResolveAgainstTheSupabaseAddressAsFoundationDoes() {
        val expected = mapOf(
            "https://auth.example.test" to "https://auth.example.test/auth/v1/otp",
            "https://auth.example.test/" to "https://auth.example.test/auth/v1/otp",
            // A base without a final slash loses its last segment, where the browser sign-in's address keeps it.
            "https://auth.example.test/base" to "https://auth.example.test/auth/v1/otp",
            "https://auth.example.test/base/" to "https://auth.example.test/base/auth/v1/otp",
            "https://auth.example.test/a/./b/../c" to "https://auth.example.test/a/auth/v1/otp",
            "https://auth.example.test?x=1" to "https://auth.example.test/auth/v1/otp",
            "https://auth.example.test/p?x=1#f" to "https://auth.example.test/auth/v1/otp",
            "http://127.0.0.1:54321" to "http://127.0.0.1:54321/auth/v1/otp",
            "mailto:a/../b" to "mailto:auth/v1/otp",
            "x:.." to "x:auth/v1/otp",
            "relative" to "auth/v1/otp",
        )
        for ((base, url) in expected) assertEquals(base, url, base.resolving("auth/v1/otp"))
        assertEquals(
            "https://auth.example.test/base/auth/v1/token?grant_type=refresh_token",
            "https://auth.example.test/base/".resolving("auth/v1/token?grant_type=refresh_token"),
        )
    }

    @Test
    fun oauthAddressesAndCallbacksReadAsTheSwiftReadsThem() {
        assertEquals(
            "https://auth.example.test/base/auth/v1/authorize?provider=custom:corp&redirect_to=peekdeliverytracker://auth-callback" +
                "&code_challenge=a+b/c%3D&code_challenge_method=s256",
            OAuthFlow.authorizationUrl(baseUrl = "https://auth.example.test/base/", provider = "custom:corp", codeChallenge = "a+b/c="),
        )
        assertEquals(
            "https://auth.example.test/auth/v1/authorize?provider=google&redirect_to=peekdeliverytracker://auth-callback" +
                "&code_challenge=a%26b%3Dc&code_challenge_method=s256",
            OAuthFlow.authorizationUrl(baseUrl = "https://auth.example.test?x=1", provider = "google", codeChallenge = "a&b=c"),
        )
        for (provider in listOf("custom:a b", "custom:BAD", "")) {
            assertNull(provider, OAuthFlow.authorizationUrl(baseUrl = "https://auth.example.test", provider = provider, codeChallenge = "abc"))
        }
        val codes = mapOf(
            "peekdeliverytracker://auth-callback?code=a%20b" to "a b",
            "peekdeliverytracker://auth-callback?code=a+b" to "a+b",
            "PeekDeliveryTracker://AUTH-CALLBACK?code=x" to "x",
            "peekdeliverytracker://auth-callback/path?code=x" to "x",
            "peekdeliverytracker://auth-callback?a=1&code=x&code=y" to "x",
            "peekdeliverytracker://auth-callback?code=%zz" to "%zz",
            "peekdeliverytracker://auth-callback?Code=x" to null,
            "peekdeliverytracker://auth-callback#?code=x" to null,
            "peekdeliverytracker://auth-callback?error=x&error_description=y" to null,
        )
        for ((url, code) in codes) assertEquals(url, code, OAuthFlow.authorizationCode(from = url))
    }

    @Test
    fun pkceAndAppleNoncesAreTheSwiftDigests() {
        assertEquals(verifier0To47, SessionStore.verifier(bytes0To47))
        assertEquals("kLfxiTSrox_ke7yPyjvQYBZ97Vxt2KfwU9deyoPONcI", SessionStore.codeChallenge(verifier0To47))
        assertEquals("2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824", AppleSignInNonce.digest("hello"))
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", AppleSignInNonce.digest(""))
        assertEquals("850f7dc43910ff890f8879c0ed26fe697c93a067ad93a7d50f466a7028a9bf4e", AppleSignInNonce.digest("café"))
        assertEquals("90b7f18934aba31fe47bbc8fca3bd060167ded5c6dd8a7f053d75eca83ce35c2", AppleSignInNonce.digest(verifier0To47))
    }

    @Test
    fun sessionsAreReadFromEitherSpellingAndSavedAsTheKeychainKeepsThem() {
        fun saved(answer: String): String? = try {
            val session = decodeSwiftKeys(AuthSession.serializer(), answer.toByteArray())
            JsonEncoder.deliveryTracker.encode(AuthSession.serializer(), session).decodeToString()
        } catch (_: DecodingError) {
            null
        }
        assertEquals(savedSession, saved(sessionAnswer))
        assertEquals(savedSession, saved(savedSession))
        // A session saved before the absolute expiry was kept, with the slash JSONEncoder escapes.
        assertEquals(
            """{"accessToken":"tok\/b","expiresIn":60,"refreshToken":"ref-b","tokenType":"bearer","user":{"id":"$userId"}}""",
            saved("""{"accessToken":"tok/b","tokenType":"bearer","expiresIn":60,"refreshToken":"ref-b","user":{"id":"$userId"}}"""),
        )
        val identities = """{"access_token":"tok-c","token_type":"bearer","expires_in":3600,"refresh_token":"ref-c","unknown":[1,{"x":null}],""" +
            """"user":{"id":"$userId","email":null,"identities":[{"provider":"email"},{"provider":"apple","identity_data":null},""" +
            """{"provider":"apple","identity_data":{"sub":"apple.sub.2"}}]}}"""
        assertEquals(
            """{"accessToken":"tok-c","expiresIn":3600,"refreshToken":"ref-c","tokenType":"bearer","user":{"id":"$userId",""" +
                """"identities":[{"provider":"email"},{"provider":"apple"},{"identityData":{"sub":"apple.sub.2"},"provider":"apple"}]}}""",
            saved(identities),
        )
        assertEquals(listOf("apple.sub.2"), decodeSwiftKeys(AuthSession.serializer(), identities.toByteArray()).user.appleSubjects)
        assertNull(saved("""{"access_token":"tok-d","token_type":"bearer","expires_in":3600,"refresh_token":"ref-d","user":{"id":"not-a-uuid"}}"""))
        assertNull(saved("""{"access_token":"tok-e","token_type":"bearer","refresh_token":"ref-e","user":{"id":"$userId"}}"""))
    }

    @Test
    fun theTokensStayOutOfLogs() {
        val session = decodeSwiftKeys(AuthSession.serializer(), sessionAnswer.toByteArray())
        assertFalse(session.toString().contains("tok-a"))
        assertFalse(session.toString().contains("ref-a"))
        val credential = AppleSignInCredential(identityToken = "id.token.synthetic", authorizationCode = "auth-code-1", user = "apple.sub.1")
        assertFalse(credential.toString().contains("id.token.synthetic"))
        assertFalse(credential.toString().contains("auth-code-1"))
    }

    @Test
    fun theAccountsEmailAndAppleIdStayOutOfLogs() {
        val session = decodeSwiftKeys(AuthSession.serializer(), sessionAnswer.toByteArray())
        for (text in listOf(session.user.toString(), session.toString(), AuthenticationState.SignedIn(session.user).toString())) {
            assertFalse(text, text.contains("person@example.test"))
            assertFalse(text, text.contains("apple.sub.1"))
            assertTrue(text, text.contains(userId.lowercase()))
        }
        assertEquals(
            "AuthUser(id=${userId.lowercase()}, email=<redacted>, isAnonymous=false, " +
                "identities=[Identity(provider=apple, identityData=IdentityData(sub=<redacted>))])",
            session.user.toString(),
        )
        assertEquals("AuthUser(id=${userId.lowercase()}, email=null, isAnonymous=null, identities=null)", AuthUser(UUID.fromString(userId)).toString())
    }

    @Test
    fun expiryIsADatesArithmeticAndASessionRefreshesInItsLastMinute() {
        fun seconds(expiresAt: Long?, nanos: Long = 0) = AuthSession(
            accessToken = "tok", tokenType = "bearer", expiresIn = 3600, expiresAt = expiresAt, refreshToken = "ref",
            user = AuthUser(id = UUID.fromString(userId)),
        ).secondsUntilExpiration(Instant.ofEpochSecond(1_900_000_000, nanos))
        assertEquals(60.0, seconds(1_900_000_060), 0.0)
        assertEquals(59.0, seconds(1_900_000_059), 0.0)
        // A nanosecond is lost in a Date's Double this far from 2001.
        assertEquals(60.0, seconds(1_900_000_060, nanos = 1), 0.0)
        assertEquals(59.5, seconds(1_900_000_060, nanos = 500_000_000), 0.0)
        // Without an absolute expiry, `Date.distantPast`.
        assertEquals(-64_035_769_600.0, seconds(null), 0.0)
        assertEquals(-1_900_000_001.0, seconds(-1), 0.0)
    }

    @Test
    fun aSessionInItsLastMinuteRefreshesBeforeItsTokenIsUsed() = runTest {
        for ((expiresAt, refreshes) in listOf(1_900_000_060L to false, 1_900_000_059L to true)) {
            // The refreshed session expires as soon: the launch refreshes it, and so does the token's next use.
            val transport = RecordingTransport { jsonResponse(200, sessionAnswer.replace("1900003600", expiresAt.toString())) }
            val session = store(transport, saved(savedSession.replace("1900003600", expiresAt.toString())))
            session.bootstrap()
            assertEquals("tok-a", session.accessToken())
            assertEquals(if (refreshes) listOf("/auth/v1/token", "/auth/v1/token") else emptyList(), transport.requests.map { it.path })
        }
    }

    @Test
    fun signingOutInTheSessionsLastMinuteRefreshesItForTheServerAndIgnoresWhatFails() = runTest {
        val refreshed = sessionAnswer.replace("tok-a", "tok-b")
        // The session's expiry, the refresh's answer (nil: none is asked for) and the token the logout is sent with.
        val cases = listOf<Triple<Long, (suspend () -> HttpResponse)?, String>>(
            Triple(1_900_000_060, null, "tok-a"),
            Triple(1_900_000_059, { jsonResponse(200, refreshed) }, "tok-b"),
            Triple(1_899_996_400, { jsonResponse(200, refreshed) }, "tok-b"),
            // Only the answer's token is used: whichever account it is for, nothing of it is kept.
            Triple(1_900_000_059, { jsonResponse(200, answer(otherUserId, "tok-c")) }, "tok-c"),
            Triple(1_900_000_059, { jsonResponse(200, refreshed.replace("\"is_anonymous\":false", "\"is_anonymous\":true")) }, "tok-b"),
            Triple(1_900_000_059, { jsonResponse(400, """{"code":"refresh_token_not_found","message":"Invalid Refresh Token"}""") }, "tok-a"),
            Triple(1_900_000_059, { jsonResponse(503, """{"message":"Unavailable"}""") }, "tok-a"),
            Triple(1_900_000_059, { jsonResponse(200, "garbage") }, "tok-a"),
            Triple(1_900_000_059, { throw IOException("The Internet connection appears to be offline.") }, "tok-a"),
        )
        val logouts = listOf<suspend () -> HttpResponse>(
            { jsonResponse(204) },
            { jsonResponse(500, """{"message":"Down"}""") },
            { throw IOException("The Internet connection appears to be offline.") },
        )
        for ((expiresAt, refresh, bearer) in cases) {
            for (logout in logouts) {
                val transport = RecordingTransport { request ->
                    when (request.path) {
                        "/auth/v1/verify" -> jsonResponse(200, answer(userId, "tok-a", expiresAt = expiresAt))
                        "/auth/v1/logout" -> logout()
                        else -> refresh!!()
                    }
                }
                val persistence = MemorySessionPersistence()
                val session = store(transport, persistence)
                session.verifyCode(email = "person@example.test", code = "123456")
                session.signOut()
                assertEquals(AuthenticationState.Welcome, session.state)
                assertNull(persistence.data)
                val requests = transport.requests.drop(1)
                assertEquals(
                    "$expiresAt",
                    listOfNotNull(refresh?.let { "/auth/v1/token?grant_type=refresh_token - " + """{"refresh_token":"ref-tok-a"}""" }) +
                        """/auth/v1/logout?scope=local Bearer $bearer {}""",
                    requests.map { "${it.path}?${Url.parse(it.url)?.query} ${it.header("Authorization") ?: "-"} ${it.body!!.decodeToString()}" },
                )
            }
        }
    }

    @Test
    fun anExpiryALongCannotHoldStaysAtTheEndOfItsRangeAndTheSessionIsKept() = runTest {
        // The clock, `expires_in`, and the expiry kept: Swift's `Int(Date().timeIntervalSince1970)` plus `expires_in`,
        // or the end of the range it overflows.
        val cases = listOf(
            Triple(Instant.ofEpochSecond(1_900_000_000), Long.MAX_VALUE - 1_900_000_001, Long.MAX_VALUE - 1),
            Triple(Instant.ofEpochSecond(1_900_000_000), Long.MAX_VALUE - 1_900_000_000, Long.MAX_VALUE),
            Triple(Instant.ofEpochSecond(1_900_000_000), Long.MAX_VALUE - 1_899_999_999, Long.MAX_VALUE),
            Triple(Instant.ofEpochSecond(1_900_000_000), Long.MAX_VALUE, Long.MAX_VALUE),
            Triple(Instant.ofEpochSecond(1_900_000_000, 999_999_999), Long.MAX_VALUE - 1_900_000_000, Long.MAX_VALUE),
            Triple(Instant.ofEpochSecond(1_900_000_000), Long.MIN_VALUE, Long.MIN_VALUE + 1_900_000_000),
            // Before 1970 a negative `expires_in` can pass the start of the range. The seconds are truncated towards zero.
            Triple(Instant.ofEpochSecond(-10), Long.MIN_VALUE + 11, Long.MIN_VALUE + 1),
            Triple(Instant.ofEpochSecond(-10), Long.MIN_VALUE + 10, Long.MIN_VALUE),
            Triple(Instant.ofEpochSecond(-10), Long.MIN_VALUE + 9, Long.MIN_VALUE),
            Triple(Instant.ofEpochSecond(-10), Long.MIN_VALUE, Long.MIN_VALUE),
            Triple(Instant.ofEpochSecond(-11, 500_000_000), Long.MIN_VALUE + 10, Long.MIN_VALUE),
            Triple(Instant.ofEpochSecond(-11, 500_000_000), Long.MIN_VALUE + 9, Long.MIN_VALUE),
            Triple(Instant.ofEpochSecond(-10), Long.MAX_VALUE, Long.MAX_VALUE - 10),
        )
        for ((clock, expiresIn, expiresAt) in cases) {
            val answer = sessionAnswer.replace("\"expires_in\":3600,\"expires_at\":1900003600", "\"expires_in\":$expiresIn")
            val transport = RecordingTransport { jsonResponse(200, answer) }
            val persistence = MemorySessionPersistence()
            val session = store(transport, persistence, clock = { clock })
            session.verifyCode(email = "person@example.test", code = "123456")
            assertEquals("$clock $expiresIn", UUID.fromString(userId), session.user?.id)
            val saved = decodeSwiftKeys(AuthSession.serializer(), persistence.data!!)
            assertEquals("$clock $expiresIn", expiresIn, saved.expiresIn)
            assertEquals("$clock $expiresIn", expiresAt, saved.expiresAt)
        }
        // A session that far out is used without a refresh.
        val transport = RecordingTransport { jsonResponse(200, sessionAnswer.replace("\"expires_in\":3600,\"expires_at\":1900003600", "\"expires_in\":${Long.MAX_VALUE}")) }
        val session = store(transport)
        session.verifyCode(email = "person@example.test", code = "123456")
        assertEquals("tok-a", session.accessToken())
        assertEquals(listOf("/auth/v1/verify"), transport.requests.map { it.path })
    }

    @Test
    fun reopeningTheSavedAccountIsAnnouncedAsSuch() = runTest {
        val session = store(RecordingTransport { throw AssertionError("Unexpected request") }, saved(savedSession))
        val announced = mutableListOf<String>()
        session.identityChanges.sink { announced += "${session.reopeningSavedAccount} ${session.state.identity}" }
        session.bootstrap()
        assertEquals(listOf("true $userId"), announced)
        assertFalse(session.reopeningSavedAccount)
        session.forceSignOut()
        assertEquals(listOf("true $userId", "false null"), announced)
    }

    @Test
    fun refreshRefusalsSignOutAndOtherFailuresKeepTheSession() = runTest {
        val cases = listOf(
            Triple(400, """{"error":"invalid_grant","error_description":"Invalid Refresh Token"}""", "missingSession"),
            Triple(400, """{"code":"refresh_token_already_used"}""", "missingSession"),
            Triple(400, """{"code":"session_expired","error":"other"}""", "missingSession"),
            Triple(400, """{"error":"user_banned"}""", "missingSession"),
            Triple(400, """{"code":"other","msg":"Nope"}""", "server(Nope)"),
            Triple(400, """{"code":"","error":"invalid_grant"}""", "requestFailed(400)"),
            Triple(401, "{}", "missingSession"),
            Triple(403, null, "missingSession"),
            Triple(500, "", "requestFailed(500)"),
            Triple(503, """{"message":"Unavailable"}""", "server(Unavailable)"),
            Triple(422, """{"error_description":"d","message":"m","msg":"g"}""", "server(d)"),
            // The older shape, whose `code` is the HTTP status: `error_code` is the code, and comes before `code`.
            Triple(400, """{"code":400,"msg":"numeric code"}""", "server(numeric code)"),
            Triple(400, """{"code":400,"error_code":"invalid_grant","msg":"Invalid Refresh Token"}""", "missingSession"),
            Triple(400, """{"code":4e2,"error_code":"user_banned"}""", "missingSession"),
            Triple(400, """{"code":400,"error_code":"other","msg":"Nope"}""", "server(Nope)"),
            Triple(400, """{"code":"other","error_code":"invalid_grant"}""", "missingSession"),
            Triple(400, """{"code":"invalid_grant","error_code":"other"}""", "requestFailed(400)"),
            Triple(400, """{"code":"invalid_grant","error_code":null}""", "missingSession"),
            Triple(400, """{"errorCode":"session_not_found"}""", "missingSession"),
            Triple(400, """{"code":null,"error_code":"session_expired","error":"other"}""", "missingSession"),
            // A field that is not a string reads as none, and leaves the others readable.
            Triple(400, """{"code":true,"error_code":"invalid_grant","msg":"Bool"}""", "missingSession"),
            Triple(400, """{"code":[400],"msg":"List"}""", "server(List)"),
            Triple(400, """{"code":400,"error_code":5,"msg":"Number"}""", "server(Number)"),
            Triple(400, """{"code":"refresh_token_not_found","error_code":{"a":1}}""", "missingSession"),
            Triple(400, """{"code":{"x":"invalid_grant"},"error":"user_not_found"}""", "missingSession"),
            Triple(400, """{"error":5,"error_description":"Five"}""", "server(Five)"),
            Triple(503, """{"error_description":5,"message":["m"],"msg":"g"}""", "server(g)"),
            Triple(503, """{"error_description":false,"message":null,"msg":0}""", "requestFailed(503)"),
            Triple(502, "garbage", "requestFailed(502)"),
            Triple(400, """["invalid_grant"]""", "requestFailed(400)"),
        )
        for ((status, body, expected) in cases) {
            val persistence = saved(savedSession)
            val session = store(RecordingTransport { jsonResponse(status, body ?: "") }, persistence)
            session.bootstrap()
            try {
                session.accessToken(forceRefresh = true)
                fail("Expected $expected")
            } catch (error: Exception) {
                assertEquals("$status $body", expected, describe(error))
            }
            val signedOut = expected == "missingSession"
            assertEquals("$status $body", !signedOut, session.signedIn)
            assertEquals("$status $body", signedOut, persistence.data == null)
        }
    }

    @Test
    fun otherAuthAnswersMapAsTheSwiftMapsThem() = runTest {
        val cases = listOf(
            Triple(429, """{"code":"over_email_send_rate_limit","msg":"Too many"}""", "server(Too many)"),
            // The older shape, whose `code` is the HTTP status.
            Triple(429, """{"code":429,"msg":"Too many"}""", "server(Too many)"),
            Triple(429, """{"code":429,"error_code":"over_email_send_rate_limit","msg":"Too many"}""", "server(Too many)"),
            // A field that is not a string reads as none, and leaves the others readable.
            Triple(422, """{"error_description":{"a":"b"},"message":"m"}""", "server(m)"),
            Triple(422, """{"message":5}""", "requestFailed(422)"),
            // Only a refresh's refusal signs out.
            Triple(401, """{"code":"invalid_grant"}""", "requestFailed(401)"),
            Triple(500, """{"message":null,"msg":"second"}""", "server(second)"),
            Triple(500, """{"error_description":""}""", "server()"),
            Triple(404, "[]", "requestFailed(404)"),
            Triple(300, null, "requestFailed(300)"),
            // An answer without a body of its own may be any JSON, or none.
            Triple(200, "garbage", "decoding"),
            Triple(200, """{"a":garbage}""", "decoding"),
            Triple(200, """[1,2,{"b":null}]""", "ok"),
            Triple(200, "\"text\"", "ok"),
            Triple(200, "-0.5e+3", "ok"),
            Triple(200, null, "ok"),
        )
        for ((status, body, expected) in cases) {
            val session = store(RecordingTransport { jsonResponse(status, body ?: "") })
            val result = try {
                session.sendCode(to = "person@example.test", language = AppLanguage.Fr)
                "ok"
            } catch (error: Exception) {
                describe(error)
            }
            assertEquals("$status $body", expected, result)
        }
    }

    /** The auth service's two shapes: the 2024-01-01 one `X-Supabase-Api-Version` asks for, and the older one. */
    @Test
    fun aRefusedRefreshSignsOutInEitherShapeOfTheAuthServicesError() = runTest {
        for (code in listOf("refresh_token_not_found", "session_not_found", "refresh_token_already_used")) {
            for (body in listOf(
                """{"code":400,"error_code":"$code","msg":"Invalid Refresh Token: $code"}""",
                """{"code":"$code","message":"Invalid Refresh Token: $code"}""",
            )) {
                val persistence = saved(savedSession)
                val session = store(RecordingTransport { jsonResponse(400, body) }, persistence)
                session.bootstrap()
                try {
                    session.accessToken(forceRefresh = true)
                    fail("Expected the refusal for $body")
                } catch (_: AuthenticationError.MissingSession) {
                }
                assertEquals(body, AuthenticationState.SignedOut, session.state)
                assertNull(body, persistence.data)

                // The same refusal of the refresh a launch makes in the session's last minute.
                val expiring = saved(savedSession.replace("1900003600", "1900000030"))
                val relaunched = store(RecordingTransport { jsonResponse(400, body) }, expiring)
                relaunched.bootstrap()
                assertEquals(body, AuthenticationState.SignedOut, relaunched.state)
                assertNull(body, expiring.data)
            }
        }
    }

    /** The server's message, in either shape of the auth service's error, shown as the iPhone shows it. */
    @Test
    fun aWrongCodeAndARateLimitShowTheServersMessageInEitherShape() = runTest {
        val localizer = Localizer(AppLanguage.En)
        val wrongCode = "Token has expired or is invalid"
        val rateLimit = "Email rate limit exceeded"
        val tooSoon = "For security purposes, you can only request this after 37 seconds."
        val cases = listOf(
            Triple("verify", 403, """{"code":403,"error_code":"otp_expired","msg":"$wrongCode"}""") to (wrongCode to "error.invalidCode"),
            Triple("verify", 403, """{"code":"otp_expired","message":"$wrongCode"}""") to (wrongCode to "error.invalidCode"),
            Triple("send", 429, """{"code":429,"error_code":"over_email_send_rate_limit","msg":"$rateLimit"}""") to (rateLimit to "error.rateLimited"),
            Triple("send", 429, """{"code":"over_email_send_rate_limit","message":"$rateLimit"}""") to (rateLimit to "error.rateLimited"),
            Triple("send", 429, """{"code":429,"error_code":"over_email_send_rate_limit","msg":"$tooSoon"}""") to (tooSoon to "auth.verifyFailed"),
            Triple("send", 429, """{"code":"over_email_send_rate_limit","message":"$tooSoon"}""") to (tooSoon to "auth.verifyFailed"),
        )
        for ((request, expected) in cases) {
            val (call, status, body) = request
            val persistence = MemorySessionPersistence()
            val session = store(RecordingTransport { jsonResponse(status, body) }, persistence)
            val error = try {
                if (call == "verify") session.verifyCode(email = "person@example.test", code = "000000") else session.sendCode(to = "person@example.test", language = AppLanguage.En)
                null
            } catch (error: AuthenticationError.Server) {
                error
            }
            assertEquals(body, expected.first, error?.serverMessage)
            assertEquals(body, localizer.text(expected.second), localizer.errorMessage(error!!))
            assertNull(body, session.user)
            assertNull(body, persistence.data)
        }
    }

    /**
     * A deliberate difference: Swift's store is held to the main actor by its compiler, where the API client may ask
     * from any thread on Android. What it asks is done on the store's thread, the test's here.
     */
    @Test
    fun whatTheClientAsksFromAnotherThreadIsDoneOnTheStoresThread() = runTest {
        val storeThread = Thread.currentThread()
        val elsewhere = Collections.synchronizedList(mutableListOf<String>())
        fun note(what: String) {
            if (Thread.currentThread() !== storeThread) elsewhere += "$what on ${Thread.currentThread().name}"
        }
        val persistence = object : SessionPersistence {
            var data: ByteArray? = savedSession.toByteArray()

            override fun save(data: ByteArray) {
                note("save")
                this.data = data
            }

            override fun load(): ByteArray? = data.also { note("load") }

            override fun delete() {
                note("delete")
                data = null
            }
        }
        val transport = RecordingTransport { jsonResponse(200, sessionAnswer.replace("tok-a", "tok-b")) }
        val session = store(transport, persistence, clock = { note("now"); now })
        session.bootstrap()
        val client: DeliveryApiClient.Session = session
        withContext(Dispatchers.Default) {
            assertEquals("tok-a", client.accessToken())
            assertEquals("tok-b", client.accessToken(forceRefresh = true))
            client.forceSignOut()
            // Done before the call returned, as Swift's synchronous call is.
            assertFalse(client.signedIn)
            assertNull(persistence.data)
        }
        assertEquals(emptyList<String>(), elsewhere)
        assertEquals(AuthenticationState.SignedOut, session.state)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun refreshesAskedAtOnceFromAnotherThreadAndTheMainOneAreOneRefresh() = runTest {
        val started = CompletableDeferred<Unit>()
        val held = CompletableDeferred<HttpResponse>()
        val transport = RecordingTransport {
            started.complete(Unit)
            held.await()
        }
        val session = store(transport, saved(savedSession))
        session.bootstrap()
        // One of Default's threads at a time, in turn: once a later call there has run, an earlier one has reached the store.
        val other = Dispatchers.Default.limitedParallelism(1)
        val fromOther = async(other) { session.accessToken(forceRefresh = true) }
        val fromMain = async { session.accessToken(forceRefresh = true) }
        started.await()
        val laterFromOther = async(other) { session.accessToken(forceRefresh = true) }
        withContext(other) {}
        // The store takes the call handed to it, which waits for the refresh under way.
        runCurrent()
        held.complete(jsonResponse(200, sessionAnswer.replace("tok-a", "tok-b")))
        assertEquals(listOf("tok-b", "tok-b", "tok-b"), listOf(fromOther, fromMain, laterFromOther).awaitAll())
        assertEquals(listOf("ref-a"), transport.refreshTokens())
    }

    /** Requests answered at once, and the refreshes sent with `held`'s refresh token held until the test answers them. */
    private class HeldRefresh(val held: String, val answer: suspend (HttpRequest) -> HttpResponse) {
        val started = CompletableDeferred<Unit>()
        val response = CompletableDeferred<HttpResponse>()
        val transport = RecordingTransport { request ->
            if (request.url.endsWith("grant_type=refresh_token") && request.body!!.decodeToString().contains("\"$held\"")) {
                started.complete(Unit)
                response.await()
            } else {
                answer(request)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun callersAtOnceShareOneRefresh() = runTest {
        val refresh = HeldRefresh("ref-a") { throw AssertionError("Unexpected request ${it.url}") }
        // A session in its last minute: the launch refreshes it.
        val session = store(refresh.transport, saved(savedSession.replace("1900003600", "1900000030")))
        val launch = async { session.bootstrap() }
        refresh.started.await()
        val second = async { session.accessToken() }
        val third = async { session.accessToken(forceRefresh = true) }
        runCurrent()
        refresh.response.complete(jsonResponse(200, sessionAnswer.replace("tok-a", "tok-b")))
        launch.await()
        assertEquals(listOf("tok-b", "tok-b"), listOf(second, third).awaitAll())
        assertEquals(listOf("ref-a"), refresh.transport.refreshTokens())
    }

    @Test
    fun aCancelledCallerThatStartedARefusedRefreshStillSignsOut() = runTest {
        for (body in listOf("""{"code":400,"error_code":"refresh_token_not_found","msg":"x"}""", """{"code":"refresh_token_not_found","message":"x"}""")) {
            val refresh = HeldRefresh("ref-a") { throw AssertionError("Unexpected request ${it.url}") }
            val persistence = saved(savedSession)
            val session = store(refresh.transport, persistence)
            session.bootstrap()
            val caller = async { runCatching { session.accessToken(forceRefresh = true) } }
            refresh.started.await()
            caller.cancel()
            refresh.response.complete(jsonResponse(400, body))
            caller.join()
            assertTrue(caller.isCancelled)
            assertEquals(body, AuthenticationState.SignedOut, session.state)
            assertNull(body, persistence.data)
        }
    }

    @Test
    fun aForcedSignOutDuringARefreshCancelsItsCallerAndKeepsNothing() = runTest {
        val refresh = HeldRefresh("ref-a") { throw AssertionError("Unexpected request ${it.url}") }
        val persistence = saved(savedSession)
        val session = store(refresh.transport, persistence)
        session.bootstrap()
        val caller = async { runCatching { session.accessToken(forceRefresh = true) } }
        refresh.started.await()
        session.forceSignOut()
        refresh.response.complete(jsonResponse(200, sessionAnswer.replace("tok-a", "tok-b")))
        val result = caller.await()
        assertTrue(result.toString(), result.exceptionOrNull() is CancellationException)
        assertEquals(AuthenticationState.SignedOut, session.state)
        assertNull(persistence.data)
        assertEquals(listOf("ref-a"), refresh.transport.refreshTokens())
    }

    /** The previous account's refresh, answered once the account changed, leaves no task for later refreshes to join. */
    @Test
    fun aRefreshAnsweredAfterTheAccountChangedLeavesTheNextAccountsRefreshesWorking() = runTest {
        val refresh = HeldRefresh("ref-a") { request ->
            if (request.path == "/auth/v1/verify") {
                // In its last minute: its next token refreshes it.
                jsonResponse(200, answer(otherUserId, "tok-b1", expiresAt = 1_900_000_030))
            } else {
                jsonResponse(200, answer(otherUserId, "tok-b2"))
            }
        }
        val persistence = saved(savedSession)
        val session = store(refresh.transport, persistence)
        session.bootstrap()
        val first = async { runCatching { session.accessToken(forceRefresh = true) } }
        refresh.started.await()
        session.verifyCode(email = "other@example.test", code = "123456")
        refresh.response.complete(jsonResponse(200, sessionAnswer.replace("tok-a", "tok-a2")))
        assertTrue(first.await().exceptionOrNull() is CancellationException)
        assertEquals("tok-b2", session.accessToken())
        assertEquals("tok-b2", session.accessToken(forceRefresh = true))
        assertEquals(listOf("ref-a", "ref-tok-b1", "ref-tok-b2"), refresh.transport.refreshTokens())
        assertEquals(UUID.fromString(otherUserId), session.user?.id)
        assertTrue(persistence.data!!.decodeToString().contains("tok-b2"))
    }

    /** The next account never joins the refresh still under way for the previous one, whatever that one is answered. */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun theNextAccountRefreshesOnItsOwnWhileThePreviousOnesRefreshIsUnderWay() = runTest {
        val late = listOf(
            jsonResponse(200, sessionAnswer.replace("tok-a", "tok-a2")),
            jsonResponse(400, """{"code":400,"error_code":"refresh_token_not_found","msg":"x"}"""),
        )
        for (lateAnswer in late) {
            val refresh = HeldRefresh("ref-a") { request ->
                jsonResponse(200, answer(otherUserId, if (request.path == "/auth/v1/verify") "tok-b1" else "tok-b2"))
            }
            val persistence = saved(savedSession)
            val session = store(refresh.transport, persistence)
            session.bootstrap()
            val first = async { runCatching { session.accessToken(forceRefresh = true) } }
            refresh.started.await()
            session.verifyCode(email = "other@example.test", code = "123456")
            val next = async { session.accessToken(forceRefresh = true) }
            runCurrent()
            // Answered at once: joined to the previous account's refresh, it would wait for that one's answer.
            val refreshedAlone = next.isCompleted
            refresh.response.complete(lateAnswer)
            assertTrue("The next account's refresh waited for the previous one's", refreshedAlone)
            assertEquals("tok-b2", next.await())
            val failure = first.await().exceptionOrNull()
            // A refusal of the previous account's refresh is no reason to sign the next one out.
            if (lateAnswer.statusCode == 200) assertTrue("$failure", failure is CancellationException) else assertTrue("$failure", failure is AuthenticationError.MissingSession)
            assertEquals(UUID.fromString(otherUserId), session.user?.id)
            assertEquals("tok-b2", session.accessToken())
            assertTrue(persistence.data!!.decodeToString().contains("tok-b2"))
            assertEquals(listOf("ref-a", "ref-tok-b1"), refresh.transport.refreshTokens())
        }
    }
}
