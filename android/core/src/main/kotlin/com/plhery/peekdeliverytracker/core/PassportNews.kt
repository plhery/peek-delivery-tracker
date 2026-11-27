package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

/**
 * The stamps and countries this device has shown, one list per account and one for a guest, as the web's
 * `peek-passport-seen:<scope>` keeps them: a sorted JSON array of `stamp:<key>`, `country:<code>` and the
 * [PassportNews.COUNTRIES_ON] marker, kept in the app's preferences and never sent. [changes] counts each write, for the
 * screens to read the list again.
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
 * What the passport holds that this device has not shown yet (the web's `usePassportNews`): the countries reached and
 * the stamps earned since the reader last opened it, given what [seen] lists. With no list kept, nothing is new: the
 * first time a passport is read on a device, everything it holds counts as seen ([PassportSeenRecord]). A list kept
 * before countries counted as news (without [COUNTRIES_ON]) takes every country the book holds now as seen.
 */
class PassportNews(val book: PassportBook, private val seen: Set<String>?) {
    /** A country reached or a stamp earned, the key it is seen by and the parcel that brought it. */
    class Item private constructor(val seenKey: String, val parcelId: UUID?, val stamp: StampProgress?, val country: CountryStamp?) {
        constructor(stamp: StampProgress) : this(stamp.seenKey, stamp.parcelId, stamp, null)
        constructor(country: CountryStamp) : this(country.seenKey, country.parcelId, null, country)
    }

    /** Everything the passport holds now: the countries in the order they were reached, then the earned stamps in page order. */
    val earned: List<Item> = book.countries.map(::Item) + book.stamps.filter { it.earned }.map(::Item)

    /** A list kept before countries counted, which takes the countries the book holds now as seen. */
    private val beforeCountries: Boolean = seen != null && COUNTRIES_ON !in seen

    /** Earned and not shown yet, countries first. */
    val unseen: List<Item> = if (seen == null) {
        emptyList()
    } else {
        earned.filter { it.seenKey !in seen && !(beforeCountries && it.country != null) }
    }

    /** The unseen ones each delivered parcel brought. */
    val byParcel: Map<UUID, List<Item>> = unseen.filter { it.parcelId != null }.groupBy { it.parcelId!! }

    /** The keys of everything held now and the marker, which opening the passport marks seen. */
    val earnedKeys: List<String> get() = earned.map { it.seenKey } + COUNTRIES_ON

    /**
     * What to keep once the parcels have loaded, or null when the list stands: everything held, for a passport read on
     * this device for the first time; the list with every country held now and the marker, for one kept before countries
     * counted.
     */
    val catchUp: List<String>?
        get() = when {
            seen == null -> earnedKeys
            beforeCountries -> (seen + book.countries.map { it.seenKey } + COUNTRIES_ON).toList()
            else -> null
        }

    companion object {
        /** In a list once countries count as news. */
        const val COUNTRIES_ON = "countries:on"
    }
}
