// Writes android/core/src/main/kotlin/com/plhery/peekdeliverytracker/core/FormatData.kt: what Foundation writes dates,
// amounts of money and distances with in each of the app's languages, for Android, whose phone ICU and JVM bring their
// own CLDR ("Sept" for September in English, "1.234,50 €" in Italian on Android 12). For `Localizer.shortDate`, the
// "EEE" and "MMM" a Gregorian DateFormatter writes and the period its "dMMM" template puts after the day; for
// `Localizer.abbreviatedDateTime`, the pattern `Date.FormatStyle(date: .abbreviated, time: .shortened)` writes in and
// its day periods; for `value.formatted(.currency(code:).locale(_:))`, each language's separators, grouping and minus, and the symbol of
// every currency in use (Foundation's common ones and each region's), with Apple's fraction digits; for
// `ParcelRoute.formattedKilometres`, the grouping and unit of kilometres. Before writing, it puts dates, amounts and distances
// together from the tables as FoundationText.kt and Core.kt do, and stops at the first that Foundation writes otherwise.
//
// Run it on a Mac from the repository root: `swift android/tools/FormatData.swift`. Run it again when the app gains a
// language, and after each major macOS release, whose formats are the ones that year's iOS writes.
import Foundation

let output = URL(fileURLWithPath: #filePath)
    .deletingLastPathComponent()
    .appendingPathComponent("../core/src/main/kotlin/com/plhery/peekdeliverytracker/core/FormatData.kt")
    .standardizedFileURL

// AppLanguage's codes and `locale` (Core.swift), and the Kotlin enum's names.
let languages = ["en", "de", "fr", "it", "es", "pt", "pl"]
func locale(_ language: String) -> Locale { Locale(identifier: language == "pt" ? "pt-PT" : language) }
func enumName(_ language: String) -> String { language.prefix(1).uppercased() + language.dropFirst() }

func fail(_ message: String) -> Never {
    FileHandle.standardError.write(Data((message + "\n").utf8))
    exit(1)
}

/// Text as a Kotlin string literal, anything outside printable ASCII written as `\uXXXX`.
func kotlin(_ text: String) -> String {
    var result = "\""
    for unit in text.utf16 {
        switch unit {
        case 0x22: result += "\\\""
        case 0x24: result += "\\$"
        case 0x5C: result += "\\\\"
        case 0x20...0x7E: result += String(UnicodeScalar(UInt8(unit)))
        default: result += String(format: "\\u%04X", unit)
        }
    }
    return result + "\""
}

// MARK: Dates

var utcCalendar = Calendar(identifier: .gregorian)
utcCalendar.timeZone = TimeZone(identifier: "UTC")!

func date(_ year: Int, _ month: Int, _ day: Int, _ hour: Int = 12) -> Date {
    utcCalendar.date(from: DateComponents(year: year, month: month, day: day, hour: hour))!
}

/// `Localizer.formatter(_:gregorian:)`, in UTC.
func formatter(_ pattern: String, _ language: String, gregorian: Bool = true) -> DateFormatter {
    let formatter = DateFormatter()
    formatter.locale = locale(language)
    if gregorian { formatter.calendar = Calendar(identifier: .gregorian) }
    formatter.timeZone = TimeZone(identifier: "UTC")!
    formatter.dateFormat = pattern
    return formatter
}

struct Dates {
    let weekdays: [String]
    let months: [String]
    let dayPeriod: String
}

func dates(_ language: String) -> Dates {
    let weekday = formatter("EEE", language), month = formatter("MMM", language)
    let template = DateFormatter()
    template.locale = locale(language)
    template.setLocalizedDateFormatFromTemplate("dMMM")
    let dates = Dates(
        // 1 February 2026 is a Sunday.
        weekdays: (1...7).map { weekday.string(from: date(2026, 2, $0)) },
        months: (1...12).map { month.string(from: date(2026, $0, 15)) },
        dayPeriod: template.dateFormat.contains("d.") ? "." : ""
    )
    // Each day of four years, at a time that moves on by seven minutes a day: the day and the clock time are digits.
    let day = formatter("d", language), clock = formatter("HH:mm", language, gregorian: false)
    var moment = date(2024, 1, 1, 0)
    while moment < date(2028, 1, 1, 0) {
        let fields = utcCalendar.dateComponents([.weekday, .month, .day, .hour, .minute], from: moment)
        let expected = [
            dates.weekdays[fields.weekday! - 1], dates.months[fields.month! - 1], String(fields.day!),
            String(format: "%02d:%02d", fields.hour!, fields.minute!),
        ]
        let written = [weekday.string(from: moment), month.string(from: moment), day.string(from: moment), clock.string(from: moment)]
        if written != expected { fail("\(language) \(moment): Foundation writes \(written), the tables \(expected)") }
        moment += 86_400 + 420
    }
    return dates
}

// MARK: Dates with their time

/// What `Date.FormatStyle(date: .abbreviated, time: .shortened)` writes in: the pattern of the "yMMMdjmm" template,
/// its hour in as few digits as it takes, as the style's `.shortened` asks, and what its "a" writes before and after
/// noon.
struct DateTimes {
    let pattern: String
    let dayPeriods: [String]
}

/// A pattern's parts, as `foundationPattern` (Core.kt) reads them: a run of one ASCII letter is a field, text between
/// apostrophes is written as it is, and two apostrophes are one.
func patternParts(_ pattern: String) -> [(field: Bool, text: String)] {
    var parts: [(field: Bool, text: String)] = []
    let characters = Array(pattern)
    var literal = ""
    var quoted = false
    var index = 0
    while index < characters.count {
        let character = characters[index]
        if character == "'" {
            if index + 1 < characters.count && characters[index + 1] == "'" {
                literal.append("'")
                index += 2
            } else {
                quoted.toggle()
                index += 1
            }
        } else if !quoted && character.isASCII && character.isLetter {
            if !literal.isEmpty { parts.append((false, literal)); literal = "" }
            var end = index
            while end < characters.count && characters[end] == character { end += 1 }
            parts.append((true, String(characters[index..<end])))
            index = end
        } else {
            literal.append(character)
            index += 1
        }
    }
    if !literal.isEmpty { parts.append((false, literal)) }
    return parts
}

/// `foundationPattern` (Core.kt): the moment's fields in UTC, written in `pattern` with the tables' names.
func modelDateTime(_ pattern: String, _ moment: Date, _ dates: Dates, _ dayPeriods: [String]) -> String {
    let fields = utcCalendar.dateComponents([.year, .month, .day, .hour, .minute], from: moment)
    let hour = fields.hour!
    return patternParts(pattern).map { part in
        guard part.field else { return part.text }
        switch part.text {
        case "d": return String(fields.day!)
        case "MM": return String(format: "%02d", fields.month!)
        case "MMM": return dates.months[fields.month! - 1]
        case "y": return String(fields.year!)
        case "H": return String(hour)
        case "h": return String(hour % 12 == 0 ? 12 : hour % 12)
        case "mm": return String(format: "%02d", fields.minute!)
        case "a": return dayPeriods[hour < 12 ? 0 : 1]
        default: fail("\(pattern): no model for \(part.text)")
        }
    }.joined()
}

func dateTimes(_ language: String, _ dates: Dates) -> DateTimes {
    let template = DateFormatter()
    template.locale = locale(language)
    template.setLocalizedDateFormatFromTemplate("yMMMdjmm")
    let pattern = template.dateFormat.replacingOccurrences(of: "HH", with: "H").replacingOccurrences(of: "hh", with: "h")
    let period = formatter("a", language)
    let times = DateTimes(pattern: pattern, dayPeriods: [period.string(from: date(2026, 2, 1, 9)), period.string(from: date(2026, 2, 1, 15))])
    let style = Date.FormatStyle(
        date: .abbreviated, time: .shortened, locale: locale(language), calendar: Calendar(identifier: .gregorian),
        timeZone: TimeZone(identifier: "UTC")!
    )
    // Each day of four years, at a time that moves on by seven minutes a day.
    var moment = date(2024, 1, 1, 0)
    while moment < date(2028, 1, 1, 0) {
        let written = style.format(moment), expected = modelDateTime(pattern, moment, dates, times.dayPeriods)
        if written != expected { fail("\(language) \(moment): Foundation writes \(written), the tables \(expected)") }
        moment += 86_400 + 420
    }
    return times
}

// MARK: Numbers

struct Digits {
    let decimal: String
    let group: String
    /// The fewest whole digits that are grouped: 4, or 5 where "1234" stays whole.
    let groupingFrom: Int
    let minus: String
}

/// The whole and fraction digits ICU writes for a magnitude: the shortest decimal that reads back as it (Swift's
/// description), rounded half to even to `fractionDigits`.
func plainDigits(_ magnitude: Double, _ fractionDigits: Int) -> (whole: String, fraction: String) {
    // The magnitude is 0.d0 d1 d2… × 10^exponent: digits[i] is worth 10^(exponent - 1 - i).
    var digits: [Int] = []
    var exponent = 0
    if magnitude != 0 {
        let parts = "\(magnitude)".split(separator: "e")
        let mantissa = parts[0]
        let point = mantissa.firstIndex(of: ".").map { mantissa.distance(from: mantissa.startIndex, to: $0) } ?? mantissa.count
        exponent = point + (parts.count > 1 ? Int(parts[1])! : 0)
        digits = mantissa.compactMap(\.wholeNumberValue)
        while digits.first == 0 { digits.removeFirst(); exponent -= 1 }
    }
    let kept = exponent + fractionDigits
    if kept < 0 {
        digits = []
    } else if kept < digits.count {
        let first = digits[kept]
        let tie = first == 5 && !digits[(kept + 1)...].contains { $0 != 0 }
        var head = Array(digits[..<kept])
        if first > 5 || (first == 5 && (!tie || (head.last ?? 0) % 2 == 1)) {
            var index = head.count - 1
            while index >= 0 && head[index] == 9 { head[index] = 0; index -= 1 }
            if index >= 0 { head[index] += 1 } else { head.insert(1, at: 0); exponent += 1 }
        }
        digits = head
    }
    func digit(_ index: Int) -> String { String(index >= 0 && index < digits.count ? digits[index] : 0) }
    let whole = exponent > 0 ? (0..<exponent).map(digit).joined() : "0"
    let fraction = (0..<fractionDigits).map { digit(exponent + $0) }.joined()
    return (whole, fraction)
}

func grouped(_ whole: String, _ digits: Digits) -> String {
    if whole.count < digits.groupingFrom { return whole }
    var groups: [String] = []
    var rest = Substring(whole)
    while rest.count > 3 {
        groups.insert(String(rest.suffix(3)), at: 0)
        rest = rest.dropLast(3)
    }
    groups.insert(String(rest), at: 0)
    return groups.joined(separator: digits.group)
}

/// A number's magnitude as ICU writes it: infinity, NaN, or its digits.
func number(_ value: Double, _ fractionDigits: Int, _ digits: Digits) -> String {
    if value.isNaN { return "NaN" }
    if value.isInfinite { return "\u{221E}" }
    let (whole, fraction) = plainDigits(abs(value), fractionDigits)
    return grouped(whole, digits) + (fractionDigits > 0 ? digits.decimal + fraction : "")
}

// Amounts the model is checked on, and their negatives: halves at each place, groupings, the largest and smallest.
let amounts: [Double] = [
    0, 0.004, 0.005, 0.015, 0.025, 0.0049999, 0.125, 0.5, 1, 1.005, 1.125, 1.5, 2.345, 2.5, 3.5, 7, 9.995, 12.5, 99.995,
    999.5, 999.995, 1_234, 1_234.5, 9_999.995, 12_345.67, 99_999.5, 123_456.789, 1_000_000, 0x1p53, 0x1p53 + 2, 0x1p60,
    1e17, 1.5e17, 1e20, 1e22, 1e23, 1e100, .greatestFiniteMagnitude, .leastNonzeroMagnitude, 1e-5, 0.00015,
    .infinity, .nan,
]
let checkedAmounts = amounts + amounts.map { -$0 }

// MARK: Money

func currency(_ value: Double, _ code: String, _ language: String) -> String {
    value.formatted(.currency(code: code).locale(locale(language)))
}

// The currencies in use: Foundation's common ones and each region's. Those it keeps for history (the lira, the escudo)
// are left to ICU, as are codes it does not know.
let regionCurrencies = Locale.Region.isoRegions.compactMap {
    Locale(components: Locale.Components(languageCode: "en", languageRegion: $0)).currency?.identifier
}
let codes = Set(Locale.commonISOCurrencyCodes + regionCurrencies).sorted()
if codes.contains(where: { $0.count != 3 || !$0.allSatisfy { ("A"..."Z").contains($0) } }) { fail("a code is not three capitals") }

// Apple's fraction digits, the same in every language: English writes them after a point.
let fractionDigits = Dictionary(uniqueKeysWithValues: codes.map { code -> (String, Int) in
    let text = currency(7, code, "en")
    guard let seven = text.range(of: "7") else { fail("en \(code): no 7 in \(text)") }
    let after = text[seven.upperBound...]
    return (code, after.hasPrefix(".") ? after.dropFirst().prefix { $0 == "0" }.count : 0)
})

/// The text on either side of 7 written with `digits` zeros after `decimal`.
func sides(_ text: String, _ fraction: Int, _ decimal: String, _ what: String) -> (before: String, after: String) {
    let number = "7" + (fraction > 0 ? decimal + String(repeating: "0", count: fraction) : "")
    guard let range = text.range(of: number) else { fail("\(what): no \(number) in \(text)") }
    return (String(text[..<range.lowerBound]), String(text[range.upperBound...]))
}

/// A language's separators, grouping and minus, as `write` writes 7 (with `fraction` digits), 1234, 12345, 1234567 and -7.
func digits(_ language: String, _ fraction: Int, _ write: (Double) -> String) -> (Digits, before: String, after: String) {
    let seven = write(7)
    guard let index = seven.firstIndex(of: "7") else { fail("\(language): no 7 in \(seven)") }
    let decimal = fraction > 0 ? String(seven[seven.index(after: index)...].prefix { $0 != "0" }) : ""
    let (before, after) = sides(seven, fraction, decimal, language)
    func digitsOf(_ text: String) -> String {
        guard text.hasPrefix(before), text.hasSuffix(after) else { fail("\(language): \(text) is not between \(before) and \(after)") }
        return String(text.dropFirst(before.count).dropLast(after.count))
    }
    let millions = digitsOf(write(1_234_567))
    let group = String(millions.dropFirst().prefix { !$0.isNumber })
    let groupingFrom = digitsOf(write(1_234)).contains(group) ? 4 : digitsOf(write(12_345)).contains(group) ? 5 : 0
    if group.isEmpty || groupingFrom == 0 { fail("\(language): no grouping in \(millions)") }
    let negative = write(-7)
    guard negative.hasSuffix(seven) else { fail("\(language): \(negative) is not a minus and \(seven)") }
    return (Digits(decimal: decimal, group: group, groupingFrom: groupingFrom, minus: String(negative.dropLast(seven.count))), before, after)
}

struct Money {
    let digits: Digits
    let currencyFirst: Bool
    /// The symbol of each currency not written as its code.
    let symbols: [String: String]
    /// Those of them that a no-break space keeps from the digits after them, as ICU spaces a symbol ending in a letter.
    let spaced: Set<String>
}

func money(_ language: String) -> Money {
    let (style, euroBefore, _) = digits(language, 2) { currency($0, "EUR", language) }
    let first = !euroBefore.isEmpty
    var symbols: [String: String] = [:]
    var spaced = Set<String>()
    for code in codes {
        let (before, after) = sides(currency(7, code, language), fractionDigits[code]!, style.decimal, "\(language) \(code)")
        if (first ? after : before) != "" { fail("\(language) \(code): written on both sides, \(before) and \(after)") }
        // Infinity is no digit, which ICU spaces a symbol from: the symbol alone stands on its side.
        let infinity = currency(.infinity, code, language)
        let symbol: String
        if first {
            guard infinity.hasSuffix("\u{221E}") else { fail("\(language) \(code): \(infinity) does not end in infinity") }
            symbol = String(infinity.dropLast())
            if before == symbol + "\u{A0}" {
                if symbol != code { spaced.insert(code) }
            } else if before != symbol || symbol == code {
                fail("\(language) \(code): \(before) before 7, \(symbol) before infinity")
            }
        } else {
            guard after.hasPrefix("\u{A0}"), infinity == "\u{221E}" + after else { fail("\(language) \(code): \(after) after 7, \(infinity)") }
            symbol = String(after.dropFirst())
        }
        if symbol != code { symbols[code] = symbol }
    }
    let money = Money(digits: style, currencyFirst: first, symbols: symbols, spaced: spaced)
    for code in codes {
        for amount in checkedAmounts {
            let expected = currency(amount, code, language)
            let written = modelCurrency(amount, code, money)
            if written != expected { fail("\(language) \(code) \(amount): Foundation writes \(expected), the tables \(written)") }
        }
    }
    return money
}

/// `foundationCurrency` (FoundationText.kt): the minus for any sign bit, -0.0 and NaN included, before everything; a
/// symbol before the number is spaced from digits alone.
func modelCurrency(_ value: Double, _ code: String, _ money: Money) -> String {
    let symbol = money.symbols[code] ?? code
    let sign = value.sign == .minus ? money.digits.minus : ""
    let written = number(value, fractionDigits[code]!, money.digits)
    if money.currencyFirst {
        let space = value.isFinite && (money.symbols[code] == nil || money.spaced.contains(code)) ? "\u{A0}" : ""
        return sign + symbol + space + written
    }
    return sign + written + "\u{A0}" + symbol
}

// MARK: Distances

func kilometres(_ value: Double, _ language: String) -> String {
    Measurement(value: value, unit: UnitLength.kilometers).formatted(
        .measurement(width: .abbreviated, usage: .asProvided, numberFormatStyle: .number.precision(.fractionLength(0)))
            .locale(locale(language))
    )
}

func distances(_ language: String) -> (Digits, unit: String) {
    let (style, before, unit) = digits(language, 0) { kilometres($0, language) }
    if !before.isEmpty { fail("\(language): \(before) before the kilometres") }
    for amount in checkedAmounts + [0.49999999999999994, 4.5, 1_000_000_000_000] {
        let expected = kilometres(amount, language)
        let written = (amount.sign == .minus ? style.minus : "") + number(amount, 0, style) + unit
        if written != expected { fail("\(language) \(amount) km: Foundation writes \(expected), the tables \(written)") }
    }
    return (style, unit)
}

// MARK: Kotlin

/// Entries on lines of at most 120 characters.
func lines(_ entries: [String], indent: String) -> String {
    var lines: [String] = []
    var line = indent
    for entry in entries {
        if line != indent && line.count + entry.count + 1 > 120 { lines.append(line); line = indent }
        line += (line == indent ? "" : " ") + entry
    }
    if line != indent { lines.append(line) }
    return lines.joined(separator: "\n")
}

func list(_ values: [String]) -> String { "listOf(" + values.map(kotlin).joined(separator: ", ") + ")" }

func digitsSource(_ digits: Digits) -> String {
    "Digits(decimal = \(kotlin(digits.decimal)), group = \(kotlin(digits.group)), groupingFrom = \(digits.groupingFrom), minus = \(kotlin(digits.minus)))"
}

var languageSources: [String] = []
for language in languages {
    let dates = dates(language)
    let times = dateTimes(language, dates)
    let money = money(language)
    let (distances, unit) = distances(language)
    let symbols = money.symbols.sorted(by: { $0.key < $1.key }).map { "\(kotlin($0.key)) to \(kotlin($0.value))," }
    let spaced = money.spaced.sorted().map(kotlin).joined(separator: ", ")
    languageSources.append("""
        private val \(language) = Language(
            weekdays = \(list(dates.weekdays)),
            months = \(list(dates.months)),
            dayPeriod = \(kotlin(dates.dayPeriod)),
            dateTime = \(kotlin(times.pattern)),
            dayPeriods = \(list(times.dayPeriods)),
            money = \(digitsSource(money.digits)),
            currencyFirst = \(money.currencyFirst),
            currencySymbols = mapOf(
    \(lines(symbols, indent: "            "))
            ),
            spacedSymbols = setOf(\(spaced)),
            distances = \(digitsSource(distances)),
            kilometres = \(kotlin(unit)),
        )
    """)
}

func wrapped(_ words: [String], indent: String, width: Int = 100) -> String {
    var lines: [String] = []
    var line = ""
    for word in words {
        if !line.isEmpty && line.count + word.count + 1 > width { lines.append(line); line = "" }
        line += (line.isEmpty ? "" : " ") + word
    }
    if !line.isEmpty { lines.append(line) }
    return lines.enumerated().map { index, line in
        indent + "\"" + line + (index < lines.count - 1 ? " \" +" : "\"")
    }.joined(separator: "\n")
}

let macOS = ProcessInfo.processInfo.operatingSystemVersion
let otherDigits = fractionDigits.filter { $0.value != 2 }.sorted { $0.key < $1.key }
let source = """
// Generated by android/tools/FormatData.swift from the Foundation of macOS \(macOS.majorVersion).\(macOS.minorVersion): do not edit.
package com.plhery.peekdeliverytracker.core

/**
 * How the iPhone writes dates, amounts of money and distances in each of the app's languages, as Foundation's
 * formatters write them, where a phone's ICU and the JVM have CLDR of their own.
 */
internal object FormatData {
    /**
     * How a language writes a number: its decimal separator (none for whole kilometres), its group separator, the
     * fewest whole digits it groups (4, or 5 where "1234" stays whole) and its minus.
     */
    class Digits(val decimal: String, val group: String, val groupingFrom: Int, val minus: String)

    /**
     * A language's formats. `weekdays`, Sunday first, and `months` are what a Gregorian DateFormatter writes for "EEE"
     * and "MMM", and `dayPeriod` is what its "dMMM" template writes after the day. `dateTime` is the pattern
     * `Date.FormatStyle(date: .abbreviated, time: .shortened)` writes in, and `dayPeriods` what its "a" writes before
     * and after noon. A currency's symbol, which is its code unless `currencySymbols` has another, goes before the
     * number when `currencyFirst`, where ICU keeps it from digits with a no-break space if it is the code or in
     * `spacedSymbols`, and else after the number and a no-break space. `kilometres` follows a number of kilometres.
     */
    class Language(
        val weekdays: List<String>,
        val months: List<String>,
        val dayPeriod: String,
        val dateTime: String,
        val dayPeriods: List<String>,
        val money: Digits,
        val currencyFirst: Boolean,
        val currencySymbols: Map<String, String>,
        val spacedSymbols: Set<String>,
        val distances: Digits,
        val kilometres: String,
    )

    /** The currencies in use: Foundation's common ones (`Locale.commonISOCurrencyCodes`) and each region's. */
    val currencyCodes: Set<String> = (
\(wrapped(codes, indent: "        "))
    ).split(' ').toSet()

    /** Apple's fraction digits for each currency that has other than two. */
    val fractionDigits: Map<String, Int> = mapOf(
\(lines(otherDigits.map { "\(kotlin($0.key)) to \($0.value)," }, indent: "        "))
    )

    fun language(language: AppLanguage): Language = when (language) {
\(languages.map { "        AppLanguage.\(enumName($0)) -> \($0)" }.joined(separator: "\n"))
    }

\(languageSources.joined(separator: "\n\n"))
}

"""
try source.write(to: output, atomically: true, encoding: .utf8)
print("Wrote \(languages.count) languages, \(codes.count) currencies to \(output.path)")
