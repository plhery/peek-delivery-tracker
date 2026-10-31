package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.KeyEvent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.ParcelAttention
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel
import com.plhery.peekdeliverytracker.core.ParcelFeedbackSubject
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.ParcelPageColumns
import com.plhery.peekdeliverytracker.core.activeTrackingCarrier
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.attention
import com.plhery.peekdeliverytracker.core.displayedCarrier
import com.plhery.peekdeliverytracker.core.hasCarrierUpdate
import com.plhery.peekdeliverytracker.core.isArchived
import com.plhery.peekdeliverytracker.core.quotedWords
import com.plhery.peekdeliverytracker.core.shipmentFacts
import com.plhery.peekdeliverytracker.core.waitingAt
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.symbols.SymbolScale
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.tabContentPadding
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.shownCarrierIdentity
import com.plhery.peekdeliverytracker.ui.zoom.pageBarItem
import java.util.UUID
import kotlin.math.max
import kotlin.math.roundToInt
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeoutOrNull

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
 * out under the bar over what scrolls under it (`softTopEdge()`). Under the paper stands the question asked about the
 * page, and over the page's foot what it says on the way back from the carrier's site, with its sheets
 * ([ParcelFeedbackOverlays]); a "Yes" there may be the moment to ask for a rating ([PageReviewing]). No bar names the
 * page; TalkBack hears it as "Parcel details" (`windowTitle`).
 *
 * A parcel the deliveries no longer have (deleted elsewhere, or signed out) shows `ContentUnavailableView` instead:
 * "Parcel", a box, and that it is no longer in the list (B-ST01).
 *
 * Esc goes back to the list from a page over it, and the ••• menu's keys act on the parcel while nothing covers it.
 */
@Composable
internal fun ParcelPage(state: DeliveriesState, parcelId: UUID) {
    val menuParcel = state.liveMenuParcel(parcelId)
    val parcel = state.sample(parcelId) ?: menuParcel
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
    val feedback = rememberFeedback(state, parcelId, parcel)
    // The page's scroll, which the word at its foot shows through its glass.
    val backdrop = rememberMaterialBackdrop()
    PageKeys(state, parcelId, menuParcel)
    Box(
        Modifier
            .fillMaxSize()
            .background(colors.background)
            .testTag(PARCEL_PAGE)
            .semantics { paneTitle = title },
    ) {
        if (parcel != null) {
            Box(Modifier.fillMaxSize().materialBackdrop(backdrop)) {
                PageScroll(state, parcel, parcelId, feedback, PageMargins(start, top, end, bottom))
            }
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
        if (feedback != null) {
            ParcelFeedbackOverlays(
                state, feedback, backdrop, Modifier.align(Alignment.BottomCenter).padding(start = start, end = end, bottom = bottom),
            )
        }
    }
    if (feedback != null) PageReviewing(state, feedback, parcel, parcelId)
}

/**
 * The page's question (`@StateObject feedback`), the source's for the page's id so that it lasts through the window made
 * again, told what the page shows each time that changes (`onChange(of: parcel.flatMap(feedbackPage), initial: true)`).
 * Made as the page is first drawn once it has opened; on its way out the page keeps the one it had, and makes none.
 */
@Composable
private fun rememberFeedback(state: DeliveriesState, pageId: UUID, parcel: Parcel?): ParcelFeedbackWork? {
    val source = state.source
    val page = feedbackPage(state, pageId, parcel)
    val client = parcel?.let(source::feedbackClient)
    val held = remember { arrayOfNulls<ParcelFeedbackWork>(1) }
    val work = source.feedbacks.work(pageId, source.scope) { source.feedbackModel().apply { show(page, client) } } ?: held[0]
    held[0] = work
    LaunchedEffect(work, page) { work?.show(page, client) }
    return work
}

/**
 * `feedbackPage(_:)`: what the question at the end of the page is asked about, or null while it asks nothing. The demo's
 * stories and the sample are made up: nothing is asked of them.
 */
@Composable
private fun feedbackPage(state: DeliveriesState, pageId: UUID, parcel: Parcel?): ParcelFeedbackModel.Page? {
    val language = LocalLanguage.current
    parcel ?: return null
    return ParcelFeedbackModel.Page(
        parcel, ParcelFeedbackSubject.Parcel(parcel.id), showsHistory(parcel), madeUp = state.source.isDemo || state.sample(pageId) != null,
        carrier = CarrierCatalog.shared.info(parcel.displayedCarrier, language).displayName, language = language,
    )
}

/** The window's safe sides the page keeps clear of, and its top under the bar. */
private class PageMargins(val start: Dp, val top: Dp, val end: Dp, val bottom: Dp)

/**
 * The page's place, kept with the window. A window made again (a rotation, a change of theme) lays the page out once
 * before the window's insets reach it, as Compose reads them a frame late: the page stands taller and its foot is
 * shorter, which takes a place near the foot back by as much. The place comes back once the page has its room, as the
 * iPhone's page keeps it, unless the reader has scrolled meanwhile.
 */
@Composable
private fun rememberPageScroll(): ScrollState {
    val scroll = rememberScrollState()
    val restored = remember(scroll) { Snapshot.withoutReadObservation { scroll.value } }
    LaunchedEffect(scroll) {
        if (restored == 0) return@LaunchedEffect
        withTimeoutOrNull(ROOM_MILLIS) { snapshotFlow { scroll.maxValue }.first { it in restored..<Int.MAX_VALUE } }
        val untouched = !scroll.lastScrolledForward && !scroll.lastScrolledBackward
        if (untouched && scroll.value < restored) scroll.scrollTo(minOf(restored, scroll.maxValue))
    }
    return scroll
}

/** The page's ScrollView: the paper, in one column or two, and the question under it. */
@Composable
private fun PageScroll(state: DeliveriesState, parcel: Parcel, pageId: UUID, feedback: ParcelFeedbackWork?, margins: PageMargins) {
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val history = showsHistory(parcel)
    // Where the scroll shows what it holds, which the question comes in as it is seen in.
    val viewport = remember { arrayOfNulls<LayoutCoordinates>(1) }
    BoxWithConstraints(Modifier.fillMaxSize().onPlaced { viewport[0] = it }) {
        val columns = ParcelPageColumns.fit(
            maxWidth.value.toDouble(), besideList = state.arrangement == DeliveriesArrangement.SideBySide, largeText = largeText, history = history,
        )
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberPageScroll())
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
            // Given the room, the history stands beside the pass rather than under it.
            PassAndHistory(columns, paper(if (columns) PAGE_COLUMNS else PAGE_SINGLE_COLUMN)) {
                LiveParcelPass(state, parcel, pageId, divided = !columns, Modifier.fillMaxWidth())
                PageHistory(state, parcel, pageId, history, Modifier.fillMaxWidth())
            }
            // Below the paper, on the ground it lies on, 8 in from its sides and no wider than 608.
            if (feedback != null) {
                ParcelFeedbackQuestion(feedback, { viewport[0] }, Modifier.widthIn(max = FEEDBACK_WIDTH).padding(horizontal = FEEDBACK_SIDE))
            }
        }
    }
}

/** The page shows what the carrier answered: a carrier is being asked, or one has answered (`showsHistory`). */
private fun showsHistory(parcel: Parcel): Boolean =
    CarrierCatalog.shared.tracksAutomatically(parcel.activeTrackingCarrier) || parcel.hasCarrierUpdate

/**
 * `liveParcelPass`: the pass and the offer of the delivery email ([EmailOfferSlot]), then what the parcel waits for
 * and what its carrier says, then its numbers, links and details, then what the page says where no carrier is asked or
 * why the last check failed, 12 apart, a line under them where the history stands under the pass (`divided`). TalkBack
 * goes through its parts before it leaves them (`.accessibilityElement(children: .contain)`).
 */
@Composable
private fun LiveParcelPass(state: DeliveriesState, parcel: Parcel, pageId: UUID, divided: Boolean, modifier: Modifier) {
    Column(modifier.semantics { isTraversalGroup = true }, verticalArrangement = Arrangement.spacedBy(PASS_SPACING)) {
        // The offer of the delivery email goes with the card, so that the room under it comes and goes with the offer.
        Column {
            PassHeader(state, parcel, pageId)
            EmailOfferSlot(state, parcel, pageId, Modifier.padding(top = PASS_SPACING))
        }
        PickupPoint(state, parcel)
        CarrierNotes(state, parcel)
        ShipmentIdentity(state, parcel, pageId)
        ShipmentDetails(state, parcel, pageId)
        if (divided) Box(Modifier.fillMaxWidth().height(1.dp).background(PeekTheme.colors.separator))
    }
}

/**
 * The pass at the page's top ([LivePassCard]): the bell where alerts can be had, which needs an account and a parcel
 * of the deliveries, and opens the parcel's alerts while the account's email is on; the share and the carrier where
 * the parcel is the reader's own, rather than followed through someone else's link; and the map once the atlas is read.
 */
@Composable
private fun PassHeader(state: DeliveriesState, parcel: Parcel, pageId: UUID) {
    val session by state.source.session.collectAsState()
    val email by state.source.deliveryEmail.collectAsState()
    val sample = state.sample(pageId) != null
    // A scan has a place, so the card shows the route once the map data has loaded.
    val atlas = rememberWorldAtlas(wanted = parcel.trackingEvents.any { it.place != null })
    val opensAlerts = email?.bellOpensAlerts(parcel) == true
    val bell = if (session != AuthenticationState.Welcome && !sample) {
        // The same bell while nothing it acts on changes, so the card isn't composed again for a new one.
        remember(state, pageId, parcel, opensAlerts) {
            PassBell(opensAlerts, open = { state.openAlerts(pageId, it) }) { muted, saved -> state.muteOnPage(pageId, parcel, muted, saved) }
        }
    } else {
        null
    }
    LivePassCard(
        parcel, atlas, sample = sample, readerLink = state.source.viewerLink(parcel) != null, bell = bell,
        working = state.pageWorking(pageId), onMap = { state.openMap(pageId, it) },
        onEditCarrier = { carrier, focus -> state.editCarrier(pageId, carrier, focus) }, onRename = { state.renameOnPage(pageId, it) },
        onShare = { state.shareOnPage(pageId, it) },
    )
}

/**
 * [PickupPointCard] while the parcel waits at its pickup point ([waitingAt]); afterwards the point is one of its
 * facts. Its directions and its copy are counted, and the copy puts the point's name and address on the clipboard with
 * the success tap ([DeliveriesState.copy]).
 */
@Composable
private fun PickupPoint(state: DeliveriesState, parcel: Parcel) {
    val point = parcel.waitingAt ?: return
    val identity = shownCarrierIdentity(parcel, LocalLocalizer.current, LocalLanguage.current) ?: return
    PickupPointCard(
        point, identity,
        onDirections = { state.source.action("parcel-pickup-directions") },
        onCopy = {
            state.source.action("parcel-pickup-copy", DeliveryAnalytics.Outcome.Success)
            state.copy(point.query)
        },
        Modifier.testTag(PAGE_PICKUP),
    )
}

/**
 * What the carrier says the parcel waits on ([CarrierSaysNote]), where its last scan does ([carrierWaits]) and says
 * something; then `ParcelQuietNote` while the parcel goes quiet, with a carrier to name: without one the note would
 * say Peek keeps asking an unknown carrier.
 */
@Composable
private fun CarrierNotes(state: DeliveriesState, parcel: Parcel) {
    val now = LocalNow.current()
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val words = carrierWaits(parcel)?.quotedWords
    if (words != null) {
        val carrier = LocalCarrierIdentities.current?.displayName(parcel.activeTrackingCarrier, language)
        if (carrier != null) CarrierSaysNote(words, carrier, Modifier.testTag(PAGE_CARRIER_SAYS))
    }
    if (parcel.isArchived || parcel.displayedCarrier == CarrierId.Unknown || parcel.attention(now) != ParcelAttention.Stalled) return
    val identity = shownCarrierIdentity(parcel, localizer, language) ?: return
    ParcelQuietNote(
        parcel, PeekTheme.colors.resolve(identity.ink), onHelp = { state.source.action("parcel-carrier-help") },
        Modifier.testTag(PAGE_QUIET),
    )
}

/**
 * The parcel's numbers and its carriers' pages ([ShipmentNumbers]), in its carrier's ink: none for the sample, whose
 * number is made up, nor for a number a link hides. A copy says so on its row ([DeliveriesState.copyOnPage]), and a
 * page opened is counted, and the page's question hears of the visit (`feedback.visited`), to ask on the way back.
 */
@Composable
private fun ShipmentIdentity(state: DeliveriesState, parcel: Parcel, pageId: UUID) {
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current ?: return
    val identity = shownCarrierIdentity(parcel, LocalLocalizer.current, language) ?: return
    val links = if (state.sample(pageId) == null && parcel.trackingNumber.isNotEmpty()) identities.catalog.trackingLinks(parcel, language) else emptyList()
    ShipmentNumbers(
        parcel, links, PeekTheme.colors.resolve(identity.ink),
        copied = state.copiedNumber?.takeIf { it.pageId == pageId }?.number,
        onCopy = { state.copyOnPage(pageId, it) },
        onLink = { link ->
            state.source.action("parcel-carrier-link")
            state.source.feedbacks.of(pageId)?.visited(link.name)
        },
        Modifier.testTag(PAGE_IDENTITY),
    )
}

/**
 * After the numbers, the shipment's facts where the parcel has carrier data ([ShipmentFacts]), then what the page says
 * where no carrier is asked, or why the last check failed ([TrackingNotes]): its carrier changes open the editor, and
 * its switch moves the parcel to the carrier its number names, where the parcel is the reader's to change rather than
 * the sample or followed through someone else's link.
 */
@Composable
private fun ShipmentDetails(state: DeliveriesState, parcel: Parcel, pageId: UUID) {
    val localizer = LocalLocalizer.current
    localizer.shipmentFacts(parcel)?.let { ShipmentFacts(it, Modifier.testTag(PAGE_FACTS)) }
    val identity = shownCarrierIdentity(parcel, localizer, LocalLanguage.current) ?: return
    TrackingNotes(
        parcel, editable = state.sample(pageId) == null && state.source.viewerLink(parcel) == null, working = state.pageWorking(pageId),
        ink = PeekTheme.colors.resolve(identity.ink), onEditCarrier = { state.editCarrier(pageId, focus = it) },
        onSwitch = { carrier, focus -> state.switchCarrierOnPage(pageId, parcel, carrier, focus) },
    )
}

/**
 * `AnyLayout(columns ? HStackLayout(alignment: .top, spacing: gap) : VStackLayout(alignment: .leading, spacing: 20))`:
 * the pass and the history side by side in equal widths, or the history 20 under the pass. One layout either way, as
 * the iPhone's is, so the pass and the journal keep what they hold (a journal shut, an address just copied) as the
 * page goes from one column to two: a tablet turning beside the list, a window resized, the text size changed.
 */
@Composable
private fun PassAndHistory(columns: Boolean, modifier: Modifier, content: @Composable () -> Unit) {
    Layout(content, modifier) { measurables, constraints ->
        val width = constraints.maxWidth
        if (columns) {
            val gap = ParcelPageColumns.gap.dp.roundToPx()
            val each = ((width - gap) / 2).coerceAtLeast(0)
            val placeables = measurables.map { it.measure(constraints.copy(minWidth = each, maxWidth = each, minHeight = 0)) }
            layout(width, placeables.maxOfOrNull { it.height } ?: 0) {
                placeables.forEachIndexed { index, placeable -> placeable.placeRelative(index * (each + gap), 0) }
            }
        } else {
            val spacing = STACK_SPACING.roundToPx()
            val placeables = measurables.map { it.measure(constraints.copy(minWidth = width, maxWidth = width, minHeight = 0)) }
            val height = placeables.sumOf { it.height } + spacing * (placeables.size - 1).coerceAtLeast(0)
            layout(width, height) {
                var y = 0
                for (placeable in placeables) {
                    placeable.placeRelative(0, y)
                    y += placeable.height + spacing
                }
            }
        }
    }
}

/** The history: the journal where the page shows what the carrier answered, then the sync line, 20 apart. */
@Composable
private fun PageHistory(state: DeliveriesState, parcel: Parcel, pageId: UUID, history: Boolean, modifier: Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(STACK_SPACING)) {
        if (history) Journal(parcel)
        SyncStatus(state, parcel, pageId)
    }
}

/** The tracking history ([ParcelJournal]) in its carrier's ink. */
@Composable
private fun Journal(parcel: Parcel) {
    val identity = shownCarrierIdentity(parcel, LocalLocalizer.current, LocalLanguage.current) ?: return
    ParcelJournal(parcel, PeekTheme.colors.resolve(identity.ink), Modifier.testTag(PAGE_JOURNAL))
}

/**
 * The sync line ([SyncLine]) in its carrier's ink, with its refresh where there is a carrier to ask again: not for the
 * sample, an archived parcel, or one whose carrier Peek doesn't ask. A refresh asks the carrier again while the page
 * waits ([DeliveriesState.refreshOnPage]).
 */
@Composable
private fun SyncStatus(state: DeliveriesState, parcel: Parcel, pageId: UUID) {
    val identity = shownCarrierIdentity(parcel, LocalLocalizer.current, LocalLanguage.current) ?: return
    val refreshes = state.sample(pageId) == null && !parcel.isArchived &&
        CarrierCatalog.shared.tracksAutomatically(parcel.activeTrackingCarrier)
    SyncLine(
        parcel, PeekTheme.colors.resolve(identity.ink), working = state.pageWorking(pageId),
        onRefresh = if (refreshes) ({ state.refreshOnPage(pageId, parcel) }) else null,
        Modifier.testTag(PAGE_SYNC_LINE),
    )
}

/**
 * The page's bar over the list, which [ZoomStack][com.plhery.peekdeliverytracker.ui.zoom.ZoomStack] keeps at the
 * window's top as the page zooms under it: ‹ on its glass at the leading edge, back to the list as the system's back
 * goes, and the parcel's ••• menu on its own at the trailing edge while the page shows a parcel of the deliveries
 * (none for the sample, nor for a parcel gone). No title: the pass names the parcel.
 */
@Composable
internal fun ParcelPageBar(state: DeliveriesState, parcelId: UUID) {
    val parcel = state.liveMenuParcel(parcelId)
    val padding = tabContentPadding()
    val direction = LocalLayoutDirection.current
    val navigator = state.navigator
    Box(Modifier.fillMaxWidth().padding(top = barRowTop()).height(BAR_ROW).testTag(PAGE_BAR)) {
        GlassSurface(
            CapsuleShape,
            Modifier
                .align(Alignment.CenterStart)
                .offset(x = padding.calculateStartPadding(direction) + BAR_MARGIN)
                .pageBarItem(state.zoom, leading = true)
                .size(BAR_BUTTON),
        ) {
            // TalkBack reads it as VoiceOver reads UIKit's back button: the title of what it goes back to, the list's or
            // the page's under this one.
            val back = localized(if (navigator.navigation.path.size > 1) "detail.label" else "native.deliveries")
            BarButton("chevron.left", back, PAGE_BACK, Modifier.align(Alignment.Center)) {
                navigator.pop(navigator.navigation.path.dropLast(1))
            }
        }
        if (parcel != null) {
            GlassSurface(
                CapsuleShape,
                Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = -(padding.calculateEndPadding(direction) + BAR_MARGIN))
                    .pageBarItem(state.zoom, leading = false)
                    .size(BAR_BUTTON),
            ) {
                PageMenuButton(state, parcelId, Modifier.align(Alignment.Center))
            }
        }
    }
}

/** The parcel's ••• (`Menu { ParcelMenuButtons }`) on the page with `pageId`, which opens its menu from where it stands. */
@Composable
internal fun PageMenuButton(state: DeliveriesState, pageId: UUID, modifier: Modifier = Modifier) {
    val focus = remember { AccessibilityFocus() }
    BarButton(
        "ellipsis", localized("detail.parcelActions"), PAGE_MENU_BUTTON,
        modifier.onGloballyPositioned { state.pageMenuButton = it.boundsInWindow() }.accessibilityFocus(focus),
    ) {
        if (state.modal) return@BarButton
        state.pageMenu = PageMenuRequest(pageId)
        state.returnFocus = focus
    }
}

/**
 * The parcel of the deliveries the page with `parcelId` acts on, as the deliveries change ([DeliveriesState.menuParcel]):
 * none for the sample, nor for a parcel gone.
 */
@Composable
internal fun DeliveriesState.liveMenuParcel(parcelId: UUID?): Parcel? {
    val parcels by source.parcels.collectAsState()
    return menuParcel(parcelId, parcels)
}

/**
 * A button of the page's bar, 44 square: a symbol in the ink, semibold at 17 points and at the large image scale, as
 * the iPhone's bar draws its items (its ‹ 20 points tall, its ••• 4), whatever the text size, as a bar's items keep
 * theirs.
 */
@Composable
private fun BarButton(symbol: String, label: String, tag: String, modifier: Modifier, action: () -> Unit) {
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
        PeekSymbol(symbol, font = PeekTheme.type.fixed(BAR_SYMBOL, FontWeight.SemiBold), scale = SymbolScale.Large, tint = PeekTheme.colors.ink)
    }
}

/**
 * The page's keys (ParcelDetailView's `keys`), while the keyboard reaches the page, nothing stands over it and the
 * search field doesn't have it: Esc back to the list from a page over it, and the keys of the ••• menu's items on its
 * parcel, Delete waiting while another change is saved. A page on its way back into its card takes none.
 */
@Composable
private fun PageKeys(state: DeliveriesState, pageId: UUID, parcel: Parcel?) {
    val handle by rememberUpdatedState { event: KeyEvent -> state.pageKey(event, pageId, parcel) }
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewCompat.OnUnhandledKeyEventListenerCompat { _, event -> handle(event) }
        ViewCompat.addOnUnhandledKeyEventListener(view, listener)
        onDispose { ViewCompat.removeOnUnhandledKeyEventListener(view, listener) }
    }
}

private fun DeliveriesState.pageKey(event: KeyEvent, pageId: UUID, parcel: Parcel?): Boolean {
    if (modal || showingFilters || searchFocused) return false
    if (arrangement == DeliveriesArrangement.Stacked && navigator.navigation.chosen == null) return false
    val act = event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0
    if (arrangement == DeliveriesArrangement.Stacked && event.hasNoModifiers() && event.keyCode == KeyEvent.KEYCODE_ESCAPE) {
        if (act) navigator.pop(navigator.navigation.path.dropLast(1))
        return true
    }
    if (parcel == null) return false
    val items = ParcelMenuItem.menu(parcel, onCard = false, reader = source.viewerLink(parcel) != null).flatten()
    val item = ParcelMenuItem.forShortcut(event, items) ?: return false
    if (act && !(item == ParcelMenuItem.Delete && pageWorking(pageId))) choseOnPage(pageId, item, parcel)
    return true
}

/**
 * The page's ••• menu open over the window, on the page with `pageId`: it shows and acts on that page's parcel as it is
 * now, and goes with it ([DeliveriesState.menuParcel]).
 */
@Stable
internal class PageMenuRequest(val pageId: UUID)

/**
 * The page's ••• menu, over the whole window where the tab shell is there to draw it ([LocalTabOverlay]), its glass
 * showing the page through it ([GlassOverlayLayer]).
 */
@Composable
internal fun PageMenuHost(state: DeliveriesState) {
    val request = state.pageMenu
    val host = LocalTabOverlay.current
    val content: (@Composable () -> Unit)? = request?.let { { PageMenuOverlay(state, it) } }
    if (host == null) {
        content?.invoke()
        return
    }
    GlassOverlayLayer(host, PAGE_MENU, content)
}

/**
 * The ••• menu (B-S09, `Menu { ParcelMenuButtons }`): the parcel's items on glass in their groups, growing out of the
 * button from its top trailing corner, over nothing dimmed; as tall as the window's safe area allows below the button's
 * top, scrolling past that. The keys that press the items show with a keyboard connected, and Delete waits while
 * another change is saved. A tap outside, the back gesture or a choice closes it; a choice acts once it has closed, on
 * the parcel as it is then. It goes at once with its parcel, deleted elsewhere, as the iPhone's goes with its •••.
 * TalkBack reaches only the menu, from its first item, and goes back to ••• as it closes.
 */
@Composable
private fun PageMenuOverlay(state: DeliveriesState, request: PageMenuRequest) {
    val density = LocalDensity.current
    val parcel = state.liveMenuParcel(request.pageId)
    val gone = parcel == null || state.navigator.navigation.shown != request.pageId
    LaunchedEffect(gone) { if (gone) state.pageMenu = null }
    if (parcel == null || gone) return
    val items = remember(parcel) { ParcelMenuItem.menu(parcel, onCard = false, reader = state.source.viewerLink(parcel) != null) }
    val shortcuts = keyboardConnected()
    val title = parcel.label.ifEmpty { localized("common.parcel") }
    val menu = rememberMenuPresence(request) { state.pageMenu = null }
    val choose = { item: ParcelMenuItem -> menu.close { state.menuParcel(request.pageId)?.let { state.choseOnPage(request.pageId, item, it) } } }
    MenuKeys(shortcuts, items, choose)

    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .testTag(PAGE_MENU)
            .pointerInput(request) { detectTapGestures { menu.close() } },
    ) {
        val windowWidth = constraints.maxWidth.toFloat()
        val windowHeight = constraints.maxHeight.toFloat()
        val insets = WindowInsets.safeDrawing
        val direction = LocalLayoutDirection.current
        var origin by remember { mutableStateOf(Offset.Zero) }
        val anchor = state.pageMenuButton.translate(-origin)
        val menuWidth = with(density) { MENU_WIDTH.toPx() }
        val frame = with(density) {
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
                    val placeable = measurable.measure(Constraints(width, width, 0, frame.height.roundToInt()))
                    layout(placeable.width, placeable.height) { placeable.place(frame.left.roundToInt(), frame.top.roundToInt()) }
                }
                .growingMenu(menu, TransformOrigin(1f, 0f))
                .pointerInput(Unit) { detectTapGestures { } }
                .semantics { paneTitle = title }
                .dropShadow(ContinuousRoundedShape(MENU_RADIUS), MENU_SHADOW),
        ) {
            Column(Modifier.verticalScroll(rememberScrollState()).padding(vertical = MENU_PADDING)) {
                MenuItems(items, shortcuts, busy = state.pageWorking(request.pageId), choose, menu.first)
            }
        }
    }
}

/** How long a page made again waits for its room before it takes its place back. */
private const val ROOM_MILLIS = 500L

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
private const val BAR_SYMBOL = 17f

/** The test tags of the page's parts, its bar's buttons and its menu. */
const val PAGE_CONTENT = "parcelPage.content"
const val PAGE_COLUMNS = "parcelPage.columns"
const val PAGE_SINGLE_COLUMN = "parcelPage.singleColumn"
const val PARCEL_MISSING = "parcelPage.missing"
const val PAGE_BAR = "parcelPage.bar"
const val PAGE_BACK = "parcelPage.back"
const val PAGE_MENU_BUTTON = "parcelPage.menuButton"
const val PAGE_MENU = "parcelPage.menu"
const val PAGE_PASS = "parcelPage.pass"
const val PAGE_MARKS = "parcelPage.marks"
const val PAGE_GLOBE = "parcelPage.globe"
const val PAGE_SHARE = "parcelPage.share"
const val PAGE_BELL = "parcelPage.bell"
const val PAGE_NAME = "parcelPage.name"
const val PAGE_ROUTE = "parcelPage.route"
const val PAGE_PROMPT = "parcelPage.prompt"
const val PAGE_QUIET = "parcelPage.quiet"
const val PAGE_JOURNAL = "parcelPage.journal"
