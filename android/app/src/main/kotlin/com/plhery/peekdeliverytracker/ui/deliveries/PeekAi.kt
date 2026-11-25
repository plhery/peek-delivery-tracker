package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.MultiContentMeasurePolicy
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.plhery.peekdeliverytracker.core.PeekAiStrip
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PeekAi
import com.plhery.peekdeliverytracker.core.displayedCarrier
import com.plhery.peekdeliverytracker.core.parcelPeekAi
import com.plhery.peekdeliverytracker.core.peekAiCarrierWord
import com.plhery.peekdeliverytracker.core.peekAiChanges
import com.plhery.peekdeliverytracker.core.peekAiClaim
import com.plhery.peekdeliverytracker.core.peekAiHeading
import com.plhery.peekdeliverytracker.core.peekAiReason
import com.plhery.peekdeliverytracker.core.peekAiRecord
import com.plhery.peekdeliverytracker.core.peekAiStrip
import com.plhery.peekdeliverytracker.core.peekAiStripSpoken
import com.plhery.peekdeliverytracker.core.peekAiUntil
import com.plhery.peekdeliverytracker.core.peekAiWhy
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.LocalUse24HourTime
import com.plhery.peekdeliverytracker.ui.draw.CarrierFleetMark
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.SmallPip
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.sheetBottomInset
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.MinimumScale
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.shownCarrierIdentity
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlinx.coroutines.delay

// Peek AI (core PeekAi.kt): Peek's own estimate, always named as Peek's, never the carrier's. The web's is
// src/components/PeekAi.tsx.

/** The web's sparkle and line icons, on a 24 grid. */
private const val SPARKLE =
    "M10 2.5c.5 4.6 2.4 6.5 7 7-4.6.5-6.5 2.4-7 7-.5-4.6-2.4-6.5-7-7 4.6-.5 6.5-2.4 7-7Zm8.5 11c.3 2.3 1.2 3.2 3.5 3.5-2.3.3-3.2 1.2-3.5 3.5-.3-2.3-1.2-3.2-3.5-3.5 2.3-.3 3.2-1.2 3.5-3.5Z"
private const val BULB = "M12 3a6 6 0 0 0-3.5 10.9V16h7v-2.1A6 6 0 0 0 12 3ZM9.5 20h5"
private const val CHART = "M4 19V5m0 14h16M8 15v-3m4 3V8m4 7v-5"
private const val EYE =
    "M2.5 12s3.5-6.5 9.5-6.5S21.5 12 21.5 12s-3.5 6.5-9.5 6.5S2.5 12 2.5 12Zm9.5 2.8a2.8 2.8 0 1 0 0-5.6 2.8 2.8 0 0 0 0 5.6Z"

private val paths = HashMap<String, Path>()

private fun path(data: String): Path = paths.getOrPut(data) { PathParser().parsePathString(data).toPath() }

/** One of the web's icons, `size` across in `tint`: the sparkle filled, the others drawn in a 1.6 line. Decorative. */
@Composable
private fun PeekAiIcon(data: String, tint: Color, size: Dp, modifier: Modifier = Modifier, line: Boolean = false) {
    Canvas(modifier.size(size).clearAndSetSemantics {}) {
        scale(this.size.width / 24f, pivot = Offset.Zero) {
            if (line) {
                drawPath(path(data), tint, style = Stroke(width = 1.6f, cap = StrokeCap.Round, join = StrokeJoin.Round))
            } else {
                drawPath(path(data), tint)
            }
        }
    }
}

/**
 * "✦ Peek AI" in a pill of `ink` at 14 %, the name in semibold a little smaller than `style`, then its `words` 4 after it
 * in `style` (`wordsWeight`), on the name's first baseline: Peek's, never the carrier's. Where `wraps`, the words wrap
 * beside the pill; else they stay on its line where they fit whole, and wrap under it where they don't, as the web's
 * `flex-wrap`. TalkBack reads the name and the words as one, or `label` in their place.
 */
@Composable
internal fun PeekAiPhrase(
    words: String,
    ink: Color,
    style: TextStyle,
    modifier: Modifier = Modifier,
    wordsWeight: FontWeight = FontWeight.Normal,
    wraps: Boolean = false,
    label: String? = null,
) {
    val localizer = LocalLocalizer.current
    val density = LocalDensity.current
    val sparkle = with(density) { style.fontSize.toDp() } * 0.95f
    Layout(
        contents = listOf(
            {
                Row(
                    Modifier
                        .background(ink.copy(alpha = ink.alpha * 0.14f), RoundedCornerShape(50))
                        .padding(start = 4.dp, end = 6.dp, top = 1.dp, bottom = 1.dp),
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PeekAiIcon(SPARKLE, ink, sparkle)
                    Text(
                        localizer.text("peekAi.name"),
                        color = ink,
                        style = style.copy(fontWeight = FontWeight.SemiBold, fontSize = style.fontSize * 0.92f).sfLines(),
                        maxLines = 1,
                        softWrap = false,
                    )
                }
            },
            { Text(words, color = ink, style = style.copy(fontWeight = wordsWeight).sfLines()) },
        ),
        modifier = if (label != null) modifier.clearAndSetSemantics { contentDescription = label } else modifier.semantics(mergeDescendants = true) {},
        measurePolicy = remember(wraps) { PhraseLayout(wraps) },
    )
}

/** [PeekAiPhrase]'s pill and words: on one line, on the first baseline, where they go there; else the words 1 under it. */
private class PhraseLayout(private val wraps: Boolean) : MultiContentMeasurePolicy {
    override fun MeasureScope.measure(measurables: List<List<Measurable>>, constraints: Constraints): MeasureResult {
        val (pillMeasurable, wordsMeasurable) = measurables.map { it.single() }
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        val pill = pillMeasurable.measure(loose)
        val room = if (constraints.hasBoundedWidth) (constraints.maxWidth - pill.width - GAP.roundToPx()).coerceAtLeast(0) else Constraints.Infinity
        // Beside: whole, or where they wrap, by words that each fit.
        val beside = room >= wordsMeasurable.run { if (wraps) minIntrinsicWidth(Constraints.Infinity) else maxIntrinsicWidth(Constraints.Infinity) }
        return if (beside) {
            val words = wordsMeasurable.measure(loose.copy(maxWidth = room))
            val pillBaseline = pill[FirstBaseline]
            val wordsBaseline = words[FirstBaseline]
            // Without a baseline (no text), the two are centred on each other.
            val (pillTop, wordsTop) = if (pillBaseline == AlignmentLine.Unspecified || wordsBaseline == AlignmentLine.Unspecified) {
                val height = maxOf(pill.height, words.height)
                (height - pill.height) / 2 to (height - words.height) / 2
            } else {
                val baseline = maxOf(pillBaseline, wordsBaseline)
                baseline - pillBaseline to baseline - wordsBaseline
            }
            val width = (pill.width + GAP.roundToPx() + words.width).coerceIn(constraints.minWidth, constraints.maxWidth)
            layout(width, maxOf(pillTop + pill.height, wordsTop + words.height).coerceAtLeast(constraints.minHeight)) {
                pill.place(0, pillTop)
                words.place(pill.width + GAP.roundToPx(), wordsTop)
            }
        } else {
            val words = wordsMeasurable.measure(loose)
            val width = maxOf(pill.width, words.width).coerceIn(constraints.minWidth, constraints.maxWidth)
            layout(width, (pill.height + LINE_GAP.roundToPx() + words.height).coerceAtLeast(constraints.minHeight)) {
                pill.place(0, 0)
                words.place(0, pill.height + LINE_GAP.roundToPx())
            }
        }
    }

    override fun IntrinsicMeasureScope.maxIntrinsicWidth(measurables: List<List<IntrinsicMeasurable>>, height: Int): Int {
        val (pill, words) = measurables.map { it.single() }
        return pill.maxIntrinsicWidth(height) + GAP.roundToPx() + words.maxIntrinsicWidth(height)
    }

    override fun IntrinsicMeasureScope.minIntrinsicWidth(measurables: List<List<IntrinsicMeasurable>>, height: Int): Int {
        val (pill, words) = measurables.map { it.single() }
        return maxOf(pill.minIntrinsicWidth(height), words.minIntrinsicWidth(height))
    }

    override fun IntrinsicMeasureScope.minIntrinsicHeight(measurables: List<List<IntrinsicMeasurable>>, width: Int): Int =
        intrinsicHeight(measurables, width) { minIntrinsicHeight(it) }

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(measurables: List<List<IntrinsicMeasurable>>, width: Int): Int =
        intrinsicHeight(measurables, width) { maxIntrinsicHeight(it) }

    private fun IntrinsicMeasureScope.intrinsicHeight(
        measurables: List<List<IntrinsicMeasurable>>,
        width: Int,
        height: IntrinsicMeasurable.(Int) -> Int,
    ): Int {
        val (pill, words) = measurables.map { it.single() }
        val pillWidth = pill.maxIntrinsicWidth(Constraints.Infinity)
        val room = if (width == Constraints.Infinity) width else (width - pillWidth - GAP.roundToPx()).coerceAtLeast(0)
        val beside = room >= if (wraps) words.minIntrinsicWidth(Constraints.Infinity) else words.maxIntrinsicWidth(Constraints.Infinity)
        return if (beside) maxOf(pill.height(pillWidth), words.height(room)) else pill.height(pillWidth) + LINE_GAP.roundToPx() + words.height(width)
    }

    private companion object {
        val GAP = 4.dp
        val LINE_GAP = 1.dp
    }
}

/**
 * The time a card is drawn at ([LocalNow]), drawn again where what Peek AI says of its parcel can change while `shown`
 * ([peekAiChanges]), as the iPhone's `PeekAiClock`: on a list left open, "by 16:00" goes at 16:00, and a day's estimate
 * the midnight after it. A card without an estimate keeps no clock.
 */
@Composable
internal fun peekAiNow(parcel: Parcel, shown: Boolean): Instant =
    peekAiClock(shown) { now -> peekAiChanges(parcel, now).firstOrNull() }

/**
 * The time the parcel's page draws Peek AI at, looked at again each minute while `watching` and its estimate holds, and
 * at the moment it ends ([peekAiUntil]), as the iPhone's `PeekAiPageCard`: the card and its sheet go as its hour or day
 * passes, and "5 hours ago" moves on.
 */
@Composable
private fun peekAiMinute(parcel: Parcel, watching: Boolean): Instant =
    peekAiClock(watching) { now ->
        parcelPeekAi(parcel, now)?.let { ai ->
            val minute = now.truncatedTo(ChronoUnit.MINUTES).plusSeconds(60)
            peekAiUntil(ai)?.takeIf { it > now && it < minute } ?: minute
        }
    }

/**
 * The app's time, read again at the `next` moment after it while `watching`, and as the app comes back from the
 * background, as the phone may have slept past that moment (a wait stands still while it sleeps). The caller is drawn
 * again each time; not as it first shows, where the lifecycle replays its resume to the new observer.
 */
@Composable
private fun peekAiClock(watching: Boolean, next: (Instant) -> Instant?): Instant {
    val clock = LocalNow.current
    var looks by remember { mutableIntStateOf(0) }
    val now = clock()
    val at = if (watching) next(now) else null
    if (at != null) {
        LaunchedEffect(at, looks) {
            // A little past it, as the web's timer: drawn a moment early, the card would wait for the same moment again.
            delay(Duration.between(clock(), at).toMillis().coerceAtLeast(0) + 20)
            looks++
        }
        var watched by remember { mutableStateOf(false) }
        LifecycleEventEffect(Lifecycle.Event.ON_RESUME) { if (watched) looks++ else watched = true }
    }
    return now
}

/**
 * Peek AI on the parcel's page, under its pass while Peek AI is on and its call holds: "✦ Peek AI likely by Thu 26 Nov"
 * with the (i) at its end, and why under it, on the secondary grey at 8 % in 16-point corners, looked at again each
 * minute ([peekAiMinute]). The (i) opens its sheet ([PeekAiSheet]), counted (`peek-ai-open`), which stays while it is
 * open: switching Peek AI off there does not pull it away. It goes with the estimate, once its hour or day has passed.
 */
@Composable
internal fun PeekAiPageCard(state: DeliveriesState, parcel: Parcel, modifier: Modifier = Modifier) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    LocalUse24HourTime.current
    val identities = LocalCarrierIdentities.current ?: return
    val shown by state.source.showPeekAi.collectAsState()
    var open by rememberSaveable(parcel.id) { mutableStateOf(false) }
    val now = peekAiMinute(parcel, shown || open)
    // The estimate while it holds, which the sheet keeps showing once Peek AI is switched off there.
    val current = parcelPeekAi(parcel, now)
    val ai = if (shown) current else null
    // The call the sheet opened on, kept while it goes; a sheet open again after the activity is recreated keeps its own.
    var kept by remember(parcel.id) { mutableStateOf(if (open) current else null) }
    LaunchedEffect(current == null) { if (current == null) open = false }
    val carrier = identities.displayName(parcel.displayedCarrier, language)
    if (ai != null) {
        val colors = PeekTheme.colors
        val type = PeekTheme.type
        val about = localizer.text("peekAi.about")
        Column(
            modifier
                .fillMaxWidth()
                .testTag(PEEK_AI_CARD)
                .background(colors.secondaryLabel.copy(alpha = colors.secondaryLabel.alpha * 0.08f), ContinuousRoundedShape(16.dp))
                .padding(start = 16.dp, top = 6.dp, end = 6.dp, bottom = 12.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val claim = localizer.peekAiClaim(ai, now)
                PeekAiPhrase(
                    claim, colors.label, type.footnote, Modifier.weight(1f), wordsWeight = FontWeight.SemiBold, wraps = true,
                    label = localizer.text("peekAi.says", mapOf("words" to claim)),
                )
                Box(
                    Modifier
                        .touchTarget(36.dp)
                        .testTag(PEEK_AI_INFO)
                        .button(interactionSource = null, indication = PlainPress, role = Role.Button, shape = CircleShape) {
                            kept = ai
                            open = true
                            state.source.action("peek-ai-open")
                        }
                        .semantics { contentDescription = about },
                    contentAlignment = Alignment.Center,
                ) {
                    PeekSymbol("info.circle", font = type.fixed(15f), tint = colors.secondaryText)
                }
            }
            Text(
                localizer.peekAiWhy(ai, carrier, now),
                Modifier.padding(end = 10.dp),
                color = colors.secondaryText,
                style = type.footnote.sfLines(),
            )
        }
    }
    val sheet = current ?: kept
    Sheet(
        visible = open && current != null,
        onDismiss = { open = false },
        paneTitle = localizer.text("peekAi.name"), id = "peekAi",
        detents = listOf(SheetDetent.Fitted, SheetDetent.Large),
        onClosed = { kept = null },
        lit = true,
    ) {
        if (sheet != null) {
            PeekAiSheet(parcel, sheet, carrier, shown, now, onShow = { on ->
                state.source.setShowPeekAi(on)
                state.source.action(if (on) "peek-ai-on" else "peek-ai-off")
            }, close = { open = false })
        }
    }
}

/**
 * Peek AI's sheet, as the alerts' ([ParcelAlertSwitches]): "Peek AI" with the close button; what it expects on a card in
 * the carrier's livery, the heading over the carrier's own word, with the parcel; the days or hours ([PeekAiStrip]); what
 * it goes by ([peekAiReason]) and how often it was right; the switch that shows it (`shown`, `onShow`); and what it is,
 * centred in caption. It is drawn at `now`, the page's time.
 */
@Composable
internal fun PeekAiSheet(parcel: Parcel, ai: PeekAi, carrier: String, shown: Boolean, now: Instant, onShow: (Boolean) -> Unit, close: () -> Unit) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    LocalUse24HourTime.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val bottom = sheetBottomInset()
    val identity = shownCarrierIdentity(parcel, localizer, language)
    val estimate = localizer.parcelDeliveryEstimate(parcel, now)
    Box(Modifier.fillMaxSize().background(colors.sheet).verticalScroll(rememberScrollState())) {
        Column(
            Modifier
                .testTag(PEEK_AI_SHEET)
                .fillMaxWidth()
                .wrapContentWidth()
                .widthIn(max = 560.dp)
                .fillMaxWidth()
                .padding(start = 22.dp, top = 26.dp, end = 22.dp, bottom = 18.dp + bottom),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    localizer.text("peekAi.name"),
                    Modifier.weight(1f).semantics { heading() },
                    color = colors.label,
                    style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(),
                )
                val closeLabel = localizer.text("common.close")
                Box(
                    Modifier
                        .size(44.dp)
                        .testTag(PEEK_AI_CLOSE)
                        .button(interactionSource = null, indication = PlainPress, role = Role.Button, shape = CircleShape, onClick = close)
                        .semantics { contentDescription = closeLabel },
                    contentAlignment = Alignment.Center,
                ) {
                    Box(Modifier.size(36.dp).background(colors.sheetButton, CircleShape), contentAlignment = Alignment.Center) {
                        PeekSymbol("xmark", font = type.fixed(13f, FontWeight.SemiBold), tint = colors.label)
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(localizer.text("peekAi.sheet.caption"), Modifier.padding(horizontal = 2.dp), color = colors.secondaryText, style = type.subheadline.sfLines())
                if (identity != null) {
                    val ink = colors.resolve(identity.ink)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(colors.resolve(identity.surface), ContinuousRoundedShape(20.dp))
                            .padding(start = 16.dp, top = 14.dp, end = 14.dp, bottom = 14.dp)
                            .semantics(mergeDescendants = true) {},
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            CarrierFleetMark(identity, Modifier.padding(bottom = 2.dp))
                            Text(localizer.peekAiHeading(ai, now), color = colors.label, style = type.style(IosTextStyle.Title3, FontWeight.SemiBold).sfLines())
                            Text(localizer.peekAiCarrierWord(ai, carrier, estimate), color = ink, style = type.subheadline.sfLines())
                        }
                        // At the accessibility sizes the words take the card's width.
                        if (!PeekTheme.dynamicTypeSize.isAccessibilitySize) SmallPip(Modifier.width(64.dp))
                    }
                }
            }
            localizer.peekAiStrip(ai, now)?.let { PeekAiStripBox(it, carrier) }
            Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                PeekAiFact(BULB, localizer.text("peekAi.sheet.why"), localizer.peekAiReason(ai, carrier, now))
                PeekAiFact(CHART, localizer.text("peekAi.sheet.sure"), localizer.peekAiRecord(ai))
                SwitchRow(
                    PEEK_AI_SHOW, null, localizer.text("peekAi.sheet.show"), localizer.text("peekAi.sheet.showHint"), on = shown, enabled = true,
                    icon = { PeekAiIcon(EYE, colors.secondaryText, 20.dp, line = true) },
                    flip = onShow,
                )
            }
            Text(
                localizer.text("peekAi.sheet.trained", mapOf("carrier" to carrier)),
                Modifier.fillMaxWidth().padding(horizontal = 2.dp),
                color = colors.secondaryText,
                style = type.caption.sfLines(),
                textAlign = TextAlign.Center,
            )
        }
    }
}

/** A fact of the sheet, as its switches are drawn ([SwitchRow]) without the switch: the icon, the title over the words. */
@Composable
private fun PeekAiFact(icon: String, title: String, words: String) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    Row(
        Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {}
            .heightIn(min = 60.dp)
            .background(colors.raised, ContinuousRoundedShape(14.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (!large) {
            Box(Modifier.size(22.dp).wrapContentSize(unbounded = true), contentAlignment = Alignment.Center) {
                PeekAiIcon(icon, colors.secondaryText, 20.dp, line = true)
            }
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(title, color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
            Text(words, color = colors.secondaryText, style = type.caption.sfLines())
        }
    }
}

/**
 * The days Peek AI expects, each with its weekday over its date, or the hours two to a cell, each shaded by its chance in
 * four steps of the ink (`peekAiShades`, [stripFill]), the likeliest in bold: the weight says it without colour. The
 * carrier's day is ringed and the hours gone faded. Under them, the scale from "Less likely" to "More likely" and the
 * ring. A cell's words keep to one line, shrinking to 70 % ("10:00 PM" on a narrow phone). TalkBack hears the shades in
 * words ([peekAiStripSpoken]), likeliest first, and not the key.
 */
@Composable
private fun PeekAiStripBox(strip: PeekAiStrip, carrier: String) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val ink = colors.label
    // With more contrast asked for, every word takes the ink.
    val soft = if (colors.highContrast) ink else colors.secondaryText
    val spoken = localizer.peekAiStripSpoken(strip, carrier)
    Column(
        Modifier
            .fillMaxWidth()
            .testTag(PEEK_AI_STRIP)
            .clearAndSetSemantics { contentDescription = spoken }
            .background(colors.sheetControl, ContinuousRoundedShape(16.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (cell in strip.cells) {
                val shape = ContinuousRoundedShape(9.dp)
                val likeliest = cell.level == 4
                Column(
                    Modifier
                        .weight(1f)
                        .alpha(if (cell.past) 0.45f else 1f)
                        .background(stripFill(cell.level), shape)
                        .then(
                            when {
                                !cell.carrier -> Modifier
                                colors.highContrast -> Modifier.border(1.5.dp, ink, shape)
                                else -> Modifier.border(1.2.dp, soft, shape)
                            },
                        )
                        .padding(top = 5.dp, bottom = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                ) {
                    // On the two strongest shades the weekday takes the ink, which the soft grey would lack contrast against.
                    if (cell.over.isNotEmpty()) {
                        CellWords(
                            cell.over,
                            (if (likeliest) type.style(IosTextStyle.Caption2, FontWeight.SemiBold) else type.caption2).sfLines()
                                .copy(color = if (cell.level >= 3) ink else soft),
                        )
                    }
                    CellWords(cell.main, type.style(IosTextStyle.Footnote, if (likeliest) FontWeight.Bold else FontWeight.Medium).sfLines().copy(color = ink))
                }
            }
        }
        FlowRow(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                KeyWords(localizer.text("peekAi.sheet.lessLikely"), soft)
                // Each swatch edged in a faint ink: the faintest shade alone would barely part from the panel.
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    for (level in 1..4) {
                        Box(
                            Modifier.size(10.dp).background(stripFill(level), RoundedCornerShape(3.dp))
                                .border(1.dp, ink.copy(alpha = ink.alpha * 0.25f), RoundedCornerShape(3.dp)),
                        )
                    }
                }
                KeyWords(localizer.text("peekAi.sheet.moreLikely"), soft)
            }
            if (strip.cells.any { it.carrier }) {
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(10.dp).border(1.2.dp, soft, RoundedCornerShape(3.dp)))
                    KeyWords(localizer.text("peekAi.sheet.carrierDate", mapOf("carrier" to carrier)), soft)
                }
            }
        }
    }
}

/**
 * A strip cell's shade at `level` (`peekAiShades`), and its swatch in the key: the ink at 5, 11, 19 and 30 %, none at 0,
 * further apart (8, 15, 23 and 32 %) with more contrast asked for. Light and dark alike.
 */
@Composable
private fun stripFill(level: Int): Color {
    val colors = PeekTheme.colors
    val ink = colors.label
    val share = (if (colors.highContrast) HIGH_CONTRAST_FILLS else FILLS).getOrElse(level) { 0f }
    return if (share == 0f) Color.Transparent else ink.copy(alpha = ink.alpha * share)
}

private val FILLS = floatArrayOf(0f, 0.05f, 0.11f, 0.19f, 0.30f)
private val HIGH_CONTRAST_FILLS = floatArrayOf(0f, 0.08f, 0.15f, 0.23f, 0.32f)

/** A strip cell's words, on one line that shrinks to 70 % before it ends in an ellipsis, as the iPhone's. */
@Composable
private fun CellWords(text: String, style: TextStyle) {
    val probe = LocalPeekAiCellWords.current
    BasicText(
        text,
        style = style,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        autoSize = MinimumScale(style.fontSize, 0.7f, 0.1.sp),
        onTextLayout = { probe?.invoke(it) },
    )
}

/** Told how a strip cell's words are laid out: the tests check they keep to one line, whole. */
internal val LocalPeekAiCellWords = staticCompositionLocalOf<((TextLayoutResult) -> Unit)?> { null }

/**
 * Lays the element out at `target` square, at least, and gives its parent `visible` square, `target` centred on it: a
 * small button keeps a finger's room without moving what is drawn around it.
 */
private fun Modifier.touchTarget(visible: Dp, target: Dp = 48.dp): Modifier = layout { measurable, _ ->
    val side = maxOf(visible, target).roundToPx()
    val shown = visible.roundToPx()
    val placeable = measurable.measure(Constraints.fixed(side, side))
    layout(shown, shown) { placeable.place((shown - side) / 2, (shown - side) / 2) }
}

/** Words of the strip's key, in caption2. */
@Composable
private fun KeyWords(words: String, tint: Color) {
    Text(words, color = tint, style = PeekTheme.type.caption2.sfLines())
}

// Test tags, named apart from the copy's `peekAi.*` keys.
internal const val PEEK_AI_CARD = "$PARCEL_PAGE.peekAi"
internal const val PEEK_AI_INFO = "$PEEK_AI_CARD.info"
internal const val PEEK_AI_SHEET = "peekAiSheet"
internal const val PEEK_AI_CLOSE = "$PEEK_AI_SHEET.close"
internal const val PEEK_AI_STRIP = "$PEEK_AI_SHEET.strip"
internal const val PEEK_AI_SHOW = "$PEEK_AI_SHEET.show"
