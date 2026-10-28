package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.time.Instant
import java.util.UUID
import kotlinx.serialization.Serializable

// From DeliveryWidgetSnapshot.swift: what the store publishes for the widget, and what the parcel logic and its tests
// use. The card's pictures and the deep link are the widget's, and come with its port.

@Serializable
data class DeliveryWidgetParcel(
    @Serializable(with = UuidSerializer::class) val id: UUID,
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

data class DeliveryWidgetSnapshot(
    val generatedAt: Instant,
    val languageCode: String,
    val parcels: List<DeliveryWidgetParcel>,
)

/**
 * What the app shares with its widget: whether it shows, the language it speaks and the next deliveries, under the
 * iPhone app's keys. On the iPhone `defaults` is the App Group's UserDefaults, which the widget extension reads too,
 * or the standard one where the build has no App Group. On Android the preferences the app's widget reads.
 */
class DeliveryWidgetSharedStore(private val defaults: UserDefaults) {
    val isEnabled: Boolean
        get() = !defaults.hasObject(forKey = enabledKey) || defaults.bool(forKey = enabledKey)

    val languageCode: String?
        get() = defaults.string(forKey = languageKey)

    val liveActivitiesEnabled: Boolean
        get() = if (!defaults.hasObject(forKey = liveActivitiesEnabledKey)) isEnabled else defaults.bool(forKey = liveActivitiesEnabledKey)

    val snapshot: DeliveryWidgetSnapshot?
        get() {
            val data = defaults.data(forKey = snapshotKey) ?: return null
            return try {
                decodeJson(Stored.serializer(), data).let { DeliveryWidgetSnapshot(referenceDate(it.generatedAt), it.languageCode, it.parcels) }
            } catch (_: Exception) {
                null
            }
        }

    fun setEnabled(enabled: Boolean) {
        defaults.set(enabled, forKey = enabledKey)
        if (!enabled) clearSnapshot()
    }

    fun setLiveActivitiesEnabled(enabled: Boolean) {
        defaults.set(enabled, forKey = liveActivitiesEnabledKey)
    }

    fun setLanguageCode(languageCode: String) {
        defaults.set(languageCode, forKey = languageKey)
    }

    fun save(snapshot: DeliveryWidgetSnapshot): Boolean {
        val stored = Stored(snapshot.generatedAt.timeIntervalSinceReferenceDate, snapshot.languageCode, snapshot.parcels)
        val data = try {
            JsonEncoder().encode(Stored.serializer(), stored)
        } catch (_: Exception) {
            return false
        }
        defaults.set(data, forKey = snapshotKey)
        return true
    }

    fun clearSnapshot() {
        defaults.removeObject(forKey = snapshotKey)
    }

    /** As the iPhone's JSONEncoder writes the snapshot: the date as seconds since 2001. */
    @Serializable
    private class Stored(val generatedAt: Double, val languageCode: String, val parcels: List<DeliveryWidgetParcel>)

    companion object {
        const val kind = "NextDeliveryWidget"

        private const val enabledKey = "sdt.deliveryWidget.enabled.v1"
        private const val liveActivitiesEnabledKey = "sdt.deliveryLiveActivities.enabled.v1"
        private const val languageKey = "sdt.deliveryWidget.language.v1"
        private const val snapshotKey = "sdt.deliveryWidget.snapshot.v1"

        /** `Date(timeIntervalSinceReferenceDate:)`, to the nanosecond the Double holds. */
        private fun referenceDate(seconds: Double): Instant {
            val whole = Math.floor(seconds)
            return Instant.ofEpochSecond(978_307_200 + whole.toLong(), ((seconds - whole) * 1_000_000_000).toLong())
        }
    }
}
