package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.stampDeliveryDate
import com.plhery.peekdeliverytracker.core.stampOrigin
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.delay
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * A self-adhesive stamp's serpentine die cut (A-S72): every edge dips into the paper in even waves that meet at the
 * corners, where each edge starts and ends at full width. `wavelength` and `depth` are one wave's length and depth.
 */
@Immutable
data class DieCutStampShape(val wavelength: Dp = 3.6.dp, val depth: Dp = 1.1.dp) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline = with(density) {
        Outline.Generic(dieCutPath(Rect(Offset.Zero, size), wavelength.toPx(), depth.toPx()))
    }

    companion object {
        /** The parcel's stamp is designed 44 wide and scales its waves with its width. */
        const val DESIGN_WIDTH = 44f
    }
}

private fun dieCutPath(rect: Rect, wavelength: Float, depth: Float): Path {
    val path = Path()
    path.moveTo(rect.left, rect.top)
    val corners = listOf(rect.topLeft, rect.topRight, rect.bottomRight, rect.bottomLeft)
    val inward = listOf(Offset(0f, 1f), Offset(-1f, 0f), Offset(0f, -1f), Offset(1f, 0f))
    for (side in 0 until 4) {
        val from = corners[side]
        val to = corners[(side + 1) % 4]
        val length = hypot(to.x - from.x, to.y - from.y)
        val waves = max(1, (length / wavelength).roundToInt())
        val steps = waves * 10
        for (step in 1..steps) {
            val t = step.toFloat() / steps
            val dip = depth * (1 - cos(2 * PI.toFloat() * waves * t)) / 2
            path.lineTo(from.x + (to.x - from.x) * t + inward[side].x * dip, from.y + (to.y - from.y) * t + inward[side].y * dip)
        }
    }
    path.close()
    return path
}

/** `StampGlobeLines`: a globe's meridians and parallels inside its bounding square. */
private fun globeLines(rect: Rect): Path {
    val radius = rect.width / 2
    val center = rect.center
    val path = Path()
    path.addOval(Rect(center.x - radius * 0.42f, rect.top, center.x + radius * 0.42f, rect.bottom))
    path.moveTo(center.x, rect.top)
    path.lineTo(center.x, rect.bottom)
    path.moveTo(rect.left, center.y)
    path.lineTo(rect.right, center.y)
    // The two parallels, half way to each pole.
    val band = radius / 2
    val chord = sqrt(radius * radius - band * band)
    for (y in listOf(center.y - band, center.y + band)) {
        path.moveTo(center.x - chord, y)
        path.lineTo(center.x + chord, y)
    }
    return path
}

/**
 * `DeliveryPostageStamp` (A-S73): the parcel's stamp, the same globe on every parcel printed in the card's own colours,
 * with the country it was posted in. Every measure is in the stamp's 44 × 56 design, scaled to `width`. It settles from
 * askew and large once `appeared`, and the postmark lands with the delivery: large and askew above the paper, then
 * pressed home. Neither moves under Reduce Motion. Decorative.
 */
@Composable
fun DeliveryPostageStamp(
    parcel: Parcel,
    identity: CarrierVisualIdentity,
    appeared: Boolean,
    modifier: Modifier = Modifier,
    width: Dp = 46.dp,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val unit = width.value / DieCutStampShape.DESIGN_WIDTH
    val origin = parcel.stampOrigin
    val date = parcel.stampDeliveryDate
    val ink = colors.resolve(identity.ink)
    val surface = colors.resolve(identity.surface)
    val paper = colors.resolve(identity.stampPaper)
    val globe = colors.resolve(identity.stampGlobe)
    val measurer = rememberTextMeasurer()
    val originStyle = remember(type, unit, ink) {
        type.tracking(type.fixed(5.2f * unit, FontWeight.Bold), 0.5f * unit).sfLines().copy(color = ink)
    }
    val dateStyle = remember(type, unit, ink) {
        type.fixed(5.2f * unit, FontWeight.ExtraBold).monospacedDigit().sfLines().copy(color = ink)
    }

    val settled = reduceMotion || appeared
    val settle = if (reduceMotion) snap() else Motion.springWithResponse<Float>(0.48, 0.6).delay(0.08)
    val rotation by animateFloatAsState(if (settled) -2f else -10f, settle, label = "stamp rotation")
    val scale by animateFloatAsState(if (settled) 1f else 1.12f, settle, label = "stamp scale")

    // How far the postmark has come down: 1 pressed home. One there when the stamp is first shown is already home.
    val pressed = remember { Animatable(if (date != null) 1f else 0f) }
    LaunchedEffect(date) {
        when {
            date == null -> pressed.snapTo(0f)
            reduceMotion -> pressed.snapTo(1f)
            pressed.value < 1f -> pressed.animateTo(1f, Motion.easeIn<Float>(0.28).delay(0.2))
        }
    }

    val shape = remember(unit) { DieCutStampShape(wavelength = (3.6f * unit).dp, depth = (1.1f * unit).dp) }
    Box(
        modifier
            .size(width, (56f * unit).dp)
            .graphicsLayer {
                rotationZ = rotation
                scaleX = scale
                scaleY = scale
            }
            .clearAndSetSemantics {},
    ) {
        // The stamp's paths and words are made once for its size; only the postmark's fall is drawn again.
        Spacer(
            Modifier
                .matchParentSize()
                .dropShadow(shape, swiftUiShadow(Color.Black.copy(alpha = 0.16f), radius = (1.2f * unit).dp, y = (0.8f * unit).dp))
                .drawWithCache {
                    val u = unit.dp.toPx()
                    val edge = dieCutPath(Rect(Offset.Zero, size), 3.6f * u, 1.1f * u)
                    // Without a caption the globe sits in the middle of the print.
                    val globeCenter = Offset(22f * u, (if (origin == null) 28f else 25f) * u)
                    val lines = globeLines(Rect(globeCenter, 11f * u))
                    val originLine = origin?.let { measurer.measure(it, originStyle, softWrap = false, maxLines = 1) }
                    val dateLine = date?.let { measurer.measure(it, dateStyle, softWrap = false, maxLines = 1) }
                    onDrawBehind {
                        drawPath(edge, paper)
                        drawRect(surface, topLeft = Offset(4.5f * u, 4.5f * u), size = Size(size.width - 9f * u, size.height - 9f * u))
                        drawCircle(globe, radius = 11f * u, center = globeCenter)
                        drawPath(lines, ink, style = Stroke(0.7f * u))
                        drawCircle(ink, radius = 11f * u, center = globeCenter, style = Stroke(0.9f * u))
                        drawRect(
                            ink.copy(alpha = ink.alpha * 0.28f),
                            topLeft = Offset(6.2f * u, 6.2f * u),
                            size = Size(size.width - 12.4f * u, size.height - 12.4f * u),
                            style = Stroke(0.6f * u),
                        )
                        if (originLine != null) drawCentred(originLine, Offset(22f * u, 45f * u), tracking = 0.5f * u)
                        if (dateLine != null) drawPostmark(dateLine, ink, u, pressed.value)
                    }
                },
        )
    }
}

/**
 * The postmark: two rings and the delivery date, 23 across, turned 12° back and at 80 %, centred at (38, 36). On its
 * way down (`pressed` below 1) it is up to 2.6 times as large, turned 16° further and faded out.
 */
private fun DrawScope.drawPostmark(date: TextLayoutResult, ink: Color, u: Float, pressed: Float) {
    val away = 1 - pressed
    val opacity = 0.8f * pressed
    if (opacity <= 0f) return
    val center = Offset(38f * u, 36f * u)
    val faded = ink.copy(alpha = ink.alpha * opacity)
    scale(1 + 1.6f * away, pivot = center) {
        rotate(-12f + 16f * away, pivot = center) {
            drawCircle(faded, radius = 11.5f * u, center = center, style = Stroke(0.9f * u))
            drawCircle(faded, radius = 8.7f * u, center = center, style = Stroke(0.55f * u))
            drawCentred(date, center, alpha = opacity)
        }
    }
}

/**
 * One line centred on `center`, as `.position` centres a Text. CoreText adds tracking after each character, so the
 * iPhone's glyphs sit half a tracking earlier in the frame than Android's.
 */
private fun DrawScope.drawCentred(line: TextLayoutResult, center: Offset, tracking: Float = 0f, alpha: Float = 1f) {
    drawText(line, topLeft = Offset(center.x - line.size.width / 2f - tracking / 2, center.y - line.size.height / 2f), alpha = alpha)
}
