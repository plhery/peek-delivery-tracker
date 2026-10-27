package com.plhery.peekdeliverytracker.core

// The logic of AddParcelView.swift's `suggestionLabel`, without SwiftUI's AttributedString: the label's text
// with where the number stands and which of its characters the suggestion changes.

object AddParcelView {
    /**
     * "Did you mean …?": the sentence with the number in it. `number` is where the number stands, set in
     * monospace; `marked` are the runs of characters the suggestion changes, as on the front door. Ranges are
     * UTF-16 offsets into `text`.
     */
    data class SuggestionLabel(val text: String, val number: IntRange, val marked: List<IntRange>)

    fun suggestionLabel(suggestion: String, typed: String, sentence: (String) -> String): SuggestionLabel {
        val parts = sentence("\u0000").split("\u0000")
        val before = parts.firstOrNull() ?: ""
        val typedCharacters = typed.characters()
        val text = StringBuilder(before)
        val marked = mutableListOf<IntRange>()
        for ((index, character) in suggestion.characters().withIndex()) {
            val start = text.length
            text.append(character)
            if (index >= typedCharacters.size || typedCharacters[index] != character) {
                // Neighbouring marked characters make one run, as AttributedString merges them.
                val last = marked.lastOrNull()
                if (last != null && last.last + 1 == start) marked[marked.size - 1] = last.first until text.length
                else marked += start until text.length
            }
        }
        val number = before.length until text.length
        text.append(parts.drop(1).joinToString(""))
        return SuggestionLabel(text.toString(), number, marked)
    }
}

/**
 * The postcode a field was filled with from those given before. While the field still holds it, it can be forgotten;
 * then no other fills the field until its details are prepared again. A value, as the Swift struct is: each change
 * gives a new one.
 */
data class PostcodeFill(val postcode: String? = null, val forgotten: Boolean = false) {
    /** What may fill the field: nothing just after a postcode was forgotten in it. */
    fun offering(suggestion: String?): String? = if (forgotten) null else suggestion

    /** Filled with `suggestion`, which the field takes as it is. */
    fun fill(suggestion: String): PostcodeFill = copy(postcode = suggestion)

    fun holds(field: String): Boolean = field.isNotEmpty() && postcode.equalsCanonically(field)

    /** The postcode to forget, when the field still holds it, and the fill once it is forgotten. */
    fun forget(field: String): Pair<String, PostcodeFill>? {
        val postcode = postcode?.takeIf { holds(field) } ?: return null
        return postcode to copy(postcode = null, forgotten = true)
    }

    /** Another carrier's details: what was given before may fill the field again. */
    fun prepare(): PostcodeFill = copy(forgotten = false)
}
