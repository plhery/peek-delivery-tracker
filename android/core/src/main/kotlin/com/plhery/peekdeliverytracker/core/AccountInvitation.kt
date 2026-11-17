package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import java.time.Instant
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

// FirstOpenView.swift's `AccountInvitationMemory`, under the iPhone's key in the app's preferences. Its times are kept as
// a `Date` keeps them, in seconds since 2001.

/**
 * When the guest's invitation to an account stands under their parcels. It shows on every visit, and from the sixth it
 * can be closed: then it stays away 30 days, comes back for one visit, and stays away again whether or not it is closed
 * then. One record for this phone, which never leaves it.
 */
class AccountInvitationMemory(private val defaults: UserDefaults, private val now: () -> Instant = AppClock::now) {
    @Serializable
    private data class Memory(
        /** The visits it was shown in. */
        @Required val displays: Int = 0,
        /** When the last visit counted began. */
        val lastShownAt: Double? = null,
        val hiddenUntil: Double? = null,
        /** The end of the visit it came back for. */
        val showingUntil: Double? = null,
    )

    /** Never closed, back after its time away, or within the visit it came back for. */
    val shows: Boolean
        get() {
            val memory = read()
            val now = now().timeIntervalSinceReferenceDate
            val hiddenUntil = memory.hiddenUntil
            if (hiddenUntil == null || now >= hiddenUntil) return true
            return memory.showingUntil?.let { now < it } ?: false
        }

    /** It has a × from its sixth visit on. */
    val closable: Boolean get() = read().displays > closableAfter

    /** The row came on screen. Back after its time away, it stays for this visit and is away again after it. */
    fun seen() {
        val now = now().timeIntervalSinceReferenceDate
        update { memory ->
            val hiddenUntil = memory.hiddenUntil
            val back = if (hiddenUntil != null && now >= hiddenUntil) {
                memory.copy(hiddenUntil = now + away, showingUntil = now + visit)
            } else {
                memory
            }
            // A clock set back starts a visit too.
            val last = back.lastShownAt
            if (last != null && now >= last && now - last < visit) back else back.copy(displays = back.displays + 1, lastShownAt = now)
        }
    }

    fun close() {
        val now = now().timeIntervalSinceReferenceDate
        update { it.copy(hiddenUntil = now + away, showingUntil = null) }
    }

    private fun update(change: (Memory) -> Memory) {
        defaults.set(JsonEncoder().encode(Memory.serializer(), change(read())), forKey = key)
    }

    private fun read(): Memory {
        val data = defaults.data(forKey = key) ?: return Memory()
        return runCatching { ApiJson.decodeFromString(Memory.serializer(), data.decodeToString()) }.getOrNull() ?: Memory()
    }

    companion object {
        private const val key = "sdt.accountInvitation.v1"

        /** The row seen again within this, in seconds, is the same visit, counted once. */
        const val visit = 30 * 60.0

        /** The visits it is shown in before it can be closed. */
        const val closableAfter = 5

        /** How long it stays away, in seconds. */
        const val away = 30 * 86_400.0
    }
}
