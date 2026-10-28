package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.PeekMarkArtwork
import com.plhery.peekdeliverytracker.ui.theme.toRgba
import kotlin.math.ceil
import com.plhery.peekdeliverytracker.core.PeekMark as PeekMarkGeometry

/**
 * The mark (PeekMark.swift): two eyes on a postal-yellow tile with continuous corners, the same in every appearance.
 * Decorative, as the name is always written beside it (A-AX16).
 */
@Composable
fun PeekMark(size: Dp, modifier: Modifier = Modifier) {
    Canvas(modifier.size(size).clearAndSetSemantics {}) {
        drawPeekMark(this.size.width.toDouble())
    }
}

/** The mark filling a square `side` pixels wide from the origin, clipped to its tile. */
fun DrawScope.drawPeekMark(side: Double) {
    val mark = PeekMarkGeometry(side)
    clipToMapPath(mark.clip) {
        val scale = mark.scale.toFloat()
        scale(scale, scale, pivot = Offset.Zero) {
            drawMapDrawings(mark.drawing, dark = false)
        }
    }
}

/**
 * The mark's eyes alone in one colour, the pupils cut out around their catchlights (the Mac menu bar's icon): `height`
 * tall and as wide as the eyes are at that height, rounded up. Decorative.
 */
@Composable
fun PeekEyes(color: Color, modifier: Modifier = Modifier, height: Dp = 12.dp) {
    val bounds = PeekMarkArtwork.eyesBounds
    val width = Dp(ceil(bounds.width * height.value / bounds.height).toFloat())
    val eyes = remember(color) { PeekMarkArtwork.drawEyes(AdaptiveColor(color.toRgba())) }
    Canvas(modifier.size(width, height).clearAndSetSemantics {}) {
        val scale = (size.height / bounds.height).toFloat()
        scale(scale, scale, pivot = Offset.Zero) {
            translate(-bounds.minX.toFloat(), -bounds.minY.toFloat()) {
                drawMapDrawings(eyes, dark = false)
            }
        }
    }
}
