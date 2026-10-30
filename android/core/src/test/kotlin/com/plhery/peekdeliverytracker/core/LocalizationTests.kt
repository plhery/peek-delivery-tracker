package com.plhery.peekdeliverytracker.core

import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Instant
import java.time.ZoneId

/** LocalizationTests.swift: the app's copy in its ten languages, its dates, counts and places. */
class LocalizationTests {
    @Test
    fun oidcSignInCopyUsesTheConfiguredProviderInEveryLanguage() {
        val localizer = Localizer(AppLanguage.En)
        for (language in AppLanguage.entries) {
            localizer.language = language
            for (key in listOf("auth.oidc", "auth.oidcOpening", "auth.oidcFailed")) {
                val text = localizer.text(key, mapOf("provider" to "Authentik"))
                assertTrue("$language.$key", text.contains("Authentik"))
                assertFalse("$language.$key", text.contains("{{provider}}"))
            }
        }
    }

    @Test
    fun sharedTrackingMessagesPreserveOriginalNotesAndHideDiagnostics() {
        val localizer = Localizer(AppLanguage.En)
        for (language in AppLanguage.entries) {
            localizer.language = language
            assertEquals(localizer.text("event.carrier.exception"), localizer.eventDescription("Shipment exception"))
            assertEquals(
                localizer.text("event.carrier.facilityArrival", mapOf("carrier" to "Dachser")),
                localizer.eventDescription("Shipment arrived at a Dachser facility"),
            )
            assertEquals("Scanned by Sophie at dock 12", localizer.eventDescription("Scanned by Sophie at dock 12"))
            assertEquals(localizer.text("tracking.failure.input"), localizer.trackingFailureMessage("carrier:input_required"))
            assertEquals(localizer.text("tracking.failure.severalParcels"), localizer.trackingFailureMessage("carrier:several_parcels"))
            assertEquals(localizer.text("detail.trackingUnavailable"), localizer.trackingFailureMessage("HTTP 403 private diagnostic"))
            assertEquals(localizer.text("detail.trackingUnavailable"), localizer.trackingFailureMessage("carrier:new_kind"))
        }
    }

    @Test
    fun deliveryWindowRejectsInvalidOrReversedStarts() {
        val localizer = Localizer(AppLanguage.En)
        val now = assertNotNull(DateParser.deliveryDate("2026-09-09"))
        assertEquals("tomorrow – Fri 11 Sep", localizer.deliveryWindow(from = "2026-09-10", to = "2026-09-11", now = now))
        for (from in listOf(null, "invalid", "2026-09-12", "2026-09-11")) {
            assertEquals("Fri 11 Sep", localizer.deliveryWindow(from = from, to = "2026-09-11", now = now))
        }
    }

    /** The iPad's variant, as `Localizer(onPad: true)` reads it. */
    @Test
    fun anIPadReadsItsOwnNameWhereTheCopyNamesTheIPhone() {
        val dictionaries = localizationDictionaries()
        val variants = dictionaries.getValue("en").keys.filter { it.endsWith(".ipad") }
        assertTrue(variants.contains("native.follow.action.ipad"))
        val pad = Localizer(AppLanguage.En, variant = "ipad")
        val phone = Localizer(AppLanguage.En)
        for (language in AppLanguage.entries) {
            pad.language = language
            phone.language = language
            val messages = dictionaries.getValue(language.rawValue)
            for (variant in variants) {
                val key = variant.dropLast(".ipad".length)
                assertEquals("$language.$key", messages[variant], pad.text(key))
                // "iPhon", not "iPhone": Polish declines it ("na tym iPhonie").
                assertFalse("$language.$key", pad.text(key).contains("iPhon"))
                assertEquals("$language.$key", messages[key], phone.text(key))
            }
        }
        pad.language = AppLanguage.En
        assertEquals("Follow on this iPad", pad.text("native.follow.action"))
        assertEquals("Account", pad.text("native.account"))
    }

    /** Android's variant, which the app reads on every phone, foldable and tablet: "this device", Android's settings. */
    @Test
    fun androidReadsItsOwnWordsWhereTheCopyNamesTheIPhone() {
        val dictionaries = localizationDictionaries()
        val variants = dictionaries.getValue("en").keys.filter { it.endsWith(".android") }
        assertTrue(variants.contains("native.follow.action.android"))
        val android = Localizer(AppLanguage.En, variant = "android")
        for (language in AppLanguage.entries) {
            android.language = language
            val messages = dictionaries.getValue(language.rawValue)
            for (variant in variants) {
                val key = variant.dropLast(".android".length)
                assertEquals("$language.$key", messages[variant], android.text(key))
                assertFalse("$language.$key", Regex("iPhon|iPad|Home Screen|Home-Bildschirm").containsMatchIn(android.text(key)))
            }
        }
        android.language = AppLanguage.En
        assertEquals("Follow on this device", android.text("native.follow.action"))
        assertEquals("Open Android settings", android.text("native.openSettings"))
        assertEquals("Account", android.text("native.account"))
    }

    @Test
    fun everyLanguageHasTheSameKeysAsEnglish() {
        val dictionaries = localizationDictionaries()
        val englishKeys = dictionaries.getValue("en").keys

        assertEquals(AppLanguage.entries.map { it.rawValue }.toSet(), dictionaries.keys)
        for (language in AppLanguage.entries.map { it.rawValue }.filter { it != "en" }) {
            assertEquals(language, englishKeys, dictionaries.getValue(language).keys)
        }
    }

    @Test
    fun translationsPreserveInterpolationVariables() {
        val dictionaries = localizationDictionaries()
        val english = dictionaries.getValue("en")

        for (language in AppLanguage.entries.map { it.rawValue }.filter { it != "en" }) {
            val translated = dictionaries.getValue(language)
            for ((key, englishValue) in english) {
                // Russian counts 21 and 101 as one too, so a count's one form may write the number where English writes 1.
                val value = translated[key] ?: ""
                val counted = key.endsWith(".one") && variables(value.replace("{{count}}", "")) == variables(englishValue)
                assertEquals("$language.$key", variables(englishValue), if (counted) variables(englishValue) else variables(value))
            }
        }
    }

    @Test
    fun notificationPresetCopyExistsInEveryLanguage() {
        val dictionaries = localizationDictionaries()
        val keys = NotificationPreset.entries.flatMap { listOf(it.titleKey, it.descriptionKey) }

        for ((language, values) in dictionaries) {
            for (key in keys) {
                assertFalse("$language.$key", (values[key] ?: "").isEmpty())
            }
        }
    }

    @Test
    fun notificationPresetsAreNamedAsOnTheWebsite() {
        val website = mapOf(
            AppLanguage.En to listOf("Every scan", "Important steps", "Delivery only"),
            AppLanguage.De to listOf("Jeden Scan", "Wichtige Schritte", "Nur die Zustellung"),
            AppLanguage.Fr to listOf("Chaque scan", "Étapes importantes", "Livraison seulement"),
            AppLanguage.It to listOf("Ogni scansione", "Passaggi importanti", "Solo la consegna"),
            AppLanguage.Es to listOf("Cada escaneo", "Pasos importantes", "Solo la entrega"),
            AppLanguage.Pt to listOf("Todas as leituras", "Passos importantes", "Só a entrega"),
            AppLanguage.PtBr to listOf("Cada movimentação", "Etapas importantes", "Só a entrega"),
            AppLanguage.Pl to listOf("Każdy skan", "Ważne etapy", "Tylko doręczenie"),
            AppLanguage.Ru to listOf("Каждая отметка", "Важные этапы", "Только доставка"),
            AppLanguage.Zh to listOf("每次扫描", "重要环节", "仅派送当天"),
        )
        assertEquals(AppLanguage.entries.toSet(), website.keys)
        val localizer = Localizer(AppLanguage.En)
        for ((language, names) in website) {
            localizer.language = language
            assertEquals(language.rawValue, names, NotificationPreset.entries.map { localizer.text(it.titleKey) })
        }
    }

    @Test
    fun deliveryEmailCopyFillsItsVariablesInEveryLanguage() {
        val localizer = Localizer(AppLanguage.En)
        val address = "alex@example.com"
        for (language in AppLanguage.entries) {
            localizer.language = language
            val place = localizer.deliveryUpdatesPlace
            assertEquals("${localizer.text("settings.title")} › ${localizer.text("settings.deliveryUpdates")}", place)
            val preset = localizer.text(NotificationPreset.Important.titleKey)
            val filled = listOf(
                localizer.text("email.setting.body", mapOf("email" to address)) to address,
                localizer.text("email.parcel.title", mapOf("name" to "New sneakers")) to "New sneakers",
                localizer.text("email.parcel.notificationsBody", mapOf("preset" to preset)) to preset,
                localizer.text("email.parcel.emailBody", mapOf("email" to address)) to address,
                localizer.text("email.parcel.note", mapOf("place" to place)) to place,
                localizer.text("email.offer.body", mapOf("email" to address)) to address,
                localizer.text("email.offer.on", mapOf("place" to place)) to place,
            )
            for ((text, value) in filled) {
                assertTrue("${language.rawValue}: $text", text.contains(value))
                assertFalse("${language.rawValue}: $text", text.contains("{{"))
            }
            for (key in listOf(
                "email.section", "email.new", "email.setting.title", "email.setting.example", "email.setting.failed",
                "email.parcel.open", "email.parcel.notifications", "email.parcel.email", "email.offer.title",
                "auth.privacyLink", "alerts.turnOn", "onboarding.notifications.notNow", "native.done",
            )) {
                val text = localizer.text(key)
                assertFalse("${language.rawValue}.$key", text.isEmpty() || text == key || text.contains("{{"))
            }
        }
    }

    @Test
    fun nativeErrorAndSettingsCopyExistsInEveryLanguage() {
        val dictionaries = localizationDictionaries()
        val keys = listOf(
            "add.requirement.dpdPostcodeHelp",
            "add.requirement.dpdPostcodeOptionalHelp",
            "add.requirement.trackingUrlHelp",
            "auth.emailOption",
            "native.configurationHelp",
            "native.appearance.title",
            "native.appearance.system",
            "native.appearance.light",
            "native.appearance.dark",
            "native.error.authenticationExpired",
            "native.auth.invalidResponse",
            "settings.sendFeedback",
            "settings.version",
            "widget.galleryName",
            "widget.galleryDescription",
            "widget.settingTitle",
            "widget.settingDescription",
            "widget.disabledTitle",
            "widget.disabledDescription",
        )

        for ((language, values) in dictionaries) {
            for (key in keys) {
                val value = values[key] ?: ""
                assertFalse("$language.$key", value.isEmpty())
                assertNotEquals("$language.$key", key, value)
            }
        }
    }

    @Test
    fun knownNativeErrorsUseTheSelectedLanguage() {
        val localizer = Localizer(AppLanguage.De)

        assertEquals(
            "Deine Anmeldung ist abgelaufen. Bitte melde dich erneut an.",
            localizer.errorMessage(DeliveryApiError.AuthenticationExpired()),
        )
        assertEquals("Die Anmeldung wurde abgebrochen.", localizer.errorMessage(AuthenticationError.OauthCancelled()))
    }

    @Test
    fun serverErrorsAndAppEventsFollowTheSelectedLanguage() {
        val localizer = Localizer(AppLanguage.Fr)
        assertEquals("Ajouté à tes colis.", localizer.eventDescription("Tracking added"))
        assertEquals("Original carrier scan", localizer.eventDescription("Original carrier scan"))
        assertEquals(localizer.text("error.generic"), localizer.errorMessage(DeliveryApiError.Service("private SQL details")))
        assertEquals(
            localizer.text("error.invalidCode"),
            localizer.errorMessage(AuthenticationError.Server("Token has expired or is invalid")),
        )
    }

    @Test
    fun polishPluralForms() {
        val localizer = Localizer(AppLanguage.Pl)
        for (count in listOf(0, 1, 2, 4, 5, 12, 14, 21, 22, 25, 101, 102, 112)) {
            val few = count in listOf(2, 4, 22, 102)
            val stamp = if (count == 1) "znaczek" else if (few) "znaczki" else "znaczków"
            val parcel = if (count == 1) "przesyłka" else if (few) "przesyłki" else "przesyłek"
            assertEquals("$count $stamp", localizer.text("friends.stampCount", mapOf("count" to count)))
            assertEquals(
                "$count $parcel",
                localizer.text(if (count == 1) "passport.parcels.one" else "passport.parcels.many", mapOf("count" to count)),
            )
        }
        assertEquals("1 dzień temu", localizer.text("time.daysAgo", mapOf("count" to 1)))
    }

    /** Polish and Russian whole counts take CLDR's forms; Russian's `one` also counts 21, 31 and 101. */
    @Test
    fun countFormsInPolishAndRussian() {
        val russian = mapOf(
            0.0 to "many", 1.0 to "one", 2.0 to "few", 4.0 to "few", 5.0 to "many", 11.0 to "many", 12.0 to "many",
            14.0 to "many", 21.0 to "one", 22.0 to "few", 25.0 to "many", 101.0 to "one", 111.0 to "many", 1.5 to "other",
        )
        for ((count, form) in russian) {
            assertEquals("ru $count", form, Localizer.countForm(count, AppLanguage.Ru))
        }
        val polish = mapOf(
            1.0 to "one", 2.0 to "few", 5.0 to "many", 12.0 to "many", 21.0 to "many", 22.0 to "few", 0.0 to "many", 1.5 to "other",
        )
        for ((count, form) in polish) {
            assertEquals("pl $count", form, Localizer.countForm(count, AppLanguage.Pl))
        }
        for (language in AppLanguage.entries.filter { it != AppLanguage.Pl && it != AppLanguage.Ru }) {
            assertEquals(language.rawValue, "one", Localizer.countForm(1.0, language))
            for (count in listOf(0.0, 2.0, 5.0, 21.0, 22.0, 1.5)) {
                assertEquals("${language.rawValue} $count", "other", Localizer.countForm(count, language))
            }
        }
    }

    /** A Russian count reads the copy's form for it, whatever words the copy has. */
    @Test
    fun russianCountsReadTheirForms() {
        val russian = localizationDictionaries().getValue("ru")
        val localizer = Localizer(AppLanguage.Ru)
        for ((count, key) in listOf(
            1 to "friends.stampCount.one", 21 to "friends.stampCount.one", 22 to "friends.stampCount.few",
            5 to "friends.stampCount", 11 to "friends.stampCount", 111 to "friends.stampCount",
        )) {
            val expected = russian.getValue(key).replace("{{count}}", "$count")
            assertEquals("$count", expected, localizer.text("friends.stampCount", mapOf("count" to count)))
        }
        assertEquals("21 марка", localizer.text("friends.stampCount", mapOf("count" to 21)))
        assertEquals("1.5 марок", localizer.text("friends.stampCount", mapOf("count" to 1.5)))
    }

    /** Device languages read as the web reads them, and a language Peek doesn't speak is none of its own. */
    @Test
    fun deviceLanguagesReadAsPeeksLanguages() {
        val tags = mapOf(
            "pt-BR" to AppLanguage.PtBr, "pt-PT" to AppLanguage.Pt, "pt" to AppLanguage.Pt, "ru-RU" to AppLanguage.Ru,
            "en-US" to AppLanguage.En, "zh-Hans-CN" to AppLanguage.Zh, "zh-Hans" to AppLanguage.Zh, "zh-CN" to AppLanguage.Zh,
            "zh" to AppLanguage.Zh, "zh-Hant-TW" to null, "zh-TW" to null, "zh-HK" to null, "nl-NL" to null,
        )
        for ((tag, language) in tags) {
            assertEquals(tag, language, AppLanguage.fromTag(tag))
        }
    }

    /** Every language is one the API names, with the locale its dates and numbers take and the catalog it reads. */
    @Test
    fun everyLanguageIsOneTheServiceNames() {
        for (language in AppLanguage.entries) {
            assertEquals(language.rawValue, NativePushLocale(language).rawValue)
        }
        assertEquals("pt-PT", AppLanguage.Pt.locale.toLanguageTag())
        assertEquals("pt-BR", AppLanguage.PtBr.locale.toLanguageTag())
        assertEquals("zh-Hans", AppLanguage.Zh.locale.toLanguageTag())
        assertEquals("ru", AppLanguage.Ru.locale.toLanguageTag())
        assertEquals(
            listOf("en", "de", "fr", "it", "es", "pt", "pt", "pl", "en", "en"),
            AppLanguage.entries.map { it.catalogLanguage.rawValue },
        )
    }

    @Test
    fun newLanguageNamesAndCopy() {
        val localizer = Localizer(AppLanguage.En)
        for ((language, name, title) in listOf(
            Triple(AppLanguage.Es, "Español", "Entregas"),
            Triple(AppLanguage.Pt, "Português", "Entregas"),
            Triple(AppLanguage.Pl, "Polski", "Dostawy"),
            Triple(AppLanguage.PtBr, "Português (Brasil)", "Entregas"),
            Triple(AppLanguage.Ru, "Русский", "Доставки"),
            Triple(AppLanguage.Zh, "简体中文", "包裹"),
        )) {
            localizer.language = language
            assertEquals(name, language.nativeName)
            assertEquals(title, localizer.text("native.deliveries"))
        }
    }

    @Test
    fun readableCalendarDatesInEveryLanguage() {
        val localizer = Localizer(AppLanguage.En)
        val date = assertNotNull(DateParser.deliveryDate("2026-09-12"))
        val cases = listOf(
            AppLanguage.En to "Sat 12 Sep", AppLanguage.De to "Sa 12. Sept",
            AppLanguage.Fr to "sam 12 sept", AppLanguage.It to "sab 12 set",
            AppLanguage.Es to "sáb 12 sept", AppLanguage.Pt to "sábado 12 set",
            AppLanguage.PtBr to "sáb 12 set", AppLanguage.Pl to "sob 12 wrz",
            AppLanguage.Ru to "Сб 12 сент", AppLanguage.Zh to "9月12日周六",
        )
        for ((language, expected) in cases) {
            localizer.language = language
            assertEquals(expected, localizer.shortDate(date))
            assertEquals(expected, localizer.expectedDelivery("2026-09-12", now = date.minusSeconds(3 * 86_400)))
        }
    }

    @Test
    fun singularCountsInEveryLanguage() {
        val localizer = Localizer(AppLanguage.En)
        val cases = listOf(
            listOf(AppLanguage.En, "stamp", "stamps", "Cancel 1 previous link"),
            listOf(AppLanguage.Fr, "timbre", "timbres", "Annuler 1 lien précédent"),
            listOf(AppLanguage.De, "Briefmarke", "Briefmarken", "1 früheren Link deaktivieren"),
            listOf(AppLanguage.It, "francobollo", "francobolli", "Annulla 1 link precedente"),
        )
        for ((language, singular, plural, link) in cases) {
            localizer.language = language as AppLanguage
            assertEquals("1 $singular", localizer.text("friends.stampCount", mapOf("count" to 1)))
            for (count in listOf(0, 2, 10)) {
                assertEquals("$count $plural", localizer.text("friends.stampCount", mapOf("count" to count)))
            }
            assertEquals(link, localizer.text("friends.cancelPrevious", mapOf("count" to 1)))
            assertEquals(localizer.text("friends.day"), localizer.text("friends.day", mapOf("count" to 1)))
        }
    }

    @Test
    fun relativeDeliveryDatesInEveryLanguage() {
        val localizer = Localizer(AppLanguage.En)
        val now = assertNotNull(DateParser.deliveryDate("2026-09-09"))
        val cases = listOf(
            AppLanguage.En to listOf("yesterday", "today", "tomorrow"),
            AppLanguage.De to listOf("gestern", "heute", "morgen"),
            AppLanguage.Fr to listOf("hier", "aujourd’hui", "demain"),
            AppLanguage.It to listOf("ieri", "oggi", "domani"),
            AppLanguage.Es to listOf("ayer", "hoy", "mañana"),
            AppLanguage.Pt to listOf("ontem", "hoje", "amanhã"),
            AppLanguage.PtBr to listOf("ontem", "hoje", "amanhã"),
            AppLanguage.Pl to listOf("wczoraj", "dziś", "jutro"),
            AppLanguage.Ru to listOf("вчера", "сегодня", "завтра"),
            AppLanguage.Zh to listOf("昨天", "今天", "明天"),
        )
        for ((language, labels) in cases) {
            localizer.language = language
            // Chinese runs a day and its time together with a space, as the web does.
            val separator = if (language == AppLanguage.Zh) " " else ", "
            for ((index, value) in listOf("2026-09-08", "2026-09-09", "2026-09-10").withIndex()) {
                val day = assertNotNull(DateParser.deliveryDate(value))
                val timestamp = settingTime(day, hour = 14, minute = 5)
                assertEquals(labels[index], localizer.expectedDelivery(value, now = now))
                assertEquals("${labels[index]}${separator}14:00–16:00", localizer.expectedDelivery("$value 14:00-16:00", now = now))
                assertEquals("${labels[index]}${separator}14:05", localizer.expectedDelivery(DateParser.isoString(timestamp), now = now))
            }
            assertEquals("Awaiting estimate", localizer.expectedDelivery("Awaiting estimate", now = now))
        }
    }

    @Test
    fun relativeDeliveryDatesUseCalendarDaysAtBoundaries() {
        val localizer = Localizer(AppLanguage.En)
        val cases = listOf(
            Triple("2026-01-01", "2025-12-31", "yesterday"),
            Triple("2026-12-31", "2027-01-01", "tomorrow"),
            Triple("2026-03-30", "2026-03-29", "yesterday"),
            Triple("2026-10-25", "2026-10-26", "tomorrow"),
        )
        for ((today, value, expected) in cases) {
            val now = assertNotNull(DateParser.deliveryDate(today))
            assertEquals(expected, localizer.expectedDelivery(value, now = now))
        }
        val day = assertNotNull(DateParser.deliveryDate("2026-09-09"))
        val late = settingTime(day, hour = 23, minute = 55)
        assertEquals("today", localizer.deliveryDate(day, now = late))
        assertEquals("Fri 11 Sep", localizer.expectedDelivery("2026-09-11", now = late))
    }

    /**
     * Chinese dates as the web's `localizedCalendarDate` and `dayAndTime` write them: month, day and weekday run
     * together, after the year when there is one, and a time follows a day after a space.
     */
    @Test
    fun chineseDatesReadAsChineseWritesThem() {
        val localizer = Localizer(AppLanguage.Zh)
        val day = assertNotNull(DateParser.deliveryDate("2026-09-12"))
        assertEquals("9月12日周六", localizer.shortDate(day))
        assertEquals("2026年9月12日周六", localizer.shortDate(day, withYear = true))
        val now = assertNotNull(DateParser.deliveryDate("2026-09-08"))
        assertEquals("9月12日周六 14:00–16:00", localizer.expectedDelivery("2026-09-12 14:00-16:00", now = now))
        assertEquals("明天 14:00–16:00", localizer.expectedDelivery("2026-09-09 14:00-16:00", now = now))
        assertEquals("明天 14:00–16:00 – 9月12日周六", localizer.deliveryWindow("2026-09-09 14:00-16:00", "2026-09-12", now = now))
        assertEquals("9月12日周六 14:05", localizer.dateTime(DateParser.isoString(settingTime(day, hour = 14, minute = 5))))
        assertEquals("Сб 12 сент 2026", Localizer(AppLanguage.Ru).shortDate(day, withYear = true))
        assertEquals("Сб 12 сент, 14:05", Localizer(AppLanguage.Ru).dateTime(DateParser.isoString(settingTime(day, hour = 14, minute = 5))))
        assertEquals("sáb 12 set 2026", Localizer(AppLanguage.PtBr).shortDate(day, withYear = true))
    }

    @Test
    fun trackingPlacesTakeOnlyExplicitCountries() {
        val places = mapOf(
            "Zürich, Schweiz" to TrackingLocation.Place(country = "CH", name = "Zürich"),
            "Bâle (Suisse)" to TrackingLocation.Place(country = "CH", name = "Bâle"),
            "Milano, Italia" to TrackingLocation.Place(country = "IT", name = "Milano"),
            "Paris; France" to TrackingLocation.Place(country = "FR", name = "Paris"),
            "London, UK" to TrackingLocation.Place(country = "GB", name = "London"),
            "ZUERICH, CH" to TrackingLocation.Place(country = "CH", name = "ZUERICH"),
            "Zürich (Mülligen), CH" to TrackingLocation.Place(country = "CH", name = "Zürich (Mülligen)"),
            "Hebron, KY, US, US" to TrackingLocation.Place(country = "US", name = "Hebron, KY"),
            "Switzerland Haerkingen" to TrackingLocation.Place(country = "CH", name = "Haerkingen"),
            "United Kingdom Coventry" to TrackingLocation.Place(country = "GB", name = "Coventry"),
            "Shenzhen-Futian, China" to TrackingLocation.Place(country = "CN", name = "Shenzhen-Futian"),
            "Canton, Chine" to TrackingLocation.Place(country = "CN", name = "Canton"),
            "France" to TrackingLocation.Place(country = "FR", name = ""),
            "DE" to TrackingLocation.Place(country = "DE", name = ""),
            "CH " to TrackingLocation.Place(country = "CH", name = ""),
            "THE NETHERLANDS" to TrackingLocation.Place(country = "NL", name = ""),
            "Czech Republic" to TrackingLocation.Place(country = "CZ", name = ""),
            "Hong Kong" to TrackingLocation.Place(country = "HK", name = ""),
        )
        for ((location, place) in places) {
            assertEquals(location, place, TrackingLocation.place(location))
        }
        for (location in listOf(
            "", "Warehouse", "Paris", "Buchs AG", "Basel, BS", "Wilmington, DE", "France distribution center",
            "Zürich Briefzentrum", "Mexico City", "Andorra la Vella",
        )) {
            assertEquals(location, TrackingLocation.Place(country = null, name = location), TrackingLocation.place(location))
        }
        // A code that is also a state or canton names the country the scan was placed in, and only that one.
        assertEquals(TrackingLocation.Place(country = "DE", name = "Haiger"), TrackingLocation.place("Haiger, DE", placedIn = "DE"))
        assertEquals(TrackingLocation.Place(country = "DE", name = "Haiger"), TrackingLocation.place("Haiger (DE)", placedIn = "DE"))
        assertEquals(
            TrackingLocation.Place(country = null, name = "Wilmington, DE"),
            TrackingLocation.place("Wilmington, DE", placedIn = "US"),
        )
        assertEquals(TrackingLocation.Place(country = null, name = "Hannover, DE"), TrackingLocation.place("Hannover, DE", placedIn = null))
        assertEquals(TrackingLocation.Place(country = null, name = "Austin, TX"), TrackingLocation.place("Austin, TX", placedIn = "US"))
        assertEquals(
            TrackingLocation.Place(country = null, name = "Los Angeles, CA"),
            TrackingLocation.place("Los Angeles, CA", placedIn = "US"),
        )
        assertEquals(TrackingLocation.Place(country = "CA", name = "Toronto"), TrackingLocation.place("Toronto, CA", placedIn = "CA"))
        assertEquals("🇨🇭", TrackingLocation.flag("CH"))
        // Passport evidence stays a final field that names a region.
        assertEquals("CH", TrackingLocation.countryCode("Zürich, CH"))
        assertNull(TrackingLocation.countryCode("Switzerland Haerkingen"))
        assertNull(TrackingLocation.countryCode("Czech Republic"))
        assertEquals("DE", TrackingLocation.countryCode("Haiger, DE", placedIn = "DE"))
        assertNull(TrackingLocation.countryCode("Wilmington, DE", placedIn = "US"))
        assertNull(TrackingLocation.countryCode("Basel", placedIn = "CH"))
        // Apple's own name for CN is "China mainland"; carriers, the web and Friends say "China".
        assertEquals("CN", TrackingLocation.countryCode("Shenzhen, China"))
        assertEquals("China", TrackingLocation.countryName("CN", language = AppLanguage.En))
        assertEquals("Chine", TrackingLocation.countryName("CN", language = AppLanguage.Fr))
        assertEquals("Китай", TrackingLocation.countryName("CN", language = AppLanguage.Ru))
        assertEquals("中国", TrackingLocation.countryName("CN", language = AppLanguage.Zh))
        assertEquals("China", TrackingLocation.countryName("CN", language = AppLanguage.PtBr))
        assertEquals(TrackingLocation.Place(country = "CN", name = "深圳"), TrackingLocation.place("深圳, 中国"))
        assertEquals(TrackingLocation.Place(country = "RU", name = "Москва"), TrackingLocation.place("Москва, Россия"))
        assertEquals(TrackingLocation.Place(country = "HK", name = ""), TrackingLocation.place("香港"))
        assertEquals("Россия", TrackingLocation.countryName("RU", language = AppLanguage.Ru))
        assertEquals("Suíça", TrackingLocation.countryName("CH", language = AppLanguage.PtBr))
        assertEquals("Schweiz", TrackingLocation.countryName("CH", language = AppLanguage.De))
    }

    private fun <T : Any> assertNotNull(value: T?): T {
        assertTrue(value != null)
        return value!!
    }

    /** `Calendar.current.date(bySettingHour:minute:second:of:)`: the time on the same local day. */
    private fun settingTime(day: Instant, hour: Int, minute: Int): Instant {
        val zone = ZoneId.systemDefault()
        return day.atZone(zone).toLocalDate().atTime(hour, minute).atZone(zone).toInstant()
    }

    private fun localizationDictionaries(): Map<String, Map<String, String>> = Json.decodeFromString(
        MapSerializer(String.serializer(), MapSerializer(String.serializer(), String.serializer())),
        SharedResources.text("Localization.json"),
    )

    private fun variables(value: String): Set<String> =
        Regex("\\{\\{([A-Za-z0-9_.-]+)\\}\\}").findAll(value).map { it.groupValues[1] }.toSet()
}
