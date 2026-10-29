package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
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
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.LaunchedEffect
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
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.ParcelSort
import com.plhery.peekdeliverytracker.core.ParcelStatusFilter
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
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
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.MotionCurve
import com.plhery.peekdeliverytracker.core.PipArtwork
import com.plhery.peekdeliverytracker.core.PipPalette
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.PeekMark
import com.plhery.peekdeliverytracker.ui.draw.PipCanvas
import com.plhery.peekdeliverytracker.ui.draw.rememberPipBlinkClock
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.symbols.SymbolRenderingMode
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabBarPadding
import com.plhery.peekdeliverytracker.ui.theme.CappedDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow

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
 * the parcel ([PageToolbar]). The glass shows the list scrolling under it ([backdrop]).
 */
@Composable
internal fun ListToolbar(state: DeliveriesState, sideBySide: Boolean, rowTop: Dp, start: Dp, end: Dp, backdrop: MaterialBackdrop) {
    val title = localized("native.deliveries")
    Box(Modifier.fillMaxWidth().padding(top = rowTop).height(ROW_HEIGHT)) {
        // The mark stands on its own, not on a control's glass.
        PeekMark(30.dp, Modifier.align(Alignment.CenterStart).offset(x = start + if (sideBySide) 17.dp else 27.dp).clearAndSetSemantics {})
        // Centred on the bar, clear of its items on either side.
        val clear = if (sideBySide) 56.dp else 128.dp
        Text(
            title,
            Modifier.align(Alignment.Center).padding(start = start + clear, end = end + clear).semantics { heading() },
            color = PeekTheme.colors.label,
            style = PeekTheme.type.headline,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (!sideBySide) {
            GlassSurface(
                CapsuleShape,
                Modifier.align(Alignment.CenterStart).offset(x = start + ADD_X).size(ADD_GLASS_WIDTH, ROW_HEIGHT),
                backdrop,
            ) {
                AddButton(state, Modifier.align(Alignment.Center))
            }
            GlassSurface(
                CapsuleShape,
                Modifier.align(Alignment.CenterEnd).offset(x = -(end + ACCOUNT_MARGIN)).size(ACCOUNT_GLASS_WIDTH, ROW_HEIGHT),
                backdrop,
            ) {
                AccountButton(state, Modifier.align(Alignment.Center))
            }
        }
    }
}

/**
 * Beside a parcel, adding one and the account on one glass at the window's trailing edge, over the parcel's page, as
 * the iPad's detail toolbar has them. The parcel's own menu joins them once its page is ported.
 */
@Composable
internal fun PageToolbar(state: DeliveriesState, backdrop: MaterialBackdrop?) {
    val end = if (LocalColumnEdges.current.end) {
        WindowInsets.systemBars.union(WindowInsets.displayCutout).asPaddingValues().calculateEndPadding(LocalLayoutDirection.current)
    } else {
        0.dp
    }
    Box(Modifier.fillMaxWidth().padding(top = barRowTop()).height(ROW_HEIGHT)) {
        GlassSurface(
            CapsuleShape,
            Modifier.align(Alignment.CenterEnd).offset(x = -(end + PAGE_TOOLBAR_MARGIN)).size(PAGE_TOOLBAR_WIDTH, ROW_HEIGHT),
            backdrop,
        ) {
            AddButton(state, Modifier.align(Alignment.CenterStart).offset(x = PAGE_ADD_CENTRE - BUTTON_SIDE / 2))
            AccountButton(state, Modifier.align(Alignment.CenterStart).offset(x = PAGE_ACCOUNT_CENTRE - BUTTON_SIDE / 2))
        }
    }
}

/** `addButton`: a plus, semibold, in the ink. Add isn't ported yet ([DeliveriesState.add]). */
@Composable
private fun AddButton(state: DeliveriesState, modifier: Modifier) {
    val label = localized("app.addParcelAria")
    Box(
        modifier
            .size(BUTTON_SIDE)
            .testTag(ADD_BUTTON)
            .clickable(interactionSource = null, indication = PressStyle.tactile(0.94f), onClick = state::add)
            .semantics {
                contentDescription = label
                role = Role.Button
            },
        contentAlignment = Alignment.Center,
    ) {
        PeekSymbol("plus", font = PeekTheme.type.style(IosTextStyle.Body, FontWeight.SemiBold), tint = PeekTheme.colors.ink)
    }
}

/**
 * `AccountToolbarButton`: the account's initial, or a figure, on a 34-point yellow disc. The account isn't ported yet
 * ([DeliveriesState.account]).
 */
@Composable
private fun AccountButton(state: DeliveriesState, modifier: Modifier) {
    val session by state.source.session.collectAsState()
    val initial = (session as? AuthenticationState.SignedIn)?.user?.email?.firstOrNull()?.uppercase()
    val label = localized("native.account")
    val colors = PeekTheme.colors
    Box(
        modifier
            .size(BUTTON_SIDE)
            .testTag(ACCOUNT_BUTTON)
            .clickable(interactionSource = null, indication = PressStyle.tactile(0.94f), onClick = state::account)
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
internal fun OverviewRow(state: DeliveriesState, active: Int, refreshes: Boolean) {
    val colors = PeekTheme.colors
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    Row(Modifier.fillMaxWidth().heightIn(min = BUTTON_SIDE), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        // The words and the count take what the buttons leave, the words giving way first.
        Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            if (active > 0) {
                Text(
                    localized("app.onTheWaySection"),
                    Modifier.weight(1f, fill = false).semantics { heading() },
                    color = colors.ink,
                    style = PeekTheme.type.headline,
                    maxLines = if (largeText) 1 else Int.MAX_VALUE,
                    overflow = TextOverflow.Ellipsis,
                )
                CountPill(active, horizontalPadding = 6.dp)
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
 * The arrow while the parcels are checked: `.symbolEffect(.rotate)`, a turn a second, or `.pulse` under Reduce Motion,
 * the arrow fading to a third and back. Only then, so that the window rests the rest of the time.
 */
@Composable
private fun Modifier.refreshing(reduceMotion: Boolean): Modifier {
    val turning = rememberInfiniteTransition("refresh")
    if (reduceMotion) {
        val pulse = turning.animateFloat(1f, 0.35f, InfiniteRepeatableSpec(tween(750), RepeatMode.Reverse), "pulse")
        return graphicsLayer { alpha = pulse.value }
    }
    val angle = turning.animateFloat(0f, 360f, infiniteRepeatable(tween(1000, easing = LinearEasing)), "turn")
    return graphicsLayer { rotationZ = angle.value }
}

/** A count in a faint capsule, beside a heading. */
@Composable
internal fun CountPill(count: Int, horizontalPadding: Dp) {
    val secondary = PeekTheme.colors.secondaryLabel
    Box(
        Modifier
            .heightIn(min = 20.dp)
            .background(secondary.copy(alpha = secondary.alpha * 0.09f), CapsuleShape)
            .padding(horizontal = horizontalPadding),
        contentAlignment = Alignment.Center,
    ) {
        Text("$count", color = secondary, style = PeekTheme.type.caption2.monospacedDigit(), maxLines = 1, softWrap = false)
    }
}

/**
 * `searchControls` (A-S27, A-G09): the search field in one quiet capsule, the glass before it and, once it holds
 * something, the button that empties it; and the filters' button beside it. The field takes the focus as it shows
 * ([DeliveriesState.openSearch]); it doesn't capitalise or correct what is typed, and the keyboard's Search ends the
 * editing. Its trailing room narrows from 13 to 5 as the clear button comes, which fades and grows in from 0.7, on a
 * snappy 0.2 s (at once under Reduce Motion). The filters' button takes the focus from the field, then opens them.
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
    // unfocused leaves it as it was until it has taken it.
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
        PeekSymbol(
            "magnifyingglass",
            Modifier.padding(top = 16.dp, bottom = 15.dp).clearAndSetSemantics {},
            font = type.fixed(NO_RESULTS_SYMBOL, FontWeight.Normal),
            tint = colors.secondaryLabel,
        )
        Text(
            localized("view.noResultsTitle"),
            Modifier.widthIn(max = NO_RESULTS_TEXT_WIDTH).semantics { heading() },
            color = colors.label,
            style = type.style(IosTextStyle.Title2, FontWeight.Bold),
            textAlign = TextAlign.Center,
        )
        Text(
            localized("view.noResultsDescription"),
            Modifier.padding(top = 2.dp).widthIn(max = NO_RESULTS_TEXT_WIDTH),
            color = colors.secondaryLabel,
            style = type.title3,
            textAlign = TextAlign.Center,
        )
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
 * track, Add a parcel and the hint about Pip. The sample, pasting and adding open what isn't ported yet: the buttons
 * call [DeliveriesState]'s hooks.
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
        ClosedParcel(onOpen = state::openSample, label = localized("landing.pip.open"))
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
        Row(
            Modifier
                .padding(top = 22.dp)
                .fillMaxWidth()
                .testTag(EMPTY_PASTE)
                .clickable(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button, onClick = state::pasteFirst)
                .heightIn(min = 54.dp)
                .background(colors.accent, ContinuousRoundedShape(16.dp)),
            horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PeekSymbol("doc.on.clipboard", font = type.style(IosTextStyle.Body, FontWeight.Medium), tint = colors.onAccent)
            Text(localized("landing.pasteAndTrack"), color = colors.onAccent, style = type.style(IosTextStyle.Body, FontWeight.SemiBold))
        }
        // Scanning a label comes with the camera: until then the one quiet button takes the column's width.
        val quiet = ContinuousRoundedShape(14.dp)
        Row(
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .testTag(EMPTY_ADD)
                .clickable(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button, onClick = state::add)
                .heightIn(min = 48.dp)
                .background(colors.paper, quiet)
                .border(0.75.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.3f), quiet),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PeekSymbol("keyboard", font = type.subheadline, tint = colors.ink)
            Text(localized("app.addParcel"), color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium), maxLines = 1)
        }
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
 * `UnwrappingParcel(open: 0)`, 204 wide: the kraft parcel taped shut on the ground, Pip's face on its left side and
 * the arrow and seal on its right. It gives under the finger, squashing toward the ground, on a spring of response
 * 0.3 s and damping 0.64.
 */
@Composable
private fun ClosedParcel(onOpen: () -> Unit, label: String) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val reduceMotion = PeekTheme.reduceMotion
    val spec = if (reduceMotion) snap() else Motion.springWithResponse<Float>(0.3, 0.64)
    val press by animateFloatAsState(if (pressed) 1f else 0f, spec, label = "press")
    val blink = rememberPipBlinkClock(blinks = !reduceMotion)
    val scale = PARCEL_WIDTH / FRAME_WIDTH
    Box(
        Modifier
            .size(PARCEL_WIDTH.dp, (PARCEL_WIDTH * 31f / 30f).dp)
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
            // The ground's soft shadow, which the press widens and darkens.
            val shadowWidth = 180f + 8f * press
            val shadowHeight = 20f - 7f * press
            Box(
                Modifier
                    .offset((151f - shadowWidth / 2).dp, (286f - shadowHeight / 2).dp)
                    .size(shadowWidth.dp, shadowHeight.dp)
                    .dropShadow(OvalShape, swiftUiShadow(Color.Black.copy(alpha = 0.08f + 0.06f * press), radius = 9.dp)),
            )
            Box(
                Modifier
                    .size(FRAME_WIDTH.dp, FRAME_HEIGHT.dp)
                    .graphicsLayer {
                        val squash = 1f - 0.044f * press
                        scaleX = squash
                        scaleY = squash
                        transformOrigin = TransformOrigin(0.5f, 1f)
                        translationY = 5.dp.toPx() * press
                    },
            ) {
                PipCanvas(Modifier.size(FRAME_WIDTH.dp, FRAME_HEIGHT.dp)) { PipArtwork.box(it, PipPalette.kraft, open = false) }
                Decoration(218f, 214f) {
                    PeekSymbol(
                        "arrow.up",
                        Modifier.rotate(-27f),
                        font = PeekTheme.type.fixed(18f, FontWeight.Medium),
                        tint = Color(0xFF735C43),
                    )
                }
                Decoration(183f, 234f) {
                    Box(
                        Modifier
                            .rotate(-27f)
                            .size(28.dp)
                            .background(Color(0xFFDECCE2), CircleShape)
                            .drawBehind {
                                val ring = 1.2.dp.toPx()
                                drawCircle(
                                    Color(0xFFFFF6FF).copy(alpha = 0.48f),
                                    radius = size.minDimension / 2 - 2.5.dp.toPx(),
                                    style = androidx.compose.ui.graphics.drawscope.Stroke(ring),
                                )
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        PeekSymbol("asterisk", font = PeekTheme.type.fixed(14f, FontWeight.Bold), tint = Color(0xFF7C6787))
                    }
                }
                PipCanvas(Modifier.size(FRAME_WIDTH.dp, FRAME_HEIGHT.dp)) {
                    PipArtwork.kraftFace(it, k = 1.0, happy = 0.0, blink = blink.time)
                    PipArtwork.frontFlaps(it, PipPalette.kraft, open = false)
                    PipArtwork.tape(it, PipPalette.kraft)
                }
            }
        }
    }
}

/** Something on the parcel's side, centred at (`x`, `y`) of Pip's frame. */
@Composable
private fun Decoration(x: Float, y: Float, content: @Composable () -> Unit) {
    Box(Modifier.offset(x.dp, y.dp).size(0.dp).wrapContentSize(unbounded = true).clearAndSetSemantics {}) { content() }
}

private val OvalShape = GenericShape { size, _ -> addOval(androidx.compose.ui.geometry.Rect(0f, 0f, size.width, size.height)) }

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
        Box(
            Modifier
                .padding(top = 6.dp)
                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = state::add)
                .background(colors.accent, CapsuleShape)
                .padding(horizontal = 14.dp, vertical = 7.dp),
        ) {
            Text(localized("app.trackAnother"), color = colors.onAccent, style = type.style(IosTextStyle.Body, FontWeight.SemiBold))
        }
    }
}

/**
 * `ViewThatFits(in: .horizontal)` of two: [wide] when it fits the width as it would lay itself out, [narrow]
 * otherwise.
 */
@Composable
internal fun FirstThatFits(modifier: Modifier, wide: @Composable () -> Unit, narrow: @Composable () -> Unit) {
    SubcomposeLayout(modifier) { constraints ->
        val first = subcompose(WIDE, wide)
        val fits = first.all { it.maxIntrinsicWidth(Constraints.Infinity) <= constraints.maxWidth }
        val chosen = if (fits) first else subcompose(NARROW, narrow)
        val placeables = chosen.map { it.measure(constraints.copy(minHeight = 0)) }
        val width = (placeables.maxOfOrNull { it.width } ?: 0).coerceIn(constraints.minWidth, constraints.maxWidth)
        val height = (placeables.maxOfOrNull { it.height } ?: 0).coerceIn(constraints.minHeight, constraints.maxHeight)
        layout(width, height) {
            placeables.forEach { it.place((width - it.width) / 2, (height - it.height) / 2) }
        }
    }
}

private const val WIDE = "wide"
private const val NARROW = "narrow"

/** `.buttonStyle(.plain)`: the label dims a little while pressed. */
internal val PlainPress = PressStyle(opacity = 0.7f, animation = MotionCurve.EaseOut(0.12))

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
private val BUTTON_SIDE = 44.dp
private val CHIP_HEIGHT = 34.dp
private val ADD_X = 75.5.dp
private val ADD_GLASS_WIDTH = 47.dp
private val ACCOUNT_GLASS_WIDTH = 53.dp
private val ACCOUNT_MARGIN = 19.5.dp
private val PAGE_TOOLBAR_WIDTH = 118.dp
private val PAGE_TOOLBAR_MARGIN = 9.5.dp
private val PAGE_ADD_CENTRE = 28.dp
private val PAGE_ACCOUNT_CENTRE = 90.dp
private const val SEARCH_FIELD_MOTION = 0.2
private const val NO_RESULTS_SYMBOL = 44f
/** How wide the words of a view with nothing to show run before they wrap, as the iPhone's wrap them. */
private val NO_RESULTS_TEXT_WIDTH = 268.dp
private const val PARCEL_WIDTH = 204f
private const val FRAME_WIDTH = 300f
private const val FRAME_HEIGHT = 310f

/** The test tags of the list's controls. */
const val ADD_BUTTON = "deliveries.add"
const val ACCOUNT_BUTTON = "deliveries.account"
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
const val ALL_ARRIVED = "deliveries.allArrived"
