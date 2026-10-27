// Fills in android/core/src/test/resources/map-text-widths.tsv: how wide the iPhone's system font writes each name the
// map layout tests measure, `(text as NSString).size(withAttributes: [.font: font]).width`, which RecordedTextMeasurer
// gives the tests in place of the platform's text metrics. Each row is a point size, a weight, a text and its width,
// invisible characters written as `\uXXXX`.
//
// Run it on a Mac from the repository root: `swift android/tools/MapTextWidths.swift`. A test that measures a name the
// file lacks fails and names it: add a row with its size, weight and text (the width can be left out) and run this
// again. Run it again after each major macOS release, whose system font is that year's iOS one: AppKit's writes these
// sizes and weights exactly as wide as UIKit's.
import AppKit

let file = URL(fileURLWithPath: #filePath)
    .deletingLastPathComponent()
    .appendingPathComponent("../core/src/test/resources/map-text-widths.tsv")
    .standardizedFileURL

// MapFont.Weight's names.
let weights: [String: NSFont.Weight] = ["Regular": .regular, "Medium": .medium, "Semibold": .semibold]

func unescaped(_ text: String) -> String {
    var result = ""
    var rest = Substring(text)
    while let range = rest.range(of: "\\u"), let scalar = UInt32(rest[range.upperBound...].prefix(4), radix: 16).flatMap(Unicode.Scalar.init) {
        result += rest[..<range.lowerBound]
        result.unicodeScalars.append(scalar)
        rest = rest[range.upperBound...].dropFirst(4)
    }
    return result + rest
}

func escaped(_ text: String) -> String {
    let invisible: Set<Unicode.GeneralCategory> = [.control, .format, .spaceSeparator, .lineSeparator, .paragraphSeparator]
    return text.unicodeScalars.map { scalar in
        scalar != " " && invisible.contains(scalar.properties.generalCategory) ? String(format: "\\u%04X", scalar.value) : String(scalar)
    }.joined()
}

var rows: [String: String] = [:]
for line in try String(contentsOf: file, encoding: .utf8).split(separator: "\n") where !line.isEmpty {
    let fields = line.split(separator: "\t", omittingEmptySubsequences: false).map(String.init)
    guard fields.count >= 3, let size = Double(fields[0]), let weight = weights[fields[1]] else { fatalError("Not a row: \(line)") }
    let text = unescaped(fields[2])
    let font = NSFont.systemFont(ofSize: size, weight: weight)
    let key = "\(size)\t\(fields[1])\t\(escaped(text))"
    rows[key] = "\(key)\t\(Double((text as NSString).size(withAttributes: [.font: font]).width))"
}
let keys = rows.keys.sorted { $0.unicodeScalars.lexicographicallyPrecedes($1.unicodeScalars) }
try keys.map { rows[$0]! + "\n" }.joined().write(to: file, atomically: true, encoding: .utf8)
print("Wrote \(rows.count) widths to \(file.path)")
