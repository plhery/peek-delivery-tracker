package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.ImportCharges
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/** ParcelLogicTests.swift: how the localizer writes a parcel's dates, estimates and charges. */
class ParcelLogicFormattingTests {
    @Test
    fun estimatesKeepTodayAndWindowsWhileOutForDeliveryAndHideObsoleteDates() {
        val id = UUID.randomUUID()
        val now = DateParser.date("2026-09-07T12:00:00Z")!!
        val localizer = Localizer(AppLanguage.Fr)
        for (stage in listOf(TrackingStage.Delivered, TrackingStage.Returned, TrackingStage.FailedAttempt, TrackingStage.ReadyForPickup, TrackingStage.Exception)) {
            val parcel = makeParcel(id, events = listOf(event(id, stage, "2026-09-07T10:00:00Z"))).copy(expectedDelivery = "2026-09-07")
            assertNull(localizer.parcelDeliveryEstimate(parcel, now))
        }
        var parcel = makeParcel(id, events = listOf(event(id, TrackingStage.OutForDelivery, "2026-09-07T10:00:00Z"))).copy(expectedDelivery = "2026-09-07")
        assertEquals("aujourd’hui", localizer.parcelDeliveryEstimate(parcel, now))
        parcel = parcel.copy(expectedDelivery = "2026-09-07 14:00–16:00")
        assertEquals("aujourd’hui, 14:00–16:00", localizer.parcelDeliveryEstimate(parcel, now))
        parcel = parcel.copy(expectedDelivery = "2026-09-07T12:30:00Z")
        val formatter = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault())
        val time = formatter.format(DateParser.date(parcel.expectedDelivery!!)!!)
        assertEquals("aujourd’hui, $time", localizer.parcelDeliveryEstimate(parcel, now))
        parcel = parcel.copy(expectedDelivery = "2026-09-06")
        assertNull(localizer.parcelDeliveryEstimate(parcel, now))
    }

    @Test
    fun completionDatesUseRelativeLabelsForFinalEvents() {
        val localizer = Localizer(AppLanguage.En)
        val now = DateParser.deliveryDate("2026-09-09")!!
        val id = UUID.randomUUID()
        for (stage in listOf(TrackingStage.Delivered, TrackingStage.Returned)) {
            for ((value, expected) in listOf("2026-09-08" to "yesterday", "2026-09-09" to "today", "2026-09-10" to "tomorrow", "2026-09-12" to "Sat 12 Sep")) {
                val day = DateParser.deliveryDate(value)!!
                val late = day.atZone(ZoneId.systemDefault()).withHour(23).withMinute(55).withSecond(0).toInstant()
                val parcel = makeParcel(id, events = listOf(event(id, stage, DateParser.isoString(late))))
                assertEquals(expected, localizer.parcelCompletionDate(parcel, now))
            }
        }
        assertNull(localizer.parcelCompletionDate(makeParcel(id, events = listOf(event(id, TrackingStage.InTransit, DateParser.isoString(now)))), now))
        assertNull(localizer.parcelCompletionDate(makeParcel(id, events = listOf(event(id, TrackingStage.Delivered, "invalid"))), now))
    }

    @Test
    fun aLinksCardSaysWhenAParcelArrivesAndAGiftNoMoreThanThat() {
        val localizer = Localizer(AppLanguage.En)
        val id = UUID.randomUUID()
        val now = Instant.now()
        // LocalDate.ofInstant is API 34; the device tests run from API 31.
        val today = now.atZone(ZoneId.systemDefault()).toLocalDate().toString()

        // On its way: the estimate is a line of its own, and a gift only says when it arrives.
        var moving = makeParcel(id, events = listOf(event(id, TrackingStage.OutForDelivery, DateParser.isoString(now))))
        assertNull(localizer.sharedParcelDetail(moving))
        assertNull(localizer.giftDetail(moving, opened = false))
        moving = moving.copy(expectedDelivery = today)
        assertEquals("Today", localizer.sharedParcelDetail(moving))
        assertEquals("Arrives today", localizer.giftDetail(moving, opened = false))

        // Arrived: the day on the usual card, the day and the time for a gift that is opened.
        val arrived = makeParcel(id, events = listOf(event(id, TrackingStage.Delivered, DateParser.isoString(now))))
        assertEquals("Today", localizer.sharedParcelDetail(arrived))
        assertEquals("Delivered today at ${localizer.clockTime(now)}", localizer.giftDetail(arrived, opened = true))
        assertNull(localizer.giftDetail(moving, opened = true))
    }

    @Test
    fun importChargesReadTheirAmountTheLowerOnlinePriceWhileItCanBePaidAndWhetherTheyArePaid() {
        val localizer = Localizer(AppLanguage.En)
        val id = UUID.randomUUID()
        fun line(charges: ImportCharges?, stage: TrackingStage = TrackingStage.InTransit): ImportChargesLine? {
            val parcel = makeParcel(id, events = listOf(event(id, stage, "2026-10-20T10:00:00Z")))
                .copy(carrierData = CarrierData(importCharges = charges))
            return localizer.importCharges(parcel)
        }
        val listed = ImportCharges(currency = "EUR", customs = 23.41, carrierFee = 11.0, carrierFeeOnline = 8.09)
        // Once the online window closes, the carrier lists the price on delivery alone.
        val closed = listed.copy(carrierFeeOnline = null, statusCode = "FINESTRA_PAGAMENTO_CHIUSA")
        val paid = listed.copy(paid = true, statusCode = "PAGATO")

        assertEquals(ImportChargesLine("€34.41 (€31.50 if paid online)", payableOnline = true), line(listed))
        assertEquals(ImportChargesLine("€34.41", payableOnline = false), line(closed))
        // Delivered unpaid: settled at the door or not at all.
        assertEquals(ImportChargesLine("€34.41", payableOnline = false), line(listed, TrackingStage.Delivered))
        assertEquals(ImportChargesLine("Paid (€34.41 on delivery, €31.50 online)", payableOnline = false), line(paid))
        assertEquals("Paid (€34.41 on delivery, €31.50 online)", line(paid, TrackingStage.Delivered)?.text)
        assertEquals("Paid (€23.41)", line(ImportCharges(currency = "EUR", customs = 23.41, paid = true))?.text)
        assertEquals("Paid", line(ImportCharges(currency = "EUR", paid = true))?.text)
        assertNull(line(ImportCharges(currency = "EUR", statusCode = "IN_ATTESA")))
        assertNull(line(null))

        localizer.language = AppLanguage.It
        assertEquals("34,41\u00A0€ (31,50\u00A0€ pagando online)", line(listed)?.text)
    }

    @Test
    fun aScanDatedByItsDayAloneShowsThatDayAndNoClockTime() {
        val localizer = Localizer(AppLanguage.En)
        val id = UUID.randomUUID()
        val today = DateParser.deliveryDate("2026-09-21")!!
        val now = today.plusSeconds(15 * 3_600L)
        // The instant only orders the scan: the start of the day in a zone two hours ahead of UTC,
        // which falls on the day before for a reader west of it.
        fun dayScan(day: String, occurredAt: String): TrackingEvent = event(id, TrackingStage.Delivered, occurredAt).copy(day = day)
        val scan = dayScan("2026-09-21", "2026-09-20T22:00:00Z")
        assertEquals(today, scan.calendarDay)
        assertEquals("today", localizer.relativeTime(of = scan, now = now))
        val arrived = makeParcel(id, events = listOf(scan))
        assertEquals("today", localizer.parcelCompletionDate(arrived, now))
        assertEquals("21.09", arrived.stampDeliveryDate)
        // A gift's card then gives the day as the card's own line does.
        assertEquals("Today", localizer.giftDetail(arrived, opened = true, now = now))
        val earlier = makeParcel(id, events = listOf(dayScan("2026-09-12", "2026-09-11T22:00:00Z")))
        assertEquals("Sat 12 Sep", localizer.giftDetail(earlier, opened = true, now = now))

        // A scan with a clock time keeps it.
        val timed = event(id, TrackingStage.InTransit, DateParser.isoString(now.minusSeconds(2 * 3_600L)))
        assertNull(timed.calendarDay)
        assertEquals("2 h ago", localizer.relativeTime(of = timed, now = now))
    }

    @Test
    fun dayKeysAndEstimatesAreStableAcrossRepeatedFormatting() {
        val localizer = Localizer(AppLanguage.En)
        val day = DateParser.deliveryDate("2026-09-12")!!
        assertEquals("2026-09-12", ParcelOrganizer.dayKey(day))
        assertNull(DateParser.date("2026-09-12"))
        assertEquals(day, DateParser.deliveryDate("2026-09-12"))
        repeat(3) {
            assertEquals("Sat 12 Sep", localizer.shortDate(day))
            assertEquals("Sat 12 Sep, 00:00", localizer.dateTime(DateParser.isoString(day)))
        }
    }
}
