package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.ImportCharges
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * What the parcel's page reads of a parcel (ParcelDetailView.swift): its pickup card, the carrier's words, the
 * shipment's facts, its journal's days and rows, and the name its rename alert saves.
 */
class ParcelDetailViewTests {
    private val id = UUID.randomUUID()

    private fun parcel(stage: TrackingStage, data: CarrierData?): Parcel =
        makeParcel(id, events = listOf(event(id, stage, "2026-09-07T10:00:00Z"))).copy(carrierData = data)

    @Test
    fun aParcelWaitsAtItsPickupPointOnlyWhileItIsReadyForPickupAndThePointHasAName() {
        val point = CarrierData(pickupPoint = "Example Kiosk\nSample Street 1\n0000 Testtown")
        assertEquals(PickupPoint("Example Kiosk", "Sample Street 1, 0000 Testtown"), parcel(TrackingStage.ReadyForPickup, point).waitingAt)
        assertNull(parcel(TrackingStage.InTransit, point).waitingAt)
        assertNull(parcel(TrackingStage.Delivered, point).waitingAt)
        assertNull(parcel(TrackingStage.ReadyForPickup, CarrierData(pickupPoint = " \n ")).waitingAt)
        assertNull(parcel(TrackingStage.ReadyForPickup, null).waitingAt)
    }

    @Test
    fun aNameIsSavedTrimmedAndCutTo80CharactersAsTheyAreSeen() {
        assertEquals("Desk lamp", parcelName("  Desk lamp \n"))
        assertEquals("", parcelName(" \n\t "))
        assertEquals("a".repeat(80), parcelName("a".repeat(81)))
        // A family emoji is one character of several scalars, kept whole.
        val family = "\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67"
        assertEquals("a".repeat(79) + family, parcelName("a".repeat(79) + family + "b"))
        // Trimmed before it is cut: the spaces in front don't count.
        assertEquals("b".repeat(80), parcelName("   " + "b".repeat(80) + "c"))
    }

    @Test
    fun theCarriersWordsAreQuotedTrimmedAndNotAtAllWhenBlank() {
        val scan = event(id, TrackingStage.FailedAttempt, "2026-09-07T10:00:00Z")
        assertEquals("Nobody was home.", scan.copy(description = "  Nobody was home.\n").quotedWords)
        assertNull(scan.copy(description = " \n ").quotedWords)
        assertNull(scan.copy(description = "").quotedWords)
    }

    @Test
    fun weightsAreWrittenInTheAppLanguageWithUpToThreeDecimals() {
        assertEquals("1.2", foundationDecimal(1.2, 3, AppLanguage.En))
        assertEquals("2", foundationDecimal(2.0, 3, AppLanguage.En))
        assertEquals("0.346", foundationDecimal(0.3456, 3, AppLanguage.En))
        assertEquals("0.02", foundationDecimal(0.015, 2, AppLanguage.En))
        assertEquals("1,234.5", foundationDecimal(1234.5, 3, AppLanguage.En))
        assertEquals("1,2", foundationDecimal(1.2, 3, AppLanguage.De))
        assertEquals("1.234,5", foundationDecimal(1234.5, 3, AppLanguage.De))
        assertEquals("1 234,5", foundationDecimal(1234.5, 3, AppLanguage.Fr))
        assertEquals("1234,5", foundationDecimal(1234.5, 3, AppLanguage.It))
        assertEquals("12.345,5", foundationDecimal(12345.5, 3, AppLanguage.Es))
        assertEquals("1234,5", foundationDecimal(1234.5, 3, AppLanguage.Pl))
        assertEquals("-0,5", foundationDecimal(-0.5, 3, AppLanguage.Pt))
    }

    @Test
    fun theFactsComeInThePagesOrderTrimmedAndOnlyThoseTheCarrierGave() {
        val localizer = Localizer(AppLanguage.En)
        val charges = ImportCharges(currency = "EUR", customs = 23.41)
        val data = CarrierData(
            pickupPoint = " Example Kiosk\nSample Street 1 ", receiverName = "  A. Reader ", weightKg = 1.25,
            dimensionsText = " 30 × 20 × 10 cm\n", importCharges = charges,
        )
        val inTransit = parcel(TrackingStage.InTransit, data)
        assertEquals(
            listOf(
                ShipmentFact("detail.importCharges", localizer.importCharges(inTransit)!!.text),
                ShipmentFact("detail.pickupPoint", "Example Kiosk\nSample Street 1"),
                ShipmentFact("detail.recipient", "A. Reader"),
                ShipmentFact("detail.weight", "1.25 kg"),
                ShipmentFact("detail.dimensions", "30 × 20 × 10 cm"),
            ),
            localizer.shipmentFacts(inTransit),
        )
        // Once delivered, where it was picked up; while it waits there, its card says so instead.
        assertEquals(ShipmentFact("detail.collectedAt", "Example Kiosk\nSample Street 1"), localizer.shipmentFacts(parcel(TrackingStage.Delivered, data))!![1])
        assertEquals(listOf("detail.importCharges", "detail.recipient", "detail.weight", "detail.dimensions"),
            localizer.shipmentFacts(parcel(TrackingStage.ReadyForPickup, data))!!.map { it.key })
    }

    @Test
    fun aWeightThatIsNoneAndBlankValuesAreLeftOutAndNoCarrierDataListsNothing() {
        val localizer = Localizer(AppLanguage.De)
        for (weight in listOf(0.0, -1.0, Double.NaN, Double.POSITIVE_INFINITY)) {
            assertEquals(emptyList<ShipmentFact>(), localizer.shipmentFacts(parcel(TrackingStage.InTransit, CarrierData(weightKg = weight))))
        }
        assertEquals(
            listOf(ShipmentFact("detail.weight", "0,9 kg")),
            localizer.shipmentFacts(parcel(TrackingStage.InTransit, CarrierData(receiverName = " ", dimensionsText = "\n", pickupPoint = "", weightKg = 0.9))),
        )
        assertEquals(emptyList<ShipmentFact>(), localizer.shipmentFacts(parcel(TrackingStage.InTransit, CarrierData())))
        assertNull(localizer.shipmentFacts(parcel(TrackingStage.InTransit, null)))
    }

    // The journal, in whatever zone the tests run in.

    private val zone: ZoneId get() = ZoneId.systemDefault()

    /** Thursday 29 October 2026 at noon in the phone's zone. */
    private val now = LocalDate.of(2026, 10, 29).atTime(12, 0).atZone(ZoneId.systemDefault()).toInstant()

    /** `day` at `hour`:`minute` in the phone's zone, as the API writes it. */
    private fun at(day: LocalDate, hour: Int, minute: Int = 0): String = DateParser.isoString(day.atTime(hour, minute).atZone(zone).toInstant())

    private fun scan(occurredAt: String, description: String = "Update", day: String? = null, tieOrder: Int? = null): TrackingEvent =
        event(id, TrackingStage.InTransit, occurredAt).copy(description = description, day = day, tieOrder = tieOrder)

    private fun days(vararg events: TrackingEvent): List<JournalDay> =
        Localizer(AppLanguage.En).journalDays(makeParcel(id, events = events.toList()).journalEntries, now)

    @Test
    fun theJournalsDaysAreTodayYesterdayOrTheirShortDateWithTheYearWhenItIsNotThisOne() {
        val today = LocalDate.of(2026, 10, 29)
        val days = days(
            scan(at(today, 9)), scan(at(today, 0, 5)), scan(at(today.minusDays(1), 23, 55)),
            scan(at(LocalDate.of(2026, 10, 20), 10)), scan(at(LocalDate.of(2025, 12, 31), 23, 30)),
        )
        assertEquals(listOf("today", "yesterday", "Tue 20 Oct", "Wed 31 Dec 2025"), days.map { it.label })
        assertEquals(listOf(2, 1, 1, 1), days.map { it.entries.size })
        assertEquals(today.atStartOfDay(zone).toInstant(), days[0].start)
        assertEquals("Mi 31. Dez 2025", Localizer(AppLanguage.De).journalDays(days[3].entries, now).single().label)
    }

    /** The day the clocks go back in Zurich lasts 25 hours there: its first and last scans still share it. */
    @Test
    fun aDayAsLongAsTheClocksMakeItKeepsItsScans() {
        val changed = LocalDate.of(2026, 10, 25)
        val days = days(scan(at(changed.plusDays(1), 0, 30)), scan(at(changed, 23, 30)), scan(at(changed, 2, 30)), scan(at(changed, 0, 30)))
        assertEquals(listOf("Mon 26 Oct", "Sun 25 Oct"), days.map { it.label })
        assertEquals(listOf(1, 3), days.map { it.entries.size })
        assertEquals(changed.atStartOfDay(zone).toInstant(), days[1].start)
    }

    @Test
    fun aScanDatedByItsDayAloneSitsUnderThatDayEvenWhenItSortsAmongTheDayBefores() {
        val evening = scan(at(LocalDate.of(2026, 10, 26), 20))
        // West of the carrier's zone, the start of its day there is the evening before here.
        val dayOnly = scan(at(LocalDate.of(2026, 10, 26), 18), day = "2026-10-27")
        val days = days(evening, dayOnly)
        assertEquals(listOf("Tue 27 Oct", "Mon 26 Oct"), days.map { it.label })
        assertEquals(listOf(listOf(dayOnly.id), listOf(evening.id)), days.map { day -> day.entries.map { it.id } })
    }

    @Test
    fun scansWithoutAReadableDateGroupUnderWhatTheCarrierWroteAndStayLastInTheRowsOrder() {
        val first = scan("soon", tieOrder = 2)
        val second = scan("not yet", tieOrder = 1)
        val again = scan("soon", tieOrder = 0)
        val dated = scan(at(LocalDate.of(2026, 10, 20), 10))
        val days = days(first, second, again, dated)
        assertEquals(listOf("Tue 20 Oct", "soon", "not yet"), days.map { it.label })
        assertEquals(listOf(first.id, again.id), days[1].entries.map { it.id })
        assertNull(days[1].start)
    }

    @Test
    fun aRowsTimeIsTheClockTimeNoneForADayAloneAndADashForADateThatDoesNotRead() {
        val localizer = Localizer(AppLanguage.En)
        assertEquals("09:05", localizer.journalTime(scan(at(LocalDate.of(2026, 10, 20), 9, 5))))
        assertEquals("", localizer.journalTime(scan(at(LocalDate.of(2026, 10, 20), 0), day = "2026-10-20")))
        assertEquals("—", localizer.journalTime(scan("soon")))
    }

    @Test
    fun aRowSaysTheCarriersWordsElseItsStagesAndItsCopiesWordsOnceEachWhenTheyDiffer() {
        val localizer = Localizer(AppLanguage.En)
        val scan = scan(at(LocalDate.of(2026, 10, 20), 9), description = "Sorted at Zu\u0308rich")
        assertEquals(localizer.text("stage.in_transit"), localizer.journalWording(scan.copy(description = "")))
        val copies = listOf("Sorted at Z\u00fcrich", "Left the hub", "", "Left the hub", "Sorted at Zu\u0308rich").map { scan.copy(id = UUID.randomUUID(), description = it) }
        assertEquals(listOf("Left the hub", localizer.text("stage.in_transit")), localizer.relayedWordings(JournalEntry(scan, copies)))
        assertEquals(emptyList<String>(), localizer.relayedWordings(JournalEntry(scan, emptyList())))
    }

    @Test
    fun aScansLocationIsTrimmedAndNoneWhenBlank() {
        val scan = scan(at(LocalDate.of(2026, 10, 20), 9))
        assertEquals("Härkingen", scan.copy(location = " Härkingen\n").journalLocation)
        assertNull(scan.copy(location = " \n ").journalLocation)
        assertNull(scan.copy(location = null).journalLocation)
    }
}
