package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.MotionDurationScale
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** With the phone's animations off, what iOS keeps under Reduce Motion still moves (KeepMotion). */
@RunWith(AndroidJUnit4::class)
class KeepMotionTest {
    /** "Remove animations": the animator duration scale at 0. */
    private object AnimationsOff : MotionDurationScale {
        override val scaleFactor: Float get() = 0f
    }

    @get:Rule
    val compose = createComposeRule(AnimationsOff)

    @Test
    fun keptAnimationsRunTheirCourseWhileOthersJump() {
        val linear = tween<Float>(durationMillis = 1000, easing = LinearEasing)
        var target by mutableFloatStateOf(0f)
        var plain = 0f
        var kept = 0f
        var launched = 0f
        compose.mainClock.autoAdvance = false
        compose.setContent {
            plain = animateFloatAsState(target, linear).value
            kept = animateKeptFloatAsState(target, linear).value
            val animatable = remember { Animatable(0f) }
            LaunchedEffect(target) { withContext(KeepMotion) { animatable.animateTo(target, linear) } }
            launched = animatable.value
        }
        compose.mainClock.advanceTimeByFrame()
        compose.runOnUiThread { target = 1f }
        compose.mainClock.advanceTimeBy(500)
        assertEquals(1f, plain)
        assertEquals(0.5f, kept, 0.05f)
        assertEquals(0.5f, launched, 0.05f)
        compose.mainClock.advanceTimeBy(600)
        assertEquals(1f, kept)
        assertEquals(1f, launched)
    }

    @Test
    fun aKeptSpinnerTurns() {
        var turn = 0f
        compose.mainClock.autoAdvance = false
        compose.setContent {
            val spinner = remember { Animatable(0f) }
            LaunchedEffect(Unit) {
                withContext(KeepMotion) { spinner.animateTo(1f, infiniteRepeatable(tween(1000, easing = LinearEasing))) }
            }
            turn = spinner.value
        }
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeBy(250)
        val quarter = turn
        compose.mainClock.advanceTimeBy(500)
        assertEquals(0.25f, quarter, 0.05f)
        assertEquals(0.75f, turn, 0.05f)
    }
}
