package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.core.PullGeometry
import com.plhery.peekdeliverytracker.core.PullToRefreshArrow
import com.plhery.peekdeliverytracker.core.PullToRefreshGlyph
import com.plhery.peekdeliverytracker.core.PullToRefreshIndicator
import com.plhery.peekdeliverytracker.core.PullToRefreshModel
import com.plhery.peekdeliverytracker.core.PullToRefreshPalette
import com.plhery.peekdeliverytracker.core.PullToRefreshSeal
import com.plhery.peekdeliverytracker.core.PullToRefreshTrigger
import com.plhery.peekdeliverytracker.core.Rect
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.draw.toComposePath
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.CappedDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekKeyframes
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.RubberBandOverscroll
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.keyframeAnimator
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * The list's pull to refresh (`.refreshable` and UIRefreshControl, A-G06): the list pulled down past its top follows the
 * finger on UIScrollView's rubber band; pulled past the model's threshold, the refresh starts at once and the list holds
 * a gap of [HOLD] open over its content until it is over, then closes it. It takes the list's drags at its top before
 * the list's own bounce does, through nested scrolling, so the list's flings are left alone, and a fling into the top
 * still bounces as before. Thrown back up while pulled, the list takes the pull back and scrolls on with what is left
 * of the throw, as a scroll view's momentum carries on past its top.
 *
 * It is the tab's ([DeliveriesState.listPull]), not the list's, so that a pull or a gap held open lives on as the window
 * turns and the list is drawn anew ([ListPullEffects]).
 *
 * Positions are in pixels: [past] is how far the content shows below its rest, [held] the gap held open.
 */
@Stable
internal class ListPull(private val state: DeliveriesState, private val scope: CoroutineScope) : NestedScrollConnection {
    var density: Density = Density(1f)
    var height: Int = 0

    var past by mutableFloatStateOf(0f)
        private set
    val held = Animatable(0f)

    private var dragging = false
    private var trigger = PullToRefreshTrigger()
    private var spring: Job? = null

    private val model: PullToRefreshModel get() = state.pull

    /** How far the content is moved down from its rest: what the list is drawn translated by, beside its gap. */
    val shown: Float get() = past + held.value

    /**
     * How far a finger or a throw has scrolled the content down since the list was made, as the list took it: with
     * [shown], how far the content has moved in all, which the cards in flight move with ([DeliveriesState.contentShift]).
     */
    var scrolled by mutableFloatStateOf(0f)
        private set

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        if (past <= 0f || available.y >= 0f) return Offset.Zero
        if (source != NestedScrollSource.UserInput) {
            // A throw up takes back the pull as it moves the content, before the list scrolls on.
            if (dragging || spring?.isActive == true) return Offset.Zero
            val back = max(available.y, -past)
            move(past + back)
            return Offset(0f, back)
        }
        // Pushing back up takes back the pull first.
        catch()
        val finger = finger(past)
        val back = max(available.y, -finger)
        move(band(finger + back))
        return Offset(0f, back)
    }

    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
        if (consumed.y != 0f) scrolled += consumed.y
        if (source != NestedScrollSource.UserInput || available.y <= 0f || height <= 0) return Offset.Zero
        catch()
        move(band(finger(past) + available.y))
        return Offset(0f, available.y)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        dragging = false
        // Thrown up, the list flings, and the throw takes the pull back first (onPreScroll).
        if (past <= 0f || available.y < 0f) return Velocity.Zero
        release(available.y)
        return available
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        // A throw up too weak to take the whole pull back leaves the rest to spring home.
        if (past > 0f && spring?.isActive != true) release(0f)
        return Velocity.Zero
    }

    private fun catch() {
        dragging = true
        spring?.cancel()
        spring = null
    }

    private fun move(next: Float) {
        past = max(0f, next)
        track()
    }

    /** Let go: the content springs back to its rest, the gap's edge while refreshing. */
    private fun release(velocity: Float) {
        spring?.cancel()
        spring = scope.launch {
            val back = Animatable(past)
            back.animateTo(0f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = STIFFNESS), initialVelocity = velocity) {
                past = max(0f, value)
                track()
            }
            past = 0f
            track()
        }
    }

    /** The model hears where the pull is; a pull past the threshold under the finger starts the refresh. */
    fun track() {
        with(density) {
            model.track(PullGeometry(overscroll = past.toDp().value.toDouble(), inset = held.value.toDp().value.toDouble(), height = height.toDp().value.toDouble()))
        }
        val next = trigger.next(model.distance, dragging, model)
        trigger = next.trigger
        if (next.starts) start()
    }

    /**
     * The refresh starts under the finger: the gap opens over the content, and the content stays where it is, the pull
     * now measured from the gap's edge.
     */
    private fun start() {
        val gap = with(density) { HOLD.toPx() }
        val shown = shown
        scope.launch { held.snapTo(gap) }
        past = max(0f, shown - gap)
        state.refreshFromPull()
    }

    /** The refresh is over: the gap closes. */
    suspend fun close() {
        held.animateTo(0f, tween(CLOSE_MILLIS, easing = EASE_IN_OUT)) { track() }
        track()
    }

    private fun band(finger: Float): Float = RubberBandOverscroll.band(finger, height)

    private fun finger(shown: Float): Float = RubberBandOverscroll.finger(shown, height)

    companion object {
        /** The gap the system holds open while refreshing. */
        val HOLD = 60.dp

        /** UIScrollView's return spring, as the list's own bounce has it. */
        private const val STIFFNESS = 100f

        /** UIRefreshControl closes its gap on an ease in and out; its length is the system's, read off by eye. */
        private const val CLOSE_MILLIS = 400
        private val EASE_IN_OUT = CubicBezierEasing(0.42f, 0f, 0.58f, 1f)
    }
}

/** The tab's side of the pull ([DeliveriesState.listPull]): the window's density, and the gap closing as the refresh settles. */
@Composable
internal fun ListPullEffects(state: DeliveriesState) {
    val pull = state.listPull
    pull.density = LocalDensity.current
    val phase by state.pull.phaseFlow.collectAsState()
    LaunchedEffect(pull, phase) {
        if (phase == PullToRefreshModel.Phase.Settling) pull.close()
    }
}

/**
 * `PullToRefreshIndicator`: the seal over its label in a band [PullToRefreshIndicator.HEIGHT] tall across the list, behind
 * its content and moving with its top (`origin`, in pixels from the list's top, `null` once the top has scrolled away),
 * kept a little under halfway down the gap. It fades in with the pull, and out as the refresh settles; it takes no
 * touches and TalkBack doesn't read it, as the list says how a refresh went. A light tap as each refresh starts.
 */
@Composable
internal fun PullIndicator(model: PullToRefreshModel, origin: () -> Float?, modifier: Modifier = Modifier) {
    val localizer = LocalLocalizer.current
    val reduceMotion = PeekTheme.reduceMotion
    val colors = PeekTheme.colors
    val density = LocalDensity.current
    val phase by model.phaseFlow.collectAsState()
    val distance by model.distanceFlow.collectAsState()
    val threshold by model.thresholdFlow.collectAsState()
    val holdDistance by model.holdDistanceFlow.collectAsState()
    val label by model.labelFlow.collectAsState()
    val starts by model.startsFlow.collectAsState()
    val awaitingRest by model.awaitingRestFlow.collectAsState()
    val result by model.resultFlow.collectAsState()
    val pullLabel = localizer.text("app.pullToRefresh")
    val indicator = remember(phase, distance, threshold, holdDistance, label, awaitingRest, result, pullLabel, reduceMotion) {
        PullToRefreshIndicator.of(model, pullLabel, reduceMotion)
    }

    // As the phase changes, the opacity and the seal's scale ease from where they were (`.animation(value: phase)`).
    val blend = remember { PhaseBlend() }
    LaunchedEffect(phase) { blend.restart() }
    val opacity = blend.value(0, indicator.opacity)
    val sealScale = blend.value(1, indicator.sealScale)

    SensoryFeedbackEffect(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light), starts)

    val bandHeight = PullToRefreshIndicator.HEIGHT.dp
    Column(
        modifier
            .fillMaxWidth()
            .height(bandHeight)
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                layout(placeable.width, placeable.height) {
                    val top = origin()
                    if (top != null) placeable.place(0, (top + indicator.offset.dp.toPx()).toInt())
                }
            }
            .graphicsLayer { alpha = opacity.toFloat() }
            .testTag(PULL_INDICATOR)
            .clearAndSetSemantics {},
        verticalArrangement = Arrangement.spacedBy(PullToRefreshIndicator.SPACING.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Seal(model, indicator.seal, starts, Modifier.graphicsLayer {
            scaleX = sealScale.toFloat()
            scaleY = sealScale.toFloat()
        })
        PullLabel(indicator.label, colors.resolve(PullToRefreshPalette.inkSoft), density)
    }
}

/** The label under the seal: caption 2 medium on one line, shrinking to 0.8 of its size before it is cut. */
@Composable
private fun PullLabel(text: String, color: Color, density: Density) {
    CappedDynamicTypeSize(DynamicTypeSize.XXLarge) {
        val style = PeekTheme.type.style(IosTextStyle.Caption2, FontWeight.Medium)
        val measurer = rememberTextMeasurer()
        Box(
            Modifier
                .fillMaxWidth()
                .height(PullToRefreshIndicator.LABEL_HEIGHT.dp)
                .padding(horizontal = PullToRefreshIndicator.LABEL_PADDING.dp)
                .layout { measurable, constraints ->
                    val line = measurer.measure(text, style, maxLines = 1, softWrap = false)
                    val scale = PullToRefreshIndicator.labelScale(
                        lineHeight = line.size.height / density.density.toDouble(),
                        textWidth = line.size.width / density.density.toDouble(),
                        availableWidth = constraints.maxWidth / density.density.toDouble(),
                    )
                    // Its own width, centred, and as wide again as it shrinks.
                    val placeable = measurable.measure(Constraints(maxWidth = (constraints.maxWidth / scale).toInt()))
                    layout(constraints.maxWidth, constraints.maxHeight) {
                        placeable.placeWithLayer((constraints.maxWidth - placeable.width) / 2, (constraints.maxHeight - placeable.height) / 2) {
                            scaleX = scale.toFloat()
                            scaleY = scale.toFloat()
                        }
                    }
                },
        ) {
            Text(text, color = color, style = style, maxLines = 1, softWrap = false, overflow = TextOverflow.Ellipsis)
        }
    }
}

/**
 * `PullToRefreshSeal`: the disc, the arrow turning with the pull and then spinning, the check or cross drawing itself
 * over it as the result comes, and a pop as each refresh starts.
 */
@Composable
private fun Seal(model: PullToRefreshModel, seal: PullToRefreshSeal, starts: Int, modifier: Modifier) {
    val colors = PeekTheme.colors
    val result by model.resultFlow.collectAsState()
    val pop by keyframeAnimator(1.0, starts) { PeekKeyframes.pullPop }
    val disc = colors.resolve(seal.disc)
    val glyph = colors.resolve(seal.glyphColor)
    val arrow = colors.resolve(seal.arrowColor)

    // A result comes on its curves, timed from its first frame; as it goes, everything is back at once.
    val since by produceState(0.0, result) {
        value = 0.0
        if (result == null) return@produceState
        var first = -1L
        while (value < RESULT_SECONDS) {
            withFrameNanos { now ->
                if (first < 0) first = now
                value = (now - first) / 1e9
            }
        }
    }
    val time = if (result == null) 0.0 else since
    val discMix = if (result == null) 1.0 else PullToRefreshSeal.discAnimation.value(time)
    val arrowOpacity = if (result == null) 1.0 else 1 - PullToRefreshSeal.arrowAnimation.value(time)
    val trim = if (result == null) 0.0 else PullToRefreshSeal.glyphDrawAnimation.value(time)
    val glyphOpacity = if (result == null) 0.0 else PullToRefreshSeal.glyphOpacity(time)
    val glyphScale = if (result == null) seal.glyphScale else PullToRefreshSeal.glyphScale(time, reduceMotion = !seal.pops)
    val previousDisc = colors.resolve(Brand.paper)
    val discColor = androidx.compose.ui.graphics.lerp(previousDisc, disc, discMix.toFloat().coerceIn(0f, 1f))

    val turn = rememberArrowTurn(seal)
    Box(
        modifier
            .size(PullToRefreshSeal.SIZE.dp)
            .graphicsLayer {
                val scale = if (seal.pops) pop.toFloat() else 1f
                scaleX = scale
                scaleY = scale
            },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            Modifier
                .size(PullToRefreshSeal.SIZE.dp)
                .dropShadow(CircleShape, SEAL_SHADOW)
                .drawBehind { drawCircle(discColor) },
        )
        Box(
            Modifier
                .size(PullToRefreshSeal.ARROW_SIZE.dp)
                .graphicsLayer {
                    rotationZ = Math.toDegrees(turn()).toFloat()
                    alpha = arrowOpacity.toFloat().coerceIn(0f, 1f)
                },
            contentAlignment = Alignment.Center,
        ) {
            PeekSymbol("arrow.clockwise", font = PeekTheme.type.fixed(ARROW_POINTS, FontWeight.Medium), tint = arrow)
        }
        val cross = seal.cross
        Canvas(
            Modifier
                .size(PullToRefreshSeal.GLYPH_SIZE.dp)
                .graphicsLayer {
                    alpha = glyphOpacity.toFloat()
                    scaleX = glyphScale.toFloat()
                    scaleY = glyphScale.toFloat()
                },
        ) {
            if (trim <= 0.0) return@Canvas
            val path = PullToRefreshGlyph.path(Rect(0.0, 0.0, size.width.toDouble(), size.height.toDouble()), cross).toComposePath()
            drawPath(
                trimmed(path, trim.toFloat()), glyph,
                style = Stroke(width = PullToRefreshSeal.glyphStroke.lineWidth.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
            )
        }
    }
}

/**
 * The arrow's turn in radians: with the pull while it isn't spinning, then a whole turn easing out of where it was and
 * steady turns after, read each frame where it is drawn.
 */
@Composable
private fun rememberArrowTurn(seal: PullToRefreshSeal): () -> Double {
    val pulled = PullToRefreshArrow.radians(seal.arrowDegrees)
    val spin = remember { SpinClock() }
    spin.pulled = pulled
    LaunchedEffect(seal.spinning) {
        if (!seal.spinning) {
            spin.start = null
            return@LaunchedEffect
        }
        spin.from = spin.pulled
        var first = -1L
        while (true) {
            withFrameNanos { now ->
                if (first < 0) first = now
                spin.start = first
                spin.now = now
            }
        }
    }
    return { spin.turn() }
}

@Stable
private class SpinClock {
    var pulled by mutableDoubleStateOf(0.0)
    var from = 0.0
    var start: Long? by mutableStateOf(null)
    var now by mutableLongStateOf(0L)

    fun turn(): Double {
        val began = start ?: return pulled
        return PullToRefreshArrow.spin(from, (now - began) / 1e9)
    }
}

/**
 * `.animation(.easeOut(duration: 0.22), value: model.phase)` for values that follow the pull: as the phase changes, each
 * eases from what it showed to what it is now over [PullToRefreshIndicator.phaseAnimation].
 */
@Stable
private class PhaseBlend {
    private val shown = DoubleArray(2)
    private val from = DoubleArray(2)
    private var progress by mutableDoubleStateOf(1.0)
    private var started = false

    suspend fun restart() {
        if (!started) {
            started = true
            return
        }
        shown.copyInto(from)
        progress = 0.0
        val curve = PullToRefreshIndicator.phaseAnimation
        var first = -1L
        while (progress < 1.0) {
            withFrameNanos { now ->
                if (first < 0) first = now
                val time = (now - first) / 1e9
                progress = if (time >= curve.duration) 1.0 else min(1.0, curve.value(time))
            }
        }
    }

    fun value(channel: Int, target: Double): Double {
        val value = from[channel] + (target - from[channel]) * progress
        shown[channel] = value
        return value
    }
}

/** `trim(from: 0, to: fraction)`: the first `fraction` of the path's length, across all its strokes. */
private fun trimmed(path: Path, fraction: Float): Path {
    if (fraction >= 1f) return path
    val source = path.asAndroidPath()
    val measure = android.graphics.PathMeasure(source, false)
    var total = 0f
    do {
        total += measure.length
    } while (measure.nextContour())
    measure.setPath(source, false)
    val out = android.graphics.Path()
    var left = total * fraction
    do {
        if (left <= 0f) break
        val length = measure.length
        measure.getSegment(0f, min(length, left), out, true)
        left -= length
    } while (measure.nextContour())
    return out.asComposePath()
}

private const val ARROW_POINTS = 13.5f

/** How long a result's curves run, the longest of them with its delay, in seconds. */
private const val RESULT_SECONDS = 1.0
private val SEAL_SHADOW = swiftUiShadow(
    Color.Black.copy(alpha = PullToRefreshSeal.SHADOW_OPACITY.toFloat()), radius = PullToRefreshSeal.SHADOW_RADIUS.dp, y = PullToRefreshSeal.SHADOW_Y.dp,
)

const val PULL_INDICATOR = "deliveries.pullIndicator"
