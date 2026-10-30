package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.ClipboardManager
import android.view.View
import android.view.accessibility.AccessibilityManager
import androidx.activity.BackEventCompat
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.TrackingNumberCopy
import com.plhery.peekdeliverytracker.core.trackingNumbers
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A parcel's page's frame (B-S01): the paper in one column or two, the page for a parcel gone (B-ST01), and its bar,
 * ‹ and the ••• menu (B-S09), which stays at the window's top as the page zooms, is dragged or is held by the back
 * gesture under it, and fades in as the page lands and out as it goes back into its card.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelPageTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    /** The text's size, times the normal. */
    private var fontScale by mutableFloatStateOf(1f)

    /** The demo's deliveries, in the demo (whose bar stands over a page) or before signing in. */
    private fun show(session: AuthenticationState = AuthenticationState.Demo) {
        source = FakeDeliveries(DemoCards.parcels(), session)
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(
                LocalDensity provides Density(density.density, fontScale),
                LocalDeliveriesState provides { deliveries = it },
            ) {
                RootHost(state = session, source = source, reduceMotion = false)
            }
        }
        compose.waitForIdle()
    }

    /** Opens `name`'s page and lets it land, or shows it beside the list. */
    private fun open(name: String) {
        compose.openCard(name)
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun bounds(node: SemanticsNodeInteraction): DpRect = node.getBoundsInRoot()

    private fun page(): DpRect = bounds(compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true))

    private fun width(rect: DpRect): Float = (rect.right - rect.left).value

    private fun item(item: ParcelMenuItem) = compose.onNodeWithTag(menuItemTag(item))

    private fun shownItems(): List<ParcelMenuItem> = ParcelMenuItem.entries.filter { exists(menuItemTag(it)) }

    private fun openMenu() {
        compose.onNodeWithTag(PAGE_MENU_BUTTON).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun choose(item: ParcelMenuItem) {
        item(item).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
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

    /** ‹ where the bar puts it, 20 in from the window's side and as large as it is at rest: no part of a page in flight. */
    private fun assertBackPinned() {
        val back = bounds(compose.onNodeWithTag(PAGE_BACK, useUnmergedTree = true))
        assertEquals(20f, back.left.value, 0.5f)
        assertEquals(44f, width(back), 0.5f)
        val menu = bounds(compose.onNodeWithTag(PAGE_MENU_BUTTON, useUnmergedTree = true))
        assertEquals(390f - 20f, menu.right.value, 0.5f)
        assertEquals(back.top.value, menu.top.value, 0.5f)
    }

    private fun closed() {
        compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true).assertDoesNotExist()
        assertTrue(deliveries.navigator.navigation.path.isEmpty())
        compose.onNodeWithTag(PARCEL_LIST).assertExists()
    }

    // The columns.

    @Test
    fun onAPhoneTheHistoryStandsUnderThePass() {
        show()
        open("Birthday gift")
        assertTrue(exists(PAGE_SINGLE_COLUMN))
        assertTrue(!exists(PAGE_COLUMNS))
        // The paper 16 in from each side of the window.
        val paper = bounds(compose.onNodeWithTag(PAGE_SINGLE_COLUMN))
        assertEquals(16f, paper.left.value, 0.5f)
        assertEquals(358f, width(paper), 0.5f)
    }

    /** An unfolded foldable on its side: the list beside the page, which has no room for two columns. */
    @Test
    @Config(qualifiers = "w841dp-h701dp-land-xhdpi")
    fun besideTheListOnAnUnfoldedFoldableTheHistoryStaysUnderThePass() {
        show()
        open("Birthday gift")
        assertEquals(DeliveriesArrangement.SideBySide, deliveries.arrangement)
        assertTrue(exists(PAGE_SINGLE_COLUMN))
        assertTrue(!exists(PAGE_COLUMNS))
    }

    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun besideTheListOnATabletTheHistoryStandsBesideThePass() {
        show()
        open("Birthday gift")
        assertTrue(exists(PAGE_COLUMNS))
        assertTrue(!exists(PAGE_SINGLE_COLUMN))
        // No wider than 1080 with the margins, centred in the page's column.
        val paper = bounds(compose.onNodeWithTag(PAGE_COLUMNS))
        val column = bounds(compose.onNodeWithTag(PAGE_COLUMN))
        assertTrue("Paper $paper", width(paper) <= 1080f - 32f + 0.5f)
        assertEquals(((column.left + column.right) / 2).value, ((paper.left + paper.right) / 2).value, 1f)
    }

    /** At the largest text the columns want 420 each: a page of 880 that holds two at the normal sizes holds one. */
    @Test
    @Config(qualifiers = "w1200dp-h900dp-land-xhdpi")
    fun atTheLargestTextThePageKeepsOneColumnWhereTwoWouldFit() {
        show()
        open("Birthday gift")
        assertTrue(exists(PAGE_COLUMNS))

        fontScale = 2f
        compose.waitForIdle()
        assertEquals(DeliveriesArrangement.SideBySide, deliveries.arrangement)
        assertTrue(exists(PAGE_SINGLE_COLUMN))
        assertTrue(!exists(PAGE_COLUMNS))
    }

    // A parcel gone, and the sample.

    @Test
    fun aParcelGoneFromTheDeliveriesSaysSoAndItsMenuGoes() {
        show()
        open("Birthday gift")
        compose.onNodeWithTag(PAGE_MENU_BUTTON).assertExists()
        val gift = DemoCards.parcel("Birthday gift").id
        source.parcels.value = source.parcels.value.filterNot { it.id == gift }
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_MISSING).assertExists()
        compose.onNode(hasText("Parcel") and hasAnyAncestor(hasTestTag(PARCEL_MISSING))).assertExists()
        compose.onNode(hasText("This parcel is no longer in your delivery list.")).assertExists()
        compose.onNodeWithTag(PAGE_MENU_BUTTON).assertDoesNotExist()
        compose.onNodeWithTag(PAGE_CONTENT).assertDoesNotExist()
        // ‹ still goes back.
        compose.onNodeWithTag(PAGE_BACK).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        closed()
    }

    @Test
    fun aParcelNeverInTheDeliveriesShowsTheSame() {
        show()
        compose.runOnUiThread { deliveries.navigator.open(UUID.randomUUID()) }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_MISSING).assertExists()
        compose.onNodeWithTag(PAGE_BACK).assertExists()
        compose.onNodeWithTag(PAGE_MENU_BUTTON).assertDoesNotExist()
    }

    @Test
    fun theSamplesPageHasNoMenu() {
        show()
        compose.runOnUiThread { deliveries.openSample() }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        compose.onNodeWithTag(PAGE_CONTENT).assertExists()
        compose.onNodeWithTag(PARCEL_MISSING).assertDoesNotExist()
        compose.onNodeWithTag(PAGE_BACK).assertExists()
        compose.onNodeWithTag(PAGE_MENU_BUTTON).assertDoesNotExist()
    }

    // The bar.

    @Test
    fun backAndTheMenuAreReadWithTheirNamesAndBackGoesBackToTheList() {
        show()
        open("Birthday gift")
        compose.onNodeWithTag(PAGE_BACK).assertContentDescriptionEquals("Back")
        compose.onNodeWithTag(PAGE_MENU_BUTTON).assertContentDescriptionEquals("Parcel actions")
        assertBackPinned()
        compose.onNodeWithTag(PAGE_BACK).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        closed()
    }

    @Test
    fun theBarStaysAtTheTopAsThePageZoomsAndFadesInAsItLands() {
        show()
        compose.mainClock.autoAdvance = false
        compose.card("Birthday gift").performClick()
        val shown = mutableListOf<Float>()
        var partway = false
        repeat(60) {
            compose.mainClock.advanceTimeByFrame()
            val bar = deliveries.zoom.pageBar
            shown += bar
            if (bar > 0f && bar < 1f && !partway) {
                partway = true
                // The page is still on its way, smaller than the window; the bar isn't.
                assertTrue("Page ${page()}", width(page()) < 389f)
                assertBackPinned()
            }
        }
        assertTrue("Never partway: $shown", partway)
        // None until the page is 60 % of the way, then more and more of it, all of it once it lands.
        assertEquals(0f, shown.first(), 0f)
        assertEquals(1f, shown.last(), 0f)
        assertTrue("Not rising: $shown", shown.zipWithNext().all { (a, b) -> b >= a - 0.001f })
        compose.mainClock.autoAdvance = true
    }

    @Test
    fun theBarStaysWholeAtTheTopWhileThePageIsDraggedOrHeldByTheBackGesture() {
        show()
        open("Birthday gift")
        val root = compose.onRoot()
        root.performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 150f), delayMillis = 400)
        }
        compose.waitForIdle()
        assertTrue("Page ${page()}", width(page()) < 385f)
        assertEquals(1f, deliveries.zoom.pageBar, 0f)
        assertBackPinned()
        root.performTouchInput { up() }
        compose.mainClock.advanceTimeBy(600)

        gesture(at(0f), at(0.5f))
        assertTrue("Page ${page()}", width(page()) < 375f)
        assertEquals(1f, deliveries.zoom.pageBar, 0f)
        assertBackPinned()
    }

    @Test
    fun goingBackTheBarStaysUntilThePageIsNearItsCardThenFadesAndTalkBackNoLongerReachesIt() {
        show()
        open("Birthday gift")
        compose.mainClock.autoAdvance = false
        back()
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        assertEquals(1f, deliveries.zoom.pageBar, 0f)
        compose.onNodeWithTag(PAGE_BACK).assertDoesNotExist()
        var fading = false
        repeat(60) {
            compose.mainClock.advanceTimeByFrame()
            val bar = deliveries.zoom.pageBar
            if (bar > 0f && bar < 1f) fading = true
        }
        assertTrue(fading)
        compose.mainClock.autoAdvance = true
        compose.mainClock.advanceTimeBy(1_000)
        closed()
    }

    // The menu.

    @Test
    fun theMenuHoldsThePagesItemsWithoutOpenOrShare() {
        show()
        open("Birthday gift")
        openMenu()
        compose.onNodeWithTag(PAGE_MENU).assertExists()
        assertEquals(
            listOf(ParcelMenuItem.Rename, ParcelMenuItem.CopyTrackingNumber, ParcelMenuItem.ChangeCarrier, ParcelMenuItem.Archive, ParcelMenuItem.Delete),
            shownItems(),
        )
        // Its top right corner at the button's.
        val button = bounds(compose.onNodeWithTag(PAGE_MENU_BUTTON, useUnmergedTree = true))
        val first = bounds(item(ParcelMenuItem.Rename))
        assertTrue("Menu at $first under $button", first.right <= button.right && first.top >= button.top)
        back()
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag(PAGE_MENU).assertDoesNotExist()
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
    }

    @Test
    fun archiveArchivesTheParcelAndGoesBackToTheList() {
        show()
        open("Birthday gift")
        openMenu()
        choose(ParcelMenuItem.Archive)
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.archived)
        closed()
    }

    @Test
    fun anArchivedParcelsMenuRestoresIt() {
        show()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.onNode(hasContentDescription("Archived", substring = true) and hasClickAction()).performClick()
        compose.waitForIdle()
        open("Linen weekend bag")
        openMenu()
        assertTrue(ParcelMenuItem.Restore in shownItems())
        assertTrue(ParcelMenuItem.Archive !in shownItems())
        choose(ParcelMenuItem.Restore)
        assertEquals(listOf(DemoCards.parcel("Linen weekend bag").id), source.restored)
        closed()
    }

    @Test
    fun deleteAsksFirstThenDeletesAndGoesBackToTheList() {
        show()
        open("Birthday gift")
        openMenu()
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(DELETE_CONFIRMATION).assertExists()
        compose.onNodeWithTag(DELETE_CANCEL).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(emptyList<Any>(), source.deleted)
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()

        openMenu()
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(DELETE_CONFIRM).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.deleted)
        closed()
    }

    @Test
    fun copyPutsTheNumberOnTheClipboardAndThePageStays() {
        show()
        open("Birthday gift")
        openMenu()
        choose(ParcelMenuItem.CopyTrackingNumber)
        val entry = DemoCards.parcel("Birthday gift").trackingNumbers.first()
        val clipboard = compose.activity.getSystemService(ClipboardManager::class.java)
        assertEquals(TrackingNumberCopy.text(entry.number, entry.carrier), clipboard.primaryClip?.getItemAt(0)?.text?.toString())
        assertEquals(listOf("parcel-copy-tracking"), source.actions.filter { it == "parcel-copy-tracking" })
        compose.onNodeWithTag(PAGE_MENU).assertDoesNotExist()
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
    }

    @Test
    fun theOtherItemsDoNothingYet() {
        show()
        open("Birthday gift")
        for (other in listOf(ParcelMenuItem.Rename, ParcelMenuItem.ChangeCarrier)) {
            openMenu()
            choose(other)
            compose.onNodeWithTag(PAGE_MENU).assertDoesNotExist()
            compose.onNodeWithTag(PARCEL_PAGE).assertExists()
        }
        assertEquals(emptyList<Any>(), source.archived)
        assertEquals(emptyList<Any>(), source.deleted)
    }

    /** Beside the list the page has no bar of its own: its ••• joins the bar's glass, and the tabs stay. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun besideTheListTheMenuJoinsTheBarsGlass() {
        show()
        open("Birthday gift")
        compose.onNodeWithTag(PAGE_BACK).assertDoesNotExist()
        compose.onNodeWithTag(PAGE_BAR).assertDoesNotExist()
        val button = bounds(compose.onNodeWithTag(PAGE_MENU_BUTTON))
        val column = bounds(compose.onNodeWithTag(PAGE_COLUMN))
        assertTrue("Button $button in $column", button.left >= column.left && button.right <= column.right)
        compose.onNodeWithTag(TAB_BAR).assertExists()
        openMenu()
        assertEquals(
            listOf(ParcelMenuItem.Rename, ParcelMenuItem.CopyTrackingNumber, ParcelMenuItem.ChangeCarrier, ParcelMenuItem.Archive, ParcelMenuItem.Delete),
            shownItems(),
        )
        choose(ParcelMenuItem.Archive)
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.archived)
        // The next card's parcel takes its place.
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
        compose.onNode(hasText("Birthday gift", substring = true) and hasAnyAncestor(hasTestTag(PAGE_COLUMN))).assertDoesNotExist()
    }

    // TalkBack.

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

    private fun SemanticsNodeInteraction.hasTalkBackFocus(view: View): Boolean {
        val id = fetchSemanticsNode().id
        var focused = false
        compose.runOnUiThread { focused = view.accessibilityNodeProvider.createAccessibilityNodeInfo(id)?.isAccessibilityFocused == true }
        return focused
    }

    @Test
    fun talkBackLandsOnThePageThenOnTheMenusFirstItemAndBackOnItsButton() {
        // Out of the demo, whose bar the page would read first.
        show(AuthenticationState.Welcome)
        val view = talkBack()
        open("Birthday gift")
        val title = compose.onNode(hasText("Birthday gift", substring = true) and hasAnyAncestor(hasTestTag(PAGE_CONTENT)))
        assertTrue(title.hasTalkBackFocus(view))

        openMenu()
        assertTrue(item(ParcelMenuItem.Rename).hasTalkBackFocus(view))
        back()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertTrue(compose.onNodeWithTag(PAGE_MENU_BUTTON).hasTalkBackFocus(view))
    }
}
