package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/** The route a parcel's cards draw (ParcelMapView.swift's `ParcelRoute(parcel:atlas:language:)`). */
class ParcelMapViewTests {
    private val atlas: WorldAtlas by lazy { WorldAtlas(SharedResources.open("World.json").use { it.readBytes() }) }

    private fun parcel(destination: String?) = ParcelLogicTestFixtures.makeParcel().let { parcel ->
        val scan = ParcelLogicTestFixtures.event(parcel.id, TrackingStage.InTransit, "2026-08-02T10:00:00Z").copy(
            place = EventPlace(latitude = 51.34, longitude = 12.37, precision = EventPlacePrecision.City, country = "DE", name = "Leipzig"),
        )
        parcel.copy(trackingEvents = listOf(scan), carrierData = destination?.let { CarrierData(destinationCountry = it) })
    }

    @Test
    fun routeEndsInTheCountryTheCarrierNames() {
        val route = ParcelRoute(parcel("CH"), atlas, AppLanguage.De)
        assertEquals(listOf("Leipzig"), route.stops.map { it.place.name })
        val destination = route.destination!!
        assertEquals("Schweiz", destination.name)
        assertEquals("CH", destination.country)
        assertEquals(atlas.label("CH"), destination.point)
    }

    @Test
    fun routeWithoutADestinationOrAnUnknownCountryEndsAtItsLastScan() {
        assertNull(ParcelRoute(parcel(null), atlas, AppLanguage.En).destination)
        assertNull(ParcelRoute(parcel("XX"), atlas, AppLanguage.En).destination)
    }
}
