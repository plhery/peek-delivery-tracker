package com.plhery.peekdeliverytracker.ui.zoom

import android.os.Looper
import androidx.activity.BackEventCompat
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performMouseInput
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.time.Duration
import kotlin.math.abs

/**
 * The zoom with a page whose content scrolls, over a root with a card and a button: on a 390 × 844 dp phone, two pixels
 * to the dp, the card 358 × 120 dp 120 dp down.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ZoomStackTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private var top: String? by mutableStateOf(null)
    private var sourceId by mutableStateOf(SOURCE)
    private var sourceTop: Dp by mutableStateOf(120.dp)
    private val list = LazyListState()
    private val rootFocus = FocusRequester()
    private var rootFocused = false
    private var pageFocused = false
    private var taps = 0
    private lateinit var transition: ZoomTransition

    /**
     * The root, with the page on top from the first frame (`initial`), or opened once the root is drawn (`open`); with
     * `bar`, the page's bar: a black square for its ‹ and one for its •••, on the window's top corners.
     */
    private fun show(reduceMotion: Boolean = false, initial: String? = null, open: Boolean = initial == null, bar: Boolean = false) {
        top = initial
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = reduceMotion, reduceTransparency = false, carrierIdentities = null) {
                val transition = rememberZoomTransition().also { this@ZoomStackTest.transition = it }
                val drawn = top ?: transition.covered
                ZoomStack(
                    transition = transition,
                    top = top,
                    onBack = { top = null },
                    root = {
                        Column(Modifier.fillMaxSize()) {
                            Box(
                                Modifier
                                    .focusRequester(rootFocus)
                                    .onFocusChanged { rootFocused = it.isFocused }
                                    .focusable()
                                    .size(40.dp),
                            )
                            Box(
                                Modifier
                                    .padding(start = 16.dp, top = sourceTop - 40.dp)
                                    .size(358.dp, 120.dp)
                                    .zoomSource(sourceId, transition, 16.dp)
                                    .background(Color.Red),
                            )
                        }
                    },
                    page = drawn?.let {
                        {
                            Column(Modifier.fillMaxSize()) {
                                Box(Modifier.onFocusChanged { pageFocused = it.isFocused }.focusable().clickable { taps++ }.size(40.dp).testTag(PAGE_BUTTON))
                                LazyColumn(Modifier.fillMaxSize().testTag(PAGE), state = list) {
                                    items(40) { row -> BasicText("Row $row", Modifier.height(80.dp)) }
                                }
                            }
                        }
                    },
                    bar = if (!bar) null else { { BarSquares(transition) } },
                )
            }
        }
        compose.waitForIdle()
        if (!open) return
        compose.runOnUiThread { top = SOURCE }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** The page's frame: in the unmerged tree, as TalkBack doesn't reach a page on its way back. */
    private fun page(): DpRect = compose.onNodeWithTag(PAGE, useUnmergedTree = true).getBoundsInRoot()

    private fun width(): Float = page().let { it.right - it.left }.value

    /** How far the content has scrolled, in pixels. */
    private fun scrolled(): Int = list.firstVisibleItemIndex * 160 + list.firstVisibleItemScrollOffset

    /** Changes the state as the next frame sees it, the clock held. */
    private fun change(block: () -> Unit) = compose.runOnUiThread {
        block()
        Snapshot.sendApplyNotifications()
    }

    private fun gesture(vararg events: BackEventCompat) {
        val back = compose.activity.onBackPressedDispatcher
        compose.runOnUiThread {
            back.dispatchOnBackStarted(events.first())
            for (event in events.drop(1)) back.dispatchOnBackProgressed(event)
        }
        compose.waitForIdle()
    }

    private fun at(progress: Float) = BackEventCompat(480f * progress, 400f, progress, BackEventCompat.EDGE_LEFT)

    @Test
    fun aPageThatScrollsIsDraggedDownOnlyFromItsTop() {
        show()
        assertEquals(390f, width(), 0.5f)
        compose.onNodeWithTag(PAGE).performScrollToIndex(5)
        compose.waitForIdle()
        val root = compose.onRoot()

        // Away from its top, a drag down scrolls the content, past the touch slop, and the page stays.
        val before = scrolled()
        root.performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 250f), delayMillis = 400)
        }
        compose.waitForIdle()
        assertEquals(390f, width(), 0.5f)
        val back = before - scrolled()
        assertTrue("Scrolled $back", back in 250..300)
        root.performTouchInput { up() }
        compose.mainClock.advanceTimeBy(1_000)

        // Once at its top, the rest of the drag takes the page.
        root.performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 1_000f), delayMillis = 400)
            moveBy(Offset(0f, 100f), delayMillis = 400)
        }
        compose.waitForIdle()
        assertEquals(0, scrolled())
        assertEquals(390f * (1f - 100f / (1.55f * 1688f)), width(), 0.5f)

        // Let go short of a quarter of the window, slowly, it springs back.
        root.performTouchInput { up() }
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(390f, width(), 0.5f)
        assertEquals(SOURCE, top)
    }

    @Test
    fun aPageAtItsTopDraggedFarGoesBackIntoItsSource() {
        show()
        compose.onRoot().performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 600f), delayMillis = 400)
            up()
        }
        compose.waitForIdle()
        assertEquals(null, top)
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag(PAGE).assertDoesNotExist()
    }

    @Test
    fun aWheelPastThePagesTopTakesNothingFromTheNextTap() {
        show()
        compose.onNodeWithTag(PAGE).performScrollToIndex(2)
        compose.waitForIdle()
        // The wheel turned up, as far as the content's top and on.
        compose.onNodeWithTag(PAGE).performMouseInput {
            moveTo(center)
            repeat(12) { scroll(-1f) }
        }
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(0, scrolled())
        assertEquals(390f, width(), 0.5f)

        // A tap low on the page afterwards is a tap: no drag from where a finger last was takes it.
        compose.onRoot().performTouchInput { click(Offset(centerX, height * 0.9f)) }
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(SOURCE, top)
        assertEquals(390f, width(), 0.5f)
    }

    @Test
    fun aDragTheSystemCancelsSpringsBackHoweverFarItWent() {
        show()
        compose.onRoot().performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 600f), delayMillis = 400)
            cancel()
        }
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(SOURCE, top)
        assertEquals(390f, width(), 0.5f)
    }

    @Test
    fun aFingerCatchesThePageSpringingBackWhereItIs() {
        show()
        val root = compose.onRoot()
        root.performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 150f), delayMillis = 400)
        }
        compose.waitForIdle()
        val dragged = width()
        compose.mainClock.autoAdvance = false
        root.performTouchInput { up() }
        compose.mainClock.advanceTimeBy(64)

        // Down on it as it springs back, the finger holds it where it is.
        root.performTouchInput { down(center) }
        val caught = width()
        assertTrue("Caught at $caught from $dragged", caught > dragged + 1f && caught < 389f)
        compose.mainClock.advanceTimeBy(500)
        assertEquals(caught, width(), 0.5f)

        // And drags it on from there.
        root.performTouchInput { moveBy(Offset(0f, 100f), delayMillis = 400) }
        compose.mainClock.advanceTimeBy(16)
        assertTrue("Dragged on to ${width()} from $caught", width() < caught - 1f)
        root.performTouchInput { up() }
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(390f, width(), 0.5f)
        assertEquals(SOURCE, top)
    }

    @Test
    fun aTapReachesThePageAsACancelledBackSpringsBack() {
        show()
        gesture(at(0f), at(0.5f))
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.dispatchOnBackCancelled() }
        compose.mainClock.advanceTimeBy(48)
        assertTrue("Springing back at ${width()}", width() < 389f)
        compose.onNodeWithTag(PAGE_BUTTON).performClick()
        compose.mainClock.advanceTimeBy(16)
        assertEquals(1, taps)
    }

    @Test
    fun aBackGestureStartedAsThePageSpringsBackTakesItOver() {
        show()
        gesture(at(0f), at(0.5f))
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.dispatchOnBackCancelled() }
        compose.mainClock.advanceTimeBy(48)
        gesture(at(0f), at(0.3f), at(0.6f))
        // Long after the spring back would have ended, the page is where the new gesture holds it: 94 % of its size.
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(390f * 0.94f, width(), 0.5f)
    }

    @Test
    fun theKeyboardsFocusLeavesTheRootUnderThePageUntilItHasGone() {
        show(open = false)
        compose.runOnUiThread { rootFocus.requestFocus() }
        compose.waitForIdle()
        assertTrue(rootFocused)
        compose.runOnUiThread { top = SOURCE }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertFalse(rootFocused)

        // Tab goes to the page, and never into the root under it; nor does a request.
        repeat(3) {
            compose.onRoot().performKeyInput { pressKey(Key.Tab) }
            compose.waitForIdle()
            assertFalse(rootFocused)
        }
        assertTrue(pageFocused)
        compose.runOnUiThread { rootFocus.requestFocus() }
        compose.waitForIdle()
        assertFalse(rootFocused)

        // Once the page has gone, the root takes it again.
        compose.runOnUiThread { top = null }
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()
        compose.runOnUiThread { rootFocus.requestFocus() }
        compose.waitForIdle()
        assertTrue(rootFocused)
    }

    @Test
    fun aPageOnItsWayBackDoesntFollowItsSourcesNodeToAnotherCard() {
        show()
        compose.mainClock.autoAdvance = false
        change { top = null }
        compose.mainClock.advanceTimeBy(64)
        // The list hands the card's node on to another card, lower down.
        change {
            sourceId = "other"
            sourceTop = 600.dp
        }
        compose.mainClock.advanceTimeBy(100)
        // Most of the way back to the card's last frame, 120 dp down, rather than to 600 dp: the list is 40 dp down the
        // page.
        val going = page()
        assertTrue("Going to the card's last frame: $going", going.top.value < 200f)
    }

    @Test
    fun aPageShownWithoutAFlightShrinksUnderTheBackGestureInPoints() {
        show(initial = SOURCE)
        assertEquals(390f, width(), 0.5f)
        gesture(at(0f), at(0.5f))
        // Half way: 95 % of its size, moved right as far as leaves 8 dp from the edge at the end, the finger level. The
        // list is 40 dp down the page.
        val held = page()
        assertEquals(370.5f, (held.right - held.left).value, 0.5f)
        assertEquals(15.5f, held.left.value, 0.5f)
        assertEquals(21.1f + 40f * 0.95f, held.top.value, 0.5f)
    }

    @Test
    fun underReduceMotionTheSourceStaysAsThePageFadesInOverIt() {
        show(reduceMotion = true, open = false)
        compose.mainClock.autoAdvance = false
        change { top = SOURCE }
        compose.mainClock.advanceTimeBy(150)
        // The card's red shows through the page coming in over it.
        val pixel = compose.onRoot().captureToImage().asAndroidBitmap().getPixel(390, 360)
        val red = (pixel shr 16) and 0xFF
        val green = (pixel shr 8) and 0xFF
        assertTrue("Red $red, green $green", red - green > 40)
    }

    // The bars' items.

    /**
     * How much of each of the bar's squares is drawn, against what is drawn just beside it, 80 dp down: black over
     * anything, a square drawn at `a` leaves `1 - a` of what is under it. It shows `bar`. Robolectric's capture now and
     * then shows a layer's alpha a frame or two late, so with the flight held it lets the window draw again; a
     * crossfade's layer can still be a frame behind then, at `before`.
     */
    private fun assertDrawn(bar: ZoomBar, before: ZoomBar) {
        fun drawn(): Pair<Float, Float> {
            val image = compose.onRoot().captureToImage().asAndroidBitmap()
            fun green(x: Int) = ((image.getPixel(x * 2, 160) shr 8) and 0xFF).toFloat()
            return (1f - green(58) / green(62)) to (1f - green(332) / green(328))
        }
        fun ZoomBar.drawnIn(d: Pair<Float, Float>) = abs(d.first - leading) <= 0.02f && abs(d.second - trailing) <= 0.02f
        var d = drawn()
        repeat(3) {
            if (bar.drawnIn(d)) return
            // The window draws on Robolectric's own clock, which the test's frame clock doesn't move.
            shadowOf(Looper.getMainLooper()).idleFor(Duration.ofMillis(17))
            d = drawn()
        }
        assertTrue(
            "‹ drawn at ${d.first} for ${bar.leading} (${before.leading}), ••• at ${d.second} for ${bar.trailing}",
            bar.drawnIn(d) || before.drawnIn(d),
        )
    }

    @Test
    fun theBarsItemsAreDrawnAsMuchAsTheFlightShowsThem() {
        show(open = false, bar = true)
        compose.mainClock.autoAdvance = false
        change { top = SOURCE }
        var before = transition.bar
        var coming = 0
        repeat(40) {
            compose.mainClock.advanceTimeByFrame()
            val bar = transition.bar
            assertDrawn(bar, before)
            before = bar
            if (bar.leading > 0.1f && bar.leading < 0.9f) coming++
        }
        assertTrue("Never partway", coming > 0)
        assertDrawn(before, before)

        change { top = null }
        var waiting = 0
        repeat(20) {
            compose.mainClock.advanceTimeByFrame()
            val bar = transition.bar
            assertDrawn(bar, before)
            before = bar
            if (bar.leading == 0f && bar.trailing == 1f) waiting++
        }
        // ‹ went at once, ••• a moment later.
        assertTrue("••• never waited", waiting > 0)
        assertEquals(0f, transition.bar.trailing, 0f)
    }

    /** Each of the bars' items, `step` at most from `before`. */
    private fun assertNear(before: ZoomBar, after: ZoomBar, step: Float) {
        val message = "$before to $after"
        assertEquals(message, before.leading, after.leading, step)
        assertEquals(message, before.trailing, after.trailing, step)
        assertEquals(message, before.root, after.root, step)
        assertEquals(message, before.title, after.title, step)
    }

    @Test
    fun aFlightTurnedRoundTakesTheBarsItemsOnFromWhereTheyAre() {
        show(bar = true)
        compose.mainClock.autoAdvance = false
        // Going back, ••• still there and the list's title partway back, it opens again.
        change { top = null }
        repeat(4) { compose.mainClock.advanceTimeByFrame() }
        val going = transition.bar
        assertEquals(0f, going.leading, 0f)
        assertEquals(1f, going.trailing, 0f)
        assertTrue("Title ${going.title}", going.title > 0f && going.title < 1f)
        change { top = SOURCE }
        compose.mainClock.advanceTimeByFrame()
        // No more than a frame of either flight from there.
        var last = transition.bar
        assertNear(going, last, 0.2f)
        repeat(60) {
            compose.mainClock.advanceTimeByFrame()
            val bar = transition.bar
            assertNear(last, bar, 0.35f)
            last = bar
        }
        assertEquals(ZoomBar(leading = 1f, trailing = 1f, root = 0f, title = 0f), last)

        // Opening, sent back with ‹ and ••• partway in: ‹ goes at once, the rest goes on from where it is.
        change { top = null }
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(ZoomBar.Root, transition.bar)
        change { top = SOURCE }
        repeat(60) { if (transition.bar.leading < 0.2f) compose.mainClock.advanceTimeByFrame() }
        val coming = transition.bar
        assertTrue("‹ ${coming.leading}", coming.leading < 0.8f)
        change { top = null }
        compose.mainClock.advanceTimeByFrame()
        val turned = transition.bar
        assertEquals(0f, turned.leading, 0f)
        assertEquals(coming.trailing, turned.trailing, 0.25f)
        assertEquals(coming.root, turned.root, 0.1f)
        assertEquals(coming.title, turned.title, 0.1f)
        last = turned
        repeat(30) {
            compose.mainClock.advanceTimeByFrame()
            val bar = transition.bar
            assertNear(last, bar, 0.35f)
            last = bar
        }
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(ZoomBar.Root, transition.bar)
    }

    /** Under Reduce Motion the page's bar and the root's crossfade with the page, both ways. */
    @Test
    fun underReduceMotionTheBarsCrossfadeWithThePage() {
        show(reduceMotion = true, open = false, bar = true)
        compose.mainClock.autoAdvance = false
        for (next in listOf(SOURCE, null)) {
            change { top = next }
            var before = transition.bar
            var partway = false
            repeat(30) {
                compose.mainClock.advanceTimeByFrame()
                val bar = transition.bar
                assertEquals(bar.leading, bar.trailing, 0f)
                assertEquals(1f - bar.leading, bar.root, 0.0001f)
                assertEquals(1f - bar.leading, bar.title, 0.0001f)
                val onward = if (next == null) bar.leading <= before.leading else bar.leading >= before.leading
                assertTrue("$next went back to ${bar.leading} from ${before.leading}", onward)
                before = bar
                if (bar.leading > 0.2f && bar.leading < 0.8f) partway = true
            }
            assertTrue("Never partway to $next", partway)
            compose.mainClock.advanceTimeBy(2_000)
            assertEquals(if (next == null) ZoomBar.Root else ZoomBar(leading = 1f, trailing = 1f, root = 0f, title = 0f), transition.bar)
            // Robolectric's capture of a crossfading layer can be frames behind; where it settles, it is drawn so.
            assertDrawn(transition.bar, transition.bar)
        }
    }

    private companion object {
        const val SOURCE = "source"
        const val PAGE = "page"
        const val PAGE_BUTTON = "page.button"
    }
}

/** The page's bar for the test: a black square for its ‹ and one for its •••, 40 dp each, 20 in and 60 down. */
@Composable
private fun BarSquares(transition: ZoomTransition) {
    Box(Modifier.fillMaxSize()) {
        Box(Modifier.padding(start = 20.dp, top = 60.dp).size(40.dp).pageBarItem(transition, leading = true).background(Color.Black))
        Box(Modifier.align(Alignment.TopEnd).padding(end = 20.dp, top = 60.dp).size(40.dp).pageBarItem(transition, leading = false).background(Color.Black))
    }
}
