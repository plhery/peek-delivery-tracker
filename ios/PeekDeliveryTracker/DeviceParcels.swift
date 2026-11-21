import Foundation
import os

/// A parcel followed on this iPhone without an account: the link the service made for it, the
/// key that owns the link, and what this device knows about the parcel. A parcel followed
/// through someone else's link has no key: this iPhone reads it as anyone with the link may.
struct DeviceParcel: Codable, Equatable, Sendable {
    var linkID: String
    /// The name given on this device; the service never learns it.
    var label: String
    var archivedAt: String?
    var parcel: Parcel
    var checkedAt: Date
    /// The parcel this one took the place of when its carrier was changed, so an open page keeps showing it.
    var replaced: UUID? = nil
    /// Followed through someone else's link: read without a key, and forgotten on this iPhone alone.
    var viewer: Bool? = nil
    /// Whether an account may keep the parcel behind someone else's link, as the link last said.
    var canKeep: Bool? = nil
    /// What its notifications on this iPhone tell, as last asked for: the preset chosen for every parcel in Settings, or
    /// while none is, the one picked when they were first turned on.
    var alertPreset: ParcelAlertPreset? = nil
    /// The device token the service last answered about for this parcel's notifications.
    var alertToken: String? = nil
    /// Someone else's parcel forgotten here before its notifications could be turned off: out of
    /// the list, and kept only until they are.
    var forgotten: Bool? = nil
    /// Whether a postcode went with the lookup that made the parcel, which its link never shows: yes or no only,
    /// never the postcode. Nil where this iPhone doesn't know, such as a parcel it kept before it noted this.
    var postcodeGiven: Bool? = nil
    /// The parcel's news this iPhone counts as seen: the `newsAt` it had when it was last opened here, or when it
    /// started as seen. Nil on a parcel kept before this was noted, which shows no mark and starts as seen with its
    /// next answer: what an older version kept has no `newsAt` to start from.
    var openedNews: String? = nil
    /// Its notifications were turned off with the bell on its page: rounds pass over it until they are turned on there
    /// again.
    var muted: Bool? = nil
    /// When this iPhone found that the service had forgotten its lookup (30 days after delivery, or 90 days without
    /// news): the parcel then stays as it was last shown, no longer updated, without its key or notifications, and is
    /// never read again, until it is removed here or a new lookup of its number takes its place. Nil while the service
    /// has it.
    var frozen: String? = nil

    var isViewer: Bool { viewer == true }
    var isMuted: Bool { muted == true }
    var isFrozen: Bool { frozen != nil }

    /// What `openedNews` holds for a parcel seen before its first scan: earlier than any news.
    static let seenBeforeAnyNews = "1970-01-01T00:00:00Z"

    /// The parcel as it stands is seen: what it tells now is no news.
    mutating func startSeen() { openedNews = parcel.newsAt ?? Self.seenBeforeAnyNews }

    /// Takes the service's newer answer. A parcel's first answer is no news, as the service starts an account's
    /// parcel: the first one with a check landed, also after a change of carrier or number.
    mutating func take(_ answer: Parcel) {
        let first = parcel.lastSyncedAt == nil && answer.lastSyncedAt != nil
        parcel = answer
        if first || openedNews == nil { startSeen() }
    }
}

/// How the service answered when this iPhone asked for a parcel's notifications.
enum DeviceAlertAnswer: Equatable, Sendable {
    /// On, or the journey is over and there is nothing left to tell.
    case on
    /// The link leads nowhere now.
    case gone
    /// The link has as many alerts as it may.
    case full
    /// Its owner stopped sharing it.
    case stopped
    /// The server sends no notifications to iPhones, or can't for now.
    case unavailable
}

/// The last postcodes given for lookups without an account, with their carriers, newest first.
/// They belong to this iPhone rather than to a parcel: they outlast forgotten parcels and a
/// sign-in, and fill in the next parcel's field. An account's postcodes stay on its parcels.
struct PostcodeMemory {
    private static let key = "sdt.postcodeMemory.v1"
    /// Most parcels go to the same few addresses.
    static let limit = 5

    private let defaults: UserDefaults
    private let catalog: CarrierCatalog

    init(defaults: UserDefaults = .standard, catalog: CarrierCatalog = .shared) {
        self.defaults = defaults
        self.catalog = catalog
    }

    var entries: [GivenPostcode] {
        defaults.data(forKey: Self.key).flatMap { try? JSONDecoder().decode([GivenPostcode].self, from: $0) } ?? []
    }

    /// The pair goes first; given before, it moves there rather than coming twice. It keeps the country sent with it,
    /// or the carrier's home where its lookup reads the country: one kept without any was kept before this iPhone kept
    /// the home country too, and its country is not known (`givenPostcodeCountry`).
    func remember(_ postcode: String, for carrier: CarrierID, country: String? = nil) {
        guard let postcode = postcode.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty else { return }
        let country = country?.nonEmpty ?? (catalog.readsPostcodeCountry(carrier) ? catalog.homeCountry(of: carrier) : nil)
        write([GivenPostcode(carrier: carrier, postcode: postcode, country: country)]
            + entries.filter { $0.carrier != carrier || Self.bare($0.postcode) != Self.bare(postcode) })
    }

    /// Forgets the postcode for every carrier it was given to, however its spaces were written.
    func forget(_ postcode: String) {
        write(entries.filter { Self.bare($0.postcode) != Self.bare(postcode) })
    }

    private func write(_ entries: [GivenPostcode]) {
        guard !entries.isEmpty else { defaults.removeObject(forKey: Self.key); return }
        defaults.set(try? JSONEncoder().encode(Array(entries.prefix(Self.limit))), forKey: Self.key)
    }

    /// The postcode with its capitals and spaces aside, as this iPhone tells two apart.
    static func bare(_ postcode: String) -> String { postcode.uppercased().filter { !$0.isWhitespace } }
}

/// Lookups are counted for each network; the day's count is spent.
struct DeviceLookupsSpent: Error, Equatable {}

/// How a parcel's link is read. On screen, the read counts as a look, and checks a parcel that is due one. With no
/// window on screen, it does neither. By its owner's hand, with the key, it also checks one not due yet, at most every
/// 2 minutes and as often as the carrier and the service allow.
enum DeviceParcelRead: Equatable, Sendable {
    case shown, hidden, byHand
}

/// How the device's parcels reach the service: no sign-in and no cookies, the link's id and
/// its owner key only.
struct DeviceParcelClient: Sendable {
    var lookup: @Sendable (PublicLookupRequest) async throws -> PublicLookupResponse
    /// Nil when the service no longer has the parcel, or no longer shares it with a reader without the key: its own 404
    /// or 410. A proxy's 404 while the server restarts throws, and the parcel stays.
    var read: @Sendable (_ linkID: String, _ key: String?, DeviceParcelRead) async throws -> PublicParcelResponse?
    var forget: @Sendable (_ linkID: String, _ key: String) async throws -> Void
    /// Moves a link of this iPhone's own to the parcel of its number with another carrier, link, postcode or phone
    /// digits, which the service finds or looks up. The link keeps all it holds. Nil when the service no longer has it.
    var changeCarrier: @Sendable (_ linkID: String, _ key: String, ParcelLinkCarrierRequest) async throws -> PublicParcelResponse? = { _, _, _ in nil }
    /// Changes what the link shows to others, or stops and resumes its sharing.
    var update: @Sendable (_ linkID: String, _ key: String, UpdateParcelLinkRequest) async throws -> PublicParcelResponse
    /// `typedNumber`: the number as typed, when it differs from the number itself. `prefixOfSaved`: the number is the
    /// start of a longer one this iPhone follows, most likely that number typed again.
    var detect: @Sendable (_ trackingNumber: String, _ typedNumber: String?, _ prefixOfSaved: Bool) async throws -> CarrierDetectionResponse
    /// What a reader says of the parcel behind a link. The owner key goes along when this iPhone holds it.
    var feedback: @Sendable (_ linkID: String, _ key: String?, ParcelFeedbackRequest) async throws -> Void = { _, _, _ in }
    /// Turns this iPhone's notifications on for the parcel behind a link, with the owner key when this iPhone holds it.
    var setAlert: @Sendable (_ linkID: String, _ key: String?, ParcelAppAlertRequest) async throws -> DeviceAlertAnswer = { _, _, _ in .unavailable }
    /// Turns them off. Anyone with the link and the token may.
    var removeAlert: @Sendable (_ linkID: String, DeleteParcelAppAlertRequest) async throws -> Void = { _, _ in }

    static func api(configuration: AppConfiguration, transport: URLSession = .shared, verification: NativeVerification = .shared) -> DeviceParcelClient {
        @Sendable func send(_ path: String, method: String, key: String? = nil, query: [URLQueryItem] = [], body: Data? = nil) async throws -> (Data, HTTPURLResponse) {
            var url = configuration.apiBaseURL.appending(path: path)
            if !query.isEmpty { url.append(queryItems: query) }
            var request = URLRequest(url: url,
                                     cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: 30)
            request.httpMethod = method
            request.httpShouldHandleCookies = false
            request.setValue("application/json", forHTTPHeaderField: "Accept")
            if let key { request.setValue(key, forHTTPHeaderField: "X-Parcel-Key") }
            if let body {
                request.setValue("application/json", forHTTPHeaderField: "Content-Type")
                request.httpBody = body
            }
            let perform: NativeVerification.Send = { request in
                let (data, response): (Data, URLResponse)
                do { (data, response) = try await transport.data(for: request) } catch { throw Maintenance.failure(error) }
                guard let response = response as? HTTPURLResponse else { throw DeliveryAPIError.invalidResponse }
                return (data, response)
            }
            // A change of carrier asks a carrier as a lookup does, with the same proof.
            let asksCarrier = (method == "POST" && path == "api/public/parcels") || (method == "PATCH" && path.hasSuffix("/carrier"))
            if asksCarrier || (method == "POST" && path == "api/public/detect") {
                return try await verification.send(request, baseURL: configuration.apiBaseURL, allowPresentation: asksCarrier, using: perform)
            }
            return try await perform(request)
        }
        @Sendable func failure(_ data: Data, _ response: HTTPURLResponse) -> Error {
            let payload = try? JSONSerialization.jsonObject(with: data) as? [String: Any]
            if response.statusCode == 429 {
                if payload?["scope"] as? String == "daily" { return DeviceLookupsSpent() }
                return DeliveryAPIError.rateLimited(response.value(forHTTPHeaderField: "Retry-After").flatMap(TimeInterval.init) ?? 60)
            }
            if response.statusCode == 400, let message = payload?["error"] as? String { return DeliveryAPIError.service(message) }
            return Maintenance.serverDown(data, response) ?? DeliveryAPIError.serviceFailed(response.statusCode)
        }
        @Sendable func decode<T: Decodable>(_ type: T.Type, _ data: Data) throws -> T {
            do { return try JSONDecoder.deliveryTracker.decode(type, from: data) }
            catch { throw DeliveryAPIError.invalidResponse }
        }
        return DeviceParcelClient(
            lookup: { request in
                let (data, response) = try await send("api/public/parcels", method: "POST", body: try JSONEncoder.deliveryTracker.encode(request))
                guard response.statusCode == 201 || response.statusCode == 200 else { throw failure(data, response) }
                return try decode(PublicLookupResponse.self, data)
            },
            read: { linkID, key, mode in
                let query: [URLQueryItem] = switch mode {
                case .shown: []
                case .hidden: [URLQueryItem(name: "hidden", value: "true")]
                // Only the owner's read asks for a check.
                case .byHand: key == nil ? [] : [URLQueryItem(name: "refresh", value: "true")]
                }
                let (data, response) = try await send("api/public/parcels/\(linkID)", method: "GET", key: key, query: query)
                if response.statusCode == 410 || response.isServiceNotFound(data) { return nil }
                guard response.statusCode == 200 else { throw failure(data, response) }
                return try decode(PublicParcelResponse.self, data)
            },
            forget: { linkID, key in
                let (data, response) = try await send("api/public/parcels/\(linkID)", method: "DELETE", key: key)
                guard response.statusCode == 204 || response.isServiceNotFound(data) else { throw failure(data, response) }
            },
            changeCarrier: { linkID, key, change in
                let (data, response) = try await send("api/public/parcels/\(linkID)/carrier", method: "PATCH", key: key, body: try JSONEncoder.deliveryTracker.encode(change))
                if response.isServiceNotFound(data) { return nil }
                guard response.statusCode == 200 else { throw failure(data, response) }
                return try decode(PublicParcelResponse.self, data)
            },
            update: { linkID, key, change in
                let (data, response) = try await send("api/public/parcels/\(linkID)", method: "PATCH", key: key, body: try JSONEncoder.deliveryTracker.encode(change))
                guard response.statusCode == 200 else { throw failure(data, response) }
                return try decode(PublicParcelResponse.self, data)
            },
            detect: { trackingNumber, typedNumber, prefixOfSaved in
                let body = try JSONEncoder.deliveryTracker.encode(CarrierDetectionRequest(
                    trackingNumber: trackingNumber, lookupCountryHint: ParcelLookupCountry.hint(), prefixOfSaved: prefixOfSaved ? true : nil,
                    typedNumber: typedNumber
                ))
                let (data, response) = try await send("api/public/detect", method: "POST", body: body)
                guard response.statusCode == 200 else { throw failure(data, response) }
                return try decode(CarrierDetectionResponse.self, data)
            },
            feedback: { linkID, key, answer in
                let (data, response) = try await send("api/public/parcels/\(linkID)/feedback", method: "POST", key: key, body: try JSONEncoder.deliveryTracker.encode(answer))
                guard response.statusCode == 204 else { throw failure(data, response) }
            },
            setAlert: { linkID, key, alert in
                let (data, response) = try await send("api/public/parcels/\(linkID)/app-alerts", method: "PUT", key: key, body: try JSONEncoder.deliveryTracker.encode(alert))
                switch response.statusCode {
                case 204: return .on
                case 404 where response.isServiceNotFound(data): return .gone
                case 409: return .full
                case 410: return .stopped
                case 503: return .unavailable
                default: throw failure(data, response)
                }
            },
            removeAlert: { linkID, alert in
                let (data, response) = try await send("api/public/parcels/\(linkID)/app-alerts", method: "DELETE", body: try JSONEncoder.deliveryTracker.encode(alert))
                guard response.statusCode == 204 else { throw failure(data, response) }
            }
        )
    }
}

/// Where the device's parcels are kept: what is known about each in a protected file, and the
/// owner keys, which are credentials, in the keychain.
protocol DeviceParcelStorage {
    func load() -> (parcels: [DeviceParcel], keys: [String: String])
    func save(_ parcels: [DeviceParcel], keys: [String: String])
    /// The links of this iPhone's own forgotten here before the service could be told, with their keys.
    func loadForgetting() -> [String: String]
    func saveForgetting(_ links: [String: String]) throws
    /// Link IDs only. Their keys remain in the active Keychain item until the queue accepts them.
    func loadUnqueuedForgetIDs() -> Set<String>
    func saveUnqueuedForgetIDs(_ ids: Set<String>) throws
}

extension DeviceParcelStorage {
    func loadForgetting() -> [String: String] { [:] }
    func saveForgetting(_ links: [String: String]) throws {}
    func loadUnqueuedForgetIDs() -> Set<String> { [] }
    func saveUnqueuedForgetIDs(_ ids: Set<String>) throws {}
}

struct DeviceParcelFiles: DeviceParcelStorage {
    private static let log = Logger(subsystem: "com.plhery.PeekDeliveryTracker", category: "DeviceParcels")
    private let keychain = KeychainStore(service: "com.plhery.PeekDeliveryTracker.deviceParcels")
    private let forgettingKeychain = KeychainStore(service: "com.plhery.PeekDeliveryTracker.deviceParcels.forgetting")

    func load() -> (parcels: [DeviceParcel], keys: [String: String]) {
        let keys: [String: String] = keychain.load() ?? [:]
        return Self.opened((try? Self.fileURL()).flatMap { try? Data(contentsOf: $0) }, keys: keys)
    }

    /// The parcels on file, with their keys. The file only shows what was last known: a row this
    /// build can't read is left out, and its key stays, for the parcel to be read again with it.
    static func opened(_ file: Data?, keys: [String: String]) -> (parcels: [DeviceParcel], keys: [String: String]) {
        guard let file else { return ([], [:]) }
        return matched(rows(file), keys: keys)
    }

    /// The parcels a file holds, row by row: one this build can't read, written by another build,
    /// leaves the others in place. A file that isn't a list holds none.
    static func rows(_ file: Data) -> [DeviceParcel] {
        guard let rows = try? JSONDecoder.deliveryTracker.decode([Row].self, from: file) else {
            log.error("The device's parcels file could not be read")
            return []
        }
        let parcels = rows.compactMap(\.parcel)
        if parcels.count < rows.count { log.error("Left out \(rows.count - parcels.count) of the device's parcels that could not be read") }
        return parcels
    }

    private struct Row: Decodable {
        let parcel: DeviceParcel?
        init(from decoder: any Decoder) throws { parcel = try? DeviceParcel(from: decoder) }
    }

    /// A parcel of this iPhone's own without its key cannot be read or forgotten. A key without its
    /// parcel is the parcel's to read again. A parcel followed through someone else's link needs no
    /// key, nor does a copy the service has forgotten, and a key beside either opens nothing.
    static func matched(_ parcels: [DeviceParcel], keys: [String: String]) -> (parcels: [DeviceParcel], keys: [String: String]) {
        let kept = parcels.filter { $0.isViewer || $0.isFrozen || keys[$0.linkID] != nil }
        let keyless = Set(kept.filter { $0.isViewer || $0.isFrozen }.map(\.linkID))
        return (kept, keys.filter { !keyless.contains($0.key) })
    }

    func save(_ parcels: [DeviceParcel], keys: [String: String]) {
        guard let url = try? Self.fileURL() else { return }
        if parcels.isEmpty, keys.isEmpty {
            keychain.delete()
            try? FileManager.default.removeItem(at: url)
            return
        }
        // The keys go first: a parcel on file always has its key.
        if keys.isEmpty { keychain.delete() } else if (try? keychain.save(keys)) == nil { return }
        guard let data = try? JSONEncoder.deliveryTracker.encode(parcels) else { return }
        try? data.write(to: url, options: [.atomic, .completeFileProtectionUntilFirstUserAuthentication])
    }

    func loadForgetting() -> [String: String] { forgettingKeychain.load() ?? [:] }

    func saveForgetting(_ links: [String: String]) throws {
        try forgettingKeychain.save(links)
    }

    func loadUnqueuedForgetIDs() -> Set<String> {
        guard let data = try? Data(contentsOf: Self.fileURL("unqueued-forgets.json")) else { return [] }
        return (try? JSONDecoder().decode(Set<String>.self, from: data)) ?? []
    }

    func saveUnqueuedForgetIDs(_ ids: Set<String>) throws {
        let url = try Self.fileURL("unqueued-forgets.json")
        try JSONEncoder().encode(ids).write(to: url, options: [.atomic, .completeFileProtectionUntilFirstUserAuthentication])
    }

    private static func fileURL(_ name: String = "parcels.json") throws -> URL {
        let manager = FileManager.default
        let directory = try manager.url(for: .applicationSupportDirectory, in: .userDomainMask, appropriateFor: nil, create: true)
            .appending(path: "DeviceParcels", directoryHint: .isDirectory)
        try manager.createDirectory(at: directory, withIntermediateDirectories: true)
        return directory.appending(path: name)
    }
}

/// The parcels someone follows before signing in. They live on this iPhone; the service keeps
/// each behind a link only this device's key owns, and forgets it some time after it arrives.
/// A parcel followed through someone else's link is read as its reader, with no key.
@MainActor
final class DeviceParcels {
    /// How long an answer is good for: a parcel on its way moves, one that arrived rests, and
    /// one its carrier has not answered about yet is asked about again at once. A held return is
    /// on its way: the server still follows it.
    static let fresh: TimeInterval = 5 * 60
    static let settledFresh: TimeInterval = 12 * 60 * 60
    static let waitingFresh: TimeInterval = 1.5
    /// How many parcels one round asks notifications for; the rest wait for the next round.
    static let alertsPerRound = 25
    /// How long a round cut short waits before it is tried again: a minute the first time, then twice as long each
    /// time, up to an hour.
    static let alertRetryFirst: TimeInterval = 60
    static let alertRetryLongest: TimeInterval = 60 * 60

    private let client: DeviceParcelClient?
    private let storage: any DeviceParcelStorage
    /// The postcodes its lookups were given, kept apart from the parcels.
    let postcodes: PostcodeMemory
    private let now: () -> Date
    private var entries: [DeviceParcel]
    /// The owner keys of this iPhone's own parcels. One without its entry is a parcel whose last answer
    /// was lost or couldn't be read: it is read again with its key, never forgotten for it.
    private var keys: [String: String]
    /// Parcels forgotten here whose notifications are still to be turned off, on file beside the others.
    private var forgotten: [DeviceParcel]
    /// Links of this iPhone's own forgotten here that the service is still to forget, with their keys.
    private var forgetting: [String: String]
    /// Links whose last answer said a check of their parcel is queued or under way. Not kept on file: a launch reads
    /// every parcel again anyway.
    private var checking: Set<String> = []
    private var savedForgetting: [String: String]
    private var unqueuedForgetIDs: Set<String>
    /// The tokens and languages rounds of notification requests are under way for.
    private var desiredAlert: String?
    private var alertGeneration = 0
    private var alertRounds: Set<Int> = []
    /// The token and language each parcel's notifications were asked for since launch, and the preset when one is
    /// chosen for every parcel (`alertKey`). Each launch asks again, in case the service lost them.
    private var alertsAsked: [String: String] = [:]
    /// The last round, when it was cut short: tried again on its token and in its language once its time comes.
    private var alertRetry: AlertRetry?
    /// What the last round asked on, which a parcel's notifications turned on again are asked for on. None once they
    /// are turned off for every parcel (`alertsOff`).
    private var alertsWanted: AlertsWanted?

    private struct AlertsWanted {
        let token: String
        let locale: NativePushLocale
        let environment: NativePushEnvironment
        let preset: ParcelAlertPreset?
    }

    private struct AlertRetry {
        let wanted: AlertsWanted
        let at: Date
        let wait: TimeInterval
    }

    /// A build without a server follows nothing.
    init(client: DeviceParcelClient?, storage: any DeviceParcelStorage = DeviceParcelFiles(),
         postcodes: PostcodeMemory = PostcodeMemory(), now: @escaping () -> Date = Date.init) {
        self.client = client
        self.storage = storage
        self.postcodes = postcodes
        self.now = now
        let (parcels, keys) = storage.load()
        savedForgetting = storage.loadForgetting()
        unqueuedForgetIDs = storage.loadUnqueuedForgetIDs()
        var forgetting = savedForgetting
        for id in unqueuedForgetIDs where forgetting[id] == nil { forgetting[id] = keys[id] }
        // A queued revocation supersedes a stale owner row. A later viewer follow can coexist with it.
        entries = parcels.filter { $0.forgotten != true && ($0.isViewer || forgetting[$0.linkID] == nil) }
        forgotten = parcels.filter { $0.forgotten == true }
        self.keys = keys.filter { forgetting[$0.key] == nil }
        self.forgetting = forgetting
    }

    var isEmpty: Bool { entries.isEmpty }
    var count: Int { entries.count }

    /// This iPhone's own parcels kept by their key alone, their saved copy lost or unreadable: listed nowhere until
    /// they are read again, and forgotten with the rest.
    var unlisted: Int { keys.keys.filter { linkID in !entries.contains { $0.linkID == linkID } }.count }

    /// The parcels as the list shows them, under the names this device gave them.
    func list() -> [Parcel] { entries.map(Self.shown) }

    /// When the service last answered about one of the parcels: how old what the list shows is at most.
    var lastCheckedAt: Date? { entries.map(\.checkedAt).max() }

    /// Whether a parcel still waits for its carrier's first answer, or for a check the service said it queued, which is
    /// worth asking about again soon. A copy no longer updated waits for nothing.
    var isWaiting: Bool { entries.contains(where: waits) }

    /// Whether a check the service said it queued, such as one its owner asked for by hand, is still to land.
    var isChecking: Bool { entries.contains { !$0.isFrozen && checking.contains($0.linkID) } }

    private func waits(_ entry: DeviceParcel) -> Bool {
        !entry.isFrozen && (Self.waiting(entry.parcel) || checking.contains(entry.linkID))
    }

    func detect(trackingNumber: String, typedNumber: String? = nil) async throws -> CarrierDetectionResponse {
        guard let client else { throw DeliveryAPIError.serviceFailed(503) }
        let prefixOfSaved = entries.contains {
            $0.parcel.trackingNumber.count > trackingNumber.count && $0.parcel.trackingNumber.hasPrefix(trackingNumber)
        }
        return try await client.detect(trackingNumber, typedNumber, prefixOfSaved)
    }

    func add(_ request: CreatePackageRequest) async throws -> Parcel {
        try await lookUp(request, replacing: nil)
    }

    /// Looks the request up. The parcel it replaces, if any, does not count as already followed.
    private func lookUp(_ request: CreatePackageRequest, replacing id: UUID?) async throws -> Parcel {
        guard let client else { throw DeliveryAPIError.serviceFailed(503) }
        if let existing = entries.first(where: {
            $0.parcel.id != id && !$0.isFrozen && $0.parcel.trackingNumber == request.trackingNumber && $0.parcel.carrier == request.carrier
        }) {
            throw DeliveryAPIError.duplicateTracking(existing.parcel.id)
        }
        // A carrier that words its scans in several languages answers the parcel in the app's.
        let response = try await client.lookup(PublicLookupRequest(
            trackingNumber: request.trackingNumber, carrier: request.carrier,
            trackingURL: request.trackingURL, dpdPostcode: request.dpdPostcode,
            dpdPostcodeCountry: request.dpdPostcodeCountry, phoneDigits: request.phoneDigits,
            lookupCountryHint: request.lookupCountryHint,
            language: AppLanguage.current.rawValue
        ))
        guard ParcelLinkRoute.validID(response.link.id) else { throw DeliveryAPIError.invalidResponse }
        var entry = DeviceParcel(linkID: response.link.id, label: request.label ?? "", archivedAt: nil,
                                 parcel: Parcel(shared: response.package), checkedAt: now(),
                                 postcodeGiven: request.dpdPostcode?.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty != nil)
        entry.startSeen()
        // A copy no longer updated of the same parcel or number gives way to the new lookup, and leaves it its name.
        let superseded = entries.filter { Self.supersedes(entry.parcel, $0, number: request.trackingNumber) }
        if !superseded.isEmpty {
            entries.removeAll { Self.supersedes(entry.parcel, $0, number: request.trackingNumber) }
            if entry.label.isEmpty, let name = superseded.lazy.compactMap(\.label.nonEmpty).first { entry.label = name }
        }
        // The service gives one parcel one link for each lookup; a second lookup of a parcel takes the first one's place.
        if let old = removeTwin(of: response.package.id, besides: entry.linkID, client) {
            // The parcel being replaced came back unchanged: only its link is new.
            if old.parcel.id == id {
                entry.label = old.label
                entry.archivedAt = old.archivedAt
                entry.replaced = old.replaced
                entry.muted = old.muted
            }
        }
        entries.append(entry)
        keys[entry.linkID] = response.key
        save()
        if let postcode = request.dpdPostcode { postcodes.remember(postcode, for: request.carrier ?? response.package.carrier, country: request.dpdPostcodeCountry) }
        // Phone digits go to the service alone: no parcel's next field starts from them, and the link never shows them.
        return Self.shown(entry)
    }

    /// Takes out the entry of the same parcel under another link: one parcel is followed here once. Its link of this
    /// iPhone's own is forgotten on the service, and its notifications are turned off.
    private func removeTwin(of parcelID: UUID, besides linkID: String, _ client: DeviceParcelClient) -> DeviceParcel? {
        guard let twin = entries.firstIndex(where: { $0.parcel.id == parcelID && $0.linkID != linkID }) else { return nil }
        let old = entries.remove(at: twin)
        let key = keys.removeValue(forKey: old.linkID)
        if let key { forgetting[old.linkID] = key }
        if key != nil || old.alertToken != nil {
            Task {
                if let token = old.alertToken { try? await client.removeAlert(old.linkID, DeleteParcelAppAlertRequest(token: token)) }
                if let key { await self.tellForgotten(old.linkID, key: key, client) }
            }
        }
        return old
    }

    /// Reads every parcel whose answer has gone stale, or every one when `all`, and brings back
    /// the ones whose last answer was lost. A lookup of this iPhone's own that the service no longer has stays as it was
    /// last shown, no longer updated (`freeze`); someone else's parcel it no longer has leaves the list, as the answer
    /// doesn't say why its link ended. A copy no longer updated is never read again. The first trouble ends the round and is thrown; the list keeps what the device has. A parcel whose
    /// answer says a check is queued or under way (`checkQueued`) is read again as soon as one waiting for its first
    /// answer (`isWaiting`). Returns whether the service answered anything, a forget included.
    @discardableResult
    func refresh(all: Bool = false, only id: UUID? = nil, read mode: DeviceParcelRead = .shown) async throws -> Bool {
        guard let client else { return false }
        var contacted = await stopForgottenAlerts(client)
        if await stopMutedAlerts(client) { contacted = true }
        if await tellForgotten(client) { contacted = true }
        var trouble: Error?
        for entry in entries where id == nil || entry.parcel.id == id {
            let key = keys[entry.linkID]
            guard !entry.isFrozen, key != nil || entry.isViewer else { continue }
            let age = now().timeIntervalSince(entry.checkedAt)
            let settled = Self.over(entry.parcel)
            let fresh = waits(entry) ? Self.waitingFresh : settled ? Self.settledFresh : Self.fresh
            guard all || id != nil || age < 0 || age >= fresh else { continue }
            do {
                // Someone else's parcel is theirs to check: it is read as on screen.
                let response = try await client.read(entry.linkID, key, key == nil && mode == .byHand ? .shown : mode)
                contacted = true
                if response?.checkQueued == true { checking.insert(entry.linkID) } else { checking.remove(entry.linkID) }
                guard let index = entries.firstIndex(where: { $0.linkID == entry.linkID }) else { continue }
                if let response {
                    entries[index].take(Parcel(shared: response.package))
                    entries[index].checkedAt = now()
                    if entry.isViewer { entries[index].canKeep = response.link.canKeep }
                } else if entries[index].isViewer {
                    entries.remove(at: index)
                    keys[entry.linkID] = nil
                } else {
                    freeze(at: index)
                }
            } catch {
                trouble = error
                break
            }
        }
        if id == nil {
            do { if try await recover(client, read: mode) { contacted = true } } catch { trouble = trouble ?? error }
        }
        save()
        if let trouble { throw trouble }
        return contacted
    }

    /// The service no longer has this iPhone's own lookup: the parcel stays as it was last shown, marked as no longer
    /// updated. Its key goes, and its notifications with it, which went with the link.
    private func freeze(at index: Int) {
        let linkID = entries[index].linkID
        keys[linkID] = nil
        alertsAsked[linkID] = nil
        entries[index].frozen = DateParser.isoString(now())
        entries[index].alertToken = nil
        entries[index].alertPreset = nil
        entries[index].muted = nil
    }

    /// Whether `copy` is a copy no longer updated that `parcel`, newly answered, takes the place of: the same parcel, or
    /// the same number.
    private static func supersedes(_ parcel: Parcel, _ copy: DeviceParcel, number: String? = nil) -> Bool {
        guard copy.isFrozen else { return false }
        if copy.parcel.id == parcel.id { return true }
        let numbers = Set([parcel.trackingNumber, number ?? ""].filter { !$0.isEmpty })
        return numbers.contains(copy.parcel.trackingNumber)
    }

    /// Reads again, with its key, each parcel of this iPhone's own whose entry was lost or couldn't
    /// be read, and puts it back on the list, without the name it had here. Only a link the service
    /// no longer has, or no longer owns with the key, lets its key go: losing the list never forgets
    /// anything on the service. The first trouble ends the round and is returned; the rest wait.
    private func recover(_ client: DeviceParcelClient, read mode: DeviceParcelRead) async throws -> Bool {
        var contacted = false
        for (linkID, key) in keys where !entries.contains(where: { $0.linkID == linkID }) {
            let response = try await client.read(linkID, key, mode)
            contacted = true
            // Forgotten, followed or let go meanwhile.
            guard keys[linkID] == key, !entries.contains(where: { $0.linkID == linkID }) else { continue }
            guard let response, response.link.id == linkID, response.link.role == .owner else {
                keys[linkID] = nil
                continue
            }
            // A copy no longer updated of the same parcel gives way to it, and leaves it its name.
            let parcel = Parcel(shared: response.package)
            let name = entries.lazy.filter { Self.supersedes(parcel, $0) }.compactMap(\.label.nonEmpty).first
            entries.removeAll { Self.supersedes(parcel, $0) }
            if let index = entries.firstIndex(where: { $0.parcel.id == response.package.id }) {
                if entries[index].isViewer {
                    upgradeViewer(at: index, with: response, key: key)
                } else {
                    keys[linkID] = nil
                }
                continue
            }
            var entry = DeviceParcel(linkID: linkID, label: name ?? "", archivedAt: nil, parcel: parcel, checkedAt: now())
            entry.startSeen()
            entries.append(entry)
        }
        return contacted
    }

    /// Tells the service what was forgotten here before it could be, without reading a parcel: signed in, the
    /// iPhone's own parcels are no longer read, and these would otherwise wait for a sign-out.
    func tellForgotten() async {
        guard let client, !forgotten.isEmpty || !forgetting.isEmpty else { return }
        await stopForgottenAlerts(client)
        await tellForgotten(client)
    }

    /// Tells the service to forget the links forgotten here before it could be, and says whether it forgot any. The
    /// first trouble ends the round; the rest wait for the next.
    @discardableResult
    private func tellForgotten(_ client: DeviceParcelClient) async -> Bool {
        var told = false
        for (linkID, key) in forgetting {
            // Followed again while the service was told of another: the link stays.
            guard forgetting[linkID] == key else { continue }
            guard await tellForgotten(linkID, key: key, client) else { return told }
            told = true
        }
        return told
    }

    /// Whether the service forgot the link, or had already. Until it has, its key stays aside.
    @discardableResult
    private func tellForgotten(_ linkID: String, key: String, _ client: DeviceParcelClient) async -> Bool {
        guard forgetting[linkID] == key, (try? await client.forget(linkID, key)) != nil else { return false }
        if forgetting[linkID] == key {
            forgetting[linkID] = nil
            save()
        }
        return true
    }

    func rename(id: UUID, label: String) throws -> Parcel {
        try update(id) { $0.label = label }
    }

    /// The parcel was opened on this iPhone: what it tells now is seen. Nothing leaves the device.
    func markSeen(id: UUID, newsAt: String) throws -> Parcel {
        try update(id) { entry in
            guard let mark = DateParser.date(newsAt), entry.openedNews.flatMap(DateParser.date).map({ $0 < mark }) ?? true else { return }
            entry.openedNews = newsAt
        }
    }

    func setArchived(id: UUID, _ archived: Bool) throws -> Parcel {
        try update(id) { $0.archivedAt = archived ? DateParser.isoString(self.now()) : nil }
    }

    /// The device forgets the parcel at once, and the service forgets the link of this iPhone's own
    /// behind it: when it can't be told yet, the key stays aside and each refresh tells it again.
    /// Someone else's parcel is forgotten here alone: its notifications on this iPhone stop, and its
    /// link goes on working for others.
    /// Returns whether the service answered.
    @discardableResult
    func forget(id: UUID) async -> Bool {
        guard let index = entries.firstIndex(where: { $0.parcel.id == id }) else { return false }
        var entry = entries.remove(at: index)
        let key = keys.removeValue(forKey: entry.linkID)
        if let key { forgetting[entry.linkID] = key }
        // Followed again, it is signed up again.
        alertsAsked[entry.linkID] = nil
        // A forgotten link of this iPhone's own takes its notifications with it; someone else's goes
        // on, and stays on file until its notifications here are turned off.
        let token = client == nil ? nil : entry.alertToken
        if token != nil, entry.isViewer {
            entry.forgotten = true
            forgotten.append(entry)
        }
        save()
        guard let client else { return false }
        var answered = false
        if let token, (try? await client.removeAlert(entry.linkID, DeleteParcelAppAlertRequest(token: token))) != nil {
            answered = true
            if entry.isViewer {
                forgotten.removeAll { $0.linkID == entry.linkID && $0.alertToken == token }
                save()
            }
        }
        if let key, await tellForgotten(entry.linkID, key: key, client) { answered = true }
        return answered
    }

    /// Turns off the notifications of parcels forgotten while the service couldn't be told. The
    /// first trouble ends the round; the rest wait for the next.
    /// Returns whether the service answered.
    @discardableResult
    private func stopForgottenAlerts(_ client: DeviceParcelClient) async -> Bool {
        var answered = false
        for entry in forgotten {
            if let token = entry.alertToken {
                guard (try? await client.removeAlert(entry.linkID, DeleteParcelAppAlertRequest(token: token))) != nil else { return answered }
                answered = true
            }
            forgotten.removeAll { $0.linkID == entry.linkID && $0.alertToken == entry.alertToken }
            save()
        }
        return answered
    }

    /// Forgets every parcel, with those still to be read again. Their keys are set aside before the service is asked
    /// anything: a refresh running meanwhile no longer reads them again, and a read already under way lands for a key
    /// that has gone and is dropped (`recover`). The links the service could not be told about yet are forgotten on a
    /// later refresh.
    /// Returns whether the service answered.
    @discardableResult
    func forgetAll() async -> Bool {
        let unread = keys.filter { linkID, _ in !entries.contains { $0.linkID == linkID } }
        if !unread.isEmpty {
            forgetting.merge(unread) { _, key in key }
            for linkID in unread.keys { keys[linkID] = nil }
            save()
        }
        var answered = false
        for entry in entries where await forget(id: entry.parcel.id) { answered = true }
        if !unread.isEmpty, let client, await tellForgotten(client) { answered = true }
        return answered
    }

    /// Follows the same number with another carrier, link, postcode or phone digits, on the same link: it keeps its key,
    /// name, archive, sharing and notifications. The parcel behind it can be another lookup of the number, under another
    /// id, which an open page follows (`replaced`). A link the service no longer has is looked up again: the new lookup
    /// takes the old one's place and name.
    func changeCarrier(id: UUID, carrier: CarrierID, trackingURL: String?, dpdPostcode: String?,
                       dpdPostcodeCountry: String? = nil, phoneDigits: String? = nil) async throws -> Parcel {
        guard let client else { throw DeliveryAPIError.serviceFailed(503) }
        // Someone else's parcel is followed as its link shows it, and its carrier stays theirs to change. A copy no
        // longer updated has nothing left to change.
        guard let entry = entries.first(where: { $0.parcel.id == id }), !entry.isViewer, !entry.isFrozen,
              let key = keys[entry.linkID] else { throw DeliveryAPIError.parcelMissing }
        let number = entry.parcel.trackingNumber
        if let existing = entries.first(where: {
            $0.parcel.id != id && !$0.isFrozen && $0.parcel.trackingNumber == number && $0.parcel.carrier == carrier
        }) {
            throw DeliveryAPIError.duplicateTracking(existing.parcel.id)
        }
        let response = try await client.changeCarrier(entry.linkID, key, ParcelLinkCarrierRequest(
            carrier: carrier, trackingURL: trackingURL, dpdPostcode: dpdPostcode, dpdPostcodeCountry: dpdPostcodeCountry,
            phoneDigits: phoneDigits, lookupCountryHint: ParcelLookupCountry.hint(), language: AppLanguage.current.rawValue
        ))
        guard let response else {
            return try await lookUpAgain(entry, carrier: carrier, trackingURL: trackingURL, dpdPostcode: dpdPostcode,
                                         dpdPostcodeCountry: dpdPostcodeCountry, phoneDigits: phoneDigits)
        }
        guard response.link.id == entry.linkID else { throw DeliveryAPIError.invalidResponse }
        // Forgotten here meanwhile: the link stays forgotten.
        guard keys[entry.linkID] == key, let index = entries.firstIndex(where: { $0.linkID == entry.linkID }) else {
            throw DeliveryAPIError.parcelMissing
        }
        let parcel = Parcel(shared: response.package)
        if parcel.id == id {
            entries[index].take(parcel)
        } else {
            // Another lookup of the number: what it tells so far is no news, as with a new lookup.
            entries[index].parcel = parcel
            entries[index].startSeen()
            entries[index].replaced = entry.replaced ?? id
        }
        entries[index].checkedAt = now()
        entries[index].postcodeGiven = dpdPostcode?.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty != nil
        // A copy no longer updated of the same parcel or number gives way, and leaves its name to a parcel without one.
        let superseded = entries.filter { Self.supersedes(parcel, $0, number: number) }
        entries.removeAll { Self.supersedes(parcel, $0, number: number) }
        _ = removeTwin(of: parcel.id, besides: entry.linkID, client)
        guard let changed = entries.firstIndex(where: { $0.linkID == entry.linkID }) else { throw DeliveryAPIError.parcelMissing }
        if entries[changed].label.isEmpty, let name = superseded.lazy.compactMap(\.label.nonEmpty).first { entries[changed].label = name }
        save()
        if let dpdPostcode { postcodes.remember(dpdPostcode, for: carrier, country: dpdPostcodeCountry) }
        return Self.shown(entries[changed])
    }

    /// A new lookup in place of a link the service no longer has, which takes its place and name.
    private func lookUpAgain(_ entry: DeviceParcel, carrier: CarrierID, trackingURL: String?, dpdPostcode: String?,
                             dpdPostcodeCountry: String?, phoneDigits: String?) async throws -> Parcel {
        let id = entry.parcel.id
        let added = try await lookUp(CreatePackageRequest(
            trackingNumber: entry.parcel.trackingNumber, label: entry.label, carrier: carrier,
            trackingURL: trackingURL, dpdPostcode: dpdPostcode, dpdPostcodeCountry: dpdPostcodeCountry,
            phoneDigits: phoneDigits, lookupCountryHint: ParcelLookupCountry.hint()
        ), replacing: id)
        if added.id != id {
            await forget(id: id)
            if let index = entries.firstIndex(where: { $0.parcel.id == added.id }) {
                entries[index].replaced = entry.replaced ?? id
                entries[index].muted = entry.muted
                save()
                return Self.shown(entries[index])
            }
        }
        return added
    }

    /// The parcel's link as others may open it, or nil once its sharing was stopped.
    func share(id: UUID) async throws -> ParcelShare? {
        guard let client, let entry = entries.first(where: { $0.parcel.id == id }), let key = keys[entry.linkID] else { throw DeliveryAPIError.parcelMissing }
        guard let response = try await client.read(entry.linkID, key, .shown) else { throw DeliveryAPIError.parcelMissing }
        return Self.share(response.link)
    }

    /// Changes what the link shows, resuming its sharing if it was stopped; nil stops it.
    func setShare(id: UUID, showNumber: Bool, gift: Bool, shared: Bool = true, giftWords: GiftWords? = nil) async throws -> ParcelShare? {
        guard let client, let entry = entries.first(where: { $0.parcel.id == id }), let key = keys[entry.linkID] else { throw DeliveryAPIError.parcelMissing }
        let change = shared ? UpdateParcelLinkRequest(showNumber: showNumber, gift: gift, shared: true, giftWords: giftWords) : UpdateParcelLinkRequest(shared: false)
        return Self.share(try await client.update(entry.linkID, key, change).link)
    }

    /// What its reader says of a parcel this iPhone follows.
    func sendFeedback(_ feedback: ParcelFeedbackRequest, id: UUID) async throws {
        guard let entry = entries.first(where: { $0.parcel.id == id }) else { throw DeliveryAPIError.parcelMissing }
        try await sendFeedback(feedback, linkID: entry.linkID)
    }

    /// The same of a parcel behind any link: someone else's goes without a key.
    func sendFeedback(_ feedback: ParcelFeedbackRequest, linkID: String) async throws {
        guard let client else { throw DeliveryAPIError.serviceFailed(503) }
        try await client.feedback(linkID, keys[linkID], feedback)
    }

    private static func share(_ link: ParcelLink) -> ParcelShare? {
        guard link.shared != false else { return nil }
        return ParcelShare(id: link.id, showNumber: link.showNumber ?? false, gift: link.gift ?? false, createdAt: link.createdAt, giftWords: link.giftWords)
    }

    /// Whether any of the device's parcels is archived, for the account that takes them over.
    func isArchived(linkID: String) -> Bool { entries.first { $0.linkID == linkID }?.archivedAt != nil }

    /// What an account needs to take the device's parcels over: its own with their keys, those whose
    /// saved copy was lost included, and someone else's that the link lets an account keep. Never a
    /// link still to be forgotten, nor a copy no longer updated: neither has a key.
    var claims: [ClaimParcelLink] {
        let listed = entries.compactMap { entry -> ClaimParcelLink? in
            if entry.isViewer { return entry.canKeep == true ? ClaimParcelLink(id: entry.linkID, label: entry.label.nonEmpty) : nil }
            return keys[entry.linkID].map { ClaimParcelLink(id: entry.linkID, key: $0, label: entry.label.nonEmpty) }
        }
        let unlisted = keys.filter { pair in !entries.contains { $0.linkID == pair.key } }
            .sorted { $0.key < $1.key }
            .map { ClaimParcelLink(id: $0.key, key: $0.value) }
        return listed + unlisted
    }

    /// Whether the parcel behind this link is someone else's, followed here as its reader.
    func isViewer(linkID: String) -> Bool { entries.first { $0.linkID == linkID }?.isViewer == true }

    /// The link of a parcel followed here through someone else's link, which is all this iPhone may share of it.
    func viewerLink(id: UUID) -> String? { entries.first { $0.parcel.id == id && $0.isViewer }?.linkID }

    /// Whether a postcode went with the lookup that made the parcel; nil when this iPhone doesn't know.
    func postcodeGiven(id: UUID) -> Bool? { entries.first { $0.parcel.id == id }?.postcodeGiven }

    /// The parcel this iPhone follows through a link, as the list shows it.
    func parcelID(linkID: String) -> UUID? { entries.first { $0.linkID == linkID }?.parcel.id }

    /// Whether the parcel is a copy the service has forgotten, no longer updated.
    func isFrozen(id: UUID) -> Bool { entries.first { $0.parcel.id == id }?.isFrozen == true }

    /// The account has these parcels now, or their links lead nowhere: the device lets go of them,
    /// and of their notifications here, which the account's own take over.
    func release(_ linkIDs: Set<String>) {
        let alerts = entries.compactMap { entry in
            linkIDs.contains(entry.linkID) ? entry.alertToken.map { (entry.linkID, $0) } : nil
        }
        entries.removeAll { linkIDs.contains($0.linkID) }
        for id in linkIDs { keys[id] = nil }
        save()
        if let client, !alerts.isEmpty {
            Task {
                for (linkID, token) in alerts { try? await client.removeAlert(linkID, DeleteParcelAppAlertRequest(token: token)) }
            }
        }
    }

    // MARK: - Following a link

    /// Keeps the parcel behind a link on this iPhone. With the link's owner key, handed over by
    /// the site, the parcel is this iPhone's own, as a lookup made here would be; without it, it is
    /// followed as its reader. A parcel here already stays as it is, unless the key makes
    /// someone else's parcel this iPhone's own. `added` is false when it was here already.
    func follow(_ response: PublicParcelResponse, key: String?, label: String?) throws -> (id: UUID, added: Bool) {
        let linkID = response.link.id
        guard ParcelLinkRoute.validID(linkID) else { throw DeliveryAPIError.invalidResponse }
        let key = key.flatMap { response.link.role == .owner && ParcelLinkRoute.validKey($0) ? $0 : nil }
        // A copy no longer updated of the same parcel or number gives way to the link, and leaves it its name.
        let shared = Parcel(shared: response.package)
        let superseded = entries.filter { $0.linkID == linkID && $0.isFrozen || Self.supersedes(shared, $0) }
        entries.removeAll { $0.linkID == linkID && $0.isFrozen || Self.supersedes(shared, $0) }
        let label = label?.nonEmpty ?? superseded.lazy.compactMap(\.label.nonEmpty).first
        if let index = entries.firstIndex(where: { $0.linkID == linkID || $0.parcel.id == response.package.id }) {
            guard let key, entries[index].isViewer else {
                if !superseded.isEmpty { save() }
                return (entries[index].parcel.id, false)
            }
            upgradeViewer(at: index, with: response, key: key)
            save()
            return (entries[index].parcel.id, false)
        }
        var entry = DeviceParcel(linkID: linkID, label: label ?? "", archivedAt: nil,
                                 parcel: shared, checkedAt: now())
        entry.startSeen()
        // Followed again before its notifications were turned off: they go on, and are its own again.
        if let left = forgotten.first(where: { $0.linkID == linkID }) {
            entry.alertToken = left.alertToken
            entry.alertPreset = left.alertPreset
            forgotten.removeAll { $0.linkID == linkID }
        }
        if let key {
            forgetting[linkID] = nil
            keys[linkID] = key
        } else {
            entry.viewer = true
            entry.canKeep = response.link.canKeep
        }
        entries.append(entry)
        save()
        return (entry.parcel.id, true)
    }

    private func upgradeViewer(at index: Int, with response: PublicParcelResponse, key: String) {
        let linkID = response.link.id
        let old = entries[index]
        entries[index].linkID = linkID
        entries[index].viewer = nil
        entries[index].canKeep = nil
        entries[index].take(Parcel(shared: response.package))
        entries[index].checkedAt = now()
        if old.linkID != linkID {
            // Another link's notifications give way to this one's, asked for on the next round.
            entries[index].alertToken = nil
            if let client, let token = old.alertToken {
                Task { try? await client.removeAlert(old.linkID, DeleteParcelAppAlertRequest(token: token)) }
            }
        }
        forgetting[linkID] = nil
        keys[linkID] = key
    }

    // MARK: - Notifications

    /// What a parcel's notifications tell when they are first turned on, as on the site: every scan
    /// while nothing has moved yet, the steps that matter after that.
    static func alertPreset(for parcel: Parcel) -> ParcelAlertPreset {
        !parcel.hasCarrierUpdate || parcel.currentStage == .registered ? .all : .important
    }

    /// Whether a parcel's journey is over: it arrived or went back, unless its carrier still scans it on its way back
    /// (`heldReturn`), which the server keeps checking and alerting on.
    static func over(_ parcel: Parcel) -> Bool { parcel.currentStage?.isFinal == true && !parcel.heldReturn }

    /// Whether a parcel is still on its way, with something left to tell: a copy no longer updated has nothing.
    var hasParcelsOnTheirWay: Bool { entries.contains { !$0.isFrozen && !Self.over($0.parcel) } }

    /// Turns notifications on, on this iPhone's token and in its language, for every parcel still
    /// on its way: once a launch, and again when the token or the language changes. A parcel that
    /// arrived needs none; a held return still does. A round is short and quiet: a few parcels at most, and it ends at the
    /// first sign that the service wants a pause, sends no notifications or can't be reached; `retryAlerts` tries it
    /// again later. A link that leads nowhere, has no room or is no longer shared is left until the next launch.
    ///
    /// Each parcel's notifications tell what `preset` says, the one chosen for every parcel in Settings: a new one asks
    /// again for those already on, and the service replaces what they told. Until one is chosen (nil), a parcel keeps
    /// what it was first asked for, or is given `alertPreset(for:)`'s. A parcel turned off with its bell is passed over,
    /// and so is a copy no longer updated.
    func registerAlerts(token: String, locale: NativePushLocale, environment: NativePushEnvironment = .current,
                        preset chosen: ParcelAlertPreset? = nil) async {
        let wanted = AlertsWanted(token: token, locale: locale, environment: environment, preset: chosen)
        let asked = Self.alertKey(wanted)
        alertsWanted = wanted
        if desiredAlert != asked {
            desiredAlert = asked
            alertGeneration += 1
            alertRetry = nil
        }
        let round = alertGeneration
        guard let client, alertRounds.insert(round).inserted else { return }
        defer { alertRounds.remove(round) }
        // How long the service asked to be left alone, when the round is cut short.
        var pause: TimeInterval?
        defer { if round == alertGeneration { holdAlerts(pause, wanted) } }
        let due = entries.filter { alertsAsked[$0.linkID] != asked && !Self.over($0.parcel) && !$0.isMuted && !$0.isFrozen }
            .prefix(Self.alertsPerRound)
        for entry in due {
            guard round == alertGeneration else { return }
            // Forgotten, followed through another link, turned off with its bell or forgotten by the service since the
            // round began.
            guard entries.contains(where: { $0.linkID == entry.linkID && !$0.isMuted && !$0.isFrozen }) else { continue }
            let preset = chosen ?? entry.alertPreset ?? Self.alertPreset(for: entry.parcel)
            let answer: DeviceAlertAnswer
            do {
                answer = try await client.setAlert(entry.linkID, keys[entry.linkID],
                                                   ParcelAppAlertRequest(token: token, environment: environment, preset: preset, locale: locale))
            } catch {
                if case DeliveryAPIError.rateLimited(let after) = error { pause = after } else { pause = 0 }
                break
            }
            guard round == alertGeneration else {
                // Turned off for every parcel while the service was asked: this one hears nothing after all.
                if desiredAlert == nil, answer == .on { try? await client.removeAlert(entry.linkID, DeleteParcelAppAlertRequest(token: token)) }
                return
            }
            if answer == .unavailable {
                pause = 0
                break
            }
            guard let index = entries.firstIndex(where: { $0.linkID == entry.linkID }) else {
                // Forgotten, or followed through another link, while the service was asked: the link hears
                // nothing more here.
                if answer == .on { await stopAlert(entry, token: token, client) }
                continue
            }
            if entries[index].isMuted || entries[index].isFrozen {
                // Turned off with its bell, or forgotten by the service, while the service was asked.
                if answer == .on { try? await client.removeAlert(entry.linkID, DeleteParcelAppAlertRequest(token: token)) }
                continue
            }
            alertsAsked[entry.linkID] = asked
            guard answer == .on else { continue }
            let old = entries[index].alertToken
            entries[index].alertToken = token
            entries[index].alertPreset = preset
            save()
            // The token changed: the old one hears nothing more of this parcel.
            if let old, old != token {
                try? await client.removeAlert(entry.linkID, DeleteParcelAppAlertRequest(token: old))
            }
        }
    }

    /// What `alertsAsked` notes for a link asked for on the token, in the language and with the preset when one is
    /// chosen for every parcel: a new choice asks every link again.
    private static func alertKey(_ wanted: AlertsWanted) -> String {
        "\(wanted.token) \(wanted.locale.rawValue) \(wanted.environment.rawValue)" + (wanted.preset.map { " \($0.rawValue)" } ?? "")
    }

    /// When the last round, cut short, is to be tried again; nil once a round went through.
    var alertsRetryAt: Date? { alertRetry?.at }

    /// Tries the last round again when it was cut short and its time has come: an outage or a pause on the service's
    /// side leaves no parcel without notifications until the next launch. The list calls it as it refreshes.
    func retryAlerts() async {
        guard let retry = alertRetry, now() >= retry.at else { return }
        let wanted = retry.wanted
        await registerAlerts(token: wanted.token, locale: wanted.locale, environment: wanted.environment, preset: wanted.preset)
    }

    /// Asks again, on the last round's token and in its language, for the notifications still missing, with `preset`
    /// for every parcel: a new preset chosen in Settings asks again for every parcel. Nothing before a first round, which
    /// takes the preset when the token comes.
    func alertsAgain(preset: ParcelAlertPreset?) async {
        guard let wanted = alertsWanted else { return }
        await registerAlerts(token: wanted.token, locale: wanted.locale, environment: wanted.environment, preset: preset)
    }

    /// Notes how a round ended: one cut short is tried again after a wait, longer each time it fails again; one that
    /// went through leaves nothing to try again.
    private func holdAlerts(_ pause: TimeInterval?, _ wanted: AlertsWanted) {
        guard let pause else {
            alertRetry = nil
            return
        }
        var wait = Self.alertRetryFirst
        if let last = alertRetry, last.wanted.token == wanted.token, last.wanted.locale == wanted.locale {
            wait = min(last.wait * 2, Self.alertRetryLongest)
        }
        alertRetry = AlertRetry(wanted: wanted, at: now().addingTimeInterval(max(wait, pause)), wait: wait)
    }

    /// Whether a parcel here still has notifications on, which `alertsOff` turns off.
    var hasAlertsOn: Bool { entries.contains { $0.alertToken != nil } }

    /// Turns every parcel's notifications on this iPhone off, from the switch in Settings: the service is told about
    /// each link signed up here, and no round asks for any until `registerAlerts` is called again; a round under way
    /// stops, and a link it signs up meanwhile is told too. A parcel turned off with its bell stays so.
    ///
    /// A link the service can't be told about yet keeps its token, and the next call tells it again; the first failure
    /// ends the call, as the service may want a pause.
    func alertsOff() async {
        desiredAlert = nil
        alertGeneration += 1
        alertRetry = nil
        alertsWanted = nil
        alertsAsked = [:]
        guard let client else { return }
        for entry in entries {
            guard let token = entry.alertToken else { continue }
            guard (try? await client.removeAlert(entry.linkID, DeleteParcelAppAlertRequest(token: token))) != nil else { return }
            // Turned on again meanwhile: the new round asks for every link, and replaces the token it finds.
            guard desiredAlert == nil else { return }
            guard let index = entries.firstIndex(where: { $0.linkID == entry.linkID }), entries[index].alertToken == token else { continue }
            entries[index].alertToken = nil
            save()
        }
    }

    /// Turns one parcel's notifications on this iPhone off, or back on, from the bell on its page. Off, the service is
    /// told at once, or by the next refresh when it can't be reached; on, the parcel is asked for again on the last
    /// round's token, or by the next round when there was none.
    func setMuted(id: UUID, muted: Bool) async throws -> Parcel {
        let shown = try update(id) { $0.muted = muted ? true : nil }
        guard let linkID = entries.first(where: { $0.parcel.id == id })?.linkID else { return shown }
        alertsAsked[linkID] = nil
        if muted {
            if let client { await stopMutedAlerts(client) }
        } else if let wanted = alertsWanted {
            // A round under way listed its parcels before this one was back on: a new round asks for it.
            alertGeneration += 1
            await registerAlerts(token: wanted.token, locale: wanted.locale, environment: wanted.environment, preset: wanted.preset)
        }
        return shown
    }

    /// Turns off the notifications of parcels turned off with their bell while the service couldn't be told. The first
    /// trouble ends the round; the rest wait for the next.
    /// Returns whether the service answered.
    @discardableResult
    private func stopMutedAlerts(_ client: DeviceParcelClient) async -> Bool {
        var answered = false
        for entry in entries where entry.isMuted {
            guard let token = entry.alertToken else { continue }
            guard (try? await client.removeAlert(entry.linkID, DeleteParcelAppAlertRequest(token: token))) != nil else { return answered }
            answered = true
            guard let index = entries.firstIndex(where: { $0.linkID == entry.linkID }), entries[index].isMuted,
                  entries[index].alertToken == token else { continue }
            entries[index].alertToken = nil
            save()
        }
        return answered
    }

    /// Turns off a link's notifications on the token, for a link no longer followed here. Someone else's link the
    /// service can't be told about yet waits with the parcels forgotten here, and each refresh tells it again; a link
    /// of this iPhone's own takes its notifications with it when the service forgets it.
    private func stopAlert(_ entry: DeviceParcel, token: String, _ client: DeviceParcelClient) async {
        guard (try? await client.removeAlert(entry.linkID, DeleteParcelAppAlertRequest(token: token))) == nil, entry.isViewer,
              !entries.contains(where: { $0.linkID == entry.linkID }) else { return }
        var left = entry
        left.alertToken = token
        left.forgotten = true
        forgotten.removeAll { $0.linkID == entry.linkID && $0.alertToken == token }
        forgotten.append(left)
        save()
    }

    private func update(_ id: UUID, _ change: (inout DeviceParcel) -> Void) throws -> Parcel {
        guard let index = entries.firstIndex(where: { $0.parcel.id == id }) else { throw DeliveryAPIError.parcelMissing }
        change(&entries[index])
        save()
        return Self.shown(entries[index])
    }

    private func save() {
        // The links being forgotten go first: their keys are never read again as parcels to bring back.
        if forgetting != savedForgetting {
            do {
                try storage.saveForgetting(forgetting)
                savedForgetting = forgetting
            } catch {
                // Keep the active keys intact and remember which to revoke if the app stops before retrying.
                let ids = Set(forgetting.keys).subtracting(savedForgetting.keys)
                if ids != unqueuedForgetIDs, (try? storage.saveUnqueuedForgetIDs(ids)) != nil {
                    unqueuedForgetIDs = ids
                }
                return
            }
        }
        if !unqueuedForgetIDs.isEmpty {
            guard (try? storage.saveUnqueuedForgetIDs([])) != nil else { return }
            unqueuedForgetIDs = []
        }
        storage.save(entries + forgotten, keys: keys)
    }

    private static func waiting(_ parcel: Parcel) -> Bool { parcel.syncStatus == .pending || parcel.syncStatus == .syncing }

    private static func shown(_ entry: DeviceParcel) -> Parcel {
        var parcel = entry.parcel
        parcel.label = entry.label
        parcel.archivedAt = entry.archivedAt
        // Without an account, the device keeps what was seen: the news it had when the parcel was last opened here.
        parcel.seenAt = entry.openedNews ?? parcel.newsAt
        // Its bell shows whether it was turned off here.
        parcel.notificationsMuted = entry.isMuted
        if let replaced = entry.replaced {
            var data = parcel.carrierData ?? CarrierData()
            data.originalPackageID = replaced
            parcel.carrierData = data
        }
        return parcel
    }
}
