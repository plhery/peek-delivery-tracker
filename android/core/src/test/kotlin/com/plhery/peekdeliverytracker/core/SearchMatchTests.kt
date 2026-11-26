package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** As src/lib/searchMatch.test.ts: where a list search matched. */
class SearchMatchTests {
    private fun marked(snippet: NoteSnippet?): List<String>? =
        snippet?.let { SearchMatch.pieces(it.text, it.ranges).filter { piece -> piece.matched }.map { piece -> piece.text } }

    private fun ranges(vararg pairs: Pair<Int, Int>) = pairs.map { SearchRange(it.first, it.second) }

    @Test
    fun findsEveryPlaceTheSearchStandsInAnyCaseByCharacters() {
        assertEquals(ranges(0 to 5, 12 to 17, 22 to 27), SearchMatch.ranges("Vinyl, more VINYL and vinyl", " vinyl "))
        // An emoji before the match counts as one character, as the row cuts it.
        assertEquals(ranges(10 to 14), SearchMatch.ranges("🎷 Kind of Blue", "blue"))
        assertEquals(emptyList<SearchRange>(), SearchMatch.ranges("Kind of Blue", ""))
        assertEquals(emptyList<SearchRange>(), SearchMatch.ranges("Kind of Blue", "green"))
        assertEquals(
            listOf(SearchPiece("Kind ", false), SearchPiece("of", true), SearchPiece(" Blue", false)),
            SearchMatch.pieces("Kind of Blue", SearchMatch.ranges("Kind of Blue", "of")),
        )
    }

    @Test
    fun keepsACharacterWhoseLowerCaseIsLongerWhole() {
        // "İ" lowers to two characters; the match still begins and ends on whole characters.
        assertEquals(ranges(1 to 8, 11 to 18), SearchMatch.ranges("İstanbul, Istanbul", "stanbul"))
    }

    @Test
    fun showsAShortNoteWholeEveryMatchMarked() {
        val snippet = SearchMatch.noteSnippet("Door code 4812.\nCode for the bike shed: 99", emptyList(), "code")
        assertEquals("Door code 4812. Code for the bike shed: 99", snippet?.text)
        assertEquals(listOf("code", "Code"), marked(snippet))
    }

    @Test
    fun showsALongNoteFromAFewWordsBeforeTheMatchCutBetweenWords() {
        val note = "Ordered for the living room after a long wait at the shop. ".repeat(3) +
            "Gift receipt is in the drawer, under the blue folder with the warranty papers and the spare keys. Call before noon."
        val snippet = SearchMatch.noteSnippet(note, emptyList(), "receipt")!!
        assertTrue(snippet.text.startsWith("…"))
        assertTrue(snippet.text.endsWith("…"))
        assertTrue(Regex("^…[^ ]").containsMatchIn(snippet.text))
        assertTrue(snippet.text.codePointCount(0, snippet.text.length) <= 92)
        // The match stands near the start, where a one-line row still shows it.
        assertTrue(snippet.ranges[0].start <= 25)
        assertEquals(listOf("receipt"), marked(snippet))
        assertTrue(note.contains(snippet.text.substring(1, snippet.text.length - 1)))
    }

    @Test
    fun marksOnlyTheMatchesTheSnippetShows() {
        val note = "Box 1 of 2 here. ${"Lorem ipsum dolor sit amet. ".repeat(6)}Box 2 of 2 at the neighbour's."
        val snippet = SearchMatch.noteSnippet(note, emptyList(), "box")!!
        assertTrue(snippet.text.startsWith("Box 1 of 2"))
        assertEquals(listOf("Box"), marked(snippet))
    }

    @Test
    fun fallsBackToAFilesNameAndIsNullWhenNeitherTheNoteNorAFileHoldsTheSearch() {
        assertEquals(NoteSnippet("Box.jpg", ranges(0 to 3)), SearchMatch.noteSnippet("Door code 4812", listOf("Receipt.pdf", "Box.jpg"), "box"))
        assertNull(SearchMatch.noteSnippet("Door code 4812", listOf("Receipt.pdf"), "vinyl"))
        assertNull(SearchMatch.noteSnippet(null, emptyList(), "vinyl"))
    }
}
