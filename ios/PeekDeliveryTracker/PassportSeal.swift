import SwiftUI

/// A paper seal: the stamp's glyph, or its numeral, in a die-cut frame of its tone. An earned one carries a postmark,
/// unless it has no day to stand for (a friend's); a new one is pressed in, then postmarked (`press`: milliseconds since
/// its press began, nil once at rest).
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
    var postmark = true

    init(glyph: String? = nil, numeral: String? = nil, ink: Color, paper: Color, earned: Bool = true, size: Size = .regular, press: Double? = nil,
         postmark: Bool = true) {
        self.glyph = glyph
        self.numeral = numeral
        self.ink = ink
        self.paper = paper
        self.earned = earned
        self.size = size
        self.press = press
        self.postmark = postmark
    }

    /// A stamp's own seal, in its tone.
    init(stamp: PassportData.Stamp, earned: Bool = true, size: Size = .regular, press: Double? = nil, postmark: Bool = true) {
        self.init(glyph: stamp.glyph, numeral: stamp.numeral, ink: stamp.tone.ink, paper: stamp.tone.paper, earned: earned, size: size, press: press,
                  postmark: postmark)
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
            if earned, postmark, size != .mini {
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

/// The passport's timings and curves (FABLE.md 2.1 to 2.6): durations in ms and cubic-béziers only, the same on the web
/// and Android. `drop` falls faster and faster, `settle` is quick then soft, `move` is in-out, `flip` turns a page.
enum PassportMotion {
    static let drop = CubicBezier(0.5, 0, 0.9, 0.4)
    static let settle = CubicBezier(0.2, 0.8, 0.3, 1)
    static let move = CubicBezier(0.3, 0, 0.2, 1)
    static let flip = CubicBezier(0.22, 0.8, 0.2, 1)
    /// A page turns in this long.
    static let flipDuration: Double = 420

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

extension PassportMotion {
    /// A page's cells rise in one after another (the web's `passport-rise`): 160 ms, then 45 ms apart, the thirteenth
    /// and any after it together.
    static func riseDelay(_ index: Int) -> Double { 160 + Double(min(index, 12)) * 45 }

    /// New countries land 120 ms apart once their page has settled; the first two drop, any after them fade in.
    static func dropDelay(_ index: Int) -> Double { 180 + Double(index) * 120 }
    /// The ink's impact, with its light haptic.
    static let dropImpact: Double = 234
    /// Everything of a landing, caption and New pill included, rests by then.
    static let dropEnd: Double = 634

    /// A new country's ink at `elapsed` ms since its landing began (FABLE.md 2.5): `drops` for the first two, which fall
    /// from above, tilted, and bleed at the impact; the others simply fade in.
    struct Drop {
        let scale: Double
        let turn: Double
        let lift: Double
        let opacity: Double
        /// The outline's stroke, 1.7 at rest.
        let outline: Double
        /// The flag, name and count, rising in after the impact.
        let caption: Double
        let pill: Double

        init(elapsed: Double?, drops: Bool = true) {
            guard let t = elapsed else {
                (scale, turn, lift, opacity, outline, caption, pill) = (1, 0, 0, 1, 1.7, 1, 1)
                return
            }
            guard drops else {
                let shown = min(1, max(0, t / 200))
                (scale, turn, lift, opacity, outline, caption, pill) = (1, 0, 0, shown, 1.7, shown, shown)
                return
            }
            let impact = PassportMotion.dropImpact
            if t < impact {
                let p = PassportMotion.drop(max(0, t) / impact)
                scale = 1.6 + (0.95 - 1.6) * p
                turn = -10 * (1 - p)
                lift = -14 * (1 - p)
                outline = 1.7
            } else {
                let p = PassportMotion.settle(min(1, (t - impact) / (520 - impact)))
                scale = 0.95 + 0.05 * p
                turn = 0
                lift = 0
                outline = 2.4 - 0.7 * p
            }
            opacity = min(1, max(0, t / 100))
            caption = PassportMotion.settle(min(1, max(0, (t - impact - 80) / 300)))
            pill = PassportMotion.settle(min(1, max(0, (t - impact - 200) / 200)))
        }
    }
}

/// A cell rising into place as its page opens (the web's `passport-rise`): from 8 points lower and clear, over 320 ms on
/// the settle curve, after `PassportMotion.riseDelay(index)`. With Reduce Motion it is simply there.
struct PassportRise: ViewModifier {
    let index: Int
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @State private var risen = false

    func body(content: Content) -> some View {
        let shown = risen || reduceMotion
        content
            .offset(y: shown ? 0 : 8)
            .opacity(shown ? 1 : 0)
            .onAppear {
                guard !risen, !reduceMotion else { return }
                withAnimation(PassportMotion.settle.animation(320, delay: PassportMotion.riseDelay(index))) { risen = true }
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
