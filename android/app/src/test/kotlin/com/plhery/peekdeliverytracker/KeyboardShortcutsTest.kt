package com.plhery.peekdeliverytracker

import android.content.ComponentName
import android.content.Intent
import android.os.Looper
import android.provider.Settings
import android.view.KeyEvent
import android.view.KeyboardShortcutGroup
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import java.io.IOException
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
 * The app's keys the system lists over its window (Meta+/), from MainActivity: those its screens answer now, titled as
 * the iPhone titles them. On the app's own stores, at a made-up address, every request turned away before it leaves.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class KeyboardShortcutsTest {
    private val app = ApplicationProvider.getApplicationContext<PeekApplication>()
    private val catalog = CarrierCatalog.shared
    private var made: AppContainer? = null
    private var window: ActivityController<MainActivity>? = null

    @Before
    fun offline() {
        Settings.Global.putFloat(app.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 0f)
        app.shareDefaults = { MemoryDefaults() }
        val configuration = BuildSettings.configuration.copy(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://peek.invalid", supabaseUrl = null, supabasePublishableKey = "",
        )
        val http = OkHttpClient.Builder().addInterceptor { throw IOException("No network in this test") }.build()
        app.makeStores = { application -> withContext(Dispatchers.IO) { AppContainer(application, configuration, http = http) }.also { made = it } }
    }

    @After
    fun stop() {
        window?.close()
        shadowOf(Looper.getMainLooper()).idle()
        made?.scope?.cancel()
        CarrierCatalog.install(catalog)
        Settings.Global.putFloat(app.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f)
    }

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

    private fun MainActivity.shortcuts(): List<KeyboardShortcutGroup> =
        mutableListOf<KeyboardShortcutGroup>().also { onProvideKeyboardShortcuts(it, null, -1) }

    /** In the demo's tabs, the app's keys stand under its name: the account, Add and the three tabs among them. */
    @Test
    fun theWindowListsTheKeysItsScreensAnswer() {
        val controller = Robolectric.buildActivity(MainActivity::class.java, Intent.makeMainActivity(ComponentName(app, MainActivity::class.java)))
            .also { window = it }.setup()
        idleUntil("the stores") { app.container.value != null }
        val activity = controller.get()
        app.container.value!!.session.enterDemo()
        idleUntil("the demo's tabs") { activity.shortcuts().isNotEmpty() }

        val group = activity.shortcuts().first()
        assertEquals("Peek", group.label.toString())
        val titles = group.items.map { it.label.toString() }
        assertTrue(titles.toString(), titles.containsAll(listOf("Account", "Add parcel", "Deliveries", "Passport", "Friends")))
        val passport = group.items.first { it.label.toString() == "Passport" }
        assertEquals(KeyEvent.KEYCODE_2, passport.keycode)
        assertEquals(KeyEvent.META_CTRL_ON, passport.modifiers)
    }
}
