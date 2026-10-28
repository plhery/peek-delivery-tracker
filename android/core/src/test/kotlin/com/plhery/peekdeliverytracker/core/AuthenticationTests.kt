package com.plhery.peekdeliverytracker.core

import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/**
 * Authentication.swift has no tests of its own. These hold what it does with the auth service's requests and answers,
 * its addresses, digests, saved sessions and expiry: each expected value is what Authentication.swift gives for the
 * same input, built with swiftc on macOS for this port.
 */
class AuthenticationTests {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://auth.example.test", supabaseUrl = "https://auth.example.test", supabasePublishableKey = "pk",
        googleAuthEnabled = true, appleAuthEnabled = true, emailOtpEnabled = true, appGroupIdentifier = "auth.test",
    )
    private val now = Instant.ofEpochSecond(1_900_000_000)
    private val userId = "0B5E6F1A-1111-4222-8333-944455556666"

    /** The auth service's session, snake_case. */
    private val sessionAnswer = """{"access_token":"tok-a","token_type":"bearer","expires_in":3600,"expires_at":1900003600,"refresh_token":"ref-a",""" +
        """"user":{"id":"0b5e6f1a-1111-4222-8333-944455556666","email":"person@example.test","is_anonymous":false,""" +
        """"identities":[{"provider":"apple","identity_data":{"sub":"apple.sub.1"}}]}}"""

    /** The same session as the Keychain keeps it. */
    private val savedSession = """{"accessToken":"tok-a","expiresAt":1900003600,"expiresIn":3600,"refreshToken":"ref-a","tokenType":"bearer",""" +
        """"user":{"email":"person@example.test","id":"$userId","identities":[{"identityData":{"sub":"apple.sub.1"},"provider":"apple"}],"isAnonymous":false}}"""

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
    ) = testSessionStore(
        configuration, backgroundScope, persistence, transport, apple, defaults, web, analytics, now = { now }, random = { bytes0To47.copyOf(it) },
    )

    private fun saved(text: String) = MemorySessionPersistence().apply { data = text.toByteArray() }

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
        val session = store(transport, persistence, defaults, apple) { event, outcome -> events += listOfNotNull(event, outcome?.name).joinToString(" ") }

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
            // A numeric code makes the whole error unreadable to the Swift's decoder, its message too.
            Triple(400, """{"code":400,"msg":"numeric code"}""", "requestFailed(400)"),
            Triple(502, "garbage", "requestFailed(502)"),
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
            Triple(429, """{"code":429,"msg":"Too many"}""", "requestFailed(429)"),
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
}
