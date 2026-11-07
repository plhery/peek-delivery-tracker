package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.app.Activity
import android.app.Application
import android.app.Notification
import android.app.NotificationManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.RemoteMessage
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthSession
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.JsonEncoder
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import java.io.IOException
import java.security.Security
import java.util.Collections
import java.time.Duration
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
import okio.Buffer
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
 * screen and ended unanswered when that window closes for good or the question can't be shown, Firebase's token kept
 * and handed to the stores, a push's data posted, and through the app's own stores the token that never comes, the
 * demo, the channels and a friend's notification taken away. The server is answered on the client itself (synthetic
 * hosts) and Firebase by a stand-in ([Tokens]), so nothing leaves the machine.
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

    /**
     * Firebase's tokens as a test gives them: each question answered with [next] on the main thread, or with [failure],
     * or never if both are null.
     */
    private class Tokens(var next: String? = null, var failure: Throwable? = null) : PushTokens {
        val asked = mutableListOf<(Result<String>) -> Unit>()
        var deletions = 0

        override fun token(answer: (Result<String>) -> Unit) {
            asked += answer
            next?.let { token -> Handler(Looper.getMainLooper()).post { answer(Result.success(token)) } }
            failure?.let { error -> Handler(Looper.getMainLooper()).post { answer(Result.failure(error)) } }
        }

        override fun delete() {
            deletions++
        }
    }

    /** What the account's devices were sent (`/api/push/devices`): each request's method and body. */
    private val devices: MutableList<String> = Collections.synchronizedList(mutableListOf())

    private val answers = Interceptor { chain ->
        val request = chain.request()
        val (code, body) = when (request.url.encodedPath) {
            "/auth/v1/verify" -> 200 to JsonEncoder.deliveryTracker.encode(
                AuthSession.serializer(),
                AuthSession(
                    accessToken = "synthetic-access", tokenType = "bearer", expiresIn = 3600,
                    expiresAt = AppClock.now().epochSecond + 3600, refreshToken = "synthetic-refresh", user = user,
                ),
            ).decodeToString()
            "/api/push/devices" -> {
                devices += "${request.method} ${request.body?.let { body -> Buffer().also(body::writeTo).readUtf8() }}"
                200 to (if (request.method == "POST") """{"ok":true,"testSent":true}""" else """{"ok":true}""")
            }
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
        FirebaseApp.getApps(context).forEach(FirebaseApp::delete)
    }

    private fun container(tokens: PushTokens? = null): AppContainer {
        val server = "https://peek.example.test"
        val configuration = AppConfiguration(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = server, supabaseUrl = server,
            supabasePublishableKey = "synthetic-publishable-key", googleAuthEnabled = false, appleAuthEnabled = false,
            emailOtpEnabled = true, appGroupIdentifier = "group.test.peek",
        )
        val http = PeekHttp.client(MemoryCookieJar()).newBuilder().addInterceptor(answers).build()
        return AppContainer(context, configuration, http, scope = MainScope(), pushTokens = tokens).also { container = it }
    }

    /** Signs `app` in with the synthetic account's code, the stores started here unless they are already. */
    private fun signIn(app: AppContainer, start: Boolean = true) {
        if (start) app.start(process.lifecycle)
        idleUntil("the session") { app.session.state != AuthenticationState.Loading }
        var signedIn = false
        app.scope.launch {
            app.session.verifyCode(email = "reader@example.com", code = "123456")
            signedIn = true
        }
        idleUntil("the sign-in") { signedIn && app.session.state is AuthenticationState.SignedIn }
    }

    /**
     * Runs the main thread's work, and lets the background threads answer, until `done`; with `ticking`, the main
     * thread's clock moves on too, a tenth of a second a turn, for the store's wait for the token.
     */
    private fun idleUntil(what: String, ticking: Boolean = false, done: () -> Boolean) {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(30)
        while (true) {
            if (ticking) shadowOf(Looper.getMainLooper()).idleFor(Duration.ofMillis(100)) else shadowOf(Looper.getMainLooper()).idle()
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
     * (`rationale`); a refusal without it the first time is the question dismissed without an answer. The question
     * [shown] takes the window's focus while it shows, as the system's dialog does; not shown, Android answers alone.
     */
    private fun ask(window: ActivityController<AskingWindow>, granted: Boolean, rationale: Boolean = false, shown: Boolean = true): Boolean {
        var answer: Result<Boolean>? = null
        MainScope().launch { answer = runCatching { notifications.requestAuthorization() } }
        shadowOf(Looper.getMainLooper()).idle()
        val request = requireNotNull(shadowOf(window.get()).lastRequestedPermission) { "Nothing was asked" }
        assertEquals(listOf(Manifest.permission.POST_NOTIFICATIONS), request.requestedPermissions.toList())
        assertNull("Not answered before the user does", answer)
        if (shown) window.windowFocusChanged(false)
        rationale(rationale)
        answer(window, granted)
        if (shown) window.windowFocusChanged(true)
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
        // The window has the focus the question takes.
        window.windowFocusChanged(true)
        assertFalse(ask(window, granted = false, rationale = false))
        assertEquals(NotificationAuthorizationStatus.NotDetermined, status())
        assertFalse(notifications.answeredBefore)
    }

    /**
     * Refused for good before this phone's app ever asked, on the phone it was set up from, whose permissions came with
     * it: Android answers without showing the question, which counts, so Settings is offered rather than a question
     * that never shows.
     */
    @Test
    @Config(sdk = [QUESTION_SDK])
    fun aRefusalAndroidGivesWithoutShowingTheQuestionIsDenied() {
        val window = window()
        window.windowFocusChanged(true)
        assertEquals(NotificationAuthorizationStatus.NotDetermined, status())
        assertFalse(ask(window, granted = false, rationale = false, shown = false))
        assertEquals("Denied: only Settings turns them on", NotificationAuthorizationStatus.Denied, status())
        assertTrue(notifications.answeredBefore)
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
     * In a build without Firebase's four settings the app's pushes aren't available, the app's own stores included:
     * nothing offers them, asks for the permission for them or tunes them ([AndroidPushNotifications.available]); nor on
     * a phone without Google Play services, which Firebase gets its tokens from. With them, on a phone with Play
     * services, the app starts Firebase with them, as google-services.json would, and the pushes are available.
     */
    @Test
    fun theAppsPushesAreAvailableOnlyWithFirebasesSettings() {
        assertNull(FirebaseSettings.of(projectId = "", applicationId = "1:1:android:1", apiKey = "synthetic-key", senderId = "1"))
        assertNull(FirebaseSettings.of(projectId = "peek-synthetic", applicationId = "1:1:android:1", apiKey = " ", senderId = "1"))
        assertNull(FirebasePushTokens.of(context, null))
        assertFalse(notifications.available)
        assertFalse(container().notifications.available)
        assertTrue("Firebase never started without its settings", FirebaseApp.getApps(context).isEmpty())

        val settings = requireNotNull(
            FirebaseSettings.of(projectId = "peek-synthetic", applicationId = "1:1:android:1", apiKey = "synthetic-key", senderId = "1"),
        )
        assertNull("No Play services, no tokens", FirebasePushTokens.of(context, settings, playServices = { false }))
        assertTrue("Firebase never started without Play services", FirebaseApp.getApps(context).isEmpty())
        val tokens = requireNotNull(FirebasePushTokens.of(context, settings, playServices = { true }))
        val options = FirebaseApp.getInstance().options
        assertEquals(listOf("peek-synthetic", "1:1:android:1", "synthetic-key", "1"), listOf(options.projectId, options.applicationId, options.apiKey, options.gcmSenderId))
        assertTrue(AndroidPushNotifications(context, app.notificationPermission, tokens).available)
        assertTrue(container(Tokens()).notifications.available)
    }

    /**
     * AppDelegate's token: Firebase's answer is kept for the launch and handed on, and so is a token Firebase makes anew
     * (`onNewToken`), but not the one already kept again. No answer keeps none. An answer to a question asked before the
     * token was deleted ([AndroidPushNotifications.unregisterForRemoteNotifications]) is dropped.
     */
    @Test
    fun firebasesTokenIsKeptAndHandedOn() {
        val tokens = Tokens()
        val handed = mutableListOf<String>()
        val pushes = AndroidPushNotifications(context, app.notificationPermission, tokens, received = { handed += it })
        pushes.registerForRemoteNotifications()
        assertNull(pushes.currentDeviceToken)
        tokens.asked.single()(Result.success("synthetic-token-1"))
        assertEquals("synthetic-token-1", pushes.currentDeviceToken)
        pushes.tokenRefreshed("synthetic-token-1")
        pushes.tokenRefreshed("synthetic-token-2")
        assertEquals("synthetic-token-2", pushes.currentDeviceToken)
        assertEquals(listOf("synthetic-token-1", "synthetic-token-2"), handed)

        pushes.clearDeviceToken()
        pushes.registerForRemoteNotifications()
        tokens.asked.last()(Result.failure(IOException("SERVICE_NOT_AVAILABLE")))
        assertNull(pushes.currentDeviceToken)

        pushes.registerForRemoteNotifications()
        pushes.unregisterForRemoteNotifications()
        pushes.clearDeviceToken()
        assertEquals(1, tokens.deletions)
        tokens.asked.last()(Result.success("synthetic-token-3"))
        assertNull(pushes.currentDeviceToken)
        assertEquals(2, handed.size)

        // Without Firebase nothing is asked, deleted or kept.
        val none = AndroidPushNotifications(context, app.notificationPermission, received = { handed += it })
        none.registerForRemoteNotifications()
        none.unregisterForRemoteNotifications()
        none.tokenRefreshed("synthetic-token-4")
        assertNull(none.currentDeviceToken)
        assertEquals(2, handed.size)
    }

    /**
     * An account turns them on with Firebase's token, sent as an FCM one with the test notification; a token Firebase
     * makes later (PeekMessagingService's `onNewToken`) reaches the stores through the application and is sent in turn.
     */
    @Test
    fun anAccountTurnsThemOnWithFirebasesTokenAndANewOneFollows() {
        val stores = container(Tokens(next = "synthetic-token-1"))
        app.makeStores = { stores }
        app.makeContainer()
        idleUntil("the stores") { app.container.value === stores }
        signIn(stores, start = false)
        allow()

        var outcome: Result<Boolean>? = null
        stores.scope.launch { outcome = runCatching { stores.parcels.enableNotifications(AppLanguage.En) } }
        idleUntil("notifications on", ticking = true) { outcome != null }
        assertEquals(true, outcome?.getOrThrow())
        assertTrue(stores.parcels.notificationsEnabledOnDevice)
        val sent = devices.first()
        assertTrue(sent, sent.startsWith("POST ") && "\"token\":\"synthetic-token-1\"" in sent && "\"transport\":\"fcm\"" in sent)
        assertTrue(sent, "\"environment\":\"production\"" in sent && "\"sendTest\":true" in sent)

        val service = Robolectric.buildService(PeekMessagingService::class.java).create().get()
        service.onNewToken("synthetic-token-2")
        idleUntil("the new token sent") { devices.any { "synthetic-token-2" in it } }
        assertEquals("synthetic-token-2", stores.notifications.currentDeviceToken)
        val renewed = devices.last { "synthetic-token-2" in it }
        assertTrue(renewed, "\"sendTest\":false" in renewed && "\"transport\":\"fcm\"" in renewed)
    }

    /** A push's data, as Firebase hands it to PeekMessagingService, is posted as the app's notification. */
    @Test
    fun aPushsDataIsPostedAsTheAppsNotification() {
        allow()
        val parcel = UUID(0, 44)
        val message = RemoteMessage.Builder("1@fcm.googleapis.com")
            .setData(mapOf("title" to "Books", "body" to "Out for delivery", "parcel_id" to "$parcel"))
            .build()
        Robolectric.buildService(PeekMessagingService::class.java).create().get().onMessageReceived(message)
        val shade = shadowOf(context.getSystemService(NotificationManager::class.java))
        val posted = requireNotNull(shade.getNotification("$parcel", AppNotifications.ID)) { "Nothing posted" }
        assertEquals("Books", posted.extras.getString(Notification.EXTRA_TITLE))
        assertEquals("Out for delivery", posted.extras.getCharSequence(Notification.EXTRA_TEXT)?.toString())
    }

    @Test
    fun anAccountOnAPhoneThatAllowsThemWaitsForTheTokenAndKeepsTheInvitation() {
        // Firebase never answers, as on a phone without Google Play services.
        val app = container(Tokens())
        signIn(app)
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
    fun aTokenFirebaseRefusesEndsTheWaitThere() {
        // Firebase answers that it can give none, as without a network: the reader is told then, not 8 s later.
        val app = container(Tokens(failure = java.io.IOException("SERVICE_NOT_AVAILABLE")))
        signIn(app)
        allow()

        var outcome: Result<Boolean>? = null
        app.scope.launch { outcome = runCatching { app.parcels.enableNotifications(AppLanguage.En) } }
        var tenths = 0
        while (outcome == null && tenths < 100) {
            shadowOf(Looper.getMainLooper()).idleFor(Duration.ofMillis(100))
            tenths++
        }
        assertTrue("$outcome", outcome?.exceptionOrNull() is DeliveryApiError.PushTokenUnavailable)
        assertTrue("Waited $tenths tenths of a second", tenths < 10)
        assertFalse(app.parcels.notificationsEnabledOnDevice)
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
