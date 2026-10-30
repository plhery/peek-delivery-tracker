// Writes android/core/src/main/resources/peek/CountryNames.json: the regions and region names the iPhone app
// gets from Foundation (`Locale.Region.isoRegions`, `Locale.localizedString(forRegionCode:)`), in each of the
// app's languages, for Android, whose CLDR names differ in places ("Hong Kong SAR China", "China mainland").
//
// Run it on a Mac from the repository root: `swift android/tools/CountryNames.swift`. Run it again when the app
// gains a language, and after each major macOS release, whose names are the ones that year's iOS shows.
import Foundation

// AppLanguage's codes and `locale` (`ExtensionLocalizer.locale(for:)`).
let languages = ["en", "de", "fr", "it", "es", "pt", "pt-br", "pl", "ru", "zh"]
func locale(_ language: String) -> Locale {
    switch language {
    case "pt": Locale(identifier: "pt-PT")
    case "pt-br": Locale(identifier: "pt-BR")
    case "zh": Locale(identifier: "zh-Hans")
    default: Locale(identifier: language)
    }
}

let regions = Locale.Region.isoRegions.map(\.identifier).sorted()
let letters = (UInt8(ascii: "A")...UInt8(ascii: "Z")).map { String(UnicodeScalar($0)) }
// Every code a name can be asked for: the ISO regions, and the other two-letter codes Foundation names (UK, SU).
let codes = Set(regions + letters.flatMap { first in letters.map { first + $0 } }).sorted()

func regionNames(_ locale: Locale) -> [String: String] {
    Dictionary(uniqueKeysWithValues: codes.compactMap { code in
        locale.localizedString(forRegionCode: code).map { (code, $0) }
    })
}

var names: [String: [String: String]] = [:]
for language in languages {
    names[language] = regionNames(locale(language))
}

let output = URL(fileURLWithPath: #filePath)
    .deletingLastPathComponent()
    .appendingPathComponent("../core/src/main/resources/peek/CountryNames.json")
    .standardizedFileURL
let data = try JSONSerialization.data(
    withJSONObject: ["regions": regions, "names": names],
    options: [.prettyPrinted, .sortedKeys, .withoutEscapingSlashes]
)
try (data + Data("\n".utf8)).write(to: output)
print("Wrote \(output.path)")
