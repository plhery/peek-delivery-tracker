package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.MonotonicFrameClock
import androidx.test.core.app.ApplicationProvider
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.DeliveryEmail
import com.plhery.peekdeliverytracker.core.NotificationPreset
import com.plhery.peekdeliverytracker.core.ParcelFeedbackClient
import com.plhery.peekdeliverytracker.core.ParcelFeedbackMemory
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel
import com.plhery.peekdeliverytracker.core.ParcelShareModel
import com.plhery.peekdeliverytracker.core.ParcelShareNotes
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.ReviewPrompt
import com.plhery.peekdeliverytracker.core.ReviewRequester
import com.plhery.peekdeliverytracker.core.UserDefaults
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.NotificationStage
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequest
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestApp
import com.plhery.peekdeliverytracker.core.api.ParcelShare
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import java.time.Instant
import java.time.ZoneOffset
import java.util.UUID
import kotlin.coroutines.ContinuationInterceptor
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * The deliveries' stores as a test sets them: its parcels, the session, what loads, and what the list asked of them.
 * A new process is a new `launch`, and the app's scope outlives every window, on the test's clock once `RootHost` shows
 * them ([dispatcher]). Archiving, restoring, deleting, renaming, muting and changing the carrier change the parcels as
 * the store does, or fail with `failure`, an archive once `archiving` lets it go when a test holds it, a mute once
 * `muting` does, an answer to the email's offer once `choosing` does, a carrier change once `changing` does and a
 * parcel's check once `checkingParcel` does; a check of every parcel answers `checkResult`, or waits for `checking`
 * when a test holds it, and finds the parcels as `found` next has them, if it has any. The server refuses the numbers
 * of `refusals`, once `refusing` lets its answer go. The demo is the demo's session. The rating prompt keeps its days
 * in a store of its own, in UTC, and the system's prompt is a fake that notes each step ([reviewCalls]). The parcels'
 * links are the service's (`shareLinks`), and the words kept for them this device's (`shareDefaults`): both outlast a
 * process, as the service and the device's store do. The pages' answers go to [feedbackRequests], or fail with
 * `feedbackFailure`, once `feedbackSending` lets them go; their model keeps the time of [now], which a test moves on,
 * and remembers them in a store of its own.
 */
class FakeDeliveries(
    parcels: List<Parcel> = emptyList(),
    session: AuthenticationState = AuthenticationState.Welcome,
    override val launch: UUID = UUID.randomUUID(),
    val shareLinks: MutableMap<UUID, ParcelShare> = mutableMapOf(),
    private val shareDefaults: UserDefaults = PreferencesDefaults(
        ApplicationProvider.getApplicationContext<Context>().getSharedPreferences("share-${UUID.randomUUID()}", Context.MODE_PRIVATE),
    ),
) : DeliveriesSource {
    override val parcels = MutableStateFlow(parcels)
    override val loading = MutableStateFlow(false)
    override val refreshing = MutableStateFlow(false)
    override val errorMessage = MutableStateFlow<String?>(null)
    override val session = MutableStateFlow(session)
    override val undoParcel = MutableStateFlow<Parcel?>(null)
    override val deliveryEmail = MutableStateFlow<DeliveryEmail?>(null)
    override val notificationPreferences = MutableStateFlow<NotificationPreferences?>(null)
    override val homePageUrl = "https://example.com/"
    override val privacyUrl = "https://example.com/privacy"
    private val job = SupervisorJob()

    /** Where the app's scope runs: the main thread, or the test's clock, which `RootHost` hands in. */
    var dispatcher: ContinuationInterceptor = Dispatchers.Main.immediate

    /** The frames of the window `RootHost` shows, which the delivery email the pages read follows the store by. */
    var frameClock: MonotonicFrameClock? = null

    override val scope: CoroutineScope get() = CoroutineScope(job + dispatcher)

    /** What the pages wait for, for this process: a window made again finds it as it was. */
    override val pageWork = PageWork()

    /** The screens and actions counted, in their order, and the outcome each action was counted with. */
    val views = mutableListOf<String>()
    val actions = mutableListOf<String>()
    val outcomes = mutableListOf<DeliveryAnalytics.Outcome?>()

    /** Each screen counted is told here too, as the app tells the tabs' and the list's to one analytics. */
    var onView: (String) -> Unit = {}

    var refreshes = 0
        private set
    var welcomes = 0
        private set

    /** What a check of every parcel finds, unless `checking` holds it until the test answers. */
    var checkResult: ParcelStore.RefreshResult? = ParcelStore.RefreshResult.Unchanged
    var checking: CompletableDeferred<ParcelStore.RefreshResult?>? = null

    /** The parcels each check of every parcel finds, in their order, as the demo's moves each a stage further. */
    val found = ArrayDeque<List<Parcel>>()

    /** What the store's changes throw, when they fail. */
    var failure: Exception? = null

    /** Holds each archive until the test lets it go, as a slow connection would. */
    var archiving: CompletableDeferred<Unit>? = null

    /** Holds each mute, of the notifications or of the email, until the test lets it go. */
    var muting: CompletableDeferred<Unit>? = null

    /** Holds each answer to the delivery email's offer until the test lets it go. */
    var choosing: CompletableDeferred<Unit>? = null

    /** Whether the server leaves the email off when the account turns it on, as it may. */
    var emailRefused = false

    /** Holds each carrier change until the test lets it go. */
    var changing: CompletableDeferred<Unit>? = null

    /** The id a carrier change files the parcel under, where a guest's change makes it anew, or null for its own. */
    var changedId: UUID? = null

    /** Holds each check of one parcel until the test lets it go. */
    var checkingParcel: CompletableDeferred<Unit>? = null

    /** The carrier and number pairs the server refuses, and what holds its answer until the test lets it go. */
    var refusals: Set<NumberRefusalRequest> = emptySet()
    var refusing: CompletableDeferred<Unit>? = null

    /** The refusal questions asked, in their order. */
    val refusalQuestions = mutableListOf<NumberRefusalRequest>()

    /** Each carrier change with all it sent, in its order. */
    val carrierRequests = mutableListOf<CarrierChangeRequest>()

    /**
     * The parcels archived, restored and deleted, in their order, each mute of the notifications or the email asked for
     * with its new setting, each answer to the email's offer, each carrier change with its carrier, each name given, and
     * the parcels checked one by one.
     */
    val archived = mutableListOf<UUID>()
    val restored = mutableListOf<UUID>()
    val deleted = mutableListOf<UUID>()
    val mutes = mutableListOf<Pair<UUID, Boolean>>()
    val emailMutes = mutableListOf<Pair<UUID, Boolean>>()
    val emailChoices = mutableListOf<Boolean>()
    val carrierChanges = mutableListOf<Pair<UUID, CarrierId>>()
    val renames = mutableListOf<Pair<UUID, String>>()
    val checked = mutableListOf<UUID>()

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
            val result = checking?.await() ?: checkResult
            found.removeFirstOrNull()?.let { parcels.value = it }
            return result
        } finally {
            refreshing.value = false
        }
    }

    override suspend fun refresh(parcel: Parcel) {
        checkingParcel?.await()
        failure?.let { throw it }
        checked += parcel.id
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

    override suspend fun rename(parcel: Parcel, label: String) {
        failure?.let { throw it }
        // As the store's guest and demo parcels refuse a parcel gone from them.
        if (parcels.value.none { it.id == parcel.id }) throw DeliveryApiError.ParcelMissing()
        renames += parcel.id to label
        parcels.value = parcels.value.map { if (it.id == parcel.id) it.copy(label = label) else it }
    }

    override suspend fun setMuted(parcel: Parcel, muted: Boolean) {
        muting?.await()
        failure?.let { throw it }
        mutes += parcel.id to muted
        parcels.value = parcels.value.map { if (it.id == parcel.id) it.copy(notificationsMuted = muted) else it }
    }

    override suspend fun setEmailMuted(parcel: Parcel, muted: Boolean) {
        muting?.await()
        failure?.let { throw it }
        emailMutes += parcel.id to muted
        parcels.value = parcels.value.map { if (it.id == parcel.id) it.copy(emailMuted = muted) else it }
    }

    /** The address the account signed in with, which its delivery email names. */
    private var address: String? = null

    /** `store.deliveryEmail`: what the store has now, which [deliveryEmail] takes a frame after the store changes. */
    private val storedEmail: DeliveryEmail? get() = DeliveryEmail(isDemo = false, preferences = notificationPreferences.value, address = address)

    /**
     * The account signed in as `address`, its preferences on `stages` with the email available, and its delivery email
     * as `choice` says: on, off, or never chosen.
     */
    fun signedInWithEmail(choice: Boolean?, address: String = "reader@example.com", stages: List<NotificationStage> = NotificationPreset.Important.stages) {
        this.address = address
        notificationPreferences.value = NotificationPreferences(enabledStages = stages, timezone = "Europe/Zurich", emailOnDelivery = choice, emailAvailable = true)
        deliveryEmail.value = storedEmail
    }

    /**
     * The choice saved in the store's preferences, which the delivery email the pages read takes on the window's next
     * frame, as the app's `stateIn` does, and what the app's source makes of that ([saveEmailOnDelivery]).
     */
    override suspend fun setEmailOnDelivery(enabled: Boolean): Boolean = saveEmailOnDelivery(deliveryEmail, { storedEmail }) {
        choosing?.await()
        failure?.let { throw it }
        emailChoices += enabled
        val preferences = notificationPreferences.value ?: return@saveEmailOnDelivery
        notificationPreferences.value = preferences.copy(emailOnDelivery = enabled && !emailRefused)
        val saved = storedEmail
        scope.launch {
            frameClock?.withFrameNanos {} ?: delay(FRAME_MILLIS)
            deliveryEmail.value = saved
        }
    }

    override suspend fun changeCarrier(
        parcel: Parcel,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String?,
        providerPostcode: String?,
    ) {
        changing?.await()
        failure?.let { throw it }
        carrierChanges += parcel.id to carrier
        carrierRequests += CarrierChangeRequest(parcel.id, carrier, trackingUrl, dpdPostcode, dpdPostcodeCountry, providerPostcode)
        parcels.value = parcels.value.map {
            if (it.id != parcel.id) {
                it
            } else {
                it.copy(id = changedId ?: it.id, carrier = carrier, syncError = null, trackingUrl = trackingUrl, dpdPostcode = dpdPostcode)
            }
        }
    }

    override val isDemo: Boolean get() = session.value == AuthenticationState.Demo

    override suspend fun numberRefusal(question: NumberRefusalRequest): Boolean {
        refusalQuestions += question
        refusing?.await()
        return question in refusals
    }

    override fun viewerLink(parcel: Parcel): String? = viewerLinks[parcel.id]

    /** Holds each reading of a link, each change and each stop until the test lets it go. */
    var shareLoading: CompletableDeferred<Unit>? = null
    var shareSaving: CompletableDeferred<Unit>? = null
    var shareStopping: CompletableDeferred<Unit>? = null

    /** What reading a link throws, and what changing or stopping one throws, when they fail. */
    var shareLoadFailure: Exception? = null
    var shareFailure: Exception? = null

    /** Each link made or changed, with what it was sent, and each link stopped, in their order. */
    val shareRequests = mutableListOf<Pair<UUID, ParcelShareModel.Settings>>()
    val shareStops = mutableListOf<UUID>()

    private var linksMade = 0

    /** The service's links, in [shareLinks]: a new one for a parcel that has none, with a made-up id. */
    override fun shareClient(parcel: Parcel): ParcelShareModel.Client {
        val id = parcel.id
        return ParcelShareModel.Client(
            current = {
                shareLoading?.await()
                shareLoadFailure?.let { throw it }
                shareLinks[id]
            },
            share = { settings ->
                shareSaving?.await()
                shareFailure?.let { throw it }
                shareRequests += id to settings
                val old = shareLinks[id]
                val link = ParcelShare(
                    id = old?.id ?: "TestLink%04d".format(++linksMade),
                    showNumber = settings.showNumber,
                    gift = settings.gift,
                    createdAt = old?.createdAt ?: SHARED_AT,
                    giftWords = settings.giftWords ?: old?.giftWords,
                )
                shareLinks[id] = link
                link
            },
            stop = {
                shareStopping?.await()
                shareFailure?.let { throw it }
                shareStops += id
                shareLinks.remove(id)
            },
        )
    }

    override val shareNotes = ParcelShareNotes(shareDefaults)
    override val linkBaseUrl = "https://example.com/"
    override val shares = ParcelShares()

    /** The pages' questions' clock: the way back asks once the app has been away a second and a half of it. */
    var now: Instant = CAPTURED

    /** Holds each answer until the test lets it go, and what it throws when it fails. */
    var feedbackSending: CompletableDeferred<Unit>? = null
    var feedbackFailure: Exception? = null

    /** Each answer sent, with its parcel's id, in its order. */
    val feedbackRequests = mutableListOf<Pair<UUID, ParcelFeedbackRequest>>()

    /** What the questions remember, empty for each new source. */
    private val feedbackDefaults = PreferencesDefaults(
        ApplicationProvider.getApplicationContext<Context>().getSharedPreferences("feedback-${UUID.randomUUID()}", Context.MODE_PRIVATE),
    )

    override val feedbacks = ParcelFeedbacks()

    override fun feedbackModel(): ParcelFeedbackModel = ParcelFeedbackModel(
        scope = scope, memory = ParcelFeedbackMemory(feedbackDefaults) { now }, now = { now }, report = { name, outcome -> action(name, outcome) },
        review = reviewPrompt, app = ParcelFeedbackRequestApp.Ios,
    )

    override fun feedbackClient(parcel: Parcel): ParcelFeedbackClient = ParcelFeedbackClient { feedback ->
        feedbackSending?.await()
        feedbackFailure?.let { throw it }
        feedbackRequests += parcel.id to feedback
    }

    override fun errorMessage(error: Throwable): String = error.message ?: "error"

    override fun showWelcome() {
        welcomes++
    }

    override fun view(screen: String) {
        views += screen
        onView(screen)
    }

    override fun action(name: String, outcome: DeliveryAnalytics.Outcome?) {
        actions += name
        outcomes += outcome
    }

    /** The same stores in a process started again, after the system closed the app. */
    fun newProcess(): FakeDeliveries = FakeDeliveries(parcels.value, session.value, shareLinks = shareLinks, shareDefaults = shareDefaults)

    /** What a carrier change sent: the parcel's id, the carrier, the link, the postcode and its country, and a provider's postcode. */
    data class CarrierChangeRequest(
        val parcelId: UUID,
        val carrier: CarrierId,
        val trackingUrl: String?,
        val dpdPostcode: String?,
        val dpdPostcodeCountry: String?,
        val providerPostcode: String?,
    )

    private companion object {
        val ARCHIVED_AT: String = Instant.parse("2026-05-01T09:00:00Z").toString()
        const val SHARED_AT = "2026-05-01T09:00:00Z"

        /** A frame, where no window gives its own. */
        const val FRAME_MILLIS = 16L
    }
}
