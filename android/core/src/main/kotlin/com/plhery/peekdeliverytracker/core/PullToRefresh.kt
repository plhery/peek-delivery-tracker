package com.plhery.peekdeliverytracker.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.PI

// From PullToRefresh.swift: the pull's state, and what the seal and its label show for it, frame by frame, in points.
// On the iPhone the scroll view does the rest: it resists the pull, starts the refresh, holds the gap open, then
// closes it. Android's list does that itself with nested scrolling; `PullToRefreshTrigger` says when a refresh starts.

/**
 * What the list's scroll view reports for pull-to-refresh. It only changes while the content is pulled below the top
 * or the top inset moves, so ordinary scrolling does no work. Equality is Swift's: 0.0 equals -0.0.
 */
class PullGeometry(val overscroll: Double, val inset: Double, val height: Double) {
    override fun equals(other: Any?): Boolean =
        other is PullGeometry && overscroll == other.overscroll && inset == other.inset && height == other.height

    override fun hashCode(): Int = listOf(overscroll, inset, height).fold(0) { hash, value ->
        hash * 31 + (if (value == 0.0) 0.0 else value).hashCode()
    }

    override fun toString(): String = "PullGeometry(overscroll=$overscroll, inset=$inset, height=$height)"

    companion object {
        /**
         * `init(_ geometry: ScrollGeometry)`: from the content's offset (negative while pulled down), the top inset and
         * the scroll view's height.
         */
        fun of(contentOffsetY: Double, contentInsetTop: Double, containerHeight: Double): PullGeometry =
            PullGeometry(swiftMax(0.0, -(contentOffsetY + contentInsetTop)), contentInsetTop, containerHeight)
    }
}

/**
 * Pull-to-refresh state shared by the list, which drives it, and the seal, the only view that reads the pull distance
 * on every frame. Used from the main thread, as the iPhone's model is `@MainActor`; the wait after a refresh runs in
 * `scope`, on the main thread too (the list's `rememberCoroutineScope()`).
 */
class PullToRefreshModel(private val scope: CoroutineScope) {
    enum class Phase { Idle, Refreshing, Settling }
    enum class Result { Succeeded, Failed }

    private val phaseState = MutableStateFlow(Phase.Idle)
    private val resultState = MutableStateFlow<Result?>(null)
    private val distanceState = MutableStateFlow(0.0)
    private val thresholdState = MutableStateFlow(175.0)
    private val holdDistanceState = MutableStateFlow(60.0)
    private val labelState = MutableStateFlow<String?>(null)
    private val startsState = MutableStateFlow(0)
    private val awaitingRestState = MutableStateFlow(false)

    val phaseFlow: StateFlow<Phase> = phaseState.asStateFlow()
    val resultFlow: StateFlow<Result?> = resultState.asStateFlow()
    val distanceFlow: StateFlow<Double> = distanceState.asStateFlow()
    val thresholdFlow: StateFlow<Double> = thresholdState.asStateFlow()
    val holdDistanceFlow: StateFlow<Double> = holdDistanceState.asStateFlow()
    val labelFlow: StateFlow<String?> = labelState.asStateFlow()
    val startsFlow: StateFlow<Int> = startsState.asStateFlow()
    val awaitingRestFlow: StateFlow<Boolean> = awaitingRestState.asStateFlow()

    var phase: Phase
        get() = phaseState.value
        private set(value) {
            phaseState.value = value
        }

    var result: Result?
        get() = resultState.value
        private set(value) {
            resultState.value = value
        }

    /** How far the content sits below its resting position. */
    var distance: Double
        get() = distanceState.value
        private set(value) {
            distanceState.value = value
        }

    /** The system starts refreshing after a pull of about a fifth of the screen. */
    var threshold: Double
        get() = thresholdState.value
        private set(value) {
            thresholdState.value = value
        }

    /** The gap the system holds open while refreshing. */
    var holdDistance: Double
        get() = holdDistanceState.value
        private set(value) {
            holdDistanceState.value = value
        }

    var label: String?
        get() = labelState.value
        private set(value) {
            labelState.value = value
        }

    /** Counts refreshes, so the seal pops once each time one starts. */
    var starts: Int
        get() = startsState.value
        private set(value) {
            startsState.value = value
        }

    /**
     * After a refresh the system waits for the content to return to the top before it can refresh again, so the seal
     * stays hidden until then.
     */
    var awaitingRest: Boolean
        get() = awaitingRestState.value
        private set(value) {
            awaitingRestState.value = value
        }

    private var restingInset: Double? = null

    /** The wait after a refresh before the seal returns to rest; tests await it. */
    var settleJob: Job? = null
        private set

    val progress: Double get() = if (phase == Phase.Idle) swiftMin(1.0, distance / threshold) else 1.0

    fun track(geometry: PullGeometry) {
        // While refreshing, the system adds its gap to the top inset, so the pull is measured from the inset the list
        // had at rest.
        if (geometry.overscroll == 0.0) {
            if (phase == Phase.Idle) restingInset = geometry.inset
            if (awaitingRest) awaitingRest = false
        }
        val added = swiftMax(0.0, geometry.inset - (restingInset ?: geometry.inset))
        val next = geometry.overscroll + added
        if (next != distance) distance = next
        if (phase == Phase.Refreshing && added > 20 && added != holdDistance) holdDistance = added
        val nextThreshold = swiftMax(80.0, geometry.height * 0.2)
        if (nextThreshold != threshold) threshold = nextThreshold
    }

    fun begin(label: String) {
        settleJob?.cancel()
        phase = Phase.Refreshing
        result = null
        this.label = label
        starts += 1
    }

    fun update(label: String) {
        if (phase == Phase.Refreshing && result == null) this.label = label
    }

    fun finish(succeeded: Boolean, label: String) {
        if (phase != Phase.Refreshing) return
        result = if (succeeded) Result.Succeeded else Result.Failed
        this.label = label
    }

    /** Called as the refresh returns: the system closes the gap while the seal fades. */
    fun settle() {
        phase = Phase.Settling
        settleJob = scope.launch {
            delay(SETTLE_MILLISECONDS)
            if (phase != Phase.Settling) return@launch
            phase = Phase.Idle
            result = null
            label = null
            awaitingRest = distance > 0
        }
    }

    companion object {
        /** How long the seal takes to fade before it returns to rest. */
        const val SETTLE_MILLISECONDS = 450L
    }
}

/**
 * UIRefreshControl's part, which Android's list plays itself. A refresh starts once the finger has pulled the content
 * the model's `threshold` below its rest, without waiting for it to lift, and the next one only once the refresh is
 * over and the content has been back at rest.
 */
data class PullToRefreshTrigger(val rested: Boolean = true) {
    class Next(val trigger: PullToRefreshTrigger, val starts: Boolean)

    /** After the model has tracked a pull of `distance`, held by the finger or not: whether the refresh starts now. */
    fun next(distance: Double, dragging: Boolean, model: PullToRefreshModel): Next = when {
        model.phase == PullToRefreshModel.Phase.Refreshing -> Next(PullToRefreshTrigger(rested = false), false)
        !(distance > 0) -> Next(PullToRefreshTrigger(rested = true), false)
        rested && dragging && distance >= model.threshold -> Next(PullToRefreshTrigger(rested = false), true)
        else -> Next(this, false)
    }
}

object PullToRefreshPalette {
    val inkSoft = Brand.color(light = "#657060", dark = "#AFB9A9")
}

/**
 * What the indicator shows for the model's state. It stands in for the system spinner, like the web app's seal:
 * neutral ink while pulling and refreshing, green only once every parcel has been checked.
 *
 * :app lays it out as the iPhone does: the seal, then the label `SPACING` below it, centred in a band `HEIGHT` tall
 * and as wide as the list, behind the list's content and aligned with its top (top padding included), moved down by
 * `offset` (so up, as it is negative) and faded to `opacity`. It takes no touches and is hidden from accessibility: the
 * list announces how a refresh went.
 */
data class PullToRefreshIndicator(
    /** The gap the seal stays centred in: the pull, or the gap held while refreshing if that is larger. */
    val gap: Double,
    val offset: Double,
    val opacity: Double,
    /** The seal's scale about its centre, beneath its pop. */
    val sealScale: Double,
    val label: String,
    val seal: PullToRefreshSeal,
) {
    companion object {
        const val HEIGHT = 48.0
        const val SPACING = 4.0

        /**
         * The label is caption 2, medium, in `PullToRefreshPalette.inkSoft`, on one line that shrinks to 0.8 of its size
         * before it is cut, no larger than the xxLarge text size, this far in from each side.
         */
        const val LABEL_PADDING = 12.0
        const val LABEL_MINIMUM_SCALE = 0.8

        /**
         * `.animation(.easeOut(duration: 0.22), value: model.phase)`: as the phase changes, the opacity and what changes
         * with it inside the indicator (the seal's scale) ease over this. The offset follows the content, unanimated.
         */
        val phaseAnimation = MotionCurve.EaseOut(0.22)

        fun of(model: PullToRefreshModel, pullLabel: String, reduceMotion: Boolean): PullToRefreshIndicator {
            val pulling = model.phase == PullToRefreshModel.Phase.Idle
            val progress = model.progress
            // Kept a little under halfway down the gap, so it stays centred as it grows.
            val gap = if (pulling) model.distance else swiftMax(model.holdDistance, model.distance)
            val opacity = when {
                model.phase == PullToRefreshModel.Phase.Settling || (pulling && model.awaitingRest) -> 0.0
                pulling -> swiftMin(1.0, swiftMax(0.0, progress * 2 - 0.15))
                else -> 1.0
            }
            return PullToRefreshIndicator(
                gap = gap,
                offset = -0.55 * gap - 21,
                opacity = opacity,
                sealScale = if (reduceMotion) 1.0 else 0.82 + 0.18 * progress,
                label = model.label ?: pullLabel,
                seal = PullToRefreshSeal.of(model, progress, reduceMotion),
            )
        }
    }
}

/**
 * The seal: a disc with a shadow, the refresh arrow while there is no result, then the check or the cross drawn over
 * it. Each part is centred in the seal.
 */
data class PullToRefreshSeal(
    val disc: AdaptiveColor,
    /** The arrow's turn while it is not spinning, in degrees clockwise. */
    val arrowDegrees: Double,
    val spinning: Boolean,
    val arrowColor: AdaptiveColor,
    val arrowOpacity: Double,
    /** The cross rather than the check (`PullToRefreshGlyph`). */
    val cross: Boolean,
    val glyphColor: AdaptiveColor,
    /** How much of the glyph is drawn, from its start (`trim(from: 0, to:)`). */
    val glyphTrim: Double,
    val glyphOpacity: Double,
    val glyphScale: Double,
    /** Whether the seal pops as each refresh starts (`pop`); not with Reduce Motion. */
    val pops: Boolean,
) {
    companion object {
        const val SIZE = 32.0
        const val ARROW_SIZE = 18.0
        const val GLYPH_SIZE = 15.0
        val glyphStroke = StrokeStyle(1.5, StrokeStyle.LineCap.Round, StrokeStyle.LineJoin.Round)

        /** The disc's shadow: black at this opacity, blurred by this radius, this far down. */
        const val SHADOW_OPACITY = 0.05
        const val SHADOW_RADIUS = 6.0
        const val SHADOW_Y = 2.0

        /** As a result comes, the disc takes its colour over this; as it goes, at once. */
        val discAnimation = MotionCurve.EaseOut(0.2)

        /** The arrow fades out as a result comes, and back in as it goes, over this. */
        val arrowAnimation = MotionCurve.EaseOut(0.18)

        /** As a result comes, the glyph draws itself in its colour over this; as it goes, it vanishes at once. */
        val glyphDrawAnimation = MotionCurve.EaseOut(0.26, delay = 0.08)

        /** As a result comes, the glyph fades in and grows to full size on this spring; as it goes, at once. */
        val glyphAnimation = MotionCurve.SpringCurve(Spring(duration = 0.3, bounce = 0.2))

        /** How long the pop lasts. */
        const val POP_DURATION = 0.52

        private val popSpring = Spring(duration = 0.4, bounce = 0.5)

        /**
         * The seal's scale `time` seconds into the pop that marks a refresh starting, where the web seal pops as it arms:
         * up to 1.06 easing out, then a bouncy spring back toward 1. After `POP_DURATION` it stays where the spring was.
         */
        fun pop(time: Double): Double {
            val at = swiftMin(swiftMax(0.0, time), POP_DURATION)
            if (at < 0.12) return 1.0 + (1.06 - 1.0) * UnitCurve.easeOut.value(at / 0.12)
            return 1.06 + popSpring.value(1.0 - 1.06, time = at - 0.12)
        }

        fun of(model: PullToRefreshModel, progress: Double, reduceMotion: Boolean): PullToRefreshSeal {
            val result = model.result
            val active = model.phase != PullToRefreshModel.Phase.Idle
            return PullToRefreshSeal(
                disc = when (result) {
                    PullToRefreshModel.Result.Succeeded -> ExperimentalPalette.deliveredSurface
                    PullToRefreshModel.Result.Failed -> ExperimentalPalette.pickupSurface
                    null -> Brand.paper
                },
                arrowDegrees = if (reduceMotion) 0.0 else 270 * progress,
                spinning = active && !reduceMotion,
                arrowColor = if (active) Brand.ink else PullToRefreshPalette.inkSoft,
                arrowOpacity = if (result == null) 1.0 else 0.0,
                cross = result == PullToRefreshModel.Result.Failed,
                glyphColor = if (result == PullToRefreshModel.Result.Failed) Brand.warning else ExperimentalPalette.delivered,
                glyphTrim = if (result == null) 0.0 else 1.0,
                glyphOpacity = if (result == null) 0.0 else 1.0,
                glyphScale = if (result == null && !reduceMotion) 0.65 else 1.0,
                pops = !reduceMotion,
            )
        }
    }
}

/** The check or cross drawn on the seal, from the web app's icon paths, in a 24-unit square scaled to fit `rect`. */
object PullToRefreshGlyph {
    fun path(rect: Rect, cross: Boolean): MapPath {
        val scale = swiftMin(rect.width, rect.height) / 24
        fun point(x: Double, y: Double): Point = Point(rect.minX + x * scale, rect.minY + y * scale)
        val path = MapPath()
        if (cross) {
            path.move(point(6.0, 6.0))
            path.addLine(point(18.0, 18.0))
            path.move(point(6.0, 18.0))
            path.addLine(point(18.0, 6.0))
        } else {
            path.move(point(5.0, 12.0))
            path.addLine(point(9.0, 16.0))
            path.addLine(point(19.0, 6.0))
        }
        return path
    }
}

/**
 * The refresh arrow, SF Symbols' `arrow.clockwise` at 13.5 points and medium weight, centred in its square: :app draws
 * its own clockwise arrow there. It turns with the pull, then spins, and keeps turning while the refreshed list renders.
 */
object PullToRefreshArrow {
    /** One turn of the spin, in seconds. */
    const val TURN = 0.85

    private val firstTurn = UnitCurve.bezier(Point(0.45, 0.0), Point(0.75, 0.75))

    /** `degrees * .pi / 180`: the turn the arrow shows while not spinning, in radians. */
    fun radians(degrees: Double): Double = degrees * PI / 180

    /**
     * The arrow's turn in radians `elapsed` seconds after it started spinning from `start`: it eases out of the pulled
     * angle for a whole turn, then turns at a constant speed.
     */
    fun spin(start: Double, elapsed: Double): Double {
        if (elapsed < TURN) {
            val from = start
            val to = start + 2 * PI
            return from + (to - from) * firstTurn.value(swiftMax(0.0, elapsed) / TURN)
        }
        val from = start + 2 * PI
        val to = start + 4 * PI
        return from + (to - from) * ((elapsed - TURN) % TURN / TURN)
    }
}
