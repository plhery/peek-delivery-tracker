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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.ParcelSection
import com.plhery.peekdeliverytracker.core.ParcelSectionKind
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.core.notice
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.tabs.tabContentPadding
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import java.util.UUID

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
 * Out of the iPhone's list: pulling to refresh, swiping a card to the archive, a card's menu, the toasts and their
 * undo, the search field and the filters, the delivered card's celebration and flight, what a card's menu asks, the
 * sheets (Add, the account), the zoom from a card to its page and dropping text on the list. The buttons that would
 * open them call [DeliveriesState]'s hooks, which do nothing yet.
 */
@Composable
internal fun DeliveryList(state: DeliveriesState) {
    val source = state.source
    val session by source.session.collectAsState()
    val loading by source.loading.collectAsState()
    val errorMessage by source.errorMessage.collectAsState()
    val colors = PeekTheme.colors
    val density = LocalDensity.current
    val edges = LocalColumnEdges.current
    val sideBySide = state.arrangement == DeliveriesArrangement.SideBySide
    val demo = session == AuthenticationState.Demo
    val guest = session == AuthenticationState.Welcome
    val parcels = state.parcels
    val firstLoad = loading && parcels.isEmpty()

    // The window's bars and cutout, on the sides this column reaches, and the tab bar's room at its top or bottom.
    val padding = tabContentPadding()
    val direction = LocalLayoutDirection.current
    val start = if (edges.start) padding.calculateStartPadding(direction) else 0.dp
    val end = if (edges.end) padding.calculateEndPadding(direction) else 0.dp
    val rowTop = barRowTop()
    val barBottom = rowTop + BAR_HEIGHT
    var demoHeight by remember { mutableStateOf(DEMO_BAR_HEIGHT) }
    val chromeBottom = barBottom + if (demo) demoHeight else 0.dp
    // The content's own 28 under the 8 the iPhone keeps above its tab bar.
    val bottom = padding.calculateBottomPadding() + BOTTOM_SPACING + BOTTOM_PADDING

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

    val reduceMotion = PeekTheme.reduceMotion
    val fade = if (reduceMotion) null else Motion.snappy<Float>(LIST_MOTION)
    val move = if (reduceMotion) null else Motion.snappy<IntOffset>(LIST_MOTION)
    val backdrop = rememberMaterialBackdrop()

    Box(Modifier.fillMaxSize().background(colors.background)) {
        // What the bar's glass shows through: the list, the band behind the bar and the demo's bar.
        Box(Modifier.fillMaxSize().materialBackdrop(backdrop)) {
            LazyColumn(
                Modifier.fillMaxSize().testTag(PARCEL_LIST),
                state = state.listState,
                contentPadding = PaddingValues(top = chromeBottom + CONTENT_TOP, bottom = bottom),
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
                        entry.content()
                    }
                }
            }
            // Behind the bar: the demo's cream from the window's top, or the page's ground fading out over the
            // cards, as iOS 26's scroll edge keeps the bar's items clear of what scrolls under them.
            if (demo) {
                Box(Modifier.fillMaxWidth().height(barBottom).background(colors.cream))
                DemoModeBar(
                    onExit = source::showWelcome,
                    modifier = Modifier.padding(top = barBottom).onSizeChanged { demoHeight = with(density) { it.height.toDp() } },
                    sides = PaddingValues(start = start, end = end),
                )
            } else {
                val ground = colors.background
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(barBottom + SCROLL_EDGE)
                        .background(Brush.verticalGradient(0f to ground, 0.7f to ground.copy(alpha = 0.85f), 1f to ground.copy(alpha = 0f))),
                )
            }
        }
        ListToolbar(state, sideBySide, rowTop, start, end, backdrop)
        if (firstLoad) FirstLoad(Modifier.align(Alignment.Center))
    }
}

/**
 * An item of the list: what it shows, under `top` of room from the item before it. A card about to become another
 * ([DeliveriesState.leaving]) goes at once rather than fading.
 */
private class ListEntry(val key: Any, val type: String, val top: Dp, val leavesAtOnce: Boolean, val content: @Composable () -> Unit)

/**
 * The list's items in its order. The first group (the overview, the states and the cards on their way) keeps 10
 * between its items; the sections, the shelf and the foot keep 24 from what is above them, and a section's cards 10
 * (past deliveries) or 12 from each other.
 */
@Composable
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
    val navigator = state.navigator
    val selected = state.selectedCard
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val now = LocalNow.current
    val leaving = state.leaving
    val entries = mutableListOf<ListEntry>()
    fun first(key: Any, type: String, content: @Composable () -> Unit) {
        entries += ListEntry(key, type, if (entries.isEmpty()) 0.dp else GROUP_SPACING, cardId(key) in leaving, content)
    }
    fun section(key: Any, type: String, top: Dp, content: @Composable () -> Unit) {
        entries += ListEntry(key, type, if (entries.isEmpty()) 0.dp else top, false, content)
    }
    fun open(parcelId: UUID) = navigator.open(parcelId)

    if (parcels.isNotEmpty()) {
        first("overview", "overview") { Overview(state, layout.active.size, sideBySide) }
    }
    // The error's notice and an invitation to turn notifications on come with the store's errors and the
    // notifications.
    if (!loading) {
        if (parcels.isEmpty()) {
            first("empty", "empty") { DeliveriesEmptyState(state) }
        } else if (!guest && errorMessage == null && layout.visible.isNotEmpty() && parcels.all { it.isDelivered }) {
            first("allArrived", "allArrived") { AllArrived(state) }
        }
    }
    // A card on its way tells the list where it stands, and is pictured flying to its new place: the flight isn't
    // ported yet.
    fun place(parcelId: UUID) = Modifier.deliveryCardPlace(parcelId, flying = false, stand = state::stand)
    layout.next?.let { next ->
        first(cardKey("next", next.id), "next") {
            NextDeliveryPass(
                state.shown(next), onOpen = { open(next.id) }, modifier = place(next.id), arrived = state.hold.holds(next.id),
                selected = next.id == selected,
            )
        }
    }
    for (parcel in layout.attention) {
        first(cardKey("attention", parcel.id), "card") {
            val arrived = state.hold.holds(parcel.id)
            val shown = state.shown(parcel)
            val notice = if (arrived) null else remember(shown, localizer, language) { shown.notice(now())?.let { localizer.text(it.localizationKey) } }
            ParcelPassCard(
                shown, notice = notice, onOpen = { open(parcel.id) }, modifier = place(parcel.id), arrived = arrived,
                selected = parcel.id == selected,
            )
        }
    }
    for (parcel in layout.remaining) {
        first(cardKey("remaining", parcel.id), "card") {
            ParcelPassCard(
                state.shown(parcel), notice = null, onOpen = { open(parcel.id) }, modifier = place(parcel.id),
                arrived = state.hold.holds(parcel.id), selected = parcel.id == selected,
            )
        }
    }
    // Someone following parcels without an account is offered one under them, which comes with the sign-in.
    for (section in layout.sections) {
        if (section.kind == ParcelSectionKind.Archived) {
            section(ARCHIVE_KEY, "archive", SECTION_SPACING) {
                ArchiveShelf(
                    section.parcels, expanded = state.archiveOpen, onExpandedChange = state::openArchive, onOpen = ::open,
                    selected = selected,
                )
            }
            continue
        }
        val group = "section-${section.kind.name}"
        section(group, "header", SECTION_SPACING) { SectionHeader(section) }
        val spacing = if (section.kind == ParcelSectionKind.Delivered) DELIVERED_SPACING else CARD_SPACING
        for (parcel in section.parcels) {
            section(cardKey(group, parcel.id), "card", spacing) { SectionCard(state, section, parcel, layout.next?.id) }
        }
    }
    // The foot ends the list once there is one: the first load shows its own message.
    if (!firstLoad) section("foot", "foot", SECTION_SPACING) { DeliveriesFoot(state.source.homePageUrl, state.source.privacyUrl) }
    return entries
}

/** A card of a section: a past delivery's, or one on its way among the returned or flagged. */
@Composable
private fun SectionCard(state: DeliveriesState, section: ParcelSection, parcel: Parcel, next: UUID?) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val now = LocalNow.current
    val selected = parcel.id == state.selectedCard
    val open = { state.navigator.open(parcel.id) }
    when {
        section.kind == ParcelSectionKind.Delivered ->
            DeliveredParcelCard(parcel, onOpen = open, modifier = Modifier.deliveryCardPlace(parcel.id, false, state::stand), selected = selected)
        parcel.id == next -> NextDeliveryPass(parcel, onOpen = open, selected = selected)
        else -> {
            val notice = if (section.kind != ParcelSectionKind.Attention) null else remember(parcel, localizer, language) {
                parcel.notice(now())?.let { localizer.text(it.localizationKey) }
            }
            ParcelPassCard(parcel, notice = notice, onOpen = open, selected = selected)
        }
    }
}

/** A section's name and its count, as the overview's. */
@Composable
private fun SectionHeader(section: ParcelSection) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val title = remember(section.kind, localizer, language) { localizer.text(sectionTitleKey(section.kind)) }
    androidx.compose.foundation.layout.Row(
        Modifier.fillMaxWidth().padding(top = 3.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, color = PeekTheme.colors.label, style = PeekTheme.type.headline)
        CountPill(section.parcels.size, horizontalPadding = 8.dp)
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
 * button; under them, the search while it shows, with a snappy 0.3 s fade, shrink and drop from the top.
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
        AnimatedVisibility(
            visible = state.showingSearch,
            enter = if (reduceMotion) EnterTransition.None else fadeIn(fade) + scaleIn(fade, 0.94f, top) +
                slideInVertically(offset) { -it / 6 } + expandVertically(size, Alignment.Top, clip = false),
            exit = if (reduceMotion) ExitTransition.None else fadeOut(fade) + scaleOut(fade, 0.94f, top) +
                slideOutVertically(offset) { -it / 6 } + shrinkVertically(size, Alignment.Top, clip = false),
        ) {
            Column {
                Spacer(Modifier.height(GROUP_SPACING))
                SearchControls()
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
private val BAR_HEIGHT = 54.dp
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
private const val LIST_MOTION = 0.32
private const val SEARCH_MOTION = 0.3
