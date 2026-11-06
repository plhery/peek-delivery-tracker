package com.plhery.peekdeliverytracker

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import java.io.File
import java.time.ZoneId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * What the app says about now reads [AppClock], never the computer's clock itself: the tests stop it at the captures'
 * moment, so a screenshot recorded one day still matches the next, at any hour.
 */
@RunWith(AndroidJUnit4::class)
class AppClockTest {
    private val clocks = Regex(
        """\b(?:Instant|LocalDate|LocalDateTime|LocalTime|ZonedDateTime|OffsetDateTime|YearMonth|Year)(?:\.now\(|::now\b)""" +
            """|\bClock\.system|\bSystem\.currentTimeMillis\(|(?<![\w.])Date\(\)|\bCalendar\.getInstance\(""",
    )

    @Test
    fun theAppAndItsCoreReadTheTimeThroughTheAppClock() {
        val module = File(System.getProperty("user.dir"))
        val sources = listOf(module.resolve("src/main/kotlin"), module.resolve("../core/src/main/kotlin"))
            .flatMap { directory -> directory.walk().filter { it.extension == "kt" && it.name != "AppClock.kt" }.toList() }
        assertTrue("No Kotlin sources under $module", sources.size > 10)
        val reads = sources.flatMap { file ->
            file.readLines().withIndex()
                .filter { (_, line) -> !line.trimStart().let { it.startsWith("//") || it.startsWith("*") || it.startsWith("/*") } }
                .filter { (_, line) -> clocks.containsMatchIn(line.substringBefore(" // ")) }
                .map { (index, line) -> "${file.name}:${index + 1}: ${line.trim()}" }
        }
        assertEquals("Read AppClock.now() instead", emptyList<String>(), reads)
    }

    @Test
    fun theTestsRunAtTheCapturesMomentInZurich() {
        ApplicationProvider.getApplicationContext<PeekApplication>()
        assertEquals(CAPTURED, AppClock.now())
        assertEquals(ZoneId.of("Europe/Zurich"), ZoneId.systemDefault())
    }
}
