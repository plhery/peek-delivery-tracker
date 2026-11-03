package com.plhery.peekdeliverytracker

import android.app.Application
import android.content.Context
import android.os.LocaleList
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthSession
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.JsonEncoder
import com.plhery.peekdeliverytracker.core.ParcelSharePreview
import com.plhery.peekdeliverytracker.platform.FakeAndroidKeyStore
import com.plhery.peekdeliverytracker.platform.KeystoreSecretStore
import com.plhery.peekdeliverytracker.platform.MemoryCookieJar
import com.plhery.peekdeliverytracker.platform.PeekHttp
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.ContainerDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ContainerParcelLinks
import java.security.Security
import java.time.Instant
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.RecordedRequest
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf

/** The stores the app starts with, against a server on this machine. */
@RunWith(AndroidJUnit4::class)
class AppContainerTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val server = MockWebServer()
    private val paths = mutableListOf<String>()
    private val agents = mutableListOf<String?>()
    private lateinit var previousCatalog: CarrierCatalog
    private var container: AppContainer? = null

    /** The app's windows' lifecycle (AppForeground's), in front. */
    private val process = object : LifecycleOwner {
        override val lifecycle = LifecycleRegistry(this)
    }

    @Before
    fun start() {
        previousCatalog = CarrierCatalog.shared
        process.lifecycle.currentState = Lifecycle.State.RESUMED
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                synchronized(paths) {
                    paths += "${request.method} ${request.url.encodedPath}"
                    agents += request.headers["User-Agent"]
                }
                // The catalog keeps the one it has; nothing else is served.
                return MockResponse.Builder().code(if (request.url.encodedPath == "/api/carriers") 503 else 404).build()
            }
        }
        server.start()
    }

    @After
    fun stop() {
        container?.scope?.cancel()
        CarrierCatalog.install(previousCatalog)
        server.close()
    }

    private fun configuration(signIn: Boolean = false): AppConfiguration {
        val base = server.url("/").toString().trimEnd('/')
        return AppConfiguration(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = base, supabaseUrl = if (signIn) base else null,
            supabasePublishableKey = if (signIn) "synthetic-publishable-key" else "", googleAuthEnabled = false,
            appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "group.test.peek",
        )
    }

    private fun container(): AppContainer = AppContainer(context, configuration(), scope = MainScope()).also { container = it }

    /** Runs the main thread's work, and lets the server and the background threads answer, until `done`. */
    private fun idleUntil(what: String, done: () -> Boolean) {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(30)
        while (true) {
            shadowOf(Looper.getMainLooper()).idle()
            if (done()) return
            if (System.nanoTime() > deadline) fail("Timed out waiting for $what")
            Thread.sleep(10)
        }
    }

    private fun requested(path: String) = synchronized(paths) { path in paths }

    private fun count(path: String) = synchronized(paths) { paths.count { it == path } }

    /** Gives anything the main thread or the server still had to do a moment to land. */
    private fun settle() {
        shadowOf(Looper.getMainLooper()).idle()
        Thread.sleep(200)
        shadowOf(Looper.getMainLooper()).idle()
    }

    @Test
    fun aFreshInstallInstallsTheCatalogAndOpensOnTheFrontDoorWithNoParcels() {
        val app = container()
        // Nothing starts before `start`.
        shadowOf(Looper.getMainLooper()).idle()
        assertEquals(AuthenticationState.Loading, app.session.state)

        app.start(process.lifecycle)
        idleUntil("the catalog") { app.catalog.value != null }
        assertSame(app.catalog.value, CarrierCatalog.shared)
        idleUntil("the session") { app.session.state != AuthenticationState.Loading }
        assertEquals(AuthenticationState.Welcome, app.session.state)
        assertTrue(app.session.isGuest)
        idleUntil("the catalog's refresh") { requested("GET /api/carriers") }
        assertTrue(app.parcels.parcelsFlow.value.isEmpty())

        // The app's own User-Agent, on every request.
        val agent = synchronized(paths) { agents.first() }
        assertTrue("$agent", agent!!.startsWith("PeekDeliveryTracker/${BuildConfig.VERSION_CODE} okhttp/"))
        assertFalse(synchronized(paths) { agents.any { it != agent } })
        // The language the widget reads, in the app group's defaults.
        assertEquals("en", PreferencesDefaults.appGroup(context, "group.test.peek").string(AppLanguage.storageKey))

        try {
            app.start(process.lifecycle)
            fail("Expected a second start to be refused")
        } catch (_: IllegalStateException) {
        }
    }

    @Test
    fun theStoresReadTheirSecretsOffTheMainThread() {
        Security.addProvider(FakeAndroidKeyStore())
        val services = listOf("auth", "appAttest", "deviceParcels", "liveActivityRevocations").map { "com.plhery.PeekDeliveryTracker.$it" }
        try {
            // Something under each that the stores cannot decode: they read it, then start as with nothing saved.
            for (service in services) KeystoreSecretStore.of(context, service).save("[]".toByteArray())
            FakeAndroidKeyStore.reads.clear()
            var made: AppContainer? = null
            MainScope().launch { made = AppContainer.make(context, configuration(signIn = true)).also { container = it } }
            idleUntil("the stores") { made != null }
            val app = made!!
            app.start(process.lifecycle)
            idleUntil("the session") { app.session.state != AuthenticationState.Loading }
            assertEquals(AuthenticationState.Welcome, app.session.state)

            // Each secret was read, the session's for the bootstrap too, and none on the main thread.
            val reads = synchronized(FakeAndroidKeyStore.reads) { FakeAndroidKeyStore.reads.toList() }
            assertEquals(services.map { "peek.secret.$it" }.toSet(), reads.map { it.first }.toSet())
            assertFalse("$reads", reads.any { it.second })
        } finally {
            for (service in services) KeystoreSecretStore.of(context, service).delete()
            Security.removeProvider("AndroidKeyStore")
        }
    }

    @Test
    fun theDemoOpensOnItsParcels() {
        PreferencesDefaults.standard(context).set("demo", forKey = "sdt.native.experience.v1")
        val app = container()
        app.start(process.lifecycle)
        idleUntil("the demo") { app.session.state == AuthenticationState.Demo }
        idleUntil("the demo's parcels") { app.parcels.parcelsFlow.value.isNotEmpty() }
        assertTrue(app.session.isDemo)
    }

    /**
     * C-SE04: Settings' widget switch, through the deliveries' source, turns the widget off for this phone, which then
     * has no parcels to show and says so, and on again, when the demo's parcels come back.
     */
    @Test
    fun theWidgetsSwitchEmptiesTheWidgetAndFillsItAgain() {
        PreferencesDefaults.standard(context).set("demo", forKey = "sdt.native.experience.v1")
        val app = container()
        val widget = DeliveryWidgetSharedStore(PreferencesDefaults.appGroup(context, "group.test.peek"))
        val source = ContainerDeliveries(app)
        app.start(process.lifecycle)
        idleUntil("the demo's widget") { widget.snapshot?.parcels?.isNotEmpty() == true }
        assertTrue(source.deliveryWidgetEnabled.value)

        source.setDeliveryWidgetEnabled(false)
        idleUntil("the widget turned off") { !widget.isEnabled }
        assertNull(widget.snapshot)
        assertFalse(source.deliveryWidgetEnabled.value)

        source.setDeliveryWidgetEnabled(true)
        idleUntil("the demo's widget again") { widget.snapshot?.parcels?.isNotEmpty() == true }
        assertTrue(widget.isEnabled)
        assertTrue(source.deliveryWidgetEnabled.value)
    }

    /**
     * C-D05 / C-D20: with Apple's sign-in on in the build's configuration, an account that uses Apple is still deleted
     * on Android, whose stand-in can't sign in with Apple again: DELETE /api/account goes without Apple's code.
     */
    @Test
    fun anAppleAccountIsDeletedWithoutSigningInWithAppleAgain() {
        Security.addProvider(FakeAndroidKeyStore())
        val deletions = mutableListOf<String>()
        val user = AuthUser(
            UUID(0, 7), email = "relay@example.com", isAnonymous = false,
            identities = listOf(AuthUser.Identity(provider = "apple", identityData = AuthUser.IdentityData(sub = "synthetic-apple-user"))),
        )
        val answers = Interceptor { chain ->
            val request = chain.request()
            val (code, body) = when (request.method to request.url.encodedPath) {
                "POST" to "/auth/v1/verify" -> 200 to JsonEncoder.deliveryTracker.encode(
                    AuthSession.serializer(),
                    AuthSession(
                        accessToken = "synthetic-access", tokenType = "bearer", expiresIn = 3600,
                        expiresAt = Instant.now().epochSecond + 3600, refreshToken = "synthetic-refresh", user = user,
                    ),
                ).decodeToString()
                "DELETE" to "/api/account" -> {
                    synchronized(deletions) { deletions += Buffer().also { request.body!!.writeTo(it) }.readUtf8() }
                    200 to """{"ok":true}"""
                }
                else -> 404 to "{}"
            }
            Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(code).message("").body(body.toResponseBody()).build()
        }
        try {
            val server = "https://peek.example.test"
            val configuration = AppConfiguration(
                mode = AppConfiguration.Mode.Api, apiBaseUrl = server, supabaseUrl = server,
                supabasePublishableKey = "synthetic-publishable-key", googleAuthEnabled = false, appleAuthEnabled = true,
                emailOtpEnabled = true, appGroupIdentifier = "group.test.peek",
            )
            val http = PeekHttp.client(MemoryCookieJar()).newBuilder().addInterceptor(answers).build()
            val app = AppContainer(context, configuration, http, scope = MainScope()).also { container = it }
            app.start(process.lifecycle)
            idleUntil("the session") { app.session.state != AuthenticationState.Loading }
            app.scope.launch { app.session.verifyCode(email = "relay@example.com", code = "123456") }
            idleUntil("the sign-in") { app.session.state is AuthenticationState.SignedIn }

            var deleted: Result<Unit>? = null
            app.scope.launch { deleted = runCatching { app.parcels.deleteAccount(confirmation = "relay@example.com") } }
            idleUntil("the deletion") { deleted != null }
            deleted!!.getOrThrow()
            assertEquals(listOf("""{"confirmation":"relay@example.com"}"""), synchronized(deletions) { deletions.toList() })
            assertTrue(app.session.state !is AuthenticationState.SignedIn)
        } finally {
            KeystoreSecretStore.of(context, "com.plhery.PeekDeliveryTracker.auth").delete()
            Security.removeProvider("AndroidKeyStore")
        }
    }

    /**
     * A-SE07: a debug build's `sdt.debug.storeScreenshots` leaves the demo's bar out (`DemoModeBar.hidden`), read at
     * each use as the iPhone reads it; never in a release.
     */
    @Test
    fun aDebugBuildsStoreScreenshotsKeyLeavesTheDemosBarOut() {
        val defaults = PreferencesDefaults.standard(context)
        val debug = AppContainer(context, configuration(), scope = MainScope(), debug = true).also { container = it }
        assertFalse(debug.storeScreenshots)
        defaults.set(true, forKey = "sdt.debug.storeScreenshots")
        assertTrue(debug.storeScreenshots)
        assertTrue(ContainerDeliveries(debug).storeScreenshots)
        debug.scope.cancel()
        val release = AppContainer(context, configuration(), scope = MainScope(), debug = false).also { container = it }
        assertFalse(release.storeScreenshots)
    }

    /**
     * B-L10: a debug build reads the iPhone's launch previews, `sdt.debug.parcelShare` and `sdt.debug.parcelFeedback`
     * (on the parcel `.parcel` names), at each use, and the share's puts the demo's link and words in its state; a
     * release reads none, whatever the defaults say.
     */
    @Test
    fun aDebugBuildReadsTheLaunchPreviewsAndAReleaseNone() {
        val defaults = PreferencesDefaults.standard(context)
        defaults.set("demo", forKey = "sdt.native.experience.v1")
        val debug = AppContainer(context, configuration(), scope = MainScope(), debug = true).also { container = it }
        assertFalse(debug.launchPreviews.opensPage)
        debug.start(process.lifecycle)
        idleUntil("the demo's parcels") { debug.session.state == AuthenticationState.Demo && debug.parcels.parcelsFlow.value.isNotEmpty() }
        val parcels = debug.parcels.parcelsFlow.value
        val named = parcels.last()
        defaults.set("gift", forKey = "sdt.debug.parcelShare")
        defaults.set("ask", forKey = "sdt.debug.parcelFeedback")
        defaults.set(named.id.toString(), forKey = "sdt.debug.parcelFeedback.parcel")
        val deliveries = ContainerDeliveries(debug)
        assertEquals("gift", deliveries.launchPreviews.share)
        assertEquals("ask", deliveries.launchPreviews.feedback)
        assertEquals("ask", ContainerParcelLinks(debug).feedbackPreview)
        val shared = ParcelSharePreview.parcel(parcels, variant = "gift")!!
        assertEquals(shared, deliveries.launchPreviews.parcel(parcels))
        // The share sheet's `prepare`: the demo's link to the parcel, a gift, with its words on this phone.
        deliveries.launchPreviews.prepareShare(shared.id)
        assertEquals(true, runBlocking { deliveries.shareClient(shared).current() }?.gift)
        assertEquals("Sam", deliveries.shareNotes.words(shared.id)?.from)
        defaults.removeObject(forKey = "sdt.debug.parcelShare")
        assertEquals(named, deliveries.launchPreviews.parcel(parcels))
        debug.scope.cancel()
        defaults.set("gift", forKey = "sdt.debug.parcelShare")
        val release = AppContainer(context, configuration(), scope = MainScope(), debug = false).also { container = it }
        assertFalse(release.launchPreviews.opensPage)
        assertNull(release.launchPreviews.share)
        assertNull(release.launchPreviews.feedback)
        assertNull(ContainerParcelLinks(release).feedbackPreview)
    }

    @Test
    fun theDemoNeverAsksForARatingButForADebugBuildsLook() {
        val defaults = PreferencesDefaults.standard(context)
        defaults.set("demo", forKey = "sdt.native.experience.v1")
        val app = container()
        // Nothing asks over the first screen, before any parcel.
        assertFalse(app.reviewSettled.value)
        app.start(process.lifecycle)
        idleUntil("the demo's parcels") { app.session.state == AuthenticationState.Demo && app.parcels.parcelsFlow.value.isNotEmpty() }
        settle()
        assertFalse(app.reviewSettled.value)
        app.scope.cancel()

        // `sdt.debug.reviewDue`: every calm moment asks, over the demo too.
        defaults.set(true, forKey = "sdt.debug.reviewDue")
        val forced = AppContainer(context, configuration(), scope = MainScope(), debug = true).also { container = it }
        forced.start(process.lifecycle)
        idleUntil("the demo's parcels") { forced.session.state == AuthenticationState.Demo && forced.parcels.parcelsFlow.value.isNotEmpty() }
        idleUntil("a calm moment") { forced.reviewSettled.value }
        assertTrue(forced.reviewPrompt.isDue(Instant.now(), calm = true))
        // Read at each use, as the iPhone reads it: cleared while the app runs, a calm moment is an ordinary one again.
        defaults.set(false, forKey = "sdt.debug.reviewDue")
        assertFalse(forced.reviewPrompt.isDue(Instant.now(), calm = true))
        defaults.set(true, forKey = "sdt.debug.reviewDue")
        assertTrue(forced.reviewPrompt.isDue(Instant.now(), calm = true))
        // Never in a release, whatever the defaults say.
        forced.scope.cancel()
        val release = AppContainer(context, configuration(), scope = MainScope(), debug = false).also { container = it }
        release.start(process.lifecycle)
        idleUntil("the demo's parcels") { release.session.state == AuthenticationState.Demo && release.parcels.parcelsFlow.value.isNotEmpty() }
        settle()
        assertFalse(release.reviewSettled.value)
        assertFalse(release.reviewPrompt.isDue(Instant.now(), calm = true))
    }

    @Test
    fun theSavedLanguageOpensTheAppAndAChangeIsSavedForAppAndWidget() {
        PreferencesDefaults.standard(context).set("de", forKey = AppLanguage.storageKey)
        val app = container()
        assertEquals(AppLanguage.De, app.localizer.language)
        assertEquals("Auf diesem Gerät verfolgen", app.localizer.text("native.follow.action"))
        app.start(process.lifecycle)
        idleUntil("the session") { app.session.state != AuthenticationState.Loading }

        app.localizer.language = AppLanguage.Fr
        idleUntil("the saved language") { app.defaults.string(AppLanguage.storageKey) == "fr" }
        assertEquals("fr", PreferencesDefaults.appGroup(context, "group.test.peek").string(AppLanguage.storageKey))
    }

    @Test
    fun withNoLanguageSavedTheAppTakesThePhonesNewOneWithoutSavingIt() {
        val phone = LocaleList.getDefault()
        try {
            LocaleList.setDefault(LocaleList.forLanguageTags("nl-NL,en-GB"))
            val app = container()
            app.start(process.lifecycle)
            idleUntil("the session") { app.session.state != AuthenticationState.Loading }
            assertEquals(AppLanguage.En, app.localizer.language)
            val widget = PreferencesDefaults.appGroup(context, "group.test.peek")

            // The phone's languages change, which relaunches the iPhone app.
            LocaleList.setDefault(LocaleList.forLanguageTags("de-DE,en-GB"))
            (context as Application).onConfigurationChanged(context.resources.configuration)
            assertEquals(AppLanguage.De, app.localizer.language)
            idleUntil("the widget's language") { widget.string(AppLanguage.storageKey) == "de" }
            assertNull(app.defaults.string(AppLanguage.storageKey))

            // One chosen is saved, and the phone's languages no longer count.
            app.localizer.language = AppLanguage.Fr
            idleUntil("the saved language") { app.defaults.string(AppLanguage.storageKey) == "fr" }
            LocaleList.setDefault(LocaleList.forLanguageTags("it-IT"))
            context.onConfigurationChanged(context.resources.configuration)
            settle()
            assertEquals(AppLanguage.Fr, app.localizer.language)
            assertEquals("fr", widget.string(AppLanguage.storageKey))
        } finally {
            LocaleList.setDefault(phone)
        }
    }

    @Test
    fun onlyLeavingTheAppAndComingBackCountsAsAForeground() {
        val app = container()
        app.start(process.lifecycle)
        idleUntil("the catalog's refresh") { requested("GET /api/carriers") }
        // The server has no analytics: each start asks again.
        idleUntil("the analytics' start") { requested("GET /api/analytics/config") }

        // A dialog or the share sheet over the app pauses it and leaves it started: still in front.
        process.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        process.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        settle()
        assertEquals(1, count("GET /api/analytics/config"))

        // Left and back: a foreground, which starts the analytics again; the catalog, read at launch, waits its interval.
        process.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        settle()
        assertEquals(1, count("GET /api/analytics/config"))
        process.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
        idleUntil("the foreground's analytics") { count("GET /api/analytics/config") == 2 }
        settle()
        assertEquals(1, count("GET /api/carriers"))
    }

    /**
     * The sign-in counted through the app's own wiring: the screen the session shows is `sign-in`, and the session's
     * actions go to the analytics (`analytics::action`), each from that screen. The server and Umami's collector are
     * answered on the client itself (synthetic hosts), so nothing leaves the machine.
     */
    @Test
    fun theSignInIsCountedAsItsScreenWithItsActions() {
        Security.addProvider(FakeAndroidKeyStore())
        val sent = mutableListOf<JSONObject>()
        val answers = Interceptor { chain ->
            val request = chain.request()
            val path = request.url.encodedPath
            val (code, body) = when {
                path == "/api/analytics/config" -> 200 to
                    """{"endpoint":"https://collector.example.test/api/send","hostname":"peek.example.test","androidWebsite":"00000000-0000-4000-8000-0000000000a1"}"""
                request.url.host == "collector.example.test" -> {
                    val event = JSONObject(Buffer().also { request.body!!.writeTo(it) }.readUtf8()).getJSONObject("payload")
                    synchronized(sent) { sent += event }
                    200 to "{}"
                }
                path == "/auth/v1/otp" -> 200 to "{}"
                else -> 404 to "{}"
            }
            Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(code).message("").body(body.toResponseBody()).build()
        }
        fun events() = synchronized(sent) { sent.map { "${it.getString("url")} ${it.optString("name")} ${it.getJSONObject("data").optString("outcome")}".trim() } }
        try {
            val server = "https://peek.example.test"
            val configuration = AppConfiguration(
                mode = AppConfiguration.Mode.Api, apiBaseUrl = server, supabaseUrl = server,
                supabasePublishableKey = "synthetic-publishable-key", googleAuthEnabled = false, appleAuthEnabled = false,
                emailOtpEnabled = true, appGroupIdentifier = "group.test.peek",
            )
            val http = PeekHttp.client(MemoryCookieJar()).newBuilder().addInterceptor(answers).build()
            val app = AppContainer(context, configuration, http, scope = MainScope()).also { container = it }
            app.start(process.lifecycle)
            idleUntil("the front door's view") { "/front-door" in events() }
            app.session.showSignIn()
            idleUntil("the sign-in's view") { "/sign-in" in events() }
            app.scope.launch { app.session.sendCode("reader@example.com", AppLanguage.En) }
            idleUntil("the send's outcome") { "/sign-in sign-in-code-send success" in events() }
            val signIn = events().dropWhile { it != "/sign-in" }
            assertEquals(listOf("/sign-in", "/sign-in sign-in-code-send started", "/sign-in sign-in-code-send success"), signIn)
            assertTrue(synchronized(sent) { sent.all { it.getJSONObject("data").getString("mode") == "anonymous" } })
        } finally {
            Security.removeProvider("AndroidKeyStore")
        }
    }
}
