package com.plhery.peekdeliverytracker.ui.sheet

import android.graphics.Insets
import android.view.WindowInsets
import android.view.WindowInsetsAnimationControlListener
import android.view.WindowInsetsAnimationController
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Velocity
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * A drag of what scrolls takes the keyboard with the finger (`.scrollDismissesKeyboard(.interactively)`), through the
 * window's control of its insets: it follows the finger down and back up, and let go ends shown or hidden. Once what
 * scrolls has left, as a sheet closing under the finger does, the window's answers that come after are let go without
 * throwing, and a controller handed then is finished at once where the keyboard stands, not left half way.
 */
@RunWith(AndroidJUnit4::class)
class KeyboardDragTest {
    /** What asked the window for control of the keyboard, in order. */
    private val asked = mutableListOf<WindowInsetsAnimationControlListener>()
    private var shown = false
    private val drag = KeyboardDrag(Density(2f), shown = { shown }) { listener, _ -> asked += listener }

    /** The window's control of a keyboard [height] pixels tall, its top [bottom] pixels above the window's foot. */
    private class Controller(bottom: Int, private val height: Int = 600) : WindowInsetsAnimationController {
        private var insets: Insets = Insets.of(0, 0, 0, bottom)

        /** Each finish, shown or not. */
        val finished = mutableListOf<Boolean>()

        override fun getHiddenStateInsets(): Insets = Insets.NONE

        override fun getShownStateInsets(): Insets = Insets.of(0, 0, 0, height)

        override fun getCurrentInsets(): Insets = insets

        override fun getCurrentFraction(): Float = insets.bottom / height.toFloat()

        override fun getCurrentAlpha(): Float = 1f

        override fun getTypes(): Int = WindowInsets.Type.ime()

        override fun setInsetsAndAlpha(insets: Insets?, alpha: Float, fraction: Float) {
            if (insets != null) this.insets = insets
        }

        override fun finish(shown: Boolean) {
            finished += shown
        }

        override fun isFinished(): Boolean = finished.isNotEmpty()

        override fun isCancelled(): Boolean = false
    }

    /** A frame every 16 ms, each at once. */
    private class Frames : MonotonicFrameClock {
        private var now = 0L

        override suspend fun <R> withFrameNanos(onFrame: (frameTimeNanos: Long) -> R): R {
            now += 16_000_000L
            return onFrame(now)
        }
    }

    /** A swipe down let go with the keyboard down: its end asks the window for the keyboard and waits for it. */
    private fun TestScope.flingWaitingForTheKeyboard(): Deferred<Velocity> {
        val fling = async(start = CoroutineStart.UNDISPATCHED) { drag.onPostFling(Velocity.Zero, Velocity(0f, 1_000f)) }
        assertEquals(1, asked.size)
        assertTrue(fling.isActive)
        return fling
    }

    @Test
    fun aControllerHandedOnceWhatScrollsHasLeftIsFinishedWhereTheKeyboardStands() = runTest {
        val fling = flingWaitingForTheKeyboard()
        drag.dispose()
        assertEquals(Velocity.Zero, fling.await())
        // The window answers after, as Android 13 and later do on the frame after the sheet has gone.
        val late = Controller(bottom = 0)
        asked.single().onReady(late, WindowInsets.Type.ime())
        assertEquals(listOf(false), late.finished)
        asked.single().onFinished(late)
        assertEquals(listOf(false), late.finished)
    }

    @Test
    fun theWindowsEndOfARequestOnceWhatScrollsHasLeftIsLetGo() = runTest {
        val fling = flingWaitingForTheKeyboard()
        drag.dispose()
        assertEquals(Velocity.Zero, fling.await())
        asked.single().onCancelled(null)
    }

    @Test
    fun theKeyboardFollowsTheFingerDownAndBackUpAndLetGoEndsShown() = runTest(Frames()) {
        shown = true
        // Down 30: taken while the controller comes.
        assertEquals(Offset(0f, 30f), drag.onPreScroll(Offset(0f, 30f), NestedScrollSource.UserInput))
        val controller = Controller(bottom = 600)
        asked.single().onReady(controller, WindowInsets.Type.ime())
        // Down 40 more, before what scrolls takes it.
        assertEquals(Offset(0f, 40f), drag.onPreScroll(Offset(0f, 40f), NestedScrollSource.UserInput))
        assertEquals(560, controller.currentInsets.bottom)
        // Up 10: what scrolls takes it first, then the keyboard what is left.
        assertEquals(Offset.Zero, drag.onPreScroll(Offset(0f, -10f), NestedScrollSource.UserInput))
        assertEquals(Offset(0f, -10f), drag.onPostScroll(Offset.Zero, Offset(0f, -10f), NestedScrollSource.UserInput))
        assertEquals(570, controller.currentInsets.bottom)
        // Let go nearer shown: it springs up and is finished shown.
        assertEquals(Velocity.Zero, drag.onPreFling(Velocity.Zero))
        drag.onPostFling(Velocity.Zero, Velocity.Zero)
        assertEquals(600, controller.currentInsets.bottom)
        assertEquals(listOf(true), controller.finished)
        assertEquals(1, asked.size)
    }
}
