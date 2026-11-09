package com.plhery.peekdeliverytracker.ui

import android.content.res.Configuration
import android.os.SystemClock
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollTo
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.KEYBOARD_MILLIS
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_CARRIER_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_QUESTION
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WHO
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_SHARE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_GIFT
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_GRABBER
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import kotlin.math.abs
import kotlin.math.max
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * A finger dragging down what scrolls takes the keyboard down with it (B-G08), as the iPhone's
 * `.scrollDismissesKeyboard(.interactively)` does, in Add (AddParcelView.swift:110), the share sheet
 * (ParcelShareSheet.swift:56) and the feedback's sheets (ParcelFeedbackViews.swift:656), on this device's own window, its
 * bars, density and keyboard the device's. With the keyboard up on a field, a finger just above the field dragged down
 * 0.8 of the keyboard's height takes the keyboard down, which goes on down once the finger lifts, and the field gives up
 * the focus with it, no caret left, as the field dismissed interactively resigns its first responder. In Add, a shorter
 * drag first, let go with the keyboard still above half its height, lets the keyboard spring back up and keeps the
 * focus, as a drag that ends shown does. Each test needs the keyboard on the screen and is skipped where the device
 * shows none (a hardware keyboard with no on-screen one beside it); each opens one keyboard and ends with it down, as
 * Gboard on an emulator can stop starting input after several in one process. The demo's parcels on fake deliveries
 * ([ParcelWalk]) whose stores answer every read in memory, for the synthetic reader@example.com: nothing goes to the
 * network. Each saved as keyboarddismiss/B-G08-<surface>-keyboard-down.png in the test package's external files, under
 * Reduce Motion.
 */
@RunWith(AndroidJUnit4::class)
class KeyboardDismissDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val touches = ScreenTouches(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    /** A parcel no carrier was found for: its page asks whether the carrier's own site shows it. */
    private val lamp: Parcel get() = DemoCards.probable()

    /**
     * B-G08: in Add, over the whole window or a form beside a parcel, with the keyboard up on the number's field, a drag
     * let go with the keyboard above half its height lets it spring back up, the field keeping the focus; one of 0.8 of
     * the keyboard's height takes it all the way down, the field giving up the focus, Add still open. B-G13 on the way:
     * Add shows no drag indicator, as neither the iPhone's `.fullScreenCover` nor the iPad's
     * `.sheet` with `.presentationSizing(.form)` (ParcelListView.swift:465, 472) shows one.
     */
    @Test
    fun aDragDownInAddTakesTheKeyboardDownAndAShortOneLetsItBackUp() {
        walk.show(DemoCards.parcels())
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        assertFalse("Add shows no drag indicator", walk.exists(SHEET_GRABBER))
        // Once up, Add puts the focus on the number's field itself; a tap there where it hasn't yet.
        runCatching { compose.waitUntil(LONG_MILLIS) { focused(ADD_TRACKING) } }
        if (!focused(ADD_TRACKING)) walk.tap(ADD_TRACKING)
        typingIn(ADD_TRACKING)
        assumeTrue("A keyboard on the screen", keyboardShown())

        val keyboard = windowKeyboard()
        touches.drag(justAbove(ADD_TRACKING, ADD_GAP), Offset(0f, keyboard * SHORT), steps = DRAG_STEPS)
        runCatching { compose.waitUntil(KEYBOARD_MILLIS) { keyboardShown() && abs(windowKeyboard() - keyboard) <= 2 } }
            .onFailure { fail("Add: the keyboard not back up after a short drag, ${windowKeyboard()} of $keyboard px") }
        keyboardStill()
        walk.node(ADD_TRACKING).assertIsFocused()

        dragsTheKeyboardDown("Add", ADD_TRACKING, ADD_GAP)
        assertTrue("Add still open", walk.exists(ADD_PARCEL))
        walk.shot("B-G08-add-keyboard-down", alsoDark = false)
    }

    /**
     * B-G08: in the share sheet, with the keyboard up on a gift's note, a drag of 0.8 of the keyboard's height takes it
     * down, the note giving up the focus, the sheet staying. B-G13 on the way: writing opens the sheet fully (its
     * `detent` set to `.large` as a field takes the focus), attached under the status bar where it is a sheet from the
     * window's bottom, and it stays there once the keyboard has gone.
     */
    @Test
    fun aDragDownInTheShareSheetTakesTheKeyboardDownAndTheSheetStaysLarge() {
        walk.show(DemoCards.parcels())
        walk.open(gift)
        walk.tap(PAGE_SHARE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_SHARE_GIFT) }
        scrollTo(PARCEL_SHARE_GIFT)
        walk.flip(PARCEL_SHARE_GIFT)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_SHARE_NOTE) }
        walk.tap(PARCEL_SHARE_NOTE)
        compose.waitUntil(LONG_MILLIS) { shareLarge() }
        typingIn(PARCEL_SHARE_NOTE)
        val compact = !room().regular
        if (compact) assertAtLarge("The share sheet while writing")
        assumeTrue("A keyboard on the screen", keyboardShown())

        dragsTheKeyboardDown("The share sheet", PARCEL_SHARE_NOTE, FIELD_GAP)
        assertTrue("The share sheet stays", walk.exists(PARCEL_SHARE))
        assertTrue("Still at large", shareLarge())
        if (compact) assertAtLarge("The share sheet with the keyboard down")
        walk.shot("B-G08-share-keyboard-down", alsoDark = false)
    }

    /**
     * B-G08: in the feedback's sheet asking who carries the parcel, with the keyboard up on its first field, a drag of
     * 0.8 of the keyboard's height takes it down, the field giving up the focus, the sheet staying at large, where
     * writing took it (`onChange(of: writing)`).
     */
    @Test
    fun aDragDownInTheFeedbackSheetTakesTheKeyboardDownAndTheSheetStaysLarge() {
        walk.show(listOf(gift, lamp))
        walk.open(lamp)
        walk.node(if (walk.exists(FEEDBACK_QUESTION)) FEEDBACK_QUESTION else FEEDBACK_ROW).performScrollTo()
        walk.settle()
        walk.tap(FEEDBACK_YES)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_CARRIER_SHEET) }
        walk.awaitStill()
        walk.tap(FEEDBACK_WHO)
        compose.waitUntil(LONG_MILLIS) { feedbackLarge() }
        typingIn(FEEDBACK_WHO)
        val compact = !room().regular
        if (compact) assertAtLarge("The feedback's sheet while writing")
        assumeTrue("A keyboard on the screen", keyboardShown())

        dragsTheKeyboardDown("The feedback's sheet", FEEDBACK_WHO, FIELD_GAP)
        assertTrue("The feedback's sheet stays", walk.exists(FEEDBACK_CARRIER_SHEET))
        assertTrue("Still at large", feedbackLarge())
        if (compact) assertAtLarge("The feedback's sheet with the keyboard down")
        walk.shot("B-G08-feedback-keyboard-down", alsoDark = false)
    }

    /**
     * With the keyboard up on `field`, a finger from just above it ([justAbove], `gap`) dragged down 0.8 of the
     * keyboard's height: it leaves the keyboard below half its height as the finger lifts, the touch slop and the
     * frames the window takes to hand over the keyboard's control counted, so it goes on down. Then no keyboard is
     * left, and the field has given up the focus.
     */
    private fun dragsTheKeyboardDown(what: String, field: String, gap: Float) {
        val keyboard = windowKeyboard()
        val from = justAbove(field, gap)
        touches.drag(from, Offset(0f, keyboard * DOWN), steps = DRAG_STEPS)
        runCatching { compose.waitUntil(KEYBOARD_MILLIS) { keyboardDown() } }
            .onFailure { fail("$what: the keyboard still up, ${windowKeyboard()} of $keyboard px, after a drag from $from") }
        keyboardStill()
        walk.node(field).assertIsNotFocused()
    }

    /**
     * The point `gap` dp over what `tag` bounds, across its middle, in what scrolls around it: no higher than a little
     * under the top of that, where the field stands at the top of what scrolls and the room over it is out of view.
     */
    private fun justAbove(tag: String, gap: Float): Offset {
        val scale = compose.density.density
        val field = touches.point(tag, y = 0f)
        val viewport = compose.onAllNodes(hasScrollAction() and hasAnyDescendant(hasTestTag(tag)), useUnmergedTree = true)
            .fetchSemanticsNodes().minByOrNull { it.boundsInWindow.height }?.boundsInWindow
        val highest = viewport?.let { touches.origin().y + it.top + EDGE * scale } ?: Float.NEGATIVE_INFINITY
        return Offset(field.x, max(field.y - gap * scale, highest))
    }

    /** `tag` has the focus and takes the keys; where the device shows a keyboard on the screen, it is up and still. */
    private fun typingIn(tag: String) {
        walk.node(tag).assertIsFocused()
        compose.waitUntil(LONG_MILLIS) { acceptingText() }
        if (showsAKeyboard()) runCatching { compose.waitUntil(KEYBOARD_COMES_MILLIS) { keyboardShown() } }
        keyboardStill()
    }

    /**
     * The keyboard's height unchanged for [QUIET_MILLIS], no slide of it playing, and a second of the app's time for the
     * field to be brought into view above it: the window can hand the app the keyboard's height a moment before its
     * slide starts.
     */
    private fun keyboardStill() {
        var last = -1
        var since = SystemClock.uptimeMillis()
        compose.waitUntil(KEYBOARD_MILLIS) {
            val height = windowKeyboard()
            if (height != last) {
                last = height
                since = SystemClock.uptimeMillis()
            }
            SystemClock.uptimeMillis() - since >= QUIET_MILLIS
        }
        walk.awaitStill()
        walk.settle()
    }

    private fun focused(tag: String): Boolean =
        compose.onAllNodes(hasTestTag(tag) and isFocused(), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** The part tagged `tag` scrolled into view where it scrolls, and still. */
    private fun scrollTo(tag: String) {
        if (compose.onAllNodes(hasTestTag(tag) and hasAnyAncestor(hasScrollAction())).fetchSemanticsNodes().isNotEmpty()) {
            walk.node(tag).performScrollTo()
        }
        walk.settle()
    }

    /** Whether the open share sheet stands at its large detent, as its work holds it. */
    private fun shareLarge(): Boolean = walk.onUi { walk.deliveries.sharing?.let { walk.source.shares.sheet(it.id) }?.large == true }

    /** Whether the lamp's feedback's sheet stands at its large detent, as its work holds it. */
    private fun feedbackLarge(): Boolean = walk.onUi { walk.source.feedbacks.of(lamp.id)?.large == true }

    /** The sheet at its large detent in a compact window: attached under the status bar, across the whole window. */
    private fun assertAtLarge(what: String) {
        val room = room()
        val top = max(room.top, LARGE_MIN_TOP)
        val panel = compose.onNodeWithTag(SHEET_PANEL, useUnmergedTree = true).getUnclippedBoundsInRoot()
        val shown = listOf(panel.left.value, panel.top.value, panel.right.value, panel.bottom.value)
        val expected = listOf(0f, top, room.width, room.height)
        assertTrue("$what at $shown, not $expected", shown.zip(expected).all { (a, b) -> abs(a - b) <= TOLERANCE })
    }

    /** The window in dp, and the room its bars and cutout take at its top. */
    private data class Room(val width: Float, val height: Float, val top: Float) {
        /** Regular as the sheets have it: a form in the window's middle rather than a sheet from its bottom. */
        val regular: Boolean get() = width >= REGULAR_WIDTH && height >= REGULAR_HEIGHT
    }

    private fun room(): Room = walk.onUi {
        val view = compose.activity.window.decorView
        val scale = compose.activity.resources.displayMetrics.density
        val bars = ViewCompat.getRootWindowInsets(view)?.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
        Room(view.width / scale, view.height / scale, (bars?.top ?: 0) / scale)
    }

    private fun acceptingText() = walk.onUi { compose.activity.getSystemService(InputMethodManager::class.java).isAcceptingText }

    /** Whether the window has a keyboard standing up, with its height. */
    private fun keyboardShown() = walk.onUi {
        val insets = ViewCompat.getRootWindowInsets(compose.activity.window.decorView)
        insets?.isVisible(WindowInsetsCompat.Type.ime()) == true && insets.getInsets(WindowInsetsCompat.Type.ime()).bottom > 0
    }

    /** Whether the window has no keyboard standing or coming up. */
    private fun keyboardDown() = walk.onUi {
        ViewCompat.getRootWindowInsets(compose.activity.window.decorView)?.isVisible(WindowInsetsCompat.Type.ime()) != true
    }

    /** The keyboard's height where the window has it, in pixels: none where none shows. */
    private fun windowKeyboard(): Int = walk.onUi {
        ViewCompat.getRootWindowInsets(compose.activity.window.decorView)?.getInsets(WindowInsetsCompat.Type.ime())?.bottom ?: 0
    }

    /**
     * Whether the device shows a keyboard on the screen for a field: always without a hardware keyboard, and with one
     * only where the system is set to show it anyway, as the sign-in's device test asks.
     */
    private fun showsAKeyboard(): Boolean {
        val context = compose.activity
        val hardware = context.resources.configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO
        return !hardware || Settings.Secure.getInt(context.contentResolver, "show_ime_with_hard_keyboard", 0) != 0
    }

    private companion object {
        const val FOLDER = "keyboarddismiss"

        /** How long a keyboard may take to come up the first time, Gboard starting on an emulator. */
        const val KEYBOARD_COMES_MILLIS = 15_000L

        /** How long the keyboard's height stays as it is before it counts as still. */
        const val QUIET_MILLIS = 500L

        /** The drag that takes the keyboard down, and the one let go with it still above half its height. */
        const val DOWN = 0.8f
        const val SHORT = 0.3f
        const val DRAG_STEPS = 30

        /**
         * Over the field, in dp: Add's number field's tag is 14 inside its frame, which stands 8 under its label; the
         * share sheet's and the feedback's fields' frames stand 5 and 6 under theirs.
         */
        const val ADD_GAP = 18f
        const val FIELD_GAP = 3f

        /** The least a drag's start stands under the top of what scrolls, in dp. */
        const val EDGE = 4f

        const val LARGE_MIN_TOP = 10f
        const val REGULAR_WIDTH = 600f
        const val REGULAR_HEIGHT = 480f

        /** How far a sheet's edge may stand from where it is worked out, in dp: a pixel's rounding at most. */
        const val TOLERANCE = 1.5f
    }
}
