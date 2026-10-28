package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale
import kotlin.math.PI
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/** A point on the globe, in degrees. */
class GeoPoint(val longitude: Double, val latitude: Double) {
    val vector: Vector3
        get() {
            val lambda = longitude * PI / 180
            val phi = latitude * PI / 180
            return Vector3(cos(phi) * cos(lambda), cos(phi) * sin(lambda), sin(phi))
        }

    /** The great-circle angle to another point, in radians. */
    fun angle(to: GeoPoint): Double {
        val phi1 = latitude * PI / 180
        val phi2 = to.latitude * PI / 180
        val a = sin((phi2 - phi1) / 2).pow(2) +
            cos(phi1) * cos(phi2) * sin((to.longitude - longitude) * PI / 360).pow(2)
        return 2 * asin(swiftMin(1.0, sqrt(a)))
    }

    fun kilometres(to: GeoPoint): Double = angle(to) * EARTH_KILOMETRES

    /** The point `fraction` of the way along the great circle to `other`. */
    fun interpolated(to: GeoPoint, fraction: Double): GeoPoint {
        val angle = angle(to)
        if (!(angle > 1e-9)) return this
        return GeoPoint(vector * (sin((1 - fraction) * angle) / sin(angle)) + to.vector * (sin(fraction * angle) / sin(angle)))
    }

    override fun equals(other: Any?): Boolean = other is GeoPoint && longitude == other.longitude && latitude == other.latitude
    override fun hashCode(): Int = Point(longitude, latitude).hashCode()
    override fun toString(): String = "GeoPoint(longitude=$longitude, latitude=$latitude)"

    companion object {
        const val EARTH_KILOMETRES = 6_371.0

        operator fun invoke(vector: Vector3): GeoPoint {
            val length = sqrt(vector.dot(vector))
            return GeoPoint(
                longitude = atan2(vector.y, vector.x) * 180 / PI,
                latitude = asin(swiftMax(-1.0, swiftMin(1.0, if (length > 0) vector.z / length else 0.0))) * 180 / PI,
            )
        }

        /** The middle of some points on the sphere. */
        fun mean(points: List<GeoPoint>): GeoPoint {
            val sum = points.fold(Vector3.Zero) { sum, point -> sum + point.vector }
            if (!(sum.dot(sum) > 1e-12)) return points.firstOrNull() ?: GeoPoint(0.0, 0.0)
            return GeoPoint(sum)
        }

        /** The widest angle between any two of the points, in radians. */
        fun extent(points: List<GeoPoint>): Double {
            var extent = 0.0
            for (a in points) for (b in points) extent = swiftMax(extent, a.angle(b))
            return extent
        }
    }
}

/** A scan's place on the map. A country-level place sits on the country's label point. */
data class RoutePlace(
    val id: String,
    val name: String,
    /** The facility's own name, longer than its town's: "Zürich-Mülligen". */
    val site: String? = null,
    val country: String,
    val point: GeoPoint,
    val isCountry: Boolean,
) {
    /** A place the server found for a scan; scans a kilometre apart are the same stop. */
    constructor(place: EventPlace) : this(
        id = "${fixed2(place.latitude)},${fixed2(place.longitude)}",
        name = place.name,
        site = place.site,
        country = place.country,
        point = GeoPoint(longitude = place.longitude, latitude = place.latitude),
        isCountry = place.precision == EventPlacePrecision.Country,
    )

    /** What to call the place: its town on a card, the facility itself on the opened map, which has the room. */
    fun name(sites: Boolean): String = if (sites) site ?: name else name

    companion object {
        fun country(code: String, name: String, at: GeoPoint): RoutePlace =
            RoutePlace(id = code, name = name, country = code, point = at, isCountry = true)

        /** `String(format: "%.2f", value)`: the exact binary value rounded half to even, as C's printf does. */
        private fun fixed2(value: Double): String {
            if (value.isNaN()) return "nan"
            if (value.isInfinite()) return if (value > 0) "inf" else "-inf"
            val text = BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).toPlainString()
            return if ((value < 0 || 1 / value < 0) && !text.startsWith("-")) "-$text" else text
        }
    }
}

/**
 * A parcel's scans as a journey: its stops, the legs between them and what is left to go.
 * It follows the same rules as the web map (`src/components/map/route.ts`).
 */
class ParcelRoute(places: List<RoutePlace?>, deliveries: Int = 0, destination: RoutePlace? = null) {
    enum class Scale { None, Point, City, Local, Region, World }
    enum class Mode { Journey, Now }

    data class Stop(val id: String, val place: RoutePlace)

    data class Leg(val id: String, val from: RoutePlace, val to: RoutePlace, val kilometres: Double) {
        /** A leg to or from a whole country only roughly shows where the parcel went. */
        val isApproximate: Boolean get() = from.isCountry || to.isCountry
    }

    val stops: List<Stop>
    val legs: List<Leg>

    /**
     * False when the newest scan has no place, so the last stop is only the last known one. A delivery is where
     * its scan was placed: a later delivery scan with no place doesn't make it unknown. A delivery with no place at
     * all happened at the last stop when that stop is in the destination country.
     */
    val latestLocated: Boolean

    /** Where the parcel is headed, until it gets there. */
    val destination: RoutePlace?
    val remainingKilometres: Double?
    val kilometres: Double
    val countries: List<String>
    val extentKilometres: Double
    val scale: Scale

    /** The newest stops close to the current one: what the close-up frames. */
    val near: List<Stop>

    val origin: Stop? get() = stops.firstOrNull()
    val current: Stop? get() = stops.lastOrNull()

    /** Places oldest first; nil for a scan without one. `deliveries` counts the newest scans that are deliveries. */
    init {
        val stops = mutableListOf<Stop>()
        for (place in places) {
            if (place == null) continue
            val last = stops.lastOrNull()
            if (last != null) {
                if (last.place.id == place.id) continue
                // "Germany" after Hamburg adds nothing to the map.
                if (last.place.country == place.country && place.isCountry) continue
                // A city makes an earlier country-level scan precise.
                if (last.place.country == place.country && last.place.isCountry) {
                    stops[stops.size - 1] = last.copy(place = place)
                    continue
                }
            }
            stops.add(Stop(id = "${place.id}:${stops.size}", place = place))
        }
        val legs = stops.zipWithNext { from, to ->
            Leg(id = "${from.id}>${to.id}", from = from.place, to = to.place, kilometres = from.place.point.kilometres(to.place.point))
        }
        var remaining: RoutePlace? = null
        val current = stops.lastOrNull()
        if (current != null && destination != null) {
            val arrived = current.place.id == destination.id ||
                (destination.isCountry && current.place.country == destination.country) ||
                current.place.point.kilometres(destination.point) < 15
            remaining = if (arrived) null else destination
        }
        val points = stops.map { it.place.point } + listOfNotNull(remaining?.point)
        val extent = GeoPoint.extent(points) * GeoPoint.EARTH_KILOMETRES
        val near = ArrayDeque<Stop>()
        if (current != null) {
            for (stop in stops.asReversed()) {
                if (!(stop.place.point.kilometres(current.place.point) <= NEAR_KILOMETRES)) break
                near.addFirst(stop)
            }
        }
        this.stops = stops
        this.legs = legs
        // A delivery with no place happened at the last stop when that stop is in the country the parcel was going to.
        latestLocated = places.takeLast(max(deliveries, 1)).any { it != null } ||
            (deliveries > 0 && destination != null && stops.lastOrNull()?.place?.country == destination.country)
        this.destination = remaining
        remainingKilometres = if (current != null && remaining != null) current.place.point.kilometres(remaining.point) else null
        kilometres = legs.fold(0.0) { sum, leg -> sum + leg.kilometres }
        countries = stops.map { it.place.country }.distinct()
        extentKilometres = extent
        scale = when {
            points.isEmpty() -> Scale.None
            points.size == 1 -> Scale.Point
            extent > 2_500 -> Scale.World
            extent > 400 -> Scale.Region
            extent > 30 -> Scale.Local
            else -> Scale.City
        }
        this.near = near.toList()
    }

    /** A parcel's scans, in the journal's order. Country-only places take the reader's name for the country. */
    constructor(events: List<TrackingEvent>, destination: RoutePlace? = null, countryName: (String) -> String) :
        this(Journal(events), destination, countryName)

    private constructor(journal: Journal, destination: RoutePlace?, countryName: (String) -> String) : this(
        places = journal.newestFirst.asReversed().map { event ->
            val place = event.place ?: return@map null
            val located = RoutePlace(place)
            if (located.isCountry) located.copy(name = countryName(located.country)) else located
        },
        deliveries = journal.newestFirst.takeWhile { it.stage == TrackingStage.Delivered }.size,
        destination = destination,
    )

    /** A generated track's places are left out: the route ends where the parcel really was last. */
    private class Journal(events: List<TrackingEvent>) {
        val newestFirst: List<TrackingEvent> = GeneratedTrack.without(events).sortedWith(eventOrderComparator)
    }

    /** Both views only make sense when part of the journey, travelled or still to go, lies outside the close-up. */
    val hasNearView: Boolean get() = near.size < stops.size || (remainingKilometres ?: 0.0) >= NEAR_KILOMETRES

    /** The camera follows the parcel: the whole trip while it travels, a close-up for the last mile. */
    fun defaultMode(stage: TrackingStage?): Mode {
        if (!hasNearView) return Mode.Journey
        return when (stage) {
            TrackingStage.OutForDelivery, TrackingStage.ReadyForPickup, TrackingStage.FailedAttempt -> Mode.Now
            else -> Mode.Journey
        }
    }

    companion object {
        /** Places closer than this to the current one belong to the close-up. */
        const val NEAR_KILOMETRES = 400.0

        /**
         * Kilometres rounded the way a journey is told: "8 km", "450 km", "9,300 km". In the app's languages, as the
         * iPhone writes them (`foundationKilometres`); another locale is the phone's ICU's.
         */
        fun formattedKilometres(kilometres: Double, locale: Locale): String {
            val rounded = if (kilometres < 100) {
                kilometres.rounded()
            } else if (kilometres < 1_000) {
                (kilometres / 10).rounded() * 10
            } else {
                (kilometres / 100).rounded() * 100
            }
            val language = AppLanguage.entries.firstOrNull { it.locale == locale }
            val value = swiftMax(rounded, 1.0)
            return if (language != null) foundationKilometres(value, language) else Icu.lengths.kilometres(value, locale)
        }
    }
}
