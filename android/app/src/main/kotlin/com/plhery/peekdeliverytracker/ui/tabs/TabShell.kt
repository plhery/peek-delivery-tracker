package com.plhery.peekdeliverytracker.ui.tabs

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.KeyEvent
import androidx.compose.foundation.background
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.core.view.ViewCompat
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
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
 * and ends, in pixels from the window's left.
 */
@Immutable
data class WindowHinge(val start: Int, val end: Int)

/** The hinge splitting the tab's window, null where none does. */
val LocalWindowHinge = compositionLocalOf<WindowHinge?> { null }

/** The reselection of the tab being drawn, or of what its stack shows at its root. */
val LocalTabReselection = staticCompositionLocalOf { TabReselection() }

/**
 * What a tab draws over the whole window, its tab bar and the system's bars too, as an iPhone's context menu dims all
 * of the screen and a sheet comes up over the tab bar: the shell draws each layer last, in the order they came, and
 * while one shows TalkBack reaches nothing under them. [backdrop] is what the shell's tab draws, for a layer's glass
 * to show through. Null outside a shell, where a tab draws its layers over itself.
 */
@Stable
class TabOverlay(val backdrop: MaterialBackdrop? = null) {
    private val layers = mutableStateListOf<Pair<Any, @Composable () -> Unit>>()

    /** Draws `content` as the layer `key`, in its place if it shows already, or takes the layer away with null. */
    fun show(key: Any, content: (@Composable () -> Unit)?) {
        val index = layers.indexOfFirst { it.first == key }
        when {
            content == null -> if (index >= 0) layers.removeAt(index)
            index >= 0 -> layers[index] = key to content
            else -> layers += key to content
        }
    }

    /** Whether a layer shows. */
    val covered: Boolean get() = layers.isNotEmpty()

    /** The layers, in the order they came. */
    @Composable
    fun Content() {
        for ((key, content) in layers) key(key) { content() }
    }
}

val LocalTabOverlay = staticCompositionLocalOf<TabOverlay?> { null }

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
 * keyboard, Ctrl or Meta with 1, 2 or 3 chooses a tab, as ⌘1–3 do on the iPhone, while this window has the keys.
 */
@Composable
fun TabShell(
    selection: Int,
    onSelect: (Int) -> Unit,
    labels: List<String>,
    onView: (String) -> Unit,
    modifier: Modifier = Modifier,
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
    TabKeys(onSelect)
    val tap = { tab: Int -> if (tab == selection) reselections[tab].reselect() else onSelect(tab) }
    val holder = rememberSaveableStateHolder()
    val backdrop = rememberMaterialBackdrop()
    val overlay = remember(backdrop) { TabOverlay(backdrop) }
    val visibility = remember { TabBarVisibility() }
    val density = LocalDensity.current
    BoxWithConstraints(modifier.fillMaxSize()) {
        val top = maxWidth >= 600.dp && maxHeight >= 480.dp
        // The status bar, or a desktop window's caption bar, and a cutout reaching past them.
        val safeTop = with(density) { WindowInsets.systemBars.union(WindowInsets.displayCutout).getTop(this).toDp() }
        val buttons = with(density) { WindowInsets.tappableElement.getBottom(this).toDp() }
        // Above the navigation buttons where there are some; over the gesture bar as over the iPhone's home indicator.
        val bottomGap = if (buttons > 0.dp) buttons + BUTTONS_GAP else GESTURE_GAP
        val topGap = safeTop + STATUS_GAP
        val footprint = if (top) PaddingValues(top = topGap + TOP_BAR_HEIGHT) else PaddingValues(bottom = bottomGap + BOTTOM_BAR_HEIGHT)
        val windowHinge = windowHinge(constraints.maxWidth)
        val hinge = windowHinge?.start
        // Under what a tab draws over the window, which is modal, as the iPhone's menus, alerts and sheets are.
        val hidden = if (overlay.covered) Modifier.clearAndSetSemantics {} else Modifier
        Box(Modifier.fillMaxSize().materialBackdrop(backdrop).background(PeekTheme.colors.background).then(hidden)) {
            CompositionLocalProvider(
                LocalTabBarPadding provides footprint,
                LocalTabReselection provides reselections[selection],
                LocalWindowHinge provides windowHinge,
                LocalTabOverlay provides overlay,
                LocalTabBarVisibility provides visibility,
            ) {
                holder.SaveableStateProvider(tabs[selection].name) { content(tabs[selection]) }
            }
        }
        // A bar the tab hides isn't placed, from the frame that hides it, and TalkBack doesn't reach it.
        val shown = Modifier.layout { measurable, constraints ->
            val bar = measurable.measure(constraints)
            layout(bar.width, bar.height) { if (!visibility.hidden) bar.place(0, 0) }
        }
        val barHidden = if (overlay.covered || visibility.hidden) Modifier.clearAndSetSemantics {} else Modifier
        if (top) {
            TopTabBar(
                labels, selection, tap, backdrop,
                Modifier.align(Alignment.TopStart).then(shown).clearOfTheHinge(hinge).padding(top = topGap).then(barHidden),
            )
        } else {
            // Clear of a cutout or of navigation buttons at the side, unless a hinge already puts it in the start half.
            val sides = WindowInsets.systemBars.union(WindowInsets.displayCutout).only(WindowInsetsSides.Horizontal)
            val clear = if (hinge == null) Modifier.windowInsetsPadding(sides) else Modifier
            BottomTabBar(
                labels, selection, tap, backdrop,
                Modifier.align(Alignment.BottomStart).then(shown).then(clear).clearOfTheHinge(hinge).padding(bottom = bottomGap).then(barHidden),
            )
        }
        overlay.Content()
    }
}

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
 * The shortcuts, taken once nothing in the window has used the key, whether or not anything has focus. A dialog or
 * a sheet is a window of its own and keeps its keys, so they don't reach the tabs under it.
 */
@Composable
private fun TabKeys(choose: (Int) -> Unit) {
    val view = LocalView.current
    val current by rememberUpdatedState(choose)
    DisposableEffect(view) {
        val listener = ViewCompat.OnUnhandledKeyEventListenerCompat { _, event ->
            val tab = shortcutTab(event) ?: return@OnUnhandledKeyEventListenerCompat false
            if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0) current(tab)
            true
        }
        ViewCompat.addOnUnhandledKeyEventListener(view, listener)
        onDispose { ViewCompat.removeOnUnhandledKeyEventListener(view, listener) }
    }
}

/**
 * Where a hinge starts and ends, in pixels from the window's left, when it stands upright and splits the window: a
 * foldable in book posture or a dual screen spanned. The bars keep to the half before it rather than straddling the
 * fold. The app lays out left to right, so that half is the left one.
 *
 * The last hinge reported is kept with the window, for a window of the same width, so the first frame after a
 * recreation (a change of theme, of text size, of language) already keeps clear of it rather than jumping there once
 * the hinge is reported again.
 */
@Composable
private fun windowHinge(windowWidth: Int): WindowHinge? {
    val activity = LocalContext.current.activity()
    var reported by rememberSaveable(stateSaver = HingeSaver) { mutableStateOf<Hinge?>(null) }
    val width by rememberUpdatedState(windowWidth)
    LaunchedEffect(activity) {
        if (activity == null) return@LaunchedEffect
        WindowInfoTracker.getOrCreate(activity).windowLayoutInfo(activity).collect { layout ->
            val fold = layout.displayFeatures.filterIsInstance<FoldingFeature>()
                .firstOrNull { it.isSeparating && it.orientation == FoldingFeature.Orientation.VERTICAL }
            reported = fold?.let { Hinge(it.bounds.left, it.bounds.right, width) }
        }
    }
    return reported?.takeIf { it.windowWidth == windowWidth }?.let { WindowHinge(it.start, it.end) }
}

/** A hinge's start and end from the window's left, and the width of the window it splits, in pixels. */
private data class Hinge(val start: Int, val end: Int, val windowWidth: Int)

private val HingeSaver = Saver<Hinge?, IntArray>(
    save = { hinge -> hinge?.let { intArrayOf(it.start, it.end, it.windowWidth) } },
    restore = { Hinge(it[0], it[1], it[2]) },
)

private tailrec fun Context.activity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.activity()
    else -> null
}

/** The bottom bar's bottom edge over the gesture bar, as the iPhone's tab bar stands over the home indicator. */
private val GESTURE_GAP = 21.dp

/** The bottom bar's bottom edge over the navigation buttons. */
private val BUTTONS_GAP = 8.dp

/** The top bar's top edge under the status bar, iPadOS's. */
private val STATUS_GAP = 7.5.dp
