package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
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
                else -> shortDate(date, withYear = FoundationDates.dateFields(date, zone).yearOfEra != year)
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

/**
 * What the change-carrier sheet (`ChangeCarrierView`) holds and works out for [parcel]: the carrier chosen, null while
 * automatic detection is, the fields typed, and the delivery country chosen beside the postcode until another carrier
 * is chosen. The view's own state is the app's; this is its arithmetic, with [region] the phone's country
 * (`CarrierCatalog.deviceCountry()`).
 */
data class CarrierChange(
    val parcel: Parcel,
    val chosenCarrier: CarrierId?,
    val trackingUrl: String,
    val deliveryPostcode: String,
    val chosenPostcodeCountry: String? = null,
    val providerPostcode: String = "",
    val catalog: CarrierCatalog = CarrierCatalog.shared,
    val region: String? = CarrierCatalog.deviceCountry(),
) {
    /** Filed under none: no carrier, or the international post. */
    val unfiled: Boolean get() = parcel.carrier == CarrierId.Unknown || parcel.carrier == CarrierId.InternationalPost

    /**
     * Automatic detection files the parcel as adding it would: under the carrier its number's shape names, else under
     * none, and Peek looks for it. A parcel filed under none already is. An Amazon number is tracked through Amazon
     * Shipping alone: none.
     */
    val automaticCarrier: CarrierId?
        get() {
            if (catalog.isAmazonTrackingNumber(parcel.trackingNumber)) return null
            if (unfiled) return parcel.carrier
            val match = catalog.detect(parcel.trackingNumber)
            return if (match.confidence == CarrierMatch.Confidence.High) match.carrier else CarrierId.Unknown
        }

    val automatic: Boolean get() = chosenCarrier == null

    /** The carrier the change files the parcel under. */
    val selectedCarrier: CarrierId get() = chosenCarrier ?: automaticCarrier ?: parcel.carrier

    /** The carrier's row: "add.detect" while automatic, else the carrier's name. */
    fun carrierName(localizer: Localizer): String =
        if (automatic) localizer.text("add.detect") else catalog.info(selectedCarrier, localizer.language).displayName

    /** What automatic detection does: names the carrier the number names, or says Peek finds one. */
    fun automaticDescription(localizer: Localizer): String {
        val carrier = automaticCarrier
        if (carrier == null || carrier == CarrierId.Unknown || carrier == CarrierId.InternationalPost) {
            return localizer.text("picker.auto.change")
        }
        return localizer.text("picker.auto.detected", mapOf("carrier" to catalog.info(carrier, localizer.language).displayName))
    }

    /** The line under the carrier's row: the automatic description, or how the carrier tracks. */
    fun hint(localizer: Localizer): String =
        if (automatic) automaticDescription(localizer)
        else localizer.text(catalog.trackingHintKey(selectedCarrier), mapOf("carrier" to catalog.info(selectedCarrier, localizer.language).displayName))

    /** The carrier's fields, the postcode's shaped for the country beside it. */
    val requirements: List<CarrierRequirement>
        get() {
            val country = postcodeCountry
            return catalog.requirements(selectedCarrier, parcel.trackingNumber).map {
                catalog.postcodeRequirement(it, selectedCarrier, country)
            }
        }

    /** The pair the refusal check asks the server about: a carrier chosen by hand and the number, outside the demo. */
    fun refusalQuestion(demo: Boolean): NumberRefusalRequest? =
        NumberRefusals.question(if (automatic) null else selectedCarrier, CarrierCatalog.normalize(parcel.trackingNumber), demo)

    /** A section the picker opens with: its id, its title's key and its carriers. */
    data class PickerSection(val id: String, val titleKey: String, val carriers: List<CarrierId>)

    /**
     * After automatic detection, the parcel's own carrier unless it is filed automatically or among the carriers its
     * number fits, which come next, then the ones used before (of [parcels]). So the carrier it has is in view without
     * scrolling, and one no longer offered is there at all.
     */
    fun pickerSections(parcels: List<Parcel>): List<PickerSection> {
        val match = catalog.detect(parcel.trackingNumber)
        val high = match.confidence == CarrierMatch.Confidence.High
        val fitting = (if (high) listOf(match.carrier) else match.candidates).filter { catalog.info(it).selectable }
        val own = if ((automaticCarrier != null && unfiled) || parcel.carrier in fitting) emptyList() else listOf(parcel.carrier)
        return listOf(
            PickerSection("current", "picker.section.current", own),
            PickerSection("fits", if (high) "picker.section.detected" else "picker.section.fits", fitting),
            PickerSection(
                "used", "picker.section.used",
                CarrierPickerSearch.usedCarriers(parcels, catalog).filter { it !in fitting && it !in own },
            ),
        )
    }

    val trackingUrlRequirement: CarrierRequirement?
        get() = requirements.firstOrNull { it.field == CarrierRequirement.Field.TrackingUrl }

    /** The carrier's postcode field, before the country beside it shapes it. */
    val carrierPostcodeRequirement: CarrierRequirement?
        get() = catalog.requirements(selectedCarrier, parcel.trackingNumber).firstOrNull { it.field == CarrierRequirement.Field.DpdPostcode }

    /** Where the postcode's country starts: the one saved with the parcel's postcode, or the field's own start. */
    val initialPostcodeCountry: String?
        get() = carrierPostcodeRequirement?.let { catalog.initialPostcodeCountry(selectedCarrier, it, parcel, region) }

    /** The country the postcode is from: the one chosen while this field takes it, otherwise where it starts. */
    val postcodeCountry: String?
        get() {
            val requirement = carrierPostcodeRequirement ?: return null
            val chosen = chosenPostcodeCountry
            if (chosen != null && chosen in catalog.postcodeCountries(selectedCarrier, requirement)) return chosen
            return initialPostcodeCountry
        }

    /** The postcode's country, offered beside the field when it takes more than one. */
    val offeredPostcodeCountry: String?
        get() {
            val requirement = carrierPostcodeRequirement ?: return null
            if (catalog.postcodeCountries(selectedCarrier, requirement).size <= 1) return null
            return postcodeCountry
        }

    val postcodeRequirement: CarrierRequirement?
        get() = requirements.firstOrNull { it.field == CarrierRequirement.Field.DpdPostcode }

    /** The keyboard a postcode field opens with (`keyboardType`). */
    enum class PostcodeKeys { NumberPad, NumbersAndPunctuation, AsciiCapable }

    /**
     * The number pad for a numeric field, the number keys first where the example of the country it reads (or the
     * phone's) is all digits, else the letters.
     */
    fun postcodeKeys(requirement: CarrierRequirement): PostcodeKeys {
        if (requirement.inputMode == "numeric") return PostcodeKeys.NumberPad
        val example = catalog.postcodeExample(selectedCarrier, requirement, if (requirement.readsCountry == true) postcodeCountry else region)
        return if (requirement.startsWithNumberKeys(example)) PostcodeKeys.NumbersAndPunctuation else PostcodeKeys.AsciiCapable
    }

    /** The lookup's provider asks for the delivery postcode, while the parcel stays with its carrier. */
    val needsProviderPostcode: Boolean
        get() = selectedCarrier == parcel.carrier && parcel.carrierData?.routing?.providerInputNeeded != null

    /** The provider that asks for it ([needsProviderPostcode]). */
    val inputProvider: String? get() = parcel.carrierData?.routing?.providerInputNeeded?.provider

    /** The provider's postcode as sent: trimmed, in capitals, and none unless it has the shape of a postcode. */
    val normalizedProviderPostcode: String?
        get() {
            if (!needsProviderPostcode) return null
            val value = providerPostcode.trimmingWhitespacesAndNewlines().uppercase()
            return if (CatalogExpressions.foundationMatches(value, PROVIDER_POSTCODE)) value else null
        }

    val normalizedTrackingUrl: String get() = trackingUrlRequirement?.normalizedValue(trackingUrl) ?: ""

    val normalizedPostcode: String get() = postcodeRequirement?.normalizedValue(deliveryPostcode) ?: ""

    /** Something differs from the parcel: its carrier, a field, the postcode's country, or a provider's postcode given. */
    val changed: Boolean
        get() = normalizedProviderPostcode != null || selectedCarrier != parcel.carrier ||
            normalizedTrackingUrl != (parcel.trackingUrl ?: "") ||
            normalizedPostcode != (parcel.dpdPostcode ?: "") ||
            (normalizedPostcode.isNotEmpty() && postcodeCountry != initialPostcodeCountry)

    /** A change with every field it needs, the tracking link a secure web address. */
    val canSave: Boolean
        get() {
            if (needsProviderPostcode && normalizedProviderPostcode == null) return false
            if (!changed) return false
            for (requirement in requirements) {
                when (requirement.field) {
                    CarrierRequirement.Field.TrackingUrl -> {
                        val value = requirement.normalizedValue(trackingUrl)
                        val url = Url.parse(value)
                        if (!requirement.accepts(value) || url == null || url.scheme != "https" || url.host == null) return false
                    }
                    CarrierRequirement.Field.DpdPostcode -> if (!requirement.isSatisfied(deliveryPostcode)) return false
                }
            }
            return true
        }

    /** The link and the postcode as `save()` sends them: none when empty. */
    val sentTrackingUrl: String? get() = normalizedTrackingUrl.nonEmpty
    val sentPostcode: String? get() = normalizedPostcode.nonEmpty

    /** The country sent with the postcode: none without one, nor for the carrier's home country. */
    val sentPostcodeCountry: String? get() = catalog.sentPostcodeCountry(postcodeCountry, normalizedPostcode, selectedCarrier)

    /**
     * The change with `carrier` chosen (null for automatic detection). Another carrier to file under starts its fields
     * from the parcel's own on its own carrier, else empty, and the country from the field's start
     * (`onChange(of: selectedCarrier)`).
     */
    fun choosing(carrier: CarrierId?): CarrierChange {
        val next = copy(chosenCarrier = carrier)
        if (next.selectedCarrier == selectedCarrier) return next
        val own = next.selectedCarrier == parcel.carrier
        return next.copy(
            trackingUrl = if (own) parcel.trackingUrl ?: "" else "",
            deliveryPostcode = if (own) parcel.dpdPostcode ?: "" else "",
            chosenPostcodeCountry = null,
        )
    }

    companion object {
        /**
         * The sheet as it opens on [parcel] (`init(parcel:initialCarrier:)`), on [initialCarrier] where a prompt names
         * one: an unfiled parcel on its own carrier starts on automatic detection, but for an Amazon number; the link
         * and the postcode start from the parcel's on its own carrier.
         */
        fun start(
            parcel: Parcel,
            initialCarrier: CarrierId? = null,
            catalog: CarrierCatalog = CarrierCatalog.shared,
            region: String? = CarrierCatalog.deviceCountry(),
        ): CarrierChange {
            val carrier = initialCarrier ?: parcel.carrier
            val unfiled = parcel.carrier == CarrierId.Unknown || parcel.carrier == CarrierId.InternationalPost
            val own = carrier == parcel.carrier
            return CarrierChange(
                parcel = parcel,
                chosenCarrier = if (unfiled && own && !catalog.isAmazonTrackingNumber(parcel.trackingNumber)) null else carrier,
                trackingUrl = if (own) parcel.trackingUrl ?: "" else "",
                deliveryPostcode = if (own) parcel.dpdPostcode ?: "" else "",
                catalog = catalog,
                region = region,
            )
        }

        /** A postcode as a provider takes it, in capitals. */
        private const val PROVIDER_POSTCODE = "^(?=.{3,12}$)(?=.*[0-9])[A-Z0-9]+(?:[ -][A-Z0-9]+)*$"
    }
}
