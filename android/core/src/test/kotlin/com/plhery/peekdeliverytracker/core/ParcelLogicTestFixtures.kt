package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.util.UUID

/** ParcelLogicTests' private helpers, shared by the classes its tests are split into. */
internal object ParcelLogicTestFixtures {
    fun makeParcel(
        id: UUID = UUID.randomUUID(),
        trackingNumber: String = "1Z999AA10123456784",
        events: List<TrackingEvent> = emptyList(),
    ): Parcel = Parcel(
        id = id,
        trackingNumber = trackingNumber,
        label = "Test parcel",
        carrier = CarrierId.Ups,
        createdAt = "2026-08-01T10:00:00Z",
        expectedDelivery = null,
        lastStatusText = null,
        lastSyncedAt = null,
        syncStatus = SyncStatus.Ok,
        syncError = null,
        trackingUrl = null,
        dpdPostcode = null,
        carrierData = null,
        archivedAt = null,
        notificationsMuted = false,
        trackingEvents = events,
    )

    fun event(packageId: UUID, stage: TrackingStage, occurredAt: String): TrackingEvent = TrackingEvent(
        id = UUID.randomUUID(), packageId = packageId, stage = stage,
        description = "Update", location = null, occurredAt = occurredAt,
    )

    fun widgetParcel(label: String, outForDelivery: Boolean): DeliveryWidgetParcel = DeliveryWidgetParcel(
        id = UUID.randomUUID(),
        label = label,
        carrier = "Swiss Post",
        trackingNumber = "99.34.123456.12345678",
        detail = "Today",
        isOutForDelivery = outForDelivery,
    )
}
