package com.plhery.peekdeliverytracker.ui.sheet

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.MapPath
import com.plhery.peekdeliverytracker.core.Rect as MapRect
import com.plhery.peekdeliverytracker.ui.deliveries.OverlayLayer
import com.plhery.peekdeliverytracker.ui.draw.toComposePath
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/** How large a popover would be and how small it may be, `.frame(minWidth:idealWidth:minHeight:idealHeight:)`. */
@Immutable
data class PopoverSize(val minWidth: Dp, val idealWidth: Dp, val minHeight: Dp, val idealHeight: Dp)

/**
 * `.popover(isPresented:)` as iPadOS 26 draws it, over the whole window ([LocalTabOverlay]): on glass, its arrow pointing
 * at [anchor] (the source's bounds in the window), under it where there is room and over it otherwise, centred on it
 * as far as the window allows. It takes its ideal size, down to its least where the window is short of room. Nothing
 * dims; a tap outside it, or the system's back, dismisses it. It grows from its arrow on a spring and shrinks back as
 * it goes. TalkBack reaches only the popover, named [paneTitle]; [onClosed] runs once it has gone.
 */
@Composable
fun Popover(
    visible: Boolean,
    anchor: Rect,
    onDismiss: () -> Unit,
    paneTitle: String,
    size: PopoverSize,
    onClosed: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    var present by remember { mutableStateOf(visible) }
    if (visible) present = true
    val host = LocalTabOverlay.current
    val dismiss by rememberUpdatedState(onDismiss)
    val closed by rememberUpdatedState(onClosed)
    val layer: (@Composable () -> Unit)? = if (!present) null else {
        {
            PopoverLayer(visible, anchor, { dismiss() }, paneTitle, size, host?.backdrop, {
                present = false
                closed()
            }, content)
        }
    }
    if (host == null) layer?.invoke() else OverlayLayer(host, PopoverKey(paneTitle), layer)
}

private data class PopoverKey(val title: String)

@Composable
private fun PopoverLayer(
    visible: Boolean,
    anchor: Rect,
    onDismiss: () -> Unit,
    paneTitle: String,
    size: PopoverSize,
    backdrop: MaterialBackdrop?,
    onGone: () -> Unit,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val reduceMotion = PeekTheme.reduceMotion
    val shown = remember { Animatable(0f) }
    val menus = remember { SheetMenus() }
    var origin by remember { mutableStateOf(Offset.Zero) }
    LaunchedEffect(visible) {
        if (visible) {
            shown.animateTo(1f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.35, 0.15))
        } else {
            shown.animateTo(0f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.25))
            onGone()
        }
    }
    BackHandler(enabled = visible) { onDismiss() }
    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned { origin = it.positionInWindow() }
            .pointerInput(Unit) { detectTapGestures { if (visible) onDismiss() } },
    ) {
        val bars = WindowInsets.systemBars.union(WindowInsets.displayCutout)
        val frame = with(density) {
            popoverFrame(
                anchor.translate(-origin), size, constraints.maxWidth.toFloat(), constraints.maxHeight.toFloat(),
                bars.getTop(this).toFloat(), bars.getBottom(this).toFloat(),
            )
        }
        val shape = with(density) { remember(frame) { PopoverShape(frame, ARROW_HEIGHT.toPx(), ARROW_WIDTH.toPx(), POPOVER_RADIUS.toPx()) } }
        Box(
            Modifier
                .layout { measurable, constraints ->
                    val bounds = frame.bounds
                    val placeable = measurable.measure(Constraints.fixed(bounds.width.roundToInt(), bounds.height.roundToInt()))
                    layout(constraints.maxWidth, constraints.maxHeight) { placeable.place(bounds.left.roundToInt(), bounds.top.roundToInt()) }
                }
                .graphicsLayer {
                    val t = shown.value
                    alpha = t.coerceIn(0f, 1f)
                    val scale = if (reduceMotion) 1f else 0.5f + 0.5f * t
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin(frame.tip, if (frame.below) 0f else 1f)
                }
                .dropShadow(shape, POPOVER_SHADOW)
                .pointerInput(Unit) { detectTapGestures { } }
                .semantics { this.paneTitle = paneTitle },
        ) {
            SheetGlass(shape, backdrop, Modifier.matchParentSize())
            CompositionLocalProvider(LocalSheetAttachment provides { 0f }, LocalSheetMenus provides menus, LocalSheetBarTop provides POPOVER_BAR_TOP) {
                Box(Modifier.padding(top = if (frame.below) ARROW_HEIGHT else 0.dp, bottom = if (frame.below) 0.dp else ARROW_HEIGHT)) {
                    content()
                }
            }
        }
        menus.content?.invoke()
    }
}

/** Where a popover stands: its bounds, its arrow included, whether it is under its source, and its arrow's tip across it. */
@Immutable
private data class PopoverFrame(val bounds: Rect, val below: Boolean, val tip: Float)

/**
 * The popover's frame in a window `width` × `height`, clear of its bars (`top`, `bottom`): under its source while
 * there is room for its ideal height there, or wherever has more; as wide and tall as it would be, down to its least.
 */
private fun Density.popoverFrame(anchor: Rect, size: PopoverSize, width: Float, height: Float, top: Float, bottom: Float): PopoverFrame {
    val margin = POPOVER_MARGIN.toPx()
    val arrow = ARROW_HEIGHT.toPx()
    val roomBelow = height - bottom - margin - anchor.bottom - arrow
    val roomAbove = anchor.top - top - margin - arrow
    val below = roomBelow >= size.idealHeight.toPx() || roomBelow >= roomAbove
    val room = if (below) roomBelow else roomAbove
    val w = min(size.idealWidth.toPx(), width - 2 * margin).coerceAtLeast(min(size.minWidth.toPx(), width))
    val h = min(size.idealHeight.toPx(), room).coerceAtLeast(min(size.minHeight.toPx(), height - top - bottom - arrow)) + arrow
    val left = (anchor.center.x - w / 2).coerceIn(margin, max(margin, width - margin - w))
    val y = if (below) anchor.bottom else anchor.top - h
    val bounds = Rect(left, y, left + w, y + h)
    return PopoverFrame(bounds, below, ((anchor.center.x - left) / w).coerceIn(0f, 1f))
}

/** The popover's body with continuous corners and its arrow, pointing at the source from the side towards it. */
private class PopoverShape(private val frame: PopoverFrame, private val arrow: Float, private val arrowWidth: Float, private val radius: Float) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val below = frame.below
        val bodyTop = if (below) arrow else 0f
        val bodyHeight = size.height - arrow
        val body = MapPath.roundedRect(MapRect(0.0, bodyTop.toDouble(), size.width.toDouble(), bodyHeight.toDouble()), radius.toDouble()).toComposePath()
        val half = arrowWidth / 2
        val x = (size.width * frame.tip).coerceIn(radius + half, max(radius + half, size.width - radius - half))
        // The arrow's sides curve into the body and round off at its tip, as UIKit's do.
        val base = if (below) arrow + 1f else size.height - arrow - 1f
        val point = if (below) 0f else size.height
        val tri = Path().apply {
            moveTo(x - half, base)
            cubicTo(x - half * 0.45f, base, x - half * 0.3f, point, x, point)
            cubicTo(x + half * 0.3f, point, x + half * 0.45f, base, x + half, base)
            close()
        }
        return Outline.Generic(Path.combine(PathOperation.Union, body, tri))
    }
}

private val POPOVER_MARGIN = 16.dp
private val POPOVER_RADIUS = 30.dp
private val ARROW_HEIGHT = 13.dp
private val ARROW_WIDTH = 34.dp
private val POPOVER_BAR_TOP = 10.dp
private val POPOVER_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.14f), radius = 30.dp, y = 10.dp)
