package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.Parcel
import java.util.UUID
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
 * made on another device arrives. One made here that its account has not taken ([unsaved]) stands over that account's
 * until it is saved; one made without an account goes to the account signed in to. What was seen is kept either way.
 */
class MarkNewUpdatesChoice(private val defaults: UserDefaults) {
    private val state = MutableStateFlow(defaults.string(forKey = KEY) != OFF)

    val enabled: StateFlow<Boolean> = state.asStateFlow()

    fun keep(enabled: Boolean) {
        if (state.value == enabled) return
        if (enabled) defaults.removeObject(forKey = KEY) else defaults.set(OFF, forKey = KEY)
        state.value = enabled
    }

    /**
     * A choice made here that its account has not taken, made while the server could not take it, or without an account:
     * whose it is ([Unsaved.owner], none without an account) and what it is, so another account signed in meanwhile
     * neither takes it nor loses it.
     */
    var unsaved: Unsaved?
        get() {
            defaults.string(forKey = UNSAVED_KEY)?.let { stored ->
                val (owner, choice) = stored.split(' ').takeIf { it.size == 2 } ?: return null
                return Unsaved(if (owner == DEVICE) null else runCatching { UUID.fromString(owner) }.getOrNull() ?: return null, choice != OFF)
            }
            // A choice left unsaved by an earlier version, which didn't say whose: the next account's, as it was then.
            return if (defaults.bool(forKey = UNSAVED_V1_KEY)) Unsaved(null, state.value) else null
        }
        set(value) {
            defaults.removeObject(forKey = UNSAVED_V1_KEY)
            if (value == null) return defaults.removeObject(forKey = UNSAVED_KEY)
            defaults.set("${value.owner?.uuidString ?: DEVICE} ${if (value.enabled) ON else OFF}", forKey = UNSAVED_KEY)
        }

    /** A choice made here (`enabled`) that the account `owner` has not taken, or made with no account (`owner` none). */
    data class Unsaved(val owner: UUID?, val enabled: Boolean)

    private companion object {
        const val KEY = "sdt.markNewUpdates.v1"
        const val ON = "on"
        const val OFF = "off"
        const val DEVICE = "device"
        const val UNSAVED_V1_KEY = "sdt.markNewUpdatesUnsaved.v1"
        const val UNSAVED_KEY = "sdt.markNewUpdatesUnsaved.v2"
    }
}
