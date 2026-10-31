package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierChange
import com.plhery.peekdeliverytracker.core.DeliveredConfetti
import com.plhery.peekdeliverytracker.core.DeliveredHold
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.DeliveriesNavigation
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.DeliveryCardKind
import com.plhery.peekdeliverytracker.core.DeliveryCardPlace
import com.plhery.peekdeliverytracker.core.DeliveryListLayout
import com.plhery.peekdeliverytracker.core.FirstOpenJourney
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.ParcelSectionKind
import com.plhery.peekdeliverytracker.core.ParcelShareModel
import com.plhery.peekdeliverytracker.core.ParcelSort
import com.plhery.peekdeliverytracker.core.ParcelStatusFilter
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.ParcelTrackingNumber
import com.plhery.peekdeliverytracker.core.PullToRefreshModel
import com.plhery.peekdeliverytracker.core.TrackingNumberCopy
import com.plhery.peekdeliverytracker.core.trackingNumbers
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.displayedCarrier
import com.plhery.peekdeliverytracker.core.parcelName
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.zoom.ZoomStack
import com.plhery.peekdeliverytracker.ui.zoom.ZoomTransition
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random
import com.plhery.peekdeliverytracker.core.Rect as CoreRect

/**
 * ParcelListView's navigation: the parcel shown and how it came to be shown ([DeliveriesNavigation]), the parcel kept
 * with the window (`@SceneStorage("deliveries.parcel")`, A-SE03) and whether it still waits for the parcels to open it
 * again, and the arrangement the window last had.
 *
 * Each change of the navigation counts the screen it shows (`now.screen(after: before)`), and only a change does: the
 * window made again finds its navigation as it left it, and counts nothing.
 */
@Stable
internal class DeliveriesNavigator(
    navigation: DeliveriesNavigation = DeliveriesNavigation(),
    savedParcel: UUID? = null,
    restoring: Boolean = true,
    arrangement: DeliveriesArrangement? = null,
) {
    var navigation: DeliveriesNavigation by mutableStateOf(navigation)
        private set

    /** The parcel someone chose, kept for the window. */
    var savedParcel: UUID? by mutableStateOf(savedParcel)
        private set

    /** The saved parcel waits for the parcels before it opens again. */
    var restoring: Boolean by mutableStateOf(restoring)
        private set

    /**
     * The arrangement the window last had, null before its first: what keeps it near the line between them. Taken as
     * the window lays out, so nothing is drawn again for it.
     */
    var arrangement: DeliveriesArrangement? = arrangement
        private set

    /** The card the list showed at its top when the window turned, which it shows there again once laid out anew. */
    var keptCard: UUID? by mutableStateOf(null)

    /** Counts a screen. */
    var view: (String) -> Unit = {}

    /** The page with this id has gone, over the list or beside it, as the iPhone's page goes with its state. */
    var left: (UUID) -> Unit = {}

    /** The page with this id opens, over the list or beside it, as the iPhone pushes a page with its state afresh. */
    var opened: (UUID) -> Unit = {}

    private fun update(next: DeliveriesNavigation) {
        val before = navigation
        if (next == before) return
        navigation = next
        if (next.chosen != before.chosen) savedParcel = next.chosen
        if (next.shown != before.shown) {
            before.shown?.let(left)
            next.shown?.let(opened)
        }
        next.screen(after = before)?.let(view)
    }

    /** Opens the parcel someone asked for: over the list, or beside it in place of the one shown there. */
    fun open(parcelId: UUID) = update(navigation.open(parcelId))

    /**
     * The stack went back to `path`, from the system's back or its gesture. The iPhone holds the parcel for a second
     * after the window turns stacked, against the split view popping it as it goes; nothing pops the stack as the
     * window turns here, so every back goes back.
     */
    fun pop(path: List<UUID>) = update(navigation.copy(path = path))

    /**
     * The window takes `arrangement`: having turned, it keeps the card at the top of the list ([topCard], read only
     * then) at the top. The first arrangement the window has is no turn.
     */
    fun turn(arrangement: DeliveriesArrangement, topCard: () -> UUID?) {
        val last = this.arrangement
        this.arrangement = arrangement
        if (last == null || last == arrangement) return
        keptCard = topCard()
    }

    fun settle(arrangement: DeliveriesArrangement, rows: List<UUID>) = update(navigation.settle(arrangement, rows))

    /** The parcel shown beside the list has gone from it: the card after it shows instead. */
    fun leave(parcelId: UUID, rows: List<UUID>) = update(navigation.leave(parcelId, rows))

    /** Shows the card `offset` on from the one shown, as ↑ and ↓ do; false past either end. */
    fun step(offset: Int, rows: List<UUID>): UUID? {
        val next = navigation.step(offset, rows) ?: return null
        update(next)
        return next.shown
    }

    /**
     * The parcels came: the parcel open when the system closed the app opens again, once they are back. True when it
     * did, and not when someone opened one first.
     */
    fun restore(parcels: List<Parcel>): Boolean {
        if (!restoring) return false
        val parcelId = savedParcel
        if (parcelId == null) {
            restoring = false
            return false
        }
        val restored = navigation.restore(parcelId, parcels.map { it.id }) ?: return false
        val opened = navigation.chosen == null && restored.chosen == parcelId
        update(restored)
        restoring = false
        return opened
    }

    companion object {
        /**
         * Kept with the window. Made again in the same launch, the navigation comes back as it was, as the iPhone's
         * view lives on through a rotation; after the process died, only the parcel someone chose comes back, once the
         * parcels do.
         */
        fun saver(launch: UUID): Saver<DeliveriesNavigator, Any> = listSaver(
            save = { navigator ->
                val navigation = navigator.navigation
                listOf(
                    launch.toString(),
                    navigation.path.joinToString(",") { it.toString() },
                    navigation.shownAutomatically,
                    navigator.savedParcel?.toString(),
                    navigator.restoring,
                    navigator.arrangement?.name,
                )
            },
            restore = { saved ->
                val savedParcel = (saved[3] as String?)?.let(UUID::fromString)
                if (saved[0] != launch.toString()) {
                    DeliveriesNavigator(savedParcel = savedParcel)
                } else {
                    val path = (saved[1] as String).split(",").filter { it.isNotEmpty() }.map(UUID::fromString)
                    val navigation = if (saved[2] as Boolean) shownAutomatically(path.single()) else DeliveriesNavigation(path)
                    DeliveriesNavigator(navigation, savedParcel, saved[4] as Boolean, (saved[5] as String?)?.let(DeliveriesArrangement::valueOf))
                }
            },
        )

        /** The navigation with `parcelId` shown beside the list by itself: what settling on it as the only card gives. */
        private fun shownAutomatically(parcelId: UUID): DeliveriesNavigation =
            DeliveriesNavigation().settle(DeliveriesArrangement.SideBySide, listOf(parcelId))
    }
}

/**
 * `@State` that lives as long as the iPhone's view does: through the window being made again (a rotation, a fold),
 * not past the process, whose next launch starts afresh.
 */
@Composable
internal fun <T> rememberForLaunch(launch: UUID, initial: T): MutableState<T> =
    rememberSaveable(
        launch,
        saver = listSaver(
            save = { listOf(launch.toString(), it.value) },
            restore = { saved ->
                @Suppress("UNCHECKED_CAST")
                mutableStateOf(if (saved[0] == launch.toString()) saved[1] as T else initial)
            },
        ),
    ) { mutableStateOf(initial) }

/**
 * What ParcelListView keeps in `@State` for its search and its filters (A-SE04): whether the search shows, the query,
 * the status, the carrier (its raw value, which a window keeps) and the sort, whether the filters are open and whether
 * sheets show as forms, each held for the launch ([rememberForLaunch]), whether the search field has the focus, and
 * the query last counted.
 */
internal class ListViewStates(
    val showingSearch: MutableState<Boolean>,
    val query: MutableState<String>,
    val status: MutableState<ParcelStatusFilter>,
    val carrier: MutableState<String?>,
    val sort: MutableState<ParcelSort>,
    val showingFilters: MutableState<Boolean>,
    val sheetsAsForms: MutableState<Boolean>,
    val searchFocused: MutableState<Boolean>,
    val countedQuery: MutableState<String?>,
)

@Composable
internal fun rememberListViewStates(launch: UUID): ListViewStates = ListViewStates(
    showingSearch = rememberForLaunch(launch, false),
    query = rememberForLaunch(launch, ""),
    status = rememberForLaunch(launch, ParcelStatusFilter.All),
    carrier = rememberForLaunch<String?>(launch, null),
    sort = rememberForLaunch(launch, ParcelSort.Priority),
    showingFilters = rememberForLaunch(launch, false),
    sheetsAsForms = rememberForLaunch(launch, false),
    searchFocused = rememberForLaunch(launch, false),
    countedQuery = rememberForLaunch<String?>(launch, null),
)

/**
 * What a menu opened over the deliveries that waits for an answer, each held for the launch ([rememberForLaunch]) as
 * the iPhone's `@State` is through a rotation: the delete it asks about, the parcel being named, the carrier editor
 * open, a parcel's alerts open and its share sheet open ([DeleteRequest.saved], [RenameRequest.saved],
 * [CarrierEditorRequest.saved], [AlertsRequest.saved], [ShareRequest.saved]), and the name typed so far.
 */
internal class PromptStates(
    val deleting: MutableState<String?>,
    val renaming: MutableState<String?>,
    val name: MutableState<String>,
    val changingCarrier: MutableState<String?>,
    val alerts: MutableState<String?>,
    val sharing: MutableState<String?>,
)

@Composable
internal fun rememberPromptStates(launch: UUID): PromptStates = PromptStates(
    deleting = rememberForLaunch<String?>(launch, null),
    renaming = rememberForLaunch<String?>(launch, null),
    name = rememberForLaunch(launch, ""),
    changingCarrier = rememberForLaunch<String?>(launch, null),
    alerts = rememberForLaunch<String?>(launch, null),
    sharing = rememberForLaunch<String?>(launch, null),
)

/**
 * A message under the list (`ListToast`): what a check of the parcels found, or that a number was copied. Each one
 * shown is another ([id]), even with the same words, and has its own four seconds.
 */
@Immutable
internal data class ListToast(val id: Int, val text: String, val warning: Boolean = false)

/** News TalkBack is told that nothing on screen says (`AccessibilityNotification.Announcement`), each one once. */
@Immutable
internal data class Announcement(val text: String, val count: Int)

/**
 * What the deliveries' list and the parcel beside it share: the parcels as the list arranges them, the window's
 * arrangement, the navigation, the list's scroll and the archive's shelf; and what ParcelListView keeps for its
 * feedback: the pull, the toast, the error of an action, the card menu open, the delete it asks about and the rename.
 *
 * The list's own work (a refresh, the pull, a card on its way out) runs in `scope`, the tab's, so that drawing the
 * list again, as the window turns, doesn't cancel it, as the iPhone's pull runs in a task of its own. Its changes to
 * the parcels (an archive, a restore, a delete) run in the app's scope ([DeliveriesSource.scope]), which leaving the
 * tab doesn't cancel either.
 */
@Stable
internal class DeliveriesState(
    val source: DeliveriesSource,
    val navigator: DeliveriesNavigator,
    val listState: LazyListState,
    archiveOpenState: MutableState<Boolean>,
    reviewDueState: MutableState<Boolean>,
    sampleShownState: MutableState<Boolean>,
    mapCoverState: MutableState<String?>,
    private val views: ListViewStates,
    private val prompts: PromptStates,
    private val now: () -> Instant,
    val scope: CoroutineScope,
) {
    /** The parcels as the list last received them: the store's, a moment before it shows them. */
    var parcels: List<Parcel> by mutableStateOf(source.parcels.value)

    /** The parcels that have just arrived, which keep their place a moment (DeliveredHold). */
    var hold: DeliveredHold by mutableStateOf(DeliveredHold())

    /** How the list and an open parcel share the window. */
    var arrangement: DeliveriesArrangement by mutableStateOf(DeliveriesArrangement.Stacked)

    var archiveOpen: Boolean by archiveOpenState
    var showingSearch: Boolean by views.showingSearch

    /** What the search field holds, kept as it is while the search hides, as the iPhone keeps it. */
    var query: String by views.query

    /**
     * The search field has the keyboard's focus (`@FocusState searchFocused`), kept with the window so that a rotation
     * finds it focused again.
     */
    var searchFocused: Boolean by views.searchFocused

    /** The query a search was last counted for, kept with the window so that a rotation doesn't count it again. */
    var countedQuery: String? by views.countedQuery

    /** The filters' button's bounds in the window, which their popover points at. */
    var filtersAnchor: Rect by mutableStateOf(Rect.Zero)

    /**
     * An ask for the search field to take the focus (true), or to give it up (false), not yet taken by the field
     * ([takeFocusRequest]): each is taken once, by the field there or by the one that shows next.
     */
    var focusRequest: Boolean? by mutableStateOf(null)
        private set

    /** The field's ask for the focus, which no other field then takes. */
    fun takeFocusRequest(): Boolean? = focusRequest.also { focusRequest = null }

    /** The parcels the list shows, by their status. Each change counts as `filter-status`. */
    var statusFilter: ParcelStatusFilter
        get() = views.status.value
        set(value) {
            if (value == views.status.value) return
            views.status.value = value
            source.action("filter-status")
        }

    /** The carrier whose parcels the list shows, or all. Each change counts as `filter-carrier`. */
    var carrierFilter: CarrierId?
        get() = views.carrier.value?.let(::CarrierId)
        set(value) {
            if (value?.rawValue == views.carrier.value) return
            views.carrier.value = value?.rawValue
            source.action("filter-carrier")
        }

    /** The list's order. Each change counts as `sort-change`. */
    var sort: ParcelSort
        get() = views.sort.value
        set(value) {
            if (value == views.sort.value) return
            views.sort.value = value
            source.action("sort-change")
        }

    /**
     * The filters are open: a sheet over the list, or a popover off its button while sheets show as forms. Opening
     * them counts as `filters-open`.
     */
    var showingFilters: Boolean
        get() = views.showingFilters.value
        set(value) {
            if (value == views.showingFilters.value) return
            views.showingFilters.value = value
            settleSheets()
            if (value) source.action("filters-open")
        }

    /** Sheets show as forms, and the filters in a popover: side by side, as settled while none is open. */
    val sheetsAsForms: Boolean get() = views.sheetsAsForms.value

    /** `settleSheets()`: sheets take the window's arrangement, but only while none is open, so a resize keeps one as it is. */
    fun settleSheets() {
        if (!showingFilters) views.sheetsAsForms.value = arrangement == DeliveriesArrangement.SideBySide
    }

    /** A search, a status, a carrier or a sort other than the list's own (`hasCustomView`). */
    val hasCustomView: Boolean
        get() = query.isNotBlank() || statusFilter != ParcelStatusFilter.All || carrierFilter != null || sort != ParcelSort.Priority

    /** `clearFilters()`: the list as it was, its query emptied too. */
    fun clearFilters() {
        query = ""
        statusFilter = ParcelStatusFilter.All
        carrierFilter = null
        sort = ParcelSort.Priority
    }

    /** `find()` and the search's button: the search shows and its field takes the focus. */
    fun openSearch() {
        showingSearch = true
        focusSearch(true)
    }

    /** `closeSearch()`: the search hides and its field gives up the focus; what it holds stays. */
    fun closeSearch() {
        showingSearch = false
        focusSearch(false)
    }

    /** The field takes the focus, or gives it up. */
    fun focusSearch(focus: Boolean) {
        focusRequest = focus
    }

    /** The filters' button: the field gives up the focus, then the filters open. */
    fun openFilters() {
        focusSearch(false)
        showingFilters = true
    }

    /** The list is looked at, and may show a card moving (`watching`). */
    var watching: Boolean = false

    /** Filtering, sorting and grouping, once for each change of the parcels or of the search and filters. */
    val layout: DeliveryListLayout by derivedStateOf { arrange(hold.arranged(parcels)) }

    /**
     * The cards that are others once the held parcels are let go: those leave without a trace (where the iPhone flies
     * their picture to their new place), the others fade.
     */
    val leaving: Set<UUID> by derivedStateOf {
        if (hold.isEmpty) emptySet() else layout.changing(into = arrange(parcels)).keys
    }

    /** `arrangement(of:)`: Next up is featured only in the list's own view. */
    private fun arrange(parcels: List<Parcel>) = DeliveryListLayout(
        parcels, query = query, status = statusFilter, carrier = carrierFilter, sort = sort, featuresNext = !hasCustomView,
        now = now(),
    )

    /**
     * The parcels the list shows a card or a row for, in its order: the archive's rows count while they show, as they
     * always do in a custom view, which shows them as a group.
     */
    val rows: List<UUID> by derivedStateOf { layout.rows(hasCustomView || archiveOpen) }

    /**
     * The carriers of all the parcels, each once, in the order of their names (`availableCarriers`), for the filters;
     * and the one filtered on, whose parcels may all have gone since, so that the picker shows it chosen as its chip
     * does.
     */
    fun availableCarriers(name: (CarrierId) -> String): List<CarrierId> =
        (parcels.map { it.carrier } + listOfNotNull(carrierFilter)).distinct().sortedWith(compareBy(DisplayNameOrder) { name(it) })

    /** The parcel whose card is marked as the one shown beside the list. */
    val selectedCard: UUID? get() = if (arrangement == DeliveriesArrangement.SideBySide) navigator.navigation.shown else null

    /** Whether the list is on screen: always beside a parcel, and under the stack while nobody opened one over it. */
    val listShown: Boolean
        get() = arrangement == DeliveriesArrangement.SideBySide || navigator.navigation.chosen == null

    /** The parcel as it is now, for a card arranged as it was. */
    fun shown(parcel: Parcel): Parcel = if (hold.isEmpty) parcel else parcels.firstOrNull { it.id == parcel.id } ?: parcel

    /** The parcels came from the store. */
    fun receive(next: List<Parcel>) {
        val held = hold.receive(shown = parcels, next = next, watching = watching)
        // Holding nothing as before is no change: the list isn't arranged again for it.
        if (!held.isEmpty || !hold.isEmpty) hold = held
        parcels = next
        // The parcel open when the system closed the app opens again as it was, without its zoom.
        if (navigator.restore(next) && arrangement == DeliveriesArrangement.Stacked) {
            navigator.navigation.chosen?.let(zoom::showStill)
        }
    }

    /** Nobody looks at the list any more: the parcels just arrived take their new places at once, unseen (`release()`). */
    fun release() {
        if (!hold.isEmpty) hold = hold.release()
    }

    /** Where each card stands, Pip's box and what each card shows, which the paper and the flights start from. */
    val places = DeliveryCardPlaces()

    /** The zoom from a card, an archived row or Pip's box to its parcel's page over the list ([ZoomStack]). */
    val zoom = ZoomTransition()

    /** The cards on their way to their new places, over the list ([DeliveredFlights]). */
    var flights: List<DeliveredFlight> by mutableStateOf(emptyList())
        private set

    /**
     * How far along the flights are, from 0 to 1 and past it as a spring goes: a new one for each letting go, so that
     * the new flights start from 0 on their first frame, whatever the last ones reached.
     */
    var flightProgress: Animatable<Float, AnimationVector1D> by mutableStateOf(Animatable(0f))
        private set

    /** Each letting go counts one, so that a flight still landing doesn't end the next. */
    private var flightRound = 0
    private var flightJob: Job? = null

    /** Each letting go counts one, so that the list's release ends with the last one. */
    private var releases = 0

    /** Each postmark that lands taps success (`.sensoryFeedback(.success, trigger: postmarks)`). */
    var postmarks by mutableIntStateOf(0)
        private set

    /** The paper out of the card just delivered, while it flies ([DeliveredPaper]). */
    var paper: DeliveredPaperBurst? by mutableStateOf(null)
        private set

    /**
     * A parcel seen arriving was the moment to ask for a rating (ReviewPrompt), and the list has not asked yet: kept as
     * `@State` is, through the window being made again and another tab.
     */
    var reviewDue: Boolean by reviewDueState

    /**
     * The held parcels are being let go: the list's items take their places with the release's spring, until the list
     * has been laid out anew ([ListMotion]).
     */
    var releasing = false
        private set

    /** What the list draws with, as it was last drawn: the carriers' liveries, the language and the window's graphics. */
    var identities: CarrierIdentities? = null
    var language: AppLanguage = AppLanguage.En
    var graphics: GraphicsContext? = null
    var density: Density = Density(1f)
    var layoutDirection: LayoutDirection = LayoutDirection.Ltr

    /** Where each burst of paper is thrown from: random, but in the tests ([LocalPaperSeeds]). */
    var seeds: () -> ULong = { Random.nextLong().toULong() }

    /** The postmark landed: a tap, and paper for the parcels that have just arrived (`postmarks += 1`, `throwPaper()`). */
    fun postmark() {
        postmarks++
        throwPaper()
    }

    /**
     * `throwPaper()`: one burst however many have arrived, out of Pip's open box on the first of their cards that
     * shows, from the top of the list down, in that card's colours.
     */
    private fun throwPaper() {
        val identities = identities ?: return
        val cards = parcels.filter { hold.holds(it.id) }
            .mapNotNull { parcel -> places.place(parcel.id)?.let { parcel to it.frame.minY } }
            .sortedBy { it.second }
        for ((parcel, _) in cards) {
            val mouth = places.mouth(parcel.id) ?: continue
            val identity = identities.of(parcel.displayedCarrier, language)
            val count = if (layout.kind(parcel.id) == DeliveryCardKind.Next) DeliveredConfetti.next else DeliveredConfetti.other
            val confetti = DeliveredConfetti(mouth, count, seeds())
            val burst = DeliveredPaperBurst(confetti, DeliveredConfetti.colors(identity.ink, identity.surface, identity.brand))
            paper = burst
            scope.launch {
                delay((confetti.duration * 1000).toLong())
                if (paper === burst) paper = null
            }
            return
        }
    }

    /**
     * `letGo()`: the held parcels take their new places. Each card that becomes another and shows whole is pictured
     * where it stands and flown to where the list now has it, and the other cards make room; a card already in flight
     * simply takes its place, as its picture would be of nothing. The moment a parcel was seen arriving may be the one
     * to ask for a rating, once its card has landed.
     */
    fun letGo() {
        val ids = hold.ids
        if (ids.isEmpty()) return
        if (source.reviewPrompt.isDue(now(), calm = source.reviewSettled.value)) reviewDue = true
        val changes = layout.changing(into = arrange(parcels))
        val visible = places.visible
        val graphics = graphics
        val inFlight = flights.mapTo(HashSet()) { it.id }
        val taking = mutableListOf<DeliveredFlight>()
        if (visible != null && graphics != null) {
            for (parcel in parcels) {
                val change = changes[parcel.id] ?: continue
                if (parcel.id in inFlight) continue
                // Only a card that shows whole can be pictured whole: another simply takes its new place.
                val from = places.place(parcel.id)?.takeIf { visible.contains(it.frame) } ?: continue
                val picture = places.picture(parcel.id, graphics, density, layoutDirection) ?: continue
                taking += DeliveredFlight(parcel.id, parcel, change.was, change.becomes, picture, from, parcel.id in ids, contentShift)
            }
        }
        // The arrivals are drawn last, over the card that takes their place.
        taking.sortBy { it.lifted }
        for (flight in taking) places.forget(flight.id)
        flightJob?.cancel()
        endFlights()
        val round = ++flightRound
        val progress = Animatable(0f)
        flightProgress = progress
        flights = taking
        val release = ++releases
        releasing = true
        hold = hold.release()
        scope.launch {
            // The list is laid out anew in the next frame, and its items start moving with the release's spring.
            withFrameNanos {}
            withFrameNanos {}
            if (release == releases) releasing = false
        }
        if (taking.isEmpty()) return
        flightJob = scope.launch {
            // The list lays its new cards out; one that stays unplaced is beyond the screen, and its picture flies out.
            for (attempt in 0 until PLACE_ATTEMPTS) {
                delay(PLACE_WAIT)
                if (taking.all { places.place(it.id) != null }) break
            }
            if (round != flightRound) return@launch
            val since = contentShift
            for (flight in taking) {
                val from = flight.from
                // Measured as the list now stands, told as it stood when the flight began.
                flight.to = places.place(flight.id)?.let { it.shifted(-(since - flight.shift) / places.density) }
                    ?: DeliveryCardPlace(
                        CoreRect(from.frame.minX, max(from.frame.maxY, visible?.maxY ?: 0.0) + 40, from.frame.width, min(from.frame.height, 102.0)),
                        min(from.title, 50.0),
                    )
            }
            progress.animateTo(1f, Motion.spring(FLIGHT_DURATION, FLIGHT_BOUNCE))
            if (round == flightRound) endFlights()
        }
    }

    /** The flights end, and the pictures they drew go. */
    private fun endFlights() {
        val ended = flights
        if (ended.isEmpty()) return
        flights = emptyList()
        graphics?.let { graphics -> ended.forEach { graphics.releaseGraphicsLayer(it.picture) } }
    }

    /**
     * The list has gone (`.onDisappear { flights = [] }`), or taken another size or another place than the flights
     * measured: its cards in flight are at their places already.
     */
    fun dropFlights() {
        if (flights.isEmpty() && flightJob?.isActive != true) return
        flightRound++
        flightJob?.cancel()
        flightJob = null
        endFlights()
    }

    /** Whether the parcel's card is in flight to its place, where it stays unseen until it lands. */
    fun flies(parcelId: UUID): Boolean = flights.any { it.id == parcelId }

    /**
     * How far the list's content has moved down since the list was made, in pixels: scrolled by a finger or a throw,
     * pulled, and pushed down by the gap held open as it refreshes. The iPhone flies its cards in the scroll content's
     * space, which moves with all of these; here a flight is drawn over the list, moved by as much as this has changed
     * since it began. The list's own scrolls (to its top, to a card) end the flights first.
     */
    val contentShift: Float get() = listPull.scrolled + listPull.shown

    fun openArchive(open: Boolean) {
        if (open == archiveOpen) return
        archiveOpen = open
        if (open) source.action("archive-open")
    }

    /** `showingAdd = true`: Add isn't ported yet, so the button does nothing for now. */
    fun add() = Unit

    /** Add with what another app shared (`sharedDraft = draft; showingAdd = true`): Add isn't ported yet. */
    @Suppress("UNUSED_PARAMETER")
    fun addSharedDraft(draft: String) = Unit

    /** `showingAccount = true`: the account isn't ported yet, so the button does nothing for now. */
    fun account() = Unit

    /** Add with what the clipboard holds (`onAdd(.paste(Clipboard.string))`): Add isn't ported yet. */
    fun pasteFirst() = Unit

    /**
     * Whether the sample parcel Pip holds has been opened (`sample`), kept with the window: its page shows it until the
     * app is closed, as the iPhone keeps it once made.
     */
    private var sampleShown: Boolean by sampleShownState
    private var sampleParcel: Parcel? = null

    /** Opens the sample parcel Pip holds out of the empty deliveries (`FirstOpenJourney.delivered`). */
    fun openSample() {
        sampleShown = true
        navigator.open(FirstOpenJourney.parcelId)
    }

    /**
     * The sample parcel, for the page with `parcelId` (ParcelListView's `page(_:)`): made up as it was first asked for,
     * its scans in the hours before then; none for any other page, or before it was opened.
     */
    fun sample(parcelId: UUID): Parcel? {
        if (parcelId != FirstOpenJourney.parcelId || !sampleShown) return null
        val localizer = localizer ?: return null
        return sampleParcel ?: FirstOpenJourney.delivered(localizer, now()).also { sampleParcel = it }
    }

    /**
     * The parcel the page with `parcelId` shows (ParcelDetailView's `parcel`): the sample, else the parcel in `parcels`
     * with that id, or that had it before its carrier changed (`originalPackageID`).
     */
    fun pageParcel(parcelId: UUID, parcels: List<Parcel>): Parcel? =
        sample(parcelId) ?: parcels.firstOrNull { it.id == parcelId || it.carrierData?.originalPackageId == parcelId }

    /**
     * The parcel of the deliveries the page with `parcelId` acts on, its ••• menu and its keys (ParcelDetailView's
     * `if let parcel, sample == nil`): none for the sample, nor for a parcel gone.
     */
    fun menuParcel(parcelId: UUID?, parcels: List<Parcel> = source.parcels.value): Parcel? =
        parcelId?.takeIf { sample(it) == null }?.let { pageParcel(it, parcels) }

    /** The app's words, in its language: the composition hands them in. */
    var localizer: Localizer? = null

    private fun text(key: String, variables: Map<String, Any> = emptyMap()): String =
        localizer?.text(key, variables) ?: key

    /** The pull's state, which the list drives and the seal draws. */
    val pull = PullToRefreshModel(scope)

    /** The finger's pull on the list, kept here rather than with the list so that a turn of the window keeps it. */
    val listPull = ListPull(this, scope)

    var toast: ListToast? by mutableStateOf(null)
        private set

    private var toasts = 0

    /** Shows a message under the list, and tells TalkBack. */
    fun show(text: String, warning: Boolean = false) {
        toast = ListToast(++toasts, text, warning)
        announce(text)
    }

    /** The message `id` has had its four seconds, unless a newer one took its place. */
    fun dismiss(id: Int) {
        if (toast?.id == id) toast = null
    }

    /** What the toast of an archived parcel says (`app.archivedToast`). */
    fun archivedText(parcel: Parcel): String = text("app.archivedToast", mapOf("name" to parcel.label.ifEmpty { text("common.parcel") }))

    /** What went wrong with an action of a card, said in an alert (`actionError`). */
    var actionError: String? by mutableStateOf(null)

    /**
     * Why the change the page shown asked for failed, said in an alert over it (ParcelDetailView's `errorMessage`): only
     * that page's, and only while it shows ([PageWork]).
     */
    val pageError: String? get() = navigator.navigation.shown?.let(source.pageWork::failure)

    /** The alert closed: a card's, or else the page's. */
    fun closeError() {
        if (actionError != null) {
            actionError = null
        } else {
            navigator.navigation.shown?.let(source.pageWork::dismiss)
        }
    }

    /** The card whose menu is open, and its parcel. */
    var menu: CardMenuRequest? by mutableStateOf(null)

    /** Where TalkBack goes back to once the menu and what it opened have closed: the card the menu came from. */
    var returnFocus: AccessibilityFocus? = null

    /** A card's delete question, answered and still on its way out: TalkBack goes back to the card once it has gone. */
    var deleteGoing: Boolean by mutableStateOf(false)

    /** The cards and archived rows on the list as drawn, by their parcel: a card's delete question points at its card. */
    internal val cardMenus: SnapshotStateMap<UUID, CardMenuSource> = mutableStateMapOf()

    /**
     * The swipes of the cards open on the list as drawn, in the order they opened: the last one's action is kept from
     * the system's back gesture.
     */
    val openSwipes: SnapshotStateList<ArchiveSwipe> = mutableStateListOf()

    /** The parcel a menu asks about before deleting it (`prompts.deleting`), kept with the window. */
    var deleting: DeleteRequest?
        get() = prompts.deleting.value?.let { DeleteRequest.from(it) }
        set(value) {
            prompts.deleting.value = value?.saved
        }

    /**
     * The parcel being named (`prompts.renaming`, the page's `showingTitleEditor`), kept with the window: its alert
     * stands over the deliveries until answered ([saveRename], [cancelRename]).
     */
    var renaming: RenameRequest?
        get() = prompts.renaming.value?.let { RenameRequest.from(it) }
        private set(value) {
            prompts.renaming.value = value?.saved
        }

    /** What the rename alert's field holds (`prompts.name`, the page's `editedTitle`), kept with the window as typed. */
    var renameText: String by prompts.name

    /**
     * The parcel a card's menu asked to name, as it was then (`prompts.renaming`): the name goes to it, even once it has
     * left the list, for the store to refuse. A window made again keeps only its id ([renaming]).
     */
    private var renamingParcel: Parcel? = null

    /** The parcel page's ••• menu, open over the window. */
    var pageMenu: PageMenuRequest? by mutableStateOf(null)

    /** Where the page's ••• stands in the window, which its menu and its delete question come out of. */
    var pageMenuButton: Rect by mutableStateOf(Rect.Zero)

    /**
     * The page with `pageId` waits for a change it asked for to be saved (its `working`): its bell, refresh, switch and
     * Delete wait, and no other change of its starts ([PageWork]).
     */
    fun pageWorking(pageId: UUID): Boolean = source.pageWork.working(pageId)

    /**
     * The page whose map is open over the whole window, kept with the window ([rememberForLaunch]): a rotation, a fold
     * or a change of theme finds the map still open, as the iPhone's cover stays up.
     */
    private var mapCoverPage: String? by mapCoverState

    /** The parcel page's map, open over the whole window (`showingMap`): the page's, made again from its id. */
    var mapCover: MapCoverRequest?
        get() = mapCoverPage?.let { MapCoverRequest(UUID.fromString(it)) }
        set(value) {
            mapCoverPage = value?.pageId?.toString()
        }

    /**
     * A menu, the question it asks, an alert, the page's map or one of its sheets shows over the list, and TalkBack
     * reaches nothing else.
     */
    val modal: Boolean
        get() = menu != null || pageMenu != null || deleting != null || renaming != null || actionError != null || pageError != null ||
            mapCover != null || carrierEditor != null || carrierEditorClosing || deleteGoing || alerts != null || alertsClosing ||
            sharing != null || sharingClosing || source.feedbacks.covering || feedbackSheetShown

    /**
     * Opens the map of the page with `pageId` over the window (`openMap()`), from its globe or its route; TalkBack goes
     * back to `focus` once it has closed.
     */
    fun openMap(pageId: UUID, focus: AccessibilityFocus?) {
        if (modal) return
        source.action("parcel-map-open")
        mapCover = MapCoverRequest(pageId)
        returnFocus = focus
    }

    /**
     * The bell of the page with `pageId` (`store.setMuted(_:muted:)` in the page's `run`): switches the parcel's
     * notifications off or back on, one change at a time, then `saved`, where the bell rings; an alert says why it
     * failed.
     */
    fun muteOnPage(pageId: UUID, parcel: Parcel, muted: Boolean, saved: () -> Unit) = workOnPage(pageId) {
        // Once the app posts notifications (B-W01), a mute cancels the parcel's posted one here.
        source.setMuted(parcel, muted)
        saved()
    }

    /**
     * The sync line's refresh on the page with `pageId` (`store.refresh(_:)` in the page's `run`): asks the parcel's
     * carrier again, one change at a time with the bell, the switch and delete; an alert says why it failed.
     */
    fun refreshOnPage(pageId: UUID, parcel: Parcel) = workOnPage(pageId) { source.refresh(parcel) }

    /**
     * The `run` of the page with `pageId`, for a change that leaves the page open: `work` in the app's scope while the
     * page waits ([pageWorking]), nothing while it waits for another, and an alert over it saying why it failed
     * ([pageError]). Only the page shown asks.
     */
    private fun workOnPage(pageId: UUID, work: suspend () -> Unit) {
        if (navigator.navigation.shown != pageId) return
        source.pageWork.run(pageId, source.scope, ::pageFailure, work = work)
    }

    /**
     * The `run` of the page with `pageId` for a change that leaves the page once saved (an archive, a restore, a
     * delete), as [workOnPage] runs one: the window that shows the page by then leaves it ([leavePage]).
     */
    private fun leaveOnPage(pageId: UUID, change: suspend () -> Unit) {
        if (navigator.navigation.shown != pageId) return
        source.pageWork.run(pageId, source.scope, ::pageFailure, cards = rows, work = change)
    }

    /**
     * What a page's failed change says. What failed in front of the reader keeps a rating from being asked for today,
     * its page gone since or not, as the page's `run` notes it.
     */
    private fun pageFailure(error: Throwable): String {
        source.reviewPrompt.noteBadMoment(now())
        return source.errorMessage(error)
    }

    /**
     * A parcel's alerts open over the window from its page's bell (`showingAlerts`), kept with the window with the switch
     * being saved and whether Done waits for it ([ParcelAlertsHost]).
     */
    var alerts: AlertsRequest?
        get() = prompts.alerts.value?.let { AlertsRequest.from(it) }
        private set(value) {
            prompts.alerts.value = value?.saved
        }

    /** The alerts have closed and their sheet is still on its way down: TalkBack goes back to the bell once it has gone. */
    var alertsClosing: Boolean by mutableStateOf(false)
        private set

    /**
     * The bell of the page with `pageId`, while the account's email is on (`showingAlerts = true`): opens the parcel's
     * alerts, where the account's preferences have come, which the sheet shows. TalkBack goes back to `focus`, the bell,
     * once they have closed.
     */
    fun openAlerts(pageId: UUID, focus: AccessibilityFocus?) {
        if (modal || navigator.navigation.shown != pageId) return
        val parcel = menuParcel(pageId) ?: return
        if (source.deliveryEmail.value?.bellOpensAlerts(parcel) != true || source.notificationPreferences.value == null) return
        returnFocus = focus
        alertsClosing = false
        alerts = AlertsRequest(UUID.randomUUID(), pageId)
    }

    /**
     * A switch of the alerts flipped to `on` (`flip(_:to:)`): the parcel's notifications or its email, saved for this
     * parcel in the app's scope ([PageWork], by the request's id), one at a time; the switch shows `on` until the
     * server has answered, and an alert over the sheet says why it failed.
     */
    fun flipAlert(alert: ParcelAlert, on: Boolean, parcel: Parcel) {
        val request = alerts ?: return
        if (source.pageWork.working(request.id)) return
        alerts = request.copy(saving = AlertSave(alert, on))
        source.pageWork.run(request.id, source.scope, source::errorMessage) {
            when (alert) {
                ParcelAlert.Notifications -> source.setMuted(parcel, muted = !on)
                ParcelAlert.Email -> source.setEmailMuted(parcel, muted = !on)
            }
        }
    }

    /**
     * The switch being saved has been answered (`flip`'s end): saved, the alerts close if Done waited for it; failed,
     * Done waits no more.
     */
    fun alertSaved() {
        val request = alerts?.takeIf { it.saving != null } ?: return
        if (source.pageWork.working(request.id)) return
        val failed = source.pageWork.failure(request.id) != null
        alerts = request.copy(saving = null, closing = false)
        if (request.closing && !failed) closeAlerts()
    }

    /** Done or ✕ (`close()`): the alerts close, once the switch being saved has been answered, so a failure is never missed. */
    fun doneWithAlerts() {
        val request = alerts ?: return
        if (source.pageWork.working(request.id)) alerts = request.copy(closing = true) else closeAlerts()
    }

    /**
     * A tap outside, the back, a drag down, Done, or the bell no longer opening them (`dismiss()`): the alerts close, and
     * forget what they saved. Their sheet keeps the parcel as it goes down ([alertsClosed]).
     */
    fun closeAlerts() {
        val request = alerts ?: return
        source.pageWork.forget(request.id)
        alerts = null
        alertsClosing = true
    }

    /** The alerts' sheet has gone. */
    fun alertsClosed() {
        if (alerts == null) alertsClosing = false
    }

    /**
     * The answer to the delivery email's offer on the page with `pageId` (`answerEmailOffer(_:)`), counted, then saved on
     * the server in the app's scope, one at a time ([PageWork.answerEmailOffer]).
     */
    fun answerEmailOffer(pageId: UUID, accepted: Boolean) {
        if (source.pageWork.emailOffer(pageId) is EmailOfferPhase.Saving) return
        source.action(if (accepted) "email-offer-accept" else "email-offer-decline")
        source.pageWork.answerEmailOffer(pageId, accepted, source.scope) { source.setEmailOnDelivery(accepted) }
    }

    /**
     * A parcel's share sheet open over the window (a page's `showingShare`, the list's `prompts.sharing`), kept with the
     * window: on the parcel of the page that asked, or of a card's menu ([ParcelShareHost]). What it shows and does is
     * the source's for as long as it is open ([ParcelShares]).
     */
    var sharing: ShareRequest?
        get() = prompts.sharing.value?.let { ShareRequest.from(it) }
        private set(value) {
            prompts.sharing.value = value?.saved
        }

    /** The share sheet has closed and is still on its way down: TalkBack goes back once it has gone. */
    var sharingClosing: Boolean by mutableStateOf(false)
        private set

    /**
     * A page's feedback sheet is up in this window, or still on its way down until it has gone. The window keeps it, as
     * it keeps [sharingClosing]: one made again once the question has closed its sheet starts uncovered, whatever the
     * sheet it had was doing ([ParcelFeedbacks.covering] says whether one is open).
     */
    var feedbackSheetShown: Boolean by mutableStateOf(false)

    /**
     * The parcel the share sheet `request` shows, with its `work`: the page's as it is now (`if let parcel`), none once
     * gone, or the one the card's menu found, as it was then, even once it has left the list and through the window made
     * again ([ParcelShareSheetWork.parcel]).
     */
    fun sharedParcel(request: ShareRequest, work: ParcelShareSheetWork, parcels: List<Parcel>): Parcel? {
        val pageId = request.pageId ?: return work.parcel
        return pageParcel(pageId, parcels)
    }

    /**
     * The share button of the page with `pageId`'s pass: a parcel followed through someone else's link hands that link
     * out as it is, through the system's share sheet (`ShareLink(item: ParcelLinkRoute.address(id: readerLink))`),
     * counting nothing, as ShareLink does; one of the reader's own opens its share sheet (`showingShare = true`).
     * TalkBack goes back to `focus`, the button, once the sheet has closed.
     */
    fun shareOnPage(pageId: UUID, focus: AccessibilityFocus?) {
        if (modal || navigator.navigation.shown != pageId) return
        val parcel = menuParcel(pageId) ?: return
        val readerLink = source.viewerLink(parcel)
        if (readerLink != null) {
            handOut(ParcelLinkRoute.address(id = readerLink, baseUrl = source.linkBaseUrl), null)
            return
        }
        if (focus != null) returnFocus = focus
        openShare(ShareRequest(UUID.randomUUID(), parcel.id, pageId), parcel)
    }

    /**
     * Opens the share sheet of `request` on `parcel` (`ParcelShareSheet(parcel:client:demo:)`): its model, which starts
     * reading the parcel's link at once (`.task { await model.load() }`), held by the source while the sheet is open.
     */
    private fun openShare(request: ShareRequest, parcel: Parcel) {
        sharing?.let { source.shares.forget(it.id) }
        sharingClosing = false
        val model = ParcelShareModel(parcel.id, parcel.label, source.shareClient(parcel), source.shareNotes, source.linkBaseUrl)
        val work = ParcelShareSheetWork(model, demo = source.isDemo, scope = source.scope, parcel = parcel.takeIf { request.pageId == null })
        source.shares.open(request.id, work)
        sharing = request
    }

    /**
     * ✕, a tap outside, the back, a drag down, or its page gone (`dismiss()`): the share sheet closes. Its reading of
     * the link stops; a switch on its way to the service still gets there, as the iPhone's `Task` does. Its sheet keeps
     * what it showed as it goes down ([shareClosed]).
     */
    fun closeShare() {
        val request = sharing ?: return
        source.shares.forget(request.id)
        sharing = null
        sharingClosing = true
    }

    /** The share sheet has gone. */
    fun shareClosed() {
        if (sharing != null) return
        sharingClosing = false
    }

    /**
     * Hands `address` to the system's share sheet (`UIActivityViewController`), the composition's: whether it could be
     * shown. `chosen` runs once the reader has chosen the app it goes to, even after the window has gone; never where
     * they back out.
     */
    var handOut: (address: String, chosen: (() -> Unit)?) -> Boolean = { _, _ -> false }

    /**
     * The carrier editor open over the window (a page's `carrierEditor`, the list's `prompts.changingCarrier`), kept
     * with the window: on the parcel of the page that asked, or of a card's menu ([CarrierEditorHost]).
     */
    var carrierEditor: CarrierEditorRequest?
        get() = prompts.changingCarrier.value?.let { CarrierEditorRequest.from(it) }
        private set(value) {
            prompts.changingCarrier.value = value?.saved
        }

    /** The carrier editor has closed and its sheet is still on its way down: TalkBack goes back once it has gone. */
    var carrierEditorClosing: Boolean by mutableStateOf(false)
        private set

    /**
     * The editor whose change was saved, by its request's id, while its sheet goes down: it still says it saves, with
     * Cancel off, as `save()` leaves `saving` on once the change is saved and the sheet dismissed.
     */
    var carrierEditorSaved: UUID? by mutableStateOf(null)
        private set

    /**
     * The parcel a card's menu asked to change, as it was then (`prompts.changingCarrier`): the editor works on it, even
     * once it has left the list or taken another id, as ParcelListView's sheet does, until its sheet has gone. A window
     * made again keeps only its id, and holds the parcel it finds by it from then on.
     */
    private var changingParcel: Parcel? = null

    /**
     * The parcel the carrier editor `request` works on: the page's as it is now (`if let parcel`), none once gone, or the
     * one the card's menu found.
     */
    fun carrierEditorParcel(request: CarrierEditorRequest, parcels: List<Parcel>): Parcel? {
        val pageId = request.pageId
        if (pageId != null) return pageParcel(pageId, parcels)
        val parcel = changingParcel?.takeIf { it.id == request.parcelId } ?: parcels.firstOrNull { it.id == request.parcelId }
        if (parcel != null) changingParcel = parcel
        return parcel
    }

    /**
     * Opens the carrier editor on the page with `pageId` (`carrierEditor = CarrierEditorRequest(initialCarrier:)`), on
     * `initialCarrier` where a prompt names one. TalkBack goes back to `focus`, the prompt or the mark that asked, once
     * the editor has closed; from the ••• menu, back to the •••.
     */
    fun editCarrier(pageId: UUID, initialCarrier: CarrierId? = null, focus: AccessibilityFocus? = null) {
        if (focus != null) returnFocus = focus
        openCarrierEditor(CarrierEditorRequest(UUID.randomUUID(), pageId, pageId, initialCarrier))
    }

    private fun openCarrierEditor(request: CarrierEditorRequest, parcel: Parcel? = null) {
        carrierEditor?.let { source.pageWork.forget(it.id) }
        changingParcel = parcel
        carrierEditorSaved = null
        carrierEditorClosing = false
        carrierEditor = request
    }

    /**
     * The editor's Save (`save()`), for `change`: the carrier, the link and the postcode it needs, the postcode's
     * country and a provider's postcode go to the store, in the app's scope while the editor waits, which a window made
     * again still hears of ([PageWork], by the request's id). Saved, the editor closes ([leavePage]); failed, it says
     * why in its own words, as `localizer.errorMessage(error)` does, for another try.
     */
    fun saveCarrier(request: CarrierEditorRequest, change: CarrierChange) {
        if (!change.canSave || carrierEditor?.id != request.id) return
        val parcel = change.parcel
        source.pageWork.run(request.id, source.scope, source::errorMessage, cards = emptyList()) {
            source.changeCarrier(
                parcel, change.selectedCarrier, change.sentTrackingUrl, change.sentPostcode, change.sentPostcodeCountry,
                change.normalizedProviderPostcode,
            )
        }
    }

    /**
     * Cancel, a tap outside, the back, a drag down, or its change saved: the editor closes, and forgets its change. Its
     * sheet keeps the parcel as it goes down ([carrierEditorClosed]).
     */
    fun closeCarrierEditor() {
        val request = carrierEditor ?: return
        source.pageWork.forget(request.id)
        carrierEditor = null
        carrierEditorClosing = true
    }

    /** The editor's sheet has gone, and with it the parcel it showed. */
    fun carrierEditorClosed() {
        if (carrierEditor != null) return
        carrierEditorClosing = false
        carrierEditorSaved = null
        changingParcel = null
    }

    /**
     * The page's switch to the carrier its parcel's number names (`switchCarrier(_:to:)`): one tap moves the parcel to
     * `carrier` (`store.changeCarrier` in the page's `run`), one change at a time, and an alert says why it failed. A
     * carrier that needs the postcode opens the editor on it instead, TalkBack coming back to `focus`, the switch, once
     * the editor has closed.
     */
    fun switchCarrierOnPage(pageId: UUID, parcel: Parcel, carrier: CarrierId, focus: AccessibilityFocus? = null) {
        if (CarrierCatalog.shared.requirements(carrier, parcel.trackingNumber).isNotEmpty()) {
            editCarrier(pageId, carrier, focus)
            return
        }
        workOnPage(pageId) { source.changeCarrier(parcel, carrier) }
    }

    /** Where TalkBack goes back to once the filters have closed: their button. */
    val filtersButton = AccessibilityFocus()

    var announcement: Announcement? by mutableStateOf(null)
        private set

    fun announce(text: String) {
        announcement = Announcement(text, (announcement?.count ?: 0) + 1)
    }

    /** TalkBack has had the news: the same words said again later are news again. */
    fun announced(announcement: Announcement) {
        if (this.announcement == announcement) this.announcement = announcement.copy(text = "")
    }

    /**
     * Copies a tracking number to the clipboard, the composition's; true when the app says so itself, where the system
     * doesn't.
     */
    var copy: (text: String) -> Boolean = { false }

    /** The number a parcel's page last copied, which its row says it copied (`copiedNumber`): null once its time is up. */
    var copiedNumber: CopiedNumber? by mutableStateOf(null)
        private set

    /**
     * Copies one of the numbers of the page with `pageId` (the page's `copy(_:carrier:)`): counted, on the clipboard as
     * its carrier's site takes it with the success tap ([copyNumber]), and said on its row for 2 s from the last copy
     * (B-AN09), of that number or another. The iPhone's task ends the word 2 s after an earlier copy of the same
     * number; this doesn't.
     */
    fun copyOnPage(pageId: UUID, entry: ParcelTrackingNumber) {
        copyNumber(entry)
        copiedNumber = CopiedNumber(pageId, entry.number)
        copiedClearing?.cancel()
        copiedClearing = scope.launch {
            delay(COPIED_DURATION)
            copiedNumber = null
        }
    }

    private var copiedClearing: Job? = null

    /** `refreshFromPull()`: holds the pull open until every parcel has been checked, then shows the result on the seal. */
    fun refreshFromPull() {
        scope.launch {
            pull.begin(text("app.refreshing"))
            val result = coroutineScope {
                // A check that answers at once still shows the arrow turning.
                val turning = launch { delay(PULL_MINIMUM) }
                source.checkAllParcels().also { turning.join() }
            }
            when (result) {
                is ParcelStore.RefreshResult.Failed -> {
                    finishPull(succeeded = false, label = text("detail.checkFailed"))
                    delay(PULL_RESULT)
                    show(result.message, warning = true)
                }
                ParcelStore.RefreshResult.StillChecking -> {
                    // The checks keep running, so the arrow keeps turning under the message.
                    val label = text("app.refreshTimeout")
                    pull.update(label)
                    announce(label)
                    delay(PULL_RESULT)
                }
                null -> Unit
                else -> {
                    finishPull(succeeded = true, label = text(result.messageKey))
                    delay(PULL_SUCCESS)
                }
            }
            pull.settle()
        }
    }

    private fun finishPull(succeeded: Boolean, label: String) {
        pull.finish(succeeded, label)
        announce(label)
    }

    /** `refreshFromButton()`: the button turns while the parcels are checked, then a toast says how it went. */
    fun refreshFromButton() {
        if (source.refreshing.value) return
        scope.launch {
            when (val result = source.checkAllParcels()) {
                is ParcelStore.RefreshResult.Failed -> show(result.message, warning = true)
                null -> Unit
                else -> show(text(result.messageKey))
            }
        }
    }

    /**
     * Archives the parcel once `after` is over (a swiped card's way out, done or cut short), in the app's scope;
     * whether it left the list, so a swiped card only comes back after a failure.
     *
     * Its Undo then lasts seven seconds, counted in the app's scope too, as the iPhone counts them in a task of its
     * own: leaving the tab or the window turning doesn't give it seven more. Each archive counts for its own Undo, so
     * one after it, even of the same parcel put back in between, keeps its whole seven.
     */
    fun archive(parcel: Parcel, after: Job? = null): Deferred<Boolean> = source.scope.async {
        after?.join()
        try {
            saveArchive(parcel)
        } catch (error: CancellationException) {
            throw error
        } catch (error: Exception) {
            actionError = source.errorMessage(error)
            return@async false
        }
        true
    }

    /** `store.archive(_:)`, then the seven seconds of its Undo ([archive]), from a card or from its page. */
    private suspend fun saveArchive(parcel: Parcel) {
        source.archive(parcel)
        source.undoParcel.value?.let { undo ->
            announce(archivedText(undo))
            source.scope.launch {
                delay(UNDO_DURATION)
                source.forgetUndo(undo)
            }
        }
    }

    /**
     * Brings a parcel back from the archive, in the app's scope: the card's Restore and the Undo of its toast. Whether
     * it came back.
     */
    fun restore(parcel: Parcel): Deferred<Boolean> = source.scope.async {
        try {
            source.restore(parcel)
            true
        } catch (error: CancellationException) {
            throw error
        } catch (error: Exception) {
            actionError = source.errorMessage(error)
            false
        }
    }

    /** Deletes the parcel a card's menu asked about, and leaves its page where one shows it ([leave]). */
    fun delete(parcel: Parcel) {
        val cards = rows
        source.scope.launch {
            try {
                source.permanentlyDelete(parcel)
                leave(parcel, cards)
            } catch (error: CancellationException) {
                throw error
            } catch (error: Exception) {
                actionError = source.errorMessage(error)
            }
        }
    }

    /**
     * The delete question was answered with Delete: the card's parcel goes; or, asked from a page that still shows, the
     * parcel it shows by then, in the page's `run` (`delete(_:)`), which leaves the page once deleted.
     */
    fun confirmDelete(request: DeleteRequest) {
        val pageId = request.pageId
        if (pageId == null) {
            source.parcels.value.firstOrNull { it.id == request.parcelId }?.let(::delete)
            return
        }
        val parcel = menuParcel(pageId) ?: return
        leaveOnPage(pageId) { source.permanentlyDelete(parcel) }
    }

    /**
     * Asks for `parcel`'s new name, from its own (`editedTitle = parcel.label`, `prompts.name = parcel.label`): from a
     * card's menu, or from the page with `pageId`, which then saves it.
     */
    private fun rename(parcel: Parcel, pageId: UUID?) {
        renameText = parcel.label
        renamingParcel = parcel
        renaming = RenameRequest(parcel.id, pageId)
    }

    /**
     * A tap on the name on the pass of the page with `pageId`, as its ••• menu's Rename (`chose(.rename, on:)`):
     * TalkBack goes back to `focus` once the alert has closed.
     */
    fun renameOnPage(pageId: UUID, focus: AccessibilityFocus?) {
        if (modal) return
        val parcel = menuParcel(pageId) ?: return
        returnFocus = focus
        rename(parcel, pageId)
    }

    /**
     * The rename alert's Save: the name typed, trimmed and cut to 80 characters ([parcelName]), goes to the store
     * (`store.rename(_:label:)`) for the parcel as it is now. Asked from a page, in its `run`: one change at a time, and
     * an alert over the page says why it failed. Asked from a card, in the app's scope, for the parcel the menu found
     * ([renamingParcel]), a failure said in the list's alert (`actionError`): one gone meanwhile is the store's to
     * refuse, as it is ParcelListView's, and is said missing where a window made again has forgotten it.
     */
    fun saveRename() {
        val request = renaming ?: return
        val asked = renamingParcel?.takeIf { it.id == request.parcelId }
        renaming = null
        renamingParcel = null
        val label = parcelName(renameText)
        val pageId = request.pageId
        if (pageId != null) {
            val parcel = menuParcel(pageId) ?: return
            workOnPage(pageId) { source.rename(parcel, label) }
            return
        }
        val parcel = asked ?: source.parcels.value.firstOrNull { it.id == request.parcelId }
        source.scope.launch {
            try {
                source.rename(parcel ?: throw DeliveryApiError.ParcelMissing(), label)
            } catch (error: CancellationException) {
                throw error
            } catch (error: Exception) {
                actionError = source.errorMessage(error)
            }
        }
    }

    /** The rename alert's Cancel, or the back gesture: the name stays as it was. */
    fun cancelRename() {
        renaming = null
        renamingParcel = null
    }

    /**
     * Leaves the page showing `parcel`, by its id or the one it had before, if one does (ParcelDetailView's `leave()`):
     * over the list it goes back into its card, as `dismiss()` pops it; beside the list the next of `cards` shows in its
     * place, or the one before at the end, as its `release` does ([DeliveriesNavigation.leave]).
     */
    fun leave(parcel: Parcel, cards: List<UUID>) {
        val page = navigator.navigation.shown ?: return
        if (page == parcel.id || parcel.carrierData?.originalPackageId == page) leaveShown(cards)
    }

    /**
     * The page with `pageId` saved a change that leaves it ([PageWork.leaving]): it leaves, if it shows in this window.
     * The carrier editor's change, saved by its own id, closes this window's editor instead, saying it saves until its
     * sheet has gone. Another window's is left for it to take, as the process's windows share the work.
     */
    fun leavePage(pageId: UUID, cards: List<UUID>) {
        if (carrierEditor?.id == pageId) {
            source.pageWork.left(pageId)
            carrierEditorSaved = pageId
            closeCarrierEditor()
            return
        }
        if (navigator.navigation.shown != pageId) return
        source.pageWork.left(pageId)
        leaveShown(cards)
    }

    private fun leaveShown(cards: List<UUID>) {
        val navigation = navigator.navigation
        when (arrangement) {
            DeliveriesArrangement.Stacked -> navigator.pop(navigation.path.dropLast(1))
            DeliveriesArrangement.SideBySide -> navigation.shown?.let { navigator.leave(it, cards) }
        }
    }

    /**
     * What the ••• menu of the page with `pageId` asked of its parcel (ParcelDetailView's `chose(_:on:)`). The page is
     * open already. Naming the parcel asks for its new name; changing its carrier opens the carrier editor. Copying puts
     * the first number on the clipboard and says so on its row. Archiving and restoring save the change, then leave the
     * page; deleting asks first. Only one change of the page's is saved at a time, and an alert over it says why one
     * failed. The menu offers no Share, as the iPhone's doesn't (only the iPad's menu bar does): the pass's button shares.
     */
    fun choseOnPage(pageId: UUID, item: ParcelMenuItem, parcel: Parcel) {
        when (item) {
            ParcelMenuItem.Open, ParcelMenuItem.Share -> Unit
            ParcelMenuItem.ChangeCarrier -> editCarrier(pageId)
            ParcelMenuItem.Rename -> rename(parcel, pageId)
            ParcelMenuItem.CopyTrackingNumber -> parcel.trackingNumbers.firstOrNull()?.let { copyOnPage(pageId, it) }
            ParcelMenuItem.Archive -> leaveOnPage(pageId) { saveArchive(parcel) }
            ParcelMenuItem.Restore -> leaveOnPage(pageId) { source.restore(parcel) }
            ParcelMenuItem.Delete -> deleting = DeleteRequest(parcel.id, pageId)
        }
    }

    /**
     * Puts the parcel's first number on the clipboard ([copyNumber]): whether the app says so itself, where the system
     * doesn't; null with no number.
     */
    private fun copyTrackingNumber(parcel: Parcel): Boolean? = parcel.trackingNumbers.firstOrNull()?.let(::copyNumber)

    /**
     * `TrackingNumberCopy.copy`: counts the copy and puts the number on the clipboard as its carrier's site takes it,
     * with the success tap ([copy]); whether the app says so itself, where the system doesn't.
     */
    private fun copyNumber(entry: ParcelTrackingNumber): Boolean {
        source.action("parcel-copy-tracking")
        return copy(TrackingNumberCopy.text(entry.number, entry.carrier))
    }

    /**
     * What a card's menu asked of its parcel. Sharing it opens its share sheet on the parcel as the menu found it
     * (`prompts.sharing = parcel`). Archiving it is the same as swiping it away, Undo and all.
     */
    fun chose(item: ParcelMenuItem, parcel: Parcel) {
        when (item) {
            ParcelMenuItem.Open -> {
                // TalkBack goes on to the parcel's page rather than back to its card.
                returnFocus = null
                navigator.open(parcel.id)
            }
            ParcelMenuItem.Rename -> rename(parcel, pageId = null)
            ParcelMenuItem.Share -> openShare(ShareRequest(UUID.randomUUID(), parcel.id, pageId = null), parcel)
            ParcelMenuItem.ChangeCarrier ->
                openCarrierEditor(CarrierEditorRequest(UUID.randomUUID(), parcel.id, pageId = null, initialCarrier = null), parcel)
            ParcelMenuItem.CopyTrackingNumber -> {
                val says = copyTrackingNumber(parcel) ?: return
                val copied = text("detail.copied")
                // Where the system shows the copy itself, TalkBack still hears of it.
                if (says) show(copied) else announce(copied)
            }
            ParcelMenuItem.Archive -> archive(parcel)
            ParcelMenuItem.Restore -> restore(parcel)
            ParcelMenuItem.Delete -> deleting = DeleteRequest(parcel.id, pageId = null)
        }
    }

    /** Where a card stands in its item of the list (`places.stand(id, at:)`), which its flight and Pip's sight start from. */
    fun stand(parcelId: UUID, frame: Rect) = places.stand(parcelId, frame)

    /** The keys of the list's items, in its order, as last drawn ([cardKey] for a card). */
    var itemKeys: List<Any> = emptyList()

    /**
     * The card at the top of the list: the first that shows below the list's top, once a card above it has scrolled
     * away. None while the list shows its first card, which keeps the list at its top as the window turns.
     */
    fun topCard(): UUID? {
        val item = listState.layoutInfo.visibleItemsInfo.firstOrNull { cardId(it.key) != null && it.offset + it.size > 0 } ?: return null
        if (itemKeys.take(item.index).none { cardId(it) != null }) return null
        return cardId(item.key)
    }

    /** The list's item that shows the parcel: its card, or the archive's shelf for an archived one. */
    private fun itemIndex(parcelId: UUID): Int? {
        itemKeys.indexOfFirst { cardId(it) == parcelId }.takeIf { it >= 0 }?.let { return it }
        if (layout.sections.none { it.kind == ParcelSectionKind.Archived && it.parcels.any { parcel -> parcel.id == parcelId } }) return null
        return itemKeys.indexOf(ARCHIVE_KEY).takeIf { it >= 0 }
    }

    suspend fun scrollToTop(reduceMotion: Boolean) {
        dropFlights()
        if (reduceMotion) listState.scrollToItem(0) else listState.animateScrollToItem(0)
    }

    /** `proxy.scrollTo(id)`: the card scrolled just far enough to show whole, on a snappy 0.25 s. */
    suspend fun bringIntoView(parcelId: UUID, reduceMotion: Boolean) {
        dropFlights()
        val info = listState.layoutInfo
        val index = itemIndex(parcelId) ?: return
        val item = info.visibleItemsInfo.firstOrNull { it.index == index }
        if (item == null) {
            if (reduceMotion) listState.scrollToItem(index) else listState.animateScrollToItem(index)
            return
        }
        val end = info.viewportEndOffset - info.afterContentPadding
        val delta = when {
            item.offset < 0 -> item.offset
            item.offset + item.size > end -> minOf(item.offset, item.offset + item.size - end)
            else -> return
        }.toFloat()
        if (reduceMotion) listState.scrollBy(delta) else listState.animateScrollBy(delta, Motion.snappy(0.25))
    }

    /** The card the list showed at its top when the window turned, at its top again once the list is laid out anew. */
    suspend fun keepCard(parcelId: UUID) {
        val index = itemIndex(parcelId) ?: return
        dropFlights()
        listState.scrollToItem(index)
    }
}

/** The number a page with `pageId` copied, which its row says it copied. */
@Immutable
internal data class CopiedNumber(val pageId: UUID, val number: String)

/**
 * The carrier editor asked for on the parcel with `parcelId`, from the page with `pageId` or a card's menu, opening on
 * `initialCarrier` where a prompt names one (`CarrierEditorRequest`): each request another ([id]), as each of the
 * iPhone's has its own id.
 */
@Immutable
internal data class CarrierEditorRequest(val id: UUID, val parcelId: UUID, val pageId: UUID?, val initialCarrier: CarrierId?) {
    /** As a window keeps it: the ids, the page's or "-", and the carrier or "-", a space between. */
    val saved: String get() = listOf(id, parcelId, pageId ?: "-", initialCarrier?.rawValue ?: "-").joinToString(" ")

    companion object {
        fun from(saved: String): CarrierEditorRequest {
            val (id, parcelId, pageId, carrier) = saved.split(" ")
            return CarrierEditorRequest(
                UUID.fromString(id), UUID.fromString(parcelId), pageId.takeIf { it != "-" }?.let(UUID::fromString),
                carrier.takeIf { it != "-" }?.let(::CarrierId),
            )
        }
    }
}

/**
 * Swift's `<` on the carriers' names: code unit by code unit, near enough to Swift's comparison of their Unicode
 * scalars for the names of carriers.
 */
private val DisplayNameOrder = Comparator<String> { a, b -> a.compareTo(b) }

/**
 * A card's key in the list: its parcel within the group that draws it (Next up, the flagged, the others on their way,
 * a section). A card that moves to another group is another card, as the iPhone's are in their own `ForEach`: it
 * leaves its place and comes in at the new one rather than sliding there. Strings, which a window keeps.
 */
internal fun cardKey(group: String, parcelId: UUID): String = "$group/$parcelId"

/** The parcel whose card an item of the list is, if it is one. */
internal fun cardId(key: Any?): UUID? {
    val text = key as? String ?: return null
    val slash = text.lastIndexOf('/')
    if (slash < 0) return null
    return runCatching { UUID.fromString(text.substring(slash + 1)) }.getOrNull()
}

/** The archive's shelf's key in the list. */
internal const val ARCHIVE_KEY = "archive"

/** How long an archived parcel's Undo stays, in milliseconds. */
private const val UNDO_DURATION = 7_000L

/** How long a page's number says it was copied, in milliseconds. */
private const val COPIED_DURATION = 2_000L

/** A place moved up or down the list by `dy` points. */
private fun DeliveryCardPlace.shifted(dy: Float): DeliveryCardPlace = copy(frame = frame.offsetBy(0.0, dy.toDouble()))

/** `.spring(duration: 0.62, bounce: 0.16)`: the flights' spring. */
private const val FLIGHT_DURATION = 0.62
private const val FLIGHT_BOUNCE = 0.16

/** The list has 4 × 30 ms to lay the new cards out before one still unplaced is taken for one beyond the screen. */
private const val PLACE_ATTEMPTS = 4
private const val PLACE_WAIT = 30L

/** The pull holds its arrow turning this long at least, and its result this long, in milliseconds. */
private const val PULL_MINIMUM = 900L
private const val PULL_RESULT = 1100L
private const val PULL_SUCCESS = 700L
