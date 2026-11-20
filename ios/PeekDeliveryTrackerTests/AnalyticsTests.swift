import XCTest
@testable import PeekDeliveryTracker

final class AnalyticsTests: XCTestCase {
    func testSharedCatalogExcludesPollingAndPrivateInputs() throws {
        let catalog = try XCTUnwrap(AnalyticsCatalog.bundled)
        XCTAssertEqual(catalog.event(path: "/api/packages/private-id?token=secret", method: "PATCH", body: nil), "parcel-rename")
        XCTAssertNil(catalog.event(path: "/api/sync/jobs?ids=private", method: "GET", body: nil))
        XCTAssertNil(catalog.event(path: "/api/push/devices", method: "POST", body: Data("{\"token\":\"secret\"}".utf8)))
        XCTAssertEqual(catalog.event(path: "/api/friends", method: "POST", body: Data("{\"action\":\"accept_invite\",\"code\":\"secret\"}".utf8)), "friend-invite-accept")
        XCTAssertNil(catalog.event(path: "/api/friends", method: "POST", body: Data("{\"action\":\"private nickname\"}".utf8)))
        for operation in catalog.operations { XCTAssertTrue(catalog.actions.contains(operation.event)) }
    }

    func testDeliveryEmailNamesAreInTheSharedCatalog() throws {
        let catalog = try XCTUnwrap(AnalyticsCatalog.bundled)
        // A name the catalog does not list is never sent.
        for name in ["email-delivery-change", "email-offer-accept", "email-offer-decline", "email-example-open"] {
            XCTAssertTrue(catalog.actions.contains(name), name)
        }
        XCTAssertEqual(catalog.event(path: "/api/packages/private-id/notifications", method: "PATCH", body: Data("{\"emailMuted\":true}".utf8)), "parcel-notifications-change")
        XCTAssertEqual(catalog.event(path: "/api/push/preferences", method: "PATCH", body: Data("{\"emailOnDelivery\":true}".utf8)), "notifications-preferences")
        XCTAssertNil(catalog.event(path: "/api/push/preferences", method: "GET", body: nil))
    }

    @MainActor func testPayloadContainsOnlyFixedNamesAndDeviceMetadata() throws {
        let configuration = DeliveryAnalytics.Configuration(endpoint: URL(string: "https://analytics.example/api/send")!, hostname: "delivery.example", iosWebsite: UUID().uuidString)
        let payload = DeliveryAnalytics.payload(.init(screen: "parcel", name: "parcel-add", outcome: .success, mode: .account), configuration: configuration)
        XCTAssertEqual(payload["url"] as? String, "/parcel")
        XCTAssertEqual(payload["name"] as? String, "parcel-add")
        XCTAssertEqual((payload["data"] as? [String: String])?["platform"], DeliveryAnalytics.Platform.current.rawValue)
        for (platform, device) in [(DeliveryAnalytics.Platform.iphone, "mobile"), (.ipad, "tablet"), (.mac, "desktop")] {
            let payload = DeliveryAnalytics.payload(.init(screen: "parcel", name: nil, outcome: nil, mode: .demo), configuration: configuration, platform: platform)
            XCTAssertEqual((payload["data"] as? [String: String])?["platform"], platform.rawValue)
            XCTAssertEqual(payload["device"] as? String, device)
        }
        for key in ["id", "referrer", "userID", "email", "trackingNumber", "token"] { XCTAssertNil(payload[key]) }
        XCTAssertTrue(configuration.valid(for: URL(string: "https://delivery.example")!))
        XCTAssertFalse(configuration.valid(for: URL(string: "https://another.example")!))
    }

    @MainActor func testConfigurationRejectsCredentialsAndUnencryptedCollection() {
        for endpoint in ["http://analytics.example/api/send", "https://secret@analytics.example/api/send", "https://analytics.example/api/send?token=secret"] {
            let configuration = DeliveryAnalytics.Configuration(endpoint: URL(string: endpoint)!, hostname: "delivery.example", iosWebsite: UUID().uuidString)
            XCTAssertFalse(configuration.valid(for: URL(string: "https://delivery.example")!))
        }
    }

    // MARK: - The counting id

    private let firstID = "00000000-0000-4000-8000-000000000001"
    private let secondID = "00000000-0000-4000-8000-000000000002"

    private func date(_ text: String) -> Date {
        ISO8601DateFormatter().date(from: text)!
    }

    /// A store of its own, so that the tests write nothing to the app's.
    private func defaults() -> (UserDefaults, String) {
        let suite = "AnalyticsTests.\(UUID().uuidString)"
        return (UserDefaults(suiteName: suite)!, suite)
    }

    func testCountsOnceADayAndAgainOnSigningIn() throws {
        let morning = date("2026-11-19T08:00:00Z")
        let first = try XCTUnwrap(CountingID.due(nil, now: morning, signedIn: false, newID: { self.firstID }))
        XCTAssertEqual(first, CountingID(id: firstID, made: "2026-11-19"))
        let counted = first.counted(now: morning, signedIn: false)
        XCTAssertEqual(counted.sent, "2026-11-19")
        XCTAssertEqual(counted.signedIn, false)

        let evening = date("2026-11-19T23:59:59Z")
        XCTAssertNil(CountingID.due(counted, now: evening, signedIn: false))
        // Signing in counts the same day again, with the same id; signing out after it does not.
        let signedIn = try XCTUnwrap(CountingID.due(counted, now: evening, signedIn: true))
        XCTAssertEqual(signedIn.id, firstID)
        let countedSignedIn = signedIn.counted(now: evening, signedIn: true)
        XCTAssertNil(CountingID.due(countedSignedIn, now: evening, signedIn: true))
        XCTAssertNil(CountingID.due(countedSignedIn, now: evening, signedIn: false))

        // The next UTC day counts again, signed in or not.
        let nextDay = date("2026-11-20T00:00:00Z")
        XCTAssertEqual(CountingID.due(countedSignedIn, now: nextDay, signedIn: false)?.id, firstID)
        XCTAssertEqual(CountingID.due(counted, now: nextDay, signedIn: true)?.id, firstID)
    }

    func testReplacesTheIDAfterThirteenMonths() throws {
        let stored = CountingID(id: firstID, made: "2025-10-19", sent: "2026-11-17", signedIn: true)
        XCTAssertEqual(CountingID.due(stored, now: date("2026-11-18T23:59:59Z"), signedIn: false, newID: { self.secondID })?.id, firstID)
        let replaced = try XCTUnwrap(CountingID.due(stored, now: date("2026-11-19T00:00:00Z"), signedIn: false, newID: { self.secondID }))
        XCTAssertEqual(replaced, CountingID(id: secondID, made: "2026-11-19"))
    }

    func testKeepsTheIDAsJSONAndDropsAnUnreadableOne() throws {
        let (store, suite) = defaults()
        defer { store.removePersistentDomain(forName: suite) }
        CountingID(id: firstID, made: "2026-11-19").save(in: store)
        let saved = try XCTUnwrap(store.data(forKey: CountingID.storageKey))
        let object = try XCTUnwrap(JSONSerialization.jsonObject(with: saved) as? [String: Any])
        XCTAssertEqual(Set(object.keys), ["id", "made"])
        CountingID(id: firstID, made: "2026-11-19").counted(now: date("2026-11-19T12:00:00Z"), signedIn: true).save(in: store)
        XCTAssertEqual(CountingID.stored(in: store), CountingID(id: firstID, made: "2026-11-19", sent: "2026-11-19", signedIn: true))
        for broken in [#"{"id":"not-a-uuid","made":"2026-11-19"}"#, #"{"id":"\#(firstID)","made":"2026-13-40"}"#, "[]"] {
            store.set(Data(broken.utf8), forKey: CountingID.storageKey)
            XCTAssertNil(CountingID.stored(in: store), broken)
        }
    }

    @MainActor func testSightingCarriesOnlyTheFourFieldsWithoutCredentials() throws {
        for (platform, sent) in [(DeliveryAnalytics.Platform.iphone, InstallSightingPlatform.iphone), (.ipad, .ipad), (.mac, .mac)] {
            XCTAssertEqual(platform.sighting, sent)
        }
        let sighting = InstallSighting(id: firstID, platform: .ipad, signedIn: false, parcels: 3)
        let request = try DeliveryAnalytics.sightingRequest(sighting, origin: URL(string: "https://delivery.example")!)
        XCTAssertEqual(request.url?.absoluteString, "https://delivery.example/api/public/seen")
        XCTAssertEqual(request.httpMethod, "POST")
        XCTAssertEqual(request.timeoutInterval, 5)
        XCTAssertFalse(request.httpShouldHandleCookies)
        XCTAssertEqual(request.allHTTPHeaderFields, ["Content-Type": "application/json"])
        let body = try XCTUnwrap(JSONSerialization.jsonObject(with: XCTUnwrap(request.httpBody)) as? [String: Any])
        XCTAssertEqual(Set(body.keys), ["id", "platform", "signedIn", "parcels"])
        XCTAssertEqual(body["id"] as? String, firstID)
        XCTAssertEqual(body["platform"] as? String, "ipad")
        XCTAssertEqual(body["signedIn"] as? Bool, false)
        XCTAssertEqual(body["parcels"] as? Int, 3)
    }

    @MainActor func testAnInstallWhoseReaderTurnedStatisticsOffStaysOff() {
        let (store, suite) = defaults()
        defer { store.removePersistentDomain(forName: suite) }
        XCTAssertTrue(DeliveryAnalytics(defaults: store).enabled)
        store.set(false, forKey: DeliveryAnalytics.preferenceKey)
        XCTAssertFalse(DeliveryAnalytics(defaults: store).enabled)
    }

    /// Settings has no Usage statistics row: the app's copy holds no words for one.
    func testSettingsHasNoUsageStatisticsRow() throws {
        let url = try XCTUnwrap(Bundle.main.url(forResource: "Localization", withExtension: "json"))
        let dictionaries = try JSONDecoder().decode([String: [String: String]].self, from: Data(contentsOf: url))
        for (language, values) in dictionaries {
            XCTAssertEqual(values.keys.filter { $0.hasPrefix("settings.usageStatistics") }, [], language)
        }
    }
}
