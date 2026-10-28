package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Locale

/**
 * Inputs where Foundation and the JVM part ways, with what the iPhone app answers: each expected value is the
 * output of CarrierCatalog.swift run on iOS 27's Foundation.
 */
class CarrierCatalogParityTests {
    private val catalog = CarrierCatalog(cacheFile = null)

    private fun assertLink(expected: TrackingInputMatch, input: String) {
        assertEquals(expected, catalog.parse(input))
        assertEquals(expected, catalog.read(input))
    }

    private fun linkMatch(number: String, carrier: CarrierId) = TrackingInputMatch(
        trackingNumber = number,
        carrier = carrier,
        confidence = CarrierMatch.Confidence.High,
        candidates = listOf(carrier),
        trackingUrl = null,
        source = TrackingInputMatch.Source.Link,
    )

    @Test
    fun readsLinksFoundationEncodes() {
        // "|" is percent-encoded, not refused, so GLS's link is read.
        assertLink(
            linkMatch("0.10.2.40193547", CarrierId.GlsDe),
            "https://www.gls-pakete.de/sendungsverfolgung?trackingNumber=0.10.2.40193547|0.10.2.40193547",
        )
        // A host that is not ASCII is a link too, whose number is read from it.
        assertLink(linkMatch("0000000000000000000000", CarrierId.CttExpress), "https://münchen.de/t/0000000000000000000000")
        // A query value that is not UTF-8 is nil, so the link's number is found in the rest.
        assertLink(
            linkMatch("0000000000000000000000", CarrierId.CttExpress),
            "https://www.ups.com/track?tracknum=0000000000000000000000%FF",
        )
        val numbers = catalog.numbers("https://münchen.de/t/1ZDEMO202600000001 or 1ZDEMO202600000029")
        assertEquals(listOf(TrackingInputMatch.Source.Link, TrackingInputMatch.Source.Text), numbers.map { it.match.source })
    }

    @Test
    fun aLinkWithAShopsNumberIsNoOrder() {
        assertNull(catalog.order("https://www.gls-pakete.de/sendungsverfolgung?trackingNumber=251015WE1ANOP9|251015WE1ANOP9"))
        assertNull(catalog.order("https://www.gls-pakete.de/sendungsverfolgung?trackingNumber=302-4571983-2294617|302-4571983-2294617"))
        assertNull(catalog.order("https://münchen.de/t/251015WE1ANOP9"))
    }

    @Test
    fun aLineEndAfterTheNumberIsNoNumberToAskAbout() {
        // `range(of:options: .regularExpression)` reads `$` as the end of the input, not before a final line end.
        for (number in listOf("000000000\n", "000000000\r\n", "ABCD\r", "ABCD\u0085", "ABCD\u2028")) {
            assertNull(number, NumberRefusals.question(CarrierId.Dhl, number, demo = false))
        }
        assertEquals(
            NumberRefusalRequest(trackingNumber = "000000000", carrier = CarrierId.Dhl),
            NumberRefusals.question(CarrierId.Dhl, "000000000", demo = false),
        )
    }

    private val nothing = TrackingInputMatch(
        trackingNumber = "",
        carrier = CarrierId.Unknown,
        confidence = CarrierMatch.Confidence.None,
        candidates = emptyList(),
        trackingUrl = null,
        source = TrackingInputMatch.Source.None,
    )

    @Test
    fun ignoringCaseTheTurkishIsAreNoIs() {
        // ICU folds İ to "i̇" and keeps ı, so "trackİng" is no tracking label.
        assertEquals(nothing, catalog.parse("Trackİng NO123456789"))
        assertEquals(nothing, catalog.parse("Your trackİng number İs 12345678901."))
        assertEquals(nothing, catalog.read("trackİng notable1234"))
        // "ıs" is no "is" after the label, and an entry holding a word is no number entered whole: only the number
        // left uncertain is read.
        val entry = "Tracking number ıs 12345678901"
        assertEquals(nothing, catalog.parse(entry))
        val read = catalog.read(entry)
        assertEquals("12345678901", read.trackingNumber)
        assertEquals(CarrierId.Unknown, read.carrier)
        assertEquals(CarrierMatch.Confidence.Low, read.confidence)
        assertEquals(TrackingInputMatch.Source.Text, read.source)
    }

    @Test
    fun ignoringCaseTheKelvinSignIsK() {
        assertEquals(nothing, catalog.parse("Tracking: JJD000\u212A0000000000"))
        assertEquals(nothing, catalog.read("Tracking: JJD000\u212A0000000000"))
        assertEquals(OrderShop.Shein, catalog.order("GSH\u212A1B2C3D4E5F6"))
        assertEquals(OrderShop.Shein, catalog.order("Order GSH0A1B2C3D4E5\u212A"))
    }

    @Test
    fun linkParametersAreNamedAsCaseInsensitiveCompareReadsThem() {
        // ı, İ and "i̇" are no i.
        assertLink(nothing, "https://www.dhl.com/track?tracking-\u0131d=1234567899")
        assertLink(nothing, "https://www.dhl.com/track?tracking-\u0130d=1234567899")
        assertLink(nothing, "https://www.dhl.com/track?tracking-i\u0307d=1234567899")
        assertLink(linkMatch("1234567899", CarrierId.Dhl), "https://www.dhl.com/track?TRACKING-ID=1234567899")
        assertLink(linkMatch("1234567899", CarrierId.Dhl), "https://www.dhl.com/track?trac\u212Aing-id=1234567899")
        // A ligature is the letters it joins.
        assertLink(linkMatch("XT123456785FR", CarrierId.Chronopost), "https://www.chronopost.fr/suivi?li\uFB06eNumeros=XT123456785FR")
    }

    private fun ids(vararg rawValues: String) = rawValues.map { CarrierId(it) }

    @Test
    fun carriersSortAsFoundationComparesTheirNames() {
        // A space sorts before letters and punctuation, which java.text.Collator passes over.
        val saved = Locale.getDefault()
        Locale.setDefault(Locale.ENGLISH)
        try {
            val selectable = catalog.selectableCarriers
            assertEquals(
                ids("a-tu-hora-express", "a-duie-pyle", "ab-courier", "a1-post", "aaa-cooper", "abf-freight"),
                selectable.filter { catalog.info(it).displayName.uppercase().startsWith("A") }.take(6),
            )
            assertEquals(
                ids("am-home-delivery", "amazon-logistics", "ameex", "american-west-express", "amerijet", "an-post", "andreani"),
                selectable.filter { catalog.info(it).displayName.uppercase().let { name -> name.startsWith("AM") || name.startsWith("AN") } }
                    .take(7),
            )
        } finally {
            Locale.setDefault(saved)
        }
        val section = CarrierPickerSearch.letterSections(catalog, AppLanguage.En).first { it.letter == "A" }
        assertEquals(ids("a-tu-hora-express", "a-duie-pyle", "ab-courier", "a1-post", "aaa-cooper", "abf-freight"), section.carriers.take(6))
    }

    @Test
    fun namesCompareAsFoundationComparesThem() {
        for (language in listOf(AppLanguage.En, AppLanguage.De, AppLanguage.Pl)) {
            val plain = LocalizedComparison.Plain.comparator(language.locale)
            val caseInsensitive = LocalizedComparison.CaseInsensitive.comparator(language.locale)
            val loose = LocalizedComparison.Loose.comparator(language.locale)
            for (comparator in listOf(plain, caseInsensitive, loose)) {
                assertTrue(comparator.compare("DHL parcel", "DHL-Paket") < 0)
                assertTrue(comparator.compare("A Tu Hora Express", "A1 POST") < 0)
            }
            // Numbers by their value in the picker's comparison only.
            assertTrue(plain.compare("DPD 2", "DPD 10") > 0)
            assertTrue(caseInsensitive.compare("DPD 2", "DPD 10") > 0)
            assertTrue(loose.compare("DPD 2", "DPD 10") < 0)
            // Case aside, ß is ss.
            assertTrue(plain.compare("Straße", "Strasse") > 0)
            assertEquals(0, caseInsensitive.compare("Straße", "Strasse"))
            assertEquals(0, loose.compare("Straße", "Strasse"))
            // Polish has Ć after every C.
            val polish = language == AppLanguage.Pl
            for (comparator in listOf(plain, caseInsensitive, loose)) {
                assertEquals(polish, comparator.compare("Ćma", "Czechy") > 0)
            }
        }
    }

    @Test
    fun countriesAreNamedAndSortedAsOnTheIPhone() {
        val names = mapOf(
            AppLanguage.En to mapOf(
                "HK" to "Hong Kong", "MO" to "Macao", "IO" to "Chagos Archipelago", "GS" to "So. Georgia & So. Sandwich Isl.",
                "UM" to "U.S. Outlying Islands", "TL" to "Timor-Leste", "PS" to "Palestinian Territories", "CI" to "Côte d’Ivoire",
                "MM" to "Myanmar (Burma)", "CN" to "China",
            ),
            AppLanguage.It to mapOf(
                "HK" to "Hong Kong", "MO" to "Macao", "IO" to "Isole Chagos", "UM" to "Isole Minori Esterne degli Stati Uniti",
                "US" to "Stati Uniti", "BA" to "Bosnia ed Erzegovina", "CN" to "Cina",
            ),
            AppLanguage.De to mapOf("HK" to "Hongkong", "CN" to "China"),
            AppLanguage.Pl to mapOf("HK" to "Hongkong", "CN" to "Chiny"),
        )
        for ((language, expected) in names) {
            for ((code, name) in expected) assertEquals("$language $code", name, TrackingLocation.countryName(code, language))
        }
        // Foundation reads a code in either case, and names a code it no longer lists.
        assertEquals("France", TrackingLocation.countryName("fr", AppLanguage.En))
        assertEquals("United Kingdom", TrackingLocation.countryName("UK", AppLanguage.En))
        assertEquals("QQ", TrackingLocation.countryName("QQ", AppLanguage.En))

        assertEquals(250, CarrierCatalog.countries.size)
        assertEquals(listOf("AF", "AX", "AL", "DZ", "AS"), CarrierCatalog.countriesByName(AppLanguage.En).take(5))
        assertEquals(listOf("AF", "EG", "AX", "AL", "DZ"), CarrierCatalog.countriesByName(AppLanguage.De).take(5))
        val polish = CarrierCatalog.countriesByName(AppLanguage.Pl)
        val laos = polish.indexOf("LA")
        assertEquals(listOf("LA", "LS", "LB", "LR", "LY", "LI", "LT", "LU", "LV", "MK", "MG", "YT", "MO", "MW"), polish.subList(laos, laos + 14))
    }

    /**
     * `foundationMatches` runs ICU over UTF-16 units where iOS 27 runs a Swift Regex over Characters. They agree on a
     * pattern anchored at both ends whose body takes printable ASCII alone, scalar by scalar: no class escape (`\d`,
     * `\w`, `\s`, `\p`, `\b`), no negated class, no flag or lookbehind, no alternation outside a group, and `.` only in
     * a lookahead, which never decides on its own what the body takes.
     */
    private fun meansTheSameOverCharacters(pattern: String): Boolean {
        if (pattern.length < 2 || !pattern.startsWith("^") || !pattern.endsWith("$")) return false
        if (pattern.any { it.code !in 0x20..0x7E }) return false
        val end = pattern.length - 1
        // The groups open at `index`, each true when it is a lookahead.
        val groups = ArrayList<Boolean>()
        fun escapable(index: Int) = index < end && !pattern[index].isLetterOrDigit()
        var index = 1
        while (index < end) {
            when (pattern[index]) {
                '\\' -> if (escapable(index + 1)) index++ else return false
                '[' -> {
                    if (pattern.getOrNull(index + 1) == '^') return false
                    index++
                    while (index < end && pattern[index] != ']') {
                        when (pattern[index]) {
                            '[' -> return false
                            '\\' -> if (escapable(index + 1)) index++ else return false
                        }
                        index++
                    }
                    if (index >= end) return false
                }
                '(' -> if (pattern.getOrNull(index + 1) == '?') {
                    groups += when (pattern.getOrNull(index + 2)) {
                        '=', '!' -> true
                        ':' -> false
                        else -> return false
                    }
                    index += 2
                } else {
                    groups += false
                }
                ')' -> groups.removeLastOrNull() ?: return false
                '|' -> if (groups.isEmpty()) return false
                '.' -> if (true !in groups) return false
                '^' -> return false
            }
            index++
        }
        return index == end && groups.isEmpty()
    }

    @Test
    fun theCatalogsPatternsMeanTheSameOverCharactersAsOverUtf16() {
        val requirements = catalog.definitions.values.flatMap { it.tracking.requirements.orEmpty() }.mapNotNull { it.pattern?.nonEmpty }
        assertTrue(requirements.isNotEmpty())
        // `hidesDestinationLeg` gives the catalog's own to `foundationMatches` too.
        val destinationLegs = catalog.definitions.values.mapNotNull { it.hidesDestinationLeg }
        assertTrue(destinationLegs.isNotEmpty())
        // The patterns CarrierCatalog, CarrierRecognition and CarrierRequirement give `foundationMatches` themselves.
        val literals = listOf(
            CarrierRequirement.anyPostcode,
            "^[A-Z0-9]{4,40}$",
            "^NJVTT[0-9]{11}$",
            "^(?:NL(SG|MY|ID|PH|TH|VN)[A-Z]{1,2}[0-9]{8,10}|NV(SG|MY|ID|PH|TH|VN)(?!STAMP[A-Z0-9]{9}$)[A-Z0-9]{8,20})$",
            "^[A-Z0-9]{11}[0-9]{5}$",
            "^(DD|LD)[A-Z0-9]{6}$",
            "^[0-9]{10}$",
            "^PK[0-9]{20}$",
            "^(?:ESP[0-9]{5}1234[0-9]{11}|PRT[0-9]{7}1234[0-9]{9})$",
            "^(J?JD[0-9]{16}|8YDR[0-9]{9}|[0-9]{2}[A-Z]{3}[0-9]{7}A[0-9]{3})$",
        )
        for (pattern in requirements + destinationLegs + literals) {
            assertTrue(pattern, meansTheSameOverCharacters(pattern))
            assertTrue(pattern, CatalogExpressions.expression(pattern) != null)
        }

        // Each of these could read a Character of several scalars or a line end otherwise than ICU does, or is no pattern.
        for (pattern in listOf(
            "^\\d{4}$", "^\\w+$", "^[0-9]\\s?[A-Z]$", "^[^ ]+$", "^.{3}$", "^[A-Z]+", "[0-9]{4}$", "^[0-9]+\\$",
            "^a|b$", "^(?i)[a-z]+$", "^(?<=a)b$", "^[\\p{L}]+$", "^\u00E9$", "^[a-z\\n]+$", "^[0-9]{4}(?=.*)[A-Z].$",
            "^(?=[0-9]{4}$", "^[0-9]{4}[$",
        )) {
            assertFalse(pattern, meansTheSameOverCharacters(pattern))
        }
    }
}
