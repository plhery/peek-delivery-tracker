package com.plhery.peekdeliverytracker.core

import java.text.Normalizer

// Canonical normalization as Swift's standard library has it, at its Unicode version, for `==` on Strings and
// Characters. java.text.Normalizer is the phone's ICU, as old as the phone: Android 12's is Unicode 13, where the
// combining marks and composites added since are unassigned, so it neither reorders nor composes them ("a" U+1ADD
// U+0301 stays as it is; Swift composes the a and the acute, as U+1ADD's class lets it). Unicode never changes the
// normalization of a scalar it has, so text without any of those (ScalarData.normalizationDelta) normalizes alike
// everywhere and goes to the platform. Text with one is normalized here, with ScalarData's combining classes and new
// decompositions, and the platform's decompositions and compositions of the scalars it knows.

/** NFC, as Swift compares Strings. */
internal fun String.nfc(): String =
    if (!hasNormalizationDelta()) Normalizer.normalize(this, Normalizer.Form.NFC) else composed(decomposed(this))

/** NFD. */
internal fun String.nfd(): String {
    if (!hasNormalizationDelta()) return Normalizer.normalize(this, Normalizer.Form.NFD)
    val scalars = decomposed(this)
    return String(scalars, 0, scalars.size)
}

/** The scalar's Canonical_Combining_Class at Swift's Unicode version. */
internal fun combiningClass(scalar: Int): Int {
    val runs = ScalarData.combiningClasses
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

private fun isDelta(scalar: Int): Boolean = ScalarData.normalizationDelta.binarySearch(scalar) >= 0

private fun String.hasNormalizationDelta(): Boolean {
    var index = 0
    while (index < length) {
        if (this[index] < 'ࢗ') {
            index++
            continue
        }
        val scalar = codePointAt(index)
        if (isDelta(scalar)) return true
        index += Character.charCount(scalar)
    }
    return false
}

/** The scalars of the text's canonical decomposition, in canonical order. */
private fun decomposed(text: String): IntArray {
    val scalars = ArrayList<Int>(text.length + 4)
    var index = 0
    while (index < text.length) {
        val scalar = text.codePointAt(index)
        index += Character.charCount(scalar)
        decompose(scalar, scalars)
    }
    // Each run of marks sorted by class, stably.
    for (end in 1 until scalars.size) {
        val scalar = scalars[end]
        val scalarClass = combiningClass(scalar)
        if (scalarClass == 0) continue
        var position = end
        while (position > 0 && combiningClass(scalars[position - 1]) > scalarClass) {
            scalars[position] = scalars[position - 1]
            position--
        }
        scalars[position] = scalar
    }
    return scalars.toIntArray()
}

private fun decompose(scalar: Int, into: MutableList<Int>) {
    val entry = newDecomposition(scalar)
    if (entry >= 0) {
        val parts = ScalarData.decompositions
        decompose(parts[entry + 1], into)
        if (parts[entry + 2] >= 0) decompose(parts[entry + 2], into)
        return
    }
    if (scalar < 0xC0 || isDelta(scalar)) {
        into.add(scalar)
        return
    }
    val decomposition = Normalizer.normalize(String(Character.toChars(scalar)), Normalizer.Form.NFD)
    var index = 0
    while (index < decomposition.length) {
        val part = decomposition.codePointAt(index)
        into.add(part)
        index += Character.charCount(part)
    }
}

/** The offset of the scalar's entry in `ScalarData.decompositions`, or -1. */
private fun newDecomposition(scalar: Int): Int {
    val entries = ScalarData.decompositions
    for (entry in entries.indices step 3) if (entries[entry] == scalar) return entry
    return -1
}

/** Unicode's canonical composition of a decomposed text. */
private fun composed(scalars: IntArray): String {
    val result = IntArray(scalars.size)
    var size = 0
    var starter = -1
    // The class of the last scalar kept: 0 when it is the starter itself, so nothing stands between them.
    var lastClass = 0
    for (scalar in scalars) {
        val scalarClass = combiningClass(scalar)
        if (starter >= 0 && (lastClass == 0 || lastClass < scalarClass)) {
            val composite = composition(result[starter], scalar)
            if (composite >= 0) {
                result[starter] = composite
                continue
            }
        }
        if (scalarClass == 0) starter = size
        lastClass = scalarClass
        result[size++] = scalar
    }
    return String(result, 0, size)
}

/** The primary composite of two scalars, or -1. */
private fun composition(first: Int, second: Int): Int {
    val entries = ScalarData.decompositions
    for (entry in entries.indices step 3) {
        if (entries[entry + 1] == first && entries[entry + 2] == second &&
            ScalarData.compositionExclusions.binarySearch(entries[entry]) < 0
        ) {
            return entries[entry]
        }
    }
    // The platform composes the scalars it knows; it knows none of the delta's.
    if (isDelta(first) || isDelta(second)) return -1
    val pair = String(Character.toChars(first)) + String(Character.toChars(second))
    val composed = Normalizer.normalize(pair, Normalizer.Form.NFC)
    return if (composed.codePointCount(0, composed.length) == 1) composed.codePointAt(0) else -1
}
