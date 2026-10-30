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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
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
 * at [anchor] (the source's bounds in the window), under it where there is room and over it otherwise, or over it first
 * where [above] (iOS 26's confirmation dialog off a list's row), centred on it as far as the window allows, its corners
 * [radius] round. It takes its ideal size, down to its least where the window is short of room, and stays
 * in the window's safe area, what it holds scrolling where even its least would not fit. Nothing dims; a tap outside
 * it, or the system's back, dismisses it. It grows from its arrow on a spring and shrinks back as it goes; the window
 * made again (a rotation) finds it there without growing again. TalkBack reaches only the popover, named [paneTitle],
 * and only its menu while one is open ([FormMenuRow]); [onClosed] runs once it has gone.
 */
@Composable
fun Popover(
    visible: Boolean,
    anchor: Rect,
    onDismiss: () -> Unit,
    paneTitle: String,
    size: PopoverSize,
    onClosed: () -> Unit = {},
    above: Boolean = false,
    radius: Dp = POPOVER_RADIUS,
    content: @Composable () -> Unit,
) {
    var present by remember { mutableStateOf(visible) }
    if (visible) present = true
    val memory = rememberSaveable(saver = SheetMemory.Saver) { SheetMemory() }
    if (!present) memory.forget()
    val host = LocalTabOverlay.current
    val dismiss by rememberUpdatedState(onDismiss)
    val closed by rememberUpdatedState(onClosed)
    val layer: (@Composable () -> Unit)? = if (!present) null else {
        {
            PopoverLayer(visible, anchor, { dismiss() }, paneTitle, size, PopoverLook(above, radius), host?.backdrop, memory, {
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
    look: PopoverLook,
    backdrop: MaterialBackdrop?,
    memory: SheetMemory,
    onGone: () -> Unit,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val direction = LocalLayoutDirection.current
    val reduceMotion = PeekTheme.reduceMotion
    // Made again with the window, it is there already.
    val shown = remember { Animatable(if (memory.up) 1f else 0f) }
    val menus = remember { SheetMenus() }
    var origin by remember { mutableStateOf(Offset.Zero) }
    val showing by rememberUpdatedState(visible)
    val dismiss by rememberUpdatedState(onDismiss)
    LaunchedEffect(visible) {
        if (visible) {
            shown.animateTo(1f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.35, 0.15))
            memory.up = true
        } else {
            memory.forget()
            shown.animateTo(0f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.25))
            onGone()
        }
    }
    BackHandler(enabled = visible) { onDismiss() }
    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned { origin = it.positionInWindow() }
            .pointerInput(Unit) { detectTapGestures { if (showing) dismiss() } },
    ) {
        val bars = WindowInsets.systemBars.union(WindowInsets.displayCutout)
        val frame = with(density) {
            popoverFrame(
                anchor.translate(-origin), size, look.above, constraints.maxWidth.toFloat(), constraints.maxHeight.toFloat(),
                SafeInsets(bars.getLeft(this, direction).toFloat(), bars.getTop(this).toFloat(), bars.getRight(this, direction).toFloat(), bars.getBottom(this).toFloat()),
            )
        }
        val shape = with(density) { remember(frame, look) { PopoverShape(frame, ARROW_HEIGHT.toPx(), ARROW_WIDTH.toPx(), look.radius.toPx()) } }
        Box(
            Modifier
                .layout { measurable, constraints ->
                    val bounds = frame.bounds
                    val placeable = measurable.measure(Constraints.fixed(bounds.width.roundToInt(), bounds.height.roundToInt()))
                    layout(constraints.maxWidth, constraints.maxHeight) { placeable.place(bounds.left.roundToInt(), bounds.top.roundToInt()) }
                }
                .graphicsLayer {
                    val t = shown.value
                    // Made again with the window, it shows once its source has been laid out again.
                    alpha = if (anchor == Rect.Zero) 0f else t.coerceIn(0f, 1f)
                    val scale = if (reduceMotion) 1f else 0.5f + 0.5f * t
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin(frame.tip, if (frame.below) 0f else 1f)
                }
                .dropShadow(shape, POPOVER_SHADOW)
                .clip(shape)
                .pointerInput(Unit) { detectTapGestures { } }
                .semantics { this.paneTitle = paneTitle }
                // While a menu is open over it, TalkBack reaches only the menu.
                .then(if (menus.content != null) Modifier.clearAndSetSemantics {} else Modifier),
        ) {
            SheetGlass(shape, backdrop, Modifier.matchParentSize())
            val insets = with(density) { SheetInsets.popover(frame.bounds.width.toDp()) }
            val ground: @Composable (Modifier) -> Unit = { SheetGlass(RectangleShape, backdrop, it) }
            CompositionLocalProvider(
                LocalSheetAttachment provides { 0f }, LocalSheetMenus provides menus, LocalSheetInsets provides insets, LocalSheetGround provides ground,
            ) {
                Box(Modifier.padding(top = if (frame.below) ARROW_HEIGHT else 0.dp, bottom = if (frame.below) 0.dp else ARROW_HEIGHT)) {
                    content()
                }
            }
        }
        menus.content?.invoke()
    }
}

/** Which side of its source a popover takes first, and its corners. */
@Immutable
private data class PopoverLook(val above: Boolean, val radius: Dp)

/** Where a popover stands: its bounds, its arrow included, whether it is under its source, and its arrow's tip across it. */
@Immutable
private data class PopoverFrame(val bounds: Rect, val below: Boolean, val tip: Float)

/** The window's bars' and cutout's insets on each side, in pixels. */
private data class SafeInsets(val left: Float, val top: Float, val right: Float, val bottom: Float)

/**
 * The popover's frame in a window `width` × `height`, in by a margin from its safe area ([safe]): under its source
 * while there is room for its ideal height there, or over it first where `above`, or wherever has more; as wide and tall as it would be, down to its
 * least, and no larger than the room it has. Its body stays in the window however close to its edge the source is.
 */
private fun Density.popoverFrame(anchor: Rect, size: PopoverSize, above: Boolean, width: Float, height: Float, safe: SafeInsets): PopoverFrame {
    val margin = POPOVER_MARGIN.toPx()
    val arrow = ARROW_HEIGHT.toPx()
    val start = safe.left + margin
    val end = width - safe.right - margin
    val top = safe.top + margin
    val bottom = height - safe.bottom - margin
    val roomBelow = max(0f, bottom - anchor.bottom - arrow)
    val roomAbove = max(0f, anchor.top - top - arrow)
    val ideal = size.idealHeight.toPx()
    val below = if (above) roomAbove < ideal && roomBelow > roomAbove else roomBelow >= ideal || roomBelow >= roomAbove
    val room = if (below) roomBelow else roomAbove
    val across = max(0f, end - start)
    val w = min(size.idealWidth.toPx(), across).coerceAtLeast(min(size.minWidth.toPx(), across))
    val h = min(size.idealHeight.toPx(), room).coerceAtLeast(min(size.minHeight.toPx(), room)) + arrow
    val left = (anchor.center.x - w / 2).coerceIn(start, max(start, end - w))
    // The arrow over or under the body: the body between the safe area's top and bottom.
    val y = if (below) {
        anchor.bottom.coerceIn(top - arrow, max(top - arrow, bottom - h))
    } else {
        (anchor.top - h).coerceIn(top, max(top, bottom - h + arrow))
    }
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
private val POPOVER_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.14f), radius = 30.dp, y = 10.dp)
