package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorConverter
import com.plhery.peekdeliverytracker.core.MotionCurve
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.core.Spring
import com.plhery.peekdeliverytracker.core.UnitCurve
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The springs and curves against SwiftUI's own numbers: `Spring.stiffness`, `.damping` and `.dampingRatio`, and
 * motion/SwiftUIMotion.txt, a swiftc probe's samples of SwiftUI's springs (`value(target:initialVelocity:time:)`,
 * `velocity(...)`, `settlingDuration`) and curves (`UnitCurve.value(at:)`, `velocity(at:)`) on a Mac.
 */
class MotionTest {
    private fun assertSpring(stiffness: Double, dampingRatio: Double, spec: SettlingSpringSpec<Float>) {
        assertEquals(stiffness.toFloat(), spec.stiffness, 0.01f)
        assertEquals(dampingRatio.toFloat(), spec.dampingRatio, 0.0001f)
    }

    private class SpringSample(val name: String, val initialVelocity: Double, val time: Double, val value: Double, val velocity: Double)

    private class CurveSample(val name: String, val x: Double, val value: Double, val velocity: Double)

    private val swiftUI: List<List<String>> = javaClass.getResourceAsStream("/motion/SwiftUIMotion.txt")!!.bufferedReader()
        .readLines().filter { it.isNotBlank() && !it.startsWith("#") }.map { it.split(" ") }

    private val springSamples = swiftUI.filter { it[0] == "spring" }
        .map { SpringSample(it[1], it[2].toDouble(), it[3].toDouble(), it[4].toDouble(), it[5].toDouble()) }

    private val settlingDurations = swiftUI.filter { it[0] == "settle" }.associate { it[1] to it[2].toDouble() }

    private val curveSamples = swiftUI.filter { it[0] == "curve" }
        .map { CurveSample(it[1], it[2].toDouble(), it[3].toDouble(), it[4].toDouble()) }

    /** `d0.5b-0.3` is `Spring(duration: 0.5, bounce: -0.3)`, `r0.5f1.5` `Spring(response: 0.5, dampingRatio: 1.5)`. */
    private fun spring(name: String): Spring {
        val (kind, first, second) = Regex("([dr])([0-9.]+)[bf](-?[0-9.]+)").matchEntire(name)!!.destructured
        return if (kind == "d") Spring(first.toDouble(), second.toDouble()) else Spring.response(first.toDouble(), second.toDouble())
    }

    private val curves = mapOf(
        "easeIn" to UnitCurve.easeIn,
        "easeOut" to UnitCurve.easeOut,
        "easeInOut" to UnitCurve.easeInOut,
        "linear" to UnitCurve.linear,
        "bezier" to UnitCurve.bezier(Point(0.2, 0.9), Point(0.3, 1.0)),
    )

    @Test
    fun theSamplesCoverEverySpringAndCurve() {
        assertEquals(12, settlingDurations.size)
        assertEquals(12 * 3 * 13, springSamples.size)
        assertEquals(settlingDurations.keys, springSamples.map { it.name }.toSet())
        assertEquals(curves.keys, curveSamples.map { it.name }.toSet())
    }

    /**
     * SwiftUI's `velocity` of an overdamped spring is its speed plus the target, still the target once at rest; its
     * `value` moves at the speed :core gives, so :core keeps the true speed.
     */
    @Test
    fun coresSpringsMoveAsSwiftUIs() {
        for (sample in springSamples) {
            val spring = spring(sample.name)
            val label = "${sample.name} from ${sample.initialVelocity} at ${sample.time} s"
            assertEquals(label, sample.value, spring.value(1.0, sample.initialVelocity, sample.time), 1e-9)
            val reported = if (spring.bounce < 0 && sample.time > 0) sample.velocity - 1 else sample.velocity
            assertEquals(label, reported, spring.velocity(1.0, sample.initialVelocity, sample.time), 1e-8)
        }
    }

    @Test
    fun composesSpringsMoveAsSwiftUIs() {
        for (sample in springSamples) {
            val spring = spring(sample.name)
            val spec = spring.animationSpec<Float>().vectorize(Float.VectorConverter)
            val start = AnimationVector1D(0f)
            val end = AnimationVector1D(1f)
            val speed = AnimationVector1D(sample.initialVelocity.toFloat())
            val nanos = (sample.time * 1e9).toLong()
            val label = "${sample.name} from ${sample.initialVelocity} at ${sample.time} s"
            assertEquals(label, sample.value.toFloat(), spec.getValueFromNanos(nanos, start, end, speed).value, 2e-4f)
            val velocity = spring.velocity(1.0, sample.initialVelocity, sample.time)
            assertEquals(label, velocity.toFloat(), spec.getVelocityFromNanos(nanos, start, end, speed).value, 2e-3f)
        }
    }

    /**
     * A spring that doesn't bounce settles where SwiftUI's does: SwiftUI's `settlingDuration` for one is the moment it
     * comes within a thousandth of its change, rounded up to a tenth of a second.
     */
    @Test
    fun springsThatDontBounceSettleWhenSwiftUIsDo() {
        val still = settlingDurations.filterKeys { spring(it).dampingRatio >= 1 - 1e-12 }
        assertEquals(setOf("d0.5b0", "d0.6b0", "d0.5b-0.3", "d0.4b-0.6", "r0.5f1.5", "r0.35f1.2", "r0.55f1"), still.keys)
        for ((name, settling) in still) {
            val spec = spring(name).animationSpec<Float>().vectorize(Float.VectorConverter)
            val duration = spec.getDurationNanos(AnimationVector1D(0f), AnimationVector1D(1f), AnimationVector1D(0f)) / 1e9
            assertTrue("$name settles at $duration s, SwiftUI's at $settling s", duration > settling - 0.1 - 1e-9 && duration <= settling + 1e-9)
        }
    }

    @Test
    fun coresCurvesAreSwiftUIs() {
        // SwiftUI works a curve out in single precision.
        for (sample in curveSamples) {
            val curve = curves.getValue(sample.name)
            assertEquals("${sample.name} at ${sample.x}", sample.value, curve.value(sample.x), 1e-6)
            assertEquals("${sample.name} at ${sample.x}", sample.velocity, curve.velocity(sample.x), 2e-5)
        }
    }

    @Test
    fun composesEasingIsSwiftUIsCurve() {
        for (sample in curveSamples) {
            val easing = curves.getValue(sample.name).easing
            assertEquals("${sample.name} at ${sample.x}", sample.value.toFloat(), easing.transform(sample.x.toFloat()), 5e-4f)
        }
    }

    @Test
    fun anOverdampedSpringKeepsItsStiffness() {
        // SwiftUI reports Spring(response: 0.5, dampingRatio: 1.5).stiffness as 552.7, but moves with (2π / 0.5)².
        val spec = Motion.springWithResponse<Float>(response = 0.5, dampingFraction = 1.5)
        assertSpring(157.91367, 1.5, spec)
        assertSpring(157.91367, 1 / 0.7, Motion.spring(duration = 0.5, bounce = -0.3))
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
        val spring = MotionCurve.SpringCurve(Spring(0.5, 0.18)).animationSpec<Float>() as SettlingSpringSpec<Float>
        assertSpring(Spring(0.5, 0.18).stiffness, 0.82, spring)
        assertTrue(spring.visibilityThreshold == null)
        val linear = MotionCurve.Timing(UnitCurve.linear, 0.4, delay = 0.1).animationSpec<Float>().vectorize(Float.VectorConverter)
        assertEquals(500_000_000L, linear.getDurationNanos(start, end, start))
        assertEquals(0.5f, linear.getValueFromNanos(300_000_000L, start, end, start).value, 0.001f)
    }

    @Test
    fun aSpringGivenAThresholdStopsThereAndOneWithoutAtAThousandthOfItsChange() {
        val start = AnimationVector1D(0f)
        val end = AnimationVector1D(100f)
        val still = AnimationVector1D(0f)
        val settling = Motion.smooth<Float>().vectorize(Float.VectorConverter)
        val settled = settling.getDurationNanos(start, end, still)
        // Compose estimates when a spring comes within its threshold, here to within a hundredth of it.
        assertEquals(100f, settling.getValueFromNanos(settled, start, end, still).value, 0.101f)
        assertTrue(100f - settling.getValueFromNanos(settled - 50_000_000L, start, end, still).value > 0.1f)
        val coarse = Motion.smooth(visibilityThreshold = 1f).vectorize(Float.VectorConverter)
        val reference = SpringSpec(1f, Spring().stiffness.toFloat(), 1f).vectorize(Float.VectorConverter)
        assertEquals(reference.getDurationNanos(start, end, still), coarse.getDurationNanos(start, end, still))
        assertTrue(coarse.getDurationNanos(start, end, still) < settled)
        // Nothing to cover is done at once.
        assertEquals(0L, settling.getDurationNanos(end, end, still))
    }
}
