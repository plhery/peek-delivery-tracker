package com.plhery.peekdeliverytracker.core

/** A match's place in a text, in characters (code points): `start` up to, not including, `end`. */
data class SearchRange(val start: Int, val end: Int)

/** The few words of a note a search matched, with "…" where the note goes on, and every match within them. */
data class NoteSnippet(val text: String, val ranges: List<SearchRange>)

/** A piece of text, matched by the search or not. */
data class SearchPiece(val text: String, val matched: Boolean)

/**
 * Where a list search matched, so a row can mark it (the web's `src/lib/searchMatch.ts`). The list finds a parcel by its
 * words in lower case ([ParcelLogic.visible]); these find the same words, and give their places in characters (code
 * points), which is how a row cuts its text.
 */
object SearchMatch {
    /** How much of a note a row shows around the first match: a few words before it, and one line in all. */
    private const val BEFORE = 24
    private const val LENGTH = 90

    private val spaces = Regex("\\s+")

    /** The search as the list reads it: trimmed, its spaces one, in lower case. Empty when nothing is searched. */
    private fun searched(query: String): String = query.trimmingWhitespacesAndNewlines().replace(spaces, " ").lowercase()

    private fun characters(text: String): List<String> = text.codePoints().toArray().map { String(Character.toChars(it)) }

    /** Every place `query` stands in `text`, ignoring case, one after the other and none overlapping. */
    fun ranges(text: String, query: String): List<SearchRange> {
        val wanted = searched(query)
        if (wanted.isEmpty()) return emptyList()
        val characters = characters(text)
        // Each character in lower case on its own, so a place in the lowered text leads back to a character.
        val lowered = StringBuilder()
        val startOf = IntArray(characters.size)
        characters.forEachIndexed { index, character ->
            startOf[index] = lowered.length
            lowered.append(character.lowercase())
        }
        fun characterAt(offset: Int): Int {
            val index = startOf.indexOfFirst { it > offset }
            return (if (index == -1) characters.size else index) - 1
        }
        val ranges = mutableListOf<SearchRange>()
        var at = lowered.indexOf(wanted)
        while (at != -1) {
            ranges += SearchRange(characterAt(at), characterAt(at + wanted.length - 1) + 1)
            at = lowered.indexOf(wanted, at + wanted.length)
        }
        return ranges
    }

    /** Text cut into its matched and unmatched pieces, in order. */
    fun pieces(text: String, ranges: List<SearchRange>): List<SearchPiece> {
        val characters = characters(text)
        val pieces = mutableListOf<SearchPiece>()
        var at = 0
        for ((start, end) in ranges) {
            if (start > at) pieces += SearchPiece(characters.subList(at, start).joinToString(""), matched = false)
            pieces += SearchPiece(characters.subList(start, end).joinToString(""), matched = true)
            at = end
        }
        if (at < characters.size) pieces += SearchPiece(characters.subList(at, characters.size).joinToString(""), matched = false)
        return pieces
    }

    /**
     * The few words of a note, or the name of one of its files, that a search matched, for the row that found it. Null
     * when neither holds the search: the row matched by its name, number, carrier or scans.
     */
    fun noteSnippet(note: String?, fileNames: List<String>, query: String): NoteSnippet? {
        val flat = (note ?: "").replace(spaces, " ").trim()
        val ranges = ranges(flat, query)
        if (ranges.isNotEmpty()) return around(flat, ranges)
        for (name in fileNames) {
            val named = ranges(name, query)
            if (named.isNotEmpty()) return around(name, named)
        }
        return null
    }

    /** The window of `text` that starts a few words before the first match and fits a line, cut between words. */
    private fun around(text: String, ranges: List<SearchRange>): NoteSnippet {
        val characters = characters(text)
        val (first, firstEnd) = ranges[0]
        var start = maxOf(0, first - BEFORE)
        if (start > 0 && characters[start - 1] != " ") {
            // Start on a whole word: after the first space in the few characters before the match, if there is one.
            val space = characters.subList(start, first).indexOf(" ")
            if (space != -1) start += space + 1
        }
        var end = minOf(characters.size, maxOf(start + LENGTH, firstEnd))
        if (end < characters.size) {
            // End on a word, never before the first match does.
            val space = characters.subList(firstEnd, end).lastIndexOf(" ")
            if (space != -1) end = firstEnd + space
        }
        val before = if (start > 0) "…" else ""
        val shift = start - before.length
        val shown = ranges.filter { it.start >= start && it.end <= end }.map { SearchRange(it.start - shift, it.end - shift) }
        val words = characters.subList(start, end).joinToString("").trimEnd()
        return NoteSnippet("$before$words${if (end < characters.size) "…" else ""}", shown)
    }
}
