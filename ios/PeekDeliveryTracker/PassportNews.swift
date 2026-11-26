import SwiftUI

/// What the passport has that this device has not shown yet: the stamps earned since the reader last opened it. The
/// seen ones are kept on this device, one list per account, one for a guest and one for the demo; the first time a
/// passport is read here, everything it already holds counts as seen, so only what arrives after that is new. The
/// web's `usePassportNews`; countries join in a later round.
@MainActor
final class PassportNews: ObservableObject {
    struct Item: Identifiable, Equatable {
        let stamp: PassportBook.StampProgress
        var seen: String { stamp.seenKey }
        var parcelID: UUID? { stamp.parcelID }
        var id: String { seen }
    }

    static let prefix = "peek-passport-seen:"

    @Published private(set) var book = PassportBook(parcels: [])
    /// Earned and not shown yet, in the passport's order.
    @Published private(set) var unseen: [Item] = []
    private var seen: Set<String>?
    private var scope: String?
    private var loaded = false
    private var parcels: [Parcel]?
    private let defaults: UserDefaults
    private let timeZone: () -> TimeZone
    /// The lines on cards that have opened already, so a card scrolled back into view does not open its line again.
    private(set) var openedLines: Set<String> = []

    init(defaults: UserDefaults = .standard, timeZone: @escaping () -> TimeZone = { .current }) {
        self.defaults = defaults
        self.timeZone = timeZone
    }

    /// The unseen ones each delivered parcel brought.
    var byParcel: [UUID: [Item]] {
        unseen.reduce(into: [:]) { result, item in if let parcel = item.parcelID { result[parcel, default: []].append(item) } }
    }

    /// The seen list's scope: an account's id, the guest's or the demo's. Nil while nobody is known yet.
    static func scope(_ state: AuthenticationState) -> String? {
        switch state {
        case .signedIn(let user): user.id.uuidString.lowercased()
        case .welcome: "guest"
        case .demo: "demo"
        default: nil
        }
    }

    /// The parcels, whose they are, and whether their list has loaded: what was earned is new only against a list read
    /// for this scope.
    func update(parcels: [Parcel], scope: String?, loaded: Bool) {
        if parcels != self.parcels {
            self.parcels = parcels
            book = PassportBook(parcels: parcels, timeZone: timeZone())
        }
        if scope != self.scope {
            self.scope = scope
            seen = scope.flatMap(read)
        }
        self.loaded = loaded && scope != nil
        // A passport read here for the first time starts with everything it holds already seen.
        if self.loaded, seen == nil { markSeen() }
        refresh()
    }

    /// Everything earned now counts as seen.
    func markSeen() {
        guard loaded, let scope else { return }
        let keys = Set(earned.map(\.seen))
        let value = keys.sorted()
        if seen != keys {
            if let data = try? JSONEncoder().encode(value), let text = String(data: data, encoding: .utf8) {
                defaults.set(text, forKey: Self.prefix + scope)
            }
            seen = keys
        }
        refresh()
    }

    func noteLineOpened(_ key: String) { openedLines.insert(key) }

    private var earned: [Item] { book.stamps.filter(\.earned).map(Item.init) }

    private func read(_ scope: String) -> Set<String>? {
        guard let text = defaults.string(forKey: Self.prefix + scope), let data = text.data(using: .utf8),
              let keys = try? JSONDecoder().decode([String].self, from: data) else { return nil }
        return Set(keys)
    }

    private func refresh() {
        let next = seen.map { seen in earned.filter { !seen.contains($0.seen) } } ?? []
        if next != unseen { unseen = next }
    }
}

private struct OpenPassportKey: EnvironmentKey {
    static let defaultValue: () -> Void = {}
}

extension EnvironmentValues {
    /// Brings the Passport tab forward, as a "New stamp" line asks.
    var openPassport: () -> Void {
        get { self[OpenPassportKey.self] }
        set { self[OpenPassportKey.self] = newValue }
    }
}

// MARK: - Words

/// Each stamp's name and what earns it, the day it was earned and how far an unearned one has come, as the web writes them.
@MainActor
struct PassportStampCopy {
    let localizer: Localizer

    private static let keys: [String: (title: String, explanation: String)] = [
        "first": ("passport.firstArrival", "passport.firstExplanation"),
        "rightNextDoor": ("passport.rightNextDoor", "passport.domesticExplanation"),
        "express": ("passport.expressArrival", "passport.expressExplanation"),
        "familiarFace": ("passport.familiarFace", "passport.familiarExplanation"),
        "pickedUp": ("passport.pickedUp", "passport.pickupExplanation"),
        "acrossBorders": ("passport.acrossBorders", "passport.acrossExplanation"),
        "connected": ("passport.wellConnected", "passport.carrierExplanation"),
        "secondTry": ("passport.secondTry", "passport.secondTryExplanation"),
        "worthTheWait": ("passport.worthTheWait", "passport.waitExplanation"),
        "aroundWorld": ("passport.aroundWorld", "passport.aroundExplanation"),
        "newHorizons": ("passport.newHorizons", "passport.horizonsExplanation"),
        "ten": ("passport.doubleDigits", "passport.tenExplanation"),
        "fourSeasons": ("passport.fourSeasons", "passport.seasonsExplanation"),
        "theRegular": ("passport.theRegular", "passport.regularExplanation"),
        "busyDoorstep": ("passport.busyDoorstep", "passport.busyExplanation"),
        "homeForHolidays": ("passport.homeForHolidays", "passport.holidayExplanation"),
        "tenCountries": ("passport.tenCountries", "passport.tenCountriesExplanation"),
        "firstAnniversary": ("passport.firstAnniversary", "passport.anniversaryExplanation"),
        "fifty": ("passport.fifty", "passport.fiftyExplanation"),
    ]

    static func hasCopy(_ key: String) -> Bool { keys[key] != nil }

    func title(_ key: String) -> String { localizer.text(Self.keys[key]?.title ?? "passport.stamps") }
    func explanation(_ key: String) -> String { localizer.text(Self.keys[key]?.explanation ?? "passport.notEarned") }

    /// How far an unearned stamp has come: "3 of 5", or "Not yet" for one a single parcel earns.
    func progress(_ stamp: PassportBook.StampProgress) -> String {
        stamp.total > 1
            ? localizer.text("passport.progress", ["count": min(stamp.count, stamp.total), "total": stamp.total])
            : localizer.text("passport.notYet")
    }

    /// "Earned on 8 November 2026", or nil for an earned stamp whose deliveries are not all dated.
    func earnedOn(_ stamp: PassportBook.StampProgress) -> String? {
        stamp.earnedAt.map { localizer.text("passport.earnedOn", ["date": PassportDates.fullDay($0, language: localizer.language)]) }
    }

    /// What is written under a seal: the day it was earned, or its progress.
    func under(_ stamp: PassportBook.StampProgress, now: Date = Date()) -> String? {
        stamp.earned ? stamp.earnedAt.map { PassportDates.day($0, language: localizer.language, now: now) } : progress(stamp)
    }

    /// A stamp's details: what earns it, then the day and the parcel that earned it, or its progress.
    func details(_ stamp: PassportBook.StampProgress) -> String {
        let explanation = explanation(stamp.key)
        if stamp.earned {
            guard let earnedOn = earnedOn(stamp) else { return explanation }
            return "\(explanation)\n\(earnedOn) · \(stamp.label?.nonEmpty ?? localizer.text("common.parcel"))"
        }
        return "\(explanation)\n\(progress(stamp))"
    }

    /// What a screen reader says of a seal: "Well connected, Earned on 5 September 2026, New".
    func accessibilityLabel(_ stamp: PassportBook.StampProgress, fresh: Bool) -> String {
        [title(stamp.key), stamp.earned ? earnedOn(stamp) : progress(stamp), fresh ? localizer.text("passport.new") : nil]
            .compactMap { $0 }.joined(separator: ", ")
    }

    /// "New stamp", or "3 new stamps".
    func newsHeading(_ count: Int) -> String {
        count == 1 ? localizer.text("passport.newStamp") : localizer.text("passport.newStamps", ["count": count])
    }
}

/// The passport's dates, in the language's own words as the web writes them with its language tags (`en-CH`, `de-CH`…).
enum PassportDates {
    private static var formatters: [String: DateFormatter] = [:]
    private static let lock = NSLock()

    /// The locale the web writes the passport's days and times in: Swiss English, German, French and Italian.
    static func locale(_ language: AppLanguage) -> Locale {
        switch language {
        case .en: Locale(identifier: "en_CH")
        case .de: Locale(identifier: "de_CH")
        case .fr: Locale(identifier: "fr_CH")
        case .it: Locale(identifier: "it_CH")
        default: language.locale
        }
    }

    private static func formatter(_ template: String, language: AppLanguage, timeZone: TimeZone) -> DateFormatter {
        lock.lock(); defer { lock.unlock() }
        let key = "\(language.rawValue)|\(timeZone.identifier)|\(template)"
        if let formatter = formatters[key] { return formatter }
        let formatter = DateFormatter()
        formatter.locale = locale(language)
        formatter.calendar = Calendar(identifier: .gregorian)
        formatter.timeZone = timeZone
        formatter.setLocalizedDateFormatFromTemplate(template)
        formatters[key] = formatter
        return formatter
    }

    /// The day a stamp was earned, as it is written under it: "12 Aug", with the year when the app's dates want it.
    static func day(_ date: Date, language: AppLanguage, now: Date = Date(), timeZone: TimeZone = .current) -> String {
        formatter(Localizer.showsYear(date, now: now) ? "dMMMy" : "dMMM", language: language, timeZone: timeZone).string(from: date)
    }

    /// The day in full, for the stamp's details and a screen reader: "12 August 2026".
    static func fullDay(_ date: Date, language: AppLanguage, timeZone: TimeZone = .current) -> String {
        formatter("dMMMMy", language: language, timeZone: timeZone).string(from: date)
    }

    /// A country stamp's day, as its ink carries it: "12 AUG 2026".
    static func inkDay(_ date: Date, language: AppLanguage, timeZone: TimeZone = .current) -> String {
        formatter("dMMMy", language: language, timeZone: timeZone).string(from: date)
            .replacingOccurrences(of: ".", with: "").uppercased(with: locale(language))
    }
}

// MARK: - The line on a delivered card

/// The one line a delivered parcel shows under its card while what it earned is not seen yet; it opens the passport.
/// `tucked`: it sits under a card, its top behind the card's lower edge; an archived row has it on its own.
struct NewStampLine: View {
    let parcelID: UUID
    let items: [PassportNews.Item]
    var tucked = true

    @EnvironmentObject private var localizer: Localizer
    @EnvironmentObject private var news: PassportNews
    @Environment(\.openPassport) private var openPassport
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @State private var open = false
    @State private var risen = false
    @State private var popped = false
    @State private var shown = true
    @State private var natural: CGFloat = 52

    private var tuck: CGFloat { tucked ? 18 : 0 }

    var body: some View {
        let copy = PassportStampCopy(localizer: localizer)
        let heading = copy.newsHeading(items.count)
        let names = items.map { copy.title($0.stamp.key) }
        let tone = items.first?.stamp.stamp.tone ?? .green
        Button(action: openPassport) {
            HStack(spacing: 10) {
                if let first = items.first {
                    PassportSeal(stamp: first.stamp.stamp, size: .mini)
                        .scaleEffect(popped ? 1 : 0.6)
                        .opacity(popped ? 1 : 0)
                        .padding(.leading, 8)
                }
                VStack(alignment: .leading, spacing: 1) {
                    Text(heading).font(.footnote.weight(.semibold)).foregroundStyle(Brand.ink)
                    Text(names.joined(separator: " · "))
                        .font(.caption)
                        .foregroundStyle(Brand.secondaryText)
                        .lineLimit(1)
                        .truncationMode(.tail)
                }
                .padding(.vertical, 7)
                .offset(y: risen ? 0 : 8)
                .opacity(risen ? 1 : 0)
                .frame(maxWidth: .infinity, alignment: .leading)
                Image(systemName: "chevron.right")
                    .font(.caption.weight(.semibold))
                    .foregroundStyle(Brand.secondaryText)
                    .padding(.trailing, 10)
                    .accessibilityHidden(true)
            }
            .frame(minHeight: 44)
            .background(Brand.paper, in: RoundedRectangle(cornerRadius: 12))
            .padding(.top, tuck + 8)
            .padding([.horizontal, .bottom], 8)
            .fixedSize(horizontal: false, vertical: true)
            .onGeometryChange(for: CGFloat.self) { $0.size.height } action: { natural = $0 }
            .frame(height: open ? natural : tuck, alignment: .top)
            .clipped()
            .background(tone.paper, in: UnevenRoundedRectangle(topLeadingRadius: tucked ? 0 : 12, bottomLeadingRadius: tucked ? 18 : 12,
                                                               bottomTrailingRadius: tucked ? 18 : 12, topTrailingRadius: tucked ? 0 : 12))
            .contentShape(Rectangle())
            .opacity(shown ? 1 : 0)
        }
        .buttonStyle(PassportPressStyle())
        .accessibilityLabel("\(heading): \(names.joined(separator: ", "))")
        .onAppear(perform: appear)
    }

    /// Opens 150 ms after the card has settled: the row in 260 ms, its words rising 80 ms later, the mini seal popping
    /// 120 ms after that. With Reduce Motion it fades in at its full height.
    private func appear() {
        let key = "\(parcelID)|\(items.map(\.seen).joined(separator: ","))"
        if news.openedLines.contains(key) {
            open = true; risen = true; popped = true
            return
        }
        news.noteLineOpened(key)
        if reduceMotion {
            open = true; risen = true; popped = true; shown = false
            withAnimation(.linear(duration: 0.2)) { shown = true }
            return
        }
        withAnimation(PassportMotion.move.animation(260, delay: 150)) { open = true }
        withAnimation(PassportMotion.settle.animation(300, delay: 230)) { risen = true }
        withAnimation(PassportMotion.settle.animation(220, delay: 350)) { popped = true }
    }
}

extension View {
    /// A delivered or archived parcel's card with its "New stamp" line under it, while it has one.
    func passportNews(_ items: [PassportNews.Item]?, parcelID: UUID, tucked: Bool = true) -> some View {
        VStack(spacing: tucked ? -18 : 4) {
            self.zIndex(1)
            if let items, !items.isEmpty {
                NewStampLine(parcelID: parcelID, items: items, tucked: tucked)
                    .transition(.opacity)
            }
        }
    }
}
