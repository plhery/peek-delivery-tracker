package com.plhery.peekdeliverytracker.ui

import android.Manifest
import android.content.pm.PackageManager
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.platform.CameraLabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER_CANCEL
import com.plhery.peekdeliverytracker.ui.deliveries.TrackingScannerCover
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The label scanner over the tabs on this device's back camera (B-S32), which the JVM tests stand in for with a fake:
 * the camera allowed, its preview streams under the bar, and Cancel takes the scanner down and lets the camera go.
 * Skipped on a device without a camera on its back. On the emulator, its virtual scene stands in front of the camera.
 */
@RunWith(AndroidJUnit4::class)
class TrackingScannerDeviceTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun aBackCameraAllowed() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        assumeTrue("No camera on the back", context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
        instrumentation.uiAutomation.grantRuntimePermission(context.packageName, Manifest.permission.CAMERA)
    }

    /** The preview in the window, if any. */
    private fun preview(): PreviewView? {
        var found: PreviewView? = null
        compose.runOnUiThread { found = find(compose.activity.window.decorView) }
        return found
    }

    private fun find(view: View): PreviewView? = when (view) {
        is PreviewView -> view
        is ViewGroup -> (0 until view.childCount).firstNotNullOfOrNull { find(view.getChildAt(it)) }
        else -> null
    }

    @Test
    fun theCameraStreamsUnderTheBarAndCancelTakesTheScannerDown() {
        val localizer = Localizer(AppLanguage.En, variant = "android")
        var up by mutableStateOf(true)
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = null) {
                PeekRoot(AuthenticationState.Welcome, localizer, onView = {}, scanner = CameraLabelScanner) {
                    Box(Modifier.fillMaxSize())
                    TrackingScannerCover(visible = up, onScan = { up = false }, onCancel = { up = false })
                }
            }
        }
        compose.waitUntil(10_000) { preview()?.previewStreamState?.value == PreviewView.StreamState.STREAMING }
        compose.onNodeWithTag(SCANNER_CANCEL).performClick()
        compose.waitUntil(5_000) { compose.onAllNodesWithTag(SCANNER).fetchSemanticsNodes().isEmpty() }
        compose.waitUntil(5_000) { preview() == null }
    }
}
