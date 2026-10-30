package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeeded
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeededField
import com.plhery.peekdeliverytracker.core.api.CarrierInputSuggested
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.time.ZoneId
import java.util.Locale
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.regex.Pattern
import kotlin.math.abs
import kotlin.math.roundToLong

// ParcelLogic.swift. A tone's colour (`ParcelTone.color`) and a menu item's keyboard shortcut
// (`ParcelMenuItem.shortcut`) are SwiftUI's, and the app's.

enum class ParcelTone { Normal, Warning, Complete }

/** A carrier's pickup point: the first line names it, any further lines give its address. */
data class PickupPoint(val name: String, val address: String?) {
    /** Name and address on one line, for a maps search or the clipboard. */
    val query: String get() = listOfNotNull(name, address).joinToString(", ")

    /**
     * Directions when the carrier gives an address; a search when it only names the place, so the person can check
     * which one it is.
     */
    val mapsUrl: String?
        get() {
            val value = query.addingPercentEncoding(mapsAllowed)
            return "https://maps.apple.com/?${if (address == null) "q" else "daddr"}=$value"
        }

    companion object {
        private val newlines = Regex("[\n\u000B\u000C\r\u0085\u2028\u2029]")
        private val mapsAllowed = UrlComponents.URL_QUERY_ALLOWED - setOf('&', '=', '+', '?', '#')

        /** Swift's failable `init?(_ text:)`: none without a line that names the place. */
        operator fun invoke(text: String?): PickupPoint? {
            val lines = (text ?: "").split(newlines).map { it.trimmingWhitespaces() }.filter { it.isNotEmpty() }
            val first = lines.firstOrNull() ?: return null
            return PickupPoint(first, if (lines.size > 1) lines.drop(1).joinToString(", ") else null)
        }
    }
}

data class StageMetadata(val symbol: String, val tone: ParcelTone, val progress: Int)

val TrackingStage.Companion.core: List<TrackingStage>
    get() = listOf(
        TrackingStage.Pending, TrackingStage.Registered, TrackingStage.Accepted, TrackingStage.InTransit,
        TrackingStage.OutForDelivery, TrackingStage.Delivered,
    )

val TrackingStage.metadata: StageMetadata
    get() = when (this) {
        TrackingStage.Pending -> StageMetadata("magnifyingglass", ParcelTone.Normal, 0)
        TrackingStage.Registered -> StageMetadata("doc.text", ParcelTone.Normal, 1)
        TrackingStage.Accepted -> StageMetadata("shippingbox", ParcelTone.Normal, 2)
        TrackingStage.InTransit -> StageMetadata("truck.box", ParcelTone.Normal, 3)
        TrackingStage.Customs -> StageMetadata("building.columns", ParcelTone.Warning, 3)
        TrackingStage.Exception -> StageMetadata("exclamationmark.circle", ParcelTone.Warning, 3)
        TrackingStage.OutForDelivery -> StageMetadata("bicycle", ParcelTone.Normal, 4)
        TrackingStage.FailedAttempt -> StageMetadata("exclamationmark.triangle", ParcelTone.Warning, 4)
        TrackingStage.ReadyForPickup -> StageMetadata("storefront", ParcelTone.Warning, 4)
        TrackingStage.Delivered -> StageMetadata("checkmark.circle.fill", ParcelTone.Complete, 5)
        TrackingStage.Returned -> StageMetadata("arrow.uturn.backward.circle", ParcelTone.Warning, 5)
    }

val TrackingStage.eventOrder: Int
    get() = when (this) {
        TrackingStage.Pending -> 0
        TrackingStage.Registered -> 1
        TrackingStage.Accepted -> 2
        TrackingStage.InTransit -> 3
        TrackingStage.Customs -> 4
        TrackingStage.Exception -> 5
        TrackingStage.OutForDelivery -> 6
        TrackingStage.FailedAttempt -> 7
        TrackingStage.ReadyForPickup -> 8
        TrackingStage.Delivered -> 9
        TrackingStage.Returned -> 10
    }

val TrackingStage.localizationKey: String get() = "stage.$rawValue"
val TrackingStage.isFinal: Boolean get() = this == TrackingStage.Delivered || this == TrackingStage.Returned

val TrackingStage.deliveryActivityPhase: DeliveryActivityPhase?
    get() = when (this) {
        TrackingStage.OutForDelivery -> DeliveryActivityPhase.OutForDelivery
        TrackingStage.Delivered -> DeliveryActivityPhase.Delivered
        TrackingStage.FailedAttempt -> DeliveryActivityPhase.FailedAttempt
        TrackingStage.ReadyForPickup -> DeliveryActivityPhase.ReadyForPickup
        TrackingStage.Returned -> DeliveryActivityPhase.Returned
        TrackingStage.Exception -> DeliveryActivityPhase.Exception
        else -> null
    }

/** Swift's `Dictionary(_:uniquingKeysWith: { first, _ in first })` keyed by id. */
private fun <T> List<T>.firstById(id: (T) -> UUID): Map<UUID, T> {
    val map = HashMap<UUID, T>(size)
    for (element in this) map.putIfAbsent(id(element), element)
    return map
}

/** The carrier an automatic correction replaced, for 12 hours. A parcel filed without a carrier only found one: nothing was swapped. */
fun Parcel.automaticallyChangedFrom(at: Instant = Instant.now()): CarrierId? {
    val from = carrierData?.autoChangedFrom ?: return null
    if (from == carrier || from == CarrierId.Unknown || from == CarrierId.InternationalPost) return null
    if (carrierData?.autoChangedTo != carrier) return null
    val changed = carrierData?.autoChangedAt?.let(DateParser::date) ?: return null
    if (at < changed || at.timeIntervalSince(changed) >= 12 * 60 * 60) return null
    return from
}

/** A carrier that knows the number but needs this input before it can track the parcel. */
val Parcel.inputNeeded: CarrierInputNeeded? get() = carrierData?.routing?.inputNeeded

/**
 * Recognition found another carrier that needs the delivery postcode, or the parcel's own carrier needs it to find
 * the parcel, which other sources track meanwhile, while it is still on its way. With nothing tracking it, the sync
 * error asks instead.
 */
val Parcel.inputNeededPrompt: CarrierInputNeeded?
    get() {
        val inputNeeded = inputNeeded ?: return null
        if (inputNeeded.field != CarrierInputNeededField.DpdPostcode || !isActive) return null
        if (inputNeeded.carrier == carrier && (dpdPostcode?.nonEmpty != null || syncError == "carrier:input_required")) return null
        return inputNeeded
    }

/**
 * The stored postcode the parcel's own carrier refused on its last answer (PostNL finding no parcel with it), which
 * its owner is asked to check.
 */
val Parcel.refusedPostcode: String?
    get() {
        if (carrierData?.routing?.postcodeRefused?.carrier != carrier) return null
        return dpdPostcode?.nonEmpty
    }

/** Nobody has answered for the number yet, and its shape points to a carrier that needs the delivery postcode before it can be asked about it. */
val Parcel.inputSuggestedPrompt: CarrierInputSuggested?
    get() {
        if (inputNeededPrompt != null) return null
        val suggested = carrierData?.routing?.inputSuggested ?: return null
        if (carrier != CarrierId.Unknown || isArchived || currentEvent != null) return null
        return suggested
    }

/**
 * Whether the parcel's carrier shows more with the delivery postcode it was saved without. An account's own postcode
 * is given at the next check, so this asks those who gave none, once the parcel has been checked.
 */
fun Parcel.showsMoreWithPostcode(catalog: CarrierCatalog): Boolean {
    if (inputNeededPrompt != null || inputSuggestedPrompt != null || dpdPostcode?.nonEmpty != null || !isActive ||
        lastSyncedAt == null
    ) return false
    return catalog.requirements(carrier, trackingNumber)
        .any { it.field == CarrierRequirement.Field.DpdPostcode && it.showsMore == true }
}

/** How long a carrier may go without listing a number of its own format before the reader is asked to check it, in seconds. */
val Parcel.Companion.likelyCarrierWait: Double get() = 7 * 24 * 60 * 60.0

/**
 * Nobody has answered for the number yet, and it points to a carrier that does not list it yet: the carrier the parcel
 * probably travels with, which its card and header name as probable.
 */
val Parcel.likelyCarrier: CarrierId?
    get() {
        val likely = carrierData?.routing?.likelyCarrier ?: return null
        if (carrier != CarrierId.Unknown || hasCarrierUpdate) return null
        return likely.carrier
    }

/** Swift's `(carrier:, waited:)` from `likelyCarrierPrompt`. */
data class LikelyCarrierPrompt(val carrier: CarrierId, val waited: Boolean)

/**
 * The likely carrier, said under the header. `waited` once it has not listed the number for a week, when its owner is
 * asked to check the number or choose another carrier.
 */
fun Parcel.likelyCarrierPrompt(now: Instant = Instant.now()): LikelyCarrierPrompt? {
    if (inputNeededPrompt != null || inputSuggestedPrompt != null) return null
    val likely = carrierData?.routing?.likelyCarrier ?: return null
    if (likelyCarrier == null || isArchived) return null
    val since = DateParser.date(likely.since)
    return LikelyCarrierPrompt(likely.carrier, since?.let { now.timeIntervalSince(it) >= Parcel.likelyCarrierWait } ?: false)
}

/**
 * Nobody has answered for the number, its format points to no carrier of its own, and it looks like a shop's order
 * number or is shorter than tracking numbers are: its owner is told where the tracking number is, and may choose the
 * carrier.
 */
val Parcel.nonTrackingShape: NonTrackingShape?
    get() {
        if (inputNeededPrompt != null || inputSuggestedPrompt != null || carrierData?.routing?.likelyCarrier != null) return null
        if (carrier != CarrierId.Unknown || isArchived || hasCarrierUpdate || syncError == "carrier:placeholder_number") return null
        return CarrierCatalog.nonTrackingShape(CarrierCatalog.normalize(trackingNumber))
    }

/** `Parcel.eventPrecedes` as a comparator, newest first. */
internal val eventOrderComparator: Comparator<TrackingEvent> = Comparator { lhs, rhs ->
    when {
        Parcel.eventPrecedes(lhs, rhs) -> -1
        Parcel.eventPrecedes(rhs, lhs) -> 1
        else -> 0
    }
}

val Parcel.sortedEvents: List<TrackingEvent> get() = trackingEvents.sortedWith(eventOrderComparator)

/**
 * The journal's rows, newest first. A scan of a relay pair (`relayOf`) is a line under the scan it names, and keeps
 * a row of its own when that scan is not there.
 */
val Parcel.journalEntries: List<JournalEntry>
    get() {
        val relays = relayLines(trackingEvents)
        val folded = relays.values.flatten().mapTo(HashSet()) { it.id }
        return sortedEvents.filter { it.id !in folded }
            .map { JournalEntry(it, (relays[it.id] ?: emptyList()).sortedWith(eventOrderComparator)) }
    }

/** The scans of relay pairs the journal shows as a line under another, by the scan they name. */
internal fun relayLines(events: List<TrackingEvent>): Map<UUID, List<TrackingEvent>> {
    val byId = events.firstById { it.id }
    val relays = HashMap<UUID, MutableList<TrackingEvent>>()
    for (event in events) {
        val scanId = event.relayOf ?: continue
        if (scanId == event.id) continue
        val scan = byId[scanId] ?: continue
        if (scan.relayOf != null) continue
        relays.getOrPut(scanId) { mutableListOf() }.add(event)
    }
    return relays
}

/** The country code of the first place the parcel was scanned, which is where it was posted. */
val Parcel.stampOrigin: String? get() = sortedEvents.lastOrNull { it.place != null }?.place?.country

/** The day and month the parcel was delivered, as a postmark shows it: 28.09. */
val Parcel.stampDeliveryDate: String?
    get() {
        if (!isDelivered) return null
        val date = currentEvent?.displayDate ?: return null
        val day = date.atZone(ZoneId.systemDefault())
        return String.format(Locale.ROOT, "%02d.%02d", day.dayOfMonth, day.monthValue)
    }

/**
 * Whether refreshed parcels bring new tracking: a changed timeline, status line or delivery date. A parcel that
 * replaced another is compared with it.
 */
fun Parcel.Companion.trackingChanged(from: List<Parcel>, to: List<Parcel>): Boolean {
    val earlier = from.firstById { it.id }
    return to.any { parcel ->
        val previous = earlier[parcel.id] ?: parcel.carrierData?.originalPackageId?.let { earlier[it] } ?: return@any true
        parcel.trackingEvents != previous.trackingEvents ||
            parcel.lastStatusText != previous.lastStatusText ||
            parcel.expectedDelivery != previous.expectedDelivery
    }
}

/** Newest first; scans of one instant by delivery progress, then by id. */
fun Parcel.Companion.eventPrecedes(lhs: TrackingEvent, rhs: TrackingEvent): Boolean {
    val left = DateParser.date(lhs.occurredAt) ?: Instant.EPOCH
    val right = DateParser.date(rhs.occurredAt) ?: Instant.EPOCH
    if (left == right) {
        if (lhs.stage != rhs.stage) return lhs.stage.eventOrder > rhs.stage.eventOrder
        return lhs.id.uuidString > rhs.id.uuidString
    }
    return left > right
}

val Parcel.currentEvent: TrackingEvent? get() = LatestEventCache.latest(this)

val Parcel.currentStage: TrackingStage? get() = currentEvent?.stage

/** Where a parcel waiting for collection waits, for its status line. */
val Parcel.pickupPlace: String?
    get() = if (currentStage == TrackingStage.ReadyForPickup) PickupPoint(carrierData?.pickupPoint)?.name else null

val Parcel.hasCarrierUpdate: Boolean get() = currentEvent?.let { it.stage != TrackingStage.Pending } ?: false
val Parcel.isUnannounced: Boolean get() = !hasCarrierUpdate && syncStatus == SyncStatus.Waiting
val Parcel.isArchived: Boolean get() = archivedAt != null
val Parcel.isDelivered: Boolean get() = currentStage == TrackingStage.Delivered
val Parcel.isReturned: Boolean get() = currentStage == TrackingStage.Returned
val Parcel.isActive: Boolean get() = !isArchived && !(currentStage?.isFinal ?: false)

/** One of a parcel's numbers: Swift's `(carrier:, number:, tracksAs:)`. */
data class ParcelTrackingNumber(val carrier: CarrierId, val number: String, val tracksAs: Boolean)

/** The carrier the followed one names as delivering, and the number it gave. */
data class NamedDelivery(val carrier: CarrierId, val number: String?)

/**
 * Distinct numbers, the followed one first, then the named delivering carrier's own, and last the number the
 * followed carrier tracks the parcel by when it is none of them. That one is `tracksAs`: a reference the
 * carrier's search resolved to a parcel, or a number typed with more or less than the carrier's own, shown so
 * the reader can tell which parcel the carrier answered for.
 */
val Parcel.trackingNumbers: List<ParcelTrackingNumber>
    get() {
        val delivery = ParcelTrackingNumber(activeTrackingCarrier, carrierData?.activeTrackingNumber?.nonEmpty ?: trackingNumber, false)
        val original = ParcelTrackingNumber(
            carrierData?.originalCarrier ?: carrier, carrierData?.originalTrackingNumber?.nonEmpty ?: trackingNumber, false,
        )
        val numbers = if (delivery.number == original.number) mutableListOf(delivery) else mutableListOf(delivery, original)
        val named = namedDelivery
        if (named?.number != null) numbers += ParcelTrackingNumber(named.carrier, named.number, false)
        val canonical = carrierData?.canonicalTrackingNumber?.nonEmpty
        if (canonical == null ||
            numbers.any { CarrierCatalog.normalize(it.number) == CarrierCatalog.normalize(canonical) }
        ) return numbers
        return numbers + ParcelTrackingNumber(delivery.carrier, canonical, true)
    }

/**
 * What a number is called beside it: the carrier's name once there are several, and what the carrier tracks
 * the parcel as for the number it resolved the typed one to.
 */
fun Parcel.trackingNumberLabel(entry: ParcelTrackingNumber, localizer: Localizer): String {
    val name = CarrierCatalog.shared.info(entry.carrier, localizer.language).displayName
    if (entry.tracksAs) return localizer.text("detail.tracksAs", mapOf("carrier" to name))
    return if (trackingNumbers.count { !it.tracksAs } > 1) name else localizer.text("detail.trackingNumber")
}

val Parcel.displayedCarrier: CarrierId get() = carrierData?.originalCarrier ?: activeTrackingCarrier

/**
 * The carrier the followed one names as delivering, while the parcel is not followed there yet, and the
 * number it gave. Once it is followed, the parcel has been handed over and `trackingSource` says so.
 */
val Parcel.namedDelivery: NamedDelivery?
    get() {
        val data = carrierData ?: return null
        val carrier = data.deliveryCarrier ?: return null
        if (data.originalCarrier != null || carrier == displayedCarrier ||
            CarrierCatalog.shared.definitions[carrier] == null
        ) return null
        val number = data.deliveryTrackingNumber?.nonEmpty
        return NamedDelivery(carrier, if (number == trackingNumber) null else number)
    }

/** Who delivers when it isn't the carrier shown: the one the parcel was handed to, else the one its carrier names. */
val Parcel.deliveringCarrier: CarrierId?
    get() = if (activeTrackingCarrier != displayedCarrier) activeTrackingCarrier else namedDelivery?.carrier

val Parcel.amazonShippingHistoryExpired: Boolean
    get() = carrier == CarrierId.AmazonShipping && syncError == "amazon_shipping_history_expired"

val Parcel.activeTrackingCarrier: CarrierId
    get() {
        if (CarrierCatalog.shared.requiresAmazonAccount(carrier, trackingNumber)) return CarrierId.AmazonLogistics
        trackingSource?.let { return it }
        return if (CarrierCatalog.supportsSwissPostHandoff(trackingNumber)) CarrierId.Aliexpress else carrier
    }

/** Whether only the owner can make the number trackable: a placeholder, or one its carrier does not issue. */
val Parcel.numberToCheck: Boolean
    get() = syncError == "carrier:placeholder_number" || syncError == "carrier:refused_number"

val Parcel.displayStatus: ParcelDisplayStatus
    get() {
        if (!hasCarrierUpdate && syncStatus == SyncStatus.Error && numberToCheck) {
            return ParcelDisplayStatus("status.checkNumber", ParcelTone.Warning, syncing = false)
        }
        if (!hasCarrierUpdate && !CarrierCatalog.shared.tracksAutomatically(activeTrackingCarrier)) {
            return ParcelDisplayStatus("status.unsupported", ParcelTone.Warning, syncing = false)
        }
        if (!hasCarrierUpdate && (syncStatus == SyncStatus.Pending || syncStatus == SyncStatus.Syncing)) {
            return ParcelDisplayStatus("status.syncing", ParcelTone.Normal, syncing = true)
        }
        if (!hasCarrierUpdate && syncStatus == SyncStatus.Error) {
            return ParcelDisplayStatus("status.failed", ParcelTone.Warning, syncing = false)
        }
        if (!hasCarrierUpdate && syncStatus == SyncStatus.Unsupported) {
            return ParcelDisplayStatus("status.unsupported", ParcelTone.Warning, syncing = false)
        }
        if (isUnannounced) {
            val key = when {
                carrierData?.routing?.providerInputNeeded != null -> "status.inputNeeded"
                carrier != CarrierId.Unknown -> "status.unannounced"
                likelyCarrier != null -> "status.likelyCarrier"
                else -> "status.noHistory"
            }
            return ParcelDisplayStatus(key, ParcelTone.Normal, syncing = false)
        }
        val currentStage = currentStage ?: return ParcelDisplayStatus("status.unannounced", ParcelTone.Normal, syncing = false)
        return ParcelDisplayStatus(currentStage.localizationKey, currentStage.metadata.tone, syncing = false)
    }

/** What a card says the parcel needs: no failed update for a number only the owner can correct, whose status says to check it. */
fun Parcel.notice(now: Instant = Instant.now()): ParcelAttention? {
    val reason = attention(now)
    return if (reason == ParcelAttention.SyncError && numberToCheck) null else reason
}

/** Whether a card says the latest update failed: not for a number only the owner can correct. */
val Parcel.updateFailed: Boolean get() = syncStatus == SyncStatus.Error && !numberToCheck

fun Parcel.attention(now: Instant = Instant.now()): ParcelAttention? {
    when (currentStage) {
        TrackingStage.FailedAttempt -> return ParcelAttention.FailedAttempt
        TrackingStage.ReadyForPickup -> return ParcelAttention.ReadyForPickup
        TrackingStage.Exception -> return ParcelAttention.Exception
        TrackingStage.Customs -> return ParcelAttention.Customs
        else -> {}
    }
    // Made-up moves say nothing of where the parcel is, whether the latest check failed or how old they are.
    if (GeneratedTrack.scans(trackingEvents).isNotEmpty()) return ParcelAttention.Generated
    if (syncStatus == SyncStatus.Error) return ParcelAttention.SyncError
    val event = currentEvent
    if (event != null && event.stage in listOf(TrackingStage.Registered, TrackingStage.Accepted, TrackingStage.InTransit)) {
        val update = DateParser.date(event.occurredAt)
        if (update != null && now.timeIntervalSince(update) >= 4 * 86_400) return ParcelAttention.Stalled
    }
    if (isUnannounced) {
        val created = DateParser.date(createdAt)
        if (created != null && now.timeIntervalSince(created) >= 2 * 86_400) return ParcelAttention.NotAnnounced
    }
    return null
}

/** Whether the map has a place to draw: a generated track's are left out. */
val Parcel.hasMappedPlace: Boolean get() = GeneratedTrack.without(trackingEvents).any { it.place != null }

/**
 * Whether the carrier has only ever announced the parcel, its first label at least a day ago: the sender made the
 * label and still has the parcel. The carrier has nothing more to say until they hand it over.
 */
fun Parcel.awaitingHandover(now: Instant = Instant.now()): Boolean {
    val carrierEvents = trackingEvents.filter { it.stage != TrackingStage.Pending }
    if (carrierEvents.isEmpty() || !carrierEvents.all { it.stage == TrackingStage.Registered }) return false
    val first = carrierEvents.mapNotNull { DateParser.date(it.occurredAt) }.minOrNull() ?: return false
    return now.timeIntervalSince(first) >= 86_400
}

private val expectedDayPattern: Pattern =
    Pattern.compile("^(\\d{4}-\\d{2}-\\d{2})(?:$|[ T])", unicodeCharacterClass)

val Parcel.expectedDayKey: String?
    get() {
        val expectedDelivery = expectedDelivery ?: return null
        val match = expectedDayPattern.matcher(expectedDelivery)
        return if (match.find()) match.group(1) else null
    }

val Parcel.searchText: String
    get() = (listOf(label, trackingNumber, lastStatusText ?: "") + trackingEvents.flatMap { listOf(it.description, it.location ?: "") })
        .joinToString(" ")
        .lowercase()

/**
 * Filters, sorts and every card ask for a parcel's latest event several times per render. Keep each answer until
 * the parcel's history changes: copies of a parcel share their event list, so a hit compares the list, not events.
 */
private object LatestEventCache {
    private class Entry(val events: List<TrackingEvent>, val latest: TrackingEvent?)

    private val entries = ConcurrentHashMap<UUID, Entry>()

    fun latest(parcel: Parcel): TrackingEvent? {
        val events = parcel.trackingEvents
        entries[parcel.id]?.let { if (it.events == events) return it.latest }
        // A line under another scan in the journal is part of that scan's step, whichever is newer.
        val lines = relayLines(events).values.flatten().mapTo(HashSet()) { it.id }
        val carrier = events.filter { it.stage != TrackingStage.Pending && it.id !in lines }.sortedWith(eventOrderComparator)
        val latest = current(carrier) ?: newest(events)
        entries[parcel.id] = Entry(events, latest)
        return latest
    }

    /** Swift's `min(by: Parcel.eventPrecedes)`: the first of the newest. */
    private fun newest(events: List<TrackingEvent>): TrackingEvent? {
        var newest = events.firstOrNull() ?: return null
        for (event in events.drop(1)) if (Parcel.eventPrecedes(event, newest)) newest = event
        return newest
    }

    /** Carrier stages before the parcel moves, in order. */
    private val early = listOf(TrackingStage.Registered, TrackingStage.Accepted)

    /** Stages of a parcel on its way, short of a final one. */
    private val moving = setOf(
        TrackingStage.InTransit, TrackingStage.Customs, TrackingStage.OutForDelivery, TrackingStage.FailedAttempt,
        TrackingStage.ReadyForPickup,
    )

    /**
     * An announcement or acceptance after the parcel has moved on is a notice or a reworded scan, not a step back:
     * the scan before it still says where the parcel is. After a problem, a return or a delivery, a new label starts
     * over.
     */
    private fun current(newestFirst: List<TrackingEvent>): TrackingEvent? {
        var current = newestFirst.firstOrNull() ?: return null
        for (event in newestFirst.drop(1)) {
            val rank = early.indexOf(current.stage)
            if (rank < 0) break
            if (event.stage in moving) return event
            val earlier = early.indexOf(event.stage)
            if (earlier < 0) break
            if (earlier > rank) current = event
        }
        return current
    }
}

/** A row of the journal: a scan, and the earlier carrier's copies of it. */
data class JournalEntry(val event: TrackingEvent, val relays: List<TrackingEvent>) {
    val id: UUID get() = event.id
}

data class ParcelDisplayStatus(val key: String, val tone: ParcelTone, val syncing: Boolean)

/**
 * The scans of a generated track, as the web app tells them (`src/lib/generatedTrack.ts`). The parcel's newest scans
 * each moved it on in transit to a new place, each instant at one place, one hour to two days after the scan before
 * and not in whole minutes, and three of those gaps are one interval to within 2 s. The track runs from the oldest
 * move at a repeated interval to the newest scan. No network moves a parcel on at one interval to the second; a
 * carrier that rounds its clock gives whole minutes. None unless the newest scan is such a move.
 */
object GeneratedTrack {
    private class Moment {
        val scans = mutableListOf<TrackingEvent>()
        val places = HashSet<String>()
        var transit = true
    }

    private class Move(val gap: Long, val scans: List<TrackingEvent>)

    fun scans(events: List<TrackingEvent>): List<TrackingEvent> {
        // Peek's own rows and notices are not the carrier moving the parcel.
        val instants = HashMap<Instant, Moment>()
        for (event in events) {
            if (event.stage == TrackingStage.Pending) continue
            val at = DateParser.date(event.occurredAt) ?: continue
            val instant = instants.getOrPut(at) { Moment() }
            instant.scans.add(event)
            val place = event.location?.trimmingWhitespacesAndNewlines()?.lowercase()
            if (!place.isNullOrEmpty()) instant.places.add(place)
            instant.transit = instant.transit && event.stage == TrackingStage.InTransit
        }
        val newestFirst = instants.entries.sortedByDescending { it.key }
        val moves = mutableListOf<Move>()
        for (index in 1 until newestFirst.size) {
            val scan = newestFirst[index - 1]
            val previous = newestFirst[index]
            val gap = scan.key.timeIntervalSince(previous.key).roundToLong()
            val moved = scan.value.transit && previous.value.transit && scan.value.places.size == 1 &&
                previous.value.places.size == 1 && scan.value.places != previous.value.places
            if (!moved || gap !in 3_600L..172_800L || gap % 60 == 0L) break
            moves.add(Move(gap, scan.value.scans))
        }
        val repeated = { gap: Long -> moves.count { abs(it.gap - gap) <= 2 } >= 3 }
        // Moves before the first repeated one may be the carrier's own.
        val first = moves.indexOfLast { repeated(it.gap) }
        if (first < 0) return emptyList()
        return moves.subList(0, first + 1).flatMap { it.scans }
    }

    /** The scans with a generated track's left out: where the parcel really was last. */
    fun without(events: List<TrackingEvent>): List<TrackingEvent> {
        val generated = scans(events).map { it.id }.toSet()
        return if (generated.isEmpty()) events else events.filter { it.id !in generated }
    }
}

enum class ParcelAttention(val rawValue: String) {
    SyncError("sync_error"),
    FailedAttempt("failed_attempt"),
    ReadyForPickup("ready_for_pickup"),
    Customs("customs"),
    Exception("exception"),
    Generated("generated"),
    Stalled("stalled"),
    NotAnnounced("not_announced");

    val localizationKey: String get() = "attention.$rawValue"
}

enum class ParcelStatusFilter(val rawValue: String) {
    All("all"), Active("active"), Attention("attention"), Today("today"), Delivered("delivered"), Archived("archived");

    val id: String get() = rawValue
    val localizationKey: String get() = "view.filter.$rawValue"
}

enum class ParcelSort(val rawValue: String) {
    Priority("priority"), Updated("updated"), Newest("newest"), Eta("eta"), Carrier("carrier");

    val id: String get() = rawValue
    val localizationKey: String get() = "view.sort.$rawValue"
}

enum class ParcelSectionKind(val rawValue: String) {
    Attention("attention"), Today("today"), Active("active"), Delivered("delivered"), Returned("returned"), Archived("archived");

    val id: String get() = rawValue
}

data class ParcelSection(val kind: ParcelSectionKind, val parcels: List<Parcel>) {
    val id: String get() = kind.id
}

object ParcelOrganizer {
    private val separators = Pattern.compile("[\\s.-]", unicodeCharacterClass).toRegex()

    fun visible(
        parcels: List<Parcel>,
        query: String,
        status: ParcelStatusFilter,
        carrier: CarrierId?,
        sort: ParcelSort,
        now: Instant = Instant.now(),
        catalog: CarrierCatalog = CarrierCatalog.shared,
    ): List<Parcel> {
        val trimmed = query.trimmingWhitespacesAndNewlines().lowercase()
        val compact = trimmed.replace(separators, "")
        val today = dayKey(now)
        val matching = parcels
            .filter { parcel ->
                if (trimmed.isEmpty()) return@filter true
                val carrierName = catalog.info(parcel.carrier).displayName.lowercase()
                val compactTracking = parcel.trackingNumber.lowercase().replace(separators, "")
                parcel.searchText.contains(trimmed) ||
                    carrierName.contains(trimmed) ||
                    (compact.isNotEmpty() && compactTracking.contains(compact))
            }
            .filter { parcel ->
                when (status) {
                    ParcelStatusFilter.All -> true
                    ParcelStatusFilter.Active -> parcel.isActive
                    ParcelStatusFilter.Attention -> parcel.isActive && parcel.attention(now) != null
                    ParcelStatusFilter.Today -> parcel.isActive && parcel.expectedDayKey == today
                    ParcelStatusFilter.Delivered -> !parcel.isArchived && parcel.isDelivered
                    ParcelStatusFilter.Archived -> parcel.isArchived
                }
            }
            .filter { carrier == null || it.carrier == carrier }
        return sorted(matching, sort, catalog)
    }

    /** Reads each parcel's sort fields once, instead of twice per comparison. */
    fun sorted(parcels: List<Parcel>, by: ParcelSort, catalog: CarrierCatalog = CarrierCatalog.shared): List<Parcel> {
        val collator = caseInsensitiveCollator()
        return parcels.map { SortKey(it, by, catalog, collator) }
            .sortedWith { left, right -> if (left.precedes(right)) -1 else if (right.precedes(left)) 1 else 0 }
            .map { it.parcel }
    }

    /** Feature an arrival or pickup. Issues are shown separately as compact notices. */
    fun nextDelivery(from: List<Parcel>, now: Instant = Instant.now()): Parcel? {
        val today = dayKey(now)
        val candidates = from.filter { parcel ->
            if (!parcel.isActive) return@filter false
            val reason = parcel.attention(now)
            reason == null || reason == ParcelAttention.ReadyForPickup ||
                (reason == ParcelAttention.SyncError && parcel.currentStage == TrackingStage.OutForDelivery)
        }

        fun urgency(parcel: Parcel): Int {
            if (parcel.currentStage == TrackingStage.ReadyForPickup) return 0
            return if (parcel.currentStage == TrackingStage.OutForDelivery || parcel.expectedDayKey == today) 1 else 2
        }

        class Candidate(val urgency: Int, val placed: Boolean, val key: SortKey)

        val collator = caseInsensitiveCollator()
        fun less(left: Candidate, right: Candidate): Boolean {
            if (left.urgency != right.urgency) return left.urgency < right.urgency
            // Next up draws the parcel's route: of two due the same day, the one with places to draw goes first.
            if (left.key.expected == right.key.expected && left.placed != right.placed) return left.placed
            return left.key.precedes(right.key)
        }
        // Swift's `min(by:)`: the first of the least.
        var next: Candidate? = null
        for (parcel in candidates) {
            val candidate = Candidate(
                urgency(parcel), parcel.trackingEvents.any { it.place != null },
                SortKey(parcel, ParcelSort.Priority, CarrierCatalog.shared, collator),
            )
            if (next == null || less(candidate, next)) next = candidate
        }
        return next?.key?.parcel
    }

    fun sections(from: List<Parcel>, now: Instant = Instant.now()): List<ParcelSection> {
        val today = dayKey(now)
        val active = from.filter { it.isActive }.map { it to it.attention(now) }
        val attention = active.filter { it.second != null }.map { it.first }
        val arrivingToday = active.filter { it.second == null && it.first.expectedDayKey == today }.map { it.first }
        val onTheWay = active.filter { it.second == null && it.first.expectedDayKey != today }.map { it.first }
        val archived = sortPastParcels(from.filter { it.isArchived })
        val values = listOf(
            ParcelSectionKind.Attention to attention,
            ParcelSectionKind.Today to arrivingToday,
            ParcelSectionKind.Active to onTheWay,
            ParcelSectionKind.Delivered to sortPastParcels(from.filter { !it.isArchived && it.isDelivered }),
            ParcelSectionKind.Returned to sortPastParcels(from.filter { !it.isArchived && it.isReturned }),
            ParcelSectionKind.Archived to archived,
        )
        return values.mapNotNull { if (it.second.isEmpty()) null else ParcelSection(it.first, it.second) }
    }

    /** `localizedCaseInsensitiveCompare`: the current locale's order, case aside. */
    private fun caseInsensitiveCollator(): Comparator<String> =
        LocalizedComparison.CaseInsensitive.comparator(Locale.getDefault())

    private class SortKey(
        val parcel: Parcel,
        val sort: ParcelSort,
        catalog: CarrierCatalog,
        private val collator: Comparator<String>,
    ) {
        val expected: String =
            if (sort == ParcelSort.Priority || sort == ParcelSort.Eta) parcel.expectedDayKey ?: "9999-99-99" else ""
        val updated: String =
            if (sort == ParcelSort.Priority || sort == ParcelSort.Updated) parcel.currentEvent?.occurredAt ?: parcel.createdAt else ""
        val carrierName: String = if (sort == ParcelSort.Carrier) catalog.info(parcel.carrier).displayName else ""

        fun precedes(other: SortKey): Boolean {
            val comparison = when (sort) {
                ParcelSort.Priority ->
                    if (expected.compareTo(other.expected) == 0) other.updated.compareTo(updated) else expected.compareTo(other.expected)
                ParcelSort.Updated -> other.updated.compareTo(updated)
                ParcelSort.Newest -> other.parcel.createdAt.compareTo(parcel.createdAt)
                ParcelSort.Eta -> expected.compareTo(other.expected)
                ParcelSort.Carrier -> collator.compare(carrierName, other.carrierName)
            }
            if (comparison != 0) return comparison < 0
            val label = collator.compare(parcel.label, other.parcel.label)
            return if (label == 0) parcel.id.uuidString < other.parcel.id.uuidString else label < 0
        }
    }

    private fun sortPastParcels(parcels: List<Parcel>): List<Parcel> =
        parcels.map { it to completionSortDate(it) }
            .sortedWith { left, right ->
                if (left.second == right.second) {
                    left.first.id.uuidString.compareTo(right.first.id.uuidString)
                } else {
                    right.second.compareTo(left.second)
                }
            }
            .map { it.first }

    private fun completionSortDate(parcel: Parcel): Instant {
        val event = parcel.currentEvent
        if (event != null && event.stage.isFinal) DateParser.date(event.occurredAt)?.let { return it }
        parcel.archivedAt?.let(DateParser::date)?.let { return it }
        event?.let { DateParser.date(it.occurredAt) }?.let { return it }
        return DateParser.date(parcel.createdAt) ?: distantPast
    }

    /** Foundation's `Date.distantPast`: the first day of year 1. */
    private val distantPast: Instant = Instant.parse("0001-01-01T00:00:00Z")

    /**
     * The parcels the list last showed on their way that have since been delivered, as the list showed them: each
     * keeps that place until its card leaves for the past deliveries.
     */
    fun justDelivered(shown: List<Parcel>, now: List<Parcel>): List<Parcel> {
        val current = now.firstById { it.id }
        return shown.filter { was ->
            if (!was.isActive) return@filter false
            val parcel = current[was.id] ?: return@filter false
            !parcel.isArchived && parcel.isDelivered
        }
    }

    /** The local calendar day as `yyyy-MM-dd`, without building a formatter per call. */
    fun dayKey(date: Instant): String {
        val day = date.atZone(ZoneId.systemDefault())
        return String.format(Locale.ROOT, "%04d-%02d-%02d", day.year, day.monthValue, day.dayOfMonth)
    }
}

/**
 * A parcel delivered since the list was last shown does not jump to the past deliveries: its card first says so
 * where it stands, then travels there. Until then the list keeps the arrangement it had, whatever else the same news
 * changed. A value, as the Swift struct is: each change gives a new one, and the list need only update when it isn't
 * equal to the one it has.
 */
class DeliveredHold private constructor(
    /** The parcels that have just arrived. */
    val ids: Set<UUID>,
    /** Every parcel as the list showed it before they did. */
    private val shown: Map<UUID, Parcel>,
) {
    constructor() : this(emptySet(), emptyMap())

    val isEmpty: Boolean get() = ids.isEmpty()
    fun holds(id: UUID): Boolean = id in ids

    /** The list is about to show `next` where it showed `shown`. A list that nobody is looking at holds nothing, and changes at once. */
    fun receive(shown: List<Parcel>, next: List<Parcel>, watching: Boolean): DeliveredHold {
        if (!watching) return release()
        val current = next.firstById { it.id }
        // A held parcel stays in its place for as long as it is still a delivery to show.
        val holding = ids.filterTo(LinkedHashSet()) { id -> current[id]?.let { !it.isArchived && it.isDelivered } ?: false }
        val arrivals = ParcelOrganizer.justDelivered(shown, next)
        val before = if (holding.isEmpty() && arrivals.isNotEmpty()) shown.firstById { it.id } else this.shown
        holding.addAll(arrivals.map { it.id })
        return if (holding.isEmpty()) release() else DeliveredHold(holding, before)
    }

    fun release(): DeliveredHold = DeliveredHold()

    /** The parcels as the list arranges them: as they were shown, but for one archived or brought back since. */
    fun arranged(parcels: List<Parcel>): List<Parcel> {
        if (ids.isEmpty()) return parcels
        return parcels.map { parcel ->
            val was = shown[parcel.id]
            if (was == null || was.isArchived != parcel.isArchived) parcel else was
        }
    }

    override fun equals(other: Any?): Boolean = other is DeliveredHold && ids == other.ids && shown == other.shown
    override fun hashCode(): Int = 31 * ids.hashCode() + shown.hashCode()
    override fun toString(): String = "DeliveredHold(ids=$ids)"
}

/**
 * How the deliveries stand in their window: a list that opens a parcel over itself, as on a phone, or the list
 * beside the parcel, where both fit side by side. Widths are in density-independent pixels, as SwiftUI's points.
 */
enum class DeliveriesArrangement {
    Stacked,
    SideBySide;

    companion object {
        /**
         * About an iPhone's width for the list, and for the parcel beside it. At the accessibility text sizes each
         * needs more, or a parcel's name breaks in the middle of its words.
         */
        fun listWidth(largeText: Boolean): Double = if (largeText) 440.0 else 320.0
        fun parcelWidth(largeText: Boolean): Double = if (largeText) 480.0 else 390.0

        /** How far a window resized across the line goes before the arrangement turns, so that it doesn't turn back and forth while the window is being dragged. */
        const val slack: Double = 20.0

        /**
         * Side by side needs a window that is wide and tall in its own right, as an iPad's or an open iPhone Duo's,
         * and room for both columns. A phone on its side is wide but short, and keeps its list.
         */
        fun resolve(
            width: Double,
            regularWidth: Boolean,
            regularHeight: Boolean,
            largeText: Boolean,
            current: DeliveriesArrangement,
        ): DeliveriesArrangement {
            if (!regularWidth || !regularHeight) return Stacked
            val both = listWidth(largeText) + parcelWidth(largeText)
            return if (width >= (if (current == SideBySide) both - slack else both + slack)) SideBySide else Stacked
        }
    }
}

/**
 * A parcel's page beside the list, given room for two columns as wide as a phone's, puts its history beside its
 * card, as the web's parcel page does on a wide screen. Over the list, as on a phone, it keeps one column.
 */
object ParcelPageColumns {
    /** A column's least width: about a phone's page, more at the accessibility text sizes. */
    fun column(largeText: Boolean): Double = if (largeText) 420.0 else 340.0
    const val gap: Double = 28.0

    /** The page's margins and the paper's padding, on both sides. */
    const val sides: Double = 2.0 * (16 + 18)

    /** Past this, the columns stop growing and the page stands centred. */
    const val widest: Double = 1080.0

    /** Without a history the second column would only say when the carrier was last asked, and the page keeps one. */
    fun fit(width: Double, besideList: Boolean, largeText: Boolean, history: Boolean): Boolean =
        besideList && history && width >= 2 * column(largeText) + gap + sides
}

/**
 * The passport lies open on two pages, as it does at an iPhone Duo's fold, in a window wide and tall in its own
 * right with room for two pages as wide as a phone's. A phone, even on its side, keeps one page.
 */
object PassportSpread {
    /** The pages' margins, on both sides of each. */
    const val margins: Double = 2.0 * 20

    /** As wide as the single page grows. */
    const val widest: Double = 480.0

    fun fits(width: Double, regularWidth: Boolean, regularHeight: Boolean, largeText: Boolean): Boolean =
        regularWidth && regularHeight && width >= 2 * (ParcelPageColumns.column(largeText) + margins)
}

/**
 * The parcel the deliveries show, over the list or beside it, and whether someone chose it. Every way a parcel
 * opens comes through here: its card, a notification, a link, the widget, Add finding it followed already. A value,
 * as the Swift struct is: each change gives a new one, and the screen need only update when it isn't equal to the
 * one it has.
 */
class DeliveriesNavigation private constructor(
    /** The parcels open over the list, the last on top. Beside the list, the last is the one shown. */
    val path: List<UUID>,
    /** The parcel shown beside the list that nobody chose, so that the column isn't empty. */
    val automaticPick: UUID?,
) {
    constructor(path: List<UUID> = emptyList()) : this(path, null)

    val shown: UUID? get() = path.lastOrNull()
    val shownAutomatically: Boolean get() = automaticPick != null && automaticPick == path.lastOrNull()

    /** The parcel someone opened, if the one shown is theirs. */
    val chosen: UUID? get() = if (shownAutomatically) null else shown

    /** The navigation once the system has moved along `path` itself, as going back does. */
    fun copy(path: List<UUID> = this.path): DeliveriesNavigation = DeliveriesNavigation(path, automaticPick)

    /** Opens the parcel someone asked for: over the list, or beside it in place of the one shown there. */
    fun open(parcelId: UUID): DeliveriesNavigation = DeliveriesNavigation(listOf(parcelId), null)

    /**
     * Takes the window's arrangement and the list's `cards`, in order, with the archive's rows after them while they
     * show. Beside the list, a parcel always shows: the one chosen, else the first card or row, and none only when
     * the list shows neither. A parcel shown by itself gives way to the first card once it leaves the cards. Over the
     * list, nothing opens by itself, and a parcel shown by itself goes back into the list: one chosen stays open over it.
     */
    fun settle(arrangement: DeliveriesArrangement, cards: List<UUID>): DeliveriesNavigation = when (arrangement) {
        DeliveriesArrangement.Stacked -> DeliveriesNavigation(if (shownAutomatically) emptyList() else path, null)
        DeliveriesArrangement.SideBySide -> {
            val shown = shown
            if (shown != null && (!shownAutomatically || shown in cards)) this else pick(cards.firstOrNull())
        }
    }

    /**
     * Opens the parcel that was open when the system last closed the app, once the parcels are back. The navigation
     * once that's settled: the parcel is open, it's gone, or someone opened another first. Null while there are none
     * yet, and nothing changes.
     */
    fun restore(parcelId: UUID, among: List<UUID>): DeliveriesNavigation? {
        if (chosen != null) return this
        if (among.isEmpty()) return null
        return if (parcelId in among) open(parcelId) else this
    }

    /**
     * The parcel shown beside the list has left it, archived, brought back or deleted from its own page or deleted
     * from its card, and the card after it shows instead: the one before it for the last, the first for one that had
     * none. `cards` is the list as it was before the parcel left.
     */
    fun leave(parcelId: UUID, cards: List<UUID>): DeliveriesNavigation {
        if (shown != parcelId) return this
        val index = cards.indexOf(parcelId).takeIf { it >= 0 }
        val after = index?.let { cards.getOrNull(it + 1) }
        val before = index?.let { cards.getOrNull(it - 1) }
        return pick(after ?: before ?: cards.firstOrNull { it != parcelId })
    }

    /**
     * Opens beside the list the card `offset` cards on from the parcel shown, in the list's `cards`, as ↑ and ↓ do,
     * and as a tap on that card would: from a parcel with no card, or none, the first card going down and the last
     * going up. The navigation with that card shown, or null past either end, where the parcel shown stays.
     */
    fun step(offset: Int, through: List<UUID>): DeliveriesNavigation? {
        val shown = shown
        val index = if (shown != null) through.indexOf(shown) else -1
        val card = if (index >= 0) through.getOrNull(index + offset) else if (offset > 0) through.firstOrNull() else through.lastOrNull()
        return card?.let(::open)
    }

    /**
     * The screen to count once the navigation has turned from `after` to this: a parcel someone opened, or the list
     * they went back to. A parcel shown by itself is nobody's view, and beside the list, the list was there all along.
     */
    fun screen(after: DeliveriesNavigation): String? {
        val chosen = chosen
        if (chosen != null) return if (chosen == after.chosen) null else "parcel"
        return if (shown == null && after.chosen != null) "deliveries" else null
    }

    private fun pick(parcelId: UUID?): DeliveriesNavigation = DeliveriesNavigation(listOfNotNull(parcelId), parcelId)

    override fun equals(other: Any?): Boolean =
        other is DeliveriesNavigation && path == other.path && automaticPick == other.automaticPick

    override fun hashCode(): Int = 31 * path.hashCode() + (automaticPick?.hashCode() ?: 0)
    override fun toString(): String = "DeliveriesNavigation(path=$path, automaticPick=$automaticPick)"
}

/**
 * A key the deliveries answer, besides the parcel's own and the refresh button's ⌘R: ⌘N adds, ⌘F finds, Escape
 * closes the search, ⌘Z undoes an archive while its toast shows, and beside a parcel, ↑ and ↓ open the card before
 * or after it and Return opens the one shown by itself.
 */
enum class DeliveriesKey {
    Add, Find, CloseSearch, Undo, Previous, Next, Open;

    companion object {
        /**
         * The keys that act on what is in front. There are none unless the deliveries are `free`: in front, with
         * nothing open over the list or its parcel. While someone types in the search, the field keeps the keys it
         * edits with, and Escape closes the search, which the field doesn't hear.
         */
        fun available(
            free: Boolean,
            searching: Boolean,
            typing: Boolean,
            arrangement: DeliveriesArrangement,
            navigation: DeliveriesNavigation,
            cards: List<UUID>,
            hasParcels: Boolean,
            canUndo: Boolean,
        ): Set<DeliveriesKey> {
            if (!free) return emptySet()
            val listShown = arrangement == DeliveriesArrangement.SideBySide || navigation.chosen == null
            val keys = linkedSetOf(Add)
            if (listShown && hasParcels) keys.add(Find)
            if (listShown && searching) keys.add(CloseSearch)
            if (typing) return keys
            if (listShown && canUndo) keys.add(Undo)
            if (arrangement == DeliveriesArrangement.SideBySide && cards.isNotEmpty()) keys.addAll(listOf(Previous, Next))
            if (arrangement == DeliveriesArrangement.SideBySide && navigation.shownAutomatically) keys.add(Open)
            return keys
        }
    }
}

/** What a parcel's menu offers: its page's •••, and its card's under a long press or a secondary click. */
enum class ParcelMenuItem {
    Open, Rename, Share, CopyTrackingNumber, ChangeCarrier, Archive, Restore, Delete;

    val titleKey: String
        get() = when (this) {
            Open -> "detail.open"
            Rename -> "detail.editTitle"
            Share -> "share.action"
            CopyTrackingNumber -> "detail.copyTracking"
            ChangeCarrier -> "detail.changeCarrier"
            Archive -> "detail.archive"
            Restore -> "detail.restore"
            Delete -> "detail.delete"
        }

    val symbol: String
        get() = when (this) {
            Open -> "shippingbox"
            Rename -> "pencil"
            Share -> "square.and.arrow.up"
            CopyTrackingNumber -> "doc.on.doc"
            ChangeCarrier -> "truck.box"
            Archive -> "archivebox"
            Restore -> "arrow.uturn.backward"
            Delete -> "trash"
        }

    companion object {
        /**
         * The items in groups that a line sets apart. A card's menu also opens the parcel, and shares it as the
         * page's own button does. Every parcel in the list can be changed, in the demo and without an account as
         * well: an archived one is brought back rather than archived. A `reader` follows someone else's parcel
         * through its link: its page shares the link as it is, its carrier is not the reader's to change, and a
         * number the link hides cannot be copied.
         */
        fun menu(parcel: Parcel, onCard: Boolean, reader: Boolean = false): List<List<ParcelMenuItem>> {
            val changes = if (reader) {
                listOf(Rename) + (if (parcel.trackingNumber.isEmpty()) emptyList() else listOf(CopyTrackingNumber))
            } else {
                listOf(Rename) + (if (onCard) listOf(Share) else emptyList()) + listOf(CopyTrackingNumber, ChangeCarrier)
            }
            return (if (onCard) listOf(listOf(Open)) else emptyList()) +
                listOf(changes, listOf(if (parcel.isArchived) Restore else Archive), listOf(Delete))
        }
    }
}

/** The card a parcel is shown on in the deliveries. */
enum class DeliveryCardKind { Next, Attention, OnTheWay, Past }

/** Everything the Deliveries list shows, derived in one pass per render. */
class DeliveryListLayout(
    parcels: List<Parcel>,
    query: String,
    status: ParcelStatusFilter,
    carrier: CarrierId?,
    sort: ParcelSort,
    featuresNext: Boolean,
    now: Instant = Instant.now(),
    catalog: CarrierCatalog = CarrierCatalog.shared,
) {
    val visible: List<Parcel> = ParcelOrganizer.visible(parcels, query, status, carrier, sort, now, catalog)
    val active: List<Parcel> = visible.filter { it.isActive }
    val next: Parcel? = if (featuresNext) ParcelOrganizer.nextDelivery(active, now) else null
    val attention: List<Parcel>
    val remaining: List<Parcel>
    val sections: List<ParcelSection>

    init {
        val others = active.filter { it.id != next?.id }.map { it to it.attention(now) }
        attention = others.filter { it.second != null }.map { it.first }
        remaining = others.filter { it.second == null }.map { it.first }
        sections = ParcelOrganizer.sections(visible, now).filter {
            it.kind == ParcelSectionKind.Delivered || it.kind == ParcelSectionKind.Returned || it.kind == ParcelSectionKind.Archived
        }
    }

    /** A parcel's card before and after the list changes. */
    data class Change(val was: DeliveryCardKind, val becomes: DeliveryCardKind)

    /** The parcels on cards, in the list's order, the archive left out. */
    val cards: List<UUID>
        get() {
            val past = sections.filter { it.kind != ParcelSectionKind.Archived }.flatMap { it.parcels }
            return (listOfNotNull(next) + attention + remaining + past).map { it.id }
        }

    /**
     * The parcels the list shows, in its order: the cards, then the archive's rows while they show, under a filter or
     * in the opened shelf. Beside the list, the parcel shown by itself is one of these.
     */
    fun rows(archiveOpen: Boolean): List<UUID> {
        val archive = sections.lastOrNull { it.kind == ParcelSectionKind.Archived }
        if (!archiveOpen || archive == null) return cards
        return cards + archive.parcels.map { it.id }
    }

    /** The card this parcel is shown on, outside the archive. */
    fun kind(of: UUID): DeliveryCardKind? {
        if (next?.id == of) return DeliveryCardKind.Next
        if (attention.any { it.id == of }) return DeliveryCardKind.Attention
        if (remaining.any { it.id == of }) return DeliveryCardKind.OnTheWay
        val past = sections.any { section -> section.kind != ParcelSectionKind.Archived && section.parcels.any { it.id == of } }
        return if (past) DeliveryCardKind.Past else null
    }

    /** The parcels whose card is another once the list becomes `into`: those take their new place in front of the reader. */
    fun changing(into: DeliveryListLayout): Map<UUID, Change> {
        val changes = LinkedHashMap<UUID, Change>()
        for (parcel in visible) {
            val was = kind(parcel.id) ?: continue
            val becomes = into.kind(parcel.id) ?: continue
            if (was == becomes) continue
            changes[parcel.id] = Change(was, becomes)
        }
        return changes
    }
}
