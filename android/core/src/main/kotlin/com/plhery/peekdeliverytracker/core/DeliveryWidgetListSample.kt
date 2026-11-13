package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Duration
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID

/**
 * The list widget's sample, which its gallery picture and its screenshots show: fifteen invented parcels across the
 * stages, the same on the iPhone, already in the widgets' order (out for delivery, the others by their day, then those
 * delivered within the day). Their names stay in English; their stages and days are the app's words in its language,
 * dated from `now`, so the sample never goes stale.
 */
object DeliveryWidgetListSample {
    private class Sample(
        val name: String?,
        val carrier: String,
        val stage: TrackingStage?,
        /** The expected day, in days from today, and the hours a carrier gave for it. */
        val day: Long? = null,
        val hours: Pair<LocalTime, LocalTime>? = null,
        /** How long ago a delivered parcel arrived. */
        val delivered: Duration? = null,
    )

    private val samples = listOf(
        Sample("Ceramic pour-over set", "DHL Express", TrackingStage.OutForDelivery, day = 0, hours = LocalTime.of(14, 0) to LocalTime.of(16, 0)),
        Sample("Trail running shoes", "Colissimo", TrackingStage.OutForDelivery),
        Sample("Vintage vinyl records", "UPS", TrackingStage.InTransit, day = 1),
        Sample("Wool blanket", "PostNL", TrackingStage.Accepted, day = 1),
        Sample("Mechanical keyboard", "DPD", TrackingStage.InTransit, day = 2),
        Sample("Terracotta plant pots", "GLS", TrackingStage.Registered, day = 3),
        Sample("Camera lens from Japan", "Japan Post", TrackingStage.Customs, day = 4),
        Sample("Handmade oak bookshelf with matching side table and drawers", "bpost", TrackingStage.InTransit, day = 6),
        Sample(null, "Royal Mail", null),
        Sample("Single-origin coffee beans", "Swiss Post", TrackingStage.InTransit),
        Sample("Kids' science kit", "Evri", TrackingStage.FailedAttempt),
        Sample("Phone case", "InPost", TrackingStage.ReadyForPickup),
        Sample("Garden hose reel", "La Poste", TrackingStage.Exception),
        Sample("Paperback novels", "Poste Italiane", TrackingStage.Delivered, delivered = Duration.ofHours(3)),
        Sample("Desk lamp", "Correos", TrackingStage.Delivered, delivered = Duration.ofHours(20)),
    )

    /** The sample as the store would publish it in `localizer`'s language at `now`, in the phone's zone. */
    fun snapshot(localizer: Localizer, now: Instant): DeliveryWidgetSnapshot {
        val zone = ZoneId.systemDefault()
        val today = now.atZone(zone).toLocalDate()
        val parcels = samples.mapIndexed { index, sample ->
            val status = sample.stage?.let { localizer.text(it.localizationKey) } ?: localizer.text("status.unannounced")
            val arrived = sample.delivered?.let { now.minus(it) }
            val estimate = sample.day?.let { offset ->
                val day = today.plusDays(offset)
                val hours = sample.hours
                if (hours == null) {
                    localizer.deliveryEstimate(null, day.toString(), now)
                } else {
                    fun at(time: LocalTime) = day.atTime(time).atZone(zone).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    localizer.deliveryEstimate(at(hours.first), at(hours.second), now)
                }
            }
            val number = index + 1
            DeliveryWidgetParcel(
                id = UUID.fromString(String.format(Locale.ROOT, "00000000-0000-4000-8000-%012d", 100 + number)),
                label = sample.name ?: localizer.text("common.parcel"),
                carrier = sample.carrier,
                trackingNumber = String.format(Locale.ROOT, "DEMOLIST%04d", number),
                detail = arrived?.let { localizer.deliveryDate(it, now) } ?: estimate ?: status,
                isOutForDelivery = sample.stage == TrackingStage.OutForDelivery,
                deliveredAt = arrived,
                stage = DeliveryWidgetStage.of(sample.stage, status),
                named = sample.name != null,
            )
        }
        return DeliveryWidgetSnapshot(now, localizer.language.rawValue, parcels)
    }
}
