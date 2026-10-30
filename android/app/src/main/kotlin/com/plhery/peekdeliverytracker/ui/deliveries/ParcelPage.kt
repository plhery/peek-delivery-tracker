package com.plhery.peekdeliverytracker.ui.deliveries

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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.ParcelPageColumns
import com.plhery.peekdeliverytracker.core.activeTrackingCarrier
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.hasCarrierUpdate
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.symbols.SymbolScale
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.tabContentPadding
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import java.util.UUID
import kotlin.math.max
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

/**
 * Where the page starts, when something other than its own bar stands over its top: the demo's bar, which [DemoPage]
 * puts it under. Null where the page keeps clear of the bar's row itself.
 */
internal val LocalPageTop = compositionLocalOf<Dp?> { null }

/**
 * ParcelDetailView's frame (B-S01): the parcel with `parcelId` on its page, over the list or beside it.
 *
 * The parcel is the sample Pip holds where that is the page's ([DeliveriesState.sample]), else the parcel of the
 * deliveries with that id or that had it before its carrier changed. The page's paper holds the pass and, under it,
 * the history; given room for two columns beside the list it puts the history beside the pass instead
 * ([ParcelPageColumns]), 28 apart, and in one column it keeps the two 20 apart, a line under the pass. The page
 * scrolls, with no scroll bar, its content 26 apart, 16 in from the window's safe sides, 10 under the bar and 36 over
 * the window's foot, and no wider than the two columns at their widest (1080) or 640 in one, centred; the ground fades
 * out under the bar over what scrolls under it (`softTopEdge()`). Under the paper stands the question Pip asks about the
 * page, once it is ported. No bar names the page; TalkBack hears it as "Parcel details" (`windowTitle`).
 *
 * A parcel the deliveries no longer have (deleted elsewhere, or signed out) shows `ContentUnavailableView` instead:
 * "Parcel", a box, and that it is no longer in the list (B-ST01).
 *
 * Esc goes back to the list from a page over it, and the ••• menu's keys act on the parcel while nothing covers it.
 */
@Composable
internal fun ParcelPage(state: DeliveriesState, parcelId: UUID) {
    val parcels by state.source.parcels.collectAsState()
    val parcel = state.pageParcel(parcelId, parcels)
    val sample = state.sample(parcelId) != null
    val colors = PeekTheme.colors
    val title = localized("detail.label")
    // Under the demo's bar, or clear of the page's own bar's row.
    val top = LocalPageTop.current ?: (barRowTop() + BAR_HEIGHT)
    val padding = tabContentPadding()
    val edges = LocalColumnEdges.current
    val direction = LocalLayoutDirection.current
    val start = if (edges.start) padding.calculateStartPadding(direction) else 0.dp
    val end = if (edges.end) padding.calculateEndPadding(direction) else 0.dp
    val bottom = padding.calculateBottomPadding()
    PageKeys(state, parcel?.takeUnless { sample })
    Box(
        Modifier
            .fillMaxSize()
            .background(colors.background)
            .testTag(PARCEL_PAGE)
            .semantics { paneTitle = title },
    ) {
        if (parcel != null) {
            PageScroll(state, parcel, PageMargins(start, top, end, bottom))
            if (top > 0.dp) SoftTopEdge(top)
        } else {
            ContentUnavailable(
                "shippingbox", localized("common.parcel"), localized("native.parcelMissing"),
                Modifier
                    .align(Alignment.Center)
                    .padding(start = start + PAGE_SIDE, top = top, end = end + PAGE_SIDE, bottom = bottom)
                    .testTag(PARCEL_MISSING),
            )
        }
    }
}

/** The window's safe sides the page keeps clear of, and its top under the bar. */
private class PageMargins(val start: Dp, val top: Dp, val end: Dp, val bottom: Dp)

/** The page's ScrollView: the paper, in one column or two, and the question under it. */
@Composable
private fun PageScroll(state: DeliveriesState, parcel: Parcel, margins: PageMargins) {
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val history = showsHistory(parcel)
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val columns = ParcelPageColumns.fit(
            maxWidth.value.toDouble(), besideList = state.arrangement == DeliveriesArrangement.SideBySide, largeText = largeText, history = history,
        )
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = margins.start, top = margins.top + PAGE_TOP, end = margins.end, bottom = margins.bottom + PAGE_BOTTOM)
                .fillMaxWidth()
                .wrapContentWidth()
                .widthIn(max = if (columns) ParcelPageColumns.widest.dp else SINGLE_COLUMN_WIDTH)
                .padding(horizontal = PAGE_SIDE)
                .testTag(PAGE_CONTENT),
            verticalArrangement = Arrangement.spacedBy(PAGE_SPACING),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val ground = PeekTheme.colors.paper
            fun paper(tag: String) = Modifier
                .testTag(tag)
                .fillMaxWidth()
                .background(ground, ContinuousRoundedShape(PAPER_RADIUS))
                .padding(PAPER_PADDING)
            if (columns) {
                // Given the room, the history stands beside the pass rather than under it.
                Row(paper(PAGE_COLUMNS), horizontalArrangement = Arrangement.spacedBy(ParcelPageColumns.gap.dp)) {
                    LiveParcelPass(parcel, divided = false, Modifier.weight(1f))
                    PageHistory(parcel, history, Modifier.weight(1f))
                }
            } else {
                Column(paper(PAGE_SINGLE_COLUMN), verticalArrangement = Arrangement.spacedBy(STACK_SPACING)) {
                    LiveParcelPass(parcel, divided = true, Modifier.fillMaxWidth())
                    PageHistory(parcel, history, Modifier.fillMaxWidth())
                }
            }
            FeedbackQuestion(Modifier.widthIn(max = FEEDBACK_WIDTH).padding(horizontal = FEEDBACK_SIDE))
        }
    }
}

/** The page shows what the carrier answered: a carrier is being asked, or one has answered (`showsHistory`). */
private fun showsHistory(parcel: Parcel): Boolean =
    CarrierCatalog.shared.tracksAutomatically(parcel.activeTrackingCarrier) || parcel.hasCarrierUpdate

/**
 * `liveParcelPass`: the pass, then what the parcel waits for and what its carrier says, then its numbers, links and
 * details, 12 apart, a line under them where the history stands under the pass (`divided`).
 */
@Composable
private fun LiveParcelPass(parcel: Parcel, divided: Boolean, modifier: Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(PASS_SPACING)) {
        PassHeader(parcel)
        PickupPoint(parcel)
        CarrierNotes(parcel)
        ShipmentIdentity(parcel)
        if (divided) Box(Modifier.fillMaxWidth().height(1.dp).background(PeekTheme.colors.separator))
    }
}

/**
 * The pass at the page's top: the carrier's marks and buttons, the map, the name, the stamp, the sender and the
 * status with its steps. Until it is ported, the parcel's name, as the pass titles it.
 */
@Composable
private fun PassHeader(parcel: Parcel) {
    val name = parcel.label.ifEmpty { localized("common.parcel") }
    Text(
        name,
        Modifier.semantics { heading() },
        color = PeekTheme.colors.label,
        style = PeekTheme.type.style(IosTextStyle.Title2, FontWeight.Bold),
    )
}

/** `pickupPointCard`, while the parcel waits at its pickup point. Not ported yet: nothing shows. */
@Suppress("UNUSED_PARAMETER")
@Composable
private fun PickupPoint(parcel: Parcel) = Unit

/** What the carrier says the parcel waits for, and `ParcelQuietNote` while it goes quiet. Not ported yet. */
@Suppress("UNUSED_PARAMETER")
@Composable
private fun CarrierNotes(parcel: Parcel) = Unit

/** `shipmentIdentity`: the numbers and the carrier's links, then the details and what a check said. Not ported yet. */
@Suppress("UNUSED_PARAMETER")
@Composable
private fun ShipmentIdentity(parcel: Parcel) = Unit

/** The history's column: `ParcelJournal` where the page shows what the carrier answered, then the sync line, 20 apart. */
@Composable
private fun PageHistory(parcel: Parcel, history: Boolean, modifier: Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(STACK_SPACING)) {
        if (history) ParcelJournal(parcel)
        SyncStatus(parcel)
    }
}

/** `ParcelJournal`, the tracking history. Not ported yet. */
@Suppress("UNUSED_PARAMETER")
@Composable
private fun ParcelJournal(parcel: Parcel) = Unit

/** `syncStatus`: when the carrier was last asked and last said something, and asking again. Not ported yet. */
@Suppress("UNUSED_PARAMETER")
@Composable
private fun SyncStatus(parcel: Parcel) = Unit

/**
 * Below the paper, on the ground it lies on, Pip asks whether the page is right (`ParcelFeedbackQuestionView`), 8 in
 * from its sides and no wider than 608. Not ported yet: it asks nothing.
 */
@Suppress("UNUSED_PARAMETER")
@Composable
private fun FeedbackQuestion(modifier: Modifier) = Unit

/**
 * The page's bar over the list, which [ZoomStack][com.plhery.peekdeliverytracker.ui.zoom.ZoomStack] keeps at the
 * window's top as the page zooms under it: ‹ on its glass at the leading edge, back to the list as the system's back
 * goes, and the parcel's ••• menu on its own at the trailing edge while the page shows a parcel of the deliveries
 * (none for the sample, nor for a parcel gone). No title: the pass names the parcel.
 */
@Composable
internal fun ParcelPageBar(state: DeliveriesState, parcelId: UUID) {
    val parcels by state.source.parcels.collectAsState()
    val parcel = parcelId.takeIf { state.sample(it) == null }?.let { state.pageParcel(it, parcels) }
    val padding = tabContentPadding()
    val direction = LocalLayoutDirection.current
    val navigator = state.navigator
    Box(Modifier.fillMaxWidth().padding(top = barRowTop()).height(BAR_ROW).testTag(PAGE_BAR)) {
        GlassSurface(
            CapsuleShape,
            Modifier.align(Alignment.CenterStart).offset(x = padding.calculateStartPadding(direction) + BAR_MARGIN).size(BAR_BUTTON),
        ) {
            BarButton(
                "chevron.left", localized("detail.back"), PAGE_BACK, Modifier.align(Alignment.Center), scale = SymbolScale.Large,
            ) { navigator.pop(navigator.navigation.path.dropLast(1)) }
        }
        if (parcel != null) {
            GlassSurface(
                CapsuleShape,
                Modifier.align(Alignment.CenterEnd).offset(x = -(padding.calculateEndPadding(direction) + BAR_MARGIN)).size(BAR_BUTTON),
            ) {
                PageMenuButton(state, parcel, Modifier.align(Alignment.Center))
            }
        }
    }
}

/** The parcel's ••• (`Menu { ParcelMenuButtons }`), which opens its menu from where it stands. */
@Composable
internal fun PageMenuButton(state: DeliveriesState, parcel: Parcel, modifier: Modifier = Modifier) {
    val focus = remember { AccessibilityFocus() }
    var bounds by remember { mutableStateOf(Rect.Zero) }
    BarButton(
        "ellipsis", localized("detail.parcelActions"), PAGE_MENU_BUTTON,
        modifier.onGloballyPositioned { bounds = it.boundsInWindow() }.accessibilityFocus(focus),
    ) {
        if (state.modal) return@BarButton
        state.pageMenu = PageMenuRequest(parcel, bounds)
        state.returnFocus = focus
    }
}

/** A button of the page's bar: a symbol in the ink, semibold, 44 square. */
@Composable
private fun BarButton(
    symbol: String,
    label: String,
    tag: String,
    modifier: Modifier,
    scale: SymbolScale = SymbolScale.Medium,
    action: () -> Unit,
) {
    Box(
        modifier
            .size(BAR_BUTTON)
            .testTag(tag)
            .clickable(interactionSource = null, indication = PressStyle.tactile(0.94f), onClick = action)
            .semantics {
                contentDescription = label
                role = Role.Button
            },
        contentAlignment = Alignment.Center,
    ) {
        PeekSymbol(symbol, font = PeekTheme.type.style(IosTextStyle.Body, FontWeight.SemiBold), scale = scale, tint = PeekTheme.colors.ink)
    }
}

/**
 * The page's keys (ParcelDetailView's `keys`), while the keyboard reaches the page, nothing stands over it and the
 * search field doesn't have it: Esc back to the list from a page over it, and the keys of the ••• menu's items on its
 * parcel, Delete waiting while another change is saved.
 */
@Composable
private fun PageKeys(state: DeliveriesState, parcel: Parcel?) {
    val handle by rememberUpdatedState { event: KeyEvent -> state.pageKey(event, parcel) }
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewCompat.OnUnhandledKeyEventListenerCompat { _, event -> handle(event) }
        ViewCompat.addOnUnhandledKeyEventListener(view, listener)
        onDispose { ViewCompat.removeOnUnhandledKeyEventListener(view, listener) }
    }
}

private fun DeliveriesState.pageKey(event: KeyEvent, parcel: Parcel?): Boolean {
    if (modal || showingFilters || searchFocused) return false
    val act = event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0
    if (arrangement == DeliveriesArrangement.Stacked && event.hasNoModifiers() && event.keyCode == KeyEvent.KEYCODE_ESCAPE) {
        if (act) navigator.pop(navigator.navigation.path.dropLast(1))
        return true
    }
    if (parcel == null) return false
    val items = ParcelMenuItem.menu(parcel, onCard = false, reader = source.viewerLink(parcel) != null).flatten()
    val item = ParcelMenuItem.forShortcut(event, items) ?: return false
    if (act && !(item == ParcelMenuItem.Delete && pageWorking)) choseOnPage(item, parcel)
    return true
}

/** The page's ••• menu open over the window: its parcel, and where its button stands in the window. */
@Stable
internal class PageMenuRequest(val parcel: Parcel, val anchor: Rect)

/** The page's ••• menu, over the whole window where the tab shell is there to draw it ([LocalTabOverlay]). */
@Composable
internal fun PageMenuHost(state: DeliveriesState) {
    val request = state.pageMenu
    val host = LocalTabOverlay.current
    val content: (@Composable () -> Unit)? = request?.let { { PageMenuOverlay(state, it) } }
    if (host == null) {
        content?.invoke()
        return
    }
    OverlayLayer(host, PAGE_MENU, content)
}

/**
 * The ••• menu (B-S09, `Menu { ParcelMenuButtons }`): the parcel's items on glass in their groups, growing out of the
 * button from its top trailing corner, over nothing dimmed; as tall as the window's safe area allows below the button's
 * top, scrolling past that. The keys that press the items show with a keyboard connected, and Delete waits while
 * another change is saved. A tap outside, the back gesture or a choice closes it; a choice acts once it has closed.
 * TalkBack reaches only the menu, from its first item, and goes back to ••• as it closes.
 */
@Composable
private fun PageMenuOverlay(state: DeliveriesState, request: PageMenuRequest) {
    val density = LocalDensity.current
    val reduceMotion = PeekTheme.reduceMotion
    val scope = rememberCoroutineScope()
    val shown = remember(request) { Animatable(0f) }
    var closing by remember(request) { mutableStateOf(false) }
    val parcel = request.parcel
    val items = remember(parcel) { ParcelMenuItem.menu(parcel, onCard = false, reader = state.source.viewerLink(parcel) != null) }
    val shortcuts = keyboardConnected()
    val first = remember(request) { AccessibilityFocus() }
    val title = parcel.label.ifEmpty { localized("common.parcel") }

    LaunchedEffect(request) { first.request() }
    LaunchedEffect(request) { shown.animateTo(1f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.3, 0.15)) }
    fun close(then: () -> Unit = {}) {
        if (closing) return
        closing = true
        scope.launch {
            shown.animateTo(0f, if (reduceMotion) Motion.easeOut(0.2) else Motion.spring(0.2))
            state.pageMenu = null
            then()
        }
    }
    val choose = { item: ParcelMenuItem -> close { state.choseOnPage(item, parcel) } }
    BackHandler { close() }
    MenuKeys(shortcuts, items, choose)

    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .testTag(PAGE_MENU)
            .pointerInput(request) { detectTapGestures { close() } },
    ) {
        val windowWidth = constraints.maxWidth.toFloat()
        val windowHeight = constraints.maxHeight.toFloat()
        val insets = WindowInsets.safeDrawing
        val direction = LocalLayoutDirection.current
        var origin by remember { mutableStateOf(Offset.Zero) }
        val anchor = request.anchor.translate(-origin)
        val menuWidth = with(density) { MENU_WIDTH.toPx() }
        val menu = with(density) {
            val margin = MENU_SIDE.toPx()
            val leftmost = insets.getLeft(this, direction) + margin
            val rightmost = max(leftmost, windowWidth - insets.getRight(this, direction) - margin)
            val left = (anchor.right - menuWidth).coerceIn(leftmost, max(leftmost, rightmost - menuWidth))
            val top = max(anchor.top, insets.getTop(this).toFloat())
            val bottom = max(top, windowHeight - insets.getBottom(this) - MENU_MARGIN.toPx())
            Rect(left, top, left + menuWidth, bottom)
        }
        Box(Modifier.fillMaxSize().onGloballyPositioned { origin = it.positionInWindow() })
        GlassSurface(
            ContinuousRoundedShape(MENU_RADIUS),
            Modifier
                .layout { measurable, _ ->
                    val width = menuWidth.roundToInt()
                    val placeable = measurable.measure(Constraints(width, width, 0, menu.height.roundToInt()))
                    layout(placeable.width, placeable.height) { placeable.place(menu.left.roundToInt(), menu.top.roundToInt()) }
                }
                .graphicsLayer {
                    val t = shown.value
                    alpha = t.coerceIn(0f, 1f)
                    val scale = if (reduceMotion) 1f else 0.6f + 0.4f * t
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin(1f, 0f)
                }
                .pointerInput(Unit) { detectTapGestures { } }
                .semantics { paneTitle = title }
                .dropShadow(ContinuousRoundedShape(MENU_RADIUS), MENU_SHADOW),
        ) {
            Column(Modifier.verticalScroll(rememberScrollState()).padding(vertical = MENU_PADDING)) {
                MenuItems(items, shortcuts, busy = state.pageWorking, choose, first)
            }
        }
    }
}

private val PAGE_SIDE = 16.dp
private val PAGE_TOP = 10.dp
private val PAGE_BOTTOM = 36.dp
private val PAGE_SPACING = 26.dp
private val SINGLE_COLUMN_WIDTH = 640.dp
private val STACK_SPACING = 20.dp
private val PASS_SPACING = 12.dp
private val PAPER_RADIUS = 26.dp
private val PAPER_PADDING = 18.dp
private val FEEDBACK_WIDTH = 608.dp
private val FEEDBACK_SIDE = 8.dp
private val BAR_ROW = 44.dp
private val BAR_BUTTON = 44.dp
private val BAR_MARGIN = 20.dp
private val MENU_SIDE = 16.dp

/** The test tags of the page's parts, its bar's buttons and its menu. */
const val PAGE_CONTENT = "parcelPage.content"
const val PAGE_COLUMNS = "parcelPage.columns"
const val PAGE_SINGLE_COLUMN = "parcelPage.singleColumn"
const val PARCEL_MISSING = "parcelPage.missing"
const val PAGE_BAR = "parcelPage.bar"
const val PAGE_BACK = "parcelPage.back"
const val PAGE_MENU_BUTTON = "parcelPage.menuButton"
const val PAGE_MENU = "parcelPage.menu"
