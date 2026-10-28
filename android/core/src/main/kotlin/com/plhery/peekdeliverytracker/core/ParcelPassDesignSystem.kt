package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap

// From ParcelPassDesignSystem.swift: the palette, the carriers' liveries and the truck they are painted on, as data for
// :app's views and canvases. The views themselves belong to :app.

object ExperimentalPalette {
    val transit = Brand.color(light = "#22588A", dark = "#9BCBFF")
    val pickup = Brand.warning
    val delivered = Brand.color(light = "#24613E", dark = "#9BE0B5")
    val lilac = Brand.color(light = "#654299", dark = "#D1B5FF")
    val rose = Brand.color(light = "#9A3D5C", dark = "#FFADC8")
    val ochre = Brand.color(light = "#745300", dark = "#FFD65F")

    val transitSurface = Brand.color(light = "#DAEAFE", dark = "#213A51")
    val pickupSurface = Brand.color(light = "#FFDDC7", dark = "#4C3123")
    val deliveredSurface = Brand.color(light = "#DCF0D7", dark = "#243D2E")
    val lilacSurface = Brand.color(light = "#E9DEFF", dark = "#382D4C")
    val roseSurface = Brand.color(light = "#FADDE7", dark = "#482C39")
    val ochreSurface = Brand.color(light = "#FFEBAB", dark = "#443918")

    fun surface(parcel: Parcel): AdaptiveColor = when (parcel.currentStage) {
        TrackingStage.Delivered -> deliveredSurface
        TrackingStage.Customs, TrackingStage.Exception, TrackingStage.FailedAttempt, TrackingStage.ReadyForPickup,
        TrackingStage.Returned,
        -> pickupSurface
        TrackingStage.OutForDelivery -> ochreSurface
        TrackingStage.Pending, TrackingStage.Registered, null -> lilacSurface
        else -> transitSurface
    }

    fun tint(parcel: Parcel): AdaptiveColor = when (parcel.currentStage) {
        TrackingStage.Delivered -> delivered
        TrackingStage.Customs, TrackingStage.Exception, TrackingStage.FailedAttempt, TrackingStage.ReadyForPickup,
        TrackingStage.Returned,
        -> pickup
        TrackingStage.InTransit -> transit
        TrackingStage.OutForDelivery -> ochre
        TrackingStage.Pending, TrackingStage.Registered, null -> lilac
        else -> transit
    }
}

/**
 * The same bundled identities and decorations the web reads from core/brand.
 * Load once so a palette or family added to carrier.json also reaches Android.
 */
@Serializable
data class CarrierBrandAssets(
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
    data class DecalShape(
        val type: Kind,
        val points: List<List<Double>>? = null,
        val segments: List<List<List<Double>>>? = null,
        val stroke: String? = null,
        val strokeWidth: Double? = null,
        val fill: String? = null,
        val cx: Double? = null,
        val cy: Double? = null,
        val r: Double? = null,
    ) {
        @Serializable
        enum class Kind(val rawValue: String) {
            @SerialName("line") Line("line"),
            @SerialName("polygon") Polygon("polygon"),
            @SerialName("circle") Circle("circle"),
        }

        val paint: String get() = if (type == Kind.Line) stroke!! else fill!!

        /** A new path each time: :app replays it and may keep it, but never adds to it. */
        val path: MapPath
            get() {
                if (type == Kind.Circle) return MapPath.ellipse(Rect(cx!! - r!!, cy!! - r!!, r!! * 2, r!! * 2))
                val path = MapPath()
                for (line in if (type == Kind.Line) segments!! else listOf(points!!)) {
                    val first = line.firstOrNull() ?: continue
                    path.move(first[0], first[1])
                    for (point in line.drop(1)) path.addLine(point[0], point[1])
                    if (type == Kind.Polygon) path.closeSubpath()
                }
                return path
            }
    }

    @Serializable
    data class Truck(val decals: Map<String, List<DecalShape>>)

    companion object {
        /** Brand.json is bundled with the app: unreadable, it fails at once, as on the iPhone. */
        val shared: CarrierBrandAssets by lazy {
            Json { ignoreUnknownKeys = true }.decodeFromString(serializer(), SharedResources.text("Brand.json"))
        }
    }
}

class CarrierVisualIdentity(id: String, carrier: CarrierDefinition) {
    val family: String
    val name: String
    val fullName: String
    val colors: List<String>
    val decal: String

    init {
        val assets = CarrierBrandAssets.shared
        family = assets.families[id] ?: id
        fullName = carrier.displayName
        name = mapOf("dhl" to "DHL", "gls" to "GLS", "ups" to "ups")[family] ?: carrier.displayName
        decal = assets.decals[id] ?: "default"
        colors = assets.derivation.map { step -> assets.palettes[id]?.get(step.property) ?: mix(carrier.color, step.base, step.amount) }
    }

    /** One of the livery's named colours (`truck`, `accent`…), or else a colour literal. */
    fun paint(value: String): RgbaColor {
        val index = CarrierBrandAssets.shared.properties.indexOf(value)
        if (index < 0) return RgbaColor.hex(value)
        return RgbaColor.hex(colors[index])
    }

    val surface: AdaptiveColor get() = Brand.color(light = colors[0], dark = colors[1])
    val ink: AdaptiveColor get() = Brand.color(light = colors[2], dark = colors[3])
    val brand: AdaptiveColor get() = Brand.color(light = colors[4], dark = colors[5])
    val truck: RgbaColor get() = RgbaColor.hex(colors[6])
    val edge: RgbaColor get() = RgbaColor.hex(colors[7])
    val accent: RgbaColor get() = RgbaColor.hex(colors[8])

    companion object {
        /** The colour of a carrier the brand data does not name (Brand.json's `defaultColor`). */
        val defaultCarrierColor: String get() = CarrierBrandAssets.shared.defaultColor
        val fallbackColor: String get() = CarrierBrandAssets.shared.fallbackColor

        private val cache = ConcurrentHashMap<String, CarrierVisualIdentity>()

        /** Cards rebuild whenever the list changes; derive each carrier's livery once. */
        fun of(carrier: CarrierId, catalog: CarrierCatalog = CarrierCatalog.shared, language: AppLanguage): CarrierVisualIdentity {
            val definition = catalog.info(carrier, language)
            val key = "${carrier.rawValue}|${definition.displayName}|${definition.color}"
            return cache.getOrPut(key) { CarrierVisualIdentity(carrier.rawValue, definition) }
        }

        /**
         * `color` mixed `amount` of the way toward `base`, as "#rrggbb". A colour that is not seven characters long, or
         * not hexadecimal after its first, is the fallback colour.
         */
        fun mix(color: String, base: String, amount: Double): String {
            val fallback = hexValue(fallbackColor) ?: 0u
            val value = if (color.characterCount() == 7) hexValue(color) ?: fallback else fallback
            val background = hexValue(base) ?: 0u
            val channels = listOf(16, 8, 0).map { shift ->
                (((value shr shift) and 255u).toDouble() * (1 - amount) + ((background shr shift) and 255u).toDouble() * amount)
                    .rounded().toInt()
            }
            return String.format(Locale.ROOT, "#%02x%02x%02x", channels[0], channels[1], channels[2])
        }

        /**
         * `UInt32(text.dropFirst(), radix: 16)`: what follows the first Character, as Swift reads an unsigned number:
         * an optional sign, then ASCII hexadecimal digits only; nil when empty, negative or too large.
         */
        private fun hexValue(text: String): UInt? {
            val digits = text.characters().drop(1).joinToString("")
            var index = 0
            var negative = false
            if (digits.startsWith("+") || digits.startsWith("-")) {
                negative = digits[0] == '-'
                index = 1
            }
            if (index == digits.length) return null
            var value = 0uL
            while (index < digits.length) {
                val digit = when (val char = digits[index]) {
                    in '0'..'9' -> char - '0'
                    in 'a'..'f' -> char - 'a' + 10
                    in 'A'..'F' -> char - 'A' + 10
                    else -> return null
                }
                value = value * 16u + digit.toULong()
                if (value > UInt.MAX_VALUE.toULong()) return null
                index++
            }
            if (negative && value != 0uL) return null
            return value.toUInt()
        }
    }
}

/**
 * The one truck, as `src/brand/truck.json` states it. The web draws the same numbers as an SVG; `BrandParityTests`
 * asserts every value here against Brand.json, so neither drawing can drift.
 *
 * The canvas fills straight-line outlines where the SVG uses a path: the UPS shield approximates its curve with five
 * points. Decorations are loaded from the bundled geometry rather than retyped as per-carrier constants.
 */
object CarrierTruckGeometry {
    val viewBox = Size(32.0, 21.0)
    const val strokeWidth = 0.6
    val body = Rect(2.0, 3.0, 18.0, 13.0)
    const val bodyCornerRadius = 1.3
    val cab = listOf(Point(20.0, 8.0), Point(25.0, 8.0), Point(30.0, 13.0), Point(30.0, 16.0), Point(20.0, 16.0))
    val windshield = listOf(Point(22.0, 9.5), Point(24.5, 9.5), Point(27.5, 12.5), Point(22.0, 12.5))
    const val windshieldColor = "#edf1ee"
    val wheelCenters = listOf(Point(8.0, 16.5), Point(25.0, 16.5))
    const val tireRadius = 2.4
    const val tireColor = "#42483d"
    const val hubRadius = 0.9
    const val hubColor = "#d2d4c7"

    /**
     * The truck in a carrier's livery, as steps for :app's canvas, in its own 32 × 21 units: :app scales its canvas to
     * the size wanted first (27 × 18 points for `CarrierTruckMark`, which is hidden from accessibility).
     */
    fun draw(identity: CarrierVisualIdentity): List<MapDrawing> {
        val steps = mutableListOf<MapDrawing>()
        val truck = AdaptiveColor(identity.truck)
        val edge = AdaptiveColor(identity.edge)
        val body = MapPath.roundedRect(body, bodyCornerRadius)
        steps += MapDrawing.Fill(body, truck)
        steps += MapDrawing.Stroke(body, edge, StrokeStyle(strokeWidth))
        val cab = polygon(cab)
        steps += MapDrawing.Fill(cab, truck)
        steps += MapDrawing.Stroke(cab, edge, StrokeStyle(strokeWidth))
        steps += MapDrawing.Fill(polygon(windshield), AdaptiveColor(RgbaColor.hex(windshieldColor)))
        val decals = CarrierBrandAssets.shared.truck.decals
        for (shape in decals[identity.decal] ?: decals.getValue("default")) {
            val paint = AdaptiveColor(identity.paint(shape.paint))
            steps += if (shape.type == CarrierBrandAssets.DecalShape.Kind.Line) {
                MapDrawing.Stroke(shape.path, paint, StrokeStyle(shape.strokeWidth!!))
            } else {
                MapDrawing.Fill(shape.path, paint)
            }
        }
        for (center in wheelCenters) {
            steps += MapDrawing.Fill(disc(center, tireRadius), AdaptiveColor(RgbaColor.hex(tireColor)))
            steps += MapDrawing.Fill(disc(center, hubRadius), AdaptiveColor(RgbaColor.hex(hubColor)))
        }
        return steps
    }

    private fun polygon(points: List<Point>): MapPath = MapPath().also { path ->
        val first = points.firstOrNull() ?: return@also
        path.move(first)
        for (point in points.drop(1)) path.addLine(point)
        path.closeSubpath()
    }

    private fun disc(center: Point, radius: Double): MapPath =
        MapPath.ellipse(Rect(center.x - radius, center.y - radius, radius * 2, radius * 2))
}
