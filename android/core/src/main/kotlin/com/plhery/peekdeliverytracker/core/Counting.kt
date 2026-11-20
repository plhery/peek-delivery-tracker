package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.InstallSighting
import com.plhery.peekdeliverytracker.core.api.InstallSightingPlatform
import java.time.DateTimeException
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.UUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull

// The web's counting.ts. The counting id (docs/ANALYTICS.md): a random id the app keeps to tell installs that come back
// from one-time ones. DeliveryAnalytics sends it alone to /api/public/seen, at most once a UTC day and again once the
// reader signs in; a new one replaces it after 13 months.

/** What the app keeps under [InstallCounting.STORAGE_KEY]: the id, the UTC day it was made, and when it was last counted. */
@Serializable
data class CountingId(
    val id: String,
    /** The UTC day the id was made, `yyyy-MM-dd`. */
    val made: String,
    /** The UTC day it was last counted, and whether the reader was signed in then. */
    val sent: String? = null,
    val signedIn: Boolean? = null,
)

object InstallCounting {
    const val STORAGE_KEY = "sdt.counting.v1"
    private const val LIFETIME_MONTHS = 13L
    private val ID = Regex("^[A-Za-z0-9_-]{16,64}$")
    private val DAY = Regex("^\\d{4}-\\d{2}-\\d{2}$")

    /** A sighting to send: the id to keep before it goes, and what to keep once the service answered 204. */
    data class Plan(val id: CountingId, val counted: CountingId)

    /**
     * Whether to count this install as seen at `now`: null once it was today, signed in or with the same sign-in. A new
     * id replaces none, or one 13 months old.
     */
    fun plan(stored: CountingId?, now: Instant, signedIn: Boolean, newId: () -> String = { UUID.randomUUID().toString() }): Plan? {
        val today = day(now)
        if (stored != null && stored.sent == today && (stored.signedIn == true || !signedIn)) return null
        val id = if (stored == null || expired(stored, now)) CountingId(newId(), today) else stored
        return Plan(id, id.copy(sent = today, signedIn = signedIn))
    }

    /** What goes to /api/public/seen: the id, the platform, the sign-in and the parcels followed without an account. */
    fun sighting(id: CountingId, signedIn: Boolean, parcels: Int): InstallSighting =
        InstallSighting(id.id, InstallSightingPlatform.Android, signedIn, parcels.coerceIn(0, 100_000))

    /** The kept id, or null when there is none or it is not one. */
    fun read(defaults: UserDefaults): CountingId? {
        val value = runCatching { Json.parseToJsonElement(defaults.string(STORAGE_KEY) ?: return null) }.getOrNull() as? JsonObject ?: return null
        fun string(key: String) = (value[key] as? JsonPrimitive)?.takeIf { it.isString }?.content
        val id = string("id")?.takeIf { ID.matches(it) } ?: return null
        val made = string("made")?.takeIf { DAY.matches(it) && date(it) != null } ?: return null
        return CountingId(id, made, string("sent"), (value["signedIn"] as? JsonPrimitive)?.booleanOrNull)
    }

    fun keep(id: CountingId, defaults: UserDefaults) {
        defaults.set(JsonEncoder().encode(CountingId.serializer(), id).decodeToString(), forKey = STORAGE_KEY)
    }

    private fun day(now: Instant): String = now.atOffset(ZoneOffset.UTC).toLocalDate().toString()

    private fun date(day: String): LocalDate? = try {
        LocalDate.parse(day)
    } catch (_: DateTimeException) {
        null
    }

    private fun expired(stored: CountingId, now: Instant): Boolean {
        val made = date(stored.made) ?: return true
        return !now.isBefore(made.plusMonths(LIFETIME_MONTHS).atStartOfDay(ZoneOffset.UTC).toInstant())
    }
}
