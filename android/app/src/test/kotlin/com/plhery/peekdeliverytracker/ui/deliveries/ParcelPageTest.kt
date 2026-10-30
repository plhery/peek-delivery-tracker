package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.ClipboardManager
import android.view.KeyEvent
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
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
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
import com.plhery.peekdeliverytracker.core.FirstOpenJourney
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.TrackingNumberCopy
import com.plhery.peekdeliverytracker.core.trackingNumbers
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import com.plhery.peekdeliverytracker.ui.zoom.ZoomBar
import com.plhery.peekdeliverytracker.ui.zoom.ZoomGeometry
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
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
 * A parcel's page's frame (B-S01): the paper in one column or two, the page for a parcel gone (B-ST01), and its bar,
 * ‹ and the ••• menu (B-S09), which stays at the window's top as the page zooms, is dragged or is held by the back
 * gesture under it, and comes in as the page lands and goes, ‹ first, as it goes back into its card, the list's bar
 * items fading and coming back around it.
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

    /** A key pressed and let go on a hardware keyboard, with `meta` held, through the window as the system sends it. */
    private fun press(key: Int, meta: Int = 0) {
        val window = compose.activity.window.decorView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, key, 0, meta))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, key, 0, meta))
        }
        compose.waitForIdle()
    }

    private val ctrl = KeyEvent.META_CTRL_ON or KeyEvent.META_CTRL_LEFT_ON

    private val gift = DemoCards.parcel("Birthday gift").id

    private fun clipboard(): String? = compose.activity.getSystemService(ClipboardManager::class.java).primaryClip?.getItemAt(0)?.text?.toString()

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
    fun theBarStaysAtTheTopAsThePageZoomsAndComesInAsItLandsAsTheListsItemsFade() {
        show()
        compose.mainClock.autoAdvance = false
        compose.card("Birthday gift").performClick()
        val frames = mutableListOf<Pair<Float, ZoomBar>>()
        var partway = false
        repeat(60) {
            compose.mainClock.advanceTimeByFrame()
            val bar = deliveries.zoom.bar
            frames += deliveries.zoom.behind to bar
            if (bar.leading > 0f && bar.leading < 1f && !partway) {
                partway = true
                // The page is still on its way, smaller than the window; the bar isn't, and TalkBack doesn't reach it yet.
                assertTrue("Page ${page()}", width(page()) < 389f)
                assertBackPinned()
                compose.onNodeWithTag(PAGE_BACK).assertDoesNotExist()
            }
        }
        assertTrue("Never partway: $frames", partway)
        // ‹ and ••• together: none until the page is 82 % of the way, all of them from 97 %. The list's items and its
        // title fade with the page.
        for ((progress, bar) in frames) {
            assertEquals("At $progress", bar.leading, bar.trailing, 0f)
            if (progress < ZoomGeometry.BAR_OPENING_START) assertEquals("At $progress", 0f, bar.leading, 0f)
            if (progress > ZoomGeometry.BAR_OPENING_END + 0.0001f) assertEquals("At $progress", 1f, bar.leading, 0f)
            assertEquals("At $progress", 1f - progress, bar.root, 0.001f)
            assertEquals("At $progress", 1f - progress, bar.title, 0.001f)
        }
        assertEquals(ZoomBar(leading = 1f, trailing = 1f, root = 0f, title = 0f), frames.last().second)
        compose.mainClock.autoAdvance = true
        compose.onNodeWithTag(PAGE_BACK).assertExists()
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
        assertEquals(ZoomBar(leading = 1f, trailing = 1f, root = 0f, title = 0f), deliveries.zoom.bar)
        assertBackPinned()
        root.performTouchInput { up() }
        compose.mainClock.advanceTimeBy(600)

        gesture(at(0f), at(0.5f))
        assertTrue("Page ${page()}", width(page()) < 375f)
        assertEquals(ZoomBar(leading = 1f, trailing = 1f, root = 0f, title = 0f), deliveries.zoom.bar)
        assertBackPinned()
    }

    /** As a drag shrinks the page below 83 % of the window, the list's title comes back over it; its other items don't. */
    @Test
    fun aDragShrinkingThePageFarBringsTheListsTitleBack() {
        show()
        open("Birthday gift")
        compose.onRoot().performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 750f), delayMillis = 400)
        }
        compose.waitForIdle()
        val scale = width(page()) / 390f
        val bar = deliveries.zoom.bar
        assertTrue("Scale $scale", scale < 0.8f)
        assertTrue("Title ${bar.title}", bar.title > 0f)
        assertEquals(ZoomGeometry.titleUncovered(scale), bar.title, 0.01f)
        assertEquals(0f, bar.root, 0f)
        assertEquals(1f, bar.leading, 0f)
        assertBackPinned()
    }

    @Test
    fun goingBackBackGoesAtOnceThenTheMenuThenTheListsItemsComeBackAndTalkBackNoLongerReachesThem() {
        show()
        open("Birthday gift")
        compose.mainClock.autoAdvance = false
        back()
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        // ‹ goes as the page sets off, ••• stays a moment, and the list's title comes back with the page.
        var bar = deliveries.zoom.bar
        assertEquals(0f, bar.leading, 0f)
        assertEquals(1f, bar.trailing, 0f)
        assertEquals(0f, bar.root, 0f)
        assertEquals(1f - deliveries.zoom.behind, bar.title, 0.001f)
        compose.onNodeWithTag(PAGE_BACK).assertDoesNotExist()
        compose.onNodeWithTag(PAGE_MENU_BUTTON).assertDoesNotExist()
        // ••• has gone 70 ms on, and the list's items are coming back.
        compose.mainClock.advanceTimeBy(80)
        bar = deliveries.zoom.bar
        assertEquals(0f, bar.trailing, 0f)
        assertTrue("Root ${bar.root}", bar.root > 0f && bar.root < 0.3f)
        assertEquals(1f - deliveries.zoom.behind, bar.title, 0.001f)
        // All of them 0.17 s later.
        compose.mainClock.advanceTimeBy(200)
        assertEquals(1f, deliveries.zoom.bar.root, 0f)
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
        // On a phone, an action sheet at the bottom.
        compose.onNodeWithTag(DELETE_POPOVER).assertDoesNotExist()
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

    @Test
    fun whileAChangeIsSavedDeleteWaits() {
        show()
        source.archiving = CompletableDeferred()
        open("Birthday gift")
        openMenu()
        choose(ParcelMenuItem.Archive)
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
        openMenu()
        item(ParcelMenuItem.Delete).assertIsNotEnabled()
        item(ParcelMenuItem.Rename).assertIsEnabled()
        back()
        compose.mainClock.advanceTimeBy(1_000)
        // Nor does its key ask.
        press(KeyEvent.KEYCODE_DEL, ctrl)
        compose.onNodeWithTag(DELETE_CONFIRMATION).assertDoesNotExist()
        compose.runOnUiThread { source.archiving?.complete(Unit) }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertEquals(listOf(gift), source.archived)
        closed()
    }

    @Test
    fun aFailedArchiveKeepsThePageAndSaysWhyInAnAlert() {
        show()
        source.failure = IllegalStateException("No connection")
        open("Birthday gift")
        openMenu()
        choose(ParcelMenuItem.Archive)
        compose.onNodeWithTag(ACTION_ERROR).assertExists()
        compose.onNode(hasText("No connection")).assertExists()
        compose.onNodeWithTag(ACTION_ERROR_CLOSE).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag(ACTION_ERROR).assertDoesNotExist()
        assertEquals(emptyList<Any>(), source.archived)
        assertEquals(listOf(gift), deliveries.navigator.navigation.path)
        compose.onNodeWithTag(PAGE_CONTENT).assertExists()
        // It can be tried again.
        source.failure = null
        openMenu()
        choose(ParcelMenuItem.Archive)
        assertEquals(listOf(gift), source.archived)
        closed()
    }

    /** The menu and its delete question show the parcel as it is now, archived elsewhere, and go with it, deleted elsewhere. */
    @Test
    fun theMenuFollowsItsParcelChangedElsewhereAndGoesWithIt() {
        show()
        open("Birthday gift")
        openMenu()
        assertTrue(ParcelMenuItem.Archive in shownItems())
        source.parcels.value = source.parcels.value.map { if (it.id == gift) it.copy(archivedAt = "2026-05-01T09:00:00Z") else it }
        compose.waitForIdle()
        assertTrue(ParcelMenuItem.Restore in shownItems())
        assertTrue(ParcelMenuItem.Archive !in shownItems())
        choose(ParcelMenuItem.Restore)
        assertEquals(listOf(gift), source.restored)
        closed()

        open("Birthday gift")
        openMenu()
        val parcels = source.parcels.value
        source.parcels.value = parcels.filterNot { it.id == gift }
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag(PAGE_MENU).assertDoesNotExist()
        assertNull(deliveries.pageMenu)
        compose.onNodeWithTag(PARCEL_MISSING).assertExists()

        source.parcels.value = parcels
        compose.waitForIdle()
        openMenu()
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(DELETE_CONFIRMATION).assertExists()
        source.parcels.value = parcels.filterNot { it.id == gift }
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag(DELETE_CONFIRMATION).assertDoesNotExist()
        assertNull(deliveries.deleting)
        assertEquals(emptyList<Any>(), source.deleted)
    }

    /** Mail's keys press the menu's items, as its menu would; Escape goes back. */
    @Test
    fun theMenusKeysActOnThePagesParcelAndEscapeGoesBack() {
        show()
        open("Birthday gift")
        press(KeyEvent.KEYCODE_C, ctrl or KeyEvent.META_SHIFT_ON)
        val entry = DemoCards.parcel("Birthday gift").trackingNumbers.first()
        assertEquals(TrackingNumberCopy.text(entry.number, entry.carrier), clipboard())
        press(KeyEvent.KEYCODE_DEL, ctrl)
        compose.onNodeWithTag(DELETE_CONFIRMATION).assertExists()
        compose.onNodeWithTag(DELETE_CANCEL).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        press(KeyEvent.KEYCODE_ESCAPE)
        compose.mainClock.advanceTimeBy(1_000)
        closed()

        open("Birthday gift")
        press(KeyEvent.KEYCODE_A, ctrl or KeyEvent.META_META_ON)
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(listOf(gift), source.archived)
        closed()
    }

    /** Once the page is on its way back, the keys are the list's again: Escape closes the search under it. */
    @Test
    fun escapeOnAPageGoingBackReachesTheList() {
        show()
        press(KeyEvent.KEYCODE_F, ctrl)
        // Return ends the typing, and leaves the search open.
        press(KeyEvent.KEYCODE_ENTER)
        assertTrue(deliveries.showingSearch)
        assertFalse(deliveries.searchFocused)
        open("Birthday gift")
        compose.mainClock.autoAdvance = false
        press(KeyEvent.KEYCODE_ESCAPE)
        compose.mainClock.advanceTimeByFrame()
        assertTrue(deliveries.navigator.navigation.path.isEmpty())
        assertEquals(gift, deliveries.zoom.covered)
        assertTrue(deliveries.showingSearch)
        press(KeyEvent.KEYCODE_ESCAPE)
        assertFalse(deliveries.showingSearch)
        compose.mainClock.autoAdvance = true
        compose.mainClock.advanceTimeBy(1_000)
        closed()
    }

    /** In a regular window the page's delete question stands off its •••, and the next card's parcel takes its place. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun besideTheListDeleteAsksOffTheMenusButtonAndTheNextCardTakesThePagesPlace() {
        show()
        open("Birthday gift")
        openMenu()
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(DELETE_CONFIRMATION).assertDoesNotExist()
        val button = bounds(compose.onNodeWithTag(PAGE_MENU_BUTTON, useUnmergedTree = true))
        val popover = bounds(compose.onNodeWithTag(DELETE_POPOVER))
        assertTrue("Popover $popover under $button", popover.top >= button.bottom)
        compose.onNodeWithTag(DELETE_CONFIRM).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertEquals(listOf(gift), source.deleted)
        compose.onNodeWithTag(DELETE_POPOVER).assertDoesNotExist()
        compose.onNode(hasText("Belgian chocolate", substring = true) and hasAnyAncestor(hasTestTag(PAGE_COLUMN))).assertExists()
    }

    /** Beside the list the sample stays as the deliveries change, none of which is it. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun besideTheListTheSampleStaysAsTheDeliveriesChange() {
        show()
        compose.runOnUiThread { deliveries.openSample() }
        compose.waitForIdle()
        source.parcels.value = source.parcels.value.drop(1)
        compose.waitForIdle()
        assertEquals(FirstOpenJourney.parcelId, deliveries.navigator.navigation.shown)
        compose.onNodeWithTag(PAGE_CONTENT).assertExists()
        compose.onNodeWithTag(PARCEL_MISSING).assertDoesNotExist()
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
        compose.onNode(hasText("Belgian chocolate", substring = true) and hasAnyAncestor(hasTestTag(PAGE_COLUMN))).assertExists()
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
        // The pass's first element: its carrier, which a tap changes.
        assertTrue(compose.onNodeWithTag(PAGE_MARKS).hasTalkBackFocus(view))

        openMenu()
        assertTrue(item(ParcelMenuItem.Rename).hasTalkBackFocus(view))
        back()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertTrue(compose.onNodeWithTag(PAGE_MENU_BUTTON).hasTalkBackFocus(view))
    }
}
