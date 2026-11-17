package com.plhery.peekdeliverytracker.core

import java.util.Locale
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** The mail filter for shipping emails: src/lib/emailFilter.test.ts, case for case. */
class EmailFilterTests {
    private val all = EmailFilter.languages

    @Test
    fun searchesTheSubjectForOneLanguagesWordsQuotingThoseOfSeveralWords() {
        assertEquals(
            "subject:(expédié OR expédiée OR expédition OR colis OR livraison OR suivi OR acheminement OR distribution OR \"en route\" OR \"en chemin\" OR \"en transit\")",
            EmailFilter.gmailFilter(listOf("fr")),
        )
    }

    @Test
    fun leavesOutTheWordsOnlyADeliveryConfirmationWrites() {
        val every = EmailFilter.gmailFilter(all).lowercase().split(Regex("[ ():\"]+"))
        for (word in listOf("delivered", "livré", "livrée", "zugestellt", "geliefert", "consegnato", "entregado", "entregue", "bezorgd", "doręczona", "доставлен", "签收")) {
            assertFalse(word, word in every)
        }
    }

    @Test
    fun joinsTheChosenLanguagesInTheOrderTheyAreOfferedNamingASharedWordOnce() {
        val filter = EmailFilter.gmailFilter(listOf("pt", "es"))
        assertEquals(1, Regex("\\benviado\\b").findAll(filter).count())
        assertEquals(1, Regex("\\bentrega\\b").findAll(filter).count())
        assertTrue(filter.indexOf("paquete") < filter.indexOf("encomenda"))
        assertEquals(filter, EmailFilter.gmailFilter(listOf("es", "pt")))
    }

    @Test
    fun isEmptyWithNoLanguage() {
        assertEquals("", EmailFilter.gmailFilter(emptyList()))
    }

    @Test
    fun startsWithTheLanguagePeekShowsThePhonesAndEnglish() {
        assertEquals(listOf("en", "de", "fr"), EmailFilter.languagesFor(listOf("fr-CH", "de-CH", "de")))
        assertEquals(listOf("en", "pt"), EmailFilter.languagesFor(listOf("pt-BR")))
        assertEquals(listOf("en"), EmailFilter.languagesFor(listOf("sv-SE", "")))
    }

    @Test
    fun holdsOnlyWordsGmailTakesAsWritten() {
        val written = Regex("^[\\p{L}\\p{M}]+(?: [\\p{L}\\p{M}]+)*$")
        for (language in all) {
            val words = EmailFilter.shippingSubjectWords.getValue(language)
            assertEquals(language, words.size, words.map { it.lowercase() }.toSet().size)
            for (word in words) {
                assertTrue(word, written.matches(word))
                assertFalse(word, "OR" in word.split(' '))
            }
        }
    }

    @Test
    fun staysWithinWhatGmailsFilterTakesWhenEveryLanguageIsChosen() {
        assertTrue(EmailFilter.gmailFilter(all).length < 1_500)
    }

    @Test
    fun givesAOneWordAtATimeRuleEachLanguagesMainWordsEveryFirstWordBeforeAnySecond() {
        assertEquals(listOf("colis", "livraison", "expédié", "expédition"), EmailFilter.subjectWords(listOf("fr")))
        assertEquals(listOf("shipped", "colis", "shipment", "livraison"), EmailFilter.subjectWords(listOf("fr", "en")).take(4))
        // "entrega" is Spanish and Portuguese: named once.
        assertEquals(1, EmailFilter.subjectWords(listOf("es", "pt")).count { it == "entrega" })
        assertEquals(emptyList<String>(), EmailFilter.subjectWords(emptyList()))
        for (language in all) {
            val words = EmailFilter.shippingSubjectWords.getValue(language)
            for (word in EmailFilter.mainSubjectWords.getValue(language)) {
                assertTrue(word, word in words)
                assertFalse(word, ' ' in word)
            }
        }
    }

    @Test
    fun readsTheMailServiceOffAnAddressAndStartsAnyOtherOnGmail() {
        val cases = mapOf(
            "someone@gmail.com" to MailService.Gmail,
            "someone@googlemail.com" to MailService.Gmail,
            "Someone@Hotmail.FR" to MailService.Outlook,
            "someone@live.co.uk" to MailService.Outlook,
            "someone@icloud.com" to MailService.Icloud,
            "someone@me.com" to MailService.Icloud,
            "someone@yahoo.co.uk" to MailService.Yahoo,
            "someone@me.example.test" to MailService.Gmail,
            "someone@example.test" to MailService.Gmail,
            "constructor@constructor.test" to MailService.Gmail,
        )
        for ((email, service) in cases) assertEquals(email, service, EmailFilter.mailServiceOf(email))
        assertEquals(MailService.Gmail, EmailFilter.mailServiceOf(null))
    }

    @Test
    fun namesEachLanguageInTheOnePeekShowsCapitalisedAsAChoiceIs() {
        assertEquals("Allemand", EmailFilter.languageName("de", Locale.FRENCH))
        assertEquals("Русский", EmailFilter.languageName("ru", Locale.forLanguageTag("ru")))
        assertEquals("English", EmailFilter.languageName("en", Locale.ENGLISH))
        assertEquals("中文", EmailFilter.languageName("zh", Locale.forLanguageTag("zh")))
        for (language in all) assertFalse(language, EmailFilter.languageName(language, Locale.forLanguageTag("pl")).isEmpty())
    }

    @Test
    fun offersTheLanguagesTheWebOffers() {
        assertEquals(listOf("en", "de", "fr", "it", "es", "pt", "nl", "pl", "ru", "zh"), all)
    }
}
