package com.plhery.peekdeliverytracker.core

import java.io.ByteArrayOutputStream
import java.nio.Buffer
import java.nio.ByteBuffer
import java.util.zip.Inflater
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.floor
import kotlin.math.pow
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
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

// From WorldDetail.swift, the tiles a close-up shows, the store that reads them, and how MapPainter draws them.

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
            val layer = { name: String -> file.getValue(name).jsonArray.map { shape(it.jsonArray.map(JsonElement::decodedInt)) } }
            rivers = layer("rivers")
            lakes = layer("lakes")
            urban = layer("urban")
            roads = layer("roads")
            towns = file.getValue("towns").jsonArray.map { element ->
                val values = element.jsonArray
                Town(
                    point = GeoPoint(
                        longitude = values[0].decodedInt().toDouble() / precision,
                        latitude = values[1].decodedInt().toDouble() / precision,
                    ),
                    name = values[2].decodedString(),
                    thousands = values[3].decodedInt(),
                )
            }
        }
    }
}

/**
 * The tiles as they ship with the app, in `WorldDetail.pack`: a first line that says where each tile starts,
 * then the tiles the web map reads one by one, each one compressed. A close-up reads the few it shows, so
 * showing it asks no map service anything. `data` may map the file, as the iPhone does: a tile's bytes are copied
 * out only when it is read, and tiles may be read on several threads at once.
 */
class WorldDetailPack(data: ByteBuffer) {
    constructor(data: ByteArray) : this(ByteBuffer.wrap(data))

    /** The pack from the buffer's position to its limit, with a position of its own that is never moved. */
    private val data: ByteBuffer = data.asReadOnlyBuffer()

    val degrees: Double
    val precision: Double

    /** The tiles there are: the open sea has none. */
    val keys: Set<String>
    private val start: Int
    private val index: Map<String, List<Long>>

    init {
        val first = this.data.position()
        val end = (first until this.data.limit()).firstOrNull { this.data.get(it) == 0x0A.toByte() }
            ?: throw IllegalArgumentException("The pack has no header line")
        val header = Json.parseToJsonElement(bytes(first, end - first).decodeToString()).jsonObject
        degrees = header.getValue("degrees").decodedDouble()
        precision = header.getValue("precision").decodedDouble()
        index = header.getValue("tiles").jsonObject.mapValues { (_, place) -> place.jsonArray.map(JsonElement::decodedLong) }
        keys = index.keys
        start = end + 1
    }

    /** One tile, or nil where there is none. It throws for a tile that cannot be read. */
    fun tile(key: String): DetailTile? {
        val place = index[key] ?: return null
        if (!(place.size == 2 && place[0] >= 0 && place[1] > 0 && start + place[0] + place[1] <= data.limit())) return null
        val json = inflate(bytes((start + place[0]).toInt(), place[1].toInt()))
        return DetailTile(json = json, precision = precision, degrees = degrees)
    }

    /** `count` bytes of the pack from `offset`, read through a view of their own so threads never share a position. */
    private fun bytes(offset: Int, count: Int): ByteArray {
        val view = data.duplicate()
        // Through Buffer, where Android declares it: built with JDK 9 or later, this would call ByteBuffer's override,
        // which older Android versions lack.
        (view as Buffer).position(offset)
        return ByteArray(count).also { view.get(it) }
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
            try {
                WorldDetailPack(SharedResources.open("WorldDetail.pack").use { it.readBytes() })
            } catch (_: Exception) {
                null
            }
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
        private fun inflate(data: ByteArray): ByteArray {
            val inflater = Inflater(true)
            try {
                inflater.setInput(data)
                val output = ByteArrayOutputStream(data.size * 4)
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

// MARK: - Drawing

/**
 * Draws the tiles over the land: built-up areas, then roads, then water. A shape the view cannot hold is
 * skipped whole. The shapes are drawn for a far wider view than the closest one, so their corners are
 * rounded: a river bends, it does not turn.
 */
fun MapPainter.drawDetail(tiles: List<DetailTile>, camera: GlobeCamera, size: Size, palette: MapPalette): List<MapDrawing> {
    val kilometres = WorldDetailPack.spanKilometres(camera, size)
    val strengths = WorldDetailPack.strengths(kilometres)
    if (tiles.isEmpty() || !(strengths[0] > 0)) return emptyList()
    val context = ArrayList<MapDrawing>()
    val projection = GlobeProjection(camera)
    val view = asin(swiftMin(1.0, reach(camera, size))) + 0.01
    val points = TracedPoints()

    /** Every shape of one level of a layer that the view holds, as one path. */
    fun path(layer: (DetailTile) -> List<DetailTile.Shape>, level: Int, closed: Boolean): MapPath {
        val path = MapPath()
        for (tile in tiles) {
            for (shape in layer(tile)) {
                // Shapes come sorted by level, and one too small to see is not worth its points.
                if (shape.level > level) break
                if (shape.level != level || (closed && shape.radius * camera.scale < 0.6) ||
                    !(acos(swiftMax(-1.0, swiftMin(1.0, shape.center.dot(projection.forward)))) - shape.radius < view)
                ) {
                    continue
                }
                trace(path, shape, projection, closed, points)
            }
        }
        return path
    }

    /** One path for each level of a layer, as strong as the level shows. */
    fun layer(layer: (DetailTile) -> List<DetailTile.Shape>, closed: Boolean, paint: (MapPath, Int, Double) -> MapDrawing) {
        for (level in strengths.indices) {
            if (!(strengths[level] > 0)) continue
            val shapes = path(layer, level, closed)
            if (shapes.isEmpty) continue
            context.add(paint(shapes, level, strengths[level]))
        }
    }
    // Lines grow a little as the map closes in, like the map itself.
    val zoom = swiftMax(1.0, swiftMin(1.8, (260 / kilometres).pow(0.4)))
    val line = { width: Double -> StrokeStyle(width * zoom, StrokeStyle.LineCap.Butt, StrokeStyle.LineJoin.Bevel) }
    layer(DetailTile::urban, closed = true) { shapes, _, strength -> MapDrawing.Fill(shapes, palette.urban, opacity = strength) }
    layer(DetailTile::roads, closed = false) { shapes, level, strength ->
        MapDrawing.Stroke(shapes, palette.road, line(listOf(1.15, 0.85, 0.65)[level]), opacity = strength)
    }
    // On a see-through map, water is cut out of the land.
    val cut = palette.ocean == null
    val colour = palette.ocean ?: black
    layer(DetailTile::rivers, closed = false) { shapes, level, strength ->
        MapDrawing.Stroke(shapes, colour, line(listOf(1.4, 1.05, 0.8)[level]), opacity = strength, cut = cut)
    }
    layer(DetailTile::lakes, closed = true) { shapes, _, strength -> MapDrawing.Fill(shapes, colour, opacity = strength, cut = cut) }
    return context
}

/** The points of a shape on screen, and whether each is a corner of the tile's cut: kept from one shape to the next. */
private class TracedPoints {
    var x = DoubleArray(64)
    var y = DoubleArray(64)
    var sharp = BooleanArray(64)
    var count = 0

    fun add(x: Double, y: Double, sharp: Boolean) {
        if (count == this.x.size) {
            this.x = this.x.copyOf(count * 2)
            this.y = this.y.copyOf(count * 2)
            this.sharp = this.sharp.copyOf(count * 2)
        }
        this.x[count] = x
        this.y[count] = y
        this.sharp[count] = sharp
        count++
    }
}

/** One shape, each corner rounded between the middles of the two sides that meet there. */
private fun trace(path: MapPath, shape: DetailTile.Shape, projection: GlobeProjection, closed: Boolean, points: TracedPoints) {
    points.count = 0
    val last = shape.points.size - 1
    // A point that falls on the one before it adds nothing to the line: a wide view keeps one point in three.
    for (index in shape.points.indices) {
        val vector = shape.points[index]
        val x = projection.screenX(vector)
        val y = projection.screenY(vector)
        val cut = shape.cut[index]
        val previous = points.count - 1
        if (previous >= 0 && !cut && index < last && abs(x - points.x[previous]) + abs(y - points.y[previous]) < 1.6) continue
        points.add(x, y, cut)
    }
    val count = points.count
    if (count < (if (closed) 3 else 2)) return
    val xs = points.x
    val ys = points.y
    // The middle of each side, where one corner's curve hands over to the next.
    if (closed) path.move((xs[count - 1] + xs[0]) / 2, (ys[count - 1] + ys[0]) / 2) else path.move(xs[0], ys[0])
    // An open line keeps its two ends as they are.
    var index = if (closed) 0 else 1
    while (index < (if (closed) count else count - 1)) {
        val following = (index + 1) % count
        val nextX = (xs[index] + xs[following]) / 2
        val nextY = (ys[index] + ys[following]) / 2
        if (points.sharp[index]) {
            path.addLine(xs[index], ys[index])
            path.addLine(nextX, nextY)
        } else {
            path.addQuadCurve(nextX, nextY, xs[index], ys[index])
        }
        index += 1
    }
    if (closed) path.closeSubpath() else path.addLine(xs[count - 1], ys[count - 1])
}

// MARK: - JSON

// JSONDecoder's rules for the numbers and strings of the atlas and the tiles, stricter than kotlinx's tree, which takes
// any unquoted word as a literal and reads a quoted number as one: a number is unquoted and written as JSON writes one,
// an Int has nothing left after its point, and a string is quoted.

internal fun JsonElement.decodedLong(): Long {
    val text = number()
    text.toLongOrNull()?.let { return it }
    // 1.0 and 1e2 are whole numbers too.
    val value = text.toDouble()
    if (value == floor(value) && value >= Long.MIN_VALUE.toDouble() && value < -Long.MIN_VALUE.toDouble()) return value.toLong()
    throw SerializationException("Expected an Int, found $text")
}

/** An Int as the 32-bit index and coordinates here hold it, where Swift's Int has 64 bits. */
internal fun JsonElement.decodedInt(): Int {
    val value = decodedLong()
    if (value < Int.MIN_VALUE || value > Int.MAX_VALUE) throw SerializationException("Expected a 32-bit Int, found $value")
    return value.toInt()
}

internal fun JsonElement.decodedDouble(): Double =
    number().toDouble().takeIf { it.isFinite() } ?: throw SerializationException("Expected a Double, found $this")

internal fun JsonElement.decodedString(): String {
    val primitive = this as? JsonPrimitive
    if (primitive == null || !primitive.isString) throw SerializationException("Expected a String, found $this")
    return primitive.content
}

private fun JsonElement.number(): String {
    val primitive = this as? JsonPrimitive
    if (primitive == null || primitive.isString || !isJsonNumber(primitive.content)) {
        throw SerializationException("Expected a number, found $this")
    }
    return primitive.content
}

/** JSON's grammar for a number: an optional minus, no leading zero, then optional fraction and exponent digits. */
private fun isJsonNumber(text: String): Boolean {
    var index = 0
    fun digits(): Boolean {
        val from = index
        while (index < text.length && text[index] in '0'..'9') index++
        return index > from
    }
    if (index < text.length && text[index] == '-') index++
    if (index < text.length && text[index] == '0') index++ else if (!digits()) return false
    if (index < text.length && text[index] == '.') {
        index++
        if (!digits()) return false
    }
    if (index < text.length && (text[index] == 'e' || text[index] == 'E')) {
        index++
        if (index < text.length && (text[index] == '+' || text[index] == '-')) index++
        if (!digits()) return false
    }
    return index == text.length
}
