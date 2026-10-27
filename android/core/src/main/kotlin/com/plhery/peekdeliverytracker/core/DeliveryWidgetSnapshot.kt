package com.plhery.peekdeliverytracker.core

import java.util.UUID

// From DeliveryWidgetSnapshot.swift, what the parcel logic and its tests use. The card's pictures, the deep link and
// the shared store are the widget's, and come with its port.

data class DeliveryWidgetParcel(
    val id: UUID,
    val label: String,
    val carrier: String,
    val trackingNumber: String,
    val detail: String,
    val isOutForDelivery: Boolean,
)

enum class DeliveryActivityPhase(val rawValue: String) {
    OutForDelivery("out_for_delivery"),
    Delivered("delivered"),
    FailedAttempt("failed_attempt"),
    ReadyForPickup("ready_for_pickup"),
    Returned("returned"),
    Exception("exception"),
    Ended("ended"),
}

object DeliveryWidgetSelection {
    fun displayParcels(from: List<DeliveryWidgetParcel>, limit: Int = 2): List<DeliveryWidgetParcel> {
        if (limit <= 0) return emptyList()
        val outForDelivery = from.filter { it.isOutForDelivery }
        if (outForDelivery.size >= limit) return outForDelivery.take(limit)
        val firstOutForDelivery = outForDelivery.firstOrNull() ?: return from.take(1)
        val nextUp = from.firstOrNull { !it.isOutForDelivery }
        if (limit <= 1 || nextUp == null) return listOf(firstOutForDelivery)
        return listOf(firstOutForDelivery, nextUp)
    }
}
