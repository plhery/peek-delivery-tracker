package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.TrackingStage
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.floor
import kotlin.math.hypot
import kotlin.math.sin

// From Pip.swift, what the map needs to place Pip and what his drawing computes: his moods, where he stands, his box's
// geometry, its opening, his colours, his blink, his motion and his glints' twinkle. The drawing itself (PipArtwork's
// GraphicsContext code, the views) belongs to :app.

/** How Pip, the parcel with a face, feels about the parcel's stage. */
enum class PipMood {
    Look, Eager, Wait, Worry, Joy;

    /** Widths to try, largest first: he is bigger when the parcel has arrived, unless only a small Pip fits. */
    val widths: List<Double> get() = if (this == Joy) listOf(66.0, 62.0, 54.0, 48.0) else listOf(48.0, 44.0)

    companion object {
        /** A pending parcel has no map, so no Pip. */
        operator fun invoke(stage: TrackingStage?): PipMood? = when (stage) {
            TrackingStage.Registered, TrackingStage.Accepted, TrackingStage.InTransit -> Look
            TrackingStage.OutForDelivery -> Eager
            TrackingStage.Customs, TrackingStage.ReadyForPickup -> Wait
            TrackingStage.FailedAttempt, TrackingStage.Exception, TrackingStage.Returned -> Worry
            TrackingStage.Delivered -> Joy
            TrackingStage.Pending, null -> null
        }
    }
}

/** Where Pip stands beside the parcel's dot: the top left of his frame as an offset from the dot, and how he stands there. */
data class PipSpot(
    val offset: Size,
    val width: Double,
    /** Which side of him the parcel's dot is on: -1 to his left, 1 to his right, 0 straight above or below. */
    val side: Int,
    /** He stands straight below the dot, and looks up at it. */
    val below: Boolean,
)

/** Where a map wants Pip: beside the parcel's place, in this mood. */
data class PipRequest(
    val mood: PipMood,
    /** Where the card's top row ends; the top inset when absent. */
    val ceiling: Double? = null,
    /** Where the card starts writing over the bottom of the map; the map's bottom edge when absent. */
    val floor: Double? = null,
    /** Keeps him inside the insets on every side, where a bar or a button covers the map's edges. */
    val inset: Boolean = false,
    /** The spot he keeps while the map is moved under him, instead of looking for the best one. */
    val held: PipSpot? = null,
)

/** Where Pip stands on a map. */
data class PipPlacement(
    /** The top left of his 300 × 310 frame, scaled to `width`. */
    val origin: Point,
    val width: Double,
    val mood: PipMood,
    /** Which side of him the parcel's dot is on: -1 to his left, 1 to his right, 0 straight above or below. */
    val side: Int,
    /** He stands straight below the dot, and looks up at it. */
    val below: Boolean,
    /** What he takes on the map, for the names to keep off. */
    val box: Rect,
    /** The same place, told from the parcel's dot. */
    val spot: PipSpot,
)

/** Pip's box on its way open: how far each part has come, so long after the news. */
data class PipOpening(
    /**
     * How far the rear flaps have folded over, and the front ones turned on their hinges: 0 closed, 1 open,
     * and a little more while a front flap goes too far.
     */
    val rear: Double,
    val front: Double,
    /** The two flaps that lie under the others on a closed box show as it opens. */
    val under: Double,
    /** How much of the tape is left, and how far it has lifted off. */
    val tape: Double,
    val lift: Double,
    /** The face he had, and the one he takes. */
    val faceBefore: Double,
    val face: Double,
    val glints: Double,
    /** How far he has settled from the stance he had into the one he takes. */
    val stance: Double,
) {
    companion object {
        val closed = PipOpening(rear = 0.0, front = 0.0, under = 0.0, tape = 1.0, lift = 0.0, faceBefore = 1.0, face = 0.0, glints = 0.0, stance = 0.0)
        val open = PipOpening(rear = 1.0, front = 1.0, under = 1.0, tape = 0.0, lift = 1.0, faceBefore = 0.0, face = 1.0, glints = 1.0, stance = 1.0)

        /** Seconds from the news to an open box at rest. */
        const val DURATION = 1.0

        private val ease = UnitCurve.bezier(Point(0.22, 1.0), Point(0.36, 1.0))

        /**
         * The box so many seconds after the news: the rear flaps fold away first, the front ones swing up
         * through upright and settle, the tape lifts off, and the glints come once the flaps are up.
         */
        fun at(seconds: Double): PipOpening {
            // Each part has its own stretch of the second.
            fun part(from: Double, length: Double): Double = swiftMin(1.0, swiftMax(0.0, (seconds - from) / length))
            val lift = ease.value(part(0.0, 0.4))
            return PipOpening(
                rear = ease.value(part(0.07, 0.62)), front = PipGeometry.settle(part(0.17, 0.78)), under = part(0.1, 0.3),
                tape = 1 - part(0.05, 0.22), lift = lift, faceBefore = 1 - part(0.0, 0.22), face = part(0.12, 0.3),
                glints = part(0.62, 0.35), stance = lift,
            )
        }
    }
}

/** The box in its 300 × 310 frame, shared by the kraft parcel and by Pip on the card maps. */
object PipGeometry {
    val frame = Size(300.0, 310.0)

    /** Pip stands on this point of the frame. */
    val ground = Point(150.0, 282.0)

    /** How far an eager Pip is lifted off the ground. */
    const val EAGER_LIFT = 16.0

    /** Where Pip may stand: his ground point, as offsets from the dot in Pip widths, best first. */
    val spots = listOf(
        Point(0.66, 0.32), Point(-0.66, 0.32), Point(0.62, -0.02), Point(-0.62, -0.02),
        Point(0.4, 0.78), Point(-0.4, 0.78), Point(0.0, -0.26),
    )

    /** The last resort, straight below the dot: for a dot at the frame's edge, with its route and its name on the open sides. */
    val spotBelow = Point(0.0, 1.08)

    /** The left side's plane, where the face is drawn. */
    val facePlane = AffineTransform(a = 1.0, b = 0.505263, c = 0.0, d = 1.0, tx = 55.0, ty = 142.0)

    /** The right side's plane, where the carrier's label sits: just below where the open front flap reaches. */
    val labelPlane = AffineTransform(a = 1.0, b = -0.505263, c = 0.0, d = 1.0, tx = 160.0, ty = 209.0)

    /** The label's own measures, as the web draws it. A name or a number too long for its line is set narrower to fit. */
    object Label {
        val size = Size(74.0, 48.0)
        const val SCALE = 0.97
        const val CORNER_RADIUS = 2.5
        const val MARGIN = 6.0

        /** The truck at half its size, and the name beside it. */
        val truck = Point(5.0, 5.0)
        val nameStart = Point(23.0, 13.0)
        const val NAME_SIZE = 7.0

        /** The barcode's bars, down from 20 to 34. */
        val bars = listOf(6.0, 8.2, 11.2, 12.8, 16.2, 18.2, 21.2, 22.6, 26.2, 28.2, 30.8, 32.6, 35.8, 37.8, 40.2, 43.2, 44.8)
        const val BAR_WIDTH = 1.05
        const val NUMBER_BASELINE = 43.0
        const val NUMBER_SIZE = 5.6
        const val NUMBER_SPACING = 0.3

        /** A point of the label, in Pip's frame. */
        fun point(x: Double, y: Double): Point = Point(x * SCALE, y * SCALE).applying(labelPlane)
    }

    val inside = points(55.0 to 142.0, 150.0 to 95.0, 245.0 to 142.0, 150.0 to 190.0)
    val leftSide = points(55.0 to 142.0, 150.0 to 190.0, 150.0 to 277.0, 55.0 to 229.0)
    val rightSide = points(150.0 to 190.0, 245.0 to 142.0, 245.0 to 229.0, 150.0 to 277.0)
    val tape = points(96.0 to 122.0, 109.0 to 115.0, 204.0 to 163.0, 191.0 to 170.0)
    val seam = points(103.0 to 119.0, 197.0 to 166.0)

    /** A flap closed on the box, and folded open on its hinge. */
    class Flap(
        val closed: List<Point>,
        val opened: List<Point>,
        /**
         * A flap that swings up through upright to open, rather than folding straight over:
         * how far it turns on its hinge, in degrees.
         */
        val swing: Double? = null,
    ) {
        /** The flap part of the way open: 0 closed, 1 open, and a little more while it goes too far. */
        fun points(at: Double): List<Point> {
            val progress = at
            if (swing == null) {
                return closed.zip(opened) { from, to -> Point(from.x + (to.x - from.x) * progress, from.y + (to.y - from.y) * progress) }
            }
            // Each corner turns about its end of the hinge: at a turn of `a` it stands at cos(a) of where
            // it lies closed, plus sin(a) of where it would stand upright.
            val full = swing * PI / 180
            val turn = full * swiftMax(0.0, progress)
            val hinge = listOf(closed[0], closed[1], closed[1], closed[0])
            return closed.indices.map { index ->
                val from = Point(closed[index].x - hinge[index].x, closed[index].y - hinge[index].y)
                val to = Point(opened[index].x - hinge[index].x, opened[index].y - hinge[index].y)
                val upright = Point((to.x - cos(full) * from.x) / sin(full), (to.y - cos(full) * from.y) / sin(full))
                Point(
                    hinge[index].x + cos(turn) * from.x + sin(turn) * upright.x,
                    hinge[index].y + cos(turn) * from.y + sin(turn) * upright.y,
                )
            }
        }
    }

    val backLeft = Flap(
        closed = points(55.0 to 142.0, 150.0 to 95.0, 190.0 to 143.0, 95.0 to 190.0),
        opened = points(55.0 to 142.0, 150.0 to 95.0, 112.0 to 48.0, 17.0 to 95.0),
    )
    val backRight = Flap(
        closed = points(150.0 to 95.0, 245.0 to 142.0, 197.5 to 166.0, 102.5 to 118.5),
        opened = points(150.0 to 95.0, 245.0 to 142.0, 270.0 to 88.0, 174.24 to 41.32),
    )

    // The front flaps rest just above level, clear of the sides: the left one is a brim over Pip's eyes.
    val frontRight = Flap(
        closed = points(245.0 to 142.0, 150.0 to 190.0, 102.5 to 166.0, 197.5 to 118.5),
        opened = points(245.0 to 142.0, 150.0 to 190.0, 193.0 to 189.0, 287.06 to 141.26),
        swing = 154.0,
    )
    val frontLeft = Flap(
        closed = points(55.0 to 142.0, 150.0 to 190.0, 197.5 to 166.0, 102.5 to 118.5),
        opened = points(55.0 to 142.0, 150.0 to 190.0, 107.0 to 189.0, 12.94 to 141.26),
        swing = 154.0,
    )

    /**
     * A flap thrown open goes a little too far, then settles: its share of the turn, over the time it is given.
     * That time is a stretch of the box's opening, which is eased already, so the swing is spread wide over it.
     */
    fun settle(progress: Double): Double {
        val swing = { time: Double -> 1 - exp(-5 * time) * cos(5.6 * time) }
        return swing(swiftMin(1.0, swiftMax(0.0, progress))) / swing(1.0)
    }

    /** One of the glints around an open box: where, and how wide. */
    data class Glint(val center: Point, val size: Double)

    val glints = listOf(
        Glint(Point(43.0, 96.0), 23.0), Glint(Point(91.0, 57.0), 16.0), Glint(Point(151.0, 36.0), 24.0),
        Glint(Point(216.0, 55.0), 18.0), Glint(Point(261.0, 96.0), 25.0), Glint(Point(233.0, 145.0), 13.0),
    )

    private fun points(vararg pairs: Pair<Double, Double>): List<Point> = pairs.map { Point(it.first, it.second) }

    private val box = points(55.0 to 142.0, 150.0 to 95.0, 245.0 to 142.0, 245.0 to 229.0, 150.0 to 277.0, 55.0 to 229.0)
    private val openBox = points(
        17.0 to 95.0, 91.0 to 45.0, 151.0 to 24.0, 216.0 to 46.0, 274.0 to 92.0, 288.0 to 141.0, 245.0 to 229.0, 150.0 to 277.0,
        55.0 to 229.0, 12.0 to 141.0,
    )
    private val speedLines = Rect(0.0, 166.0, 44.0, 60.0)

    /** The frame's box that holds Pip: the box itself, its open flaps, or the speed lines trailing away from the dot. */
    fun extents(mood: PipMood, side: Int): Rect = when (mood) {
        // The open flaps reach a little further than the box they are hinged on.
        PipMood.Joy -> Rect(12.0, 24.0, 276.0, 264.0)
        PipMood.Eager -> Rect(if (side < 0) 52.0 else 0.0, 92 - EAGER_LIFT, 248.0, 196 + EAGER_LIFT)
        PipMood.Look, PipMood.Wait, PipMood.Worry -> Rect(52.0, 92.0, 196.0, 196.0)
    }

    /** What Pip actually covers, as convex outlines in the frame: the corners of his box are empty. */
    fun outlines(mood: PipMood, side: Int): List<List<Point>> = when (mood) {
        PipMood.Joy -> listOf(openBox)
        PipMood.Look, PipMood.Wait, PipMood.Worry -> listOf(box)
        PipMood.Eager -> {
            val lines = speedLines.offsetBy(if (side < 0) frame.width - speedLines.width else 0.0, 0.0)
            listOf(
                box.map { Point(it.x, it.y - EAGER_LIFT) },
                listOf(Point(lines.minX, lines.minY), Point(lines.maxX, lines.minY), Point(lines.maxX, lines.maxY), Point(lines.minX, lines.maxY)),
            )
        }
    }

    /** How far a point is from a convex outline: 0 inside it. */
    fun distance(from: Point, to: List<Point>): Double {
        val point = from
        var nearest = Double.POSITIVE_INFINITY
        var turns = 0
        for ((index, a) in to.withIndex()) {
            val b = to[(index + 1) % to.size]
            val cross = (b.x - a.x) * (point.y - a.y) - (b.y - a.y) * (point.x - a.x)
            turns += if (cross > 0) 1 else if (cross < 0) -1 else 0
            val length = (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y)
            val along = swiftMax(0.0, swiftMin(1.0, ((point.x - a.x) * (b.x - a.x) + (point.y - a.y) * (b.y - a.y)) / (if (length > 0) length else 1.0)))
            nearest = swiftMin(nearest, hypot(point.x - a.x - along * (b.x - a.x), point.y - a.y - along * (b.y - a.y)))
        }
        // Inside, every edge turns the same way.
        return if (abs(turns) == to.size) 0.0 else nearest
    }
}

// MARK: - Colours

/** Every colour Pip is drawn with: kraft paper, or a card's ink mixed toward its surface. */
data class PipPalette(
    val inside: AdaptiveColor,
    val right: AdaptiveColor,
    val left: AdaptiveColor,
    val backLeft: AdaptiveColor,
    val backRight: AdaptiveColor,
    val frontRight: AdaptiveColor,
    val frontLeft: AdaptiveColor,
    val tape: AdaptiveColor,
    val seam: AdaptiveColor,
    val hairline: AdaptiveColor,
    val highlight: AdaptiveColor,
    val eyeWhite: AdaptiveColor,
    /** Pupils and mouth. */
    val features: AdaptiveColor,
    val catchlight: AdaptiveColor,
    val blush: AdaptiveColor,
    /** The three tones the glints alternate between. */
    val glints: List<AdaptiveColor>,
    val shadow: AdaptiveColor,
    val speedLines: AdaptiveColor,
) {
    /** The tone of `PipGeometry.glints[index]`. */
    fun glint(index: Int): AdaptiveColor = glints[GLINT_TONES[index]]

    companion object {
        private val GLINT_TONES = listOf(0, 1, 0, 2, 1, 0)

        private fun hex(hex: String): AdaptiveColor = AdaptiveColor(RgbaColor.hex(hex))
        private val black = AdaptiveColor(RgbaColor(0.0, 0.0, 0.0))
        private val white = AdaptiveColor(RgbaColor(1.0, 1.0, 1.0))

        val kraft = PipPalette(
            inside = hex("#806345"), right = hex("#B78F66"), left = hex("#C9A47B"),
            backLeft = hex("#C4A078"), backRight = hex("#D8B997"), frontRight = hex("#D1AE85"),
            frontLeft = hex("#DDBD96"), tape = hex("#EBDDCA"), seam = hex("#AF9474"),
            hairline = hex("#987450"), highlight = hex("#FFF2CF"), eyeWhite = hex("#FFFDF6"),
            features = hex("#20251E"), catchlight = white, blush = hex("#E9958F"),
            glints = listOf(hex("#C99B35"), hex("#D6AE48"), hex("#B594BE")),
            shadow = black.opacity(0.08), speedLines = hex("#B78F66").opacity(0.45),
        )

        /**
         * The card's own colours. On a dark card the surface is the darker of the two, so the eyes keep
         * their white and the pupils their depth.
         */
        fun ink(ink: AdaptiveColor, on: AdaptiveColor, dark: Boolean): PipPalette {
            val surface = on
            val mix = { amount: Double -> surface.mix(ink, amount) }
            val deep = (if (dark) surface else ink).mix(black, 0.18)
            val paper = (if (dark) ink else surface).mix(white, 0.62)
            return PipPalette(
                inside = deep, right = mix(0.84), left = mix(0.56), backLeft = mix(0.62), backRight = mix(0.40),
                frontRight = mix(0.50), frontLeft = mix(0.36), tape = mix(0.20), seam = mix(0.70), hairline = ink,
                highlight = paper, eyeWhite = paper, features = deep, catchlight = paper,
                blush = mix(0.56).mix(hex("#F0707E"), 0.55),
                glints = listOf(ink, mix(0.72), mix(0.86)),
                shadow = if (dark) black.opacity(0.26) else deep.opacity(0.14), speedLines = ink.opacity(0.32),
            )
        }
    }
}

/** PipArtwork's arithmetic: his motion and his glints' twinkle. The drawing is :app's. */
object PipArtwork {
    private val spring = UnitCurve.bezier(Point(0.22, 1.0), Point(0.36, 1.0))

    /** How long one bob, or one jump, takes. */
    fun period(mood: PipMood): Double = if (mood == PipMood.Joy) 2.2 else 1.1

    private class Key(val at: Double, val rise: Double, val turn: Double, val across: Double, val up: Double)

    private val jump = listOf(
        Key(0.0, 0.0, 0.0, 1.0, 1.0), Key(0.12, 0.0, 0.0, 1.07, 0.9), Key(0.28, -10.0, -5.0, 0.96, 1.05),
        Key(0.44, 0.0, 2.0, 1.05, 0.95), Key(0.58, 0.0, 0.0, 1.0, 1.0), Key(1.0, 0.0, 0.0, 1.0, 1.0),
    )

    /** How the mood moves him, about the bottom of the box: an eager bob, or a joyful jump. */
    fun motion(mood: PipMood, at: Double): AffineTransform {
        val time = at
        // Rise, turn in degrees, and stretch across and up.
        val rise: Double
        val turn: Double
        val across: Double
        val up: Double
        when (mood) {
            PipMood.Eager -> {
                val phase = (time / period(PipMood.Eager)) % 1
                val swing = UnitCurve.easeInOut.value(if (phase < 0.5) phase * 2 else (1 - phase) * 2)
                rise = -3 * swing
                turn = -2 + 4.5 * swing
                across = 1.0
                up = 1.0
            }
            PipMood.Joy -> {
                val phase = (time / period(PipMood.Joy)) % 1
                val next = jump.indexOfFirst { it.at > phase }.let { if (it < 0) jump.size - 1 else it }
                val from = jump[next - 1]
                val to = jump[next]
                val t = spring.value((phase - from.at) / (to.at - from.at))
                rise = from.rise + (to.rise - from.rise) * t
                turn = from.turn + (to.turn - from.turn) * t
                across = from.across + (to.across - from.across) * t
                up = from.up + (to.up - from.up) * t
            }
            PipMood.Look, PipMood.Wait, PipMood.Worry -> return AffineTransform.Identity
        }
        val pivot = Point(150.0, 277.0)
        return AffineTransform.translation(pivot.x, pivot.y + rise)
            .rotated(turn * PI / 180)
            .scaledBy(across, up)
            .translatedBy(-pivot.x, -pivot.y)
    }

    /** How a glint shows: its opacity, its size as a share of its own, and its turn in degrees. */
    data class Twinkle(val opacity: Double, val scale: Double, val degrees: Double)

    /** Each glint twinkles on its own clock: the one at `PipGeometry.glints[index]`, `at` seconds on it. */
    fun twinkle(index: Int, at: Double): Twinkle {
        val period = 2.2 + (index % 3).toDouble() * 0.5
        val phase = ((at - index.toDouble() * 0.37) / period) % 1
        val wrapped = if (phase < 0) phase + 1 else phase
        val glow = UnitCurve.easeInOut.value(if (wrapped < 0.5) wrapped * 2 else (1 - wrapped) * 2)
        return Twinkle(0.2 + 0.8 * glow, 0.6 + 0.5 * glow, -10 + 18 * glow)
    }
}

/** The kraft parcel blinks while it waits: once a period, each open eye flattens and reopens. */
object PipBlink {
    const val PERIOD = 4.6

    /** How tall a closed eye is, as a share of the open one. */
    const val CLOSED = 0.08

    /** The second eye follows the first. */
    const val LAG = 0.02

    /** The share of the period after which the eyes start to close, and the one at which they are closed. */
    private const val CLOSING = 0.93
    private const val SHUT = 0.96
    private const val FRAME = 1.0 / 60

    /** How tall an eye is, `time` seconds on the blink's clock: 1 when open. */
    fun eyeScale(at: Double, eye: Int = 0): Double {
        val local = at - eye * LAG
        if (!(local > 0)) return 1.0
        val phase = (local / PERIOD) % 1
        if (phase <= CLOSING) return 1.0
        if (phase < SHUT) return 1 - (1 - CLOSED) * UnitCurve.easeInOut.value((phase - CLOSING) / (SHUT - CLOSING))
        return CLOSED + (1 - CLOSED) * UnitCurve.easeInOut.value((phase - SHUT) / (1 - SHUT))
    }

    /** When the eyes next need drawing after `time`: every frame of a blink, and nothing between two blinks. */
    fun nextFrame(after: Double): Double {
        val time = swiftMax(0.0, after)
        val cycle = floor(time / PERIOD)
        // The second eye is still reopening as the next period starts.
        val reopened = cycle * PERIOD + LAG
        if (cycle > 0 && time < reopened) return swiftMin(time + FRAME, reopened)
        val closes = (cycle + CLOSING) * PERIOD
        return if (time < closes) closes else swiftMin(time + FRAME, (cycle + 1) * PERIOD + LAG)
    }
}
