package com.plhery.peekdeliverytracker.core

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UCharacter.GraphemeClusterBreak
import com.ibm.icu.lang.UProperty
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * GraphemeBreaking.kt and GraphemeData.kt. The data is Unicode 17, ICU4J 76's is Unicode 16: they must agree but for what
 * Unicode 17 changed and what Swift reads its own way. The expected breaks are Swift's on macOS 27.
 */
class GraphemeBreakingTests {
    companion object {
        /** GraphemeData's properties of every scalar. */
        private val data: IntArray by lazy {
            val properties = IntArray(0x110000)
            val runs = GraphemeData.runs
            for (index in runs.indices) {
                val end = if (index + 1 < runs.size) runs[index + 1] ushr 8 else properties.size
                properties.fill(runs[index] and 0xFF, runs[index] ushr 8, end)
            }
            properties
        }

        /** The scalars ICU4J knows: Swift reads a lone surrogate as U+FFFD, and Unicode 17's new scalars are new to ICU4J. */
        private val known by lazy { (0..0x10FFFF).filter { it !in 0xD800..0xDFFF && UCharacter.getAge(it).major > 0 } }
    }

    private fun ends(text: String) = characterEnds(text).toList()

    private fun text(vararg scalars: Int) = String(scalars, 0, scalars.size)

    private fun hex(scalar: Int) = "U+%04X".format(scalar)

    @Test
    fun breakPropertiesInTheDataAreUnicodes() {
        val mismatches = known.filter { scalar ->
            val expected = when (UCharacter.getIntPropertyValue(scalar, UProperty.GRAPHEME_CLUSTER_BREAK)) {
                GraphemeClusterBreak.CONTROL, GraphemeClusterBreak.CR, GraphemeClusterBreak.LF -> 1
                GraphemeClusterBreak.EXTEND -> 2
                // Unicode 17 took Zanabazar Square's cluster-initial ra off Prepend.
                GraphemeClusterBreak.PREPEND -> if (scalar == 0x11A3A) 0 else 3
                GraphemeClusterBreak.SPACING_MARK -> 4
                else -> 0
            }
            data[scalar] and 0x7 != expected
        }
        assertEquals(emptyList<String>(), mismatches.map(::hex))
    }

    @Test
    fun breakPropertiesInTheCodeAreUnicodesButKiratRais() {
        val kiratRaiVowels = listOf(0x16D63) + (0x16D67..0x16D6A)
        val mismatches = known.filter { scalar ->
            when (UCharacter.getIntPropertyValue(scalar, UProperty.GRAPHEME_CLUSTER_BREAK)) {
                GraphemeClusterBreak.L -> ends(text(scalar, 0x1161)).size != 1
                // Swift knows Hangul's vowels alone.
                GraphemeClusterBreak.V -> ends(text(0x1100, scalar)).size != (if (scalar in kiratRaiVowels) 2 else 1)
                GraphemeClusterBreak.T -> ends(text(0x1161, scalar)).size != 1
                GraphemeClusterBreak.LV -> ends(text(scalar, 0x1161)).size != 1
                GraphemeClusterBreak.LVT -> ends(text(scalar, 0x11A8)).size != 1 || ends(text(scalar, 0x1161)).size != 2
                GraphemeClusterBreak.REGIONAL_INDICATOR -> ends(text(scalar, scalar, scalar)).size != 2
                GraphemeClusterBreak.ZWJ -> ends(text(0x1F600, scalar, 0x1F600)).size != 1
                else -> false
            }
        }
        assertEquals(emptyList<String>(), mismatches.map(::hex))
    }

    @Test
    fun extendedPictographicIsUnicodes() {
        // Unicode 17 took symbols that are no emoji off Extended_Pictographic, and gave it to none.
        val mismatches = known.filter { scalar ->
            val pictographic = data[scalar] and 0x8 != 0
            val emoji = UCharacter.hasBinaryProperty(scalar, UProperty.EMOJI) &&
                !UCharacter.hasBinaryProperty(scalar, UProperty.EMOJI_COMPONENT)
            (pictographic && !UCharacter.hasBinaryProperty(scalar, UProperty.EXTENDED_PICTOGRAPHIC)) || (emoji && !pictographic)
        }
        assertEquals(emptyList<String>(), mismatches.map(::hex))
    }

    @Test
    fun indicConjunctPropertiesAreUnicodes() {
        val consonant = UCharacter.IndicConjunctBreak.CONSONANT.ordinal
        val linker = UCharacter.IndicConjunctBreak.LINKER.ordinal
        // Unicode 17 gave more scripts' letters InCB=Consonant, and took it from none.
        val mismatches = known.filter { scalar ->
            val inData = data[scalar] and 0x10 != 0
            val inIcu = UCharacter.getIntPropertyValue(scalar, UProperty.INDIC_CONJUNCT_BREAK) == consonant
            (inIcu && !inData) || (inData && !inIcu && UCharacter.getType(scalar) != UCharacter.OTHER_LETTER.toInt())
        }
        assertEquals(emptyList<String>(), mismatches.map(::hex))
        // Unicode 16's linkers are the six Swift names, and each joins two consonants.
        val linkers = known.filter { UCharacter.getIntPropertyValue(it, UProperty.INDIC_CONJUNCT_BREAK) == linker }
        assertEquals(listOf(0x94D, 0x9CD, 0xACD, 0xB4D, 0xC4D, 0xD4D), linkers)
        for (scalar in linkers) assertEquals(hex(scalar), listOf(3), ends(text(0x915, scalar, 0x915)))
    }

    @Test
    fun charactersEndWhereSwiftsDo() {
        // A conjunct is one Character in each script whose virama Swift names, Unicode 16's six.
        assertEquals(listOf(3), ends("क्ष"))
        assertEquals(listOf(3), ends("ক্ষ"))
        assertEquals(listOf(3), ends("ક્ષ"))
        assertEquals(listOf(3), ends("କ୍ଷ"))
        assertEquals(listOf(3), ends("క్ష"))
        assertEquals(listOf(3), ends("ക്ഷ"))
        assertEquals(listOf(5), ends("क़्‍ष"))
        assertEquals(listOf(3, 4), ends("क्‌ष"))
        // Unicode 17's further linkers join nothing in Swift; its further consonants follow one of the six.
        assertEquals(listOf(2, 3), ends("က္က"))
        assertEquals(listOf(2, 3), ends("ស្ត"))
        assertEquals(listOf(3), ends("क्က"))
        // Unicode 17 has ✁ as no pictograph, so a joiner does not hold it to the next.
        assertEquals(listOf(2, 3), ends("✁‍✁"))
        assertEquals(listOf(8), ends("👨‍👩‍👧"))
        assertEquals(listOf(7), ends("👩🏽‍💻"))
        assertEquals(listOf(14), ends("🏴󠁧󠁢󠁳󠁣󠁴󠁿"))
        assertEquals(listOf(4, 8, 10), ends("🇫🇷🇩🇪🇮"))
        assertEquals(listOf(3, 5), ends("각각"))
        assertEquals(listOf(1, 3, 5), ends("ᄀ𖵣𖵣"))
        assertEquals(listOf(2, 3, 4), ends("\r\n\n\r"))
        assertEquals(listOf(2, 3), ends("؀a؀"))
        // A lone surrogate is U+FFFD, which a mark joins; an unassigned scalar is like any other.
        assertEquals(listOf(2, 3, 4), ends("\uD800́\uDC00a"))
        assertEquals(listOf(2), ends("͸́"))
        assertEquals(emptyList<Int>(), ends(""))
        assertEquals(listOf("é", "x"), "éx".characters())
    }
}
