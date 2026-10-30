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

    /** The page's bar, pinned at the window's top, comes in over this stretch of an opening's progress ([openingBar]). */
    const val BAR_OPENING_START = 0.82f
    const val BAR_OPENING_END = 0.97f

    /**
     * Closing, in seconds from the moment it sets off ([closingBar]): the page's trailing items stay this long, then go
     * over this long, and the root's items come back over this long once they have gone.
     */
    const val BAR_TRAILING_HOLD = 0.05f
    const val BAR_TRAILING_FADE = 0.02f
    const val BAR_ROOT_FADE = 0.17f
    const val BAR_CLOSING_TIME = BAR_TRAILING_HOLD + BAR_TRAILING_FADE + BAR_ROOT_FADE

    /** The root's title comes back as a gesture shrinks the page from this scale, all of it this much smaller. */
    const val TITLE_SCALE = 0.826f
    const val TITLE_SCALE_SPAN = 0.27f

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

    /**
     * The bars' items on an opening `progress` of the way, which set off `from` progress `fromProgress`: the page's ‹
     * and ••• come in from there as it lands, between 82 % and 97 % of a whole opening, while the root's items and its
     * title fade with the page's progress.
     */
    fun openingBar(from: ZoomBar, fromProgress: Float, progress: Float): ZoomBar {
        val start = max(BAR_OPENING_START, fromProgress)
        val end = if (start < BAR_OPENING_END) BAR_OPENING_END else 1f
        val coming = if (start >= end) 1f else ((progress - start) / (end - start)).coerceIn(0f, 1f)
        val going = if (fromProgress >= 1f) 1f else ((progress - fromProgress) / (1f - fromProgress)).coerceIn(0f, 1f)
        return ZoomBar(
            leading = from.leading + (1f - from.leading) * coming,
            trailing = from.trailing + (1f - from.trailing) * coming,
            root = from.root * (1f - going),
            title = from.title * (1f - going),
        )
    }

    /**
     * The bars' items `elapsed` seconds into a closing `progress` of the way, which set off `from` progress
     * `fromProgress`, as the iPhone's navigation bar hands its items back: the page's ‹ goes at once, its ••• a moment
     * later, and only then do the root's items come back; the root's title comes back with the page's progress. The
     * iPhone morphs the + capsule into ‹ and the account's into ••• here, a drag widening the ‹ capsule and its symbols
     * swapping through a blur; that morph isn't drawn.
     */
    fun closingBar(from: ZoomBar, fromProgress: Float, progress: Float, elapsed: Float): ZoomBar {
        val trailing = from.trailing * (1f - ((elapsed - BAR_TRAILING_HOLD) / BAR_TRAILING_FADE).coerceIn(0f, 1f))
        val rootStart = if (from.trailing > 0f) BAR_TRAILING_HOLD + BAR_TRAILING_FADE else 0f
        val coming = ((elapsed - rootStart) / BAR_ROOT_FADE).coerceIn(0f, 1f)
        val going = if (fromProgress <= 0f) 1f else 1f - (progress / fromProgress).coerceIn(0f, 1f)
        return ZoomBar(
            leading = 0f,
            trailing = trailing,
            root = from.root + (1f - from.root) * coming,
            title = from.title + (1f - from.title) * going,
        )
    }

    /** How much of the root's title shows as a gesture holds the page at `scale`: none until it is 82.6 % of its size. */
    fun titleUncovered(scale: Float): Float = ((TITLE_SCALE - scale) / TITLE_SCALE_SPAN).coerceIn(0f, 1f)

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
