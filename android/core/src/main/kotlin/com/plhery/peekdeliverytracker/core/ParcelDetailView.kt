package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage

// From ParcelDetailView.swift, what isn't the view. Copying to the clipboard is the app's.

/** Copies a tracking number as its carrier's site takes it. */
object TrackingNumberCopy {
    fun text(number: String, carrier: CarrierId): String =
        if (carrier == CarrierId.Postlogistics) CarrierCatalog.format(number, carrier) else number
}

/**
 * Where the parcel waits for collection (`waitingAt`): its pickup point while it is ready for pickup, which the page
 * gives a card; afterwards the point is a plain fact ([shipmentFacts]).
 */
val Parcel.waitingAt: PickupPoint?
    get() = if (currentStage == TrackingStage.ReadyForPickup) PickupPoint(carrierData?.pickupPoint) else null

/** The scan's words as the page quotes them, after "What {carrier} says": trimmed, and none when nothing is left. */
val TrackingEvent.quotedWords: String?
    get() = description.trimmingWhitespacesAndNewlines().nonEmpty

/** A fact of the shipment the parcel's page lists (`shipmentFact`): the key of its label, and its value. */
data class ShipmentFact(val key: String, val value: String)

/**
 * The facts of the shipment the parcel's page lists under its numbers, in `liveParcelPass`'s order: what importing it
 * costs (`importCharges`); its pickup point, or where it was picked up once delivered, unless a card shows it
 * ([waitingAt]); who receives it; its weight in the app language's digits with up to three decimals, then " kg"; and
 * its dimensions, each trimmed and left out when empty. Null without carrier data, where the page lists nothing.
 */
fun Localizer.shipmentFacts(parcel: Parcel): List<ShipmentFact>? {
    val details = parcel.carrierData ?: return null
    fun trimmed(value: String?) = value?.trimmingWhitespacesAndNewlines()?.nonEmpty
    val facts = mutableListOf<ShipmentFact>()
    importCharges(parcel)?.let { facts += ShipmentFact("detail.importCharges", it.text) }
    val pickup = trimmed(details.pickupPoint)
    if (parcel.waitingAt == null && pickup != null) {
        facts += ShipmentFact(if (parcel.currentStage == TrackingStage.Delivered) "detail.collectedAt" else "detail.pickupPoint", pickup)
    }
    trimmed(details.receiverName)?.let { facts += ShipmentFact("detail.recipient", it) }
    val weight = details.weightKg
    if (weight != null && weight.isFinite() && weight > 0) {
        facts += ShipmentFact("detail.weight", foundationDecimal(weight, 3, language) + " kg")
    }
    trimmed(details.dimensionsText)?.let { facts += ShipmentFact("detail.dimensions", it) }
    return facts
}
