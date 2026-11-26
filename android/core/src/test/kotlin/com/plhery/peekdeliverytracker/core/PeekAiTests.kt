package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PeekAi
import com.plhery.peekdeliverytracker.core.api.PeekAiDay
import com.plhery.peekdeliverytracker.core.api.PeekAiHour
import com.plhery.peekdeliverytracker.core.api.PeekAiSays
import com.plhery.peekdeliverytracker.core.api.PeekAiStep
import com.plhery.peekdeliverytracker.core.api.PeekAiStepKind
import com.plhery.peekdeliverytracker.core.api.PeekAiWhy
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone
import java.util.UUID
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/** Peek AI's rules and words, as the web's (src/lib/peekAi.test.ts), on this phone's clock in Zurich. */
class PeekAiTests {
    private val zone = ZoneId.of("Europe/Zurich")
    private var savedZone: TimeZone? = null

    @Before
    fun inZurich() {
        savedZone = TimeZone.getDefault()
        TimeZone.setDefault(TimeZone.getTimeZone(zone))
    }

    @After
    fun backHome() {
        TimeZone.setDefault(savedZone)
    }

    /** A time on this phone's clock. */
    private fun at(day: Int, hour: Int, minute: Int = 0): Instant = LocalDateTime.of(2026, 11, day, hour, minute).atZone(zone).toInstant()

    // Tuesday 24 November 2026, 15:30 on this phone's clock.
    private val now get() = at(24, 15, 30)
    private val english = Localizer(AppLanguage.En)

    private val notToday = PeekAi(says = PeekAiSays.NotToday, why = PeekAiWhy.NoOutForDelivery, carrierDay = "2026-11-24", sure = 0.82)
    private val byTime = PeekAi(
        says = PeekAiSays.ByTime, why = PeekAiWhy.OutForDelivery, time = "16:00", sure = 0.86, outSince = "2026-11-24T08:12:00+01:00",
        hours = listOf(0.02, 0.05, 0.12, 0.2, 0.22, 0.16, 0.1, 0.06, 0.04, 0.03).mapIndexed { index, share -> PeekAiHour("%02d:00".format(8 + index), share) },
    )
    private val noDate get() = PeekAi(
        says = PeekAiSays.ByDay, why = PeekAiWhy.NoDate, day = "2026-11-26", sure = 0.81,
        last = PeekAiStep(kind = PeekAiStepKind.Depot, at = DateParser.isoString(now.minusSeconds(2 * 86_400 + 3_600))),
        days = listOf(PeekAiDay("2026-11-24", 0.05), PeekAiDay("2026-11-25", 0.34), PeekAiDay("2026-11-26", 0.41), PeekAiDay("2026-11-27", 0.12)),
    )
    private val sooner = PeekAi(says = PeekAiSays.Sooner, why = PeekAiWhy.Ahead, day = "2026-11-25", carrierDay = "2026-11-27", sure = 0.71)

    @Test
    fun saysNotTodayOnlyOnTheCarriersDayOrTheDayTheParcelWentOut() {
        assertSame(notToday, currentPeekAi(notToday, now))
        assertNull(currentPeekAi(notToday, at(25, 9)))
        val late = PeekAi(says = PeekAiSays.NotToday, why = PeekAiWhy.LateOut, sure = 0.9, outSince = "2026-11-24T08:12:00+01:00")
        assertSame(late, currentPeekAi(late, now))
        assertNull(currentPeekAi(late, at(25, 0, 5)))
    }

    @Test
    fun namesAnHourUntilItComesOnTheDayTheParcelWentOut() {
        assertSame(byTime, currentPeekAi(byTime, now))
        assertNull(currentPeekAi(byTime, at(24, 16)))
        assertNull(currentPeekAi(byTime, at(25, 9)))
    }

    @Test
    fun holdsADayUntilItHasPassedAndSoonerUntilTheCarriersOwnDayComes() {
        val noDate = noDate
        assertSame(noDate, currentPeekAi(noDate, at(26, 23, 59)))
        assertNull(currentPeekAi(noDate, at(27, 0, 1)))
        assertSame(sooner, currentPeekAi(sooner, at(26, 23, 59)))
        assertNull(currentPeekAi(sooner, at(27, 0, 1)))
        assertNull(currentPeekAi(null, now))
    }

    @Test
    fun aWidgetLetsTheCallGoWhenTheAppWould() {
        assertEquals(at(25, 0), peekAiUntil(notToday))
        assertEquals(at(24, 16), peekAiUntil(byTime))
        assertEquals(at(27, 0), peekAiUntil(noDate))
        assertEquals(at(27, 0), peekAiUntil(sooner))
        assertNull(peekAiUntil(sooner.copy(carrierDay = null)))
    }

    /** The phone in `zone` for `run`. */
    private fun <T> onAPhoneIn(zone: String, run: () -> T): T {
        val home = TimeZone.getDefault()
        TimeZone.setDefault(TimeZone.getTimeZone(zone))
        return try {
            run()
        } finally {
            TimeZone.setDefault(home)
        }
    }

    @Test
    fun endsAtTheMomentTheServerGivesWhateverThePhonesZone() {
        val zurich = byTime.copy(endsAt = "2026-11-24T16:00:00+01:00")
        assertSame(zurich, currentPeekAi(zurich, Instant.parse("2026-11-24T14:59:00Z")))
        assertNull(currentPeekAi(zurich, Instant.parse("2026-11-24T15:00:00Z")))
        assertEquals(Instant.parse("2026-11-24T15:00:00Z"), peekAiUntil(zurich))
        // A phone in Tokyo, already on the 25th: the parcel's evening still holds, not this phone's day.
        val tonight = notToday.copy(endsAt = "2026-11-25T00:00:00+01:00")
        onAPhoneIn("Asia/Tokyo") {
            assertNull("The phone's own day would have let it go", currentPeekAi(notToday, Instant.parse("2026-11-24T22:59:00Z")))
            assertSame(tonight, currentPeekAi(tonight, Instant.parse("2026-11-24T22:59:00Z")))
            assertNull(currentPeekAi(tonight, Instant.parse("2026-11-24T23:00:00Z")))
            assertEquals(Instant.parse("2026-11-24T23:00:00Z"), peekAiUntil(tonight))
            assertEquals(listOf(Instant.parse("2026-11-24T23:00:00Z")), peekAiChanges(parcel(TrackingStage.InTransit, tonight), Instant.parse("2026-11-24T20:00:00Z")))
        }
        // A copy whose moment doesn't read goes by this phone's day.
        val unread = sooner.copy(endsAt = "soon")
        assertSame(unread, currentPeekAi(unread, at(26, 12)))
        assertNull(currentPeekAi(unread, at(27, 0, 1)))
    }

    /** Zurich's clocks go back an hour on 25 October 2026: each midnight is still the day's, and the day ends at its own. */
    @Test
    fun aDayEndsAtItsOwnMidnightAcrossTheChangeOfTime() {
        fun october(day: Int, hour: Int = 0) = LocalDateTime.of(2026, 10, day, hour, 0).atZone(zone).toInstant()
        val ai = noDate.copy(day = "2026-10-27", last = null, days = null)
        val then = october(23, 12)
        assertEquals((24..28).map { october(it) }, peekAiChanges(parcel(TrackingStage.InTransit, ai), then))
        assertEquals(Duration.ofHours(25), Duration.between(october(25), october(26)))
        assertEquals(october(28), peekAiUntil(ai))
        assertSame(ai, currentPeekAi(ai, october(28).minusSeconds(60)))
        assertNull(currentPeekAi(ai, october(28)))
        // The server's moment says the same: midnight at 23:00 in UTC once the clocks have gone back.
        val served = ai.copy(endsAt = "2026-10-28T00:00:00+01:00")
        assertEquals(october(28), peekAiUntil(served))
        assertEquals(Instant.parse("2026-10-27T23:00:00Z"), peekAiUntil(served))
    }

    @Test
    fun commentsOnTheCarriersTodayOnACardAndSaysNotTodayWhereItStandsAlone() {
        assertEquals("unlikely", english.peekAiCardWords(notToday, "2026-11-24", now))
        assertEquals("not today", english.peekAiCardWords(notToday, null, now))
        assertEquals("not today", english.peekAiCardWords(notToday, "2026-11-25", now))
        assertEquals("by 16:00", english.peekAiCardWords(byTime, null, now))
        val twelveHours = Localizer(AppLanguage.En, clockPreference = ClockPreference.TwelveHour)
        assertTrue(Regex("""^by 4:00\sPM$""").matches(twelveHours.peekAiCardWords(byTime, null, now)))
        assertEquals("by Thu 26 Nov", english.peekAiCardWords(noDate, null, now))
        assertEquals("this week", english.peekAiCardWords(noDate.copy(says = PeekAiSays.ThisWeek), null, now))
        assertEquals("maybe sooner", english.peekAiCardWords(sooner, "2026-11-27", now))
    }

    @Test
    fun saysOnThePageWhatItExpectsFromTomorrowOrANamedDay() {
        assertEquals("today is unlikely", english.peekAiClaim(notToday, now))
        assertEquals("Today is unlikely", english.peekAiHeading(notToday, now))
        assertEquals("likely by 16:00", english.peekAiClaim(byTime, now))
        assertEquals("maybe sooner, from tomorrow", english.peekAiClaim(sooner, now))
        assertEquals("maybe sooner, from Thu 26 Nov", english.peekAiClaim(sooner.copy(day = "2026-11-26"), now))
        // Kept past its first day, it names none.
        assertEquals("maybe sooner", english.peekAiClaim(sooner.copy(day = "2026-11-23"), now))
        assertEquals("likely by the end of next week", english.peekAiClaim(noDate.copy(says = PeekAiSays.ByEndOfNextWeek), now))
    }

    @Test
    fun namesTheCarriersWordUnderItsHeadingAndSaysWhy() {
        assertEquals("DPD says today", english.peekAiCarrierWord(notToday, "DPD", "Today"))
        assertEquals("DPD says Fri 27 Nov", english.peekAiCarrierWord(sooner, "DPD", "Fri 27 Nov"))
        assertEquals("UPS gives no date", english.peekAiCarrierWord(noDate, "UPS", null))
        // Still out late: the carrier set out to deliver it today, though its date is hidden while out for delivery.
        val late = PeekAi(says = PeekAiSays.NotToday, why = PeekAiWhy.LateOut, sure = 0.88, outSince = "2026-11-24T08:12:00+01:00")
        assertEquals("DPD says today", english.peekAiCarrierWord(late, "DPD", null))
        assertEquals("DPD says today, but it hasn’t gone out for delivery.", english.peekAiWhy(notToday, "DPD", now))
        assertEquals("Out for delivery since 08:12. FedEx names no hour.", english.peekAiWhy(byTime, "FedEx", now))
        assertEquals("UPS gives no date. It reached the delivery depot 2 days ago.", english.peekAiWhy(noDate, "UPS", now))
        fun scanned(hoursAgo: Long) = noDate.copy(last = PeekAiStep(kind = PeekAiStepKind.Scan, at = DateParser.isoString(now.minusSeconds(hoursAgo * 3_600))))
        assertEquals("UPS gives no date. Its last scan was 5 hours ago.", english.peekAiWhy(scanned(5), "UPS", now))
        // A day or more: by the calendar, then in weeks.
        assertEquals("UPS gives no date. Its last scan was yesterday.", english.peekAiWhy(scanned(24), "UPS", now))
        assertEquals("UPS gives no date. Its last scan was 2 weeks ago.", english.peekAiWhy(scanned(24 * 20), "UPS", now))
    }

    @Test
    fun saysInItsSheetWhatItGoesByTheParcelsPeekHasFollowedTheCarriersUsualHourBeforeThisOnes() {
        assertEquals(
            "It hasn’t gone out for delivery yet. Most parcels Peek has followed that weren’t out by this hour didn’t arrive that day.",
            english.peekAiReason(notToday, "DPD", now),
        )
        assertEquals(
            "Most FedEx parcels Peek has followed arrived by 16:00 on the day they went out for delivery. This one went out at 08:12.",
            english.peekAiReason(byTime, "FedEx", now),
        )
        assertEquals(
            "It’s still out for delivery this late. Most parcels Peek has followed that were still out at this hour didn’t arrive that day.",
            english.peekAiReason(PeekAi(says = PeekAiSays.NotToday, why = PeekAiWhy.LateOut, sure = 0.88), "DPD", now),
        )
        assertEquals(
            "It was scanned in the last 24 hours. Most parcels Peek has followed that kept moving after missing their date arrived within a day or two.",
            english.peekAiReason(noDate.copy(why = PeekAiWhy.MissedDate, says = PeekAiSays.TodayOrTomorrow), "DPD", now),
        )
        assertEquals("Most parcels Peek has followed with scans like this one’s came before the date their carrier gave.", english.peekAiReason(sooner, "DPD", now))
        assertEquals(
            "Peek AI goes by how long parcels Peek has followed with scans like this one’s took. It reached the delivery depot 2 days ago.",
            english.peekAiReason(noDate, "UPS", now),
        )
    }

    @Test
    fun namesHowManyParcelsItStandsOnWhenTheServerSaysInTheReadersNumbersAndCountForms() {
        assertEquals(
            "It hasn’t gone out for delivery yet. Of 89 parcels Peek has followed that weren’t out for delivery by this hour on the day their carrier gave, most didn’t arrive that day.",
            english.peekAiReason(notToday.copy(parcels = 89), "DPD", now),
        )
        val twelveHours = Localizer(AppLanguage.En, clockPreference = ClockPreference.TwelveHour)
        assertEquals(
            "Of 85 FedEx parcels Peek has followed that went out for delivery before 10:00 AM, most arrived by 4:00 PM that day. This one went out at 8:12 AM.",
            twelveHours.peekAiReason(byTime.copy(parcels = 85, startBefore = "10:00"), "FedEx", now).replace(Regex("\\p{Zs}"), " "),
        )
        // Without the round's start, the hour is said as before.
        assertEquals(
            "Most FedEx parcels Peek has followed arrived by 16:00 on the day they went out for delivery. This one went out at 08:12.",
            english.peekAiReason(byTime.copy(parcels = 85), "FedEx", now),
        )
        assertEquals(
            "It’s still out for delivery this late. Of 508 parcels Peek has followed that were still out at this hour, most didn’t arrive that day.",
            english.peekAiReason(PeekAi(says = PeekAiSays.NotToday, why = PeekAiWhy.LateOut, sure = 0.87, parcels = 508), "DPD", now),
        )
        assertEquals(
            "Peek AI learned from 4,988 parcels Peek has followed. Most with scans like this one’s came before the date their carrier gave.",
            english.peekAiReason(sooner.copy(parcels = 4988), "DPD", now),
        )
        assertEquals(
            "Peek AI learned from 4,988 parcels Peek has followed, and goes by how long those with scans like this one’s took. It reached the delivery depot 2 days ago.",
            english.peekAiReason(noDate.copy(parcels = 4988), "UPS", now),
        )
        // A missed date names no count.
        val missed = noDate.copy(why = PeekAiWhy.MissedDate, says = PeekAiSays.TodayOrTomorrow)
        assertEquals(english.peekAiReason(missed, "DPD", now), english.peekAiReason(missed.copy(parcels = 14), "DPD", now))
        assertTrue(english.peekAiReason(missed.copy(parcels = 14), "DPD", now).startsWith("It was scanned in the last 24 hours."))
        // Russian reads 21 and 4,981 with its one form, 22 with few, 25 with many.
        val russian = Localizer(AppLanguage.Ru)
        fun assertMatches(pattern: String, text: String) = assertTrue(text, Regex(pattern).containsMatchIn(text))
        assertMatches("""^Peek AI учится на 21 посылке,""", russian.peekAiReason(sooner.copy(parcels = 21), "DPD", now))
        assertMatches("""^Peek AI учится на 4\p{Zs}981 посылке,""", russian.peekAiReason(sooner.copy(parcels = 4981), "DPD", now))
        assertTrue(russian.peekAiReason(notToday.copy(parcels = 22), "DPD", now).contains("Из 22 посылок,"))
        assertTrue(russian.peekAiReason(notToday.copy(parcels = 25), "DPD", now).contains("Из 25 посылок,"))
    }

    @Test
    fun countsHowOftenItWasRightRoundedDown() {
        assertEquals("Right 8 times in 10 at this hour.", english.peekAiRecord(notToday))
        assertEquals("Right 8 times in 10 on parcels like this one.", english.peekAiRecord(noDate))
        assertEquals("Right 9 times in 10 on parcels like this one.", english.peekAiRecord(noDate.copy(sure = 0.95)))
    }

    @Test
    fun writesTheReadersLanguageWithChineseSentencesJoinedWithoutASpace() {
        val french = Localizer(AppLanguage.Fr)
        assertEquals(french.text("peekAi.claim.notToday"), french.peekAiClaim(notToday, now))
        val chinese = Localizer(AppLanguage.Zh)
        val why = chinese.peekAiWhy(noDate, "UPS", now)
        assertTrue(why.startsWith(chinese.text("peekAi.why.noDate", mapOf("carrier" to "UPS"))))
        assertFalse(why.contains("。 "))
        val reason = chinese.peekAiReason(noDate, "UPS", now)
        assertTrue(reason.startsWith(chinese.text("peekAi.reason.noDate")))
        assertTrue(reason.length > chinese.text("peekAi.reason.noDate").length)
        assertFalse(reason.contains("。 "))
    }

    @Test
    fun shadesEachCellInFourStepsAgainstTheLikeliestStillToComeNoneUnder3In100OrGoneBy() {
        assertEquals(listOf(0, 1, 2, 3, 4, 4), peekAiShades(listOf(0.02, 0.06, 0.15, 0.25, 0.36, 0.4).map { it to false }))
        // The likeliest hours gone by leave the scale to the ones still to come.
        assertEquals(listOf(0, 4, 2), peekAiShades(listOf(0.5 to true, 0.2 to false, 0.1 to false)))
        assertEquals(listOf(0), peekAiShades(listOf(0.5 to true)))
    }

    @Test
    fun showsTheDaysShadedByTheirChanceTheCarriersDayRingedAndSaysThemLikeliestFirst() {
        val strip = requireNotNull(english.peekAiStrip(noDate.copy(carrierDay = "2026-11-27"), now))
        assertEquals(PeekAiStrip.Kind.Days, strip.kind)
        assertEquals(listOf("Tue 24", "Wed 25", "Thu 26", "Fri 27"), strip.cells.map { "${it.over} ${it.main}" })
        assertEquals(listOf(1 to false, 3 to false, 4 to false, 2 to true), strip.cells.map { it.level to it.carrier })
        // What its shades and ring show, in words.
        assertEquals("Days, likeliest first: Thu 26, Wed 25, Fri 27, Tue 24. DPD’s date: Fri 27", english.peekAiStripSpoken(strip, "DPD"))
        val french = Localizer(AppLanguage.Fr)
        val spoken = french.peekAiStripSpoken(requireNotNull(french.peekAiStrip(noDate.copy(carrierDay = "2026-11-27"), now)), "DPD")
        assertTrue(spoken, spoken.startsWith("${french.text("peekAi.sheet.daysByChance")}: "))
        assertTrue(spoken, spoken.endsWith(". ${french.text("peekAi.sheet.carrierDate", mapOf("carrier" to "DPD"))}: ven 27"))
    }

    @Test
    fun showsTheHoursOutForDeliveryTwoToACellThePastOnesFadedAndUnshaded() {
        val strip = requireNotNull(english.peekAiStrip(byTime, now))
        assertEquals(PeekAiStrip.Kind.Hours, strip.kind)
        assertEquals(listOf("08:00", "10:00", "12:00", "14:00", "16:00"), strip.cells.map { it.main })
        assertEquals(listOf(0 to true, 0 to true, 0 to true, 4 to false, 2 to false), strip.cells.map { it.level to it.past })
        assertEquals("Hours, likeliest first: 14:00, 16:00", english.peekAiStripSpoken(strip, "DPD"))
        // In the morning the midday hours are the likeliest.
        val morning = requireNotNull(english.peekAiStrip(byTime, at(24, 9, 30)))
        assertEquals(listOf(1, 3, 4, 2, 1), morning.cells.map { it.level })
        assertEquals("Hours, likeliest first: 12:00, 10:00, 14:00, 08:00, 16:00", english.peekAiStripSpoken(morning, "DPD"))
        assertNull(english.peekAiStrip(notToday, now))
    }

    @Test
    fun writesTheHoursOnTheReadersClock() {
        val twelveHours = Localizer(AppLanguage.En, clockPreference = ClockPreference.TwelveHour)
        val strip = requireNotNull(twelveHours.peekAiStrip(byTime, now))
        assertEquals(listOf("8:00 AM", "10:00 AM", "12:00 PM", "2:00 PM", "4:00 PM"), strip.cells.map { it.main.replace(Regex("\\s"), " ") })
        assertEquals("Hours, likeliest first: 2:00 PM, 4:00 PM", twelveHours.peekAiStripSpoken(strip, "DPD").replace(Regex("\\s"), " "))
    }

    /**
     * The hours are the parcel's wall clock: out in Tokyo at 14:30 there, the morning hours have gone by and 14:00 and
     * 16:00 are still to come, whatever this phone's zone.
     */
    @Test
    fun tellsTheHoursGoneByOnTheParcelsClock() {
        val tokyoNow = requireNotNull(DateParser.date("2026-11-24T14:30:00+09:00"))
        val tokyo = byTime.copy(outSince = "2026-11-24T08:12:00+09:00")
        val strip = requireNotNull(english.peekAiStrip(tokyo, tokyoNow))
        assertEquals(listOf(true, true, true, false, false), strip.cells.map { it.past })
        assertEquals(listOf(0, 0, 0, 4, 2), strip.cells.map { it.level })
        assertEquals("Hours, likeliest first: 14:00, 16:00", english.peekAiStripSpoken(strip, "DPD"))
        // Without the time it went out, the clock of the moment the estimate ends.
        val ending = byTime.copy(outSince = null, endsAt = "2026-11-24T16:00:00+09:00")
        assertEquals(listOf(true, true, true, false, false), requireNotNull(english.peekAiStrip(ending, tokyoNow)).cells.map { it.past })
        // A time that states no clock of its own ("Z", "-00:00") leaves the hours on this phone's.
        for (stated in listOf("2026-11-24T07:12:00Z", "2026-11-24T07:12:00-00:00")) {
            val unstated = requireNotNull(english.peekAiStrip(byTime.copy(outSince = stated), at(24, 16, 30)))
            assertEquals(stated, listOf(true, true, true, true, false), unstated.cells.map { it.past })
        }
    }

    /** A copy kept past midnight fades the days gone by and names none of them. */
    @Test
    fun fadesTheDaysGoneByOfACopyKeptPastMidnight() {
        val kept = sooner.copy(
            days = listOf("2026-11-24" to 0.4, "2026-11-25" to 0.3, "2026-11-26" to 0.2, "2026-11-27" to 0.1).map { (day, p) -> PeekAiDay(day, p) },
        )
        val strip = requireNotNull(english.peekAiStrip(kept, at(25, 9)))
        assertEquals(listOf(true, false, false, false), strip.cells.map { it.past })
        assertEquals(listOf(0, 4, 3, 2), strip.cells.map { it.level })
        assertEquals("Days, likeliest first: Wed 25, Thu 26, Fri 27. DPD’s date: Fri 27", english.peekAiStripSpoken(strip, "DPD"))
    }

    private fun parcel(stage: TrackingStage, ai: PeekAi?, archivedAt: String? = null, expectedDelivery: String? = null): Parcel {
        val id = UUID.randomUUID()
        return Parcel(
            id = id, trackingNumber = "TESTPEEKAI0001", label = "Synthetic parcel", carrier = CarrierId.Ups, createdAt = "2026-11-20T08:00:00Z",
            expectedDelivery = expectedDelivery, syncStatus = SyncStatus.Ok, notificationsMuted = false, archivedAt = archivedAt, peekAi = ai,
            trackingEvents = listOf(
                TrackingEvent(id = UUID.randomUUID(), packageId = id, stage = stage, description = "", occurredAt = DateParser.isoString(now.minusSeconds(3_600))),
            ),
        )
    }

    /**
     * A call, a reason or a step the server added after this release drops Peek AI alone: the parcel, and the list it
     * came in, still read, as do a link's parcel and the parcels the phone keeps.
     */
    @Test
    fun aCallTheAppCantReadDropsOnlyPeekAi() {
        val known = """{"says":"by_day","why":"no_date","sure":0.81,"day":"2026-11-26","ends_at":"2026-11-27T00:00:00+01:00"}"""
        val unread = listOf(
            """{"says":"by_moonrise","why":"no_date","sure":0.81}""",
            """{"says":"by_day","why":"tea_leaves","sure":0.81,"day":"2026-11-26"}""",
            """{"says":"by_day","why":"no_date","sure":0.81,"day":"2026-11-26","last":{"kind":"drone","at":"2026-11-24T08:00:00+01:00"}}""",
            """{"says":"by_day"}""",
            """"by_day"""",
        )
        val row = ApiJson.encodeToJsonElement(Parcel.serializer(), parcel(TrackingStage.InTransit, null)).jsonObject
        fun with(ai: String) = JsonObject(row + ("peek_ai" to ApiJson.parseToJsonElement(ai)))
        val list = JsonArray(listOf(with(known)) + unread.map(::with) + JsonObject(row + ("peek_ai" to JsonNull)))
        val parcels = ApiJson.decodeFromJsonElement(ListSerializer(Parcel.serializer()), list)
        assertEquals(unread.size + 2, parcels.size)
        assertEquals("2026-11-27T00:00:00+01:00", parcels.first().peekAi?.endsAt)
        assertTrue(parcels.drop(1).all { it.peekAi == null && it.label == "Synthetic parcel" })

        // The parcel a link shows reads the same way.
        val link = JsonObject(with(unread.first()) + ("carrier_data" to JsonObject(emptyMap())))
        assertNull(ApiJson.decodeFromJsonElement(PublicPackage.serializer(), link).peekAi)

        // A parcel the phone kept, written as the iPhone writes it and read again.
        val kept = parcels.first()
        val bytes = JsonEncoder(sortedKeys = true, swiftKeys = true).encode(ListSerializer(Parcel.serializer()), listOf(kept, parcels[1]))
        assertEquals(listOf(kept.peekAi, null), decodeSwiftKeys(ListSerializer(Parcel.serializer()), bytes).map { it.peekAi })
    }

    @Test
    fun aParcelNoLongerOnItsWaySaysNone() {
        assertSame(sooner, parcelPeekAi(parcel(TrackingStage.InTransit, sooner), now))
        for (stage in listOf(TrackingStage.Delivered, TrackingStage.Returned, TrackingStage.ReadyForPickup, TrackingStage.FailedAttempt)) {
            assertNull(stage.rawValue, parcelPeekAi(parcel(stage, sooner), now))
        }
        assertNull(parcelPeekAi(parcel(TrackingStage.InTransit, sooner, archivedAt = "2026-11-24T08:00:00Z"), now))
    }

    /**
     * A list left open draws a card again where its words can change: at the hour named, and at each midnight until the
     * day's estimate ends.
     */
    @Test
    fun aCardIsDrawnAgainWhereItsWordsCanChange() {
        assertEquals("None while it says nothing", emptyList<Instant>(), peekAiChanges(parcel(TrackingStage.OutForDelivery, null), now))
        assertEquals(listOf(at(24, 16)), peekAiChanges(parcel(TrackingStage.OutForDelivery, byTime), now))
        assertEquals(listOf(at(25, 0), at(26, 0), at(27, 0)), peekAiChanges(parcel(TrackingStage.InTransit, noDate), now))
        val week = noDate.copy(says = PeekAiSays.ThisWeek, day = null)
        assertEquals((25..30).map { at(it, 0) } + LocalDateTime.of(2026, 12, 1, 0, 0).atZone(zone).toInstant(), peekAiChanges(parcel(TrackingStage.InTransit, week), now))
        assertEquals("None once it has gone", emptyList<Instant>(), peekAiChanges(parcel(TrackingStage.OutForDelivery, byTime), at(24, 16)))
        assertEquals("None once delivered", emptyList<Instant>(), peekAiChanges(parcel(TrackingStage.Delivered, noDate), now))
    }

    @Test
    fun aCardSaysUnlikelyBesideTheCarriersTodayAndNothingWhenSwitchedOff() {
        assertEquals("unlikely", english.parcelPeekAiWords(parcel(TrackingStage.InTransit, notToday, expectedDelivery = "2026-11-24"), show = true, now))
        assertEquals("not today", english.parcelPeekAiWords(parcel(TrackingStage.InTransit, notToday), show = true, now))
        assertNull(english.parcelPeekAiWords(parcel(TrackingStage.InTransit, notToday), show = false, now))
    }

    @Test
    fun theDemosChocolateComesBy16AndTheBirthdayGiftByTheThirdDeliveryDay() {
        val samples = DemoRepository.seed(now)
        val chocolate = samples.single { it.trackingNumber == "DEMOCHOC20260001" }
        val byFour = requireNotNull(DemoRepository.peekAi(chocolate, now))
        assertEquals(PeekAiSays.ByTime, byFour.says)
        assertEquals("by 16:00", english.parcelPeekAiWords(chocolate.copy(peekAi = byFour), show = true, now))
        assertNull("Not after 16:00", DemoRepository.peekAi(chocolate, at(24, 16, 5)))

        val gift = samples.single { it.trackingNumber == "LX123456789DE" }
        val byDay = requireNotNull(DemoRepository.peekAi(gift, now))
        assertEquals(PeekAiSays.ByDay, byDay.says)
        assertEquals("2026-11-26", byDay.day)
        assertEquals(PeekAiStepKind.Customs, byDay.last?.kind)
        assertEquals("Chronopost gives no date. It reached customs 12 hours ago.", english.peekAiWhy(byDay, "Chronopost", now))
        // No courier comes on Sundays.
        assertEquals(listOf("2026-11-28", "2026-11-30", "2026-12-01", "2026-12-02", "2026-12-03"), DemoRepository.peekAi(gift, at(28, 9))?.days?.map { it.day })
        assertNull(DemoRepository.peekAi(samples.first { it.trackingNumber !in setOf("DEMOCHOC20260001", "LX123456789DE") }, now))
    }

    @Test
    fun theWidgetsShowTheCardsWordsUntilTheCallGoes() {
        val parcels = listOf(parcel(TrackingStage.OutForDelivery, byTime.copy(outSince = "2026-11-24T14:00:00+01:00")))
        val shown = ParcelStore.widgetOrder(parcels, english, now).single()
        assertEquals("by 16:00", shown.peekAi)
        assertEquals(at(24, 16), shown.peekAiUntil)
        assertEquals(shown.peekAi, shown.worded(at(24, 15, 59)).peekAi)
        assertNull("Gone at 16:00, as from the card", shown.worded(at(24, 16)).peekAi)
        assertTrue(at(24, 16) in DeliveryWidgetDays.changes(listOf(shown), after = now))
        assertNull(ParcelStore.widgetOrder(parcels, english, now, showPeekAi = false).single().peekAi)
    }
}
