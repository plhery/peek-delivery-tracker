package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow

/**
 * `Toggle` in its switch style as iOS 26 draws it: a capsule 63 × 28, the system's grey fill while off and `tint` while
 * on, its knob a white capsule 37 × 24, 2 in from its ends, shadowed, sliding from one end to the other on a snappy
 * spring as it turns. Disabled, it shows at half strength and takes no tap. A tap on it turns it to the other side
 * (`onChange`); TalkBack reads it as a switch, on or off, and a row that merges it reads as one with its label.
 */
@Composable
fun Toggle(on: Boolean, onChange: (Boolean) -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true, tint: Color = PeekTheme.colors.green) {
    val colors = PeekTheme.colors
    val progress by animateFloatAsState(if (on) 1f else 0f, Motion.snappy(SLIDE_DURATION), label = "toggle")
    val off = if (colors.dark) OFF_DARK else OFF_LIGHT
    Box(
        modifier
            .size(TRACK_WIDTH, TRACK_HEIGHT)
            .alpha(if (enabled) 1f else DISABLED_ALPHA)
            .toggleable(on, interactionSource = null, indication = null, enabled = enabled, role = Role.Switch, onValueChange = onChange)
            .background(lerp(off, tint, progress.coerceIn(0f, 1f)), CapsuleShape),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            Modifier
                .padding(start = KNOB_INSET)
                .offset { IntOffset(((TRACK_WIDTH - KNOB_WIDTH - KNOB_INSET * 2) * progress).roundToPx(), 0) }
                .size(KNOB_WIDTH, KNOB_HEIGHT)
                .dropShadow(CapsuleShape, KNOB_SHADOW)
                .background(Color.White, CapsuleShape),
        )
    }
}

private val TRACK_WIDTH = 63.dp
private val TRACK_HEIGHT = 28.dp
private val KNOB_WIDTH = 37.dp
private val KNOB_HEIGHT = 24.dp
private val KNOB_INSET = 2.dp
private const val SLIDE_DURATION = 0.3
private const val DISABLED_ALPHA = 0.5f

/** The track while off: the system's fill, (120, 120, 128) at 16 % in light and 32 % in dark. */
private val OFF_LIGHT = Color(120, 120, 128).copy(alpha = 0.16f)
private val OFF_DARK = Color(120, 120, 128).copy(alpha = 0.32f)
private val KNOB_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.15f), radius = 3.dp, y = 2.dp)
