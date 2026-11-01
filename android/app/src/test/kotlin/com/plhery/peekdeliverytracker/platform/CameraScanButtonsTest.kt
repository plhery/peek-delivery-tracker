package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivityResultRegistryOwner
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.app.ActivityOptionsCompat
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PASTE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SCAN
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_ADD
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_SCAN
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER_CANCEL
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

/**
 * The scan buttons behind the scanner on a device (B-AN13, B-P01): availability read again as the window comes back,
 * so the camera allowed in the system's settings brings them back as iOS's `isAvailable` does; a refusal given while
 * the scanner is up taking every one away; and the system's question asked once each time the scanner opens, through a
 * window made again, whose answer comes to the new window. The system's questions go to the tests, which answer them as
 * it would (Robolectric's own window answers every one with yes). The camera is never allowed while the scanner is up,
 * so CameraX never binds here, where there is no camera to bind.
 */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = PHONE)
class CameraScanButtonsTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val application = ApplicationProvider.getApplicationContext<Application>()
    private val packages = shadowOf(application.packageManager)
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))
    private val questions = Questions()
    private val window = object : ActivityResultRegistryOwner {
        override val activityResultRegistry: ActivityResultRegistry = questions
    }

    @Before
    fun aBackCameraNotYetAskedFor() {
        packages.setSystemFeature(PackageManager.FEATURE_CAMERA, true)
        packages.setShouldShowRequestPermissionRationale(Manifest.permission.CAMERA, false)
        // What an earlier test's answers left in the process, which the camera allowed clears.
        shadowOf(application).grantPermissions(Manifest.permission.CAMERA)
        CameraLabelScanner.available(compose.activity, compose.activity)
        shadowOf(application).denyPermissions(Manifest.permission.CAMERA)
    }

    /** The empty list, signed in, scanning with the device's scanner. */
    private fun show(recreation: Recreation? = null) {
        val source = FakeDeliveries(emptyList(), reader)
        val content = @Composable {
            CompositionLocalProvider(LocalActivityResultRegistryOwner provides window) {
                RootHost(state = reader, source = source, scanner = CameraLabelScanner)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performClick()
        settle()
    }

    /** "Don't allow", which the system remembers, given to the window. */
    private fun refuse(question: Question) {
        packages.setShouldShowRequestPermissionRationale(Manifest.permission.CAMERA, true)
        assertTrue(compose.runOnUiThread { questions.dispatchResult(question.code, false) })
        settle()
    }

    @Test
    fun theCameraAllowedInTheSettingsBringsTheScanButtonsBackAsTheWindowComesBack() {
        // Refused before, as the system remembers.
        packages.setShouldShowRequestPermissionRationale(Manifest.permission.CAMERA, true)
        show()
        assertFalse(exists(EMPTY_SCAN))
        tap(EMPTY_ADD)
        assertTrue(exists(ADD_PASTE))
        assertFalse(exists(ADD_SCAN))
        // Allowed in the system's settings while the app is away, and back.
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        shadowOf(application).grantPermissions(Manifest.permission.CAMERA)
        packages.setShouldShowRequestPermissionRationale(Manifest.permission.CAMERA, false)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        settle()
        assertTrue(exists(ADD_SCAN))
        tap(ADD_CLOSE)
        assertTrue(exists(EMPTY_SCAN))
        assertEquals(emptyList<Question>(), questions.asked)
    }

    @Test
    fun aRefusalWhileTheScannerIsUpTakesEveryScanButtonAway() {
        show()
        tap(EMPTY_SCAN)
        assertTrue(exists(SCANNER))
        val question = questions.asked.single()
        assertEquals(Manifest.permission.CAMERA, question.permission)
        refuse(question)
        // The scanner, black, stays up until Cancel; Add under it has no scan button left, nor the list under Add.
        assertTrue(exists(SCANNER))
        tap(SCANNER_CANCEL)
        assertTrue(exists(ADD_PASTE))
        assertFalse(exists(ADD_SCAN))
        tap(ADD_CLOSE)
        assertTrue(exists(EMPTY_ADD))
        assertFalse(exists(EMPTY_SCAN))
    }

    @Test
    fun theQuestionIsAskedOnceThroughAWindowMadeAgainWhoseAnswerComesToTheNewWindow() {
        val recreation = Recreation(compose)
        show(recreation)
        tap(EMPTY_SCAN)
        val question = questions.asked.single()
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(exists(SCANNER))
        assertEquals(listOf(question), questions.asked)
        refuse(question)
        tap(SCANNER_CANCEL)
        assertTrue(exists(ADD_PASTE))
        assertFalse(exists(ADD_SCAN))
    }
}

/** A permission the system was asked for, under its request's code. */
private data class Question(val code: Int, val permission: Any?)

/** The window's results, which keep each question the system is asked for the test to answer. */
private class Questions : ActivityResultRegistry() {
    val asked = mutableListOf<Question>()

    override fun <I, O> onLaunch(requestCode: Int, contract: ActivityResultContract<I, O>, input: I, options: ActivityOptionsCompat?) {
        asked += Question(requestCode, input)
    }
}
