package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.ImportCharges
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test

/**
 * Inputs where Foundation and the JVM part ways in Core.swift's logic, with what the iPhone app answers: each
 * expected value is the output of the Swift code run on iOS 27's Foundation.
 */
class CoreParityTests {
    companion object {
        @JvmStatic
        @BeforeClass
        fun installIcu() = TestIcu.install()
    }

    @Test
    fun aLinkHostEndingInALineEndIsLeftOut() {
        assertEquals(emptyList<String>(), AppConfiguration.linkHosts("peektracker.com\nwww.peektracker.com"))
        assertEquals(emptyList<String>(), AppConfiguration.linkHosts("peektracker.com\n"))
        assertEquals(listOf("peektracker.com"), AppConfiguration.linkHosts("PeekTracker.com, www.peektracker.com\r\n"))
    }

    @Test
    fun theLanguageIsTheSavedOneElseTheFirstOfTheDevicesTheAppSpeaks() {
        val expected = listOf(
            Triple(null, listOf("nl-NL", "de-DE"), AppLanguage.De),
            Triple("fr", listOf("de"), AppLanguage.Fr),
            Triple("", listOf("it-IT"), AppLanguage.It),
            Triple("xx", listOf("pt-BR"), AppLanguage.Pt),
            Triple("DE", listOf("it"), AppLanguage.It),
            Triple(null, listOf("PL-pl"), AppLanguage.Pl),
            Triple(null, listOf("-es"), AppLanguage.Es),
            Triple(null, listOf("zh-Hans-CN", "nl"), AppLanguage.En),
            Triple(null, listOf(""), AppLanguage.En),
            Triple(null, emptyList<String>(), AppLanguage.En),
        )
        for ((saved, preferred, language) in expected) {
            assertEquals("$saved $preferred", language, AppLanguage.current(saved, preferred))
        }
    }

    @Test
    fun aZeroWidthSpaceIsWhitespaceToFoundation() {
        assertEquals("AB 12", "​ AB 12​​".trimmingWhitespaces())
        assertEquals("AB", "​\nAB​ ".trimmingWhitespacesAndNewlines())
        assertEquals("᠎AB﻿", "᠎AB﻿".trimmingWhitespacesAndNewlines())
    }

    /** An amount as the import charges line writes it: `formatted(.currency(code:).locale(_:))` on iOS. */
    private fun money(language: AppLanguage, code: String, amount: Double): String? {
        val localizer = Localizer(language)
        val id = UUID.randomUUID()
        val parcel = makeParcel(id, events = listOf(event(id, TrackingStage.InTransit, "2026-10-20T10:00:00Z")))
            .copy(carrierData = CarrierData(importCharges = ImportCharges(currency = code, customs = amount)))
        return localizer.importCharges(parcel)?.text
    }

    @Test
    fun amountsAreWrittenAsFoundationsCurrencyFormatWritesThem() {
        val amounts = listOf(0.5, 34.41, 1234.5, 12345.67)
        val expected = mapOf(
            AppLanguage.En to mapOf(
                "EUR" to listOf("€0.50", "€34.41", "€1,234.50", "€12,345.67"),
                "CHF" to listOf("CHF\u00A00.50", "CHF\u00A034.41", "CHF\u00A01,234.50", "CHF\u00A012,345.67"),
                "GBP" to listOf("£0.50", "£34.41", "£1,234.50", "£12,345.67"),
                "PLN" to listOf("PLN\u00A00.50", "PLN\u00A034.41", "PLN\u00A01,234.50", "PLN\u00A012,345.67"),
                "JPY" to listOf("¥0", "¥34", "¥1,234", "¥12,346"),
                "USD" to listOf("\$0.50", "\$34.41", "\$1,234.50", "\$12,345.67"),
                "XYZ" to listOf("XYZ\u00A00.50", "XYZ\u00A034.41", "XYZ\u00A01,234.50", "XYZ\u00A012,345.67"),
            ),
            AppLanguage.De to mapOf(
                "EUR" to listOf("0,50\u00A0€", "34,41\u00A0€", "1.234,50\u00A0€", "12.345,67\u00A0€"),
                "CHF" to listOf("0,50\u00A0CHF", "34,41\u00A0CHF", "1.234,50\u00A0CHF", "12.345,67\u00A0CHF"),
                "GBP" to listOf("0,50\u00A0£", "34,41\u00A0£", "1.234,50\u00A0£", "12.345,67\u00A0£"),
                "PLN" to listOf("0,50\u00A0PLN", "34,41\u00A0PLN", "1.234,50\u00A0PLN", "12.345,67\u00A0PLN"),
                "JPY" to listOf("0\u00A0¥", "34\u00A0¥", "1.234\u00A0¥", "12.346\u00A0¥"),
                "USD" to listOf("0,50\u00A0\$", "34,41\u00A0\$", "1.234,50\u00A0\$", "12.345,67\u00A0\$"),
                "XYZ" to listOf("0,50\u00A0XYZ", "34,41\u00A0XYZ", "1.234,50\u00A0XYZ", "12.345,67\u00A0XYZ"),
            ),
            AppLanguage.Fr to mapOf(
                "EUR" to listOf("0,50\u00A0€", "34,41\u00A0€", "1\u202F234,50\u00A0€", "12\u202F345,67\u00A0€"),
                "CHF" to listOf("0,50\u00A0CHF", "34,41\u00A0CHF", "1\u202F234,50\u00A0CHF", "12\u202F345,67\u00A0CHF"),
                "GBP" to listOf("0,50\u00A0£GB", "34,41\u00A0£GB", "1\u202F234,50\u00A0£GB", "12\u202F345,67\u00A0£GB"),
                "PLN" to listOf("0,50\u00A0PLN", "34,41\u00A0PLN", "1\u202F234,50\u00A0PLN", "12\u202F345,67\u00A0PLN"),
                "JPY" to listOf("0\u00A0JPY", "34\u00A0JPY", "1\u202F234\u00A0JPY", "12\u202F346\u00A0JPY"),
                "USD" to listOf("0,50\u00A0\$US", "34,41\u00A0\$US", "1\u202F234,50\u00A0\$US", "12\u202F345,67\u00A0\$US"),
                "XYZ" to listOf("0,50\u00A0XYZ", "34,41\u00A0XYZ", "1\u202F234,50\u00A0XYZ", "12\u202F345,67\u00A0XYZ"),
            ),
            AppLanguage.It to mapOf(
                "EUR" to listOf("0,50\u00A0€", "34,41\u00A0€", "1234,50\u00A0€", "12.345,67\u00A0€"),
                "CHF" to listOf("0,50\u00A0CHF", "34,41\u00A0CHF", "1234,50\u00A0CHF", "12.345,67\u00A0CHF"),
                "GBP" to listOf("0,50\u00A0£", "34,41\u00A0£", "1234,50\u00A0£", "12.345,67\u00A0£"),
                "PLN" to listOf("0,50\u00A0PLN", "34,41\u00A0PLN", "1234,50\u00A0PLN", "12.345,67\u00A0PLN"),
                "JPY" to listOf("0\u00A0JPY", "34\u00A0JPY", "1234\u00A0JPY", "12.346\u00A0JPY"),
                "USD" to listOf("0,50\u00A0USD", "34,41\u00A0USD", "1234,50\u00A0USD", "12.345,67\u00A0USD"),
                "XYZ" to listOf("0,50\u00A0XYZ", "34,41\u00A0XYZ", "1234,50\u00A0XYZ", "12.345,67\u00A0XYZ"),
            ),
            AppLanguage.Es to mapOf(
                "EUR" to listOf("0,50\u00A0€", "34,41\u00A0€", "1234,50\u00A0€", "12.345,67\u00A0€"),
                "CHF" to listOf("0,50\u00A0CHF", "34,41\u00A0CHF", "1234,50\u00A0CHF", "12.345,67\u00A0CHF"),
                "GBP" to listOf("0,50\u00A0GBP", "34,41\u00A0GBP", "1234,50\u00A0GBP", "12.345,67\u00A0GBP"),
                "PLN" to listOf("0,50\u00A0PLN", "34,41\u00A0PLN", "1234,50\u00A0PLN", "12.345,67\u00A0PLN"),
                "JPY" to listOf("0\u00A0JPY", "34\u00A0JPY", "1234\u00A0JPY", "12.346\u00A0JPY"),
                "USD" to listOf("0,50\u00A0US\$", "34,41\u00A0US\$", "1234,50\u00A0US\$", "12.345,67\u00A0US\$"),
                "XYZ" to listOf("0,50\u00A0XYZ", "34,41\u00A0XYZ", "1234,50\u00A0XYZ", "12.345,67\u00A0XYZ"),
            ),
            AppLanguage.Pt to mapOf(
                "EUR" to listOf("0,50\u00A0€", "34,41\u00A0€", "1234,50\u00A0€", "12\u00A0345,67\u00A0€"),
                "CHF" to listOf("0,50\u00A0CHF", "34,41\u00A0CHF", "1234,50\u00A0CHF", "12\u00A0345,67\u00A0CHF"),
                "GBP" to listOf("0,50\u00A0£", "34,41\u00A0£", "1234,50\u00A0£", "12\u00A0345,67\u00A0£"),
                "PLN" to listOf("0,50\u00A0PLN", "34,41\u00A0PLN", "1234,50\u00A0PLN", "12\u00A0345,67\u00A0PLN"),
                "JPY" to listOf("0\u00A0JP¥", "34\u00A0JP¥", "1234\u00A0JP¥", "12\u00A0346\u00A0JP¥"),
                "USD" to listOf("0,50\u00A0US\$", "34,41\u00A0US\$", "1234,50\u00A0US\$", "12\u00A0345,67\u00A0US\$"),
                "XYZ" to listOf("0,50\u00A0XYZ", "34,41\u00A0XYZ", "1234,50\u00A0XYZ", "12\u00A0345,67\u00A0XYZ"),
            ),
            AppLanguage.Pl to mapOf(
                "EUR" to listOf("0,50\u00A0€", "34,41\u00A0€", "1234,50\u00A0€", "12\u00A0345,67\u00A0€"),
                "CHF" to listOf("0,50\u00A0CHF", "34,41\u00A0CHF", "1234,50\u00A0CHF", "12\u00A0345,67\u00A0CHF"),
                "GBP" to listOf("0,50\u00A0GBP", "34,41\u00A0GBP", "1234,50\u00A0GBP", "12\u00A0345,67\u00A0GBP"),
                "PLN" to listOf("0,50\u00A0zł", "34,41\u00A0zł", "1234,50\u00A0zł", "12\u00A0345,67\u00A0zł"),
                "JPY" to listOf("0\u00A0JPY", "34\u00A0JPY", "1234\u00A0JPY", "12\u00A0346\u00A0JPY"),
                "USD" to listOf("0,50\u00A0USD", "34,41\u00A0USD", "1234,50\u00A0USD", "12\u00A0345,67\u00A0USD"),
                "XYZ" to listOf("0,50\u00A0XYZ", "34,41\u00A0XYZ", "1234,50\u00A0XYZ", "12\u00A0345,67\u00A0XYZ"),
            ),
        )
        for ((language, codes) in expected) {
            for ((code, texts) in codes) {
                assertEquals("$language $code", texts, amounts.map { money(language, code, it) })
            }
        }
    }

    @Test
    fun aCurrencyCodeIsReadAsFoundationHandsItToICU() {
        assertEquals("HUF\u00A01,234", money(AppLanguage.En, "HUF", 1234.5))
        assertEquals("1.234\u00A0HUF", money(AppLanguage.De, "HUF", 1234.5))
        assertEquals("€34.41", money(AppLanguage.En, "eur", 34.41))
        assertEquals("€34.41", money(AppLanguage.En, "EUR\n", 34.41))
        assertEquals("34.41", money(AppLanguage.En, "EU", 34.41))
        assertEquals("10000000.0", money(AppLanguage.En, "ABCD", 10000000.0))
        assertEquals("1234.5", money(AppLanguage.En, " EUR", 1234.5))
        assertEquals("E1R\u00A034.41", money(AppLanguage.En, "E1R", 34.41))
        assertEquals("34,41\u00A0E1R", money(AppLanguage.De, "E1R", 34.41))
        assertEquals("EU+34.41", money(AppLanguage.En, "EU+", 34.41))
        assertEquals("1.5e-05", money(AppLanguage.En, "", 1.5e-5))
        assertEquals("1\u202F234,50\u00A0\$US", money(AppLanguage.Fr, "USD", 1234.5))
        assertEquals("12\u00A0345,67\u00A0zł", money(AppLanguage.Pl, "PLN", 12345.67))
        assertEquals("¥2", money(AppLanguage.En, "JPY", 2.5))
        assertEquals("€2.34", money(AppLanguage.En, "EUR", 2.345))
        assertEquals("-€3.50", money(AppLanguage.En, "EUR", -3.5))
    }

    /** The instant to the millisecond, as the iOS output was printed. */
    private fun Instant.toMillisecond(): Instant = Instant.ofEpochSecond(epochSecond, Math.round(nano / 1e6) * 1_000_000)

    // java.time writes dates in the proleptic Gregorian calendar, so a Julian date before 15 October 1582 shows
    // ten days later, and two days earlier in year 26.

    @Test
    fun aTimestampIsReadAsISO8601DateFormatterReadsIt() {
        val expected = listOf(
            "2026-09-27T10:00:00+0200" to "2026-09-27T08:00:00Z",
            "2026-09-27T10:00:00+02" to "2026-09-27T08:00:00Z",
            "2026-09-27T10:00:00+2:00" to "2026-09-26T14:00:00Z",
            "2026-09-27T10:00:00-02:30" to "2026-09-27T12:30:00Z",
            "2026-09-27T10:00:00GMT+0260" to "2026-09-27T08:00:00Z",
            "2026-09-27T10:00:00UTC+0260" to "2026-09-27T09:34:00Z",
            "2026-09-27T10:00:00GMT+02:00" to "2026-09-27T08:00:00Z",
            "2026-09-27T10:00:00UTC+5" to "2026-09-27T05:00:00Z",
            "2026-09-27T10:00:00GMT-3" to "2026-09-27T13:00:00Z",
            "2026-09-27T10:00:00UTC" to "2026-09-27T10:00:00Z",
            "2026-09-27T10:00:00UT" to "2026-09-27T10:00:00Z",
            "2026-09-27T10:00:00GMT" to "2026-09-27T10:00:00Z",
            "2026-9-7T1:2:3Z" to "2026-09-07T01:02:03Z",
            "2026-09-27T24:00:00Z" to "2026-09-28T00:00:00Z",
            "2026-02-30T10:00:00Z" to "2026-03-02T10:00:00Z",
            "2026-09-27T10:00:00Z[Europe/Paris]" to "2026-09-27T10:00:00Z",
            "2026-09-27T10:00:00+02:00 junk" to "2026-09-27T08:00:00Z",
            "2026/09/27T10:00:00Z" to "2026-09-27T10:00:00Z",
            "2026.09.27T10.00.00Z" to "2026-09-27T10:00:00Z",
            "2026-Sep-27T10:00:00Z" to "2026-09-27T10:00:00Z",
            "2026-SEPTEMBER-27T10:00:00Z" to "2026-09-27T10:00:00Z",
            "２０２６-٠٩-27T10:00:00Z" to "2026-09-27T10:00:00Z",
            "‎2026-09-27T10:00:00Z" to "2026-09-27T10:00:00Z",
            "2026-09-27T10:00:00.5Z" to "2026-09-27T10:00:00.500Z",
            "2026-09-27T10:00:00.123456Z" to "2026-09-27T10:00:00.123Z",
            "2026-09-27T1e1:00:00Z" to "2026-09-27T10:00:00Z",
            "NaN-09-27T10:00:00Z" to "0000-09-25T10:00:00Z",
            "1582-10-04T12:00:00Z" to "1582-10-14T12:00:00Z",
            "1582-10-10T12:00:00Z" to "1582-10-20T12:00:00Z",
            "1582-10-15T12:00:00Z" to "1582-10-15T12:00:00Z",
            "26-09-27T10:00:00Z" to "0026-09-25T10:00:00Z",
            "12050-01-01T00:00:00Z" to "1950-01-01T00:00:00Z",
            "2026-09-27T10:00:00" + " ".repeat(1004) + "Z" to "2026-09-27T10:00:00Z",
            "2026-09-27T10:00:00" + " ".repeat(1005) + "Z" to null,
            "2026-09-27 10:00:00Z" to null,
            "2026-09-27t10:00:00z" to null,
            "2026-Sept-27T10:00:00Z" to null,
            "2026-13-01T10:00:00Z" to null,
            "2026-09-27T25:00:00Z" to null,
            "2026-09-27T10:00:60Z" to null,
            "2026-09-27T10:00:00" to null,
            "2026-09-27T10:00:00.Z" to null,
        )
        for ((value, date) in expected) {
            assertEquals(value.take(40), date?.let(Instant::parse), DateParser.date(value)?.toMillisecond())
        }
    }

    /** `deliveryDate` of a day, in the zone given rather than the system's. */
    private fun day(value: String, zone: String): Instant? = FoundationDates.day(value.characterPrefix(10), ZoneId.of(zone))

    @Test
    fun aDayIsReadAsTheDayFormatterReadsIt() {
        val expected = listOf(
            "2026-02-30" to "2026-03-02T00:00:00Z",
            "2026-9-7" to "2026-09-07T00:00:00Z",
            " 2026-9-7" to "2026-09-07T00:00:00Z",
            "2026-9-7\t " to "2026-09-07T00:00:00Z",
            "2026-9-7​" to "2026-09-07T00:00:00Z",
            "2026-9-7　" to "2026-09-07T00:00:00Z",
            "2026-09-27x" to "2026-09-27T00:00:00Z",
            "2026/09/27" to "2026-09-27T00:00:00Z",
            "2026-Sep-7" to "2026-09-07T00:00:00Z",
            "26-09-27" to "0026-09-25T00:00:00Z",
            "1582-10-10" to "1582-10-20T00:00:00Z",
            "12049-12-31" to "2049-12-03T00:00:00Z",
            "2026-9-7 x" to null,
            "2026-9-7\n" to null,
            "2026-9-7T1" to null,
            "2026-09-00" to null,
        )
        for ((value, date) in expected) assertEquals(value, date?.let(Instant::parse), day(value, "UTC"))
        assertEquals(Instant.parse("2026-03-01T23:00:00Z"), day("2026-02-30", "Europe/Paris"))
        // Midnight falls in the gap when the clocks go forward, and the day starts by the offset before it.
        assertEquals(Instant.parse("2026-03-08T05:00:00Z"), day("2026-03-08", "America/Havana"))
        assertEquals(Instant.parse("2026-09-06T04:00:00Z"), day("2026-09-06", "America/Santiago"))
    }
}
