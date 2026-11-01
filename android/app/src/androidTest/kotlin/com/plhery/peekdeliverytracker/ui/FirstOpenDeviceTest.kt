package com.plhery.peekdeliverytracker.ui

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.pm.PackageManager
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.camera.view.PreviewView
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.Density
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.platform.CameraLabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_PASTE
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_DEMO
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_FIELD
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_PASTE
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_SCAN
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_SCROLL
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.FakeLabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.LabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER_CANCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith

/**
 * The first open a build with a server shows (A-S13, A-S14, A-ST05, A-P04), on this device's own window, its bars and
 * density the device's: the debug build has none, so the app shows only the demo's variant, and this draws the one a
 * release shows (`store.tracksWithoutAccount`) over the tabs of a guest who follows nothing. Its field, Scan on the
 * back camera and Paste and track, and no demo; Scan opens Add on the camera, Paste and track opens Add with what the
 * clipboard holds once it is tapped, counting `parcel-paste`, or empty, and the field opens Add ready to type; no Scan
 * where nothing scans; and the field's variant dark and at the largest text the first open takes, its composition's
 * own Density, not the phone's setting. Each saved as firstopen/<test>.png in the test package's external files, beside
 * the link sheet's linkwalk/, which adb pulls. On the deliveries' stores in memory ([FakeDeliveries]): nothing goes to
 * the network. Under Reduce Motion, the journey at its still step: otherwise the carriers roll frame after frame on a
 * phone's screen, and the test's clock would never be idle. Each test keeps Zurich's time as the JVM's do and puts the
 * zone back after; the preferences files the fakes write go once its window has, and a clipboard it set is cleared.
 */
@RunWith(AndroidJUnit4::class)
class FirstOpenDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val name = TestName()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(name).around(MadePreferences()).around(ZurichTime()).around(compose)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val shots = DeviceShots(compose, "firstopen")

    /** A camera on the device's back, which CameraLabelScanner scans with. */
    private val backCamera = context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)

    /** The app's clipboard: what it sets, the app reads as its own, with no toast saying it was read. */
    private val clipboard = checkNotNull(context.getSystemService(ClipboardManager::class.java))
    private var clipboardSet = false

    /** Made once the test's rules have started, so that its preferences files are among those it made. */
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
    fun clearClipboard() {
        if (clipboardSet) clipboard.clearPrimaryClip()
    }

    private fun text(key: String) = localizer.text(key)

    /**
     * The first open over the tabs of a guest who follows nothing, the build tracking without an account, scanning with
     * [scanner] (the back camera's, allowed, where there is one), edge to edge as the app's window, whose keyboard
     * resizes its room as MainActivity's manifest asks (`adjustResize`) rather than panning it.
     */
    private fun show(scanner: LabelScanner = CameraLabelScanner, dark: Boolean = false, fontScale: Float = 1f) {
        source = FakeDeliveries(emptyList(), AuthenticationState.Welcome).apply { tracksWithoutAccount = true }
        if (scanner === CameraLabelScanner && backCamera) {
            instrumentation.uiAutomation.grantRuntimePermission(context.packageName, Manifest.permission.CAMERA)
        }
        compose.waitsForMaps()
        compose.runOnUiThread {
            val window = compose.activity.window
            compose.activity.edgeToEdge(dark)
            @Suppress("DEPRECATION")
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            ViewCompat.setWindowInsetsAnimationCallback(window.decorView, keyboardSlides)
        }
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                RootHost(dark = dark, reduceMotion = true, source = source, scanner = scanner)
            }
        }
        compose.waitForIdle()
    }

    /** What a tap set off played out: Add coming up, and the keyboard or the camera 300 ms after. */
    private fun settle() {
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(SETTLE_MILLIS)
        compose.waitForIdle()
    }

    /** Whether anything has [tag], TalkBack reaching it or not, the copies that only measure the question included. */
    private fun exists(tag: String) = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** Whether TalkBack reaches what has [tag]: drawn, and not under the first open. */
    private fun reachable(tag: String) = compose.onAllNodesWithTag(tag).fetchSemanticsNodes().isNotEmpty()

    /** Whether TalkBack reads `text` somewhere. */
    private fun says(text: String) = compose.onAllNodes(hasText(text)).fetchSemanticsNodes().isNotEmpty()

    private fun tabsReachable() =
        compose.onAllNodes(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).fetchSemanticsNodes().isNotEmpty()

    /** A finger on what has [tag], scrolled into the window first where the first open scrolls, as a short phone's may. */
    private fun tap(tag: String) {
        val node = compose.onNodeWithTag(tag)
        if (reachable(FIRST_OPEN_SCROLL)) node.performScrollTo()
        node.assertIsDisplayed().performClick()
    }

    /** What the clipboard holds: `text`, or nothing. */
    private fun clip(text: String?) {
        clipboardSet = true
        if (text == null) {
            clipboard.clearPrimaryClip()
            return
        }
        val clip = ClipData.newPlainText("", text)
        // An emulator's SystemUI shows no chip for it at the screen's corner, as for the clipboard its host shares.
        clip.description.extras = PersistableBundle().apply { putBoolean(SUPPRESS_CLIPBOARD_OVERLAY, true) }
        clipboard.setPrimaryClip(clip)
    }

    private fun fieldText(): String? =
        compose.onNodeWithTag(ADD_TRACKING).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun onUi(read: () -> Boolean): Boolean {
        var value = false
        compose.runOnUiThread { value = read() }
        return value
    }

    /** The input method takes the window's text: a field has asked for the keyboard, shown or not. */
    private fun acceptingText() = onUi { compose.activity.getSystemService(InputMethodManager::class.java).isAcceptingText }

    private fun keyboardShown() =
        onUi { ViewCompat.getRootWindowInsets(compose.activity.window.decorView)?.isVisible(WindowInsetsCompat.Type.ime()) == true }

    /**
     * Add's number field has the focus and asked for the keyboard; then the keyboard has come up and finished its
     * slide, where the device shows one: a phone with a keyboard of its own, such as the smallest AVD's, shows none.
     */
    private fun assertReadyToType() {
        compose.onNodeWithTag(ADD_TRACKING).assertIsFocused()
        compose.waitUntil(5_000) { acceptingText() }
        runCatching { compose.waitUntil(KEYBOARD_MILLIS) { keyboardShown() } }
        compose.waitUntil(5_000) { onUi { keyboardSlides.playing == 0 } }
    }

    /** The preview of the camera in the window, if any. */
    private fun preview(view: View): PreviewView? = when (view) {
        is PreviewView -> view
        is ViewGroup -> (0 until view.childCount).firstNotNullOfOrNull { preview(view.getChildAt(it)) }
        else -> null
    }

    private fun streaming() = onUi { preview(compose.activity.window.decorView)?.previewStreamState?.value == PreviewView.StreamState.STREAMING }

    /**
     * Over the tabs, which with the empty deliveries are drawn under it out of TalkBack's reach: the field, read as the
     * field it leads to, over Paste and track and under the lead that goes with them, Scan in the field where the back
     * camera scans, and no demo.
     */
    private fun assertTheFieldsVariant() {
        compose.onNodeWithTag(FIRST_OPEN).assertIsDisplayed()
        assertFalse(tabsReachable())
        assertTrue(exists(EMPTY_PASTE))
        assertFalse(reachable(EMPTY_PASTE))
        compose.onNodeWithTag(FIRST_OPEN_FIELD).assertContentDescriptionEquals(text("add.tracking"))
        compose.onNodeWithTag(FIRST_OPEN_PASTE).assertExists()
        assertTrue(says(text("native.pasteLead")))
        assertTrue(says(text("landing.pasteAndTrack")))
        assertEquals(backCamera, reachable(FIRST_OPEN_SCAN))
        if (backCamera) compose.onNodeWithTag(FIRST_OPEN_SCAN).assertContentDescriptionEquals(text("add.scan"))
        assertFalse(exists(FIRST_OPEN_DEMO))
        assertFalse(says(text("arrival.welcomeSubtitle")))
    }

    private fun shot() = shots.take(name.methodName)

    @Test
    fun withAServer() {
        show()
        assertTheFieldsVariant()
        shot()
    }

    @Test
    fun withAServerInDark() {
        show(dark = true)
        assertTheFieldsVariant()
        shot()
    }

    /** It grows no further than accessibility 1. */
    @Test
    fun withAServerAtTheLargestTextSize() {
        show(fontScale = 2f)
        assertTheFieldsVariant()
        shot()
    }

    /** Add opens on the camera, streaming or not yet; Cancel takes the scanner down to Add. */
    @Test
    fun scanOpensAddOnTheCamera() {
        assumeTrue("No camera on the back", backCamera)
        show()
        tap(FIRST_OPEN_SCAN)
        settle()
        // Add is under it, out of TalkBack's reach (and so of its tag) while the scanner covers it.
        compose.waitUntil(10_000) { exists(SCANNER) }
        // A moment for the camera's first frames, which the emulator's virtual scene gives.
        runCatching { compose.waitUntil(STREAM_MILLIS) { streaming() } }
        shot()
        compose.onNodeWithTag(SCANNER_CANCEL).performClick()
        settle()
        compose.waitUntil(5_000) { !exists(SCANNER) }
        assertTrue(exists(ADD_PARCEL))
        assertEquals(emptyList<String>(), source.actions)
    }

    @Test
    fun withoutAScannerThereIsNoScan() {
        show(scanner = FakeLabelScanner(usable = false))
        compose.onNodeWithTag(FIRST_OPEN_FIELD).assertIsDisplayed()
        compose.onNodeWithTag(FIRST_OPEN_PASTE).assertExists()
        assertFalse(exists(FIRST_OPEN_SCAN))
        shot()
    }

    /** The clipboard set once the first open is up: only a read on the tap finds it. */
    @Test
    fun pasteAndTrackOpensAddWithTheClipboard() {
        show()
        clip(PASTED)
        tap(FIRST_OPEN_PASTE)
        settle()
        compose.waitUntil(5_000) { exists(ADD_PARCEL) }
        assertEquals(PASTED, fieldText())
        assertEquals(listOf("parcel-paste"), source.actions)
        assertReadyToType()
        shot()
    }

    @Test
    fun pasteAndTrackWithAnEmptyClipboardOpensAddEmpty() {
        show()
        clip(null)
        tap(FIRST_OPEN_PASTE)
        settle()
        compose.waitUntil(5_000) { exists(ADD_PARCEL) }
        assertEquals("", fieldText())
        assertEquals(listOf("parcel-paste"), source.actions)
        assertReadyToType()
        shot()
    }

    /** Opened as the empty list's Add is, it counts no action. */
    @Test
    fun theFieldOpensAddToType() {
        show()
        tap(FIRST_OPEN_FIELD)
        settle()
        compose.waitUntil(5_000) { exists(ADD_PARCEL) }
        assertFalse(exists(SCANNER))
        assertEquals("", fieldText())
        assertEquals(emptyList<String>(), source.actions)
        assertReadyToType()
        shot()
    }

    private companion object {
        /** A synthetic number in a sentence, as a shop's message brings it. */
        const val PASTED = "Your parcel 1Z999AA10123456784 is on its way"

        /** SystemUI's ClipboardListener: an emulator's clipboard chip doesn't show for a clip that carries it. */
        const val SUPPRESS_CLIPBOARD_OVERLAY = "com.android.systemui.SUPPRESS_CLIPBOARD_OVERLAY"

        /** Add's 300 ms before the keyboard or the camera, and its own entrance, under Reduce Motion. */
        const val SETTLE_MILLIS = 1_000L

        /** How long a keyboard takes to start coming up, where the device shows one. */
        const val KEYBOARD_MILLIS = 3_000L

        /** How long the camera may take to stream. */
        const val STREAM_MILLIS = 5_000L
    }
}
