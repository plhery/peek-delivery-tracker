package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.DeliveryEmail
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.NotificationPreset
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.deliveryUpdatesPlace
import com.plhery.peekdeliverytracker.core.isEmailMuted
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.Toggle
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetMenus
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.util.UUID

// ParcelAlerts.swift's `ParcelAlertsSheet` and `ParcelAlertSwitches`: a parcel's alerts, opened from its bell while the
// account's delivery email is on.

/** A switch of a parcel's alerts (`ParcelAlertSwitches.Alert`). */
internal enum class ParcelAlert { Notifications, Email }

/** The switch being saved, and the position it was flipped to (`saving`). */
@Immutable
internal data class AlertSave(val alert: ParcelAlert, val on: Boolean)

/**
 * A parcel's alerts open from the page with `pageId` (`showingAlerts`), each opening another ([id]), with the switch
 * being saved and whether Done waits for it (`closing`).
 */
@Immutable
internal data class AlertsRequest(val id: UUID, val pageId: UUID, val saving: AlertSave? = null, val closing: Boolean = false) {
    /** As a window keeps it: the ids, the switch being saved and its position or "-", and whether Done waits, a space between. */
    val saved: String
        get() = listOf(id, pageId, saving?.alert?.name ?: "-", saving?.on ?: "-", closing).joinToString(" ")

    companion object {
        fun from(saved: String): AlertsRequest {
            val (id, pageId, alert, on, closing) = saved.split(" ")
            val saving = alert.takeIf { it != "-" }?.let { AlertSave(ParcelAlert.valueOf(it), on.toBoolean()) }
            return AlertsRequest(UUID.fromString(id), UUID.fromString(pageId), saving, closing.toBoolean())
        }
    }
}

/** What the alerts' sheet shows, kept as it goes down. */
private data class AlertsShown(val request: AlertsRequest, val parcel: Parcel, val email: DeliveryEmail, val preset: NotificationPreset)

/**
 * `ParcelAlertsSheet` over the whole window, where the tab shell draws it: the app's [Sheet] as `fittedSheet`, as tall
 * as what it shows and then the large detent on a phone, a form as tall as what it shows on a tablet, scrolling where
 * that is more than the window. It shows the parcel as the store has it now, so a switch shows the server's answer as
 * it arrives, and only while the parcel, the account's email and its preferences all are; once the bell would no
 * longer open it (the parcel arrived, the email switched off elsewhere), it closes itself. While a switch saves, nothing
 * sends it away; why a save failed shows over it (the sheet's own `.alert`), its Close ending the alert only.
 */
@Composable
internal fun ParcelAlertsHost(state: DeliveriesState) {
    val request = state.alerts
    val parcels by state.source.parcels.collectAsState()
    val deliveryEmail by state.source.deliveryEmail.collectAsState()
    val preferences by state.source.notificationPreferences.collectAsState()
    val now = request?.takeIf { it.pageId == state.navigator.navigation.shown }?.let { asked ->
        val parcel = state.menuParcel(asked.pageId, parcels)
        val email = deliveryEmail
        val chosen = preferences
        if (parcel != null && email != null && chosen != null && email.bellOpensAlerts(parcel)) {
            AlertsShown(asked, parcel, email, NotificationPreset.matching(chosen.enabledStages))
        } else {
            null
        }
    }
    val current = now != null
    // Whether the sheet has come up, so that alerts closed before it did leave nothing to wait for.
    var up by remember { mutableStateOf(false) }
    if (current) up = true
    // `.onChange(of: current, initial: true) { if !current { dismiss() } }`
    LaunchedEffect(request?.id, current) {
        if (request == null || current) return@LaunchedEffect
        state.closeAlerts()
        if (!up) state.alertsClosed()
    }
    val work = state.source.pageWork
    val working = request != null && work.working(request.id)
    // A save that ends before the sheet is composed again is answered too.
    LaunchedEffect(request?.id, request?.saving, working) {
        if (!working) state.alertSaved()
    }
    var kept by remember { mutableStateOf<AlertsShown?>(null) }
    if (now != null) kept = now
    val shown = kept
    Sheet(
        visible = current,
        onDismiss = { state.closeAlerts() },
        paneTitle = localized("email.parcel.open"),
        detents = listOf(SheetDetent.Fitted, SheetDetent.Large),
        onClosed = {
            up = false
            kept = null
            state.alertsClosed()
        },
        dismissible = shown?.request?.saving == null,
    ) {
        if (shown != null) {
            val id = shown.request.id
            val failure = work.failure(id)
            val menus = LocalSheetMenus.current
            DisposableEffect(menus, failure, id) {
                menus?.content = failure?.let { message -> { ErrorAlert(message) { work.dismiss(id) } } }
                onDispose { menus?.content = null }
            }
            ParcelAlertSwitches(
                shown.parcel, shown.email, shown.preset, shown.request.saving, closing = shown.request.closing,
                flip = { alert, on -> state.flipAlert(alert, on, shown.parcel) }, close = { state.doneWithAlerts() },
            )
        }
    }
}

/**
 * `ParcelAlertSwitches` on Brand's background, 22 in from the sides, 26 under the top and 18 over the foot, 560 wide
 * at most and 14 between its parts: the parcel's name as the heading, "this parcel" where it has none, with the close
 * button; the two switches 7 apart, each for this parcel only (`flip`), the notifications with the account's preset
 * and the email with its address; the note saying where the defaults are, in caption secondary; and Done. A switch
 * shows the position it was flipped to while it saves (`saving`), and all are disabled meanwhile, one save at a time.
 * Done, and the close button, end the sheet (`close`); while a switch still saves, Done shows a spinner and the sheet
 * closes once it is saved (`closing`), so a failure is never missed.
 */
@Composable
private fun ParcelAlertSwitches(
    parcel: Parcel,
    email: DeliveryEmail,
    preset: NotificationPreset,
    saving: AlertSave?,
    closing: Boolean,
    flip: (ParcelAlert, Boolean) -> Unit,
    close: () -> Unit,
) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Box(Modifier.fillMaxSize().background(colors.background).verticalScroll(rememberScrollState())) {
        Column(
            Modifier
                .testTag(PARCEL_ALERTS)
                .fillMaxWidth()
                .wrapContentWidth()
                .widthIn(max = ALERTS_WIDTH)
                .fillMaxWidth()
                .padding(start = ALERTS_SIDE, top = ALERTS_TOP, end = ALERTS_SIDE, bottom = ALERTS_BOTTOM),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            AlertsHeader(parcel, close)
            Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                fun shownOn(alert: ParcelAlert, saved: Boolean) = if (saving?.alert == alert) saving.on else saved
                SwitchRow(
                    PARCEL_ALERTS_NOTIFICATIONS, "bell", localizer.text("email.parcel.notifications"),
                    localizer.text("email.parcel.notificationsBody", mapOf("preset" to localizer.text(preset.titleKey))),
                    on = shownOn(ParcelAlert.Notifications, !parcel.notificationsMuted), enabled = saving == null,
                ) { flip(ParcelAlert.Notifications, it) }
                SwitchRow(
                    PARCEL_ALERTS_EMAIL, "envelope", localizer.text("email.parcel.email"),
                    localizer.text("email.parcel.emailBody", mapOf("email" to email.address)),
                    on = shownOn(ParcelAlert.Email, !parcel.isEmailMuted), enabled = saving == null,
                ) { flip(ParcelAlert.Email, it) }
            }
            Text(
                localizer.text("email.parcel.note", mapOf("place" to localizer.deliveryUpdatesPlace)),
                Modifier.padding(horizontal = 2.dp),
                color = colors.secondaryLabel,
                style = type.caption.sfLines(),
            )
            DoneButton(closing, close)
        }
    }
}

/**
 * The sheet's heading, "email.parcel.title" with the parcel's name or "email.parcel.open", title2 semibold across what
 * the close button leaves it, 16 from it: an ✕ of 13 semibold in a circle of the ink at 7 %, 36 across in 44, a plain
 * button named "common.close".
 */
@Composable
private fun AlertsHeader(parcel: Parcel, close: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    // A parcel without a name is "this parcel", as on its bell.
    val title = parcel.label.ifEmpty { null }?.let { localizer.text("email.parcel.title", mapOf("name" to it)) }
        ?: localizer.text("email.parcel.open")
    val closeLabel = localizer.text("common.close")
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            title,
            Modifier.weight(1f).testTag(PARCEL_ALERTS_TITLE).semantics { heading() },
            color = colors.label,
            style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(),
        )
        Box(
            Modifier
                .size(44.dp)
                .testTag(PARCEL_ALERTS_CLOSE)
                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = close)
                .semantics { contentDescription = closeLabel },
            contentAlignment = Alignment.Center,
        ) {
            Box(Modifier.size(36.dp).background(colors.ink.copy(alpha = colors.ink.alpha * 0.07f), CircleShape), contentAlignment = Alignment.Center) {
                PeekSymbol("xmark", font = type.fixed(13f, FontWeight.SemiBold), tint = colors.label)
            }
        }
    }
}

/**
 * A switch of the sheet (`switchRow`): the symbol, 16 in the secondary grey 22 wide (none at the accessibility sizes,
 * where the words need the room), 12 from the title in subheadline semibold over what it does in caption secondary,
 * and the switch at the trailing end, in the delivered green while on; 14 and 12 in on the paper, 60 tall at least,
 * with 14-point continuous corners. A tap on the switch flips it (`flip`); TalkBack reads the row as one switch, its
 * words and its state, as iOS's `Toggle` with its label.
 */
@Composable
private fun SwitchRow(tag: String, symbol: String, title: String, hint: String, on: Boolean, enabled: Boolean, flip: (Boolean) -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(tag)
            .semantics(mergeDescendants = true) {
                role = Role.Switch
                toggleableState = ToggleableState(on)
                onClick {
                    if (enabled) flip(!on)
                    enabled
                }
                if (!enabled) disabled()
            }
            .heightIn(min = 60.dp)
            .background(colors.paper, ContinuousRoundedShape(14.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (!large) {
            Box(Modifier.size(width = 22.dp, height = 22.dp).wrapContentSize(unbounded = true), contentAlignment = Alignment.Center) {
                PeekSymbol(symbol, font = type.fixed(16f), tint = colors.secondaryLabel)
            }
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(title, color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
            Text(hint, color = colors.secondaryLabel, style = type.caption.sfLines())
        }
        Toggle(on, flip, Modifier.clearAndSetSemantics {}, enabled = enabled, tint = colors.resolve(ExperimentalPalette.delivered))
    }
}

/**
 * Done (`doneButton`): "native.done" in headline on the accent, across the sheet, 50 tall at least with 16-point
 * continuous corners, giving under the finger; while it waits for a switch to be saved, a spinner 9 before its words.
 */
@Composable
private fun DoneButton(closing: Boolean, close: () -> Unit) {
    val colors = PeekTheme.colors
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(PARCEL_ALERTS_DONE)
            .clickable(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button, onClick = close)
            .background(colors.accent, ContinuousRoundedShape(16.dp))
            .heightIn(min = 50.dp)
            .padding(horizontal = 20.dp, vertical = 13.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (closing) ActivityIndicator(size = REGULAR_SPINNER, tint = colors.onAccent)
        Text(localized("native.done"), color = colors.onAccent, style = PeekTheme.type.headline.sfLines(), textAlign = TextAlign.Center)
    }
}

private val ALERTS_WIDTH = 560.dp
private val ALERTS_SIDE = 22.dp
private val ALERTS_TOP = 26.dp
private val ALERTS_BOTTOM = 18.dp

/** `ProgressView()` at its regular size, 20 across. */
internal val REGULAR_SPINNER = 20.dp

internal const val PARCEL_ALERTS = "parcelAlerts"
internal const val PARCEL_ALERTS_TITLE = "parcelAlerts.title"
internal const val PARCEL_ALERTS_CLOSE = "parcelAlerts.close"
internal const val PARCEL_ALERTS_NOTIFICATIONS = "parcelAlerts.notifications"
internal const val PARCEL_ALERTS_EMAIL = "parcelAlerts.email"
internal const val PARCEL_ALERTS_DONE = "parcelAlerts.done"
