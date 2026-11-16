import Foundation
#if os(iOS)
import ActivityKit
#endif

struct DeliveryWidgetParcel: Codable, Equatable, Hashable, Identifiable, Sendable {
    let id: UUID
    let label: String
    let carrier: String
    let trackingNumber: String
    /// When it is expected or arrived, as the app words it when it publishes; else its status.
    var detail: String
    let isOutForDelivery: Bool
    /// When a delivered parcel arrived: the widget keeps it for a day after.
    var deliveredAt: Date? = nil
    /// The parcel's card, once the app has drawn its pictures: the widget then shows the card rather than words alone.
    var card: DeliveryWidgetCard? = nil
    /// The parcel's stage as the app's list marks it, which the list widget shows; none from an app that didn't publish it.
    var stage: DeliveryWidgetStage? = nil
    /// Whether the reader named the parcel: the list widget calls one without a name by its carrier.
    var named: Bool? = nil
    /// The detail, section and Next up from each of the next midnights where one changes, which the app writes ahead:
    /// "tomorrow" becomes "today", under Arriving today, while the app stays closed. None from an app that didn't write
    /// them.
    var later: [DeliveryWidgetWording]? = nil
    /// The section of the app's list the parcel is in (`ParcelSectionKind`), which the Parcels widget's sections follow.
    var section: String? = nil
    /// Whether it is the app list's Next up, which leads the Parcels widget's cards and its section there.
    var nextUp: Bool? = nil
    /// The carrier shown, whose truck marks the parcel's row in the Parcels widget's sections.
    var carrierId: String? = nil
    /// Its last tracking steps, latest first, which the Parcels widget shows of a parcel alone in a large widget. Only
    /// the first `DeliveryWidgetSelection.limit` parcels carry them.
    var steps: [DeliveryWidgetStep]? = nil
    /// Whether the app's list marks it with the dot for updates since it was last opened (`showsNewUpdate`), which the
    /// Parcels widget shows too. None while Settings has the mark off, or from an app that didn't publish it.
    var newUpdate: Bool? = nil

    var deepLink: URL {
        URL(string: "peekdeliverytracker://parcel/\(id.uuidString)")!
    }
}

/// A parcel's detail from a moment on, and its section and whether it is Next up, where the app published them.
struct DeliveryWidgetWording: Codable, Equatable, Hashable, Sendable {
    let from: Date
    let detail: String
    var section: String? = nil
    var nextUp: Bool? = nil

    /// Whether it words the parcel as `other` does, from whichever moment.
    func says(as other: DeliveryWidgetWording) -> Bool {
        detail == other.detail && section == other.section && nextUp == other.nextUp
    }
}

/// A step of a parcel's tracking: when, the carrier's words as the app's history writes them, where, and its time as
/// the app writes a time.
struct DeliveryWidgetStep: Codable, Equatable, Hashable, Sendable {
    let at: Date
    let text: String
    var place: String? = nil
    /// Empty for a step the carrier dated by its day alone.
    var clock: String? = nil

    /// Its time as the Parcels widget writes it: the time alone on `now`'s day, else after the short weekday
    /// ("Thu 21:40", "jeu 21:40", "Do 21:40").
    func time(now: Date, locale: Locale, calendar: Calendar = .current) -> String {
        let clock = clock ?? at.formatted(Date.FormatStyle(date: .omitted, time: .shortened, timeZone: calendar.timeZone).locale(locale))
        guard !calendar.isDate(at, inSameDayAs: now) else { return clock }
        let formatter = DateFormatter()
        formatter.locale = locale
        formatter.calendar = calendar
        formatter.timeZone = calendar.timeZone
        formatter.dateFormat = "EEE"
        let weekday = formatter.string(from: at).replacingOccurrences(of: ".", with: "")
        return clock.isEmpty ? weekday : "\(weekday) \(clock)"
    }
}

/// The days the widgets' words follow while the app stays closed: "today", "tomorrow" and "yesterday" change at
/// midnight, and an estimate that has passed gives way to the status. The app list's sections and its Next up change
/// with them: a parcel due today is under Arriving today.
enum DeliveryWidgetDays {
    /// How many midnights ahead the app words the parcels.
    static let ahead = 7

    /// The local midnights after `now`.
    static func midnights(after now: Date, count: Int = ahead, calendar: Calendar = .current) -> [Date] {
        let today = calendar.startOfDay(for: now)
        return (1...max(count, 1)).compactMap { calendar.date(byAdding: .day, value: $0, to: today).map(calendar.startOfDay(for:)) }
    }

    /// The detail's words at each of `midnights` where they change from those before.
    static func later(than detail: String, at midnights: [Date], _ words: (Date) -> String) -> [DeliveryWidgetWording]? {
        later(than: DeliveryWidgetWording(from: .distantPast, detail: detail), at: midnights) {
            DeliveryWidgetWording(from: $0, detail: words($0))
        }
    }

    /// The parcel's wording at each of `midnights` where it changes from the one before.
    static func later(
        than first: DeliveryWidgetWording, at midnights: [Date], _ wording: (Date) -> DeliveryWidgetWording
    ) -> [DeliveryWidgetWording]? {
        var last = first
        let changes = midnights.compactMap { midnight -> DeliveryWidgetWording? in
            let next = wording(midnight)
            guard !next.says(as: last) else { return nil }
            last = next
            return next
        }
        return changes.isEmpty ? nil : changes
    }

    /// When the parcels' words change after `now`, and the midnight after each of their steps, from which a step's time
    /// names its weekday: a widget's timeline has an entry at each.
    static func changes(in parcels: [DeliveryWidgetParcel], after now: Date, calendar: Calendar = .current) -> [Date] {
        let worded = parcels.flatMap { $0.later ?? [] }.map(\.from)
        let stepped = parcels.flatMap { $0.steps ?? [] }.compactMap { midnights(after: $0.at, count: 1, calendar: calendar).first }
        return Set((worded + stepped).filter { $0 > now }).sorted()
    }
}

/// A parcel's stage as a row of the app's list shows it: the status in the app's words, and the symbol and colour that
/// mark it, the colour light then dark as hex.
struct DeliveryWidgetStage: Codable, Equatable, Hashable, Sendable {
    let status: String
    let symbol: String
    let tint: [String]
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

/// A carrier's truck, which marks its parcels' rows in the Parcels widget. A widget has too little memory for the
/// carriers' liveries, so the app draws the truck of each carrier the widget lists into the shared container.
enum DeliveryWidgetTruck {
    /// The truck's size in points, as the app's cards draw it, and its pictures' pixels per point.
    static let size = CGSize(width: 27, height: 18)
    static let scale: CGFloat = 3

    static func folder(appGroupIdentifier: String) -> URL? {
        FileManager.default.containerURL(forSecurityApplicationGroupIdentifier: appGroupIdentifier)?
            .appending(path: "Library/Caches/WidgetTrucks", directoryHint: .isDirectory)
    }

    static func file(of carrier: String, dark: Bool, in folder: URL) -> URL {
        folder.appending(path: "\(carrier)-\(dark ? "dark" : "light").png")
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
    /// The picture of each shape a sample card shows in: the first card alone in a small widget, both in a medium one,
    /// and both across a large one.
    static func pictures(of index: Int) -> [DeliveryWidgetPicture] { index == 0 ? [.small, .half, .wide] : [.half, .wide] }

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
    /// How many parcels the widget's next button turns through.
    static let limit = 8
    /// How long a delivered parcel stays in the widget.
    static let deliveredFor: TimeInterval = 24 * 60 * 60
    /// How many of a parcel's last steps the Parcels widget shows of it alone, which the first `limit` parcels carry.
    static let steps = 4

    /// The parcels the widgets show, in their order: those out for delivery, then the others on their way as `onTheWay`
    /// orders them, then those delivered within the last day, as `delivered` orders them. The next-delivery widget turns
    /// through the first `limit`; the app publishes them all, so that the list widget can count those it has no room for.
    static func parcels(
        onTheWay: [DeliveryWidgetParcel],
        delivered: [DeliveryWidgetParcel],
        now: Date = Date(),
        limit: Int = limit
    ) -> [DeliveryWidgetParcel] {
        let recent = delivered.filter { isShown($0, now: now) && $0.deliveredAt != nil }
        return Array((onTheWay.filter(\.isOutForDelivery) + onTheWay.filter { !$0.isOutForDelivery } + recent).prefix(limit))
    }

    /// What a widget shows at a time, first page first, worded for `now`. A small one shows one parcel. A medium one
    /// shows a parcel out for delivery beside the parcel after it, and any other parcel alone, across it. It turns
    /// through the first `limit`.
    static func pages(of parcels: [DeliveryWidgetParcel], medium: Bool, now: Date = Date()) -> [[DeliveryWidgetParcel]] {
        var rest = parcels.prefix(limit).filter { isShown($0, now: now) }.map { $0.worded(at: now) }[...]
        var pages: [[DeliveryWidgetParcel]] = []
        while let first = rest.popFirst() {
            if medium, first.isOutForDelivery, let second = rest.popFirst() {
                pages.append([first, second])
            } else {
                pages.append([first])
            }
        }
        return pages
    }

    /// The parcels the list widget shows, in their order, worded for `now`.
    static func listed(_ parcels: [DeliveryWidgetParcel], now: Date = Date()) -> [DeliveryWidgetParcel] {
        parcels.filter { isShown($0, now: now) }.map { $0.worded(at: now) }
    }

    /// A delivered parcel leaves the widget a day after it arrived, whenever the app last published it.
    private static func isShown(_ parcel: DeliveryWidgetParcel, now: Date) -> Bool {
        parcel.deliveredAt.map { now.timeIntervalSince($0) < deliveredFor } ?? true
    }
}

/// The Parcels widget: the widgets' parcels as cards, passes, or rows under the app list's sections, by how many there
/// are, and a last line that counts those left out.
enum DeliveryWidgetList {
    /// Opens the deliveries.
    static let link = URL(string: "peekdeliverytracker://parcels")!

    enum Size: CaseIterable, Sendable {
        case small, medium, large

        /// More rows than each size could hold in its smallest type, which bounds the layouts the widget measures.
        var mostRows: Int {
            switch self {
            case .small: 6
            case .medium: 12
            case .large: 24
            }
        }

        /// The cards each size holds whole: one; two side by side; three one above another.
        var cards: Int {
            switch self {
            case .small: 1
            case .medium: 2
            case .large: 3
            }
        }
    }

    /// How the widget shows its parcels.
    enum Design: Equatable, Sendable {
        /// The Next delivery widget's cards, the app list's Next up first.
        case cards
        /// A pass for each parcel, in its carrier's colours.
        case passes
        /// Rows under the app list's sections, each marked with its carrier's truck.
        case sections
    }

    /// The design for `count` parcels: up to `size.cards` of them as cards, up to twice as many as passes, more as rows
    /// under the sections. Each shows every parcel whole up to there: a large widget holds three cards and six passes,
    /// one above another.
    static func design(of count: Int, size: Size) -> Design {
        count <= size.cards ? .cards : count <= size.cards * 2 ? .passes : .sections
    }

    /// The parcels as the cards show them: the app list's Next up first, then the widgets' order.
    static func cardOrder(_ parcels: [DeliveryWidgetParcel]) -> [DeliveryWidgetParcel] {
        parcels.filter { $0.nextUp == true } + parcels.filter { $0.nextUp != true }
    }

    /// The app list's sections, in its order (`ParcelSectionKind`).
    static let sectionKinds = ["attention", "today", "active", "delivered", "returned", "archived"]

    /// A section of the app's list and the parcels under it; of none where the app didn't publish the sections.
    struct Section: Equatable, Sendable {
        let kind: String?
        let parcels: [DeliveryWidgetParcel]
    }

    /// The parcels under the app list's sections, in its order, each with its Next up first, then the widgets' order;
    /// under none, in the widgets' order, where one has no section, as from an app that didn't publish them.
    static func sections(_ parcels: [DeliveryWidgetParcel]) -> [Section] {
        guard !parcels.isEmpty else { return [] }
        guard parcels.allSatisfy({ $0.section.map(sectionKinds.contains) ?? false }) else { return [Section(kind: nil, parcels: parcels)] }
        return sectionKinds.compactMap { kind in
            let under = parcels.filter { $0.section == kind }
            return under.isEmpty ? nil : Section(kind: kind, parcels: cardOrder(under))
        }
    }

    /// A line of the sections' design.
    enum Line: Hashable, Sendable {
        /// A section's kind and how many parcels it has.
        case heading(String, count: Int)
        case row(DeliveryWidgetParcel)
        /// How many parcels are left out.
        case more(Int)

        /// About how tall the line is, against a row's.
        var weight: Double {
            switch self {
            case .heading: 0.6
            case .row: 1
            case .more: 0.5
            }
        }
    }

    /// How many rows the sections' design shows, and in which type.
    struct Layout: Hashable, Sendable {
        let rows: Int
        /// In smaller type.
        let compact: Bool
        /// In a medium widget, down one column with each row on one line, as in a large widget, and without the headings,
        /// as in a small one, rather than in two columns.
        var oneColumn = false
    }

    /// The layouts a widget tries for `count` parcels, the first that fits being the one it shows: the most rows first,
    /// each in the usual type, then in a small or medium widget in smaller type, as more rows win over larger type. The
    /// rows then show every parcel, or the room under the last is shorter than another row.
    static func layouts(count: Int, size: Size) -> [Layout] {
        stride(from: min(size.mostRows, count), through: 1, by: -1).flatMap { rows in
            [Layout(rows: rows, compact: false)] + (size == .large ? [] : [Layout(rows: rows, compact: true)])
        }
    }

    /// What the widget shows its parcels as.
    enum Fit: Hashable, Sendable {
        /// Every parcel's pass.
        case passes
        case rows(Layout)
    }

    /// What a widget of `design` tries for `count` parcels, the first that fits being the one it shows: every parcel's
    /// pass, for the passes' design, then the most rows that fit (`layouts`). Passes show every parcel or none: where two
    /// cannot stand one above the other, in the shorter widgets of most iPhones or at larger text, the parcels are rows.
    /// In a medium widget, where every parcel's row doesn't fit in two columns either, they try one column in smaller
    /// type before leaving one out.
    static func fits(_ design: Design, count: Int, size: Size) -> [Fit] {
        let rows = layouts(count: count, size: size)
        guard design == .passes else { return rows.map(Fit.rows) }
        let every = rows.prefix { $0.rows == count }
        let oneColumn = size == .medium && !every.isEmpty ? [Layout(rows: count, compact: true, oneColumn: true)] : []
        return [.passes] + (every + oneColumn + rows.dropFirst(every.count)).map(Fit.rows)
    }

    /// The first `rows` parcels under their sections' headings, if the widget has room for them, then a line that counts
    /// the others.
    static func lines(_ sections: [Section], rows: Int, headings: Bool) -> [Line] {
        var lines: [Line] = []
        var left = rows
        for section in sections where left > 0 {
            if headings, let kind = section.kind { lines.append(.heading(kind, count: section.parcels.count)) }
            lines += section.parcels.prefix(left).map(Line.row)
            left -= min(left, section.parcels.count)
        }
        let more = sections.reduce(0) { $0 + $1.parcels.count } - (rows - left)
        return more > 0 ? lines + [.more(more)] : lines
    }

    /// A medium widget's two columns, about as tall as each other, read down the first then the second. A heading never
    /// ends the first; the line that counts the parcels left out always ends the second, as they would follow its last
    /// row. A single row and its heading take one column.
    static func columns(_ lines: [Line]) -> [[Line]] {
        let total = lines.reduce(0) { $0 + $1.weight }
        var split = 0
        var height = 0.0
        while split < lines.count - 1, height + lines[split].weight / 2 < total / 2 {
            height += lines[split].weight
            split += 1
        }
        if split > 0, case .heading = lines[split - 1] { split -= 1 }
        return split == 0 ? [lines] : [Array(lines.prefix(split)), Array(lines.dropFirst(split))]
    }
}

extension String {
    /// The words as they open a line: a day the app writes to follow a status, "tomorrow", takes a capital there.
    func openingLine(in locale: Locale) -> String {
        prefix(1).uppercased(with: locale) + dropFirst()
    }
}

extension DeliveryWidgetParcel {
    /// The parcel as the widgets word it at `date`: as the app wrote it for the last midnight before it.
    func worded(at date: Date) -> DeliveryWidgetParcel {
        guard let wording = later?.last(where: { $0.from <= date }) else { return self }
        var parcel = self
        parcel.detail = wording.detail
        parcel.section = wording.section ?? section
        parcel.nextUp = wording.nextUp ?? nextUp
        return parcel
    }

    /// The name the list widget gives the parcel: its own, else its carrier's.
    var listName: String { named == false ? carrier : label }

    /// The parcel's stage, and the day it is expected or arrived when the app knows one. Published by an app that
    /// didn't publish the stage, the parcel reads as its detail.
    var listLine: (status: String, day: String?) {
        guard let stage else { return (detail, nil) }
        return (stage.status, detail == stage.status ? nil : detail)
    }
}

/// A widget turned to other parcels with its next button: the first of them, and when. It shows its first parcels again
/// a few minutes later, as its job is the next delivery.
struct DeliveryWidgetTurn: Codable, Equatable, Sendable {
    let parcel: UUID
    let at: Date

    static let spell: TimeInterval = 5 * 60

    var ends: Date { at.addingTimeInterval(Self.spell) }
}

struct DeliveryWidgetSharedStore {
    static let kind = "NextDeliveryWidget"
    static let listKind = "ParcelListWidget"
    /// The widgets that read the snapshot.
    static let kinds = [kind, listKind]
    static let defaultAppGroupIdentifier = "group.com.plhery.PeekDeliveryTracker"

    private static let enabledKey = "sdt.deliveryWidget.enabled.v1"
    private static let liveActivitiesEnabledKey = "sdt.deliveryLiveActivities.enabled.v1"
    private static let languageKey = "sdt.deliveryWidget.language.v1"
    private static let snapshotKey = "sdt.deliveryWidget.snapshot.v1"
    private static let turnsKey = "sdt.deliveryWidget.turns.v1"
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

    /// Where the widgets of one size were turned to, until the turn's spell ends.
    func turn(size: String, now: Date = Date()) -> DeliveryWidgetTurn? {
        turns[size].flatMap { now < $0.ends ? $0 : nil }
    }

    func setTurn(_ turn: DeliveryWidgetTurn, size: String) {
        var turns = turns
        turns[size] = turn
        guard let data = try? JSONEncoder().encode(turns) else { return }
        defaults.set(data, forKey: Self.turnsKey)
    }

    private var turns: [String: DeliveryWidgetTurn] {
        guard let data = defaults.data(forKey: Self.turnsKey) else { return [:] }
        return (try? JSONDecoder().decode([String: DeliveryWidgetTurn].self, from: data)) ?? [:]
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
