package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.KeyEvent
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.view.inputmethod.EditorInfo
import androidx.activity.BackEventCompat
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
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
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelSort
import com.plhery.peekdeliverytracker.core.ParcelStatusFilter
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.awaitFrame
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
 * the filters' button beside the search's and its dot, the search's button's mark, their chips and what clears them,
 * the list with nothing to show, what is counted and when, the field's focus and the keys, the filters' sheet at its
 * heights and what dismisses it, the keyboard going as it comes up, their popover side by side and in short windows,
 * TalkBack and their menu, and what a window made again or resized keeps.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelSearchTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState
    private var width: Dp? by mutableStateOf(null)
    private var windowWidth: Int? by mutableStateOf(null)

    private fun show(reduceMotion: Boolean = false, recreation: Recreation? = null, width: Dp? = null) {
        this.width = width
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        val content = @androidx.compose.runtime.Composable {
            view = LocalView.current
            val shown = this.width
            val window = LocalWindowInfo.current
            val resized = remember(window) { ResizedWindow(window) { windowWidth } }
            CompositionLocalProvider(LocalWindowInfo provides resized, LocalDeliveriesState provides { deliveries = it }) {
                if (shown == null) {
                    RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = reduceMotion)
                } else {
                    Box(Modifier.width(shown).fillMaxHeight()) {
                        RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = reduceMotion)
                    }
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

    private fun openFilters() = tap(FILTERS_BUTTON)

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
        assertTrue(exists(SEARCH_ROW))
        done()
        assertFalse(exists(FILTERS_SHEET))
        // Hidden, the search keeps its query, and drops the focus.
        type("gift")
        tap(SEARCH_BUTTON)
        assertFalse(exists(SEARCH_ROW))
        tap(SEARCH_BUTTON)
        compose.onNodeWithTag(SEARCH_FIELD).assertTextEquals("gift")
    }

    /**
     * `listOverview`: sort & filter stands 8 before the search's button, 44 across, rather than in the search's row,
     * whose capsule takes the whole row; it opens the filters with the search closed, which stays so.
     */
    @Test
    fun theFiltersButtonBesideTheSearchOpensThemWithTheSearchClosed() {
        show()
        assertFalse(exists(SEARCH_ROW))
        val button = compose.onNodeWithTag(FILTERS_BUTTON)
        button.assertContentDescriptionEquals("Sort & filter")
        button.assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
        val filters = button.getUnclippedBoundsInRoot()
        val search = compose.onNodeWithTag(SEARCH_BUTTON).getUnclippedBoundsInRoot()
        assertEquals(search.top.value, filters.top.value, 0.5f)
        assertEquals(search.left.value - 8f, filters.right.value, 0.5f)
        assertEquals(44f, (filters.right - filters.left).value, 0.5f)
        tap(FILTERS_BUTTON)
        assertTrue(exists(FILTERS_SHEET))
        done()
        assertFalse(exists(SEARCH_ROW))

        openSearch()
        compose.onNode(hasTestTag(FILTERS_BUTTON) and hasAnyAncestor(hasTestTag(SEARCH_ROW))).assertDoesNotExist()
        assertEquals(search.right.value, compose.onNodeWithTag(SEARCH_ROW).getUnclippedBoundsInRoot().right.value, 0.5f)
    }

    /**
     * The filters' dot and "Custom view" (`filtered`): for a status, a carrier and an order other than the usual one,
     * each on its own, and not for a query alone.
     */
    @Test
    fun theDotSaysTheViewIsCustomForAStatusACarrierOrASortButNotForAQuery() {
        show()
        assertFiltered(false)
        openSearch()
        type("gift")
        assertFiltered(false)
        compose.onNodeWithTag(SEARCH_FIELD).performTextClearance()
        compose.waitForIdle()

        openFilters()
        tap("$FILTERS_STATUS.${ParcelStatusFilter.Active.rawValue}")
        done()
        assertFiltered(true)
        tap("$FILTER_CHIPS.status")
        assertFiltered(false)

        openFilters()
        tap(FILTERS_CARRIER)
        tap("$FILTERS_CARRIER.dhl")
        done()
        assertFiltered(true)
        tap("$FILTER_CHIPS.carrier")
        assertFiltered(false)

        openFilters()
        tap("$FILTERS_SORT.${ParcelSort.Newest.rawValue}")
        done()
        assertFiltered(true)
        tap("$FILTER_CHIPS.sort")
        assertFiltered(false)
    }

    /** Whether the filters' button has its dot and says "Custom view" to TalkBack; it has both or neither. */
    private fun assertFiltered(filtered: Boolean) {
        assertEquals(filtered, exists(FILTERS_DOT))
        val value = compose.onNodeWithTag(FILTERS_BUTTON).fetchSemanticsNode().config.getOrNull(SemanticsProperties.StateDescription)
        assertEquals(if (filtered) "Custom view" else null, value)
    }

    /** The dot grows and fades in on a snappy 0.2 s, and out again. */
    @Test
    fun theDotGrowsInAndShrinksAway() {
        show()
        val full = 6 * view.resources.displayMetrics.density
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { deliveries.sort = ParcelSort.Newest; Snapshot.sendApplyNotifications() }
        repeat(2) { compose.mainClock.advanceTimeByFrame() }
        compose.mainClock.advanceTimeBy(48)
        assertTrue("At ${dotWidth()} of $full", dotWidth() in 0.5f..full - 0.5f)
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(full, dotWidth(), 0.5f)

        compose.runOnUiThread { deliveries.sort = ParcelSort.Priority; Snapshot.sendApplyNotifications() }
        repeat(2) { compose.mainClock.advanceTimeByFrame() }
        compose.mainClock.advanceTimeBy(48)
        assertTrue("At ${dotWidth()} of $full", dotWidth() in 0.5f..full - 0.5f)
        compose.mainClock.advanceTimeBy(1_000)
        assertFalse(exists(FILTERS_DOT))
    }

    /** Under Reduce Motion, the dot is there at once, and gone at once. */
    @Test
    fun underReduceMotionTheDotComesAndGoesAtOnce() {
        show(reduceMotion = true)
        val full = 6 * view.resources.displayMetrics.density
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { deliveries.carrierFilter = CarrierId("dhl"); Snapshot.sendApplyNotifications() }
        repeat(2) { compose.mainClock.advanceTimeByFrame() }
        assertEquals(full, dotWidth(), 0.5f)
        compose.runOnUiThread { deliveries.carrierFilter = null; Snapshot.sendApplyNotifications() }
        repeat(2) { compose.mainClock.advanceTimeByFrame() }
        assertFalse(exists(FILTERS_DOT))
    }

    /** The dot's width in the window's pixels, as drawn, its scale included. */
    private fun dotWidth(): Float = compose.onNodeWithTag(FILTERS_DOT, useUnmergedTree = true).fetchSemanticsNode().boundsInWindow.width

    /**
     * The search's button is marked while the search shows or holds a search, not for the filters, and says whether
     * it shows the search or hides it.
     */
    @Test
    fun theSearchButtonIsMarkedForASearchAloneAndSaysWhatItDoes() {
        show()
        assertSearchButton(marked = false, label = "Search parcels")
        openSearch()
        assertSearchButton(marked = true, label = "Hide search")
        type("gift")
        tap(SEARCH_BUTTON)
        assertSearchButton(marked = true, label = "Search parcels")
        // Blank, the field holds no search.
        openSearch()
        compose.onNodeWithTag(SEARCH_FIELD).performTextClearance()
        type("  ")
        tap(SEARCH_BUTTON)
        assertSearchButton(marked = false, label = "Search parcels")

        openFilters()
        tap("$FILTERS_SORT.${ParcelSort.Newest.rawValue}")
        done()
        assertTrue(exists(FILTER_CHIPS))
        assertSearchButton(marked = false, label = "Search parcels")
    }

    /**
     * The search's button says `label`, and has its faint circle or not: a point inside the circle, clear of the glass,
     * unlike the button's corner, outside it.
     */
    private fun assertSearchButton(marked: Boolean, label: String) {
        val button = compose.onNodeWithTag(SEARCH_BUTTON)
        button.assertContentDescriptionEquals(label)
        compose.awaitFrame()
        val image = button.captureToImage().asAndroidBitmap()
        val inside = image.getPixel(image.width / 2, (4 * view.resources.displayMetrics.density).toInt())
        assertEquals("Marked", marked, inside != image.getPixel(1, 1))
    }

    @Test
    fun controlFOpensTheSearchAndEscapeClosesItButNotUnderTheFilters() {
        show()
        press(KeyEvent.KEYCODE_F, ctrl)
        assertTrue(exists(SEARCH_ROW))
        assertTrue(focused(SEARCH_FIELD))
        press(KeyEvent.KEYCODE_ESCAPE)
        assertFalse(exists(SEARCH_ROW))

        // Under the filters, Escape leaves the search and the filters as they are; a phone turns the key nothing took
        // into back, which closes the filters alone.
        press(KeyEvent.KEYCODE_F, ctrl)
        tap(FILTERS_BUTTON)
        press(KeyEvent.KEYCODE_ESCAPE)
        assertTrue(exists(SHEET_PANEL))
        assertTrue(exists(SEARCH_ROW))
        back()
        assertFalse(exists(SHEET_PANEL))
        assertTrue(exists(SEARCH_ROW))
    }

    /**
     * Android's back at the list's root closes the search, as the toggle and Esc do, keeping what it holds; then there
     * is nothing to go back to, and the system leaves the app. Over a parcel's page, the page takes the back first.
     */
    @Test
    fun backClosesTheSearchKeepingItsQueryAndThenLeavesTheApp() {
        show()
        val dispatcher = compose.activity.onBackPressedDispatcher
        assertFalse(dispatcher.hasEnabledCallbacks())
        openSearch()
        type("gift")
        inList("Birthday gift").performClick()
        compose.waitForIdle()
        back()
        assertFalse(exists(PARCEL_PAGE))
        assertTrue(exists(SEARCH_ROW))

        assertTrue(dispatcher.hasEnabledCallbacks())
        back()
        assertFalse(exists(SEARCH_ROW))
        compose.onNodeWithTag(PARCEL_LIST).assertExists()
        assertFalse(dispatcher.hasEnabledCallbacks())
        tap(SEARCH_BUTTON)
        compose.onNodeWithTag(SEARCH_FIELD).assertTextEquals("gift")
    }

    /** Beside a parcel, which takes no back, the list is at its root: back closes the search there too. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun besideAParcelBackClosesTheSearch() {
        show()
        val dispatcher = compose.activity.onBackPressedDispatcher
        openSearch()
        inList("Birthday gift").performClick()
        compose.waitForIdle()
        assertTrue(exists(PARCEL_PAGE))
        back()
        assertFalse(exists(SEARCH_ROW))
        assertTrue(exists(PARCEL_PAGE))
        assertFalse(dispatcher.hasEnabledCallbacks())
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

    /**
     * `.textInputAutocapitalization(.never)`, `.autocorrectionDisabled()`: the field asks the keyboard for no capitals,
     * corrections or suggestions, with Search as its action, and asks the same of the keyboard it keeps through a
     * window made again.
     */
    @Test
    fun theFieldAsksTheKeyboardForNoCapitalsCorrectionsOrSuggestions() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openSearch()
        assertTextWithoutSuggestions(compose.editorInfo(), EditorInfo.IME_ACTION_SEARCH)
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertTrue(focused(SEARCH_FIELD))
        assertTextWithoutSuggestions(compose.editorInfo(), EditorInfo.IME_ACTION_SEARCH)
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

    /**
     * X-AD24: the list keeps clear of the keyboard, as the iPhone's keeps to the keyboard's safe area: typing a search,
     * the list scrolls to its end, past its last result, above the keyboard, which is taller than the tab bar; once the
     * keyboard has gone, to its end over the tab bar again.
     */
    @Test
    fun withTheKeyboardUpTheListScrollsToItsEndAboveIt() {
        show()
        openSearch()
        type("e")
        assertTrue(focused(SEARCH_FIELD))
        val height = windowHeight()
        keyboard(336)
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        val foot = compose.onNodeWithTag(FOOT).getUnclippedBoundsInRoot().bottom.value
        assertTrue("The list's end at $foot, the keyboard's top at ${height - 336}", foot <= height - 336)

        keyboard(0)
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        val over = compose.onNodeWithTag(FOOT).getUnclippedBoundsInRoot().bottom.value
        assertTrue("The list's end at $over, under where the keyboard's top was", over > height - 336)
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
    fun theFiltersOpenedFromTheTypingFieldComeUpAtTheirMediumHeightAsTheKeyboardGoes() {
        show()
        openSearch()
        val medium = windowHeight() * 0.4714f
        // However long the keyboard takes to go down as the sheet comes up, it was the field's, not the sheet's.
        for (gone in listOf(0L, 150L, 250L, 350L, 1_000L)) {
            compose.onNodeWithTag(SEARCH_FIELD).performClick()
            keyboard(336)
            compose.waitForIdle()
            compose.mainClock.autoAdvance = false
            compose.onNodeWithTag(FILTERS_BUTTON).performClick()
            compose.mainClock.advanceTimeBy(gone)
            keyboard(0)
            compose.mainClock.advanceTimeBy(2_000)
            compose.mainClock.autoAdvance = true
            compose.waitForIdle()
            assertTrue("Gone at $gone ms, at ${sheetTop()} for $medium", abs(sheetTop() - medium) <= 2f)
            done()
        }
    }

    @Test
    fun theSheetMadeAgainWithTheWindowIsAtItsHeightWithoutComingUpAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openFilters()
        compose.onNodeWithTag(SHEET_PANEL).performTouchInput { swipeUp(top + 30f, top - 500f, durationMillis = 300) }
        compose.waitForIdle()
        val large = sheetTop()
        assertTrue("Large at $large", large < windowHeight() * 0.1f)
        recreation.recreate()
        compose.mainClock.advanceTimeByFrame()
        assertEquals(large, sheetTop(), 1f)
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(large, sheetTop(), 1f)
    }

    @Test
    fun aMenuOverTheFiltersIsAllTalkBackReachesAndHandsTheFocusBackToItsRow() {
        show()
        val talkBack = talkBack()
        openFilters()
        tap(FILTERS_CARRIER)
        compose.onNodeWithTag(FILTERS_DONE).assertDoesNotExist()
        compose.onNodeWithTag("$FILTERS_STATUS.${ParcelStatusFilter.All.rawValue}").assertDoesNotExist()
        assertTrue(compose.onNodeWithTag("$FILTERS_CARRIER.all").hasTalkBackFocus(talkBack))
        tap("$FILTERS_CARRIER.dhl")
        compose.onNodeWithTag(FILTERS_DONE).assertExists()
        assertTrue(compose.onNodeWithTag(FILTERS_CARRIER).hasTalkBackFocus(talkBack))
    }

    @Test
    fun aCarrierWhoseParcelsHaveGoneStaysChosenInThePicker() {
        show()
        openFilters()
        tap(FILTERS_CARRIER)
        tap("$FILTERS_CARRIER.dhl")
        source.parcels.value = source.parcels.value.filter { it.carrier.rawValue != "dhl" }
        compose.waitForIdle()
        // As its chip still says, the row and its menu have it chosen.
        assertTrue(exists("$FILTER_CHIPS.carrier"))
        compose.onNodeWithTag(FILTERS_CARRIER).assert(hasContentDescription("DHL", substring = true))
        tap(FILTERS_CARRIER)
        compose.onNodeWithTag("$FILTERS_CARRIER.dhl").assertIsSelected()
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
        openSearch()
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
        openSearch()
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
        val title = compose.onNode(hasText("Sort & filter") and isHeading())
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

    /**
     * Beside a parcel, with the search closed, the filters come in a popover titled Sort & filter off their button
     * beside the search's: its body 13 under it, across from it.
     */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun besideAParcelThePopoverComesOffTheButtonBesideTheSearch() {
        show()
        inList("Birthday gift").performClick()
        compose.waitForIdle()
        assertTrue(exists(PARCEL_PAGE))
        assertFalse(exists(SEARCH_ROW))
        openFilters()
        assertTrue(exists(FILTERS_POPOVER))
        assertFalse(exists(SHEET_PANEL))
        compose.onNode(hasText("Sort & filter") and isHeading()).assertExists()
        val button = compose.onNodeWithTag(FILTERS_BUTTON, useUnmergedTree = true).getUnclippedBoundsInRoot()
        val search = compose.onNodeWithTag(SEARCH_BUTTON, useUnmergedTree = true).getUnclippedBoundsInRoot()
        val body = compose.onNodeWithTag(FILTERS_POPOVER).getUnclippedBoundsInRoot()
        assertEquals(search.top.value, button.top.value, 0.5f)
        assertEquals(button.bottom.value + 13f, body.top.value, 1f)
        assertTrue("$button over $body", (button.left + button.right) / 2 in body.left..body.right)
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
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aPopoverInAWindowTurnedCompactIsASheet() {
        show()
        openFilters()
        assertTrue(exists(FILTERS_POPOVER))
        // Resized to 500 points across, as beside another app on a tablet.
        windowWidth = 1000
        compose.waitForIdle()
        assertTrue(exists(FILTERS_SHEET))
        assertFalse(exists(FILTERS_POPOVER))
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aMenuOverThePopoverIsAllTalkBackReaches() {
        show()
        talkBack()
        openFilters()
        tap(FILTERS_CARRIER)
        compose.onNodeWithTag(FILTERS_DONE).assertDoesNotExist()
        compose.onNodeWithTag("$FILTERS_CARRIER.all").assertExists()
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun thePopoverMadeAgainWithTheWindowIsThereWithoutGrowingAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openFilters()
        val shown = popoverInWindow()
        recreation.recreate()
        // Its button is laid out in the first frame, the popover put back over the window in the next, and from the
        // one after it stands by its button at its full size.
        repeat(2) { compose.mainClock.advanceTimeByFrame() }
        assertEquals(shown, popoverInWindow())
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertEquals(shown, popoverInWindow())
    }

    /** The popover's bounds in the window, as drawn, its scale as it grows included. */
    private fun popoverInWindow() = compose.onNodeWithTag(FILTERS_POPOVER).fetchSemanticsNode().boundsInWindow

    /** A window too short for the popover's least height under its button (B-S36). */
    @Test
    @Config(qualifiers = "w900dp-h520dp-xhdpi")
    fun inAShortWindowThePopoverStaysInItAndScrolls() = popoverStaysInTheWindow()

    /** A foldable open flat on its side. */
    @Test
    @Config(qualifiers = "w823dp-h707dp-xhdpi")
    fun onAnOpenFoldablePopoverStaysInTheWindow() = popoverStaysInTheWindow()

    private fun popoverStaysInTheWindow() {
        show()
        openFilters()
        assertTrue(exists(FILTERS_POPOVER))
        val window = compose.onRoot().getUnclippedBoundsInRoot()
        val body = compose.onNodeWithTag(FILTERS_POPOVER).getUnclippedBoundsInRoot()
        val button = compose.onNodeWithTag(FILTERS_BUTTON, useUnmergedTree = true).getUnclippedBoundsInRoot()
        assertTrue("$body in $window", body.bottom <= window.bottom - 16.dp && body.top >= window.top)
        // Its arrow points at the button: the body 13 under it, across from it.
        assertEquals(button.bottom.value + 13f, body.top.value, 1f)
        assertTrue("$button over $body", (button.left + button.right) / 2 in body.left..body.right)
        // What doesn't fit scrolls into it.
        compose.onNodeWithTag(FILTERS_CLEAR).performScrollTo()
        compose.waitForIdle()
        val clear = compose.onNodeWithTag(FILTERS_CLEAR).getUnclippedBoundsInRoot()
        assertTrue("$clear in $body", clear.top >= body.top && clear.bottom <= body.bottom)
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

/** The activity's window, [width] pixels across while that isn't null, as a window resized beside another app. */
private class ResizedWindow(private val window: WindowInfo, private val width: () -> Int?) : WindowInfo {
    override val isWindowFocused: Boolean get() = window.isWindowFocused
    override val containerSize: IntSize get() = width()?.let { IntSize(it, window.containerSize.height) } ?: window.containerSize
}
