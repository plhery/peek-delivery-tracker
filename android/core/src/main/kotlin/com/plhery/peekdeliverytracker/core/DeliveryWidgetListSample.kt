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
 * The widget's sample, which its gallery picture and its screenshots show: fifteen invented parcels across the
 * stages, the same on the iPhone, already in the widget's order (out for delivery, the others by their day, then those
 * delivered within the day), with the app list's sections, its Next up and each carrier. Their names stay in English; their stages and days are the app's words in its language,
 * dated from `now`, so the sample never goes stale.
 */
object DeliveryWidgetListSample {
    private class Sample(
        val name: String?,
        val carrier: String,
        val carrierId: String,
        val stage: TrackingStage?,
        /** The expected day, in days from today, and the hours a carrier gave for it. */
        val day: Long? = null,
        val hours: Pair<LocalTime, LocalTime>? = null,
        /** How long ago a delivered parcel arrived. */
        val delivered: Duration? = null,
    )

    private val samples = listOf(
        Sample("Ceramic pour-over set", "DHL Express", "dhl-express", TrackingStage.OutForDelivery, day = 0, hours = LocalTime.of(14, 0) to LocalTime.of(16, 0)),
        Sample("Trail running shoes", "Colissimo", "la-poste", TrackingStage.OutForDelivery),
        Sample("Vintage vinyl records", "UPS", "ups", TrackingStage.InTransit, day = 1),
        Sample("Wool blanket", "PostNL", "spring-gds", TrackingStage.Accepted, day = 1),
        Sample("Mechanical keyboard", "DPD", "dpd-de", TrackingStage.InTransit, day = 2),
        Sample("Terracotta plant pots", "GLS", "gls-de", TrackingStage.Registered, day = 3),
        Sample("Camera lens from Japan", "Japan Post", "japan-post", TrackingStage.Customs, day = 4),
        Sample("Handmade oak bookshelf with matching side table and drawers", "bpost", "bpost", TrackingStage.InTransit, day = 6),
        Sample(null, "Royal Mail", "royal-mail", null),
        Sample("Single-origin coffee beans", "Swiss Post", "swiss-post", TrackingStage.InTransit),
        Sample("Kids' science kit", "Evri", "evri", TrackingStage.FailedAttempt),
        Sample("Phone case", "InPost", "inpost", TrackingStage.ReadyForPickup),
        Sample("Garden hose reel", "La Poste", "la-poste", TrackingStage.Exception),
        Sample("Paperback novels", "Poste Italiane", "poste-italiane", TrackingStage.Delivered, delivered = Duration.ofHours(3)),
        Sample("Desk lamp", "Correos", "correos-spain", TrackingStage.Delivered, delivered = Duration.ofHours(20)),
    )

    /**
     * Where the app's list puts a sample parcel: those held up or waiting for the reader first, then the one due today,
     * the others on their way, then those delivered. The one ready for pickup is its Next up.
     */
    private fun section(sample: Sample): ParcelSectionKind = when (sample.stage) {
        TrackingStage.Customs, TrackingStage.FailedAttempt, TrackingStage.ReadyForPickup, TrackingStage.Exception -> ParcelSectionKind.Attention
        TrackingStage.Delivered -> ParcelSectionKind.Delivered
        else -> if (sample.day == 0L) ParcelSectionKind.Today else ParcelSectionKind.Active
    }

    /** The sample as the store would publish it in `localizer`'s language at `now`, in the phone's zone. */
    fun snapshot(localizer: Localizer, now: Instant): DeliveryWidgetSnapshot {
        val zone = ZoneId.systemDefault()
        val today = now.atZone(zone).toLocalDate()
        val parcels = samples.mapIndexed { index, sample ->
            val key = sample.stage?.localizationKey ?: "status.unannounced"
            val status = localizer.text(key)
            val arrived = sample.delivered?.let { now.minus(it) }
            val estimate = sample.day?.let { offset ->
                val day = today.plusDays(offset)
                val hours = sample.hours
                val words = if (hours == null) {
                    localizer.deliveryEstimate(null, day.toString(), now)
                } else {
                    fun at(time: LocalTime) = day.atTime(time).atZone(zone).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    localizer.deliveryEstimate(at(hours.first), at(hours.second), now)
                }
                localizer.text("parcel.expectedDay", mapOf("date" to words))
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
                stage = DeliveryWidgetStage.of(sample.stage, status, localizer.widgetShortStatus(key, status)),
                named = sample.name != null,
                section = section(sample).rawValue,
                nextUp = sample.stage == TrackingStage.ReadyForPickup,
                carrierId = sample.carrierId,
                dayAlone = (estimate != null && sample.stage !in setOf(null, TrackingStage.Pending, TrackingStage.Registered)
                    && section(sample) != ParcelSectionKind.Attention).takeIf { it },
            )
        }
        return DeliveryWidgetSnapshot(now, localizer.language.rawValue, parcels)
    }
}
