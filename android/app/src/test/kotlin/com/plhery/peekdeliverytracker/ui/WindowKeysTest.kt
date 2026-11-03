package com.plhery.peekdeliverytracker.ui

import android.view.KeyEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performMouseInput
import androidx.compose.ui.test.rightClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_MENU
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.FakeParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.cardNamed
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The window's keys on a hardware keyboard (A-G10, A-G11): the deliveries', the tabs' and the account's, what stops
 * them, as the iPad's `keyboardFree` and `windowCovered` do, and the list of them the system shows (Meta+/).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = TABLET)
class WindowKeysTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var view: View
    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val links = FakeParcelLinks(answer = LinkFixtures.viewer(), session = AuthenticationState.Demo)
    private val shortcuts = WindowShortcuts()

    /** The demo's deliveries beside a parcel, a parcel's link able to open over them. */
    private fun show() {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }, LocalWindowShortcuts provides shortcuts) {
                RootHost(state = AuthenticationState.Demo, source = source, links = links)
            }
        }
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun openLink() {
        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = LinkFixtures.viewer().link.id)) }
        settle()
        compose.onNodeWithTag(LINK_SHEET).assertExists()
    }

    private fun closeLink() {
        compose.runOnUiThread { links.links.close() }
        settle()
        compose.onNodeWithTag(LINK_SHEET).assertDoesNotExist()
    }

    private fun openCardMenu() {
        compose.onNode(cardNamed("New trainers") and hasAnyAncestor(hasTestTag(PARCEL_LIST))).performMouseInput { rightClick() }
        settle()
        compose.onNodeWithTag(CARD_MENU).assertExists()
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun passportShown() = exists(PASSPORT_ACCOUNT_BUTTON)

    /**
     * A key pressed and let go on a hardware keyboard, through the window's input stages after the keyboard's, as the
     * system delivers it: the views first, then what none of them used.
     */
    private fun press(key: Int, meta: Int = 0) {
        val window = view.rootView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, key, 0, meta))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, key, 0, meta))
        }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun shown() = deliveries.navigator.navigation.shown

    /** `links.route == nil` is part of `keyboardFree`: under a parcel's link's sheet, ↓ and Ctrl+N do nothing. */
    @Test
    fun theDeliveriesKeysRestUnderALinksSheet() {
        show()
        val first = shown()
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        val second = shown()
        assertNotNull(second)
        assertEquals(false, second == first)

        openLink()
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        assertEquals(second, shown())
        press(KeyEvent.KEYCODE_N, CTRL)
        assertNull(deliveries.addition)
    }

    /**
     * ⌘1–3 do nothing while anything stands over the window (`windowCovered`): under a card's menu, Add or a parcel's
     * link's sheet Ctrl+2 leaves the deliveries in front, and chooses the passport once nothing stands over them.
     */
    @Test
    fun theTabKeysWaitWhileAnythingStandsOverTheWindow() {
        show()
        openCardMenu()
        press(KeyEvent.KEYCODE_2, CTRL)
        assertFalse(passportShown())
        back()
        assertFalse(exists(CARD_MENU))

        press(KeyEvent.KEYCODE_N, CTRL)
        assertTrue(exists(ADD_PARCEL))
        press(KeyEvent.KEYCODE_2, CTRL)
        assertFalse(passportShown())
        press(KeyEvent.KEYCODE_ESCAPE)
        assertFalse(exists(ADD_PARCEL))

        openLink()
        press(KeyEvent.KEYCODE_2, CTRL)
        assertFalse(passportShown())
        closeLink()

        press(KeyEvent.KEYCODE_2, CTRL)
        assertTrue(passportShown())
    }

    /**
     * ⌘, opens the account of the screen in front (`MenuKey.account`), and waits while anything stands over the window
     * (`open()`): the passport's under a parcel's link's sheet, then the deliveries'.
     */
    @Test
    fun ctrlCommaOpensTheAccountOnceNothingStandsOverTheWindow() {
        show()
        press(KeyEvent.KEYCODE_2, CTRL)
        assertTrue(passportShown())
        openLink()
        press(KeyEvent.KEYCODE_COMMA, CTRL)
        assertFalse(exists(SETTINGS_SHEET))
        closeLink()
        press(KeyEvent.KEYCODE_COMMA, CTRL)
        assertTrue(exists(SETTINGS_SHEET))
        back()
        assertFalse(exists(SETTINGS_SHEET))

        press(KeyEvent.KEYCODE_1, CTRL)
        assertFalse(passportShown())
        assertFalse(deliveries.showingAccount)
        press(KeyEvent.KEYCODE_COMMA, CTRL)
        assertTrue(deliveries.showingAccount)
        assertTrue(exists(SETTINGS_SHEET))
    }

    /**
     * ⌘R refreshes as the View menu's Refresh does (`DeliveriesMenu.refresh`): while the deliveries have the keys, the
     * list shows with parcels in it and no refresh is running.
     */
    @Test
    fun ctrlRRefreshesWhileTheListShowsAndNoRefreshRuns() {
        show()
        val checking = CompletableDeferred<ParcelStore.RefreshResult?>()
        source.checking = checking
        press(KeyEvent.KEYCODE_R, CTRL)
        assertEquals(1, source.refreshes)
        press(KeyEvent.KEYCODE_R, CTRL)
        assertEquals(1, source.refreshes)
        compose.runOnUiThread { checking.complete(ParcelStore.RefreshResult.Unchanged) }
        settle()

        openLink()
        press(KeyEvent.KEYCODE_R, CTRL)
        assertEquals(1, source.refreshes)
        closeLink()
        press(KeyEvent.KEYCODE_R, CTRL)
        assertEquals(2, source.refreshes)
    }

    /**
     * The list the system shows over the app (Meta+/) has the keys answered now, in the menu bar's order and titles:
     * the app's under its name, the parcel's under Parcel, the deliveries' own under Deliveries. Under a card's menu
     * (no keyboard connected, so its items show no keys) nothing answers.
     */
    @Test
    fun theWindowListsTheKeysAnsweredNow() {
        show()
        val keys = shortcuts.available()
        assertTrue(keys.containsAll(listOf(WindowKey.CopyTrackingNumber, WindowKey.Delete, WindowKey.Previous, WindowKey.Next)))
        assertFalse(WindowKey.Back in keys)
        assertFalse(WindowKey.Undo in keys)
        val localizer = Localizer(AppLanguage.En, variant = "android")
        val groups = shortcuts.groups({ localizer.text(it) }, "Peek")
        assertEquals(listOf("Peek", "Parcel", "Deliveries"), groups.map { it.label.toString() })
        assertEquals(
            listOf("Account", "Add parcel", "Search parcels", "Deliveries", "Passport", "Friends", "Refresh tracking"),
            groups[0].items.map { it.label.toString() },
        )
        val add = groups[0].items[1]
        assertEquals(KeyEvent.KEYCODE_N, add.keycode)
        assertEquals(KeyEvent.META_CTRL_ON, add.modifiers)

        openCardMenu()
        assertEquals(emptyList<WindowKey>(), shortcuts.available())
    }
}

private const val TABLET = "w1032dp-h1376dp-xhdpi"
private const val CTRL = KeyEvent.META_CTRL_ON or KeyEvent.META_CTRL_LEFT_ON
