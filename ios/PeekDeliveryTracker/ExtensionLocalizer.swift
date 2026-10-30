import Foundation

/// The app's copy in its extensions, which can't reach the app's Localizer. Each extension
/// bundles its own Localization.json; a key missing in one language falls back to English.
struct ExtensionLocalizer {
    let languageCode: String
    private let dictionaries: [String: [String: String]]

    static let bundledDictionaries: [String: [String: String]] = {
        guard let url = Bundle.main.url(forResource: "Localization", withExtension: "json"),
              let data = try? Data(contentsOf: url),
              let values = try? JSONDecoder().decode([String: [String: String]].self, from: data) else {
            return [:]
        }
        return values
    }()

    init(languageCode: String, dictionaries: [String: [String: String]] = bundledDictionaries) {
        self.languageCode = languageCode
        self.dictionaries = dictionaries
    }

    init(savedLanguageCode: String?) {
        self.init(languageCode: Self.languageCode(
            saved: savedLanguageCode,
            preferred: Locale.preferredLanguages,
            available: Set(Self.bundledDictionaries.keys)
        ))
    }

    /// The language saved by the app, else the first of the device's languages Peek speaks (`language(tag:)`),
    /// else English.
    static func languageCode(saved: String?, preferred: [String], available: Set<String>) -> String {
        ([saved].compactMap { $0 } + preferred.compactMap(language(tag:))).first { available.contains($0) } ?? "en"
    }

    /// The language a device's language tag names, as Peek's languages are named and as the web reads a tag
    /// (`tagLocale`): Portuguese from Brazil is `pt-br` and any other `pt`; Chinese is `zh`, Simplified Chinese,
    /// unless it is written in Traditional characters (`Hant`, or no script and Taiwan, Hong Kong or Macau),
    /// which Peek doesn't speak; any other language is its first subtag, which Peek may not speak either.
    static func language(tag: String) -> String? {
        let subtags = tag.trimmingCharacters(in: .whitespacesAndNewlines).lowercased()
            .replacingOccurrences(of: "_", with: "-")
            .split(separator: "-", omittingEmptySubsequences: false).map(String.init)
        guard let language = subtags.first, !language.isEmpty else { return nil }
        let rest = subtags.dropFirst()
        switch language {
        case "pt":
            return rest.contains("br") ? "pt-br" : "pt"
        case "zh":
            let script = rest.first { $0.count == 4 && $0.allSatisfy { $0.isASCII && $0.isLetter } }
            let traditional = script.map { $0 == "hant" } ?? rest.contains { ["tw", "hk", "mo"].contains($0) }
            return traditional ? nil : "zh"
        default:
            return language
        }
    }

    /// The locale dates and numbers take in a language: Portuguese is Portugal's, Brazilian Portuguese
    /// Brazil's and Chinese Simplified Chinese.
    static func locale(for languageCode: String) -> Locale {
        switch languageCode {
        case "pt": Locale(identifier: "pt-PT")
        case "pt-br": Locale(identifier: "pt-BR")
        case "zh": Locale(identifier: "zh-Hans")
        default: Locale(identifier: languageCode)
        }
    }

    func text(_ key: String) -> String {
        dictionaries[languageCode]?[key]
            ?? dictionaries["en"]?[key]
            ?? key
    }
}
