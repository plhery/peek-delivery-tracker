package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_MENU
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_MENU_PREVIEW
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.cardId
import com.plhery.peekdeliverytracker.ui.deliveries.menuItemTag
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import kotlin.math.abs

/**
 * A mouse's right button on the deliveries (A-G04), pressed through the system's input into this device's window as a
 * mouse's comes ([ScreenTouches.rightClick]): a card's menu opens at once at the pointer, as `.contextMenu` opens the
 * iPad's compact menu under a pointer, with no card lifted over a dimmed screen, and the parcel neither opens nor
 * changes; an archived row low in the window opens its own, with Restore, inside the window and still from the
 * pointer. Signed in as the synthetic reader@example.com on fake deliveries ([ParcelWalk]), which reach no server; each
 * test keeps Zurich's time and the captures' moment, and puts the device back in touch mode after it, which a mouse
 * takes it out of.
 */
@RunWith(AndroidJUnit4::class)
class PointerDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val touches = ScreenTouches(compose)
    private val demo: List<Parcel> = DemoCards.parcels()

    @After
    fun backToTouch() {
        runCatching { instrumentation.setInTouchMode(true) }
    }

    /** The card of the parcel named `label` in the list: a button read with its name. */
    private fun card(label: String): SemanticsMatcher =
        hasContentDescription(label, substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST))

    /** The menu's glass, its items on it, where it is drawn in the window. */
    private fun glass(): Rect =
        compose.onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.PaneTitle) and hasAnyAncestor(hasTestTag(CARD_MENU)))
            .fetchSemanticsNode().boundsInWindow

    private fun shownItems(): List<ParcelMenuItem> = ParcelMenuItem.entries.filter {
        compose.onAllNodes(hasTestTag(menuItemTag(it))).fetchSemanticsNodes().isNotEmpty()
    }

    /** The window's size in its pixels. */
    private fun window(): Rect = walk.onUi {
        val decor = compose.activity.window.decorView
        Rect(0f, 0f, decor.width.toFloat(), decor.height.toFloat())
    }

    /**
     * The menu opened by a right click at `x` and `y` of what `matcher` finds, its glass from the pointer's side across
     * (its left or right edge at it) and over the pointer down the window (from it, to it or, with no room either way,
     * as far as the window lets it go), inside the window; returned with the point in the window.
     */
    private fun rightClick(matcher: SemanticsMatcher, x: Float, y: Float): Pair<Rect, Offset> {
        val at = touches.point(matcher, x, y)
        touches.rightClick(at)
        compose.waitUntil(LONG_MILLIS) { walk.exists(CARD_MENU) }
        walk.settle()
        val pointer = at - touches.origin()
        val glass = glass()
        assertTrue("From the pointer across: $glass, $pointer", abs(glass.left - pointer.x) < 1.5f || abs(glass.right - pointer.x) < 1.5f)
        assertTrue("Over the pointer down the window: $glass, $pointer", pointer.y in glass.top - 1.5f..glass.bottom + 1.5f)
        val window = window()
        val inside = glass.left >= 0f && glass.top >= 0f && glass.right <= window.right && glass.bottom <= window.bottom
        assertTrue("Inside the window $window: $glass", inside)
        return glass to pointer
    }

    /**
     * A-G04: a right click on a card opens its menu at once at the pointer, the glass from the point clicked, no card
     * lifted; the parcel shown beside the list, if any, stays it, and back closes the menu with nothing opened.
     */
    @Test
    fun aRightClickOnACardOpensItsMenuAtThePointerAndOpensNothing() {
        walk.show(demo)
        val gift = DemoCards.parcel("Birthday gift")
        val key = walk.onUi { walk.deliveries.itemKeys.first { cardId(it) == gift.id } }
        walk.node(PARCEL_LIST).performScrollToKey(key)
        walk.settle()
        val shown = walk.shown()

        val (glass, pointer) = rightClick(card(gift.label), x = 0.2f, y = 0.4f)
        assertEquals("Down from the pointer: $glass, $pointer", pointer.y, glass.top, 1.5f)
        assertFalse(walk.exists(CARD_MENU_PREVIEW))
        val items = shownItems()
        assertTrue("$items", ParcelMenuItem.Open in items && ParcelMenuItem.Archive in items)
        assertFalse("$items", ParcelMenuItem.Restore in items)
        assertEquals(shown, walk.shown())
        walk.shot("A-G04-right-click", alsoDark = false)

        walk.back()
        assertFalse(walk.exists(CARD_MENU))
        assertEquals(shown, walk.shown())
    }

    /**
     * A-G04: an archived row's menu opens the same way, with Restore, low in the window: inside it, still from the
     * pointer across and over it.
     */
    @Test
    fun aRightClickOnAnArchivedRowOpensItsMenuInsideTheWindow() {
        walk.show(demo)
        walk.openArchive()
        val bag = DemoCards.parcel("Linen weekend bag")
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(card(bag.label))
        walk.settle()
        val shown = walk.shown()

        rightClick(card(bag.label), x = 0.6f, y = 0.6f)
        assertFalse(walk.exists(CARD_MENU_PREVIEW))
        assertTrue(ParcelMenuItem.Restore in shownItems())
        assertEquals(shown, walk.shown())
        walk.shot("A-G04-right-click-archived", alsoDark = false)

        walk.back()
        assertFalse(walk.exists(CARD_MENU))
        assertEquals(shown, walk.shown())
    }

    private companion object {
        const val FOLDER = "pointerwalk"
    }
}
