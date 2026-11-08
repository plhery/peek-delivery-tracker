package com.plhery.peekdeliverytracker.ui

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.platform.InspectableValue
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_SAYS
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_NOT_NOW
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_ON
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_TURN_ON
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_FACTS
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_IDENTITY
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_PASS
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_PICKUP
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_QUIET
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * How the delivery email's offer moves on a delivered parcel's page (B-AN01), on this device's own window and the
 * test's clock, stopped and moved on a frame at a time, where the window stacks the page over the list as where it lays
 * them out side by side. Turn on eases the card into the line saying the email is on, the area taking the line's height
 * in 0.2 s on an ease in and out; Not now fades the card out in 0.1 s on an ease out while the area folds away in
 * 0.2 s on an ease in and out; the rows under the area follow it all the way, as the iPhone's column animates
 * (`DeliveryEmailOffer`, `leaving`). Under Remove animations each change is there at once. Signed in as the synthetic
 * reader@example.com, who never chose the email, on fake deliveries ([FakeDeliveries], [ParcelWalk]), which save each
 * answer on the window's next frame; nothing goes to the network.
 */
@RunWith(AndroidJUnit4::class)
class EmailOfferMotionDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val delivered: Parcel get() = DemoCards.parcels().first { it.currentStage == TrackingStage.Delivered && it.archivedAt == null }

    /**
     * B-AN01: Turn on eases the card into the line saying the email is on: the area takes the line's height in 0.2 s on
     * an ease in and out, slow to start and slow to end, and the rows under it follow; then the line stays, the card
     * gone.
     */
    @Test
    fun turnOnEasesTheCardIntoTheLineOverAFifthOfASecondTheRowsUnderItFollowing() {
        walk.reduceMotion = false
        val (before, seen) = answer(PAGE_EMAIL_OFFER_TURN_ON)
        val line = checkNotNull(place(PAGE_EMAIL_OFFER_ON)) { "The line saying the email is on" }
        val end = seen.last().second.gap
        assertEquals("The rows under it rise by the card's height less the line's", before.card.height - line.height, before.gap - end, 1f)
        val start = started(seen, before.gap)
        assertEased(seen.since(start) { (before.gap - it.gap) / (before.gap - end) })
        assertTurnedOn()
    }

    /**
     * B-AN01: Not now fades the card out in 0.1 s on an ease out, while the area, the card and the 12 above it, folds
     * away in 0.2 s on an ease in and out, the rows under it following: the card has gone well before its room has.
     */
    @Test
    fun notNowFadesTheCardInATenthOfASecondAsTheAreaFoldsAwayInAFifth() {
        walk.reduceMotion = false
        val (before, seen) = answer(PAGE_EMAIL_OFFER_NOT_NOW, inked = true)
        val end = seen.last().second.gap
        assertEquals("The rows under it rise by the card and the 12 above it", before.card.height + OFFER_SPACING, before.gap - end, 1f)
        val start = started(seen, before.gap)
        val folding = seen.since(start) { (before.gap - it.gap) / (before.gap - end) }
        assertEased(folding)

        assertTrue("The card's title is drawn before the answer", before.ink > 0)
        val fading = seen.since(start) { it.ink.toFloat() / before.ink }
        assertTrue("The card is fading 48 ms in: $fading", fading.near(48) in 0.1f..0.6f)
        fading.filter { it.first >= FADED_MILLIS }.forEach { (at, ink) -> assertTrue("The card has faded by $at ms: $fading", ink <= 0.05f) }
        assertTrue("The area still folds as the card has faded: $folding", folding.near(FADED_MILLIS) < 0.8f)
        assertDeclined()
    }

    /** B-AN01 under Remove animations: Turn on puts the line in the card's place at once, the rows under it with it. */
    @Test
    fun underRemoveAnimationsTurnOnShowsTheLineAtOnce() {
        walk.reduceMotion = true
        val (before, seen) = answer(PAGE_EMAIL_OFFER_TURN_ON)
        val line = checkNotNull(place(PAGE_EMAIL_OFFER_ON)) { "The line saying the email is on" }
        val end = seen.last().second.gap
        assertEquals("The rows under it rise by the card's height less the line's", before.card.height - line.height, before.gap - end, 1f)
        assertAtOnce(seen, before.gap, end)
        assertTurnedOn()
    }

    /** B-AN01 under Remove animations: Not now takes the area away at once, the rows under it rising with it. */
    @Test
    fun underRemoveAnimationsNotNowTakesTheAreaAwayAtOnce() {
        walk.reduceMotion = true
        val (before, seen) = answer(PAGE_EMAIL_OFFER_NOT_NOW)
        val end = seen.last().second.gap
        assertEquals("The rows under it rise by the card and the 12 above it", before.card.height + OFFER_SPACING, before.gap - end, 1f)
        assertAtOnce(seen, before.gap, end)
        assertDeclined()
    }

    /** The email turned on: the line stays in the card's place, and the answer was counted and saved once. */
    private fun assertTurnedOn() {
        assertTrue(walk.exists(PAGE_EMAIL_OFFER_ON))
        assertFalse(walk.exists(PAGE_EMAIL_OFFER))
        assertEquals(listOf(true), walk.onUi { walk.source.emailChoices.toList() })
        assertEquals(listOf("email-offer-accept"), offers())
    }

    /** The offer declined: neither the card nor the line is left, and the answer was counted and saved once. */
    private fun assertDeclined() {
        assertFalse(walk.exists(PAGE_EMAIL_OFFER))
        assertFalse(walk.exists(PAGE_EMAIL_OFFER_ON))
        assertEquals(listOf(false), walk.onUi { walk.source.emailChoices.toList() })
        assertEquals(listOf("email-offer-decline"), offers())
    }

    private fun offers(): List<String> = walk.onUi { walk.source.actions.filter { it.startsWith("email-offer") } }

    /**
     * The delivered parcel's page with the offer, scrolled to it and at rest, then the test's clock stopped; `button`
     * tapped, and frame by frame for [SETTLED] after the tap: how far under the pass the row after the area stands,
     * and, where `inked`, how much of the card's title is drawn. What stood before the tap comes first, at -1 ms.
     */
    private fun answer(button: String, inked: Boolean = false): Pair<Offer, List<Pair<Long, Moment>>> {
        walk.show(DemoCards.parcels()) { signedInWithEmail(null) }
        walk.open(delivered)
        walk.node(PAGE_EMAIL_OFFER).performScrollTo()
        walk.node(button).performScrollTo()
        walk.settle()
        compose.mainClock.autoAdvance = false
        val under = checkNotNull(ROWS.firstOrNull { walk.count(it) == 1 }) { "A row under the offer" }
        val card = checkNotNull(place(PAGE_EMAIL_OFFER)) { "The offer's card" }
        val before = Offer(card, gap(under), if (inked) titleInk() else 0)
        walk.node(button).performClick()
        val seen = frames(SETTLED) { Moment(gap(under), if (inked) titleInk() else 0) }
        return before to (listOf(-1L to Moment(before.gap, before.ink)) + seen)
    }

    /**
     * How far under the pass's card the row `under` stands, in dp: the offer's area and the column's spacing, as the
     * page scrolls or not.
     */
    private fun gap(under: String): Float {
        val row = checkNotNull(place(under)) { "The row $under" }
        val pass = checkNotNull(place(PAGE_PASS)) { "The pass" }
        return row.top - pass.bottom
    }

    /** Each frame's `read` from the frame at `start` on, in milliseconds since it. */
    private fun List<Pair<Long, Moment>>.since(start: Long, read: (Moment) -> Float): List<Pair<Long, Float>> =
        filter { it.first >= start }.map { (at, it) -> at - start to read(it) }

    private fun List<Pair<Long, Moment>>.gaps(): String = joinToString { (at, it) -> "$at: ${it.gap}" }

    /** The frame before the area first moved from `from`, in milliseconds since the tap: where its motion starts. */
    private fun started(seen: List<Pair<Long, Moment>>, from: Float): Long {
        val moved = seen.indexOfFirst { abs(it.second.gap - from) > MOVED }
        assertTrue("The area moved: ${seen.gaps()}", moved >= 1)
        return seen[moved - 1].first
    }

    /**
     * `progress` went from 0 to 1 as `.easeInOut(duration: 0.2)` does: slow to start (5 % 32 ms in, where an even pace
     * stands at 16 %), about half way 0.1 s in, slow to end (92 % 160 ms in), there by about 0.2 s, and never past it.
     */
    private fun assertEased(progress: List<Pair<Long, Float>>) {
        val curve = progress.joinToString { (at, it) -> "$at: $it" }
        assertTrue("Slow to start: $curve", progress.near(32) < 0.13f)
        assertTrue("About half way 0.1 s in: $curve", progress.near(96) in 0.35f..0.7f)
        assertTrue("Slow to end: $curve", progress.near(160) > 0.9f)
        val done = progress.firstOrNull { it.second > 0.995f }?.first
        assertTrue("There by about 0.2 s: $curve", done != null && done in 160L..224L)
        progress.forEach { (at, it) -> assertTrue("Never past it at $at ms: $curve", it in -0.01f..1.01f) }
        progress.filter { it.first >= done!! }.forEach { (at, it) -> assertEquals("Stays at $at ms: $curve", 1f, it, 0.006f) }
    }

    /** The area went from `from` to `to` from one frame to the next, a few frames after the tap, and stayed there. */
    private fun assertAtOnce(seen: List<Pair<Long, Moment>>, from: Float, to: Float) {
        val gaps = seen.gaps()
        seen.forEach { (at, it) -> assertTrue("Nothing in between at $at ms: $gaps", abs(it.gap - from) < STILL || abs(it.gap - to) < STILL) }
        val moved = seen.first { abs(it.second.gap - from) >= STILL }.first
        assertTrue("At once after the tap: $gaps", moved <= AT_ONCE_MILLIS)
        seen.filter { it.first >= moved }.forEach { (at, it) -> assertEquals("Stays at $at ms: $gaps", to, it.gap, STILL) }
    }

    /** Frame by frame on the stopped clock for `millis`: what `read` saw now and after each frame, in ms since. */
    private fun <T> frames(millis: Long, read: () -> T): List<Pair<Long, T>> {
        val since = walk.now()
        val seen = mutableListOf(0L to read())
        while (walk.now() < since + millis) {
            compose.mainClock.advanceTimeByFrame()
            seen += walk.now() - since to read()
        }
        return seen
    }

    /** Where the one part tagged `tag` stands, in dp, as drawn; null while there is none. */
    private fun place(tag: String): Place? =
        compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().singleOrNull()?.let(::place)

    /**
     * The box `node` is drawn in, unclipped, in dp: the box its tag stands on, not the one inside its paddings that its
     * layout reports.
     */
    private fun place(node: SemanticsNode): Place = walk.onUi {
        val tagged = node.layoutInfo.getModifierInfo().firstOrNull { (it.modifier as? InspectableValue)?.nameFallback == "testTag" }
        val coordinates = tagged?.coordinates ?: node.layoutInfo.coordinates
        val box = coordinates.findRootCoordinates().localBoundingBoxOf(coordinates, clipBounds = false)
        val dp = compose.density.density
        Place(box.left / dp, box.top / dp, box.right / dp, box.bottom / dp)
    }

    /** The card's title. */
    private fun title(): SemanticsMatcher = hasText(walk.text("email.offer.title")) and hasAnyAncestor(hasTestTag(PAGE_EMAIL_OFFER))

    /**
     * How much of the card's title is drawn: how far each of its pixels stands from the paper behind it (the colour
     * most of them have), summed, so that the title at a third of its opacity counts a third. Nothing once it has gone,
     * or has folded out of sight.
     */
    private fun titleInk(): Int {
        val node = compose.onAllNodes(title(), useUnmergedTree = true).fetchSemanticsNodes().firstOrNull() ?: return 0
        if (node.boundsInRoot.width < 1f || node.boundsInRoot.height < 1f) return 0
        val image = compose.onNode(title(), useUnmergedTree = true).captureToImage().asAndroidBitmap()
        val pixels = IntArray(image.width * image.height)
        image.getPixels(pixels, 0, image.width, 0, 0, image.width, image.height)
        val paper = pixels.asIterable().groupingBy { it }.eachCount().maxBy { it.value }.key
        return pixels.sumOf { pixel ->
            maxOf(abs(Color.red(pixel) - Color.red(paper)), abs(Color.green(pixel) - Color.green(paper)), abs(Color.blue(pixel) - Color.blue(paper)))
        }
    }

    /** What stood before the tap: the offer's card, how far under the pass the row after it stood, its title's ink. */
    private class Offer(val card: Place, val gap: Float, val ink: Int)

    /** In a frame, how far under the pass the row after the offer's area stands, and how much of the title is drawn. */
    private class Moment(val gap: Float, val ink: Int)

    /** A part's bounds in the root, as drawn, in dp. */
    private data class Place(val left: Float, val top: Float, val right: Float, val bottom: Float) {
        val height get() = bottom - top
    }

    private companion object {
        /** How long each answer is followed for, a frame at a time: what moves has settled by then. */
        const val SETTLED = 700L

        /** The column's spacing above the offer (`PASS_SPACING`), which goes with it. */
        const val OFFER_SPACING = 12f

        /** How far the rows under the offer move, in dp, before the area counts as moving: less than a first frame. */
        const val MOVED = 0.25f

        /** How far from its place a row may stand, in dp, and still stand there: under a pixel. */
        const val STILL = 0.5f

        /** By when after the tap a change under Remove animations is there: a few frames, as the answer is saved. */
        const val AT_ONCE_MILLIS = 100L

        /** By when the card's 0.1 s fade has ended, past the frame it ends on. */
        const val FADED_MILLIS = 112L

        /** The rows the pass's column may have under the offer, any of which follows it. */
        val ROWS = listOf(PAGE_PICKUP, PAGE_CARRIER_SAYS, PAGE_QUIET, PAGE_IDENTITY, PAGE_FACTS)

        /** What was seen at the frame nearest `millis`. */
        fun <T> List<Pair<Long, T>>.near(millis: Long): T = minBy { abs(it.first - millis) }.second
    }
}
