package com.plhery.peekdeliverytracker.ui.passport

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.PassportData
import com.plhery.peekdeliverytracker.core.StampDefinition
import com.plhery.peekdeliverytracker.core.StampTone
import com.plhery.peekdeliverytracker.ui.draw.DieCutStampShape
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlin.math.min

// The passport's paper seals as the web's `PassportSeal` draws them (shared/passport.json): the stamp's glyph or its
// numeral in a die-cut frame of its tone, the postmark over an earned one's lower corner, and a new one pressed in, then
// postmarked.

/** A tone's ink, light and dark. */
internal val StampTone.ink: AdaptiveColor
    get() = when (this) {
        StampTone.Green -> ExperimentalPalette.delivered
        StampTone.Blue -> ExperimentalPalette.transit
        StampTone.Peach -> ExperimentalPalette.pickup
        StampTone.Lilac -> ExperimentalPalette.lilac
        StampTone.Ochre -> ExperimentalPalette.ochre
        StampTone.Rose -> ExperimentalPalette.rose
    }

/** A tone's paper, light and dark. */
internal val StampTone.paper: AdaptiveColor
    get() = when (this) {
        StampTone.Green -> ExperimentalPalette.deliveredSurface
        StampTone.Blue -> ExperimentalPalette.transitSurface
        StampTone.Peach -> ExperimentalPalette.pickupSurface
        StampTone.Lilac -> ExperimentalPalette.lilacSurface
        StampTone.Ochre -> ExperimentalPalette.ochreSurface
        StampTone.Rose -> ExperimentalPalette.roseSurface
    }

/** A glyph of passport.json's, in its 24×24 box, parsed once. */
private val glyphs = mutableMapOf<String, Path>()

internal fun stampGlyph(name: String?): Path = synchronized(glyphs) {
    val key = name ?: "parcel"
    glyphs.getOrPut(key) { PathParser().parsePathString(PassportData.glyph(key)).toPath() }
}

/**
 * A seal's size: its paper, the frame's inset, the glyph's box and line (in the glyph's 24 units), the numeral's type,
 * and the postmark's width and how far it stands past the paper's trailing and bottom edges (none on a mini seal).
 */
internal enum class SealSize(
    val width: Dp,
    val height: Dp,
    val inset: Dp,
    val glyph: Dp,
    val line: Float,
    val numeral: Float,
    val numeralWeight: FontWeight,
    val ring: Dp,
    val ringPastEnd: Dp,
    val ringPastBottom: Dp,
) {
    Regular(54.dp, 67.dp, 6.dp, 24.dp, 1.1f, 20f, FontWeight.Light, 27.dp, 11.dp, 3.dp),
    Cover(66.dp, 80.dp, 6.dp, 24.dp, 1.1f, 20f, FontWeight.Light, 33.dp, 9.dp, (-3).dp),
    Mini(24.dp, 30.dp, 3.dp, 14.dp, 1.4f, 10f, FontWeight.Medium, 0.dp, 0.dp, 0.dp),
}

/** FABLE's curves: the drop of a seal, its settle, and a move. Cubic-béziers only, no springs. */
internal val PassportDrop: Easing = CubicBezierEasing(0.5f, 0f, 0.9f, 0.4f)
internal val PassportSettle: Easing = CubicBezierEasing(0.2f, 0.8f, 0.3f, 1f)
internal val PassportMove: Easing = CubicBezierEasing(0.3f, 0f, 0.2f, 1f)

/** How far a part of a motion has come at [time] ms, starting at [start] and lasting [duration], eased. */
internal fun phase(time: Float, start: Float, duration: Float, easing: Easing = LinearEasing): Float =
    easing.transform(((time - start) / duration).coerceIn(0f, 1f))

/**
 * A seal pressed in among the new ones (`press` its place in page order): 180 ms, then 90 more for each before it, at
 * most three apart. [time] is the ms since the passport opened; past [END] it rests. Under Reduce Motion it is there.
 */
@Stable
internal class SealPress(val press: Int?, private val clock: Animatable<Float, *>?) {
    val delay: Float get() = if (press == null) 0f else 180f + min(press, 2) * 90f
    val time: Float get() = clock?.value?.minus(delay) ?: REST

    companion object {
        /** The press (480), the ring after it (276 + 320), and the New pill (416 + 200), all done. */
        const val END = 620f
        const val REST = 10_000f
    }
}

/**
 * The clock a new seal is pressed in by, and its New pill popped: none for a seal that isn't new, nor under Reduce
 * Motion. The first seal pressed plays one light impact as it lands (216 ms into its press).
 */
@Composable
internal fun rememberSealPress(press: Int?): SealPress {
    val reduceMotion = PeekTheme.reduceMotion
    val clock = remember(press, reduceMotion) { if (press == null || reduceMotion) null else Animatable(0f) }
    val seal = remember(press, clock) { SealPress(press, clock) }
    var landed by remember { mutableIntStateOf(0) }
    SensoryFeedbackEffect(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light, 0.65), landed)
    if (clock != null) {
        LaunchedEffect(clock) {
            val end = seal.delay + SealPress.END
            if (press == 0) {
                clock.animateTo(seal.delay + IMPACT, tween((seal.delay + IMPACT).toInt(), easing = LinearEasing))
                landed++
                clock.animateTo(end, tween((end - seal.delay - IMPACT).toInt(), easing = LinearEasing))
            } else {
                clock.animateTo(end, tween(end.toInt(), easing = LinearEasing))
            }
        }
    }
    return seal
}

/** The moment the drop ends and the seal meets the page. */
private const val IMPACT = 216f

/** A stamp of passport.json's in its tone: earned, else a third as opaque and grey. */
@Composable
internal fun StampSeal(definition: StampDefinition, earned: Boolean, modifier: Modifier = Modifier, size: SealSize = SealSize.Regular, press: SealPress? = null) {
    val colors = PeekTheme.colors
    StampSeal(definition.glyph, definition.numeral, colors.resolve(definition.tone.ink), colors.resolve(definition.tone.paper), earned, modifier, size, press)
}

/**
 * `PassportSeal`: on its die-cut [paper], a frame inset in its [ink] at 40 %, the [glyph] (stroke 1.1 of its 24, round
 * caps and joins) or the [numeral], and, earned, the postmark's two rings at a third over its lower trailing corner. A
 * [press] drops it in (scale 1.5 and −8° to 0.94, then 1, the ink in over 100 ms), then draws the outer ring round
 * from its start and inks the inner one. Hidden from TalkBack: its button says it.
 */
@Composable
internal fun StampSeal(
    glyph: String?,
    numeral: String?,
    ink: Color,
    paper: Color,
    earned: Boolean,
    modifier: Modifier = Modifier,
    size: SealSize = SealSize.Regular,
    press: SealPress? = null,
) {
    val type = PeekTheme.type
    val tone = if (earned) ink else ink.grey()
    val ground = if (earned) paper else paper.grey()
    Box(
        modifier
            .size(size.width, size.height)
            .clearAndSetSemantics {}
            .graphicsLayer {
                if (!earned) alpha = UNEARNED_OPACITY
                val time = press?.time ?: SealPress.REST
                if (time < SealPress.REST) {
                    val drop = phase(time, 0f, IMPACT, PassportDrop)
                    val settle = phase(time, IMPACT, 480f - IMPACT, PassportSettle)
                    val scale = if (time < IMPACT) 1.5f + (0.94f - 1.5f) * drop else 0.94f + 0.06f * settle
                    scaleX = scale
                    scaleY = scale
                    rotationZ = if (time < IMPACT) -8f * (1 - drop) else 0f
                    alpha = phase(time, 0f, 100f)
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Box(Modifier.fillMaxSize().background(ground, DieCutStampShape()))
        Canvas(Modifier.fillMaxSize().padding(size.inset)) {
            val line = 1.dp.toPx()
            drawRect(tone.copy(alpha = 0.4f * tone.alpha), Offset(line / 2, line / 2), Size(this.size.width - line, this.size.height - line), style = Stroke(line))
        }
        if (numeral != null) {
            Text(numeral, color = tone, style = type.fixed(size.numeral, size.numeralWeight).monospacedDigit().sfLines())
        } else {
            val path = remember(glyph) { stampGlyph(glyph) }
            Canvas(Modifier.size(size.glyph)) {
                val unit = this.size.width / 24f
                scale(unit, unit, pivot = Offset.Zero) {
                    drawPath(path, tone, style = Stroke(size.line, cap = StrokeCap.Round, join = StrokeJoin.Round))
                }
            }
        }
        if (earned && size != SealSize.Mini) {
            Postmark(
                tone, press,
                Modifier.align(Alignment.BottomEnd).offset(x = size.ringPastEnd, y = size.ringPastBottom).size(size.ring),
            )
        }
    }
}

/**
 * The postmark: two rings in a 32 box (radius 15 and 10.5, line 1.05), the whole at a third. Pressed, the outer one is
 * drawn round from −150° over 320 ms once the seal has landed (276 ms in), and the inner one inks in over 160 ms after.
 */
@Composable
private fun Postmark(ink: Color, press: SealPress?, modifier: Modifier) {
    Canvas(modifier) {
        val time = press?.time ?: SealPress.REST
        val outer = phase(time, 276f, 320f, PassportMove)
        val inner = phase(time, 436f, 160f)
        val unit = size.width / 32f
        val color = ink.copy(alpha = ink.alpha * POSTMARK_OPACITY)
        val stroke = Stroke(1.05f * unit)
        if (outer > 0f) {
            drawArc(color, -150f, 360f * outer, useCenter = false, topLeft = Offset(unit, unit), size = Size(30 * unit, 30 * unit), style = stroke)
        }
        if (inner > 0f) drawCircle(color.copy(alpha = color.alpha * inner), radius = 10.5f * unit, center = Offset(16 * unit, 16 * unit), style = stroke)
    }
}

/** `filter: grayscale(1)`: the colour's luminance as grey, by the weights of Android's ColorMatrix.setSaturation. */
internal fun Color.grey(): Color {
    val luminance = 0.213f * red + 0.715f * green + 0.072f * blue
    return Color(luminance, luminance, luminance, alpha)
}

private const val UNEARNED_OPACITY = 0.3f
private const val POSTMARK_OPACITY = 0.34f
