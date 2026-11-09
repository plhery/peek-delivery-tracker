package com.plhery.peekdeliverytracker.ui.theme

import android.graphics.Region
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.isOutOfBounds
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.requireLayoutDirection
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlin.math.ceil
import kotlin.math.floor

/**
 * A button as UIKit tracks one (a SwiftUI `Button`, `Link` or `Menu` label): `Modifier.clickable`, whose finger may
 * leave the button and still hold it. Dragged off, the press stays shown while the finger is within [HELD_REACH] of
 * the bounds, goes away past it and comes back with the finger, and a lift where it shows acts. A scroll or a drag
 * that takes the finger lets go of it, as before. TalkBack, the keyboard and a pointer's hover are `clickable`'s.
 *
 * Where `shape` is given (`.contentShape(_:)`), a finger that lands in the bounds but outside it, in a rounded corner,
 * starts nothing; the room Android adds round a small button to reach the touch size still takes one. A button disabled
 * under a held finger lets go of it, as `clickable` does.
 */
fun Modifier.button(
    interactionSource: MutableInteractionSource?,
    indication: Indication?,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    shape: Shape? = null,
    onClick: () -> Unit,
): Modifier = composed(
    debugInspectorInfo {
        name = "button"
        properties["enabled"] = enabled
        properties["role"] = role
        properties["shape"] = shape
    },
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    // Inside `clickable`: a child's press comes first in the main pass, so a down a child took is told from one
    // `clickable` takes.
    Modifier
        .clickable(source, indication, enabled, onClickLabel, role, onClick)
        .then(HeldPressElement(source, enabled, shape, onClick))
}

/** How far past a button's bounds UIKit keeps tracking a finger dragged off it. */
val HELD_REACH = 70.dp

private data class HeldPressElement(
    val source: MutableInteractionSource,
    val enabled: Boolean,
    val shape: Shape?,
    val onClick: () -> Unit,
) : ModifierNodeElement<HeldPressNode>() {
    override fun create(): HeldPressNode = HeldPressNode(source, enabled, shape, onClick)

    override fun update(node: HeldPressNode) {
        node.source = source
        node.enabled = enabled
        node.shape = shape
        node.onClick = onClick
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "heldPress"
        properties["enabled"] = enabled
        properties["shape"] = shape
    }
}

private class HeldPressNode(
    var source: MutableInteractionSource,
    enabled: Boolean,
    var shape: Shape?,
    var onClick: () -> Unit,
) : DelegatingNode() {
    private val input = delegate(SuspendingPointerInputModifierNode { track() })

    var enabled: Boolean = enabled
        set(value) {
            if (field == value) return
            field = value
            // The finger held is let go of, its press cancelled ([track]'s `finally`).
            if (!value) input.resetPointerInputHandler()
        }

    /**
     * Each finger that lands on the button: `clickable` presses it and acts on a lift in its bounds; once the finger
     * leaves them, `clickable` lets go and the press is this node's. Read in the initial pass, so that the press
     * shown here comes before `clickable`'s cancel and the button never flickers.
     */
    private suspend fun PointerInputScope.track() = awaitEachGesture {
        val down = awaitFirstDown(requireUnconsumed = false, pass = PointerEventPass.Initial)
        if (!enabled) return@awaitEachGesture
        val shape = shape
        val bounds = size.toSize()
        if (shape != null && Rect(Offset.Zero, bounds).contains(down.position) &&
            !shape.createOutline(bounds, this@HeldPressNode.requireLayoutDirection(), this).holds(down.position)
        ) {
            down.consume()
            return@awaitEachGesture
        }
        val landed = awaitPointerEvent(PointerEventPass.Main)
        if (landed.changes.any { it.id == down.id && it.isConsumed }) return@awaitEachGesture
        val reach = HELD_REACH.toPx()
        val held = Rect(-reach, -reach, size.width + reach, size.height + reach)
        var away = false
        var press: PressInteraction.Press? = null
        try {
            while (true) {
                val event = awaitPointerEvent(PointerEventPass.Initial)
                val change = event.changes.firstOrNull { it.id == down.id } ?: return@awaitEachGesture
                if (change.changedToUpIgnoreConsumed()) {
                    if (away && enabled && change.changedToUp() && held.contains(change.position)) {
                        change.consume()
                        val shown = press ?: PressInteraction.Press(change.position).also { source.tryEmit(it) }
                        press = null
                        source.tryEmit(PressInteraction.Release(shown))
                        onClick()
                    }
                    return@awaitEachGesture
                }
                // `clickable` lets go of a finger out of its bounds, as it sees in the main pass of this event.
                if (!away && change.isOutOfBounds(size, extendedTouchPadding)) away = true
                if (away) {
                    val within = held.contains(change.position)
                    if (within && press == null) {
                        press = PressInteraction.Press(change.position).also { source.tryEmit(it) }
                    } else if (!within) {
                        press?.let { source.tryEmit(PressInteraction.Cancel(it)) }
                        press = null
                    }
                }
                // A scroll or a drag that took the finger.
                val taken = awaitPointerEvent(PointerEventPass.Final)
                if (taken.changes.any { it.isConsumed }) return@awaitEachGesture
            }
        } finally {
            press?.let { source.tryEmit(PressInteraction.Cancel(it)) }
        }
    }
}

/** Whether `point` falls within the outline. */
private fun Outline.holds(point: Offset): Boolean = when (this) {
    is Outline.Rectangle -> rect.contains(point)
    is Outline.Rounded -> roundRect.contains(point)
    is Outline.Generic -> {
        val bounds = path.getBounds()
        bounds.contains(point) && Region().run {
            setPath(path.asAndroidPath(), Region(floor(bounds.left).toInt(), floor(bounds.top).toInt(), ceil(bounds.right).toInt(), ceil(bounds.bottom).toInt()))
            contains(point.x.toInt(), point.y.toInt())
        }
    }
}
