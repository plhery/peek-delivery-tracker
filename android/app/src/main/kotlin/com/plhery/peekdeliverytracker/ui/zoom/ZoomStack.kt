package com.plhery.peekdeliverytracker.ui.zoom

import android.graphics.BlurMaskFilter
import android.graphics.Paint
import android.graphics.Path
import android.view.RoundedCorner
import androidx.activity.BackEventCompat
import androidx.activity.compose.PredictiveBackHandler
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animate
import androidx.compose.foundation.background
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import androidx.navigationevent.compose.rememberNavigationEventDispatcherOwner
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabBarPadding
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowHinge
import com.plhery.peekdeliverytracker.ui.tabs.TabBarHidden
import com.plhery.peekdeliverytracker.ui.theme.KeepMotion
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.Radii
import com.plhery.peekdeliverytracker.ui.theme.gaussianBlurRadius
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.abs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * NavigationStack's zoom (`.navigationTransition(.zoom(sourceID:in:))`): a page over `root` that grows out of its
 * source ([zoomSource]) as it opens and shrinks back into it as it goes, as the iPhone's parcel page does out of its
 * card.
 *
 * `top` is the id of the page on top of the stack, null with the root alone. `page` draws `top`'s page or, once it has
 * left, the one going back into its source until it lands: the stack draws `top ?: transition.covered`
 * ([ZoomTransition.covered]). `onBack` pops the page.
 *
 * - Opening, the page's container springs from its source's frame to the window's on a critically damped spring of
 *   response 0.32 s, and the source's picture gives way to the page. The root recedes and dims under it, and the tab
 *   bar goes at once. Closing runs the other way, into the source wherever it is by then. The tab bar comes back 1.1 s
 *   after the page sets off, as UIKit's transition only ends then. Where the source can't be seen (scrolled away,
 *   filtered out or gone), the page comes from and goes back to a card's frame in the middle of the window, fading.
 * - Dragged down while its content is at its top, the page follows the finger and shrinks, the root showing behind
 *   it. Let go far or fast enough, it goes back into its source from there; otherwise it springs back on a spring of
 *   response 0.25 s, and a finger down on it meanwhile catches it where it is.
 * - The predictive back gesture shrinks it as Android's does for a container the size of the window: to 90 %, away
 *   from the edge it was swiped from, and up or down with the finger. Let go, it goes back into its source from there,
 *   and a cancel springs it back, which the next gesture takes over. A back press closes it.
 * - Under Reduce Motion, the page and the root crossfade over 0.35 s, and nothing moves or shrinks.
 *
 * While a page is over the root or on its way, the root takes no touch, TalkBack doesn't reach it and the root's own
 * back handlers wait. The keyboard's focus leaves the root as a page comes over it, and goes back in only once the page
 * has gone, as the root leaves the iPhone's window under a page pushed over it, taking the keyboard with it. At rest
 * under the page, the root isn't drawn. Nothing takes a touch while the page opens or closes: its flight, unlike a
 * spring back, can't be caught. TalkBack goes to the page as it lands, and back to its source as it goes. A stack shown
 * anew (after a rotation or a turn of the window) shows its page at rest, without a flight, as does a page shown still
 * ([ZoomTransition.showStill]).
 */
@Composable
fun ZoomStack(
    transition: ZoomTransition,
    top: Any?,
    onBack: () -> Unit,
    root: @Composable () -> Unit,
    page: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val reduceMotion = PeekTheme.reduceMotion
    val density = LocalDensity.current
    val direction = LocalLayoutDirection.current
    val view = LocalView.current
    val hinge = LocalWindowHinge.current
    val safe = WindowInsets.systemBars.union(WindowInsets.displayCutout)
    val flight = remember(transition) { ZoomFlight(transition, top) }
    val scope = rememberCoroutineScope()
    val back by rememberUpdatedState(onBack)
    flight.reduceMotion = reduceMotion
    flight.follow(top) {
        // Read as a flight sets off, and only then.
        val bounds = flight.bounds
        Surroundings(
            reduceMotion = reduceMotion,
            density = density,
            displayRadius = view.rootWindowInsets?.getRoundedCorner(RoundedCorner.POSITION_TOP_LEFT)?.radius?.toFloat() ?: 0f,
            safe = Rect(
                safe.getLeft(density, direction).toFloat(),
                safe.getTop(density).toFloat(),
                bounds.width - safe.getRight(density, direction),
                bounds.height - safe.getBottom(density),
            ),
            hinge = hinge?.let { it.start.toFloat()..it.end.toFloat() },
        )
    }
    DisposableEffect(flight) {
        flight.attach()
        onDispose(flight::detach)
    }
    TabBarHidden(flight.id != null || flight.barHeld)

    val pageFocus = remember { AccessibilityFocus() }
    LaunchedEffect(flight, flight.run) {
        when (flight.phase) {
            Phase.Opening -> {
                flight.animateTo(1f)
                flight.phase = Phase.Rest
                pageFocus.request()
            }
            Phase.Closing -> {
                coroutineScope {
                    val bar = launch { if (!flight.crossfade) delay(TAB_BAR_RETURN) }
                    flight.animateTo(0f)
                    val source = flight.source?.focus
                    flight.land()
                    source?.request()
                    bar.join()
                }
                flight.barHeld = false
            }
            Phase.Rest -> Unit
        }
    }
    PredictiveBackHandler(enabled = flight.id != null) { events ->
        // A page on its way back takes no other back.
        if (flight.phase == Phase.Closing) {
            events.collect {}
            return@PredictiveBackHandler
        }
        flight.startBack()
        try {
            events.collect { event -> if (flight.canFollowBack()) flight.back(event) }
            flight.letBackGo()
            flight.beginClose()
            back()
        } catch (cancelled: CancellationException) {
            flight.springBackFromBack(scope)
            throw cancelled
        }
    }

    Box(modifier.fillMaxSize().onPlaced(flight::place)) {
        val covered = flight.id != null
        val focusManager = LocalFocusManager.current
        val rootFocus = remember { RootFocus() }
        LaunchedEffect(covered) { if (covered && rootFocus.has) focusManager.clearFocus() }
        // The root's own back handlers wait while a page is over it.
        val events = rememberNavigationEventDispatcherOwner(enabled = !covered)
        CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides events) {
            Box(
                Modifier
                    .fillMaxSize()
                    .then(if (covered) Modifier.clearAndSetSemantics {} else Modifier)
                    // Read as the focus moves, so that it goes back in from the frame the page has gone in.
                    .focusProperties { onEnter = { if (flight.id != null) cancelFocusChange() } }
                    .onFocusChanged { rootFocus.has = it.hasFocus }
                    .focusGroup()
                    .drawWithContent { if (!flight.hidesRoot) drawContent() },
            ) { root() }
        }
        if (page != null && covered) {
            val shadow = remember(density) { ShadowPaint(density) }
            // The dim over the root, and the page's shadow on it.
            Box(Modifier.fillMaxSize().drawBehind { flight.drawBehindPage(this, shadow) })
            val face = rememberGraphicsLayer()
            val connection = remember(flight) { DragConnection(flight) }
            Box(
                Modifier
                    .fillMaxSize()
                    .nestedScroll(connection)
                    .pointerInput(flight) { dragToClose(flight, scope) { back() } },
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .graphicsLayer { flight.transformContainer(this) }
                        .drawWithContent {
                            drawContent()
                            flight.drawFace(this, face)
                        }
                        .then(if (flight.phase == Phase.Closing) Modifier.clearAndSetSemantics {} else Modifier.accessibilityFocus(pageFocus))
                        .background(PeekTheme.colors.background),
                ) {
                    // The tab bar is hidden over the page.
                    CompositionLocalProvider(LocalTabBarPadding provides PaddingValues(0.dp)) { page() }
                }
            }
            // Nothing takes a touch while the page opens or closes.
            if (flight.flying) Box(Modifier.fillMaxSize().pointerInput(Unit) { swallowTouches() })
        }
    }
}

/** Whether the keyboard's focus is in the root. */
private class RootFocus {
    var has = false
}

/** How long after a page sets off back into its source the tab bar comes back: UIKit's transition ends only then. */
private const val TAB_BAR_RETURN = 1_100L

/** The flights' spring, as the iPhone's open and close measure: critically damped, response 0.32 s. */
private val FLIGHT = Motion.springWithResponse<Float>(0.32, 1.0)

/** A page let go short of closing springs back: critically damped, response 0.25 s. */
private val SPRING_BACK = Motion.springWithResponse<Float>(0.25, 1.0)
private val SPRING_BACK_OFFSET = Motion.springWithResponse<Offset>(0.25, 1.0)

/** Under Reduce Motion, UIKit's crossfade in place of the zoom, over a navigation transition's 0.35 s. */
private val CROSSFADE = Motion.easeInOut<Float>(Motion.DEFAULT_CURVE_DURATION)

internal enum class Phase { Rest, Opening, Closing }

/** What a flight needs to know of the window as it sets off, in pixels. */
internal class Surroundings(
    val reduceMotion: Boolean,
    val density: Density,
    val displayRadius: Float,
    val safe: Rect,
    val hinge: ClosedFloatingPointRange<Float>?,
)

/** A page's flight over the root, and what the gestures do to it. */
@Stable
internal class ZoomFlight(private val transition: ZoomTransition, initial: Any?) {
    /** The page drawn over the root: open, opening or closing. A stack shown with a page shows it at rest. */
    var id: Any? by mutableStateOf(initial)
        private set
    var phase: Phase by mutableStateOf(Phase.Rest)

    /** From 0, the page in its source's frame, to 1, the page over the whole window. */
    var progress: Float by mutableFloatStateOf(if (initial != null) 1f else 0f)
        private set
    private var velocity = 0f

    /** Counts the flights that set off, for the effect running them. */
    var run: Int by mutableIntStateOf(0)
        private set
    var crossfade: Boolean by mutableStateOf(false)
        private set
    var barHeld: Boolean by mutableStateOf(false)
    var reduceMotion = false

    /** The source the page flies out of and back into, and its frame as last seen; or the middle of the window. */
    private var chosenSource: ZoomSourceNode? by mutableStateOf(null)

    /** The source while it is still the page's: a list hands a card's node on to another card. */
    val source: ZoomSourceNode? get() = chosenSource?.takeIf { it.id == id && it.isAttached }
    private var sourceSeen = Rect.Zero
    private var fallback: Rect? by mutableStateOf(null)
    private var sourceRadius = 0f
    private var displayRadius = 0f
    private var density = 1f
    private var surroundings: (() -> Surroundings)? = null

    /** Where a gesture let the page go, which it flies back from. */
    private var heldAt: Rect? by mutableStateOf(null)
    private var releaseVelocity = 0f

    private var dragAnchor: Offset? by mutableStateOf(null)
    private var dragStart = Offset.Zero
    private var drag: Offset by mutableStateOf(Offset.Zero)

    /** The page springing back from a drag, which a finger catches. */
    private var dragSpring: Job? = null
    private var backing: Boolean by mutableStateOf(false)
    private var backStarting = false
    private var backProgress: Float by mutableFloatStateOf(0f)
    private var backFromLeft = true
    private var backStartY = 0f
    private var backDeltaY: Float by mutableFloatStateOf(0f)

    /** The page springing back from a cancelled back gesture, which the next one takes over. */
    private var backSpring: Job? = null

    private var host: LayoutCoordinates? = null
    var bounds: Rect by mutableStateOf(Rect.Zero)
        private set

    /** The finger on the page, as the page last saw it; null with none down. */
    var finger: Offset? = null

    val dragging: Boolean get() = dragAnchor != null
    val atRest: Boolean get() = phase == Phase.Rest && !dragging && !backing
    val flying: Boolean get() = phase != Phase.Rest
    val hidesRoot: Boolean get() = id != null && atRest && progress >= 1f

    /** Whether a drag may take the page now. */
    fun canDrag(): Boolean = id != null && atRest && !crossfade && !reduceMotion

    /** Whether the predictive back gesture moves the page: not under Reduce Motion, and only from rest. */
    fun canFollowBack(): Boolean = id != null && !crossfade && !reduceMotion && (backing || atRest)

    /** The source unseen under the page: none in Reduce Motion's crossfade, where the page fades in over it. */
    val hidden: Any? get() = if (crossfade) null else id

    fun attach() {
        transition.attach(this)
    }

    fun detach() {
        transition.detach(this)
    }

    fun place(coordinates: LayoutCoordinates) {
        host = coordinates
        bounds = Rect(Offset.Zero, coordinates.size.toSize())
    }

    /** Sets a flight off as the page on top changes. Called as the stack is drawn, so that it draws its first frame. */
    fun follow(top: Any?, surroundings: () -> Surroundings) {
        this.surroundings = surroundings
        // A page is shown still only as it next comes on top.
        val still = top != null && top == transition.still
        if (top != null) transition.still = null
        when {
            // The page left the stack: it goes back into its source, staying drawn until it lands.
            top == null -> if (id != null && phase != Phase.Closing) beginClose()
            top == id && phase != Phase.Closing -> Unit
            // Opened again on its way back: it turns round.
            top == id -> {
                heldAt = null
                barHeld = false
                start(Phase.Opening)
            }
            // Another page in place of the open one, one shown still, or one before the root was laid out: it shows
            // at once.
            (id != null && phase != Phase.Closing) || still || bounds.isEmpty -> {
                id = top
                heldAt = null
                progress = 1f
                start(Phase.Rest)
            }
            else -> {
                id = top
                val around = surroundings()
                crossfade = around.reduceMotion
                resolve(top, around)
                heldAt = null
                barHeld = false
                progress = 0f
                velocity = 0f
                start(Phase.Opening)
            }
        }
    }

    /** Sets the page off back into its source, from where it is: where a gesture holds it, or the window. */
    fun beginClose() {
        val id = id ?: return
        if (phase == Phase.Closing) return
        surroundings?.invoke()?.let { around ->
            crossfade = around.reduceMotion
            resolve(id, around)
        }
        val held = heldAt
        velocity = if (held != null && !crossfade) ZoomGeometry.closingVelocity(small(), held, releaseVelocity) else 0f
        releaseVelocity = 0f
        barHeld = true
        start(Phase.Closing)
    }

    private fun start(phase: Phase) {
        this.phase = phase
        run++
    }

    /** Where the page flies from and to: its source where it can be seen, the middle of the window otherwise. */
    private fun resolve(id: Any, around: Surroundings) {
        takeWindow(around)
        val node = transition.sources[id]
        val seen = node?.let { frameOf(it, clipped = true) }
        val frame = node?.let { frameOf(it, clipped = false) }
        if (node != null && seen != null && frame != null && seen.width > 0f && seen.height > 0f && seen.overlaps(bounds)) {
            chosenSource = node
            sourceSeen = frame
            fallback = null
            sourceRadius = with(around.density) { node.cornerRadius.toPx() }
        } else {
            chosenSource = null
            fallback = ZoomGeometry.fallback(around.safe, around.density.density, around.hinge)
            sourceRadius = with(around.density) { Radii.card.toPx() }
        }
    }

    /** The window's density and corners, which a gesture needs as a flight does: a page shown at once has had no flight. */
    private fun takeWindow(around: Surroundings) {
        density = around.density.density
        displayRadius = around.displayRadius
    }

    private fun frameOf(node: ZoomSourceNode, clipped: Boolean): Rect? {
        val coordinates = node.coordinates ?: return null
        val host = host ?: return null
        if (!coordinates.isAttached || !host.isAttached) return null
        val receding = transition.receding?.takeIf { it.isAttached && it.holds(coordinates) }
        val region = receding?.parentCoordinates ?: return host.localBoundingBoxOf(coordinates, clipBounds = clipped)
        // Receded as far as the page has come, worked out here rather than read from its layer, which a frame may
        // update only after the page's.
        val frame = receding.localBoundingBoxOf(coordinates, clipBounds = clipped)
        val middle = receding.size.toSize().center
        val scale = ZoomGeometry.receded(behind())
        val origin = host.localPositionOf(region, Offset.Zero) + middle
        return Rect(origin + (frame.topLeft - middle) * scale, origin + (frame.bottomRight - middle) * scale)
    }

    private fun LayoutCoordinates.holds(inner: LayoutCoordinates): Boolean =
        generateSequence(inner) { it.parentCoordinates }.any { it === this }

    /** The page has gone back into its source: the source shows again, and the page leaves the stack. */
    fun land() {
        id = null
        phase = Phase.Rest
        progress = 0f
        velocity = 0f
        chosenSource = null
        fallback = null
        heldAt = null
        crossfade = false
    }

    suspend fun animateTo(target: Float) {
        val spec: AnimationSpec<Float> = if (crossfade) CROSSFADE else FLIGHT
        val initialVelocity = if (crossfade) 0f else velocity
        val flight: suspend () -> Unit = {
            animate(progress, target, initialVelocity, spec) { value, speed ->
                progress = value
                velocity = speed
            }
        }
        // Reduce Motion's crossfade plays even with animations off, as iOS keeps it.
        if (crossfade) withContext(KeepMotion) { flight() } else flight()
        velocity = 0f
    }

    /** The source's frame: followed while it is laid out, kept as last seen once it isn't. */
    private fun small(): Rect {
        fallback?.let { return it }
        source?.let { node -> frameOf(node, clipped = false)?.let { sourceSeen = it } }
        return sourceSeen
    }

    /** The page's frame at the end of its flight: where a gesture holds it, or the window. */
    private fun large(): Rect {
        heldAt?.let { return it }
        dragAnchor?.let { return ZoomGeometry.dragFrame(bounds, it, drag) }
        if (backing) {
            return ZoomGeometry.backFrame(
                bounds, backProgress, backFromLeft, backDeltaY,
                ZoomGeometry.BACK_EDGE_GAP * density, ZoomGeometry.BACK_LIFT * density,
            )
        }
        return bounds
    }

    fun container(): Rect = if (crossfade || id == null) bounds else ZoomGeometry.frame(small(), large(), progress)

    fun radius(): Float = if (atRest || crossfade) 0f else ZoomGeometry.cornerRadius(sourceRadius, displayRadius, progress)

    /** How far the page has come over the root. Under Reduce Motion the root stays as it is. */
    fun behind(): Float = if (id == null || crossfade) 0f else progress

    /** The page fades in under Reduce Motion, and where it has no source to grow out of. */
    private fun containerOpacity(): Float = when {
        crossfade -> progress
        fallback != null -> ZoomGeometry.pageOpacity(progress)
        else -> 1f
    }

    /**
     * The page's container: the page laid out at the window's size, scaled by the container's width over the window's
     * from its top left, and cut to the container with its corners round.
     */
    fun transformContainer(layer: GraphicsLayerScope) = with(layer) {
        val frame = container()
        val scale = if (bounds.width > 0f) frame.width / bounds.width else 1f
        transformOrigin = TransformOrigin(0f, 0f)
        scaleX = scale
        scaleY = scale
        translationX = frame.left - bounds.left
        translationY = frame.top - bounds.top
        alpha = containerOpacity()
        val cut = !atRest && !crossfade && scale > 0f
        clip = cut
        shape = if (cut) ContainerCut(frame.height / scale, radius() / scale) else RectangleShape
    }

    fun drawBehindPage(scope: DrawScope, shadow: ShadowPaint) = with(scope) {
        if (crossfade || id == null) return@with
        drawRect(Color.Black, alpha = ZoomGeometry.dim(behind()))
        if (atRest) return@with
        val opacity = ZoomGeometry.SHADOW_OPACITY * progress * containerOpacity()
        if (opacity <= 0f) return@with
        val frame = container()
        val radius = radius()
        drawIntoCanvas { drawn ->
            val canvas = drawn.nativeCanvas
            // Around the page only, which may let it through as it fades.
            shadow.page.rewind()
            shadow.page.addRoundRect(frame.left, frame.top, frame.right, frame.bottom, radius, radius, Path.Direction.CW)
            canvas.save()
            canvas.clipOutPath(shadow.page)
            shadow.paint.color = Color.Black.copy(alpha = opacity).toArgb()
            canvas.drawRoundRect(frame.left, frame.top, frame.right, frame.bottom, radius, radius, shadow.paint)
            canvas.restore()
        }
    }

    /** The source's picture at the container's top, as wide as the container, giving way to the page. */
    fun drawFace(scope: DrawScope, face: GraphicsLayer) = with(scope) {
        if (crossfade || fallback != null || id == null) return@with
        val picture = source?.picture ?: return@with
        val opacity = ZoomGeometry.faceOpacity(progress)
        val width = picture.size.width
        if (opacity <= 0f || width <= 0 || bounds.width <= 0f) return@with
        // In the container's own pixels, where it is as wide as the window.
        val scale = bounds.width / width
        face.record(picture.size) { drawLayer(picture) }
        face.alpha = opacity
        scale(scale, scale, pivot = Offset.Zero) { drawLayer(face) }
    }

    fun startDrag(at: Offset) {
        surroundings?.invoke()?.let(::takeWindow)
        dragAnchor = at
        dragStart = at
        drag = Offset.Zero
    }

    fun dragTo(position: Offset) {
        drag = position - dragStart
    }

    /** The finger has let go: the page goes back into its source from where it is, or springs back. */
    fun release(velocity: Velocity, scope: CoroutineScope, onBack: () -> Unit) {
        val anchor = dragAnchor ?: return
        if (ZoomGeometry.dismisses(drag.y, velocity.y, bounds.height)) {
            heldAt = ZoomGeometry.dragFrame(bounds, anchor, drag)
            releaseVelocity = velocity.y
            dragAnchor = null
            drag = Offset.Zero
            beginClose()
            onBack()
        } else {
            springBack(scope)
        }
    }

    /** The page springs back to the window: let go short of closing, or the system took the drag back. */
    fun springBack(scope: CoroutineScope) {
        if (!dragging || dragSpring != null) return
        dragSpring = scope.launch {
            try {
                animate(Offset.VectorConverter, drag, Offset.Zero, animationSpec = SPRING_BACK_OFFSET) { value, _ -> drag = value }
            } finally {
                // Unless a finger caught the page meanwhile.
                if (dragSpring === coroutineContext[Job]) {
                    dragSpring = null
                    dragAnchor = null
                    drag = Offset.Zero
                }
            }
        }
    }

    /**
     * A finger down on the page as it springs back catches it where it is, and drags it on from there, as a finger
     * catches a scroll view springing back. False with no spring to catch.
     */
    fun catch(at: Offset): Boolean {
        val spring = dragSpring ?: return false
        dragSpring = null
        spring.cancel()
        dragStart = at - drag
        return true
    }

    /** A back gesture starts: it takes the page over from a spring back from the last one, where that has it. */
    fun startBack() {
        backSpring?.let { spring ->
            backSpring = null
            spring.cancel()
        }
        backStarting = true
    }

    fun back(event: BackEventCompat) {
        if (backStarting) {
            surroundings?.invoke()?.let(::takeWindow)
            backing = true
            backStarting = false
            backFromLeft = event.swipeEdge == BackEventCompat.EDGE_LEFT
            backStartY = event.touchY
        }
        backProgress = event.progress
        backDeltaY = event.touchY - backStartY
    }

    /** The gesture has let go: the page goes back into its source from where the gesture held it. */
    fun letBackGo() {
        if (!backing) return
        heldAt = large()
        backing = false
        backProgress = 0f
        backDeltaY = 0f
    }

    /** The gesture was cancelled: the page springs back to the window from where the gesture held it. */
    fun springBackFromBack(scope: CoroutineScope) {
        if (!backing || backSpring != null) return
        val progress = backProgress
        val deltaY = backDeltaY
        backSpring = scope.launch {
            try {
                animate(1f, 0f, animationSpec = SPRING_BACK) { value, _ ->
                    backProgress = progress * value
                    backDeltaY = deltaY * value
                }
            } finally {
                // Unless another gesture took the page over meanwhile.
                if (backSpring === coroutineContext[Job]) {
                    backSpring = null
                    backing = false
                    backProgress = 0f
                    backDeltaY = 0f
                }
            }
        }
    }
}

/** The page's container in its own pixels: as wide as the window, `height` tall, its corners `radius` round. */
private data class ContainerCut(val height: Float, val radius: Float) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val cut = Size(size.width, height.coerceAtMost(size.height))
        if (radius <= 0f) return Outline.Rectangle(Rect(Offset.Zero, cut))
        return ContinuousRoundedShape(with(density) { radius.toDp() }).createOutline(cut, layoutDirection, density)
    }
}

/** The page's shadow: black blurred as a Gaussian 30 points wide, as the iPhone's is, and the page it is cut around. */
internal class ShadowPaint(density: Density) {
    val paint = Paint().apply {
        isAntiAlias = true
        maskFilter = BlurMaskFilter(with(density) { gaussianBlurRadius(ZoomGeometry.SHADOW_DEVIATION.dp).toPx() }, BlurMaskFilter.Blur.NORMAL)
    }
    val page = Path()
}

/**
 * The page's content, at its top and dragged further down by a finger, hands the drag to the page ([dragToClose]); a
 * wheel or a key scrolling it there doesn't. While the page has the drag, the content scrolls no more, nor flings.
 */
private class DragConnection(private val flight: ZoomFlight) : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset = if (flight.dragging) available else Offset.Zero

    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
        val finger = flight.finger
        if (source != NestedScrollSource.UserInput || available.y <= 0f || finger == null || !flight.canDrag()) return Offset.Zero
        flight.startDrag(finger)
        return available
    }

    override suspend fun onPreFling(available: Velocity): Velocity = if (flight.dragging || flight.flying) available else Velocity.Zero
}

/**
 * The page's drag down to close it. The page sees the finger before its content does and, once the drag is the page's,
 * keeps it from the content. Content that doesn't scroll lets a drag down past the touch slop take the page; content
 * that scrolls hands it over at its top ([DragConnection]). A drag the system cancels springs back, however far it went.
 */
private suspend fun PointerInputScope.dragToClose(flight: ZoomFlight, scope: CoroutineScope, onBack: () -> Unit) {
    awaitEachGesture {
        val down = awaitFirstDown(requireUnconsumed = false, pass = PointerEventPass.Initial)
        // A page springing back stops under the finger, which the content under it doesn't take.
        if (flight.catch(down.position)) down.consume()
        flight.finger = down.position
        val tracker = VelocityTracker()
        tracker.addPosition(down.uptimeMillis, down.position)
        var released = false
        try {
            while (true) {
                val change = awaitPointerEvent(PointerEventPass.Initial).changes.firstOrNull { it.id == down.id } ?: break
                // An up taken before the page sees it is the system cancelling the gesture, as Compose hands one on.
                if (!change.pressed && change.isConsumed) break
                tracker.addPosition(change.uptimeMillis, change.position)
                flight.finger = change.position
                if (flight.dragging) {
                    flight.dragTo(change.position)
                    change.consume()
                }
                if (!change.pressed) {
                    released = true
                    if (flight.dragging) flight.release(tracker.calculateVelocity(), scope, onBack)
                    break
                }
                val after = awaitPointerEvent(PointerEventPass.Final).changes.firstOrNull { it.id == down.id } ?: break
                if (!flight.dragging && !after.isConsumed && flight.canDrag()) {
                    val moved = after.position - down.position
                    if (moved.y > viewConfiguration.touchSlop && moved.y > abs(moved.x)) {
                        flight.startDrag(after.position)
                        after.consume()
                    }
                }
            }
        } finally {
            flight.finger = null
            // A drag the system cancelled, or one whose finger went otherwise, springs back however far it went.
            if (!released) flight.springBack(scope)
        }
    }
}

/** Takes every touch, so that nothing under it does. */
private suspend fun PointerInputScope.swallowTouches() {
    awaitEachGesture {
        while (true) {
            val event = awaitPointerEvent(PointerEventPass.Initial)
            event.changes.forEach { it.consume() }
            if (event.changes.none { it.pressed }) break
        }
    }
}
