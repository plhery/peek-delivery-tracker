package com.plhery.peekdeliverytracker.ui.deliveries

import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * The deliveries' stores as a test sets them: its parcels, the session, what loads, and what the list asked of them.
 * A new process is a new `launch`.
 */
class FakeDeliveries(
    parcels: List<Parcel> = emptyList(),
    session: AuthenticationState = AuthenticationState.Welcome,
    override val launch: UUID = UUID.randomUUID(),
) : DeliveriesSource {
    override val parcels = MutableStateFlow(parcels)
    override val loading = MutableStateFlow(false)
    override val refreshing = MutableStateFlow(false)
    override val errorMessage = MutableStateFlow<String?>(null)
    override val session = MutableStateFlow(session)
    override val homePageUrl = "https://example.com/"
    override val privacyUrl = "https://example.com/privacy"

    /** The screens and actions counted, in their order. */
    val views = mutableListOf<String>()
    val actions = mutableListOf<String>()

    /** Each screen counted is told here too, as the app tells the tabs' and the list's to one analytics. */
    var onView: (String) -> Unit = {}

    var refreshes = 0
        private set
    var welcomes = 0
        private set

    override suspend fun refresh() {
        refreshes++
    }

    override fun showWelcome() {
        welcomes++
    }

    override fun view(screen: String) {
        views += screen
        onView(screen)
    }

    override fun action(name: String) {
        actions += name
    }

    /** The same stores in a process started again, after the system closed the app. */
    fun newProcess(): FakeDeliveries = FakeDeliveries(parcels.value, session.value)
}
