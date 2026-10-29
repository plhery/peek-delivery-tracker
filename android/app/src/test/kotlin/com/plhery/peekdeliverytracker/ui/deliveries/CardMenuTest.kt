package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.ClipboardManager
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.click
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.TrackingNumberCopy
import com.plhery.peekdeliverytracker.core.isArchived
import com.plhery.peekdeliverytracker.core.trackingNumbers
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A card's menu on a long press (A-S41, A-G04): its items for an active parcel and an archived one, what each does,
 * the question before a delete, what closes it, the copy, and the keys it shows on the parcel the keyboard acts on.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class CardMenuTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries

    private fun show() {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.setContent { RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false) }
        compose.waitForIdle()
    }

    private fun longPress(name: String) {
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed(name))
        compose.card(name).performTouchInput { longClick() }
        compose.waitForIdle()
    }

    private fun item(item: ParcelMenuItem) = compose.onNodeWithTag(menuItemTag(item))

    private fun choose(item: ParcelMenuItem) {
        item(item).performClick()
        compose.waitForIdle()
    }

    private fun shownItems(): List<ParcelMenuItem> =
        ParcelMenuItem.entries.filter { compose.onAllNodes(hasTestTag(menuItemTag(it))).fetchSemanticsNodes().isNotEmpty() }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    private fun inList(name: String) = compose.onNode(cardNamed(name) and hasAnyAncestor(hasTestTag(PARCEL_LIST)))

    @Test
    fun aLongPressOpensTheMenuWithTheParcelsItemsAndOpenShowsIt() {
        show()
        longPress("Birthday gift")
        compose.onNodeWithTag(CARD_MENU).assertExists()
        assertEquals(
            listOf(
                ParcelMenuItem.Open, ParcelMenuItem.Rename, ParcelMenuItem.Share, ParcelMenuItem.CopyTrackingNumber,
                ParcelMenuItem.ChangeCarrier, ParcelMenuItem.Archive, ParcelMenuItem.Delete,
            ),
            shownItems(),
        )
        // Read as their titles; the keys show only where a keyboard acts on the parcel.
        item(ParcelMenuItem.Delete).assertContentDescriptionEquals("Delete permanently")
        choose(ParcelMenuItem.Open)
        compose.onNodeWithTag(CARD_MENU).assertDoesNotExist()
        compose.onNode(hasText("Birthday gift", substring = true) and hasAnyAncestor(hasTestTag(PARCEL_PAGE))).assertExists()
    }

    @Test
    fun theMenusArchiveArchivesTheParcelWithItsUndo() {
        show()
        longPress("Birthday gift")
        choose(ParcelMenuItem.Archive)
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.archived)
        compose.onNode(hasTestTag(INLINE_TOAST) and hasContentDescription("Birthday gift", substring = true)).assertExists()
        inList("Birthday gift").assertDoesNotExist()
    }

    @Test
    fun deleteAsksFirstAndOnlyAYesDeletes() {
        show()
        longPress("Birthday gift")
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(CARD_MENU).assertDoesNotExist()
        compose.onNodeWithTag(DELETE_CONFIRMATION).assertExists()
        compose.onNode(hasText("Delete this parcel permanently?")).assertExists()
        compose.onNodeWithTag(DELETE_CANCEL).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(DELETE_CONFIRMATION).assertDoesNotExist()
        assertEquals(emptyList<Any>(), source.deleted)

        // Back answers no as well.
        longPress("Birthday gift")
        choose(ParcelMenuItem.Delete)
        back()
        compose.onNodeWithTag(DELETE_CONFIRMATION).assertDoesNotExist()
        assertEquals(emptyList<Any>(), source.deleted)

        longPress("Birthday gift")
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(DELETE_CONFIRM).performClick()
        compose.waitForIdle()
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.deleted)
        inList("Birthday gift").assertDoesNotExist()
    }

    @Test
    fun aTapOutsideOrBackClosesTheMenuAndDoesNothingElse() {
        show()
        longPress("Birthday gift")
        compose.onNodeWithTag(CARD_MENU).performTouchInput { click(Offset(width - 10f, 10f)) }
        compose.waitForIdle()
        compose.onNodeWithTag(CARD_MENU).assertDoesNotExist()

        longPress("Birthday gift")
        back()
        compose.onNodeWithTag(CARD_MENU).assertDoesNotExist()
        compose.onNodeWithTag(PARCEL_PAGE).assertDoesNotExist()
        compose.onNodeWithTag(PARCEL_LIST).assertExists()
        assertEquals(emptyList<Any>(), source.archived)
        assertEquals(emptyList<Any>(), source.deleted)
    }

    @Test
    fun copyPutsTheNumberOnTheClipboardWhichTheSystemConfirms() {
        show()
        longPress("Birthday gift")
        choose(ParcelMenuItem.CopyTrackingNumber)
        val parcel = DemoCards.parcel("Birthday gift")
        val entry = parcel.trackingNumbers.first()
        val clipboard = compose.activity.getSystemService(ClipboardManager::class.java)
        assertEquals(TrackingNumberCopy.text(entry.number, entry.carrier), clipboard.primaryClip?.getItemAt(0)?.text?.toString())
        assertEquals(listOf("parcel-copy-tracking"), source.actions.filter { it == "parcel-copy-tracking" })
        // Android 13 and later show the copy themselves: no toast of the app's, but TalkBack hears of it.
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
        compose.onNodeWithTag(ANNOUNCEMENT, useUnmergedTree = true).assertContentDescriptionEquals("Copied")
    }

    @Test
    @Config(sdk = [31])
    fun beforeAndroid13TheAppSaysTheNumberWasCopied() {
        show()
        longPress("Birthday gift")
        choose(ParcelMenuItem.CopyTrackingNumber)
        compose.onNode(hasTestTag(INLINE_TOAST) and hasContentDescription("Copied")).assertExists()
    }

    @Test
    fun anArchivedRowsMenuRestoresIt() {
        show()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.onNode(hasContentDescription("Archived", substring = true) and hasClickAction()).performClick()
        compose.waitForIdle()
        longPress("Linen weekend bag")
        assertEquals(ParcelMenuItem.Restore, shownItems().single { it == ParcelMenuItem.Restore || it == ParcelMenuItem.Archive })
        choose(ParcelMenuItem.Restore)
        assertEquals(listOf(DemoCards.parcel("Linen weekend bag").id), source.restored)
    }

    @Test
    fun aFailedChangeSaysWhyInAnAlert() {
        show()
        source.failure = IllegalStateException("No connection")
        longPress("Birthday gift")
        choose(ParcelMenuItem.Archive)
        compose.onNodeWithTag(ACTION_ERROR).assertExists()
        compose.onNode(hasText("Something went wrong")).assertExists()
        compose.onNode(hasText("No connection")).assertExists()
        inList("Birthday gift").assertExists()
        back()
        compose.onNodeWithTag(ACTION_ERROR).assertDoesNotExist()
    }

    /** ParcelLogicTests' check of the menu's keys, on the keys as Android reads them. */
    @Test
    fun aParcelsMenuShowsMailsKeysForCopyArchiveAndDeleteEachOnce() {
        val active = DemoCards.parcel("Birthday gift")
        val archived = DemoCards.parcel("Linen weekend bag")
        for (parcel in listOf(active, archived)) {
            for (onCard in listOf(true, false)) {
                val keyed = ParcelMenuItem.menu(parcel, onCard).flatten().filter { it.shortcutLabel != null }
                val change = if (parcel.isArchived) ParcelMenuItem.Restore else ParcelMenuItem.Archive
                assertEquals("${parcel.label} onCard=$onCard", listOf(ParcelMenuItem.CopyTrackingNumber, change, ParcelMenuItem.Delete), keyed)
            }
        }
        assertEquals(ParcelMenuItem.Archive.shortcutLabel, ParcelMenuItem.Restore.shortcutLabel)

        fun key(code: Int, meta: Int) = KeyEvent(0, 0, KeyEvent.ACTION_DOWN, code, 0, meta)
        val ctrl = KeyEvent.META_CTRL_ON or KeyEvent.META_CTRL_LEFT_ON
        val items = ParcelMenuItem.menu(active, onCard = true).flatten()
        assertEquals(ParcelMenuItem.CopyTrackingNumber, ParcelMenuItem.forShortcut(key(KeyEvent.KEYCODE_C, ctrl or KeyEvent.META_SHIFT_ON), items))
        assertEquals(ParcelMenuItem.Archive, ParcelMenuItem.forShortcut(key(KeyEvent.KEYCODE_A, ctrl or KeyEvent.META_META_ON), items))
        assertEquals(ParcelMenuItem.Delete, ParcelMenuItem.forShortcut(key(KeyEvent.KEYCODE_DEL, ctrl), items))
        assertEquals(
            ParcelMenuItem.Restore,
            ParcelMenuItem.forShortcut(key(KeyEvent.KEYCODE_A, ctrl or KeyEvent.META_META_ON), ParcelMenuItem.menu(archived, onCard = true).flatten()),
        )
        // Plain Ctrl+C copies what is selected, not the parcel's number.
        assertNull(ParcelMenuItem.forShortcut(key(KeyEvent.KEYCODE_C, ctrl), items))
        assertNull(ParcelMenuItem.forShortcut(key(KeyEvent.KEYCODE_A, ctrl), items))
    }
}
