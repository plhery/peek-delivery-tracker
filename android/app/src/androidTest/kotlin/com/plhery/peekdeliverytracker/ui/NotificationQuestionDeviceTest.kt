package com.plhery.peekdeliverytracker.ui

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.accessibility.AccessibilityNodeInfo
import androidx.activity.ComponentActivity
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import com.plhery.peekdeliverytracker.platform.AndroidPushNotifications
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeFalse
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The question a parcel followed through a link leads to (B-P03): the store asks for the phone's notifications once the
 * parcel is followed (`askForDeviceAlerts`, DeviceParcelTests' followingALinkWithoutAnAccountAsksForNotificationsOnce),
 * through the phone's pushes ([AndroidPushNotifications.requestAuthorization]), here on this device's own window,
 * which the process's question attaches to as it is made, as MainActivity's does. On Android 13 and later, with
 * POST_NOTIFICATIONS not given yet, the system's question shows over the window; Allow, tapped as TalkBack taps it,
 * gives the answer and the permission. Android 12 has no such question: nothing shows, and the answer is the app's
 * notification setting. Saved as notificationquestion/<name>.png in the test package's external files, which adb pulls.
 *
 * Android ends an app's process as a runtime permission is taken away, so the question is asked only on an install
 * that has never been given it: run after the app is installed afresh (instrument-check's PEEK_UNINSTALL), and on its
 * own (`-e class`). A build without Firebase's settings has no pushes, so its store asks nothing as a parcel is
 * followed; the question is the same once it has them.
 */
@RunWith(AndroidJUnit4::class)
class NotificationQuestionDeviceTest {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext
    private val app = context.applicationContext as PeekApplication
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val shots = DeviceShots(compose, FOLDER)
    private val scope = MainScope()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(AttachedWindows()).around(compose)

    private val pushes = AndroidPushNotifications(context, app.notificationPermission)

    @After
    fun end() {
        scope.cancel()
    }

    @Test
    fun androidTwelveAsksNothingAndAnswersWithTheSetting() {
        assumeTrue("Android 12 and earlier", Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
        showWindow()
        val answer = ask()
        assertNoQuestionFor(QUIET_MILLIS)
        assertEquals(NotificationManagerCompat.from(context).areNotificationsEnabled(), runBlocking { answer.await() })
        assertFalse(pushes.answeredBefore)
        shots.take("B-P03-android-12")
    }

    @Test
    fun androidThirteenAsksOverTheWindowAndAllowGivesThePermission() {
        assumeTrue("Android 13 and later", Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        assumeFalse("POST_NOTIFICATIONS given already: run after the app is installed afresh", granted())
        showWindow()
        assertEquals(NotificationAuthorizationStatus.NotDetermined, runBlocking { pushes.authorizationStatus() })
        val ground = instrumentation.uiAutomation.takeScreenshot()
        val answer = ask()
        val question = awaitQuestion()
        val allow = checkNotNull(ALLOW_IDS.firstNotNullOfOrNull { id -> question.findAccessibilityNodeInfosByViewId(id).firstOrNull() }) {
            "The question's Allow among ${question.packageName}'s"
        }
        awaitDrawn(allow, ground)
        shots.take("B-P03-question", ownWindow = false)
        assertTrue("Allow tapped", allow.performAction(AccessibilityNodeInfo.ACTION_CLICK))
        val until = SystemClock.uptimeMillis() + ANSWER_MILLIS
        while (!answer.isCompleted && SystemClock.uptimeMillis() < until) SystemClock.sleep(POLL_MILLIS)
        assertTrue("Answered once Allow was tapped", answer.isCompleted)
        assertTrue("Allowed", runBlocking { answer.await() })
        assertTrue(granted())
        assertTrue(pushes.answeredBefore)
        assertEquals(NotificationAuthorizationStatus.Authorized, runBlocking { pushes.authorizationStatus() })
        compose.waitForIdle()
        assertNoQuestionFor(POLL_MILLIS)
        shots.take("B-P03-answered")
    }

    private fun showWindow() {
        compose.setContent { BasicText("Peek") }
        compose.waitForIdle()
    }

    /** The question asked from the main thread, its answer to come. */
    private fun ask(): Deferred<Boolean> {
        lateinit var asked: Deferred<Boolean>
        instrumentation.runOnMainSync { asked = scope.async(start = CoroutineStart.UNDISPATCHED) { pushes.requestAuthorization() } }
        return asked
    }

    private fun granted(): Boolean =
        ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED

    /** What the window in front holds, as TalkBack reads it, once the system's permission controller has it. */
    private fun awaitQuestion(): AccessibilityNodeInfo {
        val until = SystemClock.uptimeMillis() + QUESTION_MILLIS
        while (SystemClock.uptimeMillis() < until) {
            instrumentation.uiAutomation.rootInActiveWindow?.takeIf { it.packageName?.contains(PERMISSION_CONTROLLER) == true }?.let { return it }
            SystemClock.sleep(POLL_MILLIS)
        }
        throw AssertionError("No permission question over the window: ${instrumentation.uiAutomation.rootInActiveWindow?.packageName}")
    }

    /**
     * Once Allow is drawn where the window was, and stays: the question's window is in front before it has come in on
     * the screen.
     */
    private fun awaitDrawn(allow: AccessibilityNodeInfo, ground: Bitmap) {
        val bounds = Rect().also { allow.getBoundsInScreen(it) }
        val x = bounds.centerX().coerceIn(0, ground.width - 1)
        val y = bounds.centerY().coerceIn(0, ground.height - 1)
        val until = SystemClock.uptimeMillis() + QUESTION_MILLIS
        var last: Int? = null
        while (SystemClock.uptimeMillis() < until) {
            val pixel = instrumentation.uiAutomation.takeScreenshot()?.getPixel(x, y)
            if (pixel != null && pixel != ground.getPixel(x, y) && pixel == last) return
            last = pixel
            SystemClock.sleep(POLL_MILLIS)
        }
        throw AssertionError("The question's Allow at $bounds never drawn")
    }

    private fun assertNoQuestionFor(millis: Long) {
        val until = SystemClock.uptimeMillis() + millis
        while (SystemClock.uptimeMillis() < until) {
            val front = instrumentation.uiAutomation.rootInActiveWindow?.packageName
            assertFalse("A permission question showed: $front", front?.contains(PERMISSION_CONTROLLER) == true)
            SystemClock.sleep(POLL_MILLIS)
        }
    }

    /**
     * The process's question attached to each of the tests' windows as it is made, before it starts, as MainActivity
     * attaches it in its `onCreate`.
     */
    private inner class AttachedWindows : ExternalResource() {
        private val callbacks = object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity is ComponentActivity) app.notificationPermission.attach(activity)
            }

            override fun onActivityStarted(activity: Activity) = Unit
            override fun onActivityResumed(activity: Activity) = Unit
            override fun onActivityPaused(activity: Activity) = Unit
            override fun onActivityStopped(activity: Activity) = Unit
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
            override fun onActivityDestroyed(activity: Activity) = Unit
        }

        override fun before() = app.registerActivityLifecycleCallbacks(callbacks)

        override fun after() = app.unregisterActivityLifecycleCallbacks(callbacks)
    }

    private companion object {
        const val FOLDER = "notificationquestion"

        /** The system's permission controller, Google's or AOSP's. */
        const val PERMISSION_CONTROLLER = "permissioncontroller"

        /** The question's Allow, by the ids AOSP's and Google's controllers give it. */
        val ALLOW_IDS = listOf(
            "com.android.permissioncontroller:id/permission_allow_button",
            "com.google.android.permissioncontroller:id/permission_allow_button",
        )

        const val QUESTION_MILLIS = 10_000L
        const val ANSWER_MILLIS = 5_000L
        const val QUIET_MILLIS = 2_000L
        const val POLL_MILLIS = 100L
    }
}
