import UserNotifications
import XCTest
@testable import PeekDeliveryTracker

/// Parcels followed on the iPhone without an account.
@MainActor
final class DeviceParcelTests: XCTestCase {
    private final class Memory: DeviceParcelStorage {
        var parcels: [DeviceParcel] = []
        var keys: [String: String] = [:]
        func load() -> (parcels: [DeviceParcel], keys: [String: String]) { (parcels, keys) }
        func save(_ parcels: [DeviceParcel], keys: [String: String]) { self.parcels = parcels; self.keys = keys }
    }

    /// What the service was asked, in order.
    private final class Calls: @unchecked Sendable {
        private let lock = NSLock()
        private var log: [String] = []
        private var countryHints: [String?] = []
        private var postcodes: [String?] = []
        func add(_ call: String) { lock.withLock { log.append(call) } }
        func lookupCountry(_ hint: String?) { lock.withLock { countryHints.append(hint) } }
        func lookupPostcode(_ postcode: String?) { lock.withLock { postcodes.append(postcode) } }
        var all: [String] { lock.withLock { log } }
        var lookupCountries: [String?] { lock.withLock { countryHints } }
        var lookupPostcodes: [String?] { lock.withLock { postcodes } }
    }

    private let key = String(repeating: "k", count: 43)

    private func given(_ carrier: CarrierID, _ postcode: String) -> GivenPostcode { GivenPostcode(carrier: carrier, postcode: postcode) }

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
                        unalert: @escaping @Sendable (String) throws -> Void = { _ in }) -> DeviceParcelClient {
        DeviceParcelClient(
            lookup: { request in
                calls.add("lookup \(request.trackingNumber) \(request.carrier?.rawValue ?? "-")")
                calls.lookupCountry(request.lookupCountryHint)
                calls.lookupPostcode(request.dpdPostcode)
                guard let lookup else { throw DeviceLookupsSpent() }
                return lookup
            },
            read: { linkID, key in calls.add("read \(linkID) \((key ?? "-").prefix(1))"); return try read(linkID) },
            forget: { linkID, _ in calls.add("forget \(linkID)") },
            update: { linkID, _, change in
                calls.add("update \(linkID) shared=\(change.shared.map(String.init) ?? "-")")
                return PublicParcelResponse(link: ParcelLink(id: linkID, role: .owner, kind: .lookup, createdAt: "2026-10-01T08:00:00Z",
                                                             numberShown: true, canKeep: true, showNumber: change.showNumber ?? false,
                                                             gift: change.gift ?? false, shared: change.shared ?? true),
                                            package: PublicPackage(id: UUID(), label: "", carrier: .dhl, createdAt: "2026-10-01T08:00:00Z", syncStatus: .ok,
                                                                   carrierData: PublicPackageCarrierData(), notificationsMuted: false, trackingEvents: []))
            },
            detect: { number, prefixOfSaved in
                calls.add("detect \(number)\(prefixOfSaved ? " prefix" : "")")
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
        XCTAssertEqual(calls.all, ["lookup TESTPARCEL123789 dhl"])
    }

    func testChangingCarrierSendsTheDeviceCountryAndKeepsTheParcelName() async throws {
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
        XCTAssertEqual(parcel.carrier, .dpd)
        XCTAssertEqual(calls.lookupCountries, [ParcelLookupCountry.hint()])
        XCTAssertEqual(calls.all, ["lookup TESTPARCEL123789 dpd", "forget 22222222222A"])
        XCTAssertEqual(storage.parcels.map(\.replaced), [previousID])
        XCTAssertNil(storage.keys["22222222222A"])
    }

    func testANewPostcodeForTheSameCarrierIsALookupThatTakesTheParcelsPlace() async throws {
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
        let device = DeviceParcels(client: client(calls, lookup: PublicLookupResponse(
            link: link("22222222222B"), key: key, package: package(nextID, carrier: .dpd)
        )), storage: storage, postcodes: PostcodeMemory(defaults: defaults))

        let parcel = try await device.changeCarrier(id: previousID, carrier: .dpd, trackingURL: nil, dpdPostcode: "75002")

        XCTAssertEqual(parcel.id, nextID)
        XCTAssertEqual(parcel.label, "Moon lamp")
        XCTAssertEqual(parcel.carrierData?.originalPackageID, previousID)
        XCTAssertEqual(calls.all, ["lookup TESTPARCEL123789 dpd", "forget 22222222222A"])
        XCTAssertEqual(calls.lookupPostcodes, ["75002"])
        XCTAssertEqual(device.list().map(\.id), [nextID])
        XCTAssertEqual(storage.parcels.map(\.replaced), [previousID])
        XCTAssertEqual(storage.keys, ["22222222222B": key])
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
        // The service finds the parcel it already has and gives it one more link.
        let device = DeviceParcels(client: client(calls, lookup: PublicLookupResponse(
            link: link("22222222222B"), key: key, package: package(id, carrier: .dpd, stage: .outForDelivery)
        )), storage: storage, postcodes: PostcodeMemory(defaults: defaults))

        let parcel = try await device.changeCarrier(id: id, carrier: .dpd, trackingURL: nil, dpdPostcode: "75001")

        XCTAssertEqual(parcel.id, id)
        XCTAssertEqual(parcel.label, "Moon lamp")
        XCTAssertEqual(parcel.archivedAt, "2026-10-03T08:00:00Z")
        XCTAssertEqual(parcel.carrierData?.originalPackageID, earlierID)
        XCTAssertEqual(parcel.currentStage, .outForDelivery)
        XCTAssertEqual(device.list(), [parcel])
        XCTAssertEqual(storage.parcels.map(\.linkID), ["22222222222B"])
        XCTAssertEqual(storage.keys, ["22222222222B": key])
        for _ in 0..<500 where !calls.all.contains("forget 22222222222A") { try await Task.sleep(for: .milliseconds(10)) }
        XCTAssertEqual(calls.all, ["lookup TESTPARCEL123789 dpd", "forget 22222222222A"])
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

    func testOnlyStaleParcelsAreReadAgainAndAForgottenOneLeaves() async throws {
        let moving = UUID(), arrived = UUID(), gone = UUID()
        var now = Date(timeIntervalSince1970: 1_800_000_000)
        let storage = Memory()
        storage.keys = ["22222222222A": key, "22222222222B": key, "22222222222C": key]
        storage.parcels = [
            DeviceParcel(linkID: "22222222222A", label: "Moving", archivedAt: nil, parcel: Parcel(shared: package(moving)), checkedAt: now),
            DeviceParcel(linkID: "22222222222B", label: "Arrived", archivedAt: nil, parcel: Parcel(shared: package(arrived, stage: .delivered)), checkedAt: now),
            DeviceParcel(linkID: "22222222222C", label: "Gone", archivedAt: nil, parcel: Parcel(shared: package(gone)), checkedAt: now),
        ]
        let calls = Calls()
        let answers: [String: PublicParcelResponse] = [
            "22222222222A": PublicParcelResponse(link: link("22222222222A"), package: package(moving, stage: .outForDelivery)),
        ]
        let device = DeviceParcels(client: client(calls, read: { answers[$0] }), storage: storage, now: { now })

        try await device.refresh()
        XCTAssertEqual(calls.all, [], "Fresh answers are not asked for again")

        now += DeviceParcels.fresh + 1
        try await device.refresh()
        // A parcel that arrived rests for hours; the one the service forgot leaves the list.
        XCTAssertEqual(calls.all, ["read 22222222222A k", "read 22222222222C k"])
        XCTAssertEqual(device.list().map(\.label), ["Moving", "Arrived"])
        XCTAssertEqual(device.list().first?.currentStage, .outForDelivery)
        XCTAssertNil(storage.keys["22222222222C"])
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

        try await device.forget(id: id)
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
        let device = DeviceParcels(client: client(calls, lookup: found), storage: storage, postcodes: memory)

        let parcel = try await device.add(CreatePackageRequest(trackingNumber: "TESTPARCEL123789", carrier: .dpd, dpdPostcode: "75001 "))

        XCTAssertEqual(calls.lookupPostcodes, ["75001 "])
        XCTAssertEqual(memory.entries, [given(.dpd, "75001")])
        XCTAssertNil(parcel.dpdPostcode)
        XCTAssertNil(storage.parcels.first?.parcel.dpdPostcode)

        // A carrier changed is a new lookup, and its postcode comes first.
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

        try await device.forget(id: first)
        XCTAssertEqual(memory.entries, [given(.dpd, "75001"), given(.glsCh, "8000")])
        try await device.forgetAll()
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
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)

        try await store.add(trackingNumber: "TESTPARCEL123789", label: "", carrier: .dpd, trackingURL: nil, dpdPostcode: " 75001 ")
        XCTAssertEqual(memory.entries, [given(.dpd, "75001")])
        XCTAssertEqual(store.givenPostcodes, [given(.dpd, "75001")])

        // An account's postcode stays on its parcel, and the account's parcels come first.
        try await session.verifyCode(email: "test@example.com", code: "123456")
        try await store.add(trackingNumber: "TESTPARCEL999", label: "", carrier: .glsCh, trackingURL: nil, dpdPostcode: "3000")
        XCTAssertEqual(memory.entries, [given(.dpd, "75001")])
        XCTAssertEqual(store.givenPostcodes, [given(.glsCh, "3000"), given(.dpd, "75001")])

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

    func testAFileWithAPostcodeOnItsParcelsStillOpens() throws {
        let saved = DeviceParcel(linkID: "22222222222A", label: "Moon lamp", archivedAt: nil, parcel: Parcel(shared: package(UUID())),
                                 checkedAt: Date(timeIntervalSince1970: 1_800_000_000))
        var file = try XCTUnwrap(JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode([saved])) as? [[String: Any]])
        file[0]["postcode"] = "75001"

        let parcels = try JSONDecoder.deliveryTracker.decode([DeviceParcel].self, from: JSONSerialization.data(withJSONObject: file))

        XCTAssertEqual(parcels, [saved])
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
        try await again.forget(id: id)
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
        XCTAssertEqual(loaded.keys, ["22222222222A": key], "A key beside someone else's parcel, or beside none, opens nothing")
        XCTAssertEqual(DeviceParcelFiles.matched([followed], keys: [:]).parcels, [followed])

        // A file written before parcels could be followed this way still opens, every parcel its own.
        let file = try JSONEncoder.deliveryTracker.encode([own])
        XCTAssertFalse(String(decoding: file, as: UTF8.self).contains("viewer"))
        XCTAssertEqual(try JSONDecoder.deliveryTracker.decode([DeviceParcel].self, from: file).map(\.isViewer), [false])
        let saved = try JSONDecoder.deliveryTracker.decode([DeviceParcel].self, from: JSONEncoder.deliveryTracker.encode([followed]))
        XCTAssertEqual(saved, [followed])
    }

    func testAFileThatCannotBeReadKeepsItsKeysUntilTheirLinksAreForgotten() async throws {
        let unreadable = DeviceParcelFiles.opened(Data(#"[{"linkID":42}]"#.utf8), keys: ["22222222222C": key])
        XCTAssertTrue(unreadable.parcels.isEmpty)
        XCTAssertEqual(unreadable.keys, ["22222222222C": key])
        XCTAssertEqual(DeviceParcelFiles.opened(nil, keys: ["22222222222C": key]).keys, [:], "No file, no parcels to forget")

        // Offline, the key stays for the next try.
        let storage = Memory()
        storage.keys = unreadable.keys
        let calls = Calls()
        var offline = client(calls)
        offline.forget = { linkID, _ in calls.add("forget \(linkID)"); throw URLError(.notConnectedToInternet) }
        try await DeviceParcels(client: offline, storage: storage).refresh()
        XCTAssertEqual(storage.keys, ["22222222222C": key])

        try await DeviceParcels(client: client(calls), storage: storage).refresh()
        XCTAssertEqual(calls.all, ["forget 22222222222C", "forget 22222222222C"])
        XCTAssertTrue(storage.keys.isEmpty)
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
        try await device.forget(id: other)
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
        try await relaunched.forget(id: theirs)
        XCTAssertEqual(calls.all.last, "unalert 33333333333B token2")
        relaunched.release(["22222222222A"])
        for _ in 0..<50 where calls.all.last != "unalert 22222222222A token2" { await Task.yield() }
        XCTAssertEqual(calls.all.last, "unalert 22222222222A token2")
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
        try await offline.forget(id: id)
        XCTAssertTrue(offline.list().isEmpty)
        XCTAssertEqual(offline.claims, [])
        XCTAssertNil(offline.parcelID(linkID: "33333333333B"))
        XCTAssertFalse(offline.hasParcelsOnTheirWay)
        try await offline.refresh(all: true)
        // Followed again meanwhile, it keeps the notifications it still has, and forgetting it asks again.
        XCTAssertTrue(try offline.follow(shown, key: nil, label: "Books for Ana").added)
        try await offline.forget(id: id)
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
        store.setDeliveryWidgetEnabled(false)
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

    override class func canInit(with request: URLRequest) -> Bool { request.url?.host == "postcodes.test" }
    override class func canonicalRequest(for request: URLRequest) -> URLRequest { request }
    override func startLoading() {
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
