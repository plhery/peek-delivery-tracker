package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorizedFiniteAnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import com.plhery.peekdeliverytracker.core.MotionCurve
import com.plhery.peekdeliverytracker.core.Spring
import com.plhery.peekdeliverytracker.core.UnitCurve
import kotlin.math.roundToInt
import kotlin.math.roundToLong

/** Compose's spring for SwiftUI's: Compose's is the same unit-mass model, given its stiffness and damping ratio. */
fun <T> Spring.animationSpec(visibilityThreshold: T? = null): SpringSpec<T> =
    spring(dampingRatio = dampingRatio.toFloat(), stiffness = stiffness.toFloat(), visibilityThreshold = visibilityThreshold)

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
    is MotionCurve.SpringCurve -> spring.animationSpec()
}

/**
 * SwiftUI's animations (`Animation`) as Compose specs, with SwiftUI's defaults: springs half a second long, curves
 * 0.35 s. The springs and curves are :core's (`Spring`, `UnitCurve`), so the numbers live in one place.
 */
object Motion {
    /** `.spring(duration:bounce:)`. */
    fun <T> spring(duration: Double = 0.5, bounce: Double = 0.0, visibilityThreshold: T? = null): SpringSpec<T> =
        Spring(duration, bounce).animationSpec(visibilityThreshold)

    /**
     * `.spring(response:dampingFraction:)`: the same spring as `spring(duration = response, bounce = 1 - dampingFraction)`.
     * The app's damping fractions are all between 0 and 1.
     */
    fun <T> springWithResponse(response: Double, dampingFraction: Double, visibilityThreshold: T? = null): SpringSpec<T> =
        spring(response, 1 - dampingFraction, visibilityThreshold)

    /** `.smooth(duration:extraBounce:)`: no bounce. */
    fun <T> smooth(duration: Double = 0.5, extraBounce: Double = 0.0, visibilityThreshold: T? = null): SpringSpec<T> =
        spring(duration, extraBounce, visibilityThreshold)

    /** `.snappy(duration:extraBounce:)`: bounce 0.15. */
    fun <T> snappy(duration: Double = 0.5, extraBounce: Double = 0.0, visibilityThreshold: T? = null): SpringSpec<T> =
        spring(duration, 0.15 + extraBounce, visibilityThreshold)

    /** `.bouncy(duration:extraBounce:)`: bounce 0.3. */
    fun <T> bouncy(duration: Double = 0.5, extraBounce: Double = 0.0, visibilityThreshold: T? = null): SpringSpec<T> =
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

/** `.delay(_:)`: the animation starts `seconds` later, holding its first value until then. */
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
