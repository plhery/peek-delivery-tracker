package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.time.ZoneId

// From ParcelDetailView.swift, what isn't the view. Copying to the clipboard is the app's.

/** Copies a tracking number as its carrier's site takes it. */
object TrackingNumberCopy {
    fun text(number: String, carrier: CarrierId): String =
        if (carrier == CarrierId.Postlogistics) CarrierCatalog.format(number, carrier) else number
}

/**
 * The name `parcelRenameAlert` saves: the one typed, trimmed of whitespace and newlines, cut to its first 80
 * characters as a reader sees them (Swift's `prefix(80)`).
 */
fun parcelName(typed: String): String = typed.trimmingWhitespacesAndNewlines().characterPrefix(80)

/**
 * Where the parcel waits for collection (`waitingAt`): its pickup point while it is ready for pickup, which the page
 * gives a card; afterwards the point is a plain fact ([shipmentFacts]).
 */
val Parcel.waitingAt: PickupPoint?
    get() = if (currentStage == TrackingStage.ReadyForPickup) PickupPoint(carrierData?.pickupPoint) else null

/** The scan's words as the page quotes them, after "What {carrier} says": trimmed, and none when nothing is left. */
val TrackingEvent.quotedWords: String?
    get() = description.trimmingWhitespacesAndNewlines().nonEmpty

/** A fact of the shipment the parcel's page lists (`shipmentFact`): the key of its label, and its value. */
data class ShipmentFact(val key: String, val value: String)

/**
 * The facts of the shipment the parcel's page lists under its numbers, in `liveParcelPass`'s order: what importing it
 * costs (`importCharges`); its pickup point, or where it was picked up once delivered, unless a card shows it
 * ([waitingAt]); who receives it; its weight in the app language's digits with up to three decimals, then " kg"; and
 * its dimensions, each trimmed and left out when empty. Null without carrier data, where the page lists nothing.
 */
fun Localizer.shipmentFacts(parcel: Parcel): List<ShipmentFact>? {
    val details = parcel.carrierData ?: return null
    fun trimmed(value: String?) = value?.trimmingWhitespacesAndNewlines()?.nonEmpty
    val facts = mutableListOf<ShipmentFact>()
    importCharges(parcel)?.let { facts += ShipmentFact("detail.importCharges", it.text) }
    val pickup = trimmed(details.pickupPoint)
    if (parcel.waitingAt == null && pickup != null) {
        facts += ShipmentFact(if (parcel.currentStage == TrackingStage.Delivered) "detail.collectedAt" else "detail.pickupPoint", pickup)
    }
    trimmed(details.receiverName)?.let { facts += ShipmentFact("detail.recipient", it) }
    val weight = details.weightKg
    if (weight != null && weight.isFinite() && weight > 0) {
        facts += ShipmentFact("detail.weight", foundationDecimal(weight, 3, language) + " kg")
    }
    trimmed(details.dimensionsText)?.let { facts += ShipmentFact("detail.dimensions", it) }
    return facts
}

/** A day of the parcel's journal (`JournalDay`): what heads it, when it starts, and its rows. */
data class JournalDay(
    val id: String,
    val label: String,
    /** The start of the day; null for scans without a readable date, which stay last. */
    val start: Instant?,
    val entries: List<JournalEntry>,
)

/**
 * The journal's rows by day (`journalDays`), in the phone's zone: each day headed "today", "yesterday" or its short
 * date, with its year when that isn't `now`'s. A scan dated by its day alone sits under that day, wherever the reader
 * is; scans without a readable date group under their raw `occurredAt` and stay last. Days newest first, and days that
 * start together in the rows' order: a scan dated by its day alone can sort among the day before's scans, west of the
 * carrier's zone.
 */
fun Localizer.journalDays(entries: List<JournalEntry>, now: Instant = Instant.now()): List<JournalDay> {
    val zone = ZoneId.systemDefault()
    val today = now.atZone(zone).toLocalDate()
    val year = FoundationDates.dateFields(now, zone).yearOfEra
    val days = mutableListOf<JournalDay>()
    for (entry in entries) {
        val event = entry.event
        val date = event.displayDate
        val start = date?.atZone(zone)?.toLocalDate()?.atStartOfDay(zone)?.toInstant()
        val key = start?.let { it.epochSecond.toDouble().swiftDescription() } ?: event.occurredAt
        val index = days.indexOfFirst { it.id.equalsCanonically(key) }
        if (index >= 0) {
            days[index] = days[index].copy(entries = days[index].entries + entry)
            continue
        }
        val label = if (date == null) {
            event.occurredAt
        } else {
            when (date.atZone(zone).toLocalDate()) {
                today -> text("time.today")
                today.minusDays(1) -> text("time.yesterday")
                else -> {
                    val dayYear = FoundationDates.dateFields(date, zone).yearOfEra
                    shortDate(date) + if (dayYear == year) "" else " $dayYear"
                }
            }
        }
        days += JournalDay(key, label, start, listOf(entry))
    }
    // A stable sort: days that start together keep the rows' order.
    return days.sortedByDescending { it.start ?: distantPast }
}

/**
 * Foundation's `Date.distantPast`, where the journal's days without a date sort: the first day of year 1 in the Julian
 * calendar, two days before the proleptic Gregorian one.
 */
private val distantPast: Instant = Instant.ofEpochSecond(-62_135_769_600)

/**
 * The clock time of a journal row (`JournalEventRow.time`): none for a scan the carrier dated by its day alone, whose
 * day heads the group, and "—" for a date that doesn't read.
 */
fun Localizer.journalTime(event: TrackingEvent): String {
    if (event.calendarDay != null) return ""
    val date = DateParser.date(event.occurredAt) ?: return "—"
    return clockTime(date)
}

/** A scan's words in the journal (`wording`): the carrier's, else its stage's, in the app language. */
fun Localizer.journalWording(event: TrackingEvent): String =
    eventDescription(event.description.nonEmpty ?: text(event.stage.localizationKey))

/** The words of a row's copies (`relayed`), once each, and only those that differ from the scan's. */
fun Localizer.relayedWordings(entry: JournalEntry): List<String> {
    // A Swift Set of strings: canonically equivalent words are the same words.
    val seen = hashSetOf(journalWording(entry.event).nfc())
    return entry.relays.map { journalWording(it) }.filter { seen.add(it.nfc()) }
}

/** Where the journal says a scan was: its location trimmed, and none when nothing is left. */
val TrackingEvent.journalLocation: String?
    get() = location?.trimmingWhitespacesAndNewlines()?.nonEmpty
