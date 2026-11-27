import Foundation

/// Peek AI: Peek's own estimate of when a parcel will come, which the server gives only on a strong hint. It is always
/// named as Peek's, never the carrier's. Its rules and words are the web's (src/lib/peekAi.ts).
extension PeekAi {
    /// The calendar the server writes its days on: Gregorian, in the zone the rest of the app reads dates in. A device
    /// that shows the Buddhist, Japanese or Persian calendar still has "2026-11-24" on the same day.
    static var calendar: Calendar {
        var calendar = Calendar(identifier: .gregorian)
        calendar.timeZone = Calendar.current.timeZone
        return calendar
    }

    /// A day on this device's clock, as the server writes days: "2026-11-24".
    static func day(of date: Date) -> String {
        let parts = calendar.dateComponents([.year, .month, .day], from: date)
        return String(format: "%04d-%02d-%02d", parts.year ?? 0, parts.month ?? 0, parts.day ?? 0)
    }

    /// A day the server wrote, as its midnight on this device's clock.
    static func date(of day: String) -> Date? {
        let parts = day.split(separator: "-").compactMap { Int($0) }
        guard parts.count == 3 else { return nil }
        return calendar.date(from: DateComponents(year: parts[0], month: parts[1], day: parts[2]))
    }

    /// The hour `now` is, its minutes as a fraction, on the clock a time states by its offset ("…T08:12:00+01:00"), the
    /// clock Peek AI gives its hours on; on this device's clock for a time that states none ("Z", "-00:00").
    static func hour(at now: Date, on stated: String?) -> Double {
        var calendar = Self.calendar
        if let stated, stated.range(of: #"T[\d:.]+[+-]\d{2}:?\d{2}$"#, options: .regularExpression) != nil,
           let sign = stated.last(where: { $0 == "+" || $0 == "-" }) {
            let digits = stated.suffix(5).filter(\.isNumber)
            let seconds = (Int(digits.prefix(2)) ?? 0) * 3_600 + (Int(digits.suffix(2)) ?? 0) * 60
            if !(sign == "-" && seconds == 0), let zone = TimeZone(secondsFromGMT: sign == "-" ? -seconds : seconds) {
                calendar.timeZone = zone
            }
        }
        let clock = calendar.dateComponents([.hour, .minute], from: now)
        return Double(clock.hour ?? 0) + Double(clock.minute ?? 0) / 60
    }

    /// The day a time with its offset names, on its own clock: "2026-11-24T08:12:00+01:00" is the 24th.
    private static func dayNamed(_ value: String?) -> String? { value.map { String($0.prefix(10)) } }

    /// The moment the server says the estimate stops holding (`ends_at`), on the parcel's clock: none from an older copy.
    private var serverEnd: Date? { endsAt.flatMap(DateParser.date) }

    /// The estimate while it still holds: none from the moment the server gives, whatever this device's zone, else once
    /// its day or the hour it named has passed on this device's clock. A page left open, or kept for a while without a
    /// connection, would otherwise go on saying it.
    func current(at now: Date = Date()) -> PeekAi? {
        if let end = serverEnd { return now < end ? self : nil }
        let today = Self.day(of: now)
        switch says {
        case .notToday:
            return (carrierDay ?? Self.dayNamed(outSince)) == today ? self : nil
        case .byTime:
            guard Self.dayNamed(outSince) == today, let time, !time.isEmpty else { return nil }
            let clock = Self.calendar.dateComponents([.hour, .minute], from: now)
            return String(format: "%02d:%02d", clock.hour ?? 0, clock.minute ?? 0) < time ? self : nil
        case .sooner:
            // Earlier than the carrier's date holds until that date comes.
            guard let carrierDay, !carrierDay.isEmpty else { return self }
            return carrierDay > today ? self : nil
        default:
            // A day holds until it has passed.
            guard let day, !day.isEmpty else { return self }
            return day >= today ? self : nil
        }
    }

    /// When the estimate stops holding, for a screen or a widget that shows it unseen: the moment the server gives, else
    /// the hour it named, the start of the carrier's day for "maybe sooner", or the midnight after its day on this
    /// device's clock. None for one that holds until the parcel moves on.
    func end(after now: Date = Date()) -> Date? {
        if let end = serverEnd { return end }
        let calendar = Self.calendar
        let nextDay = { (day: String?) in day.flatMap(Self.date(of:)).flatMap { calendar.date(byAdding: .day, value: 1, to: $0) } }
        switch says {
        case .notToday:
            return nextDay(carrierDay ?? Self.dayNamed(outSince))
        case .byTime:
            guard let day = Self.dayNamed(outSince).flatMap(Self.date(of:)), let time,
                  let hour = Int(time.prefix(2)), let minute = Int(time.suffix(2)) else { return now }
            return calendar.date(bySettingHour: hour, minute: minute, second: 0, of: day)
        case .sooner:
            return carrierDay.flatMap(Self.date(of:))
        default:
            return nextDay(day)
        }
    }
}

extension Parcel {
    /// Stages after which a parcel is no longer on its way to the door, as the server has them.
    private static let notDue: Set<TrackingStage> = [.delivered, .returned, .readyForPickup, .failedAttempt]

    /// Peek AI's estimate while it holds, and while the parcel is still on its way: a copy kept from before a scan that
    /// delivered it, or an archived parcel, says none.
    func shownPeekAi(at now: Date = Date()) -> PeekAi? {
        if isArchived { return nil }
        if let stage = currentStage, Self.notDue.contains(stage) { return nil }
        return peekAi?.current(at: now)
    }

    /// When what Peek AI says of the parcel on a card can next change, for a list left open: the hour or the midnight its
    /// estimate ends, and each midnight before it, when "today" moves on. None while it says nothing.
    func peekAiChanges(after now: Date = Date()) -> [Date] {
        guard let ai = shownPeekAi(at: now) else { return [] }
        let calendar = PeekAi.calendar
        let end = ai.end(after: now).flatMap { $0 > now ? $0 : nil }
        var changes: [Date] = []
        var midnight = calendar.startOfDay(for: now)
        while changes.count < 7, let next = calendar.date(byAdding: .day, value: 1, to: midnight), end.map({ next < $0 }) ?? true {
            changes.append(next)
            midnight = next
        }
        return changes + (end.map { [$0] } ?? [])
    }
}

/// The strip in Peek AI's sheet: days shaded by their chance, the carrier's date ringed; or, out for delivery, two hours
/// to a cell.
struct PeekAiStrip: Equatable {
    enum Kind { case days, hours }

    struct Cell: Equatable, Identifiable {
        let key: String
        /// The small line: a weekday, or nothing for hours.
        let over: String
        /// The bold line: the day of the month, or the hour.
        let main: String
        /// Its chance: the day's, or its two hours' share of the carrier's deliveries.
        let chance: Double
        /// How strongly it is shaded, from 0 (no shade) to 4 (the likeliest): `peekAiShades`.
        var level = 0
        let carrier: Bool
        let past: Bool
        var id: String { key }
    }

    let kind: Kind
    let cells: [Cell]
}

/// A 12-hour time written over two lines, for a strip cell with no room for it on one: the time over its "AM" or "PM",
/// or under "上午" in Chinese, each line kept whole ("a. m." stays together). Nil for words that hold no such time: a
/// day of the month, a 24-hour time.
func peekAiStackedClock(_ words: String) -> String? {
    guard let time = words.range(of: #"\d{1,2}:\d{2}"#, options: .regularExpression) else { return nil }
    func whole(_ part: Substring) -> String {
        part.components(separatedBy: .whitespaces).filter { !$0.isEmpty }.joined(separator: "\u{00A0}")
    }
    let before = whole(words[..<time.lowerBound]), after = whole(words[time.upperBound...])
    switch (before.isEmpty, after.isEmpty) {
    case (true, false): return "\(words[time])\n\(after)"
    case (false, true): return "\(before)\n\(words[time])"
    default: return nil
    }
}

/// Under this chance a cell has no shade: a day or two hours that seldom see a delivery.
private let peekAiUnshadedBelow = 0.03

/// Each cell's shade, from its chance against the likeliest cell still to come: 4 at 85 % of it or more (the likeliest),
/// 3 from 55 %, 2 from 25 %, 1 below that, and none under 3 in 100. Four steps a reader tells apart at a glance. Days
/// and hours gone by are unshaded: the parcel did not come in them.
func peekAiShades(_ cells: [(chance: Double, past: Bool)]) -> [Int] {
    let top = max(0, cells.filter { !$0.past }.map(\.chance).max() ?? 0)
    return cells.map { chance, past in
        if past || chance < peekAiUnshadedBelow || top <= 0 { return 0 }
        return chance >= 0.85 * top ? 4 : chance >= 0.55 * top ? 3 : chance >= 0.25 * top ? 2 : 1
    }
}

extension Localizer {
    /// A wall clock Peek AI names ("15:30"), or the hour on a time it gives with its offset, as the reader writes times.
    private func peekAiClock(_ value: String) -> String {
        deliveryWallClock(value.count > 5 ? value.dropFirst(11).prefix(5) : Substring(value))
    }

    private func peekAiCalendar(_ day: String, now: Date = Date()) -> String {
        PeekAi.date(of: day).map { shortDate($0, now: now) } ?? day
    }

    /// The few words after the name on a card: "unlikely" beside the carrier's date when it says today (`estimate`, the
    /// carrier's estimate the card shows, as the carrier wrote it), "not today" where Peek AI stands alone, else what it
    /// expects ("by 16:00", "by Thu 26 Nov").
    func peekAiCardWords(_ ai: PeekAi, estimate: String?, now: Date = Date()) -> String {
        switch ai.says {
        case .notToday:
            let today = estimate.flatMap(DateParser.deliveryDate).map { PeekAi.calendar.isDate($0, inSameDayAs: now) } ?? false
            return text(today ? "peekAi.card.unlikely" : "peekAi.card.notToday")
        case .byTime: return text("peekAi.card.byTime", ["time": peekAiClock(ai.time ?? "")])
        case .byDay: return ai.day.map { text("peekAi.card.byDay", ["date": peekAiCalendar($0, now: now)]) } ?? text("peekAi.card.todayOrTomorrow")
        case .todayOrTomorrow: return text("peekAi.card.todayOrTomorrow")
        case .thisWeek: return text("peekAi.card.thisWeek")
        case .byEndOfNextWeek: return text("peekAi.card.byEndOfNextWeek")
        case .sooner: return text("peekAi.card.sooner")
        }
    }

    /// Peek AI's words on a parcel's card, or none: the parcel's own estimate while it holds, where the reader shows
    /// them and the card gives no day it ended on.
    func parcelPeekAiWords(_ parcel: Parcel, shown: Bool, now: Date = Date()) -> String? {
        guard shown, parcelCompletionDate(parcel, now: now) == nil, let ai = parcel.shownPeekAi(at: now) else { return nil }
        return peekAiCardWords(ai, estimate: shownEstimate(parcel, now: now), now: now)
    }

    /// What Peek AI says on the parcel's page, after its name: "today is unlikely", "maybe sooner, from tomorrow".
    func peekAiClaim(_ ai: PeekAi, now: Date = Date()) -> String {
        switch ai.says {
        case .notToday: return text("peekAi.claim.notToday")
        case .byTime: return text("peekAi.claim.byTime", ["time": peekAiClock(ai.time ?? "")])
        case .byDay: return ai.day.map { text("peekAi.claim.byDay", ["date": peekAiCalendar($0, now: now)]) } ?? text("peekAi.claim.todayOrTomorrow")
        case .todayOrTomorrow: return text("peekAi.claim.todayOrTomorrow")
        case .thisWeek: return text("peekAi.claim.thisWeek")
        case .byEndOfNextWeek: return text("peekAi.claim.byEndOfNextWeek")
        case .sooner:
            // A copy kept since its first day has gone by names no day.
            guard let day = ai.day, day >= PeekAi.day(of: now) else { return text("peekAi.claim.sooner") }
            let tomorrow = PeekAi.calendar.date(byAdding: .day, value: 1, to: now).map(PeekAi.day(of:))
            return day == tomorrow ? text("peekAi.claim.soonerTomorrow") : text("peekAi.claim.soonerFrom", ["date": peekAiCalendar(day, now: now)])
        }
    }

    /// The claim as a heading: "Today is unlikely".
    func peekAiHeading(_ ai: PeekAi, now: Date = Date()) -> String {
        let claim = peekAiClaim(ai, now: now)
        return claim.prefix(1).uppercased(with: language.locale) + claim.dropFirst()
    }

    /// What the carrier says, under Peek AI's heading in its sheet: "DPD says today", "UPS gives no date". `estimate` is
    /// the carrier's estimate as the card words it.
    func peekAiCarrierWord(_ ai: PeekAi, carrier: String, estimate: String?) -> String {
        switch ai.why {
        case .missedDate: return text("peekAi.carrier.wasDue", ["carrier": carrier])
        case .outForDelivery: return text("peekAi.carrier.noHour", ["carrier": carrier])
        // A parcel still out late is one the carrier set out to deliver today.
        case .noOutForDelivery, .lateOut: return text("peekAi.carrier.saysToday", ["carrier": carrier])
        default:
            if ai.why == .ahead, let day = ai.carrierDay {
                return text("peekAi.carrier.saysDate", ["carrier": carrier, "date": peekAiCalendar(day)])
            }
            return estimate.map { text("peekAi.carrier.saysDate", ["carrier": carrier, "date": $0]) }
                ?? text("peekAi.carrier.noDate", ["carrier": carrier])
        }
    }

    /// How long ago, in words that sit in a sentence: "5 hours ago", "yesterday", "2 days ago", by the calendar on this
    /// device once a day has passed.
    private func peekAiAgo(_ value: String, now: Date) -> String {
        guard let then = DateParser.date(value) else { return "" }
        let format = RelativeDateTimeFormatter()
        format.locale = language.locale
        format.dateTimeStyle = .named
        format.unitsStyle = .full
        let elapsed = now.timeIntervalSince(then)
        let hours = Int((elapsed / 3_600).rounded(.down))
        if hours < 1 { return format.localizedString(from: DateComponents(minute: -max(1, Int((elapsed / 60).rounded(.down))))) }
        if hours < 24 { return format.localizedString(from: DateComponents(hour: -hours)) }
        let calendar = PeekAi.calendar
        let days = calendar.dateComponents([.day], from: calendar.startOfDay(for: then), to: calendar.startOfDay(for: now)).day ?? 0
        return days < 14
            ? format.localizedString(from: DateComponents(day: -days))
            : format.localizedString(from: DateComponents(weekOfMonth: -(days / 7)))
    }

    /// With no carrier date, the last step the parcel took, after the sentence before it.
    private func peekAiWithLastStep(_ first: String, _ ai: PeekAi, now: Date) -> String {
        guard let last = ai.last else { return first }
        let key = switch last.kind {
        case .depot: "peekAi.last.depot"
        case .customs: "peekAi.last.customs"
        case .country: "peekAi.last.country"
        case .scan: "peekAi.last.scan"
        }
        let step = text(key, ["ago": peekAiAgo(last.at, now: now)])
        // Chinese joins its sentences without a space.
        return language == .zh ? first + step : "\(first) \(step)"
    }

    /// Why Peek AI thinks so, about this parcel, in a line under its card: one or two sentences.
    func peekAiWhy(_ ai: PeekAi, carrier: String, now: Date = Date()) -> String {
        switch ai.why {
        case .noOutForDelivery: return text("peekAi.why.noOutForDelivery", ["carrier": carrier])
        case .outForDelivery: return text("peekAi.why.outForDelivery", ["carrier": carrier, "time": peekAiClock(ai.outSince ?? "")])
        case .lateOut: return text("peekAi.why.lateOut", ["carrier": carrier])
        case .missedDate: return text("peekAi.why.missedDate", ["carrier": carrier])
        case .ahead: return text("peekAi.why.ahead", ["carrier": carrier, "date": ai.carrierDay.map { peekAiCalendar($0, now: now) } ?? ""])
        case .noDate: return peekAiWithLastStep(text("peekAi.why.noDate", ["carrier": carrier]), ai, now: now)
        }
    }

    /// The sheet's Why: what Peek AI goes by, the parcels Peek has followed that were like this one, under the carrier's
    /// word the sheet shows above it, with how many it stands on when the server says (`parcels`). Out for delivery, the
    /// carrier's usual hour for rounds that left before `startBefore` is the reason; the hour this one went out comes after.
    func peekAiReason(_ ai: PeekAi, carrier: String, now: Date = Date()) -> String {
        if let parcels = ai.parcels {
            // The count picks the sentence's form; the number is written as the language writes it: "4,988", "4 981".
            let counted: [String: CustomStringConvertible] = [
                "count": parcels, "number": parcels.formatted(.number.locale(language.locale)),
            ]
            switch ai.why {
            case .noOutForDelivery: return text("peekAi.counted.noOutForDelivery.many", counted)
            case .outForDelivery:
                // Without the round's start, the hour is said as before.
                guard let start = ai.startBefore, !start.isEmpty else { break }
                return text("peekAi.counted.outForDelivery.many", counted.merging([
                    "carrier": carrier, "start": peekAiClock(start), "time": peekAiClock(ai.time ?? ""),
                    "since": peekAiClock(ai.outSince ?? ""),
                ]) { $1 })
            case .lateOut: return text("peekAi.counted.lateOut.many", counted)
            case .ahead: return text("peekAi.counted.ahead.many", counted)
            case .noDate: return peekAiWithLastStep(text("peekAi.counted.noDate.many", counted), ai, now: now)
            // A missed date names no count.
            case .missedDate: break
            }
        }
        switch ai.why {
        case .noOutForDelivery: return text("peekAi.reason.noOutForDelivery")
        case .outForDelivery:
            return text("peekAi.reason.outForDelivery", [
                "carrier": carrier, "time": peekAiClock(ai.time ?? ""), "since": peekAiClock(ai.outSince ?? ""),
            ])
        case .lateOut: return text("peekAi.reason.lateOut")
        case .missedDate: return text("peekAi.reason.missedDate")
        case .ahead: return text("peekAi.reason.ahead")
        case .noDate: return peekAiWithLastStep(text("peekAi.reason.noDate"), ai, now: now)
        }
    }

    /// How often Peek AI was right on parcels like this one: "Right 8 times in 10 at this hour."
    func peekAiRecord(_ ai: PeekAi) -> String {
        // Rounded down, so it never says more than it was.
        let n = min(9, max(1, Int((ai.sure * 10 + 1e-9).rounded(.down))))
        return text(ai.says == .notToday ? "peekAi.sure.hour" : "peekAi.sure.parcels", ["n": n])
    }

    /// The strip as VoiceOver reads it, which its shades and ring show: the shaded cells still to come, likeliest first,
    /// then the carrier's ringed one, "Days, likeliest first: Thu 26, Wed 25, Fri 27. DPD’s date: Fri 27", in the
    /// language's own list, colon and full stop.
    func peekAiStripSpoken(_ strip: PeekAiStrip, carrier: String) -> String {
        let named = { (cell: PeekAiStrip.Cell) in [cell.over, cell.main].filter { !$0.isEmpty }.joined(separator: " ") }
        // Cells as likely as each other keep their order in time.
        let ranked = strip.cells.enumerated().filter { $0.element.level > 0 }
            .sorted { $0.element.chance != $1.element.chance ? $0.element.chance > $1.element.chance : $0.offset < $1.offset }
            .map { named($0.element) }
        // Chinese writes full-width marks without spaces, and French a space before its colon.
        let (colon, stop) = switch language {
        case .zh: ("：", "。")
        case .fr: ("\u{A0}: ", ". ")
        default: (": ", ". ")
        }
        var parts: [String] = []
        if !ranked.isEmpty {
            let list = ranked.formatted(.list(type: .and, width: .narrow).locale(language.locale))
            parts.append(text(strip.kind == .hours ? "peekAi.sheet.hoursByChance" : "peekAi.sheet.daysByChance") + colon + list)
        }
        if let ringed = strip.cells.first(where: \.carrier) {
            parts.append(text("peekAi.sheet.carrierDate", ["carrier": carrier]) + colon + named(ringed))
        }
        return parts.joined(separator: stop)
    }

    /// Days shaded by their chance, the carrier's date ringed, a day gone by on this device's calendar marked and
    /// unshaded; or, out for delivery, two hours to a cell, the ones gone by on the parcel's clock (`PeekAi.hour`) marked
    /// and unshaded.
    func peekAiStrip(_ ai: PeekAi, now: Date = Date()) -> PeekAiStrip? {
        let shaded = { (cells: [PeekAiStrip.Cell]) in
            zip(cells, peekAiShades(cells.map { ($0.chance, $0.past) })).map { cell, level in
                var cell = cell
                cell.level = level
                return cell
            }
        }
        if let hours = ai.hours, !hours.isEmpty {
            let hour = PeekAi.hour(at: now, on: ai.outSince ?? ai.endsAt)
            let cells = stride(from: 0, to: hours.count, by: 2).map { index in
                let pair = hours[index..<min(index + 2, hours.count)]
                let first = pair[index]
                return PeekAiStrip.Cell(
                    key: first.time, over: "", main: deliveryWallClock(Substring(first.time)),
                    chance: pair.reduce(0) { $0 + $1.share }, carrier: false,
                    past: (Double(first.time.prefix(2)) ?? 0) + Double(pair.count) <= hour
                )
            }
            return PeekAiStrip(kind: .hours, cells: shaded(cells))
        }
        guard let days = ai.days, !days.isEmpty else { return nil }
        let today = PeekAi.day(of: now)
        return PeekAiStrip(kind: .days, cells: shaded(days.map { entry in
            let date = PeekAi.date(of: entry.day)
            return PeekAiStrip.Cell(
                key: entry.day, over: date.map(shortWeekday) ?? "",
                main: date.map { String(PeekAi.calendar.component(.day, from: $0)) } ?? "",
                chance: entry.p, carrier: entry.day == ai.carrierDay, past: entry.day < today
            )
        }))
    }
}
