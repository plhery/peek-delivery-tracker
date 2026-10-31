package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.pinch
import androidx.compose.ui.test.swipe
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.core.DetailTile
import com.plhery.peekdeliverytracker.core.EdgeInsets
import com.plhery.peekdeliverytracker.core.GeoPoint
import com.plhery.peekdeliverytracker.core.GlobeCamera
import com.plhery.peekdeliverytracker.core.MapDetail
import com.plhery.peekdeliverytracker.core.MapLabels
import com.plhery.peekdeliverytracker.core.MapOverlay
import com.plhery.peekdeliverytracker.core.MapPalette
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.core.Size
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.core.WorldDetailPack
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.draw.MapTextWidths
import com.plhery.peekdeliverytracker.ui.map.LocalMapCameraWatch
import com.plhery.peekdeliverytracker.ui.map.LocalMapSceneWatch
import com.plhery.peekdeliverytracker.ui.map.LocalMapsAtOnce
import com.plhery.peekdeliverytracker.ui.map.MapScene
import com.plhery.peekdeliverytracker.ui.map.MapSceneRequest
import com.plhery.peekdeliverytracker.ui.map.MapScenes
import com.plhery.peekdeliverytracker.ui.map.SceneDetail
import com.plhery.peekdeliverytracker.ui.map.WorldAtlases
import com.plhery.peekdeliverytracker.ui.map.WorldDetailPacks
import com.plhery.peekdeliverytracker.ui.map.WorldDetails
import com.plhery.peekdeliverytracker.ui.map.WorldMapView
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The opened map up close (A-S84): it reads the tiles its camera shows and keeps the last sixteen, draws their rivers,
 * lakes, built-up areas and roads, each level as strong as the view is close, names their towns, draws again as each
 * tile comes, and is the only map that does: the page's pass and the list's cards draw none.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelMapDetailTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val context: Context get() = ApplicationProvider.getApplicationContext()
    private val atlas by lazy { runBlocking { WorldAtlases.load().await() }!! }
    private val pack by lazy { runBlocking { WorldDetailPacks.load(context).await() }!! }

    /** Each camera the opened map was composed with. */
    private val opened = mutableListOf<GlobeCamera>()

    /** Each picture a map showed, and whether it was the opened map's. */
    private val scenes = mutableListOf<Pair<Boolean, MapScene>>()

    private val lastOpened: MapScene get() = scenes.last { it.first }.second

    private fun show() {
        val source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            CompositionLocalProvider(
                LocalMapCameraWatch provides { interactive, _, camera, _ -> if (interactive) opened += camera },
                LocalMapSceneWatch provides { interactive, scene -> scenes += interactive to scene },
            ) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false)
            }
        }
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()
    }

    /** The demo parcel named `name`, its page opened from the list. */
    private fun openPage(name: String) {
        show()
        compose.openCard(DemoCards.parcel(name).label)
        settle()
    }

    /** Its map opened from its page's globe, and at rest. */
    private fun openMap(name: String) {
        openPage(name)
        compose.onNodeWithTag(PAGE_GLOBE).performClick()
        settle()
    }

    private val map get() = compose.onNodeWithTag(MAP_COVER_MAP)

    /** The opened map's size, in points. */
    private fun size(): Size {
        val density = compose.density.density.toDouble()
        val shown = map.fetchSemanticsNode().size
        return Size(shown.width / density, shown.height / density)
    }

    private fun kilometres(camera: GlobeCamera = opened.last()): Double = WorldDetailPack.spanKilometres(camera, size())

    /** One finger across the opened map by `by` pixels, from high on it, clear of the summary. */
    private fun drag(by: Offset) {
        map.performTouchInput {
            val from = Offset(width * if (by.x < 0) 0.85f else 0.15f, height * 0.3f)
            swipe(from, from + by, durationMillis = 300)
        }
        settle()
    }

    /**
     * Two fingers zooming the opened map `by` times, in pinches of eight times at most, about the parcel's dot, which
     * stays where it is, or high in the middle of the map where the dot is out of view.
     */
    private fun pinch(by: Double) {
        var left = by
        while (left > 1.0001 || left < 0.9999) {
            val step = left.coerceIn(1 / 8.0, 8.0)
            left /= step
            val dot = lastOpened.current
            val density = compose.density.density
            map.performTouchInput {
                val spot = dot?.let { Offset(it.x.toFloat() * density, it.y.toFloat() * density) }
                val middle = spot?.takeIf { it.x in 250f..width - 250f && it.y in 250f..height * 0.6f } ?: Offset(centerX, height * 0.3f)
                val from = if (step >= 1) 60f else 480f
                val to = from * step.toFloat()
                pinch(middle - Offset(from / 2, 0f), middle - Offset(to / 2, 0f), middle + Offset(from / 2, 0f), middle + Offset(to / 2, 0f), 300)
            }
            settle()
        }
    }

    /**
     * How many pixels of `pixels` are `color` exactly. The built-up areas' colour is the land's a little toward the
     * ink, so the soft edges of the route and of the names have a few pixels of it too.
     */
    private fun count(pixels: IntArray, color: Int): Int = pixels.count { it == color }

    @Test
    fun theOpenedMapReadsTheTilesItsCameraShowsAndKeepsTheLastSixteen() {
        openMap("Belgian chocolate")
        val store = WorldDetails.open.single()
        val first = opened.last()
        assertTrue("${kilometres(first)} km", kilometres(first) < WorldDetailPack.CLOSE_UP_KILOMETRES)
        val keys = pack.keys(first, size())
        assertTrue(keys.isNotEmpty())
        // The tiles of the view, and no other.
        assertEquals(keys.toSet(), store.tiles.keys)
        // Turned east, a few hundred kilometres at a time: each view's tiles are read, and only the last sixteen kept.
        val seen = keys.toMutableSet()
        repeat(14) {
            drag(Offset(-600f, 0f))
            val shown = pack.keys(opened.last(), size())
            seen += shown
            assertTrue("$shown in ${store.tiles.keys}", store.tiles.keys.containsAll(shown))
            assertTrue(store.tiles.size <= 16)
        }
        assertTrue("Saw ${seen.size}", seen.size >= 16 + keys.size)
        assertEquals(16, store.tiles.size)
        assertTrue(keys.none { it in store.tiles })
        // Too wide for a close-up, it asks for none.
        val kept = store.tiles.keys
        pinch(by = 0.2)
        assertTrue("${kilometres()} km", kilometres() > WorldDetailPack.CLOSE_UP_KILOMETRES)
        settle()
        assertEquals(kept, store.tiles.keys)
        assertEquals(0, lastOpened.tiles)
    }

    @Test
    fun aPinchDownToATownShowsRiversRoadsBuiltUpAreasAndTownsWhereTheMapShowedBareLand() {
        openMap("Belgian chocolate")
        val palette = MapPalette.map(Brand.ink)
        val urban = palette.urban.light.argb
        val road = palette.road.light.argb
        // Wide, the land is bare.
        pinch(by = 0.3)
        assertTrue("${kilometres()} km", kilometres() > WorldDetailPack.CLOSE_UP_KILOMETRES)
        val wide = compose.drawn(map)
        assertEquals(0, lastOpened.tiles)
        assertTrue("${count(wide, urban)} built-up pixels", count(wide, urban) < 200)
        assertTrue("${count(wide, road)} road pixels", count(wide, road) < 200)
        // A town or two across, about the parcel: every level shows, full.
        pinch(by = kilometres() / 20)
        assertTrue("${kilometres()} km", kilometres() < 30)
        val close = compose.drawn(map)
        val store = WorldDetails.open.single()
        val keys = pack.keys(opened.last(), size())
        assertEquals(keys.size, lastOpened.tiles)
        assertTrue("${count(close, urban)} built-up pixels", count(close, urban) > 5_000)
        assertTrue("${count(close, road)} road pixels", count(close, road) > 500)
        // And the towns of its tiles are named.
        val towns = keys.flatMap { store.tiles.getValue(it).towns.map(DetailTile.Town::name) }.toSet()
        val cities = atlas.cities.map { it.name }.toSet()
        assertTrue(lastOpened.labels.any { it.kind == MapOverlay.LabelKind.City && it.text in towns && it.text !in cities })
    }

    /** `request`'s map seen from `camera`, as a picture `request`'s size in points, one pixel to the point. */
    private fun picture(request: MapSceneRequest, camera: GlobeCamera): IntArray {
        val scene = MapScenes.at(request, camera, pip = null, moving = false, context)
        val width = request.width.toInt()
        val height = request.height.toInt()
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        Canvas(bitmap).drawPicture(scene.base)
        return IntArray(width * height).also { bitmap.getPixels(it, 0, width, 0, 0, width, height) }
    }

    /** Coffee beans' journey, Zürich and Härkingen, on the opened map's palette, `width` by `height`, with `tiles`. */
    private fun request(tiles: List<Pair<String, DetailTile>>?, width: Double = 400.0, height: Double = 300.0): MapSceneRequest =
        MapSceneRequest(
            atlas, ParcelRoute(DemoCards.parcel("Coffee beans"), atlas, AppLanguage.En), ParcelRoute.Mode.Journey,
            MapPalette.map(Brand.ink), MapLabels.All, sites = true, showsContext = true, night = null, pip = null, quiet = false,
            insets = EdgeInsets(), covered = null, buttons = null, language = AppLanguage.En, width = width, height = height,
            dark = false, highContrast = false, detail = tiles?.let { SceneDetail(pack, it.toMap()) },
        )

    /** A view `kilometres` across its shorter side, around Zürich. */
    private fun close(kilometres: Double, width: Double = 400.0, height: Double = 300.0): GlobeCamera =
        GlobeCamera(GeoPoint(longitude = 8.4, latitude = 47.35), minOf(width, height) / (kilometres / GeoPoint.EARTH_KILOMETRES), Point(width / 2, height / 2))

    /** A tile with only the shapes of `level`. */
    private fun DetailTile.only(level: Int): DetailTile =
        DetailTile(rivers.filter { it.level == level }, lakes.filter { it.level == level }, urban.filter { it.level == level },
            roads.filter { it.level == level }, towns)

    @Test
    fun eachLevelShowsAsStrongAsTheViewIsClose() {
        val tiles = pack.keys(close(60.0), Size(400.0, 300.0)).map { it to pack.tile(it)!! }
        val urban = MapPalette.map(Brand.ink).urban.light.argb
        // Whether each level, drawn alone, changes the view at `kilometres`; and whether its built-up areas show full.
        fun shown(kilometres: Double): List<Pair<Boolean, Boolean>> {
            val camera = close(kilometres)
            // The same tiles with nothing in them, so the wide map's lakes show or not as they do with them.
            val bare = picture(request(tiles.map { (key, tile) -> key to tile.only(-1) }), camera)
            return (0..2).map { level ->
                val drawn = picture(request(tiles.map { (key, tile) -> key to tile.only(level) }), camera)
                !drawn.contentEquals(bare) to (count(drawn, urban) > count(bare, urban) + 100)
            }
        }
        // None at 700 km, where the map is drawn as it was without them.
        val wide = close(700.0)
        assertArrayEquals(picture(request(null), wide), picture(request(tiles), wide))
        assertEquals(List(3) { false to false }, shown(700.0))
        // The first comes in at half its strength at 500 km, the second at 360, the last at 200; all are full at 60.
        assertEquals(listOf(true to false, false to false, false to false), shown(500.0))
        assertEquals(listOf(true, true, false), shown(360.0).map { it.first })
        assertEquals(listOf(true, true, true), shown(200.0).map { it.first })
        val near = shown(60.0)
        assertEquals(listOf(true, true, true), near.map { it.first })
        assertTrue(near[0].second)
    }

    @Test
    fun aCloseUpNamesTheTownsOfTheTilesItShowsButNotTheJourneysOwn() {
        val size = Size(390.0, 700.0)
        val camera = close(60.0, size.width, size.height)
        val keys = pack.keys(camera, size)
        val tiles = keys.map { it to pack.tile(it)!! }
        val request = request(tiles, size.width, size.height)
        val names = MapScenes.at(request, camera, pip = null, moving = false, context).labels
            .filter { it.kind == MapOverlay.LabelKind.City }.map { it.text }
        // The names the map would give the towns of these tiles.
        val expected = MapOverlay(
            request.route, camera, size, request.insets, MapLabels.All, sites = true, mode = ParcelRoute.Mode.Journey, context = true,
            atlas = atlas, locale = AppLanguage.En.locale, countryName = { TrackingLocation.countryName(it, AppLanguage.En) },
            towns = tiles.flatMap { it.second.towns }, measurer = MapTextWidths.of(context),
        ).labels.filter { it.kind == MapOverlay.LabelKind.City }.map { it.text }
        assertEquals(expected, names)
        // More than the seven a map without close-ups names, none of them the journey's own places.
        assertTrue("$names", names.size > 7)
        assertFalse(names.contains("Zürich"))
        assertFalse(names.contains("Härkingen"))
        val wide = MapScenes.at(request(null, size.width, size.height), camera, pip = null, moving = false, context).labels
        assertTrue(wide.count { it.kind == MapOverlay.LabelKind.City } <= 7)
    }

    @Test
    fun theMapDrawsAgainAsATileComesAndNothingOfOneStillComing() {
        val route = ParcelRoute(DemoCards.parcel("Coffee beans"), atlas, AppLanguage.En)
        val asked = mutableListOf<List<String>>()
        var tiles by mutableStateOf(emptyMap<String, DetailTile>())
        val need = { keys: List<String> -> asked += keys }
        compose.waitsForMaps()
        compose.setContent {
            CompositionLocalProvider(LocalMapsAtOnce provides true, LocalMapSceneWatch provides { _, scene -> scenes += false to scene }) {
                PeekTheme(dark = false, highContrast = false, reduceMotion = true, reduceTransparency = false, carrierIdentities = DemoCards.identities) {
                    Box(Modifier.size(390.dp, 600.dp).testTag("map")) {
                        WorldMapView(
                            atlas = atlas, route = route, mode = ParcelRoute.Mode.Journey, palette = MapPalette.map(Brand.ink),
                            sites = true, detail = MapDetail(pack, tiles, need),
                        )
                    }
                }
            }
        }
        compose.waitForIdle()
        val camera = scenes.last().second.camera
        val keys = pack.keys(camera, Size(390.0, 600.0))
        assertTrue(keys.isNotEmpty())
        // It asks for the tiles of its view, once.
        assertEquals(listOf(keys), asked)
        // Until they come, it draws none, and nothing in their place.
        assertEquals(0, scenes.last().second.tiles)
        val before = compose.drawn(compose.onNodeWithTag("map"))
        val urban = MapPalette.map(Brand.ink).urban.light.argb
        assertTrue(count(before, urban) < 200)
        tiles = keys.associateWith { pack.tile(it)!! }
        compose.waitForIdle()
        assertEquals(keys.size, scenes.last().second.tiles)
        assertEquals(camera, scenes.last().second.camera)
        val after = compose.drawn(compose.onNodeWithTag("map"))
        assertTrue(count(after, urban) > count(before, urban) + 1_000)
        assertEquals(listOf(keys), asked)
    }

    @Test
    fun onlyTheOpenedMapDrawsCloseUpsAndItsTilesGoWithIt() {
        openPage("Belgian chocolate")
        // The page's pass shows the parcel close by, and the list's cards their journeys: none reads a tile.
        assertTrue(WorldDetails.open.isEmpty())
        assertTrue(scenes.isNotEmpty())
        assertTrue(scenes.all { !it.first && it.second.tiles == 0 })
        compose.onNodeWithTag(PAGE_GLOBE).performClick()
        settle()
        val store = WorldDetails.open.single()
        assertTrue(store.tiles.isNotEmpty())
        assertTrue(lastOpened.tiles > 0)
        assertTrue(scenes.filter { !it.first }.all { it.second.tiles == 0 })
        compose.onNodeWithTag(MAP_CLOSE).performClick()
        settle()
        assertTrue(WorldDetails.open.isEmpty())
    }
}
