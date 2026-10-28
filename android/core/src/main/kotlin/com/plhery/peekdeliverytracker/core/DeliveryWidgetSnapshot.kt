package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.time.Instant
import java.util.UUID
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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
    /** When a delivered parcel arrived: the widget keeps it for a day after. */
    @Serializable(with = ReferenceDateSerializer::class) val deliveredAt: Instant? = null,
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
    /** How many parcels the widget's next button turns through. */
    const val limit = 8

    /** How long a delivered parcel stays in the widget, in seconds. */
    const val deliveredFor: Double = 24 * 60 * 60.0

    /**
     * The parcels the widget turns through: those out for delivery, then the others on their way as `onTheWay` orders
     * them, then those delivered within the last day, as `delivered` orders them.
     */
    fun parcels(
        onTheWay: List<DeliveryWidgetParcel>,
        delivered: List<DeliveryWidgetParcel>,
        now: Instant = Instant.now(),
    ): List<DeliveryWidgetParcel> {
        val recent = delivered.filter { isShown(it, now) && it.deliveredAt != null }
        return (onTheWay.filter { it.isOutForDelivery } + onTheWay.filter { !it.isOutForDelivery } + recent).take(limit)
    }

    /**
     * What a widget shows at a time, first page first. A small one shows one parcel. A medium one shows a parcel out for
     * delivery beside the parcel after it, and any other parcel alone, across it.
     */
    fun pages(of: List<DeliveryWidgetParcel>, medium: Boolean, now: Instant = Instant.now()): List<List<DeliveryWidgetParcel>> {
        val rest = ArrayDeque(of.filter { isShown(it, now) })
        val pages = mutableListOf<List<DeliveryWidgetParcel>>()
        while (true) {
            val first = rest.removeFirstOrNull() ?: break
            val second = if (medium && first.isOutForDelivery) rest.removeFirstOrNull() else null
            pages += if (second != null) listOf(first, second) else listOf(first)
        }
        return pages
    }

    /** A delivered parcel leaves the widget a day after it arrived, whenever the app last published it. */
    private fun isShown(parcel: DeliveryWidgetParcel, now: Instant): Boolean =
        parcel.deliveredAt?.let { now.timeIntervalSince(it) < deliveredFor } ?: true
}

/**
 * A widget turned to other parcels with its next button: the first of them, and when. It shows its first parcels again
 * a few minutes later, as its job is the next delivery.
 */
@Serializable
data class DeliveryWidgetTurn(
    @Serializable(with = UuidSerializer::class) val parcel: UUID,
    @Serializable(with = ReferenceDateSerializer::class) val at: Instant,
) {
    val ends: Instant
        get() = at.plusNanos(Math.round(spell * 1e9))

    companion object {
        /** How long a turn lasts, in seconds. */
        const val spell: Double = 5 * 60.0
    }
}

data class DeliveryWidgetSnapshot(
    val generatedAt: Instant,
    val languageCode: String,
    val parcels: List<DeliveryWidgetParcel>,
)

/**
 * What the app shares with its widget: whether it shows, the language it speaks, the next deliveries and where each
 * size of widget was turned to, under the iPhone app's keys. On the iPhone `defaults` is the App Group's UserDefaults,
 * which the widget extension reads too, or the standard one where the build has no App Group. On Android the
 * preferences the app's widget reads.
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

    /** Where the widgets of one size were turned to, until the turn's spell ends. */
    fun turn(size: String, now: Instant = Instant.now()): DeliveryWidgetTurn? =
        turns[size]?.takeIf { now < it.ends }

    fun setTurn(turn: DeliveryWidgetTurn, size: String) {
        val turns = turns + (size to turn)
        val data = try {
            JsonEncoder().encode(turnsSerializer, turns)
        } catch (_: Exception) {
            return
        }
        defaults.set(data, forKey = turnsKey)
    }

    private val turns: Map<String, DeliveryWidgetTurn>
        get() {
            val data = defaults.data(forKey = turnsKey) ?: return emptyMap()
            return try {
                decodeJson(turnsSerializer, data)
            } catch (_: Exception) {
                emptyMap()
            }
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
        private const val turnsKey = "sdt.deliveryWidget.turns.v1"

        private val turnsSerializer = MapSerializer(String.serializer(), DeliveryWidgetTurn.serializer())
    }
}

/**
 * `Date(timeIntervalSinceReferenceDate:)` to the microsecond, about the Double's step at today's dates, so that a date
 * written from an Instant reads back as that Instant, as the iPhone's Date, which is the Double, does.
 */
private fun referenceDate(seconds: Double): Instant {
    val micros = Math.round(seconds * 1_000_000)
    return Instant.ofEpochSecond(978_307_200 + Math.floorDiv(micros, 1_000_000L), Math.floorMod(micros, 1_000_000L) * 1_000)
}

/** A date as the iPhone's JSONEncoder writes one: seconds since 2001. */
internal object ReferenceDateSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ReferenceDate", PrimitiveKind.DOUBLE)

    override fun deserialize(decoder: Decoder): Instant = referenceDate(decoder.decodeDouble())

    override fun serialize(encoder: Encoder, value: Instant) = encoder.encodeDouble(value.timeIntervalSinceReferenceDate)
}
