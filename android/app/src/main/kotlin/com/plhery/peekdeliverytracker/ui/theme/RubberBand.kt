package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.OverscrollFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.Velocity
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.sign
import kotlin.math.sqrt
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/** Every scrolling container bounces at its ends as an iPhone scroll view does, in place of Android's stretch. */
data object RubberBandOverscrollFactory : OverscrollFactory {
    override fun createOverscrollEffect(): OverscrollEffect = RubberBandOverscroll()
}

/**
 * UIScrollView's bounce. Dragged past an end, the content follows the finger less and less, never further than the
 * container's own length; let go, or flung into an end, it springs back. The numbers are the ones Ilya Lobanov
 * measured on UIScrollView (github.com/super-ultra/ScrollMechanics): the rubber band
 * `(1 - 1 / (x × 0.55 / d + 1)) × d` for a finger `x` past the end of a container `d` long, and a critically damped
 * spring of stiffness 100 for the return. The spring runs on frame time, so it bounces with animations off too, as an
 * iPhone's scroll views do under Reduce Motion. A fling that brings content back from past its end carries on into
 * the content.
 */
class RubberBandOverscroll : OverscrollEffect {
    /** How far the content is shown past its end, in pixels. */
    var offset by mutableStateOf(Offset.Zero)
        private set

    private var size = IntSize.Zero

    /** Bumped by every touch, so a bounce under way stops where the finger catches it. */
    private var generation = 0

    override val isInProgress: Boolean get() = offset != Offset.Zero

    override fun applyToScroll(delta: Offset, source: NestedScrollSource, performScroll: (Offset) -> Offset): Offset {
        // A fling's leftover is taken as a speed in applyToFling.
        if (source != NestedScrollSource.UserInput || size == IntSize.Zero) return performScroll(delta)
        generation++
        val pastX = finger(offset.x, size.width)
        val pastY = finger(offset.y, size.height)
        // Moving back toward the content first takes back what is past the end.
        val backX = giveBack(pastX, delta.x)
        val backY = giveBack(pastY, delta.y)
        val toScroll = Offset(delta.x - backX, delta.y - backY)
        val scrolled = performScroll(toScroll)
        val left = toScroll - scrolled
        offset = Offset(band(pastX + backX + left.x, size.width), band(pastY + backY + left.y, size.height))
        return delta
    }

    /**
     * Let go. Compose's `performFling` hands the fling to the container and whatever nests it, such as the list's pull,
     * and answers what of it they took: it runs exactly once, so that they always hear the finger lift. Past an end,
     * the content springs back first, and the fling carries on with the speed it has as it comes back into the
     * content; one that doesn't come back springs home with the whole throw while the fling goes on with none of it.
     * What the content couldn't take throws it past the end it reached, and it springs back.
     */
    override suspend fun applyToFling(velocity: Velocity, performFling: suspend (Velocity) -> Velocity) {
        var speed = velocity
        if (offset != Offset.Zero) {
            if (!comesBack(offset.x, velocity.x) && !comesBack(offset.y, velocity.y)) {
                coroutineScope {
                    launch(start = CoroutineStart.UNDISPATCHED) { performFling(Velocity.Zero) }
                    bounce(velocity)
                }
                return
            }
            // A touch that caught it drags anew, and its own fling comes as it lifts.
            speed = bounce(velocity) ?: return
        }
        val left = speed - performFling(speed)
        if (left != Velocity.Zero) bounce(left)
    }

    override val node: DelegatableNode = object : Modifier.Node(), LayoutModifierNode {
        override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
            val placeable = measurable.measure(constraints)
            size = IntSize(placeable.width, placeable.height)
            return layout(placeable.width, placeable.height) {
                placeable.placeWithLayer(0, 0) {
                    translationX = offset.x
                    translationY = offset.y
                }
            }
        }
    }

    /**
     * Springs the content from where it is shown back to its end, leaving at `velocity`. Returns the speed it has when
     * it reaches the end while still heading into the content, for the content to carry on with; none once it came to
     * rest at the end; null if a touch caught it.
     */
    private suspend fun bounce(velocity: Velocity): Velocity? {
        val run = ++generation
        val from = offset
        // The way each axis is past its end; a bounce from the end itself heads out first, the way it is flung.
        val outward = Offset(direction(from.x, velocity.x), direction(from.y, velocity.y))
        var start = -1L
        while (true) {
            val now = withFrameNanos { it }
            if (generation != run) return null
            if (start < 0) start = now
            val time = (now - start) / 1e9
            val x = spring(from.x, velocity.x, time)
            val y = spring(from.y, velocity.y, time)
            val crossedX = x * outward.x < 0
            val crossedY = y * outward.y < 0
            if (crossedX || crossedY) {
                offset = Offset.Zero
                return Velocity(
                    if (crossedX) speed(from.x, velocity.x, time) else 0f,
                    if (crossedY) speed(from.y, velocity.y, time) else 0f,
                )
            }
            val resting = abs(x) < REST && abs(y) < REST &&
                abs(speed(from.x, velocity.x, time)) < REST_SPEED && abs(speed(from.y, velocity.y, time)) < REST_SPEED
            if (resting) {
                offset = Offset.Zero
                return Velocity.Zero
            }
            offset = Offset(x, y)
        }
    }

    companion object {
        /** UIScrollView's rubber band coefficient. */
        const val RESISTANCE = 0.55f

        /** The return spring's stiffness, unit mass, critically damped. */
        const val STIFFNESS = 100f

        private val FREQUENCY = sqrt(STIFFNESS)
        private const val REST = 0.5f
        private const val REST_SPEED = 10f

        /** How far the content shows past its end for a finger `past` beyond it, in a container `length` long. */
        fun band(past: Float, length: Int): Float {
            if (length <= 0 || past == 0f) return 0f
            val extent = abs(past)
            return sign(past) * (1 - 1 / (extent * RESISTANCE / length + 1)) * length
        }

        /** The finger distance that shows the content `shown` past its end: [band] undone. */
        fun finger(shown: Float, length: Int): Float {
            if (length <= 0 || shown == 0f) return 0f
            val extent = abs(shown).coerceAtMost(length * 0.999f)
            return sign(shown) * length * (1 / (1 - extent / length) - 1) / RESISTANCE
        }

        /** The part of `delta` that takes `past` back toward the end, no further. */
        private fun giveBack(past: Float, delta: Float): Float = when {
            past == 0f || past.sign == delta.sign -> 0f
            abs(delta) > abs(past) -> -past
            else -> delta
        }

        private fun direction(shown: Float, velocity: Float): Float = if (shown != 0f) shown.sign else velocity.sign

        /** Whether the spring from `shown` past an end, leaving at `velocity`, comes back past the end into the content. */
        private fun comesBack(shown: Float, velocity: Float): Boolean = shown * (velocity + FREQUENCY * shown) < 0

        /** The critically damped spring's position `time` seconds after leaving `from` at `velocity`, toward 0. */
        private fun spring(from: Float, velocity: Float, time: Double): Float =
            ((from + (velocity + FREQUENCY * from) * time) * exp(-FREQUENCY * time)).toFloat()

        private fun speed(from: Float, velocity: Float, time: Double): Float =
            ((velocity - FREQUENCY * (velocity + FREQUENCY * from) * time) * exp(-FREQUENCY * time)).toFloat()
    }
}
