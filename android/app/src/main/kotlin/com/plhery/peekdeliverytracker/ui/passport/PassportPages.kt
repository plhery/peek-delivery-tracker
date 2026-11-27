package com.plhery.peekdeliverytracker.ui.passport

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.ExperimentalSurfaceShadow
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt

// The web's `PassportPages`: the passport's three pages on one track under their labels, the neighbour's edge showing.
// A label or a swipe turns the page; on a wide window the book lies open, two pages side by side.

/** The passport's pages, in order. */
internal object PassportPage {
    const val COUNTRIES = 0
    const val STAMPS = 1
    const val NUMBERS = 2
    const val COUNT = 3

    /** The first page in view: on an open book the spread holding [page], the first two or, for the last, the last two. */
    fun offset(page: Int, open: Boolean): Int = if (open) (if (page == COUNT - 1) COUNT - 2 else 0) else page

    /** The pages in view with [page] chosen. */
    fun visible(page: Int, open: Boolean): List<Int> = offset(page, open).let { if (open) listOf(it, it + 1) else listOf(it) }
}

/**
 * The pages' labels, a tab group named [name]: three equal labels in a capsule ringed by a line, the chosen one on a
 * yellow pill that slides to it (240 ms, `move`; at once under Reduce Motion). A label whose page has news not shown
 * yet carries the orange dot ([dots]), heard as "New" after its name.
 */
@Composable
internal fun PassportPageLabels(name: String, labels: List<String>, dots: List<Boolean>, page: Int, onPage: (Int) -> Unit, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val pill by animateFloatAsState(page.toFloat(), if (reduceMotion) tween(0) else tween(PILL_SLIDE, easing = PassportMove), label = "pill")
    val ring = colors.separator
    val soft = colors.yellowSoft
    Row(
        modifier
            .fillMaxWidth()
            .heightIn(min = LABEL_HEIGHT)
            .drawBehind {
                val radius = CornerRadius(size.height / 2)
                val line = 1.dp.toPx()
                drawRoundRect(ring, Offset(line / 2, line / 2), Size(size.width - line, size.height - line), CornerRadius(radius.x - line / 2), Stroke(line))
                val width = size.width / labels.size
                drawRoundRect(soft, Offset(pill * width, 0f), Size(width, size.height), radius)
            }
            .selectableGroup()
            .semantics {
                contentDescription = name
                collectionInfo = CollectionInfo(rowCount = 1, columnCount = labels.size)
            }
            .testTag(PASSPORT_PAGE_LABELS),
    ) {
        labels.forEachIndexed { index, label ->
            val selected = index == page
            Box(
                Modifier
                    .weight(1f)
                    .heightIn(min = LABEL_HEIGHT)
                    .testTag("$PASSPORT_PAGE_LABEL.$index")
                    .selectable(selected, interactionSource = null, indication = null, role = Role.Tab) { onPage(index) }
                    .semantics {
                        contentDescription = label
                        collectionItemInfo = CollectionItemInfo(rowIndex = 0, rowSpan = 1, columnIndex = index, columnSpan = 1)
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    label,
                    Modifier.padding(horizontal = 8.dp, vertical = 6.dp).clearAndSetSemantics {},
                    color = if (selected) colors.ink else colors.secondaryText,
                    style = type.fixed(type.scaled(13f, IosTextStyle.Footnote), FontWeight.SemiBold).sfLines(),
                    textAlign = TextAlign.Center,
                )
                TabDot(dots.getOrElse(index) { false }, ring = null, Modifier.align(Alignment.TopEnd).offset(x = (-12).dp, y = 9.dp), delay = LABEL_DOT_DELAY)
            }
        }
    }
}

/**
 * The pages' track: one sheet per page, [open] two in view, else one with its neighbour's edge showing, [page] chosen.
 * Its width runs 16 past the pages' on each side, for their shadows, and it clips there. The track follows a finger 1:1
 * (0.3 past the first and last page) and, let go, turns past a quarter of a page or a flick of 400 dp/s, settling in
 * `420 × what remains` (at least 180 ms, `settle`); a label turns it in 420 ms (`flip`), from where it stands. Under
 * Reduce Motion the track stays and the pages in view fade in over 200 ms; a swipe still turns it on release. The
 * pages out of view stand as tall as those in view, cut there; TalkBack and touches reach only those in view.
 */
@Composable
internal fun PassportTrack(open: Boolean, page: Int, onPage: (Int) -> Unit, modifier: Modifier = Modifier, sheets: List<@Composable () -> Unit>) {
    val reduceMotion = PeekTheme.reduceMotion
    val density = LocalDensity.current
    val last = sheets.size - 1
    val offset = PassportPage.offset(page, open)
    val lastOffset = if (open) last - 1 else last
    val position = remember { Animatable(offset.toFloat()) }
    val fade = remember { Animatable(1f) }
    val gesture = remember { TrackGesture() }
    var drag by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()
    val turn by rememberUpdatedState(onPage)
    LaunchedEffect(offset, reduceMotion) {
        val swiped = gesture.swiped
        gesture.swiped = false
        if (reduceMotion) {
            if (position.value == offset.toFloat()) return@LaunchedEffect
            position.snapTo(offset.toFloat())
            fade.snapTo(0f)
            fade.animateTo(1f, tween(REDUCED_FADE, easing = LinearEasing))
            return@LaunchedEffect
        }
        fade.snapTo(1f)
        val remaining = abs(position.value - offset)
        if (remaining == 0f) return@LaunchedEffect
        position.animateTo(offset.toFloat(), if (swiped) settle(remaining) else tween(FLIP, easing = PassportFlip))
    }
    val draggable = rememberDraggableState { delta ->
        gesture.raw += delta
        val against = (offset == 0 && gesture.raw > 0) || (offset == lastOffset && gesture.raw < 0)
        drag = if (reduceMotion) 0f else gesture.raw * (if (against) RESISTANCE else 1f)
    }
    Layout(
        content = {
            sheets.forEachIndexed { index, sheet ->
                val shown = index == offset || (open && index == offset + 1)
                PassportSheet(index, shown, Modifier.graphicsLayer { if (shown) alpha = fade.value }, sheet)
            }
        },
        modifier = modifier
            .clipToBounds()
            .draggable(
                draggable,
                Orientation.Horizontal,
                onDragStarted = {
                    gesture.raw = 0f
                    scope.launch { position.stop() }
                },
                onDragStopped = { velocity ->
                    val dx = gesture.raw
                    val step = gesture.step
                    // The track stays where the finger left it, then turns or settles back from there.
                    if (step > 0f) position.snapTo(position.value - drag / step)
                    drag = 0f
                    val speed = abs(velocity) / density.density
                    val commits = step > 0f && (abs(dx) >= step * COMMIT || (speed >= FLICK && velocity * dx > 0))
                    val target = (offset + if (dx < 0) 1 else -1).coerceIn(0, lastOffset)
                    if (commits && target != offset) {
                        gesture.swiped = true
                        turn(if (open) (if (target == lastOffset) last else 0) else target)
                    } else if (!reduceMotion && position.value != offset.toFloat()) {
                        position.animateTo(offset.toFloat(), settle(abs(position.value - offset)))
                    }
                },
            )
            .testTag(PASSPORT_TRACK),
    ) { measurables, constraints ->
        val width = constraints.maxWidth
        val margin = SHADOW_ROOM.roundToPx()
        val inner = max(0, width - 2 * margin)
        val gap = SHEET_GAP.roundToPx()
        val sheet = if (open) max(0, (inner - gap) / 2) else max(0, inner - PEEK.roundToPx())
        val step = sheet + gap
        gesture.step = step.toFloat()
        val shown = measurables.indices.filter { it == offset || (open && it == offset + 1) }
        val shownPlaceables = shown.associateWith {
            measurables[it].measure(Constraints(minWidth = sheet, maxWidth = sheet, minHeight = SHEET_MIN_HEIGHT.roundToPx()))
        }
        val height = shownPlaceables.values.maxOfOrNull { it.height } ?: SHEET_MIN_HEIGHT.roundToPx()
        val placeables = measurables.mapIndexed { index, measurable ->
            shownPlaceables[index] ?: measurable.measure(Constraints.fixed(sheet, height))
        }
        val top = TRACK_TOP.roundToPx()
        layout(width, top + height + TRACK_BOTTOM.roundToPx()) {
            placeables.forEachIndexed { index, placeable ->
                placeable.place(margin + ((index - position.value) * step + drag).roundToInt(), top)
            }
        }
    }
}

/** What a swipe on the track keeps between its events, none of it drawn: how far it has gone, the step, and whether it turned the page. */
private class TrackGesture {
    var raw = 0f
    var step = 0f
    var swiped = false
}

private fun settle(remaining: Float) = tween<Float>(max(SETTLE_MIN, (FLIP * remaining).roundToInt()), easing = PassportSettle)

/**
 * `passport-sheet`: a page on the paper, radius 20 with the card's shadow, in by 18 at its top, 14 at its sides and 34 at
 * its foot, its number small at the middle of its foot. Out of view ([shown] false) it is cut to its height, hidden from
 * TalkBack and takes no touch.
 */
@Composable
private fun PassportSheet(index: Int, shown: Boolean, modifier: Modifier, content: @Composable () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val shape = ContinuousRoundedShape(SHEET_RADIUS)
    Box(
        modifier
            .testTag("$PASSPORT_PAGE.$index")
            .dropShadow(shape, ExperimentalSurfaceShadow)
            .background(colors.paper, shape)
            .then(if (shown) Modifier.semantics { isTraversalGroup = true } else Modifier.clip(shape).clearAndSetSemantics {}),
    ) {
        Box(Modifier.wrapContentHeight(Alignment.Top, unbounded = true).padding(start = 14.dp, top = 18.dp, end = 14.dp, bottom = 34.dp)) {
            content()
        }
        Text(
            "${index + 1}",
            Modifier.align(Alignment.BottomCenter).padding(bottom = 12.dp).clearAndSetSemantics {},
            color = colors.secondaryText,
            style = type.fixed(type.scaled(11f, IosTextStyle.Caption2)).monospacedDigit().sfLines(),
        )
        // Out of view, the page takes no touch: a tap on its edge does nothing, as `inert` has it.
        if (!shown) {
            Box(
                Modifier.matchParentSize().pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) awaitPointerEvent()
                    }
                },
            )
        }
    }
}

private val LABEL_HEIGHT = 38.dp
private const val PILL_SLIDE = 240
private const val LABEL_DOT_DELAY = 200f
private const val FLIP = 420
private const val SETTLE_MIN = 180
private const val REDUCED_FADE = 200
private const val RESISTANCE = 0.3f
private const val COMMIT = 0.25f
private const val FLICK = 400f
private val SHEET_GAP = 10.dp

/** How much narrower than the track a lone page is: 18 of its neighbour shows beside the gap. */
private val PEEK = 28.dp
private val SHEET_RADIUS = 20.dp
private val SHEET_MIN_HEIGHT = 360.dp

/** Room round the pages for their shadows: 16 at each side, 2 over and 14 under. */
internal val SHADOW_ROOM = 16.dp
private val TRACK_TOP = 2.dp
private val TRACK_BOTTOM = 14.dp

/** The flip a page reached by a turn waits for before its news moves. */
internal const val PAGE_TURN = FLIP

/** The test tags of the pages' labels (then each one's place), the track and the pages (then each one's place). */
const val PASSPORT_PAGE_LABELS = "$PASSPORT.pageLabels"
const val PASSPORT_PAGE_LABEL = "$PASSPORT.pageLabel"
const val PASSPORT_TRACK = "$PASSPORT.track"
const val PASSPORT_PAGE = "$PASSPORT.page"
