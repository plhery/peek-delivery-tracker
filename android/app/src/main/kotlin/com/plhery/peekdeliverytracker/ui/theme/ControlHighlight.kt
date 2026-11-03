package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.min

/**
 * What a control shows besides its press. While it has the focus from a hardware keyboard or a D-pad, never from a
 * touch, Full Keyboard Access's ring: the app's accent, which the iPad's focus takes from the app's tint, [RING_WIDTH]
 * wide, just outside [shape] ([ring]). Under a pointer, where [hover], the pointer's highlight over it in [shape], as
 * `.hoverEffect(.highlight)` lights a card up on the iPad: the system's quaternary fill. A [shape] of null is the control's bounds rounded as the app's controls are:
 * a circle where they are square (the bars' round buttons), else a capsule, at most [MAX_CORNER] at the corners.
 *
 * Every press style draws them ([PressStyle], RowHighlight); this is the indication of a control with no press of its
 * own, and the theme's ([Default]), so that every clickable shows where the keyboard is.
 */
@Immutable
internal data class ControlHighlight(val shape: Shape? = null, val ring: Boolean = true, val hover: Boolean = false) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode = ControlHighlightNode(interactionSource, ControlMarks(shape, ring, hover))

    companion object {
        /** The ring, round the control's bounds rounded as the app's controls are. */
        val Default = ControlHighlight()
    }
}

private class ControlHighlightNode(private val interactionSource: InteractionSource, private val marks: ControlMarks) :
    Modifier.Node(), DrawModifierNode, CompositionLocalConsumerModifierNode {
    override fun onAttach() {
        coroutineScope.launch { interactionSource.interactions.collect(marks::take) }
    }

    override fun onDetach() = marks.reset()

    override fun ContentDrawScope.draw() {
        drawContent()
        drawMarks(marks, this@ControlHighlightNode)
    }
}

/** The focus and the pointer a control has heard of from its interactions, and how it shows them ([ControlHighlight]). */
internal class ControlMarks(private val shape: Shape?, private val ring: Boolean, private val hover: Boolean) {
    private val focuses = mutableListOf<FocusInteraction.Focus>()
    private val hovers = mutableListOf<HoverInteraction.Enter>()
    var focused by mutableStateOf(false)
        private set
    var hovered by mutableStateOf(false)
        private set

    fun take(interaction: Interaction) {
        when (interaction) {
            is FocusInteraction.Focus -> focuses += interaction
            is FocusInteraction.Unfocus -> focuses -= interaction.focus
            is HoverInteraction.Enter -> hovers += interaction
            is HoverInteraction.Exit -> hovers -= interaction.enter
            else -> return
        }
        focused = ring && focuses.isNotEmpty()
        hovered = hover && hovers.isNotEmpty()
    }

    /** What it heard goes with its collector, and an exit or an unfocus meanwhile is never told. */
    fun reset() {
        focuses.clear()
        hovers.clear()
        focused = false
        hovered = false
    }

    fun DrawScope.drawHover(dark: Boolean) {
        drawOutline(outline(shape, size, 0f), if (dark) HOVER_DARK else HOVER_LIGHT)
    }

    fun DrawScope.drawRing(accent: Color) {
        val width = RING_WIDTH.toPx()
        val half = width / 2
        translate(-half, -half) {
            drawOutline(outline(shape, Size(size.width + width, size.height + width), half), accent, style = Stroke(width))
        }
    }

    /** [shape] at `size`, or the bounds rounded; a ring's line `grown` out from the shape keeps its curve. */
    private fun DrawScope.outline(shape: Shape?, size: Size, grown: Float) = when (shape) {
        null -> {
            val half = min(size.width, size.height) / 2
            RoundedCornerShape(if (abs(size.width - size.height) < 1) half else min(half, MAX_CORNER.toPx() + grown))
        }
        is ContinuousRoundedShape -> ContinuousRoundedShape(shape.cornerRadius + grown.toDp())
        CapsuleShape -> RoundedCornerShape(50)
        else -> shape
    }.createOutline(size, layoutDirection, this)
}

/** [marks] over what is drawn: the pointer's highlight, then the ring while the keyboard has the focus here. */
internal fun DrawScope.drawMarks(marks: ControlMarks, node: CompositionLocalConsumerModifierNode) {
    val colors = node.currentValueOf(LocalPeekColors)
    if (marks.hovered) with(marks) { drawHover(colors.dark) }
    if (marks.focused && node.currentValueOf(LocalInputModeManager).inputMode == InputMode.Keyboard) {
        with(marks) { drawRing(colors.accent) }
    }
}

/** Full Keyboard Access's ring, about 3 points wide. */
private val RING_WIDTH = 3.dp

/** The most a shapeless control's corners round. */
private val MAX_CORNER = 16.dp

/** The system's quaternary fill: (116, 116, 128) at 8 % light, (118, 118, 128) at 18 % dark. */
private val HOVER_LIGHT = Color(116, 116, 128).copy(alpha = 0.08f)
private val HOVER_DARK = Color(118, 118, 128).copy(alpha = 0.18f)
