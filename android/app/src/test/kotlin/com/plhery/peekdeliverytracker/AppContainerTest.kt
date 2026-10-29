package com.plhery.peekdeliverytracker

import android.content.Context
import android.os.Looper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.RecordedRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
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

    @Before
    fun start() {
        previousCatalog = CarrierCatalog.shared
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

    private fun container(): AppContainer {
        val configuration = AppConfiguration(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = server.url("/").toString().trimEnd('/'), supabaseUrl = null,
            supabasePublishableKey = "", googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true,
            appGroupIdentifier = "group.test.peek",
        )
        return AppContainer(context, configuration, scope = MainScope()).also { container = it }
    }

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

    @Test
    fun aFreshInstallInstallsTheCatalogAndOpensOnTheFrontDoorWithNoParcels() {
        val app = container()
        // Nothing starts before `start`.
        shadowOf(Looper.getMainLooper()).idle()
        assertEquals(AuthenticationState.Loading, app.session.state)

        app.start()
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
            app.start()
            fail("Expected a second start to be refused")
        } catch (_: IllegalStateException) {
        }
    }

    @Test
    fun theDemoOpensOnItsParcels() {
        PreferencesDefaults.standard(context).set("demo", forKey = "sdt.native.experience.v1")
        val app = container()
        app.start()
        idleUntil("the demo") { app.session.state == AuthenticationState.Demo }
        idleUntil("the demo's parcels") { app.parcels.parcelsFlow.value.isNotEmpty() }
        assertTrue(app.session.isDemo)
    }

    @Test
    fun theSavedLanguageOpensTheAppAndAChangeIsSavedForAppAndWidget() {
        PreferencesDefaults.standard(context).set("de", forKey = AppLanguage.storageKey)
        val app = container()
        assertEquals(AppLanguage.De, app.localizer.language)
        app.start()
        idleUntil("the session") { app.session.state != AuthenticationState.Loading }

        app.localizer.language = AppLanguage.Fr
        idleUntil("the saved language") { app.defaults.string(AppLanguage.storageKey) == "fr" }
        assertEquals("fr", PreferencesDefaults.appGroup(context, "group.test.peek").string(AppLanguage.storageKey))
    }

    @Test
    fun comingBackToTheFrontAsksForTheCatalogNoSoonerThanItsInterval() {
        val app = container()
        app.start()
        idleUntil("the catalog's refresh") { requested("GET /api/carriers") }
        // Already in front at launch: no change.
        app.sceneActive(true)
        app.sceneActive(false)
        app.sceneActive(true)
        shadowOf(Looper.getMainLooper()).idle()
        Thread.sleep(200)
        shadowOf(Looper.getMainLooper()).idle()
        assertEquals(1, synchronized(paths) { paths.count { it == "GET /api/carriers" } })
    }
}
