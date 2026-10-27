package com.plhery.peekdeliverytracker.core

import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.nio.charset.CharacterCodingException
import java.nio.charset.CodingErrorAction

/**
 * Foundation's URLComponents as the catalog uses it. Like iOS 17's, it parses RFC 3986 strictly: a string
 * with a character no URL component allows, or a broken percent escape, is no URL. The getters return the
 * components without their percent-encoding; the setters encode what the component does not allow.
 */
class UrlComponents private constructor(
    val scheme: String?,
    private val percentEncodedUser: String?,
    /** Nil when the URL has no authority. */
    private val percentEncodedHost: String?,
    private val port: String?,
    private var percentEncodedPath: String,
    private var percentEncodedQuery: String?,
    private val percentEncodedFragment: String?,
) {
    val host: String? get() = percentEncodedHost?.let { it.removingPercentEncoding() ?: "" }

    var path: String
        get() = percentEncodedPath.removingPercentEncoding() ?: ""
        set(value) {
            percentEncodedPath = value.addingPercentEncoding(PATH_ALLOWED)
        }

    val query: String? get() = percentEncodedQuery?.let { it.removingPercentEncoding() ?: "" }

    val fragment: String? get() = percentEncodedFragment?.let { it.removingPercentEncoding() ?: "" }

    var queryItems: List<QueryItem>?
        get() {
            val query = percentEncodedQuery ?: return null
            if (query.isEmpty()) return emptyList()
            return query.split('&').map { item ->
                val equals = item.indexOf('=')
                if (equals < 0) {
                    QueryItem(item.removingPercentEncoding() ?: "", null)
                } else {
                    QueryItem(
                        item.substring(0, equals).removingPercentEncoding() ?: "",
                        item.substring(equals + 1).removingPercentEncoding() ?: "",
                    )
                }
            }
        }
        set(items) {
            percentEncodedQuery = items?.joinToString("&") { item ->
                val name = item.name.addingPercentEncoding(QUERY_ITEM_ALLOWED)
                item.value?.let { "$name=${it.addingPercentEncoding(QUERY_ITEM_ALLOWED)}" } ?: name
            }
        }

    /** `URLComponents.url`, as a string: nil when the parts make no URL. */
    val url: String?
        get() {
            if (percentEncodedHost != null && percentEncodedPath.isNotEmpty() && !percentEncodedPath.startsWith("/")) {
                return null
            }
            if (percentEncodedHost == null && percentEncodedPath.startsWith("//")) return null
            val result = StringBuilder()
            scheme?.let { result.append(it).append(':') }
            if (percentEncodedHost != null) {
                result.append("//")
                percentEncodedUser?.let { result.append(it).append('@') }
                result.append(percentEncodedHost)
                port?.let { result.append(':').append(it) }
            }
            result.append(percentEncodedPath)
            percentEncodedQuery?.let { result.append('?').append(it) }
            percentEncodedFragment?.let { result.append('#').append(it) }
            return result.toString()
        }

    data class QueryItem(val name: String, val value: String?)

    companion object {
        private val reference = Regex("^(?:([^:/?#]+):)?(?://([^/?#]*))?([^?#]*)(?:\\?([^#]*))?(?:#(.*))?$", RegexOption.DOT_MATCHES_ALL)
        private val schemeShape = Regex("^[A-Za-z][A-Za-z0-9+.-]*$")
        private const val UNRESERVED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~"
        private const val SUB_DELIMS = "!$&'()*+,;="
        private val USER_ALLOWED = (UNRESERVED + SUB_DELIMS + ":").toSet()
        private val HOST_ALLOWED = (UNRESERVED + SUB_DELIMS).toSet()
        private val IP_LITERAL_ALLOWED = (UNRESERVED + SUB_DELIMS + ":").toSet()
        private val PATH_ALLOWED = (UNRESERVED + SUB_DELIMS + ":@/").toSet()
        private val QUERY_ALLOWED = (UNRESERVED + SUB_DELIMS + ":@/?").toSet()
        private val QUERY_ITEM_ALLOWED = QUERY_ALLOWED - setOf('&', '=')
        private val ANYWHERE_ALLOWED = (UNRESERVED + SUB_DELIMS + ":/?#[]@").toSet()

        /** Foundation's `CharacterSet.urlQueryAllowed`. */
        internal val URL_QUERY_ALLOWED = QUERY_ALLOWED

        /** `URLComponents(string:)`: nil when the string is not a URL. */
        fun parse(string: String): UrlComponents? {
            val match = reference.matchEntire(string) ?: return null
            val (scheme, authority, path, query, fragment) = match.destructured
            val hasScheme = match.groups[1] != null
            if (hasScheme && !schemeShape.matches(scheme)) return null
            var user: String? = null
            var host: String? = null
            var port: String? = null
            if (match.groups[2] != null) {
                var hostAndPort = authority
                val at = authority.lastIndexOf('@')
                if (at >= 0) {
                    user = authority.substring(0, at)
                    if (!user.isEncoded(USER_ALLOWED)) return null
                    hostAndPort = authority.substring(at + 1)
                }
                if (hostAndPort.startsWith("[")) {
                    val close = hostAndPort.indexOf(']')
                    if (close < 0) return null
                    host = hostAndPort.substring(0, close + 1)
                    if (!host.substring(1, host.length - 1).all { it in IP_LITERAL_ALLOWED }) return null
                    val rest = hostAndPort.substring(close + 1)
                    if (rest.isNotEmpty()) {
                        if (!rest.startsWith(":")) return null
                        port = rest.substring(1)
                    }
                } else {
                    val colon = hostAndPort.lastIndexOf(':')
                    host = if (colon >= 0) hostAndPort.substring(0, colon) else hostAndPort
                    if (colon >= 0) port = hostAndPort.substring(colon + 1)
                    if (!host.isEncoded(HOST_ALLOWED)) return null
                }
                if (port != null && !port.all { it in '0'..'9' }) return null
            }
            if (!path.isEncoded(PATH_ALLOWED)) return null
            if (match.groups[4] != null && !query.isEncoded(QUERY_ALLOWED)) return null
            if (match.groups[5] != null && !fragment.isEncoded(QUERY_ALLOWED)) return null
            return UrlComponents(
                scheme = if (hasScheme) scheme else null,
                percentEncodedUser = user,
                percentEncodedHost = host,
                port = port,
                percentEncodedPath = path,
                percentEncodedQuery = if (match.groups[4] != null) query else null,
                percentEncodedFragment = if (match.groups[5] != null) fragment else null,
            )
        }

        /**
         * `URL(string:)` since iOS 17, which percent-encodes the characters a URL cannot hold before it parses
         * the string, where URLComponents refuses it.
         */
        fun parseEncodingInvalidCharacters(string: String): UrlComponents? {
            val encoded = StringBuilder()
            var index = 0
            while (index < string.length) {
                val character = string[index]
                val escape = character == '%' && index + 2 < string.length &&
                    string[index + 1].isHexDigit() && string[index + 2].isHexDigit()
                if (escape || (character != '%' && character in ANYWHERE_ALLOWED)) {
                    encoded.append(character)
                    index++
                } else {
                    val scalar = string.codePointAt(index)
                    val count = Character.charCount(scalar)
                    encoded.append(string.substring(index, index + count).addingPercentEncoding(emptySet()))
                    index += count
                }
            }
            return parse(encoded.toString())
        }

        private fun Char.isHexDigit(): Boolean = this in '0'..'9' || this in 'A'..'F' || this in 'a'..'f'

        private fun String.isEncoded(allowed: Set<Char>): Boolean {
            var index = 0
            while (index < length) {
                val character = this[index]
                if (character == '%') {
                    if (index + 2 >= length || !this[index + 1].isHexDigit() || !this[index + 2].isHexDigit()) return false
                    index += 3
                    continue
                }
                if (character !in allowed) return false
                index++
            }
            return true
        }
    }
}

/** Foundation's `addingPercentEncoding(withAllowedCharacters:)`: UTF-8 bytes outside the set as %XX. */
internal fun String.addingPercentEncoding(allowed: Set<Char>): String {
    val result = StringBuilder(length)
    var index = 0
    while (index < length) {
        val character = this[index]
        if (character in allowed) {
            result.append(character)
            index++
            continue
        }
        val scalar = codePointAt(index)
        val count = Character.charCount(scalar)
        for (byte in substring(index, index + count).toByteArray(Charsets.UTF_8)) {
            result.append('%').append(HEX[(byte.toInt() shr 4) and 0xF]).append(HEX[byte.toInt() and 0xF])
        }
        index += count
    }
    return result.toString()
}

private const val HEX = "0123456789ABCDEF"

/** Foundation's `removingPercentEncoding`: nil for a broken escape or bytes that are not UTF-8. */
internal fun String.removingPercentEncoding(): String? {
    if (indexOf('%') < 0) return this
    val bytes = ByteArrayOutputStream(length)
    var index = 0
    while (index < length) {
        val character = this[index]
        if (character == '%') {
            if (index + 2 >= length) return null
            val high = Character.digit(this[index + 1], 16)
            val low = Character.digit(this[index + 2], 16)
            if (high < 0 || low < 0 || this[index + 1].code > 0x7F || this[index + 2].code > 0x7F) return null
            bytes.write(high * 16 + low)
            index += 3
        } else {
            val scalar = codePointAt(index)
            val count = Character.charCount(scalar)
            bytes.write(substring(index, index + count).toByteArray(Charsets.UTF_8))
            index += count
        }
    }
    return try {
        Charsets.UTF_8.newDecoder()
            .onMalformedInput(CodingErrorAction.REPORT)
            .onUnmappableCharacter(CodingErrorAction.REPORT)
            .decode(ByteBuffer.wrap(bytes.toByteArray()))
            .toString()
    } catch (_: CharacterCodingException) {
        null
    }
}
