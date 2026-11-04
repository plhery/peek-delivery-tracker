package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.theme.LocalPeekColors

/** The warm light that catches a sheet's or a card's top edge in dark: the web's rgb(255 244 220). */
val Lamplight = Color(255, 244, 220)

/** A carrier card's lit edge (the web's `--card-edge`). */
const val CARD_EDGE_OPACITY = 0.08f

/** A sheet's lit edge (the web's `--sheet-shadow`). */
const val SHEET_EDGE_OPACITY = 0.09f

/**
 * In dark, one dp of the lamp's light at [opacity] along the top of what is shaped [shape], thinning away round its
 * corners as the web's inset `0 1px 0` shadow does: the shape less itself one dp lower. Nothing in light. Put it after
 * the fill, so that it lies over the fill and under what is drawn on it.
 */
fun Modifier.litEdge(shape: Shape, opacity: Float = CARD_EDGE_OPACITY): Modifier = this then LitEdgeElement(shape, opacity)

private data class LitEdgeElement(val shape: Shape, val opacity: Float) : ModifierNodeElement<LitEdgeNode>() {
    override fun create() = LitEdgeNode(shape, opacity)

    override fun update(node: LitEdgeNode) {
        if (node.shape != shape) {
            node.shape = shape
            node.edge = null
        }
        node.opacity = opacity
        node.invalidateDraw()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "litEdge"
        properties["shape"] = shape
        properties["opacity"] = opacity
    }
}

private class LitEdgeNode(var shape: Shape, var opacity: Float) :
    Modifier.Node(), DrawModifierNode, CompositionLocalConsumerModifierNode {
    var edge: Path? = null
    private var edgeSize = Size.Unspecified

    override fun ContentDrawScope.draw() {
        if (currentValueOf(LocalPeekColors).dark) {
            val edge = edge?.takeIf { edgeSize == size } ?: litEdgePath(shape, size, this).also {
                edge = it
                edgeSize = size
            }
            drawPath(edge, Lamplight.copy(alpha = opacity))
        }
        drawContent()
    }
}

/** [shape] at [size] less itself one dp lower: the band along its top that the lamp lights. */
internal fun litEdgePath(shape: Shape, size: Size, density: Density): Path {
    val outline = Path().apply { addOutline(shape.createOutline(size, LayoutDirection.Ltr, density)) }
    val lower = Path().apply { addPath(outline, Offset(0f, with(density) { 1.dp.toPx() })) }
    return Path.combine(PathOperation.Difference, outline, lower)
}
