import UserNotifications
import XCTest
@testable import PeekDeliveryTracker

/// Parcels followed on the iPhone without an account.
@MainActor
final class DeviceParcelTests: XCTestCase {
    private final class Memory: DeviceParcelStorage {
        var parcels: [DeviceParcel] = []
        var keys: [String: String] = [:]
        var forgetting: [String: String] = [:]
        var unqueued: Set<String> = []
        func loadUnqueuedForgetIDs() -> Set<String> { unqueued }
        func saveUnqueuedForgetIDs(_ ids: Set<String>) throws { unqueued = ids }
        var failQueueWrite = false
        func load() -> (parcels: [DeviceParcel], keys: [String: String]) { (parcels, keys) }
        func save(_ parcels: [DeviceParcel], keys: [String: String]) { self.parcels = parcels; self.keys = keys }
        func loadForgetting() -> [String: String] { forgetting }
        func saveForgetting(_ links: [String: String]) throws {
            if failQueueWrite { throw AuthenticationError.sessionSaveFailed }
            forgetting = links
        }
    }

    /// What the service was asked, in order.
    private final class Calls: @unchecked Sendable {
        private let lock = NSLock()
        private var log: [String] = []
        private var countryHints: [String?] = []
        private var postcodes: [String?] = []
        private var languages: [String?] = []
        private var digits: [String?] = []
        func add(_ call: String) { lock.withLock { log.append(call) } }
        func lookupCountry(_ hint: String?) { lock.withLock { countryHints.append(hint) } }
        func lookupPostcode(_ postcode: String?) { lock.withLock { postcodes.append(postcode) } }
        func lookupLanguage(_ language: String?) { lock.withLock { languages.append(language) } }
        func lookupPhoneDigits(_ phoneDigits: String?) { lock.withLock { digits.append(phoneDigits) } }
        var all: [String] { lock.withLock { log } }
        var lookupCountries: [String?] { lock.withLock { countryHints } }
        var lookupPostcodes: [String?] { lock.withLock { postcodes } }
        var lookupLanguages: [String?] { lock.withLock { languages } }
        var lookupPhoneDigits: [String?] { lock.withLock { digits } }
    }

    private let key = String(repeating: "k", count: 43)

    private func given(_ carrier: CarrierID, _ postcode: String) -> GivenPostcode { GivenPostcode(carrier: carrier, postcode: postcode) }

    /// The carrier's postcode field for a number of that shape.
    private func postcodeField(_ carrier: CarrierID, _ number: String) throws -> CarrierRequirement {
        try XCTUnwrap(CarrierCatalog.shared.requirements(for: carrier, trackingNumber: number).first { $0.field == .dpdPostcode })
    }

    private func defaults() -> (UserDefaults, String) {
        let suite = "DeviceParcelTests.\(UUID().uuidString)"
        return (UserDefaults(suiteName: suite)!, suite)
    }

    private func package(_ id: UUID, number: String = "TESTPARCEL123789", carrier: CarrierID = .dhl,
                         stage: TrackingStage? = .inTransit, sync: SyncStatus = .ok) -> PublicPackage {
        PublicPackage(
            id: id, trackingNumber: number, label: "", carrier: carrier, createdAt: "2026-10-01T08:00:00Z",
            syncStatus: sync, carrierData: PublicPackageCarrierData(), notificationsMuted: false,
            trackingEvents: stage.map { [TrackingEvent(id: UUID(), packageID: id, stage: $0, description: "", occurredAt: "2026-10-02T08:00:00Z")] } ?? []
        )
    }

    private func link(_ id: String, shared: Bool = true) -> ParcelLink {
        ParcelLink(id: id, role: .owner, kind: .lookup, createdAt: "2026-10-01T08:00:00Z", numberShown: true, canKeep: true,
                   showNumber: false, gift: false, shared: shared)
    }

    private func client(_ calls: Calls, lookup: PublicLookupResponse? = nil,
                        read: @escaping @Sendable (String) throws -> PublicParcelResponse? = { _ in nil },
                        alert: @escaping @Sendable (String) throws -> DeviceAlertAnswer = { _ in .on },
                        unalert: @escaping @Sendable (String) throws -> Void = { _ in },
                        forget: @escaping @Sendable (String) throws -> Void = { _ in },
                        move: @escaping @Sendable (String) throws -> PublicPackage? = { _ in nil }) -> DeviceParcelClient {
        DeviceParcelClient(
            lookup: { request in
                calls.add("lookup \(request.trackingNumber) \(request.carrier?.rawValue ?? "-")")
                calls.lookupCountry(request.lookupCountryHint)
                calls.lookupPostcode(request.dpdPostcode)
                calls.lookupLanguage(request.language)
                calls.lookupPhoneDigits(request.phoneDigits)
                guard let lookup else { throw DeviceLookupsSpent() }
                return lookup
            },
            read: { linkID, key, hidden in
                calls.add("read \(linkID) \((key ?? "-").prefix(1))\(hidden ? " hidden" : "")")
                return try read(linkID)
            },
            forget: { linkID, _ in
                calls.add("forget \(linkID)")
                try forget(linkID)
            },
            changeCarrier: { linkID, key, change in
                calls.add("carrier \(linkID) \(key.prefix(1)) \(change.carrier.rawValue)")
                calls.lookupCountry(change.lookupCountryHint)
                calls.lookupPostcode(change.dpdPostcode)
                calls.lookupLanguage(change.language)
                calls.lookupPhoneDigits(change.phoneDigits)
                return try move(linkID).map {
                    PublicParcelResponse(link: ParcelLink(id: linkID, role: .owner, kind: .lookup, createdAt: "2026-10-01T08:00:00Z",
                                                          numberShown: true, canKeep: true, showNumber: false, gift: false, shared: true),
                                         package: $0)
                }
            },
            update: { linkID, _, change in
                calls.add("update \(linkID) shared=\(change.shared.map(String.init) ?? "-")")
                return PublicParcelResponse(link: ParcelLink(id: linkID, role: .owner, kind: .lookup, createdAt: "2026-10-01T08:00:00Z",
                                                             numberShown: true, canKeep: true, showNumber: change.showNumber ?? false,
                                                             gift: change.gift ?? false, shared: change.shared ?? true),
                                            package: PublicPackage(id: UUID(), label: "", carrier: .dhl, createdAt: "2026-10-01T08:00:00Z", syncStatus: .ok,
                                                                   carrierData: PublicPackageCarrierData(), notificationsMuted: false, trackingEvents: []))
            },
            detect: { number, typed, prefixOfSaved in
                calls.add("detect \(number)\(typed.map { " as \($0)" } ?? "")\(prefixOfSaved ? " prefix" : "")")
                throw DeliveryAPIError.serviceFailed(503)
            },
            setAlert: { linkID, key, request in
                calls.add("alert \(linkID) \((key ?? "-").prefix(1)) \(request.preset.rawValue) \(request.token) \(request.locale.rawValue) \(request.environment.rawValue)")
                return try alert(linkID)
            },
            removeAlert: { linkID, request in calls.add("unalert \(linkID) \(request.token)"); try unalert(linkID) }
        )
    }

    private func reader(_ id: String, canKeep: Bool = true) -> ParcelLink {
        ParcelLink(id: id, role: .viewer, kind: .shared, createdAt: "2026-10-01T08:00:00Z", numberShown: false, canKeep: canKeep)
    }

    /// iOS as a test sees it: no notifications, and nothing asked.
    private func quietNotifications() -> DeviceNotificationAccess {
        DeviceNotificationAccess(status: { .denied }, request: {}, register: {}, token: { nil })
    }

    func testALookupIsKeptWithItsKeyUnderTheNameGivenHere() async throws {
        let id = UUID()
        let storage = Memory()
        let calls = Calls()
        let device = DeviceParcels(client: client(calls, lookup: PublicLookupResponse(link: link("22222222222A"), key: key, package: package(id))), storage: storage)

        let parcel = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", label: "New sneakers", carrier: .dhl))

        XCTAssertEqual(parcel.id, id)
        XCTAssertEqual(parcel.label, "New sneakers")
        XCTAssertEqual(device.list().map(\.label), ["New sneakers"])
        XCTAssertEqual(storage.keys, ["22222222222A": key])
        // The service never learns the name: it stays beside the parcel on the device.
        XCTAssertEqual(storage.parcels.first?.parcel.label, "")
        XCTAssertEqual(device.claims, [ClaimParcelLink(id: "22222222222A", key: key, label: "New sneakers")])
        XCTAssertEqual(calls.all, ["lookup TESTPARCEL123789 dhl"])
        XCTAssertEqual(calls.lookupCountries, [nil])
    }

    func testCountryHintUsesTheDeviceRegionRatherThanItsLanguage() {
        XCTAssertEqual(ParcelLookupCountry.hint(locale: Locale(identifier: "en_CH")), "CH")
        XCTAssertEqual(ParcelLookupCountry.hint(locale: Locale(identifier: "fr_US")), "US")
        XCTAssertEqual(ParcelLookupCountry.hint(locale: Locale(identifier: "de_GB")), "GB")
        XCTAssertEqual(ParcelLookupCountry.hint(locale: Locale(identifier: "en_UK")), "GB")
        for identifier in ["en", "en_001", "es_419", "en_EU", "en_UN", "en_ZZ", "en_AA"] {
            XCTAssertNil(ParcelLookupCountry.hint(locale: Locale(identifier: identifier)), identifier)
        }
    }

    func testANewGuestLookupForwardsItsCountryHint() async throws {
        let calls = Calls()
        let device = DeviceParcels(client: client(calls, lookup: PublicLookupResponse(
            link: link("22222222222A"), key: key, package: package(UUID())
        )), storage: Memory())

        _ = try await device.add(CreatePackageRequest(
            trackingNumber: "TESTPARCEL123789", carrier: .dhl, lookupCountryHint: "CH"
        ))

        XCTAssertEqual(calls.lookupCountries, ["CH"])
        // A carrier that words its scans in several languages answers the parcel in the app's.
        XCTAssertEqual(calls.lookupLanguages, [AppLanguage.current.rawValue])
        XCTAssertEqual(calls.all, ["lookup TESTPARCEL123789 dhl"])
    }

    func testChangingCarrierMovesTheSameLinkWithTheDeviceCountryAndKeepsItsNameArchiveAndNotifications() async throws {
        let previousID = UUID(), nextID = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        var kept = DeviceParcel(
            linkID: "22222222222A", label: "Moon lamp", archivedAt: "2026-10-03T08:00:00Z",
            parcel: Parcel(shared: package(previousID)), checkedAt: Date()
        )
        kept.alertToken = "token1"
        kept.alertPreset = .all
        storage.parcels = [kept]
        let calls = Calls()
        let moved = package(nextID, carrier: .dpd)
        let device = DeviceParcels(client: client(calls, move: { _ in moved }), storage: storage)

        let parcel = try await device.changeCarrier(id: previousID, carrier: .dpd, trackingURL: nil, dpdPostcode: nil)

        XCTAssertEqual(parcel.id, nextID)
        XCTAssertEqual(parcel.label, "Moon lamp")
        XCTAssertEqual(parcel.archivedAt, "2026-10-03T08:00:00Z")
        XCTAssertEqual(parcel.carrier, .dpd)
        XCTAssertEqual(calls.lookupCountries, [ParcelLookupCountry.hint()])
        XCTAssertEqual(calls.lookupLanguages, [AppLanguage.current.rawValue])
        // One link, which keeps its sharing and notifications: no second lookup, nothing forgotten or turned off.
        XCTAssertEqual(calls.all, ["carrier 22222222222A k dpd"])
        XCTAssertEqual(storage.parcels.map(\.linkID), ["22222222222A"])
        XCTAssertEqual(storage.parcels.map(\.replaced), [previousID])
        XCTAssertEqual(storage.parcels.map(\.alertToken), ["token1"])
        XCTAssertEqual(storage.keys, ["22222222222A": key])
        XCTAssertEqual(storage.forgetting, [:])
        XCTAssertEqual(device.claims, [ClaimParcelLink(id: "22222222222A", key: key, label: "Moon lamp")])
    }

    func testALinkTheServiceNoLongerHasIsLookedUpAgainUnderItsName() async throws {
        let previousID = UUID(), nextID = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(
            linkID: "22222222222A", label: "Moon lamp", archivedAt: nil,
            parcel: Parcel(shared: package(previousID)), checkedAt: Date()
        )]
        let calls = Calls()
        let device = DeviceParcels(client: client(calls, lookup: PublicLookupResponse(
            link: link("22222222222B"), key: key, package: package(nextID, carrier: .dpd)
        )), storage: storage)

        let parcel = try await device.changeCarrier(id: previousID, carrier: .dpd, trackingURL: nil, dpdPostcode: nil)

        XCTAssertEqual(parcel.id, nextID)
        XCTAssertEqual(parcel.label, "Moon lamp")
        XCTAssertEqual(calls.all, ["carrier 22222222222A k dpd", "lookup TESTPARCEL123789 dpd", "forget 22222222222A"])
        XCTAssertEqual(calls.lookupCountries, [ParcelLookupCountry.hint(), ParcelLookupCountry.hint()])
        XCTAssertEqual(storage.parcels.map(\.replaced), [previousID])
        XCTAssertEqual(storage.keys, ["22222222222B": key])
    }

    func testAMovedLinkTakesTheForgottenCopysPlaceAndName() async throws {
        let previousID = UUID(), copyID = UUID(), nextID = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        var copy = DeviceParcel(linkID: "22222222222C", label: "Moon lamp", archivedAt: nil,
                                parcel: Parcel(shared: package(copyID, carrier: .dpd)), checkedAt: Date())
        copy.frozen = "2026-11-01T08:00:00Z"
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(previousID)), checkedAt: Date()),
            copy,
        ]
        let calls = Calls()
        let moved = package(nextID, carrier: .dpd)
        let device = DeviceParcels(client: client(calls, move: { _ in moved }), storage: storage)

        // The copy no longer updated is no parcel followed twice.
        let parcel = try await device.changeCarrier(id: previousID, carrier: .dpd, trackingURL: nil, dpdPostcode: nil)

        XCTAssertEqual(parcel.label, "Moon lamp")
        XCTAssertEqual(device.list().map(\.id), [nextID])
        XCTAssertEqual(calls.all, ["carrier 22222222222A k dpd"])
    }

    func testANewPostcodeForTheSameCarrierMovesTheLinkToTheParcelWithIt() async throws {
        let (defaults, suite) = defaults()
        defer { defaults.removePersistentDomain(forName: suite) }
        let previousID = UUID(), nextID = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(
            linkID: "22222222222A", label: "Moon lamp", archivedAt: nil,
            parcel: Parcel(shared: package(previousID, carrier: .dpd)), checkedAt: Date()
        )]
        let calls = Calls()
        let memory = PostcodeMemory(defaults: defaults)
        let moved = package(nextID, carrier: .dpd)
        let device = DeviceParcels(client: client(calls, move: { _ in moved }), storage: storage, postcodes: memory)

        let parcel = try await device.changeCarrier(id: previousID, carrier: .dpd, trackingURL: nil, dpdPostcode: "75002")

        XCTAssertEqual(parcel.id, nextID)
        XCTAssertEqual(parcel.label, "Moon lamp")
        XCTAssertEqual(parcel.carrierData?.originalPackageID, previousID)
        XCTAssertEqual(calls.all, ["carrier 22222222222A k dpd"])
        XCTAssertEqual(calls.lookupPostcodes, ["75002"])
        XCTAssertEqual(device.list().map(\.id), [nextID])
        XCTAssertEqual(storage.parcels.map(\.replaced), [previousID])
        XCTAssertEqual(storage.parcels.map(\.postcodeGiven), [true])
        XCTAssertEqual(storage.keys, ["22222222222A": key])
        XCTAssertEqual(memory.entries, [given(.dpd, "75002")])
    }

    func testTheSameDetailsGivenAgainKeepWhatTheDeviceKnowsOfTheParcel() async throws {
        let (defaults, suite) = defaults()
        defer { defaults.removePersistentDomain(forName: suite) }
        let id = UUID(), earlierID = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(
            linkID: "22222222222A", label: "Moon lamp", archivedAt: "2026-10-03T08:00:00Z",
            parcel: Parcel(shared: package(id, carrier: .dpd)), checkedAt: Date(), replaced: earlierID
        )]
        let calls = Calls()
        // The parcel already has these details: the service answers it as it stands.
        let moved = package(id, carrier: .dpd, stage: .outForDelivery)
        let device = DeviceParcels(client: client(calls, move: { _ in moved }),
                                   storage: storage, postcodes: PostcodeMemory(defaults: defaults))

        let parcel = try await device.changeCarrier(id: id, carrier: .dpd, trackingURL: nil, dpdPostcode: "75001")

        XCTAssertEqual(parcel.id, id)
        XCTAssertEqual(parcel.label, "Moon lamp")
        XCTAssertEqual(parcel.archivedAt, "2026-10-03T08:00:00Z")
        XCTAssertEqual(parcel.carrierData?.originalPackageID, earlierID)
        XCTAssertEqual(parcel.currentStage, .outForDelivery)
        XCTAssertEqual(device.list(), [parcel])
        XCTAssertEqual(storage.parcels.map(\.linkID), ["22222222222A"])
        XCTAssertEqual(storage.keys, ["22222222222A": key])
        XCTAssertEqual(calls.all, ["carrier 22222222222A k dpd"])
    }

    func testChangingToTheNumberAndCarrierOfAnotherParcelIsRefused() async throws {
        let changing = UUID(), other = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(changing, carrier: .dhl)), checkedAt: Date()),
            DeviceParcel(linkID: "22222222222B", label: "", archivedAt: nil, parcel: Parcel(shared: package(other, carrier: .dpd)), checkedAt: Date()),
        ]
        let calls = Calls()
        let device = DeviceParcels(client: client(calls, lookup: PublicLookupResponse(
            link: link("22222222222C"), key: key, package: package(UUID(), carrier: .dpd)
        )), storage: storage)

        do {
            _ = try await device.changeCarrier(id: changing, carrier: .dpd, trackingURL: nil, dpdPostcode: nil)
            XCTFail("A second lookup went out")
        } catch DeliveryAPIError.duplicateTracking(let existing) {
            XCTAssertEqual(existing, other)
        }
        XCTAssertEqual(calls.all, [])
        XCTAssertEqual(device.list().map(\.id), [changing, other])
    }

    func testTheCountryHintIsOptionalInDetectionAndAddRequests() throws {
        func body<T: Encodable>(_ request: T) throws -> [String: String] {
            try XCTUnwrap(JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode(request)) as? [String: String])
        }
        XCTAssertNil(try body(CarrierDetectionRequest(trackingNumber: "SYNTHETIC"))["lookupCountryHint"])
        XCTAssertEqual(try body(CarrierDetectionRequest(trackingNumber: "SYNTHETIC", lookupCountryHint: "CH"))["lookupCountryHint"], "CH")
        XCTAssertEqual(try body(CreatePackageRequest(trackingNumber: "SYNTHETIC", lookupCountryHint: "US"))["lookupCountryHint"], "US")
        XCTAssertEqual(try body(PublicLookupRequest(trackingNumber: "SYNTHETIC", lookupCountryHint: "GB"))["lookupCountryHint"], "GB")
    }

    func testPhoneDigitsGoInARequestOnlyWhenGiven() throws {
        func body<T: Encodable>(_ request: T) throws -> [String: Any] {
            try XCTUnwrap(JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode(request)) as? [String: Any])
        }
        XCTAssertNil(try body(CreatePackageRequest(trackingNumber: "JMX000000000001", carrier: .jAndTMx))["phoneDigits"])
        XCTAssertEqual(try body(CreatePackageRequest(trackingNumber: "JMX000000000001", carrier: .jAndTMx, phoneDigits: "0472"))["phoneDigits"] as? String, "0472")
        XCTAssertNil(try body(ChangePackageCarrierRequest(carrier: .jAndTMx))["phoneDigits"])
        XCTAssertEqual(try body(ChangePackageCarrierRequest(carrier: .jAndTMx, phoneDigits: "0472"))["phoneDigits"] as? String, "0472")
        XCTAssertNil(try body(PublicLookupRequest(trackingNumber: "JMX000000000001", carrier: .jAndTMx))["phoneDigits"])
        XCTAssertEqual(try body(PublicLookupRequest(trackingNumber: "JMX000000000001", carrier: .jAndTMx, phoneDigits: "0472"))["phoneDigits"] as? String, "0472")
    }

    func testALookupGivesItsCarrierThePhoneDigitsAndTheIPhoneKeepsNone() async throws {
        let (defaults, suite) = defaults()
        defer { defaults.removePersistentDomain(forName: suite) }
        let memory = PostcodeMemory(defaults: defaults)
        let storage = Memory()
        let calls = Calls()
        // A set clock, a set id and no events: the file then holds only what the test chose, where a date or a random id
        // could hold the digits by chance.
        let id = try XCTUnwrap(UUID(uuidString: "00000000-0000-4000-8000-000000000001"))
        let found = PublicLookupResponse(
            link: link("22222222222A"), key: key, package: package(id, number: "JMX000000000001", carrier: .jAndTMx, stage: nil)
        )
        let now = try XCTUnwrap(ISO8601DateFormatter().date(from: "2026-10-02T09:00:00Z"))
        let device = DeviceParcels(client: client(calls, lookup: found), storage: storage, postcodes: memory, now: { now })

        let parcel = try await device.add(CreatePackageRequest(trackingNumber: "JMX000000000001", carrier: .jAndTMx, phoneDigits: "0472"))

        XCTAssertEqual(calls.lookupPhoneDigits, ["0472"])
        // The link never shows them, and the iPhone keeps none: not with the parcel, nor for the next one's field.
        XCTAssertNil(parcel.phoneDigits)
        XCTAssertFalse(String(decoding: try JSONEncoder.deliveryTracker.encode(storage.parcels), as: UTF8.self).contains("0472"))
        XCTAssertEqual(memory.entries, [])
    }

    func testChangingTheCarrierOfAParcelFollowedHereSendsTheDigitsGivenForIt() async throws {
        let previousID = UUID(), nextID = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(
            linkID: "22222222222A", label: "Moon lamp", archivedAt: nil,
            parcel: Parcel(shared: package(previousID, number: "JMX000000000001")), checkedAt: Date()
        )]
        let calls = Calls()
        let moved = package(nextID, number: "JMX000000000001", carrier: .jAndTMx)
        let device = DeviceParcels(client: client(calls, move: { _ in moved }),
                                   storage: storage)

        let parcel = try await device.changeCarrier(id: previousID, carrier: .jAndTMx, trackingURL: nil, dpdPostcode: nil, phoneDigits: "0472")

        XCTAssertEqual(parcel.id, nextID)
        XCTAssertEqual(parcel.label, "Moon lamp")
        XCTAssertEqual(calls.lookupPhoneDigits, ["0472"])
        XCTAssertEqual(calls.all, ["carrier 22222222222A k j-and-t-mx"])
    }

    func testDetectionSendsTheNumberAsTypedWhenItSpellsTheNumberDifferently() async throws {
        XCTAssertEqual(CarrierDetectionRequest.typedNumber(" test-1234-5 ", for: "TEST12345"), "test-1234-5")
        XCTAssertEqual(CarrierDetectionRequest.typedNumber("TEST 1234 5", for: "TEST12345"), "TEST 1234 5")
        XCTAssertNil(CarrierDetectionRequest.typedNumber("TEST12345", for: "TEST12345"))
        XCTAssertNil(CarrierDetectionRequest.typedNumber("TEST-1234-6", for: "TEST12345"), "Another number is not this one as typed")
        XCTAssertNil(CarrierDetectionRequest.typedNumber(nil, for: "TEST12345"))
        XCTAssertNil(CarrierDetectionRequest.typedNumber(String(repeating: "1-", count: 41), for: String(repeating: "1", count: 41)))

        let body = try XCTUnwrap(JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode(
            CarrierDetectionRequest(trackingNumber: "TEST12345", typedNumber: "TEST-1234-5")
        )) as? [String: Any])
        XCTAssertEqual(body["trackingNumber"] as? String, "TEST12345")
        XCTAssertEqual(body["typedNumber"] as? String, "TEST-1234-5")
        let bare = try XCTUnwrap(JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode(
            CarrierDetectionRequest(trackingNumber: "TEST12345")
        )) as? [String: Any])
        XCTAssertNil(bare["typedNumber"])

        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: Memory())
        _ = try? await device.detect(trackingNumber: "TEST12345", typedNumber: "TEST-1234-5")
        _ = try? await device.detect(trackingNumber: "TEST12345")
        XCTAssertEqual(calls.all, ["detect TEST12345 as TEST-1234-5", "detect TEST12345"])
    }

    func testTheStartOfANumberThisIPhoneFollowsSaysSoWhenAsked() async throws {
        let calls = Calls()
        let device = DeviceParcels(client: client(calls, lookup: PublicLookupResponse(link: link("22222222222A"), key: key, package: package(UUID()))), storage: Memory())
        _ = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", carrier: .dhl))

        _ = try? await device.detect(trackingNumber: "TESTPARCEL12")
        _ = try? await device.detect(trackingNumber: "TESTPARCEL123789")
        _ = try? await device.detect(trackingNumber: "OTHERPARCEL1")

        XCTAssertEqual(calls.all, ["lookup TESTPARCEL123789 dhl", "detect TESTPARCEL12 prefix", "detect TESTPARCEL123789", "detect OTHERPARCEL1"])
    }

    func testTheSameNumberIsNotLookedUpTwice() async throws {
        let id = UUID()
        let calls = Calls()
        let device = DeviceParcels(client: client(calls, lookup: PublicLookupResponse(link: link("22222222222A"), key: key, package: package(id))), storage: Memory())
        _ = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", carrier: .dhl))

        do {
            _ = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", carrier: .dhl))
            XCTFail("A second lookup went out")
        } catch DeliveryAPIError.duplicateTracking(let existing) {
            XCTAssertEqual(existing, id)
        }
        XCTAssertEqual(calls.all.count, 1)
    }

    func testASpentAllowanceIsToldApart() async {
        let device = DeviceParcels(client: client(Calls()), storage: Memory())
        do {
            _ = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", carrier: .dhl))
            XCTFail("The lookup went through")
        } catch {
            XCTAssertTrue(error is DeviceLookupsSpent)
        }
        XCTAssertTrue(device.isEmpty)
    }

    func testOnlyStaleParcelsAreReadAgainAndAForgottenLookupStaysNoLongerUpdated() async throws {
        let moving = UUID(), arrived = UUID(), gone = UUID(), held = UUID()
        var now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key, "22222222222C": key, "22222222222D": key]
        // A return the server still follows, as the scans after it carry the parcel back, is read as one on its way.
        var heldReturn = package(held, stage: .returned)
        heldReturn.carrierData.returnHeld = true
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "Moving", archivedAt: nil, parcel: Parcel(shared: package(moving)), checkedAt: now),
            DeviceParcel(linkID: "22222222222B", label: "Arrived", archivedAt: nil, parcel: Parcel(shared: package(arrived, stage: .delivered)), checkedAt: now),
            DeviceParcel(linkID: "22222222222C", label: "Gone", archivedAt: nil, parcel: Parcel(shared: package(gone)), checkedAt: now),
            DeviceParcel(linkID: "22222222222D", label: "Held", archivedAt: nil, parcel: Parcel(shared: heldReturn), checkedAt: now),
        ]
        let calls = Calls()
        let answers: [String: PublicParcelResponse] = [
            "22222222222A": PublicParcelResponse(link: link("22222222222A"), package: package(moving, stage: .outForDelivery)),
            "22222222222D": PublicParcelResponse(link: link("22222222222D"), package: heldReturn),
        ]
        let device = DeviceParcels(client: client(calls, read: { answers[$0] }), storage: storage, now: { now })

        try await device.refresh()
        XCTAssertEqual(calls.all, [], "Fresh answers are not asked for again")

        now += DeviceParcels.fresh + 1
        try await device.refresh()
        // A parcel that arrived rests for hours; the lookup the service forgot stays as it was, without its key.
        XCTAssertEqual(calls.all, ["read 22222222222A k", "read 22222222222C k", "read 22222222222D k"])
        XCTAssertEqual(device.list().map(\.label), ["Moving", "Arrived", "Gone", "Held"])
        XCTAssertEqual(device.list().first?.currentStage, .outForDelivery)
        XCTAssertNil(storage.keys["22222222222C"])
        XCTAssertTrue(device.isFrozen(id: gone))
        XCTAssertFalse(device.isFrozen(id: moving))

        // It is never read again.
        now += DeviceParcels.fresh + 1
        try await device.refresh(all: true)
        XCTAssertFalse(calls.all.dropFirst(3).contains { $0.contains("22222222222C") })
        try await device.refresh(only: gone)
        XCTAssertFalse(calls.all.dropFirst(3).contains { $0.contains("22222222222C") })
    }

    // MARK: - A lookup the service forgot

    /// A lookup of this iPhone's own, on file with its key and its notifications on.
    private func ownLookup(_ linkID: String, _ id: UUID, label: String = "", number: String = "TESTPARCEL123789",
                           stage: TrackingStage? = .inTransit, checkedAt: Date) -> DeviceParcel {
        var entry = DeviceParcel(linkID: linkID, label: label, archivedAt: nil,
                                 parcel: Parcel(shared: package(id, number: number, stage: stage)), checkedAt: checkedAt)
        entry.alertToken = "token1"
        entry.alertPreset = .important
        return entry
    }

    func testAForgottenLookupKeepsItsLastAnswerAndLosesItsKeyAndNotificationsWhileSomeoneElsesLinkGoes() async throws {
        let own = UUID(), theirs = UUID()
        var now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        var shared = DeviceParcel(linkID: "33333333333A", label: "Ana's books", archivedAt: nil,
                                  parcel: Parcel(shared: package(theirs)), checkedAt: now)
        shared.viewer = true
        storage.parcels = [ownLookup("22222222222A", own, label: "Trainers", checkedAt: now), shared]
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: storage, now: { now })
        XCTAssertTrue(device.hasParcelsOnTheirWay)

        now += DeviceParcels.fresh + 1
        try await device.refresh()
        // The 404 doesn't say why someone else's link ended: it goes. This iPhone's own lookup stays as last shown.
        XCTAssertEqual(device.list().map(\.label), ["Trainers"])
        XCTAssertEqual(device.list().first?.currentStage, .inTransit)
        XCTAssertTrue(device.isFrozen(id: own))
        XCTAssertTrue(storage.keys.isEmpty)
        let kept = try XCTUnwrap(storage.parcels.first)
        XCTAssertEqual(kept.frozen, DateParser.isoString(now))
        XCTAssertNil(kept.alertToken, "Its notifications went with the link")
        XCTAssertNil(kept.alertPreset)
        // Nothing is left to keep in an account, ask notifications for or wait on.
        XCTAssertEqual(device.claims, [])
        XCTAssertFalse(device.hasParcelsOnTheirWay)
        XCTAssertFalse(device.hasAlertsOn)
        await device.registerAlerts(token: "token2", locale: .en)
        XCTAssertFalse(calls.all.contains { $0.hasPrefix("alert") })
        await XCTAssertThrowsErrorAsync(try await device.changeCarrier(id: own, carrier: .ups, trackingURL: nil, dpdPostcode: nil))
        await XCTAssertThrowsErrorAsync(try await device.share(id: own))

        // It stays through a relaunch, with no key on file, and a rename or the archive stay on the device.
        let again = DeviceParcels(client: client(calls), storage: storage, now: { now })
        XCTAssertTrue(again.isFrozen(id: own))
        XCTAssertEqual(try again.rename(id: own, label: "Old trainers").label, "Old trainers")
        XCTAssertNotNil(try again.setArchived(id: own, true).archivedAt)

        // Removed here, it is gone without the service being asked.
        let before = calls.all
        let answered = await again.forget(id: own)
        XCTAssertFalse(answered)
        XCTAssertEqual(calls.all, before)
        XCTAssertTrue(again.isEmpty)
        XCTAssertTrue(storage.parcels.isEmpty)
    }

    func testANewLookupOfTheSameNumberTakesTheForgottenCopysPlaceAndName() async throws {
        let old = UUID(), new = UUID()
        let now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        var copy = ownLookup("22222222222A", old, label: "Trainers", checkedAt: now)
        copy.frozen = DateParser.isoString(now)
        copy.alertToken = nil
        storage.parcels = [copy]
        let calls = Calls()
        let device = DeviceParcels(client: client(calls, lookup: PublicLookupResponse(link: link("22222222222B"), key: key, package: package(new))),
                                   storage: storage, now: { now })
        XCTAssertTrue(device.isFrozen(id: old))
        XCTAssertFalse(device.isWaiting)

        // Not refused as a parcel already followed: the copy gives way.
        let added = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", carrier: .dhl))
        XCTAssertEqual(added.id, new)
        XCTAssertEqual(added.label, "Trainers")
        XCTAssertEqual(device.list().map(\.id), [new])
        XCTAssertFalse(device.isFrozen(id: new))
        XCTAssertEqual(storage.keys, ["22222222222B": key])
        XCTAssertFalse(calls.all.contains { $0.hasPrefix("forget") || $0.hasPrefix("unalert") })
    }

    func testALinkToTheSameParcelTakesTheForgottenCopysPlace() throws {
        let id = UUID()
        let now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        var copy = ownLookup("22222222222A", id, label: "Trainers", checkedAt: now)
        copy.frozen = DateParser.isoString(now)
        storage.parcels = [copy]
        let device = DeviceParcels(client: client(Calls()), storage: storage, now: { now })

        let followed = try device.follow(PublicParcelResponse(link: reader("33333333333A"), package: package(id, stage: .delivered)),
                                         key: nil, label: nil)
        XCTAssertTrue(followed.added)
        XCTAssertEqual(device.list().map(\.label), ["Trainers"])
        XCTAssertEqual(device.list().first?.currentStage, .delivered)
        XCTAssertFalse(device.isFrozen(id: id))
        XCTAssertEqual(device.viewerLink(id: id), "33333333333A")
    }

    func testAForgottenCopyNeedsNoKeyOnFileAndOneBesideItOpensNothing() throws {
        var copy = DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID())), checkedAt: Date())
        copy.frozen = "2026-11-20T08:00:00Z"
        let loaded = DeviceParcelFiles.matched([copy], keys: ["22222222222A": key])
        XCTAssertEqual(loaded.parcels, [copy])
        XCTAssertEqual(loaded.keys, [:])
        let saved = try JSONDecoder.deliveryTracker.decode([DeviceParcel].self, from: JSONEncoder.deliveryTracker.encode([copy]))
        XCTAssertEqual(saved, [copy])
    }

    /// While the server restarts, its proxy answers "404 page not found" as text: the server failing, and nothing on
    /// this iPhone goes. Only the service's own 404, with its JSON error, takes a parcel's key, and leaves its copy no
    /// longer updated.
    func testAProxysNotFoundWhileTheServerRestartsForgetsNothing() async throws {
        let transport = PostcodeTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        nonisolated(unsafe) var answer = (404, Data("404 page not found\n".utf8))
        PostcodeTestURLProtocol.handler = { _ in answer }
        let configuration = PostcodeTestURLProtocol.configuration
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        storage.parcels = [DeviceParcel(linkID: "22222222222A", label: "Running shoes", archivedAt: nil,
                                        parcel: Parcel(shared: package(UUID())), checkedAt: .distantPast)]
        storage.forgetting = ["22222222222C": key]
        let device = DeviceParcels(client: DeviceParcelClient.api(configuration: configuration, transport: transport), storage: storage)

        do {
            try await device.refresh()
            XCTFail("Expected the server down")
        } catch let down as ServerDown {
            XCTAssertEqual(down, ServerDown())
        }
        XCTAssertEqual(device.list().map(\.label), ["Running shoes"])
        XCTAssertEqual(storage.keys, ["22222222222A": key, "22222222222B": key], "A key went for a proxy's 404")
        XCTAssertEqual(storage.forgetting, ["22222222222C": key], "A forget counted as told for a proxy's 404")
        do {
            _ = try await DeliveryAPIClient.publicParcel(linkID: "22222222222A", configuration: configuration, transport: transport)
            XCTFail("A link sheet would say the parcel was forgotten")
        } catch let down as ServerDown {
            XCTAssertEqual(down, ServerDown())
        }

        answer = (404, Data(#"{"error":"route unavailable"}"#.utf8))
        try? await device.refresh()
        XCTAssertEqual(storage.keys.count, 2)
        XCTAssertEqual(storage.forgetting.count, 1)

        answer = (404, Data(#"{"error":"Parcel unavailable"}"#.utf8))
        try await device.refresh()
        XCTAssertEqual(device.list().map(\.label), ["Running shoes"])
        XCTAssertEqual(storage.parcels.map(\.isFrozen), [true])
        XCTAssertEqual(storage.keys, [:])
        XCTAssertEqual(storage.forgetting, [:])
        let gone = try await DeliveryAPIClient.publicParcel(linkID: "22222222222A", configuration: configuration, transport: transport)
        XCTAssertNil(gone)
    }

    /// A database being upgraded, a host restarting or the edge answering for them: the server down, which keeps every
    /// parcel. The app's own 503 about one part it was never set up for is that part failing.
    func testTheServerDownForMaintenanceIsToldFromTheAppsOwnTrouble() async throws {
        let transport = PostcodeTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        let configuration = PostcodeTestURLProtocol.configuration
        let client = DeviceParcelClient.api(configuration: configuration, transport: transport)
        let answers: [(Int, String, ServerDown?)] = [
            (502, #"{"error":"The delivery database is temporarily unavailable"}"#, ServerDown()),
            (530, "error code: 1033", ServerDown()),
            (503, #"{"error":"Peek is down for maintenance","maintenance":{"planned":true,"mode":"soft","since":null,"backAround":"2026-11-17T15:00:00Z"}}"#,
             ServerDown(notice: MaintenanceNotice(planned: true, backAround: DateParser.date("2026-11-17T15:00:00Z")))),
            (503, #"{"error":"Push notifications are not configured"}"#, nil),
        ]
        for (status, body, expected) in answers {
            PostcodeTestURLProtocol.handler = { _ in (status, Data(body.utf8)) }
            do {
                _ = try await client.read("22222222222A", key, false)
                XCTFail("Expected a failure for \(status)")
            } catch let down as ServerDown {
                XCTAssertEqual(down, expected, "\(status) \(body)")
            } catch DeliveryAPIError.serviceFailed(let failed) {
                XCTAssertNil(expected, "\(status) \(body)")
                XCTAssertEqual(failed, status)
            }
        }
    }

    /// Without an account, the list keeps the device's parcels through the server down and says how old they are; the
    /// next round that reads one takes the notice away.
    @MainActor func testAGuestsListKeepsItsParcelsThroughTheServerDownAndSaysSo() async throws {
        let transport = PostcodeTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        // /maintenance.json is the app's own 404 page before the edge is in front.
        PostcodeTestURLProtocol.handler = { _ in (404, Data("<!doctype html><title>Not found</title>".utf8)) }
        let id = UUID()
        let checkedAt = Date().addingTimeInterval(-5)
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(linkID: "22222222222A", label: "Running shoes", archivedAt: nil,
                                        parcel: Parcel(shared: package(id)), checkedAt: checkedAt)]
        nonisolated(unsafe) var down = true
        let answered = PublicParcelResponse(link: link("22222222222A"), package: package(id))
        let device = DeviceParcels(client: client(Calls(), read: { _ in
            if down { throw ServerDown() }
            return answered
        }), storage: storage)
        let configuration = PostcodeTestURLProtocol.configuration
        let session = SessionStore(configuration: configuration, persistence: PostcodeTestSessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let store = ParcelStore(configuration: configuration, session: session, localizer: Localizer(), transport: transport, device: device)
        store.setDeliveryLiveActivitiesEnabled(false)
        session.showWelcome()

        try await store.refreshAll()
        await store.load()
        XCTAssertEqual(store.parcels.map(\.label), ["Running shoes"])
        XCTAssertEqual(store.serverDown, .unannounced)
        XCTAssertNil(store.errorMessage)
        XCTAssertEqual(store.listReadAt, checkedAt)
        XCTAssertEqual(storage.keys, ["22222222222A": key])

        down = false
        await store.load()
        XCTAssertNil(store.serverDown)
        XCTAssertEqual(store.parcels.map(\.label), ["Running shoes"])
        XCTAssertGreaterThan(try XCTUnwrap(store.listReadAt), checkedAt)
    }

    /// A guest who removes their last parcel while the server is down has nothing left to read: the service forgetting
    /// it once it is back takes the notice away, and the list invites them to add one.
    @MainActor func testAGuestWhoRemovedTheirLastParcelLosesTheNoticeOnceTheServiceForgetsIt() async throws {
        let transport = PostcodeTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        PostcodeTestURLProtocol.handler = { _ in (404, Data("<!doctype html><title>Not found</title>".utf8)) }
        let id = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(linkID: "22222222222A", label: "Running shoes", archivedAt: nil,
                                        parcel: Parcel(shared: package(id)), checkedAt: Date().addingTimeInterval(-5))]
        nonisolated(unsafe) var down = true
        let calls = Calls()
        let device = DeviceParcels(client: client(calls, read: { _ in throw ServerDown() }, forget: { _ in
            if down { throw ServerDown() }
        }), storage: storage)
        let configuration = PostcodeTestURLProtocol.configuration
        let session = SessionStore(configuration: configuration, persistence: PostcodeTestSessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let store = ParcelStore(configuration: configuration, session: session, localizer: Localizer(), transport: transport, device: device)
        store.setDeliveryLiveActivitiesEnabled(false)
        session.showWelcome()

        try await store.refreshAll()
        await store.load()
        XCTAssertEqual(store.serverDown, .unannounced)
        try await store.permanentlyDelete(XCTUnwrap(store.parcels.first))
        XCTAssertTrue(store.parcels.isEmpty)
        XCTAssertEqual(store.serverDown, .unannounced)

        down = false
        await store.load()
        XCTAssertNil(store.serverDown)
        XCTAssertEqual(calls.all.filter { $0.hasPrefix("forget") }, ["forget 22222222222A", "forget 22222222222A"])
    }

    func testAParcelWaitingForItsCarrierIsAskedAboutAtOnce() async throws {
        let id = UUID()
        var now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil,
                                        parcel: Parcel(shared: package(id, stage: nil, sync: .pending)), checkedAt: now)]
        let calls = Calls()
        let answered = PublicParcelResponse(link: link("22222222222A"), package: package(id))
        let device = DeviceParcels(client: client(calls, read: { _ in answered }), storage: storage, now: { now })
        XCTAssertTrue(device.isWaiting)

        now += 2
        try await device.refresh()

        XCTAssertEqual(calls.all, ["read 22222222222A k"])
        XCTAssertFalse(device.isWaiting)
    }

    func testAReadWithNothingOnScreenSaysSo() async throws {
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        let followed = UUID()
        storage.parcels = [DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(followed)), checkedAt: .now)]
        let calls = Calls()
        let answers = ["22222222222A": PublicParcelResponse(link: link("22222222222A"), package: package(followed)),
                       "22222222222B": PublicParcelResponse(link: link("22222222222B"), package: package(UUID()))]
        let device = DeviceParcels(client: client(calls, read: { answers[$0] }), storage: storage)

        try await device.refresh(all: true, hidden: true)
        try await device.refresh(all: true)

        // The lost one comes back from a hidden read too.
        XCTAssertEqual(calls.all, ["read 22222222222A k hidden", "read 22222222222B k hidden", "read 22222222222A k", "read 22222222222B k"])
    }

    private func news(_ package: PublicPackage, at newsAt: String?) -> PublicPackage {
        var copy = package
        copy.newsAt = newsAt
        return copy
    }

    /// The package once its first check has landed.
    private func checked(_ package: PublicPackage) -> PublicPackage {
        var copy = package
        copy.lastSyncedAt = "2026-11-15T08:00:00Z"
        return copy
    }

    func testAScanAfterTheParcelWasLastOpenedHereIsMarkedAndOpeningClearsItOnThisIPhone() async throws {
        let id = UUID()
        var now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        // Followed with a version that kept no news, as every parcel kept before the mark was.
        storage.parcels = [DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil,
                                        parcel: Parcel(shared: checked(package(id))), checkedAt: now)]
        let first = PublicParcelResponse(link: link("22222222222A"), package: checked(news(package(id), at: "2026-11-15T09:00:00.12+00:00")))
        let updated = DeviceParcels(client: client(Calls(), read: { _ in first }), storage: storage, now: { now })
        XCTAssertFalse(updated.list()[0].hasNewUpdate)
        now += DeviceParcels.fresh
        try await updated.refresh()
        XCTAssertFalse(updated.list()[0].hasNewUpdate, "The parcels already here start as seen with their next answer: nobody opens the app to a wall of dots")

        let calls = Calls()
        let later = PublicParcelResponse(link: link("22222222222A"), package: checked(news(package(id), at: "2026-11-15T10:00:00.5+00:00")))
        let device = DeviceParcels(client: client(calls, read: { _ in later }), storage: storage, now: { now })
        XCTAssertFalse(device.list()[0].hasNewUpdate)

        now += DeviceParcels.fresh
        try await device.refresh()
        XCTAssertTrue(device.list()[0].hasNewUpdate)
        XCTAssertTrue(DeviceParcels(client: nil, storage: storage).list()[0].hasNewUpdate, "The mark waits for the next launch")

        XCTAssertTrue(try device.markSeen(id: id, newsAt: "2026-11-15T09:59:59Z").hasNewUpdate)
        XCTAssertFalse(try device.markSeen(id: id, newsAt: XCTUnwrap(device.list()[0].newsAt)).hasNewUpdate)
        XCTAssertFalse(device.list()[0].hasNewUpdate)
        XCTAssertEqual(calls.all, ["read 22222222222A k"], "What was seen without an account stays on the device")
        XCTAssertFalse(DeviceParcels(client: nil, storage: storage).list()[0].hasNewUpdate)

        // The same answer again is no news, and the next scan is.
        now += DeviceParcels.fresh
        try await device.refresh()
        XCTAssertFalse(device.list()[0].hasNewUpdate)
        let newer = PublicParcelResponse(link: link("22222222222A"), package: checked(news(package(id), at: "2026-11-15T11:00:00+00:00")))
        let reopened = DeviceParcels(client: client(Calls(), read: { _ in newer }), storage: storage, now: { now })
        now += DeviceParcels.fresh
        try await reopened.refresh()
        XCTAssertTrue(reopened.list()[0].hasNewUpdate)
    }

    func testAParcelNotedWhileItsLinkToldNoNewsStartsAsSeenWithItsNextAnswer() throws {
        let id = UUID()
        let saved = DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: checked(package(id))),
                                 checkedAt: Date(timeIntervalSince1970: 1_800_000_000))
        var file = try XCTUnwrap(JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode([saved])) as? [[String: Any]])
        // As the version that read links without their news noted it: seen before any news, which every scan follows.
        file[0]["seenNews"] = DeviceParcel.seenBeforeAnyNews
        var kept = try XCTUnwrap(try JSONDecoder.deliveryTracker.decode([DeviceParcel].self,
                                                                       from: JSONSerialization.data(withJSONObject: file)).first)
        XCTAssertNil(kept.openedNews)
        kept.take(Parcel(shared: checked(news(package(id), at: "2026-11-15T09:00:00Z"))))
        XCTAssertEqual(kept.openedNews, "2026-11-15T09:00:00Z", "Its scans so far are no news")
    }

    func testANewScanReadWhileTheServiceWasCheckingTheParcelIsStillNews() async throws {
        let id = UUID()
        var now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        // Kept here and seen, then read in the middle of one of the service's checks.
        var kept = DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil,
                                parcel: Parcel(shared: checked(news(package(id, sync: .syncing), at: "2026-11-15T09:00:00Z"))), checkedAt: now)
        kept.startSeen()
        storage.parcels = [kept]
        let scanned = PublicParcelResponse(link: link("22222222222A"), package: checked(news(package(id), at: "2026-11-15T10:00:00Z")))
        let device = DeviceParcels(client: client(Calls(), read: { _ in scanned }), storage: storage, now: { now })
        now += DeviceParcels.fresh
        try await device.refresh()
        XCTAssertTrue(device.list()[0].hasNewUpdate)
    }

    func testAParcelFollowedHereStartsAsSeenAndSoDoesItsFirstAnswer() async throws {
        let id = UUID()
        var now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        let calls = Calls()
        // The lookup answers before the carrier has: the parcel waits.
        let waiting = PublicLookupResponse(link: link("22222222222A"), key: key, package: package(id, stage: nil, sync: .pending))
        let first = PublicParcelResponse(link: link("22222222222A"), package: checked(news(package(id), at: "2026-11-15T09:00:00+00:00")))
        let device = DeviceParcels(client: client(calls, lookup: waiting, read: { _ in first }), storage: storage, now: { now })
        let added = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", label: "Lamp", carrier: .dhl))
        XCTAssertFalse(added.hasNewUpdate)

        now += 2
        try await device.refresh()
        XCTAssertEqual(device.list()[0].newsAt, "2026-11-15T09:00:00+00:00")
        XCTAssertFalse(device.list()[0].hasNewUpdate, "A parcel's first history is not news")

        // A lookup that comes back with its history starts as seen too.
        let other = UUID()
        let answered = PublicLookupResponse(link: link("33333333333B"), key: key,
                                            package: news(package(other, number: "TESTPARCEL123790"), at: "2026-11-15T09:30:00+00:00"))
        let second = DeviceParcels(client: client(Calls(), lookup: answered), storage: Memory(), now: { now })
        let scanned = try await second.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123790", carrier: .dhl))
        XCTAssertFalse(scanned.hasNewUpdate)
    }

    func testTheFirstScanOfAParcelItsCarrierKnewNothingAboutIsNews() async throws {
        let id = UUID()
        var now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        // The carrier answered, with nothing to tell yet, and the parcel was listed here like that.
        var kept = DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil,
                                parcel: Parcel(shared: checked(package(id, stage: nil))), checkedAt: now)
        kept.startSeen()
        storage.parcels = [kept]
        let scanned = PublicParcelResponse(link: link("22222222222A"), package: checked(news(package(id), at: "2026-11-15T09:00:00+00:00")))
        let device = DeviceParcels(client: client(Calls(), read: { _ in scanned }), storage: storage, now: { now })
        XCTAssertFalse(device.list()[0].hasNewUpdate)

        now += DeviceParcels.fresh
        try await device.refresh()
        XCTAssertTrue(device.list()[0].hasNewUpdate)
        XCTAssertFalse(try device.markSeen(id: id, newsAt: XCTUnwrap(device.list()[0].newsAt)).hasNewUpdate)
    }

    func testTroubleKeepsWhatTheDeviceHas() async {
        let id = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(linkID: "22222222222A", label: "Kept", archivedAt: nil, parcel: Parcel(shared: package(id)),
                                        checkedAt: .distantPast)]
        let device = DeviceParcels(client: client(Calls(), read: { _ in throw URLError(.notConnectedToInternet) }), storage: storage)

        do { try await device.refresh(); XCTFail("The trouble was swallowed") } catch { XCTAssertTrue(error is URLError) }

        XCTAssertEqual(device.list().map(\.label), ["Kept"])
        XCTAssertEqual(storage.keys.count, 1)
    }

    func testNamesAndTheArchiveStayOnTheDeviceAndForgettingTellsTheService() async throws {
        let id = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(id)), checkedAt: Date())]
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: storage)

        XCTAssertEqual(try device.rename(id: id, label: "Moon lamp").label, "Moon lamp")
        XCTAssertNotNil(try device.setArchived(id: id, true).archivedAt)
        XCTAssertTrue(device.isArchived(linkID: "22222222222A"))
        XCTAssertNil(try device.setArchived(id: id, false).archivedAt)
        XCTAssertEqual(calls.all, [], "Neither a name nor the archive is the service's to know")

        await device.forget(id: id)
        XCTAssertEqual(calls.all, ["forget 22222222222A"])
        XCTAssertTrue(device.isEmpty)
        XCTAssertTrue(storage.parcels.isEmpty)
        XCTAssertTrue(storage.keys.isEmpty)
    }

    func testTheLastFivePostcodesAreRememberedNewestFirst() {
        let (defaults, suite) = defaults()
        defer { defaults.removePersistentDomain(forName: suite) }
        let memory = PostcodeMemory(defaults: defaults)
        XCTAssertEqual(memory.entries, [])

        memory.remember("75001", for: .dpd)
        memory.remember(" 8000 ", for: .glsCh)
        memory.remember("75001", for: .mondialRelay)
        memory.remember("  ", for: .dpd)
        XCTAssertEqual(memory.entries, [given(.mondialRelay, "75001"), given(.glsCh, "8000"), given(.dpd, "75001")])

        // Given again, a pair moves to the front rather than coming twice.
        memory.remember("75001", for: .dpd)
        XCTAssertEqual(memory.entries, [given(.dpd, "75001"), given(.mondialRelay, "75001"), given(.glsCh, "8000")])

        memory.remember("1012 AB", for: .dpd)
        memory.remember("3000", for: .glsCh)
        memory.remember("75002", for: .heppner)
        let kept = [given(.heppner, "75002"), given(.glsCh, "3000"), given(.dpd, "1012 AB"), given(.dpd, "75001"), given(.mondialRelay, "75001")]
        XCTAssertEqual(memory.entries, kept, "The oldest leaves")
        XCTAssertEqual(PostcodeMemory(defaults: defaults).entries, kept, "Kept on the device")

        // Forgetting a postcode forgets it for every carrier, however its spaces were written.
        memory.forget("75001")
        memory.forget("1012ab")
        XCTAssertEqual(memory.entries, [given(.heppner, "75002"), given(.glsCh, "3000")])
        memory.forget("75002")
        memory.forget("3000")
        XCTAssertEqual(memory.entries, [])
        XCTAssertEqual(defaults.persistentDomain(forName: suite)?.isEmpty ?? true, true, "Nothing is left behind")
    }

    func testAGuestLookupRemembersItsPostcodeApartFromTheParcel() async throws {
        let (defaults, suite) = defaults()
        defer { defaults.removePersistentDomain(forName: suite) }
        let memory = PostcodeMemory(defaults: defaults)
        let id = UUID()
        let storage = Memory()
        let calls = Calls()
        let found = PublicLookupResponse(link: link("22222222222A"), key: key, package: package(id, carrier: .dpd))
        let moved = package(UUID(), carrier: .mondialRelay)
        let device = DeviceParcels(client: client(calls, lookup: found, move: { _ in moved }),
                                   storage: storage, postcodes: memory)

        let parcel = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", carrier: .dpd, dpdPostcode: "75001 "))

        XCTAssertEqual(calls.lookupPostcodes, ["75001 "])
        XCTAssertEqual(memory.entries, [given(.dpd, "75001")])
        XCTAssertNil(parcel.dpdPostcode)
        XCTAssertNil(storage.parcels.first?.parcel.dpdPostcode)

        // A carrier changed asks with its postcode, which comes first.
        _ = try await device.changeCarrier(id: id, carrier: .mondialRelay, trackingURL: nil, dpdPostcode: "75002")
        XCTAssertEqual(memory.entries, [given(.mondialRelay, "75002"), given(.dpd, "75001")])
        _ = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL999", carrier: .heppner))
        XCTAssertEqual(memory.entries.count, 2, "A lookup without a postcode leaves the memory alone")

        let failed = DeviceParcels(client: client(Calls()), storage: Memory(), postcodes: memory)
        _ = try? await failed.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", carrier: .glsCh, dpdPostcode: "8000"))
        XCTAssertEqual(memory.entries.count, 2, "Only a lookup that went through is remembered")
    }

    func testForgettingParcelsKeepsTheirPostcodes() async throws {
        let (defaults, suite) = defaults()
        defer { defaults.removePersistentDomain(forName: suite) }
        let memory = PostcodeMemory(defaults: defaults)
        memory.remember("8000", for: .glsCh)
        memory.remember("75001", for: .dpd)
        let first = UUID(), second = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(first, carrier: .dpd)), checkedAt: Date()),
            DeviceParcel(linkID: "22222222222B", label: "", archivedAt: nil, parcel: Parcel(shared: package(second, number: "TESTPARCEL999", carrier: .glsCh)),
                         checkedAt: Date()),
        ]
        let device = DeviceParcels(client: client(Calls()), storage: storage, postcodes: memory)

        await device.forget(id: first)
        XCTAssertEqual(memory.entries, [given(.dpd, "75001"), given(.glsCh, "8000")])
        await device.forgetAll()
        XCTAssertTrue(storage.parcels.isEmpty)
        XCTAssertEqual(memory.entries, [given(.dpd, "75001"), given(.glsCh, "8000")])
    }

    func testOnlyALookupWithoutAnAccountIsRememberedAndSigningOutKeepsIt() async throws {
        let (defaults, suite) = defaults()
        defer { defaults.removePersistentDomain(forName: suite) }
        let memory = PostcodeMemory(defaults: defaults)
        let transport = PostcodeTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        let user = AuthUser(id: UUID(), email: "test@example.com", isAnonymous: false)
        let signedIn = try JSONEncoder.deliveryTracker.encode(AuthSession(
            accessToken: "access", tokenType: "bearer", expiresIn: 3600,
            expiresAt: Int(Date().timeIntervalSince1970) + 3600, refreshToken: "refresh", user: user
        ))
        let accountParcel = Parcel(id: UUID(), trackingNumber: "TESTPARCEL999", label: "", carrier: .glsCh, createdAt: "2026-10-02T08:00:00Z",
                                   syncStatus: .pending, dpdPostcode: "3000", notificationsMuted: false)
        let added = try JSONEncoder.deliveryTracker.encode(CreatePackageResponse(package: accountParcel, jobIDs: []))
        PostcodeTestURLProtocol.handler = { request in
            switch (request.httpMethod, request.url?.path) {
            case ("POST", "/auth/v1/verify"): return (200, signedIn)
            case ("POST", "/api/packages"): return (201, added)
            default: return (404, Data())
            }
        }
        let id = UUID()
        let found = PublicLookupResponse(link: link("22222222222A"), key: key, package: package(id, carrier: .dpd))
        let answered = PublicParcelResponse(link: link("22222222222A"), package: package(id, carrier: .dpd))
        let device = DeviceParcels(client: client(Calls(), lookup: found, read: { _ in answered }), storage: Memory(), postcodes: memory)
        let configuration = PostcodeTestURLProtocol.configuration
        let session = SessionStore(configuration: configuration, persistence: PostcodeTestSessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        session.showWelcome()
        let store = ParcelStore(configuration: configuration, session: session, localizer: Localizer(), transport: transport, device: device,
                                deviceNotifications: quietNotifications())
        store.setDeliveryLiveActivitiesEnabled(false)

        try await store.add(trackingNumber: "TESTPARCEL123789", label: "", carrier: .dpd, trackingURL: nil, dpdPostcode: " 75001 ")
        XCTAssertEqual(memory.entries, [given(.dpd, "75001")])
        XCTAssertEqual(store.givenPostcodes, [given(.dpd, "75001")])
        XCTAssertTrue(store.postcodeGiven(for: try XCTUnwrap(store.parcels.first { $0.id == id })))

        // An account's postcode stays on its parcel, and the account's parcels come first.
        try await session.verifyCode(email: "test@example.com", code: "123456")
        try await store.add(trackingNumber: "TESTPARCEL999", label: "", carrier: .glsCh, trackingURL: nil, dpdPostcode: "3000")
        XCTAssertEqual(memory.entries, [given(.dpd, "75001")])
        XCTAssertEqual(store.givenPostcodes, [given(.glsCh, "3000"), given(.dpd, "75001")])
        XCTAssertFalse(store.postcodeGiven(for: accountParcel), "An account's parcel holds its own postcode")

        session.forceSignOut()
        XCTAssertEqual(memory.entries, [given(.dpd, "75001")])
        store.forgetPostcode("75001")
        XCTAssertEqual(memory.entries, [])
    }

    func testAForgottenPostcodeLeavesTheFieldUntilItsDetailsArePreparedAgain() {
        var fill = PostcodeFill()
        XCTAssertEqual(fill.offering("75001"), "75001")
        let field = fill.fill("75001")
        XCTAssertTrue(fill.holds(field))
        XCTAssertFalse(fill.holds("75002"), "Typed over, it is the user's own")
        XCTAssertNil(fill.forget("75002"))

        XCTAssertEqual(fill.forget(field), "75001")
        XCTAssertFalse(fill.holds(""))
        XCTAssertFalse(fill.holds(field))
        XCTAssertNil(fill.offering("8000"), "No other postcode fills the field straight away")

        fill.prepare()
        XCTAssertEqual(fill.offering("8000"), "8000")
    }

    func testEachCarrierKeepsTheAddSheetsPostcodeGivenForItAndAnOptionalOneIsOnlyOffered() throws {
        func requirement(_ carrier: CarrierID, _ number: String) throws -> CarrierRequirement {
            try XCTUnwrap(CarrierCatalog.shared.requirements(for: carrier, trackingNumber: number).first { $0.field == .dpdPostcode })
        }
        // Mondial Relay's 8-digit numbers need the postcode; PostNL's 3S labels only show more with it.
        let mondialRelay = try requirement(.mondialRelay, "12345678")
        let postNL = try requirement(.springGDS, "3SABCD826405193")
        XCTAssertFalse(mondialRelay.isOptional)
        XCTAssertTrue(postNL.isOptional)
        let earlier = [given(.springGDS, "1234 AB")]
        var postcodes = CarrierPostcodes()

        // The field writes back what it shows as it appears, which leaves a required one to be filled.
        postcodes[.mondialRelay] = mondialRelay.typedValue(postcodes[.mondialRelay])
        postcodes.prepare(.mondialRelay, requirement: mondialRelay, among: earlier)
        XCTAssertEqual(postcodes[.mondialRelay], "1234 AB")
        XCTAssertNil(postcodes.offering(.mondialRelay, requirement: mondialRelay, among: earlier))
        postcodes[.mondialRelay] = "75001"

        // PostNL's field shows neither the postcode typed for Mondial Relay nor the one given before, which
        // is only offered: the field, sent as it is, stays empty.
        postcodes.prepare(.springGDS, requirement: postNL, among: earlier)
        XCTAssertEqual(postcodes[.springGDS], "")
        XCTAssertEqual(postcodes.offering(.springGDS, requirement: postNL, among: earlier)?.postcode, "1234 AB")
        XCTAssertTrue(postNL.isSatisfied(by: postcodes[.springGDS]))
        postcodes.use("1234 AB", for: .springGDS)
        XCTAssertEqual(postcodes[.springGDS], "1234 AB")
        XCTAssertNil(postcodes.offering(.springGDS, requirement: postNL, among: earlier))

        // Switching back shows each carrier's own.
        postcodes.prepare(.mondialRelay, requirement: mondialRelay, among: earlier)
        XCTAssertEqual(postcodes[.mondialRelay], "75001")
        postcodes.prepare(.springGDS, requirement: postNL, among: earlier)
        XCTAssertEqual(postcodes[.springGDS], "1234 AB")

        // A postcode forgotten in one field leaves the others it filled, which fill again from what is left.
        var filled = CarrierPostcodes()
        filled.prepare(.mondialRelay, requirement: mondialRelay, among: earlier)
        filled.prepare(.springGDS, requirement: postNL, among: earlier)
        filled.use("1234 AB", for: .springGDS)
        XCTAssertTrue(filled.holdsFill(.springGDS))
        XCTAssertEqual(filled.forget(.springGDS), "1234 AB")
        XCTAssertEqual(filled[.springGDS], "")
        XCTAssertNil(filled.offering(.springGDS, requirement: postNL, among: earlier), "Not offered again straight away")
        XCTAssertEqual(filled[.mondialRelay], "")
        filled.prepare(.mondialRelay, requirement: mondialRelay, among: [given(.mondialRelay, "75002")])
        XCTAssertEqual(filled[.mondialRelay], "75002")
    }

    func testAForgottenPostcodeEmptiesEveryOtherCarriersFieldHoldingItWhateverItsCapitalsAndSpaces() throws {
        let mondialRelay = try postcodeField(.mondialRelay, "12345678")
        let glsSpain = try postcodeField(.glsEs, "12345678901")
        let postNL = try postcodeField(.springGDS, "3SABCD826405193")
        XCTAssertFalse(mondialRelay.isOptional)
        XCTAssertFalse(glsSpain.isOptional)
        XCTAssertTrue(postNL.isOptional)
        let earlier = [given(.mondialRelay, "1234ab"), given(.springGDS, "1234 AB"), given(.dpd, "75002")]
        var postcodes = CarrierPostcodes()

        // Mondial Relay's field is filled with the postcode as it was given to it, GLS's typed in other capitals and
        // spaces, DPD's typed with another one.
        postcodes.prepare(.mondialRelay, requirement: mondialRelay, among: earlier)
        XCTAssertEqual(postcodes[.mondialRelay], "1234AB")
        postcodes[.glsEs] = "1234ab"
        postcodes[.dpd] = "75002"
        postcodes.prepare(.springGDS, requirement: postNL, among: earlier)
        postcodes.use(try XCTUnwrap(postcodes.offering(.springGDS, requirement: postNL, among: earlier)).postcode, for: .springGDS)
        XCTAssertEqual(postcodes[.springGDS], "1234 AB")

        XCTAssertEqual(postcodes.forget(.springGDS), "1234 AB")
        XCTAssertEqual(postcodes[.springGDS], "")
        XCTAssertNil(postcodes.offering(.springGDS, requirement: postNL, among: earlier), "Not offered again straight away")
        XCTAssertEqual(postcodes[.mondialRelay], "", "Filled with it")
        XCTAssertEqual(postcodes[.glsEs], "", "Typed with it")
        XCTAssertEqual(postcodes[.dpd], "75002", "Another postcode stays")

        // Each fills again from what is left when its carrier comes up.
        let left = [given(.mondialRelay, "75002")]
        postcodes.prepare(.mondialRelay, requirement: mondialRelay, among: left)
        XCTAssertEqual(postcodes[.mondialRelay], "75002")
        postcodes.prepare(.glsEs, requirement: glsSpain, among: left)
        XCTAssertEqual(postcodes[.glsEs], "75002")
    }

    func testEachCarrierKeepsTheCountryChosenBesideItsPostcode() throws {
        let catalog = CarrierCatalog.shared
        let postNL = try postcodeField(.springGDS, "3SABCD826405193")
        let mondialRelay = try postcodeField(.mondialRelay, "12345678")
        let dpd = try postcodeField(.dpd, "06080000000001")
        var postcodes = CarrierPostcodes()

        // Both take any country's postcode, and start on the iPhone's.
        XCTAssertEqual(postcodes.country(.springGDS, requirement: postNL, region: "FR"), "FR")
        XCTAssertEqual(postcodes.country(.mondialRelay, requirement: mondialRelay, region: "FR"), "FR")

        // A country chosen for one carrier's field leaves the other's where it starts, and stays when switching back.
        postcodes.choose("LU", for: .springGDS)
        XCTAssertEqual(postcodes.country(.mondialRelay, requirement: mondialRelay, region: "FR"), "FR")
        XCTAssertEqual(postcodes.country(.springGDS, requirement: postNL, region: "FR"), "LU")
        postcodes.choose("BE", for: .mondialRelay)
        XCTAssertEqual(postcodes.country(.springGDS, requirement: postNL, region: "FR"), "LU")
        XCTAssertEqual(postcodes.country(.mondialRelay, requirement: mondialRelay, region: "FR"), "BE")

        // The country sent with the postcode is the shown carrier's own.
        let sent = catalog.sentPostcodeCountry(postcodes.country(.springGDS, requirement: postNL, region: "FR"),
                                               postcode: "L-1009", for: .springGDS)
        XCTAssertEqual(sent, "LU")

        // A country the field doesn't take leaves it where it starts.
        postcodes.choose("FR", for: .dpd)
        XCTAssertEqual(postcodes.country(.dpd, requirement: dpd, region: "FR"), "CH")

        // Change carrier starts each carrier picked afresh: the parcel's own postcode keeps the country saved with it,
        // another carrier's field starts on the parcel's destination.
        let parcel = Parcel(id: UUID(), trackingNumber: "3SABCD826405193", label: "", carrier: .springGDS, createdAt: "2026-10-01T08:00:00Z",
                            syncStatus: .ok, dpdPostcode: "1000",
                            carrierData: CarrierData(postcodeCountry: PostcodeCountry(postcode: "1000", country: "BE"), destinationCountry: "LU"),
                            notificationsMuted: false)
        var picked = CarrierPostcodes(parcel: parcel)
        XCTAssertEqual(picked.country(.springGDS, requirement: postNL, parcel: parcel, region: "FR"), "BE")
        picked.choose("ES", for: .springGDS)
        XCTAssertEqual(picked.country(.springGDS, requirement: postNL, parcel: parcel, region: "FR"), "ES")
        picked = CarrierPostcodes(parcel: parcel)
        XCTAssertEqual(picked.country(.mondialRelay, requirement: mondialRelay, parcel: parcel, region: "FR"), "LU")
        XCTAssertEqual(picked.country(.springGDS, requirement: postNL, parcel: parcel, region: "FR"), "BE")
    }

    func testAPostcodeGivenBeforeBringsTheCountryItWasGivenFrom() throws {
        let mondialRelay = try postcodeField(.mondialRelay, "12345678")
        let postNL = try postcodeField(.springGDS, "3SABCD826405193")
        // A Belgian postcode given to PostNL fills Mondial Relay's field as a Belgian one on a French iPhone.
        let belgian = [GivenPostcode(carrier: .springGDS, postcode: "1000", country: "BE")]
        var postcodes = CarrierPostcodes()
        postcodes.prepare(.mondialRelay, requirement: mondialRelay, among: belgian)
        XCTAssertEqual(postcodes[.mondialRelay], "1000")
        XCTAssertEqual(postcodes.country(.mondialRelay, requirement: mondialRelay, region: "FR"), "BE")

        // Offered to an optional field, it goes there with its country once used.
        let offered = try XCTUnwrap(postcodes.offering(.springGDS, requirement: postNL, among: belgian))
        XCTAssertEqual(offered, GivenPostcode(carrier: .springGDS, postcode: "1000", country: "BE"))
        postcodes.use(offered.postcode, for: .springGDS, country: offered.country)
        XCTAssertEqual(postcodes.country(.springGDS, requirement: postNL, region: "FR"), "BE")

        // Once the reader names a country, only a postcode given from it fills the field.
        var chosen = CarrierPostcodes()
        chosen.choose("LU", for: .mondialRelay)
        chosen.prepare(.mondialRelay, requirement: mondialRelay, among: belgian)
        XCTAssertEqual(chosen[.mondialRelay], "")
        chosen.prepare(.mondialRelay, requirement: mondialRelay,
                       among: belgian + [GivenPostcode(carrier: .mondialRelay, postcode: "1009", country: "LU")])
        XCTAssertEqual(chosen[.mondialRelay], "1009")
    }

    func testAPostcodeGivenToACarrierThatTakesAnyCountrysPostcodeBringsNoCountry() throws {
        let mondialRelay = try postcodeField(.mondialRelay, "12345678")
        let postNL = try postcodeField(.springGDS, "3SABCD826405193")
        let glsSwitzerland = try postcodeField(.glsCh, "999999999916")
        let dpdGermany = CarrierID(rawValue: "dpd-de")
        // DPD Switzerland and DHL Germany read the postcode alone, from any country: a French one given them is neither
        // Swiss nor German, so a field that reads the country neither takes nor offers it.
        for carrier in [CarrierID.dpd, .dhl] {
            let french = [given(carrier, "75001")]
            var postcodes = CarrierPostcodes()
            postcodes.prepare(.mondialRelay, requirement: mondialRelay, among: french)
            XCTAssertEqual(postcodes[.mondialRelay], "", "\(carrier.rawValue)")
            XCTAssertEqual(postcodes.country(.mondialRelay, requirement: mondialRelay, region: "FR"), "FR")
            postcodes.prepare(.springGDS, requirement: postNL, among: french)
            XCTAssertNil(postcodes.offering(.springGDS, requirement: postNL, among: french), "\(carrier.rawValue)")
        }
        // Changing a DPD parcel's carrier to Mondial Relay leaves its French postcode out too.
        let parcel = Parcel(id: UUID(), trackingNumber: "06080000000001", label: "", carrier: .dpd, createdAt: "2026-10-01T08:00:00Z",
                            syncStatus: .ok, dpdPostcode: "75001", notificationsMuted: false)
        var change = CarrierPostcodes(parcel: parcel)
        change.prepare(.mondialRelay, requirement: mondialRelay, among: GivenPostcode.candidates(parcels: [parcel], remembered: []))
        XCTAssertEqual(change[.mondialRelay], "")

        // DPD Germany takes German postcodes only: one given to it is German.
        let german = [given(dpdGermany, "10115")]
        var postcodes = CarrierPostcodes()
        postcodes.prepare(.mondialRelay, requirement: mondialRelay, among: german)
        XCTAssertEqual(postcodes[.mondialRelay], "10115")
        XCTAssertEqual(postcodes.country(.mondialRelay, requirement: mondialRelay, region: "FR"), "DE")
        XCTAssertEqual(postcodes.offering(.springGDS, requirement: postNL, among: german),
                       GivenPostcode(carrier: dpdGermany, postcode: "10115", country: "DE"))
        // A country recorded with it still wins.
        var recorded = CarrierPostcodes()
        recorded.prepare(.mondialRelay, requirement: mondialRelay, among: [GivenPostcode(carrier: .dpd, postcode: "75001", country: "FR")])
        XCTAssertEqual(recorded[.mondialRelay], "75001")
        XCTAssertEqual(recorded.country(.mondialRelay, requirement: mondialRelay, region: "BE"), "FR")
        // A field that reads the postcode alone takes it as before.
        var gls = CarrierPostcodes()
        gls.prepare(.glsCh, requirement: glsSwitzerland, among: [given(.dpd, "8004")])
        XCTAssertEqual(gls[.glsCh], "8004")
    }

    func testTheChangeCarrierSheetFillsOrOffersAPostcodeGivenBeforeAndKeepsTheParcelsOwn() throws {
        let mondialRelay = try postcodeField(.mondialRelay, "12345678")
        let postNL = try postcodeField(.springGDS, "3SABCD826405193")
        let earlier = [given(.springGDS, "1234 AB")]
        func parcel(_ carrier: CarrierID, _ number: String, postcode: String?) -> Parcel {
            Parcel(id: UUID(), trackingNumber: number, label: "", carrier: carrier, createdAt: "2026-10-01T08:00:00Z",
                   syncStatus: .ok, dpdPostcode: postcode, notificationsMuted: false)
        }

        // Another carrier's required field starts from the last postcode given that fits, not the parcel's own for
        // its carrier; what is typed wins.
        var other = CarrierPostcodes(parcel: parcel(.dpd, "12345678", postcode: "8000"))
        other.prepare(.mondialRelay, requirement: mondialRelay, among: earlier)
        XCTAssertEqual(other[.mondialRelay], "1234 AB")
        XCTAssertNil(other.offering(.mondialRelay, requirement: mondialRelay, among: earlier))
        other[.mondialRelay] = ""
        XCTAssertEqual(other[.mondialRelay], "")
        XCTAssertNil(other.offering(.mondialRelay, requirement: mondialRelay, among: earlier))

        // The parcel's own postcode stays in its own carrier's field, required or optional, and nothing is offered.
        var ownRequired = CarrierPostcodes(parcel: parcel(.mondialRelay, "12345678", postcode: "75001"))
        ownRequired.prepare(.mondialRelay, requirement: mondialRelay, among: earlier)
        XCTAssertEqual(ownRequired[.mondialRelay], "75001")
        var ownOptional = CarrierPostcodes(parcel: parcel(.springGDS, "3SABCD826405193", postcode: "1012 AB"))
        ownOptional.prepare(.springGDS, requirement: postNL, among: earlier)
        XCTAssertEqual(ownOptional[.springGDS], "1012 AB")
        XCTAssertNil(ownOptional.offering(.springGDS, requirement: postNL, among: earlier))

        // The parcel's own carrier without a postcode: a required field is filled, an optional one only offered it.
        var unsetRequired = CarrierPostcodes(parcel: parcel(.mondialRelay, "12345678", postcode: nil))
        unsetRequired.prepare(.mondialRelay, requirement: mondialRelay, among: earlier)
        XCTAssertEqual(unsetRequired[.mondialRelay], "1234 AB")
        var unsetOptional = CarrierPostcodes(parcel: parcel(.springGDS, "3SABCD826405193", postcode: nil))
        unsetOptional.prepare(.springGDS, requirement: postNL, among: earlier)
        XCTAssertEqual(unsetOptional[.springGDS], "")
        XCTAssertTrue(postNL.isSatisfied(by: unsetOptional[.springGDS]))
        XCTAssertEqual(unsetOptional.offering(.springGDS, requirement: postNL, among: earlier)?.postcode, "1234 AB")
        unsetOptional.use("1234 AB", for: .springGDS)
        XCTAssertEqual(unsetOptional[.springGDS], "1234 AB")
        XCTAssertNil(unsetOptional.offering(.springGDS, requirement: postNL, among: earlier))
    }

    func testAFileWithAPostcodeOnItsParcelsStillOpens() throws {
        let saved = DeviceParcel(linkID: "22222222222A", label: "Moon lamp", archivedAt: nil, parcel: Parcel(shared: package(UUID())),
                                 checkedAt: Date(timeIntervalSince1970: 1_800_000_000))
        var file = try XCTUnwrap(JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode([saved])) as? [[String: Any]])
        file[0]["postcode"] = "75001"

        let parcels = try JSONDecoder.deliveryTracker.decode([DeviceParcel].self, from: JSONSerialization.data(withJSONObject: file))

        XCTAssertEqual(parcels, [saved])
    }

    // MARK: - Whether a lookup had a postcode

    /// A DPD parcel its carrier has answered for: DPD shows more with the delivery postcode.
    private func checkedDPD(_ id: UUID) -> PublicPackage {
        var checked = package(id, number: "06080000000002", carrier: .dpd)
        checked.lastSyncedAt = "2026-10-02T08:00:00Z"
        return checked
    }

    func testALookupWithAPostcodeNotesOnlyThatItHadOneAndAsksForNone() async throws {
        let (defaults, suite) = defaults()
        defer { defaults.removePersistentDomain(forName: suite) }
        let catalog = CarrierCatalog(cacheURL: nil)
        let id = UUID()
        let storage = Memory()
        let found = PublicLookupResponse(link: link("22222222222A"), key: key, package: checkedDPD(id))
        let device = DeviceParcels(client: client(Calls(), lookup: found), storage: storage, postcodes: PostcodeMemory(defaults: defaults))

        let parcel = try await device.add(CreatePackageRequest(trackingNumber: "06080000000002", carrier: .dpd, dpdPostcode: " QX7 7QX "))

        XCTAssertEqual(storage.parcels.map(\.postcodeGiven), [true])
        XCTAssertEqual(device.postcodeGiven(id: id), true)
        // Yes or no only: the postcode stays off the parcel and its row.
        XCTAssertNil(parcel.dpdPostcode)
        XCTAssertFalse(String(decoding: try JSONEncoder.deliveryTracker.encode(storage.parcels), as: UTF8.self).contains("QX7"))
        XCTAssertTrue(parcel.showsMoreWithPostcode(catalog), "The parcel alone can't tell")
        XCTAssertFalse(parcel.showsMoreWithPostcode(catalog, postcodeGiven: true))
    }

    func testALookupWithoutAPostcodeStillAsksForOneItsCarrierShowsMoreWith() async throws {
        let (defaults, suite) = defaults()
        defer { defaults.removePersistentDomain(forName: suite) }
        let catalog = CarrierCatalog(cacheURL: nil)
        for postcode in [nil, " "] as [String?] {
            let id = UUID()
            let storage = Memory()
            let found = PublicLookupResponse(link: link("22222222222A"), key: key, package: checkedDPD(id))
            let device = DeviceParcels(client: client(Calls(), lookup: found), storage: storage, postcodes: PostcodeMemory(defaults: defaults))

            let parcel = try await device.add(CreatePackageRequest(trackingNumber: "06080000000002", carrier: .dpd, dpdPostcode: postcode))

            XCTAssertEqual(storage.parcels.map(\.postcodeGiven), [false], String(describing: postcode))
            XCTAssertTrue(parcel.showsMoreWithPostcode(catalog, postcodeGiven: device.postcodeGiven(id: id) == true), String(describing: postcode))
        }
    }

    func testChangingTheCarrierWithoutAPostcodeNotesThatItWentWithNone() async throws {
        let catalog = CarrierCatalog(cacheURL: nil)
        let previousID = UUID(), nextID = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(
            linkID: "22222222222A", label: "Moon lamp", archivedAt: nil,
            parcel: Parcel(shared: package(previousID, number: "06080000000002", carrier: .glsCh)), checkedAt: Date(), postcodeGiven: true
        )]
        let calls = Calls()
        let moved = checkedDPD(nextID)
        let device = DeviceParcels(client: client(calls, move: { _ in moved }), storage: storage)

        let parcel = try await device.changeCarrier(id: previousID, carrier: .dpd, trackingURL: nil, dpdPostcode: nil)

        XCTAssertEqual(parcel.id, nextID)
        XCTAssertEqual(calls.lookupPostcodes, [nil])
        XCTAssertEqual(storage.parcels.map(\.linkID), ["22222222222A"])
        XCTAssertEqual(storage.parcels.map(\.postcodeGiven), [false])
        XCTAssertEqual(device.postcodeGiven(id: nextID), false)
        XCTAssertTrue(parcel.showsMoreWithPostcode(catalog, postcodeGiven: device.postcodeGiven(id: nextID) == true))
    }

    func testAnEntryKeptBeforeLookupsNotedTheirPostcodeStillAsksForOne() throws {
        let catalog = CarrierCatalog(cacheURL: nil)
        let id = UUID()
        let noted = DeviceParcel(linkID: "22222222222A", label: "Moon lamp", archivedAt: nil, parcel: Parcel(shared: checkedDPD(id)),
                                 checkedAt: Date(timeIntervalSince1970: 1_800_000_000), postcodeGiven: true)
        let older = try file([noted]) { rows in
            XCTAssertEqual(rows[0]["postcodeGiven"] as? Bool, true)
            rows[0].removeValue(forKey: "postcodeGiven")
        }

        let opened = DeviceParcelFiles.opened(older, keys: ["22222222222A": key])

        XCTAssertEqual(opened.parcels.map(\.linkID), ["22222222222A"])
        XCTAssertEqual(opened.parcels.map(\.postcodeGiven), [nil])
        let storage = Memory()
        storage.parcels = opened.parcels
        storage.keys = opened.keys
        let device = DeviceParcels(client: client(Calls()), storage: storage)
        XCTAssertNil(device.postcodeGiven(id: id))
        // Unknown, as before: the note still shows.
        let parcel = try XCTUnwrap(device.list().first)
        XCTAssertTrue(parcel.showsMoreWithPostcode(catalog, postcodeGiven: device.postcodeGiven(id: id) == true))
    }

    func testAnAccountTakesTheParcelsOverAndTheDeviceLetsGo() {
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "Kept", archivedAt: nil, parcel: Parcel(shared: package(UUID())), checkedAt: Date()),
            DeviceParcel(linkID: "22222222222B", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID(), number: "TESTPARCEL999")), checkedAt: Date()),
        ]
        let device = DeviceParcels(client: client(Calls()), storage: storage)
        XCTAssertEqual(device.claims, [ClaimParcelLink(id: "22222222222A", key: key, label: "Kept"), ClaimParcelLink(id: "22222222222B", key: key)])

        device.release(["22222222222A"])

        XCTAssertEqual(device.claims.map(\.id), ["22222222222B"])
        XCTAssertEqual(storage.keys.keys.sorted(), ["22222222222B"])
    }

    /// An account with room for `room` more parcels. Each claim is answered for the links it sends, in their order:
    /// kept while there is room, `quota` once there is none.
    private final class AccountRoom: @unchecked Sendable {
        private let lock = NSLock()
        private var left: Int
        private var sent: [[String]] = []
        init(_ room: Int) { left = room }
        var room: Int {
            get { lock.withLock { left } }
            set { lock.withLock { left = newValue } }
        }
        var claims: [[String]] { lock.withLock { sent } }
        func answer(_ request: ClaimParcelsRequest) -> ClaimParcelsResponse {
            lock.withLock {
                sent.append(request.links.map(\.id))
                return ClaimParcelsResponse(results: request.links.map { link in
                    guard left > 0 else { return ClaimParcelResult(id: link.id, outcome: .quota) }
                    left -= 1
                    return ClaimParcelResult(id: link.id, outcome: .kept, packageID: UUID())
                })
            }
        }
    }

    /// This iPhone following `links` as its own, a store on it and a session that signs into `account`, whose list is
    /// empty. The session's saved sign-in is `persistence`'s, for a relaunch to reopen.
    private func signingIn(_ links: [String], into account: AccountRoom, storage: Memory = Memory(), calls: Calls = Calls())
        throws -> (store: ParcelStore, session: SessionStore, device: DeviceParcels, persistence: PostcodeTestSessionPersistence) {
        let user = AuthUser(id: UUID(), email: "test@example.com", isAnonymous: false)
        let signedIn = try JSONEncoder.deliveryTracker.encode(AuthSession(
            accessToken: "access", tokenType: "bearer", expiresIn: 3600,
            expiresAt: Int(Date().timeIntervalSince1970) + 3600, refreshToken: "refresh", user: user
        ))
        let list = try JSONEncoder.deliveryTracker.encode(PackageListResponse(packages: []))
        PostcodeTestURLProtocol.handler = { request in
            switch (request.httpMethod, request.url?.path) {
            case ("POST", "/auth/v1/verify"): return (200, signedIn)
            case ("POST", "/api/packages/claim"):
                guard let claim = try? JSONDecoder.deliveryTracker.decode(ClaimParcelsRequest.self, from: PostcodeTestURLProtocol.body(of: request)),
                      let answer = try? JSONEncoder.deliveryTracker.encode(account.answer(claim)) else { return (400, Data()) }
                return (200, answer)
            case ("GET", "/api/packages"): return (200, list)
            default: return (404, Data())
            }
        }
        storage.keys = Dictionary(uniqueKeysWithValues: links.map { ($0, key) })
        storage.parcels = links.enumerated().map { index, link in
            DeviceParcel(linkID: link, label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID(), number: "TESTPARCEL12300\(index)")),
                         checkedAt: Date())
        }
        let device = DeviceParcels(client: client(calls), storage: storage)
        let persistence = PostcodeTestSessionPersistence()
        let session = SessionStore(configuration: PostcodeTestURLProtocol.configuration, persistence: persistence,
                                   transport: PostcodeTestURLProtocol.shared)
        session.showWelcome()
        let store = ParcelStore(configuration: PostcodeTestURLProtocol.configuration, session: session, localizer: Localizer(),
                                transport: PostcodeTestURLProtocol.shared, device: device, deviceNotifications: quietNotifications())
        store.setDeliveryLiveActivitiesEnabled(false)
        return (store, session, device, persistence)
    }

    /// A link this iPhone forgot offline before signing in reaches the service once signed in, as a guest's refresh sends
    /// it, rather than wait for a sign-out with its parcel and its pushes.
    func testSignedInALinkForgottenOfflineBeforeSigningInIsToldWithoutASignOut() async throws {
        let storage = Memory()
        storage.forgetting = ["22222222222D": key]
        let calls = Calls()
        let (store, session, _, _) = try signingIn([], into: AccountRoom(0), storage: storage, calls: calls)
        defer { session.forceSignOut() }

        try await session.verifyCode(email: "test@example.com", code: "123456")
        XCTAssertEqual(calls.all, [])
        // A load, not the store's start, which goes on to the system's notification settings and to polling: in CI's
        // simulator the start never returned.
        await store.load(showSpinner: false)
        XCTAssertEqual(calls.all, ["forget 22222222222D"])
        XCTAssertEqual(storage.forgetting, [:])
        // Nothing waits any more: the next load tells nothing.
        await store.load(showSpinner: false)
        XCTAssertEqual(calls.all, ["forget 22222222222D"])
    }

    func testClosingNotificationsBeforeTheirSettingsArriveSaysNothingWentWrong() async throws {
        let (store, session, _, _) = try signingIn([], into: AccountRoom(0))
        defer { session.forceSignOut() }
        try await session.verifyCode(email: "test@example.com", code: "123456")
        let asked = expectation(description: "The settings were asked for")
        PostcodeTestURLProtocol.held = { request in
            guard request.url?.path == "/api/push/preferences" else { return false }
            asked.fulfill()
            return true
        }
        defer { PostcodeTestURLProtocol.held = nil }

        // Settings › Notifications reads them in its task, which closing the page cancels: URLSession ends the request
        // with URLError.cancelled rather than CancellationError.
        let reading = Task { await store.loadNotificationPreferences() }
        await fulfillment(of: [asked], timeout: 2)
        reading.cancel()
        await reading.value
        XCTAssertNil(store.notificationError)
        XCTAssertNil(store.notificationPreferences)
    }

    func testAParcelAFullAccountRefusesStaysHereAndTheListIsToldOnceASignIn() async throws {
        let account = AccountRoom(1)
        let (store, session, device, _) = try signingIn(["22222222222A", "22222222222B", "22222222222C"], into: account)
        defer { session.forceSignOut() }

        // Signing in brings this iPhone's parcels along: the account takes one, and the two it has no room for stay.
        try await session.verifyCode(email: "test@example.com", code: "123456")
        await store.claimDeviceParcels()
        XCTAssertEqual(account.claims, [["22222222222A", "22222222222B", "22222222222C"]])
        XCTAssertEqual(device.claims.map(\.id), ["22222222222B", "22222222222C"])
        XCTAssertEqual(store.leftOnDevice?.count, 2)
        // The list says it, and lets the news go.
        store.leftOnDevice = nil

        // Room for one more: bringing them along again takes it, and the one that stays is news.
        account.room = 1
        await store.claimDeviceParcels()
        XCTAssertEqual(account.claims.last, ["22222222222B", "22222222222C"])
        XCTAssertEqual(device.claims.map(\.id), ["22222222222C"])
        XCTAssertEqual(store.leftOnDevice?.count, 1)
        store.leftOnDevice = nil

        // The next sign-in is told again.
        session.forceSignOut()
        XCTAssertNil(store.leftOnDevice)
        try await session.verifyCode(email: "test@example.com", code: "123456")
        await store.claimDeviceParcels()
        XCTAssertEqual(device.claims.map(\.id), ["22222222222C"])
        XCTAssertEqual(store.leftOnDevice?.count, 1)
        store.leftOnDevice = nil

        // Once the account takes the last, nothing stays and nothing is said.
        account.room = 1
        await store.claimDeviceParcels()
        XCTAssertEqual(device.claims, [])
        XCTAssertNil(store.leftOnDevice)
    }

    func testARefreshOrARelaunchWhileTheAccountIsStillFullSaysNothingAgain() async throws {
        let account = AccountRoom(0)
        let (store, session, device, persistence) = try signingIn(["22222222222A"], into: account)
        defer { session.forceSignOut() }
        try await session.verifyCode(email: "test@example.com", code: "123456")
        await store.claimDeviceParcels()
        XCTAssertEqual(store.leftOnDevice?.count, 1)
        store.leftOnDevice = nil

        // A refresh reads the account's list and brings nothing along.
        await store.load(showSpinner: false)
        XCTAssertEqual(account.claims.count, 1)
        XCTAssertNil(store.leftOnDevice)
        // Bringing them along again, as the list does when an invitation comes or goes, hears the same.
        await store.claimDeviceParcels()
        XCTAssertEqual(account.claims.count, 2)
        XCTAssertEqual(device.claims.map(\.id), ["22222222222A"])
        XCTAssertNil(store.leftOnDevice)

        // A relaunch reopens the account and asks again: still nothing new.
        let reopened = SessionStore(configuration: PostcodeTestURLProtocol.configuration, persistence: persistence,
                                    transport: PostcodeTestURLProtocol.shared)
        let relaunched = ParcelStore(configuration: PostcodeTestURLProtocol.configuration, session: reopened, localizer: Localizer(),
                                     transport: PostcodeTestURLProtocol.shared, device: device, deviceNotifications: quietNotifications())
        relaunched.setDeliveryLiveActivitiesEnabled(false)
        await reopened.bootstrap()
        XCTAssertEqual(reopened.user?.id, session.user?.id)
        await relaunched.claimDeviceParcels()
        XCTAssertEqual(account.claims.count, 3)
        XCTAssertEqual(device.claims.map(\.id), ["22222222222A"])
        XCTAssertNil(relaunched.leftOnDevice)
        reopened.forceSignOut()
    }

    func testALinkIsSharedUntilItsSharingIsStopped() async throws {
        let id = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(id)), checkedAt: Date())]
        let calls = Calls()
        let shown = PublicParcelResponse(link: link("22222222222A"), package: package(id))
        let device = DeviceParcels(client: client(calls, read: { _ in shown }), storage: storage)

        let current = try await device.share(id: id)
        XCTAssertEqual(current?.id, "22222222222A")
        let changed = try await device.setShare(id: id, showNumber: true, gift: false)
        XCTAssertEqual(changed?.showNumber, true)
        let stopped = try await device.setShare(id: id, showNumber: false, gift: false, shared: false)
        XCTAssertNil(stopped)
        XCTAssertEqual(calls.all, ["read 22222222222A k", "update 22222222222A shared=true", "update 22222222222A shared=false"])
    }

    // MARK: - Following a link

    func testAParcelFollowedThroughSomeoneElsesLinkNeedsNoKeyAndIsForgottenHereAlone() async throws {
        let id = UUID()
        var now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        let calls = Calls()
        let shown = PublicParcelResponse(link: reader("33333333333A"), package: package(id))
        let device = DeviceParcels(client: client(calls), storage: storage, now: { now })

        let followed = try device.follow(shown, key: nil, label: "Books for Ana")
        XCTAssertEqual(followed.id, id)
        XCTAssertTrue(followed.added)
        XCTAssertFalse(try device.follow(shown, key: nil, label: "Again").added, "Followed once")
        XCTAssertEqual(device.list().map(\.label), ["Books for Ana"])
        XCTAssertTrue(storage.keys.isEmpty)
        XCTAssertEqual(storage.parcels.map(\.isViewer), [true])
        XCTAssertEqual(device.viewerLink(id: id), "33333333333A")
        XCTAssertEqual(device.parcelID(linkID: "33333333333A"), id)
        // An account may keep it as the link allows, with no key.
        XCTAssertEqual(device.claims, [ClaimParcelLink(id: "33333333333A", label: "Books for Ana")])
        XCTAssertThrowsError(try device.follow(PublicParcelResponse(link: reader("not-a-link"), package: package(UUID())), key: nil, label: nil))

        // After a restart it is read as anyone with the link may, and the link says whether an account may keep it.
        let moved = PublicParcelResponse(link: reader("33333333333A", canKeep: false), package: package(id, stage: .outForDelivery))
        let again = DeviceParcels(client: client(calls, read: { _ in moved }), storage: storage, now: { now })
        XCTAssertEqual(again.list().map(\.label), ["Books for Ana"])
        now += DeviceParcels.fresh + 1
        try await again.refresh()
        XCTAssertEqual(calls.all, ["read 33333333333A -"])
        XCTAssertEqual(again.list().first?.currentStage, .outForDelivery)
        XCTAssertEqual(again.claims, [], "Its link no longer lets an account keep it")
        _ = try again.rename(id: id, label: "Ana's books")
        await XCTAssertThrowsErrorAsync(try await again.changeCarrier(id: id, carrier: .ups, trackingURL: nil, dpdPostcode: nil))

        // Forgotten here alone: the link is not this iPhone's to forget.
        await again.forget(id: id)
        XCTAssertEqual(calls.all, ["read 33333333333A -"])
        XCTAssertTrue(again.isEmpty)
        XCTAssertTrue(storage.parcels.isEmpty)
    }

    func testOnlyParcelsOfThisIPhonesOwnNeedTheirKeysOnFile() throws {
        let own = DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID())), checkedAt: Date())
        let lost = DeviceParcel(linkID: "22222222222B", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID())), checkedAt: Date())
        var followed = DeviceParcel(linkID: "33333333333A", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID())), checkedAt: Date())
        followed.viewer = true
        let loaded = DeviceParcelFiles.matched([own, lost, followed], keys: ["22222222222A": key, "33333333333A": key, "22222222222C": key])
        XCTAssertEqual(loaded.parcels.map(\.linkID), ["22222222222A", "33333333333A"])
        XCTAssertEqual(loaded.keys, ["22222222222A": key, "22222222222C": key],
                       "A key beside someone else's parcel opens nothing; one beside none is its parcel's to read again")
        XCTAssertEqual(DeviceParcelFiles.matched([followed], keys: [:]).parcels, [followed])

        // A file written before parcels could be followed this way still opens, every parcel its own.
        let file = try JSONEncoder.deliveryTracker.encode([own])
        XCTAssertFalse(String(decoding: file, as: UTF8.self).contains("viewer"))
        XCTAssertEqual(try JSONDecoder.deliveryTracker.decode([DeviceParcel].self, from: file).map(\.isViewer), [false])
        let saved = try JSONDecoder.deliveryTracker.decode([DeviceParcel].self, from: JSONEncoder.deliveryTracker.encode([followed]))
        XCTAssertEqual(saved, [followed])
    }

    // MARK: - A list this build can't read

    /// The parcel behind a link of this iPhone's own, as the service answers it with the key.
    private func answer(_ linkID: String, _ id: UUID, number: String = "TESTPARCEL123789") -> PublicParcelResponse {
        PublicParcelResponse(link: link(linkID), package: package(id, number: number))
    }

    /// The list on file as JSON rows, changed as another build might have written it.
    private func file(_ parcels: [DeviceParcel], _ change: (inout [[String: Any]]) -> Void = { _ in }) throws -> Data {
        var rows = try XCTUnwrap(JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode(parcels)) as? [[String: Any]])
        change(&rows)
        return try JSONSerialization.data(withJSONObject: rows)
    }

    func testARowThisBuildCannotReadLeavesTheOthersAndForgetsNothing() async throws {
        let first = UUID(), second = UUID(), third = UUID()
        let parcels = [
            DeviceParcel(linkID: "22222222222A", label: "Lamp", archivedAt: nil, parcel: Parcel(shared: package(first)), checkedAt: Date()),
            DeviceParcel(linkID: "22222222222B", label: "Books", archivedAt: nil, parcel: Parcel(shared: package(second, number: "TESTPARCEL456")), checkedAt: Date()),
            DeviceParcel(linkID: "22222222222C", label: "Shoes", archivedAt: nil, parcel: Parcel(shared: package(third, number: "TESTPARCEL999")), checkedAt: Date()),
        ]
        let keys = ["22222222222A": key, "22222222222B": key, "22222222222C": key]

        let opened = DeviceParcelFiles.opened(try file(parcels) { $0[1]["linkID"] = 42 }, keys: keys)

        XCTAssertEqual(opened.parcels, [parcels[0], parcels[2]])
        XCTAssertEqual(opened.keys, keys, "The unread row's key stays")
        let storage = Memory()
        storage.parcels = opened.parcels
        storage.keys = opened.keys
        let calls = Calls()
        let shown = answer("22222222222B", second, number: "TESTPARCEL456")
        let device = DeviceParcels(client: client(calls, read: { $0 == "22222222222B" ? shown : nil }), storage: storage)
        XCTAssertEqual(device.list().map(\.label), ["Lamp", "Shoes"])
        XCTAssertEqual(device.unlisted, 1, "Forget all counts it")
        // Its key goes to an account like the others, before it is read again.
        XCTAssertEqual(device.claims.map(\.id), ["22222222222A", "22222222222C", "22222222222B"])
        XCTAssertEqual(device.claims.last, ClaimParcelLink(id: "22222222222B", key: key))

        try await device.refresh()

        XCTAssertEqual(calls.all, ["read 22222222222B k"], "Read again with its key, and nothing forgotten")
        XCTAssertEqual(device.list().map(\.id), [first, third, second])
        XCTAssertEqual(device.unlisted, 0)
        XCTAssertEqual(storage.keys, keys)
    }

    func testRecoveryUpgradesViewerWithOwnerLink() async throws {
        let id = UUID(), owner = "22222222222A", viewer = "22222222222B"
        let storage = Memory()
        storage.keys = [owner: key]
        let owned = answer(owner, id)
        let device = DeviceParcels(client: client(Calls(), read: { _ in owned }), storage: storage)
        _ = try device.follow(PublicParcelResponse(link: reader(viewer), package: package(id)), key: nil, label: "Lamp")

        try await device.refresh()

        let reopened = DeviceParcels(client: nil, storage: storage)
        XCTAssertEqual(reopened.list().map(\.label), ["Lamp"])
        XCTAssertNil(reopened.viewerLink(id: id))
        XCTAssertEqual(storage.parcels.map(\.linkID), [owner])
        XCTAssertEqual(storage.keys, [owner: key])
    }

    func testAFileThatCannotBeReadAtAllIsReadAgainWithItsKeys() async throws {
        let first = UUID(), second = UUID()
        let keys = ["22222222222A": key, "22222222222B": key]
        let opened = DeviceParcelFiles.opened(Data("not a list".utf8), keys: keys)
        XCTAssertTrue(opened.parcels.isEmpty)
        XCTAssertEqual(opened.keys, keys)
        XCTAssertEqual(DeviceParcelFiles.opened(Data("{}".utf8), keys: keys).keys, keys)
        XCTAssertEqual(DeviceParcelFiles.opened(nil, keys: keys).keys, [:], "No file, no parcels to read again")

        let storage = Memory()
        storage.keys = opened.keys
        let calls = Calls()
        let answers = ["22222222222A": answer("22222222222A", first), "22222222222B": answer("22222222222B", second, number: "TESTPARCEL999")]
        let device = DeviceParcels(client: client(calls, read: { answers[$0] }), storage: storage)
        XCTAssertTrue(device.isEmpty)

        try await device.refresh()

        XCTAssertEqual(Set(calls.all), ["read 22222222222A k", "read 22222222222B k"])
        XCTAssertEqual(Set(device.list().map(\.id)), [first, second])
        XCTAssertEqual(device.list().map(\.label), ["", ""], "The names lived only in the list")
        XCTAssertEqual(Set(device.claims), [ClaimParcelLink(id: "22222222222A", key: key), ClaimParcelLink(id: "22222222222B", key: key)])
        XCTAssertEqual(Set(storage.parcels.map(\.linkID)), ["22222222222A", "22222222222B"])
        XCTAssertEqual(storage.keys, keys)
        try await device.refresh()
        XCTAssertEqual(calls.all.count, 2, "Back on the list, they are fresh")
    }

    func testAnEntryWrittenBeforeAFieldWasRequiredIsReadAgainWithItsKey() async throws {
        let kept = UUID(), upgraded = UUID()
        let parcels = [
            DeviceParcel(linkID: "22222222222A", label: "Lamp", archivedAt: nil, parcel: Parcel(shared: package(kept)), checkedAt: Date()),
            DeviceParcel(linkID: "22222222222B", label: "Books", archivedAt: nil, parcel: Parcel(shared: package(upgraded, number: "TESTPARCEL999")), checkedAt: Date()),
        ]
        let keys = ["22222222222A": key, "22222222222B": key]
        // An older build wrote no `notificationsMuted`, which this one requires.
        let older = try file(parcels) { rows in
            var parcel = rows[1]["parcel"] as? [String: Any]
            parcel?.removeValue(forKey: "notificationsMuted")
            rows[1]["parcel"] = parcel
        }

        let opened = DeviceParcelFiles.opened(older, keys: keys)

        XCTAssertEqual(opened.parcels.map(\.linkID), ["22222222222A"])
        let storage = Memory()
        storage.parcels = opened.parcels
        storage.keys = opened.keys
        let calls = Calls()
        let shown = answer("22222222222B", upgraded, number: "TESTPARCEL999")
        let device = DeviceParcels(client: client(calls, read: { $0 == "22222222222B" ? shown : nil }), storage: storage)
        try await device.refresh()
        XCTAssertEqual(calls.all, ["read 22222222222B k"])
        XCTAssertEqual(device.list().map(\.id), [kept, upgraded])
        XCTAssertEqual(device.list().map(\.label), ["Lamp", ""])
        XCTAssertEqual(storage.keys, keys)
    }

    func testAKeyWhoseLinkIsGoneLetsGoWithoutForgettingAnything() async throws {
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        let calls = Calls()
        // Out of reach, the keys wait for the next refresh.
        let offline = DeviceParcels(client: client(calls, read: { _ in throw URLError(.notConnectedToInternet) }), storage: storage)
        await XCTAssertThrowsErrorAsync(try await offline.refresh())
        XCTAssertEqual(storage.keys.count, 2)

        // Gone, or no longer the key's to own: the key lets go, and the link is left as it is.
        let notOwned = PublicParcelResponse(link: reader("22222222222B"), package: package(UUID()))
        let device = DeviceParcels(client: client(calls, read: { $0 == "22222222222B" ? notOwned : nil }), storage: storage)
        try await device.refresh()
        XCTAssertTrue(device.isEmpty)
        XCTAssertEqual(storage.keys, [:])
        XCTAssertEqual(calls.all.filter { $0.hasPrefix("forget") }, [])
        XCTAssertEqual(calls.all.count, 3)
    }

    func testAParcelForgottenOfflineIsForgottenOnTheServiceLaterAndDoesNotComeBack() async throws {
        let id = UUID()
        let saved = DeviceParcel(linkID: "22222222222A", label: "Lamp", archivedAt: nil, parcel: Parcel(shared: package(id)), checkedAt: .distantPast)
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        storage.parcels = [saved]
        let calls = Calls()
        let shown = answer("22222222222A", id)
        var offline = client(calls, read: { _ in shown })
        offline.forget = { linkID, _ in calls.add("forget \(linkID)"); throw URLError(.notConnectedToInternet) }
        let device = DeviceParcels(client: offline, storage: storage)

        await device.forget(id: id)

        XCTAssertTrue(device.isEmpty, "It leaves the list at once")
        XCTAssertEqual(storage.keys, [:])
        XCTAssertEqual(storage.forgetting, ["22222222222A": key])
        try await device.refresh(all: true)
        XCTAssertEqual(calls.all, ["forget 22222222222A", "forget 22222222222A"], "Told again, never read back")
        XCTAssertTrue(device.isEmpty)

        // After a restart, with the service in reach, it is forgotten for good.
        let relaunched = DeviceParcels(client: client(calls, read: { _ in shown }), storage: storage)
        XCTAssertTrue(relaunched.isEmpty)
        try await relaunched.refresh(all: true)
        try await relaunched.refresh(all: true)
        XCTAssertEqual(Array(calls.all.dropFirst(2)), ["forget 22222222222A"])
        XCTAssertTrue(relaunched.isEmpty)
        XCTAssertEqual(storage.forgetting, [:])
        XCTAssertTrue(storage.parcels.isEmpty)

        // A list saved before the forget was leaves the parcel out all the same.
        let early = Memory()
        early.parcels = [saved]
        early.keys = ["22222222222A": key]
        early.forgetting = ["22222222222A": key]
        let earlyCalls = Calls()
        let reopened = DeviceParcels(client: client(earlyCalls, read: { _ in shown }), storage: early)
        XCTAssertTrue(reopened.isEmpty)
        try await reopened.refresh(all: true)
        XCTAssertEqual(earlyCalls.all, ["forget 22222222222A"])
        XCTAssertEqual(early.keys, [:])
    }

    func testFailedForgetQueueWriteKeepsActiveKeyUntilRetry() async throws {
        let storage = Memory()
        let id = UUID(), linkID = "22222222222A"
        var offline = client(Calls())
        offline.forget = { _, _ in throw URLError(.notConnectedToInternet) }
        let device = DeviceParcels(client: offline, storage: storage)
        _ = try device.follow(answer(linkID, id), key: key, label: nil)
        storage.failQueueWrite = true

        await device.forget(id: id)

        XCTAssertTrue(storage.forgetting.isEmpty)
        XCTAssertEqual(storage.keys, [linkID: key])
        XCTAssertEqual(storage.unqueued, [linkID])
        let restarted = DeviceParcels(client: offline, storage: storage)
        XCTAssertTrue(restarted.isEmpty)
        storage.failQueueWrite = false
        try await restarted.refresh()
        XCTAssertTrue(storage.unqueued.isEmpty)
        XCTAssertEqual(storage.forgetting, [linkID: key])
        XCTAssertTrue(storage.keys.isEmpty)

        let calls = Calls()
        let reopened = DeviceParcels(client: client(calls), storage: storage)
        await reopened.tellForgotten()
        XCTAssertEqual(calls.all, ["forget \(linkID)"])
        XCTAssertTrue(storage.forgetting.isEmpty)
    }

    func testAuthenticatedFollowCancelsUnqueuedForgetAfterRestart() async throws {
        let storage = Memory()
        let id = UUID(), linkID = "22222222222A"
        let owner = answer(linkID, id)
        let device = DeviceParcels(client: nil, storage: storage)
        _ = try device.follow(owner, key: key, label: nil)
        storage.failQueueWrite = true
        await device.forget(id: id)

        let reopened = DeviceParcels(client: nil, storage: storage)
        _ = try reopened.follow(owner, key: key, label: nil)
        let calls = Calls()
        let restored = DeviceParcels(client: client(calls), storage: storage)
        await restored.tellForgotten()

        XCTAssertTrue(calls.all.isEmpty)
        XCTAssertEqual(restored.list().map(\.id), [id])
        XCTAssertEqual(storage.keys, [linkID: key])
        XCTAssertTrue(storage.unqueued.isEmpty)
    }

    func testViewerFollowKeepsRevocationUntilOwnershipIsRestored() async throws {
        let id = UUID(), linkID = "22222222222A"
        let shown = PublicParcelResponse(link: reader(linkID), package: package(id))
        for restoreOwner in [false, true] {
            let storage = Memory()
            storage.forgetting = [linkID: key]
            let calls = Calls()
            let device = DeviceParcels(client: client(calls), storage: storage)
            _ = try device.follow(shown, key: nil, label: "Lamp")
            XCTAssertEqual(storage.forgetting, [linkID: key])
            let reopened = DeviceParcels(client: client(calls), storage: storage)
            XCTAssertEqual(reopened.viewerLink(id: id), linkID)
            if restoreOwner {
                XCTAssertFalse(try reopened.follow(answer(linkID, id), key: key, label: nil).added)
                XCTAssertEqual(storage.keys, [linkID: key])
            }
            await reopened.tellForgotten()
            XCTAssertEqual(calls.all, restoreOwner ? [] : ["forget \(linkID)"])
            XCTAssertTrue(storage.forgetting.isEmpty)
        }
    }

    func testALinkFollowedAgainBeforeTheServiceForgotItStays() async throws {
        let id = UUID()
        let storage = Memory()
        storage.forgetting = ["22222222222A": key]
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: storage)

        _ = try device.follow(answer("22222222222A", id), key: key, label: "Lamp")
        try await device.refresh()

        XCTAssertEqual(storage.forgetting, [:])
        XCTAssertEqual(calls.all, [])
        XCTAssertEqual(device.list().map(\.id), [id])
    }

    func testALinkFollowedAgainWhileTheServiceIsToldOfAnotherStays() async throws {
        let storage = Memory()
        storage.forgetting = ["22222222222A": key, "22222222222B": key]
        let calls = Calls()
        let gate = AlertGate()
        var gated = client(calls)
        gated.forget = { linkID, _ in
            calls.add("forget \(linkID)")
            await gate.pass("forget")
        }
        let device = DeviceParcels(client: gated, storage: storage)

        let refreshing = Task { try await device.refresh() }
        await gate.arrived("forget")
        let told = try XCTUnwrap(calls.all.first?.split(separator: " ").last.map(String.init))
        let other = told == "22222222222A" ? "22222222222B" : "22222222222A"
        _ = try device.follow(answer(other, UUID()), key: key, label: "Lamp")
        await gate.release("forget")
        try await refreshing.value

        XCTAssertEqual(calls.all.filter { $0.hasPrefix("forget") }, ["forget \(told)"], "The link followed again is not forgotten at the service")
        XCTAssertEqual(storage.forgetting, [:])
        XCTAssertEqual(device.list().count, 1)
    }

    func testForgettingEveryParcelForgetsTheOnesStillToBeReadAgain() async {
        let storage = Memory()
        storage.keys = ["22222222222A": key]
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: storage)
        XCTAssertTrue(device.isEmpty)
        XCTAssertEqual(device.unlisted, 1, "Nothing listed, one parcel to forget")

        await device.forgetAll()

        XCTAssertEqual(calls.all, ["forget 22222222222A"])
        XCTAssertEqual(device.unlisted, 0)
        XCTAssertEqual(storage.keys, [:])
        XCTAssertEqual(storage.forgetting, [:])
    }

    /// Signed out, the poll can come round while Forget all turns a parcel's notifications off: it no longer reads
    /// again the parcel whose saved copy was lost, so nothing is left, listed or with its key, after Forget all.
    func testForgetAllLeavesNothingWhenARefreshComesRoundMeanwhile() async throws {
        let storage = Memory()
        var listed = DeviceParcel(linkID: "22222222222A", label: "Lamp", archivedAt: nil,
                                  parcel: Parcel(shared: package(UUID())), checkedAt: Date())
        listed.alertToken = "token-1"
        storage.parcels = [listed]
        storage.keys = ["22222222222A": key, "22222222222C": key]
        let lost = PublicParcelResponse(link: link("22222222222C"), package: package(UUID()))
        let calls = Calls()
        let gate = AlertGate()
        var gated = client(calls, read: { linkID in linkID == "22222222222C" ? lost : nil })
        gated.removeAlert = { linkID, request in
            calls.add("unalert \(linkID) \(request.token)")
            await gate.pass(request.token)
        }
        let device = DeviceParcels(client: gated, storage: storage)

        let forgetting = Task { await device.forgetAll() }
        await gate.arrived("token-1")
        try await device.refresh()
        await gate.release("token-1")
        await forgetting.value

        XCTAssertEqual(device.list().map(\.id), [], "The lost parcel read again by the refresh is still listed")
        XCTAssertEqual(storage.keys, [:], "A key stays on the iPhone after Forget all")
        XCTAssertEqual(storage.forgetting, [:])
        XCTAssertFalse(calls.all.contains("read 22222222222C k"), "The refresh read the lost parcel again")
    }

    /// Signed out, Settings offers Forget all for parcels kept by their key alone, and its question counts them.
    func testSignedOutForgetAllCountsTheParcelsStillToBeReadAgain() async {
        let transport = PostcodeTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        PostcodeTestURLProtocol.handler = { _ in (404, Data()) }
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: storage)
        let configuration = PostcodeTestURLProtocol.configuration
        let session = SessionStore(configuration: configuration, persistence: PostcodeTestSessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let store = ParcelStore(configuration: configuration, session: session, localizer: Localizer(), transport: transport, device: device)
        store.setDeliveryLiveActivitiesEnabled(false)
        session.showWelcome()

        XCTAssertTrue(store.parcels.isEmpty)
        XCTAssertEqual(store.unlistedDeviceParcels, 2)

        await store.forgetDeviceParcels()

        XCTAssertEqual(Set(calls.all), ["forget 22222222222A", "forget 22222222222B"])
        XCTAssertEqual(store.unlistedDeviceParcels, 0)
    }

    func testARoundEndingLeavesAnotherUnderWayMarked() async throws {
        let calls = Calls()
        let gate = AlertGate()
        var held = client(calls, lookup: PublicLookupResponse(link: link("22222222222A"), key: key, package: package(UUID())))
        held.setAlert = { linkID, _, request in
            calls.add("alert \(linkID) \(request.token)")
            await gate.pass(request.token)
            return .on
        }
        let device = DeviceParcels(client: held, storage: Memory())
        _ = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", label: "", carrier: .dhl))
        let first = Task { await device.registerAlerts(token: "old-token", locale: .en) }
        await gate.arrived("old-token")
        let second = Task { await device.registerAlerts(token: "new-token", locale: .en) }
        await gate.arrived("new-token")
        await gate.release("old-token")
        await first.value
        // The second round is still under way: the same round does not start again beside it.
        await device.registerAlerts(token: "new-token", locale: .en)
        await gate.release("new-token")
        await second.value
        XCTAssertEqual(calls.all.filter { $0.hasPrefix("alert") }, ["alert 22222222222A old-token", "alert 22222222222A new-token"])
    }

    func testAHandedOverKeyMakesTheParcelThisIPhonesOwn() async throws {
        let id = UUID()
        let storage = Memory()
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: storage)
        _ = try device.follow(PublicParcelResponse(link: reader("33333333333A"), package: package(id)), key: nil, label: nil)

        // A key the service does not own the link with, or that is no key at all, changes nothing.
        XCTAssertFalse(try device.follow(PublicParcelResponse(link: reader("33333333333A"), package: package(id)), key: key, label: nil).added)
        XCTAssertFalse(try device.follow(PublicParcelResponse(link: link("33333333333A"), package: package(id)), key: "short", label: nil).added)
        XCTAssertTrue(storage.keys.isEmpty)
        XCTAssertTrue(device.isViewer(linkID: "33333333333A"))

        let owned = try device.follow(PublicParcelResponse(link: link("33333333333A"), package: package(id)), key: key, label: nil)
        XCTAssertEqual(owned.id, id)
        XCTAssertFalse(owned.added)
        XCTAssertEqual(storage.keys, ["33333333333A": key])
        XCTAssertFalse(device.isViewer(linkID: "33333333333A"))
        XCTAssertNil(device.viewerLink(id: id))
        XCTAssertEqual(device.claims, [ClaimParcelLink(id: "33333333333A", key: key)])

        // A key handed over for a parcel not here yet makes it this iPhone's own from the start.
        let other = UUID()
        XCTAssertTrue(try device.follow(PublicParcelResponse(link: link("22222222222B"), package: package(other, number: "TESTPARCEL999")), key: key, label: "Lamp").added)
        XCTAssertEqual(storage.keys["22222222222B"], key)
        await device.forget(id: other)
        XCTAssertEqual(calls.all, ["forget 22222222222B"])
    }

    // MARK: - Notifications

    func testNotificationsAreAskedForOnceForEveryParcelOnItsWayWithTheKeyOnlyForThisIPhonesOwn() async throws {
        let mine = UUID(), theirs = UUID(), arrived = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222C": key]
        var followed = DeviceParcel(linkID: "33333333333B", label: "", archivedAt: nil, parcel: Parcel(shared: package(theirs, stage: .registered)), checkedAt: Date())
        followed.viewer = true
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(mine)), checkedAt: Date()),
            followed,
            DeviceParcel(linkID: "22222222222C", label: "", archivedAt: nil, parcel: Parcel(shared: package(arrived, stage: .delivered)), checkedAt: Date()),
        ]
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: storage)
        XCTAssertTrue(device.hasParcelsOnTheirWay)

        await device.registerAlerts(token: "token1", locale: .fr, environment: .development)
        // Every scan while nothing has moved yet, the steps that matter after; nothing for a parcel that arrived.
        XCTAssertEqual(calls.all, ["alert 22222222222A k important token1 fr development", "alert 33333333333B - all token1 fr development"])
        XCTAssertEqual(storage.parcels.map(\.alertToken), ["token1", "token1", nil])
        XCTAssertEqual(storage.parcels.map(\.alertPreset), [.important, .all, nil])

        await device.registerAlerts(token: "token1", locale: .fr, environment: .development)
        XCTAssertEqual(calls.all.count, 2, "Asked once a launch")

        // A new token takes the old one's place, and the preset first chosen stays.
        await device.registerAlerts(token: "token2", locale: .fr, environment: .development)
        XCTAssertEqual(Array(calls.all.dropFirst(2)), [
            "alert 22222222222A k important token2 fr development", "unalert 22222222222A token1",
            "alert 33333333333B - all token2 fr development", "unalert 33333333333B token1",
        ])
        // Another language asks again.
        await device.registerAlerts(token: "token2", locale: .de, environment: .development)
        XCTAssertEqual(calls.all.count, 8)

        // A launch asks again, in case the service lost them.
        let relaunched = DeviceParcels(client: client(calls), storage: storage)
        await relaunched.registerAlerts(token: "token2", locale: .de, environment: .development)
        XCTAssertEqual(calls.all.count, 10)
        XCTAssertFalse(calls.all.contains("unalert 22222222222A token2"))

        // Forgetting a parcel, or letting an account take it over, stops its notifications here.
        await relaunched.forget(id: theirs)
        XCTAssertEqual(calls.all.last, "unalert 33333333333B token2")
        relaunched.release(["22222222222A"])
        for _ in 0..<50 where calls.all.last != "unalert 22222222222A token2" { await Task.yield() }
        XCTAssertEqual(calls.all.last, "unalert 22222222222A token2")
    }

    func testAHeldReturnIsSignedUpForNotificationsAsAParcelOnItsWay() async throws {
        let held = UUID(), returned = UUID()
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        // The carrier still scans the return on its way back: the server keeps checking it and sends its scans.
        var heldReturn = package(held, stage: .returned)
        heldReturn.carrierData.returnHeld = true
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: heldReturn), checkedAt: Date()),
            DeviceParcel(linkID: "22222222222B", label: "", archivedAt: nil, parcel: Parcel(shared: package(returned, stage: .returned)), checkedAt: Date()),
        ]
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: storage)
        XCTAssertTrue(device.hasParcelsOnTheirWay)

        await device.registerAlerts(token: "token1", locale: .fr, environment: .development)
        XCTAssertEqual(calls.all, ["alert 22222222222A k important token1 fr development"])
        XCTAssertEqual(storage.parcels.map(\.alertToken), ["token1", nil])
    }

    func testSomeoneElsesParcelLeavesTheListAtOnceAndItsNotificationsStopWhenTheServiceCanBeTold() async throws {
        let id = UUID()
        let storage = Memory()
        let calls = Calls()
        let shown = PublicParcelResponse(link: reader("33333333333B"), package: package(id))
        let offline = DeviceParcels(client: client(calls, unalert: { _ in throw DeliveryAPIError.serviceFailed(503) }), storage: storage)
        _ = try offline.follow(shown, key: nil, label: "Books for Ana")
        await offline.registerAlerts(token: "token1", locale: .fr, environment: .development)

        // The service can't turn its notifications off yet: the parcel leaves the list all the same,
        // an account can't take it over, and each refresh asks again.
        await offline.forget(id: id)
        XCTAssertTrue(offline.list().isEmpty)
        XCTAssertEqual(offline.claims, [])
        XCTAssertNil(offline.parcelID(linkID: "33333333333B"))
        XCTAssertFalse(offline.hasParcelsOnTheirWay)
        try await offline.refresh(all: true)
        // Followed again meanwhile, it keeps the notifications it still has, and forgetting it asks again.
        XCTAssertTrue(try offline.follow(shown, key: nil, label: "Books for Ana").added)
        await offline.forget(id: id)
        XCTAssertEqual(calls.all, ["alert 33333333333B - important token1 fr development",
                                   "unalert 33333333333B token1", "unalert 33333333333B token1", "unalert 33333333333B token1"])

        // After a restart it is still gone, and the next refresh turns them off for good.
        let relaunched = DeviceParcels(client: client(calls), storage: storage)
        XCTAssertTrue(relaunched.list().isEmpty)
        try await relaunched.refresh()
        try await relaunched.refresh()
        XCTAssertEqual(calls.all.count, 5)
        XCTAssertEqual(calls.all.last, "unalert 33333333333B token1")
        XCTAssertTrue(storage.parcels.isEmpty)
    }

    /// The launch's round works from the parcels listed when it began. Someone else's parcel forgotten before the round
    /// reaches it isn't signed up; one forgotten while it is being signed up is turned off again, and when the service
    /// can't be told, a refresh tells it.
    func testAParcelForgottenDuringTheRoundHearsNothingMore() async throws {
        let first = UUID(), second = UUID()
        let storage = Memory()
        storage.parcels = [(first, "33333333333A"), (second, "33333333333B")].map { id, linkID in
            var entry = DeviceParcel(linkID: linkID, label: "", archivedAt: nil,
                                     parcel: Parcel(shared: package(id, number: "TESTPARCEL\(linkID.suffix(1))")), checkedAt: Date())
            entry.viewer = true
            return entry
        }
        let calls = Calls()
        let gate = AlertGate()
        var gated = client(calls, unalert: { _ in throw DeliveryAPIError.serviceFailed(503) })
        gated.setAlert = { linkID, _, request in
            calls.add("alert \(linkID) \(request.token)")
            if linkID == "33333333333A" { await gate.pass(linkID) }
            return .on
        }
        let device = DeviceParcels(client: gated, storage: storage)
        let round = Task { await device.registerAlerts(token: "token1", locale: .en) }
        await gate.arrived("33333333333A")
        // Both leave the list while the service is asked about the first; neither had notifications yet.
        await device.forget(id: second)
        await device.forget(id: first)
        await gate.release("33333333333A")
        await round.value
        XCTAssertEqual(calls.all, ["alert 33333333333A token1", "unalert 33333333333A token1"])
        XCTAssertTrue(device.list().isEmpty)

        let relaunched = DeviceParcels(client: client(calls), storage: storage)
        XCTAssertTrue(relaunched.list().isEmpty)
        try await relaunched.refresh()
        XCTAssertEqual(calls.all.last, "unalert 33333333333A token1")
        XCTAssertTrue(storage.parcels.isEmpty)
    }

    /// The owner's link taking a reader link's place while the round signs the reader link up: the reader link's
    /// notifications stop, so each scan doesn't arrive twice.
    func testAReaderLinkReplacedByTheOwnersDuringTheRoundHearsNothingMore() async throws {
        let id = UUID()
        let calls = Calls()
        let gate = AlertGate()
        var gated = client(calls)
        gated.setAlert = { linkID, _, request in
            calls.add("alert \(linkID) \(request.token)")
            await gate.pass(linkID)
            return .on
        }
        let device = DeviceParcels(client: gated, storage: Memory())
        _ = try device.follow(PublicParcelResponse(link: reader("33333333333A"), package: package(id)), key: nil, label: nil)
        let round = Task { await device.registerAlerts(token: "token1", locale: .en) }
        await gate.arrived("33333333333A")
        _ = try device.follow(PublicParcelResponse(link: link("22222222222A"), package: package(id)), key: key, label: nil)
        await gate.release("33333333333A")
        await round.value
        XCTAssertEqual(calls.all, ["alert 33333333333A token1", "unalert 33333333333A token1"])
    }

    /// Forgotten and followed again in the same run, a parcel is signed up again.
    func testAParcelFollowedAgainAfterBeingForgottenIsSignedUpAgain() async throws {
        let id = UUID()
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: Memory())
        let shown = PublicParcelResponse(link: reader("33333333333A"), package: package(id))
        _ = try device.follow(shown, key: nil, label: nil)
        await device.registerAlerts(token: "token1", locale: .en, environment: .development)
        await device.forget(id: id)
        _ = try device.follow(shown, key: nil, label: nil)
        await device.registerAlerts(token: "token1", locale: .en, environment: .development)
        XCTAssertEqual(calls.all, ["alert 33333333333A - important token1 en development", "unalert 33333333333A token1",
                                   "alert 33333333333A - important token1 en development"])
    }

    func testARoundOfNotificationRequestsEndsQuietlyWhenTheServiceWantsAPause() async {
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID())), checkedAt: Date()),
            DeviceParcel(linkID: "22222222222B", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID(), number: "TESTPARCEL999")), checkedAt: Date()),
        ]
        let calls = Calls()
        let off = DeviceParcels(client: client(calls, alert: { _ in .unavailable }), storage: storage)
        await off.registerAlerts(token: "token1", locale: .en)
        XCTAssertEqual(calls.all.count, 1, "A server without notifications for iPhones is asked once")
        let busy = DeviceParcels(client: client(calls, alert: { _ in throw DeliveryAPIError.rateLimited(60) }), storage: storage)
        await busy.registerAlerts(token: "token1", locale: .en)
        XCTAssertEqual(calls.all.count, 2)
        XCTAssertEqual(storage.parcels.compactMap(\.alertToken), [])

        // A link with no room, or no longer shared, is left alone until the next launch.
        let full = DeviceParcels(client: client(calls, alert: { $0 == "22222222222A" ? .full : .stopped }), storage: storage)
        await full.registerAlerts(token: "token1", locale: .en)
        await full.registerAlerts(token: "token1", locale: .en)
        XCTAssertEqual(calls.all.count, 4)
        XCTAssertEqual(storage.parcels.compactMap(\.alertToken), [])
    }

    /// A round cut short by an outage or a pause is tried again: a minute later, then twice as long each time it
    /// fails again, never before a pause the service asked for runs out, and the parcels it signed up aren't asked again.
    func testARoundCutShortIsTriedAgainLater() async {
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID())), checkedAt: Date()),
            DeviceParcel(linkID: "22222222222B", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID(), number: "TESTPARCEL999")), checkedAt: Date()),
        ]
        let calls = Calls()
        let start = Date(timeIntervalSince1970: 1_800_000_000)
        var now = start
        let device = DeviceParcels(client: client(calls, alert: { linkID in
            switch calls.all.count {
            case 2: return .unavailable
            case 3: throw DeliveryAPIError.serviceFailed(502)
            case 4: throw DeliveryAPIError.rateLimited(600)
            default: return .on
            }
        }), storage: storage, now: { now })
        func asked() -> [String] { calls.all.map { String($0.split(separator: " ")[1]) } }

        await device.registerAlerts(token: "token1", locale: .en)
        XCTAssertEqual(asked(), ["22222222222A", "22222222222B"])
        for (seconds, expected) in [(59.0, 2), (60, 3), (179, 3), (180, 4), (779, 4), (780, 5), (10_000, 5)] {
            now = start.addingTimeInterval(seconds)
            await device.retryAlerts()
            XCTAssertEqual(calls.all.count, expected, "\(seconds) s after the first round")
        }
        XCTAssertEqual(asked(), ["22222222222A", "22222222222B", "22222222222B", "22222222222B", "22222222222B"])
        XCTAssertEqual(storage.parcels.compactMap(\.alertToken), ["token1", "token1"])
    }

    /// The guest list's refresh is what tries a round cut short again.
    func testTheGuestListsRefreshTriesNotificationsAgainAfterAnOutage() async throws {
        let transport = PostcodeTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        PostcodeTestURLProtocol.handler = { _ in (404, Data()) }
        let shown = PublicParcelResponse(link: reader("33333333333A"), package: package(UUID(), stage: .registered))
        let calls = Calls()
        var now = Date()
        let device = DeviceParcels(client: client(calls, read: { _ in shown }, alert: { _ in
            calls.all.count { $0.hasPrefix("alert") } == 1 ? .unavailable : .on
        }), storage: Memory(), now: { now })
        let notifications = DeviceNotificationAccess(status: { .authorized }, request: {}, register: {}, token: { "token1" })
        let configuration = PostcodeTestURLProtocol.configuration
        let session = SessionStore(configuration: configuration, persistence: PostcodeTestSessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let store = ParcelStore(configuration: configuration, session: session, localizer: Localizer(), transport: transport, device: device,
                                deviceNotifications: notifications)
        store.setDeliveryLiveActivitiesEnabled(false)
        func alerts() -> Int { calls.all.count { $0.hasPrefix("alert") } }

        _ = try await store.follow(ParcelLinkRoute(id: "33333333333A", name: "Books"), shown)
        for _ in 0..<200 where device.alertsRetryAt == nil { await Task.yield() }
        XCTAssertEqual(alerts(), 1)
        XCTAssertEqual(device.alertsRetryAt, now.addingTimeInterval(DeviceParcels.alertRetryFirst))
        await store.load()
        XCTAssertEqual(alerts(), 1, "Not before its time")
        now = now.addingTimeInterval(DeviceParcels.alertRetryFirst)
        await store.load()
        XCTAssertEqual(alerts(), 2)
        await store.load()
        XCTAssertEqual(alerts(), 2, "Once it went through, nothing is asked again")
        XCTAssertNil(device.alertsRetryAt)
    }

    /// The switch and the preset on a guest's Notifications page in Settings.
    func testAGuestsSwitchTurnsEveryParcelOffAndBackOnWithThePresetChosen() async throws {
        let defaults = UserDefaults.standard
        let kept = [NotificationDeviceKeys.deviceAlertsOff, NotificationDeviceKeys.deviceAlertPreset, "sdt.notificationInvitationDismissed.v1.device"]
        for key in kept { defaults.removeObject(forKey: key) }
        defer { for key in kept { defaults.removeObject(forKey: key) } }
        let transport = PostcodeTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        PostcodeTestURLProtocol.handler = { _ in (404, Data()) }
        let books = PublicParcelResponse(link: reader("33333333333A"), package: package(UUID()))
        let shoes = PublicParcelResponse(link: reader("33333333333B"), package: package(UUID(), number: "TESTPARCEL999"))
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: Memory())
        let notifications = DeviceNotificationAccess(status: { .authorized }, request: {}, register: {}, token: { "token1" })
        let configuration = PostcodeTestURLProtocol.configuration
        let session = SessionStore(configuration: configuration, persistence: PostcodeTestSessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let localizer = Localizer()
        let store = ParcelStore(configuration: configuration, session: session, localizer: localizer, transport: transport, device: device,
                                deviceNotifications: notifications)
        store.setDeliveryLiveActivitiesEnabled(false)
        let tail = "token1 \(NativePushLocale(localizer.language).rawValue) \(NativePushEnvironment.current.rawValue)"
        func alerts() -> [String] { calls.all.filter { $0.hasPrefix("alert") || $0.hasPrefix("unalert") } }

        _ = try await store.follow(ParcelLinkRoute(id: "33333333333A", name: "Books"), books)
        for _ in 0..<200 where !device.hasAlertsOn { await Task.yield() }
        XCTAssertEqual(alerts(), ["alert 33333333333A - important \(tail)"])
        XCTAssertTrue(store.notificationsEnabledOnDevice, "Allowed and not turned off: on for the parcels here")

        try await store.disableNotifications()
        XCTAssertFalse(store.notificationsEnabledOnDevice)
        XCTAssertTrue(store.deviceAlertsOff)
        XCTAssertEqual(alerts().last, "unalert 33333333333A token1")
        // Off, a parcel followed next asks nothing.
        _ = try await store.follow(ParcelLinkRoute(id: "33333333333B", name: "Shoes"), shoes)
        for _ in 0..<50 { await Task.yield() }
        XCTAssertEqual(alerts().count, 2)

        let welcomed = try await store.enableNotifications(language: localizer.language)
        XCTAssertTrue(welcomed, "No welcome notification goes out without an account, so none failed to")
        XCTAssertTrue(store.notificationsEnabledOnDevice)
        XCTAssertFalse(store.deviceAlertsOff)
        XCTAssertEqual(Array(alerts().suffix(2)), ["alert 33333333333A - important \(tail)", "alert 33333333333B - important \(tail)"])

        await store.saveDeviceAlertPreset(.delivery)
        XCTAssertEqual(store.deviceAlertPreset, .delivery)
        XCTAssertEqual(Array(alerts().suffix(2)), ["alert 33333333333A - delivery \(tail)", "alert 33333333333B - delivery \(tail)"])
    }

    func testAParcelsNotificationsTellEveryScanUntilItsCarrierHasNews() {
        func preset(_ stage: TrackingStage?) -> ParcelAlertPreset { DeviceParcels.alertPreset(for: Parcel(shared: package(UUID(), stage: stage))) }
        XCTAssertEqual(preset(nil), .all)
        XCTAssertEqual(preset(.pending), .all)
        XCTAssertEqual(preset(.registered), .all)
        XCTAssertEqual(preset(.accepted), .important)
        XCTAssertEqual(preset(.inTransit), .important)
    }

    func testFollowingALinkWithoutAnAccountAsksForNotificationsOnce() async throws {
        let transport = PostcodeTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        PostcodeTestURLProtocol.handler = { _ in (404, Data()) }
        let id = UUID()
        let shown = PublicParcelResponse(link: reader("33333333333A"), package: package(id, stage: .registered))
        let calls = Calls()
        let device = DeviceParcels(client: client(calls, read: { _ in shown }), storage: Memory())
        var status = UNAuthorizationStatus.notDetermined
        var asked: [String] = []
        let notifications = DeviceNotificationAccess(
            status: { asked.append("status"); return status },
            request: { asked.append("request"); status = .authorized },
            register: { asked.append("register") },
            token: { "token1" }
        )
        let configuration = PostcodeTestURLProtocol.configuration
        let session = SessionStore(configuration: configuration, persistence: PostcodeTestSessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let localizer = Localizer()
        let store = ParcelStore(configuration: configuration, session: session, localizer: localizer, transport: transport, device: device,
                                deviceNotifications: notifications)
        store.setDeliveryLiveActivitiesEnabled(false)
        XCTAssertNil(store.followedHere(shown))

        let claim = try await store.follow(ParcelLinkRoute(id: "33333333333A", name: "Books"), shown)

        XCTAssertEqual(claim, .added(id))
        XCTAssertTrue(session.isGuest, "The sign-in screen gives way to the parcels on this iPhone")
        XCTAssertEqual(store.parcels.map(\.label), ["Books"])
        XCTAssertEqual(store.followedHere(shown), id)
        XCTAssertEqual(store.followedHere(linkID: "33333333333A"), id)
        XCTAssertEqual(store.viewerLink(for: try XCTUnwrap(store.parcels.first)), "33333333333A")
        let alert = "alert 33333333333A - all token1 \(NativePushLocale(localizer.language).rawValue) \(NativePushEnvironment.current.rawValue)"
        for _ in 0..<200 where !calls.all.contains(alert) { await Task.yield() }
        XCTAssertEqual(calls.all.filter { $0.hasPrefix("alert") }, [alert])
        XCTAssertEqual(asked, ["status", "request", "status", "register"])
        let again = try await store.follow(ParcelLinkRoute(id: "33333333333A"), shown)
        XCTAssertEqual(again, .already(id))
    }

    /// Two parcels of this iPhone's own, on their way.
    private func twoOwnParcels() -> Memory {
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key]
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID())), checkedAt: Date()),
            DeviceParcel(linkID: "22222222222B", label: "", archivedAt: nil, parcel: Parcel(shared: package(UUID(), number: "TESTPARCEL999")), checkedAt: Date()),
        ]
        return storage
    }

    func testAPresetChosenForEveryParcelAsksAgainForThoseAlreadyOn() async {
        let storage = twoOwnParcels()
        let calls = Calls()
        let device = DeviceParcels(client: client(calls), storage: storage)
        await device.alertsAgain(preset: .delivery)
        XCTAssertEqual(calls.all, [], "Before a first round there is no token to ask on: the round takes the preset")

        await device.registerAlerts(token: "token1", locale: .en, environment: .development)
        await device.alertsAgain(preset: .delivery)
        await device.alertsAgain(preset: .delivery)
        XCTAssertEqual(calls.all, [
            "alert 22222222222A k important token1 en development", "alert 22222222222B k important token1 en development",
            "alert 22222222222A k delivery token1 en development", "alert 22222222222B k delivery token1 en development",
        ])
        XCTAssertEqual(storage.parcels.compactMap(\.alertPreset), [.delivery, .delivery])
        // A new token keeps the choice.
        await device.registerAlerts(token: "token2", locale: .en, environment: .development, preset: .delivery)
        XCTAssertEqual(Array(calls.all.suffix(4)), [
            "alert 22222222222A k delivery token2 en development", "unalert 22222222222A token1",
            "alert 22222222222B k delivery token2 en development", "unalert 22222222222B token1",
        ])
        XCTAssertEqual(storage.parcels.compactMap(\.alertToken), ["token2", "token2"])
    }

    func testTurningEveryParcelOffTellsTheServiceAndNothingSignsThemUpAgainUntilItIsBackOn() async {
        let storage = twoOwnParcels()
        let calls = Calls()
        let failing = Calls()
        let device = DeviceParcels(client: client(calls, unalert: { linkID in
            if linkID == "22222222222A", failing.all.isEmpty {
                failing.add("down")
                throw DeliveryAPIError.serviceFailed(503)
            }
        }), storage: storage)
        await device.registerAlerts(token: "token1", locale: .en, environment: .development)
        XCTAssertTrue(device.hasAlertsOn)

        // The service can't be told about the first link: the call ends there, and its token is kept for the next.
        await device.alertsOff()
        XCTAssertEqual(calls.all.suffix(1), ["unalert 22222222222A token1"])
        XCTAssertEqual(storage.parcels.compactMap(\.alertToken), ["token1", "token1"])
        await device.alertsOff()
        XCTAssertEqual(calls.all.suffix(2), ["unalert 22222222222A token1", "unalert 22222222222B token1"])
        XCTAssertEqual(storage.parcels.compactMap(\.alertToken), [])
        XCTAssertFalse(device.hasAlertsOn)

        let before = calls.all.count
        await device.alertsAgain(preset: .all)
        await device.retryAlerts()
        XCTAssertEqual(calls.all.count, before, "Off, nothing signs a parcel up again")
        await device.registerAlerts(token: "token1", locale: .en, environment: .development)
        XCTAssertEqual(calls.all.suffix(2), ["alert 22222222222A k important token1 en development", "alert 22222222222B k important token1 en development"])
    }

    func testABellTurnsOneParcelOffUntilItIsTurnedOnThereAgain() async throws {
        let storage = twoOwnParcels()
        let calls = Calls()
        let offline = Calls()
        let device = DeviceParcels(client: client(calls, unalert: { _ in
            if offline.all.isEmpty { return }
            throw DeliveryAPIError.serviceFailed(503)
        }), storage: storage)
        let first = try XCTUnwrap(device.list().first?.id)
        await device.registerAlerts(token: "token1", locale: .en, environment: .development)

        let muted = try await device.setMuted(id: first, muted: true)
        XCTAssertTrue(muted.notificationsMuted)
        XCTAssertEqual(device.list().map(\.notificationsMuted), [true, false])
        XCTAssertEqual(calls.all.last, "unalert 22222222222A token1")
        XCTAssertEqual(storage.parcels.map(\.alertToken), [nil, "token1"])

        // A new language asks again for the other parcel only.
        await device.registerAlerts(token: "token1", locale: .fr, environment: .development)
        XCTAssertEqual(calls.all.last, "alert 22222222222B k important token1 fr development")

        let unmuted = try await device.setMuted(id: first, muted: false)
        XCTAssertFalse(unmuted.notificationsMuted)
        XCTAssertEqual(calls.all.last, "alert 22222222222A k important token1 fr development")
        XCTAssertEqual(storage.parcels.map(\.alertToken), ["token1", "token1"])

        // Turned off while the service can't be told: it is told by the next refresh.
        offline.add("down")
        _ = try await device.setMuted(id: first, muted: true)
        XCTAssertEqual(storage.parcels.map(\.alertToken), ["token1", "token1"])
        XCTAssertEqual(storage.parcels.map(\.muted), [true, nil])
        let tries = calls.all.count { $0 == "unalert 22222222222A token1" }
        let back = DeviceParcels(client: client(calls), storage: storage)
        try await back.refresh()
        XCTAssertEqual(calls.all.count { $0 == "unalert 22222222222A token1" }, tries + 1)
        XCTAssertEqual(storage.parcels.map(\.alertToken), [nil, "token1"])
        XCTAssertEqual(back.list().map(\.notificationsMuted), [true, false], "Kept across launches")
    }

    func testTheSampleJourneyAddsOneScanAtATime() {
        let now = Date(timeIntervalSince1970: 1_800_000_000)
        let stages = (1...FirstOpenJourney.steps).map { FirstOpenJourney.parcel(step: $0, name: "New sneakers", now: now).currentStage }
        XCTAssertEqual(stages, [.accepted, .inTransit, .inTransit, .outForDelivery, .delivered])
        let first = FirstOpenJourney.parcel(step: 1, name: "New sneakers", now: now)
        let last = FirstOpenJourney.parcel(step: 9, name: "New sneakers", now: now)
        XCTAssertEqual(first.id, last.id, "The same parcel all along, so its map flies rather than starts over")
        XCTAssertEqual(last.trackingEvents.count, FirstOpenJourney.steps)
        XCTAssertEqual(last.stampOrigin, "CN")
    }

    func testTheSampleOpensOnItsWholeStory() {
        let localizer = Localizer()
        let delivered = FirstOpenJourney.delivered(localizer: localizer, now: Date(timeIntervalSince1970: 1_800_000_000))
        // The page a tap opens shows the same parcel the picture follows, picked up.
        XCTAssertEqual(delivered.id, FirstOpenJourney.parcel(step: 1, name: "", now: Date()).id)
        XCTAssertEqual(delivered.id, FirstOpenJourney.parcelID)
        XCTAssertEqual(delivered.currentStage, .delivered)
        XCTAssertEqual(delivered.trackingNumber, "123EXAMPLE")
        XCTAssertEqual(PickupPoint(delivered.carrierData?.pickupPoint)?.name, "Kiosk im Hauptbahnhof")
        XCTAssertNotNil(delivered.carrierData?.senderName)
        XCTAssertEqual(delivered.stampOrigin, "CN")
        // Every scan has its own words, and every placed one a country the history flags.
        for event in delivered.trackingEvents {
            XCTAssertFalse(event.description.isEmpty)
            XCTAssertFalse(event.description.hasPrefix("sample."), event.description)
            if let location = event.location { XCTAssertNotNil(TrackingLocation.place(location).country, location) }
        }
        XCTAssertEqual(Set(delivered.trackingEvents.compactMap(\.location).compactMap { TrackingLocation.place($0).country }), ["CN", "DE", "CH"])
    }
}

private final class PostcodeTestSessionPersistence: SessionPersistence {
    var data: Data?
    func save<T: Encodable>(_ value: T) throws { data = try JSONEncoder.deliveryTracker.encode(value) }
    func load<T: Decodable>() -> T? { data.flatMap { try? JSONDecoder.deliveryTracker.decode(T.self, from: $0) } }
    func delete() { data = nil }
}

private final class PostcodeTestURLProtocol: URLProtocol {
    nonisolated(unsafe) static var handler: ((URLRequest) -> (Int, Data))?
    /// True for a request that gets no answer, which only cancelling it ends.
    nonisolated(unsafe) static var held: ((URLRequest) -> Bool)?

    @MainActor static var configuration: AppConfiguration {
        AppConfiguration(mode: .api, apiBaseURL: URL(string: "https://postcodes.test")!, supabaseURL: URL(string: "https://postcodes.test")!,
                         supabasePublishableKey: "public", googleAuthEnabled: false, appleAuthEnabled: false, emailOTPEnabled: true,
                         appGroupIdentifier: "postcodes.test")
    }

    static func transport() -> URLSession {
        let configuration = URLSessionConfiguration.ephemeral
        configuration.protocolClasses = [PostcodeTestURLProtocol.self]
        return URLSession(configuration: configuration)
    }

    /// One transport for tests that build several stores on it, as a relaunch does.
    static let shared = transport()

    /// A request's body reaches a URL protocol as a stream.
    static func body(of request: URLRequest) -> Data {
        var data = request.httpBody ?? Data()
        guard let stream = request.httpBodyStream else { return data }
        stream.open()
        defer { stream.close() }
        var bytes = [UInt8](repeating: 0, count: 1024)
        while stream.hasBytesAvailable {
            let count = stream.read(&bytes, maxLength: bytes.count)
            if count <= 0 { break }
            data.append(contentsOf: bytes.prefix(count))
        }
        return data
    }

    override class func canInit(with request: URLRequest) -> Bool { request.url?.host == "postcodes.test" }
    override class func canonicalRequest(for request: URLRequest) -> URLRequest { request }
    override func startLoading() {
        if Self.held?(request) == true { return }
        let (status, data) = Self.handler?(request) ?? (500, Data())
        client?.urlProtocol(self, didReceive: HTTPURLResponse(url: request.url!, statusCode: status, httpVersion: nil,
                                                              headerFields: ["Content-Type": "application/json"])!, cacheStoragePolicy: .notAllowed)
        client?.urlProtocol(self, didLoad: data)
        client?.urlProtocolDidFinishLoading(self)
    }
    override func stopLoading() {}
}

/// XCTAssertThrowsError for an expression that awaits.
@MainActor
private func XCTAssertThrowsErrorAsync<T>(_ expression: @autoclosure () async throws -> T, file: StaticString = #filePath, line: UInt = #line) async {
    do { _ = try await expression(); XCTFail("No error was thrown", file: file, line: line) } catch {}
}

/// Holds the first notification request on each token until it is released; later ones go through.
private actor AlertGate {
    private var seen: Set<String> = []
    private var held: [String: CheckedContinuation<Void, Never>] = [:]
    private var arrivals: [String: [CheckedContinuation<Void, Never>]] = [:]

    func pass(_ token: String) async {
        guard seen.insert(token).inserted else { return }
        arrivals.removeValue(forKey: token)?.forEach { $0.resume() }
        await withCheckedContinuation { held[token] = $0 }
    }

    /// Returns once the first request on the token is held.
    func arrived(_ token: String) async {
        guard !seen.contains(token) else { return }
        await withCheckedContinuation { arrivals[token, default: []].append($0) }
    }

    func release(_ token: String) { held.removeValue(forKey: token)?.resume() }
}
