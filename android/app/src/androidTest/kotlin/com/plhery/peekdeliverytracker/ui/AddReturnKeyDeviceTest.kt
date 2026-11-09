package com.plhery.peekdeliverytracker.ui

import android.os.SystemClock
import android.text.InputType
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PHONE_DIGITS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_POSTCODE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING_URL
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Add's return key (B-G07), on this device's own window, as AddParcelView's: the tracking field asks the keyboard for
 * Next (`.submitLabel(.next)`) and the name for Done (`.submitLabel(.done)`). Next on the number (`submitTracking`)
 * goes to the first detail the carrier still needs (its tracking link, the delivery postcode, the phone's last digits),
 * else to the name, and leaving the field settles the number; Done on the name (`onSubmit`) adds the parcel where it
 * can be added and closes Add, else goes back to the number. The action key is pressed as the keyboard presses it:
 * through the input connection the focused field gives the window, with the action its EditorInfo asks for
 * (`performEditorAction`), so it holds where the device shows no keyboard on the screen too (a hardware one open); a
 * hardware Return does the same. Signed in as the synthetic reader@example.com on fake deliveries whose stores answer
 * every read in memory, with synthetic numbers (the public UPS sample among them); nothing goes to the network. Shots
 * in addreturn/<row>-<moment>.png.
 */
@RunWith(AndroidJUnit4::class)
class AddReturnKeyDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")

    /** Add opened from the +, over deliveries set up by `prepare`, once its tracking field has taken the focus. */
    private fun openAdd(prepare: FakeDeliveries.() -> Unit = {}) {
        walk.show(listOf(lamp)) {
            addedId = ADDED
            prepare()
        }
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        awaitFocus(ADD_TRACKING)
        walk.awaitKeyboard(required = false)
    }

    private fun focused(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag) and isFocused()).fetchSemanticsNodes().isNotEmpty()

    private fun awaitFocus(tag: String) {
        compose.waitUntil(LONG_MILLIS) { focused(tag) }
        compose.waitForIdle()
    }

    /** `text` in the tracking field, settled. */
    private fun type(text: String) {
        walk.node(ADD_TRACKING).performTextReplacement(text)
        walk.settle()
    }

    private fun action(info: EditorInfo): Int = info.imeOptions and EditorInfo.IME_MASK_ACTION

    /**
     * The keyboard's action key on the focused field, as a keyboard presses it: the field's input connection opened
     * from the window as the keyboard opens it, whose EditorInfo asks for `expected`, then that action performed
     * through it; a second of the app's time after.
     */
    private fun pressActionKey(expected: Int) {
        compose.waitForIdle()
        val view = (compose.onNode(isFocused()).fetchSemanticsNode().root as ViewRootForTest).view
        val info = EditorInfo()
        val connection = checkNotNull(walk.onUi { view.onCreateInputConnection(info) }) { "The focused field gives no input connection" }
        assertEquals("The action the field asks the keyboard for", expected, action(info))
        walk.onUi { connection.performEditorAction(expected) }
        walk.settle()
    }

    /** Return pressed and let go on a keyboard, through the system's input to the window in front; then a second. */
    private fun pressReturn() {
        val down = SystemClock.uptimeMillis()
        instrumentation.sendKeySync(KeyEvent(down, down, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER, 0))
        instrumentation.sendKeySync(KeyEvent(down, SystemClock.uptimeMillis(), KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER, 0))
        walk.settle()
    }

    private fun additions(): List<FakeDeliveries.AdditionRequest> = walk.onUi { walk.source.additions.toList() }

    private fun detected(): List<String> = walk.onUi { walk.source.detected.toList() }

    /**
     * B-G07: the tracking field, which takes several lines, asks the keyboard for Next, its return key no new line; the
     * name asks for Done, in sentences.
     */
    @Test
    fun theTrackingFieldAsksForNextAndTheNameForDone() {
        openAdd()
        val tracking = walk.editorInfo()
        assertEquals(EditorInfo.IME_ACTION_NEXT, action(tracking))
        assertEquals("The return key is the action's", 0, tracking.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION)
        assertEquals(InputType.TYPE_TEXT_FLAG_MULTI_LINE, tracking.inputType and InputType.TYPE_TEXT_FLAG_MULTI_LINE)
        walk.shot("B-G07-tracking")

        walk.tap(ADD_NAME)
        awaitFocus(ADD_NAME)
        walk.awaitKeyboard(required = false)
        val name = walk.editorInfo()
        assertEquals(EditorInfo.IME_ACTION_DONE, action(name))
        assertEquals(0, name.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION)
        assertEquals(0, name.inputType and InputType.TYPE_TEXT_FLAG_MULTI_LINE)
        assertEquals(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES, name.inputType and InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)
        walk.shot("B-G07-name")
    }

    /**
     * B-G07: Next with a number typed settles it, which asks the carriers about it as leaving the field does (not while
     * it is typed), and moves the focus to the name; nothing is added and Add stays.
     */
    @Test
    fun nextWithANumberTypedSettlesItAndMovesToTheName() {
        openAdd()
        type(ASKED)
        assertEquals("Not asked while it is typed", emptyList<String>(), detected())
        assertTrue(focused(ADD_TRACKING))
        pressActionKey(EditorInfo.IME_ACTION_NEXT)
        awaitFocus(ADD_NAME)
        assertEquals(listOf(ASKED), detected())
        assertTrue(walk.exists(ADD_PARCEL))
        assertTrue(additions().isEmpty())
        walk.awaitKeyboard(required = false)
        walk.shot("B-G07-next", alsoDark = false)
    }

    /** B-G07: Done on the name, with a number that can be added, adds the parcel under that name and closes Add. */
    @Test
    fun doneOnTheNameAddsANumberThatCanBeAddedAndClosesAdd() {
        openAdd()
        type(UPS_SAMPLE)
        pressActionKey(EditorInfo.IME_ACTION_NEXT)
        awaitFocus(ADD_NAME)
        walk.node(ADD_NAME).performTextReplacement(LABEL)
        walk.settle()
        walk.awaitKeyboard(required = false)
        walk.shot("B-G07-done-ready", alsoDark = false)
        pressActionKey(EditorInfo.IME_ACTION_DONE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) }
        val added = additions().single()
        assertEquals(UPS_SAMPLE, added.trackingNumber)
        assertEquals(LABEL, added.label)
    }

    /** B-G07: Done on the name with no number to add goes back to the tracking field; nothing is added and Add stays. */
    @Test
    fun doneWithNothingToAddGoesBackToTheTrackingField() {
        openAdd()
        walk.tap(ADD_NAME)
        awaitFocus(ADD_NAME)
        walk.node(ADD_NAME).performTextReplacement(LABEL)
        walk.settle()
        pressActionKey(EditorInfo.IME_ACTION_DONE)
        awaitFocus(ADD_TRACKING)
        assertTrue(walk.exists(ADD_PARCEL))
        assertTrue(additions().isEmpty())
        walk.awaitKeyboard(required = false)
        walk.shot("B-G07-done-back", alsoDark = false)
    }

    /**
     * B-G07: a carrier that follows a link asks for one as soon as its number is typed; Next goes to the link before the
     * name, and the link's field asks for Next in its turn (`.submitLabel(.next)`).
     */
    @Test
    fun nextGoesFirstToARequiredTrackingLink() {
        openAdd()
        assertNextGoesFirstTo(LINKED, ADD_TRACKING_URL, "B-G07-next-link")
        assertEquals(EditorInfo.IME_ACTION_NEXT, action(walk.editorInfo()))
    }

    /** B-G07: J&T Mexico's number takes the phone's last four digits: Next goes to them before the name. */
    @Test
    fun nextGoesFirstToThePhonesLastDigits() {
        openAdd()
        assertNextGoesFirstTo(PHONE, ADD_PHONE_DIGITS, "B-G07-next-phone")
    }

    /**
     * B-G07: a number the carriers say is GLS Switzerland's needs the delivery postcode, none given before: Next goes to
     * it before the name.
     */
    @Test
    fun nextGoesFirstToARequiredPostcode() {
        openAdd { detections[ASKED] = CarrierDetectionResponse(ASKED, carrier = CarrierId.GlsCh) }
        assertNextGoesFirstTo(ASKED, ADD_POSTCODE, "B-G07-next-postcode")
    }

    /**
     * `number` typed; where its carrier is only known once the number is settled, the name tapped first (the field
     * left, as a finger leaves it) until the field tagged `tag` shows, then the tracking field tapped again. Next then
     * moves the focus to `tag`'s field, not the name; nothing is added and Add stays. Shot as `moment`.
     */
    private fun assertNextGoesFirstTo(number: String, tag: String, moment: String) {
        type(number)
        if (!walk.exists(tag)) {
            walk.tap(ADD_NAME)
            awaitFocus(ADD_NAME)
            compose.waitUntil(LONG_MILLIS) { walk.exists(tag) }
            walk.settle()
            walk.tap(ADD_TRACKING)
            awaitFocus(ADD_TRACKING)
        }
        assertEquals("", walk.editable(tag))
        pressActionKey(EditorInfo.IME_ACTION_NEXT)
        awaitFocus(tag)
        assertTrue(walk.exists(ADD_PARCEL))
        assertTrue(additions().isEmpty())
        walk.awaitKeyboard(required = false)
        walk.shot(moment, alsoDark = false)
    }

    /**
     * B-G07: Return on a hardware keyboard does what the action key does: on the number it moves to the name and types
     * no new line; on the name, with a number that can be added, it adds the parcel and closes Add.
     */
    @Test
    fun aHardwareReturnDoesWhatTheActionKeyDoes() {
        openAdd()
        type(UPS_SAMPLE)
        pressReturn()
        awaitFocus(ADD_NAME)
        assertEquals("No new line typed", UPS_SAMPLE, walk.editable(ADD_TRACKING))
        walk.node(ADD_NAME).performTextReplacement(LABEL)
        walk.settle()
        pressReturn()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) }
        val added = additions().single()
        assertEquals(UPS_SAMPLE, added.trackingNumber)
        assertEquals(LABEL, added.label)
    }

    private companion object {
        const val FOLDER = "addreturn"

        /** The public UPS sample, which Add can add as it stands. */
        const val UPS_SAMPLE = "1Z999AA10123456784"

        /** A synthetic number several carriers could have, asked about only once the field is left. */
        const val ASKED = "12345478901"

        /** A synthetic J&T Mexico number, which takes the phone's last digits. */
        const val PHONE = "JMX000000000001"

        /** A synthetic Planzer number, whose carrier follows a link. */
        const val LINKED = "9999012345678"

        const val LABEL = "Running shoes"

        val ADDED: UUID = UUID(0, 7_007)
    }
}
