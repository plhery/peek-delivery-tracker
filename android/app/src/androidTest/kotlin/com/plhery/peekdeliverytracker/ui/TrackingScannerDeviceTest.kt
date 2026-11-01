package com.plhery.peekdeliverytracker.ui

import android.Manifest
import android.content.pm.PackageManager
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraInfo
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraState
import androidx.camera.core.ZoomState
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.pinch
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.platform.CameraLabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SCAN
import com.plhery.peekdeliverytracker.ui.deliveries.ContainerDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesTab
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_SCAN
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER_CAMERA
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER_CANCEL
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The label scanner over Add on this device's back camera (B-S32), which the JVM tests stand in for with a fake: on the
 * app's own stores, as a guest, the list's Scan barcode opens Add and its scanner, the camera allowed streams under the
 * bar, and Cancel takes the scanner down to Add and closes the camera; Add's own scan button does the same. Two fingers
 * drawn apart on the camera zoom it in (B-G14). Skipped on a device without a camera on its back. On the emulator, its
 * virtual scene stands in front of the camera.
 */
@RunWith(AndroidJUnit4::class)
class TrackingScannerDeviceTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private lateinit var container: AppContainer
    private lateinit var back: CameraInfo

    @Before
    fun aBackCameraAllowed() {
        val context = instrumentation.targetContext
        assumeTrue("No camera on the back", context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
        instrumentation.uiAutomation.grantRuntimePermission(context.packageName, Manifest.permission.CAMERA)
        val app = context.applicationContext as PeekApplication
        compose.runOnUiThread { app.makeContainer() }
        compose.waitUntil(20_000) { app.container.value != null }
        container = checkNotNull(app.container.value)
        val cameras = ProcessCameraProvider.getInstance(context).get()
        back = cameras.availableCameraInfos.first { it.lensFacing == CameraSelector.LENS_FACING_BACK }
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

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun camera(): CameraState.Type? {
        var type: CameraState.Type? = null
        compose.runOnUiThread { type = back.cameraState.value?.type }
        return type
    }

    /** The camera's zoom, as the preview's controller reads it. */
    private fun zoom(): ZoomState? {
        var state: ZoomState? = null
        compose.runOnUiThread { state = preview()?.controller?.zoomState?.value }
        return state
    }

    private fun showDeliveries() {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = null) {
                PeekRoot(AuthenticationState.Welcome, container.localizer, onView = {}, scanner = CameraLabelScanner) {
                    DeliveriesTab(remember { ContainerDeliveries(container) })
                }
            }
        }
        compose.waitUntil(10_000) { exists(EMPTY_SCAN) || exists(ADD_BUTTON) }
    }

    /** The scanner up with the camera streaming in it; Cancel takes it down to Add, and the camera closes. */
    private fun streamsThenCancels() {
        compose.waitUntil(10_000) { exists(SCANNER) }
        compose.waitUntil(10_000) { preview()?.previewStreamState?.value == PreviewView.StreamState.STREAMING }
        compose.waitUntil(5_000) { camera() == CameraState.Type.OPEN }
        compose.onNodeWithTag(SCANNER_CANCEL).performClick()
        compose.waitUntil(5_000) { !exists(SCANNER) }
        compose.waitUntil(5_000) { preview() == null }
        compose.waitUntil(5_000) { camera() == CameraState.Type.CLOSED }
        check(exists(ADD_PARCEL))
    }

    @Test
    fun theListsScanAndAddsOwnOpenTheCameraOverAddAndCancelClosesIt() {
        showDeliveries()
        // The empty list's Scan barcode opens Add scanning; a list with parcels has only the bar's +.
        if (exists(EMPTY_SCAN)) {
            // Its action, not a touch at its middle: on the smallest phone the floating tab bar covers the button's
            // lower half until the list scrolls, and this activity, not edge to edge, is shorter than the app's.
            compose.onNodeWithTag(EMPTY_SCAN).performSemanticsAction(SemanticsActions.OnClick)
            streamsThenCancels()
        } else {
            compose.onNodeWithTag(ADD_BUTTON).performClick()
            compose.waitUntil(5_000) { exists(ADD_SCAN) }
        }
        compose.onNodeWithTag(ADD_SCAN).performClick()
        streamsThenCancels()
    }

    @Test
    fun twoFingersDrawnApartZoomTheCameraIn() {
        showDeliveries()
        if (exists(EMPTY_SCAN)) {
            compose.onNodeWithTag(EMPTY_SCAN).performSemanticsAction(SemanticsActions.OnClick)
        } else {
            compose.onNodeWithTag(ADD_BUTTON).performClick()
            compose.waitUntil(5_000) { exists(ADD_SCAN) }
            compose.onNodeWithTag(ADD_SCAN).performClick()
        }
        compose.waitUntil(10_000) { exists(SCANNER) }
        compose.waitUntil(10_000) { preview()?.previewStreamState?.value == PreviewView.StreamState.STREAMING }
        compose.waitUntil(5_000) { zoom() != null }
        val start = checkNotNull(zoom())
        assumeTrue("The camera doesn't zoom", start.maxZoomRatio > start.minZoomRatio)
        // 200 pixels apart, then 500, about the camera's middle.
        compose.onNodeWithTag(SCANNER_CAMERA).performTouchInput {
            pinch(
                center - Offset(100f, 0f), center - Offset(250f, 0f),
                center + Offset(100f, 0f), center + Offset(250f, 0f),
                durationMillis = 400,
            )
        }
        compose.waitUntil(5_000) { (zoom()?.zoomRatio ?: 0f) > start.zoomRatio }
        compose.onNodeWithTag(SCANNER_CANCEL).performClick()
        compose.waitUntil(5_000) { camera() == CameraState.Type.CLOSED }
    }
}
