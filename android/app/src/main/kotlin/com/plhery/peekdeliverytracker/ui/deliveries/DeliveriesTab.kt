package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.KeyEvent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
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
import androidx.navigation3.scene.SinglePaneSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.DeliveriesKey
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowHinge
import com.plhery.peekdeliverytracker.ui.tabs.TabReselectionEffect
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import java.util.Objects
import java.util.UUID
import kotlinx.coroutines.delay
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
 * - Stacked, the page comes over the list on a stack that the system's back and the predictive back gesture pop, and a
 *   parcel shown by itself beside the list goes back into it as the window turns.
 * - Side by side (a window wide and tall enough, or a foldable in book posture, a half each), the page stands beside
 *   the list, the first card's parcel when nobody chose one, its card ringed, and ↑, ↓ and Return step through them.
 *
 * The stack is drawn from the navigation, as the iPhone's NavigationStack is, and the navigation is kept with the
 * window: the parcel open comes back after a rotation, a fold or a resize, and after the system closed the app, once
 * the parcels are back. Tapping the tab again goes back to the list, and at the list scrolls it to its top. A parcel a
 * link asks for ([inbox]) opens on the list.
 */
@Composable
fun DeliveriesTab(
    source: DeliveriesSource,
    modifier: Modifier = Modifier,
    inbox: DeliveriesInbox? = null,
    page: @Composable (parcelId: UUID) -> Unit,
) {
    val launch = source.launch
    val navigator = rememberSaveable(launch, saver = DeliveriesNavigator.saver(launch)) { DeliveriesNavigator() }
    navigator.view = source::view
    val listState = rememberLazyListState()
    val archiveOpen = rememberForLaunch(launch, false)
    val showingSearch = rememberForLaunch(launch, false)
    val now = LocalNow.current
    val state = remember(source, navigator, listState) {
        DeliveriesState(source, navigator, listState, archiveOpen, showingSearch, now)
    }
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
        val rows = state.rows

        LaunchedEffect(state) { source.parcels.collect(state::receive) }
        LaunchedEffect(arrangement, rows) {
            navigator.turn(arrangement, state.topCard())
            navigator.settle(arrangement, rows)
        }
        HoldingDelivered(state, reduceMotion)
        Consuming(state, inbox)
        val scope = rememberCoroutineScope()
        TabReselectionEffect {
            if (arrangement == DeliveriesArrangement.Stacked && navigator.navigation.chosen != null) {
                navigator.toList()
            } else {
                state.scrollToTop(reduceMotion)
            }
        }

        val backStack = buildList {
            add(DeliveriesEntry.ParcelList)
            val navigation = navigator.navigation
            when (arrangement) {
                DeliveriesArrangement.Stacked ->
                    if (!navigation.shownAutomatically) navigation.path.forEach { add(DeliveriesEntry.ParcelPage(it)) }
                DeliveriesArrangement.SideBySide -> navigation.shown?.let { add(DeliveriesEntry.ParcelPage(it)) }
            }
        }
        val sideBySide = remember(arrangement, largeText, hinge) {
            SideBySideStrategy(arrangement == DeliveriesArrangement.SideBySide) { list, shown ->
                SideBySideColumns(state, largeText, list, shown)
            }
        }
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.fillMaxSize().deliveriesKeys(state, scope, reduceMotion),
            onBack = { navigator.pop(navigator.navigation.path.dropLast(1)) },
            sceneStrategies = listOf(sideBySide, SinglePaneSceneStrategy()),
            transitionSpec = { if (turning()) Push.still() else Push.forward(reduceMotion) },
            popTransitionSpec = { if (turning()) Push.still() else Push.back(reduceMotion) },
            predictivePopTransitionSpec = { if (turning()) Push.still() else Push.predictiveBack(reduceMotion) },
            entryProvider = { key ->
                when (key) {
                    DeliveriesEntry.ParcelList -> NavEntry<DeliveriesEntry>(key, metadata = mapOf(PANE to LIST_PANE)) {
                        DeliveryList(state)
                    }
                    is DeliveriesEntry.ParcelPage -> NavEntry<DeliveriesEntry>(key, metadata = mapOf(PANE to PAGE_PANE)) {
                        page(key.id)
                    }
                }
            },
        )
    }
}

/** A window this wide and this tall in dp counts as regular, as the iPhone's size classes would. */
private val REGULAR_WIDTH = 600.dp
private val REGULAR_HEIGHT = 480.dp

/** Whether the stack is turning between its arrangements: the iPhone swaps them at once. */
private fun AnimatedContentTransitionScope<Scene<DeliveriesEntry>>.turning(): Boolean =
    initialState is SideBySideScene || targetState is SideBySideScene

/**
 * The parcels just delivered keep their place a moment (DeliveredHold): the postmark lands, the card is read, then it
 * takes its new place. Nobody looking, they move at once. The postmark's tap, the paper and the card's flight to its
 * new place aren't ported yet: the card simply moves.
 */
@Composable
private fun HoldingDelivered(state: DeliveriesState, reduceMotion: Boolean) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val resumed = lifecycle.currentStateAsState().value.isAtLeast(Lifecycle.State.RESUMED)
    val watching = resumed && state.listShown && !reduceMotion
    state.watching = watching
    LaunchedEffect(watching) { if (!watching) state.letGo() }
    LaunchedEffect(state.hold.ids) {
        if (state.hold.isEmpty) return@LaunchedEffect
        delay(POSTMARK_MILLIS)
        // Here the iPhone taps success and throws paper out of Pip's box.
        delay(READ_MILLIS)
        state.letGo()
    }
}

private const val POSTMARK_MILLIS = 300L
private const val READ_MILLIS = 650L

/**
 * What waits for the list (A-D26, A-L14): a parcel a link asked for, at once and whenever another comes; a parcel's
 * notification opened before the list was there, as it appears; and what another app shared, as it appears and each
 * time the app comes back to the front.
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
 * The deliveries' keys, which do what their buttons do (A-S99): ↑ and ↓ show the card before or after the parcel
 * beside the list and bring it into view, Return opens the one shown by itself, Ctrl or Meta with F opens the search
 * and Escape closes it, Ctrl or Meta with N adds. The parcel's own are on its page.
 *
 * They are heard whether or not something in the window has the keys: the list takes them before what has the focus,
 * and the window gives them over once nothing has used them. Beside a parcel the list takes the focus, so that ↑ and ↓
 * step through the cards rather than move the focus.
 */
@Composable
private fun Modifier.deliveriesKeys(state: DeliveriesState, scope: kotlinx.coroutines.CoroutineScope, reduceMotion: Boolean): Modifier {
    val handle by rememberUpdatedState { event: KeyEvent -> state.key(event, scope, reduceMotion) }
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewCompat.OnUnhandledKeyEventListenerCompat { _, event -> handle(event) }
        ViewCompat.addOnUnhandledKeyEventListener(view, listener)
        onDispose { ViewCompat.removeOnUnhandledKeyEventListener(view, listener) }
    }
    val focus = remember { FocusRequester() }
    val sideBySide = state.arrangement == DeliveriesArrangement.SideBySide
    LaunchedEffect(sideBySide) { if (sideBySide) focus.requestFocus() }
    return focusRequester(focus).onPreviewKeyEvent { handle(it.nativeKeyEvent) }.focusable()
}

/** Acts on a key the deliveries answer, as [DeliveriesKey.available] has them; true once it has used it. */
private fun DeliveriesState.key(event: KeyEvent, scope: kotlinx.coroutines.CoroutineScope, reduceMotion: Boolean): Boolean {
    val keys = DeliveriesKey.available(
        free = true, searching = showingSearch, typing = false, arrangement = arrangement, navigation = navigator.navigation,
        cards = rows, hasParcels = parcels.isNotEmpty(), canUndo = false,
    )
    val command = event.hasModifiers(KeyEvent.META_CTRL_ON) || event.hasModifiers(KeyEvent.META_META_ON)
    val bare = event.hasNoModifiers()
    val key = when {
        command && event.keyCode == KeyEvent.KEYCODE_N -> DeliveriesKey.Add
        command && event.keyCode == KeyEvent.KEYCODE_F -> DeliveriesKey.Find
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
        DeliveriesKey.Find -> showingSearch = true
        DeliveriesKey.CloseSearch -> showingSearch = false
        DeliveriesKey.Undo -> Unit
        DeliveriesKey.Previous, DeliveriesKey.Next -> {
            val card = navigator.step(if (key == DeliveriesKey.Next) 1 else -1, rows)
            if (card != null) scope.launch { bringIntoView(card, reduceMotion) }
        }
        DeliveriesKey.Open -> navigator.navigation.shown?.let(navigator::open)
    }
    return true
}

/** The parcel's page and the list side by side, a scene of their own. */
private class SideBySideStrategy(
    private val active: Boolean,
    private val columns: @Composable (list: @Composable () -> Unit, page: (@Composable () -> Unit)?) -> Unit,
) : SceneStrategy<DeliveriesEntry> {
    override fun SceneStrategyScope<DeliveriesEntry>.calculateScene(entries: List<NavEntry<DeliveriesEntry>>): Scene<DeliveriesEntry>? {
        if (!active) return null
        val list = entries.firstOrNull { it.metadata[PANE] == LIST_PANE } ?: return null
        val page = entries.lastOrNull { it.metadata[PANE] == PAGE_PANE }
        return SideBySideScene(entries, list, page, columns)
    }
}

/**
 * The list and the page beside it, one scene whichever parcel shows: a new one takes the old one's place at once, as
 * the iPhone's detail column does. Nothing is before it, so back leaves the app.
 */
private class SideBySideScene(
    override val entries: List<NavEntry<DeliveriesEntry>>,
    private val list: NavEntry<DeliveriesEntry>,
    private val page: NavEntry<DeliveriesEntry>?,
    private val columns: @Composable (list: @Composable () -> Unit, page: (@Composable () -> Unit)?) -> Unit,
) : Scene<DeliveriesEntry> {
    override val key: Any = SideBySideScene::class
    override val previousEntries: List<NavEntry<DeliveriesEntry>> = emptyList()
    override val content: @Composable () -> Unit = {
        columns({ list.Content() }, page?.let { entry -> { entry.Content() } })
    }

    override fun equals(other: Any?): Boolean =
        other is SideBySideScene && entries == other.entries && list == other.list && page == other.page

    override fun hashCode(): Int = Objects.hash(entries, list, page)
}

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
            Box(Modifier.width(listWidth).fillMaxHeight()) { list() }
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
 * How a parcel's page comes and goes until it zooms from its card: UINavigationController's push. The page slides in
 * from the trailing edge over the list, which moves a third of the way out behind it, on a spring of response 0.35 s
 * that doesn't bounce, and back reverses it. The stack keeps the page over the list both ways. Nothing moves under
 * Reduce Motion, nor as the window turns between its arrangements.
 */
private object Push {
    fun forward(reduceMotion: Boolean): ContentTransform =
        if (reduceMotion) still() else slideInHorizontally(spring) { it } togetherWith slideOutHorizontally(spring) { -it / 3 }

    fun back(reduceMotion: Boolean): ContentTransform = pop(reduceMotion, spring)

    /**
     * The predictive back gesture moves both the same way. The stack seeks the pop by the gesture's progress, so an even
     * pace keeps the page under the finger; let go, it goes the rest of the way in what is left of 0.35 s.
     */
    fun predictiveBack(reduceMotion: Boolean): ContentTransform = pop(reduceMotion, gesture)

    private fun pop(reduceMotion: Boolean, motion: FiniteAnimationSpec<IntOffset>): ContentTransform =
        if (reduceMotion) still() else slideInHorizontally(motion) { -it / 3 } togetherWith slideOutHorizontally(motion) { it }

    fun still(): ContentTransform = EnterTransition.None togetherWith ExitTransition.None

    private val spring = Motion.springWithResponse<IntOffset>(0.35, 1.0)
    private val gesture = tween<IntOffset>(durationMillis = 350, easing = LinearEasing)
}

/** A parcel's page until it is ported: the parcel's name, in the middle of the page, clear of the bars. */
@Composable
fun ParcelPagePlaceholder(label: String?) {
    val colors = PeekTheme.colors
    Box(Modifier.fillMaxSize().background(colors.background).testTag(PARCEL_PAGE), contentAlignment = Alignment.Center) {
        BasicText(label.orEmpty(), Modifier.safeDrawingPadding().padding(16.dp), style = PeekTheme.type.headline.copy(color = colors.label))
    }
}

/** Which pane of the deliveries an entry is, in its metadata. */
private const val PANE = "deliveries.pane"
private const val LIST_PANE = "list"
private const val PAGE_PANE = "page"

/** The test tags of the list, of a parcel's page and of the column beside the list. */
const val PARCEL_LIST = "parcelList"
const val PARCEL_PAGE = "parcelPage"
const val PAGE_COLUMN = "pageColumn"
