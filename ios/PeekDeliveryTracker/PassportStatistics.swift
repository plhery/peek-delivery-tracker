import Foundation

/// A passport records observed journeys. Adding a parcel or creating a label does
/// not start its delivery clock, and a carrier's home country is not its origin.
struct PassportStatistics {
    struct DeliveryRecord: Identifiable, Equatable {
        let parcelID: UUID
        let label: String
        let duration: TimeInterval
        let deliveredAt: Date
        var id: UUID { parcelID }
    }

    struct OriginCountry: Identifiable, Equatable {
        let code: String
        let count: Int
        var id: String { code }
        var flag: String { TrackingLocation.flag(code) }
    }

    /// What one delivered parcel brings to the passport, in the order its scans happened.
    struct Delivery: Equatable {
        let parcelID: UUID
        let label: String
        let carrier: CarrierID
        let deliveredAt: Date
        /// Its delivery's day on the reader's calendar, YYYY-MM-DD.
        let day: String
        let december: Bool
        let pickup: Bool
        let crossBorder: Bool
        let crossContinent: Bool
        /// A delivery came after a missed attempt.
        let secondTry: Bool
        /// Its quarter of the year by month: 0 for December to February, 1 for March to May, and so on.
        let season: Int
        var domestic = false
        var longWait = false
        /// From its first physical scan to its delivery, when both are known.
        var duration: TimeInterval?
        /// Each country it was scanned in, in the order it got there, with the first instant it was there.
        let countries: [(code: String, at: Date)]

        static func == (left: Delivery, right: Delivery) -> Bool {
            left.parcelID == right.parcelID && left.deliveredAt == right.deliveredAt && left.day == right.day
                && left.duration == right.duration && left.countries.map(\.code) == right.countries.map(\.code)
        }
    }

    /// Carrier ids that stand for "not known yet" rather than a carrier: they never count toward a carrier stamp.
    static let placeholderCarriers: Set<CarrierID> = [.unknown, .internationalPost]

    let trackedCount: Int
    let deliveredCount: Int
    let activeCount: Int
    /// Distinct real carriers of delivered parcels.
    let carrierCount: Int
    /// Every delivered parcel whose scans are all dated, in the order they arrived.
    let deliveries: [Delivery]
    let crossBorderCount: Int
    let domesticDeliveryCount: Int
    let longWaitDeliveryCount: Int
    let pickupDeliveryCount: Int
    let decemberDeliveryCount: Int
    let maxDeliveriesInOneDay: Int
    let durationSampleCount: Int
    let averageDeliveryDuration: TimeInterval?
    let fastestDelivery: DeliveryRecord?
    /// Countries explicitly reported at the first physical scan, across all parcels.
    /// This is "first seen in", which does not guarantee the sender's country.
    let originCountries: [OriginCountry]
    let knownOriginCount: Int
    var unknownOriginCount: Int { trackedCount - knownOriginCount }

    var nextMilestoneCount: Int {
        [1, 5, 10, 25, 50, 100].first(where: { $0 > deliveredCount })
            ?? ((deliveredCount / 100) + 1) * 100
    }

    var milestoneProgress: Double {
        Double(deliveredCount) / Double(nextMilestoneCount)
    }

    init(parcels: [Parcel], timeZone: TimeZone = .current) {
        var delivered = 0
        var active = 0
        var records: [DeliveryRecord] = []
        var countries: [String: Int] = [:]
        var deliveredDays: [Date: Int] = [:]
        var calendar = Calendar(identifier: .gregorian)
        calendar.timeZone = timeZone
        var crossBorder = 0, domestic = 0, longWait = 0, pickup = 0, december = 0
        var carriers: Set<CarrierID> = []
        var deliveries: [Delivery] = []
        let day = DateFormatter()
        day.locale = Locale(identifier: "en_US_POSIX")
        day.calendar = calendar
        day.timeZone = timeZone
        day.dateFormat = "yyyy-MM-dd"

        for parcel in parcels {
            let meaningful = parcel.trackingEvents.filter { $0.stage != .pending }
            let dated = meaningful.compactMap { event -> DatedEvent? in
                guard let date = DateParser.date(event.occurredAt) else { return nil }
                return DatedEvent(event: event, date: date)
            }.sorted(by: Self.earlier)
            let datesAreComplete = dated.count == meaningful.count
            // A malformed date must not turn a return into an earned delivery.
            // Keep the app's status fallback when chronology cannot be established.
            let currentStage = datesAreComplete ? dated.last?.event.stage : parcel.currentStage
            if currentStage == .delivered {
                delivered += 1
                if !Self.placeholderCarriers.contains(parcel.carrier) { carriers.insert(parcel.carrier) }
            }
            if parcel.archivedAt == nil && currentStage != .delivered && currentStage != .returned {
                active += 1
            }

            let physical = dated.filter { $0.event.stage != .registered }
            let completion = physical.first(where: { $0.event.stage == .delivered })
            var delivery: Delivery?
            if datesAreComplete, currentStage == .delivered, let completion {
                // Count each parcel once, using the device's Gregorian calendar day.
                deliveredDays[calendar.startOfDay(for: completion.date), default: 0] += 1
                let month = calendar.component(.month, from: completion.date)
                if month == 12 { december += 1 }
                // Collected where it waited: its last movement before the delivery made it ready for pickup, not a trip to the door after.
                let pickedUp = physical.last(where: { $0.date < completion.date && Self.movements.contains($0.event.stage) })?.event.stage == .readyForPickup
                if pickedUp { pickup += 1 }
                let located = physical.filter { $0.date <= completion.date }.compactMap { scan -> (country: String, date: Date)? in
                    guard let country = Self.passportCountry(scan.event) else { return nil }
                    return (country, scan.date)
                }
                // Different countries must be observed at different instants; tied scans do not prove travel.
                func travelled(_ apart: (String, String) -> Bool) -> Bool {
                    located.enumerated().contains { index, scan in
                        located.prefix(index).contains { $0.date < scan.date && apart($0.country, scan.country) }
                    }
                }
                let crossed = travelled { $0 != $1 }
                if crossed { crossBorder += 1 }
                var visited: [(code: String, at: Date)] = []
                for scan in located where !visited.contains(where: { $0.code == scan.country }) { visited.append((scan.country, scan.date)) }
                let data = PassportData.shared
                delivery = Delivery(
                    parcelID: parcel.id, label: parcel.label, carrier: parcel.carrier, deliveredAt: completion.date,
                    day: day.string(from: completion.date), december: month == 12, pickup: pickedUp, crossBorder: crossed,
                    crossContinent: travelled { data.ink($0).continent != data.ink($1).continent },
                    secondTry: physical.contains { $0.event.stage == .failedAttempt && $0.date < completion.date },
                    season: (month % 12) / 3, countries: visited
                )
            }
            defer { if let delivery { deliveries.append(delivery) } }
            guard datesAreComplete, let first = physical.first,
                  first.event.stage == .accepted || first.event.stage == .inTransit else { continue }

            // Never skip an unknown first scan and promote a destination scan into
            // an origin. City names and tracking-number suffixes are insufficient.
            if let country = TrackingLocation.countryCode(in: first.event.location, placedIn: first.event.place?.country) {
                countries[country, default: 0] += 1
            }

            guard currentStage == .delivered,
                  let completion else { continue }
            let duration = completion.date.timeIntervalSince(first.date)
            guard duration.isFinite, duration > 0 else { continue }
            if let country = Self.passportCountry(first.event), country == Self.passportCountry(completion.event) {
                domestic += 1
                delivery?.domestic = true
            }
            if duration > 30 * 86_400 {
                longWait += 1
                delivery?.longWait = true
            }
            delivery?.duration = duration
            records.append(DeliveryRecord(
                parcelID: parcel.id, label: parcel.label,
                duration: duration, deliveredAt: completion.date
            ))
        }

        crossBorderCount = crossBorder
        domesticDeliveryCount = domestic
        longWaitDeliveryCount = longWait
        pickupDeliveryCount = pickup
        decemberDeliveryCount = december
        maxDeliveriesInOneDay = deliveredDays.values.max() ?? 0
        trackedCount = parcels.count
        deliveredCount = delivered
        activeCount = active
        carrierCount = carriers.count
        self.deliveries = deliveries.sorted {
            if $0.deliveredAt != $1.deliveredAt { return $0.deliveredAt < $1.deliveredAt }
            return $0.parcelID.uuidString < $1.parcelID.uuidString
        }
        durationSampleCount = records.count
        averageDeliveryDuration = records.isEmpty
            ? nil : records.reduce(0) { $0 + $1.duration } / Double(records.count)
        fastestDelivery = records.min {
            if $0.duration != $1.duration { return $0.duration < $1.duration }
            return $0.parcelID.uuidString < $1.parcelID.uuidString
        }
        originCountries = countries.map { OriginCountry(code: $0.key, count: $0.value) }
            .sorted {
                if $0.count != $1.count { return $0.count > $1.count }
                return $0.code < $1.code
            }
        knownOriginCount = countries.values.reduce(0, +)
    }

    /// Where a scan was, as the passport's countries count it: the country its words name, else the one the server
    /// placed it in.
    static func passportCountry(_ event: TrackingEvent) -> String? {
        TrackingLocation.countryCode(in: event.location, placedIn: event.place?.country) ?? event.place?.country
    }

    /// Stages that move a parcel: notices, problem reports and a delivery itself do not.
    private static let movements: Set<TrackingStage> = [.inTransit, .customs, .outForDelivery, .failedAttempt, .readyForPickup, .returned]

    private struct DatedEvent {
        let event: TrackingEvent
        let date: Date
    }

    private static func earlier(_ left: DatedEvent, _ right: DatedEvent) -> Bool {
        if left.date != right.date { return left.date < right.date }
        // Carrier timestamps sometimes have minute precision. Put later stages
        // after earlier stages at the same instant, with return taking precedence.
        let stages: [TrackingStage] = [
            .registered, .accepted, .inTransit, .customs, .exception, .outForDelivery,
            .failedAttempt, .readyForPickup, .delivered, .returned,
        ]
        let leftRank = stages.firstIndex(of: left.event.stage) ?? 0
        let rightRank = stages.firstIndex(of: right.event.stage) ?? 0
        if leftRank != rightRank { return leftRank < rightRank }
        return left.event.id.uuidString < right.event.id.uuidString
    }

}

/// The whole passport: today's numbers, every stamp with its progress and the day it was earned, and the countries. A
/// stamp's day is the delivery that crossed its rule, replaying the deliveries in the order they arrived, so deleting a
/// parcel can move or remove it. The web's `passportBook`, which the three platforms check against PassportCases.json.
struct PassportBook {
    /// A stamp as the passport shows it: how far the reader is, and once earned, the day and the parcel that earned it.
    struct StampProgress: Identifiable, Equatable {
        let stamp: PassportData.Stamp
        let count: Int
        /// When the delivery that crossed its rule arrived; nil for an earned stamp whose deliveries are not all dated.
        let earnedAt: Date?
        let parcelID: UUID?
        let label: String?
        var key: String { stamp.key }
        var id: String { stamp.key }
        var total: Int { stamp.total }
        var earned: Bool { count >= stamp.total }
        /// The key the reader's seen list keeps it by.
        var seenKey: String { "stamp:\(stamp.key)" }
    }

    /// A country's ink stamp: the first instant a delivered parcel of the reader's was scanned there, and how many went
    /// through it.
    struct CountryStamp: Identifiable, Equatable {
        let code: String
        /// Its running number in the order the countries were reached, from 1.
        let number: Int
        let firstAt: Date
        let parcelID: UUID
        let label: String
        let count: Int
        var continent: PassportData.Continent { PassportData.shared.ink(code).continent }
        var id: String { code }
    }

    let stats: PassportStatistics
    let stamps: [StampProgress]
    let countries: [CountryStamp]

    private static let day: TimeInterval = 86_400

    init(parcels: [Parcel], timeZone: TimeZone = .current) {
        let stats = PassportStatistics(parcels: parcels, timeZone: timeZone)
        self.stats = stats
        var earned: [String: PassportStatistics.Delivery] = [:]
        func earn(_ key: String, _ delivery: PassportStatistics.Delivery, _ when: Bool = true) {
            if when, earned[key] == nil { earned[key] = delivery }
        }
        var carriers: Set<CarrierID> = []
        var byCarrier: [CarrierID: Int] = [:]
        var reached: [String: (firstAt: Date, delivery: PassportStatistics.Delivery, count: Int)] = [:]
        var days: [String: Int] = [:]
        var seasons: Set<Int> = []
        let firstDelivery = stats.deliveries.first?.deliveredAt
        for (index, delivery) in stats.deliveries.enumerated() {
            let n = index + 1
            earn("first", delivery); earn("ten", delivery, n >= 10); earn("theRegular", delivery, n >= 25); earn("fifty", delivery, n >= 50)
            if !PassportStatistics.placeholderCarriers.contains(delivery.carrier) {
                carriers.insert(delivery.carrier)
                byCarrier[delivery.carrier, default: 0] += 1
            }
            earn("connected", delivery, carriers.count >= 3)
            earn("familiarFace", delivery, (byCarrier[delivery.carrier] ?? 0) >= 3)
            earn("secondTry", delivery, delivery.secondTry)
            seasons.insert(delivery.season)
            earn("fourSeasons", delivery, seasons.count >= 4)
            earn("firstAnniversary", delivery, delivery.deliveredAt.timeIntervalSince(firstDelivery ?? delivery.deliveredAt) >= 365 * Self.day)
            earn("express", delivery, delivery.duration.map { $0 <= 2 * Self.day } ?? false)
            earn("acrossBorders", delivery, delivery.crossBorder)
            earn("newHorizons", delivery, delivery.crossContinent)
            for (code, at) in delivery.countries {
                if var known = reached[code] {
                    known.count += 1
                    if at < known.firstAt { known.firstAt = at; known.delivery = delivery }
                    reached[code] = known
                } else {
                    reached[code] = (at, delivery, 1)
                }
            }
            earn("aroundWorld", delivery, reached.count >= 5)
            earn("tenCountries", delivery, reached.count >= 10)
            earn("rightNextDoor", delivery, delivery.domestic)
            earn("worthTheWait", delivery, delivery.longWait)
            days[delivery.day, default: 0] += 1
            earn("busyDoorstep", delivery, (days[delivery.day] ?? 0) >= 3)
            earn("pickedUp", delivery, delivery.pickup)
            earn("homeForHolidays", delivery, delivery.december)
        }
        let span = (stats.deliveries.last?.deliveredAt).flatMap { last in firstDelivery.map { last.timeIntervalSince($0) } } ?? 0
        let counts: [String: Int] = [
            "first": stats.deliveredCount, "ten": stats.deliveredCount, "theRegular": stats.deliveredCount, "fifty": stats.deliveredCount,
            "connected": stats.carrierCount,
            "express": stats.fastestDelivery.map { $0.duration <= 2 * Self.day ? 1 : 0 } ?? 0,
            "acrossBorders": stats.crossBorderCount,
            "newHorizons": stats.deliveries.filter(\.crossContinent).count,
            "aroundWorld": reached.count, "tenCountries": reached.count,
            "rightNextDoor": stats.domesticDeliveryCount, "worthTheWait": stats.longWaitDeliveryCount,
            "busyDoorstep": stats.maxDeliveriesInOneDay, "pickedUp": stats.pickupDeliveryCount,
            "homeForHolidays": stats.decemberDeliveryCount,
            "familiarFace": byCarrier.values.max() ?? 0,
            "secondTry": stats.deliveries.filter(\.secondTry).count,
            "fourSeasons": seasons.count,
            "firstAnniversary": earned["firstAnniversary"] != nil ? 12 : min(11, Int((span / (30.4375 * Self.day)).rounded(.down))),
        ]
        stamps = PassportData.shared.stamps.map { stamp in
            let count = counts[stamp.key] ?? 0
            let delivery = count >= stamp.total ? earned[stamp.key] : nil
            return StampProgress(stamp: stamp, count: count, earnedAt: delivery?.deliveredAt, parcelID: delivery?.parcelID, label: delivery?.label)
        }
        countries = reached.sorted {
            if $0.value.firstAt != $1.value.firstAt { return $0.value.firstAt < $1.value.firstAt }
            return $0.key < $1.key
        }.enumerated().map { index, entry in
            CountryStamp(code: entry.key, number: index + 1, firstAt: entry.value.firstAt, parcelID: entry.value.delivery.parcelID,
                         label: entry.value.delivery.label, count: entry.value.count)
        }
    }

    /// The stamps Friends shares, in the order the apps before the new stamps know them: old apps decode a strict list.
    var sharedStamps: [FriendStamp] {
        let earned = Set(stamps.filter(\.earned).map(\.key))
        return FriendStamp.allCases.filter { earned.contains($0.rawValue) }
    }

    /// "Your <year> in parcels": the year's deliveries on the reader's calendar, the countries they went through (the six
    /// with the most of them, in the passport's order, when there are more), the stamps they earned and the fastest one.
    /// Aggregates only: no parcel, shop, carrier or place below the country.
    func year(_ year: Int) -> YearInParcels {
        let deliveries = stats.deliveries.filter { $0.day.hasPrefix("\(year)-") }
        let parcels = Set(deliveries.map(\.parcelID))
        var counts: [String: Int] = [:]
        // A country's day on the card is the year's first delivery through it, never an earlier year's.
        var firsts: [String: Date] = [:]
        for delivery in deliveries {
            for country in delivery.countries {
                counts[country.code, default: 0] += 1
                firsts[country.code] = min(firsts[country.code] ?? delivery.deliveredAt, delivery.deliveredAt)
            }
        }
        let countries = self.countries.compactMap { country in
            counts[country.code].map {
                CountryStamp(code: country.code, number: country.number, firstAt: firsts[country.code] ?? country.firstAt, parcelID: country.parcelID, label: country.label, count: $0)
            }
        }
        let shown = countries.count <= 6 ? countries : Array(countries.sorted {
            if $0.count != $1.count { return $0.count > $1.count }
            return $0.number < $1.number
        }.prefix(6)).sorted { $0.number < $1.number }
        return YearInParcels(
            year: year, deliveredCount: deliveries.count, countryCount: countries.count, countries: shown,
            stamps: stamps.filter { $0.earned && $0.parcelID.map(parcels.contains) == true },
            fastest: deliveries.compactMap(\.duration).min()
        )
    }
}

struct YearInParcels: Equatable {
    let year: Int
    let deliveredCount: Int
    let countryCount: Int
    let countries: [PassportBook.CountryStamp]
    let stamps: [PassportBook.StampProgress]
    let fastest: TimeInterval?
}

/// Country fields shared by parcel display and first-scan statistics.
enum TrackingLocation {
    private static let regionCodes = Set(Locale.Region.isoRegions.map(\.identifier).filter {
        $0.count == 2 && !["EU", "UN", "QO"].contains($0)
    })

    // A two-letter final address field can instead be a US state, Swiss canton,
    // Canadian province/territory, or Australian state. These require a full
    // country name, or a location containing only the country code.
    private static let ambiguousAddressCodes: Set<String> = [
        "AL", "AZ", "AR", "CA", "CO", "DE", "GA", "ID", "IL", "IN", "KY",
        "LA", "ME", "MD", "MA", "MN", "MS", "MO", "MT", "NE", "NC", "PA",
        "SC", "SD", "TN", "VA", "AG", "AI", "BE", "BL", "BS", "FR", "GE",
        "GL", "GR", "LU", "SG", "SH", "SO", "SZ", "TG", "NL", "NU", "PE",
        "SK", "YT", "SA",
    ]

    // Carrier spellings no region name matches. Display only: Passport evidence
    // stays in step with the web's trackingLocationCountry, which Friends stamps use.
    private static let carrierSpellings: [String: String] = [
        "united states of america": "US", "great britain": "GB", "czech republic": "CZ", "holland": "NL",
        "hong kong": "HK", "macau": "MO", "macao": "MO", "turkey": "TR", "russian federation": "RU",
        "korea": "KR", "republic of korea": "KR",
    ]

    // DHL Express's own country names, after the article and any bracket are dropped.
    private static let dhlSpellings: [String: String] = [
        "hong kong sar, china": "HK", "macau sar, china": "MO", "macao sar, china": "MO", "china mainland": "CN",
        "china, peoples republic": "CN", "people's republic of china": "CN", "korea, republic of": "KR",
        "ireland, republic of": "IE",
    ]

    // "Mexico City" is a city, not Mexico followed by one.
    private static let settlementWords: Set<String> = ["city", "town", "ville", "stadt", "ciudad", "cidade", "citta"]

    private static let fieldPattern = try! NSRegularExpression(pattern: "[^,;|()]+")

    // Apple calls CN "China mainland" in every language, never what carriers or the web write.
    private static let chinaNames: [AppLanguage: String] = [
        .en: "China", .de: "China", .fr: "Chine", .it: "Cina", .es: "China", .pt: "China", .ptBR: "China", .pl: "Chiny",
        .ru: "Китай", .zh: "中国",
    ]

    private static let countryNames: [String: String] = {
        var names: [String: String] = [:]
        for code in regionCodes.sorted() {
            for language in AppLanguage.allCases {
                if let name = language.locale.localizedString(forRegionCode: code) {
                    names[normalized(name)] = code
                }
            }
        }
        names["usa"] = "US"
        names["uk"] = "GB"
        for name in chinaNames.values { names[normalized(name)] = "CN" }
        return names
    }()

    /// A location split into the country its carrier wrote and the rest.
    struct Place: Equatable {
        let country: String?
        /// Empty when the country was all of the location.
        let name: String
    }

    /// Builds the country-name table (about 2,500 localized names) ahead of first use.
    static func prepare() {
        _ = countryNames
    }

    private static func normalized(_ value: String) -> String {
        value.trimmingCharacters(in: .whitespacesAndNewlines)
            .folding(options: [.caseInsensitive, .diacriticInsensitive], locale: Locale(identifier: "en_US_POSIX"))
    }

    private static func namedCountry(_ name: String, display: Bool) -> String? {
        let key = normalized(name)
        if let code = countryNames[key] { return code }
        guard display else { return nil }
        let bare = key.hasPrefix("the ") ? String(key.dropFirst(4)) : key
        return countryNames[bare] ?? carrierSpellings[bare]
    }

    private static func fieldCountry(_ field: String, in location: String, display: Bool) -> String? {
        if field.count == 2, field == field.uppercased(), regionCodes.contains(field) {
            return location == field || !ambiguousAddressCodes.contains(field) ? field : nil
        }
        return namedCountry(field, display: display)
    }

    /// Passport evidence: only an explicit final country field; never a country inferred from a city.
    static func countryCode(in location: String?) -> String? {
        guard let location else { return nil }
        // Require a whole country field: "Milano, IT", "Paris, France", or
        // "Berlin (Germany)". A bare city or an undelimited "Buchs AG" is unknown.
        let fields = location.components(separatedBy: CharacterSet(charactersIn: ",;|()"))
            .map { $0.trimmingCharacters(in: .whitespacesAndNewlines) }
            .filter { !$0.isEmpty }
        guard let field = fields.last else { return nil }
        return fieldCountry(field, in: location.trimmingCharacters(in: .whitespacesAndNewlines), display: false)
    }

    private static let dashed = try! NSRegularExpression(pattern: "^(.*\\S)\\s+-\\s+(.+)$")

    /// The country DHL Express ends its locations with: "UK", "NETHERLANDS, THE", "HONG KONG SAR, CHINA".
    private static func dashCountry(_ part: String) -> String? {
        let name = normalized(part)
            .replacingOccurrences(of: "\\s*\\(.*\\)$", with: "", options: .regularExpression)
            .replacingOccurrences(of: "^the |, the$", with: "", options: .regularExpression)
        // Also a US state: "ATLANTA - GEORGIA" is not in the Caucasus.
        if name == "georgia" { return nil }
        return dhlSpellings[name] ?? namedCountry(name, display: true)
    }

    /// The country after a location's last dash, with what comes before it: "SCARBOROUGH - ON - CANADA".
    private static func dashedPlace(_ text: String) -> Place? {
        guard let match = dashed.firstMatch(in: text, range: NSRange(text.startIndex..., in: text)),
              let name = Range(match.range(at: 1), in: text), let part = Range(match.range(at: 2), in: text),
              let country = dashCountry(String(text[part])) else { return nil }
        return Place(country: country, name: String(text[name]))
    }

    /// For display, takes an explicit country off a location: the name after the last dash ("SCARBOROUGH - ON -
    /// CANADA"), a country's code before a dot ("CN.CNE East China Sort Facility"), the final field ("Zürich, CH",
    /// repeated in "Hebron, KY, US, US") or a leading name ("Switzerland Haerkingen"), as the web and Android read it.
    static func place(_ location: String) -> Place {
        let text = location.trimmingCharacters(in: .whitespacesAndNewlines)
        if let dashed = dashedPlace(text) { return dashed }
        if let prefixed = prefixedPlace(text) { return prefixed }
        let fields = fieldPattern.matches(in: text, range: NSRange(text.startIndex..., in: text))
            .compactMap { Range($0.range, in: text) }
            .filter { !text[$0].trimmingCharacters(in: .whitespacesAndNewlines).isEmpty }
        var country: String?
        var kept = fields.count
        while kept > 0 {
            let field = text[fields[kept - 1]].trimmingCharacters(in: .whitespacesAndNewlines)
            guard let code = fieldCountry(field, in: text, display: true), country == nil || code == country else { break }
            country = code
            kept -= 1
        }
        if let country {
            let rest = kept > 0 ? String(text[..<fields[kept].lowerBound]) : ""
            return Place(country: country, name: rest.replacingOccurrences(of: "[\\s,;|(]+$", with: "", options: .regularExpression))
        }
        if fields.count == 1, fields[0] == text.startIndex..<text.endIndex {
            let words = text.split(whereSeparator: \.isWhitespace).map(String.init)
            for count in stride(from: min(words.count - 1, 4), to: 0, by: -1) {
                let name = words[count...].joined(separator: " ")
                if let code = namedCountry(words[..<count].joined(separator: " "), display: true),
                   name.first?.isUppercase == true, !settlementWords.contains(normalized(name)) {
                    return Place(country: code, name: name)
                }
            }
        }
        return Place(country: nil, name: text)
    }

    private static let prefixedCode = try! NSRegularExpression(pattern: "^([A-Z]{2})\\.(\\p{L}.*)$")

    /// A country's code before a dot, as CNE writes its facilities ("CN.CNE East China Sort
    /// Facility"), as the web reads it. "ST.LOUIS" and "PT.ELIZABETH" abbreviate Saint and Port.
    private static func prefixedPlace(_ text: String) -> Place? {
        guard let match = prefixedCode.firstMatch(in: text, range: NSRange(text.startIndex..., in: text)),
              let code = Range(match.range(at: 1), in: text).map({ String(text[$0]) }),
              let name = Range(match.range(at: 2), in: text),
              regionCodes.contains(code), !ambiguousAddressCodes.contains(code), code != "ST", code != "PT" else { return nil }
        return Place(country: code, name: text[name].trimmingCharacters(in: .whitespacesAndNewlines))
    }

    private static let finalCode = try! NSRegularExpression(pattern: "^(.*\\S)\\s*[,;|(]\\s*([A-Z]{2})\\)?$")

    /// The final code of a location, with what comes before it, when it is the country the scan
    /// was placed in. A code that is also a state, province or canton ("Haiger, DE", "Wilmington,
    /// DE", "Toronto, CA", "Los Angeles, CA") is not read as a country on its own.
    private static func placedCode(_ location: String, placedIn placed: String?) -> Place? {
        let text = location.trimmingCharacters(in: .whitespacesAndNewlines)
        guard let placed,
              let match = finalCode.firstMatch(in: text, range: NSRange(text.startIndex..., in: text)),
              let name = Range(match.range(at: 1), in: text), let code = Range(match.range(at: 2), in: text),
              text[code] == placed else { return nil }
        return Place(country: placed, name: String(text[name]))
    }

    /// For display: a final code that is also a state or canton names a country only when the
    /// scan was placed in that country.
    static func place(_ location: String, placedIn placed: String?) -> Place {
        let written = place(location)
        return written.country == nil ? placedCode(location, placedIn: placed) ?? written : written
    }

    /// A letter in a script other than Latin and the one each language is written in: a Russian reader's Cyrillic,
    /// a Chinese reader's characters.
    private static let foreignLetters: [AppLanguage: NSRegularExpression] = Dictionary(uniqueKeysWithValues: AppLanguage.allCases.map { language in
        let own = language == .ru ? "\\p{Script=Cyrillic}" : language == .zh ? "\\p{Script=Han}" : ""
        return (language, try! NSRegularExpression(pattern: "(?![\\p{Script=Latin}\(own)])\\p{L}"))
    })

    /// CTT's site code and office ahead of its place ("1234567 - (CDP) 3045 - Coimbra 3045").
    private static let cttSite = try! NSRegularExpression(pattern: "^\\d{6,7} - \\(")

    /// Whether a location is shown by its town's name, as the server's place lookup gives it, rather than as written,
    /// as on the web (`romanisesPlace`): it has a letter in a script other than Latin and the reader's own ("深圳市"
    /// for all but a Chinese reader, "東京都 ちよだ" for every one, "Москва" for all but a Russian reader), or it
    /// starts with CTT's site code. The stored scan keeps its words.
    static func romanises(_ location: String, language: AppLanguage) -> Bool {
        let range = NSRange(location.startIndex..., in: location)
        return foreignLetters[language]?.firstMatch(in: location, range: range) != nil
            || cttSite.firstMatch(in: location, range: range) != nil
    }

    /// For display: a location written in a script the reader may not read (`romanises`) is named by the town the
    /// server placed it in ("深圳市" is Shenzhen), or by the country alone where it placed it no closer, and kept as
    /// written where it placed it nowhere. Otherwise as `place(_:placedIn:)` reads it.
    static func place(_ location: String, placed: EventPlace?, language: AppLanguage) -> Place {
        if let placed, romanises(location, language: language) {
            return Place(country: placed.country, name: placed.precision == .city ? placed.name : "")
        }
        return place(location, placedIn: placed?.country)
    }

    /// Passport evidence, with a final code that is also a state or canton counted once the scan
    /// was placed in that country.
    static func countryCode(in location: String?, placedIn placed: String?) -> String? {
        guard let location else { return nil }
        return countryCode(in: location) ?? placedCode(location, placedIn: placed)?.country
    }

    static func countryName(_ code: String, language: AppLanguage) -> String {
        if code == "CN", let name = chinaNames[language] { return name }
        return language.locale.localizedString(forRegionCode: code) ?? code
    }

    static func flag(_ code: String) -> String {
        String(String.UnicodeScalarView(code.unicodeScalars.compactMap {
            UnicodeScalar(127_397 + $0.value)
        }))
    }
}
