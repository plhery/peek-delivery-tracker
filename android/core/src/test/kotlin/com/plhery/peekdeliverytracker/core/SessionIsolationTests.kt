package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import java.io.IOException
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.async
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/**
 * SessionIsolationTests in ParcelLogicTests.swift: those that need SessionStore and the API client. Those that need
 * ParcelStore come with its port; where one of them checks something of the session or the client alone, that part
 * is here under its name.
 */
class SessionIsolationTests {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://session.test", supabaseUrl = "https://session.test", supabasePublishableKey = "public",
        googleAuthEnabled = false, appleAuthEnabled = true, emailOtpEnabled = true, appGroupIdentifier = "session.test",
    )

    /** `Date()` in the Swift tests, held still. */
    private val now = Instant.parse("2026-09-08T12:00:00Z")

    private fun TestScope.sessionStore(
        transport: HttpTransport,
        persistence: SessionPersistence = MemorySessionPersistence(),
        apple: AppleSignInAuthorizing = StubAppleSignIn(),
    ) = testSessionStore(configuration, backgroundScope, persistence, transport, apple, now = { now })

    private fun RecordingTransport.respond(body: String, status: Int = 200) {
        handler = { jsonResponse(status, body) }
    }

    private fun RecordingTransport.offline() {
        handler = { throw IOException("The Internet connection appears to be offline.") }
    }

    private fun sessionJson(user: AuthUser, expired: Boolean = false, accessToken: String = "access"): String {
        val session = AuthSession(
            accessToken = accessToken, tokenType = "bearer", expiresIn = 3600,
            expiresAt = now.epochSecond + if (expired) -120 else 3600, refreshToken = "refresh", user = user,
        )
        return JsonEncoder.deliveryTracker.encode(AuthSession.serializer(), session).decodeToString()
    }

    private suspend fun authorize(
        session: SessionStore,
        transport: RecordingTransport,
        id: UUID = UUID.randomUUID(),
        expired: Boolean = false,
        email: String = "test@example.com",
    ) {
        val user = AuthUser(id = id, email = email, isAnonymous = false)
        transport.respond(sessionJson(user, expired, accessToken = "token-${id.uuidString}"))
        session.verifyCode(email = email, code = "123456")
    }

    private suspend fun authorizeWithApple(session: SessionStore, transport: RecordingTransport, id: UUID, appleUser: String = "apple-user") {
        val user = AuthUser(
            id = id, email = "relay@example.com", isAnonymous = false,
            identities = listOf(AuthUser.Identity(provider = "apple", identityData = AuthUser.IdentityData(sub = appleUser))),
        )
        transport.respond(sessionJson(user))
        session.signInWithApple()
    }

    private fun verification(): NativeVerification {
        val unsupported = NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) })
        return NativeVerification(unsupported, TurnstilePresenter { fail("Unexpected browser check"); "" }, emptyMap(), {}, language = { AppLanguage.En })
    }

    private fun client(session: DeliveryApiClient.Session, transport: HttpTransport) =
        DeliveryApiClient(configuration, session, transport, verification(), deviceName = "Synthetic phone", region = { Locale.UK })

    private fun parcel() = Parcel(
        id = UUID.randomUUID(), trackingNumber = "12345678", label = "Private account A parcel", carrier = CarrierId.Unknown,
        createdAt = "2026-09-08T00:00:00Z", syncStatus = SyncStatus.Ok, notificationsMuted = false,
    )

    private fun packageList(vararg parcels: Parcel): String =
        JsonEncoder.deliveryTracker.encode(PackageListResponse.serializer(), PackageListResponse(parcels.toList())).decodeToString()

    @Test
    fun appleExchangesIdentityTokenWithOriginalNonceAndPersistsSession() = runTest {
        val apple = StubAppleSignIn()
        val persistence = MemorySessionPersistence()
        val user = AuthUser(id = UUID.randomUUID(), email = "relay@example.com", isAnonymous = false)
        val response = sessionJson(user)
        val receivedNonces = mutableListOf<String>()
        val transport = RecordingTransport { request ->
            assertEquals("/auth/v1/token", request.path)
            assertEquals("grant_type=id_token", Url.parse(request.url)?.query)
            assertEquals("POST", request.method)
            val body = Json.parseToJsonElement(request.body!!.decodeToString()).jsonObject.mapValues { it.value.jsonPrimitive.content }
            assertEquals("apple", body["provider"])
            assertEquals("apple-identity-token", body["id_token"])
            receivedNonces += body["nonce"] ?: ""
            jsonResponse(200, response)
        }
        val session = sessionStore(transport, persistence, apple)
        session.signInWithApple()
        assertEquals(user, session.user)
        assertNotNull(persistence.data)
        session.signInWithApple()
        assertEquals(2, receivedNonces.size)
        assertEquals(2, receivedNonces.toSet().size)
        assertTrue(receivedNonces.all { it.length == 64 })
        assertEquals(receivedNonces.map(AppleSignInNonce::digest), apple.nonces)
        assertNotEquals(receivedNonces, apple.nonces)
    }

    @Test
    fun cancelledAppleSignInDoesNotCallTheServerOrSaveASession() = runTest {
        val apple = StubAppleSignIn()
        apple.result = Result.failure(AuthenticationError.OauthCancelled())
        val persistence = MemorySessionPersistence()
        val session = sessionStore(RecordingTransport { throw AssertionError("Cancelled Apple sign-in reached the server") }, persistence, apple)
        try {
            session.signInWithApple()
            fail("Expected cancellation")
        } catch (_: AuthenticationError.OauthCancelled) {
        }
        assertNull(session.user)
        assertNull(persistence.data)
    }

    @Test
    fun appleAuthorizationCannotRestoreASessionAfterSignOut() = runTest {
        val apple = StubAppleSignIn()
        val persistence = MemorySessionPersistence()
        val transport = RecordingTransport()
        val session = sessionStore(transport, persistence, apple)
        authorize(session, transport)
        apple.beforeReturning = { session.forceSignOut() }
        transport.handler = { throw AssertionError("Stale Apple sign-in reached the server") }
        try {
            session.signInWithApple()
            fail("Expected cancellation")
        } catch (_: CancellationException) {
        }
        assertNull(session.user)
        assertNull(persistence.data)
    }

    @Test
    fun deletingAnAppleAccountSignsInWithAppleAgainForARevocationCode() = runTest {
        val apple = StubAppleSignIn()
        val transport = RecordingTransport()
        val session = sessionStore(transport, apple = apple)
        val id = UUID.randomUUID()
        authorizeWithApple(session, transport, id)
        val code = session.reauthenticateWithAppleBeforeDeletion()
        assertEquals("apple-code", code)
        assertEquals(2, apple.nonces.size)
        assertEquals(id, session.user?.id)
    }

    @Test
    fun deletionRefusesAnotherAppleAccountBeforeReachingTheServer() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorizeWithApple(session, transport, UUID.randomUUID(), appleUser = "another-apple-user")
        transport.handler = { throw AssertionError("Another Apple Account reached the server") }
        try {
            session.reauthenticateWithAppleBeforeDeletion()
            fail("Expected a refusal")
        } catch (_: AuthenticationError.OtherAppleAccount) {
        }
        assertNotNull(session.user)
    }

    @Test
    fun deletingAnAccountWithoutAppleDoesNotAskForApple() = runTest {
        val apple = StubAppleSignIn()
        val transport = RecordingTransport()
        val session = sessionStore(transport, apple = apple)
        val user = AuthUser(id = UUID.randomUUID(), email = "test@example.com", isAnonymous = false, identities = listOf(AuthUser.Identity(provider = "email")))
        transport.respond(sessionJson(user))
        session.verifyCode(email = "test@example.com", code = "123456")
        val code = session.reauthenticateWithAppleBeforeDeletion()
        assertNull(code)
        assertTrue(apple.nonces.isEmpty())
    }

    /**
     * Adapted: the JVM's collector frees reference cycles and nothing here holds a weak reference, so the Swift test's
     * checks that the store outlives its last strong reference and is freed with the client have nothing to check. What
     * stays: the client keeps the store it was given and reads its session, the list under it and, once it signs out,
     * nothing sent.
     */
    @Test
    fun apiClientKeepsSessionAliveWithoutCreatingARetainCycle() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val client = client(session, transport)
        val parcel = parcel()
        transport.respond(packageList(parcel))
        val loaded = client.listPackages()
        assertEquals(listOf(parcel.id), loaded.map { it.id })
        session.forceSignOut()
        val sent = transport.requests.size
        try {
            client.listPackages()
            fail("Expected the signed-out client to stop")
        } catch (_: DeliveryApiError.AuthenticationExpired) {
        }
        assertEquals(sent, transport.requests.size)
    }

    @Test
    fun appAttestRefusalOfASignedInRequestKeepsTheSession() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val client = client(session, transport)
        val signedIn = transport.requests.size
        transport.handler = {
            jsonResponse(403, """{"error":"Please verify this device and try again."}""", mapOf("X-Lookup-Verification" to "required"))
        }
        try {
            client.queueRefreshAll()
            fail("Expected the refusal")
        } catch (_: NativeVerificationFailed) {
        }
        assertNotNull(session.user)
        assertEquals(listOf("/api/sync", "/api/sync"), transport.requests.drop(signedIn).map { it.path })
    }

    @Test
    fun offlineAndServerErrorPreserveExpiredRefreshableSession() = runTest {
        for (serverError in listOf(false, true)) {
            val transport = RecordingTransport()
            val persistence = MemorySessionPersistence()
            val session = sessionStore(transport, persistence)
            authorize(session, transport, expired = true)
            if (serverError) transport.respond("""{"message":"Unavailable"}""", status = 503) else transport.offline()
            val relaunched = sessionStore(transport, persistence)
            relaunched.bootstrap()
            assertNotNull(relaunched.user)
            assertEquals(session.user?.id, relaunched.user?.id)
            assertNotNull(persistence.data)
            // The refresh was asked for, and its failure kept the session.
            assertEquals("/auth/v1/token", transport.requests.last().path)
            relaunched.forceSignOut()
        }
    }

    @Test
    fun invalidRefreshTokenClearsSavedSession() = runTest {
        val transport = RecordingTransport()
        val persistence = MemorySessionPersistence()
        val session = sessionStore(transport, persistence)
        authorize(session, transport, expired = true)
        transport.respond("""{"code":"refresh_token_not_found","message":"Invalid refresh token"}""", status = 400)
        val relaunched = sessionStore(transport, persistence)
        relaunched.bootstrap()
        assertNull(relaunched.user)
        assertNull(persistence.data)
    }

    /**
     * Deferred to the ParcelStore port, which loads the list (`store.load()`) and keeps it empty with no error. Here: the
     * old account's list request, answered 401 once the next account signed in, ends without refreshing or signing out.
     */
    @Test
    fun lateUnauthorizedResponseCannotSignOutNextAccount() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val client = client(session, transport)
        val started = CompletableDeferred<Unit>()
        val answer = CompletableDeferred<HttpResponse>()
        transport.handler = { request ->
            if (request.path == "/api/packages" && request.method == "GET") {
                started.complete(Unit)
                answer.await()
            } else {
                jsonResponse(200, "{}")
            }
        }
        val pending = async {
            try {
                client.listPackages()
                "loaded"
            } catch (_: CancellationException) {
                "ended"
            }
        }
        started.await()
        session.forceSignOut()
        val nextUser = UUID.randomUUID()
        authorize(session, transport, id = nextUser)
        val sent = transport.requests.size
        answer.complete(jsonResponse(401, "{}"))
        assertEquals("ended", pending.await())
        assertEquals(nextUser, session.user?.id)
        assertEquals(sent, transport.requests.size)
    }

    /**
     * Deferred to the ParcelStore port, which loads the list and checks `authenticationRequired`. Here: the client's
     * request meets the outage, and the account stays signed in.
     */
    @Test
    fun authOutageDoesNotForceSignOut() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val client = client(session, transport)
        transport.respond("""{"error":"Authentication temporarily unavailable"}""", status = 503)
        try {
            client.listPackages()
            fail("Expected the outage")
        } catch (error: DeliveryApiError) {
            assertTrue(error !is DeliveryApiError.AuthenticationExpired)
        }
        assertNotNull(session.user)
    }

    @Test
    fun liveActivityRevocationSurvivesOfflineSignOutAndRestart() = runTest {
        val persistence = MemorySessionPersistence()
        val transport = RecordingTransport { throw IOException("The Internet connection appears to be offline.") }
        val format = ListSerializer(LiveActivityRevocations.Registration.serializer())
        val original = LiveActivityRevocations(configuration, transport, persistence)
        val installationId = UUID.randomUUID()
        val old = original.registration(ownerId = UUID.randomUUID(), installationId = installationId)
        original.queue()
        original.drain()
        val saved = persistence.load()?.let { decodeJson(format, it) }
        assertEquals(true, saved?.firstOrNull()?.pending)

        val restarted = LiveActivityRevocations(configuration, transport, persistence)
        val new = restarted.registration(ownerId = UUID.randomUUID(), installationId = installationId)
        assertNotEquals(old.revocationToken, new.revocationToken)
        transport.handler = { request ->
            assertEquals("/api/live-activities/revoke", request.path)
            assertNull(request.header("Authorization"))
            jsonResponse(200, """{"ok":true}""")
        }
        restarted.drain()
        val remaining = persistence.load()?.let { decodeJson(format, it) }
        assertEquals(listOf(new), remaining)
    }
}
