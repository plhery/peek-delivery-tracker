package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

/**
 * The stamps (and, later, countries) this device has shown, one list per account and one for a guest, as the web's
 * `peek-passport-seen:<scope>` keeps them: a sorted JSON array of `stamp:<key>`, kept in the app's preferences and never
 * sent. [changes] counts each write, for the screens to read the list again.
 */
class PassportSeenRecord(private val defaults: UserDefaults) {
    private val writes = MutableStateFlow(0)

    /** Each write to any scope's list, counted. */
    val changes: StateFlow<Int> = writes.asStateFlow()

    /** The keys shown on this device in [scope]; null when nothing, or something unreadable, is kept. */
    fun seen(scope: String): Set<String>? {
        val text = defaults.string(forKey = key(scope)) ?: return null
        return runCatching { ApiJson.decodeFromString(format, text).toSet() }.getOrNull()
    }

    /** [keys] are what [scope] has shown now, every one of them. */
    fun store(scope: String, keys: Collection<String>) {
        val value = ApiJson.encodeToString(format, keys.toSortedSet().toList())
        if (defaults.string(forKey = key(scope)) == value) return
        defaults.set(value, forKey = key(scope))
        writes.value += 1
    }

    companion object {
        private val format = ListSerializer(String.serializer())

        private fun key(scope: String) = "peek-passport-seen:$scope"

        /** The list's scope: the account signed in, else the guest's (the demo's too). */
        fun scope(state: AuthenticationState): String = (state as? AuthenticationState.SignedIn)?.user?.id?.toString() ?: "guest"
    }
}

/**
 * What the passport holds that this device has not shown yet (the web's `usePassportNews`): the stamps earned since the
 * reader last opened it, given what [seen] lists. With no list kept, nothing is new: the first time a passport is read
 * on a device, everything it holds counts as seen ([PassportSeenRecord]).
 */
class PassportNews(val book: PassportBook, seen: Set<String>?) {
    /** An earned stamp, the key it is seen by and the parcel that earned it. */
    class Item(val stamp: StampProgress) {
        val seenKey: String get() = stamp.seenKey
        val parcelId: UUID? get() = stamp.parcelId
    }

    /** Everything earned now, in page order. */
    val earned: List<Item> = book.stamps.filter { it.earned }.map(::Item)

    /** Earned and not shown yet, in page order. */
    val unseen: List<Item> = if (seen == null) emptyList() else earned.filter { it.seenKey !in seen }

    /** The unseen ones each delivered parcel brought. */
    val byParcel: Map<UUID, List<Item>> = unseen.filter { it.parcelId != null }.groupBy { it.parcelId!! }

    /** The keys of everything earned now, which opening the passport marks seen. */
    val earnedKeys: List<String> get() = earned.map { it.seenKey }
}
