package com.plhery.peekdeliverytracker.core

import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * A display choice an account keeps with its notification preferences, on until switched off ("Dot on updated parcels",
 * "Peek AI"). This phone keeps the choice; an account's, whenever its notification preferences are read or saved, takes
 * its place, so a choice made on another device arrives. One made here that its account has not taken ([unsaved])
 * stands over that account's until it is saved; one made without an account goes to the account signed in to.
 */
open class AccountChoice(
    private val defaults: UserDefaults,
    private val key: String,
    private val unsavedKey: String,
    /** Where an earlier version kept an unsaved choice without saying whose. */
    private val legacyUnsavedKey: String? = null,
) {
    private val state = MutableStateFlow(defaults.string(forKey = key) != OFF)

    val enabled: StateFlow<Boolean> = state.asStateFlow()

    fun keep(enabled: Boolean) {
        if (state.value == enabled) return
        if (enabled) defaults.removeObject(forKey = key) else defaults.set(OFF, forKey = key)
        state.value = enabled
    }

    /**
     * A choice made here that its account has not taken, made while the server could not take it, or without an account:
     * whose it is ([Unsaved.owner], none without an account) and what it is, so another account signed in meanwhile
     * neither takes it nor loses it.
     */
    var unsaved: Unsaved?
        get() {
            defaults.string(forKey = unsavedKey)?.let { stored ->
                val (owner, choice) = stored.split(' ').takeIf { it.size == 2 } ?: return null
                return Unsaved(if (owner == DEVICE) null else runCatching { UUID.fromString(owner) }.getOrNull() ?: return null, choice != OFF)
            }
            // A choice left unsaved by an earlier version, which didn't say whose: the next account's, as it was then.
            return if (legacyUnsavedKey != null && defaults.bool(forKey = legacyUnsavedKey)) Unsaved(null, state.value) else null
        }
        set(value) {
            legacyUnsavedKey?.let { defaults.removeObject(forKey = it) }
            if (value == null) return defaults.removeObject(forKey = unsavedKey)
            defaults.set("${value.owner?.uuidString ?: DEVICE} ${if (value.enabled) ON else OFF}", forKey = unsavedKey)
        }

    /** A choice made here (`enabled`) that the account `owner` has not taken, or made with no account (`owner` none). */
    data class Unsaved(val owner: UUID?, val enabled: Boolean)

    private companion object {
        const val ON = "on"
        const val OFF = "off"
        const val DEVICE = "device"
    }
}
