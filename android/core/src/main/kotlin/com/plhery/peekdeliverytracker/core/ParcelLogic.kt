package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel

// The helpers from ParcelLogic.swift that the carrier catalog uses.

/** One of a parcel's numbers: Swift's `(carrier:, number:, tracksAs:)`. */
data class ParcelTrackingNumber(val carrier: CarrierId, val number: String, val tracksAs: Boolean)

/** The carrier the followed one names as delivering, and the number it gave. */
data class NamedDelivery(val carrier: CarrierId, val number: String?)

/**
 * Distinct numbers, the followed one first, then the named delivering carrier's own, and last the number the
 * followed carrier tracks the parcel by when it is none of them. That one is `tracksAs`: a reference the
 * carrier's search resolved to a parcel, or a number typed with more or less than the carrier's own, shown so
 * the reader can tell which parcel the carrier answered for.
 */
val Parcel.trackingNumbers: List<ParcelTrackingNumber>
    get() {
        val delivery = ParcelTrackingNumber(activeTrackingCarrier, carrierData?.activeTrackingNumber?.nonEmpty ?: trackingNumber, false)
        val original = ParcelTrackingNumber(
            carrierData?.originalCarrier ?: carrier, carrierData?.originalTrackingNumber?.nonEmpty ?: trackingNumber, false,
        )
        val numbers = if (delivery.number == original.number) mutableListOf(delivery) else mutableListOf(delivery, original)
        val named = namedDelivery
        if (named?.number != null) numbers += ParcelTrackingNumber(named.carrier, named.number, false)
        val canonical = carrierData?.canonicalTrackingNumber?.nonEmpty
        if (canonical == null ||
            numbers.any { CarrierCatalog.normalize(it.number) == CarrierCatalog.normalize(canonical) }
        ) return numbers
        return numbers + ParcelTrackingNumber(delivery.carrier, canonical, true)
    }

/**
 * What a number is called beside it: the carrier's name once there are several, and what the carrier tracks
 * the parcel as for the number it resolved the typed one to.
 */
fun Parcel.trackingNumberLabel(entry: ParcelTrackingNumber, localizer: Localizer): String {
    val name = CarrierCatalog.shared.info(entry.carrier, localizer.language).displayName
    if (entry.tracksAs) return localizer.text("detail.tracksAs", mapOf("carrier" to name))
    return if (trackingNumbers.count { !it.tracksAs } > 1) name else localizer.text("detail.trackingNumber")
}

val Parcel.displayedCarrier: CarrierId get() = carrierData?.originalCarrier ?: activeTrackingCarrier

/**
 * The carrier the followed one names as delivering, while the parcel is not followed there yet, and the
 * number it gave. Once it is followed, the parcel has been handed over and `trackingSource` says so.
 */
val Parcel.namedDelivery: NamedDelivery?
    get() {
        val data = carrierData ?: return null
        val carrier = data.deliveryCarrier ?: return null
        if (data.originalCarrier != null || carrier == displayedCarrier ||
            CarrierCatalog.shared.definitions[carrier] == null
        ) return null
        val number = data.deliveryTrackingNumber?.nonEmpty
        return NamedDelivery(carrier, if (number == trackingNumber) null else number)
    }

/** Who delivers when it isn't the carrier shown: the one the parcel was handed to, else the one its carrier names. */
val Parcel.deliveringCarrier: CarrierId?
    get() = if (activeTrackingCarrier != displayedCarrier) activeTrackingCarrier else namedDelivery?.carrier

val Parcel.activeTrackingCarrier: CarrierId
    get() {
        if (CarrierCatalog.shared.requiresAmazonAccount(carrier, trackingNumber)) return CarrierId.AmazonLogistics
        trackingSource?.let { return it }
        return if (CarrierCatalog.supportsSwissPostHandoff(trackingNumber)) CarrierId.Aliexpress else carrier
    }
