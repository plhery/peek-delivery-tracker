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
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.AffineTransform
import com.plhery.peekdeliverytracker.core.MapDrawing
import com.plhery.peekdeliverytracker.core.MapFont
import com.plhery.peekdeliverytracker.core.MapPath
import com.plhery.peekdeliverytracker.core.StrokeStyle
import com.plhery.peekdeliverytracker.ui.theme.SfWidths
import com.plhery.peekdeliverytracker.ui.theme.toColor
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sign
import kotlin.math.sin
import kotlin.math.tan

// Carries out :core's drawings (MapDrawing steps over MapPaths) as SwiftUI's GraphicsContext and CoreGraphics do on
// the iPhone: the mark, the trucks, Pip, and later the world map. Everything is in the drawing's own units; the
// caller scales and moves the canvas there first (`withAffineTransform`, DrawScope's `scale`) and clips it.

/**
 * A MapPath as a Compose path, element for element. CoreGraphics' arcs and ellipses become the cubic curves it makes
 * of them (whole quarter turns, then what is left), so a full turn draws whole, which Android's own arcTo would not.
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
        // CGPathAddRelativeArc's split: a curve per whole quarter turn from the start, then one for the rest.
        val quarter = PI / 2 * sign(delta)
        var from = startAngle
        var left = delta
        while (abs(left) > abs(quarter) + ARC_EPSILON) {
            arcPiece(centerX, centerY, radius, radius, from, quarter)
            from += quarter
            left -= quarter
        }
        if (abs(left) > ARC_EPSILON) arcPiece(centerX, centerY, radius, radius, from, left)
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

    private companion object {
        /** Turns smaller than this are rounding, not arc: a quarter turn plus a hair is one quarter. */
        const val ARC_EPSILON = 1e-9
    }
}

/** A CGAffineTransform as Compose's matrix: x' = a x + c y + tx, y' = b x + d y + ty. */
fun AffineTransform.toMatrix(): Matrix = Matrix().also { into(it) }

/** Writes the transform into `matrix`, whose other entries stay the identity's. */
private fun AffineTransform.into(matrix: Matrix) {
    matrix.values[Matrix.ScaleX] = a.toFloat()
    matrix.values[Matrix.SkewY] = b.toFloat()
    matrix.values[Matrix.SkewX] = c.toFloat()
    matrix.values[Matrix.ScaleY] = d.toFloat()
    matrix.values[Matrix.TranslateX] = tx.toFloat()
    matrix.values[Matrix.TranslateY] = ty.toFloat()
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
 * steps drew, as on the iPhone's canvas, never what lies behind. Text steps need a measurer. A drawing drawn again
 * at each frame passes the same `cache` each time.
 */
fun DrawScope.drawMapDrawings(
    steps: List<MapDrawing>,
    dark: Boolean,
    highContrast: Boolean = false,
    textMeasurer: TextMeasurer? = null,
    cache: MapDrawingCache? = null,
) {
    val painter = MapDrawingPainter(dark, highContrast, textMeasurer, cache)
    if (steps.any { it.isCut }) painter.layer(this, steps) else painter.draw(this, steps)
    cache?.endDrawing()
}

/**
 * The Compose paths and strokes of a drawing drawn again at each frame (Pip moving), found again by what they hold:
 * :core records its paths anew for each frame, mostly the same shapes under another transform, and a path is far
 * dearer for Android to build than to find. Each drawing keeps only what it used. For one canvas, on the main thread.
 */
class MapDrawingCache {
    private class CachedPath(val source: MapPath, val evenOdd: Boolean, val path: Path) {
        var drawing = 0
    }

    private class CachedStroke(val stroke: Stroke) {
        var drawing = 0
    }

    private val paths = HashMap<Int, ArrayList<CachedPath>>()
    private val strokes = HashMap<StrokeStyle, CachedStroke>()
    private var drawing = 0

    internal fun path(source: MapPath, evenOdd: Boolean): Path {
        val bucket = paths.getOrPut(31 * source.contentHash() + if (evenOdd) 1 else 0) { ArrayList(1) }
        val found = bucket.firstOrNull { it.evenOdd == evenOdd && it.source.sameAs(source) }
            ?: CachedPath(source.copy(), evenOdd, source.toComposePath(evenOdd)).also { bucket += it }
        found.drawing = drawing
        return found.path
    }

    internal fun stroke(style: StrokeStyle): Stroke {
        val found = strokes.getOrPut(style) { CachedStroke(style.toStroke()) }
        found.drawing = drawing
        return found.stroke
    }

    internal fun endDrawing() {
        val buckets = paths.values.iterator()
        while (buckets.hasNext()) {
            val bucket = buckets.next()
            for (index in bucket.lastIndex downTo 0) if (bucket[index].drawing != drawing) bucket.removeAt(index)
            if (bucket.isEmpty()) buckets.remove()
        }
        val cached = strokes.values.iterator()
        while (cached.hasNext()) if (cached.next().drawing != drawing) cached.remove()
        drawing++
    }
}

private val MapDrawing.isCut: Boolean
    get() = (this is MapDrawing.Fill && cut) || (this is MapDrawing.Stroke && cut)

/** CoreGraphics' stroke: its own mitre limit (10, where Compose's default is 4) and dashes, starting at 0. */
private fun StrokeStyle.toStroke(): Stroke {
    val pattern = if (dash.size % 2 == 1) dash + dash else dash
    val effect = if (pattern.isNotEmpty() && pattern.sum() > 0) {
        PathEffect.dashPathEffect(FloatArray(pattern.size) { pattern[it].toFloat() }, 0f)
    } else {
        null
    }
    return Stroke(
        width = lineWidth.toFloat(),
        miter = miterLimit.toFloat(),
        cap = when (lineCap) {
            StrokeStyle.LineCap.Butt -> StrokeCap.Butt
            StrokeStyle.LineCap.Round -> StrokeCap.Round
            StrokeStyle.LineCap.Square -> StrokeCap.Square
        },
        join = when (lineJoin) {
            StrokeStyle.LineJoin.Miter -> StrokeJoin.Miter
            StrokeStyle.LineJoin.Round -> StrokeJoin.Round
            StrokeStyle.LineJoin.Bevel -> StrokeJoin.Bevel
        },
        pathEffect = effect,
    )
}

private class MapDrawingPainter(
    val dark: Boolean,
    val highContrast: Boolean,
    val textMeasurer: TextMeasurer?,
    val cache: MapDrawingCache?,
) {
    /** One matrix for every transform: the canvas takes its values as it is concatenated. */
    private val matrix = Matrix()

    /** Carries out `steps`, each at `fade` times its own opacity (a transformed context's opacity). */
    fun draw(scope: DrawScope, steps: List<MapDrawing>, fade: Double = 1.0) {
        for (step in steps) {
            val opacity = step.opacity * fade
            when (step) {
                is MapDrawing.Fill -> scope.drawPath(
                    path = path(step.path, step.evenOdd),
                    color = color(step.color, opacity),
                    style = Fill,
                    blendMode = if (step.cut) BlendMode.DstOut else BlendMode.SrcOver,
                )
                is MapDrawing.Stroke -> scope.drawPath(
                    path = path(step.path, evenOdd = false),
                    color = color(step.color, opacity),
                    style = cache?.stroke(step.style) ?: step.style.toStroke(),
                    blendMode = if (step.cut) BlendMode.DstOut else BlendMode.SrcOver,
                )
                is MapDrawing.Shade -> shade(scope, step, opacity)
                is MapDrawing.Layer -> layer(scope, step.steps, fade)
                is MapDrawing.Transformed -> {
                    step.transform.into(matrix)
                    scope.withTransform({ transform(matrix) }) { draw(this, step.steps, opacity) }
                }
                is MapDrawing.Text -> text(scope, step, opacity)
                is MapDrawing.TextLine -> textLine(scope, step, opacity)
            }
        }
    }

    /** `drawLayer`: the steps go into a layer of their own, then onto what is below, so a cut there clears only them. */
    fun layer(scope: DrawScope, steps: List<MapDrawing>, fade: Double = 1.0) {
        val canvas = scope.drawContext.canvas.nativeCanvas
        val saved = canvas.saveLayer(null, null)
        draw(scope, steps, fade)
        canvas.restoreToCount(saved)
    }

    fun path(path: MapPath, evenOdd: Boolean): Path = cache?.path(path, evenOdd) ?: path.toComposePath(evenOdd)

    fun color(color: AdaptiveColor, opacity: Double): Color {
        val resolved = color.resolve(dark, highContrast).toColor()
        return resolved.copy(alpha = resolved.alpha * opacity.coerceIn(0.0, 1.0).toFloat())
    }

    /**
     * A radial gradient from clear at `startRadius` to the colour at `endRadius`, flat beyond both. Clear is the colour
     * at no opacity, which is what blending from `.clear` gives in premultiplied colour.
     */
    fun shade(scope: DrawScope, step: MapDrawing.Shade, opacity: Double) {
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
        scope.drawPath(path(step.path, evenOdd = false), brush, alpha = opacity.coerceIn(0.0, 1.0).toFloat())
    }

    /**
     * A line of text at its size in the drawing's units whatever the reader's text size, the middle of its leading
     * edge at `at`, on one line.
     */
    fun text(scope: DrawScope, step: MapDrawing.Text, opacity: Double) {
        val layout = measure(step.text, step.font, color(step.color, opacity), step.tracking)
        val x = step.at.x.toFloat() - trackingLead(step.tracking)
        scope.drawText(layout, topLeft = Offset(x, step.at.y.toFloat() - layout.size.height / 2f))
    }

    /** A line from the start of its baseline, squeezed across into its room when it is wider. */
    fun textLine(scope: DrawScope, step: MapDrawing.TextLine, opacity: Double) {
        val layout = measure(step.text, step.font, color(step.color, opacity), step.tracking)
        val width = layout.getLineRight(0) - layout.getLineLeft(0)
        val squeeze = if (width > step.room) (step.room / width).toFloat() else 1f
        scope.translate(step.start.x.toFloat(), step.start.y.toFloat() - layout.firstBaseline) {
            scale(squeeze, 1f, pivot = Offset.Zero) {
                drawText(layout, topLeft = Offset(-trackingLead(step.tracking), 0f))
            }
        }
    }

    private fun measure(text: String, font: MapFont, color: Color, tracking: Double): TextLayoutResult =
        measureMapText(requireNotNull(textMeasurer) { "A text step needs a TextMeasurer" }, text, font, color, tracking)

    /**
     * How far Android sets the first character in for `tracking`: it puts half of each character's letter spacing
     * before it, where CoreText adds tracking after each character, so the text is drawn this much earlier to start
     * where the iPhone's does.
     */
    private fun trackingLead(tracking: Double): Float = tracking.toFloat() / 2
}

/** One sp to one unit of the drawing, so text keeps the size the drawing gives it. */
private val UNIT_DENSITY = Density(1f, 1f)

/**
 * One line of text in `font`, one sp to one unit of the drawing, as wide as SF's ([SfWidths]) plus `tracking` after
 * each character: as the renderer writes it, and as [MapTextWidths] measures it for the layout.
 */
internal fun measureMapText(measurer: TextMeasurer, text: String, font: MapFont, color: Color, tracking: Double): TextLayoutResult {
    val weight = when (font.weight) {
        MapFont.Weight.Regular -> FontWeight.Normal
        MapFont.Weight.Medium -> FontWeight.Medium
        MapFont.Weight.Semibold -> FontWeight.SemiBold
        MapFont.Weight.Heavy -> FontWeight.ExtraBold
    }
    val size = font.size.toFloat()
    val sfSpacing = if (font.monospaced) 0f else SfWidths.em(size, weight)
    val style = TextStyle(
        color = color,
        fontSize = size.sp,
        fontWeight = weight,
        fontStyle = if (font.italic) FontStyle.Italic else FontStyle.Normal,
        fontFamily = if (font.monospaced) FontFamily.Monospace else null,
        letterSpacing = if (size > 0) (sfSpacing + tracking.toFloat() / size).em else 0.sp,
    )
    return measurer.measure(text, style, overflow = TextOverflow.Visible, softWrap = false, maxLines = 1, density = UNIT_DENSITY)
}
