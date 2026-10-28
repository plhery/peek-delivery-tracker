package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorConverter
import com.plhery.peekdeliverytracker.core.MotionCurve
import com.plhery.peekdeliverytracker.core.Spring
import com.plhery.peekdeliverytracker.core.UnitCurve
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** The springs and curves against SwiftUI's own numbers (`Spring.stiffness`, `.damping`, `.dampingRatio` on a Mac). */
class MotionTest {
    private fun assertSpring(stiffness: Double, dampingRatio: Double, spec: SpringSpec<Float>) {
        assertEquals(stiffness.toFloat(), spec.stiffness, 0.01f)
        assertEquals(dampingRatio.toFloat(), spec.dampingRatio, 0.0001f)
    }

    @Test
    fun springPresetsAreSwiftUIs() {
        assertSpring(157.91367, 1.0, Motion.smooth())
        assertSpring(157.91367, 0.85, Motion.snappy())
        assertSpring(157.91367, 0.7, Motion.bouncy())
        assertSpring(438.64908, 0.83, Motion.snappy(duration = 0.3, extraBounce = 0.02))
    }

    @Test
    fun aResponseAndDampingFractionAreADurationAndBounce() {
        assertSpring(438.64908, 0.64, Motion.springWithResponse(response = 0.3, dampingFraction = 0.64))
        val response = Motion.springWithResponse<Float>(response = 0.42, dampingFraction = 0.86)
        val duration = Motion.spring<Float>(duration = 0.42, bounce = 0.14)
        assertEquals(duration.stiffness, response.stiffness)
        assertEquals(duration.dampingRatio, response.dampingRatio, 1e-6f)
    }

    @Test
    fun springsComeFromCoresSpring() {
        val spring = Spring(duration = 0.9, bounce = 0.2)
        assertSpring(spring.stiffness, spring.dampingRatio, Motion.spring(duration = 0.9, bounce = 0.2))
        assertSpring(spring.stiffness, 0.8, spring.animationSpec())
    }

    @Test
    fun theCurvesHaveSwiftUIsControlPoints() {
        val expected = mapOf(
            UnitCurve.easeIn to listOf(0.42, 0.0, 1.0, 1.0),
            UnitCurve.easeOut to listOf(0.0, 0.0, 0.58, 1.0),
            UnitCurve.easeInOut to listOf(0.42, 0.0, 0.58, 1.0),
            UnitCurve.linear to listOf(0.0, 0.0, 1.0, 1.0),
        )
        for ((curve, points) in expected) {
            val actual = listOf(curve.startControlPoint.x, curve.startControlPoint.y, curve.endControlPoint.x, curve.endControlPoint.y)
            assertEquals(points, actual)
        }
    }

    @Test
    fun composesEasingFollowsCoresCurve() {
        for (curve in listOf(UnitCurve.easeIn, UnitCurve.easeOut, UnitCurve.easeInOut, UnitCurve.linear)) {
            val easing = curve.easing
            for (step in 0..100) {
                val x = step / 100.0
                assertEquals("at $x", curve.value(x).toFloat(), easing.transform(x.toFloat()), 0.0005f)
            }
        }
    }

    @Test
    fun curvesLastTheirDurationOrSwiftUIsDefault() {
        val easeOut = Motion.easeOut<Float>(0.22) as TweenSpec<Float>
        assertEquals(220, easeOut.durationMillis)
        assertEquals(350, (Motion.easeInOut<Float>() as TweenSpec<Float>).durationMillis)
        assertEquals(1850, (Motion.linear<Float>(1.85) as TweenSpec<Float>).durationMillis)
        assertEquals(280, (Motion.easeIn<Float>(0.28) as TweenSpec<Float>).durationMillis)
    }

    @Test
    fun aDelayedAnimationHoldsItsStartThenRunsTheWholeAnimation() {
        val spec = Motion.easeOut<Float>(0.2).delay(0.25).vectorize(Float.VectorConverter)
        val start = AnimationVector1D(0f)
        val end = AnimationVector1D(100f)
        val still = AnimationVector1D(0f)
        assertEquals(450_000_000L, spec.getDurationNanos(start, end, still))
        assertEquals(0f, spec.getValueFromNanos(249_000_000L, start, end, still).value, 0f)
        val plain = Motion.easeOut<Float>(0.2).vectorize(Float.VectorConverter)
        val halfway = plain.getValueFromNanos(100_000_000L, start, end, still).value
        assertEquals(halfway, spec.getValueFromNanos(350_000_000L, start, end, still).value, 0.001f)
        assertEquals(100f, spec.getValueFromNanos(450_000_000L, start, end, still).value, 0.001f)
    }

    @Test
    fun coresMotionCurvesBecomeTheSameSpecs() {
        val easeOut = MotionCurve.EaseOut(duration = 0.35, delay = 0.08).animationSpec<Float>().vectorize(Float.VectorConverter)
        val start = AnimationVector1D(0f)
        val end = AnimationVector1D(1f)
        // Halfway through the curve, :core and Compose agree on how far the change has come.
        val at = 0.08 + 0.175
        val compose = easeOut.getValueFromNanos((at * 1e9).toLong(), start, end, start).value
        assertEquals(MotionCurve.EaseOut(0.35, 0.08).value(at).toFloat(), compose, 0.001f)
        val spring = MotionCurve.SpringCurve(Spring(0.5, 0.18)).animationSpec<Float>() as SpringSpec<Float>
        assertSpring(Spring(0.5, 0.18).stiffness, 0.82, spring)
        assertTrue(spring.visibilityThreshold == null)
    }
}
