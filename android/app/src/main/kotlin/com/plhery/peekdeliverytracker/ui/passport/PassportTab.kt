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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.PassportSpread
import com.plhery.peekdeliverytracker.core.PassportStatistics
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
import kotlin.math.roundToInt

// PassportView.swift's `PassportView` (A-S87 to A-S92, A-S100): the passport tab, its bar and Settings, the cover, the
// stamps, the delivery times and the countries the parcels were first scanned in, each explained in a popover.

/** What PassportView keeps in `@State`: Settings open, the popover open (`expandedCard`), all stamps shown, the touches. */
@Stable
internal class PassportState(
    override val source: DeliveriesSource,
    private val showing: MutableState<Boolean>,
    private val forms: MutableState<Boolean>,
    private val expandedCard: MutableState<String?>,
    showAll: MutableState<Boolean>,
) : AccountHost {
    override val showingAccount: Boolean get() = showing.value
    override val sheetsAsForms: Boolean get() = forms.value

    /** The item whose explanation pops over it ([PassportSelection]), at most one. */
    val expanded: String? get() = expandedCard.value

    var showAllStamps: Boolean by showAll

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
    const val COUNTRIES = "countries"

    fun milestone(id: String) = "milestone.$id"
}

/** The passport's `@State`, kept with the window (a rotation, a fold) and while another tab shows, not past the launch. */
@Composable
internal fun rememberPassportState(source: DeliveriesSource): PassportState {
    val launch = source.launch
    val showing = rememberForLaunch(launch, false)
    val forms = rememberForLaunch(launch, false)
    val expanded = rememberForLaunch<String?>(launch, null)
    val showAll = rememberForLaunch(launch, false)
    return remember(source) { PassportState(source, showing, forms, expanded, showAll) }
}

/** How the passport's pages share the window: `PassportView`'s three layouts. */
internal sealed interface PassportLayout {
    /** An upright fold standing half open: the first page before it, the second after it, each its side's width. */
    data class Fold(val first: Int, val gap: Int, val second: Int) : PassportLayout

    /** `PassportSpread.fits`: two pages of at most 480 either side of a divider. */
    data object Spread : PassportLayout

    /** One page of at most 480, centred. */
    data object Single : PassportLayout
}

/**
 * The layout `PassportView` picks: two pages either side of an upright fold standing half open (`proxy.fold`, as the
 * first open reads it), else the spread where `PassportSpread.fits` the content's [width] with the window's size
 * classes and the text size, else one page. [hinge] is the fold's start and end in pixels from the content's left,
 * [content] the content's width in pixels.
 */
internal fun passportLayout(
    width: Dp,
    windowWidth: Dp,
    windowHeight: Dp,
    largeText: Boolean,
    hinge: Pair<Int, Int>?,
    content: Int,
): PassportLayout = when {
    hinge != null -> PassportLayout.Fold(max(0, hinge.first), max(0, hinge.second - hinge.first), max(0, content - hinge.second))
    PassportSpread.fits(width.value.toDouble(), windowWidth >= REGULAR_WIDTH, windowHeight >= REGULAR_HEIGHT, largeText) -> PassportLayout.Spread
    else -> PassportLayout.Single
}

/**
 * `PassportView`: the passport's pages in a scroll without indicators on the page's ground, under the bar with the
 * inline title and the account button ([AccountBar]) and, in the demo, the demo's bar ([DemoModeBar]); Settings over it
 * from the account button ([AccountSheetHost]), a form where the deliveries would stand side by side. Where the tab bar
 * stands at the top, as the iPad's, there is no title and the account button stands in the tab bar's row
 * ([TabRowAccount]). The pages are laid out by window ([passportLayout]); a change of window
 * keeps the popover open and all the stamps shown, as its `@State` does. Each toggle plays a light impact.
 */
@Composable
internal fun PassportTab(source: DeliveriesSource) {
    val state = rememberPassportState(source)
    val session by source.session.collectAsState()
    val parcels by source.parcels.collectAsState()
    val stats = remember(parcels) { PassportStatistics(parcels) }
    val carrierCount = remember(parcels) { parcels.map { it.carrier }.toSet().size }
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
        val arrangement = passportLayout(
            maxWidth - start - end, maxWidth, maxHeight, largeText, hinge?.let { it.start - startPx to it.end - startPx }, contentPx,
        )
        // What the bar's glass shows through: the pages, the band behind the bar and the demo's bar, which is measured
        // first so the pages start under it, as the deliveries' list does.
        SubcomposeLayout(Modifier.fillMaxSize().materialBackdrop(backdrop)) { constraints ->
            val loose = constraints.copy(minWidth = 0, minHeight = 0)
            val bar = subcompose(Slot.DemoBar) {
                if (demo) DemoModeBar(onExit = source::showWelcome, sides = PaddingValues(start = start, end = end))
            }.map { it.measure(loose) }
            val chromeBottom = barBottom + (bar.maxOfOrNull { it.height } ?: 0).toDp()
            val pages = subcompose(Slot.Pages) {
                Box(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                    PassportPages(
                        arrangement, top = chromeBottom, bottom = bottom, start = start, end = end,
                        Modifier.fillMaxWidth(),
                    ) {
                        PassportCover(state, stats)
                        PassportStamps(state, stats, carrierCount)
                        PassportTimes(state, stats)
                        if (stats.originCountries.isNotEmpty()) PassportCountries(state, stats)
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
 * `page { }` laid out by [arrangement]: the cover and the stamps on the first page, the times and the countries on the
 * second, or all four on one; each page `VStack(spacing: 28)` in by 20 at its sides, 18 at its top and 36 at its foot,
 * under [top] (the bar's foot) and over [bottom] (the tab bar's room), [start] and [end] clear of the window's sides. The
 * spread's divider stands between its pages, in by 18 at its top and its foot. The items keep their place in the
 * composition whatever the layout, so a change of window keeps what each holds (its popover).
 */
@Composable
private fun PassportPages(
    arrangement: PassportLayout,
    top: Dp,
    bottom: Dp,
    start: Dp,
    end: Dp,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    val divider = PeekTheme.colors.divider
    Layout(
        content = {
            content()
            Box(Modifier.background(divider))
        },
        modifier = modifier,
    ) { measurables, constraints ->
        val items = measurables.dropLast(1)
        val seam = measurables.last()
        val width = constraints.maxWidth
        val left = start.roundToPx()
        val room = max(0, width - left - end.roundToPx())
        val side = PAGE_SIDE.roundToPx()
        val spacing = PAGE_SPACING.roundToPx()
        val pageTop = top.roundToPx() + PAGE_TOP.roundToPx()
        val widest = PassportSpread.widest.dp.roundToPx()
        val hairline = 1
        // Each page: its x, its width, and the items on it.
        val pages: List<Triple<Int, Int, List<Int>>> = when (arrangement) {
            is PassportLayout.Fold -> listOf(
                Triple(left, arrangement.first, listOf(0, 1)),
                Triple(left + arrangement.first + arrangement.gap, arrangement.second, items.indices.drop(2)),
            )
            PassportLayout.Spread -> {
                val page = min(widest, (room - hairline) / 2)
                val x = left + (room - (2 * page + hairline)) / 2
                listOf(Triple(x, page, listOf(0, 1)), Triple(x + page + hairline, page, items.indices.drop(2)))
            }
            PassportLayout.Single -> {
                val page = min(widest, room)
                listOf(Triple(left + (room - page) / 2, page, items.indices.toList()))
            }
        }
        val placed = pages.map { (x, page, indices) ->
            val inner = max(0, page - 2 * side)
            val ys = mutableListOf<Int>()
            var y = pageTop
            val placeables = indices.map { index ->
                val placeable = items[index].measure(Constraints(minWidth = inner, maxWidth = inner))
                if (ys.isNotEmpty()) y += spacing
                ys += y
                y += placeable.height
                placeable
            }
            Triple(x + side, placeables.zip(ys), y)
        }
        val contentBottom = placed.maxOf { it.third }
        val height = contentBottom + bottom.roundToPx()
        // The divider from the pages' top to 18 past the longer page's content, as `.padding(.vertical, 18)` has it.
        val seamPlaceable = if (arrangement == PassportLayout.Spread) {
            seam.measure(Constraints.fixed(hairline, max(0, contentBottom + PAGE_BOTTOM.roundToPx() - PAGE_TOP.roundToPx() - pageTop)))
        } else {
            null
        }
        layout(width, max(height, constraints.minHeight)) {
            placed.forEach { (x, column, _) -> column.forEach { (placeable, y) -> placeable.place(x, y) } }
            seamPlaceable?.place(pages[0].first + pages[0].second, pageTop)
        }
    }
}

/** The window's regular size classes, as the deliveries take them. */
private val REGULAR_WIDTH = 600.dp
private val REGULAR_HEIGHT = 480.dp
private val PAGE_SIDE = 20.dp
private val PAGE_TOP = 18.dp
internal val PAGE_BOTTOM = 36.dp
private val PAGE_SPACING = 28.dp
/** The 8 the iPhone keeps over its tab bar, as the deliveries' list does. */
private val BOTTOM_SPACING = 8.dp

/** The test tags of the passport's bar and its news for TalkBack. */
const val PASSPORT_ACCOUNT_BUTTON = "$PASSPORT.accountButton"
const val PASSPORT_ANNOUNCEMENT = "$PASSPORT.announcement"
