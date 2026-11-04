package com.plhery.peekdeliverytracker.ui

import android.content.ClipboardManager
import android.content.res.Configuration
import android.os.SystemClock
import android.view.KeyEvent
import android.view.accessibility.AccessibilityManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.TrackingNumberCopy
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.trackingNumbers
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_MENU
import com.plhery.peekdeliverytracker.ui.deliveries.DELETE_POPOVER
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.INLINE_TOAST
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.SEARCH_FIELD
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.cardId
import com.plhery.peekdeliverytracker.ui.deliveries.menuItemTag
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The window's keys on a hardware keyboard (A-G10, A-G11, A-ST23, A-S07, A-G04, B-G06), each pressed and let go through
 * the system's input into this device's window (`Instrumentation.sendKeySync`), as a keyboard's keys come: what the
 * window's views don't use reaches the deliveries', the tabs', the account's, the menus' and the page's keys. Ctrl with
 * N adds, with F finds, with Z undoes an archive, with 1 to 3 chooses a tab, with R refreshes, with a comma opens the
 * account; Escape closes the search, Add and a page over the list; ↑, ↓ and Return move the parcel shown beside the
 * list; the menus' items carry the keys that press them. Whether the window lays the list and the page out side by
 * side is the app's own rule (A-S93), which each test reads from the deliveries, so the same tests run on a phone, a
 * foldable folded and unfolded, and a tablet; whether the menus write out their keys is the device's keyboard, as the
 * app reads it from the configuration. The keys the window lists for the system (Meta+/) are read from the list
 * MainActivity hands over (`WindowShortcuts`), given to the window here. Meta with Ctrl and A, the archive's key, is
 * never pressed: Android keeps every Meta combination for itself before any window sees it. Escape is only pressed
 * where something takes it, as a key no view uses turns into back, which leaves a window with nothing to go back to.
 * Signed in as the synthetic reader@example.com on fake deliveries ([ParcelWalk]), which reach no server; each test
 * keeps Zurich's time and the captures' moment, puts the device back in touch mode after it, which a key takes it out
 * of, and clears a clipboard it copied to.
 */
@RunWith(AndroidJUnit4::class)
class KeyboardDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val clipboard = checkNotNull(instrumentation.targetContext.getSystemService(ClipboardManager::class.java))
    private var copied = false
    private val shortcuts = WindowShortcuts()
    private val demo: List<Parcel> = DemoCards.parcels()
    private val gift = DemoCards.parcel("Birthday gift")

    @After
    fun backToTouch() {
        runCatching { instrumentation.setInTouchMode(true) }
        if (copied) clipboard.clearPrimaryClip()
    }

    // A-S07, A-G11: the keys listed.

    /**
     * A-S07, A-G11: what MainActivity hands the system to list (`onProvideKeyboardShortcuts`) is what the window answers
     * now. On the list: the account, Add, the search and the three tabs under the app's name, Refresh among them where
     * the list refreshes by key; beside a parcel also its page's items under Parcel, and ↑, ↓ and Return under
     * Deliveries; each with its key. Typing in the search, Hide search joins them and the page's and the cards' keys
     * rest; under Add nothing answers.
     */
    @Test
    fun theWindowListsTheKeysItAnswersNow() {
        show()
        val sideBySide = !walk.stacked()
        val refresh = refreshesByKey()
        val onTheList = buildList {
            addAll(listOf(WindowKey.Account, WindowKey.Add, WindowKey.Find, WindowKey.Deliveries, WindowKey.Passport, WindowKey.Friends))
            if (refresh) add(WindowKey.Refresh)
            if (sideBySide) {
                addAll(listOf(WindowKey.CopyTrackingNumber, WindowKey.Archive, WindowKey.Delete, WindowKey.Previous, WindowKey.Next, WindowKey.Open))
            }
        }
        assertEquals(onTheList, available())

        val groups = walk.onUi { shortcuts.groups({ walk.text(it) }, compose.activity.getString(R.string.app_name)) }
        assertEquals(if (sideBySide) listOf("Peek", "Parcel", "Deliveries") else listOf("Peek"), groups.map { it.label.toString() })
        assertEquals(
            listOf("Account", "Add parcel", "Search parcels", "Deliveries", "Passport", "Friends") + if (refresh) listOf("Refresh tracking") else emptyList(),
            groups[0].items.map { it.label.toString() },
        )
        val items = groups.flatMap { it.items }
        assertEquals(onTheList.map { walk.text(it.titleKey) }, items.map { it.label.toString() })
        assertEquals(onTheList.map { it.keyCode to it.modifiers }, items.map { it.keycode to it.modifiers })
        val add = groups[0].items[1]
        assertEquals(KeyEvent.KEYCODE_N, add.keycode)
        assertEquals(KeyEvent.META_CTRL_ON, add.modifiers)

        press(KeyEvent.KEYCODE_F, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.searchFocused } }
        val typing = buildList {
            addAll(listOf(WindowKey.Account, WindowKey.Add, WindowKey.Find, WindowKey.Deliveries, WindowKey.Passport, WindowKey.Friends))
            if (refresh) add(WindowKey.Refresh)
            add(WindowKey.CloseSearch)
        }
        assertEquals(typing, available())
        val hide = walk.onUi { shortcuts.groups({ walk.text(it) }, "Peek") }.single { it.label.toString() == "Deliveries" }.items.single()
        assertEquals("Hide search", hide.label.toString())
        assertEquals(KeyEvent.KEYCODE_ESCAPE, hide.keycode)
        assertEquals(0, hide.modifiers)
        press(KeyEvent.KEYCODE_ESCAPE)
        compose.waitUntil(LONG_MILLIS) { !walk.onUi { walk.deliveries.showingSearch } }
        walk.settle()
        assertEquals(onTheList, available())

        press(KeyEvent.KEYCODE_N, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        assertEquals(emptyList<WindowKey>(), available())
        closeAdd()
        assertEquals(onTheList, available())
    }

    // A-G10: the deliveries' keys.

    /**
     * A-G10, B-G06: Ctrl+N opens Add and Escape closes it; Ctrl+F opens the search with its field focused, and Escape,
     * which the field leaves to the deliveries, closes it.
     */
    @Test
    fun ctrlNOpensAddCtrlFTheSearchAndEscapeClosesEach() {
        show()
        press(KeyEvent.KEYCODE_N, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        assertNotNull(walk.onUi { walk.deliveries.addition })
        walk.awaitKeyboard(required = false)
        press(KeyEvent.KEYCODE_ESCAPE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) }
        assertNull(walk.onUi { walk.deliveries.addition })
        walk.awaitKeyboard(required = false)
        walk.settle()

        press(KeyEvent.KEYCODE_F, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.showingSearch && walk.deliveries.searchFocused } }
        assertTrue(walk.exists(SEARCH_FIELD))
        walk.awaitKeyboard(required = false)
        walk.shot("A-G10-search-by-key")
        press(KeyEvent.KEYCODE_ESCAPE)
        compose.waitUntil(LONG_MILLIS) { !walk.onUi { walk.deliveries.showingSearch } }
        assertFalse(walk.onUi { walk.deliveries.searchFocused })
    }

    /**
     * A-G10, A-G11: with no parcels, Ctrl+N still opens Add, while Ctrl+F finds nothing and Ctrl+R refreshes nothing:
     * neither is listed.
     */
    @Test
    fun withNoParcelsCtrlNStillAddsWhileCtrlFAndCtrlRRest() {
        show(parcels = emptyList())
        assertEquals(listOf(WindowKey.Account, WindowKey.Add, WindowKey.Deliveries, WindowKey.Passport, WindowKey.Friends), available())
        press(KeyEvent.KEYCODE_F, CTRL)
        assertFalse(walk.onUi { walk.deliveries.showingSearch })
        press(KeyEvent.KEYCODE_R, CTRL)
        assertEquals(0, walk.onUi { walk.source.refreshes })
        press(KeyEvent.KEYCODE_N, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        closeAdd()
    }

    /**
     * A-G10: Ctrl+Z brings back the parcel archived last while its toast shows, as its Undo does, and not while the
     * search field has the keys: archived from its card's menu, the parcel stays archived through Ctrl+Z typed in the
     * search, and comes back once Escape has closed the search.
     */
    @Test
    fun ctrlZBringsBackTheParcelArchivedLastWhileItsToastShowsButNotWhileTyping() {
        show()
        holdCard(gift)
        walk.tap(menuItemTag(ParcelMenuItem.Archive))
        compose.waitUntil(LONG_MILLIS) { walk.exists(INLINE_TOAST) }
        assertEquals(listOf(gift.id), walk.onUi { walk.source.archived.toList() })
        assertTrue(WindowKey.Undo in available())

        press(KeyEvent.KEYCODE_F, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.showingSearch } }
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.searchFocused } }
        assertFalse(WindowKey.Undo in available())
        press(KeyEvent.KEYCODE_Z, CTRL)
        assertEquals(emptyList<UUID>(), walk.onUi { walk.source.restored.toList() })
        press(KeyEvent.KEYCODE_ESCAPE)
        compose.waitUntil(LONG_MILLIS) { !walk.onUi { walk.deliveries.showingSearch } }
        assertTrue("The toast is still there", walk.exists(INLINE_TOAST))
        assertTrue(WindowKey.Undo in available())

        press(KeyEvent.KEYCODE_Z, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.restored.toList() } == listOf(gift.id) }
        compose.waitUntil(LONG_MILLIS) { !walk.exists(INLINE_TOAST) }
        walk.awaitKeyboard(required = false)
        walk.shot("A-G10-undo-by-key")
    }

    /**
     * A-G10: beside the list, Return opens the parcel shown there by itself, and ↓ and ↑ show the card after and
     * before it. Where the window stacks the page over the list, they do nothing to what is shown, and aren't listed.
     */
    @Test
    fun arrowsAndReturnMoveTheParcelBesideTheListOnlyWhenSideBySide() {
        show()
        if (walk.stacked()) {
            press(KeyEvent.KEYCODE_ENTER)
            press(KeyEvent.KEYCODE_DPAD_DOWN)
            press(KeyEvent.KEYCODE_DPAD_UP)
            assertNull(walk.shown())
            val keys = available()
            for (key in listOf(WindowKey.Previous, WindowKey.Next, WindowKey.Open)) assertFalse(key.name, key in keys)
            return
        }
        val rows = walk.onUi { walk.deliveries.rows }
        assertTrue("At least three cards: $rows", rows.size >= 3)
        assertEquals(rows[0], walk.shown())
        assertTrue(walk.onUi { walk.deliveries.navigator.navigation.shownAutomatically })

        press(KeyEvent.KEYCODE_ENTER)
        assertEquals(rows[0], walk.onUi { walk.deliveries.navigator.navigation.chosen })
        assertFalse(WindowKey.Open in available())
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        assertEquals(rows[1], walk.onUi { walk.deliveries.navigator.navigation.chosen })
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        assertEquals(rows[2], walk.shown())
        press(KeyEvent.KEYCODE_DPAD_UP)
        assertEquals(rows[1], walk.shown())
        walk.shot("A-G10-arrows")
    }

    // A-G11: the tabs, Refresh and the account.

    /** A-G11: Ctrl+2, Ctrl+3 and Ctrl+1 choose Passport, Friends and Deliveries; Ctrl+comma opens the account. */
    @Test
    fun ctrlWithANumberChoosesItsTabAndCtrlCommaOpensTheAccount() {
        show()
        press(KeyEvent.KEYCODE_2, CTRL)
        compose.waitUntil(LONG_MILLIS) { selected(PeekTab.Passport) }
        press(KeyEvent.KEYCODE_3, CTRL)
        compose.waitUntil(LONG_MILLIS) { selected(PeekTab.Friends) }
        press(KeyEvent.KEYCODE_1, CTRL)
        compose.waitUntil(LONG_MILLIS) { selected(PeekTab.Deliveries) }
        assertFalse(walk.onUi { walk.deliveries.showingAccount })

        press(KeyEvent.KEYCODE_COMMA, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SHEET) }
        assertTrue(walk.onUi { walk.deliveries.showingAccount })
        walk.shot("A-G11-account-by-key")
        walk.back()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SETTINGS_SHEET) }
    }

    /**
     * A-G11: Ctrl+R checks every parcel where the list refreshes by key (a tablet's menu bar, or the refresh button
     * beside a parcel or for TalkBack), and does nothing while that check runs, when it isn't listed; elsewhere, a
     * phone's list, it does nothing and isn't listed.
     */
    @Test
    fun ctrlRRefreshesWhereTheListDoesByKeyAndNotWhileACheckRuns() {
        val checking = CompletableDeferred<ParcelStore.RefreshResult?>()
        show { this.checking = checking }
        if (!refreshesByKey()) {
            assertFalse(WindowKey.Refresh in available())
            press(KeyEvent.KEYCODE_R, CTRL)
            assertEquals(0, walk.onUi { walk.source.refreshes })
            return
        }
        assertTrue(WindowKey.Refresh in available())
        press(KeyEvent.KEYCODE_R, CTRL)
        assertEquals(1, walk.onUi { walk.source.refreshes })
        assertTrue(walk.onUi { walk.source.refreshing.value })
        assertFalse(WindowKey.Refresh in available())
        walk.shot("A-G11-refresh-by-key")
        press(KeyEvent.KEYCODE_R, CTRL)
        assertEquals(1, walk.onUi { walk.source.refreshes })

        walk.onUi { checking.complete(ParcelStore.RefreshResult.Unchanged) }
        compose.waitUntil(LONG_MILLIS) { !walk.onUi { walk.source.refreshing.value } }
        walk.settle()
        assertTrue(WindowKey.Refresh in available())
        press(KeyEvent.KEYCODE_R, CTRL)
        assertEquals(2, walk.onUi { walk.source.refreshes })
    }

    // A-ST23: what stands over the window.

    /**
     * A-ST23: with Add over the window, Ctrl+comma, Ctrl+2, Ctrl+3 and Ctrl+N do nothing, and nothing is listed; with
     * the account over it, Ctrl+N and Ctrl+2 do nothing; once it has gone, Ctrl+2 chooses Passport.
     */
    @Test
    fun withAddOrTheAccountOverTheWindowItsKeysRest() {
        show()
        press(KeyEvent.KEYCODE_N, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        val addition = walk.onUi { walk.deliveries.addition }
        assertNotNull(addition)
        assertEquals(emptyList<WindowKey>(), available())
        press(KeyEvent.KEYCODE_COMMA, CTRL)
        press(KeyEvent.KEYCODE_2, CTRL)
        press(KeyEvent.KEYCODE_3, CTRL)
        press(KeyEvent.KEYCODE_N, CTRL)
        assertFalse(walk.onUi { walk.deliveries.showingAccount })
        assertFalse(walk.exists(SETTINGS_SHEET))
        assertEquals(addition, walk.onUi { walk.deliveries.addition })
        walk.awaitKeyboard(required = false)
        walk.shot("A-ST23-add-over-the-window")
        closeAdd()
        // The tab bar says nothing under what stands over the window: read once that has gone.
        assertTrue(selected(PeekTab.Deliveries))

        press(KeyEvent.KEYCODE_COMMA, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SHEET) }
        assertEquals(emptyList<WindowKey>(), available())
        press(KeyEvent.KEYCODE_N, CTRL)
        press(KeyEvent.KEYCODE_2, CTRL)
        assertNull(walk.onUi { walk.deliveries.addition })
        walk.back()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SETTINGS_SHEET) }
        walk.settle()
        assertTrue(selected(PeekTab.Deliveries))
        press(KeyEvent.KEYCODE_2, CTRL)
        compose.waitUntil(LONG_MILLIS) { selected(PeekTab.Passport) }
    }

    // A-G04: a card's menu.

    /**
     * A-G04: the menu of the card shown beside the list, with a keyboard connected, writes out under its items the keys
     * that press them (Shift+Ctrl+C, Alt+Ctrl+A, Ctrl+Backspace), which the window lists alone; Ctrl+Shift+Backspace
     * presses nothing, and Ctrl+Shift+C copies the parcel's number and closes the menu. Another card's menu, a card's
     * menu where the window stacks the page over the list, and any without a keyboard show no keys, and Ctrl+Shift+C
     * does nothing there.
     */
    @Test
    fun theMenuOfTheCardBesideTheListShowsItsKeysWhichPressItsItems() {
        show()
        val sideBySide = !walk.stacked()
        val keys = sideBySide && keyboardConnected()
        if (sideBySide) walk.open(gift)
        holdCard(gift)
        assertMenuItems(ParcelMenuItem.menu(gift, onCard = true).flatten(), keys)
        assertEquals(if (keys) listOf(WindowKey.CopyTrackingNumber, WindowKey.Archive, WindowKey.Delete) else emptyList(), available())
        walk.shot("A-G04-card-menu-keys")
        press(KeyEvent.KEYCODE_DEL, CTRL or SHIFT)
        assertNull(walk.onUi { walk.deliveries.deleting })
        assertTrue(walk.exists(CARD_MENU))

        val before = copies()
        press(KeyEvent.KEYCODE_C, CTRL or SHIFT)
        if (!keys) {
            assertEquals(before, copies())
            assertTrue(walk.exists(CARD_MENU))
            closeMenu(CARD_MENU)
            return
        }
        copied = true
        compose.waitUntil(LONG_MILLIS) { copies() == before + 1 }
        compose.waitUntil(LONG_MILLIS) { !walk.exists(CARD_MENU) }
        assertEquals(numberCopied(gift), clip())

        val trainers = DemoCards.parcel("New trainers")
        holdCard(trainers)
        assertMenuItems(ParcelMenuItem.menu(trainers, onCard = true).flatten(), keys = false)
        assertEquals(emptyList<WindowKey>(), available())
        press(KeyEvent.KEYCODE_C, CTRL or SHIFT)
        assertEquals(before + 1, copies())
        closeMenu(CARD_MENU)
    }

    // B-G06: the parcel's page.

    /**
     * B-G06: an open page lists its parcel's keys under Parcel, and Escape, where it stands over the list; its •••
     * menu has Rename, Copy tracking number, Change carrier, Archive and Delete, with their keys written out while a
     * keyboard is connected. Ctrl+Shift+C copies its number (the page shows it copied), Ctrl+Backspace asks about
     * deleting it, and Escape goes back to the list from a page over it.
     */
    @Test
    fun thePagesItemsCarryTheirKeysWhichActOnItsParcelAndEscapeGoesBackOverTheList() {
        show()
        walk.open(gift)
        val stacked = walk.stacked()
        val listed = available()
        assertEquals(listOf(WindowKey.CopyTrackingNumber, WindowKey.Archive, WindowKey.Delete), listed.filter { it.group == WindowKeyGroup.Parcel })
        assertEquals(stacked, WindowKey.Back in listed)

        walk.tap(PAGE_MENU_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_MENU) }
        walk.settle()
        val keys = keyboardConnected()
        val items = listOf(
            ParcelMenuItem.Rename, ParcelMenuItem.CopyTrackingNumber, ParcelMenuItem.ChangeCarrier, ParcelMenuItem.Archive, ParcelMenuItem.Delete,
        )
        assertEquals(items, ParcelMenuItem.menu(gift, onCard = false).flatten())
        assertMenuItems(items, keys)
        assertEquals(if (keys) listOf(WindowKey.CopyTrackingNumber, WindowKey.Archive, WindowKey.Delete) else emptyList(), available())
        walk.shot("B-G06-page-menu")
        closeMenu(PAGE_MENU)

        copied = true
        val entry = gift.trackingNumbers.first()
        press(KeyEvent.KEYCODE_C, CTRL or SHIFT, settles = false)
        val copy = walk.onUi { walk.deliveries.copiedNumber }
        assertEquals(gift.id, copy?.pageId)
        assertEquals(entry.number, copy?.number)
        assertEquals(numberCopied(gift), clip())
        walk.settle()

        press(KeyEvent.KEYCODE_DEL, CTRL)
        compose.waitUntil(LONG_MILLIS) { walk.exists(DELETE_POPOVER) }
        assertEquals(gift.id, walk.onUi { walk.deliveries.deleting }?.parcelId)
        walk.back()
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.deleting } == null && !walk.exists(DELETE_POPOVER) }
        walk.settle()
        assertEquals(gift.id, walk.shown())
        assertEquals(emptyList<UUID>(), walk.onUi { walk.source.deleted.toList() })

        if (stacked) {
            press(KeyEvent.KEYCODE_ESCAPE)
            compose.waitUntil(LONG_MILLIS) { walk.shown() == null }
        }
    }

    /**
     * B-G06, X-AD22: Ctrl+Alt+A, the key for ⌃⌘A (the system keeps every combination with Meta, which no app sees), comes
     * through the window and archives the open page's parcel.
     */
    @Test
    fun ctrlAltAArchivesTheOpenPagesParcel() {
        show()
        walk.open(gift)
        walk.settle()
        press(KeyEvent.KEYCODE_A, CTRL or ALT)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.archived.toList() } == listOf(gift.id) }
        walk.settle()
        walk.shot("X-AD22-archive-by-key")
    }

    /**
     * B-G06: while the page's archive is being saved, Delete waits: the window leaves its key out and Ctrl+Backspace asks
     * nothing; the archive goes through once saved.
     */
    @Test
    fun deleteWaitsWhileThePagesArchiveIsSaved() {
        val archiving = CompletableDeferred<Unit>()
        show { this.archiving = archiving }
        walk.open(gift)
        assertTrue(WindowKey.Delete in available())
        walk.tap(PAGE_MENU_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_MENU) }
        walk.settle()
        walk.tap(menuItemTag(ParcelMenuItem.Archive))
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.pageWorking(gift.id) } }
        compose.waitUntil(LONG_MILLIS) { !walk.exists(PAGE_MENU) }
        walk.settle()

        assertFalse(WindowKey.Delete in available())
        press(KeyEvent.KEYCODE_DEL, CTRL)
        assertNull(walk.onUi { walk.deliveries.deleting })
        assertFalse(walk.exists(DELETE_POPOVER))
        walk.shot("B-G06-delete-waits")

        walk.onUi { archiving.complete(Unit) }
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.archived.toList() } == listOf(gift.id) }
        walk.settle()
        assertEquals(emptyList<UUID>(), walk.onUi { walk.source.deleted.toList() })
    }

    // The window.

    /** `parcels` shown, the demo's unless said, the window listing its keys for the system as MainActivity's does. */
    private fun show(parcels: List<Parcel> = demo, prepare: FakeDeliveries.() -> Unit = {}) = walk.show(
        parcels,
        around = { content -> CompositionLocalProvider(LocalWindowShortcuts provides shortcuts, content = content) },
        prepare = prepare,
    )

    /** The keys the window would list now, as the system asks for them. */
    private fun available(): List<WindowKey> = walk.onUi { shortcuts.available() }

    /**
     * The key `code`, with `meta` held, pressed and let go on a keyboard, through the system's input to the window in
     * front, once the window has handled each; then a second of the app's time where it `settles`.
     */
    private fun press(code: Int, meta: Int = 0, settles: Boolean = true) {
        val down = SystemClock.uptimeMillis()
        instrumentation.sendKeySync(KeyEvent(down, down, KeyEvent.ACTION_DOWN, code, 0, meta))
        instrumentation.sendKeySync(KeyEvent(down, SystemClock.uptimeMillis(), KeyEvent.ACTION_UP, code, 0, meta))
        if (settles) walk.settle() else compose.waitForIdle()
    }

    /** Whether a hardware keyboard is connected and in use, as the menus read it to write out their keys. */
    private fun keyboardConnected(): Boolean {
        val configuration = walk.onUi { compose.activity.resources.configuration }
        return configuration.keyboard != Configuration.KEYBOARD_NOKEYS && configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO
    }

    /**
     * Whether Ctrl+R refreshes the list with nothing over it: on a tablet, whose menu bar has Refresh, beside a parcel,
     * where the list has its refresh button, or with TalkBack on, which adds it.
     */
    private fun refreshesByKey(): Boolean {
        val activity = compose.activity
        val touch = activity.getSystemService(AccessibilityManager::class.java).isTouchExplorationEnabled
        return !walk.stacked() || touch || walk.onUi { Device.isPad(activity) }
    }

    private fun tabItem(tab: PeekTab): SemanticsMatcher =
        hasContentDescription(walk.text(tab.copyKey)) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)

    private fun selected(tab: PeekTab): Boolean =
        compose.onAllNodes(tabItem(tab)).fetchSemanticsNodes().any { it.config.getOrNull(SemanticsProperties.Selected) == true }

    /** The card of `parcel` held down, as a finger opens its menu, the list scrolled to its item first. */
    private fun holdCard(parcel: Parcel) {
        val item = walk.onUi { walk.deliveries.itemKeys.firstOrNull { cardId(it) == parcel.id } }
        checkNotNull(item) { "No card for ${parcel.label}" }
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey(item)
        compose.onAllNodes(hasContentDescription(parcel.label, substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST)))
            .onFirst().performTouchInput { longClick() }
        compose.waitUntil(LONG_MILLIS) { walk.exists(CARD_MENU) }
        walk.settle()
    }

    /** The menu tagged `tag` closed with the system's back. */
    private fun closeMenu(tag: String) {
        walk.back()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(tag) }
        walk.settle()
    }

    /** Add closed from its Cancel, and gone. */
    private fun closeAdd() {
        walk.tap(ADD_CLOSE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) }
        walk.awaitKeyboard(required = false)
        walk.settle()
    }

    /**
     * The open menu's `items`, each read as TalkBack reads it: its title, then, where the menu writes out its `keys`,
     * the key that presses it.
     */
    private fun assertMenuItems(items: List<ParcelMenuItem>, keys: Boolean) {
        for (item in items) {
            val title = walk.text(item.titleKey)
            val key = when (item) {
                ParcelMenuItem.CopyTrackingNumber -> "Shift+Ctrl+C"
                ParcelMenuItem.Archive, ParcelMenuItem.Restore -> "Alt+Ctrl+A"
                ParcelMenuItem.Delete -> "Ctrl+Backspace"
                else -> null
            }
            val said = compose.onAllNodes(hasTestTag(menuItemTag(item))).onFirst().fetchSemanticsNode()
                .config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString()
            assertEquals(item.name, if (keys && key != null) "$title, $key" else title, said)
        }
    }

    /** How many numbers were copied so far. */
    private fun copies(): Int = walk.onUi { walk.source.actions.count { it == "parcel-copy-tracking" } }

    /** The number of `parcel` as a copy puts it on the clipboard, its carrier's way. */
    private fun numberCopied(parcel: Parcel): String = parcel.trackingNumbers.first().let { TrackingNumberCopy.text(it.number, it.carrier) }

    /** What the clipboard holds as text, read by the app in front. */
    private fun clip(): String? = walk.onUi { clipboard.primaryClip?.takeIf { it.itemCount > 0 }?.getItemAt(0)?.text?.toString() }

    private companion object {
        const val FOLDER = "keyswalk"
        const val CTRL = KeyEvent.META_CTRL_ON or KeyEvent.META_CTRL_LEFT_ON
        const val SHIFT = KeyEvent.META_SHIFT_ON or KeyEvent.META_SHIFT_LEFT_ON
        const val ALT = KeyEvent.META_ALT_ON or KeyEvent.META_ALT_LEFT_ON
    }
}
