package com.plhery.peekdeliverytracker.ui.sheet

import android.view.RoundedCorner
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
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
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.deliveries.OverlayLayer
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.UnevenContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.GlassFace
import com.plhery.peekdeliverytracker.ui.theme.LargeGlassSurface
import com.plhery.peekdeliverytracker.ui.theme.LocalMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.materialBackdropLayer
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
 *   first takes it to the large detent, as a keyboard up for what it holds does. In a window lower than 480 (a phone
 *   on its side) it stands at the large detent whatever its detents, as iOS's does in compact height.
 * - Where the window is regular, it is a form sheet in the window's middle, 540 wide and 620 tall at most, opaque, which
 *   a drag down sends away too. A sheet with the [SheetDetent.Fitted] detent is as tall there as what it holds, as tall
 *   as the window's safe area at most (`fittedSheet`'s form).
 *
 * The window under it dims, black at 20 % (48 % in dark), and a tap there dismisses it, as the system's back and its
 * predictive gesture do, the sheet following the gesture down. While it isn't [dismissible]
 * (`interactiveDismissDisabled`), none of them does: the back is kept from what is under it, and a drag down past its
 * lowest detent stretches less and less and comes back. It comes and goes on a critically damped spring, and moves
 * between its detents on a snappy one. [detent] is the detent asked for (`presentationDetents(_:selection:)`): the
 * sheet comes up at it and moves to it each time it changes; where a drag, or a keyboard, leaves it at another,
 * [onDetentChange] hears which. TalkBack reaches only the sheet, named [paneTitle], and only its menu while one is open
 * ([FormMenuRow]). Once it has gone, [onClosed] runs, where TalkBack's focus goes back to what opened it. The window
 * made again (a rotation) finds it at the detent it had, without coming up again.
 */
@Composable
fun Sheet(
    visible: Boolean,
    onDismiss: () -> Unit,
    paneTitle: String,
    modifier: Modifier = Modifier,
    detents: List<SheetDetent> = listOf(SheetDetent.Medium, SheetDetent.Large),
    onClosed: () -> Unit = {},
    detent: SheetDetent? = null,
    onDetentChange: (SheetDetent) -> Unit = {},
    dismissible: Boolean = true,
    content: @Composable () -> Unit,
) {
    var present by remember { mutableStateOf(visible) }
    if (visible) present = true
    val memory = rememberSaveable(saver = SheetMemory.Saver) { SheetMemory() }
    if (!present) memory.forget()
    val host = LocalTabOverlay.current
    val dismiss by rememberUpdatedState(onDismiss)
    val closed by rememberUpdatedState(onClosed)
    val detentChanged by rememberUpdatedState(onDetentChange)
    val layer: (@Composable () -> Unit)? = if (!present) null else {
        {
            SheetLayer(
                visible, { dismiss() }, paneTitle, detents, host?.backdrop, modifier, memory,
                onGone = {
                    present = false
                    closed()
                },
                detent, { detentChanged(it) }, dismissible, content,
            )
        }
    }
    if (host == null) layer?.invoke() else OverlayLayer(host, SheetKey(paneTitle), layer)
}

private data class SheetKey(val title: String)

/**
 * What a sheet or a popover keeps with the window, so that the window made again finds it as it was: the detent it
 * rests at, of its sheet's, and whether it had come up. Forgotten once it has gone.
 */
internal class SheetMemory(var detent: Int = 0, var up: Boolean = false) {
    fun forget() {
        detent = 0
        up = false
    }

    companion object {
        val Saver: Saver<SheetMemory, Any> = listSaver(
            save = { listOf(it.detent, it.up) },
            restore = { SheetMemory(it[0] as Int, it[1] as Boolean) },
        )
    }
}

/** Where the sheet's top stands and how it moves there: the finger's drag, a settle, its coming and going. */
@Stable
private class SheetMotion(hidden: Float) {
    var top by mutableFloatStateOf(hidden)
    var rests: List<Float> = emptyList()
    var hidden: Float = hidden
    var moved = false

    /** A drag down can send it away; otherwise it stretches past its lowest rest as past its highest. */
    var dismissible = true

    /** It has begun to come up, or was made again already up. */
    var shown = false

    /** Made again already up, it keeps to its rest at once while the window's bars come, for its first frames. */
    var restoring = false
    var job: Job? = null

    /** The speed a drag let go of the sheet at as it dismissed it, which its way out keeps. */
    var releaseVelocity = 0f
    private var raw = hidden

    /**
     * The finger moved the sheet by `delta` pixels, past its highest rest less and less, as UIKit's rubber band, and
     * past its lowest too while it can't be sent away.
     */
    fun dragBy(delta: Float) {
        job?.cancel()
        restoring = false
        if (!moved) raw = top
        moved = true
        raw += delta
        val highest = rests.minOrNull() ?: return
        val lowest = rests.max()
        top = when {
            raw < highest -> highest - band(highest - raw)
            !dismissible && raw > lowest -> lowest + band(raw - lowest)
            else -> raw
        }
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

    /** The rest of the detent `detent`, the only one while the sheet has one (a form sheet, or lifted to large). */
    fun rest(detent: Int): Float = if (rests.size == 1) rests.first() else rests.getOrElse(detent) { rests.first() }

    companion object {
        const val DECELERATION = 0.998f
        const val BAND_LENGTH = 400f
    }
}

/**
 * Where a compact sheet stands in a window `width` × `height` pixels: floating `inset` in from its sides and bottom,
 * its highest rest under the status bar ([largeTop]), its lowest, and the highest it floats at, null where it always or
 * never attaches ([attaches]).
 */
@Immutable
private data class SheetGeometry(
    val width: Float,
    val height: Float,
    val inset: Float,
    val largeTop: Float,
    val lowest: Float,
    val floatingTop: Float?,
    val attaches: Boolean,
) {
    /** How attached the sheet is with its top at `top`, 0 floating to 1 at the large detent. */
    fun attachment(top: Float): Float {
        if (!attaches || floatingTop == null) return if (attaches) 1f else 0f
        return ((floatingTop - min(top, lowest)) / (floatingTop - largeTop)).coerceIn(0f, 1f)
    }

    /** The panel's size with its top at `top` and `attach` attached: it grows down to its lowest rest, then moves. */
    fun panelSize(top: Float, attach: Float): Size {
        val sideInset = inset * (1 - attach)
        return Size(width - 2 * sideInset, height - sideInset - min(top, lowest))
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
    memory: SheetMemory,
    onGone: () -> Unit,
    detent: SheetDetent?,
    onDetentChange: (SheetDetent) -> Unit,
    dismissible: Boolean,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val colors = PeekTheme.colors
    val scope = rememberCoroutineScope()
    val view = LocalView.current
    val menus = remember { SheetMenus() }
    val showing by rememberUpdatedState(visible)
    val dismiss by rememberUpdatedState(onDismiss)
    val canDismiss by rememberUpdatedState(dismissible)
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()
        val regular = maxWidth >= REGULAR_WIDTH && maxHeight >= REGULAR_HEIGHT
        val insets = SheetInsets.sheet(maxWidth)
        var backProgress by remember { mutableFloatStateOf(0f) }
        var fitted by remember { mutableFloatStateOf(0f) }
        // Whether what the sheet holds has the focus, a field it would type in.
        var focused by remember { mutableStateOf(false) }

        val bars = WindowInsets.systemBars.union(WindowInsets.displayCutout)
        val safeTop = bars.getTop(density).toFloat()
        val inset = with(density) { FLOAT_INSET.toPx() }
        val largeTop = max(safeTop, with(density) { LARGE_MIN_TOP.toPx() })
        val ime = WindowInsets.ime
        val keyboard by remember(ime, density) { derivedStateOf { ime.getBottom(density) > 0 } }
        val formWidth = with(density) { min(FORM_WIDTH.toPx(), width - 2 * FORM_MARGIN.toPx()) }
        val formRoom = with(density) { height - safeTop - bars.getBottom(this) - 2 * FORM_MARGIN.toPx() }
        // A fitted sheet's form is as tall as what it holds (`.form.fitted(horizontal: false, vertical: true)`).
        val formHeight = if (SheetDetent.Fitted in detents && fitted > 0) min(fitted, formRoom) else min(with(density) { FORM_HEIGHT.toPx() }, formRoom)
        // Low as a phone on its side, the sheet stands at its large detent, as iOS's does in compact height; and a
        // keyboard up for what it holds takes it there, as UIKit lifts a sheet clear of it. A keyboard still going
        // down from a field the sheet came over leaves it at its own detent.
        val compactHeight = maxHeight < REGULAR_HEIGHT
        val lifted = !regular && (compactHeight || keyboard && focused && SheetDetent.Large in detents)
        val rests = when {
            regular -> listOf(max(safeTop, (height - formHeight) / 2))
            lifted -> listOf(largeTop)
            else -> detents.map { detent ->
                when (detent) {
                    SheetDetent.Large -> largeTop
                    SheetDetent.Medium -> height * MEDIUM_TOP
                    is SheetDetent.Height -> height - inset - with(density) { detent.height.toPx() }
                    SheetDetent.Fitted -> if (fitted > 0) height - inset - fitted * (width - 2 * inset) / width else height * MEDIUM_TOP
                }.coerceAtLeast(largeTop)
            }
        }
        val motion = remember {
            SheetMotion(height).apply {
                this.rests = rests
                if (memory.up) {
                    top = rest(memory.detent)
                    restoring = true
                }
            }
        }
        motion.hidden = height
        motion.rests = rests
        motion.dismissible = dismissible
        // The detent asked for, which it comes up at, unless the window made again finds it up already.
        val requested = detent?.let { detents.indexOf(it) }?.takeIf { it >= 0 }
        if (requested != null && !motion.shown && !memory.up) memory.detent = requested
        val firstRest = rests.first()
        val lowest = rests.max()
        val floatingTop = rests.filter { it > largeTop + 0.5f }.minOrNull()
        val attaches = !regular && (compactHeight || SheetDetent.Large in detents)
        val geometry = SheetGeometry(width, height, inset, largeTop, lowest, floatingTop, attaches)
        // Read as the sheet is laid out and drawn, so that it moves without being composed again.
        val attachment = remember(geometry, motion) { { geometry.attachment(motion.top) } }
        val presence = remember(motion, height, firstRest) { { ((height - motion.top) / (height - firstRest).coerceAtLeast(1f)).coerceIn(0f, 1f) } }
        // How far the predictive back gesture has taken the panel down, a part of its height.
        val backOffset = { if (regular) backProgress * BACK_TRAVEL * formHeight else backProgress * BACK_TRAVEL * geometry.panelSize(motion.top, attachment()).height }

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
            val target = motion.target(velocity, dismissible)
            if (target == null) {
                motion.releaseVelocity = velocity
                dismiss()
            } else {
                // The detent the finger chose, unless the sheet stands at its only rest for now.
                val chosen = motion.rests.indexOf(target)
                if (motion.rests.size == detents.size && chosen >= 0) {
                    memory.detent = chosen
                    onDetentChange(detents[chosen])
                }
                memory.up = true
                animateTo(target, velocity, Motion.snappy(DETENT_DURATION))
            }
        }
        val settling by rememberUpdatedState<(Float) -> Unit> { settle(it) }

        // Comes up to its detent once laid out, and goes down past the window's bottom once dismissed. Made again with
        // the window already up, it stays where it is.
        LaunchedEffect(visible) {
            if (visible) {
                if (motion.restoring) {
                    motion.shown = true
                    repeat(RESTORING_FRAMES) { withFrameNanos {} }
                    motion.restoring = false
                    return@LaunchedEffect
                }
                // Opened again on its way out after the back gesture: from where the gesture left it.
                if (backProgress != 0f) {
                    motion.job?.cancel()
                    motion.top += backOffset()
                    backProgress = 0f
                }
                withFrameNanos {}
                animateTo(motion.rest(memory.detent), 0f, Motion.smooth(PRESENT_DURATION)) { memory.up = true }
                motion.shown = true
            } else {
                memory.forget()
                val velocity = motion.releaseVelocity
                motion.releaseVelocity = 0f
                animateTo(motion.hidden, velocity, Motion.smooth(PRESENT_DURATION)) { onGone() }
            }
        }
        // A keyboard, or a resize, moves the sheet to its detent's rest as it now is.
        LaunchedEffect(rests) {
            if (lifted && !compactHeight) {
                detents.indexOf(SheetDetent.Large).takeIf { it >= 0 && it != memory.detent }?.let {
                    memory.detent = it
                    onDetentChange(SheetDetent.Large)
                }
            }
            if (!visible || !motion.shown || motion.moved) return@LaunchedEffect
            val target = motion.rest(memory.detent)
            if (motion.restoring) {
                motion.top = target
                return@LaunchedEffect
            }
            if (abs(target - motion.top) < 1f) return@LaunchedEffect
            animateTo(target, 0f, if (reduceMotion) Motion.smooth(0.2) else Motion.snappy(DETENT_DURATION)) { memory.up = true }
        }

        // Asked for another detent, it moves there.
        LaunchedEffect(requested) {
            if (requested == null || requested == memory.detent) return@LaunchedEffect
            memory.detent = requested
            if (!visible || !motion.shown || motion.moved) return@LaunchedEffect
            animateTo(motion.rest(requested), 0f, if (reduceMotion) Motion.smooth(0.2) else Motion.snappy(DETENT_DURATION)) { memory.up = true }
        }

        // While it can't be sent away, the back goes nowhere, rather than to what is under it.
        BackHandler(enabled = visible && !dismissible) {}
        PredictiveBackHandler(enabled = visible && dismissible) { progress ->
            try {
                progress.collect { backProgress = it.progress }
                dismiss()
            } catch (error: CancellationException) {
                backProgress = 0f
                throw error
            }
        }

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
                    settling(available.y)
                    return available
                }
            }
        }
        val drag = rememberDraggableState { motion.dragBy(it) }

        val displayRadius = remember(view) {
            view.rootWindowInsets?.getRoundedCorner(RoundedCorner.POSITION_BOTTOM_LEFT)?.radius?.toFloat() ?: 0f
        }
        // While a menu is open over it, TalkBack reaches only the menu.
        val hidden = if (menus.content != null) Modifier.clearAndSetSemantics {} else Modifier
        val sheetModifier = Modifier
            .semantics { this.paneTitle = paneTitle }
            .onFocusChanged { focused = it.hasFocus }
            .nestedScroll(connection)
            .draggable(drag, Orientation.Vertical, onDragStopped = { settle(it) })
            .pointerInput(Unit) { detectTapGestures { } }
            .then(hidden)

        // The dim and the sheet: a layer over the tab, which the glass of an alert over the sheet blurs with the tab.
        val layer = if (backdrop != null) Modifier.materialBackdropLayer(backdrop) else Modifier
        val dim = if (colors.dark) DIM_DARK else DIM_LIGHT
        Box(Modifier.fillMaxSize().then(layer)) {
            // The dim, which a tap dismisses the sheet from.
            Box(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = presence() }
                    .background(Color.Black.copy(alpha = dim))
                    .pointerInput(Unit) { detectTapGestures { if (showing && canDismiss) dismiss() } }
                    .clearAndSetSemantics {},
            )
            if (regular) {
                val shape = ContinuousRoundedShape(FORM_RADIUS)
                val ground = remember(colors) { @Composable { bounds: Modifier -> Box(bounds.background(colors.groupedBackground)) } }
                Box(
                    Modifier
                        .placedAt({ Size(formWidth, formHeight) }) { Offset((width - formWidth) / 2, motion.top + backOffset()) }
                        .testTag(SHEET_PANEL)
                        .then(modifier)
                        .dropShadow(shape, SHEET_SHADOW)
                        .clip(shape)
                        .background(colors.groupedBackground)
                        .then(sheetModifier),
                ) {
                    CompositionLocalProvider(
                        LocalSheetAttachment provides { 1f }, LocalSheetMenus provides menus,
                        LocalSheetInsets provides SheetInsets.sheet(FORM_WIDTH, reachesBottom = false), LocalSheetGround provides ground,
                    ) {
                        Grabber(Modifier.align(Alignment.TopCenter))
                        if (SheetDetent.Fitted in detents) Box(Modifier.fillMaxSize().measureFitted { fitted = it }) { content() } else content()
                    }
                }
            } else {
                val floatingRadius = max(displayRadius - inset, with(density) { TOP_RADIUS.toPx() })
                val shape = remember(floatingRadius, attachment) { SheetShape(floatingRadius, attachment) }
                val glassDim = remember(presence, dim) { { dim * presence() } }
                val scale = remember(geometry, attachment) { { (width - 2 * inset * (1 - attachment())) / width } }
                // Drawn again in what is scaled, it is scaled back, so that its glass blurs the window where it is.
                val ground = remember(scale, attachment, backdrop, glassDim, colors) {
                    @Composable { bounds: Modifier ->
                        Box(bounds.scaledFrom { 1 / scale() }) {
                            FloatingGlass(RectangleShape, backdrop, attachment, Modifier.fillMaxSize(), glassDim)
                            Box(Modifier.fillMaxSize().drawBehind { drawRect(colors.groupedBackground, alpha = attachment()) })
                        }
                    }
                }
                Box(
                    Modifier
                        .placedAt({ geometry.panelSize(motion.top, attachment()) }) {
                            // Down to its lowest rest it grows; past it, and down with the back gesture, it moves.
                            val attach = attachment()
                            val layoutTop = min(motion.top, lowest)
                            val panelHeight = geometry.panelSize(motion.top, attach).height
                            Offset(inset * (1 - attach), layoutTop + (motion.top - layoutTop + backProgress * BACK_TRAVEL * panelHeight))
                        }
                        .testTag(SHEET_PANEL)
                        .then(modifier)
                        .dropShadow(shape, SHEET_SHADOW)
                        .then(sheetModifier),
                ) {
                    // The glass it floats on, giving way to the opaque ground as it attaches.
                    FloatingGlass(shape, backdrop, attachment, Modifier.matchParentSize(), glassDim)
                    Box(
                        Modifier
                            .matchParentSize()
                            .clip(shape)
                            .drawBehind { drawRect(colors.groupedBackground, alpha = attachment()) },
                    )
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
        }
        val above = remember(backdrop) { backdrop?.layered() }
        CompositionLocalProvider(LocalMaterialBackdrop provides above) { menus.content?.invoke() }
    }
}

/**
 * The glass a sheet floats on ([SheetGlass]), fading out as it attaches ([attachment]) and gone once it has, composed
 * again only as it comes or goes.
 */
@Composable
private fun FloatingGlass(shape: Shape, backdrop: MaterialBackdrop?, attachment: () -> Float, modifier: Modifier, dim: () -> Float) {
    val floating by remember(attachment) { derivedStateOf { attachment() < 1f } }
    if (floating) SheetGlass(shape, backdrop, modifier.graphicsLayer { alpha = 1 - attachment() }, dim)
}

/**
 * The compact sheet's outline: its top corners, and its bottom ones concentric with the display's while it floats,
 * `floatingRadius` then, squaring off as it attaches ([attachment], read as it is outlined). Its size follows the
 * attachment, so it is outlined again as that changes.
 */
private class SheetShape(private val floatingRadius: Float, private val attachment: () -> Float) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val bottom = with(density) { (floatingRadius * (1 - attachment())).toDp() }
        return UnevenContinuousRoundedShape(TOP_RADIUS, bottom, bottom, TOP_RADIUS).createOutline(size, layoutDirection, density)
    }
}

/** The panel [size] pixels in the whole window, at the offset [at] gives, both read as it is laid out. */
private fun Modifier.placedAt(size: () -> Size, at: () -> Offset): Modifier =
    this.then(
        Modifier.layout { measurable, constraints ->
            val (width, height) = size()
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
/** How many frames a sheet made again with the window keeps to its rest at once, while the window's bars come. */
private const val RESTORING_FRAMES = 2
/** How far down the predictive back gesture takes the sheet, as a part of its height. */
private const val BACK_TRAVEL = 0.1f
private val SHEET_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.12f), radius = 20.dp, y = 0.dp)

/** The sheet's panel, the dim around it aside. */
const val SHEET_PANEL = "sheet.panel"
