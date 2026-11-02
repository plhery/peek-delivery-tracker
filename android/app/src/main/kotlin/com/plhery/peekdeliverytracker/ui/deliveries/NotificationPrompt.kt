package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.stroke
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import java.util.UUID

/**
 * `NotificationPromptView` (C-S34): the invitation to turn notifications on, on the paper with 22-point corners, a line
 * of the ink at 10 % round it and a shadow (black at 10 %, radius 16, 4 down), in by 16, its parts 12 apart. The bell on
 * the accent's 44-point tile (but at the accessibility sizes), 12 from the title in headline, read as a heading
 * (C-AX07), and the description in subheadline 4 under it, the ink at half; why turning them on failed in caption, in
 * the warning's colour; then Turn on and Not now side by side 8 apart where both fit as they would lay themselves out,
 * else stacked 4 apart (`ViewThatFits`).
 *
 * Turn on (C-ST20) asks the store (`enableNotifications`, which counts `notifications-enable` and asks the phone),
 * once at a time: the spinner and "Turning on…" while the store works, both buttons at half and still meanwhile, and
 * `notifications.error.enable` once it failed, for whatever reason, the phone's refusal included, which also sends the
 * invitation away. The work runs in the app's scope ([PageWork]), so the window made again (a turn, a fold, dark mode,
 * a resize) finds it, and its failure, as they were; the failure goes with the card otherwise. Not now counts
 * `notifications-defer` and sends the invitation away for this account (C-SE09). A card of its own for each account
 * (`.id(session.user?.id)`).
 */
@Composable
internal fun NotificationPrompt(state: DeliveriesState, modifier: Modifier = Modifier) {
    val source = state.source
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val enabling by source.notificationEnableInProgress.collectAsState()
    val work = source.pageWork
    val error = work.failure(PROMPT_WORK)
    val window = LocalActivity.current
    // The failure goes with the card, but not as the window is made again, which the iPhone's `@State` lives through.
    DisposableEffect(work) {
        onDispose { if (window?.isChangingConfigurations != true) work.dismiss(PROMPT_WORK) }
    }
    val shape = ContinuousRoundedShape(PROMPT_RADIUS)
    Column(
        modifier
            .testTag(NOTIFICATIONS_PROMPT)
            .dropShadow(shape, PROMPT_SHADOW)
            .background(colors.paper, shape)
            .stroke(1.dp, colors.ink.copy(alpha = 0.1f), shape)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            if (!PeekTheme.dynamicTypeSize.isAccessibilitySize) {
                Box(
                    Modifier.testTag(NOTIFICATIONS_PROMPT_BELL).size(44.dp).background(colors.accent, ContinuousRoundedShape(TILE_RADIUS)),
                    contentAlignment = Alignment.Center,
                ) {
                    PeekSymbol("bell.badge.fill", font = type.fixed(20f, FontWeight.SemiBold), tint = colors.onAccent)
                }
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    localized("notifications.prompt.title"),
                    Modifier.semantics { heading() },
                    color = colors.ink,
                    style = type.headline.sfLines(),
                )
                Text(localized("notifications.prompt.description"), color = colors.ink.copy(alpha = SECONDARY), style = type.subheadline.sfLines())
            }
        }
        if (error != null) {
            Text(error, Modifier.testTag(NOTIFICATIONS_PROMPT_ERROR), color = colors.warning, style = type.caption.sfLines())
        }
        val enable: @Composable (Modifier) -> Unit = { fill ->
            Row(
                fill
                    .testTag(NOTIFICATIONS_PROMPT_ENABLE)
                    .plainDisabled(!enabling)
                    .clickable(interactionSource = null, indication = PlainPress, enabled = !enabling, role = Role.Button) {
                        work.run(PROMPT_WORK, source.scope, { localizer.text("notifications.error.enable") }) { source.enableNotifications() }
                    }
                    .background(colors.accent, ContinuousRoundedShape(BUTTON_RADIUS))
                    .heightIn(min = 44.dp)
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (enabling) ActivityIndicator(size = REGULAR_SPINNER, tint = colors.onAccent)
                Text(
                    localized(if (enabling) "notifications.enabling" else "notifications.enable"),
                    color = colors.onAccent,
                    style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines(),
                    textAlign = TextAlign.Center,
                )
            }
        }
        val dismiss: @Composable () -> Unit = {
            Box(
                Modifier
                    .testTag(NOTIFICATIONS_PROMPT_DISMISS)
                    .plainDisabled(!enabling)
                    .clickable(interactionSource = null, indication = PlainPress, enabled = !enabling, role = Role.Button) {
                        source.action("notifications-defer")
                        source.dismissNotificationInvitation()
                    }
                    .heightIn(min = 44.dp)
                    .padding(horizontal = 10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    localized("onboarding.notifications.notNow"),
                    color = colors.ink,
                    style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines(),
                    textAlign = TextAlign.Center,
                )
            }
        }
        FirstThatFits(
            Modifier.fillMaxWidth(),
            wide = {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    enable(Modifier.weight(1f))
                    dismiss()
                }
            },
            narrow = {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    enable(Modifier.fillMaxWidth())
                    dismiss()
                }
            },
        )
    }
}

/** The card's work, one at a time: turning notifications on. */
private val PROMPT_WORK: UUID = UUID.nameUUIDFromBytes("notifications.prompt".toByteArray())
private val PROMPT_RADIUS = 22.dp
private val TILE_RADIUS = 14.dp
private val BUTTON_RADIUS = 12.dp
private val PROMPT_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.1f), radius = 16.dp, y = 4.dp)

/** `.secondary` over the ink: the ink at half. */
private const val SECONDARY = 0.5f

internal const val NOTIFICATIONS_PROMPT = "notifications.prompt"
/** The bell's tile, which iOS gives no identifier: a hook for the tests alone, named as no copy key is. */
internal const val NOTIFICATIONS_PROMPT_BELL = "notificationsPromptBell"
internal const val NOTIFICATIONS_PROMPT_ERROR = "notifications.prompt.error"
internal const val NOTIFICATIONS_PROMPT_ENABLE = "notifications.prompt.enable"
internal const val NOTIFICATIONS_PROMPT_DISMISS = "notifications.prompt.dismiss"
