package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.withFrameNanos
import com.plhery.peekdeliverytracker.core.Spring
import com.plhery.peekdeliverytracker.core.UnitCurve

// SwiftUI's keyframe animations (keyframeAnimator, KeyframeTimeline), worked out as SwiftUI works them out, so the
// iPhone's tracks copy over number for number: the bell's ring, Pip's hop, the delivered card's dip, the pull seal's
// pop and the welcome parcel's greeting. Each keyframe ends at its value after its duration; how it gets there:
// - LinearKeyframe moves along its timing curve.
// - CubicKeyframe is a cubic Hermite curve. Its speed at each end is the one given, else the neighbour's: between two
//   cubic keyframes the slope from the value before to the value after (Catmull–Rom over uneven times), next to a
//   linear one that one's own speed there, next to a spring the slope again, at a move or at the track's end 0, and
//   at the track's start the speed the animation had (0 from rest).
// - SpringKeyframe leaves from the previous keyframe's value at that keyframe's speed (or its own startVelocity) and
//   is cut off after its duration wherever the spring is then, as SwiftUI's is, settled or not.
// - MoveKeyframe jumps to its value at once, and anything after it leaves at rest.
// Found with KeyframeTimeline.value(time:) on a Mac (KeyframesTest holds the samples).

/** One keyframe of a track: where the value is once its `duration` (seconds) has passed. */
sealed interface Keyframe {
    val to: Double
    val duration: Double
}

/** `LinearKeyframe(_:duration:timingCurve:)`. */
data class LinearKeyframe(override val to: Double, override val duration: Double, val timingCurve: UnitCurve = UnitCurve.linear) : Keyframe

/** `CubicKeyframe(_:duration:startVelocity:endVelocity:)`. */
data class CubicKeyframe(
    override val to: Double,
    override val duration: Double,
    val startVelocity: Double? = null,
    val endVelocity: Double? = null,
) : Keyframe

/** `SpringKeyframe(_:duration:spring:startVelocity:)`, with its duration given, as the app always gives it. */
data class SpringKeyframe(
    override val to: Double,
    override val duration: Double,
    val spring: Spring = Spring(),
    val startVelocity: Double? = null,
) : Keyframe

/** `MoveKeyframe(_:)`. */
data class MoveKeyframe(override val to: Double) : Keyframe {
    override val duration: Double get() = 0.0
}

/** `KeyframeTrack(\.property) { … }`: the keyframes of one property of `T`, read with `get` and written with `set`. */
class KeyframeTrack<T>(val get: (T) -> Double, val set: (T, Double) -> T, val keyframes: List<Keyframe>) {
    val duration: Double get() = keyframes.sumOf { it.duration }
}

/**
 * `KeyframeTimeline(initialValue:content:)`: `T`'s properties each on their own track from `initialValue`, the others
 * left as they are. `initialVelocities`, a track's speed when the timeline starts (by position), is what a running
 * `keyframeAnimator` hands on when it starts again.
 */
class KeyframeTimeline<T>(val initialValue: T, val tracks: List<KeyframeTrack<T>>, initialVelocities: List<Double?> = emptyList()) {
    private val resolved = tracks.mapIndexed { index, track ->
        ResolvedTrack(track.get(initialValue), initialVelocities.getOrNull(index), track.keyframes)
    }

    /** `duration`: the longest track's. */
    val duration: Double = tracks.maxOfOrNull { it.duration } ?: 0.0

    /** `value(time:)`: every track at `time` seconds, held at its ends before 0 and after its last keyframe. */
    fun value(time: Double): T = tracks.foldIndexed(initialValue) { index, value, track -> track.set(value, resolved[index].value(time)) }

    /** Each track's speed at `time`, in units a second, by position. */
    fun velocities(time: Double): List<Double> = resolved.map { it.velocity(time) }

    companion object {
        /** A timeline of one number, as `keyframeAnimator(initialValue: 1.0, …) { KeyframeTrack { … } }`. */
        fun of(initialValue: Double, vararg keyframes: Keyframe): KeyframeTimeline<Double> =
            KeyframeTimeline(initialValue, listOf(KeyframeTrack({ it }, { _, value -> value }, keyframes.toList())))
    }
}

/** One track laid out in time: where each keyframe starts, from what value and at what speed, and how it ends. */
private class ResolvedTrack(private val initialValue: Double, private val initialVelocity: Double?, keyframes: List<Keyframe>) {
    private class Segment(val keyframe: Keyframe, val startTime: Double, val from: Double, val startVelocity: Double) {
        /** A cubic keyframe's speed at its end, known once the keyframe after it is. */
        var endVelocity = 0.0

        val endTime: Double get() = startTime + keyframe.duration

        fun value(time: Double): Double {
            val elapsed = time - startTime
            return when (val key = keyframe) {
                is MoveKeyframe -> key.to
                is LinearKeyframe -> from + (key.to - from) * key.timingCurve.value(elapsed / key.duration)
                is SpringKeyframe -> from + key.spring.value(key.to - from, startVelocity, elapsed)
                is CubicKeyframe -> {
                    val s = elapsed / key.duration
                    val s2 = s * s
                    val s3 = s2 * s
                    (2 * s3 - 3 * s2 + 1) * from + (s3 - 2 * s2 + s) * key.duration * startVelocity +
                        (-2 * s3 + 3 * s2) * key.to + (s3 - s2) * key.duration * endVelocity
                }
            }
        }

        fun velocity(time: Double): Double {
            val elapsed = time - startTime
            return when (val key = keyframe) {
                is MoveKeyframe -> 0.0
                is LinearKeyframe -> (key.to - from) / key.duration * key.timingCurve.velocity(elapsed / key.duration)
                is SpringKeyframe -> key.spring.velocity(key.to - from, startVelocity, elapsed)
                is CubicKeyframe -> {
                    val s = elapsed / key.duration
                    val s2 = s * s
                    ((6 * s2 - 6 * s) * from + (3 * s2 - 4 * s + 1) * key.duration * startVelocity +
                        (-6 * s2 + 6 * s) * key.to + (3 * s2 - 2 * s) * key.duration * endVelocity) / key.duration
                }
            }
        }

        /** Where the keyframe leaves the value: a spring wherever it is when cut off. */
        val end: Double get() = if (keyframe is SpringKeyframe) value(endTime) else keyframe.to

        /** The speed it hands to the keyframe after it. */
        val handover: Double get() = when (keyframe) {
            is MoveKeyframe -> 0.0
            is CubicKeyframe -> endVelocity
            else -> velocity(endTime)
        }
    }

    private val segments = ArrayList<Segment>(keyframes.size)

    init {
        var time = 0.0
        var value = initialValue
        for (key in keyframes) {
            val previous = segments.lastOrNull()
            val cubicBefore = previous != null && previous.keyframe is CubicKeyframe
            if (previous != null && cubicBefore) {
                previous.endVelocity = (previous.keyframe as CubicKeyframe).endVelocity ?: when (key) {
                    is MoveKeyframe -> 0.0
                    is LinearKeyframe -> (key.to - value) / key.duration * key.timingCurve.velocity(0.0)
                    is CubicKeyframe -> key.startVelocity ?: slope(previous, key)
                    is SpringKeyframe -> key.startVelocity ?: slope(previous, key)
                }
            }
            val incoming = previous?.handover ?: initialVelocity ?: 0.0
            val startVelocity = when (key) {
                is MoveKeyframe, is LinearKeyframe -> 0.0
                // After a cubic keyframe, the slope across the boundary, even where that one was given its own end speed.
                is CubicKeyframe -> key.startVelocity ?: if (previous != null && cubicBefore) slope(previous, key) else incoming
                is SpringKeyframe -> key.startVelocity ?: incoming
            }
            val segment = Segment(key, time, value, startVelocity)
            segments += segment
            time += key.duration
            value = segment.end
        }
    }

    /** The slope across the boundary after `previous`: from where it starts to where `next` ends. */
    private fun slope(previous: Segment, next: Keyframe): Double =
        (next.to - previous.from) / (previous.endTime + next.duration - previous.startTime)

    /** The value at `time`; a move applies from its moment on, one at the start even before it. */
    fun value(time: Double): Double {
        var value = initialValue
        for (segment in segments) {
            when {
                segment.keyframe is MoveKeyframe -> if (segment.startTime <= time || segment.startTime == 0.0) value = segment.end else break
                time >= segment.endTime -> value = segment.end
                time > segment.startTime -> return segment.value(time)
                else -> break
            }
        }
        return value
    }

    /** The speed at `time`: the animation's own before it starts, nothing once the track has ended. */
    fun velocity(time: Double): Double {
        if (time <= 0) return initialVelocity ?: 0.0
        val segment = segments.firstOrNull { time > it.startTime && time < it.endTime } ?: return 0.0
        return segment.velocity(time)
    }
}

/**
 * `keyframeAnimator(initialValue:trigger:content:keyframes:)` on the app's own clock: holds `initialValue` until
 * `play`, then runs the timeline `keyframes` makes, from the value shown and each track's speed at that moment, so
 * playing again before the end carries on smoothly. After the end it holds the timeline's last value.
 */
class KeyframeAnimatorState<T>(initialValue: T) {
    private var timeline: KeyframeTimeline<T>? = null
    private var started = 0.0

    /** The value as of the last `value(now)` or `play`. */
    var current: T = initialValue
        private set

    /** Starts the keyframes at `now` (seconds). `keyframes` gets the value as it is, as SwiftUI's closure does. */
    fun play(now: Double, keyframes: (T) -> KeyframeTimeline<T>) {
        val running = timeline
        val from = value(now)
        val speeds = if (running != null && now - started < running.duration) running.velocities(now - started) else emptyList()
        val next = keyframes(from)
        timeline = KeyframeTimeline(from, next.tracks, speeds)
        started = now
    }

    /** The value at `now`. */
    fun value(now: Double): T {
        val running = timeline ?: return current
        current = running.value(now - started)
        return current
    }

    /** Whether the keyframes still run at `now`. */
    fun isRunning(now: Double): Boolean = timeline?.let { now - started < it.duration } ?: false
}

/**
 * `keyframeAnimator(initialValue:trigger:…)`: the value `keyframes` gives, played each time `trigger` changes (not when
 * the content first appears), frame by frame. Read it where it is drawn (`graphicsLayer { }`) so each frame redraws
 * rather than recomposes. Reduce Motion is the caller's, as on the iPhone: each site keeps its own rule. A site that
 * draws none of it meanwhile keeps it `still`, which asks for no frames.
 */
@Composable
fun <T> keyframeAnimator(initialValue: T, trigger: Any?, still: Boolean = false, keyframes: (T) -> KeyframeTimeline<T>): State<T> {
    val animator = remember { KeyframeAnimatorState(initialValue) }
    val shown = remember { mutableStateOf(initialValue) }
    val latest by rememberUpdatedState(keyframes)
    val stillNow by rememberUpdatedState(still)
    val appeared = remember { booleanArrayOf(false) }
    LaunchedEffect(trigger) {
        if (!appeared[0]) {
            appeared[0] = true
            return@LaunchedEffect
        }
        if (stillNow) return@LaunchedEffect
        var now = withFrameNanos { it } / 1e9
        animator.play(now, latest)
        shown.value = animator.value(now)
        while (animator.isRunning(now)) {
            now = withFrameNanos { it } / 1e9
            shown.value = animator.value(now)
        }
    }
    return shown
}
