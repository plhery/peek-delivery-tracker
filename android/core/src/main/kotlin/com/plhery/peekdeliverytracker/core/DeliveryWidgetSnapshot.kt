package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.io.File
import java.time.Instant
import java.time.ZoneId
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
// gallery shows, and which parcels it shows.

@Serializable
data class DeliveryWidgetParcel(
    @Serializable(with = UuidSerializer::class) val id: UUID,
    val label: String,
    val carrier: String,
    val trackingNumber: String,
    /** When it is expected or arrived, as the app words it when it publishes; else its status. */
    val detail: String,
    val isOutForDelivery: Boolean,
    /** When a delivered parcel arrived: the widget keeps it for a day after. */
    @Serializable(with = ReferenceDateSerializer::class) val deliveredAt: Instant? = null,
    /** The parcel's card, once the app has drawn its pictures: the widget then shows the card rather than words alone. */
    val card: DeliveryWidgetCard? = null,
    /** The parcel's stage as the app's list marks it, which the widget shows; none from an app that didn't publish it. */
    val stage: DeliveryWidgetStage? = null,
    /** Whether the reader named the parcel: the widget calls one without a name by its carrier. */
    val named: Boolean? = null,
    /**
     * The detail, section and Next up from each of the next midnights where one changes, which the app writes ahead:
     * "tomorrow" becomes "today", under Arriving today, while the app stays closed. None from an app that didn't write
     * them.
     */
    val later: List<DeliveryWidgetWording>? = null,
    /** The section of the app's list the parcel is in (`ParcelSectionKind`), which the Parcels widget's sections follow. */
    val section: String? = null,
    /** Whether it is the app list's Next up, which leads the Parcels widget's cards and its section there. */
    val nextUp: Boolean? = null,
    /** The carrier shown, whose truck marks the parcel's row in the Parcels widget's sections. */
    val carrierId: String? = null,
    /** Its last tracking steps, latest first, which the Parcels widget shows of a parcel alone in it. */
    val steps: List<DeliveryWidgetStep>? = null,
    /**
     * Whether its card in the app's list shows the new-update dot (#72): a scan its reader hasn't seen, while "Mark new
     * updates" is on ([marksNewUpdate]). The Parcels widget shows the same dot, and loses it when the app does: the
     * parcel opened on any of the account's devices, or the setting off. None from an app that didn't publish it.
     */
    val newUpdate: Boolean? = null,
) {
    /** `deepLink`: the parcel on the deliveries, as the app's routing opens it (NativeRoute). */
    val deepLink: String get() = "peekdeliverytracker://parcel/${id.uuidString}"

    /** The parcel as the widgets word it at `date`: as the app wrote it for the last midnight before it. */
    fun worded(at: Instant): DeliveryWidgetParcel {
        val wording = later?.lastOrNull { it.midnight() <= at } ?: return this
        return copy(detail = wording.detail, section = wording.section ?: section, nextUp = wording.nextUp ?: nextUp)
    }
}

/** A parcel's detail from a moment on, and its section and whether it is Next up, where the app published them. */
@Serializable
data class DeliveryWidgetWording(
    @Serializable(with = ReferenceDateSerializer::class) val from: Instant,
    val detail: String,
    val section: String? = null,
    val nextUp: Boolean? = null,
    val sourceZone: String? = null,
) {
    fun midnight(zone: ZoneId = ZoneId.systemDefault()): Instant {
        val source = sourceZone?.let { runCatching { ZoneId.of(it) }.getOrNull() } ?: return from
        return from.atZone(source).toLocalDate().atStartOfDay(zone).toInstant()
    }
}

/** A step of a parcel's tracking: when, the carrier's words, and where. */
@Serializable
data class DeliveryWidgetStep(
    @Serializable(with = ReferenceDateSerializer::class) val at: Instant,
    val text: String,
    val place: String? = null,
    /** Its time as the app writes a time; empty for a step the carrier dated by its day alone. */
    val clock: String? = null,
) {
    /** Its time as the Parcels widget writes it ([Localizer.stepTime]). */
    fun time(now: Instant, localizer: Localizer): String = localizer.stepTime(at, now, clock)
}

/**
 * The days the widgets' words follow while the app stays closed: "today", "tomorrow" and "yesterday" change at
 * midnight, and an estimate that has passed gives way to the status. The app list's sections and its Next up change
 * with them: a parcel due today is under Arriving today.
 */
object DeliveryWidgetDays {
    /** How many midnights ahead the app words the parcels. */
    const val ahead = 7

    /** The phone's midnights after `now`. */
    fun midnights(after: Instant, count: Int = ahead, zone: ZoneId = ZoneId.systemDefault()): List<Instant> {
        val today = after.atZone(zone).toLocalDate()
        return (1..maxOf(count, 1)).map { today.plusDays(it.toLong()).atStartOfDay(zone).toInstant() }
    }

    /** The detail's words at each of `midnights` where they change from those before. */
    fun later(than: String, at: List<Instant>, words: (Instant) -> String): List<DeliveryWidgetWording>? =
        later(DeliveryWidgetWording(Instant.EPOCH, than), at) { DeliveryWidgetWording(it, words(it)) }

    /** The parcel's wording at each of `midnights` where it changes from the one before, its moment aside. */
    fun later(than: DeliveryWidgetWording, at: List<Instant>, wording: (Instant) -> DeliveryWidgetWording): List<DeliveryWidgetWording>? {
        var last = than
        val changes = at.mapNotNull { midnight ->
            val next = wording(midnight)
            if (next.copy(from = last.from) == last) return@mapNotNull null
            last = next
            next.copy(sourceZone = ZoneId.systemDefault().id)
        }
        return changes.ifEmpty { null }
    }

    /**
     * When the parcels' words change after `now`, and the midnight after each of their steps, from which a step's time
     * names its weekday: a widget is drawn again at each.
     */
    fun changes(`in`: List<DeliveryWidgetParcel>, after: Instant, zone: ZoneId = ZoneId.systemDefault()): List<Instant> {
        val worded = `in`.flatMap { it.later.orEmpty() }.map { it.midnight(zone) }
        val stepped = `in`.flatMap { it.steps.orEmpty() }.map { midnights(after = it.at, count = 1, zone = zone).first() }
        return (worded + stepped).filter { it > after }.toSortedSet().toList()
    }
}

/**
 * A parcel's stage as a row of the app's list shows it: the status in the app's words, and the symbol and colour that
 * mark it, the colour light then dark as hex. `short` is the status in the widget's shorter words, for a line too
 * narrow for the app's ([Localizer.widgetShortStatus]); none where they are the same.
 */
@Serializable
data class DeliveryWidgetStage(val status: String, val symbol: String, val tint: List<String>, val short: String? = null) {
    companion object {
        /** As the list's rows mark a parcel at `stage`, `status` in the app's words (`ParcelStore.widgetParcel`). */
        fun of(stage: TrackingStage?, status: String, short: String? = null): DeliveryWidgetStage =
            DeliveryWidgetStage(status, stage?.metadata?.symbol ?: "shippingbox.fill", ExperimentalPalette.tintHex(stage), short)
    }
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
    /**
     * How many of the first parcels the app draws cards for and gives their last steps: more than any widget shows as
     * cards or passes.
     */
    const val limit = 8

    /** How many rows the Parcels widget shows at most, four cells by four. */
    const val listLimit = 15

    /** How many of a parcel's last steps the widget shows of it alone, which the first [limit] parcels carry. */
    const val steps = 4

    /** How long a delivered parcel stays in the widget, in seconds. */
    const val deliveredFor: Double = 24 * 60 * 60.0

    /** The first [limit] parcels in the widget's order ([ordered]). */
    fun parcels(
        onTheWay: List<DeliveryWidgetParcel>,
        delivered: List<DeliveryWidgetParcel>,
        now: Instant = AppClock.now(),
    ): List<DeliveryWidgetParcel> = ordered(onTheWay, delivered, now).take(limit)

    /**
     * The parcels the widget shows, in their order: those out for delivery, then the others on their way as `onTheWay`
     * orders them, then those delivered within the last day, as `delivered` orders them. The app publishes them all, so
     * that the widget can count those it has no room for.
     */
    fun ordered(
        onTheWay: List<DeliveryWidgetParcel>,
        delivered: List<DeliveryWidgetParcel>,
        now: Instant = AppClock.now(),
    ): List<DeliveryWidgetParcel> {
        val recent = delivered.filter { isShown(it, now) && it.deliveredAt != null }
        return onTheWay.filter { it.isOutForDelivery } + onTheWay.filter { !it.isOutForDelivery } + recent
    }

    /** A delivered parcel leaves the widget a day after it arrived, whenever the app last published it. */
    fun isShown(parcel: DeliveryWidgetParcel, now: Instant): Boolean =
        parcel.deliveredAt?.let { now.timeIntervalSince(it) < deliveredFor } ?: true
}

data class DeliveryWidgetSnapshot(
    val generatedAt: Instant,
    val languageCode: String,
    /** Every parcel in the widget's order: the widget shows as many as fit and counts the rest. */
    val parcels: List<DeliveryWidgetParcel>,
)

/**
 * What the app shares with its widget: the language it speaks and the parcels, under the iPhone app's keys. On the
 * iPhone `defaults` is the App Group's UserDefaults, which the widget extension reads too, or the standard one where the
 * build has no App Group. On Android the preferences the app's widget reads.
 */
class DeliveryWidgetSharedStore(private val defaults: UserDefaults) {
    val languageCode: String?
        get() = defaults.string(forKey = languageKey)

    val liveActivitiesEnabled: Boolean
        get() = when {
            defaults.hasObject(forKey = liveActivitiesEnabledKey) -> defaults.bool(forKey = liveActivitiesEnabledKey)
            defaults.hasObject(forKey = retiredWidgetSwitchKey) -> defaults.bool(forKey = retiredWidgetSwitchKey)
            else -> true
        }

    val snapshot: DeliveryWidgetSnapshot?
        get() {
            val data = defaults.data(forKey = snapshotKey) ?: return null
            return try {
                decodeJson(StoredSnapshot.serializer(), data).snapshot
            } catch (_: Exception) {
                null
            }
        }

    /** Keeps the Live Activities' choice, which no longer needs the widgets' old switch. */
    fun setLiveActivitiesEnabled(enabled: Boolean) {
        defaults.set(enabled, forKey = liveActivitiesEnabledKey)
        defaults.removeObject(forKey = retiredWidgetSwitchKey)
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

    companion object {
        /**
         * The Parcels widget's kind. It keeps the name of the Next delivery widget it replaced: the widgets readers
         * placed carry it.
         */
        const val kind = "NextDeliveryWidget"

        /** Where the widgets' switch, now gone, kept its choice. Live Activities followed it until they had their own. */
        private const val retiredWidgetSwitchKey = "sdt.deliveryWidget.enabled.v1"
        private const val liveActivitiesEnabledKey = "sdt.deliveryLiveActivities.enabled.v1"
        private const val languageKey = "sdt.deliveryWidget.language.v1"
        private const val snapshotKey = "sdt.deliveryWidget.snapshot.v1"
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
internal fun referenceDate(seconds: Double): Instant {
    val micros = Math.round(seconds * 1_000_000)
    return Instant.ofEpochSecond(978_307_200 + Math.floorDiv(micros, 1_000_000L), Math.floorMod(micros, 1_000_000L) * 1_000)
}

/** A date as the iPhone's JSONEncoder writes one: seconds since 2001. */
internal object ReferenceDateSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ReferenceDate", PrimitiveKind.DOUBLE)

    override fun deserialize(decoder: Decoder): Instant = referenceDate(decoder.decodeDouble())

    override fun serialize(encoder: Encoder, value: Instant) = encoder.encodeDouble(value.timeIntervalSinceReferenceDate)
}
