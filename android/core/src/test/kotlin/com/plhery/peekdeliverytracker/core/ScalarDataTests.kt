package com.plhery.peekdeliverytracker.core

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UCharacterCategory
import com.ibm.icu.text.Normalizer2
import com.ibm.icu.util.VersionInfo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * ScalarData.kt and Normalization.kt. The data is Unicode 17, as Swift's standard library has it; ICU4J 76's is Unicode
 * 16: they must agree on every scalar ICU4J knows. The expected answers for Unicode 17's scalars are Swift's on macOS 27.
 */
class ScalarDataTests {
    companion object {
        /** The scalars ICU4J knows: Swift reads a lone surrogate as U+FFFD, and Unicode 17's new scalars are new to ICU4J. */
        private val known by lazy { (0..0x10FFFF).filter { it !in 0xD800..0xDFFF && UCharacter.getAge(it).major > 0 } }

        private val nfc = Normalizer2.getNFCInstance()
        private val nfd = Normalizer2.getNFDInstance()
    }

    private fun text(vararg scalars: Int) = String(scalars, 0, scalars.size)

    private fun hex(scalar: Int) = "U+%04X".format(scalar)

    private fun hex(text: String) = text.codePoints().toArray().joinToString(" ") { "%04X".format(it) }

    @Test
    fun theDataIsSwiftsUnicode() {
        assertEquals("17.0.0", ScalarData.unicodeVersion)
        assertEquals("13.0.0", ScalarData.oldestPhoneVersion)
        assertEquals(16, UCharacter.getUnicodeVersion().major)
    }

    @Test
    fun formatCharactersAreUnicodes() {
        val mismatches = known.filter { isFormatScalar(it) != (UCharacter.getType(it) == UCharacterCategory.FORMAT.toInt()) }
        assertEquals(emptyList<String>(), mismatches.map(::hex))
        // Unknown to Android 12, whose ICU is Unicode 13.
        for (scalar in listOf(0x0890, 0x0891) + (0x13439..0x1343F)) assertTrue(hex(scalar), isFormatScalar(scalar))
        for (scalar in listOf(0x0892, 0x13440, 0x1342E, 0x0041, 0xFFFD)) assertFalse(hex(scalar), isFormatScalar(scalar))
        for (scalar in listOf(0x00AD, 0x200B, 0x200D, 0x2060, 0xFEFF, 0xE0001, 0xE007F)) assertTrue(hex(scalar), isFormatScalar(scalar))
    }

    @Test
    fun combiningClassesAreUnicodes() {
        val mismatches = known.filter { combiningClass(it) != UCharacter.getCombiningClass(it) }
        assertEquals(emptyList<String>(), mismatches.map(::hex))
        // Unicode 17's own, as Swift has them.
        assertEquals(230, combiningClass(0x1ACF))
        assertEquals(220, combiningClass(0x1ADD))
        assertEquals(230, combiningClass(0x1AE0))
        assertEquals(234, combiningClass(0x1AEB))
        assertEquals(0, combiningClass(0x10FFFF))
    }

    /** The scalars whose normalization a Unicode 13 phone does not know: new marks, new composites and their new parts. */
    @Test
    fun theDeltaIsWhatTheOldestPhoneDoesNotKnow() {
        val oldest = VersionInfo.getInstance(13, 0)
        val new = known.filter { UCharacter.getAge(it) > oldest }
        val composites = new.filter { nfc.getRawDecomposition(it) != null }
        val parts = composites.flatMap { composite -> nfc.getRawDecomposition(composite)!!.codePoints().toArray().toList() }
        val expected = (new.filter { UCharacter.getCombiningClass(it) != 0 } + composites + parts.filter { it in new }).toSortedSet()
        assertEquals(expected.map(::hex), ScalarData.normalizationDelta.filter { it in known }.map(::hex))
        // Unicode 14's, 15's and 16's, which ICU4J knows: a mark, a new composite and its new parts.
        assertTrue(expected.containsAll(listOf(0x1AC3, 0x1DFA, 0x0897, 0x16121, 0x1611E, 0x105C9, 0x105D2)))
        assertTrue(ScalarData.normalizationDelta.all { it >= 0x0897 })
        assertEquals(132, ScalarData.normalizationDelta.size)
    }

    @Test
    fun everyScalarNormalizesAsUnicodeSays() {
        val mismatches = known.filter { scalar ->
            val text = text(scalar)
            text.nfc() != nfc.normalize(text) || text.nfd() != nfd.normalize(text)
        }
        assertEquals(emptyList<String>(), mismatches.map(::hex))
    }

    /** Among other marks and beside the composites they may join, the new scalars ICU4J knows normalize as it says. */
    @Test
    fun newScalarsNormalizeInContextAsUnicodeSays() {
        val delta = ScalarData.normalizationDelta.filter { it in known }
        val texts = delta.flatMap { scalar ->
            listOf(
                text(0x61, scalar, 0x301), text(0x61, 0x301, scalar), text(0x61, scalar, 0x316, 0x301), text(scalar, scalar),
                text(scalar, 0x301), text(0x1100, 0x1161, scalar), text(0xE1, scalar), text(0x1E0B, 0x323, scalar),
            ) + (nfc.getRawDecomposition(scalar)?.let { parts -> listOf(parts, parts + "́", "a$partş") } ?: emptyList())
        }
        val mismatches = texts.filter { it.nfc() != nfc.normalize(it) || it.nfd() != nfd.normalize(it) }
        assertEquals(emptyList<String>(), mismatches.map(::hex))
    }

    /** Swift's `==` on macOS 27, with Unicode 17's marks, which no ICU4J nor phone yet knows. */
    @Test
    fun stringsCompareAsSwiftsDo() {
        val equal = listOf(
            text(0x16121) to text(0x1611E, 0x1611E),
            text(0x16126) to text(0x1611E, 0x1611E, 0x1611F),
            text(0x105C9) to text(0x105D2, 0x307),
            text(0x16D6A) to text(0x16D63, 0x16D67, 0x16D67),
            text(0x113C5) to text(0x113C2, 0x113C2),
            text(0x61, 0x1ADD, 0x301) to text(0xE1, 0x1ADD),
            text(0x61, 0x1E5EF, 0x301) to text(0xE1, 0x1E5EF),
            text(0x61, 0x1AC3, 0x301) to text(0xE1, 0x1AC3),
            text(0x61, 0x301, 0x1ADD) to text(0x61, 0x1ADD, 0x301),
            text(0x61, 0x316, 0x1AEB) to text(0x61, 0x1AEB, 0x316),
            text(0x212A) to "K",
            text(0x1E0B, 0x323) to text(0x1E0D, 0x307),
        )
        for ((left, right) in equal) {
            assertTrue("${hex(left)} == ${hex(right)}", left.equalsCanonically(right))
            assertEquals(hex(left), left.canonicalHashCode(), right.canonicalHashCode())
        }
        val different = listOf(
            text(0x61, 0x301, 0x1AE0) to text(0x61, 0x1AE0, 0x301),
            text(0x61, 0x1AE0, 0x301) to text(0xE1, 0x1AE0),
            text(0x61, 0x897, 0x301) to text(0xE1, 0x897),
        )
        for ((left, right) in different) assertFalse("${hex(left)} != ${hex(right)}", left.equalsCanonically(right))
        assertEquals(text(0xE1, 0x1ADD), text(0x61, 0x1ADD, 0x301).nfc())
        assertEquals(text(0x61, 0x1ADD, 0x301), text(0xE1, 0x1ADD).nfd())
    }
}
