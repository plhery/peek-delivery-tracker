package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.app.Application
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf

/**
 * `LabelScanner.isAvailable` on a device (B-AN13, B-P01): a camera on the back, not disabled by the device's policy,
 * and the camera allowed or not refused; a refusal, remembered by the system or not, takes the scanner away until the
 * camera is allowed.
 */
@RunWith(AndroidJUnit4::class)
class CameraLabelScannerTest {
    private val application = ApplicationProvider.getApplicationContext<Application>()
    private val packages = shadowOf(application.packageManager)
    private lateinit var activity: ComponentActivity

    @Before
    fun aBackCameraNotYetAskedFor() {
        activity = Robolectric.buildActivity(ComponentActivity::class.java).setup().get()
        packages.setSystemFeature(PackageManager.FEATURE_CAMERA, true)
        packages.setShouldShowRequestPermissionRationale(Manifest.permission.CAMERA, false)
        // What an earlier test's answers left in the process, which the camera allowed clears.
        allow(true)
        CameraLabelScanner.available(activity, activity)
        allow(false)
    }

    private fun allow(allowed: Boolean) {
        if (allowed) shadowOf(application).grantPermissions(Manifest.permission.CAMERA) else shadowOf(application).denyPermissions(Manifest.permission.CAMERA)
    }

    private fun available() = CameraLabelScanner.available(activity, activity)

    @Test
    fun aBackCameraScansOnceAllowedAndBeforeItIsAskedFor() {
        assertTrue(available())
        allow(true)
        assertTrue(available())
    }

    @Test
    fun withoutACameraOnTheBackOrWithoutAWindowToAskInNothingScansThatCannot() {
        packages.setSystemFeature(PackageManager.FEATURE_CAMERA, false)
        allow(true)
        assertFalse(available())
        // Without a window, nothing tells a refusal apart: the system asks as the scanner opens.
        packages.setSystemFeature(PackageManager.FEATURE_CAMERA, true)
        allow(false)
        assertTrue(CameraLabelScanner.available(activity, null))
    }

    @Test
    fun aCameraTheDevicesPolicyDisablesDoesNotScan() {
        allow(true)
        val policy = application.getSystemService(DevicePolicyManager::class.java)
        shadowOf(policy).setActiveAdmin(ADMIN)
        policy.setCameraDisabled(ADMIN, true)
        assertFalse(available())
    }

    @Test
    fun aRefusalTheSystemRemembersTakesTheScannerAwayUntilTheCameraIsAllowed() {
        packages.setShouldShowRequestPermissionRationale(Manifest.permission.CAMERA, true)
        CameraLabelScanner.answered(false, activity)
        assertFalse(available())
        // Allowed in the system's settings, and back.
        allow(true)
        assertTrue(available())
    }

    @Test
    fun aQuestionTurnedAwayOrRefusedForGoodTakesTheScannerAwayUntilTheCameraIsAllowed() {
        // Refused with no rationale to show: turned away, or "Don't allow" a second time.
        CameraLabelScanner.answered(false, activity)
        assertFalse(available())
        allow(true)
        assertTrue(available())
        // A permission given once, gone again: the scanner, whose system asks again.
        allow(false)
        assertTrue(available())
    }

    @Test
    fun aCameraAllowedLeavesTheScanner() {
        allow(true)
        CameraLabelScanner.answered(true, activity)
        assertTrue(available())
    }

    private companion object {
        val ADMIN = ComponentName("com.plhery.peekdeliverytracker", "SyntheticAdmin")
    }
}
