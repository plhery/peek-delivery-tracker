package com.plhery.peekdeliverytracker.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.os.SystemClock
import android.view.InputDevice
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.unit.dp
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
import kotlin.math.roundToInt

/**
 * A mouse on the deliveries, sent through the system's input into this device's window as a mouse's comes
 * ([ScreenTouches.rightClick]). Its right button (A-G04): a card's menu opens at once at the pointer, as `.contextMenu`
 * opens the iPad's compact menu under a pointer, with no card lifted over a dimmed screen, and the parcel neither opens
 * nor changes; an archived row low in the window opens its own, with Restore, inside the window and still from the
 * pointer, or across it where the window is too narrow for the menu on either side. Its pointer (A-G05): over a card,
 * it lights the card up in its shape, as `.hoverEffect(.highlight)` does on the iPad, and off it, the card is as it
 * was. Signed in as the synthetic reader@example.com on fake deliveries ([ParcelWalk]), which reach no server, under
 * Reduce Motion; each test keeps Zurich's time and the captures' moment, and puts the device back in touch mode after
 * it, which a mouse takes it out of.
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
     * (its left or right edge at it, or, in a window too narrow for it on either side, as a phone's is, across the
     * pointer as far as the window lets it go) and over the pointer down the window (from it, to it or, with no room
     * either way, as far as the window lets it go), inside the window; returned with the point in the window.
     */
    private fun rightClick(matcher: SemanticsMatcher, x: Float, y: Float): Pair<Rect, Offset> {
        val at = touches.point(matcher, x, y)
        touches.rightClick(at)
        compose.waitUntil(LONG_MILLIS) { walk.exists(CARD_MENU) }
        walk.settle()
        val pointer = at - touches.origin()
        val glass = glass()
        val edge = abs(glass.left - pointer.x) < 1.5f || abs(glass.right - pointer.x) < 1.5f
        val slack = with(compose.density) { SIDE_SLACK.toPx() }
        val room = pointer.x + glass.width <= window().right - slack || pointer.x - glass.width >= slack
        if (room) {
            assertTrue("From the pointer across: $glass, $pointer", edge)
        } else {
            assertTrue("Across the pointer, no room either side: $glass, $pointer", edge || pointer.x in glass.left..glass.right)
        }
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

    /**
     * A-G05: a mouse's pointer moved onto a card lights it up in its shape, as `.hoverEffect(.highlight)` lights a card
     * up on the iPad: the system's quaternary fill over all of the card, to just inside each rounded corner, and nothing
     * just outside them; moved off onto the list's bare ground beside it, the card is drawn as it was. The pointer
     * opens nothing, no menu and no parcel, and the card neither lifts nor moves.
     */
    @Test
    fun aPointerOverACardLightsItUpInItsShapeAndOffItPutsItBack() {
        walk.show(demo)
        val gift = DemoCards.parcel("Birthday gift")
        val key = walk.onUi { walk.deliveries.itemKeys.first { cardId(it) == gift.id } }
        walk.node(PARCEL_LIST).performScrollToKey(key)
        walk.settle()
        val shown = walk.shown()
        val matcher = card(gift.label)
        val bounds = touches.node(matcher).boundsInWindow
        val before = picture(matcher)
        val bare = beside(bounds)

        val pointer = Pointer(bare)
        try {
            pointer.enter()
            pointer.move(touches.point(matcher, x = 0.7f, y = 0.55f))
            walk.settle()
            val lit = picture(matcher)
            assertStill(bounds, matcher)
            assertEquals("The card's size: ${before.width}×${before.height}", before.width to before.height, lit.width to lit.height)
            inside(before).forEach { patch ->
                val shift = moved(before, lit, patch)
                assertTrue("Lit ${patch.where}: $shift", shift > LIT)
                assertTrue("Toward the quaternary fill ${patch.where}", towardTheFill(before, lit, patch))
            }
            outside(before).forEach { patch ->
                val shift = moved(before, lit, patch)
                assertTrue("Unlit outside the corner ${patch.where}: $shift", shift < STILL)
            }
            assertFalse(walk.exists(CARD_MENU))
            assertFalse(walk.exists(CARD_MENU_PREVIEW))
            assertEquals(shown, walk.shown())
            walk.shot("A-G05-hover", alsoDark = false)

            pointer.move(bare)
            walk.settle()
            val after = picture(matcher)
            assertStill(bounds, matcher)
            assertEquals("The card's size: ${before.width}×${before.height}", before.width to before.height, after.width to after.height)
            (inside(before) + outside(before)).forEach { patch ->
                val shift = moved(before, after, patch)
                assertTrue("As it was ${patch.where}: $shift", shift < STILL)
            }
            assertFalse(walk.exists(CARD_MENU))
            assertFalse(walk.exists(CARD_MENU_PREVIEW))
            assertEquals(shown, walk.shown())
            walk.shot("A-G05-hover-off", alsoDark = false)
        } finally {
            runCatching { pointer.exit() }
            compose.waitForIdle()
        }
    }

    /** What the window draws in the bounds of what `matcher` finds first, unmerged, as [ScreenTouches] finds it. */
    private fun picture(matcher: SemanticsMatcher): Bitmap =
        compose.onAllNodes(matcher, useUnmergedTree = true).onFirst().captureToImage().asAndroidBitmap()

    /** What `matcher` finds still where it stood at `bounds`, neither lifted nor moved. */
    private fun assertStill(bounds: Rect, matcher: SemanticsMatcher) {
        val now = touches.node(matcher).boundsInWindow
        val still = abs(now.left - bounds.left) < 0.5f && abs(now.top - bounds.top) < 0.5f &&
            abs(now.right - bounds.right) < 0.5f && abs(now.bottom - bounds.bottom) < 0.5f
        assertTrue("Neither lifted nor moved: $bounds, $now", still)
    }

    /**
     * The point on the screen on the list's bare ground beside the card at `bounds` (in the window), level with its
     * middle: 8 before its leading edge, or halfway there where the card stands closer to the window's edge, on no card.
     */
    private fun beside(bounds: Rect): Offset {
        val dp = compose.density.density
        assertTrue("Room beside the card: $bounds", bounds.left >= 4 * dp)
        val point = Offset(bounds.left - minOf(8 * dp, bounds.left / 2), bounds.center.y)
        val cards = compose.onAllNodes(hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST)), useUnmergedTree = true)
            .fetchSemanticsNodes().map { it.boundsInWindow }
        assertTrue("$point on no card: $cards", cards.none { it.contains(point) })
        return touches.origin() + point
    }

    /** A square of a card's picture, `size` pixels a side from `x`, `y`, and where on the card it stands. */
    private class Patch(val where: String, val x: Int, val y: Int, val size: Int)

    /**
     * Squares of `picture` inside the card's shape: just inside each corner, 12 in from both its edges, past the curve
     * of a card's corner (16 points, 24 on Next up), and one in its leading half, clear of where the pointer rests.
     */
    private fun inside(picture: Bitmap): List<Patch> {
        val dp = compose.density.density
        val inset = (12 * dp).roundToInt()
        val size = (6 * dp).roundToInt().coerceAtLeast(3)
        val right = picture.width - inset - size
        val bottom = picture.height - inset - size
        return listOf(
            Patch("at the top left", inset, inset, size),
            Patch("at the top right", right, inset, size),
            Patch("at the bottom left", inset, bottom, size),
            Patch("at the bottom right", right, bottom, size),
            Patch("in its leading half", (picture.width * 0.3f).toInt(), picture.height / 2 - size / 2, size),
        )
    }

    /**
     * Squares of `picture` in its bounds but outside the card's shape: in each corner, from half a point to two points in
     * from both edges, which the curve of a corner of 16 points or more, nearly 5 in from both edges at its middle,
     * leaves bare.
     */
    private fun outside(picture: Bitmap): List<Patch> {
        val dp = compose.density.density
        val inset = (0.5f * dp).roundToInt().coerceAtLeast(1)
        val size = (1.5f * dp).roundToInt().coerceAtLeast(2)
        val right = picture.width - inset - size
        val bottom = picture.height - inset - size
        return listOf(
            Patch("at the top left", inset, inset, size),
            Patch("at the top right", right, inset, size),
            Patch("at the bottom left", inset, bottom, size),
            Patch("at the bottom right", right, bottom, size),
        )
    }

    /** How far `after` stands from `before` over `patch`: the mean of each pixel's red, green and blue moved, out of 255. */
    private fun moved(before: Bitmap, after: Bitmap, patch: Patch): Float {
        var sum = 0L
        for (y in patch.y until patch.y + patch.size) {
            for (x in patch.x until patch.x + patch.size) {
                val was = before.getPixel(x, y)
                val now = after.getPixel(x, y)
                sum += abs(Color.red(now) - Color.red(was)) + abs(Color.green(now) - Color.green(was)) + abs(Color.blue(now) - Color.blue(was))
            }
        }
        return sum / (3f * patch.size * patch.size)
    }

    /**
     * Whether `after` differs from `before` over `patch` as the system's quaternary fill laid over it makes it: nine in
     * ten of the pixels that moved, at least, moved toward the fill's grey, whatever they showed.
     */
    private fun towardTheFill(before: Bitmap, after: Bitmap, patch: Patch): Boolean {
        var toward = 0
        var away = 0
        for (y in patch.y until patch.y + patch.size) {
            for (x in patch.x until patch.x + patch.size) {
                val was = before.getPixel(x, y)
                val now = after.getPixel(x, y)
                val red = Color.red(now) - Color.red(was)
                val green = Color.green(now) - Color.green(was)
                val blue = Color.blue(now) - Color.blue(was)
                if (abs(red) + abs(green) + abs(blue) < 3) continue
                val toFill = red * (FILL_RED - Color.red(was)) + green * (FILL_GREEN - Color.green(was)) + blue * (FILL_BLUE - Color.blue(was))
                if (toFill > 0) toward++ else away++
            }
        }
        return toward > 0 && away * 9 <= toward
    }

    /**
     * A mouse's pointer on the screen at [at], no button held, its moves sent through UiAutomation as a mouse's are, as
     * [ScreenTouches.rightClick] sends its button: the mouse's source and its pointer's tool. Entering, it is sent again
     * a little later while the screen refuses it, as a finger put down is.
     */
    private inner class Pointer(private var at: Offset) {
        private val since = SystemClock.uptimeMillis()

        fun enter() = send(MotionEvent.ACTION_HOVER_ENTER)

        /** Moved to `to` a frame at a time over `steps` frames, and the window idle after. */
        fun move(to: Offset, steps: Int = MOVE_STEPS) {
            val from = at
            for (step in 1..steps) {
                SystemClock.sleep(ScreenTouches.FRAME_MILLIS)
                at = from + (to - from) * (step / steps.toFloat())
                send(MotionEvent.ACTION_HOVER_MOVE)
            }
            compose.waitForIdle()
        }

        fun exit() = send(MotionEvent.ACTION_HOVER_EXIT)

        private fun send(action: Int) {
            val until = SystemClock.uptimeMillis() + ScreenTouches.DOWN_AGAIN_MILLIS
            while (!inject(action)) {
                check(action == MotionEvent.ACTION_HOVER_ENTER && SystemClock.uptimeMillis() < until) {
                    "The screen refused the pointer's ${MotionEvent.actionToString(action)} at $at"
                }
                SystemClock.sleep(ScreenTouches.FRAME_MILLIS * 6)
            }
        }

        private fun inject(action: Int): Boolean {
            val properties = MotionEvent.PointerProperties().apply {
                id = 0
                toolType = MotionEvent.TOOL_TYPE_MOUSE
            }
            val where = MotionEvent.PointerCoords().apply {
                x = at.x
                y = at.y
            }
            val event = MotionEvent.obtain(
                since, SystemClock.uptimeMillis(), action, 1, arrayOf(properties), arrayOf(where), 0, 0, 1f, 1f, 0, 0,
                InputDevice.SOURCE_MOUSE, 0,
            )
            try {
                return instrumentation.uiAutomation.injectInputEvent(event, true)
            } finally {
                event.recycle()
            }
        }
    }

    private companion object {
        const val FOLDER = "pointerwalk"

        /** The least a lit card's square moves, out of 255: the quaternary fill moves a white card's by about 11. */
        const val LIT = 2f

        /** The most a square left as it was moves, out of 255. */
        const val STILL = 1f

        /** The system's quaternary fill in the light appearance, (116, 116, 128), which `.hoverEffect(.highlight)` lays on. */
        const val FILL_RED = 116
        const val FILL_GREEN = 116
        const val FILL_BLUE = 128

        const val MOVE_STEPS = 8

        /** More than the menu keeps from the window's sides, so that a menu with room on a side is told apart. */
        val SIDE_SLACK = 32.dp
    }
}
