package com.plhery.peekdeliverytracker.ui.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.explanationKey
import com.plhery.peekdeliverytracker.core.symbol
import com.plhery.peekdeliverytracker.core.titleKey
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.deliveries.BAR_HEIGHT
import com.plhery.peekdeliverytracker.ui.deliveries.PageBarButton
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.barRowTop
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.deliveries.plainDisabled
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.passport.ExplanationPopover
import com.plhery.peekdeliverytracker.ui.passport.PassportSeal
import com.plhery.peekdeliverytracker.ui.passport.StampGrid
import com.plhery.peekdeliverytracker.ui.passport.popoverAnchor
import com.plhery.peekdeliverytracker.ui.sheet.BAR_ROW
import com.plhery.peekdeliverytracker.ui.tabs.tabContentPadding
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.ProvideDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.zoom.pageBarItem
import java.text.NumberFormat
import java.util.UUID
import kotlin.math.min
import kotlinx.coroutines.launch

// FriendsView.swift's `friendPage(_:)` and `FriendDetailView` (C-S22, C-S29, C-S30): a friend's page, pushed over the
// circle out of their card.

/**
 * `friendPage(_:)`: a scroll without indicators on the page's ground, what went wrong over the friend's page
 * ([FriendDetail]) while they are in the circle, in by 24 and 20 apart, at most 680 wide in the middle; under the bar
 * ([FriendPageBar]) and, in the demo, the demo's bar. Removing the friend closes the page once the service has done it.
 * TalkBack names it "passport.title", as the bar does.
 */
@Composable
internal fun FriendPage(state: FriendsState, friendId: UUID) {
    val source = state.source
    val store = source.friends
    val session by source.session.collectAsState()
    val snapshot by store.snapshotFlow.collectAsState()
    val errorKey by store.errorKeyFlow.collectAsState()
    val working by store.workingFlow.collectAsState()
    val padding = tabContentPadding()
    val direction = LocalLayoutDirection.current
    val start = padding.calculateStartPadding(direction)
    val end = padding.calculateEndPadding(direction)
    val bottom = padding.calculateBottomPadding()
    val title = localized("passport.title")
    val friend = snapshot?.friends?.firstOrNull { it.id == friendId }
    Box(Modifier.fillMaxSize().background(PeekTheme.colors.background).testTag(FRIENDS_PAGE).semantics { paneTitle = title }) {
        FriendsChrome(source, session == AuthenticationState.Demo, barRowTop() + BAR_HEIGHT, start, end, Modifier.fillMaxSize()) { top ->
            Box(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .testTag(FRIENDS_PAGE_SCROLL)
                    .padding(start = start, top = top, end = end, bottom = bottom),
                contentAlignment = Alignment.TopCenter,
            ) {
                Column(Modifier.widthIn(max = PAGE_MAX_WIDTH).fillMaxWidth().padding(24.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    errorKey?.let { FriendsErrorBanner(it) }
                    if (friend != null) {
                        FriendDetail(friend, working) {
                            source.scope.launch {
                                val request = FriendsActionRequest(FriendsAction.RemoveFriend, friendId = friend.id)
                                if (store.act(request, source.parcels.value) != null) state.closeFriend()
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * The page's bar, which the zoom keeps at the window's top as the page zooms under it: ‹ on its glass at the leading
 * edge, 20 in, or 10 where the tab bar stands at the top ([roomy], the iPad's), back to the circle as the system's back
 * goes, and "passport.title" in the middle at the default text size as a heading. While the store works there is no ‹
 * (`navigationBarBackButtonHidden(model.working)`). TalkBack reads ‹ as VoiceOver reads UIKit's back button: the
 * circle's title.
 */
@Composable
internal fun FriendPageBar(state: FriendsState, roomy: Boolean) {
    val working by state.source.friends.workingFlow.collectAsState()
    val zoom = state.zoom
    val padding = tabContentPadding()
    val direction = LocalLayoutDirection.current
    val start = padding.calculateStartPadding(direction)
    val end = padding.calculateEndPadding(direction)
    Box(Modifier.fillMaxWidth().padding(top = barRowTop()).height(BAR_ROW).testTag(FRIENDS_PAGE_BAR)) {
        ProvideDynamicTypeSize(DynamicTypeSize.Large) {
            Text(
                localized("passport.title"),
                Modifier
                    .align(Alignment.Center)
                    .padding(start = start + TITLE_CLEARANCE, end = end + TITLE_CLEARANCE)
                    // In with the page's bar, and out as the circle's own title comes back over a page held small.
                    .graphicsLayer { alpha = min(zoom.bar.trailing, 1f - zoom.bar.title) }
                    .semantics { heading() },
                color = PeekTheme.colors.label,
                style = PeekTheme.type.headline,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (!working) {
            GlassSurface(
                CapsuleShape,
                Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = start + if (roomy) ROOMY_MARGIN else BAR_MARGIN)
                    .pageBarItem(zoom, leading = true)
                    .size(BAR_BUTTON),
            ) {
                PageBarButton("chevron.left", localized("friends.title"), FRIENDS_PAGE_BACK, Modifier.align(Alignment.Center), state::closeFriend)
            }
        }
    }
}

/**
 * `FriendDetailView`, 22 apart: the friend's postcard in their colours; where they share their stats, what they have
 * delivered and their average delivery time side by side, "passport.stamps" and the grid of the stamps they earned and
 * the next three (all twelve once shown), four to a row, three from the xxLarge text size and two at the accessibility
 * sizes, with "passport.showAll" / "passport.showLess" under it while more than three are left to earn; else their stats
 * kept private behind a lock. Then a divider and "friends.manageFriendship", which holds Remove friend and then its
 * confirmation; Remove friend and Cancel are off while [busy]. Each stamp opens its popover, and each change of the
 * stamp open, to another or to none, plays a light impact at 0.6.
 */
@Composable
private fun FriendDetail(friend: FriendCard, busy: Boolean, remove: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val size = PeekTheme.dynamicTypeSize
    var showAll by rememberSaveable { mutableStateOf(false) }
    var stamp by rememberSaveable { mutableStateOf<FriendStamp?>(null) }
    var managing by rememberSaveable { mutableStateOf(false) }
    var confirming by rememberSaveable { mutableStateOf(false) }
    SensoryFeedbackEffect(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light, 0.6), stamp)
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(22.dp)) {
        val (tone, surface) = friend.tone()
        FriendPostcard(friend.nickname, friend.arrivedThisWeek, Modifier.testTag(FRIENDS_POSTCARD), tone, surface)
        val stats = friend.stats
        if (stats != null) {
            val count = remember(stats.deliveredCount, language) { NumberFormat.getIntegerInstance(language.locale).format(stats.deliveredCount) }
            val days = stats.averageDays?.let { localizer.text(if (it == 1) "friends.day" else "friends.days", mapOf("count" to it)) } ?: "—"
            Row(Modifier.fillMaxWidth().testTag(FRIENDS_METRICS), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.Top) {
                Metric(count, localizer.text("passport.delivered", mapOf("count" to stats.deliveredCount)), Modifier.weight(1f))
                Metric(days, localizer.text("passport.average"), Modifier.weight(1f))
            }
            Text(localized("passport.stamps"), color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
            val columns = when {
                size.isAccessibilitySize -> 2
                size >= DynamicTypeSize.XXLarge -> 3
                else -> 4
            }
            val unearned = FriendStamp.entries.filter { it !in stats.stamps }
            val upcoming = unearned.take(3).toSet()
            val visible = if (showAll) FriendStamp.entries else FriendStamp.entries.filter { it in stats.stamps || it in upcoming }
            StampGrid(columns) {
                for (value in visible) {
                    key(value) {
                        FriendStampButton(value, value in stats.stamps, stats.deliveredCount, stamp == value, { stamp = value }) {
                            if (stamp == value) stamp = null
                        }
                    }
                }
            }
            if (unearned.size > 3) {
                Box(
                    Modifier
                        .testTag(FRIENDS_SHOW_ALL)
                        .clickable(interactionSource = null, indication = PressStyle.Passport, role = Role.Button) { showAll = !showAll }
                        .heightIn(min = 44.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text(localized(if (showAll) "passport.showLess" else "passport.showAll"), color = colors.secondaryLabel, style = type.caption.sfLines())
                }
            }
        } else {
            SymbolLabel(localized("friends.privateStats"), "lock", type.footnote, colors.secondaryLabel, Modifier.testTag(FRIENDS_PRIVATE_STATS))
        }
        FormHairline()
        Disclosure(
            localized("friends.manageFriendship"), managing, { managing = it }, enabled = true,
            tag = FRIENDS_MANAGE_FRIENDSHIP, minHeight = 23.dp,
        ) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                if (confirming) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            localizer.text("friends.removeTitle", mapOf("name" to friend.nickname)),
                            color = colors.label, style = type.headline.sfLines(),
                        )
                        Text(localized("friends.removeDetail"), color = colors.secondaryLabel, style = type.subheadline.sfLines())
                        BorderedButton(localized("friends.remove"), FRIENDS_CONFIRM_REMOVE, enabled = !busy, action = remove)
                        PlainButton(localized("common.cancel"), FRIENDS_CANCEL_REMOVE, type.body, colors.ink, !busy) { confirming = false }
                    }
                } else {
                    PlainButton(localized("friends.remove"), FRIENDS_REMOVE, type.footnote, colors.secondaryLabel, !busy, Modifier.heightIn(min = 36.dp)) {
                        confirming = true
                    }
                }
            }
        }
    }
}

/** `metric(_:_:)`: the value in title2 semibold over what it counts in caption2 in the secondary grey, 4 apart. */
@Composable
private fun Metric(value: String, title: String, modifier: Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(value, color = colors.label, style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines())
        Text(title, color = colors.secondaryLabel, style = type.caption2.sfLines())
    }
}

/** A plain button's words in [style] and [tint], greyed while off, which fade as they are pressed. */
@Composable
private fun PlainButton(
    title: String,
    tag: String,
    style: androidx.compose.ui.text.TextStyle,
    tint: androidx.compose.ui.graphics.Color,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    action: () -> Unit,
) {
    Box(
        modifier
            .testTag(tag)
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = action)
            .plainDisabled(enabled),
        contentAlignment = Alignment.Center,
    ) {
        Text(title, color = tint, style = style.sfLines())
    }
}

/**
 * One of the friend's stamps (`PassportPressStyle`): its seal 50 × 64 over its title in caption2, the title faded to
 * 60 % until it is earned, 10 apart and 96 tall at least, read as its title alone. A tap opens its popover over it
 * ([open]): the title and how it is earned and, until it is, how far along the friend is; a tap outside it or the back
 * closes it ([onDismiss]).
 */
@Composable
private fun FriendStampButton(value: FriendStamp, earned: Boolean, count: Int, open: Boolean, onOpen: () -> Unit, onDismiss: () -> Unit) {
    val colors = PeekTheme.colors
    val title = localized(value.titleKey)
    val along = progress(value, earned, count, localized("passport.underTwoDays"))
    val explanation = localized(value.explanationKey) + along
    // Read as Passport reads one: its title, and while it isn't earned, how far along it is, or that it isn't.
    val notEarned = localized("passport.notEarned")
    val label = if (earned) title else "$title, ${along.trim('\n').ifEmpty { notEarned }}"
    var anchor by remember { mutableStateOf(Rect.Zero) }
    val (tint, surface) = stampColors(value)
    Column(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 96.dp)
            .popoverAnchor { anchor = it }
            .testTag(friendStampTag(value))
            .clickable(interactionSource = null, indication = PressStyle.Passport, onClick = onOpen)
            .clearAndSetSemantics {
                contentDescription = label
                role = Role.Button
                onClick {
                    onOpen()
                    true
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        PassportSeal(sealSymbol(value), colors.resolve(tint), colors.resolve(surface), earned, Modifier.size(50.dp, 64.dp))
        Text(
            title,
            color = if (earned) colors.ink else colors.ink.copy(alpha = 0.6f),
            style = PeekTheme.type.caption2.sfLines(),
            textAlign = TextAlign.Center,
        )
    }
    ExplanationPopover(open, anchor, onDismiss, title, explanation, FRIENDS_POPOVER)
}

/** The seal's symbol: "10" and "25" written out, three boxes for the busy doorstep, else the stamp's own. */
private fun sealSymbol(stamp: FriendStamp): String = when (stamp) {
    FriendStamp.Ten -> "10"
    FriendStamp.TheRegular -> "25"
    FriendStamp.BusyDoorstep -> "parcels"
    else -> stamp.symbol
}

/** `stampTint(_:)` and `stampSurface(_:)`. */
private fun stampColors(stamp: FriendStamp): Pair<AdaptiveColor, AdaptiveColor> = when (stamp) {
    FriendStamp.First, FriendStamp.AroundWorld, FriendStamp.RightNextDoor, FriendStamp.HomeForHolidays ->
        ExperimentalPalette.delivered to ExperimentalPalette.deliveredSurface
    FriendStamp.Ten, FriendStamp.TheRegular -> ExperimentalPalette.lilac to ExperimentalPalette.lilacSurface
    FriendStamp.Connected, FriendStamp.AcrossBorders, FriendStamp.PickedUp -> ExperimentalPalette.transit to ExperimentalPalette.transitSurface
    FriendStamp.Express, FriendStamp.BusyDoorstep -> ExperimentalPalette.pickup to ExperimentalPalette.pickupSurface
    FriendStamp.WorthTheWait -> ExperimentalPalette.ochre to ExperimentalPalette.ochreSurface
}

/**
 * `progress(_:earned:count:)`: how far along an unearned stamp is, on a line of its own: deliveries out of 1, 10 or 25
 * for the counting ones, "passport.underTwoDays" ([underTwoDays]) for the express; nothing once earned or for the rest.
 */
internal fun progress(stamp: FriendStamp, earned: Boolean, count: Int, underTwoDays: String): String {
    if (earned) return ""
    return when (stamp) {
        FriendStamp.First -> "\n${min(count, 1)} / 1"
        FriendStamp.Ten -> "\n${min(count, 10)} / 10"
        FriendStamp.Express -> "\n$underTwoDays"
        FriendStamp.TheRegular -> "\n${min(count, 25)} / 25"
        else -> ""
    }
}

private val PAGE_MAX_WIDTH = 680.dp
private val TITLE_CLEARANCE = 128.dp
private val BAR_BUTTON = 44.dp
private val BAR_MARGIN = 20.dp
private val ROOMY_MARGIN = 10.dp
