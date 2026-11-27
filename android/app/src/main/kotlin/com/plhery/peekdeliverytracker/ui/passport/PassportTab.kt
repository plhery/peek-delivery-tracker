package com.plhery.peekdeliverytracker.ui.passport

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.PassportSpread
import com.plhery.peekdeliverytracker.core.PassportBook
import com.plhery.peekdeliverytracker.core.PassportNews
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.readFirst
import com.plhery.peekdeliverytracker.ui.deliveries.AccountBar
import com.plhery.peekdeliverytracker.ui.deliveries.Announcement
import com.plhery.peekdeliverytracker.ui.deliveries.Announcements
import com.plhery.peekdeliverytracker.ui.deliveries.BAR_HEIGHT
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesSource
import com.plhery.peekdeliverytracker.ui.deliveries.DemoModeBar
import com.plhery.peekdeliverytracker.ui.deliveries.LocalWindowArrangement
import com.plhery.peekdeliverytracker.ui.deliveries.SoftTopEdge
import com.plhery.peekdeliverytracker.ui.deliveries.TabRowAccount
import com.plhery.peekdeliverytracker.ui.deliveries.barRowTop
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.deliveries.rememberForLaunch
import com.plhery.peekdeliverytracker.ui.settings.AccountHost
import com.plhery.peekdeliverytracker.ui.settings.AccountSheetHost
import com.plhery.peekdeliverytracker.ui.settings.DELIVERY_UPDATES_PAGE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_PAGE
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabBarPadding
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowHinge
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.tabs.tabContentPadding
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import kotlin.math.max
import kotlin.math.min

// PassportView.swift's `PassportView` (A-S87 to A-S92, A-S100): the passport tab, its bar and Settings, the cover, and
// under it three pages (the countries reached, the stamps, the numbers), each item explained in a popover.

/**
 * What PassportView keeps in `@State`: Settings open, the popover open (`expandedCard`), all stamps shown, the page
 * chosen, the touches.
 */
@Stable
internal class PassportState(
    override val source: DeliveriesSource,
    private val showing: MutableState<Boolean>,
    private val forms: MutableState<Boolean>,
    private val expandedCard: MutableState<String?>,
    showAll: MutableState<Boolean>,
    chosen: MutableState<Int>,
) : AccountHost {
    override val showingAccount: Boolean get() = showing.value
    override val sheetsAsForms: Boolean get() = forms.value

    /** The item whose explanation pops over it ([PassportSelection]), at most one. */
    val expanded: String? get() = expandedCard.value

    var showAllStamps: Boolean by showAll

    /** The page chosen ([PassportPage]): the one in view, or on an open book the one its spread is opened for. */
    var page: Int by chosen

    /** `touchCount`, which plays the light impact as each toggle counts it. */
    var touchCount: Int by mutableIntStateOf(0)
        private set

    var announcement: Announcement? by mutableStateOf(null)
        private set

    /**
     * `showingAccount = true`, from the account button: Settings afresh (`AccountView().formOnPad()`), a form [asForm]
     * and the page sheet otherwise, kept so while it is open. The passport sends no view as it opens or closes.
     */
    fun account(asForm: Boolean) {
        if (showingAccount) return
        source.pageWork.forget(SETTINGS_PAGE)
        source.pageWork.forget(DELIVERY_UPDATES_PAGE)
        forms.value = asForm
        showing.value = true
    }

    override fun closeAccount() {
        showing.value = false
    }

    /** `toggle(_:)`: the item's explanation opens, or closes if it was open, with the light impact. */
    fun toggle(id: String) {
        touchCount++
        expandedCard.value = if (expandedCard.value == id) null else id
    }

    /** The popover's binding set to false (a tap outside it, the back): it closes, without the impact. */
    fun dismiss(id: String) {
        if (expandedCard.value == id) expandedCard.value = null
    }

    override fun announce(text: String) {
        announcement = Announcement(text, (announcement?.count ?: 0) + 1)
    }

    fun announced(announcement: Announcement) {
        if (this.announcement == announcement) this.announcement = announcement.copy(text = "")
    }
}

/** `PassportSelection`: the passport's items that explain themselves, as their popovers know them. */
internal object PassportSelection {
    const val COVER = "cover"
    const val AVERAGE = "average"
    const val PERSONAL_BEST = "personalBest"

    fun milestone(id: String) = "milestone.$id"

    fun country(code: String) = "country.$code"
}

/** The passport's `@State`, kept with the window (a rotation, a fold) and while another tab shows, not past the launch. */
@Composable
internal fun rememberPassportState(source: DeliveriesSource): PassportState {
    val launch = source.launch
    val showing = rememberForLaunch(launch, false)
    val forms = rememberForLaunch(launch, false)
    val expanded = rememberForLaunch<String?>(launch, null)
    val showAll = rememberForLaunch(launch, false)
    val page = rememberForLaunch(launch, PassportPage.COUNTRIES)
    return remember(source) { PassportState(source, showing, forms, expanded, showAll, page) }
}

/** How the passport shares the window. */
internal sealed interface PassportLayout {
    /** An upright fold standing half open: the cover before it, the pages after it, each its side's width. */
    data class Fold(val first: Int, val gap: Int, val second: Int) : PassportLayout

    /** A window [OPEN_BOOK] wide or more: the book lies open under the cover, two pages side by side. */
    data object Open : PassportLayout

    /** The cover over the pages, one in view, at most 480 wide and centred. */
    data object Single : PassportLayout
}

/**
 * The layout the passport takes: the cover and the pages either side of an upright fold standing half open (`proxy.fold`,
 * as the first open reads it), else the book lying open where the window is [OPEN_BOOK] wide, else one page in view.
 * [hinge] is the fold's start and end in pixels from the content's left, [content] the content's width in pixels.
 */
internal fun passportLayout(windowWidth: Dp, hinge: Pair<Int, Int>?, content: Int): PassportLayout = when {
    hinge != null -> PassportLayout.Fold(max(0, hinge.first), max(0, hinge.second - hinge.first), max(0, content - hinge.second))
    windowWidth >= OPEN_BOOK -> PassportLayout.Open
    else -> PassportLayout.Single
}

/** The page the passport opens on with [unseen] news: the countries' if a country is new, else the stamps', else none. */
internal fun newsPage(unseen: List<PassportNews.Item>): Int? = when {
    unseen.any { it.country != null } -> PassportPage.COUNTRIES
    unseen.any { it.stamp != null } -> PassportPage.STAMPS
    else -> null
}

/**
 * `PassportView`: the cover and the pages in a scroll without indicators on the page's ground, under the bar with the
 * inline title and the account button ([AccountBar]) and, in the demo, the demo's bar ([DemoModeBar]); Settings over it
 * from the account button ([AccountSheetHost]), a form where the deliveries would stand side by side. Where the tab bar
 * stands at the top, as the iPad's, there is no title and the account button stands in the tab bar's row
 * ([TabRowAccount]). The passport is laid out by window ([passportLayout]); a change of window keeps the popover open,
 * all the stamps shown and the page chosen, as its `@State` does. Each toggle plays a light impact. In December, with a
 * parcel delivered this year, the year's card is offered at the foot of the Numbers page ([PassportYearCard]).
 *
 * The passport opens on the page with news, the countries' first ([newsPage]); else on the page last chosen. What was
 * new as it opened stays new until the reader leaves it, and each page's new stamps or countries press in the first
 * time that page shows, a label's dot marking a page with news not shown yet.
 */
@Composable
internal fun PassportTab(source: DeliveriesSource) {
    val state = rememberPassportState(source)
    val session by source.session.collectAsState()
    val parcels by source.parcels.collectAsState()
    // The tabs' passport, and what in it is new; on its own where no tabs share it.
    val news = LocalPassportNews.current
    val book = news?.book ?: remember(parcels) { PassportBook(parcels) }
    val stats = book.stats
    // What was new as the passport opened stays new, and is pressed in, until the reader leaves it; all it holds is
    // then marked seen, and the tab's dot and the cards' lines go.
    val opening = remember { news?.takeUnless { it.loading }?.news?.unseen.orEmpty() }
    var fresh by remember { mutableStateOf(opening.map { it.seenKey }.toSet()) }
    // It opens on the page with news. Set before anything reads the page, so it opens there without a turn.
    remember { newsPage(opening)?.also { state.page = it } }
    // The pages shown since the passport opened, each with how long its turn into view took ([PAGE_TURN]), which its
    // news waits for: none for those in view as it opened, nor under Reduce Motion.
    val shown = remember { HashMap<Int, Int>() }
    LaunchedEffect(news) {
        if (news == null || news.loading || news.news.unseen.isEmpty()) return@LaunchedEffect
        fresh = fresh + news.news.unseen.map { it.seenKey }
        news.markSeen()
    }
    val colors = PeekTheme.colors
    val density = LocalDensity.current
    // The demo's bar, and the cream behind the bar that goes with it, unless left out for the store's screenshots.
    val demo = session == AuthenticationState.Demo && !source.storeScreenshots
    val padding = tabContentPadding()
    val direction = LocalLayoutDirection.current
    val start = padding.calculateStartPadding(direction)
    val end = padding.calculateEndPadding(direction)
    // Where the tab bar stands at the top, the iPad's: no title, the account in the tab bar's row and what is under the
    // bar 10 under the tab bar ([barRowTop]). Else the inline title's bar, under the status bar.
    val tabBarAtTop = LocalTabBarPadding.current.calculateTopPadding() > 0.dp
    val rowTop = barRowTop()
    val barBottom = if (tabBarAtTop) rowTop else rowTop + BAR_HEIGHT
    val bottom = padding.calculateBottomPadding() + BOTTOM_SPACING + PAGE_BOTTOM
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val anyHinge = LocalWindowHinge.current
    val hinge = anyHinge?.takeIf { it.halfOpen }
    val backdrop = rememberMaterialBackdrop()
    val windowArrangement = LocalWindowArrangement.current
    SensoryFeedbackEffect(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light, 0.65), state.touchCount)
    BoxWithConstraints(Modifier.fillMaxSize().background(colors.background).testTag(PeekTab.Passport.screen)) {
        val regularWidth = maxWidth >= REGULAR_WIDTH
        val regularHeight = maxHeight >= REGULAR_HEIGHT
        // Settings as the deliveries would show it in this window now: a form where they would stand side by side, else
        // the page. They would turn from the arrangement they last had, slack included, which only they change.
        val deliveries = if (anyHinge != null && regularWidth && regularHeight) {
            DeliveriesArrangement.SideBySide
        } else {
            DeliveriesArrangement.resolve(
                maxWidth.value.toDouble(), regularWidth, regularHeight, largeText, windowArrangement?.last ?: DeliveriesArrangement.Stacked,
            )
        }
        val settingsAsForm = deliveries == DeliveriesArrangement.SideBySide
        val startPx = with(density) { start.roundToPx() }
        val contentPx = constraints.maxWidth - startPx - with(density) { end.roundToPx() }
        val arrangement = passportLayout(maxWidth, hinge?.let { it.start - startPx to it.end - startPx }, contentPx)
        val open = arrangement == PassportLayout.Open
        val opened = shown.isEmpty()
        for (page in PassportPage.visible(state.page, open)) {
            if (page !in shown) shown[page] = if (opened || PeekTheme.reduceMotion) 0 else PAGE_TURN
        }
        // What the bar's glass shows through: the pages, the band behind the bar and the demo's bar, which is measured
        // first so the pages start under it, and which TalkBack reaches first, as the deliveries' list does.
        SubcomposeLayout(Modifier.fillMaxSize().materialBackdrop(backdrop).semantics { isTraversalGroup = true }) { constraints ->
            val loose = constraints.copy(minWidth = 0, minHeight = 0)
            val bar = subcompose(Slot.DemoBar) {
                if (demo) {
                    DemoModeBar(
                        onExit = source::showWelcome,
                        modifier = Modifier.readFirst(),
                        sides = PaddingValues(start = start, end = end),
                    )
                }
            }.map { it.measure(loose) }
            val chromeBottom = barBottom + (bar.maxOfOrNull { it.height } ?: 0).toDp()
            val pages = subcompose(Slot.Pages) {
                Box(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                    PassportColumns(
                        arrangement, top = chromeBottom, bottom = bottom, start = start, end = end,
                        Modifier.fillMaxWidth(),
                    ) { inner ->
                        PassportCover(state, stats)
                        PassportBookPages(state, book, fresh, shown, open, wide = open || inner >= WIDE_PAGE)
                    }
                }
            }.map { it.measure(constraints) }
            val band = subcompose(Slot.Band) {
                if (demo) Box(Modifier.fillMaxWidth().height(barBottom).background(colors.cream)) else SoftTopEdge(barBottom)
            }.map { it.measure(loose) }
            layout(constraints.maxWidth, constraints.maxHeight) {
                pages.forEach { it.place(0, 0) }
                band.forEach { it.place(0, 0) }
                bar.forEach { it.place(0, barBottom.roundToPx()) }
            }
        }
        if (tabBarAtTop) {
            TabRowAccount(end, backdrop, source.session, onAccount = { state.account(settingsAsForm) }, accountTag = PASSPORT_ACCOUNT_BUTTON)
        } else {
            AccountBar(
                localized("passport.title"), rowTop, start, end, backdrop, source.session,
                onAccount = { state.account(settingsAsForm) }, accountTag = PASSPORT_ACCOUNT_BUTTON,
                // Nothing across a fold standing half open (a window too short or narrow for the tab bar at the top):
                // the title over the first page, as the tab bar keeps clear of it.
                titleWidth = hinge?.let { with(density) { it.start.toDp() } },
            )
        }
        Announcements(state.announcement, PASSPORT_ANNOUNCEMENT, state::announced)
    }
    AccountSheetHost(state)
}

private enum class Slot { DemoBar, Pages, Band }

/**
 * The pages' labels and their track ([PassportPageLabels], [PassportTrack]): the Countries page, the Stamps page and the
 * Numbers page. A page's new countries or stamps ([fresh]) drop or press in once it is [shown], after its turn; its label
 * carries the dot until then. [wide]: the Countries page has room for three to a row.
 */
@Composable
private fun PassportBookPages(state: PassportState, book: PassportBook, fresh: Set<String>, shown: Map<Int, Int>, open: Boolean, wide: Boolean) {
    val localizer = LocalLocalizer.current
    LocalLanguage.current
    val pressed = book.stamps.filter { it.earned && it.seenKey in fresh }.map { it.key }
    val landing = book.countries.filter { it.seenKey in fresh }.map { it.code }
    val countriesLead = shown[PassportPage.COUNTRIES]
    val stampsLead = shown[PassportPage.STAMPS]
    val turn = { page: Int -> state.page = page }
    PassportPageLabels(
        localizer.text("passport.pages.label"),
        listOf(localizer.text("passport.pages.countries"), localizer.text("passport.stamps"), localizer.text("passport.pages.numbers")),
        listOf(landing.isNotEmpty() && countriesLead == null, pressed.isNotEmpty() && stampsLead == null, false),
        state.page, turn,
    )
    PassportTrack(
        open, state.page, turn,
        sheets = listOf(
            { PassportCountries(state, book.countries, if (countriesLead != null) landing else emptyList(), wide, countriesLead ?: 0) },
            { PassportStamps(state, book, if (stampsLead != null) pressed else emptyList(), stampsLead ?: 0) },
            { PassportNumbers(state, book) },
        ),
    )
}

/**
 * The passport laid out by [arrangement]: the cover, then 28 under it the pages' labels, then 14 under them the track,
 * or across a fold the cover on its first side and the labels and the track on its second; each side, or the one column,
 * in by 20 at its sides and 18 at its top, under [top] (the bar's foot) and over [bottom] (the tab bar's room), [start]
 * and [end] clear of the window's sides. The column is at most 480 wide, or 880 with the book lying open; the track runs
 * [SHADOW_ROOM] past it on each side for the pages' shadows. [content] is told the labels' width and gives the cover,
 * the labels and the track.
 */
@Composable
private fun PassportColumns(
    arrangement: PassportLayout,
    top: Dp,
    bottom: Dp,
    start: Dp,
    end: Dp,
    modifier: Modifier,
    content: @Composable (inner: Dp) -> Unit,
) {
    SubcomposeLayout(modifier) { constraints ->
        val width = constraints.maxWidth
        val left = start.roundToPx()
        val room = max(0, width - left - end.roundToPx())
        val side = PAGE_SIDE.roundToPx()
        val pageTop = top.roundToPx() + PAGE_TOP.roundToPx()
        val shadow = SHADOW_ROOM.roundToPx()
        // The cover's x and width, and the pages' x and width.
        val (cover, pages) = when (arrangement) {
            is PassportLayout.Fold -> (left to arrangement.first) to (left + arrangement.first + arrangement.gap to arrangement.second)
            else -> {
                val page = min((if (arrangement == PassportLayout.Open) OPEN_WIDTH else SINGLE_WIDTH).roundToPx(), room)
                val x = left + (room - page) / 2
                (x to page) to (x to page)
            }
        }
        val coverInner = max(0, cover.second - 2 * side)
        val inner = max(0, pages.second - 2 * side)
        val items = subcompose(Unit) { content(inner.toDp()) }
        val coverPlaceable = items[0].measure(Constraints(minWidth = coverInner, maxWidth = coverInner))
        val labels = items[1].measure(Constraints(minWidth = inner, maxWidth = inner))
        val track = items[2].measure(Constraints(minWidth = inner + 2 * shadow, maxWidth = inner + 2 * shadow))
        val labelsTop = if (arrangement is PassportLayout.Fold) pageTop else pageTop + coverPlaceable.height + COVER_SPACING.roundToPx()
        val trackTop = labelsTop + labels.height + LABELS_SPACING.roundToPx()
        val contentBottom = max(pageTop + coverPlaceable.height, trackTop + track.height)
        layout(width, max(contentBottom + bottom.roundToPx(), constraints.minHeight)) {
            coverPlaceable.place(cover.first + side, pageTop)
            labels.place(pages.first + side, labelsTop)
            track.place(pages.first + side - shadow, trackTop)
        }
    }
}

/** The window's regular size classes, as the deliveries take them. */
private val REGULAR_WIDTH = 600.dp
private val REGULAR_HEIGHT = 480.dp
private val PAGE_SIDE = 20.dp
private val PAGE_TOP = 18.dp

/** Under the track, whose own 14 under the pages make the 36 the passport keeps at its foot. */
private val PAGE_BOTTOM = 22.dp
private val COVER_SPACING = 28.dp
private val LABELS_SPACING = 14.dp
private val SINGLE_WIDTH = PassportSpread.widest.dp
private val OPEN_WIDTH = 880.dp

/** The window width from which the book lies open. */
private val OPEN_BOOK = 840.dp

/** The labels' width from which a Countries page holds three to a row: a sheet of 560 or more. */
private val WIDE_PAGE = 560.dp + 28.dp
/** The 8 the iPhone keeps over its tab bar, as the deliveries' list does. */
private val BOTTOM_SPACING = 8.dp

/** The test tags of the passport's bar and its news for TalkBack. */
const val PASSPORT_ACCOUNT_BUTTON = "$PASSPORT.accountButton"
const val PASSPORT_ANNOUNCEMENT = "$PASSPORT.announcement"
