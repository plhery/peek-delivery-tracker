package com.plhery.peekdeliverytracker.ui

import android.content.ClipboardManager
import android.view.ActionMode
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsConfiguration
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.click
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.text.TextLayoutResult
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.FirstOpenJourney
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_CANCEL
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MARKS
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_NUMBER
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.RENAME_ALERT
import com.plhery.peekdeliverytracker.ui.deliveries.RENAME_CANCEL
import com.plhery.peekdeliverytracker.ui.deliveries.RENAME_FIELD
import com.plhery.peekdeliverytracker.ui.deliveries.RenameRequest
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.pinnedIdentities
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import java.util.UUID
import java.util.concurrent.CopyOnWriteArrayList
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What a finger does on a parcel's pass and on its tracking number (B-G01, B-G03, B-G04), on this device's own window,
 * as ParcelDetailView's do. The name opens the rename alert, and TalkBack reads it as a button whose double tap is
 * "detail.editTitle" (`.accessibilityHint`), everywhere but on the sample (`if sample == nil`), a parcel followed
 * through someone's link included. The carrier's mark opens Change carrier where the carrier is the reader's to change
 * (`if editable`, `sample == nil && readerLink == nil`); elsewhere it takes the tap and opens nothing, nor the map
 * behind it. A long press on the tracking number, on the page and in a link's sheet, selects it and brings up the
 * system's floating toolbar with Copy (`.textSelection(.enabled)`). The toolbar is seen as the platform starts it: the
 * activity's window callback, wrapped for the test and put back after, hears each action mode the window starts. Copy
 * is pressed through the toolbar's menu, as its button does, and what it copied read from the clipboard, which is put
 * back after. Signed in as the synthetic reader@example.com on fake deliveries and links whose stores answer every
 * read in memory ([ParcelWalk], [FakeParcelLinks]), the page beside the list or over it as the window lays them out.
 * Shots in pagetaps/<row>-<moment>.png.
 */
@RunWith(AndroidJUnit4::class)
class ParcelPageTapsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /** A parcel its carrier answers for, with one number. */
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    /** A parcel with places on its map, which the pass draws behind its carrier's mark. */
    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")

    private fun inPage(tag: String): SemanticsMatcher = hasTestTag(tag) and hasAnyAncestor(hasTestTag(PARCEL_PAGE))

    /** What the part `matcher` finds says and does, as it stands in the tree. */
    private fun config(matcher: SemanticsMatcher): SemanticsConfiguration =
        compose.onNode(matcher, useUnmergedTree = true).fetchSemanticsNode().config

    /** A finger's tap on the page's part tagged `tag`, where it is drawn, whether it takes taps or not. */
    private fun tap(tag: String) {
        compose.onNode(inPage(tag), useUnmergedTree = true).performTouchInput { click() }
        walk.settle()
    }

    private fun editor(): Pair<UUID?, CarrierId?>? = walk.onUi { walk.deliveries.carrierEditor?.let { it.pageId to it.initialCarrier } }

    /** Nothing in the page's part tagged `tag` is a button: no tap, no role. */
    private fun assertNoButton(what: String, tag: String) {
        val buttons = compose.onAllNodes(
            (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag))) and hasAnyAncestor(hasTestTag(PARCEL_PAGE)) and hasClickAction(),
            useUnmergedTree = true,
        ).fetchSemanticsNodes()
        assertTrue("$what takes no tap: ${buttons.map { it.config }}", buttons.isEmpty())
        assertNull("$what is no button", config(inPage(tag)).getOrNull(SemanticsProperties.Role))
    }

    /** Nothing opened over the page: no rename alert, no Change carrier, no map. */
    private fun assertNothingOpened(what: String) {
        assertFalse("$what opened the rename alert", walk.exists(RENAME_ALERT))
        assertNull("$what asked for a rename", walk.onUi { walk.deliveries.renaming })
        assertFalse("$what opened Change carrier", walk.exists(CHANGE_CARRIER_SHEET))
        assertNull("$what asked for the carrier editor", editor())
        assertNull("$what opened the map", walk.onUi { walk.deliveries.mapCover })
    }

    /** The name, a button whose double tap TalkBack says as "detail.editTitle". */
    private fun assertNameIsRenamed() {
        val name = config(inPage(PAGE_NAME))
        assertEquals(Role.Button, name.getOrNull(SemanticsProperties.Role))
        assertEquals(walk.text("detail.editTitle"), name.getOrNull(SemanticsActions.OnClick)?.label)
    }

    /** The rename alert, opened from the name of `parcel`'s page and holding its name, then cancelled. */
    private fun renameFromTheName(parcel: Parcel, moment: String?) {
        tap(PAGE_NAME)
        compose.waitUntil(LONG_MILLIS) { walk.exists(RENAME_ALERT) }
        walk.awaitKeyboard(required = false)
        assertEquals(RenameRequest(parcel.id, parcel.id), walk.onUi { walk.deliveries.renaming })
        assertTrue("The alert's title", walk.shows(RENAME_ALERT, walk.text("detail.editTitle")))
        assertEquals(parcel.label, walk.editable(RENAME_FIELD))
        if (moment != null) walk.shot(moment)
        walk.tap(RENAME_CANCEL)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(RENAME_ALERT) }
        walk.awaitStill()
        assertTrue("Nothing renamed", walk.onUi { walk.source.renames.isEmpty() })
    }

    // B-G01: the name.

    /**
     * B-G01: a tap on the parcel's name opens the rename alert on its page's parcel, its field holding the name, as
     * the ••• menu's Rename does; TalkBack reads the name as a button whose double tap is "detail.editTitle". Cancel
     * leaves the name as it was.
     */
    @Test
    fun tappingTheNameOpensTheRenameAlertAndTalkBackHearsWhatADoubleTapDoes() {
        walk.show(DemoCards.parcels())
        val gift = gift
        walk.open(gift)
        assertNameIsRenamed()
        renameFromTheName(gift, "B-G01-rename")
    }

    /**
     * B-G01, B-G03: on the sample, neither the name nor the carrier's mark is a button, and a tap on either opens
     * nothing: no rename, no Change carrier, no map.
     */
    @Test
    fun onTheSampleNeitherTheNameNorTheCarriersMarkOpensAnything() {
        walk.show(DemoCards.parcels())
        walk.onUi { walk.deliveries.openSample() }
        compose.waitUntil(LONG_MILLIS) {
            walk.shown() == FirstOpenJourney.parcelId && walk.count(PARCEL_PAGE) == 1 &&
                compose.onAllNodes(inPage(PAGE_NAME), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        }
        walk.settle()
        assertNoButton("The sample's name", PAGE_NAME)
        assertNoButton("The sample's mark", PAGE_MARKS)
        tap(PAGE_NAME)
        assertNothingOpened("A tap on the sample's name")
        tap(PAGE_MARKS)
        assertNothingOpened("A tap on the sample's mark")
        walk.shot("B-G01-sample", alsoDark = false)
    }

    // B-G03: the carrier's mark.

    /**
     * B-G03: a tap on the carrier's mark opens Change carrier on its page's parcel, on no carrier picked in advance,
     * rather than the map behind the mark; the mark is a button read with "detail.changeCarrierFrom". Cancel changes
     * nothing.
     */
    @Test
    fun tappingTheCarriersMarkOpensChangeCarrier() {
        walk.show(DemoCards.parcels())
        val lamp = lamp
        walk.open(lamp)
        val marks = config(inPage(PAGE_MARKS))
        assertEquals(Role.Button, marks.getOrNull(SemanticsProperties.Role))
        assertTrue("The mark takes a tap", SemanticsActions.OnClick in marks)
        val from = walk.text("detail.changeCarrierFrom", "carrier" to walk.identities.shown(lamp, walk.localizer).fullName)
        val label = marks.getOrNull(SemanticsProperties.ContentDescription).orEmpty().joinToString()
        assertTrue(label, from in label)
        tap(PAGE_MARKS)
        compose.waitUntil(LONG_MILLIS) { walk.exists(CHANGE_CARRIER_SHEET) }
        walk.awaitStill()
        val asked = editor()
        assertEquals(lamp.id, asked?.first)
        assertNull("No carrier picked in advance", asked?.second)
        assertNull("The map stays shut", walk.onUi { walk.deliveries.mapCover })
        walk.shot("B-G03-change-carrier", alsoDark = false)
        walk.tap(CHANGE_CARRIER_CANCEL)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(CHANGE_CARRIER_SHEET) }
        assertTrue("No carrier changed", walk.onUi { walk.source.carrierChanges.isEmpty() })
    }

    /**
     * B-G03, B-G01: on a parcel followed through someone's link, the carrier is theirs: the mark is no button, and a
     * tap on it opens nothing, nor the map behind it. The name is still the reader's to change, from a tap on it.
     */
    @Test
    fun aParcelFollowedThroughSomeonesLinkOpensNothingFromItsMarkButIsRenamedFromItsName() {
        val lamp = lamp
        walk.show(DemoCards.parcels()) { viewerLinks = mapOf(lamp.id to SYNTHETIC_LINK) }
        walk.open(lamp)
        assertNoButton("A followed parcel's mark", PAGE_MARKS)
        tap(PAGE_MARKS)
        assertNothingOpened("A tap on a followed parcel's mark")
        walk.shot("B-G03-followed", alsoDark = false)
        assertNameIsRenamed()
        renameFromTheName(lamp, moment = null)
    }

    // B-G04: the number.

    /**
     * B-G04: a long press on the page's tracking number selects it and brings up the system's floating toolbar, which
     * offers Copy; Copy puts what was selected, part of the number as printed holding the word under the finger, on
     * the clipboard.
     */
    @Test
    fun aLongPressOnThePagesNumberSelectsItAndTheSystemsToolbarOffersCopy() {
        walk.show(DemoCards.parcels())
        walk.open(gift)
        assertLongPressSelectsAndCopies(inPage(PAGE_NUMBER), "B-G04-page-selected")
    }

    /**
     * B-G04: in a link's sheet, a parcel shared with its number shown (a synthetic one), a long press on the number
     * selects it and brings up the system's floating toolbar with Copy, as on the page.
     */
    @Test
    fun aLongPressOnTheLinkSheetsNumberSelectsItAndTheSystemsToolbarOffersCopy() {
        val links = FakeParcelLinks(answer = LinkFixtures.keepable(), session = FakeParcelLinks.READER)
        compose.waitsForMaps()
        compose.runOnUiThread { compose.activity.edgeToEdge(dark = false) }
        compose.setContent { RootHost(state = FakeParcelLinks.READER, identities = pinnedIdentities, links = links) }
        compose.waitForIdle()
        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = LinkFixtures.viewer().link.id)) }
        val number = hasTestTag(PAGE_NUMBER) and hasAnyAncestor(hasTestTag(LINK_SHEET))
        compose.waitUntil(LONG_MILLIS) { compose.onAllNodes(number, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() }
        walk.settle()
        assertLongPressSelectsAndCopies(number, "B-G04-link-selected")
    }

    /**
     * A long press on the middle of the longest run of letters and digits of the number `number` finds, scrolled into
     * view first: the window starts a floating action mode whose menu holds Copy (`android.R.id.copy`), shot once up as
     * `moment`; Copy, pressed, puts on the clipboard part of the number as printed, holding the word under the finger.
     */
    private fun assertLongPressSelectsAndCopies(number: SemanticsMatcher, moment: String) {
        reveal(number)
        val node = compose.onNode(number, useUnmergedTree = true)
        val layout = layoutOf(number)
        val printed = layout.layoutInput.text.text
        val word = Regex("[\\p{L}\\p{N}]+").findAll(printed).maxByOrNull { it.value.length } ?: throw AssertionError("No number in \"$printed\"")
        val under = layout.getBoundingBox((word.range.first + word.range.last) / 2).center
        keepingTheClipboard { clipboard ->
            watchingToolbars { toolbars ->
                walk.onUi { clipboard.clearPrimaryClip() }
                node.performTouchInput { longClick(under) }
                compose.waitUntil(LONG_MILLIS) { toolbars.started.isNotEmpty() }
                compose.waitForIdle()
                val mode = toolbars.started.last()
                assertEquals("A floating toolbar", ActionMode.TYPE_FLOATING, walk.onUi { mode.type })
                val items = walk.onUi { (0 until mode.menu.size()).map { mode.menu.getItem(it) } }
                val copy = items.firstOrNull { it.itemId == android.R.id.copy } ?: throw AssertionError("No Copy among ${items.map { it.title }}")
                assertFalse("Copy says what it does", copy.title.isNullOrBlank())
                walk.shot(moment, alsoDark = false)
                assertTrue("Copy pressed", walk.onUi { mode.menu.performIdentifierAction(android.R.id.copy, 0) })
                compose.waitUntil(LONG_MILLIS) { clipText(clipboard).isNotEmpty() }
                val copied = clipText(clipboard)
                assertTrue("\"$copied\" copied from the number \"$printed\"", copied in printed)
                assertTrue("\"$copied\" holds the word under the finger, \"${word.value}\"", word.value in copied)
            }
        }
    }

    /** What `number` finds, scrolled into view clear of the tab bar where it scrolls, and still. */
    private fun reveal(number: SemanticsMatcher) {
        val scrolled = compose.onAllNodes(number and hasAnyAncestor(hasScrollAction()), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        if (scrolled) compose.reveal(compose.onNode(number, useUnmergedTree = true), hasScrollAction() and hasAnyDescendant(number))
        walk.settle()
    }

    /** The layout of the text `matcher` finds, in its own pixels, as the finger's position is given. */
    private fun layoutOf(matcher: SemanticsMatcher): TextLayoutResult {
        val node = compose.onNode(matcher, useUnmergedTree = true).fetchSemanticsNode()
        return walk.onUi {
            val laid = mutableListOf<TextLayoutResult>()
            node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(laid)
            laid.single()
        }
    }

    /** The action modes the window starts and finishes, heard through its callback, every other call passed on. */
    private class Toolbars(private val original: Window.Callback) : Window.Callback by original {
        val started = CopyOnWriteArrayList<ActionMode>()
        val finished = CopyOnWriteArrayList<ActionMode>()

        override fun onActionModeStarted(mode: ActionMode?) {
            if (mode != null) started += mode
            original.onActionModeStarted(mode)
        }

        override fun onActionModeFinished(mode: ActionMode?) {
            if (mode != null) finished += mode
            original.onActionModeFinished(mode)
        }
    }

    /** `body` with the window's action modes heard; any still up finished after it, and the callback put back. */
    private fun <T> watchingToolbars(body: (Toolbars) -> T): T {
        val window = compose.activity.window
        val original = walk.onUi { window.callback }
        val toolbars = Toolbars(original)
        walk.onUi { window.callback = toolbars }
        try {
            return body(toolbars)
        } finally {
            walk.onUi {
                toolbars.started.filter { it !in toolbars.finished }.forEach { it.finish() }
                window.callback = original
            }
        }
    }

    /** `body` with whatever the clipboard held put back after it, as the copies it makes leave it. */
    private fun <T> keepingTheClipboard(body: (ClipboardManager) -> T): T {
        val clipboard = compose.activity.getSystemService(ClipboardManager::class.java)
        val before = walk.onUi { clipboard.primaryClip }
        try {
            return body(clipboard)
        } finally {
            walk.onUi { if (before != null) clipboard.setPrimaryClip(before) else clipboard.clearPrimaryClip() }
        }
    }

    private fun clipText(clipboard: ClipboardManager): String = walk.onUi {
        clipboard.primaryClip?.takeIf { it.itemCount > 0 }?.getItemAt(0)?.coerceToText(compose.activity)?.toString().orEmpty()
    }

    private companion object {
        const val FOLDER = "pagetaps"

        /** The link a followed parcel was found through: synthetic, read by nothing but the fake deliveries. */
        const val SYNTHETIC_LINK = "link-0001"
    }
}
