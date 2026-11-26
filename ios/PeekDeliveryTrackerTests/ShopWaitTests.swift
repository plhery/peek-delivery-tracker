import XCTest
@testable import PeekDeliveryTracker

/// The waits for shops to ship: what the service answers, what a card says, the demo's wait, and the list's reads and
/// removals, as the web's tests (src/components/shopLink/ShopLink.test.tsx) have them.
@MainActor
final class ShopWaitTests: XCTestCase {
    private var suite = ""
    private var defaults: UserDefaults!
    private var english: Localizer!
    private var originalLanguage: AppLanguage = .en

    override func setUp() async throws {
        suite = "shop-wait-tests-\(UUID().uuidString)"
        defaults = UserDefaults(suiteName: suite)
        english = Localizer(defaults: defaults, systemUses24HourTime: { true })
        originalLanguage = english.language
        english.language = .en
    }

    override func tearDown() async throws {
        english.language = originalLanguage
        defaults.removePersistentDomain(forName: suite)
        ShopWaitTestURLProtocol.handler = nil
    }

    private static let first = UUID(uuidString: "0A9F3C52-6B1D-4E8A-9C77-2F5B8D1E4A60")!
    private static let second = UUID(uuidString: "7E21B4D0-3C5A-4F19-8B62-91D0C3A7E5F4")!

    private static func json(_ id: UUID, shop: String? = "H&M", label: String = "Wool coat", expiredAt: String? = nil,
                             platform: String = "parcellab") -> String {
        let shopName = shop.map { "\"\($0)\"" } ?? "null"
        let expired = expiredAt.map { "\"\($0)\"" } ?? "null"
        return """
        {"id":"\(id.uuidString.lowercased())","platform":"\(platform)","platform_name":"parcelLab","shop_name":\(shopName),\
        "label":"\(label)","created_at":"2026-11-25T09:00:00.000Z","expires_at":"2026-12-09T12:00:00.000Z","expired_at":\(expired)}
        """
    }

    private static func answer(_ waits: [String]) -> Data {
        Data(#"{"shop_waits":[\#(waits.joined(separator: ","))]}"#.utf8)
    }

    private static func wait(_ id: UUID, shop: String? = "H&M", label: String = "Wool coat", expiredAt: String? = nil) -> ShopWait {
        ShopWait(id: id, platform: .parcellab, platformName: "parcelLab", shopName: shop, label: label,
                 createdAt: "2026-11-25T09:00:00.000Z", expiresAt: "2026-12-09T12:00:00.000Z", expiredAt: expiredAt)
    }

    // MARK: - The service's answer

    func testReadsTheServicesWaitsAndDropsOneThisAppCantRead() throws {
        let waits = try ShopWaitsOffer.decode(Self.answer([
            Self.json(Self.first),
            Self.json(Self.second, shop: nil, label: "", expiredAt: "2026-12-09T12:00:00.000Z"),
            // A newer server's platform: the list keeps the others.
            Self.json(UUID(), platform: "narvar"),
        ]))
        XCTAssertEqual(waits, [Self.wait(Self.first), Self.wait(Self.second, shop: nil, label: "", expiredAt: "2026-12-09T12:00:00.000Z")])
        XCTAssertFalse(waits[0].expired)
        XCTAssertTrue(waits[1].expired)
        // The generated contract reads the same answer the same way.
        let generated = try JSONDecoder.deliveryTracker.decode(ShopWaitsResponse.self, from: Self.answer([Self.json(Self.first)]))
        XCTAssertEqual(generated.shopWaits, [Self.wait(Self.first)])
        XCTAssertThrowsError(try ShopWaitsOffer.decode(Data(#"{"waits":[]}"#.utf8)))
    }

    // MARK: - The card

    func testTheCardSaysWhoShipsWhatAndUntilWhenPeekAsks() {
        let now = DateParser.date("2026-11-26T12:00:00Z")!
        let open = ShopWaitCardText(Self.wait(Self.first), localizer: english, now: now)
        let until = english.shortDate(DateParser.date("2026-12-09T12:00:00Z")!, now: now)
        XCTAssertEqual(Self.lines(open), ["H&M via parcelLab", "Wool coat", "Waiting for H&M to ship",
                                          "Peek checks every few hours until \(until)", "Stop waiting"])
        XCTAssertFalse(open.expired)
        XCTAssertEqual(open.stateSymbol, "hourglass")
        // Noon UTC on Wednesday 9 December is that Wednesday on every clock from UTC−11 to UTC+11.
        if abs(TimeZone.current.secondsFromGMT()) < 12 * 3_600 {
            XCTAssertEqual(open.hint, "Peek checks every few hours until Wed 9 Dec")
        }

        let expired = ShopWaitCardText(Self.wait(Self.first, expiredAt: "2026-12-09T12:00:00Z"), localizer: english, now: now)
        XCTAssertEqual(Self.lines(expired), ["H&M via parcelLab", "Wool coat", "H&M named no parcel in 14 days",
                                             "Peek stopped checking. Add the tracking number when the shop sends it.", "Remove"])
        XCTAssertTrue(expired.expired)
        XCTAssertEqual(expired.stateSymbol, "clock")

        // No shop in the page's address, and no name given: the card is named after the platform.
        let unnamed = ShopWaitCardText(Self.wait(Self.first, shop: nil, label: ""), localizer: english, now: now)
        XCTAssertEqual([unnamed.via, unnamed.title, unnamed.state], ["Via parcelLab", "parcelLab", "Waiting for the shop to ship"])
        let unnamedExpired = ShopWaitCardText(Self.wait(Self.first, shop: nil, expiredAt: "2026-12-09T12:00:00Z"), localizer: english, now: now)
        XCTAssertEqual(unnamedExpired.state, "The shop named no parcel in 14 days")
        // No name given, a shop: the shop's name.
        XCTAssertEqual(ShopWaitCardText(Self.wait(Self.first, label: ""), localizer: english, now: now).title, "H&M")

        // In another language the placeholders are filled in all the same.
        english.language = .de
        let german = ShopWaitCardText(Self.wait(Self.first), localizer: english, now: now)
        XCTAssertEqual([german.via, german.state, german.action], ["H&M über parcelLab", "Warten, bis H&M verschickt", "Nicht mehr warten"])
        for language in AppLanguage.allCases {
            english.language = language
            for wait in [Self.wait(Self.first), Self.wait(Self.second, shop: nil, label: "", expiredAt: "2026-12-09T12:00:00Z")] {
                let text = ShopWaitCardText(wait, localizer: english, now: now)
                for line in [text.via, text.title, text.state, text.hint ?? "", text.action] {
                    XCTAssertFalse(line.contains("{{") || line.contains("shopWait."), "\(language): \(line)")
                }
            }
        }
    }

    private static func lines(_ text: ShopWaitCardText) -> [String?] { [text.via, text.title, text.state, text.hint, text.action] }

    // MARK: - The demo

    func testTheDemoWaitsForACoatAndAGuestForNothing() async throws {
        let now = DateParser.date("2026-11-26T12:00:00Z")!
        let demo = ShopWait.demo(now: now)
        XCTAssertEqual(demo.shopName, "H&M")
        XCTAssertEqual(demo.platform, .parcellab)
        XCTAssertEqual(demo.platformName, "parcelLab")
        XCTAssertEqual(demo.label, "Wool coat")
        XCTAssertEqual(DateParser.date(demo.createdAt), now.addingTimeInterval(-86_400))
        XCTAssertEqual(DateParser.date(demo.expiresAt), now.addingTimeInterval(13 * 86_400))
        XCTAssertNil(demo.expiredAt)
        XCTAssertEqual(ShopWaitList.opening(demo: true, now: now, defaults: defaults).waits, [demo])
        XCTAssertEqual(ShopWaitList.opening(demo: false, now: now, defaults: defaults).waits, [])
        // The App Store pictures show the demo's parcels alone.
        defaults.set(true, forKey: "sdt.debug.storeScreenshots")
        XCTAssertEqual(ShopWaitList.opening(demo: true, now: now, defaults: defaults).waits, [])
        defaults.removeObject(forKey: "sdt.debug.storeScreenshots")

        let transport = ShopWaitTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        nonisolated(unsafe) var requests = 0
        ShopWaitTestURLProtocol.handler = { _ in requests += 1; return (500, Data()) }
        let configuration = AppConfiguration(mode: .demo, apiBaseURL: URL(string: "https://shop-waits.test")!, supabaseURL: nil,
                                             supabasePublishableKey: "", googleAuthEnabled: false, appleAuthEnabled: false,
                                             emailOTPEnabled: false, appGroupIdentifier: "shop-waits.test")
        let session = SessionStore(configuration: configuration, defaults: defaults, persistence: ShopWaitTestSessionPersistence(), transport: transport)
        let store = ParcelStore(configuration: configuration, session: session, localizer: english, transport: transport,
                                device: DeviceParcels(client: nil, storage: ShopWaitTestStorage()), deviceNotifications: Self.quietNotifications)
        store.setDeliveryLiveActivitiesEnabled(false)
        session.enterDemo()
        let shown = try XCTUnwrap(store.shopWaits.waits.first)
        XCTAssertEqual(store.shopWaits.waits.count, 1)
        XCTAssertEqual(shown.id, ShopWait.demoID)
        XCTAssertEqual([shown.label, shown.shopName], ["Wool coat", "H&M"])
        XCTAssertFalse(store.parcels.contains { $0.id == shown.id }, "The wait is no parcel: the demo's parcels stay as they were")
        // Stopping removes it at once and asks nothing.
        await store.removeShopWait(shown.id)
        XCTAssertEqual(store.shopWaits.waits, [])
        await store.loadShopWaits()
        XCTAssertEqual(store.shopWaits.waits, [])

        // Without an account, the device waits for no shop and asks nothing.
        session.showWelcome()
        XCTAssertTrue(store.isGuest)
        XCTAssertEqual(store.shopWaits.waits, [])
        await store.loadShopWaits()
        XCTAssertEqual(store.shopWaits.waits, [])
        XCTAssertEqual(requests, 0)
        // Starting the demo again brings its wait back.
        session.enterDemo()
        XCTAssertEqual(store.shopWaits.waits.map(\.id), [ShopWait.demoID])
    }

    // MARK: - Reads and removals

    func testRemovingTakesTheWaitOffAtOnceAndAFailurePutsItBackWhereItStood() {
        var list = ShopWaitList()
        XCTAssertFalse(list.read([Self.wait(Self.first), Self.wait(Self.second)]), "The first read finds nothing gone")
        XCTAssertTrue(list.remove(Self.first))
        XCTAssertEqual(list.waits.map(\.id), [Self.second])
        XCTAssertFalse(list.remove(Self.first), "It is off the list already")
        // A read that began before the removal brings it back no more than one after it.
        XCTAssertFalse(list.read([Self.wait(Self.first), Self.wait(Self.second)]))
        XCTAssertEqual(list.waits.map(\.id), [Self.second])
        // The service couldn't be told: it comes back where it stood, and a read shows it again.
        list.restore(Self.first)
        XCTAssertEqual(list.waits.map(\.id), [Self.first, Self.second])
        list.restore(Self.first)
        XCTAssertEqual(list.waits.map(\.id), [Self.first, Self.second], "Restored once")
        XCTAssertFalse(list.read([Self.wait(Self.first), Self.wait(Self.second)]))
        XCTAssertEqual(list.waits.map(\.id), [Self.first, Self.second])
    }

    func testAWaitGoneWithoutTheReaderRemovingItBecameParcels() {
        var list = ShopWaitList()
        XCTAssertFalse(list.read([Self.wait(Self.first), Self.wait(Self.second)]))
        // The reader stopped waiting for one: its going says nothing.
        XCTAssertTrue(list.remove(Self.second))
        XCTAssertFalse(list.read([Self.wait(Self.first)]))
        // The other went by itself: the shop shipped.
        XCTAssertTrue(list.read([]))
        XCTAssertFalse(list.read([]), "Once")
        XCTAssertEqual(list.waits, [])
        // One that expired stays, for the reader to remove.
        XCTAssertFalse(list.read([Self.wait(Self.first, expiredAt: "2026-12-09T12:00:00Z")]))
        XCTAssertEqual(list.waits.map(\.expired), [true])
    }

    func testTheListAsksAgainEveryTenMinutesWhileItStaysOpen() {
        var list = ShopWaitList()
        let now = Date()
        XCTAssertTrue(list.due(now: now))
        list.asking(at: now)
        XCTAssertFalse(list.due(now: now.addingTimeInterval(ShopWaitList.readEvery - 1)))
        XCTAssertTrue(list.due(now: now.addingTimeInterval(ShopWaitList.readEvery)))
    }

    func testTheStoreReadsRemovesAndReadsTheParcelsOnceAWaitBecameThem() async throws {
        let transport = ShopWaitTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        let configuration = ShopWaitTestURLProtocol.configuration
        let user = AuthUser(id: UUID(), email: "test@example.test", isAnonymous: false)
        let signedIn = try JSONEncoder.deliveryTracker.encode(AuthSession(
            accessToken: "access", tokenType: "bearer", expiresIn: 3600,
            expiresAt: Int(Date().timeIntervalSince1970) + 3600, refreshToken: "refresh", user: user
        ))
        nonisolated(unsafe) var listed = [Self.json(Self.first), Self.json(Self.second)]
        nonisolated(unsafe) var removal = 404
        nonisolated(unsafe) var requests: [(method: String, path: String, authorization: String?)] = []
        ShopWaitTestURLProtocol.handler = { request in
            let path = request.url?.path ?? ""
            if path == "/auth/v1/verify" { return (200, signedIn) }
            let method = request.httpMethod ?? "GET"
            requests.append((method, path, request.value(forHTTPHeaderField: "Authorization")))
            switch (method, path) {
            case ("GET", "/api/shop-waits"): return (200, Self.answer(listed))
            case ("DELETE", _) where path.hasPrefix("/api/shop-waits/"):
                return (removal, Data(removal == 404 ? #"{"error":"Shop wait not found"}"#.utf8 : #"{"error":"Unavailable"}"#.utf8))
            case ("GET", "/api/packages"): return (200, Data(#"{"packages":[]}"#.utf8))
            default: return (404, Data(#"{"error":"Not found"}"#.utf8))
            }
        }
        let session = SessionStore(configuration: configuration, defaults: defaults, persistence: ShopWaitTestSessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let store = ParcelStore(configuration: configuration, session: session, localizer: english, transport: transport,
                                device: DeviceParcels(client: .api(configuration: configuration, transport: transport), storage: ShopWaitTestStorage()),
                                deviceNotifications: Self.quietNotifications)
        store.setDeliveryLiveActivitiesEnabled(false)
        // Signed out, there are none to ask for.
        await store.loadShopWaits()
        XCTAssertTrue(requests.isEmpty)

        try await session.verifyCode(email: "test@example.test", code: "123456")
        XCTAssertEqual(store.shopWaits.waits, [], "An account opens on none until the service is asked")
        await store.loadShopWaits()
        XCTAssertEqual(store.shopWaits.waits.map(\.id), [Self.first, Self.second])
        XCTAssertEqual(requests.last?.authorization, "Bearer access")
        let packageReads = { requests.filter { $0.path == "/api/packages" }.count }
        XCTAssertEqual(packageReads(), 0)

        // Already gone on the service (404): removed all the same.
        listed = [Self.json(Self.first)]
        await store.removeShopWait(Self.second)
        XCTAssertEqual(requests.last?.method, "DELETE")
        XCTAssertEqual(requests.last?.path, "/api/shop-waits/\(Self.second.uuidString.lowercased())")
        XCTAssertEqual(store.shopWaits.waits.map(\.id), [Self.first])
        XCTAssertNil(store.errorMessage)

        // The service fails to remove it: it comes back, and the waits are read again.
        removal = 503
        let before = requests.count
        await store.removeShopWait(Self.first)
        XCTAssertEqual(requests.dropFirst(before).map(\.method), ["DELETE", "GET"])
        XCTAssertEqual(store.shopWaits.waits.map(\.id), [Self.first])
        XCTAssertEqual(packageReads(), 0, "The reader's own removal reads no parcels")

        // The shop shipped: the wait goes by itself and the parcels are read.
        listed = []
        await store.loadShopWaits()
        XCTAssertEqual(store.shopWaits.waits, [])
        XCTAssertEqual(packageReads(), 1)
        await store.loadShopWaits()
        XCTAssertEqual(packageReads(), 1, "Once")

        // A read that fails keeps those shown.
        listed = [Self.json(Self.first)]
        await store.loadShopWaits()
        ShopWaitTestURLProtocol.handler = { _ in (500, Data()) }
        await store.loadShopWaits()
        XCTAssertEqual(store.shopWaits.waits.map(\.id), [Self.first])

        // Signing out forgets them.
        session.forceSignOut()
        XCTAssertEqual(store.shopWaits.waits, [])
    }

    private static let quietNotifications = DeviceNotificationAccess(status: { .denied }, request: {}, register: {}, token: { nil })
}

private final class ShopWaitTestURLProtocol: URLProtocol {
    nonisolated(unsafe) static var handler: ((URLRequest) -> (Int, Data))?

    @MainActor static var configuration: AppConfiguration {
        AppConfiguration(mode: .api, apiBaseURL: URL(string: "https://shop-waits.test")!, supabaseURL: URL(string: "https://shop-waits.test")!,
                         supabasePublishableKey: "public", googleAuthEnabled: false, appleAuthEnabled: false, emailOTPEnabled: true,
                         appGroupIdentifier: "shop-waits.test")
    }

    static func transport() -> URLSession {
        let configuration = URLSessionConfiguration.ephemeral
        configuration.protocolClasses = [ShopWaitTestURLProtocol.self]
        return URLSession(configuration: configuration)
    }

    override class func canInit(with request: URLRequest) -> Bool { request.url?.host == "shop-waits.test" }
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

private final class ShopWaitTestStorage: DeviceParcelStorage {
    var parcels: [DeviceParcel] = []
    var keys: [String: String] = [:]
    func load() -> (parcels: [DeviceParcel], keys: [String: String]) { (parcels, keys) }
    func save(_ parcels: [DeviceParcel], keys: [String: String]) { self.parcels = parcels; self.keys = keys }
}

private final class ShopWaitTestSessionPersistence: SessionPersistence {
    var data: Data?
    func save<T: Encodable>(_ value: T) throws { data = try JSONEncoder.deliveryTracker.encode(value) }
    func load<T: Decodable>() -> T? { data.flatMap { try? JSONDecoder.deliveryTracker.decode(T.self, from: $0) } }
    func delete() { data = nil }
}
