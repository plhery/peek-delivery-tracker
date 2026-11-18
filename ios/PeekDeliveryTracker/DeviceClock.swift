import Foundation

/// A delivered push's sentence with its time on this device's clock, wherever the device is. The server sends the
/// delivery's instant (`scan_at`), its sentence for a delivery today (`body_same_day`, with `{{time}}`) and for another
/// day (`body_other_day`, with `{{date}}` and `{{time}}`), and the language of its date (`date_locale`), as
/// `deviceClockFields` in src/server/push.ts writes them. The notification service extension fills them in as the push
/// arrives. Nil keeps the server's own sentence: a push without them, or a delivery later than this device's clock.
enum DeviceClock {
    /// The clock chosen in the app's Settings (`ClockPreference`), which the app also keeps in the App Group.
    static let preferenceKey = "sdt.native.clock"

    /// Whether times are written on the 24-hour clock: as chosen in the app, else as the device's Settings say.
    static func uses24HourTime(in defaults: UserDefaults?, locale: Locale = .autoupdatingCurrent) -> Bool {
        ClockPreference(stored: defaults?.string(forKey: preferenceKey)).uses24HourTime(system: systemUses24HourTime(locale))
    }

    /// Whether the system writes hours on the 24-hour clock, which follows the 24-Hour Time switch in Settings. A
    /// 12-hour clock's hour is `h` or `K` outside quoted text: "h a", "Bh時", but not "HH 'h'".
    static func systemUses24HourTime(_ locale: Locale = .autoupdatingCurrent) -> Bool {
        let pattern = DateFormatter.dateFormat(fromTemplate: "j", options: 0, locale: locale) ?? "HH"
        return !pattern.split(separator: "'", omittingEmptySubsequences: false).enumerated().contains { part in
            part.offset.isMultiple(of: 2) && part.element.contains { $0 == "h" || $0 == "K" }
        }
    }

    static func sharedDefaults(bundle: Bundle = .main) -> UserDefaults? {
        guard let group = bundle.object(forInfoDictionaryKey: "SDTAppGroupIdentifier") as? String, !group.isEmpty else {
            return nil
        }
        return UserDefaults(suiteName: group)
    }

    static func body(
        _ userInfo: [AnyHashable: Any], now: Date = Date(), timeZone: TimeZone = .current, use24HourTime: Bool = true
    ) -> String? {
        guard let sameDay = userInfo["body_same_day"] as? String, let otherDay = userInfo["body_other_day"] as? String,
              let scanned = (userInfo["scan_at"] as? String).flatMap(instant), scanned <= now else {
            return use24HourTime ? nil : userInfo["body_12_hour"] as? String
        }
        var calendar = Calendar(identifier: .gregorian)
        calendar.timeZone = timeZone
        let day = DateFormatter()
        let tag = userInfo["date_locale"] as? String ?? ""
        day.locale = tag.isEmpty ? .current : Locale(identifier: tag)
        day.timeZone = timeZone
        day.setLocalizedDateFormatFromTemplate("dMMMM")
        let time: String
        if use24HourTime {
            let clock = calendar.dateComponents([.hour, .minute], from: scanned)
            time = "\(twoDigits(clock.hour ?? 0)):\(twoDigits(clock.minute ?? 0))"
        } else {
            let formatter = DateFormatter()
            formatter.locale = day.locale
            formatter.timeZone = timeZone
            formatter.dateFormat = day.locale.language.languageCode?.identifier == "zh" ? "ah:mm" : "h:mm a"
            if day.locale.language.languageCode?.identifier == "en" { (formatter.amSymbol, formatter.pmSymbol) = ("AM", "PM") }
            time = formatter.string(from: scanned)
        }
        return fill(calendar.isDate(scanned, inSameDayAs: now) ? sameDay : otherDay, time: time, date: day.string(from: scanned))
    }

    /// Writes the time and the day into the sentence alone: the end of the first line, after the parcel's name and
    /// its " · " (`notificationBody` in src/server/push.ts), or the whole line without a name. The name and the place
    /// on the line under it read as they were written, even with `{{time}}` in them.
    private static func fill(_ body: String, time: String, date: String) -> String {
        let lineEnd = body.firstIndex(of: "\n") ?? body.endIndex
        let start = body[..<lineEnd].range(of: " · ", options: .backwards)?.upperBound ?? body.startIndex
        let sentence = body[start..<lineEnd]
            .replacingOccurrences(of: "{{time}}", with: time)
            .replacingOccurrences(of: "{{date}}", with: date)
        return String(body[..<start]) + sentence + String(body[lineEnd...])
    }

    /// `2026-09-06T23:51:00.000Z`, as Luxon's `toISO` writes it, or without its fraction.
    private static func instant(_ text: String) -> Date? {
        let fractional = ISO8601DateFormatter()
        fractional.formatOptions = [.withInternetDateTime, .withFractionalSeconds]
        return fractional.date(from: text) ?? ISO8601DateFormatter().date(from: text)
    }

    private static func twoDigits(_ value: Int) -> String { value < 10 ? "0\(value)" : "\(value)" }
}

/// The clock the app writes times on: the system's, or one chosen over it. A missing or unknown value is automatic.
enum ClockPreference: String, CaseIterable {
    case automatic = "auto"
    case twentyFourHour = "24"
    case twelveHour = "12"

    init(stored: String?) { self = stored.flatMap(Self.init(rawValue:)) ?? .automatic }

    func uses24HourTime(system: Bool) -> Bool {
        switch self {
        case .automatic: system
        case .twentyFourHour: true
        case .twelveHour: false
        }
    }
}
