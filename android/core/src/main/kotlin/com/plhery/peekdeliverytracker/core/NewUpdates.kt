package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.Parcel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
 * Whether cards mark their parcel's new updates ("Dot on updated parcels" in Settings), on until switched off. This phone
 * keeps the choice; an account's, whenever its notification preferences are read or saved, takes its place, so a choice
 * made on another device arrives. One made here that the account has not taken ([unsaved]) stands over the account's
 * until it is saved. What was seen is kept either way.
 */
class MarkNewUpdatesChoice(private val defaults: UserDefaults) {
    private val state = MutableStateFlow(defaults.string(forKey = KEY) != OFF)

    val enabled: StateFlow<Boolean> = state.asStateFlow()

    fun keep(enabled: Boolean) {
        if (state.value == enabled) return
        if (enabled) defaults.removeObject(forKey = KEY) else defaults.set(OFF, forKey = KEY)
        state.value = enabled
    }

    /** Set while a choice made here, without an account or while the server could not take it, is not the account's. */
    var unsaved: Boolean
        get() = defaults.bool(forKey = UNSAVED_KEY)
        set(value) {
            if (value) defaults.set(true, forKey = UNSAVED_KEY) else defaults.removeObject(forKey = UNSAVED_KEY)
        }

    private companion object {
        const val KEY = "sdt.markNewUpdates.v1"
        const val OFF = "off"
        const val UNSAVED_KEY = "sdt.markNewUpdatesUnsaved.v1"
    }
}
