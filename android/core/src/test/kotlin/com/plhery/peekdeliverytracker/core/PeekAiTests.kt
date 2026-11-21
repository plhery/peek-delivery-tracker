package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PeekAi
import com.plhery.peekdeliverytracker.core.api.PeekAiDay
import com.plhery.peekdeliverytracker.core.api.PeekAiHour
import com.plhery.peekdeliverytracker.core.api.PeekAiSays
import com.plhery.peekdeliverytracker.core.api.PeekAiStep
import com.plhery.peekdeliverytracker.core.api.PeekAiStepKind
import com.plhery.peekdeliverytracker.core.api.PeekAiWhy
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone
import java.util.UUID
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
    fun holdsADayUntilItHasPassedAndSoonerUntilTheCarriersOwnDay() {
        val noDate = noDate
        assertSame(noDate, currentPeekAi(noDate, at(26, 23, 59)))
        assertNull(currentPeekAi(noDate, at(27, 0, 1)))
        assertSame(sooner, currentPeekAi(sooner, at(27, 12)))
        assertNull(currentPeekAi(sooner, at(28, 8)))
        assertNull(currentPeekAi(null, now))
    }

    @Test
    fun aWidgetLetsTheCallGoWhenTheAppWould() {
        assertEquals(at(25, 0), peekAiUntil(notToday))
        assertEquals(at(24, 16), peekAiUntil(byTime))
        assertEquals(at(27, 0), peekAiUntil(noDate))
        assertEquals(at(28, 0), peekAiUntil(sooner))
        assertNull(peekAiUntil(sooner.copy(carrierDay = null)))
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
        assertEquals("likely by the end of next week", english.peekAiClaim(noDate.copy(says = PeekAiSays.ByEndOfNextWeek), now))
    }

    @Test
    fun namesTheCarriersWordUnderItsHeadingAndSaysWhy() {
        assertEquals("DPD says today", english.peekAiCarrierWord(notToday, "DPD", "Today"))
        assertEquals("DPD says Fri 27 Nov", english.peekAiCarrierWord(sooner, "DPD", "Fri 27 Nov"))
        assertEquals("UPS gives no date", english.peekAiCarrierWord(noDate, "UPS", null))
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
    }

    @Test
    fun showsTheDaysWithTheirChanceTheCarriersDayRinged() {
        val strip = requireNotNull(english.peekAiStrip(noDate.copy(carrierDay = "2026-11-27"), now))
        assertEquals(PeekAiStrip.Kind.Days, strip.kind)
        assertEquals(listOf("Tue 24", "Wed 25", "Thu 26", "Fri 27"), strip.cells.map { "${it.over} ${it.main}" })
        assertEquals(
            listOf(listOf(false, false, false), listOf(true, false, false), listOf(true, false, false), listOf(false, true, true)),
            strip.cells.map { listOf(it.likely, it.maybe, it.carrier) },
        )
    }

    @Test
    fun showsTheHoursOutForDeliveryTwoToACellThePastOnesFaded() {
        val strip = requireNotNull(english.peekAiStrip(byTime, now))
        assertEquals(PeekAiStrip.Kind.Hours, strip.kind)
        assertEquals(listOf("08:00", "10:00", "12:00", "14:00", "16:00"), strip.cells.map { it.main })
        assertEquals(
            listOf(listOf(false, true, true), listOf(true, false, true), listOf(true, false, true), listOf(true, false, false), listOf(false, true, false)),
            strip.cells.map { listOf(it.likely, it.maybe, it.past) },
        )
        assertNull(english.peekAiStrip(notToday, now))
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

    @Test
    fun aParcelNoLongerOnItsWaySaysNone() {
        assertSame(sooner, parcelPeekAi(parcel(TrackingStage.InTransit, sooner), now))
        for (stage in listOf(TrackingStage.Delivered, TrackingStage.Returned, TrackingStage.ReadyForPickup, TrackingStage.FailedAttempt)) {
            assertNull(stage.rawValue, parcelPeekAi(parcel(stage, sooner), now))
        }
        assertNull(parcelPeekAi(parcel(TrackingStage.InTransit, sooner, archivedAt = "2026-11-24T08:00:00Z"), now))
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
