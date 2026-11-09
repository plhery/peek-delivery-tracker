package com.plhery.peekdeliverytracker.ui

import android.os.SystemClock
import android.view.InputDevice
import android.view.MotionEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onFirst
import androidx.test.platform.app.InstrumentationRegistry

/**
 * A finger on the screen, its touches sent as the screen's are through UiAutomation, as DropDeviceTest's: taps, long
 * presses, drags and throws on what the window shows, and a mouse's right click. A drag moves a frame at a time, real
 * time and the test's clock both moved on with each move, so what follows a drag through the composition's coroutines
 * (a sheet, a scroll, the keyboard following the finger) and what waits on its clock (a long press) see the finger as a
 * person's would. A tap leaves the test's clock where it stands, so a test that holds it sees the frames after it one
 * by one.
 */
internal class ScreenTouches(private val compose: AndroidComposeTestRule<*, *>) {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    /**
     * Where the window's top-left corner stands on the screen, as Compose's own window position: a window the keyboard
     * pans moves its views up in it, which their bounds in the window already count.
     */
    fun origin(): Offset = compose.runOnUiThread {
        val decor = compose.activity.window.decorView
        val screen = IntArray(2).also { decor.getLocationOnScreen(it) }
        val window = IntArray(2).also { decor.getLocationInWindow(it) }
        Offset((screen[0] - window[0]).toFloat(), (screen[1] - window[1]).toFloat())
    }

    /** The first node `matcher` finds, unmerged. */
    fun node(matcher: SemanticsMatcher): SemanticsNode =
        compose.onAllNodes(matcher, useUnmergedTree = true).onFirst().fetchSemanticsNode()

    /** The point on the screen at `x` of the width and `y` of the height of what `matcher` finds first. */
    fun point(matcher: SemanticsMatcher, x: Float = 0.5f, y: Float = 0.5f): Offset {
        val bounds = node(matcher).boundsInWindow
        return origin() + Offset(bounds.left + bounds.width * x, bounds.top + bounds.height * y)
    }

    fun point(tag: String, x: Float = 0.5f, y: Float = 0.5f): Offset = point(hasTestTag(tag), x, y)

    /** A finger down at `at` and up 50 ms later, and the window idle after. */
    fun tap(at: Offset) {
        val finger = Finger(at)
        finger.send(MotionEvent.ACTION_DOWN)
        SystemClock.sleep(TAP_MILLIS)
        finger.send(MotionEvent.ACTION_UP)
        compose.waitForIdle()
    }

    fun tap(tag: String) = tap(point(tag))

    /**
     * A mouse's right button pressed at `at` and let go 50 ms later, sent as a mouse's are (its source, its pointer and
     * the button held while it is down), and the window idle after.
     */
    fun rightClick(at: Offset) {
        val mouse = Finger(at, mouse = true)
        mouse.send(MotionEvent.ACTION_DOWN, MotionEvent.BUTTON_SECONDARY)
        SystemClock.sleep(TAP_MILLIS)
        mouse.send(MotionEvent.ACTION_UP)
        compose.waitForIdle()
    }

    /** A finger held still at `at` for [LONG_PRESS_MILLIS], the test's clock moved on as much, then lifted. */
    fun longPress(at: Offset) {
        val finger = Finger(at)
        finger.send(MotionEvent.ACTION_DOWN)
        SystemClock.sleep(LONG_PRESS_MILLIS)
        compose.mainClock.advanceTimeBy(LONG_PRESS_MILLIS)
        finger.send(MotionEvent.ACTION_MOVE)
        finger.send(MotionEvent.ACTION_UP)
        compose.waitForIdle()
    }

    /**
     * A finger down at `from` and moved by `by` in `steps` frames; where it `holds`, it rests ten frames before it
     * lifts, so it lets go with no speed left, as a drag does, else it lifts on the move, as a throw does. `beforeLift`
     * runs as it rests, before it lifts.
     */
    fun drag(from: Offset, by: Offset, steps: Int = DRAG_STEPS, holds: Boolean = true, beforeLift: () -> Unit = {}) {
        val finger = Finger(from)
        finger.send(MotionEvent.ACTION_DOWN)
        for (step in 1..steps) {
            frame()
            finger.pace()
            finger.at = from + by * (step / steps.toFloat())
            finger.send(MotionEvent.ACTION_MOVE)
        }
        if (holds) {
            repeat(HOLD_FRAMES) {
                frame()
                finger.pace()
                finger.send(MotionEvent.ACTION_MOVE)
            }
            beforeLift()
        }
        frame()
        finger.pace()
        finger.send(MotionEvent.ACTION_UP)
        compose.waitForIdle()
    }

    /** A quick throw from `from` by `by` in six frames, lifted on the move. */
    fun fling(from: Offset, by: Offset) = drag(from, by, steps = FLING_STEPS, holds = false)

    /** A frame of real time and of the test's clock. */
    private fun frame() {
        SystemClock.sleep(FRAME_MILLIS)
        compose.mainClock.advanceTimeBy(FRAME_MILLIS)
    }

    /** A finger on the screen at [at], its touches sent as the screen's are; or a [mouse]'s pointer, as a mouse's. */
    private inner class Finger(var at: Offset, private val mouse: Boolean = false) {
        private var down = SystemClock.uptimeMillis()

        /**
         * The time a drag's touches carry, a frame after the one before, rather than when they are sent: a device can
         * take longer than 40 ms to draw a frame, and touches that far apart read as a finger that stopped, which the
         * velocity tracker counts as no speed at all. Null for a tap's, sent as they happen.
         */
        private var paced: Long? = null

        fun pace() {
            paced = (paced ?: down) + FRAME_MILLIS
        }
        private val properties = MotionEvent.PointerProperties().apply {
            id = 0
            toolType = if (mouse) MotionEvent.TOOL_TYPE_MOUSE else MotionEvent.TOOL_TYPE_FINGER
        }

        /**
         * The touch for [action], sent. Android 12 drops a touch through another app's window, even one that takes
         * no touches, and the test's own empty activity can stand over the app's for a moment as it finishes: the
         * finger put down never reaches the app then, so it is put down again a little later, until that window has
         * gone. `buttons` are a mouse's held.
         */
        fun send(action: Int, buttons: Int = 0) {
            val until = SystemClock.uptimeMillis() + DOWN_AGAIN_MILLIS
            while (!inject(action, buttons)) {
                check(action == MotionEvent.ACTION_DOWN && SystemClock.uptimeMillis() < until) {
                    "The screen refused the touch ${MotionEvent.actionToString(action)} at $at"
                }
                SystemClock.sleep(FRAME_MILLIS * 6)
                down = SystemClock.uptimeMillis()
            }
        }

        private fun inject(action: Int, buttons: Int): Boolean {
            val where = MotionEvent.PointerCoords().apply {
                x = at.x
                y = at.y
                pressure = 1f
                size = 1f
            }
            val time = if (action == MotionEvent.ACTION_DOWN) down else paced ?: SystemClock.uptimeMillis()
            val event = MotionEvent.obtain(
                down, time, action, 1, arrayOf(properties), arrayOf(where), 0, buttons, 1f, 1f, 0, 0,
                if (mouse) InputDevice.SOURCE_MOUSE else InputDevice.SOURCE_TOUCHSCREEN, 0,
            )
            try {
                return instrumentation.uiAutomation.injectInputEvent(event, true)
            } finally {
                event.recycle()
            }
        }
    }

    companion object {
        const val FRAME_MILLIS = 16L
        const val DOWN_AGAIN_MILLIS = 5_000L
        const val TAP_MILLIS = 50L

        /** Twice the system's long press, on both clocks. */
        const val LONG_PRESS_MILLIS = 800L

        const val DRAG_STEPS = 20
        const val FLING_STEPS = 6
        const val HOLD_FRAMES = 10
    }
}
