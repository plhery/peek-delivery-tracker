package com.plhery.peekdeliverytracker.ui

import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.theme.Motion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Duration
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The root's switch between its screens, as SwiftUI's transaction makes it: at once, unless the change was made with
 * an animation; then the screen before fades out under the new one as it fades in, out of reach of the finger and of
 * TalkBack, and goes when the fade ends.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class RootSwitchTest {
    @get:Rule
    val compose = createComposeRule()

    private var state by mutableStateOf<AuthenticationState>(AuthenticationState.SignedOut)
    private val transaction = RootTransaction()
    private var arrivalTaps = 0

    /** The states each screen was drawn with, in order. */
    private val drawn = mutableMapOf<RootScreen, MutableList<AuthenticationState>>()

    /** The arrival in red, which counts its taps, and the tabs in blue. */
    private fun show() {
        compose.setContent {
            RootSwitch(state, transaction) { screen, shown ->
                drawn.getOrPut(screen) { mutableListOf() } += shown
                when (screen) {
                    RootScreen.Arrival -> Box(Modifier.fillMaxSize().background(Color.Red).testTag(ARRIVAL).clickable { arrivalTaps++ })
                    RootScreen.Tabs -> Box(Modifier.fillMaxSize().background(Color.Blue).testTag(TABS))
                    RootScreen.Launch -> Box(Modifier.fillMaxSize().background(Color.Green))
                }
            }
        }
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
    }

    /**
     * The window's centre as it draws the last frame: laid out once the composition is idle, and drawn on its looper's
     * clock, which a frame's length brings up. Read once two frames agree: a busy machine can leave the first capture
     * on the frame before, and the test's clock, standing still, moves no animation on meanwhile.
     */
    private fun centre(): Color {
        compose.waitForIdle()
        var last: Color? = null
        repeat(5) {
            shadowOf(Looper.getMainLooper()).idleFor(Duration.ofMillis(17))
            val image = compose.onRoot().captureToImage().toPixelMap()
            val colour = image[image.width / 2, image.height / 2]
            if (colour == last) return colour
            last = colour
        }
        return checkNotNull(last)
    }

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun frame() {
        Snapshot.sendApplyNotifications()
        compose.mainClock.advanceTimeByFrame()
    }

    @Test
    fun aChangeWithoutAnAnimationSwitchesInTheSameFrame() {
        show()
        state = AuthenticationState.Welcome
        frame()
        assertEquals(Color.Blue, centre())
        assertFalse(exists(ARRIVAL))
    }

    @Test
    fun aChangeWithAnAnimationFadesTheScreenBeforeOutUnderTheNewOne() {
        show()
        transaction.withAnimation(Motion.easeInOut(0.45)) { AuthenticationState.Welcome.also { state = it } }
        frame()
        val start = centre()
        assertTrue("Still red: $start", start.red > 0.9f && start.blue < 0.1f)
        assertTrue(exists(TABS))
        assertFalse("Out of TalkBack's reach", exists(ARRIVAL))
        compose.onRoot().performTouchInput { click(center) }
        compose.mainClock.advanceTimeBy(225)
        val mid = centre()
        assertTrue("Halfway, both: $mid", mid.red in 0.2f..0.8f && mid.blue in 0.3f..0.9f)
        compose.mainClock.advanceTimeBy(250)
        assertEquals(Color.Blue, centre())
        assertEquals("The screen going takes no taps", 0, arrivalTaps)
    }

    /** A change recorded with an animation that the switch then doesn't make, as another change came first. */
    @Test
    fun anAnimationGoesOnlyWithTheChangeItWasMadeWith() {
        show()
        transaction.withAnimation(Motion.easeInOut(0.45)) { AuthenticationState.Welcome }
        state = AuthenticationState.Demo
        frame()
        assertEquals(Color.Blue, centre())
        state = AuthenticationState.SignedOut
        frame()
        assertEquals("The record went with the switch before", Color.Red, centre())
    }

    /**
     * The screen going is drawn with the state the change led to, and keeps it while it fades: a change during the fade
     * reaches only the screen shown, with no animation of its own.
     */
    @Test
    fun theScreenGoingKeepsTheStateItLeftOn() {
        show()
        transaction.withAnimation(Motion.easeInOut(0.45)) { AuthenticationState.Welcome.also { state = it } }
        frame()
        assertEquals(AuthenticationState.Welcome, drawn.getValue(RootScreen.Arrival).last())
        compose.mainClock.advanceTimeBy(100)
        state = AuthenticationState.Demo
        frame()
        compose.mainClock.advanceTimeBy(100)
        assertEquals("The arrival keeps the state it left on", setOf(AuthenticationState.SignedOut, AuthenticationState.Welcome), drawn.getValue(RootScreen.Arrival).toSet())
        assertEquals(AuthenticationState.Demo, drawn.getValue(RootScreen.Tabs).last())
        val mid = centre()
        assertTrue("Still fading: $mid", mid.red > 0.05f && mid.blue > 0.05f)
        compose.mainClock.advanceTimeBy(300)
        assertEquals(Color.Blue, centre())
        // The record went with the change it was made with: the next one switches at once.
        state = AuthenticationState.SignedOut
        frame()
        assertEquals(Color.Red, centre())
    }

    /** A composition that reads the record takes nothing from it: the change it was made with still fades. */
    @Test
    fun readingTheRecordLeavesItForTheSwitch() {
        show()
        val fade = Motion.easeInOut<Float>(0.45)
        transaction.withAnimation(fade) { AuthenticationState.Welcome }
        assertSame(fade, transaction.animation(AuthenticationState.Welcome))
        assertEquals(null, transaction.animation(AuthenticationState.Demo))
        state = AuthenticationState.Welcome
        frame()
        compose.mainClock.advanceTimeBy(225)
        val mid = centre()
        assertTrue("Halfway, both: $mid", mid.red in 0.2f..0.8f && mid.blue in 0.3f..0.9f)
        assertEquals("Taken once the switch showed it", null, transaction.animation(AuthenticationState.Welcome))
        compose.mainClock.advanceTimeBy(250)
        assertEquals(Color.Blue, centre())
    }

    private companion object {
        const val TABS = "tabs"
    }
}
