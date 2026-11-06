package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID
import kotlinx.serialization.Serializable

// ShareInbox.swift, and SharedContent's rules from ShareViewController.swift.

/** `SharedParcelDraft`: what another app shared, waiting for Add: the parcel's name and the text to read a number from. */
data class SharedParcelDraft(
    val id: UUID = UUID.randomUUID(),
    val label: String = "",
    val trackingInput: String,
    /** To the microsecond, as the Double a Date is written as reads back. */
    val createdAt: Instant = AppClock.now().truncatedTo(ChronoUnit.MICROS),
)

/**
 * `ShareInbox`: the draft the share card saves and the app's window takes, under the iPhone's key. The iPhone keeps it
 * in its app group, which its extension shares; the card here is the app's own, so it is in the app's own defaults.
 */
object ShareInbox {
    const val key = "sdt.sharedParcelDraft"

    /** How long a draft waits, in seconds: "Open Peek within 10 minutes". */
    private const val lifetime = 600.0

    /** As the iPhone's JSONEncoder writes the draft: every key, the date as seconds since 2001. */
    @Serializable
    private class Stored(
        @Serializable(with = UuidSerializer::class) val id: UUID,
        val label: String,
        val trackingInput: String,
        @Serializable(with = ReferenceDateSerializer::class) val createdAt: Instant,
    )

    /** `save(_:defaults:)`: true once the draft reads back as it was written. */
    fun save(draft: SharedParcelDraft, defaults: UserDefaults): Boolean {
        val data = try {
            JsonEncoder().encode(Stored.serializer(), Stored(draft.id, draft.label, draft.trackingInput, draft.createdAt))
        } catch (_: Exception) {
            return false
        }
        defaults.set(data, forKey = key)
        return defaults.data(forKey = key)?.contentEquals(data) == true
    }

    /**
     * `consume(defaults:now:)`: the draft, once, where it is 0 to 600 seconds old and has text. Any draft is taken away,
     * malformed, from an older version or expired too, so none comes back on another launch.
     */
    fun consume(defaults: UserDefaults, now: Instant = AppClock.now()): SharedParcelDraft? {
        val data = defaults.data(forKey = key) ?: return null
        defaults.removeObject(forKey = key)
        val stored = try {
            decodeJson(Stored.serializer(), data)
        } catch (_: Exception) {
            return null
        }
        val age = now.timeIntervalSinceReferenceDate - stored.createdAt.timeIntervalSinceReferenceDate
        if (age !in 0.0..lifetime || stored.trackingInput.trimmingWhitespacesAndNewlines().isEmpty()) return null
        return SharedParcelDraft(stored.id, stored.label, stored.trackingInput, stored.createdAt)
    }
}

/** `SharedContent`: what was shared, the item's title for the parcel's name, and its links and text, each once, one to a line. */
object SharedContent {
    /** `SharedContent.Read`. */
    data class Read(val label: String, val trackingInput: String)

    /** The name's length, in Characters. */
    const val labelLimit = 80

    /** The text's length, in Characters. */
    const val textLimit = 10_000

    /**
     * `read(_:completion:)`'s rules: the first title with anything but spaces in it, trimmed and cut to 80 Characters,
     * for the name ("" without one); each text trimmed, those with something left once each, in order, joined by new
     * lines and cut to 10 000 Characters. Null when no text is left. The iPhone reads each attachment as a link where it
     * holds one, else as plain text, and the item's own text after them: [texts] come in that order. Texts are the same
     * where Swift's `==` finds them so, canonically.
     */
    fun read(titles: List<String?>, texts: List<String?>): Read? {
        val label = titles.firstNotNullOfOrNull { it?.trimmingWhitespacesAndNewlines()?.nonEmpty }?.characterPrefix(labelLimit) ?: ""
        val parts = mutableListOf<String>()
        for (text in texts) {
            val part = text?.trimmingWhitespacesAndNewlines()?.nonEmpty ?: continue
            if (parts.none { it.equalsCanonically(part) }) parts += part
        }
        val trackingInput = parts.joinToString("\n").characterPrefix(textLimit)
        return if (trackingInput.isEmpty()) null else Read(label, trackingInput)
    }
}
