package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_DONE
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_POPOVER
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_COLUMN
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.cardId
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The deliveries keeping their books as the window changes width (A-S96), resized within the test's own process: the
 * app's root is laid out as wide as the test sets, at the window's top and leading edge, as a window dragged narrower or
 * wider in split screen lays it out. A window with room for the list and a parcel side by side is narrowed to 640,
 * regular still but short of the 690 that side by side keeps to, so that it stacks, and widened back; a phone's window,
 * which never lays them out side by side, is narrowed by 40 within its own width. What follows the window rather than
 * the root stays the real window's: its bars and cutout, and the size the filters and the card menu read
 * (`LocalWindowInfo`), which a regular window still has at 640, as a real resize to 640 would leave it. Signed in as the
 * synthetic reader@example.com on fake deliveries ([ParcelWalk]) whose stores answer every read in memory: nothing goes
 * to the network. Each moment saved as arrangement/A-S96-<moment>.png in the test package's external files, under
 * Reduce Motion. The saved parcel opening again without animation once the parcels are back is left out.
 */
@RunWith(AndroidJUnit4::class)
class ArrangementDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val demo: List<Parcel> = DemoCards.parcels()

    /** The root's width while the test narrows it; null while it spans the window. */
    private var rootWidth by mutableStateOf<Dp?>(null)

    /**
     * A-S96: a window widened past the line lays the list and a parcel out side by side, its first card shown beside
     * the list by itself and marked selected; narrowed back, the parcel nobody chose closes and the list stands alone,
     * no page over it and no card selected. A phone's window, narrowed within its width, keeps the list alone.
     */
    @Test
    fun aParcelNobodyChoseShowsBesideTheListOnceWidenedAndClosesOnceNarrowed() {
        val (width, _) = window()
        if (!turns()) {
            show(demo)
            assertTrue(walk.stacked())
            val narrowed = (width - PHONE_NARROWING).dp
            resize(narrowed)
            assertTrue("Stacked at $narrowed", walk.stacked())
            assertAlone(narrowed)
            walk.shot("A-S96-phone-narrowed-alone", alsoDark = false)
            return
        }
        show(demo, NARROW)
        assertTrue("Stacked at $NARROW", walk.stacked())
        assertAlone(NARROW)
        walk.shot("A-S96-stacked-alone", alsoDark = false)

        resize(null)
        compose.waitUntil(LONG_MILLIS) { !walk.stacked() && walk.count(PARCEL_PAGE) == 1 }
        walk.settle()
        val first = walk.onUi { walk.deliveries.rows.first() }
        assertTrue("Shown by itself", walk.onUi { walk.deliveries.navigator.navigation.shownAutomatically })
        assertBeside(demo.single { it.id == first })
        walk.shot("A-S96-widened-first-card", alsoDark = false)

        resize(NARROW)
        compose.waitUntil(LONG_MILLIS) { walk.stacked() && walk.shown() == null }
        walk.settle()
        assertAlone(NARROW)
        walk.shot("A-S96-narrowed-alone", alsoDark = false)
    }

    /**
     * A-S96: a parcel opened over the stacked list stays open as the window widens, beside the list and its card
     * selected, and narrowed again it is still pushed over the list, its page across the root. A phone's window,
     * narrowed within its width, keeps the page pushed where it was.
     */
    @Test
    fun aParcelOpenedOverTheListStaysOpenBesideItAndOverItAgain() {
        val (width, _) = window()
        val sideBySide = turns()
        show(demo, if (sideBySide) NARROW else null)
        val first = walk.onUi { walk.deliveries.rows.first() }
        val parcel = listOf("Birthday gift", "New trainers").map { DemoCards.parcel(it) }.first { it.id != first }
        walk.open(parcel)
        if (!sideBySide) {
            assertPushed(parcel, width.dp)
            val narrowed = (width - PHONE_NARROWING).dp
            resize(narrowed)
            assertTrue("Stacked at $narrowed", walk.stacked())
            assertPushed(parcel, narrowed)
            walk.shot("A-S96-phone-narrowed-pushed", alsoDark = false)
            return
        }
        assertPushed(parcel, NARROW)
        walk.shot("A-S96-pushed", alsoDark = false)

        resize(null)
        compose.waitUntil(LONG_MILLIS) { !walk.stacked() && walk.count(PARCEL_PAGE) == 1 }
        walk.settle()
        assertFalse("Chosen, not shown by itself", walk.onUi { walk.deliveries.navigator.navigation.shownAutomatically })
        assertBeside(parcel)
        walk.shot("A-S96-widened-chosen", alsoDark = false)

        resize(NARROW)
        compose.waitUntil(LONG_MILLIS) { walk.stacked() }
        walk.settle()
        assertPushed(parcel, NARROW)
        walk.shot("A-S96-narrowed-pushed", alsoDark = false)
    }

    /**
     * A-S96: the card at the list's top as the window turns, one further down than the first, is the card at its top
     * once the list is laid out in its new width, side by side to stacked and back. A phone's window, narrowed within its
     * width and widened back, keeps the same card at the top.
     */
    @Test
    fun theCardAtTheListsTopStaysThereAsTheWindowTurns() {
        val (width, _) = window()
        val sideBySide = turns()
        show((1..40).map { syntheticLamp(it) })
        assertEquals(!sideBySide, walk.stacked())
        val rows = walk.onUi { walk.deliveries.rows }
        scrollToTop(rows[TOP_INDEX])
        val top = walk.onUi { walk.deliveries.topCard() }
        assertNotNull("A card at the list's top", top)
        assertNotEquals("A card further down than the first", rows.first(), top)
        walk.shot(if (sideBySide) "A-S96-scrolled-side-by-side" else "A-S96-phone-scrolled", alsoDark = false)

        resize(if (sideBySide) NARROW else (width - PHONE_NARROWING).dp)
        compose.waitUntil(LONG_MILLIS) { walk.stacked() && walk.onUi { walk.deliveries.navigator.keptCard } == null }
        walk.settle()
        assertEquals("At the list's top once narrowed", top, walk.onUi { walk.deliveries.topCard() })
        walk.shot(if (sideBySide) "A-S96-scrolled-narrowed" else "A-S96-phone-scrolled-narrowed", alsoDark = false)

        resize(null)
        compose.waitUntil(LONG_MILLIS) { walk.stacked() != sideBySide && walk.onUi { walk.deliveries.navigator.keptCard } == null }
        walk.settle()
        assertEquals("At the list's top once widened again", top, walk.onUi { walk.deliveries.topCard() })
    }

    /**
     * A-S96: sheets take the window's arrangement only while none is open. The filters opened side by side, a popover
     * off their button, stay a popover as the window narrows until they close; opened again they are the stacked
     * list's sheet, which stays a sheet as the window widens until it closes, and the filters opened after are a popover
     * again. A phone's window, narrowed within its width, keeps the filters a sheet, open and opened again.
     */
    @Test
    fun aSheetKeepsItsShapeUntilItClosesAndTheNextTakesTheNewArrangement() {
        val (width, _) = window()
        val sideBySide = turns()
        show(demo)
        if (!sideBySide) {
            openFilters(FILTERS_SHEET)
            assertFalse(sheetsAsForms())
            val narrowed = (width - PHONE_NARROWING).dp
            resize(narrowed)
            assertTrue("Stacked at $narrowed", walk.stacked())
            assertTrue(walk.exists(FILTERS_SHEET))
            assertFalse(walk.exists(FILTERS_POPOVER))
            walk.shot("A-S96-phone-narrowed-filters", alsoDark = false)
            closeFilters()
            assertFalse(sheetsAsForms())
            openFilters(FILTERS_SHEET)
            closeFilters()
            return
        }
        assertFalse(walk.stacked())
        openFilters(FILTERS_POPOVER)
        assertTrue(sheetsAsForms())

        resize(NARROW)
        compose.waitUntil(LONG_MILLIS) { walk.stacked() }
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FILTERS_POPOVER) }
        assertFalse("No sheet in the popover's place", walk.exists(FILTERS_SHEET))
        assertTrue("Sheets as forms while the filters show", sheetsAsForms())
        walk.shot("A-S96-narrowed-filters-popover", alsoDark = false)
        closeFilters()
        assertFalse("Settled stacked once they closed", sheetsAsForms())
        openFilters(FILTERS_SHEET)
        assertFalse(walk.exists(FILTERS_POPOVER))
        walk.shot("A-S96-narrowed-filters-sheet", alsoDark = false)

        resize(null)
        compose.waitUntil(LONG_MILLIS) { !walk.stacked() }
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FILTERS_SHEET) }
        assertFalse("No popover in the sheet's place", walk.exists(FILTERS_POPOVER))
        assertFalse("Sheets as sheets while the filters show", sheetsAsForms())
        closeFilters()
        assertTrue("Settled side by side once they closed", sheetsAsForms())
        openFilters(FILTERS_POPOVER)
        closeFilters()
    }

    /** `parcels` in the deliveries, the root `width` wide, or across the window where null. */
    private fun show(parcels: List<Parcel>, width: Dp? = null) {
        rootWidth = width
        walk.show(parcels, around = { content -> ResizedRoot(rootWidth, content) })
    }

    /** The root `width` wide, or across the window where null, and the UI settled. */
    private fun resize(width: Dp?) {
        walk.onUi { rootWidth = width }
        walk.settle()
    }

    /** The window's width and height, in dp. */
    private fun window(): Pair<Float, Float> = walk.onUi {
        val view = compose.activity.window.decorView
        val scale = compose.activity.resources.displayMetrics.density
        view.width / scale to view.height / scale
    }

    /** Whether this window has room to lay the list and a parcel out side by side: regular, and wide enough for both. */
    private fun turns(): Boolean = window().let { (width, height) -> width >= SIDE_BY_SIDE && height >= REGULAR_HEIGHT }

    private fun sheetsAsForms(): Boolean = walk.onUi { walk.deliveries.sheetsAsForms }

    /** The cards in the list marked selected, each as TalkBack reads it. */
    private fun selectedCards(): List<String> = compose.onAllNodes(
        hasAnyAncestor(hasTestTag(PARCEL_LIST)) and hasClickAction() and SemanticsMatcher.expectValue(SemanticsProperties.Selected, true),
    ).fetchSemanticsNodes().map { it.config.getOrNull(SemanticsProperties.ContentDescription).orEmpty().joinToString() }

    /** The list alone in a root `width` wide: no parcel shown, no page over it, and no card selected. */
    private fun assertAlone(width: Dp) {
        assertNull(walk.shown())
        assertEquals(0, walk.count(PARCEL_PAGE))
        assertTrue(walk.onUi { walk.deliveries.listShown })
        assertEquals("The list across the root", width.value, walk.node(PARCEL_LIST).getBoundsInRoot().right.value, 1f)
        assertEquals(emptyList<String>(), selectedCards())
    }

    /** `parcel` shown beside the list, in the page's column, and its card the one marked selected. */
    private fun assertBeside(parcel: Parcel) {
        assertEquals(parcel.id, walk.shown())
        assertEquals(parcel.id, walk.onUi { walk.deliveries.selectedCard })
        val column = compose.onAllNodes(hasTestTag(PARCEL_PAGE) and hasAnyAncestor(hasTestTag(PAGE_COLUMN)), useUnmergedTree = true)
        assertTrue("The page in its column", column.fetchSemanticsNodes().isNotEmpty())
        val page = walk.node(PARCEL_PAGE).getBoundsInRoot()
        val list = walk.node(PARCEL_LIST).getBoundsInRoot()
        assertTrue("Beside the list: $page, $list", page.left >= list.right - 1.dp)
        val selected = selectedCards()
        assertEquals(selected.toString(), 1, selected.size)
        assertTrue(selected.single(), parcel.label in selected.single())
    }

    /** `parcel` chosen and pushed over the list, its page across the whole root, `width` wide. */
    private fun assertPushed(parcel: Parcel, width: Dp) {
        compose.waitUntil(LONG_MILLIS) { walk.shown() == parcel.id && walk.count(PARCEL_PAGE) == 1 }
        assertEquals(parcel.id, walk.onUi { walk.deliveries.navigator.navigation.chosen })
        assertFalse("The list under the page", walk.onUi { walk.deliveries.listShown })
        val page = walk.node(PARCEL_PAGE).getBoundsInRoot()
        assertEquals("From the root's leading edge: $page", 0f, page.left.value, 1f)
        assertEquals("Across the root, $width wide: $page", width.value, page.right.value, 1f)
    }

    /** The list scrolled until the card of `parcelId` stands at its top. */
    private fun scrollToTop(parcelId: UUID) {
        val key = walk.onUi { walk.deliveries.itemKeys.first { cardId(it) == parcelId } }
        walk.node(PARCEL_LIST).performScrollToKey(key)
        walk.settle()
    }

    /** The filters opened from their button, shown as `expected` says. */
    private fun openFilters(expected: String) {
        walk.tap(FILTERS_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(expected) }
        walk.settle()
        assertTrue(walk.onUi { walk.deliveries.showingFilters })
    }

    /** The filters closed with Done, and gone. */
    private fun closeFilters() {
        walk.tap(FILTERS_DONE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FILTERS_SHEET) && !walk.exists(FILTERS_POPOVER) }
        walk.settle()
        assertFalse(walk.onUi { walk.deliveries.showingFilters })
    }

    private companion object {
        const val FOLDER = "arrangement"

        /** Room for the list and a parcel side by side, and the slack past the line a window turning needs. */
        val SIDE_BY_SIDE =
            (DeliveriesArrangement.listWidth(false) + DeliveriesArrangement.parcelWidth(false) + DeliveriesArrangement.slack).toFloat()

        /** The least height of a regular window, which side by side needs. */
        const val REGULAR_HEIGHT = 480f

        /** Regular still, 600 at least, but short of the 690 that side by side keeps to: the list stacks. */
        val NARROW = 640.dp

        /** How far a phone's window is narrowed, within its own width. */
        const val PHONE_NARROWING = 40f

        /** The card the long list is scrolled to, well down from its first. */
        const val TOP_INDEX = 15
    }
}

/**
 * The app's root `width` wide at the window's top and leading edge, as tall as the window, or across the whole window
 * where null: the window resized, as far as the root's own layout can tell.
 */
@Composable
private fun ResizedRoot(width: Dp?, content: @Composable () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        Box(if (width == null) Modifier.fillMaxSize() else Modifier.fillMaxHeight().width(width), propagateMinConstraints = true) {
            content()
        }
    }
}
