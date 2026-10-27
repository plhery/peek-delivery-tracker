package com.plhery.peekdeliverytracker.core

import java.security.MessageDigest
import java.time.Duration
import java.time.Instant
import java.util.UUID
import kotlin.random.Random

// AppRouting.swift. A URL is its string, as `URL(string:)` reads it: one that reads as no URL opens nothing. Swift's
// defaults, `AppConfiguration.current.apiBaseURL` and `.linkHosts`, are the app's to pass. The app hands in what
// reaches it: an App Link's or the custom scheme's intent data, and a push message's data.

/**
 * The web addresses whose links open in the app: the site itself, and the hosts it answered on before, so links
 * shared from there keep working. Those hosts are only ever read from an incoming link. The app calls the API and
 * builds the links it shares on the site's own host.
 */
object SiteLink {
    fun belongs(url: String, baseUrl: String, linkHosts: List<String>): Boolean {
        val link = Url.parse(url) ?: return false
        return belongs(link, Url.parse(baseUrl), linkHosts)
    }

    internal fun belongs(url: Url, baseUrl: Url?, linkHosts: List<String>): Boolean {
        if (url.user != null || url.password != null) return false
        val host = url.host?.lowercased() ?: return false
        if (baseUrl != null && url.scheme == baseUrl.scheme && baseUrl.host?.lowercased()?.equalsCanonically(host) == true &&
            url.port == baseUrl.port
        ) {
            return true
        }
        return url.scheme == "https" && url.port == null && linkHosts.any { it.equalsCanonically(host) }
    }
}

/**
 * A tapped web link can reach the app twice, as a URL to open and as a browsing activity to continue. It opens once.
 * Swift's mutating struct: one instance keeps the last link it saw.
 */
class LinkDeliveries {
    private var last: Pair<String, Instant>? = null

    fun isRepeat(url: String, now: Instant = Instant.now()): Boolean {
        // URL's `==` compares the strings `URL(string:)` makes.
        val link = UrlComponents.parse(url)?.url ?: url
        val last = last
        this.last = link to now
        if (last == null) return false
        val elapsed = Duration.between(last.second, now)
        return last.first == link && !elapsed.isNegative && elapsed < Duration.ofSeconds(1)
    }
}

object FriendInvitationLink {
    fun isInvitation(url: String, baseUrl: String, linkHosts: List<String>): Boolean {
        val link = Url.parse(url) ?: return false
        return isInvitation(link, baseUrl, linkHosts)
    }

    private fun isInvitation(url: Url, baseUrl: String, linkHosts: List<String>): Boolean {
        if (url.user != null || url.password != null) return false
        if (url.scheme?.lowercased() == OAuthFlow.callbackScheme) return url.host?.lowercased() == "invite" && url.path.isEmpty()
        return SiteLink.belongs(url, Url.parse(baseUrl), linkHosts) &&
            (url.path == "/invite" || url.path.hasCharacterPrefix("/i/"))
    }

    fun code(from: String, baseUrl: String, linkHosts: List<String>): String? {
        val url = Url.parse(from.trimmingWhitespacesAndNewlines()) ?: return null
        if (!isInvitation(url, baseUrl, linkHosts)) return null
        if (url.path.hasCharacterPrefix("/i/")) {
            val key = url.path.substring(3)
            return if (validPreviewId(key)) key else null
        }
        val code = url.fragment ?: return null
        if (!validCode(code)) return null
        if (url.query != null) {
            val items = url.components.queryItems ?: emptyList()
            if (items.size != 1 || items[0].name != "preview" || items[0].value != previewHash(code)) return null
        }
        return code
    }

    private val legacyCode = Regex("[a-f0-9]{32}")
    private val previewIdFormat = Regex("[A-Za-z0-9_-]{16}")

    fun validCode(value: String): Boolean = validPreviewId(value) || legacyCode.matches(value)

    private fun validPreviewId(value: String): Boolean = previewIdFormat.matches(value)

    private fun previewHash(code: String): String =
        MessageDigest.getInstance("SHA-256").digest(code.toByteArray(Charsets.UTF_8)).joinToString("") { "%02x".format(it) }

    fun url(code: String, previewId: String? = null, baseUrl: String): String {
        if (previewId != null && validPreviewId(previewId)) {
            val components = UrlComponents.parse(baseUrl.appendingPath("i").appendingPath(previewId))!!
            components.query = null
            components.fragment = null
            return components.url!!
        }
        // Compatibility with servers that have not started returning short IDs.
        val components = UrlComponents.parse(baseUrl.appendingPath("invite"))!!
        // Fragments never travel in HTTP requests or Referer headers.
        components.queryItems = listOf(UrlComponents.QueryItem("preview", previewHash(code)))
        components.fragment = code
        return components.url!!
    }
}

/**
 * A link's public name and gift message. Older links carry them in the fragment; new gift links read the message
 * from the service after delivery.
 */
data class ParcelLinkWords(
    val name: String? = null,
    val note: String? = null,
    val from: String? = null,
) {
    /** The same words as the `#` part of an address, encoded as the site encodes them. Nil when there are none. */
    val fragment: String?
        get() {
            val parts = listOf(
                "n" to name?.let(::cleanName),
                "g" to note?.let { clean(it, limit = noteLimit) },
                "f" to from?.let { clean(it, limit = fromLimit) },
            ).mapNotNull { (key, value) -> value?.let { "$key=${it.addingPercentEncoding(unescaped)}" } }
            return if (parts.isEmpty()) null else parts.joinToString("&")
        }

    companion object {
        /** The longest note and signature a link carries, in Unicode scalars as the site counts them. */
        const val noteLimit = 280
        const val fromLimit = 60

        /** A parcel's name holds 80 UTF-16 units, the length its account accepts. */
        const val nameLimit = 80

        /** Reads `n=<name>&g=<note>&f=<from>`, each percent-encoded and cleaned to what it may hold. */
        operator fun invoke(fragment: String?): ParcelLinkWords {
            val parts = fragment?.splitOnCharacter("&") ?: emptyList()
            fun value(key: String): String? =
                parts.firstOrNull { it.hasCharacterPrefix("$key=") }?.substring(key.length + 1)?.removingPercentEncoding()
            return ParcelLinkWords(
                name = value("n")?.let(::cleanName),
                note = value("g")?.let { clean(it, limit = noteLimit) },
                from = value("f")?.let { clean(it, limit = fromLimit) },
            )
        }

        fun cleanName(value: String): String? = clean(value, limit = nameLimit) { it.length }

        /**
         * One line without control characters, at most `limit` long. Whole characters only, so a cut never leaves
         * half an emoji. `length` measures a Character; by default its Unicode scalars.
         */
        fun clean(value: String, limit: Int, length: (String) -> Int = { it.codePointCount(0, it.length) }): String? {
            val line = StringBuilder()
            var gap = false
            var index = 0
            while (index < value.length) {
                val scalar = value.codePointAt(index)
                index += Character.charCount(scalar)
                val category = Character.getType(scalar).toByte()
                if (hasWhiteSpaceProperty(scalar) || category == Character.CONTROL || category == Character.FORMAT) {
                    gap = line.isNotEmpty()
                } else {
                    if (gap) line.append(' ')
                    gap = false
                    line.appendCodePoint(scalar)
                }
            }
            val text = StringBuilder()
            var used = 0
            for (character in line.toString().characters()) {
                used += length(character)
                if (used > limit) break
                text.append(character)
            }
            return text.toString().trimmingWhitespaces().nonEmpty
        }

        /** What JavaScript's `encodeURIComponent` leaves as it is. */
        private val unescaped = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.!~*'()".toSet()
    }
}

/**
 * A parcel link, `/p/<id>`. The id is the capability. A name someone gave the parcel, and a gift's note and
 * signature, can be read from older fragments or from the service after delivery.
 */
class ParcelLinkRoute(
    val id: String,
    val name: String? = null,
    /** A gift's note and who it is from. Shown only once the parcel is delivered. */
    val note: String? = null,
    val from: String? = null,
    key: String? = null,
) {
    /**
     * The link's owner key, when the site hands a parcel it followed over to this phone with `#k=<key>`. A
     * credential: it goes to the keystore and never into an address or a log. Nil unless it is a key.
     */
    val key: String? = key?.takeIf { validKey(it) }

    fun copy(
        id: String = this.id,
        name: String? = this.name,
        note: String? = this.note,
        from: String? = this.from,
        key: String? = this.key,
    ): ParcelLinkRoute = ParcelLinkRoute(id, name, note, from, key)

    override fun equals(other: Any?): Boolean =
        other is ParcelLinkRoute && id == other.id && name == other.name && note == other.note && from == other.from &&
            key == other.key

    override fun hashCode(): Int = listOf(id, name, note, from, key).hashCode()

    /** Swift's `description` and `debugDescription`: the id alone, never the key. */
    override fun toString(): String = "ParcelLinkRoute($id)"

    companion object {
        private const val alphabet = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"

        // Twelve Characters of the alphabet are twelve of its ASCII letters and digits.
        fun validId(value: String): Boolean = value.length == 12 && value.all { it in alphabet }

        /** An id in the format the service gives a link, for a demo that has no service. */
        fun madeUpId(random: Random = Random.Default): String = String(CharArray(12) { alphabet[random.nextInt(alphabet.length)] })

        /** An owner key as the service makes them: 32 random bytes in base64url, 43 characters. */
        fun validKey(value: String): Boolean =
            value.length == 43 && value.all { it in '0'..'9' || it in 'A'..'Z' || it in 'a'..'z' || it == '-' || it == '_' }

        /** `k=<owner key>` in an address's `#` part; nil when it is missing or not a key. */
        fun key(inFragment: String?): String? {
            val part = inFragment?.splitOnCharacter("&")?.firstOrNull { it.hasCharacterPrefix("k=") } ?: return null
            val value = part.substring(2)
            return if (validKey(value)) value else null
        }

        /** The link's address on the site, with an optional public name. Gift messages never go in URLs. */
        fun address(id: String, words: ParcelLinkWords = ParcelLinkWords(), baseUrl: String): String {
            val components = UrlComponents.parse(baseUrl.appendingPath("p").appendingPath(id))!!
            components.query = null
            components.percentEncodedFragment = ParcelLinkWords(name = words.name).fragment
            return components.url!!
        }

        /** Swift's failable `init?(url:baseURL:linkHosts:)`. */
        operator fun invoke(url: String, baseUrl: String, linkHosts: List<String>): ParcelLinkRoute? {
            val link = Url.parse(url) ?: return null
            if (link.user != null || link.password != null) return null
            val id = if (link.scheme?.lowercased() == OAuthFlow.callbackScheme) {
                if (link.host?.lowercased() != "p" || !link.path.hasCharacterPrefix("/")) return null
                link.path.substring(1)
            } else {
                if (!SiteLink.belongs(link, Url.parse(baseUrl), linkHosts) || !link.path.hasCharacterPrefix("/p/")) return null
                link.path.substring(3)
            }
            if (!validId(id)) return null
            val fragment = link.fragment
            val words = ParcelLinkWords(fragment = fragment)
            return ParcelLinkRoute(id, words.name, words.note, words.from, key(inFragment = fragment))
        }

        /** `n=<percent-encoded name>`, cleaned to what a parcel's name may hold. */
        fun name(inFragment: String?): String? = ParcelLinkWords(fragment = inFragment).name
    }
}

sealed class NativeRoute {
    data class Parcel(val id: UUID) : NativeRoute()

    data class Friend(val id: UUID) : NativeRoute()

    data class Add(val trackingInput: String) : NativeRoute()

    /** A notification about a parcel followed through its link on this phone, without an account. */
    data class Link(val id: String) : NativeRoute()

    companion object {
        /** Swift's `init?(url:)`: the app's own scheme. */
        operator fun invoke(url: String): NativeRoute? {
            val link = Url.parse(url) ?: return null
            if (link.scheme?.lowercased() != OAuthFlow.callbackScheme) return null
            return when (link.host?.lowercased()) {
                "friend" -> link.pathComponents.lastOrNull()?.let(::uuid)?.let(::Friend)
                "parcel" -> link.pathComponents.lastOrNull()?.let(::uuid)?.let(::Parcel)
                "add" -> Add(link.components.queryItems?.firstOrNull { it.name == "tracking" }?.value ?: "")
                else -> null
            }
        }

        /**
         * Swift's `init?(remoteNotification:)`: a push's payload, its values as JSON reads them (a string is a String,
         * a number is not one).
         */
        operator fun invoke(remoteNotification: Map<String, Any?>): NativeRoute? {
            val payload = remoteNotification
            if (payload["kind"] as? String == "friend_accepted") {
                val friendId = (payload["friend_id"] as? String)?.let(::uuid)
                if (friendId != null) return Friend(friendId)
            }
            val linkId = payload["link_id"] as? String
            if (linkId != null) return if (ParcelLinkRoute.validId(linkId)) Link(linkId) else null
            val parcelId = (payload["parcel_id"] as? String)?.let(::uuid) ?: return null
            return Parcel(parcelId)
        }
    }
}

object OAuthFlow {
    const val callbackScheme = "peekdeliverytracker"
    const val callbackHost = "auth-callback"

    val callbackUrl: String get() = "$callbackScheme://$callbackHost"

    private val customProvider = Regex("\\Acustom:[a-z0-9:-]+\\z")

    fun validCustomProvider(identifier: String): Boolean =
        identifier.characterCount <= 50 && customProvider.containsMatchIn(identifier)

    fun authorizationUrl(baseUrl: String, provider: String, codeChallenge: String): String? {
        if (provider != "google" && !validCustomProvider(provider)) return null
        val components = UrlComponents.parse(baseUrl.appendingPath("auth/v1/authorize")) ?: return null
        components.queryItems = listOf(
            UrlComponents.QueryItem("provider", provider),
            UrlComponents.QueryItem("redirect_to", callbackUrl),
            UrlComponents.QueryItem("code_challenge", codeChallenge),
            UrlComponents.QueryItem("code_challenge_method", "s256"),
        )
        return components.url
    }

    fun authorizationCode(from: String): String? {
        val callback = Url.parse(from) ?: return null
        if (callback.scheme?.lowercased() != callbackScheme || callback.host?.lowercased() != callbackHost) return null
        val code = callback.components.queryItems?.firstOrNull { it.name == "code" }?.value
        return code?.takeIf { it.isNotEmpty() }
    }
}
