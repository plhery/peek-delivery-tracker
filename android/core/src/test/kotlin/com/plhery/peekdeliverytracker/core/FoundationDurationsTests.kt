package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * `ios-durations.tsv` holds what `DateComponentsFormatter` writes for the passport's times on iOS (PassportView's
 * `formattedDuration`), abbreviated and spelled out, for each app language: synthetic durations, from a minute to 45
 * days, including those that truncate (61 s, 119 s, 86 399 s).
 */
class FoundationDurationsTests {
    private class Row(val language: AppLanguage, val seconds: Double, val abbreviated: String, val full: String)

    private val rows: List<Row> by lazy {
        val stream = checkNotNull(FoundationDurationsTests::class.java.getResourceAsStream("/ios-durations.tsv"))
        stream.bufferedReader().use { it.readLines() }.drop(1).filter { it.isNotEmpty() }.map { line ->
            val (language, seconds, abbreviated, full) = line.split('\t')
            Row(checkNotNull(AppLanguage.fromRawValue(language)) { language }, seconds.toDouble(), abbreviated, full)
        }
    }

    @Test
    fun everyRowInEveryLanguageReadsAsOnTheIPhone() {
        assertEquals(AppLanguage.entries.toSet(), rows.map { it.language }.toSet())
        assertEquals(160, rows.size)
        for (row in rows) {
            val where = "${row.language.rawValue} ${row.seconds}"
            assertEquals(where, row.abbreviated, FoundationDurations.string(row.seconds, row.language, full = false))
            assertEquals(where, row.full, FoundationDurations.string(row.seconds, row.language, full = true))
        }
    }

    /** A fraction of a second is dropped with the rest of the unit below the last one shown. */
    @Test
    fun fractionsTruncate() {
        assertEquals("1h 59m", FoundationDurations.string(7_199.9, AppLanguage.En, full = false))
        assertEquals("1d 23h", FoundationDurations.string(172_799.5, AppLanguage.En, full = false))
    }
}
