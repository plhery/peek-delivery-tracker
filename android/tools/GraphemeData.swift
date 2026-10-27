// Writes android/core/src/main/kotlin/com/plhery/peekdeliverytracker/core/GraphemeData.kt: the scalar properties
// Swift's standard library splits a String into Characters with, for GraphemeBreaking.kt. Android's ICU is as old as
// the phone (Unicode 13 on Android 12), so the Android app carries the properties of the Unicode version this Mac's
// Swift has, read from that version's data files as the standard library's own generator reads them
// (swift/utils/gen-unicode-data, GenGraphemeBreakProperty and GenEmoji): Control, Extend, Prepend and SpacingMark from
// GraphemeBreakProperty.txt, Extended_Pictographic from emoji-data.txt and InCB=Consonant from
// DerivedCoreProperties.txt. The other break properties and the Indic conjunct linkers are written in the code, in
// Swift's and in GraphemeBreaking.kt alike.
//
// Run it on a Mac from the repository root: `swift android/tools/GraphemeData.swift`. It downloads the data files from
// unicode.org. Run it again after each major macOS release, whose Swift is that year's iOS one; it stops if that Swift
// no longer splits text the way GraphemeBreaking.kt does in the parts written in the code.
import Foundation

let output = URL(fileURLWithPath: #filePath)
    .deletingLastPathComponent()
    .appendingPathComponent("../core/src/main/kotlin/com/plhery/peekdeliverytracker/core/GraphemeData.kt")
    .standardizedFileURL

// The standard library's Unicode version: the newest age its scalars have.
var newest = (major: 0, minor: 0)
for value in 0...0x10FFFF {
    guard let age = Unicode.Scalar(value)?.properties.age else { continue }
    if age.major > newest.major || (age.major == newest.major && age.minor > newest.minor) { newest = age }
}
let version = "\(newest.major).\(newest.minor).0"

func download(_ path: String) -> String {
    let url = URL(string: "https://www.unicode.org/Public/\(version)/ucd/\(path)")!
    guard let data = try? Data(contentsOf: url), let text = String(data: data, encoding: .utf8) else {
        fatalError("Could not download \(url)")
    }
    return text
}

/// Each data line's scalars and its fields after them.
func entries(_ text: String) -> [(scalars: ClosedRange<Int>, fields: [String])] {
    text.split(separator: "\n").compactMap { line in
        let data = line.split(separator: "#", maxSplits: 1, omittingEmptySubsequences: false)[0]
        let fields = data.split(separator: ";").map { $0.trimmingCharacters(in: .whitespaces) }
        guard fields.count >= 2, !fields[0].isEmpty else { return nil }
        let bounds = fields[0].components(separatedBy: "..").map { Int($0, radix: 16)! }
        return (bounds[0]...bounds[bounds.count - 1], Array(fields.dropFirst()))
    }
}

// GraphemeBreaking.kt's numbering.
let breakProperties = ["Control": 1, "CR": 1, "LF": 1, "Extend": 2, "Prepend": 3, "SpacingMark": 4]
let extendedPictographic = 8
let conjunctConsonant = 16

var properties = [UInt8](repeating: 0, count: 0x110000)
var writtenInCode: [(ClosedRange<Int>, String)] = []
for (scalars, fields) in entries(download("auxiliary/GraphemeBreakProperty.txt")) {
    guard let property = breakProperties[fields[0]] else {
        writtenInCode.append((scalars, fields[0]))
        continue
    }
    // The standard library writes long ranges in the code: E01F0...E0FFF is Control.
    guard scalars.count < 0x200 else {
        guard scalars == 0xE01F0...0xE0FFF else { fatalError("A new long range: \(scalars) \(fields[0])") }
        continue
    }
    for scalar in scalars { properties[scalar] |= UInt8(property) }
}
for (scalars, fields) in entries(download("emoji/emoji-data.txt")) where fields[0] == "Extended_Pictographic" {
    for scalar in scalars { properties[scalar] |= UInt8(extendedPictographic) }
}
var linkers: [Int] = []
for (scalars, fields) in entries(download("DerivedCoreProperties.txt")) where fields[0] == "InCB" {
    if fields[1] == "Consonant" { for scalar in scalars { properties[scalar] |= UInt8(conjunctConsonant) } }
    if fields[1] == "Linker" { linkers += scalars }
}

// The parts written in the code must still be Swift's. Of the data's Indic conjunct linkers, Swift joins a consonant
// across the six it names (Devanagari, Bengali, Gujarati, Oriya, Telugu, Malayalam) and no other.
func string(_ scalars: [Int]) -> String {
    var text = ""
    text.unicodeScalars.append(contentsOf: scalars.map { Unicode.Scalar($0)! })
    return text
}
let swiftLinkers = [0x094D, 0x09CD, 0x0ACD, 0x0B4D, 0x0C4D, 0x0D4D]
for linker in linkers where (string([0x0915, linker, 0x0915]).count == 1) != swiftLinkers.contains(linker) {
    fatalError("Swift has changed the Indic conjunct linkers it knows (U+\(String(linker, radix: 16, uppercase: true))): update GraphemeBreaking.kt")
}
// Hangul jamo, regional indicators and the zero width joiner are the ranges Swift names; the data's others of these
// kinds (Kirat Rai's vowel signs, V) are anything else to Swift, so they neither join a jamo nor a jamo them.
let namedInCode: [String: [ClosedRange<Int>]] = [
    "L": [0x1100...0x115F, 0xA960...0xA97C], "V": [0x1160...0x11A7, 0xD7B0...0xD7C6],
    "T": [0x11A8...0x11FF, 0xD7CB...0xD7FB], "LV": [0xAC00...0xD7A3], "LVT": [0xAC00...0xD7A3],
    "Regional_Indicator": [0x1F1E6...0x1F1FF], "ZWJ": [0x200D...0x200D],
]
for (scalars, property) in writtenInCode {
    guard let named = namedInCode[property] else { fatalError("A new break property: \(property)") }
    for scalar in scalars where !named.contains(where: { $0.contains(scalar) }) {
        if string([0x1100, scalar]).count != 2 || string([scalar, 0x11A8]).count != 2 || string([scalar, 0x1160]).count != 2 {
            fatalError("Swift has changed the \(property) scalars it knows (U+\(String(scalar, radix: 16, uppercase: true))): update GraphemeBreaking.kt")
        }
    }
}

// Runs of scalars with the same properties, each the first scalar shifted left 8 bits with its properties below.
var runs: [String] = []
for scalar in 0..<properties.count where scalar == 0 || properties[scalar] != properties[scalar - 1] {
    runs.append(String(format: "0x%08X", scalar << 8 | Int(properties[scalar])))
}
let lines = stride(from: 0, to: runs.count, by: 8).map { "        " + runs[$0..<min($0 + 8, runs.count)].joined(separator: ", ") + "," }

let source = """
// Generated by android/tools/GraphemeData.swift from the Unicode \(version) data files: do not edit.
package com.plhery.peekdeliverytracker.core

/** The scalar properties Swift's standard library finds Characters with, from Unicode \(version), the version it has. */
internal object GraphemeData {
    const val unicodeVersion = "\(version)"

    /**
     * Runs of scalars with the same properties, in order, each lasting until the next: the run's first scalar shifted
     * left 8 bits, and below it the properties. The low three bits are a Grapheme_Cluster_Break (0 none of these,
     * 1 Control, CR or LF, 2 Extend, 3 Prepend, 4 SpacingMark), 8 is Extended_Pictographic and 16 InCB=Consonant.
     */
    val runs = intArrayOf(
\(lines.joined(separator: "\n"))
    )
}

"""
try source.write(to: output, atomically: true, encoding: .utf8)
print("Wrote \(runs.count) runs of Unicode \(version) to \(output.path)")
