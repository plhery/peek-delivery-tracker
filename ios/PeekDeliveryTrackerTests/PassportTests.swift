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
            for key in ["deliveredCount", "carrierCount", "stamps", "countries", "year2026"] {
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
            return Self.parcel(id: id, label: parcel.label, carrier: CarrierID(rawValue: parcel.carrier), archivedAt: parcel.archivedAt, events: events)
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
        XCTAssertEqual(defaults.string(forKey: "peek-passport-seen:test"), #"["stamp:express","stamp:first"]"#)

        // A third carrier's delivery earns Well connected, on that parcel's card.
        let third = delivered(3, .fedex, "2026-09-05T12:00:00Z")
        news.update(parcels: first + [third], scope: "test", loaded: true)
        XCTAssertEqual(news.unseen.map(\.seen), ["stamp:connected"])
        XCTAssertEqual(news.byParcel.keys.sorted(by: { $0.uuidString < $1.uuidString }), [third.id])
        let copy = PassportStampCopy(localizer: localizer)
        let item = try XCTUnwrap(news.unseen.first)
        XCTAssertEqual("\(copy.newsHeading(1)): \(copy.title(item.stamp.key))", "New stamp: Well connected")
        XCTAssertEqual(copy.accessibilityLabel(item.stamp, fresh: true), "Well connected, Earned on 5 September 2026, New")

        // Another account keeps its own list.
        news.update(parcels: first + [third], scope: "other", loaded: true)
        XCTAssertTrue(news.unseen.isEmpty)
        news.update(parcels: first + [third], scope: "test", loaded: true)
        XCTAssertEqual(news.unseen.map(\.seen), ["stamp:connected"])

        // Opening the passport marks everything earned seen, sorted.
        news.markSeen()
        XCTAssertTrue(news.unseen.isEmpty)
        XCTAssertTrue(news.byParcel.isEmpty)
        XCTAssertEqual(defaults.string(forKey: "peek-passport-seen:test"), #"["stamp:connected","stamp:express","stamp:first"]"#)
    }

    func testOneDeliveryNamesEveryStampItBroughtOnOneLine() throws {
        let news = PassportNews(defaults: try defaults())
        news.update(parcels: [], scope: "test", loaded: true)
        news.update(parcels: [delivered(1, .dhl, "2026-12-03T12:00:00Z")], scope: "test", loaded: true)
        let copy = PassportStampCopy(localizer: localizer)
        let items = try XCTUnwrap(news.byParcel.values.first)
        XCTAssertEqual("\(copy.newsHeading(items.count)): \(items.map { copy.title($0.stamp.key) }.joined(separator: ", "))",
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

    // MARK: - Parcels

    static func parcel(id: UUID, label: String, carrier: CarrierID, archivedAt: String? = nil, events: [TrackingEvent]) -> Parcel {
        Parcel(
            id: id,
            trackingNumber: "T\(id.uuidString.prefix(8))",
            label: label,
            carrier: carrier,
            createdAt: "2026-01-01T00:00:00Z",
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
