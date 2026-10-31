package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel

// The logic of CarrierPicker.swift that the carrier catalog's tests cover.

/**
 * What the carrier pickers show: search over names, other names and countries, the A–Z sections, and the
 * carriers someone used before. Mirrors `src/lib/carrierPicker.ts`.
 */
object CarrierPickerSearch {
    data class Result(
        val carrier: CarrierId,
        val score: Double,
        /** Character offsets of the query in the name, when the name itself matched. */
        val highlight: IntRange?,
        /** The other name that matched, when only that name did. */
        val alias: String?,
    )

    data class LetterSection(val letter: String, val carriers: List<CarrierId>)

    /**
     * The catalog's selectable carriers as the picker lists and searches them in one language: in the reader's
     * alphabetical order, and each name, other name and country name folded once, as `match` folds them again for
     * each query on the iPhone, so a letter typed folds only the query. Built again for another catalog or language.
     */
    class Index(catalog: CarrierCatalog, internal val language: AppLanguage) {
        private val carriers = catalog.selectableCarriers
        private val definitions = carriers.map { catalog.info(it, language) }

        /** How many carriers the picker offers (`catalog.selectableCarriers.count`). */
        val size: Int get() = carriers.size

        /** The carriers in the reader's alphabetical order (`compare`), those it calls equal in the catalog's. */
        internal val alphabetical: List<Int> = LocalizedComparison.Loose.comparator(language.locale).let { comparator ->
            carriers.indices.sortedWith { left, right -> comparator.compare(definitions[left].displayName, definitions[right].displayName) }
        }

        internal fun carrier(position: Int): CarrierId = carriers[position]

        internal fun displayName(position: Int): String = definitions[position].displayName

        /** Folded at the first search, in the catalog's order. */
        internal val entries: List<Entry> by lazy {
            val ranks = IntArray(carriers.size)
            alphabetical.forEachIndexed { rank, position -> ranks[position] = rank }
            val names = countryNames(language)
            val countries = HashMap<String, List<Characters>>()
            carriers.indices.map { position ->
                val definition = definitions[position]
                val aliases = definition.aliases ?: emptyList()
                Entry(
                    carriers[position],
                    ranks[position],
                    Characters(fold(definition.displayName)),
                    Characters(compact(definition.displayName)),
                    aliases,
                    aliases.map { Characters(fold(it)) },
                    aliases.map { Characters(compact(it)) },
                    (definition.countries ?: emptyList()).flatMap { code ->
                        countries.getOrPut(code) { names(code).map { Characters(fold(it)) } }
                    },
                )
            }
        }
    }

    /** A carrier's names folded for `match`; [rank] is its place in the reader's alphabetical order. */
    internal class Entry(
        val carrier: CarrierId,
        val rank: Int,
        val name: Characters,
        val compactName: Characters,
        val aliases: List<String>,
        val foldedAliases: List<Characters>,
        val compactAliases: List<Characters>,
        val countryNames: List<Characters>,
    )

    /**
     * A string and where each of its Characters ends, so that matching compares Characters, as Swift's `Array(text)`
     * does, without splitting the string again for each query.
     */
    internal class Characters(val text: String) {
        // None where each UTF-16 unit is a Character, as `characters()` splits ASCII without CR.
        private val ends: IntArray? = if (text.all { it.code < 0x80 && it != '\r' }) null else characterEnds(text)

        val count: Int get() = ends?.size ?: text.length

        private fun end(index: Int): Int = ends?.get(index) ?: (index + 1)

        private fun start(index: Int): Int = if (index == 0) 0 else end(index - 1)

        fun character(index: Int): String = text.substring(start(index), end(index))

        /** Whether `needle`'s Characters are this text's from `index` on. */
        fun matches(needle: Characters, index: Int): Boolean {
            if (needle.count == 0) return true
            val from = start(index)
            if (end(index + needle.count - 1) - from != needle.text.length) return false
            if (!text.regionMatches(from, needle.text, 0, needle.text.length)) return false
            if (ends == null && needle.ends == null) return true
            return (0 until needle.count - 1).all { end(index + it) - from == needle.end(it) }
        }

        fun startsWith(prefix: Characters): Boolean = prefix.count <= count && matches(prefix, 0)

        fun contains(other: Characters): Boolean = offset(other, this, startingWord = false) != null
    }

    /** Lower case without accents, one character for each character, so a match can be highlighted. */
    fun fold(value: String): String {
        // ASCII folds to its lower case, as each of its characters does on its own.
        if (value.all { it.code < 0x80 }) return value.lowercase()
        val folded = StringBuilder(value.length)
        for (character in value.characters()) folded.append(foldCharacter(character))
        return folded.toString()
    }

    private fun foldCharacter(character: String): String {
        if (character.length == 1 && character[0].code < 0x80) return character.lowercase()
        // Foundation's diacritic- and case-insensitive folding: marks dropped from the decomposed form, then
        // full case folding, which upper-then-lower casing gives for the letters it changes.
        val folded = character.nfd().withoutNonspacingMarks().uppercase().lowercase()
        return if (folded.characterCount == 1) folded else character
    }

    /** The text without its nonspacing marks (general category Mn). */
    private fun String.withoutNonspacingMarks(): String {
        val kept = StringBuilder(length)
        var index = 0
        while (index < length) {
            val scalar = codePointAt(index)
            if (Character.getType(scalar).toByte() != Character.NON_SPACING_MARK) kept.appendCodePoint(scalar)
            index += Character.charCount(scalar)
        }
        return kept.toString()
    }

    private fun isLetterOrDigit(character: String): Boolean = character.isLetterCharacter || character.isNumberCharacter

    private fun compact(value: String): String = fold(value).characters().filter(::isLetterOrDigit).joinToString("")

    /** The first match of `query` in `text`, as a character offset; only one that starts a word when asked. */
    private fun offset(query: Characters, text: Characters, startingWord: Boolean): Int? {
        if (query.count == 0 || query.count > text.count) return null
        for (index in 0..(text.count - query.count)) {
            if (!text.matches(query, index)) continue
            if (!startingWord || index == 0 || !isLetterOrDigit(text.character(index - 1))) return index
        }
        return null
    }

    /**
     * `match`: how well a carrier answers the query, [folded] trimmed and [squeezed] to its letters and digits, its
     * score lowered by [bonus].
     */
    private fun match(entry: Entry, folded: Characters, squeezed: Characters, bonus: Double): Result? {
        fun lit(start: Int, score: Double) = Result(entry.carrier, score - bonus, start until start + folded.count, null)
        fun plain(score: Double, alias: String? = null) = Result(entry.carrier, score - bonus, null, alias)
        val name = entry.name
        val aliases = entry.aliases.indices
        if (name.startsWith(folded)) return lit(0, 0.0)
        offset(folded, name, startingWord = true)?.let { word -> if (word > 0) return lit(word, 1.0) }
        if (squeezed.count > 0 && entry.compactName.startsWith(squeezed)) return plain(1.5)
        aliases.firstOrNull {
            offset(folded, entry.foldedAliases[it], startingWord = true) != null ||
                (squeezed.count > 0 && entry.compactAliases[it].startsWith(squeezed))
        }?.let { return plain(2.0, alias = entry.aliases[it]) }
        offset(folded, name, startingWord = false)?.let { inside -> if (inside > 0) return lit(inside, 3.0) }
        if (squeezed.count > 1 && entry.compactName.contains(squeezed)) return plain(3.5)
        aliases.firstOrNull { entry.foldedAliases[it].contains(folded) }?.let { return plain(4.0, alias = entry.aliases[it]) }
        val inCountry = entry.countryNames.any { offset(folded, it, startingWord = true) != null }
        return if (inCountry) plain(5.0) else null
    }

    /**
     * Carriers matching a query, best first: the name's start, a word in it, an other name ("Colissimo",
     * "Hugger"), anywhere in the name, then a country. The carriers that fit the number rank a little higher.
     */
    fun search(
        query: String,
        catalog: CarrierCatalog,
        language: AppLanguage,
        preferred: Set<CarrierId> = emptySet(),
    ): List<Result> = search(query, Index(catalog, language), preferred)

    /** [search] over an [Index] kept from one query to the next. */
    fun search(query: String, index: Index, preferred: Set<CarrierId> = emptySet()): List<Result> {
        val folded = Characters(fold(query.trimmingWhitespacesAndNewlines()))
        if (folded.count == 0) return emptyList()
        val squeezed = Characters(compact(query))
        val found = mutableListOf<Pair<Result, Int>>()
        for (entry in index.entries) {
            val result = match(entry, folded, squeezed, if (entry.carrier in preferred) 0.3 else 0.0) ?: continue
            found += result to entry.rank
        }
        // Equal scores in the reader's alphabetical order, the index's.
        return found.sortedWith { (left, leftRank), (right, rightRank) ->
            if (left.score != right.score) left.score.compareTo(right.score) else leftRank.compareTo(rightRank)
        }.map { it.first }
    }

    /** Every name a country is searched by: the reader's and the English one. */
    fun countryNames(language: AppLanguage): (String) -> List<String> = { code ->
        val local = TrackingLocation.countryName(code, language)
        val english = TrackingLocation.countryName(code, AppLanguage.En)
        if (local == english) listOf(local) else listOf(local, english)
    }

    /** Carriers by initial, in the reader's alphabet; names starting with a digit come last under "#". */
    fun letterSections(catalog: CarrierCatalog, language: AppLanguage): List<LetterSection> =
        letterSections(Index(catalog, language))

    /** [letterSections] in the order an [Index] has already sorted. */
    fun letterSections(index: Index): List<LetterSection> {
        // `compare(options: [.caseInsensitive, .diacriticInsensitive, .numeric], locale:)`, the index's order.
        val sections = mutableListOf<Pair<String, MutableList<CarrierId>>>()
        val other = mutableListOf<CarrierId>()
        for (position in index.alphabetical) {
            val carrier = index.carrier(position)
            val letter = fold(index.displayName(position).characterPrefix(1)).uppercase(index.language.locale)
            if (letter.characterCount != 1 || !letter.isLetterCharacter) {
                other += carrier
                continue
            }
            val last = sections.lastOrNull()
            if (last?.first == letter) last.second += carrier else sections += letter to mutableListOf(carrier)
        }
        val letters = sections.map { (letter, carriers) -> LetterSection(letter, carriers) }
        return if (other.isEmpty()) letters else letters + LetterSection("#", other)
    }

    /**
     * The countries line under a carrier: its first two countries, with how many more. Networks across many
     * countries (Amazon) show none; the line would not tell them apart.
     */
    fun countryLine(countries: List<String>, name: (String) -> String): String {
        if (countries.isEmpty() || countries.size > 5) return ""
        val names = countries.take(2).joinToString(" · ") { name(it) }
        return if (countries.size > 2) "$names +${countries.size - 2}" else names
    }

    /** The query as CarrierPickerView reads it (`trimmedQuery`): the picker searches while it isn't empty. */
    fun trimmedQuery(query: String): String = query.trimmingWhitespacesAndNewlines()

    /**
     * The UTF-16 range of `name` that a result's `highlight`, in characters, covers (`highlighted(_:_:)`): none without
     * one, or for one that runs past the name.
     */
    fun highlightedRange(name: String, highlight: IntRange?): IntRange? {
        val characters = name.characters()
        if (highlight == null || highlight.isEmpty() || highlight.first < 0 || highlight.last >= characters.size) return null
        val start = characters.subList(0, highlight.first).sumOf { it.length }
        return start until start + characters.subList(highlight.first, highlight.last + 1).sumOf { it.length }
    }

    /**
     * The line under a carrier's name in the picker (`row(_:highlight:alias:)`): the other name it was found by, or
     * its countries in the reader's language ([countryLine]).
     */
    fun subtitle(definition: CarrierDefinition, alias: String?, localizer: Localizer): String =
        alias?.let { localizer.text("picker.alias", mapOf("name" to it)) }
            ?: countryLine(definition.countries ?: emptyList()) { TrackingLocation.countryName(it, localizer.language) }

    /** What a carrier's row reads after its name (its `accessibilityValue`): its subtitle, then its tag. */
    fun accessibilityValue(subtitle: String, tag: String?): String =
        listOf(subtitle, tag.orEmpty()).filter { it.isNotEmpty() }.joinToString(", ")

    /** The carriers of someone's latest parcels, newest first, each once. */
    fun usedCarriers(parcels: List<Parcel>, catalog: CarrierCatalog, limit: Int = 3): List<CarrierId> {
        val used = mutableListOf<CarrierId>()
        for (parcel in parcels.sortedWith { left, right -> right.createdAt.compareTo(left.createdAt) }) {
            if (used.size >= limit) break
            if (catalog.info(parcel.carrier).selectable && parcel.carrier !in used) used += parcel.carrier
        }
        return used
    }
}
