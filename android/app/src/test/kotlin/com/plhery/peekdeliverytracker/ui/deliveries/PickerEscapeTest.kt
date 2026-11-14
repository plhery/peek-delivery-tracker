package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.KeyEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.time.Duration
import java.util.UUID

/**
 * Escape before an on-screen keyboard, as from Add's fields, reaches the carrier picker's search field over Add too and
 * closes the picker: the picker is a sheet layer of its own, where Android 12's keyboard would otherwise take the key to
 * hide itself. On a synthetic parcel.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PickerEscapeTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private var deliveries: DeliveriesState? = null
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))

    private fun show() {
        val lamp = Parcel(
            id = UUID.fromString("00000000-0000-4000-8000-000000000701"), trackingNumber = "TESTLAMP0001", label = "Desk lamp",
            carrier = CarrierId.Unknown, createdAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))),
            syncStatus = SyncStatus.Ok, notificationsMuted = false,
        )
        val source = FakeDeliveries(listOf(lamp), reader)
        val content: @Composable () -> Unit = {
            view = LocalView.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = reader, source = source, reduceMotion = true)
            }
        }
        compose.setContent(content)
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun focused(tag: String) =
        compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Focused) == true

    /** Add open, a synthetic UPS-shaped number typed, the picker opened over it with the cursor in its search. */
    private fun openPickerWithItsSearchFocused() {
        compose.onNodeWithTag(ADD_BUTTON).performClick()
        settle()
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("1Z999AA10123456784")
        compose.waitForIdle()
        compose.onNodeWithTag(ADD_CARRIER).performClick()
        settle()
        assertTrue("the picker should be up", exists(ADD_PICKER))
        compose.onNodeWithTag(CARRIER_PICKER_SEARCH).performClick()
        compose.waitForIdle()
        assertTrue("the picker's search field should have the focus", focused(CARRIER_PICKER_SEARCH))
    }

    /** Esc offered before the on-screen keyboard (`dispatchKeyEventPreIme`), down and up: whether the press was taken. */
    private fun escapeBeforeTheKeyboard(): Boolean {
        var taken = false
        compose.runOnUiThread {
            val root = view.rootView
            taken = root.dispatchKeyEventPreIme(KeyEvent(0, 0, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ESCAPE, 0))
            root.dispatchKeyEventPreIme(KeyEvent(0, 0, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ESCAPE, 0))
        }
        settle()
        return taken
    }

    /** Esc through the window as the system sends a hardware key when no on-screen keyboard takes it. */
    private fun hardwareEscape() {
        val window = view.rootView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ESCAPE, 0))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ESCAPE, 0))
        }
        settle()
    }

    @Test
    fun escapeBeforeAnOnScreenKeyboardClosesThePickerWhileItsSearchHasTheFocus() {
        show()
        openPickerWithItsSearchFocused()
        val taken = escapeBeforeTheKeyboard()
        assertTrue("Esc with the picker's search focused should be taken before the on-screen keyboard", taken)
        assertFalse("Esc before the on-screen keyboard should close the carrier picker", exists(ADD_PICKER))
        assertNotNull("Add should stay up under the picker", deliveries?.addition)
    }

    /** The same pre-IME Esc with Add's own field focused is taken, and closes Add. */
    @Test
    fun escapeBeforeTheKeyboardClosesAddFromItsOwnField() {
        show()
        compose.onNodeWithTag(ADD_BUTTON).performClick()
        settle()
        assertTrue(focused(ADD_TRACKING))
        assertTrue("Esc before the keyboard with Add's field focused should be taken", escapeBeforeTheKeyboard())
        assertNull("Add should close", deliveries?.addition)
    }

    /** With no on-screen keyboard to take it, the same Esc in the picker's search closes the picker. */
    @Test
    fun hardwareEscapeClosesThePickerFromItsSearch() {
        show()
        openPickerWithItsSearchFocused()
        hardwareEscape()
        assertFalse("a hardware Esc nothing else took should close the picker", exists(ADD_PICKER))
        assertNotNull(deliveries?.addition)
    }
}
