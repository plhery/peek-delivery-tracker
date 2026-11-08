package com.plhery.peekdeliverytracker.ui

import android.os.SystemClock
import android.text.InputType
import android.view.InputDevice
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.ScrollAxisRange
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.ArchiveSwipeState
import com.plhery.peekdeliverytracker.core.GlobeCamera
import com.plhery.peekdeliverytracker.core.GlobeProjection
import com.plhery.peekdeliverytracker.core.PipPlacement
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ARCHIVE_ACTION
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_DONE
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_POPOVER
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.INLINE_TOAST
import com.plhery.peekdeliverytracker.ui.deliveries.MAP_COVER
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CONTENT
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_ROUTE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_BURST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.SEARCH_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.SEARCH_CLEAR
import com.plhery.peekdeliverytracker.ui.deliveries.SEARCH_FIELD
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.cardId
import com.plhery.peekdeliverytracker.ui.map.LocalMapCameraWatch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import java.util.UUID
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/**
 * The deliveries' list under a finger (A-G01, A-G03, A-G07, A-G09, A-G13, A-G17), its touches sent through the system's
 * input into this device's window as the screen's are ([ScreenTouches], and a swipe's or a pinch's a frame apart): a
 * card tapped opens its parcel, over the list or beside it; an open card tapped beside its Archive closes; the Archive
 * under it takes a tap once 8 points of it show and archives once the card has left; a drag of the list ends a card's
 * arrival; the search's field, the filters' sheet or popover, the Undo under the list and a message with no action; and
 * the map on the pass of the parcel a card opens, which two fingers zoom until they lift and one leaves to the page.
 * Signed in as the synthetic reader@example.com on fake deliveries ([ParcelWalk]), the demo's parcels or synthetic
 * lamps, which reach no server; each test keeps Zurich's time and the captures' moment.
 */
@RunWith(AndroidJUnit4::class)
class ListGesturesDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val touches = ScreenTouches(compose)
    private val demo: List<Parcel> = DemoCards.parcels()
    private val density: Float get() = compose.density.density

    /** Each camera the map on the open parcel's pass was composed with (`peek`, not the opened map's), on the UI thread. */
    private val peeked = mutableListOf<GlobeCamera>()
    private val watch: (Boolean, Boolean, GlobeCamera, PipPlacement?) -> Unit = { interactive, peek, camera, _ ->
        if (peek && !interactive) peeked += camera
    }

    // A-G01: a card's tap.

    /**
     * A-G01: a card tapped opens its parcel: pushed over the whole window where the list and the page stack, the list
     * back after it; beside the list, which stays, where they stand side by side.
     */
    @Test
    fun aTapOnACardOpensItsParcelOverTheListOrBesideIt() {
        walk.show(demo)
        val before = walk.shown()
        val parcel = listOf("Birthday gift", "New trainers").map { DemoCards.parcel(it) }.first { it.id != before }
        scrollTo(parcel)

        touches.tap(touches.point(card(parcel.label), x = 0.3f, y = 0.4f))
        compose.waitUntil(LONG_MILLIS) { walk.shown() == parcel.id && pageShows(parcel) }
        walk.settle()
        val page = walk.node(PARCEL_PAGE).getBoundsInRoot()
        if (walk.stacked()) {
            val window = walk.onUi { compose.activity.window.decorView.width } / density
            val whole = page.left.value < 1f && abs(page.right.value - window) < 1f
            assertTrue("Over the whole window, $window wide: $page", whole)
            walk.shot("A-G01-tap-pushes", alsoDark = false)
            walk.back()
            compose.waitUntil(LONG_MILLIS) { walk.shown() == null }
        } else {
            val list = walk.node(PARCEL_LIST).getBoundsInRoot()
            assertTrue("Beside the list: $page, $list", page.left >= list.right - 1.dp)
            assertTrue("The list stays", listed(parcel))
            walk.shot("A-G01-tap-beside", alsoDark = false)
        }
    }

    /**
     * A-G01: a tap on an open card, left of its Archive, closes the card and opens nothing, the shown parcel kept; the
     * card tapped again, closed, opens its parcel.
     */
    @Test
    fun aTapOnAnOpenCardLeftOfItsArchiveClosesItAndOpensNothing() {
        walk.reduceMotion = false
        val lamps = (1..12).map { syntheticLamp(it, due = it) }
        walk.show(lamps)
        val lamp = lamps[3]
        scrollTo(lamp)
        val rest = cardBounds(lamp)
        val shown = walk.shown()
        swipeOpen(lamp)
        assertNear("Open on its action", rest.left - ArchiveSwipeState.ACTION_WIDTH.dp, cardBounds(lamp).left)
        walk.shot("A-G01-open-card", alsoDark = false)

        val action = touches.point(openAction(), x = 0f)
        val at = touches.point(card(lamp.label), x = 0.45f)
        assertTrue("Left of the action: $at, $action", at.x < action.x)
        touches.tap(at)
        compose.waitUntil(LONG_MILLIS) { openActions() == 0 }
        walk.settle()
        assertNear("Closed", rest.left, cardBounds(lamp).left)
        assertEquals("Nothing opened", shown, walk.shown())
        assertFalse(lamp.id in archived())

        touches.tap(touches.point(card(lamp.label), x = 0.45f))
        compose.waitUntil(LONG_MILLIS) { walk.shown() == lamp.id }
    }

    // A-G03 and A-G17: the Archive under a card, and its Undo.

    /**
     * A-G03, A-G17: the Archive under an open card, 88 wide on its trailing side, is a button once 8 points of it show,
     * riding on the card's edge until then; tapped, the card leaves to the left first and the parcel is archived once
     * it has gone. The Undo under the list says "app.undo" and, tapped, brings the parcel back to the list.
     */
    @Test
    fun theArchiveUnderAnOpenCardTakesATapOnceShownAndArchivesOnceTheCardHasLeft() {
        walk.reduceMotion = false
        val lamps = (1..12).map { syntheticLamp(it, due = it) }
        walk.show(lamps)
        val lamp = lamps[2]
        val finger = Finger(lamp)
        val rest = cardBounds(lamp)

        finger.down()
        finger.decide()
        finger.reveal(HIDDEN_REVEAL)
        assertEquals("$HIDDEN_REVEAL points shown: no button yet", 0, openActions())
        finger.reveal(REACHABLE_REVEAL)
        assertEquals("$REACHABLE_REVEAL points shown: a button", 1, openActions())
        assertNear("On the card's edge", cardBounds(lamp).right, actionBounds().left, within = 1.5f)
        finger.reveal(OPEN_REVEAL)
        finger.lift()
        walk.settle()

        val action = actionBounds()
        assertNear("Its width", ArchiveSwipeState.ACTION_WIDTH.dp, action.right - action.left)
        assertNear("At the row's trailing edge", rest.right, action.right)
        assertNear("Beside the card", cardBounds(lamp).right, action.left)
        val archive = compose.onNode(openAction()).fetchSemanticsNode().config
        assertEquals(listOf(walk.text("parcel.archive")), archive.getOrNull(SemanticsProperties.ContentDescription))

        val before = cardBounds(lamp).left
        val at = touches.point(openAction())
        compose.mainClock.autoAdvance = false
        val start = walk.now()
        touches.tap(at)
        walk.advanceTo(start + EXIT_GLIMPSE_MILLIS)
        walk.recompose()
        val leaving = cardBounds(lamp).left
        assertTrue("On its way out to the left: $leaving, from $before", leaving < before - 8.dp)
        assertFalse("Archived only once the card has left", lamp.id in archived())
        framesUntil(3.0) { lamp.id in archived() }
        compose.mainClock.autoAdvance = true

        val undo = walk.text("app.undo")
        val said = "${walk.onUi { walk.deliveries.archivedText(lamp) }}, $undo"
        val undoToast = hasTestTag(INLINE_TOAST) and hasContentDescription(said)
        compose.waitUntil(LONG_MILLIS) { compose.onAllNodes(undoToast).fetchSemanticsNodes().isNotEmpty() }
        assertFalse("The row has left the list", listed(lamp))
        val toast = compose.onNodeWithTag(INLINE_TOAST).fetchSemanticsNode().config
        assertEquals(Role.Button, toast.getOrNull(SemanticsProperties.Role))
        assertEquals(undo, toast.getOrNull(SemanticsActions.OnClick)?.label)
        walk.shot("A-G03-archived-undo", alsoDark = false)

        touches.tap(toastEnd())
        compose.waitUntil(LONG_MILLIS) { lamp.id in restored() && !walk.exists(INLINE_TOAST) && listed(lamp) }
    }

    // A-G07: a drag of the list.

    /**
     * A-G07: a drag of the list ends the arrival of a card a link has just added, well within its 0.95 s, and the
     * reveal with it (`.onScrollPhaseChange`); a card swiped open stays open through a drag of the list, each card
     * keeping its own swipe.
     */
    @Test
    fun aDragOfTheListEndsTheArrivalOfACardJustAddedAndLeavesAnOpenCardOpen() {
        walk.reduceMotion = false
        val inbox = DeliveriesInbox()
        val lamps = (1..20).map { syntheticLamp(it, due = it) } + (22..30).map { syntheticLamp(it) }
        val kept = syntheticLamp(21)
        walk.show(lamps, inbox = inbox)
        swipeOpen(lamps[3])
        touches.drag(touches.point(PARCEL_LIST, y = 0.6f), Offset(0f, -OPEN_DRAG * density))
        walk.settle()
        assertEquals("Open through a drag of the list", 1, openActions())

        compose.mainClock.autoAdvance = false
        walk.onUi { walk.source.parcels.value = lamps + kept }
        walk.recompose()
        walk.onUi { inbox.added(kept.id) }
        walk.recompose()
        framesUntil(3.0) { walk.onUi { walk.deliveries.parcelBurst } == kept.id }
        val arrived = walk.now()
        walk.recompose()
        assertTrue("The arrival plays", walk.exists(PARCEL_BURST))

        touches.drag(touches.point(PARCEL_LIST, y = 0.6f), Offset(0f, -ARRIVAL_DRAG * density))
        assertNull("The arrival ended", walk.onUi { walk.deliveries.parcelBurst })
        assertNull("The reveal ended", walk.onUi { walk.deliveries.revealParcel })
        val took = walk.now() - arrived
        assertTrue("Ended by the drag, $took ms in, short of its own end", took < BURST_MILLIS)
        walk.recompose()
        assertFalse(walk.exists(PARCEL_BURST))

        compose.mainClock.autoAdvance = true
        walk.settle()
        assertNull(walk.onUi { walk.deliveries.parcelBurst })
        walk.shot("A-G07-drag-ends-arrival", alsoDark = false)
    }

    // A-G09: the search and the filters.

    /**
     * A-G09: the search button shows the field, focused, with the keyboard up where the device shows one; it asks the
     * keyboard for no capitals, no corrections and no suggestions, and for Search as its action. Its cross empties the
     * query, the field keeping the focus, and the button hides the field again, its focus gone.
     */
    @Test
    fun theSearchButtonShowsAFocusedFieldThatNeitherCapitalisesNorCorrectsAndItsCrossEmptiesIt() {
        walk.show(demo)
        val gift = DemoCards.parcel("Birthday gift")
        val moon = DemoCards.parcel("Moon lamp")

        touches.tap(SEARCH_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SEARCH_FIELD) && walk.onUi { walk.deliveries.searchFocused } }
        walk.node(SEARCH_FIELD).assertIsFocused()
        walk.awaitKeyboard()
        val info = walk.editorInfo()
        val type = info.inputType
        assertEquals(InputType.TYPE_CLASS_TEXT, type and InputType.TYPE_MASK_CLASS)
        assertEquals("No capitals", 0, type and CAPITALS)
        assertEquals("No corrections", 0, type and InputType.TYPE_TEXT_FLAG_AUTO_CORRECT)
        val suggestions = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        assertEquals("No suggestions", suggestions, type and suggestions)
        assertEquals("Search", EditorInfo.IME_ACTION_SEARCH, info.imeOptions and EditorInfo.IME_MASK_ACTION)

        walk.node(SEARCH_FIELD).performTextInput("Moon")
        compose.waitUntil(LONG_MILLIS) { listed(moon) && !listed(gift) }
        assertTrue(walk.exists(SEARCH_CLEAR))
        walk.shot("A-G09-search", alsoDark = false)

        touches.tap(SEARCH_CLEAR)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.query }.isEmpty() && listed(gift) }
        assertEquals("", walk.editable(SEARCH_FIELD))
        assertFalse(walk.exists(SEARCH_CLEAR))
        assertTrue("The field keeps the focus", walk.onUi { walk.deliveries.searchFocused })

        touches.tap(SEARCH_BUTTON)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SEARCH_FIELD) }
        assertFalse(walk.onUi { walk.deliveries.showingSearch })
        assertFalse(walk.onUi { walk.deliveries.searchFocused })
        walk.awaitStill()
    }

    /**
     * A-G09: the filters button takes the focus from the search's field, the search left open, then opens the filters:
     * a sheet where the list and the page stack, a popover off the button, across it and under or over it, where they
     * stand side by side. Done closes them.
     */
    @Test
    fun theFiltersButtonTakesTheFocusFromTheSearchAndOpensASheetOrAPopoverOffIt() {
        walk.show(demo)
        touches.tap(SEARCH_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.searchFocused } }
        walk.awaitKeyboard(required = false)
        val stacked = walk.stacked()
        val filters = if (stacked) FILTERS_SHEET else FILTERS_POPOVER
        val other = if (stacked) FILTERS_POPOVER else FILTERS_SHEET
        val button = walk.node(FILTERS_BUTTON).getBoundsInRoot()

        touches.tap(FILTERS_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(filters) }
        walk.awaitStill()
        walk.settle()
        assertFalse(walk.exists(other))
        assertTrue(walk.onUi { walk.deliveries.showingFilters })
        assertFalse("The field gave up the focus", walk.onUi { walk.deliveries.searchFocused })
        assertTrue("The search stays open", walk.onUi { walk.deliveries.showingSearch })
        if (!stacked) {
            val popover = compose.onNodeWithTag(FILTERS_POPOVER, useUnmergedTree = true).getBoundsInRoot()
            val middle = (button.left + button.right) / 2
            assertTrue("Across the button: $popover, $button", middle >= popover.left && middle <= popover.right)
            val clear = popover.top >= button.bottom - 1.dp || popover.bottom <= button.top + 1.dp
            assertTrue("Under or over the button: $popover, $button", clear)
        }
        walk.shot(if (stacked) "A-G09-filters-sheet" else "A-G09-filters-popover", alsoDark = false)

        touches.tap(FILTERS_DONE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FILTERS_SHEET) && !walk.exists(FILTERS_POPOVER) }
        assertFalse(walk.onUi { walk.deliveries.showingFilters })
    }

    // A-G13: two fingers on the map of a parcel's pass. A card in the list draws its map for its look alone and takes no
    // touch there (`peek: false`), as the iPhone's: the pass's is the map a pinch zooms.

    /**
     * A-G13: two fingers drawn apart on the map of a parcel's pass zoom it as far as they part, about the point between
     * them as they landed, the place there staying under them (`MagnifyGesture` alone, nothing turned); the page stays
     * where it was and the map shut. With one finger lifted it stays where they put it; once both have, it flies back to
     * its own camera over its flight's time (A-AN46), never closer on the way. A tap on it then opens the map.
     */
    @Test
    fun twoFingersOnAPassMapZoomItAboutThemAndItFliesBackOnceTheyLift() {
        walk.reduceMotion = false
        showWatched()
        val gift = DemoCards.parcel("Birthday gift")
        walk.open(gift)
        awaitPass()
        val start = passCamera()
        val scroll = pageScroll().value()
        val bounds = touches.node(hasTestTag(PAGE_ROUTE)).boundsInWindow
        // Where the fingers' middle stands on the map, in its points, as the map reads where they stand.
        val anchor = Point((bounds.width / 2 / density).toDouble(), (bounds.height * PINCH_HEIGHT / density).toDouble())
        // Framed closer than the whole globe, which a pinch draws to the middle of the map rather than about the fingers.
        assertTrue("Framed close: $start on ${bounds.size}", start.scale >= 1.25 * min(bounds.width, bounds.height) / density)

        val fingers = TwoFingers(touches.point(PAGE_ROUTE, y = PINCH_HEIGHT), PINCH_DIRECTION, PINCH_FROM * density)
        fingers.put()
        fingers.spreadTo(PINCH_TO * density)
        fingers.hold()
        val zoomed = passCamera()
        val parted = (PINCH_TO / PINCH_FROM).toDouble()
        assertEquals("Zoomed as far as the fingers parted: $zoomed from $start", parted, zoomed.scale / start.scale, parted * 0.01)
        assertEquals("Nothing turned", start.center, zoomed.center)
        val under = checkNotNull(GlobeProjection(start).invert(anchor)) { "No globe under the fingers at $anchor: $start" }
        val there = GlobeProjection(zoomed).project(under)
        val kept = abs(there.x - anchor.x) <= 0.5 && abs(there.y - anchor.y) <= 0.5
        assertTrue("The place between the fingers stays under them: $there, not $anchor", kept)
        assertEquals("The page stays", scroll, pageScroll().value(), 0.5f)
        assertFalse("The map stays shut", walk.exists(MAP_COVER))
        walk.shot("A-G13-pass-pinch", alsoDark = false)

        fingers.liftSecond()
        assertEquals("One finger still down: where the fingers put it", zoomed, passCamera())
        compose.mainClock.autoAdvance = false
        val flying = walk.onUi { peeked.size }
        fingers.liftFirst()
        val lifted = walk.now()
        frame()
        val leaving = passCamera()
        assertTrue("Flying back, not there at once: $leaving", leaving != start && leaving.scale > start.scale * 2)
        val duration = GlobeCamera.flightDuration(zoomed, start)
        framesUntil(duration + 0.5) { passCamera() == start }
        val took = walk.now() - lifted
        assertTrue("Back over the flight's $duration s, not sooner: $took ms", took >= duration * 1_000 - 2 * ScreenTouches.FRAME_MILLIS)
        val scales = walk.onUi { peeked.drop(flying) }.map { it.scale }
        assertTrue("Never closer on the way: $scales", scales.zipWithNext().all { (was, next) -> next <= was * (1 + 1e-9) })
        compose.mainClock.autoAdvance = true
        walk.settle()
        assertEquals(start, passCamera())
        assertEquals("The page stays", scroll, pageScroll().value(), 0.5f)
        assertFalse(walk.exists(MAP_COVER))
        assertEquals("Nothing else opened", gift.id, walk.shown())

        touches.tap(touches.point(PAGE_ROUTE, y = PINCH_HEIGHT))
        compose.waitUntil(LONG_MILLIS) { walk.exists(MAP_COVER) }
    }

    /**
     * A-G13: one finger drawn up across the map of a parcel's pass scrolls the page and turns nothing: the map takes
     * only two fingers, and stays shut.
     */
    @Test
    fun oneFingerOnAPassMapScrollsThePageAndLeavesTheMapAsItWas() {
        showWatched()
        val trainers = DemoCards.parcel("New trainers")
        walk.open(trainers)
        awaitPass()
        val start = passCamera()
        val from = walk.onUi { peeked.size }
        val scroll = pageScroll().value()
        val drag = PASS_DRAG * density
        val room = pageScroll().maxValue() - scroll
        assertTrue("Room to scroll the page $PASS_DRAG dp: $room px", room >= drag)

        touches.drag(touches.point(PAGE_ROUTE, y = PINCH_HEIGHT), Offset(0f, -drag))
        walk.settle()
        val scrolled = pageScroll().value() - scroll
        assertTrue("The page followed the finger: $scrolled px of $drag", scrolled > drag / 2)
        val seen = walk.onUi { peeked.drop(from) }
        assertTrue("The map stayed as it was: $seen, from $start", seen.all { it == start })
        assertFalse("The map stayed shut", walk.exists(MAP_COVER))
        assertEquals(trainers.id, walk.shown())
        walk.shot("A-G13-pass-one-finger", alsoDark = false)
    }

    // A-G17: a message under the list.

    /**
     * A-G17: a message under the list ("link.added") is read with its words alone, with no button and no action; a tap
     * on it does nothing and reaches nothing under it, and it goes once its four seconds are over.
     */
    @Test
    fun aMessageUnderTheListHasNoActionAndATapOnItDoesNothing() {
        val inbox = DeliveriesInbox()
        walk.show(demo, inbox = inbox)
        compose.mainClock.autoAdvance = false
        walk.onUi { inbox.added(null) }
        walk.recompose()
        framesUntil(1.0) { walk.exists(INLINE_TOAST) }
        val toast = compose.onNodeWithTag(INLINE_TOAST).fetchSemanticsNode().config
        assertEquals(listOf(walk.text("link.added")), toast.getOrNull(SemanticsProperties.ContentDescription))
        assertNull("No button", toast.getOrNull(SemanticsProperties.Role))
        assertNull("No action", toast.getOrNull(SemanticsActions.OnClick))
        val shown = walk.shown()

        touches.tap(toastEnd())
        walk.recompose()
        assertTrue("Still under the list", walk.exists(INLINE_TOAST))
        assertEquals("Nothing under it opened", shown, walk.shown())
        assertTrue(restored().isEmpty())
        walk.shot("A-G17-message", alsoDark = false)

        compose.mainClock.advanceTimeBy(TOAST_MILLIS + 500)
        walk.recompose()
        assertFalse(walk.exists(INLINE_TOAST))
    }

    /** The card of the parcel named `label` in the list: a button read with its name. */
    private fun card(label: String): SemanticsMatcher =
        hasContentDescription(label, substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST))

    /** An open card's Archive: a button once 8 points of it show. */
    private fun openAction(): SemanticsMatcher = hasTestTag(ARCHIVE_ACTION) and hasClickAction()

    private fun openActions(): Int = compose.onAllNodes(openAction()).fetchSemanticsNodes().size

    /** Where the one open card's Archive stands, whether its row shows all of it or not. */
    private fun actionBounds(): DpRect = compose.onNode(openAction()).getUnclippedBoundsInRoot()

    /** Where the card of `parcel` stands, moved with its swipe, whether its row shows all of it or not. */
    private fun cardBounds(parcel: Parcel): DpRect =
        compose.onAllNodes(card(parcel.label)).onFirst().getUnclippedBoundsInRoot()

    private fun assertNear(message: String, expected: Dp, actual: Dp, within: Float = 1f) =
        assertTrue("$message: $actual, not $expected", abs((actual - expected).value) <= within)

    /** The list scrolled to the item of `parcel`'s card, which puts it below the top bar. */
    private fun scrollTo(parcel: Parcel) {
        val item = walk.onUi { walk.deliveries.itemKeys.first { cardId(it) == parcel.id } }
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey(item)
        compose.waitForIdle()
    }

    /** Whether the list, as last drawn, has a card for `parcel`. */
    private fun listed(parcel: Parcel): Boolean = walk.onUi { walk.deliveries.itemKeys.any { cardId(it) == parcel.id } }

    /** The page of `parcel`, alone, its name at its top. */
    private fun pageShows(parcel: Parcel): Boolean = walk.count(PARCEL_PAGE) == 1 && compose.onAllNodes(
        hasTestTag(PAGE_NAME) and hasText(parcel.label) and hasAnyAncestor(hasTestTag(PARCEL_PAGE)),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    private fun archived(): List<UUID> = walk.onUi { walk.source.archived.toList() }

    private fun restored(): List<UUID> = walk.onUi { walk.source.restored.toList() }

    /** The demo's parcels shown, each camera of a pass's map heard ([peeked]). */
    private fun showWatched() = walk.show(
        demo,
        around = { content -> CompositionLocalProvider(LocalMapCameraWatch provides watch, content = content) },
    )

    /** The map on the open parcel's pass drawn, its camera heard, and still. */
    private fun awaitPass() {
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_ROUTE) && walk.onUi { peeked.isNotEmpty() } }
        walk.settle()
    }

    /** The camera the map on the open parcel's pass was last drawn from. */
    private fun passCamera(): GlobeCamera = walk.onUi { peeked.last() }

    /** The page's scroll: how far down it stands and how far it goes, in pixels. */
    private fun pageScroll(): ScrollAxisRange =
        checkNotNull(touches.node(hasTestTag(PAGE_CONTENT)).config.getOrNull(SemanticsProperties.VerticalScrollAxisRange)) {
            "The page has no scroll"
        }

    /**
     * The point on the screen [UNDO_INSET] in from the trailing edge of the message under the list, at its middle: on
     * the Undo where it has one, which TalkBack reaches only through the message's own action.
     */
    private fun toastEnd(): Offset {
        val toast = touches.node(hasTestTag(INLINE_TOAST)).boundsInWindow
        return touches.origin() + Offset(toast.right - UNDO_INSET * density, toast.center.y)
    }

    /** The card of `parcel` drawn open by a finger past half its action, and settled open on it. */
    private fun swipeOpen(parcel: Parcel) {
        Finger(parcel).run {
            down()
            decide()
            reveal(OPEN_REVEAL)
            lift()
        }
        compose.waitUntil(LONG_MILLIS) { openActions() == 1 }
        walk.settle()
    }

    /** A frame of the test's clock, the state written since sent first. */
    private fun frame() {
        walk.onUi { Snapshot.sendApplyNotifications() }
        compose.mainClock.advanceTimeByFrame()
    }

    /** The test's clock moved a frame at a time until `done`, within `seconds`. */
    private fun framesUntil(seconds: Double, done: () -> Boolean) {
        repeat((seconds * 60).toInt()) {
            if (done()) return
            frame()
        }
        assertTrue("Not done in $seconds s", done())
    }

    /** A frame of real time and of the test's clock, as [ScreenTouches] moves a finger on in. */
    private fun touchFrame() {
        SystemClock.sleep(ScreenTouches.FRAME_MILLIS)
        compose.mainClock.advanceTimeBy(ScreenTouches.FRAME_MILLIS)
    }

    /**
     * Two fingers on the screen, their touches sent as the screen's are through UiAutomation, as [ScreenTouches] sends a
     * finger's, each a frame after the one before ([touchFrame]): the first `apart` pixels back from `middle` along
     * `direction`, the second as far on from it a frame later; then drawn apart or together about that middle, held
     * still, and lifted, the second first.
     */
    private inner class TwoFingers(private val middle: Offset, private val direction: Offset, private var apart: Float) {
        private var down = 0L
        private val pointers = Array(2) { pointer ->
            MotionEvent.PointerProperties().apply {
                id = pointer
                toolType = MotionEvent.TOOL_TYPE_FINGER
            }
        }

        fun put() {
            down = SystemClock.uptimeMillis()
            send(MotionEvent.ACTION_DOWN, 1)
            touchFrame()
            send(MotionEvent.ACTION_POINTER_DOWN or SECOND_POINTER, 2)
            compose.waitForIdle()
        }

        /** Each drawn to `to` pixels from the middle in [ScreenTouches.DRAG_STEPS] frames. */
        fun spreadTo(to: Float) {
            val from = apart
            val steps = ScreenTouches.DRAG_STEPS
            for (step in 1..steps) {
                touchFrame()
                apart = from + (to - from) * step / steps
                send(MotionEvent.ACTION_MOVE, 2)
            }
        }

        /** Held still for [ScreenTouches.HOLD_FRAMES] frames, and the window idle after. */
        fun hold() {
            repeat(ScreenTouches.HOLD_FRAMES) {
                touchFrame()
                send(MotionEvent.ACTION_MOVE, 2)
            }
            compose.waitForIdle()
        }

        fun liftSecond() {
            touchFrame()
            send(MotionEvent.ACTION_POINTER_UP or SECOND_POINTER, 2)
            compose.waitForIdle()
        }

        fun liftFirst() {
            touchFrame()
            send(MotionEvent.ACTION_UP, 1)
            compose.waitForIdle()
        }

        /**
         * The touch for `action` with the first `count` fingers, sent; the first put down again a little later while the
         * screen refuses it, as [ScreenTouches] puts its finger down again while the test's own activity stands over the
         * app's.
         */
        private fun send(action: Int, count: Int) {
            val until = SystemClock.uptimeMillis() + ScreenTouches.DOWN_AGAIN_MILLIS
            while (!inject(action, count)) {
                check(action == MotionEvent.ACTION_DOWN && SystemClock.uptimeMillis() < until) {
                    "The screen refused the touch ${MotionEvent.actionToString(action)} about $middle"
                }
                SystemClock.sleep(ScreenTouches.FRAME_MILLIS * 6)
                down = SystemClock.uptimeMillis()
            }
        }

        private fun inject(action: Int, count: Int): Boolean {
            val coords = Array(count) { pointer ->
                val at = if (pointer == 0) middle - direction * apart else middle + direction * apart
                MotionEvent.PointerCoords().apply {
                    x = at.x
                    y = at.y
                    pressure = 1f
                    size = 1f
                }
            }
            val time = if (action == MotionEvent.ACTION_DOWN) down else SystemClock.uptimeMillis()
            val event = MotionEvent.obtain(
                down, time, action, count, Array(count) { pointers[it] }, coords, 0, 0, 1f, 1f, 0, 0,
                InputDevice.SOURCE_TOUCHSCREEN, 0,
            )
            try {
                return instrumentation.uiAutomation.injectInputEvent(event, true)
            } finally {
                event.recycle()
            }
        }
    }

    /**
     * A finger on the card of `parcel`, its touches sent as a screen's are (a finger's PointerProperties through
     * Instrumentation.sendPointerSync), a frame apart: down 40 dp in from the card's trailing edge at its middle, the
     * list scrolled to the card first. The card's width, the row's, gives how far a finger travels for what the card
     * shows ([ArchiveSwipeState.travel]).
     */
    private inner class Finger(parcel: Parcel) {
        private val width: Double
        private var x: Float
        private val y: Float

        /** Where the drag was decided, from which the row measures it. */
        private var from = 0f
        private var downTime = 0L
        private var time = 0L
        private val finger = MotionEvent.PointerProperties().apply {
            id = 0
            toolType = MotionEvent.TOOL_TYPE_FINGER
        }

        init {
            scrollTo(parcel)
            val bounds = compose.onAllNodes(card(parcel.label)).onFirst().fetchSemanticsNode().boundsInWindow
            val origin = touches.origin()
            width = (bounds.width / density).toDouble()
            x = origin.x + bounds.right - 40 * density
            y = origin.y + bounds.center.y
        }

        fun down() {
            downTime = SystemClock.uptimeMillis()
            time = downTime
            send(MotionEvent.ACTION_DOWN)
        }

        /** 12 dp towards the leading edge in one move: past the 10 dp the row judges a drag by, straight across. */
        fun decide() {
            x -= 12 * density
            send(MotionEvent.ACTION_MOVE)
            from = x
        }

        /** Moved, 6 dp a frame, to where the card shows `points` of its action. */
        fun reveal(points: Double) {
            val target = from - (ArchiveSwipeState.travel(points, width) * density).toFloat()
            val start = x
            val steps = max(1, ceil(abs(target - start) / (6 * density)).toInt())
            for (step in 1..steps) {
                x = start + (target - start) * step / steps
                send(MotionEvent.ACTION_MOVE)
            }
            compose.waitForIdle()
        }

        /** Held still for eight frames, so that nothing is thrown, then lifted. */
        fun lift() {
            repeat(8) { send(MotionEvent.ACTION_MOVE) }
            send(MotionEvent.ACTION_UP)
            compose.waitForIdle()
        }

        private fun send(action: Int) {
            if (action != MotionEvent.ACTION_DOWN) time = max(time + 16, SystemClock.uptimeMillis())
            val at = x
            val height = y
            val where = MotionEvent.PointerCoords().apply {
                this.x = at
                this.y = height
                pressure = 1f
                size = 1f
            }
            val event = MotionEvent.obtain(
                downTime, time, action, 1, arrayOf(finger), arrayOf(where), 0, 0, 1f, 1f, 0, 0,
                InputDevice.SOURCE_TOUCHSCREEN, 0,
            )
            instrumentation.sendPointerSync(event)
            event.recycle()
        }
    }

    private companion object {
        const val FOLDER = "gestureswalk"

        /** How much of the action a finger shows short of its button's 8 points, and past them, in points. */
        const val HIDDEN_REVEAL = 4.0
        const val REACHABLE_REVEAL = 12.0

        /** How far a card is drawn open for its button: past half the action and short of it, so it settles open. */
        const val OPEN_REVEAL = 64.0

        /** How long into a card's way out it is looked at: well before it has gone. */
        const val EXIT_GLIMPSE_MILLIS = 100L

        /** How far the list is dragged up, in dp: past an open card, and with an arrival playing. */
        const val OPEN_DRAG = 60f
        const val ARRIVAL_DRAG = 100f

        /** How long a card's arrival plays when nothing ends it. */
        const val BURST_MILLIS = 950L

        /** How long a message stays under the list. */
        const val TOAST_MILLIS = 4_000L

        /** How far in from the message's trailing edge its Undo is tapped, in dp: within the word, past its padding. */
        const val UNDO_INSET = 34f

        /**
         * Where on a pass's map two fingers land, across its middle and a little below half its height: on the room the
         * card leaves the route, under the marks and the buttons and above the name.
         */
        const val PINCH_HEIGHT = 0.55f

        /** How far each finger stands from their middle as they land, then drawn apart, in dp: two and a half times as far. */
        const val PINCH_FROM = 25f
        const val PINCH_TO = 62.5f

        /** The line the two fingers lie on, up and back to down and on: across the page's scroll and along it. */
        val PINCH_DIRECTION = Offset(0.8f, 0.6f)

        /** The second finger's index in its touches' action. */
        const val SECOND_POINTER = 1 shl MotionEvent.ACTION_POINTER_INDEX_SHIFT

        /** How far one finger draws the page up across a pass's map, in dp. */
        const val PASS_DRAG = 100f

        const val CAPITALS = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS or InputType.TYPE_TEXT_FLAG_CAP_WORDS or
            InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
    }
}
