package com.plhery.peekdeliverytracker.ui.deliveries

import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Duration
import java.util.UUID

/**
 * The demo's parcels changed to reach what the opened map's summary says where the demo doesn't (A-ST30), seen at
 * [CAPTURED]: the demo's carriers name no destination, and none of its parcels goes back or is scanned nowhere.
 */
object MapStates {
    /** Moon lamp headed for Switzerland, as its carrier says: to there, how far it has come and how far it has to go. */
    fun headed(): Parcel {
        val lamp = DemoCards.parcel("Moon lamp")
        return lamp.copy(carrierData = (lamp.carrierData ?: CarrierData()).copy(destinationCountry = "CH"))
    }

    /** Kind of Blue scanned again an hour before the captures, nowhere said: last seen where it last was. */
    fun unplaced(): Parcel = DemoCards.parcel("Kind of Blue").scanned(TrackingStage.InTransit, "Sorted for its next leg", null)

    /** Moon lamp sent back to Berlin an hour before the captures: a journey over, its length and nothing to go. */
    fun returned(): Parcel = DemoCards.parcel("Moon lamp").scanned(TrackingStage.Returned, "Returned to the design studio", BERLIN)

    private fun Parcel.scanned(stage: TrackingStage, words: String, place: EventPlace?): Parcel = copy(
        trackingEvents = trackingEvents + TrackingEvent(
            id = UUID.nameUUIDFromBytes("$id-map".toByteArray()), packageId = id, stage = stage, description = words,
            location = place?.let { "${it.name}, Germany" }, occurredAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))),
            place = place,
        ),
    )

    /** Where the demo's Moon lamp was first scanned. */
    private val BERLIN = EventPlace(latitude = 52.524, longitude = 13.411, precision = EventPlacePrecision.City, country = "DE", name = "Berlin")
}
