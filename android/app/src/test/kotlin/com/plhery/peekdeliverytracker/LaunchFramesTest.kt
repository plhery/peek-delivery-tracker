package com.plhery.peekdeliverytracker

import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
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
import org.robolectric.shadows.ShadowChoreographer

/**
 * The frames MainActivity draws as it opens, on the app's own stores with the demo, every request turned away: the
 * first one after the launch window is the screen laid out as it stays, its top bar under the status bar, its cards
 * with their room, and nothing in it moves after, as the iPhone draws its first frame after its launch screen. The
 * window has the phone's status and navigation bars, and the carriers' brand data takes its time to decode, as on a
 * slow phone, past the session being read.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class LaunchFramesTest {
    private val app = ApplicationProvider.getApplicationContext<PeekApplication>()
    private val catalog = CarrierCatalog.shared
    private var made: AppContainer? = null
    private val windows = mutableListOf<ActivityController<MainActivity>>()

    /** Where the brand data is decoded, which takes its time ([offline]). */
    private val decoder = Executors.newSingleThreadExecutor()

    /** How long the brand data takes to decode after the session is read, in milliseconds. */
    @Volatile private var brandMillis = BRAND_MILLIS

    /**
     * A card, or anything else in the list one taps, where a frame placed it: its words, its top and its whole height
     * in pixels, the part out of the window too.
     */
    private data class Card(val said: String, val top: Float, val height: Float)

    /** What a frame drew: in which window, once the stores were made or not, the top bar's top, the list's cards. */
    private data class Frame(val window: Activity, val stores: Boolean, val top: Float?, val list: Boolean, val cards: List<Card>) {
        override fun toString() = "Frame(stores=$stores, top=$top, list=$list, cards=$cards)"
    }

    private val frames = mutableListOf<Frame>()

    /** Each window of the app as it is made: the bars' insets as the system gives them, and its frames written down. */
    private val windowsMade = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityPostCreated(activity: Activity, savedInstanceState: Bundle?) {
            if (activity !is MainActivity) return
            val decor = activity.window.decorView
            ViewCompat.setOnApplyWindowInsetsListener(decor) { view, _ -> ViewCompat.onApplyWindowInsets(view, bars) }
            decor.viewTreeObserver.addOnDrawListener(ViewTreeObserver.OnDrawListener { frames += frame(activity) })
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit
        override fun onActivityStarted(activity: Activity) = Unit
        override fun onActivityResumed(activity: Activity) = Unit
        override fun onActivityPaused(activity: Activity) = Unit
        override fun onActivityStopped(activity: Activity) = Unit
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
        override fun onActivityDestroyed(activity: Activity) = Unit
    }

    private val bars = WindowInsetsCompat.Builder()
        .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, STATUS_BAR, 0, 0))
        .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, NAVIGATION_BAR))
        .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
        .build()

    @Before
    fun offline() {
        app.shareDefaults = { MemoryDefaults() }
        val configuration = BuildSettings.configuration.copy(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://peek.invalid", supabaseUrl = null, supabasePublishableKey = "",
        )
        val http = OkHttpClient.Builder().addInterceptor { throw IOException("No network in this test") }.build()
        app.makeStores = { application -> withContext(Dispatchers.IO) { AppContainer(application, configuration, http = http) }.also { made = it } }
        // The demo, chosen on an earlier launch.
        PreferencesDefaults.standard(app).set("demo", "sdt.native.experience.v1")
        // A frame at each of the test's steps, as the phone's display asks for them, rather than at once as each is
        // asked for: one held under the launch window would be asked for again at once, and the list's animations
        // would have no end.
        ShadowChoreographer.setPaused(true)
        // The brand data is decoded only well after the session is read, as on a slow phone.
        decoder.execute {
            try {
                while (app.container.value?.session?.stateFlow?.value.let { it == null || it == AuthenticationState.Loading }) Thread.sleep(5)
                Thread.sleep(brandMillis)
            } catch (_: InterruptedException) {
                // The test is over.
            }
        }
        CarrierIdentities.decoding = decoder.asCoroutineDispatcher()
        app.registerActivityLifecycleCallbacks(windowsMade)
    }

    @After
    fun stop() {
        windows.forEach { it.close() }
        shadowOf(Looper.getMainLooper()).idle()
        app.unregisterActivityLifecycleCallbacks(windowsMade)
        made?.scope?.cancel()
        CarrierIdentities.decoding = Dispatchers.IO
        decoder.shutdownNow()
        ShadowChoreographer.setPaused(false)
        CarrierCatalog.install(catalog)
    }

    /** The first frame after the stores are made shows the deliveries, their top bar under the status bar. */
    @Test
    fun theFirstFrameAfterTheStoresHasTheTopBarUnderTheStatusBar() {
        launch()
        val first = frames.first { it.stores }
        assertTrue("The first frame after the stores has the top bar under the status bar ($STATUS_BAR px): $first", (first.top ?: -1f) >= STATUS_BAR)
    }

    /** The first frame that draws the list draws its cards as they stay, each with its room. */
    @Test
    fun theCardsHaveTheirRoomInTheFirstFrameTheListIsDrawn() {
        launch()
        val first = frames.first { it.list }
        val settled = frames.last()
        assertTrue("The demo's cards show: $settled", settled.cards.size >= 3)
        assertEquals("The first frame of the list: $first", settled.cards.map { it.said to it.height }, first.cards.map { it.said to it.height })
    }

    /** The list's first parcels stand in their places from its first frame: none moves into it, from a pile or else. */
    @Test
    fun noCardMovesAfterTheFirstFrameOfTheList() {
        launch()
        steady(frames.dropWhile { !it.list }, "after the launch window")
    }

    /**
     * A window made again, turned to landscape, draws its first frame laid out as it stays, its insets and its cards
     * with it: it waits for the insets, the carriers being decoded already.
     */
    @Test
    fun aWindowMadeAgainDrawsItsFirstFrameAsItStays() {
        val window = launch()
        val before = window.get()
        frames.clear()
        window.configurationChange(Configuration(app.resources.configuration).apply {
            orientation = Configuration.ORIENTATION_LANDSCAPE
            screenWidthDp = 844
            screenHeightDp = 390
        })
        // The window made again shown, as the system shows it once it is resumed.
        window.visible()
        settle()
        assertNotEquals("The window is made again", before, window.get())
        val drawn = frames.filter { it.window === window.get() }
        assertTrue("The window made again draws its first frame on the top bar under the status bar: ${drawn.firstOrNull()}", (drawn.first().top ?: -1f) >= STATUS_BAR)
        assertTrue("…and its cards: ${drawn.first()}", drawn.first().cards.size >= 3)
        steady(drawn, "in the window made again")
    }

    /** A change of the night mode changes the window's colours, not its places. */
    @Test
    fun aChangeOfTheNightModeMovesNothing() {
        val window = launch()
        val before = frames.last()
        frames.clear()
        window.configurationChange(Configuration(app.resources.configuration).apply {
            uiMode = (uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or Configuration.UI_MODE_NIGHT_YES
        })
        settle()
        assertTrue("The window stays and draws again", frames.isNotEmpty() && frames.all { it.window === before.window })
        steady(listOf(before) + frames, "through the night mode")
    }

    /**
     * Back to the app from Recents: the window left in the background comes back as it was, and one closed meanwhile
     * comes back made again from its first frame as it stays, the stores and the carriers still in hand.
     */
    @Test
    fun aWarmStartDrawsTheListAsItStays() {
        val window = launch()
        val before = frames.last()
        frames.clear()
        window.pause().stop()
        settle()
        window.restart().start().resume().topActivityResumed(true)
        settle()
        steady(listOf(before) + frames.filter { it.window === before.window }, "back from the background")

        window.pause().stop().destroy()
        windows.remove(window)
        frames.clear()
        open()
        settle()
        val first = frames.first()
        assertTrue("A window opened again draws its first frame laid out: $first", (first.top ?: -1f) >= STATUS_BAR && first.cards.size >= 3)
        assertEquals("…as the window before it", before.cards.map { it.said to it.height }, first.cards.map { it.said to it.height })
        steady(frames, "in the window opened again")
    }

    /**
     * Should the carriers never come, the launch window goes all the same once the stores have been made for
     * [MainActivity.LAUNCH_HOLD_MILLIS], and not before.
     */
    @Test
    fun theLaunchWindowGoesInTheEndWithoutTheCarriers() {
        brandMillis = TimeUnit.MINUTES.toMillis(10)
        open()
        framesUntil("the stores") { app.container.value != null }
        val held = (MainActivity.LAUNCH_HOLD_MILLIS / FRAME_MILLIS).toInt()
        repeat(held - 5) { frame() }
        assertTrue("The launch window stays while the carriers are awaited: ${frames.firstOrNull()}", frames.isEmpty())
        repeat(10) { frame() }
        assertTrue("The launch window goes in the end", frames.isNotEmpty())
    }

    /** No frame of `drawn` has the top bar or a card anywhere else than the first one has it. */
    private fun steady(drawn: List<Frame>, where: String) {
        assertTrue("Frames are drawn $where", drawn.isNotEmpty())
        val first = drawn.first()
        drawn.forEachIndexed { index, frame ->
            assertEquals("Frame $index $where, against the first one: $first", first.top to first.cards, frame.top to frame.cards)
        }
    }

    /** Opens a window the way the launcher does. */
    private fun open(): ActivityController<MainActivity> =
        Robolectric.buildActivity(MainActivity::class.java, Intent.makeMainActivity(ComponentName(app, MainActivity::class.java)))
            .also { windows += it }
            .setup()

    /** The app's cold start: its first window opened, then its frames until the brand data is decoded and after. */
    private fun launch(): ActivityController<MainActivity> {
        val window = open()
        framesUntil("the carriers") { made?.catalog?.value?.let { CarrierIdentities.loaded(it) } != null }
        settle()
        return window
    }

    /** One frame's time. */
    private fun frame() = shadowOf(Looper.getMainLooper()).idleFor(ShadowChoreographer.getFrameDelay())

    /** About two seconds' frames. */
    private fun settle() = repeat(125) { frame() }

    /** Frames until `done`, a frame's time apart for the background threads too, as on the phone. */
    private fun framesUntil(what: String, done: () -> Boolean) {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(30)
        while (!done()) {
            if (System.nanoTime() > deadline) fail("Timed out waiting for $what; frames: ${frames.takeLast(3)}")
            frame()
            Thread.sleep(FRAME_MILLIS)
        }
    }

    private fun frame(activity: MainActivity): Frame {
        var top: Float? = null
        var list = false
        val cards = mutableListOf<Card>()
        fun walk(node: SemanticsNode, inList: Boolean) {
            val config = node.config
            val tag = config.getOrNull(SemanticsProperties.TestTag)
            if (tag == ADD_BUTTON) top = node.boundsInRoot.top
            if (tag == PARCEL_LIST) list = true
            val said = config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString(" ")
            if (inList && said != null && SemanticsActions.OnClick in config) {
                cards += Card(said, node.positionInRoot.y, node.size.height.toFloat())
            }
            node.children.forEach { walk(it, inList || tag == PARCEL_LIST) }
        }
        roots(activity.window.decorView).forEach { walk(it.semanticsOwner.unmergedRootSemanticsNode, false) }
        return Frame(activity, app.container.value != null, top, list, cards)
    }

    private fun roots(view: View): List<ViewRootForTest> = when (view) {
        is ViewRootForTest -> listOf(view)
        is ViewGroup -> (0 until view.childCount).flatMap { roots(view.getChildAt(it)) }
        else -> emptyList()
    }

    private companion object {
        /** The status bar's and the navigation bar's heights, in pixels. */
        const val STATUS_BAR = 48
        const val NAVIGATION_BAR = 32

        /** How long the brand data takes to decode after the session is read, in milliseconds. */
        const val BRAND_MILLIS = 300L

        /** A frame's time, in milliseconds. */
        val FRAME_MILLIS = ShadowChoreographer.getFrameDelay().toMillis()
    }
}
