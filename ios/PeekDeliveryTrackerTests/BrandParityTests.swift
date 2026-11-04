import SwiftUI
import XCTest
@testable import PeekDeliveryTracker

/// The carrier livery is declared once, in `src/brand`, and
/// shipped to the app as `Resources/Brand.json`. The SwiftUI identity and canvas
/// keep their own code — a `Canvas` is not an SVG — so this replays the shared
/// definition against every constant they draw. Drift fails here, not on screen.
final class BrandParityTests: XCTestCase {
    private struct BrandDefinition: Decodable {
        struct Step: Decodable {
            struct Tone: Decodable {
                struct Amber: Decodable {
                    let from: Double
                    let to: Double
                    let hue: Double
                    let slope: Double
                }

                let lightness: Double
                let chroma: Double
                let maxChroma: Double
                let amber: Amber
            }

            let property: String
            let base: String
            let amount: Double
            let tone: Tone?
        }

        struct Truck: Decodable {
            struct ViewBox: Decodable {
                let width: Double
                let height: Double
            }

            struct Body: Decodable {
                let x: Double
                let y: Double
                let width: Double
                let height: Double
                let rx: Double
                let fill: String
                let stroke: String
            }

            struct Panel: Decodable {
                let d: String
                let points: [[Double]]
                let fill: String
                let stroke: String?
                /// The glass's paint in dark.
                let dark: String?
            }

            struct Wheels: Decodable {
                struct Disc: Decodable {
                    let r: Double
                    let fill: String
                }

                struct Tire: Decodable {
                    let r: Double
                    let fill: String
                    let dark: String
                    let rim: String
                    let rimOpacity: Double
                    let rimWidth: Double
                }

                let centers: [[Double]]
                let tire: Tire
                let hub: Disc
            }

            struct Shape: Decodable {
                let type: String
                let d: String?
                let points: [[Double]]?
                let segments: [[[Double]]]?
                let stroke: String?
                let strokeWidth: Double?
                let fill: String?
                let cx: Double?
                let cy: Double?
                let r: Double?
            }

            let viewBox: ViewBox
            let strokeWidth: Double
            let body: Body
            let cab: Panel
            let windshield: Panel
            let wheels: Wheels
            let decals: [String: [Shape]]
        }

        struct LitEdge: Decodable {
            let below: Double
            let lift: Double
            let floor: Double
            let maxChroma: Double
        }

        let defaultColor: String
        let fallbackColor: String
        let properties: [String]
        let derivation: [Step]
        /// `[input, expected]` pairs for the dark cards' tone, which the web checks too.
        let toneExamples: [[String]]
        let litEdge: LitEdge
        /// `[truck, surface-dark, edge, expected]` rows for the truck's outline on its dark card, which the web checks too.
        let litEdgeExamples: [[String]]
        let families: [String: String]
        let palettes: [String: [String: String]]
        let decals: [String: String]
        let truck: Truck
    }

    // The bundled rules, without inheriting a previously cached live catalog.
    private let catalog = CarrierCatalog(cacheURL: nil)

    private func brand() throws -> BrandDefinition {
        let url = try XCTUnwrap(Bundle.main.url(forResource: "Brand", withExtension: "json"))
        return try JSONDecoder().decode(BrandDefinition.self, from: Data(contentsOf: url))
    }

    private func identity(_ id: String) throws -> CarrierVisualIdentity {
        let definition = try XCTUnwrap(catalog.definitions[CarrierID(rawValue: id)], "\(id) is not in the catalog")
        return CarrierVisualIdentity(id: id, carrier: definition)
    }

    /// The app's tone for a step, once it matches the shared definition value for value.
    private func tone(of step: BrandDefinition.Step) throws -> CarrierBrandAssets.Step.Tone {
        let definition = try XCTUnwrap(step.tone, step.property)
        let tone = try XCTUnwrap(CarrierBrandAssets.shared.derivation.first { $0.property == step.property }?.tone, step.property)
        XCTAssertEqual([tone.lightness, tone.chroma, tone.maxChroma], [definition.lightness, definition.chroma, definition.maxChroma])
        XCTAssertEqual([tone.amber.from, tone.amber.to, tone.amber.hue, tone.amber.slope],
                       [definition.amber.from, definition.amber.to, definition.amber.hue, definition.amber.slope])
        return tone
    }

    private func point(_ pair: [Double]) -> CGPoint {
        CGPoint(x: pair[0], y: pair[1])
    }

    private func points(_ pairs: [[Double]]) -> [CGPoint] {
        pairs.map(point)
    }

    func testDeclaredPalettesFamiliesAndLiveriesMatchTheCarrierFolders() throws {
        let brand = try brand()
        XCTAssertEqual(brand.properties.count, 9)
        XCTAssertFalse(brand.palettes.isEmpty)
        for (id, palette) in brand.palettes {
            let colors = try identity(id).colors
            XCTAssertEqual(colors.count, brand.properties.count, "\(id) resolves \(colors.count) colors")
            for (index, property) in brand.properties.enumerated() {
                guard let declared = palette[property] else { continue }
                XCTAssertEqual(colors[index], declared, "\(id) \(property)")
            }
        }
        for id in catalog.definitions.keys.map(\.rawValue) {
            XCTAssertEqual(try identity(id).decal, brand.decals[id] ?? "default", "\(id) livery")
        }
        for (id, family) in brand.families {
            XCTAssertEqual(try identity(id).family, family, "\(id) family")
        }
        for name in brand.decals.values {
            XCTAssertNotNil(brand.truck.decals[name], "\(name) is not a truck livery")
        }
    }

    func testCarriersWithoutAPaletteUseTheSharedMixAmounts() throws {
        let brand = try brand()
        let sampled = ["swiss-post", "fedex", "dpd", "la-poste"].filter { brand.palettes[$0] == nil }
        XCTAssertFalse(sampled.isEmpty)
        for id in sampled {
            let color = try XCTUnwrap(catalog.definitions[CarrierID(rawValue: id)]).color
            let colors = try identity(id).colors
            XCTAssertEqual(colors.count, brand.derivation.count)
            for (index, step) in brand.derivation.enumerated() {
                let mixed = CarrierVisualIdentity.mix(color, step.base, step.amount)
                // A step with a tone settles the mix at the tone's lightness, as the dark card does.
                let expected = try step.tone == nil ? mixed : CarrierVisualIdentity.tone(mixed, tone(of: step))
                XCTAssertEqual(colors[index], expected, "\(id) \(step.property)")
                XCTAssertEqual(step.property, brand.properties[index])
            }
        }
    }

    func testDarkCardsSettleAtOneLightnessAsTheSharedExamples() throws {
        let brand = try brand()
        let step = try XCTUnwrap(brand.derivation.first { $0.property == "surface-dark" })
        let shared = try tone(of: step)
        XCTAssertFalse(brand.toneExamples.isEmpty)
        for example in brand.toneExamples {
            XCTAssertEqual(example.count, 2)
            XCTAssertEqual(CarrierVisualIdentity.tone(example[0], shared), example[1], example[0])
        }
    }

    func testNamedColorsMatchTheSharedBrand() throws {
        let brand = try brand()
        XCTAssertEqual(CarrierVisualIdentity.defaultCarrierColor, brand.defaultColor)
        XCTAssertEqual(CarrierVisualIdentity.fallbackColor, brand.fallbackColor)
        // A catalog color that is not a #rrggbb literal resolves to the fallback, not to black.
        XCTAssertEqual(CarrierVisualIdentity.mix("not-a-color", "#000000", 0), brand.fallbackColor)
        XCTAssertEqual(catalog.definitions[.unknown]?.color, brand.defaultColor)
    }

    func testTruckGeometryMatchesTheSharedDefinition() throws {
        let truck = try brand().truck
        XCTAssertEqual(CarrierTruckGeometry.viewBox, CGSize(width: truck.viewBox.width, height: truck.viewBox.height))
        XCTAssertEqual(CarrierTruckGeometry.strokeWidth, CGFloat(truck.strokeWidth))
        XCTAssertEqual(
            CarrierTruckGeometry.body,
            CGRect(x: truck.body.x, y: truck.body.y, width: truck.body.width, height: truck.body.height)
        )
        XCTAssertEqual(CarrierTruckGeometry.bodyCornerRadius, CGFloat(truck.body.rx))
        XCTAssertEqual(CarrierTruckGeometry.cab, points(truck.cab.points))
        XCTAssertEqual(CarrierTruckGeometry.windshield, points(truck.windshield.points))
        XCTAssertEqual(CarrierTruckGeometry.windshieldColor, truck.windshield.fill)
        XCTAssertEqual(CarrierTruckGeometry.wheelCenters, points(truck.wheels.centers))
        XCTAssertEqual(CarrierTruckGeometry.tireRadius, CGFloat(truck.wheels.tire.r))
        XCTAssertEqual(CarrierTruckGeometry.tireColor, truck.wheels.tire.fill)
        XCTAssertEqual(CarrierTruckGeometry.hubRadius, CGFloat(truck.wheels.hub.r))
        XCTAssertEqual(CarrierTruckGeometry.hubColor, truck.wheels.hub.fill)
        // The body and cab wear the identity's own colors; the glass is a literal.
        XCTAssertEqual([truck.body.fill, truck.body.stroke, truck.cab.fill, truck.cab.stroke], ["truck", "edge", "truck", "edge"])
        // In dark the glass and tyre take the shared dark paints, and the tyre its rim
        // (`testDarkGlassAndTyresStandOutAsMuchAsTheLightOnes`).
        XCTAssertEqual(CarrierTruckGeometry.windshieldDarkColor, truck.windshield.dark)
        XCTAssertEqual(CarrierTruckGeometry.tireDarkColor, truck.wheels.tire.dark)
        XCTAssertEqual(CarrierTruckGeometry.tireRimColor, truck.wheels.tire.rim)
        XCTAssertEqual(CarrierTruckGeometry.tireRimOpacity, truck.wheels.tire.rimOpacity)
        XCTAssertEqual(CarrierTruckGeometry.tireRimWidth, CGFloat(truck.wheels.tire.rimWidth))
    }

    func testDarkTrucksLightTheirOutlineAsTheSharedExamples() throws {
        let brand = try brand()
        let rule = CarrierBrandAssets.shared.litEdge
        XCTAssertEqual([rule.below, rule.lift, rule.floor, rule.maxChroma],
                       [brand.litEdge.below, brand.litEdge.lift, brand.litEdge.floor, brand.litEdge.maxChroma])
        XCTAssertFalse(brand.litEdgeExamples.isEmpty)
        for example in brand.litEdgeExamples {
            XCTAssertEqual(example.count, 4)
            XCTAssertEqual(CarrierVisualIdentity.litEdge(example[0], on: example[1], edge: example[2], rule), example[3], example[0])
        }
    }

    /// On its dark card every carrier's truck keeps its outline, glass and tyres at least as visible as the light paints
    /// would leave them: the outline against the card, the glass against the body, a tyre's fill or rim against the body
    /// and the card. Only a white body is lighter than the glass, and it loses less than 0.001 to it.
    func testDarkGlassAndTyresStandOutAsMuchAsTheLightOnes() throws {
        let truck = CarrierTruckGeometry.self
        let contrast = CarrierVisualIdentity.contrast
        let rim = { (ground: String) in
            CarrierVisualIdentity.mix(ground, truck.tireRimColor, truck.tireRimOpacity)
        }
        XCTAssertGreaterThan(catalog.definitions.count, 1000)
        for id in catalog.definitions.keys.map(\.rawValue) {
            let colors = try identity(id).colors
            let (card, body, edge) = (colors[1], colors[6], colors[7])
            let lit = CarrierVisualIdentity.litEdge(body, on: card, edge: edge)
            XCTAssertGreaterThanOrEqual(contrast(lit, card), contrast(edge, card), "\(id)'s outline")
            XCTAssertGreaterThanOrEqual(contrast(truck.windshieldDarkColor, body), contrast(truck.windshieldColor, body) - 0.001, "\(id)'s glass")
            for (name, ground) in [("card", card), ("body", body)] {
                let shown = max(contrast(truck.tireDarkColor, ground), contrast(rim(ground), ground))
                XCTAssertGreaterThanOrEqual(shown, contrast(truck.tireColor, ground), "\(id)'s tyre on its \(name)")
            }
        }
    }

    func testDecalGeometryMatchesTheSharedDefinition() throws {
        let expected = try brand().truck.decals
        let actual = CarrierBrandAssets.shared.truck.decals
        XCTAssertEqual(Set(actual.keys), Set(expected.keys))
        for (name, shapes) in expected {
            let rendered = try XCTUnwrap(actual[name])
            XCTAssertEqual(rendered.count, shapes.count, name)
            for (shape, drawing) in zip(shapes, rendered) {
                XCTAssertEqual(drawing.type.rawValue, shape.type, name)
                XCTAssertEqual(drawing.points, shape.points, name)
                XCTAssertEqual(drawing.segments, shape.segments, name)
                XCTAssertEqual(drawing.strokeWidth, shape.strokeWidth, name)
                XCTAssertEqual(drawing.cx, shape.cx, name)
                XCTAssertEqual(drawing.cy, shape.cy, name)
                XCTAssertEqual(drawing.r, shape.r, name)
                XCTAssertEqual(drawing.paint, shape.type == "line" ? shape.stroke : shape.fill, name)
                XCTAssertFalse(drawing.path.isEmpty, name)
                let bounds = drawing.path.boundingRect
                XCTAssertGreaterThanOrEqual(bounds.minX, 2, name)
                XCTAssertLessThanOrEqual(bounds.maxX, 20, name)
                XCTAssertGreaterThanOrEqual(bounds.minY, 3, name)
                XCTAssertLessThanOrEqual(bounds.maxY, 16, name)
            }
        }
    }

    func testCarriersWithoutAVerifiedBrandColorStayNeutral() throws {
        let brand = try brand()
        let neutral = catalog.definitions.filter { $0.value.color == CarrierVisualIdentity.defaultCarrierColor }
        XCTAssertEqual(Set(neutral.keys), [CarrierID(rawValue: "omgo"), .unknown])
        for id in neutral.keys {
            XCTAssertNil(brand.palettes[id.rawValue], id.rawValue)
            XCTAssertEqual(try identity(id.rawValue).decal, "default", id.rawValue)
        }
    }
}
