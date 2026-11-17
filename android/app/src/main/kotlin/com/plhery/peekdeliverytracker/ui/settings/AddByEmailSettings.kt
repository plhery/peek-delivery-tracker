package com.plhery.peekdeliverytracker.ui.settings

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.text.selection.SelectionContainer
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.collapse
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.expand
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.EmailFilter
import com.plhery.peekdeliverytracker.core.MailService
import com.plhery.peekdeliverytracker.core.StepPiece
import com.plhery.peekdeliverytracker.core.StepText
import com.plhery.peekdeliverytracker.core.api.AddByEmail
import com.plhery.peekdeliverytracker.core.api.AddByEmailEntry
import com.plhery.peekdeliverytracker.core.api.AddByEmailResponse
import com.plhery.peekdeliverytracker.core.api.AddByEmailResult
import com.plhery.peekdeliverytracker.core.api.ForwardingConfirmation
import com.plhery.peekdeliverytracker.core.notifies
import com.plhery.peekdeliverytracker.core.recentEmails
import com.plhery.peekdeliverytracker.core.resultText
import com.plhery.peekdeliverytracker.core.showsUsage
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.deliveries.FirstParcelPrimaryButton
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.REGULAR_SPINNER
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.stroke
import com.plhery.peekdeliverytracker.ui.sheet.BAR_ROW
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.rememberHaptics
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.util.UUID
import kotlin.math.max

// AddByEmailView.swift's `AddByEmailPage` and the web's AddByEmail.tsx: Settings' "Add parcels by email", which the
// server offers some accounts only.

/**
 * The forwarding address's page, pushed in Settings for [account]: the "Experimental" badge over the intro in
 * subheadline secondary, then "inbox.start" while the account has no address yet. Forwarding one email by hand is all
 * it asks then: the address to copy or share, and what the last emails did, with what a few of them call for; then
 * whether each parcel an email adds is notified, on unless switched off; having the mail service forward by itself,
 * with its steps ([ForwardingSteps]), stays folded away until wanted, or until Gmail's confirmation is waiting; last, a
 * new address, which asks first, and the address turned off. What it asks runs in the app's scope ([ADD_BY_EMAIL_PAGE]);
 * while it waits, a spinner stands at the bar's trailing end and nothing on the page, the back or the sheet's close can
 * be pressed. Why it failed shows under the intro, and TalkBack says it.
 */
@Composable
internal fun AddByEmailPage(state: AccountHost, account: UUID, scroll: ScrollState, onBack: () -> Unit) {
    val source = state.source
    val store = source.addByEmail
    val work = source.pageWork
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val context = LocalContext.current
    val haptics = rememberHaptics()
    val stored by store.offerFlow.collectAsState()
    val session by source.session.collectAsState()
    val offer = stored?.takeIf { it.account == account }?.value
    val inbox = offer?.inbox
    val email = (session as? AuthenticationState.SignedIn)?.user?.email
    val working = work.working(ADD_BY_EMAIL_PAGE)
    val failure = work.failure(ADD_BY_EMAIL_PAGE)
    val run = { job: suspend () -> Unit ->
        work.run(ADD_BY_EMAIL_PAGE, source.scope, { localizer.errorMessage(it, fallback = "inbox.failed") }, null, job)
    }
    val copy = { text: String ->
        context.getSystemService(ClipboardManager::class.java)?.setPrimaryClip(ClipData.newPlainText(text, text))
        haptics.success()
        state.announce(localizer.text("inbox.copied"))
    }
    PushedPage(
        localized("inbox.row"), scroll, backEnabled = !working, onBack = onBack,
        trailing = if (!working) null else {
            { GlassSurface(CapsuleShape, Modifier.size(BAR_ROW), backdrop = null, behind = colors.sheet) {
                ActivityIndicator(Modifier.align(Alignment.Center).testTag(ADD_BY_EMAIL_WORKING), size = REGULAR_SPINNER)
            } }
        },
        enabled = !working,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            SettingsBadge(localized("inbox.experimental"))
            Text(localized("inbox.intro"), color = colors.secondaryText, style = type.subheadline.sfLines())
        }
        if (failure != null) {
            Text(
                failure, Modifier.testTag(ADD_BY_EMAIL_ERROR).semantics { liveRegion = LiveRegionMode.Polite },
                color = colors.warning, style = type.subheadline.sfLines(),
            )
        }
        if (offer != null && inbox == null) {
            val focus = remember { AccessibilityFocus() }
            FirstParcelPrimaryButton(localized("inbox.start"), "envelope", false, ADD_BY_EMAIL_START, focus, Modifier.fillMaxWidth()) {
                run { store.open(account) }
            }
        } else if (offer != null && inbox != null) {
            // What was copied and the renewal asked about are the address's: a new one starts afresh.
            var copied by rememberSaveable(inbox.address) { mutableStateOf(false) }
            var confirmingRenew by rememberSaveable(inbox.address) { mutableStateOf(false) }
            AddressGroup(inbox.address, copied) {
                copy(inbox.address)
                copied = true
            }
            RecentEmails(inbox, offer)
            AutomaticForwarding(inbox, email, copy)
            SettingsGroup {
                if (confirmingRenew) {
                    RenewConfirmation(onCancel = { confirmingRenew = false }) { run { store.open(account, renew = true) } }
                } else {
                    SettingsButtonRow(localized("inbox.renew"), ADD_BY_EMAIL_RENEW, { confirmingRenew = true }, symbol = "arrow.clockwise")
                }
                SettingsDivider()
                // One line just above turning it off. The switch shows what the server last answered: it moves once the
                // change is saved, and stays on failure.
                SettingsToggleRow(
                    inbox.notifies, { on -> run { store.setNotify(account, on) } }, localized("inbox.notify"), ADD_BY_EMAIL_NOTIFY,
                    symbol = "bell", enabled = !working,
                )
                SettingsDivider()
                SettingsButtonRow(localized("inbox.off"), ADD_BY_EMAIL_OFF, { run { store.close(account) } }, symbol = "trash")
            }
        }
    }
}

/** "inbox.address": the address in body medium, which can be selected, then its copy and its share rows. */
@Composable
private fun AddressGroup(address: String, copied: Boolean, onCopy: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val context = LocalContext.current
    SettingsGroup(localized("inbox.address")) {
        SelectionContainer {
            Text(
                address, Modifier.fillMaxWidth().padding(ROW_PADDING).testTag(ADD_BY_EMAIL_ADDRESS),
                color = colors.ink, style = type.style(IosTextStyle.Body, FontWeight.Medium).sfLines(),
            )
        }
        SettingsDivider()
        SettingsButtonRow(
            localized(if (copied) "inbox.copied" else "inbox.copy"), ADD_BY_EMAIL_COPY, onCopy,
            symbol = if (copied) "checkmark" else "doc.on.doc",
        )
        SettingsDivider()
        SettingsButtonRow(localized("link.share"), ADD_BY_EMAIL_SHARE, { share(context, address) }, symbol = "square.and.arrow.up")
    }
}

/** `ShareLink(item: address)`: the address through the system's share sheet. */
private fun share(context: Context, text: String) {
    val send = Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, text)
    try {
        context.startActivity(Intent.createChooser(send, null))
    } catch (_: ActivityNotFoundException) {
        // No app to take it: the address stays on screen to copy.
    }
}

/**
 * "inbox.recent": the last emails, each its own row, then 9 under them in caption secondary why some shops' emails add
 * nothing, where one did, and how many emails today took, once half the day's allowance is used.
 */
@Composable
private fun RecentEmails(inbox: AddByEmail, offer: AddByEmailResponse) {
    val localizer = LocalLocalizer.current
    val emails = inbox.recentEmails()
    if (emails.isEmpty()) return
    Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
        SettingsGroup(localized("inbox.recent")) {
            emails.forEachIndexed { index, entry ->
                if (index > 0) SettingsDivider()
                EmailRow(entry)
            }
        }
        if (emails.any { it.result == AddByEmailResult.None }) Note(localized("inbox.noNumber"))
        if (offer.showsUsage) Note(localizer.text("inbox.usage", mapOf("used" to inbox.usedToday, "limit" to offer.dailyLimit)))
    }
}

/**
 * One of the last emails (`AddByEmailRow`): who sent it in subheadline semibold over what it did in caption secondary,
 * and when in caption secondary at the trailing end, on the first line's baseline, or under them at the accessibility
 * sizes. An email that wasn't read has no sender, and what it did stands in its place. TalkBack reads it as one.
 */
@Composable
private fun EmailRow(entry: AddByEmailEntry) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val result = entry.resultText(localizer)
    val time = localizer.relativeTime(from = entry.at)
    val strong = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines()
    val what = @Composable { modifier: Modifier ->
        Column(modifier, verticalArrangement = Arrangement.spacedBy(3.dp)) {
            if (entry.sender.isEmpty()) {
                Text(result, color = colors.ink, style = strong)
            } else {
                Text(entry.sender, color = colors.ink, style = strong)
                Text(result, color = colors.secondaryText, style = type.caption.sfLines())
            }
        }
    }
    val frame = Modifier.fillMaxWidth().semantics(mergeDescendants = true) {}.padding(ROW_PADDING)
    if (PeekTheme.dynamicTypeSize.isAccessibilitySize) {
        Column(frame, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            what(Modifier)
            Text(time, color = colors.secondaryText, style = type.caption.sfLines())
        }
    } else {
        Row(frame, horizontalArrangement = Arrangement.spacedBy(11.dp)) {
            what(Modifier.weight(1f).alignByBaseline())
            Text(time, Modifier.alignByBaseline(), color = colors.secondaryText, style = type.caption.sfLines(), textAlign = TextAlign.End)
        }
    }
}

/** A note under a group: caption secondary, 2 in. */
@Composable
private fun Note(text: String) {
    Text(text, Modifier.padding(start = 2.dp), color = PeekTheme.colors.secondaryText, style = PeekTheme.type.caption.sfLines())
}

/**
 * "inbox.auto.title" in a group of its own, with the gear, and a chevron that turns down as its steps open under it;
 * TalkBack reads it as one button that expands and collapses. It opens by itself while Gmail's confirmation waits,
 * here or on coming back from Gmail.
 */
@Composable
private fun AutomaticForwarding(inbox: AddByEmail, email: String?, copy: (String) -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val waiting = inbox.confirmation != null
    var open by rememberSaveable { mutableStateOf(waiting) }
    LaunchedEffect(waiting) { if (waiting) open = true }
    val turn by animateFloatAsState(if (open) 90f else 0f, if (reduceMotion) Motion.linear(0.0) else Motion.easeOut(0.2), label = "forwarding")
    SettingsGroup {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = ROW_HEIGHT)
                .testTag(ADD_BY_EMAIL_AUTOMATIC)
                .button(interactionSource = null, indication = PlainPress, role = Role.Button) { open = !open }
                .semantics(mergeDescendants = true) {
                    if (open) {
                        collapse {
                            open = false
                            true
                        }
                    } else {
                        expand {
                            open = true
                            true
                        }
                    }
                }
                .padding(ROW_PADDING),
            horizontalArrangement = Arrangement.spacedBy(11.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(Modifier.weight(1f)) { SettingsRow(localized("inbox.auto.title"), symbol = "gear", padded = false) }
            PeekSymbol("chevron.right", Modifier.rotate(turn), font = type.fixed(10f, FontWeight.Light), tint = colors.secondaryText)
        }
        AnimatedVisibility(
            open,
            enter = if (reduceMotion) EnterTransition.None else fadeIn(Motion.easeOut(0.2)) + expandVertically(Motion.easeOut(0.2)),
            exit = if (reduceMotion) ExitTransition.None else fadeOut(Motion.easeOut(0.2)) + shrinkVertically(Motion.easeOut(0.2)),
        ) {
            Column {
                SettingsDivider()
                Box(Modifier.padding(ROW_PADDING)) { ForwardingSteps(email, inbox.confirmation, copy) }
            }
        }
    }
}

/**
 * How to have a mail service forward shipping emails by itself (`ForwardingSteps`), each service's steps in the order
 * it asks for them, numbered, with its own names for its buttons in bold. The reader names their service, which
 * starts on the one their account's address belongs to, or Gmail while its confirmation waits; and the languages their
 * shops and carriers write in, which start with the language Peek shows, the phone's and English, the last one chosen
 * staying so.
 * - Each service's first step links to its settings and says what to check when they don't show what it speaks of.
 * - Gmail forwards only to an address it has confirmed, and then only the emails a filter picks: the address is added
 *   and confirmed first (Gmail's confirmation shows in the step), then the filter is made from a search to copy.
 * - A service whose rule takes words one at a time is given the main ones, each copied by a tap.
 * - Yahoo, whose filters only move emails and whose forwarding takes every email, says so.
 */
@Composable
private fun ForwardingSteps(email: String?, confirmation: ForwardingConfirmation?, copy: (String) -> Unit) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val haptics = rememberHaptics()
    var service by rememberSaveable { mutableStateOf(if (confirmation != null) MailService.Gmail else EmailFilter.mailServiceOf(email)) }
    // The languages chosen, or none until one is: those the reader's language and phone start with.
    var languages by rememberSaveable { mutableStateOf<ArrayList<String>?>(null) }
    var copied by rememberSaveable { mutableStateOf<String?>(null) }
    val chosen = languages ?: remember(language) { EmailFilter.languagesFor(listOf(language.rawValue) + Device.preferredLanguages()) }
    val copying = { text: String ->
        copy(text)
        copied = text
    }
    val toggle = { code: String ->
        // A filter of no language would forward nothing: the last one stays chosen.
        val next = if (code in chosen) chosen - code else EmailFilter.languages.filter { it in chosen || it == code }
        if (next.isNotEmpty()) {
            haptics.selection()
            languages = ArrayList(next)
            copied = null
        }
    }
    val picker = @Composable { LanguagePicker(chosen, toggle) }
    val words = @Composable { SubjectWords(EmailFilter.subjectWords(chosen), copied, picker, copying) }
    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Label(localized("inbox.service.title"))
            FlowRow(Modifier.selectableGroup(), horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                for (choice in MailService.entries) {
                    Choice(choice.serviceName ?: localized("inbox.service.other"), choice == service, "$ADD_BY_EMAIL_TAG.service.${choice.rawValue}", single = true) {
                        service = choice
                        copied = null
                    }
                }
            }
        }
        when (service) {
            MailService.Gmail -> {
                Step(1, "inbox.gmail.settings") { StepLink(EmailFilter.GMAIL_SETTINGS_LINK, "inbox.gmail.settingsLink") }
                Step(2, "inbox.gmail.add") { Missing("inbox.gmail.noButton") }
                Step(3, "inbox.gmail.confirm") { if (confirmation != null) Confirmation(confirmation) }
                Step(4, "inbox.gmail.keepOff")
                Step(5, "inbox.gmail.search") {
                    val search = EmailFilter.gmailFilter(chosen)
                    StepBox(padded = true) {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            picker()
                            GmailSearch(search, copied == search) { copying(search) }
                        }
                    }
                }
                Step(6, "inbox.gmail.filter")
                Step(7, "inbox.gmail.forward")
            }
            MailService.Outlook -> {
                Step(1, "inbox.outlook.rule") {
                    StepLink(EmailFilter.OUTLOOK_RULES_LINK, "inbox.outlook.link")
                    Missing("inbox.outlook.work")
                }
                Step(2, "inbox.outlook.condition") { words() }
                Step(3, "inbox.outlook.action")
            }
            MailService.Icloud -> {
                Step(1, "inbox.icloud.rule") {
                    StepLink(EmailFilter.ICLOUD_MAIL_LINK, "inbox.icloud.link")
                    Missing("inbox.icloud.noMail")
                }
                Step(2, "inbox.icloud.words") { words() }
                Step(3, "inbox.icloud.more")
            }
            MailService.Yahoo -> Text(localizer.text("inbox.yahoo"), color = PeekTheme.colors.ink, style = PeekTheme.type.subheadline.sfLines())
            MailService.Other -> {
                Step(1, "inbox.other.rule") { words() }
                Text(localizer.text("inbox.other.link"), color = PeekTheme.colors.secondaryText, style = PeekTheme.type.caption.sfLines())
            }
        }
    }
}

/** What a choice is asked about: caption medium secondary, a heading for TalkBack. */
@Composable
private fun Label(text: String) {
    Text(text, Modifier.semantics { heading() }, color = PeekTheme.colors.secondaryText, style = PeekTheme.type.style(IosTextStyle.Caption, FontWeight.Medium).sfLines())
}

/** "inbox.filter.languages" over the filter's languages by their names in the language Peek shows, any of them chosen. */
@Composable
private fun LanguagePicker(chosen: List<String>, toggle: (String) -> Unit) {
    val locale = LocalLanguage.current.locale
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Label(localized("inbox.filter.languages"))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            for (code in EmailFilter.languages) {
                Choice(EmailFilter.languageName(code, locale), code in chosen, "$ADD_BY_EMAIL_TAG.language.$code", single = false) { toggle(code) }
            }
        }
    }
}

/**
 * One of a few choices (`AddByEmailChoice`), a mail service or a language: footnote medium in the ink, a checkmark
 * before it once chosen, on the raised ground with a hairline, or green's tint with a line of green once chosen; 36
 * tall, 44 to the touch. TalkBack reads a service as a radio button, a language as a checkbox.
 */
@Composable
private fun Choice(title: String, on: Boolean, tag: String, single: Boolean, onClick: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val green = colors.resolve(settingsGreen)
    val shape = ContinuousRoundedShape(12.dp)
    Row(
        Modifier
            .overhang(TOUCH_OVERHANG)
            .testTag(tag)
            .button(interactionSource = null, indication = PressStyle.tactile(0.96f), role = if (single) Role.RadioButton else Role.Checkbox, onClick = onClick)
            .semantics { if (single) selected = on else toggleableState = ToggleableState(on) }
            .padding(vertical = TOUCH_OVERHANG)
            .heightIn(min = 36.dp)
            .background(if (on) green.copy(alpha = 0.14f) else colors.raised, shape)
            .stroke(if (on) 1.5.dp else 0.5.dp, if (on) green else colors.sheetLine(0.4f), shape)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val style = type.style(IosTextStyle.Footnote, FontWeight.Medium).sfLines()
        if (on) PeekSymbol("checkmark", font = type.style(IosTextStyle.Caption2, FontWeight.Bold), tint = colors.ink)
        Text(title, color = colors.ink, style = style)
    }
}

/**
 * The main words of the chosen languages (`words`), under the language picker, each a chip copied by a tap, which
 * says so; TalkBack enters them as "inbox.words.title". Under them, in caption secondary, that a tap copies, or that it
 * did.
 */
@Composable
private fun SubjectWords(words: List<String>, copied: String?, picker: @Composable () -> Unit, copy: (String) -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val title = localized("inbox.words.title")
    val done = localized("inbox.copied")
    StepBox(padded = true) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            picker()
            FlowRow(
                Modifier.semantics {
                    isTraversalGroup = true
                    contentDescription = title
                },
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                val shape = ContinuousRoundedShape(12.dp)
                for (word in words) {
                    Row(
                        Modifier
                            .overhang(TOUCH_OVERHANG)
                            .testTag("$ADD_BY_EMAIL_TAG.word.$word")
                            .button(interactionSource = null, indication = PressStyle.tactile(0.96f), role = Role.Button) { copy(word) }
                            .semantics(mergeDescendants = true) { if (copied == word) stateDescription = done }
                            .padding(vertical = TOUCH_OVERHANG)
                            .heightIn(min = 36.dp)
                            .background(colors.raised, shape)
                            .stroke(0.5.dp, colors.sheetLine(0.4f), shape)
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(word, color = colors.ink, style = type.style(IosTextStyle.Footnote, FontWeight.Medium).sfLines())
                        PeekSymbol(if (copied == word) "checkmark" else "doc.on.doc", font = type.caption2, tint = colors.secondaryText)
                    }
                }
            }
            Text(localized(if (copied == null) "inbox.words.copy" else "inbox.copied"), color = colors.secondaryText, style = type.caption.sfLines())
        }
    }
}

/** Gmail's search to paste, in footnote monospaced, which can be selected, over its copy row, raised in its own box. */
@Composable
private fun GmailSearch(search: String, copied: Boolean, onCopy: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val shape = ContinuousRoundedShape(12.dp)
    Column(Modifier.fillMaxWidth().background(colors.raised, shape).stroke(0.5.dp, colors.sheetLine(0.25f), shape)) {
        SelectionContainer {
            Text(
                search, Modifier.fillMaxWidth().padding(12.dp).testTag(ADD_BY_EMAIL_FILTER),
                color = colors.ink, style = type.style(IosTextStyle.Footnote, design = FontDesign.Monospaced).sfLines(),
            )
        }
        SettingsDivider()
        SettingsButtonRow(
            localized(if (copied) "inbox.copied" else "inbox.filter.copy"), ADD_BY_EMAIL_FILTER_COPY, onCopy,
            symbol = if (copied) "checkmark" else "doc.on.doc",
        )
    }
}

/** Gmail's confirmation in its step: its link to open, then the code to enter, where Gmail asks for one. */
@Composable
private fun Confirmation(confirmation: ForwardingConfirmation) {
    val localizer = LocalLocalizer.current
    val uriHandler = LocalUriHandler.current
    // Gmail's confirmation link, which the server takes only from a google.com host: opened only over https.
    val link = confirmation.link?.takeIf { it.startsWith("https://") }
    val code = confirmation.code
    if (link == null && code == null) return
    StepBox {
        Column {
            if (link != null) {
                SettingsButtonRow(
                    localized("inbox.confirm.link"), ADD_BY_EMAIL_CONFIRMATION, { runCatching { uriHandler.openUri(link) } },
                    symbol = "envelope", chevron = true,
                )
                if (code != null) SettingsDivider()
            }
            if (code != null) {
                SelectionContainer {
                    Text(
                        localizer.text("inbox.confirm.code", mapOf("code" to code)), Modifier.fillMaxWidth().padding(ROW_PADDING),
                        color = PeekTheme.colors.ink, style = PeekTheme.type.subheadline.sfLines(),
                    )
                }
            }
        }
    }
}

/** A box within a step, on the sheet's ground inside the group's raised one, 12 in where [padded]. */
@Composable
private fun StepBox(padded: Boolean = false, content: @Composable () -> Unit) {
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(12.dp)
    Box(
        Modifier
            .fillMaxWidth()
            .background(colors.sheet, shape)
            .stroke(0.5.dp, colors.sheetLine(0.25f), shape)
            .padding(if (padded) 12.dp else 0.dp),
    ) { content() }
}

/** A numbered step: its number in its small round, 10 from its words, then 10 under them what goes with it. */
@Composable
private fun Step(number: Int, key: String, extra: @Composable () -> Unit = {}) {
    val localizer = LocalLocalizer.current
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        StepNumber(number)
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            StepWords(localizer.text(key, mapOf("options" to StepText.OPTIONS_MARK)))
            extra()
        }
    }
}

/** A step's number in caption semibold, its digits as wide as each other, in its round of the sheet's control, 22 at Large. */
@Composable
private fun StepNumber(number: Int) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val size = type.scaled(22f, IosTextStyle.Caption).dp
    Box(Modifier.sizeIn(minWidth = size, minHeight = size).background(colors.sheetControl, CapsuleShape), contentAlignment = Alignment.Center) {
        Text(
            "$number", color = colors.ink, style = type.style(IosTextStyle.Caption, FontWeight.SemiBold).monospacedDigit().sfLines(),
        )
    }
}

/**
 * A step's words (`StepText`): the mail service's own button and field names in bold, Gmail's options button drawn as
 * its three sliders where the words put it; TalkBack reads the words without their marks.
 */
@Composable
private fun StepWords(text: String, style: TextStyle = PeekTheme.type.subheadline, color: Color = PeekTheme.colors.ink) {
    val pieces = remember(text) { StepText.pieces(text) }
    val spoken = remember(text) { StepText.spoken(text) }
    val annotated = remember(pieces) {
        buildAnnotatedString {
            for (piece in pieces) {
                when (piece) {
                    is StepPiece.Plain -> append(piece.text)
                    is StepPiece.Bold -> withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(piece.text) }
                    StepPiece.Options -> appendInlineContent(OPTIONS_GLYPH, "⚙")
                }
            }
        }
    }
    val glyph = mapOf(
        OPTIONS_GLYPH to InlineTextContent(Placeholder(1.25.em, 1.em, PlaceholderVerticalAlign.TextCenter)) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) { PeekSymbol("slider.horizontal.3", font = style, tint = color) }
        },
    )
    Text(
        annotated, Modifier.fillMaxWidth().semantics { contentDescription = spoken },
        color = color, style = style.sfLines(), inlineContent = glyph,
    )
}

/** What to check when the service doesn't show what the step speaks of: in caption secondary. */
@Composable
private fun Missing(key: String) {
    StepWords(localized(key), PeekTheme.type.caption, PeekTheme.colors.secondaryText)
}

/** A step's link to the mail service's page it speaks of: subheadline semibold underlined, with ↗, 44 tall. */
@Composable
private fun StepLink(url: String, key: String) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val uriHandler = LocalUriHandler.current
    Row(
        Modifier
            .heightIn(min = 44.dp)
            .button(interactionSource = null, indication = PlainPress, role = Role.Button) { runCatching { uriHandler.openUri(url) } }
            .semantics(mergeDescendants = true) {},
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold)
        Text(localized(key), color = colors.ink, style = style.copy(textDecoration = TextDecoration.Underline).sfLines())
        PeekSymbol("arrow.up.right", font = type.style(IosTextStyle.Caption, FontWeight.SemiBold), tint = colors.ink)
    }
}

/**
 * "inbox.renewNote" in subheadline over Cancel and "inbox.renew" (`renewButtons`), side by side where both fit, else one
 * under the other, 8 apart: each 44 tall at least with 12-point corners, Cancel in the ink on the sheet's button, the
 * renewal in the ink on the yellow.
 */
@Composable
private fun RenewConfirmation(onCancel: () -> Unit, onRenew: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(Modifier.fillMaxWidth().padding(ROW_PADDING), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(localized("inbox.renewNote"), color = colors.ink, style = type.subheadline.sfLines())
        SideBySide(8.dp) {
            RenewButton(localized("common.cancel"), ADD_BY_EMAIL_RENEW_CANCEL, colors.sheetButton, colors.ink, onCancel)
            RenewButton(localized("inbox.renew"), ADD_BY_EMAIL_RENEW_CONFIRM, colors.accent, colors.onAccent, onRenew)
        }
    }
}

@Composable
private fun RenewButton(title: String, tag: String, fill: Color, ink: Color, onClick: () -> Unit) {
    val shape = ContinuousRoundedShape(12.dp)
    Box(
        Modifier
            .testTag(tag)
            .button(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button, shape = shape, onClick = onClick)
            .heightIn(min = 44.dp)
            .background(fill, shape)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(title, color = ink, style = PeekTheme.type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines(), textAlign = TextAlign.Center)
    }
}

/** `ViewThatFits(in: .horizontal)` of an HStack and a VStack: the items as wide as each other side by side where each fits its share, else each the full width one under the other. */
@Composable
private fun SideBySide(gap: Dp, content: @Composable () -> Unit) {
    Layout(content, Modifier.fillMaxWidth()) { measurables, constraints ->
        val space = gap.roundToPx()
        val width = constraints.maxWidth
        val share = (width - space * (measurables.size - 1)) / measurables.size.coerceAtLeast(1)
        val across = measurables.all { it.maxIntrinsicWidth(constraints.maxHeight) <= share }
        if (across) {
            val placeables = measurables.map { it.measure(constraints.copy(minWidth = share, maxWidth = share)) }
            val height = placeables.maxOfOrNull { it.height } ?: 0
            layout(width, height) {
                placeables.forEachIndexed { index, placeable -> placeable.placeRelative(index * (share + space), (height - placeable.height) / 2) }
            }
        } else {
            val placeables = measurables.map { it.measure(constraints.copy(minWidth = width, maxWidth = width, minHeight = 0)) }
            val height = placeables.sumOf { it.height } + space * (placeables.size - 1).coerceAtLeast(0)
            layout(width, height) {
                var y = 0
                for (placeable in placeables) {
                    placeable.placeRelative(0, y)
                    y += placeable.height + space
                }
            }
        }
    }
}

/**
 * `.padding(.vertical, 4).contentShape(Rectangle()).padding(.vertical, -4)`: drawn and touched [vertical] taller at
 * each end than it is laid out, as a chip 44 tall to the touch stands in the room of 36.
 */
private fun Modifier.overhang(vertical: Dp): Modifier = layout { measurable, constraints ->
    val extra = vertical.roundToPx()
    val placeable = measurable.measure(
        constraints.copy(maxHeight = if (constraints.hasBoundedHeight) constraints.maxHeight + 2 * extra else constraints.maxHeight),
    )
    layout(placeable.width, max(0, placeable.height - 2 * extra)) { placeable.placeRelative(0, -extra) }
}

private const val OPTIONS_GLYPH = "options"
private val TOUCH_OVERHANG = 4.dp

/** The page Add parcels by email's own run is held for. */
internal val ADD_BY_EMAIL_PAGE: UUID = UUID.fromString("5e771465-0000-4000-8000-000000000003")

/** The page's test tags, iOS's accessibility identifiers. */
internal const val ADD_BY_EMAIL_TAG = "addByEmail"
internal const val ADD_BY_EMAIL_START = "$ADD_BY_EMAIL_TAG.start"
internal const val ADD_BY_EMAIL_ADDRESS = "$ADD_BY_EMAIL_TAG.address"
internal const val ADD_BY_EMAIL_COPY = "$ADD_BY_EMAIL_TAG.copy"
internal const val ADD_BY_EMAIL_SHARE = "$ADD_BY_EMAIL_TAG.share"
internal const val ADD_BY_EMAIL_NOTIFY = "$ADD_BY_EMAIL_TAG.notify"
internal const val ADD_BY_EMAIL_AUTOMATIC = "$ADD_BY_EMAIL_TAG.automatic"
internal const val ADD_BY_EMAIL_RENEW = "$ADD_BY_EMAIL_TAG.renew"
internal const val ADD_BY_EMAIL_RENEW_CANCEL = "$ADD_BY_EMAIL_RENEW.cancel"
internal const val ADD_BY_EMAIL_RENEW_CONFIRM = "$ADD_BY_EMAIL_RENEW.confirm"
internal const val ADD_BY_EMAIL_OFF = "$ADD_BY_EMAIL_TAG.off"
internal const val ADD_BY_EMAIL_ERROR = "$ADD_BY_EMAIL_TAG.error"
internal const val ADD_BY_EMAIL_WORKING = "$ADD_BY_EMAIL_TAG.working"
internal const val ADD_BY_EMAIL_FILTER = "$ADD_BY_EMAIL_TAG.filter"
internal const val ADD_BY_EMAIL_FILTER_COPY = "$ADD_BY_EMAIL_FILTER.copy"
internal const val ADD_BY_EMAIL_CONFIRMATION = "$ADD_BY_EMAIL_TAG.confirmation"
