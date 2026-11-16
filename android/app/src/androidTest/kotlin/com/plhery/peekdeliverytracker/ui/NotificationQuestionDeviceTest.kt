package com.plhery.peekdeliverytracker.ui

import android.Manifest
import android.accessibilityservice.AccessibilityService
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
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import com.plhery.peekdeliverytracker.platform.AndroidPushNotifications
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_UPDATES
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DEVICE_SWITCH
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_OPEN_SETTINGS
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import java.util.UUID
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
 * gives the answer and the permission; Don't allow twice, and Android asks no more, which Settings sends to the phone's
 * page for (X-AD07). Android 12 has no such question: nothing shows, and the answer is the app's notification setting.
 * Saved as notificationquestion/<name>.png in the test package's external files, which adb pulls.
 *
 * Android ends an app's process as a runtime permission is taken away, so the question is asked only on an install
 * that has never answered it: run each test after the app is installed afresh (instrument-check's PEEK_UNINSTALL), on
 * its own (`-e class <class>#<test>`). A build without Firebase's settings has no pushes, so its store asks nothing as
 * a parcel is followed; the question is the same once it has them.
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
        assertEquals(NotificationAuthorizationStatus.NotDetermined, status())
        assertTrue("Allowed", answer(ALLOW_IDS, "Allow", "B-P03-question"))
        assertTrue(granted())
        assertTrue(pushes.answeredBefore)
        assertEquals(NotificationAuthorizationStatus.Authorized, status())
        compose.waitForIdle()
        assertNoQuestionFor(POLL_MILLIS)
        shots.take("B-P03-answered")
    }

    /**
     * X-AD07: refused once, Android would still ask, so the phone reads as not asked yet and the app's Enable asks again;
     * refused a second time, Android asks no more and the phone reads as denied; a third question is refused at once,
     * without showing. Denied, Settings › Delivery updates offers Open Settings in place of the device's switch, which
     * opens the system's notification settings for the app; Back comes back to the app.
     */
    @Test
    fun androidThirteenRefusedTwiceAsksNoMoreAndSettingsOpensThePhonesPage() {
        assumeTrue("Android 13 and later", Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        assumeFalse("POST_NOTIFICATIONS answered already: run after the app is installed afresh", granted() || pushes.answeredBefore)
        val source = showDeliveries()
        assertEquals(NotificationAuthorizationStatus.NotDetermined, status())
        assertFalse("Refused", answer(DENY_IDS, "Don't allow", "X-AD07-question"))
        assertFalse(granted())
        assertTrue(pushes.answeredBefore)
        assertEquals(NotificationAuthorizationStatus.NotDetermined, status())
        assertFalse("Refused again", answer(DENY_IDS, "Don't allow", "X-AD07-question-again"))
        assertEquals(NotificationAuthorizationStatus.Denied, status())

        val third = ask()
        assertNoQuestionFor(QUIET_MILLIS)
        assertTrue("Refused without a question", third.isCompleted)
        assertFalse(runBlocking { third.await() })
        assertEquals(NotificationAuthorizationStatus.Denied, status())

        val denied = status()
        instrumentation.runOnMainSync { source.notificationStatus.value = denied }
        compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
        compose.waitUntil(5_000) { exists(SETTINGS_SHEET) }
        compose.onNodeWithTag(SETTINGS_DELIVERY_UPDATES).performScrollTo().performClick()
        compose.waitUntil(5_000) { exists(SETTINGS_OPEN_SETTINGS) }
        assertFalse(exists(SETTINGS_DEVICE_SWITCH))
        shots.take("X-AD07-settings-denied")
        val app = instrumentation.uiAutomation.takeScreenshot()
        compose.onNodeWithTag(SETTINGS_OPEN_SETTINGS).performScrollTo().performClick()
        val page = awaitFront(SYSTEM_SETTINGS)
        val label = context.applicationInfo.loadLabel(context.packageManager).toString()
        assertTrue("The phone's page names $label", page.findAccessibilityNodeInfosByText(label).isNotEmpty())
        awaitScreenMovedFrom(app)
        shots.take("X-AD07-phone-settings", ownWindow = false)
        assertTrue(instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK))
        awaitFront(context.packageName)
    }

    private fun showWindow() {
        compose.setContent { BasicText("Peek") }
        compose.waitForIdle()
    }

    /** A signed-in reader's deliveries on fakes, as the app's window, Settings reading what the phone allows from them. */
    private fun showDeliveries(): FakeDeliveries {
        val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))
        val source = FakeDeliveries(DemoCards.parcels(), reader).apply { signedInWithEmail(choice = null) }
        compose.waitsForMaps()
        compose.setContent { RootHost(state = reader, dark = false, reduceMotion = true, source = source) }
        compose.waitForIdle()
        return source
    }

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty()

    private fun status() = runBlocking { pushes.authorizationStatus() }

    /**
     * The question asked, and answered with its button of one of [ids] ([what]) once drawn, as TalkBack taps it, the
     * screen saved as [shot] first: the answer.
     */
    private fun answer(ids: List<String>, what: String, shot: String): Boolean {
        val ground = instrumentation.uiAutomation.takeScreenshot()
        val answer = ask()
        val question = awaitQuestion()
        val button = checkNotNull(ids.firstNotNullOfOrNull { id -> question.findAccessibilityNodeInfosByViewId(id).firstOrNull() }) {
            "The question's $what among ${question.packageName}'s"
        }
        awaitDrawn(button, ground)
        shots.take(shot, ownWindow = false)
        assertTrue("$what tapped", button.performAction(AccessibilityNodeInfo.ACTION_CLICK))
        val until = SystemClock.uptimeMillis() + ANSWER_MILLIS
        while (!answer.isCompleted && SystemClock.uptimeMillis() < until) SystemClock.sleep(POLL_MILLIS)
        assertTrue("Answered once $what was tapped", answer.isCompleted)
        return runBlocking { answer.await() }
    }

    /**
     * Once the screen shows something else than [ground] and holds still: a window in front is drawn a little after it
     * is, as it comes in.
     */
    private fun awaitScreenMovedFrom(ground: Bitmap) {
        val until = SystemClock.uptimeMillis() + QUESTION_MILLIS
        var last: Bitmap? = null
        while (SystemClock.uptimeMillis() < until) {
            val screen = instrumentation.uiAutomation.takeScreenshot()
            if (screen != null && !sameScreen(screen, ground) && last?.let { sameScreen(it, screen) } == true) return
            last = screen
            SystemClock.sleep(POLL_MILLIS)
        }
        throw AssertionError("The screen never moved from the app's window and held still")
    }

    /** Whether every eighth pixel each way of [a] and [b] is the same. */
    private fun sameScreen(a: Bitmap, b: Bitmap): Boolean {
        if (a.width != b.width || a.height != b.height) return false
        for (y in 0 until a.height step 8) {
            for (x in 0 until a.width step 8) if (a.getPixel(x, y) != b.getPixel(x, y)) return false
        }
        return true
    }

    /** What the window in front holds once it is [packageName]'s. */
    private fun awaitFront(packageName: String): AccessibilityNodeInfo {
        val until = SystemClock.uptimeMillis() + QUESTION_MILLIS
        while (SystemClock.uptimeMillis() < until) {
            instrumentation.uiAutomation.rootInActiveWindow?.takeIf { it.packageName == packageName }?.let { return it }
            SystemClock.sleep(POLL_MILLIS)
        }
        throw AssertionError("$packageName never in front: ${instrumentation.uiAutomation.rootInActiveWindow?.packageName}")
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

        /** Don't allow, the first time and once Android would ask no more after it. */
        val DENY_IDS = listOf(
            "com.android.permissioncontroller:id/permission_deny_button",
            "com.google.android.permissioncontroller:id/permission_deny_button",
            "com.android.permissioncontroller:id/permission_deny_and_dont_ask_again_button",
            "com.google.android.permissioncontroller:id/permission_deny_and_dont_ask_again_button",
        )

        /** The phone's Settings, which shows the app's notification settings. */
        const val SYSTEM_SETTINGS = "com.android.settings"

        const val QUESTION_MILLIS = 10_000L
        const val ANSWER_MILLIS = 5_000L
        const val QUIET_MILLIS = 2_000L
        const val POLL_MILLIS = 100L
    }
}
