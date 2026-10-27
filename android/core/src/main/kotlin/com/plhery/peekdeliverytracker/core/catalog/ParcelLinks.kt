package com.plhery.peekdeliverytracker.core.catalog

import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PublicPackage

// The helper from ParcelLinks.swift that the carrier catalog's tests use: Swift's `Parcel(shared:)`.

/** The parcel a parcel link shows. */
fun Parcel(shared: PublicPackage): Parcel {
    val data = shared.carrierData
    return Parcel(
        id = shared.id,
        trackingNumber = shared.trackingNumber ?: "",
        label = shared.label,
        carrier = shared.carrier,
        createdAt = shared.createdAt,
        expectedDelivery = shared.expectedDelivery,
        lastStatusText = shared.lastStatusText,
        lastSyncedAt = shared.lastSyncedAt,
        syncStatus = shared.syncStatus,
        syncError = shared.syncError,
        trackingUrl = shared.trackingUrl,
        dpdPostcode = shared.dpdPostcode,
        carrierData = CarrierData(
            activeTrackingCarrier = data.activeTrackingCarrier,
            activeTrackingNumber = data.activeTrackingNumber,
            deliveryCarrier = data.deliveryCarrier,
            deliveryTrackingNumber = data.deliveryTrackingNumber,
            canonicalTrackingNumber = data.canonicalTrackingNumber,
            originalCarrier = data.originalCarrier,
            originalTrackingNumber = data.originalTrackingNumber,
            trackingProvider = data.trackingProvider,
            carrierAnswered = data.carrierAnswered,
            autoChangedFrom = data.autoChangedFrom,
            autoChangedTo = data.autoChangedTo,
            autoChangedAt = data.autoChangedAt,
            senderName = data.senderName,
            swissPostReady = data.swissPostReady,
            expectedDeliveryFrom = data.expectedDeliveryFrom,
            pickupPoint = data.pickupPoint,
            dimensionsText = data.dimensionsText,
            weightKg = data.weightKg,
            importCharges = data.importCharges,
            destinationCountry = data.destinationCountry,
        ),
        archivedAt = shared.archivedAt,
        notificationsMuted = shared.notificationsMuted,
        trackingEvents = shared.trackingEvents,
    )
}
