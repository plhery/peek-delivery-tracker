package com.plhery.peekdeliverytracker.ui.map

import android.content.Context
import android.graphics.Picture
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.EdgeInsets
import com.plhery.peekdeliverytracker.core.GlobeCamera
import com.plhery.peekdeliverytracker.core.GlobeMotion
import com.plhery.peekdeliverytracker.core.MapLabels
import com.plhery.peekdeliverytracker.core.MapOverlay
import com.plhery.peekdeliverytracker.core.MapPainter
import com.plhery.peekdeliverytracker.core.MapPalette
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.core.PipPlacement
import com.plhery.peekdeliverytracker.core.PipRequest
import com.plhery.peekdeliverytracker.core.PipSpot
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.core.Rect
import com.plhery.peekdeliverytracker.core.Size
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.core.UnitCurve
import com.plhery.peekdeliverytracker.core.WorldAtlas
import com.plhery.peekdeliverytracker.ui.draw.MapPip
import com.plhery.peekdeliverytracker.ui.draw.MapTextWidths
import com.plhery.peekdeliverytracker.ui.draw.drawMapDrawings
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.easing
import java.time.Instant
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import androidx.compose.ui.graphics.Canvas as ComposeCanvas
import androidx.compose.ui.geometry.Size as ComposeSize

/**
 * `WorldMapView` (A-S86): the journey on the globe, framed for its size. The land, the legs, the parcel's pulsing dot
 * (`live`, never under Reduce Motion) and the marks are faded toward the bottom (`fades`) and in from the leading edge
 * (`fadesIn`); Pip stands in front of the fade, beside the parcel's dot, when `pip` asks for him on a palette with a
 * card.
 *
 * The camera of a map that moves (`interactive` or `peek`) flies to a new frame (A-AN46): along the great circle, eased
 * in and out, lifted out over a long way, from wherever a flight under way has got to, and straight there under Reduce
 * Motion; any other map shows its new frame at once. A change of view (`mode`), or of `recenter`, brings a moved map
 * back; a new frame or scan leaves it where it was put. An `interactive` map turns under one finger and zooms under
 * two, about where they first stood (A-G12): its first move frees it (`onFreeChange`), and Pip keeps his spot beside
 * the dot while it moves under him, and waits for a flight to land. A `peek` map zooms under two fingers and flies back
 * as they lift (A-G13); one finger is left to what lies under it.
 *
 * Framing and laying out a map at rest is a few milliseconds of work, so it is done off the main thread and kept
 * ([MapScenes]): a card scrolled back into view finds its picture done, and shows it at once. A map seen for the first
 * time shows nothing until then; `entrance` is how the drawing then comes in, as SwiftUI's `.transition(.opacity)`
 * brings a map in once the atlas is read, and null shows it at once. A map whose picture changes keeps the old one
 * until the new one is made. Where its picture has to be there at once ([LocalMapsAtOnce]), the map makes it then, and
 * shows it with its first frame. A map that moves is drawn again for each frame it moves in, on the main thread, as the
 * iPhone's `TimelineView` draws it. An opened map names a facility as such (`sites`) and shades the night side as it is
 * at `night`. Sizes, insets and boxes are in points (dp). Decorative.
 */
@Composable
fun WorldMapView(
    atlas: WorldAtlas,
    route: ParcelRoute,
    mode: ParcelRoute.Mode,
    palette: MapPalette,
    modifier: Modifier = Modifier,
    labels: MapLabels = MapLabels.All,
    sites: Boolean = false,
    showsContext: Boolean = true,
    interactive: Boolean = false,
    peek: Boolean = false,
    night: Instant? = null,
    live: Boolean = false,
    pip: PipRequest? = null,
    fades: Boolean = false,
    fadesIn: Boolean = false,
    quiet: Boolean = false,
    insets: EdgeInsets = EdgeInsets(),
    covered: Rect? = null,
    buttons: Rect? = null,
    recenter: Int = 0,
    language: AppLanguage = AppLanguage.En,
    entrance: FiniteAnimationSpec<Float>? = null,
    onFreeChange: (Boolean) -> Unit = {},
) {
    val colors = PeekTheme.colors
    val reduceMotion = PeekTheme.reduceMotion
    val context = LocalContext.current.applicationContext
    BoxWithConstraints(modifier.clearAndSetSemantics {}) {
        val request = MapSceneRequest(
            atlas, route, mode, palette, labels, sites, showsContext, night, pip, quiet, insets, covered, buttons, language,
            maxWidth.value.toDouble(), maxHeight.value.toDouble(), colors.dark, colors.highContrast,
        )
        val atOnce = LocalMapsAtOnce.current
        val pending = remember(request) { if (atOnce) MapScenes.made(request, context) else MapScenes.request(request, context) }
        val made = pending.completedOrNull()
        // The last scene delivered: until the new request's scene is made, a map whose route, language, stage, carrier
        // or size changes keeps the picture it shows rather than going blank, as the iPhone draws the new one at once.
        val delivered by produceState(made, pending) {
            pending.completedOrNull()?.let {
                value = it
                return@produceState
            }
            MapScenes.awaiting {
                value = try {
                    pending.await()
                } catch (cancelled: CancellationException) {
                    throw cancelled
                } catch (_: Exception) {
                    null
                }
            }
        }
        val scene = made ?: delivered
        // A map whose picture is already made, as one scrolled back into a list finds it, shows at once: the entrance
        // is for a map that waited for its picture, as the iPhone's card fades its map in once, when the atlas is read.
        val shown = remember { Animatable(if (entrance == null || scene != null) 1f else 0f) }
        val ready = scene != null
        LaunchedEffect(ready) {
            if (!ready) return@LaunchedEffect
            if (entrance == null) shown.snapTo(1f) else shown.animateTo(1f, entrance)
        }
        val motion = rememberGlobeMotion(scene?.camera, request, mode, recenter, reduceMotion, interactive || peek, onFreeChange)
        val drawn = motion.scene(scene, request, interactive) ?: return@BoxWithConstraints
        val watch = LocalMapCameraWatch.current
        if (watch != null && (interactive || peek)) SideEffect { watch(interactive, drawn.camera) }
        Box(
            Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = shown.value }
                .then(if (interactive || peek) Modifier.pointerInput(interactive) { motion.follow(this, interactive) } else Modifier),
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer { if (fades || fadesIn) compositingStrategy = CompositingStrategy.Offscreen }
                    .drawWithCache {
                        // Made once for the size, as the parcel's pulse draws the map again each frame.
                        val down = if (fades) Brush.verticalGradient(0.78f to Color.Black, 1f to Color.Transparent) else null
                        val across = if (fadesIn) Brush.horizontalGradient(0f to Color.Transparent, 0.46f to Color.Black) else null
                        onDrawWithContent {
                            drawContent()
                            if (down != null) drawRect(down, blendMode = BlendMode.DstIn)
                            if (across != null) drawRect(across, blendMode = BlendMode.DstIn)
                        }
                    },
            ) {
                Canvas(Modifier.fillMaxSize()) { drawPicture(drawn.base) }
                if (live && !reduceMotion && drawn.current != null) PulsingHalo(colors.resolve(palette.accent), drawn.current)
                Canvas(Modifier.fillMaxSize()) { drawPicture(drawn.marks) }
            }
            val card = palette.card
            if (drawn.pip != null && card != null) {
                MapPip(drawn.pip, drawn.current, ink = colors.resolve(card.ink), surface = colors.resolve(card.surface))
            }
        }
    }
}

/**
 * A map's camera as it moves ([GlobeMotion]) for a map whose target, the camera that frames it, is `target` once its
 * picture is made. A map that `moves` under the fingers takes its target two frames after it first has one, as the
 * opened map lays out its summary in its first, so it never flies to the frame it opened at. Then a new target flies
 * the camera there unless the map is free, and a new `mode` or `recenter` flies it there whatever. Any other map
 * shows each target as it comes, and asks for no frame.
 */
@Composable
private fun rememberGlobeMotion(
    target: GlobeCamera?,
    request: MapSceneRequest,
    mode: ParcelRoute.Mode,
    recenter: Int,
    reduceMotion: Boolean,
    moves: Boolean,
    onFreeChange: (Boolean) -> Unit,
): MapMotion {
    val context = LocalContext.current.applicationContext
    val density = LocalDensity.current.density
    val motion = remember { MapMotion(context) }
    motion.target = target
    motion.request = request
    motion.reduceMotion = reduceMotion
    motion.density = density
    motion.onFreeChange = onFreeChange
    val current by rememberUpdatedState(target)
    LaunchedEffect(target != null, moves) {
        if (target == null || !moves) return@LaunchedEffect
        withFrameNanos {}
        withFrameNanos {}
        current?.let { motion.update(motion.state.appeared(it)) }
    }
    // `onChange(of: target)`, `onChange(of: mode)` and `onChange(of: recenter)`, once the map shows.
    LaunchedEffect(target) { if (!motion.state.free) motion.fly() }
    LaunchedEffect(mode) { motion.fly() }
    LaunchedEffect(recenter) { motion.fly() }
    val flight = motion.state.flight
    LaunchedEffect(flight) {
        if (flight == null) return@LaunchedEffect
        val start = withFrameNanos { it }
        while (motion.progress < 1) {
            val now = withFrameNanos { it }
            motion.progress = min(1.0, (now - start) / 1e9 / flight.duration)
        }
        motion.update(motion.state.landed(flight))
    }
    return motion
}

/**
 * What [WorldMapView] keeps of its camera: the [GlobeMotion], how far through its time the flight shown is, read off
 * the frame clock, and what the map's gestures do. Its parts are snapshot state, read as the map is composed.
 */
@Stable
private class MapMotion(private val context: Context) {
    var state: GlobeMotion by mutableStateOf(GlobeMotion())
        private set

    /** How far the flight shown is through its time: each flight starts at 0. */
    var progress: Double by mutableDoubleStateOf(0.0)

    var target: GlobeCamera? = null
    lateinit var request: MapSceneRequest
    var reduceMotion = false
    var density = 1f
    var onFreeChange: (Boolean) -> Unit = {}

    fun update(next: GlobeMotion) {
        val was = state
        if (next.flight !== was.flight) progress = 0.0
        state = next
        if (next.free != was.free) onFreeChange(next.free)
    }

    /** `fly(to:size:)`, to the target, once the map shows. */
    fun fly() {
        val to = target ?: return
        if (state.camera == null) return
        update(state.flown(to, progress, max(request.width, request.height), reduceMotion))
    }

    /**
     * The picture to show: the map's own at rest, at its target, and otherwise the map drawn now for the camera on the
     * screen, with Pip where he stands then, none on an opened map while it flies, and the faint names waiting for it
     * to land.
     */
    @Composable
    fun scene(rest: MapScene?, request: MapSceneRequest, interactive: Boolean): MapScene? {
        val target = rest?.camera ?: return null
        val now = state
        val camera = now.camera
        if (camera == null || (now.flight == null && !now.free && camera == target)) return rest
        val shown = now.shown(target, progress)
        val moving = now.moving(progress)
        val pip = request.pip?.takeUnless { interactive && moving }?.copy(held = now.heldPip)
        return remember(request, shown, pip, moving) { MapScenes.at(request, shown, pip, moving, context) }
    }

    /** Where Pip stands on the map as `camera` shows it. */
    private fun pipSpot(camera: GlobeCamera?): PipSpot? = camera?.let { MapScenes.pipSpot(request, it, context) }

    private val size: Size get() = Size(request.width, request.height)

    /**
     * Follows the fingers on the map: on an opened map, a drag turns it and a pinch zooms it, both freeing it; on a
     * card's, a pinch zooms it until the fingers lift, and the map flies back to its target.
     */
    suspend fun follow(scope: PointerInputScope, interactive: Boolean) {
        scope.detectGlobeGestures(
            pinchOnly = !interactive,
            onDrag = { translation ->
                update(state.dragged(Size(translation.x / density.toDouble(), translation.y / density.toDouble()), progress, ::pipSpot))
            },
            onPinch = { magnification, around ->
                val anchor = Point(around.x / density.toDouble(), around.y / density.toDouble())
                update(
                    if (interactive) {
                        state.pinched(magnification.toDouble(), anchor, size, request.insets, progress, ::pipSpot)
                    } else {
                        state.peeked(magnification.toDouble(), anchor, size, request.insets, progress)
                    },
                )
            },
            onEnd = {
                update(state.ended())
                if (!interactive) target?.let { update(state.flown(it, progress, max(request.width, request.height), reduceMotion)) }
            },
        )
    }
}

/**
 * Whether the maps here make their pictures as they are composed, on the main thread, rather than show nothing until
 * another thread has made them: for a card drawn only into a picture, as a flight's card is, which shows as the list
 * draws it from the first frame it is seen in.
 */
internal val LocalMapsAtOnce = staticCompositionLocalOf { false }

/**
 * Hears where each map that moves (`interactive` or `peek`) looks from, each time it is composed: for tests, which see
 * its pixels but not its camera.
 */
internal val LocalMapCameraWatch = staticCompositionLocalOf<((interactive: Boolean, camera: GlobeCamera) -> Unit)?> { null }

/** The scene if it is made, without waiting. */
@OptIn(ExperimentalCoroutinesApi::class)
private fun Deferred<MapScene>.completedOrNull(): MapScene? =
    if (isCompleted && getCompletionExceptionOrNull() == null) getCompleted() else null

/** A picture recorded one unit to the point, drawn at the screen's density. */
private fun DrawScope.drawPicture(picture: Picture) {
    scale(density, density, pivot = Offset.Zero) {
        drawContext.canvas.nativeCanvas.drawPicture(picture)
    }
}

/** `PulsingHalo`: a 10-point disc in `color` at the dot, growing to 3.4 times its size as it fades out, every 2.4 s. */
@Composable
private fun PulsingHalo(color: Color, center: Point) {
    val transition = rememberInfiniteTransition(label = "halo")
    val progress by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(2400, easing = UnitCurve.easeOut.easing), RepeatMode.Restart), label = "halo")
    Canvas(Modifier.fillMaxSize()) {
        val expanded = progress
        drawCircle(
            color.copy(alpha = color.alpha * 0.35f * (1 - expanded)),
            radius = (5 * (1 + 2.4f * expanded)) * density,
            center = Offset(center.x.toFloat() * density, center.y.toFloat() * density),
        )
    }
}

/** Everything a map's picture depends on. Its route is the same object for the same parcel ([rememberParcelRoute]). */
internal data class MapSceneRequest(
    val atlas: WorldAtlas,
    val route: ParcelRoute,
    val mode: ParcelRoute.Mode,
    val palette: MapPalette,
    val labels: MapLabels,
    val sites: Boolean,
    val showsContext: Boolean,
    val night: Instant?,
    val pip: PipRequest?,
    val quiet: Boolean,
    val insets: EdgeInsets,
    val covered: Rect?,
    val buttons: Rect?,
    val language: AppLanguage,
    val width: Double,
    val height: Double,
    val dark: Boolean,
    val highContrast: Boolean,
)

/** A map drawn: the land and legs, the marks over them, the parcel's dot, Pip's spot, and the camera it is seen from. */
internal class MapScene(val base: Picture, val marks: Picture, val current: Point?, val pip: PipPlacement?, val camera: GlobeCamera)

/**
 * The maps' pictures, framed, laid out and recorded off the main thread, the last few dozen kept: the cards of a list
 * share them, and one scrolled back finds its own. One text measurer serves every map, as the layout keeps the widths
 * it measured per measurer.
 */
internal object MapScenes {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val busy = AtomicInteger()
    private const val KEPT = 48

    private val scenes = object : LinkedHashMap<MapSceneRequest, Deferred<MapScene>>(KEPT, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<MapSceneRequest, Deferred<MapScene>>?): Boolean = size > KEPT
    }

    @Volatile
    private var text: Pair<TextMeasurer, MapTextWidths>? = null

    private val waiting = AtomicInteger()

    /** Whether no picture is being made or on its way to a map, for tests that wait for the maps. */
    val isIdle: Boolean get() = busy.get() == 0 && waiting.get() == 0

    /** `delivery`, a map waiting for its picture, counted until the picture is in the map's hands. */
    suspend fun awaiting(delivery: suspend () -> Unit) {
        waiting.incrementAndGet()
        try {
            delivery()
        } finally {
            waiting.decrementAndGet()
        }
    }

    /** The picture for `request`, made now on another thread unless it was made or is being made. */
    @OptIn(ExperimentalCoroutinesApi::class)
    fun request(request: MapSceneRequest, context: Context): Deferred<MapScene> = synchronized(scenes) {
        scenes[request]?.takeUnless { it.isCompleted && it.getCompletionExceptionOrNull() != null }?.let { return it }
        val (measurer, widths) = text(context)
        busy.incrementAndGet()
        scope.async {
            try {
                make(request, measurer, widths)
            } finally {
                busy.decrementAndGet()
            }
        }.also { scenes[request] = it }
    }

    /**
     * The picture for `request`, made now on this thread unless it was made already, and kept for the maps that ask
     * for it next. One still being made on another thread is made again here rather than waited for.
     */
    fun made(request: MapSceneRequest, context: Context): Deferred<MapScene> {
        synchronized(scenes) { scenes[request]?.takeIf { it.completedOrNull() != null }?.let { return it } }
        val (measurer, widths) = text(context)
        val made = CompletableDeferred<MapScene>()
        try {
            made.complete(make(request, measurer, widths))
        } catch (error: Exception) {
            made.completeExceptionally(error)
            return made
        }
        synchronized(scenes) { scenes[request] = made }
        return made
    }

    private fun text(context: Context): Pair<TextMeasurer, MapTextWidths> = text ?: synchronized(this) {
        text ?: TextMeasurer(createFontFamilyResolver(context), Density(1f), LayoutDirection.Ltr).let { it to MapTextWidths(it) }
            .also { text = it }
    }

    /**
     * `request`'s map seen from `camera`, made now on this thread and not kept, as a map that moves draws each frame:
     * Pip as `pip` asks, and the faint names left out while it is `moving`.
     */
    fun at(request: MapSceneRequest, camera: GlobeCamera, pip: PipRequest?, moving: Boolean, context: Context): MapScene {
        val (measurer, widths) = text(context)
        return make(request, measurer, widths, camera, pip, moving)
    }

    /** `pipSpot(at:size:)`: where Pip stands on `request`'s map seen from `camera`, looking for his best spot. */
    fun pipSpot(request: MapSceneRequest, camera: GlobeCamera, context: Context): PipSpot? = with(request) {
        val standing = (if (palette.card == null) null else pip) ?: return null
        val widths = text(context).second
        synchronized(widths) {
            MapOverlay(
                route, camera, Size(width, height), insets, labels, sites = sites, mode = mode, context = showsContext, atlas = atlas,
                locale = language.locale, countryName = { TrackingLocation.countryName(it, language) }, pip = standing, covered = covered,
                buttons = buttons, measurer = widths,
            ).pip?.spot
        }
    }

    /** `targetCamera(in:)`: the camera that frames `request`'s route. */
    private fun target(request: MapSceneRequest, widths: MapTextWidths): GlobeCamera = with(request) {
        val size = Size(width, height)
        val standing = if (palette.card == null) null else pip
        if (covered == null && standing == null) return GlobeCamera.framing(route, mode, size, insets, closeUps = false)
        // What the card writes over the map also moves it when a place of the journey would lose its name to it, or
        // Pip his spot beside the parcel; a route that leaves him no spot at its end is framed with room for him.
        val shown = { camera: GlobeCamera, box: Rect? ->
            MapOverlay(
                route, camera, size, insets, labels, sites = sites, mode = mode, context = false, atlas = atlas, locale = language.locale,
                countryName = { it }, pip = standing, covered = box, buttons = buttons, measurer = widths,
            ).shown
        }
        GlobeCamera.framing(route, mode, size, insets, covered = covered, shown = shown, pip = standing != null, closeUps = false)
    }

    private fun make(
        request: MapSceneRequest,
        measurer: TextMeasurer,
        widths: MapTextWidths,
        at: GlobeCamera? = null,
        pip: PipRequest? = request.pip,
        moving: Boolean = false,
    ): MapScene = with(request) {
        val size = Size(width, height)
        val standing = if (palette.card == null) null else pip
        val camera = at ?: target(request, widths)
        val overlay = MapOverlay(
            route, camera, size, insets, labels, sites = sites, mode = mode, context = showsContext, atlas = atlas, locale = language.locale,
            countryName = { TrackingLocation.countryName(it, language) }, pip = standing, covered = covered, buttons = buttons,
            measurer = widths,
        )
        val base = MapPainter.drawBase(size, atlas, route, camera, palette, night = night) + MapPainter.drawLegs(overlay, palette, quiet)
        val marks = MapPainter.drawMarks(overlay, palette, moving = moving, quiet = quiet, measurer = widths)
        val wide = ceil(width).toInt().coerceAtLeast(1)
        val tall = ceil(height).toInt().coerceAtLeast(1)
        // The measurer is the layout's too, which measures under the widths' lock.
        val recorded = synchronized(widths) {
            record(wide, tall) { drawMapDrawings(base, dark, highContrast) } to
                record(wide, tall) { drawMapDrawings(marks, dark, highContrast, textMeasurer = measurer) }
        }
        MapScene(
            recorded.first, recorded.second, overlay.dots.firstOrNull { it.kind == MapOverlay.DotKind.Current }?.point, overlay.pip, camera,
        )
    }

    private fun record(width: Int, height: Int, draw: DrawScope.() -> Unit): Picture {
        val picture = Picture()
        val canvas = ComposeCanvas(picture.beginRecording(width, height))
        CanvasDrawScope().draw(Density(1f), LayoutDirection.Ltr, canvas, ComposeSize(width.toFloat(), height.toFloat()), draw)
        picture.endRecording()
        return picture
    }
}
