import XCTest
@testable import PeekDeliveryTracker

@MainActor
final class LocalizationTests: XCTestCase {
    func testOIDCSignInCopyUsesTheConfiguredProviderInEveryLanguage() {
        let localizer = Localizer()
        let originalLanguage = localizer.language
        defer { localizer.language = originalLanguage }
        for language in AppLanguage.allCases {
            localizer.language = language
            for key in ["auth.oidc", "auth.oidcOpening", "auth.oidcFailed"] {
                let text = localizer.text(key, ["provider": "Authentik"])
                XCTAssertTrue(text.contains("Authentik"), "\(language).\(key)")
                XCTAssertFalse(text.contains("{{provider}}"), "\(language).\(key)")
            }
        }
    }

    func testSharedTrackingMessagesPreserveOriginalNotesAndHideDiagnostics() {
        let localizer = Localizer()
        for language in AppLanguage.allCases {
            localizer.language = language
            XCTAssertEqual(localizer.eventDescription("Shipment exception"), localizer.text("event.carrier.exception"))
            XCTAssertEqual(localizer.eventDescription("Shipment arrived at a Dachser facility"), localizer.text("event.carrier.facilityArrival", ["carrier": "Dachser"]))
            XCTAssertEqual(localizer.eventDescription("Scanned by Sophie at dock 12"), "Scanned by Sophie at dock 12")
            XCTAssertEqual(localizer.trackingFailureMessage("carrier:input_required"), localizer.text("tracking.failure.input"))
            XCTAssertEqual(localizer.trackingFailureMessage("carrier:several_parcels"), localizer.text("tracking.failure.severalParcels"))
            XCTAssertEqual(localizer.trackingFailureMessage("HTTP 403 private diagnostic"), localizer.text("detail.trackingUnavailable"))
            XCTAssertEqual(localizer.trackingFailureMessage("carrier:new_kind"), localizer.text("detail.trackingUnavailable"))
        }
    }

    func testDeliveryWindowRejectsInvalidOrReversedStarts() throws {
        let localizer = Localizer()
        localizer.language = .en
        let now = try XCTUnwrap(DateParser.deliveryDate("2026-09-09"))
        XCTAssertEqual(localizer.deliveryWindow(from: "2026-09-10", to: "2026-09-11", now: now), "tomorrow – Fri 11 Sep")
        for from in [nil, "invalid", "2026-09-12", "2026-09-11"] as [String?] {
            XCTAssertEqual(localizer.deliveryWindow(from: from, to: "2026-09-11", now: now), "Fri 11 Sep")
        }
    }

    func testAnIPadReadsItsOwnNameWhereTheCopyNamesTheIPhone() throws {
        let dictionaries = try localizationDictionaries()
        let variants = try XCTUnwrap(dictionaries["en"]).keys.filter { $0.hasSuffix(".ipad") }
        XCTAssertTrue(variants.contains("native.follow.action.ipad"))
        let pad = Localizer(onPad: true)
        let phone = Localizer(onPad: false)
        let originalLanguage = pad.language
        defer { pad.language = originalLanguage }
        for language in AppLanguage.allCases {
            pad.language = language
            phone.language = language
            let messages = try XCTUnwrap(dictionaries[language.rawValue])
            for variant in variants {
                let key = String(variant.dropLast(".ipad".count))
                XCTAssertEqual(pad.text(key), messages[variant], "\(language).\(key)")
                XCTAssertFalse(pad.text(key).contains("iPhone"), "\(language).\(key)")
                XCTAssertEqual(phone.text(key), messages[key], "\(language).\(key)")
            }
        }
        pad.language = .en
        XCTAssertEqual(pad.text("native.follow.action"), "Follow on this iPad")
        XCTAssertEqual(pad.text("native.account"), "Account")
    }

    func testEveryLanguageHasTheSameKeysAsEnglish() throws {
        let dictionaries = try localizationDictionaries()
        let englishKeys = Set(try XCTUnwrap(dictionaries["en"]).keys)

        XCTAssertEqual(Set(dictionaries.keys), Set(AppLanguage.allCases.map(\.rawValue)))
        for language in AppLanguage.allCases.map(\.rawValue) where language != "en" {
            XCTAssertEqual(Set(try XCTUnwrap(dictionaries[language]).keys), englishKeys, language)
        }
    }

    func testTranslationsPreserveInterpolationVariables() throws {
        let dictionaries = try localizationDictionaries()
        let english = try XCTUnwrap(dictionaries["en"])

        for language in AppLanguage.allCases.map(\.rawValue) where language != "en" {
            let translated = try XCTUnwrap(dictionaries[language])
            for (key, englishValue) in english {
                // Russian counts 21 and 101 as one too, so a count's one form may write the number where English writes 1.
                let value = translated[key] ?? ""
                let counted = key.hasSuffix(".one") && variables(in: value.replacingOccurrences(of: "{{count}}", with: "")) == variables(in: englishValue)
                XCTAssertEqual(
                    counted ? variables(in: englishValue) : variables(in: value),
                    variables(in: englishValue),
                    "\(language).\(key)"
                )
            }
        }
    }

    func testNotificationPresetCopyExistsInEveryLanguage() throws {
        let dictionaries = try localizationDictionaries()
        let keys = NotificationPreset.allCases.flatMap { [$0.titleKey, $0.descriptionKey] }

        for (language, values) in dictionaries {
            for key in keys {
                XCTAssertFalse(values[key, default: ""].isEmpty, "\(language).\(key)")
            }
        }
    }

    func testDeliveryEmailCopyFillsItsVariablesInEveryLanguage() {
        let localizer = Localizer()
        let previousLanguage = localizer.language
        defer { localizer.language = previousLanguage }
        let address = "alex@example.com"
        for language in AppLanguage.allCases {
            localizer.language = language
            let place = localizer.deliveryUpdatesPlace
            XCTAssertEqual(place, "\(localizer.text("settings.title")) › \(localizer.text("settings.deliveryUpdates"))")
            let preset = localizer.text(NotificationPreset.important.titleKey)
            let filled: [(text: String, value: String)] = [
                (localizer.text("email.setting.body", ["email": address]), address),
                (localizer.text("email.parcel.title", ["name": "New sneakers"]), "New sneakers"),
                (localizer.text("email.parcel.notificationsBody", ["preset": preset]), preset),
                (localizer.text("email.parcel.emailBody", ["email": address]), address),
                (localizer.text("email.parcel.note", ["place": place]), place),
                (localizer.text("email.offer.body", ["email": address]), address),
                (localizer.text("email.offer.on", ["place": place]), place),
            ]
            for (text, value) in filled {
                XCTAssertTrue(text.contains(value), "\(language.rawValue): \(text)")
                XCTAssertFalse(text.contains("{{"), "\(language.rawValue): \(text)")
            }
            for key in ["email.section", "email.new", "email.setting.title", "email.setting.example", "email.setting.failed",
                        "email.parcel.open", "email.parcel.notifications", "email.parcel.email", "email.offer.title",
                        "auth.privacyLink", "alerts.turnOn", "onboarding.notifications.notNow", "native.done"] {
                let text = localizer.text(key)
                XCTAssertFalse(text.isEmpty || text == key || text.contains("{{"), "\(language.rawValue).\(key)")
            }
        }
    }

    func testNativeErrorAndSettingsCopyExistsInEveryLanguage() throws {
        let dictionaries = try localizationDictionaries()
        let keys = [
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
        ]

        for (language, values) in dictionaries {
            for key in keys {
                let value = values[key, default: ""]
                XCTAssertFalse(value.isEmpty, "\(language).\(key)")
                XCTAssertNotEqual(value, key, "\(language).\(key)")
            }
        }
    }

    func testKnownNativeErrorsUseTheSelectedLanguage() {
        let localizer = Localizer()
        localizer.language = .de

        XCTAssertEqual(
            localizer.errorMessage(DeliveryAPIError.authenticationExpired),
            "Deine Anmeldung ist abgelaufen. Bitte melde dich erneut an."
        )
        XCTAssertEqual(
            localizer.errorMessage(AuthenticationError.oauthCancelled),
            "Die Anmeldung wurde abgebrochen."
        )
    }

    func testServerErrorsAndAppEventsFollowTheSelectedLanguage() {
        let localizer = Localizer()
        localizer.language = .fr
        XCTAssertEqual(localizer.eventDescription("Tracking added"), "Ajouté à tes colis.")
        XCTAssertEqual(localizer.eventDescription("Original carrier scan"), "Original carrier scan")
        XCTAssertEqual(localizer.errorMessage(DeliveryAPIError.service("private SQL details")), localizer.text("error.generic"))
        XCTAssertEqual(localizer.errorMessage(AuthenticationError.server("Token has expired or is invalid")), localizer.text("error.invalidCode"))
    }

    func testPolishPluralForms() {
        let localizer = Localizer()
        localizer.language = .pl
        for count in [0, 1, 2, 4, 5, 12, 14, 21, 22, 25, 101, 102, 112] {
            let few = [2, 4, 22, 102].contains(count)
            let stamp = count == 1 ? "znaczek" : few ? "znaczki" : "znaczków"
            let parcel = count == 1 ? "przesyłka" : few ? "przesyłki" : "przesyłek"
            XCTAssertEqual(localizer.text("friends.stampCount", ["count": count]), "\(count) \(stamp)")
            XCTAssertEqual(localizer.text(count == 1 ? "passport.parcels.one" : "passport.parcels.many", ["count": count]), "\(count) \(parcel)")
        }
        XCTAssertEqual(localizer.text("time.daysAgo", ["count": 1]), "1 dzień temu")
    }

    /// Polish and Russian whole counts take CLDR's forms; Russian's `one` also counts 21, 31 and 101.
    func testCountFormsInPolishAndRussian() {
        let russian: [Double: String] = [1: "one", 2: "few", 4: "few", 5: "many", 11: "many", 12: "many", 21: "one", 22: "few",
                                         0: "many", 101: "one", 111: "many", 1.5: "other"]
        for (count, form) in russian {
            XCTAssertEqual(Localizer.countForm(count, in: .ru), form, "ru \(count)")
        }
        let polish: [Double: String] = [1: "one", 2: "few", 5: "many", 12: "many", 21: "many", 22: "few", 0: "many", 1.5: "other"]
        for (count, form) in polish {
            XCTAssertEqual(Localizer.countForm(count, in: .pl), form, "pl \(count)")
        }
        for language in AppLanguage.allCases where language != .pl && language != .ru {
            XCTAssertEqual(Localizer.countForm(1, in: language), "one", language.rawValue)
            for count in [0, 2, 5, 21, 22, 1.5] {
                XCTAssertEqual(Localizer.countForm(count, in: language), "other", "\(language.rawValue) \(count)")
            }
        }
    }

    /// A Russian count reads the copy's form for it, whatever words the copy has.
    func testRussianCountsReadTheirForms() throws {
        let russian = try XCTUnwrap(try localizationDictionaries()["ru"])
        let localizer = Localizer()
        let originalLanguage = localizer.language
        defer { localizer.language = originalLanguage }
        localizer.language = .ru
        for (count, key) in [(1, "friends.stampCount.one"), (21, "friends.stampCount.one"), (22, "friends.stampCount.few"),
                             (5, "friends.stampCount"), (11, "friends.stampCount"), (111, "friends.stampCount")] {
            let expected = try XCTUnwrap(russian[key]).replacingOccurrences(of: "{{count}}", with: "\(count)")
            XCTAssertEqual(localizer.text("friends.stampCount", ["count": count]), expected, "\(count)")
        }
    }

    /// Device languages read as the web reads them, and a language Peek doesn't speak is none of its own.
    func testDeviceLanguagesReadAsPeeksLanguages() {
        let tags: [String: AppLanguage?] = [
            "pt-BR": .ptBR, "pt-PT": .pt, "pt": .pt, "ru-RU": .ru, "en-US": .en,
            "zh-Hans-CN": .zh, "zh-Hans": .zh, "zh-CN": .zh, "zh": .zh,
            "zh-Hant-TW": nil, "zh-TW": nil, "zh-HK": nil, "nl-NL": nil,
        ]
        for (tag, language) in tags {
            XCTAssertEqual(AppLanguage(tag: tag), language, tag)
        }
    }

    /// Every language is one the API names, with the locale its dates and numbers take.
    func testEveryLanguageIsOneTheServiceNames() {
        for language in AppLanguage.allCases {
            XCTAssertEqual(NativePushLocale(language).rawValue, language.rawValue)
        }
        XCTAssertEqual(AppLanguage.pt.locale.identifier, "pt-PT")
        XCTAssertEqual(AppLanguage.ptBR.locale.identifier, "pt-BR")
        XCTAssertEqual(AppLanguage.zh.locale.identifier, "zh-Hans")
        XCTAssertEqual(AppLanguage.ru.locale.identifier, "ru")
    }

    func testNewLanguageNamesAndCopy() {
        let localizer = Localizer()
        for (language, name, title) in [(AppLanguage.es, "Español", "Entregas"), (.pt, "Português", "Entregas"), (.pl, "Polski", "Dostawy")] {
            localizer.language = language
            XCTAssertEqual(language.nativeName, name)
            XCTAssertEqual(localizer.text("native.deliveries"), title)
        }
        for (language, name) in [(AppLanguage.ptBR, "Português (Brasil)"), (.ru, "Русский"), (.zh, "简体中文")] {
            XCTAssertEqual(language.nativeName, name)
        }
    }

    func testReadableCalendarDatesInEveryLanguage() throws {
        let localizer = Localizer()
        let date = try XCTUnwrap(DateParser.deliveryDate("2026-09-12"))
        let cases: [(AppLanguage, String)] = [
            (.en, "Sat 12 Sep"), (.de, "Sa 12. Sept"),
            (.fr, "sam 12 sept"), (.it, "sab 12 set"),
            (.es, "sáb 12 sept"), (.pt, "sábado 12 set"),
            (.ptBR, "sáb 12 set"), (.pl, "sob 12 wrz"),
            (.zh, "周六 12 9月"),
        ]
        for (language, expected) in cases {
            localizer.language = language
            XCTAssertEqual(localizer.shortDate(date), expected)
            XCTAssertEqual(localizer.expectedDelivery("2026-09-12", now: date.addingTimeInterval(-3 * 86400)), expected)
        }
    }

    func testSingularCountsInEveryLanguage() {
        let localizer = Localizer()
        let cases: [(AppLanguage, String, String, String)] = [
            (.en, "stamp", "stamps", "Cancel 1 previous link"),
            (.fr, "timbre", "timbres", "Annuler 1 lien précédent"),
            (.de, "Briefmarke", "Briefmarken", "1 früheren Link deaktivieren"),
            (.it, "francobollo", "francobolli", "Annulla 1 link precedente"),
        ]
        for (language, singular, plural, link) in cases {
            localizer.language = language
            XCTAssertEqual(localizer.text("friends.stampCount", ["count": 1]), "1 \(singular)")
            for count in [0, 2, 10] {
                XCTAssertEqual(localizer.text("friends.stampCount", ["count": count]), "\(count) \(plural)")
            }
            XCTAssertEqual(localizer.text("friends.cancelPrevious", ["count": 1]), link)
            XCTAssertEqual(localizer.text("friends.day", ["count": 1]), localizer.text("friends.day"))
        }
    }

    func testRelativeDeliveryDatesInEveryLanguage() throws {
        let localizer = Localizer()
        let now = try XCTUnwrap(DateParser.deliveryDate("2026-09-09"))
        let cases: [(AppLanguage, [String])] = [
            (.en, ["yesterday", "today", "tomorrow"]),
            (.de, ["gestern", "heute", "morgen"]),
            (.fr, ["hier", "aujourd’hui", "demain"]),
            (.it, ["ieri", "oggi", "domani"]),
            (.es, ["ayer", "hoy", "mañana"]),
            (.pt, ["ontem", "hoje", "amanhã"]),
            (.pl, ["wczoraj", "dziś", "jutro"]),
        ]
        for (language, labels) in cases {
            localizer.language = language
            for (index, value) in ["2026-09-08", "2026-09-09", "2026-09-10"].enumerated() {
                let day = try XCTUnwrap(DateParser.deliveryDate(value))
                let timestamp = try XCTUnwrap(Calendar.current.date(bySettingHour: 14, minute: 5, second: 0, of: day))
                XCTAssertEqual(localizer.expectedDelivery(value, now: now), labels[index])
                XCTAssertEqual(localizer.expectedDelivery("\(value) 14:00-16:00", now: now), "\(labels[index]), 14:00–16:00")
                XCTAssertEqual(localizer.expectedDelivery(DateParser.isoString(timestamp), now: now), "\(labels[index]), 14:05")
            }
            XCTAssertEqual(localizer.expectedDelivery("Awaiting estimate", now: now), "Awaiting estimate")
        }
    }

    func testRelativeDeliveryDatesUseCalendarDaysAtBoundaries() throws {
        let localizer = Localizer()
        localizer.language = .en
        let cases = [
            ("2026-01-01", "2025-12-31", "yesterday"),
            ("2026-12-31", "2027-01-01", "tomorrow"),
            ("2026-03-30", "2026-03-29", "yesterday"),
            ("2026-10-25", "2026-10-26", "tomorrow"),
        ]
        for (today, value, expected) in cases {
            let now = try XCTUnwrap(DateParser.deliveryDate(today))
            XCTAssertEqual(localizer.expectedDelivery(value, now: now), expected)
        }
        let day = try XCTUnwrap(DateParser.deliveryDate("2026-09-09"))
        let late = try XCTUnwrap(Calendar.current.date(bySettingHour: 23, minute: 55, second: 0, of: day))
        XCTAssertEqual(localizer.deliveryDate(day, now: late), "today")
        XCTAssertEqual(localizer.expectedDelivery("2026-09-11", now: late), "Fri 11 Sep")
    }

    func testTrackingPlacesTakeOnlyExplicitCountries() {
        let places: [String: TrackingLocation.Place] = [
            "Zürich, Schweiz": .init(country: "CH", name: "Zürich"), "Bâle (Suisse)": .init(country: "CH", name: "Bâle"),
            "Milano, Italia": .init(country: "IT", name: "Milano"), "Paris; France": .init(country: "FR", name: "Paris"),
            "London, UK": .init(country: "GB", name: "London"), "ZUERICH, CH": .init(country: "CH", name: "ZUERICH"),
            "Zürich (Mülligen), CH": .init(country: "CH", name: "Zürich (Mülligen)"),
            "Hebron, KY, US, US": .init(country: "US", name: "Hebron, KY"),
            "Switzerland Haerkingen": .init(country: "CH", name: "Haerkingen"),
            "United Kingdom Coventry": .init(country: "GB", name: "Coventry"),
            "Shenzhen-Futian, China": .init(country: "CN", name: "Shenzhen-Futian"), "Canton, Chine": .init(country: "CN", name: "Canton"),
            "France": .init(country: "FR", name: ""), "DE": .init(country: "DE", name: ""), "CH ": .init(country: "CH", name: ""),
            "THE NETHERLANDS": .init(country: "NL", name: ""), "Czech Republic": .init(country: "CZ", name: ""),
            "Hong Kong": .init(country: "HK", name: ""),
        ]
        for (location, place) in places {
            XCTAssertEqual(TrackingLocation.place(location), place, location)
        }
        for location in ["", "Warehouse", "Paris", "Buchs AG", "Basel, BS", "Wilmington, DE", "France distribution center",
                         "Zürich Briefzentrum", "Mexico City", "Andorra la Vella"] {
            XCTAssertEqual(TrackingLocation.place(location), .init(country: nil, name: location), location)
        }
        // A code that is also a state or canton names the country the scan was placed in, and only that one.
        XCTAssertEqual(TrackingLocation.place("Haiger, DE", placedIn: "DE"), .init(country: "DE", name: "Haiger"))
        XCTAssertEqual(TrackingLocation.place("Haiger (DE)", placedIn: "DE"), .init(country: "DE", name: "Haiger"))
        XCTAssertEqual(TrackingLocation.place("Wilmington, DE", placedIn: "US"), .init(country: nil, name: "Wilmington, DE"))
        XCTAssertEqual(TrackingLocation.place("Hannover, DE", placedIn: nil), .init(country: nil, name: "Hannover, DE"))
        XCTAssertEqual(TrackingLocation.place("Austin, TX", placedIn: "US"), .init(country: nil, name: "Austin, TX"))
        XCTAssertEqual(TrackingLocation.place("Los Angeles, CA", placedIn: "US"), .init(country: nil, name: "Los Angeles, CA"))
        XCTAssertEqual(TrackingLocation.place("Toronto, CA", placedIn: "CA"), .init(country: "CA", name: "Toronto"))
        XCTAssertEqual(TrackingLocation.flag("CH"), "🇨🇭")
        // Passport evidence stays a final field that names a region.
        XCTAssertEqual(TrackingLocation.countryCode(in: "Zürich, CH"), "CH")
        XCTAssertNil(TrackingLocation.countryCode(in: "Switzerland Haerkingen"))
        XCTAssertNil(TrackingLocation.countryCode(in: "Czech Republic"))
        XCTAssertEqual(TrackingLocation.countryCode(in: "Haiger, DE", placedIn: "DE"), "DE")
        XCTAssertNil(TrackingLocation.countryCode(in: "Wilmington, DE", placedIn: "US"))
        XCTAssertNil(TrackingLocation.countryCode(in: "Basel", placedIn: "CH"))
        // Apple's own name for CN is "China mainland"; carriers, the web and Friends say "China".
        XCTAssertEqual(TrackingLocation.countryCode(in: "Shenzhen, China"), "CN")
        XCTAssertEqual(TrackingLocation.countryName("CN", language: .en), "China")
        XCTAssertEqual(TrackingLocation.countryName("CN", language: .fr), "Chine")
        XCTAssertEqual(TrackingLocation.countryName("CN", language: .ru), "Китай")
        XCTAssertEqual(TrackingLocation.countryName("CN", language: .zh), "中国")
        XCTAssertEqual(TrackingLocation.place("深圳, 中国"), .init(country: "CN", name: "深圳"))
        XCTAssertEqual(TrackingLocation.place("Москва, Россия"), .init(country: "RU", name: "Москва"))
        XCTAssertEqual(TrackingLocation.countryName("CH", language: .de), "Schweiz")
    }

    private func localizationDictionaries() throws -> [String: [String: String]] {
        let url = try XCTUnwrap(Bundle.main.url(forResource: "Localization", withExtension: "json"))
        return try JSONDecoder().decode([String: [String: String]].self, from: Data(contentsOf: url))
    }

    private func variables(in value: String) -> Set<String> {
        let expression = try! NSRegularExpression(pattern: "\\{\\{([A-Za-z0-9_.-]+)\\}\\}")
        let range = NSRange(value.startIndex..., in: value)
        return Set(expression.matches(in: value, range: range).compactMap { match in
            guard let range = Range(match.range(at: 1), in: value) else { return nil }
            return String(value[range])
        })
    }
}
