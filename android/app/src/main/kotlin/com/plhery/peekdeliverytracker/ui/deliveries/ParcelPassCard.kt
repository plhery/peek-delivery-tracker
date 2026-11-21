package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.core.GradientColorSpace
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentEvent
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.core.deliveringCarrier
import com.plhery.peekdeliverytracker.core.deliveryLineKey
import com.plhery.peekdeliverytracker.core.hasCarrierUpdate
import com.plhery.peekdeliverytracker.core.hasMappedPlace
import com.plhery.peekdeliverytracker.core.isArchived
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.core.isFinal
import com.plhery.peekdeliverytracker.core.parcelPeekAiWords
import com.plhery.peekdeliverytracker.core.pickupPlace
import com.plhery.peekdeliverytracker.core.shownCarrierOutage
import com.plhery.peekdeliverytracker.core.updateFailed
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.LocalUse24HourTime
import com.plhery.peekdeliverytracker.ui.draw.CarrierFleetMark
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.litEdge
import com.plhery.peekdeliverytracker.ui.map.CardRoute
import com.plhery.peekdeliverytracker.ui.map.CardRouteMetrics
import com.plhery.peekdeliverytracker.ui.map.rememberParcelRoute
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekKeyframes
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.Radii
import com.plhery.peekdeliverytracker.ui.theme.keyframeAnimator
import com.plhery.peekdeliverytracker.ui.theme.rememberMapInk
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.shownCarrierIdentity
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * `ExperimentalParcelPassCard` (A-S35): a parcel's card in the list. Its carrier and date on top (the date under the
 * carrier when both don't fit on one line), its name, and its status or the problem it has (`notice`, the list's
 * attention for the parcel), with a small route at its end when its scans have places and the text is not one of the
 * accessibility sizes. A past delivery's card is paler.
 *
 * `arrived`, `decorative`, `onName`, `selected`, `onLongClick`, `swipeAction` and `onTap` are as on [NextDeliveryPass];
 * an arrived card swells a little as the news lands. `newUpdate`, a scan the reader hasn't seen (#72), puts a
 * [NewUpdateDot] before the status, and TalkBack says so first. `frozen`, a copy the service forgot, still on its way
 * reads "No longer updated" after its status in place of its date or its problem (`frozenOnTheWay`), as neither may
 * hold any more; delivered, it needs no mark. While `peekAi` is on, Peek AI's words ([PeekAiPhrase]) go under the
 * carrier's date, or in its place, and TalkBack reads them after the status. Nothing is drawn until the carriers'
 * liveries are read.
 */
@Composable
fun ParcelPassCard(
    parcel: Parcel,
    notice: String?,
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
    frozen: Boolean = false,
    peekAi: Boolean = false,
) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    LocalUse24HourTime.current
    val identity = shownCarrierIdentity(parcel, localizer, language) ?: return
    val identities = LocalCarrierIdentities.current ?: return
    val now = LocalNow.current()
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val stage = parcel.currentStage
    // A forgotten copy still on its way: its last answer stays, but not its estimate or its problem.
    val stale = frozen && stage?.isFinal != true

    val date = if (stale) null else localizer.parcelDeliveryEstimate(parcel, now) ?: localizer.parcelCompletionDate(parcel, now)
    val peekAiWords = if (stale) null else localizer.parcelPeekAiWords(parcel, peekAi, now)
    // Scans with places are drawn as a small route at the end of the card; at the largest text sizes the words take
    // the whole card, and the route goes.
    val placed = parcel.hasMappedPlace
    val drawsRoute = placed && !PeekTheme.dynamicTypeSize.isAccessibilitySize
    // A past delivery's card is paler, once it stands among the past deliveries.
    val past = !arrived && (stage?.isFinal == true || parcel.isArchived)
    // Customs and a pickup say all they need in the status line; after a missed delivery or a problem, or while customs
    // asks the reader for something, the carrier waits on the reader.
    val actionNeeded = parcel.currentEvent?.waitsOnReader() == true
    val carrierIssue = !actionNeeded && (stage == TrackingStage.Customs || stage == TrackingStage.ReadyForPickup)
    // The carrier's site down tells why no update comes, unless the carrier waits on the reader.
    val outage = parcel.shownCarrierOutage?.takeIf { !actionNeeded }?.let {
        localizer.text("attention.carrier_outage", mapOf("carrier" to identities.displayName(it.carrier, language)))
    }
    val flag = when {
        stale -> null
        notice != null && !carrierIssue && outage == null -> notice
        outage != null -> outage
        parcel.updateFailed -> localizer.text("attention.sync_error")
        else -> null
    }
    val flagSymbol = when {
        notice != null && actionNeeded -> "exclamationmark.triangle"
        parcel.updateFailed || outage != null -> "arrow.clockwise"
        else -> "clock"
    }
    val showsStatus = flag == null || parcel.hasCarrierUpdate

    val name = parcel.label.ifEmpty { localizer.text("common.parcel") }
    val carrierNotice = automaticCarrierNotice(parcel)
    val delivering = parcel.deliveringCarrier?.let {
        localizer.text(parcel.deliveryLineKey, mapOf("carrier" to identities.displayName(it, language)))
    }
    val status = listOfNotNull(
        localizer.parcelStatus(parcel), parcel.pickupPlace, localizer.text("link.frozen.label").takeIf { stale },
    ).joinToString(" · ")
    val ink = colors.resolve(identity.ink)
    // A long status runs over the start of the route's map, where it takes the ink that reads on its countries.
    val mapInk = rememberMapInk(identity)
    val statusInk = if (drawsRoute) mapInk else ink
    val surface = colors.resolve(identity.surface)
    val routeSurface = remember(identity, past) {
        if (past) Brand.paper.mix(identity.surface, 0.35, GradientColorSpace.Device) else identity.surface
    }
    val press by keyframeAnimator(1.0, arrived) { PeekKeyframes.deliveredCompact }
    val atlas = rememberWorldAtlas(wanted = drawsRoute)
    val shape = ContinuousRoundedShape(Radii.card)

    Box(
        modifier
            .cardButton(
                decorative = decorative,
                label = newUpdateLabel(
                    localizer.text("parcel.newUpdate").takeIf { newUpdate && showsStatus },
                    cardLabel(
                        identity.fullName, date, name, carrierNotice, delivering, status.takeIf { showsStatus },
                        peekAiWords?.let { localizer.text("peekAi.says", mapOf("words" to it)) }, flag,
                    ),
                ),
                hint = null,
                selected = selected,
                onOpen = onOpen,
                onLongClick = onLongClick,
                cornerRadius = Radii.card,
                shadow = false,
                actions = listOfNotNull(swipeAction),
                onTap = onTap,
                interactionSource = LocalCardInteractions.current,
            )
            .graphicsLayer {
                val scale = if (reduceMotion) 1f else press.toFloat()
                scaleX = scale
                scaleY = scale
            }
            // `.clipShape(RoundedRectangle(cornerRadius: 16))`, continuous as the card button's cut.
            .clip(shape)
            .background(colors.paper, shape)
            .background(surface.copy(alpha = surface.alpha * if (past) 0.35f else 1f), shape)
            .litEdge(shape)
            .fillMaxWidth(),
    ) {
        if (drawsRoute && atlas != null) {
            Box(Modifier.matchParentSize().layout { measurable, constraints -> trailingRoute(measurable, constraints) }) {
                CardRoute(
                    atlas = atlas, route = rememberParcelRoute(parcel, atlas, language), ink = identity.ink, surface = routeSurface,
                    language = language, entrance = if (reduceMotion) null else Motion.easeOut(0.5),
                )
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 102.dp)
                .padding(horizontal = 15.dp, vertical = 13.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        ) {
            CardHeader(
                mark = { CarrierFleetMark(identity) },
                dates = listOfNotNull(
                    date?.let { @Composable { Text(it, color = ink, style = type.caption.sfLines()) } },
                    peekAiWords?.let { @Composable { PeekAiPhrase(it, ink, type.caption) } },
                ),
                modifier = Modifier.padding(bottom = 3.dp),
            )
            Column(
                Modifier.padding(end = if (drawsRoute) CardRouteMetrics.clearance else 0.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Text(
                    name,
                    if (onName != null) Modifier.reportsFrameInList { onName(it.center.y) } else Modifier,
                    color = colors.label,
                    style = type.style(IosTextStyle.Headline, FontWeight.SemiBold).sfLines(),
                )
                if (carrierNotice != null) Text(carrierNotice, color = colors.secondaryText, style = type.caption.sfLines())
                if (delivering != null) Text(delivering, color = colors.secondaryText, style = type.caption.sfLines())
                if (showsStatus) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NewUpdateDot(newUpdate, statusInk)
                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                            if (parcel.isDelivered) {
                                PeekSymbol("checkmark", font = type.style(IosTextStyle.Caption2, FontWeight.Light), tint = statusInk)
                            }
                            Text(status, color = statusInk, style = type.caption.sfLines())
                        }
                    }
                }
                if (flag != null) ParcelFlag(flag, flagSymbol, Modifier.padding(top = 4.dp), calm = flag == outage)
            }
        }
        SelectedCardRing(selected, Radii.card, Modifier.matchParentSize())
    }
}

/** `CardRoute.width(in:)` wide at the card's trailing edge, as tall as the card. */
private fun androidx.compose.ui.layout.MeasureScope.trailingRoute(
    measurable: androidx.compose.ui.layout.Measurable,
    constraints: Constraints,
): androidx.compose.ui.layout.MeasureResult {
    val width = min(constraints.maxWidth.toFloat(), CardRouteMetrics.width(constraints.maxWidth.toDp()).toPx()).roundToInt()
    val placeable = measurable.measure(Constraints.fixed(width, constraints.maxHeight))
    return layout(constraints.maxWidth, constraints.maxHeight) { placeable.place(constraints.maxWidth - width, 0) }
}

/**
 * `ViewThatFits(in: .horizontal)`: the carrier and the dates on one row, the dates at the trailing end, the carrier's
 * over Peek AI's 2 points apart, and all on top, when they fit at their own widths with 12 points either side of the
 * space between; else the dates under the carrier, 6 points down.
 */
@Composable
private fun CardHeader(mark: @Composable () -> Unit, dates: List<@Composable () -> Unit>, modifier: Modifier = Modifier) {
    Layout(content = { mark(); dates.forEach { it() } }, modifier = modifier) { measurables, constraints ->
        val spacing = 12.dp.roundToPx()
        val between = 2.dp.roundToPx()
        val markIdeal = measurables[0].maxIntrinsicWidth(Constraints.Infinity)
        val dated = measurables.drop(1)
        val datesIdeal = dated.map { it.maxIntrinsicWidth(Constraints.Infinity) }
        val row = markIdeal + spacing + (datesIdeal.maxOrNull()?.let { it + spacing } ?: 0)
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        if (row <= constraints.maxWidth) {
            val markPlaced = measurables[0].measure(loose.copy(maxWidth = markIdeal))
            val datesPlaced = dated.mapIndexed { index, it -> it.measure(loose.copy(maxWidth = datesIdeal[index])) }
            val datesHeight = datesPlaced.sumOf { it.height } + between * (datesPlaced.size - 1).coerceAtLeast(0)
            layout(constraints.maxWidth, maxOf(markPlaced.height, datesHeight)) {
                markPlaced.place(0, 0)
                var y = 0
                for (placed in datesPlaced) {
                    placed.place(constraints.maxWidth - placed.width, y)
                    y += placed.height + between
                }
            }
        } else {
            val markPlaced = measurables[0].measure(loose)
            val datesPlaced = dated.map { it.measure(loose) }
            val gap = if (datesPlaced.isNotEmpty()) 6.dp.roundToPx() else 0
            val datesHeight = datesPlaced.sumOf { it.height } + between * (datesPlaced.size - 1).coerceAtLeast(0)
            layout(constraints.maxWidth, markPlaced.height + gap + datesHeight) {
                markPlaced.place(0, 0)
                var y = markPlaced.height + gap
                for (placed in datesPlaced) {
                    placed.place(0, y)
                    y += placed.height + between
                }
            }
        }
    }
}

/**
 * `ExperimentalDeliveredParcelCard`: a past delivery's card, which has no notice. It swells as its delivery lands only
 * while it still stands among the parcels on their way, as [ParcelPassCard] with `arrived`.
 */
@Composable
fun DeliveredParcelCard(
    parcel: Parcel,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier,
    onName: ((Float) -> Unit)? = null,
    selected: Boolean = false,
    onLongClick: (() -> Unit)? = null,
    swipeAction: CustomAccessibilityAction? = null,
    onTap: () -> Unit = onOpen,
    newUpdate: Boolean = false,
    peekAi: Boolean = false,
) {
    ParcelPassCard(
        parcel, notice = null, onOpen = onOpen, modifier = modifier, onName = onName, selected = selected, onLongClick = onLongClick,
        swipeAction = swipeAction, onTap = onTap, newUpdate = newUpdate, peekAi = peekAi,
    )
}
