package com.plhery.peekdeliverytracker.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkStore
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.platform.CameraLabelScanner
import com.plhery.peekdeliverytracker.ui.arrival.ArrivalView
import com.plhery.peekdeliverytracker.ui.arrival.InvitationArrival
import com.plhery.peekdeliverytracker.ui.arrival.LocalArrivalMotion
import com.plhery.peekdeliverytracker.ui.deliveries.ContainerDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ContainerParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesSource
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesTab
import com.plhery.peekdeliverytracker.ui.deliveries.FirstOpenHost
import com.plhery.peekdeliverytracker.ui.deliveries.LabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.LocalFirstOpen
import com.plhery.peekdeliverytracker.ui.deliveries.LocalLabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.LocalParcelLinkOpen
import com.plhery.peekdeliverytracker.ui.deliveries.LocalWindowArrangement
import com.plhery.peekdeliverytracker.ui.deliveries.NoLabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelLinkSheetHost
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelLinkSource
import com.plhery.peekdeliverytracker.ui.deliveries.WindowArrangement
import com.plhery.peekdeliverytracker.ui.deliveries.covers
import com.plhery.peekdeliverytracker.ui.deliveries.rememberFirstOpen
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.PeekMark
import com.plhery.peekdeliverytracker.ui.friends.FriendLinkEffects
import com.plhery.peekdeliverytracker.ui.friends.FriendNoticeHost
import com.plhery.peekdeliverytracker.ui.friends.FriendsActivityEffects
import com.plhery.peekdeliverytracker.ui.friends.FriendsDemoEffects
import com.plhery.peekdeliverytracker.ui.friends.FriendsTab
import com.plhery.peekdeliverytracker.ui.passport.PassportTab
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowLayers
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowOverlay
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.tabs.TabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.TabShell
import com.plhery.peekdeliverytracker.ui.tabs.WindowLayers
import com.plhery.peekdeliverytracker.ui.theme.KeepMotion
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * RootView (A-D20): what the session's state shows, from the container's stores, with the device's camera for the label
 * scanner. A parcel a link to this window asked for ([inbox]) brings the deliveries forward and opens there, and a
 * parcel's link opens its sheet over all of it.
 */
@Composable
fun PeekRoot(container: AppContainer, inbox: DeliveriesInbox? = null) {
    val state by container.session.stateFlow.collectAsState()
    val source = remember(container) { ContainerDeliveries(container) }
    val links = remember(container) { ContainerParcelLinks(container) }
    CompositionLocalProvider(LocalArrivalMotion provides container.motion) {
        PeekRoot(
            state, container.localizer, container.session, onView = { container.analytics.view(it) }, inbox = inbox, browserCheck = container.turnstile,
            scanner = CameraLabelScanner, links = links, source = source, work = container.scope,
        ) {
            DeliveriesTab(source, inbox = inbox)
        }
    }
}

/**
 * RootView's routing, in its order: the launch screen while the session is read, the arrival while nobody is signed in
 * and this phone follows nothing on its own ([session]'s sign-in), the tabs otherwise. A state replaces the one before
 * it at once, but for a change made with an animation ([RootTransaction]): the arrival's back fades the arrival out
 * under the tabs fading in. The tab
 * chosen is kept with the window and through process death, as `@SceneStorage("tab")` keeps it, whatever the state
 * shows in between. A parcel waiting in [inbox] chooses the deliveries. The tabs show [browserCheck]'s sheet over
 * whatever is in front while it asks for a token, and scan labels with [scanner]. The sign-in's sends and checks run
 * in [work], the app's scope, which outlives the screen as a Swift `Task` outlives its view.
 *
 * A parcel's link ([links]) comes first (A-L02): its sheet shows over whatever state is up, the launch screen, the
 * arrival or the tabs and all they present, in the window's front layer, where nothing under it reaches TalkBack.
 * What the tabs present after it, an alert or the browser check, comes over it there, in the order they came, so the
 * back closes what is on top. Once it closes on a parcel to open, or on one just added or followed, the deliveries
 * come forward and take it ([inbox]).
 * A link kept for after sign-in opens again once someone is signed in (B-L03). A friend's link in [inbox] reveals them
 * on the friends tab while someone is signed in. A friend's invitation ([InvitationArrival]) stands in place of all the
 * state shows while it is presented, once the session is read, without the notice of an invitation accepted, and
 * chooses the friends tab once it is accepted.
 *
 * With the deliveries' [source], the first open stands over the tabs and their bar, whichever is chosen, for a guest
 * with nothing followed (TabsView's `showsFirstOpen`), from the first frame: the tab chosen stays as it was until the
 * first parcel it asks for chooses the deliveries, where Add opens.
 */
@Composable
internal fun PeekRoot(
    state: AuthenticationState,
    localizer: Localizer,
    session: SessionStore,
    onView: (String) -> Unit,
    inbox: DeliveriesInbox? = null,
    browserCheck: TurnstileSheet? = null,
    scanner: LabelScanner = NoLabelScanner,
    links: ParcelLinkSource? = null,
    source: DeliveriesSource? = null,
    work: CoroutineScope = rememberCoroutineScope(),
    deliveries: @Composable () -> Unit,
) {
    val language by localizer.languageFlow.collectAsState()
    var tab by rememberSaveable { mutableIntStateOf(PeekTab.Deliveries.ordinal) }
    LaunchedEffect(inbox) {
        inbox?.parcel?.filterNotNull()?.collect { tab = PeekTab.Deliveries.ordinal }
    }
    // `.onChange(of: session.user?.id, initial: true)`: a link opened before signing in comes back once someone is.
    LaunchedEffect(links) {
        val source = links ?: return@LaunchedEffect
        source.session.map { (it as? AuthenticationState.SignedIn)?.user?.id }.distinctUntilChanged().collect { user ->
            if (user != null) source.links.resume()
        }
    }
    // `invitation.isPresenting`: a friend's invitation stands over everything but the launch screen, made again for
    // each invitation (`.id(invitation.presentationID)`); finished, it chooses the friends tab.
    val invitation = source?.invitation
    val presenting = invitation?.presentingFlow?.collectAsState()?.value == true
    val presentation = invitation?.presentationIdFlow?.collectAsState()?.value
    LaunchedEffect(invitation) {
        invitation?.completedFlow?.drop(1)?.collect { tab = PeekTab.Friends.ordinal }
    }
    if (source != null) {
        FriendsActivityEffects(source, state, inbox) { tab = PeekTab.Friends.ordinal }
        FriendLinkEffects(source, inbox)
        FriendsDemoEffects(source, state)
    }
    // `.onChange(of: links.arrival)`: the sheet has gone on a parcel the list now shows.
    LaunchedEffect(links) {
        val store = links?.links ?: return@LaunchedEffect
        store.arrivalFlow.filterNotNull().collect { arrival ->
            store.consumeArrival()
            tab = PeekTab.Deliveries.ordinal
            when (arrival) {
                is ParcelLinkStore.Arrival.Open -> inbox?.open(arrival.id)
                is ParcelLinkStore.Arrival.Added -> inbox?.added(arrival.id)
            }
        }
    }
    NotificationLinkEffects(links, inbox)
    // Over everything the state shows, as `FrontmostSheet` presents from the frontmost controller, and under what the
    // tabs present after it: the window's front draws every layer in the order they came.
    val window = rememberSaveable(saver = WindowLayers.Saver) { WindowLayers() }
    val front = remember(window) { TabOverlay(window = window, name = "window") }
    val transaction = remember { RootTransaction() }
    ProvideLocalizer(localizer) {
        // The tags carry the iPhone's accessibility identifiers (demo.exit, deliveries.search, ...); UI Automator reads
        // them as resource ids, as XCUITest reads the identifiers. Sized and placed as its one child is.
        Box(Modifier.semantics { testTagsAsResourceId = true }, propagateMinConstraints = true) {
            ProvideLinks {
                CompositionLocalProvider(LocalWindowLayers provides window) {
                    Box(if (front.covered) Modifier.clearAndSetSemantics {} else Modifier, propagateMinConstraints = true) {
                        if (source != null && presenting && state != AuthenticationState.Loading) {
                            key(presentation) { InvitationArrival(source, state, session, work) }
                        } else RootSwitch(state, transaction) { screen, shownState ->
                            when (screen) {
                                RootScreen.Launch -> LaunchView(remember(language) { localizer.text("auth.loading") })
                                RootScreen.Arrival -> ArrivalView(shownState, session, transaction, work)
                                RootScreen.Tabs -> {
                                    val labels = remember(language) { PeekTab.entries.map { localizer.text(it.copyKey) } }
                                    val linkOpen = links?.links?.routeFlow?.collectAsState()?.value != null
                                    val firstOpen = source?.let { rememberFirstOpen(it.launch) }
                                    val arrangement = source?.let { rememberSaveable(it.launch, saver = WindowArrangement.saver(it.launch)) { WindowArrangement() } }
                                    firstOpen?.chooseDeliveries = { tab = PeekTab.Deliveries.ordinal }
                                    val covered = if (source != null && firstOpen != null) firstOpen.covers(source, shownState) else false
                                    CompositionLocalProvider(
                                        LocalLabelScanner provides scanner, LocalParcelLinkOpen provides linkOpen, LocalFirstOpen provides firstOpen,
                                        LocalWindowArrangement provides arrangement,
                                    ) {
                                        TabShell(
                                            selection = tab, onSelect = { tab = it }, labels = labels, onView = onView,
                                            covered = covered,
                                            cover = { if (source != null && firstOpen != null) FirstOpenHost(firstOpen, source, covered) },
                                            overlays = { browserCheck?.let { TurnstileSheetHost(it) } },
                                        ) { shown ->
                                            when (shown) {
                                                PeekTab.Deliveries -> deliveries()
                                                PeekTab.Passport -> if (source != null) PassportTab(source) else TabPlaceholder(shown)
                                                PeekTab.Friends -> if (source != null) FriendsTab(source) else TabPlaceholder(shown)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // The notice of an invitation accepted, over the tabs and under what stands over the window.
                    if (source != null) FriendNoticeHost(source, state, covered = window.covered, invitationShown = presenting)
                    if (links != null) CompositionLocalProvider(LocalTabOverlay provides front) { ParcelLinkSheetHost(links) }
                    // What the link's sheet presents in turn comes over it there.
                    CompositionLocalProvider(LocalWindowOverlay provides front, LocalTabOverlay provides front) { front.Content() }
                    // Each overlay's layers, the link's sheet's and the tabs', over all of it.
                    window.Content()
                }
            }
        }
    }
}

/**
 * RootView's `openLinkNotification()` (A-L10): the notification of a parcel followed through its link
 * ([DeliveriesInbox.notifiedLink]), taken once the session is read. Nobody signed in and the parcel followed on this
 * phone, it opens among this phone's parcels (`showDeviceParcels()`, `openParcel`), counted `notification-open` where
 * the deliveries were there to hear it; otherwise the link's sheet opens, counted `notification-open`.
 */
@Composable
internal fun NotificationLinkEffects(links: ParcelLinkSource?, inbox: DeliveriesInbox?) {
    LaunchedEffect(links, inbox) {
        if (links == null || inbox == null) return@LaunchedEffect
        combine(inbox.notifiedLink, links.session) { link, state -> link to state }.collect { (link, state) ->
            if (link == null || state == AuthenticationState.Loading) return@collect
            inbox.consumeNotifiedLink(link)
            val parcelId = if (state is AuthenticationState.SignedIn) null else links.followedHere(link)
            if (parcelId != null) {
                if (state == AuthenticationState.Welcome || state == AuthenticationState.Demo) links.action("notification-open")
                links.showDeviceParcels()
                inbox.open(parcelId)
            } else {
                links.action("notification-open")
                links.links.open(ParcelLinkRoute(link))
            }
        }
    }
}

/**
 * LaunchView (A-ST01): restoring the session takes a frame or two, so the screen stays as the launch screen left it
 * and shows progress only after 600 ms, fading in over 0.2 s, rather than flashing a spinner. The fade is kept under
 * Reduce Motion, as SwiftUI keeps an opacity transition. Progress once shown stays shown when the window is made
 * again (a rotation, a change of theme), as the iPhone's view lives on through those.
 */
@Composable
internal fun LaunchView(loading: String) {
    var slow by rememberSaveable { mutableStateOf(false) }
    val opacity = remember { Animatable(if (slow) 1f else 0f) }
    LaunchedEffect(Unit) {
        if (slow) return@LaunchedEffect
        delay(SLOW_MILLIS)
        slow = true
        withContext(KeepMotion) { opacity.animateTo(1f, Motion.easeOut(0.2)) }
    }
    val colors = PeekTheme.colors
    Box(Modifier.fillMaxSize().background(colors.background).testTag(LAUNCH), contentAlignment = Alignment.Center) {
        if (slow) {
            Column(
                Modifier.safeDrawingPadding().graphicsLayer { alpha = opacity.value },
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PeekMark(78.dp)
                ActivityIndicator()
                BasicText(loading, style = PeekTheme.type.subheadline.copy(color = colors.secondaryLabel))
            }
        }
    }
}

/** The passport and the friends without the deliveries' source: the page's background. */
@Composable
private fun TabPlaceholder(tab: PeekTab) {
    Box(Modifier.fillMaxSize().background(PeekTheme.colors.background).testTag(tab.screen))
}

private const val SLOW_MILLIS = 600L

/** The test tags of the launch screen and the arrival. */
const val LAUNCH = "launch"
const val ARRIVAL = "arrival"
