package com.plhery.peekdeliverytracker.ui.theme

import com.plhery.peekdeliverytracker.core.MotionCurve
import com.plhery.peekdeliverytracker.core.UnitCurve
import kotlin.math.abs

/**
 * One number animated as SwiftUI animates a property, on a clock the caller keeps in seconds (frame times):
 * - A curve (`.easeOut(duration:)`) adds its change to whatever is still running, as SwiftUI's curves do: every
 *   change plays out in full from when it was made, and the value shown is the newest target less what each change
 *   still has to cover. A press let go halfway eases back from where it was, with no jump.
 * - A spring takes over everything running: it leaves from the value and the speed shown at that moment, as SwiftUI
 *   merges springs.
 * - No animation sets the value at once and stops whatever ran.
 */
class AnimatedValue(initial: Double) {
    private class Change(val animation: MotionCurve, val delta: Double, val initialVelocity: Double, val start: Double) {
        /** What this change has still to cover at `now`. */
        fun remaining(now: Double): Double {
            val time = now - start
            return when (animation) {
                is MotionCurve.EaseOut -> delta * (1 - animation.value(time))
                is MotionCurve.SpringCurve -> delta - animation.spring.value(delta, initialVelocity, time)
            }
        }

        fun velocity(now: Double): Double {
            val time = now - start
            return when (animation) {
                is MotionCurve.EaseOut -> {
                    val progress = (time - animation.delay) / animation.duration
                    if (progress <= 0 || progress >= 1) 0.0 else delta * UnitCurve.easeOut.velocity(progress) / animation.duration
                }
                is MotionCurve.SpringCurve -> animation.spring.velocity(delta, initialVelocity, time)
            }
        }

        fun finished(now: Double): Boolean = when (animation) {
            is MotionCurve.EaseOut -> now - start >= animation.delay + animation.duration
            is MotionCurve.SpringCurve -> abs(remaining(now)) < SETTLED * abs(delta).coerceAtLeast(1.0) &&
                abs(velocity(now)) < SETTLED * 10
        }
    }

    /** Where the value is going: the last value given to `animate`. */
    var target: Double = initial
        private set

    private val changes = mutableListOf<Change>()

    /** Moves toward `to` with `animation` from `now`, or at once with none. */
    fun animate(to: Double, animation: MotionCurve?, now: Double) {
        when (animation) {
            null -> changes.clear()
            is MotionCurve.EaseOut -> changes += Change(animation, to - target, 0.0, now)
            is MotionCurve.SpringCurve -> {
                val shown = value(now)
                val speed = velocity(now)
                changes.clear()
                changes += Change(animation, to - shown, speed, now)
            }
        }
        target = to
    }

    /** The value shown at `now`. */
    fun value(now: Double): Double = target - changes.sumOf { it.remaining(now) }

    /** How fast the value shown moves at `now`, in units a second. */
    fun velocity(now: Double): Double = changes.sumOf { it.velocity(now) }

    /** Whether anything still moves at `now`; finished changes are let go. */
    fun isRunning(now: Double): Boolean {
        changes.removeAll { it.finished(now) }
        return changes.isNotEmpty()
    }

    private companion object {
        /** A spring is at rest within a ten-thousandth of its change, far below a pixel for the app's values. */
        const val SETTLED = 1e-4
    }
}
