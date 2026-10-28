package com.plhery.peekdeliverytracker.core

import java.math.BigDecimal
import java.math.BigInteger
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.zone.ZoneRules
import kotlin.math.floor

/**
 * The date formatters DateParser reads with, as Foundation runs them. ISO8601DateFormatter and DateFormatter both
 * hand the text to ICU's SimpleDateFormat through CoreFoundation, which reads it in strict mode into a lenient
 * Gregorian calendar: a field may have one digit or many, a sign or another script's digits, 24:00 is the next
 * day and 30 February is 2 March, and the calendar is Julian before October 1582. This follows ICU as Apple ships
 * it and CoreFoundation's code around it, so a phone reads a date as an iPhone does.
 */
internal object FoundationDates {
    private sealed interface Part
    private enum class Field : Part { Year, Month, Day, Hour, Minute, Second, Fraction, Zone }
    private class Literal(val char: Char) : Part

    // yyyy-MM-dd'T'HH:mm:ss.SSSXXXXX, yyyy-MM-dd'T'HH:mm:ssXXXXX and yyyy-MM-dd.
    private val internetWithFraction: List<Part> = listOf(
        Field.Year, Literal('-'), Field.Month, Literal('-'), Field.Day, Literal('T'),
        Field.Hour, Literal(':'), Field.Minute, Literal(':'), Field.Second, Literal('.'), Field.Fraction, Field.Zone,
    )
    private val internet: List<Part> = internetWithFraction.subList(0, 11) + Field.Zone
    private val calendarDay: List<Part> = internetWithFraction.subList(0, 5)

    /** CoreFoundation reads at most this many UTF-16 units of the text. */
    private const val MAX_LENGTH = 1024
    private const val DAY_MILLIS = 86_400_000L
    private const val EPOCH_JULIAN_DAY = 2_440_588
    private const val JANUARY_1_YEAR_1_JULIAN_DAY = 1_721_426
    private const val CUTOVER_JULIAN_DAY = 2_299_161
    private const val CUTOVER_YEAR = 1582
    private const val REFERENCE_DATE = 978_307_200L

    /** The formatters' two-digit start date, 1 January 1950 UTC, in milliseconds. */
    private const val TWO_DIGIT_START = -631_152_000_000.0
    private val monthStarts = intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
    private val leapMonthStarts = intArrayOf(0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335)
    private val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December",
    )
    private val shortMonths = months.map { it.take(3) }

    private class Fields {
        var year = 0
        var month = 0
        var dayOfMonth = 1
        var hour = 0
        var minute = 0
        var second = 0
        var millisecond = 0
        var offset = 0
    }

    /** `ISO8601DateFormatter.date(from:)` with `.withInternetDateTime`, and `.withFractionalSeconds` when asked. */
    fun internetDateTime(value: String, fractionalSeconds: Boolean): Instant? {
        // Whatever follows the zone is left unread.
        val (fields, _) = parse(value.take(MAX_LENGTH), if (fractionalSeconds) internetWithFraction else internet) ?: return null
        return instant(fields, FixedZone(fields.offset))
    }

    /** `date(from:)` of an en_US_POSIX Gregorian DateFormatter with the format "yyyy-MM-dd", in `zone`. */
    fun day(value: String, zone: ZoneId): Instant? {
        val (fields, end) = parse(value.take(MAX_LENGTH), calendarDay) ?: return null
        // DateFormatter takes the date only when nothing but whitespace follows it.
        if ((end until value.length).any { !isWhitespaceScalar(value[it].code) }) return null
        return instant(fields, RulesZone(zone.rules))
    }

    /** A date's day, as a number that counts days, and its month (1 to 12), in a Gregorian `Calendar` in the zone. */
    class CalendarDate(val day: Long, val month: Int)

    /** Where Foundation's Gregorian calendar puts a date in the zone: Julian before October 1582. */
    fun calendarDate(date: Instant, zone: ZoneId): CalendarDate? {
        val time = (date.timeIntervalSinceReferenceDate + REFERENCE_DATE) * 1000.0
        val rules = RulesZone(zone.rules)
        val civil = civilDate(time, rules) ?: return null
        return CalendarDate(Math.floorDiv((time + rules.offsetAt(time)).toLong(), DAY_MILLIS), civil.month + 1)
    }

    private val weekdays = listOf(
        listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
        listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"),
        listOf("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"),
        listOf("S", "M", "T", "W", "T", "F", "S"),
    )

    /** A zone name "zzz" reads in English: a fixed offset in milliseconds, and the zone that stands for it. */
    private class ZoneName(val name: String, hours: Int, val zone: String) {
        val offset = hours * HOUR_MILLIS
    }

    private val zoneNames = listOf(
        ZoneName("pst", -8, "America/Los_Angeles"), ZoneName("pdt", -7, "America/Los_Angeles"),
        ZoneName("mst", -7, "America/Denver"), ZoneName("mdt", -6, "America/Denver"),
        ZoneName("cst", -6, "America/Chicago"), ZoneName("cdt", -5, "America/Chicago"),
        ZoneName("est", -5, "America/New_York"), ZoneName("edt", -4, "America/New_York"),
        ZoneName("akst", -9, "America/Anchorage"), ZoneName("akdt", -8, "America/Anchorage"),
        ZoneName("hast", -10, "America/Adak"), ZoneName("hadt", -9, "America/Adak"),
        ZoneName("hst", -10, "Pacific/Honolulu"),
        ZoneName("ast", -4, "America/Halifax"), ZoneName("adt", -3, "America/Halifax"),
    )

    /** The largest year the formatter's strict calendar takes. */
    private const val MAX_STRICT_YEAR = 144_683

    /**
     * `date(from:)` of an en_US_POSIX DateFormatter in GMT with the format "EEE, dd MMM yyyy HH:mm:ss zzz", as the
     * seconds since 2001 the Date holds. Unlike the formatters above, its calendar is strict: 31 September, 24:00
     * and the year 0 read as nothing, while a weekday that is not the date's own passes unchecked. Names and
     * separators are read as SimpleDateFormat reads them in strict mode ("Monday", "mon" or "M" for the weekday,
     * "September" for "Sep", a missing comma), and the zone is GMT, UTC or UT with or without an offset, an ISO
     * offset, or one of the North American names English has for a zone (PST, EDT…), each read as its fixed offset.
     */
    fun httpDate(value: String): Double? {
        val text = value.take(MAX_LENGTH)
        var position = fieldStart(text, 0) ?: return null
        position = weekday(text, position) ?: return null
        position = matchLiterals(text, position, ", ", Field.Day) ?: return null
        val day = number(text, fieldStart(text, position) ?: return null) ?: return null
        position = matchLiterals(text, day.end, " ", Field.Month) ?: return null
        val (month, monthEnd) = monthName(text, fieldStart(text, position) ?: return null) ?: return null
        position = matchLiterals(text, monthEnd, " ", Field.Year) ?: return null
        val year = number(text, fieldStart(text, position) ?: return null) ?: return null
        position = matchLiterals(text, year.end, " ", Field.Hour) ?: return null
        val hour = number(text, fieldStart(text, position) ?: return null) ?: return null
        position = matchLiterals(text, hour.end, ":", Field.Minute) ?: return null
        val minute = number(text, fieldStart(text, position) ?: return null) ?: return null
        position = matchLiterals(text, minute.end, ":", Field.Second) ?: return null
        val second = number(text, fieldStart(text, position) ?: return null) ?: return null
        position = matchLiterals(text, second.end, " ", Field.Zone) ?: return null
        val zoneStart = fieldStart(text, position) ?: return null
        // GMT and its offsets first, then the names, then ISO offsets, as TimeZoneFormat tries them.
        val gmt = localizedGmtOffset(text, zoneStart)
        val named = if (gmt == null) zoneName(text, zoneStart) else null
        val (offset, end) = gmt ?: named?.let { (name, end) -> name.offset to end } ?: isoOffset(text, zoneStart) ?: return null
        // DateFormatter takes the date only when nothing but whitespace follows it.
        if ((end until value.length).any { !isWhitespaceScalar(value[it].code) }) return null
        if (year.value !in 1..MAX_STRICT_YEAR || day.value !in 1..monthLength(year.value, month)) return null
        if (hour.value !in 0..23 || minute.value !in 0..59 || second.value !in 0..59) return null
        val millisInDay = ((hour.value * 60.0 + minute.value) * 60.0 + second.value) * 1000.0
        val time = time(year.value, month, day.value, millisInDay, FixedZone(offset)) ?: return null
        // A name leaves its zone, daylight saving time and all, to the calendar that reads a two-digit year.
        return absoluteTime(time, named?.let { RulesZone(ZoneId.of(it.first.zone).rules) } ?: FixedZone(offset))
    }

    /** The days in a month, of the Julian calendar before 1582 as ICU's Gregorian one has it. */
    private fun monthLength(year: Int, month: Int): Int {
        val leap = year % 4 == 0 && (year < CUTOVER_YEAR || year % 100 != 0 || year % 400 == 0)
        val starts = if (leap) leapMonthStarts else monthStarts
        val next = if (month == 11) (if (leap) 366 else 365) else starts[month + 1]
        return next - starts[month]
    }

    /** Where a field starts: SimpleDateFormat passes over whitespace and the left-to-right and right-to-left marks. */
    private fun fieldStart(text: String, from: Int): Int? {
        var start = from
        while (true) {
            if (start >= text.length) return null
            val scalar = text.codePointAt(start)
            if (!hasWhiteSpaceProperty(scalar) && scalar != 0x200E && scalar != 0x200F) return start
            start += Character.charCount(scalar)
        }
    }

    /** A weekday by name, which the date's own day overrides: wide, abbreviated, short, then narrow names. */
    private fun weekday(text: String, start: Int): Int? {
        for (names in weekdays) {
            val length = names.maxOf { foldedPrefixLength(text, start, it) }
            if (length > 0) return start + length
        }
        return null
    }

    /** Pattern_White_Space, which a literal's whitespace also matches. */
    private fun isPatternWhiteSpace(char: Char): Boolean =
        char in '\t'..'\r' || char == ' ' || char == '\u0085' || char == '\u200E' || char == '\u200F' ||
            char == '\u2028' || char == '\u2029'

    /**
     * A run of literal text in strict mode. Whitespace in the pattern needs some in the text, of any length, bidi
     * marks passed over; any other character must be there, unless both it and the character found are ones the
     * next field lets stand in (any letter will do for the one found), which ends the run after skipping those:
     * "Mon. 21" and "Mon 21" pass for "Mon, 21", while "Mon,21" and "Mon; 21" do not.
     */
    private fun matchLiterals(text: String, start: Int, literal: String, next: Field): Int? {
        var t = start
        var p = 0
        while (p < literal.length && t < text.length) {
            var needWhitespace = false
            while (p < literal.length && isPatternWhiteSpace(literal[p])) {
                needWhitespace = true
                p++
            }
            if (needWhitespace) {
                // The Arabic letter mark may stand anywhere in the run, which still needs whitespace of its own.
                var found = false
                while (t < text.length) {
                    val space = hasWhiteSpaceProperty(text[t].code) || isPatternWhiteSpace(text[t])
                    if (!space && !isBidiMark(text[t])) break
                    found = found || space
                    t++
                }
                if (!found) return null
                if (p >= literal.length) break
            }
            var index = t
            while (index < text.length && isBidiMark(text[index])) index++
            if (index < text.length && text[index] == literal[p]) {
                t = index + 1
                p++
                continue
            }
            val found = if (index < text.length) text[index] else '\uFFFF'
            if (!ignorable(next, literal[p].code) || !(ignorable(next, found.code) || Character.isLetter(found))) return null
            while (t < text.length && (isBidiMark(text[t]) || ignorable(next, text[t].code))) t++
            return t
        }
        return if (p < literal.length) null else t
    }

    /** One of `zoneNames`, matched without regard to case as ICU folds it, the longest first, and where it ends. */
    private fun zoneName(text: String, start: Int): Pair<ZoneName, Int>? {
        var best: ZoneName? = null
        for (zone in zoneNames) {
            if (start + zone.name.length > text.length) continue
            val matches = zone.name.indices.all {
                val char = text[start + it]
                val folded = when (char) {
                    '\u017F' -> 's'
                    '\u212A' -> 'k'
                    in 'A'..'Z' -> char + 32
                    else -> char
                }
                folded == zone.name[it]
            }
            if (matches && zone.name.length > (best?.name?.length ?: 0)) best = zone
        }
        return best?.let { it to start + it.name.length }
    }

    private fun parse(text: String, pattern: List<Part>): Pair<Fields, Int>? {
        val fields = Fields()
        var position = 0
        for ((index, part) in pattern.withIndex()) {
            position = when (part) {
                is Literal -> matchLiteral(text, position, part.char, pattern[index + 1] as Field)
                is Field -> parseField(text, position, part, fields)
            } ?: return null
        }
        return fields to position
    }

    private fun isBidiMark(char: Char): Boolean = char == '\u200E' || char == '\u200F' || char == '\u061C'

    /** The characters a field lets stand in for the separator before it. */
    private fun ignorable(field: Field, code: Int): Boolean = hasWhiteSpaceProperty(code) || when (field) {
        Field.Year, Field.Month, Field.Day -> code == '-'.code || code == ','.code || code == '.'.code || code == '/'.code
        Field.Hour, Field.Minute, Field.Second -> code == '-'.code || code == '.'.code || code == ':'.code
        Field.Fraction, Field.Zone -> false
    }

    /**
     * A separator. A different one passes when both it and the character found are ones the next field lets
     * stand in (any letter will do for the one found), and the run of those is skipped: "2026/09/27" is
     * 2026-09-27, while the 'T' and the '.' before the fraction have to be there.
     */
    private fun matchLiteral(text: String, start: Int, literal: Char, next: Field): Int? {
        if (start < text.length) {
            var index = start
            while (index < text.length && isBidiMark(text[index])) index++
            if (index < text.length && text[index] == literal) return index + 1
            val found = if (index < text.length) text[index] else '\uFFFF'
            if (!ignorable(next, literal.code) || !(ignorable(next, found.code) || Character.isLetter(found))) return null
        }
        var index = start
        while (index < text.length && (isBidiMark(text[index]) || ignorable(next, text[index].code))) index++
        return index
    }

    private fun parseField(text: String, from: Int, field: Field, fields: Fields): Int? {
        val start = fieldStart(text, from) ?: return null
        if (field == Field.Zone) {
            val (offset, end) = zone(text, start) ?: return null
            fields.offset = offset
            return end
        }
        val number = number(text, start)
        if (field == Field.Month && number == null) {
            val (month, end) = monthName(text, start) ?: return null
            fields.month = month
            return end
        }
        number ?: return null
        val value = number.value
        when (field) {
            Field.Year -> fields.year = value
            Field.Month -> if (value in 1..12) fields.month = value - 1 else return null
            Field.Day -> if (value in 1..31) fields.dayOfMonth = value else return null
            Field.Hour -> if (value in 0..24) fields.hour = value else return null
            Field.Minute -> if (value in 0..59) fields.minute = value else return null
            Field.Second -> if (value in 0..59) fields.second = value else return null
            Field.Fraction -> fields.millisecond = milliseconds(value, digitCount(text, start, number.end))
            Field.Zone -> return null
        }
        return number.end
    }

    /** The fraction's digits as milliseconds, by 32-bit arithmetic: ".5" is 500, ".123456" is 123. */
    private fun milliseconds(value: Int, digits: Int): Int {
        var result = value
        var count = digits
        if (count < 3) {
            while (count < 3) {
                result *= 10
                count++
            }
            return result
        }
        var divisor = 1
        while (count > 3) {
            divisor *= 10
            count--
        }
        // An ARM division by zero gives zero.
        return if (divisor == 0) 0 else result / divisor
    }

    private fun digitCount(text: String, start: Int, end: Int): Int {
        var count = 0
        var index = start
        while (index < end) {
            val scalar = text.codePointAt(index)
            if (Character.isDigit(scalar)) count++
            index += Character.charCount(scalar)
        }
        return count
    }

    private class ParsedNumber(val value: Int, val end: Int)

    private fun isBidiControl(scalar: Int): Boolean =
        scalar == 0x061C || scalar == 0x200E || scalar == 0x200F || scalar in 0x202A..0x202E || scalar in 0x2066..0x2069

    private fun isMinusSign(scalar: Int): Boolean = when (scalar) {
        0x2D, in 0x2010..0x2013, 0x207B, 0x208B, 0x2212, 0x2796, 0xFE63, 0xFF0D -> true
        else -> false
    }

    private fun isPlusSign(scalar: Int): Boolean = when (scalar) {
        0x2B, 0x207A, 0x208A, 0x2795, 0xFB29, 0xFE62, 0xFF0B -> true
        else -> false
    }

    private fun skipBidiControls(text: String, from: Int): Int {
        var index = from
        while (index < text.length && isBidiControl(text.codePointAt(index))) index += Character.charCount(text.codePointAt(index))
        return index
    }

    /** A run of decimal digits of any script, from `start`, and where it ends. */
    private fun digits(text: String, start: Int): Pair<String, Int> {
        val digits = StringBuilder()
        var index = start
        while (index < text.length) {
            val scalar = text.codePointAt(index)
            if (!Character.isDigit(scalar)) break
            digits.append(Character.forDigit(Character.digit(scalar, 10), 10))
            index += Character.charCount(scalar)
        }
        return digits.toString() to index
    }

    /**
     * The strict integer parse SimpleDateFormat reads a field's number with: bidi controls are passed over, a minus
     * sign may lead, the digits may be any script's, NaN and ∞ stand for a number, and an exponent may follow.
     */
    private fun number(text: String, start: Int): ParsedNumber? {
        var index = skipBidiControls(text, start)
        var negative = false
        if (index < text.length && isMinusSign(text.codePointAt(index))) {
            negative = true
            index = skipBidiControls(text, index + Character.charCount(text.codePointAt(index)))
        }
        var mantissa: String? = null
        when {
            index < text.length && Character.isDigit(text.codePointAt(index)) -> digits(text, index).let { (run, end) ->
                mantissa = run
                index = end
            }
            text.regionMatches(index, "NaN", 0, 3, ignoreCase = true) -> index += 3
            index < text.length && text[index] == '∞' -> index++
            else -> return null
        }
        var end = index
        var exponent = 0L
        var exponentOverflows = false
        // An exponent counts only with its digits: "2026E" and "2026E+" end before the E.
        val separator = skipBidiControls(text, end)
        if (separator + 1 < text.length && (text[separator] == 'E' || text[separator] == 'e')) {
            var cursor = skipBidiControls(text, separator + 1)
            var sign = 1L
            if (cursor < text.length) {
                val scalar = text.codePointAt(cursor)
                if (isMinusSign(scalar)) sign = -1L
                if (isMinusSign(scalar) || isPlusSign(scalar)) cursor += Character.charCount(scalar)
            }
            val (run, runEnd) = digits(text, skipBidiControls(text, cursor))
            if (run.isNotEmpty()) {
                end = runEnd
                val magnitude = run.trimStart('0').ifEmpty { "0" }
                if (magnitude.length > 10 || magnitude.toLong() > Int.MAX_VALUE) exponentOverflows = true
                else exponent = sign * magnitude.toLong()
            }
        }
        val digitsRead = mantissa ?: return ParsedNumber(0, end)
        if (exponentOverflows) return ParsedNumber(0, end)
        return ParsedNumber(formattableLong(BigInteger(digitsRead), exponent, negative), end)
    }

    /**
     * The number as ICU's Formattable hands it to SimpleDateFormat, whose inline `getLong()` reads the low 32 bits
     * of its union: the integer when the number is one that fits a Long, else the bits of the nearest double, so
     * that NaN, infinity and 0.5 read as 0 and 5e-324 as 1.
     */
    private fun formattableLong(digits: BigInteger, exponent: Long, negative: Boolean): Int {
        if (digits.signum() == 0) return 0
        val decimal = BigDecimal(digits).stripTrailingZeros()
        val integerDigits = decimal.precision() - decimal.scale() + exponent
        // Beyond a double's range: infinity or zero, whose bits end in 32 zeros.
        if (integerDigits > 400 || integerDigits < -400) return 0
        val value = decimal.scaleByPowerOfTen(exponent.toInt()).let { if (negative) it.negate() else it }
        if (value.scale() <= 0) {
            val integer = value.toBigIntegerExact()
            if (integer.bitLength() <= 63) return integer.toLong().toInt()
        }
        return java.lang.Double.doubleToRawLongBits(value.toDouble()).toInt()
    }

    /**
     * A month by name, in English, wide names before short ones and the longest match first. The text is
     * compared case-folded in full, so "SEPTEMBER", "ſep" and "Auguﬆ" count.
     */
    private fun monthName(text: String, start: Int): Pair<Int, Int>? {
        for (names in listOf(months, shortMonths)) {
            var best = -1
            var bestLength = 0
            for ((month, name) in names.withIndex()) {
                val length = foldedPrefixLength(text, start, name)
                if (length > bestLength) {
                    best = month
                    bestLength = length
                }
            }
            if (best >= 0) return best to start + bestLength
        }
        return null
    }

    /** The length of text from `start` that folds to all of `name`, else 0. */
    private fun foldedPrefixLength(text: String, start: Int, name: String): Int {
        var index = start
        var matched = 0
        while (matched < name.length && index < text.length) {
            val scalar = text.codePointAt(index)
            val folded = asciiFolding(scalar) ?: break
            if (!name.regionMatches(matched, folded, 0, folded.length, ignoreCase = true)) break
            matched += folded.length
            index += Character.charCount(scalar)
        }
        return if (matched == name.length) index - start else 0
    }

    /** The full case folding of a scalar that folds to ASCII letters only, else null. */
    private fun asciiFolding(scalar: Int): String? = when (scalar) {
        in 'A'.code..'Z'.code, in 'a'.code..'z'.code -> scalar.toChar().lowercase()
        0x017F -> "s"
        0x212A -> "k"
        0x00DF, 0x1E9E -> "ss"
        0xFB00 -> "ff"
        0xFB01 -> "fi"
        0xFB02 -> "fl"
        0xFB03 -> "ffi"
        0xFB04 -> "ffl"
        0xFB05, 0xFB06 -> "st"
        else -> null
    }

    private const val HOUR_MILLIS = 3_600_000
    private const val MINUTE_MILLIS = 60_000
    private const val SECOND_MILLIS = 1_000

    /** TimeZoneFormat's parse of an ISO 8601 zone: an ISO offset, else a localized GMT one. Offsets are in milliseconds. */
    private fun zone(text: String, start: Int): Pair<Int, Int>? = isoOffset(text, start) ?: localizedGmtOffset(text, start)

    private fun asciiDigit(char: Char): Int = if (char in '0'..'9') char - '0' else -1

    private fun isoOffset(text: String, start: Int): Pair<Int, Int>? {
        if (start >= text.length) return null
        val first = text[start]
        if (first == 'Z' || first == 'z') return 0 to start + 1
        val sign = when (first) {
            '+' -> 1
            '-' -> -1
            else -> return null
        }
        var (offset, end) = asciiOffsetFields(text, start + 1) ?: return null
        // Read again without separators when that reads further: "+0230" is 2:30, not 2:00 and "30".
        if (end - start <= 3) {
            abuttingAsciiOffsetFields(text, start + 1)?.let { (basic, basicEnd) ->
                if (basicEnd > end) {
                    offset = basic
                    end = basicEnd
                }
            }
        }
        return sign * offset to end
    }

    /**
     * Hours, then minutes and seconds after colons. ICU's own reading: a colon after one hour digit does not end
     * the hours, so "+2:00" is 20 hours that end after "2:".
     */
    private fun asciiOffsetFields(text: String, start: Int): Pair<Int, Int>? {
        val values = intArrayOf(0, 0, 0)
        val lengths = intArrayOf(0, -1, -1)
        var index = start
        var field = 0
        while (index < text.length && field <= 2) {
            val char = text[index]
            if (char == ':') {
                if (field == 0) {
                    if (lengths[0] == 0) break
                } else {
                    if (lengths[field] != -1) break
                    lengths[field] = 0
                }
                index++
                continue
            } else if (lengths[field] == -1) {
                break
            }
            val digit = asciiDigit(char)
            if (digit < 0) break
            values[field] = values[field] * 10 + digit
            lengths[field]++
            if (lengths[field] >= 2) field++
            index++
        }
        if (lengths[0] == 0) return null
        if (values[0] > 23) return (values[0] / 10) * HOUR_MILLIS to start + 1
        var offset = values[0] * HOUR_MILLIS
        var length = lengths[0]
        if (lengths[1] == 2 && values[1] <= 59) {
            offset += values[1] * MINUTE_MILLIS
            length += 3
            if (lengths[2] == 2 && values[2] <= 59) {
                offset += values[2] * SECOND_MILLIS
                length += 3
            }
        }
        return offset to start + length
    }

    /** Offset digits that are read from the longest valid run of up to six: H, HH, Hmm, HHmm, Hmmss or HHmmss. */
    private fun offsetFromDigits(digits: List<Int>): Int? {
        var hour = 0
        var minute = 0
        var second = 0
        when (digits.size) {
            1 -> hour = digits[0]
            2 -> hour = digits[0] * 10 + digits[1]
            3 -> {
                hour = digits[0]
                minute = digits[1] * 10 + digits[2]
            }
            4 -> {
                hour = digits[0] * 10 + digits[1]
                minute = digits[2] * 10 + digits[3]
            }
            5 -> {
                hour = digits[0]
                minute = digits[1] * 10 + digits[2]
                second = digits[3] * 10 + digits[4]
            }
            6 -> {
                hour = digits[0] * 10 + digits[1]
                minute = digits[2] * 10 + digits[3]
                second = digits[4] * 10 + digits[5]
            }
        }
        if (hour > 23 || minute > 59 || second > 59) return null
        return hour * HOUR_MILLIS + minute * MINUTE_MILLIS + second * SECOND_MILLIS
    }

    private fun abuttingAsciiOffsetFields(text: String, start: Int): Pair<Int, Int>? {
        val digits = mutableListOf<Int>()
        while (digits.size < 6 && start + digits.size < text.length) {
            val digit = asciiDigit(text[start + digits.size])
            if (digit < 0) break
            digits += digit
        }
        for (count in digits.size downTo 1) {
            offsetFromDigits(digits.subList(0, count))?.let { return it to start + count }
        }
        return null
    }

    /** A decimal digit of any script, and its length in UTF-16 units. */
    private fun localizedDigit(text: String, index: Int): Pair<Int, Int>? {
        if (index >= text.length) return null
        val scalar = text.codePointAt(index)
        val digit = Character.digit(scalar, 10)
        return if (digit < 0) null else digit to Character.charCount(scalar)
    }

    /** A field of `minDigits` to `maxDigits` digits up to `maxValue`: its value and length, or -1 and 0. */
    private fun offsetField(text: String, start: Int, minDigits: Int, maxDigits: Int, maxValue: Int): Pair<Int, Int> {
        var value = 0
        var count = 0
        var index = start
        while (index < text.length && count < maxDigits) {
            val (digit, length) = localizedDigit(text, index) ?: break
            if (value * 10 + digit > maxValue) break
            value = value * 10 + digit
            count++
            index += length
        }
        return if (count < minDigits) -1 to 0 else value to index - start
    }

    private fun startsWithIgnoringCase(text: String, start: Int, prefix: String): Boolean =
        start + prefix.length <= text.length &&
            prefix.indices.all { text[start + it].lowercaseChar() == prefix[it].lowercaseChar() && text[start + it] < '\u0080' }

    private fun localizedGmtOffset(text: String, start: Int): Pair<Int, Int>? {
        // "GMT" followed by +H:mm:ss, -H:mm:ss, +H:mm, -H:mm, +H or -H.
        if (startsWithIgnoringCase(text, start, "GMT")) {
            gmtPatternOffset(text, start + 3)?.let { return it }
        }
        defaultGmtOffset(text, start)?.let { return it }
        for (zero in listOf("GMT", "UTC", "UT")) {
            if (startsWithIgnoringCase(text, start, zero)) return 0 to start + zero.length
        }
        return null
    }

    private fun gmtPatternOffset(text: String, start: Int): Pair<Int, Int>? {
        for (fields in 3 downTo 1) {
            for (sign in listOf('+', '-')) {
                if (start >= text.length || text[start] != sign) continue
                var index = start + 1
                var offset = 0
                var parsed = true
                for (field in 0 until fields) {
                    if (field > 0) {
                        if (index >= text.length || text[index] != ':') {
                            parsed = false
                            break
                        }
                        index++
                    }
                    val (value, length) =
                        if (field == 0) offsetField(text, index, 1, 2, 23) else offsetField(text, index, 2, 2, 59)
                    if (length == 0) {
                        parsed = false
                        break
                    }
                    offset += value * intArrayOf(HOUR_MILLIS, MINUTE_MILLIS, SECOND_MILLIS)[field]
                    index += length
                }
                if (parsed) return (if (sign == '+') offset else -offset) to index
            }
        }
        return null
    }

    /** "GMT", "UTC" or "UT", a sign and an offset with or without colons, whichever reads further. */
    private fun defaultGmtOffset(text: String, start: Int): Pair<Int, Int>? {
        val prefix = listOf("GMT", "UTC", "UT").firstOrNull { startsWithIgnoringCase(text, start, it) } ?: return null
        var index = start + prefix.length
        if (index + 1 >= text.length) return null
        val sign = when (text[index]) {
            '+' -> 1
            '-' -> -1
            else -> return null
        }
        index++
        val (withSeparator, separatorLength) = defaultOffsetFields(text, index)
        val offset: Int
        if (separatorLength == text.length - index) {
            offset = withSeparator
            index += separatorLength
        } else {
            val (abutting, abuttingLength) = abuttingOffsetFields(text, index)
            if (separatorLength > abuttingLength) {
                offset = withSeparator
                index += separatorLength
            } else {
                offset = abutting
                index += abuttingLength
            }
        }
        return sign * offset to index
    }

    /**
     * H, H:mm or H:mm:ss. ICU keeps the -1 a minutes or seconds field that fails to read gives, so "02:30:x"
     * is 2:29:59.
     */
    private fun defaultOffsetFields(text: String, start: Int): Pair<Int, Int> {
        var index = start
        var hour = 0
        var minute = 0
        var second = 0
        run {
            val (hours, hourLength) = offsetField(text, index, 1, 2, 23)
            if (hourLength == 0) return@run
            hour = hours
            index += hourLength
            if (index + 1 < text.length && text[index] == ':') {
                val (minutes, minuteLength) = offsetField(text, index + 1, 2, 2, 59)
                minute = minutes
                if (minuteLength == 0) return@run
                index += 1 + minuteLength
                if (index + 1 < text.length && text[index] == ':') {
                    val (seconds, secondLength) = offsetField(text, index + 1, 2, 2, 59)
                    second = seconds
                    if (secondLength == 0) return@run
                    index += 1 + secondLength
                }
            }
        }
        if (index == start) return 0 to 0
        return hour * HOUR_MILLIS + minute * MINUTE_MILLIS + second * SECOND_MILLIS to index - start
    }

    private fun abuttingOffsetFields(text: String, start: Int): Pair<Int, Int> {
        val digits = mutableListOf<Int>()
        val ends = mutableListOf<Int>()
        var index = start
        while (digits.size < 6) {
            val (digit, length) = localizedDigit(text, index) ?: break
            index += length
            digits += digit
            ends += index - start
        }
        for (count in digits.size downTo 1) {
            offsetFromDigits(digits.subList(0, count))?.let { return it to ends[count - 1] }
        }
        return 0 to 0
    }

    /** A time zone's offset, in milliseconds, at a time or for a wall time. */
    private interface DateZone {
        fun offsetAt(time: Double): Int

        /** The offset before a gap's transition and after an overlap's, ICU Calendar's defaults. */
        fun offsetFromLocal(wall: Double): Int
    }

    private class FixedZone(private val offset: Int) : DateZone {
        override fun offsetAt(time: Double): Int = offset
        override fun offsetFromLocal(wall: Double): Int = offset
    }

    private class RulesZone(private val rules: ZoneRules) : DateZone {
        override fun offsetAt(time: Double): Int =
            rules.getOffset(Instant.ofEpochSecond(floor(time / 1000).toLong())).totalSeconds * 1000

        override fun offsetFromLocal(wall: Double): Int {
            val local = LocalDateTime.ofEpochSecond(floor(wall / 1000).toLong(), 0, ZoneOffset.UTC)
            val transition = rules.getTransition(local)
            val offset = when {
                transition == null -> rules.getOffset(local)
                transition.isGap -> transition.offsetBefore
                else -> transition.offsetAfter
            }
            return offset.totalSeconds * 1000
        }
    }

    private fun addExact(left: Int, right: Int): Int? {
        val sum = left.toLong() + right
        return if (sum in Int.MIN_VALUE..Int.MAX_VALUE) sum.toInt() else null
    }

    /** The Julian day before the month starts, cut to 32 bits as ICU keeps it. */
    private fun monthStart(extendedYear: Int, month: Int, gregorian: Boolean): Int {
        var leap = extendedYear % 4 == 0
        val year = extendedYear.toLong() - 1
        var julianDay = 365 * year + Math.floorDiv(year, 4L) + JANUARY_1_YEAR_1_JULIAN_DAY - 3
        if (gregorian) {
            leap = leap && (extendedYear % 100 != 0 || extendedYear % 400 == 0)
            julianDay += Math.floorDiv(year, 400L) - Math.floorDiv(year, 100L) + 2
        }
        julianDay += (if (leap) leapMonthStarts else monthStarts)[month]
        return julianDay.toInt()
    }

    private fun julianDay(extendedYear: Int, month: Int, dayOfMonth: Int): Int? {
        var gregorian = extendedYear >= CUTOVER_YEAR
        var julianDay = addExact(dayOfMonth, monthStart(extendedYear, month, gregorian)) ?: return null
        // The days of 1582 before the cutover are Julian ones, and the other way round.
        if (gregorian != (julianDay >= CUTOVER_JULIAN_DAY)) {
            gregorian = !gregorian
            julianDay = addExact(dayOfMonth, monthStart(extendedYear, month, gregorian)) ?: return null
        }
        return julianDay
    }

    /** Calendar's computeTime: the milliseconds since 1970 of a wall date and time in the zone. */
    private fun time(extendedYear: Int, month: Int, dayOfMonth: Int, millisInDay: Double, zone: DateZone): Double? {
        if (extendedYear > Int.MAX_VALUE / 2) return null
        val julianDay = julianDay(extendedYear, month, dayOfMonth) ?: return null
        val wall = (julianDay.toDouble() - EPOCH_JULIAN_DAY) * DAY_MILLIS + millisInDay
        return wall - zone.offsetFromLocal(wall)
    }

    private class CivilDate(val extendedYear: Int, val month: Int, val dayOfMonth: Int, val millisInDay: Int) {
        val yearOfEra: Int get() = if (extendedYear < 1) 1 - extendedYear else extendedYear
    }

    /** Calendar's computeFields: the wall date of a time in the zone, Julian before the cutover. */
    private fun civilDate(time: Double, zone: DateZone): CivilDate? {
        val local = (time + zone.offsetAt(time)).toLong()
        val julianDayLong = Math.floorDiv(local, DAY_MILLIS) + EPOCH_JULIAN_DAY
        if (julianDayLong < Int.MIN_VALUE + EPOCH_JULIAN_DAY || julianDayLong > Int.MAX_VALUE) return null
        val julianDay = julianDayLong.toInt()
        val millisInDay = Math.floorMod(local, DAY_MILLIS).toInt()
        if (julianDay >= CUTOVER_JULIAN_DAY) {
            val date = LocalDate.ofEpochDay((julianDay - EPOCH_JULIAN_DAY).toLong())
            return CivilDate(date.year, date.monthValue - 1, date.dayOfMonth, millisInDay)
        }
        val julianEpochDay = julianDay - (JANUARY_1_YEAR_1_JULIAN_DAY - 2)
        val year = Math.floorDiv(4L * julianEpochDay + 1464, 1461L).toInt()
        val dayOfYear = julianEpochDay - (365 * (year - 1) + Math.floorDiv(year - 1, 4))
        val leap = (year and 3) == 0
        val correction = if (dayOfYear >= (if (leap) 60 else 59)) (if (leap) 1 else 2) else 0
        val month = (12 * (dayOfYear + correction) + 6) / 367
        val dayOfMonth = dayOfYear - (if (leap) leapMonthStarts else monthStarts)[month] + 1
        return CivilDate(year, month, dayOfMonth, millisInDay)
    }

    /** CFAbsoluteTime, the seconds since 2001 a Date holds, of ICU's time once CoreFoundation has seen to its year. */
    private fun absoluteTime(time: Double, zone: DateZone): Double? {
        val date = civilDate(time, zone) ?: return null
        val adjusted = if (date.yearOfEra in 12_000..12_099) twoDigitYear(date, zone) ?: return null else time
        return adjusted / 1000.0 - REFERENCE_DATE
    }

    private fun instant(fields: Fields, zone: DateZone): Instant? {
        val millisInDay = ((fields.hour * 60.0 + fields.minute) * 60.0 + fields.second) * 1000.0 + fields.millisecond
        val time = time(fields.year, fields.month, fields.dayOfMonth, millisInDay, zone) ?: return null
        val absolute = absoluteTime(time, zone) ?: return null
        val seconds = floor(absolute)
        // A Date reaches further than an Instant, past the year one billion either way.
        if (seconds + REFERENCE_DATE !in Instant.MIN.epochSecond.toDouble()..Instant.MAX.epochSecond.toDouble()) return null
        return Instant.ofEpochSecond(REFERENCE_DATE + seconds.toLong(), Math.round((absolute - seconds) * 1e9))
    }

    /**
     * CoreFoundation's reading of a year from 12000 to 12099 as a two-digit one: it starts the calendar at year
     * 12000 to spot a year the text left out, then moves such a year to the hundred years from the formatter's
     * two-digit start date, as the zone's calendar sees that date.
     */
    private fun twoDigitYear(date: CivilDate, zone: DateZone): Double? {
        val beforeChrist = date.extendedYear < 1
        val twoDigits = date.yearOfEra - 12_000
        val startYear = civilDate(TWO_DIGIT_START, zone)?.yearOfEra ?: return null
        fun timeIn(year: Int): Double? =
            time(if (beforeChrist) 1 - year else year, date.month, date.dayOfMonth, date.millisInDay.toDouble(), zone)
        val year = when {
            startYear % 100 < twoDigits -> startYear / 100 * 100 + twoDigits
            twoDigits < startYear % 100 -> startYear / 100 * 100 + 100 + twoDigits
            else -> if ((timeIn(startYear) ?: return null) >= TWO_DIGIT_START) startYear else startYear + 100
        }
        return timeIn(year)
    }
}
