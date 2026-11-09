package com.plhery.peekdeliverytracker.ui.tabs

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.KeyEvent
import android.view.ViewTreeObserver
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.tappableElement
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.core.view.ViewCompat
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import com.plhery.peekdeliverytracker.ui.WindowKey
import com.plhery.peekdeliverytracker.ui.WindowShortcutsEffect
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

/** The iPhone's three tabs (ParcelListView's TabView), in its order: a tab's ordinal is its tag there. */
enum class PeekTab(val copyKey: String, val symbol: String, val screen: String) {
    Deliveries("native.deliveries", "shippingbox.fill", "deliveries"),

    /** ExperimentalCopy's `passport`, which reads `passport.title`. */
    Passport("passport.title", "book.closed.fill", "passport"),
    Friends("friends.title", "person.2.fill", "friends"),
}

/**
 * The room the tab bar takes over the tab's content, which draws edge to edge under it: from the window's bottom to
 * the bar's top on a phone, from the window's top to the bar's bottom where the bar stands at the top.
 */
val LocalTabBarPadding = compositionLocalOf { PaddingValues(0.dp) }

/**
 * The padding of a tab's content, which draws edge to edge under the bar and the system's: on each side the system
 * bars and the display cutout, and at the top and the bottom whichever reaches further of those and the bar's room.
 * A bar at the top leaves the bottom to the navigation buttons or a taskbar, which still need room there.
 */
@Composable
fun tabContentPadding(): PaddingValues {
    val bar = LocalTabBarPadding.current
    val direction = LocalLayoutDirection.current
    val safe = WindowInsets.systemBars.union(WindowInsets.displayCutout).asPaddingValues()
    return PaddingValues(
        start = safe.calculateStartPadding(direction),
        top = max(safe.calculateTopPadding(), bar.calculateTopPadding()),
        end = safe.calculateEndPadding(direction),
        bottom = max(safe.calculateBottomPadding(), bar.calculateBottomPadding()),
    )
}

/**
 * Taps on the tab already chosen, for what the tab shows: a list scrolls to its top, as a UITabBarController's tab
 * does. A page pushed over a stack's root hides the bar ([TabBarHidden]), so the tab is tapped again only with the root
 * in view.
 */
@Stable
class TabReselection {
    private val taps = MutableSharedFlow<Unit>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    internal val events get() = taps

    fun reselect() {
        taps.tryEmit(Unit)
    }
}

/**
 * An upright hinge that splits the window, as a foldable in book posture or a dual screen spanned has: where it starts
 * and ends, in pixels from the window's left, and whether the device stands half open there (`halfOpen`, a fold
 * that isn't flat), as an iPhone Duo's `window.fold` is.
 */
@Immutable
data class WindowHinge(val start: Int, val end: Int, val halfOpen: Boolean = false)

/** The hinge splitting the tab's window, null where none does. */
val LocalWindowHinge = compositionLocalOf<WindowHinge?> { null }

/**
 * A level hinge that splits the window, as a foldable half open on a table has: where it starts and ends, in pixels
 * from the window's top, and whether the device stands half open there (`halfOpen`).
 */
@Immutable
data class WindowLevelHinge(val top: Int, val bottom: Int, val halfOpen: Boolean = false)

/** The level hinge splitting the tab's window, null where none does. */
val LocalWindowLevelHinge = compositionLocalOf<WindowLevelHinge?> { null }

/** The reselection of the tab being drawn, or of what its stack shows at its root. */
val LocalTabReselection = staticCompositionLocalOf { TabReselection() }

/**
 * What a tab draws over the whole window, its tab bar and the system's bars too, as an iPhone's context menu dims all
 * of the screen and a sheet comes up over the tab bar: the shell draws each layer last, in the order they came, and
 * while one shows TalkBack reaches nothing under them. [backdrop] is what the shell's tab draws, for a layer's glass
 * to show through. Null outside a shell, where a tab draws its layers over itself. With the window's front ([window])
 * the layers are drawn there instead, among every other overlay's in the order they all came; [name] tells this
 * overlay's from the others' there, the same in the window made again.
 */
@Stable
class TabOverlay(val backdrop: MaterialBackdrop? = null, private val window: WindowLayers? = null, internal val name: String = "") {
    private val layers = mutableStateListOf<Pair<Any, @Composable () -> Unit>>()

    /** Draws `content` as the layer `key`, in its place if it shows already, or takes the layer away with null. */
    fun show(key: Any, content: (@Composable () -> Unit)?) {
        val index = layers.indexOfFirst { it.first == key }
        when {
            content == null -> if (index >= 0) layers.removeAt(index)
            index >= 0 -> layers[index] = key to content
            else -> layers += key to content
        }
        window?.show(this, key, content)
    }

    /**
     * Takes the layer `key` away while it still draws `content`: an owner made again elsewhere in the window (the list
     * laid out anew as the window turns) takes the key first, and the old one, gone after it, leaves the new one's layer.
     */
    fun hide(key: Any, content: @Composable () -> Unit) {
        if (layers.any { it.first == key && it.second === content }) show(key, null)
    }

    /** Whether a layer shows. */
    val covered: Boolean get() = layers.isNotEmpty()

    /** The layers, in the order they came; where there is a window's front, what they are composed with there. */
    @Composable
    fun Content() {
        if (window != null) {
            window.Place(this)
        } else {
            for ((key, content) in layers) {
                key(key) {
                    val above = remember(this, key) { { layers.dropWhile { it.first != key }.drop(1).map { it.first } } }
                    CompositionLocalProvider(LocalLayersAbove provides above) { content() }
                }
            }
        }
    }
}

/**
 * The window's front: the layers of every overlay drawing into it, a parcel link's sheet, the tab's sheets, menus and
 * alerts, the browser check's sheet, in the order they all came, the last on top, as the iPhone presents each over the
 * one presented before it. The layer on top is the one composed last, whose back handler takes the back. Each is
 * composed with what its overlay's [TabOverlay.Content] stands under, as it would be there. TalkBack reaches only the
 * layers of the overlay that showed the one on top: each overlay's layers are modal over what is under them.
 */
@Stable
class WindowLayers private constructor(restored: List<String>) {
    /** A layer, and where it stood in the window before it was made again, if it was there; `Int.MAX_VALUE` if not. */
    private class Layer(val host: TabOverlay, val key: Any, val content: @Composable () -> Unit, val rank: Int)

    private val layers = mutableStateListOf<Layer>()
    private val places = mutableStateMapOf<TabOverlay, CompositionLocalContext>()

    /**
     * The layers the window had before it was made again, in their order. Its layers come back as they are composed,
     * not as they came, so each goes back to its place among them, once.
     */
    private val restored = restored.toMutableList()

    /** Made again with layers over it, as a fold, an unfold or a rotation makes it again with a sheet up. */
    private val remade = restored.isNotEmpty()

    /** The layers composed where they stand, by [id]: what the window made again waits for before it is first drawn. */
    private val composed = mutableSetOf<String>()

    constructor() : this(emptyList())

    /** Whether anything stands over the window: a sheet, a popover or a menu, which TalkBack keeps to. */
    val covered: Boolean get() = layers.isNotEmpty()

    internal fun show(host: TabOverlay, key: Any, content: (@Composable () -> Unit)?) {
        val index = layers.indexOfFirst { it.host === host && it.key == key }
        when {
            content == null -> if (index >= 0) layers.removeAt(index)
            index >= 0 -> layers[index] = Layer(host, key, content, layers[index].rank)
            else -> {
                val rank = restored.indexOf(id(host, key)).takeIf { it >= 0 }?.also { restored[it] = "" } ?: Int.MAX_VALUE
                val at = layers.indexOfFirst { it.rank > rank }.takeIf { it >= 0 } ?: layers.size
                layers.add(at, Layer(host, key, content, rank))
            }
        }
    }

    private fun id(host: TabOverlay, key: Any) = "${host.name}/$key"

    /** The keys of the layers over the layer `key` of `host`, read as they change. */
    private fun layersAbove(host: TabOverlay, key: Any): () -> List<Any> = {
        val at = layers.indexOfFirst { it.host === host && it.key == key }
        if (at < 0) emptyList() else layers.subList(at + 1, layers.size).map { it.key }
    }

    /** Where `host` draws its layers: what is provided there, which its layers are composed with. */
    @Composable
    internal fun Place(host: TabOverlay) {
        val context = currentCompositionLocalContext
        DisposableEffect(host, context) {
            places[host] = context
            onDispose { places -= host }
        }
    }

    /** The layers, in the order they came. */
    @Composable
    fun Content() {
        if (remade) HoldFirstFrame()
        layers.forEachIndexed { index, layer ->
            val place = places[layer.host] ?: return@forEachIndexed
            val under = (index + 1 until layers.size).any { layers[it].host !== layer.host }
            // By the overlay's name, not the overlay, which the window made again makes anew: what a layer saves is
            // keyed by where it is composed.
            key(layer.host.name, layer.key) {
                val id = id(layer.host, layer.key)
                DisposableEffect(id) {
                    composed += id
                    onDispose { composed -= id }
                }
                val above = remember(this, layer.host, layer.key) { layersAbove(layer.host, layer.key) }
                CompositionLocalProvider(place) {
                    CompositionLocalProvider(LocalLayersAbove provides above) {
                        Box(if (under) Modifier.clearAndSetSemantics {} else Modifier) { layer.content() }
                    }
                }
            }
        }
    }

    /**
     * The window made again keeps its first frame from the screen while a layer it shows isn't composed yet, as a sheet
     * stays on the iPhone's screen through a resize: each layer comes back a frame after what shows it, so the window
     * drawn at once would show what is under them for a few frames, then them, at once. [HELD_FRAMES] at most.
     */
    @Composable
    private fun HoldFirstFrame() {
        val view = LocalView.current
        DisposableEffect(view) {
            var frames = 0
            val hold = object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    if (frames++ < HELD_FRAMES && layers.any { id(it.host, it.key) !in composed }) return false
                    view.viewTreeObserver.removeOnPreDrawListener(this)
                    return true
                }
            }
            view.viewTreeObserver.addOnPreDrawListener(hold)
            onDispose { view.viewTreeObserver.removeOnPreDrawListener(hold) }
        }
    }

    companion object {
        /** The layers' order, which the window made again puts them back in. */
        val Saver: Saver<WindowLayers, ArrayList<String>> = Saver(
            save = { window -> ArrayList(window.layers.map { window.id(it.host, it.key) }) },
            restore = { WindowLayers(it) },
        )
    }
}

/** The window's front, where the root draws every overlay's layers in the order they came; null without one. */
val LocalWindowLayers = staticCompositionLocalOf<WindowLayers?> { null }

val LocalTabOverlay = staticCompositionLocalOf<TabOverlay?> { null }

/**
 * The keys of the layers over the one composed here, in the order they came, read as they change, whichever overlay
 * showed them: a sheet under another hides its grabber, as UIKit's does. None outside a layer.
 */
val LocalLayersAbove = staticCompositionLocalOf<() -> List<Any>> { { emptyList() } }

/**
 * Whether the tab's window keys wait, read as a key comes: the first open stands over the tabs, or anything stands over
 * the window (`windowCovered`), a sheet, Add, a card's menu, an alert, a popover or a parcel's link's sheet, under which
 * the iPhone's tab keys and ⌘, do nothing. Never outside a shell.
 */
val LocalTabsCovered = staticCompositionLocalOf<() -> Boolean> { { false } }

/**
 * The overlay a layer is drawn in, for what the layer presents over the whole window in turn, as a full-screen cover
 * presented from a sheet covers all of the iPad's screen. Null outside a layer.
 */
val LocalWindowOverlay = staticCompositionLocalOf<TabOverlay?> { null }

/**
 * Whether the tab bar shows: hidden while anything in the tab asks so ([TabBarHidden]), as a parcel's page hides the
 * iPhone's with `.toolbar(.hidden, for: .tabBar)`.
 */
@Stable
class TabBarVisibility {
    private val hiders = mutableStateListOf<Any>()

    val hidden: Boolean get() = hiders.isNotEmpty()

    internal fun hide(by: Any) {
        if (by !in hiders) hiders += by
    }

    internal fun show(by: Any) {
        hiders -= by
    }
}

val LocalTabBarVisibility = staticCompositionLocalOf<TabBarVisibility?> { null }

/**
 * `.toolbar(.hidden, for: .tabBar)`: the shell's tab bar goes while `hidden`, and comes back as it stops or as this
 * leaves. It goes and comes back at once, from the frame that asks: the shell stops placing it in that frame's layout,
 * so the bar takes no touch and draws nothing, and TalkBack stops reaching it once the shell is drawn again.
 */
@Composable
fun TabBarHidden(hidden: Boolean) {
    val visibility = LocalTabBarVisibility.current ?: return
    val token = remember { Any() }
    DisposableEffect(visibility, hidden) {
        if (hidden) visibility.hide(token)
        onDispose { visibility.show(token) }
    }
}

/** Runs `onReselect` each time the tab drawing this is tapped again while chosen. */
@Composable
fun TabReselectionEffect(onReselect: suspend () -> Unit) {
    val reselection = LocalTabReselection.current
    val current by rememberUpdatedState(onReselect)
    LaunchedEffect(reselection) { reselection.events.collect { current() } }
}

/**
 * ParcelListView's TabView (A-S21): the chosen tab's content under iOS 26's floating glass tab bar, at the bottom on a
 * phone and at the top in a window at least 600 wide and 480 tall, as iPadOS puts it. Each tab keeps its saved state
 * while another is shown. A change of tab plays the selection haptic and counts the tab's screen. On a hardware
 * keyboard, Ctrl or Meta with 1, 2 or 3 chooses a tab, as ⌘1–3 do on the iPhone, while nothing stands over the tabs
 * ([LocalTabsCovered]). Android's back on Passport or Friends, at their root, chooses Deliveries (iOS's tabs have no
 * back).
 * [overlays] draws over whichever tab is chosen and whatever it presented, outside every tab's saved state, through
 * an overlay host of its own above the tab's: what the app presents over the window whatever is in front, as the
 * browser check comes up over Add's cover. While it shows, TalkBack reaches nothing under it. Under a window's front
 * ([LocalWindowLayers]) both hosts draw their layers there, among what is over the shell, in the order they all came.
 *
 * [cover] stands over the tab and its bar, whichever tab is chosen, and under what the tabs present, as the iPhone's
 * first open stands over its TabView (`.overlay { FirstOpenView }`); it keeps no room for the bar under it. While it
 * [covered] them, from the frame it does, TalkBack reaches neither the tab nor the bar
 * (`.accessibilityHidden(showsFirstOpen)`), neither takes the focus, so that Tab, the arrows and a D-pad go round the
 * cover alone, and the keys that choose a tab are off (`tabKeys`). On its way out TalkBack doesn't reach the cover.
 */
@Composable
fun TabShell(
    selection: Int,
    onSelect: (Int) -> Unit,
    labels: List<String>,
    onView: (String) -> Unit,
    modifier: Modifier = Modifier,
    covered: Boolean = false,
    cover: @Composable () -> Unit = {},
    overlays: @Composable () -> Unit = {},
    content: @Composable (PeekTab) -> Unit,
) {
    val tabs = PeekTab.entries
    val reselections = remember { tabs.map { TabReselection() } }
    SensoryFeedbackEffect(SensoryFeedback.Selection, selection)
    val viewed = remember { LastTab(selection) }
    SideEffect {
        if (viewed.tab != selection) {
            viewed.tab = selection
            onView(tabs[selection].screen)
        }
    }
    val tap = { tab: Int -> if (tab == selection) reselections[tab].reselect() else onSelect(tab) }
    val holder = rememberSaveableStateHolder()
    val backdrop = rememberMaterialBackdrop()
    // In the window's front, where there is one, among the layers of what is over the shell, in the order they came.
    val window = LocalWindowLayers.current
    val overlay = remember(backdrop, window) { TabOverlay(backdrop, window, "tab") }
    // Over the tab's layers, whichever came first, as the iPhone presents on what it presented last.
    val front = remember(backdrop, window) { TabOverlay(backdrop, window, "tab.front") }
    val visibility = remember { TabBarVisibility() }
    val density = LocalDensity.current
    val coveredNow by rememberUpdatedState(covered)
    // The tabs' keys and the account's wait under the cover and under whatever stands over the window.
    val keysWait: () -> Boolean = remember(overlay, front, window) {
        { coveredNow || overlay.covered || front.covered || window?.covered == true }
    }
    TabKeys(onSelect, keysWait)
    WindowShortcutsEffect { if (keysWait()) emptyList() else WindowKey.tabs }
    // Android's back at the root of Passport or Friends, with nothing over the tabs, goes to the deliveries, at whose
    // root it leaves the app. Composed before the tab's content, so that a friend's page there, whose back handler
    // comes after this one, takes the back first.
    BackHandler(enabled = selection != PeekTab.Deliveries.ordinal && !keysWait()) { onSelect(PeekTab.Deliveries.ordinal) }
    // Under the cover the tab and the bar take no focus: what would go into them goes into the cover instead, as a
    // modal's focus wraps round it, Tab at its top and Shift-Tab at its foot, and what had it lets go.
    val coverFocus = remember { FocusRequester() }
    val shut = Modifier
        .focusProperties {
            onEnter = {
                if (coveredNow) {
                    val into = when (requestedFocusDirection) {
                        FocusDirection.Next -> FocusDirection.Down
                        FocusDirection.Previous -> FocusDirection.Up
                        else -> requestedFocusDirection
                    }
                    if (!coverFocus.requestFocus(into)) cancelFocusChange()
                }
            }
        }
        .focusGroup()
    val focus = remember { ShellFocus() }
    val focusManager = LocalFocusManager.current
    LaunchedEffect(covered) { if (covered && (focus.tab || focus.bar)) focusManager.clearFocus() }
    BoxWithConstraints(modifier.fillMaxSize()) {
        val top = maxWidth >= 600.dp && maxHeight >= 480.dp
        // The status bar, or a desktop window's caption bar, and a cutout reaching past them.
        val safeTop = with(density) { WindowInsets.systemBars.union(WindowInsets.displayCutout).getTop(this).toDp() }
        val buttons = with(density) { WindowInsets.tappableElement.getBottom(this).toDp() }
        // Above the navigation buttons where there are some; over the gesture bar as over the iPhone's home indicator.
        val bottomGap = if (buttons > 0.dp) buttons + BUTTONS_GAP else GESTURE_GAP
        val topGap = safeTop + STATUS_GAP
        val footprint = if (top) PaddingValues(top = topGap + TOP_BAR_HEIGHT) else PaddingValues(bottom = bottomGap + BOTTOM_BAR_HEIGHT)
        val (windowHinge, levelHinge) = windowHinges(constraints.maxWidth, constraints.maxHeight)
        val hinge = windowHinge?.start
        // Under what a tab draws over the window, which is modal, as the iPhone's menus, alerts and sheets are, and
        // under the cover.
        val presented = overlay.covered || front.covered
        val hidden = if (presented || covered) Modifier.clearAndSetSemantics {} else Modifier
        Box(
            Modifier
                .fillMaxSize()
                .materialBackdrop(backdrop)
                .background(PeekTheme.colors.background)
                .then(hidden)
                .onFocusChanged { focus.tab = it.hasFocus }
                .then(shut),
        ) {
            CompositionLocalProvider(
                LocalTabBarPadding provides footprint,
                LocalTabReselection provides reselections[selection],
                LocalWindowHinge provides windowHinge,
                LocalWindowLevelHinge provides levelHinge,
                LocalTabOverlay provides overlay,
                LocalTabBarVisibility provides visibility,
                LocalTabsCovered provides keysWait,
            ) {
                holder.SaveableStateProvider(tabs[selection].name) { content(tabs[selection]) }
                CompositionLocalProvider(LocalTabOverlay provides front, content = overlays)
            }
        }
        // A bar the tab hides isn't placed, from the frame that hides it, and TalkBack doesn't reach it.
        val shown = Modifier.layout { measurable, constraints ->
            val bar = measurable.measure(constraints)
            layout(bar.width, bar.height) { if (!visibility.hidden) bar.place(0, 0) }
        }
        val barHidden = if (presented || covered || visibility.hidden) Modifier.clearAndSetSemantics {} else Modifier
        val barShut = Modifier.onFocusChanged { focus.bar = it.hasFocus }.then(shut)
        if (top) {
            TopTabBar(
                labels, selection, tap, backdrop,
                Modifier.align(Alignment.TopStart).then(shown).clearOfTheHinge(hinge).padding(top = topGap).then(barHidden).then(barShut),
            )
        } else {
            // Clear of a cutout or of navigation buttons at the side, unless a hinge already puts it in the start half.
            val sides = WindowInsets.systemBars.union(WindowInsets.displayCutout).only(WindowInsetsSides.Horizontal)
            val clear = if (hinge == null) Modifier.windowInsetsPadding(sides) else Modifier
            BottomTabBar(
                labels, selection, tap, backdrop,
                Modifier.align(Alignment.BottomStart).then(shown).then(clear).clearOfTheHinge(hinge).padding(bottom = bottomGap).then(barHidden)
                    .then(barShut),
            )
        }
        // The cover, over the tab and its bar, under what the tab presents, with what the tab is given but the bar's
        // room: the bar is under it.
        Box(
            Modifier
                .fillMaxSize()
                .then(if (presented || !covered) Modifier.clearAndSetSemantics {} else Modifier)
                .focusRequester(coverFocus)
                .focusGroup(),
        ) {
            CompositionLocalProvider(
                LocalWindowHinge provides windowHinge,
                LocalWindowLevelHinge provides levelHinge,
                LocalTabOverlay provides overlay,
                LocalTabBarVisibility provides visibility,
                content = cover,
            )
        }
        // What a layer presents over the whole window in turn comes over it, in its own layer.
        Box(if (front.covered) Modifier.clearAndSetSemantics {} else Modifier) {
            CompositionLocalProvider(LocalWindowOverlay provides overlay) { overlay.Content() }
        }
        CompositionLocalProvider(LocalWindowOverlay provides front) { front.Content() }
    }
}

/** Whether the tab or its bar holds the focus, kept across compositions without causing one. */
private class ShellFocus(var tab: Boolean = false, var bar: Boolean = false)

/**
 * The bar centred across the room it is given or, where a hinge splits the window [hinge] pixels from its left, in
 * the half before the hinge when the bar fits there. The shell fills the window, so its left is the window's.
 */
private fun Modifier.clearOfTheHinge(hinge: Int?): Modifier = layout { measurable, constraints ->
    val bar = measurable.measure(constraints.copy(minWidth = 0))
    val room = hinge?.takeIf { bar.width <= it } ?: constraints.maxWidth
    layout(constraints.maxWidth, bar.height) { bar.place((room - bar.width) / 2, 0) }
}

/** The tab an effect last acted on, kept across compositions without causing one. */
internal class LastTab(var tab: Int)

/** The tab Ctrl or Meta with 1, 2 or 3 chooses, as ⌘1–3 do on the iPhone and the Mac; null for any other key. */
internal fun shortcutTab(event: KeyEvent): Int? {
    if (!event.hasModifiers(KeyEvent.META_CTRL_ON) && !event.hasModifiers(KeyEvent.META_META_ON)) return null
    return when (event.keyCode) {
        KeyEvent.KEYCODE_1 -> 0
        KeyEvent.KEYCODE_2 -> 1
        KeyEvent.KEYCODE_3 -> 2
        else -> null
    }
}

/**
 * The shortcuts, taken once nothing in the window has used the key, whether or not anything has focus. While [waits],
 * they do nothing: the sheets, Add, the menus, the alerts and the popovers are drawn in this window, over the tabs, and
 * the iPhone's `choose` does nothing under them (`windowCovered`), nor do its tab keys under the first open.
 */
@Composable
private fun TabKeys(choose: (Int) -> Unit, waits: () -> Boolean) {
    val view = LocalView.current
    val current by rememberUpdatedState(choose)
    val covered by rememberUpdatedState(waits)
    DisposableEffect(view) {
        val listener = ViewCompat.OnUnhandledKeyEventListenerCompat { _, event ->
            if (covered()) return@OnUnhandledKeyEventListenerCompat false
            val tab = shortcutTab(event) ?: return@OnUnhandledKeyEventListenerCompat false
            if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0) current(tab)
            true
        }
        ViewCompat.addOnUnhandledKeyEventListener(view, listener)
        onDispose { ViewCompat.removeOnUnhandledKeyEventListener(view, listener) }
    }
}

/**
 * Where the hinges that split the window start and end, in pixels: an upright one from the window's left (a foldable
 * in book posture, a dual screen spanned) and a level one from its top (a foldable half open on a table). The bars keep
 * to the half before an upright one rather than straddling the fold. The app lays out left to right, so that half is
 * the left one. The page's map keeps its route and its summary on either side of either.
 *
 * The last hinges reported are kept with the window, each for a window of the same size across it, so the first frame
 * after a recreation (a change of theme, of text size, of language) already keeps clear of them rather than jumping
 * there once they are reported again.
 */
@Composable
private fun windowHinges(windowWidth: Int, windowHeight: Int): Pair<WindowHinge?, WindowLevelHinge?> {
    val activity = LocalContext.current.activity()
    var upright by rememberSaveable(stateSaver = HingeSaver) { mutableStateOf<Hinge?>(null) }
    var level by rememberSaveable(stateSaver = HingeSaver) { mutableStateOf<Hinge?>(null) }
    val width by rememberUpdatedState(windowWidth)
    val height by rememberUpdatedState(windowHeight)
    LaunchedEffect(activity) {
        if (activity == null) return@LaunchedEffect
        WindowInfoTracker.getOrCreate(activity).windowLayoutInfo(activity).collect { layout ->
            val folds = layout.displayFeatures.filterIsInstance<FoldingFeature>().filter { it.isSeparating }
            upright = folds.firstOrNull { it.orientation == FoldingFeature.Orientation.VERTICAL }
                ?.let { Hinge(it.bounds.left, it.bounds.right, width, it.state == FoldingFeature.State.HALF_OPENED) }
            level = folds.firstOrNull { it.orientation == FoldingFeature.Orientation.HORIZONTAL }
                ?.let { Hinge(it.bounds.top, it.bounds.bottom, height, it.state == FoldingFeature.State.HALF_OPENED) }
        }
    }
    return upright?.takeIf { it.window == windowWidth }?.let { WindowHinge(it.start, it.end, it.halfOpen) } to
        level?.takeIf { it.window == windowHeight }?.let { WindowLevelHinge(it.start, it.end, it.halfOpen) }
}

/**
 * A hinge's start and end from the window's left, or its top for a level one, the window's size across it (its
 * width, or its height), in pixels, and whether the device stands half open there.
 */
private data class Hinge(val start: Int, val end: Int, val window: Int, val halfOpen: Boolean)

private val HingeSaver = Saver<Hinge?, IntArray>(
    save = { hinge -> hinge?.let { intArrayOf(it.start, it.end, it.window, if (it.halfOpen) 1 else 0) } },
    restore = { Hinge(it[0], it[1], it[2], it.getOrElse(3) { 0 } == 1) },
)

private tailrec fun Context.activity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.activity()
    else -> null
}

/**
 * How many frames the window made again keeps from the screen at most, waiting for its layers: a sheet comes back in
 * the window's second frame, one presented from it in the third, and a layer that never comes back keeps it no longer.
 */
private const val HELD_FRAMES = 6

/** The bottom bar's bottom edge over the gesture bar, as the iPhone's tab bar stands over the home indicator. */
private val GESTURE_GAP = 21.dp

/** The bottom bar's bottom edge over the navigation buttons. */
private val BUTTONS_GAP = 8.dp

/** The top bar's top edge under the status bar, iPadOS's. */
private val STATUS_GAP = 7.5.dp
