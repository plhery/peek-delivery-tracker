package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.theme.LocalPeekColors
import com.plhery.peekdeliverytracker.ui.theme.Radii

/**
 * SwiftUI's `.shadow(color:radius:x:y:)`: a Gaussian blur whose deviation is half the radius, which Compose's shadow
 * takes as a blur radius of √3 deviations, as Android's blur does.
 */
fun swiftUiShadow(color: Color, radius: Dp, x: Dp = 0.dp, y: Dp = 0.dp): Shadow =
    Shadow(radius = radius * (SQRT3 / 2), color = color, offset = DpOffset(x, y))

private const val SQRT3 = 1.7320508f

/** The experimental surfaces' shadow (A-S59): black at 3.5 %, radius 10, 4 points down. */
val ExperimentalSurfaceShadow: Shadow = swiftUiShadow(Color.Black.copy(alpha = 0.035f), radius = 10.dp, y = 4.dp)

/**
 * `experimentalSurface(tint:fill:cornerRadius:shadow:)` (A-S69): behind the content, a continuous rounded rectangle in
 * `fill` (paper when null), `tint` over it at 12 %, its outline stroked in the separator at 42 % and 0.6 wide, and
 * [ExperimentalSurfaceShadow] under it unless `shadow` is false.
 */
fun Modifier.experimentalSurface(
    fill: Color? = null,
    tint: Color = Color.Transparent,
    cornerRadius: Dp = Radii.experimentalSurface,
    shadow: Boolean = true,
): Modifier {
    val shape = ContinuousRoundedShape(cornerRadius)
    return (if (shadow) dropShadow(shape, ExperimentalSurfaceShadow) else this) then ExperimentalSurfaceElement(fill, tint, shape)
}

private data class ExperimentalSurfaceElement(val fill: Color?, val tint: Color, val shape: Shape) :
    ModifierNodeElement<ExperimentalSurfaceNode>() {
    override fun create() = ExperimentalSurfaceNode(fill, tint, shape)

    override fun update(node: ExperimentalSurfaceNode) {
        node.fill = fill
        node.tint = tint
        if (node.shape != shape) {
            node.shape = shape
            node.outline = null
        }
        node.invalidateDraw()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "experimentalSurface"
        properties["fill"] = fill
        properties["tint"] = tint
        properties["shape"] = shape
    }
}

private class ExperimentalSurfaceNode(var fill: Color?, var tint: Color, var shape: Shape) :
    Modifier.Node(), DrawModifierNode, CompositionLocalConsumerModifierNode {
    var outline: Outline? = null
    private var outlineSize = Size.Unspecified

    override fun ContentDrawScope.draw() {
        val colors = currentValueOf(LocalPeekColors)
        val outline = outline?.takeIf { outlineSize == size } ?: shape.createOutline(size, layoutDirection, this).also {
            outline = it
            outlineSize = size
        }
        drawOutline(outline, fill ?: colors.paper)
        if (tint.alpha > 0f) drawOutline(outline, tint.copy(alpha = tint.alpha * 0.12f))
        drawOutline(outline, colors.separator.copy(alpha = colors.separator.alpha * 0.42f), style = Stroke(0.6.dp.toPx()))
        drawContent()
    }
}
