package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Activity
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.ReviewPrompt
import com.plhery.peekdeliverytracker.core.ReviewRequester
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import java.time.Instant
import java.time.ZoneOffset
import java.util.UUID
import kotlin.coroutines.ContinuationInterceptor
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * The deliveries' stores as a test sets them: its parcels, the session, what loads, and what the list asked of them.
 * A new process is a new `launch`, and the app's scope outlives every window, on the test's clock once `RootHost` shows
 * them ([dispatcher]). Archiving, restoring and deleting change the parcels as the store does, or fail with `failure`,
 * an archive once `archiving` lets it go when a test holds it; a check of every parcel answers `checkResult`, or waits
 * for `checking` when a test holds it. The rating prompt keeps its days in a store of its own, in UTC, and the system's
 * prompt is a fake that notes each step ([reviewCalls]).
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
    override val undoParcel = MutableStateFlow<Parcel?>(null)
    override val homePageUrl = "https://example.com/"
    override val privacyUrl = "https://example.com/privacy"
    private val job = SupervisorJob()

    /** Where the app's scope runs: the main thread, or the test's clock, which `RootHost` hands in. */
    var dispatcher: ContinuationInterceptor = Dispatchers.Main.immediate

    override val scope: CoroutineScope get() = CoroutineScope(job + dispatcher)

    /** The screens and actions counted, in their order. */
    val views = mutableListOf<String>()
    val actions = mutableListOf<String>()

    /** Each screen counted is told here too, as the app tells the tabs' and the list's to one analytics. */
    var onView: (String) -> Unit = {}

    var refreshes = 0
        private set
    var welcomes = 0
        private set

    /** What a check of every parcel finds, unless `checking` holds it until the test answers. */
    var checkResult: ParcelStore.RefreshResult? = ParcelStore.RefreshResult.Unchanged
    var checking: CompletableDeferred<ParcelStore.RefreshResult?>? = null

    /** What the store's changes throw, when they fail. */
    var failure: Exception? = null

    /** Holds each archive until the test lets it go, as a slow connection would. */
    var archiving: CompletableDeferred<Unit>? = null

    /** The parcels archived, restored and deleted, in their order. */
    val archived = mutableListOf<UUID>()
    val restored = mutableListOf<UUID>()
    val deleted = mutableListOf<UUID>()

    var viewerLinks: Map<UUID, String> = emptyMap()

    /** The rating prompt's memory, empty for each new source, and the version it asks for. */
    private val reviewDefaults = PreferencesDefaults(
        ApplicationProvider.getApplicationContext<Context>().getSharedPreferences("review-${UUID.randomUUID()}", Context.MODE_PRIVATE),
    )

    override var reviewPrompt = ReviewPrompt(reviewDefaults, version = "1.0", timeZone = { ZoneOffset.UTC })

    /** Nothing stands in the way of an ask, unless a test says otherwise. */
    override val reviewSettled = MutableStateFlow(true)

    /** Play's two steps as the list took them: `prepare`, then `launch` once the ask was counted. */
    val reviewCalls = mutableListOf<String>()

    /** Whether Play has the review info a prompt needs. */
    var reviewInfo = true

    override fun reviews(activity: Activity): ReviewRequester = object : ReviewRequester {
        override suspend fun prepare(): Boolean {
            reviewCalls += "prepare"
            return reviewInfo
        }

        override fun launch() {
            reviewCalls += "launch"
        }
    }

    override suspend fun checkAllParcels(): ParcelStore.RefreshResult? {
        refreshes++
        refreshing.value = true
        try {
            return checking?.await() ?: checkResult
        } finally {
            refreshing.value = false
        }
    }

    override fun forgetUndo(parcel: Parcel) {
        if (undoParcel.value === parcel) undoParcel.value = null
    }

    override suspend fun archive(parcel: Parcel) {
        archiving?.await()
        failure?.let { throw it }
        archived += parcel.id
        parcels.value = parcels.value.map { if (it.id == parcel.id) it.copy(archivedAt = ARCHIVED_AT) else it }
        undoParcel.value = parcel
    }

    override suspend fun restore(parcel: Parcel) {
        failure?.let { throw it }
        restored += parcel.id
        parcels.value = parcels.value.map { if (it.id == parcel.id) it.copy(archivedAt = null) else it }
        if (undoParcel.value?.id == parcel.id) undoParcel.value = null
    }

    override suspend fun permanentlyDelete(parcel: Parcel) {
        failure?.let { throw it }
        deleted += parcel.id
        parcels.value = parcels.value.filter { it.id != parcel.id }
        if (undoParcel.value?.id == parcel.id) undoParcel.value = null
    }

    override fun viewerLink(parcel: Parcel): String? = viewerLinks[parcel.id]

    override fun errorMessage(error: Throwable): String = error.message ?: "error"

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

    private companion object {
        val ARCHIVED_AT: String = Instant.parse("2026-05-01T09:00:00Z").toString()
    }
}
