package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.Parcel
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive

/** A stamp's ink: each has its deep and pale text colour and its paper, in light and dark. */
enum class StampTone(val rawValue: String) {
    Green("green"), Lilac("lilac"), Blue("blue"), Peach("peach"), Ochre("ochre"), Rose("rose");

    companion object {
        fun fromRawValue(rawValue: String): StampTone = entries.firstOrNull { it.rawValue == rawValue } ?: Blue
    }
}

/** A country stamp's outline, by its continent. */
enum class Continent(val rawValue: String) {
    Europe("europe"), Asia("asia"), NorthAmerica("northAmerica"), SouthAmerica("southAmerica"), Africa("africa"), Oceania("oceania");

    companion object {
        fun fromRawValue(rawValue: String): Continent = entries.firstOrNull { it.rawValue == rawValue } ?: Europe
    }
}

/** A stamp as Passport.json defines it: its key, ink, glyph or numeral, and the count that earns it. */
class StampDefinition(val key: String, val tone: StampTone, val glyph: String?, val numeral: String?, val total: Int)

/** A country stamp's words in its 124×92 box: baselines, sizes, and the inner line's scale. */
@Serializable
class InkLayout(val number: Double, val code: Double, val codeSize: Double, val date: Double, val small: Double, val inner: Double, val dateSpacing: Double)

/**
 * The passport as the web, the iPhone and Android draw and count it (shared/passport.json): the stamps in page order,
 * their glyphs (24×24 line drawings), each country's continent, ink and tilt, fixed for good, and the continent outlines
 * in a 124×92 box. Paths use absolute M, L, C and Z only.
 */
object PassportData {
    @Serializable
    private class Stamp(val key: String, val tone: String, val total: Int, val glyph: String? = null, val numeral: String? = null)

    @Serializable
    private class File(
        val countries: Map<String, JsonArray>,
        val outlines: Map<String, String>,
        val layout: Map<String, InkLayout>,
        val glyphs: Map<String, String>,
        val stamps: List<Stamp>,
    )

    /** A country's continent, ink and tilt in degrees. */
    class CountryInk(val continent: Continent, val tone: StampTone, val tilt: Int)

    private val file: File by lazy { ApiJson.decodeFromString(File.serializer(), SharedResources.text("Passport.json")) }

    /** Every stamp in page order: the reachable ones first, so a new passport's next three are within reach. */
    val stamps: List<StampDefinition> by lazy {
        file.stamps.map { StampDefinition(it.key, StampTone.fromRawValue(it.tone), it.glyph, it.numeral, it.total) }
    }

    val glyphs: Map<String, String> get() = file.glyphs

    private val countries: Map<String, CountryInk> by lazy {
        file.countries.mapValues { (_, value) ->
            CountryInk(Continent.fromRawValue(value[0].jsonPrimitive.content), StampTone.fromRawValue(value[1].jsonPrimitive.content), value[2].jsonPrimitive.int)
        }
    }

    /** A country's continent, which gives its stamp's outline, and its ink and tilt: fixed per code, so a stamp never changes colour or jumps. */
    fun countryInk(code: String): CountryInk = countries[code] ?: CountryInk(Continent.Europe, StampTone.Blue, -3)

    fun continentOf(code: String): Continent = countryInk(code).continent

    fun outline(continent: Continent): String = file.outlines.getValue(continent.rawValue)

    fun layout(continent: Continent): InkLayout = file.layout.getValue(continent.rawValue)

    /** A stamp's glyph path in its 24×24 box, the parcel's where it names none. */
    fun glyph(name: String?): String = file.glyphs[name ?: "parcel"] ?: file.glyphs.getValue("parcel")
}

/** A stamp as the passport shows it: how far the reader is, and once earned, the day and the parcel that earned it. */
class StampProgress(
    val definition: StampDefinition,
    val count: Int,
    /** When the delivery that crossed its rule arrived; null for one not earned. */
    val earnedAt: Instant?,
    val parcelId: UUID?,
    val label: String?,
) {
    val key: String get() = definition.key
    val total: Int get() = definition.total
    val earned: Boolean get() = count >= definition.total

    /** The key the device marks it seen by. */
    val seenKey: String get() = "stamp:$key"
}

/** A country's ink stamp: the first instant a delivered parcel of the reader's was scanned there, and how many went through it. */
class CountryStamp(
    val code: String,
    /** Its running number in the order the countries were reached, from 1. */
    val number: Int,
    val firstAt: Instant,
    val parcelId: UUID,
    val label: String,
    val count: Int,
    val continent: Continent,
) {
    val seenKey: String get() = "country:$code"
}

/**
 * The whole passport (the web's `passportBook`): today's numbers, every stamp with its progress and the day it was
 * earned, and the countries. A stamp's day is the delivery that crossed its rule, replaying the deliveries in the order
 * they arrived, so deleting a parcel can move or remove it. Calendar stamps use [timeZone].
 */
class PassportBook(parcels: List<Parcel>, timeZone: ZoneId = ZoneId.systemDefault()) {
    val stats = PassportStatistics(parcels, timeZone)
    val stamps: List<StampProgress>
    val countries: List<CountryStamp>

    private class Reached(var firstAt: Instant, var delivery: PassportStatistics.Delivery, var count: Int)

    init {
        val deliveries = stats.deliveries
        val earned = HashMap<String, PassportStatistics.Delivery>()
        fun earn(key: String, delivery: PassportStatistics.Delivery, rule: Boolean = true) {
            if (rule && key !in earned) earned[key] = delivery
        }
        val carriers = HashSet<String>()
        val reached = LinkedHashMap<String, Reached>()
        val days = HashMap<String, Int>()
        val byCarrier = HashMap<String, Int>()
        val seasons = HashSet<Int>()
        val firstDelivery = deliveries.firstOrNull()?.deliveredAt
        deliveries.forEachIndexed { index, delivery ->
            val n = index + 1
            earn("first", delivery)
            earn("ten", delivery, n >= 10)
            earn("theRegular", delivery, n >= 25)
            earn("fifty", delivery, n >= 50)
            val carrier = delivery.carrier.rawValue
            if (carrier !in PLACEHOLDER_CARRIERS) {
                carriers += carrier
                byCarrier[carrier] = (byCarrier[carrier] ?: 0) + 1
            }
            earn("connected", delivery, carriers.size >= 3)
            earn("familiarFace", delivery, (byCarrier[carrier] ?: 0) >= 3)
            earn("secondTry", delivery, delivery.secondTry)
            seasons += delivery.season
            earn("fourSeasons", delivery, seasons.size >= 4)
            earn("firstAnniversary", delivery, firstDelivery != null && millisBetween(firstDelivery, delivery.deliveredAt) >= 365 * DAY_MILLIS)
            earn("express", delivery, delivery.duration?.let { it <= 2 * DAY_SECONDS } == true)
            earn("acrossBorders", delivery, delivery.crossBorder)
            earn("newHorizons", delivery, delivery.crossContinent)
            for ((code, at) in delivery.countries) {
                val known = reached[code]
                if (known == null) {
                    reached[code] = Reached(at, delivery, 1)
                } else {
                    known.count += 1
                    if (at < known.firstAt) {
                        known.firstAt = at
                        known.delivery = delivery
                    }
                }
            }
            earn("aroundWorld", delivery, reached.size >= 5)
            earn("tenCountries", delivery, reached.size >= 10)
            earn("rightNextDoor", delivery, delivery.domestic)
            earn("worthTheWait", delivery, delivery.longWait)
            days[delivery.day] = (days[delivery.day] ?: 0) + 1
            earn("busyDoorstep", delivery, (days[delivery.day] ?: 0) >= 3)
            earn("pickedUp", delivery, delivery.pickup)
            earn("homeForHolidays", delivery, delivery.december)
        }
        val fastest = stats.fastestDelivery
        // The months between the first delivery and the latest, until a year has passed.
        val months = when {
            "firstAnniversary" in earned -> 12
            firstDelivery == null -> 0
            else -> minOf(11, Math.floorDiv(millisBetween(firstDelivery, deliveries.last().deliveredAt), (30.4375 * DAY_MILLIS).toLong()).toInt())
        }
        val counts = mapOf(
            "first" to stats.deliveredCount, "ten" to stats.deliveredCount, "theRegular" to stats.deliveredCount, "fifty" to stats.deliveredCount,
            "connected" to stats.carrierCount, "express" to if (fastest != null && fastest.duration <= 2 * DAY_SECONDS) 1 else 0,
            "acrossBorders" to stats.crossBorderCount, "newHorizons" to deliveries.count { it.crossContinent },
            "aroundWorld" to reached.size, "tenCountries" to reached.size, "rightNextDoor" to stats.domesticDeliveryCount,
            "worthTheWait" to stats.longWaitDeliveryCount, "busyDoorstep" to stats.maxDeliveriesInOneDay, "pickedUp" to stats.pickupDeliveryCount,
            "homeForHolidays" to stats.decemberDeliveryCount, "familiarFace" to (byCarrier.values.maxOrNull() ?: 0),
            "secondTry" to deliveries.count { it.secondTry }, "fourSeasons" to seasons.size,
            "firstAnniversary" to months,
        )
        stamps = PassportData.stamps.map { stamp ->
            val count = counts[stamp.key] ?: 0
            val delivery = if (count >= stamp.total) earned[stamp.key] else null
            StampProgress(stamp, count, delivery?.deliveredAt, delivery?.parcelId, delivery?.label)
        }
        countries = reached.entries
            .sortedWith { left, right ->
                if (left.value.firstAt != right.value.firstAt) left.value.firstAt.compareTo(right.value.firstAt) else left.key.compareTo(right.key)
            }
            .mapIndexed { index, (code, value) ->
                CountryStamp(code, index + 1, value.firstAt, value.delivery.parcelId, value.delivery.label, value.count, PassportData.continentOf(code))
            }
    }

    /**
     * What Friends shows of a passport (`sharedPassport`): the twelve stamps the apps before the new ones know, in their
     * order, as earned in this book. Older apps decode a strict list.
     */
    val sharedStamps: List<FriendStamp>
        get() {
            val earned = stamps.filter { it.earned }.mapTo(HashSet()) { it.key }
            return FriendStamp.entries.filter { it.rawValue in earned }
        }

    companion object {
        /** Carrier ids that stand for "not known yet" rather than a carrier: they never count toward Well connected. */
        private val PLACEHOLDER_CARRIERS = setOf("unknown", "intl-post")
        private const val DAY_SECONDS = 86_400.0
        private const val DAY_MILLIS = 86_400_000L

        private fun millisBetween(from: Instant, to: Instant): Long = Duration.between(from, to).toMillis()
    }
}

/**
 * "Your <year> in parcels" (the web's `yearInParcels`): the year's deliveries on the reader's calendar, the countries
 * they went through (the six with the most of them, in the passport's order, when there are more), the stamps they
 * earned and the fastest one, in seconds. Aggregates only: no parcel, shop, carrier or place below the country.
 */
class YearInParcels(book: PassportBook, val year: Int) {
    /** A country the year's parcels went through, with how many of them did and the year's first delivery through it. */
    class Country(val stamp: CountryStamp, val count: Int, val firstAt: Instant)

    val deliveredCount: Int
    val countryCount: Int
    val countries: List<Country>
    val stamps: List<StampProgress>
    val fastest: Double?

    init {
        val prefix = "${year.toString().padStart(4, '0')}-"
        val deliveries = book.stats.deliveries.filter { it.day.startsWith(prefix) }
        val parcels = deliveries.mapTo(HashSet()) { it.parcelId }
        val counts = HashMap<String, Int>()
        // A country's day on the card is the year's first delivery through it, never an earlier year's.
        val firsts = HashMap<String, Instant>()
        for (delivery in deliveries) {
            for (code in delivery.countries.keys) {
                counts[code] = (counts[code] ?: 0) + 1
                val first = firsts[code]
                if (first == null || delivery.deliveredAt < first) firsts[code] = delivery.deliveredAt
            }
        }
        val all = book.countries.filter { it.code in counts }.map { Country(it, counts.getValue(it.code), firsts.getValue(it.code)) }
        deliveredCount = deliveries.size
        countryCount = all.size
        countries = if (all.size <= 6) {
            all
        } else {
            all.sortedWith { left, right -> if (left.count != right.count) right.count.compareTo(left.count) else left.stamp.number.compareTo(right.stamp.number) }
                .take(6).sortedBy { it.stamp.number }
        }
        stamps = book.stamps.filter { it.earned && it.parcelId != null && it.parcelId in parcels }
        fastest = deliveries.mapNotNull { it.duration }.minOrNull()
    }
}
