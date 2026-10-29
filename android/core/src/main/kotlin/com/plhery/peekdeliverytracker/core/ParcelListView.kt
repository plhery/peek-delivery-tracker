package com.plhery.peekdeliverytracker.core

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.sqrt

// From ParcelListView.swift, what isn't the view: the archive swipe's arithmetic and the springs that move it. The
// gesture, the action drawn behind the card and the haptics are the app's.

/**
 * Finger travel to revealed width, and where a released swipe settles. Matches the web card: one to one across the
 * action, resisted up to the commit point, softly bounded after, and a released speed that can open, close or archive.
 */
class ArchiveSwipeState private constructor(
    val reveal: Double,
    private val origin: Origin?,
    /** Unknown until the drag has actually moved. */
    private val horizontal: Boolean?,
) {
    constructor() : this(0.0, null, null)

    enum class Destination { Closed, Revealed, Archive }

    /** A drag let go or cancelled: the swipe after it, and where the card settles, or null when it never was a swipe. */
    class Ended(val state: ArchiveSwipeState, val destination: Destination?)

    private class Origin(val travel: Double, val slop: Double)

    /** The first points of a drag only pick its direction, so the card starts moving without a jump. */
    fun drag(translation: Size, width: Double): ArchiveSwipeState {
        // On iOS 27 a recognized pan reports zero first and measures from there, so
        // there is nothing to absorb; earlier versions report the travel so far.
        val origin = origin ?: Origin(
            travel(forReveal = reveal, width = width),
            swiftMin(swiftMax(translation.width, -HYSTERESIS), HYSTERESIS),
        )
        // Judging a zero translation would call every drag vertical.
        val horizontal = horizontal
            ?: if (translation != Size.Zero) abs(translation.width) > abs(translation.height) else null
        if (horizontal != true) return ArchiveSwipeState(reveal, origin, horizontal)
        return ArchiveSwipeState(reveal(forTravel = origin.travel - (translation.width - origin.slop), width = width), origin, true)
    }

    /** `velocity` is the finger's horizontal speed in points per second. */
    fun release(velocity: Double, width: Double): Ended {
        val ended = ArchiveSwipeState(reveal, null, null)
        if (horizontal != true || origin == null) return Ended(ended, null)
        return Ended(ended, destination(reveal = reveal, velocity = -velocity, width = width))
    }

    fun cancel(): Ended {
        val ended = ArchiveSwipeState(reveal, null, null)
        if (horizontal != true || origin == null) return Ended(ended, null)
        return Ended(ended, if (reveal > ACTION_WIDTH / 2) Destination.Revealed else Destination.Closed)
    }

    fun settle(at: Double): ArchiveSwipeState = ArchiveSwipeState(at, origin, horizontal)

    companion object {
        const val ACTION_WIDTH = 88.0
        const val HYSTERESIS = 8.0
        const val RESISTANCE = 0.8

        /** Release speeds in points per second: a flick picks open or closed, a throw past the action archives. */
        const val FLICK_SPEED = 110.0
        const val THROW_SPEED = 1000.0

        fun commitPoint(width: Double): Double = swiftMax(ACTION_WIDTH + 56, width * 0.5)

        private fun rubber(distance: Double, limit: Double): Double = limit * (1 - 1 / (1 + RESISTANCE * distance / limit))

        private fun unrubber(value: Double, limit: Double): Double = value * limit / (RESISTANCE * (limit - value))

        fun reveal(forTravel: Double, width: Double): Double {
            val travel = forTravel
            val commit = commitPoint(width)
            val knee = ACTION_WIDTH + (commit - ACTION_WIDTH) / RESISTANCE
            if (travel < 0) return -rubber(-travel, limit = ACTION_WIDTH / 2)
            if (travel <= ACTION_WIDTH) return travel
            if (travel <= knee) return ACTION_WIDTH + (travel - ACTION_WIDTH) * RESISTANCE
            return commit + rubber(travel - knee, limit = width - commit)
        }

        /** The finger travel that shows `reveal`, so a caught card continues without a jump. */
        fun travel(forReveal: Double, width: Double): Double {
            val reveal = forReveal
            val commit = commitPoint(width)
            if (reveal < 0) return -unrubber(swiftMin(-reveal, ACTION_WIDTH / 2 - 0.01), limit = ACTION_WIDTH / 2)
            if (reveal <= ACTION_WIDTH) return reveal
            if (reveal <= commit) return ACTION_WIDTH + (reveal - ACTION_WIDTH) / RESISTANCE
            return ACTION_WIDTH + (commit - ACTION_WIDTH) / RESISTANCE +
                unrubber(swiftMin(reveal - commit, width - commit - 0.01), limit = width - commit)
        }

        /** `velocity` is the revealing speed: positive while the card moves left. */
        fun destination(reveal: Double, velocity: Double, width: Double): Destination {
            if (reveal >= commitPoint(width) || (reveal >= ACTION_WIDTH && velocity >= THROW_SPEED)) return Destination.Archive
            if (abs(velocity) >= FLICK_SPEED) return if (velocity > 0) Destination.Revealed else Destination.Closed
            // Slow releases project their speed the way scrolling momentum would.
            return if (reveal + velocity * 0.5 > ACTION_WIDTH / 2) Destination.Revealed else Destination.Closed
        }
    }
}

/**
 * A damped spring with a perceptual `duration` and an optional `bounce`, solved in closed form so a motion can hand
 * over to the next one at any moment with its speed.
 */
class SwipeSpring(val duration: Double, val bounce: Double = 0.0) {
    /** Position and velocity, in units per second, `seconds` after leaving `from` at `velocity`. */
    class State(val value: Double, val velocity: Double)

    fun state(from: Double, to: Double, velocity: Double, at: Double): State {
        val seconds = at
        val omega = 2 * PI / duration
        val damping = 1 - swiftMin(swiftMax(bounce, 0.0), 0.9)
        val offset = from - to
        if (damping < 1) {
            val decay = damping * omega
            val frequency = omega * sqrt(1 - damping * damping)
            val sine = (velocity + decay * offset) / frequency
            val fade = exp(-decay * seconds)
            val cosine = cos(frequency * seconds)
            val sinus = sin(frequency * seconds)
            return State(
                to + fade * (offset * cosine + sine * sinus),
                fade * (velocity * cosine - (decay * velocity + omega * omega * offset) / frequency * sinus),
            )
        }
        val slope = velocity + omega * offset
        val fade = exp(-omega * seconds)
        return State(to + (offset + slope * seconds) * fade, (velocity - omega * slope * seconds) * fade)
    }

    /** Seconds until the spring rests within `precision` of its target. */
    fun settleTime(from: Double, to: Double, velocity: Double, precision: Double = 0.5): Double {
        var seconds = 0.0
        while (seconds < 2) {
            val current = state(from, to, velocity, seconds)
            if (abs(current.value - to) < precision && abs(current.velocity) < precision * 20) return seconds
            seconds += 1.0 / 120
        }
        return 2.0
    }

    override fun equals(other: Any?): Boolean = other is SwipeSpring && duration == other.duration && bounce == other.bounce
    override fun hashCode(): Int = 31 * duration.hashCode() + bounce.hashCode()
    override fun toString(): String = "SwipeSpring(duration=$duration, bounce=$bounce)"

    companion object {
        val snap = SwipeSpring(duration = 0.3)
        val fling = SwipeSpring(duration = 0.42, bounce = 0.15)
        val leap = SwipeSpring(duration = 0.28)
        val exit = SwipeSpring(duration = 0.32)
        val reduced = SwipeSpring(duration = 0.24)
    }
}

/**
 * How far the card has moved left, whether the action has leapt to the card's edge, and whether its label has moved to
 * the middle of the row. Equality is Swift's: 0.0 equals -0.0.
 */
class ArchiveSwipePose(val reveal: Double = 0.0, val spread: Double = 0.0, val land: Double = 0.0) {
    fun copy(reveal: Double = this.reveal, spread: Double = this.spread, land: Double = this.land): ArchiveSwipePose =
        ArchiveSwipePose(reveal, spread, land)

    override fun equals(other: Any?): Boolean =
        other is ArchiveSwipePose && reveal == other.reveal && spread == other.spread && land == other.land

    override fun hashCode(): Int =
        listOf(reveal, spread, land).fold(0) { hash, value -> 31 * hash + (if (value == 0.0) 0.0 else value).hashCode() }

    override fun toString(): String = "ArchiveSwipePose(reveal=$reveal, spread=$spread, land=$land)"
}

/**
 * Springs every channel of the pose to a target. The pose at any moment comes from the closed form, so a new motion
 * starts from the current one's position and speed. Times are in seconds on any one clock; `began` is on it. Each
 * motion is its own instance, as each Swift one has its own id.
 */
class ArchiveSwipeMotion(
    val start: ArchiveSwipePose,
    val target: ArchiveSwipePose,
    velocity: ArchiveSwipePose = ArchiveSwipePose(),
    spring: SwipeSpring,
    reduceMotion: Boolean,
    /** On the way out the action stays on the card's edge: its gap closes instead of widening. */
    val glued: Boolean = false,
    val began: Double = 0.0,
) {
    val velocity: ArchiveSwipePose = if (reduceMotion) ArchiveSwipePose() else velocity
    val revealSpring: SwipeSpring = if (reduceMotion) SwipeSpring.reduced else spring
    val leapSpring: SwipeSpring = if (reduceMotion) SwipeSpring.reduced else SwipeSpring.leap
    val duration: Double = swiftMax(
        revealSpring.settleTime(start.reveal, target.reveal, this.velocity.reveal),
        leapSpring.settleTime(start.spread, target.spread, this.velocity.spread, precision = 0.002),
        leapSpring.settleTime(start.land, target.land, this.velocity.land, precision = 0.002),
    )

    private fun states(seconds: Double): List<SwipeSpring.State> = listOf(
        revealSpring.state(start.reveal, target.reveal, velocity.reveal, seconds),
        leapSpring.state(start.spread, target.spread, velocity.spread, seconds),
        leapSpring.state(start.land, target.land, velocity.land, seconds),
    )

    /** The pose at `at`, on the clock `began` is on. */
    fun pose(at: Double): ArchiveSwipePose = poseAfter(at - began)

    private fun poseAfter(seconds: Double): ArchiveSwipePose {
        if (!(seconds < duration)) return target
        val channels = states(swiftMax(0.0, seconds))
        val pose = ArchiveSwipePose(reveal = channels[0].value, spread = channels[1].value, land = channels[2].value)
        val action = ArchiveSwipeState.ACTION_WIDTH
        val beyond = swiftMin(pose.reveal, target.reveal) - action
        if (glued && start.spread < 1 && beyond > 0) {
            val gap = (1 - start.spread) * swiftMax(0.0, swiftMin(start.reveal, target.reveal) - action)
            val progress = swiftMin(1.0, swiftMax(0.0, (pose.spread - start.spread) / (1 - start.spread)))
            return pose.copy(spread = swiftMin(1.0, swiftMax(0.0, 1 - gap * (1 - progress) / beyond)))
        }
        return pose
    }

    fun velocity(at: Double): ArchiveSwipePose {
        val seconds = at - began
        if (!(seconds < duration)) return ArchiveSwipePose()
        val channels = states(swiftMax(0.0, seconds))
        return ArchiveSwipePose(reveal = channels[0].velocity, spread = channels[1].velocity, land = channels[2].velocity)
    }

    /** Seconds from the start until `reached` holds along the way, or the whole motion. */
    fun time(until: (ArchiveSwipePose) -> Boolean): Double {
        var seconds = 0.0
        while (seconds < duration && !until(poseAfter(seconds))) seconds += 1.0 / 120
        return swiftMin(seconds, duration)
    }
}
