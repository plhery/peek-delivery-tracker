package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.ImportCharges
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/** What the parcel's page reads of a parcel (ParcelDetailView.swift): its pickup card, the carrier's words and the shipment's facts. */
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
}
