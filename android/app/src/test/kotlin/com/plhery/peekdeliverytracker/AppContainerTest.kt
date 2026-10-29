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
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.platform.FakeAndroidKeyStore
import com.plhery.peekdeliverytracker.platform.KeystoreSecretStore
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import java.security.Security
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.RecordedRequest
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

    /** The process's lifecycle (ProcessLifecycleOwner's), in front. */
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
}
