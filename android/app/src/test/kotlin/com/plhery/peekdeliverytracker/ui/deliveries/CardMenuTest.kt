package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.ClipboardManager
import android.view.KeyEvent
import android.view.View
import android.view.accessibility.AccessibilityManager
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.click
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
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
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
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

    private fun longPress(name: String, scroll: Boolean = true) {
        if (scroll) compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed(name))
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

    /** A tap away from the delete question, at the window's bottom left. */
    private fun tapOutside() {
        compose.onRoot().performTouchInput { click(Offset(10f, height - 10f)) }
        compose.waitForIdle()
    }

    /** The delete question's content, its arrow left out, in points. */
    private fun question(): Rect = drawn(compose.onNodeWithTag(DELETE_POPOVER))

    @Test
    fun deleteAsksFirstInAPopoverWithoutCancelAndOnlyAYesDeletesOnceItHasGone() {
        show()
        longPress("Birthday gift")
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(CARD_MENU).assertDoesNotExist()
        compose.onNodeWithTag(DELETE_POPOVER).assertExists()
        compose.onNode(hasText("Delete this parcel permanently?")).assertExists()
        compose.onNode(SemanticsMatcher.expectValue(SemanticsProperties.PaneTitle, "Delete this parcel permanently?")).assertExists()
        // No Cancel: a tap outside answers no.
        compose.onNode(hasText("Cancel")).assertDoesNotExist()
        tapOutside()
        compose.onNodeWithTag(DELETE_POPOVER).assertDoesNotExist()
        assertEquals(emptyList<Any>(), source.deleted)

        // Back answers no as well.
        longPress("Birthday gift")
        choose(ParcelMenuItem.Delete)
        back()
        compose.onNodeWithTag(DELETE_POPOVER).assertDoesNotExist()
        assertEquals(emptyList<Any>(), source.deleted)

        longPress("Birthday gift")
        choose(ParcelMenuItem.Delete)
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(DELETE_CONFIRM).assertTextEquals("Delete permanently").performClick()
        compose.mainClock.advanceTimeByFrame()
        // Not while the popover goes.
        assertEquals(emptyList<Any>(), source.deleted)
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.deleted)
        inList("Birthday gift").assertDoesNotExist()
    }

    @Test
    fun theQuestionPointsAtItsCardOverItWhereItFitsAndUnderItOtherwise() {
        show()
        longPress("Birthday gift")
        choose(ParcelMenuItem.Delete)
        var card = drawn(compose.onNode(cardNamed("Birthday gift"), useUnmergedTree = true))
        var asked = question()
        assertEquals(238f, asked.width, 0.5f)
        // Its arrow, 13 tall, between it and the card's top, centred on the card.
        assertEquals(card.top - 13f, asked.bottom, 1f)
        assertEquals(card.center.x, asked.center.x, 1f)
        back()

        // Next up's card, the first, leaves no room over it.
        longPress("New trainers")
        choose(ParcelMenuItem.Delete)
        card = drawn(compose.onNode(cardNamed("New trainers"), useUnmergedTree = true))
        asked = question()
        assertEquals(card.bottom + 13f, asked.top, 1f)
        assertEquals(card.center.x, asked.center.x, 1f)
        back()
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
        back()
        compose.onNodeWithTag(ACTION_ERROR).assertDoesNotExist()
        inList("Birthday gift").assertExists()
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

    // Where the menu stands (finding the room on any window), the card it lifts, and TalkBack.

    /** The window, in points. */
    private fun window() = compose.onRoot().getUnclippedBoundsInRoot()

    /** Where `node` is drawn, in points, as large as it is drawn. */
    private fun drawn(node: SemanticsNodeInteraction): Rect {
        val bounds = node.fetchSemanticsNode().boundsInRoot
        val scale = compose.density.density
        return Rect(bounds.left / scale, bounds.top / scale, bounds.right / scale, bounds.bottom / scale)
    }

    /** The card the menu lifts, as it is drawn. */
    private fun lifted(): Rect = drawn(compose.onNodeWithTag(CARD_MENU_PREVIEW))

    /**
     * Every item of the open menu scrolled to in turn, and each then whole on screen: below the window's top, above its
     * bottom, beside the lifted card or over or under it rather than on it.
     */
    private fun assertEveryItemOnScreen() {
        val window = window().let { Rect(it.left.value, it.top.value, it.right.value, it.bottom.value) }
        val preview = lifted()
        assertTrue("The card lifted on screen: $preview", preview.top >= window.top && preview.bottom <= window.bottom)
        val items = shownItems()
        assertTrue(items.isNotEmpty())
        for (shown in items) {
            item(shown).performScrollTo()
            val bounds = item(shown).getUnclippedBoundsInRoot().let { Rect(it.left.value, it.top.value, it.right.value, it.bottom.value) }
            assertTrue("$shown on screen: $bounds in $window", bounds.top >= window.top - 0.5f && bounds.bottom <= window.bottom + 0.5f)
            assertTrue("$shown on screen: $bounds in $window", bounds.left >= window.left && bounds.right <= window.right)
            assertTrue("$shown clear of the card: $bounds, $preview", !bounds.overlaps(preview))
        }
    }

    /** Deleting from the menu, its last item: it is there to tap. */
    private fun assertTheLastItemTaps() {
        item(ParcelMenuItem.Delete).performScrollTo()
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(DELETE_POPOVER).assertExists()
    }

    @Test
    @Config(qualifiers = LANDSCAPE_PHONE)
    fun onAPhoneOnItsSideNextUpsMenuStandsBesideItAndScrolls() {
        show()
        longPress("New trainers")
        assertEveryItemOnScreen()
        val preview = lifted()
        val first = drawn(item(ParcelMenuItem.Open))
        assertTrue("Beside the card: $first, $preview", first.left >= preview.right || first.right <= preview.left)
        assertTheLastItemTaps()
    }

    @Test
    @Config(qualifiers = LANDSCAPE_PHONE)
    fun onAPhoneOnItsSideACardNearTheBottomKeepsItsWholeMenuOnScreen() {
        show()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed("Matcha ritual"))
        compose.waitForIdle()
        // The card scrolled down until its foot is just over the tab bar.
        val card = compose.card("Matcha ritual").getUnclippedBoundsInRoot()
        val down = (window().bottom - 80.dp - card.bottom).value * compose.density.density
        compose.onNodeWithTag(PARCEL_LIST).performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, -down) }
        compose.waitForIdle()
        longPress("Matcha ritual", scroll = false)
        assertEveryItemOnScreen()
        assertTheLastItemTaps()
    }

    @Test
    @Config(qualifiers = TABLET)
    fun besideAParcelTheMenuStandsUnderItsCardInTheListsColumn() {
        show()
        longPress("Birthday gift")
        assertEveryItemOnScreen()
        assertTrue(drawn(item(ParcelMenuItem.Open)).top >= lifted().bottom)
        assertTheLastItemTaps()
    }

    /** In a regular window the question is wider, and over its card though there is more room under it. */
    @Test
    @Config(qualifiers = TABLET)
    fun besideAParcelTheQuestionStandsOverItsCard() {
        show()
        longPress("Birthday gift")
        choose(ParcelMenuItem.Delete)
        val card = drawn(compose.onNode(cardNamed("Birthday gift"), useUnmergedTree = true))
        val asked = question()
        assertEquals(288f, asked.width, 0.5f)
        assertTrue("More room under $card", window().bottom.value - card.bottom > card.top)
        assertEquals(card.top - 13f, asked.bottom, 1f)
        assertEquals(card.center.x, asked.center.x, 1f)
        tapOutside()
        compose.onNodeWithTag(DELETE_POPOVER).assertDoesNotExist()
        assertEquals(emptyList<Any>(), source.deleted)
    }

    @Test
    @Config(qualifiers = FOLDABLE_INNER)
    fun onAFoldablesInnerScreenNextUpsMenuFitsUnderOrOverIt() {
        show()
        longPress("New trainers")
        assertEveryItemOnScreen()
        assertTheLastItemTaps()
    }

    /** The menu opened as TalkBack's long press does, whatever covers the card. */
    private fun openMenu(name: String) {
        compose.card(name).performSemanticsAction(SemanticsActions.OnLongClick)
        compose.waitForIdle()
    }

    /** The card lifted has the card's own proportions, whatever size it is drawn at: all of it, cut nowhere. */
    private fun assertWholeCardLifted(card: DpRect) {
        val preview = lifted()
        assertEquals(card.height.value / card.width.value, preview.height / preview.width, 0.01f)
    }

    @Test
    fun aCardTheScreensFootCutsIsLiftedWhole() {
        show()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed("Matcha ritual"))
        compose.waitForIdle()
        val top = compose.card("Matcha ritual").getUnclippedBoundsInRoot()
        // Its middle on the window's foot.
        val down = (window().bottom - (top.top + top.bottom) / 2).value * compose.density.density
        compose.onNodeWithTag(PARCEL_LIST).performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, -down) }
        compose.waitForIdle()
        val card = compose.card("Matcha ritual").getUnclippedBoundsInRoot()
        assertTrue("Cut by the foot: $card", card.top < window().bottom && card.bottom > window().bottom)
        openMenu("Matcha ritual")
        assertWholeCardLifted(card)
    }

    @Test
    fun aCardTheScreensTopCutsIsLiftedWhole() {
        show()
        val top = compose.card("Birthday gift").getUnclippedBoundsInRoot()
        // Its middle on the window's top.
        val up = ((top.top + top.bottom) / 2 - window().top).value * compose.density.density
        compose.onNodeWithTag(PARCEL_LIST).performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, up) }
        compose.waitForIdle()
        val card = compose.card("Birthday gift").getUnclippedBoundsInRoot()
        assertTrue("Cut by the top: $card", card.top < window().top && card.bottom > window().top)
        openMenu("Birthday gift")
        assertWholeCardLifted(card)
    }

    /** The window's view, which TalkBack reads, with TalkBack on. */
    private fun talkBack(): View {
        val root = compose.onRoot().fetchSemanticsNode().root as ViewRootForTest
        val manager = shadowOf(compose.activity.getSystemService(AccessibilityManager::class.java))
        compose.runOnUiThread {
            manager.setEnabled(true)
            manager.setTouchExplorationEnabled(true)
            root.forceAccessibilityForTesting(true)
        }
        return root.view
    }

    /** Whether TalkBack's focus is on `node`. */
    private fun SemanticsNodeInteraction.hasTalkBackFocus(view: View): Boolean {
        val id = fetchSemanticsNode().id
        var focused = false
        compose.runOnUiThread { focused = view.accessibilityNodeProvider.createAccessibilityNodeInfo(id)?.isAccessibilityFocused == true }
        return focused
    }

    @Test
    fun theMenuIsModalForTalkBackFromItsFirstItemAndHandsTheFocusBackToTheCard() {
        show()
        val view = talkBack()
        longPress("Birthday gift")
        // Nothing under the menu is there for TalkBack: the list, its bar, the tabs.
        compose.onNodeWithTag(CARD_MENU).assertExists()
        inList("Birthday gift").assertDoesNotExist()
        inList("New trainers").assertDoesNotExist()
        compose.onNodeWithContentDescription("Friends").assertDoesNotExist()
        compose.onNodeWithTag(REFRESH_BUTTON).assertDoesNotExist()
        assertTrue(item(ParcelMenuItem.Open).hasTalkBackFocus(view))

        back()
        inList("Birthday gift").assertExists()
        compose.onNodeWithContentDescription("Friends").assertExists()
        assertTrue(compose.card("Birthday gift").hasTalkBackFocus(view))
    }

    @Test
    fun theDeleteQuestionAndAnAlertAreModalForTalkBackToo() {
        show()
        val view = talkBack()
        longPress("Birthday gift")
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(DELETE_POPOVER).assertExists()
        inList("Birthday gift").assertDoesNotExist()
        compose.onNodeWithContentDescription("Friends").assertDoesNotExist()
        assertTrue(compose.onNode(hasText("Delete this parcel permanently?", substring = true)).hasTalkBackFocus(view))
        back()
        assertTrue(compose.card("Birthday gift").hasTalkBackFocus(view))

        source.failure = IllegalStateException("No connection")
        longPress("Birthday gift")
        choose(ParcelMenuItem.Archive)
        compose.onNodeWithTag(ACTION_ERROR).assertExists()
        inList("Birthday gift").assertDoesNotExist()
        compose.onNodeWithContentDescription("Friends").assertDoesNotExist()
        assertTrue(compose.onNode(hasText("No connection", substring = true)).hasTalkBackFocus(view))
        back()
        inList("Birthday gift").assertExists()
        assertTrue(compose.card("Birthday gift").hasTalkBackFocus(view))
    }
}

/** A phone on its side, a tablet and a foldable's inner screen, as the menu finds room on them. */
private const val LANDSCAPE_PHONE = "w844dp-h390dp-land-xhdpi"
private const val TABLET = "w1032dp-h1376dp-xhdpi"
private const val FOLDABLE_INNER = "w673dp-h841dp-xxhdpi"
