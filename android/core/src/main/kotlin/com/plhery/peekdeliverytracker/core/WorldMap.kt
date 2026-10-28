package com.plhery.peekdeliverytracker.core

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Locale
import java.util.WeakHashMap
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.hypot
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

// From WorldMap.swift, everything but the views: the map data, the camera and its projection, the outlines it fills,
// the palettes, the layout of what is drawn over the land, and what MapPainter draws, as steps for :app's canvas to
// carry out. WorldMapView and PulsingHalo are :app's.

// MARK: - Map data

/**
 * Countries, borders, lakes and cities from `World.json`, the Natural Earth data the web map
 * draws. It ships with the app, so showing a parcel's journey asks no map service anything.
 */
class WorldAtlas(data: ByteArray) {
    enum class Detail { Coarse, Fine }

    /**
     * A shape with the smallest cap around it, so a view can skip what it cannot see.
     * Points are unit vectors: projecting one takes three dot products.
     */
    class Part(val rings: List<List<Vector3>>, val center: Vector3, val radius: Double)

    class Country(
        val code: String,
        val name: String,
        val label: GeoPoint?,
        /** Natural Earth's label rank: 1 for the largest countries, up to 10. */
        val rank: Int,
        val parts: List<Part>,
    )

    class City(val point: GeoPoint, val name: String, val rank: Int)

    class Layer(
        val land: List<Part>,
        val borders: List<Part>,
        /** In the file's order, which decides which names win when they collide. */
        val countries: List<Country>,
        val byCode: Map<String, Country>,
    )

    val coarse: Layer
    val fine: Layer
    val lakes: List<Part>

    /** Major cities, most prominent first. */
    val cities: List<City>

    fun layer(detail: Detail): Layer = if (detail == Detail.Fine) fine else coarse

    /** Where a country's name sits: country-only scans and destinations are drawn there. */
    fun label(of: String): GeoPoint? = coarse.byCode[of]?.label

    /** The file stores shared arcs once, so neighbouring borders always line up. */
    init {
        val file = Json.parseToJsonElement(data.decodeToString()).jsonObject
        val precision = file.getValue("precision").decodedDouble()
        val arcsByDetail = file.getValue("arcs").jsonObject.mapValues { (_, arcs) -> arcs.jsonArray.map(::ints) }
        val borderIndexes = ints(file.getValue("borders"))
        val entries = file.getValue("countries").jsonArray.map { element ->
            val entry = element.jsonObject
            Entry(
                code = entry["code"]?.takeUnless { it is JsonNull }?.decodedString(),
                name = entry.getValue("name").decodedString(),
                label = entry["label"]?.takeUnless { it is JsonNull }?.jsonArray?.map(JsonElement::decodedDouble),
                rank = entry.getValue("rank").decodedInt(),
                polygons = entry.getValue("polygons").jsonArray.map { polygon -> polygon.jsonArray.map(::ints) },
            )
        }
        val lakeArcs = file.getValue("lakes").jsonArray.map(::ints)
        val cityEntries = file.getValue("cities").jsonArray.map { element ->
            val values = element.jsonArray
            City(
                point = GeoPoint(longitude = values[0].decodedDouble(), latitude = values[1].decodedDouble()),
                name = values[2].decodedString(),
                rank = values[3].decodedInt(),
            )
        }

        val decode = { flat: List<Int> ->
            val points = ArrayList<Vector3>(flat.size / 2)
            var x = 0
            var y = 0
            var index = 0
            while (index + 1 < flat.size) {
                x += flat[index]
                y += flat[index + 1]
                points.add(GeoPoint(longitude = x.toDouble() / precision, latitude = y.toDouble() / precision).vector)
                index += 2
            }
            points as List<Vector3>
        }
        val layer = { detail: String ->
            val arcs = (arcsByDetail[detail] ?: emptyList()).map(decode)
            val ring = { indexes: List<Int> ->
                val points = mutableListOf<Vector3>()
                for (index in indexes) {
                    val arc = if (index < 0) arcs[index.inv()].asReversed() else arcs[index]
                    points.addAll(if (points.isEmpty()) arc else arc.drop(1))
                }
                points as List<Vector3>
            }
            val land = mutableListOf<Part>()
            val countries = mutableListOf<Country>()
            for (entry in entries) {
                val parts = entry.polygons
                    .map { polygon -> polygon.map(ring).filter { it.size >= 4 } }
                    .filter { it.isNotEmpty() }
                    .map { part(it, outline = it[0]) }
                land += parts
                val code = entry.code
                if (code.isNullOrEmpty()) continue
                val label = entry.label?.let { if (it.size == 2) GeoPoint(longitude = it[0], latitude = it[1]) else null }
                countries.add(Country(code = code, name = entry.name, label = label, rank = entry.rank, parts = parts))
            }
            val borders = borderIndexes.filter { it in arcs.indices }.map { part(listOf(arcs[it]), outline = arcs[it]) }
            val byCode = LinkedHashMap<String, Country>()
            for (country in countries) byCode.putIfAbsent(country.code, country)
            Layer(land = land, borders = borders, countries = countries, byCode = byCode)
        }
        coarse = layer("coarse")
        fine = layer("fine")
        lakes = lakeArcs.map { flat ->
            val points = decode(flat)
            part(listOf(points), outline = points)
        }
        cities = cityEntries
    }

    private class Entry(val code: String?, val name: String, val label: List<Double>?, val rank: Int, val polygons: List<List<List<Int>>>)

    companion object {
        private val loaded: WorldAtlas? by lazy {
            try {
                WorldAtlas(SharedResources.open("World.json").use { it.readBytes() })
            } catch (_: Exception) {
                null
            }
        }

        /** Parsed once, off the main thread, when a map first needs it. */
        suspend fun bundled(): WorldAtlas? = withContext(Dispatchers.Default) { loaded }

        private fun ints(element: JsonElement): List<Int> = element.jsonArray.map(JsonElement::decodedInt)

        private fun part(rings: List<List<Vector3>>, outline: List<Vector3>): Part {
            val sum = outline.fold(Vector3.Zero) { sum, point -> sum + point }
            val length = sum.length
            val center = if (length > 1e-9) sum / length else (outline.firstOrNull() ?: Vector3(1.0, 0.0, 0.0))
            val radius = outline.fold(0.0) { radius, point -> swiftMax(radius, acos(swiftMax(-1.0, swiftMin(1.0, center.dot(point))))) }
            return Part(rings = rings, center = center, radius = radius)
        }
    }
}

// MARK: - Camera

/**
 * Where the map looks from. One orthographic projection serves every scale: zoomed out it is
 * a globe, zoomed in it is a flat map, so the camera moves between the two without a switch.
 */
data class GlobeCamera(
    val center: GeoPoint,
    /** The globe's radius, in points. */
    val scale: Double,
    /** Where the center sits on screen. */
    val offset: Point,
) {
    override fun equals(other: Any?): Boolean =
        other is GlobeCamera && center == other.center && scale == other.scale && offset == other.offset

    override fun hashCode(): Int = (center.hashCode() * 31 + Point(scale, 0.0).hashCode()) * 31 + offset.hashCode()

    /**
     * Zooms by `ratio` about a point on screen, from the whole globe down to a town. A globe
     * that no longer fills the view drifts back to `middle`, so zooming out never loses it.
     */
    fun zoomed(by: Double, around: Point, middle: Point, viewport: Double): GlobeCamera {
        val whole = viewport / 2
        val scale = swiftMax(whole * 0.9, swiftMin(viewport / (20 / GeoPoint.EARTH_KILOMETRES), this.scale * by))
        val k = scale / this.scale
        val settle = swiftMax(0.0, swiftMin(1.0, (whole * 2.5 - scale) / (whole * 1.5)))
        val x = around.x + (offset.x - around.x) * k
        val y = around.y + (offset.y - around.y) * k
        return GlobeCamera(center = center, scale = scale, offset = Point(x + (middle.x - x) * settle, y + (middle.y - y) * settle))
    }

    companion object {
        /**
         * Frames the points in `box`, never closer than `minimumKilometres` across, and shows the
         * whole globe past `globeAbove` radians. `tilt` looks at a long route from nearer the
         * equator: seen from the side, a great circle bows toward the pole, the way flights do.
         */
        fun fit(
            points: List<GeoPoint>,
            box: Rect,
            minimumKilometres: Double,
            globeAbove: Double = 2.4,
            tilt: Boolean = false,
        ): GlobeCamera {
            val mean = GeoPoint.mean(points)
            val center = if (tilt) tilted(points, mean) else mean
            val radius = swiftMin(box.width, box.height) / 2
            val middle = Point(box.midX, box.midY)
            if (GeoPoint.extent(points) > globeAbove) {
                return GlobeCamera(center = center, scale = radius * 0.94, offset = middle)
            }
            val unit = GlobeProjection(GlobeCamera(center = center, scale = 1.0, offset = Point.Zero))
            val projected = points.map { point ->
                val vector = point.vector
                if (unit.facing(vector) >= 0) unit.screen(vector) else Point.Zero
            }
            val minimum = minimumKilometres / GeoPoint.EARTH_KILOMETRES
            val xs = projected.map { it.x }
            val ys = projected.map { it.y }
            val minX = xs.swiftMin() ?: 0.0
            val maxX = xs.swiftMax() ?: 0.0
            val minY = ys.swiftMin() ?: 0.0
            val maxY = ys.swiftMax() ?: 0.0
            // Never zoom out past the whole globe.
            val scale = swiftMax(
                radius * 0.94,
                swiftMin(box.width / swiftMax(maxX - minX, minimum), box.height / swiftMax(maxY - minY, minimum)),
            )
            return GlobeCamera(
                center = center, scale = scale,
                offset = Point(middle.x - scale * (minX + maxX) / 2, middle.y - scale * (minY + maxY) / 2),
            )
        }

        private fun tilted(points: List<GeoPoint>, center: GeoPoint): GeoPoint {
            val first = points.firstOrNull() ?: return center
            var pair = first to first
            var extent = 0.0
            for (a in points) {
                for (b in points) {
                    val angle = a.angle(b)
                    if (angle > extent) {
                        extent = angle
                        pair = a to b
                    }
                }
            }
            val angle = swiftMin(0.5, extent * 0.32)
            if (!(angle >= 0.05)) return center
            var normal = pair.first.vector.cross(pair.second.vector)
            val length = normal.length
            normal /= if (length > 0) length else 1.0
            val middle = center.vector
            // Step off the route toward the equator.
            if (normal.z * middle.z > 0) normal = -normal
            return GeoPoint(middle * cos(angle) + normal * sin(angle))
        }

        /** Flies between cameras, easing out to a wider view when the move is long. */
        fun flight(from: GlobeCamera, to: GlobeCamera, viewport: Double): (Double) -> GlobeCamera {
            val travel = from.center.angle(to.center) * swiftMin(from.scale, to.scale)
            val lift = if (travel > viewport) swiftMin(ln(travel / viewport) * 0.8, 2.2) else 0.0
            val a = ln(from.scale)
            val b = ln(to.scale)
            return { t ->
                GlobeCamera(
                    center = from.center.interpolated(to.center, t),
                    scale = exp(a + (b - a) * t - lift * sin(PI * t)),
                    offset = Point(
                        from.offset.x + (to.offset.x - from.offset.x) * t,
                        from.offset.y + (to.offset.y - from.offset.y) * t,
                    ),
                )
            }
        }

        fun flightDuration(from: GlobeCamera, to: GlobeCamera): Double =
            swiftMin(1.5, 0.7 + from.center.angle(to.center) * 0.5 + abs(ln(to.scale / from.scale)) * 0.12)

        fun easeInOut(t: Double): Double = if (t < 0.5) 4 * t * t * t else 1 - (-2 * t + 2).pow(3) / 2

        /** Where the sun is overhead, accurate to a degree or so: enough for a soft night side. */
        fun subsolarPoint(date: Instant): GeoPoint {
            val utc = date.atZone(ZoneOffset.UTC)
            val start = LocalDate.of(utc.year, 1, 1).atStartOfDay(ZoneOffset.UTC).toEpochSecond() - 86_400
            val day = ((date.epochSecond - start).toDouble() + date.nano / 1e9) / 86_400
            val declination = -23.44 * cos(2 * PI / 365 * (day + 10))
            val hours = utc.hour.toDouble() + utc.minute.toDouble() / 60
            return GeoPoint(longitude = (-15 * (hours - 12) + 540) % 360 - 180, latitude = declination)
        }

        /**
         * The camera that frames a route in the room the insets leave: the whole journey, or the parcel's surroundings.
         * `covered` is a box the card writes over the map, such as a second carrier's mark in its corner, and `shown`
         * tells how much of the journey a camera shows (see `MapOverlay.shown`), around the box when it is given one. A
         * route that would pass under the box, or that loses a name or Pip's spot to it, is framed below it or beside
         * it: where it shows most, then where it is drawn larger. `pip` says Pip looks for a spot beside the parcel's
         * dot, which a route that ends at the frame's edge can leave him none of: it is framed again with room for him
         * there. Every other route keeps its usual frame. `closeUps` says the map draws rivers, roads and towns up
         * close, which lets it come closer to a short journey.
         */
        fun framing(
            route: ParcelRoute,
            mode: ParcelRoute.Mode,
            size: Size,
            insets: EdgeInsets,
            covered: Rect? = null,
            shown: ((GlobeCamera, Rect?) -> Double)? = null,
            pip: Boolean = false,
            closeUps: Boolean = false,
        ): GlobeCamera {
            val framed = { room: EdgeInsets -> framed(route, mode, size, room, closeUps) }
            val usual = framed(insets)
            val clear = covered?.let { clearOf(route, insets, framed, usual, it, shown) } ?: usual
            if (clear != usual || !pip || shown == null) return clear
            return roomForPip(route, size, insets, framed, usual) { shown(it, covered) }
        }

        /** The frame of a route the card writes over, below the box or beside it when the usual one loses something to it. */
        private fun clearOf(
            route: ParcelRoute,
            insets: EdgeInsets,
            framed: (EdgeInsets) -> GlobeCamera,
            usual: GlobeCamera,
            covered: Rect,
            shown: ((GlobeCamera, Rect?) -> Double)?,
        ): GlobeCamera {
            val under = passesUnder(route, usual, covered)
            val names = { camera: GlobeCamera -> shown?.invoke(camera, covered) ?: 0.0 }
            val kept = names(usual)
            if (!under && kept >= (shown?.invoke(usual, null) ?: 0.0)) return usual
            val lower = insets.copy(top = swiftMax(insets.top, covered.maxY))
            val narrower = insets.copy(leading = swiftMax(insets.leading, covered.maxX))
            val below = framed(lower)
            val beside = framed(narrower)
            val frames = mutableListOf(below to below.scale, beside to beside.scale)
            // Further from the box for as long as the route is drawn as large: a name may need the room between them.
            for (step in 1..6) {
                val further = narrower.copy(leading = narrower.leading + step * 16.0)
                val camera = framed(further)
                if (camera.scale < beside.scale * 0.999) break
                frames.add(camera to beside.scale)
            }
            // The most names, then the largest drawing, then the sooner frame.
            var best: Pair<GlobeCamera, Double>? = null
            var bestScale = 0.0
            var bestIndex = 0
            for ((index, frame) in frames.withIndex()) {
                val count = names(frame.first)
                val current = best
                if (current == null || current.second < count ||
                    (current.second == count && (bestScale < frame.second || (bestScale == frame.second && -bestIndex < -index)))
                ) {
                    best = frame.first to count
                    bestScale = frame.second
                    bestIndex = index
                }
            }
            // Nothing under the box and nothing to win back: the route stays where every card shows it.
            val chosen = best ?: return usual
            if (!(under || chosen.second > kept)) return usual
            return chosen.first
        }

        /**
         * Pip stands beside the parcel's dot, and a route that ends at the frame's edge can leave him no room there. It
         * is framed again with more room on the dot's side, across and along, a step at a time, and takes the largest
         * frame where he stands and every place keeps its name. A route he already stands beside keeps its frame.
         */
        private fun roomForPip(
            route: ParcelRoute,
            size: Size,
            insets: EdgeInsets,
            framed: (EdgeInsets) -> GlobeCamera,
            usual: GlobeCamera,
            shown: (GlobeCamera) -> Double,
        ): GlobeCamera {
            val kept = shown(usual)
            val projection = GlobeProjection(usual)
            // A name counts a whole point and Pip half of one: he has a spot already.
            val current = route.current?.place?.point
            if (kotlin.math.floor(kept) != kept || current == null || !projection.sees(current)) return usual
            val dot = projection.project(current)
            val leading = dot.x < (insets.leading + size.width - insets.trailing) / 2
            val top = dot.y < (insets.top + size.height - insets.bottom) / 2
            val frames = mutableListOf<GlobeCamera>()
            for (step in 1..6) {
                val further = step * 16.0
                val across = if (leading) insets.copy(leading = insets.leading + further) else insets.copy(trailing = insets.trailing + further)
                val along = if (top) insets.copy(top = insets.top + further) else insets.copy(bottom = insets.bottom + further)
                val both = if (top) across.copy(top = across.top + further) else across.copy(bottom = across.bottom + further)
                frames += listOf(across, along, both).map(framed)
            }
            // The largest frame first, and of two as large, the one with less room added.
            return frames.withIndex()
                .sortedWith { a, b ->
                    when {
                        a.value.scale > b.value.scale || (a.value.scale == b.value.scale && -a.index > -b.index) -> -1
                        b.value.scale > a.value.scale || (b.value.scale == a.value.scale && -b.index > -a.index) -> 1
                        else -> 0
                    }
                }
                .firstOrNull { shown(it.value) > kept }?.value ?: usual
        }

        /** Whether a place of the route, or one of its legs, would be drawn under a box of the frame. */
        private fun passesUnder(route: ParcelRoute, camera: GlobeCamera, box: Rect): Boolean {
            val projection = GlobeProjection(camera)
            // A dot is drawn around its place and a leg has a width: both keep a little way off.
            val near = { point: Point ->
                point.x > box.minX - 10 && point.x < box.maxX + 10 && point.y > box.minY - 10 && point.y < box.maxY + 10
            }
            val places = route.stops.map { it.place.point } + listOfNotNull(route.destination?.point)
            val legs = route.legs.map { Triple(it.from.point, it.to.point, it.kilometres) }.toMutableList()
            val current = route.current?.place?.point
            val destination = route.destination?.point
            if (current != null && destination != null) legs.add(Triple(current, destination, route.remainingKilometres ?: 0.0))
            return places.any { projection.sees(it) && near(projection.project(it)) } ||
                legs.any { MapOverlay.track(it.first, it.second, it.third, projection).points.any(near) }
        }

        private fun framed(route: ParcelRoute, mode: ParcelRoute.Mode, size: Size, insets: EdgeInsets, closeUps: Boolean): GlobeCamera {
            val inner = swiftMin(size.width - insets.leading - insets.trailing, size.height - insets.top - insets.bottom)
            val pad = swiftMin(40.0, inner * 0.12)
            val box = Rect(
                insets.leading + pad, insets.top + pad,
                swiftMax(size.width - insets.leading - insets.trailing - pad * 2, 40.0),
                swiftMax(size.height - insets.top - insets.bottom - pad * 2, 40.0),
            )
            val destination = route.destination?.point
            val current = route.current?.place?.point
            if (mode == ParcelRoute.Mode.Now && current != null) {
                val points = route.near.map { it.place.point }.toMutableList()
                if (destination != null && destination.kilometres(current) < ParcelRoute.NEAR_KILOMETRES) points.add(destination)
                // Like the journey below, a close-up of countries is no town-sized window on their label points.
                return fit(points, box, minimumKilometres = if (route.near.all { it.place.isCountry }) 1_500.0 else 260.0)
            }
            val ends = route.stops.map { it.place.point } + listOfNotNull(destination)
            // Frame the arcs as well as their ends, so a bowed route never leaves the view.
            val arcs = route.legs.map { Triple(it.from.point, it.to.point, it.kilometres) }.toMutableList()
            if (current != null && destination != null) arcs.add(Triple(current, destination, route.remainingKilometres ?: 0.0))
            val middles = arcs.filter { it.third > 300 }.flatMap { (from, to, _) -> listOf(0.25, 0.5, 0.75).map { from.interpolated(to, it) } }
            // How close the camera may come, across the frame's shorter side, by how far the journey's places spread.
            // Places a town or two apart are framed as one place is: closer, a map of land alone is an empty field, and
            // its places would still crowd one another. A map that draws close-ups has rivers, roads and towns to show
            // around them, and comes closer to set them apart.
            val minimum = when (route.scale) {
                ParcelRoute.Scale.World -> 400.0
                ParcelRoute.Scale.Region -> 300.0
                ParcelRoute.Scale.Local -> 120.0
                ParcelRoute.Scale.City -> if (closeUps) 70.0 else 260.0
                ParcelRoute.Scale.Point -> 260.0
                ParcelRoute.Scale.None -> 0.0
            }
            // A route of countries only frames the countries, not a town-sized window on their label points.
            val countriesOnly = route.stops.isNotEmpty() && route.stops.all { it.place.isCountry }
            if (ends.isEmpty()) {
                return fit(listOf(GeoPoint(longitude = 8.2, latitude = 42.0)), box, minimumKilometres = 1e5, globeAbove = -1.0)
            }
            return fit(
                ends + middles, box, minimumKilometres = if (countriesOnly) swiftMax(minimum, 1_500.0) else minimum,
                tilt = route.scale == ParcelRoute.Scale.World,
            )
        }
    }
}

/** Swift's `min()` of a sequence: the first of the smallest, nil when empty. */
internal fun List<Double>.swiftMin(): Double? {
    if (isEmpty()) return null
    var result = this[0]
    for (index in 1 until size) if (this[index] < result) result = this[index]
    return result
}

/** Swift's `max()` of a sequence: the first of the largest, nil when empty. */
internal fun List<Double>.swiftMax(): Double? {
    if (isEmpty()) return null
    var result = this[0]
    for (index in 1 until size) if (result < this[index]) result = this[index]
    return result
}

/** The orthographic projection for one camera. */
class GlobeProjection(val camera: GlobeCamera) {
    /** Toward the viewer through the camera's center, and the directions of screen right and up there. */
    val forward: Vector3
    val east: Vector3
    val north: Vector3

    init {
        val lambda = camera.center.longitude * PI / 180
        val phi = camera.center.latitude * PI / 180
        forward = Vector3(cos(phi) * cos(lambda), cos(phi) * sin(lambda), sin(phi))
        east = Vector3(-sin(lambda), cos(lambda), 0.0)
        north = Vector3(-sin(phi) * cos(lambda), -sin(phi) * sin(lambda), cos(phi))
    }

    /** Above 0 the point faces the viewer; below 0 it is behind the globe. */
    fun facing(vector: Vector3): Double = vector.dot(forward)

    fun screen(vector: Vector3): Point = Point(screenX(vector), screenY(vector))

    /** `screen(vector)` one coordinate at a time, for the paths built each frame. */
    fun screenX(vector: Vector3): Double = camera.offset.x + camera.scale * vector.dot(east)
    fun screenY(vector: Vector3): Double = camera.offset.y - camera.scale * vector.dot(north)

    fun project(point: GeoPoint): Point = screen(point.vector)

    /** On the near side, with a little margin before the rim. */
    fun sees(point: GeoPoint): Boolean = facing(point.vector) > SEEN

    /** The place under a screen point, if the globe covers it. */
    fun invert(point: Point): GeoPoint? {
        val x = (point.x - camera.offset.x) / camera.scale
        val y = (camera.offset.y - point.y) / camera.scale
        val squared = x * x + y * y
        if (!(squared <= 1)) return null
        return GeoPoint(east * x + north * y + forward * sqrt(1 - squared))
    }

    private companion object {
        val SEEN = sin(0.02)
    }
}

// MARK: - Geometry

object MapGeometry {
    /**
     * A closed outline for an even-odd fill. Where the ring passes behind the globe, the outline
     * follows the rim, turning as the ring turns seen from behind. That outlines the visible part
     * of any shape clear of the point straight behind the globe. A shape around that point would
     * fill its hidden side instead, so it also takes in the whole `disk`: `around` says whether
     * the shape contains that point.
     */
    fun addRing(path: MapPath, ring: List<Vector3>, projection: GlobeProjection, around: Boolean = false, disk: MapPath) {
        outline(path, ring, projection, disk)
        if (around) path.addPath(disk)
    }

    // The map outlines every shape again each frame, so this works in plain numbers: a point seen from behind is the
    // pair of its dot products with east and north.
    private fun outline(path: MapPath, ring: List<Vector3>, projection: GlobeProjection, disk: MapPath) {
        val count = ring.size
        val facing = DoubleArray(count) { projection.facing(ring[it]) }
        val east = projection.east
        val north = projection.north
        // Start in front, so every stretch behind has both its ends on the rim.
        var start = -1
        for (index in 0 until count) {
            if (facing[index] >= 0) {
                start = index
                break
            }
        }
        if (start < 0) {
            // All of it behind: the rim all the way round if it winds around that point, else nothing.
            if (count == 0) return
            var turned = 0.0
            var previousX = ring[count - 1].dot(east)
            var previousY = ring[count - 1].dot(north)
            for (index in 0 until count) {
                val x = ring[index].dot(east)
                val y = ring[index].dot(north)
                turned += turn(previousX, previousY, x, y)
                previousX = x
                previousY = y
            }
            if (abs(turned) > PI) path.addPath(disk)
            return
        }
        path.move(projection.screenX(ring[start]), projection.screenY(ring[start]))
        // Behind the globe: where the ring went behind it, how far round it has turned since, and the last point it
        // passed there.
        var hidden = false
        var from = 0.0
        var turned = 0.0
        var lastX = 0.0
        var lastY = 0.0
        for (step in 1..count) {
            val a = (start + step - 1) % count
            val b = (start + step) % count
            if (facing[b] >= 0) {
                if (hidden) {
                    val edge = horizon(ring[a], facing[a], ring[b], facing[b])
                    // Angles on the rim run the other way on screen, whose y points down.
                    path.addRelativeArc(
                        center = projection.camera.offset, radius = projection.camera.scale,
                        startAngle = -from, delta = -(turned + turn(lastX, lastY, edge.dot(east), edge.dot(north))),
                    )
                    hidden = false
                }
                path.addLine(projection.screenX(ring[b]), projection.screenY(ring[b]))
            } else if (hidden) {
                val x = ring[b].dot(east)
                val y = ring[b].dot(north)
                turned += turn(lastX, lastY, x, y)
                lastX = x
                lastY = y
            } else {
                val edge = horizon(ring[a], facing[a], ring[b], facing[b])
                path.addLine(projection.screenX(edge), projection.screenY(edge))
                val edgeX = edge.dot(east)
                val edgeY = edge.dot(north)
                lastX = ring[b].dot(east)
                lastY = ring[b].dot(north)
                from = atan2(edgeY, edgeX)
                turned = turn(edgeX, edgeY, lastX, lastY)
                hidden = true
            }
        }
        path.closeSubpath()
    }

    /** The angle from one point seen from behind the globe to another, around the point straight behind it. */
    private fun turn(ax: Double, ay: Double, bx: Double, by: Double): Double = atan2(ax * by - ay * bx, ax * bx + ay * by)

    /**
     * The visible part of a cap `radius` radians around `center`, for an even-odd fill. Its circle
     * has a corner nearest the point behind the globe, so the outline and `around` always agree
     * on which side of the circle that point lies.
     */
    fun cap(around: Vector3, radius: Double, projection: GlobeProjection, disk: MapPath): MapPath {
        val path = MapPath()
        addRing(
            path, circle(around, radius, from = -projection.forward), projection,
            around = projection.facing(around) < -cos(radius), disk = disk,
        )
        return path
    }

    /** A country or lake, each ring outlined as `addRing` describes. */
    fun addPart(path: MapPath, part: WorldAtlas.Part, projection: GlobeProjection, disk: MapPath) {
        // Only a part whose cap reaches the point behind the globe can surround it.
        val reaches = acos(swiftMax(-1.0, swiftMin(1.0, -part.center.dot(projection.forward)))) <= part.radius
        for (ring in part.rings) {
            addRing(path, ring, projection, around = reaches && surroundsBack(ring, projection), disk = disk)
        }
    }

    /** Seen from the point behind the globe (a stereographic view), whether the ring winds around it. */
    private fun surroundsBack(ring: List<Vector3>, projection: GlobeProjection): Boolean {
        val east = projection.east
        val north = projection.north
        // The point straight behind is not seen from itself.
        var last = ring.size - 1
        while (last >= 0 && !(1 - projection.facing(ring[last]) > 1e-9)) last--
        if (last < 0) return false
        var distance = 1 - projection.facing(ring[last])
        var previousX = ring[last].dot(east) / distance
        var previousY = ring[last].dot(north) / distance
        var turned = 0.0
        for (index in ring.indices) {
            val vector = ring[index]
            distance = 1 - projection.facing(vector)
            if (!(distance > 1e-9)) continue
            val x = vector.dot(east) / distance
            val y = vector.dot(north) / distance
            turned += turn(previousX, previousY, x, y)
            previousX = x
            previousY = y
        }
        return abs(turned) > PI
    }

    /** An open line, cut where it passes behind the globe. */
    fun addLine(path: MapPath, line: List<Vector3>, projection: GlobeProjection) {
        var previous: Vector3? = null
        var previousFacing = 0.0
        var drawing = false
        for (index in line.indices) {
            val vector = line[index]
            val facing = projection.facing(vector)
            val before = previous
            if (facing >= 0) {
                if (drawing) {
                    path.addLine(projection.screenX(vector), projection.screenY(vector))
                } else {
                    if (before != null && previousFacing < 0) {
                        val edge = horizon(before, previousFacing, vector, facing)
                        path.move(projection.screenX(edge), projection.screenY(edge))
                        path.addLine(projection.screenX(vector), projection.screenY(vector))
                    } else {
                        path.move(projection.screenX(vector), projection.screenY(vector))
                    }
                    drawing = true
                }
            } else if (drawing && before != null) {
                val edge = horizon(before, previousFacing, vector, facing)
                path.addLine(projection.screenX(edge), projection.screenY(edge))
                drawing = false
            }
            previous = vector
            previousFacing = facing
        }
    }

    private fun horizon(a: Vector3, facingA: Double, b: Vector3, facingB: Double): Vector3 {
        val point = a + (b - a) * (facingA / (facingA - facingB))
        val length = point.length
        return if (length > 0) point / length else point
    }

    /** The great circle from `a` to `b`, sampled finely enough to curve smoothly. */
    fun greatCircle(a: GeoPoint, b: GeoPoint): List<Vector3> {
        val steps = max(2, ceil(a.angle(b) / 0.02).toInt())
        return (0..steps).map { a.interpolated(b, it.toDouble() / steps.toDouble()).vector }
    }

    /** A circle on the sphere, `radius` radians around `center`, starting on the side facing `from`. */
    fun circle(center: Vector3, radius: Double, steps: Int = 60, from: Vector3? = null): List<Vector3> {
        val helper = if (abs(center.z) < 0.9) Vector3(0.0, 0.0, 1.0) else Vector3(1.0, 0.0, 0.0)
        val aim = from?.let { it - center * it.dot(center) }
        val u = aim?.let { if (it.length > 1e-9) it.normalized() else null } ?: center.cross(helper).normalized()
        val w = center.cross(u)
        // center * cos(radius) + (u * cos(theta) + w * sin(theta)) * sin(radius), with one vector per point.
        val near = cos(radius)
        val out = sin(radius)
        return List(steps) { step ->
            val theta = step.toDouble() / steps.toDouble() * 2 * PI
            val c = cos(theta)
            val s = sin(theta)
            Vector3(
                center.x * near + (u.x * c + w.x * s) * out,
                center.y * near + (u.y * c + w.y * s) * out,
                center.z * near + (u.z * c + w.z * s) * out,
            )
        }
    }

    /** Meridians and parallels every 30°, the grid that shows the globe's curve. */
    val graticule: List<List<Vector3>> by lazy {
        val lines = mutableListOf<List<Vector3>>()
        for (meridian in 0 until 12) {
            val longitude = -180.0 + meridian * 30.0
            val limit = if (longitude % 90 == 0.0) 90.0 else 80.0
            val count = ((limit * 2) / 2.5).toInt()
            lines.add((0..count).map { GeoPoint(longitude = longitude, latitude = -limit + it * 2.5).vector })
        }
        for (parallel in 0..4) {
            val latitude = -60.0 + parallel * 30.0
            lines.add((0..144).map { GeoPoint(longitude = -180.0 + it * 2.5, latitude = latitude).vector })
        }
        lines
    }
}

// MARK: - Colours

/** Every colour the map draws with, so a card can tint it in its own ink. */
data class MapPalette(
    /** Nil leaves the card showing through. */
    val space: AdaptiveColor?,
    val ocean: AdaptiveColor?,
    val land: AdaptiveColor,
    /** Drawn over the land of countries the parcel passed through. */
    val visited: AdaptiveColor,
    /** What shows up close: built-up areas a shade off the land, roads a little further off it. */
    val urban: AdaptiveColor,
    val road: AdaptiveColor,
    val border: AdaptiveColor,
    val night: AdaptiveColor?,
    val grid: AdaptiveColor?,
    val limb: AdaptiveColor?,
    val shade: AdaptiveColor?,
    val route: AdaptiveColor,
    val routeMuted: AdaptiveColor,
    val accent: AdaptiveColor,
    val label: AdaptiveColor,
    val labelStrong: AdaptiveColor,
    val labelBackground: AdaptiveColor,
    val dotRing: AdaptiveColor,
    val currentRing: AdaptiveColor,
    /** The card's ink and surface, which Pip is drawn in; nil on a map with no card. */
    val card: Card?,
) {
    data class Card(val ink: AdaptiveColor, val surface: AdaptiveColor)

    companion object {
        /**
         * The full map: quiet greens, with the carrier's colour on the parcel. Pip beside it is a deeper green of the
         * land, whoever carries the parcel: a carrier's colour can clash with the map.
         */
        fun map(accent: AdaptiveColor): MapPalette {
            val ocean = Brand.color(light = "#F4F6F1", dark = "#171C17")
            return MapPalette(
                space = Brand.color(light = "#E9ECE5", dark = "#0E120E"),
                ocean = ocean,
                land = Brand.color(light = "#DFE4D8", dark = "#2D352B"),
                visited = accent.opacity(0.13),
                urban = Brand.color(light = "#D2D7CB", dark = "#3B4339"),
                road = Brand.color(light = "#BDC2B7", dark = "#51584F"),
                border = ocean,
                night = adaptive(light = rgb(24.0, 34.0, 40.0, 0.10), dark = rgb(0.0, 0.0, 0.0, 0.34)),
                grid = adaptive(light = rgb(32.0, 37.0, 30.0, 0.06), dark = rgb(245.0, 246.0, 242.0, 0.04)),
                limb = adaptive(light = rgb(32.0, 37.0, 30.0, 0.09), dark = rgb(245.0, 246.0, 242.0, 0.10)),
                shade = adaptive(light = rgb(32.0, 45.0, 32.0, 0.07), dark = rgb(0.0, 0.0, 0.0, 0.26)),
                route = Brand.ink,
                routeMuted = Brand.ink.opacity(0.38),
                accent = accent,
                label = Brand.color(light = "#657060", dark = "#AFB9A9"),
                labelStrong = Brand.ink,
                labelBackground = adaptive(light = rgb(255.0, 255.0, 255.0, 0.84), dark = rgb(32.0, 38.0, 31.0, 0.82)),
                dotRing = ocean,
                currentRing = Brand.paper,
                card = Card(Brand.color(light = "#55654B", dark = "#B7C6AA"), ocean),
            )
        }

        /** The card's engraving: the carrier's ink on the card's own colour. */
        fun tint(ink: AdaptiveColor, surface: AdaptiveColor): MapPalette = MapPalette(
            space = null, ocean = null, land = ink.opacity(0.10), visited = ink.opacity(0.17), urban = ink.opacity(0.07),
            road = ink.opacity(0.18), border = surface.opacity(0.8),
            night = null, grid = null, limb = ink.opacity(0.16), shade = null, route = ink, routeMuted = ink.opacity(0.45),
            accent = ink, label = ink, labelStrong = ink, labelBackground = surface.opacity(0.92), dotRing = surface,
            currentRing = Brand.paper, card = Card(ink, surface),
        )

        /**
         * The same engraving with the route in a pale ink and no bright ring, where it is part of a card's picture
         * rather than its subject.
         */
        fun quietTint(ink: AdaptiveColor, surface: AdaptiveColor): MapPalette {
            val route = surface.mix(ink, 0.38, GradientColorSpace.Device)
            return tint(ink, surface).copy(
                route = route, routeMuted = ink.opacity(0.24), accent = route, currentRing = surface, card = null,
            )
        }

        private fun rgb(red: Double, green: Double, blue: Double, alpha: Double): RgbaColor =
            RgbaColor(red / 255, green / 255, blue / 255, alpha)

        private fun adaptive(light: RgbaColor, dark: RgbaColor): AdaptiveColor = AdaptiveColor(light, dark)
    }
}

// MARK: - Layout

enum class MapLabels { All, Ends, None }

/** A font the map writes in, by its point size and weight: the system font, as `UIFont.systemFont(ofSize:weight:)`. */
data class MapFont(val size: Double, val weight: Weight) {
    enum class Weight { Regular, Medium, Semibold }
}

/**
 * How wide a line of text is in a font, unrounded: `(text as NSString).size(withAttributes: [.font: font]).width` on
 * the iPhone. Given the system font's size and weight, it returns the advance width of the text alone, on one line,
 * kerning included: the layout rounds it up and adds any tracking itself (`MapOverlay.Fonts.width`).
 *
 * Sizes and widths are in points, which are dp on Android. The iPhone's map names keep their size whatever the
 * reader's text size: it measures them with `UIFont.systemFont(ofSize:weight:)` and writes them with
 * `.system(size:weight:)`, neither of which follows Dynamic Type. So :app measures and writes them in dp, not sp.
 *
 * The layout keeps the widths it measured per measurer, font and text, so a measurer gives the same width each time,
 * and :app keeps one rather than making one for each layout.
 */
fun interface TextMeasurer {
    fun width(text: String, font: MapFont): Double
}

// MARK: - Drawing

/**
 * One step of drawing the map: what MapPainter draws with SwiftUI's GraphicsContext on the iPhone, as data for :app's
 * canvas to carry out in order. `opacity` is the context's, over the whole step, times the colour's own.
 */
sealed interface MapDrawing {
    val opacity: Double

    /**
     * Fills a path, by the even-odd rule or else the nonzero one. A cut takes out what is under it, whatever its colour
     * (`.destinationOut`), rather than painting.
     */
    class Fill(
        val path: MapPath,
        val color: AdaptiveColor,
        val evenOdd: Boolean = false,
        override val opacity: Double = 1.0,
        val cut: Boolean = false,
    ) : MapDrawing

    class Stroke(
        val path: MapPath,
        val color: AdaptiveColor,
        val style: StrokeStyle,
        override val opacity: Double = 1.0,
        val cut: Boolean = false,
    ) : MapDrawing

    /** Fills a path with a radial gradient around `center`: clear out to `startRadius`, `color` from `endRadius`. */
    class Shade(
        val path: MapPath,
        val color: AdaptiveColor,
        val center: Point,
        val startRadius: Double,
        val endRadius: Double,
        override val opacity: Double,
    ) : MapDrawing

    /** `drawLayer`: steps drawn in a layer of their own, then onto the map, so a cut there takes out only what they drew. */
    class Layer(val steps: List<MapDrawing>) : MapDrawing {
        override val opacity: Double get() = 1.0
    }

    /** A line of text, drawn at its size whatever the reader's (dp, not sp), the middle of its leading edge at `at`. */
    class Text(
        val text: String,
        val font: MapFont,
        val color: AdaptiveColor,
        val at: Point,
        val tracking: Double = 0.0,
        override val opacity: Double = 1.0,
    ) : MapDrawing
}

/**
 * SwiftUI's StrokeStyle: butt ends and mitred corners unless it says otherwise; a dash of nothing draws a solid line.
 * A mitre longer than `miterLimit` line widths is bevelled. The limit is CoreGraphics' 10, which no iPhone stroke
 * changes, where Android's Paint and Compose's Stroke default to 4: :app passes it on rather than keep its own.
 */
data class StrokeStyle(
    val lineWidth: Double,
    val lineCap: LineCap = LineCap.Butt,
    val lineJoin: LineJoin = LineJoin.Miter,
    val miterLimit: Double = 10.0,
    val dash: List<Double> = emptyList(),
) {
    enum class LineCap { Butt, Round, Square }
    enum class LineJoin { Miter, Round, Bevel }
}

object MapPainter {
    internal val black = AdaptiveColor(RgbaColor(0.0, 0.0, 0.0))

    /**
     * Space, ocean, land, visited countries, lakes, night, borders and the globe's shading.
     * Up close, `tiles` add what the wide map leaves out; `tiled` says that every tile of the view has come.
     */
    fun drawBase(
        size: Size,
        atlas: WorldAtlas,
        route: ParcelRoute,
        camera: GlobeCamera,
        palette: MapPalette,
        night: Instant?,
        tiles: List<DetailTile> = emptyList(),
        tiled: Boolean = false,
    ): List<MapDrawing> {
        val context = ArrayList<MapDrawing>()
        val projection = GlobeProjection(camera)
        val span = swiftMin(size.width, size.height) / camera.scale
        val detail = if (span < 0.45) WorldAtlas.Detail.Fine else WorldAtlas.Detail.Coarse
        val layer = atlas.layer(detail)
        val reach = reach(camera, size)
        // Only shapes whose cap reaches the view: a close-up draws a few countries, not all of them.
        val view = if (reach >= 1) PI / 2 else asin(reach) + 0.02
        val inView = { part: WorldAtlas.Part ->
            acos(swiftMax(-1.0, swiftMin(1.0, part.center.dot(projection.forward)))) - part.radius < view
        }
        // 0 in a close-up, 1 once the curve of the Earth shows.
        val globe = swiftMax(0.0, swiftMin(1.0, (span - 0.5) / 0.9))
        val bounds = Rect(0.0, 0.0, size.width, size.height)
        val disk = MapPath.ellipse(Rect(camera.offset.x - camera.scale, camera.offset.y - camera.scale, camera.scale * 2, camera.scale * 2))

        palette.space?.let { context.add(MapDrawing.Fill(MapPath.rect(bounds), it)) }
        palette.ocean?.let { context.add(MapDrawing.Fill(if (reach < 1) MapPath.rect(bounds) else disk, it)) }
        val grid = palette.grid
        if (globe > 0 && grid != null) {
            val lines = MapPath()
            for (line in MapGeometry.graticule) MapGeometry.addLine(lines, line, projection)
            context.add(MapDrawing.Stroke(lines, grid, StrokeStyle(0.6), opacity = globe))
        }
        val visited = swiftMax(0.0, swiftMin(1.0, (span * GeoPoint.EARTH_KILOMETRES - 800) / 1600))
        val land = ArrayList<MapDrawing>()
        val shapes = MapPath()
        for (part in layer.land) if (inView(part)) MapGeometry.addPart(shapes, part, projection, disk)
        land.add(MapDrawing.Fill(shapes, palette.land, evenOdd = true))
        // Countries the parcel passed through, once the view is wide enough to hold several.
        if (visited > 0) {
            val countries = MapPath()
            for (code in route.countries) {
                for (part in layer.byCode[code]?.parts.orEmpty()) if (inView(part)) MapGeometry.addPart(countries, part, projection, disk)
            }
            land.add(MapDrawing.Fill(countries, palette.visited, evenOdd = true, opacity = visited))
        }
        // Up close the tiles draw every lake, in finer lines than the wide map's.
        val tilesDrawLakes = tiled && (WorldDetailPack.strengths(span * GeoPoint.EARTH_KILOMETRES).firstOrNull() ?: 0.0) >= 1
        if (detail == WorldAtlas.Detail.Fine && !tilesDrawLakes) {
            val lakes = MapPath()
            for (part in atlas.lakes) if (inView(part)) MapGeometry.addPart(lakes, part, projection, disk)
            val ocean = palette.ocean
            land.add(
                if (ocean != null) MapDrawing.Fill(lakes, ocean, evenOdd = true) else MapDrawing.Fill(lakes, black, evenOdd = true, cut = true),
            )
        }
        context.add(MapDrawing.Layer(land))
        context.addAll(drawDetail(tiles, camera, size, palette))
        val shadow = palette.night
        if (night != null && globe > 0 && shadow != null) {
            // The night side darkens over 24° past the terminator, like dusk: twenty rings, each drawn
            // once at its own depth, since stacking twenty faint fills rounds them away.
            val sun = GlobeCamera.subsolarPoint(night)
            val antipode = GeoPoint(longitude = sun.longitude + 180, latitude = -sun.latitude).vector
            for (band in 0 until 20) {
                val outer = (92 - 1.2 * band.toDouble()) * PI / 180
                val ring = MapGeometry.cap(antipode, outer, projection, disk)
                if (band < 19) ring.addPath(MapGeometry.cap(antipode, outer - 1.2 * PI / 180, projection, disk))
                context.add(MapDrawing.Fill(ring, shadow, evenOdd = true, opacity = globe * (band + 1).toDouble() / 20))
            }
        }
        val borders = MapPath()
        for (part in layer.borders) {
            if (!inView(part)) continue
            for (line in part.rings) MapGeometry.addLine(borders, line, projection)
        }
        context.add(MapDrawing.Stroke(borders, palette.border, StrokeStyle(if (detail == WorldAtlas.Detail.Fine) 0.8 else 0.55)))
        val shade = palette.shade
        if (globe > 0 && shade != null) {
            // A soft falloff toward the rim makes the globe read as a sphere.
            context.add(MapDrawing.Shade(disk, shade, camera.offset, startRadius = camera.scale * 0.55, endRadius = camera.scale, opacity = globe))
        }
        val limb = palette.limb
        if (globe > 0 && limb != null) context.add(MapDrawing.Stroke(disk, limb, StrokeStyle(1.0), opacity = globe))
        return context
    }

    /** How far the view's farthest corner lies from the globe's center, in globe radii. */
    internal fun reach(camera: GlobeCamera, size: Size): Double {
        val corners = listOf(Point(0.0, 0.0), Point(size.width, 0.0), Point(0.0, size.height), Point(size.width, size.height))
        return corners.map { hypot(it.x - camera.offset.x, it.y - camera.offset.y) }.reduce { a, b -> swiftMax(a, b) } / camera.scale
    }

    /** A quiet route is a thin line, as part of the picture. */
    fun drawLegs(overlay: MapOverlay, palette: MapPalette, quiet: Boolean = false): List<MapDrawing> = overlay.legs.map { leg ->
        when (leg.kind) {
            MapOverlay.LegKind.Travelled, MapOverlay.LegKind.Approximate -> MapDrawing.Stroke(
                leg.path, palette.route,
                StrokeStyle(if (quiet) 0.9 else 1.8, StrokeStyle.LineCap.Round, StrokeStyle.LineJoin.Round),
            )
            MapOverlay.LegKind.Remaining -> MapDrawing.Stroke(
                leg.path, palette.routeMuted,
                StrokeStyle(if (quiet) 0.8 else 1.4, StrokeStyle.LineCap.Round, StrokeStyle.LineJoin.Round, dash = listOf(3.0, 5.0)),
            )
        }
    }

    /** How large a place is marked: the parcel's own place largest, a stop on the way smallest. A quiet route marks them all smaller. */
    fun dotRadius(kind: MapOverlay.DotKind, quiet: Boolean): Double {
        val radius = when (kind) {
            MapOverlay.DotKind.Current -> 5.0
            MapOverlay.DotKind.Origin -> 3.5
            MapOverlay.DotKind.Stop -> 2.6
            MapOverlay.DotKind.LastKnown, MapOverlay.DotKind.Area, MapOverlay.DotKind.Destination -> 4.5
        }
        return if (quiet) radius * 0.64 else radius
    }

    /**
     * Stops, place names and pointers. Faint names and pointers wait for the camera to land. `measurer` is the one the
     * overlay was laid out with: a pointer's detail follows its name.
     */
    fun drawMarks(
        overlay: MapOverlay,
        palette: MapPalette,
        moving: Boolean,
        quiet: Boolean = false,
        measurer: TextMeasurer,
    ): List<MapDrawing> {
        val context = ArrayList<MapDrawing>()
        val fonts = MapOverlay.Fonts
        for (dot in overlay.dots) {
            val radius = dotRadius(dot.kind, quiet)
            val circle = MapPath.ellipse(Rect(dot.point.x - radius, dot.point.y - radius, radius * 2, radius * 2))
            fun ring(color: AdaptiveColor, width: Double) = context.add(MapDrawing.Stroke(circle, color, StrokeStyle(width)))
            when (dot.kind) {
                MapOverlay.DotKind.Current -> {
                    context.add(MapDrawing.Fill(circle, palette.accent))
                    ring(palette.currentRing, if (quiet) 1.2 else 2.0)
                }
                MapOverlay.DotKind.LastKnown -> {
                    context.add(MapDrawing.Fill(circle, palette.currentRing))
                    ring(palette.accent, if (quiet) 1.2 else 2.0)
                }
                MapOverlay.DotKind.Destination -> {
                    context.add(MapDrawing.Fill(circle, palette.dotRing))
                    ring(palette.routeMuted, if (quiet) 1.1 else 1.5)
                }
                MapOverlay.DotKind.Area ->
                    context.add(MapDrawing.Stroke(circle, palette.route, StrokeStyle(if (quiet) 1.1 else 1.4, dash = listOf(1.6, 2.4))))
                MapOverlay.DotKind.Origin, MapOverlay.DotKind.Stop -> {
                    context.add(MapDrawing.Fill(circle, palette.route))
                    ring(palette.dotRing, if (quiet) 1.1 else 1.5)
                }
            }
        }
        for (label in overlay.labels) {
            if (moving && (label.kind == MapOverlay.LabelKind.Context || label.kind == MapOverlay.LabelKind.City)) continue
            val frame = label.frame
            val middle = frame.midY
            when (label.kind) {
                MapOverlay.LabelKind.Current, MapOverlay.LabelKind.End, MapOverlay.LabelKind.Stop -> {
                    context.add(MapDrawing.Fill(MapPath.roundedRect(frame, 7.0), palette.labelBackground))
                    val color = if (label.kind == MapOverlay.LabelKind.Current) palette.labelStrong else palette.label
                    context.add(MapDrawing.Text(label.text, fonts.label, color, Point(frame.minX + 7, middle)))
                }
                MapOverlay.LabelKind.Area ->
                    context.add(MapDrawing.Text(label.text, fonts.area, palette.label, Point(frame.minX + 5, middle), tracking = 0.84))
                MapOverlay.LabelKind.Context -> context.add(
                    MapDrawing.Text(label.text, fonts.context, palette.label.opacity(0.72), Point(frame.minX + 4, middle), tracking = 0.95),
                )
                MapOverlay.LabelKind.City -> {
                    context.add(MapDrawing.Fill(MapPath.ellipse(Rect(frame.minX + 1, frame.minY + 6, 4.0, 4.0)), palette.label.opacity(0.44)))
                    context.add(MapDrawing.Text(label.text, fonts.city, palette.label.opacity(0.8), Point(frame.minX + 9, middle)))
                }
            }
        }
        if (moving) return context
        for (pointer in overlay.pointers) {
            val frame = Rect(pointer.center.x - pointer.width / 2, pointer.center.y - 13, pointer.width, 26.0)
            context.add(MapDrawing.Fill(MapPath.roundedRect(frame, 13.0), palette.labelBackground))
            val transform = AffineTransform.translation(frame.minX + 13.5, frame.midY)
                .rotated(pointer.angle)
                .scaledBy(11.0 / 12, 11.0 / 12)
                .translatedBy(-6.0, -6.0)
            val arrow = MapPath()
            arrow.move(Point(2.0, 6.0).applying(transform))
            arrow.addLine(Point(10.0, 6.0).applying(transform))
            arrow.move(Point(7.0, 3.0).applying(transform))
            arrow.addLine(Point(10.0, 6.0).applying(transform))
            arrow.addLine(Point(7.0, 9.0).applying(transform))
            context.add(
                MapDrawing.Stroke(arrow, palette.label, StrokeStyle(1.6, StrokeStyle.LineCap.Round, StrokeStyle.LineJoin.Round)),
            )
            val nameX = frame.minX + 24
            context.add(MapDrawing.Text(pointer.name, fonts.pointerName, palette.labelStrong, Point(nameX, frame.midY)))
            val detailX = nameX + fonts.width(pointer.name, fonts.pointerName, measurer) + fonts.width(" ", fonts.pointerDetail, measurer)
            context.add(MapDrawing.Text(pointer.detail, fonts.pointerDetail, palette.label, Point(detailX, frame.midY)))
        }
        return context
    }
}

/**
 * Everything drawn over the land: legs, stops, place names, pointers to far ends, and where Pip stands.
 *
 * `towns` are the towns of the tiles in view, on a map that shows its close-ups in full. `covered` is a box the card
 * writes over the map, such as a second carrier's mark, and `buttons` the box of the buttons it puts over its top:
 * the names, Pip and the chips on the edge keep clear of both. `measurer` measures the names as the platform writes them.
 */
class MapOverlay(
    route: ParcelRoute,
    camera: GlobeCamera,
    size: Size,
    insets: EdgeInsets,
    labels: MapLabels,
    sites: Boolean = false,
    mode: ParcelRoute.Mode,
    context: Boolean,
    atlas: WorldAtlas,
    locale: Locale,
    countryName: (String) -> String,
    pip: PipRequest? = null,
    towns: List<DetailTile.Town>? = null,
    covered: Rect? = null,
    buttons: Rect? = null,
    measurer: TextMeasurer,
) {
    enum class LegKind { Travelled, Approximate, Remaining }
    enum class DotKind { Origin, Stop, Current, LastKnown, Area, Destination }
    enum class LabelKind { Current, End, Stop, Area, Context, City }

    class Leg(val path: MapPath, val kind: LegKind)
    data class Dot(val point: Point, val kind: DotKind)
    data class Label(val frame: Rect, val text: String, val kind: LabelKind)
    data class Pointer(val center: Point, val width: Double, val angle: Double, val name: String, val detail: String)

    /** A leg as it is drawn: a short hop's curve, and points along it. */
    class Track(val hop: Hop?, val points: List<Point>)
    data class Hop(val start: Point, val control: Point, val end: Point)

    val legs: List<Leg>
    val dots: List<Dot>
    val labels: List<Label>
    val pointers: List<Pointer>
    val pip: PipPlacement?

    /** How much of the journey the map shows: a point for each place it names, and half of one for Pip when he has a spot. */
    val shown: Double get() = labels.size.toDouble() + (if (pip == null) 0.0 else 0.5)

    object Fonts {
        val label = MapFont(11.5, MapFont.Weight.Medium)
        val area = MapFont(10.5, MapFont.Weight.Medium)
        val context = MapFont(9.5, MapFont.Weight.Medium)
        val city = MapFont(10.0, MapFont.Weight.Regular)
        val pointerName = MapFont(11.0, MapFont.Weight.Semibold)
        val pointerDetail = MapFont(11.0, MapFont.Weight.Regular)

        /**
         * The width rounded up, then the tracking `Text.tracking` adds after each Character. One framing lays the map
         * out dozens of times over the same names, so each measurer's widths are kept, a few thousand at most, for as
         * long as the measurer itself is.
         */
        fun width(text: String, font: MapFont, measurer: TextMeasurer, tracking: Double = 0.0): Double {
            val key = Measured(font, tracking, text)
            val widths = synchronized(measured) { measured.getOrPut(measurer, ::Widths) }
            synchronized(widths) { widths[key] }?.let { return it }
            // Untracked, the Characters do not count.
            val width = ceil(measurer.width(text, font)) + if (tracking == 0.0) 0.0 else tracking * text.characterCount()
            synchronized(widths) { widths[key] = width }
            return width
        }

        private data class Measured(val font: MapFont, val tracking: Double, val text: String)

        /** The widths a measurer measured last, the least recently used dropped first. */
        private class Widths : LinkedHashMap<Measured, Double>(KEPT, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Measured, Double>): Boolean = size > KEPT
        }

        private const val KEPT = 2_048
        private val measured = WeakHashMap<TextMeasurer, Widths>()
    }

    private class Candidate(val id: String, val place: RoutePlace, val kind: LabelKind, val priority: Int)

    private class Name(
        val frame: Rect,
        val text: String,
        val kind: LabelKind,
        val point: GeoPoint,
        val own: Boolean,
        val clean: Boolean,
        val free: Boolean,
    )

    private class Mark(val index: Int, val point: Point, val radius: Double)

    private class Bearing(val point: GeoPoint, val name: String, val reach: Double)

    init {
        val legs = mutableListOf<Leg>()
        val dots = mutableListOf<Dot>()
        val labelList = mutableListOf<Label>()
        val pointers = mutableListOf<Pointer>()
        var pipPlacement: PipPlacement? = null
        val request = pip

        fun measure(text: String, font: MapFont, tracking: Double = 0.0): Double = Fonts.width(text, font, measurer, tracking)

        val projection = GlobeProjection(camera)
        val visible = { point: GeoPoint -> projection.sees(point) }
        val at = { point: GeoPoint -> projection.project(point) }
        val inside = { point: Point, margin: Double ->
            point.x > insets.leading + margin && point.x < size.width - insets.trailing - margin &&
                point.y > insets.top + margin && point.y < size.height - insets.bottom - margin
        }
        val fits = { box: Rect, margin: Double ->
            inside(Point(box.minX, box.minY), margin) && inside(Point(box.maxX, box.maxY), margin) &&
                inside(Point(box.maxX, box.minY), margin) && inside(Point(box.minX, box.maxY), margin)
        }

        // A leg as it is drawn, and sampled along its curve so names and Pip can keep off it.
        val legPath = { a: GeoPoint, b: GeoPoint, kilometres: Double ->
            val path = MapPath()
            val drawn = track(a, b, kilometres, projection)
            val hop = drawn.hop
            if (hop != null) {
                // Short hops bow slightly: a hop, not a road.
                path.move(hop.start)
                path.addQuadCurve(hop.end, hop.control)
            } else {
                MapGeometry.addLine(path, MapGeometry.greatCircle(a, b), projection)
            }
            path to drawn.points
        }
        // Places too close to tell apart in this frame are one mark, drawn and named for all of them: the parcel's
        // place, else the first, else the newest. The route runs to that mark, and a hop between its places, which the
        // dot would hide, is left out. Closer up, each place has its own mark again.
        val kinds = route.stops.indices.map { dotKind(route, it) }
        val last = route.stops.size - 1
        val order = if (last < 0) emptyList() else listOf(last) + (if (last > 0) listOf(0) else emptyList()) +
            (if (last > 1) (1 until last).reversed().toList() else emptyList())
        val marks = mutableListOf<Mark>()
        val markOf = route.stops.indices.toMutableList()
        for (index in order) {
            if (!visible(route.stops[index].place.point)) continue
            val point = at(route.stops[index].place.point)
            val radius = MapPainter.dotRadius(kinds[index], quiet = false)
            val under = marks.firstOrNull { hypot(it.point.x - point.x, it.point.y - point.y) < it.radius + radius + MARK_AIR }
            if (under != null) markOf[index] = under.index else marks.add(Mark(index, point, radius))
        }
        val shows = { index: Int -> markOf[index] == index }

        val tracks = mutableListOf<List<Point>>()
        // Each leg runs from one stop to the next.
        for ((index, leg) in route.legs.withIndex()) {
            if (!(index + 1 < markOf.size && markOf[index] != markOf[index + 1])) continue
            val drawn = legPath(route.stops[markOf[index]].place.point, route.stops[markOf[index + 1]].place.point, leg.kilometres)
            legs.add(Leg(drawn.first, if (leg.isApproximate) LegKind.Approximate else LegKind.Travelled))
            tracks.add(drawn.second)
        }
        val currentStop = route.current
        val destinationPlace = route.destination
        if (currentStop != null && destinationPlace != null) {
            val drawn = legPath(currentStop.place.point, destinationPlace.point, route.remainingKilometres ?: 0.0)
            legs.add(0, Leg(drawn.first, LegKind.Remaining))
            tracks.add(drawn.second)
        }
        val routeTracks: List<List<Point>> = tracks
        /** Whether a leg passes through a box. */
        val onRoute = { box: Rect ->
            val reach = box.insetBy(-1.0, -1.0)
            routeTracks.any { track -> track.any { reach.contains(it) } }
        }

        for ((index, stop) in route.stops.withIndex()) {
            if (visible(stop.place.point) && shows(index)) dots.add(Dot(at(stop.place.point), kinds[index]))
        }
        if (destinationPlace != null && visible(destinationPlace.point)) {
            dots.add(0, Dot(at(destinationPlace.point), DotKind.Destination))
        }

        // Place names, most important first, skipping any that would collide.
        val placed = dots.map { Rect(it.point.x - 6, it.point.y - 6, 12.0, 12.0) }.toMutableList()
        // What the card writes or puts over the map is a mark like any other, with a little air around it: names and
        // Pip keep off it.
        val written = listOfNotNull(covered, buttons).map { it.insetBy(-3.0, -3.0) }
        placed.addAll(written)
        val overlaps = { box: Rect, taken: List<Rect> ->
            taken.any { box.minX < it.maxX && box.maxX > it.minX && box.minY < it.maxY && box.maxY > it.minY }
        }
        var candidates = mutableListOf<Candidate>()
        if (currentStop != null) candidates.add(Candidate(currentStop.id, currentStop.place, LabelKind.Current, 0))
        if (destinationPlace != null) candidates.add(Candidate("destination", destinationPlace, LabelKind.End, 1))
        val origin = route.origin
        if (origin != null && origin.id != currentStop?.id && shows(0)) {
            candidates.add(Candidate(origin.id, origin.place, LabelKind.End, 2))
        }
        if (route.stops.size > 2) {
            for (index in 1 until route.stops.size - 1) {
                if (!shows(index)) continue
                val stop = route.stops[index]
                candidates.add(Candidate(stop.id, stop.place, LabelKind.Stop, 3))
            }
        }
        candidates = candidates.filter { labels == MapLabels.All || (labels == MapLabels.Ends && it.priority < 3) }.toMutableList()
        val neighbours = HashMap<String, MutableList<GeoPoint>>()
        for ((index, stop) in route.stops.withIndex()) {
            neighbours[stop.id] = listOfNotNull(
                if (index > 0) route.stops[index - 1] else null,
                if (index + 1 < route.stops.size) route.stops[index + 1] else null,
            ).map { it.place.point }.toMutableList()
        }
        if (currentStop != null && destinationPlace != null) {
            neighbours.getOrPut(currentStop.id) { mutableListOf() }.add(destinationPlace.point)
            neighbours["destination"] = mutableListOf(currentStop.place.point)
        }

        /** Where a place's name goes among the marks already taken, and whether that spot is clear of all of them and of the route. */
        fun nameBox(candidate: Candidate, taken: List<Rect>): Name? {
            val spot = at(candidate.place.point)
            val area = candidate.place.isCountry
            val text = if (area) candidate.place.name.uppercase(locale) else candidate.place.name(sites)
            val width = if (area) measure(text, Fonts.area, tracking = 0.84) + 10 else measure(text, Fonts.label) + 14
            val height = 22.0
            var awayX = 0.0
            var awayY = 0.0
            for (other in neighbours[candidate.id] ?: emptyList()) {
                if (!visible(other)) continue
                val target = at(other)
                val distance = hypot(target.x - spot.x, target.y - spot.y)
                val length = if (distance > 0) distance else 1.0
                awayX += (target.x - spot.x) / length
                awayY += (target.y - spot.y) / length
            }
            class Side(val x: Double, val y: Double, val dx: Double, val dy: Double)
            val ranked = { sides: List<Side> ->
                sides.withIndex()
                    .map { (index, option) -> option to option.dx * awayX + option.dy * awayY + index.toDouble() * 0.01 }
                    .sortedWith { a, b -> if (a.second < b.second) -1 else if (b.second < a.second) 1 else 0 }
                    .map { Rect(it.first.x, it.first.y, width, height) }
            }
            // Beside, above or below the dot first; a town's name may also sit off one of its corners.
            val options = if (area) {
                ranked(
                    listOf(
                        Side(spot.x - width / 2, spot.y + 8, 0.0, 1.0), Side(spot.x - width / 2, spot.y - 30, 0.0, -1.0),
                        Side(spot.x + 8, spot.y + 6, 1.0, 1.0), Side(spot.x - width - 8, spot.y + 6, -1.0, 1.0),
                    ),
                )
            } else {
                ranked(
                    listOf(
                        Side(spot.x + 9, spot.y - height / 2, 1.0, 0.0), Side(spot.x - 9 - width, spot.y - height / 2, -1.0, 0.0),
                        Side(spot.x - width / 2, spot.y - 29, 0.0, -1.0), Side(spot.x - width / 2, spot.y + 8, 0.0, 1.0),
                    ),
                ) + ranked(
                    listOf(
                        Side(spot.x + 8, spot.y + 6, 1.0, 1.0), Side(spot.x - width - 8, spot.y + 6, -1.0, 1.0),
                        Side(spot.x + 8, spot.y - height - 6, 1.0, -1.0), Side(spot.x - width - 8, spot.y - height - 6, -1.0, -1.0),
                    ),
                )
            }
            val free = { box: Rect -> !overlaps(box, taken) }
            val inFrame = options.filter { fits(it, 2.0) }
            // The parcel's own place is always named: moved in from the frame's edge rather than cut off by it,
            // and over another mark if it must be.
            val moved = if (candidate.priority != 0) {
                emptyList()
            } else {
                options.withIndex().map { (index, box) ->
                    val x = swiftMax(insets.leading + 2, swiftMin(size.width - insets.trailing - 2 - box.width, box.minX))
                    val y = swiftMax(insets.top + 2, swiftMin(size.height - insets.bottom - 2 - box.height, box.minY))
                    Triple(Rect(x, y, box.width, box.height), abs(x - box.minX) + abs(y - box.minY), index)
                }
                    .sortedWith { a, b ->
                        if (a.second < b.second || (a.second == b.second && a.third < b.third)) {
                            -1
                        } else if (b.second < a.second || (b.second == a.second && b.third < a.third)) {
                            1
                        } else {
                            0
                        }
                    }
                    .map { it.first }
            }
            // A name keeps off the route when a side allows it.
            val clean = (inFrame + moved).firstOrNull { free(it) && !onRoute(it) }
            val choice = clean ?: inFrame.firstOrNull(free) ?: moved.firstOrNull(free) ?: moved.firstOrNull() ?: return null
            return Name(
                frame = choice, text = text, kind = if (area) LabelKind.Area else candidate.kind, point = candidate.place.point,
                own = candidate.priority == 0, clean = clean != null, free = free(choice),
            )
        }

        val sameTown = SAME_TOWN_KILOMETRES

        /** Every name that finds room among the marks already taken, most important first. */
        fun names(taken: List<Rect>): Pair<List<Name>, Set<String>> {
            val namesMarks = taken.toMutableList()
            val seen = HashSet<String>()
            val found = mutableListOf<Name>()
            for (candidate in candidates) {
                val point = candidate.place.point
                if (!visible(point) || candidate.place.id in seen || !inside(at(point), -2.0)) continue
                seen.add(candidate.place.id)
                // A town is named once, however many of its sites the parcel passed through.
                val name = nameBox(candidate, namesMarks) ?: continue
                if (found.any { it.text == name.text && it.point.kilometres(point) < sameTown }) continue
                namesMarks.add(name.frame)
                found.add(name)
            }
            return found to seen
        }

        // In a close-up, far ends of the journey stay on the edge, pointing the way.
        val viewCenter = Point((insets.leading + size.width - insets.trailing) / 2, (insets.top + size.height - insets.bottom) / 2)
        val middle = if (mode == ParcelRoute.Mode.Now && currentStop != null) projection.invert(viewCenter) else null
        if (currentStop != null && middle != null) {
            for (place in listOfNotNull(route.origin?.place, route.destination)) {
                if (visible(place.point) && inside(at(place.point), 12.0)) continue
                val toward = middle.interpolated(place.point, swiftMin(1.0, 0.01 / swiftMax(middle.angle(place.point), 1e-6)))
                val from = at(middle)
                val next = at(toward)
                val distance = hypot(next.x - from.x, next.y - from.y)
                val length = if (distance > 0) distance else 1.0
                val dx = (next.x - from.x) / length
                val dy = (next.y - from.y) / length
                val margin = 30.0
                val reach = swiftMin(
                    if (dx > 0) {
                        (size.width - insets.trailing - margin - viewCenter.x) / dx
                    } else if (dx < 0) {
                        (insets.leading + margin - viewCenter.x) / dx
                    } else {
                        Double.POSITIVE_INFINITY
                    },
                    if (dy > 0) {
                        (size.height - insets.bottom - margin - viewCenter.y) / dy
                    } else if (dy < 0) {
                        (insets.top + margin - viewCenter.y) / dy
                    } else {
                        Double.POSITIVE_INFINITY
                    },
                )
                val detail = ParcelRoute.formattedKilometres(currentStop.place.point.kilometres(place.point), locale)
                val name = place.name(sites)
                val width = measure(name, Fonts.pointerName) + measure(" $detail", Fonts.pointerDetail) + 34
                val half = width / 2
                // Keep the whole chip inside the frame, whichever edge it points past.
                var x = swiftMax(insets.leading + half + 8, swiftMin(size.width - insets.trailing - half - 8, viewCenter.x + dx * reach))
                var y = swiftMax(insets.top + 21, swiftMin(size.height - insets.bottom - 21, viewCenter.y + dy * reach))
                // A chip the card would write over stands beside it or just below it instead, whichever is nearer, but
                // never on the parcel's own dot: with nowhere else to go, it is left out, as the parcel matters more
                // than the way.
                val chip = { chipX: Double, chipY: Double -> Rect(chipX - half, chipY - 13, width, 26.0) }
                val dot = at(currentStop.place.point)
                val parcel = Rect(dot.x - 10, dot.y - 10, 20.0, 20.0)
                val box = written.firstOrNull { overlaps(chip(x, y), listOf(it)) }
                if (box != null) {
                    var nearest: Point? = null
                    for (option in listOf(Point(box.maxX + half + 4, y), Point(x, swiftMin(size.height - insets.bottom - 21, box.maxY + 13)))) {
                        if (!(option.x <= size.width - insets.trailing - half - 8 && !overlaps(chip(option.x, option.y), listOf(parcel)))) continue
                        val best = nearest
                        if (best == null || abs(option.x - x) + abs(option.y - y) < abs(best.x - x) + abs(best.y - y)) nearest = option
                    }
                    val chosen = nearest ?: continue
                    x = chosen.x
                    y = chosen.y
                }
                // A map too short to keep the chip off the middle would have it cover the dot, and Pip beside it: it is
                // left out.
                if (overlaps(chip(x, y), listOf(parcel.insetBy(7.0, 7.0)))) continue
                placed.add(Rect(x - half, y - 13, width, 26.0))
                pointers.add(Pointer(center = Point(x, y), width = width, angle = atan2(dy, dx), name = name, detail = detail))
            }
        }

        // Pip stands beside the parcel's dot, never on it or on its name: the first spot that leaves the other dots, the
        // route, the pointers and what the card writes over the map clear, and every name its place.
        if (request != null && currentStop != null && visible(currentStop.place.point) && inside(at(currentStop.place.point), -2.0)) {
            val dot = at(currentStop.place.point)
            val ceiling = request.ceiling ?: insets.top
            val floor = request.floor ?: (size.height - (if (request.inset) insets.bottom else 4.0))
            val west = if (request.inset) insets.leading + 4 else 4.0
            val east = size.width - (if (request.inset) insets.trailing + 4 else 4.0)
            val chips = placed.drop(dots.size)
            val named = names(placed).first.size
            val dotPoints = dots.map { it.point }
            var best: Pair<Int, PipPlacement>? = null
            // Every spot beside the dot at every size, before the one below it; or the one spot he already stands on.
            val trials = request.held?.let { listOf(it) }
                ?: (
                    request.mood.widths.flatMap { width -> PipGeometry.spots.map { width to it } } +
                        request.mood.widths.map { it to PipGeometry.spotBelow }
                    ).map { (width, spot) ->
                    val unit = width / PipGeometry.frame.width
                    PipSpot(
                        offset = Size(spot.x * width - PipGeometry.ground.x * unit, spot.y * width - PipGeometry.ground.y * unit),
                        width = width, side = if (spot.x > 0) -1 else if (spot.x < 0) 1 else 0, below = spot.y > 1,
                    )
                }
            for (spot in trials) {
                val width = spot.width
                val side = spot.side
                val unit = width / PipGeometry.frame.width
                val pipOrigin = Point(dot.x + spot.offset.width, dot.y + spot.offset.height)
                val extents = PipGeometry.extents(request.mood, side)
                val box = Rect(
                    pipOrigin.x + extents.minX * unit, pipOrigin.y + extents.minY * unit, extents.width * unit, extents.height * unit,
                )
                // Inside the map, below the card's top row, and never on what the card writes over the map, whatever the
                // other spots cost.
                if (!(box.minX >= west && box.maxX <= east && box.minY >= ceiling && box.maxY <= floor && !overlaps(box, written))) continue
                val place = PipPlacement(
                    origin = pipOrigin, width = width, mood = request.mood, side = side, below = spot.below, box = box, spot = spot,
                )
                if (request.held != null) {
                    best = 0 to place
                    break
                }
                // How far a point on the map is from Pip himself: the corners of his box are empty.
                val outlines = PipGeometry.outlines(request.mood, side)
                val away = { point: Point ->
                    val local = Point((point.x - pipOrigin.x) / unit, (point.y - pipOrigin.y) / unit)
                    (outlines.map { PipGeometry.distance(local, it) }.swiftMin() ?: Double.POSITIVE_INFINITY) * unit
                }
                if (!(away(dot) >= 10)) continue
                val beside = names(placed + box).first
                val own = beside.firstOrNull { it.own }
                if (own != null && !own.free) continue
                val cost = dotPoints.count { away(it) < 6 } +
                    routeTracks.count { track -> track.any { away(it) < 1.5 } } +
                    chips.count { overlaps(box, listOf(it)) } +
                    (own?.let { if (it.clean) 0 else 1 } ?: 0) + max(0, named - beside.size)
                if (best?.let { cost < it.first } != false) best = cost to place
                if (cost == 0) break
            }
            if (best != null) {
                pipPlacement = best.second
                placed.add(best.second.box)
            }
        }

        val (found, seen) = names(placed)
        placed.addAll(found.map { it.frame })
        labelList.addAll(found.map { Label(it.frame, it.text, it.kind) })

        // Faint country names for orientation, fewer as the view widens.
        val spanKilometres = swiftMin(size.width, size.height) / camera.scale * GeoPoint.EARTH_KILOMETRES
        val maxRank = when {
            !context || labels == MapLabels.None || spanKilometres < 180 || spanKilometres > 10_000 -> 0
            spanKilometres > 6_000 -> 2
            spanKilometres > 3_000 -> 3
            spanKilometres > 1_200 -> 4
            else -> 5
        }
        for (country in if (maxRank > 0) atlas.coarse.countries else emptyList()) {
            // Names near the rim of the globe read as clutter.
            val label = country.label
            if (country.rank > maxRank || label == null || country.code in seen || !(label.angle(camera.center) <= 1.15)) continue
            val spot = at(label)
            val text = countryName(country.code).uppercase(locale)
            val width = measure(text, Fonts.context, tracking = 0.95) + 8
            val box = Rect(spot.x - width / 2, spot.y - 9, width, 18.0)
            if (overlaps(box, placed) || !fits(box, 6.0)) continue
            placed.add(box)
            labelList.add(Label(box, text, LabelKind.Context))
        }

        // In a close-up, a few big cities give bearings. Closer still there is room for more names, and smaller towns
        // take it.
        val maxCityRank = when {
            !context || labels == MapLabels.None || spanKilometres > 1_600 -> -1
            spanKilometres > 800 -> 3
            spanKilometres > 400 -> 6
            else -> 7
        }
        // A map that shows its close-ups in full names as many places as it has room for, and ever smaller towns.
        val room = (size.width - insets.leading - insets.trailing) * (size.height - insets.top - insets.bottom) / 15_000
        val most = if (towns != null && spanKilometres < WorldDetailPack.CLOSE_UP_KILOMETRES) max(7, min(24, room.rounded().toInt())) else 7
        val smallest = if (spanKilometres > 420) Int.MAX_VALUE else if (spanKilometres > 260) 50 else if (spanKilometres > 130) 25 else 0
        // A place the parcel passed through has its name already: a city's stands for the whole of it, a town's for its
        // own centre.
        val ends = route.stops.map { it.place } + listOfNotNull(route.destination)
        val namedAlready = { point: GeoPoint, name: String, reach: Double ->
            ends.any { place ->
                val distance = place.point.kilometres(point)
                distance < reach || (distance < sameTown && (place.name == name || place.site == name))
            }
        }
        var bearings = emptyList<Bearing>()
        if (maxCityRank >= 0) {
            bearings = atlas.cities.filter { it.rank <= maxCityRank }.map { Bearing(it.point, it.name, if (spanKilometres > 150) 12.0 else 3.0) } +
                (towns ?: emptyList()).filter { it.thousands >= smallest }.map { Bearing(it.point, it.name, 2.0) }
        }
        var shownCities = 0
        for (city in bearings) {
            if (shownCities >= most) break
            if (!visible(city.point)) continue
            val spot = at(city.point)
            // Off the map, which is most of them.
            if (!inside(spot, 4.0) || namedAlready(city.point, city.name, city.reach)) continue
            val box = Rect(spot.x - 3, spot.y - 8, measure(city.name, Fonts.city) + 12, 16.0)
            if (overlaps(box, placed) || !fits(box, 4.0)) continue
            placed.add(box)
            labelList.add(Label(box, city.name, LabelKind.City))
            shownCities += 1
        }

        this.legs = legs
        this.dots = dots
        this.labels = labelList
        this.pointers = pointers
        this.pip = pipPlacement
    }

    companion object {
        /** A town is named once, however many of its sites the parcel passed through. */
        private const val SAME_TOWN_KILOMETRES = 30.0

        /** The room two dots need between their rings to read as two places: closer, they are one mark. */
        private const val MARK_AIR = 3.0

        /** How a stop is marked: the parcel's place, or its last known one, the first place, a place on the way, or a country. */
        private fun dotKind(route: ParcelRoute, index: Int): DotKind {
            val area = route.stops[index].place.isCountry
            if (index == route.stops.size - 1) return if (route.latestLocated) DotKind.Current else if (area) DotKind.Area else DotKind.LastKnown
            return if (area) DotKind.Area else if (index == 0) DotKind.Origin else DotKind.Stop
        }

        /** A leg as it is drawn, sampled along its curve: a short hop bows slightly to the left of travel, a long one follows the globe. */
        fun track(from: GeoPoint, to: GeoPoint, kilometres: Double, projection: GlobeProjection): Track {
            val a = from
            val b = to
            if (!(kilometres < 900 && projection.sees(a) && projection.sees(b))) {
                return Track(null, (0..120).map { a.interpolated(b, it.toDouble() / 120) }.filter(projection::sees).map(projection::project))
            }
            val start = projection.project(a)
            val end = projection.project(b)
            val distance = hypot(end.x - start.x, end.y - start.y)
            val length = if (distance > 0) distance else 1.0
            val bend = swiftMin(length * 0.18, 70.0)
            val control = Point(
                (start.x + end.x) / 2 + (end.y - start.y) / length * bend,
                (start.y + end.y) / 2 - (end.x - start.x) / length * bend,
            )
            val steps = max(8, ceil(length / 3).toInt())
            return Track(
                Hop(start, control, end),
                (0..steps).map { step ->
                    val t = step.toDouble() / steps.toDouble()
                    Point(
                        (1 - t) * (1 - t) * start.x + 2 * (1 - t) * t * control.x + t * t * end.x,
                        (1 - t) * (1 - t) * start.y + 2 * (1 - t) * t * control.y + t * t * end.y,
                    )
                },
            )
        }
    }
}
