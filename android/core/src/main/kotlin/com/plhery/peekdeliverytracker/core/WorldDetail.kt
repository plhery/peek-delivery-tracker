package com.plhery.peekdeliverytracker.core

import java.io.ByteArrayOutputStream
import java.util.zip.Inflater
import kotlin.math.acos
import kotlin.math.floor
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.double
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

// From WorldDetail.swift, the tiles a close-up shows and the store that reads them. MapPainter's drawing of them is
// :app's.

// MARK: - Tiles

/**
 * What the map shows up close: rivers, lakes, built-up areas, main roads and towns, cut into tiles of a few
 * degrees. It follows the same rules as the web map (`src/components/map/detail.ts`).
 */
class DetailTile(
    val rivers: List<Shape>,
    val lakes: List<Shape>,
    val urban: List<Shape>,
    val roads: List<Shape>,
    /** The largest first. */
    val towns: List<Town>,
) {
    /** A line or an outline as unit vectors, with the smallest cap around it. */
    class Shape(
        /** 0 for what shows first as the map closes in, 2 for what shows last. */
        val level: Int,
        val points: List<Vector3>,
        /** The points where the shape was cut at its tile's edge: its outline keeps its corner there, to meet the rest of the shape. */
        val cut: List<Boolean>,
        val center: Vector3,
        /** In radians. */
        val radius: Double,
    )

    data class Town(
        val point: GeoPoint,
        val name: String,
        /** Its inhabitants, in thousands. */
        val thousands: Int,
    )

    /** A tile as its file holds it: each shape a level, then its points as steps from one to the next. */
    constructor(json: ByteArray, precision: Double, degrees: Double) : this(Parsed(json, precision, degrees))

    private constructor(parsed: Parsed) : this(parsed.rivers, parsed.lakes, parsed.urban, parsed.roads, parsed.towns)

    private class Parsed(json: ByteArray, precision: Double, degrees: Double) {
        val rivers: List<Shape>
        val lakes: List<Shape>
        val urban: List<Shape>
        val roads: List<Shape>
        val towns: List<Town>

        init {
            val file = Json.parseToJsonElement(json.decodeToString()).jsonObject
            val edge = (degrees * precision).toInt()
            val shape = { flat: List<Int> ->
                val points = ArrayList<Vector3>(flat.size / 2)
                val cut = ArrayList<Boolean>(flat.size / 2)
                var longitude = 0
                var latitude = 0
                var index = 1
                while (index + 1 < flat.size) {
                    longitude += flat[index]
                    latitude += flat[index + 1]
                    points.add(GeoPoint(longitude = longitude.toDouble() / precision, latitude = latitude.toDouble() / precision).vector)
                    cut.add(longitude % edge == 0 || latitude % edge == 0)
                    index += 2
                }
                val sum = points.fold(Vector3.Zero) { sum, point -> sum + point }
                val length = sum.length
                val center = if (length > 1e-9) sum / length else (points.firstOrNull() ?: Vector3(1.0, 0.0, 0.0))
                val radius = points.fold(0.0) { radius, point -> swiftMax(radius, acos(swiftMax(-1.0, swiftMin(1.0, center.dot(point))))) }
                Shape(level = flat.firstOrNull() ?: 0, points = points, cut = cut, center = center, radius = radius)
            }
            val layer = { name: String -> file.getValue(name).jsonArray.map { shape(ints(it)) } }
            rivers = layer("rivers")
            lakes = layer("lakes")
            urban = layer("urban")
            roads = layer("roads")
            towns = file.getValue("towns").jsonArray.map { element ->
                val values = element.jsonArray
                Town(
                    point = GeoPoint(
                        longitude = values[0].jsonPrimitive.int.toDouble() / precision,
                        latitude = values[1].jsonPrimitive.int.toDouble() / precision,
                    ),
                    name = values[2].jsonPrimitive.content,
                    thousands = values[3].jsonPrimitive.int,
                )
            }
        }
    }

    private companion object {
        fun ints(element: JsonElement): List<Int> = element.jsonArray.map { it.jsonPrimitive.int }
    }
}

/**
 * The tiles as they ship with the app, in `WorldDetail.pack`: a first line that says where each tile starts,
 * then the tiles the web map reads one by one, each one compressed. A close-up reads the few it shows, so
 * showing it asks no map service anything.
 */
class WorldDetailPack(private val data: ByteArray) {
    val degrees: Double
    val precision: Double

    /** The tiles there are: the open sea has none. */
    val keys: Set<String>
    private val start: Int
    private val index: Map<String, List<Int>>

    init {
        val end = data.indexOf(0x0A.toByte())
        if (end < 0) throw IllegalArgumentException("The pack has no header line")
        val header = Json.parseToJsonElement(data.decodeToString(0, end)).jsonObject
        degrees = header.getValue("degrees").jsonPrimitive.double
        precision = header.getValue("precision").jsonPrimitive.double
        index = header.getValue("tiles").jsonObject.mapValues { (_, place) -> place.jsonArray.map { it.jsonPrimitive.int } }
        keys = index.keys
        start = end + 1
    }

    /** One tile, or nil where there is none. It throws for a tile that cannot be read. */
    fun tile(key: String): DetailTile? {
        val place = index[key] ?: return null
        if (!(place.size == 2 && place[0] >= 0 && place[1] > 0 && start.toLong() + place[0] + place[1] <= data.size)) return null
        val json = inflate(data, start + place[0], place[1])
        return DetailTile(json = json, precision = precision, degrees = degrees)
    }

    /** The tiles a close-up shows; none while the view is too wide for them. */
    fun keys(camera: GlobeCamera, size: Size): List<String> {
        if (!(spanKilometres(camera, size) < CLOSE_UP_KILOMETRES)) return emptyList()
        val projection = GlobeProjection(camera)
        val found = HashSet<String>()
        // The view is far smaller than a tile, so a few points across it find every tile it touches.
        for (column in 0..6) {
            for (row in 0..8) {
                val point = projection.invert(Point(size.width * column.toDouble() / 6, size.height * row.toDouble() / 8)) ?: continue
                val x = minOf((360 / degrees).toInt() - 1, floor((point.longitude + 180) / degrees).toInt())
                val y = minOf((180 / degrees).toInt() - 1, floor((point.latitude + 90) / degrees).toInt())
                val key = "${x}_$y"
                if (key in keys) found.add(key)
            }
        }
        return found.sorted()
    }

    companion object {
        /** How wide a view may be, across its shorter side, to count as a close-up: under it the opened map shows what is in the tiles. */
        const val CLOSE_UP_KILOMETRES = 560.0

        /** Where each level starts to show, in kilometres across the view, and where it is fully there. */
        private val levels: List<Pair<Double, Double>> = listOf(CLOSE_UP_KILOMETRES to 440.0, 400.0 to 320.0, 230.0 to 170.0)

        private val loaded: WorldDetailPack? by lazy {
            runCatching { WorldDetailPack(SharedResources.open("WorldDetail.pack").use { it.readBytes() }) }.getOrNull()
        }

        /** Read once, off the main thread, when an opened map first closes in. */
        suspend fun bundled(): WorldDetailPack? = withContext(Dispatchers.IO) { loaded }

        /** How wide the view is across its shorter side, in kilometres. */
        fun spanKilometres(camera: GlobeCamera, size: Size): Double =
            swiftMin(size.width, size.height) / camera.scale * GeoPoint.EARTH_KILOMETRES

        /** How strongly each level shows in a view this wide: 0 not at all, 1 fully. */
        fun strengths(kilometres: Double): List<Double> =
            levels.map { (from, full) -> swiftMax(0.0, swiftMin(1.0, (from - kilometres) / (from - full))) }

        /** `NSData.decompressed(using: .zlib)`, which reads raw DEFLATE data with no zlib header. */
        private fun inflate(data: ByteArray, offset: Int, length: Int): ByteArray {
            val inflater = Inflater(true)
            try {
                inflater.setInput(data, offset, length)
                val output = ByteArrayOutputStream(length * 4)
                val buffer = ByteArray(16_384)
                while (!inflater.finished()) {
                    val count = inflater.inflate(buffer)
                    if (count == 0 && (inflater.needsInput() || inflater.needsDictionary())) {
                        throw IllegalArgumentException("The tile is cut short")
                    }
                    output.write(buffer, 0, count)
                }
                return output.toByteArray()
            } finally {
                inflater.end()
            }
        }
    }
}

/**
 * The tiles one opened map has read so far. The iPhone's store is a main-actor object; this one guards its state
 * itself, so `need` may be called from any thread and `scope` may run on any dispatcher. Tiles are read on `reading`.
 */
class WorldDetailStore(
    private val scope: CoroutineScope,
    private val reading: CoroutineDispatcher = Dispatchers.Default,
    private val bundled: suspend () -> WorldDetailPack? = { WorldDetailPack.bundled() },
) {
    private val tilesState = MutableStateFlow<Map<String, DetailTile>>(emptyMap())
    private val packState = MutableStateFlow<WorldDetailPack?>(null)

    val tilesFlow: StateFlow<Map<String, DetailTile>> = tilesState.asStateFlow()

    /** Nil until the pack's first line has been read. */
    val packFlow: StateFlow<WorldDetailPack?> = packState.asStateFlow()

    val tiles: Map<String, DetailTile> get() = tilesState.value
    val pack: WorldDetailPack? get() = packState.value

    /** Guards `asked`, `shown` and every change to the tiles, as the main actor does on the iPhone. */
    private val lock = Any()

    /** The tiles being read, each with a ticket of its own read, so a read that ends late never clears a newer one. */
    private val asked = HashMap<String, Any>()

    /** The tiles in the order they were last shown, the oldest first. */
    private var shown: List<String> = emptyList()

    suspend fun open() {
        if (packState.value == null) packState.compareAndSet(null, bundled())
    }

    /** Reads the tiles that are not read yet, off the main thread. */
    fun need(keys: List<String>) {
        val pack = packState.value ?: return
        val reads = ArrayList<Pair<String, Any>>()
        synchronized(lock) {
            shown = shown.filter { it !in keys } + keys
            for (key in keys) {
                if (tilesState.value[key] != null || key in asked) continue
                val ticket = Any()
                asked[key] = ticket
                reads.add(key to ticket)
            }
        }
        for ((key, ticket) in reads) {
            val job = scope.launch {
                val tile = withContext(reading) { read(pack, key) }
                synchronized(lock) {
                    asked.remove(key, ticket)
                    if (tile == null) return@launch
                    tilesState.update { current ->
                        val tiles = LinkedHashMap(current)
                        tiles[key] = tile
                        for (oldest in shown) {
                            if (tiles.size > KEPT && oldest !in keys) tiles.remove(oldest)
                        }
                        tiles
                    }
                }
            }
            // A read cancelled with its scope may be asked for again, even one that never started and so never ran a
            // `finally`.
            job.invokeOnCompletion { synchronized(lock) { asked.remove(key, ticket) } }
        }
    }

    private companion object {
        /** Tiles kept once read: a few journeys' worth. */
        const val KEPT = 16

        /** `try? pack.tile(key)`: a tile that cannot be read is none, but cancellation still cancels. */
        fun read(pack: WorldDetailPack, key: String): DetailTile? = try {
            pack.tile(key)
        } catch (cancelled: CancellationException) {
            throw cancelled
        } catch (_: Exception) {
            null
        }
    }
}

/** What an opened map knows of its close-ups: the tiles read so far, and how to ask for more. */
data class MapDetail(
    val pack: WorldDetailPack,
    val tiles: Map<String, DetailTile>,
    val need: (List<String>) -> Unit,
)
