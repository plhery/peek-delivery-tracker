package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.ParcelArrival
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import java.time.Duration
import java.util.UUID
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The arrival of the card Add added (B-S34, B-S35, B-AN17, B-AN18, B-AX11, B-AX18, A-AN13): once the list has brought
 * it to its middle, the card wobbles as six little parcels fly out of its stamp, with one tap of success; under Reduce
 * Motion it only brightens and back. Each way it ends early, the arrival ended only where it is still the card's own.
 * On synthetic lamps, signed in, read a frame at a time on the frame clock.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ArrivalCelebrationTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private lateinit var feeling: TappedView
    private val reduceMotion = mutableStateOf(false)
    private val bursts = mutableMapOf<UUID, ParcelBurstFrame>()
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))
    private val added = UUID.fromString("00000000-0000-4000-8000-000000000799")
    private val success = hapticFeedbackConstant(SensoryFeedback.Success)

    private fun lamp(index: Int, due: Int? = null) = Parcel(
        id = UUID(0, 1000L + index), trackingNumber = "TESTLAMP%04d".format(index), label = "Lamp $index",
        carrier = CarrierId.Unknown, createdAt = DateParser.isoString(CAPTURED.minus(Duration.ofMinutes(index.toLong()))),
        syncStatus = SyncStatus.Ok, notificationsMuted = false, expectedDelivery = due?.let { "2026-11-%02d".format(it) },
    )

    /**
     * Eight lamps due in November, the first Next up, then the card added, newest of the undated, then eight more: the
     * card added is out of sight below, with room after it to come to the list's middle.
     */
    private val long = (1..8).map { lamp(it, due = it) } + (9..16).map { lamp(it) }

    private fun show(list: List<Parcel> = long, dark: Boolean = false) {
        feeling = TappedView(compose.activity)
        source = FakeDeliveries(list, reader).apply { addedId = added }
        compose.setContent {
            CompositionLocalProvider(
                LocalView provides feeling,
                LocalDeliveriesState provides { deliveries = it },
                LocalParcelBursts provides { id, burst -> bursts[id] = burst },
            ) {
                RootHost(state = reader, dark = dark, reduceMotion = reduceMotion.value, source = source)
            }
        }
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun frame() {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        compose.mainClock.advanceTimeByFrame()
    }

    /** The clock moved a frame at a time until `done`, within `seconds`. */
    private fun framesUntil(seconds: Double = 3.0, done: () -> Boolean) {
        repeat((seconds * 60).toInt()) {
            if (done()) return
            frame()
        }
        if (!done()) throw AssertionError("Not done in $seconds s")
    }

    private fun shows(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun taps(): Int = feeling.played.count { it == success }

    /** Running shoes typed into Add and saved, the clock then held: it moves a frame at a time from here. */
    private fun saveRunningShoes() {
        compose.onNodeWithTag(ADD_BUTTON).performClick()
        settle()
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("1Z999AA10123456784")
        compose.onNodeWithTag(ADD_NAME).performClick()
        settle()
        compose.onNodeWithTag(ADD_NAME).performTextInput("Running shoes")
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(ADD_SAVE).performClick()
    }

    /** The arrival started on the card of `id` and drawn once, as a reveal ending starts it. */
    private fun celebrate(id: UUID): ParcelBurstFrame {
        compose.mainClock.autoAdvance = false
        bursts.remove(id)
        compose.runOnUiThread { deliveries.parcelBurst = id }
        frame()
        frame()
        return bursts.getValue(id)
    }

    /**
     * How far the middle of `name`'s card is below the list's, in pixels: the card itself, not the room above it in its
     * item, is what the reveal brings to the middle (`.id` is on the card).
     */
    private fun offCentre(name: String): Float {
        val card = compose.card(name).fetchSemanticsNode().layoutInfo.coordinates
        val middle = card.localToRoot(Offset(card.size.width / 2f, card.size.height / 2f)).y
        return middle - compose.onNodeWithTag(PARCEL_LIST).fetchSemanticsNode().boundsInRoot.center.y
    }

    /** Where each item the list shows stands, by its key, in pixels. */
    private fun standing(): Map<Any, Int> = deliveries.listState.layoutInfo.visibleItemsInfo.associate { it.key to it.offset }

    /**
     * The card of `id` revealed from where the list stands: how far the list moved at each frame until the card's
     * arrival starts, in pixels, up the screen as positive, each read from an item it showed in both frames.
     */
    private fun revealMoves(id: UUID): List<Float> {
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { deliveries.revealParcel = id }
        val moves = mutableListOf<Float>()
        var before = standing()
        repeat(180) {
            if (deliveries.parcelBurst == id) return moves
            frame()
            val now = standing()
            val seen = now.keys.firstOrNull { it in before } ?: throw AssertionError("The list jumped past all it showed, after $moves")
            moves += (before.getValue(seen) - now.getValue(seen)).toFloat()
            before = now
        }
        throw AssertionError("No arrival in 3 s, after $moves")
    }

    /** One scroll of the list, `up` the screen or down it: one way only, frame by frame, with no jump. */
    private fun assertOneScroll(moves: List<Float>, up: Boolean) {
        val total = moves.sum()
        assertTrue("Scrolled $total px: $moves", if (up) total > 400 else total < -400)
        for (move in moves) {
            assertTrue("A move of $move px against the scroll: $moves", if (up) move >= 0 else move <= 0)
            assertTrue("A jump of $move px in $total: $moves", abs(move) <= abs(total) * 0.2f)
        }
        // Over the smooth 0.35 s, not at once.
        assertTrue("Over ${moves.count { it != 0f }} frames: $moves", moves.count { it != 0f } >= 12)
    }

    /** The window's pixels in `bounds`, drawn now in software, as [drawn] draws a node's. */
    private fun pixels(bounds: Rect): IntArray {
        val root = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        val whole = Bitmap.createBitmap(root.width, root.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { root.draw(Canvas(whole)) }
        val left = bounds.left.roundToInt()
        val top = bounds.top.roundToInt()
        val width = bounds.width.roundToInt()
        val height = bounds.height.roundToInt()
        return IntArray(width * height).also { whole.getPixels(it, 0, width, left, top, width, height) }
    }

    /** Where the card's layer puts its corners and its middle, in the window's pixels. */
    private class Pose(val topLeft: Offset, val topRight: Offset, val centre: Offset, val width: Float) {
        val degrees: Double get() = atan2((topRight.y - topLeft.y).toDouble(), (topRight.x - topLeft.x).toDouble()) * 180 / PI
        val scale: Double get() = hypot((topRight.x - topLeft.x).toDouble(), (topRight.y - topLeft.y).toDouble()) / width
    }

    private fun pose(name: String): Pose {
        val coordinates = compose.card(name).fetchSemanticsNode().layoutInfo.coordinates
        val width = coordinates.size.width.toFloat()
        val height = coordinates.size.height.toFloat()
        return Pose(
            coordinates.localToRoot(Offset.Zero), coordinates.localToRoot(Offset(width, 0f)),
            coordinates.localToRoot(Offset(width / 2, height / 2)), width,
        )
    }

    private val density: Float get() = compose.activity.resources.displayMetrics.density

    private fun assertAt(expected: Offset, actual: Offset) {
        assertEquals(expected.x, actual.x, 0.01f)
        assertEquals(expected.y, actual.y, 0.01f)
    }

    /** The card stands as it was: no turn, no swell. */
    private fun assertStill(name: String) {
        val pose = pose(name)
        assertEquals(0.0, pose.degrees, 1e-6)
        assertEquals(1.0, pose.scale, 1e-6)
    }

    /** The card's layer at the wobble's progress (B-AN17): turned, swollen, and moved its offset turned and swollen. */
    private fun assertWobble(name: String, rest: Offset, progress: Double) {
        val pose = pose(name)
        val wobble = sin(progress * PI * 6) * (1 - progress)
        val degrees = wobble * 1.1
        val scale = 1 + sin(progress * PI) * 0.012
        val offset = wobble * 3 * density * scale
        assertEquals(degrees, pose.degrees, 1e-3)
        assertEquals(scale, pose.scale, 1e-5)
        assertEquals(rest.x + offset * cos(degrees * PI / 180), pose.centre.x.toDouble(), 0.01)
        assertEquals(rest.y + offset * sin(degrees * PI / 180), pose.centre.y.toDouble(), 0.01)
    }

    @Test
    fun savingInAddBringsTheCardToTheMiddleThenItsArrivalPlaysOnceWithATap() {
        show()
        saveRunningShoes()
        // Not before the list has brought the card to its middle.
        framesUntil {
            if (deliveries.parcelBurst == null) assertEquals(0, taps())
            deliveries.parcelBurst != null
        }
        assertEquals(added, deliveries.parcelBurst)
        assertNull(deliveries.revealParcel)
        assertEquals(0f, offCentre("Running shoes"), 1f)
        frame()
        frame()
        assertTrue(shows(PARCEL_BURST))
        assertEquals(1, taps())
        val burst = bursts.getValue(added)
        assertEquals(ParcelArrival.STUB_INSET, burst.stubInset, 0.0)
        // Over 0.95 s after its first frame, with no second tap.
        var before = 0.0
        framesUntil {
            if (deliveries.parcelBurst != null) before = burst.seconds
            deliveries.parcelBurst == null
        }
        assertTrue("Still on at $before s", before < 0.95)
        assertTrue("Over at ${burst.seconds} s", burst.seconds >= 0.95 && burst.seconds < 0.95 + 0.017)
        frame()
        assertFalse(shows(PARCEL_BURST))
        assertStill("Running shoes")
        assertEquals(1, taps())
    }

    @Test
    fun aCardAddedToAListTooShortToScrollHasItsArrival() {
        show(list = listOf(lamp(9)))
        saveRunningShoes()
        framesUntil { deliveries.parcelBurst != null }
        assertEquals(added, deliveries.parcelBurst)
        assertNull(deliveries.revealParcel)
        frame()
        frame()
        assertTrue(shows(PARCEL_BURST))
        assertEquals(1, taps())
    }

    @Test
    fun aCardAlreadyInTheMiddleHasItsArrivalWithNoScroll() {
        show()
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { deliveries.revealParcel = lamp(10).id }
        framesUntil { deliveries.parcelBurst != null }
        assertEquals(0f, offCentre("Lamp 10"), 1f)
        framesUntil { deliveries.parcelBurst == null }
        // Revealed again where it stands: the reveal returns at once, and the arrival plays all the same.
        val list = deliveries.listState
        val stood = list.firstVisibleItemIndex to list.firstVisibleItemScrollOffset
        compose.runOnUiThread { deliveries.revealParcel = lamp(10).id }
        frame()
        frame()
        assertEquals(lamp(10).id, deliveries.parcelBurst)
        assertNull(deliveries.revealParcel)
        assertEquals(stood, list.firstVisibleItemIndex to list.firstVisibleItemScrollOffset)
        frame()
        assertTrue(shows(PARCEL_BURST))
        assertEquals(2, taps())
    }

    @Test
    fun aCardOutOfViewBelowRisesIntoTheMiddleInOneScroll() {
        show()
        assertTrue(compose.onAllNodes(cardNamed("Lamp 11")).fetchSemanticsNodes().isEmpty())
        assertOneScroll(revealMoves(lamp(11).id), up = true)
        assertEquals(0f, offCentre("Lamp 11"), 1f)
        frame()
        frame()
        assertTrue(shows(PARCEL_BURST))
        assertEquals(1, taps())
    }

    @Test
    fun aCardOutOfViewAboveComesDownIntoTheMiddleInOneScroll() {
        show()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToIndex(deliveries.listState.layoutInfo.totalItemsCount - 1)
        settle()
        assertTrue(compose.onAllNodes(cardNamed("Lamp 4")).fetchSemanticsNodes().isEmpty())
        assertOneScroll(revealMoves(lamp(4).id), up = false)
        assertEquals(0f, offCentre("Lamp 4"), 1f)
        frame()
        frame()
        assertTrue(shows(PARCEL_BURST))
        assertEquals(1, taps())
    }

    @Test
    fun underReduceMotionACardOutOfViewComesToTheMiddleAtOnce() {
        reduceMotion.value = true
        show()
        val moves = revealMoves(lamp(11).id)
        // The frame the reveal starts on, after the one the list is drawn on.
        assertEquals(moves.toString(), 1, moves.count { it != 0f })
        assertEquals(0f, offCentre("Lamp 11"), 1f)
    }

    @Test
    fun underReduceMotionTheCardComesAtOnceAndNothingFlies() {
        reduceMotion.value = true
        show()
        saveRunningShoes()
        framesUntil {
            assertFalse(shows(PARCEL_BURST))
            deliveries.parcelBurst != null
        }
        assertEquals(0f, offCentre("Running shoes"), 1f)
        val rest = pose("Running shoes").centre
        frame()
        frame()
        assertEquals(1, taps())
        val burst = bursts.getValue(added)
        var before = 0.0
        framesUntil {
            assertFalse(shows(PARCEL_BURST))
            assertStill("Running shoes")
            assertAt(rest, pose("Running shoes").centre)
            if (deliveries.parcelBurst != null) before = burst.seconds
            deliveries.parcelBurst == null
        }
        // Over after 0.75 s.
        assertTrue("Still on at $before s", before < 0.75)
        assertTrue("Over at ${burst.seconds} s", burst.seconds >= 0.75 && burst.seconds < 0.75 + 0.017)
        assertEquals(1, taps())
    }

    @Test
    fun underReduceMotionTheCardBrightensAndBack() {
        reduceMotion.value = true
        show(dark = true)
        val card = compose.card("Lamp 2")
        val before = compose.drawn(card)
        val burst = celebrate(lamp(2).id)
        // Close to the pulse's top, 0.08 at 0.375 s.
        repeat(22) { frame() }
        val step = (sin(PI * burst.seconds / ParcelArrival.REDUCED_WOBBLE_DURATION) * 0.08 * 255).roundToInt()
        assertTrue("The pulse at ${burst.seconds} s adds $step", step >= 19)
        val during = compose.drawn(card)
        var brightened = 0
        var counted = 0
        for (index in before.indices) {
            val was = before[index]
            if (Color.alpha(was) != 255 || maxOf(Color.red(was), Color.green(was), Color.blue(was)) > 255 - step) continue
            counted++
            val now = during[index]
            val each = listOf(Color.red(now) - Color.red(was), Color.green(now) - Color.green(was), Color.blue(now) - Color.blue(was))
            if (each.all { abs(it - step) <= 1 }) brightened++
        }
        assertTrue("$brightened of $counted pixels brightened by $step", counted > 1000 && brightened > counted * 0.95)
        framesUntil { deliveries.parcelBurst == null }
        frame()
        assertTrue(before.contentEquals(compose.drawn(card)))
    }

    @Test
    fun underReduceMotionNextUpKeepsItsShadowAsItBrightens() {
        reduceMotion.value = true
        show()
        val card = compose.card("Lamp 1").fetchSemanticsNode().boundsInRoot
        // Under the card, above the next one: Next up's shadow and nothing else.
        val band = Rect(card.left, card.bottom + 2, card.right, card.bottom + 8 * density)
        val before = pixels(band)
        val shade = before.maxOf { Color.red(it) } - before.minOf { Color.red(it) }
        assertTrue("A shadow $shade deep", shade >= 4)
        val burst = celebrate(lamp(1).id)
        repeat(22) { frame() }
        assertTrue(burst.seconds in 0.3..0.45)
        // The shadow brightened with the card, by a little as it is so faint, rather than gone.
        val during = pixels(band)
        val most = before.indices.maxOf { index ->
            val was = before[index]
            val now = during[index]
            maxOf(abs(Color.red(now) - Color.red(was)), abs(Color.green(now) - Color.green(was)), abs(Color.blue(now) - Color.blue(was)))
        }
        assertTrue("A change of $most under the card", most <= 2)
    }

    @Test
    fun theCardWobblesFrameByFrame() {
        show()
        val rest = pose("Lamp 2").centre
        val burst = celebrate(lamp(2).id)
        repeat(8) { frame() }
        // Two frames a frame apart, on the frame clock.
        val first = burst.seconds
        assertWobble("Lamp 2", rest, first / ParcelArrival.WOBBLE_DURATION)
        frame()
        assertEquals(first + 0.016, burst.seconds, 1e-9)
        assertWobble("Lamp 2", rest, burst.seconds / ParcelArrival.WOBBLE_DURATION)
        assertNotEquals(first, burst.seconds)
        // Still once the wobble is done, as the little parcels keep flying.
        framesUntil { burst.seconds >= ParcelArrival.WOBBLE_DURATION }
        frame()
        assertStill("Lamp 2")
        assertAt(rest, pose("Lamp 2").centre)
        assertEquals(lamp(2).id, deliveries.parcelBurst)
    }

    @Test
    fun theCardsPlaceIsWhereItStandsNotWhereItWobbles() {
        show()
        for (name in listOf("Lamp 1", "Lamp 2")) {
            val id = long.first { it.label == name }.id
            val still = checkNotNull(deliveries.places.place(id)).frame
            celebrate(id)
            repeat(4) { frame() }
            assertNotEquals(0.0, pose(name).degrees, 1e-3)
            // What a delivery's flight starts from, its paper comes out of, and lands on. The name, measured where it is
            // drawn, follows the card, as on the iPhone.
            assertEquals(still, checkNotNull(deliveries.places.place(id)).frame)
            compose.runOnUiThread { deliveries.endArrival() }
            frame()
        }
    }

    @Test
    fun theLittleParcelsFlyOutOfEachCardsStamp() {
        show()
        for ((name, inset) in listOf("Lamp 1" to ParcelArrival.NEXT_STUB_INSET, "Lamp 2" to ParcelArrival.STUB_INSET)) {
            val id = long.first { it.label == name }.id
            val burst = celebrate(id)
            assertEquals(inset, burst.stubInset, 0.0)
            repeat(18) { frame() }
            repeat(2) {
                // Where each is drawn, on the card as it wobbles: its middle, its turn and its scale on the card's.
                val card = compose.card(name).fetchSemanticsNode().layoutInfo.coordinates
                val width = card.size.width / density.toDouble()
                val height = card.size.height / density.toDouble()
                val cardPose = pose(name)
                val pips = compose.onNodeWithTag(PARCEL_BURST, useUnmergedTree = true).fetchSemanticsNode().children
                assertEquals(6, pips.size)
                pips.forEachIndexed { index, node ->
                    val at = "$name, pip $index at ${burst.seconds} s"
                    val (x, y, turn, scale) = particle(index, burst.seconds, width, height, inset).toList()
                    val pip = node.layoutInfo.coordinates
                    val side = pip.size.width.toFloat()
                    assertEquals(at, IOS_PARCELS[index][3] * 1.25 * density, side.toDouble(), 0.5)
                    val middle = pip.localToRoot(Offset(side / 2, pip.size.height / 2f))
                    val expected = card.localToRoot(Offset((x * density).toFloat(), (y * density).toFloat()))
                    assertEquals(at, expected.x, middle.x, 0.05f)
                    assertEquals(at, expected.y, middle.y, 0.05f)
                    val drawn = Pose(pip.localToRoot(Offset.Zero), pip.localToRoot(Offset(side, 0f)), middle, side)
                    assertEquals(at, turn, drawn.degrees - cardPose.degrees, 1e-3)
                    assertEquals(at, scale, drawn.scale / cardPose.scale, 1e-4)
                }
                frame()
            }
            compose.runOnUiThread { deliveries.endArrival() }
            frame()
        }
    }

    @Test
    fun theLittleParcelsTakeNoTouchesAndTalkBackNeverReachesThem() {
        show()
        val read = compose.card("Lamp 2").fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)
        val burst = celebrate(lamp(2).id)
        repeat(18) { frame() }
        val node = compose.onNodeWithTag(PARCEL_BURST, useUnmergedTree = true).fetchSemanticsNode()
        // Nothing in it for TalkBack to read: its little parcels have no semantics, and it has only its tag.
        assertEquals(6, node.children.size)
        assertTrue(node.children.all { !it.config.iterator().hasNext() && it.children.isEmpty() })
        assertNull(node.config.getOrNull(SemanticsProperties.ContentDescription))
        assertNull(node.config.getOrNull(SemanticsProperties.Text))
        assertEquals(read, compose.card("Lamp 2").fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription))
        // A tap on the first little parcel, over the card, opens the card.
        val pip = burst.poses[0]
        assertTrue(pip.opacity > 0.9 && pip.y > 0)
        val corner = pose("Lamp 2").topLeft
        compose.onRoot().performTouchInput { click(Offset(corner.x + pip.x.toFloat() * density, corner.y + pip.y.toFloat() * density)) }
        framesUntil { shows(PARCEL_PAGE) }
        assertNull(deliveries.parcelBurst)
    }

    @Test
    fun aDragOfTheListEndsTheArrivalAtOnce() {
        show()
        celebrate(lamp(2).id)
        repeat(4) { frame() }
        assertNotEquals(0.0, pose("Lamp 2").degrees, 1e-3)
        compose.onNodeWithTag(PARCEL_LIST).performTouchInput {
            down(center)
            repeat(6) { moveBy(Offset(0f, -12f)) }
            up()
        }
        frame()
        assertNull(deliveries.parcelBurst)
        frame()
        assertFalse(shows(PARCEL_BURST))
        assertStill("Lamp 2")
        repeat(60) { frame() }
        assertEquals(1, taps())
    }

    @Test
    fun addTheFiltersTheAccountOrAPageOpeningEndTheArrival() {
        show()
        celebrate(lamp(2).id)
        compose.onNodeWithTag(ADD_BUTTON).performClick()
        frame()
        assertNull(deliveries.parcelBurst)
        repeat(60) { frame() }
        compose.onNodeWithTag(ADD_CLOSE).performClick()
        repeat(60) { frame() }

        celebrate(lamp(2).id)
        compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
        frame()
        assertNull(deliveries.parcelBurst)
        compose.runOnUiThread { deliveries.closeAccount() }
        repeat(60) { frame() }

        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        repeat(30) { frame() }
        celebrate(lamp(2).id)
        compose.onNodeWithTag(FILTERS_BUTTON).performClick()
        frame()
        assertNull(deliveries.parcelBurst)
        compose.runOnUiThread { deliveries.showingFilters = false }
        repeat(60) { frame() }

        celebrate(lamp(3).id)
        compose.card("Lamp 2").performClick()
        frame()
        assertNull(deliveries.parcelBurst)
        // A tap at each start, none as each ended.
        repeat(60) { frame() }
        assertEquals(4, taps())
    }

    @Test
    fun theWindowGoingToTheBackgroundEndsTheArrival() {
        show()
        celebrate(lamp(2).id)
        repeat(4) { frame() }
        compose.activityRule.scenario.moveToState(Lifecycle.State.STARTED)
        framesUntil(0.05) { deliveries.parcelBurst == null }
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        frame()
        frame()
        assertNull(deliveries.parcelBurst)
        assertFalse(shows(PARCEL_BURST))
        assertStill("Lamp 2")
        assertEquals(1, taps())
    }

    @Test
    fun theWindowLeavingTheFrontAsAddGoesDownForgetsTheCardItAdded() {
        show()
        saveRunningShoes()
        // Saved: Add has closed, and is on its way down.
        framesUntil { deliveries.addition == null }
        assertTrue(deliveries.additionClosing)
        compose.activityRule.scenario.moveToState(Lifecycle.State.STARTED)
        frame()
        frame()
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        // Back in front before Add has gone: once it has, the list shows nothing of the card.
        framesUntil {
            assertNull(deliveries.revealParcel)
            assertNull(deliveries.parcelBurst)
            !deliveries.additionClosing
        }
        repeat(60) {
            frame()
            assertNull(deliveries.revealParcel)
            assertNull(deliveries.parcelBurst)
        }
        assertEquals(0, taps())
    }

    @Test
    fun aCardLeavingTheListEndsOnlyItsOwnArrival() {
        show()
        celebrate(lamp(2).id)
        // Another card leaves: the arrival isn't its own, and goes on.
        assertTrue(compose.onAllNodes(cardNamed("Lamp 3")).fetchSemanticsNodes().isNotEmpty())
        compose.runOnUiThread { source.parcels.value = source.parcels.value.filter { it.id != lamp(3).id } }
        framesUntil { compose.onAllNodes(cardNamed("Lamp 3")).fetchSemanticsNodes().isEmpty() }
        repeat(3) { frame() }
        assertEquals(lamp(2).id, deliveries.parcelBurst)
        assertTrue(shows(PARCEL_BURST))

        // The card scrolled out of the list, without a drag: its arrival ends with it.
        compose.onNodeWithTag(PARCEL_LIST).performScrollToIndex(deliveries.listState.layoutInfo.totalItemsCount - 1)
        frame()
        frame()
        assertTrue(compose.onAllNodes(cardNamed("Lamp 2")).fetchSemanticsNodes().isEmpty())
        assertNull(deliveries.parcelBurst)
        assertEquals(1, taps())
    }

    @Test
    fun reduceMotionTurningOnEndsTheArrival() {
        show()
        celebrate(lamp(2).id)
        repeat(4) { frame() }
        assertTrue(shows(PARCEL_BURST))
        reduceMotion.value = true
        framesUntil(0.05) { deliveries.parcelBurst == null }
        frame()
        assertFalse(shows(PARCEL_BURST))
        assertStill("Lamp 2")
        assertEquals(1, taps())
    }

    /** iOS's `particle(_:elapsed:origin:spread:)`, written out again: x, y, turn, scale and opacity, in points. */
    private fun particle(index: Int, elapsed: Double, width: Double, height: Double, inset: Double): DoubleArray {
        val (spread, impulse, turn) = IOS_PARCELS[index]
        val delay = IOS_PARCELS[index][4]
        val progress = max(0.0, min(1.0, (elapsed - delay) / 0.8))
        val reach = min(width * 0.25, 80.0)
        return doubleArrayOf(
            width - inset + spread * (1 - (1 - progress).pow(2)) * reach,
            height / 2 - impulse * progress + 120 * progress * progress,
            turn * progress + sin(progress * PI * 2) * 8,
            min(1.0, progress / 0.12) * (1 - progress * 0.25),
            min(1.0, progress / 0.05) * min(1.0, (1 - progress) / 0.3),
        )
    }

    private companion object {
        /** `ParcelAddedBurst.parcels`: horizontal spread, upward impulse, rotation, size, launch delay. */
        val IOS_PARCELS = listOf(
            doubleArrayOf(-1.0, 90.0, -80.0, 19.0, 0.02), doubleArrayOf(-0.7, 145.0, 65.0, 24.0, 0.0),
            doubleArrayOf(-0.35, 115.0, -100.0, 21.0, 0.05), doubleArrayOf(0.0, 155.0, 85.0, 25.0, 0.03),
            doubleArrayOf(0.35, 120.0, -55.0, 20.0, 0.06), doubleArrayOf(0.6, 95.0, 100.0, 22.0, 0.01),
        )
    }
}

/** A view that keeps the haptics played through it. */
private class TappedView(context: Context) : View(context) {
    val played = mutableListOf<Int>()

    override fun performHapticFeedback(feedbackConstant: Int): Boolean {
        played += feedbackConstant
        return true
    }
}
