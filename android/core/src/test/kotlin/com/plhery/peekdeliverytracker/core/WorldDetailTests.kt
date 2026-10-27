package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import java.util.Locale
import kotlin.math.acos
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
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

        @JvmStatic
        @BeforeClass
        fun installIcu() = TestIcu.install()
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

    /** The pixels wait for :app's MapPainter; the tiles a 200 km close-up is drawn with are checked here. */
    @Test
    fun upCloseTheTilesAreDrawnOverTheLand() {
        val pack = pack()
        val tiles = pack.keys(close(200.0, small), small).mapNotNull { pack.tile(it) }
        assertFalse(tiles.isEmpty())
    }

    @Test
    fun anOpenedMapReadsItsTilesOnceAndKeepsTheLastOnesShown() = runBlocking {
        val store = WorldDetailStore(this)
        suspend fun eventually(met: () -> Boolean) {
            for (attempt in 0 until 500) {
                if (met()) break
                delay(10)
            }
            assertTrue(met())
        }
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
}
