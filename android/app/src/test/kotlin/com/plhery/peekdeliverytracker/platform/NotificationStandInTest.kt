package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthSession
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.JsonEncoder
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import java.security.Security
import java.time.Duration
import java.time.Instant
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

/**
 * The notifications' stand-in until M6 ([NoPushNotifications]), through the app's own stores: its question answers what
 * Android already allows, so a phone that allows them waits for the token as the iPhone does and says it never came,
 * the demo turns them on, and only a refusal keeps the invitation away. The server is answered on the client itself
 * (synthetic hosts), so nothing leaves the machine.
 */
@RunWith(AndroidJUnit4::class)
class NotificationStandInTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var previousCatalog: CarrierCatalog
    private var container: AppContainer? = null

    private val process = object : LifecycleOwner {
        override val lifecycle = LifecycleRegistry(this)
    }

    private val user = AuthUser(UUID(0, 7), email = "reader@example.com", isAnonymous = false)

    private val answers = Interceptor { chain ->
        val request = chain.request()
        val (code, body) = when (request.url.encodedPath) {
            "/auth/v1/verify" -> 200 to JsonEncoder.deliveryTracker.encode(
                AuthSession.serializer(),
                AuthSession(
                    accessToken = "synthetic-access", tokenType = "bearer", expiresIn = 3600,
                    expiresAt = Instant.now().epochSecond + 3600, refreshToken = "synthetic-refresh", user = user,
                ),
            ).decodeToString()
            else -> 404 to "{}"
        }
        Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(code).message("").body(body.toResponseBody()).build()
    }

    @Before
    fun start() {
        previousCatalog = CarrierCatalog.shared
        process.lifecycle.currentState = Lifecycle.State.RESUMED
        Security.addProvider(FakeAndroidKeyStore())
    }

    @After
    fun stop() {
        container?.scope?.cancel()
        CarrierCatalog.install(previousCatalog)
        KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.auth").delete()
        Security.removeProvider("AndroidKeyStore")
    }

    private fun container(): AppContainer {
        val server = "https://peek.example.test"
        val configuration = AppConfiguration(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = server, supabaseUrl = server,
            supabasePublishableKey = "synthetic-publishable-key", googleAuthEnabled = false, appleAuthEnabled = false,
            emailOtpEnabled = true, appGroupIdentifier = "group.test.peek",
        )
        val http = PeekHttp.client(MemoryCookieJar()).newBuilder().addInterceptor(answers).build()
        return AppContainer(context, configuration, http, scope = MainScope()).also { container = it }
    }

    private fun idleUntil(what: String, done: () -> Boolean) {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(30)
        while (true) {
            shadowOf(Looper.getMainLooper()).idle()
            if (done()) return
            if (System.nanoTime() > deadline) fail("Timed out waiting for $what")
            Thread.sleep(10)
        }
    }

    private fun allow() = shadowOf(context as Application).grantPermissions(Manifest.permission.POST_NOTIFICATIONS)

    private fun turnOff() = shadowOf(context.getSystemService(NotificationManager::class.java)).setNotificationsEnabled(false)

    @Test
    fun itsQuestionAnswersWhatAndroidAlreadyAllows() = runBlocking {
        val notifications = NoPushNotifications(context)
        // Android 13 and later: not granted yet, a question the stand-in can't ask.
        assertEquals(NotificationAuthorizationStatus.NotDetermined, notifications.authorizationStatus())
        assertFalse(notifications.requestAuthorization())
        allow()
        assertEquals(NotificationAuthorizationStatus.Authorized, notifications.authorizationStatus())
        assertTrue(notifications.requestAuthorization())
        turnOff()
        assertEquals(NotificationAuthorizationStatus.Denied, notifications.authorizationStatus())
        assertFalse(notifications.requestAuthorization())
    }

    @Test
    @Config(sdk = [31])
    fun onAndroid12ItsQuestionAnswersWhetherTheAppsNotificationsAreOn() = runBlocking {
        val notifications = NoPushNotifications(context)
        assertTrue(notifications.requestAuthorization())
        turnOff()
        assertFalse(notifications.requestAuthorization())
    }

    @Test
    fun anAccountOnAPhoneThatAllowsThemWaitsForTheTokenAndKeepsTheInvitation() {
        val app = container()
        app.start(process.lifecycle)
        idleUntil("the session") { app.session.state != AuthenticationState.Loading }
        var signedIn = false
        app.scope.launch {
            app.session.verifyCode(email = "reader@example.com", code = "123456")
            signedIn = true
        }
        idleUntil("the sign-in") { signedIn && app.session.state is AuthenticationState.SignedIn }
        allow()

        var outcome: Result<Boolean>? = null
        app.scope.launch { outcome = runCatching { app.parcels.enableNotifications(AppLanguage.En) } }
        // The iPhone's wait for the token: 80 tenths of a second, on the main thread's clock.
        repeat(100) {
            if (outcome == null) shadowOf(Looper.getMainLooper()).idleFor(Duration.ofMillis(100))
        }
        val error = outcome?.exceptionOrNull()
        assertTrue("$outcome", error is DeliveryApiError.PushTokenUnavailable)
        assertEquals(app.localizer.text("native.apnsTokenError"), app.localizer.errorMessage(error!!))
        assertFalse(app.parcels.notificationsEnabledOnDevice)
        // Not a refusal: M6's invitation can still come.
        assertFalse(app.parcels.notificationInvitationDismissed)
    }

    @Test
    fun theDemoTurnsThemOnWithThePermissionAlone() {
        PreferencesDefaults.standard(context).set("demo", forKey = "sdt.native.experience.v1")
        val app = container()
        app.start(process.lifecycle)
        idleUntil("the demo") { app.session.state == AuthenticationState.Demo }

        // Not allowed yet: the question is answered no.
        var refused: Result<Boolean>? = null
        app.scope.launch { refused = runCatching { app.parcels.enableNotifications(AppLanguage.En) } }
        idleUntil("the refusal") { refused != null }
        assertTrue("$refused", refused?.exceptionOrNull() is DeliveryApiError.NotificationsDenied)
        assertFalse(app.parcels.notificationsEnabledOnDevice)

        allow()
        var outcome: Result<Boolean>? = null
        app.scope.launch { outcome = runCatching { app.parcels.enableNotifications(AppLanguage.En) } }
        idleUntil("the demo's notifications") { outcome != null }
        assertEquals(true, outcome?.getOrNull())
        assertTrue(app.parcels.notificationsEnabledOnDevice)
    }
}
