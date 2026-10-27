import Foundation
import SwiftUI

enum ParcelTone: Sendable {
    case normal, warning, complete

    var color: Color {
        switch self {
        case .normal: Brand.accent
        case .warning: Brand.warning
        case .complete: ExperimentalPalette.delivered
        }
    }
}

/// A carrier's pickup point: the first line names it, any further lines give its address.
struct PickupPoint: Equatable, Sendable {
    let name: String
    let address: String?

    init?(_ text: String?) {
        let lines = (text ?? "").components(separatedBy: .newlines)
            .map { $0.trimmingCharacters(in: .whitespaces) }
            .filter { !$0.isEmpty }
        guard let first = lines.first else { return nil }
        name = first
        address = lines.count > 1 ? lines.dropFirst().joined(separator: ", ") : nil
    }

    /// Name and address on one line, for a maps search or the clipboard.
    var query: String { [name, address].compactMap { $0 }.joined(separator: ", ") }

    /// Directions when the carrier gives an address; a search when it only
    /// names the place, so the person can check which one it is.
    var mapsURL: URL? {
        let allowed = CharacterSet.urlQueryAllowed.subtracting(CharacterSet(charactersIn: "&=+?#"))
        guard let value = query.addingPercentEncoding(withAllowedCharacters: allowed) else { return nil }
        return URL(string: "https://maps.apple.com/?\(address == nil ? "q" : "daddr")=\(value)")
    }
}

struct StageMetadata: Sendable {
    let symbol: String
    let tone: ParcelTone
    let progress: Int
}

extension TrackingStage {
    static let core: [TrackingStage] = [
        .pending, .registered, .accepted, .inTransit, .outForDelivery, .delivered,
    ]

    var metadata: StageMetadata {
        switch self {
        case .pending: StageMetadata(symbol: "magnifyingglass", tone: .normal, progress: 0)
        case .registered: StageMetadata(symbol: "doc.text", tone: .normal, progress: 1)
        case .accepted: StageMetadata(symbol: "shippingbox", tone: .normal, progress: 2)
        case .inTransit: StageMetadata(symbol: "truck.box", tone: .normal, progress: 3)
        case .customs: StageMetadata(symbol: "building.columns", tone: .warning, progress: 3)
        case .exception: StageMetadata(symbol: "exclamationmark.circle", tone: .warning, progress: 3)
        case .outForDelivery: StageMetadata(symbol: "bicycle", tone: .normal, progress: 4)
        case .failedAttempt: StageMetadata(symbol: "exclamationmark.triangle", tone: .warning, progress: 4)
        case .readyForPickup: StageMetadata(symbol: "storefront", tone: .warning, progress: 4)
        case .delivered: StageMetadata(symbol: "checkmark.circle.fill", tone: .complete, progress: 5)
        case .returned: StageMetadata(symbol: "arrow.uturn.backward.circle", tone: .warning, progress: 5)
        }
    }

    var eventOrder: Int {
        switch self {
        case .pending: 0
        case .registered: 1
        case .accepted: 2
        case .inTransit: 3
        case .customs: 4
        case .exception: 5
        case .outForDelivery: 6
        case .failedAttempt: 7
        case .readyForPickup: 8
        case .delivered: 9
        case .returned: 10
        }
    }

    var localizationKey: String { "stage.\(rawValue)" }
    var isFinal: Bool { self == .delivered || self == .returned }

    var deliveryActivityPhase: DeliveryActivityPhase? {
        switch self {
        case .outForDelivery: .outForDelivery
        case .delivered: .delivered
        case .failedAttempt: .failedAttempt
        case .readyForPickup: .readyForPickup
        case .returned: .returned
        case .exception: .exception
        default: nil
        }
    }
}

extension Parcel {
    /// The carrier an automatic correction replaced, for 12 hours. A parcel filed
    /// without a carrier only found one: nothing was swapped.
    func automaticallyChangedFrom(at now: Date = Date()) -> CarrierID? {
        guard let from = carrierData?.autoChangedFrom, from != carrier,
              from != .unknown, from != .internationalPost,
              carrierData?.autoChangedTo == carrier,
              let at = carrierData?.autoChangedAt.flatMap(DateParser.date),
              now >= at, now.timeIntervalSince(at) < 12 * 60 * 60 else { return nil }
        return from
    }

    /// A carrier that knows the number but needs this input before it can track the parcel.
    var inputNeeded: CarrierInputNeeded? { carrierData?.routing?.inputNeeded }

    /// Recognition found another carrier that needs the delivery postcode, or the parcel's own
    /// carrier needs it to find the parcel, which other sources track meanwhile, while it is
    /// still on its way. With nothing tracking it, the sync error asks instead.
    var inputNeededPrompt: CarrierInputNeeded? {
        guard let inputNeeded, inputNeeded.field == .dpdPostcode, isActive,
              inputNeeded.carrier != carrier
                || (dpdPostcode?.nonEmpty == nil && syncError != "carrier:input_required") else { return nil }
        return inputNeeded
    }

    /// The stored postcode the parcel's own carrier refused on its last answer (PostNL finding no
    /// parcel with it), which its owner is asked to check.
    var refusedPostcode: String? {
        guard carrierData?.routing?.postcodeRefused?.carrier == carrier else { return nil }
        return dpdPostcode?.nonEmpty
    }

    /// Nobody has answered for the number yet, and its shape points to a carrier that needs
    /// the delivery postcode before it can be asked about it.
    var inputSuggestedPrompt: CarrierInputSuggested? {
        guard inputNeededPrompt == nil, let suggested = carrierData?.routing?.inputSuggested,
              carrier == .unknown, !isArchived, currentEvent == nil else { return nil }
        return suggested
    }

    /// How long a carrier may go without listing a number of its own format before the reader is asked to check it.
    static let likelyCarrierWait: TimeInterval = 7 * 24 * 60 * 60

    /// Nobody has answered for the number yet, and it has one carrier's own format, which does not list
    /// it: the carrier it probably travels with. `waited` once it has not for a week, when its owner is
    /// asked to check the number or choose another carrier.
    func likelyCarrierPrompt(now: Date = .now) -> (carrier: CarrierID, waited: Bool)? {
        guard inputNeededPrompt == nil, inputSuggestedPrompt == nil, let likely = carrierData?.routing?.likelyCarrier,
              carrier == .unknown, !isArchived, !hasCarrierUpdate else { return nil }
        let since = DateParser.date(likely.since)
        return (likely.carrier, since.map { now.timeIntervalSince($0) >= Self.likelyCarrierWait } ?? false)
    }

    var sortedEvents: [TrackingEvent] {
        trackingEvents.sorted(by: Self.eventPrecedes)
    }

    /// The journal's rows, newest first. A scan of a relay pair (`relayOf`) is
    /// a line under the scan it names, and keeps a row of its own when that
    /// scan is not there.
    var journalEntries: [JournalEntry] {
        let byID = Dictionary(trackingEvents.map { ($0.id, $0) }, uniquingKeysWith: { first, _ in first })
        var relays: [UUID: [TrackingEvent]] = [:]
        var folded = Set<UUID>()
        for event in trackingEvents {
            guard let scanID = event.relayOf, scanID != event.id, let scan = byID[scanID], scan.relayOf == nil else { continue }
            relays[scanID, default: []].append(event)
            folded.insert(event.id)
        }
        return sortedEvents.filter { !folded.contains($0.id) }
            .map { JournalEntry(event: $0, relays: (relays[$0.id] ?? []).sorted(by: Self.eventPrecedes)) }
    }

    /// The country code of the first place the parcel was scanned, which is where it was posted.
    var stampOrigin: String? {
        sortedEvents.last { $0.place != nil }?.place?.country
    }

    /// The day and month the parcel was delivered, as a postmark shows it: 28.09.
    var stampDeliveryDate: String? {
        guard isDelivered, let event = currentEvent, let date = event.displayDate else { return nil }
        let parts = Calendar.current.dateComponents([.day, .month], from: date)
        return String(format: "%02d.%02d", parts.day ?? 0, parts.month ?? 0)
    }

    /// Whether refreshed parcels bring new tracking: a changed timeline, status
    /// line or delivery date. A parcel that replaced another is compared with it.
    static func trackingChanged(from before: [Parcel], to after: [Parcel]) -> Bool {
        let earlier = Dictionary(before.map { ($0.id, $0) }, uniquingKeysWith: { first, _ in first })
        return after.contains { parcel in
            guard let previous = earlier[parcel.id] ?? parcel.carrierData?.originalPackageID.flatMap({ earlier[$0] })
            else { return true }
            return parcel.trackingEvents != previous.trackingEvents
                || parcel.lastStatusText != previous.lastStatusText
                || parcel.expectedDelivery != previous.expectedDelivery
        }
    }

    /// Newest first; scans of one instant by delivery progress, then by id.
    static func eventPrecedes(_ lhs: TrackingEvent, _ rhs: TrackingEvent) -> Bool {
        let left = DateParser.date(lhs.occurredAt)?.timeIntervalSince1970 ?? 0
        let right = DateParser.date(rhs.occurredAt)?.timeIntervalSince1970 ?? 0
        if left == right {
            if lhs.stage != rhs.stage { return lhs.stage.eventOrder > rhs.stage.eventOrder }
            return lhs.id.uuidString > rhs.id.uuidString
        }
        return left > right
    }

    var currentEvent: TrackingEvent? {
        LatestEventCache.shared.latest(of: self)
    }

    var currentStage: TrackingStage? { currentEvent?.stage }
    /// Where a parcel waiting for collection waits, for its status line.
    var pickupPlace: String? { currentStage == .readyForPickup ? PickupPoint(carrierData?.pickupPoint)?.name : nil }
    var hasCarrierUpdate: Bool { currentEvent.map { $0.stage != .pending } ?? false }
    var isUnannounced: Bool { !hasCarrierUpdate && syncStatus == .waiting }
    var isArchived: Bool { archivedAt != nil }
    var isDelivered: Bool { currentStage == .delivered }
    var isReturned: Bool { currentStage == .returned }
    var isActive: Bool { !isArchived && !(currentStage?.isFinal ?? false) }

    /// Distinct numbers, the followed one first, then the named delivering carrier's own, and last the number the
    /// followed carrier tracks the parcel by when it is none of them. That one is `tracksAs`: a reference the carrier's
    /// search resolved to a parcel, or a number typed with more or less than the carrier's own, shown so the reader can
    /// tell which parcel the carrier answered for.
    var trackingNumbers: [(carrier: CarrierID, number: String, tracksAs: Bool)] {
        let delivery = (carrier: activeTrackingCarrier, number: carrierData?.activeTrackingNumber?.nonEmpty ?? trackingNumber, tracksAs: false)
        let original = (carrier: carrierData?.originalCarrier ?? carrier, number: carrierData?.originalTrackingNumber?.nonEmpty ?? trackingNumber, tracksAs: false)
        var numbers = delivery.number == original.number ? [delivery] : [delivery, original]
        if let named = namedDelivery, let number = named.number { numbers.append((carrier: named.carrier, number: number, tracksAs: false)) }
        guard let canonical = carrierData?.canonicalTrackingNumber?.nonEmpty,
              !numbers.contains(where: { CarrierCatalog.normalize($0.number) == CarrierCatalog.normalize(canonical) }) else { return numbers }
        return numbers + [(carrier: delivery.carrier, number: canonical, tracksAs: true)]
    }

    /// What a number is called beside it: the carrier's name once there are several, and what the carrier tracks the
    /// parcel as for the number it resolved the typed one to.
    @MainActor func trackingNumberLabel(_ entry: (carrier: CarrierID, number: String, tracksAs: Bool), localizer: Localizer) -> String {
        let name = CarrierCatalog.shared.info(for: entry.carrier, language: localizer.language).displayName
        if entry.tracksAs { return localizer.text("detail.tracksAs", ["carrier": name]) }
        return trackingNumbers.filter { !$0.tracksAs }.count > 1 ? name : localizer.text("detail.trackingNumber")
    }

    var displayedCarrier: CarrierID { carrierData?.originalCarrier ?? activeTrackingCarrier }

    /// The carrier the followed one names as delivering, while the parcel is not followed there yet, and the number it
    /// gave. Once it is followed, the parcel has been handed over and `trackingSource` says so.
    var namedDelivery: (carrier: CarrierID, number: String?)? {
        guard let carrier = carrierData?.deliveryCarrier, carrierData?.originalCarrier == nil, carrier != displayedCarrier,
              CarrierCatalog.shared.definitions[carrier] != nil else { return nil }
        let number = carrierData?.deliveryTrackingNumber?.nonEmpty
        return (carrier, number == trackingNumber ? nil : number)
    }

    /// Who delivers when it isn't the carrier shown: the one the parcel was handed to, else the one its carrier names.
    var deliveringCarrier: CarrierID? {
        activeTrackingCarrier != displayedCarrier ? activeTrackingCarrier : namedDelivery?.carrier
    }

    var amazonShippingHistoryExpired: Bool {
        carrier == .amazonShipping && syncError == "amazon_shipping_history_expired"
    }

    var activeTrackingCarrier: CarrierID {
        if CarrierCatalog.shared.requiresAmazonAccount(carrier, trackingNumber: trackingNumber) { return .amazonLogistics }
        if let trackingSource { return trackingSource }
        return CarrierCatalog.supportsSwissPostHandoff(trackingNumber) ? .aliexpress : carrier
    }

    /// Whether only the owner can make the number trackable: a placeholder, or one its carrier does not issue.
    var numberToCheck: Bool {
        syncError == "carrier:placeholder_number" || syncError == "carrier:refused_number"
    }

    var displayStatus: ParcelDisplayStatus {
        if !hasCarrierUpdate && syncStatus == .error && numberToCheck {
            return ParcelDisplayStatus(key: "status.checkNumber", tone: .warning, syncing: false)
        }
        if !hasCarrierUpdate && !CarrierCatalog.shared.tracksAutomatically(activeTrackingCarrier) {
            return ParcelDisplayStatus(key: "status.unsupported", tone: .warning, syncing: false)
        }
        if !hasCarrierUpdate && (syncStatus == .pending || syncStatus == .syncing) {
            return ParcelDisplayStatus(key: "status.syncing", tone: .normal, syncing: true)
        }
        if !hasCarrierUpdate && syncStatus == .error {
            return ParcelDisplayStatus(key: "status.failed", tone: .warning, syncing: false)
        }
        if !hasCarrierUpdate && syncStatus == .unsupported {
            return ParcelDisplayStatus(key: "status.unsupported", tone: .warning, syncing: false)
        }
        if isUnannounced {
            return ParcelDisplayStatus(key: carrierData?.routing?.providerInputNeeded != nil ? "status.inputNeeded" : carrier == .unknown ? "status.noHistory" : "status.unannounced", tone: .normal, syncing: false)
        }
        guard let currentStage else {
            return ParcelDisplayStatus(key: "status.unannounced", tone: .normal, syncing: false)
        }
        return ParcelDisplayStatus(
            key: currentStage.localizationKey,
            tone: currentStage.metadata.tone,
            syncing: false
        )
    }

    /// What a card says the parcel needs: no failed update for a number only the owner can correct, whose status says to check it.
    func notice(now: Date = Date()) -> ParcelAttention? {
        let reason = attention(now: now)
        return reason == .syncError && numberToCheck ? nil : reason
    }

    /// Whether a card says the latest update failed: not for a number only the owner can correct.
    var updateFailed: Bool { syncStatus == .error && !numberToCheck }

    func attention(now: Date = Date()) -> ParcelAttention? {
        switch currentStage {
        case .failedAttempt: return .failedAttempt
        case .readyForPickup: return .readyForPickup
        case .exception: return .exception
        case .customs: return .customs
        default: break
        }
        // Made-up moves say nothing of where the parcel is, whether the latest check failed or how old they are.
        if !GeneratedTrack.scans(trackingEvents).isEmpty { return .generated }
        if syncStatus == .error { return .syncError }
        if let event = currentEvent,
           [.registered, .accepted, .inTransit].contains(event.stage),
           let update = DateParser.date(event.occurredAt),
           now.timeIntervalSince(update) >= 4 * 86_400 {
            return .stalled
        }
        if isUnannounced,
           let created = DateParser.date(createdAt),
           now.timeIntervalSince(created) >= 2 * 86_400 {
            return .notAnnounced
        }
        return nil
    }

    /// Whether the map has a place to draw: a generated track's are left out.
    var hasMappedPlace: Bool { GeneratedTrack.without(trackingEvents).contains { $0.place != nil } }

    /// Whether the carrier has only ever announced the parcel, its first label at least a day ago: the sender made the
    /// label and still has the parcel. The carrier has nothing more to say until they hand it over.
    func awaitingHandover(now: Date = Date()) -> Bool {
        let carrierEvents = trackingEvents.filter { $0.stage != .pending }
        guard !carrierEvents.isEmpty, carrierEvents.allSatisfy({ $0.stage == .registered }),
              let first = carrierEvents.compactMap({ DateParser.date($0.occurredAt) }).min() else { return false }
        return now.timeIntervalSince(first) >= 86_400
    }

    private static let expectedDayPattern = try? NSRegularExpression(pattern: "^(\\d{4}-\\d{2}-\\d{2})(?:$|[ T])")

    var expectedDayKey: String? {
        guard let expectedDelivery else { return nil }
        let range = NSRange(expectedDelivery.startIndex..., in: expectedDelivery)
        guard let match = Self.expectedDayPattern?.firstMatch(in: expectedDelivery, range: range),
              let swiftRange = Range(match.range(at: 1), in: expectedDelivery) else { return nil }
        return String(expectedDelivery[swiftRange])
    }

    var searchText: String {
        ([label, trackingNumber, lastStatusText ?? ""]
            + trackingEvents.flatMap { [$0.description, $0.location ?? ""] })
            .joined(separator: " ")
            .lowercased()
    }
}

/// Filters, sorts and every card ask for a parcel's latest event several times
/// per render. Keep each answer until the parcel's history changes: copies of a
/// list share their event arrays, so a hit compares storage, not events.
private final class LatestEventCache: @unchecked Sendable {
    static let shared = LatestEventCache()
    private let lock = NSLock()
    private var entries: [UUID: (events: [TrackingEvent], latest: TrackingEvent?)] = [:]

    func latest(of parcel: Parcel) -> TrackingEvent? {
        let events = parcel.trackingEvents
        if let entry = lock.withLock({ entries[parcel.id] }), entry.events == events {
            return entry.latest
        }
        let carrier = events.filter { $0.stage != .pending }.sorted(by: Parcel.eventPrecedes)
        let latest = Self.current(newestFirst: carrier) ?? events.min(by: Parcel.eventPrecedes)
        lock.withLock { entries[parcel.id] = (events, latest) }
        return latest
    }

    /// Carrier stages before the parcel moves, in order.
    private static let early: [TrackingStage] = [.registered, .accepted]
    /// Stages of a parcel on its way, short of a final one.
    private static let moving: Set<TrackingStage> = [.inTransit, .customs, .outForDelivery, .failedAttempt, .readyForPickup]

    /// An announcement or acceptance after the parcel has moved on is a notice
    /// or a reworded scan, not a step back: the scan before it still says where
    /// the parcel is. After a problem, a return or a delivery, a new label starts over.
    private static func current(newestFirst carrier: [TrackingEvent]) -> TrackingEvent? {
        guard var current = carrier.first else { return nil }
        for event in carrier.dropFirst() {
            guard let rank = early.firstIndex(of: current.stage) else { break }
            if moving.contains(event.stage) { return event }
            guard let earlier = early.firstIndex(of: event.stage) else { break }
            if earlier > rank { current = event }
        }
        return current
    }
}

/// A row of the journal: a scan, and the earlier carrier's copies of it.
struct JournalEntry: Identifiable, Equatable, Sendable {
    let event: TrackingEvent
    let relays: [TrackingEvent]
    var id: UUID { event.id }
}

struct ParcelDisplayStatus: Sendable {
    let key: String
    let tone: ParcelTone
    let syncing: Bool
}

/// The scans of a generated track, as the web app tells them (`src/lib/generatedTrack.ts`). The parcel's newest scans each
/// moved it on in transit to a new place, each instant at one place, one hour to two days after the scan before and not
/// in whole minutes, and three of those gaps are one interval to within 2 s. The track runs from the oldest move at a
/// repeated interval to the newest scan. No network moves a parcel on at one interval to the second; a carrier that
/// rounds its clock gives whole minutes. None unless the newest scan is such a move.
enum GeneratedTrack {
    private struct Instant {
        var scans: [TrackingEvent] = []
        var places: Set<String> = []
        var transit = true
    }

    static func scans(_ events: [TrackingEvent]) -> [TrackingEvent] {
        // Peek's own rows and notices are not the carrier moving the parcel.
        var instants: [TimeInterval: Instant] = [:]
        for event in events where event.stage != .pending {
            guard let at = DateParser.date(event.occurredAt)?.timeIntervalSince1970 else { continue }
            var instant = instants[at] ?? Instant()
            instant.scans.append(event)
            if let place = event.location?.trimmingCharacters(in: .whitespacesAndNewlines).lowercased(), !place.isEmpty {
                instant.places.insert(place)
            }
            instant.transit = instant.transit && event.stage == .inTransit
            instants[at] = instant
        }
        let newestFirst = instants.sorted { $0.key > $1.key }
        var moves: [(gap: Int, scans: [TrackingEvent])] = []
        for index in newestFirst.indices.dropFirst() {
            let scan = newestFirst[index - 1], previous = newestFirst[index]
            let gap = Int((scan.key - previous.key).rounded())
            let moved = scan.value.transit && previous.value.transit && scan.value.places.count == 1
                && previous.value.places.count == 1 && scan.value.places != previous.value.places
            guard moved, (3_600...172_800).contains(gap), gap % 60 != 0 else { break }
            moves.append((gap, scan.value.scans))
        }
        let repeated = { (gap: Int) in moves.filter { abs($0.gap - gap) <= 2 }.count >= 3 }
        // Moves before the first repeated one may be the carrier's own.
        guard let first = moves.lastIndex(where: { repeated($0.gap) }) else { return [] }
        return moves[...first].flatMap(\.scans)
    }

    /// The scans with a generated track's left out: where the parcel really was last.
    static func without(_ events: [TrackingEvent]) -> [TrackingEvent] {
        let generated = Set(scans(events).map(\.id))
        return generated.isEmpty ? events : events.filter { !generated.contains($0.id) }
    }
}

enum ParcelAttention: String, Sendable {
    case syncError = "sync_error"
    case failedAttempt = "failed_attempt"
    case readyForPickup = "ready_for_pickup"
    case customs, exception, generated, stalled
    case notAnnounced = "not_announced"

    var localizationKey: String { "attention.\(rawValue)" }
}

enum ParcelStatusFilter: String, CaseIterable, Identifiable {
    case all, active, attention, today, delivered, archived
    var id: String { rawValue }
    var localizationKey: String { "view.filter.\(rawValue)" }
}

enum ParcelSort: String, CaseIterable, Identifiable {
    case priority, updated, newest, eta, carrier
    var id: String { rawValue }
    var localizationKey: String { "view.sort.\(rawValue)" }
}

enum ParcelSectionKind: String, Identifiable {
    case attention, today, active, delivered, returned, archived
    var id: String { rawValue }
}

struct ParcelSection: Identifiable {
    let kind: ParcelSectionKind
    let parcels: [Parcel]
    var id: String { kind.id }
}

enum ParcelOrganizer {
    static func visible(
        _ parcels: [Parcel],
        query: String,
        status: ParcelStatusFilter,
        carrier: CarrierID?,
        sort: ParcelSort,
        now: Date = Date(),
        catalog: CarrierCatalog = .shared
    ) -> [Parcel] {
        let trimmed = query.trimmingCharacters(in: .whitespacesAndNewlines).lowercased()
        let compact = trimmed.replacingOccurrences(of: "[\\s.-]", with: "", options: .regularExpression)
        let today = dayKey(now)
        let matching = parcels
            .filter { parcel in
                guard !trimmed.isEmpty else { return true }
                let carrierName = catalog.info(for: parcel.carrier).displayName.lowercased()
                let compactTracking = parcel.trackingNumber.lowercased()
                    .replacingOccurrences(of: "[\\s.-]", with: "", options: .regularExpression)
                return parcel.searchText.contains(trimmed)
                    || carrierName.contains(trimmed)
                    || (!compact.isEmpty && compactTracking.contains(compact))
            }
            .filter { parcel in
                switch status {
                case .all: true
                case .active: parcel.isActive
                case .attention: parcel.isActive && parcel.attention(now: now) != nil
                case .today: parcel.isActive && parcel.expectedDayKey == today
                case .delivered: !parcel.isArchived && parcel.isDelivered
                case .archived: parcel.isArchived
                }
            }
            .filter { carrier == nil || $0.carrier == carrier }
        return sorted(matching, by: sort, catalog: catalog)
    }

    /// Reads each parcel's sort fields once, instead of twice per comparison.
    static func sorted(_ parcels: [Parcel], by sort: ParcelSort, catalog: CarrierCatalog = .shared) -> [Parcel] {
        parcels.map { SortKey($0, sort: sort, catalog: catalog) }
            .sorted { $0.precedes($1) }
            .map(\.parcel)
    }

    /// Feature an arrival or pickup. Issues are shown separately as compact notices.
    static func nextDelivery(from parcels: [Parcel], now: Date = Date()) -> Parcel? {
        let today = dayKey(now)
        let candidates = parcels.filter { parcel in
            guard parcel.isActive else { return false }
            let reason = parcel.attention(now: now)
            return reason == nil || reason == .readyForPickup
                || (reason == .syncError && parcel.currentStage == .outForDelivery)
        }
        func urgency(_ parcel: Parcel) -> Int {
            if parcel.currentStage == .readyForPickup { return 0 }
            return parcel.currentStage == .outForDelivery || parcel.expectedDayKey == today ? 1 : 2
        }
        let next = candidates
            .map { (urgency: urgency($0), placed: $0.trackingEvents.contains { $0.place != nil },
                    key: SortKey($0, sort: .priority, catalog: .shared)) }
            .min { left, right in
                if left.urgency != right.urgency { return left.urgency < right.urgency }
                // Next up draws the parcel's route: of two due the same day, the one with places to draw goes first.
                if left.key.expected == right.key.expected, left.placed != right.placed { return left.placed }
                return left.key.precedes(right.key)
            }
        return next?.key.parcel
    }

    static func sections(from parcels: [Parcel], now: Date = Date()) -> [ParcelSection] {
        let today = dayKey(now)
        let active = parcels.filter(\.isActive).map { (parcel: $0, attention: $0.attention(now: now)) }
        let attention = active.filter { $0.attention != nil }.map(\.parcel)
        let arrivingToday = active.filter {
            $0.attention == nil && $0.parcel.expectedDayKey == today
        }.map(\.parcel)
        let onTheWay = active.filter {
            $0.attention == nil && $0.parcel.expectedDayKey != today
        }.map(\.parcel)
        let archived = sortPastParcels(parcels.filter(\.isArchived))
        let values: [(ParcelSectionKind, [Parcel])] = [
            (.attention, attention),
            (.today, arrivingToday),
            (.active, onTheWay),
            (.delivered, sortPastParcels(parcels.filter { !$0.isArchived && $0.isDelivered })),
            (.returned, sortPastParcels(parcels.filter { !$0.isArchived && $0.isReturned })),
            (.archived, archived),
        ]
        return values.compactMap { $0.1.isEmpty ? nil : ParcelSection(kind: $0.0, parcels: $0.1) }
    }

    private struct SortKey {
        let parcel: Parcel
        let sort: ParcelSort
        let expected: String
        let updated: String
        let carrierName: String

        init(_ parcel: Parcel, sort: ParcelSort, catalog: CarrierCatalog) {
            self.parcel = parcel
            self.sort = sort
            expected = sort == .priority || sort == .eta ? parcel.expectedDayKey ?? "9999-99-99" : ""
            updated = sort == .priority || sort == .updated
                ? parcel.currentEvent?.occurredAt ?? parcel.createdAt
                : ""
            carrierName = sort == .carrier ? catalog.info(for: parcel.carrier).displayName : ""
        }

        func precedes(_ other: SortKey) -> Bool {
            let comparison: ComparisonResult
            switch sort {
            case .priority:
                comparison = expected.compare(other.expected) == .orderedSame
                    ? other.updated.compare(updated)
                    : expected.compare(other.expected)
            case .updated:
                comparison = other.updated.compare(updated)
            case .newest:
                comparison = other.parcel.createdAt.compare(parcel.createdAt)
            case .eta:
                comparison = expected.compare(other.expected)
            case .carrier:
                comparison = carrierName.localizedCaseInsensitiveCompare(other.carrierName)
            }
            if comparison != .orderedSame { return comparison == .orderedAscending }
            let label = parcel.label.localizedCaseInsensitiveCompare(other.parcel.label)
            return label == .orderedSame
                ? parcel.id.uuidString < other.parcel.id.uuidString
                : label == .orderedAscending
        }
    }

    private static func sortPastParcels(_ parcels: [Parcel]) -> [Parcel] {
        parcels.map { (parcel: $0, date: completionSortDate($0)) }
            .sorted { left, right in
                if left.date == right.date { return left.parcel.id.uuidString < right.parcel.id.uuidString }
                return left.date > right.date
            }
            .map(\.parcel)
    }

    private static func completionSortDate(_ parcel: Parcel) -> Date {
        if let event = parcel.currentEvent,
           event.stage.isFinal,
           let completionDate = DateParser.date(event.occurredAt) {
            return completionDate
        }
        if let archivedAt = parcel.archivedAt.flatMap(DateParser.date) {
            return archivedAt
        }
        if let eventDate = parcel.currentEvent.flatMap({ DateParser.date($0.occurredAt) }) {
            return eventDate
        }
        return DateParser.date(parcel.createdAt) ?? .distantPast
    }

    /// The parcels the list last showed on their way that have since been delivered, as the list
    /// showed them: each keeps that place until its card leaves for the past deliveries.
    static func justDelivered(shown: [Parcel], now: [Parcel]) -> [Parcel] {
        let current = Dictionary(now.map { ($0.id, $0) }, uniquingKeysWith: { first, _ in first })
        return shown.filter { was in
            guard was.isActive, let parcel = current[was.id] else { return false }
            return !parcel.isArchived && parcel.isDelivered
        }
    }

    /// The local calendar day as `yyyy-MM-dd`, without building a formatter per call.
    static func dayKey(_ date: Date) -> String {
        let parts = Calendar(identifier: .gregorian).dateComponents([.year, .month, .day], from: date)
        return String(format: "%04d-%02d-%02d", parts.year ?? 0, parts.month ?? 0, parts.day ?? 0)
    }
}

/// A parcel delivered since the list was last shown does not jump to the past deliveries: its
/// card first says so where it stands, then travels there. Until then the list keeps the
/// arrangement it had, whatever else the same news changed.
struct DeliveredHold: Equatable {
    /// The parcels that have just arrived.
    private(set) var ids: Set<UUID> = []
    /// Every parcel as the list showed it before they did.
    private var shown: [UUID: Parcel] = [:]

    var isEmpty: Bool { ids.isEmpty }
    func holds(_ id: UUID) -> Bool { ids.contains(id) }

    /// The list is about to show `next` where it showed `before`. A list that nobody is
    /// looking at holds nothing, and changes at once.
    mutating func receive(shown before: [Parcel], next: [Parcel], watching: Bool) {
        guard watching else {
            release()
            return
        }
        let current = Dictionary(next.map { ($0.id, $0) }, uniquingKeysWith: { first, _ in first })
        // A held parcel stays in its place for as long as it is still a delivery to show.
        var holding = ids.filter { id in current[id].map { !$0.isArchived && $0.isDelivered } ?? false }
        let arrivals = ParcelOrganizer.justDelivered(shown: before, now: next)
        if holding.isEmpty, !arrivals.isEmpty {
            shown = Dictionary(before.map { ($0.id, $0) }, uniquingKeysWith: { first, _ in first })
        }
        holding.formUnion(arrivals.map(\.id))
        if holding.isEmpty {
            release()
        } else if holding != ids {
            ids = holding
        }
    }

    mutating func release() {
        if !ids.isEmpty { ids = [] }
        if !shown.isEmpty { shown = [:] }
    }

    /// The parcels as the list arranges them: as they were shown, but for one archived or brought back since.
    func arranged(_ parcels: [Parcel]) -> [Parcel] {
        guard !ids.isEmpty else { return parcels }
        return parcels.map { parcel in
            guard let was = shown[parcel.id], was.isArchived == parcel.isArchived else { return parcel }
            return was
        }
    }
}

/// How the deliveries stand in their window: a list that opens a parcel over itself, as on a phone, or
/// the list beside the parcel, where both fit side by side.
enum DeliveriesArrangement: Equatable {
    case stacked
    case sideBySide

    /// About an iPhone's width for the list, and for the parcel beside it. At the accessibility text sizes
    /// each needs more, or a parcel's name breaks in the middle of its words.
    static func listWidth(largeText: Bool) -> CGFloat { largeText ? 440 : 320 }
    static func parcelWidth(largeText: Bool) -> CGFloat { largeText ? 480 : 390 }
    /// How far a window resized across the line goes before the arrangement turns, so that it doesn't
    /// turn back and forth while the window is being dragged.
    static let slack: CGFloat = 20

    /// Side by side needs a window that is wide and tall in its own right, as an iPad's or an open
    /// iPhone Duo's, and room for both columns. A phone on its side is wide but short, and keeps its list.
    static func resolve(width: CGFloat, regularWidth: Bool, regularHeight: Bool, largeText: Bool, current: Self) -> Self {
        guard regularWidth, regularHeight else { return .stacked }
        let both = listWidth(largeText: largeText) + parcelWidth(largeText: largeText)
        return width >= (current == .sideBySide ? both - slack : both + slack) ? .sideBySide : .stacked
    }
}

/// A parcel's page beside the list, given room for two columns as wide as a phone's, puts its history beside its
/// card, as the web's parcel page does on a wide screen. Over the list, as on a phone, it keeps one column.
enum ParcelPageColumns {
    /// A column's least width: about a phone's page, more at the accessibility text sizes.
    static func column(largeText: Bool) -> CGFloat { largeText ? 420 : 340 }
    static let gap: CGFloat = 28
    /// The page's margins and the paper's padding, on both sides.
    static let sides: CGFloat = 2 * (16 + 18)
    /// Past this, the columns stop growing and the page stands centred.
    static let widest: CGFloat = 1080

    /// Without a history the second column would only say when the carrier was last asked, and the page keeps one.
    static func fit(width: CGFloat, besideList: Bool, largeText: Bool, history: Bool) -> Bool {
        besideList && history && width >= 2 * column(largeText: largeText) + gap + sides
    }
}

/// The passport lies open on two pages, as it does at an iPhone Duo's fold, in a window wide and tall in its own
/// right with room for two pages as wide as a phone's. A phone, even on its side, keeps one page.
enum PassportSpread {
    /// The pages' margins, on both sides of each.
    static let margins: CGFloat = 2 * 20
    /// As wide as the single page grows.
    static let widest: CGFloat = 480

    static func fits(width: CGFloat, regularWidth: Bool, regularHeight: Bool, largeText: Bool) -> Bool {
        regularWidth && regularHeight && width >= 2 * (ParcelPageColumns.column(largeText: largeText) + margins)
    }
}

/// The parcel the deliveries show, over the list or beside it, and whether someone chose it. Every way a
/// parcel opens comes through here: its card, a notification, a link, the widget, Add finding it followed already.
struct DeliveriesNavigation: Equatable {
    /// The parcels open over the list, the last on top. Beside the list, the last is the one shown.
    var path: [UUID] = []
    /// The parcel shown beside the list that nobody chose, so that the column isn't empty.
    private(set) var automaticPick: UUID?

    var shown: UUID? { path.last }
    var shownAutomatically: Bool { automaticPick != nil && automaticPick == path.last }
    /// The parcel someone opened, if the one shown is theirs.
    var chosen: UUID? { shownAutomatically ? nil : shown }

    /// Opens the parcel someone asked for: over the list, or beside it in place of the one shown there.
    mutating func open(_ parcelID: UUID) {
        path = [parcelID]
        automaticPick = nil
    }

    /// Takes the window's arrangement and the list's `cards`, in order, with the archive's rows after them while
    /// they show. Beside the list, a parcel always shows: the one chosen, else the first card or row, and none only
    /// when the list shows neither. A parcel shown by itself gives way to the first card once it leaves the cards.
    /// Over the list, nothing opens by itself, and a parcel shown by itself goes back into the list: one chosen
    /// stays open over it.
    mutating func settle(_ arrangement: DeliveriesArrangement, cards: [UUID]) {
        switch arrangement {
        case .stacked:
            if shownAutomatically { path = [] }
            automaticPick = nil
        case .sideBySide:
            if let shown, !shownAutomatically || cards.contains(shown) { return }
            pick(cards.first)
        }
    }

    /// Opens the parcel that was open when the system last closed the app, once the parcels are back. True once
    /// that's settled: the parcel is open, it's gone, or someone opened another first. False while there are none yet.
    mutating func restore(_ parcelID: UUID, among parcels: [UUID]) -> Bool {
        if chosen != nil { return true }
        guard !parcels.isEmpty else { return false }
        if parcels.contains(parcelID) { open(parcelID) }
        return true
    }

    /// The parcel shown beside the list has left it, archived, brought back or deleted from its own page or
    /// deleted from its card, and the card after it shows instead: the one before it for the last, the first
    /// for one that had none. `cards` is the list as it was before the parcel left.
    mutating func leave(_ parcelID: UUID, cards: [UUID]) {
        guard shown == parcelID else { return }
        let index = cards.firstIndex(of: parcelID)
        let after = index.flatMap { cards[($0 + 1)...].first }
        let before = index.flatMap { cards[..<$0].last }
        pick(after ?? before ?? cards.first { $0 != parcelID })
    }

    /// Opens beside the list the card `offset` cards on from the parcel shown, in the list's `cards`, as ↑ and ↓
    /// do, and as a tap on that card would: from a parcel with no card, or none, the first card going down and the
    /// last going up. Returns the card, nil past either end, where the parcel shown stays.
    @discardableResult
    mutating func step(_ offset: Int, through cards: [UUID]) -> UUID? {
        let card: UUID?
        if let shown, let index = cards.firstIndex(of: shown) {
            card = cards.indices.contains(index + offset) ? cards[index + offset] : nil
        } else {
            card = offset > 0 ? cards.first : cards.last
        }
        if let card { open(card) }
        return card
    }

    /// The screen to count once the navigation has turned from `before` to this: a parcel someone opened, or
    /// the list they went back to. A parcel shown by itself is nobody's view, and beside the list, the list
    /// was there all along.
    func screen(after before: Self) -> String? {
        if let chosen { return chosen == before.chosen ? nil : "parcel" }
        return shown == nil && before.chosen != nil ? "deliveries" : nil
    }

    private mutating func pick(_ parcelID: UUID?) {
        path = parcelID.map { [$0] } ?? []
        automaticPick = parcelID
    }
}

/// A key the deliveries answer, besides the parcel's own and the refresh button's ⌘R: ⌘N adds, ⌘F finds, Escape
/// closes the search, ⌘Z undoes an archive while its toast shows, and beside a parcel, ↑ and ↓ open the card
/// before or after it and Return opens the one shown by itself.
enum DeliveriesKey: Hashable {
    case add, find, closeSearch, undo, previous, next, open

    /// The keys that act on what is in front. SwiftUI keeps every shortcut in the window, the list's under the
    /// parcel open over it and a tab's behind the tab chosen since, so there are none unless the deliveries are
    /// `free`: in front, with nothing open over the list or its parcel. While someone types in the search, the
    /// field keeps the keys it edits with, and Escape closes the search, which the field doesn't hear.
    static func available(
        free: Bool, searching: Bool, typing: Bool, arrangement: DeliveriesArrangement,
        navigation: DeliveriesNavigation, cards: [UUID], hasParcels: Bool, canUndo: Bool
    ) -> Set<Self> {
        guard free else { return [] }
        let listShown = arrangement == .sideBySide || navigation.chosen == nil
        var keys: Set<Self> = [.add]
        if listShown, hasParcels { keys.insert(.find) }
        if listShown, searching { keys.insert(.closeSearch) }
        guard !typing else { return keys }
        if listShown, canUndo { keys.insert(.undo) }
        if arrangement == .sideBySide, !cards.isEmpty { keys.formUnion([.previous, .next]) }
        if arrangement == .sideBySide, navigation.shownAutomatically { keys.insert(.open) }
        return keys
    }
}

/// What a parcel's menu offers: its page's •••, and its card's under a long press or a secondary click.
enum ParcelMenuItem: Hashable {
    case open, rename, share, copyTrackingNumber, changeCarrier, archive, restore, delete

    /// The items in groups that a line sets apart. A card's menu also opens the parcel, and shares it as the
    /// page's own button does. Every parcel in the list can be changed, in the demo and without an account as
    /// well: an archived one is brought back rather than archived. A `reader` follows someone else's parcel
    /// through its link: its page shares the link as it is, its carrier is not the reader's to change, and a
    /// number the link hides cannot be copied.
    static func menu(for parcel: Parcel, onCard: Bool, reader: Bool = false) -> [[Self]] {
        let changes: [Self] = reader
            ? [.rename] + (parcel.trackingNumber.isEmpty ? [] : [.copyTrackingNumber])
            : [.rename] + (onCard ? [.share] : []) + [.copyTrackingNumber, .changeCarrier]
        return (onCard ? [[.open]] : []) + [changes, [parcel.isArchived ? .restore : .archive], [.delete]]
    }

    var titleKey: String {
        switch self {
        case .open: "detail.open"
        case .rename: "detail.editTitle"
        case .share: "share.action"
        case .copyTrackingNumber: "detail.copyTracking"
        case .changeCarrier: "detail.changeCarrier"
        case .archive: "detail.archive"
        case .restore: "detail.restore"
        case .delete: "detail.delete"
        }
    }

    /// What a keyboard presses for it, as in Mail. It acts on the parcel shown, beside the list or over it.
    var shortcut: KeyboardShortcut? {
        switch self {
        case .copyTrackingNumber: KeyboardShortcut("c", modifiers: [.command, .shift])
        case .archive, .restore: KeyboardShortcut("a", modifiers: [.command, .control])
        case .delete: KeyboardShortcut(.delete, modifiers: .command)
        case .open, .rename, .share, .changeCarrier: nil
        }
    }

    var symbol: String {
        switch self {
        case .open: "shippingbox"
        case .rename: "pencil"
        case .share: "square.and.arrow.up"
        case .copyTrackingNumber: "doc.on.doc"
        case .changeCarrier: "truck.box"
        case .archive: "archivebox"
        case .restore: "arrow.uturn.backward"
        case .delete: "trash"
        }
    }
}

/// The card a parcel is shown on in the deliveries.
enum DeliveryCardKind: Equatable {
    case next, attention, onTheWay, past
}

/// Everything the Deliveries list shows, derived in one pass per render.
struct DeliveryListLayout {
    let visible: [Parcel]
    let active: [Parcel]
    let next: Parcel?
    let attention: [Parcel]
    let remaining: [Parcel]
    let sections: [ParcelSection]

    init(
        parcels: [Parcel],
        query: String,
        status: ParcelStatusFilter,
        carrier: CarrierID?,
        sort: ParcelSort,
        featuresNext: Bool,
        now: Date = Date(),
        catalog: CarrierCatalog = .shared
    ) {
        visible = ParcelOrganizer.visible(
            parcels, query: query, status: status, carrier: carrier, sort: sort, now: now, catalog: catalog
        )
        active = visible.filter(\.isActive)
        let next = featuresNext ? ParcelOrganizer.nextDelivery(from: active, now: now) : nil
        self.next = next
        let others = active.filter { $0.id != next?.id }.map { (parcel: $0, attention: $0.attention(now: now)) }
        attention = others.filter { $0.attention != nil }.map(\.parcel)
        remaining = others.filter { $0.attention == nil }.map(\.parcel)
        sections = ParcelOrganizer.sections(from: visible, now: now).filter {
            $0.kind == .delivered || $0.kind == .returned || $0.kind == .archived
        }
    }

    /// The parcels on cards, in the list's order, the archive left out.
    var cards: [UUID] {
        let past = sections.filter { $0.kind != .archived }.flatMap(\.parcels)
        return ([next].compactMap { $0 } + attention + remaining + past).map(\.id)
    }

    /// The parcels the list shows, in its order: the cards, then the archive's rows while they show, under a
    /// filter or in the opened shelf. Beside the list, the parcel shown by itself is one of these.
    func rows(archiveOpen: Bool) -> [UUID] {
        guard archiveOpen, let archive = sections.last(where: { $0.kind == .archived }) else { return cards }
        return cards + archive.parcels.map(\.id)
    }

    /// The card this parcel is shown on, outside the archive.
    func kind(of id: UUID) -> DeliveryCardKind? {
        if next?.id == id { return .next }
        if attention.contains(where: { $0.id == id }) { return .attention }
        if remaining.contains(where: { $0.id == id }) { return .onTheWay }
        let past = sections.contains { $0.kind != .archived && $0.parcels.contains { $0.id == id } }
        return past ? .past : nil
    }

    /// The parcels whose card is another once the list becomes `next`: those take their new place in front of the reader.
    func changing(into next: DeliveryListLayout) -> [UUID: (was: DeliveryCardKind, becomes: DeliveryCardKind)] {
        var changes: [UUID: (was: DeliveryCardKind, becomes: DeliveryCardKind)] = [:]
        for parcel in visible {
            guard let was = kind(of: parcel.id), let becomes = next.kind(of: parcel.id), was != becomes else { continue }
            changes[parcel.id] = (was, becomes)
        }
        return changes
    }
}
