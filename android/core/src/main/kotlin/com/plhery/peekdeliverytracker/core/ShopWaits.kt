package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.ShopPlatform
import com.plhery.peekdeliverytracker.core.api.ShopWait
import com.plhery.peekdeliverytracker.core.api.ShopWaitsResponse
import java.time.Duration
import java.time.Instant
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer

// The web's `useShopWaits` and `ShopWaitCards`: a shop's tracking-page link (parcelLab's, as H&M's "Follow package")
// names no parcel before the shop ships, so the server keeps it as a wait and asks again every few hours for 14 days.
// It then adds the parcels under the wait's name, and the wait goes, or marks it expired for the reader to remove.

internal const val SHOP_WAITS = "/api/shop-waits"

/** The waits' requests of `DeliveryApiClient`, for the account signed in: the app's client, or a test's fake. */
interface ShopWaitsClient {
    /** The account's waits, oldest first, as the server keeps them. */
    suspend fun list(): List<ShopWait>

    /** Stops waiting: one already gone counts as done. */
    suspend fun remove(id: UUID)

    /** Waits for the parcels of the shop links in [text], named [label]: the account's waits for those links. */
    suspend fun keep(text: String, label: String): List<ShopWait>
}

/** The waits' requests through the app's API client. */
fun DeliveryApiClient.shopWaitsClient(): ShopWaitsClient = object : ShopWaitsClient {
    override suspend fun list() = shopWaits()

    override suspend fun remove(id: UUID) = removeShopWait(id)

    override suspend fun keep(text: String, label: String) = keepShopWait(text, label)
}

/**
 * [ShopWaitsResponse] read leniently: a wait this version can't read, such as one on a platform a newer server adds,
 * drops out of the list instead of failing the whole answer.
 */
object ShopWaitsResponseReader : JsonTransformingSerializer<ShopWaitsResponse>(ShopWaitsResponse.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        val response = element as? JsonObject ?: return element
        val waits = response[KEY] as? JsonArray ?: return element
        return JsonObject(response + (KEY to JsonArray(waits.filter(::readable))))
    }

    private fun readable(element: JsonElement): Boolean =
        try {
            ApiJson.decodeFromJsonElement(ShopWait.serializer(), element)
            true
        } catch (_: Exception) {
            false
        }

    private const val KEY = "shop_waits"
}

/** Whose waits the list shows: the demo's one, or a signed-in account's. A guest has none. */
sealed interface ShopWaitsOwner {
    data object Demo : ShopWaitsOwner

    data class Account(val id: UUID) : ShopWaitsOwner

    companion object {
        fun of(state: AuthenticationState): ShopWaitsOwner? = when (state) {
            AuthenticationState.Demo -> Demo
            is AuthenticationState.SignedIn -> Account(state.user.id)
            else -> null
        }
    }
}

/** The demo's wait's id, the same at every launch. */
val DEMO_SHOP_WAIT_ID: UUID = UUID.fromString("5f1e0c3a-7b2d-4c8e-9a61-0d4b8e2f7c15")

/** The demo's wait (the web's `demoShopWait`): a wool coat H&M hasn't shipped, kept a day ago, asked about for 13 more days. */
fun demoShopWait(now: Instant = AppClock.now()): ShopWait = ShopWait(
    id = DEMO_SHOP_WAIT_ID,
    platform = ShopPlatform.Parcellab,
    platformName = "parcelLab",
    shopName = "H&M",
    label = "Wool coat",
    createdAt = DateParser.isoString(now.minus(Duration.ofDays(1))),
    expiresAt = DateParser.isoString(now.plus(Duration.ofDays(13))),
    expiredAt = null,
)

/** Peek stopped asking without a parcel: the 14 days passed. */
val ShopWait.isExpired: Boolean get() = expiredAt != null

/**
 * What a wait's card says (the web's `ShopWaitCard`): the shop and its platform where a parcel's card names its carrier
 * ("H&M via parcelLab"), the name the parcel will take (else the shop, else the platform), whom it waits for, until when
 * Peek asks, in the list's short date ("Wed 9 Dec"), and its button. Once expired, it says so plainly and offers to remove it.
 */
data class ShopWaitWords(val via: String, val title: String, val state: String, val detail: String, val action: String)

fun ShopWait.words(localizer: Localizer, now: Instant = AppClock.now()): ShopWaitWords {
    val shop = shopName?.takeIf { it.isNotEmpty() }
    val platform = mapOf("platform" to platformName)
    val expired = isExpired
    val until = DateParser.date(expiresAt)?.let { localizer.shortDate(it, now) } ?: expiresAt
    return ShopWaitWords(
        via = if (shop != null) localizer.text("shopWait.via", platform + ("shop" to shop)) else localizer.text("shopWait.via.unnamed", platform),
        title = label.ifEmpty { shop ?: platformName },
        state = when {
            expired && shop != null -> localizer.text("shopWait.expired", mapOf("shop" to shop))
            expired -> localizer.text("shopWait.expired.unnamed")
            shop != null -> localizer.text("shopWait.status", mapOf("shop" to shop))
            else -> localizer.text("shopWait.status.unnamed")
        },
        detail = if (expired) localizer.text("shopWait.expiredHint") else localizer.text("shopWait.until", mapOf("date" to until)),
        action = localizer.text(if (expired) "shopWait.remove" else "shopWait.stop"),
    )
}

/**
 * The waits the list shows, kept for the process: the demo's one ([demo], none in a build or test without it), or the
 * account's as last read. A wait the reader removes goes at once; one that goes without them became parcels, which
 * [read] and [remove] say, for the list to read its parcels again.
 */
class ShopWaitsStore(private val client: ShopWaitsClient, private val demo: () -> ShopWait? = { demoShopWait() }) {
    /** Whose waits were last shown, and the waits. */
    data class Shown(val owner: ShopWaitsOwner, val waits: List<ShopWait>)

    private val shownState = MutableStateFlow<Shown?>(null)

    val shownFlow: StateFlow<Shown?> = shownState.asStateFlow()

    /** The waits [owner] has, as last read or changed; none for anyone else, or a guest. */
    fun waits(owner: ShopWaitsOwner?): List<ShopWait> = shownState.value.waits(owner)

    /** The ids the last read found, to tell which went without the reader. */
    private var known: Set<UUID> = emptySet()

    /** The waits the reader removed, which a read that began before the server forgot them doesn't bring back. */
    private val removed = mutableSetOf<UUID>()

    /** Bumped as the owner changes: work begun for one never finishes for another. */
    private var generation = 0

    /** Bumped as each read begins: one that answers after a newer one began is set aside. */
    private var reads = 0

    /** The list shows [owner]'s waits: the demo's own wait on entering the demo, an account's none until read. */
    fun show(owner: ShopWaitsOwner?) {
        if (owner != null && shownState.value?.owner == owner) return
        generation += 1
        known = emptySet()
        removed.clear()
        shownState.value = when (owner) {
            null -> null
            ShopWaitsOwner.Demo -> Shown(owner, listOfNotNull(demo()))
            is ShopWaitsOwner.Account -> Shown(owner, emptyList())
        }
    }

    /**
     * Reads the account's waits, once the list shows them ([show]). True when one went without the reader removing it.
     * A failure leaves what shows: the next read tries again.
     */
    suspend fun read(owner: ShopWaitsOwner?): Boolean {
        if (owner !is ShopWaitsOwner.Account || shownState.value?.owner != owner) return false
        val started = generation
        val read = ++reads
        val next = try {
            client.list()
        } catch (error: CancellationException) {
            throw error
        } catch (_: Exception) {
            return false
        }
        if (started != generation || read != reads) return false
        val ids = next.mapTo(HashSet()) { it.id }
        val gone = known.any { it !in ids && it !in removed }
        known = ids
        shownState.value = Shown(owner, next.filter { it.id !in removed })
        return gone
    }

    /**
     * Waits for the shop links in [text], named [label], for [owner] (Add's "Wait for H&M"): the waits the server kept
     * join the list at once, then the account's are read again. True when that read found one gone without the reader.
     * A failure to keep throws, and leaves the list as it was.
     */
    suspend fun keep(owner: ShopWaitsOwner.Account, text: String, label: String): Boolean {
        show(owner)
        val started = generation
        val kept = client.keep(text, label)
        val shown = shownState.value
        if (started != generation || shown == null) return false
        val added = kept.filter { wait -> wait.id !in removed && shown.waits.none { it.id == wait.id } }
        shownState.value = shown.copy(waits = shown.waits + added)
        return read(owner)
    }

    /**
     * Stops waiting for [id]: it leaves the list at once, then the server forgets it. If that fails, it comes back where
     * it stood and the waits are read again; true when that read found one gone without the reader.
     */
    suspend fun remove(owner: ShopWaitsOwner?, id: UUID): Boolean {
        val shown = shownState.value?.takeIf { owner != null && it.owner == owner } ?: return false
        val index = shown.waits.indexOfFirst { it.id == id }
        if (index < 0) return false
        val wait = shown.waits[index]
        removed += id
        shownState.value = shown.copy(waits = shown.waits.filterNot { it.id == id })
        if (owner !is ShopWaitsOwner.Account) return false
        val started = generation
        try {
            client.remove(id)
            return false
        } catch (error: CancellationException) {
            throw error
        } catch (_: Exception) {
            if (started != generation) return false
            removed -= id
            val current = shownState.value
            if (current != null && current.waits.none { it.id == id }) {
                shownState.value = current.copy(waits = current.waits.toMutableList().apply { add(index.coerceAtMost(size), wait) })
            }
            return read(owner)
        }
    }
}

/** What [ShopWaitsStore.Shown] holds for [owner]: none for anyone else, or a guest. */
fun ShopWaitsStore.Shown?.waits(owner: ShopWaitsOwner?): List<ShopWait> =
    this?.takeIf { owner != null && it.owner == owner }?.waits.orEmpty()
