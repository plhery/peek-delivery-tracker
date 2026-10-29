package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import android.view.accessibility.AccessibilityManager
import androidx.activity.BackEventCompat
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * How a parcel's page zooms out of its card over the list and back into it, as the iPhone's does: on a 390 × 844 dp
 * phone, the flights on a critically damped spring of response 0.32 s, about 58 % of the way 0.1 s in.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class DeliveriesTabTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var deliveries: DeliveriesState

    private val window = DpRect(0.dp, 0.dp, 390.dp, 844.dp)

    private fun show(reduceMotion: Boolean = false) {
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) { RootHost(reduceMotion = reduceMotion) }
        }
        compose.waitForIdle()
    }

    private fun bounds(node: SemanticsNodeInteraction): DpRect = node.getBoundsInRoot()

    /** The page's frame: in the unmerged tree, as TalkBack doesn't reach a page on its way back. */
    private fun page(): DpRect = bounds(compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true))

    private fun parcel(name: String): UUID = deliveries.parcels.first { it.label?.startsWith(name) == true }.id

    /** Opens `name`'s page and lets it land. */
    private fun open(name: String) {
        compose.card(name).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /**
     * Sends `events` to the back dispatcher as the system does, and lets the main thread act on them without moving
     * the clock: what they change reaches the composition from a message of its own.
     */
    private fun gesture(vararg events: BackEventCompat) {
        val back = compose.activity.onBackPressedDispatcher
        compose.runOnUiThread {
            back.dispatchOnBackStarted(events.first())
            for (event in events.drop(1)) back.dispatchOnBackProgressed(event)
        }
        compose.waitForIdle()
    }

    /** Back, pressed or a gesture let go. */
    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    /** The finger `progress` of the way across from the left edge. */
    private fun at(progress: Float) = BackEventCompat(480f * progress, 400f, progress, BackEventCompat.EDGE_LEFT)

    /** `frame` partway from `small` to the window: each of its left, top and width strictly between theirs. */
    private fun assertPartway(small: DpRect, frame: DpRect) {
        assertTrue("Left of $frame from $small", frame.left.value > 1f && frame.left.value < small.left.value - 1f)
        assertTrue("Top of $frame from $small", frame.top.value > 1f && frame.top.value < small.top.value - 1f)
        val width = frame.right - frame.left
        assertTrue("Width of $frame from $small", width > small.right - small.left + 1.dp && width < window.right - 1.dp)
    }

    private fun assertWindow(frame: DpRect) {
        assertEquals(0f, frame.left.value, 0.5f)
        assertEquals(0f, frame.top.value, 0.5f)
        assertEquals(390f, frame.right.value, 0.5f)
        assertEquals(844f, frame.bottom.value, 0.5f)
    }

    @Test
    fun theParcelsPageZoomsOutOfItsCardAndBackIntoIt() {
        show()
        val card = bounds(compose.card("New trainers"))
        val id = parcel("New trainers")
        compose.mainClock.autoAdvance = false
        compose.card("New trainers").performClick()
        compose.mainClock.advanceTimeBy(100)
        assertPartway(card, page())
        // The card is the page's meanwhile, and the tab bar has gone at once.
        assertEquals(id, deliveries.zoom.covered)
        compose.onNodeWithTag(TAB_BAR).assertDoesNotExist()
        compose.mainClock.advanceTimeBy(600)
        assertWindow(page())
        compose.onNodeWithTag(PARCEL_LIST).assertDoesNotExist()

        back()
        compose.mainClock.advanceTimeBy(100)
        assertPartway(card, page())
        assertEquals(id, deliveries.zoom.covered)
        compose.mainClock.advanceTimeBy(600)
        compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true).assertDoesNotExist()
        compose.onNodeWithTag(PARCEL_LIST).assertExists()
        assertNull(deliveries.zoom.covered)
        // The tab bar comes back 1.1 s after the page set off, as UIKit's transition only ends then.
        compose.onNodeWithTag(TAB_BAR).assertDoesNotExist()
        compose.mainClock.advanceTimeBy(500)
        compose.onNodeWithTag(TAB_BAR).assertExists()
    }

    @Test
    fun theListTakesNoTapWhileThePageFlies() {
        show()
        val other = bounds(compose.card("Birthday gift"))
        open("New trainers")
        compose.mainClock.autoAdvance = false
        back()
        compose.mainClock.advanceTimeBy(100)
        val x = ((other.left + other.right) / 2).value * compose.density.density
        val y = ((other.top + other.bottom) / 2).value * compose.density.density
        compose.onRoot().performTouchInput { click(Offset(x, y)) }
        compose.mainClock.advanceTimeBy(1_500)
        compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true).assertDoesNotExist()
        assertTrue(deliveries.navigator.navigation.path.isEmpty())
    }

    @Test
    fun thePredictiveBackGestureShrinksThePageAndLetGoItGoesBackIntoItsCard() {
        show()
        val card = bounds(compose.card("New trainers"))
        open("New trainers")
        gesture(at(0f), at(0.5f))
        // Half way: 95 % of its size, moved right as far as leaves 8 dp from the edge at the end, the finger level.
        val held = page()
        assertEquals(370.5f, (held.right - held.left).value, 0.5f)
        assertEquals(15.5f, held.left.value, 0.5f)
        assertEquals(21.1f, held.top.value, 0.5f)

        // Cancelled, it springs back.
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.dispatchOnBackCancelled() }
        compose.mainClock.advanceTimeBy(600)
        assertWindow(page())
        compose.onNodeWithTag(TAB_BAR).assertDoesNotExist()

        // Let go, it goes back into its card from where the gesture held it.
        gesture(at(0f), at(0.5f))
        compose.mainClock.autoAdvance = false
        back()
        compose.mainClock.advanceTimeBy(100)
        val going = page()
        assertTrue("Smaller than held: $going", going.right - going.left < 370.dp)
        assertTrue("Lower than held: $going", going.top > held.top + 1.dp && going.top < card.top)
        compose.mainClock.advanceTimeBy(600)
        compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true).assertDoesNotExist()
        assertTrue(deliveries.navigator.navigation.path.isEmpty())
    }

    @Test
    fun aDragDownPastAQuarterOfTheWindowClosesThePageAndAShortOneSpringsBack() {
        show()
        open("New trainers")
        val root = compose.onRoot()
        // 150 px down from where the drag took it, slowly: 1 - 150 / (1.55 × 1688) of its size, and back.
        root.performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 150f), delayMillis = 400)
        }
        compose.waitForIdle()
        val dragged = page()
        assertEquals(390f * (1f - 150f / (1.55f * 1688f)), (dragged.right - dragged.left).value, 0.5f)
        root.performTouchInput { up() }
        compose.mainClock.advanceTimeBy(600)
        assertWindow(page())
        assertFalse(deliveries.navigator.navigation.path.isEmpty())

        // 500 px is past a quarter of 1688.
        root.performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 500f), delayMillis = 400)
            up()
        }
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true).assertDoesNotExist()
        assertTrue(deliveries.navigator.navigation.path.isEmpty())
        compose.onNodeWithTag(PARCEL_LIST).assertExists()
    }

    @Test
    fun underReduceMotionThePageCrossfadesInPlace() {
        show(reduceMotion = true)
        val list = compose.drawn(compose.onRoot())
        compose.mainClock.autoAdvance = false
        compose.card("New trainers").performClick()
        compose.mainClock.advanceTimeBy(150)
        // The whole page, neither moved nor scaled, over the list as it was: both show.
        assertWindow(page())
        assertEquals(0f, deliveries.zoom.behind, 0f)
        val crossfading = compose.drawn(compose.onRoot())
        compose.mainClock.advanceTimeBy(400)
        val shown = compose.drawn(compose.onRoot())
        assertFalse(crossfading.contentEquals(list))
        assertFalse(crossfading.contentEquals(shown))

        // The gesture moves nothing.
        gesture(at(0f), at(0.5f))
        compose.mainClock.advanceTimeBy(50)
        assertWindow(page())
        back()
        compose.mainClock.advanceTimeBy(150)
        assertWindow(page())
        compose.mainClock.advanceTimeBy(400)
        compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true).assertDoesNotExist()
        // The tab bar is back once the page has gone.
        compose.onNodeWithTag(TAB_BAR).assertExists()
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun besideTheListThePageShowsAtOnceWithoutAZoom() {
        show()
        compose.mainClock.autoAdvance = false
        compose.card("Birthday gift").performClick()
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        compose.onNode(hasText("Birthday gift", substring = true) and hasAnyAncestor(hasTestTag(PAGE_COLUMN))).assertExists()
        assertNull(deliveries.zoom.covered)
        assertEquals(0f, deliveries.zoom.behind, 0f)
        compose.onNodeWithTag(TAB_BAR).assertExists()
    }

    @Test
    fun aPageWhoseCardIsOffScreenComesFromTheMiddleOfTheWindowAndGoesBackThere() {
        show()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.waitForIdle()
        val id = parcel("New trainers")
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { deliveries.navigator.open(id) }
        var frames = 0
        while (compose.onAllNodesWithTag(PARCEL_PAGE, useUnmergedTree = true).fetchSemanticsNodes().isEmpty()) {
            check(frames++ < 10) { "The page didn't open" }
            compose.mainClock.advanceTimeByFrame()
        }
        // A card of the list's width less its margins, in the middle.
        val coming = page()
        assertEquals(358f, (coming.right - coming.left).value, 0.5f)
        assertEquals(195f, ((coming.left + coming.right) / 2).value, 0.5f)
        compose.mainClock.advanceTimeBy(1_000)
        assertWindow(page())

        back()
        compose.mainClock.advanceTimeBy(400)
        val going = page()
        assertEquals(358f, (going.right - going.left).value, 1f)
        assertEquals(195f, ((going.left + going.right) / 2).value, 0.5f)
        compose.mainClock.advanceTimeBy(500)
        compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun aParcelOpenedWhileAnotherGoesBackTakesItsPlace() {
        show()
        open("New trainers")
        compose.mainClock.autoAdvance = false
        back()
        compose.mainClock.advanceTimeBy(100)
        val other = parcel("Birthday gift")
        compose.runOnUiThread { deliveries.navigator.open(other) }
        compose.mainClock.advanceTimeBy(1_000)
        assertWindow(page())
        compose.onNode(hasText("Birthday gift", substring = true) and hasAnyAncestor(hasTestTag(PARCEL_PAGE))).assertExists()
        assertEquals(other, deliveries.zoom.covered)
        compose.onNodeWithTag(TAB_BAR).assertDoesNotExist()
    }

    @Test
    fun theWindowMadeAgainMidFlightShowsThePageAtRest() {
        val source = FakeDeliveries(DemoCards.parcels())
        val recreation = Recreation(compose)
        recreation.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) { RootHost(reduceMotion = false, source = source) }
        }
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        compose.card("New trainers").performClick()
        compose.mainClock.advanceTimeBy(100)
        recreation.recreate()
        // From its first frame, without a flight.
        assertWindow(page())
        assertEquals(parcel("New trainers"), deliveries.zoom.covered)
        // TalkBack stops reaching the bar, unplaced from the first frame, once the shell is drawn again.
        compose.mainClock.advanceTimeByFrame()
        compose.onNodeWithTag(TAB_BAR).assertDoesNotExist()
    }

    @Test
    fun theParcelOpenWhenTheSystemClosedTheAppOpensAgainWithoutAZoom() {
        var source by mutableStateOf(FakeDeliveries(DemoCards.parcels()))
        val recreation = Recreation(compose)
        recreation.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) { RootHost(reduceMotion = false, source = source) }
        }
        compose.waitForIdle()
        open("New trainers")
        // The new process's parcels, which the window made again draws from.
        compose.mainClock.autoAdvance = false
        source = source.newProcess()
        recreation.recreate()
        // It opens once the parcels are back, at rest from its first frame.
        var frames = 0
        while (compose.onAllNodesWithTag(PARCEL_PAGE).fetchSemanticsNodes().isEmpty()) {
            check(frames++ < 10) { "The parcel didn't open again" }
            compose.mainClock.advanceTimeByFrame()
        }
        assertWindow(page())
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
    fun talkBackGoesToThePageAsItLandsAndBackToTheCardAsItGoes() {
        show()
        val view = talkBack()
        open("New trainers")
        val title = compose.onNode(hasText("New trainers", substring = true) and hasAnyAncestor(hasTestTag(PARCEL_PAGE)))
        assertTrue(title.hasTalkBackFocus(view))

        back()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertTrue(compose.card("New trainers").hasTalkBackFocus(view))
    }

    @Test
    fun aParcelOpenedBeforeTheParcelsCameBackZoomsOutOfItsCardTheNextTime() {
        var process = FakeDeliveries(DemoCards.parcels())
        val restoration = StateRestorationTester(compose)
        restoration.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(reduceMotion = false, source = remember { process })
            }
        }
        compose.waitForIdle()
        open("New trainers")
        // The system closes the app, and the next process has no parcels until it has read them again.
        process = FakeDeliveries(emptyList())
        restoration.emulateSavedInstanceStateRestore()
        compose.waitForIdle()
        // A notification opens another parcel before they come.
        val gift = DemoCards.parcels().first { it.label?.startsWith("Birthday gift") == true }.id
        compose.runOnUiThread { deliveries.navigator.open(gift) }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        compose.runOnUiThread { process.parcels.value = DemoCards.parcels() }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        back()
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()

        val card = bounds(compose.card("Birthday gift"))
        compose.mainClock.autoAdvance = false
        compose.card("Birthday gift").performClick()
        compose.mainClock.advanceTimeBy(100)
        assertPartway(card, page())
    }
}
