package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityService
import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.Settings
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.Density
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.DEVICE_ACCOUNT_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ACCOUNT_DATA
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ALERT
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ALERT_ACTION
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ALERT_CANCEL
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ALERT_FIELD
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELETE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_EMAIL
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_EMAIL_EXAMPLE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_UPDATES
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_EXPORT
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_FEEDBACK
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_FORGET_ALL
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_HOME_PAGE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SIGN_IN
import com.plhery.peekdeliverytracker.ui.settings.appearanceTag
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.PeekColors
import com.plhery.peekdeliverytracker.ui.theme.appearanceSetting
import java.io.File
import java.util.UUID
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith

/**
 * Settings (C-S38 to C-S45) on this device's own window, its bars, density and keyboard the device's, for the synthetic
 * reader@example.com on fake deliveries, which reach no server: opened from the account button; the data page pushed
 * and the system's back taking it off; the appearance set to dark, the window following it, and back to light; the
 * delete alert with the keyboard up on its field and the address typed; the export handed to the system's share sheet,
 * which the back closes; a guest's offers of an account, in the list (A-S19) and in Settings, and the question before
 * the parcels are forgotten; Delivery updates pushed, with the delivery email and its example; the home page and the
 * feedback mail handed to the system, kept from opening. Each saved as settings/<test>.png in the test package's
 * external files, which adb pulls.
 * Under Reduce Motion, as the other device tests are. Each test puts back the appearance it found and deletes the
 * export it made; the preferences files the fakes write go once its window has.
 */
@RunWith(AndroidJUnit4::class)
class SettingsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val name = TestName()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(name).around(MadePreferences()).around(ZurichTime()).around(compose)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val shots = DeviceShots(compose, "settings")
    private val store = AppearanceStore.of(instrumentation.targetContext)
    private val appearanceBefore = store.appearance.value
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))
    private lateinit var source: FakeDeliveries

    /** The keyboard's slides in or out still playing, as the window hears of them. */
    private val keyboardSlides = object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_CONTINUE_ON_SUBTREE) {
        var playing = 0

        override fun onPrepare(animation: WindowInsetsAnimationCompat) {
            if (animation.typeMask and WindowInsetsCompat.Type.ime() != 0) playing++
        }

        override fun onProgress(insets: WindowInsetsCompat, runningAnimations: List<WindowInsetsAnimationCompat>) = insets

        override fun onEnd(animation: WindowInsetsAnimationCompat) {
            if (animation.typeMask and WindowInsetsCompat.Type.ime() != 0) playing--
        }
    }

    @After
    fun putBack() {
        store.set(appearanceBefore)
        File(instrumentation.targetContext.cacheDir, "exports/peek-export-2026-05-01.json").delete()
    }

    /**
     * The deliveries of [session] (the reader's unless said), edge to edge as the app's window, whose keyboard resizes
     * its room (`adjustResize`), and whose theme and bars follow the appearance as MainActivity's do; Settings opened
     * from the account button where [open]. [prepare] sets the fakes up before the window shows them.
     */
    private fun show(
        fontScale: Float = 1f,
        session: AuthenticationState = reader,
        open: Boolean = true,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        store.set(AppAppearance.Light)
        source = FakeDeliveries(DemoCards.parcels(), session).apply(prepare)
        compose.waitsForMaps()
        compose.runOnUiThread {
            val window = compose.activity.window
            @Suppress("DEPRECATION")
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            ViewCompat.setWindowInsetsAnimationCallback(window.decorView, keyboardSlides)
        }
        compose.setContent {
            val night = isSystemInDarkTheme()
            val dark = appearanceSetting().isDark(night)
            DisposableEffect(dark, night) {
                compose.activity.edgeToEdge(dark)
                onDispose {}
            }
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                RootHost(state = session, dark = dark, reduceMotion = true, source = source)
            }
        }
        compose.waitForIdle()
        if (open) openSettings()
    }

    private fun openSettings() {
        compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
        compose.waitUntil(5_000) { exists(SETTINGS_SHEET) }
        awaitStill()
    }

    /**
     * [body] with the views the app asks the system for (a page in the browser, a mail) kept here and not started, so
     * nothing leaves the device and no browser covers the window; it reads what was asked.
     */
    private fun keepingViews(body: (asked: () -> List<Intent>) -> Unit) {
        val asked = mutableListOf<Intent>()
        val monitor = object : Instrumentation.ActivityMonitor() {
            override fun onStartActivity(intent: Intent): Instrumentation.ActivityResult? {
                if (intent.action != Intent.ACTION_VIEW && intent.action != Intent.ACTION_SENDTO) return null
                synchronized(asked) { asked += intent }
                return Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null)
            }
        }
        instrumentation.addMonitor(monitor)
        try {
            body { synchronized(asked) { asked.toList() } }
        } finally {
            instrumentation.removeMonitor(monitor)
        }
    }

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty()

    private fun onUi(read: () -> Boolean): Boolean {
        var value = false
        compose.runOnUiThread { value = read() }
        return value
    }

    /** A finger on what has [tag], scrolled into the window first. */
    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().assertIsDisplayed().performClick()
        compose.waitForIdle()
    }

    private fun acceptingText() = onUi { compose.activity.getSystemService(InputMethodManager::class.java).isAcceptingText }

    private fun keyboardShown() =
        onUi { ViewCompat.getRootWindowInsets(compose.activity.window.decorView)?.isVisible(WindowInsetsCompat.Type.ime()) == true }

    /** Whether the device shows a keyboard on the screen for a field, as the sign-in's device test asks. */
    private fun showsAKeyboard(): Boolean {
        val context = compose.activity
        val hardware = context.resources.configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO
        return !hardware || Settings.Secure.getInt(context.contentResolver, "show_ime_with_hard_keyboard", 0) != 0
    }

    /**
     * The keyboard up where the device shows one ([showsAKeyboard]). Where a hardware keyboard is open the system may
     * still bring the soft one up, late, as Android 17 does on the tablet, so that is given the same time, without failing
     * where it stays down.
     */
    private fun awaitTheKeyboard() {
        if (showsAKeyboard()) {
            compose.waitUntil(KEYBOARD_MILLIS) { keyboardShown() }
        } else {
            runCatching { compose.waitUntil(KEYBOARD_MILLIS) { keyboardShown() } }
        }
    }

    /** The window's height above the keyboard, in pixels: all of it where none shows. */
    private fun roomAboveTheKeyboard(): Int {
        var room = 0
        compose.runOnUiThread {
            val view = compose.activity.window.decorView
            val ime = ViewCompat.getRootWindowInsets(view)?.getInsets(WindowInsetsCompat.Type.ime())?.bottom ?: 0
            room = view.height - ime
        }
        return room
    }

    /** No keyboard slide playing, and the UI idle. */
    private fun awaitStill() {
        compose.waitUntil(5_000) { onUi { keyboardSlides.playing == 0 } }
        compose.waitForIdle()
    }

    /** The screen as [name], once still; Settings' ground at its leading edge, halfway down, is the [dark] theme's. */
    private fun shot(dark: Boolean = false) {
        awaitStill()
        val screen = shots.take(name.methodName)
        val sheet = compose.onNodeWithTag(SETTINGS_SHEET).fetchSemanticsNode().boundsInWindow
        val x = sheet.left.toInt() + 4
        val y = ((sheet.top + minOf(sheet.bottom, roomAboveTheKeyboard().toFloat())) / 2).toInt()
        val ground = PeekColors(dark).background
        val drawn = Color(screen.getPixel(x, y))
        val apart = maxOf(kotlin.math.abs(drawn.red - ground.red), kotlin.math.abs(drawn.green - ground.green), kotlin.math.abs(drawn.blue - ground.blue))
        assertTrue("Settings' ground at $x, $y: $drawn for $ground", apart < 0.06f)
    }

    @Test
    fun theAccountButtonOpensSettings() {
        show()
        assertEquals("account", source.views.last())
        shot()
    }

    /** The page comes in; the system's back, a gesture or the button, takes it off and leaves Settings open. */
    @Test
    fun theSystemBackTakesTheDataPageOff() {
        show()
        tap(SETTINGS_ACCOUNT_DATA)
        compose.waitUntil(5_000) { exists(SETTINGS_EXPORT) }
        shot()
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitUntil(5_000) { !exists(SETTINGS_EXPORT) }
        assertTrue(exists(SETTINGS_SHEET))
        assertFalse(compose.activity.isFinishing)
    }

    @Test
    fun theAppearanceGoesDarkAndBack() {
        show()
        tap(appearanceTag(AppAppearance.Dark))
        assertEquals(AppAppearance.Dark, store.appearance.value)
        shot(dark = true)
        tap(appearanceTag(AppAppearance.Light))
        assertEquals(AppAppearance.Light, store.appearance.value)
        shot()
    }

    /** The alert's field takes the focus and the keyboard; the alert stands above it, and the address typed turns it on. */
    @Test
    fun theDeleteAlertWithTheKeyboardUp() {
        show()
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_DELETE)
        compose.onNodeWithTag(SETTINGS_ALERT_FIELD).assertIsFocused()
        compose.waitUntil(5_000) { acceptingText() }
        awaitTheKeyboard()
        awaitStill()
        compose.onNodeWithTag(SETTINGS_ALERT_FIELD).performTextInput("reader@example.com")
        compose.onNodeWithTag(SETTINGS_ALERT_ACTION).assertIsEnabled()
        awaitStill()
        val cancel = compose.onNodeWithTag(SETTINGS_ALERT_CANCEL).fetchSemanticsNode().boundsInWindow
        assertTrue("Above the keyboard: ${cancel.bottom} in ${roomAboveTheKeyboard()}", cancel.bottom <= roomAboveTheKeyboard())
        shots.take(name.methodName)
        tap(SETTINGS_ALERT_CANCEL)
        assertFalse(source.accountCalls.any { it.startsWith("deleteAccount") })
    }

    /**
     * A guest with parcels on the phone: the list's last row offers an account and opens the sign-in (A-S19); Settings
     * offers one in place of the account row and has no Delivery updates; the data page asks before it forgets them.
     */
    @Test
    fun aGuestIsOfferedAnAccountAndAskedBeforeForgetting() {
        show(session = AuthenticationState.Welcome, open = false) { isGuest = true }
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("deviceAccount")
        compose.onNodeWithTag(DEVICE_ACCOUNT_ROW).assertIsDisplayed()
        awaitStill()
        shots.take("${name.methodName}-list")
        compose.onNodeWithTag(DEVICE_ACCOUNT_ROW).performClick()
        compose.waitForIdle()
        assertEquals(1, source.signIns)
        openSettings()
        compose.onNodeWithTag(SETTINGS_SIGN_IN).assertIsDisplayed()
        assertFalse(exists(SETTINGS_DELIVERY_UPDATES))
        shot()
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_FORGET_ALL)
        compose.waitUntil(5_000) { exists(SETTINGS_ALERT) }
        awaitStill()
        shots.take("${name.methodName}-forget")
        tap(SETTINGS_ALERT_CANCEL)
        assertFalse(exists(SETTINGS_ALERT))
        assertFalse("forgetDeviceParcels" in source.accountCalls)
        assertTrue(exists(SETTINGS_FORGET_ALL))
    }

    /** Delivery updates pushed for a reader with the delivery email; its example asked of the system's browser. */
    @Test
    fun deliveryUpdatesShowTheEmailAndItsExample() = keepingViews { asked ->
        show { signedInWithEmail(choice = false) }
        tap(SETTINGS_DELIVERY_UPDATES)
        compose.waitUntil(5_000) { exists(SETTINGS_DELIVERY_EMAIL) }
        shot()
        tap(SETTINGS_DELIVERY_EMAIL_EXAMPLE)
        compose.waitUntil(5_000) { asked().isNotEmpty() }
        val example = asked().last().data.toString()
        assertTrue(example, example.startsWith(source.linkBaseUrl) && "/email/example" in example)
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitUntil(5_000) { !exists(SETTINGS_DELIVERY_EMAIL) }
        assertTrue(exists(SETTINGS_DELIVERY_UPDATES))
    }

    /** The home page and the feedback mail asked of the system, as the device resolves them. */
    @Test
    fun theSiteRowsAskTheSystem() = keepingViews { asked ->
        show()
        tap(SETTINGS_HOME_PAGE)
        compose.waitUntil(5_000) { asked().size == 1 }
        assertEquals(source.homePageUrl, asked()[0].data.toString())
        tap(SETTINGS_FEEDBACK)
        compose.waitUntil(5_000) { asked().size == 2 }
        assertTrue(asked()[1].data.toString().startsWith("mailto:"))
        assertTrue(onUi { compose.activity.hasWindowFocus() })
        shot()
    }

    /** The export goes to the system's share sheet as a file the chosen app may read; the back closes the sheet. */
    @Test
    fun theExportOpensTheShareSheet() {
        var chooser: Intent? = null
        val monitor = object : Instrumentation.ActivityMonitor() {
            override fun onStartActivity(intent: Intent): Instrumentation.ActivityResult? {
                if (intent.action == Intent.ACTION_CHOOSER) chooser = intent
                return null
            }
        }
        instrumentation.addMonitor(monitor)
        try {
            show()
            tap(SETTINGS_ACCOUNT_DATA)
            tap(SETTINGS_EXPORT)
            compose.waitUntil(5_000) { chooser != null }
            val send = IntentCompat.getParcelableExtra(chooser!!, Intent.EXTRA_INTENT, Intent::class.java)
            assertNotNull(send)
            assertEquals(Intent.ACTION_SEND, send!!.action)
            val uri = IntentCompat.getParcelableExtra(send, Intent.EXTRA_STREAM, Uri::class.java)!!
            assertEquals("${compose.activity.packageName}.exports", uri.authority)
            compose.waitUntil(5_000) { !onUi { compose.activity.hasWindowFocus() } }
            instrumentation.uiAutomation.waitForIdle(500, 5_000)
            shots.take(name.methodName, ownWindow = false)
            instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
            compose.waitUntil(5_000) { onUi { compose.activity.hasWindowFocus() } }
            assertTrue(exists(SETTINGS_EXPORT))
        } finally {
            instrumentation.removeMonitor(monitor)
        }
    }

    private companion object {
        /** How long a keyboard takes to start coming up, where the device shows one. */
        const val KEYBOARD_MILLIS = 3_000L
    }
}
