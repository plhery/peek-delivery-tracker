import Foundation

/// Peek AI: Peek's own estimate of when a parcel will come, which the server gives only on a strong hint. It is always
/// named as Peek's, never the carrier's. Its rules and words are the web's (src/lib/peekAi.ts).
extension PeekAi {
    /// A day on this device's calendar, as the server writes days: "2026-11-24".
    static func day(of date: Date) -> String {
        let parts = Calendar.current.dateComponents([.year, .month, .day], from: date)
        return String(format: "%04d-%02d-%02d", parts.year ?? 0, parts.month ?? 0, parts.day ?? 0)
    }

    /// A day the server wrote, as a date on this device's calendar.
    static func date(of day: String) -> Date? {
        let parts = day.split(separator: "-").compactMap { Int($0) }
        guard parts.count == 3 else { return nil }
        return Calendar.current.date(from: DateComponents(year: parts[0], month: parts[1], day: parts[2]))
    }

    /// The day a time with its offset names, on its own clock: "2026-11-24T08:12:00+01:00" is the 24th.
    private static func dayNamed(_ value: String?) -> String? { value.map { String($0.prefix(10)) } }

    /// The estimate while it still holds on this device's clock: none once its day has passed, or the hour it named. A
    /// page left open, or kept for a while without a connection, would otherwise go on saying it.
    func current(at now: Date = Date()) -> PeekAi? {
        let today = Self.day(of: now)
        switch says {
        case .notToday:
            return (carrierDay ?? Self.dayNamed(outSince)) == today ? self : nil
        case .byTime:
            guard Self.dayNamed(outSince) == today, let time, !time.isEmpty else { return nil }
            let clock = Calendar.current.dateComponents([.hour, .minute], from: now)
            return String(format: "%02d:%02d", clock.hour ?? 0, clock.minute ?? 0) < time ? self : nil
        default:
            // Earlier than the carrier's date holds until that date; the other days, until their own.
            let until = says == .sooner ? carrierDay : day
            guard let until, !until.isEmpty else { return self }
            return until >= today ? self : nil
        }
    }

    /// When the estimate stops holding on this device's clock, for a widget that shows it unseen: the hour it named, or
    /// the midnight after its day. None for one that holds until the parcel moves on.
    func endsAt(after now: Date = Date()) -> Date? {
        let calendar = Calendar.current
        let nextDay = { (day: String?) in day.flatMap(Self.date(of:)).flatMap { calendar.date(byAdding: .day, value: 1, to: $0) } }
        switch says {
        case .notToday:
            return nextDay(carrierDay ?? Self.dayNamed(outSince))
        case .byTime:
            guard let day = Self.dayNamed(outSince).flatMap(Self.date(of:)), let time,
                  let hour = Int(time.prefix(2)), let minute = Int(time.suffix(2)) else { return now }
            return calendar.date(bySettingHour: hour, minute: minute, second: 0, of: day)
        default:
            return nextDay(says == .sooner ? carrierDay : day)
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
}

/// The strip in Peek AI's sheet: days with their chance, the carrier's date ringed; or, out for delivery, two hours to
/// a cell.
struct PeekAiStrip: Equatable {
    enum Kind { case days, hours }

    struct Cell: Equatable, Identifiable {
        let key: String
        /// The small line: a weekday, or nothing for hours.
        let over: String
        /// The bold line: the day of the month, or the hour.
        let main: String
        let likely: Bool
        let maybe: Bool
        let carrier: Bool
        let past: Bool
        var id: String { key }
    }

    let kind: Kind
    let cells: [Cell]
}

extension Localizer {
    /// A wall clock Peek AI names ("15:30"), or the hour on a time it gives with its offset, as the reader writes times.
    private func peekAiClock(_ value: String) -> String {
        deliveryWallClock(value.count > 5 ? value.dropFirst(11).prefix(5) : Substring(value))
    }

    private func peekAiCalendar(_ day: String) -> String {
        PeekAi.date(of: day).map(shortDate) ?? day
    }

    /// The few words after the name on a card: "unlikely" beside the carrier's date when it says today (`estimate`, the
    /// carrier's estimate the card shows, as the carrier wrote it), "not today" where Peek AI stands alone, else what it
    /// expects ("by 16:00", "by Thu 26 Nov").
    func peekAiCardWords(_ ai: PeekAi, estimate: String?, now: Date = Date()) -> String {
        switch ai.says {
        case .notToday:
            let today = estimate.flatMap(DateParser.deliveryDate).map { Calendar.current.isDate($0, inSameDayAs: now) } ?? false
            return text(today ? "peekAi.card.unlikely" : "peekAi.card.notToday")
        case .byTime: return text("peekAi.card.byTime", ["time": peekAiClock(ai.time ?? "")])
        case .byDay: return ai.day.map { text("peekAi.card.byDay", ["date": peekAiCalendar($0)]) } ?? text("peekAi.card.todayOrTomorrow")
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
        case .byDay: return ai.day.map { text("peekAi.claim.byDay", ["date": peekAiCalendar($0)]) } ?? text("peekAi.claim.todayOrTomorrow")
        case .todayOrTomorrow: return text("peekAi.claim.todayOrTomorrow")
        case .thisWeek: return text("peekAi.claim.thisWeek")
        case .byEndOfNextWeek: return text("peekAi.claim.byEndOfNextWeek")
        case .sooner:
            guard let day = ai.day else { return text("peekAi.claim.sooner") }
            let tomorrow = Calendar.current.date(byAdding: .day, value: 1, to: now).map(PeekAi.day(of:))
            return day == tomorrow ? text("peekAi.claim.soonerTomorrow") : text("peekAi.claim.soonerFrom", ["date": peekAiCalendar(day)])
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
        case .noOutForDelivery: return text("peekAi.carrier.saysToday", ["carrier": carrier])
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
        let calendar = Calendar.current
        let days = calendar.dateComponents([.day], from: calendar.startOfDay(for: then), to: calendar.startOfDay(for: now)).day ?? 0
        return days < 14
            ? format.localizedString(from: DateComponents(day: -days))
            : format.localizedString(from: DateComponents(weekOfMonth: -(days / 7)))
    }

    /// Why Peek AI thinks so, about this parcel: one or two sentences.
    func peekAiWhy(_ ai: PeekAi, carrier: String, now: Date = Date()) -> String {
        switch ai.why {
        case .noOutForDelivery: return text("peekAi.why.noOutForDelivery", ["carrier": carrier])
        case .outForDelivery: return text("peekAi.why.outForDelivery", ["carrier": carrier, "time": peekAiClock(ai.outSince ?? "")])
        case .lateOut: return text("peekAi.why.lateOut", ["carrier": carrier])
        case .missedDate: return text("peekAi.why.missedDate", ["carrier": carrier])
        case .ahead: return text("peekAi.why.ahead", ["carrier": carrier, "date": ai.carrierDay.map(peekAiCalendar) ?? ""])
        case .noDate:
            let first = text("peekAi.why.noDate", ["carrier": carrier])
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
    }

    /// How often Peek AI was right on parcels like this one: "Right 8 times in 10 at this hour."
    func peekAiRecord(_ ai: PeekAi) -> String {
        // Rounded down, so it never says more than it was.
        let n = min(9, max(1, Int((ai.sure * 10 + 1e-9).rounded(.down))))
        return text(ai.says == .notToday ? "peekAi.sure.hour" : "peekAi.sure.parcels", ["n": n])
    }

    /// Days with their chance, the carrier's date ringed; or, out for delivery, two hours to a cell, the past ones marked.
    func peekAiStrip(_ ai: PeekAi, now: Date = Date()) -> PeekAiStrip? {
        if let hours = ai.hours, !hours.isEmpty {
            let clock = Calendar.current.dateComponents([.hour, .minute], from: now)
            let hour = Double(clock.hour ?? 0) + Double(clock.minute ?? 0) / 60
            let cells = stride(from: 0, to: hours.count, by: 2).map { index in
                let pair = hours[index..<min(index + 2, hours.count)]
                let first = pair[index]
                let share = pair.reduce(0) { $0 + $1.share }
                return PeekAiStrip.Cell(
                    key: first.time, over: "", main: deliveryWallClock(Substring(first.time)),
                    likely: share >= 0.15, maybe: share >= 0.06 && share < 0.15, carrier: false,
                    past: (Double(first.time.prefix(2)) ?? 0) + Double(pair.count) <= hour
                )
            }
            return PeekAiStrip(kind: .hours, cells: cells)
        }
        guard let days = ai.days, !days.isEmpty else { return nil }
        return PeekAiStrip(kind: .days, cells: days.map { entry in
            let date = PeekAi.date(of: entry.day)
            return PeekAiStrip.Cell(
                key: entry.day, over: date.map(shortWeekday) ?? "",
                main: date.map { String(Calendar.current.component(.day, from: $0)) } ?? "",
                likely: entry.p >= 0.25, maybe: entry.p >= 0.08 && entry.p < 0.25, carrier: entry.day == ai.carrierDay, past: false
            )
        })
    }
}
