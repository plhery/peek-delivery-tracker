package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.GiftWords
import com.plhery.peekdeliverytracker.core.api.ParcelShare
import com.plhery.peekdeliverytracker.core.api.ShareParcelRequest
import java.io.IOException
import java.security.SecureRandom
import java.time.Instant
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlin.random.asKotlinRandom
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer

// ParcelShare.swift. The sheet reaches the parcel's link through `ParcelShareModel.Client`, which the app makes of its
// API client, the device's parcels or the demo (`ParcelStore.shareClient(for:)` on iOS).

/** Gift words are kept by the service until delivery; public names can travel after `#`. */
@Serializable
data class ParcelShareWords(
    /** The link carries the parcel's name. */
    val name: Boolean = false,
    /** A gift's note, and who it is from, as typed. */
    val note: String = "",
    val from: String = "",
) {
    /** Swift's `==`, which compares the note and who it is from canonically. */
    internal fun equalsCanonically(other: ParcelShareWords): Boolean =
        name == other.name && note.equalsCanonically(other.note) && from.equalsCanonically(other.from)

    /** The words as a link may carry them: one line each, within their limits. */
    val cleaned: ParcelShareWords
        get() = ParcelShareWords(
            name = name,
            note = ParcelLinkWords.clean(note, limit = ParcelLinkWords.noteLimit) ?: "",
            from = ParcelLinkWords.clean(from, limit = ParcelLinkWords.fromLimit) ?: "",
        )

    /** What is being typed, kept within what a link may carry. Spaces stay until the words are cleaned. */
    val limited: ParcelShareWords
        get() = ParcelShareWords(name = name, note = limit(note, ParcelLinkWords.noteLimit), from = limit(from, ParcelLinkWords.fromLimit))

    private companion object {
        /** Whole Characters while their Unicode scalars fit. A lone surrogate is U+FFFD, as in a Swift String. */
        fun limit(typed: String, limit: Int): String {
            val text = typed.wellFormed()
            if (text.codePointCount(0, text.length) <= limit) return text
            val kept = StringBuilder()
            var used = 0
            for (character in text.characters()) {
                used += character.codePointCount(0, character.length)
                if (used > limit) break
                kept.append(character)
            }
            return kept.toString()
        }
    }
}

/**
 * The words this device keeps for the links it shares, one entry per parcel. They are dropped when sharing stops,
 * when the parcel is deleted and when the account leaves this device.
 */
class ParcelShareNotes(private val defaults: UserDefaults) {
    /** `savedAt` is Swift's `Date` in JSON: seconds since 2001. */
    @Serializable
    private class Entry(val words: ParcelShareWords, val savedAt: Double)

    fun words(parcelId: UUID): ParcelShareWords? = read()[parcelId.uuidString]?.words

    /** Words that say nothing are not kept. */
    fun remember(words: ParcelShareWords, parcelId: UUID, now: Instant = Instant.now()) {
        val entries = read().toMutableMap()
        val cleaned = words.cleaned
        if (cleaned == ParcelShareWords()) {
            entries.remove(parcelId.uuidString)
        } else {
            entries[parcelId.uuidString] = Entry(cleaned, now.timeIntervalSinceReferenceDate)
        }
        write(entries)
    }

    fun forget(parcelId: UUID) {
        val entries = read().toMutableMap()
        if (entries.remove(parcelId.uuidString) == null) return
        write(entries)
    }

    fun forgetAll() {
        defaults.removeObject(forKey = key)
    }

    private fun read(): Map<String, Entry> {
        val data = defaults.data(forKey = key) ?: return emptyMap()
        return runCatching { ApiJson.decodeFromString(format, data.decodeToString()) }.getOrNull() ?: emptyMap()
    }

    private fun write(entries: Map<String, Entry>) {
        if (entries.isEmpty()) {
            forgetAll()
            return
        }
        val kept = entries.entries.sortedByDescending { it.value.savedAt }.take(limit)
        defaults.set(JsonEncoder().encode(format, kept.associate { it.key to it.value }), forKey = key)
    }

    private companion object {
        const val key = "sdt.parcelShareWords.v1"

        /** Only the latest links can still matter. */
        const val limit = 60

        val format = MapSerializer(String.serializer(), Entry.serializer())
    }
}

/** The demo's own links: made up on this device, and leading nowhere. */
class DemoParcelShares(private val defaults: UserDefaults) {
    fun link(parcelId: UUID): ParcelShare? = read()[parcelId.uuidString]

    /** Makes the parcel's link when it has none, else changes what the link shows. */
    fun share(parcelId: UUID, showNumber: Boolean, gift: Boolean, giftWords: GiftWords? = null, now: Instant = Instant.now()): ParcelShare {
        val links = read().toMutableMap()
        var link = links[parcelId.uuidString] ?: ParcelShare(
            id = ParcelLinkRoute.madeUpId(SecureRandom().asKotlinRandom()), showNumber = showNumber, gift = gift,
            createdAt = DateParser.isoString(now),
        )
        link = link.copy(showNumber = showNumber, gift = gift)
        if (giftWords != null) link = link.copy(giftWords = giftWords)
        links[parcelId.uuidString] = link
        write(links)
        return link
    }

    fun stop(parcelId: UUID) {
        val links = read().toMutableMap()
        if (links.remove(parcelId.uuidString) == null) return
        write(links)
    }

    fun reset() {
        defaults.removeObject(forKey = key)
    }

    private fun read(): Map<String, ParcelShare> {
        val data = defaults.data(forKey = key) ?: return emptyMap()
        return runCatching { ApiJson.decodeFromString(format, data.decodeToString()) }.getOrNull() ?: emptyMap()
    }

    private fun write(links: Map<String, ParcelShare>) {
        if (links.isEmpty()) reset() else defaults.set(JsonEncoder().encode(format, links), forKey = key)
    }

    private companion object {
        const val key = "sdt.native.demo.shares.v1"
        val format = MapSerializer(String.serializer(), ParcelShare.serializer())
    }
}

/**
 * Sharing one of the account's parcels: its link, what the link shows, and the way to stop. The link is made when the
 * person shares or copies it, not when the sheet opens: until then nothing about the parcel can be reached from
 * outside the account.
 *
 * Used from the main thread, as the iPhone's model is `@MainActor`.
 */
class ParcelShareModel(
    val parcelId: UUID,
    name: String?,
    private val client: Client,
    private val notes: ParcelShareNotes,
    private val baseUrl: String,
) {
    /** What the service keeps for a link. */
    data class Settings(
        val showNumber: Boolean = false,
        val gift: Boolean = false,
        val giftWords: GiftWords? = null,
    ) {
        /**
         * The body that changes a link through the account, `PUT api/packages/{id}/share`: the switches, and gift words
         * only when there are some. The iPhone's `ParcelStore.shareClient(for:)` writes it inline.
         */
        val request: ShareParcelRequest
            get() = ShareParcelRequest(showNumber = showNumber, gift = gift, giftWords = giftWords)
    }

    enum class Field { ShowNumber, Gift }

    /**
     * How the sheet reaches the parcel's link: through the service, or in the demo. A call the app cancelled must
     * surface as a `CancellationException`, which shows nothing. A network failure, a connection that timed out and an
     * OkHttp `Call.cancel()` from outside the coroutine surface as an `IOException`: "error.connection". A
     * `withTimeout` around the call that runs out (`TimeoutCancellationException`) shows that too.
     */
    class Client(
        /** The parcel's live link, or nil while it is not shared. */
        val current: suspend () -> ParcelShare?,
        /** Makes the link when there is none, else changes it. */
        val share: suspend (Settings) -> ParcelShare,
        /** The link goes blank for good; sharing again makes a new one. */
        val stop: suspend () -> Unit,
    )

    /** The parcel's name, which the link carries when its sharer says so. */
    val name: String? = name?.let(ParcelLinkWords::cleanName)

    private val linkState = MutableStateFlow<ParcelShare?>(null)
    private val loadingState = MutableStateFlow(true)
    private val stoppedState = MutableStateFlow(false)
    private val workingState = MutableStateFlow(false)
    private val failureKeyState = MutableStateFlow<String?>(null)
    private val wordsState = MutableStateFlow(ParcelShareWords())
    private val shownState = MutableStateFlow(Settings())
    private val savingState = MutableStateFlow<Set<Field>>(emptySet())

    val linkFlow: StateFlow<ParcelShare?> = linkState.asStateFlow()
    val loadingFlow: StateFlow<Boolean> = loadingState.asStateFlow()
    val stoppedFlow: StateFlow<Boolean> = stoppedState.asStateFlow()
    val workingFlow: StateFlow<Boolean> = workingState.asStateFlow()
    val failureKeyFlow: StateFlow<String?> = failureKeyState.asStateFlow()
    val wordsFlow: StateFlow<ParcelShareWords> = wordsState.asStateFlow()

    /** `shown` as it changes. */
    val shownFlow: StateFlow<Settings> = shownState.asStateFlow()

    /** The switches on their way to the service, as they change. */
    val savingFlow: StateFlow<Set<Field>> = savingState.asStateFlow()

    var link: ParcelShare?
        get() = linkState.value
        private set(value) {
            linkState.value = value
        }

    /** The link's state is being read. */
    var loading: Boolean
        get() = loadingState.value
        private set(value) {
            loadingState.value = value
        }

    /** Sharing was stopped from this sheet. */
    var stopped: Boolean
        get() = stoppedState.value
        private set(value) {
            stoppedState.value = value
        }

    /** Sharing, copying or stopping is under way. */
    var working: Boolean
        get() = workingState.value
        private set(value) {
            workingState.value = value
        }

    /** The message for what went wrong last, as a localization key. */
    var failureKey: String?
        get() = failureKeyState.value
        private set(value) {
            failureKeyState.value = value
        }

    private var settings = Settings()
        set(value) {
            field = value
            publishSwitches()
        }

    /** Switches on their way to the service: shown at once, and put back if the service refuses. */
    private var saving: Map<Field, Boolean> = emptyMap()
        set(value) {
            field = value
            publishSwitches()
        }

    /** The public name and gift message, as typed. */
    var words: ParcelShareWords
        get() = wordsState.value
        set(value) {
            wordsState.value = value.limited
            remember()
        }

    /** What the device holds for this parcel's link, as far as this sheet knows. */
    private var remembered: ParcelShareWords? = null

    private fun publishSwitches() {
        shownState.value = shown
        savingState.value = saving.keys
    }

    /** The switches as the sheet shows them. */
    val shown: Settings
        get() = Settings(showNumber = saving[Field.ShowNumber] ?: settings.showNumber, gift = saving[Field.Gift] ?: settings.gift)

    fun isSaving(field: Field): Boolean = saving[field] != null

    /** What the link carries: the name when it is shared, and a gift's note and signature. */
    val carried: ParcelLinkWords
        get() {
            val gift = shown.gift
            return ParcelLinkWords(name = if (words.name) name else null, note = if (gift) words.note else null, from = if (gift) words.from else null)
        }

    val savingChanges: Boolean get() = saving.isNotEmpty()

    /** The address to hand out, once there is a link. */
    val address: String?
        get() = link?.let { ParcelLinkRoute.address(id = it.id, words = if (shown.gift) ParcelLinkWords() else carried, baseUrl = baseUrl) }

    /** Reads the parcel's link. Without an answer the sheet offers what it can: sharing makes or finds the link. */
    suspend fun load() {
        try {
            val current = client.current()
            if (!currentCoroutineContext().isActive) return
            if (current != null) adopt(current) else notes.forget(parcelId)
        } catch (error: Exception) {
            if (!currentCoroutineContext().isActive) return
            words = notes.words(parcelId) ?: words
        }
        loading = false
    }

    /** Flips a switch. With a link it is saved at once; without one it is what the next link will show. */
    suspend fun set(field: Field, to: Boolean) {
        val value = to
        failureKey = null
        val next = when (field) {
            Field.ShowNumber -> shown.copy(showNumber = value)
            Field.Gift -> shown.copy(gift = value)
        }
        if (link == null) {
            settings = next
            return
        }
        saving = saving + (field to value)
        try {
            adopt(client.share(next))
        } catch (error: Exception) {
            failureKey = failureKey(error)
        }
        // A later flip of the same switch may be on its way.
        if (saving[field] == value) saving = saving - field
    }

    /** The address to share or copy, with the link made now when there is none. Nil when that failed. */
    suspend fun addressToHandOut(): String? {
        if (loading || working || savingChanges) return null
        val address = address
        if (!shown.gift && address != null) {
            failureKey = null
            return address
        }
        if (!create()) return null
        return this.address
    }

    suspend fun stop() {
        if (working) return
        working = true
        failureKey = null
        try {
            client.stop()
            // The words went out with the link: they go with it.
            notes.forget(parcelId)
            remembered = null
            link = null
            words = ParcelShareWords()
            stopped = true
        } catch (error: Exception) {
            failureKey = failureKey(error)
        }
        working = false
    }

    /** Sharing again makes a new link, showing what the switches say. */
    suspend fun shareAgain() {
        if (create()) stopped = false
    }

    /** Copying or sharing itself failed. */
    fun handOutFailed() {
        failureKey = "link.shareFailed"
    }

    private suspend fun create(): Boolean {
        if (working) return false
        working = true
        failureKey = null
        try {
            var request = shown
            if (request.gift) {
                val clean = carried
                request = request.copy(
                    giftWords = GiftWords(
                        name = clean.name,
                        note = ParcelLinkWords.clean(clean.note ?: "", limit = ParcelLinkWords.noteLimit),
                        from = ParcelLinkWords.clean(clean.from ?: "", limit = ParcelLinkWords.fromLimit),
                    ),
                )
            }
            adopt(client.share(request))
            return true
        } catch (error: Exception) {
            failureKey = failureKey(error)
            return false
        } finally {
            working = false
        }
    }

    /** Takes the link as the service describes it. */
    private fun adopt(answer: ParcelShare) {
        val known = link?.id.equalsCanonically(answer.id)
        link = answer
        settings = Settings(showNumber = answer.showNumber, gift = answer.gift)
        if (known) return
        // A link found again carries what this device kept for it; a new one, what was typed for it.
        val saved = answer.giftWords
        val kept = if (words == ParcelShareWords() && saved == null) notes.words(parcelId) else null
        if (words == ParcelShareWords() && saved != null) {
            words = ParcelShareWords(name = saved.name != null, note = saved.note ?: "", from = saved.from ?: "")
        } else if (kept != null) {
            remembered = kept
            words = kept
        } else {
            remember()
        }
    }

    /** Keeps the words on this device once there is a link to carry them. */
    private fun remember() {
        if (link == null) return
        val cleaned = words.cleaned
        // Swift compares the words canonically. `ParcelShareWords.equals` does not, so the StateFlow holding what is
        // typed takes every edit.
        if (remembered?.let(cleaned::equalsCanonically) == true) return
        remembered = cleaned
        notes.remember(cleaned, parcelId)
    }

    private companion object {
        /**
         * A connection failure is an `IOException`, as OkHttp throws it (Swift's `URLError`), and so is a `withTimeout`
         * that ran out, as `URLError.timedOut` is.
         */
        fun failureKey(error: Throwable): String? = when (error) {
            is TimeoutCancellationException -> "error.connection"
            is CancellationException -> null
            is IOException -> "error.connection"
            is DeliveryApiError.RateLimited -> "error.rateLimited"
            is DeliveryApiError.AuthenticationExpired -> "native.error.authenticationExpired"
            else -> "share.failed"
        }
    }
}

/**
 * Swift's `GiftWords.encode(to:)`: every field goes out, an empty one as null. `JsonEncoder` writes the types named
 * here so.
 */
internal val typesEncodingNil: Set<String> = setOf(GiftWords.serializer().descriptor.serialName)
