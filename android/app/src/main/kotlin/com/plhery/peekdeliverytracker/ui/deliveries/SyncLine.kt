package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentEvent
import com.plhery.peekdeliverytracker.core.isArchived
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines

/**
 * `syncStatus` (B-S08): when the carrier was last asked and last said something, as the web says them, 3 apart, in
 * caption medium in the secondary grey: when the next check comes, in caption regular, as the iPhone's format style
 * writes a date with its time ([com.plhery.peekdeliverytracker.core.Localizer.abbreviatedDateTime]), while the
 * parcel isn't archived; how long ago the carrier was last asked; and how long ago its latest scan was, but a pending
 * one. The times are those of when the page was drawn, as the iPhone's are. Then, 7 after them and centred on them,
 * the refresh where `onRefresh` is given ([RefreshButton]), waiting while `working`. Nothing where there is nothing to
 * say, so the page leaves no gap for it.
 */
@Composable
internal fun SyncLine(parcel: Parcel, tint: Color, working: Boolean, onRefresh: (() -> Unit)?, modifier: Modifier = Modifier) {
    val localizer = LocalLocalizer.current
    val now = LocalNow.current()
    val next = parcel.carrierData?.routing?.nextCheckAt?.let(DateParser::date)?.takeUnless { parcel.isArchived }
    val checked = parcel.lastSyncedAt
    val update = parcel.currentEvent?.takeIf { it.stage != TrackingStage.Pending }
    if (next == null && checked == null && update == null && onRefresh == null) return
    val type = PeekTheme.type
    val secondary = PeekTheme.colors.secondaryLabel
    val medium = type.style(IosTextStyle.Caption, FontWeight.Medium).sfLines()
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            if (next != null) {
                Text(
                    localizer.text("detail.nextCheckAfter", mapOf("date" to localizer.abbreviatedDateTime(next))),
                    color = secondary,
                    style = type.caption.sfLines(),
                )
            }
            if (checked != null) {
                Text(localizer.text("detail.lastChecked", mapOf("date" to localizer.relativeTime(checked, now))), color = secondary, style = medium)
            }
            if (update != null) {
                Text(localizer.text("detail.lastUpdate", mapOf("date" to localizer.relativeTime(update, now))), color = secondary, style = medium)
            }
        }
        if (onRefresh != null) RefreshButton(tint, working, onRefresh)
    }
}

/**
 * The sync line's refresh: an arrow in caption in `tint`, in 26 square, which TalkBack hears as "Check now". It waits
 * while `working`, its arrow turning a turn a second, or pulsing under Reduce Motion, as the list's does
 * ([refreshing]). Compose takes touches up to 48 square around so small a button, as Android asks, and leaves the
 * line as the iPhone lays it.
 */
@Composable
private fun RefreshButton(tint: Color, working: Boolean, onRefresh: () -> Unit) {
    val label = LocalLocalizer.current.text("detail.checkNow")
    Box(
        Modifier
            .size(REFRESH_SIDE)
            .testTag(PAGE_REFRESH)
            .clickable(interactionSource = null, indication = PlainPress, enabled = !working, role = Role.Button, onClick = onRefresh)
            .semantics { contentDescription = label },
        contentAlignment = Alignment.Center,
    ) {
        PeekSymbol(
            "arrow.clockwise",
            if (working) Modifier.refreshing(PeekTheme.reduceMotion) else Modifier,
            font = PeekTheme.type.caption,
            tint = tint,
        )
    }
}

private val REFRESH_SIDE = 26.dp

/** The test tags of the page's sync line and its refresh. */
const val PAGE_SYNC_LINE = "parcelPage.syncLine"
const val PAGE_REFRESH = "parcelPage.refresh"
