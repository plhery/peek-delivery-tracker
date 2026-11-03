package com.plhery.peekdeliverytracker.ui

import android.content.res.Configuration
import android.os.SystemClock
import android.provider.Settings
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Density
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.ui.arrival.AUTH_DEMO
import com.plhery.peekdeliverytracker.ui.arrival.CODE_FIELD
import com.plhery.peekdeliverytracker.ui.arrival.CODE_VERIFY
import com.plhery.peekdeliverytracker.ui.arrival.EMAIL_FIELD
import com.plhery.peekdeliverytracker.ui.arrival.EMAIL_SEND
import com.plhery.peekdeliverytracker.ui.arrival.FakeAuthService
import com.plhery.peekdeliverytracker.ui.arrival.READER_CODE
import com.plhery.peekdeliverytracker.ui.arrival.READER_EMAIL
import com.plhery.peekdeliverytracker.ui.arrival.SIGN_IN
import com.plhery.peekdeliverytracker.ui.arrival.SIGN_IN_ERROR
import com.plhery.peekdeliverytracker.ui.arrival.WELCOME_BACK
import com.plhery.peekdeliverytracker.ui.arrival.signInConfiguration
import com.plhery.peekdeliverytracker.ui.arrival.signInSession
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import com.plhery.peekdeliverytracker.ui.theme.PeekColors
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * The sign-in a build with a server shows (C-S04 to C-S08), on this device's own window, its bars, density and keyboard
 * the device's: the debug build has none, so this draws the one a release shows, on the real session store with a
 * fake auth service (`https://auth.example.test`, which nothing reaches) and the synthetic reader@example.com. The
 * email step, with the keyboard up on its field; the code step on the number keyboard; a code refused; a code taken,
 * which signs in to the tabs; the back to the first open, and the system's; at the largest text the sign-in takes and
 * dark. Each saved as signin/<test>.png in the test package's external files, beside firstopen/, which adb pulls.
 * Under Reduce Motion, as the first open's device test is. Each test keeps Zurich's time and puts the zone back
 * after; the preferences files the fakes write go once its window has.
 */
@RunWith(AndroidJUnit4::class)
class SignInDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val name = TestName()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(name).around(MadePreferences()).around(ZurichTime()).around(compose)

    private val shots = DeviceShots(compose, "signin")
    private val service = FakeAuthService()
    private lateinit var session: SessionStore
    private var dark = false

    /**
     * The keyboard's height as the app's layout last read it (`WindowInsets.ime`), which reaches the window's once the
     * keyboard's slide has ended there.
     */
    @Volatile private var appKeyboard = 0

    /** The keyboard's slides and the app's readings of it, in the order they came, for a failure's message. */
    private val events = ConcurrentLinkedQueue<String>()

    /** When the keyboard last slid, the app last read a new height for it or a field began to type, in uptime milliseconds. */
    @Volatile private var lastChange = 0L

    /** A slide, a new reading or a field that began to type, which [still] waits to settle. */
    private fun note(what: String) {
        lastChange = SystemClock.uptimeMillis()
        mark(what)
    }

    /** A step of the test, in [events] alone. */
    private fun mark(what: String) {
        events.add("${SystemClock.uptimeMillis() % 100_000} $what")
    }

    /** The keyboard's slides in or out still playing, as the window hears of them. */
    private val keyboardSlides = object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_CONTINUE_ON_SUBTREE) {
        var playing = 0

        override fun onPrepare(animation: WindowInsetsAnimationCompat) {
            if (animation.typeMask and WindowInsetsCompat.Type.ime() != 0) {
                playing++
                note("slide")
            }
        }

        override fun onProgress(insets: WindowInsetsCompat, runningAnimations: List<WindowInsetsAnimationCompat>) = insets

        override fun onEnd(animation: WindowInsetsAnimationCompat) {
            if (animation.typeMask and WindowInsetsCompat.Type.ime() != 0) {
                playing--
                note("slid")
            }
        }
    }

    /**
     * The sign-in as a guest who follows nothing reaches it from the first open, email sign-in alone, edge to edge as
     * the app's window, whose keyboard resizes its room as MainActivity's manifest asks (`adjustResize`).
     */
    private fun show(dark: Boolean = false, fontScale: Float = 1f) {
        this.dark = dark
        val guest = FakeDeliveries(emptyList(), AuthenticationState.Welcome)
        compose.waitsForMaps()
        compose.runOnUiThread {
            val window = compose.activity.window
            compose.activity.edgeToEdge(dark)
            @Suppress("DEPRECATION")
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            ViewCompat.setWindowInsetsAnimationCallback(window.decorView, keyboardSlides)
        }
        compose.setContent {
            val scope = rememberCoroutineScope()
            session = remember { signInSession(scope, signInConfiguration(), service).also { it.showSignIn() } }
            val density = LocalDensity.current
            val ime = WindowInsets.ime
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(
                    Modifier.fillMaxSize().layout { measurable, constraints ->
                        val read = ime.getBottom(this)
                        if (read != appKeyboard) note("app $read")
                        appKeyboard = read
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) { placeable.place(0, 0) }
                    },
                    propagateMinConstraints = true,
                ) {
                    RootHost(dark = dark, reduceMotion = true, source = guest, session = session)
                }
            }
        }
        compose.waitForIdle()
    }

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty()

    private fun <T> onUi(read: () -> T): T {
        var value: Result<T>? = null
        compose.runOnUiThread { value = runCatching(read) }
        return checkNotNull(value).getOrThrow()
    }

    private fun acceptingText() = onUi { compose.activity.getSystemService(InputMethodManager::class.java).isAcceptingText }

    /**
     * Whether the window has a keyboard standing up, with its height: the window can call the keyboard visible a moment
     * before it knows how tall it is, as when it comes back up just after going down (the code step after the address).
     */
    private fun keyboardShown() = onUi {
        val insets = ViewCompat.getRootWindowInsets(compose.activity.window.decorView)
        insets?.isVisible(WindowInsetsCompat.Type.ime()) == true && insets.getInsets(WindowInsetsCompat.Type.ime()).bottom > 0
    }

    /** Whether the window has no keyboard standing or coming up. */
    private fun keyboardDown() = onUi {
        ViewCompat.getRootWindowInsets(compose.activity.window.decorView)?.isVisible(WindowInsetsCompat.Type.ime()) != true
    }

    /** The keyboard's height where the window knows it will stand, in pixels: none where none shows. */
    private fun windowKeyboard(): Int {
        var height = 0
        compose.runOnUiThread {
            height = ViewCompat.getRootWindowInsets(compose.activity.window.decorView)?.getInsets(WindowInsetsCompat.Type.ime())?.bottom ?: 0
        }
        return height
    }

    /** The window's height above the keyboard, in pixels: all of it where none shows. */
    private fun roomAboveTheKeyboard(): Int {
        var height = 0
        compose.runOnUiThread { height = compose.activity.window.decorView.height }
        return height - windowKeyboard()
    }

    /** A finger on what has [tag], scrolled into the window first, as the page scrolls at the largest text. */
    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().assertIsDisplayed().performClick()
    }

    /**
     * Whether the device shows a keyboard on the screen for a field: not where a hardware keyboard is connected and
     * open, unless the system's setting to show the on-screen one with it (`show_ime_with_hard_keyboard`) is on.
     */
    private fun showsAKeyboard(): Boolean {
        val context = compose.activity
        val hardware = context.resources.configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO
        return !hardware || Settings.Secure.getInt(context.contentResolver, "show_ime_with_hard_keyboard", 0) != 0
    }

    /**
     * No keyboard slide playing, the app's layout with the keyboard where the window has it (the slide has ended there
     * too), and the UI idle.
     */
    private fun awaitStill() {
        compose.waitUntil(5_000) { still() }
        compose.waitForIdle()
    }

    /**
     * No keyboard slide playing, the app's layout with the keyboard where the window has it, and neither changed for
     * [QUIET_MILLIS]: the system can hand the app the keyboard's whole height at once and start its slide from nothing
     * a moment later (the code step's keyboard, on a large window), which a check in between would take for the end.
     * A field that begins to type counts as a change too: its keyboard's slide starts a few frames later, which a check
     * at once would miss, as where a hardware keyboard is open and the on-screen one comes anyway.
     */
    private fun still() = onUi { keyboardSlides.playing == 0 } && appKeyboard == windowKeyboard() &&
        SystemClock.uptimeMillis() - lastChange >= QUIET_MILLIS

    /**
     * The field with [tag] has the focus and asked for the keyboard; then the keyboard has come up and finished its
     * slide, where the device shows one ([showsAKeyboard]), and the field stands above it, the page scrolled to keep
     * it in view. The page's last step of that scroll can come a frame after the slide has ended, so the field is
     * awaited there before it is checked. The keyboard itself can take a few seconds to come the first time a layout
     * of it is drawn (the email one, turned, on a tablet just booted), so its coming is awaited on its own first.
     */
    private fun assertTypingIn(tag: String) {
        compose.onNodeWithTag(tag).assertIsFocused()
        compose.waitUntil(5_000) { acceptingText() }
        note("typing")
        val keyboard = showsAKeyboard()
        if (keyboard) runCatching { compose.waitUntil(KEYBOARD_COMES_MILLIS) { keyboardShown() } }.onSuccess { mark("shown") }
        runCatching {
            compose.waitUntil(KEYBOARD_MILLIS) { (!keyboard || keyboardShown()) && still() && fieldBottom(tag) <= roomAboveTheKeyboard() }
        }.onSuccess { mark("above") }
        awaitStill()
        mark("still")
        if (keyboard) assertTrue("A keyboard shown: ${layoutNow()}", keyboardShown())
        val bottom = fieldBottom(tag)
        val room = roomAboveTheKeyboard()
        if (bottom <= room) return
        // What stood there, then 2 s later, and the keyboard's events: a page that got there late, or one that stays.
        val then = "Above the keyboard: $bottom in $room; ${layoutNow()}"
        Thread.sleep(2_000)
        compose.waitForIdle()
        fail("$then. 2 s later: ${fieldBottom(tag)} in ${roomAboveTheKeyboard()}; ${layoutNow()}. Events: ${events.joinToString()}")
    }

    private fun fieldBottom(tag: String) = compose.onNodeWithTag(tag).fetchSemanticsNode().boundsInWindow.bottom

    /** The page's scroll, its viewport and the window's and app's keyboard, for a failure's message. */
    private fun layoutNow(): String {
        val page = compose.onNodeWithTag(SIGN_IN).fetchSemanticsNode()
        val range = page.config.getOrNull(SemanticsProperties.VerticalScrollAxisRange)
        val root = onUi { compose.activity.findViewById<android.view.View>(android.R.id.content).height }
        return "scroll ${range?.value?.invoke()} of ${range?.maxValue?.invoke()}, page ${page.boundsInWindow}, " +
            "keyboard ${windowKeyboard()} (app $appKeyboard, slides ${onUi { keyboardSlides.playing }}), window ${onUi { compose.activity.window.decorView.height }}, content $root"
    }

    private fun typed(tag: String): String? =
        compose.onNodeWithTag(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    /**
     * The address typed and sent, which the service takes: the code step, once the keyboard that went down with the
     * address's field is down and still. Tapped while it is still up, the code's field would see it go down and come
     * back up after the test had found it standing.
     */
    private fun sendCode() {
        tap(EMAIL_FIELD)
        compose.onNodeWithTag(EMAIL_FIELD).performTextInput(READER_EMAIL)
        tap(EMAIL_SEND)
        compose.waitUntil(5_000) { exists(CODE_FIELD) }
        compose.waitUntil(KEYBOARD_MILLIS) { keyboardDown() }
        awaitStill()
    }

    /**
     * The screen once any keyboard slide has ended and the UI is idle, as [name]; where the arrival shows, its ground
     * at the window's leading edge, halfway down the room above any keyboard, is the theme's, not a window gone blank.
     */
    private fun shot(arrival: Boolean = true) {
        awaitStill()
        val screen = shots.take(name.methodName)
        if (!arrival) return
        val y = roomAboveTheKeyboard() / 2
        val ground = PeekColors(dark).background
        val drawn = Color(screen.getPixel(2, y))
        val apart = maxOf(kotlin.math.abs(drawn.red - ground.red), kotlin.math.abs(drawn.green - ground.green), kotlin.math.abs(drawn.blue - ground.blue))
        assertTrue("The arrival's ground at 2, $y: $drawn for $ground", apart < 0.06f)
    }

    @Test
    fun theEmailStep() {
        show()
        compose.onNodeWithTag(EMAIL_FIELD).assertIsDisplayed()
        shot()
    }

    @Test
    fun theEmailStepInDark() {
        show(dark = true)
        shot()
    }

    /** The page grows and scrolls; the demo is reached at its foot. */
    @Test
    fun theEmailStepAtTheLargestText() {
        show(fontScale = 2f)
        shot()
        compose.onNodeWithTag(AUTH_DEMO).performScrollTo().assertIsDisplayed()
    }

    @Test
    fun theKeyboardOnTheAddress() {
        show()
        tap(EMAIL_FIELD)
        assertTypingIn(EMAIL_FIELD)
        shot()
    }

    @Test
    fun theKeyboardOnTheAddressAtTheLargestText() {
        show(fontScale = 2f)
        tap(EMAIL_FIELD)
        assertTypingIn(EMAIL_FIELD)
        shot()
    }

    @Test
    fun theCodeStepOnTheNumberKeyboard() {
        show()
        sendCode()
        assertEquals("https://auth.example.test/auth/v1/otp", service.requests.single().url)
        tap(CODE_FIELD)
        assertTypingIn(CODE_FIELD)
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        assertEquals(READER_CODE, typed(CODE_FIELD))
        shot()
    }

    @Test
    fun theCodeStepAtTheLargestTextInDark() {
        show(dark = true, fontScale = 2f)
        sendCode()
        tap(CODE_FIELD)
        assertTypingIn(CODE_FIELD)
        shot()
    }

    @Test
    fun aCodeRefused() {
        service.verify = FakeAuthService.Answer.Refused()
        show()
        sendCode()
        tap(CODE_FIELD)
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        tap(CODE_VERIFY)
        compose.waitUntil(5_000) { exists(SIGN_IN_ERROR) }
        assertEquals(AuthenticationState.SignedOut, session.state)
        shot()
    }

    @Test
    fun aCodeTakenSignsInToTheTabs() {
        show()
        sendCode()
        tap(CODE_FIELD)
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        tap(CODE_VERIFY)
        compose.waitUntil(5_000) { exists(TAB_BAR) }
        assertEquals(READER_EMAIL, (session.state as AuthenticationState.SignedIn).user.email)
        assertFalse(exists(SIGN_IN))
        // The keyboard the code step left up goes with it, its slide down ended.
        compose.waitUntil(KEYBOARD_MILLIS) { !keyboardShown() && onUi { keyboardSlides.playing == 0 } }
        assertFalse("The keyboard has gone", keyboardShown())
        shot(arrival = false)
    }

    @Test
    fun theBackGoesToTheFirstOpen() {
        show()
        tap(WELCOME_BACK)
        compose.waitUntil(5_000) { exists(FIRST_OPEN) }
        assertEquals(AuthenticationState.Welcome, session.state)
        assertFalse(exists(SIGN_IN))
        shot(arrival = false)
    }

    /** The system's back, a gesture or the button, does what the back does and leaves the app open. */
    @Test
    fun theSystemBackGoesToTheFirstOpen() {
        show()
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitUntil(5_000) { exists(FIRST_OPEN) }
        assertEquals(AuthenticationState.Welcome, session.state)
        assertFalse(compose.activity.isFinishing)
    }

    private companion object {
        /** How long a keyboard takes to start coming up, where the device shows one. */
        const val KEYBOARD_MILLIS = 3_000L

        /** How long the keyboard stays as it is before it counts as still. */
        const val QUIET_MILLIS = 500L
        const val KEYBOARD_COMES_MILLIS = 15_000L
    }
}
