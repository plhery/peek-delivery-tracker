package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import com.plhery.peekdeliverytracker.ui.draw.DieCutStampShape
import java.text.BreakIterator
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.currentStateAsState
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.MapDrawingContext
import com.plhery.peekdeliverytracker.core.MapPath
import com.plhery.peekdeliverytracker.core.PipArtwork
import com.plhery.peekdeliverytracker.core.PipGeometry
import com.plhery.peekdeliverytracker.core.PipLabel
import com.plhery.peekdeliverytracker.core.PipOpening
import com.plhery.peekdeliverytracker.core.PipPalette
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.core.StrokeStyle
import com.plhery.peekdeliverytracker.ui.draw.KraftPipFace
import com.plhery.peekdeliverytracker.ui.draw.PipCanvas
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin

/**
 * `UnwrappingParcel(open:pressed:celebrating:label:ribbon:grounded:)` in Pip's 300 × 310 frame, `open` of the way from
 * closed (0) to open (1, and a little past while a spring gives): the box behind its sides with the rear flaps folding
 * away from 0.06; the card rising out of it from 0.24 (its check drawn from 0.48) behind a warm glow that breathes as
 * the box opens; the sides with the carrier's label or the arrow and the seal; Pip's face, its eyes turning into happy
 * arcs from 0.2 (a gift's smile showing with them, out from under its ribbon); the front flaps swinging up from 0.2
 * through upright and out over the face, a little too far, then settling; the tape tearing out of sight and a gift's
 * ribbon coming off by a quarter of the way. The whole box dips just before it opens and lifts and swells as it does,
 * but not under Reduce Motion. `celebrating` throws the stars ([ParcelStarBurst]). At rest it is still but for the
 * blink of a closed box's eyes. It draws its own box where Pip's ([PipArtwork]) differs: the rear right flap folds open
 * to (175, 41), the sides are shaded across the frame without hairlines, and the seam runs from (102.5, 118.5).
 *
 * `grounded`, it stands on a soft shadow, an ellipse 180 × 20 at (151, 286) in black at 8 %, blurred 9; off, as the
 * share sheet's sticker is cut to its box, it has none. `pressed` is how far a press has gone, 0 to 1, as SwiftUI's
 * animation carries `pressed` from one look to the other: the box squashes to 0.956 toward the ground and sinks 5, and
 * its shadow widens to 188, flattens to 13 and darkens to 14 %. A friend's invitation names its `sender` on the card
 * (`senderName`). The welcome's greeting `lift`s the closed box (negative is up) and `sway`s it by degrees about its
 * middle, less as it opens; its shadow narrows by 3 and lightens by 0.4 % a point of lift.
 */
@Composable
internal fun UnwrappingParcel(
    open: Double,
    label: PipLabel?,
    ribbon: Boolean,
    celebrating: Boolean = false,
    pressed: Float = 0f,
    grounded: Boolean = true,
    sender: String? = null,
    lift: Float = 0f,
    sway: Float = 0f,
) {
    val frame = Modifier.size(FRAME_WIDTH.dp, FRAME_HEIGHT.dp)
    val palette = PipPalette.kraft
    val reduceMotion = PeekTheme.reduceMotion
    fun phase(start: Double, duration: Double) = min(1.0, max(0.0, (open - start) / duration))
    val rear = phase(0.06, 0.66)
    val card = phase(0.24, 0.76)
    val happy = phase(0.2, 0.3)
    val tear = phase(0.0, 0.3)
    // sin(π) is a hair above zero: an open box is as still as a closed one.
    val breath = if (reduceMotion || open == 1.0) 0.0 else sin(open * PI)
    val anticipation = if (reduceMotion) 0.0 else max(0.0, 1 - abs(open - 0.12) / 0.12)
    val wrapping = max(0.0, 1 - open * 4)
    Box(frame) {
        if (grounded) {
            val shadowWidth = 180f + lift * 3 + (188f - 180f - lift * 3) * pressed
            val shadowHeight = 20f - 7f * pressed
            Box(
                Modifier
                    .offset((151f - shadowWidth / 2).dp, (286f - shadowHeight / 2).dp)
                    .size(shadowWidth.dp, shadowHeight.dp)
                    .dropShadow(OvalShape, swiftUiShadow(Color.Black.copy(alpha = (0.08f + lift * 0.004f).coerceAtLeast(0f) * (1 - pressed) + 0.14f * pressed), radius = 9.dp)),
            )
        }
        Box(
            frame.graphicsLayer {
                val swell = (1 - anticipation * 0.032 + breath * 0.025).toFloat()
                val scale = swell + (0.956f - swell) * pressed
                scaleX = scale
                scaleY = scale
                transformOrigin = TransformOrigin(0.5f, 1f)
                translationY = (lift * (1 - open).toFloat() + (anticipation * 4 - breath * 8).toFloat() * (1 - pressed) + 5f * pressed).dp.toPx()
            }.graphicsLayer { rotationZ = sway * (1 - open).toFloat() },
        ) {
            PipCanvas(frame) {
                it.fill(PipGeometry.polygon(PipGeometry.inside), palette.inside)
                // The rear pair folds away before the front panels reveal the box.
                unwrappingFlap(it, between(PipGeometry.backLeft.closed, PipGeometry.backLeft.opened, rear), palette.backLeft, min(1.0, open))
                unwrappingFlap(it, between(PipGeometry.backRight.closed, BACK_RIGHT_OPENED, rear), palette.backRight)
            }
            val glow = breath * 0.48
            if (glow > 0) {
                Box(
                    Modifier
                        .offset((150f - 124f / 2).dp, (140f - 40f / 2).dp)
                        .size(124.dp, 40.dp)
                        .dropShadow(OvalShape, swiftUiShadow(Color(0xFFFFE8AE).copy(alpha = glow.toFloat()), radius = 10.dp)),
                )
            }
            if (card > 0) DeliveryCard(ribbon, card, check = phase(0.48, 0.3), sender)
            UnwrappingSides(frame)
            if (label != null) PipCanvas(frame) { PipArtwork.label(it, label) }
            if (label == null) ParcelSideMarks()
            // A ribbon runs where Pip smiles: until the box opens, the eyes say it all.
            KraftPipFace(happy = happy, smile = if (ribbon) happy else 1.0)
            PipCanvas(frame) {
                // The front flaps swing up through upright and out over the face, go a little too far, and settle just
                // above level: the left one is a brim over the eyes.
                PipArtwork.frontFlaps(it, palette, PipOpening.open.copy(front = PipGeometry.settle(phase(0.2, 0.8)), under = min(1.0, open)))
                // The tape tears out of sight as the flaps open.
                if (wrapping > 0) it.copy().also { tape -> tape.opacity *= wrapping }.fill(PipGeometry.polygon(tornTape(tear)), palette.tape)
                val seam = max(0.0, 0.6 - open * 3)
                if (seam > 0) {
                    val path = MapPath().apply {
                        move(102.5, 118.5)
                        addLine(197.5, 166.0)
                    }
                    it.stroke(path, palette.seam.opacity(seam), StrokeStyle(lineWidth = 1.0, dash = listOf(3.0, 3.0)))
                }
                if (ribbon && wrapping >= 1) PipArtwork.ribbon(it)
            }
            // The ribbon comes off as the flaps open, faded as one piece.
            if (ribbon && wrapping > 0 && wrapping < 1) PipCanvas(frame.graphicsLayer { alpha = wrapping.toFloat() }) { PipArtwork.ribbon(it) }
            ParcelStarBurst(celebrating)
        }
    }
}

/** The parcel closed or open, at rest. */
@Composable
internal fun UnwrappingParcel(open: Boolean, label: PipLabel?, ribbon: Boolean, grounded: Boolean = true) =
    UnwrappingParcel(if (open) 1.0 else 0.0, label, ribbon, grounded = grounded)

/** One of the box's flaps at `points`, in `color` with its hairline edge, at `opacity`. */
private fun unwrappingFlap(context: MapDrawingContext, points: List<Point>, color: AdaptiveColor, opacity: Double = 1.0) {
    if (!(opacity > 0)) return
    val layer = context.copy()
    layer.opacity *= opacity
    val path = PipGeometry.polygon(points)
    layer.fill(path, color)
    layer.stroke(path, PipPalette.kraft.hairline.opacity(0.24), lineWidth = 0.7)
}

/** A flap `progress` of the way from `closed` to `opened`, corner by corner. */
private fun between(closed: List<Point>, opened: List<Point>, progress: Double): List<Point> = when (progress) {
    0.0 -> closed
    1.0 -> opened
    else -> closed.zip(opened) { from, to -> Point(from.x + (to.x - from.x) * progress, from.y + (to.y - from.y) * progress) }
}

/**
 * The tape `progress` of the way off: lifted 6 left and 19 up, then turned 8° back about the middle of Pip's frame, as
 * SwiftUI turns the offset shape.
 */
private fun tornTape(progress: Double): List<Point> {
    if (progress == 0.0) return PipGeometry.tape
    val angle = -8 * progress * PI / 180
    val (cx, cy) = FRAME_WIDTH / 2.0 to FRAME_HEIGHT / 2.0
    return PipGeometry.tape.map {
        val x = it.x - 6 * progress - cx
        val y = it.y - 19 * progress - cy
        Point(cx + x * cos(angle) - y * sin(angle), cy + x * sin(angle) + y * cos(angle))
    }
}

/**
 * `ParcelStarBurst`: the stars on their own clock, so they linger while the parcel moves on. `active` throws them over
 * 1.85 s at an even pace; turned off, the window gone from the front or Reduce Motion on, they are gone, and none come
 * under Reduce Motion.
 */
@Composable
private fun ParcelStarBurst(active: Boolean) {
    val reduceMotion = PeekTheme.reduceMotion
    val resumed = LocalLifecycleOwner.current.lifecycle.currentStateAsState().value.isAtLeast(Lifecycle.State.RESUMED)
    val progress = remember { Animatable(0f) }
    val throws by rememberUpdatedState(!reduceMotion && resumed)
    LaunchedEffect(active) {
        progress.snapTo(0f)
        if (active && throws) progress.animateTo(1f, tween(STAR_BURST_MILLIS, easing = LinearEasing))
    }
    LaunchedEffect(reduceMotion, resumed) {
        if (reduceMotion || !resumed) progress.snapTo(0f)
    }
    if (!reduceMotion) ParcelStars(progress.value.toDouble())
}

/**
 * `ParcelStars`: six sparkles, in gold and one in lilac, `progress` of the way through their burst. Each sets off a
 * little after the last of its three, flies out from the box's middle easing to where Pip's glints sit and rises 12,
 * grows in and swells a little, turns from -18° to 18°, and fades in fast and out over its last third.
 */
@Composable
private fun ParcelStars(progress: Double) {
    PipGeometry.glints.forEachIndexed { index, star ->
        val delay = 0.14 + (index % 3) * 0.03
        val p = min(1.0, max(0.0, (progress - delay) / (1 - delay)))
        val travel = 1 - (1 - p).pow(3)
        val opacity = min(1.0, p / 0.16) * min(1.0, (1 - p) / 0.32)
        key(index) {
            if (opacity > 0) {
                val grow = (0.3 + 0.7 * min(1.0, p / 0.2)) * (1 + 0.16 * sin(p * PI))
                val x = 150 + (star.center.x - 150) * (0.45 + 0.55 * travel)
                val y = 140 + (star.center.y - 140) * (0.3 + 0.7 * travel) - p * 12
                Decoration(x.toFloat(), y.toFloat()) {
                    PeekSymbol(
                        "sparkle",
                        Modifier.graphicsLayer {
                            scaleX = grow.toFloat()
                            scaleY = grow.toFloat()
                            rotationZ = (-18 + 36 * travel).toFloat()
                            alpha = opacity.toFloat()
                        },
                        font = PeekTheme.type.fixed(star.size.toFloat(), FontWeight.Medium),
                        tint = STAR_COLORS[index],
                    )
                }
            }
        }
    }
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
 * The small card rising out of the box, `rise` of the way up, 83 × 111 on cream with 7-point corners and a white edge at
 * 60 %: centred at (152, 174) of Pip's frame and turned -11° as it sets off, at (152, 105) and -5° once risen, coming in
 * over its first third. 14 in: a gift's symbol in lilac on its pale circle over two grey lines, centred at its top, for a
 * gift; else a green check in its pale circle, drawn `check` of the way, over two lines, lined up at their leading side,
 * the three in the card's middle. From a friend ([sender]), on lilac in place of cream, lined up at their leading side
 * in plum: the sender's initial in medium 13 on a die-cut stamp 21 × 26 at the trailing side, "friends.from" in 8, the
 * name in semibold 17 on one line, made as small as 45 % to fit, and a hairline 29 wide under it, 8 apart.
 */
@Composable
private fun DeliveryCard(ribbon: Boolean, rise: Double, check: Double, sender: String? = null) {
    val shape = RoundedCornerShape(7.dp)
    Box(
        Modifier
            .offset { IntOffset((152f - 83f / 2).dp.roundToPx(), (174f - 69f * rise.toFloat() - 111f / 2).dp.roundToPx()) }
            .size(83.dp, 111.dp)
            .graphicsLayer {
                rotationZ = (-11 + rise * 6).toFloat()
                alpha = min(1.0, rise * 3).toFloat()
            }
            .background(if (sender == null) Color(0xFFFCFAF4) else Color(0xFFE7DCF4), shape)
            .drawWithContent {
                drawContent()
                // On the card's edge, half of it outside, as SwiftUI strokes a shape.
                drawRoundRect(Color.White.copy(alpha = 0.6f), cornerRadius = CornerRadius(7.dp.toPx()), style = Stroke(0.8.dp.toPx()))
            }
            .padding(14.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (sender != null) {
            SenderCard(sender)
        } else if (ribbon) {
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
                    val mark = Path().apply {
                        moveTo(10f * unit, 19f * unit)
                        lineTo(16f * unit, 25f * unit)
                        lineTo(28f * unit, 12f * unit)
                    }
                    val drawn = if (check >= 1) {
                        mark
                    } else {
                        Path().also { PathMeasure().apply { setPath(mark, false) }.let { measure -> measure.getSegment(0f, measure.length * check.toFloat(), it) } }
                    }
                    if (check > 0) drawPath(drawn, Color(0xFF587260), style = Stroke(2.5f * unit, cap = StrokeCap.Round, join = StrokeJoin.Round))
                }
                Box(Modifier.size(45.dp, 4.dp).background(Color(0xFFDAD7CE), CircleShape))
                Box(Modifier.size(29.dp, 4.dp).background(Color(0xFFE7E4DC), CircleShape))
            }
        }
    }
}

/** The card's words from a friend ([DeliveryCard]), in plum, `.minimumScaleFactor(0.45)` on the name. */
@Composable
private fun SenderCard(sender: String) {
    val plum = Color(0xFF69517F)
    val initial = sender.firstCharacter()
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Box(Modifier.size(21.dp, 26.dp).background(Color(0xFFD1BEDF), DieCutStampShape()), contentAlignment = Alignment.Center) {
                Text(initial, color = plum, style = PeekTheme.type.fixed(13f, FontWeight.Medium), maxLines = 1)
            }
        }
        Text(localized("friends.from"), color = plum, style = PeekTheme.type.fixed(8f), maxLines = 1)
        ShrinkingLine(sender, PeekTheme.type.fixed(17f, FontWeight.SemiBold).copy(color = plum), MIN_SENDER_SCALE)
        Box(Modifier.size(29.dp, 1.dp).background(Color(0xFFB39BC7), CircleShape))
    }
}

/** One line of [text] in [style], made smaller down to [minimum] of its size where the room is too little for it. */
@Composable
private fun ShrinkingLine(text: String, style: TextStyle, minimum: Float) {
    val measurer = rememberTextMeasurer()
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val room = constraints.maxWidth
        val width = remember(text, style, room) { measurer.measure(text, style, maxLines = 1, softWrap = false).size.width }
        val scale = if (width <= room || width == 0) 1f else max(minimum, room.toFloat() / width)
        Text(text, style = style.copy(fontSize = style.fontSize * scale), maxLines = 1, softWrap = false, overflow = TextOverflow.Ellipsis)
    }
}

private const val MIN_SENDER_SCALE = 0.45f

/** `String(name.prefix(1))`: the first character as the reader sees it, an emoji whole; nothing for no name. */
internal fun String.firstCharacter(): String {
    if (isEmpty()) return ""
    val characters = BreakIterator.getCharacterInstance()
    characters.setText(this)
    return substring(0, characters.next())
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

/** How long the stars take to burst, from the first setting off to the last gone. */
internal const val STAR_BURST_MILLIS = 1_850

/** The stars' colours, one by one: gold, a lighter gold, and one lilac. */
private val STAR_COLORS = listOf(0xFFC99B35, 0xFFD6AE48, 0xFFC99B35, 0xFFB594BE, 0xFFD6AE48, 0xFFC99B35).map { Color(it) }
