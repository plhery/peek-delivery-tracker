package com.plhery.peekdeliverytracker.ui.theme

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.MotionCurve
import com.plhery.peekdeliverytracker.core.Spring
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * How a button of the iPhone app gives under the finger (A-AN38), its SwiftUI `ButtonStyle`: pressed, it scales to
 * `scale` about its centre, moves `offsetY` down, shows at `opacity` and darkens by `brightness`, moving there and
 * back with `animation`. Under Reduce Motion it neither scales nor moves, and what remains changes at once.
 *
 * A style is Compose's `Indication`, so a button takes it in place of the ripple, which the iPhone has none of:
 * `Modifier.clickable(onClick = …, interactionSource = null, indication = PressStyle.Tactile)`, placed before what it
 * presses (`background`, `clip`), as `.buttonStyle` takes the whole label. A press told some other way (a card whose
 * gesture is its own) uses `Modifier.pressEffect(style, pressed)`.
 */
@Immutable
data class PressStyle(
    val scale: Float = 1f,
    val offsetY: Dp = 0.dp,
    val opacity: Float = 1f,
    /** SwiftUI's `.brightness(_:)`: added to each of red, green and blue as the colour is written, -1 to 1. */
    val brightness: Float = 0f,
    val animation: MotionCurve,
) : IndicationNodeFactory {
    /** How a press `progress` of the way in (0 at rest, 1 pressed, past 1 while a spring overshoots) shows. */
    fun appearance(progress: Double, reduceMotion: Boolean): PressAppearance {
        val amount = progress.toFloat()
        return PressAppearance(
            scale = if (reduceMotion) 1f else 1 + (scale - 1) * amount,
            offsetY = if (reduceMotion) 0.dp else offsetY * amount,
            // SwiftUI keeps an opacity within 0…1, even while a spring overshoots.
            opacity = (1 + (opacity - 1) * amount).coerceIn(0f, 1f),
            brightness = brightness * amount,
        )
    }

    override fun create(interactionSource: InteractionSource): DelegatableNode = PressEffectNode(this, interactionSource, false)

    companion object {
        /** `TactileButtonStyle(scale:)`, most of the app's buttons: 0.985 unless the button asks for its own give. */
        fun tactile(scale: Float = 0.985f): PressStyle = PressStyle(scale = scale, opacity = 0.94f, animation = MotionCurve.EaseOut(0.16))

        /** `TactileButtonStyle()`. */
        val Tactile: PressStyle = tactile()

        /** `ExperimentalLiftButtonStyle`: the archive's rows, and the deliveries' cards through `pressEffect`. */
        val ExperimentalLift: PressStyle = PressStyle(
            scale = 0.975f,
            offsetY = 1.5.dp,
            brightness = -0.025f,
            // `.snappy(duration: 0.22)`: bounce 0.15.
            animation = MotionCurve.SpringCurve(Spring(duration = 0.22, bounce = 0.15)),
        )

        /** `PassportPressStyle`: the passport's stamps and cards, on `.spring(response: 0.3, dampingFraction: 0.75)`. */
        val Passport: PressStyle = PressStyle(
            scale = 0.975f,
            opacity = 0.94f,
            animation = MotionCurve.SpringCurve(Spring(duration = 0.3, bounce = 0.25)),
        )

        /**
         * `FirstOpenHeroStyle`: the first open's picture darkens a little. The iPhone's other two styles,
         * `PipPressStyle` and `ParcelOpeningButtonStyle`, only tell the drawing it is pressed: there a button takes
         * `indication = null` and reads `interactionSource.collectIsPressedAsState()`.
         */
        val FirstOpenHero: PressStyle = PressStyle(brightness = -0.02f, animation = MotionCurve.EaseOut(0.18))
    }
}

/** A press as drawn: the content scaled about its centre, moved down, faded and darkened. */
data class PressAppearance(val scale: Float, val offsetY: Dp, val opacity: Float, val brightness: Float)

/** `style` as `pressed` says, from a gesture the content keeps itself (the deliveries' cards, A-AN38). */
fun Modifier.pressEffect(style: PressStyle, pressed: Boolean): Modifier = this then PressEffectElement(style, pressed)

private data class PressEffectElement(val style: PressStyle, val pressed: Boolean) : ModifierNodeElement<PressEffectNode>() {
    override fun create(): PressEffectNode = PressEffectNode(style, null, pressed)

    override fun update(node: PressEffectNode) {
        node.style = style
        node.press(pressed)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "pressEffect"
        properties["style"] = style
        properties["pressed"] = pressed
    }
}

private class PressEffectNode(
    var style: PressStyle,
    private val interactionSource: InteractionSource?,
    pressed: Boolean,
) : Modifier.Node(), DrawModifierNode, CompositionLocalConsumerModifierNode {
    private val progress = AnimatedValue(if (pressed) 1.0 else 0.0)

    /** The press as of the last frame. */
    private var shown = progress.target

    /** A change of press waiting for the next frame, where its animation starts. */
    private var pending: Double? = null
    private var clock: Job? = null
    private val paint = Paint()

    override fun onAttach() {
        // A press that came before the node was attached, or one cut off when it was last detached.
        if (pending != null || shown != progress.target) clock = coroutineScope.launch { tick() }
        val source = interactionSource ?: return
        coroutineScope.launch {
            val presses = mutableListOf<PressInteraction.Press>()
            source.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> presses += interaction
                    is PressInteraction.Release -> presses -= interaction.press
                    is PressInteraction.Cancel -> presses -= interaction.press
                }
                press(presses.isNotEmpty())
            }
        }
    }

    override fun onDetach() {
        clock = null
    }

    fun press(pressed: Boolean) {
        val target = if (pressed) 1.0 else 0.0
        if (target == (pending ?: progress.target)) return
        pending = target
        if (!isAttached) return
        if (clock?.isActive != true) clock = coroutineScope.launch { tick() }
    }

    /** Runs the press frame by frame until it rests. */
    private suspend fun tick() {
        while (true) {
            val now = withFrameNanos { it } / 1e9
            pending?.let { target ->
                pending = null
                progress.animate(target, if (currentValueOf(LocalReduceMotion)) null else style.animation, now)
            }
            shown = progress.value(now)
            invalidateDraw()
            if (pending == null && !progress.isRunning(now)) {
                shown = progress.target
                invalidateDraw()
                return
            }
        }
    }

    override fun ContentDrawScope.draw() {
        val look = style.appearance(shown, currentValueOf(LocalReduceMotion))
        val offset = look.offsetY.toPx()
        val layer = look.opacity < 1f || look.brightness != 0f
        if (!layer && look.scale == 1f && offset == 0f) {
            drawContent()
            return
        }
        val canvas = drawContext.canvas.nativeCanvas
        val saved = if (layer) canvas.saveLayer(null, paint.of(look)) else canvas.save()
        withTransform({
            translate(top = offset)
            scale(look.scale, look.scale, center)
        }) {
            this@draw.drawContent()
        }
        canvas.restoreToCount(saved)
    }

    /** The layer's paint: its opacity, and the brightness added to each channel before the colour is premultiplied. */
    private fun Paint.of(look: PressAppearance): Paint {
        alpha = (look.opacity * 255).roundToInt()
        colorFilter = if (look.brightness == 0f) {
            null
        } else {
            val add = look.brightness * 255
            ColorMatrixColorFilter(ColorMatrix(floatArrayOf(1f, 0f, 0f, 0f, add, 0f, 1f, 0f, 0f, add, 0f, 0f, 1f, 0f, add, 0f, 0f, 0f, 1f, 0f)))
        }
        return this
    }
}
