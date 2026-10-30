package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.ParcelSectionKind
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.core.notice
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.LocalPipSight
import com.plhery.peekdeliverytracker.ui.tabs.tabContentPadding
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.zoom.recedingUnderZoom
import com.plhery.peekdeliverytracker.ui.zoom.zoomSource
import java.util.UUID
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.isArchived
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.Radii
import kotlinx.coroutines.delay

/** Which of the window's sides a column of the deliveries reaches, whose bars and cutout it keeps clear of. */
@Immutable
data class ColumnEdges(val start: Boolean, val end: Boolean)

/** The sides of the window the deliveries' column being drawn reaches: both, unless it stands beside another. */
val LocalColumnEdges = compositionLocalOf { ColumnEdges(start = true, end = true) }

/**
 * The deliveries' list (A-S24): its bar with the mark, adding a parcel, the title and the account (beside a parcel,
 * adding and the account stand over the parcel instead), the demo's bar under it, then the parcels' overview, the
 * search, the empty and all-arrived states, Next up, the cards on their way, the past deliveries, the archive's shelf
 * and the foot. The list scrolls under its bar and the tab bar, edge to edge, its cards in a readable column at most
 * 660 wide. While the parcels first load, it says so in its middle.
 *
 * Its items are what they show ([ListItem]), so a card is drawn again only when its parcel changes, and a card marked
 * as the one beside the list only as that changes.
 *
 * Pulling the list down checks every parcel ([PullToRefresh]); a card swipes to the archive ([SwipeToArchive]) and
 * opens its menu on a long press ([CardMenuHost]), as an archived row does; under the list, over the tab bar, the toast
 * of a parcel just archived offers Undo for seven seconds, and a message says for four what a check found
 * ([BottomControls]).
 *
 * The search's button shows the search field and the filters' button ([SearchControls]); a search, a filter or a sort
 * other than the list's own makes it a custom view, which shows their chips ([FilterChips]), no Next up, the archive
 * as a group under its header and, when nothing matches, a word on it ([NoResults]). The filters open in a sheet, or
 * side by side in a popover off their button ([FiltersHost]).
 *
 * A parcel delivered as the list is looked at keeps its card a moment, postmarked, and paper comes out of Pip's box
 * ([DeliveriesState.postmark]); then the card flies to its new place over the list ([DeliveredFlights]) as the other
 * cards make room.
 *
 * A parcel's page zooms out of its card and back into it ([zoomSource]): the list recedes under the page and the bar's
 * items fade, the bar staying where it is.
 *
 * Out of the iPhone's list: the invitation to turn notifications on (and the device account's row, which a custom view
 * hides), what a card's menu asks (a new name, sharing, another carrier), the sheets (Add, the account) and dropping
 * text on the list. The buttons that would open them call [DeliveriesState]'s hooks, which do nothing yet.
 */
@Composable
internal fun DeliveryList(state: DeliveriesState) {
    val source = state.source
    val session by source.session.collectAsState()
    val loading by source.loading.collectAsState()
    val errorMessage by source.errorMessage.collectAsState()
    val colors = PeekTheme.colors
    val edges = LocalColumnEdges.current
    val sideBySide = state.arrangement == DeliveriesArrangement.SideBySide
    val demo = session == AuthenticationState.Demo
    val guest = session == AuthenticationState.Welcome
    val firstLoad = loading && state.parcels.isEmpty()

    // The window's bars and cutout, on the sides this column reaches, and the tab bar's room at its top or bottom.
    val padding = tabContentPadding()
    val direction = LocalLayoutDirection.current
    val start = if (edges.start) padding.calculateStartPadding(direction) else 0.dp
    val end = if (edges.end) padding.calculateEndPadding(direction) else 0.dp
    val rowTop = barRowTop()
    val barBottom = rowTop + BAR_HEIGHT
    // The content's own 28 under the 8 the iPhone keeps above its tab bar, and over the toast while one shows, as its
    // `safeAreaInset` raises the list.
    var controls by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val raised = if (controls > 0) with(density) { controls.toDp() } + CONTROLS_SPACING else 0.dp
    val bottom = padding.calculateBottomPadding() + BOTTOM_SPACING + BOTTOM_PADDING + raised

    val entries = listEntries(state, sideBySide, guest, loading, errorMessage, firstLoad)
    state.itemKeys = entries.map { it.key }
    val keptCard = state.navigator.keptCard
    LaunchedEffect(keptCard) {
        val parcelId = keptCard ?: return@LaunchedEffect
        // Once the list is laid out in its new width.
        withFrameNanos {}
        state.keepCard(parcelId)
        state.navigator.keptCard = null
    }

    val backdrop = rememberMaterialBackdrop()
    Box(Modifier.fillMaxSize().background(colors.background)) {
        // While a card's menu, the question it asks or an alert shows over the list, TalkBack reaches only that, as
        // iOS's are modal; the tab shell hides the tab bar the same way.
        Box(Modifier.fillMaxSize().then(if (state.modal) Modifier.clearAndSetSemantics {} else Modifier)) {
            // What the bar's glass shows through: the list, the band behind the bar and the demo's bar. The demo's bar
            // is measured first and the list starts under it, from the first frame at any size of text.
            SubcomposeLayout(Modifier.fillMaxSize().materialBackdrop(backdrop)) { constraints ->
                val loose = constraints.copy(minWidth = 0, minHeight = 0)
                val bar = subcompose(ChromeSlot.DemoBar) {
                    if (demo) DemoModeBar(onExit = source::showWelcome, sides = PaddingValues(start = start, end = end))
                }.map { it.measure(loose) }
                val chromeBottom = barBottom + (bar.maxOfOrNull { it.height } ?: 0).toDp()
                val list = subcompose(ChromeSlot.List) {
                    ListColumn(state, entries, PaddingValues(top = chromeBottom + CONTENT_TOP, bottom = bottom), start, end)
                }.map { it.measure(constraints) }
                // Behind the bar: the demo's cream from the window's top, or the page's ground fading out over the
                // cards, as iOS 26's scroll edge keeps the bar's items clear of what scrolls under them.
                val band = subcompose(ChromeSlot.Band) {
                    if (demo) {
                        Box(Modifier.fillMaxWidth().height(barBottom).background(colors.cream))
                    } else {
                        SoftTopEdge(barBottom)
                    }
                }.map { it.measure(loose) }
                layout(constraints.maxWidth, constraints.maxHeight) {
                    list.forEach { it.place(0, 0) }
                    band.forEach { it.place(0, 0) }
                    bar.forEach { it.place(0, barBottom.roundToPx()) }
                }
            }
            ListToolbar(state, sideBySide, rowTop, start, end, backdrop)
            if (firstLoad) FirstLoad(Modifier.align(Alignment.Center))
            BottomControls(
                state, backdrop,
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = start + 16.dp, end = end + 16.dp, bottom = padding.calculateBottomPadding() + CONTROLS_BOTTOM)
                    .onSizeChanged { controls = it.height },
            )
        }
        Announcements(state)
        CardMenuHost(state)
        FiltersHost(state)
    }
}

/**
 * `bottomControls`: the toast of the parcel archived last, whose Undo brings it back, or else the latest message, for
 * four seconds from when it came, which the tab counts ([DeliveriesTab]). The invitation to turn notifications on comes
 * with the notifications.
 */
@Composable
private fun BottomControls(state: DeliveriesState, backdrop: MaterialBackdrop, modifier: Modifier) {
    val undo by state.source.undoParcel.collectAsState()
    val toast = state.toast
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val parcel = undo
    when {
        parcel != null -> InlineToast(
            text = state.archivedText(parcel), button = localizer.text("app.undo"), symbol = "archivebox.fill",
            tint = colors.resolve(ExperimentalPalette.ochre), action = { state.restore(parcel) }, backdrop = backdrop, modifier = modifier,
        )
        toast != null -> InlineToast(
            text = toast.text, button = null,
            symbol = if (toast.warning) "exclamationmark.triangle.fill" else "checkmark.circle.fill",
            tint = if (toast.warning) colors.warning else colors.resolve(ExperimentalPalette.delivered),
            action = null, backdrop = backdrop, modifier = modifier,
        )
        // Nothing shows: the list comes back down.
        else -> Box(modifier)
    }
}

/**
 * News for TalkBack that nothing on screen says as it comes (`AccessibilityNotification.Announcement`): a node that
 * says it, polite, and is emptied once said, so the same words later are news again.
 */
@Composable
private fun Announcements(state: DeliveriesState) {
    val announcement = state.announcement
    Box(
        Modifier
            .size(1.dp)
            .testTag(ANNOUNCEMENT)
            .semantics {
                liveRegion = LiveRegionMode.Polite
                contentDescription = announcement?.text.orEmpty()
            },
    )
    LaunchedEffect(announcement) {
        if (announcement == null || announcement.text.isEmpty()) return@LaunchedEffect
        delay(ANNOUNCEMENT_HOLD)
        state.announced(announcement)
    }
}

private enum class ChromeSlot { DemoBar, List, Band }

/**
 * `softTopEdge()`, iOS 26's soft scroll edge: the ground, from the top down to `bottom` and fading out past it, over
 * what scrolls under a bar, which keeps the bar's items clear of it.
 */
@Composable
internal fun SoftTopEdge(bottom: Dp, modifier: Modifier = Modifier) {
    val ground = PeekTheme.colors.background
    Box(
        modifier
            .fillMaxWidth()
            .height(bottom + SCROLL_EDGE)
            .background(Brush.verticalGradient(0f to ground, 0.7f to ground.copy(alpha = 0.85f), 1f to ground.copy(alpha = 0f))),
    )
}

/**
 * The list's scrolling column, `padding` clear of the bars at its top and bottom and `start` and `end` of the sides,
 * which pulls down to refresh ([ListPull], the tab's): the pull's seal behind its content, at the top of what the list
 * scrolls, and the cards in flight over it. Its items move as its parcels change, with the release's spring as the
 * held parcels are let go ([ListMotion]). The flights end as the list goes or takes another size, as the iPhone's end
 * as it disappears.
 */
@Composable
private fun ListColumn(state: DeliveriesState, entries: List<ListEntry>, padding: PaddingValues, start: Dp, end: Dp) {
    val reduceMotion = PeekTheme.reduceMotion
    val fade = remember(state, reduceMotion) { if (reduceMotion) null else ListMotion<Float> { state.releasing } }
    val move = remember(state, reduceMotion) { if (reduceMotion) null else ListMotion<IntOffset> { state.releasing } }
    val pull = state.listPull
    val density = LocalDensity.current
    state.places.density = density.density
    // While refreshing, the gap stands over the content, as the system adds it to the list's inset.
    val held = with(density) { pull.held.value.toDp() }
    val listState = state.listState
    DisposableEffect(state) { onDispose(state::dropFlights) }
    Box(
        Modifier
            .fillMaxSize()
            .recedingUnderZoom(state.zoom)
            .nestedScroll(pull)
            .onSizeChanged {
                pull.height = it.height
                state.dropFlights()
            }
            .onPlaced { state.places.list = it },
    ) {
        PullIndicator(state.pull, origin = {
            // The top of the content: the list's first item, less the content's own room above it.
            val info = listState.layoutInfo
            info.visibleItemsInfo.firstOrNull { it.index == 0 }?.let {
                it.offset - info.viewportStartOffset - with(density) { CONTENT_TOP.toPx() } + pull.past
            }
        })
        LazyColumn(
            Modifier.fillMaxSize().graphicsLayer { translationY = pull.past }.testTag(PARCEL_LIST),
            state = listState,
            contentPadding = PaddingValues(top = padding.calculateTopPadding() + held, bottom = padding.calculateBottomPadding()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(entries, key = { it.key }, contentType = { it.type }) { entry ->
                Box(
                    Modifier
                        .animateItem(fadeInSpec = fade, placementSpec = move, fadeOutSpec = if (entry.leavesAtOnce) null else fade)
                        .padding(start = start, end = end)
                        .widthIn(max = COLUMN_WIDTH)
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = entry.top, end = 16.dp)
                        .deliveryListItem(),
                ) {
                    ListItemContent(state, entry.item)
                }
            }
        }
        DeliveredFlights(state)
    }
}

/**
 * An item of the list: what it shows, under `top` of room from the item before it. A card about to become another
 * ([DeliveriesState.leaving]) goes at once rather than fading.
 */
@Immutable
private data class ListEntry(val key: Any, val type: String, val top: Dp, val leavesAtOnce: Boolean, val item: ListItem)

/** What an item of the list shows: equal from one change of the parcels to the next while it shows the same. */
@Immutable
private sealed interface ListItem {
    @Immutable
    data class Overview(val active: Int, val sideBySide: Boolean) : ListItem

    @Immutable
    data object Empty : ListItem

    @Immutable
    data object AllArrived : ListItem

    /** The chips of a custom view. */
    @Immutable
    data object Chips : ListItem

    /** Nothing in the parcels matches the custom view. */
    @Immutable
    data object NoResults : ListItem

    /** Next up's card. */
    @Immutable
    data class Next(val parcel: Parcel) : ListItem

    /** A card on its way after Next up: one of those that need attention, which says what, or one of the others. */
    @Immutable
    data class OnTheWay(val parcel: Parcel, val attention: Boolean) : ListItem

    /** The archived parcels: on the shelf, or in a custom view as a group under its header. */
    @Immutable
    data class Archive(val parcels: List<Parcel>, val grouped: Boolean) : ListItem

    @Immutable
    data class Header(val kind: ParcelSectionKind, val count: Int) : ListItem

    /** A card of a section: a past delivery's, or one on its way among the returned or flagged (Next up's own as Next up's). */
    @Immutable
    data class SectionCard(val kind: ParcelSectionKind, val parcel: Parcel, val next: Boolean) : ListItem

    @Immutable
    data class Foot(val homePageUrl: String, val privacyUrl: String) : ListItem
}

/**
 * The list's items in its order. The first group (the overview, the states and the cards on their way) keeps 10
 * between its items; the sections, the shelf and the foot keep 24 from what is above them, and a section's cards 10
 * (past deliveries) or 12 from each other.
 */
private fun listEntries(
    state: DeliveriesState,
    sideBySide: Boolean,
    guest: Boolean,
    loading: Boolean,
    errorMessage: String?,
    firstLoad: Boolean,
): List<ListEntry> {
    val layout = state.layout
    val parcels = state.parcels
    val leaving = state.leaving
    val entries = mutableListOf<ListEntry>()
    fun first(key: Any, type: String, item: ListItem) {
        entries += ListEntry(key, type, if (entries.isEmpty()) 0.dp else GROUP_SPACING, cardId(key) in leaving, item)
    }
    fun section(key: Any, type: String, top: Dp, item: ListItem) {
        entries += ListEntry(key, type, if (entries.isEmpty()) 0.dp else top, false, item)
    }

    val custom = state.hasCustomView
    if (parcels.isNotEmpty()) first("overview", "overview", ListItem.Overview(layout.active.size, sideBySide))
    if (custom) first("chips", "chips", ListItem.Chips)
    // The error's notice and an invitation to turn notifications on come with the store's errors and the
    // notifications.
    if (!loading) {
        if (parcels.isEmpty()) {
            first("empty", "empty", ListItem.Empty)
        } else if (layout.visible.isEmpty()) {
            first("noResults", "noResults", ListItem.NoResults)
        } else if (!guest && !custom && errorMessage == null && parcels.all { it.isDelivered }) {
            first("allArrived", "allArrived", ListItem.AllArrived)
        }
    }
    layout.next?.let { first(cardKey("next", it.id), "next", ListItem.Next(it)) }
    for (parcel in layout.attention) first(cardKey("attention", parcel.id), "card", ListItem.OnTheWay(parcel, attention = true))
    for (parcel in layout.remaining) first(cardKey("remaining", parcel.id), "card", ListItem.OnTheWay(parcel, attention = false))
    // Someone following parcels without an account is offered one under them, outside a custom view, which comes with
    // the sign-in.
    for (section in layout.sections) {
        if (section.kind == ParcelSectionKind.Archived) {
            // A custom view shows the archived parcels it finds under their header, rather than on the shelf.
            if (custom) {
                section("section-${section.kind.name}", "header", SECTION_SPACING, ListItem.Header(section.kind, section.parcels.size))
                section(ARCHIVE_KEY, "archiveGroup", GROUP_SPACING, ListItem.Archive(section.parcels, grouped = true))
            } else {
                section(ARCHIVE_KEY, "archive", SECTION_SPACING, ListItem.Archive(section.parcels, grouped = false))
            }
            continue
        }
        val group = "section-${section.kind.name}"
        section(group, "header", SECTION_SPACING, ListItem.Header(section.kind, section.parcels.size))
        val spacing = if (section.kind == ParcelSectionKind.Delivered) DELIVERED_SPACING else CARD_SPACING
        for (parcel in section.parcels) {
            section(cardKey(group, parcel.id), "card", spacing, ListItem.SectionCard(section.kind, parcel, parcel.id == layout.next?.id))
        }
    }
    // The foot ends the list once there is one: the first load shows its own message.
    if (!firstLoad) section("foot", "foot", SECTION_SPACING, ListItem.Foot(state.source.homePageUrl, state.source.privacyUrl))
    return entries
}

@Composable
private fun ListItemContent(state: DeliveriesState, item: ListItem) {
    when (item) {
        is ListItem.Overview -> Overview(state, item.active, item.sideBySide)
        ListItem.Empty -> DeliveriesEmptyState(state)
        ListItem.AllArrived -> AllArrived(state)
        ListItem.Chips -> FilterChips(state)
        ListItem.NoResults -> NoResults(state)
        is ListItem.Next -> NextCard(state, item)
        is ListItem.OnTheWay -> OnTheWayCard(state, item)
        is ListItem.Archive -> ArchiveItem(state, item)
        is ListItem.Header -> SectionHeader(item.kind, item.count)
        is ListItem.SectionCard -> SectionCard(state, item)
        is ListItem.Foot -> DeliveriesFoot(item.homePageUrl, item.privacyUrl)
    }
}

/**
 * Next up's card. A card on its way tells the list where it stands, its name and, on Next up, where Pip shows, which
 * the paper of its delivery comes out of; it is pictured flying to its new place ([DeliveriesState.letGo]).
 */
@Composable
private fun NextCard(state: DeliveriesState, item: ListItem.Next) {
    val parcel = item.parcel
    // Read here rather than where the card is drawn, inside its swipe: the card is drawn again as a whole.
    val shown = state.shows(parcel).value
    val arrived = state.holds(parcel.id).value
    val selected = state.selects(parcel.id).value
    val place = state.cardPlace(parcel.id)
    val onName = state.names(parcel.id)
    val sight = remember(state, parcel.id) { { pip: () -> Rect? -> state.places.see(pip, parcel.id) } }
    CardComposed(parcel.id)
    SwipeableCard(state, shown, Radii.nextUp, shadow = true, place, pictured = true) { moved, open, menu, archive ->
        CompositionLocalProvider(LocalPipSight provides sight) {
            NextDeliveryPass(
                shown, onOpen = open, modifier = moved, arrived = arrived, onName = onName, selected = selected, onLongClick = menu,
                swipeAction = archive,
            )
        }
    }
}

/** A card on its way after Next up; one that needs attention says what. */
@Composable
private fun OnTheWayCard(state: DeliveriesState, item: ListItem.OnTheWay) {
    val parcel = item.parcel
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val now = LocalNow.current
    val shown = state.shows(parcel).value
    val arrived = state.holds(parcel.id).value
    val selected = state.selects(parcel.id).value
    CardComposed(parcel.id)
    val notice = if (!item.attention || arrived) {
        null
    } else {
        remember(shown, localizer, language) { shown.notice(now())?.let { localizer.text(it.localizationKey) } }
    }
    val place = state.cardPlace(parcel.id)
    val onName = state.names(parcel.id)
    SwipeableCard(state, shown, Radii.card, shadow = false, place, pictured = true) { moved, open, menu, archive ->
        ParcelPassCard(
            shown, notice = notice, onOpen = open, modifier = moved, arrived = arrived, onName = onName, selected = selected,
            onLongClick = menu, swipeAction = archive,
        )
    }
}

/** A card of a section: a past delivery's, or one on its way among the returned or flagged. */
@Composable
private fun SectionCard(state: DeliveriesState, item: ListItem.SectionCard) {
    val parcel = item.parcel
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val now = LocalNow.current
    val selected = state.selects(parcel.id).value
    CardComposed(parcel.id)
    when {
        item.kind == ParcelSectionKind.Delivered -> {
            val place = state.cardPlace(parcel.id)
            val onName = state.names(parcel.id)
            SwipeableCard(state, parcel, Radii.card, shadow = false, place, pictured = true) { moved, open, menu, archive ->
                DeliveredParcelCard(parcel, onOpen = open, modifier = moved, onName = onName, selected = selected, onLongClick = menu, swipeAction = archive)
            }
        }
        item.next -> SwipeableCard(state, parcel, Radii.nextUp, shadow = true, Modifier) { moved, open, menu, archive ->
            NextDeliveryPass(parcel, onOpen = open, modifier = moved, selected = selected, onLongClick = menu, swipeAction = archive)
        }
        else -> {
            val notice = if (item.kind != ParcelSectionKind.Attention) null else remember(parcel, localizer, language) {
                parcel.notice(now())?.let { localizer.text(it.localizationKey) }
            }
            // An archived parcel found here isn't archived again by a swipe; its menu restores it.
            SwipeableCard(state, parcel, Radii.card, shadow = false, Modifier, swipes = !parcel.isArchived) { moved, open, menu, archive ->
                ParcelPassCard(parcel, notice = notice, onOpen = open, modifier = moved, selected = selected, onLongClick = menu, swipeAction = archive)
            }
        }
    }
}

/**
 * A card that swipes to the archive (`experimentalSwipeToArchive`) and whose menu opens on a long press (`.cardMenu`),
 * in its corners. `card` draws it with the modifier that moves it, what a tap does (closes the swipe while it is open,
 * opens the parcel otherwise), the long press and the swipe's action for TalkBack. Without `swipes` the card only has
 * its menu. A `pictured` card's picture, its menu's, is what flies when it takes another place ([DeliveryCardPlaces]).
 * The parcel's page zooms out of the card ([zoomSource]).
 */
@Composable
private fun SwipeableCard(
    state: DeliveriesState,
    parcel: Parcel,
    cornerRadius: Dp,
    shadow: Boolean,
    modifier: Modifier,
    swipes: Boolean = true,
    pictured: Boolean = false,
    card: @Composable (moved: Modifier, onOpen: () -> Unit, onLongClick: () -> Unit, swipeAction: CustomAccessibilityAction?) -> Unit,
) {
    val localizer = LocalLocalizer.current
    val menu = rememberCardMenu(state, parcel, cornerRadius)
    if (pictured) {
        DisposableEffect(state, parcel.id, menu) {
            state.places.picture(parcel.id, menu.picture)
            onDispose { state.places.dropPicture(parcel.id, menu.picture) }
        }
    }
    val open = remember(state, parcel.id) { { state.navigator.open(parcel.id) } }
    if (!swipes) {
        card(menu.modifier.then(modifier).then(menu.target).zoomSource(parcel.id, state.zoom, cornerRadius, menu.focus), open, menu::open, null)
        return
    }
    val title = localizer.text("parcel.archive")
    val swipe = rememberArchiveSwipe(state, parcel.id) { leaving -> state.archive(menu.parcel, after = leaving) }
    val action = remember(swipe, title) { archiveAction(swipe, title) }
    val tap = remember(swipe, open) { { swipe.tap(open) } }
    val excluded by remember(state, swipe) { derivedStateOf { state.openSwipes.lastOrNull() === swipe } }
    SwipeToArchive(swipe, title, cornerRadius, shadow, menu.modifier.then(modifier), excluded) { moved ->
        card(moved.then(menu.target).zoomSource(parcel.id, state.zoom, cornerRadius, menu.focus), tap, menu::open, action)
    }
}

/** The archive's shelf, or its group in a custom view, its row of the parcel beside the list marked. */
@Composable
private fun ArchiveItem(state: DeliveriesState, item: ListItem.Archive) {
    val parcels = item.parcels
    val selected by remember(state, parcels) {
        derivedStateOf(structuralEqualityPolicy()) { state.selectedCard?.takeIf { id -> parcels.any { it.id == id } } }
    }
    // Each row lifts as its menu opens, on the archive's paper, cut to the surface's corners.
    val menus = remember(state) { mutableMapOf<UUID, CardMenuSource>() }
    val paper = PeekTheme.colors.paper
    val rowModifier: @Composable (Parcel) -> Modifier = { parcel ->
        val menu = rememberCardMenu(state, parcel, ARCHIVE_RADIUS, fill = paper)
        DisposableEffect(menus, menu) {
            menus[parcel.id] = menu
            onDispose { if (menus[parcel.id] === menu) menus.remove(parcel.id) }
        }
        menu.modifier.then(menu.target).zoomSource(parcel.id, state.zoom, ARCHIVE_RADIUS, menu.focus)
    }
    if (item.grouped) {
        ArchivedParcelGroup(
            parcels, onOpen = state.navigator::open, selected = selected, onLongClick = { menus[it.id]?.open() }, rowModifier = rowModifier,
        )
    } else {
        ArchiveShelf(
            parcels, expanded = state.archiveOpen, onExpandedChange = state::openArchive, onOpen = state.navigator::open, selected = selected,
            onLongClick = { menus[it.id]?.open() }, rowModifier = rowModifier,
        )
    }
}

/** Whether the parcel's card is marked as the one shown beside the list: a card is drawn again only as that changes. */
@Composable
private fun DeliveriesState.selects(parcelId: UUID): State<Boolean> =
    remember(this, parcelId) { derivedStateOf(structuralEqualityPolicy()) { selectedCard == parcelId } }

/**
 * `place(of:leaving:)`: the card tells the list where it stands, and stays unseen while its picture flies to its place.
 * A card is drawn again only as its own flight starts and ends.
 */
@Composable
private fun DeliveriesState.cardPlace(parcelId: UUID): Modifier {
    val flying by remember(this, parcelId) { derivedStateOf(structuralEqualityPolicy()) { flies(parcelId) } }
    val stand = remember(this) { this::stand }
    return Modifier.deliveryCardPlace(parcelId, flying, stand).cardInList(parcelId, places)
}

/** `onName: { places.name(parcel.id, at: $0) }`: where the card writes its parcel's name, which its flight keeps level. */
@Composable
private fun DeliveriesState.names(parcelId: UUID): (Float) -> Unit =
    remember(this, parcelId) { { middle: Float -> places.name(parcelId, middle) } }

/** Whether the parcel has just arrived and keeps its place a moment. */
@Composable
private fun DeliveriesState.holds(parcelId: UUID): State<Boolean> =
    remember(this, parcelId) { derivedStateOf(structuralEqualityPolicy()) { hold.holds(parcelId) } }

/** The parcel as it is now, for its card arranged as it was ([DeliveriesState.shown]). */
@Composable
private fun DeliveriesState.shows(parcel: Parcel): State<Parcel> =
    remember(this, parcel) { derivedStateOf(referentialEqualityPolicy()) { shown(parcel) } }

/** Told the parcel each time its card in the list is drawn again: the tests count them. */
internal val LocalCardComposed = staticCompositionLocalOf<((UUID) -> Unit)?> { null }

@Composable
@NonRestartableComposable
private fun CardComposed(parcelId: UUID) {
    val told = LocalCardComposed.current ?: return
    SideEffect { told(parcelId) }
}

/** A section's name and its count, as the overview's. */
@Composable
private fun SectionHeader(kind: ParcelSectionKind, count: Int) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val title = remember(kind, localizer, language) { localizer.text(sectionTitleKey(kind)) }
    androidx.compose.foundation.layout.Row(
        Modifier.fillMaxWidth().padding(top = 3.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, color = PeekTheme.colors.label, style = PeekTheme.type.headline)
        CountPill(count, horizontalPadding = 8.dp)
    }
}

private fun sectionTitleKey(kind: ParcelSectionKind): String = when (kind) {
    ParcelSectionKind.Attention -> "app.needsAttention"
    ParcelSectionKind.Today -> "app.arrivingToday"
    ParcelSectionKind.Active -> "app.onTheWaySection"
    ParcelSectionKind.Delivered -> "app.pastDeliveries"
    ParcelSectionKind.Returned -> "app.returned"
    ParcelSectionKind.Archived -> "app.archived"
}

/**
 * The parcels on their way and their count, the search's button and, beside a parcel or for TalkBack, the refresh
 * button; under them, the search while it shows, with a snappy 0.3 s fade, shrink to 94 % from the top and drop of 10,
 * and none under reduce motion.
 */
@Composable
private fun Overview(state: DeliveriesState, active: Int, sideBySide: Boolean) {
    val reduceMotion = PeekTheme.reduceMotion
    Column {
        OverviewRow(state, active, refreshes = sideBySide || touchExplorationEnabled())
        val fade = Motion.snappy<Float>(SEARCH_MOTION)
        val size = Motion.snappy<IntSize>(SEARCH_MOTION)
        val offset = Motion.snappy<IntOffset>(SEARCH_MOTION)
        val top = TransformOrigin(0.5f, 0f)
        val drop = with(LocalDensity.current) { SEARCH_DROP.roundToPx() }
        AnimatedVisibility(
            visible = state.showingSearch,
            enter = if (reduceMotion) EnterTransition.None else fadeIn(fade) + scaleIn(fade, 0.94f, top) +
                slideInVertically(offset) { -drop } + expandVertically(size, Alignment.Top, clip = false),
            exit = if (reduceMotion) ExitTransition.None else fadeOut(fade) + scaleOut(fade, 0.94f, top) +
                slideOutVertically(offset) { -drop } + shrinkVertically(size, Alignment.Top, clip = false),
        ) {
            Column {
                Spacer(Modifier.height(GROUP_SPACING))
                SearchControls(state)
            }
        }
    }
}

/** `ProgressView().controlSize(.large)` over "Opening…", while the parcels first load. */
@Composable
private fun FirstLoad(modifier: Modifier) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val text = remember(localizer, language) { localizer.text("app.opening") }
    Column(modifier, verticalArrangement = Arrangement.spacedBy(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        ActivityIndicator()
        Text(text, color = PeekTheme.colors.secondaryLabel, style = PeekTheme.type.subheadline)
    }
}

/** The bar's row: 44 tall, in a bar 54 tall. */
internal val BAR_HEIGHT = 54.dp
internal val DEMO_BAR_HEIGHT = 48.dp
private val CONTENT_TOP = 8.dp
private val BOTTOM_SPACING = 8.dp
private val BOTTOM_PADDING = 28.dp
private val SCROLL_EDGE = 12.dp
private val COLUMN_WIDTH = 660.dp
private val GROUP_SPACING = 10.dp
private val SECTION_SPACING = 24.dp
private val DELIVERED_SPACING = 10.dp
private val CARD_SPACING = 12.dp
/** Over the toast, the room it keeps under itself and the list's spacing above it (`safeAreaInset(spacing: 8)`). */
private val CONTROLS_BOTTOM = 5.dp
private val CONTROLS_SPACING = CONTROLS_BOTTOM + 8.dp
private val ARCHIVE_RADIUS = 18.dp
/** How long TalkBack's news stays said before it is emptied. */
private const val ANNOUNCEMENT_HOLD = 1_000L
private const val SEARCH_MOTION = 0.3
/** How far above its place the search row comes from and goes to (`.offset(y: -10)`). */
private val SEARCH_DROP = 10.dp

const val ANNOUNCEMENT = "deliveries.announcement"
