import Combine
import Foundation

struct CarrierRequirement: Codable, Hashable, Sendable {
    enum Field: String, Codable, Sendable {
        case trackingURL = "trackingUrl"
        case dpdPostcode
    }

    let field: Field
    let validator: String?
    let whenTrackingNumber: String?
    let optional: Bool?
    let label: String
    let type: String
    var placeholder: String?
    let help: String?
    var pattern: String?
    var maxLength: Int?
    var inputMode: String?
    let autoComplete: String?

    /// Any country's postcode, as the service checks one from another country than the carrier's own.
    static let anyPostcode = "^(?=.{3,12}$)(?=.*[0-9])[A-Za-z0-9]+(?:[ -][A-Za-z0-9]+)*$"

    func normalizedValue(_ rawValue: String) -> String {
        var value = rawValue.trimmingCharacters(in: .whitespacesAndNewlines)
        if inputMode == "numeric" {
            value = value.filter(\.isNumber)
        } else if validator == "paackPostcode" {
            value = value.uppercased().filter { !$0.isWhitespace }
        } else if takesAnyPostcode {
            // As the country writes it: capitals, one space between groups.
            value = value.uppercased().split(whereSeparator: \.isWhitespace).joined(separator: " ")
        }
        if let maxLength {
            value = String(value.prefix(maxLength))
        }
        return value
    }

    /// The carrier delivers abroad too and takes any country's postcode.
    var takesAnyPostcode: Bool { validator == "internationalPostcode" }

    /// The value a field keeps while it is typed: `normalizedValue`, with the
    /// space a postcode in two groups ("SW1A 1AA") needs before its second group.
    func typedValue(_ rawValue: String) -> String {
        let value = normalizedValue(rawValue)
        guard takesAnyPostcode, rawValue.last?.isWhitespace == true,
              !value.isEmpty, value.count < maxLength ?? .max else { return value }
        return value + " "
    }

    /// Number keys first where the example is all digits; letters stay one tap away.
    func startsWithNumberKeys(example: String?) -> Bool {
        takesAnyPostcode && example?.contains(where: \.isLetter) != true
    }

    /// The parcel can be saved without it; a value that is typed is still checked.
    var isOptional: Bool { optional == true }

    func accepts(_ rawValue: String) -> Bool {
        var value = rawValue.trimmingCharacters(in: .whitespacesAndNewlines)
        if inputMode == "numeric" {
            value = value.filter(\.isNumber)
        }
        guard !value.isEmpty else { return false }
        if let maxLength, value.count > maxLength { return false }
        guard let pattern, !pattern.isEmpty else { return true }
        return value.range(of: pattern, options: .regularExpression) != nil
    }

    func isSatisfied(by rawValue: String) -> Bool {
        if isOptional, rawValue.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty { return true }
        return accepts(rawValue)
    }

    /// The postcode this field suggests for `carrier`, from the postcodes given before in the
    /// order they come: the first given to that carrier that fits, otherwise the first given to
    /// any carrier that fits, since most parcels go to the same address. Returned as the field
    /// takes it.
    func suggestedPostcode(for carrier: CarrierID, among given: [GivenPostcode]) -> String? {
        let fitting = given.compactMap { entry in fitted(entry.postcode).map { (carrier: entry.carrier, postcode: $0) } }
        return (fitting.first { $0.carrier == carrier } ?? fitting.first)?.postcode
    }

    /// A postcode as this field takes it, or nil when it does not fit as written: "1012 AB" is
    /// not "1012" in a four-digit field. Like the web, the pattern is tried on the postcode in
    /// capitals with single spaces.
    private func fitted(_ postcode: String) -> String? {
        let value = postcode.uppercased().split(whereSeparator: \.isWhitespace).joined(separator: " ")
        guard !value.isEmpty else { return nil }
        if let pattern = pattern?.nonEmpty, value.range(of: pattern, options: .regularExpression) == nil { return nil }
        return normalizedValue(value)
    }
}

/// A postcode given to a carrier for an earlier parcel.
struct GivenPostcode: Codable, Hashable, Sendable {
    var carrier: CarrierID
    var postcode: String

    /// What the add sheet suggests from: the postcodes of the account's parcels, newest parcel
    /// first, then the ones this iPhone remembers, in their order.
    static func candidates(parcels: [Parcel], remembered: [GivenPostcode]) -> [GivenPostcode] {
        parcels.sorted { $0.createdAt > $1.createdAt }
            .compactMap { parcel in parcel.dpdPostcode.map { GivenPostcode(carrier: parcel.carrier, postcode: $0) } }
            + remembered
    }
}

struct CarrierDefinition: Codable, Sendable {
    struct Tracking: Codable, Sendable {
        let mode: String
        let adapter: String?
        let requirements: [CarrierRequirement]?
        /// Present when the carrier can recognize a number; no two carriers share a rank.
        var recognitionRank: Int? = nil
        /// The numbers that recognition can answer; the carrier's rules only suggest it for others.
        var recognitionPattern: String? = nil
        /// Recognition asks the carrier only for a country hint among its countries.
        var recognitionServedCountriesOnly: Bool? = nil
        var browserRecognitionRank: Int? = nil
    }

    struct LinkRule: Codable, Sendable {
        let domains: [String]
        let params: [String]?
        /// Captures the number from the whole query, for portals without named parameters.
        var query: String? = nil
        let path: String?
        let pathPattern: String?
        let fragment: String?
        let detectFromNumber: Bool?
        /// Names the carrier only for a number its own rules offer; another number is detected on its own.
        var ownNumbersOnly: Bool? = nil
        let keepsCapabilityURL: Bool?

        enum CodingKeys: String, CodingKey {
            case domains, params, query, path, pathPattern, fragment, detectFromNumber, ownNumbersOnly
            case keepsCapabilityURL = "keepsCapabilityUrl"
        }
    }

    struct DetectionRule: Codable, Sendable {
        let pattern: String
        let rawPattern: String?
        let confidence: String
        let checksum: String?
        /// Low-confidence number evidence: the carrier is listed first among suggestions.
        let preferred: Bool?
    }

    var displayName: String
    let displayNames: [String: String]?
    /// Other names the carrier is known by, searched by the carrier picker.
    var aliases: [String]? = nil
    /// Where it delivers under its own name, home country first.
    var countries: [String]? = nil
    let trackingSiteName: String?
    let color: String
    let selectable: Bool
    let timezone: String
    let tracking: Tracking
    let trackingURLTemplate: String?
    /// The carrier's page for typing a number in, linked when a parcel link hides the number.
    var trackingSearchURL: String? = nil
    let linkRules: [LinkRule]
    let detectionRules: [DetectionRule]

    enum CodingKeys: String, CodingKey {
        case displayName, displayNames, aliases, countries, trackingSiteName, color, selectable, timezone, tracking
        case linkRules, detectionRules
        case trackingURLTemplate = "trackingUrlTemplate"
        case trackingSearchURL = "trackingSearchUrl"
    }
}

struct CarrierMatch: Equatable, Sendable {
    enum Confidence: String, Sendable {
        case high, low, none
    }

    let carrier: CarrierID
    let confidence: Confidence
    let candidates: [CarrierID]
    /// Candidates a `preferred` rule backs with number evidence, listed first.
    var preferred: [CarrierID] = []
}

struct TrackingInputMatch: Equatable, Sendable {
    enum Source: String, Sendable {
        case number, link, text, none
    }

    let trackingNumber: String
    let carrier: CarrierID
    let confidence: CarrierMatch.Confidence
    let candidates: [CarrierID]
    let trackingURL: String?
    let source: Source
}

/// A shop whose order numbers Add tells from tracking numbers, as the front door does (`shared/reading.json`).
enum OrderShop: String, Decodable, Sendable {
    case amazon, shein, shopee, temu
}

/// One tracking number a text names, with what its shape says about the carrier.
struct NumberInText: Equatable, Sendable {
    /// The number without spaces, dots or dashes: what makes two spellings the same number.
    let normalized: String
    let match: TrackingInputMatch
}

/// A postal number whose check digit doesn't add up, which Add asks about as the front door does.
struct CheckDigitTrouble: Equatable, Sendable {
    /// The number with a lookalike letter read as its digit, or back, when that one adds up.
    let suggestion: String?
}

/// Carrier recognition in the Add sheet: a shape several carriers share is
/// checked with them once the number is settled (the field loses focus, a
/// paste, a scan, a shared number), never on each keystroke. It never holds
/// the Add button: tracking services check the number again after saving.
/// Mirrors `carrierCheck` in `src/lib/carrierPicker.ts`.
struct CarrierRecognition: Equatable, Sendable {
    enum Status: Equatable, Sendable {
        /// Nothing to report: the number is not settled, or needs no check.
        case idle
        /// No carrier can be asked about this shape; routing looks it up after saving.
        case unasked
        /// Services are checking; which carriers were asked is known only in their answer.
        case asking([CarrierID])
        /// One carrier knows the number.
        case recognized(CarrierID)
        /// Unrelated carriers all know it: the user chooses.
        case several([CarrierID])
        /// Every carrier answered and none knows it yet, which is normal for a new label.
        /// `services` when tracking services gave this answer rather than the carriers asked.
        case notFound([CarrierID], services: Bool = false)
        /// No carrier could answer, or no tracking service with `services`; the first sync asks again.
        case failed([CarrierID], services: Bool = false)
    }

    /// The normalized number as it stood when it was last settled.
    var settledNumber: String?
    /// The server's answer for a settled number.
    var answer: CarrierDetectionResponse?
    /// A check that failed before a server answer; it has no known carrier attempts.
    var failedNumber: String?

    /// Only a number no direct carrier claims with confidence is worth asking about. The
    /// check keeps running when a carrier is picked by hand meanwhile: its answer
    /// then only says when another carrier has the parcel.
    static func applies(to input: TrackingInputMatch, amazon: Bool, demo: Bool) -> Bool {
        !demo && !amazon && ((input.confidence == .low && input.carrier == .unknown)
            || input.carrier == .internationalPost)
    }

    /// The number to ask about: the settled one, while it is still the number in
    /// the field and has no answer yet.
    func request(for number: String, applies: Bool) -> String? {
        guard applies, !number.isEmpty, settledNumber == number,
              answer?.trackingNumber != number, failedNumber != number else { return nil }
        return number
    }

    /// Carrier attempt lists come from the server's answer.
    func status(for number: String, applies: Bool) -> Status {
        guard applies, !number.isEmpty else { return .idle }
        if failedNumber == number { return .failed([]) }
        guard let answer, answer.trackingNumber == number else {
            guard settledNumber == number else { return .idle }
            return .asking([])
        }
        if answer.carrier != .unknown && answer.carrier != .internationalPost { return .recognized(answer.carrier) }
        if let choices = answer.recognized, choices.count > 1 { return .several(choices) }
        let answered = answer.asked ?? []
        if let providers = answer.providers, !providers.isEmpty {
            return answer.trackingFound == true || providers.contains(where: { $0.outcome == .inputRequired || $0.outcome == .noHistory })
                ? .notFound(answered, services: true) : .failed(answered, services: true)
        }
        if answered.isEmpty { return .unasked }
        if (answer.unanswered?.count ?? 0) >= answered.count { return .failed(answered) }
        return .notFound(answered)
    }
}

/// Whether a carrier picked by hand refuses a number as one it does not issue, so the
/// pickers can say so before tracking starts. A carrier's refusal of a number does not
/// change: each pair is asked once while the app runs. Nothing is refused until the
/// server answers, or when it cannot.
@MainActor
enum NumberRefusals {
    private static var answers: [NumberRefusalRequest: Bool] = [:]

    /// The pair worth asking about: a carrier and a number the server accepts, outside the demo.
    static func question(carrier: CarrierID?, trackingNumber: String, demo: Bool) -> NumberRefusalRequest? {
        guard !demo, let carrier, carrier != .unknown,
              trackingNumber.range(of: "^[A-Z0-9]{4,40}$", options: .regularExpression) != nil else { return nil }
        return NumberRefusalRequest(trackingNumber: trackingNumber, carrier: carrier)
    }

    static func refuses(
        _ question: NumberRefusalRequest,
        ask: (NumberRefusalRequest) async throws -> Bool = { try await DeliveryAPIClient.numberRefusal($0) }
    ) async -> Bool {
        if let known = answers[question] { return known }
        guard let answer = try? await ask(question) else { return false }
        answers[question] = answer
        return answer
    }
}

struct ParcelTrackingLink: Identifiable, Sendable {
    enum Role: Sendable { case active, waiting, history }
    let carrier: CarrierID
    let name: String
    let url: URL
    let role: Role
    var id: String { "\(role):\(url.absoluteString)" }

    /// Each carrier's page under its own tracking number: the one the parcel is followed on under the first number, an
    /// earlier carrier's under its own. `loose` are the pages with no number of their own, such as a carrier not ready yet.
    static func byNumber(_ links: [ParcelTrackingLink], numbers: [(carrier: CarrierID, number: String, tracksAs: Bool)])
        -> (own: [[ParcelTrackingLink]], loose: [ParcelTrackingLink]) {
        var own = numbers.map { _ in [ParcelTrackingLink]() }
        var loose: [ParcelTrackingLink] = []
        for link in links {
            let row = link.role == .active ? numbers.indices.first
                : numbers.indices.dropFirst().first { !numbers[$0].tracksAs && numbers[$0].carrier == link.carrier }
            if let row { own[row].append(link) } else { loose.append(link) }
        }
        return (own, loose)
    }
}

enum CarrierCatalogRefreshResult: Equatable, Sendable {
    case updated
    case notModified
    case skipped
    case failed
}

final class CarrierCatalog: ObservableObject, @unchecked Sendable {
    typealias Loader = @Sendable (URLRequest) async throws -> (Data, URLResponse)

    private struct Contract: Codable {
        var carriers: [String: CarrierDefinition]
        /// Carriers this build cannot read, such as one asking for an input it does not know.
        var unreadable: Set<String> = []
        enum CodingKeys: String, CodingKey { case carriers = "x-carriers" }

        init(from decoder: Decoder) throws {
            let entries = try decoder.container(keyedBy: CodingKeys.self).decode([String: Entry].self, forKey: .carriers)
            carriers = entries.compactMapValues(\.definition)
            unreadable = Set(entries.filter { $0.value.definition == nil }.keys)
        }

        /// One carrier, which a newer catalog may write in a way this build cannot read.
        private struct Entry: Decodable {
            let definition: CarrierDefinition?
            init(from decoder: Decoder) throws { definition = try? CarrierDefinition(from: decoder) }
        }
    }

    private struct CacheRecord: Codable {
        let etag: String?
        let contract: Contract
    }

    private enum CatalogError: Error {
        case invalidContract
    }

    static let shared = CarrierCatalog()
    @Published private(set) var definitions: [CarrierID: CarrierDefinition] {
        didSet {
            detections.removeAllObjects()
            parsedInputs.removeAllObjects()
            readings.removeAllObjects()
            orders.removeAllObjects()
            numbersRead.removeAllObjects()
        }
    }

    /// Views ask for the same input's result many times per render and keystroke.
    private final class Memo<Value>: @unchecked Sendable {
        let value: Value
        init(_ value: Value) { self.value = value }
    }
    private let detections = NSCache<NSString, Memo<CarrierMatch>>()
    private let parsedInputs = NSCache<NSString, Memo<TrackingInputMatch>>()
    private let readings = NSCache<NSString, Memo<TrackingInputMatch>>()
    private let orders = NSCache<NSString, Memo<OrderShop?>>()
    private let numbersRead = NSCache<NSString, Memo<[NumberInText]>>()
    private static let expressions = NSCache<NSString, NSRegularExpression>()

    private static let refreshInterval: TimeInterval = 15 * 60
    /// Far above the catalog, which grows with each scraper release: a larger answer is
    /// refused and the app keeps the catalog it has.
    static let maximumResponseBytes = 4 * 1_024 * 1_024
    private static let defaultLoader: Loader = { request in
        try await URLSession.shared.data(for: request)
    }

    private let cacheURL: URL?
    private let loader: Loader
    private var cachedETag: String?
    private var lastRefreshAttempt: Date?

    init(
        bundle: Bundle = .main,
        cacheURL: URL? = CarrierCatalog.defaultCacheURL(),
        loader: @escaping Loader = CarrierCatalog.defaultLoader
    ) {
        self.cacheURL = cacheURL
        self.loader = loader

        if let cacheURL,
           let cached = Self.cachedContract(at: cacheURL) {
            definitions = cached.definitions
            cachedETag = cached.etag
        } else {
            definitions = Self.bundledDefinitions(in: bundle)
            cachedETag = nil
        }
    }

    init(
        data: Data,
        cacheURL: URL? = nil,
        loader: @escaping Loader = CarrierCatalog.defaultLoader
    ) throws {
        let contract = try JSONDecoder().decode(Contract.self, from: data)
        definitions = try Self.validatedDefinitions(contract)
        self.cacheURL = cacheURL
        self.loader = loader
        cachedETag = nil
    }

    @MainActor
    func refresh(
        from apiBaseURL: URL,
        force: Bool = false,
        now: Date = Date()
    ) async -> CarrierCatalogRefreshResult {
        if !force,
           let lastRefreshAttempt,
           now.timeIntervalSince(lastRefreshAttempt) < Self.refreshInterval {
            return .skipped
        }
        lastRefreshAttempt = now

        var request = URLRequest(url: apiBaseURL.appending(path: "api/carriers"))
        request.cachePolicy = .reloadIgnoringLocalCacheData
        request.timeoutInterval = 15
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        if let cachedETag {
            request.setValue(cachedETag, forHTTPHeaderField: "If-None-Match")
        }

        do {
            let (data, response) = try await loader(request)
            guard let response = response as? HTTPURLResponse else { return .failed }
            if response.statusCode == 304 { return .notModified }
            guard response.statusCode == 200,
                  data.count <= Self.maximumResponseBytes else { return .failed }

            var contract = try JSONDecoder().decode(Contract.self, from: data)
            // A carrier this build cannot read keeps the definition it has; the others refresh.
            for key in contract.unreadable {
                contract.carriers[key] = definitions[CarrierID(rawValue: key)]
            }
            let refreshed = try Self.validatedDefinitions(contract)
            let etag = response.value(forHTTPHeaderField: "ETag")
            definitions = refreshed
            cachedETag = etag
            saveCache(contract: contract, etag: etag)
            return .updated
        } catch {
            return .failed
        }
    }

    func info(for carrier: CarrierID, language: AppLanguage? = nil) -> CarrierDefinition {
        var definition = definitions[carrier] ?? definitions[.unknown] ?? Self.fallbackDefinitions[.unknown]!
        if let language, let name = definition.displayNames?[language.rawValue] {
            definition.displayName = name
        }
        return definition
    }

    var selectableCarriers: [CarrierID] {
        definitions.compactMap { $0.value.selectable ? $0.key : nil }
            .sorted { info(for: $0).displayName.localizedCaseInsensitiveCompare(
                info(for: $1).displayName
            ) == .orderedAscending }
    }

    func tracksAutomatically(_ carrier: CarrierID) -> Bool {
        info(for: carrier).tracking.mode == "automatic"
    }

    /// Carriers the detect route asks at once.
    static let maximumRecognitions = 5
    /// Brands with several catalog networks ("DPD" is `dpd` and `dpd-fr`), as in
    /// Universal Parcel Scraper's catalog networks.
    private static let networkBrands = ["dhl", "dpd", "gls", "hermes", "evri", "gofo"]

    static func networkBrand(_ carrier: CarrierID) -> String? {
        networkBrands.first { carrier.rawValue == $0 || carrier.rawValue.hasPrefix("\($0)-") }
    }

    /// The carriers the detect route asks about an ambiguous number, best first:
    /// number evidence, then a passing check digit, then the catalog's recognition
    /// rank. A carrier whose recognition names the numbers it answers is asked only
    /// about those, and one asked only for its own countries is not asked: this build
    /// gives no country hint. Mirrors `recognitionAskedCarriers`; the detection golden
    /// file keeps them in step.
    func recognitionCandidates(for raw: String, browser: Bool = false) -> [CarrierID] {
        let match = detect(raw)
        let unknownPostalCarrier = match.carrier == .internationalPost
        guard match.confidence == .low || unknownPostalCarrier else { return [] }
        var candidates = match.candidates
        var preferred = match.preferred
        let number = Self.normalize(raw)
        let printed = raw.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()
        if unknownPostalCarrier {
            // The generic postal match already checked S10. Recover low rules
            // it hid; each carrier still has to confirm the whole identity. A rule
            // gated by another check, known to this build or not, stays out, as in
            // the shared engine: S10 does not vouch for it.
            let postalMatches = definitions.compactMap { carrier, definition -> (CarrierID, Bool)? in
                guard let rule = definition.detectionRules.first(where: { rule in
                    rule.confidence == "low" && (rule.checksum == nil || rule.checksum == "s10")
                        && Self.matches(number, pattern: rule.pattern)
                        && (rule.rawPattern.map { Self.matches(printed, pattern: $0) } ?? true)
                }) else { return nil }
                return (carrier, rule.preferred == true)
            }
            candidates = postalMatches.map { $0.0 }
            preferred = postalMatches.filter { $0.1 }.map { $0.0 }
        }
        func rank(_ carrier: CarrierID) -> Int? {
            browser ? definitions[carrier]?.tracking.browserRecognitionRank : definitions[carrier]?.tracking.recognitionRank
        }
        // A preferred carrier that cannot be asked (DPD France) keeps its brand's
        // other networks out: DPD's guest API also answers for DPD France parcels.
        let shadowed = Set(preferred.filter { rank($0) == nil }.compactMap(Self.networkBrand))
        let eligible = candidates.filter { carrier in
            guard let definition = definitions[carrier], rank(carrier) != nil,
                  definition.tracking.mode == "automatic", definition.tracking.adapter != "universal" else { return false }
            if !browser, let pattern = definition.tracking.recognitionPattern, !Self.matches(number, pattern: pattern) { return false }
            if !browser, definition.tracking.recognitionServedCountriesOnly == true { return false }
            if browser && requirements(for: carrier, trackingNumber: raw).contains(where: { $0.optional != true }) { return false }
            return Self.networkBrand(carrier).map { !shadowed.contains($0) } ?? true
        }
        let checked = Set(eligible.filter { passesCheckDigit($0, number: number, printed: printed) })
        // Ranks are unique, so the order does not depend on the catalog's key order.
        let ordered = eligible.sorted { left, right in
            let leftPreferred = preferred.contains(left)
            if leftPreferred != preferred.contains(right) { return leftPreferred }
            let leftChecked = checked.contains(left)
            if leftChecked != checked.contains(right) { return leftChecked }
            return (rank(left) ?? 0) > (rank(right) ?? 0)
        }
        return Array(ordered.prefix(browser ? 2 : Self.maximumRecognitions))
    }

    /// Whether the rule that offered a carrier for this number, its first rule that fits as in
    /// `detect`, passed a check digit. A check this build cannot run passes nothing.
    private func passesCheckDigit(_ carrier: CarrierID, number: String, printed: String) -> Bool {
        let rule = definitions[carrier]?.detectionRules.first { rule in
            Self.matches(number, pattern: rule.pattern)
                && (rule.rawPattern.map { Self.matches(printed, pattern: $0) } ?? true)
                && (rule.checksum.map { Self.checksumPasses($0, number) != false } ?? true)
        }
        return rule?.checksum.flatMap { Self.checksumPasses($0, number) } == true
    }

    func discoveryCandidates(for raw: String) -> [CarrierID] {
        let http = recognitionCandidates(for: raw)
        return http + recognitionCandidates(for: raw, browser: true).filter { !http.contains($0) }
    }

    func isAmazonTrackingNumber(_ raw: String) -> Bool {
        guard let pattern = definitions[.amazonLogistics]?.detectionRules.first?.pattern else { return false }
        return Self.matches(Self.normalize(raw), pattern: pattern)
    }

    func requiresAmazonAccount(_ carrier: CarrierID, trackingNumber: String = "") -> Bool {
        carrier != .amazonShipping && (carrier == .amazonLogistics || isAmazonTrackingNumber(trackingNumber))
    }

    // Amazon sends visitors from a European country without a store of its own to amazon.de
    // (Iceland's to amazon.co.uk), so its parcels' orders are there.
    private static let amazonStores = ["FR": "fr", "DE": "de", "AT": "de", "BE": "com.be", "UK": "co.uk", "GB": "co.uk",
        "IT": "it", "ES": "es", "PT": "es", "NL": "nl", "IE": "ie", "PL": "pl", "SE": "se", "TR": "com.tr",
        "CH": "de", "LI": "de", "LU": "de", "DK": "de", "NO": "de", "FI": "de", "CZ": "de", "HU": "de", "RO": "de",
        "HR": "de", "SI": "de", "EE": "de", "LV": "de", "LT": "de", "GR": "de", "IS": "co.uk"]

    /// The store whose order history holds the parcel. TBA names no country and amazon.ca orders
    /// ship under it too, so the delivery country decides there, and when there is no number.
    static func amazonMarketplace(_ number: String, country: String? = nil) -> String {
        let normalized = normalize(number)
        let destination = (country ?? "").trimmingCharacters(in: .whitespaces).uppercased()
        if normalized.hasPrefix("TBC") { return "ca" }
        if normalized.hasPrefix("TBM") { return "com.mx" }
        if normalized.hasPrefix("TBA") { return destination == "CA" ? "ca" : "com" }
        if normalized.isEmpty {
            return ["CA": "ca", "MX": "com.mx"][destination] ?? amazonStores[destination] ?? "com"
        }
        return amazonStores[String(normalized.prefix(2))] ?? "com"
    }

    static func amazonOrdersURL(_ number: String, country: String? = nil) -> URL {
        URL(string: "https://www.amazon.\(amazonMarketplace(number, country: country))/gp/your-account/order-history")!
    }

    static func amazonShippingURL(_ number: String) -> URL {
        let normalized = normalize(number)
        let domains = ["IT": "it", "ES": "es", "UK": "co.uk", "GB": "co.uk", "TB": "com"]
        let domain = domains[String(normalized.prefix(2))] ?? "fr"
        return URL(string: "https://track.amazon.\(domain)/tracking/\(urlEncode(normalized))")!
    }

    func trackingHintKey(for carrier: CarrierID) -> String {
        if requiresAmazonAccount(carrier) { return "add.amazonAccount" }
        return tracksAutomatically(carrier) ? "add.autoSync" : "add.linkSync"
    }

    /// One postcode per country, shown as the example in a field that takes any country's.
    static let postcodeExamples: [String: String] = {
        guard let url = Bundle.main.url(forResource: "PostcodeExamples", withExtension: "json"),
              let data = try? Data(contentsOf: url),
              let examples = try? JSONDecoder().decode([String: String].self, from: data) else { return [:] }
        return examples
    }()

    /// The example in a carrier's field. A national carrier states its own
    /// postcode, and a field for another country's postcode that country's, or
    /// none (an empty placeholder). One that takes any country's leaves it out
    /// and shows the region given, or the carrier's own country when that has
    /// no example.
    func postcodeExample(
        for carrier: CarrierID,
        requirement: CarrierRequirement,
        region: String? = Locale.current.region?.identifier
    ) -> String? {
        if let placeholder = requirement.placeholder { return placeholder.nonEmpty }
        guard requirement.field == .dpdPostcode else { return nil }
        return ([region].compactMap { $0?.uppercased() } + (info(for: carrier).countries ?? []))
            .lazy.compactMap { Self.postcodeExamples[$0] }.first
    }

    /// Every country a delivery postcode can be from: ISO 3166-1's, and Kosovo, as the service takes
    /// them. The system's regions also name unions, groupings and reserved codes, which are left out.
    static let countries: [String] = {
        let others: Set<String> = ["AC", "CP", "CQ", "DG", "EA", "EU", "EZ", "IC", "QO", "TA", "UN", "XA", "XB", "ZZ"]
        return Locale.Region.isoRegions.map(\.identifier).filter { code in
            code.count == 2 && code.allSatisfy { $0.isASCII && $0.isUppercase } && !others.contains(code)
        }.sorted()
    }()

    private static let countryOrders = NSCache<NSString, Memo<[String]>>()

    /// Every country, by its name in the language. A menu of them is drawn again with each keystroke beside it.
    static func countriesByName(in language: AppLanguage) -> [String] {
        let key = language.rawValue as NSString
        if let memo = countryOrders.object(forKey: key) { return memo.value }
        let names = Dictionary(uniqueKeysWithValues: countries.map { ($0, TrackingLocation.countryName($0, language: language)) })
        let ordered = countries.sorted {
            names[$0, default: $0].compare(names[$1, default: $1], locale: language.locale) == .orderedAscending
        }
        countryOrders.setObject(Memo(ordered), forKey: key)
        return ordered
    }

    /// The country a postcode given for the carrier is from unless another is named: its first.
    func homeCountry(of carrier: CarrierID) -> String? {
        info(for: carrier).countries?.first
    }

    /// The countries a postcode for this field can be from: any when the carrier takes any country's,
    /// otherwise the carrier's own, whose shapes its check reads.
    func postcodeCountries(for carrier: CarrierID, requirement: CarrierRequirement) -> [String] {
        requirement.takesAnyPostcode ? Self.countries : info(for: carrier).countries ?? []
    }

    /// The same countries as a menu lists them: the carrier's own, home first, then the others by name.
    func postcodeCountryChoices(
        for carrier: CarrierID,
        requirement: CarrierRequirement,
        language: AppLanguage
    ) -> (own: [String], others: [String]) {
        let own = info(for: carrier).countries ?? []
        guard requirement.takesAnyPostcode else { return (own, []) }
        return (own, Self.countriesByName(in: language).filter { !own.contains($0) })
    }

    /// The country a postcode field starts on. The parcel's own postcode for the carrier keeps the
    /// country stored with it, or the carrier's home when none was; otherwise the first the field
    /// takes of the parcel's destination and the device's region, else the carrier's home.
    func initialPostcodeCountry(
        for carrier: CarrierID,
        requirement: CarrierRequirement,
        parcel: Parcel? = nil,
        region: String? = Locale.current.region?.identifier
    ) -> String? {
        let home = homeCountry(of: carrier)
        let known: [String?]
        if let parcel, parcel.carrier == carrier, let postcode = parcel.dpdPostcode?.nonEmpty {
            let stored = parcel.carrierData?.postcodeCountry
            known = [stored?.postcode == postcode ? stored?.country : home]
        } else {
            known = [parcel?.carrierData?.destinationCountry, region]
        }
        let countries = postcodeCountries(for: carrier, requirement: requirement)
        return known.lazy.compactMap { $0?.uppercased() }.first(where: countries.contains) ?? home
    }

    /// The field for a postcode from `country`: the carrier's own for its home country, or when its
    /// check reads only its own countries' shapes; otherwise any country's shape, with that country's
    /// example or none, never the carrier's.
    func postcodeRequirement(_ requirement: CarrierRequirement, for carrier: CarrierID, country: String?) -> CarrierRequirement {
        guard requirement.field == .dpdPostcode, requirement.takesAnyPostcode,
              let country, country != homeCountry(of: carrier) else { return requirement }
        var field = requirement
        field.pattern = CarrierRequirement.anyPostcode
        field.maxLength = 12
        field.inputMode = "text"
        field.placeholder = Self.postcodeExamples[country] ?? ""
        return field
    }

    /// The country sent with a postcode: none without one, nor for the carrier's home country, so a
    /// postcode from there goes as it always has.
    func sentPostcodeCountry(_ country: String?, postcode: String?, for carrier: CarrierID) -> String? {
        guard postcode?.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty != nil,
              country != homeCountry(of: carrier) else { return nil }
        return country
    }

    func requirements(for carrier: CarrierID, trackingNumber: String) -> [CarrierRequirement] {
        let normalized = Self.normalize(trackingNumber)
        return (info(for: carrier).tracking.requirements ?? []).filter { requirement in
            guard let pattern = requirement.whenTrackingNumber else { return true }
            return Self.matches(normalized, pattern: pattern)
        }
    }

    func detect(_ raw: String) -> CarrierMatch {
        let number = Self.normalize(raw)
        guard !number.isEmpty else {
            return CarrierMatch(carrier: .unknown, confidence: .none, candidates: [])
        }
        let printed = raw.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()
        if let memo = detections.object(forKey: printed as NSString) { return memo.value }
        let match = detectNormalized(number, printed: printed)
        detections.setObject(Memo(match), forKey: printed as NSString)
        return match
    }

    /// The one carrier a number's shape and check digit name, when it is not the parcel's own and the reader can
    /// choose it; nil when no single carrier claims the number.
    func claimingCarrier(for trackingNumber: String, besides carrier: CarrierID) -> CarrierID? {
        let match = detect(trackingNumber)
        guard match.confidence == .high, match.carrier != carrier, match.carrier != .unknown,
              match.carrier != .internationalPost, info(for: match.carrier).selectable else { return nil }
        return match.carrier
    }

    private func detectNormalized(_ number: String, printed: String) -> CarrierMatch {
        var matches: [(carrier: CarrierID, confidence: CarrierMatch.Confidence, preferred: Bool)] = []
        for (carrier, definition) in definitions {
            for rule in definition.detectionRules {
                guard Self.matches(number, pattern: rule.pattern) else { continue }
                if let rawPattern = rule.rawPattern, !Self.matches(printed, pattern: rawPattern) { continue }
                var confidence: CarrierMatch.Confidence = rule.confidence == "high" ? .high : .low
                var preferred = rule.preferred == true
                if let checksum = rule.checksum {
                    switch Self.checksumPasses(checksum, number) {
                    case true?: break
                    case false?: continue
                    case nil:
                        // A check this build cannot run proves nothing about a mistyped number:
                        // the shape still suggests the carrier, but never selects it or lists it first.
                        confidence = .low
                        preferred = false
                    }
                }
                matches.append((carrier, confidence, preferred))
                break
            }
        }
        let high = matches.filter { $0.confidence == .high }
        let ranked = high.isEmpty ? matches : high
        // Number evidence first; the dictionary has no order, so sort each group.
        let preferred = ranked.filter(\.preferred).map(\.carrier).sorted { $0.rawValue < $1.rawValue }
        let candidates = preferred + ranked.filter { !$0.preferred }.map(\.carrier).sorted { $0.rawValue < $1.rawValue }
        if high.count == 1 {
            return CarrierMatch(carrier: high[0].carrier, confidence: .high, candidates: candidates, preferred: preferred)
        }
        return CarrierMatch(
            carrier: .unknown,
            confidence: matches.isEmpty ? .none : .low,
            candidates: candidates,
            preferred: preferred
        )
    }

    func parse(_ raw: String) -> TrackingInputMatch {
        let input = raw.trimmingCharacters(in: .whitespacesAndNewlines)
        guard !input.isEmpty else { return Self.emptyMatch }
        if let memo = parsedInputs.object(forKey: input as NSString) { return memo.value }
        let match = parseTrimmed(input)
        parsedInputs.setObject(Memo(match), forKey: input as NSString)
        return match
    }

    /// The number Add reads out of a text, as the front door reads it (`readText` in src/peek/lookup/reading.ts):
    /// the parser's, or else the one number a message introduces though its shape proves no carrier.
    func read(_ raw: String) -> TrackingInputMatch {
        let parsed = parse(raw)
        guard parsed.trackingNumber.isEmpty else { return parsed }
        if let memo = readings.object(forKey: raw as NSString) { return memo.value }
        let match = uncertainNumber(in: raw) ?? parsed
        readings.setObject(Memo(match), forKey: raw as NSString)
        return match
    }

    /// The shop whose order number the input is, read as the front door reads it: a number a carrier
    /// claims with certainty wins; otherwise the number read, or the whole text, is tried. A message
    /// with a number of its own beside the order number is that number's.
    func order(in raw: String) -> OrderShop? {
        if let memo = orders.object(forKey: raw as NSString) { return memo.value }
        let match = read(raw)
        let order = match.confidence == .high ? nil : Self.orderShop(in: match.trackingNumber.nonEmpty ?? raw)
        orders.setObject(Memo(order), forKey: raw as NSString)
        return order
    }

    /// Every number a text names with certainty, in the order written, when it names two or more: Add lists them
    /// to choose from, as the front door does (`numbersInText` in src/peek/lookup/reading.ts). Links count through
    /// their own number; each line is read on its own, so numbers on neighbouring lines never run together.
    func numbers(in raw: String) -> [NumberInText] {
        if let memo = numbersRead.object(forKey: raw as NSString) { return memo.value }
        var found: [NumberInText] = []
        func keep(_ match: TrackingInputMatch) {
            let normalized = Self.normalize(match.trackingNumber)
            guard !normalized.isEmpty, !found.contains(where: { $0.normalized == normalized }) else { return }
            found.append(NumberInText(normalized: normalized, match: match))
        }
        for line in raw.components(separatedBy: "\n") {
            for link in Self.matches(in: line, pattern: Self.linkPattern, caseInsensitive: true) {
                let match = parse(link)
                if !match.trackingNumber.isEmpty { keep(match) }
            }
            let prose = Self.blanking(Self.linkPattern, in: line, caseInsensitive: true)
            var candidates: [(range: NSRange, number: String)] = []
            for shape in Self.candidatePatterns {
                for hit in Self.hits(in: prose, pattern: shape.pattern, caseInsensitive: shape.caseInsensitive) {
                    let number = hit.text.trimmingCharacters(in: .whitespacesAndNewlines)
                    guard detect(number).confidence == .high else { continue }
                    // A shape inside another certain one is the same number read twice.
                    let overlaps = candidates.contains {
                        hit.range.location < NSMaxRange($0.range) && NSMaxRange(hit.range) > $0.range.location
                    }
                    if !overlaps { candidates.append((hit.range, number)) }
                }
            }
            for candidate in candidates.sorted(by: { $0.range.location < $1.range.location }) {
                keep(makeMatch(candidate.number, source: .text))
            }
        }
        let numbers = found.count > 1 ? found : []
        numbersRead.setObject(Memo(numbers), forKey: raw as NSString)
        return numbers
    }

    private func parseTrimmed(_ input: String) -> TrackingInputMatch {
        for pasted in Self.matches(in: input, pattern: Self.linkPattern, caseInsensitive: true) {
            let cleaned = pasted
                .trimmingCharacters(
                    in: CharacterSet.whitespacesAndNewlines
                        .union(CharacterSet(charactersIn: "<>'\"()[],;.!?"))
                )
            guard let components = URLComponents(string: cleaned),
                  let host = components.host?.lowercased() else { continue }
            var matchingRules: [(carrier: CarrierID, rule: CarrierDefinition.LinkRule)] = []
            for (carrier, definition) in definitions {
                for rule in definition.linkRules where rule.domains.contains(where: {
                    host == $0 || host.hasSuffix(".\($0)")
                }) {
                    if let pattern = rule.pathPattern,
                       Self.matches(in: components.path, pattern: pattern, caseInsensitive: true).isEmpty { continue }
                    matchingRules.append((carrier, rule))
                }
            }
            func specificity(_ rule: CarrierDefinition.LinkRule) -> Int {
                rule.domains.filter { host == $0 || host.hasSuffix("." + $0) }.map(\.count).max() ?? 0
            }
            matchingRules.sort {
                let left = specificity($0.rule), right = specificity($1.rule)
                return left == right ? $0.carrier.rawValue < $1.carrier.rawValue : left > right
            }
            for firstMatch in matchingRules {
                let queryValue = components.queryItems?.first { item in
                    firstMatch.rule.params?.contains {
                        $0.caseInsensitiveCompare(item.name) == .orderedSame
                    } == true
                }?.value
                let bareQueryValue = firstMatch.rule.query.flatMap {
                    Self.capture(components.query ?? "", pattern: $0, caseInsensitive: true)
                }
                var pathValue: String?
                if let path = firstMatch.rule.path {
                    pathValue = Self.capture(components.path, pattern: path, caseInsensitive: true)
                }
                let fragmentValue = firstMatch.rule.fragment.flatMap {
                    Self.capture(components.fragment ?? "", pattern: $0, caseInsensitive: true)
                }
                let candidate = (queryValue ?? bareQueryValue ?? pathValue ?? fragmentValue ?? "")
                    .split(whereSeparator: { $0 == "," || $0 == "|" }).first.map(String.init) ?? ""
                if valid(candidate) {
                    let detected = detect(candidate)
                    let suggested = matchingRules.filter { detected.candidates.contains($0.carrier) }
                    let uniqueSuggestions = Set(suggested.map { $0.carrier })
                    if firstMatch.rule.detectFromNumber == true,
                       detected.confidence == .high || (detected.confidence == .low && uniqueSuggestions.count > 1) {
                        return makeMatch(candidate, source: .link)
                    }
                    let selected = detected.confidence == .high
                        ? matchingRules.first(where: { $0.carrier == detected.carrier }) ?? firstMatch
                        : (uniqueSuggestions.count == 1 ? suggested[0] : firstMatch)
                    if selected.rule.ownNumbersOnly == true, !detected.candidates.contains(selected.carrier) {
                        return makeMatch(candidate, source: .link)
                    }
                    // A host that is also the number's carrier's own page, as Royal Mail's is Parcelforce's, names that carrier.
                    let carrier = detected.confidence == .high && coversTrackingPage(selected.rule, of: detected.carrier)
                        ? detected.carrier : selected.carrier
                    return TrackingInputMatch(
                        trackingNumber: candidate,
                        carrier: carrier,
                        confidence: .high,
                        candidates: [carrier],
                        trackingURL: selected.rule.keepsCapabilityURL == true ? cleaned : nil,
                        source: .link
                    )
                }
            }
            let decoded = cleaned.removingPercentEncoding ?? cleaned
            if let recognized = recognizedNumber(in: decoded) {
                return makeMatch(recognized, source: .link)
            }
        }

        // No part of a shop's order number is read as a tracking number.
        let prose = Self.withoutOrders(input)
        if let recognized = recognizedNumber(in: prose) {
            return makeMatch(recognized, source: input == recognized ? .number : .text)
        }

        if let labelled = Self.capture(prose, pattern: Self.labelledNumberPattern, caseInsensitive: true) {
            // A sentence can end right after its number.
            let keyword = labelled.replacingOccurrences(of: "[.-]+$", with: "", options: .regularExpression)
            if Self.validInText(keyword) { return makeMatch(keyword, source: .text) }
        }
        if let whole = numberEnteredWhole(input) { return makeMatch(whole.number, source: whole.labelled ? .text : .number) }
        return Self.emptyMatch
    }

    /// Words that offer a second number: "1234567899 or 9987654321" holds two.
    private static let alternativeWords: Set<String> = ["or", "oder", "ou", "o", "of", "lub"]

    /// A word naming the code after it, alone or after another word ("Pickup code"), and the rest of
    /// the entry. German and Dutch join theirs into one word: Abholcode, Sendungsnummer. French,
    /// Spanish, Italian and Portuguese name what the number tracks after a preposition ("N° de suivi",
    /// "número de seguimiento", "numero di tracciamento"), Polish after the word ("numer przesyłki"),
    /// and the tracking word can stand alone ("Suivi"), as can a courier's word for its waybill ("AWB",
    /// "Guía"). An abbreviated "No." can follow the label ("AWB No."), and "N°" can touch its digits.
    private static let entryLabelPattern: String = {
        let tracked = "suivi|seguimiento|rastreo|tracciamento|rastreio|rastreamento|tracking|waybill|awb|guía|guia"
        return "^(?:\\p{L}+\\s+)?(?:(?:\\p{L}*(?:code|nummer)|number|numer|numero|número|numéro|nr|n\\.?[°º]|kod|codice|codigo|código)"
            + "(?:\\s+(?:(?:de|du|del|di|do|da)\\s+(?:\(tracked)|colis|envío|envio|spedizione)|przesyłki|paczki))?|\(tracked))"
            + "(?:\\s+(?:no|nr)\\.)?(?:[.:#\\s]+|(?<=[°º]))(?=[0-9])([\\s\\S]*)"
    }()

    /// The number a short entry holds whole, groups a label prints apart included, as in
    /// "1Z 999 AA1 01 2345 6784", as the shared engine reads it (`numberEnteredWhole`). A word offering
    /// a second number leaves it unread, and a label word before the digits is no part of it, unless a
    /// carrier confidently claims the two together.
    private func numberEnteredWhole(_ input: String) -> (number: String, labelled: Bool)? {
        guard !input.contains("://") else { return nil }
        var alternatives = [""]
        for word in input.split(whereSeparator: \.isWhitespace) {
            if Self.alternativeWords.contains(word.lowercased()) {
                alternatives.append("")
            } else {
                alternatives[alternatives.count - 1] += word
            }
        }
        guard alternatives.filter({ Self.matches($0, pattern: "[0-9]") }).count <= 1 else { return nil }
        if let number = Self.capture(input, pattern: Self.entryLabelPattern, caseInsensitive: true),
           detect(input).confidence != .high, valid(number) {
            return (number, true)
        }
        return valid(input) ? (input, false) : nil
    }

    /// A number in prose whose shape fits a carrier without proving one: "Track it with DHL: 1234567899".
    /// The parser leaves these alone, since a phone number can look the same; Add takes one when the
    /// text holds no other candidate, or when only one stands on a line that speaks of tracking, and the
    /// carriers are then asked. A candidate holds a digit: some shapes also fit a word, and a word in a
    /// message is not a number.
    private func uncertainNumber(in text: String) -> TrackingInputMatch? {
        var candidates: [String: (number: String, introduced: Bool)] = [:]
        for line in text.components(separatedBy: "\n") {
            let prose = Self.withoutOrders(Self.blanking(Self.linkPattern, in: line, caseInsensitive: true))
            let introduced = Self.numberWords.map { Self.matches(prose, pattern: $0, caseInsensitive: true) } ?? false
            for shape in Self.candidatePatterns {
                for hit in Self.matches(in: prose, pattern: shape.pattern, caseInsensitive: shape.caseInsensitive) {
                    let number = hit.trimmingCharacters(in: .whitespacesAndNewlines)
                    guard Self.matches(number, pattern: "[0-9]"), detect(number).confidence == .low else { continue }
                    let normalized = Self.normalize(number)
                    candidates[normalized] = (number, introduced || candidates[normalized]?.introduced == true)
                }
            }
        }
        let introduced = candidates.values.filter { $0.introduced }
        guard let picked = candidates.count == 1 ? candidates.values.first : introduced.count == 1 ? introduced.first : nil
        else { return nil }
        return makeMatch(picked.number, source: .text)
    }

    /// The followed carrier's pages first, and then the named delivering carrier's, for its own number.
    func trackingLinks(for parcel: Parcel, language: AppLanguage) -> [ParcelTrackingLink] {
        let links = followedTrackingLinks(for: parcel, language: language)
        guard let named = parcel.namedDelivery, let number = named.number else { return links }
        var delivery = parcel
        delivery.carrier = named.carrier
        delivery.trackingNumber = number
        delivery.trackingURL = nil
        delivery.carrierData = nil
        // The parcel is not followed there yet.
        return links + followedTrackingLinks(for: delivery, language: language).filter { $0.carrier == named.carrier }.map {
            ParcelTrackingLink(carrier: $0.carrier, name: $0.name, url: $0.url, role: .waiting)
        }
    }

    /// Where a link that hides the number leads: the carrier's page for typing one in, or nowhere.
    func searchLink(for parcel: Parcel, language: AppLanguage) -> ParcelTrackingLink? {
        let carrier = parcel.activeTrackingCarrier
        let definition = info(for: carrier, language: language)
        guard let raw = definition.trackingSearchURL,
              let url = localizedURL(raw, carrier: carrier, language: language) else { return nil }
        return ParcelTrackingLink(carrier: carrier, name: definition.trackingSiteName ?? definition.displayName, url: url, role: .active)
    }

    private func followedTrackingLinks(for parcel: Parcel, language: AppLanguage) -> [ParcelTrackingLink] {
        if requiresAmazonAccount(parcel.carrier, trackingNumber: parcel.trackingNumber) {
            return [ParcelTrackingLink(carrier: .amazonLogistics,
                name: info(for: .amazonLogistics, language: language).displayName,
                url: Self.amazonOrdersURL(parcel.trackingNumber, country: parcel.carrierData?.destinationCountry),
                role: .active)]
        }
        let links = carrierTrackingLinks(for: parcel, language: language)
        let lookupNumber = parcel.carrierData?.originalCarrier != nil && parcel.carrierData?.activeTrackingCarrier != nil
            ? parcel.carrierData?.activeTrackingNumber?.nonEmpty ?? parcel.trackingNumber : parcel.trackingNumber
        let number = Self.urlEncode(lookupNumber)
        // A carrier that answered the same check keeps the link, even when its clock sent the result elsewhere.
        guard let provider = parcel.carrierData?.trackingProvider, parcel.carrierData?.carrierAnswered != true else { return links }
        let raw: String
        switch provider {
        case "17TRACK": raw = "https://t.17track.net/en#nums=\(number)"
        case "ParcelsApp": raw = "https://parcelsapp.com/en/tracking/\(number)"
        case "Ship24": raw = "https://www.ship24.com/tracking?p=\(number)"
        case "UPU": raw = "https://globaltracktrace.ptc.post/gtt.web/Search.aspx"
        // Public entry point, without guessing a private/session-specific URL.
        case "Postal Ninja": raw = "https://postal.ninja/en/track"
        default: return links
        }
        guard let url = localizedURL(raw, carrier: .unknown, language: language) else { return links }
        let primary = ParcelTrackingLink(carrier: .unknown, name: provider, url: url, role: .active)
        return [primary] + links.filter { $0.role != .active && $0.url != url }
    }

    private func carrierTrackingLinks(for parcel: Parcel, language: AppLanguage) -> [ParcelTrackingLink] {
        if let originalCarrier = parcel.carrierData?.originalCarrier,
           let originalNumber = parcel.carrierData?.originalTrackingNumber?.nonEmpty {
            var delivery = parcel
            delivery.carrier = parcel.activeTrackingCarrier
            delivery.trackingNumber = parcel.carrierData?.activeTrackingNumber?.nonEmpty ?? parcel.trackingNumber
            delivery.carrierData = nil
            if delivery.carrier != parcel.carrier || delivery.trackingNumber != parcel.trackingNumber { delivery.trackingURL = nil }
            var original = delivery
            original.carrier = originalCarrier
            original.trackingNumber = originalNumber
            original.trackingURL = parcel.carrierData?.originalTrackingURL
            let history = trackingLinks(for: original, language: language).map {
                ParcelTrackingLink(carrier: $0.carrier, name: $0.name, url: $0.url, role: .history)
            }
            return trackingLinks(for: delivery, language: language) + history
        }
        if !Self.supportsSwissPostHandoff(parcel.trackingNumber) {
            let carrier = parcel.activeTrackingCarrier
            let number = parcel.carrierData?.activeTrackingNumber?.nonEmpty ?? parcel.trackingNumber
            let definition = info(for: carrier, language: language)
            // Replace obsolete generated links saved by earlier app versions.
            let savedPostNLURL = parcel.trackingURL.flatMap { URL(string: $0) }
            let obsoletePostNLLink = parcel.carrier == .springGDS
                && savedPostNLURL?.host?.lowercased() == "postnl.post"
                && savedPostNLURL?.path.hasPrefix("/details/") == true
            let savedURL = requiresAmazonAccount(carrier, trackingNumber: number) || carrier == .internationalPost || obsoletePostNLLink || carrier != parcel.carrier
                || number != parcel.trackingNumber ? nil : parcel.trackingURL
            guard let raw = savedURL
                    ?? Self.renderTrackingURL(
                        definition.trackingURLTemplate,
                        carrier: carrier,
                        trackingNumber: number
                    ),
                  let url = localizedURL(raw, carrier: carrier, language: language) else { return [] }
            return [ParcelTrackingLink(
                carrier: carrier,
                name: definition.trackingSiteName ?? definition.displayName,
                url: url,
                role: .active
            )]
        }

        let active = parcel.activeTrackingCarrier
        let ready = parcel.swissPostReady || active == .swissPost
        return [CarrierID.aliexpress, .swissPost].compactMap { carrier in
            let definition = info(for: carrier, language: language)
            guard let template = definition.trackingURLTemplate,
                  let url = localizedURL(
                    template.replacingOccurrences(
                        of: "{trackingNumber}", with: Self.urlEncode(parcel.trackingNumber)
                    ),
                    carrier: carrier,
                    language: language
                  ) else { return nil }
            let role: ParcelTrackingLink.Role = carrier == active
                ? .active
                : (carrier == .swissPost && !ready ? .waiting : .history)
            return ParcelTrackingLink(carrier: carrier, name: definition.trackingSiteName ?? definition.displayName, url: url, role: role)
        }.sorted { left, _ in left.role == .active }
    }

    /// The first candidate in the text that one carrier claims with certainty.
    private func recognizedNumber(in text: String) -> String? {
        for shape in Self.candidatePatterns {
            for candidate in Self.matches(in: text, pattern: shape.pattern, caseInsensitive: shape.caseInsensitive) {
                if detect(candidate).confidence == .high { return candidate.trimmingCharacters(in: .whitespaces) }
            }
        }
        return nil
    }

    /// The rule's link covers the host of the carrier's own tracking page.
    private func coversTrackingPage(_ rule: CarrierDefinition.LinkRule, of carrier: CarrierID) -> Bool {
        guard let template = definitions[carrier]?.trackingURLTemplate,
              let host = URLComponents(string: template.replacingOccurrences(of: "{trackingNumber}", with: ""))?
                .host?.lowercased() else { return false }
        return rule.domains.contains { host == $0 || host.hasSuffix(".\($0)") }
    }

    private func makeMatch(_ number: String, source: TrackingInputMatch.Source) -> TrackingInputMatch {
        let result = detect(number)
        return TrackingInputMatch(
            trackingNumber: number,
            carrier: result.carrier,
            confidence: result.confidence,
            candidates: result.candidates,
            trackingURL: nil,
            source: source
        )
    }

    private func localizedURL(_ raw: String, carrier: CarrierID, language: AppLanguage) -> URL? {
        guard var components = URLComponents(string: raw) else { return nil }
        if carrier == .swissPost {
            var items = components.queryItems ?? []
            items.removeAll { $0.name == "lang" }
            let siteLanguage = [AppLanguage.en, .de, .fr, .it].contains(language) ? language : .en
            items.append(URLQueryItem(name: "lang", value: siteLanguage.rawValue))
            components.queryItems = items
        } else if components.host == "t.17track.net" || components.host == "parcelsapp.com" {
            // Parcels has no Polish page; 17TRACK supports all app languages.
            let siteLanguage = components.host == "parcelsapp.com" && language == .pl ? AppLanguage.en : language
            components.path = components.path.replacingOccurrences(
                of: "^/[a-z]{2}(?=/|$)", with: "/\(siteLanguage.rawValue)", options: .regularExpression
            )
        }
        return components.url
    }

    /// JavaScript's `\s`, which the shared engine strips. ICU's `\s` misses U+FEFF, which copied
    /// text can carry, and adds U+0085.
    private static let separatorPattern =
        "[\\t\\n\\x{0B}\\f\\r\\x{20}\\x{A0}\\x{1680}\\x{2000}-\\x{200A}\\x{2028}\\x{2029}\\x{202F}\\x{205F}\\x{3000}\\x{FEFF}.-]"

    /// Capitals without separators. A label prints its SSCC behind the bracketed GS1 identifier,
    /// "(00) 3 7012345 678901234 7", which carriers track as the twenty digits without brackets;
    /// other bracketed identifiers stay as typed, since (420), for one, carries a ZIP.
    static func normalize(_ raw: String) -> String {
        let value = raw.uppercased()
        guard let separators = expression(separatorPattern) else { return value }
        let compact = separators.stringByReplacingMatches(
            in: value,
            range: NSRange(value.startIndex..., in: value),
            withTemplate: ""
        )
        return matches(compact, pattern: "^\\(00\\)[0-9]{18}$") ? "00" + compact.dropFirst(4) : compact
    }

    /// Six digits and nothing else, normalized: the code that opens a parcel locker or collects a parcel at a
    /// pickup point, which no carrier's detection claims. Only a carrier picked by hand takes one as its number.
    static func isPickupCode(_ normalized: String) -> Bool {
        matches(normalized, pattern: "^[0-9]{6}$")
    }

    /// How the front door reads a text (`shared/reading.json`), with the shapes of a number in a text
    /// that the shared engine tries, in its order.
    private struct ReadingRules: Decodable {
        struct Shop: Decodable {
            let shop: OrderShop
            let pattern: String
        }
        struct Shape: Decodable {
            let source: String
            let flags: String
        }
        struct Lookalikes: Decodable {
            let digitForLetter: [String: String]
            let letterForDigit: [String: String]
        }
        let shops: [Shop]
        let numberWords: String
        let lookalikes: Lookalikes
        let candidates: [Shape]
    }

    private static let readingRules: ReadingRules? = {
        guard let url = Bundle.main.url(forResource: "Reading", withExtension: "json"),
              let data = try? Data(contentsOf: url) else { return nil }
        return try? JSONDecoder().decode(ReadingRules.self, from: data)
    }()

    /// Each shop's order number, tried in turn and read without regard to case: the front door's patterns.
    static let orderNumbers: [(shop: OrderShop, pattern: String)] = readingRules?.shops.map { ($0.shop, $0.pattern) } ?? []

    /// The shapes of a number in a text, as the shared engine tries them (`TRACKING_CANDIDATE_PATTERNS`).
    static let candidatePatterns: [(pattern: String, caseInsensitive: Bool)] =
        readingRules?.candidates.map { ($0.source, $0.flags.contains("i")) } ?? []

    /// Words that introduce a tracking number in the app's languages.
    private static let numberWords = readingRules?.numberWords

    private static let linkPattern = "https?://[^\\s<>\\\"']+"

    /// Letters a scanner or a tired eye reads where a label prints a digit, and the other way around.
    private static let digitForLetter = characters(readingRules?.lookalikes.digitForLetter)
    private static let letterForDigit = characters(readingRules?.lookalikes.letterForDigit)

    private static func characters(_ map: [String: String]?) -> [Character: Character] {
        (map ?? [:]).reduce(into: [:]) { characters, pair in
            if let key = pair.key.first, let value = pair.value.first { characters[key] = value }
        }
    }

    /// A postal number (2 letters, 9 digits, 2 letters) whose check digit doesn't add up, as the front door
    /// reads it (`checkDigitTrouble` in src/peek/lookup/reading.ts). A letter standing where a digit belongs
    /// has one likely correction; a wrong digit has many, each as likely as the next, so none is offered.
    /// Carriers that reuse the shape without the check digit exist, so this is a question, never a refusal.
    func checkDigitTrouble(_ number: String) -> CheckDigitTrouble? {
        let value = Self.normalize(number)
        guard Self.matches(value, pattern: "^[A-Z0-9]{13}$"), !Self.isValidS10(value), detect(value).confidence != .high
        else { return nil }
        var postal = ""
        for (index, character) in value.enumerated() {
            let digit = character.isASCII && character.isNumber
            let read = (2...10).contains(index)
                ? (digit ? character : Self.digitForLetter[character])
                : (digit ? Self.letterForDigit[character] : character)
            guard let read else { return nil }
            postal.append(read)
        }
        if postal == value { return CheckDigitTrouble(suggestion: nil) }
        return Self.isValidS10(postal) ? CheckDigitTrouble(suggestion: postal) : nil
    }

    /// The shop whose order number a text holds.
    static func orderShop(in text: String) -> OrderShop? {
        orderNumbers.first { matches(text, pattern: $0.pattern, caseInsensitive: true) }?.shop
    }

    /// The text without its order numbers, so that no part of one is read as a tracking number.
    private static func withoutOrders(_ text: String) -> String {
        orderNumbers.reduce(text) { rest, order in blanking(order.pattern, in: rest, caseInsensitive: true) }
    }

    /// The text with a space for each match of the pattern.
    private static func blanking(_ pattern: String, in text: String, caseInsensitive: Bool = false) -> String {
        expression(pattern, caseInsensitive: caseInsensitive)?.stringByReplacingMatches(
            in: text,
            range: NSRange(text.startIndex..., in: text),
            withTemplate: " "
        ) ?? text
    }

    static func format(_ raw: String, carrier: CarrierID? = nil) -> String {
        let value = normalize(raw)
        if carrier == .postlogistics {
            let printed = raw.trimmingCharacters(in: .whitespacesAndNewlines)
            if matches(printed, pattern: "^[0-9]{11}$") {
                return "\(printed.prefix(8))-\(printed.suffix(3))"
            }
            return printed
        }
        if matches(value, pattern: "^99990\\d{8}$") {
            return "\(value.prefix(3)).\(value.dropFirst(3).prefix(2)).\(value.dropFirst(5))"
        }
        if matches(value, pattern: "^\\d{18}$") {
            return "\(value.prefix(2)).\(value.dropFirst(2).prefix(2)).\(value.dropFirst(4).prefix(6)).\(value.dropFirst(10))"
        }
        return value
    }

    /// The rule's check, as the shared engine runs it; nil for an id this build does not know,
    /// which a newer downloaded catalog can name. Detection then keeps the rule's carrier as a
    /// low-confidence candidate that is never preferred.
    static func checksumPasses(_ checksum: String, _ number: String) -> Bool? {
        switch checksum {
        case "mondial-relay": isValidMondialRelayBarcode(number)
        case "s10": isValidS10(number)
        case "hermes": isValidHermesParcelNumber(number)
        case "gls": isValidGlsParcelNumber(number)
        case "dhl-express": isValidDhlExpressWaybill(number)
        case "tnt": isValidTntConsignmentNumber(number)
        case "poczta-polska": isValidPocztaPolskaBarcode(number)
        case "correos-spain": isValidCorreosSpainCheckLetter(number)
        case "dpd": isValidDpdParcelNumber(number)
        case "usps": isValidUspsPackageBarcode(number)
        case "sscc": isValidSscc(number)
        case "ups": isValidUpsTrackingNumber(number)
        case "colissimo": isValidColissimoParcelNumber(number)
        case "ukrposhta": isValidUkrposhtaBarcode(number)
        case "evri": isValidEvriParcelNumber(number)
        case "mod7": hasMod7CheckDigit(number)
        case "gs1": hasGs1CheckDigit(number)
        case "ontrac": isValidOnTracTrackingNumber(number)
        case "luhn": hasLuhnCheckDigit(number)
        case "fedex": isValidFedExTrackingNumber(number)
        case "sf-express": isValidSfExpressWaybill(number)
        case "fedex-ground-96": isValidFedExGround96Barcode(number)
        case "fedex-1d": isValidFedEx1DBarcode(number)
        case "identcode": isValidDhlIdentcode(number)
        case "fedex-ground-economy": isValidFedExGroundEconomyNumber(number)
        case "pos-laju": isValidPosLajuConsignment(number)
        case "saia": isValidSaiaPro(number)
        case "magyar-posta": isValidMagyarPostaNumber(number)
        case "poste-italiane": isValidPosteItalianeRegisteredNumber(number)
        case "frankier-id": isValidFrankierId(number)
        case "dhl-ecommerce-pl": isValidDhlEcommercePolandNumber(number)
        default: nil
        }
    }

    /// Mod 11 with weights 2 to 7 repeating from the right; a result of 10 or 11 becomes 0.
    private static func mod11CheckDigit(_ digits: ArraySlice<Int>) -> Int {
        let sum = digits.reversed().enumerated().reduce(0) { $0 + $1.element * (2 + $1.offset % 6) }
        let remainder = 11 - sum % 11
        return remainder >= 10 ? 0 : remainder
    }

    static func isValidMondialRelayBarcode(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{26}$") else { return false }
        let digits = value.compactMap(\.wholeNumberValue)
        func check(_ range: Range<Int>) -> Int { mod11CheckDigit(digits[range]) }
        let sequence = digits[10] * 10 + digits[11]
        let count = digits[12] * 10 + digits[13]
        return sequence > 0 && sequence <= count && check(0..<14) == digits[14] && check(15..<25) == digits[25]
    }

    /// Hermes Germany's legacy 14-digit numbers: a modulo-10 check digit weighted 3, 1, 3, … from the left.
    static func isValidHermesParcelNumber(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{14}$") else { return false }
        let digits = value.compactMap(\.wholeNumberValue)
        let sum = digits[0..<13].enumerated().reduce(0) { $0 + $1.element * ($1.offset % 2 == 0 ? 3 : 1) }
        return (10 - sum % 10) % 10 == digits[13]
    }

    /// Deutsche Post's Identcode on DHL Paket's 12-digit numbers: a modulo-10 check digit weighted
    /// 4, 9, 4, … from the left.
    static func isValidDhlIdentcode(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{12}$") else { return false }
        let digits = value.compactMap(\.wholeNumberValue)
        let sum = digits[0..<11].enumerated().reduce(0) { $0 + $1.element * ($1.offset % 2 == 0 ? 4 : 9) }
        return (10 - sum % 10) % 10 == digits[11]
    }

    /// DHL eCommerce Poland's 11-digit parcel numbers: the Identcode's weights, 4, 9, 4, … from the
    /// left, over ten digits and a modulo-10 check digit.
    static func isValidDhlEcommercePolandNumber(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{11}$") else { return false }
        let digits = value.compactMap(\.wholeNumberValue)
        let sum = digits[0..<10].enumerated().reduce(0) { $0 + $1.element * ($1.offset % 2 == 0 ? 4 : 9) }
        return (10 - sum % 10) % 10 == digits[10]
    }

    /// GLS 12-digit parcel numbers: a modulo-10 check digit weighted 3, 1, 3, … from the right, plus one.
    static func isValidGlsParcelNumber(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{12}$") else { return false }
        let digits = value.compactMap(\.wholeNumberValue)
        let sum = digits[0..<11].reversed().enumerated().reduce(1) { $0 + $1.element * ($1.offset % 2 == 0 ? 3 : 1) }
        return (10 - sum % 10) % 10 == digits[11]
    }

    /// Ukrposhta's 13-digit domestic barcode ends in the mod 11 check of its first twelve digits.
    static func isValidUkrposhtaBarcode(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{13}$") else { return false }
        let digits = value.compactMap(\.wholeNumberValue)
        return mod11CheckDigit(digits[0..<12]) == digits[12]
    }

    /// The number before the last digit, divided by seven, leaves the last digit: Yamato's
    /// 12-digit numbers, and Blue Dart and Aramex 11-digit waybills alike.
    static func hasMod7CheckDigit(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{2,15}$"),
              let serial = Int(value.dropLast()), let check = value.last?.wholeNumberValue else { return false }
        return serial % 7 == check
    }

    /// A twelve-digit Saia PRO: ten digits, a 0, then the remainder of the ten divided by seven.
    /// The check is inferred from public PROs; one with another eleventh digit is not checked.
    static func isValidSaiaPro(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{10}0[0-6]$"),
              let ten = Int(value.prefix(10)), let check = value.last?.wholeNumberValue else { return false }
        return ten % 7 == check
    }

    /// Magyar Posta's domestic numbers: `P`, a product letter, a three-character customer code,
    /// seven digits and a check digit. The seven digits weigh 3, 1, 3, … from the left; the sum
    /// mod 10 is the last digit, with no complement. The code is outside the check.
    static func isValidMagyarPostaNumber(_ value: String) -> Bool {
        guard matches(value, pattern: "^P[A-Z][0-9A-Z]{3}[0-9]{8}$") else { return false }
        let digits = value.dropFirst(5).compactMap(\.wholeNumberValue)
        let sum = digits[0..<7].enumerated().reduce(0) { $0 + $1.element * ($1.offset % 2 == 0 ? 3 : 1) }
        return sum % 10 == digits[7]
    }

    /// Poste Italiane's registered mail: eleven digits and a check digit. Eleven times the sum of
    /// the digits in even places, plus the sum of those in odd places, gives a number whose digit
    /// sum mod 10 is the last digit.
    static func isValidPosteItalianeRegisteredNumber(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{12}$") else { return false }
        let digits = value.compactMap(\.wholeNumberValue)
        let sums = digits[0..<11].enumerated().reduce(into: (odd: 0, even: 0)) { sums, item in
            if item.offset % 2 == 0 { sums.odd += item.element } else { sums.even += item.element }
        }
        let digitSum = String(11 * sums.even + sums.odd).compactMap(\.wholeNumberValue).reduce(0, +)
        return digitSum % 10 == digits[11]
    }

    /// Deutsche Post's Frankier-ID: nineteen characters and a hexadecimal check character, the
    /// CRC-4 (polynomial x⁴ + x + 1, from zero) of the nineteen as ASCII bytes, most significant
    /// bit first.
    static func isValidFrankierId(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9A-Z]{19}[0-9A-F]$"), let check = value.last?.hexDigitValue else { return false }
        var crc = 0
        for byte in value.utf8.prefix(19) {
            for bit in (0..<8).reversed() {
                let carry = (crc >> 3) ^ (Int(byte >> UInt8(bit)) & 1)
                crc = (crc << 1) & 0xF
                if carry == 1 { crc ^= 0x3 }
            }
        }
        return crc == check
    }

    /// GS1 mod 10: counted from the check digit, the digits before it weigh 3, 1, 3, …
    static func hasGs1CheckDigit(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{2,}$") else { return false }
        let digits = value.compactMap(\.wholeNumberValue)
        let sum = digits.dropLast().reversed().enumerated().reduce(0) { $0 + $1.element * ($1.offset % 2 == 0 ? 3 : 1) }
        return (10 - sum % 10) % 10 == digits[digits.count - 1]
    }

    /// Luhn over every digit, the last one being the check: Purolator's 12-digit PINs.
    static func hasLuhnCheckDigit(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{2,}$") else { return false }
        let sum = value.compactMap(\.wholeNumberValue).reversed().enumerated().reduce(0) { total, item in
            let doubled = item.element * (item.offset % 2 == 0 ? 1 : 2)
            return total + (doubled > 9 ? doubled - 9 : doubled)
        }
        return sum % 10 == 0
    }

    /// FedEx 12-digit numbers: the first eleven digits weigh 3, 1, 7, … from the left, and the
    /// sum mod 11 mod 10 is the last digit.
    static func isValidFedExTrackingNumber(_ value: String) -> Bool {
        guard matches(value, pattern: "^[0-9]{12}$") else { return false }
        let digits = value.compactMap(\.wholeNumberValue)
        let weights = [3, 1, 7]
        let sum = digits[0..<11].enumerated().reduce(0) { $0 + $1.element * weights[$1.offset % 3] }
        return sum % 11 % 10 == digits[11]
    }

    /// FedEx Ground's 22-digit `96` barcode ends in the 15-digit tracking number it carries;
    /// its GS1 mod 10 check covers only those fifteen digits.
    static func isValidFedExGround96Barcode(_ value: String) -> Bool {
        matches(value, pattern: "^96[0-9]{20}$") && hasGs1CheckDigit(String(value.dropFirst(7)))
    }

    /// FedEx Ground Economy's 20-digit number is a USPS package identifier without its `92`
    /// channel; its check digit is the identifier's MOD10, computed with the `92` in front.
    static func isValidFedExGroundEconomyNumber(_ value: String) -> Bool {
        matches(value, pattern: "^[0-9]{20}$") && hasGs1CheckDigit("92" + value)
    }

    /// FedEx's 34-digit barcode carries a 12-digit FedEx number behind two zeros in positions
    /// 21–34; its own check reduces there to the 12-digit check.
    static func isValidFedEx1DBarcode(_ value: String) -> Bool {
        matches(value, pattern: "^[0-9]{20}00[0-9]{12}$") && isValidFedExTrackingNumber(String(value.suffix(12)))
    }

    /// A digit keeps its value; a letter counts as its ASCII code minus 63, mod 10 (A = 2, …, Z = 7).
    private static func upsCharacterValue(_ character: Character) -> Int {
        character.wholeNumberValue ?? (Int(character.asciiValue ?? 63) - 63) % 10
    }

    /// Weights 1, 2, 1, … from the left, plain products, and ten's complement.
    private static func upsCheckDigit(_ payload: Substring) -> Int {
        let sum = payload.enumerated().reduce(0) { $0 + upsCharacterValue($1.element) * ($1.offset % 2 == 0 ? 1 : 2) }
        return (10 - sum % 10) % 10
    }

    /// UPS `1Z` numbers: fifteen characters and a check digit over those fifteen.
    static func isValidUpsTrackingNumber(_ value: String) -> Bool {
        guard matches(value, pattern: "^1Z[0-9A-Z]{15}[0-9]$") else { return false }
        return upsCheckDigit(value.dropFirst(2).prefix(15)) == value.last?.wholeNumberValue
    }

    /// OnTrac's `C` or `D` and fourteen digits: the letter counts as 4 or 5 in the UPS check.
    static func isValidOnTracTrackingNumber(_ value: String) -> Bool {
        guard matches(value, pattern: "^[CD][0-9]{14}$") else { return false }
        return upsCheckDigit(value.prefix(14)) == value.last?.wholeNumberValue
    }

    /// Evri 16-character parcel numbers: the first fifteen characters, valued as UPS values
    /// letters, weigh 2, 1, 2, … from the left; the sum mod 10 is the last digit.
    static func isValidEvriParcelNumber(_ value: String) -> Bool {
        guard matches(value, pattern: "^[HT][0-9A-Z]{5}[0-9]{10}$") else { return false }
        let sum = value.prefix(15).enumerated().reduce(0) { $0 + upsCharacterValue($1.element) * ($1.offset % 2 == 0 ? 2 : 1) }
        return sum % 10 == value.last?.wholeNumberValue
    }

    /// Colissimo's two-character product code, ten digits and their GS1 key.
    static func isValidColissimoParcelNumber(_ value: String) -> Bool {
        matches(value, pattern: "^[0-9A-Z]{2}[0-9]{11}$") && hasGs1CheckDigit(String(value.dropFirst(2)))
    }

    /// SF Express waybills: after the three-digit area code, the serial weighs 1, 3, 5, … from the
    /// right; each product adds its tens and units, and the last digit tops the sum up to a
    /// multiple of ten.
    static func isValidSfExpressWaybill(_ value: String) -> Bool {
        guard matches(value, pattern: "^(?:[0-9]{12}|SF[0-9]{13})$") else { return false }
        let digits = (value.hasPrefix("SF") ? value.dropFirst(2) : Substring(value)).compactMap(\.wholeNumberValue)
        let sum = digits[3..<(digits.count - 1)].reversed().enumerated().reduce(0) { total, item in
            let product = item.element * (2 * item.offset + 1)
            return total + product / 10 + product % 10
        }
        return (10 - sum % 10) % 10 == digits[digits.count - 1]
    }

    /// An SSCC behind its GS1 application identifier `00`. Shippers issue SSCCs, so a valid
    /// one names no carrier.
    static func isValidSscc(_ value: String) -> Bool {
        matches(value, pattern: "^00[0-9]{18}$") && hasGs1CheckDigit(value)
    }

    static func isValidDhlExpressWaybill(_ value: String) -> Bool {
        guard value.count == 10, matches(value, pattern: "^[0-9]{10}$"),
              let serial = Int(value.prefix(9)), let check = value.last?.wholeNumberValue else { return false }
        return serial % 7 == check
    }

    static func isValidTntConsignmentNumber(_ value: String) -> Bool {
        guard value.count == 9, matches(value, pattern: "^[0-9]{9}$"),
              let serial = Int(value.prefix(8)), let check = value.last?.wholeNumberValue else { return false }
        if serial % 7 == check { return true }
        let digits = value.compactMap(\.wholeNumberValue)
        let weights = [8, 6, 4, 2, 3, 5, 9, 7]
        let sum = weights.enumerated().reduce(0) { $0 + digits[$1.offset] * $1.element }
        let digit = 11 - sum % 11
        return (digit == 11 ? 5 : digit == 10 ? 0 : digit) == check
    }

    static func isValidPocztaPolskaBarcode(_ value: String) -> Bool {
        guard value.count == 20, matches(value, pattern: "^[0-9]{20}$") else { return false }
        let digits = value.compactMap(\.wholeNumberValue)
        let sum = digits[0..<19].enumerated().reduce(0) { $0 + $1.element * ($1.offset % 2 == 0 ? 3 : 1) }
        return (10 - sum % 10) % 10 == digits[19]
    }

    /// Correos parcel and expedition codes: the summed character codes before the last letter pick it from the tax-id letter table.
    static func isValidCorreosSpainCheckLetter(_ value: String) -> Bool {
        let letters = Array("TRWAGMYFPDXBNJZSQVHLCKE")
        guard value.count > 1, let last = value.last else { return false }
        let sum = value.dropLast().unicodeScalars.reduce(0) { $0 + Int($1.value) }
        return letters[sum % letters.count] == last
    }

    /// DPD parcel labels: fourteen digits and the ISO/IEC 7064 MOD 37,36 check character printed after them.
    static func isValidDpdParcelNumber(_ value: String) -> Bool {
        guard value.count == 15, matches(value, pattern: "^[0-9]{14}[0-9A-Z]$") else { return false }
        let alphabet = Array("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        var remainder = 36
        for digit in value.prefix(14).compactMap(\.wholeNumberValue) {
            remainder += digit
            if remainder > 36 { remainder -= 36 }
            remainder *= 2
            if remainder > 36 { remainder -= 37 }
        }
        return alphabet[(37 - remainder) % 36] == value.last
    }

    /// The ninth of nine digits checks the first eight: weights 8, 6, 4, 2, 3, 5, 9, 7, then
    /// 11 minus the sum mod 11, where 10 becomes 0 and 11 becomes 5.
    private static func hasS10CheckDigit(_ digits: Substring) -> Bool {
        let values = digits.compactMap(\.wholeNumberValue)
        guard values.count == 9 else { return false }
        let weights = [8, 6, 4, 2, 3, 5, 9, 7]
        let sum = weights.enumerated().reduce(0) { $0 + values[$1.offset] * $1.element }
        let rawCheck = 11 - sum % 11
        let expected = rawCheck == 10 ? 0 : (rawCheck == 11 ? 5 : rawCheck)
        return values[8] == expected
    }

    static func isValidS10(_ raw: String) -> Bool {
        let value = normalize(raw)
        guard matches(value, pattern: "^[A-Z]{2}[0-9]{9}[A-Z]{2}$") else { return false }
        return hasS10CheckDigit(value.dropFirst(2).prefix(9))
    }

    /// A Pos Laju consignment: three letters, nine digits and MY. The ninth digit is the S10
    /// check digit of the eight before it; the extra letter is outside the check.
    static func isValidPosLajuConsignment(_ value: String) -> Bool {
        matches(value, pattern: "^[A-Z]{3}[0-9]{9}MY$") && hasS10CheckDigit(value.dropFirst(3).prefix(9))
    }

    static func supportsSwissPostHandoff(_ raw: String) -> Bool {
        let value = normalize(raw)
        return matches(value, pattern: "^L[A-Z]\\d{9}CH$") && isValidS10(value)
    }

    /// USPS package barcodes: only the PIC participates in MOD10; a routing ZIP precedes it.
    static func isValidUspsPackageBarcode(_ raw: String) -> Bool {
        let number = normalize(raw)
        func validPic(_ pic: String) -> Bool {
            // Retail 95 and legacy 91 are read only at 22 digits, so a ZIP+4 add-on never splits ambiguously.
            guard matches(pic, pattern: "^(?:9[1-5][0-9]{20}|9[2-4][0-9]{24})$") else { return false }
            let sum = pic.compactMap(\.wholeNumberValue).reversed().enumerated()
                .reduce(0) { $0 + $1.element * ($1.offset % 2 == 0 ? 1 : 3) }
            return sum % 10 == 0
        }
        if validPic(number) { return true }
        // The ship-to AI 420 and a ZIP5 or ZIP9 before a 22- or 26-digit PIC, by length alone.
        guard matches(number, pattern: "^420(?:[0-9]{5}|[0-9]{9})(?:[0-9]{22}|[0-9]{26})$") else { return false }
        let readings = [String(number.dropFirst(8)), String(number.dropFirst(12))].filter(validPic)
        if readings.count < 2 { return readings.count == 1 }
        // A 34-digit scan fits ZIP5/PIC26 and ZIP9/PIC22. When both pass, the one whose Mailer ID
        // fits its channel wins: nine digits starting with 9 after 92 and the legacy 91's service
        // code, six starting with 0 to 8 after 93. If both or neither fit, the split is rejected.
        return readings.filter { matches($0, pattern: "^(?:91[0-9]{2}9|92[0-9]{3}9|93[0-9]{3}[0-8]|9[45])") }.count == 1
    }

    /// A number introduced by a "tracking number:" or "Waybill No." style label, as the web engine reads it.
    /// "No" and "ID" also start numbers, so they belong to the label only when they stand
    /// apart from what follows. The token holds a digit, so a label followed by a plain
    /// word does not end the search.
    private static let labelledNumberPattern: String = {
        let suffix = "(?:numbers?(?![A-Z])|no\\.|(?:no|id)(?![A-Z0-9.]))"
        return "(?:track(?:ing)?(?:\\s*\(suffix)|(?![A-Z]))"
            + "|(?:parcel|shipment|waybill|awb)(?:\\s+(?:tracking(?![A-Z])|\(suffix))|(?![A-Z])))"
            + "(?:\\s+is(?![A-Z0-9]))?\\s*[:#-]?\\s*"
            + "((?=[A-Z0-9.-]{0,39}\\d)[A-Z0-9][A-Z0-9.-]{3,39})"
    }()

    private static func shaped(_ value: String) -> Bool {
        (4...40).contains(value.count)
            && (matches(value, pattern: "^[A-Z0-9]+$") || matches(value, pattern: "^\\d{4}/\\d{8}$"))
    }

    /// A number entered whole or carried by a carrier's link. Without a digit it must be
    /// six to ten unbroken letters that a carrier's detection rule claims, as GLS issues
    /// six-letter Track IDs: a word no carrier uses is not a number.
    private func valid(_ raw: String) -> Bool {
        let value = Self.normalize(raw)
        guard Self.shaped(value) else { return false }
        if Self.matches(value, pattern: "\\d") { return true }
        return Self.matches(raw.trimmingCharacters(in: .whitespacesAndNewlines), pattern: "^[A-Za-z]{6,10}$")
            && detect(value).confidence != .none
    }

    /// A number pulled out of prose always holds a digit: a word there is not a number.
    private static func validInText(_ raw: String) -> Bool {
        let value = normalize(raw)
        return shaped(value) && matches(value, pattern: "\\d")
    }

    /// Compiling a pattern costs far more than matching it, and detection runs
    /// every carrier rule for each keystroke and card.
    private static func expression(_ pattern: String, caseInsensitive: Bool = false) -> NSRegularExpression? {
        let key = ((caseInsensitive ? "i:" : "s:") + pattern) as NSString
        if let cached = expressions.object(forKey: key) { return cached }
        let options: NSRegularExpression.Options = caseInsensitive ? .caseInsensitive : []
        guard let compiled = try? NSRegularExpression(pattern: endOfInput(pattern), options: options) else { return nil }
        expressions.setObject(compiled, forKey: key)
        return compiled
    }

    /// The shared patterns are JavaScript's, where `$` is the end of the input. ICU's `$` also
    /// matches before a final line break, so it becomes `\z` outside character classes.
    private static func endOfInput(_ pattern: String) -> String {
        var result = ""
        var escaped = false
        var inClass = false
        for character in pattern {
            if escaped {
                escaped = false
            } else if character == "\\" {
                escaped = true
            } else if character == "[" {
                inClass = true
            } else if character == "]" {
                inClass = false
            } else if character == "$", !inClass {
                result += "\\z"
                continue
            }
            result.append(character)
        }
        return result
    }

    private static func matches(_ value: String, pattern: String, caseInsensitive: Bool = false) -> Bool {
        guard let expression = expression(pattern, caseInsensitive: caseInsensitive) else { return false }
        return expression.firstMatch(
            in: value,
            range: NSRange(value.startIndex..., in: value)
        ) != nil
    }

    private static func matches(
        in value: String,
        pattern: String,
        caseInsensitive: Bool = false
    ) -> [String] {
        hits(in: value, pattern: pattern, caseInsensitive: caseInsensitive).map(\.text)
    }

    /// Each match with where it stands in the value.
    private static func hits(
        in value: String,
        pattern: String,
        caseInsensitive: Bool = false
    ) -> [(range: NSRange, text: String)] {
        guard let expression = expression(pattern, caseInsensitive: caseInsensitive) else { return [] }
        return expression.matches(in: value, range: NSRange(value.startIndex..., in: value)).compactMap { match in
            Range(match.range, in: value).map { (match.range, String(value[$0])) }
        }
    }

    private static func capture(
        _ value: String,
        pattern: String,
        caseInsensitive: Bool = false
    ) -> String? {
        guard let expression = expression(pattern, caseInsensitive: caseInsensitive),
              let match = expression.firstMatch(
                in: value,
                range: NSRange(value.startIndex..., in: value)
              ),
              match.numberOfRanges > 1,
              let range = Range(match.range(at: 1), in: value) else { return nil }
        return String(value[range])
    }

    private static func urlEncode(_ value: String) -> String {
        value.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? value
    }

    private static func renderTrackingURL(
        _ template: String?,
        carrier: CarrierID,
        trackingNumber: String
    ) -> String? {
        if carrier == .amazonLogistics { return amazonOrdersURL(trackingNumber).absoluteString }
        if carrier == .amazonShipping { return amazonShippingURL(trackingNumber).absoluteString }
        let normalized = normalize(trackingNumber)
        // The country in these families chooses Ninja Van's lookup page, not the destination.
        if carrier == .ninjaVan {
            var country = "my"
            if normalized.range(of: "^NJVTT[0-9]{11}$", options: .regularExpression) != nil {
                country = "id"
            } else if normalized.range(
                of: "^(?:NL(SG|MY|ID|PH|TH|VN)[A-Z]{1,2}[0-9]{8,10}|NV(SG|MY|ID|PH|TH|VN)(?!STAMP[A-Z0-9]{9}$)[A-Z0-9]{8,20})$",
                options: .regularExpression
            ) != nil {
                country = normalized.dropFirst(2).prefix(2).lowercased()
            }
            let host = country == "id" ? "www.ninjaxpress.co" : "www.ninjavan.co"
            return "https://\(host)/en-\(country)/tracking?id=\(urlEncode(normalized))"
        }
        guard let template else { return nil }
        var linkNumber = trackingNumber
        if carrier == .mondialRelay, isValidMondialRelayBarcode(normalized) { linkNumber = String(normalized.prefix(12)) }
        if carrier == .cChezVous,
           normalized.range(of: "^[A-Z0-9]{11}[0-9]{5}$", options: .regularExpression) != nil {
            let split = normalized.index(normalized.startIndex, offsetBy: 11)
            linkNumber = "\(normalized[..<split])--\(normalized[split...])"
        }
        if carrier == .theCourierGuy,
           normalized.range(of: "^(DD|LD)[A-Z0-9]{6}$", options: .regularExpression) != nil {
            let split = normalized.index(normalized.startIndex, offsetBy: 2)
            linkNumber = "\(normalized[..<split])-\(normalized[split...])"
        }
        if carrier == .postlogistics { linkNumber = format(trackingNumber, carrier: carrier) }
        // CDL's page finds an order number only with the hyphen before its last digit.
        if carrier == .cdl, normalized.range(of: "^[0-9]{10}$", options: .regularExpression) != nil {
            linkNumber = "\(normalized.prefix(9))-\(normalized.suffix(1))"
        }
        // GOFO France's page keeps a shipper reference's hyphens.
        if carrier == .gofoFr {
            linkNumber = normalized.range(of: "^PK[0-9]{20}$", options: .regularExpression) != nil
                ? "PK-\(normalized.dropFirst(2).dropLast())-\(normalized.suffix(1))"
                : normalized
        }
        // Ecoscooting's page finds a Cainiao reference only with its CN.
        if carrier == .ecoscooting,
           normalized.range(of: "^(?:ESP[0-9]{5}1234[0-9]{11}|PRT[0-9]{7}1234[0-9]{9})$", options: .regularExpression) != nil {
            linkNumber = "CN\(normalized)"
        }
        // InPost's Polish page does not find Yodel's layouts; InPost UK's page does.
        if carrier == .inpost,
           normalized.range(of: "^(J?JD[0-9]{16}|8YDR[0-9]{9}|[0-9]{2}[A-Z]{3}[0-9]{7}A[0-9]{3})$", options: .regularExpression) != nil {
            return "https://inpost.co.uk/tracking/result?parcel_code=\(urlEncode(normalized))"
        }
        return template.replacingOccurrences(
            of: "{trackingNumber}",
            with: urlEncode(linkNumber)
        )
    }

    private static func bundledDefinitions(in bundle: Bundle) -> [CarrierID: CarrierDefinition] {
        guard let url = bundle.url(forResource: "CarrierCatalog", withExtension: "json"),
              let data = try? Data(contentsOf: url),
              let contract = try? JSONDecoder().decode(Contract.self, from: data),
              let definitions = try? validatedDefinitions(contract) else {
            return fallbackDefinitions
        }
        return definitions
    }

    private static func cachedContract(
        at url: URL
    ) -> (definitions: [CarrierID: CarrierDefinition], etag: String?)? {
        guard let data = try? Data(contentsOf: url),
              let record = try? JSONDecoder().decode(CacheRecord.self, from: data),
              let definitions = try? validatedDefinitions(record.contract) else { return nil }
        return (definitions, record.etag)
    }

    private static func validatedDefinitions(
        _ contract: Contract
    ) throws -> [CarrierID: CarrierDefinition] {
        let validEntries = contract.carriers.filter { key, _ in
            matches(key, pattern: "^[a-z0-9]+(?:-[a-z0-9]+)*$") && key.count <= 64
        }
        let definitions = Dictionary(uniqueKeysWithValues: validEntries.map { key, value in
            (CarrierID(rawValue: key), value)
        })
        guard definitions[.unknown] != nil,
              definitions.values.contains(where: \.selectable) else {
            throw CatalogError.invalidContract
        }
        return definitions
    }

    private func saveCache(contract: Contract, etag: String?) {
        guard let cacheURL,
              let data = try? JSONEncoder().encode(CacheRecord(etag: etag, contract: contract)) else {
            return
        }
        let directory = cacheURL.deletingLastPathComponent()
        try? FileManager.default.createDirectory(
            at: directory,
            withIntermediateDirectories: true
        )
        try? data.write(to: cacheURL, options: .atomic)
    }

    /// The cache is re-encoded from the decoded structs, so a file written by a build
    /// that did not know a catalog field has lost it. Bump the name when one is added.
    private static func defaultCacheURL() -> URL? {
        FileManager.default.urls(for: .cachesDirectory, in: .userDomainMask).first?
            .appending(path: "delivery-tracker", directoryHint: .isDirectory)
            .appending(path: "carrier-catalog-v5.json")
    }

    static let emptyMatch = TrackingInputMatch(
        trackingNumber: "",
        carrier: .unknown,
        confidence: .none,
        candidates: [],
        trackingURL: nil,
        source: .none
    )

    private static let fallbackDefinitions: [CarrierID: CarrierDefinition] = [
        .unknown: CarrierDefinition(
            displayName: "Unknown carrier",
            displayNames: ["en": "Unknown carrier", "de": "Paketdienst unbekannt",
                           "fr": "Transporteur inconnu", "it": "Corriere sconosciuto"],
            trackingSiteName: nil,
            color: CarrierVisualIdentity.defaultCarrierColor,
            selectable: false,
            timezone: "UTC",
            tracking: .init(mode: "link-only", adapter: nil, requirements: nil),
            trackingURLTemplate: nil,
            linkRules: [],
            detectionRules: []
        ),
    ]
}
