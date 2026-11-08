/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Changed for Peek from Compose Foundation's WindowInsetsConnection.android.kt (`imeNestedScroll`,
 * `WindowInsetsNestedScrollConnection`): only the keyboard at the window's bottom, from Android 12, the app's minimum;
 * the fling's spline is Compose Animation's `SplineBasedFloatDecayAnimationSpec` rather than a copy of it; and once
 * disposed it hands what waits for a controller back once and forgets it, ignores the system's callbacks after it, and
 * finishes at once a controller that comes then. Compose's resumed the wait without forgetting it, so a controller
 * that came after its modifier had left (a sheet dragged as it closed) resumed it again and threw. Only a finger on
 * what scrolls moves the keyboard ([keyboardDrag]): Compose hands a field brought into view on as a drag too, which
 * took control of the keyboard, slid it part way down and kept it there. A controller that comes once the finger has
 * lifted with nothing waiting for it is finished at once, never kept.
 */
package com.plhery.peekdeliverytracker.ui.sheet

import android.graphics.Insets
import android.os.CancellationSignal
import android.view.WindowInsetsAnimationControlListener
import android.view.WindowInsetsAnimationController
import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Velocity
import kotlin.math.roundToInt
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

/**
 * A drag of what scrolls taking the keyboard down with the finger and back up, on to shown or hidden as the finger
 * lets go (`.scrollDismissesKeyboard(.interactively)`): the connection for its nested scroll, through the window's
 * control of the keyboard's insets, given back as it leaves.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun rememberKeyboardDrag(): KeyboardDrag {
    val view = LocalView.current
    val density = LocalDensity.current
    val shown by rememberUpdatedState(WindowInsets.isImeVisible)
    val drag = remember(view, density) {
        KeyboardDrag(density, shown = { shown }) { listener, signal ->
            view.windowInsetsController?.controlWindowInsetsAnimation(android.view.WindowInsets.Type.ime(), -1, null, signal, listener)
        }
    }
    DisposableEffect(drag) { onDispose(drag::dispose) }
    return drag
}

/**
 * What scrolls, [drag] on its nested scroll, and the fingers on it watched for it ([KeyboardDrag.pressed]) without
 * taking them: a scroll with no finger on it, a field brought into view, leaves the keyboard alone.
 */
internal fun Modifier.keyboardDrag(drag: KeyboardDrag): Modifier = this
    .pointerInput(drag) {
        awaitEachGesture {
            awaitFirstDown(requireUnconsumed = false, pass = PointerEventPass.Initial)
            drag.pressed = true
            try {
                do {
                    val event = awaitPointerEvent(PointerEventPass.Initial)
                } while (event.changes.any { it.pressed })
            } finally {
                drag.pressed = false
            }
        }
    }
    .nestedScroll(drag)

/**
 * The keyboard moved by the scroll it is handed: a drag down moves it down before what scrolls takes the drag, a drag
 * up what is left of it once that has scrolled to its top; a fling ends it shown or hidden. [shown] is whether the
 * keyboard is up, as the window's insets tell; [control] asks the window for control of the keyboard's insets, answered
 * through this listener. Once [dispose]d, nothing waits on it and what the window answers after is let go.
 */
internal class KeyboardDrag(
    private val density: Density,
    private val shown: () -> Boolean,
    private val control: (WindowInsetsAnimationControlListener, CancellationSignal) -> Unit,
) : NestedScrollConnection, WindowInsetsAnimationControlListener {
    /** The window's controller of the keyboard's insets, once it has handed one. */
    private var controller: WindowInsetsAnimationController? = null

    /** Whether a controller is asked for and not answered yet. */
    private var requested = false

    /** Never cancelled: the keyboard is moved here, not animated by the controller. */
    private val cancellationSignal = CancellationSignal()

    /** The finger's fractions of a pixel not moved yet, so that the keyboard stays under it. */
    private var partial = 0f

    /** A fling's animation of the keyboard, which a finger stops. */
    private var animation: Job? = null

    /** A fling waiting for a controller. */
    private var waiting: CancellableContinuation<WindowInsetsAnimationController?>? = null

    /** What scrolls has left: nothing is asked, moved or waited for after. */
    private var disposed = false

    /**
     * Whether a finger is on what scrolls ([keyboardDrag]): only its drag moves the keyboard, not a scroll what scrolls
     * makes itself, which Compose hands on as a drag too (a field brought into view).
     */
    var pressed = false

    private fun request() {
        if (requested || disposed) return
        requested = true
        control(this, cancellationSignal)
    }

    private suspend fun awaitController(): WindowInsetsAnimationController? = controller ?: if (disposed) null else suspendCancellableCoroutine {
        waiting = it
        request()
    }

    /** Hands [handed] to the fling waiting for a controller, once: the wait is forgotten as it is resumed. */
    private fun hand(handed: WindowInsetsAnimationController?) {
        val waiting = waiting ?: return
        this.waiting = null
        waiting.resume(handed) { _, _, _ -> }
    }

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset = scroll(available, (-available.y).coerceAtMost(0f))

    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset =
        scroll(available, (-available.y).coerceAtLeast(0f))

    /** Moves the keyboard by `amount`, up where positive, and returns what it took of `available`. */
    private fun scroll(available: Offset, amount: Float): Offset {
        if (!pressed) return Offset.Zero
        animation?.cancel()
        animation = null
        val controller = controller
        // No motion its way, or the keyboard already all the way there.
        if (disposed || amount == 0f || (shown() == amount > 0f && controller == null)) return Offset.Zero
        if (controller == null) {
            // Taken while the controller comes.
            partial = 0f
            request()
            return Offset(0f, available.y)
        }
        val hidden = controller.hiddenStateInsets.bottom
        val up = controller.shownStateInsets.bottom
        val insets = controller.currentInsets
        val current = insets.bottom
        if (current == if (amount > 0f) up else hidden) {
            partial = 0f
            return Offset.Zero
        }
        val total = current + amount + partial
        val next = total.roundToInt().coerceIn(hidden, up)
        partial = total - total.roundToInt()
        if (next != current) controller.setInsetsAndAlpha(Insets.of(insets.left, insets.top, insets.right, next), 1f, 0f)
        return Offset(0f, available.y)
    }

    override suspend fun onPreFling(available: Velocity): Velocity = fling(available, (-available.y).coerceAtMost(0f), towardShown = false)

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity =
        fling(available, (-available.y).coerceAtLeast(0f), towardShown = true)

    /**
     * Flings the keyboard by `amount`, up where positive, to shown or hidden, and returns what it took of `available`.
     * Without motion toward hiding nothing moves; toward showing it is taken all the same, so that a keyboard let go
     * half way ends shown or hidden.
     */
    private suspend fun fling(available: Velocity, amount: Float, towardShown: Boolean): Velocity {
        animation?.cancel()
        animation = null
        partial = 0f
        if (disposed || (amount == 0f && !towardShown) || (controller == null && shown() == towardShown)) return Velocity.Zero
        val controller = awaitController() ?: return Velocity.Zero
        val hidden = controller.hiddenStateInsets.bottom
        val up = controller.shownStateInsets.bottom
        val current = controller.currentInsets.bottom
        if ((amount <= 0f && current == hidden) || (amount >= 0f && current == up)) {
            controller.finish(current == up)
            this.controller = null
            return Velocity.Zero
        }
        val spec = SplineBasedFloatDecayAnimationSpec(density)
        val distance = spec.getTargetValue(current.toFloat(), amount)
        val endsShown = (distance - hidden) / (up - hidden) > 0.5f
        if (distance > up || distance < hidden) {
            // Fast enough to reach an end: the platform's spline, to where it meets it.
            var left = 0f
            coroutineScope {
                animation = launch {
                    animateDecay(current.toFloat(), amount, spec) { value, velocity ->
                        if (value in hidden.toFloat()..up.toFloat()) {
                            move(value)
                        } else {
                            left = velocity
                            controller.finish(endsShown)
                            this@KeyboardDrag.controller = null
                            animation?.cancel()
                        }
                    }
                }
                animation?.join()
                animation = null
            }
            return Velocity(0f, available.y + left)
        }
        // Too slow to reach one: a spring to the nearer.
        coroutineScope {
            animation = launch {
                Animatable(current.toFloat()).animateTo((if (endsShown) up else hidden).toFloat(), initialVelocity = amount) { move(value) }
                controller.finish(endsShown)
                this@KeyboardDrag.controller = null
            }
        }
        return Velocity(0f, available.y)
    }

    /** The keyboard's top `bottom` pixels above the window's foot. */
    private fun move(bottom: Float) {
        val controller = controller ?: return
        val insets = controller.currentInsets
        controller.setInsetsAndAlpha(Insets.of(insets.left, insets.top, insets.right, bottom.roundToInt()), 1f, 0f)
    }

    override fun onReady(controller: WindowInsetsAnimationController, types: Int) {
        requested = false
        // Once what scrolls has left, or once the finger has lifted with no fling waiting for it: nothing would finish
        // it, and the keyboard would stay in the app's hands.
        if (disposed || (!pressed && waiting == null)) {
            leave(controller)
            return
        }
        this.controller = controller
        hand(controller)
    }

    /** What scrolls has left: the keyboard is left where it stands, and what the window answers after is let go. */
    fun dispose() {
        if (disposed) return
        disposed = true
        hand(null)
        animation?.cancel()
        animation = null
        controller?.let(::leave)
        controller = null
    }

    /** Finishes [controller] where the keyboard stands, shown unless all the way down. */
    private fun leave(controller: WindowInsetsAnimationController) = controller.finish(controller.currentInsets != controller.hiddenStateInsets)

    override fun onFinished(controller: WindowInsetsAnimationController) = ended()

    override fun onCancelled(controller: WindowInsetsAnimationController?) = ended()

    /** The window's control of the keyboard ended, or was never given. */
    private fun ended() {
        if (disposed) return
        if (controller?.isReady == true) controller?.finish(shown())
        controller = null
        hand(null)
        animation?.cancel()
        animation = null
        partial = 0f
        requested = false
    }
}
