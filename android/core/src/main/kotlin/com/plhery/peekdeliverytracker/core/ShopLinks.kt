package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ShopLinkParcel
import com.plhery.peekdeliverytracker.core.api.ShopLinkResponse
import com.plhery.peekdeliverytracker.core.api.UnresolvedShopLink
import java.io.ByteArrayOutputStream
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer

// The web's `useShopLinkReading` and `ShopLinkNotice`, and the scraper's `shopTrackingLinks`: a shop's tracking-page
// link (parcelLab's, as H&M's "Follow package") pasted, typed or shared into Add where the text holds no number of its
// own. Peek asks the shop's platform what it names: a parcel fills the number in, with its carrier; none yet, and Add
// says so and, signed in, offers to wait for the shop ([ShopWaitsStore.keep]). The link opens a page with the reader's
// name and address: it goes in the bodies of those requests alone, never to a log, analytics or anything kept.

internal const val SHOP_LINKS = "/api/shop-links"

/**
 * The scraper's `shopTrackingLinks`, the app's own: the links in a text to a shop's tracking page that Peek reads, a
 * shop's own page on parcelLab (`delivery-status.com` and its subdomains) or parcelLab's portal (`prtl.parcellab.com`
 * under `/trackings/`), over http or https. A link's host and path are read as a browser's URL parser reads them, so a
 * lookalike (`delivery-status.com.example.test`, a host after a backslash or an `@`) is no link; a host that isn't ASCII
 * once percent-decoded is none either, where the scraper would map it first.
 */
object ShopTrackingLinks {
    /** The most links one text is read for, as the scraper's `MOST_LINKS`. */
    private const val MOST_LINKS = 200

    /** The most links the server reads in one text (its `MOST_SHOP_LINKS`): what Add sends of a text. */
    const val MOST_SENT = 3

    // JavaScript's `\s`, written out, with the `<>"'` that end a link: neither the JVM's nor Android's `\s` is
    // JavaScript's, and `https` is spelt out in either case for the same reason, rather than by a flag.
    private const val ENDS = "\\t\\n\\u000B\\f\\r \\u00A0\\u1680\\u2000-\\u200A\\u2028\\u2029\\u202F\\u205F\\u3000\\uFEFF<>\"'"
    private val candidate = Regex("[hH][tT][tT][pP][sS]?://[^$ENDS]+")
    private val ampersand = Regex("&[aA][mM][pP];")

    /** What a link's end drops before it is read: the punctuation prose closes it with. */
    private const val TRAILING = ">'\"),;.!?]"

    // A host's code points no URL takes (WHATWG's forbidden domain code points), after percent-decoding.
    private const val FORBIDDEN = " #%/:<>?@[\\]^|"

    /**
     * The shop tracking-page links [text] holds, each once, in the order written: in prose and in an HTML email's
     * `href`s alike, with HTML's `&amp;` read as `&`, and without the punctuation that closes them.
     */
    fun links(text: String): List<String> {
        val found = LinkedHashSet<String>()
        for (match in candidate.findAll(text).take(MOST_LINKS)) {
            link(match.value.replace(ampersand, "&"))?.let(found::add)
        }
        return found.toList()
    }

    /**
     * What Add sends of [text] to read or wait for its shop links: the first [MOST_SENT], one to a line, as the server
     * reads no more; none where it holds none. The rest of the text, a shipping email's words, stays on the phone.
     */
    fun query(text: String): String? = links(text).take(MOST_SENT).takeIf { it.isNotEmpty() }?.joinToString("\n")

    /** `raw` without its closing punctuation, where it is a shop's tracking-page link (the scraper's `shopTrackingLink`). */
    internal fun link(raw: String): String? {
        val text = raw.dropLastWhile { it in TRAILING }
        val (host, path) = hostAndPath(text) ?: return null
        val shopPage = host == "delivery-status.com" || host.endsWith(".delivery-status.com")
        val portal = host == "prtl.parcellab.com" && path.length >= PORTAL.length && path.substring(0, PORTAL.length).asciiLowercase() == PORTAL
        return if (shopPage || portal) text else null
    }

    private const val PORTAL = "/trackings/"

    /**
     * The host and path an http or https URL names, as WHATWG's parser reads them (`new URL`): the slashes and
     * backslashes after the scheme skipped, the authority up to a slash, backslash, `?` or `#`, the host after its last
     * `@` and before its port (digits up to 65535), percent-decoded and lowercased; the path with backslashes as
     * slashes and its dot segments resolved. Null for no such URL, or a host in brackets or not ASCII.
     */
    private fun hostAndPath(text: String): Pair<String, String>? {
        val colon = text.indexOf(':')
        if (colon < 0) return null
        val scheme = text.substring(0, colon).asciiLowercase()
        if (scheme != "http" && scheme != "https") return null
        var start = colon + 1
        while (start < text.length && (text[start] == '/' || text[start] == '\\')) start++
        val authorityEnd = text.indexOfAny(charArrayOf('/', '\\', '?', '#'), start).let { if (it < 0) text.length else it }
        val authority = text.substring(start, authorityEnd)
        val hostAndPort = authority.substring(authority.lastIndexOf('@') + 1)
        if (hostAndPort.startsWith("[")) return null
        val portStart = hostAndPort.indexOf(':')
        if (portStart >= 0) {
            val port = hostAndPort.substring(portStart + 1)
            if (!port.all { it in '0'..'9' }) return null
            val digits = port.trimStart('0')
            if (digits.length > 5 || (digits.isNotEmpty() && digits.toInt() > 65_535)) return null
        }
        val host = domain(if (portStart >= 0) hostAndPort.substring(0, portStart) else hostAndPort) ?: return null
        val pathEnd = text.indexOfAny(charArrayOf('?', '#'), authorityEnd).let { if (it < 0) text.length else it }
        return host to pathname(text.substring(authorityEnd, pathEnd))
    }

    /** The host `raw` names once percent-decoded and lowercased: null when empty, not ASCII, or holding what no host may. */
    private fun domain(raw: String): String? {
        val bytes = ByteArrayOutputStream()
        var index = 0
        while (index < raw.length) {
            val character = raw[index]
            val high = raw.getOrNull(index + 1)?.let(::hexDigit)
            val low = raw.getOrNull(index + 2)?.let(::hexDigit)
            if (character == '%' && high != null && low != null) {
                bytes.write(high * 16 + low)
                index += 3
                continue
            }
            if (character.code >= 0x80) return null
            bytes.write(character.code)
            index++
        }
        val decoded = bytes.toByteArray()
        if (decoded.isEmpty() || decoded.any { it < 0 }) return null
        val host = String(decoded, Charsets.US_ASCII)
        if (host.any { it.code < 0x20 || it.code == 0x7F || it in FORBIDDEN }) return null
        return host.asciiLowercase()
    }

    /** The path as `URL.pathname` reads it: a slash, then the segments with `.` and `..` (or `%2e`) resolved. */
    private fun pathname(rest: String): String {
        val body = if (rest.startsWith("/") || rest.startsWith("\\")) rest.substring(1) else rest
        val segments = mutableListOf<String>()
        val parts = body.split('/', '\\')
        for ((index, part) in parts.withIndex()) {
            val last = index == parts.lastIndex
            when (part.asciiLowercase()) {
                "..", ".%2e", "%2e.", "%2e%2e" -> {
                    segments.removeLastOrNull()
                    if (last) segments += ""
                }
                ".", "%2e" -> if (last) segments += ""
                else -> segments += part
            }
        }
        return "/" + segments.joinToString("/")
    }

    /** An ASCII hex digit's value, as percent-decoding reads one. */
    private fun hexDigit(character: Char): Int? = when (character) {
        in '0'..'9' -> character - '0'
        in 'a'..'f' -> character - 'a' + 10
        in 'A'..'F' -> character - 'A' + 10
        else -> null
    }

    private fun String.asciiLowercase(): String = buildString(length) {
        for (character in this@asciiLowercase) append(if (character in 'A'..'Z') character + ('a' - 'A') else character)
    }
}

/** The links Add reads in what its field holds: none where the field holds a number of its own (the web's `applies`). */
val ParcelAddition.shopLinkQuery: String?
    get() = if (parsed.trackingNumber.isEmpty()) ShopTrackingLinks.query(trackingInput) else null

/** Where Add's reading of a shop's link stands (the web's `ShopLinkReading`), and what its answer says. */
sealed interface ShopLinkReading {
    /** Peek asks the shop's platform. */
    data object Reading : ShopLinkReading

    /** The link names a parcel: its number fills the field in, with its carrier where the catalog knows it. */
    data class Found(val trackingNumber: String, val carrier: CarrierId?) : ShopLinkReading

    /** The link names no parcel yet, or none Peek can read: [link] says why and for which shop. */
    data class Unresolved(val link: UnresolvedShopLink) : ShopLinkReading

    /** The shop's platform did not answer, or Peek didn't. */
    data object Failed : ShopLinkReading

    companion object {
        /**
         * What an answer says (`readShopLink(...).then`): its first parcel, the carrier where it is the catalog's and not
         * `unknown`; else its first link left unresolved; else that the platform didn't answer.
         */
        fun answer(response: ShopLinkResponse, catalog: CarrierCatalog = CarrierCatalog.shared): ShopLinkReading {
            val parcel = response.parcels.firstOrNull { it.trackingNumber.isNotBlank() }
            if (parcel != null) {
                val carrier = CarrierId(parcel.carrier).takeIf { it != CarrierId.Unknown && it in catalog.definitions }
                return Found(parcel.trackingNumber, carrier)
            }
            return response.unresolved.firstOrNull()?.let(::Unresolved) ?: Failed
        }

        /** A reading as a window keeps it through being made again: its answer, without the link, which the field holds. */
        fun kept(reading: ShopLinkReading): String? = when (reading) {
            Failed -> FAILED
            is Unresolved -> ApiJson.encodeToString(UnresolvedShopLink.serializer(), reading.link)
            else -> null
        }

        fun restored(kept: String): ShopLinkReading? =
            if (kept == FAILED) Failed else runCatching { Unresolved(ApiJson.decodeFromString(UnresolvedShopLink.serializer(), kept)) }.getOrNull()

        private const val FAILED = "failed"
    }
}

/**
 * What Add says under its field while it reads a shop's link, and once it has (the web's `ShopLinkNotice`): [title] in
 * bold over [body]; [calm] in the sheet's quiet ink, a shop that hasn't shipped being no fault, else in the warning
 * colour, a page Peek can't read or that doesn't answer.
 */
data class ShopLinkNotice(val title: String?, val body: String, val calm: Boolean) {
    /** As TalkBack reads it out: the title and its words one after the other, as two lines. */
    val spoken: String get() = title?.let { "$it\n$body" } ?: body
}

/** The shop's name where the platform gives one: an empty one is none. */
private val UnresolvedShopLink.shop: String? get() = shopName?.takeIf { it.isNotEmpty() }

/** What Add says of [this] reading, signed in or not: none once a parcel is found, as the field then holds its number. */
fun ShopLinkReading.notice(localizer: Localizer, signedIn: Boolean): ShopLinkNotice? = when (this) {
    ShopLinkReading.Reading -> ShopLinkNotice(null, localizer.text("shopLink.checking"), calm = true)
    is ShopLinkReading.Found -> null
    ShopLinkReading.Failed -> ShopLinkNotice(null, localizer.text("shopLink.unavailable"), calm = false)
    is ShopLinkReading.Unresolved -> if (!link.waitable) {
        ShopLinkNotice(null, localizer.text("shopLink.unreadable"), calm = false)
    } else {
        val shop = link.shop
        ShopLinkNotice(
            title = if (shop != null) localizer.text("shopLink.noParcelYet", mapOf("shop" to shop)) else localizer.text("shopLink.noParcelYet.unnamed"),
            body = localizer.text(if (signedIn) "shopLink.waitHint" else "shopLink.signIn"),
            calm = true,
        )
    }
}

/**
 * Add's button while it offers to wait for the shop (`canWait`), signed in, for a link that names no parcel yet and
 * can be waited for: "Wait for H&M", or for the shop. Null otherwise, where it adds a parcel.
 */
fun ShopLinkReading?.waitWords(localizer: Localizer, signedIn: Boolean): String? {
    if (!signedIn || this !is ShopLinkReading.Unresolved || !link.waitable) return null
    val shop = link.shop
    return if (shop != null) localizer.text("shopLink.wait", mapOf("shop" to shop)) else localizer.text("shopLink.wait.unnamed")
}

/**
 * [ShopLinkResponse] read leniently: a parcel or a link this version can't read, such as one on a platform a newer
 * server adds, drops out of the answer instead of failing it whole.
 */
object ShopLinkResponseReader : JsonTransformingSerializer<ShopLinkResponse>(ShopLinkResponse.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        val response = element as? JsonObject ?: return element
        return JsonObject(
            response + listOfNotNull(readable(response, "parcels", ShopLinkParcel.serializer()), readable(response, "unresolved", UnresolvedShopLink.serializer())),
        )
    }

    private fun <T> readable(response: JsonObject, key: String, serializer: KSerializer<T>): Pair<String, JsonElement>? {
        val items = response[key] as? JsonArray ?: return null
        return key to JsonArray(items.filter { runCatching { ApiJson.decodeFromJsonElement(serializer, it) }.isSuccess })
    }
}
