package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.animation.core.SpringSpec
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.MotionCurve
import com.plhery.peekdeliverytracker.core.Spring
import com.plhery.peekdeliverytracker.core.UnitCurve
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** The press styles' numbers against the iPhone's ButtonStyles (A-AN38), and SwiftUI's way of animating them. */
class PressStyleTest {
    private fun assertSpring(expected: SpringSpec<Float>, actual: MotionCurve) {
        val spring = (actual as MotionCurve.SpringCurve).spring
        assertEquals(expected.stiffness, spring.stiffness.toFloat(), 0.001f)
        assertEquals(expected.dampingRatio, spring.dampingRatio.toFloat(), 1e-6f)
    }

    @Test
    fun theStylesGiveAsTheIPhonesDo() {
        // TactileButtonStyle: scaleEffect 0.985 (or the button's own), opacity 0.94, easeOut(duration: 0.16).
        with(PressStyle.Tactile) {
            assertEquals(0.985f, scale)
            assertEquals(0.94f, opacity)
            assertEquals(0.dp, offsetY)
            assertEquals(0f, brightness)
            assertEquals(MotionCurve.EaseOut(0.16), animation)
        }
        for (scale in listOf(0.94f, 0.96f, 0.97f, 0.98f, 0.995f)) assertEquals(PressStyle.Tactile.copy(scale = scale), PressStyle.tactile(scale))
        // ExperimentalLiftButtonStyle: scaleEffect 0.975, offset y 1.5, brightness -0.025, snappy(duration: 0.22).
        with(PressStyle.ExperimentalLift) {
            assertEquals(0.975f, scale)
            assertEquals(1.5.dp, offsetY)
            assertEquals(1f, opacity)
            assertEquals(-0.025f, brightness)
            assertSpring(Motion.snappy(duration = 0.22), animation)
        }
        // PassportPressStyle: scaleEffect 0.975, opacity 0.94, spring(response: 0.3, dampingFraction: 0.75).
        with(PressStyle.Passport) {
            assertEquals(0.975f, scale)
            assertEquals(0.94f, opacity)
            assertEquals(0f, brightness)
            assertSpring(Motion.springWithResponse(response = 0.3, dampingFraction = 0.75), animation)
        }
        // FirstOpenHeroStyle: brightness -0.02, easeOut(duration: 0.18).
        with(PressStyle.FirstOpenHero) {
            assertEquals(1f, scale)
            assertEquals(1f, opacity)
            assertEquals(-0.02f, brightness)
            assertEquals(MotionCurve.EaseOut(0.18), animation)
        }
    }

    @Test
    fun reduceMotionKeepsTheFadeAndTheShadeButNotTheMove() {
        val lift = PressStyle.ExperimentalLift
        assertEquals(PressAppearance(0.975f, 1.5.dp, 1f, -0.025f), lift.appearance(1.0, reduceMotion = false))
        assertEquals(PressAppearance(1f, 0.dp, 1f, -0.025f), lift.appearance(1.0, reduceMotion = true))
        assertEquals(PressAppearance(1f, 0.dp, 0.94f, 0f), PressStyle.Tactile.appearance(1.0, reduceMotion = true))
        assertEquals(PressAppearance(1f, 0.dp, 1f, 0f), PressStyle.Passport.appearance(0.0, reduceMotion = false))
        val halfway = PressStyle.Tactile.appearance(0.5, reduceMotion = false)
        assertEquals(0.9925f, halfway.scale, 1e-6f)
        assertEquals(0.97f, halfway.opacity, 1e-6f)
    }

    @Test
    fun anOvershootingSpringScalesPastButNeverFadesPastOpaque() {
        val past = PressStyle.Passport.appearance(1.1, reduceMotion = false)
        assertEquals(1 - 0.025f * 1.1f, past.scale, 1e-6f)
        assertEquals(1 - 0.06f * 1.1f, past.opacity, 1e-6f)
        val back = PressStyle.Passport.appearance(-0.1, reduceMotion = false)
        assertEquals(1 + 0.025f * 0.1f, back.scale, 1e-6f)
        assertEquals(1f, back.opacity)
    }

    @Test
    fun curvesLetGoHalfwayEaseBackFromWhereTheyWere() {
        val ease = { time: Double -> UnitCurve.easeOut.value(time / 0.16) }
        val value = AnimatedValue(0.0)
        value.animate(1.0, MotionCurve.EaseOut(0.16), now = 10.0)
        assertEquals(ease(0.05), value.value(10.05), 1e-12)
        value.animate(0.0, MotionCurve.EaseOut(0.16), now = 10.05)
        // Both changes run in full, each from its own start: no jump where the second begins.
        assertEquals(ease(0.05), value.value(10.05), 1e-12)
        assertEquals(ease(0.1) - ease(0.05), value.value(10.1), 1e-12)
        assertEquals(1 - ease(0.15), value.value(10.2), 1e-12)
        assertTrue(value.isRunning(10.2))
        assertEquals(0.0, value.value(10.21), 1e-12)
        assertFalse(value.isRunning(10.21))
    }

    @Test
    fun aSpringTakesOverFromTheValueAndSpeedShown() {
        val spring = Spring(duration = 0.22, bounce = 0.15)
        val value = AnimatedValue(0.0)
        value.animate(1.0, MotionCurve.SpringCurve(spring), now = 0.0)
        assertEquals(spring.value(1.0, time = 0.08), value.value(0.08), 1e-12)
        val shown = value.value(0.08)
        val speed = value.velocity(0.08)
        assertEquals(spring.velocity(1.0, time = 0.08), speed, 1e-9)
        value.animate(0.0, MotionCurve.SpringCurve(spring), now = 0.08)
        assertEquals(shown, value.value(0.08), 1e-12)
        assertEquals(speed, value.velocity(0.08), 1e-9)
        assertEquals(shown + spring.value(-shown, speed, 0.05), value.value(0.13), 1e-12)
        assertTrue(value.isRunning(0.3))
        assertFalse(value.isRunning(2.0))
        assertEquals(0.0, value.value(2.0), 1e-4)
    }

    @Test
    fun withoutAnAnimationTheValueMovesAtOnce() {
        val value = AnimatedValue(0.0)
        value.animate(1.0, MotionCurve.EaseOut(0.16), now = 0.0)
        value.animate(0.0, null, now = 0.05)
        assertEquals(0.0, value.value(0.05), 0.0)
        assertFalse(value.isRunning(0.05))
    }
}
