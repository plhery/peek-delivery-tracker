package com.plhery.peekdeliverytracker

import android.content.ComponentName
import android.content.Intent
import android.os.Looper
import android.provider.Settings
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import java.io.IOException
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The app in front or left, as its stores follow it (`scenePhase`, [AppForeground]): its window starts and stops it, a
 * turn of the window doesn't, and the share card, the iPhone's extension in a process of its own, neither brings the
 * app to the front nor sends it away: no "app-foreground", no reload, no catalog asked for. A foreground starts the
 * analytics again, which ask for their configuration: each ask is one foreground. On the app's own stores, at a made-up
 * address, every request turned away before it leaves.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class AppForegroundTest {
    private val app = ApplicationProvider.getApplicationContext<PeekApplication>()
    private val catalog = CarrierCatalog.shared
    private val paths = mutableListOf<String>()
    private val events = mutableListOf<Lifecycle.Event>()
    private var made: AppContainer? = null
    private val windows = mutableListOf<ActivityController<*>>()

    @Before
    fun offline() {
        // Animations off: a guest with nothing followed sees the first open, whose carriers roll on frame after frame,
        // and the main looper would never run dry.
        Settings.Global.putFloat(app.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 0f)
        app.shareDefaults = { MemoryDefaults() }
        val configuration = BuildSettings.configuration.copy(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://peek.invalid", supabaseUrl = null, supabasePublishableKey = "",
        )
        val http = OkHttpClient.Builder().addInterceptor { chain ->
            synchronized(paths) { paths += chain.request().url.encodedPath }
            throw IOException("No network in this test")
        }.build()
        app.makeStores = { application -> withContext(Dispatchers.IO) { AppContainer(application, configuration, http = http) }.also { made = it } }
    }

    /** The windows gone, and their compositions let finish, so none waits on in the tests after this one. */
    @After
    fun stop() {
        windows.forEach { it.close() }
        shadowOf(Looper.getMainLooper()).idle()
        made?.scope?.cancel()
        CarrierCatalog.install(catalog)
        Settings.Global.putFloat(app.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f)
    }

    private fun window(): ActivityController<MainActivity> =
        Robolectric.buildActivity(MainActivity::class.java, Intent.makeMainActivity(ComponentName(app, MainActivity::class.java))).also { windows += it }

    private fun card(): ActivityController<ShareActivity> = Robolectric.buildActivity(
        ShareActivity::class.java,
        Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, "TESTLAMP0001"),
    )

    /** The analytics' asks for their configuration: one as the stores start, one more each foreground. */
    private fun foregrounds(): Int = synchronized(paths) { paths.count { it == "/api/analytics/config" } }

    /** Runs the main thread's work, and lets the background threads answer, until `done`. */
    private fun idleUntil(what: String, done: () -> Boolean) {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(30)
        while (true) {
            shadowOf(Looper.getMainLooper()).idle()
            if (done()) return
            if (System.nanoTime() > deadline) fail("Timed out waiting for $what")
            Thread.sleep(10)
        }
    }

    /** The stores made and started, and the analytics' first ask; from then on, what the stores hear is written down. */
    private fun started() {
        idleUntil("the stores' analytics") { app.container.value != null && foregrounds() == 1 }
        app.foreground.lifecycle.addObserver(
            LifecycleEventObserver { _, event -> if (event == Lifecycle.Event.ON_START || event == Lifecycle.Event.ON_STOP) events += event },
        )
        events.clear()
    }

    /** Past the moment a last window's stop is taken for leaving, and anything the stores began then given time. */
    private fun settle() {
        shadowOf(Looper.getMainLooper()).idleFor(Duration.ofMillis(AppForeground.LEAVE_DELAY))
        Thread.sleep(200)
        shadowOf(Looper.getMainLooper()).idle()
    }

    @Test
    fun theShareCardOverAnotherAppNeitherBringsTheAppToTheFrontNorSendsItAway() {
        val window = window().setup()
        started()
        // Left for another app, which shares.
        window.pause().stop()
        settle()
        assertEquals(listOf(Lifecycle.Event.ON_STOP), events)

        val card = card().setup()
        settle()
        assertEquals(Lifecycle.State.CREATED, app.foreground.lifecycle.currentState)
        assertEquals("No foreground counted", 1, foregrounds())
        // Done or Cancel, and it goes.
        card.get().finish()
        card.pause().stop().destroy()
        settle()
        assertEquals(listOf(Lifecycle.Event.ON_STOP), events)
        assertEquals(1, foregrounds())

        // The app opened: the foreground the card didn't count.
        window.restart().resume()
        idleUntil("the foreground's analytics") { foregrounds() == 2 }
        assertEquals(listOf(Lifecycle.Event.ON_STOP, Lifecycle.Event.ON_START), events)
    }

    @Test
    fun theShareCardOverTheAppLeavesItInFront() {
        val window = window().setup()
        started()
        // Shared from the app's own share sheet: the card stands over its window.
        window.pause()
        val card = card().setup()
        settle()
        card.get().finish()
        card.pause().stop().destroy()
        window.resume()
        settle()
        assertEquals(Lifecycle.State.STARTED, app.foreground.lifecycle.currentState)
        assertEquals(emptyList<Lifecycle.Event>(), events)
        assertEquals(1, foregrounds())
    }

    @Test
    fun aTurnOfTheWindowIsNoSecondForegroundAndLeavingAndComingBackIsOne() {
        val window = window().setup()
        started()
        window.recreate()
        settle()
        assertTrue(app.foreground.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
        assertEquals(emptyList<Lifecycle.Event>(), events)
        assertEquals(1, foregrounds())

        window.pause().stop()
        settle()
        window.restart().resume()
        idleUntil("the foreground's analytics") { foregrounds() == 2 }
        assertEquals(listOf(Lifecycle.Event.ON_STOP, Lifecycle.Event.ON_START), events)
    }
}
