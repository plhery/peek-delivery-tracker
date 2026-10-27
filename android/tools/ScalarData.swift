// Writes android/core/src/main/kotlin/com/plhery/peekdeliverytracker/core/ScalarData.kt: the scalar properties the
// Android core reads where Swift's standard library answers from its own Unicode version and a phone's ICU from the
// phone's (Unicode 13 on Android 12, the oldest the app runs on). General_Category=Format, which
// `ParcelLinkWords.clean` drops, and what Normalization.kt needs to normalize text as Swift compares it: every scalar's
// canonical combining class, the scalars whose normalization Unicode 13 does not know, and the canonical
// decompositions added since. The categories, classes and ages are the standard library's own; the decompositions,
// which it does not show, come from its version's data files and are checked against its `==`.
//
// Run it on a Mac from the repository root: `swift android/tools/ScalarData.swift`. It downloads the data files from
// unicode.org. Run it again after each major macOS release, whose Swift is that year's iOS one, as GraphemeData.swift;
// it stops if Unicode has changed the normalization of a scalar Unicode 13 already had, which the delta cannot cover.
import Foundation

let output = URL(fileURLWithPath: #filePath)
    .deletingLastPathComponent()
    .appendingPathComponent("../core/src/main/kotlin/com/plhery/peekdeliverytracker/core/ScalarData.kt")
    .standardizedFileURL

/// Android 12's ICU 68.
let oldestPhone = (major: 13, minor: 0)

// The standard library's Unicode version: the newest age its scalars have.
var newest = (major: 0, minor: 0)
for value in 0...0x10FFFF {
    guard let age = Unicode.Scalar(value)?.properties.age else { continue }
    if age.major > newest.major || (age.major == newest.major && age.minor > newest.minor) { newest = age }
}
let version = "\(newest.major).\(newest.minor).0"
let oldestVersion = "\(oldestPhone.major).\(oldestPhone.minor).0"

func download(_ version: String, _ path: String) -> String {
    let url = URL(string: "https://www.unicode.org/Public/\(version)/ucd/\(path)")!
    guard let data = try? Data(contentsOf: url), let text = String(data: data, encoding: .utf8) else {
        fatalError("Could not download \(url)")
    }
    return text
}

/// Canonical decompositions (one level) and combining classes from UnicodeData.txt.
func unicodeData(_ version: String) -> (decompositions: [Int: [Int]], classes: [Int: Int]) {
    var decompositions: [Int: [Int]] = [:]
    var classes: [Int: Int] = [:]
    for line in download(version, "UnicodeData.txt").split(separator: "\n") {
        let fields = line.split(separator: ";", omittingEmptySubsequences: false)
        let scalar = Int(fields[0], radix: 16)!
        if let value = Int(fields[3]), value != 0 { classes[scalar] = value }
        let mapping = fields[5]
        if !mapping.isEmpty, !mapping.hasPrefix("<") {
            decompositions[scalar] = mapping.split(separator: " ").map { Int($0, radix: 16)! }
        }
    }
    return (decompositions, classes)
}

func compositionExclusions(_ version: String) -> Set<Int> {
    var excluded = Set<Int>()
    for line in download(version, "DerivedNormalizationProps.txt").split(separator: "\n") {
        let data = line.split(separator: "#", maxSplits: 1, omittingEmptySubsequences: false)[0]
        let fields = data.split(separator: ";").map { $0.trimmingCharacters(in: .whitespaces) }
        guard fields.count >= 2, fields[1] == "Full_Composition_Exclusion" else { continue }
        let bounds = fields[0].components(separatedBy: "..").map { Int($0, radix: 16)! }
        excluded.formUnion(bounds[0]...bounds[bounds.count - 1])
    }
    return excluded
}

func string(_ scalars: [Int]) -> String {
    var text = ""
    text.unicodeScalars.append(contentsOf: scalars.map { Unicode.Scalar($0)! })
    return text
}

func hex(_ scalar: Int) -> String { "U+" + String(scalar, radix: 16, uppercase: true) }

let current = unicodeData(version)
let oldest = unicodeData(oldestVersion)
let excluded = compositionExclusions(version)
let oldestExcluded = compositionExclusions(oldestVersion)

func isNew(_ scalar: Int) -> Bool {
    guard let age = Unicode.Scalar(scalar)?.properties.age else { return false }
    return age.major > oldestPhone.major || (age.major == oldestPhone.major && age.minor > oldestPhone.minor)
}

var format: [Int] = []
var classRuns: [String] = []
var previousClass = -1
var delta = Set<Int>()
for value in 0...0x10FFFF {
    let properties = Unicode.Scalar(value)?.properties
    // Format ranges: the first scalar of each, and the scalar after its last.
    if (properties?.generalCategory == .format) != (format.count % 2 == 1) { format.append(value) }
    let combiningClass = Int(properties?.canonicalCombiningClass.rawValue ?? 0)
    if combiningClass != previousClass {
        classRuns.append(String(format: "0x%08X", value << 8 | combiningClass))
        previousClass = combiningClass
    }
    if combiningClass != (current.classes[value] ?? 0) {
        fatalError("The standard library's combining class of \(hex(value)) is not its data's")
    }
    guard properties?.age != nil else { continue }
    if isNew(value) {
        if combiningClass != 0 || current.decompositions[value] != nil { delta.insert(value) }
    } else if current.decompositions[value] != oldest.decompositions[value] || combiningClass != (oldest.classes[value] ?? 0) ||
        excluded.contains(value) != oldestExcluded.contains(value) {
        fatalError("Unicode changed the normalization of \(hex(value)), which Unicode \(oldestVersion) had: Normalization.kt's delta does not cover it")
    }
}
if format.count % 2 == 1 { format.append(0x110000) }

// The decompositions added since, which the standard library must find equivalent; the new scalars they are made of
// join the delta, so text holding them is normalized here and composes.
let added = current.decompositions.keys.filter(isNew).sorted()
var decompositions: [String] = []
var exclusions: [String] = []
for composite in added {
    let parts = current.decompositions[composite]!
    guard parts.count <= 2 else { fatalError("\(hex(composite)) decomposes to more than two scalars") }
    guard string([composite]) == string(parts) else { fatalError("Swift does not find \(hex(composite)) equivalent to its decomposition") }
    // Text without a delta scalar goes to the phone's normalizer, which must not miss a composition.
    guard parts.contains(where: isNew) else { fatalError("\(hex(composite)) is made of scalars Unicode \(oldestVersion) had: text without a delta scalar would compose to it") }
    delta.formUnion(parts.filter(isNew))
    let second = parts.count == 2 ? String(format: "0x%05X", parts[1]) : "-1"
    decompositions.append(String(format: "0x%05X, 0x%05X, ", composite, parts[0]) + second)
    if excluded.contains(composite) { exclusions.append(String(format: "0x%05X", composite)) }
}

func lines(_ items: [String], perLine: Int) -> String {
    stride(from: 0, to: items.count, by: perLine)
        .map { "        " + items[$0..<min($0 + perLine, items.count)].joined(separator: ", ") + "," }
        .joined(separator: "\n")
}

let source = """
// Generated by android/tools/ScalarData.swift from Swift's standard library and the Unicode \(version) data files: do not edit.
package com.plhery.peekdeliverytracker.core

/**
 * Scalar properties as Swift's standard library has them, from Unicode \(version), where a phone's ICU has its own
 * version's (Unicode \(oldestVersion) on Android 12).
 */
internal object ScalarData {
    const val unicodeVersion = "\(version)"

    /** The Unicode version of the oldest phone the app runs on, which the normalization delta is counted from. */
    const val oldestPhoneVersion = "\(oldestVersion)"

    /** General_Category=Format, as ranges in order: the first scalar of each, then the scalar after its last. */
    val format = intArrayOf(
\(lines(format.map { String(format: "0x%05X", $0) }, perLine: 8))
    )

    /**
     * Canonical_Combining_Class: runs of scalars with the same class, in order, each lasting until the next: the run's
     * first scalar shifted left 8 bits, and below it the class.
     */
    val combiningClasses = intArrayOf(
\(lines(classRuns, perLine: 8))
    )

    /**
     * The scalars added since Unicode \(oldestVersion) that have a combining class or a canonical decomposition, or are
     * part of one, in order. A phone as old reads them as unassigned: no class, no decomposition, no composition.
     */
    val normalizationDelta = intArrayOf(
\(lines(delta.sorted().map { String(format: "0x%05X", $0) }, perLine: 8))
    )

    /** The canonical decompositions added since Unicode \(oldestVersion), in order: a scalar and the one or two (else -1) it decomposes to. */
    val decompositions = intArrayOf(
\(lines(decompositions, perLine: 3))
    )

    /** Those of them that never compose (Full_Composition_Exclusion). */
    val compositionExclusions = intArrayOf(\(exclusions.joined(separator: ", ")))
}

"""
try source.write(to: output, atomically: true, encoding: .utf8)
print("Wrote Unicode \(version) to \(output.path): \(format.count / 2) format ranges, \(classRuns.count) class runs, \(delta.count) delta scalars, \(added.count) decompositions")
