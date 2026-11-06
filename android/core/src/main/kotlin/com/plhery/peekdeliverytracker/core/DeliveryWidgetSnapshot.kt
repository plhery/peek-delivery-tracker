package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.io.File
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

// From DeliveryWidgetSnapshot.swift: what the store publishes for the widget, the pictures of its cards, the sample its
// gallery shows, and which parcels a widget shows (with DeliveryWidget.swift's provider: its entry and its timeline).

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
    /** The parcel's card, once the app has drawn its pictures: the widget then shows the card rather than words alone. */
    val card: DeliveryWidgetCard? = null,
) {
    /** `deepLink`: the parcel on the deliveries, as the app's routing opens it (NativeRoute). */
    val deepLink: String get() = "peekdeliverytracker://parcel/${id.uuidString}"
}

/**
 * A parcel's card as the widget shows it. A widget has too little memory to draw the map, so the app draws the card's
 * pictures into the widget's folder and the widget writes the words over them.
 */
@Serializable
data class DeliveryWidgetCard(
    /** The card's colour and its words', light then dark, as hex. */
    val surface: List<String>,
    val ink: List<String>,
    /** The parcel's status, which the card writes before its date. */
    val status: String,
    /** What the pictures' file names start with. It changes whenever they do. */
    val pictures: String,
    /** The pictures' pixels per point (dp). */
    val scale: Double,
)

/**
 * The pictures of a card: its carrier's mark, and its route as a small widget, half a medium one or a whole medium one
 * shows it, across the top of the card.
 */
enum class DeliveryWidgetPicture(val rawValue: String) {
    Mark("mark"),
    Small("small"),
    Half("half"),
    Wide("wide"),
    ;

    fun file(of: DeliveryWidgetCard, dark: Boolean, folder: File): File =
        File(folder, "${of.pictures}-$rawValue-${if (dark) "dark" else "light"}.png")

    companion object {
        val routes: List<DeliveryWidgetPicture> = listOf(Small, Half, Wide)
    }
}

/**
 * What the widget gallery shows until the app has drawn the reader's own cards: the demo's next deliveries as the app
 * publishes them in each language, with the pictures the iPhone app draws for them. Both apps read them from the
 * iPhone widget's folder (ios/DeliveryWidgetExtension/WidgetSample), which its tests redraw.
 */
object DeliveryWidgetSample {
    const val folderName = "WidgetSample"
    const val snapshotsFile = "snapshots.json"

    /** The picture of each shape a sample card shows in: the first card alone in a small widget, both in a medium one. */
    fun pictures(of: Int): List<DeliveryWidgetPicture> =
        if (of == 0) listOf(DeliveryWidgetPicture.Small, DeliveryWidgetPicture.Half) else listOf(DeliveryWidgetPicture.Half)

    /** The sample in `languageCode`, else in English, from the bytes of [snapshotsFile]; null when they can't be read. */
    fun snapshot(languageCode: String, snapshots: ByteArray): DeliveryWidgetSnapshot? {
        val all = try {
            decodeJson(MapSerializer(String.serializer(), StoredSnapshot.serializer()), snapshots)
        } catch (_: Exception) {
            return null
        }
        return (all[languageCode] ?: all["en"])?.snapshot
    }
}

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
        now: Instant = AppClock.now(),
    ): List<DeliveryWidgetParcel> {
        val recent = delivered.filter { isShown(it, now) && it.deliveredAt != null }
        return (onTheWay.filter { it.isOutForDelivery } + onTheWay.filter { !it.isOutForDelivery } + recent).take(limit)
    }

    /**
     * What a widget shows at a time, first page first. A small one shows one parcel. A medium one shows a parcel out for
     * delivery beside the parcel after it, and any other parcel alone, across it.
     */
    fun pages(of: List<DeliveryWidgetParcel>, medium: Boolean, now: Instant = AppClock.now()): List<List<DeliveryWidgetParcel>> {
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
                decodeJson(StoredSnapshot.serializer(), data).snapshot
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
        val stored = StoredSnapshot(snapshot.generatedAt.timeIntervalSinceReferenceDate, snapshot.languageCode, snapshot.parcels)
        val data = try {
            JsonEncoder().encode(StoredSnapshot.serializer(), stored)
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
    fun turn(size: String, now: Instant = AppClock.now()): DeliveryWidgetTurn? =
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

/** As the iPhone's JSONEncoder writes a snapshot: the date as seconds since 2001. */
@Serializable
internal class StoredSnapshot(val generatedAt: Double, val languageCode: String, val parcels: List<DeliveryWidgetParcel>) {
    val snapshot: DeliveryWidgetSnapshot get() = DeliveryWidgetSnapshot(referenceDate(generatedAt), languageCode, parcels)
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
