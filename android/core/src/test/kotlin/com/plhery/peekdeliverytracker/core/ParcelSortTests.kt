package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * The list's orders against the web's (src/lib/parcelView.ts `parcelComparator`, src/lib/parcelView.test.ts), with the
 * web test's parcels: Delivery date, the list's own order, takes the soonest expected day first and parcels without one
 * last, then the one that moved last, then the name. Priority, which ordered the same way, is gone.
 */
class ParcelSortTests {
    @Test
    fun theOrdersAreTheWebsWithoutPriority() {
        assertEquals(listOf("updated", "newest", "eta", "carrier"), ParcelSort.entries.map { it.rawValue })
    }

    /** The web's "combines search, status, carrier, and stable sort choices". */
    @Test
    fun searchStatusCarrierAndOrderCombineAsOnTheWeb() {
        val swiss = parcel("long-name", TrackingStage.InTransit, CarrierId.SwissPost).copy(expectedDelivery = "2026-08-08")
        val ups = parcel("ups", TrackingStage.InTransit, CarrierId.Ups).copy(expectedDelivery = "2026-08-06")
        val delivered = parcel("past", TrackingStage.Delivered, CarrierId.SwissPost)
        val all = listOf(swiss, ups, delivered)

        assertEquals(listOf("ups", "long-name"), names(ParcelOrganizer.visible(all, "", ParcelStatusFilter.Active, null, ParcelSort.Eta, NOW)))
        assertEquals(
            listOf("long-name", "past"),
            names(ParcelOrganizer.visible(all, "coffee", ParcelStatusFilter.All, CarrierId.SwissPost, ParcelSort.Newest, NOW)),
        )
    }

    @Test
    fun ofTwoDueTheSameDayTheOneThatMovedLastComesFirst() {
        // "alpha" comes first by name, "zulu" moved last; "date" moved last of all but has no expected day.
        val alpha = parcel("alpha", TrackingStage.InTransit, moved = "2026-08-02T09:00:00Z").copy(expectedDelivery = "2026-08-08")
        val zulu = parcel("zulu", TrackingStage.InTransit, moved = "2026-08-04T09:00:00Z").copy(expectedDelivery = "2026-08-08 13:00–15:00")
        val soon = parcel("soon", TrackingStage.InTransit, moved = "2026-08-01T09:00:00Z").copy(expectedDelivery = "2026-08-06")
        val undated = parcel("date", TrackingStage.InTransit, moved = "2026-08-05T09:00:00Z")

        assertEquals(
            listOf("soon", "zulu", "alpha", "date"),
            names(ParcelOrganizer.visible(listOf(undated, alpha, zulu, soon), "", ParcelStatusFilter.All, null, ParcelSort.Eta, NOW)),
        )
    }

    @Test
    fun aParcelWithoutAScanMovedWhenItWasAdded() {
        // The web's `currentEvent(...)?.occurredAt ?? createdAt`.
        val scanned = parcel("scanned", TrackingStage.InTransit, moved = "2026-08-03T09:00:00Z").copy(expectedDelivery = "2026-08-08")
        val unscanned = parcel("unscanned", TrackingStage.InTransit)
            .copy(createdAt = "2026-08-04T09:00:00Z", trackingEvents = emptyList(), expectedDelivery = "2026-08-08")

        assertEquals(listOf("unscanned", "scanned"), names(ParcelOrganizer.sorted(listOf(scanned, unscanned), ParcelSort.Eta)))
    }

    @Test
    fun theSameDayAndTheSameMoveFallBackToTheName() {
        val bravo = parcel("bravo", TrackingStage.InTransit, moved = "2026-08-03T09:00:00Z").copy(expectedDelivery = "2026-08-08")
        val alpha = parcel("alpha", TrackingStage.InTransit, moved = "2026-08-03T09:00:00Z").copy(expectedDelivery = "2026-08-08")

        assertEquals(listOf("alpha", "bravo"), names(ParcelOrganizer.sorted(listOf(bravo, alpha), ParcelSort.Eta)))
    }

    /** The web test's `parcel(id, stage, carrier)`: named "<id> coffee", added and scanned on days from the id's length. */
    private fun parcel(name: String, stage: TrackingStage, carrier: CarrierId = CarrierId.SwissPost, moved: String? = null): Parcel {
        val id = UUID.randomUUID()
        val scan = TrackingEvent(
            id = UUID.randomUUID(), packageId = id, stage = stage, description = "${stage.rawValue} at the parcel center",
            location = "Härkingen", occurredAt = moved ?: "2026-08-0${minOf(name.length + 1, 9)}T08:00:00Z",
        )
        return makeParcel(id, trackingNumber = "9934.${name}56-12345678", events = listOf(scan))
            .copy(label = "$name coffee", carrier = carrier, createdAt = "2026-08-0${minOf(name.length, 9)}T08:00:00Z")
    }

    private fun names(parcels: List<Parcel>): List<String> = parcels.map { it.label.removeSuffix(" coffee") }

    private companion object {
        /** The web test's NOW, 5 August 2026 at noon. */
        val NOW: Instant = Instant.parse("2026-08-05T12:00:00Z")
    }
}
