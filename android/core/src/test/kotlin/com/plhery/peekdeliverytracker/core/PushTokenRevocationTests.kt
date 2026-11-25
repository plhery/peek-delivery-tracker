package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import java.io.IOException
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * A token the account leaving could not take off the service waits on the phone (`sdt.notificationsRevocationPending.v1`),
 * as on the iPhone: Sign out pressed while offline, or a session that ended without Sign out. The next account takes it
 * over and off before the phone registers again, and the same account signing in again takes it off as its own.
 * Firebase, offline, keeps handing the same token out; F-Droid's build forgets its installation token with the account
 * and makes a new one, so the one the service holds would otherwise stay the account's, reaching nobody.
 */
class PushTokenRevocationTests {
    private val configuration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://push.test", supabaseUrl = "https://push.test", supabasePublishableKey = "public",
        googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "push.test",
    )
    private val now = Instant.parse("2026-11-20T15:00:00Z")
    private val alice = UUID.fromString("5a000000-0000-4000-a000-0000000000a1")
    private val bob = UUID.fromString("5a000000-0000-4000-a000-0000000000b2")
    private val bearers = mapOf("access-a" to alice, "access-b" to bob)

    private var online = true

    /** The service's push devices: each token's account, the one that registered it last. */
    private val rows = mutableMapOf<String, UUID>()
    private val deviceCalls = mutableListOf<String>()
    private val defaults = MemoryDefaults()

    /** Firebase as it behaves offline: a deletion that can't reach Google's servers keeps the token it hands out. */
    private inner class Firebase : PushNotifications {
        private var minted = 0
        private var stored: String? = null
        override var currentDeviceToken: String? = null
        override suspend fun authorizationStatus() = NotificationAuthorizationStatus.Authorized
        override suspend fun requestAuthorization() = true
        override fun registerForRemoteNotifications() {
            currentDeviceToken = stored ?: "synthetic-fcm-token-${++minted}".also { stored = it }
        }
        override fun unregisterForRemoteNotifications() {
            if (online) stored = null
        }
        override fun clearDeviceToken() {
            currentDeviceToken = null
        }
    }

    /**
     * F-Droid's address (`PushRoute`): an installation token kept between launches, which the service hears nothing of
     * when the phone forgets it, and a new one after.
     */
    private inner class Inbox : PushNotifications {
        private var minted = 0
        private var installation: String? = null
        override var currentDeviceToken: String? = null
        override val savedDeviceToken: String? get() = currentDeviceToken ?: installation
        override suspend fun authorizationStatus() = NotificationAuthorizationStatus.Authorized
        override suspend fun requestAuthorization() = true
        override fun registerForRemoteNotifications() {
            currentDeviceToken = installation ?: "inbox synthetic-installation-${++minted}".also { installation = it }
        }
        override fun unregisterForRemoteNotifications() {
            installation = null
        }
        override fun clearDeviceToken() {
            currentDeviceToken = null
        }
    }

    private fun auth(user: UUID) = AuthSession(
        accessToken = if (user == alice) "access-a" else "access-b", tokenType = "bearer", expiresIn = 3600, expiresAt = now.epochSecond + 3600,
        refreshToken = "refresh-$user", user = AuthUser(id = user, email = "${if (user == alice) "a" else "b"}@example.com", isAnonymous = false),
    )

    private fun answer(request: HttpRequest): HttpResponse {
        if (!online) throw IOException("Unable to resolve host \"push.test\"")
        return when {
            request.path == "/api/push/devices" -> {
                val token = Json.parseToJsonElement(request.body!!.decodeToString()).jsonObject["token"]!!.jsonPrimitive.content
                val user = bearers.getValue(request.header("Authorization")!!.removePrefix("Bearer "))
                deviceCalls += "${request.method} as ${if (user == alice) "A" else "B"}"
                if (request.method == "POST") rows[token] = user
                if (request.method == "DELETE" && rows[token] == user) rows.remove(token)
                jsonResponse(200, """{"ok":true,"testSent":false}""")
            }
            request.path == "/api/packages" && request.method == "GET" ->
                jsonResponse(200, JsonEncoder.deliveryTracker.encode(PackageListResponse.serializer(), PackageListResponse(emptyList())).decodeToString())
            else -> jsonResponse(200, "{}")
        }
    }

    private class Phone(val store: ParcelStore, val session: SessionStore, val transport: RecordingTransport, val push: PushNotifications)

    private fun TestScope.phone(push: PushNotifications): Phone {
        val transport = RecordingTransport()
        val session = testSessionStore(configuration, backgroundScope, transport = transport, now = { now })
        return Phone(testParcelStore(configuration, session, transport, backgroundScope, defaults, notifications = push, now = { now }), session, transport, push)
    }

    /** A signed in with notifications on: the token the service holds for A, as the service names it. */
    private suspend fun TestScope.registered(phone: Phone): String {
        signIn(phone, alice)
        phone.store.setDeliveryLiveActivitiesEnabled(false)
        foreground(phone)
        val token = PushAddress.of(phone.push.currentDeviceToken!!).token
        assertEquals(alice, rows[token])
        return token
    }

    /** A signed in with notifications on, then Sign out, online or not. */
    private suspend fun TestScope.signedOut(online: Boolean): Pair<Phone, String> {
        val phone = phone(Firebase())
        val token = registered(phone)
        this@PushTokenRevocationTests.online = online
        phone.store.signOut()
        runCurrent()
        this@PushTokenRevocationTests.online = true
        return phone to token
    }

    /** A signed in with F-Droid's address, then its session over without Sign out, as when it expired. */
    private suspend fun TestScope.sessionEnded(): Pair<Phone, String> {
        val phone = phone(Inbox())
        val token = registered(phone)
        phone.session.forceSignOut()
        runCurrent()
        assertEquals("Nothing reached the service", listOf("POST as A"), deviceCalls)
        return phone to token
    }

    private suspend fun TestScope.signIn(phone: Phone, user: UUID) {
        phone.transport.handler = { jsonResponse(200, JsonEncoder.deliveryTracker.encode(AuthSession.serializer(), auth(user)).decodeToString()) }
        phone.session.verifyCode(email = "${if (user == alice) "a" else "b"}@example.com", code = "123456")
        phone.transport.handler = { answer(it) }
        runCurrent()
    }

    /** The app back in front: its notifications read, then the token handed on as the phone gives it. */
    private suspend fun TestScope.foreground(phone: Phone) {
        phone.store.refreshNotificationState()
        phone.push.currentDeviceToken?.let { phone.store.forwardNativePushToken(it, AppLanguage.En) }
        runCurrent()
    }

    @Test
    fun theNextAccountTakesATokenLeftOnTheServiceOverAndOffBeforeThePhoneRegistersAgain() = runTest {
        val (phone, token) = signedOut(online = false)
        assertEquals("Kept for later", token, defaults.string(forKey = NotificationDeviceKeys.REVOCATION))

        signIn(phone, bob)
        foreground(phone)
        assertEquals(listOf("POST as A", "POST as B", "DELETE as B"), deviceCalls)
        assertNull("The token no longer brings A's notifications", rows[token])
        assertNull(defaults.string(forKey = NotificationDeviceKeys.REVOCATION))
        assertFalse("B is asked before its notifications come on", phone.store.notificationsEnabledOnDevice)
    }

    @Test
    fun aTokenLeftOnTheServiceWaitsWithoutAnAccountAndWhileTheServiceCantBeReached() = runTest {
        val (phone, token) = signedOut(online = false)
        foreground(phone)
        assertEquals(token, defaults.string(forKey = NotificationDeviceKeys.REVOCATION))

        online = false
        signIn(phone, bob)
        online = false
        foreground(phone)
        assertEquals("No registration while the token waits", token, defaults.string(forKey = NotificationDeviceKeys.REVOCATION))
        online = true
        foreground(phone)
        assertNull(rows[token])
        assertNull(defaults.string(forKey = NotificationDeviceKeys.REVOCATION))
    }

    @Test
    fun theSameAccountSigningInAgainTakesTheTokenOffAsItsOwnThenHasItBack() = runTest {
        val (phone, token) = signedOut(online = false)
        signIn(phone, alice)
        foreground(phone)
        assertNull(defaults.string(forKey = NotificationDeviceKeys.REVOCATION))
        assertEquals(alice, rows[token])
        assertEquals(listOf("POST as A", "DELETE as A", "POST as A"), deviceCalls)
    }

    @Test
    fun anOnlineSignOutTakesTheTokenOffAndKeepsNothing() = runTest {
        val (_, token) = signedOut(online = true)
        assertNull(rows[token])
        assertNull(defaults.string(forKey = NotificationDeviceKeys.REVOCATION))
        assertEquals(listOf("POST as A", "DELETE as A"), deviceCalls)
    }

    @Test
    fun aSessionOverWithoutSignOutLeavesItsInstallationTokenForTheNextAccountToTakeOff() = runTest {
        val (phone, token) = sessionEnded()
        assertEquals("inbox $token", defaults.string(forKey = NotificationDeviceKeys.REVOCATION))

        signIn(phone, bob)
        foreground(phone)
        assertEquals(listOf("POST as A", "POST as B", "DELETE as B"), deviceCalls)
        assertEquals("A's notifications reach no forgotten address", emptyMap<String, UUID>(), rows)
        assertNull(defaults.string(forKey = NotificationDeviceKeys.REVOCATION))
    }

    @Test
    fun theSameAccountBackAfterItsSessionEndedTakesItsForgottenTokenOffBeforeRegisteringTheNewOne() = runTest {
        val (phone, token) = sessionEnded()
        signIn(phone, alice)
        foreground(phone)
        val renewed = PushAddress.of(phone.push.currentDeviceToken!!).token
        assertEquals(listOf("POST as A", "DELETE as A", "POST as A"), deviceCalls)
        assertEquals("One row, the phone's address now", mapOf(renewed to alice), rows)
        assertFalse(renewed == token)
        assertNull(defaults.string(forKey = NotificationDeviceKeys.REVOCATION))
    }

    @Test
    fun turningNotificationsOffTakesOffTheKeptAddressInALaunchThatNeverAskedForIt() = runTest {
        val phone = phone(Inbox())
        val token = registered(phone)
        // A new launch, the permission off in Android's settings meanwhile: nothing asks for the address.
        phone.push.clearDeviceToken()
        phone.store.disableNotifications()
        assertNull(rows[token])
        assertEquals(listOf("POST as A", "DELETE as A"), deviceCalls)
    }

    @Test
    fun signOutTakesOffTheKeptAddressInALaunchThatNeverAskedForIt() = runTest {
        val phone = phone(Inbox())
        val token = registered(phone)
        phone.push.clearDeviceToken()
        phone.store.signOut()
        runCurrent()
        assertNull(rows[token])
        assertEquals(listOf("POST as A", "DELETE as A"), deviceCalls)
        assertNull(defaults.string(forKey = NotificationDeviceKeys.REVOCATION))
    }
}
