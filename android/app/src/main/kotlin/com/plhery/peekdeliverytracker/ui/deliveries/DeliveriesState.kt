package com.plhery.peekdeliverytracker.ui.deliveries

import android.os.SystemClock
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import com.plhery.peekdeliverytracker.core.DeliveredHold
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.DeliveriesNavigation
import com.plhery.peekdeliverytracker.core.DeliveryListLayout
import com.plhery.peekdeliverytracker.core.ParcelSectionKind
import com.plhery.peekdeliverytracker.core.ParcelSort
import com.plhery.peekdeliverytracker.core.ParcelStatusFilter
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.theme.Motion
import java.time.Instant
import java.util.UUID

/**
 * ParcelListView's navigation: the parcel shown and how it came to be shown ([DeliveriesNavigation]), the parcel kept
 * with the window (`@SceneStorage("deliveries.parcel")`, A-SE03) and whether it still waits for the parcels to open it
 * again, the arrangement the window last had and when it last turned stacked.
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
    private val clock: () -> Long = SystemClock::uptimeMillis,
) {
    var navigation: DeliveriesNavigation by mutableStateOf(navigation)
        private set

    /** The parcel someone chose, kept for the window. */
    var savedParcel: UUID? by mutableStateOf(savedParcel)
        private set

    /** The saved parcel waits for the parcels before it opens again. */
    var restoring: Boolean by mutableStateOf(restoring)
        private set

    /** The arrangement the window last had; null before its first. */
    var arrangement: DeliveriesArrangement? by mutableStateOf(arrangement)
        private set

    /** When the window last turned stacked, on the uptime clock. */
    private var stackedAt: Long? = null

    /** The card the list showed at its top when the window turned, which it shows there again once laid out anew. */
    var keptCard: UUID? by mutableStateOf(null)

    /** Counts a screen. */
    var view: (String) -> Unit = {}

    private fun update(next: DeliveriesNavigation) {
        val before = navigation
        if (next == before) return
        navigation = next
        if (next.chosen != before.chosen) savedParcel = next.chosen
        next.screen(after = before)?.let(view)
    }

    /** Opens the parcel someone asked for: over the list, or beside it in place of the one shown there. */
    fun open(parcelId: UUID) = update(navigation.open(parcelId))

    /**
     * The stack went back to `path`. The split view going away pops the parcel off the stack that takes its place as
     * the window turns, on the iPhone: nobody goes back that fast, and for a second after turning the parcel stays.
     */
    fun pop(path: List<UUID>) {
        val stackedAt = stackedAt
        if (path.size < navigation.path.size && stackedAt != null && clock() - stackedAt < POP_GUARD_MILLIS) return
        update(navigation.copy(path = path))
    }

    /**
     * The window takes `arrangement`: having turned stacked, it notes when, and it keeps `topCard` at the top of the
     * list. The first arrangement the window has is no turn.
     */
    fun turn(arrangement: DeliveriesArrangement, topCard: UUID?) {
        val last = this.arrangement
        this.arrangement = arrangement
        if (last == null || last == arrangement) return
        if (arrangement == DeliveriesArrangement.Stacked) stackedAt = clock()
        keptCard = topCard
    }

    fun settle(arrangement: DeliveriesArrangement, rows: List<UUID>) = update(navigation.settle(arrangement, rows))

    /** Back to the list, as tapping the tab again goes: at once, whenever the window turned. */
    fun toList() = update(navigation.copy(path = emptyList()))

    /** The parcel shown beside the list has gone from it: the card after it shows instead. */
    fun leave(parcelId: UUID, rows: List<UUID>) = update(navigation.leave(parcelId, rows))

    /** Shows the card `offset` on from the one shown, as ↑ and ↓ do; false past either end. */
    fun step(offset: Int, rows: List<UUID>): UUID? {
        val next = navigation.step(offset, rows) ?: return null
        update(next)
        return next.shown
    }

    /** The parcels came: the parcel open when the system closed the app opens again, once they are back. */
    fun restore(parcels: List<Parcel>) {
        if (!restoring) return
        val parcelId = savedParcel
        if (parcelId == null) {
            restoring = false
            return
        }
        val restored = navigation.restore(parcelId, parcels.map { it.id }) ?: return
        update(restored)
        restoring = false
    }

    companion object {
        private const val POP_GUARD_MILLIS = 1000L

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
 * What the deliveries' list and the parcel beside it share: the parcels as the list arranges them, the window's
 * arrangement, the navigation, the list's scroll and the archive's shelf.
 */
@Stable
internal class DeliveriesState(
    val source: DeliveriesSource,
    val navigator: DeliveriesNavigator,
    val listState: LazyListState,
    archiveOpenState: MutableState<Boolean>,
    showingSearchState: MutableState<Boolean>,
    private val now: () -> Instant,
) {
    /** The parcels as the list last received them: the store's, a moment before it shows them. */
    var parcels: List<Parcel> by mutableStateOf(source.parcels.value)

    /** The parcels that have just arrived, which keep their place a moment (DeliveredHold). */
    var hold: DeliveredHold by mutableStateOf(DeliveredHold())

    /** How the list and an open parcel share the window. */
    var arrangement: DeliveriesArrangement by mutableStateOf(DeliveriesArrangement.Stacked)

    var archiveOpen: Boolean by archiveOpenState
    var showingSearch: Boolean by showingSearchState

    /** The list is looked at, and may show a card moving (`watching`). */
    var watching: Boolean = false

    /** Filtering, sorting and grouping, once for each change of the parcels: with nothing searched or filtered yet. */
    val layout: DeliveryListLayout by derivedStateOf { arrange(hold.arranged(parcels)) }

    /**
     * The cards that are others once the held parcels are let go: those leave without a trace (where the iPhone flies
     * their picture to their new place), the others fade.
     */
    val leaving: Set<UUID> by derivedStateOf {
        if (hold.isEmpty) emptySet() else layout.changing(into = arrange(parcels)).keys
    }

    private fun arrange(parcels: List<Parcel>) = DeliveryListLayout(
        parcels, query = "", status = ParcelStatusFilter.All, carrier = null, sort = ParcelSort.Priority, featuresNext = true,
        now = now(),
    )

    /** The parcels the list shows a card or a row for, in its order: the archive's rows count while they show. */
    val rows: List<UUID> by derivedStateOf { layout.rows(archiveOpen) }

    /** The parcel whose card is marked as the one shown beside the list. */
    val selectedCard: UUID? get() = if (arrangement == DeliveriesArrangement.SideBySide) navigator.navigation.shown else null

    /** Whether the list is on screen: always beside a parcel, and under the stack while nobody opened one over it. */
    val listShown: Boolean
        get() = arrangement == DeliveriesArrangement.SideBySide || navigator.navigation.chosen == null

    /** The parcel as it is now, for a card arranged as it was. */
    fun shown(parcel: Parcel): Parcel = if (hold.isEmpty) parcel else parcels.firstOrNull { it.id == parcel.id } ?: parcel

    /** The parcels came from the store. */
    fun receive(next: List<Parcel>) {
        val rowsBefore = rows
        val shownBefore = navigator.navigation.shown
        hold = hold.receive(shown = parcels, next = next, watching = watching)
        parcels = next
        // A parcel shown beside the list that has gone, deleted from its card or elsewhere, gives way to the card after it.
        if (arrangement == DeliveriesArrangement.SideBySide && shownBefore != null && next.none { it.id == shownBefore }) {
            navigator.leave(shownBefore, rowsBefore)
        }
        navigator.restore(next)
    }

    /** The parcels just arrived take their new places. Their card's flight there isn't ported yet: they simply move. */
    fun letGo() {
        hold = hold.release()
    }

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

    /** Opens the sample parcel Pip holds (`FirstOpenJourney.delivered`): its page isn't ported yet. */
    fun openSample() = Unit

    /**
     * Where a card stands in the list, which the delivered card's flight and Pip's sight measure from: neither is
     * ported yet.
     */
    @Suppress("UNUSED_PARAMETER")
    fun stand(parcelId: UUID, frame: Rect) = Unit

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
        if (reduceMotion) listState.scrollToItem(0) else listState.animateScrollToItem(0)
    }

    /** `proxy.scrollTo(id)`: the card scrolled just far enough to show whole, on a snappy 0.25 s. */
    suspend fun bringIntoView(parcelId: UUID, reduceMotion: Boolean) {
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
        listState.scrollToItem(index)
    }
}

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
