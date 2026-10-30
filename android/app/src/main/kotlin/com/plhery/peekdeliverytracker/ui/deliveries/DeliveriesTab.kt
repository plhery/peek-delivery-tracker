package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import android.view.KeyEvent
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.core.view.ViewCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.currentStateAsState
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope
import androidx.navigation3.ui.NavDisplay
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.DeliveriesKey
import com.plhery.peekdeliverytracker.core.ReviewPrompt
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowHinge
import com.plhery.peekdeliverytracker.ui.tabs.TabReselectionEffect
import com.plhery.peekdeliverytracker.ui.tabs.tabContentPadding
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberHaptics
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.zoom.ZoomStack
import java.time.Instant
import java.util.Objects
import java.util.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

/** What the deliveries' stack holds: the list at its root and, over it or beside it, a parcel's page. */
sealed interface DeliveriesEntry : NavKey {
    /** The list, the stack's root. */
    data object ParcelList : DeliveriesEntry

    /** A parcel's page. */
    data class ParcelPage(val id: UUID) : DeliveriesEntry
}

/**
 * ParcelListView's DeliveryListView: the list and a parcel opened over it or beside it, as the window has room for.
 *
 * - Stacked, the page zooms out of its card over the list and back into it ([ZoomStack]): the system's back, the
 *   predictive back gesture and a drag down from the page's top send it back. A parcel shown by itself beside the list
 *   goes back into it as the window turns.
 * - Side by side (a window wide and tall enough, or a foldable in book posture, a half each), the page stands beside
 *   the list, the first card's parcel when nobody chose one, its card ringed, and ↑, ↓ and Return step through them.
 *
 * The stack is drawn from the navigation, as the iPhone's NavigationStack is, and the navigation is kept with the
 * window: the parcel open comes back after a rotation, a fold or a resize, and after the system closed the app, once
 * the parcels are back. Tapping the tab again scrolls the list to its top: over a parcel's page the tab bar has gone.
 * A parcel a link asks for ([inbox]) opens on the list.
 */
@Composable
fun DeliveriesTab(
    source: DeliveriesSource,
    modifier: Modifier = Modifier,
    inbox: DeliveriesInbox? = null,
) {
    val launch = source.launch
    val navigator = rememberSaveable(launch, saver = DeliveriesNavigator.saver(launch)) { DeliveriesNavigator() }
    navigator.view = source::view
    navigator.left = source.pageWork::forget
    val listState = rememberLazyListState()
    val archiveOpen = rememberForLaunch(launch, false)
    val reviewDue = rememberForLaunch(launch, false)
    val sampleShown = rememberForLaunch(launch, false)
    val mapCover = rememberForLaunch<String?>(launch, null)
    val views = rememberListViewStates(launch)
    val prompts = rememberPromptStates(launch)
    val now = LocalNow.current
    val scope = rememberCoroutineScope()
    val state = remember(source, navigator, listState) {
        DeliveriesState(source, navigator, listState, archiveOpen, reviewDue, sampleShown, mapCover, views, prompts, now, scope)
    }
    state.localizer = LocalLocalizer.current
    // What the paper and the flights are drawn with, as the list was last drawn.
    state.identities = LocalCarrierIdentities.current
    state.language = LocalLanguage.current
    state.graphics = LocalGraphicsContext.current
    state.density = LocalDensity.current
    state.layoutDirection = LocalLayoutDirection.current
    LocalPaperSeeds.current?.let { state.seeds = it }
    LocalDeliveriesState.current?.invoke(state)
    val context = LocalContext.current
    val haptics = rememberHaptics()
    state.copy = { text ->
        context.getSystemService(ClipboardManager::class.java)?.setPrimaryClip(ClipData.newPlainText(text, text))
        haptics.success()
        // From Android 13 the system confirms a copy itself, and the app says nothing more.
        Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
    }
    // A message under the list has four seconds while it shows, from when it came; a newer one, even in the same
    // words, has its own. Counted here rather than by the list, which a turn of the window draws anew. An archive's
    // Undo counts its seven in the app's scope ([DeliveriesState.archive]).
    val undo by source.undoParcel.collectAsState()
    val toast = state.toast.takeIf { undo == null }
    LaunchedEffect(toast?.id) {
        if (toast == null) return@LaunchedEffect
        delay(TOAST_DURATION)
        state.dismiss(toast.id)
    }
    ListPullEffects(state)
    val reduceMotion = PeekTheme.reduceMotion
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val hinge = LocalWindowHinge.current

    BoxWithConstraints(modifier.fillMaxSize()) {
        val regularWidth = maxWidth >= REGULAR_WIDTH
        val regularHeight = maxHeight >= REGULAR_HEIGHT
        // A foldable in book posture shows the two halves side by side, whatever its width.
        val arrangement = if (hinge != null && regularWidth && regularHeight) {
            DeliveriesArrangement.SideBySide
        } else {
            DeliveriesArrangement.resolve(
                maxWidth.value.toDouble(), regularWidth, regularHeight, largeText, navigator.arrangement ?: DeliveriesArrangement.Stacked,
            )
        }
        // Read by the list as it is drawn, after this.
        state.arrangement = arrangement
        state.settleSheets()
        val rows = state.rows
        // Here rather than once drawn, so that the page beside the list shows from the window's first frame. The card
        // at the list's top is read as it was before the turn, and not followed as the list scrolls.
        navigator.turn(arrangement) { Snapshot.withoutReadObservation { state.topCard() } }
        navigator.settle(arrangement, rows)

        LaunchedEffect(state) { source.parcels.collect(state::receive) }
        // A page whose change was saved leaves, from the window that shows it by then, made again since or not.
        LaunchedEffect(state) {
            snapshotFlow { source.pageWork.leaving.toMap() }.collect { leaving ->
                leaving.forEach { (pageId, cards) -> state.leavePage(pageId, cards) }
            }
        }
        SearchCounted(state)
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        val resumed = lifecycle.currentStateAsState().value.isAtLeast(Lifecycle.State.RESUMED)
        // `watching`: the list is looked at, and may show a card moving.
        val watching = resumed && state.listShown && !state.showingFilters && !state.hasCustomView && !reduceMotion
        HoldingDelivered(state, resumed, watching)
        Reviewing(state, resumed, watching, now)
        Consuming(state, inbox)
        DeliveriesKeys(state, scope, reduceMotion)
        // Over a parcel's page the tab bar has gone, so the tab is tapped again only with the list in view.
        TabReselectionEffect { state.scrollToTop(reduceMotion) }

        val backStack = buildList {
            add(DeliveriesEntry.ParcelList)
            val navigation = navigator.navigation
            when (arrangement) {
                DeliveriesArrangement.Stacked -> state.pageOverList?.let { add(DeliveriesEntry.ParcelPage(it)) }
                DeliveriesArrangement.SideBySide -> navigation.shown?.let { add(DeliveriesEntry.ParcelPage(it)) }
            }
        }
        val panes = remember(arrangement, largeText, hinge) {
            PanesStrategy { list, shown ->
                when (arrangement) {
                    DeliveriesArrangement.SideBySide -> SideBySideColumns(state, largeText, list, shown)
                    DeliveriesArrangement.Stacked -> ZoomStack(
                        transition = state.zoom,
                        top = navigator.navigation.chosen,
                        onBack = { navigator.pop(navigator.navigation.path.dropLast(1)) },
                        root = list,
                        page = shown,
                        bar = { state.pageOverList?.let { ParcelPageBar(state, it) } },
                    )
                }
            }
        }
        // Each arrangement draws its own stack, as the iPhone swaps its NavigationStack and NavigationSplitView: a turn
        // is no navigation, and the entries start again. The list keeps its place, its pull and its messages, held
        // above; cards left open shut, and a card's menu closes with its card, as the iPhone's do. One NavDisplay
        // across turns kept the side-by-side scene on its books, as it tidies them only once it has seen a transition
        // run and never sees a still one run; that scene could then claim the list's entry from the list a back went
        // to, and neither drew it.
        key(arrangement) {
            NavDisplay(
                backStack = backStack,
                modifier = Modifier.fillMaxSize(),
                onBack = { navigator.pop(navigator.navigation.path.dropLast(1)) },
                sceneStrategies = listOf(panes),
                transitionSpec = { still() },
                popTransitionSpec = { still() },
                predictivePopTransitionSpec = { still() },
                entryProvider = { key ->
                    when (key) {
                        DeliveriesEntry.ParcelList -> NavEntry<DeliveriesEntry>(key, metadata = mapOf(PANE to LIST_PANE)) {
                            DeliveryList(state)
                        }
                        is DeliveriesEntry.ParcelPage -> NavEntry<DeliveriesEntry>(key, metadata = mapOf(PANE to PAGE_PANE)) {
                            DemoPage(state) { ParcelPage(state, key.id) }
                        }
                    }
                },
            )
        }
        // The page's ••• menu, over the whole window where the tab shell draws it.
        PageMenuHost(state)
        // The carrier editor, a page's or a card's, over the whole window where the tab shell draws it.
        CarrierEditorHost(state)
        // A parcel's alerts, over the whole window where the tab shell draws them.
        ParcelAlertsHost(state)
        // The page's map, over the whole window where the tab shell draws it.
        ParcelMapHost(state)
        // The paper of a delivery, over the list and the parcel beside it, as the iPhone's is over the whole screen.
        DeliveredPaper(state)
    }
}

/**
 * The page drawn over the list, and its bar: the one on top, or the one that left, which stays on the stack until it
 * has gone back into its card.
 */
private val DeliveriesState.pageOverList: UUID? get() = navigator.navigation.chosen ?: zoom.covered as UUID?

/** A window this wide and this tall in dp counts as regular, as the iPhone's size classes would. */
private val REGULAR_WIDTH = 600.dp
private val REGULAR_HEIGHT = 480.dp

/**
 * `holdingDelivered`: the parcels just delivered keep their place a moment (DeliveredHold). The postmark lands with a
 * tap of success and paper out of Pip's open box ([DeliveriesState.postmark]), the card is read, then it flies to its
 * new place ([DeliveriesState.letGo]). Nobody looking, or under Reduce Motion, they take their new places at once,
 * unseen, as nothing is held then. The window's frames stop while it is away: the cards in flight are at their places
 * as it comes back, as the iPhone's flights have landed meanwhile.
 */
@Composable
private fun HoldingDelivered(state: DeliveriesState, resumed: Boolean, watching: Boolean) {
    state.watching = watching
    SensoryFeedbackEffect(SensoryFeedback.Success, state.postmarks)
    LaunchedEffect(watching) { if (!watching) state.release() }
    LaunchedEffect(resumed) { if (!resumed) state.dropFlights() }
    LaunchedEffect(state.hold.ids) {
        if (state.hold.isEmpty) return@LaunchedEffect
        delay(POSTMARK_MILLIS)
        state.postmark()
        delay(READ_MILLIS)
        state.letGo()
    }
}

/**
 * `reviewing`: a parcel seen arriving asks for a rating a moment after its card has landed ([DeliveriesState.reviewDue]),
 * while the list is still looked at with nothing over it or at its foot. The days the list is in use count towards the
 * first ask; a failure shown on it keeps any ask away for the day. Play's prompt is shown over the window's activity.
 * A card's open menu stands over the list too, as the questions it asks do on the iPhone.
 *
 * Out of the iPhone's: the Add and account sheets, a page of the site, the notifications' invitation and what a card's
 * menu asks, which don't stand over the list on Android yet, nor anything over the parcel beside it.
 */
@Composable
private fun Reviewing(state: DeliveriesState, resumed: Boolean, watching: Boolean, now: () -> Instant) {
    val source = state.source
    val prompt = source.reviewPrompt
    val settled by source.reviewSettled.collectAsState()
    val errorMessage by source.errorMessage.collectAsState()
    val undo by source.undoParcel.collectAsState()
    val refreshing by source.refreshing.collectAsState()
    val inUse = resumed && settled
    LaunchedEffect(inUse) { if (inUse) prompt.used(now()) }
    // Something failed in front of the reader: the list's notice, a check, or an action. Noted as it comes into view
    // (`.onChange(of: failureShown)`), not as the list first shows with it.
    val failureShown = resumed &&
        (errorMessage != null || state.toast?.warning == true || state.actionError != null || state.pageError != null)
    val failure by rememberUpdatedState(failureShown)
    LaunchedEffect(prompt) {
        snapshotFlow { failure }.drop(1).collect { shown -> if (shown) prompt.noteBadMoment(now()) }
    }
    val covered = state.showingFilters || state.modal
    val ready = watching && !covered && state.hold.isEmpty && state.flights.isEmpty() && state.toast == null && undo == null &&
        !refreshing && !state.showingSearch && settled
    val activity = LocalActivity.current
    val requester = remember(source, activity) { activity?.let(source::reviews) }
    LaunchedEffect(state.reviewDue, ready, requester) {
        if (requester == null) return@LaunchedEffect
        ReviewPrompt.ask(
            due = state.reviewDue, ready = ready,
            take = {
                if (!state.reviewDue) {
                    false
                } else {
                    state.reviewDue = false
                    prompt.take(now(), calm = true)
                }
            },
            requester = requester, report = source::action,
        )
    }
}

/**
 * A search counts once its query has stayed the same 800 ms, and isn't empty (`.task(id: query)`). Only a change counts:
 * the list drawn again, after a rotation or back from a parcel, doesn't count the query it last counted again
 * ([DeliveriesState.countedQuery]), and counts one typed just before once it has stayed.
 */
@Composable
private fun SearchCounted(state: DeliveriesState) {
    LaunchedEffect(state) {
        snapshotFlow { state.query }.collectLatest { query ->
            if (query == state.countedQuery) return@collectLatest
            state.countedQuery = null
            if (query.isBlank()) return@collectLatest
            delay(SEARCH_SETTLE_MILLIS)
            state.countedQuery = query
            state.source.action("search")
        }
    }
}

private const val SEARCH_SETTLE_MILLIS = 800L

private const val POSTMARK_MILLIS = 300L
private const val READ_MILLIS = 650L

/** How long a message under the list stays, in milliseconds. */
private const val TOAST_DURATION = 4_000L

/**
 * What waits for the list in this window (A-D26, A-L14): a parcel a link asked for, at once and whenever another comes;
 * a parcel's notification opened before the list was there, as it appears; and what another app shared, as it appears
 * and each time the app comes back to the front.
 */
@Composable
private fun Consuming(state: DeliveriesState, inbox: DeliveriesInbox?) {
    if (inbox == null) return
    LaunchedEffect(inbox) {
        inbox.consumePendingParcelNotification()?.let(state.navigator::open)
        inbox.parcel.filterNotNull().collect { parcelId ->
            state.navigator.open(parcelId)
            inbox.consume(parcelId)
        }
    }
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        inbox.consumeSharedDraft()?.let { draft ->
            state.source.action("parcel-share-received")
            state.addSharedDraft(draft)
        }
    }
}

/**
 * The deliveries' keys, which do what their buttons do (A-S99, A-G10): ↑ and ↓ show the card before or after the
 * parcel beside the list and bring it into view, Return opens the one shown by itself, Ctrl or Meta with F opens the
 * search and focuses its field and Escape closes it, Ctrl or Meta with N adds. The parcel's own are on its page. None
 * while the filters, a card's menu, the delete it asks about or an alert are open (`keyboardFree`); while the search
 * field has the focus, only adding, finding and closing the search.
 *
 * As the iPhone's, they are the window's: once nothing in it has used a key, the window gives it over here. Beside a
 * parcel the list's column also takes them, and the focus, so that ↑ and ↓ step through the cards rather than move
 * the focus ([listKeys]).
 */
@Composable
private fun DeliveriesKeys(state: DeliveriesState, scope: CoroutineScope, reduceMotion: Boolean) {
    val handle by rememberUpdatedState { event: KeyEvent -> state.key(event, scope, reduceMotion) }
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewCompat.OnUnhandledKeyEventListenerCompat { _, event -> handle(event) }
        ViewCompat.addOnUnhandledKeyEventListener(view, listener)
        onDispose { ViewCompat.removeOnUnhandledKeyEventListener(view, listener) }
    }
}

/**
 * The list's column beside a parcel takes the focus and the deliveries' keys, after what has the focus in it: a card
 * or a button reached with the keyboard opens or presses on Return itself.
 */
@Composable
private fun Modifier.listKeys(state: DeliveriesState): Modifier {
    val scope = rememberCoroutineScope()
    val reduceMotion = PeekTheme.reduceMotion
    val focus = remember { FocusRequester() }
    LaunchedEffect(focus) { focus.requestFocus() }
    return focusRequester(focus).onKeyEvent { state.key(it.nativeKeyEvent, scope, reduceMotion) }.focusable()
}

/** Acts on a key the deliveries answer, as [DeliveriesKey.available] has them; true once it has used it. */
private fun DeliveriesState.key(event: KeyEvent, scope: CoroutineScope, reduceMotion: Boolean): Boolean {
    val keys = DeliveriesKey.available(
        free = !showingFilters && !modal, searching = showingSearch, typing = searchFocused, arrangement = arrangement,
        navigation = navigator.navigation,
        cards = rows, hasParcels = parcels.isNotEmpty(), canUndo = source.undoParcel.value != null,
    )
    val command = event.hasModifiers(KeyEvent.META_CTRL_ON) || event.hasModifiers(KeyEvent.META_META_ON)
    val bare = event.hasNoModifiers()
    val key = when {
        command && event.keyCode == KeyEvent.KEYCODE_N -> DeliveriesKey.Add
        command && event.keyCode == KeyEvent.KEYCODE_F -> DeliveriesKey.Find
        command && event.keyCode == KeyEvent.KEYCODE_Z -> DeliveriesKey.Undo
        bare && event.keyCode == KeyEvent.KEYCODE_ESCAPE -> DeliveriesKey.CloseSearch
        bare && event.keyCode == KeyEvent.KEYCODE_DPAD_UP -> DeliveriesKey.Previous
        bare && event.keyCode == KeyEvent.KEYCODE_DPAD_DOWN -> DeliveriesKey.Next
        bare && (event.keyCode == KeyEvent.KEYCODE_ENTER || event.keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER) -> DeliveriesKey.Open
        else -> return false
    }
    if (key !in keys) return false
    // A key held down repeats ↑ and ↓ as a keyboard does, and nothing else.
    if (event.action != KeyEvent.ACTION_DOWN) return true
    if (event.repeatCount > 0 && key != DeliveriesKey.Previous && key != DeliveriesKey.Next) return true
    when (key) {
        DeliveriesKey.Add -> add()
        DeliveriesKey.Find -> openSearch()
        DeliveriesKey.CloseSearch -> closeSearch()
        DeliveriesKey.Undo -> source.undoParcel.value?.let { restore(it) }
        DeliveriesKey.Previous, DeliveriesKey.Next -> {
            val card = navigator.step(if (key == DeliveriesKey.Next) 1 else -1, rows)
            if (card != null) scope.launch { bringIntoView(card, reduceMotion) }
        }
        DeliveriesKey.Open -> navigator.navigation.shown?.let(navigator::open)
    }
    return true
}

/**
 * The list and the parcel's page as one scene, whichever parcel shows: `panes` draws them, side by side or the page
 * over the list. NavDisplay sees no transition, and nothing before the scene, so it leaves the system's back to the
 * page over the list ([ZoomStack]); with none, back leaves the app.
 */
private class PanesStrategy(
    private val panes: @Composable (list: @Composable () -> Unit, page: (@Composable () -> Unit)?) -> Unit,
) : SceneStrategy<DeliveriesEntry> {
    override fun SceneStrategyScope<DeliveriesEntry>.calculateScene(entries: List<NavEntry<DeliveriesEntry>>): Scene<DeliveriesEntry>? {
        val list = entries.firstOrNull { it.metadata[PANE] == LIST_PANE } ?: return null
        val page = entries.lastOrNull { it.metadata[PANE] == PAGE_PANE }
        return PanesScene(entries, list, page, panes)
    }
}

/** The list and the page, side by side or one over the other: a new page takes the old one's place in the same scene. */
private class PanesScene(
    override val entries: List<NavEntry<DeliveriesEntry>>,
    private val list: NavEntry<DeliveriesEntry>,
    private val page: NavEntry<DeliveriesEntry>?,
    private val panes: @Composable (list: @Composable () -> Unit, page: (@Composable () -> Unit)?) -> Unit,
) : Scene<DeliveriesEntry> {
    override val key: Any = PanesScene::class
    override val previousEntries: List<NavEntry<DeliveriesEntry>> = emptyList()
    override val content: @Composable () -> Unit = {
        panes({ list.Content() }, page?.let { entry -> { entry.Content() } })
    }

    override fun equals(other: Any?): Boolean =
        other is PanesScene && entries == other.entries && list == other.list && page == other.page

    override fun hashCode(): Int = Objects.hash(entries, list, page)
}

/** NavDisplay's own transitions: none, as the zoom is drawn by the scene. */
private fun still(): ContentTransform = EnterTransition.None togetherWith ExitTransition.None

/**
 * NavigationSplitView's columns: the list `listWidth` wide, about 380 (480 at the accessibility text sizes) and no
 * wider than leaves the parcel its width, and the page beside it, or the page's ground while none shows. Adding a
 * parcel and the account stand at the window's trailing edge, over the page. A hinge splits them at the fold instead.
 */
@Composable
private fun SideBySideColumns(
    state: DeliveriesState,
    largeText: Boolean,
    list: @Composable () -> Unit,
    page: (@Composable () -> Unit)?,
) {
    val hinge = LocalWindowHinge.current
    val density = LocalDensity.current
    BoxWithConstraints(Modifier.fillMaxSize().background(PeekTheme.colors.background)) {
        val listWidth: Dp
        val pageStart: Dp
        if (hinge != null) {
            listWidth = with(density) { hinge.start.toDp() }
            pageStart = with(density) { hinge.end.toDp() }
        } else {
            val smallest = DeliveriesArrangement.listWidth(largeText).dp
            val ideal = if (largeText) 480.dp else 380.dp
            val largest = if (largeText) 560.dp else 480.dp
            listWidth = min(largest, max(smallest, min(ideal, maxWidth - DeliveriesArrangement.parcelWidth(largeText).dp)))
            pageStart = listWidth
        }
        CompositionLocalProvider(LocalColumnEdges provides ColumnEdges(start = true, end = false)) {
            Box(Modifier.width(listWidth).fillMaxHeight().testTag(LIST_COLUMN).listKeys(state)) { list() }
        }
        CompositionLocalProvider(LocalColumnEdges provides ColumnEdges(start = false, end = true)) {
            // The toolbar's glass shows the page under it.
            val backdrop = rememberMaterialBackdrop()
            Box(Modifier.offset(x = pageStart).width(maxWidth - pageStart).fillMaxHeight().testTag(PAGE_COLUMN)) {
                Box(Modifier.fillMaxSize().materialBackdrop(backdrop)) {
                    if (page != null) page() else Box(Modifier.fillMaxSize().background(PeekTheme.colors.background))
                }
                PageToolbar(state, backdrop)
            }
        }
    }
}

/**
 * A parcel's page over the list in the demo, under the demo's bar as the list is (`safeAreaInset(edge: .top)`), which
 * stands under the page's own bar, on the cream from the window's top: the page keeps clear of the bar as it would of
 * the window's. Beside the list the bar stays over the list alone.
 */
@Composable
private fun DemoPage(state: DeliveriesState, content: @Composable () -> Unit) {
    val source = state.source
    val session by source.session.collectAsState()
    val bar = state.arrangement == DeliveriesArrangement.Stacked && session == AuthenticationState.Demo
    Column(Modifier.fillMaxSize()) {
        if (bar) {
            val padding = tabContentPadding()
            val direction = LocalLayoutDirection.current
            DemoModeBar(
                onExit = source::showWelcome,
                modifier = Modifier.background(PeekTheme.colors.cream).padding(top = barRowTop() + BAR_HEIGHT),
                sides = PaddingValues(start = padding.calculateStartPadding(direction), end = padding.calculateEndPadding(direction)),
            )
        }
        val topTaken = if (bar) Modifier.consumeWindowInsets(WindowInsets.safeDrawing.only(WindowInsetsSides.Top)) else Modifier
        Box(Modifier.weight(1f).fillMaxWidth().then(topTaken)) {
            CompositionLocalProvider(LocalPageTop provides if (bar) 0.dp else null) { content() }
        }
    }
}

/** Which pane of the deliveries an entry is, in its metadata. */
private const val PANE = "deliveries.pane"
private const val LIST_PANE = "list"
private const val PAGE_PANE = "page"

/** The test tags of the list, of a parcel's page, and of the list's column and the page's beside a parcel. */
const val PARCEL_LIST = "parcelList"
const val PARCEL_PAGE = "parcelPage"
const val LIST_COLUMN = "listColumn"
const val PAGE_COLUMN = "pageColumn"
