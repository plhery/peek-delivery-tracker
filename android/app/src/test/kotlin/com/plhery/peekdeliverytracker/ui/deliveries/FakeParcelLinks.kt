package com.plhery.peekdeliverytracker.ui.deliveries

import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.ParcelLinkClaim
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkStore
import com.plhery.peekdeliverytracker.core.UserDefaults
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.GiftWords
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.ParcelNumberHint
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Duration
import java.util.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * The link sheet's stores in memory: a real [ParcelLinkStore] reading through [read], the session and the list's
 * parcels as the test sets them, and the parcel store's keep and follow answering as the test says, each call kept.
 */
class FakeParcelLinks(
    answer: PublicParcelResponse? = LinkFixtures.viewer(),
    session: AuthenticationState = AuthenticationState.Welcome,
    override val tracksWithoutAccount: Boolean = true,
    override val authenticationConfigured: Boolean = true,
) : ParcelLinkSource {
    val defaults = MemoryDefaults()

    /** What a read gives: a response, null for a link gone, or what it throws. */
    var read: suspend (id: String, key: String?) -> PublicParcelResponse? = { _, _ -> answer }

    /** Each read, its link and its key. */
    val reads = mutableListOf<Pair<String, String?>>()

    override val links = ParcelLinkStore(defaults, now = { CAPTURED }) { id, key ->
        reads += id to key
        read(id, key)
    }
    override val session = MutableStateFlow(session)
    override val parcels = MutableStateFlow<List<Parcel>>(emptyList())
    private val job = SupervisorJob()
    override val scope: CoroutineScope get() = CoroutineScope(job + Dispatchers.Main.immediate)

    /** How keeping a link ends. */
    var keeping: suspend (ParcelLinkRoute) -> ParcelLinkClaim = { ParcelLinkClaim.Added(KEPT) }
    val kept = mutableListOf<ParcelLinkRoute>()

    /** How following a link on this phone ends. */
    var following: (ParcelLinkRoute, PublicParcelResponse) -> ParcelLinkClaim = { _, _ -> ParcelLinkClaim.Added(FOLLOWED) }
    val followed = mutableListOf<ParcelLinkRoute>()

    /** The parcel this phone follows itself behind any link. */
    var here: UUID? = null
    var deviceParcelsShown = 0
    var signIns = 0
    val actions = mutableListOf<String>()

    override suspend fun keep(link: ParcelLinkRoute): ParcelLinkClaim {
        kept += link
        return keeping(link)
    }

    override fun follow(link: ParcelLinkRoute, response: PublicParcelResponse): ParcelLinkClaim {
        followed += link
        return following(link, response)
    }

    override fun followedHere(response: PublicParcelResponse): UUID? = here

    override fun showDeviceParcels() {
        deviceParcelsShown += 1
    }

    override fun showSignIn() {
        signIns += 1
    }

    override fun errorMessage(error: Throwable): String = error.message ?: "Something went wrong"

    override fun action(name: String) {
        actions += name
    }

    companion object {
        val KEPT: UUID = UUID.fromString("00000000-0000-4000-8000-000000000a01")
        val FOLLOWED: UUID = UUID.fromString("00000000-0000-4000-8000-000000000a02")
        val READER = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))
    }
}

/**
 * The links the sheet's tests open, as the contract's synthetic fixtures (ContractFixtures.json's `publicParcel`, a DPD
 * parcel seen through a viewer's link, its number hidden, and `publicGiftParcel`, a DHL gift on its way), set at the
 * captures' moment ([CAPTURED]).
 */
object LinkFixtures {
    /** An owner key as the site hands one over: 43 characters. */
    const val KEY = "SyntheticOwnerKeyForTestsOnly0123456789abcd"

    private val zurich = EventPlace(47.367, 8.55, EventPlacePrecision.City, "CH", "Zürich")
    private val germany = EventPlace(50.96, 9.68, EventPlacePrecision.Country, "DE", "Germany")

    private fun fixture(name: String): PublicParcelResponse = when (name) {
        "publicParcel" -> PublicParcelResponse(
            link = ParcelLink(
                id = "k7Qm2xHd9RtW", role = ParcelLinkRole.Viewer, kind = ParcelLinkKind.Lookup, createdAt = "2026-10-02T08:00:00.000Z",
                forgetAt = "2026-12-31T08:05:00.000Z", numberShown = false, canKeep = false,
            ),
            parcel = PublicPackage(
                id = UUID.fromString("43000000-0000-0000-0000-000000000004"), numberHint = ParcelNumberHint(head = "", tail = "3456"),
                label = "", carrier = CarrierId.Dpd, createdAt = "2026-10-02T08:00:00+00:00", expectedDelivery = "2026-10-03",
                lastStatusText = "In transit", lastSyncedAt = "2026-10-02T08:05:00+00:00", syncStatus = SyncStatus.Ok,
                carrierData = PublicPackageCarrierData(senderName = "Example Shop", weightKg = 1.2, destinationCountry = "CH"),
                notificationsMuted = false, trackingEvents = emptyList(),
            ),
        )
        else -> {
            val id = UUID.fromString("43000000-0000-0000-0000-000000000006")
            fun scan(n: Int, stage: TrackingStage, description: String, location: String, at: String, place: EventPlace) = TrackingEvent(
                id = UUID.fromString("44000000-0000-0000-0000-00000000006$n"), packageId = id, stage = stage, description = description,
                location = location, occurredAt = at, place = place,
            )
            PublicParcelResponse(
                link = ParcelLink(
                    id = "g8Rn3yJe2SuX", role = ParcelLinkRole.Viewer, kind = ParcelLinkKind.Shared, createdAt = "2026-10-01T08:00:00.000Z",
                    numberShown = false, canKeep = false, gift = true, shared = true,
                ),
                parcel = PublicPackage(
                    id = id, numberHint = ParcelNumberHint(head = "", tail = "6789"), label = "", carrier = CarrierId.Dhl,
                    createdAt = "2026-10-01T08:00:00+00:00", expectedDelivery = "2026-10-03", lastSyncedAt = "2026-10-02T08:05:00+00:00",
                    syncStatus = SyncStatus.Ok, carrierData = PublicPackageCarrierData(destinationCountry = "CH"), notificationsMuted = false,
                    trackingEvents = listOf(
                        scan(3, TrackingStage.OutForDelivery, "With the courier for delivery", "Zürich, CH", "2026-10-02T06:12:00+00:00", zurich),
                        scan(2, TrackingStage.InTransit, "Left the sender", "DE", "2026-10-01T15:48:00+00:00", germany),
                        scan(1, TrackingStage.Accepted, "Left the sender", "DE", "2026-10-01T10:30:00+00:00", germany),
                    ),
                ),
            )
        }
    }

    private fun ago(minutes: Long): String = DateParser.isoString(CAPTURED.minus(Duration.ofMinutes(minutes)))

    private fun event(parcel: PublicPackage, stage: TrackingStage, description: String, minutes: Long, location: String? = null, place: EventPlace? = null) =
        TrackingEvent(
            id = UUID.nameUUIDFromBytes("${parcel.id}-$minutes".toByteArray()), packageId = parcel.id, stage = stage,
            description = description, location = location, occurredAt = ago(minutes), place = place,
        )

    private fun PublicParcelResponse.parcel(edit: (PublicPackage) -> PublicPackage) = copy(parcel = edit(parcel))

    /** A viewer's link, its number hidden: on its way since this morning, updated two minutes ago. */
    fun viewer(): PublicParcelResponse = fixture("publicParcel").parcel {
        it.copy(
            lastSyncedAt = ago(2), expectedDelivery = "2026-10-30",
            trackingEvents = listOf(
                event(it, TrackingStage.InTransit, "In transit", 95, location = "Zürich, CH"),
                event(it, TrackingStage.Accepted, "Parcel handed to DPD", 600),
            ),
        )
    }

    /** A link that shows its number and may be kept. */
    fun keepable(role: ParcelLinkRole = ParcelLinkRole.Viewer): PublicParcelResponse = viewer().let { response ->
        response.copy(link = response.link.copy(numberShown = true, canKeep = true, role = role)).parcel {
            it.copy(trackingNumber = "TESTPARCEL983456", numberHint = null)
        }
    }

    /** The link the site hands over with its owner key: the parcel's own. */
    fun owner(): PublicParcelResponse = keepable(ParcelLinkRole.Owner)

    /** A keepable link whose scans have places: from Leipzig to Zürich. */
    fun routed(): PublicParcelResponse = keepable().parcel {
        it.copy(
            trackingEvents = listOf(
                event(
                    it, TrackingStage.InTransit, "Arrived at the depot", 95, location = "Zürich, CH",
                    place = EventPlace(47.37, 8.54, EventPlacePrecision.City, "CH", "Zürich"),
                ),
                event(
                    it, TrackingStage.Accepted, "Parcel handed to DPD", 900, location = "Leipzig, DE",
                    place = EventPlace(51.34, 12.37, EventPlacePrecision.City, "DE", "Leipzig"),
                ),
            ),
        )
    }

    /** The viewer's link once the parcel has arrived, without places: its box opens. */
    fun arrived(): PublicParcelResponse = viewer().parcel {
        it.copy(trackingEvents = listOf(event(it, TrackingStage.Delivered, "Delivered", 3)) + it.trackingEvents)
    }

    /** A gift on its way to its recipient: with the courier this morning, arriving between 13:00 and 17:00. */
    fun wrappedGift(): PublicParcelResponse = fixture("publicGiftParcel").let { gift ->
        gift.copy(link = gift.link.copy(giftWords = GiftWords(name = "trail running shoes", note = "Happy birthday!", from = "Sam"))).parcel {
            it.copy(
                lastSyncedAt = ago(2), expectedDelivery = "2026-10-29 13:00–17:00",
                trackingEvents = it.trackingEvents.mapIndexed { index, event -> event.copy(occurredAt = ago(50L + index * 600)) },
            )
        }
    }

    /** The same gift delivered: its number shown, and the words its link carried. */
    fun openedGift(): PublicParcelResponse = wrappedGift().let { gift ->
        gift.copy(
            link = gift.link.copy(
                numberShown = true, canKeep = true,
                giftWords = GiftWords(
                    name = "trail running shoes", note = "Happy birthday, Alex! I hope these keep up with you on the trails.", from = "Sam",
                ),
            ),
        ).parcel {
            it.copy(
                trackingNumber = "TESTPARCEL123789", numberHint = null,
                trackingEvents = listOf(event(it, TrackingStage.Delivered, "Delivered", 3, location = "Zürich, CH")) + it.trackingEvents,
            )
        }
    }
}

/** A UserDefaults suite in memory. */
class MemoryDefaults(private val values: MutableMap<String, Any> = mutableMapOf()) : UserDefaults {
    override fun bool(forKey: String): Boolean = values[forKey] as? Boolean ?: false

    override fun set(value: Boolean, forKey: String) {
        values[forKey] = value
    }

    override fun data(forKey: String): ByteArray? = values[forKey] as? ByteArray

    override fun set(value: ByteArray, forKey: String) {
        values[forKey] = value
    }

    override fun string(forKey: String): String? = values[forKey] as? String

    override fun set(value: String, forKey: String) {
        values[forKey] = value
    }

    override fun removeObject(forKey: String) {
        values.remove(forKey)
    }

    override fun hasObject(forKey: String): Boolean = forKey in values

    /** Everything saved, as text where it is bytes. */
    fun dump(): String = values.entries.joinToString { (key, value) -> "$key=${if (value is ByteArray) value.decodeToString() else value}" }
}
