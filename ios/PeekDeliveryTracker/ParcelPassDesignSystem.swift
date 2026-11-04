import SwiftUI

enum ExperimentalPalette {
    static let transit = Brand.color(light: "#22588A", dark: "#9BCBFF")
    static let pickup = Brand.warning
    static let delivered = Brand.color(light: "#24613E", dark: "#9BE0B5")
    static let lilac = Brand.color(light: "#654299", dark: "#D1B5FF")
    static let rose = Brand.color(light: "#9A3D5C", dark: "#FFADC8")
    static let ochre = Brand.color(light: "#745300", dark: "#FFD65F")

    static let transitSurface = Brand.color(light: "#DAEAFE", dark: "#152636")
    static let pickupSurface = Brand.color(light: "#FFDDC7", dark: "#341F14")
    static let deliveredSurface = Brand.color(light: "#DCF0D7", dark: "#142A1D")
    static let lilacSurface = Brand.color(light: "#E9DEFF", dark: "#272034")
    static let roseSurface = Brand.color(light: "#FADDE7", dark: "#331D27")
    static let ochreSurface = Brand.color(light: "#FFEBAB", dark: "#2F220E")

    static func surface(for parcel: Parcel) -> Color {
        switch parcel.currentStage {
        case .delivered: deliveredSurface
        case .customs, .exception, .failedAttempt, .readyForPickup, .returned: pickupSurface
        case .outForDelivery: ochreSurface
        case .pending, .registered, .none: lilacSurface
        default: transitSurface
        }
    }

    static func tint(for parcel: Parcel) -> Color {
        switch parcel.currentStage {
        case .delivered:
            delivered
        case .customs, .exception, .failedAttempt, .readyForPickup, .returned:
            pickup
        case .inTransit:
            transit
        case .outForDelivery:
            ochre
        case .pending, .registered, .none:
            lilac
        default:
            transit
        }
    }
}

struct ExperimentalBackdrop: View {
    var body: some View {
        Brand.background.ignoresSafeArea()
    }
}

/// A self-adhesive stamp's serpentine die cut: every edge dips into the paper in
/// even waves that meet at the corners, where each edge starts and ends at full width.
struct DieCutStampShape: Shape {
    /// The parcel's stamp is designed 44 wide and scales its waves with its width.
    static let designWidth: CGFloat = 44
    /// The length and depth of one wave. The other stamps keep these at any size.
    var wavelength: CGFloat = 3.6
    var depth: CGFloat = 1.1

    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.move(to: CGPoint(x: rect.minX, y: rect.minY))
        let corners = [CGPoint(x: rect.minX, y: rect.minY), CGPoint(x: rect.maxX, y: rect.minY),
                       CGPoint(x: rect.maxX, y: rect.maxY), CGPoint(x: rect.minX, y: rect.maxY)]
        let inward = [CGVector(dx: 0, dy: 1), CGVector(dx: -1, dy: 0), CGVector(dx: 0, dy: -1), CGVector(dx: 1, dy: 0)]
        for side in 0..<4 {
            let from = corners[side], to = corners[(side + 1) % 4]
            let length = hypot(to.x - from.x, to.y - from.y)
            let waves = max(1, Int((length / wavelength).rounded()))
            let steps = waves * 10
            for step in 1...steps {
                let t = CGFloat(step) / CGFloat(steps)
                let dip = depth * (1 - cos(2 * .pi * CGFloat(waves) * t)) / 2
                path.addLine(to: CGPoint(x: from.x + (to.x - from.x) * t + inward[side].dx * dip,
                                         y: from.y + (to.y - from.y) * t + inward[side].dy * dip))
            }
        }
        path.closeSubpath()
        return path
    }
}

/// A globe's meridians and parallels, inside its bounding square.
struct StampGlobeLines: Shape {
    func path(in rect: CGRect) -> Path {
        let radius = rect.width / 2
        let center = CGPoint(x: rect.midX, y: rect.midY)
        var path = Path()
        path.addEllipse(in: CGRect(x: center.x - radius * 0.42, y: rect.minY, width: radius * 0.84, height: rect.height))
        path.move(to: CGPoint(x: center.x, y: rect.minY))
        path.addLine(to: CGPoint(x: center.x, y: rect.maxY))
        path.move(to: CGPoint(x: rect.minX, y: center.y))
        path.addLine(to: CGPoint(x: rect.maxX, y: center.y))
        // The two parallels, half way to each pole.
        let band = radius / 2
        let chord = (radius * radius - band * band).squareRoot()
        for y in [center.y - band, center.y + band] {
            path.move(to: CGPoint(x: center.x - chord, y: y))
            path.addLine(to: CGPoint(x: center.x + chord, y: y))
        }
        return path
    }
}

/// The parcel's stamp: the same globe on every parcel, printed in the card's own
/// colours, with the country it was posted in. The postmark lands with the delivery.
struct DeliveryPostageStamp: View {
    let parcel: Parcel
    let identity: CarrierVisualIdentity
    var width: CGFloat = 46
    let appeared: Bool
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @Environment(\.colorScheme) private var colorScheme

    var body: some View {
        // Every measure below is in the stamp's 44 by 56 design, scaled to `width`.
        let unit = width / DieCutStampShape.designWidth
        let origin = parcel.stampOrigin
        // Without a caption the globe sits in the middle of the print.
        let globeY = (origin == nil ? 28 : 25) * unit
        let ink = identity.ink
        let paper = Color(hex: colorScheme == .dark ? "#D9D0BC" : "#FFFEFA")
        ZStack {
            DieCutStampShape(wavelength: 3.6 * unit, depth: 1.1 * unit)
                .fill(paper)
                .shadow(color: .black.opacity(0.16), radius: 1.2 * unit, y: 0.8 * unit)
            Rectangle()
                .fill(identity.surface)
                .padding(4.5 * unit)
            Group {
                Circle().fill(identity.surface.mix(with: ink, by: 0.14))
                StampGlobeLines().stroke(ink, lineWidth: 0.7 * unit)
                Circle().stroke(ink, lineWidth: 0.9 * unit)
            }
            .frame(width: 22 * unit, height: 22 * unit)
            .position(x: 22 * unit, y: globeY)
            Rectangle()
                .stroke(ink.opacity(0.28), lineWidth: 0.6 * unit)
                .padding(6.2 * unit)
            if let origin {
                Text(origin)
                    .font(.system(size: 5.2 * unit, weight: .bold))
                    .tracking(0.5 * unit)
                    .foregroundStyle(ink)
                    .position(x: 22 * unit, y: 45 * unit)
            }
        }
        .frame(width: width, height: 56 * unit)
        .overlay {
            if colorScheme == .dark {
                // The card's light ink would hardly show on the cream paper, so where it lands there the postmark is
                // printed in the card's own colour.
                postmark(ink, unit: unit)
                    .mask { StampPaper(unit: unit, inverted: true).fill(style: FillStyle(eoFill: true)) }
                postmark(identity.surface, unit: unit)
                    .mask { StampPaper(unit: unit).fill(style: FillStyle(eoFill: true)) }
            } else {
                postmark(ink, unit: unit)
            }
        }
        .animation(reduceMotion ? nil : .easeIn(duration: 0.28).delay(0.2), value: parcel.stampDeliveryDate)
        .rotationEffect(.degrees(reduceMotion || appeared ? -2 : -10))
        .scaleEffect(reduceMotion || appeared ? 1 : 1.12)
        .animation(reduceMotion ? nil : .spring(response: 0.48, dampingFraction: 0.6).delay(0.08), value: appeared)
        .accessibilityHidden(true)
    }

    /// The delivery's postmark in `ink`, once the parcel is delivered.
    @ViewBuilder
    private func postmark(_ ink: Color, unit: CGFloat) -> some View {
        if let date = parcel.stampDeliveryDate {
            ZStack {
                Circle().stroke(ink, lineWidth: 0.9 * unit)
                Circle().stroke(ink, lineWidth: 0.55 * unit).padding(2.8 * unit)
                Text(date)
                    .font(.system(size: 5.2 * unit, weight: .heavy))
                    .monospacedDigit()
                    .foregroundStyle(ink)
            }
            .frame(width: 23 * unit, height: 23 * unit)
            .rotationEffect(.degrees(-12))
            .opacity(0.8)
            .position(x: 38 * unit, y: 36 * unit)
            .allowsHitTesting(false)
            // On a stamp that is being looked at, the postmark comes down: large and askew above the paper, then pressed home.
            .transition(.asymmetric(
                insertion: .modifier(active: PostmarkPose(scale: 2.6, degrees: 16, opacity: 0), identity: PostmarkPose(scale: 1, degrees: 0, opacity: 1)),
                removal: .identity
            ))
        }
    }
}

/// The stamp's paper around its print, filled even-odd; `inverted`, everything but the paper, as far out as a
/// postmark on its way down reaches.
private struct StampPaper: Shape {
    let unit: CGFloat
    var inverted = false

    func path(in rect: CGRect) -> Path {
        var path = DieCutStampShape(wavelength: 3.6 * unit, depth: 1.1 * unit).path(in: rect)
        path.addRect(rect.insetBy(dx: 4.5 * unit, dy: 4.5 * unit))
        if inverted { path.addRect(rect.insetBy(dx: -2 * rect.width, dy: -2 * rect.height)) }
        return path
    }
}

/// The postmark's place on the stamp, and how far it still is from the paper.
private struct PostmarkPose: ViewModifier {
    let scale: CGFloat
    let degrees: Double
    let opacity: Double
    private static let mark = UnitPoint(x: 38.0 / 44, y: 36.0 / 56)

    func body(content: Content) -> some View {
        content
            .scaleEffect(scale, anchor: Self.mark)
            .rotationEffect(.degrees(degrees), anchor: Self.mark)
            .opacity(opacity)
    }
}

extension View {
    func experimentalSurface(
        tint: Color = .clear,
        fill: Color = Brand.paper,
        cornerRadius: CGFloat = 26,
        shadow: Bool = true
    ) -> some View {
        background {
            RoundedRectangle(cornerRadius: cornerRadius, style: .continuous)
                .fill(fill)
                .overlay {
                    RoundedRectangle(cornerRadius: cornerRadius, style: .continuous)
                        .fill(tint.opacity(0.12))
                }
                .overlay {
                    RoundedRectangle(cornerRadius: cornerRadius, style: .continuous)
                        .stroke(Brand.separator.opacity(0.42), lineWidth: 0.6)
                }
                .shadow(
                    color: shadow ? .black.opacity(0.035) : .clear,
                    radius: shadow ? 10 : 0,
                    y: shadow ? 4 : 0
                )
        }
    }

}

struct ExperimentalLiftButtonStyle: ButtonStyle {
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .scaleEffect(configuration.isPressed && !reduceMotion ? 0.975 : 1)
            .offset(y: configuration.isPressed && !reduceMotion ? 1.5 : 0)
            .brightness(configuration.isPressed ? -0.025 : 0)
            .animation(reduceMotion ? nil : .snappy(duration: 0.22), value: configuration.isPressed)
    }
}

struct ExperimentalCarrierToken: View {
    let carrier: CarrierDefinition
    let tint: Color

    var body: some View {
        HStack(spacing: 7) {
            Image(systemName: "shippingbox.fill")
                .font(.caption2.weight(.bold))
            Text(carrier.displayName)
                .lineLimit(1)
        }
        .font(.caption.weight(.semibold))
        .foregroundStyle(.primary)
        .padding(.horizontal, 10)
        .frame(height: 30)
        .background(tint.opacity(0.14), in: Capsule())
        .overlay(Capsule().stroke(tint.opacity(0.22), lineWidth: 0.7))
    }
}

struct ExperimentalJourneyRail: View {
    let stage: TrackingStage?
    let tint: Color
    var compact = false

    @EnvironmentObject private var localizer: Localizer
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    var body: some View {
        let current = max(0, min(stage?.metadata.progress ?? 0, TrackingStage.core.count - 1))

        HStack(spacing: compact ? 4 : 5) {
            ForEach(Array(TrackingStage.core.enumerated()), id: \.offset) { index, _ in
                Capsule()
                    .fill(index <= current ? tint : Color.secondary.opacity(0.15))
                    .frame(height: 3)
            }
        }
        .frame(height: 3)
        .animation(reduceMotion ? nil : .easeOut(duration: 0.24), value: current)
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(localizer.text("native.deliveryProgress"))
        .accessibilityValue(
            localizer.text("progress.step", [
                "step": current + 1,
                "total": TrackingStage.core.count,
                "stage": localizer.text(stage?.localizationKey ?? "status.pending"),
            ])
        )
    }
}

@MainActor
struct ExperimentalCopy {
    let localizer: Localizer

    var passport: String { localizer.text("passport.title") }
    var showArchive: String { localizer.text("design.showArchive") }
    var hideArchive: String { localizer.text("design.hideArchive") }
    var fullJourney: String { localizer.text("design.fullJourney") }
    var lessJourney: String { localizer.text("design.lessJourney") }
    var parcelTitle: String { localizer.text("design.parcelTitle") }
}

extension Parcel {
    var experimentalLatestLocation: String? {
        sortedEvents.compactMap { $0.location?.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty }.first
    }
}

/// The same bundled identities and decorations the web reads from core/brand.
/// Load once so a palette or family added to carrier.json also reaches iPhone.
struct CarrierBrandAssets: Decodable {
    struct Step: Decodable {
        /// Brings a mix to one OKLCH lightness, its hue kept and its chroma scaled and capped; the yellows between
        /// `amber.from` and `amber.to`, which turn khaki when dark, move towards `amber.hue`. A colour already
        /// darker than the result, by WCAG luminance, stays as it is, so text on it never loses contrast.
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
        /// Then settles the mix at one lightness, as the dark cards do.
        let tone: Tone?
    }

    struct DecalShape: Decodable {
        enum Kind: String, Decodable { case line, polygon, circle }
        let type: Kind
        let points: [[Double]]?
        let segments: [[[Double]]]?
        let stroke: String?
        let strokeWidth: Double?
        let fill: String?
        let cx: Double?
        let cy: Double?
        let r: Double?

        var paint: String { type == .line ? stroke! : fill! }

        var path: Path {
            if type == .circle {
                return Path(ellipseIn: CGRect(x: cx! - r!, y: cy! - r!, width: r! * 2, height: r! * 2))
            }
            return Path { path in
                for line in type == .line ? segments! : [points!] {
                    guard let first = line.first else { continue }
                    path.move(to: CGPoint(x: first[0], y: first[1]))
                    for point in line.dropFirst() { path.addLine(to: CGPoint(x: point[0], y: point[1])) }
                    if type == .polygon { path.closeSubpath() }
                }
            }
        }
    }

    /// A dark card can swallow a dark truck: when the body's contrast with the card is under `below`, its outline takes
    /// the body's hue at `lift` more OKLCH lightness (at least `floor`), its chroma capped at `maxChroma`.
    struct LitEdge: Decodable {
        let below: Double
        let lift: Double
        let floor: Double
        let maxChroma: Double
    }

    struct Truck: Decodable { let decals: [String: [DecalShape]] }
    let defaultColor: String
    let fallbackColor: String
    let properties: [String]
    let derivation: [Step]
    let litEdge: LitEdge
    let families: [String: String]
    let palettes: [String: [String: String]]
    let decals: [String: String]
    let truck: Truck

    static let shared: CarrierBrandAssets = {
        guard let url = Bundle.main.url(forResource: "Brand", withExtension: "json"),
              let data = try? Data(contentsOf: url),
              let assets = try? JSONDecoder().decode(CarrierBrandAssets.self, from: data) else {
            preconditionFailure("Missing or invalid bundled carrier brand data")
        }
        return assets
    }()
}

struct CarrierVisualIdentity {
    static let defaultCarrierColor = CarrierBrandAssets.shared.defaultColor
    static let fallbackColor = CarrierBrandAssets.shared.fallbackColor

    let family: String
    private(set) var name: String
    private(set) var fullName: String
    let colors: [String]
    let decal: String
    /// The truck's outline in dark, on its dark card (`litEdge`).
    let edgeInDark: Color

    private final class Cached {
        let identity: CarrierVisualIdentity
        init(_ identity: CarrierVisualIdentity) { self.identity = identity }
    }
    private static let cache = NSCache<NSString, Cached>()

    /// Cards rebuild whenever the list changes; derive each carrier's livery once.
    static func of(_ carrier: CarrierID, catalog: CarrierCatalog = .shared, language: AppLanguage) -> CarrierVisualIdentity {
        let definition = catalog.info(for: carrier, language: language)
        let key = "\(carrier.rawValue)|\(definition.displayName)|\(definition.color)" as NSString
        if let cached = cache.object(forKey: key) { return cached.identity }
        let identity = CarrierVisualIdentity(id: carrier.rawValue, carrier: definition)
        cache.setObject(Cached(identity), forKey: key)
        return identity
    }

    /// The carrier a card or the parcel's header shows: the parcel's own, or while none is known, the one
    /// its number points to, named as probable in the livery of no carrier.
    @MainActor
    static func shown(for parcel: Parcel, catalog: CarrierCatalog = .shared, localizer: Localizer) -> CarrierVisualIdentity {
        var identity = of(parcel.displayedCarrier, catalog: catalog, language: localizer.language)
        guard parcel.displayedCarrier == .unknown, let likely = parcel.likelyCarrier else { return identity }
        let probable = localizer.text("link.likely.carrier", ["carrier": catalog.info(for: likely, language: localizer.language).displayName])
        identity.name = probable
        identity.fullName = probable
        return identity
    }

    init(id: String, carrier: CarrierDefinition) {
        let assets = CarrierBrandAssets.shared
        family = assets.families[id] ?? id
        fullName = carrier.displayName
        name = ["dhl": "DHL", "gls": "GLS", "ups": "ups"][family] ?? carrier.displayName
        decal = assets.decals[id] ?? "default"
        colors = assets.derivation.map { step in
            assets.palettes[id]?[step.property] ?? Self.derived(carrier.color, step)
        }
        edgeInDark = Color(hex: Self.litEdge(colors[6], on: colors[1], edge: colors[7]))
    }

    func paint(_ value: String) -> Color {
        guard let index = CarrierBrandAssets.shared.properties.firstIndex(of: value) else {
            return Color(hex: value)
        }
        return Color(hex: colors[index])
    }

    var surface: Color { Brand.color(light: colors[0], dark: colors[1]) }
    var ink: Color { Brand.color(light: colors[2], dark: colors[3]) }
    var brand: Color { Brand.color(light: colors[4], dark: colors[5]) }
    var truck: Color { Color(hex: colors[6]) }
    var edge: Color { Color(hex: colors[7]) }
    var accent: Color { Color(hex: colors[8]) }

    static func mix(_ color: String, _ base: String, _ amount: Double) -> String {
        let fallback = UInt32(fallbackColor.dropFirst(), radix: 16) ?? 0
        let value = color.count == 7 ? UInt32(color.dropFirst(), radix: 16) ?? fallback : fallback
        let background = UInt32(base.dropFirst(), radix: 16) ?? 0
        let channels = [16, 8, 0].map { shift in
            Int((Double((value >> shift) & 255) * (1 - amount) + Double((background >> shift) & 255) * amount).rounded())
        }
        return String(format: "#%02x%02x%02x", channels[0], channels[1], channels[2])
    }

    /// One property for a carrier color that declares none: the mix, then the tone if the step has one.
    static func derived(_ color: String, _ step: CarrierBrandAssets.Step) -> String {
        let mixed = mix(color, step.base, step.amount)
        return step.tone.map { tone(mixed, $0) } ?? mixed
    }

    /// `color` at the tone's lightness, unless that would make it brighter (see `CarrierBrandAssets.Step.Tone`).
    static func tone(_ color: String, _ tone: CarrierBrandAssets.Step.Tone) -> String {
        let (_, sourceChroma, sourceHue) = oklch(color)
        let amber = tone.amber
        let hue = sourceHue > amber.from && sourceHue < amber.to
            ? amber.hue + (sourceHue - amber.from) * amber.slope
            : sourceHue
        let toned = fromOklch(tone.lightness, min(tone.maxChroma, sourceChroma * tone.chroma), hue)
        return luminance(toned) > luminance(color) ? color : toned
    }

    /// The truck's outline on its dark card: `edge`, unless the body sinks into the card (see `CarrierBrandAssets.LitEdge`).
    static func litEdge(_ truck: String, on surface: String, edge: String,
                        _ rule: CarrierBrandAssets.LitEdge = CarrierBrandAssets.shared.litEdge) -> String {
        guard contrast(truck, surface) < rule.below else { return edge }
        let (lightness, chroma, hue) = oklch(truck)
        return fromOklch(max(lightness + rule.lift, rule.floor), min(chroma, rule.maxChroma), hue)
    }

    /// WCAG 2 contrast ratio of two `#rrggbb` colours.
    static func contrast(_ first: String, _ second: String) -> Double {
        let (one, other) = (luminance(first), luminance(second))
        return (max(one, other) + 0.05) / (min(one, other) + 0.05)
    }

    /// WCAG 2 relative luminance of a `#rrggbb` colour.
    static func luminance(_ hex: String) -> Double {
        let rgb = channels(hex).map(linear)
        return 0.2126 * rgb[0] + 0.7152 * rgb[1] + 0.0722 * rgb[2]
    }

    /// A `#rrggbb` colour as OKLCH: lightness, chroma, hue in degrees.
    static func oklch(_ hex: String) -> (lightness: Double, chroma: Double, hue: Double) {
        let rgb = channels(hex).map(linear)
        let l = cbrt(0.4122214708 * rgb[0] + 0.5363325363 * rgb[1] + 0.0514459929 * rgb[2])
        let m = cbrt(0.2119034982 * rgb[0] + 0.6806995451 * rgb[1] + 0.1073969566 * rgb[2])
        let s = cbrt(0.0883024619 * rgb[0] + 0.2817188376 * rgb[1] + 0.6299787005 * rgb[2])
        let a = 1.9779984951 * l - 2.428592205 * m + 0.4505937099 * s
        let b = 0.0259040371 * l + 0.7827717662 * m - 0.808675766 * s
        return (0.2104542553 * l + 0.793617785 * m - 0.0040720468 * s, hypot(a, b),
                (atan2(b, a) * 180 / .pi + 360).truncatingRemainder(dividingBy: 360))
    }

    /// An OKLCH colour as `#rrggbb`, each channel clamped into sRGB.
    static func fromOklch(_ lightness: Double, _ chroma: Double, _ hue: Double) -> String {
        let a = chroma * cos(hue * .pi / 180)
        let b = chroma * sin(hue * .pi / 180)
        let l = pow(lightness + 0.3963377774 * a + 0.2158037573 * b, 3)
        let m = pow(lightness - 0.1055613458 * a - 0.0638541728 * b, 3)
        let s = pow(lightness - 0.0894841775 * a - 1.291485548 * b, 3)
        let channels = [
            4.0767416621 * l - 3.3077115913 * m + 0.2309699292 * s,
            -1.2684380046 * l + 2.6097574011 * m - 0.3413193965 * s,
            -0.0041960863 * l - 0.7034186147 * m + 1.707614701 * s,
        ].map { Int((encoded($0) * 255).rounded()) }
        return String(format: "#%02x%02x%02x", channels[0], channels[1], channels[2])
    }

    private static func channels(_ hex: String) -> [Double] {
        let value = UInt32(hex.dropFirst(), radix: 16) ?? 0
        return [16, 8, 0].map { Double((value >> $0) & 255) / 255 }
    }

    private static func linear(_ channel: Double) -> Double {
        channel <= 0.04045 ? channel / 12.92 : pow((channel + 0.055) / 1.055, 2.4)
    }

    private static func encoded(_ channel: Double) -> Double {
        let clamped = min(1, max(0, channel))
        return clamped <= 0.0031308 ? 12.92 * clamped : 1.055 * pow(clamped, 1 / 2.4) - 0.055
    }
}

/// The one truck, as `src/brand/truck.json` states it. The web
/// draws the same numbers as an SVG; `BrandParityTests` asserts every value here
/// against `Resources/Brand.json`, so neither drawing can drift.
///
/// The canvas fills straight-line outlines where the SVG uses a path: the UPS
/// shield approximates its curve with five points. Decorations are loaded from
/// the bundled geometry rather than retyped as per-carrier constants.
enum CarrierTruckGeometry {
    static let viewBox = CGSize(width: 32, height: 21)
    static let strokeWidth: CGFloat = 0.6
    static let body = CGRect(x: 2, y: 3, width: 18, height: 13)
    static let bodyCornerRadius: CGFloat = 1.3
    static let cab = [CGPoint(x: 20, y: 8), CGPoint(x: 25, y: 8), CGPoint(x: 30, y: 13),
                      CGPoint(x: 30, y: 16), CGPoint(x: 20, y: 16)]
    static let windshield = [CGPoint(x: 22, y: 9.5), CGPoint(x: 24.5, y: 9.5),
                             CGPoint(x: 27.5, y: 12.5), CGPoint(x: 22, y: 12.5)]
    static let windshieldColor = "#edf1ee"
    /// The glass in dark: a warm glass at the light glass's luminance, so it stands out from every body as much as the
    /// light glass does (`BrandParityTests`).
    static let windshieldDarkColor = "#fff0ba"
    static let wheelCenters = [CGPoint(x: 8, y: 16.5), CGPoint(x: 25, y: 16.5)]
    static let tireRadius: CGFloat = 2.4
    static let tireColor = "#42483d"
    /// In dark a tyre is darker than any card, with a faint rim so it still reads.
    static let tireDarkColor = "#0e0d0a"
    static let tireRimColor = "#fff4dc"
    /// Strong enough that a tyre stands out from the darkest bodies as much as the light tyre does.
    static let tireRimOpacity = 0.29
    static let tireRimWidth: CGFloat = 0.45
    static let hubRadius: CGFloat = 0.9
    static let hubColor = "#d2d4c7"

    /// Draws the truck in a carrier's livery, in its own 32 × 21 units: scale the context to the size wanted first.
    /// `dark` gives it the dark card's paints: a lit outline where the body would sink into the card, lit glass and
    /// rimmed tyres.
    static func draw(_ context: GraphicsContext, _ identity: CarrierVisualIdentity, dark: Bool) {
        let body = Path(roundedRect: Self.body, cornerRadius: bodyCornerRadius)
        let edge = dark ? identity.edgeInDark : identity.edge
        context.fill(body, with: .color(identity.truck))
        context.stroke(body, with: .color(edge), lineWidth: strokeWidth)
        let cab = polygon(Self.cab)
        context.fill(cab, with: .color(identity.truck))
        context.stroke(cab, with: .color(edge), lineWidth: strokeWidth)
        context.fill(polygon(windshield), with: .color(Color(hex: dark ? windshieldDarkColor : windshieldColor)))
        let decals = CarrierBrandAssets.shared.truck.decals
        for shape in decals[identity.decal] ?? decals["default"]! {
            let paint = GraphicsContext.Shading.color(identity.paint(shape.paint))
            if shape.type == .line {
                context.stroke(shape.path, with: paint, lineWidth: shape.strokeWidth!)
            } else {
                context.fill(shape.path, with: paint)
            }
        }
        for center in wheelCenters {
            let tire = disc(center, tireRadius)
            context.fill(tire, with: .color(Color(hex: dark ? tireDarkColor : tireColor)))
            if dark {
                context.stroke(tire, with: .color(Color(hex: tireRimColor).opacity(tireRimOpacity)), lineWidth: tireRimWidth)
            }
            context.fill(disc(center, hubRadius), with: .color(Color(hex: hubColor)))
        }
    }

    private static func polygon(_ points: [CGPoint]) -> Path {
        Path { path in
            guard let first = points.first else { return }
            path.move(to: first)
            for point in points.dropFirst() { path.addLine(to: point) }
            path.closeSubpath()
        }
    }

    private static func disc(_ center: CGPoint, _ radius: CGFloat) -> Path {
        Path(ellipseIn: CGRect(x: center.x - radius, y: center.y - radius, width: radius * 2, height: radius * 2))
    }
}

/// The carrier's truck alone, in its livery, beside a name written elsewhere.
struct CarrierTruckMark: View {
    let identity: CarrierVisualIdentity
    @Environment(\.colorScheme) private var colorScheme

    var body: some View {
        let dark = colorScheme == .dark
        Canvas { context, size in
            let truck = CarrierTruckGeometry.self
            context.scaleBy(x: size.width / truck.viewBox.width, y: size.height / truck.viewBox.height)
            truck.draw(context, identity, dark: dark)
        }
        .frame(width: 27, height: 18)
        .accessibilityHidden(true)
    }
}

struct CarrierFleetMark: View {
    let identity: CarrierVisualIdentity

    var body: some View {
        HStack(spacing: 7) {
            CarrierTruckMark(identity: identity)
            Text(identity.name + (identity.family == "gls" ? "." : ""))
                .font(.caption.weight(.bold))
                .italic(identity.family == "dhl")
                .fixedSize(horizontal: false, vertical: true)
        }
        .foregroundStyle(identity.brand)
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(identity.fullName)
    }
}
