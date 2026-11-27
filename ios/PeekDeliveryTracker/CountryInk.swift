import SwiftUI

/// A country's ink stamp, as the passport's Countries page and the year's card draw it: its continent's outline with an
/// inner line, its running number, its code and the day a parcel first reached it, in the country's tone and tilt.
enum CountryInkDrawing {
    /// The drawing's own box.
    static let size = CGSize(width: 124, height: 92)

    /// Draws the stamp over `box`, scaled by the box's width and turned by the country's tilt plus `turn` degrees.
    /// `outline`: the outline's stroke in the drawing's units, 1.7 at rest, more while a new stamp's ink bleeds.
    static func draw(_ context: GraphicsContext, code: String, number: Int, day: String, color: Color, in box: CGRect,
                     outline: CGFloat = 1.7, turn: Double = 0) {
        let data = PassportData.shared
        let ink = data.ink(code)
        guard let layout = data.layout[ink.continent], let outlineData = data.outlines[ink.continent] else { return }
        let path = PassportPath.path(outlineData)
        var stamp = context
        stamp.translateBy(x: box.midX, y: box.midY)
        stamp.rotate(by: .degrees(ink.tilt + turn))
        stamp.scaleBy(x: box.width / size.width, y: box.width / size.width)
        stamp.translateBy(x: -size.width / 2, y: -size.height / 2)
        stamp.stroke(path, with: .color(color), style: StrokeStyle(lineWidth: outline, lineJoin: .round))
        var inner = stamp
        inner.opacity = 0.55
        inner.translateBy(x: 62, y: 46)
        inner.scaleBy(x: layout.inner, y: layout.inner)
        inner.translateBy(x: -62, y: -46)
        inner.stroke(path, with: .color(color), style: StrokeStyle(lineWidth: 0.8 / layout.inner, lineJoin: .round))
        stamp.drawLine(Text("Nº \(String(format: "%02d", number))").font(.system(size: layout.small, weight: .semibold)).tracking(1.3)
            .foregroundStyle(color), x: 62, baseline: layout.number, centred: true)
        stamp.drawLine(Text(code).font(.system(size: layout.codeSize, weight: .bold)).tracking(2).foregroundStyle(color),
                       x: 63, baseline: layout.code, centred: true)
        stamp.drawLine(Text(day).font(.system(size: layout.small, weight: .semibold)).tracking(layout.dateSpacing).foregroundStyle(color),
                       x: 62, baseline: layout.date, centred: true)
    }
}

/// A country's ink stamp on the page, in its tone's ink for the theme: as wide as it is offered, at most 124 points, its
/// height in the drawing's proportion. A tilted corner reaches past its frame, as ink on paper does.
struct CountryInk: View {
    let code: String
    let number: Int
    let day: String
    var outline: CGFloat = 1.7
    var turn: Double = 0

    var body: some View {
        let color = PassportData.shared.ink(code).tone.ink
        Color.clear
            .aspectRatio(CountryInkDrawing.size.width / CountryInkDrawing.size.height, contentMode: .fit)
            .frame(maxWidth: CountryInkDrawing.size.width)
            .overlay {
                GeometryReader { proxy in
                    // Room around the box for the tilt and the stroke.
                    let margin = proxy.size.width * 0.18
                    Canvas { context, size in
                        CountryInkDrawing.draw(context, code: code, number: number, day: day, color: color,
                                               in: CGRect(x: margin, y: margin, width: size.width - 2 * margin, height: size.height - 2 * margin),
                                               outline: outline, turn: turn)
                    }
                    .frame(width: proxy.size.width + 2 * margin, height: proxy.size.height + 2 * margin)
                    .offset(x: -margin, y: -margin)
                }
            }
            .accessibilityHidden(true)
    }
}

extension GraphicsContext {
    /// One line set on its baseline, as a canvas sets it; a line wider than `maxWidth` is condensed to fit.
    func drawLine(_ text: Text, x: CGFloat, baseline: CGFloat, centred: Bool = false, maxWidth: CGFloat? = nil) {
        let resolved = resolve(text)
        let size = resolved.measure(in: CGSize(width: CGFloat.greatestFiniteMagnitude, height: .greatestFiniteMagnitude))
        let top = baseline - resolved.firstBaseline(in: size)
        let fit = maxWidth.map { min(1, $0 / max(size.width, 1)) } ?? 1
        var line = self
        line.translateBy(x: centred ? x - size.width * fit / 2 : x, y: top)
        line.scaleBy(x: fit, y: 1)
        line.draw(resolved, in: CGRect(origin: .zero, size: size))
    }
}
