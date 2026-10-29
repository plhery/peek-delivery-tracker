package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.animation.core.withInfiniteAnimationFrameNanos
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme

/**
 * `ProgressView()` at `.controlSize(.large)`, UIKit's large activity indicator: eight rounded spokes, the leading one
 * whole and the others fading behind it, stepping round clockwise once a second. It turns at its own pace whatever
 * the animator duration scale and under Reduce Motion, as iOS's spinners do, on frame time.
 */
@Composable
fun ActivityIndicator(modifier: Modifier = Modifier, size: Dp = 37.dp, tint: Color = PeekTheme.colors.ink) {
    var step by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        val start = withInfiniteAnimationFrameNanos { it }
        while (true) {
            withInfiniteAnimationFrameNanos { now -> step = ((now - start) / STEP_NANOS % SPOKES).toInt() }
        }
    }
    Canvas(modifier.progressSemantics().size(size)) {
        val radius = this.size.minDimension / 2
        val width = radius * SPOKE_WIDTH
        for (spoke in 0 until SPOKES) {
            val behind = (step - spoke + SPOKES) % SPOKES
            rotate(spoke * 360f / SPOKES) {
                drawLine(
                    tint.copy(alpha = tint.alpha * (1 - FADE * behind / (SPOKES - 1))),
                    start = Offset(center.x, center.y - radius * SPOKE_INNER - width / 2),
                    end = Offset(center.x, center.y - radius + width / 2),
                    strokeWidth = width,
                    cap = StrokeCap.Round,
                )
            }
        }
    }
}

private const val SPOKES = 8
private const val STEP_NANOS = 1_000_000_000L / SPOKES

/** A spoke's width, and where it starts, as fractions of the indicator's radius. */
private const val SPOKE_WIDTH = 0.24f
private const val SPOKE_INNER = 0.42f

/** How much the spoke furthest behind the leading one has faded. */
private const val FADE = 0.7f
