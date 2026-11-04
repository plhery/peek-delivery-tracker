import XCTest
@testable import PeekDeliveryTracker

@MainActor
final class PullToRefreshTests: XCTestCase {
    func testPullIsMeasuredFromTheRestingInsetWhileTheSystemHoldsItsGap() {
        let model = PullToRefreshModel()
        model.track(PullGeometry(overscroll: 0, inset: 164, height: 874))
        XCTAssertEqual(model.threshold, 174.8, accuracy: 0.01)
        model.track(PullGeometry(overscroll: 87.4, inset: 164, height: 874))
        XCTAssertEqual(model.progress, 0.5, accuracy: 0.001)

        // The system starts refreshing mid-pull and adds its gap to the inset,
        // sometimes before the refresh action has run.
        model.track(PullGeometry(overscroll: 118.7, inset: 224, height: 874))
        XCTAssertEqual(model.distance, 178.7, accuracy: 0.01)
        model.begin(label: "Checking for updates…")
        model.track(PullGeometry(overscroll: 110, inset: 224, height: 874))
        XCTAssertEqual(model.distance, 170, accuracy: 0.01)
        XCTAssertEqual(model.holdDistance, 60)
        XCTAssertEqual(model.progress, 1)

        // After the finger lifts, the gap stays open at its held height.
        model.track(PullGeometry(overscroll: 0, inset: 224, height: 874))
        XCTAssertEqual(model.distance, 60)
    }

    func testGrownWordsGetRoomOnlyWhileRefreshing() async {
        let model = PullToRefreshModel()
        model.track(PullGeometry(overscroll: 0, inset: 164, height: 874))
        // At the default sizes the seal and its words keep to the gap the system holds, where they always stood.
        XCTAssertEqual(PullToRefreshModel.offset(gap: 60, height: PullToRefreshModel.height), -0.55 * 60 - 21, accuracy: 0.001)
        model.begin(label: "Checking for updates…")
        model.track(PullGeometry(overscroll: 0, inset: 224, height: 874))
        XCTAssertEqual(model.room, 0)

        // Two lines of the largest text: the list moves down by what the 60-point gap lacks.
        model.grow(to: 132)
        XCTAssertEqual(model.room, 94)
        let gap = model.holdDistance + model.room
        let top = PullToRefreshModel.offset(gap: gap, height: 132)
        XCTAssertGreaterThanOrEqual(top, -gap, "clear of the bar")
        XCTAssertLessThanOrEqual(top + 132, 0, "clear of the cards")

        // A finger holding the list further down leaves less to make up.
        model.track(PullGeometry(overscroll: 50, inset: 224, height: 874))
        XCTAssertEqual(model.room, 44)
        model.track(PullGeometry(overscroll: 0, inset: 224, height: 874))
        model.finish(succeeded: true, label: "Tracking updated")
        XCTAssertEqual(model.room, 94)

        // The list goes back up as the system closes its gap.
        model.settle()
        XCTAssertEqual(model.room, 0)
        await model.settleTask?.value
        XCTAssertEqual(model.room, 0)
    }

    func testResultAppearsOnlyAtTheEndAndClearsOnceSettled() async {
        let model = PullToRefreshModel()
        model.begin(label: "Checking for updates…")
        XCTAssertEqual(model.phase, .refreshing)
        XCTAssertEqual(model.starts, 1)
        XCTAssertNil(model.result)

        model.update(label: "Still checking. Updates will appear here.")
        XCTAssertEqual(model.label, "Still checking. Updates will appear here.")
        XCTAssertNil(model.result)

        model.finish(succeeded: true, label: "Tracking updated")
        XCTAssertEqual(model.result, .succeeded)
        model.update(label: "Still checking. Updates will appear here.")
        XCTAssertEqual(model.label, "Tracking updated")

        model.settle()
        XCTAssertEqual(model.phase, .settling)
        XCTAssertEqual(model.result, .succeeded, "the result fades out with the seal")
        await model.settleTask?.value
        XCTAssertEqual(model.phase, .idle)
        XCTAssertNil(model.result)
        XCTAssertNil(model.label)
        XCTAssertEqual(model.distance, 0)
    }

    func testSealStaysHiddenUntilTheContentReturnsToTheTopAfterARefresh() async {
        let model = PullToRefreshModel()
        model.track(PullGeometry(overscroll: 0, inset: 164, height: 874))
        model.track(PullGeometry(overscroll: 180, inset: 164, height: 874))
        model.begin(label: "Checking for updates…")
        model.finish(succeeded: true, label: "Tracking updated")
        model.settle()
        // The finger still holds the content down when the refresh ends.
        model.track(PullGeometry(overscroll: 150, inset: 164, height: 874))
        await model.settleTask?.value
        XCTAssertEqual(model.phase, .idle)
        XCTAssertTrue(model.awaitingRest)
        model.track(PullGeometry(overscroll: 90, inset: 164, height: 874))
        XCTAssertTrue(model.awaitingRest)
        model.track(PullGeometry(overscroll: 0, inset: 164, height: 874))
        XCTAssertFalse(model.awaitingRest)
        model.track(PullGeometry(overscroll: 40, inset: 164, height: 874))
        XCTAssertEqual(model.distance, 40)
        XCTAssertFalse(model.awaitingRest)
    }

    func testRefreshStartedWhileSettlingIsNotResetByTheEarlierOne() async throws {
        let model = PullToRefreshModel()
        model.begin(label: "Checking for updates…")
        model.finish(succeeded: false, label: "Couldn’t check. Try again.")
        XCTAssertEqual(model.result, .failed)
        model.settle()
        let earlier = try XCTUnwrap(model.settleTask)
        model.begin(label: "Checking for updates…")
        XCTAssertNil(model.result)
        await earlier.value
        XCTAssertEqual(model.phase, .refreshing)
        XCTAssertEqual(model.label, "Checking for updates…")
        XCTAssertEqual(model.starts, 2)
    }
}
