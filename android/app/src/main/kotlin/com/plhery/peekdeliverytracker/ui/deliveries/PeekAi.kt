package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
import com.plhery.peekdeliverytracker.core.peekAiRecord
import com.plhery.peekdeliverytracker.core.peekAiStrip
import com.plhery.peekdeliverytracker.core.peekAiWhy
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.draw.CarrierFleetMark
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.SmallPip
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.sheetBottomInset
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
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
 * in `style` (`wordsWeight`): Peek's, never the carrier's. The words wrap beside the pill where `wraps`, else stay on one
 * line. TalkBack reads the name and the words as one.
 */
@Composable
internal fun PeekAiPhrase(
    words: String,
    ink: Color,
    style: TextStyle,
    modifier: Modifier = Modifier,
    wordsWeight: FontWeight = FontWeight.Normal,
    wraps: Boolean = false,
) {
    val localizer = LocalLocalizer.current
    val density = LocalDensity.current
    val sparkle = with(density) { style.fontSize.toDp() } * 0.95f
    Row(
        modifier.semantics(mergeDescendants = true) {},
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
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
        Text(
            words,
            if (wraps) Modifier.weight(1f, fill = false) else Modifier,
            color = ink,
            style = style.copy(fontWeight = wordsWeight).sfLines(),
            maxLines = if (wraps) Int.MAX_VALUE else 1,
            softWrap = wraps,
        )
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
 * The time the parcel's page draws Peek AI at, looked at again each minute while `watching` and its estimate holds, as
 * the iPhone's `PeekAiPageCard`: the card goes once its hour or day has passed, and "5 hours ago" moves on.
 */
@Composable
private fun peekAiMinute(parcel: Parcel, watching: Boolean): Instant =
    peekAiClock(watching) { now -> if (parcelPeekAi(parcel, now) != null) now.truncatedTo(ChronoUnit.MINUTES).plusSeconds(60) else null }

/**
 * The app's time, read again at the `next` moment after it while `watching`, and as the app comes back from the
 * background, as the phone may have slept past that moment (a wait stands still while it sleeps). The caller is drawn
 * again each time.
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
        LifecycleEventEffect(Lifecycle.Event.ON_RESUME) { looks++ }
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
    val identities = LocalCarrierIdentities.current ?: return
    val shown by state.source.showPeekAi.collectAsState()
    var open by rememberSaveable(parcel.id) { mutableStateOf(false) }
    val now = peekAiMinute(parcel, shown || open)
    // The estimate while it holds, which the sheet keeps showing once Peek AI is switched off there.
    val current = parcelPeekAi(parcel, now)
    val ai = if (shown) current else null
    // The call the sheet opened on, kept while it goes.
    var kept by remember(parcel.id) { mutableStateOf<PeekAi?>(null) }
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
                PeekAiPhrase(
                    localizer.peekAiClaim(ai, now), colors.label, type.footnote, Modifier.weight(1f), wordsWeight = FontWeight.SemiBold, wraps = true,
                )
                Box(
                    Modifier
                        .size(36.dp)
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
        paneTitle = localizer.text("peekAi.name"),
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
 * the carrier's livery, the heading over the carrier's own word, with the parcel; the days or hours ([PeekAiStrip]); why
 * and how often it was right; the switch that shows it (`shown`, `onShow`); and what it is, centred in caption. It is
 * drawn at `now`, the page's time.
 */
@Composable
internal fun PeekAiSheet(parcel: Parcel, ai: PeekAi, carrier: String, shown: Boolean, now: Instant, onShow: (Boolean) -> Unit, close: () -> Unit) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
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
                            Text(localizer.peekAiHeading(ai, now), color = colors.label, style = type.style(IosTextStyle.Title3, FontWeight.Bold).sfLines())
                            Text(localizer.peekAiCarrierWord(ai, carrier, estimate), color = ink, style = type.subheadline.sfLines())
                        }
                        SmallPip(Modifier.width(64.dp))
                    }
                }
            }
            localizer.peekAiStrip(ai, now)?.let { PeekAiStripBox(it, carrier) }
            Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                PeekAiFact(BULB, localizer.text("peekAi.sheet.why"), localizer.peekAiWhy(ai, carrier, now))
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
 * The days Peek AI expects, each with its weekday over its date, or the hours two to a cell: the likely ones on the ink
 * at 13 %, the possible ones at 5 %, the carrier's day ringed and the hours gone faded; under them, what the shades say.
 */
@Composable
private fun PeekAiStripBox(strip: PeekAiStrip, carrier: String) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val ink = colors.label
    val soft = colors.secondaryText
    Column(
        Modifier
            .fillMaxWidth()
            .testTag(PEEK_AI_STRIP)
            .background(colors.sheetControl, ContinuousRoundedShape(16.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (cell in strip.cells) {
                val shape = ContinuousRoundedShape(9.dp)
                Column(
                    Modifier
                        .weight(1f)
                        .alpha(if (cell.past) 0.45f else 1f)
                        .background(
                            when {
                                cell.likely -> ink.copy(alpha = ink.alpha * 0.13f)
                                cell.maybe -> ink.copy(alpha = ink.alpha * 0.05f)
                                else -> Color.Transparent
                            },
                            shape,
                        )
                        .then(if (cell.carrier) Modifier.border(1.2.dp, soft, shape) else Modifier)
                        .padding(top = 5.dp, bottom = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                ) {
                    if (cell.over.isNotEmpty()) Text(cell.over, color = soft, style = type.caption2.sfLines(), maxLines = 1, softWrap = false)
                    Text(cell.main, color = ink, style = type.style(IosTextStyle.Footnote, FontWeight.Medium).sfLines(), maxLines = 1, softWrap = false)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
            StripKey(localizer.text(if (strip.kind == PeekAiStrip.Kind.Hours) "peekAi.sheet.hours" else "peekAi.sheet.days"), soft) {
                Box(Modifier.size(9.dp).background(soft.copy(alpha = soft.alpha * 0.3f), RoundedCornerShape(3.dp)))
            }
            if (strip.cells.any { it.carrier }) {
                StripKey(localizer.text("peekAi.sheet.carrierDate", mapOf("carrier" to carrier)), soft) {
                    Box(Modifier.size(9.dp).border(1.2.dp, soft, RoundedCornerShape(3.dp)))
                }
            }
        }
    }
}

@Composable
private fun StripKey(words: String, tint: Color, swatch: @Composable () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
        swatch()
        Text(words, color = tint, style = PeekTheme.type.caption2.sfLines())
    }
}

// Test tags, named apart from the copy's `peekAi.*` keys.
internal const val PEEK_AI_CARD = "$PARCEL_PAGE.peekAi"
internal const val PEEK_AI_INFO = "$PEEK_AI_CARD.info"
internal const val PEEK_AI_SHEET = "peekAiSheet"
internal const val PEEK_AI_CLOSE = "$PEEK_AI_SHEET.close"
internal const val PEEK_AI_STRIP = "$PEEK_AI_SHEET.strip"
internal const val PEEK_AI_SHOW = "$PEEK_AI_SHEET.show"
