package com.plhery.peekdeliverytracker.ui.passport

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
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
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.FoundationDurations
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.PassportStatistics
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.DieCutStampShape
import com.plhery.peekdeliverytracker.ui.sheet.Popover
import com.plhery.peekdeliverytracker.ui.sheet.PopoverSize
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.text.NumberFormat
import kotlin.math.max
import kotlin.math.min

/** `PassportMilestone`: a stamp, earned once its count reaches its target. */
internal class PassportMilestone(
    val id: String,
    val title: String,
    val symbol: String,
    val tint: AdaptiveColor,
    val surface: AdaptiveColor,
    val current: Int,
    val target: Int,
    val explanation: String,
    private val pendingLabel: String? = null,
) {
    val earned: Boolean get() = current >= target
    val progressLabel: String get() = pendingLabel ?: "${min(current, target)} / $target"
}

/** `milestones(_:)`: the twelve stamps in their order. */
internal fun passportMilestones(stats: PassportStatistics, carrierCount: Int, localizer: Localizer): List<PassportMilestone> {
    fun text(key: String) = localizer.text("passport.$key")
    val palette = ExperimentalPalette
    val fastest = stats.fastestDelivery
    return listOf(
        PassportMilestone("first", text("firstArrival"), "shippingbox", palette.delivered, palette.deliveredSurface, stats.deliveredCount, 1, text("firstExplanation")),
        PassportMilestone("ten", text("doubleDigits"), "10", palette.lilac, palette.lilacSurface, stats.deliveredCount, 10, text("tenExplanation")),
        PassportMilestone("carriers", text("wellConnected"), "globe", palette.transit, palette.transitSurface, carrierCount, 3, text("carrierExplanation")),
        PassportMilestone(
            "speed", text("expressArrival"), "bolt", palette.pickup, palette.pickupSurface,
            if (fastest != null && fastest.duration <= 48 * 60 * 60) 1 else 0, 1, text("expressExplanation"), text("underTwoDays"),
        ),
        PassportMilestone("acrossBorders", text("acrossBorders"), "globe.europe.africa", palette.transit, palette.transitSurface, stats.crossBorderCount, 1, text("acrossExplanation")),
        PassportMilestone("aroundWorld", text("aroundWorld"), "map", palette.delivered, palette.deliveredSurface, stats.originCountries.size, 5, text("aroundExplanation")),
        PassportMilestone("theRegular", text("theRegular"), "25", palette.lilac, palette.lilacSurface, stats.deliveredCount, 25, text("regularExplanation")),
        PassportMilestone("rightNextDoor", text("rightNextDoor"), "house", palette.delivered, palette.deliveredSurface, stats.domesticDeliveryCount, 1, text("domesticExplanation")),
        PassportMilestone("worthTheWait", text("worthTheWait"), "hourglass", palette.ochre, palette.ochreSurface, stats.longWaitDeliveryCount, 1, text("waitExplanation")),
        PassportMilestone("busyDoorstep", text("busyDoorstep"), "parcels", palette.pickup, palette.pickupSurface, stats.maxDeliveriesInOneDay, 3, text("busyExplanation")),
        PassportMilestone("pickedUp", text("pickedUp"), "storefront", palette.transit, palette.transitSurface, stats.pickupDeliveryCount, 1, text("pickupExplanation")),
        PassportMilestone("homeForHolidays", text("homeForHolidays"), "gift", palette.delivered, palette.deliveredSurface, stats.decemberDeliveryCount, 1, text("holidayExplanation")),
    )
}

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
        paneTitle = title,
        size = BUBBLE_SIZE,
        above = true,
        radius = BUBBLE_RADIUS,
        ground = PeekTheme.colors.paper,
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
        Text(explanation, color = colors.ink.copy(alpha = SECONDARY), style = type.subheadline.sfLines())
    }
}

/**
 * `passport(_:)`, the cover: the parcels delivered, large, rolling to a new count, over "Delivered", and the parcel
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
                RollingCount(number(count, localizer), type.tracking(type.fixed(size, FontWeight.SemiBold), -2f).sfLines(), colors.onAccent)
                Text(delivered, color = colors.onAccent.copy(alpha = 0.7f), style = type.subheadline.sfLines())
            }
            if (!PeekTheme.dynamicTypeSize.isAccessibilitySize) {
                Spacer(Modifier.width(20.dp))
                PassportSeal(
                    "shippingbox", tint = COVER_SEAL_TINT, surface = Color.White, earned = true, cancelled = true,
                    modifier = Modifier.size(66.dp, 80.dp).rotate(-7f),
                )
            }
        }
    }
    ExplanationPopover(state, PassportSelection.COVER, anchor, delivered, explanation)
}

/**
 * `.contentTransition(.numericText())`: as the count changes, each figure that changes rolls, the new one up from under
 * the old as it goes up and fades; the others stay. None under Reduce Motion. Figures are matched from the right, as
 * the units, tens and hundreds stand.
 */
@Composable
private fun RollingCount(text: String, style: androidx.compose.ui.text.TextStyle, color: Color) {
    val reduceMotion = PeekTheme.reduceMotion
    Row(Modifier.clipToBounds()) {
        // From the left: the figures a longer count adds come in at its left.
        for (fromRight in text.length - 1 downTo 0) {
            val character = text[text.length - 1 - fromRight]
            androidx.compose.runtime.key(fromRight) {
                AnimatedContent(
                    targetState = character,
                    transitionSpec = {
                        if (reduceMotion) {
                            ContentTransform(EnterTransition.None, ExitTransition.None, sizeTransform = null)
                        } else {
                            (slideInVertically(tween(ROLL_MILLIS)) { it } + fadeIn(tween(ROLL_MILLIS)))
                                .togetherWith(slideOutVertically(tween(ROLL_MILLIS)) { -it } + fadeOut(tween(ROLL_MILLIS)))
                        }
                    },
                    label = "figure",
                ) { figure ->
                    Text(figure.toString(), color = color, style = style, maxLines = 1, softWrap = false)
                }
            }
        }
    }
}

/**
 * `stamps(_:)`: "Stamps" over the grid of the stamps earned and the next three, or all twelve once shown; four to a
 * row, three from the xxLarge text size, two at the accessibility sizes. Each is a button whose popover says how it is
 * earned and, until it is, how far along it is. "All stamps" / "Show less" under the grid while some are left out.
 */
@Composable
internal fun PassportStamps(state: PassportState, stats: PassportStatistics, carrierCount: Int) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val size = PeekTheme.dynamicTypeSize
    val collection = remember(stats, carrierCount, localizer, language) { passportMilestones(stats, carrierCount, localizer) }
    val upcoming = collection.filter { !it.earned }.take(3).map { it.id }.toSet()
    val visible = if (state.showAllStamps) collection else collection.filter { it.earned || it.id in upcoming }
    val hasMore = collection.any { !it.earned && it.id !in upcoming }
    val columns = when {
        size.isAccessibilitySize -> 2
        size >= DynamicTypeSize.XXLarge -> 3
        else -> 4
    }
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(localizer.text("passport.stamps"), color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
        StampGrid(columns) {
            for (milestone in visible) {
                androidx.compose.runtime.key(milestone.id) { Stamp(state, milestone) }
            }
        }
        if (hasMore) {
            Text(
                localizer.text(if (state.showAllStamps) "passport.showLess" else "passport.showAll"),
                Modifier
                    .outset(8.dp)
                    .testTag(PASSPORT_SHOW_ALL)
                    .clickable(interactionSource = null, indication = PressStyle.Passport, role = Role.Button) {
                        state.showAllStamps = !state.showAllStamps
                    }
                    .heightIn(min = 44.dp)
                    .wrapContentHeightCentred(),
                color = colors.secondaryLabel,
                style = type.caption.sfLines(),
            )
        }
    }
}

/** One stamp: its seal over its title, the title faded until it is earned. */
@Composable
private fun Stamp(state: PassportState, milestone: PassportMilestone) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val id = PassportSelection.milestone(milestone.id)
    val expanded = state.expanded == id
    var anchor by remember { mutableStateOf(Rect.Zero) }
    val label = if (milestone.earned) milestone.title else "${milestone.title}, ${milestone.progressLabel}"
    Column(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 96.dp)
            .popoverAnchor { anchor = it }
            .testTag("$PASSPORT_STAMP.${milestone.id}")
            .passportButton(label, expansionValue(expanded, milestone.explanation, localizer), expansionHint(expanded, localizer)) { state.toggle(id) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        PassportSeal(
            milestone.symbol, colors.resolve(milestone.tint), colors.resolve(milestone.surface), milestone.earned, modifier = Modifier.size(50.dp, 64.dp),
        )
        Text(
            milestone.title,
            color = if (milestone.earned) colors.ink else colors.ink.copy(alpha = 0.6f),
            style = PeekTheme.type.caption2.sfLines(),
            textAlign = TextAlign.Center,
        )
    }
    ExplanationPopover(
        state, id, anchor, milestone.title, milestone.explanation + if (milestone.earned) "" else "\n${milestone.progressLabel}",
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
            Text(localizer.text("passport.waitingForTimes"), color = colors.secondaryLabel, style = PeekTheme.type.footnote.sfLines())
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
                    autoSize = TextAutoSize.StepBased(minFontSize = style.fontSize * MINIMUM_SCALE, maxFontSize = style.fontSize),
                )
            }
            Text(title, color = colors.ink.copy(alpha = SECONDARY), style = type.caption.sfLines())
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
                .clickable(interactionSource = null, indication = PressStyle.Passport, onClick = toggle)
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
                    Text(number(country.count, localizer), color = colors.secondaryLabel, style = type.caption.monospacedDigit().sfLines())
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

/**
 * `PassportSeal`: a die-cut stamp on its surface with a frame inset 6 in its tint, its symbol ("10", "25", three boxes
 * for "parcels", else an SF Symbol) in its tint at ultralight, and the cancellation's two rings over its lower trailing
 * corner where [cancelled]. Unearned, it is a third as opaque and grey. Hidden from TalkBack: the button says it.
 */
@Composable
internal fun PassportSeal(symbol: String, tint: Color, surface: Color, earned: Boolean, modifier: Modifier = Modifier, cancelled: Boolean = false) {
    val type = PeekTheme.type
    val shade = { color: Color -> if (earned) color else color.grey().let { it.copy(alpha = it.alpha * UNEARNED_OPACITY) } }
    val ink = shade(tint)
    Box(modifier.clearAndSetSemantics {}, contentAlignment = Alignment.Center) {
        Box(Modifier.fillMaxSize().background(shade(surface), DieCutStampShape()))
        Canvas(Modifier.fillMaxSize().padding(6.dp)) {
            val line = 0.7.dp.toPx()
            drawRect(shade(tint.copy(alpha = 0.4f * tint.alpha)), Offset(line / 2, line / 2), Size(size.width - line, size.height - line), style = Stroke(line))
        }
        when (symbol) {
            "10", "25" -> Text(symbol, color = ink, style = type.fixed(20f, FontWeight.Light).sfLines())
            "parcels" -> Box(contentAlignment = Alignment.Center) {
                val font = type.fixed(13f, FontWeight.Thin)
                PeekSymbol("shippingbox", Modifier.offset(y = (-7).dp), font = font, tint = ink)
                PeekSymbol("shippingbox", Modifier.offset(x = (-7).dp, y = 6.dp), font = font, tint = ink)
                PeekSymbol("shippingbox", Modifier.offset(x = 7.dp, y = 6.dp), font = font, tint = ink)
            }
            else -> PeekSymbol(symbol, font = type.fixed(22f, FontWeight.Thin), tint = ink)
        }
        if (cancelled) {
            Canvas(Modifier.align(Alignment.BottomEnd).offset(x = 3.dp, y = (-5).dp).size(31.dp)) {
                val line = 0.7.dp.toPx()
                val ring = shade(tint.copy(alpha = 0.2f * tint.alpha))
                drawCircle(ring, radius = size.minDimension / 2, style = Stroke(line))
                drawCircle(ring, radius = size.minDimension / 2 - 4.dp.toPx(), style = Stroke(line))
            }
        }
    }
}

/** `.saturation(0)`: the colour's luminance as grey, by the weights of Android's ColorMatrix.setSaturation. */
private fun Color.grey(): Color {
    val luminance = 0.213f * red + 0.715f * green + 0.072f * blue
    return Color(luminance, luminance, luminance, alpha)
}

/** The cover seal's ochre, the same in dark, and its paper. */
private val COVER_SEAL_TINT = Color(0xFF745300)
private val COVER_RADIUS = 20.dp
private const val COUNT_SIZE = 54f
private const val ROLL_MILLIS = 350
/** `.secondary` over the ink: the ink at half. */
private const val SECONDARY = 0.5f
private const val UNEARNED_OPACITY = 0.3f
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
