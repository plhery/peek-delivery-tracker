import Combine
import SwiftUI
import XCTest
import UIKit
@testable import PeekDeliveryTracker

final class ParcelLogicTests: XCTestCase {
    func testAddPhoneDigitsBelongToOneParcelAndCarrier() {
        var input = ParcelPhoneInput()
        input["TEST-A", .jAndTMx] = "0123"
        XCTAssertEqual(input["TEST-A", .jAndTMx], "0123")
        XCTAssertEqual(input["TEST-B", .jAndTMx], "")
        XCTAssertEqual(input["TEST-A", .jAndTCn], "")
    }

    func testPickupPointSplitsNameFromAddressAndOpensAppleMaps() {
        let shop = PickupPoint(" Corner shop & café \n12 Main Street\n\n1000 Town ")
        XCTAssertEqual(shop?.name, "Corner shop & café")
        XCTAssertEqual(shop?.address, "12 Main Street, 1000 Town")
        XCTAssertEqual(shop?.query, "Corner shop & café, 12 Main Street, 1000 Town")
        XCTAssertEqual(shop?.mapsURL?.absoluteString,
                       "https://maps.apple.com/?daddr=Corner%20shop%20%26%20caf%C3%A9,%2012%20Main%20Street,%201000%20Town")
        let office = PickupPoint("Post office 42")
        XCTAssertNil(office?.address)
        XCTAssertEqual(office?.mapsURL?.absoluteString, "https://maps.apple.com/?q=Post%20office%2042")
        XCTAssertNil(PickupPoint(nil))
        XCTAssertNil(PickupPoint(" \n "))
    }

    func testAutomaticCarrierNoticeExpiresAndDoesNotSurviveManualSelection() {
        let at = DateParser.date("2026-09-10T12:00:00Z")!
        var parcel = DemoRepository.seed(now: at)[0]
        parcel.carrier = .ups
        parcel.carrierData = CarrierData(autoChangedFrom: .dhl, autoChangedTo: .ups, autoChangedAt: DateParser.isoString(at))
        XCTAssertEqual(parcel.automaticallyChangedFrom(at: at), .dhl)
        XCTAssertEqual(parcel.automaticallyChangedFrom(at: at.addingTimeInterval(43_199)), .dhl)
        XCTAssertNil(parcel.automaticallyChangedFrom(at: at.addingTimeInterval(43_200)))
        XCTAssertNil(parcel.automaticallyChangedFrom(at: at.addingTimeInterval(-1)))
        parcel.carrier = .fedex
        XCTAssertNil(parcel.automaticallyChangedFrom(at: at))
    }

    func testAutomaticCarrierNoticeIgnoresParcelsFiledWithoutCarrier() {
        let at = DateParser.date("2026-09-10T12:00:00Z")!
        var parcel = DemoRepository.seed(now: at)[0]
        parcel.carrier = .ups
        for from in [CarrierID.unknown, .internationalPost] {
            parcel.carrierData = CarrierData(autoChangedFrom: from, autoChangedTo: .ups, autoChangedAt: DateParser.isoString(at))
            XCTAssertNil(parcel.automaticallyChangedFrom(at: at))
        }
    }

    func testSharedDemoCatalogHasVariedHistoriesAndRelativeDates() {
        let now = DateParser.date("2026-09-09T12:00:00Z")!
        let parcels = DemoRepository.seed(now: now)
        XCTAssertEqual(parcels.count, 17)
        XCTAssertEqual(Set(parcels.map(\.trackingNumber)).count, 17)
        XCTAssertEqual(parcels.filter(\.isActive).count, 7)
        XCTAssertEqual(parcels.filter(\.isDelivered).count, 10)
        XCTAssertEqual(parcels.filter(\.isArchived).count, 5)
        XCTAssertEqual(ParcelOrganizer.nextDelivery(from: parcels, now: now)?.label, "New trainers 👟")
        XCTAssertTrue(parcels.contains { $0.expectedDelivery != nil })
        XCTAssertTrue(parcels.contains { $0.currentStage == .inTransit && $0.expectedDelivery == nil })
        for parcel in parcels {
            XCTAssertTrue(CarrierID.allCases.contains(parcel.carrier))
            let dates = parcel.trackingEvents.compactMap { DateParser.date($0.occurredAt) }
            XCTAssertEqual(dates.count, parcel.trackingEvents.count)
            XCTAssertEqual(dates, dates.sorted())
            XCTAssertTrue(dates.allSatisfy { $0 <= now })
            XCTAssertTrue(parcel.trackingEvents.allSatisfy { $0.packageID == parcel.id })
        }
    }

    func testDemoUpgradePreservesEditsAndOnlyAddsExamplesOnce() throws {
        let suite = "demo-catalog-tests-\(UUID().uuidString)"
        let defaults = UserDefaults(suiteName: suite)!
        defer { defaults.removePersistentDomain(forName: suite) }
        var coffee = try XCTUnwrap(DemoRepository.seed().first { $0.trackingNumber == "993412345678901234" })
        coffee.label = "My edited coffee"
        coffee.archivedAt = DateParser.isoString(Date())
        defaults.set(try JSONEncoder.deliveryTracker.encode([coffee]), forKey: "sdt.native.demo.parcels.v1")
        let repo = DemoRepository(defaults: defaults, language: { .en })
        let upgraded = repo.list()
        XCTAssertEqual(upgraded.count, 17)
        XCTAssertEqual(upgraded.first { $0.id == coffee.id }?.label, "My edited coffee")
        XCTAssertTrue(try XCTUnwrap(upgraded.first { $0.id == coffee.id }).isArchived)
        let lamp = try XCTUnwrap(upgraded.first { $0.label == "Moon lamp 🌙" })
        try repo.permanentlyDelete(id: lamp.id)
        XCTAssertEqual(DemoRepository(defaults: defaults).list().count, 16)
        defaults.set(try JSONEncoder.deliveryTracker.encode([Parcel]()), forKey: "sdt.native.demo.parcels.v1")
        defaults.removeObject(forKey: "sdt.native.demo.catalog.v2")
        XCTAssertTrue(DemoRepository(defaults: defaults).list().isEmpty)
    }

    func testAParcelIsNewWhileItsLatestScanArrivedAfterItWasLastOpened() {
        var parcel = Parcel(id: UUID(), trackingNumber: "12345678", label: "", carrier: .unknown, createdAt: "2026-11-01T08:00:00Z",
                            syncStatus: .ok, notificationsMuted: false)
        XCTAssertFalse(parcel.hasNewUpdate, "A parcel without a scan has no news")
        parcel.newsAt = "2026-11-15T10:00:00.123456+00:00"
        XCTAssertFalse(parcel.hasNewUpdate, "Nothing is marked before the server says when the parcel was seen")
        parcel.seenAt = "2026-11-15T09:59:59.9+00:00"
        XCTAssertTrue(parcel.hasNewUpdate)
        // The server writes its moments in more than one way: they are compared as moments, not as text.
        parcel.seenAt = "2026-11-15T11:59:59+02:00"
        XCTAssertTrue(parcel.hasNewUpdate)
        parcel.seenAt = "2026-11-15T10:00:00.02933+00:00"
        XCTAssertTrue(parcel.hasNewUpdate)
        // Opened since: seen with its news, or later.
        parcel.seenAt = "2026-11-15T10:00:00.123456+00:00"
        XCTAssertFalse(parcel.hasNewUpdate)
        parcel.seenAt = "2026-11-15T10:00:01Z"
        XCTAssertFalse(parcel.hasNewUpdate)

        // Settings can switch the mark off; the parcel is as new as it was.
        parcel.seenAt = "2026-11-15T09:00:00+00:00"
        XCTAssertTrue(parcel.showsNewUpdate(marking: true))
        XCTAssertFalse(parcel.showsNewUpdate(marking: false))
        XCTAssertTrue(parcel.hasNewUpdate)
    }

    func testTheDemoMarksAMadeUpScanUntilItsParcelIsOpened() throws {
        let suite = "demo-new-update-tests-\(UUID().uuidString)"
        let defaults = UserDefaults(suiteName: suite)!
        defer { defaults.removePersistentDomain(forName: suite) }
        let repo = DemoRepository(defaults: defaults, language: { .en })
        XCTAssertFalse(repo.list().contains(where: \.hasNewUpdate), "The examples start as seen")
        let moving = try XCTUnwrap(repo.list().first { $0.isActive && $0.currentStage == .inTransit })
        let scanned = try repo.refresh(id: moving.id)
        XCTAssertGreaterThan(scanned.trackingEvents.count, moving.trackingEvents.count)
        XCTAssertTrue(scanned.hasNewUpdate)
        XCTAssertEqual(repo.list().filter(\.hasNewUpdate).map(\.id), [moving.id])
        XCTAssertFalse(try repo.markSeen(id: moving.id).hasNewUpdate)
        XCTAssertFalse(DemoRepository(defaults: defaults, language: { .en }).list().contains(where: \.hasNewUpdate))
    }

    func testDemoCarrierChangeKeepsTheHistory() throws {
        let suite = "demo-carrier-tests-\(UUID().uuidString)"
        let defaults = UserDefaults(suiteName: suite)!
        defer { defaults.removePersistentDomain(forName: suite) }
        let repo = DemoRepository(defaults: defaults, language: { .en })
        let sneakers = try XCTUnwrap(repo.list().first { $0.trackingNumber == "1234567899" })
        let changed = try repo.changeCarrier(id: sneakers.id, carrier: .ups, trackingURL: nil, dpdPostcode: nil, dpdPostcodeCountry: nil)
        XCTAssertEqual(changed.carrier, .ups)
        XCTAssertEqual(changed.syncStatus, .pending)
        XCTAssertEqual(changed.trackingEvents, sneakers.trackingEvents)
    }

    func testDemoIsWrittenInTheAppLanguageAndKeepsEditedNames() throws {
        let suite = "demo-language-tests-\(UUID().uuidString)"
        let defaults = UserDefaults(suiteName: suite)!
        defer { defaults.removePersistentDomain(forName: suite) }
        var language = AppLanguage.en
        let repo = DemoRepository(defaults: defaults, language: { language })
        let coffee = try XCTUnwrap(repo.list().first { $0.label == "Coffee beans ☕" })
        _ = try repo.rename(id: coffee.id, label: "My own coffee")

        language = .fr
        let french = repo.list()
        XCTAssertEqual(french.first { $0.id == coffee.id }?.label, "My own coffee")
        let sneakers = try XCTUnwrap(french.first { $0.trackingNumber == "1234567899" })
        XCTAssertEqual(sneakers.label, "Nouvelles baskets 👟")
        XCTAssertEqual(sneakers.pickupPlace, "Kiosk im Hauptbahnhof")
        XCTAssertTrue(sneakers.trackingEvents.contains { $0.description == "Personne à la maison. Sans doute parti courir." })
        XCTAssertEqual(sneakers.lastStatusText, "T’attend au point de retrait")
        // A refresh adds its scan in the same language.
        let collected = try repo.refresh(id: sneakers.id)
        XCTAssertEqual(collected.currentEvent?.description, "Livré dans ta boîte aux lettres")
        XCTAssertEqual(collected.currentEvent?.location, "Domicile")
        XCTAssertNil(collected.pickupPlace)

        language = .de
        let german = repo.list()
        XCTAssertEqual(german.first { $0.id == sneakers.id }?.label, "Neue Sneaker 👟")
        XCTAssertEqual(german.first { $0.id == sneakers.id }?.currentEvent?.description, "In deinen Briefkasten zugestellt")
        XCTAssertEqual(german.first { $0.id == coffee.id }?.label, "My own coffee")
        // Another launch, and a reset, keep the chosen language.
        let later = DemoRepository(defaults: defaults, language: { .de })
        XCTAssertEqual(later.list().first { $0.id == sneakers.id }?.label, "Neue Sneaker 👟")
        later.reset()
        XCTAssertTrue(later.list().contains { $0.label == "Kaffeebohnen ☕" })

        language = .en
        XCTAssertEqual(Set(repo.list().map(\.label)), Set(DemoRepository.seed().map(\.label)))
    }

    func testArrivalTiltFiltersJitterAndBoundsLargeMovements() {
        var tilt = ArrivalTilt()
        tilt.follow(roll: 0.44, pitch: -0.44)
        XCTAssertEqual(tilt.x, 0.22, accuracy: 0.0001)
        XCTAssertEqual(tilt.y, -0.22, accuracy: 0.0001)
        for _ in 0..<100 { tilt.follow(roll: 50, pitch: -50) }
        XCTAssertLessThanOrEqual(tilt.x, 1)
        XCTAssertGreaterThanOrEqual(tilt.y, -1)
        let prior = tilt
        tilt.follow(roll: .nan, pitch: .infinity)
        XCTAssertEqual(tilt, prior)
    }

    func testArrivalTiltFollowsScreenOrientationAndSettlesAtRest() {
        var portrait = ArrivalTilt()
        var landscape = ArrivalTilt()
        portrait.follow(roll: 0.44, pitch: 0)
        landscape.follow(roll: 0.44, pitch: 0, quarterTurns: 1)
        XCTAssertEqual(landscape.x, 0, accuracy: 0.0001)
        XCTAssertEqual(landscape.y, portrait.x, accuracy: 0.0001)
        for _ in 0..<40 { landscape.follow(roll: 0, pitch: 0) }
        XCTAssertEqual(landscape.y, 0, accuracy: 0.0001)
    }

    func testArrivalTiltRespondsGentlyToAccelerationAndRejectsInvalidSamples() {
        var tilt = ArrivalTilt()
        tilt.follow(roll: 0, pitch: 0, accelerationX: 0.35, accelerationY: 0.35)
        XCTAssertGreaterThan(tilt.x, 0)
        XCTAssertLessThan(tilt.y, 0)
        XCTAssertLessThan(abs(tilt.x) + abs(tilt.y), 0.1)
        let previous = tilt
        tilt.follow(roll: 0, pitch: 0, accelerationX: .nan)
        XCTAssertEqual(tilt, previous)
        for _ in 0..<60 { tilt.follow(roll: 0, pitch: 0) }
        XCTAssertEqual(tilt.x, 0, accuracy: 0.0001)
        XCTAssertEqual(tilt.y, 0, accuracy: 0.0001)
    }

    @MainActor
    func testArchiveRecognizerDeclinesVerticalDragsBeforeTheyCanBlockScrolling() {
        let delegate = ArchivePanGestureDelegate()
        let recognizer = StubArchivePanGestureRecognizer()
        for translation in [CGPoint(x: -3, y: -18), CGPoint(x: 4, y: 24)] {
            recognizer.testTranslation = translation
            XCTAssertFalse(delegate.gestureRecognizerShouldBegin(recognizer))
        }
    }

    @MainActor
    func testArchiveRecognizerLeavesAmbiguousDiagonalDragsToScrolling() {
        let delegate = ArchivePanGestureDelegate()
        let recognizer = StubArchivePanGestureRecognizer()
        for translation in [CGPoint(x: -20, y: -20), CGPoint(x: -20, y: 18), .zero] {
            recognizer.testTranslation = translation
            XCTAssertFalse(delegate.gestureRecognizerShouldBegin(recognizer))
        }
    }

    @MainActor
    func testArchiveRecognizerAllowsHorizontalRevealAndReverseDrags() {
        let delegate = ArchivePanGestureDelegate()
        let recognizer = StubArchivePanGestureRecognizer()
        for translation in [CGPoint(x: -20, y: 3), CGPoint(x: 20, y: -3)] {
            recognizer.testTranslation = translation
            XCTAssertTrue(delegate.gestureRecognizerShouldBegin(recognizer))
        }
    }

    @MainActor
    func testArchiveRecognizerLetsTheListScrollUntilTheDragShowsItsDirection() {
        let delegate = ArchivePanGestureDelegate()
        let recognizer = ArchivePanGestureRecognizer()
        let list = UIScrollView()
        XCTAssertTrue(delegate.gestureRecognizer(recognizer, shouldRecognizeSimultaneouslyWith: list.panGestureRecognizer))
        XCTAssertTrue(recognizer.scroll === list.panGestureRecognizer)
        XCTAssertFalse(delegate.gestureRecognizer(recognizer, shouldRecognizeSimultaneouslyWith: UIPanGestureRecognizer()))
        XCTAssertFalse(delegate.gestureRecognizer(recognizer, shouldRecognizeSimultaneouslyWith: UITapGestureRecognizer()))
    }

    func testArchiveReleaseKeepsTheFingerPositionUntilTheSettleAnimation() {
        var swipe = ArchiveSwipeState()
        // The first 8 points only pick the direction, so the card starts without a jump.
        swipe.drag(translation: CGSize(width: -60, height: 3), width: 360)
        XCTAssertEqual(swipe.reveal, 52, accuracy: 0.001)

        XCTAssertEqual(swipe.release(velocity: -300, width: 360), .revealed)
        // Releasing used to reset the displayed translation to zero for a frame.
        XCTAssertEqual(swipe.reveal, 52, accuracy: 0.001)
        XCTAssertNil(swipe.cancel())
        swipe.settle(at: 88)

        // Past the action the card resists the finger.
        swipe.drag(translation: CGSize(width: -12, height: 0), width: 360)
        XCTAssertEqual(swipe.reveal, 91.2, accuracy: 0.001)
    }

    func testArchiveSwipeThatStartsAtZeroFollowsTheFingerOneToOne() {
        // iOS 27 reports a recognized pan at zero before it moves.
        var swipe = ArchiveSwipeState()
        swipe.drag(translation: .zero, width: 360)
        swipe.drag(translation: CGSize(width: -15.5, height: -2), width: 360)
        XCTAssertEqual(swipe.reveal, 15.5, accuracy: 0.001)
        swipe.drag(translation: CGSize(width: -48, height: -5), width: 360)
        XCTAssertEqual(swipe.reveal, 48, accuracy: 0.001)
        XCTAssertEqual(swipe.release(velocity: -600, width: 360), .revealed)

        swipe.settle(at: 0)
        swipe.drag(translation: .zero, width: 360)
        swipe.drag(translation: CGSize(width: -3, height: -18), width: 360)
        swipe.drag(translation: CGSize(width: -120, height: -20), width: 360)
        XCTAssertEqual(swipe.reveal, 0)
        XCTAssertNil(swipe.release(velocity: -900, width: 360))
    }

    func testShortArchiveSwipeClosesFromTheReleasePosition() {
        var swipe = ArchiveSwipeState()
        swipe.drag(translation: CGSize(width: -25, height: 0), width: 360)
        XCTAssertEqual(swipe.release(velocity: 0, width: 360), .closed)
        XCTAssertEqual(swipe.reveal, 17, accuracy: 0.001)
    }

    func testReversingAnOpenArchiveSwipeClosesIt() {
        var swipe = ArchiveSwipeState()
        swipe.settle(at: 88)
        swipe.drag(translation: CGSize(width: 65, height: 2), width: 360)
        XCTAssertEqual(swipe.reveal, 31, accuracy: 0.001)
        XCTAssertEqual(swipe.release(velocity: 400, width: 360), .closed)
        XCTAssertEqual(swipe.reveal, 31, accuracy: 0.001)
    }

    func testArchiveNeedsTheCommitPointOrAThrowPastTheAction() {
        var swipe = ArchiveSwipeState()
        swipe.drag(translation: CGSize(width: -30, height: 0), width: 360)
        XCTAssertEqual(swipe.release(velocity: -2000, width: 360), .revealed)

        swipe.settle(at: 0)
        swipe.drag(translation: CGSize(width: -120, height: 1), width: 360)
        XCTAssertEqual(swipe.release(velocity: -500, width: 360), .revealed)
        swipe.settle(at: 0)
        swipe.drag(translation: CGSize(width: -120, height: 1), width: 360)
        XCTAssertEqual(swipe.release(velocity: -1500, width: 360), .archive)

        swipe.settle(at: 0)
        swipe.drag(translation: CGSize(width: -230, height: 1), width: 360)
        XCTAssertGreaterThanOrEqual(swipe.reveal, ArchiveSwipeState.commitPoint(width: 360))
        XCTAssertEqual(swipe.release(velocity: 0, width: 360), .archive)
    }

    func testVerticalScrollDoesNotBecomeAnArchiveSwipe() {
        var swipe = ArchiveSwipeState()
        swipe.drag(translation: CGSize(width: -3, height: -18), width: 360)
        swipe.drag(translation: CGSize(width: -200, height: -25), width: 360)
        XCTAssertEqual(swipe.reveal, 0)
        XCTAssertNil(swipe.release(velocity: -900, width: 360))
    }

    func testCancelledSwipeSettlesWithoutArchivingOrResettingItsPosition() {
        var swipe = ArchiveSwipeState()
        swipe.drag(translation: CGSize(width: -220, height: 0), width: 360)
        let reveal = swipe.reveal
        XCTAssertEqual(swipe.cancel(), .revealed)
        XCTAssertEqual(swipe.reveal, reveal)
        XCTAssertNil(swipe.cancel())
    }

    func testArchiveSwipeStaysWithinCardBounds() {
        var swipe = ArchiveSwipeState()
        swipe.drag(translation: CGSize(width: 50, height: 0), width: 360)
        XCTAssertLessThan(swipe.reveal, 0)
        XCTAssertGreaterThan(swipe.reveal, -44)
        swipe.drag(translation: CGSize(width: -500, height: 0), width: 360)
        XCTAssertLessThan(swipe.reveal, 360)
    }

    func testArchiveSwipeTravelIsContinuousAndInvertible() {
        let knee = 88 + (ArchiveSwipeState.commitPoint(width: 360) - 88) / 0.8
        for travel: CGFloat in [-120, -1, 0, 30, 88, 120, knee - 1, knee + 1, 260, 400] {
            let reveal = ArchiveSwipeState.reveal(forTravel: travel, width: 360)
            XCTAssertEqual(ArchiveSwipeState.travel(forReveal: reveal, width: 360), travel, accuracy: 0.0001)
        }
        for edge: CGFloat in [0, 88, knee] {
            let step = ArchiveSwipeState.reveal(forTravel: edge + 0.0001, width: 360) - ArchiveSwipeState.reveal(forTravel: edge - 0.0001, width: 360)
            XCTAssertLessThan(step, 0.001)
        }
        XCTAssertEqual(ArchiveSwipeState.commitPoint(width: 360), 180)
        XCTAssertEqual(ArchiveSwipeState.commitPoint(width: 200), 144)
    }

    func testSwipeSpringStartsWithItsReleaseSpeedAndSettlesOnTarget() {
        let start = SwipeSpring.fling.state(from: 40, to: 88, velocity: 600, at: 0)
        XCTAssertEqual(start.value, 40, accuracy: 0.0001)
        XCTAssertEqual(start.velocity, 600, accuracy: 0.0001)
        let rest = SwipeSpring.snap.state(from: 40, to: 88, velocity: 600, at: 1.5)
        XCTAssertEqual(rest.value, 88, accuracy: 0.01)
        let samples = (0..<120).map { SwipeSpring.snap.state(from: 0, to: 100, velocity: 0, at: Double($0) / 120).value }
        XCTAssertLessThanOrEqual(samples.max() ?? 0, 100)
        let settle = SwipeSpring.snap.settleTime(from: 0, to: 100, velocity: 0)
        XCTAssertGreaterThan(settle, 0.25)
        XCTAssertLessThan(settle, 0.6)
    }

    func testArchiveSwipeMotionHandsOverItsPositionAndSpeed() {
        let began = Date(timeIntervalSinceReferenceDate: 0)
        let motion = ArchiveSwipeMotion(
            from: ArchiveSwipePose(reveal: 150), to: ArchiveSwipePose(reveal: 360, spread: 1, land: 1),
            velocity: ArchiveSwipePose(reveal: 900), spring: .exit, reduceMotion: false, began: began
        )
        XCTAssertEqual(motion.pose(at: began).reveal, 150, accuracy: 0.001)
        XCTAssertEqual(motion.velocity(at: began).reveal, 900, accuracy: 0.001)
        let midway = began.addingTimeInterval(0.1)
        XCTAssertGreaterThan(motion.pose(at: midway).reveal, 150)
        XCTAssertGreaterThan(motion.pose(at: midway).land, 0)
        XCTAssertEqual(motion.pose(at: began.addingTimeInterval(motion.duration + 0.1)), motion.target)
        let leave = motion.time { $0.reveal >= 358 && $0.land >= 0.92 }
        XCTAssertGreaterThan(leave, 0.1)
        XCTAssertLessThan(leave, motion.duration)

        // A throw from beside the action: the gap to the card only closes on the way out.
        let exit = ArchiveSwipeMotion(
            from: ArchiveSwipePose(reveal: 106), to: ArchiveSwipePose(reveal: 370, spread: 1, land: 1),
            velocity: ArchiveSwipePose(reveal: 2000), spring: .exit, reduceMotion: false, glued: true, began: began
        )
        var gap: CGFloat = 18.001
        for step in 0...60 {
            let pose = exit.pose(at: began.addingTimeInterval(Double(step) / 120))
            let next = (1 - pose.spread) * max(0, min(pose.reveal, 370) - 88)
            XCTAssertLessThanOrEqual(next, gap + 0.001)
            gap = next
        }

        let still = ArchiveSwipeMotion(from: ArchiveSwipePose(reveal: 150), to: ArchiveSwipePose(),
                                       velocity: ArchiveSwipePose(reveal: 900), spring: .fling, reduceMotion: true, began: began)
        XCTAssertEqual(still.velocity, ArchiveSwipePose())
        XCTAssertEqual(still.revealSpring, .reduced)
    }

    func testInternationalPostWaitsForItsAutomaticLookup() {
        let id = UUID()
        var parcel = makeParcel(id: id, events: [])
        parcel.carrier = .internationalPost
        parcel.syncStatus = .pending
        XCTAssertEqual(parcel.displayStatus.key, "status.syncing")
        XCTAssertTrue(parcel.displayStatus.syncing)
    }

    @MainActor
    func testRetryAfterSupportsBothHeaderFormats() {
        let now = DateParser.date("2026-09-06T12:00:00Z")!
        XCTAssertEqual(DeliveryAPIClient.retryAfterSeconds("12", now: now), 12)
        XCTAssertEqual(DeliveryAPIClient.retryAfterSeconds("Sun, 06 Sep 2026 12:00:12 GMT", now: now), 12)
        XCTAssertEqual(DeliveryAPIClient.retryAfterSeconds("invalid", now: now), 0)
        XCTAssertEqual(DeliveryAPIClient.retryAfterSeconds(nil, now: now), 0)
    }

    func testPickupAndDeliveryActionsSurviveSyncErrors() {
        let id = UUID()
        for (stage, attention) in [
            (TrackingStage.readyForPickup, ParcelAttention.readyForPickup),
            (.failedAttempt, .failedAttempt),
            (.customs, .customs),
            (.exception, .exception),
        ] {
            var parcel = makeParcel(id: id, events: [event(id, stage, "2026-09-06T10:00:00Z")])
            parcel.syncStatus = .error
            XCTAssertEqual(parcel.attention(), attention)
            XCTAssertEqual(parcel.currentStage, stage)
        }
    }

    func testCustomsRequestToTheRecipientNeedsActionWhileItIsTheNewestScan() throws {
        let now = DateParser.date("2026-08-09T12:00:00Z")!
        let id = UUID()
        // As the server serves it (parcelPriority.test.ts).
        let held = try JSONDecoder.deliveryTracker.decode(TrackingEvent.self, from: Data("""
            {"id":"\(UUID())","package_id":"\(id)","stage":"customs","description":"Duties or taxes are due on this package",
             "occurred_at":"2026-08-09T08:00:00Z","recipient_action":"duties"}
            """.utf8))
        XCTAssertEqual(held.recipientAction, .duties)
        var parcel = makeParcel(id: id, events: [event(id, .inTransit, "2026-08-08T10:00:00Z"), held])
        XCTAssertEqual(parcel.attention(now: now), .customsDuties)
        XCTAssertEqual(parcel.notice(now: now)?.localizationKey, "attention.customs_duties")
        XCTAssertNil(ParcelOrganizer.nextDelivery(from: [parcel], now: now))
        parcel.syncStatus = .error
        XCTAssertEqual(parcel.attention(now: now), .customsDuties)
        // Each request reads as the web's label.
        for (action, attention) in [(RecipientAction.taxID, ParcelAttention.customsTaxID), (.duties, .customsDuties),
                                    (.documents, .customsDocuments)] {
            XCTAssertEqual(ParcelAttention(action), attention)
            XCTAssertEqual(attention.localizationKey, "attention.customs_\(action.rawValue)")
        }
        // Paid: the carrier moved on.
        let released = makeParcel(id: id, events: [held, event(id, .inTransit, "2026-08-09T10:00:00Z")])
        XCTAssertNil(released.attention(now: now))
    }

    func testSenderMetadataDecodesWithoutRequiringItOnOlderResponses() throws {
        let sender = try JSONDecoder.deliveryTracker.decode(
            CarrierData.self, from: Data(#"{"sender_name":"Example sender"}"#.utf8)
        )
        XCTAssertEqual(sender.senderName, "Example sender")
        let older = try JSONDecoder.deliveryTracker.decode(CarrierData.self, from: Data("{}".utf8))
        XCTAssertNil(older.senderName)
    }

    func testDecodesRoutingInputNeededAndPromptsOnlyWhileAnotherCarrierWaits() throws {
        let packageID = UUID()
        let json = """
        {
          "packages": [{
            "id": "\(packageID.uuidString)",
            "tracking_number": "12345678901",
            "label": "Test parcel",
            "carrier": "gls-de",
            "created_at": "2026-08-01T10:00:00Z",
            "sync_status": "ok",
            "carrier_data": { "routing": { "input_needed": { "carrier": "gls-ch", "field": "dpdPostcode" } } },
            "notifications_muted": false,
            "tracking_events": [{
              "id": "\(UUID().uuidString)",
              "package_id": "\(packageID.uuidString)",
              "stage": "in_transit",
              "description": "In transit",
              "location": null,
              "occurred_at": "2026-08-09T10:00:00Z"
            }]
          }]
        }
        """
        let parcel = try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(json.utf8)).packages.first)
        XCTAssertEqual(parcel.inputNeeded, CarrierInputNeeded(carrier: .glsCh, field: .dpdPostcode))
        XCTAssertEqual(parcel.inputNeededPrompt?.carrier, .glsCh)

        // Its own carrier asks only while the parcel has no postcode and the sync error isn't already asking.
        var sameCarrier = parcel
        sameCarrier.carrier = .glsCh
        XCTAssertEqual(sameCarrier.inputNeededPrompt?.carrier, .glsCh)
        var withPostcode = sameCarrier
        withPostcode.dpdPostcode = "8000"
        XCTAssertNil(withPostcode.inputNeededPrompt)
        var errorAsks = sameCarrier
        errorAsks.syncError = "carrier:input_required"
        XCTAssertNil(errorAsks.inputNeededPrompt)
        // The postcode its own carrier refused is the owner's to check; another carrier's refusal is not.
        XCTAssertNil(withPostcode.refusedPostcode)
        var refused = withPostcode
        refused.carrierData?.routing?.postcodeRefused = CarrierPostcodeRefused(carrier: .glsCh)
        XCTAssertEqual(refused.refusedPostcode, "8000")
        // Before its first scan the list says the postcode wasn't found, not that it waits for the carrier.
        var unscanned = refused
        unscanned.trackingEvents = []
        unscanned.syncStatus = .waiting
        XCTAssertEqual(unscanned.displayStatus.key, "status.postcodeRefused")
        unscanned.syncStatus = .error
        unscanned.syncError = "carrier:input_required"
        XCTAssertEqual(unscanned.displayStatus.key, "status.postcodeRefused")
        var unrefused = unscanned
        unrefused.carrierData?.routing?.postcodeRefused = nil
        unrefused.syncStatus = .waiting
        unrefused.syncError = nil
        XCTAssertEqual(unrefused.displayStatus.key, "status.waitingFor")
        XCTAssertEqual(refused.displayStatus.key, "stage.in_transit")
        refused.carrier = .dpd
        XCTAssertNil(refused.refusedPostcode)
        var archived = parcel
        archived.archivedAt = "2026-08-10T10:00:00Z"
        XCTAssertNil(archived.inputNeededPrompt)
        for stage in [TrackingStage.delivered, .returned] {
            var finished = parcel
            finished.trackingEvents.append(event(packageID, stage, "2026-08-10T10:00:00Z"))
            XCTAssertNil(finished.inputNeededPrompt, stage.rawValue)
        }
        let trackingURLNeeded = try JSONDecoder.deliveryTracker.decode(
            CarrierInputNeeded.self, from: Data(#"{"carrier":"gls-ch","field":"trackingUrl"}"#.utf8)
        )
        XCTAssertEqual(trackingURLNeeded.field, .trackingURL)
        var link = parcel
        link.carrierData?.routing?.inputNeeded = trackingURLNeeded
        XCTAssertNil(link.inputNeededPrompt)

        let older = try JSONDecoder.deliveryTracker.decode(CarrierData.self, from: Data(#"{"sender_name":"Example sender"}"#.utf8))
        XCTAssertNil(older.routing)
        var untouched = parcel
        untouched.carrierData = older
        XCTAssertNil(untouched.inputNeeded)
        XCTAssertNil(untouched.inputNeededPrompt)
    }

    func testPromptsForASuggestedCarrierOnlyWhileNobodyAnsweredForTheNumber() throws {
        let packageID = UUID()
        let json = """
        {
          "packages": [{
            "id": "\(packageID.uuidString)",
            "tracking_number": "12345678",
            "label": "Test parcel",
            "carrier": "unknown",
            "created_at": "2026-08-01T10:00:00Z",
            "sync_status": "waiting",
            "carrier_data": { "routing": { "input_suggested": { "carrier": "mondial-relay", "field": "dpdPostcode" } } },
            "notifications_muted": false,
            "tracking_events": []
          }]
        }
        """
        let parcel = try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(json.utf8)).packages.first)
        XCTAssertEqual(parcel.inputSuggestedPrompt, CarrierInputSuggested(carrier: .mondialRelay, field: .dpdPostcode))
        XCTAssertNil(parcel.inputNeededPrompt)

        // Filed under a carrier, archived, with a scan, or once a carrier asks for the postcode itself: nothing suggested.
        var filed = parcel
        filed.carrier = .mondialRelay
        XCTAssertNil(filed.inputSuggestedPrompt)
        var archived = parcel
        archived.archivedAt = "2026-08-10T10:00:00Z"
        XCTAssertNil(archived.inputSuggestedPrompt)
        var scanned = parcel
        scanned.trackingEvents.append(event(packageID, .inTransit, "2026-08-10T10:00:00Z"))
        XCTAssertNil(scanned.inputSuggestedPrompt)
        var needed = parcel
        needed.carrierData?.routing?.inputNeeded = CarrierInputNeeded(carrier: .glsCh, field: .dpdPostcode)
        XCTAssertNil(needed.inputSuggestedPrompt)
        XCTAssertEqual(needed.inputNeededPrompt?.carrier, .glsCh)
    }

    func testAsksForThePostcodeACheckedParcelsCarrierShowsMoreWith() throws {
        let catalog = CarrierCatalog(cacheURL: nil)
        let json = """
        {
          "packages": [{
            "id": "\(UUID().uuidString)",
            "tracking_number": "06080000000002",
            "label": "Test parcel",
            "carrier": "dpd",
            "created_at": "2026-08-01T10:00:00Z",
            "last_synced_at": "2026-08-02T10:00:00Z",
            "sync_status": "ok",
            "notifications_muted": false,
            "tracking_events": []
          }]
        }
        """
        let parcel = try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(json.utf8)).packages.first)
        XCTAssertTrue(parcel.showsMoreWithPostcode(catalog))

        // Not before its first check, which gives it the account's own postcode, nor with one, nor once archived.
        var unchecked = parcel
        unchecked.lastSyncedAt = nil
        XCTAssertFalse(unchecked.showsMoreWithPostcode(catalog))
        var given = parcel
        given.dpdPostcode = "8000"
        XCTAssertFalse(given.showsMoreWithPostcode(catalog))
        // Nor for a parcel followed without an account whose lookup had one, which its link never shows.
        XCTAssertFalse(parcel.showsMoreWithPostcode(catalog, postcodeGiven: true))
        var archived = parcel
        archived.archivedAt = "2026-08-10T10:00:00Z"
        XCTAssertFalse(archived.showsMoreWithPostcode(catalog))
        // DHL needs it for some twelve-digit numbers only, and asks for those.
        var dhl = parcel
        dhl.carrier = .dhl
        dhl.trackingNumber = "000000000012"
        XCTAssertFalse(dhl.showsMoreWithPostcode(catalog))
    }

    func testAsksForThePhoneDigitsACheckedParcelsCarrierShowsItsScansWith() throws {
        let catalog = try PhoneDigitsCatalog.make()
        let json = """
        {
          "packages": [{
            "id": "\(UUID().uuidString)",
            "tracking_number": "JMX000000000001",
            "label": "Test parcel",
            "carrier": "j-and-t-mx",
            "created_at": "2026-11-01T10:00:00Z",
            "last_synced_at": "2026-11-02T10:00:00Z",
            "sync_status": "ok",
            "phone_digits": null,
            "notifications_muted": false,
            "tracking_events": []
          }]
        }
        """
        let parcel = try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(json.utf8)).packages.first)
        XCTAssertNil(parcel.phoneDigits)
        XCTAssertTrue(parcel.showsMoreWithPhoneDigits(catalog))
        XCTAssertFalse(parcel.showsMoreWithPostcode(catalog))

        // Not with the digits, nor before its first check, once archived, delivered or returned, nor while the sync
        // error asks for them already.
        let given = try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(
            json.replacingOccurrences(of: "\"phone_digits\": null", with: "\"phone_digits\": \"0472\"").utf8
        )).packages.first)
        XCTAssertEqual(given.phoneDigits, "0472")
        XCTAssertFalse(given.showsMoreWithPhoneDigits(catalog))
        var unchecked = parcel
        unchecked.lastSyncedAt = nil
        XCTAssertFalse(unchecked.showsMoreWithPhoneDigits(catalog))
        var archived = parcel
        archived.archivedAt = "2026-11-03T10:00:00Z"
        XCTAssertFalse(archived.showsMoreWithPhoneDigits(catalog))
        for stage in [TrackingStage.delivered, .returned] {
            var finished = parcel
            finished.trackingEvents = [event(parcel.id, stage, "2026-11-03T10:00:00Z")]
            XCTAssertFalse(finished.showsMoreWithPhoneDigits(catalog), stage.rawValue)
        }
        var moving = parcel
        moving.trackingEvents = [event(parcel.id, .inTransit, "2026-11-03T10:00:00Z")]
        XCTAssertTrue(moving.showsMoreWithPhoneDigits(catalog))
        var asked = parcel
        asked.syncError = "carrier:input_required"
        XCTAssertFalse(asked.showsMoreWithPhoneDigits(catalog))
        // A postcode asked for comes first.
        var postcode = parcel
        postcode.carrierData = CarrierData(routing: CarrierDataRouting(inputNeeded: CarrierInputNeeded(carrier: .dpd, field: .dpdPostcode)))
        XCTAssertNotNil(postcode.inputNeededPrompt)
        XCTAssertFalse(postcode.showsMoreWithPhoneDigits(catalog))
        // A carrier that never reads them, and a parcel followed without an account, whose link never says if it has them.
        var dpd = parcel
        dpd.carrier = .dpd
        dpd.trackingNumber = "06080000000002"
        XCTAssertFalse(dpd.showsMoreWithPhoneDigits(catalog))
        XCTAssertFalse(parcel.showsMoreWithPhoneDigits(catalog, knowsInputs: false))
    }

    func testTheDemoKeepsTheDigitsWithItsCarrierAndForgetsThemWithout() throws {
        let suite = "demo-digits-tests-\(UUID().uuidString)"
        let defaults = UserDefaults(suiteName: suite)!
        defer { defaults.removePersistentDomain(forName: suite) }
        let repo = DemoRepository(defaults: defaults, language: { .en })
        let added = try repo.add(CreatePackageRequest(trackingNumber: "JMX000000000001", label: "", carrier: .jAndTMx, phoneDigits: "0472"))
        XCTAssertEqual(added.phoneDigits, "0472")
        let kept = try repo.changeCarrier(id: added.id, carrier: .jAndTMx, trackingURL: nil, dpdPostcode: nil, dpdPostcodeCountry: nil,
                                          phoneDigits: "0473")
        XCTAssertEqual(kept.phoneDigits, "0473")
        let cleared = try repo.changeCarrier(id: added.id, carrier: .jAndTCn, trackingURL: nil, dpdPostcode: nil, dpdPostcodeCountry: nil)
        XCTAssertNil(cleared.phoneDigits)
    }

    func testNamesTheLikelyCarrierWhileNobodyAnsweredAndAsksToChooseAfterAWeek() throws {
        let packageID = UUID()
        let json = """
        {
          "packages": [{
            "id": "\(packageID.uuidString)",
            "tracking_number": "EOB10293847561029384",
            "label": "Test parcel",
            "carrier": "unknown",
            "created_at": "2026-08-01T10:00:00Z",
            "sync_status": "waiting",
            "carrier_data": { "routing": { "likely_carrier": { "carrier": "brt", "since": "2026-08-01T10:00:00Z" } } },
            "notifications_muted": false,
            "tracking_events": []
          }]
        }
        """
        let parcel = try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(json.utf8)).packages.first)
        let since = try XCTUnwrap(DateParser.date("2026-08-01T10:00:00Z"))
        let early = try XCTUnwrap(parcel.likelyCarrierPrompt(now: since.addingTimeInterval(24 * 60 * 60)))
        XCTAssertEqual(early.carrier, .brt)
        XCTAssertFalse(early.waited)
        XCTAssertEqual(parcel.likelyCarrierPrompt(now: since.addingTimeInterval(Parcel.likelyCarrierWait))?.waited, true)

        // Filed under a carrier, archived, with a scan, or once a postcode is asked for: no likely carrier.
        var filed = parcel
        filed.carrier = .brt
        XCTAssertNil(filed.likelyCarrierPrompt(now: since))
        var archived = parcel
        archived.archivedAt = "2026-08-10T10:00:00Z"
        XCTAssertNil(archived.likelyCarrierPrompt(now: since))
        var scanned = parcel
        scanned.trackingEvents.append(event(packageID, .inTransit, "2026-08-10T10:00:00Z"))
        XCTAssertNil(scanned.likelyCarrierPrompt(now: since))
        var suggested = parcel
        suggested.carrierData?.routing?.inputSuggested = CarrierInputSuggested(carrier: .mondialRelay, field: .dpdPostcode)
        XCTAssertNil(suggested.likelyCarrierPrompt(now: since))
    }

    @MainActor
    func testNamesTheProbableCarrierWhereTheCarrierIsNamedUntilACarrierAnswers() throws {
        let packageID = UUID()
        // A synthetic number in DPD Switzerland's depot range, which no carrier has answered for yet.
        let json = """
        {
          "packages": [{
            "id": "\(packageID.uuidString)",
            "tracking_number": "06080000000002",
            "label": "Test parcel",
            "carrier": "unknown",
            "created_at": "2026-10-28T09:00:00Z",
            "sync_status": "waiting",
            "carrier_data": { "routing": { "likely_carrier": { "carrier": "dpd", "since": "2026-10-28T10:00:00Z" } } },
            "notifications_muted": false,
            "tracking_events": []
          }]
        }
        """
        let parcel = try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(json.utf8)).packages.first)
        let localizer = Localizer()
        let originalLanguage = localizer.language
        defer { localizer.language = originalLanguage }
        localizer.language = .en
        XCTAssertEqual(parcel.likelyCarrier, .dpd)
        XCTAssertEqual(parcel.displayStatus.key, "status.likelyCarrier")
        XCTAssertEqual(localizer.parcelStatus(parcel), "Waiting for DPD Switzerland’s first scan")
        let identity = CarrierVisualIdentity.shown(for: parcel, localizer: localizer)
        XCTAssertEqual(identity.name, "Probably DPD Switzerland")
        XCTAssertEqual(identity.decal, CarrierVisualIdentity.of(.unknown, language: .en).decal)
        // Every language names the carrier in both lines.
        for language in AppLanguage.allCases {
            localizer.language = language
            XCTAssertTrue(localizer.parcelStatus(parcel).contains("DPD Switzerland"), "\(language)")
            XCTAssertTrue(CarrierVisualIdentity.shown(for: parcel, localizer: localizer).name.contains("DPD Switzerland"), "\(language)")
        }
        localizer.language = .en

        // A shape nobody leads keeps the words of a number no carrier knows yet.
        var shared = parcel
        shared.carrierData = nil
        XCTAssertNil(shared.likelyCarrier)
        XCTAssertEqual(shared.displayStatus.key, "status.noHistory")
        XCTAssertEqual(CarrierVisualIdentity.shown(for: shared, localizer: localizer).name, CarrierVisualIdentity.of(.unknown, language: .en).name)
        // Once another carrier answers for the number, the parcel is filed under it and the guess goes.
        var filed = parcel
        filed.carrier = .seur
        XCTAssertNil(filed.likelyCarrier)
        XCTAssertEqual(filed.displayStatus.key, "status.waitingFor")
        XCTAssertEqual(localizer.parcelStatus(filed), "Waiting for SEUR")
        XCTAssertEqual(CarrierVisualIdentity.shown(for: filed, localizer: localizer).name, "SEUR")
    }

    @MainActor func testTellsAnOrderNumberOrAShortNumberNoCarrierAnsweredFor() throws {
        // Synthetic numbers: Amazon's without hyphens, AliExpress's, Temu's as a lookup stores it, a short one.
        XCTAssertEqual(CarrierCatalog.nonTrackingShape("30245719832294617"), .order(shop: "amazon"))
        XCTAssertEqual(CarrierCatalog.nonTrackingShape("3041234567890123"), .order(shop: "aliexpress"))
        // Mercado Libre's order and pack ids: 16 digits that start 2000, named Mercado Livre in Brazil's Portuguese.
        XCTAssertEqual(CarrierCatalog.nonTrackingShape("2000123456789012"), .order(shop: "mercadolibre"))
        XCTAssertNil(CarrierCatalog.nonTrackingShape("200012345678901"))
        XCTAssertNil(CarrierCatalog.nonTrackingShape("2001123456789012"))
        XCTAssertEqual(NonTrackingShape.shopName("mercadolibre", language: .es), "Mercado Libre")
        XCTAssertEqual(NonTrackingShape.shopName("mercadolibre", language: .ptBR), "Mercado Livre")
        XCTAssertEqual(NonTrackingShape.shopName("amazon", language: .ptBR), "Amazon")
        // The parcel page tells Mercado Libre's buyers where the number is; other shops' notes name the shop.
        let localizer = Localizer()
        let originalLanguage = localizer.language
        defer { localizer.language = originalLanguage }
        localizer.language = .en
        XCTAssertEqual(NonTrackingShape.orderNote("mercadolibre", localizer: localizer), localizer.text("link.notTracking.mercadolibre"))
        XCTAssertTrue(NonTrackingShape.orderNote("mercadolibre", localizer: localizer).contains("My purchases"))
        XCTAssertEqual(NonTrackingShape.orderNote("aliexpress", localizer: localizer),
                       localizer.text("link.notTracking.order", ["shop": "AliExpress"]))
        XCTAssertEqual(CarrierCatalog.nonTrackingShape("PO00012345678901234567"), .order(shop: "temu"))
        XCTAssertEqual(CarrierCatalog.nonTrackingShape("10293"), .short)
        XCTAssertNil(CarrierCatalog.nonTrackingShape("99945719832294617"))
        XCTAssertNil(CarrierCatalog.nonTrackingShape("12345678"))
        // Synthetic shipping container numbers whose ISO 6346 check digit adds up, one through its remainder of 10;
        // one whose check digit doesn't, and four letters and seven digits that aren't a freight container's.
        XCTAssertEqual(CarrierCatalog.nonTrackingShape("TEMU1234565"), .container)
        XCTAssertEqual(CarrierCatalog.nonTrackingShape("TEMU0000080"), .container)
        XCTAssertNil(CarrierCatalog.nonTrackingShape("TEMU1234567"))
        XCTAssertNil(CarrierCatalog.nonTrackingShape("ZZZJ0000008"))
        // Synthetic numbers that start like UPS's or J&T Australia's with a character missing or one too many; UPS's
        // own length, a DHL rule of several lengths, two characters short, and one character off two carriers' numbers.
        XCTAssertEqual(CarrierCatalog.nonTrackingShape("1Z999AA1012345678"), .mistyped(carrier: .ups, change: .missing))
        XCTAssertEqual(CarrierCatalog.nonTrackingShape("1Z999AA101234567840"), .mistyped(carrier: .ups, change: .extra))
        XCTAssertEqual(CarrierCatalog.nonTrackingShape("JTAU12345678901"), .mistyped(carrier: .jAndTAu, change: .missing))
        XCTAssertNil(CarrierCatalog.nonTrackingShape("1Z999AA10123456784"))
        XCTAssertNil(CarrierCatalog.nonTrackingShape("JJD0000000000000000"))
        XCTAssertNil(CarrierCatalog.nonTrackingShape("1Z999AA10123456"))
        XCTAssertNil(CarrierCatalog.nonTrackingShape("JTE12345678901"))

        let packageID = UUID()
        let json = """
        {
          "packages": [{
            "id": "\(packageID.uuidString)",
            "tracking_number": "30245719832294617",
            "label": "Test parcel",
            "carrier": "unknown",
            "created_at": "2026-08-01T10:00:00Z",
            "sync_status": "waiting",
            "notifications_muted": false,
            "tracking_events": []
          }]
        }
        """
        let parcel = try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(json.utf8)).packages.first)
        XCTAssertEqual(parcel.nonTrackingShape, .order(shop: "amazon"))
        // Filed under a carrier, or with a carrier its own format points to: nothing said.
        var filed = parcel
        filed.carrier = .ups
        XCTAssertNil(filed.nonTrackingShape)
        var likely = parcel
        likely.carrierData = CarrierData(routing: CarrierDataRouting(likelyCarrier: LikelyCarrier(carrier: .brt, since: "2026-08-01T10:00:00Z")))
        XCTAssertNil(likely.nonTrackingShape)
    }

    func testDecodesSharedAPIContractFixture() throws {
        struct Fixture: Decodable {
            let packageList: PackageListResponse
            let queue: QueueResponse
            let job: SyncJobResponse
        }
        let url = try XCTUnwrap(Bundle.main.url(forResource: "ContractFixtures", withExtension: "json"))
        let fixture = try JSONDecoder.deliveryTracker.decode(
            Fixture.self,
            from: Data(contentsOf: url)
        )

        XCTAssertEqual(fixture.packageList.packages.first?.carrier, .swissPost)
        XCTAssertEqual(fixture.packageList.packages.first?.trackingEvents.first?.stage, .inTransit)
        XCTAssertEqual(fixture.queue.jobIDs.count, 1)
        XCTAssertEqual(fixture.job.status, .succeeded)
        XCTAssertEqual(fixture.job.result?.checked, 1)
    }

    func testDecodesProductionPackagePayload() throws {
        let packageID = UUID()
        let eventID = UUID()
        let json = """
        {
          "packages": [{
            "id": "\(packageID.uuidString)",
            "tracking_number": "1Z999AA10123456784",
            "label": "Test parcel",
            "carrier": "ups",
            "created_at": "2026-08-01T10:00:00Z",
            "expected_delivery": null,
            "last_status_text": "In transit",
            "last_synced_at": "2026-08-09T10:00:00Z",
            "sync_status": "ok",
            "sync_error": null,
            "tracking_url": "https://www.ups.com/track?tracknum=1Z999AA10123456784",
            "dpd_postcode": null,
            "carrier_data": null,
            "archived_at": null,
            "notifications_muted": false,
            "tracking_events": [{
              "id": "\(eventID.uuidString)",
              "package_id": "\(packageID.uuidString)",
              "stage": "in_transit",
              "description": "In transit",
              "location": null,
              "occurred_at": "2026-08-09T10:00:00Z"
            }]
          }]
        }
        """

        let response = try JSONDecoder.deliveryTracker.decode(
            PackageListResponse.self,
            from: Data(json.utf8)
        )

        XCTAssertEqual(response.packages.first?.trackingURL, "https://www.ups.com/track?tracknum=1Z999AA10123456784")
        XCTAssertEqual(response.packages.first?.trackingEvents.first?.packageID, packageID)
    }

    func testDecodesNullTrackingEventsAsEmptyHistory() throws {
        let json = """
        {
          "packages": [{
            "id": "\(UUID().uuidString)",
            "tracking_number": "999999999999999999",
            "label": "New parcel",
            "carrier": "swiss-post",
            "created_at": "2026-08-09T10:00:00Z",
            "expected_delivery": null,
            "last_status_text": null,
            "last_synced_at": null,
            "sync_status": "waiting",
            "sync_error": null,
            "tracking_url": null,
            "dpd_postcode": null,
            "archived_at": null,
            "notifications_muted": false,
            "tracking_events": null
          }]
        }
        """

        let response = try JSONDecoder.deliveryTracker.decode(
            PackageListResponse.self,
            from: Data(json.utf8)
        )

        XCTAssertEqual(response.packages.first?.trackingEvents, [])
    }

    func testJournalShowsAnEarlierCarriersCopyUnderTheScanItRepeats() throws {
        let packageID = UUID()
        let (scan, copy, other, orphan) = (UUID(), UUID(), UUID(), UUID())
        let event = { (id: UUID, description: String, at: String, relayOf: UUID?) in
            """
            {"id": "\(id.uuidString)", "package_id": "\(packageID.uuidString)", "stage": "in_transit",
             "description": "\(description)", "location": null, "occurred_at": "\(at)"\(relayOf.map { ", \"relay_of\": \"\($0.uuidString)\"" } ?? "")}
            """
        }
        let json = """
        {"packages": [{
          "id": "\(packageID.uuidString)", "tracking_number": "RELAYTEST0001", "label": "Relayed", "carrier": "dhl",
          "created_at": "2026-09-01T10:00:00Z", "expected_delivery": null, "last_status_text": null, "last_synced_at": null,
          "sync_status": "ok", "sync_error": null, "tracking_url": null, "dpd_postcode": null, "archived_at": null,
          "notifications_muted": false,
          "tracking_events": [
            \(event(scan, "Arrival in destination country", "2026-09-09T14:16:51Z", nil)),
            \(event(copy, "The shipment has arrived in the destination country", "2026-09-09T14:16:00Z", scan)),
            \(event(other, "Sorted for delivery", "2026-09-09T15:42:29Z", nil)),
            \(event(orphan, "The shipment is being prepared for delivery", "2026-09-10T04:44:00Z", UUID()))
          ]
        }]}
        """
        let parcel = try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(json.utf8)).packages.first)
        XCTAssertEqual(parcel.trackingEvents.first { $0.id == copy }?.relayOf, scan)
        // The copy is a line under its scan; one whose scan is not there keeps its row.
        XCTAssertEqual(parcel.journalEntries.map(\.id), [orphan, other, scan])
        XCTAssertEqual(parcel.journalEntries.last?.relays.map(\.id), [copy])
        XCTAssertTrue(parcel.journalEntries.dropLast().allSatisfy(\.relays.isEmpty))
    }

    func testCurrentEventIsTheScanTheJournalShowsNotTheLineUnderIt() throws {
        let packageID = UUID()
        let scan = UUID(uuidString: "00000000-0000-4000-8000-000000000001")!
        let line = UUID(uuidString: "FFFFFFFF-0000-4000-8000-000000000001")!
        let event = { (id: UUID, at: String, relayOf: UUID?) in
            """
            {"id": "\(id.uuidString)", "package_id": "\(packageID.uuidString)", "stage": "in_transit",
             "description": "Sorted", "location": null, "occurred_at": "\(at)"\(relayOf.map { ", \"relay_of\": \"\($0.uuidString)\"" } ?? "")}
            """
        }
        let parcel = { (lineAt: String) in
            let json = """
            {"packages": [{
              "id": "\(packageID.uuidString)", "tracking_number": "RELAYTEST0002", "label": "Relayed", "carrier": "la-poste",
              "created_at": "2026-09-01T10:00:00Z", "expected_delivery": null, "last_status_text": null, "last_synced_at": null,
              "sync_status": "ok", "sync_error": null, "tracking_url": null, "dpd_postcode": null, "archived_at": null,
              "notifications_muted": false,
              "tracking_events": [
                \(event(scan, "2026-09-09T14:16:00Z", nil)),
                \(event(line, lineAt, scan))
              ]
            }]}
            """
            return try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(json.utf8)).packages.first)
        }
        // The line would win the tie on its id, or is a few seconds newer: the scan it sits under is the parcel's step.
        XCTAssertEqual(try parcel("2026-09-09T14:16:00Z").currentEvent?.id, scan)
        XCTAssertEqual(try parcel("2026-09-09T14:16:20Z").currentEvent?.id, scan)
    }

    func testScansOfOneInstantAndStageReadInTheOrderTheirSourceListedThem() throws {
        let packageID = UUID()
        let arrived = UUID(uuidString: "FFFFFFFF-0000-4000-8000-000000000001")!
        let departed = UUID(uuidString: "00000000-0000-4000-8000-000000000001")!
        let unordered = UUID(uuidString: "FFFFFFFF-0000-4000-8000-000000000002")!
        let event = { (id: UUID, tieOrder: Int?) in
            """
            {"id": "\(id.uuidString)", "package_id": "\(packageID.uuidString)", "stage": "in_transit",
             "description": "Sorted", "location": null, "occurred_at": "2026-09-09T14:16:00Z"\(tieOrder.map { ", \"tie_order\": \($0)" } ?? "")}
            """
        }
        let json = """
        {"packages": [{
          "id": "\(packageID.uuidString)", "tracking_number": "TIETEST0001", "label": "Tied", "carrier": "la-poste",
          "created_at": "2026-09-01T10:00:00Z", "expected_delivery": null, "last_status_text": null, "last_synced_at": null,
          "sync_status": "ok", "sync_error": null, "tracking_url": null, "dpd_postcode": null, "archived_at": null,
          "notifications_muted": false,
          "tracking_events": [\(event(unordered, nil)), \(event(arrived, 0)), \(event(departed, 1))]
        }]}
        """
        let parcel = try XCTUnwrap(JSONDecoder.deliveryTracker.decode(PackageListResponse.self, from: Data(json.utf8)).packages.first)
        // The ids would put the arrival first; a scan stored before the order goes after those with one.
        XCTAssertEqual(parcel.sortedEvents.map(\.id), [departed, arrived, unordered])
        XCTAssertEqual(parcel.currentEvent?.id, departed)
    }

    func testGeneratedRequestModelsEncodeAPIFieldNamesAndEnumValues() throws {
        let packageRequest = CreatePackageRequest(
            trackingNumber: "99.34.123456.99999980",
            carrier: .swissPost,
            trackingURL: "https://service.post.ch/parcel/123"
        )
        let packageJSON = try XCTUnwrap(
            JSONSerialization.jsonObject(
                with: JSONEncoder.deliveryTracker.encode(packageRequest)
            ) as? [String: Any]
        )

        XCTAssertEqual(packageJSON["trackingNumber"] as? String, packageRequest.trackingNumber)
        XCTAssertEqual(packageJSON["carrier"] as? String, "swiss-post")
        XCTAssertEqual(packageJSON["trackingUrl"] as? String, packageRequest.trackingURL)
        XCTAssertNil(packageJSON["trackingURL"])

        let carrierRequest = ChangePackageCarrierRequest(
            carrier: .mondialRelay,
            dpdPostcode: "59650"
        )
        let carrierJSON = try XCTUnwrap(
            JSONSerialization.jsonObject(
                with: JSONEncoder.deliveryTracker.encode(carrierRequest)
            ) as? [String: Any]
        )
        XCTAssertEqual(carrierJSON["carrier"] as? String, "mondial-relay")
        XCTAssertEqual(carrierJSON["dpdPostcode"] as? String, "59650")

        let pushRequest = NativePushDeviceRequest(
            token: "device-token",
            environment: .production,
            locale: .fr,
            deviceName: "iPhone",
            sendTest: true
        )
        let pushJSON = try XCTUnwrap(
            JSONSerialization.jsonObject(
                with: JSONEncoder.deliveryTracker.encode(pushRequest)
            ) as? [String: Any]
        )

        XCTAssertEqual(pushJSON["environment"] as? String, "production")
        XCTAssertEqual(pushJSON["locale"] as? String, "fr")
        XCTAssertEqual(pushJSON["sendTest"] as? Bool, true)

        let installationID = UUID()
        let parcelID = UUID()
        let liveRequest = LiveActivityUpdateTokenRequest(
            installationID: installationID,
            activityID: "activity-1",
            parcelID: parcelID,
            token: "ab".repeated(32),
            environment: .development,
            locale: .de
        )
        let liveJSON = try XCTUnwrap(
            JSONSerialization.jsonObject(
                with: JSONEncoder.deliveryTracker.encode(liveRequest)
            ) as? [String: Any]
        )
        XCTAssertEqual(liveJSON["installationId"] as? String, installationID.uuidString)
        XCTAssertEqual(liveJSON["parcelId"] as? String, parcelID.uuidString)
        XCTAssertEqual(liveJSON["activityId"] as? String, "activity-1")
    }

    func testDuplicateErrorDecodesItsExistingParcelLink() throws {
        let packageID = UUID()
        let error = try JSONDecoder.deliveryTracker.decode(
            ErrorResponse.self,
            from: Data("""
            {
              "error": "This tracking number is already in your delivery box",
              "packageId": "\(packageID.uuidString)"
            }
            """.utf8)
        )

        XCTAssertEqual(error.packageID, packageID)
    }

    func testFutureCarrierIdentifierDecodesAndEncodesWithoutAnAppRelease() throws {
        let packageID = UUID()
        let response = try JSONDecoder.deliveryTracker.decode(
            PackageListResponse.self,
            from: Data("""
            {
              "packages": [{
                "id": "\(packageID.uuidString)",
                "tracking_number": "FX12345678",
                "label": "Future parcel",
                "carrier": "future-express",
                "created_at": "2026-09-01T09:00:00Z",
                "sync_status": "waiting",
                "notifications_muted": false,
                "tracking_events": []
              }]
            }
            """.utf8)
        )
        let futureCarrier = try XCTUnwrap(response.packages.first?.carrier)
        XCTAssertEqual(futureCarrier.rawValue, "future-express")
        XCTAssertFalse(CarrierID.allCases.contains(futureCarrier))

        let request = CreatePackageRequest(
            trackingNumber: "FX12345678",
            carrier: futureCarrier
        )
        let object = try XCTUnwrap(
            JSONSerialization.jsonObject(
                with: JSONEncoder.deliveryTracker.encode(request)
            ) as? [String: Any]
        )
        XCTAssertEqual(object["carrier"] as? String, "future-express")
    }

    func testWidgetPrioritizesOutForDeliveryThenKeepsNextUp() {
        let nextUp = widgetParcel(label: "Next up", outForDelivery: false)
        let outForDelivery = widgetParcel(label: "Courier", outForDelivery: true)
        let later = widgetParcel(label: "Later", outForDelivery: false)
        let parcels = DeliveryWidgetSelection.parcels(onTheWay: [nextUp, outForDelivery, later], delivered: [])

        XCTAssertEqual(parcels, [outForDelivery, nextUp, later])
    }

    func testWidgetShowsEveryOutForDeliveryParcelBeforeOtherCandidates() {
        let nextUp = widgetParcel(label: "Next up", outForDelivery: false)
        let first = widgetParcel(label: "Courier one", outForDelivery: true)
        let second = widgetParcel(label: "Courier two", outForDelivery: true)
        let third = widgetParcel(label: "Courier three", outForDelivery: true)
        let parcels = DeliveryWidgetSelection.parcels(onTheWay: [nextUp, first, second, third], delivered: [])

        XCTAssertEqual(parcels, [first, second, third, nextUp])
    }

    /// A delivered parcel comes after those on their way, for a day after it arrived, even when the app has not
    /// published since.
    func testWidgetKeepsADeliveredParcelForADay() {
        let now = Date(timeIntervalSince1970: 1_800_000_000)
        let nextUp = widgetParcel(label: "Next up", outForDelivery: false)
        var arrived = widgetParcel(label: "Arrived", outForDelivery: false)
        arrived.deliveredAt = now.addingTimeInterval(-3_600)
        var older = widgetParcel(label: "Older", outForDelivery: false)
        older.deliveredAt = now.addingTimeInterval(-DeliveryWidgetSelection.deliveredFor - 1)
        let parcels = DeliveryWidgetSelection.parcels(onTheWay: [nextUp], delivered: [arrived, older], now: now)

        XCTAssertEqual(parcels, [nextUp, arrived])
        XCTAssertEqual(DeliveryWidgetSelection.listed(parcels, now: now), [nextUp, arrived])
        XCTAssertEqual(DeliveryWidgetSelection.listed(parcels, now: now.addingTimeInterval(DeliveryWidgetSelection.deliveredFor)), [nextUp])
    }

    /// The widget lists every parcel the app publishes, in its order.
    func testWidgetListsEveryPublishedParcelInItsOrder() {
        let now = Date(timeIntervalSince1970: 1_800_000_000)
        let courier = widgetParcel(label: "Courier", outForDelivery: true)
        let onTheWay = (0..<15).map { widgetParcel(label: "Parcel \($0)", outForDelivery: false) }
        var arrived = widgetParcel(label: "Arrived", outForDelivery: false)
        arrived.deliveredAt = now.addingTimeInterval(-3_600)
        let parcels = DeliveryWidgetSelection.parcels(onTheWay: onTheWay + [courier], delivered: [arrived], now: now)

        XCTAssertEqual(parcels, [courier] + onTheWay + [arrived])
        let listed = DeliveryWidgetSelection.listed(parcels, now: now)
        XCTAssertEqual(listed, parcels)
        XCTAssertEqual(DeliveryWidgetSelection.listed(parcels, now: now.addingTimeInterval(DeliveryWidgetSelection.deliveredFor)), [courier] + onTheWay)

        XCTAssertEqual(DeliveryWidgetSelection.listed([], now: now), [])
    }

    /// The Parcels widget shows up to one, two or three parcels for a small, medium or large widget as cards, up to twice
    /// as many as passes, and more as rows under the app list's sections. A parcel delivered within the day counts, and
    /// stops counting a day after it arrived.
    func testParcelsWidgetPicksItsDesignByHowManyParcelsItLists() {
        func designs(_ size: DeliveryWidgetList.Size) -> String {
            String((1...8).map { count -> Character in
                switch DeliveryWidgetList.design(of: count, size: size) {
                case .cards: "C"
                case .passes: "P"
                case .sections: "S"
                }
            })
        }
        XCTAssertEqual(DeliveryWidgetList.Size.allCases.map(\.cards), [1, 2, 3])
        XCTAssertEqual(designs(.small), "CPSSSSSS")
        XCTAssertEqual(designs(.medium), "CCPPSSSS")
        XCTAssertEqual(designs(.large), "CCCPPPSS")

        let now = Date(timeIntervalSince1970: 1_800_000_000)
        let onTheWay = (0..<2).map { widgetParcel(label: "Parcel \($0)", outForDelivery: false) }
        var arrived = widgetParcel(label: "Arrived", outForDelivery: false)
        arrived.deliveredAt = now.addingTimeInterval(-3_600)
        let parcels = DeliveryWidgetSelection.parcels(onTheWay: onTheWay, delivered: [arrived], now: now)
        let design = { (date: Date) in DeliveryWidgetList.design(of: DeliveryWidgetSelection.listed(parcels, now: date).count, size: .medium) }
        XCTAssertEqual(design(now), .passes)
        XCTAssertEqual(design(now.addingTimeInterval(DeliveryWidgetSelection.deliveredFor)), .cards)
    }

    /// Under its sections the widget lists the parcels as the app's list does: its sections in its order, each led by its
    /// Next up, then the widgets' order; the cards put the Next up first. A snapshot from an app that published no
    /// sections lists them under none, in the widgets' order.
    func testParcelsWidgetSectionsAreTheAppListsWithItsNextUpFirst() {
        func parcel(_ label: String, _ section: String?, nextUp: Bool = false) -> DeliveryWidgetParcel {
            var parcel = widgetParcel(label: label, outForDelivery: false)
            parcel.section = section
            parcel.nextUp = nextUp
            return parcel
        }
        let today = parcel("Today", "today")
        let coming = parcel("Coming", "active")
        let next = parcel("Next", "today", nextUp: true)
        let arrived = parcel("Arrived", "delivered")
        let stuck = parcel("Stuck", "attention")
        let widgets = [today, coming, next, arrived, stuck]

        let sections = DeliveryWidgetList.sections(widgets)
        XCTAssertEqual(sections.map(\.kind), ["attention", "today", "active", "delivered"])
        XCTAssertEqual(sections.map { $0.parcels.map(\.label) }, [["Stuck"], ["Next", "Today"], ["Coming"], ["Arrived"]])
        XCTAssertEqual(DeliveryWidgetList.cardOrder(widgets).map(\.label), ["Next", "Today", "Coming", "Arrived", "Stuck"])
        XCTAssertEqual(DeliveryWidgetList.sectionKinds, ParcelSectionKind.allCases.map(\.rawValue))

        let unsectioned = [today, parcel("Older app", nil), coming]
        XCTAssertEqual(DeliveryWidgetList.sections(unsectioned), [DeliveryWidgetList.Section(kind: nil, parcels: unsectioned)])
        XCTAssertEqual(DeliveryWidgetList.sections([parcel("Newer app", "someday"), next]).map(\.kind), [nil])
        XCTAssertEqual(DeliveryWidgetList.sections([]), [])
    }

    /// The line that counts the parcels left out follows the last row shown, as they would: in a medium widget, at the
    /// foot of the second column, whichever section they are in. A heading never ends the first column.
    func testParcelsWidgetCountsTheParcelsLeftOutUnderTheColumnTheyWouldFollow() {
        func parcel(_ label: String, _ section: String) -> DeliveryWidgetParcel {
            var parcel = widgetParcel(label: label, outForDelivery: false)
            parcel.section = section
            return parcel
        }
        typealias Line = DeliveryWidgetList.Line
        let stuck = parcel("Stuck", "attention")
        let held = parcel("Held", "attention")
        let today = parcel("Today", "today")
        let coming = (1...4).map { parcel("Coming \($0)", "active") }
        let quiet = DeliveryWidgetList.sections([stuck, today] + coming)
        let busy = DeliveryWidgetList.sections([stuck, held, today] + coming)

        // Six parcels, four shown: the two left out are on their way, under the second column's last rows.
        let six = DeliveryWidgetList.lines(quiet, rows: 4, headings: true)
        XCTAssertEqual(six, [.heading("attention", count: 1), .row(stuck), .heading("today", count: 1), .row(today),
                             .heading("active", count: 4), .row(coming[0]), .row(coming[1]), .more(2)])
        XCTAssertEqual(DeliveryWidgetList.columns(six), [
            [.heading("attention", count: 1), .row(stuck), .heading("today", count: 1), .row(today)],
            [.heading("active", count: 4), .row(coming[0]), .row(coming[1]), .more(2)],
        ])
        // Seven: the first column ends on a row, not on the heading that would lead the second.
        XCTAssertEqual(DeliveryWidgetList.columns(DeliveryWidgetList.lines(busy, rows: 4, headings: true)), [
            [.heading("attention", count: 2), .row(stuck), .row(held)],
            [.heading("today", count: 1), .row(today), .heading("active", count: 4), .row(coming[0]), .more(3)],
        ])
        // Left out from a section none of whose rows shows: still after the last row shown.
        XCTAssertEqual(DeliveryWidgetList.columns(DeliveryWidgetList.lines(quiet, rows: 2, headings: true)), [
            [.heading("attention", count: 1), .row(stuck)],
            [.heading("today", count: 1), .row(today), .more(4)],
        ])
        // Every parcel shown: no line for others. A small widget has no headings.
        XCTAssertEqual(DeliveryWidgetList.lines(quiet, rows: 6, headings: true).last, Line.row(coming[3]))
        XCTAssertEqual(DeliveryWidgetList.lines(quiet, rows: 8, headings: true).filter { if case .more = $0 { true } else { false } }, [])
        XCTAssertEqual(DeliveryWidgetList.lines(busy, rows: 3, headings: false), [.row(stuck), .row(held), .row(today), .more(4)])
        // A single row, at the largest text, takes one column with its heading and the line for the others.
        XCTAssertEqual(DeliveryWidgetList.columns(DeliveryWidgetList.lines(busy, rows: 1, headings: true)), [
            [.heading("attention", count: 2), .row(stuck), .more(6)],
        ])
    }

    /// The Parcels widget shows as many rows as fit, trying the most first: four parcels in a small widget are four rows
    /// where they fit, in smaller type if they must, before three and a line for the fourth. A large widget's rows keep
    /// the usual type.
    func testParcelsWidgetShowsAsManyRowsAsFitBeforeCountingTheOthers() {
        typealias Layout = DeliveryWidgetList.Layout
        func rows(_ layouts: some Collection<Layout>) -> [String] { layouts.map { "\($0.rows)\($0.compact ? " smaller" : "")" } }

        XCTAssertEqual(rows(DeliveryWidgetList.layouts(count: 4, size: .small)),
                       ["4", "4 smaller", "3", "3 smaller", "2", "2 smaller", "1", "1 smaller"])
        XCTAssertEqual(rows(DeliveryWidgetList.layouts(count: 9, size: .medium).prefix(4)), ["9", "9 smaller", "8", "8 smaller"])
        let large = DeliveryWidgetList.layouts(count: 18, size: .large)
        XCTAssertEqual(large.map(\.rows), Array((1...18).reversed()))
        XCTAssertFalse(large.contains(where: \.compact))
        // However many parcels are listed, the widget measures a bounded number of layouts.
        for size in DeliveryWidgetList.Size.allCases {
            XCTAssertEqual(DeliveryWidgetList.layouts(count: 1_000, size: size).first, Layout(rows: size.mostRows, compact: false))
        }
    }

    /// Passes show every parcel or none: where they don't all fit, the widget tries rows, the most first, as the sections'
    /// design does, rather than fewer passes and a line for the others; in a medium widget, every parcel down one column
    /// before one is left out. The cards' and the sections' designs try no pass and no single column.
    func testParcelsWidgetTurnsPassesThatDontAllFitIntoRows() {
        func fits(_ design: DeliveryWidgetList.Design, _ count: Int, _ size: DeliveryWidgetList.Size) -> [String] {
            DeliveryWidgetList.fits(design, count: count, size: size).map { fit in
                switch fit {
                case .passes: "passes"
                case let .rows(layout):
                    "\(layout.rows)\(layout.compact ? " smaller" : "")\(layout.oneColumn ? " in one column" : "")"
                }
            }
        }
        XCTAssertEqual(fits(.passes, 2, .small), ["passes", "2", "2 smaller", "1", "1 smaller"])
        XCTAssertEqual(fits(.passes, 3, .medium),
                       ["passes", "3", "3 smaller", "3 smaller in one column", "2", "2 smaller", "1", "1 smaller"])
        XCTAssertEqual(fits(.passes, 4, .medium),
                       ["passes", "4", "4 smaller", "4 smaller in one column", "3", "3 smaller", "2", "2 smaller", "1", "1 smaller"])
        XCTAssertEqual(fits(.passes, 6, .large), ["passes", "6", "5", "4", "3", "2", "1"])
        for size in DeliveryWidgetList.Size.allCases {
            for count in 1...8 {
                let design = DeliveryWidgetList.design(of: count, size: size)
                let tried = DeliveryWidgetList.fits(design, count: count, size: size)
                XCTAssertEqual(tried.first == .passes, design == .passes, "\(size) \(count)")
                XCTAssertEqual(tried.filter { $0 == .passes }.count, design == .passes ? 1 : 0, "\(size) \(count)")
                // One column is only a medium widget's passes' last try at every parcel; the rest are the rows' layouts.
                let oneColumn = tried.filter { if case let .rows(layout) = $0 { layout.oneColumn } else { false } }
                XCTAssertEqual(oneColumn, design == .passes && size == .medium
                               ? [.rows(.init(rows: count, compact: true, oneColumn: true))] : [], "\(size) \(count)")
                XCTAssertEqual(Array(tried.dropFirst(design == .passes ? 1 : 0)).filter { !oneColumn.contains($0) },
                               DeliveryWidgetList.layouts(count: count, size: size).map(DeliveryWidgetList.Fit.rows))
            }
        }
    }

    /// A step's time is its clock on its day, and its short weekday and clock from the next, in the reader's language.
    func testAWidgetStepsTimeNamesItsWeekdayFromAnotherDay() throws {
        var calendar = Calendar(identifier: .gregorian)
        calendar.timeZone = try XCTUnwrap(TimeZone(identifier: "Europe/Paris"))
        let thursdayEvening = try XCTUnwrap(calendar.date(from: DateComponents(year: 2026, month: 11, day: 12, hour: 21, minute: 40)))
        let thursdayMorning = try XCTUnwrap(calendar.date(from: DateComponents(year: 2026, month: 11, day: 12, hour: 8, minute: 5)))
        let friday = try XCTUnwrap(calendar.date(byAdding: .day, value: 1, to: thursdayMorning))
        let time = { (step: DeliveryWidgetStep, now: Date, language: String) in
            step.time(now: now, locale: Locale(identifier: language), calendar: calendar)
        }

        XCTAssertEqual(time(DeliveryWidgetStep(at: thursdayMorning, text: "Sorted", clock: "08:05"), thursdayEvening, "en"), "08:05")
        let evening = DeliveryWidgetStep(at: thursdayEvening, text: "Sorted", clock: "21:40")
        XCTAssertEqual(["en", "fr", "de"].map { time(evening, friday, $0) }, ["Thu 21:40", "jeu 21:40", "Do 21:40"])
        // A step dated by its day alone names the day; one without the app's clock reads the time in the language.
        XCTAssertEqual(time(DeliveryWidgetStep(at: thursdayMorning, text: "Sorted", clock: ""), friday, "en"), "Thu")
        XCTAssertEqual(time(DeliveryWidgetStep(at: thursdayEvening, text: "Sorted"), thursdayEvening, "fr"), "21:40")
    }

    /// A row names the parcel, or its carrier when the reader gave it no name, and reads its stage then its day.
    func testListWidgetRowNamesTheParcelAndReadsItsStage() {
        let long = "Handmade oak bookshelf with matching side table and drawers"
        var parcel = widgetParcel(label: long, outForDelivery: false)
        XCTAssertEqual(parcel.listName, long)
        XCTAssertEqual(parcel.listLine.status, "Today")
        XCTAssertNil(parcel.listLine.day)

        parcel.stage = DeliveryWidgetStage(status: "In transit", symbol: "shippingbox.fill", tint: ["#22588A", "#9BCBFF"])
        parcel.named = true
        XCTAssertEqual(parcel.listName, long)
        XCTAssertEqual(parcel.listLine.status, "In transit")
        XCTAssertEqual(parcel.listLine.day, "Today")

        parcel.named = false
        XCTAssertEqual(parcel.listName, "Swiss Post")
        // A parcel with no day reads its stage once.
        let undated = DeliveryWidgetParcel(id: UUID(), label: "", carrier: "Swiss Post", trackingNumber: "DEMOLIST0001",
                                           detail: "In transit", isOutForDelivery: false, stage: parcel.stage, named: false)
        XCTAssertEqual(undated.listLine.status, "In transit")
        XCTAssertNil(undated.listLine.day)
    }

    /// A day the app writes to follow a status takes a capital where it opens a line, in each language's own way.
    func testADayOpeningAWidgetLineTakesACapital() {
        XCTAssertEqual("tomorrow".openingLine(in: Locale(identifier: "en")), "Tomorrow")
        XCTAssertEqual("today, 14:00–16:00".openingLine(in: Locale(identifier: "en")), "Today, 14:00–16:00")
        XCTAssertEqual("aujourd’hui".openingLine(in: Locale(identifier: "fr")), "Aujourd’hui")
        XCTAssertEqual("sam 12 sept".openingLine(in: Locale(identifier: "fr")), "Sam 12 sept")
        XCTAssertEqual("завтра".openingLine(in: Locale(identifier: "ru")), "Завтра")
        XCTAssertEqual("明天".openingLine(in: Locale(identifier: "zh-Hans")), "明天")
        XCTAssertEqual("In transit".openingLine(in: Locale(identifier: "en")), "In transit")
        XCTAssertEqual("".openingLine(in: Locale(identifier: "en")), "")
    }

    /// The widget's last line counts the parcels left out in the form each language gives the count.
    func testListWidgetCountsInEachLanguagesForm() {
        let dictionaries = [
            "en": ["widget.listMore.one": "+{{count}} more", "widget.listMore.many": "+{{count}} more"],
            "de": ["widget.listMore.one": "+{{count}} weiteres", "widget.listMore.many": "+{{count}} weitere"],
            "pl": ["widget.listMore.one": "one {{count}}", "widget.listMore.few": "few {{count}}", "widget.listMore.many": "many {{count}}"],
            "ru": ["widget.listMore.one": "one {{count}}", "widget.listMore.few": "few {{count}}", "widget.listMore.many": "many {{count}}"],
        ]
        let text = { (language: String, count: Int) in
            ExtensionLocalizer(languageCode: language, dictionaries: dictionaries).text("widget.listMore.many", count: count)
        }

        XCTAssertEqual(text("en", 12), "+12 more")
        XCTAssertEqual(text("de", 1), "+1 weiteres")
        XCTAssertEqual(text("de", 2), "+2 weitere")
        XCTAssertEqual([1, 3, 5, 12, 22].map { text("pl", $0) }, ["one 1", "few 3", "many 5", "many 12", "few 22"])
        XCTAssertEqual([1, 21, 3, 11].map { text("ru", $0) }, ["one 1", "one 21", "few 3", "many 11"])
        // A language without the copy reads English.
        XCTAssertEqual(text("it", 4), "+4 more")
    }

    /// The widgets' switch is gone: a snapshot kept under it stays, and Live Activities, which followed it until they had
    /// their own choice, take it as theirs once, after which the old key goes.
    func testTheRetiredWidgetSwitchOnlyLeavesLiveActivitiesItsChoice() throws {
        let suiteName = "DeliveryWidgetSharedStoreTests.\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suiteName))
        defer { defaults.removePersistentDomain(forName: suiteName) }
        let store = DeliveryWidgetSharedStore(defaults: defaults)
        let snapshot = DeliveryWidgetSnapshot(
            generatedAt: Date(timeIntervalSince1970: 1_700_000_000),
            languageCode: "fr",
            parcels: [widgetParcel(label: "Private parcel", outForDelivery: true)]
        )
        XCTAssertTrue(store.liveActivitiesEnabled)

        defaults.set(false, forKey: "sdt.deliveryWidget.enabled.v1")
        XCTAssertTrue(store.save(snapshot))
        XCTAssertEqual(store.snapshot, snapshot)
        XCTAssertFalse(store.liveActivitiesEnabled)

        store.setLiveActivitiesEnabled(store.liveActivitiesEnabled)
        XCTAssertNil(defaults.object(forKey: "sdt.deliveryWidget.enabled.v1"))
        XCTAssertFalse(store.liveActivitiesEnabled)
        store.setLiveActivitiesEnabled(true)
        XCTAssertTrue(store.liveActivitiesEnabled)
        XCTAssertEqual(store.snapshot, snapshot)
    }

    func testDeliveryLiveActivityOnlyStartsForDeliveryDay() {
        XCTAssertNil(TrackingStage.pending.deliveryActivityPhase)
        XCTAssertNil(TrackingStage.registered.deliveryActivityPhase)
        XCTAssertNil(TrackingStage.accepted.deliveryActivityPhase)
        XCTAssertNil(TrackingStage.inTransit.deliveryActivityPhase)
        XCTAssertNil(TrackingStage.customs.deliveryActivityPhase)
        XCTAssertEqual(TrackingStage.outForDelivery.deliveryActivityPhase, .outForDelivery)
        XCTAssertEqual(TrackingStage.delivered.deliveryActivityPhase, .delivered)
        XCTAssertEqual(TrackingStage.failedAttempt.deliveryActivityPhase, .failedAttempt)
        XCTAssertEqual(TrackingStage.readyForPickup.deliveryActivityPhase, .readyForPickup)
        XCTAssertEqual(TrackingStage.returned.deliveryActivityPhase, .returned)
        // A reported problem ends a running activity, like a failed attempt.
        XCTAssertEqual(TrackingStage.exception.deliveryActivityPhase, .exception)
    }

    func testLiveActivityPayloadUsesParcelAsStableIdentity() throws {
        let parcelID = UUID()
        let attributes = DeliveryActivityAttributes(parcelID: parcelID)
        let state = DeliveryActivityAttributes.ContentState(
            parcel: DeliveryActivityParcel(
                id: parcelID,
                label: "Running shoes",
                carrier: "Swiss Post",
                status: "Out for delivery",
                detail: "Today, 14:00–16:00",
                phase: .outForDelivery
            ),
            languageCode: "en"
        )
        let attributesJSON = try XCTUnwrap(
            JSONSerialization.jsonObject(with: JSONEncoder().encode(attributes)) as? [String: Any]
        )
        let stateJSON = try XCTUnwrap(
            JSONSerialization.jsonObject(with: JSONEncoder().encode(state)) as? [String: Any]
        )
        let parcelJSON = try XCTUnwrap(stateJSON["parcel"] as? [String: Any])

        XCTAssertEqual(attributesJSON["parcelID"] as? String, parcelID.uuidString)
        XCTAssertEqual(parcelJSON["id"] as? String, parcelID.uuidString)
        XCTAssertEqual(parcelJSON["phase"] as? String, "out_for_delivery")
        XCTAssertEqual(stateJSON["languageCode"] as? String, "en")
        XCTAssertNil(parcelJSON["trackingNumber"])
    }

    func testPendingEventDoesNotOverrideCarrierProgress() {
        let id = UUID()
        let parcel = makeParcel(
            id: id,
            events: [
                event(id, .inTransit, "2026-08-08T10:00:00Z"),
                event(id, .pending, "2026-08-09T10:00:00Z"),
            ]
        )
        XCTAssertEqual(parcel.currentStage, .inTransit)
        XCTAssertEqual(parcel.currentEvent?.stage, .inTransit)
    }

    func testAnnouncementDoesNotStepAMovingParcelBack() {
        let id = UUID()
        let transit = event(id, .inTransit, "2026-08-08T10:00:00Z")
        let accepted = event(id, .accepted, "2026-08-09T10:00:00Z")
        let notice = event(id, .registered, "2026-08-10T10:00:00Z")
        XCTAssertEqual(makeParcel(id: id, events: [notice, accepted, transit]).currentEvent?.id, transit.id)
        XCTAssertEqual(makeParcel(id: id, events: [notice, accepted]).currentEvent?.id, accepted.id)
        XCTAssertEqual(makeParcel(id: id, events: [notice]).currentEvent?.id, notice.id)
        for stage in [TrackingStage.exception, .returned, .delivered] {
            let before = event(id, stage, "2026-08-09T12:00:00Z")
            XCTAssertEqual(makeParcel(id: id, events: [transit, before, notice]).currentEvent?.id, notice.id)
        }
    }

    func testReturnHoldsWhileTheLaterScansCarryTheParcelBack() {
        let id = UUID()
        let failed = event(id, .failedAttempt, "2026-08-04T10:00:00Z")
        let returned = event(id, .returned, "2026-08-05T10:00:00Z")
        let hub = event(id, .inTransit, "2026-08-06T10:00:00Z")
        let border = event(id, .customs, "2026-08-07T10:00:00Z")
        XCTAssertEqual(makeParcel(id: id, events: [failed, returned, hub]).currentEvent?.id, returned.id)
        XCTAssertEqual(makeParcel(id: id, events: [failed, returned, hub, border]).currentEvent?.id, returned.id)
        let notice = event(id, .accepted, "2026-08-08T10:00:00Z")
        XCTAssertEqual(makeParcel(id: id, events: [failed, returned, hub, notice]).currentEvent?.id, returned.id)
        for stage in [TrackingStage.outForDelivery, .failedAttempt, .exception] {
            let round = event(id, stage, "2026-08-08T10:00:00Z")
            XCTAssertEqual(makeParcel(id: id, events: [failed, returned, hub, round]).currentEvent?.id, round.id)
            let after = event(id, .inTransit, "2026-08-09T10:00:00Z")
            XCTAssertEqual(makeParcel(id: id, events: [failed, returned, hub, round, after]).currentEvent?.id, after.id)
        }
        let label = event(id, .registered, "2026-08-08T10:00:00Z")
        let moved = event(id, .inTransit, "2026-08-09T10:00:00Z")
        XCTAssertEqual(makeParcel(id: id, events: [returned, hub, label, moved]).currentEvent?.id, moved.id)
    }

    func testEventOrderingUsesInstantsAndStableTieBreakers() {
        let id = UUID()
        let accepted = event(id, .accepted, "2026-08-08T10:00:00Z")
        var transit = event(id, .inTransit, "2026-08-08T12:00:00.000+02:00")
        transit.id = UUID(uuidString: "00000000-0000-0000-0000-000000000001")!
        var tied = transit
        tied.id = UUID(uuidString: "00000000-0000-0000-0000-000000000002")!
        let older = event(id, .registered, "2026-08-08T09:59:59.999Z")
        let invalid = event(id, .delivered, "invalid")
        let pending = event(id, .pending, "2026-08-09T10:00:00Z")
        let events = [older, tied, invalid, pending, accepted, transit]
        let expected = [pending, tied, transit, accepted, older, invalid].map(\.id)
        for offset in events.indices {
            let reordered = Array(events[offset...] + events[..<offset])
            let parcel = makeParcel(id: id, events: reordered)
            XCTAssertEqual(parcel.sortedEvents.map(\.id), expected)
            XCTAssertEqual(parcel.currentEvent?.id, tied.id)
        }
    }

    func testCurrentEventHandlesEmptyPendingAndUpdatedHistories() {
        let id = UUID()
        var parcel = makeParcel(id: id)
        XCTAssertNil(parcel.currentEvent)
        let pending = event(id, .pending, "2026-08-09T10:00:00Z")
        parcel.trackingEvents = [pending, event(id, .pending, "2026-08-08T10:00:00Z")]
        XCTAssertEqual(parcel.currentEvent?.id, pending.id)
        let accepted = event(id, .accepted, "2026-08-07T10:00:00Z")
        parcel.trackingEvents.append(accepted)
        XCTAssertEqual(parcel.currentEvent?.id, accepted.id)
        parcel.trackingEvents = [event(id, .delivered, "2026-08-10T10:00:00Z")]
        XCTAssertEqual(parcel.currentStage, .delivered)
    }

    func testCarrierNoticeInNoStageLeavesTheNewestScanInAStageCurrent() {
        // The server stores a scan no rule gives a stage as pending where in transit would step back.
        let id = UUID()
        let notice = event(id, .pending, "2026-08-10T15:08:00Z")
        for stage in [TrackingStage.delivered, .exception, .outForDelivery, .readyForPickup] {
            let scan = event(id, stage, "2026-08-10T14:59:00Z")
            let parcel = makeParcel(id: id, events: [scan, notice])
            // The notice stays in the timeline, newest first.
            XCTAssertEqual(parcel.sortedEvents.first?.id, notice.id)
            XCTAssertEqual(parcel.currentEvent?.id, scan.id)
            XCTAssertEqual(parcel.currentStage, stage)
        }
    }

    func testRepeatedTimestampParsingPreservesFormatsAndInvalidValues() throws {
        let expected = try XCTUnwrap(DateParser.date("2026-08-08T10:00:00Z"))
        for _ in 0..<3 {
            XCTAssertEqual(DateParser.date("2026-08-08T12:00:00.000+02:00"), expected)
            XCTAssertEqual(DateParser.date("2026-08-08T10:00:00.123456Z")!.timeIntervalSince(expected), 0.123456, accuracy: 0.001)
            XCTAssertEqual(DateParser.date("2026-08-08T10:00:00Z"), expected)
            XCTAssertNil(DateParser.date("invalid"))
            XCTAssertNil(DateParser.date(""))
        }
    }

    func testParcelStatusLookupPerformance() {
        let parcels = (0..<30).map { index in
            let id = UUID()
            return makeParcel(id: id, events: (0..<20).map { day in
                event(id, .inTransit, String(format: "2026-08-%02dT%02d:00:00.000Z", day + 1, index % 24))
            })
        }
        measure {
            var count = 0
            // List grouping and card rendering repeatedly inspect each parcel's status.
            for parcel in parcels {
                for _ in 0..<20 {
                    if parcel.currentStage == .inTransit { count += 1 }
                }
            }
            XCTAssertEqual(count, 600)
        }
    }

    func testAttentionRulesMatchWebApp() {
        let now = DateParser.date("2026-08-09T12:00:00Z")!
        let id = UUID()
        let stalled = makeParcel(
            id: id,
            events: [event(id, .inTransit, "2026-08-04T10:00:00Z")]
        )
        XCTAssertEqual(stalled.attention(now: now), .stalled)

        var failed = stalled
        failed.syncStatus = .error
        XCTAssertEqual(failed.attention(now: now), .syncError)
        XCTAssertEqual(failed.notice(now: now), .syncError)
        XCTAssertTrue(failed.updateFailed)
        // A number only the owner can correct still sorts first, but its card does not say the update failed.
        for code in ["carrier:refused_number", "carrier:placeholder_number", "carrier:number_unknown"] {
            var refused = failed
            refused.syncError = code
            XCTAssertEqual(refused.attention(now: now), .syncError)
            XCTAssertNil(refused.notice(now: now))
            XCTAssertFalse(refused.updateFailed)
        }

        // A generated track, however old and whatever the latest check (parcelPriority.test.ts).
        let trackID = UUID()
        let moves = (0..<4).map { step in
            TrackingEvent(id: UUID(), packageID: trackID, stage: .inTransit, description: "In transit", location: "Place \(step)",
                          occurredAt: ISO8601DateFormatter().string(from: DateParser.date("2026-07-20T09:00:00Z")!
                              .addingTimeInterval(Double(step) * 86_111)))
        }
        var generated = makeParcel(id: trackID, events: moves)
        XCTAssertEqual(generated.attention(now: now), .generated)
        generated.syncStatus = .error
        XCTAssertEqual(generated.attention(now: now), .generated)
        // Two moves at the interval are not yet a track.
        XCTAssertEqual(makeParcel(id: trackID, events: Array(moves.prefix(3))).attention(now: now), .stalled)

        let announcedID = UUID()
        let announced = makeParcel(
            id: announcedID,
            events: [event(announcedID, .pending, "2026-08-01T08:00:00Z")]
        )
        XCTAssertNil(announced.attention(now: now))

        var oldUnannounced = announced
        oldUnannounced.syncStatus = .waiting
        XCTAssertEqual(oldUnannounced.displayStatus.key, "status.waitingFor")
        XCTAssertEqual(oldUnannounced.attention(now: now), .notAnnounced)

        var recentUnannounced = oldUnannounced
        recentUnannounced.createdAt = "2026-08-08T12:01:00Z"
        XCTAssertNil(recentUnannounced.attention(now: now))
    }

    /// As parcelStatus.test.ts: a parcel with no scans names the carrier it waits for, which has not listed the number yet
    /// or could not be reached, without a failed update.
    @MainActor
    func testNamesTheCarrierAParcelWithNoScansWaitsFor() {
        let now = DateParser.date("2026-08-09T12:00:00Z")!
        let localizer = Localizer()
        let originalLanguage = localizer.language
        defer { localizer.language = originalLanguage }
        localizer.language = .en
        let id = UUID()
        let added = makeParcel(id: id, events: [event(id, .pending, "2026-08-09T08:00:00Z")])
        func with(_ status: SyncStatus, _ error: String? = nil) -> Parcel {
            var parcel = added
            parcel.syncStatus = status
            parcel.syncError = error
            return parcel
        }
        for unreached in [with(.waiting), with(.error), with(.error, "carrier:transport")] {
            XCTAssertEqual(unreached.awaitedCarrier, .ups)
            XCTAssertEqual(unreached.displayStatus.key, "status.waitingFor")
            XCTAssertEqual(unreached.displayStatus.tone, .normal)
            XCTAssertEqual(localizer.parcelStatus(unreached), "Waiting for UPS")
            // Not a missed update.
            if unreached.syncStatus == .error { XCTAssertNil(unreached.attention(now: now)) }
            XCTAssertFalse(unreached.updateFailed)
        }
        // Every language names the carrier.
        for language in AppLanguage.allCases {
            localizer.language = language
            XCTAssertTrue(localizer.parcelStatus(with(.error, "carrier:transport")).contains("UPS"), "\(language)")
        }
        localizer.language = .en

        // What only its owner can fix, or a postal service not named yet, keeps its words.
        let input = with(.error, "carrier:input_required")
        XCTAssertNil(input.awaitedCarrier)
        XCTAssertEqual(input.displayStatus.key, "status.failed")
        XCTAssertEqual(input.attention(now: now), .syncError)
        XCTAssertTrue(input.updateFailed)
        var posted = with(.waiting)
        posted.carrier = .internationalPost
        XCTAssertNil(posted.awaitedCarrier)
        XCTAssertEqual(posted.displayStatus.key, "status.unannounced")
        // Its first scan says more.
        var scanned = with(.error, "carrier:transport")
        scanned.trackingEvents.append(event(id, .inTransit, "2026-08-09T10:00:00Z"))
        XCTAssertNil(scanned.awaitedCarrier)
        XCTAssertEqual(scanned.displayStatus.key, "stage.in_transit")
        XCTAssertEqual(scanned.attention(now: now), .syncError)
    }

    /// Two days on, a carrier that still doesn't know its number asks its owner to check it, choose another carrier or
    /// delete the parcel: no failed update, and no carrier waited for.
    @MainActor
    func testANumberItsCarrierHasNotKnownForTwoDaysIsOneToCheck() {
        let now = DateParser.date("2026-08-09T12:00:00Z")!
        let localizer = Localizer()
        let originalLanguage = localizer.language
        defer { localizer.language = originalLanguage }
        localizer.language = .en
        let id = UUID()
        var parcel = makeParcel(id: id, events: [event(id, .pending, "2026-08-07T08:00:00Z")])
        parcel.syncStatus = .error
        parcel.syncError = Parcel.numberUnknown
        XCTAssertEqual(Parcel.numberUnknown, "carrier:number_unknown")
        XCTAssertTrue(parcel.numberToCheck)
        XCTAssertNil(parcel.awaitedCarrier)
        XCTAssertEqual(parcel.displayStatus.key, "status.checkNumber")
        XCTAssertEqual(parcel.displayStatus.tone, .warning)
        XCTAssertEqual(localizer.parcelStatus(parcel), "Check the tracking number")
        XCTAssertNil(parcel.notice(now: now))
        XCTAssertFalse(parcel.updateFailed)
        XCTAssertEqual(localizer.trackingFailureMessage(Parcel.numberUnknown),
                       "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.")
    }

    func testAwaitingHandoverMatchesWebApp() {
        let now = DateParser.date("2026-08-09T12:00:00Z")!
        let id = UUID()
        let labelled = makeParcel(id: id, events: [
            event(id, .pending, "2026-08-07T08:00:00Z"), event(id, .registered, "2026-08-08T12:00:00Z")
        ])
        XCTAssertTrue(labelled.awaitingHandover(now: now))
        // A label reprinted since: the first still counts.
        let reprinted = makeParcel(id: id, events: [event(id, .registered, "2026-08-07T12:00:00Z"), event(id, .registered, "2026-08-09T11:00:00Z")])
        XCTAssertTrue(reprinted.awaitingHandover(now: now))

        XCTAssertFalse(makeParcel(id: id, events: [event(id, .registered, "2026-08-08T12:00:01Z")]).awaitingHandover(now: now))
        XCTAssertFalse(makeParcel(id: id, events: [
            event(id, .registered, "2026-08-05T12:00:00Z"), event(id, .accepted, "2026-08-07T12:00:00Z")
        ]).awaitingHandover(now: now))
        XCTAssertFalse(makeParcel(id: id, events: [event(id, .pending, "2026-08-05T12:00:00Z")]).awaitingHandover(now: now))
        XCTAssertFalse(makeParcel(id: id).awaitingHandover(now: now))
    }

    @MainActor func testClockPreferencePersistsAndFormatsDeliveryWindows() throws {
        let suite = "clock-preference-\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        let sharedSuite = "\(suite).shared"
        let shared = try XCTUnwrap(UserDefaults(suiteName: sharedSuite))
        defer {
            defaults.removePersistentDomain(forName: suite)
            shared.removePersistentDomain(forName: sharedSuite)
        }
        let localizer = Localizer(defaults: defaults, sharedClockDefaults: shared, systemUses24HourTime: { true })
        let midnight = Date(timeIntervalSince1970: 0)
        let noon = midnight.addingTimeInterval(12 * 3_600)
        let day = "2026-11-12"
        let originalDay = localizer.expectedDelivery(day)
        let window = "\(day) 11:30–15:30"
        let originalWindow = localizer.expectedDelivery(window)

        // Nothing chosen is Automatic, which the App Group holds for the notification service extension.
        XCTAssertEqual(localizer.clock, .automatic)
        XCTAssertTrue(localizer.use24HourTime)
        XCTAssertEqual(shared.string(forKey: DeviceClock.preferenceKey), "auto")
        XCTAssertEqual(localizer.clockTime(midnight, in: .gmt), "00:00")
        XCTAssertTrue(originalWindow.hasSuffix("11:30–15:30"))

        localizer.clock = .twelveHour
        XCTAssertFalse(localizer.use24HourTime)
        XCTAssertEqual(defaults.string(forKey: DeviceClock.preferenceKey), "12")
        XCTAssertEqual(shared.string(forKey: DeviceClock.preferenceKey), "12")
        shared.removeObject(forKey: DeviceClock.preferenceKey)
        let reopened = Localizer(defaults: defaults, sharedClockDefaults: shared, systemUses24HourTime: { true })
        XCTAssertEqual(shared.string(forKey: DeviceClock.preferenceKey), "12")
        XCTAssertEqual(reopened.clock, .twelveHour)
        XCTAssertFalse(reopened.use24HourTime)
        XCTAssertEqual(reopened.clockTime(midnight, in: .gmt), "12:00 AM")
        XCTAssertEqual(reopened.clockTime(noon, in: .gmt), "12:00 PM")
        XCTAssertFalse(reopened.expectedDelivery(window).contains("15:30"))
        XCTAssertTrue(reopened.expectedDelivery("\(day) 23:00–24:00").hasSuffix("–12:00 AM"))
        XCTAssertEqual(reopened.expectedDelivery(day), originalDay)

        reopened.clock = .twentyFourHour
        XCTAssertEqual(defaults.string(forKey: DeviceClock.preferenceKey), "24")
        XCTAssertEqual(shared.string(forKey: DeviceClock.preferenceKey), "24")
        XCTAssertEqual(reopened.clockTime(noon, in: .gmt), "12:00")
    }

    @MainActor func testAutomaticClockAndDistanceFollowTheSystem() throws {
        let suite = "system-units-\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        defer { defaults.removePersistentDomain(forName: suite) }
        var system24 = false
        var systemUnit = DistanceUnit.miles
        let localizer = Localizer(
            defaults: defaults, systemUses24HourTime: { system24 }, systemDistanceUnit: { systemUnit }
        )

        // Automatic follows a 12-hour, imperial system, then a 24-hour, metric one once the system changes.
        XCTAssertEqual(localizer.clock, .automatic)
        XCTAssertEqual(localizer.distance, .automatic)
        XCTAssertFalse(localizer.use24HourTime)
        XCTAssertEqual(localizer.distanceUnit, .miles)
        system24 = true
        systemUnit = .kilometres
        localizer.systemChanged()
        XCTAssertTrue(localizer.systemUses24HourTime)
        XCTAssertTrue(localizer.use24HourTime)
        XCTAssertEqual(localizer.distanceUnit, .kilometres)

        // A choice beats the system, both ways.
        localizer.clock = .twelveHour
        localizer.distance = .miles
        XCTAssertFalse(localizer.use24HourTime)
        XCTAssertEqual(localizer.distanceUnit, .miles)
        system24 = false
        systemUnit = .miles
        localizer.systemChanged()
        localizer.clock = .twentyFourHour
        localizer.distance = .kilometres
        XCTAssertTrue(localizer.use24HourTime)
        XCTAssertEqual(localizer.distanceUnit, .kilometres)
        XCTAssertFalse(localizer.systemUses24HourTime)
        XCTAssertEqual(localizer.systemDistanceUnit, .miles)

        // The distance persists as auto, km or mi; a missing or unknown one reads as Automatic.
        XCTAssertEqual(defaults.string(forKey: "sdt.native.distanceUnit"), "km")
        localizer.distance = .automatic
        XCTAssertEqual(defaults.string(forKey: "sdt.native.distanceUnit"), "auto")
        XCTAssertEqual(localizer.distanceUnit, .miles)
        localizer.distance = .miles
        XCTAssertEqual(Localizer(defaults: defaults, systemDistanceUnit: { .kilometres }).distanceUnit, .miles)
        defaults.set("furlongs", forKey: "sdt.native.distanceUnit")
        XCTAssertEqual(Localizer(defaults: defaults, systemDistanceUnit: { .kilometres }).distance, .automatic)
        defaults.removeObject(forKey: "sdt.native.distanceUnit")
        defaults.removeObject(forKey: DeviceClock.preferenceKey)
        let fresh = Localizer(defaults: defaults, systemUses24HourTime: { false }, systemDistanceUnit: { .kilometres })
        XCTAssertEqual(fresh.clock, .automatic)
        XCTAssertFalse(fresh.use24HourTime)
        XCTAssertEqual(fresh.distanceUnit, .kilometres)

        // The system's unit: miles in the US and the UK, kilometres elsewhere.
        XCTAssertEqual(DistanceUnit.system(Locale(identifier: "en_US")), .miles)
        XCTAssertEqual(DistanceUnit.system(Locale(identifier: "en_GB")), .miles)
        XCTAssertEqual(DistanceUnit.system(Locale(identifier: "fr_FR")), .kilometres)
    }

    @MainActor func testWeightsFollowTheUSMeasurementSystemOrTheWeightChosen() throws {
        let suite = "system-weights-\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        defer { defaults.removePersistentDomain(forName: suite) }
        var systemWeight = WeightUnit.pounds
        let localizer = Localizer(defaults: defaults, systemDistanceUnit: { .miles }, systemWeightUnit: { systemWeight })
        let originalLanguage = localizer.language
        defer { localizer.language = originalLanguage }
        localizer.language = .en

        // Automatic weighs in the system's unit, as it changes; a weight chosen beats it, whatever the distance units.
        XCTAssertEqual(localizer.weight, .automatic)
        XCTAssertEqual(localizer.weight(kilograms: 1.2), "2.6 lb")
        systemWeight = .kilograms
        localizer.systemChanged()
        XCTAssertEqual(localizer.weight(kilograms: 1.2), "1.2 kg")
        localizer.distance = .miles
        XCTAssertEqual(localizer.weightUnit, .kilograms)
        localizer.weight = .pounds
        XCTAssertEqual(localizer.weightUnit, .pounds)
        XCTAssertEqual(localizer.dimensions("40 × 30 × 20 cm"), "15.7 × 11.8 × 7.9 in")
        localizer.distance = .kilometres
        XCTAssertEqual(localizer.weightUnit, .pounds)
        XCTAssertEqual(defaults.string(forKey: "sdt.native.weightUnit"), "lb")
        localizer.weight = .kilograms
        XCTAssertEqual(localizer.dimensions("40 × 30 × 20 cm"), "40 × 30 × 20 cm")
        localizer.weight = .automatic
        localizer.distance = .automatic
        XCTAssertEqual(defaults.string(forKey: "sdt.native.weightUnit"), "auto")
        XCTAssertEqual(WeightPreference(stored: "stone"), .automatic)

        // A reader who chose distance units before weights had their own keeps the weight they brought, saved once.
        for (distance, weight) in [("mi", WeightPreference.pounds), ("km", .kilograms), ("auto", .automatic)] {
            defaults.removeObject(forKey: "sdt.native.weightUnit")
            defaults.set(distance, forKey: "sdt.native.distanceUnit")
            let carried = Localizer(defaults: defaults, systemWeightUnit: { .kilograms })
            XCTAssertEqual(carried.weight, weight)
            XCTAssertEqual(defaults.string(forKey: "sdt.native.weightUnit"), weight.rawValue)
            carried.distance = .automatic
            XCTAssertEqual(Localizer(defaults: defaults, systemWeightUnit: { .kilograms }).weight, weight)
        }

        // Pounds in the United States and Liberia; kilograms in the UK, Myanmar and elsewhere.
        XCTAssertEqual(WeightUnit.system(Locale(identifier: "en_US")), .pounds)
        XCTAssertEqual(WeightUnit.system(Locale(identifier: "es_US")), .pounds)
        XCTAssertEqual(WeightUnit.system(Locale(identifier: "en_LR")), .pounds)
        XCTAssertEqual(WeightUnit.system(Locale(identifier: "en_GB")), .kilograms)
        XCTAssertEqual(WeightUnit.system(Locale(identifier: "my_MM")), .kilograms)
        XCTAssertEqual(WeightUnit.system(Locale(identifier: "en_MM")), .kilograms)
        XCTAssertEqual(WeightUnit.system(Locale(identifier: "fr_FR")), .kilograms)

        // Kilograms to three decimals, pounds to one, or to three under a tenth of a pound, in the language's digits.
        let english = Locale(identifier: "en"), german = Locale(identifier: "de")
        XCTAssertEqual([1.2, 0.35, 0.0123, 8.6].map { WeightUnit.kilograms.format(kilograms: $0, locale: english) },
                       ["1.2 kg", "0.35 kg", "0.012 kg", "8.6 kg"])
        XCTAssertEqual([1.2, 0.35, 0.02, 0.4536, 8.6, 25].map { WeightUnit.pounds.format(kilograms: $0, locale: english) },
                       ["2.6 lb", "0.8 lb", "0.044 lb", "1 lb", "19 lb", "55.1 lb"])
        XCTAssertEqual(WeightUnit.pounds.format(kilograms: 1.2, locale: german), "2,6 lb")

        // A carrier's sides in centimetres go to inches with pounds, to one decimal; any other text stays as written.
        XCTAssertEqual(WeightUnit.pounds.dimensions("40 × 30 × 20 cm", locale: english), "15.7 × 11.8 × 7.9 in")
        XCTAssertEqual(WeightUnit.pounds.dimensions(" 40 x 30.5 cm ", locale: english), "15.7 × 12 in")
        XCTAssertEqual(WeightUnit.pounds.dimensions("25.4 X 2.54 X 0.5 CM", locale: german), "10 × 1 × 0,2 in")
        XCTAssertEqual(WeightUnit.kilograms.dimensions(" 40 × 30 × 20 cm ", locale: english), "40 × 30 × 20 cm")
        for text in ["12 × 9 × 4 in", "40 × 30 cm × 20", "40 cm", "Large box", "40 × 30 × 20 × 10 cm"] {
            XCTAssertEqual(WeightUnit.pounds.dimensions(text, locale: english), text)
        }
        systemWeight = .pounds
        localizer.systemChanged()
        XCTAssertEqual(localizer.dimensions("30 × 20 × 10 cm"), "11.8 × 7.9 × 3.9 in")
    }

    func testScheduledDeliveryMatchesWebApp() {
        let id = UUID()
        var delivered = makeParcel(id: id, events: [event(id, .delivered, "2026-08-08T12:00:00Z")])
        XCTAssertFalse(delivered.scheduledDelivery)
        delivered.carrier = .gzyl
        XCTAssertTrue(delivered.scheduledDelivery)
        delivered.carrier = .xingheFreight
        XCTAssertTrue(delivered.scheduledDelivery)
        // A carrier it was handed to delivers for real.
        delivered.carrierData = CarrierData(deliveryCarrier: .dpdDe, deliveryTrackingNumber: "01234567890123")
        XCTAssertFalse(delivered.scheduledDelivery)
        // One that lists it only as announced, without a scan of its own, has not taken it: the delivered step is still
        // the schedule's.
        delivered.carrierData?.deliveryAnnounced = true
        XCTAssertTrue(delivered.scheduledDelivery)
        // Delivered, it waits for no partner: the line names who delivers, as on the web.
        XCTAssertEqual(delivered.deliveryLineKey, "parcel.deliveryCarrier")
        var returned = makeParcel(id: id, events: [event(id, .returned, "2026-08-08T12:00:00Z")])
        returned.carrier = .gzyl
        returned.carrierData = delivered.carrierData
        XCTAssertEqual(returned.deliveryLineKey, "parcel.deliveryCarrier")
        // On its way, it is still only announced there.
        var moving = makeParcel(id: id, events: [event(id, .inTransit, "2026-08-08T12:00:00Z")])
        moving.carrier = .gzyl
        moving.carrierData = delivered.carrierData
        XCTAssertEqual(moving.deliveryLineKey, "parcel.deliveryAnnounced")
        XCTAssertFalse(moving.scheduledDelivery)
    }

    func testTheWrittenAheadNoteSaysTheCarriersStepsShowGreyedOnceItNamesThem() {
        var parcel = makeParcel()
        parcel.carrier = .gzyl
        XCTAssertNil(parcel.writtenAheadNoteKey)
        parcel.writtenAheadBy = .gzyl
        XCTAssertEqual(parcel.writtenAheadNoteKey, "parcel.writtenAhead")
        // A "Delivered" the carrier wrote in advance, before any real scan, is no scan: the parcel is not delivered.
        parcel.writtenAheadEvents = [PlannedTrackingEvent(description: "Delivered", stage: .delivered, occurredAt: "2026-08-09T07:00:00Z")]
        XCTAssertEqual(parcel.writtenAheadNoteKey, "parcel.writtenAheadShown")
        XCTAssertNil(parcel.currentEvent)
        XCTAssertNotEqual(parcel.currentStage, .delivered)
        XCTAssertFalse(parcel.scheduledDelivery)
        parcel.writtenAheadEvents = nil
        parcel.plannedEvents = [PlannedTrackingEvent(description: "Delivered", stage: .delivered, occurredAt: "2026-08-20T07:00:00Z")]
        XCTAssertEqual(parcel.writtenAheadNoteKey, "parcel.writtenAheadShown")
    }

    // As src/components/CarrierOutage.test.tsx: the carrier's site down, as the API serves it, until the parcel ends.
    func testACarrierOutageIsShownUntilTheParcelIsDeliveredOrReturned() throws {
        let id = UUID()
        let row = """
        {"id":"\(id.uuidString)","tracking_number":"TESTCP00001","label":"","carrier":"colis-prive","created_at":"2026-11-17T12:00:00Z","notifications_muted":false,
         "sync_status":"error","sync_error":"carrier:transport","carrier_outage":{"carrier":"colis-prive","since":"2026-11-19T12:20:41Z",
         "tracking_url":"https://colisprive.com/moncolis/pages/DetailColis.aspx?numColis=TESTCP00001&lang=fr"}}
        """
        var parcel = try JSONDecoder.deliveryTracker.decode(Parcel.self, from: Data(row.utf8))
        XCTAssertEqual(parcel.shownCarrierOutage, CarrierOutage(carrier: .colisPrive, since: "2026-11-19T12:20:41Z",
            trackingURL: "https://colisprive.com/moncolis/pages/DetailColis.aspx?numColis=TESTCP00001&lang=fr"))
        parcel.trackingEvents = [event(id, .inTransit, "2026-11-18T08:00:00Z")]
        XCTAssertNotNil(parcel.shownCarrierOutage)
        for stage in [TrackingStage.delivered, .returned] {
            var ended = parcel
            ended.trackingEvents.append(event(id, stage, "2026-11-19T10:00:00Z"))
            XCTAssertNil(ended.shownCarrierOutage)
        }
        // None served: none shown, the failed update stays.
        parcel.carrierOutage = nil
        XCTAssertNil(parcel.shownCarrierOutage)
        XCTAssertTrue(parcel.updateFailed)
    }

    // As src/components/TrackingJournal.test.tsx: the line under a written delivery nothing confirms.
    func testAWrittenDeliveryWhoseTimeHasPassedIsUnconfirmedWhileTheScansTellNothingPastTheLabel() {
        let id = UUID()
        let now = DateParser.date("2026-08-20T00:00:00Z")!
        let departed = PlannedTrackingEvent(description: "Departed the origin facility", stage: .inTransit, occurredAt: "2026-08-08T01:00:00Z")
        let delivered = PlannedTrackingEvent(description: "Delivered", stage: .delivered, occurredAt: "2026-08-15T07:00:00Z")
        var parcel = makeParcel(id: id, events: [event(id, .pending, "2026-08-01T10:00:00Z"), event(id, .registered, "2026-08-02T10:00:00Z")])
        parcel.carrier = .gzyl
        parcel.writtenAheadEvents = [departed, delivered]
        // Not without the carrier named as writing ahead.
        XCTAssertNil(parcel.unconfirmedWrittenDelivery(now: now))
        parcel.writtenAheadBy = .gzyl
        XCTAssertEqual(parcel.unconfirmedWrittenDelivery(now: now), delivered)
        // The newest of two written deliveries.
        let later = PlannedTrackingEvent(description: "Delivered", stage: .delivered, occurredAt: "2026-08-16T07:00:00Z")
        parcel.writtenAheadEvents = [later, delivered]
        XCTAssertEqual(parcel.unconfirmedWrittenDelivery(now: now), later)

        // A scan past the label.
        var moving = parcel
        moving.trackingEvents.append(event(id, .inTransit, "2026-08-03T10:00:00Z"))
        XCTAssertNil(moving.unconfirmedWrittenDelivery(now: now))
        // A delivery whose time is still ahead, a day-only one through its day.
        parcel.writtenAheadEvents = [PlannedTrackingEvent(description: "Delivered", stage: .delivered, occurredAt: "2026-08-21T07:00:00Z")]
        XCTAssertNil(parcel.unconfirmedWrittenDelivery(now: now))
        parcel.writtenAheadEvents = [PlannedTrackingEvent(description: "Delivered", stage: .delivered, occurredAt: "2026-08-19T16:00:00Z", day: "2026-08-20")]
        XCTAssertNil(parcel.unconfirmedWrittenDelivery(now: now))
        // No written delivery.
        parcel.writtenAheadEvents = [departed]
        XCTAssertNil(parcel.unconfirmedWrittenDelivery(now: now))
    }

    // As src/lib/stages.test.ts: a problem row an earlier check dated after the return scan.
    func testAReturnTheServerHoldsStaysOverAProblemRowAndTheTripAfterIt() {
        let id = UUID()
        let failed = event(id, .failedAttempt, "2026-06-04T08:00:00Z")
        let returned = event(id, .returned, "2026-06-05T08:00:00Z")
        let problem = event(id, .exception, "2026-06-05T13:00:00Z")
        let hub = event(id, .inTransit, "2026-06-07T08:00:00Z")
        func held(_ events: [TrackingEvent]) -> Parcel {
            var parcel = makeParcel(id: id, events: events)
            parcel.carrierData = CarrierData(returnHeld: true)
            return parcel
        }
        XCTAssertEqual(held([failed, returned, problem]).currentEvent?.id, returned.id)
        XCTAssertEqual(held([failed, returned, problem, hub]).currentEvent?.id, returned.id)
        XCTAssertTrue(held([failed, returned, problem, hub]).heldReturn)
        // Without the hold, the problem row ends the return.
        XCTAssertEqual(makeParcel(id: id, events: [failed, returned, problem, hub]).currentEvent?.id, hub.id)
        // A delivery round after the return is news of its own, held or not.
        let round = event(id, .outForDelivery, "2026-06-08T08:00:00Z")
        XCTAssertEqual(held([failed, returned, problem, hub, round]).currentEvent?.id, round.id)
        // A hold with no return among the rows reads them as they are.
        XCTAssertEqual(held([failed, hub]).currentEvent?.id, hub.id)
    }

    func testHeldReturnMatchesWebApp() {
        let id = UUID()
        // Returned, then carried back: held while the server says so.
        var returned = makeParcel(id: id, events: [event(id, .returned, "2026-08-08T12:00:00Z"), event(id, .inTransit, "2026-08-09T12:00:00Z")])
        XCTAssertEqual(returned.currentStage, .returned)
        XCTAssertFalse(returned.heldReturn)
        returned.carrierData = CarrierData(returnHeld: true)
        XCTAssertTrue(returned.heldReturn)
        // A delivery round after the return ends it, whatever the flag still says.
        var round = makeParcel(id: id, events: [event(id, .returned, "2026-08-08T12:00:00Z"), event(id, .outForDelivery, "2026-08-09T12:00:00Z")])
        round.carrierData = CarrierData(returnHeld: true)
        XCTAssertFalse(round.heldReturn)
    }

    // The list's orders against the web's (src/lib/parcelView.test.ts) and Android's (ParcelSortTests), with their parcels.
    func testTheOrdersAreTheWebsWithoutPriority() {
        XCTAssertEqual(ParcelSort.allCases.map(\.rawValue), ["updated", "newest", "eta", "carrier"])
        XCTAssertEqual(ParcelSort.standard, .eta)
    }

    func testOfTwoDueTheSameDayTheOneThatMovedLastComesFirst() {
        // "alpha" comes first by name, "zulu" moved last; "date" moved last of all but has no expected day.
        let alpha = sortParcel("alpha", moved: "2026-08-02T09:00:00Z", expected: "2026-08-08")
        let zulu = sortParcel("zulu", moved: "2026-08-04T09:00:00Z", expected: "2026-08-08 13:00–15:00")
        let soon = sortParcel("soon", moved: "2026-08-01T09:00:00Z", expected: "2026-08-06")
        let undated = sortParcel("date", moved: "2026-08-05T09:00:00Z", expected: nil)
        let now = ISO8601DateFormatter().date(from: "2026-08-05T12:00:00Z")!

        let listed = ParcelOrganizer.visible([undated, alpha, zulu, soon], query: "", status: .all, carrier: nil, sort: .eta, now: now)
        XCTAssertEqual(listed.map(\.label), ["soon", "zulu", "alpha", "date"])
    }

    func testAParcelWithoutAScanMovedWhenItWasAdded() {
        let scanned = sortParcel("scanned", moved: "2026-08-03T09:00:00Z", expected: "2026-08-08")
        var unscanned = sortParcel("unscanned", moved: nil, expected: "2026-08-08")
        unscanned.createdAt = "2026-08-04T09:00:00Z"

        XCTAssertEqual(ParcelOrganizer.sorted([scanned, unscanned], by: .eta).map(\.label), ["unscanned", "scanned"])
    }

    func testTheSameDayAndTheSameMoveFallBackToTheName() {
        let bravo = sortParcel("bravo", moved: "2026-08-03T09:00:00Z", expected: "2026-08-08")
        let alpha = sortParcel("alpha", moved: "2026-08-03T09:00:00Z", expected: "2026-08-08")

        XCTAssertEqual(ParcelOrganizer.sorted([bravo, alpha], by: .eta).map(\.label), ["alpha", "bravo"])
    }

    private func sortParcel(_ name: String, moved: String?, expected: String?) -> Parcel {
        let id = UUID()
        var parcel = makeParcel(id: id, events: moved.map { [event(id, .inTransit, $0)] } ?? [])
        parcel.label = name
        parcel.expectedDelivery = expected
        return parcel
    }

    func testFiltersSearchCompactTrackingNumbers() {
        let parcel = makeParcel(trackingNumber: "99.34.123456.99999980")
        let result = ParcelOrganizer.visible(
            [parcel],
            query: "9934 123456",
            status: .all,
            carrier: nil,
            sort: .standard
        )
        XCTAssertEqual(result.map(\.id), [parcel.id])
    }

    func testArchivedParcelsAreNotActive() {
        var parcel = makeParcel()
        parcel.archivedAt = "2026-08-09T12:00:00Z"
        XCTAssertFalse(parcel.isActive)
        XCTAssertTrue(ParcelOrganizer.sections(from: [parcel]).contains { $0.kind == .archived })
    }

    func testPastDeliveriesSortByCompletionInsteadOfETAOrCreationOrder() {
        let olderID = UUID(), newerID = UUID()
        var older = makeParcel(id: olderID, events: [event(olderID, .delivered, "2026-09-06T12:00:00Z")])
        older.createdAt = "2026-09-07T12:00:00Z"
        older.expectedDelivery = "2026-09-12"
        var newer = makeParcel(id: newerID, events: [event(newerID, .delivered, "2026-09-07T12:00:00Z")])
        newer.createdAt = "2026-09-01T12:00:00Z"
        newer.expectedDelivery = "2026-09-14"
        let past = ParcelOrganizer.sections(from: [older, newer]).first { $0.kind == .delivered }
        XCTAssertEqual(past?.parcels.map(\.id), [newerID, olderID])
    }

    func testArchivedParcelsAreOrderedByNewestDisplayedDate() {
        let olderID = UUID()
        var older = makeParcel(
            id: olderID,
            events: [event(olderID, .delivered, "2026-08-05T12:00:00Z")]
        )
        older.archivedAt = "2026-08-10T12:00:00Z"

        let newerID = UUID()
        var newer = makeParcel(
            id: newerID,
            events: [event(newerID, .delivered, "2026-08-07T12:00:00Z")]
        )
        newer.archivedAt = "2026-08-09T12:00:00Z"

        let archived = ParcelOrganizer.sections(from: [older, newer])
            .first(where: { $0.kind == .archived })

        XCTAssertEqual(archived?.parcels.map(\.id), [newerID, olderID])
    }

    @MainActor
    func testEstimatesKeepOnlyTodaysHoursWhileOutForDeliveryAndHideObsoleteDates() {
        let id = UUID()
        let now = DateParser.date("2026-09-07T12:00:00Z")!
        let localizer = Localizer(systemUses24HourTime: { true })
        localizer.language = .fr
        for stage in [TrackingStage.delivered, .returned, .failedAttempt, .readyForPickup, .exception] {
            var parcel = makeParcel(id: id, events: [event(id, stage, "2026-09-07T10:00:00Z")])
            parcel.expectedDelivery = "2026-09-07"
            XCTAssertNil(localizer.parcelDeliveryEstimate(parcel, now: now))
        }
        var parcel = makeParcel(id: id, events: [event(id, .outForDelivery, "2026-09-07T10:00:00Z")])
        // Out for delivery says today already: a day, or the last day of a window, would only repeat or contradict it.
        let contradicting: [(String?, String)] = [
            (nil, "2026-09-07"), (nil, "2026-09-08"), (nil, "2026-09-08 09:00–12:00"), ("2026-09-06", "2026-09-08"),
        ]
        for (from, to) in contradicting {
            parcel.carrierData = from.map { CarrierData(expectedDeliveryFrom: $0) }
            parcel.expectedDelivery = to
            XCTAssertNil(localizer.parcelDeliveryEstimate(parcel, now: now), to)
        }
        parcel.carrierData = nil
        parcel.expectedDelivery = "2026-09-07 14:00–16:00"
        XCTAssertEqual(localizer.parcelDeliveryEstimate(parcel, now: now), "aujourd’hui, 14:00–16:00")
        parcel.expectedDelivery = "2026-09-07T12:30:00Z"
        let formatter = DateFormatter()
        formatter.dateFormat = "HH:mm"
        let time = formatter.string(from: DateParser.date(parcel.expectedDelivery!)!)
        XCTAssertEqual(localizer.parcelDeliveryEstimate(parcel, now: now), "aujourd’hui, \(time)")
        parcel.expectedDelivery = "2026-09-06"
        XCTAssertNil(localizer.parcelDeliveryEstimate(parcel, now: now))
    }

    @MainActor
    func testCardsGiveAWindowOfDaysThatHasBegunByItsLastDayAndThePageWhole() {
        let id = UUID()
        let now = DateParser.date("2026-09-07T12:00:00Z")!
        let localizer = Localizer()
        localizer.language = .en
        var parcel = makeParcel(id: id, events: [event(id, .inTransit, "2026-09-07T10:00:00Z")])
        parcel.expectedDelivery = "2026-09-09"
        parcel.carrierData = CarrierData(expectedDeliveryFrom: "2026-09-07")
        XCTAssertEqual(localizer.parcelDeliveryEstimate(parcel, now: now), "by Wed 9 Sep")
        XCTAssertEqual(localizer.parcelDeliveryWindow(parcel, now: now), "today – Wed 9 Sep")
        parcel.carrierData = CarrierData(expectedDeliveryFrom: "2026-09-08")
        XCTAssertEqual(localizer.parcelDeliveryEstimate(parcel, now: now), "tomorrow – Wed 9 Sep")
        parcel.expectedDelivery = "2026-09-06"
        XCTAssertNil(localizer.parcelDeliveryEstimate(parcel, now: now))
        XCTAssertNil(localizer.parcelDeliveryWindow(parcel, now: now))
    }

    @MainActor
    func testCompletionDatesUseRelativeLabelsForFinalEvents() throws {
        let localizer = Localizer()
        localizer.language = .en
        let now = try XCTUnwrap(DateParser.deliveryDate("2026-09-09"))
        let id = UUID()
        for stage in [TrackingStage.delivered, .returned] {
            for (value, expected) in [("2026-09-08", "yesterday"), ("2026-09-09", "today"), ("2026-09-10", "tomorrow"), ("2026-09-12", "Sat 12 Sep")] {
                let day = try XCTUnwrap(DateParser.deliveryDate(value))
                let late = try XCTUnwrap(Calendar.current.date(bySettingHour: 23, minute: 55, second: 0, of: day))
                let parcel = makeParcel(id: id, events: [event(id, stage, DateParser.isoString(late))])
                XCTAssertEqual(localizer.parcelCompletionDate(parcel, now: now), expected)
            }
        }
        XCTAssertNil(localizer.parcelCompletionDate(makeParcel(id: id, events: [event(id, .inTransit, DateParser.isoString(now))]), now: now))
        XCTAssertNil(localizer.parcelCompletionDate(makeParcel(id: id, events: [event(id, .delivered, "invalid")]), now: now))
    }

    /// The app last opened late on Thursday: on Friday the widgets and Siri say "today" for the parcel expected then and
    /// "yesterday" for Thursday evening's delivery, and on Saturday the passed estimate gives way to the status.
    @MainActor
    func testTheWidgetsDaysFollowTheClockWhileTheAppStaysClosed() throws {
        let localizer = Localizer()
        localizer.language = .en
        let calendar = Calendar.current
        let thursday = try XCTUnwrap(DateParser.deliveryDate("2026-11-12"))
        let lateThursday = try XCTUnwrap(calendar.date(bySettingHour: 23, minute: 30, second: 0, of: thursday))
        let friday = try XCTUnwrap(calendar.date(byAdding: .day, value: 1, to: thursday))
        let saturday = try XCTUnwrap(calendar.date(byAdding: .day, value: 2, to: thursday))
        let coming = UUID(), arrived = UUID()
        var expected = makeParcel(id: coming, events: [event(coming, .inTransit, DateParser.isoString(thursday.addingTimeInterval(8 * 3_600)))])
        expected.expectedDelivery = "2026-11-13"
        let delivered = makeParcel(id: arrived, events: [event(arrived, .delivered, DateParser.isoString(thursday.addingTimeInterval(19.9 * 3_600)))])

        let published = ParcelStore.widgetDeliveries(of: [expected, delivered], localizer: localizer, now: lateThursday)
        XCTAssertEqual(published.map(\.detail), ["tomorrow", "today"])
        // On Tuesday the parcel last scanned on Thursday morning has gone four days without a scan, and needs the reader.
        let tuesday = try XCTUnwrap(calendar.date(byAdding: .day, value: 5, to: thursday))
        XCTAssertEqual(DeliveryWidgetDays.changes(in: published, after: lateThursday), [friday, saturday, tuesday])
        XCTAssertEqual(published.first?.worded(at: tuesday).section, "attention")
        XCTAssertEqual(DeliveryWidgetSelection.listed(published, now: lateThursday).map(\.detail), ["tomorrow", "today"])

        let fridayMorning = friday.addingTimeInterval(8 * 3_600)
        XCTAssertEqual(DeliveryWidgetSelection.listed(published, now: fridayMorning).map(\.detail), ["today", "yesterday"])
        let snapshot = DeliveryWidgetSnapshot(generatedAt: lateThursday, languageCode: "en", parcels: published)
        XCTAssertTrue(NextDeliveryAnswer.text(snapshot: snapshot, copy: ExtensionLocalizer(languageCode: "en"), now: fridayMorning)
            .hasSuffix(": today."))

        // A day later the estimate has passed, and the delivery has left the widgets.
        XCTAssertEqual(DeliveryWidgetSelection.listed(published, now: saturday.addingTimeInterval(8 * 3_600)).map(\.detail), ["In transit"])
    }

    /// The app publishes where its list puts each parcel, which one is its Next up, and the carrier shown, and the last
    /// steps of the first parcels, latest first, for a large widget's card.
    @MainActor
    func testTheStorePublishesEachParcelsSectionNextUpAndLastSteps() throws {
        let localizer = Localizer(systemUses24HourTime: { true })
        localizer.language = .en
        let calendar = Calendar.current
        let thursday = try XCTUnwrap(DateParser.deliveryDate("2026-11-12"))
        let now = try XCTUnwrap(calendar.date(bySettingHour: 12, minute: 0, second: 0, of: thursday))
        let hoursAgo = { (hours: Double) in DateParser.isoString(now.addingTimeInterval(-hours * 3_600)) }
        let courierID = UUID(), busyID = UUID(), arrivedID = UUID()
        var courier = makeParcel(id: courierID, events: [event(courierID, .inTransit, hoursAgo(26)), event(courierID, .outForDelivery, hoursAgo(2))])
        courier.expectedDelivery = "2026-11-12"
        let busy = makeParcel(id: busyID, events: (1...6).map { hour in
            TrackingEvent(id: UUID(), packageID: busyID, stage: .inTransit, description: "Step \(hour)", occurredAt: hoursAgo(Double(hour)))
        }.shuffled())
        // The first of the others is held by the carrier, and needs the reader.
        let others = (0..<8).map { index in
            let id = UUID()
            return makeParcel(id: id, events: [event(id, index == 0 ? .exception : .inTransit, hoursAgo(10 + Double(index)))])
        }
        let quiet = makeParcel(events: [])
        let arrived = makeParcel(id: arrivedID, events: [event(arrivedID, .delivered, hoursAgo(3))])

        let published = ParcelStore.widgetDeliveries(of: [quiet, arrived, busy, courier] + others, localizer: localizer, now: now)
        XCTAssertEqual(published.count, 12)
        XCTAssertEqual(published.map(\.carrierId), Array(repeating: "ups", count: 12))
        XCTAssertEqual(published.filter { $0.nextUp == true }.map(\.id), [courierID])
        XCTAssertTrue(published.allSatisfy { $0.nextUp != nil && $0.section != nil })
        let section = { (id: UUID) in published.first { $0.id == id }?.section }
        XCTAssertEqual([section(courierID), section(busyID), section(others[0].id), section(quiet.id), section(arrivedID)],
                       ["today", "active", "attention", "active", "delivered"])

        let steps = try XCTUnwrap(published.first { $0.id == busyID }?.steps)
        XCTAssertEqual(steps.map(\.text), ["Step 1", "Step 2", "Step 3", "Step 4"])
        XCTAssertEqual(steps.map(\.clock), ["11:00", "10:00", "09:00", "08:00"])
        XCTAssertEqual(steps.first?.time(now: now, locale: Locale(identifier: "en")), "11:00")
        // Only the first parcels carry steps; a parcel without events has none.
        XCTAssertTrue(published.prefix(DeliveryWidgetSelection.limit).allSatisfy { $0.steps != nil })
        XCTAssertEqual(published.dropFirst(DeliveryWidgetSelection.limit).map(\.steps), [nil, nil, nil, nil])
        XCTAssertNil(ParcelStore.widgetDeliveries(of: [quiet], localizer: localizer, now: now).first?.steps)
    }

    /// Published late on Thursday, Friday's parcel moves under Arriving today at midnight and leads as Next up, and
    /// Thursday's goes back under On the way. The steps then name their weekday, so the widget is drawn again at the
    /// midnight after each.
    @MainActor
    func testTheParcelsWidgetsSectionsAndNextUpFollowTheDay() throws {
        let localizer = Localizer(systemUses24HourTime: { true })
        localizer.language = .en
        let calendar = Calendar.current
        let thursday = try XCTUnwrap(DateParser.deliveryDate("2026-11-12"))
        let lateThursday = try XCTUnwrap(calendar.date(bySettingHour: 23, minute: 30, second: 0, of: thursday))
        let friday = try XCTUnwrap(calendar.date(byAdding: .day, value: 1, to: thursday))
        let fridayMorning = friday.addingTimeInterval(8 * 3_600)
        let thursdays = UUID(), fridays = UUID()
        var due = makeParcel(id: thursdays, events: [event(thursdays, .inTransit, DateParser.isoString(thursday.addingTimeInterval(20 * 3_600)))])
        due.expectedDelivery = "2026-11-12"
        var next = makeParcel(id: fridays, events: [event(fridays, .inTransit, DateParser.isoString(thursday.addingTimeInterval(9 * 3_600)))])
        next.expectedDelivery = "2026-11-13"

        let published = ParcelStore.widgetDeliveries(of: [due, next], localizer: localizer, now: lateThursday)
        func shown(_ date: Date) -> [String] {
            DeliveryWidgetList.cardOrder(DeliveryWidgetSelection.listed(published, now: date)).map {
                "\($0.id == fridays ? "Friday" : "Thursday") \($0.section ?? "none")\($0.nextUp == true ? ", next" : "")"
            }
        }
        XCTAssertEqual(shown(lateThursday), ["Thursday today, next", "Friday active"])
        XCTAssertEqual(shown(fridayMorning), ["Friday today, next", "Thursday active"])
        XCTAssertEqual(DeliveryWidgetList.sections(DeliveryWidgetSelection.listed(published, now: fridayMorning)).map(\.kind), ["today", "active"])
        XCTAssertEqual(published.first { $0.id == thursdays }?.steps?.first?.time(now: fridayMorning, locale: Locale(identifier: "en")), "Thu 20:00")

        // A step alone, its words and its parcel's unchanged, still has the widget drawn again the midnight after it.
        var stepped = widgetParcel(label: "Sorted", outForDelivery: false)
        stepped.steps = [DeliveryWidgetStep(at: thursday.addingTimeInterval(20 * 3_600), text: "Sorted", clock: "20:00")]
        XCTAssertEqual(DeliveryWidgetDays.changes(in: [stepped], after: lateThursday), [friday])
        XCTAssertEqual(DeliveryWidgetDays.changes(in: [stepped], after: fridayMorning), [])
    }

    @MainActor
    func testALinksCardSaysWhenAParcelArrivesAndAGiftNoMoreThanThat() {
        let localizer = Localizer(systemUses24HourTime: { true })
        localizer.language = .en
        let id = UUID()
        let now = Date()
        let today = DateFormatter()
        today.dateFormat = "yyyy-MM-dd"

        // On its way: the estimate is a line of its own, and a gift only says when it arrives.
        var moving = makeParcel(id: id, events: [event(id, .outForDelivery, DateParser.isoString(now))])
        XCTAssertNil(localizer.sharedParcelDetail(moving))
        XCTAssertNil(localizer.giftDetail(moving, opened: false))
        moving.expectedDelivery = today.string(from: now) + " 13:00–17:00"
        XCTAssertEqual(localizer.sharedParcelDetail(moving), "Today, 13:00–17:00")
        XCTAssertEqual(localizer.giftDetail(moving, opened: false), "Arrives today, 13:00–17:00")
        // Out for delivery, a card names no day; a gift's names no stage, so it says what out for delivery means.
        moving.expectedDelivery = today.string(from: now)
        XCTAssertNil(localizer.sharedParcelDetail(moving))
        XCTAssertEqual(localizer.giftDetail(moving, opened: false), "Arrives today")
        // Sent out yesterday with no scan since, it comes today only if the carrier still says today.
        let calendar = Calendar.current
        let noon = calendar.date(bySettingHour: 12, minute: 0, second: 0, of: now)!
        var outYesterday = makeParcel(id: id, events: [event(id, .outForDelivery, DateParser.isoString(calendar.date(byAdding: .day, value: -1, to: noon)!))])
        outYesterday.expectedDelivery = today.string(from: calendar.date(byAdding: .day, value: 1, to: noon)!)
        XCTAssertNil(localizer.giftDetail(outYesterday, opened: false, now: noon))
        outYesterday.expectedDelivery = today.string(from: noon)
        XCTAssertEqual(localizer.giftDetail(outYesterday, opened: false, now: noon), "Arrives today")

        // Arrived: the day on the usual card, the day and the time for a gift that is opened.
        let arrived = makeParcel(id: id, events: [event(id, .delivered, DateParser.isoString(now))])
        XCTAssertEqual(localizer.sharedParcelDetail(arrived), "Today")
        XCTAssertEqual(localizer.giftDetail(arrived, opened: true), "Delivered today at \(localizer.clockTime(now))")
        XCTAssertNil(localizer.giftDetail(moving, opened: true))
    }

    @MainActor
    func testImportChargesReadTheirAmountTheLowerOnlinePriceWhileItCanBePaidAndWhetherTheyArePaid() {
        let localizer = Localizer()
        localizer.language = .en
        let id = UUID()
        func line(_ charges: ImportCharges?, _ stage: TrackingStage = .inTransit) -> ImportChargesLine? {
            var parcel = makeParcel(id: id, events: [event(id, stage, "2026-10-20T10:00:00Z")])
            parcel.carrierData = CarrierData(importCharges: charges)
            return localizer.importCharges(parcel)
        }
        let listed = ImportCharges(currency: "EUR", customs: 23.41, carrierFee: 11, carrierFeeOnline: 8.09)
        // Once the online window closes, the carrier lists the price on delivery alone.
        var closed = listed
        closed.carrierFeeOnline = nil
        closed.statusCode = "FINESTRA_PAGAMENTO_CHIUSA"
        var paid = listed
        paid.paid = true
        paid.statusCode = "PAGATO"

        XCTAssertEqual(line(listed), ImportChargesLine(text: "€34.41 (€31.50 if paid online)", payableOnline: true))
        XCTAssertEqual(line(closed), ImportChargesLine(text: "€34.41", payableOnline: false))
        // Delivered unpaid: settled at the door or not at all.
        XCTAssertEqual(line(listed, .delivered), ImportChargesLine(text: "€34.41", payableOnline: false))
        XCTAssertEqual(line(paid), ImportChargesLine(text: "Paid (€34.41 on delivery, €31.50 online)", payableOnline: false))
        XCTAssertEqual(line(paid, .delivered)?.text, "Paid (€34.41 on delivery, €31.50 online)")
        XCTAssertEqual(line(ImportCharges(currency: "EUR", customs: 23.41, paid: true))?.text, "Paid (€23.41)")
        XCTAssertEqual(line(ImportCharges(currency: "EUR", paid: true))?.text, "Paid")
        XCTAssertNil(line(ImportCharges(currency: "EUR", statusCode: "IN_ATTESA")))
        XCTAssertNil(line(nil))

        localizer.language = .it
        XCTAssertEqual(line(listed)?.text, "34,41\u{00A0}€ (31,50\u{00A0}€ pagando online)")
    }

    @MainActor
    func testAScanDatedByItsDayAloneShowsThatDayAndNoClockTime() throws {
        let localizer = Localizer()
        localizer.language = .en
        let id = UUID()
        let today = try XCTUnwrap(DateParser.deliveryDate("2026-09-21"))
        let now = today.addingTimeInterval(15 * 3_600)
        // The instant only orders the scan: the start of the day in a zone two hours ahead of UTC,
        // which falls on the day before for a reader west of it.
        func dayScan(_ day: String, _ occurredAt: String) -> TrackingEvent {
            var scan = event(id, .delivered, occurredAt)
            scan.day = day
            return scan
        }
        let scan = dayScan("2026-09-21", "2026-09-20T22:00:00Z")
        XCTAssertEqual(scan.calendarDay, today)
        XCTAssertEqual(localizer.relativeTime(of: scan, now: now), "today")
        let arrived = makeParcel(id: id, events: [scan])
        XCTAssertEqual(localizer.parcelCompletionDate(arrived, now: now), "today")
        XCTAssertEqual(arrived.stampDeliveryDate, "21.09")
        // A gift's card then gives the day as the card's own line does.
        XCTAssertEqual(localizer.giftDetail(arrived, opened: true, now: now), "Today")
        let earlier = makeParcel(id: id, events: [dayScan("2026-09-12", "2026-09-11T22:00:00Z")])
        XCTAssertEqual(localizer.giftDetail(earlier, opened: true, now: now), "Sat 12 Sep")

        // A scan with a clock time keeps it.
        let timed = event(id, .inTransit, DateParser.isoString(now.addingTimeInterval(-2 * 3_600)))
        XCTAssertNil(timed.calendarDay)
        XCTAssertEqual(localizer.relativeTime(of: timed, now: now), "2 h ago")
    }

    @MainActor
    func testAStatusSeenAtACheckShowsTheCheckDayAndNoClockTime() throws {
        let localizer = Localizer()
        localizer.language = .en
        let id = UUID()
        let today = try XCTUnwrap(DateParser.deliveryDate("2026-09-21"))
        let now = today.addingTimeInterval(15 * 3_600)
        // The carrier gave no time for the status: the row's time is the check's, two hours ago.
        var seen = event(id, .inTransit, DateParser.isoString(now.addingTimeInterval(-2 * 3_600)))
        seen.observed = true
        XCTAssertEqual(seen.calendarDay, today)
        XCTAssertFalse(seen.showsTime)
        XCTAssertEqual(localizer.relativeTime(of: seen, now: now), "today")
        let earlier = makeParcel(id: id, events: [seen])
        XCTAssertEqual(earlier.trackingEvents.first?.displayDate, today)
    }

    func testPassportNewStampsUseExplicitScansAndLocalCompletionDates() throws {
        func shipment(_ scans: [(TrackingStage, String, String?)]) -> Parcel {
            let id = UUID()
            return makeParcel(id: id, events: scans.map { stage, date, location in
                var scan = event(id, stage, date)
                scan.location = location
                return scan
            })
        }
        var international = shipment([
            (.accepted, "2026-10-31T12:00:00Z", "Berlin, Germany"),
            (.readyForPickup, "2026-11-30T12:00:00Z", "Zurich, Switzerland"),
            (.delivered, "2026-12-01T08:00:00Z", "Zurich, Switzerland"),
            (.delivered, "2026-12-01T08:10:00Z", "Zurich, Switzerland"),
        ])
        international.archivedAt = "2026-12-02T12:00:00Z"
        let parcels = [international,
            shipment([(.accepted, "2026-11-29T12:00:00Z", "CH"), (.delivered, "2026-12-01T09:00:00Z", "CH")]),
            shipment([(.delivered, "2026-11-30T23:30:00Z", nil)]),
        ]
        let stats = PassportStatistics(parcels: parcels, timeZone: try XCTUnwrap(TimeZone(identifier: "Europe/Zurich")))
        XCTAssertEqual(stats.crossBorderCount, 1)
        XCTAssertEqual(stats.domesticDeliveryCount, 1)
        XCTAssertEqual(stats.longWaitDeliveryCount, 1)
        XCTAssertEqual(stats.pickupDeliveryCount, 1)
        XCTAssertEqual(stats.decemberDeliveryCount, 3)
        XCTAssertEqual(stats.maxDeliveriesInOneDay, 3)
        let utc = PassportStatistics(parcels: parcels, timeZone: try XCTUnwrap(TimeZone(secondsFromGMT: 0)))
        XCTAssertEqual(utc.decemberDeliveryCount, 2)
        XCTAssertEqual(utc.maxDeliveriesInOneDay, 2)
    }

    func testPassportNewStampsRejectUnprovenJourneys() throws {
        func shipment(_ scans: [(TrackingStage, String, String?)]) -> Parcel {
            let id = UUID()
            return makeParcel(id: id, events: scans.map { stage, date, location in
                var scan = event(id, stage, date)
                scan.location = location
                return scan
            })
        }
        let stats = PassportStatistics(parcels: [
            shipment([(.registered, "2026-11-01T00:00:00Z", "DE"), (.accepted, "2026-11-02T00:00:00Z", nil), (.delivered, "2026-11-03T00:00:00Z", "CH")]),
            shipment([(.accepted, "2026-11-01T00:00:00Z", "Wilmington, DE"), (.delivered, "2026-11-03T00:00:00Z", "Geneva, GE")]),
            shipment([(.accepted, "2026-11-01T00:00:00Z", "DE"), (.readyForPickup, "2026-11-01T00:00:00Z", "CH"), (.delivered, "2026-11-01T00:00:00Z", "CH")]),
            shipment([(.accepted, "2026-11-01T00:00:00Z", "DE"), (.readyForPickup, "2026-12-02T00:00:00Z", "CH")]),
            shipment([(.accepted, "2026-11-01T00:00:00Z", "DE"), (.delivered, "2026-12-02T00:00:00Z", "CH"), (.returned, "2026-12-03T00:00:00Z", nil)]),
            // It waited at a pickup point, then went out to the door.
            shipment([(.accepted, "2026-11-01T00:00:00Z", nil), (.readyForPickup, "2026-11-02T00:00:00Z", nil),
                      (.outForDelivery, "2026-11-04T08:00:00Z", nil), (.delivered, "2026-11-04T12:00:00Z", nil)]),
            shipment([(.accepted, "invalid", "DE"), (.delivered, "2026-12-02T00:00:00Z", "CH")]),
        ], timeZone: try XCTUnwrap(TimeZone(secondsFromGMT: 0)))
        XCTAssertEqual(stats.crossBorderCount, 0)
        XCTAssertEqual(stats.domesticDeliveryCount, 0)
        XCTAssertEqual(stats.longWaitDeliveryCount, 0)
        XCTAssertEqual(stats.pickupDeliveryCount, 0)
        XCTAssertEqual(stats.decemberDeliveryCount, 0)
    }

    func testPassportCountsAStateCodeOnlyInTheCountryTheScanWasPlacedIn() {
        func shipment(_ scans: [(TrackingStage, String, String?, String?)]) -> Parcel {
            let id = UUID()
            return makeParcel(id: id, events: scans.map { stage, date, location, placed in
                var scan = event(id, stage, date)
                scan.location = location
                scan.place = placed.map { EventPlace(latitude: 0, longitude: 0, precision: .country, country: $0, name: $0) }
                return scan
            })
        }
        let stats = PassportStatistics(parcels: [
            shipment([(.accepted, "2026-08-04T10:00:00Z", "Haiger, DE", "DE"), (.delivered, "2026-08-05T10:00:00Z", "Koblenz, DE", "DE")]),
            shipment([(.accepted, "2026-08-04T10:00:00Z", "Toronto, CA", "CA"), (.delivered, "2026-08-06T10:00:00Z", nil, nil)]),
            // Delaware and California, placed in the United States, and a code nobody placed: none of them a country.
            shipment([(.accepted, "2026-08-04T10:00:00Z", "Wilmington, DE", "US"), (.delivered, "2026-08-06T10:00:00Z", "Los Angeles, CA", "US")]),
            shipment([(.accepted, "2026-08-04T10:00:00Z", "Hannover, DE", nil), (.delivered, "2026-08-06T10:00:00Z", nil, nil)]),
        ])
        XCTAssertEqual(Set(stats.originCountries.map(\.code)), ["CA", "DE"])
        XCTAssertEqual(stats.originCountries.map(\.count), [1, 1])
        XCTAssertEqual(stats.domesticDeliveryCount, 1)
        XCTAssertEqual(stats.crossBorderCount, 0)
    }

    func testPassportWaitingStampRequiresMoreThanThirtyDays() {
        let id = UUID()
        let start = event(id, .accepted, "2026-11-01T00:00:00Z")
        let exact = makeParcel(id: id, events: [start, event(id, .delivered, "2026-12-01T00:00:00Z")])
        let longer = makeParcel(id: id, events: [start, event(id, .delivered, "2026-12-01T00:00:00.001Z")])
        XCTAssertEqual(PassportStatistics(parcels: [exact]).longWaitDeliveryCount, 0)
        XCTAssertEqual(PassportStatistics(parcels: [longer]).longWaitDeliveryCount, 1)
    }

    func testEmptyPassportHasNoInventedRecordsOrCountries() {
        let statistics = PassportStatistics(parcels: [])
        XCTAssertEqual(statistics.trackedCount, 0)
        XCTAssertEqual(statistics.deliveredCount, 0)
        XCTAssertEqual(statistics.activeCount, 0)
        XCTAssertEqual(statistics.durationSampleCount, 0)
        XCTAssertNil(statistics.averageDeliveryDuration)
        XCTAssertNil(statistics.fastestDelivery)
        XCTAssertTrue(statistics.originCountries.isEmpty)
        XCTAssertEqual(statistics.unknownOriginCount, 0)
        XCTAssertEqual(statistics.nextMilestoneCount, 1)
        XCTAssertEqual(statistics.milestoneProgress, 0)
    }

    func testPassportUsesCarrierJourneyTimesAndIncludesArchivedDeliveries() throws {
        let firstID = UUID()
        var first = makeParcel(id: firstID, events: [
            event(firstID, .registered, "2026-08-01T10:00:00Z"),
            event(firstID, .accepted, "2026-08-06T10:00:00Z"),
            event(firstID, .delivered, "2026-08-08T10:00:00Z"),
        ])
        first.archivedAt = "2026-08-09T10:00:00Z"
        let secondID = UUID()
        var second = makeParcel(id: secondID, events: [
            event(secondID, .inTransit, "2026-08-07T10:00:00Z"),
            event(secondID, .delivered, "2026-08-08T10:00:00Z"),
            event(secondID, .pending, "2026-08-09T10:00:00Z"),
        ])
        // A parcel added after delivery still has a valid carrier journey.
        second.createdAt = "2026-08-10T10:00:00Z"
        let returnedID = UUID()
        let returned = makeParcel(id: returnedID, events: [
            event(returnedID, .accepted, "2026-08-05T10:00:00Z"),
            event(returnedID, .delivered, "2026-08-06T10:00:00Z"),
            event(returnedID, .returned, "2026-08-08T10:00:00Z"),
        ])
        let statistics = PassportStatistics(parcels: [first, second, returned, makeParcel()])
        XCTAssertEqual(statistics.trackedCount, 4)
        XCTAssertEqual(statistics.deliveredCount, 2)
        XCTAssertEqual(statistics.activeCount, 1)
        XCTAssertEqual(statistics.durationSampleCount, 2)
        XCTAssertEqual(try XCTUnwrap(statistics.averageDeliveryDuration), 36 * 3_600, accuracy: 0.001)
        XCTAssertEqual(statistics.fastestDelivery?.parcelID, secondID)
        XCTAssertEqual(statistics.fastestDelivery?.duration, 24 * 3_600)
        XCTAssertEqual(statistics.fastestDelivery?.deliveredAt, DateParser.date("2026-08-08T10:00:00Z"))
    }

    func testPassportOrdersActualInstantsAcrossTimeZones() throws {
        let id = UUID()
        let parcel = makeParcel(id: id, events: [
            // Lexical order incorrectly puts the accepted scan last.
            event(id, .accepted, "2026-08-08T12:00:00+03:00"),
            event(id, .delivered, "2026-08-08T11:00:00Z"),
        ])
        let statistics = PassportStatistics(parcels: [parcel])
        XCTAssertEqual(statistics.deliveredCount, 1)
        XCTAssertEqual(statistics.activeCount, 0)
        XCTAssertEqual(try XCTUnwrap(statistics.averageDeliveryDuration), 2 * 3_600, accuracy: 0.001)
    }

    func testPassportDoesNotInventDurationsFromPartialOrInvalidHistories() {
        let histories: [[(TrackingStage, String)]] = [
            [(.delivered, "2026-08-08T10:00:00Z")],
            [(.registered, "2026-08-07T10:00:00Z"), (.delivered, "2026-08-08T10:00:00Z")],
            [(.outForDelivery, "2026-08-08T09:00:00Z"), (.delivered, "2026-08-08T10:00:00Z")],
            [(.accepted, "invalid"), (.delivered, "2026-08-08T10:00:00Z")],
            [(.accepted, "2026-08-08T10:00:00Z"), (.delivered, "invalid")],
            [(.accepted, "2026-08-09T10:00:00Z"), (.delivered, "2026-08-08T10:00:00Z")],
            [(.accepted, "2026-08-08T10:00:00Z"), (.delivered, "2026-08-08T10:00:00Z")],
        ]
        for history in histories {
            let id = UUID()
            let parcel = makeParcel(id: id, events: history.map { event(id, $0.0, $0.1) })
            let statistics = PassportStatistics(parcels: [parcel])
            XCTAssertEqual(statistics.durationSampleCount, 0, "Unexpected duration for \(history)")
            XCTAssertNil(statistics.averageDeliveryDuration)
            XCTAssertNil(statistics.fastestDelivery)
        }
    }

    func testPassportCountryRanksRequireExplicitFirstPhysicalScanCountry() {
        func originParcel(_ location: String?) -> Parcel {
            let id = UUID()
            var scan = event(id, .accepted, "2026-08-06T10:00:00Z")
            scan.location = location
            // Neither the international identifier nor UPS's headquarters is evidence.
            return makeParcel(id: id, trackingNumber: "RR123456785US", events: [scan])
        }
        let statistics = PassportStatistics(parcels: [
            originParcel("Milano, IT"), originParcel("Roma, Italy"),
            originParcel("Berlin (Germany)"), originParcel("Paris, France"),
            originParcel("Buchs AG"), originParcel(nil), originParcel("Europe"),
        ])
        XCTAssertEqual(statistics.originCountries.map(\.code), ["IT", "DE", "FR"])
        XCTAssertEqual(statistics.originCountries.map(\.count), [2, 1, 1])
        XCTAssertEqual(statistics.originCountries.first?.flag, "🇮🇹")
        XCTAssertEqual(statistics.knownOriginCount, 4)
        XCTAssertEqual(statistics.unknownOriginCount, 3)
    }

    func testPassportNeverPromotesLaterDestinationScanToOrigin() {
        let id = UUID()
        var registered = event(id, .registered, "2026-08-05T10:00:00Z")
        registered.location = "London, GB"
        let accepted = event(id, .accepted, "2026-08-06T10:00:00Z")
        var transit = event(id, .inTransit, "2026-08-07T10:00:00Z")
        transit.location = "Zürich, CH"
        let unknown = PassportStatistics(parcels: [makeParcel(id: id, events: [registered, accepted, transit])])
        XCTAssertTrue(unknown.originCountries.isEmpty)
        XCTAssertEqual(unknown.unknownOriginCount, 1)

        // Registration's location is ignored; the first physical scan supplies evidence.
        var locatedAccepted = accepted
        locatedAccepted.location = "Paris, Frankreich"
        let known = PassportStatistics(parcels: [makeParcel(id: id, events: [registered, locatedAccepted, transit])])
        XCTAssertEqual(known.originCountries.map(\.code), ["FR"])

        var customs = event(id, .customs, "2026-08-06T10:00:00Z")
        customs.location = "Basel, CH"
        XCTAssertTrue(PassportStatistics(parcels: [makeParcel(id: id, events: [customs, transit])]).originCountries.isEmpty)
    }

    func testPassportDoesNotMistakeStateOrCantonCodesForCountries() {
        for location in ["Los Angeles, CA", "Bern, BE", "Dover, DE", "Fribourg (FR)", "St. John's, NL"] {
            let id = UUID()
            var scan = event(id, .accepted, "2026-08-06T10:00:00Z")
            scan.location = location
            let statistics = PassportStatistics(parcels: [makeParcel(id: id, events: [scan])])
            XCTAssertTrue(statistics.originCountries.isEmpty, "Ambiguous address: \(location)")
        }
        for (location, code) in [("Toronto, Canada", "CA"), ("Brussels, Belgium", "BE"), ("DE", "DE")] {
            let id = UUID()
            var scan = event(id, .accepted, "2026-08-06T10:00:00Z")
            scan.location = location
            let statistics = PassportStatistics(parcels: [makeParcel(id: id, events: [scan])])
            XCTAssertEqual(statistics.originCountries.first?.code, code)
        }
    }

    func testPassportTiesAndMilestonesAreStableWhenParcelOrderChanges() {
        let parcels = (1...5).map { index in
            let id = UUID(uuidString: "00000000-0000-0000-0000-\(String(format: "%012d", index))")!
            return makeParcel(id: id, events: [
                event(id, .accepted, "2026-08-06T10:00:00Z"),
                event(id, .delivered, "2026-08-07T10:00:00Z"),
            ])
        }
        let forward = PassportStatistics(parcels: parcels)
        let reverse = PassportStatistics(parcels: parcels.reversed())
        XCTAssertEqual(forward.fastestDelivery, reverse.fastestDelivery)
        XCTAssertEqual(forward.fastestDelivery?.parcelID, parcels.first?.id)
        XCTAssertEqual(forward.nextMilestoneCount, 10)
        XCTAssertEqual(forward.milestoneProgress, 0.5)
    }

    @MainActor
    func testFriendsPreviewSharesOnlyChosenRoundedSummary() throws {
        let id = UUID()
        let parcel = makeParcel(id: id, events: [
            event(id, .accepted, "2026-09-06T10:00:00Z"),
            event(id, .delivered, "2026-09-07T11:00:00Z"),
        ])
        let now = DateParser.date("2026-09-08T12:00:00Z")!
        var profile = FriendProfile(nickname: "My nickname", shareStats: true, shareArrival: false)
        let card = FriendsStore.ownCard(parcels: [parcel], profile: profile, now: now)
        XCTAssertEqual(card.stats?.deliveredCount, 1)
        XCTAssertEqual(card.stats?.averageDays, 2)
        XCTAssertEqual(card.stats?.stamps, [.first, .express])
        XCTAssertNil(card.arrivedThisWeek)
        let encoded = String(data: try JSONEncoder().encode(card), encoding: .utf8)!
        for secret in [parcel.trackingNumber, parcel.label, id.uuidString, "occurredAt", "trackingEvents", "carrier"] {
            XCTAssertFalse(encoded.contains(secret))
        }
        profile.shareStats = false
        profile.shareArrival = true
        let privateCard = FriendsStore.ownCard(parcels: [parcel], profile: profile, now: now)
        XCTAssertNil(privateCard.stats)
        XCTAssertEqual(privateCard.arrivedThisWeek, true)
        var returned = parcel
        returned.trackingEvents.append(event(id, .returned, "2026-09-08T10:00:00Z"))
        XCTAssertEqual(FriendsStore.ownCard(parcels: [returned], profile: profile, now: now).arrivedThisWeek, false)
    }

    @MainActor
    func testFriendsWeekUsesFirstDeliveryAndUTCBoundary() {
        let id = UUID()
        let parcel = makeParcel(id: id, events: [
            event(id, .accepted, "2026-09-04T10:00:00Z"),
            event(id, .delivered, "2026-09-06T23:59:00Z"),
            event(id, .delivered, "2026-09-07T11:00:00Z"),
        ])
        let profile = FriendProfile(nickname: "Test", shareStats: false, shareArrival: true)
        let now = DateParser.date("2026-09-08T12:00:00Z")!
        XCTAssertEqual(FriendsStore.ownCard(parcels: [parcel], profile: profile, now: now).arrivedThisWeek, false)
    }

    func testSharedFriendsFixtureDecodesIncludingHiddenStatistics() throws {
        let url = Bundle.main.url(forResource: "FriendsDemo", withExtension: "json")!
        let snapshot = try JSONDecoder().decode(FriendsSnapshot.self, from: Data(contentsOf: url))
        XCTAssertEqual(snapshot.friends.count, 3)
        XCTAssertEqual(snapshot.friends.first?.stats?.stamps, [.first, .ten, .connected, .express])
        XCTAssertNil(snapshot.friends.last?.stats)
        XCTAssertNil(snapshot.friends.last?.arrivedThisWeek)
        XCTAssertEqual(snapshot.profile?.shareArrival, false)
    }

    func testNextDeliveryKeepsIssuesSeparateAndPrioritizesPickup() {
        let now = DateParser.date("2026-09-09T12:00:00Z")!
        func shipment(_ stage: TrackingStage) -> Parcel {
            let id = UUID()
            return makeParcel(id: id, events: [event(id, stage, "2026-09-09T08:00:00Z")])
        }
        let customs = shipment(.customs)
        let failed = shipment(.failedAttempt)
        var delivery = shipment(.outForDelivery)
        delivery.syncStatus = .error
        let pickup = shipment(.readyForPickup)
        XCTAssertEqual(ParcelOrganizer.nextDelivery(from: [customs, failed, delivery], now: now)?.id, delivery.id)
        XCTAssertEqual(ParcelOrganizer.nextDelivery(from: [customs, delivery, pickup], now: now)?.id, pickup.id)
        XCTAssertNil(ParcelOrganizer.nextDelivery(from: [customs, failed], now: now))
        XCTAssertNil(ParcelOrganizer.nextDelivery(from: [shipment(.delivered), shipment(.returned)], now: now))
        var archived = delivery
        archived.archivedAt = "2026-09-09T10:00:00Z"
        XCTAssertNil(ParcelOrganizer.nextDelivery(from: [archived], now: now))
    }

    func testNextDeliveryPrefersAParcelWhoseRouteCanBeDrawnOnTheSameDay() {
        let now = DateParser.date("2026-09-09T12:00:00Z")!
        let zurich = EventPlace(latitude: 47.37, longitude: 8.54, precision: .city, country: "CH", name: "Zürich")
        func shipment(_ stage: TrackingStage, at occurredAt: String, place: EventPlace? = nil, expected: String? = nil) -> Parcel {
            let id = UUID()
            var parcel = makeParcel(id: id, events: [TrackingEvent(
                id: UUID(), packageID: id, stage: stage, description: "Update", location: nil, occurredAt: occurredAt, place: place
            )])
            parcel.expectedDelivery = expected
            return parcel
        }
        // Without a date on either, the newer update used to win; the map now decides first.
        let newer = shipment(.inTransit, at: "2026-09-09T09:00:00Z")
        let placed = shipment(.inTransit, at: "2026-09-09T07:00:00Z", place: zurich)
        XCTAssertEqual(ParcelOrganizer.nextDelivery(from: [newer, placed], now: now)?.id, placed.id)
        let datedNewer = shipment(.inTransit, at: "2026-09-09T09:00:00Z", expected: "2026-09-10")
        let datedPlaced = shipment(.inTransit, at: "2026-09-09T07:00:00Z", place: zurich, expected: "2026-09-10")
        XCTAssertEqual(ParcelOrganizer.nextDelivery(from: [datedNewer, datedPlaced], now: now)?.id, datedPlaced.id)
        // An earlier day or a more urgent stage still comes first.
        let sooner = shipment(.inTransit, at: "2026-09-09T06:00:00Z", expected: "2026-09-09")
        XCTAssertEqual(ParcelOrganizer.nextDelivery(from: [sooner, datedPlaced], now: now)?.id, sooner.id)
        let pickup = shipment(.readyForPickup, at: "2026-09-09T06:00:00Z")
        XCTAssertEqual(ParcelOrganizer.nextDelivery(from: [pickup, placed], now: now)?.id, pickup.id)
    }

    func testStampNamesTheFirstPlacedCountryAndIsDatedOnlyOnceDelivered() {
        func scan(_ day: Int, _ stage: TrackingStage, _ place: EventPlace?) -> TrackingEvent {
            TrackingEvent(id: UUID(), packageID: UUID(), stage: stage, description: "Update", location: nil,
                          occurredAt: "2026-09-\(day)T12:00:00Z", place: place)
        }
        let kyoto = EventPlace(latitude: 35.01, longitude: 135.77, precision: .city, country: "JP", name: "Kyoto")
        let zurich = EventPlace(latitude: 47.37, longitude: 8.54, precision: .city, country: "CH", name: "Zürich")
        // Scans arrive in no particular order; the origin is the earliest one with a place.
        let scans = [scan(20, .outForDelivery, zurich), scan(10, .registered, nil), scan(11, .accepted, kyoto)]
        let travelling = makeParcel(events: scans)
        XCTAssertEqual(travelling.stampOrigin, "JP")
        XCTAssertNil(travelling.stampDeliveryDate)

        let arrived = makeParcel(events: scans + [scan(21, .delivered, zurich)])
        XCTAssertEqual(arrived.stampOrigin, "JP")
        XCTAssertEqual(arrived.stampDeliveryDate, "21.09")

        let unplaced = makeParcel(events: [scan(10, .registered, nil)])
        XCTAssertNil(unplaced.stampOrigin)
    }

    func testJustDeliveredAreTheParcelsShownOnTheirWayThatHaveArrived() {
        let tea = UUID(), lamp = UUID(), book = UUID()
        let shown = [
            makeParcel(id: tea, events: [event(tea, .outForDelivery, "2026-09-02T08:00:00Z")]),
            makeParcel(id: lamp, events: [event(lamp, .inTransit, "2026-09-02T08:00:00Z")]),
            makeParcel(id: book, events: [event(book, .delivered, "2026-09-01T08:00:00Z")]),
        ]
        var now = shown
        now[0].trackingEvents.append(event(tea, .delivered, "2026-09-03T08:00:00Z"))
        now[1].trackingEvents.append(event(lamp, .outForDelivery, "2026-09-03T08:00:00Z"))
        // The parcel comes back as the list showed it: that is the place it keeps.
        XCTAssertEqual(ParcelOrganizer.justDelivered(shown: shown, now: now), [shown[0]])
        // One archived in the meantime, or gone, has no card to move.
        now[0].archivedAt = "2026-09-03T09:00:00Z"
        XCTAssertTrue(ParcelOrganizer.justDelivered(shown: shown, now: now).isEmpty)
        XCTAssertTrue(ParcelOrganizer.justDelivered(shown: shown, now: []).isEmpty)
    }

    func testDeliveredHoldKeepsTheListAsItWasUntilItsCardsLeave() {
        let tea = UUID(), lamp = UUID()
        // Scanned in the last hours: neither parcel has been still for long enough to be flagged.
        let earlier = DateParser.isoString(Date().addingTimeInterval(-7_200))
        let lately = DateParser.isoString(Date().addingTimeInterval(-600))
        let shown = [
            makeParcel(id: tea, events: [event(tea, .outForDelivery, earlier)]),
            makeParcel(id: lamp, events: [event(lamp, .inTransit, earlier)]),
        ]
        var now = shown
        now[0].trackingEvents.append(event(tea, .delivered, lately))
        // The same news makes the lamp the most urgent parcel: on its own, that would move the tea out of its place.
        now[1].trackingEvents.append(event(lamp, .readyForPickup, lately))
        func layout(_ parcels: [Parcel]) -> DeliveryListLayout {
            DeliveryListLayout(parcels: parcels, query: "", status: .all, carrier: nil, sort: .standard, featuresNext: true)
        }

        var hold = DeliveredHold()
        hold.receive(shown: shown, next: now, watching: true)
        XCTAssertEqual(hold.ids, [tea])
        XCTAssertTrue(hold.holds(tea))
        let held = layout(hold.arranged(now))
        XCTAssertEqual(held.next?.id, tea)
        XCTAssertEqual(held.kind(of: lamp), .onTheWay)
        XCTAssertEqual(held.active.count, 2)

        // Let go, the tea joins the past deliveries and the lamp becomes Next up: those two cards are others.
        let after = layout(now)
        let changes = held.changing(into: after)
        XCTAssertEqual(Set(changes.keys), [tea, lamp])
        XCTAssertEqual(changes[tea]?.was, .next)
        XCTAssertEqual(changes[tea]?.becomes, .past)
        XCTAssertEqual(changes[lamp]?.becomes, .next)

        // More news for a held parcel keeps it held; archiving it lets it go, and it is arranged as it is.
        hold.receive(shown: now, next: now, watching: true)
        XCTAssertEqual(hold.ids, [tea])
        var archived = now
        archived[0].archivedAt = lately
        XCTAssertTrue(hold.arranged(archived)[0].isArchived)
        hold.receive(shown: now, next: archived, watching: true)
        XCTAssertTrue(hold.isEmpty)
        XCTAssertEqual(hold.arranged(now), now)

        // A list that nobody is looking at holds nothing, and one that stops being looked at lets go.
        var unseen = DeliveredHold()
        unseen.receive(shown: shown, next: now, watching: false)
        XCTAssertTrue(unseen.isEmpty)
        unseen.receive(shown: shown, next: now, watching: true)
        XCTAssertFalse(unseen.isEmpty)
        unseen.release()
        XCTAssertTrue(unseen.isEmpty)
        XCTAssertEqual(unseen, DeliveredHold())
    }

    func testACopyNoLongerUpdatedNeverLeadsTheListAsksNothingAndHasNoLinkOrCarrierToChange() {
        let tea = UUID(), lamp = UUID()
        let earlier = DateParser.isoString(Date().addingTimeInterval(-7_200))
        let parcels = [
            makeParcel(id: tea, events: [event(tea, .outForDelivery, earlier)]),
            makeParcel(id: lamp, events: [event(lamp, .failedAttempt, earlier)]),
        ]
        func layout(frozen: Set<UUID>) -> DeliveryListLayout {
            DeliveryListLayout(parcels: parcels, query: "", status: .all, carrier: nil, sort: .standard, featuresNext: true, frozen: frozen)
        }
        let live = layout(frozen: [])
        XCTAssertEqual(live.next?.id, tea)
        XCTAssertEqual(live.kind(of: lamp), .attention)
        let frozen = layout(frozen: [tea, lamp])
        XCTAssertNil(frozen.next)
        XCTAssertEqual(frozen.kind(of: tea), .onTheWay)
        XCTAssertEqual(frozen.kind(of: lamp), .onTheWay)
        XCTAssertEqual(frozen.active.count, 2)

        XCTAssertEqual(ParcelMenuItem.menu(for: parcels[0], onCard: true, frozen: true),
                       [[.open], [.rename, .copyTrackingNumber], [.archive], [.delete]])
        XCTAssertEqual(ParcelMenu.items(for: parcels[0], reader: false, frozen: true),
                       [[.rename, .copyTrackingNumber], [.archive], [.delete]])
    }

    func testADeliveryTheListDidNotSeeArrivesOnItTheNextTimeItIsInView() throws {
        let suite = "ParcelLogicTests.onTheWay.\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        defer { defaults.removePersistentDomain(forName: suite) }
        let record = OnTheWayRecord(defaults: defaults)
        let tea = UUID(), lamp = UUID(), mug = UUID(), vase = UUID(), pen = UUID(), book = UUID()
        func ago(_ hours: Double) -> String { DateParser.isoString(Date().addingTimeInterval(-hours * 3_600)) }
        func layout(_ parcels: [Parcel]) -> DeliveryListLayout {
            DeliveryListLayout(parcels: parcels, query: "", status: .all, carrier: nil, sort: .standard, featuresNext: true)
        }
        let shown = [
            makeParcel(id: tea, events: [event(tea, .outForDelivery, ago(2))]),
            makeParcel(id: lamp, events: [event(lamp, .inTransit, ago(2))]),
            makeParcel(id: mug, events: [event(mug, .inTransit, ago(2))]),
            makeParcel(id: vase, events: [event(vase, .inTransit, ago(240))]),
            makeParcel(id: pen, events: [event(pen, .inTransit, ago(3))]),
            makeParcel(id: book, events: [event(book, .delivered, ago(30))]),
        ]
        // A list still loading says nothing; one in view keeps which parcels it shows on their way.
        record.seen([])
        XCTAssertTrue(record.ids.isEmpty)
        record.seen(shown)
        XCTAssertEqual(record.ids, [tea, lamp, mug, vase, pen])

        // Out of view, the tea arrives; the lamp arrives and is deleted, the mug arrives and is archived, and the vase
        // arrived eight days ago. Only the tea is news when the list is next in view.
        var now = shown
        now[0].trackingEvents.append(event(tea, .delivered, ago(1)))
        now[2].trackingEvents.append(event(mug, .delivered, ago(1)))
        now[2].archivedAt = ago(0.5)
        now[3].trackingEvents.append(event(vase, .delivered, ago(192)))
        now.remove(at: 1)
        XCTAssertEqual(record.arrivals(in: now, at: Date()).map(\.id), [tea])

        // It arrives where it stood on its way, Next up, and only it: the other cards are as they are.
        var hold = DeliveredHold()
        hold.catchUp(record.arrivals(in: now, at: Date()))
        XCTAssertEqual(hold.ids, [tea])
        XCTAssertTrue(hold.late)
        XCTAssertEqual(now[0].onItsWay.currentStage, .outForDelivery)
        let held = layout(hold.arranged(now))
        XCTAssertEqual(held.next?.id, tea)
        XCTAssertEqual(held.kind(of: pen), .onTheWay)
        XCTAssertEqual(held.changing(into: layout(now))[tea]?.becomes, .past)
        // Until its postmark lands it still counts as on its way: a list that goes out of view before then, as one
        // opening a parcel's page at launch does, catches up again.
        record.seen(now, holding: hold.unstamped)
        XCTAssertEqual(record.ids, [tea, pen])

        // Once stamped, never again, even should the list go out of view before the card leaves.
        hold.stamp()
        record.forget(hold.ids)
        record.seen(now, holding: hold.unstamped)
        XCTAssertEqual(record.ids, [pen])
        XCTAssertTrue(record.arrivals(in: now, at: Date()).isEmpty)

        // News that lands meanwhile brings another arrival, held where the list showed it, and stamped in turn.
        var later = now
        later[3].trackingEvents.append(event(pen, .delivered, ago(0)))
        hold.receive(shown: now, next: later, watching: true)
        XCTAssertEqual(hold.ids, [tea, pen])
        XCTAssertEqual(hold.unstamped, [pen])
        XCTAssertEqual(layout(hold.arranged(later)).kind(of: pen), .onTheWay)
        hold.stamp()
        record.forget(hold.ids)
        hold.release()
        XCTAssertFalse(hold.late)
        XCTAssertTrue(hold.stamped.isEmpty)
        XCTAssertTrue(record.ids.isEmpty)

        // A list that showed it delivered, as one beside the parcel's page does, has nothing to catch up with.
        record.seen(shown)
        record.seen(now)
        XCTAssertTrue(record.arrivals(in: now, at: Date()).isEmpty)
        // And a hold takes no parcel that isn't a delivery to show.
        hold.catchUp([shown[0], now[1]])
        XCTAssertTrue(hold.isEmpty)
    }

    func testDeliveredConfettiPopsOutOfItsOriginAndFallsAway() throws {
        let origin = CGPoint(x: 200, y: 300)
        let burst = DeliveredConfetti(origin: origin, count: DeliveredConfetti.next, seed: 7)
        // A burst follows from its seed.
        XCTAssertEqual(burst, DeliveredConfetti(origin: origin, count: DeliveredConfetti.next, seed: 7))
        XCTAssertNotEqual(burst.pieces, DeliveredConfetti(origin: origin, count: DeliveredConfetti.next, seed: 8).pieces)
        XCTAssertEqual(burst.pieces.count, 58)
        XCTAssertEqual(DeliveredConfetti(origin: origin, count: DeliveredConfetti.other, seed: 7).pieces.count, 38)
        XCTAssertEqual(burst.glints.count, 6)
        XCTAssertGreaterThan(burst.duration, 1.5)
        XCTAssertLessThanOrEqual(burst.duration, 2.4)
        XCTAssertGreaterThan(Set(burst.pieces.map(\.tone)).count, 4)

        for piece in burst.pieces {
            XCTAssertTrue((0..<DeliveredConfetti.tones).contains(piece.tone))
            // Not there before it is thrown, nor once it has gone.
            XCTAssertNil(burst.pose(of: piece, at: piece.delay - 0.001))
            XCTAssertNil(burst.pose(of: piece, at: piece.delay + piece.life + 0.001))
            let start = try XCTUnwrap(burst.pose(of: piece, at: piece.delay))
            XCTAssertEqual(start.center.x, origin.x, accuracy: 0.001)
            XCTAssertEqual(start.center.y, origin.y, accuracy: 0.001)
            XCTAssertEqual(start.opacity, 0, accuracy: 0.001)
            // Up out of the box, then down, a long way below its highest point, and faint by the end.
            let heights = stride(from: 0.0, to: piece.life, by: 0.02).compactMap { burst.pose(of: piece, at: piece.delay + $0)?.center.y }
            let top = try XCTUnwrap(heights.min())
            XCTAssertLessThan(top, origin.y - 5)
            XCTAssertGreaterThan(try XCTUnwrap(heights.last), top + 100)
            XCTAssertEqual(try XCTUnwrap(burst.pose(of: piece, at: piece.delay + 0.3)).opacity, 1, accuracy: 0.001)
            XCTAssertLessThan(try XCTUnwrap(burst.pose(of: piece, at: piece.delay + piece.life - 0.01)).opacity, 0.05)
        }
        for glint in burst.glints {
            XCTAssertNil(burst.pose(of: glint, at: glint.delay + glint.life + 0.001))
            let start = try XCTUnwrap(burst.pose(of: glint, at: glint.delay))
            XCTAssertEqual(start.center.x, origin.x, accuracy: 0.001)
            XCTAssertEqual(start.scale.width, 0, accuracy: 0.001)
            // It flies out as far as it reaches, shrinking to nothing.
            let end = try XCTUnwrap(burst.pose(of: glint, at: glint.delay + glint.life - 0.0001))
            XCTAssertEqual(hypot(end.center.x - origin.x, end.center.y - origin.y), glint.reach, accuracy: 0.5)
            XCTAssertLessThan(end.scale.width, 0.01)
        }
        // The paper's colours: the card's ink, its carrier's and the app's yellow, one for each tone.
        XCTAssertEqual(DeliveredPaperBurst.colors(ink: .black, surface: .white, brand: .red).count, DeliveredConfetti.tones)
    }

    @MainActor func testDeliveredFlightFoldsEarlyGrowsLateAndKeepsTheNamesOnOneLine() {
        let high = DeliveryCardPlaces.Place(frame: CGRect(x: 16, y: 62, width: 370, height: 242), title: 166)
        let low = DeliveryCardPlaces.Place(frame: CGRect(x: 16, y: 474, width: 370, height: 102), title: 54)
        func falling(_ progress: CGFloat) -> DeliveredFlightPose {
            DeliveredFlightPose(from: high, to: low, was: .next, becomes: .past, lifted: true, progress: progress)
        }
        XCTAssertEqual(falling(0).frame, high.frame)
        XCTAssertEqual(falling(0).cornerRadius, 24)
        XCTAssertEqual(falling(0).leaving, 0)
        XCTAssertEqual(falling(0).arriving, 112)
        XCTAssertEqual(falling(0).arrival, 0)
        XCTAssertEqual(falling(0).shadow, 0)
        // A card that shrinks is small well before it lands, and casts its shadow on the way.
        XCTAssertEqual(falling(0.72).frame.height, 102, accuracy: 0.001)
        XCTAssertEqual(falling(0.72).frame.minY, 62 + 412 * 0.72, accuracy: 0.001)
        XCTAssertEqual(falling(0.5).shadow, 1)
        XCTAssertEqual(falling(1).frame, low.frame)
        XCTAssertEqual(falling(1).cornerRadius, 16)
        XCTAssertEqual(falling(1).leaving, -112)
        XCTAssertEqual(falling(1).arriving, 0)
        XCTAssertEqual(falling(1).arrival, 1)
        XCTAssertEqual(falling(1).shadow, 0)
        // Past its place, as a spring goes, the card moves on but keeps its shape.
        XCTAssertEqual(falling(1.02).frame.height, 102, accuracy: 0.001)
        XCTAssertGreaterThan(falling(1.02).frame.minY, 474)

        // The card that takes the place grows late, and is not lifted.
        let rising = DeliveredFlightPose(from: low, to: high, was: .onTheWay, becomes: .next, lifted: false, progress: 0.28)
        XCTAssertEqual(rising.frame.height, 102, accuracy: 0.001)
        XCTAssertEqual(rising.arrival, 0)
        XCTAssertEqual(rising.shadow, 0)
        XCTAssertEqual(DeliveredFlightPose(from: low, to: high, was: .onTheWay, becomes: .next, lifted: false, progress: 1).frame, high.frame)
    }

    private func makeParcel(
        id: UUID = UUID(),
        trackingNumber: String = "1Z999AA10123456784",
        events: [TrackingEvent] = []
    ) -> Parcel {
        Parcel(
            id: id,
            trackingNumber: trackingNumber,
            label: "Test parcel",
            carrier: .ups,
            createdAt: "2026-08-01T10:00:00Z",
            expectedDelivery: nil,
            lastStatusText: nil,
            lastSyncedAt: nil,
            syncStatus: .ok,
            syncError: nil,
            trackingURL: nil,
            dpdPostcode: nil,
            carrierData: nil,
            archivedAt: nil,
            notificationsMuted: false,
            trackingEvents: events
        )
    }

    private func event(_ packageID: UUID, _ stage: TrackingStage, _ occurredAt: String) -> TrackingEvent {
        TrackingEvent(
            id: UUID(), packageID: packageID, stage: stage,
            description: "Update", location: nil, occurredAt: occurredAt
        )
    }

    private func widgetParcel(label: String, outForDelivery: Bool) -> DeliveryWidgetParcel {
        DeliveryWidgetParcel(
            id: UUID(),
            label: label,
            carrier: "Swiss Post",
            trackingNumber: "99.34.123456.99999980",
            detail: "Today",
            isOutForDelivery: outForDelivery
        )
    }
}

private extension String {
    func repeated(_ count: Int) -> String {
        String(repeating: self, count: count)
    }
}

@MainActor
private final class StubArchivePanGestureRecognizer: UIPanGestureRecognizer {
    var testTranslation: CGPoint = .zero

    override func translation(in view: UIView?) -> CGPoint { testTranslation }
    override func velocity(in view: UIView?) -> CGPoint { .zero }
}

private final class MemorySessionPersistence: SessionPersistence {
    var data: Data?
    func save<T: Encodable>(_ value: T) throws { data = try JSONEncoder.deliveryTracker.encode(value) }
    func load<T: Decodable>() -> T? { data.flatMap { try? JSONDecoder.deliveryTracker.decode(T.self, from: $0) } }
    func delete() { data = nil }
}

private final class SessionTestURLProtocol: URLProtocol {
    nonisolated(unsafe) static var handler: ((URLRequest, @escaping (Result<(Int, Data), Error>) -> Void) -> Void)?
    /// Headers every response carries besides its content type.
    nonisolated(unsafe) static var headers: [String: String] = [:]
    override class func canInit(with request: URLRequest) -> Bool { request.url?.host == "session.test" }
    override class func canonicalRequest(for request: URLRequest) -> URLRequest { request }
    override func startLoading() {
        Self.handler?(request) { result in
            switch result {
            case .success(let (status, data)):
                self.client?.urlProtocol(self, didReceive: HTTPURLResponse(url: self.request.url!, statusCode: status, httpVersion: nil, headerFields: Self.headers.merging(["Content-Type": "application/json"]) { $1 })!, cacheStoragePolicy: .notAllowed)
                self.client?.urlProtocol(self, didLoad: data)
                self.client?.urlProtocolDidFinishLoading(self)
            case .failure(let error): self.client?.urlProtocol(self, didFailWithError: error)
            }
        }
    }
    override func stopLoading() {}
}

@MainActor
private final class StubAppleSignIn: AppleSignInAuthorizing {
    var nonces: [String] = []
    var result: Result<AppleSignInCredential, Error> = .success(
        AppleSignInCredential(identityToken: "apple-identity-token", authorizationCode: "apple-code", user: "apple-user")
    )
    var beforeReturning: (() -> Void)?
    func credential(nonce: String) async throws -> AppleSignInCredential {
        nonces.append(nonce)
        beforeReturning?()
        return try result.get()
    }
}

final class SessionIsolationTests: XCTestCase {
    @MainActor private var configuration: AppConfiguration {
        AppConfiguration(mode: .api, apiBaseURL: URL(string: "https://session.test")!, supabaseURL: URL(string: "https://session.test")!, supabasePublishableKey: "public", googleAuthEnabled: false, appleAuthEnabled: true, emailOTPEnabled: true, appGroupIdentifier: "session.test")
    }
    private func transport() -> URLSession {
        let config = URLSessionConfiguration.ephemeral
        config.protocolClasses = [SessionTestURLProtocol.self]
        return URLSession(configuration: config)
    }
    private func respond(_ data: Data, status: Int = 200) {
        SessionTestURLProtocol.handler = { _, complete in complete(.success((status, data))) }
    }
    private func offline() {
        SessionTestURLProtocol.handler = { _, complete in complete(.failure(URLError(.notConnectedToInternet))) }
    }
    @MainActor private func authorize(_ session: SessionStore, id: UUID = UUID(), expired: Bool = false, email: String = "test@example.com") async throws {
        let value = AuthSession(accessToken: "token-" + id.uuidString, tokenType: "bearer", expiresIn: 3600, expiresAt: Int(Date().timeIntervalSince1970) + (expired ? -120 : 3600), refreshToken: "refresh", user: AuthUser(id: id, email: email, isAnonymous: false))
        respond(try JSONEncoder.deliveryTracker.encode(value))
        try await session.verifyCode(email: email, code: "123456")
    }
    @MainActor private func store(_ session: SessionStore, _ transport: URLSession) -> ParcelStore {
        let store = ParcelStore(configuration: configuration, session: session, localizer: Localizer(), transport: transport)
        store.setDeliveryLiveActivitiesEnabled(false)
        return store
    }
    private func parcel() -> Parcel {
        Parcel(id: UUID(), trackingNumber: "12345678", label: "Private account A parcel", carrier: .unknown, createdAt: "2026-09-08T00:00:00Z", syncStatus: .ok, notificationsMuted: false)
    }

    /// "Sign in to unlock this" is asked over the guest's parcels: until a sign-in succeeds they stay a guest, so the
    /// widget, the checks and a relaunch keep their parcels; then the session goes straight to the account.
    @MainActor func testAGuestSigningInOverTheirParcelsIsNeverSignedOutOnTheWay() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let suite = "UnlockTests.\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        defer { defaults.removePersistentDomain(forName: suite) }
        let session = SessionStore(configuration: configuration, defaults: defaults, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        await session.bootstrap()
        guard case .welcome = session.state else { return XCTFail("Expected the guest's deliveries") }

        var identities: [String?] = []
        let watching = session.$state.dropFirst().sink { identities.append($0.identity) }
        defer { watching.cancel() }
        let id = UUID()
        try await authorize(session, id: id)

        XCTAssertEqual(identities, [id.uuidString], "From the guest's parcels to the account, nothing between")
        XCTAssertEqual(defaults.string(forKey: "sdt.native.experience.v1"), "account", "A relaunch opens on the account")
    }

    @MainActor func testAppleExchangesIdentityTokenWithOriginalNonceAndPersistsSession() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let apple = StubAppleSignIn()
        let persistence = MemorySessionPersistence()
        let session = SessionStore(configuration: configuration, persistence: persistence, transport: transport, appleSignIn: apple)
        defer { session.forceSignOut() }
        let user = AuthUser(id: UUID(), email: "private@privaterelay.appleid.com", isAnonymous: false)
        let response = try JSONEncoder.deliveryTracker.encode(AuthSession(accessToken: "access", tokenType: "bearer", expiresIn: 3600,
            expiresAt: Int(Date().timeIntervalSince1970) + 3600, refreshToken: "refresh", user: user))
        nonisolated(unsafe) var receivedNonces: [String] = []
        SessionTestURLProtocol.handler = { request, complete in
            XCTAssertEqual(request.url?.path, "/auth/v1/token")
            XCTAssertEqual(request.url?.query, "grant_type=id_token")
            XCTAssertEqual(request.httpMethod, "POST")
            var data = request.httpBody ?? Data()
            if let stream = request.httpBodyStream {
                stream.open()
                var bytes = [UInt8](repeating: 0, count: 1024)
                while stream.hasBytesAvailable {
                    let count = stream.read(&bytes, maxLength: bytes.count)
                    if count <= 0 { break }
                    data.append(contentsOf: bytes.prefix(count))
                }
                stream.close()
            }
            let body = (try? JSONSerialization.jsonObject(with: data)) as? [String: String]
            XCTAssertEqual(body?["provider"], "apple")
            XCTAssertEqual(body?["id_token"], "apple-identity-token")
            receivedNonces.append(body?["nonce"] ?? "")
            complete(.success((200, response)))
        }
        try await session.signInWithApple()
        XCTAssertEqual(session.user, user)
        XCTAssertNotNil(persistence.data)
        try await session.signInWithApple()
        XCTAssertEqual(receivedNonces.count, 2)
        XCTAssertEqual(Set(receivedNonces).count, 2)
        XCTAssertTrue(receivedNonces.allSatisfy { $0.count == 64 })
        XCTAssertEqual(apple.nonces, receivedNonces.map(AppleSignInNonce.digest))
        XCTAssertNotEqual(apple.nonces, receivedNonces)
    }

    @MainActor func testCancelledAppleSignInDoesNotCallTheServerOrSaveASession() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let apple = StubAppleSignIn()
        apple.result = .failure(AuthenticationError.oauthCancelled)
        let persistence = MemorySessionPersistence()
        let session = SessionStore(configuration: configuration, persistence: persistence, transport: transport, appleSignIn: apple)
        SessionTestURLProtocol.handler = { _, complete in
            XCTFail("Cancelled Apple sign-in reached the server")
            complete(.failure(URLError(.cancelled)))
        }
        do { try await session.signInWithApple(); XCTFail("Expected cancellation") }
        catch AuthenticationError.oauthCancelled { }
        XCTAssertNil(session.user)
        XCTAssertNil(persistence.data)
    }

    @MainActor func testAppleAuthorizationCannotRestoreASessionAfterSignOut() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let apple = StubAppleSignIn()
        let persistence = MemorySessionPersistence()
        let session = SessionStore(configuration: configuration, persistence: persistence, transport: transport, appleSignIn: apple)
        try await authorize(session)
        apple.beforeReturning = { [weak session] in session?.forceSignOut() }
        SessionTestURLProtocol.handler = { _, complete in
            XCTFail("Stale Apple sign-in reached the server")
            complete(.failure(URLError(.cancelled)))
        }
        do { try await session.signInWithApple(); XCTFail("Expected cancellation") }
        catch is CancellationError { }
        XCTAssertNil(session.user)
        XCTAssertNil(persistence.data)
    }

    @MainActor private func authorizeWithApple(_ session: SessionStore, id: UUID, appleUser: String = "apple-user") async throws {
        let user = AuthUser(id: id, email: "private@privaterelay.appleid.com", isAnonymous: false,
                            identities: [.init(provider: "apple", identityData: .init(sub: appleUser))])
        respond(try JSONEncoder.deliveryTracker.encode(AuthSession(accessToken: "access", tokenType: "bearer", expiresIn: 3600,
            expiresAt: Int(Date().timeIntervalSince1970) + 3600, refreshToken: "refresh", user: user)))
        try await session.signInWithApple()
    }

    @MainActor func testDeletingAnAppleAccountSignsInWithAppleAgainForARevocationCode() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let apple = StubAppleSignIn()
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport, appleSignIn: apple)
        defer { session.forceSignOut() }
        let id = UUID()
        try await authorizeWithApple(session, id: id)
        let code = try await session.reauthenticateWithAppleBeforeDeletion()
        XCTAssertEqual(code, "apple-code")
        XCTAssertEqual(apple.nonces.count, 2)
        XCTAssertEqual(session.user?.id, id)
    }

    @MainActor func testDeletionRefusesAnotherAppleAccountBeforeReachingTheServer() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let apple = StubAppleSignIn()
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport, appleSignIn: apple)
        defer { session.forceSignOut() }
        try await authorizeWithApple(session, id: UUID(), appleUser: "another-apple-user")
        SessionTestURLProtocol.handler = { _, complete in
            XCTFail("Another Apple Account reached the server")
            complete(.failure(URLError(.cancelled)))
        }
        do { _ = try await session.reauthenticateWithAppleBeforeDeletion(); XCTFail("Expected a refusal") }
        catch AuthenticationError.otherAppleAccount { }
        XCTAssertNotNil(session.user)
    }

    @MainActor func testDeletingAnAccountWithoutAppleDoesNotAskForApple() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let apple = StubAppleSignIn()
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport, appleSignIn: apple)
        defer { session.forceSignOut() }
        let user = AuthUser(id: UUID(), email: "test@example.com", isAnonymous: false, identities: [.init(provider: "email")])
        respond(try JSONEncoder.deliveryTracker.encode(AuthSession(accessToken: "access", tokenType: "bearer", expiresIn: 3600,
            expiresAt: Int(Date().timeIntervalSince1970) + 3600, refreshToken: "refresh", user: user)))
        try await session.verifyCode(email: "test@example.com", code: "123456")
        let code = try await session.reauthenticateWithAppleBeforeDeletion()
        XCTAssertNil(code)
        XCTAssertTrue(apple.nonces.isEmpty)
    }
    @MainActor func testParcelStoreKeepsSessionAliveForDeferredDeliveryCleanup() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        var session: SessionStore? = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        try await authorize(session!)
        weak var retainedSession = session
        defer { retainedSession?.forceSignOut() }
        let store = store(session!, transport)
        session = nil
        // Disabling Live Activities queues cleanup that reads the session later.
        guard retainedSession != nil else { XCTFail("Delivery cleanup lost its session"); return }
        let parcel = parcel()
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel])))
        await store.load()
        XCTAssertEqual(store.parcels.map(\.id), [parcel.id])
        retainedSession?.forceSignOut()
        XCTAssertTrue(store.parcels.isEmpty)
    }
    @MainActor func testAPIClientKeepsSessionAliveWithoutCreatingARetainCycle() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        var session: SessionStore? = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        try await authorize(session!)
        weak var retainedSession = session
        var client: DeliveryAPIClient? = DeliveryAPIClient(configuration: configuration, session: session!, transport: transport)
        session = nil
        guard retainedSession != nil else { XCTFail("API client lost its session"); return }
        let parcel = parcel()
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel])))
        let loaded = try await client!.listPackages()
        XCTAssertEqual(loaded.map(\.id), [parcel.id])
        retainedSession?.forceSignOut()
        client = nil
        XCTAssertNil(retainedSession)
    }
    @MainActor func testAppAttestRefusalOfASignedInRequestKeepsTheSession() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let unsupported = NativeAttestProvider(supported: { false }, generateKey: { "" }, attest: { _, _ in Data() }, assert: { _, _ in Data() })
        let verification = NativeVerification(provider: unsupported, present: { _ in XCTFail("Unexpected browser check"); return "" },
                                              records: [:], save: { _ in })
        let client = DeliveryAPIClient(configuration: configuration, session: session, transport: transport, verification: verification)
        let log = RequestLog()
        SessionTestURLProtocol.headers = ["X-Lookup-Verification": "required"]
        defer { SessionTestURLProtocol.headers = [:] }
        respond(Data(#"{"error":"Please verify this device and try again."}"#.utf8), status: 403, recording: log)
        do {
            _ = try await client.queueRefreshAll()
            XCTFail("Expected the refusal")
        } catch is NativeVerificationFailed {}
        XCTAssertNotNil(session.user)
        XCTAssertEqual(log.requests.map { $0.path }, ["/api/sync", "/api/sync"])
    }
    @MainActor func testForcedSignOutClearsParcelsBeforeNextAccountLoads() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let parcel = parcel()
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel])))
        await store.load()
        store.undoParcel = parcel
        XCTAssertEqual(store.parcels.map(\.id), [parcel.id])
        session.forceSignOut()
        XCTAssertTrue(store.parcels.isEmpty)
        XCTAssertNil(store.undoParcel)
        let nextUser = UUID()
        try await authorize(session, id: nextUser)
        offline()
        await store.load()
        XCTAssertEqual(session.user?.id, nextUser)
        XCTAssertTrue(store.parcels.isEmpty)
    }
    @MainActor func testSuccessfulDeleteIsReflectedInOfflineCache() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let parcel = parcel()
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel])))
        await store.load()
        respond(Data("{\"ok\":true}".utf8))
        try await store.permanentlyDelete(parcel)
        offline()
        let relaunchedStore = self.store(session, transport)
        await relaunchedStore.load()
        XCTAssertTrue(relaunchedStore.parcels.isEmpty)
        XCTAssertTrue(relaunchedStore.usingCachedData)
    }
    @MainActor func testSuccessfulRenamePersistsForOfflineLaunch() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        var parcel = parcel()
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel])))
        await store.load()
        parcel.label = "Updated label"
        respond(try JSONEncoder.deliveryTracker.encode(parcel))
        try await store.rename(parcel, label: parcel.label)
        offline()
        let relaunchedStore = self.store(session, transport)
        await relaunchedStore.load()
        XCTAssertEqual(relaunchedStore.parcels.first?.label, "Updated label")
    }
    @MainActor func testOfflineAndServerErrorPreserveExpiredRefreshableSession() async throws {
        for serverError in [false, true] {
            let transport = transport()
            defer { transport.invalidateAndCancel() }
            let persistence = MemorySessionPersistence()
            let session = SessionStore(configuration: configuration, persistence: persistence, transport: transport)
            try await authorize(session, expired: true)
            if serverError { respond(Data("{\"message\":\"Unavailable\"}".utf8), status: 503) } else { offline() }
            let relaunched = SessionStore(configuration: configuration, persistence: persistence, transport: transport)
            await relaunched.bootstrap()
            XCTAssertEqual(relaunched.user?.id, session.user?.id)
            XCTAssertNotNil(persistence.data)
            relaunched.forceSignOut()
        }
    }
    /// Waits for work the store starts on its own, such as asking `/maintenance.json`, for up to two seconds.
    @MainActor private func waitUntil(_ condition: () -> Bool) async {
        for _ in 0..<100 where !condition() { try? await Task.sleep(for: .milliseconds(20)) }
    }

    /// A host restart: Cloudflare answers 530 in the server's place. The account's saved list stays, with when it was
    /// last read, under a calm notice instead of an error, the sign-in stays, and the notice goes once the server answers.
    @MainActor func testTheServerDownForMaintenanceKeepsTheSavedListAndTheSignInAndSaysSoCalmly() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let persistence = MemorySessionPersistence()
        let session = SessionStore(configuration: configuration, persistence: persistence, transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let parcel = parcel()
        let list = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel]))
        respond(list)
        await store.load()
        let readAt = try XCTUnwrap(store.listReadAt)

        // /maintenance.json too: before the edge is in front, nothing says whether the maintenance was announced.
        nonisolated(unsafe) var asked: [String] = []
        SessionTestURLProtocol.handler = { request, complete in
            asked.append(request.url?.path ?? "")
            complete(.success((530, Data("error code: 1033".utf8))))
        }
        let relaunched = self.store(session, transport)
        await relaunched.load()
        XCTAssertEqual(relaunched.parcels.map(\.id), [parcel.id])
        XCTAssertEqual(relaunched.serverDown, .unannounced)
        XCTAssertNil(relaunched.errorMessage)
        XCTAssertFalse(relaunched.authenticationRequired)
        XCTAssertEqual(try XCTUnwrap(relaunched.listReadAt).timeIntervalSince1970, readAt.timeIntervalSince1970, accuracy: 1)
        XCTAssertNotNil(session.user)
        XCTAssertNotNil(persistence.data)
        await waitUntil { asked.contains("/maintenance.json") }
        XCTAssertTrue(asked.contains("/maintenance.json"))

        respond(list)
        await relaunched.load()
        XCTAssertNil(relaunched.serverDown)
        XCTAssertEqual(relaunched.parcels.map(\.id), [parcel.id])
    }

    /// The sign-in server down for an announced maintenance, as the edge answers for it: an expired session's refresh
    /// and a refused token's both keep the session, and the list says when Peek should be back, not to sign in again.
    @MainActor func testASignInServerDownForMaintenanceKeepsTheSessionWithoutAskingToSignInAgain() async throws {
        let maintenance = Data(#"{"error":"Peek is down for maintenance","maintenance":{"planned":true,"mode":"soft","since":null,"backAround":"2026-11-17T15:00:00Z"}}"#.utf8)
        let announced = MaintenanceNotice(planned: true, backAround: DateParser.date("2026-11-17T15:00:00Z"))
        SessionTestURLProtocol.headers = ["Retry-After": "120"]
        defer { SessionTestURLProtocol.headers = [:] }
        for expired in [true, false] {
            let transport = transport()
            defer { transport.invalidateAndCancel() }
            let persistence = MemorySessionPersistence()
            let session = SessionStore(configuration: configuration, persistence: persistence, transport: transport)
            defer { session.forceSignOut() }
            try await authorize(session, expired: expired)
            let store = store(session, transport)
            SessionTestURLProtocol.handler = { request, complete in
                // An unexpired token the API refuses is refreshed, from a sign-in server that is down.
                if request.url?.path == "/api/packages" { complete(.success((401, Data(#"{"error":"Unauthorized"}"#.utf8)))) }
                else { complete(.success((503, maintenance))) }
            }
            await store.load()
            XCTAssertEqual(store.serverDown, announced, "expired: \(expired)")
            XCTAssertNil(store.errorMessage)
            XCTAssertFalse(store.authenticationRequired)
            XCTAssertNotNil(session.user)
            XCTAssertNotNil(persistence.data)
        }
    }

    @MainActor func testInvalidRefreshTokenClearsSavedSession() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let persistence = MemorySessionPersistence()
        let session = SessionStore(configuration: configuration, persistence: persistence, transport: transport)
        try await authorize(session, expired: true)
        respond(Data("{\"code\":\"refresh_token_not_found\",\"message\":\"Invalid refresh token\"}".utf8), status: 400)
        let relaunched = SessionStore(configuration: configuration, persistence: persistence, transport: transport)
        await relaunched.bootstrap()
        XCTAssertNil(relaunched.user)
        XCTAssertNil(persistence.data)
    }
    @MainActor func testLateUnauthorizedResponseCannotSignOutNextAccount() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let started = expectation(description: "Old account request started")
        nonisolated(unsafe) var complete: ((Result<(Int, Data), Error>) -> Void)?
        SessionTestURLProtocol.handler = { request, callback in
            if request.url?.path == "/api/packages", request.httpMethod == "GET" {
                complete = callback
                started.fulfill()
            } else { callback(.success((200, Data("{}".utf8)))) }
        }
        let pending = Task { await store.load() }
        await fulfillment(of: [started], timeout: 2)
        session.forceSignOut()
        let nextUser = UUID()
        try await authorize(session, id: nextUser)
        complete?(.success((401, Data("{}".utf8))))
        await pending.value
        XCTAssertEqual(session.user?.id, nextUser)
        XCTAssertTrue(store.parcels.isEmpty)
        XCTAssertNil(store.errorMessage)
    }
    @MainActor func testAListReadWithNothingOnScreenSaysSo() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let data = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel()]))
        nonisolated(unsafe) var queries: [String?] = []
        SessionTestURLProtocol.handler = { request, callback in
            if request.url?.path == "/api/packages", request.httpMethod == "GET" { queries.append(request.url?.query) }
            callback(.success((200, data)))
        }

        await store.load()
        await store.load(hidden: true)

        XCTAssertEqual(queries, ["includeArchived=true", "includeArchived=true&hidden=true"])
        XCTAssertEqual(store.parcels.count, 1)
    }
    @MainActor func testListStartedBeforeDeleteCannotRestoreDeletedParcel() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let parcel = parcel()
        let data = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel]))
        respond(data)
        await store.load()
        let started = expectation(description: "Stale collection request started")
        nonisolated(unsafe) var complete: ((Result<(Int, Data), Error>) -> Void)?
        SessionTestURLProtocol.handler = { request, callback in
            if request.url?.path == "/api/packages", request.httpMethod == "GET" {
                complete = callback
                started.fulfill()
            } else { callback(.success((200, Data("{}".utf8)))) }
        }
        let pending = Task { await store.load() }
        await fulfillment(of: [started], timeout: 2)
        respond(Data("{\"ok\":true}".utf8))
        try await store.permanentlyDelete(parcel)
        complete?(.success((200, data)))
        await pending.value
        XCTAssertTrue(store.parcels.isEmpty)
        offline()
        let relaunchedStore = self.store(session, transport)
        await relaunchedStore.load()
        XCTAssertTrue(relaunchedStore.parcels.isEmpty)
    }
}

extension SessionIsolationTests {
    @MainActor func testLiveActivityRevocationSurvivesOfflineSignOutAndRestart() async throws {
        let persistence = MemorySessionPersistence()
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let original = LiveActivityRevocations(configuration: configuration, transport: transport, persistence: persistence)
        let installationID = UUID()
        let old = try original.registration(ownerID: UUID(), installationID: installationID)
        try original.queue()
        offline()
        await original.drain()
        let saved: [LiveActivityRevocations.Registration]? = persistence.load()
        XCTAssertEqual(saved?.first?.pending, true)

        let restarted = LiveActivityRevocations(configuration: configuration, transport: transport, persistence: persistence)
        let new = try restarted.registration(ownerID: UUID(), installationID: installationID)
        XCTAssertNotEqual(new.revocationToken, old.revocationToken)
        SessionTestURLProtocol.handler = { request, complete in
            XCTAssertEqual(request.url?.path, "/api/live-activities/revoke")
            XCTAssertNil(request.value(forHTTPHeaderField: "Authorization"))
            complete(.success((200, Data("{\"ok\":true}".utf8))))
        }
        await restarted.drain()
        let remaining: [LiveActivityRevocations.Registration]? = persistence.load()
        XCTAssertEqual(remaining, [new])
    }

    @MainActor func testLiveActivitiesStayOffWhenTheyWereSwitchedOnBefore() throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let shared = try XCTUnwrap(DeliveryWidgetSharedStore(appGroupIdentifier: configuration.appGroupIdentifier, fallbackToStandard: true))
        shared.setLiveActivitiesEnabled(true)
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }

        let store = ParcelStore(configuration: configuration, session: session, localizer: Localizer(), transport: transport)

        XCTAssertFalse(store.deliveryLiveActivitiesEnabled)
        XCTAssertFalse(shared.liveActivitiesEnabled)
    }

    @MainActor func testAuthOutageDoesNotForceSignOut() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        try await authorize(session)
        let store = store(session, transport)
        respond(Data("{\"error\":\"Authentication temporarily unavailable\"}".utf8), status: 503)
        await store.load()
        XCTAssertNotNil(session.user)
        XCTAssertFalse(store.authenticationRequired)
    }

    func testEqualTimeEventsPreferDeliveryProgressRegardlessOfUUIDOrTimeZone() {
        var value = parcel()
        value.trackingEvents = [
            TrackingEvent(id: UUID(uuidString: "ffffffff-ffff-4fff-8fff-ffffffffffff")!, packageID: value.id, stage: .inTransit, description: "Transit", occurredAt: "2026-09-08T12:00:00+02:00"),
            TrackingEvent(id: UUID(uuidString: "00000000-0000-4000-8000-000000000001")!, packageID: value.id, stage: .delivered, description: "Delivered", occurredAt: "2026-09-08T10:00:00Z")
        ]
        XCTAssertEqual(value.currentStage, .delivered)
    }
}

extension SessionIsolationTests {
    /// After a flight to another time zone, the widgets' days change at the new zone's midnights, not the old one's.
    @MainActor func testANewTimeZoneWordsTheWidgetsForItsMidnights() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let shared = try XCTUnwrap(DeliveryWidgetSharedStore(appGroupIdentifier: configuration.appGroupIdentifier, fallbackToStandard: true))
        let home = TimeZone.current
        // Half an hour off every whole-hour zone, so no midnight of one is a midnight of the other.
        let away = try XCTUnwrap(TimeZone(identifier: home.secondsFromGMT() == 19_800 ? "Pacific/Honolulu" : "Asia/Kolkata"))
        func startsADay(_ date: Date, in zone: TimeZone) -> Bool {
            var calendar = Calendar(identifier: .gregorian)
            calendar.timeZone = zone
            return calendar.startOfDay(for: date) == date
        }
        let now = Date()
        let day = DateFormatter()
        day.locale = Locale(identifier: "en_US_POSIX")
        day.calendar = Calendar(identifier: .gregorian)
        day.dateFormat = "yyyy-MM-dd"
        var expected = parcel()
        expected.trackingEvents = [TrackingEvent(id: UUID(), packageID: expected.id, stage: .inTransit, description: "Transit",
                                                 occurredAt: DateParser.isoString(now.addingTimeInterval(-3_600)))]
        expected.expectedDelivery = day.string(from: now.addingTimeInterval(3 * 86_400))
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [expected])))
        await store.load()
        let before = try XCTUnwrap(shared.snapshot?.parcels.first?.later).map(\.from)
        XCTAssertTrue(before.allSatisfy { startsADay($0, in: home) })

        NSTimeZone.default = away
        defer { NSTimeZone.default = home }
        NotificationCenter.default.post(name: .NSSystemTimeZoneDidChange, object: nil)
        let heard = expectation(description: "the store heard the change")
        DispatchQueue.main.async { heard.fulfill() }
        await fulfillment(of: [heard], timeout: 5)

        let after = try XCTUnwrap(shared.snapshot?.parcels.first?.later).map(\.from)
        XCTAssertFalse(after.isEmpty)
        XCTAssertTrue(after.allSatisfy { startsADay($0, in: away) })
        XCTAssertFalse(after.contains { startsADay($0, in: home) })
    }

    @MainActor func testUnchangedPollKeepsTheListWithoutRepublishing() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel()])))
        await store.load()
        var republished = 0
        let observation = store.$parcels.dropFirst().sink { _ in republished += 1 }
        defer { observation.cancel() }
        await store.load()
        XCTAssertEqual(republished, 0)
        XCTAssertEqual(store.parcels.count, 1)
    }

    @MainActor func testReopenedAccountShowsItsSavedParcelsBeforeTheServerAnswers() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let persistence = MemorySessionPersistence()
        let session = SessionStore(configuration: configuration, persistence: persistence, transport: transport)
        try await authorize(session)
        let saved = parcel()
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [saved])))
        await store(session, transport).load()

        let relaunched = SessionStore(configuration: configuration, persistence: persistence, transport: transport)
        defer { relaunched.forceSignOut() }
        let relaunchedStore = store(relaunched, transport)
        offline()
        await relaunched.bootstrap()
        XCTAssertEqual(relaunchedStore.parcels.map(\.id), [saved.id])
        XCTAssertFalse(relaunchedStore.loading)
        await relaunchedStore.load()
        XCTAssertEqual(relaunchedStore.parcels.map(\.id), [saved.id])
        XCTAssertTrue(relaunchedStore.usingCachedData)
    }

    @MainActor func testArchivedParcelLeavesTheListBeforeTheServerAnswers() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let parcel = parcel()
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel])))
        await store.load()
        for accepted in [false, true] {
            let started = expectation(description: "Archive request started")
            nonisolated(unsafe) var complete: ((Result<(Int, Data), Error>) -> Void)?
            SessionTestURLProtocol.handler = { request, callback in
                if request.httpMethod == "DELETE" {
                    complete = callback
                    started.fulfill()
                } else { callback(.success((200, Data("{}".utf8)))) }
            }
            let archiving = Task { try await store.archive(parcel) }
            await fulfillment(of: [started], timeout: 2)
            XCTAssertEqual(store.parcels.first?.isArchived, true)
            XCTAssertNil(store.undoParcel)
            if accepted {
                complete?(.success((200, Data("{\"ok\":true}".utf8))))
                try await archiving.value
                XCTAssertEqual(store.parcels.first?.isArchived, true)
                XCTAssertEqual(store.undoParcel?.id, parcel.id)
            } else {
                complete?(.success((500, Data("{\"error\":\"Archive unavailable\"}".utf8))))
                do { try await archiving.value; XCTFail("Expected the refusal") } catch {}
                XCTAssertEqual(store.parcels.first?.isArchived, false)
            }
        }
    }

    @MainActor func testRefreshReturnsOnceQueuedAndReportsCompletion() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let job = UUID()
        let parcel = parcel()
        let queued = try JSONEncoder.deliveryTracker.encode(QueueResponse(queued: true, pending: 1, jobIDs: [job]))
        let jobs = try JSONEncoder.deliveryTracker.encode(SyncJobListResponse(jobs: [
            SyncJobResponse(id: job, status: .succeeded, requestedAt: "2026-09-25T10:00:00Z"),
        ]))
        let list = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel]))
        let jobsRequested = expectation(description: "Job status requested")
        nonisolated(unsafe) var completeJobs: ((Result<(Int, Data), Error>) -> Void)?
        SessionTestURLProtocol.handler = { request, callback in
            switch (request.httpMethod, request.url?.path) {
            case ("POST", "/api/sync"): callback(.success((202, queued)))
            case ("GET", "/api/sync/jobs"):
                completeJobs = callback
                jobsRequested.fulfill()
            default: callback(.success((200, list)))
            }
        }
        let start = try await store.refreshAll()
        XCTAssertEqual(start, .queued)
        XCTAssertTrue(store.refreshing)
        await fulfillment(of: [jobsRequested], timeout: 2)
        XCTAssertTrue(store.refreshing)
        let finished = expectation(description: "Refresh finished")
        let observation = store.$refreshOutcome.dropFirst().sink { if $0 != nil { finished.fulfill() } }
        defer { observation.cancel() }
        completeJobs?(.success((200, jobs)))
        await fulfillment(of: [finished], timeout: 2)
        XCTAssertFalse(store.refreshing)
        // The parcel was not on the list before the check.
        XCTAssertEqual(store.refreshOutcome?.result, .updated)
        XCTAssertEqual(store.parcels.map(\.id), [parcel.id])
    }

    /// Starts a check of every parcel and waits for the outcome the store reports once it is done.
    @MainActor private func refreshOutcome(_ store: ParcelStore) async throws -> ParcelStore.RefreshResult? {
        let previous = store.refreshOutcome?.id
        let finished = expectation(description: "Refresh finished")
        finished.assertForOverFulfill = false
        let observation = store.$refreshOutcome.sink { if let outcome = $0, outcome.id != previous { finished.fulfill() } }
        defer { observation.cancel() }
        let start = try await store.refreshAll()
        XCTAssertEqual(start, .queued)
        await fulfillment(of: [finished], timeout: 2)
        return store.refreshOutcome?.result
    }

    /// The checks can succeed just as the connection drops: the list read after them failing is a failed refresh,
    /// not "No new updates" beside the warning.
    @MainActor func testRefreshSaysUnchangedOnlyAfterReadingTheListAndFailsWhenTheReadFails() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let job = UUID()
        let list = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel()]))
        respond(list)
        await store.load()
        let queued = try JSONEncoder.deliveryTracker.encode(QueueResponse(queued: true, pending: 1, jobIDs: [job]))
        let jobs = try JSONEncoder.deliveryTracker.encode(SyncJobListResponse(jobs: [
            SyncJobResponse(id: job, status: .succeeded, requestedAt: "2026-09-25T10:00:00Z"),
        ]))
        nonisolated(unsafe) var listAnswer: Result<(Int, Data), Error> = .success((200, list))
        SessionTestURLProtocol.handler = { request, callback in
            switch (request.httpMethod, request.url?.path) {
            case ("POST", "/api/sync"): callback(.success((202, queued)))
            case ("GET", "/api/sync/jobs"): callback(.success((200, jobs)))
            default: callback(listAnswer)
            }
        }
        // The same list, read again, has nothing new.
        let unchanged = try await refreshOutcome(store)
        XCTAssertEqual(unchanged, .unchanged)
        XCTAssertNil(store.errorMessage)

        listAnswer = .failure(URLError(.notConnectedToInternet))
        let failed = try await refreshOutcome(store)
        let message = try XCTUnwrap(store.errorMessage)
        XCTAssertEqual(failed, .failed(message))
        XCTAssertFalse(store.refreshing)
    }

    /// A read that lands while the refresh's own is on its way has the list, so the refresh's answer is set aside:
    /// it was not compared, so it is not "No new updates".
    @MainActor func testRefreshWhoseReadIsSetAsideIsStillChecking() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let job = UUID()
        let list = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel()]))
        respond(list)
        await store.load()
        let queued = try JSONEncoder.deliveryTracker.encode(QueueResponse(queued: true, pending: 1, jobIDs: [job]))
        let jobs = try JSONEncoder.deliveryTracker.encode(SyncJobListResponse(jobs: [
            SyncJobResponse(id: job, status: .succeeded, requestedAt: "2026-09-25T10:00:00Z"),
        ]))
        let refreshRead = expectation(description: "The refresh read the list")
        nonisolated(unsafe) var completeRefreshRead: ((Result<(Int, Data), Error>) -> Void)?
        nonisolated(unsafe) var reads = 0
        SessionTestURLProtocol.handler = { request, callback in
            switch (request.httpMethod, request.url?.path) {
            case ("POST", "/api/sync"): callback(.success((202, queued)))
            case ("GET", "/api/sync/jobs"): callback(.success((200, jobs)))
            default:
                reads += 1
                if reads == 1 {
                    completeRefreshRead = callback
                    refreshRead.fulfill()
                } else { callback(.success((200, list))) }
            }
        }
        let finished = expectation(description: "Refresh finished")
        let observation = store.$refreshOutcome.dropFirst().sink { if $0 != nil { finished.fulfill() } }
        defer { observation.cancel() }
        let start = try await store.refreshAll()
        XCTAssertEqual(start, .queued)
        await fulfillment(of: [refreshRead], timeout: 2)
        // A poll reads the list meanwhile and lands first.
        await store.load()
        completeRefreshRead?(.success((200, list)))
        await fulfillment(of: [finished], timeout: 2)
        XCTAssertEqual(store.refreshOutcome?.result, .stillChecking)
        XCTAssertFalse(store.refreshing)
        XCTAssertNil(store.errorMessage)
    }

    func testRefreshReportsNewTrackingOnlyWhenTheTimelineStatusOrDeliveryDateChanged() {
        let checked = parcel()
        var rechecked = checked
        rechecked.lastSyncedAt = "2026-09-25T10:05:00Z"
        rechecked.syncStatus = .ok
        XCTAssertFalse(Parcel.trackingChanged(from: [checked], to: [rechecked]))

        var status = checked
        status.lastStatusText = "Sorted at the depot"
        XCTAssertTrue(Parcel.trackingChanged(from: [checked], to: [status]))
        var delivery = checked
        delivery.expectedDelivery = "2026-09-27"
        XCTAssertTrue(Parcel.trackingChanged(from: [checked], to: [delivery]))
        var scanned = checked
        scanned.trackingEvents.append(TrackingEvent(
            id: UUID(), packageID: checked.id, stage: .inTransit,
            description: "Sorted at the depot", occurredAt: "2026-09-25T10:00:00Z"
        ))
        XCTAssertTrue(Parcel.trackingChanged(from: [checked], to: [scanned]))

        // A delivery partner's parcel continues the one it replaced.
        var handoff = checked
        handoff.id = UUID()
        handoff.carrierData = CarrierData(originalPackageID: checked.id)
        XCTAssertFalse(Parcel.trackingChanged(from: [checked], to: [handoff]))
    }
}

/// What the requests of a test carried, in the order they were made.
/** A response held back until the test lets it go. */
private final class LockedCompletion: @unchecked Sendable {
    private let lock = NSLock()
    private var completion: ((Result<(Int, Data), Error>) -> Void)?
    func keep(_ completion: @escaping (Result<(Int, Data), Error>) -> Void) { lock.withLock { self.completion = completion } }
    func complete(_ result: Result<(Int, Data), Error>) { lock.withLock { completion }?(result) }
}

private final class RequestLog: @unchecked Sendable {
    var requests: [(method: String, path: String, body: [String: Any])] = []

    func record(_ request: URLRequest) {
        var data = request.httpBody ?? Data()
        if let stream = request.httpBodyStream {
            stream.open()
            defer { stream.close() }
            var bytes = [UInt8](repeating: 0, count: 1024)
            while stream.hasBytesAvailable {
                let count = stream.read(&bytes, maxLength: bytes.count)
                if count <= 0 { break }
                data.append(contentsOf: bytes.prefix(count))
            }
        }
        let body = (try? JSONSerialization.jsonObject(with: data)) as? [String: Any] ?? [:]
        requests.append((request.httpMethod ?? "", request.url?.path ?? "", body))
    }
}

extension SessionIsolationTests {
    private func respond(_ data: Data, status: Int = 200, recording log: RequestLog) {
        SessionTestURLProtocol.handler = { request, complete in
            log.record(request)
            complete(.success((status, data)))
        }
    }

    /// An account the server can email, which has never chosen.
    /// With the clock and units the test store's app shows, so reading them sends nothing back.
    @MainActor private var emailPreferences: NotificationPreferences {
        let shown = Localizer()
        return NotificationPreferences(
            enabledStages: NotificationPreset.important.stages, quietHoursStart: "22:00", quietHoursEnd: "07:00",
            timezone: "Pacific/Auckland", emailOnDelivery: nil, emailAvailable: true,
            clock: shown.use24HourTime ? .twentyFour : .twelve, distance: shown.distanceUnit == .miles ? .mi : .km
        )
    }

    /// A parcel as the server answers it, with its two mutes.
    private func row(_ parcel: Parcel, muted: Bool, emailMuted: Bool) -> Data {
        Data("""
        {"id":"\(parcel.id.uuidString)","tracking_number":"\(parcel.trackingNumber)","label":"\(parcel.label)",
         "carrier":"\(parcel.carrier.rawValue)","created_at":"\(parcel.createdAt)","sync_status":"ok",
         "notifications_muted":\(muted),"email_muted":\(emailMuted),"tracking_events":[]}
        """.utf8)
    }

    @MainActor func testEmailSwitchSendsOnlyTheChoiceAndShowsTheServersAnswer() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session, email: "alex@example.com")
        let store = store(session, transport)
        XCTAssertNil(store.deliveryEmail)
        let saved = emailPreferences
        respond(try JSONEncoder.deliveryTracker.encode(saved))
        await store.loadNotificationPreferences()
        let offered = try XCTUnwrap(store.deliveryEmail)
        XCTAssertEqual(offered.address, "alex@example.com")
        XCTAssertNil(offered.choice)

        var answer = saved
        answer.emailOnDelivery = true
        let log = RequestLog()
        respond(try JSONEncoder.deliveryTracker.encode(answer), recording: log)
        try await store.setEmailOnDelivery(true)
        XCTAssertEqual(log.requests.map(\.method), ["PATCH"])
        XCTAssertEqual(log.requests.first?.path, "/api/push/preferences")
        let sent = try XCTUnwrap(log.requests.first?.body)
        // Only the choice and the zone go: events chosen elsewhere since this device read them stay.
        XCTAssertEqual(Set(sent.keys), ["timezone", "emailOnDelivery"])
        XCTAssertEqual(sent["emailOnDelivery"] as? Bool, true)
        XCTAssertEqual(sent["timezone"] as? String, TimeZone.current.identifier)
        XCTAssertEqual(store.deliveryEmail?.isOn, true)

        // Saving a preset afterwards sends the events alone and clears the retired quiet hours: an email switched off
        // from its link since this device read it stays off.
        var draft = NotificationPreferencesDraft(preferences: store.notificationPreferences)
        draft.preset = .deliveryDay
        var presetAnswer = draft.preferences(timezone: "Europe/Zurich")
        presetAnswer.emailOnDelivery = true
        presetAnswer.emailAvailable = true
        respond(try JSONEncoder.deliveryTracker.encode(presetAnswer), recording: log)
        try await store.saveNotificationPreferences(draft.preferences(timezone: "Europe/Zurich"))
        let preset = try XCTUnwrap(log.requests.last?.body)
        XCTAssertEqual(Set(preset.keys), ["enabledStages", "quietHoursStart", "quietHoursEnd", "timezone"])
        XCTAssertTrue(preset["quietHoursStart"] is NSNull)
        XCTAssertTrue(preset["quietHoursEnd"] is NSNull)
        XCTAssertEqual(store.notificationPreferences?.enabledStages, NotificationPreset.deliveryDay.stages)
        XCTAssertEqual(store.deliveryEmail?.isOn, true)

        // A refusal leaves what was saved.
        respond(Data("{\"error\":\"This account cannot be emailed\"}".utf8), status: 409)
        do { try await store.setEmailOnDelivery(false); XCTFail("Expected the refusal") } catch {}
        XCTAssertEqual(store.deliveryEmail?.isOn, true)

        // The state is the server's answer, whatever was asked.
        presetAnswer.emailOnDelivery = false
        respond(try JSONEncoder.deliveryTracker.encode(presetAnswer))
        try await store.setEmailOnDelivery(true)
        XCTAssertEqual(try XCTUnwrap(store.deliveryEmail).choice, false)

        // Once the server cannot email the account, there is nothing to switch.
        presetAnswer.emailAvailable = false
        respond(try JSONEncoder.deliveryTracker.encode(presetAnswer))
        await store.loadNotificationPreferences()
        XCTAssertNil(store.deliveryEmail)
        let silent = RequestLog()
        respond(Data("{}".utf8), recording: silent)
        try await store.setEmailOnDelivery(true)
        XCTAssertTrue(silent.requests.isEmpty)
    }

    @MainActor func testAReadThatBeganBeforeTheEmailWasSwitchedCannotUndoIt() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session, email: "alex@example.com")
        let store = store(session, transport)
        let saved = emailPreferences
        let stale = try JSONEncoder.deliveryTracker.encode(saved)
        respond(stale)
        await store.loadNotificationPreferences()
        var answer = saved
        answer.emailOnDelivery = true
        let answered = try JSONEncoder.deliveryTracker.encode(answer)
        let started = expectation(description: "Read started")
        nonisolated(unsafe) var completeRead: ((Result<(Int, Data), Error>) -> Void)?
        SessionTestURLProtocol.handler = { request, callback in
            if request.httpMethod == "GET" {
                completeRead = callback
                started.fulfill()
            } else { callback(.success((200, answered))) }
        }
        let reading = Task { await store.loadNotificationPreferences() }
        await fulfillment(of: [started], timeout: 2)
        try await store.setEmailOnDelivery(true)
        completeRead?(.success((200, stale)))
        await reading.value
        XCTAssertEqual(store.deliveryEmail?.isOn, true)
    }

    /// A parcel whose latest scan arrived an hour after it was last opened.
    private func scannedParcel() -> Parcel {
        var parcel = parcel()
        parcel.newsAt = "2026-11-15T10:00:00.25+00:00"
        parcel.seenAt = "2026-11-15T09:00:00+00:00"
        return parcel
    }

    @MainActor func testAScanAfterTheLastOpenIsMarkedAndOpeningTheParcelClearsItForTheAccount() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        var scanned = scannedParcel()
        // Opened since its last scan, as every parcel is when the app starts with the parcels it had.
        var quiet = parcel()
        quiet.newsAt = "2026-11-15T08:00:00+00:00"
        quiet.seenAt = "2026-11-15T09:00:00+00:00"
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [scanned, quiet])))
        await store.load()
        XCTAssertEqual(store.parcels.filter(\.hasNewUpdate).map(\.id), [scanned.id])

        // Opening a parcel with no news tells the server nothing.
        let log = RequestLog()
        respond(Data("{\"seen_at\":\"2026-11-15T10:05:00.5+00:00\"}".utf8), recording: log)
        await store.markSeen(quiet.id, newsAt: quiet.newsAt)
        XCTAssertTrue(log.requests.isEmpty)

        await store.markSeen(scanned.id, newsAt: scanned.newsAt)
        XCTAssertEqual(log.requests.map(\.method), ["POST"])
        XCTAssertEqual(log.requests.first?.path, "/api/packages/\(scanned.id.uuidString)/seen")
        XCTAssertEqual(log.requests.first?.body["news_at"] as? String, "2026-11-15T10:00:00.25+00:00",
                       "Seen as of the news its page showed")
        XCTAssertEqual(store.parcels.first { $0.id == scanned.id }?.seenAt, "2026-11-15T10:05:00.5+00:00")
        XCTAssertFalse(store.parcels.contains(where: \.hasNewUpdate))

        // The account's other device reads its list next: the moment the server kept clears the mark there too.
        scanned.seenAt = "2026-11-15T10:05:00.5+00:00"
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [scanned, quiet])))
        let other = self.store(session, transport)
        await other.load()
        XCTAssertEqual(other.parcels.count, 2)
        XCTAssertFalse(other.parcels.contains(where: \.hasNewUpdate))

        // The next scan is news again, on both.
        scanned.newsAt = "2026-11-15T12:00:00+00:00"
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [scanned, quiet])))
        await store.load()
        XCTAssertEqual(store.parcels.filter(\.hasNewUpdate).map(\.id), [scanned.id])
    }

    /// The Parcels widget marks what the list marks: a scan after the last open, until the parcel is opened, and nothing
    /// while Settings has the mark off.
    @MainActor func testTheParcelsWidgetMarksWhatTheListMarks() async throws {
        forgetMarkNewUpdatesChoice()
        defer { forgetMarkNewUpdatesChoice() }
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let shared = try XCTUnwrap(DeliveryWidgetSharedStore(appGroupIdentifier: configuration.appGroupIdentifier, fallbackToStandard: true))
        let scanned = scannedParcel()
        var quiet = parcel()
        quiet.newsAt = "2026-11-15T08:00:00+00:00"
        quiet.seenAt = "2026-11-15T09:00:00+00:00"
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [scanned, quiet])))
        await store.load()
        func marked() -> [UUID] { (shared.snapshot?.parcels ?? []).filter { $0.newUpdate == true }.map(\.id) }
        XCTAssertEqual(shared.snapshot?.parcels.count, 2)
        XCTAssertEqual(marked(), [scanned.id])
        XCTAssertTrue(shared.snapshot?.parcels.allSatisfy { $0.newUpdate == true || $0.newUpdate == nil } ?? false)

        // Switched off, the dot leaves the widget at once; what was seen is kept, so switched on again it comes back.
        respond(Data("{}".utf8))
        store.setMarkNewUpdates(false)
        XCTAssertEqual(marked(), [])
        store.setMarkNewUpdates(true)
        XCTAssertEqual(marked(), [scanned.id])

        // Opening the parcel clears it in the widget too.
        respond(Data("{\"seen_at\":\"2026-11-15T10:05:00.5+00:00\"}".utf8))
        await store.markSeen(scanned.id, newsAt: scanned.newsAt)
        XCTAssertEqual(marked(), [])
        XCTAssertEqual(shared.snapshot?.parcels.count, 2)
    }

    @MainActor func testAMarkTheServerCouldNotTakeGoesAtOnceAndComesBackWithTheNextRead() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let scanned = scannedParcel()
        let list = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [scanned]))
        respond(list)
        await store.load()

        offline()
        await store.markSeen(scanned.id, newsAt: scanned.newsAt)
        XCTAssertEqual(store.parcels.first?.hasNewUpdate, false, "The reader has seen it, whatever the network says")
        XCTAssertNil(store.errorMessage, "Opening a parcel is nothing to report on")

        respond(list)
        await store.load()
        XCTAssertEqual(store.parcels.first?.hasNewUpdate, true)
    }

    @MainActor func testAReadOfTheListUnderWayWhenTheParcelIsOpenedLandsSeenAsLateAsItWasMarked() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let scanned = scannedParcel()
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [scanned])))
        await store.load()

        // The read under way brings a scan stored after the one the page shows, and the parcel as it was before it opened.
        var newer = scanned
        newer.newsAt = "2026-11-15T10:30:00+00:00"
        let answer = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [newer]))
        let kept = Data("{\"seen_at\":\"2026-11-15T10:00:00.250999+00:00\"}".utf8)
        let started = expectation(description: "Read started")
        nonisolated(unsafe) var completeRead: ((Result<(Int, Data), Error>) -> Void)?
        SessionTestURLProtocol.handler = { request, complete in
            if request.httpMethod == "GET", request.url?.path == "/api/packages" {
                completeRead = complete
                started.fulfill()
            } else { complete(.success((200, kept))) }
        }
        let reading = Task { await store.load() }
        await fulfillment(of: [started], timeout: 2)
        await store.markSeen(scanned.id, newsAt: scanned.newsAt)
        completeRead?(.success((200, answer)))
        await reading.value

        let shown = try XCTUnwrap(store.parcels.first)
        XCTAssertEqual(shown.newsAt, newer.newsAt, "The read landed")
        XCTAssertEqual(shown.seenAt, "2026-11-15T10:00:00.250999+00:00", "Seen as late as this device marked it")
        XCTAssertTrue(shown.hasNewUpdate, "The scan stored since the page showed its news is new")

        // A read that brings the server's mark takes over from this device's.
        var caughtUp = newer
        caughtUp.seenAt = "2026-11-15T10:00:00.250999+00:00"
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [caughtUp])))
        await store.load()
        caughtUp.seenAt = "2026-11-15T09:30:00+00:00"
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [caughtUp])))
        await store.load()
        XCTAssertEqual(store.parcels.first?.seenAt, "2026-11-15T09:30:00+00:00", "Once caught up, the server's word is the list's")
    }

    @MainActor func testTheMarkForNewUpdatesIsOnUntilSwitchedOffAndFollowsTheAccount() async throws {
        forgetMarkNewUpdatesChoice()
        defer { forgetMarkNewUpdatesChoice() }
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session, email: "alex@example.com")
        let store = store(session, transport)
        let scanned = scannedParcel()
        XCTAssertTrue(store.markNewUpdates)
        XCTAssertTrue(scanned.showsNewUpdate(marking: store.markNewUpdates))

        var saved = emailPreferences
        saved.markNewUpdates = true
        respond(try JSONEncoder.deliveryTracker.encode(saved))
        await store.loadNotificationPreferences()
        XCTAssertTrue(store.markNewUpdates)

        // Switched off here: gone from the list at once, and saved for the account with the rest as stored.
        var answer = saved
        answer.markNewUpdates = false
        let log = RequestLog()
        respond(try JSONEncoder.deliveryTracker.encode(answer), recording: log)
        store.setMarkNewUpdates(false)
        XCTAssertFalse(store.markNewUpdates)
        XCTAssertFalse(scanned.showsNewUpdate(marking: store.markNewUpdates))
        for _ in 0..<300 where store.notificationPreferences?.markNewUpdates != false { try await Task.sleep(for: .milliseconds(10)) }
        XCTAssertEqual(store.notificationPreferences?.markNewUpdates, false)
        XCTAssertEqual(log.requests.map(\.method), ["PATCH"])
        XCTAssertEqual(log.requests.first?.path, "/api/push/preferences")
        let sent = try XCTUnwrap(log.requests.first?.body)
        XCTAssertEqual(Set(sent.keys), ["markNewUpdates"])
        XCTAssertEqual(sent["markNewUpdates"] as? Bool, false)
        // The next launch starts from it, before the account's preferences are read.
        XCTAssertFalse(self.store(session, transport).markNewUpdates)

        // Another save says nothing of the mark, so the server keeps the account's choice.
        var emailed = answer
        emailed.emailOnDelivery = true
        respond(try JSONEncoder.deliveryTracker.encode(emailed), recording: log)
        try await store.setEmailOnDelivery(true)
        XCTAssertNil(log.requests.last?.body["markNewUpdates"])
        XCTAssertFalse(store.markNewUpdates)

        // Switched back on from another device: the next read of the preferences brings it here.
        respond(try JSONEncoder.deliveryTracker.encode(saved))
        await store.loadNotificationPreferences()
        XCTAssertTrue(store.markNewUpdates)
        XCTAssertTrue(scanned.showsNewUpdate(marking: store.markNewUpdates))
    }

    @MainActor func testWithoutAnAccountTheMarkSwitchStaysOnThisDevice() async throws {
        forgetMarkNewUpdatesChoice()
        defer { forgetMarkNewUpdatesChoice() }
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        let store = store(session, transport)
        let log = RequestLog()
        respond(Data("{}".utf8), recording: log)

        store.setMarkNewUpdates(false)

        XCTAssertFalse(store.markNewUpdates)
        XCTAssertFalse(self.store(session, transport).markNewUpdates)
        try await Task.sleep(for: .milliseconds(100))
        XCTAssertTrue(log.requests.isEmpty)
        store.setMarkNewUpdates(true)
        XCTAssertTrue(self.store(session, transport).markNewUpdates)
    }

    @MainActor func testAMarkChoiceTheServerCouldNotTakeStandsUntilItIsSaved() async throws {
        forgetMarkNewUpdatesChoice()
        defer { forgetMarkNewUpdatesChoice() }
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session, email: "alex@example.com")
        let store = store(session, transport)
        var on = emailPreferences
        on.markNewUpdates = true
        respond(try JSONEncoder.deliveryTracker.encode(on))
        await store.loadNotificationPreferences()

        // Switched off while the server cannot take it: the next read, which still says on, leaves it off and saves it.
        offline()
        store.setMarkNewUpdates(false)
        // The failed save has to be over before the server answers again, however slow the simulator.
        for _ in 0..<300 where store.markNewUpdatesSaving > 0 { try await Task.sleep(for: .milliseconds(10)) }
        XCTAssertEqual(store.markNewUpdatesSaving, 0)
        var off = on
        off.markNewUpdates = false
        let log = RequestLog()
        SessionTestURLProtocol.handler = { request, complete in
            log.record(request)
            complete(.success((200, try! JSONEncoder.deliveryTracker.encode(request.httpMethod == "PATCH" ? off : on))))
        }
        await store.loadNotificationPreferences()
        XCTAssertFalse(store.markNewUpdates)
        for _ in 0..<300 where store.notificationPreferences?.markNewUpdates != false { try await Task.sleep(for: .milliseconds(10)) }
        XCTAssertEqual(log.requests.map(\.method), ["GET", "PATCH"])
        XCTAssertEqual(log.requests.last?.body["markNewUpdates"] as? Bool, false)
        XCTAssertFalse(UserDefaults.standard.bool(forKey: ParcelStore.markNewUpdatesUnsavedKey))

        // Saved, it follows the account again.
        respond(try JSONEncoder.deliveryTracker.encode(on))
        await store.loadNotificationPreferences()
        XCTAssertTrue(store.markNewUpdates)
    }

    @MainActor func testAMarkChoiceWhoseSaveFailsLetsTheReadAlreadyUnderWayLand() async throws {
        forgetMarkNewUpdatesChoice()
        defer { forgetMarkNewUpdatesChoice() }
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session, email: "alex@example.com")
        let store = store(session, transport)
        // The launch's read is under way when the switch is flipped, and the save's own read of the preferences fails.
        let stored = try JSONEncoder.deliveryTracker.encode(emailPreferences)
        let held = LockedCompletion()
        let asked = expectation(description: "The launch's read was sent")
        let log = RequestLog()
        SessionTestURLProtocol.handler = { request, complete in
            log.record(request)
            if log.requests.count == 1 { held.keep(complete); asked.fulfill() } else { complete(.failure(URLError(.notConnectedToInternet))) }
        }
        let reading = Task { await store.loadNotificationPreferences() }
        await fulfillment(of: [asked], timeout: 2)
        store.setMarkNewUpdates(false)
        for _ in 0..<300 where log.requests.count < 2 { try await Task.sleep(for: .milliseconds(10)) }
        try await Task.sleep(for: .milliseconds(50))
        held.complete(.success((200, stored)))
        await reading.value
        XCTAssertNotNil(store.notificationPreferences, "The launch's read lands")
        XCTAssertFalse(store.markNewUpdates, "The choice stands until it is saved")
    }

    @MainActor func testTheAccountLearnsHowThisDeviceWritesATimeAndADistanceForTheDeliveryEmail() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session, email: "alex@example.com")
        let suite = "units-\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        defer { defaults.removePersistentDomain(forName: suite) }
        let localizer = Localizer(defaults: defaults, systemUses24HourTime: { true }, systemDistanceUnit: { .kilometres })
        let store = ParcelStore(configuration: configuration, session: session, localizer: localizer, transport: transport)
        store.setDeliveryLiveActivitiesEnabled(false)

        // Read without any: this device's go to the account, the rest as stored and the email choice left out.
        var none = emailPreferences
        none.clock = nil
        none.distance = nil
        none.emailOnDelivery = true
        var kept = none
        kept.clock = .twentyFour
        kept.distance = .km
        let log = RequestLog()
        SessionTestURLProtocol.handler = { request, complete in
            log.record(request)
            complete(.success((200, try! JSONEncoder.deliveryTracker.encode(request.httpMethod == "PATCH" ? kept : none))))
        }
        await store.loadNotificationPreferences()
        for _ in 0..<300 where store.notificationPreferences?.clock != .twentyFour { try await Task.sleep(for: .milliseconds(10)) }
        XCTAssertEqual(log.requests.map(\.method), ["GET", "PATCH"])
        let sent = try XCTUnwrap(log.requests.last?.body)
        XCTAssertEqual(Set(sent.keys), ["clock", "distance"])
        XCTAssertEqual(sent["clock"] as? String, "24")
        XCTAssertEqual(sent["distance"] as? String, "km")

        // Chosen in Settings: sent at once, both in one save.
        var twelve = kept
        twelve.clock = .twelve
        twelve.distance = .mi
        respond(try JSONEncoder.deliveryTracker.encode(twelve), recording: log)
        localizer.clock = .twelveHour
        localizer.distance = .miles
        for _ in 0..<300 where store.notificationPreferences?.distance != .mi { try await Task.sleep(for: .milliseconds(10)) }
        XCTAssertEqual(store.notificationPreferences?.clock, .twelve)
        XCTAssertEqual(log.requests.map(\.method), ["GET", "PATCH", "PATCH"])
        XCTAssertEqual(log.requests.last?.body["clock"] as? String, "12")
        XCTAssertEqual(log.requests.last?.body["distance"] as? String, "mi")

        // Another save leaves them out, so the server keeps the account's.
        var emailed = twelve
        emailed.emailOnDelivery = false
        respond(try JSONEncoder.deliveryTracker.encode(emailed), recording: log)
        try await store.setEmailOnDelivery(false)
        XCTAssertNil(log.requests.last?.body["clock"])
        XCTAssertNil(log.requests.last?.body["distance"])

        // Changed since on another device: the next read brings it, and this device sends its own again.
        SessionTestURLProtocol.handler = { request, complete in
            log.record(request)
            complete(.success((200, try! JSONEncoder.deliveryTracker.encode(request.httpMethod == "PATCH" ? emailed : kept))))
        }
        await store.loadNotificationPreferences()
        for _ in 0..<300 where store.notificationPreferences?.clock != .twelve { try await Task.sleep(for: .milliseconds(10)) }
        XCTAssertEqual(log.requests.suffix(2).map(\.method), ["GET", "PATCH"])
        XCTAssertEqual(log.requests.last?.body["clock"] as? String, "12")

        // A server that does not keep them is not asked again and again.
        let before = log.requests.count
        respond(try JSONEncoder.deliveryTracker.encode(none), recording: log)
        await store.loadNotificationPreferences()
        try await Task.sleep(for: .milliseconds(300))
        XCTAssertEqual(log.requests.dropFirst(before).map(\.method), ["GET", "PATCH"])
    }

    @MainActor func testAMarkChoiceMadeWithoutAnAccountGoesToTheAccountSignedInTo() async throws {
        forgetMarkNewUpdatesChoice()
        defer { forgetMarkNewUpdatesChoice() }
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        store(session, transport).setMarkNewUpdates(false)

        try await authorize(session, email: "alex@example.com")
        let store = store(session, transport)
        var on = emailPreferences
        on.markNewUpdates = true
        var off = on
        off.markNewUpdates = false
        let log = RequestLog()
        SessionTestURLProtocol.handler = { request, complete in
            log.record(request)
            complete(.success((200, try! JSONEncoder.deliveryTracker.encode(request.httpMethod == "PATCH" ? off : on))))
        }
        await store.loadNotificationPreferences()
        XCTAssertFalse(store.markNewUpdates)
        for _ in 0..<300 where store.notificationPreferences?.markNewUpdates != false { try await Task.sleep(for: .milliseconds(10)) }
        XCTAssertEqual(log.requests.last?.method, "PATCH")
        XCTAssertEqual(log.requests.last?.body["markNewUpdates"] as? Bool, false)
    }

    private func forgetMarkNewUpdatesChoice() {
        UserDefaults.standard.removeObject(forKey: ParcelStore.markNewUpdatesOffKey)
        UserDefaults.standard.removeObject(forKey: ParcelStore.markNewUpdatesUnsavedKey)
    }

    @MainActor func testReturningToTheAppReadsTheEmailChoiceAgain() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session, email: "alex@example.com")
        let store = store(session, transport)
        var saved = emailPreferences
        saved.emailOnDelivery = true
        respond(try JSONEncoder.deliveryTracker.encode(saved))
        await store.loadNotificationPreferences()
        XCTAssertEqual(store.deliveryEmail?.isOn, true)

        // Meanwhile the email was switched off from a link in an email.
        saved.emailOnDelivery = false
        let switchedOff = try JSONEncoder.deliveryTracker.encode(saved)
        let list = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: []))
        let log = RequestLog()
        SessionTestURLProtocol.handler = { request, complete in
            log.record(request)
            complete(.success((200, request.url?.path == "/api/push/preferences" ? switchedOff : list)))
        }
        let read = expectation(description: "Choice read again")
        let observation = store.$notificationPreferences.dropFirst().sink { if $0?.emailOnDelivery == false { read.fulfill() } }
        defer { observation.cancel() }
        // The launch loads the preferences itself: only a return reads them again.
        store.setActive(true)
        store.setActive(false)
        store.setActive(true)
        await fulfillment(of: [read], timeout: 5)
        XCTAssertEqual(try XCTUnwrap(store.deliveryEmail).choice, false)
        XCTAssertEqual(log.requests.filter { $0.path == "/api/push/preferences" }.map(\.method), ["GET"])
    }

    @MainActor func testAParcelsAlertsAreSavedOneFieldAtATimeAndKeptForAnOfflineLaunch() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session, email: "alex@example.com")
        let store = store(session, transport)
        let parcel = parcel()
        respond(try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [parcel])))
        await store.load()
        XCTAssertNil(store.parcels.first?.emailMuted)

        let log = RequestLog()
        respond(row(parcel, muted: false, emailMuted: true), recording: log)
        try await store.setEmailMuted(parcel, muted: true)
        XCTAssertEqual(log.requests.map(\.method), ["PATCH"])
        XCTAssertEqual(log.requests.first?.path, "/api/packages/\(parcel.id.uuidString)/notifications")
        XCTAssertEqual(log.requests.first?.body as NSDictionary?, ["emailMuted": true])
        XCTAssertEqual(store.parcels.first?.emailMuted, true)
        XCTAssertEqual(store.parcels.first?.notificationsMuted, false)

        respond(row(parcel, muted: true, emailMuted: true), recording: log)
        try await store.setMuted(parcel, muted: true)
        XCTAssertEqual(log.requests.last?.body as NSDictionary?, ["muted": true])
        XCTAssertEqual(store.parcels.first?.allAlertsMuted, true)

        // A refusal leaves the parcel as it was.
        respond(Data("{\"error\":\"Alerts unavailable\"}".utf8), status: 502)
        do { try await store.setEmailMuted(parcel, muted: false); XCTFail("Expected the refusal") } catch {}
        XCTAssertEqual(store.parcels.first?.emailMuted, true)

        offline()
        let relaunchedStore = self.store(session, transport)
        await relaunchedStore.load()
        XCTAssertEqual(relaunchedStore.parcels.first?.emailMuted, true)
        XCTAssertEqual(relaunchedStore.parcels.first?.notificationsMuted, true)
    }

    @MainActor func testDemoHasNoDeliveryEmail() async throws {
        let suite = "demo-email-store-tests-\(UUID().uuidString)"
        let defaults = UserDefaults(suiteName: suite)!
        defer { defaults.removePersistentDomain(forName: suite) }
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, defaults: defaults, persistence: MemorySessionPersistence(), transport: transport)
        session.enterDemo()
        let store = store(session, transport)
        let silent = RequestLog()
        respond(try JSONEncoder.deliveryTracker.encode(emailPreferences), recording: silent)
        await store.loadNotificationPreferences()
        XCTAssertNotNil(store.notificationPreferences)
        XCTAssertNil(store.deliveryEmail)
        try await store.setEmailOnDelivery(true)
        XCTAssertNil(store.notificationPreferences?.emailOnDelivery)
        XCTAssertTrue(silent.requests.isEmpty)
    }
}

extension SessionIsolationTests {
    @MainActor func testRefusedRefreshTokenSignsOutInEitherErrorShape() async throws {
        // Supabase Auth's answer with X-Supabase-Api-Version, and its older one, whose `code` is the HTTP status.
        let answers = [
            #"{"code":"refresh_token_not_found","message":"Invalid Refresh Token: Refresh Token Not Found"}"#,
            #"{"code":400,"error_code":"refresh_token_not_found","msg":"Invalid Refresh Token: Refresh Token Not Found"}"#,
        ]
        for answer in answers {
            let transport = transport()
            defer { transport.invalidateAndCancel() }
            let persistence = MemorySessionPersistence()
            let session = SessionStore(configuration: configuration, persistence: persistence, transport: transport)
            try await authorize(session, expired: true)
            nonisolated(unsafe) var versions: [String?] = []
            SessionTestURLProtocol.handler = { request, complete in
                versions.append(request.value(forHTTPHeaderField: "X-Supabase-Api-Version"))
                complete(.success((400, Data(answer.utf8))))
            }
            do { _ = try await session.accessToken(); XCTFail("Expected the refusal") } catch AuthenticationError.missingSession {}
            XCTAssertNil(session.user)
            XCTAssertNil(persistence.data)
            XCTAssertEqual(versions, ["2024-01-01"])
        }
    }

    /// A firewall's page in front of the API or of Supabase Auth says nothing about the session: nobody is signed out.
    @MainActor func testAFirewallsForbiddenPageKeepsTheSession() async throws {
        let page = Data("<!doctype html><title>Attention Required! | Cloudflare</title>".utf8)
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let persistence = MemorySessionPersistence()
        let session = SessionStore(configuration: configuration, persistence: persistence, transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        nonisolated(unsafe) var paths: [String] = []
        let forbidden: (URLRequest, @escaping (Result<(Int, Data), Error>) -> Void) -> Void = { request, complete in
            paths.append(request.url?.path ?? "")
            complete(.success((403, page)))
        }
        SessionTestURLProtocol.handler = forbidden

        await store.load()

        XCTAssertNotNil(session.user)
        XCTAssertNotNil(persistence.data)
        XCTAssertFalse(store.authenticationRequired)
        XCTAssertEqual(paths, ["/api/packages"], "Not a refused token, so nothing to refresh")

        let expired = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { expired.forceSignOut() }
        try await authorize(expired, expired: true)
        SessionTestURLProtocol.handler = forbidden
        do { _ = try await expired.accessToken(); XCTFail("Expected the failure") } catch AuthenticationError.requestFailed(let status) {
            XCTAssertEqual(status, 403)
        }
        XCTAssertNotNil(expired.user)
    }

    @MainActor func testAuthErrorInTheOlderShapeKeepsItsMessage() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        respond(Data(#"{"code":403,"error_code":"otp_expired","msg":"Token has expired or is invalid"}"#.utf8), status: 403)
        do {
            try await session.verifyCode(email: "test@example.com", code: "123456")
            XCTFail("Expected the refusal")
        } catch AuthenticationError.server(let message) {
            XCTAssertEqual(message, "Token has expired or is invalid")
        }
    }

    @MainActor func testRefreshBegunBeforeTheAccountChangedDoesNotHoldUpLaterOnes() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let id = UUID()
        try await authorize(session, id: id, expired: true)
        let started = expectation(description: "Refresh started")
        nonisolated(unsafe) var complete: ((Result<(Int, Data), Error>) -> Void)?
        SessionTestURLProtocol.handler = { _, callback in
            complete = callback
            started.fulfill()
        }
        let stale = Task { try await session.accessToken() }
        await fulfillment(of: [started], timeout: 2)
        // The account changes while the refresh is under way, without the session being cleared first.
        session.showSignIn()
        try await authorize(session, id: id, expired: true)
        let fresh = AuthSession(accessToken: "fresh", tokenType: "bearer", expiresIn: 3600, expiresAt: Int(Date().timeIntervalSince1970) + 3600,
                                refreshToken: "next", user: AuthUser(id: id, email: "test@example.com", isAnonymous: false))
        let freshData = try JSONEncoder.deliveryTracker.encode(fresh)
        complete?(.success((200, freshData)))
        do { _ = try await stale.value; XCTFail("Expected the stale refresh to give way") } catch is CancellationError {}
        respond(freshData)
        let token = try await session.accessToken()
        XCTAssertEqual(token, "fresh")
    }

    @MainActor func testSigningOutWithAnExpiredTokenRefreshesItForTheServer() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        let id = UUID()
        try await authorize(session, id: id, expired: true)
        let fresh = try JSONEncoder.deliveryTracker.encode(AuthSession(
            accessToken: "fresh", tokenType: "bearer", expiresIn: 3600, expiresAt: Int(Date().timeIntervalSince1970) + 3600,
            refreshToken: "next", user: AuthUser(id: id, email: "test@example.com", isAnonymous: false)))
        nonisolated(unsafe) var calls: [String] = []
        SessionTestURLProtocol.handler = { request, complete in
            calls.append("\(request.url!.path)?\(request.url!.query ?? "") \(request.value(forHTTPHeaderField: "Authorization") ?? "-")")
            if request.url?.query == "grant_type=refresh_token" { complete(.success((200, fresh))) }
            else { complete(.success((204, Data()))) }
        }
        try await session.signOut()
        XCTAssertNil(session.user)
        XCTAssertEqual(calls, ["/auth/v1/token?grant_type=refresh_token -", "/auth/v1/logout?scope=local Bearer fresh"])
    }

    @MainActor func testASessionWhoseExpiryAnIntCannotHoldIsKept() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let user = AuthUser(id: UUID(), email: "test@example.com", isAnonymous: false)
        respond(try JSONEncoder.deliveryTracker.encode(AuthSession(accessToken: "access", tokenType: "bearer", expiresIn: .max,
                                                                   expiresAt: nil, refreshToken: "refresh", user: user)))
        try await session.verifyCode(email: "test@example.com", code: "123456")
        XCTAssertEqual(session.user?.id, user.id)
        let token = try await session.accessToken()
        XCTAssertEqual(token, "access")
    }

    @MainActor func testLateNotificationAnswersForThePreviousAccountShowNoError() async throws {
        for path in ["/api/push/preferences", "/api/push/devices"] {
            let transport = transport()
            defer { transport.invalidateAndCancel() }
            let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
            defer { session.forceSignOut() }
            try await authorize(session)
            let store = store(session, transport)
            let started = expectation(description: "The first account's request started")
            nonisolated(unsafe) var complete: ((Result<(Int, Data), Error>) -> Void)?
            SessionTestURLProtocol.handler = { request, callback in
                if request.url?.path == path {
                    complete = callback
                    started.fulfill()
                } else { callback(.success((200, Data("{}".utf8)))) }
            }
            // The token goes only while this device's notifications are on and belong to the account signed in.
            for key in ["sdt.notificationsDeviceOptOut", "sdt.notificationsOwner.v1"] { UserDefaults.standard.removeObject(forKey: key) }
            let pending = Task {
                if path == "/api/push/preferences" { await store.loadNotificationPreferences() }
                else { await store.forwardNativePushToken("synthetic-token", language: .en) }
            }
            await fulfillment(of: [started], timeout: 2)
            session.forceSignOut()
            try await authorize(session, id: UUID())
            complete?(.success((500, Data(#"{"error":"Unavailable"}"#.utf8))))
            await pending.value
            XCTAssertNil(store.notificationError, path)
        }
    }

    @MainActor func testSigningBackInBringsTheDevicesNotificationsBackAndAnotherAccountIsAsked() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let owner = UUID()
        try await authorize(session, id: owner)
        let store = store(session, transport)
        for key in ["sdt.notificationsDeviceOptOut", "sdt.notificationsOwner.v1"] { UserDefaults.standard.removeObject(forKey: key) }
        nonisolated(unsafe) var registrations = 0
        let answer: (URLRequest, @escaping (Result<(Int, Data), Error>) -> Void) -> Void = { request, complete in
            if request.httpMethod == "POST", request.url?.path == "/api/push/devices" { registrations += 1 }
            complete(.success((200, Data(#"{"ok":true,"testSent":false}"#.utf8))))
        }
        SessionTestURLProtocol.handler = answer
        await store.forwardNativePushToken("synthetic-token", language: .en)
        XCTAssertEqual(registrations, 1)
        // Signing out, or a session that ends, takes the token away without saying no for the person.
        session.forceSignOut()
        XCTAssertFalse(UserDefaults.standard.bool(forKey: "sdt.notificationsDeviceOptOut"))
        try await authorize(session)
        SessionTestURLProtocol.handler = answer
        await store.forwardNativePushToken("synthetic-token", language: .en)
        XCTAssertEqual(registrations, 1, "Another account is asked first")
        XCTAssertFalse(store.notificationsEnabledOnDevice)
        session.forceSignOut()
        try await authorize(session, id: owner)
        SessionTestURLProtocol.handler = answer
        await store.forwardNativePushToken("synthetic-token", language: .en)
        XCTAssertEqual(registrations, 2, "The same account gets its notifications back")
        XCTAssertTrue(store.notificationsEnabledOnDevice)
    }

    @MainActor func testATokenTheAccountSigningOutCouldNotRemoveIsTakenOffByTheNextAccount() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let keys = ["sdt.notificationsDeviceOptOut", "sdt.notificationsOwner.v1", "sdt.notificationsRevocationPending.v1"]
        for key in keys { UserDefaults.standard.removeObject(forKey: key) }
        defer { for key in keys { UserDefaults.standard.removeObject(forKey: key) } }
        let owner = UUID()
        nonisolated(unsafe) var calls: [String] = []
        nonisolated(unsafe) var reachable = true
        let answer: (URLRequest, @escaping (Result<(Int, Data), Error>) -> Void) -> Void = { request, complete in
            guard request.url?.path == "/api/push/devices" else { return complete(.success((200, Data("{}".utf8)))) }
            let bearer = request.value(forHTTPHeaderField: "Authorization") ?? ""
            calls.append("\(request.httpMethod ?? "") \(bearer.hasSuffix(owner.uuidString) ? "owner" : "other")")
            if reachable { complete(.success((200, Data(#"{"ok":true,"testSent":false}"#.utf8)))) }
            else { complete(.failure(URLError(.notConnectedToInternet))) }
        }
        try await authorize(session, id: owner)
        let store = store(session, transport)
        SessionTestURLProtocol.handler = answer
        await store.forwardNativePushToken("synthetic-token", language: .en)
        // The owner signs out offline: its token stays on the service, so the device keeps it to take off.
        reachable = false
        await store.revokeNativePushToken("synthetic-token")
        session.forceSignOut()
        reachable = true
        let waiting = await store.settleNativePushRevocation()
        XCTAssertFalse(waiting, "Without an account, it waits")

        // Another account takes it over, which moves it there, then takes it off; only then may the device register again.
        try await authorize(session)
        SessionTestURLProtocol.handler = answer
        calls = []
        let settled = await store.settleNativePushRevocation()
        XCTAssertTrue(settled)
        XCTAssertEqual(calls, ["POST other", "DELETE other"])
        calls = []
        let again = await store.settleNativePushRevocation()
        XCTAssertTrue(again)
        XCTAssertEqual(calls, [], "Once off, nothing is left to do")

        // The owner signing out offline and in again has its notifications back as its own: nothing to take off.
        session.forceSignOut()
        try await authorize(session, id: owner)
        SessionTestURLProtocol.handler = answer
        reachable = false
        await store.revokeNativePushToken("synthetic-token")
        session.forceSignOut()
        try await authorize(session, id: owner)
        SessionTestURLProtocol.handler = answer
        reachable = true
        calls = []
        let own = await store.settleNativePushRevocation()
        XCTAssertTrue(own)
        XCTAssertEqual(calls, [])
    }

    @MainActor func testATokenLeftOnTheServiceByAnAccountTheAppSignedOutIsTakenOffByTheNextAccount() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let keys = ["sdt.notificationsDeviceOptOut", "sdt.notificationsOwner.v1", "sdt.notificationsRevocationPending.v1",
                    "sdt.notificationsForwardedToken.v1", "sdt.notificationsNativePushRegistered.v1"]
        for key in keys { UserDefaults.standard.removeObject(forKey: key) }
        defer { for key in keys { UserDefaults.standard.removeObject(forKey: key) } }
        let owner = UUID()
        nonisolated(unsafe) var calls: [String] = []
        let answer: (URLRequest, @escaping (Result<(Int, Data), Error>) -> Void) -> Void = { request, complete in
            guard request.url?.path == "/api/push/devices" else { return complete(.success((200, Data("{}".utf8)))) }
            let bearer = request.value(forHTTPHeaderField: "Authorization") ?? ""
            calls.append("\(request.httpMethod ?? "") \(bearer.hasSuffix(owner.uuidString) ? "owner" : "other")")
            complete(.success((200, Data(#"{"ok":true,"testSent":false}"#.utf8))))
        }
        try await authorize(session, id: owner)
        let store = store(session, transport)
        SessionTestURLProtocol.handler = answer
        await store.forwardNativePushToken("synthetic-token", language: .en)

        // Its session refused, the account is signed out by the app, which takes nothing off: the token is kept.
        calls = []
        session.forceSignOut()
        XCTAssertEqual(calls, [])
        XCTAssertEqual(UserDefaults.standard.string(forKey: NotificationDeviceKeys.revocation), "synthetic-token")

        // The next account takes it over and off before the device registers with Apple again.
        try await authorize(session)
        SessionTestURLProtocol.handler = answer
        let settled = await store.settleNativePushRevocation()
        XCTAssertTrue(settled)
        XCTAssertEqual(calls, ["POST other", "DELETE other"])
        session.forceSignOut()

        // A Sign out before Apple gave this launch its token takes off the one the account was given.
        try await authorize(session, id: owner)
        SessionTestURLProtocol.handler = answer
        await store.forwardNativePushToken("synthetic-token", language: .en)
        calls = []
        try await store.signOut()
        XCTAssertEqual(calls, ["DELETE owner"])
        XCTAssertNil(UserDefaults.standard.string(forKey: NotificationDeviceKeys.revocation))
    }

    @MainActor func testFailedAccountDeletionKeepsWatchingTheNextChecks() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        // An account without Apple deletes without signing in again.
        let user = AuthUser(id: UUID(), email: "test@example.com", isAnonymous: false, identities: [.init(provider: "email")])
        respond(try JSONEncoder.deliveryTracker.encode(AuthSession(accessToken: "access", tokenType: "bearer", expiresIn: 3600,
            expiresAt: Int(Date().timeIntervalSince1970) + 3600, refreshToken: "refresh", user: user)))
        try await session.verifyCode(email: "test@example.com", code: "123456")
        let store = store(session, transport)
        let added = try JSONEncoder.deliveryTracker.encode(CreatePackageResponse(package: parcel(), jobIDs: [UUID()]))
        nonisolated(unsafe) var watched: [XCTestExpectation] = []
        SessionTestURLProtocol.handler = { request, callback in
            switch (request.httpMethod, request.url?.path) {
            case ("POST", "/api/packages"): callback(.success((201, added)))
            // The checks never finish: only whether they are watched counts.
            case ("GET", "/api/sync/jobs"): if !watched.isEmpty { watched.removeFirst().fulfill() }
            case ("DELETE", "/api/account"): callback(.success((500, Data(#"{"error":"Unavailable"}"#.utf8))))
            default: callback(.success((200, Data("{}".utf8))))
            }
        }
        for attempt in 0..<2 {
            let watching = expectation(description: "Checks watched \(attempt)")
            watched = [watching]
            _ = try await store.add(trackingNumber: "TESTPARCEL123789", label: "", carrier: .dhl, trackingURL: nil, dpdPostcode: nil)
            await fulfillment(of: [watching], timeout: 3)
            if attempt == 0 {
                do { try await store.deleteAccount(confirmation: "DELETE"); XCTFail("Expected the refusal") } catch {}
                XCTAssertNotNil(session.user)
            }
        }
    }

    /// A refused add says why in the Add sheet: the day's allowance until tomorrow, any other limit a moment. The list
    /// raises no notice of its own.
    @MainActor func testRefusedAddSaysWhyWithoutAListNotice() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        respond(Data(#"{"error":"Today’s tracking allowance is used up. Try again tomorrow.","scope":"daily"}"#.utf8), status: 429)
        do {
            _ = try await store.add(trackingNumber: "TESTPARCEL123789", label: "", carrier: .dhl, trackingURL: nil, dpdPostcode: nil)
            XCTFail("Expected the refusal")
        } catch DeliveryAPIError.allowanceUsed {}
        respond(Data(#"{"error":"Too many requests"}"#.utf8), status: 429)
        do {
            _ = try await store.add(trackingNumber: "TESTPARCEL123789", label: "", carrier: .dhl, trackingURL: nil, dpdPostcode: nil)
            XCTFail("Expected the refusal")
        } catch DeliveryAPIError.rateLimited {}
        XCTAssertNil(store.errorMessage)
        XCTAssertNotNil(session.user)
    }

    /// The first check of a parcel just added can fail, as when its carrier can't be reached: the list is read again, so the
    /// parcel says why, and raises no notice of its own.
    @MainActor func testFailedFirstCheckOfAnAddedParcelRereadsTheListWithoutAListNotice() async throws {
        let transport = transport()
        defer { transport.invalidateAndCancel() }
        let session = SessionStore(configuration: configuration, persistence: MemorySessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        try await authorize(session)
        let store = store(session, transport)
        let job = UUID()
        let added = parcel()
        let created = try JSONEncoder.deliveryTracker.encode(CreatePackageResponse(package: added, jobIDs: [job]))
        let jobs = try JSONEncoder.deliveryTracker.encode(SyncJobListResponse(jobs: [
            SyncJobResponse(id: job, status: .succeeded, requestedAt: "2026-09-25T10:00:00Z", result: SyncSummary(errors: 1)),
        ]))
        var checked = added
        checked.syncStatus = .error
        checked.syncError = "carrier:transport"
        let list = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: [checked]))
        let reread = expectation(description: "The list was read again after the check")
        reread.assertForOverFulfill = false
        nonisolated(unsafe) var checkAnswered = false
        SessionTestURLProtocol.handler = { request, callback in
            switch (request.httpMethod, request.url?.path) {
            case ("POST", "/api/packages"): callback(.success((201, created)))
            case ("GET", "/api/sync/jobs"):
                checkAnswered = true
                callback(.success((200, jobs)))
            case ("GET", "/api/packages"):
                callback(.success((200, list)))
                if checkAnswered { reread.fulfill() }
            default: callback(.success((200, Data("{}".utf8))))
            }
        }
        _ = try await store.add(trackingNumber: "TESTPARCEL123789", label: "", carrier: .dhl, trackingURL: nil, dpdPostcode: nil)
        await fulfillment(of: [reread], timeout: 3)
        let deadline = Date().addingTimeInterval(2)
        while store.parcels.first?.syncError == nil, Date() < deadline { try await Task.sleep(for: .milliseconds(20)) }
        XCTAssertEqual(store.parcels.map(\.syncError), ["carrier:transport"])
        XCTAssertNil(store.errorMessage)
    }
}

extension ParcelLogicTests {
    func testDeliveryListLayoutFeaturesOneArrivalAndKeepsIssuesOnTheirOwnCards() {
        let now = DateParser.date("2026-09-09T12:00:00Z")!
        let parcels = DemoRepository.seed(now: now)
        let layout = DeliveryListLayout(parcels: parcels, query: "", status: .all, carrier: nil, sort: .standard, featuresNext: true, now: now)
        let next = try? XCTUnwrap(layout.next)
        XCTAssertEqual(next?.label, "New trainers 👟")
        XCTAssertEqual(Set(layout.active.map(\.id)), Set(parcels.filter(\.isActive).map(\.id)))
        XCTAssertEqual(Set(layout.attention.map(\.id) + layout.remaining.map(\.id) + [next?.id].compactMap { $0 }),
                       Set(layout.active.map(\.id)))
        XCTAssertFalse(layout.attention.contains { $0.id == next?.id } || layout.remaining.contains { $0.id == next?.id })
        XCTAssertTrue(layout.attention.allSatisfy { $0.attention(now: now) != nil })
        XCTAssertTrue(layout.remaining.allSatisfy { $0.attention(now: now) == nil })
        XCTAssertEqual(layout.sections.map(\.kind), [.delivered, .archived])
        let searched = DeliveryListLayout(parcels: parcels, query: "trainers", status: .all, carrier: nil, sort: .standard, featuresNext: false, now: now)
        XCTAssertNil(searched.next)
        XCTAssertEqual(searched.visible.map(\.label), ["New trainers 👟"])
    }

    func testLatestEventFollowsAChangedHistory() {
        let now = DateParser.date("2026-09-09T12:00:00Z")!
        var parcel = DemoRepository.seed(now: now).first { $0.label == "New trainers 👟" }!
        let before = parcel.currentStage
        XCTAssertEqual(before, .readyForPickup)
        parcel.trackingEvents.append(TrackingEvent(
            id: UUID(), packageID: parcel.id, stage: .delivered,
            description: "Delivered", occurredAt: DateParser.isoString(now)
        ))
        XCTAssertEqual(parcel.currentStage, .delivered)
        parcel.trackingEvents.removeLast()
        XCTAssertEqual(parcel.currentStage, before)
    }

    @MainActor func testDayKeysAndEstimatesAreStableAcrossRepeatedFormatting() throws {
        let localizer = Localizer(systemUses24HourTime: { true })
        localizer.language = .en
        let day = try XCTUnwrap(DateParser.deliveryDate("2026-09-12"))
        XCTAssertEqual(ParcelOrganizer.dayKey(day), "2026-09-12")
        XCTAssertNil(DateParser.date("2026-09-12"))
        XCTAssertEqual(DateParser.deliveryDate("2026-09-12"), day)
        for _ in 0..<3 {
            XCTAssertEqual(localizer.shortDate(day), "Sat 12 Sep")
            XCTAssertEqual(localizer.dateTime(DateParser.isoString(day)), "Sat 12 Sep, 00:00")
        }
    }
}

extension ParcelLogicTests {
    func testTheDeliveriesStandBesideTheParcelOnlyInAWindowLargeEnoughForBoth() {
        func arrangement(_ width: CGFloat, regularWidth: Bool = true, regularHeight: Bool = true, largeText: Bool = false,
                         from current: DeliveriesArrangement = .stacked) -> DeliveriesArrangement {
            DeliveriesArrangement.resolve(width: width, regularWidth: regularWidth, regularHeight: regularHeight,
                                          largeText: largeText, current: current)
        }
        // An iPad, and an open iPhone Duo held like a book, less its bar.
        XCTAssertEqual(arrangement(1376), .sideBySide)
        XCTAssertEqual(arrangement(887), .sideBySide)
        // An open Duo turned tall is regular both ways, but two columns there would each be narrower than a phone.
        XCTAssertEqual(arrangement(669), .stacked)
        // A phone on its side, a narrow iPad window and the Duo's cover keep the list.
        XCTAssertEqual(arrangement(956, regularHeight: false), .stacked)
        XCTAssertEqual(arrangement(1024, regularWidth: false), .stacked)
        XCTAssertEqual(arrangement(678, regularWidth: false, regularHeight: false), .stacked)
        // A window dragged across the line turns once, past the slack, and not back until past it the other way.
        XCTAssertEqual(arrangement(720), .stacked)
        XCTAssertEqual(arrangement(731), .sideBySide)
        XCTAssertEqual(arrangement(700, from: .sideBySide), .sideBySide)
        XCTAssertEqual(arrangement(689, from: .sideBySide), .stacked)
        // Size classes rule before width: side by side ends at once when the window becomes compact.
        XCTAssertEqual(arrangement(1376, regularWidth: false, from: .sideBySide), .stacked)
        // At the accessibility text sizes each column needs more: the open Duo keeps one column, an iPad still has room for two.
        XCTAssertEqual(arrangement(887, largeText: true), .stacked)
        XCTAssertEqual(arrangement(887, largeText: true, from: .sideBySide), .stacked)
        XCTAssertEqual(arrangement(1032, largeText: true), .sideBySide)
    }

    func testAParcelsPageBesideTheListPutsItsHistoryBesideTheCardWhereTwoPhoneWideColumnsFit() {
        func columns(_ width: CGFloat, besideList: Bool = true, largeText: Bool = false, history: Bool = true) -> Bool {
            ParcelPageColumns.fit(width: width, besideList: besideList, largeText: largeText, history: history)
        }
        // Beside the list on a 13-inch iPad on its side, and on an 11-inch one.
        XCTAssertTrue(columns(996))
        XCTAssertTrue(columns(800))
        // The 13-inch upright and an open Duo leave the page a phone's column or a little more.
        XCTAssertFalse(columns(652))
        XCTAssertFalse(columns(507))
        XCTAssertTrue(columns(776))
        XCTAssertFalse(columns(775))
        // Over the list, as on a phone on its side, the page keeps one column whatever its width.
        XCTAssertFalse(columns(956, besideList: false))
        // With nothing to tell yet, the history has no column of its own.
        XCTAssertFalse(columns(996, history: false))
        // At the accessibility text sizes each column needs more: the 13-inch still has room, the 11-inch not.
        XCTAssertTrue(columns(996, largeText: true))
        XCTAssertFalse(columns(800, largeText: true))
    }

    func testThePassportLiesOpenOnTwoPagesWhereAWideAndTallWindowHasRoomForTwoPhoneWidePages() {
        func spread(_ width: CGFloat, regularWidth: Bool = true, regularHeight: Bool = true, largeText: Bool = false) -> Bool {
            PassportSpread.fits(width: width, regularWidth: regularWidth, regularHeight: regularHeight, largeText: largeText)
        }
        // An iPad upright, 13-inch and 11-inch, and an open Duo lying flat, less its bar.
        XCTAssertTrue(spread(1032))
        XCTAssertTrue(spread(820))
        XCTAssertTrue(spread(887))
        XCTAssertTrue(spread(760))
        XCTAssertFalse(spread(759))
        // A phone on its side is wide but short; a narrow iPad window and the Duo's cover are compact.
        XCTAssertFalse(spread(956, regularHeight: false))
        XCTAssertFalse(spread(1024, regularWidth: false))
        XCTAssertFalse(spread(678, regularWidth: false, regularHeight: false))
        // At the accessibility text sizes each page needs more: the 13-inch still has room, the 11-inch not.
        XCTAssertTrue(spread(1032, largeText: true))
        XCTAssertFalse(spread(820, largeText: true))
    }
}

extension ParcelLogicTests {
    func testTheListsCardsRunFromNextUpToThePastDeliveriesWithoutTheArchive() throws {
        let now = DateParser.date("2026-09-09T12:00:00Z")!
        let parcels = DemoRepository.seed(now: now)
        let layout = DeliveryListLayout(parcels: parcels, query: "", status: .all, carrier: nil, sort: .standard, featuresNext: true, now: now)
        let delivered = try XCTUnwrap(layout.sections.first { $0.kind == .delivered })
        XCTAssertEqual(layout.cards, ([layout.next].compactMap { $0 } + layout.attention + layout.remaining + delivered.parcels).map(\.id))
        XCTAssertEqual(layout.cards.first, layout.next?.id)
        XCTAssertEqual(Set(layout.cards), Set(parcels.filter { !$0.isArchived }.map(\.id)))
    }

    func testEveryWayAParcelOpensShowsItAsChosen() {
        let (first, second) = (UUID(), UUID())
        var navigation = DeliveriesNavigation()
        navigation.open(first)
        XCTAssertEqual(navigation.path, [first])
        XCTAssertEqual(navigation.chosen, first)
        // Another opened from beside the list, or from a notification over another, takes its place.
        navigation.open(second)
        XCTAssertEqual(navigation.path, [second])
        // A parcel the app showed by itself becomes the person's once they open it.
        navigation = DeliveriesNavigation()
        navigation.settle(.sideBySide, cards: [first, second])
        XCTAssertTrue(navigation.shownAutomatically)
        navigation.open(first)
        XCTAssertFalse(navigation.shownAutomatically)
        XCTAssertEqual(navigation.chosen, first)
    }

    func testBesideTheListTheFirstCardShowsUntilSomeoneChoosesAnother() {
        let (next, other, later) = (UUID(), UUID(), UUID())
        var navigation = DeliveriesNavigation()
        // Nothing to show while the list shows no card.
        navigation.settle(.sideBySide, cards: [])
        XCTAssertNil(navigation.shown)
        navigation.settle(.sideBySide, cards: [next, other])
        XCTAssertEqual(navigation.shown, next)
        XCTAssertTrue(navigation.shownAutomatically)
        XCTAssertNil(navigation.chosen)
        // It stays while its card stays, wherever the card goes, and gives way to the first once it leaves.
        navigation.settle(.sideBySide, cards: [other, next])
        XCTAssertEqual(navigation.shown, next)
        navigation.settle(.sideBySide, cards: [other, later])
        XCTAssertEqual(navigation.shown, other)
        XCTAssertTrue(navigation.shownAutomatically)
        navigation.settle(.sideBySide, cards: [])
        XCTAssertNil(navigation.shown)
        // A parcel someone chose stays, even once a search hides its card.
        navigation.open(later)
        navigation.settle(.sideBySide, cards: [next])
        XCTAssertEqual(navigation.shown, later)
        XCTAssertEqual(navigation.chosen, later)
        // Over the list, nothing opens by itself.
        var stacked = DeliveriesNavigation()
        stacked.settle(.stacked, cards: [next, other])
        XCTAssertNil(stacked.shown)
    }

    func testAFoldKeepsTheParcelSomeoneChoseAndPutsBackOneShownByItself() {
        let (next, other) = (UUID(), UUID())
        var navigation = DeliveriesNavigation()
        navigation.settle(.sideBySide, cards: [next, other])
        navigation.settle(.stacked, cards: [next, other])
        XCTAssertEqual(navigation.path, [])
        XCTAssertFalse(navigation.shownAutomatically)
        // Unfolded again, the first card shows by itself once more.
        navigation.settle(.sideBySide, cards: [next, other])
        XCTAssertEqual(navigation.shown, next)
        navigation.open(other)
        navigation.settle(.stacked, cards: [next, other])
        XCTAssertEqual(navigation.path, [other])
        navigation.settle(.sideBySide, cards: [next, other])
        XCTAssertEqual(navigation.chosen, other)
    }

    func testTheParcelOpenWhenTheAppWasClosedOpensAgainOnceItIsBack() {
        let (open, other) = (UUID(), UUID())
        var navigation = DeliveriesNavigation()
        // The parcels aren't back yet: it waits.
        XCTAssertFalse(navigation.restore(open, among: []))
        XCTAssertNil(navigation.shown)
        XCTAssertTrue(navigation.restore(open, among: [other, open]))
        XCTAssertEqual(navigation.chosen, open)
        // It takes the place of the one shown by itself beside the list.
        navigation = DeliveriesNavigation()
        navigation.settle(.sideBySide, cards: [other, open])
        XCTAssertTrue(navigation.restore(open, among: [other, open]))
        XCTAssertEqual(navigation.chosen, open)
        // Gone since, it opens nothing.
        navigation = DeliveriesNavigation()
        XCTAssertTrue(navigation.restore(open, among: [other]))
        XCTAssertNil(navigation.shown)
        // A parcel someone opened first, from a notification or a link, stays.
        navigation = DeliveriesNavigation()
        navigation.open(other)
        XCTAssertTrue(navigation.restore(open, among: [other, open]))
        XCTAssertEqual(navigation.chosen, other)
    }

    func testAParcelThatLeavesTheListFromBesideItGivesWayToTheCardAfterIt() {
        let (a, b, c) = (UUID(), UUID(), UUID())
        let cards = [a, b, c]
        var navigation = DeliveriesNavigation()
        navigation.open(b)
        navigation.leave(b, cards: cards)
        XCTAssertEqual(navigation.shown, c)
        // Nobody chose the next one: a fold puts it back into the list, and no parcel view is counted.
        XCTAssertTrue(navigation.shownAutomatically)
        navigation.open(c)
        navigation.leave(c, cards: cards)
        XCTAssertEqual(navigation.shown, b, "the last card gives way to the one before it")
        // One brought back from the archive had no card: the first shows.
        let archived = UUID()
        navigation.open(archived)
        navigation.leave(archived, cards: cards)
        XCTAssertEqual(navigation.shown, a)
        navigation.open(a)
        navigation.leave(a, cards: [a])
        XCTAssertNil(navigation.shown, "the only card leaves nothing to show")
        // A page that is no longer the one shown changes nothing.
        navigation.open(a)
        navigation.leave(b, cards: cards)
        XCTAssertEqual(navigation.chosen, a)
    }

    func testBesideTheListAnArchiveThatShowsItsRowsCountsAsTheListsOwn() throws {
        let onTheWay = makeParcel()
        var older = makeParcel()
        older.archivedAt = "2026-08-09T12:00:00Z"
        var newer = makeParcel()
        newer.archivedAt = "2026-08-10T12:00:00Z"
        let parcels = [onTheWay, older, newer]
        let all = DeliveryListLayout(parcels: parcels, query: "", status: .all, carrier: nil, sort: .standard, featuresNext: true)
        let archive = try XCTUnwrap(all.sections.first { $0.kind == .archived }).parcels.map(\.id)
        XCTAssertEqual(Set(archive), [older.id, newer.id])
        // A closed shelf shows no row; an open one, or a filter, shows them after the cards.
        XCTAssertEqual(all.rows(archiveOpen: false), [onTheWay.id])
        XCTAssertEqual(all.rows(archiveOpen: true), [onTheWay.id] + archive)
        // The Archived filter shows rows and no card: the first row shows beside the list by itself.
        let archived = DeliveryListLayout(parcels: parcels, query: "", status: .archived, carrier: nil, sort: .standard, featuresNext: false)
        XCTAssertEqual(archived.cards, [])
        let rows = archived.rows(archiveOpen: true)
        XCTAssertEqual(Set(rows), [older.id, newer.id])
        var navigation = DeliveriesNavigation()
        navigation.settle(.sideBySide, cards: rows)
        XCTAssertEqual(navigation.shown, rows.first)
        XCTAssertTrue(navigation.shownAutomatically)
        // Leaving the filter, the first card takes its place.
        navigation.settle(.sideBySide, cards: all.rows(archiveOpen: false))
        XCTAssertEqual(navigation.shown, onTheWay.id)
    }

    func testTheArrowKeysGoThroughTheCardsBesideTheList() {
        let (a, b, c, archived) = (UUID(), UUID(), UUID(), UUID())
        let cards = [a, b, c]
        var navigation = DeliveriesNavigation()
        navigation.open(b)
        XCTAssertEqual(navigation.step(1, through: cards), c)
        // It opens, as a tap on its card does: it stays open through a fold, and comes back after a relaunch.
        XCTAssertEqual(navigation.chosen, c)
        XCTAssertEqual(navigation.step(-1, through: cards), b)
        XCTAssertEqual(navigation.step(-1, through: cards), a)
        XCTAssertEqual(navigation.chosen, a)
        // From the first card shown by itself, ↓ opens the second.
        navigation = DeliveriesNavigation()
        navigation.settle(.sideBySide, cards: cards)
        XCTAssertTrue(navigation.shownAutomatically)
        XCTAssertEqual(navigation.step(1, through: cards), b)
        XCTAssertEqual(navigation.chosen, b)
        navigation.open(a)
        // Past either end, the parcel shown stays.
        XCTAssertNil(navigation.step(-1, through: cards))
        XCTAssertEqual(navigation.chosen, a)
        navigation.open(c)
        XCTAssertNil(navigation.step(1, through: cards))
        XCTAssertEqual(navigation.chosen, c)
        // From a parcel with no card, or none, to the first card going down and the last going up.
        navigation.open(archived)
        XCTAssertEqual(navigation.step(1, through: cards), a)
        navigation.open(archived)
        XCTAssertEqual(navigation.step(-1, through: cards), c)
        navigation = DeliveriesNavigation()
        XCTAssertEqual(navigation.step(-1, through: cards), c)
        // No card, nowhere to go.
        navigation = DeliveriesNavigation()
        XCTAssertNil(navigation.step(1, through: []))
        XCTAssertNil(navigation.shown)
    }

    func testTheDeliveriesKeysActOnlyOnWhatIsInFront() {
        let (a, b) = (UUID(), UUID())
        var automatic = DeliveriesNavigation()
        automatic.settle(.sideBySide, cards: [a, b])
        var chosen = DeliveriesNavigation()
        chosen.open(a)
        func keys(
            free: Bool = true, searching: Bool = false, typing: Bool = false,
            _ arrangement: DeliveriesArrangement = .stacked,
            _ navigation: DeliveriesNavigation = DeliveriesNavigation(), cards: [UUID] = [a, b],
            hasParcels: Bool = true, canUndo: Bool = false
        ) -> Set<DeliveriesKey> {
            DeliveriesKey.available(free: free, searching: searching, typing: typing, arrangement: arrangement,
                                    navigation: navigation, cards: cards, hasParcels: hasParcels, canUndo: canUndo)
        }
        // The list by itself: Add and Find, and Undo while an archive's toast shows.
        XCTAssertEqual(keys(), [.add, .find])
        XCTAssertEqual(keys(canUndo: true), [.add, .find, .undo])
        XCTAssertEqual(keys(hasParcels: false), [.add])
        // A parcel open over the list, which has its own keys: the list's toast and search are under it.
        XCTAssertEqual(keys(.stacked, chosen, canUndo: true), [.add])
        // Beside a parcel, the arrows, and Return while it shows by itself.
        XCTAssertEqual(keys(.sideBySide, automatic), [.add, .find, .previous, .next, .open])
        XCTAssertEqual(keys(.sideBySide, chosen), [.add, .find, .previous, .next])
        XCTAssertEqual(keys(.sideBySide, chosen, cards: []), [.add, .find])
        // Typing in the search, the field keeps the keys it edits with; Escape closes the search, typing or not.
        XCTAssertEqual(keys(searching: true, typing: true, .sideBySide, automatic, canUndo: true),
                       [.add, .find, .closeSearch])
        XCTAssertEqual(keys(searching: true), [.add, .find, .closeSearch])
        // A parcel open over the list keeps Escape for going back, though the search under it is open.
        XCTAssertEqual(keys(searching: true, .stacked, chosen), [.add])
        // Another tab, or a sheet, an alert or a question over the deliveries or their parcel: none.
        XCTAssertEqual(keys(free: false, .sideBySide, automatic, canUndo: true), [])
    }

    func testOnlyAParcelSomeoneOpensAndTheListTheyGoBackToCountAsScreens() {
        let (a, b) = (UUID(), UUID())
        let list = DeliveriesNavigation()
        var opened = list
        opened.open(a)
        XCTAssertEqual(opened.screen(after: list), "parcel")
        XCTAssertNil(opened.screen(after: opened))
        XCTAssertEqual(list.screen(after: opened), "deliveries")
        var other = opened
        other.open(b)
        XCTAssertEqual(other.screen(after: opened), "parcel")
        // Shown by itself, beside the list.
        var automatic = list
        automatic.settle(.sideBySide, cards: [a, b])
        XCTAssertNil(automatic.screen(after: list))
        var chosen = automatic
        chosen.open(a)
        XCTAssertEqual(chosen.screen(after: automatic), "parcel")
        // After archiving, the next parcel shows by itself beside the list, which was there all along.
        var next = chosen
        next.leave(a, cards: [a, b])
        XCTAssertNil(next.screen(after: chosen))
        // Folded, it goes back into the list, which was in view already.
        var folded = next
        folded.settle(.stacked, cards: [b])
        XCTAssertNil(folded.screen(after: next))
    }
}

extension ParcelLogicTests {
    func testACardsMenuOffersWhatItsPageDoesAndOpensAndSharesTheParcelToo() {
        let parcel = makeParcel()
        XCTAssertEqual(ParcelMenuItem.menu(for: parcel, onCard: true), [
            [.open], [.rename, .share, .copyTrackingNumber, .changeCarrier], [.archive], [.delete],
        ])
        // The page has its own share button, and is open already.
        XCTAssertEqual(ParcelMenuItem.menu(for: parcel, onCard: false), [
            [.rename, .copyTrackingNumber, .changeCarrier], [.archive], [.delete],
        ])
        // An archived parcel is brought back rather than archived again.
        var archived = parcel
        archived.archivedAt = "2026-08-02T10:00:00Z"
        XCTAssertEqual(ParcelMenuItem.menu(for: archived, onCard: true)[2], [.restore])
        XCTAssertEqual(ParcelMenuItem.menu(for: archived, onCard: false)[1], [.restore])
    }

    func testAParcelsMenuShowsMailsKeysForCopyArchiveAndDeleteEachOnce() {
        XCTAssertEqual(ParcelMenuItem.copyTrackingNumber.shortcut, KeyboardShortcut("c", modifiers: [.command, .shift]))
        XCTAssertEqual(ParcelMenuItem.archive.shortcut, KeyboardShortcut("a", modifiers: [.command, .control]))
        XCTAssertEqual(ParcelMenuItem.restore.shortcut, ParcelMenuItem.archive.shortcut)
        XCTAssertEqual(ParcelMenuItem.delete.shortcut, KeyboardShortcut(.delete, modifiers: .command))
        var archived = makeParcel()
        archived.archivedAt = "2026-08-02T10:00:00Z"
        for parcel in [makeParcel(), archived] {
            for onCard in [true, false] {
                let items = ParcelMenuItem.menu(for: parcel, onCard: onCard).joined().filter { $0.shortcut != nil }
                XCTAssertEqual(items, [.copyTrackingNumber, parcel.isArchived ? .restore : .archive, .delete])
            }
        }
    }

    func testTheMenuBarsParcelMenuIsTheCardsWithoutOpenAndDimsTheSameItemsWithoutAParcel() {
        let parcel = makeParcel()
        XCTAssertEqual(ParcelMenu.items(for: parcel, reader: false), [
            [.rename, .share, .copyTrackingNumber, .changeCarrier], [.archive], [.delete],
        ])
        XCTAssertEqual(ParcelMenu.placeholder, ParcelMenu.items(for: parcel, reader: false))
        // Someone else's parcel, followed through its link: no carrier to change, nothing to copy the link hides.
        var hidden = parcel
        hidden.trackingNumber = ""
        XCTAssertEqual(ParcelMenu.items(for: hidden, reader: true), [[.rename], [.archive], [.delete]])
    }

    func testTheMenuBarListsMailsKeysOnceEachBesideTheParcelsOwn() {
        XCTAssertEqual(MenuKey.account, KeyboardShortcut(",", modifiers: .command))
        XCTAssertEqual(MenuKey.add, KeyboardShortcut("n", modifiers: .command))
        XCTAssertEqual(MenuKey.find, KeyboardShortcut("f", modifiers: .command))
        XCTAssertEqual(MenuKey.refresh, KeyboardShortcut("r", modifiers: .command))
        XCTAssertEqual((0..<3).map { MenuKey.tab($0).key.character }, ["1", "2", "3"])
        let keys = [MenuKey.account, MenuKey.add, MenuKey.find, MenuKey.refresh] + (0..<3).map(MenuKey.tab)
            + ParcelMenu.placeholder.joined().compactMap(\.shortcut)
        XCTAssertEqual(keys.count, 10)
        XCTAssertEqual(Set(keys.map { "\($0.key.character)\($0.modifiers.rawValue)" }).count, keys.count)
        // A phone has no menu bar: its screens' own keys answer, as before.
        if UIDevice.current.userInterfaceIdiom == .phone {
            XCTAssertFalse(MenuBar.shown)
        }
    }

    func testACopiedTrackingNumberIsTheOneItsCarriersSiteTakes() {
        XCTAssertEqual(TrackingNumberCopy.text("1Z999AA10123456784", carrier: .ups), "1Z999AA10123456784")
        // PostLogistics prints its references with a dash, and its site searches for them so.
        XCTAssertEqual(TrackingNumberCopy.text("19999980001", carrier: .postlogistics), "19999980-001")
    }

    func testTextDroppedOnTheDeliveriesOpensAddOnlyWhenItHoldsATrackingOrOrderNumber() {
        let catalog = CarrierCatalog(cacheURL: nil)
        XCTAssertEqual(FirstParcelRequest.drop([" 1Z999AA10123456784\n"], catalog: catalog), .paste("1Z999AA10123456784"))
        let message = "Your order has shipped: 1Z999AA10123456784"
        XCTAssertEqual(FirstParcelRequest.drop([message], catalog: catalog), .paste(message))
        // A link and the words that came with it are read together, each once, one to a line, as the share extension reads them.
        let link = "https://www.ups.com/track?tracknum=1Z999AA10123456784"
        XCTAssertEqual(FirstParcelRequest.drop([link, "Track your parcel", link], catalog: catalog),
                       .paste("\(link)\nTrack your parcel"))
        // An order number opens Add, which says where to find the tracking number.
        let order = "Order #302-4571983-2294617 is confirmed"
        XCTAssertEqual(FirstParcelRequest.drop([order], catalog: catalog), .paste(order))
        // So does a message's one number whose shape proves no carrier: Add asks the carriers.
        let uncertain = "Your order has shipped!\nTrack it with DHL: 1234567899"
        XCTAssertEqual(FirstParcelRequest.drop([uncertain], catalog: catalog), .paste(uncertain))
        // Nothing to track: the drop is refused, and Add stays closed.
        XCTAssertNil(FirstParcelRequest.drop([], catalog: catalog))
        XCTAssertNil(FirstParcelRequest.drop([" ", "\n"], catalog: catalog))
        XCTAssertNil(FirstParcelRequest.drop(["Your shipment arrives Monday"], catalog: catalog))
        // No more than Add takes in from a paste.
        let long = "Your order has shipped: 1Z999AA10123456784. " + String(repeating: "More news follows. ", count: 200)
        guard case .paste(let text)? = FirstParcelRequest.drop([long], catalog: catalog) else {
            return XCTFail("a long message with a number is refused")
        }
        XCTAssertEqual(text.count, FirstParcelRequest.pasteLimit)
    }
}

extension ParcelLogicTests {
    /// The deliveries' screens drawn in a window at the widths a phone, an iPad's columns, its narrow windows and its
    /// whole screen give them, at regular type in light and at large type in dark, each picture kept with the test's
    /// results.
    @MainActor func testTheDeliveriesScreensDrawInEveryWidthTheyAreGiven() async throws {
        try await draw(.en, named: "list", in: [CGSize(width: 320, height: 700), CGSize(width: 380, height: 800)]) { _ in
            ParcelListView(selection: .constant(0))
        }
        try await draw(.de, named: "list", in: [CGSize(width: 330, height: 700)]) { _ in
            ParcelListView(selection: .constant(0))
        }
        try await draw(.en, named: "detail", in: [CGSize(width: 390, height: 844), CGSize(width: 640, height: 900)]) { store in
            DetailPage(parcelID: try XCTUnwrap(store.parcels.first(where: \.isActive)).id)
        }
        // Beside the list, the parcel waiting at its pickup point, the fullest page: one column upright on a 13-inch
        // iPad, two on its side and on an 11-inch one.
        try await draw(.en, named: "detail-beside", in: [CGSize(width: 652, height: 1000), CGSize(width: 800, height: 800),
                                                          CGSize(width: 996, height: 1000)]) { store in
            DetailPage(parcelID: try XCTUnwrap(store.parcels.first { $0.currentStage == .readyForPickup }).id, besideList: true)
        }
        try await draw(.de, named: "detail-beside", in: [CGSize(width: 800, height: 800)]) { store in
            DetailPage(parcelID: try XCTUnwrap(store.parcels.first { $0.currentStage == .readyForPickup }).id, besideList: true)
        }
        // Its pickup point's buttons in German, whose "Copy address" is the longest, on a phone.
        try await draw(.de, named: "detail-pickup", in: [CGSize(width: 390, height: 844)]) { store in
            DetailPage(parcelID: try XCTUnwrap(store.parcels.first { $0.currentStage == .readyForPickup }).id)
        }
        // Two pages on an iPad upright, 11-inch and 13-inch; one on a phone on its side.
        try await draw(.en, named: "passport", in: [CGSize(width: 820, height: 1180), CGSize(width: 1032, height: 1376)]) { _ in
            PassportView()
        }
        try await draw(.en, named: "passport-phone", in: [CGSize(width: 956, height: 440)]) { _ in
            PassportView().environment(\.verticalSizeClass, .compact)
        }
        try await draw(.en, named: "first-open", in: [CGSize(width: 466, height: 678), CGSize(width: 678, height: 466),
                                                      CGSize(width: 1032, height: 1376), CGSize(width: 1376, height: 1032)]) { _ in
            FirstOpenView(covered: false) { _ in }
        }
    }

    /// The welcome screen at the largest text size, on the smallest iPhone the app runs on (an iPhone SE), a 6.1-inch
    /// one and an 11-inch iPad either way up: the picture grows, so the masthead, the route and the parcel's name
    /// keep apart and the screen scrolls. Each is drawn once its map has landed, before the next scan comes in.
    @MainActor func testTheWelcomeScreenMakesRoomForTheLargestText() async throws {
        try await draw(.en, named: "first-open-largest", in: [CGSize(width: 375, height: 667), CGSize(width: 390, height: 844),
                                                              CGSize(width: 820, height: 1180), CGSize(width: 1180, height: 820)],
                       largeType: .accessibility5, settle: .seconds(2)) { _ in
            FirstOpenView(covered: false) { _ in }
        }
    }

    func testTheWelcomePictureGrowsByWhatItsWordsOutgrow() {
        // At the usual text size it keeps the height the screen leaves it, and the route its place.
        let usual = FirstOpenHeroLayout(fits: 446, top: 62, masthead: 44, caption: 108)
        XCTAssertEqual([usual.height, usual.routeTop, usual.routeBottom, usual.floor], [446, 174, 128, 328])
        let small = FirstOpenHeroLayout(fits: 300, top: 20, masthead: 44, caption: 108)
        XCTAssertEqual([small.height, small.routeTop, small.routeBottom, small.floor], [300, 132, 128, 182])
        // At the largest, on an iPhone SE, the masthead stands taller and the name and status take several lines.
        let largest = FirstOpenHeroLayout(fits: 300, top: 20, masthead: 64, caption: 420)
        XCTAssertGreaterThanOrEqual(largest.routeTop, 20 + 10 + 64 + 12 + 26, "the masthead and its alert end over the route")
        XCTAssertGreaterThanOrEqual(largest.routeBottom, 420, "the route ends over the name")
        XCTAssertLessThanOrEqual(largest.floor, largest.height - 420, "Pip stays over the name")
        XCTAssertEqual(largest.ceiling, 20 + 10 + 64 + 12 + 26, "Pip stays under the masthead and its alert")
        XCTAssertEqual(usual.ceiling, 62 + 10 + 44 + 12 + 26)
        XCTAssertEqual(largest.height - largest.routeTop - largest.routeBottom, 144, "the route keeps 144 points at least")
        // A taller phone's route keeps the room it has at the usual size.
        let tall = FirstOpenHeroLayout(fits: 446, top: 62, masthead: 64, caption: 420)
        XCTAssertEqual(tall.height - tall.routeTop - tall.routeBottom, usual.height - usual.routeTop - usual.routeBottom)
        // Beside a fold, the fold sets its height.
        XCTAssertEqual(FirstOpenHeroLayout(fits: 300, top: 20, masthead: 64, caption: 420, grows: false).height, 300)
    }

    /// Draws a screen over the demo's parcels in a window of each size, and checks it asks for no more width than it has.
    /// Each is drawn at the usual text size in light and at `largeType` in dark, `settle` after it opens.
    @MainActor private func draw<Content: View>(
        _ language: AppLanguage,
        named name: String,
        in sizes: [CGSize],
        largeType largeSize: DynamicTypeSize = .accessibility3,
        settle: Duration = .milliseconds(400),
        @ViewBuilder content: (ParcelStore) throws -> Content
    ) async throws {
        let localizer = Localizer()
        let previousLanguage = localizer.language
        defer { localizer.language = previousLanguage }
        localizer.language = language
        let suite = "render-tests-\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        defer { defaults.removePersistentDomain(forName: suite) }
        let demo = AppConfiguration(mode: .demo, apiBaseURL: URL(string: "https://render.test")!, supabaseURL: nil, supabasePublishableKey: "",
                                    googleAuthEnabled: false, appleAuthEnabled: false, emailOTPEnabled: false, appGroupIdentifier: "render.test")
        let session = SessionStore(configuration: demo, defaults: defaults, persistence: MemorySessionPersistence())
        let store = ParcelStore(configuration: demo, session: session, localizer: localizer,
                                device: DeviceParcels(client: nil, storage: RenderTestStorage()))
        session.enterDemo()
        let links = ParcelLinkStore(defaults: defaults, configuration: demo)
        let scene = try XCTUnwrap(UIApplication.shared.connectedScenes.compactMap { $0 as? UIWindowScene }.first)
        for size in sizes {
            for largeType in [false, true] {
                let host = UIHostingController(rootView: try content(store)
                    .environmentObject(session)
                    .environmentObject(store)
                    .environmentObject(localizer)
                    .environmentObject(FriendInvitationStore(defaults: defaults))
                    .environmentObject(FriendsActivityStore())
                    .environmentObject(links)
                    .environment(\.locale, language.locale)
                    .environment(\.dynamicTypeSize, largeType ? largeSize : .large))
                host.overrideUserInterfaceStyle = largeType ? .dark : .light
                // The window takes the simulator's shape: the screen is a view of the size asked for inside it.
                let window = UIWindow(windowScene: scene)
                let container = UIViewController()
                window.rootViewController = container
                container.addChild(host)
                host.view.frame = CGRect(origin: .zero, size: size)
                container.view.addSubview(host.view)
                host.didMove(toParent: container)
                window.isHidden = false
                defer { window.isHidden = true }
                try await Task.sleep(for: settle)
                let label = "\(name)-\(Int(size.width))x\(Int(size.height))-\(language.rawValue)-\(largeType ? "large-dark" : "regular-light")"
                XCTAssertLessThanOrEqual(host.sizeThatFits(in: size).width, size.width + 0.5, label)
                let image = UIGraphicsImageRenderer(size: size).image { _ in
                    host.view.drawHierarchy(in: host.view.bounds, afterScreenUpdates: true)
                }
                let attachment = XCTAttachment(image: image)
                attachment.name = label
                attachment.lifetime = .keepAlways
                add(attachment)
            }
        }
    }
}

extension ParcelLogicTests {
    /// The demo's vinyl carries the steps its carrier wrote ahead apart from its scans: none of them moves the parcel
    /// on, dates it or counts as an update.
    func testTheDemosPlannedStepsStayApartFromItsScans() throws {
        let now = DateParser.date("2026-09-09T12:00:00Z")!
        let vinyl = try XCTUnwrap(DemoRepository.seed(now: now).first { $0.label == Self.vinyl })
        XCTAssertEqual(vinyl.carrier, .ups)
        XCTAssertEqual(vinyl.trackingEvents.map(\.stage), [.registered, .accepted, .inTransit])
        let planned = try XCTUnwrap(vinyl.plannedEvents)
        XCTAssertEqual(planned.map(\.description), ["Customs cleared", "Arrived at the local delivery centre", "Out for delivery", "Delivered"])
        XCTAssertEqual(planned.map(\.stage), [.customs, .inTransit, .outForDelivery, .delivered])
        XCTAssertEqual(planned.map(\.location), ["Basel, Switzerland", "Zürich, Switzerland", nil, nil])
        // A negative `hoursAgo` is still to come, as every planned step the demo shows.
        XCTAssertEqual(planned.compactMap { DateParser.date($0.occurredAt) },
                       [2.0, 10, 20, 24].map { now.addingTimeInterval($0 * 3_600) })
        XCTAssertEqual(vinyl.currentStage, .inTransit)
        XCTAssertFalse(vinyl.isDelivered)
        XCTAssertNil(vinyl.expectedDelivery)
        XCTAssertEqual(vinyl.journalEntries.count, 3)
        XCTAssertTrue(DemoRepository.seed(now: now).filter { $0.label != Self.vinyl }.allSatisfy { $0.plannedEvents == nil })
    }

    private static let vinyl = "Kind of Blue · vinyl 🎷"

    func testTheDemoTranslatesItsPlannedStepsAndARefreshDropsTheOnesItsScansOvertake() throws {
        let suite = "demo-planned-tests-\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        defer { defaults.removePersistentDomain(forName: suite) }
        var language = AppLanguage.en
        let repo = DemoRepository(defaults: defaults, language: { language })
        let vinyl = try XCTUnwrap(repo.list().first { $0.label == Self.vinyl })

        language = .fr
        let french = try XCTUnwrap(repo.list().first { $0.id == vinyl.id })
        XCTAssertEqual(french.plannedEvents?.map(\.description), [
            "Dédouané", "Arrivé au centre de distribution local", "En cours de livraison", "Livré",
        ])
        XCTAssertEqual(french.plannedEvents?.map(\.occurredAt), vinyl.plannedEvents?.map(\.occurredAt))
        language = .en
        XCTAssertEqual(try XCTUnwrap(repo.list().first { $0.id == vinyl.id }).plannedEvents, vinyl.plannedEvents)

        // Out for delivery for real: a later step of an earlier stage goes. Customs tells where a parcel is, not how far
        // it has come, so its step stays while its time is ahead.
        let out = try repo.refresh(id: vinyl.id)
        XCTAssertEqual(out.currentStage, .outForDelivery)
        XCTAssertEqual(out.plannedEvents?.map(\.description), ["Customs cleared", "Out for delivery", "Delivered"])
        // Delivered, nothing is planned any more.
        let delivered = try repo.refresh(id: vinyl.id)
        XCTAssertEqual(delivered.currentStage, .delivered)
        XCTAssertNil(delivered.plannedEvents)
        XCTAssertNil(try XCTUnwrap(repo.list().first { $0.id == vinyl.id }).plannedEvents)
    }

    func testAPlannedStepStaysUntilAScanIsAsLateOrHasComeFurther() {
        let id = UUID()
        func step(_ stage: TrackingStage, _ occurredAt: String) -> PlannedTrackingEvent {
            PlannedTrackingEvent(description: "\(stage.rawValue) planned", stage: stage, occurredAt: occurredAt)
        }
        var parcel = makeParcel(id: id, events: [
            event(id, .accepted, "2026-09-01T10:00:00Z"), event(id, .outForDelivery, "2026-09-03T08:00:00Z"),
        ])
        parcel.plannedEvents = [
            step(.inTransit, "2026-09-02T10:00:00Z"),
            step(.inTransit, "2026-09-03T09:00:00Z"),
            step(.customs, "2026-09-03T08:00:00Z"),
            step(.customs, "2026-09-03T10:00:00Z"),
            step(.exception, "2026-09-03T11:00:00Z"),
            step(.outForDelivery, "2026-09-03T12:00:00Z"),
            step(.delivered, "2026-09-03T15:00:00Z"),
        ]
        // Dated before the newest scan, or as late, it is overtaken; so is a step behind the furthest stage scanned. A
        // step outside the stages' order is never behind.
        XCTAssertEqual(DemoRepository.stillPlanned(parcel).plannedEvents?.map(\.occurredAt), [
            "2026-09-03T10:00:00Z", "2026-09-03T11:00:00Z", "2026-09-03T12:00:00Z", "2026-09-03T15:00:00Z",
        ])
        XCTAssertEqual(DemoRepository.stillPlanned(makeParcel(id: id, events: parcel.trackingEvents)), makeParcel(id: id, events: parcel.trackingEvents))
        for ending in [TrackingStage.delivered, .returned] {
            var ended = parcel
            ended.trackingEvents.insert(event(id, ending, "2026-09-02T09:00:00Z"), at: 1)
            XCTAssertNil(DemoRepository.stillPlanned(ended).plannedEvents, "\(ending)")
        }
        // None left: none planned.
        var overtaken = parcel
        overtaken.trackingEvents.append(event(id, .outForDelivery, "2026-09-04T08:00:00Z"))
        XCTAssertNil(DemoRepository.stillPlanned(overtaken).plannedEvents)
    }

    func testAPlannedStepShowsOnlyWhileItsTimeIsAhead() throws {
        func step(_ occurredAt: String, day: String? = nil) -> PlannedTrackingEvent {
            PlannedTrackingEvent(description: "Delivered", stage: .delivered, occurredAt: occurredAt, day: day)
        }
        let now = try XCTUnwrap(DateParser.date("2026-11-04T10:00:00Z"))
        XCTAssertTrue(step("2026-11-04T10:01:00Z").isAhead(of: now))
        // The carrier's pre-written "Delivered" whose time has come is no longer planned.
        XCTAssertFalse(step("2026-11-04T10:00:00Z").isAhead(of: now))
        XCTAssertFalse(step("2026-11-04T07:00:00Z").isAhead(of: now))
        // A step dated by its day alone stands through that day, from its start in the carrier's zone.
        XCTAssertTrue(step("2026-11-03T16:00:00Z", day: "2026-11-04").isAhead(of: now))
        XCTAssertFalse(step("2026-11-02T16:00:00Z", day: "2026-11-03").isAhead(of: now))
        XCTAssertFalse(step("not a date").isAhead(of: now))
    }

    func testAPlannedStepSaysWhenItStopsBeingAheadSoAnOpenJournalCanDropIt() throws {
        func step(_ occurredAt: String, day: String? = nil) -> PlannedTrackingEvent {
            PlannedTrackingEvent(description: "Delivered", stage: .delivered, occurredAt: occurredAt, day: day)
        }
        let timed = step("2026-11-04T10:00:00Z")
        let until = try XCTUnwrap(timed.aheadUntil)
        XCTAssertEqual(until, DateParser.date("2026-11-04T10:00:00Z"))
        XCTAssertTrue(timed.isAhead(of: until.addingTimeInterval(-1)))
        XCTAssertFalse(timed.isAhead(of: until))
        let daily = step("2026-11-03T23:00:00Z", day: "2026-11-04")
        let end = try XCTUnwrap(daily.aheadUntil)
        XCTAssertEqual(end, DateParser.date("2026-11-04T23:00:00Z"))
        XCTAssertTrue(daily.isAhead(of: end.addingTimeInterval(-1)))
        XCTAssertFalse(daily.isAhead(of: end))
        XCTAssertNil(step("not a date").aheadUntil)
    }

    func testADayOnlyStepEndsAtTheCarriersNextMidnightWhenTheClocksChange() throws {
        // 28 March 2027 lasts 23 hours in Zurich: it starts at 23:00 UTC the day before and ends at 22:00 UTC.
        func step(day: String, from start: String, until end: String?) -> PlannedTrackingEvent {
            PlannedTrackingEvent(description: "Delivered", stage: .delivered, occurredAt: start, expiresAt: end, day: day)
        }
        let short = step(day: "2027-03-28", from: "2027-03-27T23:00:00Z", until: "2027-03-28T22:00:00Z")
        XCTAssertTrue(short.isAhead(of: try XCTUnwrap(DateParser.date("2027-03-28T21:30:00Z"))))
        XCTAssertFalse(short.isAhead(of: try XCTUnwrap(DateParser.date("2027-03-28T22:30:00Z"))))
        XCTAssertEqual(short.aheadUntil, DateParser.date("2027-03-28T22:00:00Z"))
        // 31 October 2027 lasts 25 hours there.
        let long = step(day: "2027-10-31", from: "2027-10-30T22:00:00Z", until: "2027-10-31T23:00:00Z")
        XCTAssertTrue(long.isAhead(of: try XCTUnwrap(DateParser.date("2027-10-31T22:30:00Z"))))
        // A step cached before the server said when it ends stands for a day, as before.
        let cached = step(day: "2027-03-28", from: "2027-03-27T23:00:00Z", until: nil)
        XCTAssertEqual(cached.aheadUntil, DateParser.date("2027-03-28T23:00:00Z"))
    }

    @MainActor
    func testAPlannedStepReadsItsDayAsTheJournalDoesAndItsTimeWhenItHasOne() throws {
        let localizer = Localizer(systemUses24HourTime: { true })
        let originalLanguage = localizer.language
        defer { localizer.language = originalLanguage }
        let calendar = Calendar.current
        let now = try XCTUnwrap(calendar.date(from: DateComponents(year: 2026, month: 11, day: 4, hour: 12)))
        func at(month: Int, day: Int, year: Int = 2026) throws -> String {
            DateParser.isoString(try XCTUnwrap(calendar.date(from: DateComponents(year: year, month: month, day: day, hour: 7, minute: 54))))
        }
        func step(_ occurredAt: String, day: String? = nil) -> PlannedTrackingEvent {
            PlannedTrackingEvent(description: "Planned", stage: .inTransit, occurredAt: occurredAt, day: day)
        }
        localizer.language = .en
        XCTAssertEqual(localizer.plannedDate(step(try at(month: 11, day: 4)), now: now), "today, 07:54")
        XCTAssertEqual(localizer.plannedDate(step(try at(month: 11, day: 5)), now: now), "tomorrow, 07:54")
        XCTAssertEqual(localizer.plannedDate(step(try at(month: 11, day: 3)), now: now), "yesterday, 07:54")
        XCTAssertEqual(localizer.plannedDate(step(try at(month: 11, day: 6)), now: now), "Fri 6 Nov, 07:54")
        XCTAssertEqual(localizer.plannedDate(step(try at(month: 1, day: 8, year: 2027)), now: now), "Fri 8 Jan 2027, 07:54")
        // A step the carrier dated by its day alone has no time.
        XCTAssertEqual(localizer.plannedDate(step(try at(month: 11, day: 6), day: "2026-11-06"), now: now), "Fri 6 Nov")
        XCTAssertEqual(localizer.plannedDate(step(try at(month: 11, day: 5), day: "2026-11-05"), now: now), "tomorrow")
        XCTAssertEqual(localizer.plannedDate(step("not a date"), now: now), "")
        localizer.language = .zh
        XCTAssertEqual(localizer.plannedDate(step(try at(month: 11, day: 5)), now: now), "明天 07:54")
    }

    func testALinkCarriesThePlannedStepsItShows() throws {
        let now = DateParser.date("2026-09-09T12:00:00Z")!
        let vinyl = try XCTUnwrap(DemoRepository.seed(now: now).first { $0.label == Self.vinyl })
        let package = PublicPackage(
            id: vinyl.id, trackingNumber: vinyl.trackingNumber, label: "", carrier: vinyl.carrier, createdAt: vinyl.createdAt,
            syncStatus: .ok, carrierData: PublicPackageCarrierData(), notificationsMuted: false,
            trackingEvents: vinyl.trackingEvents, plannedEvents: vinyl.plannedEvents
        )
        XCTAssertEqual(Parcel(shared: package).plannedEvents, vinyl.plannedEvents)
        XCTAssertEqual(Parcel(shared: package).trackingEvents, vinyl.trackingEvents)
    }

    func testALinkCarriesTheStepsItsCarrierWroteAheadApartFromItsScans() throws {
        let now = DateParser.date("2026-09-09T12:00:00Z")!
        let vinyl = try XCTUnwrap(DemoRepository.seed(now: now).first { $0.label == Self.vinyl })
        let written = [PlannedTrackingEvent(description: "Delivered", stage: .delivered, occurredAt: "2026-09-08T07:00:00Z")]
        let package = PublicPackage(
            id: vinyl.id, trackingNumber: vinyl.trackingNumber, label: "", carrier: .gzyl, createdAt: vinyl.createdAt,
            syncStatus: .ok, carrierData: PublicPackageCarrierData(), notificationsMuted: false,
            trackingEvents: [], writtenAheadBy: .gzyl, writtenAheadEvents: written
        )
        let parcel = Parcel(shared: package)
        XCTAssertEqual(parcel.writtenAheadEvents, written)
        XCTAssertNil(parcel.plannedEvents)
        XCTAssertTrue(parcel.trackingEvents.isEmpty)
        XCTAssertNotEqual(parcel.currentStage, .delivered)
        XCTAssertEqual(parcel.writtenAheadNoteKey, "parcel.writtenAheadShown")
    }

    /// The journal of the demo's vinyl, with the steps its carrier planned over the scans, at regular type in light and
    /// at large type in dark, each picture kept with the test's results.
    @MainActor func testTheJournalDrawsThePlannedStepsOverTheScans() async throws {
        try await draw(.en, named: "journal-planned", in: [CGSize(width: 390, height: 1000)]) { store in
            let vinyl = try XCTUnwrap(store.parcels.first { $0.label == Self.vinyl })
            ScrollView { ParcelJournal(parcel: vinyl, tint: .orange).padding(16) }
        }
    }

    /// The same journal once its carrier's pre-written steps have passed: greyed under "Written in advance by", a
    /// delivery among them, over the scans.
    @MainActor func testTheJournalDrawsTheStepsWrittenInAdvanceOverTheScans() async throws {
        try await draw(.en, named: "journal-written", in: [CGSize(width: 390, height: 1000)]) { store in
            let vinyl = try XCTUnwrap(store.parcels.first { $0.label == Self.vinyl })
            let written: [PlannedTrackingEvent] = (vinyl.plannedEvents ?? []).enumerated().map { index, step in
                var past = step
                past.occurredAt = DateParser.isoString(Date().addingTimeInterval(-3_600 * Double(10 - index)))
                past.day = nil
                return past
            }
            let scripted: Parcel = {
                var parcel = vinyl
                parcel.plannedEvents = nil
                parcel.writtenAheadBy = .gzyl
                parcel.writtenAheadEvents = written
                return parcel
            }()
            ScrollView { ParcelJournal(parcel: scripted, tint: .orange).padding(16) }
        }
    }
}

/// A parcel's page as the list opens it, over itself or beside it, with the transition it comes in by.
private struct DetailPage: View {
    let parcelID: UUID
    var besideList = false
    @Namespace private var transition

    var body: some View {
        NavigationStack { ParcelDetailView(parcelID: parcelID, transition: transition, release: besideList ? {} : nil) }
    }
}

private final class RenderTestStorage: DeviceParcelStorage {
    func load() -> (parcels: [DeviceParcel], keys: [String: String]) { ([], [:]) }
    func save(_ parcels: [DeviceParcel], keys: [String: String]) {}
}

/// The server down for maintenance, told from the app refusing or failing as the web app tells it.
final class MaintenanceTests: XCTestCase {
    private func answer(_ status: Int, _ body: String = "") -> Bool {
        Maintenance.isServerDown(status: status, data: Data(body.utf8))
    }

    func testAProxysOrCloudflaresErrorTheDatabasesAndTheEdgesAreTheServerDown() {
        for status in [502, 503, 504, 520, 521, 522, 523, 524, 525, 526, 527, 530] { XCTAssertTrue(answer(status), "\(status)") }
        XCTAssertTrue(answer(502, #"{"error":"The delivery database is temporarily unavailable"}"#))
        XCTAssertTrue(answer(503, #"{"error":"Peek is down for maintenance","maintenance":{"planned":false,"mode":null,"since":null,"backAround":null}}"#))
        XCTAssertTrue(answer(503, #"{"error":"Supabase authentication is temporarily unavailable"}"#))
        // A 404 the app did not write, as the proxy's while the app starts; never the app's own.
        XCTAssertTrue(answer(404, "404 page not found\n"))
        XCTAssertFalse(answer(404, #"{"error":"Parcel unavailable"}"#))
    }

    func testTheAppRefusingFailingOrNeverSetUpForSomethingIsNot() {
        for status in [400, 401, 403, 409, 410, 429, 500] { XCTAssertFalse(answer(status, #"{"error":"No"}"#), "\(status)") }
        XCTAssertFalse(answer(503, #"{"error":"Push notifications are not configured"}"#))
        XCTAssertFalse(answer(503, #"{"error":"Amazon Shipping could not be checked. Try again."}"#))
        XCTAssertFalse(answer(503, #"{"error":"The carrier could not be asked. Try again later."}"#))
        XCTAssertFalse(answer(503, #"{"error":"Browser verification is temporarily unavailable. Try again shortly."}"#))
    }

    func testARequestWithNoAnswerWhileOnlineIsTheServerDownAndAnOfflineDeviceStaysOffline() {
        XCTAssertTrue(Maintenance.unanswered(URLError(.timedOut)))
        XCTAssertTrue(Maintenance.unanswered(URLError(.cannotConnectToHost)))
        XCTAssertTrue(Maintenance.unanswered(URLError(.networkConnectionLost)))
        XCTAssertFalse(Maintenance.unanswered(URLError(.notConnectedToInternet)))
        XCTAssertFalse(Maintenance.unanswered(URLError(.cancelled)))
        XCTAssertFalse(Maintenance.unanswered(CancellationError()))
        XCTAssertTrue(Maintenance.failure(URLError(.notConnectedToInternet)) is URLError)
        XCTAssertEqual(Maintenance.failure(URLError(.timedOut)) as? ServerDown, ServerDown())
    }

    func testOnlyAnAnnouncedMaintenanceSaysWhenItEnds() {
        XCTAssertEqual(Maintenance.notice(in: Data(#"{"maintenance":{"planned":true,"backAround":"2026-11-17T09:30:00Z"}}"#.utf8)),
                       MaintenanceNotice(planned: true, backAround: DateParser.date("2026-11-17T09:30:00Z")))
        XCTAssertEqual(Maintenance.notice(in: Data(#"{"maintenance":{"planned":false,"backAround":"2026-11-17T09:30:00Z"}}"#.utf8)), .unannounced)
        XCTAssertEqual(Maintenance.notice(in: Data(#"{"maintenance":{"planned":true,"backAround":"soon"}}"#.utf8)),
                       MaintenanceNotice(planned: true, backAround: nil))
        XCTAssertNil(Maintenance.notice(in: Data(#"{"error":"The delivery database is temporarily unavailable"}"#.utf8)))
    }

    func testItIsAskedAgainAsSoonAsTheServerAsksAndAtLeastOnceAMinute() {
        XCTAssertEqual((0...5).map { Maintenance.retryDelay(attempt: $0) }, [5, 10, 20, 40, 60, 60])
        XCTAssertEqual(Maintenance.retryDelay(attempt: 0, retryAfter: 30), 30)
        XCTAssertEqual(Maintenance.retryDelay(attempt: 0, retryAfter: 900), 60)
    }

    @MainActor func testTheNoticeSaysHowOldTheListIsAndWhenAnAnnouncedMaintenanceEnds() {
        let localizer = Localizer(systemUses24HourTime: { true })
        let originalLanguage = localizer.language
        defer { localizer.language = originalLanguage }
        localizer.language = .en
        let now = Calendar.current.date(bySettingHour: 12, minute: 0, second: 0, of: Date())!
        let readAt = now.addingTimeInterval(-30 * 60), back = now.addingTimeInterval(30 * 60)
        let read = localizer.clockTime(readAt), end = localizer.clockTime(back)
        XCTAssertEqual(localizer.maintenanceText(.unannounced, readAt: readAt, now: now),
                       "Peek is down for a few minutes of maintenance. Your parcels below are from \(read).")
        XCTAssertEqual(localizer.maintenanceText(MaintenanceNotice(planned: true, backAround: back), readAt: readAt, now: now),
                       "Scheduled maintenance, back around \(end). Your parcels below are from \(read).")
        XCTAssertEqual(localizer.maintenanceText(.unannounced, readAt: nil, now: now), "Peek is down for a few minutes of maintenance.")
        XCTAssertEqual(localizer.maintenanceText(.unannounced, readAt: now.addingTimeInterval(-86_400), now: now),
                       "Peek is down for a few minutes of maintenance. Your parcels below are from yesterday, \(localizer.clockTime(now)).")
        XCTAssertEqual(localizer.errorMessage(ServerDown()), "Peek is down for a few minutes of maintenance.")
    }
}
