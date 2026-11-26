import Foundation

/// A shop's tracking-page link that names no parcel yet, as H&M's "Follow package" before the shop ships, which the
/// server keeps as a wait: it asks the shop's platform again every few hours for 14 days, then adds the parcels it names
/// under the wait's name, or marks the wait expired for the reader to remove. The link never comes back to the app.
/// The web's are `src/components/shopLink/useShopWaits.ts` and `ShopWaitCards.tsx`.
extension ShopWait {
    /// The demo's wait: a coat H&M hasn't shipped yet, kept a day ago, as the web's demo has it.
    static func demo(now: Date = Date()) -> ShopWait {
        ShopWait(
            id: demoID, platform: .parcellab, platformName: "parcelLab", shopName: "H&M", label: "Wool coat",
            createdAt: DateParser.isoString(now.addingTimeInterval(-day)),
            expiresAt: DateParser.isoString(now.addingTimeInterval(13 * day)), expiredAt: nil
        )
    }

    static let demoID = UUID(uuidString: "5A0D5E00-0000-4000-8000-00000000C0A7")!
    private static let day: TimeInterval = 86_400

    /// Peek stopped asking without a parcel: the reader can only remove it.
    var expired: Bool { expiredAt != nil }
}

/// The answer of `/api/shop-waits`, read leniently: a wait this app can't read, such as a newer server's platform,
/// drops out instead of failing the whole list.
struct ShopWaitsOffer: Decodable {
    let waits: [ShopWait]

    static func decode(_ data: Data) throws -> [ShopWait] {
        do { return try JSONDecoder.deliveryTracker.decode(Self.self, from: data).waits }
        catch { throw DeliveryAPIError.invalidResponse }
    }

    private enum Keys: String, CodingKey { case shopWaits }

    init(from decoder: any Decoder) throws {
        let container = try decoder.container(keyedBy: Keys.self)
        waits = try container.decode([Known].self, forKey: .shopWaits).compactMap(\.wait)
    }

    private struct Known: Decodable {
        let wait: ShopWait?
        init(from decoder: any Decoder) throws { wait = try? ShopWait(from: decoder) }
    }
}

/// The waits the list shows, and what it knows of them: the ones the service named at its last read, so that one
/// gone without the reader removing it tells the list it became parcels, and the ones the reader removed, which a
/// read that began before the removal doesn't bring back.
struct ShopWaitList {
    /// How often the list asks again while it stays open: the server asks the shops only every few hours.
    static let readEvery: TimeInterval = 10 * 60

    private(set) var waits: [ShopWait]
    /// When the service was last asked, answered or not.
    private(set) var askedAt: Date?
    private var known: Set<UUID> = []
    private var removed: [UUID: (wait: ShopWait, index: Int)] = [:]

    init(_ waits: [ShopWait] = []) {
        self.waits = waits
    }

    /// The list an account opens on: the demo's one wait, or none until the service is asked. A guest's device has none.
    /// The App Store pictures (`-sdt.debug.storeScreenshots YES`) show the demo's parcels without it.
    static func opening(demo: Bool, now: Date = Date(), defaults: UserDefaults = .standard) -> ShopWaitList {
        #if DEBUG
        let pictures = defaults.bool(forKey: "sdt.debug.storeScreenshots")
        #else
        let pictures = false
        #endif
        return ShopWaitList(demo && !pictures ? [.demo(now: now)] : [])
    }

    /// Whether the list is due to ask again while it stays open.
    func due(now: Date = Date()) -> Bool {
        askedAt.map { now.timeIntervalSince($0) >= Self.readEvery } ?? true
    }

    mutating func asking(at now: Date = Date()) { askedAt = now }

    /// Takes the service's waits. True when one it named before is gone without the reader removing it: the shop
    /// shipped and the wait became parcels, which the list then reads.
    mutating func read(_ next: [ShopWait]) -> Bool {
        let ids = Set(next.map(\.id))
        let gone = known.contains { !ids.contains($0) && removed[$0] == nil }
        known = ids
        waits = next.filter { removed[$0.id] == nil }
        return gone
    }

    /// Takes a wait off the list at once, before the service is told. False when the list doesn't show it.
    mutating func remove(_ id: UUID) -> Bool {
        guard let index = waits.firstIndex(where: { $0.id == id }) else { return false }
        removed[id] = (waits.remove(at: index), index)
        return true
    }

    /// Puts back, where it stood, a wait the service could not be told to remove.
    mutating func restore(_ id: UUID) {
        guard let removal = removed.removeValue(forKey: id), !waits.contains(where: { $0.id == id }) else { return }
        waits.insert(removal.wait, at: min(removal.index, waits.count))
    }
}

/// What a wait's card says, as the web's card words it: the shop and its platform where a parcel shows its carrier,
/// the name the parcels will take, whether Peek still asks and until when, and its button.
struct ShopWaitCardText: Equatable {
    let via: String
    let title: String
    let state: String
    /// "Peek checks every few hours until Wed 9 Dec", or what to do once Peek stopped. Nil for an end it can't read.
    let hint: String?
    let action: String
    let expired: Bool

    var stateSymbol: String { expired ? "clock" : "hourglass" }

    @MainActor init(_ wait: ShopWait, localizer: Localizer, now: Date = Date()) {
        let shop = wait.shopName?.nonEmpty
        let platform = wait.platformName
        expired = wait.expired
        via = shop.map { localizer.text("shopWait.via", ["shop": $0, "platform": platform]) }
            ?? localizer.text("shopWait.via.unnamed", ["platform": platform])
        title = wait.label.nonEmpty ?? shop ?? platform
        if expired {
            state = shop.map { localizer.text("shopWait.expired", ["shop": $0]) } ?? localizer.text("shopWait.expired.unnamed")
            hint = localizer.text("shopWait.expiredHint")
            action = localizer.text("shopWait.remove")
        } else {
            state = shop.map { localizer.text("shopWait.status", ["shop": $0]) } ?? localizer.text("shopWait.status.unnamed")
            hint = DateParser.date(wait.expiresAt).map { localizer.text("shopWait.until", ["date": localizer.shortDate($0, now: now)]) }
            action = localizer.text("shopWait.stop")
        }
    }
}
