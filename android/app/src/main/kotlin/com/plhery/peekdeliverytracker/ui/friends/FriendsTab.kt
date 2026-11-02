package com.plhery.peekdeliverytracker.ui.friends

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.currentStateAsState
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.PullToRefreshModel
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendUpdate
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsSnapshot
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.deliveries.AccountBar
import com.plhery.peekdeliverytracker.ui.deliveries.Announcement
import com.plhery.peekdeliverytracker.ui.deliveries.Announcements
import com.plhery.peekdeliverytracker.ui.deliveries.BAR_HEIGHT
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesSource
import com.plhery.peekdeliverytracker.ui.deliveries.DemoModeBar
import com.plhery.peekdeliverytracker.ui.deliveries.ListPull
import com.plhery.peekdeliverytracker.ui.deliveries.LocalWindowArrangement
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.PullEffects
import com.plhery.peekdeliverytracker.ui.deliveries.SoftTopEdge
import com.plhery.peekdeliverytracker.ui.deliveries.TabRowAccount
import com.plhery.peekdeliverytracker.ui.deliveries.barRowTop
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.deliveries.plainDisabled
import com.plhery.peekdeliverytracker.ui.deliveries.rememberForLaunch
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.settings.AccountHost
import com.plhery.peekdeliverytracker.ui.settings.AccountSheetHost
import com.plhery.peekdeliverytracker.ui.settings.DELIVERY_UPDATES_PAGE
import com.plhery.peekdeliverytracker.ui.settings.FORM_BAR_SIDE
import com.plhery.peekdeliverytracker.ui.settings.FORM_BAR_TOP
import com.plhery.peekdeliverytracker.ui.settings.FORM_TITLE_SIDE
import com.plhery.peekdeliverytracker.ui.settings.NAV_BAR_DROP
import com.plhery.peekdeliverytracker.ui.settings.NAV_BAR_FOOT
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_PAGE
import com.plhery.peekdeliverytracker.ui.sheet.BAR_ROW
import com.plhery.peekdeliverytracker.ui.sheet.BarButton
import com.plhery.peekdeliverytracker.ui.sheet.FormBar
import com.plhery.peekdeliverytracker.ui.sheet.FormScrollEdge
import com.plhery.peekdeliverytracker.ui.sheet.KeyboardScrollColumn
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetInsets
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.sheetBottomInset
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabBarPadding
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowHinge
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.tabs.tabContentPadding
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.ProvideDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import com.plhery.peekdeliverytracker.ui.zoom.ZoomStack
import com.plhery.peekdeliverytracker.ui.zoom.ZoomTransition
import com.plhery.peekdeliverytracker.ui.zoom.recedingUnderZoom
import com.plhery.peekdeliverytracker.ui.zoom.zoomSource
import java.text.NumberFormat
import java.util.UUID
import kotlin.math.roundToInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

// FriendsView.swift's `FriendsView` (C-S19 to C-S28) and FirstOpenView.swift's `FriendsSignedOutView` (A-S20): the
// friends tab, its bar and Settings, the circle and its panels; without an account, what the tab is for.

/** `FriendsPanel`: the sheet over the circle, its title and its detents on a phone. */
internal enum class FriendsPanel(val titleKey: String) {
    Profile("friends.settings"),
    Create("friends.join"),
    Invite("friends.inviteTitle"),
    ;

    /** `.height(590)` and `.large` for the invitation, `.large` for the profile. */
    val detents: List<SheetDetent> get() = if (this == Invite) listOf(SheetDetent.Height(590.dp), SheetDetent.Large) else listOf(SheetDetent.Large)
}

/**
 * What FriendsView keeps in `@State`: Settings open, the panel open, the friend whose page is open, the presentation
 * the circle was last loaded for (`checkedPresentation`). Kept with the window and while another tab shows, not past
 * the launch; the tab's own disappearance closes the panel and the page, as `onDisappear` does.
 */
@Stable
internal class FriendsState(
    override val source: DeliveriesSource,
    private val showing: MutableState<Boolean>,
    private val forms: MutableState<Boolean>,
    private val shownPanel: MutableState<FriendsPanel?>,
    private val panelForm: MutableState<Boolean>,
    private val friend: MutableState<UUID?>,
    private val invitationValue: MutableState<InvitationValue>,
    landedValue: MutableState<Boolean>,
    presentedValue: MutableState<UUID?>,
) : AccountHost {
    override val showingAccount: Boolean get() = showing.value
    override val sheetsAsForms: Boolean get() = forms.value

    val panel: FriendsPanel? get() = shownPanel.value

    /** The panel shows as the iPad's form, as the tab settled it when it opened. */
    val panelAsForm: Boolean get() = panelForm.value

    val openFriend: UUID? get() = friend.value

    /** Each panel opened afresh, as `.sheet(item:)` makes its view anew. */
    var openings by mutableIntStateOf(0)
        private set

    /** The invitation the panel open made, kept as its `@State` is while it shows, and with the window. */
    val invitation = InvitationState(invitationValue)

    var checkedPresentation: UUID? by mutableStateOf(null)

    /** `friendTransition`: a friend's page opens out of their card and goes back into it, as a parcel's does. */
    val zoom = ZoomTransition()

    /** `arrivingID`: the friend a reveal lands, first in the circle while the tab shows. */
    var arriving: UUID? by mutableStateOf(null)

    /** `cardLanded`: whether the friend revealed has landed, kept with the window as the iPhone's `@State` is. */
    var landed: Boolean by landedValue

    /** `presented`: the presentation whose friend has landed, kept with the window. */
    var presented: UUID? by presentedValue

    /** How far the friend revealed has landed, from 0 (out of sight) to 1 ([landed]). */
    val landing = Animatable(if (landedValue.value) 1f else 0f)

    /** Where the circle's scrolled content and its cards stand, for the reveal to scroll to a card. */
    val places = CardPlaces()

    /**
     * The invitation's link handed to the share sheet: the app the reader picks there stops the window, which keeps the
     * panel, its link and the circle through that stop, as the iPhone's share sheet stands over a scene that stays
     * active. Forgotten as the window is next resumed, whether the reader shared or left the sheet.
     */
    var sharing = false

    var announcement: Announcement? by mutableStateOf(null)
        private set

    /** `showingAccount = true`, from the account button: Settings afresh, a form [asForm]. */
    fun account(asForm: Boolean) {
        if (showingAccount) return
        source.pageWork.forget(SETTINGS_PAGE)
        source.pageWork.forget(DELIVERY_UPDATES_PAGE)
        forms.value = asForm
        showing.value = true
    }

    override fun closeAccount() {
        showing.value = false
    }

    /** `panel = value`: the panel afresh, a form [asForm] and the page sheet or the phone's sheet otherwise. */
    fun open(value: FriendsPanel, asForm: Boolean) {
        if (shownPanel.value != null) return
        openings++
        invitationValue.value = InvitationValue()
        panelForm.value = asForm
        shownPanel.value = value
    }

    /** `panel = nil`. */
    fun closePanel() {
        shownPanel.value = null
    }

    /** A friend's card tapped: counted (`friend-open`), and their page opens. */
    fun openFriend(id: UUID) {
        source.action("friend-open")
        friend.value = id
    }

    fun closeFriend() {
        friend.value = null
    }

    /** `panel = nil; openFriend = nil`, as the tab goes or the app leaves the foreground. */
    fun hide() {
        shownPanel.value = null
        friend.value = null
    }

    override fun announce(text: String) {
        announcement = Announcement(text, (announcement?.count ?: 0) + 1)
    }

    fun announced(announcement: Announcement) {
        if (this.announcement == announcement) this.announcement = announcement.copy(text = "")
    }
}

@Composable
internal fun rememberFriendsState(source: DeliveriesSource): FriendsState {
    val launch = source.launch
    val showing = rememberForLaunch(launch, false)
    val forms = rememberForLaunch(launch, false)
    val panel = rememberForLaunch<FriendsPanel?>(launch, null)
    val panelForm = rememberForLaunch(launch, false)
    val friend = rememberForLaunch<UUID?>(launch, null)
    val invitation = rememberForLaunch(launch, InvitationValue())
    val landed = rememberForLaunch(launch, false)
    val presented = rememberForLaunch<UUID?>(launch, null)
    return remember(source) { FriendsState(source, showing, forms, panel, panelForm, friend, invitation, landed, presented) }
}

/** The circle's scrolled content and its cards as last placed. */
internal class CardPlaces {
    var content: LayoutCoordinates? = null
    val cards = HashMap<UUID, LayoutCoordinates>()
}

/**
 * The friends tab (ParcelListView's `session.isGuest ? FriendsSignedOutView() : FriendsView()`): a scroll without
 * indicators on the page's ground, at most 680 wide in the middle, under the bar with the inline title "friends.title"
 * and the account button ([AccountBar]) and, in the demo, the demo's bar; Settings over it from the account button, a
 * form where the deliveries would stand side by side. Where the tab bar stands at the top, as the iPad's, there is no
 * title and the account button stands in the tab bar's row ([TabRowAccount]), as the passport's.
 */
@Composable
internal fun FriendsTab(source: DeliveriesSource) {
    val state = rememberFriendsState(source)
    val session by source.session.collectAsState()
    val guest = session == AuthenticationState.Welcome
    val demo = session == AuthenticationState.Demo
    val colors = PeekTheme.colors
    val padding = tabContentPadding()
    val direction = LocalLayoutDirection.current
    val start = padding.calculateStartPadding(direction)
    val end = padding.calculateEndPadding(direction)
    val tabBarAtTop = LocalTabBarPadding.current.calculateTopPadding() > 0.dp
    val rowTop = barRowTop()
    val barBottom = if (tabBarAtTop) rowTop else rowTop + BAR_HEIGHT
    val bottom = padding.calculateBottomPadding()
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val hinge = LocalWindowHinge.current
    val backdrop = rememberMaterialBackdrop()
    val windowArrangement = LocalWindowArrangement.current
    val working by source.friends.workingFlow.collectAsState()
    BoxWithConstraints(Modifier.fillMaxSize().background(colors.background).testTag(PeekTab.Friends.screen)) {
        val regularWidth = maxWidth >= REGULAR_WIDTH
        val regularHeight = maxHeight >= REGULAR_HEIGHT
        // The passport's rule: a form where the deliveries would stand side by side in this window, else the page.
        val deliveries = if (hinge != null && regularWidth && regularHeight) {
            DeliveriesArrangement.SideBySide
        } else {
            DeliveriesArrangement.resolve(
                maxWidth.value.toDouble(), regularWidth, regularHeight, largeText, windowArrangement?.last ?: DeliveriesArrangement.Stacked,
            )
        }
        val asForm = deliveries == DeliveriesArrangement.SideBySide
        val zoom = state.zoom
        val shown = if (guest) null else state.openFriend
        // `top ?: covered`: the page going back into its card stays drawn until it lands.
        val pageId = shown ?: zoom.covered as? UUID
        ZoomStack(
            transition = zoom,
            top = shown,
            onBack = state::closeFriend,
            root = {
                Box(Modifier.fillMaxSize()) {
                    FriendsChrome(source, demo, barBottom, start, end, Modifier.fillMaxSize().materialBackdrop(backdrop)) { top ->
                        if (guest) {
                            FriendsSignedOut(source, top, bottom, start, end)
                        } else {
                            FriendsCircleView(source, state, top, bottom, start, end, asForm)
                        }
                    }
                    if (tabBarAtTop) {
                        TabRowAccount(end, backdrop, source.session, onAccount = { state.account(asForm) }, accountTag = FRIENDS_ACCOUNT_BUTTON, zoom = zoom)
                    } else {
                        AccountBar(
                            localized("friends.title"), rowTop, start, end, backdrop, source.session,
                            onAccount = { state.account(asForm) }, accountTag = FRIENDS_ACCOUNT_BUTTON, zoom = zoom,
                        )
                    }
                }
            },
            page = if (pageId != null) { { FriendPage(state, pageId) } } else null,
            bar = { FriendPageBar(state, roomy = tabBarAtTop) },
            backEnabled = !working,
        )
        Announcements(state.announcement, FRIENDS_ANNOUNCEMENT, state::announced)
    }
    if (!guest) FriendsPanelHost(state)
    AccountSheetHost(state)
}

private enum class Slot { DemoBar, Content, Band }

/**
 * The tab's chrome, the circle's and a friend's page's (`.safeAreaInset(edge: .top) { DemoModeBar() }` under the inline
 * bar): what [content] shows from the window's top, given where it starts clear of the bar's foot [barBottom] and, in
 * the demo, of the demo's bar under it; behind the bar, the cream in the demo, else the soft edge the scroll fades under.
 */
@Composable
internal fun FriendsChrome(
    source: DeliveriesSource,
    demo: Boolean,
    barBottom: Dp,
    start: Dp,
    end: Dp,
    modifier: Modifier,
    content: @Composable (top: Dp) -> Unit,
) {
    val colors = PeekTheme.colors
    SubcomposeLayout(modifier) { constraints ->
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        val bar = subcompose(Slot.DemoBar) {
            if (demo) DemoModeBar(onExit = source::showWelcome, sides = PaddingValues(start = start, end = end))
        }.map { it.measure(loose) }
        val chromeBottom = barBottom + (bar.maxOfOrNull { it.height } ?: 0).toDp()
        val shown = subcompose(Slot.Content) { content(chromeBottom) }.map { it.measure(constraints) }
        val band = subcompose(Slot.Band) {
            if (demo) Box(Modifier.fillMaxWidth().height(barBottom).background(colors.cream)) else SoftTopEdge(barBottom)
        }.map { it.measure(loose) }
        layout(constraints.maxWidth, constraints.maxHeight) {
            shown.forEach { it.place(0, 0) }
            band.forEach { it.place(0, 0) }
            bar.forEach { it.place(0, barBottom.roundToPx()) }
        }
    }
}

/**
 * `FriendsSignedOutView` (A-S20): the postage pair (12 over the rest), "friends.title" in semibold 26 (growing with the
 * text at the accessibility sizes, as `@ScaledMetric(relativeTo: .title)`), the summary in subheadline in the secondary
 * grey, centred, and Sign in, which opens the sign-in (`session.showSignIn()`); 22 apart, in by 70 and 20.
 */
@Composable
private fun FriendsSignedOut(source: DeliveriesSource, top: Dp, bottom: Dp, start: Dp, end: Dp) {
    Box(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).testTag(FRIENDS_SCROLL).padding(start = start, top = top, end = end, bottom = bottom),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            Modifier.widthIn(max = MAX_WIDTH).fillMaxWidth().padding(vertical = 70.dp, horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Intro(localized("arrival.signInTitle"), FRIENDS_SIGN_IN, source::showSignIn)
        }
    }
}

/** The intro the signed-out tab and a circle without a profile share: the pair, the title, the summary and [action]. */
@Composable
private fun Intro(title: String, tag: String, action: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val size = if (PeekTheme.dynamicTypeSize.isAccessibilitySize) type.scaled(26f, IosTextStyle.Title) else 26f
    FriendsPostagePair(Modifier.padding(bottom = 12.dp))
    Text(localized("friends.title"), color = colors.label, style = type.fixed(size, FontWeight.SemiBold).sfLines(), textAlign = TextAlign.Center)
    Text(localized("friends.introSummary"), color = colors.secondaryLabel, style = type.subheadline.sfLines(), textAlign = TextAlign.Center)
    ProminentButton(title, tag = tag, action = action)
}

/**
 * `FriendsView`'s scroll: the error over all but while a panel is open, the line for a revealed friend who is no
 * longer in the circle, the circle ([Circle]) or the intro without a profile, which opens the profile's panel; without
 * a circle, Retry where it failed, else the spinner. In by 20, 18 apart. A pull from its top loads it again, its
 * spinner in the gap the pull opens (`.refreshable`). It loads as it shows, then every 60 s while it shows and the app
 * is in front, and is cleared, its panel and page closed, as it goes or the app leaves the foreground, but for the app
 * the invitation's Share hands its link to. It recedes under a friend's page as the page zooms over it.
 *
 * A friend revealed lands once the circle that shows holds them, once for each presentation (the focus task): first in
 * the circle, out of sight, then 60 ms later their card is scrolled to the middle and 60 ms after that it springs in
 * from 100 to its right (response 0.55 s, damping 0.78), or fades in over 0.15 s easing out under Reduce Motion; the
 * friend is acknowledged as it lands, and the focus let go once that is done.
 */
@Composable
private fun FriendsCircleView(source: DeliveriesSource, state: FriendsState, top: Dp, bottom: Dp, start: Dp, end: Dp, asForm: Boolean) {
    val store = source.friends
    val activity = source.friendsActivity
    val session by source.session.collectAsState()
    val demo = session == AuthenticationState.Demo
    val snapshot by store.snapshotFlow.collectAsState()
    val errorKey by store.errorKeyFlow.collectAsState()
    val focusId by activity.focusIdFlow.collectAsState()
    val presentation by activity.presentationIdFlow.collectAsState()
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val lifecycle = LocalLifecycleOwner.current.lifecycle.currentStateAsState().value
    val active = lifecycle.isAtLeast(Lifecycle.State.STARTED)
    val window = LocalActivity.current
    val reduceMotion = PeekTheme.reduceMotion
    val density = LocalDensity.current
    val scroll = rememberScrollState()
    // `onAppear`: the demo's circle or the account's, and the circle an accepted invitation answered with at once.
    LaunchedEffect(demo) {
        store.configure(demo)
        activity.arrivalSnapshot?.let(store::seed)
    }
    // `onDisappear`, but not as the window is made again (a rotation), which the iPhone's tab lives on through.
    DisposableEffect(store) {
        onDispose {
            if (window?.isChangingConfigurations != true) {
                state.hide()
                state.arriving = null
                store.clear()
            }
        }
    }
    // `.task(id: active ? presentationID : "hidden")`, but for the stop the invitation's Share brings
    // ([FriendsState.sharing]).
    val shown = if (active) presentation else null
    LaunchedEffect(active, shown) {
        if (!active) {
            if (state.sharing) return@LaunchedEffect
            state.hide()
            store.clear()
            return@LaunchedEffect
        }
        while (true) {
            store.load(source.parcels.value)
            // `guard !Task.isCancelled`: a load called off as it answered checks nothing, nor for a newer presentation.
            ensureActive()
            state.checkedPresentation = shown
            delay(RELOAD_MILLIS)
        }
    }
    val resumed = lifecycle.isAtLeast(Lifecycle.State.RESUMED)
    LaunchedEffect(resumed) {
        if (resumed) state.sharing = false
    }
    // `.onChange(of: model.snapshot)`: a friend no longer in the circle closes their page.
    LaunchedEffect(snapshot) {
        val open = state.openFriend ?: return@LaunchedEffect
        if (snapshot?.friends?.any { it.id == open } != true) state.closeFriend()
    }
    // `.refreshable { await model.load(parcels:) }`.
    val pullScope = rememberCoroutineScope()
    val pullModel = remember { PullToRefreshModel(pullScope) }
    val refresh by rememberUpdatedState {
        pullModel.begin("")
        pullScope.launch {
            store.load(source.parcels.value)
            pullModel.settle()
        }
        Unit
    }
    val pull = remember(pullModel) { ListPull(pullModel, pullScope, { refresh() }) }
    val pullPhase by pullModel.phaseFlow.collectAsState()
    PullEffects(pull, pullModel)
    // `.task(id: focusReady ? presentationID : "waiting")`: the friend revealed lands once the circle holds them.
    val focusReady = active && focusId != null && snapshot?.friends?.any { it.id == focusId } == true
    val landingTop by rememberUpdatedState(top)
    val landingBottom by rememberUpdatedState(bottom)
    val scope = rememberCoroutineScope()
    LaunchedEffect(if (focusReady) presentation else null) {
        val friendId = activity.focusId
        if (!focusReady || state.presented == presentation || friendId == null) return@LaunchedEffect
        state.arriving = friendId
        state.landed = false
        state.landing.snapTo(0f)
        activity.consumeArrival()
        if (store.snapshot?.friends?.any { it.id == friendId } != true) return@LaunchedEffect
        delay(LANDING_STEP_MILLIS)
        with(density) { scrollToMiddle(state.places, friendId, scroll, pull.height, landingTop.roundToPx(), landingBottom.roundToPx()) }
        delay(LANDING_STEP_MILLIS)
        state.presented = presentation
        state.landed = true
        // `withAnimation`: the landing goes on once the focus let go below ends this task.
        scope.launch { state.landing.animateTo(1f, if (reduceMotion) Motion.easeOut(0.15) else Motion.springWithResponse(0.55, 0.78)) }
        activity.acknowledge(friendId)
        activity.consumeFocus(friendId)
    }
    val held = with(density) { pull.held.value.toDp() }
    Box(
        Modifier
            .fillMaxSize()
            .recedingUnderZoom(state.zoom)
            .nestedScroll(pull)
            .onSizeChanged { pull.height = it.height },
    ) {
        // The iPhone's refresh control: its spinner in the middle of the gap the pull opens, under the bar.
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = top)
                .graphicsLayer {
                    translationY = pull.shown / 2 - 10.dp.toPx()
                    alpha = when (pullPhase) {
                        PullToRefreshModel.Phase.Refreshing -> 1f
                        PullToRefreshModel.Phase.Settling -> (pull.held.value / ListPull.HOLD.toPx()).coerceIn(0f, 1f)
                        PullToRefreshModel.Phase.Idle -> pullModel.progress.toFloat()
                    }
                },
        ) {
            ActivityIndicator(size = 20.dp, tint = colors.secondaryLabel)
        }
        Box(
            Modifier
                .fillMaxSize()
                .graphicsLayer { translationY = pull.past }
                .verticalScroll(scroll)
                .onPlaced { state.places.content = it }
                .testTag(FRIENDS_SCROLL)
                .padding(start = start, top = top + held, end = end, bottom = bottom),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                Modifier.widthIn(max = MAX_WIDTH).fillMaxWidth().padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                val error = errorKey
                if (error != null && state.panel == null) FriendsErrorBanner(error)
                val data = snapshot
                if (data != null) {
                    val focus = focusId
                    if (focus != null && state.checkedPresentation == presentation && data.friends.none { it.id == focus }) {
                        Text(
                            localized("friends.friendUnavailable"), Modifier.testTag(FRIENDS_UNAVAILABLE_FRIEND),
                            color = colors.secondaryLabel, style = type.footnote.sfLines(),
                        )
                    }
                    if (data.profile != null) {
                        Circle(state, data, focus, asForm)
                    } else {
                        Column(
                            Modifier.fillMaxWidth().padding(vertical = 70.dp),
                            verticalArrangement = Arrangement.spacedBy(22.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Intro(localized("friends.join"), FRIENDS_JOIN) { state.open(FriendsPanel.Create, asForm) }
                        }
                    }
                } else if (error != null) {
                    BorderedButton(localized("common.retry"), FRIENDS_RETRY) { scope.launch { store.load(source.parcels.value) } }
                } else {
                    Box(Modifier.fillMaxWidth().heightIn(min = 260.dp).testTag(FRIENDS_LOADING), contentAlignment = Alignment.Center) {
                        ActivityIndicator(size = 20.dp, tint = colors.secondaryLabel)
                    }
                }
            }
        }
    }
}

/**
 * `.buttonStyle(.bordered)` in the tab's ink: its title in body, in ink on a capsule of ink at 12 %, in by 14 and 7;
 * it fades as it is pressed, and is greyed while off.
 */
@Composable
internal fun BorderedButton(title: String, tag: String, enabled: Boolean = true, action: () -> Unit) {
    val colors = PeekTheme.colors
    Box(
        Modifier
            .testTag(tag)
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = action)
            .plainDisabled(enabled)
            .background(colors.ink.copy(alpha = 0.12f), CapsuleShape)
            .padding(horizontal = 14.dp, vertical = 7.dp),
    ) {
        Text(title, color = colors.ink, style = PeekTheme.type.body.sfLines())
    }
}

/**
 * `circle(_:)`: what the reader shares, which opens the profile's panel (read as "friends.settings"); then, with no
 * friend yet, the postage pair, "friends.emptyTitle" and Invite; else "friends.circle" with its count and Invite
 * beside it (under it, across the column, at the accessibility sizes), and the friends' cards 9 apart, the friend
 * being revealed first. A card opens the friend's page.
 */
@Composable
private fun Circle(state: FriendsState, data: FriendsSnapshot, focus: UUID?, asForm: Boolean) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val profile = data.profile ?: return
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val settings = localized("friends.settings")
    Column(
        Modifier
            .fillMaxWidth()
            .testTag(FRIENDS_SHARING)
            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button) { state.open(FriendsPanel.Profile, asForm) }
            .clearAndSetSemantics {
                contentDescription = settings
                role = Role.Button
                onClick {
                    state.open(FriendsPanel.Profile, asForm)
                    true
                }
            },
    ) {
        Row(Modifier.fillMaxWidth().padding(bottom = 18.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
            FriendAvatar(profile.nickname, Modifier.size(31.dp, 38.dp), ExperimentalPalette.transit, ExperimentalPalette.transitSurface)
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(localized("friends.yourSharing"), color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines())
                val summary = localizer.text(if (profile.shareStats) "friends.shareStats" else "friends.privateStats") +
                    if (profile.shareArrival) " · " + localizer.text("friends.shareArrival") else ""
                Text(summary, color = colors.secondaryLabel, style = type.caption2.sfLines())
            }
            // `Spacer(minLength: 6)`, with the stack's 12 either side of it.
            PeekSymbol("slider.horizontal.3", Modifier.padding(start = 18.dp), font = type.footnote, tint = colors.secondaryLabel)
        }
        FormHairline()
    }
    val invite = { state.open(FriendsPanel.Invite, asForm) }
    if (data.friends.isEmpty()) {
        Column(
            Modifier.fillMaxWidth().padding(vertical = 45.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            FriendsPostagePair()
            Text(localized("friends.emptyTitle"), color = colors.label, style = type.style(IosTextStyle.Title3, FontWeight.SemiBold).sfLines(), textAlign = TextAlign.Center)
            ProminentButton(localized("friends.invite"), symbol = "plus", tag = FRIENDS_INVITE, action = invite)
        }
        return
    }
    val count = remember(data.friends.size, language) { NumberFormat.getIntegerInstance(language.locale).format(data.friends.size) }
    val title = @Composable {
        Row(Modifier.semantics(mergeDescendants = true) {}, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(localized("friends.circle"), Modifier.alignByBaseline(), color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
            Text(count, Modifier.alignByBaseline(), color = colors.secondaryLabel, style = type.caption2.sfLines())
        }
    }
    val pill = @Composable { modifier: Modifier ->
        Box(
            modifier
                .heightIn(min = 36.dp)
                .testTag(FRIENDS_INVITE)
                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = invite)
                .background(colors.accent, ContinuousRoundedShape(11.dp))
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center,
        ) {
            SymbolLabel(localized("friends.invite"), "plus", type.style(IosTextStyle.Caption, FontWeight.Medium), colors.onAccent)
        }
    }
    if (large) {
        Column(Modifier.fillMaxWidth().testTag(FRIENDS_HEADER), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            title()
            pill(Modifier.fillMaxWidth())
        }
    } else {
        Row(Modifier.fillMaxWidth().testTag(FRIENDS_HEADER), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.weight(1f)) { title() }
            Spacer(Modifier.width(8.dp))
            pill(Modifier)
        }
    }
    // The friend landing, or else the one revealed.
    val featuredId = state.arriving ?: focus
    val featured = data.friends.firstOrNull { it.id == featuredId }
    val remaining = data.friends.filter { it.id != featured?.id }
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(9.dp)) {
        featured?.let { key(it.id) { FriendButton(state, it, landing = true) } }
        remaining.forEach { key(it.id) { FriendButton(state, it, landing = false) } }
    }
}

/**
 * `friendButton(_:)`: the friend's card (`TactileButtonStyle(scale: 0.98)`), which opens their page out of it,
 * counted. The friend [landing] stays out of sight until they have landed, 100 to the right of their place but under
 * Reduce Motion.
 */
@Composable
private fun FriendButton(state: FriendsState, friend: FriendCard, landing: Boolean) {
    val reduceMotion = PeekTheme.reduceMotion
    Box(
        Modifier
            .fillMaxWidth()
            .onPlaced { state.places.cards[friend.id] = it }
            .then(
                if (landing) {
                    Modifier.graphicsLayer {
                        val landed = state.landing.value
                        if (!reduceMotion) translationX = (1f - landed) * LANDING_OFFSET.toPx()
                        alpha = landed.coerceIn(0f, 1f)
                    }
                } else {
                    Modifier
                },
            )
            .testTag(friendCardTag(friend.id))
            .zoomSource(friend.id, state.zoom, CARD_RADIUS)
            .clickable(interactionSource = null, indication = PressStyle.tactile(0.98f), role = Role.Button) { state.openFriend(friend.id) },
    ) {
        FriendCardView(friend)
    }
    DisposableEffect(friend.id) {
        onDispose { state.places.cards.remove(friend.id) }
    }
}

/**
 * `scroll.scrollTo(friendID, anchor: .center)`: the scroll moves at once so that the friend's card stands in the middle
 * of what shows of it between [top] and [bottom], as far as it can go.
 */
private suspend fun scrollToMiddle(places: CardPlaces, friendId: UUID, scroll: ScrollState, viewport: Int, top: Int, bottom: Int) {
    val content = places.content?.takeIf { it.isAttached } ?: return
    val card = places.cards[friendId]?.takeIf { it.isAttached } ?: return
    val y = content.localPositionOf(card, Offset.Zero).y + card.size.height / 2f
    val middle = top + (viewport - top - bottom) / 2f
    scroll.scrollTo((y - middle).roundToInt().coerceIn(0, scroll.maxValue))
}

/**
 * `.sheet(item: $panel)`: the panel in its `NavigationStack`, its title inline and Close at its trailing end (which also
 * clears what went wrong), what went wrong over what it holds; at the invitation's 590 and the large detent, or the
 * profile's large one, on a phone (the large one alone at the accessibility sizes), and where the deliveries would
 * stand side by side the iPad's form (`formOnPad`), with its drag indicator. While the store works, neither Close, a drag
 * down, a tap outside nor the back closes it (`interactiveDismissDisabled(working)`).
 */
@Composable
private fun FriendsPanelHost(state: FriendsState) {
    val source = state.source
    val store = source.friends
    val working by store.workingFlow.collectAsState()
    val errorKey by store.errorKeyFlow.collectAsState()
    val snapshot by store.snapshotFlow.collectAsState()
    val session by source.session.collectAsState()
    var drawn by remember { mutableStateOf(state.panel) }
    state.panel?.let { drawn = it }
    val panel = drawn
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val title = panel?.let { localized(it.titleKey) } ?: ""
    Sheet(
        visible = state.panel != null,
        onDismiss = state::closePanel,
        paneTitle = title,
        detents = if (large || panel == null) listOf(SheetDetent.Large) else panel.detents,
        onClosed = { drawn = null },
        dismissible = !working,
        page = !state.panelAsForm,
    ) {
        if (panel == null) return@Sheet
        val insets = LocalSheetInsets.current
        val form = if (!state.panelAsForm) insets else insets.copy(barTop = FORM_BAR_TOP, barSide = FORM_BAR_SIDE, formSide = FORM_TITLE_SIDE)
        CompositionLocalProvider(LocalSheetInsets provides form) {
            PanelPage(title, closeEnabled = !working, close = {
                state.closePanel()
                store.errorKey = null
            }) {
                errorKey?.let { FriendsErrorBanner(it) }
                key(state.openings) {
                    PanelContent(state, panel, snapshot, working, session == AuthenticationState.Demo)
                }
            }
        }
    }
}

/** `sheetContent(_:)`: the profile's form, or the invitation, the demo's or the account's. */
@Composable
private fun PanelContent(state: FriendsState, panel: FriendsPanel, snapshot: FriendsSnapshot?, working: Boolean, demo: Boolean) {
    val source = state.source
    val store = source.friends
    val colors = PeekTheme.colors
    val nickname = snapshot?.profile?.nickname ?: localized("friends.you")
    when (panel) {
        FriendsPanel.Profile, FriendsPanel.Create -> FriendsProfileForm(source, if (panel == FriendsPanel.Create) null else snapshot?.profile, working) { profile ->
            source.scope.launch {
                val request = FriendsActionRequest(
                    action = FriendsAction.SaveProfile, nickname = profile.nickname, shareStats = profile.shareStats, shareArrival = profile.shareArrival,
                )
                if (store.act(request, source.parcels.value) != null) state.closePanel()
            }
        }
        FriendsPanel.Invite -> if (demo) {
            FriendsInvitationParcel(nickname)
            Text(localized("friends.demoInvites"), color = colors.secondaryLabel, style = PeekTheme.type.subheadline.sfLines())
            ProminentButton(localized("welcome.signInInstead"), tag = FRIENDS_DEMO_SIGN_IN) {
                state.closePanel()
                store.errorKey = null
                source.showWelcome()
            }
        } else {
            FriendsInvitationView(
                source, nickname, working, state.invitation,
                act = { store.act(it, source.parcels.value) },
                completed = state::closePanel,
                sharing = { state.sharing = true },
            )
        }
    }
}

/**
 * The panel's `NavigationStack` and `ScrollView`: the page's ground, what it holds in by 24 and 20 apart, 10 under the
 * inline bar, which stands a point lower than the sheet's own bar row; its [title] in the middle as a heading and Close
 * on glass at its trailing end, semibold as the confirmation, and the scroll edge once it has scrolled.
 */
@Composable
private fun PanelPage(title: String, closeEnabled: Boolean, close: () -> Unit, content: @Composable () -> Unit) {
    val backdrop = rememberMaterialBackdrop()
    val insets = LocalSheetInsets.current
    val barTop = insets.barTop + NAV_BAR_DROP
    val barHeight = barTop + BAR_ROW
    val bottom = sheetBottomInset()
    val scroll = rememberScrollState()
    Box(Modifier.fillMaxSize().testTag(FRIENDS_PANEL)) {
        Box(Modifier.fillMaxSize().materialBackdrop(backdrop).background(PeekTheme.colors.background)) {
            KeyboardScrollColumn(
                Modifier.fillMaxSize(), scroll, top = barHeight, bottom = bottom,
                contentModifier = Modifier.padding(top = barHeight + NAV_BAR_FOOT, bottom = bottom).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) { content() }
        }
        FormScrollEdge(barHeight) { scroll.value.toFloat() }
        ProvideDynamicTypeSize(DynamicTypeSize.Large) {
            FormBar(
                title, null, Modifier.fillMaxWidth().padding(top = barTop).height(BAR_ROW),
                confirmation = { BarButton(localized("common.close"), close, backdrop, FontWeight.SemiBold, closeEnabled, FRIENDS_CLOSE) },
                cancellation = null,
            )
        }
    }
}

/**
 * RootView's friends' activity (`friendsActivity`): cleared as the session changes; while someone is signed in and the
 * app is in front, read every 30 s, and hidden as it leaves the foreground. A friend revealed chooses the friends tab
 * ([chooseFriends]), as `selectedTab = 2` does once the presentation changes with a friend to focus.
 */
@Composable
internal fun FriendsActivityEffects(source: DeliveriesSource, session: AuthenticationState, chooseFriends: () -> Unit) {
    val activity = source.friendsActivity
    val identity = when (session) {
        is AuthenticationState.SignedIn -> session.user.id.toString()
        else -> session::class.simpleName ?: ""
    }
    val active = LocalLifecycleOwner.current.lifecycle.currentStateAsState().value.isAtLeast(Lifecycle.State.STARTED)
    val seen = remember { arrayOf(identity) }
    LaunchedEffect(identity, active) {
        if (seen[0] != identity) {
            seen[0] = identity
            activity.clear()
        }
        if (session !is AuthenticationState.SignedIn) {
            activity.clear()
            return@LaunchedEffect
        }
        if (!active) {
            activity.hidePrivateContent()
            return@LaunchedEffect
        }
        while (true) {
            activity.refresh()
            delay(ACTIVITY_MILLIS)
        }
    }
    val choose by rememberUpdatedState(chooseFriends)
    // `.onChange(of: friendsActivity.presentationID)`: a presentation other than the one the window first showed,
    // whichever effect revealed it first.
    val shown = remember(activity) { activity.presentationId }
    LaunchedEffect(activity) {
        activity.presentationIdFlow.collect { if (it != shown && activity.focusId != null) choose() }
    }
}

/**
 * `open(_:)`'s `.friend` route: a friend a link to this window asked for ([DeliveriesInbox.friend]) is revealed while
 * someone is signed in (`session.user != nil`), which chooses the friends tab ([FriendsActivityEffects]); a guest, the
 * demo or the sign-in, as the launch screen while the session is read, take it and do nothing with it.
 */
@Composable
internal fun FriendLinkEffects(source: DeliveriesSource, inbox: DeliveriesInbox?) {
    LaunchedEffect(source, inbox) {
        inbox?.friend?.filterNotNull()?.collect { friendId ->
            inbox.consumeFriend(friendId)
            if (source.session.value is AuthenticationState.SignedIn) source.friendsActivity.reveal(friendId)
        }
    }
}

/**
 * The demo's circle as the iPhone makes it: ParcelListView makes FriendsView, and its store (`@StateObject`), anew as
 * the tab comes back out of the first screen, so the session leaving the demo forgets what the demo's actions changed
 * and the demo shows FriendsDemo.json afresh once it comes back. Watched from the window's root rather than the tab,
 * which Android disposes as another tab is chosen while the iPhone's lives on: a change made in the demo outlasts a trip
 * to the passport and back.
 */
@Composable
internal fun FriendsDemoEffects(source: DeliveriesSource, session: AuthenticationState) {
    val demo = session == AuthenticationState.Demo
    LaunchedEffect(source, demo) {
        if (!demo) source.friends.leaveDemo()
    }
}

/** How far right of their place a friend revealed comes in from, and the cards' corners, as their page zooms. */
private val LANDING_OFFSET = 100.dp
private val CARD_RADIUS = 18.dp
private const val LANDING_STEP_MILLIS = 60L
private val MAX_WIDTH = 680.dp
private val REGULAR_WIDTH = 600.dp
private val REGULAR_HEIGHT = 480.dp
private const val RELOAD_MILLIS = 60_000L
private const val ACTIVITY_MILLIS = 30_000L

/**
 * RootView's `.overlay(alignment: .top)`: the first notice of an invitation accepted ([FriendAcceptedNotice]) over the
 * window while someone is signed in, 16 in from its sides and 6 under the status bar, under what stands over the window
 * ([covered]), which TalkBack then keeps to. Open reveals the friend, which chooses the friends tab where their card
 * lands, and acknowledges them; ✕ acknowledges them. None over an invitation being presented ([invitationShown],
 * `!invitation.isPresenting`), which comes with the invitations received. The iPhone's declares a move from the top with
 * a fade, but no change of the notices animates, so it comes and goes at once, as here.
 */
@Composable
internal fun FriendNoticeHost(source: DeliveriesSource, session: AuthenticationState, covered: Boolean, invitationShown: Boolean = false) {
    val activity = source.friendsActivity
    val updates by activity.updatesFlow.collectAsState()
    val update = updates.firstOrNull()
    if (invitationShown || session !is AuthenticationState.SignedIn || update == null) return
    val safe = WindowInsets.systemBars.union(WindowInsets.displayCutout).asPaddingValues()
    val direction = LocalLayoutDirection.current
    Box(
        Modifier
            .fillMaxSize()
            .padding(
                start = safe.calculateStartPadding(direction) + NOTICE_SIDE,
                top = safe.calculateTopPadding() + NOTICE_TOP,
                end = safe.calculateEndPadding(direction) + NOTICE_SIDE,
            )
            .then(if (covered) Modifier.clearAndSetSemantics {} else Modifier),
        contentAlignment = Alignment.TopCenter,
    ) {
        key(update.friendId) {
            FriendAcceptedNotice(
                update,
                open = {
                    activity.reveal(update.friendId)
                    source.scope.launch { activity.acknowledge(update.friendId) }
                },
                dismiss = { source.scope.launch { activity.acknowledge(update.friendId) } },
            )
        }
    }
}

/**
 * `FriendAcceptedNotice`: on the paper with 22-point corners, a line of the ink at 8 % round it and a shadow (black at
 * 12 %, radius 18, 6 down), at most 520 wide, in by 16 and 8: the friends' symbol in title3 in delivered, "Name
 * accepted your invitation" in subheadline medium and → in caption semibold, 12 apart and 44 tall at least, which opens
 * the friend; then, 8 after it, ✕ on 32 × 44, read as "friends.dismissUpdate", which dismisses the notice.
 */
@Composable
internal fun FriendAcceptedNotice(update: FriendUpdate, open: () -> Unit, dismiss: () -> Unit, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val shape = ContinuousRoundedShape(NOTICE_RADIUS)
    val dismissal = localized("friends.dismissUpdate")
    Row(
        modifier
            .widthIn(max = NOTICE_WIDTH)
            .fillMaxWidth()
            .testTag(FRIENDS_NOTICE)
            .dropShadow(shape, NOTICE_SHADOW)
            .background(colors.paper, shape)
            .border(1.dp, colors.ink.copy(alpha = 0.08f), shape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            Modifier
                .weight(1f)
                .heightIn(min = 44.dp)
                .testTag(FRIENDS_NOTICE_OPEN)
                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = open),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PeekSymbol("person.2.fill", font = type.title3, tint = colors.resolve(ExperimentalPalette.delivered))
            Text(
                localizer.text("friends.invitationAccepted", mapOf("name" to update.nickname)),
                Modifier.weight(1f),
                color = colors.ink,
                style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines(),
            )
            // `Spacer(minLength: 4)`, with the stack's 12 either side of it.
            Spacer(Modifier.width(4.dp))
            PeekSymbol("arrow.right", font = type.style(IosTextStyle.Caption, FontWeight.SemiBold), tint = colors.ink)
        }
        Box(
            Modifier
                .size(32.dp, 44.dp)
                .testTag(FRIENDS_NOTICE_DISMISS)
                .clickable(interactionSource = null, indication = PlainPress, onClick = dismiss)
                .semantics {
                    contentDescription = dismissal
                    role = Role.Button
                },
            contentAlignment = Alignment.Center,
        ) {
            PeekSymbol("xmark", font = type.body, tint = colors.ink)
        }
    }
}

private val NOTICE_SIDE = 16.dp
private val NOTICE_TOP = 6.dp
private val NOTICE_WIDTH = 520.dp
private val NOTICE_RADIUS = 22.dp
private val NOTICE_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.12f), radius = 18.dp, y = 6.dp)
