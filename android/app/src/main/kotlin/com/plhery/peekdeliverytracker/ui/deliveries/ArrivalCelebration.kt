package com.plhery.peekdeliverytracker.ui.deliveries

import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.ParcelArrival
import com.plhery.peekdeliverytracker.ui.draw.ExperimentalSurfaceShadow
import com.plhery.peekdeliverytracker.ui.draw.SmallPip
import com.plhery.peekdeliverytracker.ui.theme.KeepMotion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.util.UUID
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlinx.coroutines.withContext

/**
 * `ParcelArrivalCelebration`: the card of the parcel just added, once the list has brought it to its middle
 * ([DeliveriesState.parcelBurst]). On the row as a whole, outside the card's swipe clipping so the little parcels can
 * leave its edge: the card wobbles on a linear 0.48 s (`ParcelArrivalEffect`) as little parcels fly out of its stamp,
 * `stubInset` in from its end ([ParcelAddedBurst]); under Reduce Motion it only brightens and back, on 0.75 s, the
 * one motion iOS keeps for it ([KeepMotion]). It stands as it was at once as the arrival ends, which the card leaving
 * the list does, if the arrival is still its own.
 *
 * `modifier` goes outside the effect, as the list's `place(of:)` comes after `arrivalCelebration(for:)`: the card's
 * place in the list is where it stands, not where it wobbles, and the card kept unseen while its picture flies keeps
 * the little parcels unseen with it.
 */
@Composable
internal fun ArrivalCelebration(
    state: DeliveriesState,
    parcelId: UUID,
    modifier: Modifier = Modifier,
    stubInset: Double = ParcelArrival.STUB_INSET,
    content: @Composable () -> Unit,
) {
    val active = state.celebrates(parcelId)
    val reduceMotion = PeekTheme.reduceMotion
    val progress = remember { Animatable(0f) }
    if (active.value) {
        LaunchedEffect(Unit) {
            val duration = if (reduceMotion) ParcelArrival.REDUCED_WOBBLE_DURATION else ParcelArrival.WOBBLE_DURATION
            progress.snapTo(0f)
            withContext(KeepMotion) { progress.animateTo(1f, tween((duration * 1000).roundToInt(), easing = LinearEasing)) }
        }
    }
    DisposableEffect(state, parcelId) { onDispose { state.burstFinished(parcelId) } }
    Box(modifier.arrivalEffect(active, progress, reduceMotion)) {
        content()
        if (active.value) ParcelAddedBurst(parcelId, stubInset, Modifier.matchParentSize()) { state.burstFinished(parcelId) }
    }
}

/** Whether the arrival plays on the card of `parcelId`: a card is drawn again only as that changes. */
@Composable
private fun DeliveriesState.celebrates(parcelId: UUID): State<Boolean> =
    remember(this, parcelId) { derivedStateOf { parcelBurst == parcelId } }

/**
 * `ParcelArrivalEffect` at the wobble's progress, read as the layer is drawn, while the arrival plays: SwiftUI's offset
 * turned and scaled with the card, as its rotation and scale come after it, the turn and the scale about the card's
 * middle. Under Reduce Motion only the brightness, which clips nothing on the iPhone: what the row draws past its
 * bounds, Next up's shadow, is drawn brightened in a layer that reaches it ([BRIGHTNESS_REACH]), where a layer's own
 * colour filter would draw it only within the row.
 */
private fun Modifier.arrivalEffect(active: State<Boolean>, progress: Animatable<Float, AnimationVector1D>, reduced: Boolean): Modifier =
    graphicsLayer {
        if (reduced || !active.value) return@graphicsLayer
        val shown = progress.value.toDouble()
        val offset = ParcelArrival.offsetX(shown, reduced) * density
        val degrees = ParcelArrival.rotation(shown, reduced)
        val scale = ParcelArrival.scale(shown, reduced)
        val radians = degrees * PI / 180
        translationX = (offset * scale * cos(radians)).toFloat()
        translationY = (offset * scale * sin(radians)).toFloat()
        rotationZ = degrees.toFloat()
        scaleX = scale.toFloat()
        scaleY = scale.toFloat()
    }.drawWithContent {
        val paint = if (reduced && active.value) brightnessPaint(ParcelArrival.brightness(progress.value.toDouble(), true)) else null
        if (paint == null) {
            drawContent()
            return@drawWithContent
        }
        val canvas = drawContext.canvas.nativeCanvas
        val reach = BRIGHTNESS_REACH.toPx()
        val saved = canvas.saveLayer(-reach, -reach, size.width + reach, size.height + reach, paint)
        drawContent()
        canvas.restoreToCount(saved)
    }

/**
 * How far past the row its brightened layer reaches: Next up's shadow ([ExperimentalSurfaceShadow]) has faded out
 * within twice its blur radius past its offset.
 */
private val BRIGHTNESS_REACH: Dp = with(ExperimentalSurfaceShadow) { radius * 2 + spread + maxOf(abs(offset.x.value), abs(offset.y.value)).dp }

/**
 * `.brightness(_:)`: `amount` added to each of red, green and blue, before the colour is premultiplied, in the
 * screen's own steps of 1/255. Each step's paint is made once, so the pulse makes nothing new frame after frame; none
 * at 0.
 */
private fun brightnessPaint(amount: Double): Paint? {
    val step = (amount * 255).roundToInt().coerceIn(0, 255)
    if (step == 0) return null
    return brightnessPaints[step] ?: run {
        val add = step.toFloat()
        Paint().apply { colorFilter = ColorMatrixColorFilter(floatArrayOf(1f, 0f, 0f, 0f, add, 0f, 1f, 0f, 0f, add, 0f, 0f, 1f, 0f, add, 0f, 0f, 0f, 1f, 0f)) }
            .also { brightnessPaints[step] = it }
    }
}

private val brightnessPaints = arrayOfNulls<Paint>(256)

/**
 * `ParcelAddedBurst`: six small Pips fly, unevenly, out of the card's stamp, `stubInset` in from its end, and follow it
 * as the list scrolls; they take no touches and TalkBack never reaches them. Drawn a frame at a time from the time
 * since the burst's first frame, each Pip drawn once and moved on its own layer; over 0.95 s after that frame. Under
 * Reduce Motion nothing flies and it is over after 0.75 s; Reduce Motion changing meanwhile ends it.
 */
@Composable
private fun ParcelAddedBurst(parcelId: UUID, stubInset: Double, modifier: Modifier, onFinished: () -> Unit) {
    val reduceMotion = PeekTheme.reduceMotion
    val finished by rememberUpdatedState(onFinished)
    val burst = remember { ParcelBurstFrame(stubInset) }
    val reducedAtStart = remember { reduceMotion }
    LocalParcelBursts.current?.let { told -> DisposableEffect(burst) { told(parcelId, burst); onDispose {} } }
    LaunchedEffect(burst) {
        val duration = if (reducedAtStart) ParcelArrival.REDUCED_BURST_DURATION else ParcelArrival.BURST_DURATION
        do {
            withFrameNanos { now ->
                if (burst.started < 0) burst.started = now
                burst.seconds = (now - burst.started) / 1e9
            }
        } while (burst.seconds < duration)
        finished()
    }
    LaunchedEffect(reduceMotion) { if (reduceMotion != reducedAtStart) finished() }
    if (reduceMotion) return
    Layout(
        content = { for (pip in ParcelArrival.pips) SmallPip(Modifier.width(pip.width.dp)) },
        modifier = modifier.testTag(PARCEL_BURST).clearAndSetSemantics {},
    ) { measurables, constraints ->
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        val pips = measurables.map { it.measure(Constraints()) }
        layout(width, height) {
            pips.forEachIndexed { index, pip -> pip.placeWithLayer(0, 0) { with(burst) { place(index, pip, width, height) } } }
        }
    }
}

/**
 * A burst as it draws: the time since its first frame on the frame clock, and where each little parcel was last put,
 * in points on its card ([ParcelArrival.pose]). The tests read it ([LocalParcelBursts]).
 */
@Stable
internal class ParcelBurstFrame(val stubInset: Double) {
    /** The frame clock's time of the burst's first frame, in nanoseconds; -1 before it. */
    var started = -1L

    var seconds: Double by mutableDoubleStateOf(0.0)

    val poses = Array(ParcelArrival.pips.size) { ParcelArrival.PipPose() }

    /** The card's size in points, as the burst was last laid out over it. */
    var width = 0.0
        private set
    var height = 0.0
        private set

    /** The little parcel at `index`, `pip` laid out at the card's top left, moved to its pose on its layer. */
    fun GraphicsLayerScope.place(index: Int, pip: Placeable, cardWidth: Int, cardHeight: Int) {
        width = cardWidth / density.toDouble()
        height = cardHeight / density.toDouble()
        val pose = ParcelArrival.pose(ParcelArrival.pips[index], seconds, width, height, stubInset, poses[index])
        translationX = (pose.x * density).toFloat() - pip.width / 2f
        translationY = (pose.y * density).toFloat() - pip.height / 2f
        rotationZ = pose.rotation.toFloat()
        scaleX = pose.scale.toFloat()
        scaleY = pose.scale.toFloat()
        alpha = pose.opacity.toFloat()
    }
}

/** Told each burst as it starts, with its parcel's id: the tests read where its little parcels fly. */
internal val LocalParcelBursts = staticCompositionLocalOf<((UUID, ParcelBurstFrame) -> Unit)?> { null }

/** The little parcels of a card's arrival, while they fly. */
const val PARCEL_BURST = "deliveries.parcelBurst"
