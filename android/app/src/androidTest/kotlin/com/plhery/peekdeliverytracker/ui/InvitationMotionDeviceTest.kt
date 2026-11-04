package com.plhery.peekdeliverytracker.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.platform.InspectableValue
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.FriendInvitationStore
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_ACCEPT
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_STAMP
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_WELCOME
import com.plhery.peekdeliverytracker.ui.arrival.LocalArrivalParcel
import com.plhery.peekdeliverytracker.ui.arrival.SIGN_IN
import com.plhery.peekdeliverytracker.ui.arrival.WELCOME_OPEN_PARCEL
import com.plhery.peekdeliverytracker.ui.arrival.signInSession
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.INVITATION_BASE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.friends.FakeFriendsClient
import com.plhery.peekdeliverytracker.ui.friends.FriendFixtures
import com.plhery.peekdeliverytracker.ui.friends.friendCardTag
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import java.util.UUID
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * A friend's invitation in motion (C-AN01, C-AN03 to C-AN05, C-AN07, C-AN08, C-AN10 to C-AN14, C-AN16) on this device's
 * own window, frame by frame on the test's stopped clock, as the iPhone plays it: the welcome's parcel greeting 250 ms
 * after it shows and every 4.6 s after (up 9 at 2.3 s, turned 1.1° clockwise, then back, turned 0.8° the other way),
 * pressed in on its spring and let go; tapped, opening over 0.95 s under its stars as "tap to open" fades, then 980 ms
 * on springing to the sign-in's anchor as the welcome fades out over 0.45 s, and the back springing it shut where the
 * welcome comes back; accepted, the receipt's stamp dropped onto it 250 ms after the receipt and the friend's card
 * landing from 100 to the right in the friends. Under Remove animations, each at once or faded where iOS says. What is
 * drawn is read off the window: the parcel's box from its ink against the paper, its frame from where the arrival says
 * it drew it ([LocalArrivalParcel]), the stamp and the card from their boxes through their layers. Nothing leaves the
 * device: the deliveries, friends and codes are fakes and made up.
 */
@RunWith(AndroidJUnit4::class)
class InvitationMotionDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val store = AppearanceStore.of(instrumentation.targetContext)
    private val appearanceBefore = store.appearance.value
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))
    private val localizer = Localizer(AppLanguage.En, variant = "android")

    /** The app's own preferences, and the invitation they had waiting before the test, if any. */
    private val preferences = PreferencesDefaults.standard(instrumentation.targetContext)
    private val pendingBefore = preferences.data(FriendInvitationStore.STORAGE_KEY)

    @After
    fun putBack() {
        store.set(appearanceBefore)
        val pending = pendingBefore
        if (pending != null) {
            preferences.set(pending, forKey = FriendInvitationStore.STORAGE_KEY)
        } else {
            preferences.removeObject(forKey = FriendInvitationStore.STORAGE_KEY)
        }
    }

    private lateinit var source: FakeDeliveries
    private lateinit var inbox: DeliveriesInbox

    /** Where the arrival last drew the parcel's frame, in its own pixels, and how far open. */
    @Volatile
    private var drawn: Pair<Rect, Float>? = null

    /**
     * The tabs of [session] edge to edge on the stopped clock, a parcel that doesn't pulse in the list, the window's links
     * through an inbox that knows the fakes' invitations; with [signIn] the real session store on a fake auth service.
     */
    private fun show(reduceMotion: Boolean, session: AuthenticationState = reader, signIn: Boolean = false) {
        store.set(AppAppearance.Light)
        source = FakeDeliveries(listOf(DemoCards.parcel("Birthday gift")), session)
        source.friendsClient.accepting()
        inbox = DeliveriesInbox(invitationLink = source.invitation::open)
        compose.waitsForMaps()
        // The welcome's parcel greets every 4.6 s for as long as it shows: the clock never runs out of frames by
        // itself then, so the test moves it on.
        compose.mainClock.autoAdvance = false
        compose.setContent {
            DisposableEffect(Unit) {
                compose.activity.edgeToEdge(false)
                onDispose {}
            }
            val scope = rememberCoroutineScope()
            val sessionStore = if (signIn) remember { signInSession(scope).also { it.showWelcome() } } else null
            CompositionLocalProvider(LocalArrivalParcel provides { rect, open -> drawn = rect to open }) {
                RootHost(state = session, reduceMotion = reduceMotion, source = source, inbox = inbox, session = sessionStore)
            }
        }
        advanceUntil("the tabs") { exists(TAB_BAR) }
        compose.mainClock.advanceTimeBy(1_000)
    }

    /** The service answering: the invitation can be accepted, and accepting it makes Sasha a friend. */
    private fun FakeFriendsClient.accepting() {
        answer = { request ->
            when (request.action) {
                FriendsAction.AcceptInvite -> {
                    snapshot = FriendFixtures.circle(listOf(sasha, FriendFixtures.ada), snapshot.profile)
                    FriendsActionResponse(snapshot = snapshot, acceptedFriend = sasha)
                }
                else -> FriendsActionResponse(snapshot = snapshot)
            }
        }
    }

    /**
     * The invitation's link to the window: the clock's time as its welcome first shows, where its parcel's greeting is
     * counted from; then, the sender known, 150 ms on, before the first greeting.
     */
    private fun follow(): Long {
        instrumentation.runOnMainSync { inbox.follow(Intent(Intent.ACTION_VIEW, Uri.parse(LINK))) }
        advanceUntil("the welcome", step = { compose.mainClock.advanceTimeByFrame() }) { exists(INVITATION_WELCOME) }
        val since = now()
        compose.mainClock.advanceTimeBy(150 - (now() - since))
        assertTrue("The sender is known 150 ms in", exists(WELCOME_OPEN_PARCEL))
        return since
    }

    private fun now(): Long = compose.mainClock.currentTime

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun advanceUntil(what: String, step: () -> Unit = { compose.mainClock.advanceTimeBy(100) }, done: () -> Boolean) {
        val start = now()
        while (!done()) {
            assertTrue("No $what after ${now() - start} ms", now() - start < 10_000)
            step()
        }
    }

    /** Frame by frame on the stopped clock for [millis]: what [read] saw now and after each frame, in milliseconds since [since]. */
    private fun <T> frames(millis: Long, since: Long = now(), read: () -> T): List<Pair<Long, T>> {
        val end = now() + millis
        val seen = mutableListOf(now() - since to read())
        while (now() < end) {
            compose.mainClock.advanceTimeByFrame()
            seen += now() - since to read()
        }
        return seen
    }

    private fun <T> onUi(read: () -> T): T {
        var value: Result<T>? = null
        instrumentation.runOnMainSync { value = runCatching(read) }
        return value!!.getOrThrow()
    }

    /** Where the arrival stands in the window, which its parcel's frame is counted from. */
    private fun origin(): Offset = compose.onNodeWithTag(ARRIVAL, useUnmergedTree = true).fetchSemanticsNode().positionInRoot

    /** The parcel's frame where it was last drawn, in the window's pixels, and how far open. */
    private fun parcel(): Pair<Rect, Float> {
        val (rect, open) = checkNotNull(drawn) { "The parcel isn't drawn" }
        return rect.translate(origin()) to open
    }

    /** The box as the window draws it now: its top and bottom down the frame's middle, in Pip's points from the frame's top. */
    private fun box(): Box {
        val frame = parcel().first
        val image = compose.onRoot().captureToImage().asAndroidBitmap()
        val scale = frame.width / FRAME_WIDTH
        fun px(x: Float) = (frame.left + x * scale).roundToInt().coerceIn(0, image.width - 1)
        fun py(y: Float) = (frame.top + y * scale).roundToInt().coerceIn(0, image.height - 1)
        val paper = image.getPixel(px(4f), py(4f))
        val middle = px(FRAME_WIDTH / 2)
        fun ink(y: Int) = differs(image.getPixel(middle, y), paper) > INK
        val top = (py(0f)..py(FRAME_HEIGHT)).first(::ink)
        val bottom = (py(FRAME_HEIGHT) downTo py(0f)).first(::ink)
        return Box((top - frame.top) / scale, (bottom - frame.top) / scale)
    }

    /** What the window draws of the parcel's frame now: the luminance of its pixels, and where the frame stands. */
    private fun drawing(): Drawing {
        val frame = parcel().first
        val image = compose.onRoot().captureToImage().asAndroidBitmap()
        val left = frame.left.toInt().coerceIn(0, image.width - 1)
        val top = frame.top.toInt().coerceIn(0, image.height - 1)
        val width = frame.right.toInt().coerceIn(left + 1, image.width) - left
        val height = frame.bottom.toInt().coerceIn(top + 1, image.height) - top
        val pixels = IntArray(width * height)
        image.getPixels(pixels, 0, width, left, top, width, height)
        val luma = FloatArray(pixels.size) {
            val pixel = pixels[it]
            0.299f * android.graphics.Color.red(pixel) + 0.587f * android.graphics.Color.green(pixel) + 0.114f * android.graphics.Color.blue(pixel)
        }
        return Drawing(luma, left, top, width, height, frame)
    }

    /**
     * How far, in degrees clockwise about the frame's middle, the box in [now] is turned from [rest]: the turn that
     * best carries the one onto the other, lifted about [lift] points, read across the box above its shadow.
     */
    private fun turnedFrom(rest: Drawing, now: Drawing, lift: Float): Float {
        val points = buildList {
            var y = 20f
            while (y <= 250f) {
                var x = 20f
                while (x <= 280f) {
                    add(x to y)
                    x += 1.5f
                }
                y += 1.5f
            }
        }
        val nowScale = now.frame.width / FRAME_WIDTH
        val restScale = rest.frame.width / FRAME_WIDTH
        val seen = FloatArray(points.size) { now.at(now.frame.left + points[it].first * nowScale, now.frame.top + points[it].second * nowScale) }
        fun cost(degrees: Float, dy: Float): Float {
            val angle = Math.toRadians(-degrees.toDouble())
            val c = cos(angle).toFloat()
            val s = sin(angle).toFloat()
            var sum = 0f
            var count = 0
            points.forEachIndexed { i, (x, y) ->
                val value = seen[i]
                if (value.isNaN()) return@forEachIndexed
                val qx = x - FRAME_WIDTH / 2
                val qy = y - FRAME_HEIGHT / 2 - dy
                val was = rest.at(
                    rest.frame.left + (FRAME_WIDTH / 2 + qx * c - qy * s) * restScale,
                    rest.frame.top + (FRAME_HEIGHT / 2 + qx * s + qy * c) * restScale,
                )
                if (!was.isNaN()) {
                    sum += abs(value - was)
                    count++
                }
            }
            return sum / count
        }
        var best = 0f to lift
        var least = Float.MAX_VALUE
        fun tryTurn(degrees: Float, dy: Float) {
            val cost = cost(degrees, dy)
            if (cost < least) {
                least = cost
                best = degrees to dy
            }
        }
        for (turn in -15..15) for (up in -4..4) tryTurn(turn * 0.2f, lift + up * 0.25f)
        val (coarse, raised) = best
        for (turn in -8..8) for (up in -2..2) tryTurn(coarse + turn * 0.025f, raised + up * 0.125f)
        return best.first
    }

    /** How many of the parcel frame's pixels [b] draws otherwise than [a], by more than a little. */
    private fun changed(a: Drawing, b: Drawing): Int {
        var count = 0
        for (y in 0 until min(a.height, b.height)) {
            for (x in 0 until min(a.width, b.width)) {
                if (abs(a.luma[y * a.width + x] - b.luma[y * b.width + x]) > 24) count++
            }
        }
        return count
    }

    /** Where the welcome's lockup stands in the window, which nothing else draws over as the welcome comes or goes. */
    private fun lockup(): Rect = compose.onAllNodes(
        hasText(localizer.text("app.title")) and hasAnyAncestor(hasTestTag(INVITATION_WELCOME)),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().minBy { it.boundsInRoot.top }.boundsInRoot

    /** How far what the window draws in [area] stands out from the paper: the most a pixel differs from its row's first. */
    private fun ink(area: Rect): Int {
        val image = compose.onRoot().captureToImage().asAndroidBitmap()
        val left = area.left.toInt().coerceIn(0, image.width - 1)
        val right = area.right.toInt().coerceIn(left + 1, image.width)
        val top = area.top.toInt().coerceIn(0, image.height - 1)
        val bottom = area.bottom.toInt().coerceIn(top + 1, image.height)
        var most = 0
        for (y in top until bottom) {
            val paper = image.getPixel(left, y)
            for (x in left until right) most = max(most, differs(image.getPixel(x, y), paper))
        }
        return most
    }

    private fun ink(image: Bitmap, rows: IntRange): Int {
        var most = 0
        for (y in rows) {
            val paper = image.getPixel(0, y)
            for (x in 0 until image.width) most = max(most, differs(image.getPixel(x, y), paper))
        }
        return most
    }

    /** How far the welcome's "tap to open" stands out: the open button's last 44 points, under the parcel's anchor. */
    private fun captionInk(): Int {
        val image = compose.onNodeWithTag(WELCOME_OPEN_PARCEL, useUnmergedTree = true).captureToImage().asAndroidBitmap()
        val strip = (44 * compose.density.density).roundToInt()
        return ink(image, image.height - strip until image.height)
    }

    /**
     * The box of the node tagged [tag] where the window draws it, through every layer it is drawn in (turned, scaled,
     * moved), in pixels: the bounds of the tag's own modifier, mapped corner by corner.
     */
    private fun place(tag: String): Rect? =
        compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().singleOrNull()?.let(::place)

    private fun place(node: SemanticsNode): Rect = onUi {
        val tagged = node.layoutInfo.getModifierInfo().firstOrNull { (it.modifier as? InspectableValue)?.nameFallback == "testTag" }
        val coordinates = tagged?.coordinates ?: node.layoutInfo.coordinates
        coordinates.findRootCoordinates().localBoundingBoxOf(coordinates, clipBounds = false)
    }

    /** The scroll holding [tag] moved, on the stopped clock, until [tag] stands 16 above its foot. */
    private fun scrollToStopped(tag: String) {
        val scroll = compose.onNode(hasScrollAction() and hasAnyDescendant(hasTestTag(tag)), useUnmergedTree = true).fetchSemanticsNode()
        val part = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode()
        val under = part.positionInRoot.y + part.size.height - scroll.boundsInRoot.bottom
        if (under > 0) compose.runOnUiThread { scroll.config[SemanticsActions.ScrollBy].action?.invoke(0f, under + 16 * compose.density.density) }
        compose.mainClock.advanceTimeBy(700)
    }

    /** Tapped, the parcel opens and the screen changes: what was drawn each frame for [millis], and when the change came. */
    private fun unwrap(millis: Long): Pair<List<Pair<Long, Moving>>, Long> {
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performClick()
        val since = now()
        val seen = frames(millis, since) { parcel().let { (frame, open) -> Moving(frame, open, source.invitation.opened) } }
        return seen to seen.first { it.second.opened }.first
    }

    /**
     * C-AN04, C-AN05: 250 ms after the welcome shows the box greets, up 9 at its height 2.3 s on, turned 1.1° clockwise
     * about its middle, then down again turned 0.8° the other way; and again 4.6 s later, for as long as it shows.
     */
    @Test
    fun theParcelGreetsAQuarterSecondAfterTheWelcomeShowsThenEveryFourPointSixSeconds() {
        show(reduceMotion = false)
        val since = follow()
        val rest = box()
        val still = drawing()
        val seen = mutableListOf<Pair<Long, Float>>()
        var height: Pair<Drawing, Float>? = null
        var end: Pair<Drawing, Float>? = null
        while (now() - since < 9_800) {
            compose.mainClock.advanceTimeBy(100)
            val at = now() - since
            val lift = box().liftFrom(rest)
            seen += at to lift
            if (height == null && at >= 2_550) height = drawing() to lift
            if (end == null && at >= 4_800) end = drawing() to lift
        }
        val trace = seen.map { (at, it) -> "$at: %.1f".format(it) }
        seen.filter { it.first <= 250 }.forEach { assertEquals("Still before the greeting: $trace", 0f, it.second, 0.5f) }
        val first = seen.filter { it.first in 1_500..3_500 }.minBy { it.second }
        assertEquals("Up 9, 2.55 s in: $trace", 2_550f, first.first.toFloat(), 200f)
        assertEquals("Up 9: $trace", -9f, first.second, 1f)
        val (top, raised) = height!!
        assertEquals("Turned 1.1° clockwise at its height", 1.1f, turnedFrom(still, top, raised), 0.25f)
        val (last, lowered) = end!!
        assertEquals("Down again at the greeting's end: $trace", 0f, lowered, 0.7f)
        assertEquals("Turned 0.8° anticlockwise at its end", -0.8f, turnedFrom(still, last, lowered), 0.25f)
        val second = seen.filter { it.first in 6_100..8_100 }.minBy { it.second }
        assertEquals("Again 4.6 s later: $trace", 4_600f, (second.first - first.first).toFloat(), 200f)
        assertEquals("Up 9 again: $trace", -9f, second.second, 1f)
    }

    /** C-AN04, C-AN05: under Remove animations the box stands still. */
    @Test
    fun underRemoveAnimationsTheParcelDoesntGreet() {
        show(reduceMotion = true)
        val since = follow()
        val rest = box()
        val still = drawing()
        while (now() - since < 5_000) {
            compose.mainClock.advanceTimeBy(500)
            assertEquals("Still at ${now() - since}", 0f, box().liftFrom(rest), 0.5f)
        }
        assertEquals("Level", 0f, turnedFrom(still, drawing(), 0f), 0.15f)
    }

    /**
     * C-AN07: a finger on the parcel presses the box in toward the ground on `.spring(response: 0.3, dampingFraction:
     * 0.64)`, squashed to 0.956 a little past it 0.2 s in, and let go it comes back on the same spring.
     */
    @Test
    fun aFingerOnTheParcelPressesItInOnASpringAndLetGoItComesBack() {
        show(reduceMotion = false)
        val since = follow()
        compose.mainClock.advanceTimeBy(1_950 - (now() - since))
        val rest = box()
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performTouchInput { down(center) }
        val pressing = frames(650) { 1 - box().height / rest.height }
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performTouchInput { cancel() }
        val letGo = frames(550) { 1 - box().height / rest.height }
        val trace = (pressing + letGo).map { (at, it) -> "$at: %.4f".format(it) }
        // The press shows once the scroll it is in knows it isn't a drag; the spring starts after the last frame at rest.
        val start = pressing.last { it.second <= 0.001f }.first
        val most = pressing.maxOf { it.second }
        val peak = pressing.filter { it.first > start && it.second >= most - 0.0015f }.let { (it.first().first + it.last().first) / 2 }
        assertEquals("Squashed a little past 0.956: $trace", 0.044f * 1.073f, most, 0.004f)
        assertEquals("At its most 0.2 s in: $trace", 203f, (peak - start).toFloat(), 45f)
        assertEquals("Then at 0.956: $trace", 0.044f, pressing.last().second, 0.003f)
        assertEquals("Let go, as it was: $trace", 0f, letGo.last().second, 0.003f)
        val back = letGo.first { it.second < 0.042f }.first
        assertTrue("Back on the spring: $trace", letGo.any { it.first in back + 64..back + 160 && it.second in 0.005f..0.035f })
    }

    /** C-AN07: under Remove animations a press leaves the box as it is. */
    @Test
    fun underRemoveAnimationsAPressLeavesTheParcelAsItIs() {
        show(reduceMotion = true)
        follow()
        val rest = box()
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performTouchInput { down(center) }
        frames(500) { 1 - box().height / rest.height }.forEach { (at, squash) -> assertEquals("As it was at $at", 0f, squash, 0.003f) }
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performTouchInput { cancel() }
    }

    /**
     * C-AN08, C-AN09, C-AN12, C-AN14, C-AN10, C-AN01, C-AN03: tapped, the parcel opens over 0.95 s on `.easeInOut`,
     * "tap to open" fading as it does and the stars flying out; 980 ms after the tap's frame (`.task(id:)` waits from
     * the update the tap makes) the sign-in comes, the welcome fading out over 0.45 s as the parcel springs to the
     * sign-in's anchor (`.spring(duration: 0.75, bounce: 0.08)`).
     */
    @Test
    fun tappedTheParcelOpensUnderItsStarsThenSpringsToTheSignInAsTheWelcomeFades() {
        show(reduceMotion = false, session = AuthenticationState.Welcome, signIn = true)
        follow()
        val caption = captionInk()
        val lockup = lockup()
        val name = ink(lockup)
        val welcome = parcel().first
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performClick()
        val since = now()
        val seen = mutableListOf<Pair<Long, Moving>>()
        var halfCaption: Int? = null
        var goneCaption: Int? = null
        var stars: List<Boolean>? = null
        var switched: Long? = null
        var fading: Int? = null
        var faded: Int? = null
        while (now() - since < 2_400) {
            compose.mainClock.advanceTimeByFrame()
            val at = now() - since
            seen += at to parcel().let { (frame, open) -> Moving(frame, open, source.invitation.opened) }
            if (halfCaption == null && at >= 475) halfCaption = captionInk()
            if (stars == null && at >= 925) stars = starsAt(at / STAR_BURST)
            if (goneCaption == null && at >= 950) goneCaption = captionInk()
            if (switched == null && source.invitation.opened) switched = at
            if (fading == null && switched != null && at >= switched + 225) fading = ink(lockup)
            if (faded == null && switched != null && at >= switched + 470) faded = ink(lockup)
        }
        val opens = seen.map { (at, it) -> at to it.open }
        assertTrue("Opening on .easeInOut(0.95): $opens", opens.near(240) in 0.08f..0.2f)
        assertTrue("Half open half way: $opens", opens.near(475) in 0.44f..0.56f)
        assertTrue("Nearly open: $opens", opens.near(712) in 0.8f..0.93f)
        assertEquals("Open at 0.95 s: $opens", 1f, opens.near(960), 0.005f)
        assertTrue("Tap to open fades with it: $halfCaption of $caption", halfCaption!! in caption * 3 / 10..caption * 7 / 10)
        assertTrue("Tap to open gone: $goneCaption of $caption", goneCaption!! < caption / 10)
        assertEquals("The stars out: $stars", List(STARS.size) { true }, stars)
        val change = checkNotNull(switched) { "No sign-in: $opens" }
        assertEquals("The sign-in 980 ms after the tap's frame", FRAME + 980f, change.toFloat(), 17f)
        assertTrue("The welcome fading out over 0.45 s: $fading of $name", fading!! in name * 3 / 10..name * 7 / 10)
        assertTrue("The welcome gone at 0.45 s: $faded of $name", faded!! < name / 10)
        val target = seen.last().second.frame
        assertTrue("At the sign-in's smaller anchor", target.width < welcome.width - 10)
        val moving = seen.filter { it.first >= change }.map { (at, it) -> at - change to it.frame.progress(welcome, target) }
        assertTrue("Springing to the sign-in: $moving", moving.near(250) in 0.55f..0.76f)
        assertTrue("Nearly there half a second in: $moving", moving.near(500) in 0.9f..0.985f)
        assertTrue("Barely past it: $moving", moving.all { it.second < 1.01f })
        assertTrue("On the sign-in", exists(SIGN_IN))
    }

    /**
     * C-AN08, C-AN12, C-AN14, C-AN10, C-AN01, C-AN03: under Remove animations, tapped, the parcel opens and tap to open
     * goes at once, without stars; 150 ms after that frame the sign-in comes, the welcome fading out over 0.15 s, the parcel at the
     * sign-in's anchor at once.
     */
    @Test
    fun underRemoveAnimationsTheParcelOpensAtOnceAndTheSignInComesAMomentLater() {
        show(reduceMotion = true, session = AuthenticationState.Welcome, signIn = true)
        follow()
        val caption = captionInk()
        val lockup = lockup()
        val name = ink(lockup)
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performClick()
        val since = now()
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        assertEquals("Open at once", 1f, parcel().second, 0.001f)
        assertTrue("Tap to open gone at once", captionInk() < caption / 10)
        val opened = drawing()
        compose.mainClock.advanceTimeBy(100)
        assertEquals("No stars fly out", 0, changed(opened, drawing()))
        while (!source.invitation.opened) {
            assertTrue("No sign-in", now() - since < 1_000)
            compose.mainClock.advanceTimeByFrame()
        }
        val change = now() - since
        assertEquals("The sign-in 150 ms after the tap's frame", FRAME + 150f, change.toFloat(), 17f)
        compose.mainClock.advanceTimeBy(75 - (now() - since - change))
        val fading = ink(lockup)
        assertTrue("The welcome fading over 0.15 s: $fading of $name", fading in name * 2 / 10..name * 8 / 10)
        val target = frames(300) { parcel().first }
        assertTrue("At the sign-in's anchor at once: $target", target.all { (_, it) -> abs(it.top - target.last().second.top) < 1f })
        val settled = drawing()
        compose.mainClock.advanceTimeBy(600)
        assertEquals("No stars fly over it", 0, changed(settled, drawing()))
    }

    /**
     * C-AN11, C-AN03, C-AN01: back from the sign-in the parcel springs back to the welcome's anchor and shuts on the
     * same spring, the welcome fading in over 0.45 s.
     */
    @Test
    fun backFromTheSignInTheParcelSpringsBackShutAndTheWelcomeFadesIn() {
        show(reduceMotion = false, session = AuthenticationState.Welcome, signIn = true)
        follow()
        val lockup = lockup()
        val name = ink(lockup)
        val welcome = parcel().first
        unwrap(2_400)
        val signIn = parcel().first
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        val since = now()
        var fading: Int? = null
        val seen = frames(1_400, since) {
            if (fading == null && now() - since >= 225) fading = ink(lockup)
            parcel()
        }
        assertFalse("Shut again", source.invitation.opened)
        val opens = seen.map { (at, it) -> at to it.second }
        assertTrue("Shutting on the spring: $opens", opens.near(250) in 0.24f..0.45f)
        assertTrue("Nearly shut half a second in: $opens", opens.near(500) in 0.015f..0.1f)
        assertEquals("Shut: $opens", 0f, opens.last().second, 0.003f)
        val moving = seen.map { (at, it) -> at to it.first.progress(signIn, welcome) }
        assertTrue("Springing back: $moving", moving.near(250) in 0.55f..0.76f)
        assertEquals("At the welcome's anchor: $moving", 1f, moving.last().second, 0.01f)
        assertTrue("The welcome fading in over 0.45 s: $fading of $name", fading!! in name * 3 / 10..name * 7 / 10)
        assertTrue("On the welcome", exists(WELCOME_OPEN_PARCEL) && !exists(SIGN_IN))
    }

    /**
     * C-AN13, C-AN16: accepted, the receipt's stamp is dropped onto the parcel 250 ms after it came, from 80 above,
     * turned 22° anticlockwise and 1.7 times as large, landing 34 below its middle and 28 right, turned 9°, on
     * `.spring(response: 0.32, dampingFraction: 0.62)`, a little past its place 0.2 s on; 910 ms after the receipt the
     * friends come with Sasha's card, which lands from 100 to the right on `.spring(response: 0.55, dampingFraction: 0.78)`.
     */
    @Test
    fun acceptedTheStampDropsOntoTheParcelThenTheFriendsCardLands() {
        show(reduceMotion = false)
        follow()
        unwrap(2_400)
        advanceUntil("the acceptance") { exists(INVITATION_ACCEPT) }
        scrollToStopped(INVITATION_ACCEPT)
        compose.onNodeWithTag(INVITATION_ACCEPT).performClick()
        advanceUntil("the receipt", step = { compose.mainClock.advanceTimeByFrame() }) { exists(INVITATION_STAMP) }
        val since = now()
        val landing = frames(880, since) { stamped() }
        val trace = landing.map { (at, it) -> "$at: (%.1f, %.1f) %.1f".format(it.x, it.y, it.width) }
        landing.filter { it.first < 250 }.forEach { (_, it) ->
            assertEquals("Waiting 80 above: $trace", -80f, it.y, 1.5f)
            assertEquals("1.7 times as large, turned 22°: $trace", 1.7f * turned(100f, 116f, 22.0), it.width, 3f)
        }
        landing.forEach { (_, it) -> assertEquals("28 right: $trace", 28f, it.x, 1f) }
        val most = landing.maxBy { it.second.y }
        assertEquals("A little past its place: $trace", 34f + 114f * 0.0835f, most.second.y, 2.5f)
        assertEquals("0.2 s after it drops: $trace", 454f, most.first.toFloat(), 50f)
        assertEquals("Landed 34 below: $trace", 34f, landing.last().second.y, 0.6f)
        assertEquals("Its size, turned 9°: $trace", turned(100f, 116f, 9.0), landing.last().second.width, 1.5f)

        advanceUntil("Sasha's card", step = { compose.mainClock.advanceTimeByFrame() }) { exists(friendCardTag(sasha.id)) }
        val cards = frames(1_500) { place(friendCardTag(sasha.id))?.left }
        val rest = checkNotNull(cards.last().second)
        val offsets = cards.mapNotNull { (at, left) -> left?.let { at to (it - rest) / compose.density.density } }
        val start = offsets.first { it.second < 99.5f }.first
        val flying = offsets.map { (at, it) -> at - start to it }
        assertEquals("Waiting 100 to the right: $offsets", 100f, offsets.first().second, 1f)
        assertTrue("Landing on the spring: $flying", flying.near(100) in 40f..85f)
        assertTrue("Nearly there 0.2 s in: $flying", flying.near(200) in 8f..36f)
        assertTrue("Barely past its place: $flying", flying.all { it.second > -4f })
        assertEquals("Landed: $flying", 0f, flying.last().second, 0.3f)
    }

    /**
     * C-AN13: under Remove animations the stamp comes at its place and size 30 ms after the receipt, turning from 22° to
     * 9° and coming into sight over 0.12 s.
     */
    @Test
    fun underRemoveAnimationsTheStampTurnsInAtItsPlace() {
        show(reduceMotion = true)
        follow()
        unwrap(400)
        advanceUntil("the acceptance") { exists(INVITATION_ACCEPT) }
        scrollToStopped(INVITATION_ACCEPT)
        compose.onNodeWithTag(INVITATION_ACCEPT).performClick()
        advanceUntil("the receipt", step = { compose.mainClock.advanceTimeByFrame() }) { exists(INVITATION_STAMP) }
        val since = now()
        val landing = frames(270, since) { stamped() }
        val trace = landing.map { (at, it) -> "$at: (%.1f, %.1f) %.1f".format(it.x, it.y, it.width) }
        landing.forEach { (_, it) -> assertEquals("At its place: $trace", 34f, it.y, 0.6f) }
        assertEquals("Turned 22° at first: $trace", turned(100f, 116f, 22.0), landing.first().second.width, 1.5f)
        assertEquals("Turned 9° 0.15 s in: $trace", turned(100f, 116f, 9.0), landing.last().second.width, 1.5f)
    }

    /** Where the stamp stands from the parcel's middle, and how wide its box is, in Pip's points. */
    private fun stamped(): Stamped {
        val frame = parcel().first
        val stamp = checkNotNull(place(INVITATION_STAMP)) { "No stamp" }
        val scale = frame.width / FRAME_WIDTH
        return Stamped((stamp.center.x - frame.center.x) / scale, (stamp.center.y - frame.center.y) / scale, stamp.width / scale)
    }

    /**
     * Whether each of the six stars shows its colour about where it flies `progress` of the way through its burst, as
     * SignInView's `ParcelStarBurst` throws it, in the parcel's frame where it now stands.
     */
    private fun starsAt(progress: Double): List<Boolean> {
        val frame = parcel().first
        val image = compose.onRoot().captureToImage().asAndroidBitmap()
        val scale = frame.width / FRAME_WIDTH
        return STARS.mapIndexed { index, star ->
            val delay = 0.14 + (index % 3) * 0.03
            val p = min(1.0, max(0.0, (progress - delay) / (1 - delay)))
            val travel = 1 - (1 - p).pow(3)
            val x = 150 + (star.x - 150) * (0.45 + 0.55 * travel)
            val y = 140 + (star.y - 140) * (0.3 + 0.7 * travel) - p * 12
            val cx = frame.left + x.toFloat() * scale
            val cy = frame.top + y.toFloat() * scale
            val reach = (star.size * 0.2f * scale).roundToInt().coerceAtLeast(2)
            (-reach..reach).any { dy ->
                (-reach..reach).any { dx ->
                    val px = (cx + dx).roundToInt().coerceIn(0, image.width - 1)
                    val py = (cy + dy).roundToInt().coerceIn(0, image.height - 1)
                    differs(image.getPixel(px, py), star.color) <= STAR_TOLERANCE
                }
            }
        }
    }

    /** The parcel's box as drawn, in Pip's points from its frame's top: its top and bottom down the frame's middle. */
    private data class Box(val top: Float, val bottom: Float) {
        val height get() = bottom - top

        /** How far up the box is from [rest], negative as SwiftUI's offset is. */
        fun liftFrom(rest: Box): Float = (top - rest.top + bottom - rest.bottom) / 2
    }

    /** The luminance of what the window draws of the parcel's frame, its first pixel at [left], [top]; and the frame. */
    private class Drawing(val luma: FloatArray, val left: Int, val top: Int, val width: Int, val height: Int, val frame: Rect) {
        /** The luminance at [x], [y] in the window's pixels, blended between pixels; NaN outside. */
        fun at(x: Float, y: Float): Float {
            val fx = x - left
            val fy = y - top
            val x0 = floor(fx).toInt()
            val y0 = floor(fy).toInt()
            if (x0 < 0 || y0 < 0 || x0 + 1 >= width || y0 + 1 >= height) return Float.NaN
            val ax = fx - x0
            val ay = fy - y0
            val i = y0 * width + x0
            return (luma[i] * (1 - ax) + luma[i + 1] * ax) * (1 - ay) + (luma[i + width] * (1 - ax) + luma[i + width + 1] * ax) * ay
        }
    }

    private data class Moving(val frame: Rect, val open: Float, val opened: Boolean)

    private data class Stamped(val x: Float, val y: Float, val width: Float)

    private data class Star(val x: Double, val y: Double, val size: Float, val color: Int)

    /** How far this frame has gone from [from] toward [to], by its middle's height. */
    private fun Rect.progress(from: Rect, to: Rect): Float = (center.y - from.center.y) / (to.center.y - from.center.y)

    /** What was seen nearest [millis]. */
    private fun List<Pair<Long, Float>>.near(millis: Long): Float = minBy { abs(it.first - millis) }.second

    private companion object {
        /** A made-up invitation code, in the 16-character form the service gives. */
        const val LINK = "$INVITATION_BASE/invite#Qa3vT8nL5wZ1pR7k"

        val sasha = FriendCard(UUID.fromString("14000000-0000-4000-8000-000000000014"), "Sasha", FriendStats(5, 3, emptyList()), arrivedThisWeek = false)

        /** A frame of the test's clock, in milliseconds. */
        const val FRAME = 16f

        /** Pip's frame, in points. */
        const val FRAME_WIDTH = 300f
        const val FRAME_HEIGHT = 310f

        /** How far a channel stands from the paper for a pixel to be the box's ink rather than its shadow's. */
        const val INK = 48

        /** The stars' burst, in milliseconds, and how near a pixel's channels must be to a star's colour. */
        const val STAR_BURST = 1_850.0
        const val STAR_TOLERANCE = 24

        /** SignInView's six sparkles: where each ends in Pip's frame, its size and colour. */
        val STARS = listOf(
            Star(43.0, 96.0, 23f, 0xFFC99B35.toInt()),
            Star(91.0, 57.0, 16f, 0xFFD6AE48.toInt()),
            Star(151.0, 36.0, 24f, 0xFFC99B35.toInt()),
            Star(216.0, 55.0, 18f, 0xFFB594BE.toInt()),
            Star(261.0, 96.0, 25f, 0xFFD6AE48.toInt()),
            Star(233.0, 145.0, 13f, 0xFFC99B35.toInt()),
        )

        /** The most any channel of two colours differs by. */
        fun differs(a: Int, b: Int): Int = maxOf(
            abs(android.graphics.Color.red(a) - android.graphics.Color.red(b)),
            abs(android.graphics.Color.green(a) - android.graphics.Color.green(b)),
            abs(android.graphics.Color.blue(a) - android.graphics.Color.blue(b)),
        )

        /** How wide the box of a [width] × [height] rectangle turned [degrees] is. */
        fun turned(width: Float, height: Float, degrees: Double): Float {
            val angle = Math.toRadians(degrees)
            return (width * abs(cos(angle)) + height * abs(sin(angle))).toFloat()
        }
    }
}
