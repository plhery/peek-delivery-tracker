package com.plhery.peekdeliverytracker.ui.settings

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import com.plhery.peekdeliverytracker.ui.draw.Toggle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.DeliveryEmail
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import com.plhery.peekdeliverytracker.core.NotificationPreferencesDraft
import com.plhery.peekdeliverytracker.core.NotificationPreset
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.REGULAR_SPINNER
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.util.TimeZone
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.launch

// SettingsViews.swift's `NotificationSettingsView(embedded: true)` (C-S35) and `DeliveryEmailSettings` (C-S36, C-S37):
// Delivery updates, pushed in Settings for an account.

/**
 * `NotificationSettingsView(embedded: true)`, "settings.deliveryUpdates" inline in the bar: what Android says of the
 * app's notifications, with "Open Android settings" where they are blocked (but in the demo), else the device's
 * switch; the stage presets, once the account's preferences are loaded, with Save; what was saved or failed; the
 * delivery email; and when updates come. Nothing on it takes a tap while it saves, or while the email switch waits for
 * its answer. It loads the notifications' state as it opens (`.task`), and again as the app comes back from Android's
 * settings.
 */
@Composable
internal fun DeliveryUpdatesPage(state: AccountHost, scroll: ScrollState, onBack: () -> Unit) {
    val source = state.source
    val work = source.pageWork
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val status by source.notificationStatus.collectAsState()
    val enabledOnDevice by source.notificationsEnabledOnDevice.collectAsState()
    val preferences by source.notificationPreferences.collectAsState()
    val storeError by source.notificationError.collectAsState()
    val email by source.deliveryEmail.collectAsState()
    val working = work.working(DELIVERY_UPDATES_PAGE)
    val failure = work.failure(DELIVERY_UPDATES_PAGE)
    var preset by rememberSaveable { mutableStateOf(NotificationPreferencesDraft(preferences).preset) }
    var emailOnDelivery by remember { mutableStateOf(preferences?.emailOnDelivery) }
    var notice by rememberSaveable { mutableStateOf<String?>(null) }
    // Held for the page in the app's scope, as its runs are: a window made again mid-save still waits for the answer.
    val emailChoice = work.emailChoice(DELIVERY_UPDATES_PAGE)
    val emailFailed = work.emailFailed(DELIVERY_UPDATES_PAGE)
    var seen by remember { mutableStateOf(preferences) }
    LaunchedEffect(Unit) {
        source.refreshNotificationState()
        source.loadNotificationPreferences()
    }
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) { source.scope.launch { source.refreshNotificationState() } }
    // `.onChange(of: store.notificationPreferences)`: the email switch saves on its own, so a preset picked but not
    // saved yet stays picked; the choice changed after all, here or from a link in an email, makes the failure old news.
    LaunchedEffect(preferences) {
        val old = seen
        val new = preferences
        if (old == new) return@LaunchedEffect
        if (old != null && new != null && old.enabledStages == new.enabledStages) {
            emailOnDelivery = new.emailOnDelivery
        } else if (new != null) {
            preset = NotificationPreferencesDraft(new).preset
            emailOnDelivery = new.emailOnDelivery
        }
        if (old?.emailOnDelivery != new?.emailOnDelivery) work.emailFailureSeen(DELIVERY_UPDATES_PAGE)
        seen = new
    }
    val run = { job: suspend () -> Unit -> work.run(DELIVERY_UPDATES_PAGE, source.scope, source::errorMessage, null, job) }
    val setEmail = { enabled: Boolean ->
        if (emailChoice == null) {
            notice = null
            work.chooseEmail(DELIVERY_UPDATES_PAGE, enabled, source.scope, failed = { state.announce(localizer.text("email.setting.failed")) }) {
                try {
                    source.setEmailOnDelivery(enabled)
                    source.action("email-delivery-change", DeliveryAnalytics.Outcome.Success)
                } catch (error: Exception) {
                    if (error !is CancellationException) source.action("email-delivery-change", DeliveryAnalytics.Outcome.Error)
                    throw error
                }
            }
        }
    }
    PushedPage(localized("settings.deliveryUpdates"), scroll, backEnabled = true, onBack = onBack, enabled = !working && emailChoice == null) {
        SettingsGroup {
            Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(systemStatusText(status, enabledOnDevice), color = colors.secondaryLabel, style = type.subheadline.sfLines())
                if (status == NotificationAuthorizationStatus.Denied && !source.isDemo) {
                    Row(
                        Modifier
                            .heightIn(min = 44.dp)
                            .testTag(SETTINGS_OPEN_SETTINGS)
                            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button) { openNotificationSettings(context) }
                            .semantics(mergeDescendants = true) {},
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        PeekSymbol("gear", font = type.body, tint = colors.ink)
                        Text(localized("native.openSettings"), color = colors.ink, style = type.body.sfLines())
                    }
                } else {
                    DeviceSwitch(enabledOnDevice) { on ->
                        run {
                            notice = if (on) {
                                if (source.enableNotifications()) null else localizer.text("notifications.error.welcome")
                            } else {
                                source.disableNotifications()
                                null
                            }
                        }
                    }
                }
            }
        }
        if (preferences != null) {
            SettingsGroup(localized("notifications.preferencesTitle")) {
                NotificationPreset.entries.forEachIndexed { index, option ->
                    if (index > 0) SettingsDivider()
                    PresetRow(option, option == preset) {
                        preset = option
                        notice = null
                    }
                }
            }
            SaveButton(working) {
                val value = NotificationPreferencesDraft(preset, emailOnDelivery).preferences(TimeZone.getDefault().id)
                run {
                    source.saveNotificationPreferences(value)
                    notice = localizer.text("notifications.saved")
                }
            }
        }
        notice?.let { Text(it, Modifier.testTag(SETTINGS_NOTICE), color = colors.resolve(settingsGreen), style = type.caption.sfLines()) }
        (failure ?: storeError)?.let { Text(it, Modifier.testTag(SETTINGS_UPDATES_ERROR), color = colors.red, style = type.caption.sfLines()) }
        email?.let { shown ->
            val example = DeliveryEmail.exampleUrl(source.linkBaseUrl, language)
            DeliveryEmailSettings(
                address = shown.address,
                isOn = emailChoice ?: shown.isOn,
                onChange = setEmail,
                saving = emailChoice != null,
                failed = emailFailed,
                onPrivacy = { uriHandler.openUri(source.privacyUrl) },
                onExample = example?.let {
                    {
                        source.action("email-example-open")
                        uriHandler.openUri(it)
                    }
                },
            )
        }
        Text(localized("notifications.schedule"), color = colors.secondaryLabel, style = type.caption.sfLines())
    }
}

/** `systemStatusText`: on, blocked, waiting for a choice, or still being read. */
@Composable
private fun systemStatusText(status: NotificationAuthorizationStatus, enabledOnDevice: Boolean): String = localized(
    when {
        enabledOnDevice -> "notifications.state.enabled"
        status == NotificationAuthorizationStatus.Denied -> "notifications.state.blocked"
        else -> "notifications.state.prompt"
    },
)

/** The device's `Toggle("settings.deliveryUpdates")`: its words in body beside the switch in [settingsGreen]. */
@Composable
private fun DeviceSwitch(on: Boolean, onChange: (Boolean) -> Unit) {
    val colors = PeekTheme.colors
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(SETTINGS_DEVICE_SWITCH)
            .semantics(mergeDescendants = true) {
                role = Role.Switch
                toggleableState = ToggleableState(on)
                onClick {
                    onChange(!on)
                    true
                }
            },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(localized("settings.deliveryUpdates"), Modifier.weight(1f), color = colors.label, style = PeekTheme.type.body.sfLines())
        Toggle(on, onChange, Modifier.clearAndSetSemantics {}, tint = colors.resolve(settingsGreen))
    }
}

/**
 * A preset's row: the radio, filled in [settingsGreen] where chosen, else an empty circle in the secondary grey, 16
 * regular, 2 down, 12 from its title in subheadline over its description in caption secondary; 15 in. TalkBack reads
 * it as one, the chosen one selected.
 */
@Composable
private fun PresetRow(option: NotificationPreset, chosen: Boolean, onChoose: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(presetTag(option))
            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onChoose)
            .semantics(mergeDescendants = true) { selected = chosen }
            .padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        PeekSymbol(
            if (chosen) "largecircle.fill.circle" else "circle", Modifier.padding(top = 2.dp),
            font = type.fixed(16f), tint = if (chosen) colors.resolve(settingsGreen) else colors.secondaryLabel,
        )
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(localized(option.titleKey), color = colors.label, style = type.subheadline.sfLines())
            Text(localized(option.descriptionKey), color = colors.secondaryLabel, style = type.caption.sfLines())
        }
    }
}

/** Save: "notifications.save", or a spinner and "notifications.saving" while it saves, subheadline medium, on the accent. */
@Composable
private fun SaveButton(saving: Boolean, onSave: () -> Unit) {
    val colors = PeekTheme.colors
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(SETTINGS_SAVE)
            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onSave)
            .background(colors.accent, RoundedCornerShape(14.dp))
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (saving) ActivityIndicator(Modifier.clearAndSetSemantics {}, size = REGULAR_SPINNER, tint = colors.onAccent)
        Text(
            localized(if (saving) "notifications.saving" else "notifications.save"), color = colors.onAccent,
            style = PeekTheme.type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines(),
        )
    }
}

/**
 * `DeliveryEmailSettings`: "email.section" with its "email.new" badge over the switch for the account's delivery email,
 * which waits for the server's answer before it can be flipped again ([saving]); why it failed in caption red; then
 * the quiet links, the example email (where there is a page for it) and the privacy notice, in caption secondary
 * underlined in the tertiary grey, each 44 tall under the finger without taking that room, side by side 20 apart or
 * one under the other where they don't fit.
 */
@Composable
private fun DeliveryEmailSettings(
    address: String,
    isOn: Boolean,
    onChange: (Boolean) -> Unit,
    saving: Boolean,
    failed: Boolean,
    onPrivacy: () -> Unit,
    onExample: (() -> Unit)?,
) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
        SettingsGroup(localized("email.section"), badge = localized("email.new")) {
            SettingsToggleRow(
                isOn, onChange, localized("email.setting.title"), SETTINGS_DELIVERY_EMAIL,
                symbol = "envelope", detail = localizer.text("email.setting.body", mapOf("email" to address)), enabled = !saving,
            )
        }
        if (failed) {
            Text(
                localized("email.setting.failed"), Modifier.testTag(SETTINGS_DELIVERY_EMAIL_ERROR),
                color = colors.red, style = PeekTheme.type.caption.sfLines(),
            )
        }
        val links = buildList<@Composable () -> Unit> {
            if (onExample != null) add { QuietLink("email.setting.example", SETTINGS_DELIVERY_EMAIL_EXAMPLE, onExample) }
            add { QuietLink("auth.privacyLink", SETTINGS_DELIVERY_EMAIL_PRIVACY, onPrivacy) }
        }
        // `ViewThatFits(in: .horizontal)`: side by side where both fit, else one under the other; -10 above and below
        // so the links' room under the finger takes none of the page's.
        Layout(
            content = { links.forEach { Box { it() } } },
            modifier = Modifier.padding(start = 2.dp),
        ) { measurables, constraints ->
            val loose = constraints.copy(minWidth = 0, minHeight = 0)
            val placeables = measurables.map { it.measure(loose) }
            val gap = 20.dp.roundToPx()
            val trim = 10.dp.roundToPx()
            val across = placeables.sumOf { it.width } + gap * (placeables.size - 1) <= constraints.maxWidth
            if (across) {
                val height = (placeables.maxOf { it.height } - 2 * trim).coerceAtLeast(0)
                layout(constraints.maxWidth, height) {
                    var x = 0
                    placeables.forEach {
                        it.place(x, -trim)
                        x += it.width + gap
                    }
                }
            } else {
                val height = (placeables.sumOf { it.height } - 2 * trim).coerceAtLeast(0)
                layout(constraints.maxWidth, height) {
                    var y = -trim
                    placeables.forEach {
                        it.place(0, y)
                        y += it.height
                    }
                }
            }
        }
    }
}

/** `quietLink(_:)`: caption secondary, underlined, 44 tall at least. */
@Composable
private fun QuietLink(key: String, tag: String, onClick: () -> Unit) {
    val colors = PeekTheme.colors
    Box(
        Modifier
            .heightIn(min = 44.dp)
            .testTag(tag)
            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            localized(key), color = colors.secondaryLabel,
            style = PeekTheme.type.caption.sfLines().copy(textDecoration = TextDecoration.Underline),
        )
    }
}

/**
 * "Open Settings": the app's notification settings in Android's settings (`openSettingsURLString`), or the app's own
 * page where a phone has no such screen.
 */
private fun openNotificationSettings(context: Context) {
    val notifications = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
    try {
        context.startActivity(notifications)
    } catch (_: ActivityNotFoundException) {
        try {
            context.startActivity(
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, android.net.Uri.fromParts("package", context.packageName, null)),
            )
        } catch (_: ActivityNotFoundException) {
            // Nowhere to send the reader: nothing opens.
        }
    }
}

internal fun presetTag(option: NotificationPreset) = "$SETTINGS_SHEET.preset.${option.rawValue}"

internal const val SETTINGS_OPEN_SETTINGS = "$SETTINGS_SHEET.openSettings"
internal const val SETTINGS_DEVICE_SWITCH = "$SETTINGS_SHEET.deviceNotifications"
internal const val SETTINGS_SAVE = "$SETTINGS_SHEET.save"
internal const val SETTINGS_NOTICE = "$SETTINGS_SHEET.notice"
internal const val SETTINGS_UPDATES_ERROR = "$SETTINGS_SHEET.deliveryUpdates.error"
internal const val SETTINGS_DELIVERY_EMAIL = "$SETTINGS_SHEET.deliveryEmail"
internal const val SETTINGS_DELIVERY_EMAIL_ERROR = "$SETTINGS_SHEET.deliveryEmail.error"
internal const val SETTINGS_DELIVERY_EMAIL_EXAMPLE = "$SETTINGS_SHEET.deliveryEmail.example"
internal const val SETTINGS_DELIVERY_EMAIL_PRIVACY = "$SETTINGS_SHEET.deliveryEmail.privacy"
