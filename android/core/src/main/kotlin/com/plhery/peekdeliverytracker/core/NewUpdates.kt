package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.Parcel

// "New since you last looked" (#72): a parcel whose latest scan reached Peek after its reader last opened it carries a
// dot on its card. The server's side, `news_at`, `seen_at` and `POST /api/packages/{packageId}/seen`, is in
// contracts/openapi.json; the web's in src/lib/newUpdate.ts.

/**
 * Whether the parcel's latest scan reached Peek after its reader last opened it: `news_at` later than `seen_at`, two
 * server times compared as moments, never with this device's clock. A parcel without either has nothing new. A parcel
 * followed without an account carries as its `seen_at` the `news_at` this phone last saw ([DeviceParcel.seenNewsAt]).
 */
val Parcel.hasNewUpdate: Boolean
    get() {
        val news = newsAt?.let(DateParser::date) ?: return false
        val seen = seenAt?.let(DateParser::date) ?: return false
        return news > seen
    }

/** Whether the parcel's card shows its dot: a new update, while the cards mark them. */
fun Parcel.marksNewUpdate(markNewUpdates: Boolean): Boolean = markNewUpdates && hasNewUpdate

/**
 * Whether cards mark their parcel's new updates ("Dot on updated parcels" in Settings), on until switched off, as an
 * [AccountChoice]. A choice left unsaved by an earlier version, which didn't say whose, goes to the next account.
 */
class MarkNewUpdatesChoice(defaults: UserDefaults) :
    AccountChoice(defaults, key = "sdt.markNewUpdates.v1", unsavedKey = "sdt.markNewUpdatesUnsaved.v2", legacyUnsavedKey = "sdt.markNewUpdatesUnsaved.v1")
