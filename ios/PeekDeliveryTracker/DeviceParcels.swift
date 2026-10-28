import Foundation

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
    /// What its notifications on this iPhone tell, chosen when they were first turned on.
    var alertPreset: ParcelAlertPreset? = nil
    /// The device token the service last answered about for this parcel's notifications.
    var alertToken: String? = nil

    var isViewer: Bool { viewer == true }
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
    /// The server sends no notifications to iPhones.
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

    init(defaults: UserDefaults = .standard) {
        self.defaults = defaults
    }

    var entries: [GivenPostcode] {
        defaults.data(forKey: Self.key).flatMap { try? JSONDecoder().decode([GivenPostcode].self, from: $0) } ?? []
    }

    /// The pair goes first; given before, it moves there rather than coming twice.
    func remember(_ postcode: String, for carrier: CarrierID) {
        guard let postcode = postcode.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty else { return }
        write([GivenPostcode(carrier: carrier, postcode: postcode)]
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

    private static func bare(_ postcode: String) -> String { postcode.uppercased().filter { !$0.isWhitespace } }
}

/// Lookups are counted for each network; the day's count is spent.
struct DeviceLookupsSpent: Error, Equatable {}

/// How the device's parcels reach the service: no sign-in and no cookies, the link's id and
/// its owner key only.
struct DeviceParcelClient: Sendable {
    var lookup: @Sendable (PublicLookupRequest) async throws -> PublicLookupResponse
    /// Nil when the service no longer has the parcel, or no longer shares it with a reader without the key.
    var read: @Sendable (_ linkID: String, _ key: String?) async throws -> PublicParcelResponse?
    var forget: @Sendable (_ linkID: String, _ key: String) async throws -> Void
    /// Changes what the link shows to others, or stops and resumes its sharing.
    var update: @Sendable (_ linkID: String, _ key: String, UpdateParcelLinkRequest) async throws -> PublicParcelResponse
    /// `prefixOfSaved`: the number is the start of a longer one this iPhone follows, most likely that number typed again.
    var detect: @Sendable (_ trackingNumber: String, _ prefixOfSaved: Bool) async throws -> CarrierDetectionResponse
    /// What a reader says of the parcel behind a link. The owner key goes along when this iPhone holds it.
    var feedback: @Sendable (_ linkID: String, _ key: String?, ParcelFeedbackRequest) async throws -> Void = { _, _, _ in }
    /// Turns this iPhone's notifications on for the parcel behind a link, with the owner key when this iPhone holds it.
    var setAlert: @Sendable (_ linkID: String, _ key: String?, ParcelAppAlertRequest) async throws -> DeviceAlertAnswer = { _, _, _ in .unavailable }
    /// Turns them off. Anyone with the link and the token may.
    var removeAlert: @Sendable (_ linkID: String, DeleteParcelAppAlertRequest) async throws -> Void = { _, _ in }

    static func api(configuration: AppConfiguration, transport: URLSession = .shared, verification: NativeVerification = .shared) -> DeviceParcelClient {
        @Sendable func send(_ path: String, method: String, key: String? = nil, body: Data? = nil) async throws -> (Data, HTTPURLResponse) {
            var request = URLRequest(url: configuration.apiBaseURL.appending(path: path),
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
                let (data, response) = try await transport.data(for: request)
                guard let response = response as? HTTPURLResponse else { throw DeliveryAPIError.invalidResponse }
                return (data, response)
            }
            if method == "POST", path == "api/public/parcels" || path == "api/public/detect" {
                return try await verification.send(request, baseURL: configuration.apiBaseURL, allowPresentation: path == "api/public/parcels", using: perform)
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
            return DeliveryAPIError.serviceFailed(response.statusCode)
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
            read: { linkID, key in
                let (data, response) = try await send("api/public/parcels/\(linkID)", method: "GET", key: key)
                if response.statusCode == 404 || response.statusCode == 410 { return nil }
                guard response.statusCode == 200 else { throw failure(data, response) }
                return try decode(PublicParcelResponse.self, data)
            },
            forget: { linkID, key in
                let (data, response) = try await send("api/public/parcels/\(linkID)", method: "DELETE", key: key)
                guard response.statusCode == 204 || response.statusCode == 404 else { throw failure(data, response) }
            },
            update: { linkID, key, change in
                let (data, response) = try await send("api/public/parcels/\(linkID)", method: "PATCH", key: key, body: try JSONEncoder.deliveryTracker.encode(change))
                guard response.statusCode == 200 else { throw failure(data, response) }
                return try decode(PublicParcelResponse.self, data)
            },
            detect: { trackingNumber, prefixOfSaved in
                let body = try JSONEncoder.deliveryTracker.encode(CarrierDetectionRequest(
                    trackingNumber: trackingNumber, lookupCountryHint: ParcelLookupCountry.hint(), prefixOfSaved: prefixOfSaved ? true : nil
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
                case 404: return .gone
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
}

struct DeviceParcelFiles: DeviceParcelStorage {
    private let keychain = KeychainStore(service: "com.plhery.PeekDeliveryTracker.deviceParcels")

    func load() -> (parcels: [DeviceParcel], keys: [String: String]) {
        let keys: [String: String] = keychain.load() ?? [:]
        return Self.opened((try? Self.fileURL()).flatMap { try? Data(contentsOf: $0) }, keys: keys)
    }

    /// The parcels on file, with their keys. A file this build can't read keeps every key, so
    /// the service can be asked to forget their links.
    static func opened(_ file: Data?, keys: [String: String]) -> (parcels: [DeviceParcel], keys: [String: String]) {
        guard let file else { return ([], [:]) }
        guard let parcels = try? JSONDecoder.deliveryTracker.decode([DeviceParcel].self, from: file) else { return ([], keys) }
        return matched(parcels, keys: keys)
    }

    /// A parcel of this iPhone's own without its key cannot be read or forgotten, and a key without
    /// its parcel opens nothing. A parcel followed through someone else's link needs no key.
    static func matched(_ parcels: [DeviceParcel], keys: [String: String]) -> (parcels: [DeviceParcel], keys: [String: String]) {
        let kept = parcels.filter { $0.isViewer || keys[$0.linkID] != nil }
        return (kept, keys.filter { key, _ in kept.contains { $0.linkID == key && !$0.isViewer } })
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
        // Keys alone are those of a file that couldn't be read, which stays until their links are forgotten.
        guard !parcels.isEmpty, let data = try? JSONEncoder.deliveryTracker.encode(parcels) else { return }
        try? data.write(to: url, options: [.atomic, .completeFileProtectionUntilFirstUserAuthentication])
    }

    private static func fileURL() throws -> URL {
        let manager = FileManager.default
        let directory = try manager.url(for: .applicationSupportDirectory, in: .userDomainMask, appropriateFor: nil, create: true)
            .appending(path: "DeviceParcels", directoryHint: .isDirectory)
        try manager.createDirectory(at: directory, withIntermediateDirectories: true)
        return directory.appending(path: "parcels.json")
    }
}

/// The parcels someone follows before signing in. They live on this iPhone; the service keeps
/// each behind a link only this device's key owns, and forgets it some time after it arrives.
/// A parcel followed through someone else's link is read as its reader, with no key.
@MainActor
final class DeviceParcels {
    /// How long an answer is good for: a parcel on its way moves, one that arrived rests, and
    /// one its carrier has not answered about yet is asked about again at once.
    static let fresh: TimeInterval = 5 * 60
    static let settledFresh: TimeInterval = 12 * 60 * 60
    static let waitingFresh: TimeInterval = 1.5
    /// How many parcels one round asks notifications for; the rest wait for the next round.
    static let alertsPerRound = 25

    private let client: DeviceParcelClient?
    private let storage: any DeviceParcelStorage
    /// The postcodes its lookups were given, kept apart from the parcels.
    let postcodes: PostcodeMemory
    private let now: () -> Date
    private var entries: [DeviceParcel]
    private var keys: [String: String]
    /// The tokens and languages rounds of notification requests are under way for.
    private var alertRounds: Set<String> = []
    /// The token and language each parcel's notifications were asked for since launch. Each
    /// launch asks again, in case the service lost them.
    private var alertsAsked: [String: String] = [:]

    /// A build without a server follows nothing.
    init(client: DeviceParcelClient?, storage: any DeviceParcelStorage = DeviceParcelFiles(),
         postcodes: PostcodeMemory = PostcodeMemory(), now: @escaping () -> Date = Date.init) {
        self.client = client
        self.storage = storage
        self.postcodes = postcodes
        self.now = now
        (entries, keys) = storage.load()
    }

    var isEmpty: Bool { entries.isEmpty }

    /// The parcels as the list shows them, under the names this device gave them.
    func list() -> [Parcel] { entries.map(Self.shown) }

    /// Whether a parcel still waits for its carrier's first answer, which is worth asking about again soon.
    var isWaiting: Bool { entries.contains { Self.waiting($0.parcel) } }

    func detect(trackingNumber: String) async throws -> CarrierDetectionResponse {
        guard let client else { throw DeliveryAPIError.serviceFailed(503) }
        let prefixOfSaved = entries.contains {
            $0.parcel.trackingNumber.count > trackingNumber.count && $0.parcel.trackingNumber.hasPrefix(trackingNumber)
        }
        return try await client.detect(trackingNumber, prefixOfSaved)
    }

    func add(_ request: CreatePackageRequest) async throws -> Parcel {
        try await lookUp(request, replacing: nil)
    }

    /// Looks the request up. The parcel it replaces, if any, does not count as already followed.
    private func lookUp(_ request: CreatePackageRequest, replacing id: UUID?) async throws -> Parcel {
        guard let client else { throw DeliveryAPIError.serviceFailed(503) }
        if let existing = entries.first(where: {
            $0.parcel.id != id && $0.parcel.trackingNumber == request.trackingNumber && $0.parcel.carrier == request.carrier
        }) {
            throw DeliveryAPIError.duplicateTracking(existing.parcel.id)
        }
        let response = try await client.lookup(PublicLookupRequest(
            trackingNumber: request.trackingNumber, carrier: request.carrier,
            trackingURL: request.trackingURL, dpdPostcode: request.dpdPostcode,
            dpdPostcodeCountry: request.dpdPostcodeCountry, lookupCountryHint: request.lookupCountryHint
        ))
        guard ParcelLinkRoute.validID(response.link.id) else { throw DeliveryAPIError.invalidResponse }
        var entry = DeviceParcel(linkID: response.link.id, label: request.label ?? "", archivedAt: nil,
                                 parcel: Parcel(shared: response.package), checkedAt: now())
        // The service gives one parcel one link for each lookup; a second lookup of a parcel takes the first one's place.
        if let twin = entries.firstIndex(where: { $0.parcel.id == response.package.id }) {
            let old = entries.remove(at: twin)
            let key = keys.removeValue(forKey: old.linkID)
            if key != nil || old.alertToken != nil {
                Task {
                    if let token = old.alertToken { try? await client.removeAlert(old.linkID, DeleteParcelAppAlertRequest(token: token)) }
                    if let key { try? await client.forget(old.linkID, key) }
                }
            }
            // The parcel being replaced came back unchanged: only its link is new.
            if old.parcel.id == id {
                entry.label = old.label
                entry.archivedAt = old.archivedAt
                entry.replaced = old.replaced
            }
        }
        entries.append(entry)
        keys[entry.linkID] = response.key
        save()
        if let postcode = request.dpdPostcode { postcodes.remember(postcode, for: request.carrier ?? response.package.carrier) }
        return Self.shown(entry)
    }

    /// Reads every parcel whose answer has gone stale, or every one when `all`. A parcel the
    /// service no longer has leaves the list. The first trouble ends the round and is thrown;
    /// the list keeps what the device has.
    func refresh(all: Bool = false, only id: UUID? = nil) async throws {
        guard let client else { return }
        // Keys without their parcel, left by a file that couldn't be read: the service forgets those links too.
        for (linkID, key) in keys where !entries.contains(where: { $0.linkID == linkID }) {
            guard (try? await client.forget(linkID, key)) != nil, !entries.contains(where: { $0.linkID == linkID }) else { continue }
            keys[linkID] = nil
        }
        var trouble: Error?
        for entry in entries where id == nil || entry.parcel.id == id {
            let key = keys[entry.linkID]
            guard key != nil || entry.isViewer else { continue }
            let age = now().timeIntervalSince(entry.checkedAt)
            let fresh = Self.waiting(entry.parcel) ? Self.waitingFresh
                : entry.parcel.currentStage?.isFinal == true ? Self.settledFresh : Self.fresh
            guard all || id != nil || age < 0 || age >= fresh else { continue }
            do {
                let response = try await client.read(entry.linkID, key)
                guard let index = entries.firstIndex(where: { $0.linkID == entry.linkID }) else { continue }
                if let response {
                    entries[index].parcel = Parcel(shared: response.package)
                    entries[index].checkedAt = now()
                    if entry.isViewer { entries[index].canKeep = response.link.canKeep }
                } else {
                    entries.remove(at: index)
                    keys[entry.linkID] = nil
                }
            } catch {
                trouble = error
                break
            }
        }
        save()
        if let trouble { throw trouble }
    }

    func rename(id: UUID, label: String) throws -> Parcel {
        try update(id) { $0.label = label }
    }

    func setArchived(id: UUID, _ archived: Bool) throws -> Parcel {
        try update(id) { $0.archivedAt = archived ? DateParser.isoString(self.now()) : nil }
    }

    /// The service forgets the parcel, then the device does. Someone else's parcel is forgotten
    /// here alone: its notifications on this iPhone stop, and its link goes on working for others.
    func forget(id: UUID) async throws {
        guard let index = entries.firstIndex(where: { $0.parcel.id == id }) else { return }
        let entry = entries[index]
        if let client, let token = entry.alertToken {
            try? await client.removeAlert(entry.linkID, DeleteParcelAppAlertRequest(token: token))
        }
        if let client, let key = keys[entry.linkID] { try await client.forget(entry.linkID, key) }
        entries.removeAll { $0.linkID == entry.linkID }
        keys[entry.linkID] = nil
        save()
    }

    /// Forgets every parcel. The ones the service could not be told about stay, and the trouble is thrown.
    func forgetAll() async throws {
        var trouble: Error?
        for entry in entries {
            do { try await forget(id: entry.parcel.id) } catch { trouble = error }
        }
        if let trouble { throw trouble }
    }

    /// Follows the same number with another carrier, link or postcode: a new lookup, which takes the old one's place and name.
    func changeCarrier(id: UUID, carrier: CarrierID, trackingURL: String?, dpdPostcode: String?,
                       dpdPostcodeCountry: String? = nil) async throws -> Parcel {
        // Someone else's parcel is followed as its link shows it, and its carrier stays theirs to change.
        guard let entry = entries.first(where: { $0.parcel.id == id }), !entry.isViewer else { throw DeliveryAPIError.parcelMissing }
        let added = try await lookUp(CreatePackageRequest(
            trackingNumber: entry.parcel.trackingNumber, label: entry.label, carrier: carrier,
            trackingURL: trackingURL, dpdPostcode: dpdPostcode, dpdPostcodeCountry: dpdPostcodeCountry,
            lookupCountryHint: ParcelLookupCountry.hint()
        ), replacing: id)
        if added.id != id {
            try? await forget(id: id)
            if let index = entries.firstIndex(where: { $0.parcel.id == added.id }) {
                entries[index].replaced = entry.replaced ?? id
                save()
                return Self.shown(entries[index])
            }
        }
        return added
    }

    /// The parcel's link as others may open it, or nil once its sharing was stopped.
    func share(id: UUID) async throws -> ParcelShare? {
        guard let client, let entry = entries.first(where: { $0.parcel.id == id }), let key = keys[entry.linkID] else { throw DeliveryAPIError.parcelMissing }
        guard let response = try await client.read(entry.linkID, key) else { throw DeliveryAPIError.parcelMissing }
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

    /// What an account needs to take the device's parcels over: its own with their keys, and
    /// someone else's that the link lets an account keep.
    var claims: [ClaimParcelLink] {
        entries.compactMap { entry in
            if entry.isViewer { return entry.canKeep == true ? ClaimParcelLink(id: entry.linkID, label: entry.label.nonEmpty) : nil }
            return keys[entry.linkID].map { ClaimParcelLink(id: entry.linkID, key: $0, label: entry.label.nonEmpty) }
        }
    }

    /// Whether the parcel behind this link is someone else's, followed here as its reader.
    func isViewer(linkID: String) -> Bool { entries.first { $0.linkID == linkID }?.isViewer == true }

    /// The link of a parcel followed here through someone else's link, which is all this iPhone may share of it.
    func viewerLink(id: UUID) -> String? { entries.first { $0.parcel.id == id && $0.isViewer }?.linkID }

    /// The parcel this iPhone follows through a link, as the list shows it.
    func parcelID(linkID: String) -> UUID? { entries.first { $0.linkID == linkID }?.parcel.id }

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
        if let index = entries.firstIndex(where: { $0.linkID == linkID || $0.parcel.id == response.package.id }) {
            guard let key, entries[index].isViewer else { return (entries[index].parcel.id, false) }
            let old = entries[index]
            entries[index].linkID = linkID
            entries[index].viewer = nil
            entries[index].canKeep = nil
            entries[index].parcel = Parcel(shared: response.package)
            entries[index].checkedAt = now()
            if old.linkID != linkID {
                // Another link's notifications give way to this one's, asked for on the next round.
                entries[index].alertToken = nil
                if let client, let token = old.alertToken {
                    Task { try? await client.removeAlert(old.linkID, DeleteParcelAppAlertRequest(token: token)) }
                }
            }
            keys[linkID] = key
            save()
            return (entries[index].parcel.id, false)
        }
        var entry = DeviceParcel(linkID: linkID, label: label?.nonEmpty ?? "", archivedAt: nil,
                                 parcel: Parcel(shared: response.package), checkedAt: now())
        if let key {
            keys[linkID] = key
        } else {
            entry.viewer = true
            entry.canKeep = response.link.canKeep
        }
        entries.append(entry)
        save()
        return (entry.parcel.id, true)
    }

    // MARK: - Notifications

    /// What a parcel's notifications tell when they are first turned on, as on the site: every scan
    /// while nothing has moved yet, the steps that matter after that.
    static func alertPreset(for parcel: Parcel) -> ParcelAlertPreset {
        !parcel.hasCarrierUpdate || parcel.currentStage == .registered ? .all : .important
    }

    /// Whether a parcel is still on its way, with something left to tell.
    var hasParcelsOnTheirWay: Bool { entries.contains { $0.parcel.currentStage?.isFinal != true } }

    /// Turns notifications on, on this iPhone's token and in its language, for every parcel still
    /// on its way: once a launch, and again when the token or the language changes. A parcel that
    /// arrived needs none. A round is short and quiet: a few parcels at most, and it ends at the
    /// first sign that the service wants a pause or sends no notifications. A link that leads
    /// nowhere, has no room or is no longer shared is left until the next launch.
    func registerAlerts(token: String, locale: NativePushLocale, environment: NativePushEnvironment = .current) async {
        let asked = "\(token) \(locale.rawValue)"
        guard let client, alertRounds.insert(asked).inserted else { return }
        defer { alertRounds.remove(asked) }
        let due = entries.filter { alertsAsked[$0.linkID] != asked && $0.parcel.currentStage?.isFinal != true }.prefix(Self.alertsPerRound)
        for entry in due {
            let preset = entry.alertPreset ?? Self.alertPreset(for: entry.parcel)
            let answer: DeviceAlertAnswer
            do {
                answer = try await client.setAlert(entry.linkID, keys[entry.linkID],
                                                   ParcelAppAlertRequest(token: token, environment: environment, preset: preset, locale: locale))
            } catch {
                break
            }
            if answer == .unavailable { break }
            alertsAsked[entry.linkID] = asked
            guard answer == .on, let index = entries.firstIndex(where: { $0.linkID == entry.linkID }) else { continue }
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

    private func update(_ id: UUID, _ change: (inout DeviceParcel) -> Void) throws -> Parcel {
        guard let index = entries.firstIndex(where: { $0.parcel.id == id }) else { throw DeliveryAPIError.parcelMissing }
        change(&entries[index])
        save()
        return Self.shown(entries[index])
    }

    private func save() { storage.save(entries, keys: keys) }

    private static func waiting(_ parcel: Parcel) -> Bool { parcel.syncStatus == .pending || parcel.syncStatus == .syncing }

    private static func shown(_ entry: DeviceParcel) -> Parcel {
        var parcel = entry.parcel
        parcel.label = entry.label
        parcel.archivedAt = entry.archivedAt
        if let replaced = entry.replaced {
            var data = parcel.carrierData ?? CarrierData()
            data.originalPackageID = replaced
            parcel.carrierData = data
        }
        return parcel
    }
}
