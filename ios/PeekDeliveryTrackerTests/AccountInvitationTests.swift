import XCTest
@testable import PeekDeliveryTracker

/// The guest's invitation to an account under their parcels: shown on every visit, closable from the sixth, away 30
/// days once closed, then back for one visit.
@MainActor
final class AccountInvitationTests: XCTestCase {
    private final class Clock {
        var now = Date(timeIntervalSince1970: 1_790_000_000)
    }

    private func defaults() -> (UserDefaults, String) {
        let suite = "AccountInvitationTests.\(UUID().uuidString)"
        return (UserDefaults(suiteName: suite)!, suite)
    }

    /// The row seen on `count` visits, each starting as the one before ends.
    private func visits(_ count: Int, _ memory: AccountInvitationMemory, _ clock: Clock) {
        for _ in 0..<count {
            memory.seen()
            clock.now += AccountInvitationMemory.visit
        }
    }

    func testTheRowCanBeClosedFromItsSixthVisit() {
        let (store, suite) = defaults()
        defer { store.removePersistentDomain(forName: suite) }
        let clock = Clock()
        let memory = AccountInvitationMemory(defaults: store, now: { clock.now })
        XCTAssertTrue(memory.shows)
        XCTAssertFalse(memory.closable)

        visits(5, memory, clock)
        XCTAssertTrue(memory.shows)
        XCTAssertFalse(memory.closable, "Five visits show it without its ×")

        memory.seen()
        XCTAssertTrue(memory.closable, "The sixth has it")
        clock.now += 3 * AccountInvitationMemory.visit
        memory.seen()
        XCTAssertTrue(memory.closable, "And every one after")
        XCTAssertTrue(memory.shows)
    }

    func testAVisitIsCountedOnceEvery30Minutes() {
        let (store, suite) = defaults()
        defer { store.removePersistentDomain(forName: suite) }
        let clock = Clock()
        let memory = AccountInvitationMemory(defaults: store, now: { clock.now })
        visits(4, memory, clock)

        // The fifth visit: the row comes on screen again and again within it.
        memory.seen()
        clock.now += 10 * 60
        memory.seen()
        clock.now += AccountInvitationMemory.visit - 10 * 60 - 1
        memory.seen()
        XCTAssertFalse(memory.closable, "Still five visits")

        clock.now += 1
        memory.seen()
        XCTAssertTrue(memory.closable)
    }

    func testAClockSetBackStartsAVisit() {
        let (store, suite) = defaults()
        defer { store.removePersistentDomain(forName: suite) }
        let clock = Clock()
        let memory = AccountInvitationMemory(defaults: store, now: { clock.now })
        visits(5, memory, clock)
        clock.now -= 3 * 3_600

        memory.seen()

        XCTAssertTrue(memory.closable)
        // The visit is counted from the time the clock gives now.
        let (other, otherSuite) = defaults()
        defer { other.removePersistentDomain(forName: otherSuite) }
        let fresh = AccountInvitationMemory(defaults: other, now: { clock.now })
        visits(4, fresh, clock)
        clock.now -= 3 * 3_600
        fresh.seen()
        clock.now += 10 * 60
        fresh.seen()
        XCTAssertFalse(fresh.closable, "Ten minutes later is the same visit")
    }

    func testClosedItStaysAway30DaysThenComesBackForOneVisit() {
        let (store, suite) = defaults()
        defer { store.removePersistentDomain(forName: suite) }
        let clock = Clock()
        let memory = AccountInvitationMemory(defaults: store, now: { clock.now })
        visits(6, memory, clock)

        memory.close()

        XCTAssertFalse(memory.shows)
        clock.now += AccountInvitationMemory.away - 1
        XCTAssertFalse(memory.shows)
        clock.now += 1
        XCTAssertTrue(memory.shows, "Back after 30 days")
        XCTAssertTrue(memory.closable, "With its ×")

        // It stays for the visit it comes back for, closed or not, and then is away again.
        memory.seen()
        clock.now += AccountInvitationMemory.visit - 1
        XCTAssertTrue(memory.shows)
        memory.seen()
        clock.now += 1
        XCTAssertFalse(memory.shows)
        clock.now += AccountInvitationMemory.away - AccountInvitationMemory.visit - 1
        XCTAssertFalse(memory.shows)
        clock.now += 1
        XCTAssertTrue(memory.shows, "Back again 30 days after it came back")

        // Closed on the visit it came back for, it is away at once.
        memory.seen()
        memory.close()
        XCTAssertFalse(memory.shows)
        clock.now += AccountInvitationMemory.away
        XCTAssertTrue(memory.shows)
    }

    func testAVisitKeepsWhatItDecidedUntilTheAppIsInUseAgain() {
        let (store, suite) = defaults()
        defer { store.removePersistentDomain(forName: suite) }
        let clock = Clock()
        let memory = AccountInvitationMemory(defaults: store, now: { clock.now })
        visits(5, memory, clock)

        let visit = AccountInvitationVisit(memory: memory)
        XCTAssertTrue(visit.stands)
        XCTAssertFalse(visit.closable)
        visit.appeared()
        XCTAssertTrue(visit.closable, "Counted as it comes on screen: the sixth visit")

        visit.close()
        XCTAssertFalse(visit.stands)
        visit.disappeared()
        XCTAssertFalse(AccountInvitationVisit(memory: memory).stands, "Nor on the next visit")

        // In use again 30 days later: back for one visit, counted as it comes on screen.
        clock.now += AccountInvitationMemory.away
        visit.resume()
        XCTAssertTrue(visit.stands)
        visit.appeared()
        clock.now += AccountInvitationMemory.visit
        XCTAssertTrue(visit.stands, "The visit keeps it until the app is in use again")
        visit.resume()
        XCTAssertFalse(visit.stands)
    }

    func testARowOnScreenIsCountedWhenTheAppIsInUseAgain() {
        let (store, suite) = defaults()
        defer { store.removePersistentDomain(forName: suite) }
        let clock = Clock()
        let memory = AccountInvitationMemory(defaults: store, now: { clock.now })
        let visit = AccountInvitationVisit(memory: memory)

        visit.appeared()
        for _ in 0..<5 {
            clock.now += AccountInvitationMemory.visit
            visit.resume()
        }
        XCTAssertTrue(visit.closable, "Six visits with the row on screen")

        // Off screen, the next visit counts it once it comes on screen.
        let (other, otherSuite) = defaults()
        defer { other.removePersistentDomain(forName: otherSuite) }
        let unseen = AccountInvitationVisit(memory: AccountInvitationMemory(defaults: other, now: { clock.now }))
        unseen.appeared()
        unseen.disappeared()
        for _ in 0..<5 {
            clock.now += AccountInvitationMemory.visit
            unseen.resume()
        }
        XCTAssertFalse(unseen.closable)
    }
}
