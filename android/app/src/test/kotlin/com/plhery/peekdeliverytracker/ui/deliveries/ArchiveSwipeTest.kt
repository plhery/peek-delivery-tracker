package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.TouchInjectionScope
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A card swiped to the archive on a phone (A-G01–G03): which drags the swipe takes and which it leaves to the list,
 * opening, flicking, throwing and dragging past the commit point, cards left open, a tap that closes, TalkBack's
 * action, and the Undo that follows.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ArchiveSwipeTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries

    private fun show(reduceMotion: Boolean = false) {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.setContent { RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = reduceMotion) }
        compose.waitForIdle()
    }

    /** Where the card stands, in points, as it moves inside its row, which cuts it. */
    private fun left(name: String): Float = compose.card(name).getUnclippedBoundsInRoot().left.value

    private fun top(name: String): Float = compose.card(name).getUnclippedBoundsInRoot().top.value

    private fun dp(value: Float): Float = value * compose.density.density

    /**
     * A finger down near the card's trailing edge, moved `distance` points across (negative to the left) in `steps`
     * frames, held still for `hold` milliseconds, and lifted: a slow release when it holds, a flick when it doesn't.
     */
    private fun drag(name: String, distance: Float, steps: Int = 12, hold: Long = 200, vertical: Float = 0f) {
        compose.card(name).performTouchInput { slide(distance, steps, hold, vertical) }
        compose.waitForIdle()
    }

    private fun TouchInjectionScope.slide(distance: Float, steps: Int, hold: Long, vertical: Float = 0f) {
        down(Offset(width - dp(30f), height / 2f))
        repeat(steps) { moveBy(Offset(dp(distance) / steps, dp(vertical) / steps)) }
        advanceEventTime(hold)
        up()
    }

    /** The action behind a card open enough to press. */
    private fun openActions(): Int =
        compose.onAllNodes(hasTestTag(ARCHIVE_ACTION) and hasClickAction()).fetchSemanticsNodes().size

    private fun undoShown(name: String) =
        compose.onNode(hasTestTag(INLINE_TOAST) and hasContentDescription(name, substring = true) and hasContentDescription("Undo", substring = true))

    // ArchivePanGestureRecognizer's rule, as ParcelLogicTests checks it on the iPhone.

    @Test
    fun theRecognizerDeclinesVerticalDragsBeforeTheyCanBlockScrolling() {
        for ((dx, dy) in listOf(-3f to -18f, 4f to 24f)) {
            assertEquals("($dx, $dy)", ArchivePan.Failed, archivePanDecision(dx, dy, 10f))
        }
    }

    @Test
    fun theRecognizerLeavesAmbiguousDiagonalDragsToScrolling() {
        for ((dx, dy) in listOf(-20f to -20f, -20f to 18f, 0f to 0f)) {
            assertNotEquals("($dx, $dy)", ArchivePan.Began, archivePanDecision(dx, dy, 10f))
        }
    }

    @Test
    fun theRecognizerAllowsHorizontalRevealAndReverseDrags() {
        for ((dx, dy) in listOf(-20f to 3f, 20f to -3f)) {
            assertEquals("($dx, $dy)", ArchivePan.Began, archivePanDecision(dx, dy, 10f))
        }
    }

    @Test
    fun theRecognizerLetsTheListScrollUntilTheDragShowsItsDirection() {
        show()
        val restLeft = left("Birthday gift")
        val restTop = top("Birthday gift")
        // Up the card: the list scrolls, and the card stays shut.
        drag("Birthday gift", distance = -4f, vertical = -160f, hold = 0)
        assertTrue("The list scrolled: ${top("Birthday gift")} from $restTop", top("Birthday gift") < restTop - 100f)
        assertEquals(restLeft, left("Birthday gift"), 0.5f)
        assertEquals(0, openActions())

        // Across it: the card opens on its action, and the list stays where it was.
        val scrolledTop = top("Birthday gift")
        drag("Birthday gift", distance = -60f, vertical = 6f)
        assertEquals(scrolledTop, top("Birthday gift"), 0.5f)
        assertEquals(restLeft - 88f, left("Birthday gift"), 1f)
        assertEquals(1, openActions())
    }

    @Test
    fun aSlowDragPastHalfTheActionOpensItAndLessShutsIt() {
        show()
        val rest = left("New trainers")
        drag("New trainers", distance = -30f)
        assertEquals(rest, left("New trainers"), 0.5f)
        drag("New trainers", distance = -60f)
        assertEquals(rest - 88f, left("New trainers"), 1f)
        // Dragged back right, it shuts; past its rest it gives a little and comes back.
        drag("New trainers", distance = 140f)
        assertEquals(rest, left("New trainers"), 0.5f)
        assertEquals(emptyList<Any>(), source.archived)
    }

    @Test
    fun aFlickOpensTheActionAndAFlickBackShutsIt() {
        show()
        val rest = left("New trainers")
        drag("New trainers", distance = -30f, steps = 3, hold = 0)
        assertEquals(rest - 88f, left("New trainers"), 1f)
        drag("New trainers", distance = 30f, steps = 3, hold = 0)
        assertEquals(rest, left("New trainers"), 0.5f)
    }

    @Test
    fun aThrowPastTheActionArchivesTheParcelAndOffersUndo() {
        show()
        val parcel = DemoCards.parcel("New trainers")
        drag("New trainers", distance = -120f, steps = 4, hold = 0)
        assertEquals(listOf(parcel.id), source.archived)
        undoShown("New trainers").assertExists()
        compose.onNode(cardNamed("New trainers") and hasAnyAncestor(hasTestTag(PARCEL_LIST))).assertDoesNotExist()
    }

    @Test
    fun aDragPastTheCommitPointArchivesTheParcelAndUndoBringsItBack() {
        show()
        val parcel = DemoCards.parcel("New trainers")
        // Half the card's 358 points: the action leaps to the card's edge and the parcel goes as the finger lifts.
        drag("New trainers", distance = -230f, steps = 20)
        assertEquals(listOf(parcel.id), source.archived)
        undoShown("New trainers").performSemanticsAction(SemanticsActions.OnClick)
        compose.waitForIdle()
        assertEquals(listOf(parcel.id), source.restored)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
        compose.card("New trainers").assertExists()
    }

    @Test
    fun cardsStayOpenTogetherAndATapShutsOnlyItsOwn() {
        show()
        val trainers = left("New trainers")
        val gift = left("Birthday gift")
        drag("New trainers", distance = -60f)
        drag("Birthday gift", distance = -60f)
        assertEquals(2, openActions())

        // Scrolling leaves them open.
        compose.onNodeWithTag(PARCEL_LIST).performTouchInput {
            down(Offset(dp(30f), height / 2f))
            repeat(6) { moveBy(Offset(0f, -dp(8f))) }
            up()
        }
        compose.waitForIdle()
        assertEquals(2, openActions())

        // A tap on an open card shuts it rather than opening the parcel; the other stays open.
        compose.card("New trainers").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_PAGE).assertDoesNotExist()
        assertEquals(trainers, left("New trainers"), 0.5f)
        assertEquals(gift - 88f, left("Birthday gift"), 1f)
        assertEquals(1, openActions())

        // Shut, a tap opens the parcel.
        compose.card("New trainers").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
    }

    @Test
    fun theOpenActionArchivesTheParcelWhenPressed() {
        show()
        drag("Birthday gift", distance = -60f)
        compose.onNode(hasTestTag(ARCHIVE_ACTION) and hasClickAction()).assertContentDescriptionEquals("Archive").performClick()
        compose.waitForIdle()
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.archived)
        undoShown("Birthday gift").assertExists()
    }

    @Test
    fun talkBackArchivesThroughTheCardsActionAndPastDeliveriesOfferItToo() {
        show()
        val actions = compose.card("Birthday gift").fetchSemanticsNode().config.getOrNull(SemanticsActions.CustomActions).orEmpty()
        val archive = actions.single { it.label == "Archive" }
        compose.runOnUiThread { archive.action() }
        compose.waitForIdle()
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.archived)

        // Next up's card swipes too, and a past delivery's.
        for (name in listOf("New trainers", "Matcha ritual")) {
            compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed(name))
            val labels = compose.card(name).fetchSemanticsNode().config.getOrNull(SemanticsActions.CustomActions).orEmpty().map { it.label }
            assertEquals(name, listOf("Archive"), labels)
        }
    }

    @Test
    fun underReduceMotionTheCardStillOpensAndArchives() {
        show(reduceMotion = true)
        val rest = left("Birthday gift")
        drag("Birthday gift", distance = -60f)
        assertEquals(rest - 88f, left("Birthday gift"), 1f)
        drag("Birthday gift", distance = -230f, steps = 20)
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.archived)
    }

    @Test
    fun anArchiveThatFailsBringsTheCardBackAndSaysWhy() {
        show()
        source.failure = IllegalStateException("No connection")
        val rest = left("Birthday gift")
        drag("Birthday gift", distance = -230f, steps = 20)
        assertEquals(emptyList<Any>(), source.archived)
        compose.onNodeWithTag(ACTION_ERROR).assertExists()
        compose.onNode(hasText("No connection", substring = true)).assertExists()
        assertEquals(rest, left("Birthday gift"), 0.5f)
        compose.onNodeWithTag(ACTION_ERROR_CLOSE).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(ACTION_ERROR).assertDoesNotExist()
    }

    @Test
    fun anArchiveUnderWayFinishesThoughTheTabIsLeftAndItsUndoKeepsItsSevenSeconds() {
        show()
        val held = CompletableDeferred<Unit>()
        source.archiving = held
        compose.mainClock.autoAdvance = false
        compose.card("Birthday gift").performTouchInput { slide(-230f, steps = 20, hold = 200) }
        compose.mainClock.advanceTimeByFrame()
        // Another tab at once, the card still on its way out, then the store answers. (The tabs are pressed as TalkBack
        // does, the clock held still.)
        compose.onNodeWithContentDescription("Friends").performSemanticsAction(SemanticsActions.OnClick)
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(1000)
        compose.onNodeWithTag("friends").assertExists()
        assertEquals(emptyList<Any>(), source.archived)
        held.complete(Unit)
        compose.mainClock.advanceTimeBy(1000)
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.archived)

        compose.onNodeWithContentDescription("Deliveries").performSemanticsAction(SemanticsActions.OnClick)
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(500)
        compose.onNode(cardNamed("Birthday gift") and hasAnyAncestor(hasTestTag(PARCEL_LIST))).assertDoesNotExist()
        undoShown("Birthday gift").assertExists()
        // Seven seconds from the archive, not from coming back.
        compose.mainClock.advanceTimeBy(4600)
        undoShown("Birthday gift").assertExists()
        compose.mainClock.advanceTimeBy(1000)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
    }

    @Test
    fun anArchiveUnderWayFinishesThoughTheWindowIsMadeAgain() {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        val recreation = Recreation(compose)
        recreation.setContent { RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false) }
        compose.waitForIdle()
        val held = CompletableDeferred<Unit>()
        source.archiving = held
        compose.card("Birthday gift").fetchSemanticsNode().config[SemanticsActions.CustomActions].single { it.label == "Archive" }
            .let { action -> compose.runOnUiThread { action.action() } }
        compose.waitForIdle()
        recreation.recreate()
        held.complete(Unit)
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertEquals(listOf(DemoCards.parcel("Birthday gift").id), source.archived)
        compose.onNode(cardNamed("Birthday gift") and hasAnyAncestor(hasTestTag(PARCEL_LIST))).assertDoesNotExist()
        undoShown("Birthday gift").assertExists()
    }
}
