package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.FloatAnimationSpec
import androidx.compose.animation.core.FloatSpringSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorizedFiniteAnimationSpec
import androidx.compose.animation.core.VectorizedFloatAnimationSpec
import androidx.compose.animation.core.tween
import com.plhery.peekdeliverytracker.core.MotionCurve
import com.plhery.peekdeliverytracker.core.Spring
import com.plhery.peekdeliverytracker.core.UnitCurve
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.math.sqrt

/**
 * Compose's spring for SwiftUI's: Compose's is the same unit-mass model, given its stiffness and damping ratio,
 * overdamped ones included. It settles as SwiftUI's does ([SettlingSpringSpec]).
 */
fun <T> Spring.animationSpec(visibilityThreshold: T? = null): SettlingSpringSpec<T> =
    SettlingSpringSpec(dampingRatio = dampingRatio.toFloat(), stiffness = stiffness.toFloat(), visibilityThreshold = visibilityThreshold)

/**
 * A spring that is done once within a thousandth of its change, or within `visibilityThreshold` when given: SwiftUI's
 * `settlingDuration` of a spring that doesn't bounce is where it comes within a thousandth of its change, rounded up to
 * a tenth of a second. Compose's own `spring` stops within 0.01 of the target in the value's units, a visible jump at
 * the end of a scale or a fraction.
 */
class SettlingSpringSpec<T>(val dampingRatio: Float, val stiffness: Float, val visibilityThreshold: T? = null) : FiniteAnimationSpec<T> {
    override fun <V : AnimationVector> vectorize(converter: TwoWayConverter<T, V>): VectorizedFiniteAnimationSpec<V> =
        if (visibilityThreshold != null) {
            SpringSpec<T>(dampingRatio, stiffness, visibilityThreshold).vectorize(converter)
        } else {
            VectorizedFloatAnimationSpec(SettlingFloatSpring(dampingRatio, stiffness))
        }

    override fun equals(other: Any?): Boolean = other is SettlingSpringSpec<*> && other.dampingRatio == dampingRatio &&
        other.stiffness == stiffness && other.visibilityThreshold == visibilityThreshold

    override fun hashCode(): Int = (dampingRatio.hashCode() * 31 + stiffness.hashCode()) * 31 + visibilityThreshold.hashCode()

    override fun toString(): String = "SettlingSpringSpec(dampingRatio=$dampingRatio, stiffness=$stiffness, visibilityThreshold=$visibilityThreshold)"
}

/** One component of a [SettlingSpringSpec]: Compose's spring motion, ending within a thousandth of its change. */
private class SettlingFloatSpring(private val dampingRatio: Float, private val stiffness: Float) : FloatAnimationSpec {
    private val motion = FloatSpringSpec(dampingRatio, stiffness)

    override fun getValueFromNanos(playTimeNanos: Long, initialValue: Float, targetValue: Float, initialVelocity: Float): Float =
        motion.getValueFromNanos(playTimeNanos, initialValue, targetValue, initialVelocity)

    override fun getVelocityFromNanos(playTimeNanos: Long, initialValue: Float, targetValue: Float, initialVelocity: Float): Float =
        motion.getVelocityFromNanos(playTimeNanos, initialValue, targetValue, initialVelocity)

    override fun getEndVelocity(initialValue: Float, targetValue: Float, initialVelocity: Float): Float = 0f

    override fun getDurationNanos(initialValue: Float, targetValue: Float, initialVelocity: Float): Long {
        // A spring flicked back to where it is moves about as far as its speed over its natural frequency.
        val change = max(abs(targetValue - initialValue), abs(initialVelocity) / sqrt(stiffness))
        if (change == 0f) return 0
        return FloatSpringSpec(dampingRatio, stiffness, SETTLED * change).getDurationNanos(initialValue, targetValue, initialVelocity)
    }

    private companion object {
        const val SETTLED = 1e-3f
    }
}

/** Compose's easing for one of SwiftUI's unit curves, from the same control points. */
val UnitCurve.easing: Easing
    get() = CubicBezierEasing(
        startControlPoint.x.toFloat(),
        startControlPoint.y.toFloat(),
        endControlPoint.x.toFloat(),
        endControlPoint.y.toFloat(),
    )

/** One of :core's animations as a Compose spec. */
fun <T> MotionCurve.animationSpec(): FiniteAnimationSpec<T> = when (this) {
    is MotionCurve.EaseOut -> Motion.easeOut<T>(duration).delay(delay)
    is MotionCurve.Timing -> Motion.curve<T>(curve, duration).delay(delay)
    is MotionCurve.SpringCurve -> spring.animationSpec()
}

/**
 * SwiftUI's animations (`Animation`) as Compose specs, with SwiftUI's defaults: springs half a second long, curves
 * 0.35 s. The springs and curves are :core's (`Spring`, `UnitCurve`), so the numbers live in one place.
 */
object Motion {
    /** `.spring(duration:bounce:)`; a bounce below zero is overdamped. */
    fun <T> spring(duration: Double = 0.5, bounce: Double = 0.0, visibilityThreshold: T? = null): SettlingSpringSpec<T> =
        Spring(duration, bounce).animationSpec(visibilityThreshold)

    /**
     * `.spring(response:dampingFraction:)`: the spring of `Spring(response:dampingRatio:)`, overdamped above 1 as
     * SwiftUI's.
     */
    fun <T> springWithResponse(response: Double, dampingFraction: Double, visibilityThreshold: T? = null): SettlingSpringSpec<T> =
        Spring.response(response, dampingFraction).animationSpec(visibilityThreshold)

    /** `.smooth(duration:extraBounce:)`: no bounce. */
    fun <T> smooth(duration: Double = 0.5, extraBounce: Double = 0.0, visibilityThreshold: T? = null): SettlingSpringSpec<T> =
        spring(duration, extraBounce, visibilityThreshold)

    /** `.snappy(duration:extraBounce:)`: bounce 0.15. */
    fun <T> snappy(duration: Double = 0.5, extraBounce: Double = 0.0, visibilityThreshold: T? = null): SettlingSpringSpec<T> =
        spring(duration, 0.15 + extraBounce, visibilityThreshold)

    /** `.bouncy(duration:extraBounce:)`: bounce 0.3. */
    fun <T> bouncy(duration: Double = 0.5, extraBounce: Double = 0.0, visibilityThreshold: T? = null): SettlingSpringSpec<T> =
        spring(duration, 0.3 + extraBounce, visibilityThreshold)

    /** `.easeIn(duration:)`. */
    fun <T> easeIn(duration: Double = DEFAULT_CURVE_DURATION): FiniteAnimationSpec<T> = curve(UnitCurve.easeIn, duration)

    /** `.easeOut(duration:)`. */
    fun <T> easeOut(duration: Double = DEFAULT_CURVE_DURATION): FiniteAnimationSpec<T> = curve(UnitCurve.easeOut, duration)

    /** `.easeInOut(duration:)`. */
    fun <T> easeInOut(duration: Double = DEFAULT_CURVE_DURATION): FiniteAnimationSpec<T> = curve(UnitCurve.easeInOut, duration)

    /** `.linear(duration:)`. */
    fun <T> linear(duration: Double = DEFAULT_CURVE_DURATION): FiniteAnimationSpec<T> = curve(UnitCurve.linear, duration)

    /** `.timingCurve(_:_:_:_:duration:)` and the other curves: `curve` over `duration` seconds. */
    fun <T> curve(curve: UnitCurve, duration: Double): FiniteAnimationSpec<T> =
        tween(durationMillis = (duration * 1000).roundToInt(), easing = curve.easing)

    const val DEFAULT_CURVE_DURATION = 0.35
}

/**
 * `.delay(_:)`: the animation starts `seconds` later, holding its first value until then.
 *
 * Compose restarts an interrupted animation from the value shown, so a delayed one that takes over holds that value
 * through its delay, where SwiftUI adds the new animation to the one running, which carries on meanwhile. Where an
 * interruption during a delay shows, animate with [AnimatedValue], which adds curves as SwiftUI does.
 */
fun <T> FiniteAnimationSpec<T>.delay(seconds: Double): FiniteAnimationSpec<T> =
    if (seconds <= 0) this else DelayedSpec(this, (seconds * 1_000_000_000).roundToLong())

private class DelayedSpec<T>(private val spec: FiniteAnimationSpec<T>, private val delayNanos: Long) : FiniteAnimationSpec<T> {
    override fun <V : AnimationVector> vectorize(converter: TwoWayConverter<T, V>): VectorizedFiniteAnimationSpec<V> =
        DelayedVectorizedSpec(spec.vectorize(converter), delayNanos)

    override fun equals(other: Any?): Boolean = other is DelayedSpec<*> && other.spec == spec && other.delayNanos == delayNanos

    override fun hashCode(): Int = spec.hashCode() * 31 + delayNanos.hashCode()
}

private class DelayedVectorizedSpec<V : AnimationVector>(
    private val spec: VectorizedFiniteAnimationSpec<V>,
    private val delayNanos: Long,
) : VectorizedFiniteAnimationSpec<V> {
    override fun getValueFromNanos(playTimeNanos: Long, initialValue: V, targetValue: V, initialVelocity: V): V =
        if (playTimeNanos < delayNanos) {
            initialValue
        } else {
            spec.getValueFromNanos(playTimeNanos - delayNanos, initialValue, targetValue, initialVelocity)
        }

    override fun getVelocityFromNanos(playTimeNanos: Long, initialValue: V, targetValue: V, initialVelocity: V): V =
        if (playTimeNanos < delayNanos) {
            initialVelocity
        } else {
            spec.getVelocityFromNanos(playTimeNanos - delayNanos, initialValue, targetValue, initialVelocity)
        }

    override fun getDurationNanos(initialValue: V, targetValue: V, initialVelocity: V): Long =
        delayNanos + spec.getDurationNanos(initialValue, targetValue, initialVelocity)
}
