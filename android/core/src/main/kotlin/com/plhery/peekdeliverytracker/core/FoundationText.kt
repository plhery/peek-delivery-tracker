package com.plhery.peekdeliverytracker.core

import java.text.BreakIterator
import java.text.Normalizer
import java.util.Locale

// Swift's String and Character behaviour the catalog relies on, on the JVM: a Swift String counts
// grapheme clusters, and Foundation's character sets and Character properties have their own members.

/** Foundation's `CharacterSet.whitespacesAndNewlines`: general category Z*, U+0009 to U+000D and U+0085. */
internal fun isWhitespaceOrNewline(scalar: Int): Boolean = when (scalar) {
    in 0x09..0x0D, 0x85 -> true
    else -> when (Character.getType(scalar).toByte()) {
        Character.SPACE_SEPARATOR, Character.LINE_SEPARATOR, Character.PARAGRAPH_SEPARATOR -> true
        else -> false
    }
}

/** Foundation's `CharacterSet.whitespaces`: general category Zs and U+0009. */
internal fun isWhitespaceScalar(scalar: Int): Boolean =
    scalar == 0x09 || Character.getType(scalar).toByte() == Character.SPACE_SEPARATOR

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
