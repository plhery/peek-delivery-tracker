package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.ReviewPrompt
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_DONE
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_POPOVER
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import java.time.Duration
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * When the list asks for a rating (A-ST36), on this device's own window and the test's clock, stopped and moved on a
 * frame at a time. A parcel seen arriving delivered is the moment: once its card has landed, and the list has stood
 * two seconds ([ReviewPrompt.settle]) looked at with nothing over it, the list launches Play's prompt and counts the
 * ask, once, as the iPhone asks StoreKit. The filters over the list (a sheet where the window stacks the page over it,
 * a popover where it lays them out side by side) put the ask off until two seconds after they close. Play's review
 * info is fetched while the moment settles; without it nothing is asked, and the moment waits for the list to be clear
 * again. The reader used the app on the two days before the captures' day, which the list counts as the third.
 * Signed in as the synthetic reader@example.com on fake deliveries ([FakeDeliveries], [ParcelWalk]), whose Play only
 * notes each step; nothing goes to the network.
 */
@RunWith(AndroidJUnit4::class)
class ReviewPromptDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val before: List<Parcel> = DemoCards.parcels()
    private val trainers: Parcel = DemoCards.parcel("New trainers")

    /** The demo's parcels, New trainers delivered at the captures' moment. */
    private val after: List<Parcel> = before.map { parcel ->
        if (parcel.id != trainers.id) return@map parcel
        val delivered = TrackingEvent(
            id = UUID.nameUUIDFromBytes("${parcel.id}-delivered".toByteArray()), packageId = parcel.id, stage = TrackingStage.Delivered,
            description = "Delivered", location = null, occurredAt = DateParser.isoString(CAPTURED),
        )
        parcel.copy(trackingEvents = parcel.trackingEvents + delivered)
    }

    /**
     * A-ST36: New trainers seen arriving with animations on. Nothing is due while its card is held for the postmark,
     * and nothing is asked while it flies to its new place; once it has landed, Play's review info is fetched, and two
     * seconds on, the list still clear, the prompt is launched and the ask counted. Once: nothing more is asked after,
     * and this version of the app asks no more.
     */
    @Test
    fun aParcelSeenArrivingAsksForARatingTwoSecondsAfterItsCardHasLanded() {
        walk.reduceMotion = false
        show()
        deliver()
        val held = frameUntil("The delivered card held for its postmark") { holding() }
        assertFalse("Nothing is due while the card is held", due())
        assertEquals(emptyList<String>(), calls())
        val asked = frameUntil("Play's review info fetched", LANDING_MILLIS) { calls().isNotEmpty() }
        assertTrue("Fetched once the postmark has been read: ${asked - held} ms", asked - held >= POSTMARK_READ_MILLIS)
        assertFalse("The card has been let go", holding())
        assertTrue("The card has landed", walk.onUi { walk.deliveries.flights.isEmpty() })
        assertTrue("The moment is due", due())

        walk.advanceTo(asked + SETTLE_MILLIS - SLACK_MILLIS)
        assertEquals("Nothing launched before two seconds", listOf(PREPARE), calls())
        assertEquals(0, asks())
        walk.advanceTo(asked + SETTLE_MILLIS + SLACK_MILLIS)
        assertEquals("Launched two seconds on", listOf(PREPARE, LAUNCH), calls())
        assertEquals(1, asks())
        assertFalse("The moment is taken", due())

        walk.advanceTo(walk.now() + LATER_MILLIS)
        assertEquals("Asked once", listOf(PREPARE, LAUNCH), calls())
        assertEquals(1, asks())
        assertFalse("This version asks no more", walk.onUi { walk.source.reviewPrompt.isDue(CAPTURED, calm = true) })
    }

    /**
     * A-ST36: the filters opened over the list a second into the wait put the ask off, a sheet where the window stacks
     * the page over the list and a popover where it lays them out side by side: nothing is launched while they show,
     * well past the two seconds, and the moment is kept. Closed with Done, the list fetches Play's review info again on
     * the next frame, and launches the prompt two seconds after, counting the ask once.
     */
    @Test
    fun theFiltersOverTheListPutTheAskOffUntilTwoSecondsAfterTheyClose() {
        walk.reduceMotion = false
        show()
        deliver()
        val asked = frameUntil("Play's review info fetched", LANDING_MILLIS) { calls().isNotEmpty() }
        walk.advanceTo(asked + SETTLE_MILLIS / 2)
        openFilters()
        walk.advanceTo(asked + SETTLE_MILLIS * 2)
        assertEquals("Nothing launched under the filters", listOf(PREPARE), calls())
        assertEquals(0, asks())
        assertTrue("The moment is kept", due())

        val again = closeFilters()
        walk.advanceTo(again + SETTLE_MILLIS - SLACK_MILLIS)
        assertEquals("Nothing launched before two seconds", listOf(PREPARE, PREPARE), calls())
        assertEquals(0, asks())
        walk.advanceTo(again + SETTLE_MILLIS + SLACK_MILLIS)
        assertEquals("Launched two seconds after the filters closed", listOf(PREPARE, PREPARE, LAUNCH), calls())
        assertEquals(1, asks())
        assertFalse("The moment is taken", due())
    }

    /**
     * A-ST36, Android's own step: without Play's review info (no Play Store, or one that isn't Google's) nothing is
     * launched or counted, and the moment and this version's ask are kept, as no ask reached the system; StoreKit needs
     * no such step. Under Remove animations the card takes its new place at once, and the moment counts all the same.
     * The list clear again after the filters, with Play's info there by then, the prompt is launched two seconds on.
     */
    @Test
    fun withoutPlaysReviewInfoNothingIsAskedAndTheMomentWaitsForTheListToBeClearAgain() {
        show { reviewInfo = false }
        deliver()
        val asked = frameUntil("Play's review info asked for", LANDING_MILLIS) { calls().isNotEmpty() }
        assertTrue("The moment is due", due())
        walk.advanceTo(asked + SETTLE_MILLIS + LATER_MILLIS)
        assertEquals("Nothing launched without the info", listOf(PREPARE), calls())
        assertEquals(0, asks())
        assertTrue("The moment is kept", due())
        assertTrue("This version keeps its ask", walk.onUi { walk.source.reviewPrompt.isDue(CAPTURED, calm = true) })

        walk.onUi { walk.source.reviewInfo = true }
        openFilters()
        val again = closeFilters()
        walk.advanceTo(again + SETTLE_MILLIS - SLACK_MILLIS)
        assertEquals("Nothing launched before two seconds", listOf(PREPARE, PREPARE), calls())
        walk.advanceTo(again + SETTLE_MILLIS + SLACK_MILLIS)
        assertEquals("Launched two seconds after the list was clear again", listOf(PREPARE, PREPARE, LAUNCH), calls())
        assertEquals(1, asks())
        assertFalse("The moment is taken", due())
    }

    /**
     * The demo's parcels in the reader's deliveries, the stores set up by `setUp`, after two days with use before the
     * captures' day; then the test's clock stopped.
     */
    private fun show(setUp: FakeDeliveries.() -> Unit = {}) {
        walk.show(before) {
            reviewPrompt.used(CAPTURED.minus(Duration.ofDays(2)))
            reviewPrompt.used(CAPTURED.minus(Duration.ofDays(1)))
            setUp()
        }
        compose.mainClock.autoAdvance = false
    }

    /** New trainers delivered, as the store tells the list. */
    private fun deliver() = walk.onUi { walk.source.parcels.value = after }

    /** Play's steps so far: `prepare` as its review info is fetched, `launch` as its prompt is shown. */
    private fun calls(): List<String> = walk.onUi { walk.source.reviewCalls.toList() }

    /** The asks counted. */
    private fun asks(): Int = walk.onUi { walk.source.actions.count { it == ASK } }

    /** Whether a parcel seen arriving made the moment to ask, which no ask has taken yet. */
    private fun due(): Boolean = walk.onUi { walk.deliveries.reviewDue }

    /** Whether the list holds a card just delivered in its place. */
    private fun holding(): Boolean = walk.onUi { !walk.deliveries.hold.isEmpty }

    /**
     * The filters opened from their button over the list, a sheet where the window stacks and a popover where it lays
     * the list and the page out side by side, and left a second to come up.
     */
    private fun openFilters() {
        val shape = if (walk.stacked()) FILTERS_SHEET else FILTERS_POPOVER
        compose.onNodeWithTag(FILTERS_BUTTON).performClick()
        frameUntil("The filters as $shape") { walk.exists(shape) }
        assertTrue(walk.onUi { walk.deliveries.showingFilters })
        walk.advanceTo(walk.now() + OPENING_MILLIS)
    }

    /** The filters closed with Done: the test's clock as Play's review info is fetched again, a frame or so after. */
    private fun closeFilters(): Long {
        val fetched = calls().size
        compose.onNodeWithTag(FILTERS_DONE).performClick()
        val again = frameUntil("Play's review info fetched again once the filters closed", CLOSING_MILLIS) { calls().size > fetched }
        assertFalse(walk.onUi { walk.deliveries.showingFilters })
        return again
    }

    /** Frame by frame on the stopped clock until `done` holds, for `millis` at most: the test's clock then. */
    private fun frameUntil(what: String, millis: Long = OPENING_MILLIS, done: () -> Boolean): Long {
        val end = walk.now() + millis
        while (!done()) {
            if (walk.now() >= end) throw AssertionError("$what within $millis ms")
            compose.mainClock.advanceTimeByFrame()
        }
        return walk.now()
    }

    private companion object {
        const val PREPARE = "prepare"
        const val LAUNCH = "launch"

        /** The analytics action an ask is counted with. */
        const val ASK = "review-ask"

        /** How long the moment settles before the ask. */
        val SETTLE_MILLIS = ReviewPrompt.settle.inWholeMilliseconds

        /** Three frames either side of the ask, for the frame the wait started in. */
        const val SLACK_MILLIS = 48L

        /** The postmark's 0.3 s and the card's 0.65 s of reading before it is let go, less a frame. */
        const val POSTMARK_READ_MILLIS = 930L

        /** Long enough for the card to be held, read and flown to its place. */
        const val LANDING_MILLIS = 10_000L

        /** Long enough for the filters to come up. */
        const val OPENING_MILLIS = 1_000L

        /** A few frames, for the list clear again once the filters close. */
        const val CLOSING_MILLIS = 250L

        /** Well past any ask. */
        const val LATER_MILLIS = 5_000L
    }
}
