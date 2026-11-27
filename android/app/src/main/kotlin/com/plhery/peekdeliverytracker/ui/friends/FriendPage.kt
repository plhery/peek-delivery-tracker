package com.plhery.peekdeliverytracker.ui.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.PassportBook
import com.plhery.peekdeliverytracker.core.PassportData
import com.plhery.peekdeliverytracker.core.StampProgress
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendCountry
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.explanationKey
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
import com.plhery.peekdeliverytracker.ui.passport.CountryInk
import com.plhery.peekdeliverytracker.ui.passport.ExplanationPopover
import com.plhery.peekdeliverytracker.ui.passport.LocalPassportNews
import com.plhery.peekdeliverytracker.ui.passport.PassportSettle
import com.plhery.peekdeliverytracker.ui.passport.StampGrid
import com.plhery.peekdeliverytracker.ui.passport.StampSeal
import com.plhery.peekdeliverytracker.ui.passport.phase
import com.plhery.peekdeliverytracker.ui.passport.popoverAnchor
import com.plhery.peekdeliverytracker.ui.passport.stampTitle
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
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.zoom.pageBarItem
import java.text.Collator
import java.text.NumberFormat
import java.util.UUID
import kotlin.math.min
import kotlinx.coroutines.launch

// FriendsView.swift's `friendPage(_:)` and `FriendDetailView` (C-S22, C-S29, C-S30): a friend's page, pushed over the
// circle out of their card.

/**
 * `friendPage(_:)`: a scroll without indicators on the page's ground, what went wrong over the friend's page
 * ([FriendDetail]) while they are in the circle, in by 24 and 20 apart, at most 680 wide in the middle; under the bar
 * ([FriendPageBar]) and, in the demo, the demo's bar. The friend's passport stands beside the reader's, worked out here
 * from the reader's own parcels as their Passport is. Removing the friend closes the page once the service has done it.
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
    val parcels by source.parcels.collectAsState()
    val book = LocalPassportNews.current?.book ?: remember(parcels) { PassportBook(parcels) }
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
                        FriendDetail(friend, book, working) {
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
 * delivered and their average delivery time side by side, then their passport beside the reader's [book]
 * ([FriendCompare]); else their stats kept private behind a lock. Then a divider and "friends.manageFriendship", which
 * holds Remove friend and then its confirmation; Remove friend and Cancel are off while [busy].
 */
@Composable
private fun FriendDetail(friend: FriendCard, book: PassportBook, busy: Boolean, remove: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    var managing by rememberSaveable { mutableStateOf(false) }
    var confirming by rememberSaveable { mutableStateOf(false) }
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
            FriendCompare(friend, stats, book)
        } else {
            SymbolLabel(localized("friends.privateStats"), "lock", type.footnote, colors.secondaryText, Modifier.testTag(FRIENDS_PRIVATE_STATS))
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
                        Text(localized("friends.removeDetail"), color = colors.secondaryText, style = type.subheadline.sfLines())
                        BorderedButton(localized("friends.remove"), FRIENDS_CONFIRM_REMOVE, enabled = !busy, action = remove)
                        PlainButton(localized("common.cancel"), FRIENDS_CANCEL_REMOVE, type.body, colors.ink, !busy) { confirming = false }
                    }
                } else {
                    PlainButton(localized("friends.remove"), FRIENDS_REMOVE, type.footnote, colors.secondaryText, !busy, Modifier.heightIn(min = 36.dp)) {
                        confirming = true
                    }
                }
            }
        }
    }
}

/**
 * The stamps of a friend whom no comparison has anything to show for, neither of them holding a stamp yet:
 * "passport.stamps" and the grid of the stamps they earned and the next three (all twelve once shown), four to a row,
 * three from the xxLarge text size and two at the accessibility sizes, with "passport.showAll" / "passport.showLess"
 * under it while more than three are left to earn; 22 apart. Each stamp opens its popover, and each change of the stamp
 * open, to another or to none, plays a light impact at 0.6.
 */
@Composable
private fun FriendStampCollection(stats: FriendStats) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val size = PeekTheme.dynamicTypeSize
    var showAll by rememberSaveable { mutableStateOf(false) }
    var stamp by rememberSaveable { mutableStateOf<FriendStamp?>(null) }
    SensoryFeedbackEffect(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light, 0.6), stamp)
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(22.dp)) {
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
            // Its words alone take a finger, as the iPhone's with its `.frame(minHeight: 44)` outside the button.
            Box(Modifier.heightIn(min = 44.dp), contentAlignment = Alignment.CenterStart) {
                Text(
                    localized(if (showAll) "passport.showLess" else "passport.showAll"),
                    Modifier
                        .testTag(FRIENDS_SHOW_ALL)
                        .button(interactionSource = null, indication = PressStyle.Passport, role = Role.Button) { showAll = !showAll },
                    color = colors.secondaryText,
                    style = type.caption.sfLines(),
                )
            }
        }
    }
}

/** The groups a friend's page puts the twelve shared stamps in, in their order, each with its heading's key. */
internal enum class StampGroup(val headingKey: String) {
    Both("friends.bothHave"),
    Friend("friends.onlyFriend"),
    Reader("friends.onlyYou"),
}

/**
 * The web's groups in `FriendPassportCompare`: the twelve shared stamps in their order, each as the reader's [book] has
 * it, put with those the friend ([theirs]) and the reader both have, those only the friend has, or those only the
 * reader has; a group with none is left out.
 */
internal fun compareStamps(book: PassportBook, theirs: List<FriendStamp>): List<Pair<StampGroup, List<StampProgress>>> {
    val mine = book.stamps.associateBy { it.key }
    fun has(stamp: FriendStamp) = mine[stamp.rawValue]?.earned == true
    fun pick(test: (FriendStamp) -> Boolean) = FriendStamp.entries.filter(test).mapNotNull { mine[it.rawValue] }
    return listOf(
        StampGroup.Both to pick { it in theirs && has(it) },
        StampGroup.Friend to pick { it in theirs && !has(it) },
        StampGroup.Reader to pick { it !in theirs && has(it) },
    ).filter { it.second.isNotEmpty() }
}

/** "friends.yourProgress" under a stamp only the friend has: how far the reader is, where it counts to more than one. */
internal fun yourProgress(stamp: StampProgress, localizer: Localizer): String? =
    if (stamp.total > 1) localizer.text("friends.yourProgress", mapOf("count" to min(stamp.count, stamp.total), "total" to stamp.total)) else null

/**
 * The web's `FriendPassportCompare`: the friend's passport beside the reader's [book], 26 between its sections. The
 * stamp groups ([compareStamps]), each its heading and count over a grid of the reader's seals for its stamps, four to a
 * row (three in a window narrower than 360, or from the xxLarge text size, and two at the accessibility sizes), rows 14
 * apart and columns 8; under each stamp only the friend has, how far the reader is ([yourProgress]). With no group at
 * all, the friend's stamps as they were shown before ([FriendStampCollection]). Then, where the friend shares them,
 * their countries: "passport.countriesLabel" and its count, how many the reader's parcels went through too, and their
 * inks with the code alone, as the Countries page lays them out, the most parcels first, then by name.
 *
 * Each stamp and country rises in, one after the other, 160 ms in and 45 ms apart up to the thirteenth, over 320 ms on
 * the passport's settle; under Reduce Motion they are there. Nothing on it opens a popover.
 */
@Composable
private fun FriendCompare(friend: FriendCard, stats: FriendStats, book: PassportBook) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val size = PeekTheme.dynamicTypeSize
    val window = with(LocalDensity.current) { LocalWindowInfo.current.containerSize.width.toDp() }
    val groups = remember(book, stats.stamps) { compareStamps(book, stats.stamps) }
    val countries = remember(stats.countries, language) {
        val collator = Collator.getInstance(language.locale)
        stats.countries.orEmpty()
            .map { it to TrackingLocation.countryName(it.code, language) }
            .sortedWith(compareByDescending<Pair<FriendCountry, String>> { it.first.count }.thenBy(collator) { it.second })
    }
    val reached = remember(book) { book.countries.mapTo(HashSet()) { it.code } }
    val common = countries.count { it.first.code in reached }
    val columns = when {
        size.isAccessibilitySize -> 2
        size >= DynamicTypeSize.XXLarge || window < NARROW_WINDOW -> 3
        else -> 4
    }
    // One count through the stamps, then the countries: where each starts.
    val firsts = groups.runningFold(0) { first, group -> first + group.second.size }
    val cells = firsts.last() + countries.size
    val reduceMotion = PeekTheme.reduceMotion
    val clock = remember(reduceMotion) { if (reduceMotion) null else Animatable(0f) }
    if (clock != null) {
        val end = riseStart(cells) + RISE
        LaunchedEffect(clock) { clock.animateTo(end, tween(end.toInt(), easing = LinearEasing)) }
    }
    val rise = { order: Int ->
        Modifier.graphicsLayer {
            if (clock != null) {
                val rise = phase(clock.value, riseStart(order), RISE, PassportSettle)
                translationY = 8.dp.toPx() * (1 - rise)
                alpha = rise
            }
        }
    }
    Column(Modifier.fillMaxWidth().testTag(FRIENDS_COMPARE), verticalArrangement = Arrangement.spacedBy(26.dp)) {
        if (groups.isEmpty()) FriendStampCollection(stats)
        groups.forEachIndexed { index, (group, stamps) ->
            Column(Modifier.fillMaxWidth().testTag(friendGroupTag(group))) {
                CompareHeading(localizer.text(group.headingKey, mapOf("name" to friend.nickname)), stamps.size)
                Spacer(Modifier.height(14.dp))
                StampGrid(columns, gap = 8.dp, rowGap = 14.dp) {
                    stamps.forEachIndexed { at, stamp ->
                        key(stamp.key) {
                            val progress = if (group == StampGroup.Friend) yourProgress(stamp, localizer) else null
                            CompareStamp(stamp, progress, rise(firsts[index] + at))
                        }
                    }
                }
            }
        }
        if (countries.isNotEmpty()) {
            Column(Modifier.fillMaxWidth().testTag(FRIENDS_COUNTRIES)) {
                CompareHeading(localizer.text("passport.countriesLabel", mapOf("count" to countries.size)), countries.size)
                if (common > 0) {
                    Spacer(Modifier.height(6.dp))
                    Text(
                        localizer.text("friends.countriesBoth", mapOf("count" to common)), color = colors.secondaryText,
                        style = type.fixed(type.scaled(12f, IosTextStyle.Caption)).sfLines(),
                    )
                }
                Spacer(Modifier.height(14.dp))
                StampGrid(if (window >= WIDE_WINDOW) 3 else 2, gap = 12.dp, rowGap = 22.dp) {
                    countries.forEachIndexed { at, (country, name) ->
                        key(country.code) { CompareCountry(country, name, rise(firsts.last() + at)) }
                    }
                }
            }
        }
    }
}

/** When the cell [order] places rises: 160 ms in, then 45 ms after the one before, up to the thirteenth. */
private fun riseStart(order: Int): Float = 160f + min(order, 12) * 45f

/** A cell's rise, in ms. */
private const val RISE = 320f

/** Narrower than this, a group's stamps go three to a row. */
private val NARROW_WINDOW = 360.dp

/** From this wide, the countries go three to a row, as the Countries page's on the web. */
private val WIDE_WINDOW = 560.dp

/** A section's heading in semibold 16, then its [count] in the regular weight in the secondary grey; a heading for TalkBack. */
@Composable
private fun CompareHeading(title: String, count: Int) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val heading = buildAnnotatedString {
        append(title)
        append(" ")
        withStyle(SpanStyle(color = colors.secondaryText, fontWeight = FontWeight.Normal)) { append(count.toString()) }
    }
    Text(
        heading, Modifier.semantics { heading() }, color = colors.label,
        style = type.fixed(type.scaled(16f, IosTextStyle.Callout), FontWeight.SemiBold).sfLines(),
    )
}

/**
 * A stamp in a group: the reader's seal, earned or not, without its postmark; its title in caption2 in ink under it, 6
 * apart, and the [progress] in 10 in the secondary grey under that. TalkBack reads its words as one.
 */
@Composable
private fun CompareStamp(stamp: StampProgress, progress: String?, modifier: Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val value = FriendStamp.entries.firstOrNull { it.rawValue == stamp.key }
    Column(
        modifier
            .fillMaxWidth()
            .then(if (value != null) Modifier.testTag(friendCompareTag(value)) else Modifier)
            .semantics(mergeDescendants = true) {},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        StampSeal(stamp.definition, stamp.earned, postmark = false)
        Text(stampTitle(stamp.key, localizer), color = colors.ink, style = type.caption2.sfLines(), textAlign = TextAlign.Center)
        if (progress != null) {
            Text(progress, color = colors.secondaryText, style = type.fixed(type.scaled(10f, IosTextStyle.Caption2)).sfLines(), textAlign = TextAlign.Center)
        }
    }
}

/**
 * One of the friend's countries, laid out as the Countries page lays its own: the ink with the code alone, 124 wide at
 * most, then the flag (hidden from TalkBack) and the [name], then the parcels; read as the name and the parcels.
 */
@Composable
private fun CompareCountry(country: FriendCountry, name: String, modifier: Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val parcels = LocalLocalizer.current.text("passport.parcels.many", mapOf("count" to country.count))
    Column(
        modifier
            .fillMaxWidth()
            .testTag(friendCountryTag(country.code))
            .clearAndSetSemantics { contentDescription = "$name, $parcels" }
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        // Not filling the width, which would override the ink's own cap of 124.
        CountryInk(country.code, number = null, day = null, drop = null, Modifier.padding(bottom = 6.dp))
        Text(
            "${TrackingLocation.flag(country.code)} $name", color = colors.ink,
            style = type.fixed(type.scaled(12.5f, IosTextStyle.Caption)).sfLines(), textAlign = TextAlign.Center,
        )
        Text(parcels, color = colors.secondaryText, style = type.fixed(type.scaled(11f, IosTextStyle.Caption2)).sfLines(), textAlign = TextAlign.Center)
    }
}

/** `metric(_:_:)`: the value in title2 semibold over what it counts in caption2 in the secondary grey, 4 apart. */
@Composable
private fun Metric(value: String, title: String, modifier: Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(value, color = colors.label, style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines())
        Text(title, color = colors.secondaryText, style = type.caption2.sfLines())
    }
}

/**
 * A plain button's words in [style] and [tint], greyed while off, which fade as they are pressed. The words alone take a
 * finger, as a SwiftUI `Button` does with a frame outside it: [modifier] (Remove's `.frame(minHeight: 36)`) only places
 * them.
 */
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
    Box(modifier, contentAlignment = Alignment.Center) {
        Text(
            title,
            Modifier
                .testTag(tag)
                .button(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = action)
                .plainDisabled(enabled),
            color = tint,
            style = style.sfLines(),
        )
    }
}

/**
 * One of the friend's stamps (`PassportPressStyle`): the passport's seal over its title in caption2, the title faded to
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
    val seal = remember(value) { PassportData.stamps.firstOrNull { it.key == value.rawValue } }
    Column(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 96.dp)
            .popoverAnchor { anchor = it }
            .testTag(friendStampTag(value))
            .button(interactionSource = null, indication = PressStyle.Passport, onClick = onOpen)
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
        // The passport's own seal for the stamp, in its tone.
        if (seal != null) StampSeal(seal, earned)
        Text(
            title,
            color = if (earned) colors.ink else colors.secondaryText,
            style = PeekTheme.type.caption2.sfLines(),
            textAlign = TextAlign.Center,
        )
    }
    ExplanationPopover(open, anchor, onDismiss, title, explanation, FRIENDS_POPOVER)
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
