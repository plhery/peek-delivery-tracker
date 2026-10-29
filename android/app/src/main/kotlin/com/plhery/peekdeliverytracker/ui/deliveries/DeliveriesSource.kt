package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.NativeRoute
import com.plhery.peekdeliverytracker.core.api.Parcel
import java.util.UUID
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * What the deliveries read and ask of the app's stores: ParcelListView's `store`, `session`, the configuration's pages
 * and `DeliveryAnalytics.shared`.
 */
interface DeliveriesSource {
    /** The app's launch these stores belong to: a window saved in another one comes back after the process died. */
    val launch: UUID

    val parcels: StateFlow<List<Parcel>>
    val loading: StateFlow<Boolean>
    val refreshing: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val session: StateFlow<AuthenticationState>

    /** The site's landing page and its privacy notice, the foot's links. */
    val homePageUrl: String
    val privacyUrl: String

    /** Checks every parcel, as the refresh button does. What it found is told in a toast, which isn't ported yet. */
    suspend fun refresh()

    /** Leaves the demo for the first screen. */
    fun showWelcome()

    fun view(screen: String)

    fun action(name: String)
}

/** The deliveries from the app's container. */
class ContainerDeliveries(private val container: AppContainer) : DeliveriesSource {
    override val launch: UUID get() = LAUNCH
    override val parcels get() = container.parcels.parcelsFlow
    override val loading get() = container.parcels.loadingFlow
    override val refreshing get() = container.parcels.refreshingFlow
    override val errorMessage get() = container.parcels.errorMessageFlow
    override val session get() = container.session.stateFlow
    override val homePageUrl get() = container.configuration.homePageUrl
    override val privacyUrl get() = container.configuration.privacyUrl

    override suspend fun refresh() {
        try {
            container.parcels.refreshAll()
        } catch (error: CancellationException) {
            throw error
        } catch (_: Exception) {
            // The iPhone says what went wrong in a toast (refreshFromButton), which comes with the toasts.
        }
    }

    override fun showWelcome() = container.session.showWelcome()

    override fun view(screen: String) = container.analytics.view(screen)

    override fun action(name: String) = container.analytics.action(name)

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
