package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Rect
import com.plhery.peekdeliverytracker.core.DeliveredHold
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.DeliveriesNavigation
import com.plhery.peekdeliverytracker.core.DeliveryListLayout
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.ParcelSectionKind
import com.plhery.peekdeliverytracker.core.ParcelSort
import com.plhery.peekdeliverytracker.core.ParcelStatusFilter
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.PullToRefreshModel
import com.plhery.peekdeliverytracker.core.TrackingNumberCopy
import com.plhery.peekdeliverytracker.core.trackingNumbers
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.theme.Motion
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
 * feedback: the pull, the toast, the error of an action, the card menu open and the delete it asks about.
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
    showingSearchState: MutableState<Boolean>,
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
        val held = hold.receive(shown = parcels, next = next, watching = watching)
        // Holding nothing as before is no change: the list isn't arranged again for it.
        if (!held.isEmpty || !hold.isEmpty) hold = held
        parcels = next
        // A parcel shown beside the list that has gone, deleted from its card or elsewhere, gives way to the card after it.
        if (arrangement == DeliveriesArrangement.SideBySide && shownBefore != null && next.none { it.id == shownBefore }) {
            navigator.leave(shownBefore, rowsBefore)
        }
        navigator.restore(next)
    }

    /** The parcels just arrived take their new places. Their card's flight there isn't ported yet: they simply move. */
    fun letGo() {
        if (!hold.isEmpty) hold = hold.release()
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

    /** What went wrong with an action, said in an alert (`actionError`). */
    var actionError: String? by mutableStateOf(null)

    /** The card whose menu is open, and its parcel. */
    var menu: CardMenuRequest? by mutableStateOf(null)

    /**
     * The swipes of the cards open on the list as drawn, in the order they opened: the last one's action is kept from
     * the system's back gesture.
     */
    val openSwipes: SnapshotStateList<ArchiveSwipe> = mutableStateListOf()

    /** The parcel its menu asks about before deleting it (`prompts.deleting`). */
    var deleting: Parcel? by mutableStateOf(null)

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
            source.archive(parcel)
        } catch (error: CancellationException) {
            throw error
        } catch (error: Exception) {
            actionError = source.errorMessage(error)
            return@async false
        }
        source.undoParcel.value?.let { undo ->
            announce(archivedText(undo))
            source.scope.launch {
                delay(UNDO_DURATION)
                source.forgetUndo(undo)
            }
        }
        true
    }

    /** Brings a parcel back from the archive: the card's Restore, and the Undo of its toast. */
    fun restore(parcel: Parcel) {
        source.scope.launch {
            try {
                source.restore(parcel)
            } catch (error: CancellationException) {
                throw error
            } catch (error: Exception) {
                actionError = source.errorMessage(error)
            }
        }
    }

    /** Deletes the parcel its menu asked about. Beside the list, a card that was shown gives way to the next. */
    fun delete(parcel: Parcel) {
        val cards = rows
        source.scope.launch {
            try {
                source.permanentlyDelete(parcel)
                navigator.leave(parcel.id, cards)
            } catch (error: CancellationException) {
                throw error
            } catch (error: Exception) {
                actionError = source.errorMessage(error)
            }
        }
    }

    /** What a card's menu asked of its parcel. Archiving it is the same as swiping it away, Undo and all. */
    fun chose(item: ParcelMenuItem, parcel: Parcel) {
        when (item) {
            ParcelMenuItem.Open -> navigator.open(parcel.id)
            // Naming a parcel, sharing it and changing its carrier open what comes with the parcel's page: the
            // menu offers them, and they do nothing yet.
            ParcelMenuItem.Rename, ParcelMenuItem.Share, ParcelMenuItem.ChangeCarrier -> Unit
            ParcelMenuItem.CopyTrackingNumber -> {
                val entry = parcel.trackingNumbers.firstOrNull() ?: return
                source.action("parcel-copy-tracking")
                val copied = text("detail.copied")
                // Where the system shows the copy itself, TalkBack still hears of it.
                if (copy(TrackingNumberCopy.text(entry.number, entry.carrier))) show(copied) else announce(copied)
            }
            ParcelMenuItem.Archive -> archive(parcel)
            ParcelMenuItem.Restore -> restore(parcel)
            ParcelMenuItem.Delete -> deleting = parcel
        }
    }

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

/** How long an archived parcel's Undo stays, in milliseconds. */
private const val UNDO_DURATION = 7_000L

/** The pull holds its arrow turning this long at least, and its result this long, in milliseconds. */
private const val PULL_MINIMUM = 900L
private const val PULL_RESULT = 1100L
private const val PULL_SUCCESS = 700L
