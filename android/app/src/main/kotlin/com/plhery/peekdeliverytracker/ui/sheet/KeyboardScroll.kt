package com.plhery.peekdeliverytracker.ui.sheet

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.onFocusedBoundsChanged
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first

/**
 * What scrolls in a sheet that has fields, as a SwiftUI `ScrollView` or `Form` keeps clear of the keyboard: room at its
 * foot for the part of the keyboard over it, less the [bottom] its content leaves there already (the navigation bar's,
 * [sheetBottomInset]), so that the last field can scroll above it; and the field with the focus in view, between [top]
 * from the viewport's top (under a bar) and the keyboard, as the focus moves, as the keyboard comes and as the viewport
 * grows or shrinks (a button over the keyboard under it, a turn). The scroll's own, not the reader's: the sheet stays.
 * With [dismissesKeyboard], a drag of the content down takes the keyboard down with the finger
 * (`.scrollDismissesKeyboard(.interactively)`), through [rememberKeyboardDrag]. [modifier] is the viewport's,
 * [contentModifier] is what scrolls in it (its padding). The room isn't counted in what a fitted sheet measures it for.
 */
@Composable
internal fun KeyboardScrollColumn(
    modifier: Modifier = Modifier,
    scroll: ScrollState = rememberScrollState(),
    top: Dp = 0.dp,
    bottom: Dp = 0.dp,
    dismissesKeyboard: Boolean = false,
    contentModifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable ColumnScope.() -> Unit,
) {
    val density = LocalDensity.current
    val keyboard = remember { SheetKeyboard() }
    val ime = WindowInsets.ime
    val reduceMotion = PeekTheme.reduceMotion
    val drag = if (dismissesKeyboard) rememberKeyboardDrag() else null
    LaunchedEffect(keyboard, top, reduceMotion, scroll) {
        val clearance = with(density) { top.toPx() }
        snapshotFlow { Triple(keyboard.focused, keyboard.covered(ime.getBottom(density)), keyboard.height) }.collectLatest { (_, covered, _) ->
            reachTheFocusedField(
                offset = { keyboard.focusedOffset(clearance, covered) },
                scrollBy = { if (reduceMotion) scroll.scrollBy(it) else scroll.animateScrollBy(it) },
                roomAhead = { scroll.value < scroll.maxValue },
            )
        }
    }
    Column(
        modifier
            .onGloballyPositioned { keyboard.place(it) }
            .then(if (drag != null) Modifier.keyboardDrag(drag) else Modifier)
            .verticalScroll(scroll)
            .onFocusedBoundsChanged { keyboard.focused = it }
            .then(KeyboardRoom(keyboard, ime, bottom))
            .then(contentModifier),
        verticalArrangement = verticalArrangement,
        content = content,
    )
}

/**
 * Scrolls by the [offset] that puts the focused field in its place until there is none left. A scroll down that stops
 * short has met the scroll's end before the keyboard's room under the content was laid out (the room comes in a layout
 * after the keyboard is known, before or after the scroll's last frame hands back), so it goes on once there is
 * [roomAhead]; one up that stops short has met the top, and stays.
 */
internal suspend fun reachTheFocusedField(offset: () -> Float, scrollBy: suspend (Float) -> Float, roomAhead: () -> Boolean) {
    while (true) {
        val wanted = offset()
        if (abs(wanted) < 0.5f) return
        val moved = scrollBy(wanted)
        if (wanted < 0f || abs(wanted - moved) < 0.5f) return
        snapshotFlow(roomAhead).first { it }
    }
}

/**
 * The navigation bar's height where the sheet reaches the window's bottom ([SheetInsets.reachesBottom]), which what it
 * holds ends clear of; none on a regular window's form or page sheet, or a popover. What asks for it keeps clear of the
 * bar itself, its ground and what scrolls going on under it, so the sheet leaves it be ([SheetFoot]).
 */
@Composable
internal fun sheetBottomInset(): Dp {
    if (!LocalSheetInsets.current.reachesBottom) return 0.dp
    SheetGoesUnderTheBar()
    val density = LocalDensity.current
    return with(density) { WindowInsets.navigationBars.getBottom(this).toDp() }
}

/**
 * Tells the sheet from the window's bottom that holds this that what it holds goes on under the navigation bar as it
 * is, as a web page does: the sheet leaves its foot be ([SheetFoot]).
 */
@Composable
internal fun SheetGoesUnderTheBar() {
    LocalSheetFoot.current?.let { if (!it.clearsTheBar) it.clearsTheBar = true }
}

/**
 * What a sheet from the window's bottom knows of what it holds at its foot: whether it keeps clear of the navigation bar
 * itself ([sheetBottomInset]). If it doesn't, the sheet holds it above the bar ([sheetFootRoom]), so that nothing it
 * holds, a button at its foot above all, stands in the bar: with three buttons the bar is 48 tall.
 */
@Stable
internal class SheetFoot {
    var clearsTheBar by mutableStateOf(false)
}

/** The foot of the sheet from the window's bottom that holds this; none outside one, or in a regular window's form. */
internal val LocalSheetFoot = staticCompositionLocalOf<SheetFoot?> { null }

/**
 * What a sheet from the window's bottom holds, above the navigation bar [bar] unless it keeps clear of the bar itself
 * ([SheetFoot.clearsTheBar]); the room under it counts in what a fitted sheet measures it for.
 */
internal fun Modifier.sheetFootRoom(foot: SheetFoot, bar: WindowInsets): Modifier = this.then(SheetFootRoom(foot, bar))

private data class SheetFootRoom(val foot: SheetFoot, val bar: WindowInsets) : ModifierNodeElement<SheetFootRoomNode>() {
    override fun create() = SheetFootRoomNode(foot, bar)

    override fun update(node: SheetFootRoomNode) {
        node.foot = foot
        node.bar = bar
    }
}

private class SheetFootRoomNode(var foot: SheetFoot, var bar: WindowInsets) : Modifier.Node(), LayoutModifierNode {
    private fun Density.room(): Int = if (foot.clearsTheBar) 0 else bar.getBottom(this)

    override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
        val room = room()
        val placeable = measurable.measure(constraints.offset(vertical = -room))
        return layout(placeable.width, (placeable.height + room).coerceAtMost(constraints.maxHeight)) { placeable.place(0, 0) }
    }

    override fun IntrinsicMeasureScope.minIntrinsicHeight(measurable: IntrinsicMeasurable, width: Int) = measurable.minIntrinsicHeight(width) + room()

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(measurable: IntrinsicMeasurable, width: Int) = measurable.maxIntrinsicHeight(width) + room()
}

/**
 * Where a keyboard leaves the viewport room: the part of it the keyboard covers, from where it stands in the window (on
 * a sheet that floats and is drawn smaller, or a regular window's form or page sheet), and the field with the
 * focus, which [focusedOffset] finds a place for in what is left of the scroll.
 */
@Stable
private class SheetKeyboard {
    /** The window's pixels under the viewport's foot, and the viewport's pixels to each of the window's. */
    private var below by mutableFloatStateOf(0f)
    private var scale by mutableFloatStateOf(1f)

    /** The viewport's height. */
    var height by mutableIntStateOf(0)
        private set

    /** The field with the focus, or null. */
    var focused: LayoutCoordinates? by mutableStateOf(null)

    /** Where the content scrolls. */
    private var viewport: LayoutCoordinates? = null

    fun place(viewport: LayoutCoordinates) {
        this.viewport = viewport
        if (!viewport.isAttached) return
        height = viewport.size.height
        val root = viewport.findRootCoordinates()
        val top = root.localPositionOf(viewport, Offset.Zero).y
        val foot = root.localPositionOf(viewport, Offset(0f, viewport.size.height.toFloat())).y
        below = root.size.height - foot
        scale = if (foot - top > 0f) viewport.size.height / (foot - top) else 1f
    }

    /** The viewport's pixels a keyboard [ime] pixels tall covers, at its foot. */
    fun covered(ime: Int): Int = if (ime <= 0) 0 else ((ime - below) * scale).roundToInt().coerceAtLeast(0)

    /**
     * How far the content scrolls for the focused field to stand between [top] pixels from the viewport's top and the
     * keyboard, which covers [covered] pixels at its foot: none where it stands there already; its top at [top] where it
     * is taller than the room.
     */
    fun focusedOffset(top: Float, covered: Int): Float {
        val field = focused?.takeIf { it.isAttached } ?: return 0f
        val viewport = viewport?.takeIf { it.isAttached } ?: return 0f
        val bounds = viewport.localBoundingBoxOf(field, clipBounds = false)
        val bottom = viewport.size.height - covered
        return when {
            bounds.top < top -> bounds.top - top
            bounds.bottom > bottom -> min(bounds.bottom - bottom, bounds.top - top)
            else -> 0f
        }
    }
}

/** The keyboard's room at the foot of what scrolls, past what a fitted sheet measures it for. */
private data class KeyboardRoom(val keyboard: SheetKeyboard, val ime: WindowInsets, val bottom: Dp) : ModifierNodeElement<KeyboardRoomNode>() {
    override fun create() = KeyboardRoomNode(keyboard, ime, bottom)

    override fun update(node: KeyboardRoomNode) {
        node.keyboard = keyboard
        node.ime = ime
        node.bottom = bottom
    }
}

private class KeyboardRoomNode(var keyboard: SheetKeyboard, var ime: WindowInsets, var bottom: Dp) : Modifier.Node(), LayoutModifierNode {
    override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
        val room = (keyboard.covered(ime.getBottom(this)) - bottom.roundToPx()).coerceAtLeast(0)
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height + room) { placeable.place(0, 0) }
    }

    override fun IntrinsicMeasureScope.minIntrinsicHeight(measurable: IntrinsicMeasurable, width: Int) = measurable.minIntrinsicHeight(width)

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(measurable: IntrinsicMeasurable, width: Int) = measurable.maxIntrinsicHeight(width)
}
