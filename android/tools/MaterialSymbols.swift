// Writes android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/symbols/MaterialSymbolPaths.kt: for each SF
// Symbol the iPhone app draws, the Material Symbols Rounded outlines Android draws in its place, at the weights and
// optical sizes material-symbols.tsv lists, and the measurements PeekSymbol sizes them by.
//
// Run it on a Mac from the repository root, with a new empty folder for the downloads:
//   swift android/tools/MaterialSymbols.swift <empty folder>
// It fetches each SVG from google/material-design-icons (Apache License 2.0) at `revision` below into that folder and
// reads only its path data. To take newer icons, set `revision` to a newer commit of that repository and run it again.
//
// It also measures each SF Symbol as AppKit draws it, 100 pt, regular, medium scale: the image's box and where the ink
// lies in it (SF Symbols on a Mac are the iPhone's), where its inner glyph lies for a composed outline, and the opacity
// of its lower layer where iOS draws it hierarchical. And it checks material-symbols.tsv lists every SF Symbol name in
// ios/, against the system's list of names.
import AppKit

let revision = "10ecc14790612ca8fd164e3f4ffe9c16fc50d5b7"

let root = URL(fileURLWithPath: #filePath).deletingLastPathComponent().appendingPathComponent("../..").standardizedFileURL
let table = root.appendingPathComponent("android/tools/material-symbols.tsv")
let output = root.appendingPathComponent("android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/symbols/MaterialSymbolPaths.kt")

guard CommandLine.arguments.count == 2 else {
    print("usage: swift android/tools/MaterialSymbols.swift <empty folder for the downloads>")
    exit(2)
}
let downloads = URL(fileURLWithPath: CommandLine.arguments[1], isDirectory: true)

// Literals in ios/ that are SF Symbol names but aren't symbols there.
let words: Set<String> = ["a", "abc", "app", "k", "note", "right", "phone", "transmission", "number", "link", "scope", "ipad"]

struct Row {
    let sf: String
    let material: String
    let fill: Bool
    let uses: [String]

    /// Whether a use is `.symbolRenderingMode(.hierarchical)`.
    var hierarchical: Bool { uses.contains { $0.split(separator: " ").contains("hierarchical") } }
}

let rows: [Row] = try String(contentsOf: table, encoding: .utf8).split(separator: "\n").compactMap { line in
    if line.hasPrefix("#") || line.isEmpty { return nil }
    let columns = line.split(separator: "\t", omittingEmptySubsequences: false).map(String.init)
    precondition(columns.count == 5, "five columns: \(line)")
    return Row(sf: columns[0], material: columns[1], fill: columns[2] == "1", uses: columns[3].components(separatedBy: "; "))
}

// Every SF Symbol name written in ios/ (outside the tests) must have a row.
let names = (NSDictionary(contentsOf: URL(fileURLWithPath: "/System/Library/CoreServices/CoreGlyphs.bundle/Contents/Resources/name_availability.plist"))?["symbols"] as? [String: Any]).map { Set($0.keys) } ?? []
precondition(!names.isEmpty, "no SF Symbols list on this Mac")
var written = Set<String>()
let swiftFiles = FileManager.default.enumerator(at: root.appendingPathComponent("ios"), includingPropertiesForKeys: nil)!
let literal = try NSRegularExpression(pattern: "\"([a-z0-9][a-z0-9.]*)\"")
for case let file as URL in swiftFiles where file.pathExtension == "swift" && !file.path.contains("Tests/") {
    let text = try String(contentsOf: file, encoding: .utf8)
    for match in literal.matches(in: text, range: NSRange(text.startIndex..., in: text)) {
        let name = String(text[Range(match.range(at: 1), in: text)!])
        if names.contains(name) && !words.contains(name) { written.insert(name) }
    }
}
let missing = written.subtracting(rows.map(\.sf)).sorted()
precondition(missing.isEmpty, "material-symbols.tsv lacks \(missing)")

// SF weights as Material Symbols' weight axis.
let weights = ["ultraLight": 100, "thin": 200, "light": 300, "regular": 400, "medium": 500, "semibold": 600, "bold": 700, "heavy": 700, "black": 700]

/// The weight and optical size a use needs; nil for one Android doesn't draw (the Mac's, or none).
func variant(_ use: String) -> (weight: Int, opticalSize: Int)? {
    let parts = use.split(separator: " ").map(String.init)
    switch parts[1] {
    case "mac", "none": return nil
    case "menu", "tab", "toolbar", "phase": return (400, 24)
    case "inherited": return (400, 20)
    default:
        let size = Double(parts[1])!
        let opticalSize = [20, 24, 40, 48].min { abs(Double($0) - size) < abs(Double($1) - size) }!
        return (weights[parts[2]]!, opticalSize)
    }
}

/// Where a subpath starts: the text after its move's coordinates, the point moved to, and whether the move was relative.
struct Subpath {
    let rest: Substring.Index
    let point: CGPoint
    let relative: Bool
}

// The outline from SVG path data in Material Symbols' grid, its exact bounds, and where its subpaths start.
func parse(_ data: String) -> (path: CGPath, bounds: CGRect, subpaths: [(start: String.Index, subpath: Subpath)]) {
    var subpaths: [(start: String.Index, subpath: Subpath)] = []
    let path = CGMutablePath()
    var scanner = data[...]
    var command: Character = "M"
    var current = CGPoint.zero
    var start = CGPoint.zero
    var control = CGPoint.zero
    var previous: Character = " "
    func skip() { while let c = scanner.first, c == " " || c == "," || c == "\n" { scanner.removeFirst() } }
    func number() -> CGFloat {
        skip()
        var text = ""
        var seenDot = false
        var seenExponent = false
        while let c = scanner.first {
            if c.isNumber || ((c == "-" || c == "+") && (text.isEmpty || text.last == "e")) || (c == "." && !seenDot && !seenExponent) || (c == "e" && !seenExponent && !text.isEmpty) {
                if c == "." { seenDot = true }
                if c == "e" { seenExponent = true }
                text.append(c)
                scanner.removeFirst()
            } else { break }
        }
        return CGFloat(Double(text)!)
    }
    func flag() -> Bool { skip(); return scanner.removeFirst() == "1" }
    while true {
        skip()
        guard let c = scanner.first else { break }
        let commandStart = scanner.startIndex
        if c.isLetter { command = c; scanner.removeFirst() }
        let relative = command.isLowercase
        let base = relative ? current : .zero
        switch command.uppercased() {
        case "M":
            current = CGPoint(x: base.x + number(), y: base.y + number())
            start = current
            path.move(to: current)
            subpaths.append((commandStart, Subpath(rest: scanner.startIndex, point: current, relative: relative)))
            command = relative ? "l" : "L"
        case "L": current = CGPoint(x: base.x + number(), y: base.y + number()); path.addLine(to: current)
        case "H": current = CGPoint(x: (relative ? current.x : 0) + number(), y: current.y); path.addLine(to: current)
        case "V": current = CGPoint(x: current.x, y: (relative ? current.y : 0) + number()); path.addLine(to: current)
        case "C":
            let c1 = CGPoint(x: base.x + number(), y: base.y + number())
            control = CGPoint(x: base.x + number(), y: base.y + number())
            current = CGPoint(x: base.x + number(), y: base.y + number())
            path.addCurve(to: current, control1: c1, control2: control)
        case "S":
            let c1 = "CS".contains(previous) ? CGPoint(x: 2 * current.x - control.x, y: 2 * current.y - control.y) : current
            control = CGPoint(x: base.x + number(), y: base.y + number())
            current = CGPoint(x: base.x + number(), y: base.y + number())
            path.addCurve(to: current, control1: c1, control2: control)
        case "Q":
            control = CGPoint(x: base.x + number(), y: base.y + number())
            current = CGPoint(x: base.x + number(), y: base.y + number())
            path.addQuadCurve(to: current, control: control)
        case "T":
            control = "QT".contains(previous) ? CGPoint(x: 2 * current.x - control.x, y: 2 * current.y - control.y) : current
            current = CGPoint(x: base.x + number(), y: base.y + number())
            path.addQuadCurve(to: current, control: control)
        case "A":
            let rx = abs(number()), ry = abs(number()), angle = number() * .pi / 180
            let large = flag(), sweep = flag()
            let end = CGPoint(x: base.x + number(), y: base.y + number())
            addArc(path, from: current, to: end, rx: rx, ry: ry, angle: angle, large: large, sweep: sweep)
            current = end
        case "Z":
            path.closeSubpath()
            current = start
        default: fatalError("path command \(command)")
        }
        previous = Character(command.uppercased())
    }
    return (path, path.boundingBoxOfPath, subpaths)
}

/// `path` as SVG path data, in absolute coordinates.
func pathData(_ path: CGPath) -> String {
    func n(_ v: CGFloat) -> String {
        var text = String(format: "%.2f", v)
        while text.contains(".") && (text.hasSuffix("0") || text.hasSuffix(".")) { text.removeLast() }
        return text == "-0" ? "0" : text
    }
    var data = ""
    path.applyWithBlock { element in
        let p = element.pointee.points
        switch element.pointee.type {
        case .moveToPoint: data += "M\(n(p[0].x)) \(n(p[0].y))"
        case .addLineToPoint: data += "L\(n(p[0].x)) \(n(p[0].y))"
        case .addQuadCurveToPoint: data += "Q\(n(p[0].x)) \(n(p[0].y)) \(n(p[1].x)) \(n(p[1].y))"
        case .addCurveToPoint: data += "C\(n(p[0].x)) \(n(p[0].y)) \(n(p[1].x)) \(n(p[1].y)) \(n(p[2].x)) \(n(p[2].y))"
        case .closeSubpath: data += "Z"
        @unknown default: fatalError("path element")
        }
    }
    return data
}

/// The `index`th subpath (from 1) of `data` alone, its move made absolute.
func subpath(_ index: Int, of data: String) -> String {
    let all = parse(data).subpaths
    let (_, first) = all[index - 1]
    let end = index < all.count ? all[index].start : data.endIndex
    var rest = String(data[first.rest..<end]).trimmingCharacters(in: .whitespaces)
    // Coordinates straight after a move are lines, relative after a relative move.
    if let c = rest.first, !c.isLetter { rest = (first.relative ? "l" : "L") + rest }
    return String(format: "M%g %g", first.point.x, first.point.y) + rest
}

/// SVG's arc as CoreGraphics curves (SVG 1.1, F.6.5).
func addArc(_ path: CGMutablePath, from p0: CGPoint, to p1: CGPoint, rx: CGFloat, ry: CGFloat, angle: CGFloat, large: Bool, sweep: Bool) {
    if rx == 0 || ry == 0 { path.addLine(to: p1); return }
    let dx = (p0.x - p1.x) / 2, dy = (p0.y - p1.y) / 2
    let x1 = cos(angle) * dx + sin(angle) * dy, y1 = -sin(angle) * dx + cos(angle) * dy
    var rx = rx, ry = ry
    let lambda = x1 * x1 / (rx * rx) + y1 * y1 / (ry * ry)
    if lambda > 1 { rx *= sqrt(lambda); ry *= sqrt(lambda) }
    let sign: CGFloat = large == sweep ? -1 : 1
    let numerator = max(0, rx * rx * ry * ry - rx * rx * y1 * y1 - ry * ry * x1 * x1)
    let factor = sign * sqrt(numerator / (rx * rx * y1 * y1 + ry * ry * x1 * x1))
    let cx1 = factor * rx * y1 / ry, cy1 = -factor * ry * x1 / rx
    let center = CGPoint(x: cos(angle) * cx1 - sin(angle) * cy1 + (p0.x + p1.x) / 2, y: sin(angle) * cx1 + cos(angle) * cy1 + (p0.y + p1.y) / 2)
    let theta = atan2((y1 - cy1) / ry, (x1 - cx1) / rx)
    var delta = atan2((-y1 - cy1) / ry, (-x1 - cx1) / rx) - theta
    if sweep && delta < 0 { delta += 2 * .pi }
    if !sweep && delta > 0 { delta -= 2 * .pi }
    let transform = CGAffineTransform(translationX: center.x, y: center.y).rotated(by: angle).scaledBy(x: rx, y: ry)
    path.addRelativeArc(center: .zero, radius: 1, startAngle: theta, delta: delta, transform: transform)
}

/// The SF Symbol `name` at 100 pt, regular, `scale`, configured further by `style`, and its drawing at 4 pixels a point.
func render(_ name: String, scale: NSImage.SymbolScale = .medium, style: NSImage.SymbolConfiguration? = nil) -> (image: NSImage, bitmap: NSBitmapImageRep) {
    var configuration = NSImage.SymbolConfiguration(pointSize: 100, weight: .regular, scale: scale)
    if let style { configuration = configuration.applying(style) }
    guard let image = NSImage(systemSymbolName: name, accessibilityDescription: nil)?.withSymbolConfiguration(configuration) else {
        fatalError("no SF Symbol \(name)")
    }
    let size = image.size
    let pixels = 4.0
    let bitmap = NSBitmapImageRep(bitmapDataPlanes: nil, pixelsWide: Int(ceil(size.width * pixels)), pixelsHigh: Int(ceil(size.height * pixels)),
                                  bitsPerSample: 8, samplesPerPixel: 4, hasAlpha: true, isPlanar: false, colorSpaceName: .deviceRGB, bytesPerRow: 0, bitsPerPixel: 0)!
    bitmap.size = NSSize(width: Double(bitmap.pixelsWide) / pixels, height: Double(bitmap.pixelsHigh) / pixels)
    NSGraphicsContext.saveGraphicsState()
    NSGraphicsContext.current = NSGraphicsContext(bitmapImageRep: bitmap)
    image.draw(in: NSRect(origin: .zero, size: size), from: .zero, operation: .sourceOver, fraction: 1)
    NSGraphicsContext.restoreGraphicsState()
    return (image, bitmap)
}

/// The rectangle, in points from the top left, of the pixels `ink` picks.
func inkRectangle(_ bitmap: NSBitmapImageRep, _ ink: (NSColor) -> Bool) -> CGRect {
    var minX = Int.max, minY = Int.max, maxX = -1, maxY = -1
    for y in 0..<bitmap.pixelsHigh {
        for x in 0..<bitmap.pixelsWide where ink(bitmap.colorAt(x: x, y: y)!) {
            minX = min(minX, x); maxX = max(maxX, x); minY = min(minY, y); maxY = max(maxY, y)
        }
    }
    precondition(maxX >= 0, "no ink")
    let pixels = Double(bitmap.pixelsWide) / bitmap.size.width
    return CGRect(x: Double(minX) / pixels, y: Double(minY) / pixels, width: Double(maxX + 1 - minX) / pixels, height: Double(maxY + 1 - minY) / pixels)
}

/// The image's size (SwiftUI's frame for it), its ink's rectangle from the top left, and its baseline from the top, in
/// points at 100 pt.
func measure(_ name: String, scale: NSImage.SymbolScale = .medium) -> [Double] {
    let (image, bitmap) = render(name, scale: scale)
    let ink = inkRectangle(bitmap) { $0.alphaComponent >= 0.5 }
    // The alignment rectangle runs from the baseline up the cap height: its bottom is the baseline, from the image's.
    let baseline = image.size.height - image.alignmentRect.minY
    return [image.size.width, image.size.height, ink.minX, ink.minY, ink.width, ink.height, baseline]
}

/// Where the symbol's inner glyph lies, in points from the top left at 100 pt: its primary layer, as palette rendering
/// draws it.
func innerGlyph(_ name: String) -> CGRect {
    let palette = NSImage.SymbolConfiguration(paletteColors: [NSColor(deviceRed: 1, green: 0, blue: 0, alpha: 1), NSColor(deviceRed: 0, green: 0, blue: 1, alpha: 1), NSColor(deviceRed: 0, green: 0, blue: 1, alpha: 1)])
    return inkRectangle(render(name, style: palette).bitmap) { $0.alphaComponent >= 0.5 && $0.redComponent >= 0.5 }
}

/// The opacity hierarchical rendering gives the symbol's lower layer: the commonest alpha between none and full.
func lowerOpacity(_ name: String) -> Double {
    let bitmap = render(name, style: NSImage.SymbolConfiguration(hierarchicalColor: NSColor(deviceRed: 0, green: 0, blue: 0, alpha: 1))).bitmap
    var counts: [Int: Int] = [:]
    for y in 0..<bitmap.pixelsHigh {
        for x in 0..<bitmap.pixelsWide {
            let alpha = Int((bitmap.colorAt(x: x, y: y)!.alphaComponent * 1000).rounded())
            if alpha > 50 && alpha < 950 { counts[alpha, default: 0] += 1 }
        }
    }
    return Double(counts.max { $0.value < $1.value }!.key) / 1000
}

func fetch(_ name: String, fill: Bool, weight: Int, opticalSize: Int) throws -> String {
    var suffix = weight == 400 ? "" : "wght\(weight)"
    if fill { suffix += "fill1" }
    let file = "\(name)\(suffix.isEmpty ? "" : "_" + suffix)_\(opticalSize)px.svg"
    let local = downloads.appendingPathComponent(file)
    if !FileManager.default.fileExists(atPath: local.path) {
        let remote = URL(string: "https://raw.githubusercontent.com/google/material-design-icons/\(revision)/symbols/web/\(name)/materialsymbolsrounded/\(file)")!
        let data = try Data(contentsOf: remote)
        try data.write(to: local)
    }
    let svg = try String(contentsOf: local, encoding: .utf8)
    // Most are drawn on a 960-unit grid, some older ones on a grid of their pixel size. PeekSymbol places outlines by
    // their bounds, so any does.
    precondition(svg.contains("viewBox=\"0 -960 960 960\"") || (svg.contains("height=\"\(opticalSize)\"") && !svg.contains("viewBox")), "\(file): another grid")
    let paths = svg.components(separatedBy: " d=\"").dropFirst().map { $0.prefix { $0 != "\"" } }
    precondition(paths.count == 1, "\(file): \(paths.count) paths")
    let data = String(paths[0])
    precondition(data.allSatisfy { "0123456789.-+eMmLlHhVvCcSsQqTtAaZz ,".contains($0) }, "\(file): unexpected path data")
    return data
}

func decimal(_ value: Double) -> String { String(format: "%.2f", value) + "f" }

/// The outline `spec` names at a weight and optical size, as SVG path data: an icon, `name#n` its nth subpath alone, or
/// `a + b` and `a - b`, b drawn over a or cut out of it where SF draws its inner glyph (`glyph`) within its ink (`ink`).
func outline(_ spec: String, fill: Bool, weight: Int, opticalSize: Int, ink: CGRect, glyph: CGRect) throws -> String {
    let terms = spec.components(separatedBy: " ")
    if terms.count == 3 {
        precondition(terms[1] == "+" || terms[1] == "-", "\(spec): a + b or a - b")
        let base = parse(try outline(terms[0], fill: fill, weight: weight, opticalSize: opticalSize, ink: ink, glyph: glyph))
        let inner = parse(try outline(terms[2], fill: fill, weight: weight, opticalSize: opticalSize, ink: ink, glyph: glyph))
        // PeekSymbol fits the base to SF's ink, so an SF point is 1 / fit units of its grid there.
        let fit = min(ink.width / base.bounds.width, ink.height / base.bounds.height)
        let scale = min(glyph.width / inner.bounds.width, glyph.height / inner.bounds.height) / fit
        var transform = CGAffineTransform(translationX: base.bounds.midX + (glyph.midX - ink.midX) / fit, y: base.bounds.midY + (glyph.midY - ink.midY) / fit)
            .scaledBy(x: scale, y: scale).translatedBy(x: -inner.bounds.midX, y: -inner.bounds.midY)
        let placed = inner.path.copy(using: &transform)!
        let composed = terms[1] == "+" ? base.path.union(placed) : base.path.subtracting(placed)
        precondition(composed.boundingBoxOfPath.insetBy(dx: -0.01, dy: -0.01).contains(base.bounds) && base.bounds.insetBy(dx: -0.01, dy: -0.01).contains(composed.boundingBoxOfPath), "\(spec): the glyph leaves the base")
        return pathData(composed)
    }
    let parts = spec.split(separator: "#")
    let data = try fetch(String(parts[0]), fill: fill, weight: weight, opticalSize: opticalSize)
    return parts.count == 2 ? subpath(Int(parts[1])!, of: data) : data
}

/// A filled icon's layers for hierarchical rendering, back to front: its outline without its holes, then its holes
/// filled, as SF draws a .fill symbol's inner glyph over its shape.
func layers(_ data: String) -> [String] {
    let subpaths = parse(data).subpaths.indices.map { subpath($0 + 1, of: data) }
    let bounds = subpaths.map { parse($0).bounds }
    let holes = Set(bounds.indices.filter { i in bounds.indices.contains { $0 != i && bounds[$0].contains(bounds[i]) } })
    precondition(!holes.isEmpty && holes.count < subpaths.count, "no holes to draw")
    return [subpaths.indices.filter { !holes.contains($0) }, holes.sorted()].map { $0.map { subpaths[$0] }.joined() }
}

var lines: [String] = []
var drawn = 0
var outlines = 0
for row in rows where row.material != "-" {
    var needed: [(weight: Int, opticalSize: Int)] = []
    for use in row.uses { if let v = variant(use), !needed.contains(where: { $0 == v }) { needed.append(v) } }
    if needed.isEmpty { continue }
    needed.sort { ($0.weight, $0.opticalSize) < ($1.weight, $1.opticalSize) }
    precondition(!row.hierarchical || row.fill, "\(row.sf): a hierarchical symbol is drawn from a filled icon")
    drawn += 1
    let measured = measure(row.sf)
    let ink = CGRect(x: measured[2], y: measured[3], width: measured[4], height: measured[5])
    let glyph = row.material.contains(" ") ? innerGlyph(row.sf) : .zero
    var variants: [String] = []
    for v in needed {
        let data = try outline(row.material, fill: row.fill, weight: v.weight, opticalSize: v.opticalSize, ink: ink, glyph: glyph)
        let box = parse(data).bounds
        let layered = row.hierarchical ? ", layerData = listOf(" + layers(data).map { "\"\($0)\"" }.joined(separator: ", ") + ")" : ""
        variants.append("            MaterialOutline(\(v.weight), \(v.opticalSize), floatArrayOf(\(decimal(box.minX)), \(decimal(box.minY)), \(decimal(box.width)), \(decimal(box.height))), \"\(data)\"\(layered)),")
        outlines += 1
    }
    let opacity = row.hierarchical ? ", lowerOpacity = \(decimal(lowerOpacity(row.sf)))" : ""
    lines.append("""
            "\(row.sf)" to SymbolArt(
                "\(row.material)", fill = \(row.fill), sf = floatArrayOf(\(measured.map(decimal).joined(separator: ", ")))\(opacity),
                outlines = listOf(
        \(variants.joined(separator: "\n"))
                ),
            ),
        """)
}

// How much `.imageScale(.small)` and `.large` scale a symbol, from a plain one, and the cap height they keep it centred
// on: the alignment rectangle, from the baseline up, stays where the text puts it while the image grows round it.
let medium = measure("circle")
let small = measure("circle", scale: .small)
let large = measure("circle", scale: .large)
let capHeight = render("circle").image.alignmentRect.height
for scaled in [small, large] {
    let modelled = (medium[6] - capHeight / 2) * scaled[1] / medium[1] + capHeight / 2
    precondition(abs(modelled - scaled[6]) < 1.5, "an image scale no longer keeps the symbol on the cap height's middle")
}

let kotlin = """
// Generated by android/tools/MaterialSymbols.swift from android/tools/material-symbols.tsv; run it again rather than
// editing this file. The outlines are Material Symbols Rounded from google/material-design-icons at \(revision),
// under the Apache License 2.0 (android/app/src/main/assets/MATERIAL-SYMBOLS-LICENSE.txt, which the app carries).
package com.plhery.peekdeliverytracker.ui.symbols

/** Material Symbols' revision the outlines come from. */
internal const val MATERIAL_SYMBOLS_REVISION = "\(revision)"

/** `.imageScale(.small)` and `.large` against `.medium`: the image's height, as AppKit draws `circle`. */
internal const val SMALL_SCALE = \(decimal(small[1] / medium[1]))
internal const val LARGE_SCALE = \(decimal(large[1] / medium[1]))

/** SF's cap height at 100 pt, the height of a symbol's alignment rectangle: an image scale keeps its middle in place. */
internal const val CAP_HEIGHT = \(decimal(capHeight))

/** The SF Symbols Android draws (\(drawn)), each as its Material Symbols outlines (\(outlines)). */
internal val materialSymbols: Map<String, SymbolArt> = mapOf(
\(lines.joined(separator: "\n"))
)

"""
try kotlin.write(to: output, atomically: true, encoding: .utf8)
print("\(drawn) symbols, \(outlines) outlines, small \(small[1] / medium[1]), large \(large[1] / medium[1]), cap height \(capHeight)")
