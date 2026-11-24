import SwiftUI
import XCTest
@testable import PeekDeliveryTracker

/// Peek AI's rules and words, as the web's tests (src/lib/peekAi.test.ts) have them.
@MainActor
final class PeekAiTests: XCTestCase {
    /// Tuesday 24 November 2026, 15:30 on this device's clock.
    private let now = PeekAiTests.local(2026, 11, 24, 15, 30)
    private var suite = ""
    private var defaults: UserDefaults!
    private var english: Localizer!
    private var originalLanguage: AppLanguage = .en

    private static func local(_ year: Int, _ month: Int, _ day: Int, _ hour: Int = 0, _ minute: Int = 0) -> Date {
        Calendar.current.date(from: DateComponents(year: year, month: month, day: day, hour: hour, minute: minute))!
    }

    private func at(_ year: Int, _ month: Int, _ day: Int, _ hour: Int = 0, _ minute: Int = 0) -> Date {
        Self.local(year, month, day, hour, minute)
    }

    override func setUp() async throws {
        suite = "peek-ai-tests-\(UUID().uuidString)"
        defaults = UserDefaults(suiteName: suite)
        english = Localizer(defaults: defaults, systemUses24HourTime: { true })
        originalLanguage = english.language
        english.language = .en
    }

    override func tearDown() async throws {
        english.language = originalLanguage
        defaults.removePersistentDomain(forName: suite)
    }

    private let notToday = PeekAi(says: .notToday, why: .noOutForDelivery, sure: 0.82, carrierDay: "2026-11-24")
    private let byTime = PeekAi(
        says: .byTime, why: .outForDelivery, sure: 0.86, time: "16:00", outSince: "2026-11-24T08:12:00+01:00",
        hours: zip(["08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"],
                   [0.02, 0.05, 0.12, 0.2, 0.22, 0.16, 0.1, 0.06, 0.04, 0.03]).map { PeekAiHour(time: $0, share: $1) }
    )
    private var noDate: PeekAi {
        PeekAi(
            says: .byDay, why: .noDate, sure: 0.81, day: "2026-11-26",
            last: PeekAiStep(kind: .depot, at: DateParser.isoString(now.addingTimeInterval(-2 * 86_400 - 3_600))),
            days: [("2026-11-24", 0.05), ("2026-11-25", 0.34), ("2026-11-26", 0.41), ("2026-11-27", 0.12)].map { PeekAiDay(day: $0, p: $1) }
        )
    }
    private let sooner = PeekAi(says: .sooner, why: .ahead, sure: 0.71, day: "2026-11-25", carrierDay: "2026-11-27")

    private func with(_ ai: PeekAi, _ change: (inout PeekAi) -> Void) -> PeekAi {
        var copy = ai
        change(&copy)
        return copy
    }

    // MARK: On this device's clock

    func testItSaysNotTodayOnlyOnTheCarriersDayOrTheDayTheParcelWentOut() {
        XCTAssertEqual(notToday.current(at: now), notToday)
        XCTAssertNil(notToday.current(at: at(2026, 11, 25, 9)))
        let late = PeekAi(says: .notToday, why: .lateOut, sure: 0.9, outSince: "2026-11-24T08:12:00+01:00")
        XCTAssertEqual(late.current(at: now), late)
        XCTAssertNil(late.current(at: at(2026, 11, 25, 0, 5)))
    }

    func testItNamesAnHourUntilItComesOnTheDayTheParcelWentOut() {
        XCTAssertEqual(byTime.current(at: now), byTime)
        XCTAssertNil(byTime.current(at: at(2026, 11, 24, 16, 0)))
        XCTAssertNil(byTime.current(at: at(2026, 11, 25, 9)))
    }

    func testItHoldsADayUntilItHasPassedAndSoonerUntilTheCarriersOwnDay() {
        XCTAssertEqual(noDate.current(at: at(2026, 11, 26, 23, 59)), noDate)
        XCTAssertNil(noDate.current(at: at(2026, 11, 27, 0, 1)))
        // Earlier than the carrier's date holds until that date comes.
        XCTAssertEqual(sooner.current(at: at(2026, 11, 26, 23, 59)), sooner)
        XCTAssertNil(sooner.current(at: at(2026, 11, 27, 0, 1)))
    }

    /// A widget, unseen, drops the words when they stop holding: at the hour named, the midnight after the day, or the
    /// carrier's own day for "maybe sooner".
    func testItKnowsWhenItStopsHolding() {
        XCTAssertEqual(byTime.end(after: now), at(2026, 11, 24, 16))
        XCTAssertEqual(notToday.end(after: now), at(2026, 11, 25))
        XCTAssertEqual(noDate.end(after: now), at(2026, 11, 27))
        XCTAssertEqual(sooner.end(after: now), at(2026, 11, 27))
        XCTAssertNil(with(noDate) { $0.says = .thisWeek; $0.day = nil }.end(after: now))
    }

    /// The moment the server gives (`ends_at`) ends it, whatever this device's zone: "by 16:00" in Zurich ends at 15:00
    /// UTC on a device set to Tokyo too, where the day has already moved on.
    func testItEndsAtTheMomentTheServerGivesWhateverThisDevicesZone() throws {
        let home = NSTimeZone.default
        NSTimeZone.default = try XCTUnwrap(TimeZone(identifier: "Asia/Tokyo"))
        defer { NSTimeZone.default = home }
        func utc(_ value: String) -> Date { ISO8601DateFormatter().date(from: value)! }

        let hour = with(byTime) { $0.endsAt = "2026-11-24T16:00:00+01:00" }
        XCTAssertNil(byTime.current(at: utc("2026-11-24T14:59:00Z")), "On this device's days alone, 23:59 in Tokyo is past 16:00")
        XCTAssertEqual(hour.current(at: utc("2026-11-24T14:59:00Z")), hour)
        XCTAssertNil(hour.current(at: utc("2026-11-24T15:00:00Z")))
        XCTAssertEqual(hour.end(after: utc("2026-11-24T10:00:00Z")), utc("2026-11-24T15:00:00Z"))

        // 07:59 on the 25th in Tokyo, still the 24th in Zurich.
        let day = with(notToday) { $0.endsAt = "2026-11-25T00:00:00+01:00" }
        XCTAssertNil(notToday.current(at: utc("2026-11-24T22:59:00Z")))
        XCTAssertEqual(day.current(at: utc("2026-11-24T22:59:00Z")), day)
        XCTAssertNil(day.current(at: utc("2026-11-24T23:00:00Z")))

        // A list left open draws its card again at Tokyo's midnight, and at the moment it ends.
        var parcel = Parcel(id: UUID(), trackingNumber: "TEST0000000006", label: "Test parcel", carrier: .unknown,
                            createdAt: "2026-11-20T08:00:00Z", syncStatus: .ok, notificationsMuted: false)
        parcel.trackingEvents = [event(parcel, .inTransit)]
        parcel.peekAi = day
        let noon = utc("2026-11-24T12:00:00Z")
        XCTAssertEqual(parcel.peekAiChanges(after: noon), [utc("2026-11-24T15:00:00Z"), utc("2026-11-24T23:00:00Z")])

        // A widget drops the words then.
        let widget = try XCTUnwrap(ParcelStore.widgetDeliveries(of: [parcel], localizer: english, peekAi: true, now: noon).first)
        XCTAssertEqual(widget.peekAi, "not today")
        XCTAssertEqual(widget.peekAiUntil, utc("2026-11-24T23:00:00Z"))
        XCTAssertEqual(widget.worded(at: utc("2026-11-24T22:59:00Z")).peekAi, "not today")
        XCTAssertNil(widget.worded(at: utc("2026-11-24T23:00:00Z")).peekAi)
    }

    /// The server writes its days on the Gregorian calendar; a device that shows the Buddhist or Japanese one reads them on
    /// the same days.
    func testItReadsTheServersDaysOnTheGregorianCalendarWhateverThisDeviceShows() throws {
        XCTAssertEqual(PeekAi.calendar.identifier, .gregorian)
        XCTAssertEqual(PeekAi.calendar.timeZone, Calendar.current.timeZone)
        for identifier in [Calendar.Identifier.buddhist, .japanese] {
            var other = Calendar(identifier: identifier)
            other.timeZone = Calendar.current.timeZone
            let parts = other.dateComponents([.era, .year, .month, .day, .hour, .minute], from: now)
            XCTAssertNotEqual(parts.year, 2026, "\(identifier) counts its own years")
            let moment = try XCTUnwrap(other.date(from: parts))
            XCTAssertEqual(PeekAi.day(of: moment), "2026-11-24")
            XCTAssertEqual(PeekAi.date(of: "2026-11-24"), other.startOfDay(for: moment))
        }
    }

    /// A list left open draws a card again where its words can change: at the hour named, and at each midnight until the
    /// day's estimate ends.
    func testACardIsDrawnAgainWhereItsWordsCanChange() {
        var parcel = Parcel(id: UUID(), trackingNumber: "TEST0000000004", label: "Test parcel", carrier: .unknown,
                            createdAt: "2026-11-20T08:00:00Z", syncStatus: .ok, notificationsMuted: false)
        parcel.trackingEvents = [event(parcel, .outForDelivery)]
        XCTAssertEqual(parcel.peekAiChanges(after: now), [], "None while it says nothing")
        parcel.peekAi = byTime
        XCTAssertEqual(parcel.peekAiChanges(after: now), [at(2026, 11, 24, 16)])
        parcel.trackingEvents = [event(parcel, .inTransit)]
        parcel.peekAi = noDate
        XCTAssertEqual(parcel.peekAiChanges(after: now), [at(2026, 11, 25), at(2026, 11, 26), at(2026, 11, 27)])
        parcel.peekAi = with(noDate) { $0.says = .thisWeek; $0.day = nil }
        XCTAssertEqual(parcel.peekAiChanges(after: now), (25...30).map { at(2026, 11, $0) } + [at(2026, 12, 1)])
    }

    func testAParcelShowsItOnlyOnItsWayToTheDoor() {
        var parcel = Parcel(id: UUID(), trackingNumber: "TEST0000000001", label: "Test parcel", carrier: .unknown,
                            createdAt: "2026-11-20T08:00:00Z", syncStatus: .ok, notificationsMuted: false)
        parcel.peekAi = noDate
        parcel.trackingEvents = [event(parcel, .inTransit)]
        XCTAssertEqual(parcel.shownPeekAi(at: now), noDate)
        parcel.trackingEvents.append(event(parcel, .delivered))
        XCTAssertNil(parcel.shownPeekAi(at: now), "A copy kept from before the scan that delivered it says none")
        parcel.trackingEvents.removeLast()
        parcel.archivedAt = "2026-11-24T10:00:00Z"
        XCTAssertNil(parcel.shownPeekAi(at: now))
    }

    private func event(_ parcel: Parcel, _ stage: TrackingStage) -> TrackingEvent {
        TrackingEvent(id: UUID(), packageID: parcel.id, stage: stage, description: "", occurredAt: DateParser.isoString(now.addingTimeInterval(-3_600)))
    }

    // MARK: Its words

    func testItCommentsOnTheCarriersTodayOnACardAndSaysNotTodayWhereItStandsAlone() {
        XCTAssertEqual(english.peekAiCardWords(notToday, estimate: "2026-11-24", now: now), "unlikely")
        XCTAssertEqual(english.peekAiCardWords(notToday, estimate: nil, now: now), "not today")
        XCTAssertEqual(english.peekAiCardWords(notToday, estimate: "2026-11-25", now: now), "not today")
        XCTAssertEqual(english.peekAiCardWords(byTime, estimate: nil, now: now), "by 16:00")
        XCTAssertEqual(english.peekAiCardWords(noDate, estimate: nil, now: now), "by Thu 26 Nov")
        XCTAssertEqual(english.peekAiCardWords(with(noDate) { $0.says = .thisWeek }, estimate: nil, now: now), "this week")
        XCTAssertEqual(english.peekAiCardWords(sooner, estimate: "2026-11-27", now: now), "maybe sooner")

        let twelve = Localizer(defaults: defaults, systemUses24HourTime: { false })
        let words = twelve.peekAiCardWords(byTime, estimate: nil, now: now)
        XCTAssertNotNil(words.range(of: #"^by 4:00\sPM$"#, options: .regularExpression), words)
    }

    func testItSaysOnThePageWhatItExpectsFromTomorrowOrANamedDay() {
        XCTAssertEqual(english.peekAiClaim(notToday, now: now), "today is unlikely")
        XCTAssertEqual(english.peekAiHeading(notToday, now: now), "Today is unlikely")
        XCTAssertEqual(english.peekAiClaim(byTime, now: now), "likely by 16:00")
        XCTAssertEqual(english.peekAiClaim(sooner, now: now), "maybe sooner, from tomorrow")
        XCTAssertEqual(english.peekAiClaim(with(sooner) { $0.day = "2026-11-26" }, now: now), "maybe sooner, from Thu 26 Nov")
        // Kept past its first day, it names none.
        XCTAssertEqual(english.peekAiClaim(with(sooner) { $0.day = "2026-11-23" }, now: now), "maybe sooner")
        XCTAssertEqual(english.peekAiClaim(with(noDate) { $0.says = .byEndOfNextWeek }, now: now), "likely by the end of next week")
    }

    func testItNamesTheCarriersWordUnderItsHeadingAndSaysWhy() {
        XCTAssertEqual(english.peekAiCarrierWord(notToday, carrier: "DPD", estimate: "Today"), "DPD says today")
        XCTAssertEqual(english.peekAiCarrierWord(sooner, carrier: "DPD", estimate: "Fri 27 Nov"), "DPD says Fri 27 Nov")
        XCTAssertEqual(english.peekAiCarrierWord(noDate, carrier: "UPS", estimate: nil), "UPS gives no date")
        // Still out late: the carrier set out to deliver it today.
        let late = PeekAi(says: .notToday, why: .lateOut, sure: 0.9, outSince: "2026-11-24T08:12:00+01:00")
        XCTAssertEqual(english.peekAiCarrierWord(late, carrier: "DPD", estimate: nil), "DPD says today")
        XCTAssertEqual(english.peekAiWhy(notToday, carrier: "DPD", now: now), "DPD says today, but it hasn’t gone out for delivery.")
        XCTAssertEqual(english.peekAiWhy(byTime, carrier: "FedEx", now: now), "Out for delivery since 08:12. FedEx names no hour.")
        XCTAssertEqual(english.peekAiWhy(noDate, carrier: "UPS", now: now), "UPS gives no date. It reached the delivery depot 2 days ago.")
        func scanned(hoursAgo: Double) -> PeekAi {
            with(noDate) { $0.last = PeekAiStep(kind: .scan, at: DateParser.isoString(now.addingTimeInterval(-hoursAgo * 3_600))) }
        }
        XCTAssertEqual(english.peekAiWhy(scanned(hoursAgo: 5), carrier: "UPS", now: now), "UPS gives no date. Its last scan was 5 hours ago.")
        XCTAssertEqual(english.peekAiWhy(scanned(hoursAgo: 25.0 / 60), carrier: "UPS", now: now), "UPS gives no date. Its last scan was 25 minutes ago.")
        // A day or more: by the calendar, then in weeks.
        XCTAssertEqual(english.peekAiWhy(scanned(hoursAgo: 24), carrier: "UPS", now: now), "UPS gives no date. Its last scan was yesterday.")
        XCTAssertEqual(english.peekAiWhy(scanned(hoursAgo: 24 * 20), carrier: "UPS", now: now), "UPS gives no date. Its last scan was 2 weeks ago.")
    }

    /// The sheet says what it goes by: the parcels Peek has followed, the carrier's usual hour before this one's.
    func testItSaysInItsSheetWhatItGoesBy() {
        XCTAssertEqual(english.peekAiReason(notToday, carrier: "DPD", now: now),
                       "It hasn’t gone out for delivery yet. Most parcels Peek has followed that weren’t out by this hour didn’t arrive that day.")
        XCTAssertEqual(english.peekAiReason(byTime, carrier: "FedEx", now: now),
                       "Most FedEx parcels Peek has followed arrived by 16:00 on the day they went out for delivery. This one went out at 08:12.")
        XCTAssertEqual(english.peekAiReason(PeekAi(says: .notToday, why: .lateOut, sure: 0.88), carrier: "DPD", now: now),
                       "It’s still out for delivery this late. Most parcels Peek has followed that were still out at this hour didn’t arrive that day.")
        XCTAssertEqual(english.peekAiReason(with(noDate) { $0.why = .missedDate; $0.says = .todayOrTomorrow }, carrier: "DPD", now: now),
                       "It was scanned in the last 24 hours. Most parcels Peek has followed that kept moving after missing their date arrived within a day or two.")
        XCTAssertEqual(english.peekAiReason(sooner, carrier: "DPD", now: now),
                       "Most parcels Peek has followed with scans like this one’s came before the date their carrier gave.")
        XCTAssertEqual(english.peekAiReason(noDate, carrier: "UPS", now: now),
                       "Peek AI goes by how long parcels Peek has followed with scans like this one’s took. It reached the delivery depot 2 days ago.")
        english.language = .zh
        let reason = english.peekAiReason(noDate, carrier: "UPS", now: now)
        XCTAssertTrue(reason.hasPrefix(english.text("peekAi.reason.noDate")), reason)
        XCTAssertFalse(reason.contains("。 "), reason)
    }

    func testItCountsHowOftenItWasRightRoundedDown() {
        XCTAssertEqual(english.peekAiRecord(notToday), "Right 8 times in 10 at this hour.")
        XCTAssertEqual(english.peekAiRecord(noDate), "Right 8 times in 10 on parcels like this one.")
        XCTAssertEqual(english.peekAiRecord(with(noDate) { $0.sure = 0.95 }), "Right 9 times in 10 on parcels like this one.")
    }

    func testItWritesTheReadersLanguageWithChineseSentencesJoinedWithoutASpace() {
        english.language = .fr
        let claim = english.peekAiClaim(notToday, now: now)
        XCTAssertEqual(claim, english.text("peekAi.claim.notToday"))
        XCTAssertNotEqual(claim, "today is unlikely")
        english.language = .zh
        let why = english.peekAiWhy(noDate, carrier: "UPS", now: now)
        XCTAssertTrue(why.hasPrefix(english.text("peekAi.why.noDate", ["carrier": "UPS"])), why)
        XCTAssertFalse(why.contains("。 "), why)
    }

    // MARK: The strip in its sheet

    /// Each cell is shaded in four steps against the likeliest still to come, none under 3 in 100 or gone by.
    func testTheStripShadesEachCellInFourStepsAgainstTheLikeliestStillToCome() {
        XCTAssertEqual(peekAiShades([0.02, 0.06, 0.15, 0.25, 0.36, 0.4].map { ($0, false) }), [0, 1, 2, 3, 4, 4])
        // The likeliest hours gone by leave the scale to the ones still to come.
        XCTAssertEqual(peekAiShades([(0.5, true), (0.2, false), (0.1, false)]), [0, 4, 2])
        XCTAssertEqual(peekAiShades([(0.5, true)]), [0])
        XCTAssertEqual(peekAiShades([]), [])
    }

    func testTheStripShowsTheDaysShadedByTheirChanceTheCarriersDayRinged() throws {
        let strip = try XCTUnwrap(english.peekAiStrip(with(noDate) { $0.carrierDay = "2026-11-27" }, now: now))
        XCTAssertEqual(strip.kind, .days)
        XCTAssertEqual(strip.cells.map { "\($0.over) \($0.main)" }, ["Tue 24", "Wed 25", "Thu 26", "Fri 27"])
        XCTAssertEqual(strip.cells.map(\.level), [1, 3, 4, 2])
        XCTAssertEqual(strip.cells.map(\.carrier), [false, false, false, true])
    }

    /// VoiceOver reads the strip as one line, which its shades and ring show: the shaded days or hours still to come,
    /// likeliest first, then the carrier's own.
    func testTheStripIsSpokenLikeliestFirstThenTheCarriers() throws {
        let days = try XCTUnwrap(english.peekAiStrip(with(noDate) { $0.carrierDay = "2026-11-27" }, now: now))
        XCTAssertEqual(english.peekAiStripSpoken(days, carrier: "DPD"), "Days, likeliest first: Thu 26, Wed 25, Fri 27, Tue 24. DPD’s date: Fri 27")
        let hours = try XCTUnwrap(english.peekAiStrip(byTime, now: now))
        XCTAssertEqual(english.peekAiStripSpoken(hours, carrier: "FedEx"), "Hours, likeliest first: 14:00, 16:00")

        english.language = .zh
        let chinese = try XCTUnwrap(english.peekAiStrip(with(noDate) { $0.carrierDay = "2026-11-27" }, now: now))
        let spoken = english.peekAiStripSpoken(chinese, carrier: "DPD")
        XCTAssertTrue(spoken.hasPrefix(english.text("peekAi.sheet.daysByChance") + "："), spoken)
        XCTAssertTrue(spoken.contains("。" + english.text("peekAi.sheet.carrierDate", ["carrier": "DPD"]) + "："), spoken)
        XCTAssertFalse(spoken.contains(": ") || spoken.contains(". "), spoken)
    }

    func testTheStripShowsTheHoursOutForDeliveryTwoToACellThePastOnesFadedAndUnshaded() throws {
        let strip = try XCTUnwrap(english.peekAiStrip(byTime, now: now))
        XCTAssertEqual(strip.kind, .hours)
        XCTAssertEqual(strip.cells.map(\.main), ["08:00", "10:00", "12:00", "14:00", "16:00"])
        XCTAssertEqual(strip.cells.map(\.level), [0, 0, 0, 4, 2])
        XCTAssertEqual(strip.cells.map(\.past), [true, true, true, false, false])
        // In the morning the midday hours are the likeliest; cells as likely as each other keep their order in time.
        let morning = try XCTUnwrap(english.peekAiStrip(byTime, now: at(2026, 11, 24, 9, 30)))
        XCTAssertEqual(morning.cells.map(\.level), [1, 3, 4, 2, 1])
        XCTAssertEqual(english.peekAiStripSpoken(morning, carrier: "DPD"), "Hours, likeliest first: 12:00, 10:00, 14:00, 08:00, 16:00")
        XCTAssertNil(english.peekAiStrip(notToday, now: now))
    }

    // MARK: Cards and the widgets

    /// A card shows the words only where Settings shows Peek AI, and never beside the day a parcel's journey ended.
    func testACardShowsTheWordsWhereSettingsShowsThem() {
        var parcel = Parcel(id: UUID(), trackingNumber: "TEST0000000002", label: "Test parcel", carrier: .unknown,
                            createdAt: "2026-11-20T08:00:00Z", syncStatus: .ok, notificationsMuted: false)
        parcel.peekAi = noDate
        parcel.trackingEvents = [event(parcel, .inTransit)]
        XCTAssertEqual(english.parcelPeekAiWords(parcel, shown: true, now: now), "by Thu 26 Nov")
        XCTAssertNil(english.parcelPeekAiWords(parcel, shown: false, now: now))
        XCTAssertNil(english.parcelPeekAiWords(parcel, shown: true, now: at(2026, 11, 27, 9)))
    }

    /// "Unlikely" comments on the carrier's today the card shows; where the card shows none, it says "not today".
    func testACardSaysUnlikelyOnlyBesideTheCarriersToday() {
        var parcel = Parcel(id: UUID(), trackingNumber: "TEST0000000007", label: "Test parcel", carrier: .unknown,
                            createdAt: "2026-11-20T08:00:00Z", syncStatus: .ok, notificationsMuted: false)
        parcel.peekAi = notToday
        parcel.trackingEvents = [event(parcel, .inTransit)]
        parcel.expectedDelivery = "2026-11-24"
        XCTAssertEqual(english.parcelPeekAiWords(parcel, shown: true, now: now), "unlikely")
        // Out for delivery, a card shows no day the carrier gave, only its hours for today.
        parcel.peekAi = PeekAi(says: .notToday, why: .lateOut, sure: 0.9, outSince: "2026-11-24T08:12:00+01:00")
        parcel.trackingEvents = [event(parcel, .outForDelivery)]
        XCTAssertNil(english.shownEstimate(parcel, now: now))
        XCTAssertEqual(english.parcelPeekAiWords(parcel, shown: true, now: now), "not today")
        parcel.expectedDelivery = nil
        XCTAssertEqual(english.parcelPeekAiWords(parcel, shown: true, now: now), "not today")
    }

    /// The Parcels widget shows the card's words while they hold, with an entry in its timeline where they stop.
    func testTheWidgetShowsTheWordsUntilTheyStopHolding() throws {
        var parcel = Parcel(id: UUID(), trackingNumber: "TEST0000000003", label: "Test parcel", carrier: .unknown,
                            createdAt: "2026-11-20T08:00:00Z", syncStatus: .ok, notificationsMuted: false)
        parcel.peekAi = noDate
        parcel.trackingEvents = [event(parcel, .inTransit)]
        let hidden = ParcelStore.widgetDeliveries(of: [parcel], localizer: english, now: now)
        XCTAssertNil(hidden.first?.peekAi, "Off unless Settings shows it")
        let shown = try XCTUnwrap(ParcelStore.widgetDeliveries(of: [parcel], localizer: english, peekAi: true, now: now).first)
        XCTAssertEqual(shown.peekAi, "by Thu 26 Nov")
        XCTAssertEqual(shown.peekAiUntil, at(2026, 11, 27))
        XCTAssertTrue(DeliveryWidgetDays.changes(in: [shown], after: now).contains(at(2026, 11, 27)))
        XCTAssertEqual(shown.worded(at: at(2026, 11, 26, 23, 59)).peekAi, "by Thu 26 Nov")
        XCTAssertNil(shown.worded(at: at(2026, 11, 27)).peekAi)
    }

    /// The wide widget card gives no estimate of the carrier's at its top, so Peek AI says "not today" there, not
    /// "unlikely".
    func testTheWideWidgetCardHasTheWordsThatStandAlone() throws {
        var parcel = Parcel(id: UUID(), trackingNumber: "TEST0000000008", label: "Test parcel", carrier: .unknown,
                            createdAt: "2026-11-20T08:00:00Z", syncStatus: .ok, notificationsMuted: false)
        parcel.peekAi = notToday
        parcel.trackingEvents = [event(parcel, .inTransit)]
        parcel.expectedDelivery = "2026-11-24"
        let item = try XCTUnwrap(ParcelStore.widgetDeliveries(of: [parcel], localizer: english, peekAi: true, now: now).first)
        XCTAssertEqual(item.peekAi, "unlikely")
        XCTAssertEqual(item.peekAiAlone, "not today")
        XCTAssertNil(item.worded(at: at(2026, 11, 25)).peekAiAlone)
        parcel.expectedDelivery = nil
        let alone = try XCTUnwrap(ParcelStore.widgetDeliveries(of: [parcel], localizer: english, peekAi: true, now: now).first)
        XCTAssertEqual(alone.peekAi, "not today")
        XCTAssertNil(alone.peekAiAlone, "The same words twice are kept once")
    }

    /// The widget gallery's sample leaves Peek AI out where Settings has it off, which the app keeps in the App Group.
    func testTheAppGroupKeepsWhetherPeekAiIsShown() throws {
        let suite = "peek-ai-group-\(UUID().uuidString)"
        let group = DeliveryWidgetSharedStore(defaults: try XCTUnwrap(UserDefaults(suiteName: suite)))
        defer { UserDefaults(suiteName: suite)?.removePersistentDomain(forName: suite) }
        XCTAssertTrue(group.showsPeekAi, "Shown until switched off")
        group.setShowsPeekAi(false)
        XCTAssertFalse(group.showsPeekAi)
        group.setShowsPeekAi(true)
        XCTAssertTrue(group.showsPeekAi)
    }

    /// The widget gallery's first sample is out for delivery, and Peek AI names an hour ahead of the gallery's.
    func testTheWidgetGallerysSampleNamesAnHourAhead() {
        let english = ExtensionLocalizer(languageCode: "en")
        XCTAssertEqual(DeliveryWidgetSample.peekAi(at: at(2026, 11, 24, 9, 10), copy: english, uses24HourTime: true), "by 16:00")
        XCTAssertEqual(DeliveryWidgetSample.peekAi(at: at(2026, 11, 24, 17, 50), copy: english, uses24HourTime: true), "by 19:00")
        XCTAssertEqual(DeliveryWidgetSample.peekAi(at: at(2026, 11, 24, 9, 10), copy: english, uses24HourTime: false), "by 4:00 PM")
        XCTAssertEqual(DeliveryWidgetSample.peekAi(at: at(2026, 11, 24, 9, 10), copy: ExtensionLocalizer(languageCode: "fr"), uses24HourTime: true),
                       "avant 16:00")
        XCTAssertNil(DeliveryWidgetSample.peekAi(at: at(2026, 11, 24, 20), copy: english, uses24HourTime: true), "No courier is expected so late")
    }

    // MARK: Reading the server's answer

    /// An estimate the app cannot read, a newer server's word, leaves Peek AI out and keeps the parcel and its list.
    func testAnEstimateItCannotReadLeavesOutOnlyPeekAi() throws {
        func package(_ peekAi: String) -> String {
            """
            {"id":"5f0c7a52-3c1e-4b7a-9d55-000000000009","tracking_number":"TEST0000000009","label":"Test parcel","carrier":"dpd",\
            "created_at":"2026-11-20T08:00:00Z","sync_status":"ok","notifications_muted":false,"carrier_data":{},\
            "tracking_events":[],"peek_ai":\(peekAi)}
            """
        }
        let read = #"{"says":"by_day","why":"no_date","sure":0.81,"day":"2026-11-26","ends_at":"2026-11-27T00:00:00+01:00"}"#
        let unread = [
            #"{"says":"next_month","why":"no_date","sure":0.9}"#,
            #"{"says":"by_day","why":"a_hunch","sure":0.9,"day":"2026-11-26"}"#,
            #"{"says":"by_day","why":"no_date","sure":0.9,"day":"2026-11-26","last":{"kind":"drone","at":"2026-11-23T10:00:00Z"}}"#,
        ]
        let decoder = JSONDecoder.deliveryTracker
        let kept = try decoder.decode(Parcel.self, from: Data(package(read).utf8))
        XCTAssertEqual(kept.peekAi?.says, .byDay)
        XCTAssertEqual(kept.peekAi?.endsAt, "2026-11-27T00:00:00+01:00")
        for peekAi in unread {
            let parcel = try decoder.decode(Parcel.self, from: Data(package(peekAi).utf8))
            XCTAssertEqual(parcel.trackingNumber, "TEST0000000009")
            XCTAssertNil(parcel.peekAi, peekAi)
            let list = try decoder.decode(PackageListResponse.self, from: Data(#"{"packages":[\#(package(peekAi)),\#(package(read))]}"#.utf8))
            XCTAssertEqual(list.packages.map { $0.peekAi?.says }, [nil, .byDay], peekAi)
            // A parcel's link page, read by a guest, too.
            let shared = try decoder.decode(PublicPackage.self, from: Data(package(peekAi).utf8))
            XCTAssertEqual(shared.label, "Test parcel")
            XCTAssertNil(shared.peekAi, peekAi)
        }
        XCTAssertEqual(try decoder.decode(PublicPackage.self, from: Data(package(read).utf8)).peekAi?.day, "2026-11-26")
    }

    // MARK: The sheet

    /// The sheet shows the parcel's estimate as it is now: it follows a new one, and goes as soon as the parcel is
    /// delivered, or once the estimate ends with the sheet left open.
    func testTheSheetGoesWithTheEstimate() async throws {
        let localizer = Localizer(defaults: defaults, systemUses24HourTime: { true })
        let demo = AppConfiguration(mode: .demo, apiBaseURL: URL(string: "https://render.test")!, supabaseURL: nil, supabasePublishableKey: "",
                                    googleAuthEnabled: false, appleAuthEnabled: false, emailOTPEnabled: false, appGroupIdentifier: "render.test")
        let session = SessionStore(configuration: demo, defaults: defaults, persistence: PeekAiTestSessionPersistence())
        let store = ParcelStore(configuration: demo, session: session, localizer: localizer,
                                device: DeviceParcels(client: nil, storage: PeekAiTestStorage()))
        var parcel = Parcel(id: UUID(), trackingNumber: "TEST0000000010", label: "Test parcel", carrier: .unknown,
                            createdAt: "2026-11-20T08:00:00Z", syncStatus: .ok, notificationsMuted: false)
        parcel.trackingEvents = [event(parcel, .inTransit)]
        parcel.peekAi = PeekAi(says: .thisWeek, why: .noDate, sure: 0.84)
        let page = PeekAiTestPage(parcel: parcel)
        let host = UIHostingController(rootView: PeekAiTestPageView(page: page).environmentObject(store).environmentObject(localizer))
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.compactMap { $0 as? UIWindowScene }.first)
        let window = UIWindow(windowScene: scene)
        window.rootViewController = host
        window.makeKeyAndVisible()
        defer { window.isHidden = true }
        func until(_ condition: () -> Bool, _ message: String) async throws {
            for _ in 0..<100 where !condition() { try await Task.sleep(for: .milliseconds(50)) }
            XCTAssertTrue(condition(), message)
        }

        page.request = PeekAiSheetRequest(id: parcel.id)
        try await until({ host.presentedViewController != nil }, "The sheet opens")
        page.parcel?.peekAi = PeekAi(says: .byEndOfNextWeek, why: .noDate, sure: 0.84)
        try await Task.sleep(for: .milliseconds(500))
        XCTAssertNotNil(host.presentedViewController, "A new estimate keeps it open")
        page.parcel?.trackingEvents.append(event(parcel, .delivered))
        try await until({ host.presentedViewController == nil }, "Delivered, the sheet goes")
        XCTAssertNil(page.request)

        // Left open, it goes at the moment the estimate ends.
        page.parcel = parcel
        page.parcel?.peekAi = PeekAi(says: .byDay, why: .noDate, sure: 0.84, day: PeekAi.day(of: Date()),
                                     endsAt: DateParser.isoString(Date().addingTimeInterval(2)))
        page.request = PeekAiSheetRequest(id: parcel.id)
        try await until({ host.presentedViewController != nil }, "The sheet opens again")
        try await until({ host.presentedViewController == nil }, "The estimate ended, the sheet goes")
    }

    // MARK: The demo

    /// The demo works it out as the web's does: the chocolate out for delivery today, by 16:00 until then; the gift in
    /// customs with no date, by the third delivery day from today, Sundays skipped.
    func testTheDemoSaysWhatPeekAiWouldOfItsTwoSamples() throws {
        let suite = "peek-ai-demo-\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        defer { defaults.removePersistentDomain(forName: suite) }
        let parcels = DemoRepository(defaults: defaults, language: { .en }).list()

        var chocolate = try XCTUnwrap(parcels.first { $0.trackingNumber == "DEMOCHOC20260001" })
        XCTAssertEqual(chocolate.currentStage, .outForDelivery)
        let morning = at(2026, 11, 24, 9, 40)
        chocolate.trackingEvents[chocolate.trackingEvents.count - 1].occurredAt = DateParser.isoString(morning)
        let out = try XCTUnwrap(DemoRepository.peekAi(of: chocolate, now: at(2026, 11, 24, 11)))
        XCTAssertEqual(out.says, .byTime)
        XCTAssertEqual(out.time, "16:00")
        XCTAssertEqual(english.peekAiWhy(out, carrier: "FedEx", now: at(2026, 11, 24, 11)), "Out for delivery since 09:40. FedEx names no hour.")
        XCTAssertNotNil(out.current(at: at(2026, 11, 24, 11)))
        XCTAssertNil(DemoRepository.peekAi(of: chocolate, now: at(2026, 11, 24, 16, 5)), "Past the hour it named")

        let gift = try XCTUnwrap(parcels.first { $0.trackingNumber == "LX123456789DE" })
        XCTAssertEqual(gift.currentStage, .customs)
        // Saturday 21 November: the next delivery days skip the Sunday.
        let saturday = at(2026, 11, 21, 10)
        let held = try XCTUnwrap(DemoRepository.peekAi(of: gift, now: saturday))
        XCTAssertEqual(held.says, .byDay)
        XCTAssertEqual(held.why, .noDate)
        XCTAssertEqual(held.days?.map(\.day), ["2026-11-21", "2026-11-23", "2026-11-24", "2026-11-25", "2026-11-26"])
        XCTAssertEqual(held.day, "2026-11-24")
        XCTAssertEqual(held.last?.kind, .customs)
        XCTAssertEqual(english.peekAiCardWords(held, estimate: nil, now: saturday), "by Tue 24 Nov")

        // The other samples say nothing.
        let others = parcels.filter { !["DEMOCHOC20260001", "LX123456789DE"].contains($0.trackingNumber) }
        XCTAssertFalse(others.isEmpty)
        XCTAssertTrue(others.allSatisfy { DemoRepository.peekAi(of: $0, now: saturday) == nil && DemoRepository.peekAi(of: $0, now: Date()) == nil })
    }
}

/// A page that holds Peek AI's sheet as the parcel's page does: it looks the parcel up each time it draws.
@MainActor
private final class PeekAiTestPage: ObservableObject {
    @Published var parcel: Parcel?
    @Published var request: PeekAiSheetRequest?

    init(parcel: Parcel) { self.parcel = parcel }
}

private struct PeekAiTestPageView: View {
    @ObservedObject var page: PeekAiTestPage
    @EnvironmentObject private var store: ParcelStore
    @EnvironmentObject private var localizer: Localizer

    var body: some View {
        Color.clear.sheet(item: $page.request) { _ in
            PeekAiSheet(parcel: page.parcel).environmentObject(store).environmentObject(localizer)
        }
    }
}

private final class PeekAiTestStorage: DeviceParcelStorage {
    func load() -> (parcels: [DeviceParcel], keys: [String: String]) { ([], [:]) }
    func save(_ parcels: [DeviceParcel], keys: [String: String]) {}
}

private final class PeekAiTestSessionPersistence: SessionPersistence {
    var data: Data?
    func save<T: Encodable>(_ value: T) throws { data = try JSONEncoder.deliveryTracker.encode(value) }
    func load<T: Decodable>() -> T? { data.flatMap { try? JSONDecoder.deliveryTracker.decode(T.self, from: $0) } }
    func delete() { data = nil }
}
