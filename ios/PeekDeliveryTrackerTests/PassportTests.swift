import XCTest
@testable import PeekDeliveryTracker

/// The passport's book, its news and the year's card, as the web and Android count and word them.
@MainActor
final class PassportTests: XCTestCase {
    private var localizer: Localizer!
    private var previousLanguage: AppLanguage!

    override func setUp() async throws {
        localizer = Localizer()
        previousLanguage = localizer.language
        localizer.language = .en
    }

    override func tearDown() async throws {
        localizer.language = previousLanguage
    }

    // MARK: - The cases every platform replays

    private struct Cases: Decodable {
        let cases: [Case]
    }

    private struct Case: Decodable {
        struct Input: Decodable {
            let id: String
            let label: String
            let carrier: String
            let archivedAt: String?
            let createdAt: String?
            let scans: [[String?]]
        }

        let name: String
        let timeZone: String
        let parcels: [Input]
    }

    func testThePassportCasesCountAsOnTheWebAndAndroid() throws {
        let url = try XCTUnwrap(Bundle(for: Self.self).url(forResource: "PassportCases", withExtension: "json"))
        let data = try Data(contentsOf: url)
        let cases = try JSONDecoder().decode(Cases.self, from: data).cases
        let file = try XCTUnwrap(JSONSerialization.jsonObject(with: data) as? [String: Any])
        let expectations = try XCTUnwrap(file["cases"] as? [[String: Any]]).map { $0["expect"] as? [String: Any] }
        XCTAssertFalse(cases.isEmpty)
        XCTAssertEqual(cases.count, expectations.count)
        for (input, expected) in zip(cases, expectations) {
            let expected = try XCTUnwrap(expected, input.name)
            let actual = try outcome(input)
            for key in ["deliveredCount", "carrierCount", "stamps", "countries", "numbers", "year2026"] {
                XCTAssertEqual(try json(actual[key] as Any), try json(expected[key] as Any), "\(input.name): \(key)")
            }
        }
    }

    /// What a case's passport holds, in the fields the web's test compares.
    private func outcome(_ input: Case) throws -> [String: Any] {
        // Ids in the order the web sorts its string ids, so ties between parcels fall the same way.
        let ids = input.parcels.map(\.id).sorted()
        func uuid(_ prefix: Int, _ index: Int) -> UUID { UUID(uuidString: String(format: "%08d-0000-0000-0000-%012d", prefix, index))! }
        let parcelIDs = Dictionary(uniqueKeysWithValues: ids.enumerated().map { ($1, uuid(0, $0)) })
        let names = Dictionary(uniqueKeysWithValues: parcelIDs.map { ($1, $0) })
        let parcels = try input.parcels.map { parcel -> Parcel in
            let id = try XCTUnwrap(parcelIDs[parcel.id])
            let events = try parcel.scans.enumerated().map { index, scan -> TrackingEvent in
                let stage = try XCTUnwrap(TrackingStage(rawValue: scan[0] ?? ""), "\(input.name): \(scan)")
                var event = TrackingEvent(id: uuid(1 + ids.firstIndex(of: parcel.id)!, index), packageID: id, stage: stage,
                                          description: stage.rawValue, location: scan.count > 2 ? scan[2] : nil, occurredAt: scan[1] ?? "")
                if scan.count > 3, let placed = scan[3] {
                    event.place = EventPlace(latitude: 0, longitude: 0, precision: .city, country: placed, name: event.location ?? placed)
                }
                return event
            }
            return Self.parcel(id: id, label: parcel.label, carrier: CarrierID(rawValue: parcel.carrier), archivedAt: parcel.archivedAt,
                               createdAt: parcel.createdAt ?? "2026-01-01T00:00:00Z", events: events)
        }
        let book = PassportBook(parcels: parcels, timeZone: try XCTUnwrap(TimeZone(identifier: input.timeZone)))
        let year = book.year(2026)
        let instant = ISO8601DateFormatter()
        instant.formatOptions = [.withInternetDateTime, .withFractionalSeconds]
        func iso(_ date: Date?) -> Any { date.map(instant.string(from:)) ?? NSNull() }
        func name(_ id: UUID?) -> Any { id.flatMap { names[$0] } ?? NSNull() }
        return [
            "deliveredCount": book.stats.deliveredCount,
            "carrierCount": book.stats.carrierCount,
            "stamps": Dictionary(uniqueKeysWithValues: book.stamps.map { stamp in
                (stamp.key, ["count": stamp.count, "earned": stamp.earned, "earnedAt": iso(stamp.earnedAt), "parcel": name(stamp.parcelID)] as [String: Any])
            }),
            "countries": book.countries.map { country in
                ["code": country.code, "number": country.number, "firstAt": iso(country.firstAt), "parcel": name(country.parcelID),
                 "count": country.count, "continent": country.continent.rawValue] as [String: Any]
            },
            "numbers": [
                "busiestMonth": book.numbers.busiestMonth.map { ["month": $0.month, "count": $0.count] as [String: Any] } ?? NSNull(),
                "carrierCount": book.numbers.carrierCount,
                "topCarrier": book.numbers.topCarrier.map { ["carrier": $0.carrier.rawValue, "count": $0.count] as [String: Any] } ?? NSNull(),
            ] as [String: Any],
            "year2026": [
                "deliveredCount": year.deliveredCount, "countryCount": year.countryCount,
                "countries": year.countries.map { [$0.code, $0.count] as [Any] },
                "stamps": year.stamps.map(\.key),
                "fastest": year.fastest.map { Int(($0 * 1000).rounded()) } ?? NSNull(),
            ] as [String: Any],
        ]
    }

    private func json(_ value: Any) throws -> String {
        String(decoding: try JSONSerialization.data(withJSONObject: value, options: [.sortedKeys, .fragmentsAllowed]), as: UTF8.self)
    }

    // MARK: - News

    private func delivered(_ id: Int, _ carrier: CarrierID, _ at: String) -> Parcel {
        let parcelID = UUID(uuidString: String(format: "00000000-0000-0000-0000-%012d", id))!
        return Self.parcel(id: parcelID, label: "Parcel \(id)", carrier: carrier, events: [
            TrackingEvent(id: UUID(), packageID: parcelID, stage: .inTransit, description: "In transit", occurredAt: at.replacingOccurrences(of: "T12", with: "T08")),
            TrackingEvent(id: UUID(), packageID: parcelID, stage: .delivered, description: "Delivered", occurredAt: at),
        ])
    }

    private func defaults() throws -> UserDefaults {
        let suite = "passport-tests-\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        addTeardownBlock { UserDefaults().removePersistentDomain(forName: suite) }
        return defaults
    }

    func testNewStampsWaitOnTheirCardsUntilThePassportOpens() throws {
        let defaults = try defaults()
        let news = PassportNews(defaults: defaults)
        let first = [delivered(1, .dhl, "2026-09-03T12:00:00Z"), delivered(2, .ups, "2026-09-04T12:00:00Z")]
        // Before the list has loaded nothing is written; once it has, what the passport already holds counts as seen.
        news.update(parcels: [], scope: "test", loaded: false)
        XCTAssertNil(defaults.string(forKey: "peek-passport-seen:test"))
        news.update(parcels: first, scope: "test", loaded: true)
        XCTAssertTrue(news.unseen.isEmpty)
        XCTAssertEqual(defaults.string(forKey: "peek-passport-seen:test"), #"["countries:on","stamp:express","stamp:first"]"#)

        // A third carrier's delivery earns Well connected, on that parcel's card.
        let third = delivered(3, .fedex, "2026-09-05T12:00:00Z")
        news.update(parcels: first + [third], scope: "test", loaded: true)
        XCTAssertEqual(news.unseen.map(\.seen), ["stamp:connected"])
        XCTAssertEqual(news.byParcel.keys.sorted(by: { $0.uuidString < $1.uuidString }), [third.id])
        let copy = PassportStampCopy(localizer: localizer)
        let item = try XCTUnwrap(news.unseen.first)
        XCTAssertEqual("\(copy.newsHeading(1)): \(copy.name(item))", "New stamp: Well connected")
        XCTAssertEqual(copy.accessibilityLabel(try XCTUnwrap(item.stamp), fresh: true), "Well connected, Earned on 5 September 2026, New")

        // Another account keeps its own list.
        news.update(parcels: first + [third], scope: "other", loaded: true)
        XCTAssertTrue(news.unseen.isEmpty)
        news.update(parcels: first + [third], scope: "test", loaded: true)
        XCTAssertEqual(news.unseen.map(\.seen), ["stamp:connected"])

        // Opening the passport marks everything earned seen, sorted.
        news.markSeen()
        XCTAssertTrue(news.unseen.isEmpty)
        XCTAssertTrue(news.byParcel.isEmpty)
        XCTAssertEqual(defaults.string(forKey: "peek-passport-seen:test"), #"["countries:on","stamp:connected","stamp:express","stamp:first"]"#)
    }

    func testACountryADeliveryFirstReachedIsNewsUntilThePassportOpens() throws {
        let defaults = try defaults()
        let news = PassportNews(defaults: defaults)
        let first = [located(1, "2026-09-03T12:00:00Z", "Wien, AT")]
        news.update(parcels: first, scope: "test", loaded: true)
        XCTAssertTrue(news.unseen.isEmpty)
        XCTAssertEqual(defaults.string(forKey: "peek-passport-seen:test"),
                       #"["countries:on","country:AT","stamp:express","stamp:first","stamp:rightNextDoor"]"#)
        XCTAssertEqual(PassportView.openingPage(news.unseen), PassportView.countries)

        // Japan, reached for the first time, is news on that parcel's card, the line naming it in the app's language.
        let tokyo = located(2, "2026-09-08T12:00:00Z", "Tokyo, JP")
        news.update(parcels: first + [tokyo], scope: "test", loaded: true)
        XCTAssertEqual(news.unseen.map(\.seen), ["country:JP"])
        XCTAssertEqual(news.byParcel.keys.map { $0 }, [tokyo.id])
        let copy = PassportStampCopy(localizer: localizer)
        let items = try XCTUnwrap(news.byParcel[tokyo.id])
        XCTAssertEqual("\(copy.newsHeading(items.count)): \(items.map(copy.name).joined(separator: ", "))", "New stamp: Japan")
        let japan = try XCTUnwrap(items.first?.country)
        XCTAssertEqual(copy.accessibilityLabel(japan, fresh: true), "Japan, First reached on 8 September 2026, 1 parcel, New")
        XCTAssertEqual(copy.details(japan), "First reached on 8 September 2026 · Order 2\n1 parcel")
        // The passport opens on the Countries page, which holds the news.
        XCTAssertEqual(PassportView.openingPage(news.unseen), PassportView.countries)
        localizer.language = .fr
        XCTAssertEqual(copy.name(try XCTUnwrap(items.first)), "Japon")
        localizer.language = .en

        news.markSeen()
        XCTAssertTrue(news.unseen.isEmpty)
        XCTAssertEqual(defaults.string(forKey: "peek-passport-seen:test"),
                       #"["countries:on","country:AT","country:JP","stamp:express","stamp:first","stamp:rightNextDoor"]"#)
    }

    func testTheCountriesAListKeptBeforeTheyCountedAreTakenAsSeen() throws {
        let defaults = try defaults()
        defaults.set(#"["stamp:first"]"#, forKey: "peek-passport-seen:test")
        let news = PassportNews(defaults: defaults)
        let parcels = [located(1, "2026-09-03T12:00:00Z", "Wien, AT"), delivered(2, .ups, "2026-09-04T12:00:00Z")]
        news.update(parcels: parcels, scope: "test", loaded: true)
        // The stamps are news; Austria, already in the passport before countries counted, is not.
        XCTAssertEqual(news.unseen.map(\.seen), ["stamp:rightNextDoor", "stamp:express"])
        XCTAssertEqual(defaults.string(forKey: "peek-passport-seen:test"), #"["countries:on","country:AT","stamp:first"]"#)
        XCTAssertEqual(PassportView.openingPage(news.unseen), PassportView.stamps)
        XCTAssertEqual(PassportView.openingPage([]), PassportView.countries)

        // From then on a new country is news like a stamp.
        let tokyo = located(3, "2026-09-08T12:00:00Z", "Tokyo, JP")
        news.update(parcels: parcels + [tokyo], scope: "test", loaded: true)
        XCTAssertEqual(Set(news.unseen.map(\.seen)), ["stamp:rightNextDoor", "stamp:express", "country:JP"])
        XCTAssertEqual(PassportView.openingPage(news.unseen), PassportView.countries)
    }

    func testTheNumbersPageNamesTheBusiestMonthAndTheCarriers() throws {
        let utc = try XCTUnwrap(TimeZone(identifier: "UTC"))
        let copy = PassportStampCopy(localizer: localizer)
        // Months count the parcels added in them, from two.
        func added(_ id: Int, _ carrier: CarrierID, _ at: String) -> Parcel {
            Self.parcel(id: UUID(uuidString: String(format: "00000000-0000-0000-0000-%012d", id))!, label: "Parcel \(id)", carrier: carrier,
                        createdAt: at, events: [])
        }
        let one = PassportBook(parcels: [added(1, .dhl, "2026-09-03T12:00:00Z")], timeZone: utc)
        XCTAssertNil(copy.busiestMonth(one.numbers))
        XCTAssertEqual(copy.carriers(one.numbers), "DHL")
        let three = PassportBook(parcels: [added(1, .dhl, "2026-09-03T12:00:00Z"), added(2, .ups, "2026-11-04T12:00:00Z"),
                                           added(3, .ups, "2026-11-05T12:00:00Z")], timeZone: utc)
        XCTAssertEqual(copy.busiestMonth(three.numbers), "November 2026 · 2 parcels")
        XCTAssertEqual(copy.carriers(three.numbers), "2 · most often UPS")
        localizer.language = .ru
        // The month alone, in its standalone form.
        XCTAssertEqual(copy.busiestMonth(three.numbers)?.hasPrefix("ноябрь 2026"), true, copy.busiestMonth(three.numbers) ?? "")
        localizer.language = .en
        let none = PassportBook(parcels: [], timeZone: utc)
        XCTAssertNil(copy.busiestMonth(none.numbers))
        XCTAssertNil(copy.carriers(none.numbers))
    }

    func testOneDeliveryNamesEveryStampItBroughtOnOneLine() throws {
        let news = PassportNews(defaults: try defaults())
        news.update(parcels: [], scope: "test", loaded: true)
        news.update(parcels: [delivered(1, .dhl, "2026-12-03T12:00:00Z")], scope: "test", loaded: true)
        let copy = PassportStampCopy(localizer: localizer)
        let items = try XCTUnwrap(news.byParcel.values.first)
        XCTAssertEqual("\(copy.newsHeading(items.count)): \(items.map(copy.name).joined(separator: ", "))",
                       "3 new stamps: First arrival, Express arrival, Home for the holidays")
    }

    func testAStampSaysItsDayOrItsProgress() throws {
        let book = PassportBook(parcels: [delivered(1, .dhl, "2026-09-03T12:00:00Z"), delivered(2, .ups, "2026-09-04T12:00:00Z")],
                                timeZone: try XCTUnwrap(TimeZone(identifier: "UTC")))
        let copy = PassportStampCopy(localizer: localizer)
        let connected = try XCTUnwrap(book.stamps.first { $0.key == "connected" })
        XCTAssertEqual(copy.accessibilityLabel(connected, fresh: false), "Well connected, 2 of 3")
        let first = try XCTUnwrap(book.stamps.first { $0.key == "first" })
        XCTAssertEqual(copy.accessibilityLabel(first, fresh: false), "First arrival, Earned on 3 September 2026")
        XCTAssertEqual(copy.details(first), "\(copy.explanation("first"))\nEarned on 3 September 2026 · Parcel 1")
        // Earned stamps first, in the passport's order.
        XCTAssertEqual(book.stamps.filter(\.earned).map(\.key), ["first", "express"])
    }

    func testFriendsSeeOnlyTheStampsOlderAppsKnowAndRealCarriers() throws {
        let utc = try XCTUnwrap(TimeZone(identifier: "UTC"))
        let placeholders = PassportBook(parcels: [delivered(1, .dhl, "2026-09-03T12:00:00Z"), delivered(2, .unknown, "2026-09-04T12:00:00Z"),
                                                  delivered(3, .internationalPost, "2026-09-05T12:00:00Z")], timeZone: utc)
        XCTAssertEqual(placeholders.stats.carrierCount, 1)
        XCTAssertFalse(placeholders.sharedStamps.contains(.connected))
        let three = PassportBook(parcels: [delivered(1, .dhl, "2026-09-03T12:00:00Z"), delivered(2, .ups, "2026-09-04T12:00:00Z"),
                                           delivered(3, .fedex, "2026-09-05T12:00:00Z")], timeZone: utc)
        XCTAssertTrue(three.sharedStamps.contains(.connected))
        XCTAssertTrue(Set(three.stamps.filter(\.earned).map(\.key)).isSuperset(of: three.sharedStamps.map(\.rawValue)))
    }

    // MARK: - The year's card

    private func located(_ id: Int, _ at: String, _ location: String?) -> Parcel {
        let parcelID = UUID(uuidString: String(format: "00000000-0000-0000-0000-%012d", id))!
        return Self.parcel(id: parcelID, label: "Order \(id)", carrier: .dhl, events: [
            TrackingEvent(id: UUID(), packageID: parcelID, stage: .inTransit, description: "Sorted", location: location,
                          occurredAt: at.replacingOccurrences(of: "T12", with: "T00")),
            TrackingEvent(id: UUID(), packageID: parcelID, stage: .delivered, description: "Delivered", location: location, occurredAt: at),
        ])
    }

    func testTheYearsCardIsOfferedInDecemberOnceAParcelWasDeliveredThatYear() throws {
        var calendar = Calendar(identifier: .gregorian)
        calendar.timeZone = try XCTUnwrap(TimeZone(identifier: "Europe/Zurich"))
        XCTAssertNil(YearCard.year(try XCTUnwrap(DateParser.date("2026-11-30T12:00:00Z")), calendar: calendar))
        XCTAssertEqual(YearCard.year(try XCTUnwrap(DateParser.date("2026-12-01T09:00:00Z")), calendar: calendar), 2026)
        XCTAssertEqual(PassportBook(parcels: [located(1, "2026-03-02T12:00:00Z", "Zürich, CH")]).year(2026).deliveredCount, 1)
        XCTAssertEqual(PassportBook(parcels: [located(1, "2025-03-02T12:00:00Z", nil)]).year(2026).deliveredCount, 0)
    }

    func testTheYearsCardSaysOnlyCountsCountriesAndTheFastestTime() throws {
        let utc = try XCTUnwrap(TimeZone(identifier: "UTC"))
        let year = PassportBook(parcels: [located(1, "2026-03-02T12:00:00Z", "Zürich, CH")], timeZone: utc).year(2026)
        let words = YearCardWords(year: year, localizer: localizer, timeZone: utc)
        XCTAssertEqual(words.lineOne, "1 delivered · 1 country")
        XCTAssertEqual(words.lineTwo, "Fastest 12h · peektracker.com")
        XCTAssertEqual(words.onlyCountry, "Switzerland")
        XCTAssertEqual(words.description, "2026 in parcels. 1 delivered, 1 country: Switzerland. Fastest 12h.")
        XCTAssertFalse("\(words)".contains("Order"))
        XCTAssertEqual(PassportDates.inkDay(try XCTUnwrap(DateParser.date("2026-08-12T12:00:00Z")), language: .en, timeZone: utc), "12 AUG 2026")
        XCTAssertEqual(YearCard.journeyDuration(34 * 3600, language: .en), "1d 10h")
        XCTAssertEqual(YearCard.journeyDuration(20, language: .en), "1m")
    }

    func testTheYearsCardLaysOutUpToSixCountryStampsRowsCentred() {
        let five = YearCard.countryLayout(5, top: 430, bottom: 900)
        XCTAssertEqual(five.map { [Int($0.minX.rounded()), Int($0.minY.rounded())] }, [[120, 461], [416, 461], [712, 461], [268, 685], [564, 685]])
        let one = YearCard.countryLayout(1, top: 430, bottom: 900)[0]
        XCTAssertEqual([one.minX, one.width, one.height], [330, 420, 312])
        XCTAssertTrue(YearCard.countryLayout(7, top: 430, bottom: 900).isEmpty)
    }

    func testTheYearsCardDrawsAt1080By1350() throws {
        let year = PassportBook(parcels: [located(1, "2026-03-02T12:00:00Z", "Zürich, CH")]).year(2026)
        let image = try XCTUnwrap(YearCard.render(year, words: YearCardWords(year: year, localizer: localizer)))
        XCTAssertEqual([image.width, image.height], [1080, 1350])
        XCTAssertNotNil(YearCard.png(image))
    }

    // MARK: - Friends

    func testAFriendsCardCarriesCountriesOnlyWithStatsAndCountriesOn() throws {
        let now = try XCTUnwrap(DateParser.date("2026-09-08T12:00:00Z"))
        let parcels = [located(1, "2026-09-03T12:00:00Z", "Zürich, CH"), located(2, "2026-09-04T12:00:00Z", "Berlin, Germany"),
                       located(3, "2026-09-05T12:00:00Z", "Basel, CH")]
        let card = FriendsStore.ownCard(parcels: parcels, profile: FriendProfile(nickname: "Alex", shareStats: true, shareArrival: false, shareCountries: true), now: now)
        // Each country by code with how many went through it, in code order: no days and no order of arrival.
        XCTAssertEqual(card.stats?.countries, [FriendCountry(code: "CH", count: 2), FriendCountry(code: "DE", count: 1)])
        let encoded = try XCTUnwrap(String(data: JSONEncoder().encode(card), encoding: .utf8))
        for secret in ["2026", "Zürich", "Berlin", "Order"] { XCTAssertFalse(encoded.contains(secret), secret) }
        // A profile saved before the switch, one that turned it off, and one that keeps its stats private share none.
        for profile in [FriendProfile(nickname: "Alex", shareStats: true, shareArrival: false),
                        FriendProfile(nickname: "Alex", shareStats: true, shareArrival: false, shareCountries: false),
                        FriendProfile(nickname: "Alex", shareStats: false, shareArrival: false, shareCountries: true)] {
            let card = FriendsStore.ownCard(parcels: parcels, profile: profile, now: now)
            XCTAssertNil(card.stats?.countries)
            if profile.shareStats { XCTAssertEqual(card.stats?.deliveredCount, 3) }
        }
    }

    func testTheCountriesSwitchStartsOnForANewProfileAndRidesOnStats() throws {
        let new = FriendsSharing(nil)
        XCTAssertEqual([new.stats, new.sharesCountries, new.arrival], [true, true, false])
        // A profile saved before the switch reads as not sharing its countries; one that chose it keeps its choice.
        XCTAssertFalse(FriendsSharing(FriendProfile(nickname: "Alex", shareStats: true, shareArrival: false)).sharesCountries)
        XCTAssertTrue(FriendsSharing(FriendProfile(nickname: "Alex", shareStats: true, shareArrival: false, shareCountries: true)).sharesCountries)
        // While Stats & stamps are off, the switch shows off and rests; it comes back as it was.
        var sharing = new
        sharing.stats = false
        XCTAssertFalse(sharing.sharesCountries)
        XCTAssertTrue(sharing.countriesRest)
        sharing.stats = true
        XCTAssertTrue(sharing.sharesCountries)
        XCTAssertFalse(sharing.countriesRest)
        // Every save the form makes says whether it shares its countries.
        let saved = try JSONSerialization.jsonObject(with: JSONEncoder().encode(FriendsActionRequest.saveProfile(sharing.profile(nickname: "Alex")))) as? [String: Any]
        XCTAssertEqual(saved?["action"] as? String, "save_profile")
        XCTAssertEqual(saved?["shareCountries"] as? Bool, true)
        sharing.countries = false
        XCTAssertEqual(FriendsActionRequest.saveProfile(sharing.profile(nickname: "Alex")).shareCountries, false)
        // A profile that has no choice yet passes none, so the server keeps what it has.
        let kept = try JSONSerialization.jsonObject(with: JSONEncoder().encode(FriendsActionRequest.saveProfile(FriendProfile(nickname: "Alex", shareStats: true, shareArrival: false)))) as? [String: Any]
        XCTAssertNil(kept?["shareCountries"])
    }

    func testAFriendsStampsStandBesideTheReaders() throws {
        let utc = try XCTUnwrap(TimeZone(identifier: "UTC"))
        let stats = FriendStats(deliveredCount: 12, averageDays: 3, stamps: [.first, .ten, .connected, .express],
                                countries: [FriendCountry(code: "CH", count: 9), FriendCountry(code: "DE", count: 1),
                                            FriendCountry(code: "FR", count: 2), FriendCountry(code: "US", count: 1)])
        func headings(_ comparison: FriendPassportComparison) -> [String] {
            comparison.groups.map { "\(FriendPassportComparison.heading($0.kind, nickname: "Mila", localizer: localizer)) \($0.stamps.count)" }
        }
        let copy = PassportStampCopy(localizer: localizer)
        // The reader has First arrival and Express arrival; Mila has those, Double digits and Well connected.
        let reader = FriendPassportComparison(stats: stats, book: PassportBook(parcels: [delivered(1, .dhl, "2026-09-03T12:00:00Z")], timeZone: utc), language: .en)
        XCTAssertEqual(headings(reader), ["You both have 2", "Only Mila 2"])
        XCTAssertEqual(reader.groups[0].stamps.map { copy.title($0.key) }, ["First arrival", "Express arrival"])
        XCTAssertEqual(reader.groups[1].stamps.map { copy.title($0.key) }, ["Double digits", "Well connected"])
        XCTAssertEqual(reader.groups[1].stamps.compactMap { FriendPassportComparison.progress($0, localizer: localizer) }, ["You’re at 1 of 10", "You’re at 1 of 3"])
        // One count runs through the stamps, then the countries, as they rise in.
        XCTAssertEqual(reader.groups.map(\.start), [0, 2])
        XCTAssertEqual(reader.countriesStart, 4)
        // Her countries, the most-visited first, then by name; the reader's parcel went through none of them.
        XCTAssertEqual(reader.countries.map(\.code), ["CH", "FR", "DE", "US"])
        XCTAssertEqual(reader.countries.map(\.name), ["Switzerland", "France", "Germany", "United States"])
        XCTAssertEqual(reader.common, 0)

        // With no parcels of the reader's, each of Mila's stamps is hers alone, with how far the reader is when it takes more than one.
        let nobody = FriendPassportComparison(stats: stats, book: PassportBook(parcels: [], timeZone: utc), language: .en)
        XCTAssertEqual(headings(nobody), ["Only Mila 4"])
        XCTAssertEqual(nobody.groups[0].stamps.map { FriendPassportComparison.progress($0, localizer: localizer) },
                       [nil, "You’re at 0 of 10", "You’re at 0 of 3", nil])

        // A reader in Switzerland has a stamp Mila lacks, and one of her countries.
        let traveller = PassportBook(parcels: [located(1, "2026-09-03T12:00:00Z", "Zürich, CH")], timeZone: utc)
        let mine = FriendPassportComparison(stats: stats, book: traveller, language: .en)
        XCTAssertEqual(mine.groups.map(\.kind), [.both, .theirs, .mine])
        XCTAssertEqual(mine.groups.last?.stamps.map(\.key), traveller.sharedStamps.map(\.rawValue).filter { !["first", "ten", "connected", "express"].contains($0) })
        XCTAssertEqual(mine.common, 1)
        XCTAssertEqual(localizer.text("friends.countriesBoth", ["count": mine.common]), "You both have 1")

        // Neither holding a shared stamp: no groups, and the friend's collection shows instead.
        XCTAssertTrue(FriendPassportComparison(stats: FriendStats(deliveredCount: 0, stamps: []), book: PassportBook(parcels: []), language: .en).groups.isEmpty)
    }

    // MARK: - Parcels

    static func parcel(id: UUID, label: String, carrier: CarrierID, archivedAt: String? = nil, createdAt: String = "2026-01-01T00:00:00Z", events: [TrackingEvent]) -> Parcel {
        Parcel(
            id: id,
            trackingNumber: "T\(id.uuidString.prefix(8))",
            label: label,
            carrier: carrier,
            createdAt: createdAt,
            expectedDelivery: nil,
            lastStatusText: nil,
            lastSyncedAt: nil,
            syncStatus: .ok,
            syncError: nil,
            trackingURL: nil,
            dpdPostcode: nil,
            carrierData: nil,
            archivedAt: archivedAt,
            notificationsMuted: false,
            trackingEvents: events
        )
    }
}
