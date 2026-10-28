package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * The SwiftUI curves and springs :app animates with, against SwiftUI's own answers on a Mac (swiftc probes of
 * `UnitCurve.velocity(at:)`, `Spring.value(target:initialVelocity:time:)` and `Spring.velocity(...)`). SwiftUI works
 * a curve out in single precision, hence the tolerance.
 */
class GeometryTests {
    private val progress = listOf(0.0, 0.1, 0.25, 0.5, 0.75, 0.9, 1.0)

    private fun assertVelocities(expected: List<Double>, curve: UnitCurve) {
        for ((x, velocity) in progress.zip(expected)) assertEquals("at $x", velocity, curve.velocity(x), 2e-6)
    }

    @Test
    fun curvesRiseAsFastAsSwiftUIs() {
        assertVelocities(listOf(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), UnitCurve.linear)
        assertVelocities(listOf(0.0, 0.32418346405029297, 0.6745977401733398, 1.0739784240722656, 1.3682889938354492, 1.535074234008789, 0.0), UnitCurve.easeIn)
        assertVelocities(listOf(1.0, 1.535074234008789, 1.3682889938354492, 1.0739784240722656, 0.6745977401733398, 0.32418346405029297, 0.0), UnitCurve.easeOut)
        assertVelocities(listOf(0.0, 0.40224456787109375, 1.0592613220214844, 1.7241382598876953, 1.0592613220214844, 0.40224456787109375, 0.0), UnitCurve.easeInOut)
        val wave = UnitCurve.bezier(Point(0.3, 0.6), Point(0.7, 0.4))
        assertVelocities(listOf(2.0, 1.3986749649047852, 0.8650493621826172, 0.5714282989501953, 0.8650493621826172, 1.3986749649047852, 2.0), wave)
        val pip = UnitCurve.bezier(Point(0.22, 1.0), Point(0.36, 1.0))
        assertVelocities(listOf(4.545454978942871, 3.3966798782348633, 1.5629901885986328, 0.3102455139160156, 0.04203224182128906, 0.00502777099609375, 0.0), pip)
    }

    @Test
    fun aCurveThatLeavesStraightUpStartsInfinitelyFast() {
        val steep = UnitCurve.bezier(Point(0.0, 0.5), Point(0.5, 1.0))
        assertEquals(Double.POSITIVE_INFINITY, steep.velocity(0.0), 0.0)
        assertEquals(1.9796533584594727, steep.velocity(0.1), 2e-6)
        assertEquals(0.0, steep.velocity(1.0), 0.0)
        // Outside 0…1 the curve holds its ends.
        assertEquals(1.0, UnitCurve.easeOut.velocity(-0.5), 0.0)
        assertEquals(0.0, UnitCurve.easeOut.velocity(1.5), 0.0)
    }

    // (time, value, velocity) for SwiftUI's springs, leaving zero toward a target at an initial velocity.
    private class SpringSample(val spring: Spring, val target: Double, val initialVelocity: Double, val samples: List<Triple<Double, Double, Double>>)

    private val springs = listOf(
        SpringSample(
            Spring(0.4, 0.5), 1.0, 0.0,
            listOf(
                Triple(0.01, 0.011691817475669, 2.273997823272964), Triple(0.05, 0.229847307170392, 7.702713692315330),
                Triple(0.2, 1.140699674143518, 1.540544218433050), Triple(0.5, 0.977249370504006, 0.177124286573146),
                Triple(1.0, 0.999609558889570, 0.006062110004427),
            ),
        ),
        SpringSample(
            Spring(0.4, 0.5), -0.06, 2.5,
            listOf(
                Triple(0.01, 0.022338906056871, 1.972412592760346), Triple(0.1, 0.043102352613396, -0.890701999408596),
                Triple(0.3, -0.080067645999480, 0.053842536764045), Triple(1.0, -0.059915151517272, -0.000352438596962),
            ),
        ),
        SpringSample(
            Spring(0.22, 0.15), 3.0, -4.0,
            listOf(
                Triple(0.01, 0.072790656335304, 16.779955435704416), Triple(0.05, 1.312751208540908, 33.456569735778260),
                Triple(0.2, 3.017899800154036, 0.205015088916456), Triple(0.5, 2.999968929585471, 0.000848834941963),
            ),
        ),
        SpringSample(
            Spring(0.3, 0.25), -0.06, 2.5,
            listOf(
                Triple(0.05, 0.033403521933073, -0.501326445473764), Triple(0.2, -0.058523529829701, -0.174857187788931),
                Triple(0.3, -0.062180236338324, 0.024253619379643),
            ),
        ),
        SpringSample(
            Spring(0.5, 0.0), 3.0, -4.0,
            listOf(
                Triple(0.01, -0.013483346887912, 1.093627377854688), Triple(0.1, 0.959374852074282, 13.775286714639781),
                Triple(0.3, 2.642436884764633, 3.531934680918950), Triple(1.0, 2.999844118893985, 0.001813440671018),
            ),
        ),
    )

    @Test
    fun springsMoveAsSwiftUIsFromAnyStartingSpeed() {
        for (sample in springs) {
            val spring = sample.spring
            assertEquals(sample.initialVelocity, spring.velocity(sample.target, sample.initialVelocity, time = 0.0), 0.0)
            for ((time, value, velocity) in sample.samples) {
                val label = "$spring to ${sample.target} at ${sample.initialVelocity}, $time s"
                assertEquals(label, value, spring.value(sample.target, sample.initialVelocity, time), 1e-12)
                assertEquals(label, velocity, spring.velocity(sample.target, sample.initialVelocity, time), 1e-10)
            }
            assertEquals(0.0, spring.velocity(sample.target, sample.initialVelocity, Double.POSITIVE_INFINITY), 0.0)
        }
    }

    @Test
    fun aBounceBelowZeroIsOverdampedAsSwiftUIs() {
        // Spring(duration: 0.5, bounce: -0.3) and Spring(response: 0.5, dampingRatio: 1.5): SwiftUI's damping and ratio.
        val overdamped = Spring(0.5, -0.3)
        assertEquals(35.903916041, overdamped.damping, 1e-9)
        assertEquals(1 / 0.7, overdamped.dampingRatio, 1e-12)
        assertEquals(157.913670417, overdamped.stiffness, 1e-9)
        val response = Spring.response(0.5, 1.5)
        assertEquals(Spring(0.5, 1 / 1.5 - 1), response)
        assertEquals(37.6991118431, response.damping, 1e-9)
        assertEquals(1.5, response.dampingRatio, 1e-12)
        assertEquals(0.14, Spring.response(0.42, 0.86).bounce, 1e-12)
        assertEquals(Spring(0.55, 0.0), Spring.response(0.55, 1.0))
        assertEquals(0.281874693577, response.value(1.0, time = 0.1), 1e-11)
    }

    /**
     * (target, initial velocity, time, value, velocity) of `Spring(duration: 0.5, bounce: -0.3)` in SwiftUI. Its
     * `velocity` of an overdamped spring is the speed plus the target, the target still once at rest; :core gives the
     * speed its `value` moves at.
     */
    @Test
    fun anOverdampedSpringMovesAsSwiftUIsFromAnyStartingSpeed() {
        val spring = Spring(0.5, -0.3)
        val samples = listOf(
            listOf(1.0, 0.0, 0.1, 0.290825710989, 4.40276162898),
            listOf(1.0, 3.0, 0.3, 0.767689764507, 2.19193676135),
            listOf(2.0, 0.0, 0.3, 1.48521162544, 4.64073259697),
            listOf(2.0, 3.0, 0.1, 0.64629614121, 8.61204755322),
            listOf(-3.0, 3.0, 0.1, -0.807832413737, -13.4017605917),
            listOf(-3.0, 0.0, 5.0, -2.99999999997, -3.00000000013),
            listOf(0.0, 3.0, 0.3, 0.0250839517882, -0.128429537138),
        )
        for ((target, initialVelocity, time, value, velocity) in samples) {
            val label = "to $target at $initialVelocity, $time s"
            assertEquals(label, value, spring.value(target, initialVelocity, time), 1e-11)
            assertEquals(label, velocity - target, spring.velocity(target, initialVelocity, time), 1e-10)
        }
        assertEquals(3.0, spring.velocity(2.0, 3.0, time = 0.0), 0.0)
    }
}
