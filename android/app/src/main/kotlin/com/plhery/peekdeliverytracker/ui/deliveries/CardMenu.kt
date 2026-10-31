package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.res.Configuration
import android.view.KeyEvent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.toSize
import androidx.core.view.ViewCompat
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.sheet.Popover
import com.plhery.peekdeliverytracker.ui.sheet.PopoverSize
import com.plhery.peekdeliverytracker.ui.sheet.REGULAR_HEIGHT
import com.plhery.peekdeliverytracker.ui.sheet.REGULAR_WIDTH
import com.plhery.peekdeliverytracker.ui.sheet.RowHighlight
import com.plhery.peekdeliverytracker.ui.sheet.SheetMemory
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.TabOverlay
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import java.util.UUID
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * A card, or an archived row, whose menu can open: where it stands in the window, whole even where the list cuts it,
 * and its picture, which the menu lifts over the dimmed screen in the card's own shape
 * (`.contentShape(.contextMenuPreview, shape)`), on `fill` where the card has no ground of its own (a row of the
 * archive's surface), as iOS lifts a preview on a platter. [modifier] goes on the outside of the card; while its menu
 * is open the card is drawn only there, lifted, as iOS hides the card it lifts. [target] goes on what TalkBack reads
 * as the card, where TalkBack comes back as the menu closes.
 */
@Stable
internal class CardMenuSource(
    parcel: Parcel,
    val cornerRadius: Dp,
    val fill: Color?,
    val picture: GraphicsLayer,
    private val state: DeliveriesState,
) {
    /** The parcel as the card last showed it. */
    var parcel: Parcel by mutableStateOf(parcel)

    /** The whole card in the window, the part the list cuts off included. */
    var bounds: Rect by mutableStateOf(Rect.Zero)
        private set

    val focus = AccessibilityFocus()

    val modifier: Modifier = Modifier
        .onGloballyPositioned { bounds = Rect(it.positionInWindow(), it.size.toSize()) }
        .drawWithContent {
            picture.record { this@drawWithContent.drawContent() }
            if (state.menu?.source !== this@CardMenuSource) drawLayer(picture)
        }

    val target: Modifier = Modifier.accessibilityFocus(focus)

    /** The long press: the menu opens over the screen. */
    fun open() {
        if (state.menu != null) return
        state.menu = CardMenuRequest(this)
        state.returnFocus = focus
    }
}

/**
 * A card's menu, which closes as the card goes: off the list, or as the window turns and the list is drawn anew. The
 * card stands in [DeliveriesState.cardMenus] while it is drawn, for its delete question to point at.
 */
@Composable
internal fun rememberCardMenu(state: DeliveriesState, parcel: Parcel, cornerRadius: Dp, fill: Color? = null): CardMenuSource {
    val picture = rememberGraphicsLayer()
    val source = remember(state, parcel.id, cornerRadius, fill, picture) { CardMenuSource(parcel, cornerRadius, fill, picture, state) }
    SideEffect { source.parcel = parcel }
    DisposableEffect(source) {
        state.cardMenus[parcel.id] = source
        onDispose {
            if (state.cardMenus[parcel.id] === source) state.cardMenus.remove(parcel.id)
            if (state.menu?.source === source) state.menu = null
            if (state.returnFocus === source.focus) state.returnFocus = null
        }
    }
    return source
}

/** The menu open over the list: the card it came from, and its parcel's items. */
@Stable
internal class CardMenuRequest(val source: CardMenuSource) {
    val parcel: Parcel get() = source.parcel
}

/** The menu's side of the lifted card, where it grows from. */
internal enum class MenuSide { Below, Above, Left, Right }

/**
 * Where the lifted card and its menu stand, as iOS places a context menu: the menu under the card when both fit, above
 * it otherwise, the card moved just enough for the two to fit. Where they don't fit over each other, as on a phone
 * turned sideways, the menu stands beside the card, the card smaller, on the side with more room; where the window is
 * too narrow to show the card at half its size beside the menu, the card shrinks over or under the menu instead,
 * whichever shows it larger. The menu is [menu] tall at most, and scrolls past that. All of it within the window's
 * safe area `safe`, `margin` from its top and bottom and `side` from its sides; all in pixels in the window.
 */
internal data class CardMenuPlacement(val card: Rect, val scale: Float, val menu: Rect, val side: MenuSide)

internal fun cardMenuPlacement(
    card: Rect,
    menuWidth: Float,
    menuHeight: Float,
    safe: Rect,
    margin: Float,
    gap: Float,
    side: Float,
): CardMenuPlacement {
    val highest = safe.top + margin
    val lowest = max(highest, safe.bottom - margin)
    val room = lowest - highest
    val leftmost = safe.left + side
    val rightmost = max(leftmost, safe.right - side)
    // A card as wide as the window is drawn a little smaller, so it keeps the menu's margins at its sides.
    val fullScale = min(1f, (rightmost - leftmost) / card.width)

    fun stacked(scale: Float, shown: Float): CardMenuPlacement {
        val width = card.width * scale
        val height = card.height * scale
        val left = (card.center.x - width / 2).coerceIn(leftmost, max(leftmost, rightmost - width))
        var top = card.center.y - height / 2
        val above = top + height + gap + shown > lowest
        top = if (above) min(max(top, highest + shown + gap), lowest - height) else max(top, highest)
        val menuTop = if (above) top - gap - shown else top + height + gap
        val menuLeft = (card.center.x - menuWidth / 2).coerceIn(leftmost, max(leftmost, rightmost - menuWidth))
        return CardMenuPlacement(
            Rect(left, top, left + width, top + height), scale, Rect(menuLeft, menuTop, menuLeft + menuWidth, menuTop + shown),
            if (above) MenuSide.Above else MenuSide.Below,
        )
    }

    if (card.height * fullScale + gap + menuHeight <= room) return stacked(fullScale, menuHeight)

    // Over each other, the card keeps at least a third of the room and the menu scrolls under it.
    val shrunkHeight = (room - gap - menuHeight).coerceIn(min(card.height * fullScale, room * MIN_PREVIEW), card.height * fullScale)
    val shrunkScale = shrunkHeight / card.height
    // Beside each other, the card as large as the room left of the menu allows.
    val besideScale = minOf(1f, (rightmost - leftmost - gap - menuWidth) / card.width, room / card.height)
    if (besideScale < min(shrunkScale, BESIDE_SCALE)) return stacked(shrunkScale, max(0f, min(menuHeight, room - gap - shrunkHeight)))

    val width = card.width * besideScale
    val height = card.height * besideScale
    val right = card.center.x < (leftmost + rightmost) / 2
    val group = width + gap + menuWidth
    // The card stays where it was as far as the menu leaves room for it.
    val start = if (right) card.center.x - width / 2 else card.center.x + width / 2 - group
    val groupLeft = start.coerceIn(leftmost, max(leftmost, rightmost - group))
    val cardLeft = if (right) groupLeft else groupLeft + menuWidth + gap
    val menuLeft = if (right) groupLeft + width + gap else groupLeft
    val top = (card.center.y - height / 2).coerceIn(highest, max(highest, lowest - height))
    val shown = min(menuHeight, room)
    val menuTop = top.coerceIn(highest, max(highest, lowest - shown))
    return CardMenuPlacement(
        Rect(cardLeft, top, cardLeft + width, top + height), besideScale, Rect(menuLeft, menuTop, menuLeft + menuWidth, menuTop + shown),
        if (right) MenuSide.Right else MenuSide.Left,
    )
}

/** The least of the room a card shrunk over or under its menu keeps. */
private const val MIN_PREVIEW = 1f / 3

/** A card this large beside its menu, or larger, stands beside it rather than over or under it. */
private const val BESIDE_SCALE = 0.5f

/**
 * The open menu, the delete it asks about, the rename alert, or the alert of an action that failed, over the whole
 * window where the tab shell is there to draw it ([LocalTabOverlay]), over the list otherwise. Nothing while none is
 * open.
 */
@Composable
internal fun CardMenuHost(state: DeliveriesState) {
    val request = state.menu
    val deleting = state.deleting
    val error = state.actionError ?: state.pageError
    val host = LocalTabOverlay.current
    // Once the menu and what it opened have all closed, TalkBack goes back to the card the menu came from.
    val modal = state.modal
    LaunchedEffect(modal) {
        if (modal) return@LaunchedEffect
        state.returnFocus?.request()
        state.returnFocus = null
    }
    // The page's question goes with its parcel, or its page, as the iPhone's goes with the ••• it stands off.
    val pageParcel = state.liveMenuParcel(deleting?.pageId?.takeIf { it == state.navigator.navigation.shown })
    val unasked = deleting?.fromPage == true && pageParcel == null
    LaunchedEffect(unasked) { if (unasked) state.deleting = null }
    val window = LocalWindowInfo.current.containerSize
    val regular = with(LocalDensity.current) { window.width.toDp() >= REGULAR_WIDTH && window.height.toDp() >= REGULAR_HEIGHT }
    // The page's question, made again with the window, is there already, as a popover is.
    val asked = rememberSaveable(saver = SheetMemory.Saver) { SheetMemory() }
    if (deleting?.fromPage != true) asked.forget()
    CardDeleteQuestion(state, deleting?.takeIf { request == null && !it.fromPage }, regular)
    // The page's rename alert goes with its page, as the iPhone's goes with the page it stands over.
    val renaming = state.renaming
    val unnamed = renaming?.pageId != null && renaming.pageId != state.navigator.navigation.shown
    LaunchedEffect(unnamed) { if (unnamed) state.cancelRename() }
    val content: (@Composable () -> Unit)? = when {
        request != null -> { { CardMenuOverlay(state, request) } }
        deleting != null && deleting.fromPage && !unasked -> { { PageDeleteQuestion(state, deleting, regular, asked) } }
        renaming != null && !unnamed -> { { RenameAlert(state, renaming) } }
        error != null -> { { ActionErrorAlert(state, error) } }
        else -> null
    }
    if (host == null) {
        content?.invoke()
        return
    }
    OverlayLayer(host, CARD_MENU, content)
}

/**
 * Draws `content` over the window as the shell's layer `key` ([LocalTabOverlay]) while it isn't null, taking the latest
 * `content` each time it is drawn. Each composition here draws the whole layer again: `content` is the same lambda
 * with new captures, which Compose remembers as one, and Compose drops that lambda's own redraw when this composes in
 * the frame the layer was put in (a popover made again with the window, its source laid out that frame), so the layer
 * would keep what it first drew.
 */
@Composable
internal fun OverlayLayer(host: TabOverlay, key: Any, content: (@Composable () -> Unit)?) {
    val latest = remember { mutableStateOf<LayerPass?>(null) }
    latest.value = content?.let(::LayerPass)
    DisposableEffect(host, key, content != null) {
        val layer: @Composable () -> Unit = {
            latest.value?.let { pass -> CompositionLocalProvider(LocalLayerPass provides pass) { pass.content() } }
        }
        host.show(key, if (content != null) layer else null)
        onDispose { host.show(key, null) }
    }
}

/** A composition of a layer's owner. Each is new, and a new one provided over the layer composes all of it again. */
private class LayerPass(val content: @Composable () -> Unit)

private val LocalLayerPass = staticCompositionLocalOf<LayerPass?> { null }

/**
 * The card's menu (A-S41, A-G04): the screen dims, the card lifts in its shape, and the parcel's items stand beside it on
 * glass in their groups, each with its symbol, and the keys that press them on the parcel the keyboard acts on. A tap
 * outside, the back gesture or a choice closes it; a choice acts once it has closed. TalkBack reaches only the menu,
 * from its first item.
 */
@Composable
private fun CardMenuOverlay(state: DeliveriesState, request: CardMenuRequest) {
    val localizer = LocalLocalizer.current
    val density = LocalDensity.current
    val parcel = request.parcel
    val items = remember(parcel) { ParcelMenuItem.menu(parcel, onCard = true, reader = state.source.viewerLink(parcel) != null) }
    val shortcuts = parcel.id == state.selectedCard && keyboardConnected()
    val menu = rememberMenuPresence(request) { state.menu = null }
    val shown = menu.shown
    val choose = { item: ParcelMenuItem -> menu.close { state.chose(item, parcel) } }
    MenuKeys(shortcuts, items, choose)

    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .testTag(CARD_MENU)
            .pointerInput(request) { detectTapGestures { menu.close() } },
    ) {
        val windowWidth = constraints.maxWidth.toFloat()
        val windowHeight = constraints.maxHeight.toFloat()
        val insets = WindowInsets.safeDrawing
        val direction = LocalLayoutDirection.current
        var origin by remember { mutableStateOf(Offset.Zero) }
        val menuWidth = with(density) { MENU_WIDTH.toPx() }
        // The menu's whole height, as tall as its items; it shows as much as the placement leaves room for.
        var menuHeight by remember(request) { mutableIntStateOf(0) }
        val card = request.source.bounds.translate(-origin)
        val placement = with(density) {
            val safe = Rect(
                insets.getLeft(this, direction).toFloat(), insets.getTop(this).toFloat(),
                windowWidth - insets.getRight(this, direction), windowHeight - insets.getBottom(this),
            )
            cardMenuPlacement(card, menuWidth, menuHeight.toFloat(), safe, margin = MENU_MARGIN.toPx(), gap = MENU_GAP.toPx(), side = PREVIEW_SIDE.toPx())
        }
        // The dim, over everything but the card and its menu.
        Box(
            Modifier
                .fillMaxSize()
                .onGloballyPositioned { origin = it.positionInWindow() }
                .graphicsLayer { alpha = shown.value }
                .background(DIM),
        )
        // The card, lifted from its place to the menu's side.
        val radius = request.source.cornerRadius
        Box(
            Modifier
                .layout { measurable, _ ->
                    val placeable = measurable.measure(Constraints.fixed(card.width.toInt(), card.height.toInt()))
                    layout(placeable.width, placeable.height) { placeable.place(card.left.toInt(), card.top.toInt()) }
                }
                .graphicsLayer {
                    val t = shown.value
                    val scale = 1 + (placement.scale - 1) * t
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                    scaleX = scale
                    scaleY = scale
                    translationX = (placement.card.center.x - card.center.x) * t
                    translationY = (placement.card.center.y - card.center.y) * t
                }
                .dropShadow(ContinuousRoundedShape(radius), PREVIEW_SHADOW)
                .clip(ContinuousRoundedShape(radius))
                .then(request.source.fill?.let { Modifier.background(it) } ?: Modifier)
                // Gone with its card, as the window turns, a frame before the menu closes.
                .drawBehind { if (!request.source.picture.isReleased) drawLayer(request.source.picture) }
                .clearAndSetSemantics { testTag = CARD_MENU_PREVIEW },
        )
        // The menu, growing out of the card's side, as tall as the room allows and scrolling past it.
        GlassSurface(
            ContinuousRoundedShape(MENU_RADIUS),
            Modifier
                .layout { measurable, _ ->
                    val width = menuWidth.roundToInt()
                    val placeable = measurable.measure(Constraints(width, width, 0, placement.menu.height.roundToInt()))
                    layout(placeable.width, placeable.height) {
                        placeable.place(placement.menu.left.roundToInt(), placement.menu.top.roundToInt())
                    }
                }
                .growingMenu(
                    menu,
                    when (placement.side) {
                        MenuSide.Below -> TransformOrigin(0.5f, 0f)
                        MenuSide.Above -> TransformOrigin(0.5f, 1f)
                        MenuSide.Right -> TransformOrigin(0f, 0f)
                        MenuSide.Left -> TransformOrigin(1f, 0f)
                    },
                )
                .pointerInput(Unit) { detectTapGestures { } }
                .semantics { paneTitle = parcel.label.ifEmpty { localizer.text("common.parcel") } }
                .dropShadow(ContinuousRoundedShape(MENU_RADIUS), MENU_SHADOW),
        ) {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        menuHeight = placeable.height
                        layout(placeable.width, placeable.height) { placeable.place(0, 0) }
                    }
                    .padding(vertical = MENU_PADDING),
            ) {
                MenuItems(items, shortcuts, busy = false, choose, menu.first)
            }
        }
    }
}

/**
 * A menu's coming and going over the window, the card's and the page's, and the page's delete question's: it grows in
 * on a spring as it opens, TalkBack going to its first item ([first]), and shrinks away as it closes ([close]); under
 * Reduce Motion it only fades. Made again with the window `up`, it is there already. The back gesture closes it.
 */
@Stable
internal class MenuPresence(private val scope: CoroutineScope, up: Boolean, private val onClosed: () -> Unit) {
    val shown = Animatable(if (up) 1f else 0f)
    val first = AccessibilityFocus()
    var reduceMotion = false
    private var closing = false

    /** Closes the menu, then runs `then`: a choice acts once the menu has gone. */
    fun close(then: () -> Unit = {}) {
        if (closing) return
        closing = true
        scope.launch {
            shown.animateTo(0f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.2))
            onClosed()
            then()
        }
    }
}

/** The presence of the menu `request` opened, there already where `up`, which `onClosed` forgets once it has gone. */
@Composable
internal fun rememberMenuPresence(request: Any, up: Boolean = false, onClosed: () -> Unit): MenuPresence {
    val scope = rememberCoroutineScope()
    val closed by rememberUpdatedState(onClosed)
    val menu = remember(request) { MenuPresence(scope, up) { closed() } }
    menu.reduceMotion = PeekTheme.reduceMotion
    LaunchedEffect(menu) { menu.first.request() }
    LaunchedEffect(menu) { menu.shown.animateTo(1f, if (menu.reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.3, 0.15)) }
    BackHandler { menu.close() }
    return menu
}

/** The menu's glass, growing from `origin` as it opens and shrinking back into it as it closes. */
internal fun Modifier.growingMenu(menu: MenuPresence, origin: TransformOrigin): Modifier = graphicsLayer {
    val t = menu.shown.value
    alpha = t.coerceIn(0f, 1f)
    val scale = if (menu.reduceMotion) 1f else 0.6f + 0.4f * t
    scaleX = scale
    scaleY = scale
    transformOrigin = origin
}

/**
 * `ParcelMenuButtons`: a parcel's items in their groups, a line between them, the keys that press them shown where
 * `shortcuts`, and Delete waiting while `busy` saves another change. TalkBack starts at the first, which `first` marks.
 */
@Composable
internal fun MenuItems(
    items: List<List<ParcelMenuItem>>,
    shortcuts: Boolean,
    busy: Boolean,
    choose: (ParcelMenuItem) -> Unit,
    first: AccessibilityFocus,
) {
    items.forEachIndexed { index, group ->
        if (index > 0) MenuSeparator()
        for (item in group) {
            MenuItemRow(
                item, shortcuts, choose,
                enabled = !(busy && item == ParcelMenuItem.Delete),
                modifier = if (item == items.first().first()) Modifier.accessibilityFocus(first) else Modifier,
            )
        }
    }
}

@Composable
private fun MenuItemRow(
    item: ParcelMenuItem,
    shortcuts: Boolean,
    choose: (ParcelMenuItem) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    val title = localizer.text(item.titleKey)
    val key = if (shortcuts) item.shortcutLabel else null
    // The menu's words and symbols are vibrant on its glass: the label a shade lighter, a disabled item's fainter.
    val ink = if (enabled) colors.label.copy(alpha = 0.8f) else colors.tertiaryLabel
    Row(
        modifier
            .fillMaxWidth()
            .heightIn(min = ROW_HEIGHT)
            .testTag(menuItemTag(item))
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled) { choose(item) }
            .clearAndSetSemantics {
                contentDescription = listOfNotNull(title, key).joinToString(", ")
                role = Role.Button
                if (enabled) onClick { choose(item); true } else disabled()
            }
            .padding(start = 26.dp, end = 20.dp, top = ROW_PADDING, bottom = ROW_PADDING),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.width(26.dp), contentAlignment = Alignment.Center) {
            PeekSymbol(item.symbol, font = type.body, tint = ink)
        }
        Spacer(Modifier.width(12.dp))
        // UIKit writes the keys as glyphs (⇧⌘C) at the row's end, beside any title. Android writes them out
        // (Shift+Ctrl+C), which beside a title in a menu this narrow would squeeze it into broken words: they stand
        // under it.
        Column(Modifier.weight(1f)) {
            Text(title, color = ink, style = type.body.sfLines())
            if (key != null) Text(key, color = colors.secondaryLabel, style = type.body.sfLines())
        }
    }
}

@Composable
private fun MenuSeparator() {
    Box(Modifier.fillMaxWidth().height(SEPARATOR_BLOCK), contentAlignment = Alignment.Center) {
        Box(Modifier.padding(horizontal = SEPARATOR_INSET).fillMaxWidth().height(1.dp).background(PeekTheme.colors.separator))
    }
}

/**
 * `ParcelMenuItem.shortcut`, as the keys read on Android, where Ctrl stands for ⌘ as the deliveries' keys take it:
 * ⇧⌘C, ⌃⌘A and ⌘⌫.
 */
internal val ParcelMenuItem.shortcutLabel: String?
    get() = when (this) {
        ParcelMenuItem.CopyTrackingNumber -> "Shift+Ctrl+C"
        ParcelMenuItem.Archive, ParcelMenuItem.Restore -> "Meta+Ctrl+A"
        ParcelMenuItem.Delete -> "Ctrl+Backspace"
        else -> null
    }

/** The item a key presses while the menu shows its keys. */
internal fun ParcelMenuItem.Companion.forShortcut(event: KeyEvent, items: List<ParcelMenuItem>): ParcelMenuItem? {
    val ctrl = event.isCtrlPressed
    val wanted = when {
        ctrl && event.isShiftPressed && event.keyCode == KeyEvent.KEYCODE_C -> listOf(ParcelMenuItem.CopyTrackingNumber)
        ctrl && event.isMetaPressed && event.keyCode == KeyEvent.KEYCODE_A -> listOf(ParcelMenuItem.Archive, ParcelMenuItem.Restore)
        ctrl && event.keyCode == KeyEvent.KEYCODE_DEL -> listOf(ParcelMenuItem.Delete)
        else -> return null
    }
    return items.firstOrNull { it in wanted }
}

/** While the menu shows its keys, they press its items, as a menu's key equivalents do. */
@Composable
internal fun MenuKeys(shortcuts: Boolean, items: List<List<ParcelMenuItem>>, choose: (ParcelMenuItem) -> Unit) {
    if (!shortcuts) return
    val view = LocalView.current
    val current by rememberUpdatedState(choose)
    DisposableEffect(view, items) {
        val listener = ViewCompat.OnUnhandledKeyEventListenerCompat { _, event ->
            val item = ParcelMenuItem.forShortcut(event, items.flatten()) ?: return@OnUnhandledKeyEventListenerCompat false
            if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0) current(item)
            true
        }
        ViewCompat.addOnUnhandledKeyEventListener(view, listener)
        onDispose { ViewCompat.removeOnUnhandledKeyEventListener(view, listener) }
    }
}

/** A hardware keyboard is connected and in use (`ConnectedKeyboard.shared.isConnected`). */
@Composable
internal fun keyboardConnected(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.keyboard != Configuration.KEYBOARD_NOKEYS &&
        configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO
}

/** A delete a menu asks about first: its parcel, and the page whose ••• asked, if one did, whose question stands off it. */
@Immutable
internal data class DeleteRequest(val parcelId: UUID, val pageId: UUID?) {
    val fromPage: Boolean get() = pageId != null

    /** As a window keeps it. */
    val saved: String get() = savedPrompt(parcelId, pageId)

    companion object {
        fun from(saved: String): DeleteRequest = promptIds(saved).let { (parcelId, pageId) -> DeleteRequest(parcelId, pageId) }
    }
}

/** A parcel being named: from a card's menu, or from the page with `pageId`, which saves the name as one of its changes. */
@Immutable
internal data class RenameRequest(val parcelId: UUID, val pageId: UUID?) {
    /** As a window keeps it. */
    val saved: String get() = savedPrompt(parcelId, pageId)

    companion object {
        fun from(saved: String): RenameRequest = promptIds(saved).let { (parcelId, pageId) -> RenameRequest(parcelId, pageId) }
    }
}

/** A prompt's parcel and the page that asked, if one did, as a window keeps them: their ids, a space between. */
private fun savedPrompt(parcelId: UUID, pageId: UUID?): String = listOfNotNull(parcelId, pageId).joinToString(" ")

private fun promptIds(saved: String): Pair<UUID, UUID?> {
    val ids = saved.split(" ").map(UUID::fromString)
    return ids[0] to ids.getOrNull(1)
}

/**
 * `parcelDeleteConfirmation` asked from the page's ••• (B-S11), as iOS 26 shows a confirmation dialog off a menu: the
 * question on glass with no arrow ([DeleteQuestion]), growing out of the ••• as its menu does, its top at the button's
 * and its trailing edge 10 past the button's, over nothing dimmed; as tall as what it holds, scrolling past the window.
 * A tap outside or the back gesture cancels, as there is no Cancel; Delete acts once it has gone, on the page's parcel
 * as it is then. It goes at once with its parcel, deleted elsewhere. TalkBack reaches only it, from the question, and
 * the window made again finds it there without growing again.
 */
@Composable
private fun PageDeleteQuestion(state: DeliveriesState, request: DeleteRequest, regular: Boolean, memory: SheetMemory) {
    val density = LocalDensity.current
    val title = LocalLocalizer.current.text("detail.deleteQuestion")
    val menu = rememberMenuPresence(request, up = memory.up) { state.deleting = null }
    LaunchedEffect(menu) { memory.up = true }
    val width = if (regular) DELETE_WIDTH_REGULAR else DELETE_WIDTH
    val shape = ContinuousRoundedShape(if (regular) DELETE_RADIUS_REGULAR else DELETE_RADIUS)
    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .pointerInput(request) { detectTapGestures { menu.close() } },
    ) {
        val windowWidth = constraints.maxWidth.toFloat()
        val windowHeight = constraints.maxHeight.toFloat()
        val insets = WindowInsets.safeDrawing
        val direction = LocalLayoutDirection.current
        var origin by remember { mutableStateOf(Offset.Zero) }
        val anchor = state.pageMenuButton.translate(-origin)
        val frame = with(density) {
            val side = DELETE_SIDE.toPx()
            val across = width.toPx()
            val leftmost = insets.getLeft(this, direction) + side
            val rightmost = max(leftmost, windowWidth - insets.getRight(this, direction) - side)
            val left = (anchor.right + DELETE_OUTSET.toPx() - across).coerceIn(leftmost, max(leftmost, rightmost - across))
            val top = max(anchor.top, insets.getTop(this).toFloat())
            val bottom = max(top, windowHeight - insets.getBottom(this) - MENU_MARGIN.toPx())
            Rect(left, top, left + across, bottom)
        }
        Box(Modifier.fillMaxSize().onGloballyPositioned { origin = it.positionInWindow() })
        GlassSurface(
            shape,
            Modifier
                .layout { measurable, _ ->
                    val across = frame.width.roundToInt()
                    val placeable = measurable.measure(Constraints(across, across, 0, frame.height.roundToInt()))
                    layout(placeable.width, placeable.height) { placeable.place(frame.left.roundToInt(), frame.top.roundToInt()) }
                }
                // Made again with the window, it shows once the ••• has been laid out again.
                .graphicsLayer { if (state.pageMenuButton == Rect.Zero) alpha = 0f }
                .growingMenu(menu, TransformOrigin(1f, 0f))
                .pointerInput(Unit) { detectTapGestures { } }
                .testTag(DELETE_POPOVER)
                .semantics { paneTitle = title }
                .dropShadow(shape, MENU_SHADOW),
        ) {
            DeleteQuestion(menu.first, Modifier.verticalScroll(rememberScrollState())) { menu.close { state.confirmDelete(request) } }
        }
    }
}

/**
 * `parcelDeleteConfirmation` asked from a card's menu (B-S11), as iOS 26 shows a confirmation dialog off a list's row:
 * once the menu has closed, a popover whose arrow points at the card, over it where it fits, as an iPad puts it even
 * with more room under it, and under it otherwise, as for a phone's first card ([DeleteQuestion]). `request` is the
 * card's question while it is asked. A tap outside or the back gesture cancels; Delete acts once the popover has gone,
 * on the parcel it asked about. It goes with its card: with the parcel deleted elsewhere, or with a window made again
 * whose list no longer shows the card.
 */
@Composable
private fun CardDeleteQuestion(state: DeliveriesState, request: DeleteRequest?, regular: Boolean) {
    val localizer = LocalLocalizer.current
    // The card asked about, which the popover keeps pointing at as it goes.
    val last = remember { AskedCard() }
    if (request != null) last.parcelId = request.parcelId
    val card = last.parcelId?.let { state.cardMenus[it] }
    card?.bounds?.let { last.bounds = it }
    val missing = request != null && card == null
    LaunchedEffect(missing) {
        if (!missing) return@LaunchedEffect
        // A window made again lays its list out first.
        repeat(2) { withFrameNanos { } }
        state.deleting = null
    }
    var confirmed by remember { mutableStateOf<DeleteRequest?>(null) }
    // As tall as what it says, which a popover doesn't work out itself.
    var height by remember { mutableIntStateOf(0) }
    val tall = with(LocalDensity.current) { height.toDp() }
    val question = remember { AccessibilityFocus() }
    val asking = request != null
    LaunchedEffect(asking) { if (asking) state.deleteGoing = true }
    val width = if (regular) DELETE_WIDTH_REGULAR else DELETE_WIDTH
    Popover(
        visible = asking,
        anchor = last.bounds,
        onDismiss = { state.deleting = null },
        paneTitle = localizer.text("detail.deleteQuestion"),
        size = PopoverSize(width, width, tall, tall),
        onClosed = {
            state.deleteGoing = false
            confirmed?.let(state::confirmDelete)
            confirmed = null
        },
        above = true,
        radius = if (regular) DELETE_RADIUS_REGULAR else DELETE_RADIUS,
    ) {
        // TalkBack goes to the question once the popover is there to reach.
        LaunchedEffect(Unit) { question.request() }
        Box(Modifier.fillMaxSize().testTag(DELETE_POPOVER).verticalScroll(rememberScrollState())) {
            DeleteQuestion(question, Modifier.onSizeChanged { height = it.height }) {
                confirmed = state.deleting
                state.deleting = null
            }
        }
    }
}

/** The card a delete question was last asked about, and where it last stood in the window. */
private class AskedCard {
    var parcelId: UUID? = null
    var bounds: Rect = Rect.Zero
}

/**
 * The delete question as iOS 26 asks it in a popover (`ParcelDeleteConfirmation`): "Delete this parcel?" in the
 * headline's weight and its message under it in the secondary colour, both leading, 30 in from the sides, then Delete
 * in a capsule as an alert's buttons are ([AlertButton]), in the label's colour as it has no role. TalkBack reads the
 * question and its message as one, from [focus].
 */
@Composable
private fun DeleteQuestion(focus: AccessibilityFocus, modifier: Modifier = Modifier, delete: () -> Unit) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    Column(modifier.fillMaxWidth().padding(start = ALERT_BUTTON_SIDE, top = ALERT_TOP, end = ALERT_BUTTON_SIDE, bottom = ALERT_BUTTON_SIDE)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = QUESTION_INSET)
                .semantics(mergeDescendants = true) {}
                .accessibilityFocus(focus),
            verticalArrangement = Arrangement.spacedBy(MESSAGE_GAP),
        ) {
            Text(localizer.text("detail.deleteQuestion"), color = colors.label, style = type.headline.uiKitLines(IosTextStyle.Headline))
            Text(localizer.text("detail.deleteDescription"), color = colors.secondaryLabel, style = type.subheadline.uiKitLines(IosTextStyle.Subheadline))
        }
        Spacer(Modifier.height(DELETE_BUTTON_GAP))
        AlertButton(localizer.text("detail.delete"), DELETE_CONFIRM, Modifier.fillMaxWidth(), delete)
    }
}

/**
 * `parcelRenameAlert` (B-S10), drawn as iOS 26 draws an alert with a text field ([AlertCard]): "Edit parcel name", the
 * field holding the name typed so far, "Parcel" while it is empty, on a capsule in the alert's fill, then Cancel and
 * Save name side by side, or Save name over Cancel where either doesn't fit its half, as at the accessibility text
 * sizes ([RenameButtons]). The field takes the focus and the keyboard as the alert opens, the cursor after the name,
 * and the keyboard's Done saves, as Return does; the back gesture cancels. The alert stands in the middle of what the
 * keyboard leaves of the window, clear of the system's bars, and scrolls where it is taller than that room, as
 * UIAlertController does in a phone on its side with the keyboard up. TalkBack starts at its title.
 */
@Composable
private fun RenameAlert(state: DeliveriesState, request: RenameRequest) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    val heading = remember(request) { AccessibilityFocus() }
    val focus = remember(request) { FocusRequester() }
    // The name typed so far, from the window's: a window made again finds it as it was.
    var field by remember(request) { mutableStateOf(state.renameText.let { TextFieldValue(it, TextRange(it.length)) }) }
    LaunchedEffect(request) {
        focus.requestFocus()
        heading.request()
    }
    BackHandler(onBack = state::cancelRename)
    val title = localizer.text("detail.editTitle")
    val font = type.body
    val bars = WindowInsets.systemBars.union(WindowInsets.displayCutout)
    val keyboard = WindowInsets.ime
    Box(
        Modifier
            .fillMaxSize()
            .testTag(RENAME_ALERT)
            .background(DIM)
            .pointerInput(Unit) { detectTapGestures { } }
            .imePadding(),
        contentAlignment = Alignment.Center,
    ) {
        AlertCard(
            title,
            Modifier.layout { measurable, constraints ->
                // In the middle of the room, as tall as it leaves between the status bar and the navigation bar where
                // the keyboard doesn't cover it.
                val clear = max(bars.getTop(this), bars.getBottom(this) - keyboard.getBottom(this))
                val placeable = measurable.measure(constraints.copy(minHeight = 0, maxHeight = max(0, constraints.maxHeight - 2 * clear)))
                layout(placeable.width, placeable.height) { placeable.place(0, 0) }
            },
        ) {
            Text(
                title, Modifier.padding(horizontal = ALERT_BUTTON_SIDE - ALERT_SIDE).accessibilityFocus(heading), color = colors.label,
                style = type.body.uiKitLines(IosTextStyle.Body), textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(ALERT_FIELD_GAP))
            val placeholder = localizer.text("common.parcel")
            BasicTextField(
                field,
                onValueChange = {
                    field = it
                    state.renameText = it.text
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(RENAME_FIELD)
                    .focusRequester(focus)
                    .semantics { contentDescription = placeholder },
                textStyle = font.copy(color = colors.label),
                singleLine = true,
                cursorBrush = SolidColor(colors.accent),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { state.saveRename() }),
                decorationBox = { text ->
                    Box(
                        Modifier
                            .heightIn(min = ALERT_CAPSULE_HEIGHT)
                            .background(alertFill(colors.dark), CapsuleShape)
                            .padding(horizontal = FIELD_INSET, vertical = ALERT_CAPSULE_PADDING),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        if (field.text.isEmpty()) {
                            Text(placeholder, Modifier.clearAndSetSemantics {}, color = colors.tertiaryLabel, style = font, maxLines = 1)
                        }
                        text()
                    }
                },
            )
            Spacer(Modifier.height(ALERT_BUTTONS_GAP))
            RenameButtons(localizer.text("common.cancel"), localizer.text("detail.saveTitle"), state::cancelRename, state::saveRename)
        }
    }
}

/**
 * The rename alert's Cancel and Save name, as iOS 26 lays out an alert's two actions: capsules side by side while each
 * title fits its half; otherwise, and always at the accessibility text sizes, one under the other, Cancel last.
 */
@Composable
private fun RenameButtons(cancel: String, save: String, onCancel: () -> Unit, onSave: () -> Unit) {
    val measurer = rememberTextMeasurer()
    val accessibilitySize = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val half = with(LocalDensity.current) { ((ALERT_WIDTH - ALERT_BUTTON_SIDE * 2 - ALERT_BUTTON_GAP) / 2 - ALERT_BUTTON_PADDING * 2).roundToPx() }
    val font = alertButtonFont()
    val stacked = accessibilitySize || listOf(cancel, save).any { measurer.measure(it, font, maxLines = 1, softWrap = false).size.width > half }
    val side = Modifier.padding(horizontal = ALERT_BUTTON_SIDE - ALERT_SIDE)
    if (stacked) {
        Column(side.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(ALERT_BUTTON_GAP)) {
            AlertButton(save, RENAME_SAVE, Modifier.fillMaxWidth(), onSave)
            AlertButton(cancel, RENAME_CANCEL, Modifier.fillMaxWidth(), onCancel)
        }
    } else {
        Row(side.fillMaxWidth().height(IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(ALERT_BUTTON_GAP)) {
            AlertButton(cancel, RENAME_CANCEL, Modifier.weight(1f).fillMaxHeight(), onCancel)
            AlertButton(save, RENAME_SAVE, Modifier.weight(1f).fillMaxHeight(), onSave)
        }
    }
}

/** The list's or the page's alert of what failed ([ErrorAlert]), which closes it ([DeliveriesState.closeError]). */
@Composable
private fun ActionErrorAlert(state: DeliveriesState, message: String) = ErrorAlert(message, state::closeError)

/**
 * `.alert("native.errorTitle")` with the error's words and Close, as iOS 26 draws an alert ([AlertCard]): the title,
 * the words under it as the delete question's message, and one Close as wide as the alert, which calls `close`, as the
 * back does. TalkBack starts at the words.
 */
@Composable
internal fun ErrorAlert(message: String, close: () -> Unit) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    val said = remember(message) { AccessibilityFocus() }
    LaunchedEffect(message) { said.request() }
    BackHandler(onBack = close)
    Box(
        Modifier
            .fillMaxSize()
            .testTag(ACTION_ERROR)
            .background(DIM)
            .pointerInput(Unit) { detectTapGestures { } },
        contentAlignment = Alignment.Center,
    ) {
        AlertCard(localizer.text("native.errorTitle")) {
            Column(
                Modifier
                    .padding(horizontal = ALERT_BUTTON_SIDE - ALERT_SIDE)
                    .semantics(mergeDescendants = true) {}
                    .accessibilityFocus(said),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MESSAGE_GAP),
            ) {
                Text(localizer.text("native.errorTitle"), color = colors.label, style = type.body.uiKitLines(IosTextStyle.Body), textAlign = TextAlign.Center)
                Text(message, color = colors.secondaryLabel, style = type.subheadline.uiKitLines(IosTextStyle.Subheadline), textAlign = TextAlign.Center)
            }
            Spacer(Modifier.height(ALERT_BUTTONS_GAP))
            AlertButton(localizer.text("common.close"), ACTION_ERROR_CLOSE, Modifier.padding(horizontal = ALERT_BUTTON_SIDE - ALERT_SIDE).fillMaxWidth(), close)
        }
    }
}

/**
 * An alert as iOS 26 draws it: a glass card 320 wide, the same on a tablet, with continuous corners, named [title]
 * for TalkBack, what it holds in a column 15 in from its sides, under its title's room at the top and 16 over its
 * foot, scrolling where the room [modifier] gives it is shorter.
 */
@Composable
private fun AlertCard(title: String, modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    GlassSurface(ContinuousRoundedShape(ALERT_RADIUS), Modifier.width(ALERT_WIDTH).then(modifier).semantics { paneTitle = title }) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = ALERT_SIDE, top = ALERT_TOP, end = ALERT_SIDE, bottom = ALERT_BUTTON_SIDE),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content,
        )
    }
}

/**
 * A button of an alert or of the delete question as iOS 26 draws it: a capsule at least 48 tall in the alert's fill
 * ([alertFill]), its title at the body size, medium, in the label colour (an alert's buttons take no tint, and Delete
 * has no role), darkening under the finger as the bar's glass buttons do ([RowHighlight.Capsule]).
 */
@Composable
private fun AlertButton(title: String, tag: String, modifier: Modifier, action: () -> Unit) {
    Box(
        modifier
            .heightIn(min = ALERT_CAPSULE_HEIGHT)
            .clip(CapsuleShape)
            .background(alertFill(PeekTheme.colors.dark))
            .testTag(tag)
            .clickable(interactionSource = null, indication = RowHighlight.Capsule, role = Role.Button, onClick = action)
            .padding(horizontal = ALERT_BUTTON_PADDING, vertical = ALERT_CAPSULE_PADDING),
        contentAlignment = Alignment.Center,
    ) {
        Text(title, color = PeekTheme.colors.label, style = alertButtonFont(), textAlign = TextAlign.Center)
    }
}

@Composable
private fun alertButtonFont(): TextStyle = PeekTheme.type.style(IosTextStyle.Body, FontWeight.Medium).sfLines()

/**
 * An alert's field and buttons on its glass: in light the system's tertiary fill as the glass's vibrancy deepens it,
 * measured on the iPhone's alert; in dark, which wasn't measured, white at a tenth, the tertiary fill on dark glass.
 */
private fun alertFill(dark: Boolean): Color = if (dark) ALERT_FILL_DARK else ALERT_FILL_LIGHT

/**
 * Lines as UIKit sets an alert's words: each as tall as the text style's leading at Large (22 for the body's 17
 * points, 20 for the subheadline's 15), the words in the middle of it.
 */
private fun TextStyle.uiKitLines(style: IosTextStyle): TextStyle = copy(
    lineHeight = when (style) {
        IosTextStyle.Subheadline -> 20f / 15f
        else -> 22f / 17f
    }.em,
    lineHeightStyle = LineHeightStyle(alignment = LineHeightStyle.Alignment.Center, trim = LineHeightStyle.Trim.None),
)

private val DIM = Color.Black.copy(alpha = 0.2f)
internal val MENU_WIDTH = 250.dp
internal val MENU_RADIUS = 24.dp
internal val MENU_PADDING = 10.dp
internal val MENU_MARGIN = 21.dp
private val MENU_GAP = 21.dp
private val PREVIEW_SIDE = 22.dp
private val ROW_HEIGHT = 42.dp

/**
 * The room above and below a row's words, measured on the iPhone's "Copy tracking number": two lines of the body's 17
 * (20.29 each) in a row 62 tall. A row of one line stays [ROW_HEIGHT].
 */
private val ROW_PADDING = 10.7.dp
private val SEPARATOR_BLOCK = 21.dp

/** A line between groups, 24 in from the glass's sides as the iPhone's. */
private val SEPARATOR_INSET = 24.dp

/** An alert as the iPhone draws it in iOS 26 ([AlertCard]), measured on its rename alert. */
private val ALERT_RADIUS = 34.dp
private val ALERT_WIDTH = 320.dp
private val ALERT_SIDE = 15.dp
private val ALERT_TOP = 22.dp
private val ALERT_FIELD_GAP = 14.dp
private val ALERT_BUTTONS_GAP = 20.dp

/** An alert's buttons: 16 from its sides and its foot, 8 apart, each a capsule at least 48 tall. */
private val ALERT_BUTTON_SIDE = 16.dp
private val ALERT_BUTTON_GAP = 8.dp
private val ALERT_CAPSULE_HEIGHT = 48.dp
private val ALERT_CAPSULE_PADDING = 12.dp
private val ALERT_FILL_LIGHT = Color(118, 118, 128).copy(alpha = 0.3f)
private val ALERT_FILL_DARK = Color.White.copy(alpha = 0.1f)

/** The words in the rename field, 15 in from its ends. */
private val FIELD_INSET = 15.dp

/** Between an alert's or the delete question's title and its message. */
private val MESSAGE_GAP = 6.dp

/** The room on either side of an alert's button title. */
private val ALERT_BUTTON_PADDING = 8.dp

/**
 * The delete question, as wide as iOS 26 draws it on a phone and in a regular window, and its corners; the page's 10 in
 * from the window's trailing edge, 10 past its •••'s.
 */
private val DELETE_WIDTH = 238.dp
private val DELETE_WIDTH_REGULAR = 288.dp
private val DELETE_RADIUS = 34.dp
private val DELETE_RADIUS_REGULAR = 30.dp
private val DELETE_SIDE = 10.dp
private val DELETE_OUTSET = 10.dp

/** The question and its message 30 in from the delete question's sides, 14 in from its button's. */
private val QUESTION_INSET = 14.dp
private val DELETE_BUTTON_GAP = 19.dp
internal val MENU_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.12f), radius = 24.dp, y = 8.dp)
private val PREVIEW_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.1f), radius = 16.dp, y = 6.dp)

const val CARD_MENU = "deliveries.cardMenu"
const val CARD_MENU_PREVIEW = "deliveries.cardMenu.preview"
const val DELETE_CONFIRM = "deliveries.deleteConfirmation.delete"
const val DELETE_POPOVER = "deliveries.deleteConfirmation.popover"
const val RENAME_ALERT = "deliveries.renameAlert"
const val RENAME_FIELD = "deliveries.renameAlert.field"
const val RENAME_CANCEL = "deliveries.renameAlert.cancel"
const val RENAME_SAVE = "deliveries.renameAlert.save"
const val ACTION_ERROR = "deliveries.actionError"
const val ACTION_ERROR_CLOSE = "deliveries.actionError.close"

internal fun menuItemTag(item: ParcelMenuItem): String = "deliveries.cardMenu.${item.name}"
