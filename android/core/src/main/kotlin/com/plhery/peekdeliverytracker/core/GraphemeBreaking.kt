package com.plhery.peekdeliverytracker.core

// Swift's standard library's own Character breaking (StringGraphemeBreaking.swift, UnicodeBreakProperty.swift), on
// GraphemeData's properties. Swift does not ask the system's ICU, and neither does this: Android's is as old as the
// phone, so a conjunct such as क्ष would be two Characters on Android 12 to 14 and one on an iPhone. Swift departs from
// Unicode's rules in places, and so does this: only six scripts' viramas join consonants, and Kirat Rai's vowel signs
// are no Hangul vowels.

/** The UTF-16 offset after each of the string's Characters, in order, the last being the string's length. */
internal fun characterEnds(text: String): IntArray {
    if (text.isEmpty()) return IntArray(0)
    val ends = IntArray(text.length)
    var count = 0
    val state = GraphemeBreakingState()
    var scalar = scalarAt(text, 0)
    var index = Character.charCount(scalar)
    while (index < text.length) {
        val next = scalarAt(text, index)
        if (state.shouldBreak(scalar, next)) {
            ends[count++] = index
            state.reset()
        }
        scalar = next
        index += Character.charCount(next)
    }
    ends[count++] = text.length
    return ends.copyOf(count)
}

/** A Swift String holds no lone surrogate: one from Java text reads as U+FFFD, the replacement character, as it is bridged. */
private fun scalarAt(text: String, index: Int): Int {
    val scalar = text.codePointAt(index)
    return if (scalar in 0xD800..0xDFFF) 0xFFFD else scalar
}

// Swift's `Unicode._GraphemeBreakProperty`.
private const val ANY = 0
private const val CONTROL = 1
private const val EXTEND = 2
private const val PREPEND = 3
private const val SPACING_MARK = 4
private const val L = 5
private const val V = 6
private const val T = 7
private const val LV = 8
private const val LVT = 9
private const val REGIONAL_INDICATOR = 10
private const val ZWJ = 11

/** GraphemeData's properties of a scalar, with Grapheme_Cluster_Break 0 for a scalar whose break property is in the code. */
private fun dataProperties(scalar: Int): Int {
    val runs = GraphemeData.runs
    var low = 0
    var high = runs.size - 1
    val key = scalar shl 8 or 0xFF
    // The last run that starts at or before the scalar.
    while (low < high) {
        val middle = (low + high + 1) ushr 1
        if (runs[middle] <= key) low = middle else high = middle - 1
    }
    return runs[low] and 0xFF
}

private fun graphemeBreakProperty(scalar: Int): Int = when (scalar) {
    in 0x0..0x1F -> CONTROL
    in 0x20..0x7E -> ANY
    0x200D -> ZWJ
    in 0x1100..0x115F, in 0xA960..0xA97C -> L
    in 0x1160..0x11A7, in 0xD7B0..0xD7C6 -> V
    in 0x11A8..0x11FF, in 0xD7CB..0xD7FB -> T
    in 0xAC00..0xD7A3 -> if (scalar % 28 == 16) LV else LVT
    in 0x1F1E6..0x1F1FF -> REGIONAL_INDICATOR
    in 0xE01F0..0xE0FFF -> CONTROL
    else -> dataProperties(scalar) and 0x7
}

/** Grapheme_Cluster_Break=Extend, as Swift's standard library has it: the marks Foundation's diacritic folding drops. */
internal fun isGraphemeExtend(scalar: Int): Boolean = graphemeBreakProperty(scalar) == EXTEND

private fun isExtendedPictographic(scalar: Int): Boolean = scalar >= 0xA9 && dataProperties(scalar) and 0x8 != 0

private fun isConjunctConsonant(scalar: Int): Boolean = dataProperties(scalar) and 0x10 != 0

/** Swift names these viramas, of Devanagari, Bengali, Gujarati, Oriya, Telugu and Malayalam, and no other linker. */
private fun isConjunctLinker(scalar: Int): Boolean = when (scalar) {
    0x94D, 0x9CD, 0xACD, 0xB4D, 0xC4D, 0xD4D -> true
    else -> false
}

/** For a scalar already Extend or ZWJ. */
private fun isConjunctExtend(scalar: Int): Boolean = !(isConjunctConsonant(scalar) || isConjunctLinker(scalar) || scalar == 0x200C)

/**
 * `_quickHasGraphemeBreakBetween`: true where there is a break whatever came before, false between CR and LF, null when
 * the rules must decide.
 */
private fun quickBreak(left: Int, right: Int): Boolean? {
    if (left == 0x0D && right == 0x0A) return false
    return if (breaksWhenPaired(left) && breaksWhenPaired(right)) true else null
}

private fun breaksWhenPaired(scalar: Int): Boolean = when (scalar) {
    in 0x3400..0xA4CF, in 0x0000..0x02FF, in 0x3041..0x3096, in 0x30A1..0x30FC, in 0x0400..0x0482, in 0x061D..0x064A,
    in 0xAC00..0xD7AF, in 0x2010..0x2029, in 0x3000..0x3029, in 0xFF01..0xFF9D,
    -> true
    else -> false
}

/** `_GraphemeBreakingState`: what the scalars before a pair tell about it, within one Character. */
private class GraphemeBreakingState {
    // At least one linker between two consonants.
    private var hasSeenConjunctLinker = false
    private var isInEmojiSequence = false
    private var isInIndicSequence = false
    private var shouldBreakRegionalIndicator = false

    fun reset() {
        hasSeenConjunctLinker = false
        isInEmojiSequence = false
        isInIndicSequence = false
        shouldBreakRegionalIndicator = false
    }

    fun shouldBreak(scalar1: Int, scalar2: Int): Boolean {
        quickBreak(scalar1, scalar2)?.let { return it }
        val x = graphemeBreakProperty(scalar1)
        // GB4
        if (x == CONTROL) return true
        // Both sequences end with this pair unless it carries them on.
        var enterEmojiSequence = false
        var enterIndicSequence = false
        val y = graphemeBreakProperty(scalar2)
        val result = when {
            x == ANY && y == ANY -> true
            // GB5
            y == CONTROL -> true
            // GB6, GB7, GB8
            x == L && (y == L || y == V || y == LV || y == LVT) -> false
            (x == LV || x == V) && (y == V || y == T) -> false
            (x == LVT || x == T) && y == T -> false
            // GB9, and the start of GB11 and GB9c
            y == EXTEND || y == ZWJ -> {
                // GB11: Extended_Pictographic Extend* ZWJ × Extended_Pictographic.
                if (isExtendedPictographic(scalar1) || (isInEmojiSequence && x == EXTEND)) enterEmojiSequence = true
                // GB9c: Consonant [Extend Linker]* Linker [Extend Linker]* × Consonant.
                val linker = isConjunctLinker(scalar2)
                if ((linker || isConjunctExtend(scalar2)) && (isConjunctConsonant(scalar1) || isInIndicSequence)) {
                    enterIndicSequence = true
                    if (linker) hasSeenConjunctLinker = true
                }
                false
            }
            // GB9a, GB9b
            y == SPACING_MARK -> false
            x == PREPEND -> false
            // GB11
            x == ZWJ && isExtendedPictographic(scalar2) -> !isInEmojiSequence
            // GB12, GB13
            x == REGIONAL_INDICATOR && y == REGIONAL_INDICATOR -> shouldBreakRegionalIndicator.also {
                shouldBreakRegionalIndicator = !it
            }
            // GB9c
            isInIndicSequence && hasSeenConjunctLinker && isConjunctConsonant(scalar2) -> {
                hasSeenConjunctLinker = false
                false
            }
            // GB999
            else -> true
        }
        isInEmojiSequence = enterEmojiSequence
        isInIndicSequence = enterIndicSequence
        return result
    }
}
