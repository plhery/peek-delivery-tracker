package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.onConsumedWindowInsetsChanged
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierPickerSearch
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.NoSuggestions
import com.plhery.peekdeliverytracker.ui.keptFocus
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.CarrierTruck
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.UnevenContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.sheet.BAR_ROW
import com.plhery.peekdeliverytracker.ui.sheet.FORM_BOTTOM
import com.plhery.peekdeliverytracker.ui.sheet.FormHeader
import com.plhery.peekdeliverytracker.ui.sheet.FormScrollEdge
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetAttachment
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetInsets
import com.plhery.peekdeliverytracker.ui.sheet.ROW_HEIGHT
import com.plhery.peekdeliverytracker.ui.sheet.RowHighlight
import com.plhery.peekdeliverytracker.ui.sheet.SECTION_BOTTOM
import com.plhery.peekdeliverytracker.ui.sheet.SECTION_GAP
import com.plhery.peekdeliverytracker.ui.sheet.SECTION_RADIUS
import com.plhery.peekdeliverytracker.ui.sheet.SEPARATOR
import com.plhery.peekdeliverytracker.ui.sheet.TITLE_GAP
import com.plhery.peekdeliverytracker.ui.sheet.drawFormRows
import com.plhery.peekdeliverytracker.ui.sheet.drawFormSeparator
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.symbols.SymbolScale
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.ProvideDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.carrierIdentity
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/** A section the picker opens with, after the automatic row (`CarrierPickerView.PickerSection`). */
@Immutable
internal data class CarrierPickerSection(val id: String, val title: String, val carriers: List<CarrierId>)

/** A carrier's tag in the picker (`CarrierPickerView.Tag`): [found] for one that knows the number, not one still asked. */
@Immutable
internal data class CarrierPickerTag(val label: String, val found: Boolean)

/** The automatic row's words and state (`CarrierPickerView.Automatic`). */
@Immutable
internal data class CarrierPickerAutomatic(val description: String, val recommended: Boolean, val busy: Boolean)

/**
 * `CarrierPickerView`: choose one of the catalog's carriers. Automatic detection first when it is offered ([automatic],
 * chosen while [selection] is null), then the sections the picker opens with, the empty ones left out and kept as they
 * opened, so an answer arriving meanwhile changes only [tags] and the automatic row's words, then every carrier from A
 * to Z under its letter, with the letter rail beside them ([LetterRail]). Under the inline bar, titled "add.carrier"
 * with [leading] at its leading end (the stack's ‹, or a Cancel), the search field always shows
 * (`.searchable(placement: .navigationBarDrawer(displayMode: .always))`): while it holds a query, the list is the
 * matches under their count, best first and the matched part marked, or says none matches, and the rail goes. From the
 * field's first focus the search is on, as UISearchController's `isActive`: the bar goes up out of the way, the field
 * taking its place, and a ✕ shows beside the field, which empties it, puts the keyboard away and ends the search, as
 * the system's back does meanwhile. With the keyboard up, the list's foot and the rail end above it. A choice goes to
 * [onSelect], then the picker is left ([onLeave]), as `choose` dismisses it. The query, the sections it opened with,
 * whether the search is on and the place in the list stay through a window made again.
 */
@Composable
internal fun CarrierPicker(
    selection: CarrierId?,
    sections: List<CarrierPickerSection>,
    onSelect: (CarrierId?) -> Unit,
    onLeave: () -> Unit,
    modifier: Modifier = Modifier,
    automatic: CarrierPickerAutomatic? = null,
    tags: Map<CarrierId, CarrierPickerTag> = emptyMap(),
    leading: (@Composable () -> Unit)? = null,
) {
    // Before the identities are read, the catalog the app has names the carriers, as the filters' do: only the marks
    // wait for them.
    val identities = LocalCarrierIdentities.current
    val catalog = remember(identities) { identities?.catalog ?: CarrierCatalog.shared }
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val density = LocalDensity.current
    val insets = LocalSheetInsets.current
    val reduceMotion = PeekTheme.reduceMotion
    val focusManager = LocalFocusManager.current
    var query by rememberSaveable { mutableStateOf("") }
    // The search is on from the field's first focus until its ✕. The bar's room goes on the system's ease meanwhile
    // (`searchPresentationToolbarBehavior`'s automatic hides it on the iPhone), at once under Reduce Motion.
    var searchOn by rememberSaveable { mutableStateOf(false) }
    val endSearch = {
        query = ""
        searchOn = false
        focusManager.clearFocus()
    }
    BackHandler(enabled = searchOn, onBack = endSearch)
    val bar = animateFloatAsState(if (searchOn) 0f else 1f, if (reduceMotion) snap() else Motion.easeInOut(), label = "bar")
    val barGone by remember { derivedStateOf { bar.value == 0f } }
    val opening = rememberSaveable(saver = OPENING_SAVER) { sections.filter { it.carriers.isNotEmpty() } }
    val list = rememberLazyListState()
    val trimmed = CarrierPickerSearch.trimmedQuery(query)
    val searching = trimmed.isNotEmpty()
    // The carriers sorted and their names folded once, not on each letter typed; again for a refreshed catalog.
    val index = remember(catalog, catalog.definitions, language) { CarrierPickerSearch.Index(catalog, language) }
    val letters = remember(index) { CarrierPickerSearch.letterSections(index) }
    val preferred = remember(opening) { opening.firstOrNull()?.carriers.orEmpty().toSet() }
    val results = remember(index, trimmed, preferred) {
        if (!searching) emptyList() else CarrierPickerSearch.search(trimmed, index, preferred)
    }
    val entries = remember(letters, results, opening, trimmed, automatic != null, localizer, language) {
        pickerEntries(searching, automatic != null, opening, letters, results) { key, count ->
            localizer.text(key, mapOf("count" to count, "query" to trimmed))
        }
    }
    val letterRows = remember(entries) {
        entries.withIndex().filter { (_, entry) -> entry is PickerEntry.Header && entry.letter != null }
            .associate { (index, entry) -> (entry as PickerEntry.Header).letter!! to index }
    }
    // A new query shows its matches from the top, as the list it replaces goes.
    var shownQuery by rememberSaveable { mutableStateOf(trimmed) }
    LaunchedEffect(trimmed) {
        if (trimmed == shownQuery) return@LaunchedEffect
        shownQuery = trimmed
        list.requestScrollToItem(0)
    }
    val choose: (CarrierId?) -> Unit = { carrier ->
        onSelect(carrier)
        onLeave()
    }
    // The keyboard, where it covers the picker as drawn and no host has taken it already (made room for it or kept it
    // for itself): the list's foot and the rail end above it, as the List's content and the rail do on the iPhone.
    var consumed by remember { mutableStateOf(WindowInsets(0, 0, 0, 0)) }
    var below by remember { mutableFloatStateOf(0f) }
    var scale by remember { mutableFloatStateOf(1f) }
    val ime = WindowInsets.ime
    val keyboard by remember(ime, density) {
        derivedStateOf { with(density) { ((ime.exclude(consumed).getBottom(density) - below).coerceAtLeast(0f) / scale).toDp() } }
    }
    val navigation = if (!insets.reachesBottom) 0.dp else with(density) { WindowInsets.navigationBars.getBottom(this).toDp() }
    val bottom = maxOf(navigation, keyboard)
    var barHeight by remember { mutableStateOf(insets.barTop + BAR_ROW + SEARCH_TOP + SEARCH_HEIGHT + SEARCH_BOTTOM) }
    // `.contentMargins(.trailing, ...)`: the rows keep clear of the rail's touch strip.
    val start = insets.formSide
    val end = if (searching) SEARCHING_MARGIN else RAIL_WIDTH
    val stacked = PeekTheme.dynamicTypeSize.isAccessibilitySize
    Box(
        modifier
            .fillMaxSize()
            .testTag(CARRIER_PICKER)
            .onConsumedWindowInsetsChanged { consumed = it }
            .onGloballyPositioned { coordinates ->
                // How far above the window's foot the picker ends, and how much smaller a floating sheet draws it.
                val root = coordinates.findRootCoordinates()
                val drawn = root.localBoundingBoxOf(coordinates, clipBounds = false)
                below = root.size.height - drawn.bottom
                scale = if (coordinates.size.height > 0) drawn.height / coordinates.size.height else 1f
            },
    ) {
        LazyColumn(
            Modifier.fillMaxSize().testTag(CARRIER_PICKER_LIST),
            state = list,
            contentPadding = PaddingValues(top = barHeight, bottom = FORM_BOTTOM + bottom),
        ) {
            items(entries, key = { it.key }, contentType = { it::class }) { entry ->
                when (entry) {
                    is PickerEntry.Gap -> Spacer(Modifier.height(SECTION_GAP))
                    // `Section(title)` and the letters' `header: { Text(letter) }`, over their rows.
                    is PickerEntry.Header -> FormHeader(
                        entry.title,
                        Modifier.fillMaxWidth().padding(start = start * 2, end = end + start).testTag(carrierPickerHeaderTag(entry.key)),
                    )
                    is PickerEntry.Automatic -> if (automatic != null) {
                        AutomaticRow(automatic, selected = selection == null, stacked, start, end) { choose(null) }
                    }
                    is PickerEntry.Carrier -> CarrierRow(
                        entry, catalog, selected = selection == entry.carrier, tag = tags[entry.carrier], stacked, start, end,
                    ) { choose(entry.carrier) }
                    is PickerEntry.Empty -> PickerCell(first = true, last = true, fill = null, start, end, separatorStart = 0.dp) {
                        Text(entry.text, color = colors.secondaryLabel, style = PeekTheme.type.body.sfLines())
                    }
                }
            }
        }
        // The sheet's scroll edge under the search field, as SheetPage's under its bar.
        FormScrollEdge(barHeight) {
            if (list.firstVisibleItemIndex > 0) Float.POSITIVE_INFINITY else list.firstVisibleItemScrollOffset.toFloat()
        }
        Column(
            Modifier
                .fillMaxWidth()
                .onSizeChanged { barHeight = with(density) { it.height.toDp() } }
                .padding(top = insets.barTop),
        ) {
            if (!barGone) {
                // Going, the bar rises under the field, which rises in its place.
                Box(
                    Modifier
                        .clipToBounds()
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            val height = (placeable.height * bar.value).roundToInt()
                            layout(placeable.width, height) { placeable.placeWithLayer(0, height - placeable.height) { alpha = bar.value } }
                        },
                ) {
                    PickerBar(localizer.text("add.carrier"), leading)
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    // 10 under the bar's row, the field rising into the row's place as the bar goes.
                    .layout { measurable, constraints ->
                        val top = (SEARCH_TOP.toPx() * bar.value).roundToInt()
                        val placeable = measurable.measure(constraints.offset(vertical = -top))
                        layout(placeable.width, placeable.height + top) { placeable.place(0, top) }
                    }
                    .padding(start = insets.formSide, end = insets.formSide, bottom = SEARCH_BOTTOM),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SearchField(
                    query, { query = it }, localizer.text("picker.search", mapOf("count" to index.size)),
                    localizer.text("picker.clear"), onFocus = { searchOn = true }, Modifier.weight(1f),
                )
                SearchClose(searchOn, localizer.text("common.close"), endSearch)
            }
        }
        if (!searching) {
            LetterRail(
                letters.map { it.letter }, localizer.text("picker.jump"),
                { letter -> letterRows[letter]?.let { list.requestScrollToItem(it) } },
                Modifier.align(Alignment.TopEnd).padding(top = barHeight, bottom = bottom).fillMaxHeight(),
            )
        }
    }
}

/** What the picker's list shows, in its order, each keyed by its section so a carrier can show in two. */
private sealed interface PickerEntry {
    val key: String

    /** A section without a header's room above it. */
    data class Gap(override val key: String) : PickerEntry

    /** A section's header; a letter's carries its [letter], which the rail jumps to (`letterID`). */
    data class Header(override val key: String, val title: String, val letter: String? = null) : PickerEntry

    data object Automatic : PickerEntry {
        override val key = "automatic"
    }

    data class Carrier(
        override val key: String,
        val carrier: CarrierId,
        val first: Boolean,
        val last: Boolean,
        val highlight: IntRange? = null,
        val alias: String? = null,
    ) : PickerEntry

    data class Empty(override val key: String, val text: String) : PickerEntry
}

/**
 * The list's entries (`body`): not searching, the automatic row's section, the opening sections and the letters'; while
 * searching, the matches under their count (`picker.results.many`), or the line saying none matches (`picker.empty`).
 */
private fun pickerEntries(
    searching: Boolean,
    automatic: Boolean,
    opening: List<CarrierPickerSection>,
    letters: List<CarrierPickerSearch.LetterSection>,
    results: List<CarrierPickerSearch.Result>,
    text: (key: String, count: Int) -> String,
): List<PickerEntry> = buildList {
    fun section(id: String, carriers: List<CarrierId>) = carriers.forEachIndexed { index, carrier ->
        add(PickerEntry.Carrier("$id:${carrier.rawValue}", carrier, first = index == 0, last = index == carriers.lastIndex))
    }
    if (!searching) {
        if (automatic) {
            add(PickerEntry.Gap("automatic.gap"))
            add(PickerEntry.Automatic)
        }
        for (opened in opening) {
            add(PickerEntry.Header("section:${opened.id}", opened.title))
            section("section:${opened.id}", opened.carriers)
        }
        for (letter in letters) {
            add(PickerEntry.Header("letter:${letter.letter}", letter.letter, letter.letter))
            section("letter:${letter.letter}", letter.carriers)
        }
    } else if (results.isEmpty()) {
        add(PickerEntry.Gap("empty.gap"))
        add(PickerEntry.Empty("empty", text("picker.empty", 0)))
    } else {
        add(PickerEntry.Header("results", text("picker.results.many", results.size)))
        results.forEachIndexed { index, result ->
            add(
                PickerEntry.Carrier(
                    "results:${result.carrier.rawValue}", result.carrier, first = index == 0, last = index == results.lastIndex,
                    highlight = result.highlight, alias = result.alias,
                ),
            )
        }
    }
}

/** The sections the picker opened with, kept through a window made again as the iPhone's `@State` is. */
private val OPENING_SAVER = listSaver<List<CarrierPickerSection>, List<String>>(
    save = { sections -> sections.map { listOf(it.id, it.title) + it.carriers.map(CarrierId::rawValue) } },
    restore = { saved -> saved.map { CarrierPickerSection(it[0], it[1], it.drop(2).map(::CarrierId)) } },
)

/** The inline bar's row: the title in its middle as a heading, [leading] at its leading end. */
@Composable
private fun PickerBar(title: String, leading: (@Composable () -> Unit)?) {
    val insets = LocalSheetInsets.current
    Box(Modifier.fillMaxWidth().height(BAR_ROW)) {
        // At the default text size whatever the reader's, as UIKit's inline title stands.
        ProvideDynamicTypeSize(DynamicTypeSize.Large) {
            Text(
                title,
                Modifier.align(Alignment.Center).padding(horizontal = insets.barSide + BAR_ROW + TITLE_GAP).semantics { heading() },
                color = PeekTheme.colors.label,
                style = PeekTheme.type.headline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (leading != null) {
            Box(Modifier.align(Alignment.CenterStart).padding(start = insets.barSide)) { leading() }
        }
    }
}

/**
 * The search field under the bar (`.searchable`), as iOS 26 draws it: a 44 capsule of the bar's glass, as the stack's ‹
 * is, the magnifying glass in the label's colour, the prompt in the secondary grey, and once it holds something the
 * button that empties it, in the label's colour. It takes no capitalisation, correction or suggestions
 * (`.autocorrectionDisabled()`, `.textInputAutocapitalization(.never)`, [NoSuggestions]), and the keyboard's Search
 * puts the keyboard away, the search left on. A tap anywhere on the capsule, the magnifying glass or its padding,
 * focuses the field and brings the keyboard, as UISearchTextField's whole frame takes it, with nothing drawn for it and
 * nothing more for TalkBack to read. TalkBack reads it by its prompt. It keeps the focus and the keyboard through the
 * window made again.
 */
@Composable
private fun SearchField(query: String, onQuery: (String) -> Unit, prompt: String, clear: String, onFocus: () -> Unit, modifier: Modifier) {
    val colors = PeekTheme.colors
    val font = PeekTheme.type.body
    val focusManager = LocalFocusManager.current
    val focus = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    GlassSurface(
        CapsuleShape,
        modifier
            .testTag(CARRIER_PICKER_FIELD)
            .pointerInput(focus, keyboard) {
                // The clear button keeps its own taps; on the field, this asks again for what the field's own tap gives.
                detectTapGestures {
                    focus.requestFocus()
                    keyboard?.show()
                }
            },
        backdrop = null,
        behind = colors.groupedBackground,
    ) {
        Row(
            Modifier
                .heightIn(min = SEARCH_HEIGHT)
                .padding(start = SEARCH_LEADING, end = if (query.isEmpty()) SEARCH_LEADING else CLEAR_TRAILING),
            horizontalArrangement = Arrangement.spacedBy(SEARCH_SPACING),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PeekSymbol("magnifyingglass", Modifier.clearAndSetSemantics {}, font = font, tint = colors.label)
            NoSuggestions {
                BasicTextField(
                    query,
                    onValueChange = onQuery,
                    modifier = Modifier
                        .weight(1f)
                        .testTag(CARRIER_PICKER_SEARCH)
                        .focusRequester(focus)
                        .keptFocus()
                        .onFocusChanged { if (it.isFocused) onFocus() }
                        .semantics { contentDescription = prompt },
                    textStyle = font.copy(color = colors.label),
                    singleLine = true,
                    cursorBrush = SolidColor(colors.accent),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrectEnabled = false,
                        imeAction = ImeAction.Search,
                    ),
                    keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
                    decorationBox = { field ->
                        Box(contentAlignment = Alignment.CenterStart) {
                            if (query.isEmpty()) {
                                Text(
                                    prompt, Modifier.clearAndSetSemantics {}, color = colors.secondaryLabel, style = font,
                                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                                )
                            }
                            field()
                        }
                    },
                )
            }
            if (query.isNotEmpty()) {
                Box(
                    Modifier
                        .size(SEARCH_HEIGHT)
                        .testTag(CARRIER_PICKER_CLEAR)
                        .clickable(interactionSource = null, indication = PlainPress) { onQuery("") }
                        .semantics {
                            contentDescription = clear
                            role = Role.Button
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    PeekSymbol("xmark.circle.fill", font = font, tint = colors.label)
                }
            }
        }
    }
}

/**
 * The search's ✕ while it is on, where UISearchBar had its Cancel: a 44 glass circle 10 from the field's trailing end,
 * drawn as the stack's ‹ is, its xmark in the label's colour at Material's lightest weight, the nearest to the iPhone's
 * 2-point stroke. TalkBack reads "Close", as VoiceOver does. The field narrows to make its room as it comes in, on the
 * system's ease (at once under Reduce Motion).
 */
@Composable
private fun SearchClose(shown: Boolean, label: String, onClose: () -> Unit) {
    val colors = PeekTheme.colors
    val reduceMotion = PeekTheme.reduceMotion
    // Unclipped, so that the glass's shadow shows round the circle.
    AnimatedVisibility(
        shown,
        enter = if (reduceMotion) EnterTransition.None else fadeIn(Motion.easeInOut()) + expandHorizontally(Motion.easeInOut(), Alignment.Start, clip = false),
        exit = if (reduceMotion) ExitTransition.None else fadeOut(Motion.easeInOut()) + shrinkHorizontally(Motion.easeInOut(), Alignment.Start, clip = false),
    ) {
        Box(Modifier.padding(start = CLOSE_GAP)) {
            GlassSurface(CapsuleShape, Modifier.size(CLOSE_SIZE), backdrop = null, behind = colors.groupedBackground) {
                Box(
                    Modifier
                        .size(CLOSE_SIZE)
                        .testTag(CARRIER_PICKER_CANCEL)
                        .clickable(interactionSource = null, indication = PressStyle.tactile(CLOSE_PRESSED), onClick = onClose)
                        .semantics {
                            contentDescription = label
                            role = Role.Button
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    PeekSymbol("xmark", font = PeekTheme.type.fixed(CLOSE_SYMBOL, FontWeight.Normal), scale = SymbolScale.Large, tint = colors.label)
                }
            }
        }
    }
}

/**
 * A row of an inset grouped section: inset `start` and `end` from the list's sides, its section's rounded corners on
 * the first's top and the last's bottom, and a hairline under it but the last, from `separatorStart`, where its words
 * begin, to the margin. Its ground and hairline are a form's, on the sheet's glass or its opaque ground (FormSection's),
 * unless it has a `fill` of its own, as the chosen row has (`.listRowBackground`).
 */
@Composable
private fun PickerCell(
    first: Boolean,
    last: Boolean,
    fill: Color?,
    start: Dp,
    end: Dp,
    separatorStart: Dp,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    val colors = PeekTheme.colors
    val attachment = LocalSheetAttachment.current
    val side = LocalSheetInsets.current.formSide
    val top = if (first) SECTION_RADIUS else 0.dp
    val bottom = if (last) SECTION_RADIUS else 0.dp
    Row(
        Modifier
            .padding(start = start, end = end, bottom = if (last) SECTION_BOTTOM else 0.dp)
            .clip(UnevenContinuousRoundedShape(top, bottom, bottom, top))
            .drawBehind {
                val attached = attachment()
                if (fill != null) drawRect(fill) else drawFormRows(Outline.Rectangle(size.toRect()), attached, colors)
                if (last) return@drawBehind
                val line = SEPARATOR.toPx()
                val from = (side + separatorStart).toPx()
                drawFormSeparator(Offset(from, size.height - line), Size(size.width - from - side.toPx(), line), attached, colors)
            }
            .then(modifier)
            .fillMaxWidth()
            .heightIn(min = ROW_HEIGHT)
            .padding(horizontal = side, vertical = ROW_VERTICAL),
        horizontalArrangement = Arrangement.spacedBy(ROW_SPACING),
        verticalAlignment = Alignment.CenterVertically,
        content = content,
    )
}

/**
 * `automaticRow`: the wand in ochre, or a small spinner while the number is being looked up, then "add.detect" in the
 * ink over the "picker.recommended" tag, when it is, beside the description; checked on the ochre surface while chosen.
 * TalkBack reads its words in order, a button, and "selected" only while it is chosen, as `.isSelected` is added only
 * then. At the accessibility text sizes the symbol gives its room to the words and the tag stands above the description.
 */
@Composable
private fun AutomaticRow(automatic: CarrierPickerAutomatic, selected: Boolean, stacked: Boolean, start: Dp, end: Dp, onChoose: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val ochre = colors.resolve(ExperimentalPalette.ochre)
    PickerCell(
        first = true, last = true, if (selected) colors.resolve(ExperimentalPalette.ochreSurface) else null,
        start, end, separatorStart = 0.dp,
        Modifier
            .testTag(CARRIER_PICKER_AUTOMATIC)
            .clickable(interactionSource = null, indication = RowHighlight.Row, role = Role.Button, onClick = onChoose)
            .semantics { if (selected) this.selected = true },
    ) {
        if (!stacked) {
            Box(Modifier.width(MARK_WIDTH), contentAlignment = Alignment.Center) {
                if (automatic.busy) {
                    ActivityIndicator(size = SMALL_SPINNER, tint = colors.secondaryLabel)
                } else {
                    PeekSymbol("wand.and.sparkles", font = type.body, tint = ochre)
                }
            }
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(localized("add.detect"), color = colors.ink, style = type.body.sfLines())
            val description: @Composable (Modifier) -> Unit = { modifier ->
                Text(automatic.description, modifier, color = colors.secondaryLabel, style = type.caption.sfLines())
            }
            if (stacked) {
                Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    if (automatic.recommended) PickerTag(localized("picker.recommended"), found = false, wraps = true)
                    description(Modifier)
                }
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    if (automatic.recommended) PickerTag(localized("picker.recommended"), found = false, wraps = false, Modifier.alignByBaseline())
                    description(Modifier.weight(1f, fill = false).alignByBaseline())
                }
            }
        }
        Spacer(Modifier.width(TRAILING_SPACER))
        if (selected) Checkmark()
    }
}

/**
 * A carrier's row (`row(_:highlight:alias:)`): its truck, its name in the ink with the part the query matched marked
 * in the accent and semibold, and under it the other name it was found by or its countries; its tag at the trailing
 * end, or under the name at the accessibility text sizes; checked on the ochre surface while chosen. The truck's room
 * stays empty until the identities are read. TalkBack reads it
 * as one element (`readsAsItsLabel`): its name, then its subtitle and tag (the label and value VoiceOver reads), a
 * button, and "selected" only on the chosen one. No state description: Compose would read it in place of "selected".
 */
@Composable
private fun CarrierRow(
    entry: PickerEntry.Carrier,
    catalog: CarrierCatalog,
    selected: Boolean,
    tag: CarrierPickerTag?,
    stacked: Boolean,
    start: Dp,
    end: Dp,
    onChoose: () -> Unit,
) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val definition = remember(catalog, entry.carrier, language) { catalog.info(entry.carrier, language) }
    val identity = carrierIdentity(entry.carrier, language)
    val subtitle = remember(definition, entry.alias, localizer, language) { CarrierPickerSearch.subtitle(definition, entry.alias, localizer) }
    val value = CarrierPickerSearch.accessibilityValue(subtitle, tag?.label)
    val mark = colors.accent.copy(alpha = HIGHLIGHT_OPACITY)
    val name = remember(definition, entry.highlight, mark) { highlighted(definition.displayName, entry.highlight, mark) }
    PickerCell(
        entry.first, entry.last, if (selected) colors.resolve(ExperimentalPalette.ochreSurface) else null,
        start, end, separatorStart = MARK_WIDTH + ROW_SPACING,
        Modifier
            .testTag(carrierPickerRowTag(entry.carrier))
            .clickable(interactionSource = null, indication = RowHighlight.Row, role = Role.Button, onClick = onChoose)
            .clearAndSetSemantics {
                contentDescription = listOf(definition.displayName, value).filter(String::isNotEmpty).joinToString(", ")
                if (selected) this.selected = true
            },
    ) {
        if (identity != null) CarrierTruck(identity) else Spacer(Modifier.width(MARK_WIDTH))
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(name, color = colors.ink, style = type.body.sfLines())
            if (subtitle.isNotEmpty()) Text(subtitle, color = colors.secondaryLabel, style = type.caption.sfLines())
            if (stacked && tag != null) PickerTag(tag.label, tag.found, wraps = true, Modifier.padding(top = 3.dp))
        }
        Spacer(Modifier.width(TRAILING_SPACER))
        if (!stacked && tag != null) PickerTag(tag.label, tag.found, wraps = false)
        if (selected) Checkmark()
    }
}

/** `highlighted(_:_:)`: the name, the matched characters on the accent at 0.45 and semibold. */
internal fun highlighted(name: String, highlight: IntRange?, mark: Color): AnnotatedString {
    val range = CarrierPickerSearch.highlightedRange(name, highlight) ?: return AnnotatedString(name)
    return buildAnnotatedString {
        append(name)
        addStyle(SpanStyle(background = mark, fontWeight = FontWeight.SemiBold), range.first, range.last + 1)
    }
}

/**
 * `tag(_:found:wraps:)`: caption2 semibold, in the delivered green on its surface for a carrier that has the parcel,
 * else the secondary grey on cream; on one line in a capsule, or, where it `wraps`, in a box with 8-point corners.
 */
@Composable
private fun PickerTag(label: String, found: Boolean, wraps: Boolean, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    Text(
        label,
        modifier
            .background(
                if (found) colors.resolve(ExperimentalPalette.deliveredSurface) else colors.cream,
                if (wraps) ContinuousRoundedShape(TAG_RADIUS) else CapsuleShape,
            )
            .padding(horizontal = 8.dp, vertical = 2.dp),
        color = if (found) colors.resolve(ExperimentalPalette.delivered) else colors.secondaryLabel,
        style = PeekTheme.type.style(IosTextStyle.Caption2, FontWeight.SemiBold).sfLines(),
        softWrap = wraps,
        maxLines = if (wraps) Int.MAX_VALUE else 1,
    )
}

/** The chosen row's checkmark, body semibold in ochre; TalkBack hears the row selected instead. */
@Composable
private fun Checkmark() {
    PeekSymbol(
        "checkmark", font = PeekTheme.type.style(IosTextStyle.Body, FontWeight.SemiBold),
        tint = PeekTheme.colors.resolve(ExperimentalPalette.ochre),
    )
}

/**
 * `LetterRail`: the A–Z beside the list, in a thumb-wide strip of 44 rather than the system index's thin one. Its rows
 * are as tall as the height allows, 8 to 16, their letters semibold at up to 11 in ochre, the one under the finger on
 * an ochre plate. It answers from the first touch and follows the finger along it: each new letter under the finger
 * jumps the list there (`onLetter`), ticks, and shows in a bubble beside the finger, which goes as the finger lifts;
 * past either end the first or last letter holds. TalkBack reads it as one adjustable element, "picker.jump" and the
 * letter it last jumped to, which swipes up and down move through.
 */
@Composable
private fun LetterRail(letters: List<String>, label: String, onLetter: (String) -> Unit, modifier: Modifier) {
    var current by remember { mutableStateOf<String?>(null) }
    var announced by rememberSaveable { mutableIntStateOf(0) }
    val jump by rememberUpdatedState(onLetter)
    SensoryFeedbackEffect(SensoryFeedback.FrequentTick, current) { _, letter -> letter != null }
    BoxWithConstraints(modifier.width(RAIL_WIDTH)) {
        val rowHeight = ((maxHeight - RAIL_PADDING * 2) / max(letters.size, 1)).coerceIn(8.dp, 16.dp)
        Box(Modifier.align(Alignment.CenterEnd)) {
            Box(
                Modifier
                    .width(RAIL_WIDTH)
                    .testTag(LETTER_RAIL)
                    .pointerInput(letters, rowHeight) {
                        val padding = RAIL_PADDING.toPx()
                        val row = rowHeight.toPx()
                        fun select(y: Float) {
                            if (letters.isEmpty()) return
                            val letter = letters[floor((y - padding) / row).toInt().coerceIn(0, letters.lastIndex)]
                            if (letter == current) return
                            current = letter
                            jump(letter)
                        }
                        awaitEachGesture {
                            val down = awaitFirstDown(requireUnconsumed = false)
                            down.consume()
                            select(down.position.y)
                            try {
                                while (true) {
                                    val change = awaitPointerEvent().changes.firstOrNull { it.id == down.id } ?: break
                                    if (!change.pressed) break
                                    change.consume()
                                    select(change.position.y)
                                }
                            } finally {
                                current = null
                            }
                        }
                    }
                    .clearAndSetSemantics {
                        contentDescription = label
                        stateDescription = letters.getOrNull(announced).orEmpty()
                        if (letters.size > 1) {
                            progressBarRangeInfo = ProgressBarRangeInfo(announced.toFloat(), 0f..letters.lastIndex.toFloat(), letters.size - 2)
                            setProgress { target ->
                                announced = target.roundToInt().coerceIn(0, letters.lastIndex)
                                jump(letters[announced])
                                true
                            }
                        }
                    },
                contentAlignment = Alignment.TopEnd,
            ) {
                RailLetters(letters, current, rowHeight)
            }
            // Beside the rail rather than in it, so TalkBack, which hears the rail as one element, passes it by.
            val index = current?.let(letters::indexOf) ?: -1
            if (index >= 0) LetterBubble(letters[index], y = RAIL_PADDING + rowHeight * index + rowHeight / 2 - BUBBLE_SIDE / 2)
        }
    }
}

/**
 * The rail's letters, the one under the finger on its ochre plate (`RoundedRectangle(cornerRadius: 5)`). Each stands
 * where the finger finds it ([LetterRail]'s `select`): from its own place on the rail, `RAIL_PADDING` and `index` rows
 * of [rowHeight] down, rounded to the pixel once, rather than after rows each rounded, whose error would grow down the
 * rail where a row isn't a whole number of pixels.
 */
@Composable
private fun RailLetters(letters: List<String>, current: String?, rowHeight: Dp) {
    val colors = PeekTheme.colors
    val ochre = colors.resolve(ExperimentalPalette.ochre)
    val font = PeekTheme.type.fixed(min(11f, rowHeight.value * 0.72f), FontWeight.SemiBold)
    Layout(
        content = {
            for (letter in letters) {
                val chosen = letter == current
                Box(
                    if (chosen) Modifier.background(ochre, RoundedCornerShape(5.dp)) else Modifier,
                    contentAlignment = Alignment.Center,
                ) {
                    Text(letter, color = if (chosen) colors.background else ochre, style = font, maxLines = 1, softWrap = false)
                }
            }
        },
        modifier = Modifier.padding(end = 4.dp),
    ) { measurables, _ ->
        val padding = RAIL_PADDING.toPx()
        val row = rowHeight.toPx()
        val width = LETTER_WIDTH.roundToPx()
        fun edge(index: Int) = (padding + row * index).roundToInt()
        val placeables = measurables.mapIndexed { index, letter -> letter.measure(Constraints.fixed(width, edge(index + 1) - edge(index))) }
        layout(width, (padding * 2 + row * letters.size).roundToInt()) {
            placeables.forEachIndexed { index, letter -> letter.place(0, edge(index)) }
        }
    }
}

/** The letter under the finger, title2 semibold in a 52-point circle of ink, 64 to the rail's left, level with it. */
@Composable
private fun LetterBubble(letter: String, y: Dp) {
    val colors = PeekTheme.colors
    Box(
        Modifier
            .offset(x = BUBBLE_X, y = y)
            .wrapContentSize(Alignment.TopStart, unbounded = true)
            .size(BUBBLE_SIDE)
            .background(colors.ink, CircleShape)
            .testTag(LETTER_BUBBLE)
            .clearAndSetSemantics {},
        contentAlignment = Alignment.Center,
    ) {
        Text(letter, color = colors.background, style = PeekTheme.type.style(IosTextStyle.Title2, FontWeight.SemiBold), maxLines = 1)
    }
}

internal const val CARRIER_PICKER = "carrierPicker"
internal const val CARRIER_PICKER_LIST = "carrierPicker.list"
internal const val CARRIER_PICKER_SEARCH = "carrierPicker.search"
internal const val CARRIER_PICKER_FIELD = "carrierPicker.field"
internal const val CARRIER_PICKER_CLEAR = "carrierPicker.clear"
internal const val CARRIER_PICKER_CANCEL = "carrierPicker.cancel"
internal const val CARRIER_PICKER_AUTOMATIC = "carrierPicker.automatic"
internal const val LETTER_RAIL = "carrierPicker.letterRail"
internal const val LETTER_BUBBLE = "carrierPicker.letterBubble"

/** A carrier's row, in whichever section it shows. */
internal fun carrierPickerRowTag(carrier: CarrierId) = "carrierPicker.carrier.${carrier.rawValue}"

/** A section's header: `section:<id>`, `letter:<letter>`, or `results`. */
internal fun carrierPickerHeaderTag(key: String) = "carrierPicker.header.$key"

/** UIKit's cells' layout margins above and below, which a row of two lines shows. */
private val ROW_VERTICAL = 11.dp
private val ROW_SPACING = 12.dp

/**
 * iOS 26's search field: 44 tall, 10 under the bar's row and 16 over the list; the magnifying glass 12 in from its
 * leading end and 8 before the words, the clear button's middle 25 in from its trailing end.
 */
private val SEARCH_HEIGHT = 44.dp
private val SEARCH_TOP = 10.dp
private val SEARCH_BOTTOM = 16.dp
private val SEARCH_LEADING = 12.dp
private val SEARCH_SPACING = 8.dp
private val CLEAR_TRAILING = 3.dp

/** The search's ✕: a 44 circle 10 from the field, its xmark as large as the stack's ‹. */
private val CLOSE_SIZE = 44.dp
private val CLOSE_GAP = 10.dp
private const val CLOSE_SYMBOL = 17f
private const val CLOSE_PRESSED = 0.94f
private val SEARCHING_MARGIN = 20.dp
private val MARK_WIDTH = 27.dp
private val TRAILING_SPACER = 8.dp
private val SMALL_SPINNER = 16.dp
private val TAG_RADIUS = 8.dp
private const val HIGHLIGHT_OPACITY = 0.45f
private val RAIL_WIDTH = 44.dp
private val RAIL_PADDING = 12.dp
private val LETTER_WIDTH = 20.dp
private val BUBBLE_SIDE = 52.dp
private val BUBBLE_X = (-64).dp
