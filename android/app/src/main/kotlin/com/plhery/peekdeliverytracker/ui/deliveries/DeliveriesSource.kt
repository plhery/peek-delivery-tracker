package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateMapOf
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.DeliveryApiClient
import com.plhery.peekdeliverytracker.core.DeliveryEmail
import com.plhery.peekdeliverytracker.core.NativeRoute
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.ReviewPrompt
import com.plhery.peekdeliverytracker.core.ReviewRequester
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
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
import kotlinx.coroutines.launch

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

    /** What the parcel pages wait for, by page, for as long as the changes they asked for run in [scope]. */
    val pageWork: PageWork

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

    /** `store.refresh(_:)`: asks the parcel's carrier again, then shows what it answered. */
    suspend fun refresh(parcel: Parcel)

    suspend fun archive(parcel: Parcel)

    suspend fun restore(parcel: Parcel)

    suspend fun permanentlyDelete(parcel: Parcel)

    /** `store.rename(_:label:)`: names the parcel `label`, trimmed of whitespace and newlines. */
    suspend fun rename(parcel: Parcel, label: String)

    /** `store.setMuted(_:muted:)`: switches the parcel's notifications off, or back on. Nothing for a guest's parcel. */
    suspend fun setMuted(parcel: Parcel, muted: Boolean)

    /** `store.setEmailMuted(_:muted:)`: switches the delivery email off, or back on, for this parcel only. */
    suspend fun setEmailMuted(parcel: Parcel, muted: Boolean)

    /**
     * `store.changeCarrier(_:carrier:trackingURL:dpdPostcode:dpdPostcodeCountry:providerPostcode:)`: moves the parcel to
     * `carrier`, with the link and the postcode it needs, the postcode's country where it isn't the carrier's home, and
     * the postcode a provider asked for. The page's switch to the carrier its number names sends none of them.
     */
    suspend fun changeCarrier(
        parcel: Parcel,
        carrier: CarrierId,
        trackingUrl: String? = null,
        dpdPostcode: String? = null,
        dpdPostcodeCountry: String? = null,
        providerPostcode: String? = null,
    )

    /** `store.isDemo`: the demo's parcels, which no server is asked about. */
    val isDemo: Boolean

    /**
     * `DeliveryAPIClient.numberRefusal`: whether the server says `question`'s carrier issues no number like its own,
     * which [com.plhery.peekdeliverytracker.core.NumberRefusals] asks once per pair.
     */
    suspend fun numberRefusal(question: NumberRefusalRequest): Boolean

    /** `store.deliveryEmail`, as the account's preferences and the session change: null without one. */
    val deliveryEmail: StateFlow<DeliveryEmail?>

    /** `store.notificationPreferences`: the account's, once loaded; null before then and without an account. */
    val notificationPreferences: StateFlow<NotificationPreferences?>

    /**
     * `store.setEmailOnDelivery(_:)`: switches the account's delivery email on or off, then says whether the store has it
     * on once saved (`store.deliveryEmail?.isOn`), which [deliveryEmail] may say only a moment later.
     */
    suspend fun setEmailOnDelivery(enabled: Boolean): Boolean

    /** `store.viewerLink(for:)`: the link someone else's parcel is followed through, which its menu reads. */
    fun viewerLink(parcel: Parcel): String?

    /** `localizer.errorMessage(error)`. */
    fun errorMessage(error: Throwable): String

    /** Leaves the demo for the first screen. */
    fun showWelcome()

    fun view(screen: String)

    fun action(name: String, outcome: DeliveryAnalytics.Outcome? = null)

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
    override val pageWork: PageWork get() = PAGE_WORK
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

    override suspend fun refresh(parcel: Parcel) = container.parcels.refresh(parcel)

    override suspend fun archive(parcel: Parcel) = container.parcels.archive(parcel)

    override suspend fun restore(parcel: Parcel) = container.parcels.restore(parcel)

    override suspend fun permanentlyDelete(parcel: Parcel) = container.parcels.permanentlyDelete(parcel)

    override suspend fun rename(parcel: Parcel, label: String) = container.parcels.rename(parcel, label)

    override suspend fun setMuted(parcel: Parcel, muted: Boolean) = container.parcels.setMuted(parcel, muted)

    override suspend fun setEmailMuted(parcel: Parcel, muted: Boolean) = container.parcels.setEmailMuted(parcel, muted)

    override suspend fun changeCarrier(
        parcel: Parcel,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String?,
        providerPostcode: String?,
    ) = container.parcels.changeCarrier(parcel, carrier, trackingUrl, dpdPostcode, dpdPostcodeCountry, providerPostcode)

    override val isDemo: Boolean get() = container.parcels.isDemo

    override suspend fun numberRefusal(question: NumberRefusalRequest): Boolean =
        DeliveryApiClient.numberRefusal(question, container.configuration, container.transport)

    override val deliveryEmail get() = container.deliveryEmail

    override val notificationPreferences get() = container.parcels.notificationPreferencesFlow

    override suspend fun setEmailOnDelivery(enabled: Boolean): Boolean {
        container.parcels.setEmailOnDelivery(enabled)
        return container.parcels.deliveryEmail?.isOn == true
    }

    override fun viewerLink(parcel: Parcel): String? = container.parcels.viewerLink(parcel)

    override fun errorMessage(error: Throwable): String = container.localizer.errorMessage(error)

    override fun showWelcome() = container.session.showWelcome()

    override fun view(screen: String) = container.analytics.view(screen)

    override fun action(name: String, outcome: DeliveryAnalytics.Outcome?) = container.analytics.action(name, outcome)

    override val reviewPrompt get() = container.reviewPrompt

    override val reviewSettled get() = container.reviewSettled

    override fun reviews(activity: Activity): ReviewRequester = container.reviews(activity)

    private companion object {
        /** This process's launch: the same for every window it makes, another once the system made the process again. */
        val LAUNCH: UUID = UUID.randomUUID()

        /** What this process's pages wait for, for every window it makes, as its launch is. */
        val PAGE_WORK = PageWork()
    }
}

/**
 * What each parcel page waits for (ParcelDetailView's `working`) and what came of it, by the page's id: a page's change
 * holds only that page's bell, refresh, switch and delete, and only the page that asked hears why it failed (its
 * `errorMessage`), or leaves once it was saved. Each page's answer to the delivery email's offer stands here too (its
 * `emailOffer`), saved apart from its changes.
 *
 * The source holds it for the launch, as long as the changes run, in the app's scope: a page drawn again as the window
 * is made again (a rotation, a fold) still waits for its change and hears how it went, as the iPhone's `@State` lasts
 * through a rotation. Kept with the window, it would come back as a copy the change, once done, never reaches. A page
 * that goes forgets what it asked for ([forget]), as the iPhone's page goes with its state: the change goes on, and the
 * page opened again starts afresh.
 */
@Stable
class PageWork {
    /** The change each page waits for, each its own: one its page forgot comes to nothing once done. */
    private val runs = mutableStateMapOf<UUID, Any>()
    private val failures = mutableStateMapOf<UUID, String>()
    private val leaves = mutableStateMapOf<UUID, List<UUID>>()
    private val offers = mutableStateMapOf<UUID, EmailOfferPhase>()
    private val offerNews = mutableStateMapOf<UUID, EmailOfferPhase>()

    /** Whether the page with `pageId` waits for a change it asked for. */
    fun working(pageId: UUID): Boolean = pageId in runs

    /** Why the last change the page with `pageId` asked for failed, until its alert closes ([dismiss]). */
    fun failure(pageId: UUID): String? = failures[pageId]

    fun dismiss(pageId: UUID) {
        failures -= pageId
    }

    /**
     * The pages whose change was saved and which leave for it, each with the list's cards as they stood when it was
     * asked for: the window that shows them by then takes them ([left]).
     */
    val leaving: Map<UUID, List<UUID>> get() = leaves

    fun left(pageId: UUID) {
        leaves -= pageId
    }

    /**
     * The page's `run`: `work` in `scope` while the page with `pageId` waits, and nothing while it waits for another.
     * Once done, unless the page forgot it: why it failed, in `message`'s words, or the page's leaving, where it leaves
     * once saved (`cards`).
     */
    fun run(pageId: UUID, scope: CoroutineScope, message: (Throwable) -> String, cards: List<UUID>? = null, work: suspend () -> Unit) {
        if (pageId in runs) return
        val run = Any()
        runs[pageId] = run
        failures -= pageId
        scope.launch {
            val failure = try {
                work()
                null
            } catch (error: CancellationException) {
                end(pageId, run)
                throw error
            } catch (error: Exception) {
                message(error)
            }
            if (!end(pageId, run)) return@launch
            if (failure != null) {
                failures[pageId] = failure
            } else if (cards != null) {
                leaves[pageId] = cards
            }
        }
    }

    /** Where the answer to the delivery email's offer on the page with `pageId` stands (its `emailOffer`). */
    fun emailOffer(pageId: UUID): EmailOfferPhase = offers[pageId] ?: EmailOfferPhase.Open

    /**
     * The answer just saved on the page with `pageId`, accepted or failed, until the page has said so ([heard]): the
     * page shown by then, made again or not, gives the tap and tells TalkBack.
     */
    fun emailOfferNews(pageId: UUID): EmailOfferPhase? = offerNews[pageId]

    fun heard(pageId: UUID) {
        offerNews -= pageId
    }

    /**
     * `answerEmailOffer(_:)` on the page with `pageId`: saves the answer in `scope` (`save`, which says whether the email
     * is on once saved), one at a time, saying it saves meanwhile. Once saved the offer says the email is on where it was
     * turned on and is, and goes back to open otherwise, which leaves the offer once the account has chosen; once failed
     * it says so, for another try. Nothing once the page forgot it.
     */
    fun answerEmailOffer(pageId: UUID, accepted: Boolean, scope: CoroutineScope, save: suspend () -> Boolean) {
        if (offers[pageId] is EmailOfferPhase.Saving) return
        val saving = EmailOfferPhase.Saving(accepted)
        offers[pageId] = saving
        scope.launch {
            val phase = try {
                if (save() && accepted) EmailOfferPhase.Accepted else EmailOfferPhase.Open
            } catch (error: CancellationException) {
                if (offers[pageId] === saving) offers[pageId] = EmailOfferPhase.Open
                throw error
            } catch (_: Exception) {
                EmailOfferPhase.Failed
            }
            if (offers[pageId] !== saving) return@launch
            offers[pageId] = phase
            if (phase != EmailOfferPhase.Open) offerNews[pageId] = phase
        }
    }

    /** The page with `pageId` has gone: it waits for nothing more, and hears nothing of what it asked for. */
    fun forget(pageId: UUID) {
        runs -= pageId
        failures -= pageId
        leaves -= pageId
        offers -= pageId
        offerNews -= pageId
    }

    /** The change `run` of the page with `pageId` is over: whether the page still waited for it. */
    private fun end(pageId: UUID, run: Any): Boolean {
        if (runs[pageId] !== run) return false
        runs -= pageId
        return true
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
