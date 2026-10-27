package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant

// From ParcelLinkSheet.swift, the words of a shared parcel's card. The sheet is the app's.

/** Where a shared parcel waits and when it is expected, or the day it arrived. It is a line of its own, so it starts with a capital. */
fun Localizer.sharedParcelDetail(parcel: Parcel, now: Instant = Instant.now()): String? {
    val line = listOfNotNull(parcel.pickupPlace, parcelCompletionDate(parcel, now) ?: parcelDeliveryEstimate(parcel, now))
        .joinToString(" · ")
    val first = line.characterPrefix(1)
    return (first.uppercase(language.locale) + line.substring(first.length)).nonEmpty
}

/**
 * "Arrives today, 13:00–17:00" while a gift is on its way; "Delivered today at 14:12" once it is there. Without a
 * clock time the card's own line gives the day ("Today").
 */
fun Localizer.giftDetail(parcel: Parcel, opened: Boolean, now: Instant = Instant.now()): String? {
    if (opened) {
        val event = parcel.currentEvent ?: return null
        if (event.stage != TrackingStage.Delivered) return null
        if (event.calendarDay != null) return sharedParcelDetail(parcel, now)
        val date = DateParser.date(event.occurredAt) ?: return null
        return text("share.gift.delivered", mapOf("date" to deliveryDate(date, now), "time" to clockTime(date)))
    }
    return parcelDeliveryEstimate(parcel, now)?.let { text("share.gift.arrives", mapOf("date" to it)) }
}
