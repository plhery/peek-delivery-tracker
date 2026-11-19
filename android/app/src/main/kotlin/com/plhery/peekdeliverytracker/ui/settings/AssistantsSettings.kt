package com.plhery.peekdeliverytracker.ui.settings

import android.content.ClipData
import android.content.ClipboardManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collapse
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.expand
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.plhery.peekdeliverytracker.core.AssistantSetup
import com.plhery.peekdeliverytracker.core.AssistantSetups
import com.plhery.peekdeliverytracker.core.SetupLine
import com.plhery.peekdeliverytracker.core.SetupPiece
import com.plhery.peekdeliverytracker.core.api.AssistantConnection
import com.plhery.peekdeliverytracker.core.assistants
import com.plhery.peekdeliverytracker.core.assistantsGuideUrl
import com.plhery.peekdeliverytracker.core.detail
import com.plhery.peekdeliverytracker.platform.SitePage
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.REGULAR_SPINNER
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.sheet.BAR_ROW
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.rememberHaptics
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.util.UUID
import kotlinx.coroutines.launch

// AssistantsView.swift's `AssistantsPage` and the website's Settings › Assistants, which the server opens to some
// accounts only.

/**
 * The assistants' page, pushed in Settings for [account], as the website's: the "Beta" badge over the intro in
 * subheadline secondary and the link to the public page on connecting an assistant, which says the same and more; why
 * the last Disconnect failed, which TalkBack says; then, once the server answered, the address to give an assistant,
 * how to add Peek to each one, what to ask one, and the ones connected, each with Disconnect. A Disconnect runs in the
 * app's scope; while it waits, a spinner stands at the bar's trailing end, and neither the back, the sheet's close nor
 * another Disconnect can be pressed.
 */
@Composable
internal fun AssistantsPage(state: AccountHost, account: UUID, scroll: ScrollState, onBack: () -> Unit) {
    val source = state.source
    val store = source.assistants
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val stored by store.offerFlow.collectAsState()
    val working by store.workingFlow.collectAsState()
    val failure by store.failureFlow.collectAsState()
    val offer = stored?.takeIf { it.account == account }?.value
    // `session.configuration.apiBaseURL`, the site the parcels' links lead to too.
    val guide = remember(source.linkBaseUrl, language) { assistantsGuideUrl(source.linkBaseUrl, language) }
    PushedPage(
        localized("assistants.row"), scroll, backEnabled = !working, onBack = onBack,
        trailing = if (!working) null else {
            { GlassSurface(CapsuleShape, Modifier.size(BAR_ROW), backdrop = null, behind = colors.sheet) {
                ActivityIndicator(Modifier.align(Alignment.Center).testTag(ASSISTANTS_WORKING), size = REGULAR_SPINNER)
            } }
        },
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            SettingsBadge(localized("assistants.experimental"))
            Text(localized("assistants.intro"), color = colors.secondaryText, style = type.subheadline.sfLines())
            // A plain button of subheadline medium in the ink, 44 tall to the touch in the room of its line.
            Box(
                Modifier
                    .overhang(GUIDE_OVERHANG)
                    .testTag(ASSISTANTS_GUIDE)
                    .button(interactionSource = null, indication = PlainPress, role = Role.Button) { SitePage.open(context, guide, uriHandler) }
                    .semantics(mergeDescendants = true) {}
                    .padding(vertical = GUIDE_OVERHANG),
            ) {
                Text(localized("assistants.page.link"), color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines())
            }
        }
        failure?.let { error ->
            Text(
                localizer.errorMessage(error, fallback = "assistants.failed"),
                Modifier.testTag(ASSISTANTS_ERROR).semantics { liveRegion = LiveRegionMode.Polite },
                color = colors.warning, style = type.subheadline.sfLines(),
            )
        }
        if (offer != null) {
            AddressGroup(state, offer.mcpUrl)
            Setups(offer.mcpUrl)
            Uses()
            Connected(offer.assistants, working) { id -> source.scope.launch { store.revoke(account, id) } }
        }
    }
}

/** "assistants.address": Peek's address for assistants in body medium, which can be selected, then its copy row. */
@Composable
private fun AddressGroup(state: AccountHost, address: String) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val context = LocalContext.current
    val haptics = rememberHaptics()
    var copied by rememberSaveable(address) { mutableStateOf(false) }
    SettingsGroup(localized("assistants.address")) {
        SelectionContainer {
            Text(
                address, Modifier.fillMaxWidth().padding(ROW_PADDING).testTag(ASSISTANTS_ADDRESS),
                color = colors.ink, style = type.style(IosTextStyle.Body, FontWeight.Medium).sfLines(),
            )
        }
        SettingsDivider()
        SettingsButtonRow(
            localized(if (copied) "inbox.copied" else "inbox.copy"), ASSISTANTS_COPY,
            {
                context.getSystemService(ClipboardManager::class.java)?.setPrimaryClip(ClipData.newPlainText(address, address))
                haptics.success()
                state.announce(localizer.text("inbox.copied"))
                copied = true
            },
            symbol = if (copied) "checkmark" else "doc.on.doc",
        )
    }
}

/** "assistants.setup": each assistant's steps folded under its name, one open at a time, then a note for any other assistant. */
@Composable
private fun Setups(address: String) {
    val setups = remember { AssistantSetups.bundled }
    // The assistant whose steps are open.
    var open by rememberSaveable { mutableStateOf<String?>(null) }
    Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
        SettingsGroup(localized("assistants.setup")) {
            setups.forEachIndexed { index, setup ->
                if (index > 0) SettingsDivider()
                SetupRow(setup, address, open == setup.name) { open = if (open == setup.name) null else setup.name }
            }
        }
        Note(localized("assistants.how"))
    }
}

/**
 * One assistant (`setupRow`): its name in subheadline, and a chevron that turns down as its steps open under it;
 * TalkBack reads it as one button that expands and collapses. Open, the plans and places that can in caption
 * secondary, the steps numbered with signing in to Peek as the one before the last, then what to do where they don't
 * apply, 12 apart and 15 in.
 */
@Composable
private fun SetupRow(setup: AssistantSetup, address: String, shown: Boolean, onToggle: () -> Unit) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val turn by animateFloatAsState(if (shown) 90f else 0f, if (reduceMotion) Motion.linear(0.0) else Motion.easeOut(0.2), label = "setup")
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = ROW_HEIGHT)
                .testTag("$ASSISTANTS_SETUP.${setup.name}")
                .button(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onToggle)
                .semantics(mergeDescendants = true) {
                    // `.accessibilityAddTraits(shown ? .isSelected : [])`: TalkBack says "selected" while it is open.
                    selected = shown
                    if (shown) {
                        collapse {
                            onToggle()
                            true
                        }
                    } else {
                        expand {
                            onToggle()
                            true
                        }
                    }
                }
                .padding(ROW_PADDING),
            horizontalArrangement = Arrangement.spacedBy(11.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(setup.name, Modifier.weight(1f), color = colors.ink, style = type.subheadline.sfLines())
            PeekSymbol("chevron.right", Modifier.rotate(turn), font = type.fixed(10f, FontWeight.Light), tint = colors.secondaryText)
        }
        AnimatedVisibility(
            shown,
            enter = if (reduceMotion) EnterTransition.None else fadeIn(Motion.easeOut(0.2)) + expandVertically(Motion.easeOut(0.2)),
            exit = if (reduceMotion) ExitTransition.None else fadeOut(Motion.easeOut(0.2)) + shrinkVertically(Motion.easeOut(0.2)),
        ) {
            val steps = remember(setup, localizer, language) { setup.stepsWithSignIn(localizer) }
            Column(
                Modifier.fillMaxWidth().padding(start = ROW_PADDING, end = ROW_PADDING, bottom = ROW_PADDING).testTag("$ASSISTANTS_STEPS.${setup.name}"),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                SetupText(setup.who, address, secondary = true)
                steps.forEachIndexed { index, step ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "${index + 1}.", Modifier.alignByBaseline().clearAndSetSemantics {},
                            color = colors.secondaryText, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines(),
                        )
                        SetupText(step, address, Modifier.weight(1f).alignByBaseline())
                    }
                }
                setup.fallback?.let { SetupText(it, address, secondary = true) }
            }
        }
    }
}

/**
 * A setup line (`line`), which can be selected: the assistant's menus and buttons in bold, its own pages underlined
 * in the ink, opened as Settings' other pages of the web are ([SitePage]), and commands in monospace; in subheadline in
 * the ink, or caption secondary where [secondary].
 */
@Composable
private fun SetupText(line: SetupLine, address: String, modifier: Modifier = Modifier, secondary: Boolean = false) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val pieces = remember(line, address, localizer, language) { line.pieces(localizer, address) }
    val ink = colors.ink
    val text = remember(pieces, ink, context, uriHandler) {
        buildAnnotatedString {
            for (piece in pieces) {
                when (piece) {
                    is SetupPiece.Plain -> append(piece.text)
                    is SetupPiece.Name -> withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(piece.text) }
                    is SetupPiece.Link -> withLink(
                        LinkAnnotation.Url(piece.url, TextLinkStyles(SpanStyle(color = ink, textDecoration = TextDecoration.Underline))) {
                            SitePage.open(context, piece.url, uriHandler)
                        },
                    ) { append(piece.text) }
                    // Monospace's own widths, without the spacing that gives Roboto SF's.
                    is SetupPiece.Command -> withStyle(SpanStyle(fontFamily = FontDesign.Monospaced.family, letterSpacing = 0.em)) { append(piece.text) }
                }
            }
        }
    }
    SelectionContainer(modifier) {
        Text(
            text, Modifier.fillMaxWidth(),
            color = if (secondary) colors.secondaryText else colors.ink,
            style = (if (secondary) type.caption else type.subheadline).sfLines(),
        )
    }
}

/** "assistants.uses": what to ask a connected assistant, first, in bold, to add the tracking numbers in the reader's emails. */
@Composable
private fun Uses() {
    val colors = PeekTheme.colors
    val style = PeekTheme.type.subheadline.sfLines()
    val email = localized("assistants.uses.email")
    val note = localized("assistants.uses.emailNote")
    SettingsGroup(localized("assistants.uses")) {
        Column(Modifier.fillMaxWidth().padding(ROW_PADDING), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            val first = remember(email, note) {
                buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(email) }
                    append(" ")
                    append(note)
                }
            }
            Text(first, color = colors.ink, style = style)
            for (key in listOf("assistants.uses.week", "assistants.uses.where", "assistants.uses.archive")) {
                Text(localized(key), color = colors.ink, style = style)
            }
        }
    }
}

/** "assistants.connected": "assistants.none" in subheadline secondary, or each assistant connected with OAuth. */
@Composable
private fun Connected(assistants: List<AssistantConnection>, working: Boolean, onRevoke: (UUID) -> Unit) {
    SettingsGroup(localized("assistants.connected")) {
        if (assistants.isEmpty()) {
            Text(
                localized("assistants.none"), Modifier.fillMaxWidth().padding(ROW_PADDING).testTag(ASSISTANTS_NONE),
                color = PeekTheme.colors.secondaryText, style = PeekTheme.type.subheadline.sfLines(),
            )
        }
        assistants.forEachIndexed { index, connection ->
            if (index > 0) SettingsDivider()
            AssistantRow(connection, working) { onRevoke(connection.id) }
        }
    }
}

/**
 * A connected assistant (`AssistantRow`): its name in subheadline semibold over where it sends the reader back, what it
 * may do and when it was last used, in caption secondary, read by TalkBack as one; then Disconnect in subheadline
 * medium red, which TalkBack names with the assistant, off while one waits.
 */
@Composable
private fun AssistantRow(connection: AssistantConnection, working: Boolean, onRevoke: () -> Unit) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val detail = remember(connection, localizer, language) { connection.detail(localizer) }
    val label = remember(connection.name, localizer, language) { localizer.text("assistants.revokeNamed", mapOf("name" to connection.name)) }
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = ROW_HEIGHT)
            .semantics { isTraversalGroup = true }
            .padding(ROW_PADDING),
        horizontalArrangement = Arrangement.spacedBy(11.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f).semantics(mergeDescendants = true) {}, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(connection.name, color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
            Text(detail, color = colors.secondaryText, style = type.caption.sfLines())
        }
        Box(
            Modifier
                .heightIn(min = 44.dp)
                .testTag("$ASSISTANTS_DISCONNECT.${connection.id}")
                .button(interactionSource = null, indication = PlainPress, enabled = !working, role = Role.Button, onClick = onRevoke)
                .semantics { contentDescription = label },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                localized("assistants.disconnect"), Modifier.clearAndSetSemantics {},
                color = if (working) colors.tertiaryLabel else colors.red,
                style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines(),
            )
        }
    }
}

/** How far the guide's link is touched above and below its line, as a button 44 tall stands in the room of its words. */
private val GUIDE_OVERHANG = 12.dp

/** The page's test tags, iOS's accessibility identifiers. */
internal const val ASSISTANTS_TAG = "assistants"
internal const val ASSISTANTS_GUIDE = "$ASSISTANTS_TAG.guide"
internal const val ASSISTANTS_ERROR = "$ASSISTANTS_TAG.error"
internal const val ASSISTANTS_WORKING = "$ASSISTANTS_TAG.working"
internal const val ASSISTANTS_ADDRESS = "$ASSISTANTS_TAG.address"
internal const val ASSISTANTS_COPY = "$ASSISTANTS_TAG.copy"
internal const val ASSISTANTS_SETUP = "$ASSISTANTS_TAG.setup"
internal const val ASSISTANTS_STEPS = "$ASSISTANTS_TAG.steps"
internal const val ASSISTANTS_NONE = "$ASSISTANTS_TAG.none"
internal const val ASSISTANTS_DISCONNECT = "$ASSISTANTS_TAG.disconnect"
