package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import java.util.regex.Pattern
import kotlinx.serialization.Serializable

/**
 * A passport records observed journeys. Adding a parcel or creating a label does
 * not start its delivery clock, and a carrier's home country is not its origin.
 *
 * Days and months are the Gregorian calendar's in `timeZone`, as an iPhone's (FoundationDates.kt).
 */
class PassportStatistics(parcels: List<Parcel>, timeZone: ZoneId = ZoneId.systemDefault()) {
    class DeliveryRecord(val parcelId: UUID, val label: String, val duration: Double, val deliveredAt: Instant) {
        val id: UUID get() = parcelId

        // Swift compares the label as it compares Strings, canonically.
        override fun equals(other: Any?): Boolean = other is DeliveryRecord && parcelId == other.parcelId &&
            label.equalsCanonically(other.label) && duration == other.duration && deliveredAt == other.deliveredAt

        override fun hashCode(): Int = parcelId.hashCode()

        override fun toString(): String = "DeliveryRecord(parcelId=$parcelId, label=$label, duration=$duration, deliveredAt=$deliveredAt)"
    }

    class OriginCountry(val code: String, val count: Int) {
        val id: String get() = code
        val flag: String get() = TrackingLocation.flag(code)

        override fun equals(other: Any?): Boolean = other is OriginCountry && code.equalsCanonically(other.code) && count == other.count

        override fun hashCode(): Int = code.canonicalHashCode()

        override fun toString(): String = "OriginCountry(code=$code, count=$count)"
    }

    val trackedCount: Int
    val deliveredCount: Int
    val activeCount: Int
    val crossBorderCount: Int
    val domesticDeliveryCount: Int
    val longWaitDeliveryCount: Int
    val pickupDeliveryCount: Int
    val decemberDeliveryCount: Int
    val maxDeliveriesInOneDay: Int
    val durationSampleCount: Int
    val averageDeliveryDuration: Double?
    val fastestDelivery: DeliveryRecord?

    /**
     * Countries explicitly reported at the first physical scan, across all parcels.
     * This is "first seen in", which does not guarantee the sender's country.
     */
    val originCountries: List<OriginCountry>
    val knownOriginCount: Int
    val unknownOriginCount: Int get() = trackedCount - knownOriginCount

    val nextMilestoneCount: Int
        get() = listOf(1, 5, 10, 25, 50, 100).firstOrNull { it > deliveredCount } ?: ((deliveredCount / 100) + 1) * 100

    val milestoneProgress: Double get() = deliveredCount.toDouble() / nextMilestoneCount.toDouble()

    private class DatedEvent(val event: TrackingEvent, val date: Instant)

    /** A country counted, under the spelling it was first seen in, as a Swift dictionary keeps its first key. */
    private class Tally(val code: String, var count: Int)

    init {
        var delivered = 0
        var active = 0
        val records = mutableListOf<DeliveryRecord>()
        // Keyed as Swift's Dictionary compares Strings, canonically.
        val countries = LinkedHashMap<String, Tally>()
        val deliveredDays = HashMap<Long, Int>()
        var crossBorder = 0
        var domestic = 0
        var longWait = 0
        var pickup = 0
        var december = 0

        for (parcel in parcels) {
            val meaningful = parcel.trackingEvents.filter { it.stage != TrackingStage.Pending }
            val dated = meaningful.mapNotNull { event -> DateParser.date(event.occurredAt)?.let { DatedEvent(event, it) } }
                .sortedWith(earlier)
            val datesAreComplete = dated.size == meaningful.size
            // A malformed date must not turn a return into an earned delivery.
            // Keep the app's status fallback when chronology cannot be established.
            val currentStage = if (datesAreComplete) dated.lastOrNull()?.event?.stage else parcel.currentStage
            if (currentStage == TrackingStage.Delivered) delivered += 1
            if (parcel.archivedAt == null && currentStage != TrackingStage.Delivered && currentStage != TrackingStage.Returned) {
                active += 1
            }

            val physical = dated.filter { it.event.stage != TrackingStage.Registered }
            val completion = physical.firstOrNull { it.event.stage == TrackingStage.Delivered }
            if (datesAreComplete && currentStage == TrackingStage.Delivered && completion != null) {
                // Count each parcel once, using the device's Gregorian calendar day.
                val day = FoundationDates.calendarDate(completion.date, timeZone)
                if (day != null) {
                    deliveredDays[day.day] = (deliveredDays[day.day] ?: 0) + 1
                    if (day.month == 12) december += 1
                }
                if (physical.any { it.event.stage == TrackingStage.ReadyForPickup && it.date < completion.date }) pickup += 1
                val located = physical.filter { it.date <= completion.date }.mapNotNull { scan ->
                    TrackingLocation.countryCode(scan.event.location, scan.event.place?.country)?.let { it to scan.date }
                }
                if (located.indices.any { index ->
                        val scan = located[index]
                        (0 until index).any { located[it].second < scan.second && !located[it].first.equalsCanonically(scan.first) }
                    }
                ) crossBorder += 1
            }
            val first = physical.firstOrNull()
            if (!datesAreComplete || first == null ||
                !(first.event.stage == TrackingStage.Accepted || first.event.stage == TrackingStage.InTransit)
            ) continue

            // Never skip an unknown first scan and promote a destination scan into
            // an origin. City names and tracking-number suffixes are insufficient.
            val origin = TrackingLocation.countryCode(first.event.location, first.event.place?.country)
            if (origin != null) countries.getOrPut(origin.nfc()) { Tally(origin, 0) }.count += 1

            if (currentStage != TrackingStage.Delivered || completion == null) continue
            // `timeIntervalSince`, as Swift subtracts the seconds each Date holds.
            val duration = completion.date.timeIntervalSinceReferenceDate - first.date.timeIntervalSinceReferenceDate
            if (!duration.isFinite() || duration <= 0) continue
            if (origin != null &&
                origin.equalsCanonically(TrackingLocation.countryCode(completion.event.location, completion.event.place?.country))
            ) domestic += 1
            if (duration > 30 * 86_400) longWait += 1
            records += DeliveryRecord(parcelId = parcel.id, label = parcel.label, duration = duration, deliveredAt = completion.date)
        }

        crossBorderCount = crossBorder
        domesticDeliveryCount = domestic
        longWaitDeliveryCount = longWait
        pickupDeliveryCount = pickup
        decemberDeliveryCount = december
        maxDeliveriesInOneDay = deliveredDays.values.maxOrNull() ?: 0
        trackedCount = parcels.size
        deliveredCount = delivered
        activeCount = active
        durationSampleCount = records.size
        averageDeliveryDuration = if (records.isEmpty()) null else records.fold(0.0) { sum, record -> sum + record.duration } / records.size
        fastestDelivery = records.minWithOrNull { left, right ->
            if (left.duration != right.duration) left.duration.compareTo(right.duration)
            else left.parcelId.uuidString.compareTo(right.parcelId.uuidString)
        }
        originCountries = countries.values.map { OriginCountry(code = it.code, count = it.count) }
            .sortedWith { left, right ->
                if (left.count != right.count) right.count.compareTo(left.count) else swiftStringOrder.compare(left.code, right.code)
            }
        knownOriginCount = countries.values.sumOf { it.count }
    }

    private companion object {
        // Carrier timestamps sometimes have minute precision. Put later stages
        // after earlier stages at the same instant, with return taking precedence.
        private val stages = listOf(
            TrackingStage.Registered, TrackingStage.Accepted, TrackingStage.InTransit, TrackingStage.Customs,
            TrackingStage.Exception, TrackingStage.OutForDelivery, TrackingStage.FailedAttempt,
            TrackingStage.ReadyForPickup, TrackingStage.Delivered, TrackingStage.Returned,
        )

        private val earlier = Comparator<DatedEvent> { left, right ->
            if (left.date != right.date) return@Comparator left.date.compareTo(right.date)
            val leftRank = stages.indexOf(left.event.stage).coerceAtLeast(0)
            val rightRank = stages.indexOf(right.event.stage).coerceAtLeast(0)
            if (leftRank != rightRank) return@Comparator leftRank.compareTo(rightRank)
            left.event.id.uuidString.compareTo(right.event.id.uuidString)
        }
    }
}

/**
 * Country fields shared by parcel display and first-scan statistics. The regions and their names are Foundation's
 * (CountryNames.json, which android/tools/CountryNames.swift writes), never the phone's, and Strings compare as Swift
 * compares them, canonically.
 */
object TrackingLocation {
    /** Foundation's regions and their names in each language, which android/tools/CountryNames.swift writes. */
    @Serializable
    private class RegionNames(val regions: List<String>, val names: Map<String, Map<String, String>>)

    private val regionNames: RegionNames by lazy {
        ApiJson.decodeFromString(RegionNames.serializer(), SharedResources.text("CountryNames.json"))
    }

    /** `Locale.Region.isoRegions`' codes. */
    val regions: List<String> get() = regionNames.regions

    private val regionCodes: Set<String> by lazy {
        regionNames.regions.filter { it.characterCount == 2 && it !in setOf("EU", "UN", "QO") }.map { it.nfc() }.toSet()
    }

    // A two-letter final address field can instead be a US state, Swiss canton,
    // Canadian province/territory, or Australian state. These require a full
    // country name, or a location containing only the country code.
    private val ambiguousAddressCodes: Set<String> = setOf(
        "AL", "AZ", "AR", "CA", "CO", "DE", "GA", "ID", "IL", "IN", "KY",
        "LA", "ME", "MD", "MA", "MN", "MS", "MO", "MT", "NE", "NC", "PA",
        "SC", "SD", "TN", "VA", "AG", "AI", "BE", "BL", "BS", "FR", "GE",
        "GL", "GR", "LU", "SG", "SH", "SO", "SZ", "TG", "NL", "NU", "PE",
        "SK", "YT", "SA",
    )

    // Carrier spellings no region name matches. Display only: Passport evidence
    // stays in step with the web's trackingLocationCountry, which Friends stamps use.
    private val carrierSpellings: Map<String, String> = mapOf(
        "united states of america" to "US", "great britain" to "GB", "czech republic" to "CZ", "holland" to "NL",
        "hong kong" to "HK", "macau" to "MO", "macao" to "MO", "turkey" to "TR", "russian federation" to "RU",
        "korea" to "KR", "republic of korea" to "KR",
    )

    // "Mexico City" is a city, not Mexico followed by one.
    private val settlementWords: Set<String> = setOf("city", "town", "ville", "stadt", "ciudad", "cidade", "citta")

    private val fieldPattern: Pattern = Pattern.compile("[^,;|()]+")

    // Apple calls CN "China mainland" in every language, never what carriers or the web write.
    private val chinaNames = mapOf(
        AppLanguage.En to "China", AppLanguage.De to "China", AppLanguage.Fr to "Chine", AppLanguage.It to "Cina",
        AppLanguage.Es to "China", AppLanguage.Pt to "China", AppLanguage.PtBr to "China", AppLanguage.Pl to "Chiny",
        AppLanguage.Ru to "Китай", AppLanguage.Zh to "中国",
    )

    /** Each region's names in every language, as its `locale` gives them, keyed by their NFC folded form. */
    private val countryNames: Map<String, String> by lazy {
        val names = HashMap<String, String>()
        for (code in regionCodes.sorted()) {
            for (language in AppLanguage.entries) {
                regionNames.names[language.rawValue]?.get(code)?.let { names[normalized(it).nfc()] = code }
            }
        }
        names["usa"] = "US"
        names["uk"] = "GB"
        for (name in chinaNames.values) names[normalized(name).nfc()] = "CN"
        names
    }

    /** A location split into the country its carrier wrote and the rest. */
    class Place(val country: String?, /** Empty when the country was all of the location. */ val name: String) {
        override fun equals(other: Any?): Boolean =
            other is Place && country.equalsCanonically(other.country) && name.equalsCanonically(other.name)

        override fun hashCode(): Int = 31 * (country?.canonicalHashCode() ?: 0) + name.canonicalHashCode()

        override fun toString(): String = "Place(country=$country, name=$name)"
    }

    /** Builds the country-name table (about 2,500 localized names) ahead of first use. */
    fun prepare() {
        countryNames
    }

    private fun normalized(value: String): String = value.trimmingWhitespacesAndNewlines().foldingCaseAndDiacritics()

    private fun namedCountry(name: String, display: Boolean): String? {
        val key = normalized(name)
        countryNames[key.nfc()]?.let { return it }
        if (!display) return null
        val bare = if (key.hasCanonicalPrefix("the ")) key.characters().drop(4).joinToString("") else key
        return countryNames[bare.nfc()] ?: carrierSpellings[bare.nfc()]
    }

    private fun fieldCountry(field: String, location: String, display: Boolean): String? {
        if (field.characterCount == 2 && field.equalsCanonically(field.uppercased()) && field.nfc() in regionCodes) {
            return if (location.equalsCanonically(field) || field.nfc() !in ambiguousAddressCodes) field else null
        }
        return namedCountry(field, display)
    }

    /** Passport evidence: only an explicit final country field; never a country inferred from a city. */
    fun countryCode(location: String?): String? {
        location ?: return null
        // Require a whole country field: "Milano, IT", "Paris, France", or
        // "Berlin (Germany)". A bare city or an undelimited "Buchs AG" is unknown.
        val field = location.split(',', ';', '|', '(', ')')
            .map { it.trimmingWhitespacesAndNewlines() }
            .lastOrNull { it.isNotEmpty() } ?: return null
        return fieldCountry(field, location.trimmingWhitespacesAndNewlines(), display = false)
    }

    /**
     * For display, takes an explicit country off a location: the final field ("Zürich, CH",
     * repeated in "Hebron, KY, US, US") or a leading name ("Switzerland Haerkingen").
     */
    fun place(location: String): Place {
        val text = location.trimmingWhitespacesAndNewlines()
        val fields = mutableListOf<IntRange>()
        val matcher = fieldPattern.matcher(text)
        while (matcher.find()) {
            if (text.substring(matcher.start(), matcher.end()).trimmingWhitespacesAndNewlines().isNotEmpty()) {
                fields += matcher.start() until matcher.end()
            }
        }
        var country: String? = null
        var kept = fields.size
        while (kept > 0) {
            val field = text.substring(fields[kept - 1].first, fields[kept - 1].last + 1).trimmingWhitespacesAndNewlines()
            val code = fieldCountry(field, text, display = true) ?: break
            if (country != null && !code.equalsCanonically(country)) break
            country = code
            kept -= 1
        }
        if (country != null) {
            val rest = if (kept > 0) text.substring(0, fields[kept].first) else ""
            return Place(country = country, name = CatalogExpressions.foundationReplacing(rest, "[\\s,;|(]+$", ""))
        }
        if (fields.size == 1 && fields[0].first == 0 && fields[0].last + 1 == text.length) {
            val words = text.splitOnWhitespace()
            for (count in minOf(words.size - 1, 4) downTo 1) {
                val name = words.subList(count, words.size).joinToString(" ")
                val code = namedCountry(words.subList(0, count).joinToString(" "), display = true)
                if (code != null && name.characters().firstOrNull()?.isUppercaseCharacter == true &&
                    normalized(name).nfc() !in settlementWords
                ) {
                    return Place(country = code, name = name)
                }
            }
        }
        return Place(country = null, name = text)
    }

    // ICU's `.` stops at every line end, java.util.regex's lets U+000B and U+000C through.
    private val finalCode: Pattern = Pattern.compile(
        "^([^\\n\\x{0B}\\f\\r\\x{85}\\x{2028}\\x{2029}]*\\S)\\s*[,;|(]\\s*([A-Z]{2})\\)?$",
        unicodeCharacterClass,
    )

    /**
     * The final code of a location, with what comes before it, when it is the country the scan
     * was placed in. A code that is also a state, province or canton ("Haiger, DE", "Wilmington,
     * DE", "Toronto, CA", "Los Angeles, CA") is not read as a country on its own.
     */
    private fun placedCode(location: String, placed: String?): Place? {
        val text = location.trimmingWhitespacesAndNewlines()
        placed ?: return null
        val match = finalCode.matcher(text)
        if (!match.find() || !text.substring(match.start(2), match.end(2)).equalsCanonically(placed)) return null
        return Place(country = placed, name = text.substring(match.start(1), match.end(1)))
    }

    /**
     * For display: a final code that is also a state or canton names a country only when the
     * scan was placed in that country.
     */
    fun place(location: String, placedIn: String?): Place {
        val written = place(location)
        return if (written.country == null) placedCode(location, placedIn) ?: written else written
    }

    /**
     * Passport evidence, with a final code that is also a state or canton counted once the scan
     * was placed in that country.
     */
    fun countryCode(location: String?, placedIn: String?): String? {
        location ?: return null
        return countryCode(location) ?: placedCode(location, placedIn)?.country
    }

    /** `locale.localizedString(forRegionCode:)`, which reads the code in either case. */
    fun countryName(code: String, language: AppLanguage): String {
        if (code == "CN") chinaNames[language]?.let { return it }
        val key = if (code.all { it in 'a'..'z' || it in 'A'..'Z' || it in '0'..'9' }) code.uppercase() else code
        return regionNames.names[language.rawValue]?.get(key) ?: code
    }

    fun flag(code: String): String {
        val flag = StringBuilder()
        var index = 0
        while (index < code.length) {
            val scalar = 127_397 + code.scalarAt(index)
            if (scalar <= 0x10FFFF) flag.appendCodePoint(scalar)
            index += Character.charCount(code.codePointAt(index))
        }
        return flag.toString()
    }
}
