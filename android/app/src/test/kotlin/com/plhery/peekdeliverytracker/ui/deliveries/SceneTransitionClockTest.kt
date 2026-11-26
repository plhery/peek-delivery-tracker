package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.core.SeekableTransitionState
import androidx.compose.animation.core.rememberTransition
import androidx.compose.runtime.AbstractApplier
import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composition
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.snapshots.Snapshot
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The deliveries tab's NavDisplay moves its scene with a SeekableTransitionState whose transition lasts no time at all
 * (its transitions are still, as the zoom draws the page), at each page opened or closed, a back gesture's included.
 * Compose before 1.13 divided by that zero length when a frame's time came before the last one's, and the fraction it
 * then sought to, NaN, stopped the app: "Cannot round NaN value." in SeekableTransitionState.seekToFraction, from a
 * frame callback (Play, 1.1.28 on Android 17).
 */
@RunWith(AndroidJUnit4::class)
class SceneTransitionClockTest {
    /** A composition with nothing to draw: the transition is all this test composes. */
    private object NoNodes : AbstractApplier<Unit>(Unit) {
        override fun insertTopDown(index: Int, instance: Unit) = Unit
        override fun insertBottomUp(index: Int, instance: Unit) = Unit
        override fun remove(index: Int, count: Int) = Unit
        override fun move(from: Int, to: Int, count: Int) = Unit
        override fun onClear() = Unit
    }

    private fun TestScope.frame(clock: BroadcastFrameClock, nanos: Long) {
        Snapshot.sendApplyNotifications()
        clock.sendFrame(nanos)
        runCurrent()
    }

    @Test
    fun aStillSceneChangeSurvivesAFrameTimedBeforeTheLastOne() = runTest {
        val clock = BroadcastFrameClock()
        withContext(clock) {
            val recomposer = Recomposer(coroutineContext)
            val recomposing = launch { recomposer.runRecomposeAndApplyChanges() }
            val scene = SeekableTransitionState("list")
            val composition = Composition(NoNodes, recomposer)
            composition.setContent { rememberTransition(scene, label = "scene") }
            runCurrent()

            val moving = launch { scene.animateTo("page") }
            runCurrent()
            frame(clock, 1_000_000_000L)
            frame(clock, 999_000_000L)
            frame(clock, 1_016_000_000L)
            frame(clock, 1_032_000_000L)

            assertEquals("page", scene.currentState)
            assertEquals(0f, scene.fraction)
            moving.join()
            composition.dispose()
            recomposer.cancel()
            recomposing.join()
        }
    }
}
