package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.snap
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collapse
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.expand
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.JournalDay
import com.plhery.peekdeliverytracker.core.JournalEntry
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PlannedTrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentEvent
import com.plhery.peekdeliverytracker.core.displayStatus
import com.plhery.peekdeliverytracker.core.journalDays
import com.plhery.peekdeliverytracker.core.journalEntries
import com.plhery.peekdeliverytracker.core.journalLocation
import com.plhery.peekdeliverytracker.core.journalTime
import com.plhery.peekdeliverytracker.core.journalWording
import com.plhery.peekdeliverytracker.core.aheadUntil
import com.plhery.peekdeliverytracker.core.isAhead
import com.plhery.peekdeliverytracker.core.plannedNewestFirst
import com.plhery.peekdeliverytracker.core.plannedWording
import com.plhery.peekdeliverytracker.core.predictedDate
import com.plhery.peekdeliverytracker.core.predictedStepLabel
import com.plhery.peekdeliverytracker.core.relayedWordings
import com.plhery.peekdeliverytracker.core.showsTime
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.LocalUse24HourTime
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import kotlinx.coroutines.delay

/**
 * `ParcelJournal` (B-S14): the parcel's tracking history, newest first and grouped by day ([journalDays]), named
 * again at each midnight while it stays open, 2 in from its sides. Its header shows or hides the days (B-G15), which
 * show at first and stay as they were through a window made again (B-SE11). They fade in and out where they stand while the journal's height, and what is under it, move
 * over 0.2 s easeInOut, nothing clipped; at once under Reduce Motion (B-AN10). Without a row it says the carrier is
 * being asked while the parcel syncs, and that there is nothing yet otherwise. Each day is headed in caption2 capitals
 * tracked 1 in the secondary grey, 22 under what comes before and 13 over its rows, which stand 18 apart; TalkBack
 * hears the heading as one, as written rather than in capitals. The row of the current scan, or of the scan whose copy it is, has its time in `tint`.
 * Before the days, apart from the scans, one line says the carrier's site shows steps that are only predictions, and
 * keeps them folded behind it ([PredictedSteps]): those it planned and has not confirmed, and those it wrote in advance
 * whose time has passed (`writtenAheadEvents`). A carrier that writes its route ahead (`writtenAheadBy`) gets the line
 * even with no step to list ([PredictedNote]). Where a scan or such a step shows a time of day, a line in caption2 in
 * the opaque secondary grey, 22 under the last row, says the times are the phone's and names its zone as the app
 * language names it all year ([Localizer.timeZoneName]).
 */
@Composable
internal fun ParcelJournal(parcel: Parcel, tint: Color, modifier: Modifier = Modifier) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val now = LocalNow.current
    val entries = remember(parcel) { parcel.journalEntries }
    // Worked out again on the day it has become and in the zone the phone is in, as the iPhone works them out each time:
    // the history also draws again at each midnight while it stays open, its parcel changed or not.
    val zone = ZoneId.systemDefault()
    val redraw = currentRecomposeScope
    LaunchedEffect(zone, now) {
        while (true) {
            val at = now()
            delay(Duration.between(at, at.atZone(zone).toLocalDate().plusDays(1).atStartOfDay(zone).toInstant()).toMillis())
            redraw.invalidate()
        }
    }
    val today = now().atZone(zone).toLocalDate()
    val days = remember(localizer, language, entries, zone, today) { localizer.journalDays(entries, now()) }
    // The planned steps still ahead, read again when the first of them passes while the history stays open.
    var plannedClock by remember { mutableStateOf(now()) }
    val moment = maxOf(now(), plannedClock)
    val planned = parcel.plannedEvents?.filter { it.isAhead(moment) }.orEmpty()
    val nextPlannedEnd = planned.mapNotNull { it.aheadUntil }.minOrNull()
    LaunchedEffect(nextPlannedEnd, now) {
        val end = nextPlannedEnd ?: return@LaunchedEffect
        while (now() < end) delay(maxOf(Duration.between(now(), end).toMillis(), 100))
        plannedClock = now()
    }
    // The steps the carrier's site shows that no scan confirms: those still ahead, and those it wrote in advance.
    val predicted = planned + parcel.writtenAheadEvents.orEmpty()
    var predictedOpen by rememberSaveable { mutableStateOf(false) }
    // A copy the journal shows under its scan is current through that scan.
    val currentId = remember(parcel, entries) {
        parcel.currentEvent?.let { current -> entries.firstOrNull { it.id == current.id || current in it.relays }?.id }
    }
    val syncing = parcel.displayStatus.syncing
    var full by rememberSaveable { mutableStateOf(true) }
    Column(modifier.padding(horizontal = 2.dp)) {
        JournalHeader(entries.size, full, reduceMotion) { full = !full }
        AnimatedVisibility(
            visible = full,
            enter = if (reduceMotion) EnterTransition.None else
                fadeIn(Motion.easeInOut(TOGGLE)) + expandVertically(Motion.easeInOut<IntSize>(TOGGLE), Alignment.Top, clip = false),
            exit = if (reduceMotion) ExitTransition.None else
                fadeOut(Motion.easeInOut(TOGGLE)) + shrinkVertically(Motion.easeInOut<IntSize>(TOGGLE), Alignment.Top, clip = false),
        ) {
            Column(Modifier.fillMaxWidth()) {
                if (predicted.isNotEmpty()) {
                    PredictedSteps(predicted, moment, open = predictedOpen) { predictedOpen = !predictedOpen }
                } else if (parcel.writtenAheadBy != null) {
                    PredictedNote()
                }
                if (days.isEmpty()) {
                    Text(
                        localizer.text(if (syncing) "timeline.emptySyncing" else "timeline.empty"),
                        Modifier.padding(vertical = 12.dp),
                        color = colors.secondaryText,
                        style = type.footnote.sfLines(),
                    )
                }
                for (day in days) {
                    if (day.label.isEmpty()) {
                        // A scan without a date or a day: SwiftUI's empty text takes no room, its padding does.
                        Spacer(Modifier.height(22.dp + 13.dp))
                    } else {
                        JournalHeading(day.label, bottom = 13.dp, Modifier.testTag(JOURNAL_DAY))
                    }
                    JournalDayRows(day, tint, currentId, syncing)
                }
                // The times are the phone's, wherever the carrier scanned: one quiet line under the history says so.
                if (entries.any { it.event.showsTime } || predicted.any { it.showsTime }) {
                    val zoneName = remember(localizer, language, zone) { localizer.timeZoneName(zone) }
                    Text(
                        localizer.text("timeline.timeZone", mapOf("zone" to zoneName)),
                        Modifier
                            .padding(top = 22.dp)
                            .testTag(JOURNAL_TIME_ZONE),
                        color = colors.secondaryText,
                        style = type.caption2.sfLines(),
                    )
                }
            }
        }
    }
}

/**
 * A day's heading in the journal: `label` in caption2 capitals tracked 1 in the secondary grey, 22 under what comes
 * before and `bottom` over what follows. TalkBack hears it as one heading, as written rather than in capitals.
 */
@Composable
private fun JournalHeading(label: String, bottom: Dp, modifier: Modifier = Modifier) {
    val type = PeekTheme.type
    Text(
        label.uppercase(LocalLanguage.current.locale),
        // CoreText tracks after each letter, Android half before it: the words start where the iPhone's start.
        Modifier
            .padding(top = 22.dp, bottom = bottom)
            .offset(x = (-0.5).dp)
            .then(modifier)
            // Capitals only to the eye, as `.textCase` draws them: TalkBack hears the day as written.
            .clearAndSetSemantics {
                heading()
                text = AnnotatedString(label)
            },
        color = PeekTheme.colors.secondaryText,
        style = type.tracking(type.caption2, 1f).sfLines(),
    )
}

/**
 * `PredictedSteps`: the steps the carrier's site shows that no scan confirms, folded behind one line that says they're
 * only predictions ([PredictedLine]), closed at first. Open, 8 under it, they list newest first ([plannedNewestFirst]),
 * 18 apart ([PredictedStepRow]), and a dashed line in the separator's colour, 1 high, closes them 18 under the last.
 * They fade in and out over 0.2 s easeInOut as the history's days do, at once under Reduce Motion. They are no scans
 * and count as no update.
 */
@Composable
private fun PredictedSteps(steps: List<PlannedTrackingEvent>, now: Instant, open: Boolean, onToggle: () -> Unit) {
    val colors = PeekTheme.colors
    val reduceMotion = PeekTheme.reduceMotion
    val ordered = remember(steps) { plannedNewestFirst(steps) }
    Column(Modifier.fillMaxWidth()) {
        PredictedLine(open, onToggle)
        AnimatedVisibility(
            visible = open,
            enter = if (reduceMotion) EnterTransition.None else
                fadeIn(Motion.easeInOut(TOGGLE)) + expandVertically(Motion.easeInOut<IntSize>(TOGGLE), Alignment.Top, clip = false),
            exit = if (reduceMotion) ExitTransition.None else
                fadeOut(Motion.easeInOut(TOGGLE)) + shrinkVertically(Motion.easeInOut<IntSize>(TOGGLE), Alignment.Top, clip = false),
        ) {
            Column(Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                    ordered.forEachIndexed { index, step -> PredictedStepRow(step, joinsNext = index < ordered.lastIndex, now = now) }
                }
                val separator = colors.separator
                Canvas(
                    Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth()
                        .height(1.dp),
                ) {
                    val dash = 3.dp.toPx()
                    drawLine(
                        separator, Offset(0f, size.height / 2), Offset(size.width, size.height / 2), strokeWidth = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(dash, dash)),
                    )
                }
            }
        }
    }
}

/**
 * The line the predicted steps are folded behind, in caption2 in the opaque secondary grey: `timeline.predicted.note`,
 * then "Show them", or "Hide them" while they show, in semibold in the primary colour, kept on one line and underlined
 * 3 under its baseline in the separator's colour. The whole line is a plain button 44 tall at least, its words in the
 * middle, which TalkBack reads as one that expands and collapses.
 */
@Composable
private fun PredictedLine(open: Boolean, onToggle: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val note = localizer.text("timeline.predicted.note")
    // Kept on one line, as the web's `white-space: nowrap`.
    val action = localizer.text(if (open) "timeline.predicted.hide" else "timeline.predicted.show").replace(' ', NO_BREAK_SPACE)
    val words = buildAnnotatedString {
        append(note)
        append(' ')
        withStyle(SpanStyle(color = colors.label, fontWeight = FontWeight.SemiBold)) { append(action) }
    }
    val start = note.length + 1
    var layout by remember { mutableStateOf<TextLayoutResult?>(null) }
    val underline = colors.separator
    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 44.dp)
            .testTag(JOURNAL_PREDICTED)
            .button(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onToggle)
            .semantics {
                if (open) {
                    collapse {
                        onToggle()
                        true
                    }
                } else {
                    expand {
                        onToggle()
                        true
                    }
                }
            },
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            words,
            Modifier.drawBehind {
                val result = layout?.takeIf { it.layoutInput.text == words } ?: return@drawBehind
                val end = words.length
                for (line in result.getLineForOffset(start)..result.getLineForOffset(end - 1)) {
                    val from = maxOf(start, result.getLineStart(line))
                    val to = minOf(end, result.getLineEnd(line, visibleEnd = true))
                    if (to <= from) continue
                    val left = result.getHorizontalPosition(from, usePrimaryDirection = true)
                    val right = result.getHorizontalPosition(to, usePrimaryDirection = true)
                    val y = result.getLineBaseline(line) + 3.dp.toPx()
                    drawLine(underline, Offset(minOf(left, right), y), Offset(maxOf(left, right), y), strokeWidth = 1.dp.toPx())
                }
            },
            color = colors.secondaryText,
            style = type.caption2.sfLines(),
            onTextLayout = { layout = it },
        )
    }
}

/**
 * The line alone, 10 under the header, for a carrier that writes its route ahead with no step to list: in caption2 in
 * the opaque secondary grey, without its button.
 */
@Composable
private fun PredictedNote() {
    Text(
        LocalLocalizer.current.text("timeline.predicted.note"),
        Modifier
            .padding(top = 10.dp)
            .testTag(JOURNAL_PREDICTED),
        color = PeekTheme.colors.secondaryText,
        style = PeekTheme.type.caption2.sfLines(),
    )
}

/**
 * A predicted step laid out as a scan's row ([JournalEventRow]), greyed: in the time's column, a hollow dashed circle 12
 * across, 3 lower than the words, and when `joinsNext` a dashed rail at 45% down from under it to just over the next
 * step's circle; then the words in footnote, the day it was predicted for in italics ([predictedDate]) 3 under them
 * and where ([EventLocation]) 4 under that, all in the opaque secondary grey. The circle and the rail grow with the
 * footnote words; at the accessibility sizes the circle keeps a column of its own width beside the words, its rail
 * beside them too. TalkBack reads the row as one ([predictedStepLabel]).
 */
@Composable
private fun PredictedStepRow(step: PlannedTrackingEvent, joinsNext: Boolean, now: Instant) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val secondary = colors.secondaryText
    val date = localizer.predictedDate(step, now)
    // The circle, 12 across beside footnote words at the default size, grown with them.
    val mark = type.scaled(12f, IosTextStyle.Footnote)
    val scale = mark / 12f
    val timeWidth = if (LocalUse24HourTime.current) TIME_WIDTH else TIME_WIDTH_WITH_PERIOD
    val circle = @Composable {
        Box(Modifier.padding(top = (3f * scale).dp).width((if (large) mark else maxOf(mark, type.scaled(timeWidth, IosTextStyle.Caption2))).dp)) {
            Canvas(Modifier.size(mark.dp)) {
                drawCircle(
                    secondary, radius = (4.6f * scale).dp.toPx(),
                    style = Stroke(
                        (1.2f * scale).dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf((2.4f * scale).dp.toPx(), (1.9f * scale).dp.toPx())),
                    ),
                )
            }
        }
    }
    val details = @Composable { modifier: Modifier ->
        Column(modifier) {
            Text(localizer.plannedWording(step), color = secondary, style = type.footnote.sfLines())
            if (date.isNotEmpty()) {
                Text(
                    date, Modifier.padding(top = 3.dp), color = secondary,
                    style = type.caption2.copy(fontStyle = FontStyle.Italic).monospacedDigit().sfLines(),
                )
            }
            step.journalLocation?.let { location -> Box(Modifier.padding(top = 4.dp)) { EventLocation(location, step.place) } }
        }
    }
    val label = localizer.predictedStepLabel(step, now)
    val row = Modifier
        .fillMaxWidth()
        .testTag(JOURNAL_PREDICTED_ROW)
        .then(
            if (joinsNext) {
                Modifier.drawBehind {
                    // From just under this step's circle to just over the next row's, whose circle stands lower.
                    val x = (mark / 2f).dp.toPx()
                    val dash = (3f * scale).dp.toPx()
                    drawLine(
                        secondary.copy(alpha = secondary.alpha * 0.45f), Offset(x, (18f * scale).dp.toPx()), Offset(x, size.height + 16.dp.toPx()),
                        strokeWidth = maxOf(1f, 0.7f * scale).dp.toPx(), pathEffect = PathEffect.dashPathEffect(floatArrayOf(dash, dash)),
                    )
                }
            } else {
                Modifier
            },
        )
        .clearAndSetSemantics { contentDescription = label }
    Row(row, horizontalArrangement = Arrangement.spacedBy(if (large) 10.dp else 12.dp), verticalAlignment = Alignment.Top) {
        circle()
        details(Modifier.weight(1f))
    }
}

/**
 * The journal's header, a plain button 36 tall at least across the journal: "Tracking history" in semibold
 * subheadline, then, 28 on at least, the count of its rows in caption2 and a chevron at 10 points, 12 apart, both in
 * the secondary grey. The chevron points up while the days show and down while they don't, the one fading into the
 * other with the days. TalkBack hears whether a tap shows less or the full journey.
 */
@Composable
private fun JournalHeader(count: Int, full: Boolean, reduceMotion: Boolean, onToggle: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val value = localizer.text(if (full) "design.lessJourney" else "design.fullJourney")
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 36.dp)
            .testTag(JOURNAL_TOGGLE)
            .button(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onToggle)
            .semantics { stateDescription = value },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // `Spacer(minLength: 4)` after the words, 12 on either side of it: the words take what the count leaves.
        Text(
            localizer.text("timeline.label"),
            Modifier.weight(1f),
            color = colors.label,
            style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines(),
        )
        Text(
            localizer.text(if (count == 1) "detail.updateCount.one" else "detail.updateCount.many", mapOf("count" to count)),
            Modifier.padding(start = 16.dp),
            color = colors.secondaryText,
            style = type.caption2.sfLines(),
        )
        // The symbol changes in the toggle's transaction, which SwiftUI draws as a crossfade (its `.opacity` content
        // transition).
        Crossfade(full, animationSpec = if (reduceMotion) snap() else Motion.easeInOut<Float>(TOGGLE), label = "chevron") { up ->
            PeekSymbol(if (up) "chevron.up" else "chevron.down", font = type.fixed(10f), tint = colors.secondaryText)
        }
    }
}

/** A day's rows (`JournalEventRow`), 18 apart. */
@Composable
private fun JournalDayRows(day: JournalDay, tint: Color, currentId: UUID?, syncing: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        for (entry in day.entries) JournalEventRow(entry, tint, current = entry.id == currentId, syncing = syncing)
    }
}

/**
 * `JournalEventRow` (B-S15): the scan's time in caption2 with figures of one width, in `tint` for the `current` row and
 * the opaque secondary grey otherwise, 2 lower than the words and in a column 38 wide at the Large text size, 64 on a
 * 12-hour clock, that grows with caption2; then, 12 on, the words in footnote, which say the carrier is being asked for
 * the current pending scan while the parcel syncs, and under them, 4 apart, where the scan was ([EventLocation]) and its
 * copies' words in caption2 secondary. At the accessibility sizes the time goes 2 over the words, as wide as it is. A
 * scan dated by its day alone has no time, which takes no room, as SwiftUI's empty text takes none. TalkBack reads the
 * row as one.
 */
@Composable
private fun JournalEventRow(entry: JournalEntry, tint: Color, current: Boolean, syncing: Boolean) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val event = entry.event
    val time = localizer.journalTime(event)
    val timeWidth = if (LocalUse24HourTime.current) TIME_WIDTH else TIME_WIDTH_WITH_PERIOD
    val timeFrame = Modifier.padding(top = 2.dp).then(if (large) Modifier else Modifier.width(type.scaled(timeWidth, IosTextStyle.Caption2).dp))
    val timeView = @Composable {
        if (time.isEmpty()) {
            Box(timeFrame)
        } else {
            Text(time, timeFrame, color = if (current) tint else colors.secondaryText, style = type.caption2.monospacedDigit().sfLines())
        }
    }
    val details = @Composable { modifier: Modifier ->
        Column(modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                if (syncing && current && event.stage == TrackingStage.Pending) localizer.text("timeline.syncing") else localizer.journalWording(event),
                color = colors.label,
                style = type.footnote.sfLines(),
            )
            event.journalLocation?.let { EventLocation(it, event.place) }
            // A copy's words stay in sight, in case it was a scan of its own.
            for (line in localizer.relayedWordings(entry)) {
                Text(line, color = colors.secondaryText, style = type.caption2.sfLines())
            }
        }
    }
    val row = Modifier
        .fillMaxWidth()
        .testTag(JOURNAL_ROW)
        .semantics(mergeDescendants = true) {}
    if (large) {
        Column(row, verticalArrangement = Arrangement.spacedBy(2.dp)) {
            timeView()
            details(Modifier.fillMaxWidth())
        }
    } else {
        Row(row, horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Top) {
            timeView()
            details(Modifier.weight(1f))
        }
    }
}

/**
 * `EventLocation` (B-S16), in caption2 in the opaque secondary grey: with a country, its flag in caption, then 4 on and
 * on the flag's baseline the place and the country named in the app language, or the country alone where the place has
 * no name; TalkBack reads the words without the flag. Without a country, the place as written. `placed` is where the
 * server placed the scan: its country settles a final code that is also a state or canton, and its town names a place
 * written in Chinese, Japanese or Korean script ([TrackingLocation.place]).
 */
@Composable
private fun EventLocation(location: String, placed: EventPlace?) {
    val language = LocalLanguage.current
    val type = PeekTheme.type
    val secondary = PeekTheme.colors.secondaryText
    val place = TrackingLocation.place(location, placed, language)
    val country = place.country
    if (country == null) {
        Text(place.name, color = secondary, style = type.caption2.sfLines())
        return
    }
    val name = TrackingLocation.countryName(country, language)
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            TrackingLocation.flag(country),
            Modifier
                .alignByBaseline()
                .clearAndSetSemantics {},
            // A colour emoji draws solid on the iPhone whatever the text's grey; Android would lay the grey's alpha on it.
            color = PeekTheme.colors.label,
            style = type.caption.sfLines(),
        )
        Text(
            if (place.name.isEmpty()) name else "${place.name}, $name",
            Modifier
                .alignByBaseline()
                .weight(1f, fill = false),
            color = secondary,
            style = type.caption2.sfLines(),
        )
    }
}

/** How long the days take to show or hide, in seconds. */
private const val TOGGLE = 0.2

/** The space that keeps "Show them" on one line. */
private val NO_BREAK_SPACE = Char(0x00A0)

/** The time column's width at the Large text size (`@ScaledMetric(relativeTo: .caption2)`), and on a 12-hour clock. */
private const val TIME_WIDTH = 38f
private const val TIME_WIDTH_WITH_PERIOD = 64f

/**
 * The test tags of the journal's header, its days' headings, its rows, the line its predicted steps are folded behind
 * (or the line alone) and their rows, and the line naming the zone its times are in.
 */
const val JOURNAL_TOGGLE = "journal.toggle"
const val JOURNAL_DAY = "journal.day"
const val JOURNAL_ROW = "journal.row"
const val JOURNAL_PREDICTED = "journal.predicted"
const val JOURNAL_PREDICTED_ROW = "journal.predicted.row"
const val JOURNAL_TIME_ZONE = "journal.timeZone"
