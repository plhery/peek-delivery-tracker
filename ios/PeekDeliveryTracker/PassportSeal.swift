import SwiftUI

/// A paper seal: the stamp's glyph, or its numeral, in a die-cut frame of its tone. An earned one carries a postmark;
/// a new one is pressed in, then postmarked (`press`: milliseconds since its press began, nil once at rest).
struct PassportSeal: View {
    enum Size {
        case regular, cover, mini

        var frame: CGSize {
            switch self {
            case .regular: CGSize(width: 54, height: 67)
            case .cover: CGSize(width: 66, height: 80)
            case .mini: CGSize(width: 24, height: 30)
            }
        }
    }

    var glyph: String?
    var numeral: String?
    let ink: Color
    let paper: Color
    var earned = true
    var size: Size = .regular
    var press: Double?

    init(glyph: String? = nil, numeral: String? = nil, ink: Color, paper: Color, earned: Bool = true, size: Size = .regular, press: Double? = nil) {
        self.glyph = glyph
        self.numeral = numeral
        self.ink = ink
        self.paper = paper
        self.earned = earned
        self.size = size
        self.press = press
    }

    /// A stamp's own seal, in its tone.
    init(stamp: PassportData.Stamp, earned: Bool = true, size: Size = .regular, press: Double? = nil) {
        self.init(glyph: stamp.glyph, numeral: stamp.numeral, ink: stamp.tone.ink, paper: stamp.tone.paper, earned: earned, size: size, press: press)
    }

    /// The cover's parcel seal: white paper, ochre ink.
    static let cover = PassportSeal(glyph: "parcel", ink: Color(hex: "#745300"), paper: .white, size: .cover)

    var body: some View {
        let motion = PassportMotion.Press(elapsed: press)
        let inset: CGFloat = size == .mini ? 3 : 6
        ZStack {
            DieCutStampShape().fill(paper)
            Rectangle().strokeBorder(ink.opacity(0.4), lineWidth: size == .mini ? 0.7 : 1).padding(inset)
            if let numeral {
                Text(numeral)
                    .font(.system(size: size == .mini ? 10 : 20, weight: size == .mini ? .medium : .light).monospacedDigit())
                    .foregroundStyle(ink)
            } else {
                PassportGlyph(name: glyph ?? "parcel")
                    .stroke(ink, style: StrokeStyle(lineWidth: size == .mini ? 1.4 * 14 / 24 : 1.1, lineCap: .round, lineJoin: .round))
                    .frame(width: size == .mini ? 14 : 24, height: size == .mini ? 14 : 24)
            }
        }
        .frame(width: size.frame.width, height: size.frame.height)
        .overlay(alignment: .bottomTrailing) {
            if earned, size != .mini {
                let ring: CGFloat = size == .cover ? 33 : 27
                PassportPostmark(drawn: motion.ring, inner: motion.innerRing)
                    .foregroundStyle(ink)
                    .frame(width: ring, height: ring)
                    .offset(x: size == .cover ? 9 : 11, y: size == .cover ? -3 : 3)
            }
        }
        .scaleEffect(motion.scale)
        .rotationEffect(.degrees(motion.rotation))
        .opacity(motion.opacity)
        .opacity(earned ? 1 : 0.3)
        .saturation(earned ? 1 : 0)
        .accessibilityHidden(true)
    }
}

/// One of the passport's 24×24 line drawings, scaled to its frame.
struct PassportGlyph: Shape {
    let name: String

    func path(in rect: CGRect) -> Path {
        let data = PassportData.shared.glyphs[name] ?? PassportData.shared.glyphs["parcel"] ?? ""
        let scale = min(rect.width, rect.height) / 24
        return PassportPath.path(data)
            .applying(CGAffineTransform(scaleX: scale, y: scale).concatenating(CGAffineTransform(translationX: rect.minX, y: rect.minY)))
    }
}

/// The postmark over an earned seal's lower corner: two rings in a 32×32 box, at .34. The outer one draws clockwise from
/// about ten o'clock as `drawn` goes to 1; the inner one fades in with `inner`.
struct PassportPostmark: View {
    var drawn: Double = 1
    var inner: Double = 1

    var body: some View {
        GeometryReader { proxy in
            let unit = proxy.size.width / 32
            ZStack {
                Circle()
                    .inset(by: unit)
                    .trim(from: 0, to: drawn)
                    .stroke(style: StrokeStyle(lineWidth: 1.05 * unit, lineCap: .butt))
                    .rotationEffect(.degrees(-150))
                Circle()
                    .inset(by: 5.5 * unit)
                    .stroke(lineWidth: 1.05 * unit)
                    .opacity(inner)
            }
        }
        .opacity(0.34)
        .allowsHitTesting(false)
    }
}

/// The passport's timings and curves (FABLE.md 2.1, 2.2 and 2.6): durations in ms and cubic-béziers only, the same on
/// the web and Android. `drop` falls faster and faster, `settle` is quick then soft, `move` is in-out.
enum PassportMotion {
    static let drop = CubicBezier(0.5, 0, 0.9, 0.4)
    static let settle = CubicBezier(0.2, 0.8, 0.3, 1)
    static let move = CubicBezier(0.3, 0, 0.2, 1)

    /// A new seal waits for the page to settle, then the seals press 90 ms apart; the third and any after it together.
    static func pressDelay(_ index: Int) -> Double { 180 + Double(min(index, 2)) * 90 }
    /// The light impact of the first press.
    static let impact: Double = 216
    /// Everything of a press, ring included, rests by then.
    static let pressEnd: Double = 596

    /// A press at `elapsed` ms (nil: at rest; before 0: not pressed yet).
    struct Press {
        let scale: Double
        let rotation: Double
        let opacity: Double
        let ring: Double
        let innerRing: Double
        /// The New pill: it pops 416 ms into the press.
        let pill: Double

        init(elapsed: Double?) {
            guard let t = elapsed else {
                (scale, rotation, opacity, ring, innerRing, pill) = (1, 0, 1, 1, 1, 1)
                return
            }
            if t < 216 {
                let p = PassportMotion.drop(max(0, t) / 216)
                scale = 1.5 + (0.94 - 1.5) * p
                rotation = -8 * (1 - p)
            } else {
                scale = 0.94 + 0.06 * PassportMotion.settle(min(1, (t - 216) / 264))
                rotation = 0
            }
            opacity = min(1, max(0, t / 100))
            ring = PassportMotion.move(min(1, max(0, (t - 276) / 320)))
            innerRing = min(1, max(0, (t - 436) / 160))
            pill = PassportMotion.settle(min(1, max(0, (t - 416) / 200)))
        }
    }
}

/// A CSS cubic-bézier timing curve, evaluated at a moment between 0 and 1.
struct CubicBezier {
    let x1, y1, x2, y2: Double

    init(_ x1: Double, _ y1: Double, _ x2: Double, _ y2: Double) {
        (self.x1, self.y1, self.x2, self.y2) = (x1, y1, x2, y2)
    }

    private func point(_ t: Double, _ a: Double, _ b: Double) -> Double {
        let u = 1 - t
        return 3 * u * u * t * a + 3 * u * t * t * b + t * t * t
    }

    func callAsFunction(_ x: Double) -> Double {
        if x <= 0 { return 0 }
        if x >= 1 { return 1 }
        var low = 0.0, high = 1.0, t = x
        for _ in 0..<40 {
            let value = point(t, x1, x2)
            if abs(value - x) < 1e-6 { break }
            if value < x { low = t } else { high = t }
            t = (low + high) / 2
        }
        return point(t, y1, y2)
    }

    /// The same curve for SwiftUI's animations.
    func animation(_ milliseconds: Double, delay: Double = 0) -> Animation {
        .timingCurve(x1, y1, x2, y2, duration: milliseconds / 1000).delay(delay / 1000)
    }
}
