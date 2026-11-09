package com.plhery.peekdeliverytracker.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.os.SystemClock
import android.view.InputDevice
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel.Moment
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequest
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ADD_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_BUBBLE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_CHIP
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_NOT_QUITE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_REASON
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WORDS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelFeedbackWork
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.numberRowTag
import java.util.UUID
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sqrt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What a finger meets on the question's pills, its words sheet's chips and Add's number rows (B-G17), on this device's
 * own window, as the iPhone's FeedbackPill, `chip(_:)` and `numberRow` with their `TactileButtonStyle(scale:)`: a pill
 * is drawn 36 tall and takes a touch 4 above and below it (`.padding(.vertical, 4).contentShape(Rectangle())
 * .padding(.vertical, -4)`), a chip is 44 tall at least and a number row 56; a finger held on one gives it at 0.96, 0.97
 * and 0.98 (with the style's 0.94 opacity) and does nothing until it lifts, which acts once. A finger dragged off a chip
 * well past where either platform still holds the press does nothing; a long press acts once on lift, as a SwiftUI
 * Button's does. "feedback.addNote", a plain button, is 36 tall in a touch 44 tall. The touches measured are the
 * screen's own, sent through UiAutomation ([ScreenTouches], and a finger held here while the screen is photographed);
 * what only leads to a moment is TalkBack's click. How much a held button gives is read from the screen: the scale
 * about the button's centre that best maps the photograph at rest onto the one held, the press's opacity fitted out as
 * a straight line through the greys. Motion is on, as the scale needs; the device's own "Show taps" and pointer
 * location must be off. Signed in as the synthetic reader@example.com on fake deliveries whose stores answer every read
 * in memory, with the public UPS sample and a synthetic second number. Shots in touchtargets/<row>-<moment>.png.
 */
@RunWith(AndroidJUnit4::class)
class TouchTargetsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val touches = ScreenTouches(compose)
    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /** A parcel its carrier answers for, with its history: Pip asks about it. */
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")

    private fun px(dp: Dp): Float = with(compose.density) { dp.toPx() }

    /** Where what `matcher` finds first stands on the screen, in pixels, whole: what clips it left in. */
    private fun onScreen(matcher: SemanticsMatcher): Rect {
        val node = touches.node(matcher)
        return Rect(node.positionInWindow, node.size.toSize()).translate(touches.origin())
    }

    private fun onScreen(tag: String): Rect = onScreen(hasTestTag(tag))

    private fun assertTall(what: String, box: Rect, height: Dp) =
        assertEquals("$what: ${box.height} px for $height", px(height), box.height, PIXEL)

    private fun assertAtLeast(what: String, box: Rect, height: Dp) =
        assertTrue("$what: ${box.height} px, under $height", box.height >= px(height) - PIXEL)

    /** The window on the screen less the system's bars, the cutout and the keyboard: where a finger reaches the app. */
    private fun clear(): Rect {
        val inWindow = walk.onUi {
            val decor = compose.activity.window.decorView
            val insets = ViewCompat.getRootWindowInsets(decor)
                ?.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout() or WindowInsetsCompat.Type.ime())
                ?: Insets.NONE
            Rect(insets.left.toFloat(), insets.top.toFloat(), (decor.width - insets.right).toFloat(), (decor.height - insets.bottom).toFloat())
        }
        return inWindow.translate(touches.origin())
    }

    private fun within(box: Rect, area: Rect): Boolean =
        box.left >= area.left - 1f && box.top >= area.top - 1f && box.right <= area.right + 1f && box.bottom <= area.bottom + 1f

    /**
     * What has `tag` drawn whole, nothing clipping it, and standing where a finger reaches it. Its box as drawn is
     * read with and without the clips round it, not against its laid out size: a floating sheet lays its content out
     * across the window and draws it scaled into its inset panel, as UIKit does, so a chip in it is drawn smaller than
     * it is laid out.
     */
    private fun assertWithin(tag: String) {
        val node = touches.node(hasTestTag(tag))
        val (shown, whole) = walk.onUi {
            val coordinates = node.layoutInfo.coordinates
            val root = coordinates.findRootCoordinates()
            root.localBoundingBoxOf(coordinates, clipBounds = true) to root.localBoundingBoxOf(coordinates, clipBounds = false)
        }
        assertTrue("$tag drawn whole: $shown of $whole", abs(shown.width - whole.width) <= 1f && abs(shown.height - whole.height) <= 1f)
        val box = onScreen(tag)
        val clear = clear()
        assertTrue("$tag at $box within the window's clear area $clear", within(box, clear))
    }

    /**
     * What has `tag`, scrolled into the window clear of the tab bar ([reveal]), then on, by the innermost scroll that
     * holds it, until it stands inside that scroll's viewport and clear of the system's bars and the keyboard.
     */
    private fun revealClear(tag: String) {
        compose.reveal(tag)
        walk.settle()
        val scrolls = compose.onAllNodes(hasScrollAction() and hasAnyDescendant(hasTestTag(tag)), useUnmergedTree = true)
        if (scrolls.fetchSemanticsNodes().isNotEmpty()) {
            val viewport = scrolls.onLast().fetchSemanticsNode().boundsInWindow.translate(touches.origin())
            val room = clear().intersect(viewport).deflate(px(CLEAR_MARGIN))
            val box = onScreen(tag)
            val shift = when {
                box.bottom > room.bottom -> box.bottom - room.bottom
                box.top < room.top -> box.top - room.top
                else -> 0f
            }
            if (shift != 0f) {
                scrolls.onLast().performSemanticsAction(SemanticsActions.ScrollBy) { scroll -> scroll(0f, shift) }
                walk.settle()
            }
        }
        walk.awaitStill()
        assertWithin(tag)
    }

    /** No keyboard on the screen, none sliding: where none was up, at once. */
    private fun awaitNoKeyboard() {
        runCatching {
            compose.waitUntil(LONG_MILLIS) {
                walk.onUi { ViewCompat.getRootWindowInsets(compose.activity.window.decorView)?.isVisible(WindowInsetsCompat.Type.ime()) != true }
            }
        }
        walk.awaitStill()
    }

    /** A tap on what has `tag` as TalkBack's does, scrolled to first: what leads to the moment a test is about. */
    private fun press(tag: String) {
        compose.reveal(tag)
        walk.node(tag).performClick()
        walk.settle()
    }

    private fun work(): ParcelFeedbackWork = checkNotNull(walk.source.feedbacks.of(gift.id)) { "No question on the gift" }

    private fun moment(): Moment = walk.onUi { work().moment }

    private fun requests(): List<Pair<UUID, ParcelFeedbackRequest>> = walk.onUi { walk.source.feedbackRequests.toList() }

    private fun reasons(): List<ParcelFeedbackReason> = walk.onUi { work().page?.reasons } ?: ParcelFeedbackReason.entries

    private fun chosen(): Set<ParcelFeedbackReason> = walk.onUi { work().reasons }

    private fun selected(tag: String): Boolean? = walk.node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected)

    /** The gift's page, with motion, scrolled to Pip's question, which asks. */
    private fun openQuestion() {
        walk.reduceMotion = false
        walk.show(listOf(gift, lamp))
        walk.open(gift)
        walk.settle()
        revealClear(FEEDBACK_NOT_QUITE)
        walk.settle()
        assertEquals(Moment.Ask, moment())
    }

    /** Not quite, then `reason`, then Add a note: the words sheet with `reason` chosen. */
    private fun openWords(reason: ParcelFeedbackReason) {
        openQuestion()
        press(FEEDBACK_NOT_QUITE)
        walk.settle()
        press(FEEDBACK_REASON + reason.name)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_ADD_NOTE) }
        walk.settle()
        press(FEEDBACK_ADD_NOTE)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_WORDS_SHEET) }
        walk.settle()
        walk.settle()
        walk.awaitStill()
        assertEquals(setOf(reason), chosen())
    }

    /**
     * A finger put down at `at` and held: once its touch has reached the window and the test's clock has moved on past
     * the press's delay and its 0.16 s, short of a long press, `whileHeld` runs and the screen is shot as `moment`; the
     * finger lifts after, whatever happened. The shot, held.
     */
    private fun held(at: Offset, moment: String, whileHeld: () -> Unit): Bitmap {
        val finger = Finger(at)
        finger.down()
        try {
            SystemClock.sleep(FINGER_MILLIS)
            compose.mainClock.advanceTimeBy(PRESS_MILLIS)
            compose.waitForIdle()
            whileHeld()
            return walk.shot(moment, alsoDark = false)
        } finally {
            finger.up()
            compose.waitForIdle()
        }
    }

    /** That the content of `box` in `held` is `rest`'s scaled by `expected` about the box's centre. */
    private fun assertScale(expected: Float, rest: Bitmap, held: Bitmap, box: Rect) {
        val measured = scaleOf(rest, held, box)
        assertEquals("Scaled by $measured under the finger, for $expected", expected, measured, SCALE_TOLERANCE)
    }

    /**
     * How much the content of `box` (on the screen, in pixels) is scaled in `held` against `rest`, about the box's
     * centre: of the scales from [SCALE_LOWEST] to [SCALE_HIGHEST] a [SCALE_STEP] apart, the one under which each held
     * pixel best matches the rest's grey at the point it came from, up to the press's opacity (a straight line through
     * the greys, fitted for each scale), refined between the best three.
     */
    private fun scaleOf(rest: Bitmap, held: Bitmap, box: Rect): Float {
        val left = ceil(box.left).toInt()
        val top = ceil(box.top).toInt()
        val right = floor(box.right).toInt()
        val bottom = floor(box.bottom).toInt()
        check(left >= 0 && top >= 0 && right <= held.width && bottom <= held.height) { "$box beyond the screen's ${held.width}×${held.height}" }
        val margin = (0.05f * max(box.width, box.height)).roundToInt() + 3
        val before = Greys(rest, left - margin, top - margin, right + margin, bottom + margin)
        val after = Greys(held, left, top, right, bottom)
        val stride = max(1, ceil(sqrt((right - left).toDouble() * (bottom - top) / MAX_SAMPLES)).toInt())
        val steps = ((SCALE_HIGHEST - SCALE_LOWEST) / SCALE_STEP).roundToInt()
        val misfits = DoubleArray(steps + 1) { misfit(before, after, box.center, SCALE_LOWEST + it * SCALE_STEP, stride) }
        val best = misfits.indices.minBy { misfits[it] }
        if (best == 0 || best == steps) return SCALE_LOWEST + best * SCALE_STEP
        val bend = misfits[best - 1] - 2 * misfits[best] + misfits[best + 1]
        val shift = if (bend > 0) 0.5 * (misfits[best - 1] - misfits[best + 1]) / bend else 0.0
        return (SCALE_LOWEST + (best + shift) * SCALE_STEP).toFloat()
    }

    /**
     * What is left once `after`'s greys are fitted as a straight line through `before`'s at the points they come from
     * under `scale` about `centre`: the sum of the squares left over, every `stride`th pixel each way.
     */
    private fun misfit(before: Greys, after: Greys, centre: Offset, scale: Float, stride: Int): Double {
        var n = 0
        var sx = 0.0
        var sy = 0.0
        var sxx = 0.0
        var sxy = 0.0
        var syy = 0.0
        var y = after.top
        while (y < after.bottom) {
            var x = after.left
            while (x < after.right) {
                val from = before.sample(centre.x + (x + 0.5f - centre.x) / scale, centre.y + (y + 0.5f - centre.y) / scale).toDouble()
                val now = after.at(x, y).toDouble()
                n++
                sx += from
                sy += now
                sxx += from * from
                sxy += from * now
                syy += now * now
                x += stride
            }
            y += stride
        }
        val spreadX = sxx - sx * sx / n
        val spreadY = syy - sy * sy / n
        val together = sxy - sx * sy / n
        return if (spreadX <= 1e-9) spreadY else spreadY - together * together / spreadX
    }

    /**
     * The rows drawn in `box` down its middle column on `screen`: from the first to the last that differs from the
     * ground at the box's top, as pixel edges on the screen.
     */
    private fun drawnSpan(screen: Bitmap, box: Rect): Pair<Float, Float> {
        val x = box.center.x.toInt()
        val top = ceil(box.top).toInt()
        val bottom = floor(box.bottom).toInt()
        val ground = screen.getPixel(x, top + 1)
        val rows = (top until bottom).filter { differs(screen.getPixel(x, it), ground) }
        check(rows.isNotEmpty()) { "Nothing drawn in $box" }
        return rows.first().toFloat() to (rows.last() + 1).toFloat()
    }

    private fun differs(a: Int, b: Int): Boolean =
        max(abs(Color.red(a) - Color.red(b)), max(abs(Color.green(a) - Color.green(b)), abs(Color.blue(a) - Color.blue(b)))) > GROUND_TOLERANCE

    /**
     * B-G17: Yes and Not quite take a touch 44 tall, the pill drawn 36 tall in the middle of it, 4 from either end. A
     * finger held 2 above where Not quite is drawn gives it at 0.96 and answers nothing until it lifts, which turns to
     * the reasons once. Each reason takes a touch 44 tall at least, and a tap 2 above the touch's foot, under where the
     * pill is drawn, sends that reason.
     */
    @Test
    fun aPillIsDrawn36TallTakesATouch4AboveAndBelowAndGivesTo096UnderAHeldFinger() {
        openQuestion()
        assertTall("Yes to the touch", onScreen(FEEDBACK_YES), 44.dp)
        val touch = onScreen(FEEDBACK_NOT_QUITE)
        assertTall("Not quite to the touch", touch, 44.dp)
        val rest = walk.shot("B-G17-pill", alsoDark = false)
        val (top, bottom) = drawnSpan(rest, touch)
        assertEquals("Not quite drawn", px(36.dp), bottom - top, PIXEL)
        assertEquals("The touch over it", px(4.dp), top - touch.top, PIXEL)
        assertEquals("The touch under it", px(4.dp), touch.bottom - bottom, PIXEL)

        val pressed = held(Offset(touch.center.x, touch.top + px(2.dp)), "B-G17-pill-held") {
            assertEquals("Nothing answered while held", Moment.Ask, moment())
        }
        assertScale(0.96f, rest, pressed, touch)
        walk.settle()
        walk.settle()
        assertEquals("Not quite once lifted", Moment.Reasons, moment())
        assertTrue(requests().isEmpty())

        val shown = reasons()
        for (reason in shown) assertAtLeast("${reason.name} to the touch", onScreen(FEEDBACK_REASON + reason.name), 44.dp)
        val reason = shown.last()
        val tag = FEEDBACK_REASON + reason.name
        revealClear(tag)
        val pill = onScreen(tag)
        touches.tap(Offset(pill.center.x, pill.bottom - px(2.dp)))
        compose.waitUntil(LONG_MILLIS) { requests().isNotEmpty() }
        walk.settle()
        assertEquals(Moment.Sent, moment())
        assertEquals(listOf(reason), requests().single().second.reasons)
        walk.shot("B-G17-pill-below", alsoDark = false)
    }

    /**
     * B-G17: "feedback.addNote" stands in the room of 36 and takes a touch 44 tall, 4 past its room at the foot (inside
     * the bubble's 8), its words in the middle. A plain button: held 2 above the touch's foot it fades and gives nothing
     * of its size, and opens nothing until it lifts, which opens the words sheet.
     */
    @Test
    fun addANoteIsDrawn36TallInATouch44TallAndOpensTheWordsOnceLifted() {
        openQuestion()
        press(FEEDBACK_NOT_QUITE)
        walk.settle()
        press(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        compose.waitUntil(LONG_MILLIS) { moment() == Moment.Sent && walk.exists(FEEDBACK_ADD_NOTE) }
        walk.settle()
        walk.settle()
        revealClear(FEEDBACK_ADD_NOTE)
        val touch = onScreen(FEEDBACK_ADD_NOTE)
        assertTall("Add a note to the touch", touch, 44.dp)
        assertEquals("The touch 4 past its room, in the bubble's 8", px(4.dp), onScreen(FEEDBACK_BUBBLE).bottom - touch.bottom, PIXEL)
        val words = onScreen(hasText(walk.text("feedback.addNote")) and hasAnyAncestor(hasTestTag(FEEDBACK_ADD_NOTE)))
        assertEquals("Its words in the middle of the touch", touch.center.y, words.center.y, PIXEL)
        val rest = walk.shot("B-G17-add-note", alsoDark = false)

        val pressed = held(Offset(words.center.x, touch.bottom - px(2.dp)), "B-G17-add-note-held") {
            assertNull("No words sheet while held", walk.onUi { work().words })
            assertFalse(walk.exists(FEEDBACK_WORDS_SHEET))
        }
        assertScale(1f, rest, pressed, touch)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_WORDS_SHEET) }
        assertNotNull(walk.onUi { work().words })
    }

    /**
     * B-G17: each chip of the words sheet is 44 tall at least. A finger dragged off one, sideways, to well past where
     * either platform still holds the press, chooses nothing. Held on it, it gives at 0.97 and is not chosen until the
     * finger lifts, which chooses it once; a long press then takes it off once, as a tap does.
     */
    @Test
    fun aReasonChipIsAtLeast44TallGivesTo097AndTogglesOnceOnLiftButNotDraggedOff() {
        openWords(ParcelFeedbackReason.Steps)
        val shown = reasons()
        for (reason in shown) assertAtLeast("${reason.name}'s chip", onScreen(FEEDBACK_CHIP + reason.name), 44.dp)

        val clear = clear()
        val candidates = shown.filter { it != ParcelFeedbackReason.Steps }
            .map { it to onScreen(FEEDBACK_CHIP + it.name) }
            .filter { (_, box) -> within(box, clear) }
        check(candidates.isNotEmpty()) { "No chip but the chosen one stands where a finger reaches it" }
        val (reason, box) = candidates.maxBy { (_, place) -> max(place.left - clear.left, clear.right - place.right) }
        val tag = FEEDBACK_CHIP + reason.name
        val rightwards = clear.right - box.right >= box.left - clear.left
        val room = (if (rightwards) clear.right - box.right else box.left - clear.left) - px(CLEAR_MARGIN)
        val beyond = min(px(DRAG_BEYOND), room)
        assertTrue("Room to drag ${STILL_HELD_ON_IOS} past the chip: ${beyond / compose.density.density} dp", beyond >= px(STILL_HELD_ON_IOS))
        touches.drag(box.center, Offset((if (rightwards) 1f else -1f) * (box.width / 2 + beyond), 0f))
        walk.settle()
        assertNull("Dragged off, not chosen", selected(tag))
        assertEquals(setOf(ParcelFeedbackReason.Steps), chosen())

        assertWithin(tag)
        val chip = onScreen(tag)
        val rest = walk.shot("B-G17-chip", alsoDark = false)
        val pressed = held(chip.center, "B-G17-chip-held") {
            assertNull("Not chosen while held", selected(tag))
        }
        assertScale(0.97f, rest, pressed, chip)
        walk.settle()
        assertEquals("Chosen once lifted", true, selected(tag))
        assertEquals(setOf(ParcelFeedbackReason.Steps, reason), chosen())

        touches.longPress(onScreen(tag).center)
        walk.settle()
        assertNull("A long press takes it off once, as a tap does", selected(tag))
        assertEquals(setOf(ParcelFeedbackReason.Steps), chosen())
    }

    /**
     * B-G17: two numbers in Add's field are listed as rows 56 tall at least, the first chosen. Held on the second, it
     * gives at 0.98 and is not chosen until the finger lifts, which chooses it once; a long press on the first then
     * chooses it once, as a tap does.
     */
    @Test
    fun aNumberRowIsAtLeast56TallGivesTo098AndIsChosenOnceOnLift() {
        walk.reduceMotion = false
        walk.show(listOf(lamp))
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        walk.node(ADD_TRACKING).performTextReplacement("UPS $NUMBER\nUPS $SECOND")
        walk.settle()
        val first = numberRowTag(NUMBER)
        val second = numberRowTag(SECOND)
        compose.waitUntil(LONG_MILLIS) { walk.exists(first) && walk.exists(second) }
        walk.awaitKeyboard(required = false)
        walk.settle()
        assertAtLeast("The first number's row", onScreen(first), 56.dp)
        assertAtLeast("The second number's row", onScreen(second), 56.dp)
        assertEquals("The first chosen", true, selected(first))
        assertNull(selected(second))

        revealClear(second)
        val row = onScreen(second)
        val rest = walk.shot("B-G17-number", alsoDark = false)
        val pressed = held(row.center, "B-G17-number-held") {
            assertNull("Not chosen while held", selected(second))
            assertEquals(true, selected(first))
        }
        assertScale(0.98f, rest, pressed, row)
        walk.settle()
        // A number chosen ends the editing: the keyboard, where one was up, goes down before the first is measured.
        awaitNoKeyboard()
        assertEquals("Chosen once lifted", true, selected(second))
        assertNull(selected(first))

        revealClear(first)
        touches.longPress(onScreen(first).center)
        walk.settle()
        assertEquals("A long press chooses it once, as a tap does", true, selected(first))
        assertNull(selected(second))
    }

    /** A finger on the screen at `at`, put down and lifted as the screen's own touches come, as [ScreenTouches]'s. */
    private inner class Finger(private val at: Offset) {
        private var down = SystemClock.uptimeMillis()
        private val properties = MotionEvent.PointerProperties().apply {
            id = 0
            toolType = MotionEvent.TOOL_TYPE_FINGER
        }

        /** Down, put down again a little later while the screen refuses it, as [ScreenTouches]' finger is. */
        fun down() {
            val until = SystemClock.uptimeMillis() + ScreenTouches.DOWN_AGAIN_MILLIS
            while (!send(MotionEvent.ACTION_DOWN)) {
                check(SystemClock.uptimeMillis() < until) { "The screen refused the finger at $at" }
                SystemClock.sleep(ScreenTouches.FRAME_MILLIS * 6)
                down = SystemClock.uptimeMillis()
            }
        }

        fun up() {
            check(send(MotionEvent.ACTION_UP)) { "The screen refused the finger's lift at $at" }
        }

        private fun send(action: Int): Boolean {
            val where = MotionEvent.PointerCoords().apply {
                x = at.x
                y = at.y
                pressure = 1f
                size = 1f
            }
            val time = if (action == MotionEvent.ACTION_DOWN) down else SystemClock.uptimeMillis()
            val event = MotionEvent.obtain(
                down, time, action, 1, arrayOf(properties), arrayOf(where), 0, 0, 1f, 1f, 0, 0, InputDevice.SOURCE_TOUCHSCREEN, 0,
            )
            try {
                return instrumentation.uiAutomation.injectInputEvent(event, true)
            } finally {
                event.recycle()
            }
        }
    }

    /** The greys of `bitmap` over its pixels from `left`, `top` to `right`, `bottom` (not included), kept within it. */
    private class Greys(bitmap: Bitmap, left: Int, top: Int, right: Int, bottom: Int) {
        val left = left.coerceIn(0, bitmap.width - 1)
        val top = top.coerceIn(0, bitmap.height - 1)
        val right = right.coerceIn(this.left + 1, bitmap.width)
        val bottom = bottom.coerceIn(this.top + 1, bitmap.height)
        private val width = this.right - this.left
        private val values = FloatArray(width * (this.bottom - this.top)).also { values ->
            val pixels = IntArray(values.size)
            bitmap.getPixels(pixels, 0, width, this.left, this.top, width, this.bottom - this.top)
            for (i in pixels.indices) {
                val colour = pixels[i]
                values[i] = 0.299f * Color.red(colour) + 0.587f * Color.green(colour) + 0.114f * Color.blue(colour)
            }
        }

        /** The grey of the pixel at `x`, `y`, the nearest kept where it lies outside. */
        fun at(x: Int, y: Int): Float = values[(y.coerceIn(top, bottom - 1) - top) * width + (x.coerceIn(left, right - 1) - left)]

        /** The grey at the point `x`, `y` on the screen, between the four pixels whose centres stand around it. */
        fun sample(x: Float, y: Float): Float {
            val fx = x - 0.5f
            val fy = y - 0.5f
            val x0 = floor(fx).toInt()
            val y0 = floor(fy).toInt()
            val tx = fx - x0
            val ty = fy - y0
            val upper = at(x0, y0) * (1 - tx) + at(x0 + 1, y0) * tx
            val lower = at(x0, y0 + 1) * (1 - tx) + at(x0 + 1, y0 + 1) * tx
            return upper * (1 - ty) + lower * ty
        }
    }

    private companion object {
        const val FOLDER = "touchtargets"

        /** The public UPS sample, and a second synthetic UPS number beside it. */
        const val NUMBER = "1Z999AA10123456784"
        const val SECOND = "1Z999AA10123456793"

        /** How far a measured length may be off, in pixels: each of the parts it is laid out from rounded to the pixel. */
        const val PIXEL = 2f

        /**
         * How far a measured scale may be off: the fit of a shot to a shot comes within 0.008 of the press's scale on
         * these devices, the press's fade and the pixels' rounding as they are. It still tells 0.96 and 0.97 from the
         * buttons' 0.985.
         */
        const val SCALE_TOLERANCE = 0.01f
        const val SCALE_LOWEST = 0.93f
        const val SCALE_HIGHEST = 1.01f
        const val SCALE_STEP = 0.0025f

        /** The most pixels a scale is fitted on; a larger button is read every few pixels. */
        const val MAX_SAMPLES = 60_000.0

        /** How far a channel may stray from the ground before a pixel counts as drawn. */
        const val GROUND_TOLERANCE = 6

        /** The real time a touch takes to reach the window. */
        const val FINGER_MILLIS = 250L

        /**
         * The test's time a finger is held before the shot: past the press's delay in what scrolls (100 ms) and its
         * 0.16 s ease, short of a long press (400 ms), which nothing here has.
         */
        const val PRESS_MILLIS = 360L

        val CLEAR_MARGIN = 8.dp

        /** How far past a chip a finger is dragged off it, where there is room. */
        val DRAG_BEYOND = 96.dp

        /** Past this, UIKit lets go of a press a finger dragged off (about 70 points around the control). */
        val STILL_HELD_ON_IOS = 72.dp
    }
}
