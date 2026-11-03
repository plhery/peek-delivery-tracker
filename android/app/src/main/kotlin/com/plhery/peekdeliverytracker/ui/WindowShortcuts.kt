package com.plhery.peekdeliverytracker.ui

import android.view.KeyEvent
import android.view.KeyboardShortcutGroup
import android.view.KeyboardShortcutInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import com.plhery.peekdeliverytracker.core.DeliveriesKey
import com.plhery.peekdeliverytracker.core.ParcelMenuItem

/** Where the system's list of the app's keys puts a key, as the iPad's menu bar and its key list group them. */
enum class WindowKeyGroup {
    /** The app's menu, File, Edit and View: under the app's name, the copy having no File, Edit or View. */
    App,

    /** The Parcel menu (`CommandMenu(localizer.text("common.parcel"))`). */
    Parcel,

    /** The deliveries' own key buttons (`KeyboardShortcuts`), which no menu lists: under the tab's name. */
    Deliveries,
}

/**
 * A key the window answers, under the title the iPad's menu bar or key button gives it, in the order they list them:
 * Ctrl stands for ⌘ and Meta for ⌃, as the keys take them. Add's Esc has no title on the iPhone, and isn't listed.
 */
enum class WindowKey(val group: WindowKeyGroup, val titleKey: String, val keyCode: Int, val modifiers: Int = 0) {
    Account(WindowKeyGroup.App, "native.account", KeyEvent.KEYCODE_COMMA, KeyEvent.META_CTRL_ON),
    Add(WindowKeyGroup.App, "app.addParcel", KeyEvent.KEYCODE_N, KeyEvent.META_CTRL_ON),
    Find(WindowKeyGroup.App, "view.search", KeyEvent.KEYCODE_F, KeyEvent.META_CTRL_ON),
    Deliveries(WindowKeyGroup.App, "native.deliveries", KeyEvent.KEYCODE_1, KeyEvent.META_CTRL_ON),
    Passport(WindowKeyGroup.App, "passport.title", KeyEvent.KEYCODE_2, KeyEvent.META_CTRL_ON),
    Friends(WindowKeyGroup.App, "friends.title", KeyEvent.KEYCODE_3, KeyEvent.META_CTRL_ON),
    Refresh(WindowKeyGroup.App, "app.refresh", KeyEvent.KEYCODE_R, KeyEvent.META_CTRL_ON),
    CopyTrackingNumber(WindowKeyGroup.Parcel, "detail.copyTracking", KeyEvent.KEYCODE_C, KeyEvent.META_CTRL_ON or KeyEvent.META_SHIFT_ON),
    Archive(WindowKeyGroup.Parcel, "detail.archive", KeyEvent.KEYCODE_A, KeyEvent.META_CTRL_ON or KeyEvent.META_META_ON),
    Restore(WindowKeyGroup.Parcel, "detail.restore", KeyEvent.KEYCODE_A, KeyEvent.META_CTRL_ON or KeyEvent.META_META_ON),
    Delete(WindowKeyGroup.Parcel, "detail.delete", KeyEvent.KEYCODE_DEL, KeyEvent.META_CTRL_ON),
    CloseSearch(WindowKeyGroup.Deliveries, "view.hideControls", KeyEvent.KEYCODE_ESCAPE),
    Undo(WindowKeyGroup.Deliveries, "app.undo", KeyEvent.KEYCODE_Z, KeyEvent.META_CTRL_ON),
    Previous(WindowKeyGroup.Deliveries, "app.previousParcel", KeyEvent.KEYCODE_DPAD_UP),
    Next(WindowKeyGroup.Deliveries, "app.nextParcel", KeyEvent.KEYCODE_DPAD_DOWN),
    Open(WindowKeyGroup.Deliveries, "detail.open", KeyEvent.KEYCODE_ENTER),
    Back(WindowKeyGroup.Deliveries, "detail.back", KeyEvent.KEYCODE_ESCAPE),
    ;

    companion object {
        /** The tab Ctrl with 1, 2 or 3 chooses, in the tab bar's order. */
        val tabs: List<WindowKey> = listOf(Deliveries, Passport, Friends)

        /** A deliveries' key ([DeliveriesKey]). */
        fun of(key: DeliveriesKey): WindowKey = when (key) {
            DeliveriesKey.Add -> Add
            DeliveriesKey.Find -> Find
            DeliveriesKey.CloseSearch -> CloseSearch
            DeliveriesKey.Undo -> Undo
            DeliveriesKey.Previous -> Previous
            DeliveriesKey.Next -> Next
            DeliveriesKey.Open -> Open
        }

        /** A parcel's item's key (`ParcelMenuItem.shortcut`); null for an item with none. */
        fun of(item: ParcelMenuItem): WindowKey? = when (item) {
            ParcelMenuItem.CopyTrackingNumber -> CopyTrackingNumber
            ParcelMenuItem.Archive -> Archive
            ParcelMenuItem.Restore -> Restore
            ParcelMenuItem.Delete -> Delete
            else -> null
        }
    }
}

/**
 * The keys the window answers now, which the system lists over the app when asked (Meta+/), as an iPad lists them
 * holding ⌘ and in its menu bar, where an item whose key would do nothing is dimmed: here it is left out. Each screen
 * says, while it is composed, which of its keys it answers now ([WindowShortcutsEffect]); MainActivity hands them over
 * (`onProvideKeyboardShortcuts`).
 */
class WindowShortcuts {
    private val sources = mutableListOf<() -> List<WindowKey>>()

    internal fun add(source: () -> List<WindowKey>) {
        sources += source
    }

    internal fun remove(source: () -> List<WindowKey>) {
        sources -= source
    }

    /** The keys answered now, each once, in the order the menu bar lists them. */
    fun available(): List<WindowKey> = sources.flatMap { it() }.distinct().sorted()

    /** The keys answered now in their groups, titled in the app's language ([text]); the app's own under [app]. */
    fun groups(text: (String) -> String, app: String): List<KeyboardShortcutGroup> =
        available().groupBy { it.group }.toSortedMap().map { (group, keys) ->
            val title = when (group) {
                WindowKeyGroup.App -> app
                WindowKeyGroup.Parcel -> text("common.parcel")
                WindowKeyGroup.Deliveries -> text("native.deliveries")
            }
            KeyboardShortcutGroup(title, keys.map { KeyboardShortcutInfo(text(it.titleKey), it.keyCode, it.modifiers) })
        }
}

/** The window's list of the keys answered now; null where nothing lists them. */
val LocalWindowShortcuts = staticCompositionLocalOf<WindowShortcuts?> { null }

/** While this is composed, the window lists the keys [keys] says are answered now, read when the list is asked for. */
@Composable
fun WindowShortcutsEffect(keys: () -> List<WindowKey>) {
    val shortcuts = LocalWindowShortcuts.current ?: return
    val current by rememberUpdatedState(keys)
    DisposableEffect(shortcuts) {
        val source = { current() }
        shortcuts.add(source)
        onDispose { shortcuts.remove(source) }
    }
}
