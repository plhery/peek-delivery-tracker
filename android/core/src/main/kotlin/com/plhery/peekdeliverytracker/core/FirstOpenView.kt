package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.util.Locale
import java.util.UUID

// From FirstOpenView.swift, what isn't the view.

/** How someone starts following their first parcel. The deliveries list owns the sheet that adds it. */
sealed class FirstParcelRequest {
    /** Whatever is on the clipboard, read once the person asked for it, or text dropped on the deliveries. */
    data class Paste(val text: String) : FirstParcelRequest() {
        /** What Add opens with (`sharedDraft`): the text trimmed, as much of it as Add takes in; none when nothing is left. */
        val draft: String? get() = text.trimmingWhitespacesAndNewlines().nonEmpty?.let(::taken)
    }
    data object Type : FirstParcelRequest()
    data object Scan : FirstParcelRequest()

    companion object {
        /** As much pasted text as Add takes in. */
        const val pasteLimit = 2000

        /** As much of `text` as Add takes in: its first [pasteLimit] characters. */
        fun taken(text: String): String = text.characterPrefix(pasteLimit)

        /**
         * Text or a link dragged onto the deliveries from another app is taken as a paste: each piece once, one to a
         * line, as the share extension joins them. Null when nothing in it reads as a tracking number, or as a shop's
         * order number, which Add explains.
         */
        fun drop(items: List<String>, catalog: CarrierCatalog = CarrierCatalog.shared): FirstParcelRequest? {
            val pieces = mutableListOf<String>()
            for (item in items) {
                val piece = item.trimmingWhitespacesAndNewlines().nonEmpty ?: continue
                if (piece !in pieces) pieces.add(piece)
            }
            val text = pieces.joinToString("\n").characterPrefix(pasteLimit)
            return if (catalog.read(text).trackingNumber.isEmpty() && catalog.order(text) == null) null else Paste(text)
        }
    }
}

/** The sample parcel the first screen follows across the world, and the page it opens on. */
object FirstOpenJourney {
    /** Its scans; the journey is told one more scan at a time. */
    const val steps = 5

    /** What someone who asked for less motion sees: the parcel a day away, its route drawn. */
    const val stillStep = 3

    /** The number says what the parcel is, where a note would have had to. */
    const val trackingNumber = "123EXAMPLE"

    /** The same parcel all along, from the first screen's picture to the page it opens. */
    val parcelId: UUID = UUID.fromString("5A3B1E00-0000-4000-8000-000000000000")
    private const val kiosk = "Kiosk im Hauptbahnhof"

    private data class Stop(
        val name: String,
        /** Written out, so the history shows its flag and its name in the reader's language. */
        val country: String,
        val code: String,
        val latitude: Double,
        val longitude: Double,
    ) {
        val location: String get() = "$name, $country"
        val place: EventPlace get() = EventPlace(latitude = latitude, longitude = longitude, precision = EventPlacePrecision.City, country = code, name = name)
    }

    private val shenzhen = Stop(name = "Shenzhen", country = "China", code = "CN", latitude = 22.543, longitude = 114.058)
    private val leipzig = Stop(name = "Leipzig", country = "Germany", code = "DE", latitude = 51.340, longitude = 12.375)
    private val basel = Stop(name = "Basel", country = "Switzerland", code = "CH", latitude = 47.558, longitude = 7.573)
    private val zurich = Stop(name = "Zürich", country = "Switzerland", code = "CH", latitude = 47.367, longitude = 8.55)

    private data class Scan(val stage: TrackingStage, val hoursAgo: Double, val stop: Stop)

    private val scans = listOf(
        Scan(TrackingStage.Accepted, 80.0, shenzhen),
        Scan(TrackingStage.InTransit, 26.0, leipzig),
        Scan(TrackingStage.InTransit, 6.0, basel),
        Scan(TrackingStage.OutForDelivery, 3.0, zurich),
        Scan(TrackingStage.Delivered, 0.0, zurich),
    )

    private data class StoryScan(val stage: TrackingStage, val hoursAgo: Double, val line: String, val stop: Stop?)

    /** The whole story, as the parcel opens on it: a missed doorbell, and the kiosk it was picked up from. */
    private val story = listOf(
        StoryScan(TrackingStage.Registered, 84.0, "sample.journey.packed", null),
        StoryScan(TrackingStage.Accepted, 80.0, "sample.journey.collected", shenzhen),
        StoryScan(TrackingStage.InTransit, 30.0, "sample.journey.flight", leipzig),
        StoryScan(TrackingStage.Customs, 9.0, "sample.journey.customs", basel),
        StoryScan(TrackingStage.OutForDelivery, 6.0, "sample.journey.courier", zurich),
        StoryScan(TrackingStage.FailedAttempt, 4.5, "sample.journey.missed", zurich),
        StoryScan(TrackingStage.ReadyForPickup, 3.5, "sample.journey.waiting", zurich),
        StoryScan(TrackingStage.Delivered, 0.4, "sample.journey.pickedUp", zurich),
    )

    fun name(localizer: Localizer): String = "${localizer.text("landing.parcel.sneakers")} 👟"

    /** The parcel once `step` of its scans are in. */
    fun parcel(step: Int, name: String, now: Instant = Instant.now()): Parcel {
        val step = maxOf(1, minOf(step, steps))
        val events = scans.take(step).mapIndexed { index, scan ->
            TrackingEvent(
                id = UUID.fromString("5A3B1E00-0000-4000-8000-00000000000${index + 1}"), packageId = parcelId,
                stage = scan.stage, description = "", location = scan.stop.name,
                occurredAt = DateParser.isoString(now.addingTimeInterval(-scan.hoursAgo * 3600)),
                place = scan.stop.place,
            )
        }
        // It arrives in three days, then two, then tomorrow, then today.
        val daysLeft = maxOf(0, 4 - step).toDouble()
        return Parcel(
            id = parcelId, trackingNumber = trackingNumber, label = name, carrier = CarrierId.Dhl,
            createdAt = DateParser.isoString(now.addingTimeInterval(-96.0 * 3600)),
            expectedDelivery = ParcelOrganizer.dayKey(now.addingTimeInterval(daysLeft * 86_400)),
            lastSyncedAt = DateParser.isoString(now), syncStatus = SyncStatus.Ok,
            carrierData = CarrierData(destinationCountry = "CH"),
            notificationsMuted = false, trackingEvents = events,
        )
    }

    /** The parcel as it opens: picked up, every scan of its story told in the app's language. */
    fun delivered(localizer: Localizer, now: Instant = Instant.now()): Parcel {
        val events = story.mapIndexed { index, scan ->
            TrackingEvent(
                id = UUID.fromString(String.format(Locale.ROOT, "5A3B1E00-0000-4000-8000-%012X", 0x100 + index)), packageId = parcelId,
                stage = scan.stage, description = localizer.text(scan.line), location = scan.stop?.location,
                occurredAt = DateParser.isoString(now.addingTimeInterval(-scan.hoursAgo * 3600)),
                place = scan.stop?.place,
            )
        }
        return Parcel(
            id = parcelId, trackingNumber = trackingNumber, label = name(localizer), carrier = CarrierId.Dhl,
            createdAt = DateParser.isoString(now.addingTimeInterval(-96.0 * 3600)),
            expectedDelivery = ParcelOrganizer.dayKey(now),
            lastSyncedAt = DateParser.isoString(now), syncStatus = SyncStatus.Ok,
            carrierData = CarrierData(
                senderName = "Sole Studio", pickupPoint = "$kiosk\nBahnhofplatz 15, 8001 Zürich",
                weightKg = 0.9, destinationCountry = "CH",
            ),
            notificationsMuted = false, trackingEvents = events,
        )
    }

    /** How long a scan stays before the next one comes in: the last mile and the arrival get a longer look. */
    fun hold(step: Int): Double = when (step) {
        4 -> 3.4
        5 -> 5.2
        else -> 2.7
    }

    /** The alert that lands with a scan worth one. */
    fun ping(step: Int, localizer: Localizer): String? = when (step) {
        4 -> "${localizer.text("stage.out_for_delivery")} · ${localizer.text("time.today")}, 13:00–17:00"
        5 -> "${localizer.text("stage.delivered")} · $kiosk, 18:05"
        else -> null
    }

    /** Swift's `Date.addingTimeInterval`. */
    private fun Instant.addingTimeInterval(seconds: Double): Instant = plusNanos(Math.round(seconds * 1e9))
}
