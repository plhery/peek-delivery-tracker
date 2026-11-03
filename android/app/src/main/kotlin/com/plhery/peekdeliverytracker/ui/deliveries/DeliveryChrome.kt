package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
import android.view.KeyEvent
import android.view.accessibility.AccessibilityManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.withContext
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.FirstOpenJourney
import com.plhery.peekdeliverytracker.core.FirstParcelRequest
import com.plhery.peekdeliverytracker.core.ParcelSort
import com.plhery.peekdeliverytracker.core.ParcelStatusFilter
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.MotionCurve
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.NoSuggestions
import com.plhery.peekdeliverytracker.ui.WindowKey
import com.plhery.peekdeliverytracker.ui.WindowShortcutsEffect
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.PeekMark
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.symbols.SymbolRenderingMode
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabBarPadding
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabsCovered
import com.plhery.peekdeliverytracker.ui.tabs.TOP_BAR_HEIGHT
import com.plhery.peekdeliverytracker.ui.theme.CappedDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.KeepMotion
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.MinimumScale
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.ProvideDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.zoom.ZoomTransition
import com.plhery.peekdeliverytracker.ui.zoom.fadingUnderZoom
import com.plhery.peekdeliverytracker.ui.zoom.givingWayToPageBar
import com.plhery.peekdeliverytracker.ui.zoom.zoomSource

/** The localized text for `key`, written again when the language changes. */
@Composable
internal fun localized(key: String): String {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    return remember(key, localizer, language) { localizer.text(key) }
}

/**
 * Where the bar's row stands: under the status bar, or 10 under a tab bar at the top, as the iPad's does under its
 * tabs.
 */
@Composable
internal fun barRowTop(): Dp {
    val tabTop = LocalTabBarPadding.current.calculateTopPadding()
    if (tabTop > 0.dp) return tabTop + 10.dp
    return WindowInsets.systemBars.union(WindowInsets.displayCutout).asPaddingValues().calculateTopPadding()
}

/**
 * The list's bar, `.navigationBarTitleDisplayMode(.inline)` with its items. Stacked: Peek's mark at the leading edge on
 * its own, adding a parcel on its glass beside it, "Deliveries" in the window's middle and the account at the trailing
 * edge. Beside a parcel: the mark and the title, in the middle of the list's column; adding and the account stand over
 * the parcel ([PageToolbar]). The glass shows the list scrolling under it ([backdrop]). As a parcel's page zooms over
 * the list, the title fades with the list's coming under it and comes back as the page shrinks off it, and the other
 * items give way to the page's ‹ and ••• ([givingWayToPageBar]).
 */
@Composable
internal fun ListToolbar(
    state: DeliveriesState,
    sideBySide: Boolean,
    rowTop: Dp,
    start: Dp,
    end: Dp,
    backdrop: MaterialBackdrop,
    modifier: Modifier = Modifier,
) {
    val title = localized("native.deliveries")
    Box(modifier.fillMaxWidth().padding(top = rowTop).height(ROW_HEIGHT)) {
        // The mark stands on its own, not on a control's glass.
        PeekMark(
            30.dp,
            Modifier.align(Alignment.CenterStart).offset(x = start + if (sideBySide) 17.dp else 27.dp).givingWayToPageBar(state.zoom).clearAndSetSemantics {},
        )
        // Centred on the bar, clear of its items on either side, at the default text size whatever the reader's, as
        // UIKit's inline title stands.
        val clear = if (sideBySide) 56.dp else 128.dp
        ProvideDynamicTypeSize(DynamicTypeSize.Large) {
            Text(
                title,
                Modifier.align(Alignment.Center).padding(start = start + clear, end = end + clear).fadingUnderZoom(state.zoom).semantics { heading() },
                color = PeekTheme.colors.label,
                style = PeekTheme.type.headline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (!sideBySide) {
            GlassSurface(
                CapsuleShape,
                Modifier.align(Alignment.CenterStart).offset(x = start + ADD_X).givingWayToPageBar(state.zoom).size(ADD_GLASS_WIDTH, ROW_HEIGHT),
                backdrop,
            ) {
                AddButton(state, Modifier.align(Alignment.Center))
            }
            GlassSurface(
                CapsuleShape,
                Modifier.align(Alignment.CenterEnd).offset(x = -(end + ACCOUNT_MARGIN)).givingWayToPageBar(state.zoom).size(ACCOUNT_GLASS_WIDTH, ROW_HEIGHT),
                backdrop,
            ) {
                AccountButton(state, Modifier.align(Alignment.Center))
            }
        }
    }
}

/**
 * Beside a parcel, adding one and the account on one glass at the window's trailing edge, over the parcel's page, as
 * the iPad's detail toolbar has them, and the parcel's ••• menu after them while a parcel of the deliveries shows
 * (ParcelDetailView's `.topBarTrailing` item): none for the sample, nor while no parcel shows.
 */
@Composable
internal fun PageToolbar(state: DeliveriesState, backdrop: MaterialBackdrop?) {
    val end = if (LocalColumnEdges.current.end) {
        WindowInsets.systemBars.union(WindowInsets.displayCutout).asPaddingValues().calculateEndPadding(LocalLayoutDirection.current)
    } else {
        0.dp
    }
    val shown = state.navigator.navigation.shown
    val parcel = state.liveMenuParcel(shown)
    Box(Modifier.fillMaxWidth().padding(top = barRowTop()).height(ROW_HEIGHT)) {
        GlassSurface(
            CapsuleShape,
            Modifier
                .align(Alignment.CenterEnd)
                .offset(x = -(end + PAGE_TOOLBAR_MARGIN))
                .size(if (parcel != null) PAGE_TOOLBAR_MENU_WIDTH else PAGE_TOOLBAR_WIDTH, ROW_HEIGHT),
            backdrop,
        ) {
            AddButton(state, Modifier.align(Alignment.CenterStart).offset(x = PAGE_ADD_CENTRE - BUTTON_SIDE / 2))
            AccountButton(state, Modifier.align(Alignment.CenterStart).offset(x = PAGE_ACCOUNT_CENTRE - BUTTON_SIDE / 2))
            if (parcel != null && shown != null) {
                PageMenuButton(state, shown, Modifier.align(Alignment.CenterStart).offset(x = PAGE_MENU_CENTRE - BUTTON_SIDE / 2))
            }
        }
    }
}

/**
 * `addButton`: a plus, semibold at 17 points whatever the text size, in the ink, which opens Add
 * ([DeliveriesState.add]), as text or a link dropped on it does ([parcelDrop]); TalkBack comes back to it once Add has
 * gone.
 */
@Composable
private fun AddButton(state: DeliveriesState, modifier: Modifier) {
    val label = localized("app.addParcelAria")
    val focus = remember { AccessibilityFocus() }
    Box(
        modifier
            .size(BUTTON_SIDE)
            .testTag(ADD_BUTTON)
            .accessibilityFocus(focus)
            .parcelDrop(state)
            .clickable(interactionSource = null, indication = PressStyle.tactile(0.94f)) { state.add(focus) }
            .semantics {
                contentDescription = label
                role = Role.Button
            },
        contentAlignment = Alignment.Center,
    ) {
        PeekSymbol("plus", font = PeekTheme.type.fixed(17f, FontWeight.SemiBold), tint = PeekTheme.colors.ink)
    }
}

/**
 * A tab's bar with only its inline title and `AccountToolbarItem`, placed as the list's bar places them
 * ([ListToolbar]): the passport's, whose account button, tagged [accountTag], opens its own Settings. Where a fold
 * stands half open [titleWidth] from the window's leading edge (in a window too narrow or too short for the tab bar at
 * the top, where [TabRowAccount] stands instead), the title centres over that first half, clear of it. Under a page
 * that zooms over the tab ([zoom]), the title fades as the list's does and the account gives way to the page's bar.
 */
@Composable
internal fun AccountBar(
    title: String,
    rowTop: Dp,
    start: Dp,
    end: Dp,
    backdrop: MaterialBackdrop,
    session: StateFlow<AuthenticationState>,
    onAccount: () -> Unit,
    accountTag: String,
    modifier: Modifier = Modifier,
    titleWidth: Dp? = null,
    zoom: ZoomTransition? = null,
) {
    Box(modifier.fillMaxWidth().padding(top = rowTop).height(ROW_HEIGHT)) {
        val place = if (titleWidth != null) {
            Modifier.align(Alignment.CenterStart).width(titleWidth).padding(start = start + FOLD_TITLE_MARGIN, end = FOLD_TITLE_MARGIN)
        } else {
            Modifier.align(Alignment.Center).padding(start = start + 128.dp, end = end + 128.dp)
        }
        ProvideDynamicTypeSize(DynamicTypeSize.Large) {
            Text(
                title,
                place.then(if (zoom != null) Modifier.fadingUnderZoom(zoom) else Modifier).semantics { heading() },
                color = PeekTheme.colors.label,
                style = PeekTheme.type.headline,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        GlassSurface(
            CapsuleShape,
            Modifier
                .align(Alignment.CenterEnd)
                .offset(x = -(end + ACCOUNT_MARGIN))
                .then(if (zoom != null) Modifier.givingWayToPageBar(zoom) else Modifier)
                .size(ACCOUNT_GLASS_WIDTH, ROW_HEIGHT),
            backdrop,
        ) {
            AccountButton(session, onAccount, accountTag, Modifier.align(Alignment.Center))
        }
    }
}

/** How far an [AccountBar]'s title keeps from the edges of the half before a fold. */
private val FOLD_TITLE_MARGIN = 16.dp

/**
 * Where the tab bar stands at the top, a tab's `AccountToolbarItem` as the iPad places it with no title: the account's
 * glass in the tab bar's row, centred on it, 10 in from the window's trailing edge and [end], clear of the system's
 * bars and cutout there (ipad-07). It opens the tab's own Settings ([onAccount]); its button is tagged [accountTag].
 * Under a page that zooms over the tab ([zoom]), it gives way to the page's bar.
 */
@Composable
internal fun TabRowAccount(
    end: Dp,
    backdrop: MaterialBackdrop,
    session: StateFlow<AuthenticationState>,
    onAccount: () -> Unit,
    accountTag: String,
    modifier: Modifier = Modifier,
    zoom: ZoomTransition? = null,
) {
    val rowTop = LocalTabBarPadding.current.calculateTopPadding() - TOP_BAR_HEIGHT
    Box(modifier.fillMaxWidth().padding(top = rowTop).height(TOP_BAR_HEIGHT)) {
        GlassSurface(
            CapsuleShape,
            Modifier
                .align(Alignment.CenterEnd)
                .offset(x = -(end + TAB_ROW_ACCOUNT_MARGIN))
                .then(if (zoom != null) Modifier.givingWayToPageBar(zoom) else Modifier)
                .size(TAB_ROW_ACCOUNT_WIDTH, ROW_HEIGHT),
            backdrop,
        ) {
            AccountButton(session, onAccount, accountTag, Modifier.align(Alignment.Center))
        }
    }
}

/** The iPad's account glass beside its tab bar: 52 wide, 10 from the window's edge. */
private val TAB_ROW_ACCOUNT_WIDTH = 52.dp
private val TAB_ROW_ACCOUNT_MARGIN = 10.dp

/**
 * The list's account button, which opens Settings over the deliveries ([DeliveriesState.account]); its key reaches it
 * while the deliveries' keys do (`.environment(\.keyboardInFront, keyboardFree)`).
 */
@Composable
private fun AccountButton(state: DeliveriesState, modifier: Modifier) =
    AccountButton(state.source.session, state::account, ACCOUNT_BUTTON, modifier, inFront = { state.keyboardFree })

/**
 * `AccountToolbarButton`: the account's initial, or a figure, on a 34-point yellow disc, which opens Settings. Ctrl or
 * Meta with a comma presses it, as ⌘, does, while its screen has the keys ([inFront]), and does nothing while anything
 * stands over the tabs ([LocalTabsCovered]), as the iPhone's `open()` waits while `windowCovered`.
 */
@Composable
private fun AccountButton(
    session: StateFlow<AuthenticationState>,
    onClick: () -> Unit,
    tag: String,
    modifier: Modifier,
    inFront: () -> Boolean = { true },
) {
    AccountKey(onClick, inFront)
    val state by session.collectAsState()
    val initial = (state as? AuthenticationState.SignedIn)?.user?.email?.firstOrNull()?.uppercase()
    val label = localized("native.account")
    val colors = PeekTheme.colors
    Box(
        modifier
            .size(BUTTON_SIDE)
            .testTag(tag)
            .clickable(interactionSource = null, indication = PressStyle.tactile(0.94f), onClick = onClick)
            .semantics {
                contentDescription = label
                role = Role.Button
            },
        contentAlignment = Alignment.Center,
    ) {
        Box(Modifier.size(34.dp).background(colors.accent, CircleShape), contentAlignment = Alignment.Center) {
            if (initial != null) {
                Text(initial, color = colors.onAccent, style = PeekTheme.type.style(IosTextStyle.Subheadline, FontWeight.Bold))
            } else {
                PeekSymbol("person.fill", font = PeekTheme.type.style(IosTextStyle.Subheadline, FontWeight.SemiBold), tint = colors.onAccent)
            }
        }
    }
}

/** The account's key, Ctrl or Meta with a comma (`MenuKey.account`), taken once nothing in the window has used it. */
@Composable
private fun AccountKey(open: () -> Unit, inFront: () -> Boolean) {
    val view = LocalView.current
    val action by rememberUpdatedState(open)
    val front by rememberUpdatedState(inFront)
    val covered by rememberUpdatedState(LocalTabsCovered.current)
    DisposableEffect(view) {
        val listener = ViewCompat.OnUnhandledKeyEventListenerCompat { _, event ->
            val command = event.hasModifiers(KeyEvent.META_CTRL_ON) || event.hasModifiers(KeyEvent.META_META_ON)
            if (!command || event.keyCode != KeyEvent.KEYCODE_COMMA || !front()) return@OnUnhandledKeyEventListenerCompat false
            if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0 && !covered()) action()
            true
        }
        ViewCompat.addOnUnhandledKeyEventListener(view, listener)
        onDispose { ViewCompat.removeOnUnhandledKeyEventListener(view, listener) }
    }
    WindowShortcutsEffect { if (front() && !covered()) listOf(WindowKey.Account) else emptyList() }
}

/**
 * `DemoModeBar`: under the bar in the demo, what this is and how to leave it, on the cream with a faint rule under it.
 * It keeps to one line as the system's bars do: at the largest sizes it says only how to leave. `sides` keeps its words
 * clear of the window's bars and cutout.
 */
@Composable
internal fun DemoModeBar(onExit: () -> Unit, modifier: Modifier = Modifier, sides: PaddingValues = PaddingValues()) {
    val colors = PeekTheme.colors
    val demo = localized("app.demo")
    val exit = localized("native.exitDemo")
    Box(
        modifier
            .fillMaxWidth()
            .background(colors.cream)
            .drawBehind {
                val line = 1f
                drawRect(colors.separator.copy(alpha = colors.separator.alpha * 0.45f), Offset(0f, size.height - line), size.copy(height = line))
            },
    ) {
        CappedDynamicTypeSize(DynamicTypeSize.Accessibility1) {
            Box(
                Modifier
                    .testTag(DEMO_EXIT)
                    .clickable(interactionSource = null, indication = PressStyle.tactile(0.995f), onClick = onExit)
                    .semantics(mergeDescendants = false) {
                        contentDescription = "$demo, $exit"
                        role = Role.Button
                    }
                    .padding(sides)
                    .padding(horizontal = 20.dp, vertical = 6.dp)
                    .heightIn(min = DEMO_BAR_HEIGHT - 12.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart,
            ) {
                FirstThatFits(
                    Modifier.clearAndSetSemantics {},
                    { DemoBarLine(demo, exit, named = true) },
                    { DemoBarLine(demo, exit, named = false) },
                )
            }
        }
    }
}

@Composable
private fun DemoBarLine(demo: String, exit: String, named: Boolean) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        if (named) {
            Text(demo, color = colors.secondaryLabel, style = type.style(IosTextStyle.Caption, FontWeight.Medium), maxLines = 1)
            Spacer(Modifier.width(12.dp))
        }
        Spacer(Modifier.widthIn(min = 8.dp).weight(1f))
        Spacer(Modifier.width(12.dp))
        Text(exit, color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold), maxLines = 1)
        Spacer(Modifier.width(12.dp))
        PeekSymbol("xmark.circle.fill", font = type.title3, renderingMode = SymbolRenderingMode.Hierarchical, tint = colors.ink)
    }
}

/**
 * `listOverview`: "On the way" and its count while parcels are on their way (in the view the list shows), then the
 * search's button, marked while the search shows or the list shows a custom view, and the refresh button where there
 * is nothing to pull with (beside a parcel) or for TalkBack. The search's button opens the search and focuses its
 * field, or closes it and drops the focus. While the parcels are checked, the refresh arrow turns where it is, or
 * pulses under Reduce Motion.
 */
@Composable
internal fun OverviewRow(state: DeliveriesState, active: Int, change: ListChange?, refreshes: Boolean) {
    val colors = PeekTheme.colors
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    Row(Modifier.fillMaxWidth().heightIn(min = BUTTON_SIDE), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        // The words and the count take what the buttons leave, the words giving way first.
        Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            if (active > 0) {
                // At the accessibility sizes the heading gets a little smaller rather than break in two.
                val style = PeekTheme.type.headline
                val probe = LocalOnTheWayHeading.current
                Text(
                    localized("app.onTheWaySection"),
                    Modifier.weight(1f, fill = false).semantics { heading() },
                    color = colors.ink,
                    style = style,
                    maxLines = if (largeText) 1 else Int.MAX_VALUE,
                    overflow = TextOverflow.Ellipsis,
                    autoSize = if (largeText) MinimumScale(style.fontSize, HEADING_MINIMUM_SCALE) else null,
                    onTextLayout = { probe?.invoke(it) },
                )
                CountPill(active, horizontalPadding = 6.dp, change, Modifier.testTag(ACTIVE_COUNT))
            }
        }
        val searchLabel = localized(if (state.showingSearch) "view.hideControls" else "view.showControls")
        val marked = state.showingSearch || state.hasCustomView
        Box(
            Modifier
                .size(BUTTON_SIDE)
                .testTag(SEARCH_BUTTON)
                .background(if (marked) colors.ink.copy(alpha = 0.06f) else Color.Transparent, CircleShape)
                .clickable(interactionSource = null, indication = PlainPress) {
                    if (state.showingSearch) state.closeSearch() else state.openSearch()
                }
                .semantics {
                    contentDescription = searchLabel
                    role = Role.Button
                },
            contentAlignment = Alignment.Center,
        ) {
            PeekSymbol("magnifyingglass", font = PeekTheme.type.body, tint = colors.ink)
        }
        if (refreshes) RefreshButton(state)
    }
}

@Composable
private fun RefreshButton(state: DeliveriesState) {
    val refreshing by state.source.refreshing.collectAsState()
    val reduceMotion = PeekTheme.reduceMotion
    val label = localized(if (refreshing) "app.refreshing" else "app.refresh")
    Box(
        Modifier
            .size(BUTTON_SIDE)
            .testTag(REFRESH_BUTTON)
            .clickable(interactionSource = null, indication = PlainPress) {
                if (!refreshing) state.refreshFromButton()
            }
            .semantics {
                contentDescription = label
                role = Role.Button
            },
        contentAlignment = Alignment.Center,
    ) {
        PeekSymbol(
            "arrow.clockwise",
            if (refreshing) Modifier.refreshing(reduceMotion) else Modifier,
            font = PeekTheme.type.body,
            tint = PeekTheme.colors.ink,
        )
    }
}

/**
 * A refresh arrow while it checks, the list's or a parcel page's: `.symbolEffect(.rotate)`, a turn a second, or
 * `.pulse` under Reduce Motion, the arrow fading to a third and back. Only then, so that the window rests the rest of
 * the time. iOS pulses under Reduce Motion, which is Android's "Remove animations", so the effect runs whatever the
 * animator duration scale ([KeepMotion]).
 */
@Composable
internal fun Modifier.refreshing(reduceMotion: Boolean): Modifier {
    val effect = remember(reduceMotion) { Animatable(if (reduceMotion) 1f else 0f) }
    LaunchedEffect(effect) {
        withContext(KeepMotion) {
            if (reduceMotion) {
                effect.animateTo(0.35f, infiniteRepeatable(tween(750), RepeatMode.Reverse))
            } else {
                effect.animateTo(360f, infiniteRepeatable(tween(1000, easing = LinearEasing)))
            }
        }
    }
    return graphicsLayer { if (reduceMotion) alpha = effect.value else rotationZ = effect.value }
}

/**
 * `searchControls` (A-S27, A-G09): the search field in one quiet capsule, the glass before it and, once it holds
 * something, the button that empties it; and the filters' button beside it. The field takes the focus as it shows
 * ([DeliveriesState.openSearch]); it doesn't capitalise, correct or suggest what is typed ([NoSuggestions]), and the
 * keyboard's Search ends the editing. Its trailing room narrows from 13 to 5 as the clear button comes, which fades and
 * grows in from 0.7, on a snappy 0.2 s (at once under Reduce Motion). The filters' button takes the focus from the
 * field, then opens them.
 */
@Composable
internal fun SearchControls(state: DeliveriesState) {
    val colors = PeekTheme.colors
    val font = PeekTheme.type.subheadline
    val reduceMotion = PeekTheme.reduceMotion
    val focus = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val query = state.query
    val label = localized("view.search")
    val clear = localized("view.clear")
    val filters = localized("view.showControls")
    val motion = Motion.snappy<Float>(SEARCH_FIELD_MOTION)
    val trailing by animateDpAsState(
        if (query.isEmpty()) 13.dp else 5.dp,
        if (reduceMotion) snap() else Motion.snappy(SEARCH_FIELD_MOTION),
        label = "trailing",
    )
    // `.onAppear { searchFocused = true }` as the search opens, and again in the window made again (a rotation) that
    // found it focused: the field going with the old window keeps the focus for the new one, and the new one coming in
    // unfocused leaves it as it was until it has taken it. And again as the list comes back from under a parcel's
    // page, which took the focus from it as it came ([ZoomStack]), as the iPhone's list appears again.
    val activity = LocalActivity.current
    val windowMadeAgain = { activity?.isChangingConfigurations == true }
    val refocus = remember { mutableStateOf(state.searchFocused) }
    LaunchedEffect(state.focusRequest) {
        when (state.takeFocusRequest()) {
            true -> focus.requestFocus()
            false -> if (state.searchFocused) focusManager.clearFocus()
            null -> Unit
        }
    }
    LaunchedEffect(Unit) {
        if (refocus.value) focus.requestFocus()
        refocus.value = false
    }
    LaunchedEffect(state.zoom) {
        snapshotFlow { state.zoom.covered != null }.drop(1).collect { covered -> if (!covered) focus.requestFocus() }
    }
    DisposableEffect(Unit) { onDispose { if (!windowMadeAgain()) state.searchFocused = false } }
    Row(Modifier.fillMaxWidth().testTag(SEARCH_ROW), horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
        Row(
            Modifier
                .weight(1f)
                .heightIn(min = BUTTON_SIDE)
                .background(colors.ink.copy(alpha = 0.06f), CapsuleShape)
                .padding(start = 13.dp, end = trailing),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PeekSymbol("magnifyingglass", Modifier.clearAndSetSemantics {}, font = font, tint = colors.secondaryLabel)
            NoSuggestions {
                BasicTextField(
                    query,
                    onValueChange = { state.query = it },
                    modifier = Modifier
                        .weight(1f)
                        .testTag(SEARCH_FIELD)
                        .focusRequester(focus)
                        .onFocusChanged { if (it.isFocused || !refocus.value && !windowMadeAgain()) state.searchFocused = it.isFocused }
                        .semantics { contentDescription = label },
                    textStyle = font.copy(color = colors.label),
                    singleLine = true,
                    cursorBrush = SolidColor(colors.ink),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrectEnabled = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search,
                    ),
                    keyboardActions = KeyboardActions(onSearch = { state.focusSearch(false) }),
                    decorationBox = { field ->
                        Box(contentAlignment = Alignment.CenterStart) {
                            if (query.isEmpty()) {
                                Text(
                                    localized("view.searchPlaceholder"), Modifier.clearAndSetSemantics {}, color = colors.tertiaryLabel, style = font,
                                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                                )
                            }
                            field()
                        }
                    },
                )
            }
            AnimatedVisibility(
                visible = query.isNotEmpty(),
                enter = if (reduceMotion) EnterTransition.None else fadeIn(motion) + scaleIn(motion, 0.7f),
                exit = if (reduceMotion) ExitTransition.None else fadeOut(motion) + scaleOut(motion, 0.7f),
            ) {
                Box(
                    Modifier
                        .size(30.dp, BUTTON_SIDE)
                        .testTag(SEARCH_CLEAR)
                        .clickable(interactionSource = null, indication = PlainPress) { state.query = "" }
                        .semantics {
                            contentDescription = clear
                            role = Role.Button
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    PeekSymbol("xmark.circle.fill", font = font, tint = colors.tertiaryLabel)
                }
            }
        }
        Box(
            Modifier
                .size(BUTTON_SIDE)
                .testTag(FILTERS_BUTTON)
                .onGloballyPositioned { state.filtersAnchor = it.boundsInWindow() }
                .accessibilityFocus(state.filtersButton)
                .clickable(interactionSource = null, indication = PlainPress, onClick = state::openFilters)
                .semantics {
                    contentDescription = filters
                    role = Role.Button
                },
            contentAlignment = Alignment.Center,
        ) {
            PeekSymbol("line.3.horizontal.decrease", font = font, tint = colors.ink)
        }
    }
}

/**
 * `filterChips` (A-S28), while the list shows a custom view: a chip for the query (in quotes), the status, the carrier
 * and the sort that aren't the list's own, each of which a tap undoes, then Clear all; in a row that scrolls sideways.
 */
@Composable
internal fun FilterChips(state: DeliveriesState) {
    val colors = PeekTheme.colors
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current
    val query = state.query
    val status = state.statusFilter
    val carrier = state.carrierFilter
    val sort = state.sort
    Row(
        Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).testTag(FILTER_CHIPS),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (query.isNotBlank()) FilterChip("\u201C$query\u201D", "query") { state.query = "" }
        if (status != ParcelStatusFilter.All) FilterChip(localized(status.localizationKey), "status") { state.statusFilter = ParcelStatusFilter.All }
        if (carrier != null) {
            val name = identities?.displayName(carrier, language) ?: CarrierCatalog.shared.info(carrier, language).displayName
            FilterChip(name, "carrier") { state.carrierFilter = null }
        }
        if (sort != ParcelSort.Priority) FilterChip(localized(sort.localizationKey), "sort") { state.sort = ParcelSort.Priority }
        // As tall as a chip, so that the row is as tall as the iPhone's, and Clear all as easy to tap as a chip.
        Box(
            Modifier
                .heightIn(min = CHIP_HEIGHT)
                .testTag(CLEAR_ALL)
                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = state::clearFilters)
                .padding(horizontal = 5.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(localized("view.clearAll"), color = colors.secondaryLabel, style = PeekTheme.type.style(IosTextStyle.Caption, FontWeight.SemiBold), maxLines = 1)
        }
    }
}

/** `filterChip`: its words and a cross, caption semibold, in the ink on a faint capsule at least 34 high. */
@Composable
private fun FilterChip(title: String, id: String, action: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    Row(
        Modifier
            .testTag("$FILTER_CHIPS.$id")
            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = action)
            .heightIn(min = CHIP_HEIGHT)
            .background(colors.ink.copy(alpha = 0.06f), CapsuleShape)
            .padding(horizontal = 11.dp, vertical = if (largeText) 6.dp else 0.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, color = colors.ink, style = type.style(IosTextStyle.Caption, FontWeight.SemiBold), maxLines = 1)
        PeekSymbol("xmark", font = type.style(IosTextStyle.Caption2, FontWeight.Bold), tint = colors.ink)
    }
}

/**
 * `ContentUnavailableView(_:systemImage:description:)`: the symbol large and secondary, the title in bold under it and
 * what it means under that, centred.
 */
@Composable
internal fun ContentUnavailable(symbol: String, title: String, description: String, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        PeekSymbol(
            symbol,
            Modifier.padding(top = 16.dp, bottom = 15.dp).clearAndSetSemantics {},
            font = type.fixed(NO_RESULTS_SYMBOL, FontWeight.Normal),
            tint = colors.secondaryLabel,
        )
        Text(
            title,
            Modifier.widthIn(max = NO_RESULTS_TEXT_WIDTH).semantics { heading() },
            color = colors.label,
            style = type.style(IosTextStyle.Title2, FontWeight.Bold),
            textAlign = TextAlign.Center,
        )
        Text(
            description,
            Modifier.padding(top = 2.dp).widthIn(max = NO_RESULTS_TEXT_WIDTH),
            color = colors.secondaryLabel,
            style = type.title3,
            textAlign = TextAlign.Center,
        )
    }
}

/**
 * `ContentUnavailableView` for a search or filters nothing matches (A-ST09): the glass large and secondary, "No
 * matching parcels" in bold, a word on what to try, and Clear filters, which clears them all, the query too.
 */
@Composable
internal fun NoResults(state: DeliveriesState) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(
        Modifier.fillMaxWidth().padding(vertical = 32.dp).testTag(NO_RESULTS),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ContentUnavailable("magnifyingglass", localized("view.noResultsTitle"), localized("view.noResultsDescription"))
        Box(
            Modifier
                .padding(top = 18.dp)
                .heightIn(min = BUTTON_SIDE)
                .testTag(NO_RESULTS_CLEAR)
                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = state::clearFilters)
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(localized("view.clear"), color = colors.ink, style = type.callout)
        }
    }
}

/**
 * `DeliveriesFoot`: Peek's mark and name, then the site's landing, its privacy notice and the code, side by side where
 * they fit and one under the other otherwise. Each opens in the browser.
 */
@Composable
internal fun DeliveriesFoot(homePageUrl: String, privacyUrl: String) {
    val colors = PeekTheme.colors
    val font = PeekTheme.type.caption
    val uriHandler = LocalUriHandler.current
    val title = localized("app.title")
    val tagline = localized("native.tagline")
    val home = localized("app.homePage")
    val privacy = localized("auth.privacyLink")
    Column(Modifier.fillMaxWidth().testTag(FOOT), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            Modifier.heightIn(min = BUTTON_SIDE).semantics(mergeDescendants = true) {},
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PeekMark(18.dp)
            Text("$title · $tagline", Modifier.weight(1f, fill = false), color = colors.secondaryLabel, style = font, textAlign = TextAlign.Center)
        }
        val links = @Composable {
            FootLink(home, "home") { uriHandler.openUri(homePageUrl) }
            FootLink(privacy, "privacy") { uriHandler.openUri(privacyUrl) }
            FootLink("GitHub", "source") { uriHandler.openUri(AppConfiguration.sourceUrl) }
        }
        FirstThatFits(
            Modifier,
            { Row(horizontalArrangement = Arrangement.spacedBy(22.dp)) { links() } },
            { Column(horizontalAlignment = Alignment.CenterHorizontally) { links() } },
        )
    }
}

/** A quiet link, underlined in the tertiary label's grey, with a full-height touch target. */
@Composable
private fun FootLink(title: String, id: String, open: () -> Unit) {
    val colors = PeekTheme.colors
    var layout by remember { mutableStateOf<TextLayoutResult?>(null) }
    Box(
        Modifier
            .heightIn(min = BUTTON_SIDE)
            .testTag("$FOOT.$id")
            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = open),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            title,
            Modifier.drawBehind {
                val text = layout ?: return@drawBehind
                val thickness = 1.dp.toPx()
                for (line in 0 until text.lineCount) {
                    val y = text.getLineBaseline(line) + 1.5.dp.toPx()
                    drawLine(colors.tertiaryLabel, Offset(text.getLineLeft(line), y), Offset(text.getLineRight(line), y), thickness)
                }
            },
            color = colors.secondaryLabel,
            style = PeekTheme.type.caption,
            textAlign = TextAlign.Center,
            onTextLayout = { layout = it },
        )
    }
}

/**
 * `DeliveriesEmptyState`: Pip's closed parcel, which opens a sample when tapped, "Peek", what the list is for, Paste &
 * track, Scan barcode where the device scans, Add a parcel and the hint about Pip. The sample opens on its page; Paste
 * & track opens Add with what the clipboard holds ([DeliveriesState.pasteFirst]), Scan barcode opens it on the camera,
 * Add a parcel opens it empty.
 *
 * A guest with nothing followed sees the first open over it instead ([FirstOpenHost]), with its own field and scan
 * button; this shows to someone signed in, and in the demo.
 */
@Composable
internal fun DeliveriesEmptyState(state: DeliveriesState) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentWidth()
            .widthIn(max = 420.dp)
            .padding(start = 8.dp, top = 34.dp, end = 8.dp, bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ClosedParcel(
            onOpen = state::openSample,
            label = localized("landing.pip.open"),
            modifier = Modifier.zoomSource(FirstOpenJourney.parcelId, state.zoom),
        )
        Text(
            localized("peek.title"),
            Modifier.semantics { heading() },
            color = colors.ink,
            style = type.tracking(type.style(IosTextStyle.Title, FontWeight.Bold, FontDesign.Rounded), -0.9f),
            textAlign = TextAlign.Center,
        )
        Text(
            localized("app.emptyDescription"),
            Modifier.padding(top = 8.dp),
            color = colors.secondaryLabel,
            style = type.subheadline,
            textAlign = TextAlign.Center,
        )
        // TalkBack comes back to the button that opened Add once it has gone.
        val pasteFocus = remember { AccessibilityFocus() }
        Row(
            Modifier
                .padding(top = 22.dp)
                .fillMaxWidth()
                .testTag(EMPTY_PASTE)
                .accessibilityFocus(pasteFocus)
                .clickable(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button) { state.pasteFirst(pasteFocus) }
                .heightIn(min = 54.dp)
                .background(colors.accent, ContinuousRoundedShape(16.dp)),
            horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PeekSymbol("doc.on.clipboard", font = type.style(IosTextStyle.Body, FontWeight.Medium), tint = colors.onAccent)
            Text(localized("landing.pasteAndTrack"), color = colors.onAccent, style = type.style(IosTextStyle.Body, FontWeight.SemiBold))
        }
        // Side by side while both fit; otherwise one under the other, each across the column.
        val canScan = LocalLabelScanner.current.available()
        FirstThatFits(
            Modifier.padding(top = 10.dp).fillMaxWidth(),
            { QuietButtons(state, canScan, wraps = false) },
            { QuietButtons(state, canScan, wraps = true) },
        )
        val localizer = LocalLocalizer.current
        val language = LocalLanguage.current
        val hint = remember(localizer, language, colors) {
            val action = localizer.text("landing.pip.action")
            val parts = localizer.text("landing.pip.hint", mapOf("action" to "\u0000")).split("\u0000")
            buildAnnotatedString {
                append(parts.first())
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = colors.ink)) { append(action) }
                if (parts.size > 1) append(parts[1])
            }
        }
        Text(
            hint,
            Modifier.padding(top = 14.dp).clearAndSetSemantics {},
            color = colors.secondaryLabel,
            style = type.footnote,
            textAlign = TextAlign.Center,
        )
    }
}

/**
 * `quietButtons(wraps:)`: Scan barcode where the device scans ([scan]), then Add a parcel, side by side 10 apart, each
 * half the row and its words on one line, shrinking to 80 % rather than wrap; or, where [wraps], one under the other 10
 * apart, each across the column, its words wrapping. Scan barcode opens Add on the camera, Add a parcel opens it empty,
 * and TalkBack goes back to the one tapped once Add has gone.
 */
@Composable
private fun QuietButtons(state: DeliveriesState, scan: Boolean, wraps: Boolean) {
    val scanFocus = remember { AccessibilityFocus() }
    val addFocus = remember { AccessibilityFocus() }
    val buttons = @Composable {
        if (scan) {
            QuietButton(localized("add.scan"), "barcode.viewfinder", wraps, EMPTY_SCAN, scanFocus) { state.add(FirstParcelRequest.Scan, scanFocus) }
        }
        QuietButton(localized("app.addParcel"), "keyboard", wraps, EMPTY_ADD, addFocus) { state.add(addFocus) }
    }
    if (wraps) Column(verticalArrangement = Arrangement.spacedBy(10.dp)) { buttons() } else EvenRow(10.dp, buttons)
}

/**
 * `quiet(_:symbol:wraps:)`: the symbol and the words 8 apart in subheadline, the words medium, in ink, across the
 * button, at least 48 tall, on the paper with 14-point corners outlined by a hairline of the separator at 30 %; it
 * gives under the finger (`TactileButtonStyle`).
 */
@Composable
private fun QuietButton(title: String, symbol: String, wraps: Boolean, tag: String, focus: AccessibilityFocus, open: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val shape = ContinuousRoundedShape(14.dp)
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(tag)
            .accessibilityFocus(focus)
            .clickable(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button, onClick = open)
            .heightIn(min = 48.dp)
            .background(colors.paper, shape)
            .border(0.75.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.3f), shape),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeekSymbol(symbol, font = type.subheadline, tint = colors.ink)
        val style = type.style(IosTextStyle.Subheadline, FontWeight.Medium)
        if (wraps) {
            Text(title, Modifier.weight(1f, fill = false), color = colors.ink, style = style, textAlign = TextAlign.Center)
        } else {
            Text(
                title, Modifier.weight(1f, fill = false), color = colors.ink, style = style, maxLines = 1,
                autoSize = TextAutoSize.StepBased(minFontSize = style.fontSize * QUIET_MINIMUM_SCALE, maxFontSize = style.fontSize),
            )
        }
    }
}

/**
 * Views side by side [spacing] apart, each an even share of the row, as tall as the tallest and centred on it, as an
 * HStack shares its room among views that all take what they are given. Its widest is the sum of theirs, as an HStack's
 * ideal width, which [FirstThatFits] asks for.
 */
@Composable
private fun EvenRow(spacing: Dp, content: @Composable () -> Unit) {
    Layout(content, measurePolicy = remember(spacing) { EvenRowPolicy(spacing) })
}

private class EvenRowPolicy(private val spacing: Dp) : MeasurePolicy {
    override fun MeasureScope.measure(measurables: List<Measurable>, constraints: Constraints): MeasureResult {
        val gap = spacing.roundToPx()
        val share = if (measurables.isEmpty()) 0 else ((constraints.maxWidth - gap * (measurables.size - 1)) / measurables.size).coerceAtLeast(0)
        val placeables = measurables.map { it.measure(Constraints(minWidth = share, maxWidth = share, maxHeight = constraints.maxHeight)) }
        val height = (placeables.maxOfOrNull { it.height } ?: 0).coerceIn(constraints.minHeight, constraints.maxHeight)
        return layout(constraints.maxWidth, height) {
            placeables.forEachIndexed { index, placeable -> placeable.place(index * (share + gap), (height - placeable.height) / 2) }
        }
    }

    override fun IntrinsicMeasureScope.maxIntrinsicWidth(measurables: List<IntrinsicMeasurable>, height: Int): Int =
        measurables.sumOf { it.maxIntrinsicWidth(height) } + gaps(measurables.size)

    override fun IntrinsicMeasureScope.minIntrinsicWidth(measurables: List<IntrinsicMeasurable>, height: Int): Int =
        measurables.sumOf { it.minIntrinsicWidth(height) } + gaps(measurables.size)

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int =
        measurables.maxOfOrNull { it.maxIntrinsicHeight(share(width, measurables.size)) } ?: 0

    override fun IntrinsicMeasureScope.minIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int =
        measurables.maxOfOrNull { it.minIntrinsicHeight(share(width, measurables.size)) } ?: 0

    private fun IntrinsicMeasureScope.gaps(count: Int) = spacing.roundToPx() * (count - 1).coerceAtLeast(0)

    private fun IntrinsicMeasureScope.share(width: Int, count: Int) =
        if (width == Constraints.Infinity || count == 0) width else ((width - gaps(count)) / count).coerceAtLeast(0)
}

/**
 * `UnwrappingParcel(open: 0, pressed:)`, 204 wide: the kraft parcel taped shut on the ground ([UnwrappingParcel]), Pip's
 * face on its left side and the arrow and seal on its right. It gives under the finger, squashing toward the ground
 * over its widening shadow, on a spring of response 0.3 s and damping 0.64.
 */
@Composable
private fun ClosedParcel(onOpen: () -> Unit, label: String, modifier: Modifier = Modifier) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val reduceMotion = PeekTheme.reduceMotion
    val spec = if (reduceMotion) snap() else Motion.springWithResponse<Float>(0.3, 0.64)
    val press by animateFloatAsState(if (pressed) 1f else 0f, spec, label = "press")
    val scale = PARCEL_WIDTH / FRAME_WIDTH
    Box(
        Modifier
            .size(PARCEL_WIDTH.dp, (PARCEL_WIDTH * 31f / 30f).dp)
            .then(modifier)
            .testTag(EMPTY_SAMPLE)
            .clickable(interaction, indication = null, role = Role.Button, onClick = onOpen)
            .semantics { contentDescription = label },
    ) {
        // Pip's 300 × 310 frame, drawn at the parcel's width.
        Box(
            Modifier.layout { measurable, _ ->
                val frame = measurable.measure(Constraints.fixed(FRAME_WIDTH.dp.roundToPx(), FRAME_HEIGHT.dp.roundToPx()))
                layout((frame.width * scale).toInt(), (frame.height * scale).toInt()) {
                    frame.placeWithLayer(0, 0) {
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
                }
            },
        ) {
            UnwrappingParcel(0.0, label = null, ribbon = false, pressed = press)
        }
    }
}

/** An ellipse filling its box, as SwiftUI's `Ellipse()`. */
internal val OvalShape = GenericShape { size, _ -> addOval(androidx.compose.ui.geometry.Rect(0f, 0f, size.width, size.height)) }

/**
 * Every parcel has arrived: a fine check, "All arrived", a word about it, and Track another, which opens Add (not
 * ported yet).
 */
@Composable
internal fun AllArrived(state: DeliveriesState) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(
        Modifier.fillMaxWidth().padding(vertical = 32.dp).testTag(ALL_ARRIVED),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PeekSymbol(
            "checkmark",
            Modifier.padding(bottom = 6.dp).clearAndSetSemantics {},
            font = type.fixed(36f, FontWeight.W100),
            tint = colors.secondaryLabel,
        )
        Text(localized("app.allArrived"), color = colors.label, style = type.style(IosTextStyle.Title2, FontWeight.SemiBold), textAlign = TextAlign.Center)
        Text(localized("app.allArrivedDescription"), color = colors.secondaryLabel, style = type.subheadline, textAlign = TextAlign.Center)
        val focus = remember { AccessibilityFocus() }
        Box(
            Modifier
                .padding(top = 6.dp)
                .accessibilityFocus(focus)
                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button) { state.add(focus) }
                .background(colors.accent, CapsuleShape)
                .padding(horizontal = 14.dp, vertical = 7.dp),
        ) {
            Text(localized("app.trackAnother"), color = colors.onAccent, style = type.style(IosTextStyle.Body, FontWeight.SemiBold))
        }
    }
}

/**
 * `ViewThatFits(in: .horizontal)` of two: [wide] when it fits the width as it would lay itself out, [narrow]
 * otherwise. The width is taken on a copy of [wide] that TalkBack doesn't read, which stays composed and unplaced
 * beside the narrow one, so only the one drawn is in the tree.
 */
@Composable
internal fun FirstThatFits(modifier: Modifier, wide: @Composable () -> Unit, narrow: @Composable () -> Unit) {
    // The same content from one pass to the next, or each would compose the copy again and ask for another frame.
    val probe: @Composable () -> Unit = remember(wide) { { Box(Modifier.clearAndSetSemantics {}) { wide() } } }
    SubcomposeLayout(modifier) { constraints ->
        val fits = subcompose(PROBE, probe).all { it.maxIntrinsicWidth(Constraints.Infinity) <= constraints.maxWidth }
        val chosen = if (fits) subcompose(WIDE, wide) else subcompose(NARROW, narrow)
        val placeables = chosen.map { it.measure(constraints.copy(minHeight = 0)) }
        val width = (placeables.maxOfOrNull { it.width } ?: 0).coerceIn(constraints.minWidth, constraints.maxWidth)
        val height = (placeables.maxOfOrNull { it.height } ?: 0).coerceIn(constraints.minHeight, constraints.maxHeight)
        layout(width, height) {
            placeables.forEach { it.place((width - it.width) / 2, (height - it.height) / 2) }
        }
    }
}

private const val PROBE = "probe"
private const val WIDE = "wide"
private const val NARROW = "narrow"

/** `.buttonStyle(.plain)`: the label dims a little while pressed. */
internal val PlainPress = PressStyle(opacity = 0.7f, animation = MotionCurve.EaseOut(0.12))

/**
 * `.buttonStyle(.plain)` while `.disabled`: the whole button, what it is drawn on too, at half strength, as SwiftUI
 * draws it. A button of the default style in its own colour keeps it.
 */
internal fun Modifier.plainDisabled(enabled: Boolean): Modifier = if (enabled) this else alpha(0.5f)

/** Whether TalkBack, or another service that explores by touch, is on: the list adds a refresh button for it. */
@Composable
internal fun touchExplorationEnabled(): Boolean {
    val context = LocalContext.current
    val manager = remember(context) { context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager? }
    var enabled by remember(manager) { mutableStateOf(manager?.isTouchExplorationEnabled == true) }
    DisposableEffect(manager) {
        val listener = AccessibilityManager.TouchExplorationStateChangeListener { enabled = it }
        manager?.addTouchExplorationStateChangeListener(listener)
        onDispose { manager?.removeTouchExplorationStateChangeListener(listener) }
    }
    return enabled
}

private val ROW_HEIGHT = 44.dp

/** `minimumScaleFactor(0.75)` of "On the way" at the accessibility sizes. */
private const val HEADING_MINIMUM_SCALE = 0.75f

/** Told how "On the way" is laid out: the tests check it keeps to one line, whole. */
internal val LocalOnTheWayHeading = staticCompositionLocalOf<((TextLayoutResult) -> Unit)?> { null }

private val BUTTON_SIDE = 44.dp
private val CHIP_HEIGHT = 34.dp
private val ADD_X = 75.5.dp
private val ADD_GLASS_WIDTH = 47.dp
private val ACCOUNT_GLASS_WIDTH = 53.dp
private val ACCOUNT_MARGIN = 19.5.dp
private val PAGE_TOOLBAR_WIDTH = 118.dp
private val PAGE_TOOLBAR_MENU_WIDTH = 170.dp
private val PAGE_MENU_CENTRE = 147.dp
private val PAGE_TOOLBAR_MARGIN = 9.5.dp
private val PAGE_ADD_CENTRE = 28.dp
private val PAGE_ACCOUNT_CENTRE = 90.dp
private const val SEARCH_FIELD_MOTION = 0.2
private const val NO_RESULTS_SYMBOL = 44f
/** How wide the words of a view with nothing to show run before they wrap, as the iPhone's wrap them. */
private val NO_RESULTS_TEXT_WIDTH = 268.dp
/** `minimumScaleFactor(0.8)` of a quiet button's words on one line. */
private const val QUIET_MINIMUM_SCALE = 0.8f
private const val PARCEL_WIDTH = 204f
private const val FRAME_WIDTH = 300f
private const val FRAME_HEIGHT = 310f

/** The test tags of the list's controls. */
const val ADD_BUTTON = "deliveries.add"
const val ACCOUNT_BUTTON = "deliveries.accountButton"
const val SEARCH_BUTTON = "deliveries.search"
const val SEARCH_ROW = "deliveries.searchRow"
const val SEARCH_FIELD = "deliveries.searchField"
const val SEARCH_CLEAR = "deliveries.searchClear"
const val FILTERS_BUTTON = "deliveries.filters"
const val FILTER_CHIPS = "deliveries.chips"
const val CLEAR_ALL = "deliveries.chips.clearAll"
const val NO_RESULTS = "deliveries.noResults"
const val NO_RESULTS_CLEAR = "deliveries.noResults.clear"
const val REFRESH_BUTTON = "deliveries.refresh"
const val DEMO_EXIT = "demo.exit"
const val FOOT = "deliveries.foot"
const val EMPTY_SAMPLE = "deliveries.empty.sample"
const val EMPTY_PASTE = "deliveries.empty.paste"
const val EMPTY_ADD = "deliveries.empty.add"
const val EMPTY_SCAN = "deliveries.empty.scan"
const val ALL_ARRIVED = "deliveries.allArrived"
