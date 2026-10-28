package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.sp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.AffineTransform
import com.plhery.peekdeliverytracker.core.MapDrawing
import com.plhery.peekdeliverytracker.core.MapFont
import com.plhery.peekdeliverytracker.core.MapPath
import com.plhery.peekdeliverytracker.core.StrokeStyle
import com.plhery.peekdeliverytracker.ui.theme.toColor
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

// Carries out :core's drawings (MapDrawing steps over MapPaths) as SwiftUI's GraphicsContext and CoreGraphics do on
// the iPhone: the mark, the trucks, and later the world map and Pip. Everything is in the drawing's own units; the
// caller scales and moves the canvas there first (`withAffineTransform`, DrawScope's `scale`) and clips it.

/**
 * A MapPath as a Compose path, element for element. CoreGraphics' arcs and ellipses become the cubic curves it makes
 * of them (a quarter turn at most per curve), so a full turn draws whole, which Android's own arcTo would not.
 */
fun MapPath.toComposePath(evenOdd: Boolean = false): Path {
    val path = Path()
    path.fillType = if (evenOdd) PathFillType.EvenOdd else PathFillType.NonZero
    replay(ComposePathSink(path))
    return path
}

private class ComposePathSink(private val path: Path) : MapPath.Sink {
    private var hasCurrentPoint = false
    private var startX = 0.0
    private var startY = 0.0

    override fun move(x: Double, y: Double) {
        path.moveTo(x.toFloat(), y.toFloat())
        hasCurrentPoint = true
        startX = x
        startY = y
    }

    // With no current point a line starts the subpath, as :core reads it; a curve has nowhere to start and is skipped.
    override fun line(x: Double, y: Double) {
        if (hasCurrentPoint) path.lineTo(x.toFloat(), y.toFloat()) else move(x, y)
    }

    override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) {
        if (hasCurrentPoint) path.quadraticTo(controlX.toFloat(), controlY.toFloat(), x.toFloat(), y.toFloat())
    }

    override fun curve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double) {
        if (!hasCurrentPoint) return
        path.cubicTo(control1X.toFloat(), control1Y.toFloat(), control2X.toFloat(), control2Y.toFloat(), x.toFloat(), y.toFloat())
    }

    override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) {
        val firstX = centerX + radius * cos(startAngle)
        val firstY = centerY + radius * sin(startAngle)
        line(firstX, firstY)
        val pieces = ceil(abs(delta) / (PI / 2)).toInt()
        val step = delta / pieces
        for (piece in 0 until pieces) arcPiece(centerX, centerY, radius, radius, startAngle + step * piece, step)
    }

    override fun ellipse(x: Double, y: Double, width: Double, height: Double) {
        val centerX = x + width / 2
        val centerY = y + height / 2
        move(x + width, centerY)
        for (quarter in 0 until 4) arcPiece(centerX, centerY, width / 2, height / 2, quarter * PI / 2, PI / 2)
        close()
    }

    override fun close() {
        path.close()
    }

    /** One cubic curve for a turn of `sweep` (a quarter at most) round an ellipse, toward screen y when positive. */
    private fun arcPiece(centerX: Double, centerY: Double, radiusX: Double, radiusY: Double, from: Double, sweep: Double) {
        val to = from + sweep
        // 4/3 tan(θ/4): for a quarter turn, CoreGraphics' 4/3 (√2 − 1).
        val handle = 4.0 / 3 * tan(sweep / 4)
        val cosFrom = cos(from)
        val sinFrom = sin(from)
        val cosTo = cos(to)
        val sinTo = sin(to)
        path.cubicTo(
            (centerX + radiusX * (cosFrom - handle * sinFrom)).toFloat(),
            (centerY + radiusY * (sinFrom + handle * cosFrom)).toFloat(),
            (centerX + radiusX * (cosTo + handle * sinTo)).toFloat(),
            (centerY + radiusY * (sinTo - handle * cosTo)).toFloat(),
            (centerX + radiusX * cosTo).toFloat(),
            (centerY + radiusY * sinTo).toFloat(),
        )
    }
}

/** A CGAffineTransform as Compose's matrix: x' = a x + c y + tx, y' = b x + d y + ty. */
fun AffineTransform.toMatrix(): Matrix = Matrix().also {
    it.values[Matrix.ScaleX] = a.toFloat()
    it.values[Matrix.SkewY] = b.toFloat()
    it.values[Matrix.SkewX] = c.toFloat()
    it.values[Matrix.ScaleY] = d.toFloat()
    it.values[Matrix.TranslateX] = tx.toFloat()
    it.values[Matrix.TranslateY] = ty.toFloat()
}

/** `context.concatenate(transform)` for the steps drawn in `block`. */
inline fun DrawScope.withAffineTransform(affine: AffineTransform, block: DrawScope.() -> Unit) {
    val matrix = affine.toMatrix()
    withTransform({ transform(matrix) }, block)
}

/** `context.clip(to:)`: what `block` draws shows only inside `path`. */
inline fun DrawScope.clipToMapPath(path: MapPath, evenOdd: Boolean = false, block: DrawScope.() -> Unit) {
    clipPath(path.toComposePath(evenOdd), block = block)
}

/**
 * Carries out `steps` in order, each colour as it is in this appearance. A cut at the top level clears only what these
 * steps drew, as on the iPhone's canvas, never what lies behind. Text steps need a measurer.
 */
fun DrawScope.drawMapDrawings(
    steps: List<MapDrawing>,
    dark: Boolean,
    highContrast: Boolean = false,
    textMeasurer: TextMeasurer? = null,
) {
    val painter = MapDrawingPainter(dark, highContrast, textMeasurer)
    if (steps.any { it.isCut }) painter.layer(this, steps) else painter.draw(this, steps)
}

private val MapDrawing.isCut: Boolean
    get() = (this is MapDrawing.Fill && cut) || (this is MapDrawing.Stroke && cut)

private class MapDrawingPainter(val dark: Boolean, val highContrast: Boolean, val textMeasurer: TextMeasurer?) {
    fun draw(scope: DrawScope, steps: List<MapDrawing>) {
        for (step in steps) {
            when (step) {
                is MapDrawing.Fill -> scope.drawPath(
                    path = step.path.toComposePath(step.evenOdd),
                    color = color(step.color, step.opacity),
                    style = Fill,
                    blendMode = if (step.cut) BlendMode.DstOut else BlendMode.SrcOver,
                )
                is MapDrawing.Stroke -> scope.drawPath(
                    path = step.path.toComposePath(),
                    color = color(step.color, step.opacity),
                    style = stroke(step.style),
                    blendMode = if (step.cut) BlendMode.DstOut else BlendMode.SrcOver,
                )
                is MapDrawing.Shade -> shade(scope, step)
                is MapDrawing.Layer -> layer(scope, step.steps)
                is MapDrawing.Text -> text(scope, step)
            }
        }
    }

    /** `drawLayer`: the steps go into a layer of their own, then onto what is below, so a cut there clears only them. */
    fun layer(scope: DrawScope, steps: List<MapDrawing>) {
        val canvas = scope.drawContext.canvas.nativeCanvas
        val saved = canvas.saveLayer(null, null)
        draw(scope, steps)
        canvas.restoreToCount(saved)
    }

    fun color(color: AdaptiveColor, opacity: Double): Color {
        val resolved = color.resolve(dark, highContrast).toColor()
        return resolved.copy(alpha = resolved.alpha * opacity.coerceIn(0.0, 1.0).toFloat())
    }

    /** CoreGraphics' stroke: its own mitre limit (10, where Compose's default is 4) and dashes, starting at 0. */
    fun stroke(style: StrokeStyle): Stroke {
        val dash = if (style.dash.size % 2 == 1) style.dash + style.dash else style.dash
        val effect = if (dash.isNotEmpty() && dash.sum() > 0) {
            PathEffect.dashPathEffect(FloatArray(dash.size) { dash[it].toFloat() }, 0f)
        } else {
            null
        }
        return Stroke(
            width = style.lineWidth.toFloat(),
            miter = style.miterLimit.toFloat(),
            cap = when (style.lineCap) {
                StrokeStyle.LineCap.Butt -> StrokeCap.Butt
                StrokeStyle.LineCap.Round -> StrokeCap.Round
                StrokeStyle.LineCap.Square -> StrokeCap.Square
            },
            join = when (style.lineJoin) {
                StrokeStyle.LineJoin.Miter -> StrokeJoin.Miter
                StrokeStyle.LineJoin.Round -> StrokeJoin.Round
                StrokeStyle.LineJoin.Bevel -> StrokeJoin.Bevel
            },
            pathEffect = effect,
        )
    }

    /**
     * A radial gradient from clear at `startRadius` to the colour at `endRadius`, flat beyond both. Clear is the colour
     * at no opacity, which is what blending from `.clear` gives in premultiplied colour.
     */
    fun shade(scope: DrawScope, step: MapDrawing.Shade) {
        val color = color(step.color, 1.0)
        val end = step.endRadius.toFloat()
        if (!(end > 0)) return
        val start = (step.startRadius.toFloat() / end).coerceIn(0f, 1f)
        val brush = Brush.radialGradient(
            0f to color.copy(alpha = 0f),
            start to color.copy(alpha = 0f),
            1f to color,
            center = Offset(step.center.x.toFloat(), step.center.y.toFloat()),
            radius = end,
            tileMode = TileMode.Clamp,
        )
        scope.drawPath(step.path.toComposePath(), brush, alpha = step.opacity.coerceIn(0.0, 1.0).toFloat())
    }

    /**
     * A line of text at its size in the drawing's units whatever the reader's text size, the middle of its leading
     * edge at `at`, on one line.
     */
    fun text(scope: DrawScope, step: MapDrawing.Text) {
        val measurer = requireNotNull(textMeasurer) { "A text step needs a TextMeasurer" }
        val style = TextStyle(
            color = color(step.color, step.opacity),
            fontSize = step.font.size.sp,
            fontWeight = when (step.font.weight) {
                MapFont.Weight.Regular -> FontWeight.Normal
                MapFont.Weight.Medium -> FontWeight.Medium
                MapFont.Weight.Semibold -> FontWeight.SemiBold
            },
            letterSpacing = step.tracking.sp,
        )
        val layout = measurer.measure(
            step.text,
            style,
            overflow = TextOverflow.Visible,
            softWrap = false,
            maxLines = 1,
            density = UNIT_DENSITY,
        )
        scope.drawText(layout, topLeft = Offset(step.at.x.toFloat(), step.at.y.toFloat() - layout.size.height / 2f))
    }

    companion object {
        /** One sp to one unit of the drawing, so text keeps the size the drawing gives it. */
        private val UNIT_DENSITY = Density(1f, 1f)
    }
}
