package com.plhery.peekdeliverytracker.core

import java.math.BigDecimal
import java.text.BreakIterator
import java.text.Collator
import java.text.Normalizer
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

private val characterBreaks = ThreadLocal.withInitial { BreakIterator.getCharacterInstance(Locale.ROOT) }

/** The string's Characters: its extended grapheme clusters. */
internal fun String.characters(): List<String> {
    if (all { it.code < 0x80 && it != '\r' }) return map { it.toString() }
    val breaks = characterBreaks.get()
    breaks.setText(this)
    val characters = mutableListOf<String>()
    var start = breaks.first()
    var end = breaks.next()
    while (end != BreakIterator.DONE) {
        characters += substring(start, end)
        start = end
        end = breaks.next()
    }
    return characters
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
    val decomposed = Normalizer.normalize(this, Normalizer.Form.NFD)
    val folded = StringBuilder(decomposed.length)
    var index = 0
    while (index < decomposed.length) {
        val scalar = decomposed.codePointAt(index)
        val text = String(Character.toChars(scalar))
        // Lowered, raised and lowered again, the way full case folding takes ẞ to ss; ı has no folding.
        folded.append(if (scalar == 0x131) text else text.lowercase(Locale.ROOT).uppercase(Locale.ROOT).lowercase(Locale.ROOT))
        index += Character.charCount(scalar)
    }
    return Normalizer.normalize(folded, Normalizer.Form.NFD)
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
 * Swift's `Double.description`: the shortest digits that read back as the value, written with an exponent
 * below 0.0001 and beyond 2^53.
 */
internal fun Double.swiftDescription(): String {
    if (isNaN()) return "nan"
    if (isInfinite()) return if (this > 0) "inf" else "-inf"
    if (this == 0.0) return if (1.0 / this < 0) "-0.0" else "0.0"
    val sign = if (this < 0) "-" else ""
    // Double.toString gives those shortest digits (JDK 19 on).
    val decimal = BigDecimal(abs(this).toString()).stripTrailingZeros()
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
