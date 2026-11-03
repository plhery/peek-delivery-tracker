package com.plhery.peekdeliverytracker.ui

import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.ShareActivity
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.ShareInbox
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.io.IOException
import kotlinx.coroutines.cancel
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * "Add to Peek" on this device (B-S70, A-L08), which the JVM tests stand in for with Robolectric's windows: text shared
 * to the app shows the card in a task of its own; Save, then Done, takes it down with its task, and the app's deliveries
 * opened then show Add filled with the text and the name shared. The deliveries are the app's own root on stores made
 * offline in demo mode for this test alone, never the process's: nothing goes to the network, and the stores, the card
 * and the draft are gone once it ends. On a synthetic number.
 */
@RunWith(AndroidJUnit4::class)
class ShareDeviceTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext
    private val app = context.applicationContext as PeekApplication
    private val catalog = CarrierCatalog.shared
    private var container: AppContainer? = null

    @Before
    fun nothingWaiting() {
        ShareInbox.consume(app.shareDefaults())
    }

    @After
    fun undo() {
        val open = cards()
        // On the main thread, where the stores install their catalog: none comes after the process's is back.
        instrumentation.runOnMainSync {
            open.forEach { it.finishAndRemoveTask() }
            container?.scope?.cancel()
            CarrierCatalog.install(catalog)
        }
        ShareInbox.consume(app.shareDefaults())
    }

    /** The share cards not yet gone. */
    private fun cards(): List<ShareActivity> {
        var found = emptyList<ShareActivity>()
        instrumentation.runOnMainSync {
            val monitor = ActivityLifecycleMonitorRegistry.getInstance()
            found = listOf(Stage.CREATED, Stage.STARTED, Stage.RESUMED, Stage.PAUSED, Stage.STOPPED)
                .flatMap { monitor.getActivitiesInStage(it) }
                .filterIsInstance<ShareActivity>()
                .filterNot(Activity::isFinishing)
        }
        return found
    }

    /** Whether a node with [tag] is drawn; false while no window has content yet (the card not up, the test's own empty). */
    private fun exists(tag: String) = try {
        compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    } catch (_: IllegalStateException) {
        false
    }

    private fun text(tag: String): String? =
        compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text)?.joinToString()

    private fun fieldText(tag: String): String? =
        compose.onNodeWithTag(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    /** The app's tasks, as Recents holds them. */
    private fun tasks(): List<Int> = context.getSystemService(ActivityManager::class.java).appTasks.mapNotNull { it.taskInfo?.taskId }

    @Test
    fun aShareShowsTheCardAndWhatItSavesOpensAddFilledInTheApp() {
        val copy = ExtensionLocalizer(app.shareDefaults().string(AppLanguage.storageKey), Device.preferredLanguages())
        // As the share sheet sends it: in a task of its own.
        val share = Intent(Intent.ACTION_SEND).setType("text/plain").setPackage(context.packageName)
            .putExtra(Intent.EXTRA_TEXT, "Your parcel 1Z999AA10123456784 is on its way")
            .putExtra(Intent.EXTRA_SUBJECT, "Running shoes")
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(share)
        compose.waitUntil(10_000) { exists(SHARE_CARD) && text(SHARE_STATUS) == copy.text("shareExtension.ready") }
        val task = cards().single().taskId

        compose.onNodeWithTag(SHARE_SAVE).performClick()
        compose.waitUntil(5_000) { text(SHARE_STATUS) == copy.text("shareExtension.saved") }
        assertEquals(copy.text("native.done"), text(SHARE_CLOSE))
        compose.onNodeWithTag(SHARE_CLOSE).performClick()
        compose.waitUntil(5_000) { !exists(SHARE_CARD) && cards().isEmpty() }
        // Its task went with it: Recents keeps none.
        compose.waitUntil(5_000) { task !in tasks() }

        // The app's deliveries, as its window shows them, taking what was saved as they come to the front.
        val made = AppContainer(
            app, BuildSettings.configuration.copy(mode = AppConfiguration.Mode.Demo),
            http = OkHttpClient.Builder().addInterceptor { throw IOException("No network in this test") }.build(),
        )
        container = made
        // In the demo, as its button enters it: whatever the phone kept from before (an account's sign-in opened, the
        // welcome) would show the arrival instead of the deliveries.
        compose.runOnUiThread {
            made.session.enterDemo()
            made.start(compose.activity.lifecycle)
        }
        val inbox = DeliveriesInbox(sharedDrafts = { ShareInbox.consume(app.shareDefaults()) })
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = null) {
                PeekRoot(made, inbox)
            }
        }
        compose.waitUntilOrLogTree("ShareDeviceTest", 20_000) { exists(ADD_PARCEL) }
        compose.waitForIdle()
        assertEquals("Your parcel 1Z999AA10123456784 is on its way", fieldText(ADD_TRACKING))
        assertEquals("Running shoes", fieldText(ADD_NAME))
    }
}
