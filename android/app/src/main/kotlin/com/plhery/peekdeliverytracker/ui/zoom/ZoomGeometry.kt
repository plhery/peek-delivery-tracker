package com.plhery.peekdeliverytracker.ui.zoom

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.lerp
import com.plhery.peekdeliverytracker.ui.theme.RubberBandOverscroll
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sign

/**
 * The geometry of the iPhone's zoom from a card to its page (`.navigationTransition(.zoom(sourceID:in:))`), as its
 * captures measure it, in the window's pixels.
 *
 * A flight's progress runs from 0, the page in its source's frame, to 1, the page over the whole window. The page's
 * container goes from one frame to the other with each edge on a straight line ([frame]), its corners from the
 * source's radius to the display's. The page keeps its layout: it is drawn at the window's size, scaled by the
 * container's width over the window's, from the container's top left, and cut to the container. The source's picture
 * is drawn the same way at the container's top, scaled by the container's width over its own, and gives way to the
 * page over the first half ([pageOpacity]). What is under the page recedes to 92 % about the window's middle and dims
 * to black at 15 % ([receded], [dim]); the page casts a shadow of black at 40 %, a Gaussian 30 points wide.
 */
internal object ZoomGeometry {
    /** The size of what is under a page over it, about the window's middle. */
    const val RECEDED_SCALE = 0.92f

    /** The black over what is under a page, at most. */
    const val DIM = 0.15f

    /** The page's shadow: black at this opacity, a Gaussian of this deviation in dp, with no offset. */
    const val SHADOW_OPACITY = 0.4f
    const val SHADOW_DEVIATION = 30f

    /** The share of a flight over which the source's picture gives way to the page. */
    const val CROSSFADE = 0.5f

    /** Dragged down, the page loses its whole size over this many window heights (s = 1 - 0.00059 × top on 956). */
    const val DRAG_SHRINK = 1.55f

    /** The smallest a dragged page gets; the captures show none down to 0.725. */
    const val SMALLEST_DRAG_SCALE = 0.4f

    /** Material's predictive back for a container filling the window: down to 90 %, short of the edges by 8 dp. */
    const val BACK_SCALE = 0.9f
    const val BACK_EDGE_GAP = 8f
    const val BACK_LIFT = 24f

    /**
     * Where a drag let go would come to rest, as UIScrollView projects one at its normal deceleration (0.998 a
     * millisecond): the speed in pixels a second times 0.499 s.
     */
    const val PROJECTION = 0.998f / (1 - 0.998f) / 1000

    /** Let go past a quarter of the window's height, or flung there, a drag closes the page. */
    const val DISMISS_SHARE = 0.25f

    /** A card of the list, in dp, for a page whose card can't be seen: the list's column less its margins. */
    const val FALLBACK_WIDTH = 628f
    const val FALLBACK_HEIGHT = 100f
    const val FALLBACK_MARGIN = 16f

    /** The container `progress` of the way from `small` (the source) to `large` (the page). */
    fun frame(small: Rect, large: Rect, progress: Float): Rect = lerp(small, large, progress)

    fun cornerRadius(small: Float, large: Float, progress: Float): Float = small + (large - small) * progress

    /** How much of the page shows over the source's picture: all of it once the flight is half way. */
    fun pageOpacity(progress: Float): Float = (progress / CROSSFADE).coerceIn(0f, 1f)

    fun faceOpacity(progress: Float): Float = 1f - pageOpacity(progress)

    /** The size of what is under the page, `behind` (0 to 1) of the way to being covered. */
    fun receded(behind: Float): Float = 1f - (1f - RECEDED_SCALE) * behind.coerceIn(0f, 1f)

    fun dim(behind: Float): Float = DIM * behind.coerceIn(0f, 1f)

    /** The page's scale dragged `down` pixels in a window `height` tall. */
    fun dragScale(down: Float, height: Float): Float =
        if (height <= 0f) 1f else max(SMALLEST_DRAG_SCALE, 1f - max(down, 0f) / (DRAG_SHRINK * height))

    /**
     * The page dragged by `translation` from where the finger took it, `anchor`: it follows the finger both ways and,
     * as it goes down, shrinks about the finger, which stays on the same point of the page. Up, it only gives a
     * little, as a scroll view past its end does.
     */
    fun dragFrame(page: Rect, anchor: Offset, translation: Offset): Rect {
        val height = page.height
        val scale = dragScale(translation.y, height)
        val dy = if (translation.y < 0f) -RubberBandOverscroll.band(-translation.y, height.roundToInt()) else translation.y
        return Rect(
            anchor.x + (page.left - anchor.x) * scale + translation.x,
            anchor.y + (page.top - anchor.y) * scale + dy,
            anchor.x + (page.right - anchor.x) * scale + translation.x,
            anchor.y + (page.bottom - anchor.y) * scale + dy,
        )
    }

    /**
     * The page as the predictive back gesture has it, `progress` of the way: shrunk about its middle, moved away from
     * the edge the swipe came from, and up or down with the finger, never closer than `edgeGap` to the window's edges
     * nor more than `lift` up or down (pixels both).
     */
    fun backFrame(page: Rect, progress: Float, fromLeft: Boolean, touchDeltaY: Float, edgeGap: Float, lift: Float): Rect {
        val amount = progress.coerceIn(0f, 1f)
        val scale = 1f - (1f - BACK_SCALE) * amount
        val width = page.width * scale
        val height = page.height * scale
        val roomX = max(0f, (page.width - page.width * BACK_SCALE) / 2 - edgeGap)
        val shiftX = roomX * amount * if (fromLeft) 1f else -1f
        val roomY = min(max(0f, (page.height - height) / 2 - edgeGap), lift)
        val shiftY = if (page.height <= 0f) 0f else sign(touchDeltaY) * roomY * min(1f, abs(touchDeltaY) / page.height)
        val left = page.center.x - width / 2 + shiftX
        val top = page.center.y - height / 2 + shiftY
        return Rect(left, top, left + width, top + height)
    }

    /** Whether a drag let go `distance` down, moving down at `velocity` pixels a second, closes the page. */
    fun dismisses(distance: Float, velocity: Float, height: Float): Boolean =
        distance > 0f && distance + velocity * PROJECTION > height * DISMISS_SHARE

    /**
     * How fast the flight back starts, in progress a second, for a page let go moving down at `velocity`: the
     * container's top keeps the finger's speed toward its card, and never sets off away from it.
     */
    fun closingVelocity(small: Rect, large: Rect, velocity: Float): Float {
        val span = large.top - small.top
        if (abs(span) < 1f) return 0f
        return (velocity / span).coerceIn(-MAX_CLOSING_RATE, 0f)
    }

    private const val MAX_CLOSING_RATE = 12f

    /**
     * Where a page whose source can't be seen comes from and goes back to: a card of the list, centred in the window
     * clear of its bars (`safe`), in the wider side of a hinge splitting it (`hinge`, its left and right). All in
     * pixels, `density` pixels to the dp.
     */
    fun fallback(safe: Rect, density: Float, hinge: ClosedFloatingPointRange<Float>? = null): Rect {
        var area = safe
        if (hinge != null && hinge.start > safe.left && hinge.endInclusive < safe.right) {
            area = if (hinge.start - safe.left >= safe.right - hinge.endInclusive) {
                Rect(safe.left, safe.top, hinge.start, safe.bottom)
            } else {
                Rect(hinge.endInclusive, safe.top, safe.right, safe.bottom)
            }
        }
        val width = max(0f, min(FALLBACK_WIDTH * density, area.width - 2 * FALLBACK_MARGIN * density))
        val height = min(FALLBACK_HEIGHT * density, area.height)
        val center = area.center
        return Rect(center.x - width / 2, center.y - height / 2, center.x + width / 2, center.y + height / 2)
    }
}
