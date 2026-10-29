package com.plhery.peekdeliverytracker.ui.sheet

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.deliveries.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.deliveries.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import kotlin.math.max
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

/**
 * A `NavigationStack` in a sheet or a popover with a `Form` in it: the inline bar, its title in the middle and the
 * confirmation on a glass capsule at its trailing end (`ToolbarItem(placement: .confirmationAction)`, semibold), over
 * the form, which scrolls under the bar and fades out under it once it has. TalkBack reads the title as a heading, and
 * goes there first ([titleFocus]).
 */
@Composable
internal fun SheetPage(
    title: String,
    confirmation: String,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    titleFocus: AccessibilityFocus? = null,
    confirmTag: String? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val scroll = rememberScrollState()
    val attachment = LocalSheetAttachment.current
    val backdrop = rememberMaterialBackdrop()
    val bottom = with(LocalDensity.current) { WindowInsets.navigationBars.getBottom(this).toDp() }
    val insets = LocalSheetInsets.current
    val barTop = insets.barTop
    val barHeight = barTop + BAR_ROW
    val ground = LocalSheetGround.current
    Box(modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize().materialBackdrop(backdrop)) {
            // The sheet, drawn again where the confirmation's glass can take it on, as the iPhone's does: its tint
            // over the glass, or its lighter grey on the grouped ground in dark.
            ground?.invoke(Modifier.fillMaxSize())
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll)
                    .padding(top = barHeight, bottom = FORM_BOTTOM + bottom),
            ) {
                content()
            }
        }
        // iOS 26's hard scroll edge on an attached sheet: once the form has scrolled under the bar, a band the cells'
        // colour, which leaves a ghost of what is under it, down to a hairline EDGE_BELOW under the bar.
        Box(
            Modifier
                .fillMaxWidth()
                .height(barHeight + EDGE_BELOW)
                .drawBehind {
                    val shown = (scroll.value / EDGE_BELOW.toPx()).coerceIn(0f, 1f) * attachment()
                    if (shown <= 0f) return@drawBehind
                    drawRect(colors.secondaryGroupedBackground, alpha = shown * EDGE_OPACITY)
                    val line = 1.dp.toPx()
                    drawRect(colors.separator, Offset(0f, size.height - line), Size(size.width, line), alpha = shown)
                },
        )
        Box(Modifier.fillMaxWidth().padding(top = barTop).height(BAR_ROW)) {
            Text(
                title,
                Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = TITLE_CLEARANCE)
                    .then(if (titleFocus != null) Modifier.accessibilityFocus(titleFocus) else Modifier)
                    .semantics { heading() },
                color = colors.label,
                style = type.headline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            ConfirmationButton(confirmation, onConfirm, backdrop, Modifier.align(Alignment.CenterEnd).padding(end = insets.barSide), confirmTag)
        }
    }
}

/** The bar's confirmation, `.fontWeight(.semibold)` on its glass capsule. */
@Composable
private fun ConfirmationButton(title: String, onConfirm: () -> Unit, backdrop: MaterialBackdrop, modifier: Modifier, tag: String?) {
    val colors = PeekTheme.colors
    GlassSurface(CapsuleShape, modifier.heightIn(min = BAR_ROW), backdrop) {
        Box(
            Modifier
                .heightIn(min = BAR_ROW)
                .then(if (tag != null) Modifier.testTag(tag) else Modifier)
                .clickable(interactionSource = null, indication = RowHighlight.Capsule, role = Role.Button, onClick = onConfirm)
                .padding(horizontal = CONFIRM_PADDING),
            contentAlignment = Alignment.Center,
        ) {
            Text(title, color = colors.label, style = PeekTheme.type.style(IosTextStyle.Body, FontWeight.SemiBold), maxLines = 1)
        }
    }
}

/**
 * Where a page's bar and form stand in a sheet or a popover: the bar's row `barTop` under its top, clear of a sheet's
 * grabber, its confirmation `barSide` in from the side, and the form's sections and rows `formSide` in, UIKit's layout
 * margins, 16 on a surface narrower than 414 points, as a popover is, and 20 from there.
 */
@Immutable
internal data class SheetInsets(val barTop: Dp, val barSide: Dp, val formSide: Dp) {
    companion object {
        /** A sheet `width` wide. */
        fun sheet(width: Dp) = SheetInsets(19.dp, 20.dp, margin(width))

        /** A popover `width` wide. */
        fun popover(width: Dp) = SheetInsets(10.dp, 10.dp, margin(width))

        private fun margin(width: Dp) = if (width >= 414.dp) 20.dp else 16.dp
    }
}

internal val LocalSheetInsets = staticCompositionLocalOf { SheetInsets.sheet(440.dp) }

/**
 * `Section(header)` in an inset grouped form: its header, then its rows on one rounded surface, a hairline between
 * each, inset 20 on both sides. The surface is white (dark grey in dark) on the sheet's opaque ground, and a veil on
 * its glass, where the hairlines are a shade of the rows ([LocalSheetAttachment]). Without a header, the section keeps the room one would take.
 */
@Composable
fun FormSection(header: String?, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val colors = PeekTheme.colors
    val attachment = LocalSheetAttachment.current
    val side = LocalSheetInsets.current.formSide
    Column(modifier.fillMaxWidth().padding(horizontal = side).padding(bottom = SECTION_BOTTOM)) {
        if (header != null) {
            Text(
                header,
                Modifier.padding(start = side, end = side, top = HEADER_TOP, bottom = HEADER_BOTTOM).semantics { heading() },
                color = colors.secondaryLabel,
                style = PeekTheme.type.style(IosTextStyle.Headline, FontWeight.SemiBold).sfLines(),
            )
        } else {
            Spacer(Modifier.height(SECTION_GAP))
        }
        val shape = ContinuousRoundedShape(SECTION_RADIUS)
        val bounds = remember { mutableListOf<Int>() }
        Layout(
            content,
            Modifier
                .fillMaxWidth()
                .drawBehind {
                    val attached = attachment()
                    val outline = shape.createOutline(size, layoutDirection, this)
                    val veil = if (colors.dark) Color.White.copy(alpha = GLASS_ROWS_DARK) else Color.Black.copy(alpha = GLASS_ROWS_LIGHT)
                    if (attached < 1f) drawOutline(outline, veil, alpha = 1 - attached)
                    if (attached > 0f) drawOutline(outline, colors.secondaryGroupedBackground, alpha = attached)
                    val inset = side.toPx()
                    val line = SEPARATOR.toPx()
                    // On glass, the hairlines are the rows' own colour darkened (lightened in dark), as the
                    // iPhone's show; on the ground, the separator.
                    val glassLine = if (colors.dark) Color.White.copy(alpha = GLASS_SEPARATOR_DARK) else Color.Black.copy(alpha = GLASS_SEPARATOR_LIGHT)
                    for (y in bounds) {
                        val origin = Offset(inset, y - line)
                        val lineSize = Size(size.width - 2 * inset, line)
                        if (attached < 1f) drawRect(glassLine, origin, lineSize, alpha = 1 - attached)
                        if (attached > 0f) drawRect(colors.separator, origin, lineSize, alpha = attached)
                    }
                },
        ) { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints.copy(minHeight = 0)) }
            bounds.clear()
            var y = 0
            placeables.dropLast(1).forEach { y += it.height; bounds += y }
            layout(constraints.maxWidth, placeables.sumOf { it.height }) {
                var top = 0
                placeables.forEach { placeable ->
                    placeable.place(0, top)
                    top += placeable.height
                }
            }
        }
    }
}

/** A row's words, as an inline picker's label heads its options. */
@Composable
fun FormLabelRow(title: String) {
    FormRowFrame(Modifier) {
        Text(title, Modifier.weight(1f), color = PeekTheme.colors.label, style = PeekTheme.type.body.sfLines())
    }
}

/**
 * An option of an inline `Picker`: its words, and a checkmark in the ink at its trailing end while it is chosen.
 * TalkBack reads it as a button, selected while it is chosen.
 */
@Composable
fun FormCheckRow(title: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier, tag: String? = null) {
    val colors = PeekTheme.colors
    FormRowFrame(
        modifier
            .then(if (tag != null) Modifier.testTag(tag) else Modifier)
            .selectable(selected, interactionSource = null, indication = RowHighlight.Row, role = Role.Button, onClick = onClick),
    ) {
        Text(title, Modifier.weight(1f), color = colors.label, style = PeekTheme.type.body.sfLines())
        if (selected) {
            PeekSymbol("checkmark", Modifier.padding(start = 8.dp), font = PeekTheme.type.style(IosTextStyle.Body, FontWeight.SemiBold), tint = colors.ink)
        }
    }
}

/** `Button(role: .destructive)` in a form: its words in red, at the row's leading edge. */
@Composable
fun FormButtonRow(title: String, destructive: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier, tag: String? = null) {
    val colors = PeekTheme.colors
    FormRowFrame(
        modifier
            .then(if (tag != null) Modifier.testTag(tag) else Modifier)
            .clickable(interactionSource = null, indication = RowHighlight.Row, role = Role.Button, onClick = onClick),
    ) {
        Text(title, Modifier.weight(1f), color = if (destructive) colors.red else colors.ink, style = PeekTheme.type.body.sfLines())
    }
}

/** An option of a menu picker ([FormMenuRow]). */
data class MenuOption(val title: String, val tag: String? = null)

/**
 * A `Picker` of the menu style: its label, then the option chosen in the secondary grey and the up and down chevrons.
 * A tap opens iOS's menu of the options over the sheet, the chosen one checked ([SheetMenu]).
 */
@Composable
fun FormMenuRow(
    title: String,
    options: List<MenuOption>,
    selected: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tag: String? = null,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val menus = LocalSheetMenus.current
    var row by remember { mutableStateOf(Rect.Zero) }
    var value by remember { mutableStateOf(Rect.Zero) }
    val returnFocus = remember { AccessibilityFocus() }
    val current = options.getOrNull(selected)?.title.orEmpty()
    val open = {
        menus?.content = {
            SheetMenu(options, selected, row, value.right, onClose = { menus.content = null; returnFocus.request() }) { onSelect(it) }
        }
    }
    FormRowFrame(
        modifier
            .then(if (tag != null) Modifier.testTag(tag) else Modifier)
            .onGloballyPositioned { row = it.boundsInWindow() }
            .accessibilityFocus(returnFocus)
            .clickable(interactionSource = null, indication = RowHighlight.Row, role = Role.Button, onClick = open)
            .clearAndSetSemantics {
                contentDescription = "$title, $current"
                role = Role.Button
                onClick { open(); true }
            },
    ) {
        Text(title, Modifier.weight(1f).padding(end = 8.dp), color = colors.label, style = type.body.sfLines(), maxLines = 1, overflow = TextOverflow.Ellipsis)
        Row(Modifier.onGloballyPositioned { value = it.boundsInWindow() }, verticalAlignment = Alignment.CenterVertically) {
            Text(current, color = colors.secondaryLabel, style = type.body.sfLines(), maxLines = 1, overflow = TextOverflow.Ellipsis)
            PeekSymbol(
                "chevron.up.chevron.down",
                Modifier.padding(start = 5.dp),
                font = type.style(IosTextStyle.Footnote, FontWeight.Medium),
                tint = colors.secondaryLabel,
            )
        }
    }
}

/**
 * The menu a picker opens (`UIMenu`): its options on glass 250 wide, each 42 high, the chosen one checked at the leading
 * end, its top at the row's and its trailing edge at the picker's value, moved up as far as the window needs. It grows
 * from its top on a spring; a choice or a tap outside closes it, as the system's back does. TalkBack reaches only the
 * menu, from the chosen option, and goes back to the row once it has closed.
 */
@Composable
private fun SheetMenu(options: List<MenuOption>, selected: Int, row: Rect, trailing: Float, onClose: () -> Unit, onSelect: (Int) -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val bars = WindowInsets.systemBars
    val scope = rememberCoroutineScope()
    val shown = remember { Animatable(0f) }
    val first = remember { AccessibilityFocus() }
    var origin by remember { mutableStateOf(Offset.Zero) }
    var closing by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { first.request() }
    LaunchedEffect(Unit) { shown.animateTo(1f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.3, 0.15)) }
    fun close(then: () -> Unit = {}) {
        if (closing) return
        closing = true
        scope.launch {
            shown.animateTo(0f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.2))
            then()
            onClose()
        }
    }
    BackHandler { close() }
    val shape = ContinuousRoundedShape(MENU_RADIUS)
    Box(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned { origin = it.positionInWindow() }
            .pointerInput(Unit) { detectTapGestures { close() } },
    ) {
        Box(
            Modifier
                .layout { measurable, constraints ->
                    val margin = MENU_MARGIN.roundToPx()
                    val top = max(margin, bars.getTop(this))
                    val bottom = max(margin, bars.getBottom(this))
                    val width = MENU_WIDTH.roundToPx()
                    val placeable = measurable.measure(Constraints(width, width, 0, max(0, constraints.maxHeight - top - bottom)))
                    layout(constraints.maxWidth, constraints.maxHeight) {
                        val x = (trailing - origin.x - width).roundToInt().coerceIn(margin, max(margin, constraints.maxWidth - width - margin))
                        val wanted = (row.top - origin.y + MENU_DROP.toPx()).roundToInt()
                        val y = wanted.coerceAtMost(constraints.maxHeight - bottom - placeable.height).coerceAtLeast(top)
                        placeable.place(x, y)
                    }
                }
                .graphicsLayer {
                    val t = shown.value
                    alpha = t.coerceIn(0f, 1f)
                    val scale = if (reduceMotion) 1f else 0.6f + 0.4f * t
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin(1f, 0f)
                }
                .dropShadow(shape, MENU_SHADOW)
                .pointerInput(Unit) { detectTapGestures { } }
                .semantics { paneTitle = options.getOrNull(selected)?.title.orEmpty() },
        ) {
            // On the glass of a control, its edge lit and shaded, over the sheet's ground.
            GlassSurface(shape, Modifier.matchParentSize(), backdrop = null, behind = colors.groupedBackground) {}
            Column(Modifier.verticalScroll(rememberScrollState()).padding(vertical = MENU_PADDING)) {
                options.forEachIndexed { index, option ->
                    val chosen = index == selected
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .heightIn(min = MENU_ROW)
                            .then(if (option.tag != null) Modifier.testTag(option.tag) else Modifier)
                            .then(if (chosen) Modifier.accessibilityFocus(first) else Modifier)
                            .selectable(chosen, interactionSource = null, indication = RowHighlight.Row, role = Role.Button) {
                                close { onSelect(index) }
                            }
                            .padding(start = MENU_CHECK_START, end = 16.dp, top = 10.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(Modifier.width(MENU_CHECK_WIDTH)) {
                            if (chosen) PeekSymbol("checkmark", font = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold), tint = colors.label)
                        }
                        Text(option.title, Modifier.weight(1f), color = colors.label, style = type.body.sfLines(), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        }
    }
}

/** A row of a form: at least 52 high, its content in by the form's margin on either side. */
@Composable
private fun FormRowFrame(modifier: Modifier, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier.fillMaxWidth().heightIn(min = ROW_HEIGHT).padding(horizontal = LocalSheetInsets.current.formSide, vertical = ROW_VERTICAL),
        verticalAlignment = Alignment.CenterVertically,
        content = content,
    )
}

/**
 * A cell's highlight under the finger: the system's grey over the row, (209, 209, 214) on white, gone as it lifts. On
 * the bar's capsule, a lighter one.
 */
internal class RowHighlight private constructor(private val amount: Float) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode = HighlightNode(interactionSource, amount)

    override fun equals(other: Any?): Boolean = other is RowHighlight && other.amount == amount

    override fun hashCode(): Int = amount.hashCode()

    companion object {
        val Row = RowHighlight(0.18f)
        val Capsule = RowHighlight(0.08f)
    }
}

private class HighlightNode(private val interactionSource: InteractionSource, private val amount: Float) : Modifier.Node(), DrawModifierNode {
    private var pressed by mutableStateOf(false)

    override fun onAttach() {
        coroutineScope.launch {
            val presses = mutableListOf<PressInteraction.Press>()
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> presses += interaction
                    is PressInteraction.Release -> presses -= interaction.press
                    is PressInteraction.Cancel -> presses -= interaction.press
                }
                pressed = presses.isNotEmpty()
            }
        }
    }

    override fun ContentDrawScope.draw() {
        drawContent()
        if (pressed) drawRect(Color.Black.copy(alpha = amount))
    }
}

private val BAR_ROW = 44.dp
private val TITLE_CLEARANCE = 96.dp
private val CONFIRM_PADDING = 16.dp
private val EDGE_BELOW = 11.dp
private const val EDGE_OPACITY = 0.95f
private val FORM_BOTTOM = 20.dp
private val ROW_HEIGHT = 52.dp
private val ROW_VERTICAL = 6.dp
private val HEADER_TOP = 21.dp
private val HEADER_BOTTOM = 10.dp
private val SECTION_GAP = 29.dp
private val SECTION_BOTTOM = 6.dp
private val SEPARATOR = 1.dp
private val SECTION_RADIUS = 26.dp
private const val GLASS_ROWS_LIGHT = 0.07f
private const val GLASS_ROWS_DARK = 0.08f
private const val GLASS_SEPARATOR_LIGHT = 0.11f
private const val GLASS_SEPARATOR_DARK = 0.145f
private val MENU_WIDTH = 250.dp
private val MENU_RADIUS = 34.dp
private val MENU_ROW = 42.dp
private val MENU_PADDING = 8.dp
private val MENU_MARGIN = 16.dp
private val MENU_DROP = 8.dp
private val MENU_CHECK_START = 20.5.dp
private val MENU_CHECK_WIDTH = 23.dp
private val MENU_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.12f), radius = 24.dp, y = 8.dp)
