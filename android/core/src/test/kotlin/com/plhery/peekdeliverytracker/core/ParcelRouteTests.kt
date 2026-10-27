package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlin.math.PI
import kotlin.math.hypot
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test

private fun city(name: String, country: String, longitude: Double, latitude: Double): RoutePlace =
    RoutePlace(id = name, name = name, country = country, point = GeoPoint(longitude, latitude), isCountry = false)

private val kyoto = city("Kyoto", "JP", 135.77, 35.01)
private val leipzig = city("Leipzig", "DE", 12.37, 51.34)
private val basel = city("Basel", "CH", 7.59, 47.56)
private val zurich = city("Zürich", "CH", 8.54, 47.38)
private val bern = city("Bern", "CH", 7.45, 46.95)
private val mulligen = city("Mülligen", "CH", 8.46, 47.39)
private val switzerland = RoutePlace.country("CH", name = "Switzerland", at = GeoPoint(7.46, 46.72))

/** The map in the overlay tests, with names as wide as the iPhone writes them. */
private fun mapOverlay(
    route: ParcelRoute,
    camera: GlobeCamera,
    size: Size,
    insets: EdgeInsets = EdgeInsets(),
    labels: MapLabels,
    sites: Boolean = false,
    mode: ParcelRoute.Mode,
    context: Boolean,
    pip: PipRequest? = null,
    towns: List<DetailTile.Town>? = null,
    covered: Rect? = null,
    buttons: Rect? = null,
): MapOverlay = MapOverlay(
    route = route, camera = camera, size = size, insets = insets, labels = labels, sites = sites, mode = mode, context = context,
    atlas = ParcelRouteTests.atlas, locale = Locale.forLanguageTag("en"), countryName = { it }, pip = pip, towns = towns,
    covered = covered, buttons = buttons, measurer = RecordedTextMeasurer.recorded,
)

private fun event(stage: TrackingStage, occurredAt: String, place: EventPlace?, location: String? = null): TrackingEvent =
    TrackingEvent(
        id = UUID.randomUUID(), packageId = UUID.randomUUID(), stage = stage, description = "Scan", location = location,
        occurredAt = occurredAt, place = place,
    )

/** Swift's `stride(from: 0, through: 1, by: 1.0 / 80)`. */
private val eightieths = (0..80).map { it * (1.0 / 80) }

/** ParcelRouteTests.swift: a parcel's route, the globe's camera and maths, the map's names and marks, and Pip. */
class ParcelRouteTests {
    companion object {
        val atlas: WorldAtlas by lazy { WorldAtlas(SharedResources.open("World.json").use { it.readBytes() }) }

        @JvmStatic
        @BeforeClass
        fun installIcu() = TestIcu.install()
    }

    @Test
    fun scansBecomeStopsLegsAndDistances() {
        val route = ParcelRoute(listOf(kyoto, kyoto, null, leipzig, basel, zurich))
        assertEquals(listOf("Kyoto", "Leipzig", "Basel", "Zürich"), route.stops.map { it.place.name })
        assertEquals(3, route.legs.size)
        assertTrue(route.kilometres > 9_000)
        assertEquals(listOf("JP", "DE", "CH"), route.countries)
        assertEquals(ParcelRoute.Scale.World, route.scale)
        assertEquals(kyoto, route.origin?.place)
        assertEquals(zurich, route.current?.place)
        assertTrue(route.latestLocated)
        // Leipzig is 530 km from Zürich, so only the Swiss stops are near.
        assertEquals(listOf("Basel", "Zürich"), route.near.map { it.place.name })
    }

    @Test
    fun countryOnlyScansStayHonest() {
        val germany = RoutePlace.country("DE", name = "Germany", at = GeoPoint(10.4, 51.1))
        val china = RoutePlace.country("CN", name = "China", at = GeoPoint(106.34, 32.5))
        // A country after a city in it adds nothing; a city after its country replaces it.
        assertEquals(1, ParcelRoute(listOf(leipzig, germany)).stops.size)
        val upgraded = ParcelRoute(listOf(china, city("Shenzhen", "CN", 114.06, 22.54)))
        assertEquals(listOf("Shenzhen"), upgraded.stops.map { it.place.name })
        assertFalse(ParcelRoute(listOf(china, switzerland, null)).latestLocated)
        assertTrue(ParcelRoute(listOf(china, germany)).legs[0].isApproximate)
    }

    @Test
    fun aGeneratedTrackEndsWhereTheParcelReallyWasLast() {
        // A new place every 23 h 55 min 11 s, as the web map is told (route.test.ts): no network moves a parcel so.
        val start = checkNotNull(DateParser.date("2026-10-01T09:00:00Z"))
        val villages = listOf("Exampleton", "Sampleby", "Testford", "Mockham").mapIndexed { step, name ->
            event(
                TrackingStage.InTransit, DateParser.isoString(start.plusSeconds(step * 86_111L)),
                EventPlace(latitude = 52 + step.toDouble() / 10, longitude = -1.0, precision = EventPlacePrecision.City, country = "GB", name = name),
                location = name,
            )
        }
        val shenzhen = event(
            TrackingStage.InTransit, "2026-09-28T08:00:00Z",
            EventPlace(latitude = 22.54, longitude = 114.06, precision = EventPlacePrecision.City, country = "CN", name = "Shenzhen"),
        )
        val route = ParcelRoute(listOf(shenzhen) + villages) { it }
        assertEquals(listOf("Shenzhen", "Exampleton"), route.stops.map { it.place.name })
    }

    @Test
    fun aParcelIsWhereItsDeliveryWasPlacedAndNowhereElse() {
        fun event(hour: Int, stage: TrackingStage, place: EventPlace?): TrackingEvent = event(stage, "2026-09-28T$hour:00:00Z", place)
        val zurich = EventPlace(latitude = 47.37, longitude = 8.55, precision = EventPlacePrecision.City, country = "CH", name = "Zürich")
        val bern = EventPlace(latitude = 46.95, longitude = 7.45, precision = EventPlacePrecision.City, country = "CH", name = "Bern")
        // A later delivery scan with no place, such as another source's copy, leaves the parcel delivered in Bern.
        assertTrue(
            ParcelRoute(listOf(event(10, TrackingStage.InTransit, zurich), event(12, TrackingStage.Delivered, bern), event(13, TrackingStage.Delivered, null))) { it }
                .latestLocated,
        )
        // A delivery the map can't place happened somewhere after the last stop it can.
        assertFalse(
            ParcelRoute(listOf(event(10, TrackingStage.InTransit, zurich), event(12, TrackingStage.OutForDelivery, bern), event(13, TrackingStage.Delivered, null))) { it }
                .latestLocated,
        )
        // Once something follows the delivery, only that newest scan says where the parcel is.
        assertFalse(
            ParcelRoute(listOf(event(10, TrackingStage.InTransit, zurich), event(12, TrackingStage.Delivered, bern), event(13, TrackingStage.Returned, null))) { it }
                .latestLocated,
        )
        // Scans of one second read as the journal reads them, by delivery progress.
        val sameSecond = ParcelRoute(listOf(event(10, TrackingStage.InTransit, zurich), event(10, TrackingStage.Accepted, bern))) { it }
        assertEquals(listOf("Bern", "Zürich"), sameSecond.stops.map { it.place.name })
    }

    @Test
    fun aDeliveryWithNoPlaceIsAtTheLastStopInTheDestinationCountry() {
        val seychelles = RoutePlace.country("SC", name = "Seychelles", at = GeoPoint(55.45, -4.62))
        val delivered = { destination: RoutePlace? -> ParcelRoute(listOf(zurich, bern, null), deliveries = 1, destination = destination) }
        assertTrue(delivered(switzerland).latestLocated)
        // Going to another country, or nowhere known: the last stop is only where the parcel was last seen.
        assertFalse(delivered(seychelles).latestLocated)
        assertEquals(seychelles, delivered(seychelles).destination)
        assertFalse(delivered(null).latestLocated)
        // Any other scan with no place leaves the last stop the last known one, even in the destination country.
        assertFalse(ParcelRoute(listOf(zurich, bern, null), destination = switzerland).latestLocated)
    }

    @Test
    fun theRemainingLegLastsUntilArrival() {
        val travelling = ParcelRoute(listOf(kyoto, leipzig), destination = switzerland)
        assertEquals(switzerland, travelling.destination)
        assertTrue((travelling.remainingKilometres ?: 0.0) > 500)
        assertNull(ParcelRoute(listOf(kyoto, basel), destination = switzerland).destination)
        assertNull(ParcelRoute(listOf(bern), destination = city("Bern centre", "CH", 7.451, 46.949)).destination)
    }

    @Test
    fun scalesAndViews() {
        assertEquals(ParcelRoute.Scale.None, ParcelRoute(emptyList()).scale)
        assertEquals(ParcelRoute.Scale.Point, ParcelRoute(listOf(zurich)).scale)
        assertEquals(ParcelRoute.Scale.City, ParcelRoute(listOf(zurich, mulligen)).scale)
        assertEquals(ParcelRoute.Scale.Local, ParcelRoute(listOf(bern, zurich)).scale)
        assertEquals(ParcelRoute.Scale.Region, ParcelRoute(listOf(leipzig, zurich)).scale)
        val world = ParcelRoute(listOf(kyoto, basel, zurich))
        assertTrue(world.hasNearView)
        assertFalse(ParcelRoute(listOf(bern, zurich)).hasNearView)
        // The way still to go counts too, when it leads out of the close-up.
        assertTrue(ParcelRoute(listOf(kyoto), destination = switzerland).hasNearView)
        assertFalse(ParcelRoute(listOf(bern), destination = zurich).hasNearView)
        // The camera follows the parcel: a close-up for the last mile, the whole trip otherwise.
        assertEquals(ParcelRoute.Mode.Journey, world.defaultMode(TrackingStage.InTransit))
        assertEquals(ParcelRoute.Mode.Now, world.defaultMode(TrackingStage.OutForDelivery))
        assertEquals(ParcelRoute.Mode.Now, world.defaultMode(TrackingStage.ReadyForPickup))
        assertEquals(ParcelRoute.Mode.Journey, world.defaultMode(TrackingStage.Delivered))
        assertEquals(ParcelRoute.Mode.Journey, ParcelRoute(listOf(bern, zurich)).defaultMode(TrackingStage.OutForDelivery))
    }

    @Test
    fun eventsAreOrderedByTimeAndCountriesNamedForTheReader() {
        fun event(day: Int, place: EventPlace?): TrackingEvent = event(TrackingStage.InTransit, "2026-09-${day}T08:00:00Z", place)
        val route = ParcelRoute(
            listOf(
                event(28, EventPlace(latitude = 47.37, longitude = 8.55, precision = EventPlacePrecision.City, country = "CH", name = "Zürich")),
                event(20, EventPlace(latitude = 32.5, longitude = 106.34, precision = EventPlacePrecision.Country, country = "CN", name = "China")),
                event(24, null),
            ),
        ) { if (it == "CN") "Chine" else it }
        assertEquals(listOf("Chine", "Zürich"), route.stops.map { it.place.name })
        assertTrue(route.latestLocated)
        assertEquals("47.37,8.55", route.stops.last().place.id)
    }

    @Test
    fun distancesAreRoundedTheWayAJourneyIsTold() {
        fun text(kilometres: Double, locale: String = "en"): String =
            ParcelRoute.formattedKilometres(kilometres, Locale.forLanguageTag(locale))
                .replace("\u00A0", " ")
                .replace("\u202F", " ")
        assertEquals("1 km", text(0.2))
        assertEquals("8 km", text(8.4))
        assertEquals("450 km", text(447.0))
        assertEquals("9,300 km", text(9_321.0))
        assertEquals("9.300 km", text(9_321.0, "de"))
    }

    /** Foundation's distances in each of the app's languages, from ICU as a phone has it and from a plain JVM's stand-in. */
    @Test
    fun distancesAreWrittenInEachLanguageAsFoundationWritesThem() {
        val written = mapOf(
            "en" to listOf("8 km", "1,200 km", "12,000 km", "1,234,500 km"),
            "de" to listOf("8 km", "1.200 km", "12.000 km", "1.234.500 km"),
            "fr" to listOf("8\u202Fkm", "1\u202F200\u202Fkm", "12\u202F000\u202Fkm", "1\u202F234\u202F500\u202Fkm"),
            "it" to listOf("8 km", "1200 km", "12.000 km", "1.234.500 km"),
            "es" to listOf("8 km", "1200 km", "12.000 km", "1.234.500 km"),
            "pt-PT" to listOf("8 km", "1200 km", "12\u00A0000 km", "1\u00A0234\u00A0500 km"),
            "pl" to listOf("8 km", "1200 km", "12\u00A0000 km", "1\u00A0234\u00A0500 km"),
        )
        for (formatter in listOf(Icu.lengths, JdkLengths)) {
            for ((language, texts) in written) {
                val locale = Locale.forLanguageTag(language)
                assertEquals(texts, listOf(8.0, 1_200.0, 12_000.0, 1_234_500.0).map { formatter.kilometres(it, locale) })
            }
        }
    }

    @Test
    fun theCameraFramesTheRouteAndSeesWhatItShows() {
        val box = Rect(20.0, 20.0, 360.0, 260.0)
        val camera = GlobeCamera.fit(listOf(kyoto.point, zurich.point), box, minimumKilometres = 400.0)
        val projection = GlobeProjection(camera)
        for (place in listOf(kyoto, zurich)) {
            assertTrue(place.name, box.insetBy(-1.0, -1.0).contains(projection.project(place.point)))
        }
        val single = GlobeCamera.fit(listOf(zurich.point), box, minimumKilometres = 260.0)
        val centre = GlobeProjection(single).project(zurich.point)
        assertEquals(box.midX, centre.x, 0.5)
        assertEquals(box.midY, centre.y, 0.5)
        // Looking from nearer the equator makes a long route bow toward the pole.
        val tilted = GlobeCamera.fit(listOf(kyoto.point, zurich.point), box, minimumKilometres = 400.0, tilt = true)
        assertTrue(tilted.center.latitude < camera.center.latitude)
        val back = projection.invert(projection.project(basel.point))
        assertEquals(basel.point.longitude, back?.longitude ?: 0.0, 1e-6)
        assertEquals(basel.point.latitude, back?.latitude ?: 0.0, 1e-6)
        assertNull(projection.invert(Point(camera.offset.x + camera.scale * 2, camera.offset.y)))
        // The sun stands over Greenwich at noon, and over the tropic of Cancer in June.
        val sun = GlobeCamera.subsolarPoint(Instant.ofEpochSecond(1_782_043_200))
        assertEquals(0.0, sun.longitude, 0.5)
        assertTrue(sun.latitude > 23)
    }

    @Test
    fun zoomKeepsThePlaceUnderTheFingers() {
        val camera = GlobeCamera(center = zurich.point, scale = 2_000.0, offset = Point(200.0, 150.0))
        val closer = camera.zoomed(by = 2.0, around = Point(100.0, 100.0), middle = Point(200.0, 150.0), viewport = 300.0)
        assertEquals(4_000.0, closer.scale, 1e-9)
        val place = checkNotNull(GlobeProjection(camera).invert(Point(100.0, 100.0)))
        val after = GlobeProjection(closer).project(place)
        assertEquals(100.0, after.x, 1e-6)
        assertEquals(100.0, after.y, 1e-6)
        // Zooming out stops at the whole globe, back in the middle.
        val globe = camera.zoomed(by = 1e-6, around = Point(20.0, 20.0), middle = Point(200.0, 150.0), viewport = 300.0)
        assertEquals(135.0, globe.scale, 1e-9)
        assertEquals(Point(200.0, 150.0), globe.offset)
    }

    /** CGPath's even-odd `contains` becomes MapPath.containsEvenOdd, which flattens the rim's arcs and the disk. */
    @Test
    fun shapesAroundTheFarSideFillOnlyWhatShows() {
        val disk = { camera: GlobeCamera ->
            MapPath.ellipse(Rect(camera.offset.x - camera.scale, camera.offset.y - camera.scale, camera.scale * 2, camera.scale * 2))
        }
        // Seen from above Kazakhstan with the sun over Japan, the night covers Europe, not Japan.
        val asia = GlobeCamera(center = GeoPoint(70.0, 40.0), scale = 150.0, offset = Point(200.0, 200.0))
        val view = GlobeProjection(asia)
        val antipode = GeoPoint(127.0 - 180, 2.0).vector
        val night = MapGeometry.cap(antipode, 80 * PI / 180, view, disk(asia))
        assertTrue(night.containsEvenOdd(view.project(GeoPoint(5.0, 45.0))))
        assertFalse(night.containsEvenOdd(view.project(GeoPoint(130.0, 35.0))))
        // A cap around the south pole shows its northern edge from the tropics, and nothing from the north.
        // Probe points stay off the disk's axes, where its curve segments meet.
        val ring = (0 until 72).map { GeoPoint(it * 5.0, -50.0).vector }
        val south = WorldAtlas.Part(listOf(ring), GeoPoint(0.0, -90.0).vector, 40 * PI / 180)
        val tropics = GlobeCamera(center = GeoPoint(0.0, -20.0), scale = 150.0, offset = Point(200.0, 200.0))
        val visible = MapPath()
        MapGeometry.addPart(visible, south, GlobeProjection(tropics), disk(tropics))
        assertTrue(visible.containsEvenOdd(GlobeProjection(tropics).project(GeoPoint(0.0, -70.0))))
        assertFalse(visible.containsEvenOdd(Point(203.1, 196.7)))
        val north = GlobeCamera(center = GeoPoint(0.0, 60.0), scale = 150.0, offset = Point(200.0, 200.0))
        val hidden = MapPath()
        MapGeometry.addPart(hidden, south, GlobeProjection(north), disk(north))
        for (point in listOf(Point(203.1, 196.7), Point(207.3, 338.2), Point(61.9, 211.4))) {
            assertFalse("$point", hidden.containsEvenOdd(point))
        }
        // Dusk across the middle of the view also passes just behind the globe: each of its bands
        // fills the night side, not wedges across the day.
        val indianOcean = GlobeCamera(center = GeoPoint(70.0, 0.0), scale = 150.0, offset = Point(200.0, 200.0))
        val dusk = GlobeProjection(indianOcean)
        val midnight = GeoPoint(160.0, 0.0).vector
        for (degrees in listOf(88.4, 89.6, 89.9, 90.1, 90.8, 92.0)) {
            val band = MapGeometry.cap(midnight, degrees * PI / 180, dusk, disk(indianOcean))
            for (place in listOf(GeoPoint(130.0, 40.0), GeoPoint(130.0, -40.0))) {
                assertTrue("$degrees°", band.containsEvenOdd(dusk.project(place)))
            }
            for (place in listOf(GeoPoint(10.0, 40.0), GeoPoint(10.0, -40.0))) {
                assertFalse("$degrees°", band.containsEvenOdd(dusk.project(place)))
            }
        }
    }

    /**
     * The pixels wait for :app's MapPainter. What it fills is checked here: drawBase shades the night in twenty rings
     * around the sun's antipode, 1.2° wide from 92° in, ring `b` at an opacity of (b + 1) / 20 of the night colour.
     */
    @Test
    fun theNightSideIsDrawnWhereItIsNight() {
        val camera = GlobeCamera(center = GeoPoint(70.0, 30.0), scale = 180.0, offset = Point(200.0, 200.0))
        val projection = GlobeProjection(camera)
        val disk = MapPath.ellipse(Rect(camera.offset.x - camera.scale, camera.offset.y - camera.scale, camera.scale * 2, camera.scale * 2))
        // 03:53 UTC: the sun is over China, and Europe is in the dark.
        val time = checkNotNull(DateParser.date("2026-09-29T03:53:00Z"))
        val sun = GlobeCamera.subsolarPoint(time)
        val antipode = GeoPoint(sun.longitude + 180, -sun.latitude).vector
        fun night(place: GeoPoint): Double {
            val point = projection.project(place)
            return (0 until 20).sumOf { band ->
                val outer = (92 - 1.2 * band.toDouble()) * PI / 180
                val ring = MapGeometry.cap(antipode, outer, projection, disk)
                if (band < 19) ring.addPath(MapGeometry.cap(antipode, outer - 1.2 * PI / 180, projection, disk))
                if (ring.containsEvenOdd(point)) (band + 1) / 20.0 else 0.0
            }
        }
        val africa = night(GeoPoint(3.0, 28.0))
        val europe = night(GeoPoint(30.0, 50.0))
        val china = night(GeoPoint(105.0, 35.0))
        // Deep night over the Sahara, dusk over Eastern Europe, day in China.
        assertEquals("Africa $africa", 1.0, africa, 1e-12)
        assertTrue("Europe $europe", europe > 0 && europe < 1)
        assertEquals("China $china", 0.0, china, 0.0)
    }

    @Test
    fun theBundledAtlasHasCountriesBordersAndCities() {
        val label = checkNotNull(atlas.label("CH"))
        assertEquals(8.0, label.longitude, 1.0)
        assertEquals(46.8, label.latitude, 1.0)
        assertNull(atlas.label("XX"))
        assertTrue(atlas.coarse.countries.size > 200)
        assertTrue(atlas.fine.borders.size > 100)
        assertFalse(atlas.lakes.isEmpty())
        assertTrue(atlas.cities.any { it.name == "Tokyo" })
    }

    @Test
    fun closeUpNamesThePlaceAndPointsToTheFarOrigin() {
        val route = ParcelRoute(listOf(kyoto, basel, zurich))
        val size = Size(400.0, 300.0)
        val box = Rect(40.0, 40.0, 320.0, 220.0)
        val camera = GlobeCamera.fit(route.near.map { it.place.point }, box, minimumKilometres = 260.0)
        val overlay = mapOverlay(route, camera, size, labels = MapLabels.All, mode = ParcelRoute.Mode.Now, context = true)
        assertTrue(overlay.labels.any { it.text == "Zürich" && it.kind == MapOverlay.LabelKind.Current })
        val pointer = checkNotNull(overlay.pointers.firstOrNull())
        assertEquals("Kyoto", pointer.name)
        assertTrue(pointer.detail.endsWith("km"))
        assertTrue(Rect(Point.Zero, size).contains(pointer.center))
        assertTrue(overlay.dots.any { it.kind == MapOverlay.DotKind.Current })
    }

    @Test
    fun aFacilityIsNamedByItsTownOnACardAndInFullOnTheOpenedMap() {
        val centre = RoutePlace(
            EventPlace(latitude = 47.3959, longitude = 8.4695, precision = EventPlacePrecision.City, country = "CH", name = "Zürich", site = "Zürich-Mülligen"),
        )
        assertEquals(listOf("Zürich", "Zürich-Mülligen"), listOf(centre.name(sites = false), centre.name(sites = true)))
        assertEquals("Zürich", zurich.name(sites = true))
        val size = Size(400.0, 300.0)
        val box = Rect(40.0, 40.0, 320.0, 220.0)
        fun names(route: ParcelRoute, sites: Boolean, mode: ParcelRoute.Mode = ParcelRoute.Mode.Journey): Pair<List<String>, List<String>> {
            val points = if (mode == ParcelRoute.Mode.Now) route.near.map { it.place.point } else route.stops.map { it.place.point }
            // Close enough for the centre's dot and the town's to stand apart.
            val overlay = mapOverlay(
                route, GlobeCamera.fit(points, box, minimumKilometres = 80.0), size, labels = MapLabels.All, sites = sites, mode = mode,
                context = false,
            )
            return overlay.labels.map { it.text } to overlay.pointers.map { it.name }
        }
        // The town and the centre 7 km from it are both "Zürich": the name is written once, at the parcel.
        val route = ParcelRoute(listOf(basel, centre, zurich))
        assertEquals(listOf("Basel", "Zürich"), names(route, sites = false).first.sorted())
        assertEquals(listOf("Basel", "Zürich", "Zürich-Mülligen"), names(route, sites = true).first.sorted())
        // Far from the centre, the pointer to it follows the same rule.
        val far = ParcelRoute(listOf(centre, kyoto))
        assertEquals(listOf("Zürich"), names(far, sites = false, mode = ParcelRoute.Mode.Now).second)
        assertEquals(listOf("Zürich-Mülligen"), names(far, sites = true, mode = ParcelRoute.Mode.Now).second)
    }

    @Test
    fun aTownIsFramedAsOnePlaceOnACardAndCloserWithCloseUps() {
        val size = Size(400.0, 300.0)
        val span = { camera: GlobeCamera -> minOf(size.width, size.height) / camera.scale * GeoPoint.EARTH_KILOMETRES }
        val winterthur = city("Winterthur", "CH", 8.72, 47.5)
        val town = ParcelRoute(listOf(zurich, winterthur))
        assertEquals(ParcelRoute.Scale.City, town.scale)
        val card = span(GlobeCamera.framing(town, ParcelRoute.Mode.Journey, size, EdgeInsets()))
        assertEquals(span(GlobeCamera.framing(ParcelRoute(listOf(zurich)), ParcelRoute.Mode.Journey, size, EdgeInsets())), card, 0.001)
        // With rivers, roads and towns around them, the two places are set apart, and still a town or two of land around.
        val opened = span(GlobeCamera.framing(town, ParcelRoute.Mode.Journey, size, EdgeInsets(), closeUps = true))
        assertTrue(opened > 60)
        assertTrue(opened < card / 2.5)
        // Places further apart, and a single place, are framed as before.
        for (route in listOf(ParcelRoute(listOf(city("Lyon", "FR", 4.84, 45.76), zurich)), ParcelRoute(listOf(zurich)))) {
            assertEquals(
                GlobeCamera.framing(route, ParcelRoute.Mode.Journey, size, EdgeInsets()),
                GlobeCamera.framing(route, ParcelRoute.Mode.Journey, size, EdgeInsets(), closeUps = true),
            )
        }
    }

    @Test
    fun placesTooCloseToTellApartAreOneMark() {
        val size = Size(400.0, 300.0)
        fun overlay(route: ParcelRoute, camera: GlobeCamera): MapOverlay =
            mapOverlay(route, camera, size, labels = MapLabels.Ends, mode = ParcelRoute.Mode.Journey, context = false)
        val lyon = city("Lyon", "FR", 4.84, 45.76)
        val dubendorf = city("Dübendorf", "CH", 8.62, 47.4)
        val route = ParcelRoute(listOf(lyon, dubendorf, zurich))
        // One dot and one name for Dübendorf and Zürich, at the parcel's, and one line from Lyon to that dot.
        val whole = overlay(route, GlobeCamera.framing(route, ParcelRoute.Mode.Journey, size, EdgeInsets()))
        assertEquals(listOf(MapOverlay.DotKind.Origin, MapOverlay.DotKind.Current), whole.dots.map { it.kind })
        assertEquals(setOf("Lyon", "Zürich"), whole.labels.map { it.text }.toSet())
        assertEquals(1, whole.legs.size)
        val end = checkNotNull(whole.legs.firstOrNull()?.path?.currentPoint)
        val current = checkNotNull(whole.dots.lastOrNull()?.point)
        assertTrue(hypot(end.x - current.x, end.y - current.y) < 1)
        // Up close, each place has its own mark again, and the hop between them is drawn.
        val box = Rect(40.0, 40.0, 320.0, 220.0)
        val near = overlay(route, GlobeCamera.fit(listOf(dubendorf.point, zurich.point), box, minimumKilometres = 40.0))
        assertEquals(listOf(MapOverlay.DotKind.Origin, MapOverlay.DotKind.Stop, MapOverlay.DotKind.Current), near.dots.map { it.kind })
        assertEquals(2, near.legs.size)
        // Of a first place and a later one beside it, the first keeps its mark and its name.
        val back = ParcelRoute(
            listOf(city("Winterthur", "CH", 8.72, 47.5), city("Frankfurt", "DE", 8.68, 50.11), zurich, city("Hamburg", "DE", 9.99, 53.55)),
        )
        val far = overlay(back, GlobeCamera.framing(back, ParcelRoute.Mode.Journey, size, EdgeInsets()))
        assertEquals(listOf(MapOverlay.DotKind.Origin, MapOverlay.DotKind.Stop, MapOverlay.DotKind.Current), far.dots.map { it.kind })
        assertEquals(setOf("Hamburg", "Winterthur"), far.labels.map { it.text }.toSet())
    }

    @Test
    fun thePlaceOfTheParcelIsNamedInsideTheFrame() {
        val size = Size(350.0, 160.0)
        fun overlay(route: ParcelRoute, camera: GlobeCamera, insets: EdgeInsets): MapOverlay =
            mapOverlay(route, camera, size, insets, labels = MapLabels.Ends, mode = ParcelRoute.Mode.Journey, context = false)
        // On a card, the parcel's place lands at the west end and the destination's ring takes the one side with room.
        val card = EdgeInsets(top = 40.0, leading = 16.0, bottom = 28.0, trailing = 16.0)
        val paris = city("Paris", "FR", 2.55, 49.01)
        val middles = listOf(0.25, 0.5, 0.75).flatMap {
            listOf(kyoto.point.interpolated(paris.point, it), paris.point.interpolated(switzerland.point, it))
        }
        val far = GlobeCamera.fit(
            listOf(kyoto.point, paris.point, switzerland.point) + middles, Rect(16.0, 40.0, 318.0, 92.0).insetBy(11.0, 11.0),
            minimumKilometres = 400.0, tilt = true,
        )
        val name = checkNotNull(overlay(ParcelRoute(listOf(kyoto, paris), destination = switzerland), far, card).labels.firstOrNull { it.text == "Paris" })
        assertEquals(MapOverlay.LabelKind.Current, name.kind)
        assertTrue(name.frame.minX >= card.leading)
        assertTrue(name.frame.maxX <= size.width - card.trailing)

        // In the corner of a small frame no side of the dot has room: the name moves in from the edge.
        val small = EdgeInsets(top = 32.0, leading = 125.0, bottom = 32.0, trailing = 125.0)
        val bergamo = city("Bergamo", "IT", 9.67, 45.7)
        val mulhouse = city("Mulhouse", "FR", 7.34, 47.75)
        val corner = GlobeCamera.fit(
            listOf(bergamo.point, mulhouse.point), Rect(125.0, 32.0, 100.0, 96.0).insetBy(11.52, 11.52), minimumKilometres = 120.0,
        )
        val moved = checkNotNull(overlay(ParcelRoute(listOf(bergamo, mulhouse)), corner, small).labels.firstOrNull { it.text == "Mulhouse" })
        val frame = Rect(small.leading, small.top, size.width - small.leading - small.trailing, size.height - small.top - small.bottom)
        assertTrue("${moved.frame}", frame.contains(moved.frame))
    }

    // Not an iPhone test: the layout measures each name once per measurer and font, and tracks it per Character.
    @Test
    fun eachNameIsMeasuredOnce() {
        val measured = mutableListOf<String>()
        val measurer = TextMeasurer { text, font -> measured.add("$text ${font.size}"); 10.2 }
        val font = MapOverlay.Fonts.pointerName
        repeat(3) { assertEquals(11.0, MapOverlay.Fonts.width("Zürich", font, measurer), 0.0) }
        assertEquals(11.0, MapOverlay.Fonts.width("Zürich", MapOverlay.Fonts.pointerDetail, measurer), 0.0)
        assertEquals(11.0 + 6 * 0.5, MapOverlay.Fonts.width("Zürich", font, measurer, tracking = 0.5), 0.0)
        assertEquals(listOf("Zürich 11.0", "Zürich 11.0", "Zürich 11.0"), measured)
        // Another measurer measures for itself.
        assertEquals(12.0, MapOverlay.Fonts.width("Zürich", font, TextMeasurer { _, _ -> 12.0 }), 0.0)
    }

    @Test
    fun pipHasAMoodForEveryStageWithAMap() {
        val moods = TrackingStage.entries.associateWith { PipMood(it) }
        assertEquals(
            mapOf(
                TrackingStage.Pending to null, TrackingStage.Registered to PipMood.Look, TrackingStage.Accepted to PipMood.Look,
                TrackingStage.InTransit to PipMood.Look, TrackingStage.OutForDelivery to PipMood.Eager, TrackingStage.Customs to PipMood.Wait,
                TrackingStage.ReadyForPickup to PipMood.Wait, TrackingStage.FailedAttempt to PipMood.Worry, TrackingStage.Exception to PipMood.Worry,
                TrackingStage.Returned to PipMood.Worry, TrackingStage.Delivered to PipMood.Joy,
            ),
            moods,
        )
        assertNull(PipMood(null))
        for (mood in PipMood.entries) {
            val extents = PipGeometry.extents(mood, 1)
            val away = { point: Point -> PipGeometry.outlines(mood, 1).map { PipGeometry.distance(point, it) }.minOrNull() ?: 0.0 }
            // The middle of his box is on him; its top corner on the dot's side is not.
            assertEquals("$mood", 0.0, away(Point(extents.midX, extents.midY)), 0.0)
            assertTrue("$mood", away(Point(extents.maxX, extents.minY)) > 10)
        }
        // An eager Pip's speed lines trail on the far side from the dot.
        assertEquals(0.0, PipGeometry.extents(PipMood.Eager, 1).minX, 0.0)
        assertEquals(300.0, PipGeometry.extents(PipMood.Eager, -1).maxX, 0.0)
        // Still unless the mood moves him: a bob that comes back to where it started, and a jump that lands.
        assertEquals(AffineTransform.Identity, PipArtwork.motion(PipMood.Look, 3.0))
        assertNotEquals(AffineTransform.Identity, PipArtwork.motion(PipMood.Joy, 0.28 * 2.2))
        assertEquals(0.0, PipArtwork.motion(PipMood.Joy, 0.8 * 2.2).ty, 0.001)
        assertEquals(PipArtwork.motion(PipMood.Eager, 1.1).b, PipArtwork.motion(PipMood.Eager, 0.0).b, 0.001)
    }

    @Test
    fun pipOpensHisBoxOverASecondRearFlapsFirst() {
        val parts = { box: PipOpening ->
            listOf(box.rear, box.front, box.under, box.tape, box.lift, box.faceBefore, box.face, box.glints, box.stance)
        }
        for ((box, rest) in listOf(PipOpening.at(0.0) to PipOpening.closed, PipOpening.at(-1.0) to PipOpening.closed, PipOpening.at(PipOpening.DURATION) to PipOpening.open)) {
            for ((part, value) in parts(box).zip(parts(rest))) assertEquals(value, part, 0.001)
        }
        // The rear flaps fold away first; the front ones swing past open and come back.
        val early = PipOpening.at(0.2)
        assertTrue(early.rear > early.front)
        assertTrue(early.front > 0)
        assertTrue(((0..20).map { PipOpening.at(0.17 + it * 0.04).front }.maxOrNull() ?: 0.0) > 1.02)
        // The two flaps that lie under the others show before they have turned far.
        assertEquals(1.0, PipOpening.at(0.4).under, 0.001)
        // Half way, the tape is off and he has his new face; the glints wait for the flaps to settle.
        val middle = PipOpening.at(0.5)
        assertEquals(0.0, middle.tape, 0.001)
        assertEquals(0.0, middle.faceBefore, 0.001)
        assertEquals(1.0, middle.face, 0.001)
        assertEquals(0.0, middle.glints, 0.001)
        assertEquals(1.0, middle.stance, 0.001)
        // A flap part of the way open stands between closed and open.
        val half = PipGeometry.backRight.points(PipOpening.at(0.2).rear)[2]
        assertTrue(half.y < PipGeometry.backRight.closed[2].y)
        assertTrue(half.y > PipGeometry.backRight.opened[2].y)
    }

    @Test
    fun openFrontFlapsSwingThroughUprightAndRestAboveTheEyes() {
        for (flap in listOf(PipGeometry.frontLeft, PipGeometry.frontRight)) {
            assertEquals(flap.closed, flap.points(0.0))
            for ((point, rest) in flap.points(1.0).zip(flap.opened)) {
                assertEquals(rest.x, point.x, 0.001)
                assertEquals(rest.y, point.y, 0.001)
            }
            // A quarter turn stands the flap upright on the front corner, about as tall as it is deep.
            val upright = flap.points(90.0 / 154)[2]
            assertEquals(150.0, upright.x, 1.5)
            assertEquals(190.0 - 52, upright.y, 1.5)
        }
        // The rear flaps still fold straight over.
        assertEquals(Point(151.0, 95.5), PipGeometry.backLeft.points(0.5)[2])
        // The left flap is a brim: the happy eyes of the kraft parcel and of the ink Pip stay clear of it.
        for (top in listOf(Point(32.5, 33.5), Point(63.5, 33.5), Point(26.25, 29.25), Point(69.75, 29.25))) {
            assertTrue(PipGeometry.distance(top.applying(PipGeometry.facePlane), PipGeometry.frontLeft.opened) > 6)
        }
        // Thrown open, a flap goes a little too far before it settles.
        assertEquals(0.0, PipGeometry.settle(0.0), 0.0)
        assertEquals(1.0, PipGeometry.settle(1.0), 0.0)
        assertTrue(PipGeometry.settle(0.43) > 1.05)
        assertEquals(1.0, PipGeometry.settle(0.9), 0.01)
    }

    /** The pixels of the label on SmallPip and UnwrappingParcel wait for :app; the clear patch they are read at is checked here. */
    @Test
    fun theCarriersLabelSitsOnTheBoxsRightSideClosedAndOpen() {
        // A clear patch of the label's paper, right of its barcode, as a point of Pip's frame.
        val patch = PipGeometry.Label.point(62.0, 28.0)
        assertEquals(220.14, patch.x, 0.01)
        assertEquals(205.77, patch.y, 0.01)
    }

    @Test
    fun pipBlinksAtTheEndOfEachPeriodAndIsLeftAloneBetween() {
        val period = PipBlink.PERIOD
        for (time in listOf(0.0, 1.0, 0.93 * period, period, period + 2)) {
            assertEquals("$time", 1.0, PipBlink.eyeScale(time), 0.0001)
        }
        assertEquals(0.54, PipBlink.eyeScale(0.945 * period), 0.001)
        assertEquals(PipBlink.CLOSED, PipBlink.eyeScale(0.96 * period), 0.001)
        assertEquals(0.54, PipBlink.eyeScale(0.98 * period), 0.001)
        assertEquals(PipBlink.CLOSED, PipBlink.eyeScale(3.96 * period), 0.001)
        // The second eye follows the first, and waits for its first turn.
        assertEquals(PipBlink.CLOSED, PipBlink.eyeScale(0.96 * period + PipBlink.LAG, eye = 1), 0.001)
        assertTrue(PipBlink.eyeScale(0.96 * period, eye = 1) > PipBlink.eyeScale(0.96 * period))
        assertEquals(1.0, PipBlink.eyeScale(0.01, eye = 1), 0.0)

        // The face is drawn for the frames of a blink only, and both eyes are open whenever it rests.
        assertEquals(0.93 * period, PipBlink.nextFrame(0.0), 0.0001)
        val frames = mutableListOf<Double>()
        var time = 0.0
        while (time < 3 * period) {
            val next = PipBlink.nextFrame(time)
            assertTrue(next > time)
            if (next - time > 1) {
                assertEquals("$time", 1.0, PipBlink.eyeScale(time), 0.0001)
                assertEquals("$time", 1.0, PipBlink.eyeScale(time, eye = 1), 0.0001)
                assertEquals(0.93 * period - (if (time > 0) PipBlink.LAG else 0.0), next - time, 0.0001)
            }
            frames.add(next)
            time = next
        }
        assertTrue(frames.size < 3 * 25)
        assertTrue(frames.any { PipBlink.eyeScale(it) < 0.2 })
        assertTrue(frames.any { PipBlink.eyeScale(it, eye = 1) < 0.2 })
    }

    // Not an iPhone test: PipPalette's colours as SwiftUI resolves them on the iPhone, in Float32.
    @Test
    fun pipIsDrawnInTheIphonesColours() {
        val kraft = PipPalette.kraft
        val light = PipPalette.ink(Brand.ink, on = Brand.paper, dark = false)
        val dark = PipPalette.ink(Brand.ink, on = Brand.paper, dark = true)
        val cases = listOf(
            Triple(kraft.inside, false, listOf(0.501961, 0.388235, 0.270588, 1.0)),
            Triple(kraft.eyeWhite, false, listOf(1.0, 0.992157, 0.964706, 1.0)),
            Triple(kraft.catchlight, true, listOf(1.0, 1.0, 1.0, 1.0)),
            Triple(kraft.shadow, false, listOf(0.0, 0.0, 0.0, 0.08)),
            Triple(kraft.speedLines, false, listOf(0.717647, 0.560784, 0.4, 0.45)),
            Triple(kraft.glint(3), false, listOf(0.709804, 0.580392, 0.745098, 1.0)),
            Triple(light.inside, false, listOf(0.102902, 0.11898, 0.096471, 1.0)),
            Triple(light.right, false, listOf(0.265412, 0.281882, 0.258824, 1.0)),
            Triple(light.eyeWhite, false, listOf(1.0, 1.0, 1.0, 1.0)),
            Triple(light.blush, false, listOf(0.747271, 0.476133, 0.499412, 1.0)),
            Triple(light.shadow, false, listOf(0.102902, 0.11898, 0.096471, 0.14)),
            Triple(light.speedLines, false, listOf(0.12549, 0.145098, 0.117647, 0.32)),
            Triple(light.glint(3), false, listOf(0.247922, 0.264784, 0.241176, 1.0)),
            Triple(dark.inside, true, listOf(0.115765, 0.128627, 0.115765, 1.0)),
            Triple(dark.right, true, listOf(0.829647, 0.835451, 0.819765, 1.0)),
            Triple(dark.eyeWhite, true, listOf(0.985098, 0.986588, 0.980627, 1.0)),
            Triple(dark.blush, true, listOf(0.787718, 0.515733, 0.538871, 1.0)),
            Triple(dark.shadow, true, listOf(0.0, 0.0, 0.0, 0.26)),
            Triple(dark.speedLines, true, listOf(0.960784, 0.964706, 0.94902, 0.32)),
            Triple(dark.glint(3), true, listOf(0.846039, 0.851608, 0.835922, 1.0)),
        )
        for ((index, case) in cases.withIndex()) {
            val (color, inDark, expected) = case
            val resolved = color.resolve(inDark)
            val actual = listOf(resolved.red, resolved.green, resolved.blue, resolved.opacity)
            for (channel in 0..3) assertEquals("case $index channel $channel", expected[channel], actual[channel], 1e-6)
        }
        // The glints take their three tones in turn, the ink's own first.
        assertEquals(listOf(0, 1, 0, 2, 1, 0).map { light.glints[it] }, (0..5).map(light::glint))
        assertEquals(Brand.ink, light.glints[0])
    }

    // Not an iPhone test: each glint's twinkle as the iPhone's PipArtwork.twinkle gives it, its curve in Float32.
    @Test
    fun eachGlintTwinklesOnItsOwnClock() {
        val cases = listOf(
            Triple(0, 0.0, listOf(0.2, 0.6, -10.0)),
            Triple(3, 0.0, listOf(0.9998748779296875, 1.0999217987060548, 7.997184753417969)),
            Triple(0, 1.1, listOf(1.0, 1.1, 8.0)),
            Triple(1, 1.1, listOf(0.65595703125, 0.88497314453125, 0.259033203125)),
            Triple(2, 2.7, listOf(0.9167694091796876, 1.0479808807373048, 6.127311706542969)),
            Triple(5, 2.7, listOf(0.6429962158203125, 0.8768726348876953, -0.03258514404296875)),
            // Before its clock starts, a glint twinkles as it would a period later.
            Triple(3, -1.0, listOf(0.21048507690429688, 0.6065531730651855, -9.76408576965332)),
        )
        for ((index, time, expected) in cases) {
            val twinkle = PipArtwork.twinkle(index, at = time)
            assertEquals("$index at $time", expected[0], twinkle.opacity, 1e-5)
            assertEquals("$index at $time", expected[1], twinkle.scale, 1e-5)
            assertEquals("$index at $time", expected[2], twinkle.degrees, 1e-4)
        }
        // Each glint repeats on a period of its own: 2.2, 2.7 or 3.2 seconds.
        for ((index, period) in listOf(0 to 2.2, 1 to 2.7, 2 to 3.2, 4 to 2.7)) {
            assertEquals("$index", PipArtwork.twinkle(index, at = 0.5).degrees, PipArtwork.twinkle(index, at = 0.5 + period).degrees, 1e-9)
        }
    }

    @Test
    fun aMapItsCardWritesOverKeepsClearOfTheWriting() {
        // The parcel's page, and a second carrier's mark in its top left corner.
        val size = Size(358.0, 176.0)
        val card = EdgeInsets(top = 40.0, leading = 16.0, bottom = 44.0, trailing = 16.0)
        val mark = Rect(18.0, 56.0, 100.0, 18.0)
        fun overlay(
            route: ParcelRoute,
            camera: GlobeCamera,
            mode: ParcelRoute.Mode = ParcelRoute.Mode.Journey,
            covered: Rect? = null,
            pip: Boolean = false,
        ): MapOverlay = mapOverlay(
            route, camera, size, card, labels = MapLabels.Ends, mode = mode, context = false,
            pip = if (pip) PipRequest(PipMood.Look, ceiling = 52.0, floor = 160.0) else null, covered = covered,
        )
        // How many places of the journey a camera names, around the writing when there is some, as the map counts them.
        fun counted(route: ParcelRoute, mode: ParcelRoute.Mode = ParcelRoute.Mode.Journey): (GlobeCamera, Rect?) -> Double =
            { camera, covered -> overlay(route, camera, mode, covered = covered).shown }
        fun usual(route: ParcelRoute, mode: ParcelRoute.Mode = ParcelRoute.Mode.Journey): GlobeCamera =
            GlobeCamera.framing(route, mode, size, card)
        fun name(overlay: MapOverlay, text: String): Rect? = overlay.labels.firstOrNull { it.text == text }?.frame
        val harkingen = city("Härkingen", "CH", 7.821, 47.305)
        val london = city("London", "GB", -0.128, 51.507)

        // A route that stays clear of the writing keeps its usual frame, and a name moves off it.
        val fromLondon = ParcelRoute(listOf(london, city("Reims", "FR", 4.031, 49.258), basel, harkingen))
        assertEquals(usual(fromLondon), GlobeCamera.framing(fromLondon, ParcelRoute.Mode.Journey, size, card, covered = mark, shown = counted(fromLondon)))
        assertTrue(checkNotNull(name(overlay(fromLondon, usual(fromLondon)), "London")).intersects(mark))
        val written = overlay(fromLondon, usual(fromLondon), covered = mark)
        assertEquals(listOf("Härkingen", "London"), written.labels.map { it.text })
        assertFalse(written.labels.any { it.frame.intersects(mark) })

        // A route that would pass under the writing is framed clear of it.
        val fromShenzhen = ParcelRoute(listOf(city("Shenzhen", "CN", 114.06, 22.54), city("Liège", "BE", 5.57, 50.63), zurich))
        val under = { overlay: MapOverlay -> overlay.dots.filter { mark.insetBy(-10.0, -10.0).contains(it.point) } }
        assertFalse(under(overlay(fromShenzhen, usual(fromShenzhen))).isEmpty())
        val lower = overlay(fromShenzhen, GlobeCamera.framing(fromShenzhen, ParcelRoute.Mode.Journey, size, card, covered = mark), covered = mark, pip = true)
        assertTrue(under(lower).isEmpty())
        assertTrue(lower.dots.all { it.point.x > 16 && it.point.x < size.width - 16 && it.point.y > 40 && it.point.y < size.height - 44 })
        assertEquals(listOf("Zürich", "Shenzhen"), lower.labels.map { it.text })
        assertFalse(lower.labels.any { it.frame.intersects(mark) })
        assertFalse(checkNotNull(lower.pip).box.intersects(mark))

        // Nothing passes under the mark, but the name beside the first dot has nowhere left to go: the route is framed again.
        val fromAmsterdam = ParcelRoute(listOf(city("Amsterdam", "NL", 4.9, 52.37), city("Köln", "DE", 6.96, 50.94), basel, bern))
        assertEquals(usual(fromAmsterdam), GlobeCamera.framing(fromAmsterdam, ParcelRoute.Mode.Journey, size, card, covered = mark))
        assertEquals(listOf("Bern"), overlay(fromAmsterdam, usual(fromAmsterdam), covered = mark).labels.map { it.text })
        val beside = GlobeCamera.framing(fromAmsterdam, ParcelRoute.Mode.Journey, size, card, covered = mark, shown = counted(fromAmsterdam))
        assertNotEquals(usual(fromAmsterdam), beside)
        assertEquals(usual(fromAmsterdam).scale, beside.scale, 0.000001)
        val moved = overlay(fromAmsterdam, beside, covered = mark)
        assertEquals(listOf("Bern", "Amsterdam"), moved.labels.map { it.text })
        assertFalse(moved.labels.any { it.frame.intersects(mark) })
        // A narrower card leaves the name less room beside the mark: the route moves further aside, drawn as large.
        val narrow = Size(288.0, 176.0)
        fun onNarrow(camera: GlobeCamera, covered: Rect?): MapOverlay =
            mapOverlay(fromAmsterdam, camera, narrow, card, labels = MapLabels.Ends, mode = ParcelRoute.Mode.Journey, context = false, covered = covered)
        val roomy = GlobeCamera.framing(fromAmsterdam, ParcelRoute.Mode.Journey, narrow, card)
        val aside = GlobeCamera.framing(fromAmsterdam, ParcelRoute.Mode.Journey, narrow, card, covered = mark, shown = { camera, covered -> onNarrow(camera, covered).shown })
        assertEquals(roomy.scale, aside.scale, 0.000001)
        assertTrue(aside.offset.x - roomy.offset.x > (mark.maxX - card.leading) / 2 + 1)
        assertEquals(listOf("Bern", "Amsterdam"), onNarrow(aside, mark).labels.map { it.text })

        // Pip stands clear of the writing, wherever it is.
        val fromBerlin = ParcelRoute(listOf(city("Berlin", "DE", 13.405, 52.52), city("Neuenstein", "DE", 9.58, 49.2), basel, harkingen))
        val spot = checkNotNull(overlay(fromBerlin, usual(fromBerlin), pip = true).pip).box.insetBy(4.0, 4.0)
        assertFalse(checkNotNull(overlay(fromBerlin, usual(fromBerlin), covered = spot, pip = true).pip).box.intersects(spot))

        // A shared parcel's card, and a parcel just under its second mark: Pip never stands on the mark, so the route is framed
        // again for him to have a spot, every place keeping its name.
        val shared = Size(358.0, 236.0)
        val sharedCard = EdgeInsets(top = 48.0, leading = 18.0, bottom = 56.0, trailing = 18.0)
        val sharedMark = Rect(20.0, 50.0, 100.0, 18.0)
        val toZurich = ParcelRoute(
            listOf(city("Shenzhen", "CN", 114.06, 22.54), city("Hong Kong", "HK", 114.17, 22.32), city("Leipzig", "DE", 12.37, 51.34), zurich),
        )
        fun onShared(camera: GlobeCamera, covered: Rect?): MapOverlay = mapOverlay(
            toZurich, camera, shared, sharedCard, labels = MapLabels.Ends, mode = ParcelRoute.Mode.Journey, context = false,
            pip = PipRequest(PipMood.Look, ceiling = 48.0), covered = covered,
        )
        val sharedUsual = GlobeCamera.framing(toZurich, ParcelRoute.Mode.Journey, shared, sharedCard)
        assertNotNull(onShared(sharedUsual, null).pip)
        assertFalse(onShared(sharedUsual, sharedMark).pip?.box?.intersects(sharedMark) ?: false)
        val roomForPip = GlobeCamera.framing(toZurich, ParcelRoute.Mode.Journey, shared, sharedCard, covered = sharedMark, shown = { camera, covered ->
            onShared(camera, covered).shown
        })
        val framedForPip = onShared(roomForPip, sharedMark)
        assertFalse(checkNotNull(framedForPip.pip).box.intersects(sharedMark))
        assertEquals(listOf("Zürich", "Shenzhen"), framedForPip.labels.map { it.text })
        assertFalse(framedForPip.labels.any { it.frame.intersects(sharedMark) })

        // In a close-up, the chip of a far end moves beside the writing, off the parcel's dot.
        val lastMile = ParcelRoute(listOf(london, basel, harkingen, bern))
        val close = GlobeCamera.framing(lastMile, ParcelRoute.Mode.Now, size, card, covered = mark, shown = counted(lastMile, ParcelRoute.Mode.Now))
        assertEquals(usual(lastMile, ParcelRoute.Mode.Now), close)
        val chip = { overlay: MapOverlay ->
            val pointer = overlay.pointers[0]
            Rect(pointer.center.x - pointer.width / 2, pointer.center.y - 13, pointer.width, 26.0)
        }
        val plain = overlay(lastMile, close, ParcelRoute.Mode.Now)
        assertTrue(chip(plain).intersects(mark))
        val clear = overlay(lastMile, close, ParcelRoute.Mode.Now, covered = mark)
        assertFalse(chip(clear).intersects(mark))
        assertEquals(plain.pointers[0].center.y, clear.pointers[0].center.y, 0.0)
        val parcel = checkNotNull(clear.dots.firstOrNull { it.kind == MapOverlay.DotKind.Current }).point
        assertFalse(chip(clear).intersects(Rect(parcel.x - 10, parcel.y - 10, 20.0, 20.0)))
        assertEquals(name(plain, "Bern"), name(clear, "Bern"))
    }

    @Test
    fun aMapKeepsItsNamesAndPipOffTheCardsButtons() {
        // The parcel's page, with its globe, share and bell buttons in the top right corner.
        val size = Size(358.0, 176.0)
        val card = EdgeInsets(top = 40.0, leading = 16.0, bottom = 44.0, trailing = 16.0)
        val buttons = Rect(208.0, 18.0, 132.0, 44.0)
        val route = ParcelRoute(listOf(city("Wilmington", "US", -75.55, 39.74), city("Haiger", "DE", 8.21, 50.74)))
        fun overlay(camera: GlobeCamera, buttons: Rect?): MapOverlay = mapOverlay(
            route, camera, size, card, labels = MapLabels.Ends, mode = ParcelRoute.Mode.Journey, context = false,
            pip = PipRequest(PipMood.Look, ceiling = 52.0, floor = 160.0), buttons = buttons,
        )
        // The frame the map takes, with room for Pip beside the parcel's dot.
        fun framed(buttons: Rect?): GlobeCamera =
            GlobeCamera.framing(route, ParcelRoute.Mode.Journey, size, card, shown = { camera, _ -> overlay(camera, buttons).shown }, pip = true)

        // The route ends in the buttons' corner: its last name went under them.
        val before = overlay(framed(null), null)
        assertTrue(before.labels.any { it.frame.intersects(buttons) })
        val clear = overlay(framed(buttons), buttons)
        assertEquals(listOf("Haiger", "Wilmington"), clear.labels.map { it.text })
        assertFalse(clear.labels.any { it.frame.intersects(buttons) })
        assertFalse(checkNotNull(clear.pip).box.intersects(buttons))

        // Up close on the last mile, the chip pointing back to Berlin falls under the buttons, and just below them it would
        // cover the parcel's dot: on a phone's page it is left out, and Pip has his spot; a wider page keeps it.
        val lastMile = ParcelRoute(listOf(city("Berlin", "DE", 13.41, 52.52), city("Neuenstein", "DE", 9.58, 49.2)))
        fun upClose(width: Double, buttons: Boolean): MapOverlay {
            val size = Size(width, 176.0)
            val draw = { camera: GlobeCamera ->
                mapOverlay(
                    lastMile, camera, size, card, labels = MapLabels.Ends, mode = ParcelRoute.Mode.Now, context = false,
                    pip = PipRequest(PipMood.Eager, ceiling = 52.0, floor = 160.0), buttons = if (buttons) Rect(width - 150, 18.0, 132.0, 44.0) else null,
                )
            }
            return draw(GlobeCamera.framing(lastMile, ParcelRoute.Mode.Now, size, card, shown = { camera, _ -> draw(camera).shown }, pip = true))
        }
        assertEquals(listOf("Berlin"), upClose(358.0, buttons = false).pointers.map { it.name })
        val phone = upClose(358.0, buttons = true)
        assertTrue(phone.pointers.isEmpty())
        assertEquals(listOf("Neuenstein"), phone.labels.map { it.text })
        assertFalse(checkNotNull(phone.pip).box.intersects(buttons))
        assertEquals(listOf("Berlin"), upClose(480.0, buttons = true).pointers.map { it.name })
    }

    @Test
    fun pipStandsBesideTheDotOffItsNameAndTheRoute() {
        val card = EdgeInsets(top = 40.0, leading = 16.0, bottom = 28.0, trailing = 16.0)
        val hamburg = city("Hamburg", "DE", 9.99, 53.55)
        val regensdorf = city("Regensdorf", "CH", 8.47, 47.43)

        class Journey(val name: String, val route: ParcelRoute, val mode: ParcelRoute.Mode, val edge: Boolean)
        // The last flag: the journey ends at the frame's edge with a stop close by, which can leave no clear spot.
        val journeys = listOf(
            Journey("a last mile", ParcelRoute(listOf(hamburg, regensdorf, zurich)), ParcelRoute.Mode.Now, false),
            Journey(
                "a journey across a country", ParcelRoute(listOf(city("Berlin", "DE", 13.4, 52.52), city("Neuenstein", "DE", 9.58, 49.2))),
                ParcelRoute.Mode.Journey, false,
            ),
            Journey(
                "a journey with the way still to go", ParcelRoute(listOf(city("Lyon", "FR", 4.83, 45.76), basel), destination = switzerland),
                ParcelRoute.Mode.Journey, false,
            ),
            Journey("a journey across the world", ParcelRoute(listOf(kyoto, leipzig, zurich)), ParcelRoute.Mode.Journey, true),
            Journey("a journey that ends in the east", ParcelRoute(listOf(zurich, leipzig, kyoto)), ParcelRoute.Mode.Journey, true),
            Journey("a single place", ParcelRoute(listOf(zurich)), ParcelRoute.Mode.Journey, false),
        )

        class Frame(val size: Size, val floor: Double, val nextUp: Boolean)
        // Next up, and the parcel's page. Both write their title over the bottom of the map.
        val frames = listOf(Frame(Size(350.0, 160.0), 140.0, true), Frame(Size(358.0, 176.0), 160.0, false))
        fun overlay(route: ParcelRoute, mode: ParcelRoute.Mode, size: Size, request: PipRequest?, insets: EdgeInsets): MapOverlay =
            mapOverlay(route, GlobeCamera.framing(route, mode, size, insets), size, insets, labels = MapLabels.Ends, mode = mode, context = false, pip = request)

        for (journey in journeys) {
            for (frame in frames) {
                // A delivered parcel is never Next up, so the open box is only drawn on the parcel's page.
                for (mood in PipMood.entries.filter { it != PipMood.Joy || !frame.nextUp }) {
                    val drawn = overlay(journey.route, journey.mode, frame.size, PipRequest(mood, ceiling = 52.0, floor = frame.floor), card)
                    val place = "$mood on ${journey.name} in ${frame.size.width.toInt()} × ${frame.size.height.toInt()}"
                    val pip = checkNotNull(drawn.pip) { place }
                    val unit = pip.width / PipGeometry.frame.width
                    val away = { point: Point ->
                        val local = Point((point.x - pip.origin.x) / unit, (point.y - pip.origin.y) / unit)
                        (PipGeometry.outlines(pip.mood, pip.side).map { PipGeometry.distance(local, it) }.minOrNull() ?: 0.0) * unit
                    }
                    assertEquals(mood, pip.mood)
                    // Inside the map, below the card's top row and above what the card writes over the map.
                    assertTrue(place, pip.box.minX >= 4)
                    assertTrue(place, pip.box.maxX <= frame.size.width - 4)
                    assertTrue(place, pip.box.minY >= 52)
                    assertTrue(place, pip.box.maxY <= frame.floor)
                    val dot = checkNotNull(drawn.dots.firstOrNull { it.kind == MapOverlay.DotKind.Current }) { place }.point
                    assertTrue(place, away(dot) >= 9.99)
                    assertTrue(place, drawn.labels.any { it.kind == MapOverlay.LabelKind.Current })
                    for (label in drawn.labels) {
                        val overlap = label.frame.intersection(pip.box)
                        assertTrue("$place: ${label.text}", overlap.isNull || overlap.width < 0.01 || overlap.height < 0.01)
                    }
                    // He looks toward the dot from the side he stands on.
                    assertTrue(place, pip.side == 0 || (dot.x < pip.box.midX) == (pip.side < 0))
                    // With no clear spot he takes the one with the fewest overlaps: at most the stop beside the dot and the leg between them.
                    val crossed = drawn.legs.any { leg -> eightieths.any { away(leg.path.trimmedEnd(it) ?: dot) < 1.4 } }
                    assertTrue(place, drawn.dots.count { away(it.point) < 5.9 } + (if (crossed) 1 else 0) <= (if (journey.edge) 2 else 0))
                }
            }
        }

        // The parcel ends at the frame's edge: only a smaller Pip finds a clear spot.
        val world = journeys[3].route
        val delivered = checkNotNull(overlay(world, ParcelRoute.Mode.Journey, frames[1].size, PipRequest(PipMood.Joy, ceiling = 52.0, floor = 160.0), card).pip)
        assertTrue(delivered.width < 66)
        // With no room beside the dot he stays away, and the place keeps its name.
        val cramped = overlay(world, ParcelRoute.Mode.Journey, Size(120.0, 70.0), PipRequest(PipMood.Joy), EdgeInsets(4.0, 4.0, 4.0, 4.0))
        assertNull(cramped.pip)
        assertTrue(cramped.labels.any { it.text == "Zürich" })
        // Without him the map is as it was, and names keep off the route when a side allows it.
        val plain = overlay(journeys[1].route, ParcelRoute.Mode.Journey, frames[1].size, null, card)
        assertNull(plain.pip)
        for (label in plain.labels) {
            assertFalse(label.text, plain.legs.any { leg -> eightieths.any { label.frame.contains(leg.path.trimmedEnd(it) ?: Point.Zero) } })
        }
    }

    @Test
    fun aJourneyThatEndsAtTheEdgeMakesRoomForPip() {
        // The parcel's page, and a parcel picked up in Zürich, at the frame's edge, just past Basel.
        val size = Size(358.0, 176.0)
        val card = EdgeInsets(top = 40.0, leading = 16.0, bottom = 44.0, trailing = 16.0)
        val request = PipRequest(PipMood.Joy, ceiling = 52.0, floor = 160.0)
        fun overlay(route: ParcelRoute, camera: GlobeCamera): MapOverlay =
            mapOverlay(route, camera, size, card, labels = MapLabels.Ends, mode = ParcelRoute.Mode.Journey, context = false, pip = request)
        fun framing(route: ParcelRoute, pip: Boolean): GlobeCamera =
            GlobeCamera.framing(route, ParcelRoute.Mode.Journey, size, card, shown = { camera, _ -> overlay(route, camera).shown }, pip = pip)
        val picked = ParcelRoute(listOf(city("Shenzhen", "CN", 114.06, 22.54), leipzig, basel, city("Zürich", "CH", 8.55, 47.37)))
        val usual = framing(picked, pip = false)
        assertNull(overlay(picked, usual).pip)
        val roomy = framing(picked, pip = true)
        val drawn = overlay(picked, roomy)
        assertEquals(PipMood.Joy, checkNotNull(drawn.pip).mood)
        assertTrue(roomy.scale < usual.scale)
        assertEquals(listOf("Zürich", "Shenzhen"), drawn.labels.map { it.text })
        assertTrue(drawn.dots.all { it.point.x > 16 && it.point.x < size.width - 16 && it.point.y > 40 && it.point.y < size.height - 44 })
        // A journey he already stands beside keeps its frame.
        val beside = ParcelRoute(
            listOf(city("Berlin", "DE", 13.405, 52.52), city("Neuenstein", "DE", 9.58, 49.2), basel, city("Härkingen", "CH", 7.821, 47.305)),
        )
        assertNotNull(overlay(beside, framing(beside, pip = false)).pip)
        assertEquals(framing(beside, pip = false), framing(beside, pip = true))
    }

    @Test
    fun demoParcelsCarryPlaces() {
        val parcels = DemoRepository.seed()
        val matcha = parcels.firstOrNull { it.label.startsWith("Matcha") }
        assertNotNull(matcha)
        val route = ParcelRoute(matcha?.trackingEvents ?: emptyList()) { it }
        assertEquals("Kyoto", route.origin?.place?.name)
        assertEquals(ParcelRoute.Scale.World, route.scale)
    }
}
