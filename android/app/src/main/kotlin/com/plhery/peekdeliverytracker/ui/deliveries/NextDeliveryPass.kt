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
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.EdgeInsets
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.core.deliveringCarrier
import com.plhery.peekdeliverytracker.core.hasMappedPlace
import com.plhery.peekdeliverytracker.core.pickupPlace
import com.plhery.peekdeliverytracker.core.updateFailed
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.draw.CarrierFleetMark
import com.plhery.peekdeliverytracker.ui.draw.DeliveryPostageStamp
import com.plhery.peekdeliverytracker.ui.draw.UnevenContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.experimentalSurface
import com.plhery.peekdeliverytracker.ui.map.RouteEngraving
import com.plhery.peekdeliverytracker.ui.map.rememberParcelRoute
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.CappedDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekKeyframes
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.Radii
import com.plhery.peekdeliverytracker.ui.theme.keyframeAnimator
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
) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val identity = shownCarrierIdentity(parcel, localizer, language) ?: return
    val identities = LocalCarrierIdentities.current ?: return
    val now = LocalNow.current()
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
    val nextUp = localizer.text("app.nextUp")
    val notice = automaticCarrierNotice(parcel)
    val delivering = parcel.deliveringCarrier?.let {
        localizer.text("parcel.deliveryCarrier", mapOf("carrier" to identities.displayName(it, language)))
    }
    val status = listOfNotNull(
        localizer.parcelStatus(parcel),
        parcel.pickupPlace,
        localizer.parcelDeliveryEstimate(parcel, now) ?: if (arrived) localizer.parcelCompletionDate(parcel, now) else null,
    ).joinToString(" · ")
    val flag = if (parcel.updateFailed) localizer.text("attention.sync_error") else null
    val ink = colors.resolve(identity.ink)
    val atlas = rememberWorldAtlas(wanted = placed)

    Box(
        modifier
            .then(if (decorative) Modifier else Modifier.graphicsLayer { alpha = opacity })
            .cardButton(
                decorative = decorative,
                label = cardLabel(identity.fullName, nextUp.takeUnless { arrived }, name, notice, delivering, status, flag),
                hint = localizer.text("detail.label"),
                selected = selected,
                onOpen = onOpen,
                onLongClick = onLongClick,
                cornerRadius = Radii.nextUp,
                shadow = swipeAction == null,
                actions = listOfNotNull(swipeAction),
            )
            .graphicsLayer {
                val scale = if (reduceMotion) 1f else press.toFloat()
                scaleX = scale
                scaleY = scale
            }
            // A card the list can swipe is cut to its corners there, which takes its surface's shadow: the swipe's own
            // replaces it, in the same colours (`cardButton`).
            .experimentalSurface(fill = colors.resolve(identity.surface), cornerRadius = Radii.nextUp, shadow = decorative)
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
                insets = EdgeInsets(top = 40.0, leading = 16.0, bottom = 28.0, trailing = 16.0), floor = 140.0,
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
                NextUpWords(nextUp, identity, language.locale, Modifier.alpha(if (arrived) 0f else 1f))
            }
            Row(
                Modifier.padding(top = if (placed) 101.dp else 19.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    name,
                    Modifier
                        .weight(1f)
                        .then(if (onName != null) Modifier.reportsFrameInList { onName(it.center.y) } else Modifier),
                    color = colors.label,
                    style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(),
                )
                DeliveryPostageStamp(parcel, identity, appeared = appeared || decorative)
            }
            if (notice != null) Text(notice, color = colors.secondaryLabel, style = type.caption.sfLines())
            if (delivering != null) Text(delivering, color = colors.secondaryLabel, style = type.caption.sfLines())
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                if (arrived) PeekSymbol("checkmark", font = type.style(IosTextStyle.Caption2, FontWeight.Light), tint = ink)
                Text(status, color = ink, style = type.caption.sfLines())
            }
            if (flag != null) ParcelFlag(flag, "arrow.clockwise", Modifier.padding(top = 8.dp))
        }
        SelectedCardRing(selected, Radii.nextUp, continuous = true, Modifier.matchParentSize())
    }
}

/**
 * "Next up" in caption2 capitals, tracked 1.2 and in the carrier's ink at 75 %, on one line that shrinks to 70 % before
 * it ends in an ellipsis, and no larger than at the first accessibility size.
 */
@Composable
private fun NextUpWords(text: String, identity: CarrierVisualIdentity, locale: java.util.Locale, modifier: Modifier) {
    val ink = PeekTheme.colors.resolve(identity.ink)
    CappedDynamicTypeSize(DynamicTypeSize.Accessibility1) {
        val type = PeekTheme.type
        val style = type.tracking(type.caption2, 1.2f).sfLines().copy(color = ink.copy(alpha = ink.alpha * 0.75f))
        BasicText(
            text.uppercase(locale),
            // CoreText tracks after each letter, Android half before it: the words end where the iPhone's end.
            modifier.offset(x = (-0.6).dp),
            style = style,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            autoSize = TextAutoSize.StepBased(minFontSize = style.fontSize * 0.7f, maxFontSize = style.fontSize, stepSize = 0.1.sp),
        )
    }
}
