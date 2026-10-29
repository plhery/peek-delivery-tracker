package com.plhery.peekdeliverytracker.ui.map

import android.content.Context
import android.graphics.Picture
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.EdgeInsets
import com.plhery.peekdeliverytracker.core.GlobeCamera
import com.plhery.peekdeliverytracker.core.MapLabels
import com.plhery.peekdeliverytracker.core.MapOverlay
import com.plhery.peekdeliverytracker.core.MapPainter
import com.plhery.peekdeliverytracker.core.MapPalette
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.core.PipPlacement
import com.plhery.peekdeliverytracker.core.PipRequest
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.core.Rect
import com.plhery.peekdeliverytracker.core.Size
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.core.WorldAtlas
import com.plhery.peekdeliverytracker.ui.draw.MapPip
import com.plhery.peekdeliverytracker.ui.draw.MapTextWidths
import com.plhery.peekdeliverytracker.ui.draw.drawMapDrawings
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.easing
import com.plhery.peekdeliverytracker.core.UnitCurve
import androidx.compose.animation.core.tween
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.ceil
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
 * `WorldMapView` as the cards show it (A-S86): the journey on the globe, framed once for its size, with no gestures and
 * no flights between cameras. The land, the legs, the parcel's pulsing dot (`live`, never under Reduce Motion) and the
 * marks are faded toward the bottom (`fades`) and in from the leading edge (`fadesIn`); Pip stands in front of the
 * fade, beside the parcel's dot, when `pip` asks for him on a palette with a card.
 *
 * Framing and laying out the map is a few milliseconds of work, so it is done off the main thread and kept
 * ([MapScenes]): a card scrolled back into view finds its picture done, and shows it at once. A map seen for the first
 * time shows nothing until then; `entrance` is how the drawing then comes in, as SwiftUI's `.transition(.opacity)`
 * brings a map in once the atlas is read, and null shows it at once. A map whose picture changes keeps the old one
 * until the new one is made. Where its picture has to be there at once ([LocalMapsAtOnce]), the map makes it then, and
 * shows it with its first frame. Sizes, insets and boxes are in points (dp). Decorative.
 */
@Composable
fun WorldMapView(
    atlas: WorldAtlas,
    route: ParcelRoute,
    mode: ParcelRoute.Mode,
    palette: MapPalette,
    modifier: Modifier = Modifier,
    labels: MapLabels = MapLabels.All,
    showsContext: Boolean = true,
    live: Boolean = false,
    pip: PipRequest? = null,
    fades: Boolean = false,
    fadesIn: Boolean = false,
    quiet: Boolean = false,
    insets: EdgeInsets = EdgeInsets(),
    covered: Rect? = null,
    buttons: Rect? = null,
    language: AppLanguage = AppLanguage.En,
    entrance: FiniteAnimationSpec<Float>? = null,
) {
    val colors = PeekTheme.colors
    val reduceMotion = PeekTheme.reduceMotion
    val context = LocalContext.current.applicationContext
    BoxWithConstraints(modifier.clearAndSetSemantics {}) {
        val request = MapSceneRequest(
            atlas, route, mode, palette, labels, showsContext, pip, quiet, insets, covered, buttons, language,
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
        val drawn = scene ?: return@BoxWithConstraints
        Box(Modifier.fillMaxSize().graphicsLayer { alpha = shown.value }) {
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
 * Whether the maps here make their pictures as they are composed, on the main thread, rather than show nothing until
 * another thread has made them: for a card drawn only into a picture, as a flight's card is, which shows as the list
 * draws it from the first frame it is seen in.
 */
internal val LocalMapsAtOnce = staticCompositionLocalOf { false }

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
    val showsContext: Boolean,
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

/** A map drawn: the land and legs, the marks over them, the parcel's dot, and Pip's spot. */
internal class MapScene(val base: Picture, val marks: Picture, val current: Point?, val pip: PipPlacement?)

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

    private fun make(request: MapSceneRequest, measurer: TextMeasurer, widths: MapTextWidths): MapScene = with(request) {
        val size = Size(width, height)
        val locale = language.locale
        val standing = if (palette.card == null) null else pip
        val camera = if (covered == null && standing == null) {
            GlobeCamera.framing(route, mode, size, insets, closeUps = false)
        } else {
            // What the card writes over the map also moves it when a place of the journey would lose its name to it,
            // or Pip his spot beside the parcel; a route that leaves him no spot at its end is framed with room for him.
            val shown = { camera: GlobeCamera, box: Rect? ->
                MapOverlay(
                    route, camera, size, insets, labels, sites = false, mode = mode, context = false, atlas = atlas, locale = locale,
                    countryName = { it }, pip = standing, covered = box, buttons = buttons, measurer = widths,
                ).shown
            }
            GlobeCamera.framing(route, mode, size, insets, covered = covered, shown = shown, pip = standing != null, closeUps = false)
        }
        val overlay = MapOverlay(
            route, camera, size, insets, labels, sites = false, mode = mode, context = showsContext, atlas = atlas, locale = locale,
            countryName = { TrackingLocation.countryName(it, language) }, pip = standing, covered = covered, buttons = buttons,
            measurer = widths,
        )
        val base = MapPainter.drawBase(size, atlas, route, camera, palette, night = null) + MapPainter.drawLegs(overlay, palette, quiet)
        val marks = MapPainter.drawMarks(overlay, palette, moving = false, quiet = quiet, measurer = widths)
        val wide = ceil(width).toInt().coerceAtLeast(1)
        val tall = ceil(height).toInt().coerceAtLeast(1)
        // The measurer is the layout's too, which measures under the widths' lock.
        val recorded = synchronized(widths) {
            record(wide, tall) { drawMapDrawings(base, dark, highContrast) } to
                record(wide, tall) { drawMapDrawings(marks, dark, highContrast, textMeasurer = measurer) }
        }
        MapScene(recorded.first, recorded.second, overlay.dots.firstOrNull { it.kind == MapOverlay.DotKind.Current }?.point, overlay.pip)
    }

    private fun record(width: Int, height: Int, draw: DrawScope.() -> Unit): Picture {
        val picture = Picture()
        val canvas = ComposeCanvas(picture.beginRecording(width, height))
        CanvasDrawScope().draw(Density(1f), LayoutDirection.Ltr, canvas, ComposeSize(width.toFloat(), height.toFloat()), draw)
        picture.endRecording()
        return picture
    }
}
