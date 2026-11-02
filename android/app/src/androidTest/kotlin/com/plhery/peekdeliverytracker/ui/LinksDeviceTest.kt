package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AuthenticationError
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.core.WebAuthenticating
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.ui.arrival.GOOGLE_TAG
import com.plhery.peekdeliverytracker.ui.arrival.SIGN_IN_ERROR
import com.plhery.peekdeliverytracker.ui.arrival.signInConfiguration
import com.plhery.peekdeliverytracker.ui.arrival.signInSession
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The app's own links on this device (A-L06, A-L07), which the JVM tests stand in for with Robolectric's windows:
 * `peekdeliverytracker://add?tracking=<text>`, sent as a browser sends it, starts the app's window with Add open on the
 * text; web sign-in opens its page in a Custom Tab over the window, and Back closes the tab and ends the sign-in as
 * cancelled, which the sign-in shows as no error. The page is a made-up address that resolves nowhere
 * (`https://auth.example.test`), so the tab shows the browser's offline page; the test is skipped on a device without a
 * browser. On the app's own stores, made offline in demo mode: nothing goes to the network. The stores are made once a
 * process, so run the class on its own (`-e class`), before any other test makes them.
 */
@RunWith(AndroidJUnit4::class)
class LinksDeviceTest {
    @get:Rule
    val compose = createEmptyComposeRule()

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext
    private val app = context.applicationContext as PeekApplication

    @After
    fun close() {
        instrumentation.runOnMainSync {
            ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
                .filterIsInstance<MainActivity>().forEach { it.finishAndRemoveTask() }
        }
    }

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun fieldText(tag: String): String? = compose.onAllNodesWithTag(tag).fetchSemanticsNodes().firstOrNull()
        ?.config?.getOrNull(SemanticsProperties.EditableText)?.text

    /** Waits on the main thread's view of things, up to `millis`, for `condition`. */
    private fun waitFor(millis: Long, condition: () -> Boolean): Boolean {
        val deadline = SystemClock.uptimeMillis() + millis
        while (SystemClock.uptimeMillis() < deadline) {
            var met = false
            instrumentation.runOnMainSync { met = condition() }
            if (met) return true
            SystemClock.sleep(100)
        }
        return false
    }

    @Test
    fun anAddLinkStartsTheAppsWindowWithAddOpenOnItsText() {
        assumeTrue("The app's stores were made before this test, on the network: run the class on its own", app.container.value == null)
        app.makeStores = { application ->
            withContext(Dispatchers.IO) {
                // Offline, in demo mode, which prepares no browser check: nothing leaves the device.
                AppContainer(
                    application, BuildSettings.configuration.copy(mode = AppConfiguration.Mode.Demo),
                    http = OkHttpClient.Builder().addInterceptor { throw IOException("No network in this test") }.build(),
                )
            }
        }
        val link = Intent(Intent.ACTION_VIEW, Uri.parse("peekdeliverytracker://add?tracking=TEST%200001"))
            .addCategory(Intent.CATEGORY_BROWSABLE).setPackage(context.packageName).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(link)
        compose.waitUntil(20_000) { fieldText(ADD_TRACKING) == "TEST 0001" }
    }

    @Test
    fun theSignInTabOpensOverTheWindowAndBackEndsTheSignInWithNoError() {
        var outcome: Result<String>? = null
        val web = WebAuthenticating { url, scheme ->
            runCatching { app.signInTab.authenticate(url, scheme) }.also { outcome = it }.getOrThrow()
        }
        lateinit var session: SessionStore
        val scenario = ActivityScenario.launch(ComponentActivity::class.java)
        lateinit var window: ComponentActivity
        scenario.onActivity { activity ->
            window = activity
            app.signInTab.attach(activity)
            activity.setContent {
                val scope = rememberCoroutineScope()
                session = remember { signInSession(scope, signInConfiguration(google = true), webAuthentication = web).also { it.showSignIn() } }
                RootHost(reduceMotion = true, source = remember { FakeDeliveries(emptyList(), AuthenticationState.Welcome) }, session = session)
            }
        }
        compose.waitUntil(10_000) { exists(GOOGLE_TAG) }
        compose.onNodeWithTag(GOOGLE_TAG).performClick()

        // The tab over the window, or no browser to open it in, which the sign-in says.
        assertTrue(waitFor(10_000) { !window.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) || outcome != null })
        assumeTrue("No browser on this device", outcome == null)
        // The browser's page for an address that resolves nowhere, then Back.
        SystemClock.sleep(1_500)
        instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)

        assertTrue(waitFor(10_000) { window.lifecycle.currentState == Lifecycle.State.RESUMED && outcome != null })
        assertTrue(outcome?.exceptionOrNull().toString(), outcome?.exceptionOrNull() is AuthenticationError.OauthCancelled)
        compose.waitForIdle()
        assertFalse("No error", exists(SIGN_IN_ERROR))
        assertEquals(null, app.signInTab.waiting)
        scenario.close()
    }
}
