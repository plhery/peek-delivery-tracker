package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * BrandParityTests.swift. The carrier livery is declared once, in `src/brand`, and shipped to the app as Brand.json.
 * The identity and the truck keep their own code, so this replays the shared definition against every constant they
 * draw. Drift fails here, not on screen.
 */
class BrandParityTests {
    @Serializable
    private data class BrandDefinition(
        val defaultColor: String,
        val fallbackColor: String,
        val properties: List<String>,
        val derivation: List<Step>,
        val toneExamples: List<List<String>>,
        val litEdge: LitEdge,
        /** `[truck, surface-dark, edge, expected]` rows for the truck's outline on its dark card, which the web checks too. */
        val litEdgeExamples: List<List<String>>,
        val families: Map<String, String>,
        val palettes: Map<String, Map<String, String>>,
        val decals: Map<String, String>,
        val truck: Truck,
    ) {
        @Serializable
        data class Step(val property: String, val base: String, val amount: Double, val tone: Tone? = null)

        @Serializable
        data class Tone(val lightness: Double, val chroma: Double, val maxChroma: Double, val amber: Amber) {
            @Serializable
            data class Amber(val from: Double, val to: Double, val hue: Double, val slope: Double)
        }

        @Serializable
        data class LitEdge(val below: Double, val lift: Double, val floor: Double, val maxChroma: Double)

        @Serializable
        data class Truck(
            val viewBox: ViewBox,
            val strokeWidth: Double,
            val body: Body,
            val cab: Panel,
            val windshield: Panel,
            val wheels: Wheels,
            val decals: Map<String, List<Shape>>,
        ) {
            @Serializable
            data class ViewBox(val width: Double, val height: Double)

            @Serializable
            data class Body(
                val x: Double,
                val y: Double,
                val width: Double,
                val height: Double,
                val rx: Double,
                val fill: String,
                val stroke: String,
            )

            /** `dark` is the glass's paint in dark. */
            @Serializable
            data class Panel(
                val d: String,
                val points: List<List<Double>>,
                val fill: String,
                val stroke: String? = null,
                val dark: String? = null,
            )

            @Serializable
            data class Wheels(val centers: List<List<Double>>, val tire: Tire, val hub: Disc) {
                @Serializable
                data class Disc(val r: Double, val fill: String)

                @Serializable
                data class Tire(
                    val r: Double,
                    val fill: String,
                    val dark: String,
                    val rim: String,
                    val rimOpacity: Double,
                    val rimWidth: Double,
                )
            }

            @Serializable
            data class Shape(
                val type: String,
                val d: String? = null,
                val points: List<List<Double>>? = null,
                val segments: List<List<List<Double>>>? = null,
                val stroke: String? = null,
                val strokeWidth: Double? = null,
                val fill: String? = null,
                val cx: Double? = null,
                val cy: Double? = null,
                val r: Double? = null,
            )
        }
    }

    // The bundled rules, without inheriting a previously cached live catalog.
    private val catalog = CarrierCatalog(cacheFile = null)

    private val json = Json { ignoreUnknownKeys = true }

    private fun brand(): BrandDefinition = json.decodeFromString(BrandDefinition.serializer(), SharedResources.text("Brand.json"))

    private fun identity(id: String): CarrierVisualIdentity {
        val definition = catalog.definitions[CarrierId(id)]
        assertNotNull("$id is not in the catalog", definition)
        return CarrierVisualIdentity(id, definition!!)
    }

    private fun points(pairs: List<List<Double>>): List<Point> = pairs.map { Point(it[0], it[1]) }

    @Test
    fun declaredPalettesFamiliesAndLiveriesMatchTheCarrierFolders() {
        val brand = brand()
        assertEquals(9, brand.properties.size)
        assertFalse(brand.palettes.isEmpty())
        for ((id, palette) in brand.palettes) {
            val colors = identity(id).colors
            assertEquals("$id resolves ${colors.size} colors", brand.properties.size, colors.size)
            for ((index, property) in brand.properties.withIndex()) {
                val declared = palette[property] ?: continue
                assertEquals("$id $property", declared, colors[index])
            }
        }
        for (id in catalog.definitions.keys.map { it.rawValue }) {
            assertEquals("$id livery", brand.decals[id] ?: "default", identity(id).decal)
        }
        for ((id, family) in brand.families) {
            assertEquals("$id family", family, identity(id).family)
        }
        for (name in brand.decals.values) {
            assertNotNull("$name is not a truck livery", brand.truck.decals[name])
        }
    }

    @Test
    fun carriersWithoutAPaletteUseTheSharedMixAmounts() {
        val brand = brand()
        val sampled = listOf("swiss-post", "fedex", "dpd", "la-poste").filter { brand.palettes[it] == null }
        assertFalse(sampled.isEmpty())
        for (id in sampled) {
            val color = catalog.definitions.getValue(CarrierId(id)).color
            val colors = identity(id).colors
            assertEquals(brand.derivation.size, colors.size)
            for ((index, step) in brand.derivation.withIndex()) {
                val mixed = CarrierVisualIdentity.mix(color, step.base, step.amount)
                val expected = step.tone?.let { CarrierVisualIdentity.tone(mixed, it.shared()) } ?: mixed
                assertEquals("$id ${step.property}", expected, colors[index])
                assertEquals(brand.properties[index], step.property)
            }
        }
    }

    @Test
    fun darkCardsSettleAtOneLightnessAsTheSharedExamplesDo() {
        val brand = brand()
        val step = brand.derivation.single { it.property == "surface-dark" }
        assertNotNull("surface-dark has no tone", step.tone)
        val tone = step.tone!!.shared()
        // The app decodes the same tone the brand data declares.
        assertEquals(tone, CarrierBrandAssets.shared.derivation.single { it.property == "surface-dark" }.tone)
        assertFalse(brand.toneExamples.isEmpty())
        for ((input, expected) in brand.toneExamples) {
            assertEquals(input, expected, CarrierVisualIdentity.tone(input, tone))
        }
        // The other properties are mixed only.
        assertTrue(brand.derivation.filter { it.property != "surface-dark" }.all { it.tone == null })
    }

    @Test
    fun aDarkCardIsNeverBrighterThanItsMixSoItsWordsKeepTheirContrast() {
        val brand = brand()
        val index = brand.properties.indexOf("surface-dark")
        val step = brand.derivation[index]
        for ((id, definition) in catalog.definitions) {
            val colors = identity(id.rawValue).colors
            val declared = brand.palettes[id.rawValue]?.get("surface-dark")
            val mixed = declared ?: CarrierVisualIdentity.mix(definition.color, step.base, step.amount)
            val surface = CarrierVisualIdentity.luminance(colors[index])
            assertTrue(id.rawValue, surface <= CarrierVisualIdentity.luminance(mixed))
            assertTrue(id.rawValue, CarrierVisualIdentity.luminance(colors[brand.properties.indexOf("ink-dark")]) > surface)
            assertTrue(id.rawValue, CarrierVisualIdentity.luminance(colors[brand.properties.indexOf("brand-dark")]) > surface)
        }
    }

    private fun BrandDefinition.Tone.shared() = CarrierBrandAssets.Tone(
        lightness, chroma, maxChroma, CarrierBrandAssets.Tone.Amber(amber.from, amber.to, amber.hue, amber.slope),
    )

    @Test
    fun namedColorsMatchTheSharedBrand() {
        val brand = brand()
        assertEquals(brand.defaultColor, CarrierVisualIdentity.defaultCarrierColor)
        assertEquals(brand.fallbackColor, CarrierVisualIdentity.fallbackColor)
        // A catalog color that is not a #rrggbb literal resolves to the fallback, not to black.
        assertEquals(brand.fallbackColor, CarrierVisualIdentity.mix("not-a-color", "#000000", 0.0))
        assertEquals(brand.defaultColor, catalog.definitions[CarrierId.Unknown]?.color)
    }

    @Test
    fun truckGeometryMatchesTheSharedDefinition() {
        val truck = brand().truck
        assertEquals(Size(truck.viewBox.width, truck.viewBox.height), CarrierTruckGeometry.viewBox)
        assertEquals(truck.strokeWidth, CarrierTruckGeometry.strokeWidth, 0.0)
        assertEquals(Rect(truck.body.x, truck.body.y, truck.body.width, truck.body.height), CarrierTruckGeometry.body)
        assertEquals(truck.body.rx, CarrierTruckGeometry.bodyCornerRadius, 0.0)
        assertEquals(points(truck.cab.points), CarrierTruckGeometry.cab)
        assertEquals(points(truck.windshield.points), CarrierTruckGeometry.windshield)
        assertEquals(truck.windshield.fill, CarrierTruckGeometry.windshieldColor)
        assertEquals(points(truck.wheels.centers), CarrierTruckGeometry.wheelCenters)
        assertEquals(truck.wheels.tire.r, CarrierTruckGeometry.tireRadius, 0.0)
        assertEquals(truck.wheels.tire.fill, CarrierTruckGeometry.tireColor)
        assertEquals(truck.wheels.hub.r, CarrierTruckGeometry.hubRadius, 0.0)
        assertEquals(truck.wheels.hub.fill, CarrierTruckGeometry.hubColor)
        // The body and cab wear the identity's own colors; the glass is a literal.
        assertEquals(listOf("truck", "edge", "truck", "edge"), listOf(truck.body.fill, truck.body.stroke, truck.cab.fill, truck.cab.stroke))
        // In dark the tyre is the shared dark paint with its rim.
        assertEquals(truck.wheels.tire.dark, CarrierTruckGeometry.tireDarkColor)
        assertEquals(truck.wheels.tire.rim, CarrierTruckGeometry.tireRimColor)
        assertEquals(truck.wheels.tire.rimOpacity, CarrierTruckGeometry.tireRimOpacity, 0.0)
        assertEquals(truck.wheels.tire.rimWidth, CarrierTruckGeometry.tireRimWidth, 0.0)
        assertEquals(truck.windshield.dark, CarrierTruckGeometry.windshieldDarkColor)
    }

    @Test
    fun darkTrucksLightTheirOutlineAsTheSharedExamples() {
        val brand = brand()
        val rule = CarrierBrandAssets.shared.litEdge
        assertEquals(
            listOf(brand.litEdge.below, brand.litEdge.lift, brand.litEdge.floor, brand.litEdge.maxChroma),
            listOf(rule.below, rule.lift, rule.floor, rule.maxChroma),
        )
        assertFalse(brand.litEdgeExamples.isEmpty())
        for (example in brand.litEdgeExamples) {
            assertEquals(4, example.size)
            assertEquals(example[0], example[3], CarrierVisualIdentity.litEdge(example[0], example[1], example[2], rule))
        }
    }

    /**
     * On its dark card every carrier's truck keeps its outline, glass and tyres at least as visible as the light paints
     * would leave them: the outline against the card, the glass against the body, a tyre's fill or rim against the body
     * and the card. Only a white body is lighter than the glass, and it loses less than 0.001 to it.
     */
    @Test
    fun darkGlassAndTyresStandOutAsMuchAsTheLightOnes() {
        val truck = CarrierTruckGeometry
        fun contrast(first: String, second: String) = CarrierVisualIdentity.contrast(first, second)
        fun rim(ground: String) = CarrierVisualIdentity.mix(ground, truck.tireRimColor, truck.tireRimOpacity)
        assertTrue(catalog.definitions.size > 1000)
        for (id in catalog.definitions.keys.map { it.rawValue }) {
            val identity = identity(id)
            val colors = identity.colors
            val (card, body, edge) = Triple(colors[1], colors[6], colors[7])
            val lit = CarrierVisualIdentity.litEdge(body, card, edge)
            assertEquals(id, RgbaColor.hex(lit), identity.edgeInDark)
            assertTrue("$id's outline", contrast(lit, card) >= contrast(edge, card))
            assertTrue("$id's glass", contrast(truck.windshieldDarkColor, body) >= contrast(truck.windshieldColor, body) - 0.001)
            for ((name, ground) in listOf("card" to card, "body" to body)) {
                val shown = Math.max(contrast(truck.tireDarkColor, ground), contrast(rim(ground), ground))
                assertTrue("$id's tyre on its $name", shown >= contrast(truck.tireColor, ground))
            }
        }
    }

    @Test
    fun decalGeometryMatchesTheSharedDefinition() {
        val expected = brand().truck.decals
        val actual = CarrierBrandAssets.shared.truck.decals
        assertEquals(expected.keys, actual.keys)
        for ((name, shapes) in expected) {
            val rendered = actual.getValue(name)
            assertEquals(name, shapes.size, rendered.size)
            for ((shape, drawing) in shapes.zip(rendered)) {
                assertEquals(name, shape.type, drawing.type.rawValue)
                assertEquals(name, shape.points, drawing.points)
                assertEquals(name, shape.segments, drawing.segments)
                assertEquals(name, shape.strokeWidth, drawing.strokeWidth)
                assertEquals(name, shape.cx, drawing.cx)
                assertEquals(name, shape.cy, drawing.cy)
                assertEquals(name, shape.r, drawing.r)
                assertEquals(name, if (shape.type == "line") shape.stroke else shape.fill, drawing.paint)
                assertFalse(name, drawing.path.isEmpty)
                val bounds = drawing.path.boundingBoxOfPath
                assertTrue(name, bounds.minX >= 2)
                assertTrue(name, bounds.maxX <= 20)
                assertTrue(name, bounds.minY >= 3)
                assertTrue(name, bounds.maxY <= 16)
            }
        }
    }

    @Test
    fun carriersWithoutAVerifiedBrandColorStayNeutral() {
        val brand = brand()
        val neutral = catalog.definitions.filter { it.value.color == CarrierVisualIdentity.defaultCarrierColor }
        assertEquals(setOf(CarrierId.Omgo, CarrierId.Unknown), neutral.keys)
        for (id in neutral.keys) {
            assertNull(id.rawValue, brand.palettes[id.rawValue])
            assertEquals(id.rawValue, "default", identity(id.rawValue).decal)
        }
    }

    @Test
    fun theTruckIsDrawnInTheCarriersLiveryBackToFront() {
        val identity = identity("dhl")
        val steps = CarrierTruckGeometry.draw(identity, dark = false)
        val decals = CarrierBrandAssets.shared.truck.decals.getValue(identity.decal)
        // Body and cab, filled then outlined, the glass, the decals, then a tire and its hub for each wheel.
        assertEquals(5 + decals.size + 4, steps.size)
        val body = steps[0] as MapDrawing.Fill
        assertEquals(AdaptiveColor(identity.truck), body.color)
        assertEquals(CarrierTruckGeometry.body, body.path.boundingBoxOfPath)
        // `Path(roundedRect:cornerRadius:)`, continuous, as SwiftUI gives it (from a swiftc harness on the Mac).
        assertPathElements(
            listOf(
                "M 20 9.5", "L 20 14.012735569477082",
                "C 20 14.584962987899781 20 14.871070885658265 19.902615179121494 15.179057818651199",
                "C 19.780221991240978 15.51532878279686 19.51532878279686 15.780221991240978 19.179057818651199 15.902615179121494",
                "C 18.871070885658263 16 18.584962987899779 16 18.01273556947708 16", "L 3.9872644305229188 16",
                "C 3.4150370121002198 16 3.1289291143417359 16 2.8209421813488005 15.902615179121494",
                "C 2.4846712172031404 15.780221991240978 2.2197780087590218 15.51532878279686 2.0973848208785055 15.179057818651199",
                "C 2 14.871070885658265 2 14.584962987899781 2 14.012735569477082", "L 2 4.9872644305229183",
                "C 2 4.4150370121002194 2 4.1289291143417355 2.0973848208785055 3.8209421813488005",
                "C 2.2197780087590218 3.4846712172031404 2.4846712172031404 3.2197780087590218 2.8209421813488005 3.0973848208785055",
                "C 3.1289291143417359 3 3.4150370121002198 3 3.9872644305229188 3", "L 18.01273556947708 3",
                "C 18.584962987899779 3 18.871070885658263 3 19.179057818651199 3.0973848208785055",
                "C 19.51532878279686 3.2197780087590218 19.780221991240978 3.4846712172031404 19.902615179121494 3.8209421813488005",
                "C 20 4.1289291143417355 20 4.4150370121002194 20 4.9872644305229183", "Z",
            ),
            body.path,
        )
        val outline = steps[1] as MapDrawing.Stroke
        assertEquals(AdaptiveColor(identity.edge), outline.color)
        assertEquals(CarrierTruckGeometry.strokeWidth, outline.style.lineWidth, 0.0)
        // Every outline mitres its corners up to 10 line widths before it bevels, as CoreGraphics does, rather than at
        // Android's own limit of 4.
        for (step in steps.filterIsInstance<MapDrawing.Stroke>()) {
            assertEquals(StrokeStyle.LineJoin.Miter, step.style.lineJoin)
            assertEquals(10.0, step.style.miterLimit, 0.0)
        }
        assertEquals(AdaptiveColor(RgbaColor.hex(CarrierTruckGeometry.windshieldColor)), (steps[4] as MapDrawing.Fill).color)
        for ((index, shape) in decals.withIndex()) {
            val step = steps[5 + index]
            assertEquals(shape.type == CarrierBrandAssets.DecalShape.Kind.Line, step is MapDrawing.Stroke)
        }
        val hub = steps.last() as MapDrawing.Fill
        assertEquals(AdaptiveColor(RgbaColor.hex(CarrierTruckGeometry.hubColor)), hub.color)
        assertEquals(25.0, hub.path.boundingBoxOfPath.midX, 1e-12)
        assertEquals(1.8, hub.path.boundingBoxOfPath.width, 1e-12)
        assertEquals(AdaptiveColor(RgbaColor.hex(CarrierTruckGeometry.tireColor)), (steps[steps.size - 2] as MapDrawing.Fill).color)
    }

    @Test
    fun aDarkTruckLightsItsOutlineGlassAndTyres() {
        // UPS's brown body sinks into its dark card, so its outline is lit; DHL's yellow keeps its own edge.
        val ups = identity("ups")
        val steps = CarrierTruckGeometry.draw(ups, dark = true)
        val decals = CarrierBrandAssets.shared.truck.decals.getValue(ups.decal)
        // As in light, with a rim stroked round each tyre between the tyre and its hub.
        assertEquals(5 + decals.size + 6, steps.size)
        assertEquals(AdaptiveColor(ups.truck), (steps[0] as MapDrawing.Fill).color)
        assertEquals(AdaptiveColor(RgbaColor.hex("#c29b88")), (steps[1] as MapDrawing.Stroke).color)
        assertEquals(AdaptiveColor(RgbaColor.hex("#c29b88")), (steps[3] as MapDrawing.Stroke).color)
        assertEquals(AdaptiveColor(RgbaColor.hex(CarrierTruckGeometry.windshieldDarkColor)), (steps[4] as MapDrawing.Fill).color)
        val wheel = steps.takeLast(3)
        assertEquals(AdaptiveColor(RgbaColor.hex(CarrierTruckGeometry.tireDarkColor)), (wheel[0] as MapDrawing.Fill).color)
        val rim = wheel[1] as MapDrawing.Stroke
        assertEquals(AdaptiveColor(RgbaColor.hex(CarrierTruckGeometry.tireRimColor).opacity(CarrierTruckGeometry.tireRimOpacity)), rim.color)
        assertEquals(CarrierTruckGeometry.tireRimWidth, rim.style.lineWidth, 0.0)
        assertEquals((wheel[0] as MapDrawing.Fill).path.boundingBoxOfPath, rim.path.boundingBoxOfPath)
        assertEquals(AdaptiveColor(RgbaColor.hex(CarrierTruckGeometry.hubColor)), (wheel[2] as MapDrawing.Fill).color)
        val dhl = identity("dhl")
        assertEquals(AdaptiveColor(dhl.edge), (CarrierTruckGeometry.draw(dhl, dark = true)[1] as MapDrawing.Stroke).color)
    }

    // Not an iPhone test: `Color.mix(with:by:)` mixes in OKLab unless Swift passes `.device`, each colour weighted by its
    // opacity (SwiftUI's resolved colours, from a swiftc harness on the Mac, which keeps them in Float).
    @Test
    fun colorsMixAsSwiftUIMixesThemInEachSpace() {
        fun assertColor(expected: List<Double>, actual: RgbaColor, accuracy: Double) {
            for ((wanted, value) in expected.zip(listOf(actual.red, actual.green, actual.blue, actual.opacity))) {
                assertEquals("$actual", wanted, value, accuracy)
            }
        }
        val surface = RgbaColor.hex("#243D2E")
        val ink = RgbaColor.hex("#9BE0B5")
        assertColor(listOf(0.233976245, 0.366837889, 0.285870612, 1.0), surface.mix(ink, 0.22), 1e-5)
        assertColor(listOf(0.243843138, 0.379843086, 0.296862692, 1.0), surface.mix(ink, 0.22, GradientColorSpace.Device), 1e-6)
        val red = RgbaColor(1.0, 0.0, 0.0)
        val blue = RgbaColor(0.0, 0.0, 1.0)
        assertColor(listOf(0.550440967, 0.325620681, 0.636500657, 1.0), red.mix(blue, 0.5), 1e-5)
        assertColor(listOf(0.5, 0.0, 0.5, 1.0), red.mix(blue, 0.5, GradientColorSpace.Device), 1e-6)
        assertColor(listOf(0.388572842, 0.388572842, 0.388572842, 1.0), RgbaColor(1.0, 1.0, 1.0).mix(RgbaColor(0.0, 0.0, 0.0), 0.5), 1e-5)
        // A clear colour lends no colour, only its opacity.
        val clear = RgbaColor.hex("#5B794A").opacity(0.0)
        assertColor(listOf(0.525490224, 0.0588229969, 0.14509806, 0.389999986), RgbaColor.hex("#860F25").opacity(0.5).mix(clear, 0.22), 1e-5)
        // Each appearance mixes on its own, in the space asked for.
        val adaptive = Brand.color(light = "#FFFFFF", dark = "#243D2E").mix(Brand.color(light = "#000000", dark = "#9BE0B5"), 0.22)
        assertEquals(surface.mix(ink, 0.22), adaptive.dark)
        assertEquals(RgbaColor(1.0, 1.0, 1.0).mix(RgbaColor(0.0, 0.0, 0.0), 0.22), adaptive.light)

        // The stamp's globe mixes the livery's surface toward its ink perceptually; its paper is warm white in light mode
        // and a cream in dark mode, whatever the carrier.
        val identity = identity("dhl")
        assertEquals(identity.surface.mix(identity.ink, 0.14), identity.stampGlobe)
        assertNotEquals(identity.surface.mix(identity.ink, 0.14, GradientColorSpace.Device), identity.stampGlobe)
        assertEquals(RgbaColor.hex("#FFFEFA"), identity.stampPaper.resolve(dark = false))
        assertEquals(RgbaColor.hex("#FFFEFA"), identity.stampPaper.resolve(dark = false, highContrast = true))
        assertEquals(RgbaColor.hex("#D9D0BC"), identity.stampPaper.resolve(dark = true))
        assertEquals(RgbaColor.hex("#D9D0BC"), identity.stampPaper.resolve(dark = true, highContrast = true))
        assertEquals(identity.stampPaper, identity("ups").stampPaper)
    }

    // Not an iPhone test: `Color(hex:)` trims what is not a letter, mark or number from both ends, then reads ASCII
    // hexadecimal digits only, into 64 bits; anything else is black (SwiftUI's answers, from a swiftc harness on the Mac).
    @Test
    fun aHexColorReadsOnlyAsciiDigits() {
        fun bytes(hex: String): List<Long> = RgbaColor.hex(hex).let { listOf(it.red, it.green, it.blue).map { value -> Math.round(value * 255) } }
        val black = listOf(0L, 0L, 0L)
        assertEquals(listOf(18L, 52L, 86L), bytes("#123456"))
        assertEquals(listOf(32L, 37L, 30L), bytes("  #20251E  "))
        assertEquals(listOf(32L, 37L, 30L), bytes("#20251E!"))
        assertEquals(listOf(1L, 35L, 69L), bytes("#12345"))
        assertEquals(listOf(35L, 69L, 103L), bytes("#1234567"))
        assertEquals(listOf(52L, 86L, 120L), bytes("#12345678"))
        assertEquals(listOf(255L, 255L, 255L), bytes("#FFFFFFFFFFFFFFFF"))
        assertEquals("past 64 bits", black, bytes("#FFFFFFFFFFFFFFFFF"))
        assertEquals(black, bytes("#10000000000000000"))
        assertEquals("a sign is trimmed with the #", listOf(0L, 0L, 18L), bytes("#+-12"))
        assertEquals(black, bytes("#12-34"))
        assertEquals(black, bytes("#12g"))
        assertEquals(black, bytes(""))
        assertEquals(black, bytes("#"))
        assertEquals("full-width digits", black, bytes("#\uFF11\uFF12\uFF13\uFF14\uFF15\uFF16"))
        assertEquals("Arabic-Indic digits", black, bytes("#\u0661\u0662\u0663\u0664\u0665\u0666"))
        assertEquals("full-width letters", black, bytes("#\uFF26\uFF260000"))
        assertEquals("superscripts", black, bytes("#\u00B2\u00B3"))
        assertEquals("a trailing combining mark is kept, and unreadable", black, bytes("#123456\u0301"))
        assertEquals("a zero-width space is trimmed", listOf(18L, 52L, 86L), bytes("#123456\u200B"))
        assertEquals("an emoji is trimmed", listOf(18L, 52L, 86L), bytes("#123456\uD83D\uDE00"))
    }
}
