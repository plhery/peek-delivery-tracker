package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.res.Configuration
import android.view.KeyEvent
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.RootForTest
import androidx.compose.ui.node.SemanticsModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.core.view.ViewCompat
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.TabOverlay
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlinx.coroutines.Job
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
    var bounds: Rect = Rect.Zero
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

/** A card's menu, which closes as the card goes: off the list, or as the window turns and the list is drawn anew. */
@Composable
internal fun rememberCardMenu(state: DeliveriesState, parcel: Parcel, cornerRadius: Dp, fill: Color? = null): CardMenuSource {
    val picture = rememberGraphicsLayer()
    val source = remember(state, parcel.id, cornerRadius, fill, picture) { CardMenuSource(parcel, cornerRadius, fill, picture, state) }
    SideEffect { source.parcel = parcel }
    DisposableEffect(source) {
        onDispose {
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
 * The open menu, the delete it asks about, or the alert of an action that failed, over the whole window where the tab
 * shell is there to draw it ([LocalTabOverlay]), over the list otherwise. Nothing while none is open.
 */
@Composable
internal fun CardMenuHost(state: DeliveriesState) {
    val request = state.menu
    val deleting = state.deleting
    val error = state.actionError
    val host = LocalTabOverlay.current
    // Once the menu and what it opened have all closed, TalkBack goes back to the card the menu came from.
    val modal = state.modal
    LaunchedEffect(modal) {
        if (modal) return@LaunchedEffect
        state.returnFocus?.request()
        state.returnFocus = null
    }
    val content: (@Composable () -> Unit)? = when {
        request != null -> { { CardMenuOverlay(state, request) } }
        deleting != null -> { { DeleteConfirmation(state, deleting) } }
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
    val reduceMotion = PeekTheme.reduceMotion
    val scope = rememberCoroutineScope()
    val shown = remember(request) { Animatable(0f) }
    var closing by remember(request) { mutableIntStateOf(0) }
    val parcel = request.parcel
    val items = remember(parcel) { ParcelMenuItem.menu(parcel, onCard = true, reader = state.source.viewerLink(parcel) != null) }
    val shortcuts = parcel.id == state.selectedCard && keyboardConnected()
    val first = remember(request) { AccessibilityFocus() }

    LaunchedEffect(request) { first.request() }
    LaunchedEffect(request) { shown.animateTo(1f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.3, 0.15)) }
    fun close(then: () -> Unit = {}) {
        if (closing > 0) return
        closing++
        scope.launch {
            shown.animateTo(0f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.2))
            state.menu = null
            then()
        }
    }
    val choose = { item: ParcelMenuItem -> close { state.chose(item, parcel) } }
    BackHandler { close() }
    MenuKeys(shortcuts, items, choose)

    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .testTag(CARD_MENU)
            .pointerInput(request) { detectTapGestures { close() } },
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
                .graphicsLayer {
                    val t = shown.value
                    alpha = t.coerceIn(0f, 1f)
                    val scale = if (reduceMotion) 1f else 0.6f + 0.4f * t
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = when (placement.side) {
                        MenuSide.Below -> TransformOrigin(0.5f, 0f)
                        MenuSide.Above -> TransformOrigin(0.5f, 1f)
                        MenuSide.Right -> TransformOrigin(0f, 0f)
                        MenuSide.Left -> TransformOrigin(1f, 0f)
                    }
                }
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
                items.forEachIndexed { index, group ->
                    if (index > 0) MenuSeparator()
                    for (item in group) {
                        MenuItemRow(item, shortcuts, choose, if (item == items.first().first()) Modifier.accessibilityFocus(first) else Modifier)
                    }
                }
            }
        }
    }
}

@Composable
private fun MenuItemRow(item: ParcelMenuItem, shortcuts: Boolean, choose: (ParcelMenuItem) -> Unit, modifier: Modifier = Modifier) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    val title = localizer.text(item.titleKey)
    val key = if (shortcuts) item.shortcutLabel else null
    // The menu's words and symbols are vibrant on its glass: the label a shade lighter.
    val ink = colors.label.copy(alpha = 0.8f)
    Row(
        modifier
            .fillMaxWidth()
            .heightIn(min = ROW_HEIGHT)
            .testTag(menuItemTag(item))
            .clickable(interactionSource = null, indication = PlainPress) { choose(item) }
            .clearAndSetSemantics {
                contentDescription = listOfNotNull(title, key).joinToString(", ")
                role = Role.Button
                onClick { choose(item); true }
            }
            .padding(start = 26.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.width(26.dp), contentAlignment = Alignment.Center) {
            PeekSymbol(item.symbol, font = type.body, tint = ink)
        }
        Spacer(Modifier.width(12.dp))
        Text(title, Modifier.weight(1f), color = ink, style = type.body.sfLines())
        if (key != null) {
            Text(key, Modifier.padding(start = 8.dp), color = colors.secondaryLabel, style = type.body.sfLines(), textAlign = TextAlign.End)
        }
    }
}

@Composable
private fun MenuSeparator() {
    Box(Modifier.fillMaxWidth().height(SEPARATOR_BLOCK), contentAlignment = Alignment.Center) {
        Box(Modifier.padding(horizontal = 20.dp).fillMaxWidth().height(1.dp).background(PeekTheme.colors.separator))
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
private fun MenuKeys(shortcuts: Boolean, items: List<List<ParcelMenuItem>>, choose: (ParcelMenuItem) -> Unit) {
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
private fun keyboardConnected(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.keyboard != Configuration.KEYBOARD_NOKEYS &&
        configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO
}

/**
 * `parcelDeleteConfirmation` (iOS's `confirmationDialog`, its title shown): an action sheet at the foot of the screen
 * asking before the parcel goes for good, its message under the question, Delete, and Cancel apart under it.
 */
@Composable
private fun DeleteConfirmation(state: DeliveriesState, parcel: Parcel) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    val shown = remember(parcel) { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val reduceMotion = PeekTheme.reduceMotion
    val question = remember(parcel) { AccessibilityFocus() }
    LaunchedEffect(parcel) { question.request() }
    LaunchedEffect(parcel) { shown.animateTo(1f, Motion.easeOut(if (reduceMotion) 0.2 else 0.3)) }
    fun answer(delete: Boolean) {
        scope.launch {
            shown.animateTo(0f, Motion.easeOut(0.2))
            state.deleting = null
            if (delete) state.delete(parcel)
        }
    }
    BackHandler { answer(delete = false) }
    Box(
        Modifier
            .fillMaxSize()
            .testTag(DELETE_CONFIRMATION)
            .graphicsLayer { alpha = shown.value }
            .background(DIM)
            .pointerInput(parcel) { detectTapGestures { answer(delete = false) } },
    ) {
        val safe = WindowInsets.safeDrawing
        val bottom = with(LocalDensity.current) { safe.getBottom(this).toDp() }
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .widthIn(max = 500.dp)
                .padding(start = 8.dp, end = 8.dp, bottom = bottom + 8.dp)
                .graphicsLayer { translationY = (1 - shown.value) * size.height * if (reduceMotion) 0f else 1f }
                .pointerInput(Unit) { detectTapGestures { } },
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            GlassSurface(ContinuousRoundedShape(SHEET_RADIUS), Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Column(
                        Modifier.padding(horizontal = 16.dp, vertical = 14.dp).semantics(mergeDescendants = true) {}.accessibilityFocus(question),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(localizer.text("detail.deleteQuestion"), color = colors.secondaryLabel, style = type.style(IosTextStyle.Footnote, FontWeight.SemiBold).sfLines(), textAlign = TextAlign.Center)
                        Text(localizer.text("detail.deleteDescription"), color = colors.secondaryLabel, style = type.style(IosTextStyle.Footnote).sfLines(), textAlign = TextAlign.Center)
                    }
                    Box(Modifier.fillMaxWidth().height(1.dp).background(colors.separator))
                    SheetButton(localizer.text("detail.delete"), bold = false, tag = DELETE_CONFIRM) { answer(delete = true) }
                }
            }
            GlassSurface(ContinuousRoundedShape(SHEET_RADIUS), Modifier.fillMaxWidth()) {
                SheetButton(localizer.text("common.cancel"), bold = true, tag = DELETE_CANCEL) { answer(delete = false) }
            }
        }
    }
}

/** `.alert("native.errorTitle")` with the error's words and Close, as iOS draws an alert. */
@Composable
private fun ActionErrorAlert(state: DeliveriesState, message: String) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    val close = { state.actionError = null }
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
        GlassSurface(ContinuousRoundedShape(ALERT_RADIUS), Modifier.width(ALERT_WIDTH).semantics { paneTitle = localizer.text("native.errorTitle") }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 16.dp).semantics(mergeDescendants = true) {}.accessibilityFocus(said),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(localizer.text("native.errorTitle"), color = colors.label, style = type.style(IosTextStyle.Headline).sfLines(), textAlign = TextAlign.Center)
                    Text(message, color = colors.label, style = type.style(IosTextStyle.Footnote).sfLines(), textAlign = TextAlign.Center)
                }
                Box(Modifier.fillMaxWidth().height(1.dp).background(colors.separator))
                SheetButton(localizer.text("common.close"), bold = true, tag = ACTION_ERROR_CLOSE, height = 44.dp, action = close)
            }
        }
    }
}

@Composable
private fun SheetButton(title: String, bold: Boolean, tag: String, height: Dp = 56.dp, action: () -> Unit) {
    val type = PeekTheme.type
    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(min = height)
            .testTag(tag)
            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = action),
        contentAlignment = Alignment.Center,
    ) {
        Text(title, color = PeekTheme.colors.accent, style = type.style(IosTextStyle.Body, if (bold) FontWeight.SemiBold else FontWeight.Normal).sfLines())
    }
}

private val DIM = Color.Black.copy(alpha = 0.2f)
private val MENU_WIDTH = 250.dp
private val MENU_RADIUS = 24.dp
private val MENU_PADDING = 10.dp
private val MENU_MARGIN = 21.dp
private val MENU_GAP = 21.dp
private val PREVIEW_SIDE = 22.dp
private val ROW_HEIGHT = 42.dp
private val SEPARATOR_BLOCK = 21.dp
private val SHEET_RADIUS = 14.dp
private val ALERT_RADIUS = 14.dp
private val ALERT_WIDTH = 270.dp
private val MENU_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.12f), radius = 24.dp, y = 8.dp)
private val PREVIEW_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.1f), radius = 16.dp, y = 6.dp)

const val CARD_MENU = "deliveries.cardMenu"
const val CARD_MENU_PREVIEW = "deliveries.cardMenu.preview"
const val DELETE_CONFIRMATION = "deliveries.deleteConfirmation"
const val DELETE_CONFIRM = "deliveries.deleteConfirmation.delete"
const val DELETE_CANCEL = "deliveries.deleteConfirmation.cancel"
const val ACTION_ERROR = "deliveries.actionError"
const val ACTION_ERROR_CLOSE = "deliveries.actionError.close"

internal fun menuItemTag(item: ParcelMenuItem): String = "deliveries.cardMenu.${item.name}"

/**
 * Where TalkBack's focus can be sent ([request]): the element [accessibilityFocus] is on, which TalkBack reads as one
 * (or the first such in it), as VoiceOver moves to a menu or an alert as it opens and back to its card as it closes.
 */
@Stable
internal class AccessibilityFocus {
    internal var node: AccessibilityFocusNode? = null

    /** Moves TalkBack's focus there once it is laid out and TalkBack can reach it; nothing while TalkBack is off. */
    fun request() {
        node?.request()
    }
}

internal fun Modifier.accessibilityFocus(focus: AccessibilityFocus): Modifier = this then AccessibilityFocusElement(focus)

private data class AccessibilityFocusElement(val focus: AccessibilityFocus) : ModifierNodeElement<AccessibilityFocusNode>() {
    override fun create() = AccessibilityFocusNode(focus)

    override fun update(node: AccessibilityFocusNode) {
        node.focus = focus
    }
}

internal class AccessibilityFocusNode(focus: AccessibilityFocus) :
    Modifier.Node(), SemanticsModifierNode, CompositionLocalConsumerModifierNode {
    var focus: AccessibilityFocus = focus
        set(value) {
            if (field.node === this) field.node = null
            field = value
            if (isAttached) value.node = this
        }

    private var pending: Job? = null

    override fun onAttach() {
        focus.node = this
    }

    override fun onDetach() {
        if (focus.node === this) focus.node = null
    }

    // Marks the element, without adding one, so that it can be found among the window's.
    override fun SemanticsPropertyReceiver.applySemantics() {
        this[FocusMark] = this@AccessibilityFocusNode
    }

    fun request() {
        val view = currentValueOf(LocalView)
        val manager = view.context.getSystemService(AccessibilityManager::class.java)
        if (manager == null || !manager.isEnabled || !manager.isTouchExplorationEnabled) return
        pending?.cancel()
        pending = coroutineScope.launch {
            // Once laid out, and once what covered it from TalkBack has gone: within a few frames.
            repeat(FOCUS_FRAMES) {
                withFrameNanos {}
                val id = target(view)?.id ?: return@repeat
                val provider = view.accessibilityNodeProvider ?: return@launch
                if (provider.createAccessibilityNodeInfo(id)?.isAccessibilityFocused == true) return@launch
                if (provider.performAction(id, AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS, null)) return@launch
            }
        }
    }

    /**
     * What TalkBack stops on, while it can reach it: the element it reads the marked one in (the marked one itself, or
     * the one it is merged into) when that says something, else the first in it that does. The window's elements are
     * found through its merged semantics, the elements TalkBack reads.
     */
    private fun target(view: View): SemanticsNode? {
        val root = (view as? RootForTest)?.semanticsOwner?.rootSemanticsNode ?: return null
        val marked = find(root) { it.config.getOrNull(FocusMark) === this } ?: return null
        return find(marked) { it.speaks() }
    }

    private fun SemanticsNode.speaks(): Boolean = config.isMergingSemanticsOfDescendants || config.isClearingSemantics ||
        SemanticsProperties.ContentDescription in config || SemanticsProperties.Text in config

    private fun find(from: SemanticsNode, matches: (SemanticsNode) -> Boolean): SemanticsNode? {
        val queue = ArrayDeque(listOf(from))
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (matches(node)) return node
            queue.addAll(node.children)
        }
        return null
    }
}

private val FocusMark = SemanticsPropertyKey<AccessibilityFocusNode>("AccessibilityFocus", mergePolicy = { parent, child -> parent ?: child })

private const val FOCUS_FRAMES = 10
