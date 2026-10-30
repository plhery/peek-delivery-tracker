package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** `keyframeAnimator` on Compose's frame clock: still when it appears, played when its trigger changes. */
@RunWith(AndroidJUnit4::class)
class KeyframeAnimatorTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun itPlaysWhenTheTriggerChangesButNotWhenItAppears() {
        compose.mainClock.autoAdvance = false
        var trigger by mutableIntStateOf(0)
        var shown = 0.0
        compose.setContent {
            val scale by keyframeAnimator(1.0, trigger) { PeekKeyframes.pullPop }
            shown = scale
        }
        compose.mainClock.advanceTimeBy(500)
        assertEquals(1.0, shown, 0.0)
        // A write from the test's thread reaches the composition once the snapshot is applied.
        compose.runOnIdle {
            trigger += 1
            Snapshot.sendApplyNotifications()
        }
        compose.mainClock.advanceTimeBy(150)
        // The pop's first keyframe rises to 1.06 in 0.12 s.
        assertTrue("$shown", shown > 1.03)
        compose.mainClock.advanceTimeBy(1000)
        assertEquals(PeekKeyframes.pullPop.value(10.0), shown, 1e-12)
    }

    @Test
    fun keptStillItStaysAtRestAndAsksForNoFrames() {
        var trigger by mutableIntStateOf(0)
        var shown = 0.0
        compose.setContent {
            val scale by keyframeAnimator(1.0, trigger, still = true) { PeekKeyframes.pullPop }
            shown = scale
        }
        compose.waitForIdle()
        val before = compose.mainClock.currentTime
        trigger += 1
        // The clock runs on only while frames are asked for: a pop would take it on by its length.
        compose.waitForIdle()
        assertTrue("${compose.mainClock.currentTime - before} ms", compose.mainClock.currentTime - before <= 32)
        assertEquals(1.0, shown, 0.0)
    }
}
