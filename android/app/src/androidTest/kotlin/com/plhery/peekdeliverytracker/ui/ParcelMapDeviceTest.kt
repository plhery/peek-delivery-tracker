package com.plhery.peekdeliverytracker.ui

import android.content.ContextWrapper
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.pinch
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.GlobeCamera
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.MAP_COVER_MAP
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_GLOBE
import com.plhery.peekdeliverytracker.ui.map.LocalMapCameraWatch
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.rememberCarrierIdentities
import java.io.File
import java.io.IOException
import kotlinx.coroutines.cancel
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Two fingers on the opened map on this device (A-G14), which the JVM tests give through Robolectric's windows: drawn
 * apart, they zoom the globe in about the point between them, and its picture changes. The deliveries are the demo's,
 * entered on stores made offline for this test alone, their preferences and files its own, never the process's.
 */
@RunWith(AndroidJUnit4::class)
class ParcelMapDeviceTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val app = instrumentation.targetContext.applicationContext as PeekApplication
    private val catalog = CarrierCatalog.shared
    private var container: AppContainer? = null

    /** Each camera the opened map was composed with. */
    private val opened = mutableListOf<GlobeCamera>()

    /** The folder the test's stores keep their files in, which [undo] deletes. */
    private val scratch = File(app.cacheDir, "ParcelMapDeviceTest")

    /** Each preferences file the test's stores opened, by the name they asked for. */
    private val preferences = mutableSetOf<String>()

    /** The app, but for the preferences and folders its stores keep, which are this test's own. */
    private val context = object : ContextWrapper(app) {
        override fun getSharedPreferences(name: String, mode: Int): SharedPreferences {
            synchronized(preferences) { preferences += name }
            return super.getSharedPreferences("$PREFIX$name", mode)
        }

        override fun getFilesDir() = File(scratch, "files").apply { mkdirs() }

        override fun getCacheDir() = File(scratch, "cache").apply { mkdirs() }
    }

    @After
    fun undo() {
        // On the main thread, where the stores install their catalog: none comes after the process's is back.
        instrumentation.runOnMainSync {
            container?.scope?.cancel()
            CarrierCatalog.install(catalog)
        }
        synchronized(preferences) { preferences.forEach { app.deleteSharedPreferences("$PREFIX$it") } }
        scratch.deleteRecursively()
    }

    private fun exists(tag: String) = try {
        compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    } catch (_: IllegalStateException) {
        false
    }

    /** Waits up to `millis` for a node `matcher` finds, else fails with the words the screen holds instead. */
    private fun waitFor(matcher: SemanticsMatcher, millis: Long) {
        try {
            compose.waitUntil(millis) { compose.onAllNodes(matcher).fetchSemanticsNodes().isNotEmpty() }
        } catch (e: ComposeTimeoutException) {
            val described = compose.onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsProperties.ContentDescription))
                .fetchSemanticsNodes().flatMap { it.config[SemanticsProperties.ContentDescription] }
            val texts = compose.onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsProperties.Text))
                .fetchSemanticsNodes().flatMap { node -> node.config[SemanticsProperties.Text].map { it.text } }
            throw AssertionError("No ${matcher.description} in $millis ms. Described: $described. Texts: $texts.", e)
        }
    }

    @Test
    fun twoFingersDrawnApartZoomTheOpenedGlobeIn() {
        val made = AppContainer(
            context, BuildSettings.configuration.copy(mode = AppConfiguration.Mode.Demo),
            http = OkHttpClient.Builder().addInterceptor { throw IOException("No network in this test") }.build(),
        )
        container = made
        // The demo, as its button on the empty list enters it: a guest's list is empty on a phone that follows nothing.
        compose.runOnUiThread {
            made.session.enterDemo()
            made.start(compose.activity.lifecycle)
        }
        compose.setContent {
            // The liveries from the stores' catalog, as MainActivity's: a card draws nothing without them.
            val identities = rememberCarrierIdentities(made.catalog.collectAsState().value)
            CompositionLocalProvider(LocalMapCameraWatch provides { interactive, _, camera, _ -> if (interactive) opened += camera }) {
                PeekTheme(dark = false, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = identities) {
                    PeekRoot(made, DeliveriesInbox())
                }
            }
        }
        // The demo's parcel on its way to Zürich, which the list shows first, as Next up.
        val card = hasContentDescription("New trainers", substring = true) and hasClickAction()
        waitFor(card, 20_000)
        compose.onAllNodes(card).onFirst().performClick()
        compose.waitUntil(10_000) { exists(PAGE_GLOBE) }
        compose.onNodeWithTag(PAGE_GLOBE).performClick()
        compose.waitUntil(10_000) { exists(MAP_COVER_MAP) && opened.isNotEmpty() }
        compose.waitForIdle()

        val map = compose.onNodeWithTag(MAP_COVER_MAP)
        val start = opened.last()
        val before = map.captureToImage().toPixelMap().buffer.copyOf()
        // 200 pixels apart, then 400, high on the map and clear of its summary.
        map.performTouchInput {
            val middle = Offset(centerX, height * 0.3f)
            pinch(
                middle - Offset(100f, 0f), middle - Offset(200f, 0f),
                middle + Offset(100f, 0f), middle + Offset(200f, 0f),
                durationMillis = 400,
            )
        }
        compose.waitForIdle()
        val zoomed = opened.last()
        // Twice as close, less what the touch slop takes before the pinch starts.
        val ratio = zoomed.scale / start.scale
        assertTrue("Zoomed ×$ratio", ratio in 1.7..2.0)
        assertEquals(start.center, zoomed.center)
        assertFalse(before.contentEquals(map.captureToImage().toPixelMap().buffer))
    }

    private companion object {
        /** Before each preferences file's name: the app's own stay as they were. */
        const val PREFIX = "ParcelMapDeviceTest."
    }
}
