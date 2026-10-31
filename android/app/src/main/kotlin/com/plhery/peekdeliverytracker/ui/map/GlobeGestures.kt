package com.plhery.peekdeliverytracker.ui.map

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.unit.dp
import kotlin.math.abs

/**
 * The globe's gestures, as SwiftUI recognises them together: `DragGesture(minimumDistance: 4)` and `MagnifyGesture()`
 * (`moveGesture`), or the pinch alone (`peekGesture`, with `pinchOnly`). One finger and two are read from the same
 * stream of touches, so a pinch can start during a drag, as SwiftUI's simultaneous gestures allow.
 *
 * - `onDrag`: the first finger's way from where it went down, in pixels, once it has gone 4 points, until the gesture
 *   pinches. Never with `pinchOnly`.
 * - `onPinch`: how far apart two fingers are over how far apart they were as the second went down, once that has
 *   changed by a hundredth, and where they stood between them then. A pinch ends with a finger lifted; the gesture
 *   pinches only once.
 * - `onEnd`: the last finger lifted after a drag or a pinch.
 *
 * A touch something holding the map took on its way to it ends the gesture. Every touch is taken, except with
 * `pinchOnly`: then one finger is left to what lies under the map, a page's scroll or a card's tap, and touches are
 * taken only while two fingers are down or the gesture has pinched.
 */
internal suspend fun PointerInputScope.detectGlobeGestures(
    pinchOnly: Boolean,
    onDrag: (translation: Offset) -> Unit,
    onPinch: (magnification: Float, anchor: Offset) -> Unit,
    onEnd: () -> Unit,
) = awaitEachGesture {
    val first = awaitFirstDown()
    val minimumDistance = 4.dp.toPx()
    var dragging = false
    var moved = false
    // How far apart the two fingers were as the second went down, and where they stood between them.
    var span: Float? = null
    var anchor = Offset.Zero
    var pinching = false
    var pinched = false
    while (true) {
        val event = awaitPointerEvent()
        val down = event.changes.filter { it.pressed }
        // Taken on its way here, by what holds the map as it goes: the gesture is over.
        if (down.isEmpty() || event.changes.any { it.isConsumed }) break
        if (down.size >= 2) {
            val a = down[0].position
            val b = down[1].position
            val from = span
            if (from == null) {
                // A second pinch in the same gesture is none, as SwiftUI's ended pinch doesn't start again.
                if (!pinched) {
                    span = (a - b).getDistance()
                    anchor = (a + b) / 2f
                }
            } else if (from > 0) {
                val magnification = (a - b).getDistance() / from
                if (!pinching && abs(magnification - 1) >= MINIMUM_SCALE_DELTA) pinching = true
                if (pinching) {
                    pinched = true
                    onPinch(magnification, anchor)
                }
            }
        } else {
            span = null
            pinching = false
        }
        if (!pinchOnly && !pinched) {
            val finger = event.changes.firstOrNull { it.id == first.id && it.pressed }
            if (finger != null) {
                val translation = finger.position - first.position
                if (!dragging && translation.getDistance() >= minimumDistance) dragging = true
                if (dragging) {
                    moved = true
                    onDrag(translation)
                }
            }
        }
        if (!pinchOnly || down.size >= 2 || pinched) event.changes.forEach { it.consume() }
    }
    if (moved || pinched) onEnd()
}

/** `MagnifyGesture`'s `minimumScaleDelta`. */
private const val MINIMUM_SCALE_DELTA = 0.01f
