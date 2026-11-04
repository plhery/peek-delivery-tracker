package com.plhery.peekdeliverytracker.ui

import android.graphics.Bitmap
import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.platform.InspectableValue
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_BACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_BUBBLE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_NOT_QUITE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_PIP
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_QUESTION
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_REASON
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WORD
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_LINK
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CONTENT
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * How the question under a parcel's page moves on this device's own window (B-AN22-B-AN30), on the test's clock, stopped
 * and moved on a frame at a time, where the parts stand after each frame: Pip popping up as the question first shows and
 * his bubble growing out of its tail after him; the row where no carrier was found coming in from below; Pip's hop at an
 * answer; what an answer brings rising into place as the bubble grows or shrinks to it on the moment's spring; the
 * reasons coming in one after another; the thanks' tick drawn in; the word at the page's foot rising from under it, and
 * its thanks fading in its place. Each again under Remove animations, where nothing moves but the bubble's size, eased.
 * Signed in on fake deliveries ([FakeDeliveries], [ParcelWalk]); nothing goes to the network, and the carrier's site is
 * kept from opening.
 */
@RunWith(AndroidJUnit4::class)
class ParcelFeedbackMotionDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /** A parcel its carrier answers for, with its history: Pip asks about it. */
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    /** A parcel no carrier was found for: the row asks about it. */
    private val lamp: Parcel get() = DemoCards.probable()

    /** B-AN24: Pip pops up as the question first shows, from 10 lower and at 90 %, past his place; his bubble grows out of its tail 0.15 s after, from 84 %, past its size. */
    @Test
    fun pipPopsUpAndHisBubbleGrowsOutOfItsTailAfterHim() {
        walk.reduceMotion = false
        val seen = openStopped(gift, FEEDBACK_QUESTION) { Asking(place(FEEDBACK_PIP), place(FEEDBACK_BUBBLE), place(FEEDBACK_QUESTION)) }
        val rest = seen.last().second
        val drawn = seen.filter { it.second.drawn }
        val came = drawn.indexOfFirst { rest.pipScale(it.second) > 0.902f }
        val curve = drawn.joinToString { (at, it) -> "$at: ${rest.pipScale(it)}/${rest.bubbleScale(it)}/${rest.drop(it)}" }
        assertTrue("Pip came in as he showed: $curve", came >= 1)
        val waiting = drawn[came - 1].second
        assertEquals("Pip waits at 90 %: $curve", 0.9f, rest.pipScale(waiting), 0.004f)
        assertEquals("Pip waits 10 lower: $curve", 10f, rest.drop(waiting), 0.6f)
        assertEquals("The bubble waits at 84 %: $curve", 0.84f, rest.bubbleScale(waiting), 0.004f)
        val grows = drawn.first { rest.bubbleScale(it.second) > 0.844f }.first
        assertTrue("The bubble grows 0.15 s after Pip comes: $curve", grows - drawn[came].first in 100L..210L)
        assertTrue("Pip pops past his size: $curve", drawn.maxOf { rest.pipScale(it.second) } > 1.004f)
        assertTrue("The bubble grows past its size: $curve", drawn.maxOf { rest.bubbleScale(it.second) } > 1.004f)
    }

    /** B-AN24 under Remove animations: Pip and his bubble stand in their place as the question shows. */
    @Test
    fun underRemoveAnimationsPipAndHisBubbleStandInPlaceAtOnce() {
        walk.reduceMotion = true
        val seen = openStopped(gift, FEEDBACK_QUESTION) { Asking(place(FEEDBACK_PIP), place(FEEDBACK_BUBBLE), place(FEEDBACK_QUESTION)) }
        val rest = seen.last().second
        val drawn = seen.filter { it.second.drawn }
        assertTrue(drawn.isNotEmpty())
        drawn.forEach { (at, it) ->
            assertEquals("Pip at $at", 1f, rest.pipScale(it), 0.003f)
            assertEquals("The bubble at $at", 1f, rest.bubbleScale(it), 0.003f)
            assertEquals("Pip's foot at $at", 0f, rest.drop(it), 0.3f)
        }
    }

    /** B-AN25: the row where no carrier was found comes in from 10 lower as it first shows, and settles in its place. */
    @Test
    fun theRowComesInFromBelowAsItShows() {
        walk.reduceMotion = false
        val seen = openStopped(lamp, FEEDBACK_ROW) { Rowing(place(FEEDBACK_ROW), place(PAGE_CONTENT)) }
        val rest = seen.last().second
        val drawn = seen.filter { it.second.drawn }
        val curve = drawn.joinToString { (at, it) -> "$at: ${rest.drop(it)}" }
        val came = drawn.indexOfFirst { rest.drop(it.second) < 9.8f }
        assertTrue("The row came in as it showed: $curve", came >= 1)
        assertEquals("The row waits 10 lower: $curve", 10f, rest.drop(drawn[came - 1].second), 0.6f)
        val settled = drawn.first { it.first >= drawn[came].first + 500 }.second
        assertTrue("The row has nearly settled half a second on: $curve", rest.drop(settled) < 1f)
        assertTrue("The row hardly goes past its place: $curve", drawn.minOf { rest.drop(it.second) } > -0.3f)
    }

    /**
     * B-AN23, B-AN26, B-AN29: Yes makes Pip hop 9 up and lean about his feet; his thanks rise 6 into place 0.06 s late,
     * their tick drawn in from 0.16 s.
     */
    @Test
    fun yesMakesPipHopAndHisThanksRiseInWithTheirTickDrawn() {
        walk.reduceMotion = false
        val seen = answerYes()
        val pip = seen.first().second
        val hop = seen.mapNotNull { (at, it) -> it.lift(pip)?.let { lift -> at to lift } }
        val pips = seen.map { (at, it) -> "$at: ${it.pip}" }
        val highest = hop.minBy { it.second }
        assertTrue("Pip hops 9 up: $hop $pips", highest.second in -10.5f..-7f)
        assertTrue("Pip is highest a fifth of a second in: $hop", highest.first in 140L..330L)
        assertEquals("Pip lands: $hop", 0f, hop.last().second, 0.4f)
        val leans = seen.maxOf { (_, it) -> (it.pip?.width ?: 0f) - pip.pip!!.width }
        assertTrue("Pip leans as he hops: $leans", leans >= 1.5f)

        val rise = seen.mapNotNull { (at, it) -> it.rise()?.let { rise -> at to rise } }
        val first = rise.first()
        assertTrue("The thanks come from 6 lower: $rise", first.second in 5f..6.6f)
        rise.filter { it.first <= first.first + 40 }.forEach { assertTrue("The thanks wait 0.06 s: $rise", it.second >= 5f) }
        assertEquals("The thanks rise into place: $rise", 0f, rise.last().second, 0.4f)

        val ink = seen.map { (at, it) -> at to it.ink }
        val drawn = ink.last().second.toFloat()
        assertTrue("The tick is there: $ink", drawn > 0)
        assertTrue("The tick waits 0.16 s: $ink", ink.near(170) <= drawn * 0.25f)
        assertTrue("The tick is being drawn 0.4 s in: $ink", ink.near(400) in (drawn * 0.35f).toInt()..(drawn * 0.93f).toInt())
        assertEquals("The tick is drawn by 0.6 s: $ink", drawn, ink.near(1000).toFloat(), drawn * 0.04f)
    }

    /** The same under Remove animations: Pip stays where he stands, his thanks fade in in place, their tick drawn at once. */
    @Test
    fun underRemoveAnimationsYesFadesInTheThanksWithTheirTick() {
        walk.reduceMotion = true
        val seen = answerYes()
        val pip = seen.first().second
        seen.forEach { (at, it) ->
            it.lift(pip)?.let { lift -> assertEquals("Pip at $at", 0f, lift, 0.4f) }
            it.pip?.let { place -> assertEquals("Pip's width at $at", pip.pip!!.width, place.width, 0.4f) }
            it.rise()?.let { rise -> assertEquals("The thanks at $at", 0f, rise, 0.4f) }
        }
        val ink = seen.map { (at, it) -> at to it.ink }
        val drawn = ink.last().second.toFloat()
        assertTrue("The tick is there: $ink", drawn > 0)
        ink.filter { it.first >= 220 }.forEach { assertTrue("The tick is drawn at once: $ink", it.second >= drawn * 0.9f) }
    }

    /**
     * B-AN22, B-AN28, B-AN27: Not quite grows the bubble to the reasons on the moment's spring, the reasons coming in
     * from 6 lower one after another; a reason given folds them away as the bubble shrinks to the thanks, and Pip hops.
     */
    @Test
    fun theReasonsComeInOneAfterAnotherAsTheBubbleGrowsThenFoldAwayToTheThanks() {
        walk.reduceMotion = false
        val (asked, grown) = offerReasons()
        val growth = grown.map { (at, it) -> at to it.bubble?.height }
        val tall = growth.last().second!!
        assertTrue("The reasons need more room: $growth", tall > asked.bubble!!.height + 20)
        val growing = growth.progress(asked.bubble.height, tall)
        assertTrue("The bubble grows on a spring: $growing", growing.near(100) in 0.1f..0.95f)
        assertTrue("The bubble is still settling 0.22 s in: $growing", growing.near(220) < 0.985f)

        val rest = grown.last().second
        assertLaidOutOnce(grown)
        val pair = (0 until REASONS - 1).first { abs(rest.pills[it]!!.top - rest.pills[it + 1]!!.top) < 0.5f }
        val lag = grown.mapNotNull { (at, it) -> it.lag(pair)?.let { lag -> at to lag } }
        assertTrue("Each reason comes after the one before it: $lag", lag.maxOf { it.second } >= 0.4f)
        val under = grown.mapNotNull { (at, it) -> it.under(0, rest)?.let { under -> at to under } }
        assertEquals("The first reason comes from 6 lower: $under", 6f, under.first().second, 0.5f)
        val last = grown.mapNotNull { (at, it) -> it.under(REASONS - 1, rest)?.let { under -> at to under } }
        assertEquals("The last reason waits 0.23 s: $last", 6f, last.near(200), 0.5f)
        assertEquals("The reasons come into place: $under", 0f, under.last().second, 0.3f)

        compose.mainClock.advanceTimeBy(500)
        // Folded, the grown bubble's last reasons stand under the window's foot.
        scrollToStopped(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        compose.mainClock.advanceTimeBy(700)
        val pip = Answered(place(FEEDBACK_PIP), place(FEEDBACK_BUBBLE), null, 0)
        walk.node(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name).performClick()
        val folded = frames(SETTLED) { Answered(place(FEEDBACK_PIP), place(FEEDBACK_BUBBLE), null, 0) }
        val shrinking = folded.map { (at, it) -> at to it.bubble?.height }
        val short = shrinking.last().second!!
        assertTrue("The thanks need less room: $shrinking", short < tall - 20)
        assertTrue("The bubble shrinks on a spring: $shrinking", shrinking.progress(tall, short).near(100) in 0.1f..0.95f)
        val hop = folded.mapNotNull { (at, it) -> it.lift(pip)?.let { lift -> at to lift } }
        assertTrue("Pip hops at a reason given: $hop", hop.minOf { it.second } <= -7f)
    }

    /** Under Remove animations the bubble still eases to the reasons, over 0.2 s, and they stand in their place at once. */
    @Test
    fun underRemoveAnimationsTheBubbleEasesToTheReasonsWhichStandInPlace() {
        walk.reduceMotion = true
        val (asked, grown) = offerReasons()
        val growth = grown.map { (at, it) -> at to it.bubble?.height }
        val tall = growth.last().second!!
        val growing = growth.progress(asked.bubble!!.height, tall)
        assertTrue("The bubble eases: $growing", growing.near(100) in 0.2f..0.8f)
        assertTrue("The bubble has eased by a quarter of a second: $growing", growing.near(260) > 0.995f)
        val rest = grown.last().second
        assertLaidOutOnce(grown)
        grown.forEach { (at, it) ->
            (0 until REASONS).forEach { order -> it.under(order, rest)?.let { under -> assertEquals("Reason $order at $at", 0f, under, 0.3f) } }
        }
    }

    /** B-AN30: back from the carrier's site the word rises from under the page's foot; its thanks fade in in its place. */
    @Test
    fun theWordRisesFromThePagesFootAndItsThanksFadeInItsPlace() {
        walk.reduceMotion = false
        val (rising, thanked) = askOnTheWayBack()
        val rest = rising.last().second!!
        val first = rising.first { it.second != null }
        assertTrue("The word rises from under its place: $rising", first.second!!.top - rest.top >= rest.height * 0.6f)
        thanked.forEach { (at, it) -> it?.let { place -> assertEquals("The thanks' foot at $at", rest.bottom, place.bottom, 0.5f) } }
    }

    /** B-AN30 under Remove animations: the word fades in where it stands. */
    @Test
    fun underRemoveAnimationsTheWordFadesInWhereItStands() {
        walk.reduceMotion = true
        val (rising, thanked) = askOnTheWayBack()
        val rest = rising.last().second!!
        (rising + thanked).forEach { (at, it) -> it?.let { place -> assertEquals("The word's foot at $at", rest.bottom, place.bottom, 0.5f) } }
        rising.forEach { (at, it) -> it?.let { place -> assertEquals("The word's top at $at", rest.top, place.top, 0.5f) } }
    }

    private fun show() = walk.show(listOf(gift, lamp))

    /** Each reason stands where it ends from the frame it first shows, its line broken and centred once, as the iPhone lays it out. */
    private fun assertLaidOutOnce(grown: List<Pair<Long, Offering>>) {
        val rest = grown.last().second
        val lines = grown.filter { (_, it) -> it.pills.all { pill -> pill != null } }.map { (at, it) -> "$at: ${it.pills.map { pill -> pill!!.left }}" }
        grown.forEach { (at, it) ->
            it.pills.forEachIndexed { order, pill -> pill?.let { assertEquals("Reason $order at $at: $lines", rest.pills[order]!!.left, it.left, 0.3f) } }
        }
    }

    /**
     * The page of `parcel` opened from its card on the stopped clock, then `tag` scrolled to, frame by frame: what `read`
     * saw after each, and last a second after, at rest. Where the window already shows the parcel's page beside the list,
     * the other's is opened first, so its page is made anew.
     */
    private fun <T> openStopped(parcel: Parcel, tag: String, read: () -> T): List<Pair<Long, T>> {
        show()
        if (walk.shown() == parcel.id) walk.open(if (parcel.id == gift.id) lamp else gift)
        compose.mainClock.autoAdvance = false
        val since = walk.now()
        walk.tapCard(parcel)
        val opening = frames(SETTLED, since, read)
        compose.waitUntil(LONG_MILLIS) { walk.count(tag) == 1 }
        scrollToStopped(tag)
        val scrolled = frames(SETTLED, since, read)
        compose.mainClock.advanceTimeBy(1_000)
        return opening + scrolled + (walk.now() - since to read())
    }

    /**
     * The page scrolled until `tag` shows whole, as a reader's swipe would, the scroll left to run on the stopped clock:
     * `performScrollTo` waits for a scroll that moves on its own frames, which never come.
     */
    private fun scrollToStopped(tag: String) {
        val scroll = compose.onNode(hasScrollAction() and hasAnyDescendant(hasTestTag(tag)), useUnmergedTree = true).fetchSemanticsNode()
        val part = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode()
        val under = part.positionInRoot.y + part.size.height - scroll.boundsInRoot.bottom
        if (under > 0) compose.runOnUiThread { scroll.config[SemanticsActions.ScrollBy].action?.invoke(0f, under + 16 * compose.density.density) }
    }

    /** The question asked, at rest, on the stopped clock. */
    private fun openQuestion() {
        show()
        walk.open(gift)
        compose.onNodeWithTag(FEEDBACK_QUESTION, useUnmergedTree = true).performScrollTo()
        walk.settle()
        compose.mainClock.autoAdvance = false
    }

    /** Yes on the page, frame by frame from just before, in milliseconds since. */
    private fun answerYes(): List<Pair<Long, Answered>> {
        openQuestion()
        val before = answered()
        walk.node(FEEDBACK_YES).performClick()
        return listOf(-1L to before) + frames(SETTLED) { answered() }
    }

    private fun answered() = Answered(place(FEEDBACK_PIP), place(FEEDBACK_BUBBLE), text(walk.text("feedback.pip.right")), tickInk())

    /** Not quite on the page: the bubble as asked, then the reasons frame by frame, in milliseconds since. */
    private fun offerReasons(): Pair<Answered, List<Pair<Long, Offering>>> {
        openQuestion()
        val asked = Answered(place(FEEDBACK_PIP), place(FEEDBACK_BUBBLE), null, 0)
        walk.node(FEEDBACK_NOT_QUITE).performClick()
        val whatsOff = walk.text("feedback.whatsOff")
        return asked to frames(SETTLED) {
            Offering(place(FEEDBACK_BUBBLE), text(whatsOff), ParcelFeedbackReason.entries.map { place(FEEDBACK_REASON + it.name) })
        }
    }

    /** Back from the carrier's site, the word frame by frame; then Yes on it, its thanks frame by frame. */
    private fun askOnTheWayBack(): Pair<List<Pair<Long, Place?>>, List<Pair<Long, Place?>>> {
        show()
        walk.open(gift)
        walk.tap(PAGE_CARRIER_LINK)
        compose.waitUntil(LONG_MILLIS) { walk.asked().isNotEmpty() }
        walk.awayAndBack()
        val rising = frames(SETTLED) { place(FEEDBACK_WORD) }
        walk.node(FEEDBACK_BACK_YES).performClick()
        return rising to frames(SETTLED) { place(FEEDBACK_WORD) }
    }

    /** Frame by frame on the stopped clock for `millis`: what `read` saw now and after each frame, in milliseconds `since`. */
    private fun <T> frames(millis: Long, since: Long = walk.now(), read: () -> T): List<Pair<Long, T>> {
        val end = walk.now() + millis
        val seen = mutableListOf(walk.now() - since to read())
        while (walk.now() < end) {
            compose.mainClock.advanceTimeByFrame()
            seen += walk.now() - since to read()
        }
        return seen
    }

    /** Where the one part tagged `tag` stands, in dp, as drawn; null while there is none. */
    private fun place(tag: String): Place? =
        compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().singleOrNull()?.let(::place)

    /** Where the words `text` stand in the bubble, in dp, as drawn; null while they aren't there. */
    private fun text(text: String): Place? {
        val words: SemanticsMatcher = hasText(text) and hasAnyAncestor(hasTestTag(FEEDBACK_BUBBLE))
        return compose.onAllNodes(words, useUnmergedTree = true).fetchSemanticsNodes().firstOrNull()?.let(::place)
    }

    /**
     * The box `node` is drawn in, its layers' moves, scales and turns and all, unclipped, in dp: the box its tag stands on
     * (a text's own), not the one inside its paddings that its layout reports. Its unclipped bounds in the root are where
     * its layers take its top leading corner, at its own size.
     */
    private fun place(node: SemanticsNode): Place = walk.onUi {
        val tagged = node.layoutInfo.getModifierInfo().firstOrNull { (it.modifier as? InspectableValue)?.nameFallback == "testTag" }
        val coordinates = tagged?.coordinates ?: node.layoutInfo.coordinates
        val box = coordinates.findRootCoordinates().localBoundingBoxOf(coordinates, clipBounds = false)
        val dp = compose.density.density
        Place(box.left / dp, box.top / dp, box.right / dp, box.bottom / dp)
    }

    /**
     * How much of the tick the bubble draws: its pixels unlike the paper where the thanks' tick stands, 14 to 30 in from
     * its leading side and 12 clear of its top and foot.
     */
    private fun tickInk(): Int {
        if (walk.count(FEEDBACK_BUBBLE) != 1) return 0
        val image: Bitmap = compose.onNodeWithTag(FEEDBACK_BUBBLE, useUnmergedTree = true).captureToImage().asAndroidBitmap()
        val dp = compose.density.density
        val paper = image.getPixel(image.width - (4 * dp).toInt(), image.height / 2)
        var ink = 0
        for (x in (13 * dp).toInt() until minOf(image.width, (31 * dp).toInt())) {
            for (y in (12 * dp).toInt() until image.height - (12 * dp).toInt()) if (unlike(image.getPixel(x, y), paper)) ink++
        }
        return ink
    }

    private fun unlike(pixel: Int, paper: Int): Boolean =
        maxOf(abs(Color.red(pixel) - Color.red(paper)), abs(Color.green(pixel) - Color.green(paper)), abs(Color.blue(pixel) - Color.blue(paper))) > 60

    /** A part's bounds in the root, as drawn, in dp. */
    private data class Place(val left: Float, val top: Float, val right: Float, val bottom: Float) {
        val width get() = right - left
        val height get() = bottom - top
        val middle get() = (top + bottom) / 2
    }

    /** Pip, his bubble and the question's row they stand in. */
    private class Asking(val pip: Place?, val bubble: Place?, val question: Place?) {
        val drawn get() = pip != null && bubble != null && question != null

        /** The page's own scale against this, at rest, from the question's row, which neither moves. */
        private fun page(other: Asking) = other.question!!.width / question!!.width

        fun pipScale(other: Asking) = (other.pip!!.width / other.question!!.width) / (pip!!.width / question!!.width)

        fun bubbleScale(other: Asking) = (other.bubble!!.width / other.question!!.width) / (bubble!!.width / question!!.width)

        /** How much lower Pip's feet stand than at rest, against the bubble's foot, which its growth doesn't move. */
        fun drop(other: Asking) = ((other.pip!!.bottom - other.bubble!!.bottom) - (pip!!.bottom - bubble!!.bottom)) / page(other)
    }

    /** The row where no carrier was found, and the page's content it stands at the foot of. */
    private class Rowing(val row: Place?, val content: Place?) {
        val drawn get() = row != null && content != null

        /** How much lower the row stands than at rest, against the content's foot, which scrolls with it. */
        fun drop(other: Rowing) = ((other.row!!.top - other.content!!.bottom) - (row!!.top - content!!.bottom)) / (other.row.width / row.width)
    }

    /** Pip, his bubble, the thanks' words, and how much of their tick is drawn. */
    private class Answered(val pip: Place?, val bubble: Place?, val thanks: Place?, val ink: Int) {
        /** How much higher Pip stands than `before`, against the bubble's foot, which its size doesn't move; up is less. */
        fun lift(before: Answered): Float? {
            if (pip == null || bubble == null) return null
            return (pip.middle - bubble.bottom) - (before.pip!!.middle - before.bubble!!.bottom)
        }

        /** How much lower the thanks stand than the middle of the bubble they are centred in. */
        fun rise(): Float? = if (thanks == null || bubble == null) null else thanks.middle - bubble.middle
    }

    /** The bubble, its question and each reason, in [ParcelFeedbackReason]'s order. */
    private class Offering(val bubble: Place?, val question: Place?, val pills: List<Place?>) {
        /** How much lower the reason after `order` stands than it, both on one line at rest. */
        fun lag(order: Int): Float? {
            val first = pills[order] ?: return null
            val second = pills[order + 1] ?: return null
            return second.top - first.top
        }

        /** How much lower the reason `order` stands under the question than at `rest`. */
        fun under(order: Int, rest: Offering): Float? {
            val pill = pills[order] ?: return null
            val asked = question ?: return null
            return (pill.top - asked.top) - (rest.pills[order]!!.top - rest.question!!.top)
        }
    }

    private companion object {
        /** How long each step is followed for, a frame at a time: what moves has settled by then. */
        const val SETTLED = 1_200L

        val REASONS = ParcelFeedbackReason.entries.size

        /** What was seen at the frame nearest `millis`. */
        fun <T> List<Pair<Long, T>>.near(millis: Long): T = minBy { abs(it.first - millis) }.second

        /** Each height as a share of the way from `from` to `to`. */
        fun List<Pair<Long, Float?>>.progress(from: Float, to: Float): List<Pair<Long, Float>> =
            mapNotNull { (at, height) -> height?.let { at to (it - from) / (to - from) } }
    }
}
