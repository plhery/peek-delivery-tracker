package com.plhery.peekdeliverytracker.ui.sheet

import android.view.RoundedCorner
import androidx.activity.compose.PredictiveBackHandler
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animate
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.plhery.peekdeliverytracker.ui.deliveries.OverlayLayer
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.UnevenContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.GlassFace
import com.plhery.peekdeliverytracker.ui.theme.LargeGlassSurface
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** A height a sheet rests at (`PresentationDetent`). */
@Immutable
sealed interface SheetDetent {
    /** `.medium`: about half the window, floating clear of its edges. */
    data object Medium : SheetDetent

    /** `.large`: the window under the status bar, attached to its sides and bottom. */
    data object Large : SheetDetent

    /** `.height(_:)`: this tall, floating as the medium one does. */
    data class Height(val height: Dp) : SheetDetent

    /** `fittedSheet`'s `.height(h)`: as tall as what the sheet holds needs. */
    data object Fitted : SheetDetent
}

/**
 * How far a sheet is attached to the window's edges and opaque, 0 floating on glass to 1 at the large detent, read as
 * it is drawn: its form's rows take the ground it stands on ([FormSection]).
 */
internal val LocalSheetAttachment = staticCompositionLocalOf<() -> Float> { { 1f } }

/**
 * What a sheet or a popover stands on, its glass or its opaque ground, drawn again in `Modifier`'s bounds under what
 * blurs it: the bar's confirmation stands on the sheet, and takes it on through its glass ([SheetPage]). Null outside one.
 */
internal val LocalSheetGround = staticCompositionLocalOf<(@Composable (Modifier) -> Unit)?> { null }

/** Where a menu opened in a sheet or a popover is drawn, over all of it ([MenuRow]); null outside one. */
internal val LocalSheetMenus = staticCompositionLocalOf<SheetMenus?> { null }

/** The menu open over a sheet or a popover, which the layer draws last. */
@Stable
internal class SheetMenus {
    var content: (@Composable () -> Unit)? by mutableStateOf(null)
}

/**
 * `.sheet(isPresented:)` as iOS 26 draws it, over the whole window, its tab bar and the system's bars too ([LocalTabOverlay]).
 *
 * - Where the window is compact (narrower than 600 or lower than 480, as a phone is), it comes up from the bottom to the
 *   first of its [detents]. At the medium detent, and at a height of its own, it floats on glass inset 8 from the
 *   window's sides and bottom, its bottom corners concentric with the display's own, what it holds laid out as at the
 *   window's width and made smaller to the panel's; at the large one it stands under
 *   the status bar, attached and opaque, on the grouped ground. Dragged by its grabber, its bar or what it scrolls once
 *   at its top, it follows the finger between them, morphing from one to the other, and settles where the release and
 *   its speed would carry it, as UIKit projects a flick; dragged down past its lowest detent, it goes. A scroll in it
 *   first takes it to the large detent.
 * - Where the window is regular, it is a form sheet in the window's middle, 540 wide and 620 tall at most, opaque, which
 *   a drag down sends away too.
 *
 * The window under it dims, black at 20 % (48 % in dark), and a tap there dismisses it, as the system's back and its
 * predictive gesture do, the sheet following the gesture down. It comes and goes on a critically damped spring, and
 * moves between its detents on a snappy one. TalkBack reaches only the sheet, named [paneTitle]. Once it has gone,
 * [onClosed] runs, where TalkBack's focus goes back to what opened it.
 */
@Composable
fun Sheet(
    visible: Boolean,
    onDismiss: () -> Unit,
    paneTitle: String,
    modifier: Modifier = Modifier,
    detents: List<SheetDetent> = listOf(SheetDetent.Medium, SheetDetent.Large),
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
            SheetLayer(visible, { dismiss() }, paneTitle, detents, host?.backdrop, modifier, {
                present = false
                closed()
            }, content)
        }
    }
    if (host == null) layer?.invoke() else OverlayLayer(host, SheetKey(paneTitle), layer)
}

private data class SheetKey(val title: String)

/** Where the sheet's top stands and how it moves there: the finger's drag, a settle, its coming and going. */
@Stable
private class SheetMotion(hidden: Float) {
    var top by mutableFloatStateOf(hidden)
    var rests: List<Float> = emptyList()
    var hidden: Float = hidden
    var moved = false
    var shown = false
    var job: Job? = null

    /** The speed a drag let go of the sheet at as it dismissed it, which its way out keeps. */
    var releaseVelocity = 0f
    private var raw = hidden

    /** The finger moved the sheet by `delta` pixels, past its highest rest less and less, as UIKit's rubber band. */
    fun dragBy(delta: Float) {
        job?.cancel()
        if (!moved) raw = top
        moved = true
        raw += delta
        val highest = rests.minOrNull() ?: return
        top = if (raw >= highest) raw else highest - band(highest - raw)
    }

    private fun band(past: Float): Float = (1 - 1 / (past * 0.55f / BAND_LENGTH + 1)) * BAND_LENGTH

    /** Where a release at `velocity` pixels a second would carry the sheet: UIScrollView's normal deceleration. */
    fun projected(velocity: Float): Float = top + velocity / 1000f * DECELERATION / (1 - DECELERATION)

    /** The sheet's rest nearest where the release would carry it, or null where it would go. */
    fun target(velocity: Float, dismissible: Boolean): Float? {
        val projected = projected(velocity)
        val lowest = rests.maxOrNull() ?: return null
        val nearest = rests.minBy { abs(it - projected) }
        if (dismissible && abs(hidden - projected) < abs(lowest - projected) && projected > lowest) return null
        return nearest
    }

    companion object {
        const val DECELERATION = 0.998f
        const val BAND_LENGTH = 400f
    }
}

@Composable
private fun SheetLayer(
    visible: Boolean,
    onDismiss: () -> Unit,
    paneTitle: String,
    detents: List<SheetDetent>,
    backdrop: MaterialBackdrop?,
    modifier: Modifier,
    onGone: () -> Unit,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val colors = PeekTheme.colors
    val scope = rememberCoroutineScope()
    val view = LocalView.current
    val menus = remember { SheetMenus() }
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()
        val regular = maxWidth >= REGULAR_WIDTH && maxHeight >= REGULAR_HEIGHT
        val insets = SheetInsets.sheet(maxWidth)
        val motion = remember { SheetMotion(height) }
        motion.hidden = height
        var backProgress by remember { mutableFloatStateOf(0f) }
        var fitted by remember { mutableFloatStateOf(0f) }

        val bars = WindowInsets.systemBars.union(WindowInsets.displayCutout)
        val safeTop = bars.getTop(density).toFloat()
        val inset = with(density) { FLOAT_INSET.toPx() }
        val largeTop = max(safeTop, with(density) { LARGE_MIN_TOP.toPx() })
        val ime = WindowInsets.ime.getBottom(density).toFloat()
        val formWidth = with(density) { min(FORM_WIDTH.toPx(), width - 2 * FORM_MARGIN.toPx()) }
        val formHeight = with(density) { min(FORM_HEIGHT.toPx(), height - safeTop - bars.getBottom(this) - 2 * FORM_MARGIN.toPx()) }
        val rests = if (regular) {
            listOf(max(safeTop, (height - formHeight) / 2))
        } else {
            val tops = detents.map { detent ->
                when (detent) {
                    SheetDetent.Large -> largeTop
                    SheetDetent.Medium -> height * MEDIUM_TOP
                    is SheetDetent.Height -> height - inset - with(density) { detent.height.toPx() }
                    SheetDetent.Fitted -> if (fitted > 0) height - inset - fitted * (width - 2 * inset) / width else height * MEDIUM_TOP
                }.coerceAtLeast(largeTop)
            }
            // A keyboard up takes the sheet to its large detent, as UIKit lifts a sheet clear of it.
            if (ime > 0 && SheetDetent.Large in detents) listOf(largeTop) else tops
        }
        motion.rests = rests
        val firstRest = rests.first()
        val lowest = rests.max()
        val floatingTop = rests.filter { it > largeTop + 0.5f }.minOrNull()
        val attaches = !regular && SheetDetent.Large in detents

        val reduceMotion = PeekTheme.reduceMotion
        fun animateTo(target: Float, velocity: Float, spec: AnimationSpec<Float>, then: () -> Unit = {}) {
            motion.job?.cancel()
            motion.job = scope.launch {
                animate(motion.top, target, velocity, spec) { value, _ -> motion.top = value }
                then()
            }
        }
        fun settle(velocity: Float) {
            if (!motion.moved) return
            motion.moved = false
            val target = motion.target(velocity, dismissible = true)
            if (target == null) {
                motion.releaseVelocity = velocity
                onDismiss()
            } else {
                animateTo(target, velocity, Motion.snappy(DETENT_DURATION))
            }
        }

        // Comes up to its first detent once laid out, and goes down past the window's bottom once dismissed.
        LaunchedEffect(visible) {
            if (visible) {
                withFrameNanos {}
                animateTo(motion.rests.first(), 0f, Motion.smooth(PRESENT_DURATION))
                motion.shown = true
            } else {
                val velocity = motion.releaseVelocity
                motion.releaseVelocity = 0f
                animateTo(motion.hidden, velocity, Motion.smooth(PRESENT_DURATION)) { onGone() }
            }
        }
        // A keyboard, or a resize, moves the sheet to the rest it now has nearest.
        LaunchedEffect(rests) {
            if (!visible || !motion.shown || motion.moved) return@LaunchedEffect
            if (rests.any { abs(it - motion.top) < 1f }) return@LaunchedEffect
            animateTo(rests.minBy { abs(it - motion.top) }, 0f, if (reduceMotion) Motion.smooth(0.2) else Motion.snappy(DETENT_DURATION))
        }

        PredictiveBackHandler(enabled = visible) { progress ->
            try {
                progress.collect { backProgress = it.progress }
                onDismiss()
            } catch (error: CancellationException) {
                backProgress = 0f
                throw error
            }
        }

        val presence = ((height - motion.top) / (height - firstRest).coerceAtLeast(1f)).coerceIn(0f, 1f)
        val dim = if (colors.dark) DIM_DARK else DIM_LIGHT
        // The dim, which a tap dismisses the sheet from.
        Box(
            Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = presence }
                .background(Color.Black.copy(alpha = dim))
                .pointerInput(Unit) { detectTapGestures { if (visible) onDismiss() } }
                .clearAndSetSemantics {},
        )

        val connection = remember(motion) {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    if (source != NestedScrollSource.UserInput || available.y >= 0) return Offset.Zero
                    val highest = motion.rests.minOrNull() ?: return Offset.Zero
                    if (motion.top <= highest + 0.5f) return Offset.Zero
                    val used = max(available.y, highest - motion.top)
                    motion.dragBy(used)
                    return Offset(0f, used)
                }

                override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                    if (source != NestedScrollSource.UserInput || available.y <= 0) return Offset.Zero
                    motion.dragBy(available.y)
                    return Offset(0f, available.y)
                }

                override suspend fun onPreFling(available: Velocity): Velocity {
                    if (!motion.moved) return Velocity.Zero
                    settle(available.y)
                    return available
                }
            }
        }
        val drag = rememberDraggableState { motion.dragBy(it) }

        val displayRadius = remember(view) {
            view.rootWindowInsets?.getRoundedCorner(RoundedCorner.POSITION_BOTTOM_LEFT)?.radius?.toFloat() ?: 0f
        }
        val sheetModifier = Modifier
            .semantics { this.paneTitle = paneTitle }
            .nestedScroll(connection)
            .draggable(drag, Orientation.Vertical, onDragStopped = { settle(it) })
            .pointerInput(Unit) { detectTapGestures { } }

        if (regular) {
            val shape = ContinuousRoundedShape(FORM_RADIUS)
            Box(
                Modifier
                    .placedAt(formWidth, formHeight) { (width - formWidth) / 2 to motion.top + backProgress * BACK_TRAVEL * formHeight }
                    .testTag(SHEET_PANEL)
                    .then(modifier)
                    .dropShadow(shape, SHEET_SHADOW)
                    .clip(shape)
                    .background(colors.groupedBackground)
                    .then(sheetModifier),
            ) {
                val ground: @Composable (Modifier) -> Unit = { Box(it.background(colors.groupedBackground)) }
                CompositionLocalProvider(
                    LocalSheetAttachment provides { 1f }, LocalSheetMenus provides menus, LocalSheetInsets provides SheetInsets.sheet(FORM_WIDTH),
                    LocalSheetGround provides ground,
                ) {
                    Grabber(Modifier.align(Alignment.TopCenter))
                    content()
                }
            }
        } else {
            val attachment = {
                val top = min(motion.top, lowest)
                if (!attaches || floatingTop == null) {
                    if (attaches) 1f else 0f
                } else {
                    ((floatingTop - top) / (floatingTop - largeTop)).coerceIn(0f, 1f)
                }
            }
            val attach = attachment()
            val sideInset = inset * (1 - attach)
            val layoutTop = min(motion.top, lowest)
            val panelHeight = height - sideInset - layoutTop
            val below = motion.top - layoutTop + backProgress * BACK_TRAVEL * panelHeight
            val bottomRadius = with(density) {
                val floating = max(displayRadius - inset, TOP_RADIUS.toPx())
                (floating * (1 - attach)).toDp()
            }
            val shape = UnevenContinuousRoundedShape(TOP_RADIUS, bottomRadius, bottomRadius, TOP_RADIUS)
            Box(
                Modifier
                    .placedAt(width - 2 * sideInset, panelHeight) { sideInset to layoutTop + below }
                    .testTag(SHEET_PANEL)
                    .then(modifier)
                    .dropShadow(shape, SHEET_SHADOW)
                    .then(sheetModifier),
            ) {
                // The glass it floats on, giving way to the opaque ground as it attaches.
                if (attach < 1f) {
                    SheetGlass(shape, backdrop, Modifier.matchParentSize().graphicsLayer { alpha = 1 - attachment() }) { dim * presence }
                }
                Box(
                    Modifier
                        .matchParentSize()
                        .clip(shape)
                        .drawBehind { drawRect(colors.groupedBackground, alpha = attachment()) },
                )
                val scale = { (width - 2 * inset * (1 - attachment())) / width }
                // Drawn again in what is scaled, it is scaled back, so that its glass blurs the window where it is.
                val ground: @Composable (Modifier) -> Unit = { bounds ->
                    Box(bounds.scaledFrom { 1 / scale() }) {
                        if (attach < 1f) SheetGlass(RectangleShape, backdrop, Modifier.fillMaxSize().graphicsLayer { alpha = 1 - attachment() }) { dim * presence }
                        Box(Modifier.fillMaxSize().drawBehind { drawRect(colors.groupedBackground, alpha = attachment()) })
                    }
                }
                Box(Modifier.matchParentSize().clip(shape)) {
                    CompositionLocalProvider(
                        LocalSheetAttachment provides attachment, LocalSheetMenus provides menus, LocalSheetInsets provides insets,
                        LocalSheetGround provides ground,
                    ) {
                        // Floating, the sheet is its attached self made smaller to its width, as UIKit draws it: laid
                        // out across the whole window, and scaled into the inset panel.
                        Box(
                            Modifier
                                .fillMaxSize()
                                .scaledFrom(scale)
                                .then(if (SheetDetent.Fitted in detents) Modifier.measureFitted { fitted = it } else Modifier),
                        ) { content() }
                        Grabber(Modifier.align(Alignment.TopCenter))
                    }
                }
            }
        }
        menus.content?.invoke()
    }
}

/** The panel `width` × `height` pixels in the whole window, at the offset [at] gives, read as it is placed. */
private fun Modifier.placedAt(width: Float, height: Float, at: () -> Pair<Float, Float>): Modifier =
    this.then(
        Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(Constraints.fixed(width.toInt().coerceAtLeast(0), height.toInt().coerceAtLeast(0)))
            layout(constraints.maxWidth, constraints.maxHeight) {
                val (x, y) = at()
                placeable.place(x.toInt(), y.toInt())
            }
        },
    )

/**
 * Lays the content out larger by 1 / [scale] and draws it scaled by it from its top leading corner, so that it fills
 * the room it is given as if laid out in a larger one.
 */
private fun Modifier.scaledFrom(scale: () -> Float): Modifier = this.then(
    Modifier.layout { measurable, constraints ->
        val factor = scale().coerceIn(0.5f, 2f)
        val placeable = measurable.measure(
            Constraints.fixed((constraints.maxWidth / factor).roundToInt(), (constraints.maxHeight / factor).roundToInt()),
        )
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeable.placeWithLayer(0, 0) {
                scaleX = factor
                scaleY = factor
                transformOrigin = TransformOrigin(0f, 0f)
            }
        }
    },
)

/**
 * Reports the height the content needs, laid out with all the room it could have, for [SheetDetent.Fitted], whose
 * content must answer intrinsic measurements (no lazy list or `BoxWithConstraints` in it).
 */
private fun Modifier.measureFitted(report: (Float) -> Unit): Modifier = this.then(
    Modifier.layout { measurable, constraints ->
        val needed = measurable.maxIntrinsicHeight(constraints.maxWidth)
        report(needed.toFloat())
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) { placeable.place(0, 0) }
    },
)

/**
 * The glass a sheet floats on, and a popover stands on: iOS 26's large glass ([LargeGlassSurface]). The iPhone's glass
 * blurs the window dimmed under it, where [backdrop] is the window as it is: in light, the dim is laid over the glass as
 * the glass would show it, its face's own colour for black at the dim's strength, `dim()`. In dark, the iPhone's sheet
 * is lighter than its glass would make of the dimmed window, and comes closest without it.
 */
@Composable
internal fun SheetGlass(shape: Shape, backdrop: MaterialBackdrop?, modifier: Modifier, dim: () -> Float = { 0f }) {
    val colors = PeekTheme.colors
    LargeGlassSurface(shape, modifier, backdrop, behind = colors.groupedBackground)
    if (colors.dark) return
    val black = GlassFace.of(colors.highContrast, PeekTheme.reduceTransparency).recipe(false).over(Color.Black, 400f)
    Box(modifier.clip(shape).drawBehind { drawRect(black, alpha = dim()) })
}

/** `.presentationDragIndicator(.visible)`: 36 × 5, 5 under the sheet's top. */
@Composable
private fun Grabber(modifier: Modifier) {
    Box(
        modifier
            .padding(top = GRABBER_TOP)
            .size(GRABBER_WIDTH, GRABBER_HEIGHT)
            .background(PeekTheme.colors.grabber, ContinuousRoundedShape(GRABBER_HEIGHT / 2))
            .clearAndSetSemantics {},
    )
}

/** The window is regular this wide and this tall, as the iPhone's size classes would have it. */
internal val REGULAR_WIDTH = 600.dp
internal val REGULAR_HEIGHT = 480.dp

/** The medium detent's top, as a part of the window's height, measured on the iPhone's screenshots. */
private const val MEDIUM_TOP = 0.4714f
private val FLOAT_INSET = 8.dp
private val LARGE_MIN_TOP = 10.dp
private val TOP_RADIUS = 38.dp
private val FORM_WIDTH = 540.dp
private val FORM_HEIGHT = 620.dp
private val FORM_MARGIN = 20.dp
private val FORM_RADIUS = 38.dp
private val GRABBER_TOP = 5.dp
private val GRABBER_WIDTH = 36.dp
private val GRABBER_HEIGHT = 5.dp
private const val DIM_LIGHT = 0.2f
private const val DIM_DARK = 0.48f
private const val PRESENT_DURATION = 0.5
private const val DETENT_DURATION = 0.4
/** How far down the predictive back gesture takes the sheet, as a part of its height. */
private const val BACK_TRAVEL = 0.1f
private val SHEET_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.12f), radius = 20.dp, y = 0.dp)

/** The sheet's panel, the dim around it aside. */
const val SHEET_PANEL = "sheet.panel"
