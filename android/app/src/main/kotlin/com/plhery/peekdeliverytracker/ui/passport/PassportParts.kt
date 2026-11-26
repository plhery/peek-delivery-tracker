package com.plhery.peekdeliverytracker.ui.passport

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.FoundationDurations
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.PassportBook
import com.plhery.peekdeliverytracker.core.PassportStatistics
import com.plhery.peekdeliverytracker.core.StampProgress
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.sheet.Popover
import com.plhery.peekdeliverytracker.ui.sheet.PopoverSize
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.MinimumScale
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.text.NumberFormat
import kotlin.math.max

/**
 * `formattedDuration(_:full:)`: under a minute the passport's own words, else Foundation's days and hours or hours and
 * minutes ([FoundationDurations]), spelled out where [full].
 */
internal fun passportDuration(seconds: Double, localizer: Localizer, full: Boolean = false): String =
    if (seconds < 60) {
        localizer.text(if (full) "passport.lessThanOneMinute" else "passport.underOneMinute")
    } else {
        FoundationDurations.string(seconds, localizer.language, full)
    }

/** `expansionValue(_:explanation:)`: collapsed, or expanded and what the popover says. */
private fun expansionValue(expanded: Boolean, explanation: String, localizer: Localizer): String {
    val state = expansionState(expanded, localizer)
    return if (expanded) "$state. $explanation" else state
}

private fun expansionState(expanded: Boolean, localizer: Localizer): String =
    localizer.text(if (expanded) "passport.expansionState.expanded" else "passport.expansionState.collapsed")

/** `PassportCopy.expansionHint(expanded:)`. */
private fun expansionHint(expanded: Boolean, localizer: Localizer): String =
    localizer.text(if (expanded) "passport.expansionHint" else "passport.detailsHint")

/** `Text(_:format: .number)`: a count in the app language's digits and grouping. */
private fun number(count: Int, localizer: Localizer): String = NumberFormat.getIntegerInstance(localizer.language.locale).format(count)

/**
 * A button of the passport's that explains itself: `PassportPressStyle` under the finger, [toggle] on a tap, and read
 * as its [label] alone (`readsAsItsLabel`) with the popover's state as its value and what a tap does as its hint, as
 * iOS's `.accessibilityLabel`, `.accessibilityValue` and `.accessibilityHint` give them.
 */
private fun Modifier.passportButton(label: String, value: String, hint: String, toggle: () -> Unit): Modifier =
    clickable(interactionSource = null, indication = PressStyle.Passport, onClick = toggle)
        .clearAndSetSemantics {
            contentDescription = label
            stateDescription = value
            role = Role.Button
            onClick(label = hint) {
                toggle()
                true
            }
        }

/** Where a view stands in the window, unclipped by the scroll, for the popover's arrow to point at. */
internal fun Modifier.popoverAnchor(onAnchor: (Rect) -> Unit): Modifier = onGloballyPositioned { coordinates ->
    val position = coordinates.positionInWindow()
    onAnchor(Rect(position, Size(coordinates.size.width.toFloat(), coordinates.size.height.toFloat())))
}

/**
 * `.popover(isPresented: presentation(id)) { explanationBubble(...) }` with `.presentationCompactAdaptation(.popover)`:
 * a popover on the paper, over its item with the arrow down at its middle, on a phone too. A tap outside it or the
 * back closes it without the impact.
 */
@Composable
private fun ExplanationPopover(state: PassportState, id: String, anchor: Rect, title: String, explanation: String) =
    ExplanationPopover(state.expanded == id, anchor, { state.dismiss(id) }, title, explanation, PASSPORT_POPOVER)

/**
 * The passport's popover for whoever explains with it, a friend's stamps too: [title] and [explanation] on the paper,
 * over [anchor] with the arrow down at its middle, while [visible]; [onDismiss] as a tap outside it or the back closes
 * it. What it holds is tagged [tag].
 */
@Composable
internal fun ExplanationPopover(visible: Boolean, anchor: Rect, onDismiss: () -> Unit, title: String, explanation: String, tag: String) {
    Popover(
        visible = visible,
        anchor = anchor,
        onDismiss = onDismiss,
        paneTitle = title, id = tag,
        size = BUBBLE_SIZE,
        above = true,
        radius = BUBBLE_RADIUS,
        // One step up from the page's paper, as a sheet's rows are.
        ground = PeekTheme.colors.raised,
    ) {
        ExplanationBubble(title, explanation, tag)
    }
}

/**
 * `explanationBubble(title:explanation:)`: the title as a heading and what it means under it in the ink's secondary,
 * 260 wide and as tall as they are, in by 20.
 */
@Composable
private fun ExplanationBubble(title: String, explanation: String, tag: String) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(Modifier.testTag(tag).fillMaxWidth().padding(BUBBLE_PADDING), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, Modifier.semantics { heading() }, color = colors.ink, style = type.headline.sfLines())
        Text(explanation, color = colors.secondaryText, style = type.subheadline.sfLines())
    }
}

/**
 * `passport(_:)`, the cover: the parcels delivered, large, a new count shown at once, over "Delivered", and the parcel
 * seal tilted at its side (none at the accessibility sizes), on the accent; one button whose popover says what counts.
 */
@Composable
internal fun PassportCover(state: PassportState, stats: PassportStatistics) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val count = stats.deliveredCount
    val delivered = remember(count, localizer, language) { localizer.text("passport.delivered", mapOf("count" to count)) }
    val explanation = remember(localizer, language) { localizer.text("passport.deliveredExplanation") }
    val expanded = state.expanded == PassportSelection.COVER
    var anchor by remember { mutableStateOf(Rect.Zero) }
    val toggle = { state.toggle(PassportSelection.COVER) }
    Box(
        Modifier
            .fillMaxWidth()
            .background(colors.accent, ContinuousRoundedShape(COVER_RADIUS))
            .padding(vertical = 26.dp, horizontal = 24.dp),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 118.dp)
                .popoverAnchor { anchor = it }
                .testTag(PASSPORT_COVER)
                .passportButton(
                    "$count $delivered", expansionValue(expanded, explanation, localizer), expansionHint(expanded, localizer), toggle,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                val size = type.scaled(COUNT_SIZE, IosTextStyle.LargeTitle)
                // `.contentTransition(.numericText())` rolls only in an animated change, and the count never comes in one.
                val style = type.tracking(type.fixed(size, FontWeight.SemiBold), -2f).sfLines()
                Text(number(count, localizer), color = colors.onAccent, style = style)
                Text(delivered, color = colors.onAccent.copy(alpha = 0.7f), style = type.subheadline.sfLines())
            }
            if (!PeekTheme.dynamicTypeSize.isAccessibilitySize) {
                Spacer(Modifier.width(20.dp))
                StampSeal("parcel", null, COVER_SEAL_TINT, Color.White, earned = true, Modifier.rotate(-7f), SealSize.Cover)
            }
        }
    }
    ExplanationPopover(state, PassportSelection.COVER, anchor, delivered, explanation)
}

/**
 * `stamps(_:)`: "Stamps" over the grid of the stamps earned, in the passport's order, and the next three to reach, or all
 * of them once shown; four to a row, three from the xxLarge text size, two at the accessibility sizes. Each is a button
 * whose popover says how it is earned and when, or how far along it is. The ones new since the passport last opened
 * ([fresh]) are pressed in, in page order, and carry a New pill. "All stamps" / "Show less" under the grid while more
 * than three are ahead.
 */
@Composable
internal fun PassportStamps(state: PassportState, book: PassportBook, fresh: Set<String>) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val size = PeekTheme.dynamicTypeSize
    val earned = book.stamps.filter { it.earned }
    val ahead = book.stamps.filter { !it.earned }
    val visible = if (state.showAllStamps) earned + ahead else earned + ahead.take(3)
    val pressed = earned.filter { it.seenKey in fresh }.map { it.key }
    val columns = when {
        size.isAccessibilitySize -> 2
        size >= DynamicTypeSize.XXLarge -> 3
        else -> 4
    }
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(localizer.text("passport.stamps"), color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
        StampGrid(columns) {
            for (stamp in visible) {
                androidx.compose.runtime.key(stamp.key) { Stamp(state, stamp, pressed.indexOf(stamp.key).takeIf { it >= 0 }) }
            }
        }
        if (ahead.size > 3) {
            Text(
                localizer.text(if (state.showAllStamps) "passport.showLess" else "passport.showAll"),
                Modifier
                    .outset(8.dp)
                    .testTag(PASSPORT_SHOW_ALL)
                    .button(interactionSource = null, indication = PressStyle.Passport, role = Role.Button) {
                        state.showAllStamps = !state.showAllStamps
                    }
                    .heightIn(min = 44.dp)
                    .wrapContentHeightCentred(),
                color = colors.secondaryText,
                style = type.caption.sfLines(),
            )
        }
    }
}

/**
 * One stamp: its seal over its name, then the day it was earned ("12 Aug") or how far along it is ("2 of 3", "Not
 * yet"). Read as its name and when it was earned (or its progress), and "New" while it is new; [press] is its place
 * among the seals pressed in. Its details open with `stamp-open` counted, never as they close.
 */
@Composable
private fun Stamp(state: PassportState, stamp: StampProgress, press: Int?) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val now = LocalNow.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val id = PassportSelection.milestone(stamp.key)
    val expanded = state.expanded == id
    var anchor by remember { mutableStateOf(Rect.Zero) }
    val title = stampTitle(stamp.key, localizer)
    val earnedLine = stampEarnedLine(stamp, localizer, language)
    val progress = stampProgress(stamp, localizer)
    val under = if (stamp.earned) stamp.earnedAt?.let { stampDay(it, language, now()) } else progress
    val label = listOfNotNull(title, if (stamp.earned) earnedLine else progress, if (press != null) localizer.text("passport.new") else null).joinToString(", ")
    val explanation = stampExplanation(stamp.key, localizer)
    val details = listOfNotNull(
        explanation,
        if (stamp.earned) earnedLine?.let { "$it · ${stamp.label?.ifEmpty { null } ?: localizer.text("common.parcel")}" } else progress,
    ).joinToString("\n")
    val seal = rememberSealPress(press)
    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 96.dp)
            .popoverAnchor { anchor = it }
            .testTag("$PASSPORT_STAMP.${stamp.key}")
            .passportButton(label, expansionValue(expanded, details, localizer), expansionHint(expanded, localizer)) {
                if (!expanded) state.source.action("stamp-open")
                state.toggle(id)
            },
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            StampSeal(stamp.definition, stamp.earned, press = seal)
            Spacer(Modifier.height(10.dp))
            Text(title, color = if (stamp.earned) colors.ink else colors.secondaryText, style = type.caption2.sfLines(), textAlign = TextAlign.Center)
            if (under != null) {
                Spacer(Modifier.height(2.dp))
                Text(under, color = colors.secondaryText, style = type.caption2.monospacedDigit().sfLines(), textAlign = TextAlign.Center)
            }
        }
        if (press != null) {
            NewPill(
                seal,
                Modifier.align(Alignment.TopStart).layout { measurable, constraints ->
                    // Its trailing edge 38 past the stamp's middle, 6 over the seal's top.
                    val pill = measurable.measure(constraints.copy(minWidth = 0, minHeight = 0))
                    layout(constraints.maxWidth, pill.height) {
                        pill.place(constraints.maxWidth / 2 + NEW_PILL_REACH.roundToPx() - pill.width, (-6).dp.roundToPx())
                    }
                },
            )
        }
    }
    ExplanationPopover(state, id, anchor, title, details)
}

/** The New pill over a fresh seal's corner, on the accent, popping in as the seal is postmarked (416 ms into its press). */
@Composable
private fun NewPill(seal: SealPress, modifier: Modifier) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    Text(
        localizer.text("passport.new"),
        modifier
            .graphicsLayer {
                val pop = phase(seal.time, 416f, 200f, PassportSettle)
                scaleX = 0.6f + 0.4f * pop
                scaleY = scaleX
                alpha = pop
            }
            .clearAndSetSemantics {}
            .background(colors.accent, RoundedCornerShape(8.dp))
            .padding(horizontal = 6.dp, vertical = 1.dp),
        color = colors.onAccent,
        style = PeekTheme.type.fixed(9f, FontWeight.SemiBold).sfLines(),
    )
}

/**
 * `LazyVGrid` of `columns` flexible columns 8 apart, rows 16 apart, each stamp at the top of its row: every column as
 * wide as the others.
 */
@Composable
internal fun StampGrid(columns: Int, content: @Composable () -> Unit) {
    Layout(content) { measurables, constraints ->
        val gap = 8.dp.roundToPx()
        val rowGap = 16.dp.roundToPx()
        val width = constraints.maxWidth
        val column = max(0, (width - gap * (columns - 1)) / columns)
        val placeables = measurables.map { it.measure(Constraints(minWidth = column, maxWidth = column)) }
        val rows = placeables.chunked(columns)
        val heights = rows.map { row -> row.maxOf { it.height } }
        val height = heights.sum() + rowGap * max(0, rows.size - 1)
        layout(width, height) {
            var y = 0
            rows.forEachIndexed { index, row ->
                row.forEachIndexed { at, placeable -> placeable.place(at * (column + gap), y) }
                y += heights[index] + rowGap
            }
        }
    }
}

/**
 * `deliveryTimes(_:)`: between two dividers, the average delivery time and the fastest side by side (one over the other
 * at the accessibility sizes), each a button whose popover says how it is counted; "Waiting for a complete journey"
 * until a delivered parcel has both its first scan and its delivery.
 */
@Composable
internal fun PassportTimes(state: PassportState, stats: PassportStatistics) {
    val localizer = LocalLocalizer.current
    // The words follow the app's language as it changes.
    LocalLanguage.current
    val colors = PeekTheme.colors
    val average = stats.averageDeliveryDuration
    val fastest = stats.fastestDelivery
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    Column {
        Hairline()
        Spacer(Modifier.height(21.dp))
        if (average != null && fastest != null) {
            val timing = localizer.text("passport.timingExplanation")
            val count = stats.durationSampleCount
            val journeys = localizer.text(if (count == 1) "passport.timedJourneys.one" else "passport.timedJourneys.many", mapOf("count" to count))
            val averageCard: @Composable (Modifier) -> Unit = {
                TimeCard(state, PassportSelection.AVERAGE, PASSPORT_AVERAGE, localizer.text("passport.average"), average, "$journeys. $timing", it)
            }
            val title = fastest.label.ifEmpty { localizer.text("common.parcel") }
            val fastestCard: @Composable (Modifier) -> Unit = {
                TimeCard(
                    state, PassportSelection.PERSONAL_BEST, PASSPORT_PERSONAL_BEST, localizer.text("passport.personalBest"), fastest.duration,
                    "$title · ${localizer.shortDate(fastest.deliveredAt)}\n\n$timing", it,
                )
            }
            if (largeText) {
                Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                    averageCard(Modifier.fillMaxWidth())
                    fastestCard(Modifier.fillMaxWidth())
                }
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(18.dp), verticalAlignment = Alignment.Top) {
                    averageCard(Modifier.weight(1f))
                    fastestCard(Modifier.weight(1f))
                }
            }
        } else {
            Text(localizer.text("passport.waitingForTimes"), color = colors.secondaryText, style = PeekTheme.type.footnote.sfLines())
        }
        Spacer(Modifier.height(21.dp))
        Hairline()
    }
}

/**
 * `timeCard(id:title:duration:explanation:)`: the time, shrinking to three quarters rather than wrap (but at the
 * accessibility sizes, where it wraps), over what it is, read as both with the time spelled out.
 */
@Composable
private fun TimeCard(state: PassportState, id: String, tag: String, title: String, duration: Double, explanation: String, modifier: Modifier) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val expanded = state.expanded == id
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    var anchor by remember { mutableStateOf(Rect.Zero) }
    Box(
        modifier
            .heightIn(min = 48.dp)
            .popoverAnchor { anchor = it }
            .testTag(tag)
            .passportButton(
                "$title, ${passportDuration(duration, localizer, full = true)}", expansionValue(expanded, explanation, localizer),
                expansionHint(expanded, localizer),
            ) { state.toggle(id) },
        contentAlignment = Alignment.CenterStart,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            val style = type.style(IosTextStyle.Title2, FontWeight.Medium).monospacedDigit().sfLines()
            if (largeText) {
                Text(passportDuration(duration, localizer), color = colors.ink, style = style)
            } else {
                Text(
                    passportDuration(duration, localizer), color = colors.ink, style = style, maxLines = 1, softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                    autoSize = MinimumScale(style.fontSize, MINIMUM_SCALE),
                )
            }
            Text(title, color = colors.secondaryText, style = type.caption.sfLines())
        }
    }
    ExplanationPopover(state, id, anchor, title, explanation)
}

/**
 * `countries(_:)`: "First scanned in", a button whose popover says what it means, over the three countries the most
 * parcels were first scanned in, each with its flag (hidden from TalkBack) and its count, read as its name and its
 * parcels.
 */
@Composable
internal fun PassportCountries(state: PassportState, stats: PassportStatistics) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val title = localizer.text("passport.firstSeenIn")
    val explanation = localizer.text("passport.countryExplanation")
    val expanded = state.expanded == PassportSelection.COUNTRIES
    var anchor by remember { mutableStateOf(Rect.Zero) }
    val toggle = { state.toggle(PassportSelection.COUNTRIES) }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            title,
            Modifier
                .popoverAnchor { anchor = it }
                .outset(10.dp)
                .testTag(PASSPORT_COUNTRIES)
                .button(interactionSource = null, indication = PressStyle.Passport, onClick = toggle)
                .clearAndSetSemantics {
                    contentDescription = title
                    stateDescription = expansionState(expanded, localizer)
                    role = Role.Button
                    onClick(label = expansionHint(expanded, localizer)) {
                        toggle()
                        true
                    }
                }
                .heightIn(min = 44.dp)
                .wrapContentHeightCentred(),
            color = colors.ink,
            style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines(),
        )
        Column {
            stats.originCountries.take(3).forEachIndexed { index, country ->
                val name = TrackingLocation.countryName(country.code, language)
                val parcels = localizer.text(if (country.count == 1) "passport.parcels.one" else "passport.parcels.many", mapOf("count" to country.count))
                if (index > 0) Hairline()
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                        .heightIn(min = 44.dp)
                        .clearAndSetSemantics { contentDescription = "$name, $parcels" },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(country.flag, color = colors.label, style = type.subheadline.sfLines())
                    Spacer(Modifier.width(11.dp))
                    // `Spacer(minLength: 8)`: the count at the trailing edge, at least 8 clear of the name.
                    Text(name, Modifier.weight(1f), color = colors.label, style = type.subheadline.sfLines())
                    Spacer(Modifier.width(8.dp))
                    Text(number(country.count, localizer), color = colors.secondaryText, style = type.caption.monospacedDigit().sfLines())
                }
            }
        }
    }
    ExplanationPopover(state, PassportSelection.COUNTRIES, anchor, title, explanation)
}

/** SwiftUI's `Divider()`: a pixel's line in the system's divider colour. */
@Composable
private fun Hairline() {
    val hairline = with(LocalDensity.current) { 1.toDp() }
    Box(Modifier.fillMaxWidth().height(hairline).background(PeekTheme.colors.divider))
}

/**
 * `.padding(.vertical, -outset)`: the view takes `outset` less room above and below than it has, standing over its
 * neighbours by as much, as the passport's 44-point buttons do round their words.
 */
private fun Modifier.outset(outset: Dp): Modifier = layout { measurable, constraints ->
    val inset = outset.roundToPx()
    val placeable = measurable.measure(constraints.copy(minHeight = 0, maxHeight = Constraints.Infinity))
    val height = max(0, placeable.height - 2 * inset)
    layout(placeable.width, height) { placeable.place(0, -inset) }
}

/** `.frame(minHeight: 44, alignment: .leading)` round words: the words in the middle of the height. */
private fun Modifier.wrapContentHeightCentred(): Modifier = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints.copy(minHeight = 0))
    val height = max(placeable.height, constraints.minHeight)
    layout(placeable.width, height) { placeable.place(0, (height - placeable.height) / 2) }
}

/** The cover seal's ochre, the same in dark, on white paper. */
private val COVER_SEAL_TINT = Color(0xFF745300)
/** How far the New pill's trailing edge stands past the stamp's middle. */
private val NEW_PILL_REACH = 38.dp
private val COVER_RADIUS = 20.dp
private const val COUNT_SIZE = 54f
/** `minimumScaleFactor(0.75)` of a time on one line. */
private const val MINIMUM_SCALE = 0.75f
private val BUBBLE_WIDTH = 260.dp
private val BUBBLE_PADDING = 20.dp
private val BUBBLE_RADIUS = 26.dp
private val BUBBLE_SIZE = PopoverSize(minWidth = 200.dp, idealWidth = BUBBLE_WIDTH + BUBBLE_PADDING * 2, minHeight = 60.dp, idealHeight = Dp.Unspecified)

/** The passport's screen tag, which its items' tags start with: hooks for tests, not copy keys. */
internal const val PASSPORT = "passport"

/** The test tags of the passport's items and its popovers. */
const val PASSPORT_COVER = "$PASSPORT.cover"
const val PASSPORT_STAMP = "$PASSPORT.stamp"
const val PASSPORT_SHOW_ALL = "$PASSPORT.showAll"
const val PASSPORT_AVERAGE = "$PASSPORT.average"
const val PASSPORT_PERSONAL_BEST = "$PASSPORT.personalBest"
const val PASSPORT_COUNTRIES = "$PASSPORT.countries"
const val PASSPORT_POPOVER = "$PASSPORT.popover"
