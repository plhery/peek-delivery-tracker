package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import java.nio.ByteBuffer
import java.util.Locale
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import kotlin.math.acos
import kotlin.math.hypot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

private fun city(name: String, country: String, longitude: Double, latitude: Double): RoutePlace =
    RoutePlace(id = name, name = name, country = country, point = GeoPoint(longitude, latitude), isCountry = false)

private fun town(name: String, longitude: Double, latitude: Double, thousands: Int): DetailTile.Town =
    DetailTile.Town(point = GeoPoint(longitude, latitude), name = name, thousands = thousands)

private val zurich = city("Zürich", "CH", 8.55, 47.37)
private val wide = Size(800.0, 600.0)
private val small = Size(400.0, 300.0)
private val towns = listOf(town("Winterthur", 8.72, 47.5, 112), town("Zug", 8.52, 47.17, 31), town("Rapperswil", 8.82, 47.23, 27), town("Baden", 8.31, 47.47, 19))

/** A view `kilometres` across its shorter side, around Zürich unless told otherwise. */
private fun close(kilometres: Double, size: Size, around: GeoPoint = zurich.point): GlobeCamera =
    GlobeCamera(
        center = around, scale = minOf(size.width, size.height) / (kilometres / GeoPoint.EARTH_KILOMETRES),
        offset = Point(size.width / 2, size.height / 2),
    )

/** WorldDetailTests.swift: the close-ups' tiles, the towns they name, and the store that reads them. */
class WorldDetailTests {
    companion object {
        private val atlas: WorldAtlas by lazy { WorldAtlas(SharedResources.open("World.json").use { it.readBytes() }) }

        private fun pack(): WorldDetailPack = WorldDetailPack(SharedResources.open("WorldDetail.pack").use { it.readBytes() })
    }

    /** The towns a close-up names for bearings. */
    private fun bearings(route: ParcelRoute, kilometres: Double, given: List<DetailTile.Town>?, size: Size = wide): List<String> =
        MapOverlay(
            route = route, camera = close(kilometres, size), size = size, insets = EdgeInsets(), labels = MapLabels.All, sites = true,
            mode = ParcelRoute.Mode.Now, context = true, atlas = atlas, locale = Locale.forLanguageTag("en"), countryName = { it },
            towns = given, measurer = RecordedTextMeasurer.recorded,
        ).labels.filter { it.kind == MapOverlay.LabelKind.City }.map { it.text }

    @Test
    fun theBundledPackHoldsTheTilesOfACloseUp() {
        val pack = pack()
        assertEquals(5.0, pack.degrees, 0.0)
        assertTrue(pack.keys.size > 800)
        // 300 km across, around Zürich, reaches into the tile to the east; half as wide, around Bern, one tile holds it.
        val around = GeoPoint(8.5, 47.4)
        assertEquals(300.0, WorldDetailPack.spanKilometres(close(300.0, small), small), 1e-6)
        assertEquals(listOf("37_27", "38_27"), pack.keys(close(300.0, small, around), small))
        assertEquals(listOf("37_27"), pack.keys(close(150.0, small, GeoPoint(7.5, 47.0)), small))
        // A wide view asks for none, and the open ocean has none.
        assertEquals(emptyList<String>(), pack.keys(close(800.0, small, around), small))
        assertEquals(emptyList<String>(), pack.keys(close(300.0, small, GeoPoint(-150.0, -40.0)), small))
        // At the date line the view reaches both ends of the world.
        assertEquals(listOf("0_15", "71_14"), pack.keys(close(500.0, small, GeoPoint(179.9, -17.0)), small))

        val tile = checkNotNull(pack.tile("37_27"))
        assertTrue(tile.towns.any { it.name == "Winterthur" })
        assertTrue(tile.towns.any { it.name == "Konstanz" })
        // The largest towns come first, and the cities the wide map names are left to it.
        assertEquals(tile.towns.map { it.thousands }.sortedDescending(), tile.towns.map { it.thousands })
        assertFalse(tile.towns.any { it.name == "Zürich" })
        for (layer in listOf(tile.rivers, tile.lakes, tile.urban, tile.roads)) {
            assertTrue(layer.size > 10)
            assertEquals(layer.map { it.level }.sorted(), layer.map { it.level })
            for (shape in layer) {
                assertEquals(shape.points.size, shape.cut.size)
                // The cap around a shape reaches every one of its points.
                assertTrue(shape.points.all { acos(swiftMax(-1.0, swiftMin(1.0, shape.center.dot(it)))) <= shape.radius + 1e-9 })
            }
        }
        assertNull(pack.tile("nowhere"))
        assertThrows(IllegalArgumentException::class.java) { WorldDetailPack("no first line".toByteArray()) }
    }

    /** :app maps the pack as an uncompressed asset, as the iPhone maps it: the tiles read from a buffer are the same. */
    @Test
    fun aMappedPackReadsTheSameTiles() {
        val bytes = SharedResources.open("WorldDetail.pack").use { it.readBytes() }
        // A buffer off the heap, holding the pack after some other bytes, as a mapped file's region may.
        val buffer = ByteBuffer.allocateDirect(bytes.size + 7)
        buffer.put(ByteArray(7) { 0x0A }).put(bytes).flip()
        buffer.position(7)
        val mapped = WorldDetailPack(buffer)
        val read = pack()
        assertEquals(read.keys, mapped.keys)
        assertEquals(7, buffer.position())
        for (key in listOf("37_27", "0_15", "71_14")) {
            val a = checkNotNull(read.tile(key))
            val b = checkNotNull(mapped.tile(key))
            assertEquals(a.towns, b.towns)
            assertEquals(a.rivers.flatMap { it.points }, b.rivers.flatMap { it.points })
        }
    }

    /** As JSONDecoder reads them: a quoted number, a fraction where an Int goes or a number where a name goes is no pack or tile. */
    @Test
    fun aPackOrTileWithNumbersWrittenWronglyIsNotRead() {
        fun pack(header: String) = WorldDetailPack("$header\n".toByteArray())
        assertEquals(setOf("1_2"), pack("""{"degrees":5,"precision":1e3,"tiles":{"1_2":[0,1.0]}}""").keys)
        for (header in listOf(
            """{"degrees":"5","precision":1000,"tiles":{}}""",
            """{"degrees":5,"precision":1000,"tiles":{"1_2":["0",1]}}""",
            """{"degrees":5,"precision":1000,"tiles":{"1_2":[0,1.5]}}""",
            """{"degrees":5,"precision":1000,"tiles":{"1_2":[00,1]}}""",
            """{"degrees":5,"precision":1e400,"tiles":{}}""",
        )) {
            assertThrows(header, Exception::class.java) { pack(header) }
        }
        fun tile(json: String) = DetailTile(json.toByteArray(), precision = 1_000.0, degrees = 5.0)
        val town = """"towns":[[8724,47506,"Winterthur",112]]"""
        assertEquals(1, tile("""{"rivers":[[1,8540,47370]],"lakes":[],"urban":[],"roads":[],$town}""").rivers.size)
        for (json in listOf(
            """{"rivers":[[1,"8540",47370]],"lakes":[],"urban":[],"roads":[],$town}""",
            """{"rivers":[],"lakes":[],"urban":[],"roads":[],"towns":[[8724,47506,1,112]]}""",
            """{"rivers":[],"lakes":[],"urban":[],"roads":[],"towns":[[8724,47506,"Winterthur",+112]]}""",
        )) {
            assertThrows(json, Exception::class.java) { tile(json) }
        }
    }

    @Test
    fun aTileIsReadAsLevelsStepsAndTowns() {
        // The Limmat, more or less: a river in three points, the last on the edge of its tile.
        val json = """{"rivers":[[1,8540,47370,-140,40,-1600,2590]],"lakes":[],"urban":[],"roads":[[0,8300,47400,400,20],[2,8500,47300,60,60]],"towns":[[8724,47506,"Winterthur",112]]}"""
            .toByteArray()
        val tile = DetailTile(json, precision = 1_000.0, degrees = 5.0)
        val river = checkNotNull(tile.rivers.firstOrNull())
        assertEquals(1, river.level)
        assertEquals(3, river.points.size)
        assertEquals(8.54, GeoPoint(river.points[0]).longitude, 1e-6)
        assertEquals(47.37, GeoPoint(river.points[0]).latitude, 1e-6)
        assertEquals(6.8, GeoPoint(river.points[2]).longitude, 1e-6)
        assertEquals(50.0, GeoPoint(river.points[2]).latitude, 1e-6)
        // Only the point on the tile's edge is one the river was cut at.
        assertEquals(listOf(false, false, true), river.cut)
        assertTrue(river.radius > 0.02)
        assertTrue(river.radius < 0.06)
        assertEquals(listOf(0, 2), tile.roads.map { it.level })
        assertEquals(listOf(town("Winterthur", 8.724, 47.506, 112)), tile.towns)
    }

    @Test
    fun eachLevelComesInAsTheViewClosesIn() {
        assertEquals(listOf(0.0, 0.0, 0.0), WorldDetailPack.strengths(700.0))
        assertEquals(listOf(0.5, 0.0, 0.0), WorldDetailPack.strengths(500.0))
        assertEquals(listOf(1.0, 0.5, 0.0), WorldDetailPack.strengths(360.0))
        assertEquals(listOf(1.0, 1.0, 0.5), WorldDetailPack.strengths(200.0))
        assertEquals(listOf(1.0, 1.0, 1.0), WorldDetailPack.strengths(60.0))
    }

    @Test
    fun aCloseUpNamesSmallerTownsTheCloserTheView() {
        val route = ParcelRoute(listOf(zurich))
        assertFalse(bearings(route, 500.0, towns).contains("Winterthur"))
        assertTrue(bearings(route, 300.0, towns).contains("Winterthur"))
        assertFalse(bearings(route, 300.0, towns).contains("Zug"))
        assertTrue(bearings(route, 200.0, towns).toSet().containsAll(listOf("Winterthur", "Zug", "Rapperswil")))
        assertFalse(bearings(route, 200.0, towns).contains("Baden"))
        assertTrue(bearings(route, 100.0, towns).toSet().containsAll(listOf("Winterthur", "Zug", "Rapperswil", "Baden")))
        // A map that was given no towns names the large cities, a few of them, as before.
        assertEquals(listOf("Luzern"), bearings(route, 100.0, null))
        assertTrue(bearings(route, 300.0, null).size <= 7)
        assertTrue(bearings(route, 300.0, emptyList()).size > 7)
    }

    @Test
    fun aPlaceTheParcelPassedThroughKeepsItsOwnName() {
        val mulligen = RoutePlace(
            EventPlace(latitude = 47.39, longitude = 8.49, precision = EventPlacePrecision.City, country = "CH", name = "Zürich", site = "Zürich-Mülligen"),
        )
        val route = ParcelRoute(listOf(city("Regensdorf", "CH", 8.47, 47.43), mulligen))
        val given = towns + listOf(
            town("Regensdorf", 8.468, 47.434, 18), town("Zürich", 8.55, 47.37, 400), town("Zürich-Mülligen", 8.6, 47.4, 20),
            town("Adliswil", 8.52, 47.31, 19),
        )
        val names = bearings(route, 60.0, given)
        // The town of a stop, by its place or by either of its names; a town of its own a few kilometres on is named.
        assertFalse(names.contains("Regensdorf"))
        assertFalse(names.contains("Zürich"))
        assertFalse(names.contains("Zürich-Mülligen"))
        assertTrue(names.contains("Adliswil"))
    }

    @Test
    fun aCloseUpNamesAsManyPlacesAsItHasRoomFor() {
        val route = ParcelRoute(listOf(zurich))
        val many = (0 until 60).map { town("Town $it", 8.05 + (it % 10).toDouble() * 0.1, 47.05 + (it / 10).toDouble() * 0.11, 60 - it) }
        val few = bearings(route, 100.0, many, small)
        val more = bearings(route, 120.0, many, Size(1_200.0, 900.0))
        // 400 × 300 has room for eight names; a screen nine times the size stops at twenty-four.
        assertTrue(few.size > 4)
        assertTrue(few.size <= 8)
        assertEquals(24, more.size)
        // The largest towns are the first to be named.
        assertTrue(few.contains("Town 0"))
    }

    @Test
    fun pipStandsClearOfWhatCoversTheMapAndKeepsHisSpotWhileItIsMoved() {
        val route = ParcelRoute(listOf(city("Hamburg", "DE", 9.99, 53.55), zurich))
        val insets = EdgeInsets(top = 40.0, leading = 130.0, bottom = 120.0, trailing = 10.0)
        fun show(camera: GlobeCamera, request: PipRequest): MapOverlay = MapOverlay(
            route = route, camera = camera, size = small, insets = insets, labels = MapLabels.All, sites = true, mode = ParcelRoute.Mode.Now,
            context = true, atlas = atlas, locale = Locale.forLanguageTag("en"), countryName = { it }, pip = request,
            measurer = RecordedTextMeasurer.recorded,
        )
        fun dot(overlay: MapOverlay): Point = checkNotNull(overlay.dots.firstOrNull { it.kind == MapOverlay.DotKind.Current }).point
        val view = close(300.0, small).copy(offset = Point(280.0, 110.0))
        val first = show(view, PipRequest(PipMood.Look, inset = true))
        val pip = checkNotNull(first.pip)
        assertEquals(pip.spot.offset.width, pip.origin.x - dot(first).x, 1e-9)
        assertEquals(pip.spot.offset.height, pip.origin.y - dot(first).y, 1e-9)
        // Inside the room the summary and the buttons leave.
        assertTrue(pip.box.minX >= insets.leading + 4)
        assertTrue(pip.box.maxX <= small.width - insets.trailing - 4)
        assertTrue(pip.box.minY >= insets.top)
        assertTrue(pip.box.maxY <= small.height - insets.bottom)
        // Moved a little, he moves with the dot, wherever the best spot would now be.
        val moved = view.copy(offset = Point(300.0, 120.0))
        val held = show(moved, PipRequest(PipMood.Look, inset = true, held = pip.spot))
        val kept = checkNotNull(held.pip)
        assertEquals(pip.spot, kept.spot)
        assertEquals(dot(held).x + pip.spot.offset.width, kept.origin.x, 1e-9)
        assertEquals(dot(held).y + pip.spot.offset.height, kept.origin.y, 1e-9)
        // Moved until he would stand past the room there is, he steps out, where a Pip who had just come would pick the other side.
        val edge = view.copy(offset = Point(if (pip.box.midX >= dot(first).x) 388.0 else 132.0, view.offset.y))
        assertNull(show(edge, PipRequest(PipMood.Look, inset = true, held = pip.spot)).pip)
        assertNotNull(show(edge, PipRequest(PipMood.Look, inset = true)).pip)
    }

    /**
     * The iPhone test counts the pixels the tiles change; the canvas is :app's, so this one sums what the tiles' steps
     * cover: each stroke's length in the view times its width, and each fill's area, sampled every 4 points.
     */
    @Test
    fun upCloseTheTilesAreDrawnOverTheLand() {
        val pack = pack()
        val route = ParcelRoute(listOf(zurich))
        val frame = Rect(0.0, 0.0, small.width, small.height)
        fun steps(kilometres: Double, tiles: List<DetailTile>, palette: MapPalette = MapPalette.map(AdaptiveColor(RgbaColor(1.0, 0.0, 0.0)))) =
            MapPainter.drawBase(small, atlas, route, close(kilometres, small), palette, night = null, tiles = tiles, tiled = tiles.isNotEmpty())
        // What the tiles add: the steps after the land's layer and before the borders.
        fun detail(steps: List<MapDrawing>): List<MapDrawing> {
            assertTrue(steps.last() is MapDrawing.Stroke)
            return steps.subList(steps.indexOfFirst { it is MapDrawing.Layer } + 1, steps.size - 1)
        }
        fun covered(step: MapDrawing): Double = when (step) {
            is MapDrawing.Stroke -> length(lines(step.path), frame) * step.style.lineWidth
            is MapDrawing.Fill -> area(lines(step.path), frame)
            else -> 0.0
        }
        val tiles = pack.keys(close(200.0, small), small).mapNotNull { pack.tile(it) }
        assertFalse(tiles.isEmpty())
        // Rivers, roads and built-up areas take a fair share of a close-up, and more of a closer one.
        for (kilometres in listOf(200.0, 100.0)) {
            assertTrue(detail(steps(kilometres, emptyList())).isEmpty())
            val drawn = detail(steps(kilometres, tiles))
            assertTrue("$kilometres", drawn.sumOf { covered(it) } > 2_000)
        }
        // A wide view is drawn as it always was.
        val wideWith = steps(700.0, tiles)
        val wideWithout = steps(700.0, emptyList())
        assertTrue(detail(wideWith).isEmpty())
        assertEquals(wideWithout.map { it::class to it.opacity }, wideWith.map { it::class to it.opacity })
        // On a see-through map, water is cut out of the land rather than painted over it.
        val ink = AdaptiveColor(RgbaColor(0.2, 0.1, 0.4))
        val white = AdaptiveColor(RgbaColor(1.0, 1.0, 1.0))
        val cut = detail(steps(200.0, tiles, MapPalette.tint(ink, surface = white))).filter {
            (it as? MapDrawing.Fill)?.cut ?: (it as? MapDrawing.Stroke)?.cut ?: false
        }
        assertTrue(cut.sumOf { covered(it) } > 500)
    }

    /** A path's lines, each subpath closed or not, a curve taken as eight straight steps. */
    private fun lines(path: MapPath): List<List<Point>> {
        val lines = ArrayList<MutableList<Point>>()
        path.replay(object : MapPath.Sink {
            override fun move(x: Double, y: Double) {
                lines.add(mutableListOf(Point(x, y)))
            }

            override fun line(x: Double, y: Double) {
                lines.last().add(Point(x, y))
            }

            override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) {
                val from = lines.last().last()
                for (index in 1..8) {
                    val t = index / 8.0
                    val u = 1 - t
                    lines.last().add(Point(u * u * from.x + 2 * u * t * controlX + t * t * x, u * u * from.y + 2 * u * t * controlY + t * t * y))
                }
            }

            override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) =
                throw AssertionError("The tiles draw no arcs")

            override fun ellipse(x: Double, y: Double, width: Double, height: Double) = throw AssertionError("The tiles draw no ellipses")

            override fun roundedRect(x: Double, y: Double, width: Double, height: Double, cornerRadius: Double) =
                throw AssertionError("The tiles draw no rectangles")

            override fun close() {
                lines.last().add(lines.last().first())
            }
        })
        return lines
    }

    /** How long lines are inside `frame`, each step counted where its middle lies. */
    private fun length(lines: List<List<Point>>, frame: Rect): Double = lines.sumOf { line ->
        line.zipWithNext().sumOf { (a, b) -> if (frame.contains(Point((a.x + b.x) / 2, (a.y + b.y) / 2))) hypot(b.x - a.x, b.y - a.y) else 0.0 }
    }

    /** How much of `frame` the lines fill by the even-odd rule, sampled every 4 points. */
    private fun area(lines: List<List<Point>>, frame: Rect): Double {
        var cells = 0
        for (row in 0 until (frame.height / 4).toInt()) {
            val y = frame.minY + row * 4.0 + 2
            val crossings = ArrayList<Double>()
            for (line in lines) {
                for (index in line.indices) {
                    val a = line[index]
                    val b = line[(index + 1) % line.size]
                    if ((a.y > y) != (b.y > y)) crossings.add(a.x + (y - a.y) / (b.y - a.y) * (b.x - a.x))
                }
            }
            for (column in 0 until (frame.width / 4).toInt()) {
                val x = frame.minX + column * 4.0 + 2
                if (crossings.count { it > x } % 2 == 1) cells++
            }
        }
        return cells * 16.0
    }

    /** Waits for what the store reads off the main thread, up to five seconds. */
    private suspend fun eventually(met: () -> Boolean) {
        for (attempt in 0 until 500) {
            if (met()) break
            delay(10)
        }
        assertTrue(met())
    }

    private suspend fun readsItsTilesOnceAndKeepsTheLastOnesShown(scope: CoroutineScope) {
        val store = WorldDetailStore(scope)
        // Before the pack is open there is nothing to read from.
        store.need(listOf("37_27"))
        assertNull(store.pack)
        store.open()
        val pack = checkNotNull(store.pack)
        store.need(listOf("37_27", "nowhere"))
        store.need(listOf("37_27"))
        eventually { store.tiles["37_27"] != null }
        assertEquals(listOf("37_27"), store.tiles.keys.sorted())
        val keys = pack.keys.sorted().filter { it != "37_27" }.take(20)
        for (key in keys) {
            store.need(listOf(key))
            eventually { store.tiles[key] != null }
            // The first tile is shown again and again, so it is never the oldest.
            store.need(listOf(keys[0]))
        }
        assertEquals(16, store.tiles.size)
        assertNotNull(store.tiles[keys[0]])
        assertNull(store.tiles["37_27"])
        assertNull(store.tiles[keys[1]])
        assertNotNull(store.tiles[keys[19]])
    }

    @Test
    fun anOpenedMapReadsItsTilesOnceAndKeepsTheLastOnesShown() = runBlocking {
        readsItsTilesOnceAndKeepsTheLastOnesShown(this)
    }

    /** The iPhone's store lives on the main actor; this one keeps the same tiles whatever threads its scope runs on. */
    @Test
    fun onAnyThreadTheStoreKeepsTheSameTiles() = runBlocking {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        try {
            readsItsTilesOnceAndKeepsTheLastOnesShown(scope)
        } finally {
            scope.cancel()
        }
    }

    @Test
    fun tilesReadAtTheSameTimeAreAllKept() = runBlocking {
        val pack = pack()
        val keys = pack.keys.sorted().take(16)
        repeat(6) { round ->
            val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
            val store = WorldDetailStore(scope, bundled = { pack })
            store.open()
            if (round % 2 == 0) {
                // Asked for together, read on several threads, and finished at about the same time.
                store.need(keys)
            } else {
                // Asked for from several threads at once.
                keys.map { key -> scope.launch { store.need(listOf(key)) } }.joinAll()
            }
            eventually { store.tiles.size == keys.size }
            assertEquals(keys, store.tiles.keys.sorted())
            scope.cancel()
        }
    }

    @Test
    fun aReadCancelledBeforeItStartsCanBeAskedForAgain() = runBlocking {
        val pack = pack()
        val dispatcher = StandardTestDispatcher()
        val scope = CoroutineScope(SupervisorJob() + dispatcher)
        val store = WorldDetailStore(scope, reading = dispatcher, bundled = { pack })
        store.open()
        store.need(listOf("37_27"))
        // The read is cancelled before it has started, as when what asked for it goes away.
        scope.coroutineContext.job.cancelChildren()
        dispatcher.scheduler.advanceUntilIdle()
        assertNull(store.tiles["37_27"])
        store.need(listOf("37_27"))
        dispatcher.scheduler.advanceUntilIdle()
        assertNotNull(store.tiles["37_27"])
        scope.cancel()
    }

    @Test
    fun aReadCancelledWhileItWaitsCanBeAskedForAgain() = runBlocking {
        val pack = pack()
        val executor = Executors.newSingleThreadExecutor()
        val gate = CountDownLatch(1)
        // The reading thread is busy, so the read waits for it.
        executor.execute { gate.await() }
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        try {
            val store = WorldDetailStore(scope, reading = executor.asCoroutineDispatcher(), bundled = { pack })
            store.open()
            store.need(listOf("37_27"))
            scope.coroutineContext.job.cancelChildren()
            gate.countDown()
            eventually {
                store.need(listOf("37_27"))
                store.tiles["37_27"] != null
            }
        } finally {
            scope.cancel()
            executor.shutdown()
        }
    }
}
