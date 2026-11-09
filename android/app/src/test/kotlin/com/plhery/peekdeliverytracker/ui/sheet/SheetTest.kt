package com.plhery.peekdeliverytracker.ui.sheet

import android.view.View
import androidx.activity.BackEventCompat
import androidx.activity.compose.BackHandler
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.TabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.WindowLayers
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The sheet on its own, a field in what it holds: what takes it to its large height, a keyboard up for that field and
 * a window as low as a phone on its side; a drag that moves it without composing what it holds again; the field's
 * focus let go as it starts to go; the sheet opened again on its way out after the back gesture; the detent asked
 * for; nothing but its own content sending it away while it isn't dismissible; taps on the status bar, which leave it
 * up; the page sheet in regular windows, the iPad's, an Android tablet's and an unfolded foldable's, beside the
 * form there; and the grabber, whose target a tap takes the sheet to its next detent from, gone on a phone on its side
 * and under another sheet.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class SheetTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var view: View
    private var visible by mutableStateOf(true)
    private var text by mutableStateOf("")
    private var compositions = 0
    private var detent by mutableStateOf<SheetDetent?>(null)
    private var dismissible by mutableStateOf(true)
    private var page by mutableStateOf(false)
    private val rested = mutableListOf<SheetDetent>()

    /** The backs that reached what the sheet is over. */
    private var hostBacks = 0

    private fun show() {
        compose.setContent {
            view = LocalView.current
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = null) {
                // What the sheet is over takes the back, unless the sheet does.
                BackHandler { hostBacks++ }
                Box(Modifier.fillMaxSize()) {
                    Sheet(
                        visible, onDismiss = { visible = false }, paneTitle = "Sheet", detent = detent,
                        onDetentChange = { rested += it }, dismissible = dismissible, page = page,
                    ) {
                        SideEffect { compositions++ }
                        SheetPage("Sheet", "Done", onConfirm = { visible = false }) {
                            FormSection("Name") {
                                BasicTextField(text, { text = it }, Modifier.testTag(FIELD))
                            }
                            FormSection(null) {
                                repeat(12) { FormLabelRow("Row $it") }
                            }
                        }
                    }
                }
            }
        }
        compose.waitForIdle()
    }

    /** The sheet's top, in points from the window's. */
    private fun top(): Float = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot().top.value

    private fun windowHeight(): Float = compose.onRoot().getUnclippedBoundsInRoot().bottom.value

    /** The keyboard `height` points tall, or gone at 0, as the window's insets tell it. */
    private fun keyboard(height: Int) {
        compose.runOnUiThread {
            val px = (height * view.resources.displayMetrics.density).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.ime(), Insets.of(0, 0, 0, px))
                .setVisible(WindowInsetsCompat.Type.ime(), px > 0)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
    }

    /** The status bar `status` points tall and the bottom bar `bottom`, as the window's insets tell them. */
    private fun bars(status: Int, bottom: Int = 0) {
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, (status * scale).toInt(), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, (bottom * scale).toInt()))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    /** A tap at (`x`, `y`), in points from the window's top leading corner. */
    private fun tap(x: Float, y: Float) {
        compose.onRoot().performTouchInput { click(Offset(x.dp.toPx(), y.dp.toPx())) }
        compose.waitForIdle()
    }

    /** The sheet stands at (`left`, `top`), `width` × `height` points. */
    private fun assertFrame(left: Float, top: Float, width: Float, height: Float) {
        val panel = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot()
        val frame = listOf(panel.left, panel.top, panel.right - panel.left, panel.bottom - panel.top).map { it.value }
        listOf(left, top, width, height).zip(frame).forEach { (expected, actual) -> assertEquals("$frame", expected, actual, 0.5f) }
    }

    /**
     * The page sheet stands at (`left`, `top`), `width` × `height`; the form, in its place, as it was, 580 × 660 in the
     * window's middle.
     */
    private fun assertPageSheetThenForm(left: Float, top: Float, width: Float, height: Float) {
        page = true
        show()
        assertFrame(left, top, width, height)
        page = false
        compose.waitForIdle()
        val window = compose.onRoot().getUnclippedBoundsInRoot()
        assertFrame((window.right.value - 580f) / 2, (window.bottom.value - 660f) / 2, 580f, 660f)
    }

    @Test
    fun aKeyboardUpForTheFieldItHoldsTakesItToItsLargeHeight() {
        show()
        assertEquals(windowHeight() * 0.4714f, top(), 2f)
        compose.onNodeWithTag(FIELD).performClick()
        keyboard(336)
        compose.waitForIdle()
        assertEquals(10f, top(), 1f)
    }

    @Test
    fun itLetsGoOfTheFieldsFocusAsItStartsToGo() {
        show()
        compose.onNodeWithTag(FIELD).performClick()
        compose.onNodeWithTag(FIELD).assertIsFocused()
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread {
            visible = false
            Snapshot.sendApplyNotifications()
        }
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        assertTrue("Still on its way down: ${top()}", top() < windowHeight() * 0.6f)
        compose.onNodeWithTag(FIELD).assertIsNotFocused()
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
    }

    @Test
    @Config(qualifiers = "w844dp-h390dp-land-xhdpi")
    fun onAPhoneOnItsSideItStandsAtItsLargeHeightAttachedWithNoGrabber() {
        show()
        assertEquals(10f, top(), 1f)
        val panel = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot()
        assertEquals(0f, panel.left.value, 0.5f)
        assertEquals(compose.onRoot().getUnclippedBoundsInRoot().right.value, panel.right.value, 0.5f)
        assertEquals(emptyList<Float>(), grabbers())
    }

    @Test
    fun aTapOnTheGrabberOrBesideItsPillTakesItToTheNextDetentAndBack() {
        show()
        val medium = top()
        val target = compose.onNodeWithTag(SHEET_GRABBER, useUnmergedTree = true).getUnclippedBoundsInRoot()
        val panel = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot()
        // 72 × 20 from the sheet's top, in its middle: wider and taller than the 36 × 5 pill.
        assertEquals(72f, (target.right - target.left).value, 0.5f)
        assertEquals(20f, (target.bottom - target.top).value, 0.5f)
        assertEquals(medium, target.top.value, 0.5f)
        assertEquals(((panel.left + panel.right) / 2).value, ((target.left + target.right) / 2).value, 0.5f)
        compose.onNodeWithTag(SHEET_GRABBER, useUnmergedTree = true).performTouchInput { click(center) }
        compose.waitForIdle()
        assertEquals(10f, top(), 1f)
        assertEquals(listOf(SheetDetent.Large), rested)
        // 30 beside the pill's middle and 15 under the sheet's top, off the pill: back to medium.
        compose.onNodeWithTag(SHEET_GRABBER, useUnmergedTree = true).performTouchInput { click(Offset(centerX - 30.dp.toPx(), 15.dp.toPx())) }
        compose.waitForIdle()
        assertEquals(medium, top(), 2f)
        assertEquals(listOf(SheetDetent.Large, SheetDetent.Medium), rested)
    }

    @Test
    fun underAnotherSheetInAnOverlayItShowsNoGrabberUntilThatOneHasGone() = aSheetOverAnother(windowFront = false)

    @Test
    fun underAnotherSheetInTheWindowsFrontItShowsNoGrabberUntilThatOneHasGone() = aSheetOverAnother(windowFront = true)

    /**
     * A sheet at its medium detent, in an overlay drawing its layers itself or in the window's front ([windowFront]),
     * with its grabber; another over it at large, whose grabber alone shows; that one gone, the first's again.
     */
    private fun aSheetOverAnother(windowFront: Boolean) {
        var over by mutableStateOf(false)
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = null) {
                val front = remember { if (windowFront) WindowLayers() else null }
                val overlay = remember { TabOverlay(window = front, name = "sheets") }
                Box(Modifier.fillMaxSize()) {
                    CompositionLocalProvider(LocalTabOverlay provides overlay) {
                        Sheet(visible, onDismiss = { visible = false }, paneTitle = "Under") { Box(Modifier.fillMaxSize()) }
                        Sheet(over, onDismiss = { over = false }, paneTitle = "Over", detent = SheetDetent.Large) { Box(Modifier.fillMaxSize()) }
                    }
                    overlay.Content()
                    front?.Content()
                }
            }
        }
        compose.waitForIdle()
        val medium = windowHeight() * 0.4714f
        assertEquals(1, grabbers().size)
        assertEquals(medium, grabbers().single(), 2f)
        over = true
        compose.waitForIdle()
        assertEquals("The one over it, at large", listOf(10f), grabbers().map { Math.round(it).toFloat() })
        over = false
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertEquals(1, grabbers().size)
        assertEquals(medium, grabbers().single(), 2f)
    }

    /** The tops of the grabbers showing, in points from the window's. */
    private fun grabbers(): List<Float> =
        compose.onAllNodesWithTag(SHEET_GRABBER, useUnmergedTree = true).fetchSemanticsNodes().map { it.boundsInRoot.top / compose.density.density }

    @Test
    fun aDragMovesItWithoutComposingWhatItHoldsAgain() {
        show()
        val before = compositions
        compose.onNodeWithTag(SHEET_PANEL).performTouchInput { swipeUp(top + 30f, top - 500f, durationMillis = 300) }
        compose.waitForIdle()
        assertTrue("At ${top()}", top() < 50f)
        assertTrue("Composed ${compositions - before} times", compositions - before <= 1)
    }

    @Test
    fun openedAgainOnItsWayOutAfterTheBackGestureItComesBackToItsHeight() {
        show()
        val medium = top()
        val back = compose.activity.onBackPressedDispatcher
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread {
            back.dispatchOnBackStarted(BackEventCompat(0f, 400f, 0f, BackEventCompat.EDGE_LEFT))
            back.dispatchOnBackProgressed(BackEventCompat(240f, 400f, 1f, BackEventCompat.EDGE_LEFT))
            back.onBackPressed()
        }
        compose.mainClock.advanceTimeBy(100)
        assertFalse(visible)
        visible = true
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertEquals(medium, top(), 1f)
    }

    @Test
    fun itComesUpAtTheDetentAskedForMovesToAnotherAndADragSaysWhereItRests() {
        detent = SheetDetent.Large
        show()
        assertEquals(10f, top(), 1f)
        detent = SheetDetent.Medium
        compose.waitForIdle()
        assertEquals(windowHeight() * 0.4714f, top(), 2f)
        assertEquals(emptyList<SheetDetent>(), rested)
        compose.onNodeWithTag(SHEET_PANEL).performTouchInput { swipeUp(top + 30f, top - 500f, durationMillis = 300) }
        compose.waitForIdle()
        assertEquals(listOf(SheetDetent.Large), rested)
        assertEquals(10f, top(), 1f)
    }

    @Test
    fun whileItIsntDismissibleNeitherADragDownNorATapOutsideNorTheBackSendItAway() {
        dismissible = false
        show()
        val medium = top()
        compose.onNodeWithTag(SHEET_PANEL).performTouchInput { swipeDown(top + 30f, top + 600f, durationMillis = 200) }
        compose.waitForIdle()
        assertTrue(visible)
        assertEquals(medium, top(), 1f)
        compose.onRoot().performTouchInput { click(Offset(width / 2f, 40f)) }
        compose.waitForIdle()
        assertTrue(visible)
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
        assertTrue(visible)
        assertEquals(medium, top(), 1f)
        // The back went nowhere, rather than to what the sheet is over.
        assertEquals(0, hostBacks)
        // Dismissible again, the back sends it away, and goes no further.
        dismissible = true
        compose.waitForIdle()
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
        assertFalse(visible)
        assertEquals(0, hostBacks)
        // Gone, it leaves the back to what it was over.
        compose.mainClock.advanceTimeBy(1_000)
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
        assertEquals(1, hostBacks)
    }

    @Test
    fun aTapOnTheStatusBarAboveItAtItsLargeDetentLeavesItUp() {
        detent = SheetDetent.Large
        show()
        bars(status = 62)
        assertEquals(62f, top(), 1f)
        // Where the iPhone's taps left its page sheet up.
        tap(220f, 40f)
        tap(300f, 57f)
        tap(60f, 57f)
        assertTrue(visible)
    }

    @Test
    fun aTapOnTheDimJustUnderTheStatusBarSendsItAway() {
        show()
        bars(status = 62)
        tap(195f, 63f)
        assertFalse(visible)
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun overARegularFormATapOnTheStatusBarLeavesItUpAndOneBesideItSendsItAway() {
        show()
        bars(status = 32)
        tap(516f, 20f)
        assertTrue(visible)
        tap(100f, 700f)
        assertFalse(visible)
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun onTheIPadUprightThePageSheetStands52InAnd32FromItsTopAndBottom() = assertPageSheetThenForm(52f, 32f, 928f, 1312f)

    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun onTheIPadOnItsSideThePageSheetIsCentred76DownAnd32Up() = assertPageSheetThenForm(224f, 76f, 928f, 924f)

    @Test
    @Config(qualifiers = "w800dp-h1280dp-xhdpi")
    fun onAnAndroidTabletUprightThePageSheetIsAsWideAsTheWindowLess52OnEachSide() = assertPageSheetThenForm(52f, 32f, 696f, 1216f)

    @Test
    @Config(qualifiers = "w1280dp-h800dp-land-xhdpi")
    fun onAnAndroidTabletOnItsSideThePageSheetIsAsWideAsTheWindowIsTallLess52OnEachSide() = assertPageSheetThenForm(292f, 76f, 696f, 692f)

    @Test
    @Config(qualifiers = "w850dp-h880dp-xhdpi")
    fun onAnUnfoldedFoldableThePageSheetStandsAsInATallWindow() = assertPageSheetThenForm(52f, 32f, 746f, 816f)

    @Test
    @Config(qualifiers = "w1280dp-h800dp-land-xhdpi")
    fun thePageSheetStandsUnderTheStatusBarAndOverTheBottomBarWhereTheyAreTallerThanItsMargins() {
        page = true
        show()
        // A status bar lower than 32 leaves its top where it was; a three-button bar, taller than 32, lifts its foot.
        bars(status = 24, bottom = 48)
        assertFrame(292f, 76f, 696f, 676f)
        bars(status = 40, bottom = 20)
        assertFrame(292f, 84f, 696f, 684f)
    }
}

private const val FIELD = "sheet.test.field"
