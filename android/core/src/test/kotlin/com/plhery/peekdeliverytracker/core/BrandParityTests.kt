package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
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
        val families: Map<String, String>,
        val palettes: Map<String, Map<String, String>>,
        val decals: Map<String, String>,
        val truck: Truck,
    ) {
        @Serializable
        data class Step(val property: String, val base: String, val amount: Double)

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

            @Serializable
            data class Panel(val d: String, val points: List<List<Double>>, val fill: String, val stroke: String? = null)

            @Serializable
            data class Wheels(val centers: List<List<Double>>, val tire: Disc, val hub: Disc) {
                @Serializable
                data class Disc(val r: Double, val fill: String)
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
                assertEquals("$id ${step.property}", CarrierVisualIdentity.mix(color, step.base, step.amount), colors[index])
                assertEquals(brand.properties[index], step.property)
            }
        }
    }

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
        val steps = CarrierTruckGeometry.draw(identity)
        val decals = CarrierBrandAssets.shared.truck.decals.getValue(identity.decal)
        // Body and cab, filled then outlined, the glass, the decals, then a tire and its hub for each wheel.
        assertEquals(5 + decals.size + 4, steps.size)
        val body = steps[0] as MapDrawing.Fill
        assertEquals(AdaptiveColor(identity.truck), body.color)
        assertEquals(CarrierTruckGeometry.body, body.path.boundingBoxOfPath)
        val outline = steps[1] as MapDrawing.Stroke
        assertEquals(AdaptiveColor(identity.edge), outline.color)
        assertEquals(CarrierTruckGeometry.strokeWidth, outline.style.lineWidth, 0.0)
        assertEquals(AdaptiveColor(RgbaColor.hex(CarrierTruckGeometry.windshieldColor)), (steps[4] as MapDrawing.Fill).color)
        for ((index, shape) in decals.withIndex()) {
            val step = steps[5 + index]
            assertEquals(shape.type == CarrierBrandAssets.DecalShape.Kind.Line, step is MapDrawing.Stroke)
        }
        val hub = steps.last() as MapDrawing.Fill
        assertEquals(AdaptiveColor(RgbaColor.hex(CarrierTruckGeometry.hubColor)), hub.color)
        assertEquals(25.0, hub.path.boundingBoxOfPath.midX, 1e-12)
        assertEquals(1.8, hub.path.boundingBoxOfPath.width, 1e-12)
    }
}
