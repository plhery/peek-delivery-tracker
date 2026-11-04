import Foundation

/// A delivered push's sentence with its time on this device's clock, wherever the device is. The server sends the
/// delivery's instant (`scan_at`), its sentence for a delivery today (`body_same_day`, with `{{time}}`) and for another
/// day (`body_other_day`, with `{{date}}` and `{{time}}`), and the language of its date (`date_locale`), as
/// `deviceClockFields` in src/server/push.ts writes them. The notification service extension fills them in as the push
/// arrives. Nil keeps the server's own sentence: a push without them, or a delivery later than this device's clock.
enum DeviceClock {
    static func body(_ userInfo: [AnyHashable: Any], now: Date = Date(), timeZone: TimeZone = .current) -> String? {
        guard let sameDay = userInfo["body_same_day"] as? String, let otherDay = userInfo["body_other_day"] as? String,
              let scanned = (userInfo["scan_at"] as? String).flatMap(instant), scanned <= now else { return nil }
        var calendar = Calendar(identifier: .gregorian)
        calendar.timeZone = timeZone
        let clock = calendar.dateComponents([.hour, .minute], from: scanned)
        let time = "\(twoDigits(clock.hour ?? 0)):\(twoDigits(clock.minute ?? 0))"
        let day = DateFormatter()
        let tag = userInfo["date_locale"] as? String ?? ""
        day.locale = tag.isEmpty ? .current : Locale(identifier: tag)
        day.timeZone = timeZone
        day.setLocalizedDateFormatFromTemplate("dMMMM")
        return (calendar.isDate(scanned, inSameDayAs: now) ? sameDay : otherDay)
            .replacingOccurrences(of: "{{time}}", with: time)
            .replacingOccurrences(of: "{{date}}", with: day.string(from: scanned))
    }

    /// `2026-09-06T23:51:00.000Z`, as Luxon's `toISO` writes it, or without its fraction.
    private static func instant(_ text: String) -> Date? {
        let fractional = ISO8601DateFormatter()
        fractional.formatOptions = [.withInternetDateTime, .withFractionalSeconds]
        return fractional.date(from: text) ?? ISO8601DateFormatter().date(from: text)
    }

    private static func twoDigits(_ value: Int) -> String { value < 10 ? "0\(value)" : "\(value)" }
}
