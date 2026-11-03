import XCTest
@testable import PeekDeliveryTracker

/// What Siri says for the next delivery, and what Add a parcel leaves for Add.
@MainActor
final class ShortcutIntentsTests: XCTestCase {
    private let english = ExtensionLocalizer(languageCode: "en")

    private func parcel(_ label: String, carrier: String = "DHL", detail: String = "Tomorrow", outForDelivery: Bool = false,
                        deliveredAt: Date? = nil) -> DeliveryWidgetParcel {
        DeliveryWidgetParcel(id: UUID(), label: label, carrier: carrier, trackingNumber: "TEST0000000001", detail: detail,
                             isOutForDelivery: outForDelivery, deliveredAt: deliveredAt)
    }

    private func snapshot(_ parcels: [DeliveryWidgetParcel], language: String = "en") -> DeliveryWidgetSnapshot {
        DeliveryWidgetSnapshot(generatedAt: Date(), languageCode: language, parcels: parcels)
    }

    func testWithoutTheWidgetsParcelsItAsksForPeek() {
        XCTAssertEqual(NextDeliveryAnswer.text(snapshot: nil, enabled: true, copy: english), "Open Peek to see your next delivery.")
        XCTAssertEqual(NextDeliveryAnswer.text(snapshot: snapshot([parcel("Coffee beans")]), enabled: false, copy: english),
                       "Open Peek to see your next delivery.")
    }

    func testSaysWhenNothingIsOnTheWay() {
        XCTAssertEqual(NextDeliveryAnswer.text(snapshot: snapshot([]), enabled: true, copy: english), "Nothing on the way right now")
        // A parcel delivered in the last day stays in the widget, but isn't on its way.
        let delivered = parcel("New shoes", detail: "Today", deliveredAt: Date())
        XCTAssertEqual(NextDeliveryAnswer.text(snapshot: snapshot([delivered]), enabled: true, copy: english),
                       "Nothing on the way right now")
    }

    func testNamesTheOneParcelOnItsWay() {
        let delivered = parcel("New shoes", detail: "Today", deliveredAt: Date())
        XCTAssertEqual(
            NextDeliveryAnswer.text(snapshot: snapshot([parcel("Coffee beans", carrier: "La Poste"), delivered]), enabled: true,
                                    copy: english),
            "Your next delivery is “Coffee beans” with La Poste: Tomorrow."
        )
    }

    func testNamesTheWidgetsFirstParcelAndCountsTheOthers() {
        let first = parcel("Coffee beans", detail: "Out for delivery", outForDelivery: true)
        let later = [parcel("Lamp", carrier: "UPS"), parcel("Book", carrier: "GLS")]
        let delivered = parcel("New shoes", detail: "Today", deliveredAt: Date())
        XCTAssertEqual(
            NextDeliveryAnswer.text(snapshot: snapshot([first, later[0], delivered]), enabled: true, copy: english),
            "Your next delivery is “Coffee beans” with DHL: Out for delivery. 1 more parcel is on the way."
        )
        XCTAssertEqual(
            NextDeliveryAnswer.text(snapshot: snapshot([first] + later + [delivered]), enabled: true, copy: english),
            "Your next delivery is “Coffee beans” with DHL: Out for delivery. 2 more parcels are on the way."
        )
    }

    func testCountsInTheLanguagesForms() {
        let polish = ExtensionLocalizer(languageCode: "pl")
        let parcels = { (count: Int) in self.snapshot((0..<count).map { self.parcel("Paczka \($0)", detail: "Jutro") }, language: "pl") }
        XCTAssertTrue(NextDeliveryAnswer.text(snapshot: parcels(4), enabled: true, copy: polish)
            .hasSuffix("W drodze są jeszcze 3 przesyłki."))
        XCTAssertTrue(NextDeliveryAnswer.text(snapshot: parcels(6), enabled: true, copy: polish)
            .hasSuffix("W drodze jest jeszcze 5 przesyłek."))
    }

    func testKeepsANamesOwnBraces() {
        XCTAssertEqual(NextDeliveryAnswer.text(snapshot: snapshot([parcel("Gift {{carrier}}")]), enabled: true, copy: english),
                       "Your next delivery is “Gift {{carrier}}” with DHL: Tomorrow.")
    }

    func testAddParcelLeavesTheNumberAndNameForAdd() {
        let suite = "shortcut-intents-tests"
        let defaults = UserDefaults(suiteName: suite)!
        defer { defaults.removePersistentDomain(forName: suite) }
        let now = Date()
        XCTAssertTrue(AddParcelIntent.save(trackingNumber: "  TEST0000000001\n", name: " Coffee shop ", defaults: defaults, now: now))
        let draft = ShareInbox.consume(defaults: defaults, now: now)
        XCTAssertEqual(draft?.trackingInput, "TEST0000000001")
        XCTAssertEqual(draft?.label, "Coffee shop")
        XCTAssertEqual(draft?.createdAt, now)

        XCTAssertTrue(AddParcelIntent.save(trackingNumber: "TEST0000000002", name: nil, defaults: defaults, now: now))
        XCTAssertEqual(ShareInbox.consume(defaults: defaults, now: now)?.label, "")
    }

    func testAddParcelFailsWithoutTheAppGroup() {
        XCTAssertFalse(AddParcelIntent.save(trackingNumber: "TEST0000000001", name: nil, defaults: nil))
    }
}
