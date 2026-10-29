package com.plhery.peekdeliverytracker.ui.zoom

import androidx.activity.BackEventCompat
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
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
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

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

    /** The root, with the page on top from the first frame (`initial`), or opened once the root is drawn (`open`). */
    private fun show(reduceMotion: Boolean = false, initial: String? = null, open: Boolean = initial == null) {
        top = initial
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = reduceMotion, reduceTransparency = false, carrierIdentities = null) {
                val transition = rememberZoomTransition()
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
                                Box(Modifier.onFocusChanged { pageFocused = it.isFocused }.focusable().size(40.dp).testTag(PAGE_BUTTON))
                                LazyColumn(Modifier.fillMaxSize().testTag(PAGE), state = list) {
                                    items(40) { row -> BasicText("Row $row", Modifier.height(80.dp)) }
                                }
                            }
                        }
                    },
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

    private companion object {
        const val SOURCE = "source"
        const val PAGE = "page"
        const val PAGE_BUTTON = "page.button"
    }
}
