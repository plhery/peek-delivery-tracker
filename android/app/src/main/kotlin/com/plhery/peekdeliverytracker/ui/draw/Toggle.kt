package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlin.math.abs

/**
 * `Toggle` in its switch style as iOS 26 draws it: a capsule 63 × 28, the system's grey fill while off and `tint` while
 * on, its knob a white capsule 37 × 24, 2 in from its ends, with no shadow past the track, sliding from one end to the
 * other on a snappy spring as it turns. Disabled, it shows at half strength and takes no touch. A tap on it turns it
 * to the other side (`onChange`); so does a drag of the knob across, the knob going to the side of the middle the
 * finger is on and the switch turning there as the finger lifts, a drag back undoing it. Under the finger the knob
 * stretches toward the track's middle and turns to glass over the track (`behind` is what the track lies on), settling
 * back as the finger lifts. It plays no haptic, as SwiftUI's `Toggle` plays none of its own. TalkBack reads it as a
 * switch, on or off, and a row that merges it reads as one with its label.
 */
@Composable
fun Toggle(
    on: Boolean,
    onChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = PeekTheme.colors.green,
    behind: Color = PeekTheme.colors.paper,
) {
    val colors = PeekTheme.colors
    // The side the knob is dragged to, while it is, and whether the finger is down on the switch.
    var dragged by remember { mutableStateOf<Boolean?>(null) }
    var pressed by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(if (dragged ?: on) 1f else 0f, Motion.snappy(SLIDE_DURATION), label = "toggle")
    val press by animateFloatAsState(if (pressed && enabled) 1f else 0f, Motion.snappy(PRESS_DURATION), label = "togglePress")
    val currentOn by rememberUpdatedState(on)
    val currentChange by rememberUpdatedState(onChange)
    val track = lerp(if (colors.dark) OFF_DARK else OFF_LIGHT, tint, progress.coerceIn(0f, 1f))
    Box(
        modifier
            .size(TRACK_WIDTH, TRACK_HEIGHT)
            .alpha(if (enabled) 1f else DISABLED_ALPHA)
            .toggleable(on, interactionSource = null, indication = null, enabled = enabled, role = Role.Switch, onValueChange = onChange)
            .pointerInput(enabled) {
                if (enabled) knobGestures(press = { pressed = it }, drag = { dragged = it }) { side -> if (side != currentOn) currentChange(side) }
            }
            .clip(CapsuleShape)
            .background(track),
        contentAlignment = Alignment.CenterStart,
    ) {
        val stretch = press.coerceAtLeast(0f)
        val width = KNOB_WIDTH + KNOB_STRETCH * stretch
        Box(
            Modifier
                .padding(start = KNOB_INSET)
                .offset { IntOffset(((TRACK_WIDTH - width - KNOB_INSET * 2) * progress).roundToPx(), 0) }
                .size(width, KNOB_HEIGHT),
        ) {
            if (stretch > 0f) {
                GlassSurface(CapsuleShape, Modifier.matchParentSize().alpha(stretch.coerceAtMost(1f)), backdrop = null, behind = track.compositeOver(behind)) {}
            }
            Box(Modifier.matchParentSize().alpha(1 - stretch.coerceAtMost(1f)).background(Color.White, CapsuleShape))
        }
    }
}

/**
 * The finger on the switch: [press] as it comes down and lifts. Once it has moved across further than a touch's slop,
 * before it moved as far up or down (a scroll, which takes it), it holds the knob: [drag] gives the side of the middle
 * it is on, and as it lifts [change] that side. A tap is the switch's own, its `toggleable`'s.
 */
private suspend fun PointerInputScope.knobGestures(press: (Boolean) -> Unit, drag: (Boolean?) -> Unit, change: (Boolean) -> Unit) {
    awaitEachGesture {
        val down = awaitFirstDown(requireUnconsumed = false)
        press(true)
        var travelled = Offset.Zero
        var dragging = false
        var side: Boolean? = null
        var lifted = false
        // Let go however the gesture ends, the switch disabled under the finger too.
        try {
            while (true) {
                val event = awaitPointerEvent()
                val finger = event.changes.firstOrNull { it.id == down.id } ?: break
                if (finger.changedToUpIgnoreConsumed()) {
                    lifted = true
                    if (dragging) finger.consume()
                    break
                }
                if (!dragging) {
                    if (finger.isConsumed) break
                    travelled += finger.positionChange()
                    val slop = viewConfiguration.touchSlop
                    if (abs(travelled.y) > slop && abs(travelled.y) >= abs(travelled.x)) break
                    dragging = abs(travelled.x) > slop
                }
                if (dragging) {
                    finger.consume()
                    side = finger.position.x > size.width / 2f
                    drag(side)
                }
            }
        } finally {
            press(false)
            drag(null)
        }
        if (lifted && side != null) change(side)
    }
}

private val TRACK_WIDTH = 63.dp
private val TRACK_HEIGHT = 28.dp
private val KNOB_WIDTH = 37.dp
private val KNOB_HEIGHT = 24.dp
private val KNOB_INSET = 2.dp

/** How much wider the knob grows toward the track's middle under the finger. */
private val KNOB_STRETCH = 7.dp
private const val SLIDE_DURATION = 0.3
private const val PRESS_DURATION = 0.25
private const val DISABLED_ALPHA = 0.5f

/** The track while off: the system's fill, (120, 120, 128) at 16 % in light and 32 % in dark. */
private val OFF_LIGHT = Color(120, 120, 128).copy(alpha = 0.16f)
private val OFF_DARK = Color(120, 120, 128).copy(alpha = 0.32f)
