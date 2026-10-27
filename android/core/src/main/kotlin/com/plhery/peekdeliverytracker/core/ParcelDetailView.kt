package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId

// From ParcelDetailView.swift, what isn't the view. Copying to the clipboard is the app's.

/** Copies a tracking number as its carrier's site takes it. */
object TrackingNumberCopy {
    fun text(number: String, carrier: CarrierId): String =
        if (carrier == CarrierId.Postlogistics) CarrierCatalog.format(number, carrier) else number
}
