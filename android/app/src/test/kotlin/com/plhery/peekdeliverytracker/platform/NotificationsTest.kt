package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.app.Activity
import android.app.Application
import android.app.NotificationManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.PeekApplication
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
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

/** A window of the app as far as notifications go: it registers POST_NOTIFICATIONS' question, as MainActivity does. */
class AskingWindow : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as PeekApplication).notificationPermission.attach(this)
    }
}

/**
 * The phone's notifications (C-P01, C-P03, C-ST21): POST_NOTIFICATIONS' states as the store reads them (not asked, may
 * ask again, denied once the system no longer asks, Android 12's setting alone), the question asked from the window on
 * screen and ended unanswered when that window closes for good or the question can't be shown, and through the app's
 * own stores the token that never comes until Firebase, the demo, the channels and a friend's notification taken away. The server is answered on the client itself (synthetic hosts), so nothing leaves
 * the machine.
 */
@RunWith(AndroidJUnit4::class)
class NotificationsTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val app = context as PeekApplication
    private lateinit var previousCatalog: CarrierCatalog
    private var container: AppContainer? = null
    private val windows = mutableListOf<ActivityController<*>>()

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
        windows.forEach { it.close() }
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

    private fun rationale(shown: Boolean) =
        shadowOf(context.packageManager).setShouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS, shown)

    private fun window(): ActivityController<AskingWindow> = Robolectric.buildActivity(AskingWindow::class.java).setup().also { windows += it }

    private val notifications get() = AndroidPushNotifications(context, app.notificationPermission)

    private fun status() = runBlocking { notifications.authorizationStatus() }

    /**
     * Answers the question `window` showed as the system's dialog does, through the window's result: `granted` or
     * refused.
     */
    private fun answer(window: ActivityController<AskingWindow>, granted: Boolean) {
        val started = requireNotNull(shadowOf(window.get()).nextStartedActivityForResult) { "Nothing was asked" }
        if (granted) allow()
        val result = Intent()
            .putExtra(REQUEST_PERMISSIONS_NAMES, arrayOf(Manifest.permission.POST_NOTIFICATIONS))
            .putExtra(REQUEST_PERMISSIONS_RESULTS, intArrayOf(if (granted) PackageManager.PERMISSION_GRANTED else PackageManager.PERMISSION_DENIED))
        shadowOf(window.get()).receiveResult(started.intent, Activity.RESULT_OK, result)
        shadowOf(Looper.getMainLooper()).idle()
    }

    /**
     * Asks from `window` and answers as the user would: `granted`, and whether Android would still ask after it
     * (`rationale`); a refusal without it the first time is the question dismissed without an answer.
     */
    private fun ask(window: ActivityController<AskingWindow>, granted: Boolean, rationale: Boolean = false): Boolean {
        var answer: Result<Boolean>? = null
        MainScope().launch { answer = runCatching { notifications.requestAuthorization() } }
        shadowOf(Looper.getMainLooper()).idle()
        val request = requireNotNull(shadowOf(window.get()).lastRequestedPermission) { "Nothing was asked" }
        assertEquals(listOf(Manifest.permission.POST_NOTIFICATIONS), request.requestedPermissions.toList())
        assertNull("Not answered before the user does", answer)
        rationale(rationale)
        answer(window, granted)
        return requireNotNull(answer).getOrThrow()
    }

    @Test
    @Config(sdk = [QUESTION_SDK])
    fun notAskedThenAskedAgainAfterARefusalThenDeniedOnceAndroidNoLongerAsks() {
        val window = window()
        assertEquals("Not asked", NotificationAuthorizationStatus.NotDetermined, status())
        assertFalse(notifications.answeredBefore)

        assertFalse(ask(window, granted = false, rationale = true))
        assertEquals("May ask again", NotificationAuthorizationStatus.NotDetermined, status())
        assertTrue(notifications.answeredBefore)

        assertFalse(ask(window, granted = false, rationale = false))
        assertEquals("Denied: only Settings turns them on", NotificationAuthorizationStatus.Denied, status())
    }

    @Test
    @Config(sdk = [QUESTION_SDK])
    fun grantedTheyAreAuthorizedAndNotAskedAgain() {
        val window = window()
        assertTrue(ask(window, granted = true))
        assertEquals(NotificationAuthorizationStatus.Authorized, status())
        val asked = shadowOf(window.get()).lastRequestedPermission
        assertTrue(runBlocking { notifications.requestAuthorization() })
        assertTrue("Nothing asked again", asked === shadowOf(window.get()).lastRequestedPermission)
        // Granted, with the app's notifications turned off in the phone's settings: Settings' blocked line.
        turnOff()
        assertEquals(NotificationAuthorizationStatus.Denied, status())
        assertFalse(runBlocking { notifications.requestAuthorization() })
    }

    @Test
    @Config(sdk = [QUESTION_SDK])
    fun aQuestionDismissedWithoutAnAnswerIsStillToBeAsked() {
        val window = window()
        assertFalse(ask(window, granted = false, rationale = false))
        assertEquals(NotificationAuthorizationStatus.NotDetermined, status())
        assertFalse(notifications.answeredBefore)
    }

    @Test
    @Config(sdk = [QUESTION_SDK])
    fun theAnswerComesToTheWindowMadeAgain() {
        val window = window()
        var answer: Result<Boolean>? = null
        MainScope().launch { answer = runCatching { notifications.requestAuthorization() } }
        shadowOf(Looper.getMainLooper()).idle()
        assertNotNull(shadowOf(window.get()).lastRequestedPermission)
        // Turned while the question shows: the answer comes to the window made again.
        window.recreate()
        assertNull(answer)
        answer(window, granted = true)
        assertEquals(true, answer?.getOrNull())
    }

    /** The window that asked closed for good before the answer: the question ends unanswered, and the next is asked. */
    @Test
    @Config(sdk = [QUESTION_SDK])
    fun aWindowClosedBeforeTheAnswerEndsTheQuestionUnanswered() {
        val first = window()
        var answer: Result<Boolean>? = null
        MainScope().launch { answer = runCatching { notifications.requestAuthorization() } }
        shadowOf(Looper.getMainLooper()).idle()
        assertNotNull(shadowOf(first.get()).lastRequestedPermission)
        first.get().finish()
        first.pause().stop().destroy()
        shadowOf(Looper.getMainLooper()).idle()
        assertEquals(false, answer?.getOrNull())
        assertFalse("No answer to count", notifications.answeredBefore)
        assertTrue(ask(window(), granted = true))
    }

    /** The question that couldn't be shown fails, and leaves the next one to be asked. */
    @Test
    @Config(sdk = [QUESTION_SDK])
    fun aQuestionThatCouldNotBeShownLeavesTheNextToBeAsked() {
        val window = window()
        var failed: Result<Boolean>? = null
        ActivityCompat.setPermissionCompatDelegate(
            object : ActivityCompat.PermissionCompatDelegate {
                override fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int): Boolean =
                    throw ActivityNotFoundException("No screen to ask on")

                override fun onActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?): Boolean = false
            },
        )
        try {
            MainScope().launch { failed = runCatching { notifications.requestAuthorization() } }
            shadowOf(Looper.getMainLooper()).idle()
        } finally {
            ActivityCompat.setPermissionCompatDelegate(null)
        }
        assertTrue("$failed", failed?.exceptionOrNull() is ActivityNotFoundException)
        assertTrue(ask(window, granted = true))
    }

    @Test
    @Config(sdk = [QUESTION_SDK])
    fun withoutAWindowOnScreenNothingCanBeAsked() {
        val window = window()
        window.pause().stop()
        var answer: Result<Boolean>? = null
        MainScope().launch { answer = runCatching { notifications.requestAuthorization() } }
        shadowOf(Looper.getMainLooper()).idle()
        assertTrue("$answer", answer?.exceptionOrNull() is IllegalStateException)
        assertNull(shadowOf(window.get()).lastRequestedPermission)
    }

    @Test
    @Config(sdk = [31])
    fun onAndroid12TheAppsSettingAloneAndNothingAsked() {
        val window = window()
        assertEquals(NotificationAuthorizationStatus.Authorized, status())
        assertTrue(runBlocking { notifications.requestAuthorization() })
        assertFalse(notifications.answeredBefore)
        turnOff()
        assertEquals(NotificationAuthorizationStatus.Denied, status())
        assertFalse(runBlocking { notifications.requestAuthorization() })
        assertNull(shadowOf(window.get()).lastRequestedPermission)
    }

    /**
     * Until Firebase Cloud Messaging is set up the app's pushes aren't available, the app's own stores included: nothing
     * offers them, asks for the permission for them or tunes them ([AndroidPushNotifications.available]).
     */
    @Test
    fun theAppsPushesAreNotAvailableUntilFirebase() {
        assertFalse(notifications.available)
        assertFalse(container().notifications.available)
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
        // Not a refusal: the invitation can still come.
        assertFalse(app.parcels.notificationInvitationDismissed)
    }

    @Test
    fun theDemoTurnsThemOnWithThePermission() {
        PreferencesDefaults.standard(context).set("demo", forKey = "sdt.native.experience.v1")
        val app = container()
        app.start(process.lifecycle)
        idleUntil("the demo") { app.session.state == AuthenticationState.Demo }

        // No window to ask from: it fails, and nothing is turned on.
        var failed: Result<Boolean>? = null
        app.scope.launch { failed = runCatching { app.parcels.enableNotifications(AppLanguage.En) } }
        idleUntil("the failure") { failed != null }
        assertTrue("$failed", failed?.exceptionOrNull() is IllegalStateException)
        assertFalse(app.parcels.notificationsEnabledOnDevice)

        allow()
        var outcome: Result<Boolean>? = null
        app.scope.launch { outcome = runCatching { app.parcels.enableNotifications(AppLanguage.En) } }
        idleUntil("the demo's notifications") { outcome != null }
        assertEquals(true, outcome?.getOrNull())
        assertTrue(app.parcels.notificationsEnabledOnDevice)
    }

    /** A-W01, C-W20: the channels are made with the stores, in the app's language, and renamed when it changes. */
    @Test
    fun theChannelsAreMadeAtLaunchAndRenamedWithTheAppsLanguage() {
        val manager = context.getSystemService(NotificationManager::class.java)
        assertNull(manager.getNotificationChannel(AppNotifications.PARCELS))
        val app = container()
        app.start(process.lifecycle)
        app.localizer.language = AppLanguage.En
        idleUntil("the session") { app.session.state != AuthenticationState.Loading }
        assertEquals("Deliveries", manager.getNotificationChannel(AppNotifications.PARCELS).name)
        assertEquals("Friends", manager.getNotificationChannel(AppNotifications.FRIENDS).name)

        app.localizer.language = AppLanguage.De
        idleUntil("the German channels") { manager.getNotificationChannel(AppNotifications.PARCELS).name == "Sendungen" }
        assertEquals("Freunde", manager.getNotificationChannel(AppNotifications.FRIENDS).name)
        assertEquals(NotificationManager.IMPORTANCE_HIGH, manager.getNotificationChannel(AppNotifications.FRIENDS).importance)
    }

    /** C-W17: acknowledging a friend's notice takes their notification away, and only theirs. */
    @Test
    fun aFriendAcknowledgedTakesTheirNotificationAway() {
        allow()
        val app = container()
        app.start(process.lifecycle)
        idleUntil("the session") { app.session.state != AuthenticationState.Loading }
        val friend = UUID(0, 42)
        val parcel = UUID(0, 43)
        app.posted.post(mapOf("title" to "Sam", "body" to "Sam accepted your invitation", "kind" to "friend_accepted", "friend_id" to "$friend"))
        app.posted.post(mapOf("title" to "Books", "body" to "Out for delivery", "parcel_id" to "$parcel"))
        val shade = shadowOf(context.getSystemService(NotificationManager::class.java))
        assertEquals("The friend's, its group's summary and the parcel's", 3, shade.size())

        var done = false
        app.scope.launch {
            app.friendsActivity.acknowledge(friend)
            done = true
        }
        idleUntil("the acknowledgement") { done }
        assertNull(shade.getNotification("friend-$friend", AppNotifications.ID))
        assertNotNull(shade.getNotification("$parcel", AppNotifications.ID))
        assertEquals("The summary gone with the last friend's", 1, shade.size())
    }
}

/**
 * Android 13, where the question goes to the system's dialog: Android 17 answers a question it can't ask itself, at
 * once, and its stand-in here can't tell.
 */
private const val QUESTION_SDK = 33

/** `PackageManager.EXTRA_REQUEST_PERMISSIONS_NAMES` and `_RESULTS`, the dialog's answer, hidden from apps. */
private const val REQUEST_PERMISSIONS_NAMES = "android.content.pm.extra.REQUEST_PERMISSIONS_NAMES"
private const val REQUEST_PERMISSIONS_RESULTS = "android.content.pm.extra.REQUEST_PERMISSIONS_RESULTS"
