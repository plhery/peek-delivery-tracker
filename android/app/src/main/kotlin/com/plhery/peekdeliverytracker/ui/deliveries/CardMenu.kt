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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.launch

/**
 * A card, or an archived row, whose menu can open: where it stands in the window and its picture, which the menu lifts
 * over the dimmed screen in the card's own shape (`.contentShape(.contextMenuPreview, shape)`), on `fill` where the
 * card has no ground of its own (a row of the archive's surface), as iOS lifts a preview on a platter. [modifier] goes
 * on the outside of the card; while its menu is open the card is drawn only there, lifted, as iOS hides the card it
 * lifts.
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

    var bounds: Rect = Rect.Zero
        private set

    val modifier: Modifier = Modifier
        .onGloballyPositioned { bounds = it.boundsInWindow() }
        .drawWithContent {
            picture.record { this@drawWithContent.drawContent() }
            if (state.menu?.source !== this@CardMenuSource) drawLayer(picture)
        }

    /** The long press: the menu opens over the screen. */
    fun open() {
        if (state.menu == null) state.menu = CardMenuRequest(this)
    }
}

@Composable
internal fun rememberCardMenu(state: DeliveriesState, parcel: Parcel, cornerRadius: Dp, fill: Color? = null): CardMenuSource {
    val picture = rememberGraphicsLayer()
    val source = remember(state, parcel.id, cornerRadius, fill, picture) { CardMenuSource(parcel, cornerRadius, fill, picture, state) }
    SideEffect { source.parcel = parcel }
    return source
}

/** The menu open over the list: the card it came from, and its parcel's items. */
@Stable
internal class CardMenuRequest(val source: CardMenuSource) {
    val parcel: Parcel get() = source.parcel
}

/**
 * Where the menu stands beside the lifted card, as iOS places a context menu: under the card when it fits, above it
 * otherwise, and the card moved just enough for the menu to fit when neither does. All in pixels in the window.
 */
internal data class CardMenuPlacement(val card: Rect, val scale: Float, val menuTop: Float, val menuLeft: Float, val above: Boolean)

internal fun cardMenuPlacement(
    card: Rect,
    menuWidth: Float,
    menuHeight: Float,
    windowWidth: Float,
    windowHeight: Float,
    top: Float,
    bottom: Float,
    margin: Float,
    gap: Float,
    side: Float,
): CardMenuPlacement {
    // A card as wide as the window is drawn a little smaller, so it keeps the menu's margins at its sides.
    val scale = min(1f, (windowWidth - 2 * side) / card.width)
    val width = card.width * scale
    val height = card.height * scale
    var cardTop = card.center.y - height / 2
    val left = card.center.x - width / 2
    val lowest = windowHeight - bottom - margin
    val highest = top + margin
    val above = cardTop + height + gap + menuHeight > lowest
    if (above) {
        cardTop = max(cardTop, highest + menuHeight + gap)
        cardTop = min(cardTop, lowest - height)
    } else {
        cardTop = max(cardTop, highest)
    }
    val menuTop = if (above) cardTop - gap - menuHeight else cardTop + height + gap
    val menuLeft = (card.center.x - menuWidth / 2).coerceIn(side, max(side, windowWidth - side - menuWidth))
    return CardMenuPlacement(Rect(left, cardTop, left + width, cardTop + height), scale, menuTop, menuLeft, above)
}

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
    val current by rememberUpdatedState(content)
    DisposableEffect(host, content != null) {
        host.content = if (content != null) { { current?.invoke() } } else null
        onDispose { host.content = null }
    }
}

/**
 * The card's menu (A-S41, A-G04): the screen dims, the card lifts in its shape, and the parcel's items stand beside it on
 * glass in their groups, each with its symbol, and the keys that press them on the parcel the keyboard acts on. A tap
 * outside, the back gesture or a choice closes it; a choice acts once it has closed.
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
        val safe = WindowInsets.safeDrawing
        var origin by remember { mutableStateOf(Offset.Zero) }
        val menuWidth = with(density) { MENU_WIDTH.toPx() }
        var menuHeight by remember(request) { mutableIntStateOf(0) }
        val card = request.source.bounds.translate(-origin)
        val placement = with(density) {
            cardMenuPlacement(
                card, menuWidth, menuHeight.toFloat(), windowWidth, windowHeight,
                top = safe.getTop(this).toFloat(), bottom = safe.getBottom(this).toFloat(),
                margin = MENU_MARGIN.toPx(), gap = MENU_GAP.toPx(), side = PREVIEW_SIDE.toPx(),
            )
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
                .drawBehind { drawLayer(request.source.picture) }
                .clearAndSetSemantics {},
        )
        // The menu, growing out of the card's side.
        GlassSurface(
            ContinuousRoundedShape(MENU_RADIUS),
            Modifier
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints.copy(minWidth = menuWidth.toInt(), maxWidth = menuWidth.toInt(), minHeight = 0))
                    menuHeight = placeable.height
                    layout(placeable.width, placeable.height) {
                        placeable.place(placement.menuLeft.toInt(), placement.menuTop.toInt())
                    }
                }
                .graphicsLayer {
                    val t = shown.value
                    alpha = t.coerceIn(0f, 1f)
                    val scale = if (reduceMotion) 1f else 0.6f + 0.4f * t
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin(0.5f, if (placement.above) 1f else 0f)
                }
                .pointerInput(Unit) { detectTapGestures { } }
                .semantics { paneTitle = parcel.label.ifEmpty { localizer.text("common.parcel") } }
                .dropShadow(ContinuousRoundedShape(MENU_RADIUS), MENU_SHADOW),
        ) {
            Column(Modifier.padding(vertical = MENU_PADDING)) {
                items.forEachIndexed { index, group ->
                    if (index > 0) MenuSeparator()
                    for (item in group) MenuItemRow(item, shortcuts, choose)
                }
            }
        }
    }
}

@Composable
private fun MenuItemRow(item: ParcelMenuItem, shortcuts: Boolean, choose: (ParcelMenuItem) -> Unit) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    val title = localizer.text(item.titleKey)
    val key = if (shortcuts) item.shortcutLabel else null
    // The menu's words and symbols are vibrant on its glass: the label a shade lighter.
    val ink = colors.label.copy(alpha = 0.8f)
    Row(
        Modifier
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
                        Modifier.padding(horizontal = 16.dp, vertical = 14.dp).semantics(mergeDescendants = true) {},
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
                    Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 16.dp).semantics(mergeDescendants = true) {},
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
const val DELETE_CONFIRMATION = "deliveries.deleteConfirmation"
const val DELETE_CONFIRM = "deliveries.deleteConfirmation.delete"
const val DELETE_CANCEL = "deliveries.deleteConfirmation.cancel"
const val ACTION_ERROR = "deliveries.actionError"
const val ACTION_ERROR_CLOSE = "deliveries.actionError.close"

internal fun menuItemTag(item: ParcelMenuItem): String = "deliveries.cardMenu.${item.name}"
