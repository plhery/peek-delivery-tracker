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
 * Sign out pressed while offline: the token the account leaving could not take off the service waits on the phone
 * (`sdt.notificationsRevocationPending.v1`), as on the iPhone. Firebase, offline too, keeps handing the same token out,
 * so the next account takes it over and off before the phone registers again, and the same account signing in again has
 * it back as its own.
 */
class SignOutOfflineTests {
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

    private class Phone(val store: ParcelStore, val session: SessionStore, val transport: RecordingTransport, val firebase: PushNotifications)

    /** A signed in with notifications on, then Sign out, online or not. */
    private suspend fun TestScope.signedOut(online: Boolean): Pair<Phone, String> {
        val transport = RecordingTransport()
        val session = testSessionStore(configuration, backgroundScope, transport = transport, now = { now })
        val firebase = Firebase()
        val phone = Phone(
            testParcelStore(configuration, session, transport, backgroundScope, defaults, notifications = firebase, now = { now }),
            session, transport, firebase,
        )
        signIn(phone, alice)
        phone.store.setDeliveryLiveActivitiesEnabled(false)
        phone.store.refreshNotificationState()
        val token = phone.firebase.currentDeviceToken!!
        phone.store.forwardNativePushToken(token, AppLanguage.En)
        runCurrent()
        assertEquals(alice, rows[token])
        this@SignOutOfflineTests.online = online
        phone.store.signOut()
        runCurrent()
        this@SignOutOfflineTests.online = true
        return phone to token
    }

    private suspend fun TestScope.signIn(phone: Phone, user: UUID) {
        phone.transport.handler = { jsonResponse(200, JsonEncoder.deliveryTracker.encode(AuthSession.serializer(), auth(user)).decodeToString()) }
        phone.session.verifyCode(email = "${if (user == alice) "a" else "b"}@example.com", code = "123456")
        phone.transport.handler = { answer(it) }
        runCurrent()
    }

    /** The app back in front: its notifications read, then the token handed on as Firebase gives it. */
    private suspend fun TestScope.foreground(phone: Phone) {
        phone.store.refreshNotificationState()
        phone.firebase.currentDeviceToken?.let { phone.store.forwardNativePushToken(it, AppLanguage.En) }
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
    fun theSameAccountSigningInAgainHasTheTokenBackAsItsOwn() = runTest {
        val (phone, token) = signedOut(online = false)
        signIn(phone, alice)
        foreground(phone)
        assertNull(defaults.string(forKey = NotificationDeviceKeys.REVOCATION))
        assertEquals(alice, rows[token])
        assertEquals(listOf("POST as A", "POST as A"), deviceCalls)
    }

    @Test
    fun anOnlineSignOutTakesTheTokenOffAndKeepsNothing() = runTest {
        val (_, token) = signedOut(online = true)
        assertNull(rows[token])
        assertNull(defaults.string(forKey = NotificationDeviceKeys.REVOCATION))
        assertEquals(listOf("POST as A", "DELETE as A"), deviceCalls)
    }
}
