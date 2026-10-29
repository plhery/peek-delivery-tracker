package com.plhery.peekdeliverytracker.core

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin

// DeliveredCardFlight.swift's maths: where a card that takes another place in the list is on its way, and where each
// piece of the paper thrown out of a delivered parcel's card is. The screen keeps the places and draws them.

/** A card's frame in the list, and the middle of the parcel's name from the card's top (`DeliveryCardPlaces.Place`). */
data class DeliveryCardPlace(val frame: Rect, val title: Double)

/**
 * How a flying card changes on its way, from how far along its flight is (`DeliveredFlightPose`). One pose is set
 * again for each frame ([set]), so that drawing a flight makes nothing new.
 */
class DeliveredFlightPose {
    var minX = 0.0
        private set
    var minY = 0.0
        private set
    var width = 0.0
        private set
    var height = 0.0
        private set
    var cornerRadius = 0.0
        private set

    /** How far the card it was has slid, and how far the card it becomes still has to. */
    var leaving = 0.0
        private set
    var arriving = 0.0
        private set

    /** How much of the card it becomes shows over the picture of the one it was. */
    var arrival = 0.0
        private set
    var shadow = 0.0
        private set

    val frame: Rect get() = Rect(minX, minY, width, height)

    fun set(
        from: DeliveryCardPlace,
        to: DeliveryCardPlace,
        was: DeliveryCardKind,
        becomes: DeliveryCardKind,
        lifted: Boolean,
        progress: Double,
    ): DeliveredFlightPose {
        // A card that shrinks does so early in its flight and one that grows does so late, so two tall cards never cross.
        val share = when {
            to.frame.height < from.frame.height -> ramp(progress, 0.0, 0.72)
            to.frame.height > from.frame.height -> ramp(progress, 0.28, 1.0)
            else -> part(progress)
        }
        // The names stay on one line: each card slides by as much as the name stands higher or lower in it.
        val shift = from.title - to.title
        minX = between(from.frame.minX, to.frame.minX, progress)
        minY = between(from.frame.minY, to.frame.minY, progress)
        width = between(from.frame.width, to.frame.width, share)
        height = between(from.frame.height, to.frame.height, share)
        cornerRadius = between(radius(was), radius(becomes), share)
        leaving = -shift * share
        arriving = shift * (1 - share)
        arrival = ramp(share, 0.2, 0.72)
        shadow = if (lifted) swiftMin(ramp(progress, 0.0, 0.22), 1 - ramp(progress, 0.7, 1.0)) else 0.0
        return this
    }

    companion object {
        operator fun invoke(
            from: DeliveryCardPlace,
            to: DeliveryCardPlace,
            was: DeliveryCardKind,
            becomes: DeliveryCardKind,
            lifted: Boolean,
            progress: Double,
        ): DeliveredFlightPose = DeliveredFlightPose().set(from, to, was, becomes, lifted, progress)

        fun radius(kind: DeliveryCardKind): Double = if (kind == DeliveryCardKind.Next) 24.0 else 16.0

        private fun part(value: Double): Double = swiftMin(1.0, swiftMax(0.0, value))

        private fun ramp(value: Double, start: Double, end: Double): Double = part((value - start) / (end - start))

        private fun between(start: Double, end: Double, share: Double): Double = start + (end - start) * share
    }
}

/** Numbers that follow from a seed, so a burst can be told in advance (`SeededNumbers`, SplitMix64). */
class SeededNumbers(seed: ULong) {
    private var state = seed

    fun next(): ULong {
        state += 0x9E37_79B9_7F4A_7C15uL
        var mixed = state
        mixed = (mixed xor (mixed shr 30)) * 0xBF58_476D_1CE4_E5B9uL
        mixed = (mixed xor (mixed shr 27)) * 0x94D0_49BB_1331_11EBuL
        return mixed xor (mixed shr 31)
    }

    /**
     * `Double.random(in: 0..<1, using:)`: the low 53 bits of the next number, as a fraction. Swift draws again for a
     * value equal to 1, which 53 bits never make.
     */
    fun unit(): Double = (next() and UNIT_MASK).toDouble() * UNIT_SCALE

    private companion object {
        val UNIT_MASK = (1uL shl 53) - 1uL
        const val UNIT_SCALE = 1.0 / (1L shl 53)
    }
}

/**
 * Paper thrown when a parcel has arrived: a sharp pop against the air, then a tumbling fall (`DeliveredConfetti`).
 * Where every piece is follows from where it came out, a seed and the time since.
 */
data class DeliveredConfetti(val origin: Point, val pieces: List<Piece>, val glints: List<Glint>) {
    /** A dot, a streamer or a sheet, thrown up and out at its own speed. */
    data class Piece(
        val width: Double,
        val height: Double,
        val round: Boolean,
        val tone: Int,
        val angle: Double,
        val speed: Double,
        val drag: Double,
        val life: Double,
        val spin: Double,
        val tumble: Double,
        val sway: Double,
        val beat: Double,
        val phase: Double,
        val delay: Double,
    )

    /** A glint that flies out a little way and goes. */
    data class Glint(val size: Double, val angle: Double, val reach: Double, val life: Double, val delay: Double, val tone: Int)

    /**
     * Where a piece or a glint is: its centre, its turn in degrees, how much of its face shows as it tumbles (its
     * scale), and its opacity. Set again for each frame, so that drawing the paper makes nothing new.
     */
    class Pose {
        var centerX = 0.0
        var centerY = 0.0
        var turn = 0.0
        var scaleX = 0.0
        var scaleY = 0.0
        var opacity = 0.0
    }

    /** Seconds until the last piece has gone. */
    val duration: Double = swiftMax(pieces.maxOfOrNull { it.delay + it.life } ?: 0.0, glints.maxOfOrNull { it.delay + it.life } ?: 0.0)

    /** Where a piece is so long after the burst; nil before it is thrown and once it has gone. */
    fun pose(piece: Piece, seconds: Double): Pose? = Pose().takeIf { pose(piece, seconds, it) }

    /** [pose] into `into`: false, and `into` as it was, before the piece is thrown and once it has gone. */
    fun pose(piece: Piece, seconds: Double, into: Pose): Boolean {
        val time = seconds - piece.delay
        if (!(time >= 0 && time < piece.life)) return false
        val terminal = 980 / piece.drag
        val slowed = 1 - exp(-piece.drag * time)
        val thrown = piece.speed * cos(piece.angle) / piece.drag * slowed
        val swayed = piece.sway * sin(piece.beat * time + piece.phase) * swiftMin(1.0, time / 0.5)
        val risen = (piece.speed * sin(piece.angle) - terminal) / piece.drag * slowed
        val fallen = terminal * time
        val fade = (1 - time / piece.life) / 0.3
        into.centerX = origin.x + thrown + swayed
        into.centerY = origin.y + fallen + risen
        into.turn = piece.spin * (1 - exp(-1.3 * time)) / 1.3
        into.scaleX = 1.0
        into.scaleY = cos(piece.tumble * time + piece.phase)
        into.opacity = swiftMin(1.0, time / 0.04, fade)
        return true
    }

    /** The same for a glint: out, a turn, and gone. */
    fun pose(glint: Glint, seconds: Double): Pose? = Pose().takeIf { pose(glint, seconds, it) }

    fun pose(glint: Glint, seconds: Double, into: Pose): Boolean {
        val time = seconds - glint.delay
        if (!(time >= 0 && time < glint.life)) return false
        val part = burst.value(time / glint.life)
        // It swells on the first part of its way, and shrinks to nothing on the rest.
        val early = swiftMin(1.0, part / 0.45)
        val late = swiftMax(0.0, (part - 0.45) / 0.55)
        val out = glint.reach * (early * 0.7 + late * 0.3)
        val swell = if (part < 0.45) early * 1.1 else 1.1 * (1 - late)
        into.centerX = origin.x + cos(glint.angle) * out
        into.centerY = origin.y + sin(glint.angle) * out
        into.turn = early * 50 + late * 60
        into.scaleX = swell
        into.scaleY = swell
        into.opacity = swiftMin(1.0, swell)
        return true
    }

    companion object {
        /** More paper from the large card than from a row of the list. */
        const val next = 58
        const val other = 38

        /** How many colours the paper comes in. */
        const val tones = 7

        private val burst = UnitCurve.bezier(Point(0.2, 0.7), Point(0.3, 1.0))

        /** A burst of `count` pieces out of `origin`, as `seed` has it: the pieces first, then the six glints. */
        operator fun invoke(origin: Point, count: Int, seed: ULong): DeliveredConfetti {
            val numbers = SeededNumbers(seed)
            val pieces = List(count) { piece(numbers) }
            val glints = List(6) { glint(it, numbers) }
            return DeliveredConfetti(origin, pieces, glints)
        }

        /** The numbers are drawn in the iPhone's order, so a seed throws the same paper on both. */
        private fun piece(numbers: SeededNumbers): Piece {
            val kind = numbers.unit()
            val width = if (kind < 0.18) 5 + numbers.unit() * 4 else if (kind < 0.36) 3.0 else 5 + numbers.unit() * 6
            val height = if (kind < 0.18) width else if (kind < 0.36) 11 + numbers.unit() * 7 else width * (1.25 + numbers.unit() * 0.8)
            val tone = (numbers.unit() * tones).toInt()
            val degrees = -90 + (numbers.unit() - 0.5) * 140
            return Piece(
                width = width,
                height = height,
                round = kind < 0.18,
                tone = tone,
                angle = degrees * PI / 180,
                speed = 430 + numbers.unit() * 740,
                drag = 2.5 + numbers.unit() * 1.1,
                life = 1.5 + numbers.unit() * 0.8,
                spin = (numbers.unit() - 0.5) * 1500,
                tumble = 7 + numbers.unit() * 11,
                sway = 5 + numbers.unit() * 15,
                beat = 3 + numbers.unit() * 4,
                phase = numbers.unit() * 6.28,
                delay = numbers.unit() * 0.07,
            )
        }

        /** The glints fan out above the box, each a little off its place in the fan. */
        private fun glint(index: Int, numbers: SeededNumbers): Glint {
            val size = 9 + numbers.unit() * 9
            val fan = (index / 5.0 - 0.5) * 190
            val degrees = -90 + fan + (numbers.unit() - 0.5) * 18
            return Glint(
                size = size,
                angle = degrees * PI / 180,
                reach = 30 + numbers.unit() * 34,
                life = 0.52 + numbers.unit() * 0.18,
                delay = numbers.unit() * 0.08,
                tone = if (index % 3 == 1) 2 else 0,
            )
        }

        /**
         * `DeliveredPaperBurst.colors(ink:surface:brand:)`, one for each tone: the card's ink, its carrier's colour and
         * the app's yellow twice, with a lighter mix of the ink, white, and the ink a little toward the yellow.
         */
        fun colors(ink: AdaptiveColor, surface: AdaptiveColor, brand: AdaptiveColor): List<AdaptiveColor> = listOf(
            ink, brand, Brand.accent, Brand.accent, ink.mix(surface, 0.45, GradientColorSpace.Device),
            Brand.color(light = "#FFFFFF", dark = "#F5F6F2"), ink.mix(Brand.accent, 0.3, GradientColorSpace.Device),
        )
    }
}
