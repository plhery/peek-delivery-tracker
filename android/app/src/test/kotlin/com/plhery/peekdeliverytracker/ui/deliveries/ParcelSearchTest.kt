package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.KeyEvent
import android.view.View
import android.view.accessibility.AccessibilityManager
import androidx.activity.BackEventCompat
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelSort
import com.plhery.peekdeliverytracker.core.ParcelStatusFilter
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import kotlin.math.abs

/**
 * The deliveries' search and filters (A-S27, A-S28, A-S98, B-S36): what a query, a status, a carrier and a sort show,
 * their chips and what clears them, the list with nothing to show, what is counted and when, the field's focus and the
 * keys, the filters' sheet at its heights and what dismisses it, their popover side by side, TalkBack, and what a
 * window made again or resized keeps.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelSearchTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var view: View
    private var width: Dp? by mutableStateOf(null)

    private fun show(reduceMotion: Boolean = false, recreation: Recreation? = null, width: Dp? = null) {
        this.width = width
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        val content = @androidx.compose.runtime.Composable {
            view = LocalView.current
            val shown = this.width
            if (shown == null) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = reduceMotion)
            } else {
                Box(Modifier.width(shown).fillMaxHeight()) {
                    RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = reduceMotion)
                }
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        compose.waitForIdle()
    }

    private fun openSearch() = tap(SEARCH_BUTTON)

    private fun type(text: String) {
        compose.onNodeWithTag(SEARCH_FIELD).performTextInput(text)
        compose.waitForIdle()
    }

    private fun openFilters() {
        if (compose.onAllNodes(hasTestTag(SEARCH_ROW)).fetchSemanticsNodes().isEmpty()) openSearch()
        tap(FILTERS_BUTTON)
    }

    private fun done() {
        compose.onNodeWithTag(FILTERS_DONE).performClick()
        compose.waitForIdle()
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    private fun inList(name: String) = compose.onNode(cardNamed(name) and hasAnyAncestor(hasTestTag(PARCEL_LIST)))

    private fun shows(name: String) = compose.onAllNodes(cardNamed(name) and hasAnyAncestor(hasTestTag(PARCEL_LIST))).fetchSemanticsNodes().isNotEmpty()

    /** Whether the node is there, though a sheet over it hides it from TalkBack. */
    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun focused(tag: String) =
        compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Focused) == true

    /** The sheet's top, in points from the window's. */
    private fun sheetTop(): Float = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot().top.value

    private fun windowHeight(): Float = compose.onRoot().getUnclippedBoundsInRoot().bottom.value

    private fun searches() = source.actions.filter { it == "search" }

    /** A key pressed and let go on a hardware keyboard, with `meta` held, through the window as the system sends it. */
    private fun press(key: Int, meta: Int = 0) {
        val window = view.rootView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, key, 0, meta))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, key, 0, meta))
        }
        compose.waitForIdle()
    }

    private val ctrl = KeyEvent.META_CTRL_ON or KeyEvent.META_CTRL_LEFT_ON

    @Test
    fun aQueryShowsWhatMatchesItWithItsChipAndNoNextUp() {
        show()
        assertTrue(shows("New trainers"))
        openSearch()
        type("gift")
        assertTrue(shows("Birthday gift"))
        assertFalse(shows("New trainers"))
        compose.onNodeWithTag("$FILTER_CHIPS.query").assertExists()
        compose.onNode(hasText("“gift”") and hasAnyAncestor(hasTestTag(FILTER_CHIPS))).assertExists()
        // No card is featured as next up in a view of one's own, and the count is of what it shows.
        compose.onNodeWithText("NEXT UP", substring = true, ignoreCase = true, useUnmergedTree = true).assertDoesNotExist()
        compose.onNode(hasText("1") and hasAnyAncestor(hasTestTag(PARCEL_LIST))).assertExists()
    }

    @Test
    fun theChipsClearOneThingEachAndClearAllClearsEverything() {
        show()
        openSearch()
        type("e")
        openFilters()
        tap("$FILTERS_STATUS.${ParcelStatusFilter.Active.rawValue}")
        tap("$FILTERS_SORT.${ParcelSort.Carrier.rawValue}")
        done()
        assertTrue(exists("$FILTER_CHIPS.status"))
        assertTrue(exists("$FILTER_CHIPS.sort"))
        assertFalse(exists("$FILTER_CHIPS.carrier"))

        tap("$FILTER_CHIPS.status")
        assertFalse(exists("$FILTER_CHIPS.status"))
        assertTrue(exists("$FILTER_CHIPS.query"))
        assertTrue(exists("$FILTER_CHIPS.sort"))

        tap(CLEAR_ALL)
        assertFalse(exists(FILTER_CHIPS))
        compose.onNodeWithTag(SEARCH_FIELD).assertTextEquals("")
        // The list as it was: next up is featured again.
        assertTrue(shows("New trainers"))
        compose.onNodeWithText("NEXT UP", substring = true, ignoreCase = true, useUnmergedTree = true).assertExists()
    }

    @Test
    fun nothingMatchingSaysSoAndItsClearShowsEverythingAgain() {
        show()
        openSearch()
        type("giftzz")
        assertTrue(exists(NO_RESULTS))
        compose.onNodeWithText("No matching parcels").assertExists()
        // With nothing on its way shown, the overview has no title or count.
        compose.onNodeWithText("On the way").assertDoesNotExist()
        tap(NO_RESULTS_CLEAR)
        assertFalse(exists(NO_RESULTS))
        compose.onNodeWithTag(SEARCH_FIELD).assertTextEquals("")
        assertTrue(shows("New trainers"))
    }

    @Test
    fun aCustomViewShowsTheArchivedItFindsAsAGroup() {
        show()
        openSearch()
        type("ramen")
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKeyIfAny(ARCHIVE_KEY)
        compose.onNodeWithText("Ceramic ramen bowls", substring = true, useUnmergedTree = true).assertExists()
        compose.onNode(hasText("Archived") and hasAnyAncestor(hasTestTag(PARCEL_LIST)), useUnmergedTree = true).assertExists()
    }

    @Test
    fun aSearchIsCountedOnceTheQueryHasRestedForEightHundredMilliseconds() {
        show()
        openSearch()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(SEARCH_FIELD).performTextInput("g")
        compose.mainClock.advanceTimeBy(500)
        compose.onNodeWithTag(SEARCH_FIELD).performTextInput("i")
        compose.mainClock.advanceTimeBy(700)
        assertEquals(emptyList<String>(), source.actions.filter { it == "search" })
        compose.mainClock.advanceTimeBy(200)
        assertEquals(listOf("search"), source.actions.filter { it == "search" })
        // Emptied, it isn't counted.
        compose.onNodeWithTag(SEARCH_FIELD).performTextClearance()
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(listOf("search"), source.actions.filter { it == "search" })
    }

    @Test
    fun theFiltersCountTheirOpeningAndEachChange() {
        show()
        openFilters()
        tap("$FILTERS_STATUS.${ParcelStatusFilter.Delivered.rawValue}")
        // Chosen again, the same status is no change.
        tap("$FILTERS_STATUS.${ParcelStatusFilter.Delivered.rawValue}")
        tap(FILTERS_CARRIER)
        tap("$FILTERS_CARRIER.dhl")
        tap("$FILTERS_SORT.${ParcelSort.Newest.rawValue}")
        assertEquals(
            listOf("filters-open", "filter-status", "filter-carrier", "sort-change"),
            source.actions.filter { it.startsWith("filter") || it == "sort-change" },
        )
        compose.onNodeWithTag("$FILTERS_STATUS.${ParcelStatusFilter.Delivered.rawValue}").assertIsSelected()
        // Clear filters puts the three back and leaves the query.
        tap(FILTERS_CLEAR)
        compose.onNodeWithTag("$FILTERS_STATUS.${ParcelStatusFilter.All.rawValue}").assertIsSelected()
        compose.onNodeWithTag("$FILTERS_SORT.${ParcelSort.Priority.rawValue}").assertIsSelected()
    }

    @Test
    fun theFieldTakesTheFocusAsItShowsAndGivesItUpToTheFilters() {
        show()
        openSearch()
        assertTrue(focused(SEARCH_FIELD))
        tap(FILTERS_BUTTON)
        assertTrue(exists(FILTERS_SHEET))
        assertFalse(focused(SEARCH_FIELD))
        done()
        assertFalse(exists(FILTERS_SHEET))
        // Hidden, the search keeps its query, and drops the focus.
        type("gift")
        tap(SEARCH_BUTTON)
        assertFalse(exists(SEARCH_ROW))
        tap(SEARCH_BUTTON)
        compose.onNodeWithTag(SEARCH_FIELD).assertTextEquals("gift")
    }

    @Test
    fun controlFOpensTheSearchAndEscapeClosesItButNotUnderTheFilters() {
        show()
        press(KeyEvent.KEYCODE_F, ctrl)
        assertTrue(exists(SEARCH_ROW))
        assertTrue(focused(SEARCH_FIELD))
        press(KeyEvent.KEYCODE_ESCAPE)
        assertFalse(exists(SEARCH_ROW))

        // Under the filters, Escape leaves the search as it is.
        press(KeyEvent.KEYCODE_F, ctrl)
        tap(FILTERS_BUTTON)
        press(KeyEvent.KEYCODE_ESCAPE)
        assertTrue(exists(SEARCH_ROW))
    }

    @Test
    fun theFieldKeepsItsFocusThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openSearch()
        assertTrue(focused(SEARCH_FIELD))
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertTrue(focused(SEARCH_FIELD))

        // Unfocused, it stays so.
        compose.onNodeWithTag(SEARCH_FIELD).performImeAction()
        compose.waitForIdle()
        assertFalse(focused(SEARCH_FIELD))
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertTrue(exists(SEARCH_ROW))
        assertFalse(focused(SEARCH_FIELD))
    }

    @Test
    fun aQueryTypedJustBeforeTheWindowIsMadeAgainIsCountedOnce() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openSearch()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(SEARCH_FIELD).performTextInput("gift")
        compose.mainClock.advanceTimeBy(300)
        recreation.recreate()
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(listOf("search"), searches())

        // Neither the window made again once more nor a parcel opened and closed counts it again.
        recreation.recreate()
        compose.mainClock.advanceTimeBy(1_000)
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertEquals(listOf("search"), searches())
        inList("Birthday gift").performClick()
        compose.waitForIdle()
        back()
        assertTrue(shows("Birthday gift"))
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(listOf("search"), searches())
    }

    @Test
    fun theSheetComesUpToItsMediumHeightAndADragTakesItBetweenItsHeights() {
        show()
        openFilters()
        val height = windowHeight()
        assertEquals(height * 0.4714f, sheetTop(), 2f)
        compose.onNodeWithTag(SHEET_PANEL).performTouchInput { swipeUp(top + 30f, top - 500f, durationMillis = 300) }
        compose.waitForIdle()
        val large = sheetTop()
        assertTrue("Large at $large", large < height * 0.1f)
        // Let go slowly, it settles at the medium height; flicked, it would go.
        compose.onNodeWithTag(SHEET_PANEL).performTouchInput { swipeDown(top + 30f, top + 330f * density, durationMillis = 1_500) }
        compose.waitForIdle()
        assertEquals(height * 0.4714f, sheetTop(), 2f)
        // Pulled down from its lowest height, it goes.
        compose.onNodeWithTag(SHEET_PANEL).performTouchInput { swipeDown(top + 30f, top + 300f * density, durationMillis = 300) }
        compose.waitForIdle()
        assertFalse(exists(SHEET_PANEL))
        assertFalse(exists(FILTERS_SHEET))
    }

    @Test
    fun aScrollUpInTheMediumSheetFirstTakesItToItsLargeHeight() {
        show()
        openFilters()
        val medium = sheetTop()
        compose.onNodeWithTag(FILTERS_SHEET).performTouchInput { swipeUp(centerY, centerY - 200f * density, durationMillis = 300) }
        compose.waitForIdle()
        assertTrue("Still at ${sheetTop()} from $medium", sheetTop() < medium - 100f)
    }

    @Test
    fun backOrATapOnTheDimDismissesTheSheet() {
        show()
        openFilters()
        back()
        assertFalse(exists(SHEET_PANEL))
        assertTrue(exists(SEARCH_ROW))

        openFilters()
        compose.onRoot().performTouchInput { click(androidx.compose.ui.geometry.Offset(centerX, 40f * density)) }
        compose.waitForIdle()
        assertFalse(exists(SHEET_PANEL))
    }

    @Test
    fun thePredictiveBackGestureTakesTheSheetDownWithItAndLetGoDismissesIt() {
        show()
        openFilters()
        val medium = sheetTop()
        val back = compose.activity.onBackPressedDispatcher
        compose.runOnUiThread {
            back.dispatchOnBackStarted(BackEventCompat(0f, 400f, 0f, BackEventCompat.EDGE_LEFT))
            back.dispatchOnBackProgressed(BackEventCompat(240f, 400f, 1f, BackEventCompat.EDGE_LEFT))
        }
        compose.waitForIdle()
        assertTrue("At ${sheetTop()} from $medium", sheetTop() > medium + 30f)
        // Called off, it goes back up.
        compose.runOnUiThread { back.dispatchOnBackCancelled() }
        compose.waitForIdle()
        assertEquals(medium, sheetTop(), 1f)

        compose.runOnUiThread {
            back.dispatchOnBackStarted(BackEventCompat(0f, 400f, 0f, BackEventCompat.EDGE_LEFT))
            back.dispatchOnBackProgressed(BackEventCompat(120f, 400f, 0.5f, BackEventCompat.EDGE_LEFT))
            back.onBackPressed()
        }
        compose.waitForIdle()
        assertFalse(exists(SHEET_PANEL))
        assertTrue(exists(SEARCH_ROW))
    }

    @Test
    fun theSheetIsModalForTalkBackFromItsTitleAndHandsTheFocusBackToTheFiltersButton() {
        show()
        val talkBack = talkBack()
        openFilters()
        compose.onNodeWithTag(PARCEL_LIST).assertDoesNotExist()
        compose.onNodeWithTag(FILTERS_BUTTON).assertDoesNotExist()
        val title = compose.onNode(hasText("Search & filters") and isHeading())
        assertTrue(title.hasTalkBackFocus(talkBack))
        compose.onNodeWithTag("$FILTERS_STATUS.${ParcelStatusFilter.All.rawValue}").assertIsSelected()
        done()
        compose.onNodeWithTag(PARCEL_LIST).assertExists()
        assertTrue(compose.onNodeWithTag(FILTERS_BUTTON).hasTalkBackFocus(talkBack))
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun sideBySideTheFiltersArePopoverOffTheirButton() {
        show()
        openFilters()
        assertTrue(exists(FILTERS_POPOVER))
        assertFalse(exists(SHEET_PANEL))
        val button = compose.onNodeWithTag(FILTERS_BUTTON, useUnmergedTree = true)
        // A tap outside dismisses it, as back does.
        compose.onRoot().performTouchInput { click(androidx.compose.ui.geometry.Offset(width - 40f * density, height - 40f * density)) }
        compose.waitForIdle()
        assertFalse(exists(FILTERS_POPOVER))
        button.assertExists()
        openFilters()
        back()
        assertFalse(exists(FILTERS_POPOVER))
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aPopoverStaysAPopoverAsTheWindowNarrowsAndTheNextOpeningIsASheet() {
        show(width = 1032.dp)
        openFilters()
        assertTrue(exists(FILTERS_POPOVER))
        width = 700.dp
        compose.waitForIdle()
        // Side by side no longer; the popover is now a sheet only once it has closed and opens again.
        assertTrue(exists(FILTERS_POPOVER))
        done()
        width = 500.dp
        compose.waitForIdle()
        openFilters()
        assertTrue(exists(FILTERS_SHEET))
        assertFalse(exists(FILTERS_POPOVER))
    }

    @Test
    fun theQueryAndTheFiltersOutliveTheWindowMadeAgainButNotANewLaunch() {
        val recreation = Recreation(compose)
        var launch by mutableStateOf(FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo))
        recreation.setContent { RootHost(state = AuthenticationState.Demo, source = launch) }
        compose.waitForIdle()
        openSearch()
        type("gift")
        openFilters()
        tap("$FILTERS_SORT.${ParcelSort.Newest.rawValue}")
        done()
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        compose.onNodeWithTag(SEARCH_FIELD).assertTextEquals("gift")
        assertTrue(exists("$FILTER_CHIPS.sort"))

        // A new launch's window starts again from the list as it is.
        launch = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertFalse(exists(SEARCH_ROW))
        assertFalse(exists(FILTER_CHIPS))
    }

    @Test
    fun underReduceMotionTheSheetIsInPlaceAtOnce() {
        show(reduceMotion = true)
        openFilters()
        assertEquals(windowHeight() * 0.4714f, sheetTop(), 2f)
    }

    @Test
    fun underReduceMotionTheSearchIsInPlaceAtOnce() {
        show(reduceMotion = true)
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        val first = rowTop()
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(rowTop(), first, 0.5f)
    }

    @Test
    fun theSearchComesDownIntoPlace() {
        show()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        val first = rowTop()
        compose.mainClock.advanceTimeBy(1_000)
        assertTrue("From $first to ${rowTop()}", rowTop() - first > 3f)
    }

    private fun rowTop(): Float = compose.onNodeWithTag(SEARCH_ROW).getUnclippedBoundsInRoot().top.value

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

    /** Whether TalkBack's focus is on this node. */
    private fun SemanticsNodeInteraction.hasTalkBackFocus(view: View): Boolean {
        val id = fetchSemanticsNode().id
        var focused = false
        compose.runOnUiThread { focused = view.accessibilityNodeProvider.createAccessibilityNodeInfo(id)?.isAccessibilityFocused == true }
        return focused
    }

    private fun SemanticsNodeInteraction.performScrollToKeyIfAny(key: Any) {
        runCatching { performScrollToKey(key) }
        compose.waitForIdle()
    }

    private fun assertEquals(expected: Float, actual: Float, tolerance: Float) {
        assertTrue("Expected $expected, was $actual", abs(expected - actual) <= tolerance)
    }
}
