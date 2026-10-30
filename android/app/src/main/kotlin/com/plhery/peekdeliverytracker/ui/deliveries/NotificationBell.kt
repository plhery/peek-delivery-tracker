package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekKeyframes
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.keyframeAnimator

/**
 * `ParcelNotificationBell` (B-S03): the page's bell, 18 square, light at 16 points in `tint`. A line strikes it out
 * while `muted`, drawn in over 0.22 s (B-AN06), and it rings each time `trigger` changes (B-AN07): it swings about a
 * point 16 % down its height and gives a little, and two sound lines pulse beside it as it comes back on. Reduce Motion
 * keeps it still and draws the line at once. Decorative: its button says what it does.
 */
@Composable
internal fun ParcelNotificationBell(muted: Boolean, trigger: Int, tint: Color, modifier: Modifier = Modifier) {
    val reduceMotion = PeekTheme.reduceMotion
    val pose = keyframeAnimator(PeekKeyframes.BellPose(), trigger, still = reduceMotion) { PeekKeyframes.bell(muted) }
    // Worked out once a frame, for the bell's two layers and its sound lines.
    val drawing by remember(muted, reduceMotion) { derivedStateOf { BellDrawing.of(pose.value, muted, reduceMotion) } }
    val slash by animateFloatAsState(
        if (muted) 1f else 0f,
        if (reduceMotion) snap() else Motion.easeOut(0.22),
        label = "bell slash",
    )
    Box(modifier.size(BELL_SIDE).clearAndSetSemantics {}, contentAlignment = Alignment.Center) {
        // `.rotationEffect(_:anchor:)` under `.scaleEffect(_:)`, on the bell alone.
        Box(
            Modifier
                .size(BELL_SIDE)
                .graphicsLayer {
                    val scale = drawing.scale
                    scaleX = scale
                    scaleY = scale
                }
                .graphicsLayer {
                    rotationZ = drawing.angle
                    transformOrigin = TransformOrigin(0.5f, 0.16f)
                },
            contentAlignment = Alignment.Center,
        ) {
            PeekSymbol("bell", font = PeekTheme.type.fixed(16f, FontWeight.Light), tint = tint)
        }
        Canvas(Modifier.fillMaxSize()) {
            val unit = size.width / 18f
            if (slash > 0f) {
                // `.trim(from: 0, to:)` of the line from (2, 2) to (16, 16), and as opaque as it is drawn.
                val end = 2f + 14f * slash
                drawLine(
                    tint.copy(alpha = tint.alpha * slash), Offset(2f * unit, 2f * unit), Offset(end * unit, end * unit),
                    strokeWidth = 1.dp.toPx(), cap = StrokeCap.Round,
                )
            }
            val waves = drawing.waves
            if (waves > 0f) {
                val lines = Path().apply {
                    moveTo(0f, 4f * unit)
                    quadraticTo(-2f * unit, 7.5f * unit, 0f, 11f * unit)
                    moveTo(18f * unit, 4f * unit)
                    quadraticTo(20f * unit, 7.5f * unit, 18f * unit, 11f * unit)
                }
                drawPath(lines, tint.copy(alpha = tint.alpha * waves), style = Stroke(0.9.dp.toPx(), cap = StrokeCap.Round))
            }
        }
    }
}

/** What the bell shows of its keyframes' `pose`: still under Reduce Motion, and without its sound lines while muted. */
internal data class BellDrawing(val angle: Float, val scale: Float, val waves: Float) {
    companion object {
        fun of(pose: PeekKeyframes.BellPose, muted: Boolean, reduceMotion: Boolean): BellDrawing = BellDrawing(
            angle = if (reduceMotion) 0f else pose.angle.toFloat(),
            scale = if (reduceMotion) 1f else pose.scale.toFloat(),
            waves = if (reduceMotion || muted) 0f else pose.waves.toFloat(),
        )
    }
}

private val BELL_SIDE = 18.dp
