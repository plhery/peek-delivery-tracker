package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// From ParcelStore.swift, the demo's sample parcels. The store, and the demo repository that keeps them, come with
// the store port.

class DemoRepository {
    /** The sample parcels, in English, and each language's translation of their text. */
    @Serializable
    private class Catalog(val parcels: List<Sample>, val translations: Map<String, Map<String, String>>) {
        @Serializable
        class Sample(
            val label: String,
            val trackingNumber: String,
            val carrier: CarrierId,
            val expectedInDays: Int? = null,
            val archivedHoursAgo: Double? = null,
            val senderName: String? = null,
            val pickupPoint: String? = null,
            val weightKg: Double? = null,
            val events: List<Event>,
        ) {
            @Serializable
            class Event(
                val stage: TrackingStage,
                val hoursAgo: Double,
                val description: String,
                val location: String? = null,
                val place: EventPlace? = null,
            )
        }
    }

    companion object {
        private val catalog: Catalog by lazy {
            runCatching {
                Json { ignoreUnknownKeys = true }.decodeFromString(Catalog.serializer(), SharedResources.text("DeliveryDemo.json"))
            }.getOrElse { Catalog(emptyList(), emptyMap()) }
        }

        /** The sample parcels in English; the repository writes them in the app's language. */
        fun seed(now: Instant = Instant.now()): List<Parcel> {
            fun iso(hoursAgo: Double): String = DateParser.isoString(now.minusNanos((hoursAgo * 3_600 * 1_000_000_000).toLong()))
            return catalog.parcels.map { sample ->
                val id = UUID.randomUUID()
                val history = sample.events.map { event ->
                    TrackingEvent(
                        id = UUID.randomUUID(), packageId = id, stage = event.stage, description = event.description,
                        location = event.location, occurredAt = iso(event.hoursAgo), place = event.place,
                    )
                }
                val expected = sample.expectedInDays?.let { days ->
                    ParcelOrganizer.dayKey(now.atZone(ZoneId.systemDefault()).plusDays(days.toLong()).toInstant())
                }
                Parcel(
                    id = id, trackingNumber = sample.trackingNumber, label = sample.label, carrier = sample.carrier,
                    createdAt = iso(sample.events.maxOfOrNull { it.hoursAgo } ?: 0.0), expectedDelivery = expected,
                    lastStatusText = history.sortedByDescending { it.occurredAt }.firstOrNull()?.description,
                    lastSyncedAt = iso(0.2), syncStatus = SyncStatus.Ok, syncError = null,
                    trackingUrl = null, dpdPostcode = if (sample.carrier == CarrierId.Dpd) "8000" else null,
                    carrierData = if (sample.senderName == null && sample.pickupPoint == null && sample.weightKg == null) {
                        null
                    } else {
                        CarrierData(senderName = sample.senderName, pickupPoint = sample.pickupPoint, weightKg = sample.weightKg)
                    },
                    archivedAt = sample.archivedHoursAgo?.let(::iso), notificationsMuted = false,
                    trackingEvents = history,
                )
            }
        }
    }
}
