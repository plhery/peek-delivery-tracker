package com.plhery.peekdeliverytracker.core

import java.util.Optional
import java.util.concurrent.ConcurrentHashMap
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

/**
 * `Pattern.UNICODE_CHARACTER_CLASS` where java.util.regex takes it, as on the JVM the tests run on, where it reads `\d`,
 * `\w`, `\s` and `\b` as ICU does. Android's java.util.regex is ICU itself, Unicode-aware whatever the flags, and
 * refuses this one (and an inline `(?U)`) with an exception.
 */
internal val unicodeCharacterClass: Int = try {
    Pattern.compile("", Pattern.UNICODE_CHARACTER_CLASS)
    Pattern.UNICODE_CHARACTER_CLASS
} catch (_: IllegalArgumentException) {
    0
}

/**
 * The catalog's patterns as the iPhone app runs them. The shared patterns are JavaScript's, and iOS compiles
 * them with NSRegularExpression, which is ICU; java.util.regex is given ICU's meaning where the two differ:
 * Unicode `\d`, `\w` and `\s`, a `.` that stops at every ICU line end, ICU's `\b`, which looks through
 * combining marks and format characters, and, for Foundation's `replacingOccurrences` with
 * `.regularExpression`, ICU's `$`, which also matches before a final line end of any kind. Foundation's
 * `range(of:options: .regularExpression)` reads `$` as the end of the input, as JavaScript does.
 */
internal object CatalogExpressions {
    /** A match with where it stands in the value, in UTF-16 offsets as NSRange counts them. */
    data class Hit(val start: Int, val end: Int, val text: String)

    // Compiling a pattern costs far more than matching it, and detection runs every carrier rule for
    // each keystroke and card. A pattern that does not compile is remembered too. Keyed by the pattern
    // itself, whose hash the string keeps, so a lookup costs no new key.
    private val sensitive = ConcurrentHashMap<String, Optional<Pattern>>()
    private val insensitive = ConcurrentHashMap<String, Optional<Pattern>>()
    private val foundationPatterns = ConcurrentHashMap<String, Optional<Pattern>>()

    /** `CarrierCatalog.expression`: a shared pattern, whose `$` is the end of the input as in JavaScript. */
    fun expression(pattern: String, caseInsensitive: Boolean = false): Pattern? =
        compile(if (caseInsensitive) insensitive else sensitive, pattern, caseInsensitive) {
            icu(endOfInput(pattern), icuDollar = false)
        }

    /** A pattern as NSRegularExpression compiles it, ICU's `$` included. */
    fun icuExpression(pattern: String): Pattern? = foundation(pattern)

    /** A pattern as Foundation's `replacingOccurrences(of:with:options: .regularExpression)` runs it, `$` included. */
    private fun foundation(pattern: String): Pattern? =
        compile(foundationPatterns, pattern, caseInsensitive = false) { icu(pattern, icuDollar = true) }

    private inline fun compile(
        compiled: ConcurrentHashMap<String, Optional<Pattern>>,
        key: String,
        caseInsensitive: Boolean,
        source: () -> String,
    ): Pattern? {
        compiled[key]?.let { return it.orElse(null) }
        var flags = unicodeCharacterClass
        if (caseInsensitive) flags = flags or Pattern.CASE_INSENSITIVE or Pattern.UNICODE_CASE
        val pattern = try {
            Pattern.compile(source(), flags)
        } catch (_: PatternSyntaxException) {
            null
        } catch (_: IllegalArgumentException) {
            null
        }
        compiled[key] = Optional.ofNullable(pattern)
        return pattern
    }

    /**
     * The shared patterns are JavaScript's, where `$` is the end of the input. ICU's `$` also matches before a
     * final line break, so it becomes `\z` outside character classes.
     */
    fun endOfInput(pattern: String): String {
        val result = StringBuilder(pattern.length + 4)
        var escaped = false
        var inClass = false
        for (character in pattern) {
            if (escaped) {
                escaped = false
            } else if (character == '\\') {
                escaped = true
            } else if (character == '[') {
                inClass = true
            } else if (character == ']') {
                inClass = false
            } else if (character == '$' && !inClass) {
                result.append("\\z")
                continue
            }
            result.append(character)
        }
        return result.toString()
    }

    // ICU's `.` stops at U+000A to U+000D, U+0085, U+2028 and U+2029; Java's lets U+000B and U+000C through.
    private const val ANY = "[^\\n\\x{0B}\\f\\r\\x{85}\\x{2028}\\x{2029}]"

    // ICU's `$`: the end, or before a line end that ends the input, never inside a final CR LF.
    private const val END =
        "(?:(?=\\r\\n\\z)|(?<!\\r)(?=\\n\\z)|(?=[\\x{0B}\\f\\r\\x{85}\\x{2028}\\x{2029}]?\\z))"

    // ICU's `\b` skips Grapheme_Extend and format characters on both sides, then compares ICU's word set,
    // which is UNICODE_CHARACTER_CLASS's `\w`.
    private const val SKIPPED = "\\p{Mn}\\p{Me}\\p{Cf}\\x{FF9E}\\x{FF9F}\\x{09BE}\\x{09D7}\\x{0B3E}\\x{0B57}" +
        "\\x{0BBE}\\x{0BD7}\\x{0CC2}\\x{0CD5}\\x{0CD6}\\x{0D3E}\\x{0D57}\\x{0DCF}\\x{0DDF}\\x{1B35}\\x{302E}" +
        "\\x{302F}\\x{1D165}\\x{1D16E}-\\x{1D172}"
    private const val SKIP = "[$SKIPPED]"
    private const val WORD = "[\\w&&[^$SKIPPED]]"
    private const val WORD_OR_SKIP = "[\\w$SKIPPED]"
    private const val WORD_BEFORE = "$WORD$SKIP{0,16}"
    private const val WORD_BOUNDARY = "(?:(?=$WORD)(?<!$WORD_BEFORE)|(?!$WORD_OR_SKIP)(?<=$WORD_BEFORE))"
    private const val NOT_WORD_BOUNDARY =
        "(?:(?=$SKIP)|(?=$WORD)(?<=$WORD_BEFORE)|(?!$WORD_OR_SKIP)(?<!$WORD_BEFORE))"

    /** Rewrites what ICU and java.util.regex read differently outside character classes. */
    private fun icu(pattern: String, icuDollar: Boolean): String {
        val result = StringBuilder(pattern.length + 16)
        var depth = 0
        var index = 0
        while (index < pattern.length) {
            val character = pattern[index]
            if (character == '\\' && index + 1 < pattern.length) {
                val next = pattern[index + 1]
                when {
                    depth == 0 && next == 'b' -> result.append(WORD_BOUNDARY)
                    depth == 0 && next == 'B' -> result.append(NOT_WORD_BOUNDARY)
                    else -> result.append(character).append(next)
                }
                index += 2
                continue
            }
            when {
                character == '[' -> {
                    depth++
                    result.append(character)
                }
                character == ']' && depth > 0 -> {
                    depth--
                    result.append(character)
                }
                depth == 0 && character == '.' -> result.append(ANY)
                depth == 0 && character == '$' && icuDollar -> result.append(END)
                else -> result.append(character)
            }
            index++
        }
        return result.toString()
    }

    /**
     * The value as java.util.regex is given it. Ignoring case, ICU folds İ to "i̇" and leaves ı alone, so
     * neither is i or I, while java.util.regex lowers İ to i and raises ı to I: both become ƻ, a letter
     * without case. ICU folds the Kelvin sign to k, which `[A-Z]` holds, so it becomes K. Each stands for
     * one UTF-16 unit, so offsets stay, and the text matched is read from the value itself. Two more of ICU's
     * case-insensitive ways stay its own, as they would cost offsets: it matches the ligatures ﬅ and ﬆ as "st", and
     * counts U+0345 as a letter. Android's java.util.regex is ICU, so they differ on the JVM alone.
     */
    private fun subject(value: String, caseInsensitive: Boolean): String {
        if (!caseInsensitive || value.none { it == '\u0130' || it == '\u0131' || it == '\u212A' }) return value
        val subject = StringBuilder(value.length)
        for (character in value) {
            subject.append(
                when (character) {
                    '\u0130', '\u0131' -> '\u01BB'
                    '\u212A' -> 'K'
                    else -> character
                },
            )
        }
        return subject.toString()
    }

    fun matches(value: String, pattern: String, caseInsensitive: Boolean = false): Boolean =
        expression(pattern, caseInsensitive)?.matcher(subject(value, caseInsensitive))?.find() == true

    fun matchesIn(value: String, pattern: String, caseInsensitive: Boolean = false): List<String> =
        hits(value, pattern, caseInsensitive).map { it.text }

    fun hits(value: String, pattern: String, caseInsensitive: Boolean = false): List<Hit> {
        val matcher = expression(pattern, caseInsensitive)?.matcher(subject(value, caseInsensitive)) ?: return emptyList()
        val hits = mutableListOf<Hit>()
        while (matcher.find()) hits += Hit(matcher.start(), matcher.end(), value.substring(matcher.start(), matcher.end()))
        return hits
    }

    fun capture(value: String, pattern: String, caseInsensitive: Boolean = false): String? {
        val matcher = expression(pattern, caseInsensitive)?.matcher(subject(value, caseInsensitive)) ?: return null
        if (!matcher.find() || matcher.groupCount() < 1 || matcher.start(1) < 0) return null
        return value.substring(matcher.start(1), matcher.end(1))
    }

    /** `stringByReplacingMatches` with a template free of `$` and `\`. */
    fun replacing(pattern: String, value: String, template: String, caseInsensitive: Boolean = false): String? {
        val matcher = expression(pattern, caseInsensitive)?.matcher(subject(value, caseInsensitive)) ?: return null
        val result = StringBuilder(value.length)
        var copied = 0
        while (matcher.find()) {
            result.append(value, copied, matcher.start()).append(template)
            copied = matcher.end()
        }
        return result.append(value, copied, value.length).toString()
    }

    /**
     * `value.range(of: pattern, options: .regularExpression) != nil` for the patterns the catalog and its code give it.
     * The value is a native Swift String, which iOS 27 searches with a Swift Regex over Characters, where `$` is the end
     * of the input; this runs ICU over UTF-16 units with that `$` (`expression`). The two agree on these patterns alone:
     * each is anchored at both ends, with ASCII literals and classes, no `\d`, `\w`, `\s` or negated class, and `.` only
     * in a lookahead, so whatever matches is ASCII, a scalar to each Character. CarrierCatalogParityTests holds every
     * pattern to that; another needs `nativeMatches`.
     */
    fun foundationMatches(value: String, pattern: String): Boolean = matches(value, pattern)

    /**
     * `value.range(of: pattern, options: .regularExpression) != nil` on a native Swift String, which iOS 27 runs as a
     * Swift Regex over Characters: a letter or a slash with a combining mark or a joiner is one character, which `.`
     * and `[^/]` take whole and no ASCII literal equals, and CR LF is one line end, which `.` does not take. For
     * patterns of ASCII literals, `.` and classes, each Character of several scalars stands as one none of them names.
     * Not modelled: `\d`, `\w` and `\s`, which a Swift Regex asks of such a Character as a whole ("1\u20E3" is a digit
     * to it, a decomposed "é" a word character) and which take nothing here; no pattern given here has them.
     */
    fun nativeMatches(value: String, pattern: String): Boolean {
        if (value.all { it.code < 0x80 && it != '\r' }) return matches(value, pattern)
        val subject = value.characters().joinToString("") {
            when {
                it == "\r\n" -> "\n"
                it.codePointCount(0, it.length) > 1 -> "\uE000"
                else -> it
            }
        }
        return matches(subject, pattern)
    }

    /** Foundation's `value.replacingOccurrences(of: pattern, with: template, options: .regularExpression)`. */
    fun foundationReplacing(value: String, pattern: String, template: String): String =
        foundation(pattern)?.matcher(value)?.replaceAll(template) ?: value
}
