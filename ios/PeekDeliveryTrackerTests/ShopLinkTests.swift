import XCTest
@testable import PeekDeliveryTracker

/// A shop's tracking-page link in Add: which links are one, what Add makes of Peek's answer, what it sends, and that a
/// shared link goes the way a pasted one does, as the web's tests (src/components/shopLink/ShopLink.test.tsx) have them.
@MainActor
final class ShopLinkTests: XCTestCase {
    private var suite = ""
    private var defaults: UserDefaults!
    private var english: Localizer!
    private var originalLanguage: AppLanguage = .en

    override func setUp() async throws {
        suite = "shop-link-tests-\(UUID().uuidString)"
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

    /// A made-up order key, as the web's tests use.
    private static let link = "https://hm.delivery-status.com/pl/?orderNo=9900000000001&s=0000aaaa1111bbbb2222cccc3333dddd"
    private static let notYet = #"{"links":1,"parcels":[],"unresolved":[{"platform":"parcellab","platform_name":"parcelLab","shop_name":"H&M","reason":"not_found","waitable":true}]}"#
    private static let waitID = UUID(uuidString: "3B6E2A10-8C4D-4F7E-9A21-5D0C7B3E9F18")!
    private static let kept = """
    {"shop_waits":[{"id":"\(waitID.uuidString.lowercased())","platform":"parcellab","platform_name":"parcelLab","shop_name":"H&M",\
    "label":"Coat","created_at":"2026-11-26T09:00:00.000Z","expires_at":"2026-12-10T09:00:00.000Z","expired_at":null}]}
    """

    // MARK: - Which links are a shop's tracking page

    func testTellsAShopsTrackingPageFromLookalikes() {
        for link in [
            Self.link,
            "http://delivery-status.com/pl/?orderNo=1",
            "HTTPS://HM.Delivery-Status.COM/de/?orderNo=1&s=2",
            "https://www.zalando.delivery-status.com/?xid=1",
            "https://prtl.parcellab.com/trackings/details?trackingNo=1&courier=dhl-germany",
            "https://prtl.parcellab.com/TRACKINGS/details?trackingNo=1",
        ] {
            XCTAssertEqual(ShopLink.link(link), link, link)
        }
        for lookalike in [
            "https://delivery-status.com.example.test/pl/?orderNo=1",
            "https://hm-delivery-status.com/pl/?orderNo=1",
            "https://hmdelivery-status.com/pl/?orderNo=1",
            "https://hm.delivery-status.co/pl/?orderNo=1",
            "https://hm.delivery-status.com.example.test/pl/?orderNo=1",
            "https://prtl.parcellab.com/other/details?trackingNo=1",
            "https://prtl.parcellab.com/trackings",
            "https://prtl.parcellab.com.example.test/trackings/details",
            "https://example.test/trackings/?host=prtl.parcellab.com",
            "https://example.test/?next=https://hm.delivery-status.com/pl/",
            "https://example.test\\@hm.delivery-status.com/pl/",
            "ftp://hm.delivery-status.com/pl/",
            "hm.delivery-status.com/pl/?orderNo=1",
            "",
        ] {
            XCTAssertNil(ShopLink.link(lookalike), lookalike)
            XCTAssertNil(ShopLink.request(in: "Follow package: \(lookalike)"), lookalike)
        }

        // In an email's words: the punctuation after it is no part of it, HTML's &amp; is &, and each link is read once.
        let email = """
        Your order is on its way (\(Self.link.replacingOccurrences(of: "&", with: "&amp;"))).
        Follow package: \(Self.link)!
        <a href="https://cos.delivery-status.com/de/?orderNo=2">Track</a> https://example.test/?orderNo=3
        """
        XCTAssertEqual(ShopLink.links(in: email), [Self.link, "https://cos.delivery-status.com/de/?orderNo=2"])
        // Only the links go, and at most the three Peek reads.
        XCTAssertEqual(ShopLink.request(in: email), "\(Self.link)\nhttps://cos.delivery-status.com/de/?orderNo=2")
        let many = (1...5).map { "https://hm.delivery-status.com/pl/?orderNo=\($0)" }
        XCTAssertEqual(ShopLink.request(in: many.joined(separator: " ")), many.prefix(3).joined(separator: "\n"))
        XCTAssertNil(ShopLink.request(in: "00340000000000000001"))
    }

    // MARK: - What Add makes of the answer

    func testAddFillsInTheNumberAWaitsForTheShopOrSaysWhyNot() throws {
        // A parcel: its number, with its carrier when the catalog knows it.
        let found = try ShopLinkAnswer.decode(Data(#"{"links":1,"parcels":[{"tracking_number":"00340000000000000001","carrier":"dhl","confidence":"high"}],"unresolved":[]}"#.utf8))
        XCTAssertNotNil(CarrierCatalog.shared.definitions[.dhl])
        XCTAssertEqual(ShopLinkOutcome(found), .found(.init(trackingNumber: "00340000000000000001", carrier: .dhl)))
        for carrier in ["unknown", "a-carrier-from-a-newer-catalog"] {
            let answer = ShopLinkAnswer(parcels: [.init(trackingNumber: "1Z0000000000000001", carrier: carrier)], unresolved: [])
            XCTAssertEqual(ShopLinkOutcome(answer), .found(.init(trackingNumber: "1Z0000000000000001", carrier: nil)), carrier)
        }
        XCTAssertEqual(ShopLinkOutcome(found, knows: { _ in false }), .found(.init(trackingNumber: "00340000000000000001", carrier: nil)))

        // Not shipped yet: signed in, Peek waits; a visitor is asked to sign in.
        let notYet = try ShopLinkAnswer.decode(Data(Self.notYet.utf8))
        let hm = ShopLinkAnswer.Unresolved(platformName: "parcelLab", shopName: "H&M", waitable: true)
        XCTAssertEqual(ShopLinkOutcome(notYet), .unresolved(hm))
        let waiting = ShopLinkReading.unresolved(hm)
        XCTAssertTrue(waiting.canWait(signedIn: true))
        XCTAssertFalse(waiting.canWait(signedIn: false))
        XCTAssertEqual(ShopLinkNoticeText(waiting, signedIn: true, localizer: english), ShopLinkNoticeText(
            title: "H&M hasn’t named a parcel on this page yet: it hasn’t shipped.",
            body: "Peek checks every few hours for 14 days and adds the parcel as soon as a carrier and tracking number appear."))
        XCTAssertEqual(ShopLinkNoticeText(waiting, signedIn: false, localizer: english)?.body,
                       "Sign in, and Peek waits for it: it checks every few hours for 14 days.")
        XCTAssertEqual(ShopLinkNoticeText.waitAction(waiting, localizer: english), "Wait for H&M")
        let unnamed = ShopLinkReading.unresolved(.init(platformName: "parcelLab", shopName: nil, waitable: true))
        XCTAssertEqual(ShopLinkNoticeText(unnamed, signedIn: true, localizer: english)?.title,
                       "The shop hasn’t named a parcel on this page yet: it hasn’t shipped.")
        XCTAssertEqual(ShopLinkNoticeText.waitAction(unnamed, localizer: english), "Wait for the shop")

        // A page Peek can't read: nothing to wait for.
        let unreadable = ShopLinkReading.unresolved(.init(platformName: "parcelLab", shopName: "H&M", waitable: false))
        XCTAssertFalse(unreadable.canWait(signedIn: true))
        XCTAssertEqual(ShopLinkNoticeText(unreadable, signedIn: true, localizer: english),
                       ShopLinkNoticeText(title: nil, body: "Peek can’t read this shop’s tracking page. Paste the tracking number from the shipping email."))

        // No answer: the page isn't answering.
        XCTAssertEqual(ShopLinkOutcome(ShopLinkAnswer(parcels: [], unresolved: [])), .failed)
        XCTAssertFalse(ShopLinkReading.failed.canWait(signedIn: true))
        XCTAssertEqual(ShopLinkNoticeText(.failed, signedIn: true, localizer: english)?.body,
                       "The shop’s tracking page isn’t answering. Try again in a little while.")
        XCTAssertEqual(ShopLinkNoticeText(.reading, signedIn: true, localizer: english)?.spoken, "Reading the shop’s tracking page…")
        XCTAssertNil(ShopLinkNoticeText(.none, signedIn: true, localizer: english))

        // A newer server's platform or reason still reads; an answer that isn't one throws.
        let newer = try ShopLinkAnswer.decode(Data(#"{"links":1,"parcels":[],"unresolved":[{"platform":"narvar","platform_name":"Narvar","shop_name":null,"reason":"paused","waitable":true}]}"#.utf8))
        XCTAssertEqual(newer.unresolved, [.init(platformName: "Narvar", shopName: nil, waitable: true)])
        XCTAssertThrowsError(try ShopLinkAnswer.decode(Data(#"{"error":"Too many requests"}"#.utf8))) {
            guard case .invalidResponse? = $0 as? DeliveryAPIError else { return XCTFail("\($0)") }
        }

        // Every language words each notice, with no placeholder left.
        for language in AppLanguage.allCases {
            english.language = language
            for reading in [ShopLinkReading.reading, .failed, waiting, unnamed, unreadable] {
                for signedIn in [true, false] {
                    let notice = try XCTUnwrap(ShopLinkNoticeText(reading, signedIn: signedIn, localizer: english))
                    XCTAssertFalse(notice.spoken.contains("{{"), "\(language) \(notice.spoken)")
                    XCTAssertFalse(notice.spoken.contains("shopLink."), "\(language) \(notice.spoken)")
                }
                XCTAssertFalse(ShopLinkNoticeText.waitAction(reading, localizer: english).contains("{{"), "\(language)")
            }
        }
    }

    func testTheFieldReadsALinkWithNoNumberAndTakesTheNumberItNames() {
        // The link holds no number of its own: Add reads it through the shop's platform.
        XCTAssertEqual(AddParcelView.trackingNumber(in: Self.link), "")
        XCTAssertEqual(ShopLinkField.question(in: Self.link, number: AddParcelView.trackingNumber(in: Self.link)), Self.link)
        // A number beside it is added as any other; words alone are not read.
        XCTAssertNil(ShopLinkField.question(in: "\(Self.link) 00340000000000000001", number: "00340000000000000001"))
        XCTAssertNil(ShopLinkField.question(in: "Your order is on its way", number: ""))

        var field = ShopLinkField()
        XCTAssertEqual(field.reading(for: nil), .none)
        XCTAssertEqual(field.reading(for: Self.link), .reading)
        // Typed, it waits for typing to pause; pasted, it is read at once.
        XCTAssertTrue(field.waitsForTyping(Self.link))
        field.arrived(Self.link)
        XCTAssertFalse(field.waitsForTyping(Self.link))
        XCTAssertTrue(field.waitsForTyping(Self.link + "x"))

        let hm = ShopLinkAnswer.Unresolved(platformName: "parcelLab", shopName: "H&M", waitable: true)
        XCTAssertNil(field.answer(.unresolved(hm), for: Self.link))
        XCTAssertEqual(field.reading(for: Self.link), .unresolved(hm))
        // Another link is read anew; the first one's answer stands for it.
        XCTAssertEqual(field.reading(for: "https://cos.delivery-status.com/de/?orderNo=2"), .reading)
        XCTAssertNil(field.answer(.failed, for: "https://cos.delivery-status.com/de/?orderNo=2"))
        XCTAssertEqual(field.reading(for: "https://cos.delivery-status.com/de/?orderNo=2"), .failed)

        // A parcel named: the field takes its number, and its carrier stays picked while the field holds that number.
        let found = ShopLinkOutcome.Found(trackingNumber: "00340000000000000001", carrier: .dhl)
        XCTAssertEqual(field.answer(.found(found), for: Self.link), found)
        XCTAssertEqual(field.carrier(for: "00340000000000000001"), .dhl)
        XCTAssertNil(field.carrier(for: "0034000000000000000"))
        XCTAssertEqual(field.reading(for: ShopLinkField.question(in: "00340000000000000001", number: "00340000000000000001")), .none)
    }

    func testASharedLinkGoesTheWayAPastedOneDoes() throws {
        // The share extension's hand-off, unchanged: the draft waits in the App Group and is gone once Add takes it.
        XCTAssertTrue(ShareInbox.save(SharedParcelDraft(label: "Coat", trackingInput: "Your H&M order\nFollow package: \(Self.link)"), defaults: defaults))
        let draft = try XCTUnwrap(ShareInbox.consume(defaults: defaults))
        XCTAssertNil(ShareInbox.consume(defaults: defaults))

        // Add opens on it as on a paste: its link alone is read, at once.
        let field = ShopLinkField(arriving: draft.trackingInput)
        let question = ShopLinkField.question(in: draft.trackingInput, number: AddParcelView.trackingNumber(in: draft.trackingInput))
        XCTAssertEqual(question, Self.link)
        XCTAssertFalse(field.waitsForTyping(draft.trackingInput))
        XCTAssertEqual(field.reading(for: question), .reading)
        XCTAssertEqual(draft.label, "Coat")
    }

    // MARK: - What is sent

    func testTheStoreReadsAsAVisitorOrForTheAccountAndWaits() async throws {
        let transport = ShopWaitTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        let configuration = ShopWaitTestURLProtocol.configuration
        let user = AuthUser(id: UUID(), email: "test@example.test", isAnonymous: false)
        let signedIn = try JSONEncoder.deliveryTracker.encode(AuthSession(
            accessToken: "access", tokenType: "bearer", expiresIn: 3600,
            expiresAt: Int(Date().timeIntervalSince1970) + 3600, refreshToken: "refresh", user: user
        ))
        struct Sent { let method: String; let path: String; let headers: [String: String]; let body: [String: String]? }
        nonisolated(unsafe) var sent: [Sent] = []
        nonisolated(unsafe) var reading: (Int, String) = (200, Self.notYet)
        nonisolated(unsafe) var keeping: (Int, String) = (200, Self.kept)
        ShopWaitTestURLProtocol.handler = { request in
            let path = request.url?.path ?? ""
            if path == "/auth/v1/verify" { return (200, signedIn) }
            let method = request.httpMethod ?? "GET"
            let body = Self.body(of: request).flatMap { try? JSONSerialization.jsonObject(with: $0) as? [String: String] }
            sent.append(Sent(method: method, path: path, headers: request.allHTTPHeaderFields ?? [:], body: body))
            switch (method, path) {
            case ("POST", "/api/public/shop-links"), ("POST", "/api/shop-links"): return (reading.0, Data(reading.1.utf8))
            case ("POST", "/api/shop-waits"): return (keeping.0, Data(keeping.1.utf8))
            case ("GET", "/api/shop-waits"): return (200, Data(Self.kept.utf8))
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
        let hm = ShopLinkAnswer.Unresolved(platformName: "parcelLab", shopName: "H&M", waitable: true)

        // A visitor: the public address, the link alone in a JSON body, and nothing that names a session or a site.
        session.showWelcome()
        XCTAssertTrue(store.readsShopLinks)
        XCTAssertFalse(store.waitsForShops)
        let asVisitor = await store.readShopLink(Self.link)
        XCTAssertEqual(asVisitor, .unresolved(hm))
        let visitor = try XCTUnwrap(sent.last)
        XCTAssertEqual([visitor.method, visitor.path], ["POST", "/api/public/shop-links"])
        XCTAssertEqual(visitor.body, ["text": Self.link])
        XCTAssertEqual(visitor.headers["Content-Type"], "application/json")
        XCTAssertEqual(visitor.headers["Accept"], "application/json")
        XCTAssertEqual(visitor.headers["X-Peek-Inputs"], URLRequest.peekInputs)
        for header in ["Authorization", "Cookie", "Origin", "Sec-Fetch-Site"] { XCTAssertNil(visitor.headers[header], header) }
        // A refusal or a server down is a page that doesn't answer.
        for status in [429, 503, 400] {
            reading = (status, #"{"error":"No"}"#)
            let failed = await store.readShopLink(Self.link)
            XCTAssertEqual(failed, .failed, "\(status)")
        }
        reading = (200, "Not JSON")
        let unreadableAnswer = await store.readShopLink(Self.link)
        XCTAssertEqual(unreadableAnswer, .failed)
        reading = (200, Self.notYet)
        // A visitor can't wait: nothing is sent.
        let beforeWait = sent.count
        do { try await store.keepShopWait(Self.link, label: "Coat"); XCTFail("A visitor waits for no shop") } catch {}
        XCTAssertEqual(sent.count, beforeWait)

        // The demo reads as a visitor, as the web's does, and waits for nothing.
        session.enterDemo()
        XCTAssertFalse(store.waitsForShops)
        let inDemo = await store.readShopLink(Self.link)
        XCTAssertEqual(inDemo, .unresolved(hm))
        XCTAssertEqual(sent.last?.path, "/api/public/shop-links")
        XCTAssertNil(sent.last?.headers["Authorization"])

        // Signed in: the account's address, with its token.
        try await session.verifyCode(email: "test@example.test", code: "123456")
        XCTAssertTrue(store.waitsForShops)
        let forAccount = await store.readShopLink(Self.link)
        XCTAssertEqual(forAccount, .unresolved(hm))
        let account = try XCTUnwrap(sent.last)
        XCTAssertEqual([account.method, account.path], ["POST", "/api/shop-links"])
        XCTAssertEqual(account.body, ["text": Self.link])
        XCTAssertEqual(account.headers["Authorization"], "Bearer access")

        // Waiting sends the link and the name as typed, trimmed; the wait shows at once, and the waits are read again.
        try await store.keepShopWait(Self.link, label: "  Coat \n")
        let kept = try XCTUnwrap(sent.last { $0.method == "POST" && $0.path == "/api/shop-waits" })
        XCTAssertEqual(kept.body, ["text": Self.link, "label": "Coat"])
        XCTAssertEqual(kept.headers["Authorization"], "Bearer access")
        XCTAssertEqual(store.shopWaits.waits.map(\.id), [Self.waitID])
        XCTAssertEqual(store.shopWaits.waits.first?.label, "Coat")
        for _ in 0..<100 where !sent.contains(where: { $0.method == "GET" && $0.path == "/api/shop-waits" }) {
            try await Task.sleep(for: .milliseconds(20))
        }
        XCTAssertTrue(sent.contains { $0.method == "GET" && $0.path == "/api/shop-waits" }, "The waits are read again")

        // A refusal comes back to Add, to show the app's words for it.
        keeping = (400, #"{"error":"Invalid label"}"#)
        do {
            try await store.keepShopWait(Self.link, label: "Coat")
            XCTFail("The refusal reaches Add")
        } catch {
            guard case .service(let message)? = error as? DeliveryAPIError else { return XCTFail("\(error)") }
            XCTAssertEqual(message, "Invalid label")
            XCTAssertFalse(english.errorMessage(error).isEmpty)
        }

        // Neither request is counted: the statistics never see a shop link.
        let catalog = try XCTUnwrap(AnalyticsCatalog.bundled)
        for request in sent where request.path.hasPrefix("/api/shop-") || request.path.hasPrefix("/api/public/shop-") {
            let body = request.body.flatMap { try? JSONSerialization.data(withJSONObject: $0) }
            XCTAssertNil(catalog.event(path: request.path, method: request.method, body: body), request.path)
        }
    }

    private static let quietNotifications = DeviceNotificationAccess(status: { .denied }, request: {}, register: {}, token: { nil })

    /// A request's body, which reaches a URL protocol as a stream.
    nonisolated private static func body(of request: URLRequest) -> Data? {
        if let body = request.httpBody { return body }
        guard let stream = request.httpBodyStream else { return nil }
        stream.open()
        defer { stream.close() }
        var data = Data()
        var buffer = [UInt8](repeating: 0, count: 4096)
        while stream.hasBytesAvailable {
            let count = stream.read(&buffer, maxLength: buffer.count)
            if count <= 0 { break }
            data.append(buffer, count: count)
        }
        return data
    }
}
