package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.plhery.peekdeliverytracker.core.hasCarrierUpdate
import com.plhery.peekdeliverytracker.core.hasMappedPlace
import com.plhery.peekdeliverytracker.core.isArchived
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.core.isFinal
import com.plhery.peekdeliverytracker.core.pickupPlace
import com.plhery.peekdeliverytracker.core.updateFailed
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.draw.CarrierFleetMark
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
 * `arrived`, `decorative`, `onName`, `selected`, `onLongClick` and `swipeAction` are as on [NextDeliveryPass]; an arrived card swells a
 * little as the news lands. Nothing is drawn until the carriers' liveries are read.
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
) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val identity = shownCarrierIdentity(parcel, localizer, language) ?: return
    val identities = LocalCarrierIdentities.current ?: return
    val now = LocalNow.current()
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val stage = parcel.currentStage

    val date = localizer.parcelDeliveryEstimate(parcel, now) ?: localizer.parcelCompletionDate(parcel, now)
    // Scans with places are drawn as a small route at the end of the card; at the largest text sizes the words take
    // the whole card, and the route goes.
    val placed = parcel.hasMappedPlace
    val drawsRoute = placed && !PeekTheme.dynamicTypeSize.isAccessibilitySize
    // A past delivery's card is paler, once it stands among the past deliveries.
    val past = !arrived && (stage?.isFinal == true || parcel.isArchived)
    // Customs and a pickup say all they need in the status line; after a missed delivery or a problem, or while customs
    // asks the reader for something, the carrier waits on the reader.
    val actionNeeded = parcel.currentEvent?.recipientAction != null ||
        stage == TrackingStage.FailedAttempt || stage == TrackingStage.Exception
    val carrierIssue = !actionNeeded && (stage == TrackingStage.Customs || stage == TrackingStage.ReadyForPickup)
    val flag = if (notice != null && !carrierIssue) notice else if (parcel.updateFailed) localizer.text("attention.sync_error") else null
    val flagSymbol = when {
        notice != null && actionNeeded -> "exclamationmark.triangle"
        parcel.updateFailed -> "arrow.clockwise"
        else -> "clock"
    }
    val showsStatus = flag == null || parcel.hasCarrierUpdate

    val name = parcel.label.ifEmpty { localizer.text("common.parcel") }
    val carrierNotice = automaticCarrierNotice(parcel)
    val delivering = parcel.deliveringCarrier?.let {
        localizer.text("parcel.deliveryCarrier", mapOf("carrier" to identities.displayName(it, language)))
    }
    val status = listOfNotNull(localizer.parcelStatus(parcel), parcel.pickupPlace).joinToString(" · ")
    val ink = colors.resolve(identity.ink)
    val surface = colors.resolve(identity.surface)
    val routeSurface = remember(identity, past) {
        if (past) Brand.paper.mix(identity.surface, 0.35, GradientColorSpace.Device) else identity.surface
    }
    val press by keyframeAnimator(1.0, arrived) { PeekKeyframes.deliveredCompact }
    val atlas = rememberWorldAtlas(wanted = drawsRoute)
    val shape = RoundedCornerShape(Radii.card)

    Box(
        modifier
            .cardButton(
                decorative = decorative,
                label = cardLabel(
                    identity.fullName, date, name, carrierNotice, delivering, status.takeIf { showsStatus }, flag,
                ),
                hint = null,
                selected = selected,
                onOpen = onOpen,
                onLongClick = onLongClick,
                cornerRadius = Radii.card,
                shadow = false,
                actions = listOfNotNull(swipeAction),
            )
            .graphicsLayer {
                val scale = if (reduceMotion) 1f else press.toFloat()
                scaleX = scale
                scaleY = scale
            }
            // `RoundedRectangle(cornerRadius: 16)`: circular corners, which the swipe's continuous ones cut a little.
            .clip(shape)
            .background(colors.paper, shape)
            .background(surface.copy(alpha = surface.alpha * if (past) 0.35f else 1f), shape)
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
                date = date?.let { { Text(it, color = ink, style = type.caption.sfLines()) } },
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
                if (carrierNotice != null) Text(carrierNotice, color = colors.secondaryLabel, style = type.caption.sfLines())
                if (delivering != null) Text(delivering, color = colors.secondaryLabel, style = type.caption.sfLines())
                if (showsStatus) {
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                        if (parcel.isDelivered) {
                            PeekSymbol("checkmark", font = type.style(IosTextStyle.Caption2, FontWeight.Light), tint = ink)
                        }
                        Text(status, color = ink, style = type.caption.sfLines())
                    }
                }
                if (flag != null) ParcelFlag(flag, flagSymbol, Modifier.padding(top = 4.dp))
            }
        }
        SelectedCardRing(selected, Radii.card, continuous = false, Modifier.matchParentSize())
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
 * `ViewThatFits(in: .horizontal)`: the carrier and the date on one row, the date at the trailing end and both on top,
 * when the two fit at their own widths with 12 points either side of the space between; else the date under the
 * carrier, 6 points down.
 */
@Composable
private fun CardHeader(mark: @Composable () -> Unit, date: (@Composable () -> Unit)?, modifier: Modifier = Modifier) {
    Layout(content = { mark(); date?.invoke() }, modifier = modifier) { measurables, constraints ->
        val spacing = 12.dp.roundToPx()
        val markIdeal = measurables[0].maxIntrinsicWidth(Constraints.Infinity)
        val dateIdeal = measurables.getOrNull(1)?.maxIntrinsicWidth(Constraints.Infinity)
        val row = markIdeal + spacing + (dateIdeal?.let { it + spacing } ?: 0)
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        if (row <= constraints.maxWidth) {
            val markPlaced = measurables[0].measure(loose.copy(maxWidth = markIdeal))
            val datePlaced = dateIdeal?.let { measurables[1].measure(loose.copy(maxWidth = it)) }
            val height = maxOf(markPlaced.height, datePlaced?.height ?: 0)
            layout(constraints.maxWidth, height) {
                markPlaced.place(0, 0)
                datePlaced?.place(constraints.maxWidth - datePlaced.width, 0)
            }
        } else {
            val markPlaced = measurables[0].measure(loose)
            val datePlaced = measurables.getOrNull(1)?.measure(loose)
            val gap = if (datePlaced != null) 6.dp.roundToPx() else 0
            layout(constraints.maxWidth, markPlaced.height + gap + (datePlaced?.height ?: 0)) {
                markPlaced.place(0, 0)
                datePlaced?.place(0, markPlaced.height + gap)
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
) {
    ParcelPassCard(
        parcel, notice = null, onOpen = onOpen, modifier = modifier, onName = onName, selected = selected, onLongClick = onLongClick,
        swipeAction = swipeAction,
    )
}
