package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.activeTrackingCarrier
import com.plhery.peekdeliverytracker.core.currentEvent
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.core.displayDate
import com.plhery.peekdeliverytracker.core.isFinal
import com.plhery.peekdeliverytracker.core.metadata
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.draw.experimentalSurface
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.time.Instant
import java.util.UUID

/**
 * `ExperimentalArchiveShelf` (A-S39): the archived parcels under the deliveries, behind a header that names them and
 * counts them, its chevron turned up while they show. A tap shows or hides them with a snappy 0.3 s fade and slide from
 * the top, and a selection tick; at once under Reduce Motion. `selected` is the parcel shown beside the list;
 * `onLongClick` and `rowModifier` are the list's for each row, as on [ArchivedParcelGroup].
 */
@Composable
fun ArchiveShelf(
    parcels: List<Parcel>,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onOpen: (UUID) -> Unit,
    modifier: Modifier = Modifier,
    selected: UUID? = null,
    onLongClick: ((Parcel) -> Unit)? = null,
    rowModifier: @Composable (Parcel) -> Modifier = { Modifier },
) {
    val localizer = LocalLocalizer.current
    // The localizer is the same in every language: its words are written again as the language changes.
    val language = LocalLanguage.current
    val title = remember(localizer, language) { localizer.text("app.archived") }
    val hint = remember(localizer, language, expanded) { localizer.text(if (expanded) "design.hideArchive" else "design.showArchive") }
    val reduceMotion = PeekTheme.reduceMotion
    SensoryFeedbackEffect(SensoryFeedback.Selection, expanded)
    Column(modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ArchiveShelfHeader(
            title = title,
            count = parcels.size,
            expanded = expanded,
            label = cardLabel(title, parcels.size.toString()),
            hint = hint,
            onToggle = { onExpandedChange(!expanded) },
        )
        val spring = Motion.snappy<Float>(0.3, extraBounce = 0.02)
        val springSize = Motion.snappy<androidx.compose.ui.unit.IntSize>(0.3, extraBounce = 0.02)
        val springOffset = Motion.snappy<IntOffset>(0.3, extraBounce = 0.02)
        AnimatedVisibility(
            visible = expanded,
            // `.transition(.opacity.combined(with: .move(edge: .top)))`, the rows below making room as it comes.
            enter = if (reduceMotion) EnterTransition.None else
                fadeIn(spring) + slideInVertically(springOffset) { -it } + expandVertically(springSize, Alignment.Top, clip = false),
            exit = if (reduceMotion) ExitTransition.None else
                fadeOut(spring) + slideOutVertically(springOffset) { -it } + shrinkVertically(springSize, Alignment.Top, clip = false),
        ) {
            ArchivedParcelGroup(parcels, onOpen, selected = selected, onLongClick = onLongClick, rowModifier = rowModifier)
        }
    }
}

/**
 * The shelf's header: the archive box, `title` ("Archived"), the count in brackets and the chevron, all secondary, a
 * lift under the finger. One button, read as "Archived, n", whose action shows or hides the archive.
 */
@Composable
private fun ArchiveShelfHeader(title: String, count: Int, expanded: Boolean, label: String, hint: String, onToggle: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val turn by animateFloatAsState(
        if (expanded) 180f else 0f,
        if (reduceMotion) snap() else Motion.snappy(0.3, extraBounce = 0.02),
        label = "chevron",
    )
    Row(
        Modifier
            .clearAndSetSemantics {
                contentDescription = label
                role = Role.Button
                onClick(label = hint) {
                    onToggle()
                    true
                }
            }
            .clickable(interactionSource = null, indication = PressStyle.ExperimentalLift, onClick = onToggle)
            .fillMaxWidth()
            .heightIn(min = 44.dp)
            .padding(horizontal = 2.dp, vertical = 9.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.width(22.dp), contentAlignment = Alignment.Center) {
            PeekSymbol("archivebox", font = type.subheadline, tint = colors.secondaryLabel)
        }
        Text(title, color = colors.secondaryLabel, style = type.subheadline.sfLines())
        Spacer(Modifier.weight(1f))
        // `Spacer(minLength: 6)` between the words and the count, and 9 on either side of it.
        Text("($count)", Modifier.padding(start = 6.dp), color = colors.secondaryLabel, style = type.caption.monospacedDigit().sfLines())
        PeekSymbol(
            "chevron.down",
            Modifier.graphicsLayer { rotationZ = turn },
            font = type.style(IosTextStyle.Caption, FontWeight.Normal),
            tint = colors.secondaryLabel,
        )
    }
}

/**
 * `ExperimentalArchivedParcelGroup`: archived parcels' rows on one surface (corner 18, no shadow), each but the last
 * with a hairline under it from past its symbol. The list uses it for a search's archived results too. For each row,
 * `onLongClick` is the list's card menu (Swift's `menu: (Parcel) -> CardMenu`) and `rowModifier` what else the list
 * puts on it, such as the zoom into the parcel's page.
 */
@Composable
fun ArchivedParcelGroup(
    parcels: List<Parcel>,
    onOpen: (UUID) -> Unit,
    modifier: Modifier = Modifier,
    selected: UUID? = null,
    onLongClick: ((Parcel) -> Unit)? = null,
    rowModifier: @Composable (Parcel) -> Modifier = { Modifier },
) {
    val hairline = with(LocalDensity.current) { 1.toDp() }
    val divider = PeekTheme.colors.divider
    Column(modifier.experimentalSurface(cornerRadius = 18.dp, shadow = false)) {
        parcels.forEachIndexed { index, parcel ->
            key(parcel.id) {
                ArchivedParcelRow(
                    parcel, onOpen = { onOpen(parcel.id) }, modifier = rowModifier(parcel), selected = parcel.id == selected,
                    onLongClick = onLongClick?.let { { it(parcel) } },
                )
                if (index < parcels.lastIndex) {
                    Box(
                        Modifier
                            .padding(start = 48.dp)
                            .fillMaxWidth()
                            .height(hairline)
                            .background(divider),
                    )
                }
            }
        }
    }
}

/**
 * `ExperimentalArchivedParcelRow` (A-S40): an archived parcel's stage symbol in its tint, its name, its carrier and status,
 * and the day it was delivered or archived, on one line each; at the accessibility sizes the symbol goes and the words
 * wrap, with the day under them. One button, its texts read together, selected while the parcel is shown beside the
 * list; tinted with the ink then. A lift under the finger; a long press calls `onLongClick` ([cardTouches]).
 */
@Composable
fun ArchivedParcelRow(
    parcel: Parcel,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onLongClick: (() -> Unit)? = null,
) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current ?: return
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    // At the largest text sizes the row gives its words the width, and the date goes under them.
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val name = parcel.label.ifEmpty { localizer.text("common.parcel") }
    val detail = "${identities.displayName(parcel.activeTrackingCarrier, language)} · ${localizer.parcelStatus(parcel)}"
    val date = parcel.archivedDisplayDate?.let(localizer::shortDate)
    val lines = if (large) Int.MAX_VALUE else 1

    Row(
        modifier
            .cardTouches(cardLabel(name, detail, date), hint = null, selected = selected, onOpen = onOpen, onLongClick = onLongClick)
            .then(if (selected) Modifier.background(colors.ink.copy(alpha = colors.ink.alpha * 0.08f)) else Modifier)
            .fillMaxWidth()
            .heightIn(min = 58.dp)
            .padding(horizontal = 13.dp, vertical = if (large) 10.dp else 0.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (!large) {
            Box(Modifier.width(28.dp), contentAlignment = Alignment.Center) {
                PeekSymbol(
                    parcel.currentStage?.metadata?.symbol ?: "shippingbox.fill",
                    font = type.subheadline,
                    tint = colors.resolve(ExperimentalPalette.tint(parcel)),
                )
            }
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(
                name, color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines(),
                maxLines = lines, overflow = TextOverflow.Ellipsis,
            )
            Text(detail, color = colors.secondaryLabel, style = type.caption.sfLines(), maxLines = lines, overflow = TextOverflow.Ellipsis)
            if (large && date != null) ArchivedDate(date)
        }
        // `Spacer(minLength: 6)`: the words take what the date leaves them.
        Spacer(Modifier.width(6.dp))
        if (!large && date != null) ArchivedDate(date)
        PeekSymbol("chevron.right", font = type.style(IosTextStyle.Caption2, FontWeight.Bold), tint = colors.tertiaryLabel)
    }
}

@Composable
private fun ArchivedDate(date: String) {
    val type = PeekTheme.type
    Text(
        date, color = PeekTheme.colors.secondaryLabel,
        style = type.style(IosTextStyle.Caption, FontWeight.SemiBold).monospacedDigit().sfLines(), maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

/** The day an archived parcel was delivered or returned, else the day it was archived. */
private val Parcel.archivedDisplayDate: Instant?
    get() = currentEvent?.takeIf { it.stage.isFinal }?.displayDate ?: archivedAt?.let(DateParser::date)
