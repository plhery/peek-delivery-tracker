package com.plhery.peekdeliverytracker.core

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.text.Collator
import java.util.Locale
import kotlin.math.abs

// Swift's String and Character behaviour the catalog relies on, on the JVM: a Swift String counts
// grapheme clusters, and Foundation's character sets and Character properties have their own members.

/** Foundation's `CharacterSet.whitespacesAndNewlines`: `whitespaces`, U+000A to U+000D, U+0085, U+2028 and U+2029. */
internal fun isWhitespaceOrNewline(scalar: Int): Boolean = when (scalar) {
    in 0x0A..0x0D, 0x85, 0x2028, 0x2029 -> true
    else -> isWhitespaceScalar(scalar)
}

/**
 * Foundation's `CharacterSet.whitespaces`: general category Zs, U+0009 and, as CoreFoundation still counts it,
 * the zero width space U+200B.
 */
internal fun isWhitespaceScalar(scalar: Int): Boolean =
    scalar == 0x09 || scalar == 0x200B || Character.getType(scalar).toByte() == Character.SPACE_SEPARATOR

/** Unicode's White_Space, which Swift's `Character.isWhitespace` reads on the first scalar. */
internal fun hasWhiteSpaceProperty(scalar: Int): Boolean = when (scalar) {
    in 0x09..0x0D, 0x20, 0x85, 0xA0, 0x1680, in 0x2000..0x200A, 0x2028, 0x2029, 0x202F, 0x205F, 0x3000 -> true
    else -> false
}

/** General_Category=Control, which Unicode never changes: U+0000 to U+001F and U+007F to U+009F. */
internal fun isControlScalar(scalar: Int): Boolean = scalar in 0x00..0x1F || scalar in 0x7F..0x9F

/**
 * General_Category=Format as Swift's standard library has it (ScalarData.kt). The phone's ICU is as old as the phone:
 * Android 12 does not know U+0890 or U+13439 to U+1343F.
 */
internal fun isFormatScalar(scalar: Int): Boolean {
    val index = ScalarData.format.binarySearch(scalar)
    // The ranges' bounds alternate: a first scalar, then the one after the last.
    return if (index >= 0) index % 2 == 0 else (-index - 1) % 2 == 1
}

/**
 * The scalar at a UTF-16 offset, as a Swift String has it: a lone surrogate, which a Swift String cannot hold, is
 * U+FFFD, the replacement character, as bridging makes it. Either takes one UTF-16 unit.
 */
internal fun String.scalarAt(index: Int): Int {
    val scalar = codePointAt(index)
    return if (scalar in 0xD800..0xDFFF) 0xFFFD else scalar
}

/** The text as a Swift String holds it: each lone surrogate replaced by U+FFFD. */
internal fun String.wellFormed(): String {
    if (none { it.isSurrogate() }) return this
    val text = StringBuilder(length)
    var index = 0
    while (index < length) {
        val scalar = scalarAt(index)
        text.appendCodePoint(scalar)
        index += Character.charCount(scalar)
    }
    return text.toString()
}

/** `trimmingCharacters(in:)`: the scalars in the set dropped from both ends. */
internal inline fun String.trimmingScalars(member: (Int) -> Boolean): String {
    var start = 0
    var end = length
    while (start < end) {
        val scalar = codePointAt(start)
        if (!member(scalar)) break
        start += Character.charCount(scalar)
    }
    while (end > start) {
        val scalar = codePointBefore(end)
        if (!member(scalar)) break
        end -= Character.charCount(scalar)
    }
    return substring(start, end)
}

/** `trimmingCharacters(in: .whitespacesAndNewlines)`. */
internal fun String.trimmingWhitespacesAndNewlines(): String = trimmingScalars(::isWhitespaceOrNewline)

/** `trimmingCharacters(in: .whitespaces)`. */
internal fun String.trimmingWhitespaces(): String = trimmingScalars(::isWhitespaceScalar)

/** The string's Characters: its extended grapheme clusters, as Swift finds them (GraphemeBreaking.kt). */
internal fun String.characters(): List<String> {
    if (all { it.code < 0x80 && it != '\r' }) return map { it.toString() }
    var start = 0
    return characterEnds(this).map { end -> substring(start, end).also { start = end } }
}

/** `count`: how many Characters the string has. */
internal fun String.characterCount(): Int = if (all { it.code < 0x80 && it != '\r' }) length else characterEnds(this).size

/**
 * Swift's `hasPrefix`, which compares Characters: the prefix must end where a Character does, so "n=\u0301" does not
 * start with "n=". For an ASCII prefix without CR.
 */
internal fun String.hasCharacterPrefix(prefix: String): Boolean {
    if (!startsWith(prefix)) return false
    if (length == prefix.length || this[prefix.length].code < 0x80) return true
    return characterEnds(this).binarySearch(prefix.length) >= 0
}

/**
 * Swift's `split(separator:)` on a String: pieces between the Characters that are the separator, empty pieces left out.
 * A separator a combining mark follows ("&\u0301") is another Character, so it does not split.
 */
internal fun String.splitOnCharacter(separator: String): List<String> {
    if (all { it.code < 0x80 }) return split(separator).filter { it.isNotEmpty() }
    val pieces = mutableListOf<String>()
    val piece = StringBuilder()
    for (character in characters()) {
        if (character == separator) {
            if (piece.isNotEmpty()) pieces += piece.toString()
            piece.setLength(0)
        } else {
            piece.append(character)
        }
    }
    if (piece.isNotEmpty()) pieces += piece.toString()
    return pieces
}

/**
 * Swift's `==` on Strings, optional or not: canonically equivalent, as NFC compares them at Swift's Unicode version
 * (Normalization.kt). Nil equals nil alone.
 */
internal fun String?.equalsCanonically(other: String?): Boolean =
    this == other || (this != null && other != null && nfc() == other.nfc())

/** A hash code that agrees with `equalsCanonically`, for a type that compares a String as Swift does. */
internal fun String.canonicalHashCode(): Int = nfc().hashCode()

/** Swift's `lowercased()`: each scalar's full lowercase mapping on its own, so a final Σ is σ, not ς. */
internal fun String.lowercased(): String {
    if (all { it.code < 0x80 }) return lowercase(Locale.ROOT)
    val result = StringBuilder(length)
    var index = 0
    while (index < length) {
        val scalar = codePointAt(index)
        result.append(String(Character.toChars(scalar)).lowercase(Locale.ROOT))
        index += Character.charCount(scalar)
    }
    return result.toString()
}

/**
 * Swift's `uppercased()`: each scalar's full uppercase mapping on its own (ß is SS), as Swift's standard library has
 * them (ScalarData.kt) rather than the phone's ICU.
 */
internal fun String.uppercased(): String {
    if (all { it.code < 0x80 }) return uppercase(Locale.ROOT)
    val result = StringBuilder(length)
    var index = 0
    while (index < length) {
        // A lone surrogate is U+FFFD, as a Swift String holds it.
        appendUppercase(result, scalarAt(index))
        index += Character.charCount(codePointAt(index))
    }
    return result.toString()
}

private val uppercaseSpecial: Map<Int, IntArray> by lazy {
    val entries = ScalarData.uppercaseSpecial
    val mappings = HashMap<Int, IntArray>()
    var index = 0
    while (index < entries.size) {
        val count = entries[index + 1]
        mappings[entries[index]] = entries.copyOfRange(index + 2, index + 2 + count)
        index += 2 + count
    }
    mappings
}

private fun appendUppercase(result: StringBuilder, scalar: Int) {
    uppercaseSpecial[scalar]?.let { mapping ->
        mapping.forEach(result::appendCodePoint)
        return
    }
    val runs = ScalarData.uppercaseRuns
    // The last run that starts at or before the scalar.
    var low = 0
    var high = runs.size / 4 - 1
    var run = -1
    while (low <= high) {
        val middle = (low + high) ushr 1
        if (runs[middle * 4] <= scalar) {
            run = middle
            low = middle + 1
        } else {
            high = middle - 1
        }
    }
    val first = if (run >= 0) runs[run * 4] else 0
    if (run >= 0 && scalar <= runs[run * 4 + 1] && (scalar - first) % runs[run * 4 + 2] == 0) {
        result.appendCodePoint(scalar + runs[run * 4 + 3])
    } else {
        result.appendCodePoint(scalar)
    }
}

/**
 * Swift's `hasPrefix` for any prefix: Character by Character, each canonically equivalent to the one it stands for, so
 * "e\u0301x" does not start with "e" and "\u212AAB" (the Kelvin sign) starts with "K".
 */
internal fun String.hasCanonicalPrefix(prefix: String): Boolean {
    if (prefix.isEmpty()) return true
    val characters = characters()
    val start = prefix.characters()
    return start.size <= characters.size && start.indices.all { characters[it].equalsCanonically(start[it]) }
}

/** `String.count`. */
internal val String.characterCount: Int
    get() = if (all { it.code < 0x80 && it != '\r' }) length else characters().size

/** `String(prefix(count))`, in Characters. */
internal fun String.characterPrefix(count: Int): String {
    if (all { it.code < 0x80 && it != '\r' }) return take(count)
    return characters().take(count).joinToString("")
}

/** `String.last`. */
internal fun String.lastCharacter(): String? = characters().lastOrNull()

/** `String(dropLast())`, in Characters. */
internal fun String.droppingLastCharacter(): String = characters().dropLast(1).joinToString("")

private val String.firstScalar: Int get() = codePointAt(0)

/** `Character.isWhitespace`. */
internal val String.isWhitespaceCharacter: Boolean get() = isNotEmpty() && hasWhiteSpaceProperty(firstScalar)

/** `Character.isLetter`: the first scalar is Alphabetic. */
internal val String.isLetterCharacter: Boolean get() = isNotEmpty() && Character.isAlphabetic(firstScalar)

/**
 * `Character.isNumber`: the first scalar has a numeric type. The JVM has no Numeric_Type, so the number
 * categories stand for it; Unihan ideographs with a numeric value (五) are not numbers here.
 */
internal val String.isNumberCharacter: Boolean
    get() = isNotEmpty() && when (Character.getType(firstScalar).toByte()) {
        Character.DECIMAL_DIGIT_NUMBER, Character.LETTER_NUMBER, Character.OTHER_NUMBER -> true
        else -> false
    }

/** `filter(\.isNumber)`. */
internal fun String.filteringNumbers(): String = characters().filter { it.isNumberCharacter }.joinToString("")

/** `filter { !$0.isWhitespace }`. */
internal fun String.removingWhitespace(): String = characters().filterNot { it.isWhitespaceCharacter }.joinToString("")

/** `split(whereSeparator: \.isWhitespace)`, empty pieces left out. */
internal fun String.splitOnWhitespace(): List<String> {
    val words = mutableListOf<String>()
    val word = StringBuilder()
    for (character in characters()) {
        if (character.isWhitespaceCharacter) {
            if (word.isNotEmpty()) words += word.toString()
            word.setLength(0)
        } else {
            word.append(character)
        }
    }
    if (word.isNotEmpty()) words += word.toString()
    return words
}

/**
 * `caseInsensitiveCompare(_:) == .orderedSame`: the same once case is folded in full (ß is ss, ﬆ is st,
 * İ is i̇ and ı stays ı) and canonically equivalent.
 */
internal fun String.equalsFoldingCase(other: String): Boolean = foldedCase() == other.foldedCase()

private fun String.foldedCase(): String {
    val decomposed = nfd()
    val folded = StringBuilder(decomposed.length)
    var index = 0
    while (index < decomposed.length) {
        val scalar = decomposed.codePointAt(index)
        val text = String(Character.toChars(scalar))
        // Lowered, raised and lowered again, the way full case folding takes ẞ to ss; ı has no folding.
        folded.append(if (scalar == 0x131) text else text.lowercase(Locale.ROOT).uppercase(Locale.ROOT).lowercase(Locale.ROOT))
        index += Character.charCount(scalar)
    }
    return folded.toString().nfd()
}

/** Foundation's localized comparisons, by the options they are given, as ICU collation (`Icu.collators`). */
internal enum class LocalizedComparison {
    /**
     * `compare(_:locale:)`: accents and case tell strings apart, a letter's width or compatibility form (ﬁ, ②)
     * does not. ICU's secondary strength with the case level.
     */
    Plain,

    /** `localizedCaseInsensitiveCompare`, `.caseInsensitive`: accents count and case does not, ß is ss. */
    CaseInsensitive,

    /**
     * `[.caseInsensitive, .diacriticInsensitive, .numeric]`: the letters alone, and numbers by their value.
     * Foundation also calls two strings equal when they differ only in accents, even letters the language
     * tells apart (Polish ó); that is left out, as a sort cannot rely on a comparison that does it.
     */
    Loose;

    fun comparator(locale: Locale): Comparator<String> = when (this) {
        Plain -> Icu.collators.collator(locale, Collator.SECONDARY, caseLevel = true, numeric = false)
        CaseInsensitive -> {
            // Foundation folds case in full before it collates (ß is ss, ſ is s), which ICU's secondary strength
            // alone does not. ASCII needs no folding: its case is all the secondary strength passes over.
            val collator = Icu.collators.collator(locale, Collator.SECONDARY, caseLevel = false, numeric = false)
            Comparator { left, right -> collator.compare(left.foldedForCollation(), right.foldedForCollation()) }
        }
        Loose -> Icu.collators.collator(locale, Collator.PRIMARY, caseLevel = false, numeric = true)
    }
}

private fun String.foldedForCollation(): String = if (all { it < '\u0080' }) this else foldedCase()

/**
 * Swift's `Double.description`: the shortest digits that read back as the value (the nearest of them when several
 * do), written with an exponent below 0.0001 and beyond 2^53.
 */
internal fun Double.swiftDescription(): String {
    if (isNaN()) return "nan"
    if (isInfinite()) return if (this > 0) "inf" else "-inf"
    if (this == 0.0) return if (1.0 / this < 0) "-0.0" else "0.0"
    val sign = if (this < 0) "-" else ""
    // Rounded from the exact value: Double.toString keeps two digits (4.9E-324 for 5e-324), and before JDK 19, as on
    // Android, is not always the shortest.
    val magnitude = abs(this)
    val exact = BigDecimal(magnitude)
    // The decimals that read back as the value, exactly: those between the midpoints to its neighbours, and the
    // midpoints themselves when its significand is even, as a tie rounds to it. Past the largest Double the midpoint is
    // as far above as the one below.
    val below = BigDecimal(Math.nextDown(magnitude))
    val above = if (magnitude == Double.MAX_VALUE) exact + (exact - below) else BigDecimal(Math.nextUp(magnitude))
    val low = (below + exact).divide(BigDecimal.valueOf(2))
    val high = (exact + above).divide(BigDecimal.valueOf(2))
    val closed = magnitude.toRawBits() and 1L == 0L
    fun readsBack(decimal: BigDecimal): Boolean {
        val fromLow = decimal.compareTo(low)
        val toHigh = decimal.compareTo(high)
        return if (closed) fromLow >= 0 && toHigh <= 0 else fromLow > 0 && toHigh < 0
    }
    val decimal = (1..17).firstNotNullOf { length ->
        sequenceOf(RoundingMode.HALF_EVEN, RoundingMode.DOWN, RoundingMode.UP)
            .map { exact.round(MathContext(length, it)) }
            .firstOrNull(::readsBack)
    }.stripTrailingZeros()
    val digits = decimal.unscaledValue().toString()
    // The value is 0.digits × 10^exponent.
    val exponent = digits.length - decimal.scale()
    if (exponent < -3 || abs(this) > 9007199254740992.0) {
        val power = exponent - 1
        val mantissa = if (digits.length == 1) digits else digits[0] + "." + digits.substring(1)
        return sign + mantissa + "e" + (if (power < 0) "-" else "+") + abs(power).toString().padStart(2, '0')
    }
    return sign + when {
        exponent <= 0 -> "0." + "0".repeat(-exponent) + digits
        exponent >= digits.length -> digits + "0".repeat(exponent - digits.length) + ".0"
        else -> digits.substring(0, exponent) + "." + digits.substring(exponent)
    }
}

// ASCII digits alone, as Swift reads them: `\d` would take any script's.
private val swiftDecimal = Regex("^[+-]?(?:[0-9]+\\.?[0-9]*|\\.[0-9]+)(?:[eE][+-]?[0-9]+)?$")
// A lowercase x only; the exponent is optional.
private val swiftHexadecimal = Regex("^[+-]?0x(?:[0-9a-fA-F]+\\.?[0-9a-fA-F]*|\\.[0-9a-fA-F]+)(?:[pP][+-]?[0-9]+)?$")
private val swiftInfinity = Regex("^[+-]?[iI][nN][fF](?:[iI][nN][iI][tT][yY])?$")
// Any case, with a payload of decimal digits, or of hexadecimal ones after a lowercase 0x.
private val swiftNan = Regex("^[+-]?[sS]?[nN][aA][nN](?:\\((?:[0-9]*|0x[0-9a-fA-F]*)\\))?$")

/**
 * Swift's `Double(_:)` on a String: the text up to any NUL, whole, without spaces; decimal or hexadecimal (`0x1.8p1`),
 * inf, infinity, nan and snan in any case. Too large a number is infinite and too small one zero, rather than nil.
 */
internal fun swiftDouble(value: String): Double? {
    val text = value.substringBefore('\u0000')
    return when {
        swiftDecimal.matches(text) -> text.toDouble()
        // The JVM wants an exponent on a hexadecimal number.
        swiftHexadecimal.matches(text) -> (if ('p' in text || 'P' in text) text else text + "p0").toDouble()
        swiftInfinity.matches(text) -> if (text.startsWith('-')) Double.NEGATIVE_INFINITY else Double.POSITIVE_INFINITY
        swiftNan.matches(text) -> Double.NaN
        else -> null
    }
}
