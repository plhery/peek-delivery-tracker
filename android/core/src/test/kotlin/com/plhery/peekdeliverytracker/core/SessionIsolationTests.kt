package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.QueueResponse
import com.plhery.peekdeliverytracker.core.api.SyncJobListResponse
import com.plhery.peekdeliverytracker.core.api.SyncJobResponse
import com.plhery.peekdeliverytracker.core.api.SyncJobStatus
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import java.io.IOException
import java.time.Instant
import java.time.ZoneId
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/**
 * SessionIsolationTests in ParcelLogicTests.swift: SessionStore, the API client and ParcelStore under them. What the
 * store leaves to a `Task` runs in the test's `backgroundScope`; the Swift tests' expectations are deferreds and flows.
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

    // UserDefaults.standard and the cache's folder, which every store of a Swift test shares.
    private val defaults = MemoryDefaults()
    private val cacheFiles = MemoryParcelCacheFiles()

    private fun TestScope.store(session: SessionStore, transport: HttpTransport): ParcelStore {
        val store = testParcelStore(configuration, session, transport, backgroundScope, defaults, cacheFiles, now = { now })
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)
        return store
    }

    private fun <T> json(serializer: SerializationStrategy<T>, value: T): String = JsonEncoder.deliveryTracker.encode(serializer, value).decodeToString()

    /** A request's JSON body, as the Swift tests' RequestLog reads it. */
    private fun body(request: HttpRequest): JsonObject = request.body?.let { Json.parseToJsonElement(it.decodeToString()).jsonObject } ?: JsonObject(emptyMap())

    /** An account the server can email, which has never chosen. */
    private val emailPreferences = NotificationPreferences(
        enabledStages = NotificationPreset.Important.stages, quietHoursStart = "22:00", quietHoursEnd = "07:00",
        timezone = "Pacific/Auckland", emailOnDelivery = null, emailAvailable = true,
    )

    /** A parcel as the server answers it, with its two mutes. */
    private fun row(parcel: Parcel, muted: Boolean, emailMuted: Boolean): String =
        """{"id":"${parcel.id.uuidString}","tracking_number":"${parcel.trackingNumber}","label":"${parcel.label}",
         "carrier":"${parcel.carrier.rawValue}","created_at":"${parcel.createdAt}","sync_status":"ok",
         "notifications_muted":$muted,"email_muted":$emailMuted,"tracking_events":[]}"""

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
     * Adapted: nothing on the JVM holds the session weakly, so the Swift test's check that the store keeps the last
     * strong reference to it has nothing to check: the store is given its session. What stays: the store loads the
     * list under it, and a forced sign-out empties the list at once.
     */
    @Test
    fun parcelStoreKeepsSessionAliveForDeferredDeliveryCleanup() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val store = store(session, transport)
        val parcel = parcel()
        transport.respond(packageList(parcel))
        store.load()
        assertEquals(listOf(parcel.id), store.parcels.map { it.id })
        session.forceSignOut()
        assertTrue(store.parcels.isEmpty())
    }

    @Test
    fun forcedSignOutClearsParcelsBeforeNextAccountLoads() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val store = store(session, transport)
        val parcel = parcel()
        transport.respond(packageList(parcel))
        store.load()
        store.undoParcel = parcel
        assertEquals(listOf(parcel.id), store.parcels.map { it.id })
        session.forceSignOut()
        assertTrue(store.parcels.isEmpty())
        assertNull(store.undoParcel)
        val nextUser = UUID.randomUUID()
        authorize(session, transport, id = nextUser)
        transport.offline()
        store.load()
        assertEquals(nextUser, session.user?.id)
        assertTrue(store.parcels.isEmpty())
    }

    @Test
    fun successfulDeleteIsReflectedInOfflineCache() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val store = store(session, transport)
        val parcel = parcel()
        transport.respond(packageList(parcel))
        store.load()
        transport.respond("""{"ok":true}""")
        store.permanentlyDelete(parcel)
        transport.offline()
        val relaunchedStore = store(session, transport)
        relaunchedStore.load()
        assertTrue(relaunchedStore.parcels.isEmpty())
        assertTrue(relaunchedStore.usingCachedData)
    }

    @Test
    fun successfulRenamePersistsForOfflineLaunch() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val store = store(session, transport)
        var parcel = parcel()
        transport.respond(packageList(parcel))
        store.load()
        parcel = parcel.copy(label = "Updated label")
        transport.respond(json(Parcel.serializer(), parcel))
        store.rename(parcel, label = parcel.label)
        transport.offline()
        val relaunchedStore = store(session, transport)
        relaunchedStore.load()
        assertEquals("Updated label", relaunchedStore.parcels.firstOrNull()?.label)
    }

    @Test
    fun lateUnauthorizedResponseCannotSignOutNextAccount() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val store = store(session, transport)
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
        val pending = async { store.load() }
        started.await()
        session.forceSignOut()
        val nextUser = UUID.randomUUID()
        authorize(session, transport, id = nextUser)
        answer.complete(jsonResponse(401, "{}"))
        pending.await()
        assertEquals(nextUser, session.user?.id)
        assertTrue(store.parcels.isEmpty())
        assertNull(store.errorMessage)
    }

    @Test
    fun listStartedBeforeDeleteCannotRestoreDeletedParcel() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val store = store(session, transport)
        val parcel = parcel()
        val data = packageList(parcel)
        transport.respond(data)
        store.load()
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
        val pending = async { store.load() }
        started.await()
        transport.respond("""{"ok":true}""")
        store.permanentlyDelete(parcel)
        answer.complete(jsonResponse(200, data))
        pending.await()
        assertTrue(store.parcels.isEmpty())
        transport.offline()
        val relaunchedStore = store(session, transport)
        relaunchedStore.load()
        assertTrue(relaunchedStore.parcels.isEmpty())
    }

    @Test
    fun liveActivitiesStayOffWhenTheyWereSwitchedOnBefore() = runTest {
        val transport = RecordingTransport()
        val shared = DeliveryWidgetSharedStore(defaults)
        shared.setLiveActivitiesEnabled(true)
        val session = sessionStore(transport)

        val store = testParcelStore(configuration, session, transport, backgroundScope, defaults, cacheFiles)

        assertFalse(store.deliveryLiveActivitiesEnabled)
        assertFalse(shared.liveActivitiesEnabled)
    }

    @Test
    fun authOutageDoesNotForceSignOut() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val store = store(session, transport)
        transport.respond("""{"error":"Authentication temporarily unavailable"}""", status = 503)
        store.load()
        assertNotNull(session.user)
        assertFalse(store.authenticationRequired)
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

    /** Adapted: StateFlow drops an equal value, so the list being the same instance shows it was not set again. */
    @Test
    fun unchangedPollKeepsTheListWithoutRepublishing() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val store = store(session, transport)
        transport.respond(packageList(parcel()))
        store.load()
        val shown = store.parcels
        store.load()
        assertSame(shown, store.parcels)
        assertEquals(1, store.parcels.size)
    }

    @Test
    fun reopenedAccountShowsItsSavedParcelsBeforeTheServerAnswers() = runTest {
        val transport = RecordingTransport()
        val persistence = MemorySessionPersistence()
        val session = sessionStore(transport, persistence)
        authorize(session, transport)
        val saved = parcel()
        transport.respond(packageList(saved))
        store(session, transport).load()

        val relaunched = sessionStore(transport, persistence)
        val relaunchedStore = store(relaunched, transport)
        transport.offline()
        relaunched.bootstrap()
        assertEquals(listOf(saved.id), relaunchedStore.parcels.map { it.id })
        assertFalse(relaunchedStore.loading)
        relaunchedStore.load()
        assertEquals(listOf(saved.id), relaunchedStore.parcels.map { it.id })
        assertTrue(relaunchedStore.usingCachedData)
    }

    @Test
    fun archivedParcelLeavesTheListBeforeTheServerAnswers() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val store = store(session, transport)
        val parcel = parcel()
        transport.respond(packageList(parcel))
        store.load()
        for (accepted in listOf(false, true)) {
            val started = CompletableDeferred<Unit>()
            val answer = CompletableDeferred<HttpResponse>()
            transport.handler = { request ->
                if (request.method == "DELETE") {
                    started.complete(Unit)
                    answer.await()
                } else {
                    jsonResponse(200, "{}")
                }
            }
            val archiving = async {
                try {
                    store.archive(parcel)
                    null
                } catch (error: Exception) {
                    error
                }
            }
            started.await()
            assertEquals(true, store.parcels.firstOrNull()?.isArchived)
            assertNull(store.undoParcel)
            if (accepted) {
                answer.complete(jsonResponse(200, """{"ok":true}"""))
                assertNull(archiving.await())
                assertEquals(true, store.parcels.firstOrNull()?.isArchived)
                assertEquals(parcel.id, store.undoParcel?.id)
            } else {
                answer.complete(jsonResponse(500, """{"error":"Archive unavailable"}"""))
                assertNotNull("Expected the refusal", archiving.await())
                assertEquals(false, store.parcels.firstOrNull()?.isArchived)
            }
        }
    }

    @Test
    fun refreshReturnsOnceQueuedAndReportsCompletion() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport)
        val store = store(session, transport)
        val job = UUID.randomUUID()
        val parcel = parcel()
        val queued = json(QueueResponse.serializer(), QueueResponse(queued = true, pending = 1, jobIds = listOf(job)))
        val jobs = json(
            SyncJobListResponse.serializer(),
            SyncJobListResponse(listOf(SyncJobResponse(id = job, status = SyncJobStatus.Succeeded, requestedAt = "2026-09-25T10:00:00Z"))),
        )
        val list = packageList(parcel)
        val jobsRequested = CompletableDeferred<Unit>()
        val jobsAnswer = CompletableDeferred<HttpResponse>()
        transport.handler = { request ->
            when (request.method to request.path) {
                "POST" to "/api/sync" -> jsonResponse(202, queued)
                "GET" to "/api/sync/jobs" -> {
                    jobsRequested.complete(Unit)
                    jobsAnswer.await()
                }
                else -> jsonResponse(200, list)
            }
        }
        val start = store.refreshAll()
        assertEquals(ParcelStore.RefreshStart.Queued, start)
        assertTrue(store.refreshing)
        jobsRequested.await()
        assertTrue(store.refreshing)
        jobsAnswer.complete(jsonResponse(200, jobs))
        val outcome = store.refreshOutcomeFlow.first { it != null }
        assertFalse(store.refreshing)
        // The parcel was not on the list before the check.
        assertEquals(ParcelStore.RefreshResult.Updated, outcome?.result)
        assertEquals(listOf(parcel.id), store.parcels.map { it.id })
    }

    @Test
    fun emailSwitchSendsTheSavedPreferencesAndShowsTheServersAnswer() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport, email = "alex@example.com")
        val store = store(session, transport)
        assertNull(store.deliveryEmail)
        val saved = emailPreferences
        transport.respond(json(NotificationPreferences.serializer(), saved))
        store.loadNotificationPreferences()
        val offered = store.deliveryEmail ?: return@runTest fail("Expected the delivery email")
        assertEquals("alex@example.com", offered.address)
        assertNull(offered.choice)

        val answer = saved.copy(emailOnDelivery = true)
        transport.respond(json(NotificationPreferences.serializer(), answer))
        val before = transport.requests.size
        store.setEmailOnDelivery(true)
        val log = transport.requests.drop(before)
        assertEquals(listOf("PATCH"), log.map { it.method })
        assertEquals("/api/push/preferences", log.first().path)
        val sent = body(log.first())
        // What was last saved goes back with the choice; what only the server knows stays out.
        assertEquals(setOf("enabledStages", "quietHoursStart", "quietHoursEnd", "timezone", "emailOnDelivery"), sent.keys)
        assertEquals(true, sent["emailOnDelivery"]?.jsonPrimitive?.boolean)
        assertEquals(saved.enabledStages.map { it.rawValue }, sent["enabledStages"]?.jsonArray?.map { it.jsonPrimitive.content })
        assertEquals("22:00", sent["quietHoursStart"]?.jsonPrimitive?.content)
        assertEquals(ZoneId.systemDefault().id, sent["timezone"]?.jsonPrimitive?.content)
        assertEquals(true, store.deliveryEmail?.isOn)

        // Saving a preset afterwards carries the choice along unchanged.
        val draft = NotificationPreferencesDraft(store.notificationPreferences).copy(preset = NotificationPreset.DeliveryDay)
        var presetAnswer = draft.preferences(timezone = "Europe/Zurich").copy(emailAvailable = true)
        transport.respond(json(NotificationPreferences.serializer(), presetAnswer))
        store.saveNotificationPreferences(draft.preferences(timezone = "Europe/Zurich"))
        val preset = body(transport.requests.last())
        assertEquals(setOf("enabledStages", "timezone", "emailOnDelivery"), preset.keys)
        assertEquals(true, preset["emailOnDelivery"]?.jsonPrimitive?.boolean)
        assertEquals(NotificationPreset.DeliveryDay.stages, store.notificationPreferences?.enabledStages)
        assertEquals(true, store.deliveryEmail?.isOn)

        // A refusal leaves what was saved.
        transport.respond("""{"error":"This account cannot be emailed"}""", status = 409)
        try {
            store.setEmailOnDelivery(false)
            fail("Expected the refusal")
        } catch (_: DeliveryApiError) {
        }
        assertEquals(true, store.deliveryEmail?.isOn)

        // The state is the server's answer, whatever was asked.
        presetAnswer = presetAnswer.copy(emailOnDelivery = false)
        transport.respond(json(NotificationPreferences.serializer(), presetAnswer))
        store.setEmailOnDelivery(true)
        assertEquals(false, store.deliveryEmail?.choice)

        // Once the server cannot email the account, there is nothing to switch.
        presetAnswer = presetAnswer.copy(emailAvailable = false)
        transport.respond(json(NotificationPreferences.serializer(), presetAnswer))
        store.loadNotificationPreferences()
        assertNull(store.deliveryEmail)
        transport.respond("{}")
        val silent = transport.requests.size
        store.setEmailOnDelivery(true)
        assertEquals(silent, transport.requests.size)
    }

    @Test
    fun aReadThatBeganBeforeTheEmailWasSwitchedCannotUndoIt() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport, email = "alex@example.com")
        val store = store(session, transport)
        val saved = emailPreferences
        val stale = json(NotificationPreferences.serializer(), saved)
        transport.respond(stale)
        store.loadNotificationPreferences()
        val answered = json(NotificationPreferences.serializer(), saved.copy(emailOnDelivery = true))
        val started = CompletableDeferred<Unit>()
        val read = CompletableDeferred<HttpResponse>()
        transport.handler = { request ->
            if (request.method == "GET") {
                started.complete(Unit)
                read.await()
            } else {
                jsonResponse(200, answered)
            }
        }
        val reading = async { store.loadNotificationPreferences() }
        started.await()
        store.setEmailOnDelivery(true)
        read.complete(jsonResponse(200, stale))
        reading.await()
        assertEquals(true, store.deliveryEmail?.isOn)
    }

    @Test
    fun returningToTheAppReadsTheEmailChoiceAgain() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport, email = "alex@example.com")
        val store = store(session, transport)
        var saved = emailPreferences.copy(emailOnDelivery = true)
        transport.respond(json(NotificationPreferences.serializer(), saved))
        store.loadNotificationPreferences()
        assertEquals(true, store.deliveryEmail?.isOn)

        // Meanwhile the email was switched off from a link in an email.
        saved = saved.copy(emailOnDelivery = false)
        val switchedOff = json(NotificationPreferences.serializer(), saved)
        val list = packageList()
        val before = transport.requests.size
        transport.handler = { request -> jsonResponse(200, if (request.path == "/api/push/preferences") switchedOff else list) }
        // The launch loads the preferences itself: only a return reads them again.
        store.setActive(true)
        store.setActive(false)
        store.setActive(true)
        store.notificationPreferencesFlow.first { it?.emailOnDelivery == false }
        runCurrent()
        assertEquals(false, store.deliveryEmail?.choice)
        assertEquals(listOf("GET"), transport.requests.drop(before).filter { it.path == "/api/push/preferences" }.map { it.method })
    }

    @Test
    fun aParcelsAlertsAreSavedOneFieldAtATimeAndKeptForAnOfflineLaunch() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        authorize(session, transport, email = "alex@example.com")
        val store = store(session, transport)
        val parcel = parcel()
        transport.respond(packageList(parcel))
        store.load()
        assertNull(store.parcels.firstOrNull()?.emailMuted)

        var before = transport.requests.size
        transport.respond(row(parcel, muted = false, emailMuted = true))
        store.setEmailMuted(parcel, muted = true)
        val log = transport.requests.drop(before)
        assertEquals(listOf("PATCH"), log.map { it.method })
        assertEquals("/api/packages/${parcel.id.uuidString}/notifications", log.first().path)
        assertEquals(JsonObject(mapOf("emailMuted" to JsonPrimitive(true))), body(log.first()))
        assertEquals(true, store.parcels.firstOrNull()?.emailMuted)
        assertEquals(false, store.parcels.firstOrNull()?.notificationsMuted)

        before = transport.requests.size
        transport.respond(row(parcel, muted = true, emailMuted = true))
        store.setMuted(parcel, muted = true)
        assertEquals(JsonObject(mapOf("muted" to JsonPrimitive(true))), body(transport.requests.drop(before).last()))
        assertEquals(true, store.parcels.firstOrNull()?.allAlertsMuted)

        // A refusal leaves the parcel as it was.
        transport.respond("""{"error":"Alerts unavailable"}""", status = 502)
        try {
            store.setEmailMuted(parcel, muted = false)
            fail("Expected the refusal")
        } catch (_: DeliveryApiError) {
        }
        assertEquals(true, store.parcels.firstOrNull()?.emailMuted)

        transport.offline()
        val relaunchedStore = store(session, transport)
        relaunchedStore.load()
        assertEquals(true, relaunchedStore.parcels.firstOrNull()?.emailMuted)
        assertEquals(true, relaunchedStore.parcels.firstOrNull()?.notificationsMuted)
    }

    @Test
    fun demoHasNoDeliveryEmail() = runTest {
        val transport = RecordingTransport()
        val session = testSessionStore(configuration, backgroundScope, transport = transport, defaults = defaults, now = { now })
        session.enterDemo()
        val store = store(session, transport)
        transport.respond(json(NotificationPreferences.serializer(), emailPreferences))
        store.loadNotificationPreferences()
        assertNotNull(store.notificationPreferences)
        assertNull(store.deliveryEmail)
        store.setEmailOnDelivery(true)
        assertNull(store.notificationPreferences?.emailOnDelivery)
        assertTrue(transport.requests.isEmpty())
    }

    @Test
    fun refusedRefreshTokenSignsOutInEitherErrorShape() = runTest {
        // Supabase Auth's answer with X-Supabase-Api-Version, and its older one, whose `code` is the HTTP status.
        val answers = listOf(
            """{"code":"refresh_token_not_found","message":"Invalid Refresh Token: Refresh Token Not Found"}""",
            """{"code":400,"error_code":"refresh_token_not_found","msg":"Invalid Refresh Token: Refresh Token Not Found"}""",
        )
        for (answer in answers) {
            val transport = RecordingTransport()
            val persistence = MemorySessionPersistence()
            val session = sessionStore(transport, persistence)
            authorize(session, transport, expired = true)
            val versions = mutableListOf<String?>()
            transport.handler = { request ->
                versions += request.header("X-Supabase-Api-Version")
                jsonResponse(400, answer)
            }
            try {
                session.accessToken()
                fail("Expected the refusal")
            } catch (_: AuthenticationError.MissingSession) {
            }
            assertNull(session.user)
            assertNull(persistence.data)
            assertEquals(listOf<String?>("2024-01-01"), versions)
        }
    }

    @Test
    fun authErrorInTheOlderShapeKeepsItsMessage() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        transport.respond("""{"code":403,"error_code":"otp_expired","msg":"Token has expired or is invalid"}""", status = 403)
        try {
            session.verifyCode(email = "test@example.com", code = "123456")
            fail("Expected the refusal")
        } catch (error: AuthenticationError.Server) {
            assertEquals("Token has expired or is invalid", error.serverMessage)
        }
    }

    @Test
    fun refreshBegunBeforeTheAccountChangedDoesNotHoldUpLaterOnes() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        val id = UUID.randomUUID()
        authorize(session, transport, id = id, expired = true)
        val started = CompletableDeferred<Unit>()
        val complete = CompletableDeferred<HttpResponse>()
        transport.handler = {
            started.complete(Unit)
            complete.await()
        }
        val stale = async { runCatching { session.accessToken() } }
        started.await()
        // The account changes while the refresh is under way, without the session being cleared first.
        session.showSignIn()
        authorize(session, transport, id = id, expired = true)
        val fresh = json(
            AuthSession.serializer(),
            AuthSession(
                accessToken = "fresh", tokenType = "bearer", expiresIn = 3600, expiresAt = now.epochSecond + 3600, refreshToken = "next",
                user = AuthUser(id = id, email = "test@example.com", isAnonymous = false),
            ),
        )
        complete.complete(jsonResponse(200, fresh))
        val failure = stale.await().exceptionOrNull()
        assertTrue("Expected the stale refresh to give way, got $failure", failure is CancellationException)
        transport.respond(fresh)
        val token = session.accessToken()
        assertEquals("fresh", token)
    }

    @Test
    fun signingOutWithAnExpiredTokenRefreshesItForTheServer() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        val id = UUID.randomUUID()
        authorize(session, transport, id = id, expired = true)
        val fresh = json(
            AuthSession.serializer(),
            AuthSession(
                accessToken = "fresh", tokenType = "bearer", expiresIn = 3600, expiresAt = now.epochSecond + 3600, refreshToken = "next",
                user = AuthUser(id = id, email = "test@example.com", isAnonymous = false),
            ),
        )
        val calls = mutableListOf<String>()
        transport.handler = { request ->
            val query = Url.parse(request.url)?.query
            calls += "${request.path}?${query ?: ""} ${request.header("Authorization") ?: "-"}"
            if (query == "grant_type=refresh_token") jsonResponse(200, fresh) else jsonResponse(204)
        }
        session.signOut()
        assertNull(session.user)
        assertEquals(listOf("/auth/v1/token?grant_type=refresh_token -", "/auth/v1/logout?scope=local Bearer fresh"), calls)
    }

    @Test
    fun aSessionWhoseExpiryAnIntCannotHoldIsKept() = runTest {
        val transport = RecordingTransport()
        val session = sessionStore(transport)
        val user = AuthUser(id = UUID.randomUUID(), email = "test@example.com", isAnonymous = false)
        transport.respond(
            json(
                AuthSession.serializer(),
                AuthSession(accessToken = "access", tokenType = "bearer", expiresIn = Long.MAX_VALUE, expiresAt = null, refreshToken = "refresh", user = user),
            ),
        )
        session.verifyCode(email = "test@example.com", code = "123456")
        assertEquals(user.id, session.user?.id)
        val token = session.accessToken()
        assertEquals("access", token)
    }
}
