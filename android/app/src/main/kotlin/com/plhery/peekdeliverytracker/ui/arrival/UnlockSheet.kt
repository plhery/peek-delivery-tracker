package com.plhery.peekdeliverytracker.ui.arrival

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.deliveries.plainDisabled
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.sheet.KeyboardScrollColumn
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.sheetBottomInset
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlinx.coroutines.CoroutineScope

/**
 * What a guest asked for that an account unlocks (the iPhone's `UnlockReason`): the line the sheet says it with, before
 * the parcels coming along, and the title over it.
 */
enum class UnlockReason(val key: String?) {
    /** Signing in itself, from Settings, the list's account row or a link kept for later: only the parcels coming along. */
    Account(null),

    /** Several numbers in one text, from Add: "unlock.several". */
    Several("unlock.several"),

    /** No lookups left for today, from Add: "unlock.daily". */
    Daily("unlock.daily"),

    /** Friends compare passports between accounts, from the friends tab or the demo's invitation: "unlock.friends". */
    Friends("unlock.friends"),
    ;

    /** "Sign in" where signing in is itself the ask, else "Sign in to unlock this". */
    val title: String get() = if (key == null) "arrival.signInTitle" else "unlock.title"
}

/** Where the sheet may show: nobody is signed in, a guest's list or the demo under it. */
internal val AuthenticationState.unlockable: Boolean
    get() = this == AuthenticationState.Welcome || this == AuthenticationState.Demo

/**
 * The unlock sheet asked for over the window ([UnlockSheetHost]): one for the process ([process]), so a window made
 * again keeps it up. What the guest was doing stays under it as it was (Add and what it holds, Settings, a link's sheet,
 * the friends tab), so closing it goes back there and signing in carries on there.
 */
@Stable
class UnlockRequests {
    /** Why the sheet is up, while it is. */
    var reason by mutableStateOf<UnlockReason?>(null)
        private set

    /** A sign-in under way in the sheet, which holds it up until it ends. */
    internal var working by mutableStateOf(false)

    /** The email's form or its code is open, which the keyboard needs the whole height for. */
    internal var large by mutableStateOf(false)

    fun ask(reason: UnlockReason) {
        large = false
        this.reason = reason
    }

    fun close() {
        if (!working) reason = null
    }

    /** Signed in, or nobody to sign in any more: the sheet goes, whatever runs. */
    internal fun end() {
        working = false
        reason = null
    }

    companion object {
        /** This process's unlock sheet, for every window it makes and the link's sheet, as its pages' work is. */
        val process = UnlockRequests()
    }
}

/**
 * "Sign in to unlock this" (the iPhone's `UnlockSheet`, the guest design's one sheet per platform): what a guest can't
 * do without an account, asked over the screen where they wanted it rather than on the sign-in screen, which would
 * leave their list. It shows while nobody is signed in ([unlockable]: a guest's list, or the demo's invitation) and
 * signs in with [session], which leaves the guest's list, the widget and the Next delivery shortcut as they are until it
 * succeeds. Once someone is signed in it goes and the screen under it carries on (Add with its text, Settings, a link's
 * sheet, the friends tab). A drag, the dim, the back, its ✕ and "Not now" close it, but not while a sign-in runs. It is
 * as tall as it needs, and takes the whole height once the email's form opens, for the keyboard.
 */
@Composable
internal fun UnlockSheetHost(requests: UnlockRequests, state: AuthenticationState, session: SessionStore, work: CoroutineScope) {
    val reason = requests.reason
    LaunchedEffect(requests, reason != null, state) {
        if (reason != null && !state.unlockable) requests.end()
    }
    var shown by remember { mutableStateOf(reason) }
    if (reason != null) shown = reason
    Sheet(
        visible = reason != null && state.unlockable,
        onDismiss = requests::close,
        paneTitle = localized((shown ?: UnlockReason.Several).title),
        detents = listOf(SheetDetent.Fitted, SheetDetent.Large),
        onClosed = { shown = null },
        detent = if (requests.large) SheetDetent.Large else SheetDetent.Fitted,
        onDetentChange = { requests.large = it == SheetDetent.Large },
        dismissible = !requests.working,
        lit = true,
    ) {
        shown?.let { UnlockSheet(it, session, work, requests) }
    }
}

/**
 * The sheet, on the sheet's background, 22 in, 26 under its top and 18 over its foot, 560 wide at most, 14 apart: the
 * reason's title in title2 semibold beside the ✕ ("common.close"); the reason's line and "unlock.comes" in one
 * paragraph in subheadline secondary, as the iPhone writes them, or "unlock.comes" alone for [UnlockReason.Account];
 * the sign-in's card (paper, corners of 24, a hairline of the separator at 35 %, 22 in) with the ways in and the short
 * privacy line, as on the sign-in screen but without its title, which the sheet's says; and "Not now" in subheadline
 * secondary, centred, as the website's, which closes it.
 */
@Composable
private fun UnlockSheet(reason: UnlockReason, session: SessionStore, work: CoroutineScope, requests: UnlockRequests) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val flow = rememberSignInFlow(session, work)
    val form = flow.form
    val working = flow.working
    val large = form.emailExpanded || form.step == SignInStep.Code
    SideEffect {
        requests.working = working
        if (large) requests.large = true
    }
    val what = listOfNotNull(reason.key?.let { localized(it) }, localized("unlock.comes")).joinToString(" ")
    val closeLabel = LocalLocalizer.current.text("common.close")
    val bottom = sheetBottomInset()
    KeyboardScrollColumn(Modifier.fillMaxSize().background(colors.sheet), bottom = bottom, dismissesKeyboard = true) {
        Column(
            Modifier
                .testTag(UNLOCK_SHEET)
                .fillMaxWidth()
                .wrapContentWidth()
                .widthIn(max = UNLOCK_WIDTH)
                .fillMaxWidth()
                .padding(start = UNLOCK_SIDE, top = UNLOCK_TOP, end = UNLOCK_SIDE, bottom = UNLOCK_BOTTOM + bottom),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    localized(reason.title),
                    Modifier.weight(1f).semantics { heading() },
                    color = colors.label,
                    style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(),
                )
                Box(
                    Modifier
                        .size(44.dp)
                        .testTag(UNLOCK_CLOSE)
                        .button(interactionSource = null, indication = PlainPress, enabled = !working, role = Role.Button, shape = CircleShape, onClick = requests::close)
                        .plainDisabled(!working)
                        .semantics { contentDescription = closeLabel },
                    contentAlignment = Alignment.Center,
                ) {
                    Box(Modifier.size(36.dp).background(colors.sheetButton, CircleShape), contentAlignment = Alignment.Center) {
                        PeekSymbol("xmark", font = type.fixed(13f, FontWeight.SemiBold), tint = colors.label)
                    }
                }
            }
            Text(what, Modifier.testTag(UNLOCK_WHAT), color = colors.secondaryText, style = type.subheadline.sfLines())
            val card = ContinuousRoundedShape(24.dp)
            Column(
                Modifier
                    .fillMaxWidth()
                    .testTag(UNLOCK_CARD)
                    .background(colors.paper, card)
                    .border(0.75.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.35f), card)
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SignInWays(flow)
                PrivacyLine(session.configuration.privacyUrl, Modifier.padding(top = 18.dp))
            }
            Text(
                localized("unlock.later"),
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .heightIn(min = 44.dp)
                    .testTag(UNLOCK_LATER)
                    .button(interactionSource = null, indication = PlainPress, enabled = !working, role = Role.Button, onClick = requests::close)
                    .plainDisabled(!working)
                    .padding(horizontal = 12.dp)
                    .wrapContentHeight(Alignment.CenterVertically),
                color = colors.secondaryText,
                textAlign = TextAlign.Center,
                style = type.subheadline.sfLines(),
            )
        }
    }
}

private val UNLOCK_WIDTH = 560.dp
private val UNLOCK_SIDE = 22.dp
private val UNLOCK_TOP = 26.dp
private val UNLOCK_BOTTOM = 18.dp

// Test tags, which stay out of the copy's "unlock." keys.
internal const val UNLOCK_SHEET = "unlockSheet"
internal const val UNLOCK_CLOSE = "$UNLOCK_SHEET.close"
internal const val UNLOCK_WHAT = "$UNLOCK_SHEET.what"
internal const val UNLOCK_CARD = "$UNLOCK_SHEET.card"
internal const val UNLOCK_LATER = "$UNLOCK_SHEET.later"
