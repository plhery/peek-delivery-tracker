package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.ClipData
import android.content.ClipboardManager
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.ImportChargesLine
import com.plhery.peekdeliverytracker.core.ParcelAttention
import com.plhery.peekdeliverytracker.core.ParcelLinkClaim
import com.plhery.peekdeliverytracker.core.ParcelLinkFailure
import com.plhery.peekdeliverytracker.core.ParcelLinkGift
import com.plhery.peekdeliverytracker.core.ParcelLinkStore
import com.plhery.peekdeliverytracker.core.ParcelTrackingLink
import com.plhery.peekdeliverytracker.core.ParcelTrackingNumber
import com.plhery.peekdeliverytracker.core.PickupPoint
import com.plhery.peekdeliverytracker.core.PipGeometry
import com.plhery.peekdeliverytracker.core.PipLabel
import com.plhery.peekdeliverytracker.core.TrackingNumberCopy
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.ParcelNumberHint
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.Parcel
import com.plhery.peekdeliverytracker.core.activeTrackingCarrier
import com.plhery.peekdeliverytracker.core.attention
import com.plhery.peekdeliverytracker.core.awaitingHandover
import com.plhery.peekdeliverytracker.core.collapsingGiftRows
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.core.deliveringCarrier
import com.plhery.peekdeliverytracker.core.displayedCarrier
import com.plhery.peekdeliverytracker.core.gift
import com.plhery.peekdeliverytracker.core.giftDetail
import com.plhery.peekdeliverytracker.core.hasCarrierUpdate
import com.plhery.peekdeliverytracker.core.hasMappedPlace
import com.plhery.peekdeliverytracker.core.masked
import com.plhery.peekdeliverytracker.core.sharedParcelDetail
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.CarrierFleetMark
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.SmallPip
import com.plhery.peekdeliverytracker.ui.draw.UnevenContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.map.RouteEngraving
import com.plhery.peekdeliverytracker.ui.map.rememberParcelRoute
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.sheet.BAR_ROW
import com.plhery.peekdeliverytracker.ui.sheet.BarButton
import com.plhery.peekdeliverytracker.ui.sheet.FormBar
import com.plhery.peekdeliverytracker.ui.sheet.FormScrollEdge
import com.plhery.peekdeliverytracker.ui.sheet.FullScreenCover
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetInsets
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.sheetBottomInset
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.ProvideDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.delay
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberHaptics
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.max
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * What the sheet does that outlives a presentation's drawing: Add or follow under way (`adding`), a read asked for
 * again (`refreshing`) and why keeping failed (`errorMessage`). Each runs on the app's scope, so the window made again
 * doesn't cancel it half done.
 */
@Stable
internal class ParcelLinkSheetState(private val source: ParcelLinkSource) {
    var adding by mutableStateOf(false)
        private set
    var refreshing by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)

    /** `refresh()`: the link read again, once at a time. */
    fun refresh() {
        if (refreshing) return
        refreshing = true
        source.scope.launch {
            try {
                source.links.load()
            } finally {
                refreshing = false
            }
        }
    }

    /** `add()`: the parcel kept in the account. An added parcel closes the sheet; an answer stays on it. */
    fun add() {
        val links = source.links
        val link = links.route
        if (adding || link == null) return
        adding = true
        errorMessage = null
        source.scope.launch {
            try {
                val claim = source.keep(link)
                // The person may have closed this link, or opened another, while it was kept.
                if (links.route == link) links.resolve(claim)
            } catch (_: CancellationException) {
            } catch (error: Exception) {
                errorMessage = source.errorMessage(error)
            } finally {
                adding = false
            }
        }
    }

    /** `follow(_:)`: the parcel followed on this phone. One that was here already opens. */
    fun follow(response: PublicParcelResponse) {
        val links = source.links
        val link = links.route
        if (adding || link == null) return
        source.action("parcel-link-alerts")
        adding = true
        errorMessage = null
        source.scope.launch {
            try {
                val claim = source.follow(link, response)
                // The person may have closed this link, or opened another, meanwhile.
                if (links.route == link) {
                    val id = (claim as? ParcelLinkClaim.Already)?.id
                    if (id != null) links.openExisting(id) else links.resolve(claim)
                }
            } catch (_: CancellationException) {
            } catch (error: Exception) {
                errorMessage = source.errorMessage(error)
            } finally {
                adding = false
            }
        }
    }

    /** `signIn()`: the link kept for after sign-in, without its key, and the sign-in shown. */
    fun signIn() {
        source.action("parcel-link-sign-in")
        source.links.rememberForSignIn()
        if (source.session.value !is AuthenticationState.SignedIn) source.showSignIn()
    }
}

/**
 * `FrontmostSheet { ParcelLinkSheet() }` (A-S05, B-S50): the link's sheet over whatever is up, the list, a page, Add,
 * the share sheet, a cover or a menu, signed in or not, since it is drawn in the window's front layer over all of
 * them. On a phone it is the large sheet without a grabber, as the iPhone's page sheet; in a regular window it is the
 * form sheet, as the iPad's. It is never refused, so it needs none of the iPhone's 350 ms retries past an alert or a
 * transition. A second link while it is up opens in it, as a new presentation. The person closing it (Close, a drag,
 * the dim, the back) is `links.close()`; once it has gone, `links.dismissed()` lets the list take over. While a keep or
 * a follow runs, none of those closes it (`interactiveDismissDisabled(adding)`, B-G09). The window made again keeps it
 * up, as the store, not the window, holds the link.
 */
@Composable
internal fun ParcelLinkSheetHost(source: ParcelLinkSource) {
    val links = source.links
    val route by links.routeFlow.collectAsState()
    val state = remember(source) { ParcelLinkSheetState(source) }
    Sheet(
        visible = route != null,
        onDismiss = { links.close() },
        paneTitle = localized("link.shared"),
        detents = listOf(SheetDetent.Large),
        onClosed = { links.dismissed() },
        dismissible = !state.adding,
        grabber = false,
    ) {
        ParcelLinkSheet(source, state)
    }
}

/**
 * ParcelLinkSheet (B-S41, B-ST28): the inline bar, its title "link.shared" but for a handed-over link, a gift as its
 * recipient sees it and a stopped link, which keep the sheet to themselves, and Close at its trailing end, off while
 * keeping; under it, the phase: a spinner while reading, the parcel, or what stands for none. Each presentation reads
 * the link once, its error cleared, and a link handed over with its owner key is kept or followed by itself, once.
 */
@Composable
private fun ParcelLinkSheet(source: ParcelLinkSource, state: ParcelLinkSheetState) {
    val links = source.links
    val localizer = LocalLocalizer.current
    val route by links.routeFlow.collectAsState()
    val presentation by links.presentationIdFlow.collectAsState()
    val phase by links.phaseFlow.collectAsState()
    val session by source.session.collectAsState()
    val loadingSession = session == AuthenticationState.Loading
    val signedIn = links.previewsSignedIn || session is AuthenticationState.SignedIn
    val followsHere = !loadingSession && source.tracksWithoutAccount
    val canSignIn = !loadingSession && source.authenticationConfigured

    // `.task(id: presentationID)`: once per presentation, and not again in the window made again.
    var loaded by rememberSaveable { mutableStateOf<String?>(null) }
    LaunchedEffect(presentation) {
        if (loaded == presentation.toString()) return@LaunchedEffect
        loaded = presentation.toString()
        state.errorMessage = null
        source.scope.launch { links.load() }
    }
    // The link handed over with its owner key, read as its owner's and nothing done with it yet: kept, or followed.
    val shown = phase as? ParcelLinkStore.Phase.Shown
    val handOver = shown?.response?.takeIf {
        route?.key != null && it.link.role == ParcelLinkRole.Owner && !loadingSession && (signedIn || followsHere)
    }
    val latestHandOver by rememberUpdatedState(handOver)
    var handedOver by rememberSaveable { mutableStateOf<String?>(null) }
    LaunchedEffect(handOver == null, presentation) {
        val response = latestHandOver ?: return@LaunchedEffect
        if (handedOver == presentation.toString()) return@LaunchedEffect
        handedOver = presentation.toString()
        if (signedIn) state.add() else state.follow(response)
    }

    val title = when {
        // A parcel the site hands over is the person's own, and the sheet only passes by.
        route?.key != null -> ""
        shown != null -> shown.response.gift.let { if (it == ParcelLinkGift.Wrapped || it == ParcelLinkGift.Opened) "" else localizer.text("link.shared") }
        phase == ParcelLinkStore.Phase.Stopped -> ""
        else -> localizer.text("link.shared")
    }
    val scroll = rememberScrollState()
    LinkSheetPage(title, closeEnabled = !state.adding, close = { links.close() }, scrolled = { scroll.value.toFloat() }) { top, bottom ->
        key(presentation) {
            when (val current = phase) {
                ParcelLinkStore.Phase.Loading -> Centered(top, bottom) { ActivityIndicator(Modifier.testTag(LINK_LOADING)) }
                is ParcelLinkStore.Phase.Shown -> LinkShared(
                    source, state, current.response, top, bottom, scroll,
                    keeping = KeepingContext(signedIn, followsHere, canSignIn),
                )
                ParcelLinkStore.Phase.Unavailable -> Centered(top, bottom) { Nothing("link.gone.title", "link.gone.body") { links.close() } }
                ParcelLinkStore.Phase.Stopped -> Centered(top, bottom) { Nothing("share.stopped.title", "share.stopped.body") { links.close() } }
                is ParcelLinkStore.Phase.Failed -> Centered(top, bottom) { Failed(current.failure, state.refreshing) { state.refresh() } }
            }
        }
    }
}

/** Who is looking, for the keeping block: signed in, may follow on this phone, may sign in. */
private class KeepingContext(val signedIn: Boolean, val followsHere: Boolean, val canSignIn: Boolean)

/**
 * The sheet's `NavigationStack`: the page's ground, what it holds clear of the bar ([content] gets the bar's height
 * and the navigation bar's, to keep clear of), the scroll edge once [scrolled], and the inline bar over it, its
 * [title] in the middle as a heading and Close on glass at its trailing end, semibold as the confirmation.
 */
@Composable
private fun LinkSheetPage(
    title: String,
    closeEnabled: Boolean,
    close: () -> Unit,
    scrolled: () -> Float,
    content: @Composable (top: Dp, bottom: Dp) -> Unit,
) {
    val backdrop = rememberMaterialBackdrop()
    val insets = LocalSheetInsets.current
    val barHeight = insets.barTop + BAR_ROW
    val bottom = sheetBottomInset()
    Box(Modifier.fillMaxSize().testTag(LINK_SHEET)) {
        Box(Modifier.fillMaxSize().materialBackdrop(backdrop).background(PeekTheme.colors.background)) {
            content(barHeight, bottom)
        }
        FormScrollEdge(barHeight, scrolled)
        ProvideDynamicTypeSize(DynamicTypeSize.Large) {
            FormBar(
                title, null, Modifier.fillMaxWidth().padding(top = insets.barTop).height(BAR_ROW),
                confirmation = { BarButton(localized("common.close"), close, backdrop, FontWeight.SemiBold, closeEnabled, LINK_CLOSE) },
                cancellation = null,
            )
        }
    }
}

/** What stands for the parcel, in the middle of the room under the bar. */
@Composable
private fun Centered(top: Dp, bottom: Dp, content: @Composable () -> Unit) {
    Box(Modifier.fillMaxSize().padding(top = top, bottom = bottom), contentAlignment = Alignment.Center) { content() }
}

/**
 * `shared(_:)`: the parcel, its card, a later read's failure, a gift's surprise or note, the keeping block, the quiet
 * parcel's note (B-S76), then 6 in its number, its import charges and its journal. A gift on its way tells its hidden
 * beginning once (`collapsingGiftRows`) and hides its number, in lilac. It scrolls at most 560 wide in the middle, and
 * a pull from its top reads the link again (B-G12).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LinkShared(
    source: ParcelLinkSource,
    state: ParcelLinkSheetState,
    response: PublicParcelResponse,
    top: Dp,
    bottom: Dp,
    scroll: ScrollState,
    keeping: KeepingContext,
) {
    val links = source.links
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current ?: return
    val now = LocalNow.current
    val route by links.routeFlow.collectAsState()
    val refreshFailure by links.refreshFailureFlow.collectAsState()
    val gift = response.gift
    val wrapped = gift == ParcelLinkGift.Wrapped
    val opened = gift == ParcelLinkGift.Opened
    val parcel = remember(response, wrapped) {
        val shared = Parcel(shared = response.parcel)
        // A gift on its way tells its hidden beginning once.
        if (wrapped) shared.copy(trackingEvents = shared.trackingEvents.collapsingGiftRows()) else shared
    }
    val identity = remember(identities, parcel, language) { identities.of(parcel.displayedCarrier, language) }
    val colors = PeekTheme.colors
    val tint = if (wrapped || opened) colors.resolve(ExperimentalPalette.lilac) else colors.resolve(identity.ink)
    val pull = rememberPullToRefreshState()
    PullToRefreshBox(
        isRefreshing = state.refreshing,
        onRefresh = { state.refresh() },
        modifier = Modifier.fillMaxSize().padding(top = top),
        state = pull,
        indicator = {
            // The iPhone's refresh control: its spinner, coming in with the pull.
            Box(
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 12.dp)
                    .graphicsLayer { alpha = if (state.refreshing) 1f else pull.distanceFraction.coerceIn(0f, 1f) },
            ) {
                ActivityIndicator(size = 20.dp, tint = colors.secondaryLabel)
            }
        },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .testTag(LINK_SCROLL)
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 32.dp + bottom),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(Modifier.widthIn(max = 560.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                if (wrapped || opened) {
                    GiftParcelCard(parcel, opened, state.refreshing) { state.refresh() }
                } else {
                    SharedParcelCard(source, parcel, route?.name, response.parcel.numberHint, gift == ParcelLinkGift.Own, state.refreshing) {
                        state.refresh()
                    }
                }
                refreshFailure?.let { LinkNote(localizer.text(it.messageKey)) }
                if (wrapped) {
                    GiftSurprise()
                } else if (opened) {
                    // What the link carried all along comes out with the delivery.
                    GiftNote(route?.note, route?.from, route?.name)
                }
                // A gift cannot be kept before it is delivered: that would show its number.
                Keeping(source, state, response, keeping, keepable = response.link.canKeep && !wrapped, followable = !wrapped)
                if (!wrapped && parcel.displayedCarrier != CarrierId.Unknown && parcel.attention(now()) == ParcelAttention.Stalled) {
                    ParcelQuietNote(parcel, tint, onHelp = { source.action("parcel-carrier-help") })
                }
                Column(Modifier.padding(start = 6.dp, top = 6.dp, end = 6.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    // The number and the carrier's own page would tell where a gift comes from.
                    if (!wrapped) SharedParcelNumber(source, parcel, response.parcel.numberHint, tint)
                    // A gift's recipient usually pays them at the door.
                    localizer.importCharges(parcel)?.let { SharedImportCharges(it, asksForNumber = parcel.trackingNumber.isEmpty()) }
                    if (identities.catalog.tracksAutomatically(parcel.activeTrackingCarrier) || parcel.hasCarrierUpdate) {
                        ParcelJournal(parcel, tint)
                    }
                }
            }
        }
    }
}

/**
 * `keeping(_:keepable:followable:)` (B-S42, B-ST29): one tap to keep the parcel for someone signed in, or the answer
 * already given (already followed, no room). Anyone else follows it on this phone, or signs in to keep it, or sees it
 * is followed here already. Following needs a build with a server and the saved account reopened; signing in needs a
 * way to sign in.
 */
@Composable
private fun Keeping(
    source: ParcelLinkSource,
    state: ParcelLinkSheetState,
    response: PublicParcelResponse,
    context: KeepingContext,
    keepable: Boolean,
    followable: Boolean,
) {
    val links = source.links
    val localizer = LocalLocalizer.current
    val claim by links.claimFlow.collectAsState()
    // The list's parcels change as this phone follows one, which `followedHere` reads.
    val parcels by source.parcels.collectAsState()
    if (context.signedIn) {
        if (!keepable) return
        val already = claim as? ParcelLinkClaim.Already
        if (already != null) {
            AlreadyFollowed(source, already.id, here = false, parcels)
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (claim == ParcelLinkClaim.Full) LinkNote(localizer.text("linkapp.full"))
                state.errorMessage?.let { LinkNote(it) }
                LinkPrimaryButton(localizer.text("link.add"), "plus", LINK_ADD, busy = state.adding) { state.add() }
                LinkHint(localizer.text("link.addHint"))
            }
        }
        return
    }
    val here = remember(response, parcels) { source.followedHere(response) }
    if (here != null) {
        AlreadyFollowed(source, here, here = true, parcels)
    } else if (followable && context.followsHere) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            state.errorMessage?.let { LinkNote(it) }
            LinkPrimaryButton(localizer.text("native.follow.action"), "bell", LINK_FOLLOW, busy = state.adding) { state.follow(response) }
            LinkHint(localizer.text("native.follow.hint"))
            if (keepable && context.canSignIn) {
                val enabled = !state.adding
                Box(
                    Modifier
                        .fillMaxWidth()
                        .heightIn(min = 44.dp)
                        .testTag(LINK_SIGN_IN)
                        .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button) { state.signIn() }
                        .plainDisabled(enabled),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        localizer.text("link.signInToAdd"),
                        color = PeekTheme.colors.ink,
                        style = PeekTheme.type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    } else if (keepable && context.canSignIn) {
        LinkPrimaryButton(localizer.text("link.signInToAdd"), null, LINK_SIGN_IN, busy = false) { state.signIn() }
    }
}

/** `hint(_:)`: caption secondary, in the middle. */
@Composable
private fun LinkHint(text: String) {
    Text(
        text, Modifier.fillMaxWidth(), color = PeekTheme.colors.secondaryLabel, style = PeekTheme.type.caption.sfLines(),
        textAlign = TextAlign.Center,
    )
}

/**
 * `primaryButton(_:symbol:busy:action:)`: its symbol in body semibold, or a spinner while busy, 9 before its words in
 * headline, on the accent across the sheet, 20 and 13 in, 50 tall at least with 16-point corners, giving under the
 * finger; off while busy, which TalkBack hears as in progress.
 */
@Composable
private fun LinkPrimaryButton(title: String, symbol: String?, tag: String, busy: Boolean, action: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(tag)
            .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = !busy, role = Role.Button, onClick = action)
            .semantics { if (busy) progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate }
            .background(colors.accent, ContinuousRoundedShape(16.dp))
            .heightIn(min = 50.dp)
            .padding(horizontal = 20.dp, vertical = 13.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (busy) {
            ActivityIndicator(Modifier.clearAndSetSemantics {}, size = REGULAR_SPINNER, tint = colors.onAccent)
        } else if (symbol != null) {
            PeekSymbol(symbol, font = type.style(IosTextStyle.Body, FontWeight.SemiBold), tint = colors.onAccent)
        }
        Text(title, Modifier.weight(1f, fill = false), color = colors.onAccent, style = type.headline.sfLines(), textAlign = TextAlign.Center)
    }
}

/**
 * `alreadyFollowed(_:here:)`: the delivered check, "link.already.title" in subheadline semibold and, when the parcel
 * has a name, "link.already.body" in footnote secondary, read as one; then "link.open" with its chevron on the accent,
 * which closes the sheet and has the list open the parcel, among the ones this phone follows itself when `here`. 16 in
 * on the paper, with 16-point corners.
 */
@Composable
private fun AlreadyFollowed(source: ParcelLinkSource, parcelId: UUID?, here: Boolean, parcels: List<Parcel>) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val name = parcelId?.let { id -> parcels.firstOrNull { it.id == id } }?.label?.ifEmpty { null }
    Column(
        Modifier
            .fillMaxWidth()
            .testTag(LINK_ALREADY)
            .background(colors.paper, ContinuousRoundedShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Top) {
            PeekSymbol("checkmark.circle.fill", font = type.body, tint = colors.resolve(ExperimentalPalette.delivered))
            Column(Modifier.semantics(mergeDescendants = true) {}, verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(localizer.text("link.already.title"), color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
                if (name != null) {
                    Text(localizer.text("link.already.body", mapOf("name" to name)), color = colors.secondaryLabel, style = type.footnote.sfLines())
                }
            }
        }
        if (parcelId != null) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .testTag(LINK_OPEN_EXISTING)
                    .clickable(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button) {
                        source.action("parcel-link-open-existing")
                        if (here) source.showDeviceParcels()
                        source.links.openExisting(parcelId)
                    }
                    .background(colors.accent, ContinuousRoundedShape(12.dp))
                    .heightIn(min = 44.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(localizer.text("link.open"), color = colors.onAccent, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
                PeekSymbol("chevron.right", font = type.style(IosTextStyle.Caption, FontWeight.SemiBold), tint = colors.onAccent)
            }
        }
    }
}

/** `note(_:)`: the exclamation mark 10 before the words in footnote, in the warning colour on its soft tint at 60 %. */
@Composable
private fun LinkNote(text: String) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(LINK_NOTE)
            .background(colors.warningSoft.copy(alpha = colors.warningSoft.alpha * 0.6f), ContinuousRoundedShape(12.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top,
    ) {
        PeekSymbol("exclamationmark.circle", font = type.footnote, tint = colors.warning)
        Text(text, Modifier.weight(1f), color = colors.warning, style = type.footnote.sfLines())
    }
}

/**
 * `nothing(title:body:)` (B-S49, B-AX15): a link that shows no parcel, forgotten or unknown alike, or stopped. Pip
 * 120 wide, washed out, the title in title2 semibold as a heading, the body in subheadline secondary, and Close on the
 * accent; in the middle, 32 in, 480 wide at most.
 */
@Composable
private fun Nothing(title: String, body: String, close: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(
        Modifier.widthIn(max = 480.dp).padding(horizontal = 32.dp).testTag(LINK_NOTHING),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SmallPip(Modifier.padding(bottom = 8.dp).size(width = 120.dp, height = Dp.Unspecified).washedOut().graphicsLayer { alpha = 0.8f })
        Text(
            localized(title), Modifier.semantics { heading() }, color = colors.label,
            style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(), textAlign = TextAlign.Center,
        )
        Text(localized(body), color = colors.secondaryLabel, style = type.subheadline.sfLines(), textAlign = TextAlign.Center)
        ProminentButton(localized("common.close"), LINK_NOTHING_CLOSE, enabled = true, Modifier.padding(top = 8.dp), close)
    }
}

/**
 * `failed(_:)` (B-S49): the no-Wi-Fi symbol offline, else the exclamation mark, 34 light in the secondary grey, what
 * went wrong, and Try again on the accent, off while a read runs.
 */
@Composable
private fun Failed(failure: ParcelLinkFailure, refreshing: Boolean, retry: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(
        Modifier.widthIn(max = 480.dp).padding(horizontal = 32.dp).testTag(LINK_FAILED),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PeekSymbol(
            if (failure == ParcelLinkFailure.Offline) "wifi.exclamationmark" else "exclamationmark.circle",
            font = type.fixed(34f, FontWeight.Light), tint = colors.secondaryLabel,
        )
        Text(localized(failure.messageKey), color = colors.secondaryLabel, style = type.subheadline.sfLines(), textAlign = TextAlign.Center)
        ProminentButton(localized("app.tryAgain"), LINK_TRY_AGAIN, enabled = !refreshing, Modifier.padding(top = 8.dp), retry)
    }
}

/** `.buttonStyle(.borderedProminent)` in the accent: body on a capsule, 44 tall, greyed while off. */
@Composable
private fun ProminentButton(title: String, tag: String, enabled: Boolean, modifier: Modifier, action: () -> Unit) {
    val colors = PeekTheme.colors
    Box(
        modifier
            .testTag(tag)
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = action)
            .plainDisabled(enabled)
            .background(colors.accent, CapsuleShape)
            .heightIn(min = 44.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(title, color = colors.onAccent, style = PeekTheme.type.body.sfLines(), textAlign = TextAlign.Center)
    }
}

/**
 * `.padding(.top, -top)` and `.padding(.bottom, -bottom)`: drawn as it is, [top] over what comes before and [bottom]
 * over what comes after.
 */
private fun Modifier.overlap(top: Dp, bottom: Dp): Modifier = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val above = top.roundToPx()
    layout(placeable.width, max(0, placeable.height - above - bottom.roundToPx())) { placeable.place(0, -above) }
}

/** `.saturation(0.3)`: what is drawn, with 30 % of its colour. */
private fun Modifier.washedOut(): Modifier = drawWithCache {
    val paint = Paint().apply { colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.3f) }) }
    onDrawWithContent {
        drawIntoCanvas { canvas ->
            canvas.saveLayer(size.toRect(), paint)
            drawContent()
            canvas.restore()
        }
    }
}

/**
 * SharedParcelCard (B-S43, B-ST31): what is happening first, since a shared parcel rarely has a name. Its carrier's
 * mark, and the delivering carrier's where it was handed over, then the refresh button and, with a route, the globe,
 * 44 tall. With places, the route is engraved across its top (176 tall, 40 more with a second mark), and a tap there
 * or on the globe opens the map. Without one, a parcel that arrived shows its box opening above the words, and any
 * other the kraft parcel 84 wide beside them, its carrier's label on its side with the number as the link shows it;
 * neither at the accessibility sizes. Then a gift's marker for its sender, the name, the status in title (large title
 * once arrived) as a heading, where and when, the sender, the pickup point, who delivers and, but for a gift, that the
 * sender still has it; the journey rail at its foot. 18 in on the carrier's surface, with 24-point corners.
 */
@Composable
private fun SharedParcelCard(
    source: ParcelLinkSource,
    parcel: Parcel,
    name: String?,
    numberHint: ParcelNumberHint?,
    gift: Boolean,
    refreshing: Boolean,
    onRefresh: () -> Unit,
) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current ?: return
    val now = LocalNow.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val density = LocalDensity.current
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val reduceMotion = PeekTheme.reduceMotion
    val identity = remember(identities, parcel, language) { identities.of(parcel.displayedCarrier, language) }
    val ink = colors.resolve(identity.ink)
    val secondary = colors.secondaryLabel
    val placed = parcel.hasMappedPlace
    val arrived = !placed && parcel.currentStage == TrackingStage.Delivered && !large
    val label = if (parcel.carrier != CarrierId.Unknown || parcel.hasCarrierUpdate) {
        PipLabel(identity, numberHint?.masked ?: CarrierCatalog.format(parcel.trackingNumber, parcel.carrier))
    } else {
        null
    }
    val atlas = rememberWorldAtlas(placed)
    val route = if (placed && atlas != null) rememberParcelRoute(parcel, atlas, language) else null
    // Handed from one carrier to another, or about to be, the card carries both marks and says who delivers.
    val delivering = parcel.deliveringCarrier
    val delivery = remember(identities, delivering, language) { delivering?.let { identities.of(it, language) } }
    val deliveryLine = delivering?.let { localizer.text("parcel.deliveryCarrier", mapOf("carrier" to identities.displayName(it, language))) }
    val metrics = PassRouteMetrics.of(handover = delivery != null, largeText = false, headerHeight = 0.0)
    val frames = remember { PassFrames() }
    var showingMap by rememberSaveable { mutableStateOf(false) }
    val openMap = {
        source.action("parcel-map-open")
        showingMap = true
    }
    // A map that keeps clear of the second mark and the buttons waits to know where they stand.
    val engraved = route?.takeIf { frames.buttons != null && (delivery == null || frames.deliveryMark != null) }
    Box(
        Modifier
            .fillMaxWidth()
            .background(colors.resolve(identity.surface), ContinuousRoundedShape(CARD_RADIUS))
            .onGloballyPositioned {
                frames.card = it
                frames.measure(density)
            }
            .testTag(LINK_CARD),
    ) {
        if (engraved != null && atlas != null) {
            Box(
                Modifier
                    .matchParentSize()
                    .wrapContentHeight(Alignment.Top, unbounded = true)
                    .fillMaxWidth()
                    .height(metrics.height.dp)
                    .clip(UnevenContinuousRoundedShape(topLeading = CARD_RADIUS, topTrailing = CARD_RADIUS))
                    .pointerInput(Unit) { detectTapGestures { openMap() } }
                    .testTag(LINK_ROUTE),
            ) {
                // The status is written over the bottom of the map, so Pip stays above it.
                RouteEngraving(
                    atlas = atlas, route = engraved, stage = parcel.currentStage, identity = identity, language = language,
                    modifier = Modifier.fillMaxSize(), floor = metrics.floor, covered = if (delivery == null) null else frames.deliveryMark,
                    buttons = frames.buttons, room = metrics.routeRoom, entrance = if (reduceMotion) null else Motion.easeOut(0.5),
                )
            }
        }
        Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            val tint = ink.copy(alpha = ink.alpha * 0.75f)
            CardHeader(
                marks = {
                    // Over the route the second mark takes no room, and the map keeps clear of it.
                    if (placed) PassMarks(identity, delivery, frames, change = null, Modifier) else CarrierFleetMark(identity)
                },
                second = if (!placed && delivery != null) ({ CarrierFleetMark(delivery) }) else null,
                buttons = {
                    Row(
                        Modifier.onGloballyPositioned {
                            frames.buttonsNode = it
                            frames.measure(density)
                        },
                    ) {
                        LinkRefreshButton(parcel, refreshing, tint, onRefresh)
                        if (placed) {
                            HeaderButton(localizer.text("map.open"), LINK_MAP, enabled = route != null, Modifier, openMap) {
                                PeekSymbol("globe", font = type.fixed(17f), tint = tint)
                            }
                        }
                    }
                },
                reserved = if (placed) 2 else 1,
            )
            // Room for the route engraved behind this part of the card.
            if (placed) Spacer(Modifier.height(metrics.spacer.dp))
            if (arrived) {
                OpeningParcel(width = 230.dp, label = label, ribbon = false, opens = true, Modifier.align(Alignment.CenterHorizontally).overlap(top = 34.dp, bottom = 22.dp))
            }
            val center = if (arrived) Alignment.CenterHorizontally else Alignment.Start
            val textAlign = if (arrived) TextAlign.Center else TextAlign.Start
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp), horizontalAlignment = center) {
                    if (gift) GiftMarker()
                    if (name != null) Text(name, color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines(), textAlign = textAlign)
                    Text(
                        localizer.parcelStatus(parcel),
                        Modifier.semantics { heading() },
                        color = colors.label,
                        style = type.style(if (arrived) IosTextStyle.LargeTitle else IosTextStyle.Title, FontWeight.SemiBold).sfLines(),
                        textAlign = textAlign,
                    )
                    localizer.sharedParcelDetail(parcel, now())?.let {
                        Text(it, color = ink, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines(), textAlign = textAlign)
                    }
                    val sender = parcel.carrierData?.senderName?.ifEmpty { null }
                    val pickedUpAt = if (parcel.currentStage == TrackingStage.Delivered) PickupPoint(parcel.carrierData?.pickupPoint)?.name else null
                    if (sender != null || pickedUpAt != null || deliveryLine != null) {
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp), horizontalAlignment = center) {
                            val caption = type.caption.sfLines()
                            if (sender != null) Text(localizer.text("parcel.sender", mapOf("sender" to sender)), color = secondary, style = caption, textAlign = textAlign)
                            if (pickedUpAt != null) Text(localizer.text("parcel.pickedUpAt", mapOf("place" to pickedUpAt)), color = secondary, style = caption, textAlign = textAlign)
                            if (deliveryLine != null) Text(deliveryLine, color = secondary, style = caption, textAlign = textAlign)
                        }
                    }
                    // A label a day old or more: the sender still has the parcel, and the carrier can tell nothing until then.
                    if (!gift && parcel.awaitingHandover(now())) {
                        Text(
                            localizer.text("parcel.awaitingHandover", mapOf("carrier" to identities.displayName(parcel.displayedCarrier, language))),
                            color = secondary, style = type.caption.sfLines(), textAlign = textAlign,
                        )
                    }
                }
                // Without a route to draw, the kraft parcel keeps the card company, its carrier's label on its side.
                if (!placed && !arrived && !large) SmallPip(Modifier.size(width = 84.dp, height = Dp.Unspecified).testTag(LINK_PIP), label = label)
            }
            JourneyRail(parcel.currentStage, ink.copy(alpha = ink.alpha * 0.45f), compact = true)
        }
    }
    if (atlas != null && route != null) {
        FullScreenCover(visible = showingMap, onDismiss = { showingMap = false }, paneTitle = localizer.text("map.open")) {
            ParcelMapScreen(
                atlas = atlas, route = route, stage = parcel.currentStage, accent = identity.ink, hinge = null, levelHinge = null,
                onClose = { showingMap = false },
            )
        }
    }
}

/**
 * The card's top row, 44 tall at least: the marks, which take their room first, then 8 at least, then the buttons at
 * its trailing end with what is left, lined up with the middle of the first mark (`.firstMark`). A second mark not
 * over a route stands 7 under the first, taking room of its own. `reserved` buttons of 44 are kept clear of the marks.
 */
@Composable
private fun CardHeader(marks: @Composable () -> Unit, second: (@Composable () -> Unit)?, buttons: @Composable () -> Unit, reserved: Int) {
    Layout(
        content = {
            Box { marks() }
            if (second != null) Box { second() }
            Box { buttons() }
        },
    ) { measurables, constraints ->
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        val gap = 8.dp.roundToPx()
        val markMax = max(0, constraints.maxWidth - gap - reserved * 44.dp.roundToPx())
        val first = measurables[0].measure(loose.copy(maxWidth = markMax))
        val under = if (second != null) measurables[1].measure(loose.copy(maxWidth = markMax)) else null
        val markWidth = max(first.width, under?.width ?: 0)
        val row = measurables.last().measure(loose.copy(maxWidth = max(0, constraints.maxWidth - markWidth - gap)))
        val line = maxOf(first.height, row.height, 44.dp.roundToPx())
        val markY = (line - first.height) / 2
        val spacing = 7.dp.roundToPx()
        val height = max(line, if (under != null) markY + first.height + spacing + under.height else 0)
        layout(constraints.maxWidth, height) {
            first.place(0, markY)
            under?.place(0, markY + first.height + spacing)
            row.place(constraints.maxWidth - row.width, (line - row.height) / 2)
        }
    }
}

/** "share.gift.marker" with the gift symbol in caption semibold lilac, 8 and 3 in on the paper at 72 %. */
@Composable
private fun GiftMarker() {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val lilac = colors.resolve(ExperimentalPalette.lilac)
    val style = type.style(IosTextStyle.Caption, FontWeight.SemiBold)
    Row(
        Modifier
            .background(colors.paper.copy(alpha = colors.paper.alpha * 0.72f), ContinuousRoundedShape(10.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeekSymbol("gift", font = style, tint = lilac)
        Text(localized("share.gift.marker"), color = lilac, style = style.sfLines())
    }
}

/**
 * LinkRefreshButton (B-S48, B-AN21, B-AX15): when the carrier was last asked, "parcel.updated" kept current each
 * minute, and the arrow, or a spinner while the link is read, 16 square, in caption, in `tint` at 75 %, 44 at least.
 * Where the words don't fit beside the marks (`ViewThatFits`), the arrow alone. TalkBack hears "detail.checkNow" and,
 * as its value, when it was updated.
 */
@Composable
private fun LinkRefreshButton(parcel: Parcel, refreshing: Boolean, tint: Color, action: () -> Unit) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val now = LocalNow.current
    val checked = parcel.lastSyncedAt
    // `TimelineView(.periodic(from: .now, by: 60))`: the age is written out, so it is kept current while the sheet stays.
    val time by produceState(now(), checked) {
        while (checked != null) {
            delay(60_000)
            value = now()
        }
    }
    val updated = checked?.let { localizer.text("parcel.updated", mapOf("date" to localizer.relativeTime(from = it, now = time))) }
    val color = tint.copy(alpha = tint.alpha * 0.75f)
    val enabled = !refreshing
    val label = localizer.text("detail.checkNow")
    val indicator = @Composable {
        Box(Modifier.size(16.dp), contentAlignment = Alignment.Center) {
            if (refreshing) {
                ActivityIndicator(size = 12.dp, tint = color)
            } else {
                PeekSymbol("arrow.clockwise", font = type.style(IosTextStyle.Caption2, FontWeight.Medium), tint = color)
            }
        }
    }
    Box(
        Modifier
            .heightIn(min = 44.dp)
            .testTag(LINK_REFRESH)
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = action)
            .clearAndSetSemantics {
                contentDescription = label
                stateDescription = updated ?: ""
                role = Role.Button
                if (!enabled) disabled()
                onClick {
                    action()
                    true
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Layout(
            content = {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    if (updated != null) Text(updated, color = color, style = type.caption.sfLines(), maxLines = 1, softWrap = false)
                    indicator()
                }
                indicator()
            },
        ) { measurables, constraints ->
            val minimum = 44.dp.roundToPx()
            val dated = measurables[0].measure(Constraints())
            val shown = if (dated.width <= constraints.maxWidth) dated else measurables[1].measure(Constraints())
            val width = max(minimum, shown.width).coerceAtMost(max(minimum, constraints.maxWidth))
            val height = max(minimum, shown.height)
            layout(width, height) { shown.place((width - shown.width) / 2, (height - shown.height) / 2) }
        }
    }
}

/**
 * GiftParcelCard (B-S44): a gift as its recipient sees it, in lilac: wrapped while it is on its way, with nothing
 * about who sent it or what it is, and its box opening once delivered. Its carriers' marks and the refresh button,
 * the ribboned box (150 wide at the accessibility sizes, 250 open, 220 wrapped), "share.gift.here" in large title or
 * "share.gift.headline" in title as a heading, when it arrives or arrived, who delivers, and the journey rail until it
 * is there. 18 in, 10 at the top and 20 at the foot, on the lilac surface with 24-point corners.
 */
@Composable
private fun GiftParcelCard(parcel: Parcel, opened: Boolean, refreshing: Boolean, onRefresh: () -> Unit) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current ?: return
    val now = LocalNow.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val identity = remember(identities, parcel, language) { identities.of(parcel.displayedCarrier, language) }
    val delivering = parcel.deliveringCarrier
    val delivery = remember(identities, delivering, language) { delivering?.let { identities.of(it, language) } }
    val deliveryLine = delivering?.let { localizer.text("parcel.deliveryCarrier", mapOf("carrier" to identities.displayName(it, language))) }
    val tone = colors.resolve(ExperimentalPalette.lilac)
    val width = if (PeekTheme.dynamicTypeSize.isAccessibilitySize) 150.dp else if (opened) 250.dp else 220.dp
    Column(
        Modifier
            .fillMaxWidth()
            .background(colors.resolve(ExperimentalPalette.lilacSurface), ContinuousRoundedShape(CARD_RADIUS))
            .padding(start = 18.dp, top = 10.dp, end = 18.dp, bottom = 20.dp)
            .testTag(LINK_GIFT_CARD),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CardHeader(
            marks = { CarrierFleetMark(identity) },
            second = delivery?.let { { CarrierFleetMark(it) } },
            buttons = { LinkRefreshButton(parcel, refreshing, tone, onRefresh) },
            reserved = 1,
        )
        OpeningParcel(
            width, label = null, ribbon = true, opens = opened,
            Modifier.overlap(top = if (opened) 6.dp else 16.dp, bottom = if (opened) 8.dp else 6.dp),
        )
        Column(verticalArrangement = Arrangement.spacedBy(7.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                localizer.text(if (opened) "share.gift.here" else "share.gift.headline"),
                Modifier.semantics { heading() },
                color = colors.label,
                style = type.style(if (opened) IosTextStyle.LargeTitle else IosTextStyle.Title, FontWeight.SemiBold).sfLines(),
                textAlign = TextAlign.Center,
            )
            localizer.giftDetail(parcel, opened, now())?.let {
                Text(it, color = tone, style = type.style(IosTextStyle.Callout, FontWeight.Medium).sfLines(), textAlign = TextAlign.Center)
            }
            if (deliveryLine != null) Text(deliveryLine, color = colors.secondaryLabel, style = type.caption.sfLines(), textAlign = TextAlign.Center)
        }
        if (!opened) JourneyRail(parcel.currentStage, tone.copy(alpha = tone.alpha * 0.55f), Modifier.padding(top = 18.dp), compact = true)
    }
}

/**
 * `UnwrappingParcel(open:)` `width` wide, in Pip's frame: closed, or opening once shown, its open box coming over the
 * closed one on `.spring(duration: 0.9, bounce: 0.2)` a quarter of a second after it appears (B-AN20), growing into
 * place with the spring's give; open at once under Reduce Motion. TalkBack doesn't read it.
 */
@Composable
private fun OpeningParcel(width: Dp, label: PipLabel?, ribbon: Boolean, opens: Boolean, modifier: Modifier) {
    val reduceMotion = PeekTheme.reduceMotion
    val open = remember { Animatable(0f) }
    LaunchedEffect(opens, reduceMotion) {
        if (!opens) {
            open.snapTo(0f)
        } else if (reduceMotion) {
            open.snapTo(1f)
        } else {
            open.animateTo(1f, Motion.spring<Float>(duration = 0.9, bounce = 0.2).delay(0.25))
        }
    }
    val frameWidth = PipGeometry.frame.width.toFloat()
    val frameHeight = PipGeometry.frame.height.toFloat()
    val scale = width.value / frameWidth
    Box(
        modifier
            .size(width, (frameHeight * scale).dp)
            .testTag(LINK_BOX)
            .clearAndSetSemantics {}
            .layout { measurable, _ ->
                val frame = measurable.measure(Constraints.fixed(frameWidth.dp.roundToPx(), frameHeight.dp.roundToPx()))
                layout(width.roundToPx(), (frameHeight * scale).dp.roundToPx()) {
                    frame.placeWithLayer(0, 0) {
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
                }
            },
    ) {
        val progress = open.value
        if (progress < 1f) Box(Modifier.graphicsLayer { alpha = 1f - (progress / 0.3f).coerceIn(0f, 1f) }) { UnwrappingParcel(false, label, ribbon) }
        if (progress > 0f) {
            Box(
                Modifier.graphicsLayer {
                    alpha = (progress / 0.3f).coerceIn(0f, 1f)
                    val grow = 0.94f + 0.06f * progress
                    scaleX = grow
                    scaleY = grow
                    transformOrigin = TransformOrigin(0.5f, 0.9f)
                },
            ) { UnwrappingParcel(true, label, ribbon) }
        }
    }
}

/** GiftSurprise (B-S45): under a gift on its way, why the sheet says so little, in footnote secondary, 6 in. */
@Composable
private fun GiftSurprise() {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Row(
        Modifier.fillMaxWidth().padding(horizontal = 6.dp).testTag(LINK_SURPRISE),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PeekSymbol("gift", font = type.footnote, tint = colors.secondaryLabel)
        Text(localized("share.gift.surprise"), Modifier.weight(1f), color = colors.secondaryLabel, style = type.footnote.sfLines())
    }
}

/**
 * GiftNote (B-S45, B-AN32, B-AX05): what a gift's link carried all along and shows once it is delivered, none of it
 * ever sent to the service: the note in serif title3 semibold italic, 4 between its lines; "— " and who it is from in
 * serif body semibold italic; then, under a dashed fold, what is inside. On note paper 20 in, its edge drawn, turned
 * -0.8°, and read as one.
 */
@Composable
private fun GiftNote(note: String?, from: String?, inside: String?) {
    if (note == null && from == null && inside == null) return
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val ink = colors.resolve(GiftNotePalette.ink)
    val fold = colors.resolve(GiftNotePalette.fold)
    val shape = ContinuousRoundedShape(20.dp)
    Column(
        Modifier
            .fillMaxWidth()
            .testTag(LINK_GIFT_NOTE)
            .rotate(-0.8f)
            .background(colors.resolve(GiftNotePalette.paper), shape)
            .border(1.dp, colors.resolve(GiftNotePalette.edge), shape)
            .padding(20.dp)
            .semantics(mergeDescendants = true) {},
    ) {
        if (note != null) {
            val style = type.style(IosTextStyle.Title3, FontWeight.SemiBold, FontDesign.Serif).copy(fontStyle = FontStyle.Italic)
            Text(note, color = ink, style = style.sfLines().copy(lineHeight = (style.fontSize.value * SF_LINE + 4f).sp))
        }
        if (from != null) {
            Text(
                "— $from",
                Modifier.padding(top = if (note == null) 0.dp else 10.dp),
                color = colors.resolve(GiftNotePalette.signature),
                style = type.style(IosTextStyle.Body, FontWeight.SemiBold, FontDesign.Serif).copy(fontStyle = FontStyle.Italic).sfLines(),
            )
        }
        if (inside != null) {
            if (note != null || from != null) {
                Canvas(Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 12.dp).height(1.dp)) {
                    val y = size.height / 2
                    drawLine(
                        fold, Offset(0f, y), Offset(size.width, y), strokeWidth = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(4.dp.toPx(), 3.dp.toPx())),
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PeekSymbol("gift", font = type.footnote, tint = colors.secondaryLabel)
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = colors.secondaryLabel)) { append(LocalLocalizer.current.text("share.gift.inside") + " ") }
                        withStyle(SpanStyle(color = colors.label, fontWeight = FontWeight.SemiBold)) { append(inside) }
                    },
                    style = type.footnote.sfLines(),
                )
            }
        }
    }
}

/** GiftNote's paper, its edge, its fold, its ink and its signature, light and dark. */
private object GiftNotePalette {
    val paper = Brand.color(light = "#FFFDF7", dark = "#2C2A24")
    val edge = Brand.color(light = "#EBE5D6", dark = "#45413A")
    val fold = Brand.color(light = "#D8CFB8", dark = "#55503F")
    val ink = Brand.color(light = "#3B3A33", dark = "#ECE7DA")
    val signature = Brand.color(light = "#6B675A", dark = "#B9B3A4")
}

/**
 * SharedImportCharges (B-S46): "detail.importCharges" in caption2 secondary over the charges in footnote, and, where
 * they are paid online and the link hides the number, that the carrier will ask for it; read as one.
 */
@Composable
private fun SharedImportCharges(charges: ImportChargesLine, asksForNumber: Boolean) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(Modifier.fillMaxWidth().semantics(mergeDescendants = true) {}, verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Text(localized("detail.importCharges"), color = colors.secondaryLabel, style = type.caption2.sfLines())
        Text(charges.text, color = colors.label, style = type.footnote.sfLines())
        if (charges.payableOnline && asksForNumber) {
            Text(
                localized("detail.importChargesAskNumber"), Modifier.padding(top = 4.dp), color = colors.secondaryLabel,
                style = type.caption.sfLines(),
            )
        }
    }
}

/**
 * SharedParcelNumber (B-S47, B-G04, B-H03): the number as the link shows it. Hidden, "detail.trackingNumber" over the
 * masked number, read as one, and the carrier's search page, which takes a number typed in. Whole, each number, which
 * a long press selects, with its copy and its carriers' pages ([ShipmentNumbers]); a copy puts the number on the
 * clipboard as its carrier's site takes it with the success tap, and says so on its row for two seconds.
 */
@Composable
private fun SharedParcelNumber(source: ParcelLinkSource, parcel: Parcel, hint: ParcelNumberHint?, tint: Color) {
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current ?: return
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val onLink = { _: ParcelTrackingLink -> source.action("parcel-carrier-link") }
    if (hint != null) {
        // A hidden number cannot be copied; the carrier's page takes one typed in.
        Column(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(horizontal = 2.dp).semantics(mergeDescendants = true) {}, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(localized("detail.trackingNumber"), color = colors.secondaryLabel, style = type.caption2.sfLines())
                Text(hint.masked, color = colors.label, style = type.style(IosTextStyle.Caption, design = FontDesign.Monospaced).sfLines())
            }
            CarrierLinks(listOfNotNull(identities.catalog.searchLink(parcel, language)), own = true, tint, onLink)
        }
        return
    }
    if (parcel.trackingNumber.isEmpty()) return
    val context = LocalContext.current
    val haptics = rememberHaptics()
    val scope = rememberCoroutineScope()
    var copied by remember { mutableStateOf<String?>(null) }
    ShipmentNumbers(
        parcel, identities.catalog.trackingLinks(parcel, language), tint, copied,
        onCopy = { entry: ParcelTrackingNumber ->
            source.action("parcel-copy-tracking")
            val text = TrackingNumberCopy.text(entry.number, entry.carrier)
            context.getSystemService(ClipboardManager::class.java)?.setPrimaryClip(ClipData.newPlainText(text, text))
            haptics.success()
            copied = entry.number
            scope.launch {
                delay(COPIED_MILLIS)
                if (copied == entry.number) copied = null
            }
        },
        onLink = onLink,
    )
}

private val CARD_RADIUS = 24.dp

/** SF Pro's line height for its size ([sfLines]). */
private const val SF_LINE = (1950f + 494f) / 2048f

/** How long a number's row says it was copied. */
private const val COPIED_MILLIS = 2_000L

/** The test tags of the link's sheet and its parts. */
const val LINK_SHEET = "parcelLink.sheet"
const val LINK_CLOSE = "parcelLink.close"
const val LINK_LOADING = "parcelLink.loading"
const val LINK_SCROLL = "parcelLink.scroll"
const val LINK_CARD = "parcelLink.card"
const val LINK_GIFT_CARD = "parcelLink.giftCard"
const val LINK_ROUTE = "parcelLink.route"
const val LINK_MAP = "parcelLink.map"
const val LINK_BOX = "parcelLink.box"
const val LINK_PIP = "parcelLink.pip"
const val LINK_REFRESH = "parcelLink.refresh"
const val LINK_NOTE = "parcelLink.note"
const val LINK_ADD = "parcelLink.add"
const val LINK_FOLLOW = "parcelLink.follow"
const val LINK_SIGN_IN = "parcelLink.signIn"
const val LINK_ALREADY = "parcelLink.already"
const val LINK_OPEN_EXISTING = "parcelLink.openExisting"
const val LINK_NOTHING = "parcelLink.nothing"
const val LINK_NOTHING_CLOSE = "parcelLink.nothingClose"
const val LINK_FAILED = "parcelLink.failed"
const val LINK_TRY_AGAIN = "parcelLink.tryAgain"
const val LINK_SURPRISE = "parcelLink.surprise"
const val LINK_GIFT_NOTE = "parcelLink.giftNote"
