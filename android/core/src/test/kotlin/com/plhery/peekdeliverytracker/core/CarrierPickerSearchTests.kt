package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** The picker's search over the whole catalog: the same results as the search it replaced, at a letter's pace. */
class CarrierPickerSearchTests {
    private val catalog = CarrierCatalog(cacheFile = null)

    /** Queries for every branch of `match`, accents, scripts, spaces, and pieces of the catalog's own names. */
    private val queries: List<String> by lazy {
        val fixed = listOf(
            "c", "co", "col", "coli", "colis", "zzqx", "ü", "é", "e", "日本", "中国", "россия", "deutschland", "italy",
            "italien", "suisse", "brasil", "colis prive", "colisprive", "c-o", "j&t", "jt", "4px", "dhl express", "die post",
            "hermes uk", "hugger", "post", "a", "ß", "İ", "ﬁ", " dpd ", " ", "1", "17", "ups", "Ł", "ö", "ç", "ñ",
            "😀", "x́", "é", "ö", "\r\n", "  colis\t", "ПОЧТА", "españa", "österreich",
        )
        val index = CarrierPickerSearch.Index(catalog, AppLanguage.En)
        val sampled = (0 until index.size step 131).flatMap { position ->
            val definition = catalog.info(catalog.selectableCarriers[position], AppLanguage.De)
            val name = definition.displayName
            listOfNotNull(name, name.drop(name.length / 3).take(3), definition.aliases?.firstOrNull())
        }
        fixed + sampled
    }

    private val preferred = setOf(CarrierId.ColisPrive, CarrierId.LaPoste, CarrierId.SwissPost, CarrierId.GlsCh, CarrierId.DpdFr)

    @Test
    fun findsWhatTheSearchItReplacedFound() {
        for (language in AppLanguage.entries) {
            val index = CarrierPickerSearch.Index(catalog, language)
            queries.forEachIndexed { number, query ->
                val chosen = if (number % 2 == 0) emptySet() else preferred
                assertEquals(
                    "$language '$query'",
                    ReferenceSearch.search(query, catalog, language, chosen),
                    CarrierPickerSearch.search(query, index, chosen),
                )
            }
        }
    }

    @Test
    fun foldsAsTheRegexDid() {
        val texts = buildList {
            for (scalar in 0..0xFFFF) if (scalar !in 0xD800..0xDFFF) add(String(Character.toChars(scalar)))
            for (language in AppLanguage.entries) {
                for (carrier in catalog.selectableCarriers) {
                    val definition = catalog.info(carrier, language)
                    add(definition.displayName)
                    addAll(definition.aliases.orEmpty())
                    definition.countries.orEmpty().forEach { addAll(CarrierPickerSearch.countryNames(language)(it)) }
                }
            }
            addAll(listOf("é", "각", "🇫🇷", "👩‍💻", "a\r\nb", "\uD800x"))
        }
        for (text in texts) assertEquals(text, ReferenceSearch.fold(text), CarrierPickerSearch.fold(text))
    }

    @Test
    fun sortsLettersAsBefore() {
        for (language in AppLanguage.entries) {
            assertEquals(language.name, ReferenceSearch.letterSections(catalog, language), CarrierPickerSearch.letterSections(catalog, language))
        }
    }

    @Test
    fun searchesEachLetterQuickly() {
        // Typing "colis" a letter at a time, then a query matching nothing, which walks every name, other name and
        // country. The search this replaced took over 10 ms a query on a laptop and seconds on a small phone.
        val typed = listOf("c", "co", "col", "coli", "colis", "zzqx", "日本", "deutschland")
        val index = CarrierPickerSearch.Index(catalog, AppLanguage.Fr)
        fun run() = typed.forEach { CarrierPickerSearch.search(it, index, preferred) }
        repeat(3) { run() }
        val best = (1..5).minOf {
            val start = System.nanoTime()
            run()
            System.nanoTime() - start
        }
        val milliseconds = best / 1_000_000.0
        assertTrue("${typed.size} queries took $milliseconds ms", milliseconds < 50)
    }

    /** The search as it was, which folded every name again for each query. */
    private object ReferenceSearch {
        private val combiningMarks = Regex("\\p{Mn}+")
        private val folds = HashMap<String, String>()

        // Kept from one query to the next only to spare the test's time: folding gives the same for the same text.
        fun fold(value: String): String = folds.getOrPut(value) {
            value.characters().joinToString("") { character ->
                val folded = combiningMarks.replace(character.nfd(), "").uppercase().lowercase()
                if (folded.characterCount == 1) folded else character
            }
        }

        private fun isLetterOrDigit(character: String): Boolean = character.isLetterCharacter || character.isNumberCharacter

        private fun compact(value: String): String = fold(value).characters().filter(::isLetterOrDigit).joinToString("")

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
        ): CarrierPickerSearch.Result? {
            val folded = fold(query.trimmingWhitespacesAndNewlines())
            if (folded.isEmpty()) return null
            val squeezed = compact(query)
            val name = fold(definition.displayName)
            val aliases = definition.aliases ?: emptyList()
            fun lit(start: Int, score: Double) = CarrierPickerSearch.Result(carrier, score, start until start + folded.characterCount, null)
            fun plain(score: Double, alias: String? = null) = CarrierPickerSearch.Result(carrier, score, null, alias)
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

        fun search(query: String, catalog: CarrierCatalog, language: AppLanguage, preferred: Set<CarrierId>): List<CarrierPickerSearch.Result> {
            val names = CarrierPickerSearch.countryNames(language)
            val comparator = LocalizedComparison.Loose.comparator(language.locale)
            return catalog.selectableCarriers.mapNotNull { carrier ->
                val definition = catalog.info(carrier, language)
                val found = match(carrier, definition, query, names) ?: return@mapNotNull null
                CarrierPickerSearch.Result(carrier, found.score - (if (carrier in preferred) 0.3 else 0.0), found.highlight, found.alias)
            }.sortedWith { left, right ->
                if (left.score != right.score) return@sortedWith left.score.compareTo(right.score)
                comparator.compare(catalog.info(left.carrier, language).displayName, catalog.info(right.carrier, language).displayName)
            }
        }

        fun letterSections(catalog: CarrierCatalog, language: AppLanguage): List<CarrierPickerSearch.LetterSection> {
            val comparator = LocalizedComparison.Loose.comparator(language.locale)
            val sorted = catalog.selectableCarriers.sortedWith { left, right ->
                comparator.compare(catalog.info(left, language).displayName, catalog.info(right, language).displayName)
            }
            val sections = mutableListOf<CarrierPickerSearch.LetterSection>()
            val other = mutableListOf<CarrierId>()
            for (carrier in sorted) {
                val letter = fold(catalog.info(carrier, language).displayName.characterPrefix(1)).uppercase(language.locale)
                if (letter.characterCount != 1 || !letter.isLetterCharacter) {
                    other += carrier
                    continue
                }
                if (sections.lastOrNull()?.letter == letter) {
                    sections[sections.size - 1] = CarrierPickerSearch.LetterSection(letter, sections[sections.size - 1].carriers + carrier)
                } else {
                    sections += CarrierPickerSearch.LetterSection(letter, listOf(carrier))
                }
            }
            return if (other.isEmpty()) sections else sections + CarrierPickerSearch.LetterSection("#", other)
        }
    }
}
