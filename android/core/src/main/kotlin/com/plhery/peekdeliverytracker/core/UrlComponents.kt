package com.plhery.peekdeliverytracker.core

import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.nio.charset.CharacterCodingException
import java.nio.charset.CodingErrorAction

/**
 * Foundation's URLComponents as the catalog uses it, after swift-foundation's RFC 3986 parser. A string holding
 * characters a URL cannot is not refused: `URLComponents(string:)` percent-encodes each component that holds one
 * (a `%` that starts no escape included, and then every `%` of that component), gives a host that is not ASCII
 * its IDNA form, and reads the URL that makes. It is nil only for a scheme or port that cannot be one, text after
 * an IP literal, or a host IDNA refuses. The getters return the components without their percent-encoding: nil
 * (the path: empty) when that is not UTF-8. The setters encode what the component does not allow.
 */
class UrlComponents private constructor(
    val scheme: String?,
    val percentEncodedUser: String?,
    val percentEncodedPassword: String?,
    /** Nil when the URL has no authority. */
    internal val encodedHost: String?,
    /** The port as written, digits only but for `addressbook:`. */
    internal val portString: String?,
    percentEncodedPath: String,
    percentEncodedQuery: String?,
    percentEncodedFragment: String?,
    /** The string as read, until a setter changes a component. */
    private var parsedString: String?,
) {
    /** Setting one takes it as it is, already encoded. */
    var percentEncodedPath: String = percentEncodedPath
        set(value) {
            field = value
            parsedString = null
        }

    var percentEncodedQuery: String? = percentEncodedQuery
        set(value) {
            field = value
            parsedString = null
        }

    var percentEncodedFragment: String? = percentEncodedFragment
        set(value) {
            field = value
            parsedString = null
        }

    /** A host with a `%` is percent-decoded (`%00` kept); any other is IDNA-decoded, nil if that fails. */
    val host: String?
        get() {
            val host = encodedHost ?: return null
            if (host.isEmpty()) return ""
            return if ('%' in host) host.removingUrlPercentEncoding(keepingNul = true) else decodedHostName(host)
        }

    var path: String
        get() = percentEncodedPath.removingUrlPercentEncoding() ?: ""
        set(value) {
            percentEncodedPath = encodedPath(value)
        }

    var query: String?
        get() = percentEncodedQuery?.removingUrlPercentEncoding()
        set(value) {
            percentEncodedQuery = value?.addingPercentEncoding(QUERY)
        }

    var fragment: String?
        get() = percentEncodedFragment?.removingUrlPercentEncoding()
        set(value) {
            percentEncodedFragment = value?.addingPercentEncoding(FRAGMENT)
        }

    var queryItems: List<QueryItem>?
        get() {
            val query = percentEncodedQuery ?: return null
            if (query.isEmpty()) return emptyList()
            return query.split('&').map { item ->
                val equals = item.indexOf('=')
                if (equals < 0) {
                    QueryItem(item.removingUrlPercentEncoding() ?: "", null)
                } else {
                    QueryItem(
                        item.substring(0, equals).removingUrlPercentEncoding() ?: "",
                        item.substring(equals + 1).removingUrlPercentEncoding(),
                    )
                }
            }
        }
        set(items) {
            percentEncodedQuery = items?.let(::encodedQuery)
        }

    /** `URLComponents.url`, as a string: nil when the parts make no URL. */
    val url: String?
        get() {
            parsedString?.let { return it }
            if (hasAuthority) {
                if (percentEncodedPath.isNotEmpty() && !percentEncodedPath.startsWith("/")) return null
            } else if (percentEncodedPath.startsWith("//")) {
                return null
            }
            val result = StringBuilder()
            scheme?.let { result.append(it).append(':') }
            if (hasAuthority) result.append("//")
            percentEncodedUser?.let { result.append(it) }
            percentEncodedPassword?.let { result.append(':').append(it) }
            if (percentEncodedUser != null || percentEncodedPassword != null) result.append('@')
            encodedHost?.let { result.append(it) }
            // The port as the number it reads, or as written when it is no number.
            portString?.let { result.append(':').append(it.toLongOrNull()?.toString() ?: it) }
            result.append(if (result.isEmpty()) pathWithoutSchemeColon() else percentEncodedPath)
            percentEncodedQuery?.let { result.append('?').append(it) }
            percentEncodedFragment?.let { result.append('#').append(it) }
            return result.toString()
        }

    internal val hasAuthority: Boolean
        get() = encodedHost != null || portString != null || percentEncodedUser != null || percentEncodedPassword != null

    /** A path that would start the URL encodes the colons of its first segment, which would read as a scheme. */
    private fun pathWithoutSchemeColon(): String {
        val path = percentEncodedPath
        if (path.firstOrNull { it == ':' || it == '/' } != ':' || path.startsWith(":")) return path
        val slash = path.indexOf('/').let { if (it < 0) path.length else it }
        return path.substring(0, slash).replace(":", "%3A") + path.substring(slash)
    }

    data class QueryItem(val name: String, val value: String?)

    /** The components found between the delimiters, before any is checked. */
    private class Parts(
        val scheme: String?,
        val user: String?,
        val password: String?,
        val host: String?,
        val isIpLiteral: Boolean,
        val port: String?,
        val path: String,
        val query: String?,
        val fragment: String?,
    )

    companion object {
        private const val ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        private val SCHEME = (ALPHANUMERIC + "+-.").toSet()
        private val USER = (ALPHANUMERIC + "!$&'()*+,-.;=_~").toSet()
        private val HOST = USER
        private val IP_LITERAL = USER + ':'
        private val ZONE_ID = (ALPHANUMERIC + "-._~").toSet()
        internal val PATH = (ALPHANUMERIC + "!$&'()*+,-./:;=@_~").toSet()
        private val PATH_FIRST_SEGMENT = PATH - ':'
        private val QUERY = PATH + '?'
        private val FRAGMENT = QUERY
        private val QUERY_ITEM = QUERY - setOf('&', '=')

        /** Foundation's `CharacterSet.urlQueryAllowed`. */
        internal val URL_QUERY_ALLOWED = QUERY

        // Schemes whose host Foundation percent-encodes instead of giving it its IDNA form.
        private val percentEncodedHostSchemes = setOf(
            "tel", "telemergencycall", "telprompt", "callto", "facetime", "facetime-prompt", "facetime-audio",
            "facetime-audio-prompt", "imap", "pop", "addressbook", "contact", "phasset", "http+unix", "https+unix",
            "ws+unix", "wss+unix",
        )

        /** `URLComponents(string:)`: nil when the string is no URL, even once encoded. */
        fun parse(string: String): UrlComponents? {
            val parts = split(string) ?: return null
            val invalidUser = parts.user != null && !parts.user.isEncoded(USER)
            val invalidPassword = parts.password != null && !parts.password.isEncoded(USER)
            val invalidHost = parts.host != null && !isValidHost(parts.host, parts.isIpLiteral)
            val invalidPath = !parts.path.isEncoded(PATH)
            val invalidQuery = parts.query != null && !parts.query.isEncoded(QUERY)
            val invalidFragment = parts.fragment != null && !parts.fragment.isEncoded(FRAGMENT)
            if (!invalidUser && !invalidPassword && !invalidHost && !invalidPath && !invalidQuery && !invalidFragment) {
                return make(parts, string)
            }
            val encoded = StringBuilder()
            parts.scheme?.let { encoded.append(it).append(':') }
            if (parts.host != null) encoded.append("//")
            if (parts.user != null) {
                encoded.append(if (invalidUser) parts.user.addingPercentEncoding(USER) else parts.user)
                if (parts.password != null) {
                    encoded.append(':').append(if (invalidPassword) parts.password.addingPercentEncoding(USER) else parts.password)
                }
                encoded.append('@')
            }
            if (parts.host != null) {
                encoded.append(
                    when {
                        !invalidHost -> parts.host
                        parts.isIpLiteral -> encodedIpLiteral(parts.host)?.takeIf { isValidHost(it, true) } ?: return null
                        parts.scheme?.lowercase() in percentEncodedHostSchemes -> parts.host.addingPercentEncoding(HOST)
                        else -> asciiHostName(parts.host)?.takeIf { isValidHost(it, false) } ?: return null
                    },
                )
            }
            // The port as the number it reads ("0443" is 443); one past a 64-bit number, or none, is left out.
            parts.port?.toLongOrNull()?.let { encoded.append(':').append(it) }
            encoded.append(if (invalidPath) encodedPath(parts.path) else parts.path)
            parts.query?.let { encoded.append('?').append(if (invalidQuery) it.addingPercentEncoding(QUERY) else it) }
            parts.fragment?.let {
                encoded.append('#').append(if (invalidFragment) it.addingPercentEncoding(FRAGMENT) else it)
            }
            val result = encoded.toString()
            return make(split(result) ?: return null, result)
        }

        private fun make(parts: Parts, string: String) = UrlComponents(
            scheme = parts.scheme,
            percentEncodedUser = parts.user,
            percentEncodedPassword = parts.password,
            encodedHost = parts.host,
            portString = parts.port,
            percentEncodedPath = parts.path,
            percentEncodedQuery = parts.query,
            percentEncodedFragment = parts.fragment,
            parsedString = string,
        )

        /** The query `queryItems` sets: names and values encoded, `&` and `=` too. */
        private fun encodedQuery(items: List<QueryItem>): String = items.joinToString("&") { item ->
            val name = item.name.addingPercentEncoding(QUERY_ITEM)
            item.value?.let { "$name=${it.addingPercentEncoding(QUERY_ITEM)}" } ?: name
        }

        /** Splits a URL at its delimiters, as RFC3986Parser does: nil for a bad scheme or port, or text after an IP literal. */
        private fun split(string: String): Parts? {
            var index = 0
            var scheme: String? = null
            while (index < string.length) {
                val character = string[index]
                if (character == ':') {
                    // A scheme has at least one character; ":x" is a relative reference.
                    if (index > 0) {
                        scheme = string.substring(0, index)
                        index++
                    }
                    break
                } else if (character in "/?#[]@") {
                    // "[", "]" and "@" say an authority is meant, often followed by a port's ":".
                    index = 0
                    break
                }
                index++
            }
            if (scheme != null && (!scheme[0].isAsciiLetter() || !scheme.all { it in SCHEME })) return null
            if (scheme == null && index == string.length) index = 0

            var user: String? = null
            var password: String? = null
            var host: String? = null
            var isIpLiteral = false
            var port: String? = null
            if (string.startsWith("//", index)) {
                index += 2
                val start = index
                while (index < string.length && string[index] !in "/?#") index++
                val authority = string.substring(start, index)
                var hostStart = 0
                val at = authority.lastIndexOf('@')
                if (at >= 0) {
                    val colon = authority.indexOf(':').takeIf { it in 0 until at }
                    if (colon != null) {
                        user = authority.substring(0, colon)
                        password = authority.substring(colon + 1, at)
                    } else {
                        user = authority.substring(0, at)
                    }
                    hostStart = at + 1
                }
                val bracket = if (authority.startsWith("[", hostStart)) authority.indexOf(']', hostStart) else -1
                var hostEnd = authority.length
                if (bracket >= 0) {
                    isIpLiteral = true
                    hostEnd = bracket + 1
                    if (hostEnd < authority.length) {
                        if (authority[hostEnd] != ':') return null
                        port = authority.substring(hostEnd + 1)
                    }
                } else {
                    val colon = authority.indexOf(':', hostStart)
                    if (colon >= 0) {
                        hostEnd = colon
                        // An empty port is no port.
                        if (colon + 1 < authority.length) port = authority.substring(colon + 1)
                    }
                }
                host = authority.substring(hostStart, hostEnd)
                if (port != null && !port.all { it in '0'..'9' } && scheme != "addressbook") return null
            }

            val pathStart = index
            while (index < string.length && string[index] != '?' && string[index] != '#') index++
            val path = string.substring(pathStart, index)
            var query: String? = null
            var fragment: String? = null
            if (index < string.length) {
                if (string[index] == '?') {
                    val pound = string.indexOf('#', index + 1)
                    if (pound >= 0) {
                        query = string.substring(index + 1, pound)
                        fragment = string.substring(pound + 1)
                    } else {
                        query = string.substring(index + 1)
                    }
                } else {
                    fragment = string.substring(index + 1)
                }
            }
            return Parts(scheme, user, password, host, isIpLiteral, port, path, query, fragment)
        }

        private fun Char.isAsciiLetter(): Boolean = this in 'A'..'Z' || this in 'a'..'z'

        private fun Char.isHexDigit(): Boolean = this in '0'..'9' || this in 'A'..'F' || this in 'a'..'f'

        /** Only allowed characters and complete percent escapes. */
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

        private fun looksLikeIpLiteral(host: String) = host.length >= 2 && host.startsWith("[") && host.endsWith("]")

        private fun isValidHost(host: String, ipLiteral: Boolean): Boolean {
            if (!ipLiteral && !looksLikeIpLiteral(host)) return host.isEncoded(HOST)
            val inner = host.substring(1, host.length - 1)
            // A zone ID follows its delimiter, which must be written "%25".
            val percent = inner.indexOf('%')
            if (percent < 0) return inner.all { it in IP_LITERAL }
            return inner.startsWith("%25", percent) && inner.substring(0, percent).all { it in IP_LITERAL } &&
                inner.substring(percent + 3).isEncoded(ZONE_ID)
        }

        /** An IP literal with its zone ID ("%en0") encoded, the only part that may be. */
        private fun encodedIpLiteral(host: String): String? {
            val percent = host.indexOf('%')
            if (percent < 0) return if (isValidHost(host, true)) host else null
            return host.substring(0, percent) + host.substring(percent, host.length - 1).addingPercentEncoding(ZONE_ID) + "]"
        }

        /** Each component's own characters, `%` encoded too; a first segment that would start the URL keeps no ':'. */
        private fun encodedPath(path: String): String {
            if (path.isEmpty()) return ""
            val slash = path.indexOf('/')
            if (slash < 0) return path.addingPercentEncoding(PATH_FIRST_SEGMENT)
            return path.substring(0, slash).addingPercentEncoding(PATH_FIRST_SEGMENT) +
                path.substring(slash).addingPercentEncoding(PATH)
        }

        // Foundation's ICU host names: nontransitional UTS #46 with the bidi and CONTEXTJ checks, never longer
        // than 2048 bytes. Encoding allows no error; decoding allows the ones about lengths and hyphens. A
        // result that only lowercases ASCII letters gives back the name as written.
        private val decodingErrorsAllowed = setOf(
            "EMPTY_LABEL", "LABEL_TOO_LONG", "DOMAIN_NAME_TOO_LONG", "LEADING_HYPHEN", "TRAILING_HYPHEN", "HYPHEN_3_4",
        )

        private fun asciiHostName(host: String): String? = idnaHost(host, toAscii = true)

        private fun decodedHostName(host: String): String? = idnaHost(host, toAscii = false)

        private fun idnaHost(host: String, toAscii: Boolean): String? {
            if (host.utf8Length > 2048) return null
            val (converted, errors) = Icu.hostNames.convert(host, toAscii)
            if (converted.isEmpty() || converted.utf8Length > 2048) return null
            if (if (toAscii) errors.isNotEmpty() else !decodingErrorsAllowed.containsAll(errors)) return null
            if (toAscii && emptiesLastLabel(host)) return null
            val sameName = converted.length == host.length && converted.indices.all { index ->
                host[index] == converted[index] || (host[index] in 'A'..'Z' && host[index].lowercaseChar() == converted[index])
            }
            return if (sameName) host else converted
        }

        /**
         * ICU's UTF-8 conversion, which Foundation uses, reads the ASCII labels first and the rest as a name of its
         * own, so a last label that maps to nothing ("a.\uFEFF") is an empty label there, unlike in a UTF-16 one.
         */
        private fun emptiesLastLabel(host: String): Boolean {
            if (host.utf8Length > 256) return false
            var labelStart = 0
            var index = 0
            while (index < host.length) {
                val character = host[index]
                if (character.code > 0x7F) break
                // "xn--…" and any "??--" label leave the ASCII reading too.
                if (character == '-' && index == labelStart + 3 && host[index - 1] == '-') break
                if (character == '.') labelStart = index + 1
                index++
            }
            if (index == host.length || labelStart == 0) return false
            return Icu.hostNames.convert(host.substring(labelStart), true).first.isEmpty()
        }

        private val String.utf8Length: Int get() = toByteArray(Charsets.UTF_8).size
    }
}

/**
 * Foundation's URL, for the getters where it differs from URLComponents: `host` is percent-decoded but keeps its IDNA
 * form and drops an IP literal's brackets, `user` is nil when it is not UTF-8, `password`, `query` and `fragment`
 * stay percent-encoded, `path` loses its trailing slashes and is empty when it is not UTF-8, and `pathComponents`
 * decodes each component on its own. A URL is the string `URL(string:)` reads, `absoluteString`.
 */
internal class Url private constructor(internal val components: UrlComponents) {
    val absoluteString: String get() = components.url ?: ""
    val scheme: String? get() = components.scheme
    val user: String? get() = components.percentEncodedUser?.removingUrlPercentEncoding()
    val password: String? get() = components.percentEncodedPassword

    /** Nil without an authority, when the authority is empty ("http:///p"), or when the host is not UTF-8. */
    val host: String?
        get() {
            val host = components.encodedHost ?: return null
            if (host.isEmpty() && authorityIsEmpty) return null
            val name = if (host.length >= 2 && host.startsWith("[") && host.endsWith("]")) host.substring(1, host.length - 1) else host
            return name.removingUrlPercentEncoding(keepingNul = true)
        }

    private val authorityIsEmpty: Boolean
        get() {
            val string = absoluteString
            val start = (components.scheme?.length?.plus(1) ?: 0) + 2
            return start >= string.length || string[start] in "/?#"
        }

    /** `Int(port)`: nil past a 64-bit number. */
    val port: Long? get() = components.portString?.toLongOrNull()

    val path: String
        get() {
            val path = components.percentEncodedPath.removingUrlPercentEncoding() ?: return ""
            var end = path.length
            while (end > 1 && path[end - 1] == '/') end--
            return path.substring(0, end)
        }

    /** "/" for a leading slash, then each component decoded ("" when it is not UTF-8). A last "/" is left out. */
    val pathComponents: List<String>
        get() {
            val path = components.percentEncodedPath
            val segments = path.split('/').filter { it.isNotEmpty() }
            val result = mutableListOf<String>()
            if (path.startsWith("/")) result += "/"
            segments.mapTo(result) { it.removingUrlPercentEncoding() ?: "" }
            if (path.endsWith("/") && segments.isNotEmpty()) result += "/"
            if (result.size > 1 && result.last() == "/") result.removeAt(result.size - 1)
            return result
        }

    val query: String? get() = components.percentEncodedQuery
    val fragment: String? get() = components.percentEncodedFragment

    companion object {
        /** `URL(string:)`. */
        fun parse(string: String): Url? = UrlComponents.parse(string)?.let(::Url)
    }
}

/**
 * `URL.appending(path:)` on a URL's string: the component percent-encoded after the path, one slash between them.
 * The rest of the URL stays as written, its port too. The string as it was when it is no URL.
 */
internal fun String.appendingPath(component: String): String {
    val url = UrlComponents.parse(this) ?: return this
    val string = url.url ?: return this
    val added = component.addingPercentEncoding(UrlComponents.PATH)
    val path = url.percentEncodedPath
    val joined = when {
        path.isEmpty() -> if (url.hasAuthority && !added.startsWith("/")) "/$added" else added
        path.endsWith("/") && added.startsWith("/") -> path + added.substring(1)
        path.endsWith("/") || added.startsWith("/") -> path + added
        else -> "$path/$added"
    }
    var pathStart = url.scheme?.length?.plus(1) ?: 0
    if (string.startsWith("//", pathStart)) {
        pathStart += 2
        while (pathStart < string.length && string[pathStart] !in "/?#") pathStart++
    }
    val pathEnd = pathStart + path.length
    return string.substring(0, pathStart) + joined + string.substring(pathEnd)
}

/**
 * `URL.appending(queryItems:)` on a URL's string: the items after those it has, which are read and encoded again as
 * `queryItems` does (a value that is not UTF-8 is lost).
 */
internal fun String.appendingQueryItems(items: List<UrlComponents.QueryItem>): String {
    val url = UrlComponents.parse(this) ?: return this
    url.queryItems = (url.queryItems ?: emptyList()) + items
    return url.url ?: this
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
internal fun String.removingPercentEncoding(): String? = percentDecoded(keepingNul = false)

/**
 * URLComponents' percent-decoding, which can leave `%00` encoded, as a host's is, and drops a byte order mark
 * that starts the result, as Foundation's UTF-8 decoding does.
 */
private fun String.removingUrlPercentEncoding(keepingNul: Boolean = false): String? =
    percentDecoded(keepingNul)?.removePrefix("\uFEFF")

private fun String.percentDecoded(keepingNul: Boolean): String? {
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
            val byte = high * 16 + low
            if (keepingNul && byte == 0) bytes.write(substring(index, index + 3).toByteArray()) else bytes.write(byte)
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
