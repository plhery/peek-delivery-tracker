package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.MapDrawingContext
import com.plhery.peekdeliverytracker.core.MapPath
import com.plhery.peekdeliverytracker.core.PipArtwork
import com.plhery.peekdeliverytracker.core.PipGeometry
import com.plhery.peekdeliverytracker.core.PipLabel
import com.plhery.peekdeliverytracker.core.PipPalette
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.core.StrokeStyle
import com.plhery.peekdeliverytracker.ui.draw.KraftPipFace
import com.plhery.peekdeliverytracker.ui.draw.PipCanvas
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme

/**
 * `UnwrappingParcel` at rest, closed or open, in Pip's 300 × 310 frame, still but for the blink of a closed box's eyes:
 * the box behind its sides, the card risen out of an open one, the sides with the carrier's label or the arrow and
 * the seal, Pip's face (happy arcs once open; no smile under a gift's ribbon), the front flaps, and on a closed box the
 * tape and a gift's ribbon. It draws its own box where Pip's ([PipArtwork]) differs: the rear right flap folds open to
 * (175, 41), the sides are shaded across the frame without hairlines, and the seam runs from (102.5, 118.5).
 */
@Composable
internal fun UnwrappingParcel(open: Boolean, label: PipLabel?, ribbon: Boolean) {
    val frame = Modifier.size(FRAME_WIDTH.dp, FRAME_HEIGHT.dp)
    val palette = PipPalette.kraft
    Box(frame) {
        PipCanvas(frame) {
            it.fill(PipGeometry.polygon(PipGeometry.inside), palette.inside)
            if (open) unwrappingFlap(it, PipGeometry.backLeft.opened, palette.backLeft)
            unwrappingFlap(it, if (open) BACK_RIGHT_OPENED else PipGeometry.backRight.closed, palette.backRight)
        }
        if (open) DeliveryCard(ribbon)
        UnwrappingSides(frame)
        if (label != null) PipCanvas(frame) { PipArtwork.label(it, label) }
        if (label == null) ParcelSideMarks()
        KraftPipFace(happy = if (open) 1.0 else 0.0, smile = if (ribbon && !open) 0.0 else 1.0)
        PipCanvas(frame) {
            PipArtwork.frontFlaps(it, palette, open)
            if (!open) {
                it.fill(PipGeometry.polygon(PipGeometry.tape), palette.tape)
                val seam = MapPath().apply {
                    move(102.5, 118.5)
                    addLine(197.5, 166.0)
                }
                it.stroke(seam, palette.seam.opacity(0.6), StrokeStyle(lineWidth = 1.0, dash = listOf(3.0, 3.0)))
                if (ribbon) PipArtwork.ribbon(it)
            }
        }
    }
}

/** One of the box's flaps at `points`, in `color` with its hairline edge. */
private fun unwrappingFlap(context: MapDrawingContext, points: List<Point>, color: AdaptiveColor) {
    val path = PipGeometry.polygon(points)
    context.fill(path, color)
    context.stroke(path, PipPalette.kraft.hairline.opacity(0.24), lineWidth = 0.7)
}

/**
 * The box's two sides as `UnwrappingParcel` shades them, each from its lighter kraft at the frame's top leading corner
 * to its darker at the bottom trailing one, the left side lit a little, and their light edges, without hairlines.
 */
@Composable
private fun UnwrappingSides(modifier: Modifier) {
    Canvas(modifier) {
        scale(density, density, pivot = Offset.Zero) {
            val corner = Offset(FRAME_WIDTH, FRAME_HEIGHT)
            val left = framePath(PipGeometry.leftSide)
            drawPath(left, Brush.linearGradient(listOf(Color(0xFFDDBC95), Color(0xFFC49A6E)), Offset.Zero, corner))
            drawPath(framePath(PipGeometry.rightSide), Brush.linearGradient(listOf(Color(0xFFBB946A), Color(0xFFA77E55)), Offset.Zero, corner))
            drawPath(left, Color(0xFFFFF4D6).copy(alpha = 0.07f))
            val edges = Path().apply {
                moveTo(55f, 142f)
                lineTo(150f, 190f)
                lineTo(245f, 142f)
                moveTo(150f, 190f)
                lineTo(150f, 277f)
            }
            drawPath(edges, Color(0xFFFFF2CF).copy(alpha = 0.4f), style = Stroke(1f, miter = 10f))
        }
    }
}

/** A closed shape through `points`, in the frame's units. */
private fun framePath(points: List<Point>) = Path().apply {
    points.forEachIndexed { index, point ->
        if (index == 0) moveTo(point.x.toFloat(), point.y.toFloat()) else lineTo(point.x.toFloat(), point.y.toFloat())
    }
    close()
}

/**
 * The small card risen out of the open box, 83 × 111 on cream with 7-point corners and a white edge at 60 %, centred at
 * (152, 105) of Pip's frame and turned -5°, 14 in: a gift's symbol in lilac on its pale circle over two grey lines,
 * centred at its top, for a gift; else a green check in its pale circle over two lines, lined up at their leading
 * side, the three in the card's middle.
 */
@Composable
private fun DeliveryCard(ribbon: Boolean) {
    val shape = RoundedCornerShape(7.dp)
    Box(
        Modifier
            .offset((152f - 83f / 2).dp, (105f - 111f / 2).dp)
            .size(83.dp, 111.dp)
            .rotate(-5f)
            .background(Color(0xFFFCFAF4), shape)
            .drawWithContent {
                drawContent()
                // On the card's edge, half of it outside, as SwiftUI strokes a shape.
                drawRoundRect(Color.White.copy(alpha = 0.6f), cornerRadius = CornerRadius(7.dp.toPx()), style = Stroke(0.8.dp.toPx()))
            }
            .padding(14.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (ribbon) {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(9.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(Modifier.size(24.dp).background(Color(0xFFEFE3F1), CircleShape), contentAlignment = Alignment.Center) {
                    PeekSymbol("gift", font = PeekTheme.type.fixed(12f, FontWeight.Medium), tint = Color(0xFF7C6787))
                }
                Box(Modifier.size(47.dp, 4.dp).background(Color(0xFFDAD7CE), CircleShape))
                Box(Modifier.size(29.dp, 4.dp).background(Color(0xFFE7E4DC), CircleShape))
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Canvas(Modifier.size(38.dp).background(Color(0xFFE7ECE4), CircleShape)) {
                    val unit = size.width / 38f
                    val check = Path().apply {
                        moveTo(10f * unit, 19f * unit)
                        lineTo(16f * unit, 25f * unit)
                        lineTo(28f * unit, 12f * unit)
                    }
                    drawPath(check, Color(0xFF587260), style = Stroke(2.5f * unit, cap = StrokeCap.Round, join = StrokeJoin.Round))
                }
                Box(Modifier.size(45.dp, 4.dp).background(Color(0xFFDAD7CE), CircleShape))
                Box(Modifier.size(29.dp, 4.dp).background(Color(0xFFE7E4DC), CircleShape))
            }
        }
    }
}

/**
 * The arrow and the seal on the parcel's right side, where it carries no carrier's label (`UnwrappingParcel`): an
 * arrow.up of 18 medium in brown and an asterisk of 14 bold in lilac on its lilac circle, 28 across, both turned -27°.
 * Drawn in Pip's 300 × 310 frame.
 */
@Composable
private fun ParcelSideMarks() {
    Decoration(218f, 214f) {
        PeekSymbol(
            "arrow.up",
            Modifier.rotate(-27f),
            font = PeekTheme.type.fixed(18f, FontWeight.Medium),
            tint = Color(0xFF735C43),
        )
    }
    Decoration(183f, 234f) {
        Box(
            Modifier
                .rotate(-27f)
                .size(28.dp)
                .background(Color(0xFFDECCE2), CircleShape)
                .drawBehind {
                    val ring = 1.2.dp.toPx()
                    drawCircle(
                        Color(0xFFFFF6FF).copy(alpha = 0.48f),
                        radius = size.minDimension / 2 - 2.5.dp.toPx(),
                        style = Stroke(ring),
                    )
                },
            contentAlignment = Alignment.Center,
        ) {
            PeekSymbol("asterisk", font = PeekTheme.type.fixed(14f, FontWeight.Bold), tint = Color(0xFF7C6787))
        }
    }
}

/** Something on the parcel's side, centred at (`x`, `y`) of Pip's frame. */
@Composable
private fun Decoration(x: Float, y: Float, content: @Composable () -> Unit) {
    Box(Modifier.offset(x.dp, y.dp).size(0.dp).wrapContentSize(unbounded = true).clearAndSetSemantics {}) { content() }
}

/** Pip's frame. */
private val FRAME_WIDTH = PipGeometry.frame.width.toFloat()
private val FRAME_HEIGHT = PipGeometry.frame.height.toFloat()

/** `UnwrappingParcel`'s rear right flap folded open, its far corner at (175, 41) where Pip's is at (174.24, 41.32). */
private val BACK_RIGHT_OPENED = listOf(Point(150.0, 95.0), Point(245.0, 142.0), Point(270.0, 88.0), Point(175.0, 41.0))
