import Foundation
#if os(iOS)
import ActivityKit
#endif

struct DeliveryWidgetParcel: Codable, Equatable, Hashable, Identifiable, Sendable {
    let id: UUID
    let label: String
    let carrier: String
    let trackingNumber: String
    let detail: String
    let isOutForDelivery: Bool
    /// The parcel's card, once the app has drawn its pictures: the widget then shows the card rather than words alone.
    var card: DeliveryWidgetCard? = nil

    var deepLink: URL {
        URL(string: "peekdeliverytracker://parcel/\(id.uuidString)")!
    }
}

/// A parcel's card as the widget shows it. A widget has too little memory to draw the map, so the app draws the card's
/// pictures into the shared container and the widget writes the words over them.
struct DeliveryWidgetCard: Codable, Equatable, Hashable, Sendable {
    /// The card's colour and its words', light then dark, as hex.
    let surface: [String]
    let ink: [String]
    /// The parcel's status, which the card writes before its date.
    let status: String
    /// What the pictures' file names start with. It changes whenever they do.
    let pictures: String
    /// The pictures' pixels per point.
    let scale: Double
}

/// The pictures of a card: its carrier's mark, and its route as a small widget, half a medium one or a whole medium one
/// shows it, across the top of the card.
enum DeliveryWidgetPicture: String, CaseIterable, Sendable {
    case mark, small, half, wide

    static let routes: [DeliveryWidgetPicture] = [.small, .half, .wide]

    static func folder(appGroupIdentifier: String) -> URL? {
        FileManager.default.containerURL(forSecurityApplicationGroupIdentifier: appGroupIdentifier)?
            .appending(path: "Library/Caches/WidgetCards", directoryHint: .isDirectory)
    }

    func file(of card: DeliveryWidgetCard, dark: Bool, in folder: URL) -> URL {
        folder.appending(path: "\(card.pictures)-\(rawValue)-\(dark ? "dark" : "light").png")
    }
}

struct DeliveryWidgetSnapshot: Codable, Equatable, Sendable {
    let generatedAt: Date
    let languageCode: String
    let parcels: [DeliveryWidgetParcel]
}

/// What the widget gallery shows until the app has drawn the reader's own cards: the demo's next deliveries as the app
/// publishes them in each language, with the pictures the app draws for them. The widget bundles them in this folder;
/// the app's tests check them against the demo, and redraw them into the folder `PEEK_WIDGET_SAMPLE` names.
enum DeliveryWidgetSample {
    static let folderName = "WidgetSample"
    static let snapshotsFile = "snapshots.json"
    /// The picture of each shape a sample card shows in: the first card alone in a small widget, both in a medium one.
    static func pictures(of index: Int) -> [DeliveryWidgetPicture] { index == 0 ? [.small, .half] : [.half] }

    static func snapshot(languageCode: String, in folder: URL) -> DeliveryWidgetSnapshot? {
        guard let data = try? Data(contentsOf: folder.appending(path: snapshotsFile)),
              let snapshots = try? JSONDecoder().decode([String: DeliveryWidgetSnapshot].self, from: data) else { return nil }
        return snapshots[languageCode] ?? snapshots["en"]
    }
}

enum DeliveryActivityPhase: String, Codable, Hashable, Sendable {
    case outForDelivery = "out_for_delivery"
    case delivered
    case failedAttempt = "failed_attempt"
    case readyForPickup = "ready_for_pickup"
    case returned
    case exception
    case ended
}

struct DeliveryActivityParcel: Codable, Equatable, Hashable, Identifiable, Sendable {
    let id: UUID
    let label: String
    let carrier: String
    let status: String
    let detail: String
    let phase: DeliveryActivityPhase

    var deepLink: URL {
        URL(string: "peekdeliverytracker://parcel/\(id.uuidString)")!
    }
}

// Live Activities are the iPhone's and iPad's: ActivityKit imports on macOS but has none.
#if os(iOS)
struct DeliveryActivityAttributes: ActivityAttributes {
    struct ContentState: Codable, Hashable {
        let parcel: DeliveryActivityParcel
        let languageCode: String
    }

    let parcelID: UUID
}
#endif

enum DeliveryWidgetSelection {
    static func displayParcels(
        from priorityOrderedCandidates: [DeliveryWidgetParcel],
        limit: Int = 2
    ) -> [DeliveryWidgetParcel] {
        guard limit > 0 else { return [] }
        let outForDelivery = priorityOrderedCandidates.filter(\.isOutForDelivery)
        if outForDelivery.count >= limit {
            return Array(outForDelivery.prefix(limit))
        }
        guard let firstOutForDelivery = outForDelivery.first else {
            return Array(priorityOrderedCandidates.prefix(1))
        }
        guard limit > 1,
              let nextUp = priorityOrderedCandidates.first(where: { !$0.isOutForDelivery }) else {
            return [firstOutForDelivery]
        }
        return [firstOutForDelivery, nextUp]
    }
}

struct DeliveryWidgetSharedStore {
    static let kind = "NextDeliveryWidget"
    static let defaultAppGroupIdentifier = "group.com.plhery.PeekDeliveryTracker"

    private static let enabledKey = "sdt.deliveryWidget.enabled.v1"
    private static let liveActivitiesEnabledKey = "sdt.deliveryLiveActivities.enabled.v1"
    private static let languageKey = "sdt.deliveryWidget.language.v1"
    private static let snapshotKey = "sdt.deliveryWidget.snapshot.v1"
    private let defaults: UserDefaults

    init?(appGroupIdentifier: String, fallbackToStandard: Bool = false) {
        if FileManager.default.containerURL(
            forSecurityApplicationGroupIdentifier: appGroupIdentifier
        ) != nil,
           let defaults = UserDefaults(suiteName: appGroupIdentifier) {
            self.defaults = defaults
        } else if fallbackToStandard {
            // Personal Team builds cannot carry App Group entitlements. Keeping the
            // preference local still lets their Live Activity opt-out work.
            defaults = .standard
        } else {
            return nil
        }
    }

    init(defaults: UserDefaults) {
        self.defaults = defaults
    }

    var isEnabled: Bool {
        defaults.object(forKey: Self.enabledKey) == nil
            || defaults.bool(forKey: Self.enabledKey)
    }

    var languageCode: String? {
        defaults.string(forKey: Self.languageKey)
    }

    var liveActivitiesEnabled: Bool {
        defaults.object(forKey: Self.liveActivitiesEnabledKey) == nil
            ? isEnabled
            : defaults.bool(forKey: Self.liveActivitiesEnabledKey)
    }

    var snapshot: DeliveryWidgetSnapshot? {
        guard let data = defaults.data(forKey: Self.snapshotKey) else { return nil }
        return try? JSONDecoder().decode(DeliveryWidgetSnapshot.self, from: data)
    }

    func setEnabled(_ enabled: Bool) {
        defaults.set(enabled, forKey: Self.enabledKey)
        if !enabled { clearSnapshot() }
    }

    func setLiveActivitiesEnabled(_ enabled: Bool) {
        defaults.set(enabled, forKey: Self.liveActivitiesEnabledKey)
    }

    func setLanguageCode(_ languageCode: String) {
        defaults.set(languageCode, forKey: Self.languageKey)
    }

    @discardableResult
    func save(_ snapshot: DeliveryWidgetSnapshot) -> Bool {
        guard let data = try? JSONEncoder().encode(snapshot) else { return false }
        defaults.set(data, forKey: Self.snapshotKey)
        return true
    }

    func clearSnapshot() {
        defaults.removeObject(forKey: Self.snapshotKey)
    }

    static func appGroupIdentifier(bundle: Bundle = .main) -> String {
        let configured = bundle.object(forInfoDictionaryKey: "SDTAppGroupIdentifier") as? String
        guard let configured,
              !configured.isEmpty,
              !configured.contains("$(") else {
            return defaultAppGroupIdentifier
        }
        return configured
    }
}
