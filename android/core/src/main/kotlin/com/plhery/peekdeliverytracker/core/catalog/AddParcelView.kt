package com.plhery.peekdeliverytracker.core.catalog

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
