package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ClaimParcelResultOutcome
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsResponse
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.ImportCharges
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.ParcelNumberHint
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.io.IOException
import java.time.Instant
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.Serializable

// ParcelLinks.swift. The store reads a link through a `ParcelLinkReader`, which the app makes of its API client
// (`DeliveryAPIClient.publicParcel` on iOS), and keeps the link across sign-in in the app's `UserDefaults`.

/** The parcel a parcel link shows, as the card and the journal draw it. A hidden number stays empty. */
fun Parcel(shared: PublicPackage): Parcel {
    val data = shared.carrierData
    return Parcel(
        id = shared.id,
        trackingNumber = shared.trackingNumber ?: "",
        label = shared.label,
        carrier = shared.carrier,
        createdAt = shared.createdAt,
        expectedDelivery = shared.expectedDelivery,
        lastStatusText = shared.lastStatusText,
        lastSyncedAt = shared.lastSyncedAt,
        syncStatus = shared.syncStatus,
        syncError = shared.syncError,
        trackingUrl = shared.trackingUrl,
        dpdPostcode = shared.dpdPostcode,
        carrierData = CarrierData(
            activeTrackingCarrier = data.activeTrackingCarrier,
            activeTrackingNumber = data.activeTrackingNumber,
            deliveryCarrier = data.deliveryCarrier,
            deliveryTrackingNumber = data.deliveryTrackingNumber,
            canonicalTrackingNumber = data.canonicalTrackingNumber,
            originalCarrier = data.originalCarrier,
            originalTrackingNumber = data.originalTrackingNumber,
            trackingProvider = data.trackingProvider,
            carrierAnswered = data.carrierAnswered,
            autoChangedFrom = data.autoChangedFrom,
            autoChangedTo = data.autoChangedTo,
            autoChangedAt = data.autoChangedAt,
            senderName = data.senderName,
            swissPostReady = data.swissPostReady,
            expectedDeliveryFrom = data.expectedDeliveryFrom,
            pickupPoint = data.pickupPoint,
            dimensionsText = data.dimensionsText,
            weightKg = data.weightKg,
            importCharges = data.importCharges,
            destinationCountry = data.destinationCountry,
        ),
        archivedAt = shared.archivedAt,
        notificationsMuted = shared.notificationsMuted,
        trackingEvents = shared.trackingEvents,
    )
}

/** How a hidden tracking number reads: "••• 0899". */
val ParcelNumberHint.masked: String get() = "$head ••• $tail".trimmingWhitespaces()

/**
 * What a link shows of a number it hides, by the service's rule: its last quarter, four characters at most, and
 * nothing of its start.
 */
fun ParcelNumberHint(hiding: String): ParcelNumberHint {
    val characters = hiding.characters()
    val tail = minOf(4, characters.size / 4)
    return ParcelNumberHint(head = "", tail = characters.takeLast(tail).joinToString(""))
}

/** How a link shows a gift. A link that is no gift shows none of these. */
enum class ParcelLinkGift {
    /** On its way to someone who is not its sender: the sender, the contents and where it comes from stay a surprise. */
    Wrapped,

    /** Delivered: the note, who it is from and what is inside come out. */
    Opened,

    /** Its sender's own view: the usual parcel, marked as a gift. */
    Own,
    ;

    companion object {
        operator fun invoke(link: ParcelLink, stage: TrackingStage?): ParcelLinkGift? {
            if (link.gift != true) return null
            return if (link.role == ParcelLinkRole.Owner) Own else if (stage == TrackingStage.Delivered) Opened else Wrapped
        }
    }
}

val PublicParcelResponse.gift: ParcelLinkGift? get() = ParcelLinkGift(link, Parcel(shared = parcel).currentStage)

/** What the service writes in place of a scan's own words while a gift hides where it comes from. */
val TrackingEvent.Companion.giftOriginDescription: String get() = "Left the sender"

/** Foundation's `Date.distantPast`, 0001-01-01. */
private val distantPast: Instant = Instant.ofEpochSecond(-62_135_769_600)

/**
 * A gift's journey with its hidden beginning told once: the service blurs every scan made where the parcel comes
 * from, and scans that follow one another then read the same. The newest of each run stays.
 */
fun List<TrackingEvent>.collapsingGiftRows(): List<TrackingEvent> {
    val journey = withIndex().sortedWith { first, second ->
        val a = DateParser.date(first.value.occurredAt) ?: distantPast
        val b = DateParser.date(second.value.occurredAt) ?: distantPast
        if (a == b) first.index.compareTo(second.index) else a.compareTo(b)
    }.map { it.value }
    val blurred = TrackingEvent.giftOriginDescription
    val dropped = HashSet<UUID>()
    for ((event, next) in journey.zipWithNext()) {
        // Swift's `==` on strings: canonical equivalence.
        if (event.description.equalsCanonically(blurred) && next.description.equalsCanonically(blurred) &&
            (event.location ?: "").equalsCanonically(next.location ?: "")
        ) {
            dropped.add(event.id)
        }
    }
    return filter { it.id !in dropped }
}

/** How keeping a shared parcel ended. */
sealed class ParcelLinkClaim {
    /** The parcel is in the account now. */
    data class Added(val id: UUID?) : ParcelLinkClaim()

    /** The account already follows this parcel. */
    data class Already(val id: UUID?) : ParcelLinkClaim()

    /** The account holds as many parcels as it may. */
    data object Full : ParcelLinkClaim()

    data object Unavailable : ParcelLinkClaim()

    companion object {
        /** Nil when the answer says nothing about this link. */
        operator fun invoke(response: ClaimParcelsResponse, linkId: String): ParcelLinkClaim? {
            val result = response.results.firstOrNull { it.id.equalsCanonically(linkId) } ?: return null
            return when (result.outcome) {
                ClaimParcelResultOutcome.Kept -> Added(result.packageId)
                ClaimParcelResultOutcome.Already -> Already(result.packageId)
                ClaimParcelResultOutcome.Quota -> Full
                ClaimParcelResultOutcome.Unavailable -> Unavailable
            }
        }
    }
}

/** The link's owner stopped sharing it. */
class ParcelLinkStopped : Exception() {
    override fun equals(other: Any?): Boolean = other is ParcelLinkStopped

    override fun hashCode(): Int = ParcelLinkStopped::class.hashCode()
}

/** Why a link could not be read just now. The link itself may be fine. */
enum class ParcelLinkFailure {
    RateLimited, Offline, Service;

    val messageKey: String
        get() = when (this) {
            RateLimited -> "error.rateLimited"
            Offline -> "error.connection"
            Service -> "native.error.serviceFailed"
        }

    companion object {
        /**
         * Nil for a read that was cancelled. A connection failure is an `IOException`, as OkHttp throws it (Swift's
         * `URLError`); a call the app cancelled reaches here as a `CancellationException`. A `withTimeout` that ran out
         * is one too, but the read timed out, as `URLError.timedOut` does: offline.
         */
        operator fun invoke(error: Throwable): ParcelLinkFailure? = when (error) {
            is TimeoutCancellationException -> Offline
            is CancellationException -> null
            is IOException -> Offline
            is DeliveryApiError.RateLimited -> RateLimited
            else -> Service
        }
    }
}

/**
 * The parcel link on screen. Anyone may look at it; a signed-in person keeps the parcel with one tap, and anyone else
 * may follow it on this phone. A link opened before signing in is remembered on this device for up to seven days,
 * without the owner key a handed-over link carries.
 *
 * Used from the main thread, as the iPhone's store is `@MainActor`: a newer read supersedes one still on its way.
 * `read` is nil in a build without a server, which never reads a link: every link is unavailable there.
 */
class ParcelLinkStore(
    private val defaults: UserDefaults,
    private val now: () -> Instant = Instant::now,
    private val read: ParcelLinkReader?,
) {
    sealed class Phase {
        data object Loading : Phase()

        data class Shown(val response: PublicParcelResponse) : Phase()

        /** Forgotten or unknown: the link never says which. */
        data object Unavailable : Phase()

        /** Its owner stopped sharing it. */
        data object Stopped : Phase()

        data class Failed(val failure: ParcelLinkFailure) : Phase()
    }

    /** What the deliveries list does once the sheet has closed. */
    sealed class Arrival {
        data class Added(val id: UUID?) : Arrival()

        data class Open(val id: UUID) : Arrival()
    }

    /** Swift's `init(defaults:configuration:now:)`: reads through `api`, the app's API client, when the build has a server. */
    constructor(defaults: UserDefaults, configuration: AppConfiguration, api: ParcelLinkReader, now: () -> Instant = Instant::now) :
        this(defaults, now, if (configuration.mode == AppConfiguration.Mode.Api) api else null)

    private val routeState = MutableStateFlow<ParcelLinkRoute?>(null)
    private val presentationIdState = MutableStateFlow(UUID.randomUUID())
    private val phaseState = MutableStateFlow<Phase>(Phase.Loading)
    private val refreshFailureState = MutableStateFlow<ParcelLinkFailure?>(null)
    private val claimState = MutableStateFlow<ParcelLinkClaim?>(null)
    private val arrivalState = MutableStateFlow<Arrival?>(null)

    val routeFlow: StateFlow<ParcelLinkRoute?> = routeState.asStateFlow()
    val presentationIdFlow: StateFlow<UUID> = presentationIdState.asStateFlow()
    val phaseFlow: StateFlow<Phase> = phaseState.asStateFlow()
    val refreshFailureFlow: StateFlow<ParcelLinkFailure?> = refreshFailureState.asStateFlow()
    val claimFlow: StateFlow<ParcelLinkClaim?> = claimState.asStateFlow()
    val arrivalFlow: StateFlow<Arrival?> = arrivalState.asStateFlow()

    var route: ParcelLinkRoute?
        get() = routeState.value
        private set(value) {
            routeState.value = value
        }

    var presentationId: UUID
        get() = presentationIdState.value
        private set(value) {
            presentationIdState.value = value
        }

    var phase: Phase
        get() = phaseState.value
        private set(value) {
            phaseState.value = value
        }

    /** A later read failed; the parcel read before stays on screen. */
    var refreshFailure: ParcelLinkFailure?
        get() = refreshFailureState.value
        private set(value) {
            refreshFailureState.value = value
        }

    /** An answer the sheet shows instead of closing: already followed, or no room. */
    var claim: ParcelLinkClaim?
        get() = claimState.value
        private set(value) {
            claimState.value = value
        }

    var arrival: Arrival?
        get() = arrivalState.value
        private set(value) {
            arrivalState.value = value
        }

    private var queuedArrival: Arrival? = null
    private var generation = 0

    /** Swift's `Date` in JSON: seconds since 2001. */
    @Serializable
    private class Pending(
        val id: String,
        val name: String? = null,
        val note: String? = null,
        val from: String? = null,
        val receivedAt: Double,
    )

    fun open(link: ParcelLinkRoute) {
        generation += 1
        route = link
        presentationId = UUID.randomUUID()
        phase = Phase.Loading
        refreshFailure = null
        claim = null
    }

    /** Reads the link, again when asked to refresh. */
    suspend fun load() {
        val route = route ?: return
        val read = read ?: run {
            gone()
            return
        }
        generation += 1
        val current = generation
        try {
            val response = read(route.id, route.key)
            if (current != generation || !currentCoroutineContext().isActive) return
            refreshFailure = null
            if (response != null) {
                var next = route
                // A key the link does not answer to as its owner's is no key.
                if (response.link.role != ParcelLinkRole.Owner) next = next.copy(key = null)
                val words = response.link.giftWords
                if (response.gift != ParcelLinkGift.Wrapped && words != null) {
                    next = ParcelLinkRoute(id = route.id, name = words.name, note = words.note, from = words.from, key = next.key)
                }
                // Swift compares the words canonically: the same words written another way keep the route.
                if (!next.equalsCanonically(route)) this.route = next
                phase = Phase.Shown(response)
            } else {
                gone()
            }
        } catch (stopped: ParcelLinkStopped) {
            if (current != generation || !currentCoroutineContext().isActive) return
            gone(Phase.Stopped)
        } catch (error: Exception) {
            // A read that timed out ends in a CancellationException while this coroutine carries on.
            if (current != generation || !currentCoroutineContext().isActive) return
            val failure = ParcelLinkFailure(error) ?: return
            if (phase is Phase.Shown) refreshFailure = failure else phase = Phase.Failed(failure)
        }
    }

    /** Keeps the link across sign-in. The sheet closes; `resume` opens it again. */
    fun rememberForSignIn() {
        val route = route ?: return
        val pending = Pending(id = route.id, name = route.name, note = route.note, from = route.from, receivedAt = now().timeIntervalSinceReferenceDate)
        defaults.set(JsonEncoder().encode(Pending.serializer(), pending), forKey = storageKey)
        generation += 1
        this.route = null
    }

    /** Opens the remembered link again, once someone is signed in. */
    fun resume() {
        if (route != null) return
        val link = remembered ?: return
        open(link)
    }

    /** The link kept for after sign-in, while it is still fresh. */
    val remembered: ParcelLinkRoute?
        get() {
            val data = defaults.data(forKey = storageKey) ?: return null
            val pending = runCatching { ApiJson.decodeFromString(Pending.serializer(), data.decodeToString()) }.getOrNull()
            // Swift's `timeIntervalSince`, on the same seconds since 2001 the link was saved with.
            val age = pending?.let { now().timeIntervalSinceReferenceDate - it.receivedAt }
            // Written as Swift accepts it, so an age that is not a number is refused.
            if (pending == null || !ParcelLinkRoute.validId(pending.id) || age == null || !(age >= 0 && age < 604_800)) {
                defaults.removeObject(forKey = storageKey)
                return null
            }
            return ParcelLinkRoute(id = pending.id, name = pending.name, note = pending.note, from = pending.from)
        }

    /** Acts on how keeping the parcel ended. An added parcel closes the sheet, and the list shows it. */
    fun resolve(claim: ParcelLinkClaim) {
        when (claim) {
            is ParcelLinkClaim.Added -> {
                forget()
                queuedArrival = Arrival.Added(claim.id)
                route = null
            }
            is ParcelLinkClaim.Already -> {
                forget()
                this.claim = claim
            }
            ParcelLinkClaim.Full -> this.claim = claim
            ParcelLinkClaim.Unavailable -> gone()
        }
    }

    /** Closes the sheet and has the list open the parcel the account already follows. */
    fun openExisting(parcelId: UUID) {
        queuedArrival = Arrival.Open(parcelId)
        route = null
    }

    /** The person closed the sheet. */
    fun close() {
        forget()
        generation += 1
        route = null
    }

    /** The sheet has left the screen, so the list may take over. */
    fun dismissed() {
        val queuedArrival = queuedArrival ?: return
        this.queuedArrival = null
        arrival = queuedArrival
    }

    fun consumeArrival() {
        arrival = null
    }

    private fun gone(reason: Phase = Phase.Unavailable) {
        forget()
        phase = reason
        refreshFailure = null
        claim = null
    }

    /** A remembered link is dropped once this link is settled; another link's stays. */
    private fun forget() {
        val route = route ?: return
        if (!remembered?.id.equalsCanonically(route.id)) return
        defaults.removeObject(forKey = storageKey)
    }

    /**
     * Set by `debugPreview`: the sheet shows what a signed-in person sees. Debug-only API: the iPhone compiles it into
     * debug builds alone (`#if DEBUG`), so the app reads and sets it only in a debug build.
     */
    var previewsSignedIn = false

    companion object {
        private const val storageKey = "sdt.pendingParcelLink.v1"

        /**
         * Opens the link sheet at launch without a server, to look at it in a debug build. `variant` (the iPhone's
         * `-sdt.debug.parcelLink`): `fixture` (the contract fixture: a viewer, number hidden), `delivered` (the
         * fixture once it has arrived: no route, so its box opens), `route` (a demo parcel with a route, which may be
         * kept), `gift` (the gift fixture, on its way), `giftDelivered` (the same gift, delivered, with the words its
         * link carried), `giftHandover` (the gift on its way, handed to Swiss Post), `handover` (a parcel AliExpress
         * handed to Swiss Post, on its way from Shenzhen to Zürich), `handoverPlain` (the same parcel, its scans
         * without a place), `stopped` or `gone`. `signedIn` (`-sdt.debug.parcelLink.signedIn`) shows it as a
         * signed-in person sees it; `importCharges` (`-sdt.debug.importCharges`) gives the parcel import charges (see
         * `ImportChargesPreview`). `demoParcels` is the demo repository's list.
         *
         * Debug-only API: the iPhone compiles it into debug builds alone (`#if DEBUG`), so the app calls it only in a
         * debug build.
         */
        fun debugPreview(
            variant: String?,
            defaults: UserDefaults,
            signedIn: Boolean = false,
            importCharges: String? = null,
            demoParcels: () -> List<Parcel> = { emptyList() },
        ): ParcelLinkStore? {
            if (variant == null) return null
            val fixtures = runCatching {
                ApiJson.decodeFromString(DebugFixtures.serializer(), SharedResources.text("ContractFixtures.json"))
            }.getOrNull()
            var response = if (variant.startsWith("gift")) fixtures?.publicGiftParcel else fixtures?.publicParcel
            var words = ParcelLinkWords()
            fun change(edit: (PublicParcelResponse) -> PublicParcelResponse) {
                response = response?.let(edit)
            }
            fun changePackage(edit: (PublicPackage) -> PublicPackage) = change { it.copy(parcel = edit(it.parcel)) }
            fun changeLink(edit: (ParcelLink) -> ParcelLink) = change { it.copy(link = edit(it.link)) }

            val giftPackage = response?.parcel
            if (variant.startsWith("gift") && giftPackage != null) {
                // The link carries its words from the start; they show only once the gift is there.
                words = ParcelLinkWords(
                    name = "trail running shoes", note = "Happy birthday, Alex! I hope these keep up with you on the trails.", from = "Sam",
                )
                val now = Instant.now()
                if (variant == "giftDelivered") {
                    changeLink { it.copy(numberShown = true, canKeep = true) }
                    val delivered = TrackingEvent(
                        id = UUID.randomUUID(), packageId = giftPackage.id, stage = TrackingStage.Delivered, description = "Delivered",
                        location = "Zürich, CH", occurredAt = DateParser.isoString(now.minusSeconds(180)),
                    )
                    changePackage { it.copy(trackingNumber = "TESTPARCEL123789", numberHint = null, trackingEvents = listOf(delivered) + it.trackingEvents) }
                } else {
                    changePackage { it.copy(expectedDelivery = ParcelOrganizer.dayKey(now) + " 13:00–17:00") }
                }
                if (variant == "giftHandover") {
                    changePackage {
                        it.copy(carrierData = it.carrierData.copy(originalCarrier = giftPackage.carrier, activeTrackingCarrier = CarrierId.SwissPost))
                    }
                }
                changePackage { it.copy(lastSyncedAt = DateParser.isoString(now.minusSeconds(120))) }
            }
            val deliveredPackage = response?.parcel
            if (variant == "delivered" && deliveredPackage != null) {
                val now = Instant.now()
                val delivered = TrackingEvent(
                    id = UUID.randomUUID(), packageId = deliveredPackage.id, stage = TrackingStage.Delivered, description = "Delivered",
                    occurredAt = DateParser.isoString(now.minusSeconds(180)),
                )
                changePackage { it.copy(trackingEvents = listOf(delivered) + it.trackingEvents) }
                changePackage { it.copy(lastSyncedAt = DateParser.isoString(now.minusSeconds(120))) }
            }
            val routed = if (variant == "route") {
                demoParcels().firstOrNull { parcel -> parcel.isActive && parcel.trackingEvents.any { it.place != null } }
            } else {
                null
            }
            if (routed != null) {
                changeLink { it.copy(numberShown = true, canKeep = true) }
                changePackage {
                    PublicPackage(
                        id = routed.id, trackingNumber = routed.trackingNumber, label = "", carrier = routed.carrier,
                        createdAt = routed.createdAt, expectedDelivery = routed.expectedDelivery, lastStatusText = routed.lastStatusText,
                        lastSyncedAt = routed.lastSyncedAt, syncStatus = routed.syncStatus,
                        carrierData = PublicPackageCarrierData(
                            senderName = routed.carrierData?.senderName, destinationCountry = routed.carrierData?.destinationCountry,
                        ),
                        notificationsMuted = false, trackingEvents = routed.trackingEvents,
                    )
                }
            }
            val handedOver = response?.parcel
            if (variant.startsWith("handover") && handedOver != null) {
                val now = Instant.now()
                val stops = listOf(
                    EventPlace(22.54, 114.06, EventPlacePrecision.City, "CN", "Shenzhen"),
                    EventPlace(22.32, 114.17, EventPlacePrecision.City, "HK", "Hong Kong"),
                    EventPlace(51.34, 12.37, EventPlacePrecision.City, "DE", "Leipzig"),
                    EventPlace(47.37, 8.54, EventPlacePrecision.City, "CH", "Zürich"),
                )
                changeLink { it.copy(numberShown = true) }
                changePackage {
                    it.copy(
                        trackingNumber = "LP00123456789012",
                        numberHint = null,
                        carrier = CarrierId.Aliexpress,
                        carrierData = PublicPackageCarrierData(
                            activeTrackingCarrier = CarrierId.SwissPost, activeTrackingNumber = "RB123456789CN",
                            originalCarrier = CarrierId.Aliexpress, originalTrackingNumber = "LP00123456789012",
                        ),
                        trackingEvents = stops.withIndex().reversed().map { (index, stop) ->
                            TrackingEvent(
                                id = UUID.randomUUID(), packageId = handedOver.id,
                                stage = if (index == 0) TrackingStage.Accepted else TrackingStage.InTransit,
                                description = "Scan", location = stop.name,
                                occurredAt = DateParser.isoString(now.minusSeconds((stops.size - index) * 43_200L)),
                                place = if (variant == "handover") stop else null,
                            )
                        },
                        lastSyncedAt = DateParser.isoString(now.minusSeconds(120)),
                    )
                }
            }
            val charges = ImportChargesPreview.charges(importCharges)
            if (charges != null) {
                changePackage { it.copy(carrier = ImportChargesPreview.carrier, carrierData = it.carrierData.copy(importCharges = charges)) }
            }
            val shown = if (variant == "gone") null else response
            val store = ParcelLinkStore(defaults = defaults, read = { _, _ ->
                if (variant == "stopped") throw ParcelLinkStopped()
                shown
            })
            store.previewsSignedIn = signedIn
            store.open(ParcelLinkRoute(id = shown?.link?.id ?: "22222222222A", name = words.name, note = words.note, from = words.from))
            return store
        }
    }
}

@Serializable
private class DebugFixtures(val publicParcel: PublicParcelResponse, val publicGiftParcel: PublicParcelResponse)

/**
 * Swift's `ParcelLinkStore.Reader`: reads a link, with its owner key when the site handed the parcel over. The app
 * makes it of its API client, as `APIClient.publicParcel` does on iOS: `GET api/public/parcels/{linkId}`, asking for
 * JSON, past any cache, with a 15-second timeout, and
 * - without cookies (none sent, none kept) and without an `Authorization` header: a link reads the same signed in or
 *   not;
 * - with `X-Parcel-Key` only when there is a key, and the key in that header alone. The device's app alerts follow
 *   the same rule (`DeviceParcels.setAlert`): the key goes along only for a parcel whose owner key this phone holds;
 * - a 404 is null, a 410 throws `ParcelLinkStopped`, a 429 throws `DeliveryApiError.RateLimited` with the wait its
 *   `Retry-After` asks for (`DeliveryApiClient.retryAfterSeconds`), a 200 whose body is not a link (`{}`) throws
 *   `DeliveryApiError.InvalidResponse` and any other status `DeliveryApiError.ServiceFailed`: both a service failure.
 *
 * A read the app cancelled must surface as a `CancellationException`, which leaves the sheet as it was. A network
 * failure, a connection that timed out and an OkHttp `Call.cancel()` from outside the coroutine surface as an
 * `IOException`: offline. A `withTimeout` around the read that runs out (`TimeoutCancellationException`) is offline too.
 */
typealias ParcelLinkReader = suspend (linkId: String, key: String?) -> PublicParcelResponse?

/**
 * Gives a parcel Poste Italiane's import charges, to look at them in a debug build. Debug-only API, as on the iPhone
 * (`#if DEBUG`). `setting` (the iPhone's `-sdt.debug.importCharges`): `unpaid` (with a lower online price), `closed`
 * (the online window closed) or `paid`. In the demo the page of its first parcel on its way opens with them; a link
 * opened with `ParcelLinkStore.debugPreview` carries them.
 */
object ImportChargesPreview {
    val carrier = CarrierId("poste-italiane")

    fun charges(setting: String?): ImportCharges? {
        val charges = ImportCharges(currency = "EUR", customs = 23.41, carrierFee = 11.0, carrierFeeOnline = 8.09)
        return when (setting) {
            "unpaid" -> charges
            "closed" -> charges.copy(carrierFeeOnline = null, statusCode = "FINESTRA_PAGAMENTO_CHIUSA")
            "paid" -> charges.copy(paid = true, statusCode = "PAGATO")
            else -> null
        }
    }

    /** The demo's parcels, the first in transit carried by Poste Italiane with the charges. */
    fun charging(parcels: List<Parcel>, setting: String?): List<Parcel> {
        val charges = charges(setting) ?: return parcels
        val index = parcels.indexOfFirst { it.currentStage == TrackingStage.InTransit }.takeIf { it >= 0 }
            ?: parcels.indexOfFirst { it.isActive }.takeIf { it >= 0 }
            ?: return parcels
        val data = parcels[index].carrierData ?: CarrierData()
        return parcels.toMutableList().also {
            it[index] = parcels[index].copy(carrier = carrier, carrierData = data.copy(importCharges = charges))
        }
    }

    /** The demo parcel whose page opens. */
    fun parcel(parcels: List<Parcel>, setting: String?): Parcel? =
        if (charges(setting) == null) null else parcels.firstOrNull { it.carrierData?.importCharges != null }
}
