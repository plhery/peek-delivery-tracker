package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.NativeRoute
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.ReviewPrompt
import com.plhery.peekdeliverytracker.core.ReviewRequester
import com.plhery.peekdeliverytracker.core.api.Parcel
import java.util.UUID
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

/**
 * What the deliveries read and ask of the app's stores: ParcelListView's `store`, `session`, the configuration's pages
 * and `DeliveryAnalytics.shared`.
 */
interface DeliveriesSource {
    /** The app's launch these stores belong to: a window saved in another one comes back after the process died. */
    val launch: UUID

    /**
     * The app's own scope, which outlives the window: the list's changes to the parcels run in it, so that leaving the
     * tab or the window being made again doesn't cancel one half done, which the store would undo.
     */
    val scope: CoroutineScope

    val parcels: StateFlow<List<Parcel>>
    val loading: StateFlow<Boolean>
    val refreshing: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val session: StateFlow<AuthenticationState>

    /** The site's landing page and its privacy notice, the foot's links. */
    val homePageUrl: String
    val privacyUrl: String

    /** The parcel archived last, which its toast's Undo brings back (`store.undoParcel`). */
    val undoParcel: StateFlow<Parcel?>

    /**
     * `store.undoParcel = nil` once the toast has had its time: only while it still offers this very Undo (`parcel` as
     * the store gave it), not one archived since, even of the same parcel.
     */
    fun forgetUndo(parcel: Parcel)

    /**
     * `checkAllParcels()`: checks every parcel and says how it went, waiting for a check the service queued. Null when
     * the check was interrupted, such as by signing out.
     */
    suspend fun checkAllParcels(): ParcelStore.RefreshResult?

    suspend fun archive(parcel: Parcel)

    suspend fun restore(parcel: Parcel)

    suspend fun permanentlyDelete(parcel: Parcel)

    /** `store.viewerLink(for:)`: the link someone else's parcel is followed through, which its menu reads. */
    fun viewerLink(parcel: Parcel): String?

    /** `localizer.errorMessage(error)`. */
    fun errorMessage(error: Throwable): String

    /** Leaves the demo for the first screen. */
    fun showWelcome()

    fun view(screen: String)

    fun action(name: String)

    /** When the list asks for a rating (ReviewPrompt): the days used, the bad days and the asks, kept on this phone. */
    val reviewPrompt: ReviewPrompt

    /** Nothing in the app stands in the way of asking for a rating (`ReviewPrompt.settled`). */
    val reviewSettled: StateFlow<Boolean>

    /** The system's prompt for a rating, over the window's `activity` (`requestReview`). */
    fun reviews(activity: Activity): ReviewRequester
}

/** The deliveries from the app's container. */
class ContainerDeliveries(private val container: AppContainer) : DeliveriesSource {
    override val launch: UUID get() = LAUNCH
    override val scope: CoroutineScope get() = container.scope
    override val parcels get() = container.parcels.parcelsFlow
    override val loading get() = container.parcels.loadingFlow
    override val refreshing get() = container.parcels.refreshingFlow
    override val errorMessage get() = container.parcels.errorMessageFlow
    override val session get() = container.session.stateFlow
    override val homePageUrl get() = container.configuration.homePageUrl
    override val privacyUrl get() = container.configuration.privacyUrl

    override val undoParcel get() = container.parcels.undoParcelFlow

    override fun forgetUndo(parcel: Parcel) {
        if (container.parcels.undoParcel === parcel) container.parcels.undoParcel = null
    }

    override suspend fun checkAllParcels(): ParcelStore.RefreshResult? {
        val store = container.parcels
        val previousOutcome = store.refreshOutcomeFlow.value?.id
        return try {
            when (val start = store.refreshAll()) {
                is ParcelStore.RefreshStart.Completed -> start.result
                else -> {
                    // The store finishes a queued check in the background; wait for its outcome.
                    store.refreshingFlow.first { !it }
                    store.refreshOutcomeFlow.value?.takeIf { it.id != previousOutcome }?.result
                }
            }
        } catch (error: CancellationException) {
            // The caller's own cancellation goes on; the store's, as the account changed, is an interrupted check.
            currentCoroutineContext().ensureActive()
            null
        } catch (error: Exception) {
            ParcelStore.RefreshResult.Failed(container.localizer.errorMessage(error))
        }
    }

    override suspend fun archive(parcel: Parcel) = container.parcels.archive(parcel)

    override suspend fun restore(parcel: Parcel) = container.parcels.restore(parcel)

    override suspend fun permanentlyDelete(parcel: Parcel) = container.parcels.permanentlyDelete(parcel)

    override fun viewerLink(parcel: Parcel): String? = container.parcels.viewerLink(parcel)

    override fun errorMessage(error: Throwable): String = container.localizer.errorMessage(error)

    override fun showWelcome() = container.session.showWelcome()

    override fun view(screen: String) = container.analytics.view(screen)

    override fun action(name: String) = container.analytics.action(name)

    override val reviewPrompt get() = container.reviewPrompt

    override val reviewSettled get() = container.reviewSettled

    override fun reviews(activity: Activity): ReviewRequester = container.reviews(activity)

    private companion object {
        /** This process's launch: the same for every window it makes, another once the system made the process again. */
        val LAUNCH: UUID = UUID.randomUUID()
    }
}

/**
 * What reaches a window's deliveries from outside the list: a parcel a link asked for (`.onOpenURL`'s
 * `parcel/<uuid>`), which the list shows and then takes. Each window has its own ([of]), as each iPhone scene follows
 * its own links, so the window the link came to opens it. The tabs bring the deliveries forward while one waits.
 */
class DeliveriesInbox(parcel: UUID? = null) {
    private val parcelState = MutableStateFlow(parcel)

    /** The parcel waiting to be shown. */
    val parcel: StateFlow<UUID?> = parcelState.asStateFlow()

    /** A link asked for the parcel. */
    fun open(parcelId: UUID) {
        parcelState.value = parcelId
    }

    /** The list has shown the parcel. */
    fun consume(parcelId: UUID) {
        parcelState.compareAndSet(parcelId, null)
    }

    /** `.onOpenURL` (A-L14): `peekdeliverytracker://parcel/<uuid>` opens the parcel on the deliveries. */
    fun follow(intent: Intent?) {
        if (intent?.action != Intent.ACTION_VIEW) return
        val url = intent.dataString ?: return
        when (val route = NativeRoute(url)) {
            is NativeRoute.Parcel -> open(route.id)
            // The friends, Add and the links' notifications come with their screens.
            else -> Unit
        }
    }

    /**
     * `ShareInbox.consume()`: what another app shared with this one, for Add. Android has no share target yet, so
     * nothing is ever waiting.
     */
    fun consumeSharedDraft(): String? = null

    /**
     * `AppDelegate.consumePendingParcelID()`: the parcel of a notification opened before the list was there. The
     * notifications aren't ported yet, so none is ever waiting.
     */
    fun consumePendingParcelNotification(): UUID? = null

    companion object {
        private const val SAVED = "deliveries.inbox"
        private const val PARCEL = "parcel"

        /**
         * The window's inbox, after its `super.onCreate`: it follows the link that started the window, and each link
         * that comes to it after. Made again, the window finds the parcel still waiting, and doesn't follow the link
         * that started it again once its list has shown it.
         */
        fun of(activity: ComponentActivity): DeliveriesInbox {
            val registry = activity.savedStateRegistry
            val saved = registry.consumeRestoredStateForKey(SAVED)
            val inbox = DeliveriesInbox(saved?.getString(PARCEL)?.let(UUID::fromString))
            registry.registerSavedStateProvider(SAVED) { Bundle().apply { putString(PARCEL, inbox.parcel.value?.toString()) } }
            if (saved == null) inbox.follow(activity.intent)
            activity.addOnNewIntentListener { inbox.follow(it) }
            return inbox
        }
    }
}
