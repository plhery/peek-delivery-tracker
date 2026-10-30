package com.plhery.peekdeliverytracker.ui.deliveries

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.util.UUID

/**
 * Made-up parcels for the parcel page's journal, seen from Zurich at [CAPTURED] (Thursday 29 October 2026, 06:11),
 * their scans in the demo's words and places.
 */
object JournalFixtures {
    val parcelId: UUID = UUID.fromString("00000000-0000-4000-8000-0000000000a1")

    val harkingen = EventPlace(latitude = 47.305, longitude = 7.821, precision = EventPlacePrecision.City, country = "CH", name = "Härkingen")

    /** The scan numbered `number`: its id is made from it, so scans keep their order and their ids from run to run. */
    fun scan(
        number: Int,
        occurredAt: String,
        description: String,
        stage: TrackingStage = TrackingStage.InTransit,
        location: String? = null,
        place: EventPlace? = null,
        day: String? = null,
        relayOf: UUID? = null,
    ): TrackingEvent = TrackingEvent(
        id = id(number), packageId = parcelId, stage = stage, description = description, location = location,
        occurredAt = occurredAt, day = day, place = place, relayOf = relayOf,
    )

    fun id(number: Int): UUID = UUID.fromString("00000000-0000-4000-8000-%012d".format(number))

    fun parcel(events: List<TrackingEvent>, syncStatus: SyncStatus = SyncStatus.Ok): Parcel = Parcel(
        id = parcelId, trackingNumber = "DEMOJOURNAL0001", label = "Desk lamp 💡", carrier = CarrierId.SwissPost,
        createdAt = "2026-10-20T12:00:00Z", lastSyncedAt = "2026-10-29T05:00:00Z", syncStatus = syncStatus,
        notificationsMuted = false, trackingEvents = events,
    )

    /**
     * A journey over three days: out for delivery this morning, the current scan; sorted in Härkingen last night, with
     * the same carrier's repeat of that scan, which folds into it; accepted yesterday morning, with the sender's
     * carrier's copy in its own words; and labelled the week before.
     */
    fun journey(): Parcel = parcel(
        listOf(
            scan(1, "2026-10-20T12:30:00Z", "The sender created a shipping label", TrackingStage.Registered),
            scan(2, "2026-10-28T07:02:00Z", "Parcel accepted at the counter", TrackingStage.Accepted, "Zürich-Mülligen"),
            scan(3, "2026-10-28T07:04:00Z", "Shipment details received from the sender's carrier", TrackingStage.Accepted, relayOf = id(2)),
            scan(4, "2026-10-28T20:15:00Z", "Sorted at the parcel centre", location = "Härkingen, CH", place = harkingen),
            scan(5, "2026-10-28T20:40:00Z", "Sorted at the parcel centre", location = "Härkingen, CH", place = harkingen, relayOf = id(4)),
            scan(6, "2026-10-29T04:40:00Z", "With the courier for delivery today", TrackingStage.OutForDelivery, "Your neighbourhood"),
        ),
    )
}
