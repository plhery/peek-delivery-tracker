package com.plhery.peekdeliverytracker.ui.draw

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.gaussianBlurRadius
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * `Toggle` in its switch style as iOS 26 draws it: a capsule 63 × 28, the see-through grey of the tertiary label while
 * off and `tint` while on, its knob a white capsule 37 × 24, 2 in from its ends, with no shadow past the track.
 * Disabled, it shows at half strength and takes no touch. A tap on it turns it to the other side (`onChange`); so does
 * a drag of the knob across, the knob going to the side of the middle the finger is on and the switch turning there as
 * the finger lifts, a drag back undoing it. It plays no haptic, as SwiftUI's `Toggle` plays none of its own. TalkBack
 * reads it as a switch, on or off, and a row that merges it reads as one with its label.
 *
 * Under the finger the knob swells into a glass lens 59.3 × 39.7 about its centre, over the track and past it, 9 past
 * its end and 6 past its top and bottom, holding there while the finger is down and following a drag. As measured
 * frame by frame on the iPhone 17 Pro Max simulator: it grows on a critically damped spring of 0.1 s toward 12 % past
 * its size, turns back at 0.14 s and settles on the same spring; let go, the knob slides to its side on
 * `.spring(duration: 0.28, bounce: -0.1)`, the lens shrinks back to the white knob on `.spring(duration: 0.46,
 * bounce: 0.26)`, a little smaller than the knob before it settles, and the track's colour changes on
 * `.spring(duration: 0.73, bounce: 0.26)`. The lens is as white as it is short of its full size. Under Reduce Motion
 * it grows and shrinks without passing either size; its animations, as the slide's, are Compose's own, which jump
 * when animations are removed.
 *
 * The lens is a drawing ([drawLens]), not a refraction: a rim 0.6 wide at the top and bottom and 1.2 at the ends, 50
 * darker than the track where it crosses it and than `behind` elsewhere, 50 lighter than `behind` in dark, with a thin
 * line of the track inside it; within, `behind` and the track lighter, the track narrower about the lens's centre and
 * 9 in from its top and bottom, and where the track runs on past an end, the track across the lens's whole height
 * there, the pockets of `behind` ending in rounded tips; a soft shadow below, seen through the glass as round it, and
 * the glass darker under its top edge. Dark, measured over black, is the same lens but for its rim.
 */
@Composable
fun Toggle(
    on: Boolean,
    onChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = PeekTheme.colors.green,
    behind: Color = PeekTheme.colors.paper,
) {
    val colors = PeekTheme.colors
    val reduceMotion = PeekTheme.reduceMotion
    // The side the knob is dragged to, while it is, and whether the finger is down on the switch.
    var dragged by remember { mutableStateOf<Boolean?>(null) }
    var pressed by remember { mutableStateOf(false) }
    val target = if (dragged ?: on) 1f else 0f
    val progress by animateFloatAsState(target, Motion.spring(SLIDE_DURATION, SLIDE_BOUNCE), label = "toggle")
    val fill by animateFloatAsState(target, Motion.spring(FILL_DURATION, FILL_BOUNCE), label = "toggleFill")
    val lens = remember { Animatable(0f) }
    val held = pressed && enabled
    LaunchedEffect(held) {
        val grow = Motion.spring<Float>(GROW_DURATION)
        when {
            !held -> lens.animateTo(0f, Motion.spring(SHRINK_DURATION, if (reduceMotion) 0.0 else SHRINK_BOUNCE))
            reduceMotion -> lens.animateTo(1f, grow)
            else -> {
                withTimeoutOrNull(SWELL_HOLD) { lens.animateTo(SWELL, grow) }
                lens.animateTo(1f, grow)
            }
        }
    }
    val currentOn by rememberUpdatedState(on)
    val currentChange by rememberUpdatedState(onChange)
    val track = lerp(if (colors.dark) OFF_DARK else OFF_LIGHT, tint, fill.coerceIn(0f, 1f))
    Box(
        modifier
            .size(TRACK_WIDTH, TRACK_HEIGHT)
            .alpha(if (enabled) 1f else DISABLED_ALPHA)
            .toggleable(on, interactionSource = null, indication = null, enabled = enabled, role = Role.Switch, onValueChange = onChange)
            .pointerInput(enabled) {
                if (enabled) knobGestures(press = { pressed = it }, drag = { dragged = it }) { side -> if (side != currentOn) currentChange(side) }
            }
            .drawWithContent {
                drawContent()
                drawLens(progress, lens.value, track.compositeOver(behind), behind, colors.dark)
            }
            .clip(CapsuleShape)
            .background(track),
    )
}

/**
 * The knob `slide` of the way across, `grown` of the way from the white knob to the full lens (past 1 as it swells,
 * below 0 as it settles), over the track, opaque `track`, lying on `behind`, in the `dark` colours or not. At rest it
 * is the knob alone.
 */
private fun DrawScope.drawLens(slide: Float, grown: Float, track: Color, behind: Color, dark: Boolean) {
    val knob = Size(KNOB_WIDTH.roundToPx().toFloat(), KNOB_HEIGHT.roundToPx().toFloat())
    val start = (KNOB_INSET.roundToPx() + ((TRACK_WIDTH - KNOB_WIDTH - KNOB_INSET * 2) * slide).roundToPx()).toFloat()
    val left = if (layoutDirection == LayoutDirection.Ltr) start else size.width - start - knob.width
    val top = ((size.height - knob.height) / 2).roundToInt().toFloat()
    if (grown == 0f) {
        translate(left, top) { drawOutline(CapsuleShape.createOutline(knob, layoutDirection, this), Color.White) }
        return
    }
    val glass = grown.coerceIn(0f, 1f)
    val center = Offset(left + knob.width / 2, top + knob.height / 2)
    val width = knob.width + (LENS_WIDTH.toPx() - knob.width) * grown
    val height = knob.height + (LENS_HEIGHT.toPx() - knob.height) * grown
    val lens = Rect(center.x - width / 2, center.y - height / 2, center.x + width / 2, center.y + height / 2)
    val outline = capsule(lens)
    if (glass > 0f) {
        clipPath(outline) { drawThrough(lens, track, behind) }
        drawShadow(lens, outline, glass)
        drawRim(lens, outline, glass, track, behind, dark)
    }
    if (glass < 1f) drawPath(outline, Color.White.copy(alpha = 1 - glass))
}

/**
 * What the lens shows: `behind` and the track a little lighter, the track narrower about the lens's centre and 9 in
 * from its top and bottom, and where the track runs on past an end, its colour across the whole height there, the
 * pockets of `behind` above and below the track ending in rounded tips; just inside the rim, a thin line of the track
 * over its length. Its insets shrink with the lens.
 */
private fun DrawScope.drawThrough(lens: Rect, track: Color, behind: Color) {
    val scale = lens.height / LENS_HEIGHT.toPx()
    val bevel = BEVEL.toPx() * scale
    val edge = POCKET_EDGE.toPx() * scale
    val tip = (bevel - edge) / 2
    val half = lens.height / 2 - bevel
    val seen = track.lighter()
    drawRect(behind.lighter(), lens.topLeft, lens.size)
    val trackStart = lens.center.x * (1 - NARROWER)
    val trackEnd = lens.center.x + (size.width - lens.center.x) * NARROWER
    drawRoundRect(seen, Offset(trackStart, lens.center.y - half), Size(trackEnd - trackStart, half * 2), CornerRadius(half))
    val reach = FLARE_REACH.toPx()
    for ((side, inward) in listOf(lens.left to 1f, lens.right to -1f)) {
        val strength = ((if (inward > 0) lens.left else size.width - lens.right) / reach).coerceIn(0f, 1f)
        if (strength == 0f) continue
        val tips = side + (bevel + tip) * inward
        val pockets = Path().apply {
            addOval(Rect(Offset(tips, lens.top + edge + tip), tip))
            addOval(Rect(Offset(tips, lens.bottom - edge - tip), tip))
        }
        val across = Path().apply { addRect(Rect(minOf(side, tips), lens.top, maxOf(side, tips), lens.bottom)) }
        drawPath(Path().apply { op(across, pockets, PathOperation.Difference) }, seen, alpha = strength)
    }
    val line = EDGE_LINE.toPx()
    clipRect(trackStart, lens.top, trackEnd, lens.bottom) {
        drawPath(capsule(inside(lens).deflate(line / 2)), seen, style = Stroke(line))
    }
}

/**
 * The lens's shadow: a blurred ring inside its edge and below it, seen through the glass as round it, and inside, the
 * glass darker under its top edge.
 */
private fun DrawScope.drawShadow(lens: Rect, outline: Path, glass: Float) {
    val scale = lens.height / LENS_HEIGHT.toPx()
    val ring = capsule(lens.deflate(SHADOW_INSET.toPx() * scale).translate(0f, SHADOW_DROP.toPx() * scale))
    val stroke = blurred(SHADOW_BLUR, SHADOW_ALPHA * glass).apply {
        style = PaintingStyle.Stroke
        strokeWidth = SHADOW_WIDTH.toPx() * scale
    }
    drawIntoCanvas { it.drawPath(ring, stroke) }
    val around = lens.inflate(INNER_BLUR.toPx() * 4)
    val above = Path().apply {
        op(Path().apply { addRect(around) }, capsule(lens.translate(0f, INNER_DROP.toPx() * scale)), PathOperation.Difference)
    }
    clipPath(outline) { drawIntoCanvas { it.drawPath(above, blurred(INNER_BLUR, INNER_ALPHA * glass)) } }
}

/** Black at `alpha`, blurred as a Gaussian of `deviation`. */
private fun DrawScope.blurred(deviation: Dp, alpha: Float): Paint = Paint().apply {
    color = Color.Black.copy(alpha = alpha)
    asFrameworkPaint().maskFilter = BlurMaskFilter(gaussianBlurRadius(deviation).toPx(), BlurMaskFilter.Blur.NORMAL)
}

/**
 * The lens's rim, wider at its ends, darker than the track where it crosses it, and elsewhere darker than `behind`, or
 * lighter in `dark`.
 */
private fun DrawScope.drawRim(lens: Rect, outline: Path, glass: Float, track: Color, behind: Color, dark: Boolean) {
    val rim = Path().apply { op(outline, capsule(inside(lens)), PathOperation.Difference) }
    val under = capsule(Rect(Offset.Zero, size))
    clipPath(under) { drawPath(rim, track.shaded(), alpha = glass) }
    clipPath(under, ClipOp.Difference) { drawPath(rim, behind.shaded(lighter = dark), alpha = glass) }
}

/** The lens within its rim. */
private fun DrawScope.inside(lens: Rect): Rect = Rect(lens.left + RIM_END.toPx(), lens.top + RIM.toPx(), lens.right - RIM_END.toPx(), lens.bottom - RIM.toPx())

/** The colour as the glass shows it: lighter by [LIGHTER] of the way from its luma to white, in each channel alike. */
private fun Color.lighter(): Color {
    val add = LIGHTER * (1 - (LUMA_RED * red + LUMA_GREEN * green + LUMA_BLUE * blue))
    return Color((red + add).coerceAtMost(1f), (green + add).coerceAtMost(1f), (blue + add).coerceAtMost(1f))
}

/** The colour as the rim shows it: [RIM_SHADE] darker in each channel, or as much `lighter`. */
private fun Color.shaded(lighter: Boolean = false): Color {
    val by = if (lighter) RIM_SHADE else -RIM_SHADE
    return Color((red + by).coerceIn(0f, 1f), (green + by).coerceIn(0f, 1f), (blue + by).coerceIn(0f, 1f))
}

/** SwiftUI's `Capsule()` filling `rect`. */
private fun DrawScope.capsule(rect: Rect): Path = Path().apply {
    addOutline(CapsuleShape.createOutline(rect.size, layoutDirection, this@capsule))
    translate(rect.topLeft)
}

/**
 * The finger on the switch: [press] as it comes down and lifts. Once it has moved across further than a touch's slop,
 * before it moved as far up or down (a scroll, which takes it), it holds the knob: [drag] gives the side of the middle
 * it is on, and as it lifts [change] that side. A tap is the switch's own, its `toggleable`'s.
 */
private suspend fun PointerInputScope.knobGestures(press: (Boolean) -> Unit, drag: (Boolean?) -> Unit, change: (Boolean) -> Unit) {
    awaitEachGesture {
        val down = awaitFirstDown(requireUnconsumed = false)
        press(true)
        var travelled = Offset.Zero
        var dragging = false
        var side: Boolean? = null
        var lifted = false
        // Let go however the gesture ends, the switch disabled under the finger too.
        try {
            while (true) {
                val event = awaitPointerEvent()
                val finger = event.changes.firstOrNull { it.id == down.id } ?: break
                if (finger.changedToUpIgnoreConsumed()) {
                    lifted = true
                    if (dragging) finger.consume()
                    break
                }
                if (!dragging) {
                    if (finger.isConsumed) break
                    travelled += finger.positionChange()
                    val slop = viewConfiguration.touchSlop
                    if (abs(travelled.y) > slop && abs(travelled.y) >= abs(travelled.x)) break
                    dragging = abs(travelled.x) > slop
                }
                if (dragging) {
                    finger.consume()
                    side = finger.position.x > size.width / 2f
                    drag(side)
                }
            }
        } finally {
            press(false)
            drag(null)
        }
        if (lifted && side != null) change(side)
    }
}

private val TRACK_WIDTH = 63.dp
private val TRACK_HEIGHT = 28.dp
private val KNOB_WIDTH = 37.dp
private val KNOB_HEIGHT = 24.dp
private val KNOB_INSET = 2.dp
private const val DISABLED_ALPHA = 0.5f

/** The knob's slide and the track's change of colour as it turns. */
private const val SLIDE_DURATION = 0.28
private const val SLIDE_BOUNCE = -0.1
private const val FILL_DURATION = 0.73
private const val FILL_BOUNCE = 0.26

/** The lens under the finger, centred on the knob. */
private val LENS_WIDTH = 59.3.dp
private val LENS_HEIGHT = 39.7.dp

/**
 * Its growth, toward [SWELL] of its size until [SWELL_HOLD] ms, then back to its size on the same spring, and its
 * shrinking.
 */
private const val GROW_DURATION = 0.1
private const val SWELL = 1.12f
private const val SWELL_HOLD = 140L
private const val SHRINK_DURATION = 0.46
private const val SHRINK_BOUNCE = 0.26

/** Inside it: how far the track is in from its top and bottom, and how much narrower it is, about the lens's centre. */
private val BEVEL = 9.dp
private const val NARROWER = 0.884f

/** How far the pockets of `behind` above and below the track are in from the lens's edge. */
private val POCKET_EDGE = 2.dp

/** How far the track must run on past an end of the lens to show across its whole height there. */
private val FLARE_REACH = 8.dp

/** How much lighter the glass shows the track and `behind` ([lighter]), with sRGB's luma weights. */
private const val LIGHTER = 0.095f
private const val LUMA_RED = 0.299f
private const val LUMA_GREEN = 0.587f
private const val LUMA_BLUE = 0.114f

/**
 * The rim, at the top and bottom and at the ends, how much darker (or lighter) it is than what lies under it in each of
 * red, green and blue, and the track's line inside it.
 */
private val RIM = 0.6.dp
private val RIM_END = 1.2.dp
private const val RIM_SHADE = 50 / 255f
private val EDGE_LINE = 0.33.dp

/**
 * The shadow: a ring [SHADOW_WIDTH] wide inside the lens's edge, dropped, blurred as a Gaussian of [SHADOW_BLUR], black
 * at [SHADOW_ALPHA].
 */
private val SHADOW_INSET = 2.2.dp
private val SHADOW_DROP = 7.5.dp
private val SHADOW_WIDTH = 5.dp
private val SHADOW_BLUR = 2.8.dp
private const val SHADOW_ALPHA = 0.12f

/** The glass darker under its top edge: what lies round the lens, dropped, blurred, black at [INNER_ALPHA], in it. */
private val INNER_DROP = 5.5.dp
private val INNER_BLUR = 3.dp
private const val INNER_ALPHA = 0.05f

/**
 * The track while off: the tertiary label, (60, 60, 67) at 30 % in light and (235, 235, 245) at 30 % in dark, as
 * measured over white, black, grey and red on the simulator.
 */
private val OFF_LIGHT = Color(60, 60, 67).copy(alpha = 0.3f)
private val OFF_DARK = Color(235, 235, 245).copy(alpha = 0.3f)
