package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import java.text.Collator
import java.text.Normalizer

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

    private val combiningMarks = Regex("\\p{Mn}+")

    /** Lower case without accents, one character for each character, so a match can be highlighted. */
    fun fold(value: String): String = value.characters().joinToString("") { character ->
        // Foundation's diacritic- and case-insensitive folding: marks dropped from the decomposed form, then
        // full case folding, which upper-then-lower casing gives for the letters it changes.
        val folded = combiningMarks.replace(Normalizer.normalize(character, Normalizer.Form.NFD), "").uppercase().lowercase()
        if (folded.characterCount == 1) folded else character
    }

    private fun isLetterOrDigit(character: String): Boolean = character.isLetterCharacter || character.isNumberCharacter

    private fun compact(value: String): String = fold(value).characters().filter(::isLetterOrDigit).joinToString("")

    /** The first match of `query` in `text`, as a character offset; only one that starts a word when asked. */
    private fun offset(query: String, text: String, startingWord: Boolean): Int? {
        val characters = text.characters()
        val needle = query.characters()
        if (needle.isEmpty() || needle.size > characters.size) return null
        for (index in 0..(characters.size - needle.size)) {
            if (characters.subList(index, index + needle.size) != needle) continue
            if (!startingWord || index == 0 || !isLetterOrDigit(characters[index - 1])) return index
        }
        return null
    }

    private fun String.hasPrefixCharacters(prefix: String): Boolean {
        val characters = characters()
        val start = prefix.characters()
        return start.size <= characters.size && characters.subList(0, start.size) == start
    }

    private fun String.containsCharacters(other: String): Boolean = offset(other, this, startingWord = false) != null

    fun match(
        carrier: CarrierId,
        definition: CarrierDefinition,
        query: String,
        countryNames: (String) -> List<String>,
    ): Result? {
        val folded = fold(query.trimmingWhitespacesAndNewlines())
        if (folded.isEmpty()) return null
        val squeezed = compact(query)
        val name = fold(definition.displayName)
        val aliases = definition.aliases ?: emptyList()
        fun lit(start: Int, score: Double) = Result(carrier, score, start until start + folded.characterCount, null)
        fun plain(score: Double, alias: String? = null) = Result(carrier, score, null, alias)
        if (name.hasPrefixCharacters(folded)) return lit(0, 0.0)
        offset(folded, name, startingWord = true)?.let { word -> if (word > 0) return lit(word, 1.0) }
        if (squeezed.isNotEmpty() && compact(definition.displayName).hasPrefixCharacters(squeezed)) return plain(1.5)
        aliases.firstOrNull {
            offset(folded, fold(it), startingWord = true) != null || (squeezed.isNotEmpty() && compact(it).hasPrefixCharacters(squeezed))
        }?.let { return plain(2.0, alias = it) }
        offset(folded, name, startingWord = false)?.let { inside -> if (inside > 0) return lit(inside, 3.0) }
        if (squeezed.characterCount > 1 && compact(definition.displayName).containsCharacters(squeezed)) return plain(3.5)
        aliases.firstOrNull { fold(it).containsCharacters(folded) }?.let { return plain(4.0, alias = it) }
        val inCountry = (definition.countries ?: emptyList()).any { code ->
            countryNames(code).any { offset(folded, fold(it), startingWord = true) != null }
        }
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
    ): List<Result> {
        val names = countryNames(language)
        return catalog.selectableCarriers.mapNotNull { carrier ->
            val definition = catalog.info(carrier, language)
            val found = match(carrier, definition, query, names) ?: return@mapNotNull null
            Result(carrier, found.score - (if (carrier in preferred) 0.3 else 0.0), found.highlight, found.alias)
        }.sortedWith { left, right ->
            if (left.score != right.score) return@sortedWith left.score.compareTo(right.score)
            compare(catalog.info(left.carrier, language).displayName, catalog.info(right.carrier, language).displayName, language)
        }
    }

    /** Every name a country is searched by: the reader's and the English one. */
    fun countryNames(language: AppLanguage): (String) -> List<String> = { code ->
        val local = TrackingLocation.countryName(code, language)
        val english = TrackingLocation.countryName(code, AppLanguage.En)
        if (local == english) listOf(local) else listOf(local, english)
    }

    /**
     * `compare(options: [.caseInsensitive, .diacriticInsensitive, .numeric], locale:)`: the language's
     * collation at primary strength, with runs of digits compared by their value.
     */
    private fun compare(left: String, right: String, language: AppLanguage): Int {
        val collator = Collator.getInstance(language.locale).apply { strength = Collator.PRIMARY }
        val leftRuns = runs(left)
        val rightRuns = runs(right)
        for (index in 0 until minOf(leftRuns.size, rightRuns.size)) {
            val a = leftRuns[index]
            val b = rightRuns[index]
            val result = if (a[0] in '0'..'9' && b[0] in '0'..'9') compareNumbers(a, b) else collator.compare(a, b)
            if (result != 0) return result
        }
        return leftRuns.size.compareTo(rightRuns.size)
    }

    private fun runs(value: String): List<String> {
        val runs = mutableListOf<String>()
        var start = 0
        while (start < value.length) {
            val digits = value[start] in '0'..'9'
            var end = start + 1
            while (end < value.length && (value[end] in '0'..'9') == digits) end++
            runs += value.substring(start, end)
            start = end
        }
        return runs
    }

    private fun compareNumbers(left: String, right: String): Int {
        val a = left.trimStart('0')
        val b = right.trimStart('0')
        return if (a.length != b.length) a.length.compareTo(b.length) else a.compareTo(b)
    }

    /** Carriers by initial, in the reader's alphabet; names starting with a digit come last under "#". */
    fun letterSections(catalog: CarrierCatalog, language: AppLanguage): List<LetterSection> {
        val sorted = catalog.selectableCarriers.sortedWith { left, right ->
            compare(catalog.info(left, language).displayName, catalog.info(right, language).displayName, language)
        }
        val sections = mutableListOf<LetterSection>()
        val other = mutableListOf<CarrierId>()
        for (carrier in sorted) {
            val letter = fold(catalog.info(carrier, language).displayName.characterPrefix(1)).uppercase(language.locale)
            if (letter.characterCount != 1 || !letter.isLetterCharacter) {
                other += carrier
                continue
            }
            if (sections.lastOrNull()?.letter == letter) {
                sections[sections.size - 1] = LetterSection(letter, sections[sections.size - 1].carriers + carrier)
            } else {
                sections += LetterSection(letter, listOf(carrier))
            }
        }
        return if (other.isEmpty()) sections else sections + LetterSection("#", other)
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
