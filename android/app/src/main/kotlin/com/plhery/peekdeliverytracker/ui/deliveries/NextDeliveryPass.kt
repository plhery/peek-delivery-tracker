package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
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
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.EdgeInsets
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.core.deliveringCarrier
import com.plhery.peekdeliverytracker.core.deliveryLineKey
import com.plhery.peekdeliverytracker.core.hasMappedPlace
import com.plhery.peekdeliverytracker.core.parcelPeekAiWords
import com.plhery.peekdeliverytracker.core.pickupPlace
import com.plhery.peekdeliverytracker.core.shownCarrierOutage
import com.plhery.peekdeliverytracker.core.updateFailed
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalUse24HourTime
import com.plhery.peekdeliverytracker.ui.draw.CarrierFleetMark
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.DeliveryPostageStamp
import com.plhery.peekdeliverytracker.ui.draw.UnevenContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.experimentalSurface
import com.plhery.peekdeliverytracker.ui.draw.litEdge
import com.plhery.peekdeliverytracker.ui.map.RouteEngraving
import com.plhery.peekdeliverytracker.ui.map.rememberParcelRoute
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.CappedDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.MinimumScale
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekKeyframes
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.Radii
import com.plhery.peekdeliverytracker.ui.theme.cardGrounds
import com.plhery.peekdeliverytracker.ui.theme.keyframeAnimator
import com.plhery.peekdeliverytracker.ui.theme.readableInk
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.shownCarrierIdentity

/**
 * `ExperimentalNextDeliveryPass` (A-S34): the large card of the parcel that comes next. Its carrier and "Next up" on
 * top, its route engraved behind the top of the card when its scans have places, its name beside its stamp, and its
 * status, pickup place and estimate under them.
 *
 * - `arrived`: just delivered and still shown where it stood. "Next up" keeps its room and goes, the status line takes
 *   a tick and the delivery's date, and the card gives a little as the news lands.
 * - `decorative`: drawn for its look alone, on a card that flies over the list; it says nothing and takes no touches.
 * - `onName`: where the parcel's name is written, its middle in points in its item of the list ([deliveryListItem]).
 * - `selected`: the parcel shown beside the list, ringed.
 * - `onLongClick`: a long press, the list's card menu; the press then doesn't open the parcel ([cardButton]).
 * - `swipeAction`: the list's swipe to archive, offered to TalkBack; the swipe's row then draws the card's shadow.
 * - `onTap`: what a finger's tap does, `onOpen` unless the swipe closes the card first; TalkBack's double tap opens it.
 * - `newUpdate`: a scan the reader hasn't seen (#72), a [NewUpdateDot] first on the status line, read first by TalkBack.
 * - `peekAi`: Peek AI on, whose words ([PeekAiPhrase]) follow the status, after a dot, or under it where they don't fit;
 *   the card is drawn again where they can change ([peekAiNow]).
 *
 * It fades in as it first shows, and its map once drawn, neither under Reduce Motion. Nothing is drawn until the
 * carriers' liveries are read.
 */
@Composable
fun NextDeliveryPass(
    parcel: Parcel,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier,
    arrived: Boolean = false,
    decorative: Boolean = false,
    onName: ((Float) -> Unit)? = null,
    selected: Boolean = false,
    onLongClick: (() -> Unit)? = null,
    swipeAction: CustomAccessibilityAction? = null,
    onTap: () -> Unit = onOpen,
    newUpdate: Boolean = false,
    peekAi: Boolean = false,
) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    LocalUse24HourTime.current
    val identity = shownCarrierIdentity(parcel, localizer, language) ?: return
    val identities = LocalCarrierIdentities.current ?: return
    // Drawn again where Peek AI's words can change.
    val now = peekAiNow(parcel, peekAi)
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    // Scans with places are drawn as a route across the top of the card, as on the opened parcel.
    val placed = parcel.hasMappedPlace

    // Kept while the card is scrolled out of the list, which saves it under the card's key: it fades in and its stamp
    // settles once, as the iPhone's lazy list keeps a card's state.
    var appeared by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(Unit) { appeared = true }
    val opacity by animateFloatAsState(
        if (appeared || reduceMotion) 1f else 0f,
        if (reduceMotion) snap() else Motion.easeOut(0.3),
        label = "next up appearing",
    )
    val press by keyframeAnimator(1.0, arrived) { PeekKeyframes.deliveredNextUp }

    val name = parcel.label.ifEmpty { localizer.text("common.parcel") }
    // While the reader's own list is searching, the name marks the match, and a match in the note shows its few words.
    val nameMarks = listSearchMarks(parcel.label)
    val noteHit = listNoteMatch(parcel)
    val nextUp = localizer.text("app.nextUp")
    val notice = automaticCarrierNotice(parcel)
    val delivering = parcel.deliveringCarrier?.let {
        localizer.text(parcel.deliveryLineKey, mapOf("carrier" to identities.displayName(it, language)))
    }
    val status = listOfNotNull(
        localizer.parcelStatus(parcel),
        parcel.pickupPlace,
        localizer.parcelExpectedDay(parcel, now) ?: if (arrived) localizer.parcelCompletionDate(parcel, now) else null,
    ).joinToString(" · ")
    val peekAiWords = localizer.parcelPeekAiWords(parcel, peekAi, now)
    // The carrier's site down for every parcel says so calmly, in place of the failed update.
    val outage = parcel.shownCarrierOutage?.let {
        localizer.text("attention.carrier_outage", mapOf("carrier" to identities.displayName(it.carrier, language)))
    }
    val flag = outage ?: if (parcel.updateFailed) localizer.text("attention.sync_error") else null
    val ink = colors.resolve(identity.ink)
    val atlas = rememberWorldAtlas(wanted = placed)

    Box(
        modifier
            .then(if (decorative) Modifier else Modifier.graphicsLayer { alpha = opacity })
            .cardButton(
                decorative = decorative,
                label = newUpdateLabel(
                    localizer.text("parcel.newUpdate").takeIf { newUpdate },
                    cardLabel(
                        identity.fullName, nextUp.takeUnless { arrived }, name, noteHeard(parcel, noteHit, localizer), notice, delivering, status,
                        peekAiWords?.let { localizer.text("peekAi.says", mapOf("words" to it)) }, flag,
                    ),
                ),
                hint = localizer.text("detail.label"),
                selected = selected,
                onOpen = onOpen,
                onLongClick = onLongClick,
                cornerRadius = Radii.nextUp,
                shadow = swipeAction == null,
                actions = listOfNotNull(swipeAction),
                onTap = onTap,
                interactionSource = LocalCardInteractions.current,
            )
            .graphicsLayer {
                val scale = if (reduceMotion) 1f else press.toFloat()
                scaleX = scale
                scaleY = scale
            }
            // A card the list can swipe is cut to its corners there, which takes its surface's shadow: the swipe's own
            // replaces it, in the same colours (`cardButton`).
            .experimentalSurface(fill = colors.resolve(identity.surface), cornerRadius = Radii.nextUp, shadow = decorative)
            .litEdge(ContinuousRoundedShape(Radii.nextUp))
            .fillMaxWidth(),
    ) {
        if (placed && atlas != null) {
            // The card is one tap target, so the drawing takes no touches of its own.
            RouteEngraving(
                atlas = atlas, route = rememberParcelRoute(parcel, atlas, language), stage = parcel.currentStage,
                identity = identity, language = language,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(UnevenContinuousRoundedShape(topLeading = Radii.nextUp, topTrailing = Radii.nextUp)),
                insets = EdgeInsets(top = 40.0, leading = 16.0, bottom = 28.0, trailing = 16.0), floor = 140.0, peek = false,
                entrance = if (reduceMotion) null else Motion.easeOut(0.5),
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 166.dp)
                .padding(18.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Top) {
                CarrierFleetMark(identity)
                Spacer(Modifier.weight(1f))
                // A parcel that has arrived is nobody's next: the words keep their room and go. They keep to the top
                // of the card, clear of the route drawn below them.
                NextUpWords(nextUp, identity, language.locale, map = placed, Modifier.alpha(if (arrived) 0f else 1f))
            }
            Row(
                Modifier.padding(top = if (placed) 101.dp else 19.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val (marked, markContent) = noteMarked(name, parcel)
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(1.dp)) {
                    SearchMarkedText(
                        marked,
                        nameMarks,
                        if (onName != null) Modifier.reportsFrameInList { onName(it.center.y) } else Modifier,
                        color = colors.label,
                        style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(),
                        inlineContent = markContent,
                    )
                    if (noteHit != null) NoteMatchLine(noteHit)
                }
                DeliveryPostageStamp(parcel, identity, appeared = appeared || decorative)
            }
            if (notice != null) Text(notice, color = colors.secondaryText, style = type.caption.sfLines())
            if (delivering != null) Text(delivering, color = colors.secondaryText, style = type.caption.sfLines())
            StatusLine(
                status = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NewUpdateDot(newUpdate, ink)
                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                            if (arrived) PeekSymbol("checkmark", font = type.style(IosTextStyle.Caption2, FontWeight.Light), tint = ink)
                            Text(status, color = ink, style = type.caption.sfLines())
                        }
                    }
                },
                dot = { Text(" · ", color = ink, style = type.caption.sfLines()) },
                peekAi = peekAiWords?.let { words -> { PeekAiPhrase(words, ink, type.caption) } },
            )
            if (flag != null) ParcelFlag(flag, "arrow.clockwise", Modifier.padding(top = 8.dp), calm = outage != null)
        }
        SelectedCardRing(selected, Radii.nextUp, Modifier.matchParentSize())
    }
}

/**
 * The card's status, then Peek AI's words after a dot, on their first baseline, where the two fit on one line; else the
 * words 3 under it, without the dot, which would end the status's line on nothing.
 */
@Composable
private fun StatusLine(status: @Composable () -> Unit, dot: @Composable () -> Unit, peekAi: (@Composable () -> Unit)?) {
    if (peekAi == null) return status()
    Layout(contents = listOf(status, dot, peekAi)) { (statusMeasurable, dotMeasurable, phraseMeasurable), constraints ->
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        val wide = listOf(statusMeasurable, dotMeasurable, phraseMeasurable).map { it.single().maxIntrinsicWidth(Constraints.Infinity) }
        if (!constraints.hasBoundedWidth || wide.sum() <= constraints.maxWidth) {
            val placed = listOf(statusMeasurable, dotMeasurable, phraseMeasurable).mapIndexed { index, it -> it.single().measure(loose.copy(maxWidth = wide[index])) }
            val baselines = placed.map { it[FirstBaseline] }
            val baseline = baselines.takeIf { AlignmentLine.Unspecified !in it }?.max()
            // Without a baseline, they are centred on each other.
            val height = placed.maxOf { it.height }
            val tops = placed.mapIndexed { index, it -> if (baseline != null) baseline - baselines[index] else (height - it.height) / 2 }
            layout(placed.sumOf { it.width }.coerceAtMost(constraints.maxWidth), placed.indices.maxOf { tops[it] + placed[it].height }) {
                var x = 0
                placed.forEachIndexed { index, it -> it.place(x, tops[index]); x += it.width }
            }
        } else {
            val shown = statusMeasurable.single().measure(loose)
            val words = phraseMeasurable.single().measure(loose)
            val gap = 3.dp.roundToPx()
            layout(maxOf(shown.width, words.width), shown.height + gap + words.height) {
                shown.place(0, 0)
                words.place(0, shown.height + gap)
            }
        }
    }
}

/**
 * "Next up" in caption2 capitals, tracked 1.2 and in the carrier's ink, as on the iPhone, moved toward the label's colour
 * where it wouldn't read at 4.5:1 on the card's map ([readableInk]), on one line that shrinks to 70 % before it ends in an
 * ellipsis, and no larger than at the first accessibility size.
 */
@Composable
private fun NextUpWords(text: String, identity: CarrierVisualIdentity, locale: java.util.Locale, map: Boolean, modifier: Modifier) {
    val colors = PeekTheme.colors
    val ink = colors.resolve(identity.ink)
    val surface = colors.resolve(identity.surface)
    val shade = remember(ink, surface, map, colors.label) { readableInk(ink, cardGrounds(ink, surface, map), colors.label) }
    CappedDynamicTypeSize(DynamicTypeSize.Accessibility1) {
        val type = PeekTheme.type
        val style = type.tracking(type.caption2, 1.2f).sfLines().copy(color = shade)
        val probe = LocalNextUpWords.current
        BasicText(
            text.uppercase(locale),
            // CoreText tracks after each letter, Android half before it: the words end where the iPhone's end.
            modifier.offset(x = (-0.6).dp),
            style = style,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            autoSize = MinimumScale(style.fontSize, 0.7f, 0.1.sp),
            onTextLayout = { probe?.invoke(it) },
        )
    }
}

/** Told how "Next up" is laid out: the tests check it keeps to one line, whole, as TalkBack doesn't read it. */
internal val LocalNextUpWords = staticCompositionLocalOf<((TextLayoutResult) -> Unit)?> { null }
