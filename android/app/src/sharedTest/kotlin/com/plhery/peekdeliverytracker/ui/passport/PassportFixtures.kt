package com.plhery.peekdeliverytracker.ui.passport

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Duration
import java.time.Instant
import java.util.UUID

/**
 * Made-up parcels for the passport, seen from Zurich: made-up numbers and labels, each scan's place only its country's
 * code (as a carrier may write it; "Hub, DE" would read as Delaware), so each first scan and delivery counts there.
 */
object PassportFixtures {
    private val carriers = listOf(CarrierId.SwissPost, CarrierId.Dpd, CarrierId.Ups)
    private val origins = listOf("CH", "DE", "FR", "IT", "AT")

    /** The first of the days the parcels below are delivered on, a Monday in March, mid-morning in Zurich. */
    private val firstDay: Instant = Instant.parse("2026-03-02T09:00:00Z")

    /**
     * Parcel `index`: first scanned in [from] at [accepted] and delivered at [delivered] in [to] (somewhere unnamed
     * when null), waiting at a pickup point a minute before where [pickup]; still on its way while [delivered] is null.
     */
    fun parcel(
        index: Int,
        from: String,
        accepted: Instant,
        delivered: Instant?,
        to: String? = null,
        carrier: CarrierId = CarrierId.SwissPost,
        pickup: Boolean = false,
        label: String = "Parcel ${index + 1}",
    ): Parcel {
        val id = UUID.fromString("00000000-0000-4000-8000-%012d".format(index + 1))
        var scan = 0
        fun event(stage: TrackingStage, at: Instant, location: String?) = TrackingEvent(
            id = UUID.fromString("00000000-0000-4000-9000-%06d%06d".format(index + 1, ++scan)), packageId = id, stage = stage,
            description = stage.name, location = location, occurredAt = at.toString(),
        )
        val events = buildList {
            add(event(TrackingStage.Accepted, accepted, from))
            if (delivered != null) {
                if (pickup) add(event(TrackingStage.ReadyForPickup, delivered.minusSeconds(60), null))
                add(event(TrackingStage.Delivered, delivered, to))
            }
        }
        return Parcel(
            id = id, trackingNumber = "DEMOPASSPORT%04d".format(index + 1), label = label, carrier = carrier,
            createdAt = accepted.minusSeconds(3_600).toString(), lastSyncedAt = "2026-10-29T05:00:00Z", syncStatus = SyncStatus.Ok,
            notificationsMuted = false, trackingEvents = events,
        )
    }

    /**
     * Twenty-five parcels that earn every stamp: three carriers, first scanned in five countries, three delivered each
     * day, one delivered in its own country and one from Germany to Switzerland, one picked up, one after 31 days, one
     * in December, and the fastest within 36 hours.
     */
    fun everyStamp(): List<Parcel> = (0 until 25).map { index ->
        val day = firstDay.plus(Duration.ofDays(index / 3L))
        val delivered = if (index == 4) Instant.parse("2025-12-03T10:00:00Z") else day.plusSeconds(index * 60L)
        val wait = when (index) {
            3 -> Duration.ofDays(31)
            else -> Duration.ofHours(36L + index)
        }
        parcel(
            index, origins[index % origins.size], delivered.minus(wait), delivered,
            to = when (index) {
                0 -> "CH"
                1 -> "CH"
                else -> null
            },
            carrier = carriers[index % carriers.size], pickup = index == 2,
        )
    }

    /**
     * Twenty-four parcels just short of the stamps they can miss: two carriers, first scanned in four countries, two
     * delivered a day, the fastest 48 hours and a minute after its first scan, none delivered in a country it names, none
     * picked up, none waited for 30 days, none in December.
     */
    fun justShort(): List<Parcel> = (0 until 24).map { index ->
        val delivered = firstDay.plus(Duration.ofDays(index / 2L)).plusSeconds(index * 60L)
        parcel(
            index, origins[index % 4], delivered.minus(Duration.ofHours(48).plusMinutes(1).plusHours(index.toLong())), delivered,
            carrier = carriers[index % 2],
        )
    }

    /** [count] Swiss Post parcels first scanned and delivered in Switzerland, 20 hours apart, one a day. */
    fun delivered(count: Int): List<Parcel> = (0 until count).map { index ->
        val delivered = firstDay.plus(Duration.ofDays(index.toLong()))
        parcel(index, "CH", delivered.minus(Duration.ofHours(20)), delivered, to = "CH")
    }

    /** Parcels first scanned in the countries [counts] names, as many from each as it says, delivered somewhere unnamed. */
    fun fromCountries(vararg counts: Pair<String, Int>): List<Parcel> {
        var index = 0
        return counts.flatMap { (country, count) ->
            (0 until count).map {
                val delivered = firstDay.plus(Duration.ofDays(index.toLong()))
                parcel(index++, country, delivered.minus(Duration.ofHours(30)), delivered)
            }
        }
    }
}
