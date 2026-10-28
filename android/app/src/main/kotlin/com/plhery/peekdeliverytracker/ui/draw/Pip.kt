package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.MapDrawingContext
import com.plhery.peekdeliverytracker.core.PipArtwork
import com.plhery.peekdeliverytracker.core.PipBlinkSchedule
import com.plhery.peekdeliverytracker.core.PipGeometry
import com.plhery.peekdeliverytracker.core.PipLabel
import com.plhery.peekdeliverytracker.core.PipMood
import com.plhery.peekdeliverytracker.core.PipOpening
import com.plhery.peekdeliverytracker.core.PipPalette
import com.plhery.peekdeliverytracker.core.PipPlacement
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.toRgba
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.math.roundToLong

// Pip's views (Pip.swift): :core's PipArtwork drawn on a canvas, clocked as the iPhone clocks it. Each is decorative,
// hidden from accessibility (A-AX16).

private val FRAME = PipGeometry.frame

/**
 * A canvas Pip's drawing is recorded onto by `draw`, one unit to the point (dp), as SwiftUI's `Canvas { context, _ in
 * PipArtwork… }` (his label, a gift's ribbon). It records again at each drawing. Decorative.
 */
@Composable
fun PipCanvas(modifier: Modifier = Modifier, draw: (MapDrawingContext) -> Unit) {
    val measurer = rememberTextMeasurer()
    val colors = PeekTheme.colors
    val cache = remember { MapDrawingCache() }
    Canvas(modifier.clearAndSetSemantics {}) {
        scale(density, density, pivot = Offset.Zero) {
            drawPip(draw, colors.dark, measurer, cache)
        }
    }
}

private fun DrawScope.drawPip(draw: (MapDrawingContext) -> Unit, dark: Boolean, measurer: TextMeasurer?, cache: MapDrawingCache) {
    drawMapDrawings(MapDrawingContext().also(draw).drawing, dark, textMeasurer = measurer, cache = cache)
}

/**
 * The kraft parcel's face at full size, in Pip's 300 × 310 frame. Its open eyes blink while it waits; the happy arcs
 * don't, and nothing does under Reduce Motion. `happy` is 0 for open eyes, 1 for happy arcs; `smile` is how much of
 * the mouth shows, none under a gift's ribbon.
 */
@Composable
fun KraftPipFace(happy: Double, modifier: Modifier = Modifier, smile: Double = 1.0) {
    val blink = rememberPipBlinkClock(blinks = !PeekTheme.reduceMotion && happy == 0.0)
    val dark = PeekTheme.colors.dark
    val cache = remember { MapDrawingCache() }
    Canvas(modifier.size(FRAME.width.dp, FRAME.height.dp).clearAndSetSemantics {}) {
        scale(density, density, pivot = Offset.Zero) {
            drawPip({ PipArtwork.kraftFace(it, k = 1.0, happy = happy, blink = blink.time, smile = smile) }, dark, null, cache)
        }
    }
}

/**
 * The kraft parcel at sticker size, cropped to the box, as wide as it is given. `label` is the carrier's, for a parcel
 * whose carrier is known. `happy` is 0 for open eyes and 1 for happy arcs, drawn as given at each frame: animate it to
 * turn one into the other. Its open eyes blink now and then when it `blinks`, as it waits for an answer; not under
 * Reduce Motion. Decorative.
 */
@Composable
fun SmallPip(modifier: Modifier = Modifier, label: PipLabel? = null, happy: Double = 0.0, blinks: Boolean = false) {
    val blink = rememberPipBlinkClock(blinks = blinks && !PeekTheme.reduceMotion && happy == 0.0)
    val measurer = rememberTextMeasurer()
    val dark = PeekTheme.colors.dark
    val cache = remember { MapDrawingCache() }
    val crop = PipGeometry.stickerCrop
    Canvas(modifier.aspectRatio((crop.width / crop.height).toFloat()).clearAndSetSemantics {}) {
        val scale = (size.width / crop.width).toFloat()
        scale(scale, scale, pivot = Offset.Zero) {
            translate(-crop.minX.toFloat(), -crop.minY.toFloat()) {
                drawPip({ PipArtwork.sticker(it, label, happy, blink.time) }, dark, measurer, cache)
            }
        }
    }
}

/**
 * Pip in the card's own ink, beside the parcel's place on its map, as wide as he is given. Decorative. He fades in
 * while rising into place. Eager, he bobs; delivered, he jumps; neither under Reduce Motion. When the parcel arrives
 * while he is looked at, his box opens instead of being drawn open.
 */
@Composable
fun InkPip(mood: PipMood, side: Int, below: Boolean, ink: Color, surface: Color, modifier: Modifier = Modifier) {
    val dark = PeekTheme.colors.dark
    val reduceMotion = PeekTheme.reduceMotion
    val palette = remember(ink, surface, dark) { PipPalette.ink(AdaptiveColor(ink.toRgba()), AdaptiveColor(surface.toRgba()), dark) }
    val clock = rememberInkPipClock(mood, reduceMotion)
    val cache = remember { MapDrawingCache() }
    val arrival = remember { Animatable(if (reduceMotion) 1f else 0f) }
    LaunchedEffect(Unit) {
        if (reduceMotion) arrival.snapTo(1f) else arrival.animateTo(1f, Motion.spring(duration = 0.6))
    }
    val rise = 7.dp
    val shown = modifier
        .aspectRatio((FRAME.width / FRAME.height).toFloat())
        .graphicsLayer {
            alpha = arrival.value
            translationY = if (reduceMotion) 0f else (1 - arrival.value) * rise.toPx()
        }
        .clearAndSetSemantics {}
    Canvas(shown) {
        val frame = clock.frame(mood)
        val scale = (size.width / FRAME.width).toFloat()
        scale(scale, scale, pivot = Offset.Zero) {
            drawPip({ PipArtwork.ink(it, palette, mood, side, below, frame.time, frame.opening, frame.before) }, dark, null, cache)
        }
    }
}

/**
 * Told where Pip shows in the window, by a card that has something come out of his box (`pipSight`): his bounds in
 * points (dp) from the window's top left, as iOS gives his frame in its global space.
 */
val LocalPipSight = staticCompositionLocalOf<((Rect) -> Unit)?> { null }

/**
 * Pip beside the parcel's dot, in the map's points, his frame's top left at `place.origin`; he takes no room in the
 * layout. When he takes another spot or another size, as he does when his box opens, he goes there from where he stood
 * with a spring (none under Reduce Motion) instead of appearing there. The camera may be carrying the dot meanwhile, so
 * his place is kept from the dot. Decorative.
 */
@Composable
fun MapPip(place: PipPlacement, dot: Point?, ink: Color, surface: Color, modifier: Modifier = Modifier) {
    val reduceMotion = PeekTheme.reduceMotion
    val sight = LocalPipSight.current
    val pixelsPerPoint = LocalDensity.current.density
    val anchor = dot ?: place.origin
    val x = place.origin.x - anchor.x
    val y = place.origin.y - anchor.y
    // Where he stands from the dot, to the half point: the camera's own arithmetic is not a move. He springs from
    // one stand to the next, and goes the rest of the way at once, as SwiftUI offsets him without animation then.
    val standX = (x * 2).roundToInt() / 2.0
    val standY = (y * 2).roundToInt() / 2.0
    val stand = Triple(standX, standY, place.width)
    val offset = remember { Animatable(Offset(standX.toFloat(), standY.toFloat()), Offset.VectorConverter) }
    val width = remember { Animatable(place.width.toFloat()) }
    LaunchedEffect(stand) {
        val to = Offset(standX.toFloat(), standY.toFloat())
        if (reduceMotion) {
            offset.snapTo(to)
            width.snapTo(place.width.toFloat())
        } else {
            launch { offset.animateTo(to, Motion.spring(duration = 0.5, bounce = 0.18)) }
            launch { width.animateTo(place.width.toFloat(), Motion.spring(duration = 0.5, bounce = 0.18)) }
        }
    }
    val placed = modifier
        .layout { measurable, _ ->
            val pipWidth = width.value.dp.roundToPx()
            val pipHeight = (width.value * FRAME.height / FRAME.width).toFloat().dp.roundToPx()
            val pip = measurable.measure(Constraints.fixed(pipWidth, pipHeight))
            layout(0, 0) {
                val left = anchor.x + offset.value.x + (x - standX)
                val top = anchor.y + offset.value.y + (y - standY)
                pip.place((left * density).roundToInt(), (top * density).roundToInt())
            }
        }
        .onGloballyPositioned { coordinates ->
            val bounds = coordinates.boundsInWindow()
            sight?.invoke(Rect(bounds.left / pixelsPerPoint, bounds.top / pixelsPerPoint, bounds.right / pixelsPerPoint, bounds.bottom / pixelsPerPoint))
        }
    InkPip(place.mood, place.side, place.below, ink, surface, placed)
}

// MARK: - Clocks

/**
 * The kraft parcel's blink clock (`PipBlinkSchedule` from when it first showed): seconds since then, moved on at each
 * frame of each blink and still between two, when it sleeps. Null while it doesn't blink. Read it while drawing.
 */
@Stable
internal class PipBlinkClock(blinks: Boolean) {
    internal var appeared: Long? = null
    private var shown by mutableDoubleStateOf(0.0)
    internal var blinks by mutableStateOf(blinks)

    val time: Double? get() = if (blinks) shown else null

    /** Draws at each of the schedule's times from now on, at the first frame at or after it. */
    internal suspend fun run() {
        var now = withFrameNanos { it }
        val start = appeared ?: now.also { appeared = it }
        for (entry in PipBlinkSchedule().entries((now - start) / 1e9)) {
            val due = start + (entry * 1e9).toLong()
            // Asleep until just before a blink, then awake for its frames.
            if (due - now > WAKE_NANOS) delay((due - now - WAKE_NANOS) / 1_000_000)
            while (now < due) now = withFrameNanos { it }
            shown = entry
        }
    }

    private companion object {
        const val WAKE_NANOS = 50_000_000L
    }
}

@Composable
internal fun rememberPipBlinkClock(blinks: Boolean): PipBlinkClock {
    val clock = remember { PipBlinkClock(blinks) }
    SideEffect { clock.blinks = blinks }
    // Its clock starts when it shows, whether it blinks yet or not.
    LaunchedEffect(Unit) {
        withFrameNanos { if (clock.appeared == null) clock.appeared = it }
    }
    LaunchedEffect(blinks) {
        if (blinks) clock.run()
    }
    return clock
}

/** What InkPip draws at a frame: the time he moves at, null when he is still, and his box on its way open. */
internal class InkPipFrame(val time: Double?, val opening: PipOpening?, val before: PipMood?)

/**
 * InkPip's clock: the date he is drawn at in seconds since 1 January 2001, as his bob keeps time on the iPhone (two
 * Pips bob together), and the news of his parcel's arrival. While he moves it goes on 30 times a second, asleep
 * between two frames, and at every frame while his box opens.
 */
@Stable
internal class InkPipClock(private var mood: PipMood, reduceMotion: Boolean, private val wall: () -> Double) {
    /** When the news came, as his next frame's date in nanoseconds, and the mood he was in. */
    internal class Unboxing(val from: PipMood) {
        var at: Long? = null
    }

    /** The date in whole nanoseconds, so the time since the news is the frames' own and not the wall's rounding. */
    private var nanos by mutableLongStateOf(nanos(wall()))
    val date: Double get() = nanos / 1e9
    var unboxing by mutableStateOf<Unboxing?>(null)
        private set
    internal var moves by mutableStateOf(moves(mood, reduceMotion))
        private set

    /** `onChange(of: mood)`: the news of an arrival opens his box, unless motion is reduced; any other change ends it. */
    fun show(mood: PipMood, reduceMotion: Boolean) {
        moves = moves(mood, reduceMotion)
        if (mood == this.mood) return
        unboxing = if (mood == PipMood.Joy && this.mood != PipMood.Joy && !reduceMotion) Unboxing(this.mood) else null
        this.mood = mood
    }

    /** Moves the date on with the frames, from the wall's time at the first. */
    internal suspend fun run() {
        val first = withFrameNanos { it }
        val start = nanos(wall())
        var frame = first
        while (true) {
            nanos = start + (frame - first)
            val news = unboxing
            if (news != null && news.at == null) news.at = nanos
            // The flaps swing faster than he bobs. The iPhone keeps every frame for as long as he stays joyful after
            // the news (Pip.swift's `interval`); only the opening needs them.
            val opening = news?.at?.let { nanos - it < OPENING_NANOS } == true
            frame = if (opening) withFrameNanos { it } else nextFrame(after = frame)
        }
    }

    /** The first frame a 30th of a second after `after`, asleep until just before it. */
    private suspend fun nextFrame(after: Long): Long {
        val due = after + INTERVAL_NANOS
        delay((INTERVAL_NANOS - WAKE_NANOS) / 1_000_000)
        var frame = withFrameNanos { it }
        while (frame < due) frame = withFrameNanos { it }
        return frame
    }

    /** `draw`: his jump starts with the news, as his box does, which is open a second after it. */
    fun frame(mood: PipMood): InkPipFrame {
        val news = unboxing
        val since = news?.let { (nanos - (it.at ?: nanos)) / 1e9 }
        val opening = since?.takeIf { it < PipOpening.DURATION }?.let { PipOpening.at(it) }
        return InkPipFrame(
            time = if (moves) since ?: date else null,
            opening = if (mood == PipMood.Joy) opening else null,
            before = news?.from,
        )
    }

    private companion object {
        /** TimelineView's `.animation(minimumInterval: 1 / 30)`, 2 ms early for the display's jitter. */
        const val INTERVAL_NANOS = 1_000_000_000L / 30 - 2_000_000L

        /**
         * How long before a frame is due he wakes to ask for one: late enough that at 120 Hz the next frame is the one
         * due, early enough for a frame's own work to run late.
         */
        const val WAKE_NANOS = 6_000_000L

        val OPENING_NANOS = nanos(PipOpening.DURATION)

        fun nanos(seconds: Double): Long = (seconds * 1e9).roundToLong()

        fun moves(mood: PipMood, reduceMotion: Boolean) = !reduceMotion && (mood == PipMood.Eager || mood == PipMood.Joy)
    }
}

/** Seconds since 1 January 2001 by the wall clock (`timeIntervalSinceReferenceDate`). */
private fun secondsSinceReferenceDate(): Double = (System.currentTimeMillis() - 978_307_200_000L) / 1000.0

@Composable
internal fun rememberInkPipClock(mood: PipMood, reduceMotion: Boolean, wall: () -> Double = ::secondsSinceReferenceDate): InkPipClock {
    val clock = remember { InkPipClock(mood, reduceMotion, wall) }
    // Before the frame draws, so the news never finds his box drawn open first.
    SideEffect { clock.show(mood, reduceMotion) }
    LaunchedEffect(mood, reduceMotion) {
        if (clock.moves) clock.run()
    }
    return clock
}
