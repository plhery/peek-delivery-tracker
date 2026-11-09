package com.plhery.peekdeliverytracker.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Color
import android.text.InputType
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.READER
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PASTE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PHONE_DIGITS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PHONE_DIGITS_PRIVATE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PLACEHOLDER
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SCAN
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.CARRIER_PICKER
import com.plhery.peekdeliverytracker.ui.deliveries.CARRIER_PICKER_SEARCH
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_PHONE_DIGITS
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.FakeLabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER
import com.plhery.peekdeliverytracker.ui.deliveries.SCANNER_CANCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.carrierPickerRowTag
import com.plhery.peekdeliverytracker.ui.deliveries.menuItemTag
import com.plhery.peekdeliverytracker.ui.theme.PeekColors
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Add's fields on this device's own window (B-S22, B-S77, B-S79), as AddParcelView's `trackingField`, its
 * `captureActions`, the placeholder number's caption and the phone's last digits, and the same digits in Change
 * carrier (ParcelDetailView's ChangeCarrierView). Signed in as the synthetic reader@example.com, or as a guest, on fake
 * deliveries ([ParcelWalk]) whose stores answer every read in memory; the numbers are synthetic or public samples, and
 * nothing goes to the network. The scanner is a fake one that draws no camera ([FakeLabelScanner]), so Scan shows on
 * every window. The clipboard is put back after each test. Each saved as addfields/<row>-<moment>.png in the test
 * package's external files, which adb pulls.
 */
@RunWith(AndroidJUnit4::class)
class AddFieldsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val clipboard = KeptClipboard()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(clipboard).around(walk)

    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")

    /** A J&T Mexico parcel like the demo's kit, numbered with the synthetic [PHONE], its phone's last digits `digits`. */
    private fun jAndT(digits: String?): Parcel = DemoCards.parcel("Trail weekend kit").copy(
        carrier = CarrierId.JAndTMx, trackingNumber = PHONE, dpdPostcode = null, phoneDigits = digits,
    )

    /**
     * Add opened from the + over `lamp` in the deliveries of `session` set up by `prepare`; once its tracking field has
     * taken the focus where `focused`.
     */
    private fun openAdd(session: AuthenticationState = READER, focused: Boolean = true, prepare: FakeDeliveries.() -> Unit = {}) {
        walk.show(listOf(lamp), session = session, prepare = prepare)
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        if (focused) awaitFocus(ADD_TRACKING)
        walk.awaitKeyboard(required = false)
    }

    private fun focused(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag) and isFocused()).fetchSemanticsNodes().isNotEmpty()

    private fun awaitFocus(tag: String) {
        compose.waitUntil(LONG_MILLIS) { focused(tag) }
        compose.waitForIdle()
    }

    /** `text` in the tracking field, as typed, then a second of the app's time. */
    private fun type(text: String) {
        walk.node(ADD_TRACKING).performTextReplacement(text)
        walk.settle()
    }

    /**
     * The phone's last digits shown for the number typed: where its carrier is only known once the number has settled,
     * the name tapped first (the field left, as a finger leaves it), then the tracking field again.
     */
    private fun awaitPhoneDigits() {
        if (walk.exists(ADD_PHONE_DIGITS)) return
        walk.tap(ADD_NAME)
        awaitFocus(ADD_NAME)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PHONE_DIGITS) }
        walk.settle()
        walk.tap(ADD_TRACKING)
        awaitFocus(ADD_TRACKING)
    }

    /**
     * The keyboard's action key on the focused field, as a keyboard presses it: its input connection opened from the
     * window, whose EditorInfo asks for `expected`, then that action performed through it; a second after.
     */
    private fun pressActionKey(expected: Int) {
        compose.waitForIdle()
        val view = (compose.onNode(isFocused()).fetchSemanticsNode().root as ViewRootForTest).view
        val info = EditorInfo()
        val connection = checkNotNull(walk.onUi { view.onCreateInputConnection(info) }) { "The focused field gives no input connection" }
        assertEquals("The action the field asks the keyboard for", expected, info.imeOptions and EditorInfo.IME_MASK_ACTION)
        walk.onUi { connection.performEditorAction(expected) }
        walk.settle()
    }

    private fun actions(name: String): List<String> = walk.onUi { walk.source.actions.filter { it == name } }

    /** What TalkBack reads the part tagged `tag` by, from its own node. */
    private fun description(tag: String): List<String>? =
        compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)

    /** The tracking field's height as laid out with `text` in it, in pixels. */
    private fun fieldHeight(text: String): Int {
        type(text)
        return walk.node(ADD_TRACKING).fetchSemanticsNode().size.height
    }

    /**
     * How wide the tracking field's drawing runs, in dp: its columns holding a pixel unlike the field's own ground, read
     * near its bottom trailing corner, which neither its placeholder nor a short number reaches. The 16 at each side
     * and the 2 at the top and foot are left out, where its frame, its corners and a short number with the cursor stand.
     */
    private fun inkWidth(): Float {
        compose.waitForIdle()
        val image = walk.node(ADD_TRACKING).captureToImage().asAndroidBitmap()
        val width = image.width
        val height = image.height
        val pixels = IntArray(width * height)
        image.getPixels(pixels, 0, width, 0, 0, width, height)
        val density = compose.density.density
        val side = (16 * density).toInt()
        val edge = (2 * density).toInt()
        val ground = pixels[(height - edge - 1) * width + (width - side - 1)]
        val columns = (side until width - side).count { x -> (edge until height - edge).any { y -> unlike(pixels[y * width + x], ground) } }
        return columns / density
    }

    private fun unlike(pixel: Int, ground: Int): Boolean =
        maxOf(abs(Color.red(pixel) - Color.red(ground)), abs(Color.green(pixel) - Color.green(ground)), abs(Color.blue(pixel) - Color.blue(ground))) > INK

    /** The layout of the text tagged `tag`: its style as drawn. */
    private fun layoutOf(tag: String): TextLayoutResult {
        val node = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode()
        return walk.onUi {
            val laid = mutableListOf<TextLayoutResult>()
            node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(laid)
            laid.single()
        }
    }

    // B-S22: the tracking field and its capture actions.

    /**
     * B-S22: the tracking field draws "add.trackingPlaceholder" while empty, which goes once something is typed and
     * comes back once it is emptied, under "add.tracking", which TalkBack reads it by, the placeholder after it while it
 * is empty as VoiceOver reads a field's placeholder; it asks the keyboard for Next,
     * no capitals, no autocorrection and no suggestions (AddParcelView's `trackingField`: `.textInputAutocapitalization
     * (.never)`, `.autocorrectionDisabled()`, `.submitLabel(.next)`).
     */
    @Test
    fun theTrackingFieldShowsItsPlaceholderUnderItsLabelAndAsksForNextWithoutCapitals() {
        openAdd()
        val label = walk.text("add.tracking")
        assertTrue("The label over the field", walk.shows(ADD_PARCEL, label))
        val placeholder = walk.text("add.trackingPlaceholder")
        assertEquals("Its name, then its placeholder while empty", listOf("$label, $placeholder"), description(ADD_TRACKING))
        val empty = inkWidth()
        assertTrue("The placeholder drawn across the empty field: $empty dp", empty >= PLACEHOLDER_INK)

        val info = walk.editorInfo()
        assertEquals(InputType.TYPE_CLASS_TEXT, info.inputType and InputType.TYPE_MASK_CLASS)
        val capitals = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS or InputType.TYPE_TEXT_FLAG_CAP_WORDS or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        assertEquals("No capitals asked of the keyboard", 0, info.inputType and capitals)
        assertEquals("No autocorrection", 0, info.inputType and InputType.TYPE_TEXT_FLAG_AUTO_CORRECT)
        assertEquals("No suggestions", InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS, info.inputType and InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
        assertEquals(EditorInfo.IME_ACTION_NEXT, info.imeOptions and EditorInfo.IME_MASK_ACTION)

        type("1")
        val typed = inkWidth()
        assertTrue("The placeholder gone once a digit is typed: $typed dp", typed < TYPED_INK)
        assertEquals("Its name alone once it has words", listOf(label), description(ADD_TRACKING))
        type("")
        assertTrue("The placeholder back once the field is emptied", inkWidth() >= PLACEHOLDER_INK)
        assertEquals(listOf("$label, $placeholder"), description(ADD_TRACKING))
        walk.shot("B-S22-field", alsoDark = false)
    }

    /**
     * B-S22: the tracking field is two lines tall while empty or holding one or two lines, grows a line with a third and
     * a fourth, and stops growing there, its lines kept (AddParcelView's `.lineLimit(2...4)`).
     */
    @Test
    fun theTrackingFieldIsTwoLinesTallEmptyAndGrowsWithItsLinesUpToFour() {
        openAdd()
        val empty = fieldHeight("")
        val one = fieldHeight("A")
        val two = fieldHeight("A\nB")
        val three = fieldHeight("A\nB\nC")
        val four = fieldHeight("A\nB\nC\nD")
        val six = fieldHeight(SIX_LINES)
        val line = three - two
        assertTrue("A third line makes it taller: $two px, then $three px", line > 0)
        assertEquals("One line keeps the empty field's two lines", empty.toFloat(), one.toFloat(), PX)
        assertEquals("Two lines keep it", empty.toFloat(), two.toFloat(), PX)
        assertEquals("A fourth line grows it a line more", line.toFloat(), (four - three).toFloat(), PX)
        assertEquals("No taller past four lines", four.toFloat(), six.toFloat(), PX)
        // Two lines but the leading under the last, which its text trims as SwiftUI's does: far more than one line.
        assertTrue("Empty, two lines tall: $empty px, lines of $line px", empty >= 2 * line - line / 4 && empty <= 2 * line + PX)
        assertEquals("The lines past four kept", SIX_LINES, walk.editable(ADD_TRACKING))
        walk.shot("B-S22-four-lines", alsoDark = false)
    }

    /**
     * B-S22: at the accessibility sizes the empty field draws its placeholder on two lines at most, cut short, and so is
     * as tall as with two lines typed (AddParcelView draws it in the field's place with `.lineLimit(2)`).
     */
    @Test
    fun atTheLargestTextThePlaceholderKeepsToTheFieldsTwoLines() {
        walk.fontScale = 2f
        openAdd()
        val two = fieldHeight("A\nB")
        val three = fieldHeight("A\nB\nC")
        val empty = fieldHeight("")
        assertTrue("A third line makes it taller: $two px, then $three px", three > two)
        assertEquals("Empty, as tall as with two lines", two.toFloat(), empty.toFloat(), PX)
        walk.shot("B-S22-largest-placeholder", alsoDark = false)
    }

    /**
     * B-S22: Paste is a finger's 44 tall as drawn, and Scan a 44 square read "add.scan" (`captureActions`); a tap on
     * Scan counts "parcel-scan" and opens the scanner over Add, whose Cancel brings Add back.
     */
    @Test
    fun pasteIsAFingerTallAndScanA44SquareThatCountsAndOpensTheScanner() {
        walk.scanner = FakeLabelScanner()
        openAdd()
        walk.node(ADD_PASTE).assertHeightIsAtLeast(44.dp)
        val drawn = walk.node(ADD_PASTE).fetchSemanticsNode().boundsInRoot
        assertTrue("Paste drawn 44 tall, not clipped: $drawn", drawn.height >= 44 * compose.density.density - 1f)
        walk.node(ADD_SCAN).assertWidthIsEqualTo(44.dp).assertHeightIsEqualTo(44.dp)
        assertEquals(listOf(walk.text("add.scan")), description(ADD_SCAN))

        walk.node(ADD_SCAN).performClick()
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(SCANNER) }
        assertEquals(listOf("parcel-scan"), actions("parcel-scan"))
        walk.awaitStill()
        walk.node(SCANNER_CANCEL).performClick()
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SCANNER) && walk.exists(ADD_PARCEL) && walk.exists(ADD_SCAN) }
        walk.shot("B-S22-capture", alsoDark = false)
    }

    /**
     * B-S22: a paste that finds nothing says "add.pasteFailed" in the error banner; the field changed, it goes, as
     * `onChange(of: trackingInput)` clears `errorMessage`.
     */
    @Test
    fun aFailedPastesErrorGoesOnceTheFieldChanges() {
        openAdd()
        clipboard.empty()
        walk.node(ADD_PASTE).performClick()
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_ERROR) }
        assertTrue(walk.shows(ADD_ERROR, walk.text("add.pasteFailed")))
        assertEquals(listOf("parcel-paste"), actions("parcel-paste"))
        walk.shot("B-S22-paste-failed", alsoDark = false)

        type("1")
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_ERROR) }
        assertEquals("1", walk.editable(ADD_TRACKING))
    }

    // B-S77: a placeholder number held back.

    /**
     * B-S77: a placeholder rather than a number a carrier issued (an S10 item whose serial runs 1 to 8) is said to be
     * one, in the caption's warning colour, only once the number has settled, and Add stays off (AddParcelView's
     * `placeholderNumber`, `Brand.warning`).
     */
    @Test
    fun aPlaceholderNumberIsSaidInTheWarningColourOnceSettledAndAddStaysOff() {
        assertTrue(CarrierCatalog.isPlaceholderNumber(PLACEHOLDER))
        openAdd()
        type(PLACEHOLDER)
        assertFalse("Not said while it is typed", walk.exists(ADD_PLACEHOLDER))
        walk.tap(ADD_NAME)
        awaitFocus(ADD_NAME)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PLACEHOLDER) }
        walk.settle()
        assertEquals(listOf(walk.text("tracking.failure.placeholderNumber")), walk.texts(ADD_PLACEHOLDER))
        assertEquals("The warning colour", PeekColors(dark = false).warning, layoutOf(ADD_PLACEHOLDER).layoutInput.style.color)
        walk.node(ADD_SAVE).assertIsNotEnabled()
        walk.awaitKeyboard(required = false)
        walk.shot("B-S77-placeholder", alsoDark = false)
    }

    // B-S79: the phone's last digits.

    /**
     * B-S79: J&T Mexico's number shows the field "add.requirement.phoneDigits", read by that name, with its help naming
     * the carrier, and no caption for a guest; Next on the number goes to it, it asks for number keys, and typing
     * "04 72 9" leaves "0472" (AddParcelView's `requirementField`, `.keyboardType(.numberPad)`, `normalizedValue`).
     */
    @Test
    fun jAndTMexicosNumberAsksForTheLastFourDigitsOnNumberKeys() {
        openAdd()
        type(PHONE)
        awaitPhoneDigits()
        val title = walk.text("add.requirement.phoneDigits")
        val carrier = walk.identities.displayName(CarrierId.JAndTMx, walk.language)
        assertTrue(walk.shows(ADD_PARCEL, title))
        assertTrue(walk.shows(ADD_PARCEL, walk.text("add.requirement.phoneDigitsHelp", "carrier" to carrier)))
        assertEquals(listOf(title), description(ADD_PHONE_DIGITS))
        assertFalse("No guest's caption when signed in", walk.exists(ADD_PHONE_DIGITS_PRIVATE))

        assertTrue(focused(ADD_TRACKING))
        pressActionKey(EditorInfo.IME_ACTION_NEXT)
        awaitFocus(ADD_PHONE_DIGITS)
        walk.awaitKeyboard(required = false)
        assertEquals("Number keys", InputType.TYPE_CLASS_NUMBER, walk.editorInfo().inputType and InputType.TYPE_MASK_CLASS)
        walk.node(ADD_PHONE_DIGITS).performTextReplacement("04 72 9")
        walk.settle()
        assertEquals("0472", walk.editable(ADD_PHONE_DIGITS))
        walk.shot("B-S79-add", alsoDark = false)
    }

    /** B-S79: signed out, the digits say "door.phoneDigits.private" under them (AddParcelView, `store.isGuest`). */
    @Test
    fun signedOutThePhoneDigitsSayTheyAreNeverShownOnSharedLinks() {
        openAdd(session = AuthenticationState.Welcome, focused = false) { isGuest = true }
        type(PHONE)
        awaitPhoneDigits()
        assertTrue(walk.shows(ADD_PHONE_DIGITS_PRIVATE, walk.text("door.phoneDigits.private")))
        walk.shot("B-S79-guest", alsoDark = false)
    }

    /**
     * B-S79: Change carrier on a J&T Mexico parcel with digits starts its field with them, Save off; edited, Save comes
     * on; another carrier chosen (J&T Express China, which asks for them too) empties the field (ChangeCarrierView,
     * CarrierChange's `start` and `choosing`).
     */
    @Test
    fun changeCarrierStartsWithTheParcelsDigitsAndEmptiesThemForAnotherCarrier() {
        val parcel = jAndT(digits = "0472")
        walk.show(listOf(parcel))
        walk.open(parcel)
        walk.tap(PAGE_MENU_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_MENU) }
        walk.settle()
        walk.tap(menuItemTag(ParcelMenuItem.ChangeCarrier))
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(CHANGE_CARRIER_SHEET) && walk.exists(CHANGE_CARRIER_PHONE_DIGITS) }
        walk.awaitStill()
        assertEquals("0472", walk.editable(CHANGE_CARRIER_PHONE_DIGITS))
        walk.node(CHANGE_CARRIER_SAVE).assertIsNotEnabled()

        walk.node(CHANGE_CARRIER_PHONE_DIGITS).performTextReplacement("1234")
        walk.settle()
        assertEquals("1234", walk.editable(CHANGE_CARRIER_PHONE_DIGITS))
        walk.node(CHANGE_CARRIER_SAVE).assertIsEnabled()

        walk.tap(CHANGE_CARRIER_ROW)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(CARRIER_PICKER_SEARCH) }
        walk.awaitStill()
        val china = carrierPickerRowTag(CarrierId.JAndTCn)
        walk.node(CARRIER_PICKER_SEARCH).performTextInput(walk.identities.displayName(CarrierId.JAndTCn, walk.language))
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(china) }
        walk.tap(china)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(CARRIER_PICKER) && walk.exists(CHANGE_CARRIER_PHONE_DIGITS) }
        assertEquals("", walk.editable(CHANGE_CARRIER_PHONE_DIGITS))
        assertNotNull(walk.onUi { walk.deliveries.carrierEditor })
        walk.shot("B-S79-change-carrier", alsoDark = false)
    }

    /** The phone's clipboard, emptied on the main thread where a test asks, as it stood before put back after it. */
    private class KeptClipboard : ExternalResource() {
        private val manager: ClipboardManager get() =
            InstrumentationRegistry.getInstrumentation().targetContext.getSystemService(ClipboardManager::class.java)
        private var before: ClipData? = null

        override fun before() {
            before = onMain { manager.primaryClip }
        }

        override fun after() {
            val kept = before
            onMain { if (kept != null) manager.setPrimaryClip(kept) else manager.clearPrimaryClip() }
        }

        fun empty() = onMain { manager.clearPrimaryClip() }

        private fun <T> onMain(work: () -> T): T {
            var result: Result<T>? = null
            InstrumentationRegistry.getInstrumentation().runOnMainSync { result = runCatching(work) }
            return checkNotNull(result).getOrThrow()
        }
    }

    private companion object {
        const val FOLDER = "addfields"

        /** A synthetic J&T Mexico number, which takes the phone's last digits. */
        const val PHONE = "JMX000000000001"

        /** The UPU's sample S10 item, whose serial runs 1 to 8: a placeholder, not a number a carrier issued. */
        const val PLACEHOLDER = "RR123456785CH"

        const val SIX_LINES = "A\nB\nC\nD\nE\nF"

        /** How far apart two heights laid out alike may round, in pixels. */
        const val PX = 2f

        /** How unlike the field's ground a pixel is drawn, in any channel, to count as ink. */
        const val INK = 24

        /** At least how wide the placeholder runs past the field's leading 16, in dp: "Paste a number, link or message". */
        const val PLACEHOLDER_INK = 120f

        /** At most how wide the field's drawing runs past its leading 16 with one digit in it, in dp. */
        const val TYPED_INK = 40f
    }
}
