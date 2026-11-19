import XCTest
@testable import PeekDeliveryTracker

final class AddByEmailFilterTests: XCTestCase {
    private var filter: EmailFilter { EmailFilter.bundled }

    func testTheLanguagesComeInTheFilesOrder() {
        XCTAssertEqual(filter.languages, ["en", "de", "fr", "it", "es", "pt", "nl", "pl", "ru", "zh"])
        XCTAssertEqual(Set(filter.languages), Set(filter.words.keys))
        XCTAssertEqual(Set(filter.mainWords.keys), Set(filter.words.keys))
    }

    func testTheOrderIsReadOffTheDocumentWhateverItsSpacing() {
        let data = Data(#"{"other":{"x":1},"words":{"zh":["包裹"],"b\"q":[],"en":["a: b"],"de":[{"k":"v"}]},"mainWords":{"en":[]}}"#.utf8)
        XCTAssertEqual(EmailFilter.keys(ofObject: "words", in: data), ["zh", "b\\\"q", "en", "de"])
        XCTAssertEqual(EmailFilter.keys(ofObject: "mainWords", in: data), ["en"])
    }

    // The web's expectations, from src/lib/emailFilter.test.ts.

    func testSearchesTheSubjectForOneLanguagesWordsQuotingThoseOfSeveralWords() {
        XCTAssertEqual(filter.gmailFilter(["fr"]), #"subject:(expédié OR expédiée OR expédition OR colis OR livraison OR suivi OR acheminement OR distribution OR "en route" OR "en chemin" OR "en transit")"#)
    }

    func testLeavesOutTheWordsOnlyADeliveryConfirmationWrites() {
        let every = filter.gmailFilter(filter.languages).lowercased()
        let words = every.components(separatedBy: CharacterSet(charactersIn: " ():\"")).filter { !$0.isEmpty }
        for word in ["delivered", "livré", "livrée", "zugestellt", "geliefert", "consegnato", "entregado", "entregue", "bezorgd", "doręczona", "доставлен", "签收"] {
            XCTAssertFalse(words.contains(word), word)
        }
    }

    func testJoinsTheChosenLanguagesInTheOrderOfferedNamingASharedWordOnce() throws {
        let search = filter.gmailFilter(["pt", "es"])
        XCTAssertTrue(search.hasPrefix("subject:(") && search.hasSuffix(")"))
        let terms = String(search.dropFirst("subject:(".count).dropLast()).components(separatedBy: " OR ")
        XCTAssertEqual(terms.filter { $0 == "enviado" }.count, 1)
        XCTAssertEqual(terms.filter { $0 == "entrega" }.count, 1)
        let paquete = try XCTUnwrap(search.range(of: "paquete")), encomenda = try XCTUnwrap(search.range(of: "encomenda"))
        XCTAssertLessThan(paquete.lowerBound, encomenda.lowerBound)
        XCTAssertEqual(filter.gmailFilter(["es", "pt"]), search)
    }

    func testIsEmptyWithNoLanguage() {
        XCTAssertEqual(filter.gmailFilter([]), "")
    }

    func testStartsWithTheLanguagePeekShowsTheDevicesAndEnglish() {
        XCTAssertEqual(filter.languages(for: ["fr-CH", "de-CH", "de"]), ["en", "de", "fr"])
        XCTAssertEqual(filter.languages(for: ["pt-BR"]), ["en", "pt"])
        XCTAssertEqual(filter.languages(for: ["sv-SE", ""]), ["en"])
        // The app's own language tags, and the device's.
        XCTAssertEqual(filter.languages(for: [AppLanguage.ptBR.rawValue, "zh-Hans-CN"]), ["en", "pt", "zh"])
    }

    func testHoldsOnlyWordsGmailTakesAsWritten() throws {
        let shape = try NSRegularExpression(pattern: #"^[\p{L}\p{M}]+(?: [\p{L}\p{M}]+)*$"#)
        for language in filter.languages {
            let words = try XCTUnwrap(filter.words[language])
            XCTAssertEqual(Set(words.map { $0.lowercased() }).count, words.count, language)
            for word in words {
                XCTAssertNotNil(shape.firstMatch(in: word, range: NSRange(word.startIndex..., in: word)), word)
                XCTAssertFalse(word.components(separatedBy: " ").contains("OR"), word)
            }
        }
    }

    func testStaysWithinWhatGmailsFilterTakesWhenEveryLanguageIsChosen() {
        XCTAssertLessThan(filter.gmailFilter(filter.languages).utf16.count, 1_500)
    }

    func testGivesAOneWordAtATimeRuleEachLanguagesMainWordsEveryFirstWordBeforeAnySecond() throws {
        XCTAssertEqual(filter.subjectWords(["fr"]), ["colis", "livraison", "expédié", "expédition"])
        XCTAssertEqual(Array(filter.subjectWords(["fr", "en"]).prefix(4)), ["shipped", "colis", "shipment", "livraison"])
        // "entrega" is Spanish and Portuguese: named once.
        XCTAssertEqual(filter.subjectWords(["es", "pt"]).filter { $0 == "entrega" }.count, 1)
        XCTAssertEqual(filter.subjectWords([]), [])
        for language in filter.languages {
            let all = try XCTUnwrap(filter.words[language])
            for word in try XCTUnwrap(filter.mainWords[language]) {
                XCTAssertTrue(all.contains(word), word)
                XCTAssertFalse(word.contains(" "), word)
            }
        }
    }

    func testReadsTheMailServiceOffAnAddressAndStartsAnyOtherOnGmail() {
        XCTAssertEqual(MailService.of("someone@gmail.com"), .gmail)
        XCTAssertEqual(MailService.of("someone@googlemail.com"), .gmail)
        XCTAssertEqual(MailService.of("Someone@Hotmail.FR"), .outlook)
        XCTAssertEqual(MailService.of("someone@live.co.uk"), .outlook)
        XCTAssertEqual(MailService.of("someone@icloud.com"), .icloud)
        XCTAssertEqual(MailService.of("someone@me.com"), .icloud)
        XCTAssertEqual(MailService.of("someone@yahoo.co.uk"), .yahoo)
        XCTAssertEqual(MailService.of("someone@me.example.test"), .gmail)
        XCTAssertEqual(MailService.of("someone@example.test"), .gmail)
        XCTAssertEqual(MailService.of("constructor@constructor.test"), .gmail)
        XCTAssertEqual(MailService.of(nil), .gmail)
    }

    func testAStepMarksTheServicesOwnNamesAndTheOptionsButton() {
        XCTAssertEqual(StepText.pieces("Click **Next**, then the [[options]] button (**Show search options**)."), [
            .plain("Click "), .bold("Next"), .plain(", then the "), .options, .plain(" button ("), .bold("Show search options"), .plain(")."),
        ])
        // As the web's `\*\*[^*]+\*\*`: a mark left open is written as it is.
        XCTAssertEqual(StepText.pieces("a ** b **** c*"), [.plain("a "), .bold(" b "), .plain("** c*")])
        XCTAssertEqual(StepText.pieces("***x** 5 * 3 ****"), [.plain("*"), .bold("x"), .plain(" 5 * 3 ****")])
        XCTAssertEqual(StepText.spoken("[[options]] **Create filter**"), "Create filter")
        XCTAssertEqual(StepText.spoken("Click the [[options]] button (**Show search options**)."), "Click the button (Show search options).")
    }

    @MainActor func testEveryStepReadsInEveryLanguageWithItsMarksClosed() {
        let localizer = Localizer()
        let original = localizer.language
        defer { localizer.language = original }
        let keys = [
            "inbox.gmail.settings", "inbox.gmail.add", "inbox.gmail.noButton", "inbox.gmail.confirm", "inbox.gmail.keepOff",
            "inbox.gmail.search", "inbox.gmail.filter", "inbox.gmail.forward", "inbox.outlook.rule", "inbox.outlook.work",
            "inbox.outlook.condition", "inbox.outlook.action", "inbox.icloud.rule", "inbox.icloud.noMail", "inbox.icloud.words",
            "inbox.icloud.more", "inbox.other.rule",
        ]
        for language in AppLanguage.allCases {
            localizer.language = language
            for key in keys {
                let text = localizer.text(key, ["options": StepText.optionsMark])
                XCTAssertNotEqual(text, key, "\(language).\(key)")
                XCTAssertFalse(StepText.spoken(text).contains("**"), "\(language).\(key)")
                XCTAssertFalse(text.contains("{{"), "\(language).\(key)")
            }
            XCTAssertTrue(StepText.pieces(localizer.text("inbox.gmail.filter", ["options": StepText.optionsMark])).contains(.options), "\(language)")
        }
    }
}

final class AddByEmailDecodingTests: XCTestCase {
    private func offer(_ json: String) throws -> AddByEmailResponse {
        try AddByEmailOffer.decode(Data(json.utf8))
    }

    func testReadsTheAddressWithEveryResultTheAppKnows() throws {
        let value = try offer("""
        {"inbox":{"address":"test-0123456789@in.example.test","createdAt":"2026-11-01T09:00:00.000Z","usedToday":3,
        "lastMailAt":"2026-11-17T08:00:00.000Z","lastResult":"named","lastAdded":0,
        "recent":[{"at":"2026-11-17T08:00:00.000Z","sender":"Moon Lamps","result":"named","added":0},
        {"at":"2026-11-16T08:00:00.000Z","sender":"Garden Shop","result":"delivered","added":0},
        {"at":"2026-11-15T08:00:00.000Z","sender":"Book Shop","result":"added","added":2},
        {"at":"2026-11-14T08:00:00.000Z","sender":"","result":"confirmation","added":0}],
        "confirmation":{"code":"123456789","link":"https://mail-settings.google.com/mail/vf-test","receivedAt":"2026-11-14T08:00:00.000Z"}},
        "dailyLimit":50}
        """)
        let inbox = try XCTUnwrap(value.inbox)
        XCTAssertEqual(value.dailyLimit, 50)
        XCTAssertEqual(inbox.address, "test-0123456789@in.example.test")
        XCTAssertEqual(inbox.lastResult, .named)
        XCTAssertEqual(inbox.recent.map(\.result), [.named, .delivered, .added, .confirmation])
        XCTAssertEqual(inbox.recent[2].added, 2)
        XCTAssertEqual(inbox.confirmation?.code, "123456789")
        XCTAssertEqual(inbox.confirmation?.link, "https://mail-settings.google.com/mail/vf-test")
    }

    func testAResultFromANewerServerDropsOutRatherThanFailTheWholePage() throws {
        let value = try offer("""
        {"inbox":{"address":"test@in.example.test","createdAt":"2026-11-01T09:00:00Z","usedToday":0,"lastMailAt":"2026-11-17T08:00:00Z",
        "lastResult":"teleported","lastAdded":null,
        "recent":[{"at":"2026-11-17T08:00:00Z","sender":"New","result":"teleported","added":0},
        {"at":"2026-11-16T08:00:00Z","sender":"Old","result":"known","added":0}],
        "confirmation":{"code":"not a code","link":null,"receivedAt":7},"somethingNew":true},"dailyLimit":50,"alsoNew":[1]}
        """)
        let inbox = try XCTUnwrap(value.inbox)
        XCTAssertNil(inbox.lastResult)
        XCTAssertEqual(inbox.recent.map(\.sender), ["Old"])
        XCTAssertNil(inbox.confirmation, "A confirmation the app can't read isn't offered")
    }

    func testNoAddressYetAndAnswersThatAreNoOffer() throws {
        let none = try offer(#"{"inbox":null,"dailyLimit":50}"#)
        XCTAssertNil(none.inbox)
        XCTAssertEqual(none.dailyLimit, 50)
        for broken in ["", "[]", #"{"inbox":null}"#, #"{"inbox":{"address":"x"},"dailyLimit":50}"#] {
            XCTAssertThrowsError(try offer(broken), broken) { error in
                guard case DeliveryAPIError.invalidResponse = error else { return XCTFail("\(error)") }
            }
        }
    }

    func testNotifiesEachParcelAnEmailAddsUnlessSwitchedOff() throws {
        let address = #"{"address":"test@in.example.test","createdAt":"2026-11-01T09:00:00Z","usedToday":0,"lastMailAt":null,"lastResult":null,"lastAdded":null,"recent":[],"confirmation":null"#
        let unsaid = try XCTUnwrap(offer(#"{"inbox":\#(address)},"dailyLimit":50}"#).inbox)
        XCTAssertNil(unsaid.notify)
        XCTAssertTrue(unsaid.notifies, "A server before the switch says nothing: on")
        let off = try XCTUnwrap(offer(#"{"inbox":\#(address),"notify":false},"dailyLimit":50}"#).inbox)
        XCTAssertEqual(off.notify, false)
        XCTAssertFalse(off.notifies)
        XCTAssertTrue(try XCTUnwrap(offer(#"{"inbox":\#(address),"notify":true},"dailyLimit":50}"#).inbox).notifies)
        XCTAssertTrue(try XCTUnwrap(offer(#"{"inbox":\#(address),"notify":"no"},"dailyLimit":50}"#).inbox).notifies,
                      "A value the app can't read leaves the switch on rather than fail the page")
    }

    func testListsTheLastFiveEmailsOrTheOneLastEmailOfAnOlderMailbox() {
        let entries = (0..<7).map { AddByEmailEntry(at: "2026-11-1\($0)T08:00:00Z", sender: "Shop \($0)", result: .known, added: 0) }
        var inbox = AddByEmail(address: "test@in.example.test", createdAt: "2026-11-01T09:00:00Z", usedToday: 0, recent: entries)
        XCTAssertEqual(inbox.recentEmails.map(\.sender), ["Shop 0", "Shop 1", "Shop 2", "Shop 3", "Shop 4"])
        inbox.recent = []
        XCTAssertEqual(inbox.recentEmails, [])
        inbox.lastMailAt = "2026-11-17T08:00:00Z"
        inbox.lastResult = .added
        inbox.lastAdded = 1
        XCTAssertEqual(inbox.recentEmails, [AddByEmailEntry(at: "2026-11-17T08:00:00Z", sender: "", result: .added, added: 1)])
    }

    func testSaysTodaysUsageOnceHalfTheAllowanceIsUsed() {
        var inbox = AddByEmail(address: "test@in.example.test", createdAt: "2026-11-01T09:00:00Z", usedToday: 24, recent: [])
        XCTAssertFalse(AddByEmailResponse(inbox: inbox, dailyLimit: 50).showsUsage)
        inbox.usedToday = 25
        XCTAssertTrue(AddByEmailResponse(inbox: inbox, dailyLimit: 50).showsUsage)
        XCTAssertFalse(AddByEmailResponse(inbox: nil, dailyLimit: 0).showsUsage)
    }

    @MainActor func testEveryResultSaysWhatTheEmailDidInEveryLanguage() {
        let localizer = Localizer()
        let original = localizer.language
        defer { localizer.language = original }
        localizer.language = .en
        func entry(_ result: AddByEmailResult, _ added: Int = 0) -> AddByEmailEntry {
            AddByEmailEntry(at: "2026-11-17T08:00:00Z", sender: "Shop", result: result, added: added)
        }
        XCTAssertEqual(entry(.added, 1).resultText(localizer), "1 parcel added")
        XCTAssertEqual(entry(.added, 3).resultText(localizer), "3 parcels added")
        XCTAssertEqual(entry(.named).resultText(localizer), "Already in your deliveries: shop name added")
        XCTAssertEqual(entry(.delivered).resultText(localizer), "Already delivered: not added")
        // A shop that shows its numbers only in the reader's account with it is named where the number is.
        let amazon = AddByEmailEntry(at: "2026-11-17T08:00:00Z", sender: "Amazon.it", result: .none, added: 0, account: "Amazon")
        XCTAssertEqual(amazon.resultText(localizer), "The tracking number shows only in your Amazon account")
        XCTAssertEqual(entry(.none).resultText(localizer), "No tracking number in it")
        XCTAssertFalse(amazon.heldNoNumberUnexplained)
        XCTAssertTrue(entry(.none).heldNoNumberUnexplained)
        XCTAssertFalse(entry(.added, 1).heldNoNumberUnexplained)
        for language in AppLanguage.allCases {
            localizer.language = language
            let text = amazon.resultText(localizer)
            XCTAssertTrue(text.contains("Amazon"), "\(language)")
            XCTAssertFalse(text.contains("{{"), "\(language)")
        }
        for language in AppLanguage.allCases {
            localizer.language = language
            for result in AddByEmailResult.allCases {
                let text = entry(result, 2).resultText(localizer)
                XCTAssertFalse(text.hasPrefix("inbox."), "\(language).\(result)")
                XCTAssertFalse(text.contains("{{"), "\(language).\(result)")
            }
        }
    }
}

@MainActor
final class AddByEmailModelTests: XCTestCase {
    private let none = AddByEmailResponse(inbox: nil, dailyLimit: 50)
    private let on = AddByEmailResponse(
        inbox: AddByEmail(address: "test@in.example.test", createdAt: "2026-11-01T09:00:00Z", usedToday: 0, recent: []),
        dailyLimit: 50
    )

    private func client(read: @escaping @MainActor () async throws -> AddByEmailResponse?,
                        open: @escaping @MainActor (Bool) async throws -> AddByEmailResponse = { _ in throw DeliveryAPIError.invalidResponse },
                        close: @escaping @MainActor () async throws -> Void = {},
                        notify: @escaping @MainActor (Bool) async throws -> AddByEmailResponse = { _ in throw DeliveryAPIError.invalidResponse })
        -> AddByEmailModel.Client {
        AddByEmailModel.Client(read: read, open: open, close: close, notify: notify)
    }

    func testTheRowShowsOnlyOnceTheServerOffersTheAddress() async {
        // No account: the demo and a guest have no client.
        let model = AddByEmailModel()
        await model.load()
        XCTAssertNil(model.offer)

        // 404: not open to the account.
        model.configure(client(read: { nil }))
        await model.load()
        XCTAssertNil(model.offer)

        // An error before any answer: no row.
        model.configure(client(read: { throw DeliveryAPIError.serviceFailed(502) }))
        await model.load()
        XCTAssertNil(model.offer)

        // Offered, with or without an address yet.
        model.configure(client(read: { [none] in none }))
        await model.load()
        XCTAssertEqual(model.offer, none)

        // A moment offline afterwards leaves the row.
        model.configure(client(read: { throw URLError(.notConnectedToInternet) }))
        await model.load()
        XCTAssertEqual(model.offer, none)

        // Signed out: it goes.
        model.configure(nil)
        XCTAssertNil(model.offer)
    }

    func testTurningOnRenewingAndOffKeepTheRowAndSayWhyAChangeFailed() async {
        nonisolated(unsafe) var opened: [Bool] = []
        nonisolated(unsafe) var closed = 0
        nonisolated(unsafe) var fail = false
        let on = on
        let model = AddByEmailModel(client: client(
            read: { [none] in none },
            open: { renew in
                if fail { throw DeliveryAPIError.serviceFailed(502) }
                opened.append(renew)
                return on
            },
            close: { closed += 1 }
        ))
        await model.load()
        let turnedOn = await model.turnOn()
        XCTAssertTrue(turnedOn)
        XCTAssertEqual(model.offer?.inbox?.address, "test@in.example.test")
        let renewed = await model.renew()
        XCTAssertTrue(renewed)
        XCTAssertEqual(opened, [false, true])

        fail = true
        let failed = await model.renew()
        XCTAssertFalse(failed)
        XCTAssertNotNil(model.failure)
        XCTAssertEqual(model.offer, on, "A failed change leaves the address")
        XCTAssertEqual(Localizer().errorMessage(model.failure!, fallback: "inbox.failed"), Localizer().text("inbox.failed"))

        let off = await model.turnOff()
        XCTAssertTrue(off)
        XCTAssertNil(model.failure)
        XCTAssertEqual(closed, 1)
        XCTAssertEqual(model.offer, none, "Off, the row stays to turn it on again")
        XCTAssertFalse(model.working)
    }

    func testSwitchingTheNotificationShowsTheAnswerAndAFailureLeavesIt() async throws {
        nonisolated(unsafe) var asked: [Bool] = []
        nonisolated(unsafe) var fail = false
        let on = on
        let model = AddByEmailModel(client: client(
            read: { on },
            notify: { value in
                if fail { throw DeliveryAPIError.serviceFailed(502) }
                asked.append(value)
                var next = on
                next.inbox?.notify = value
                return next
            }
        ))
        await model.load()
        XCTAssertEqual(model.offer?.inbox?.notifies, true)

        let switchedOff = await model.setNotify(false)
        XCTAssertTrue(switchedOff)
        XCTAssertEqual(asked, [false])
        XCTAssertEqual(model.offer?.inbox?.notify, false, "The server's answer replaces the address")
        XCTAssertNil(model.failure)

        fail = true
        let failed = await model.setNotify(true)
        XCTAssertFalse(failed)
        XCTAssertNotNil(model.failure)
        XCTAssertEqual(model.offer?.inbox?.notify, false, "A failed change leaves the switch as it was")
        XCTAssertEqual(Localizer().errorMessage(try XCTUnwrap(model.failure), fallback: "inbox.failed"), Localizer().text("inbox.failed"))
        XCTAssertFalse(model.working)

        fail = false
        let switchedOn = await model.setNotify(true)
        XCTAssertTrue(switchedOn)
        XCTAssertNil(model.failure, "The next change clears the last failure")
        XCTAssertEqual(model.offer?.inbox?.notifies, true)
    }

    func testAReadThatBeganBeforeAChangeDoesNotUndoIt() async {
        nonisolated(unsafe) var release: CheckedContinuation<Void, Never>?
        let on = on
        let model = AddByEmailModel(client: client(
            read: { [none] in
                await withCheckedContinuation { release = $0 }
                return none
            },
            open: { _ in on }
        ))
        let reading = Task { await model.load() }
        while release == nil { await Task.yield() }
        await model.turnOn()
        release?.resume()
        await reading.value
        XCTAssertEqual(model.offer, on)
    }

    func testReadDuringRenewalCannotRestoreThePreviousOffer() async {
        var finishRead: CheckedContinuation<Void, Never>?
        var finishRenew: CheckedContinuation<Void, Never>?
        let model = AddByEmailModel(client: client(read: { [none] in
            await withCheckedContinuation { finishRead = $0 }
            return none
        }, open: { [on] _ in
            await withCheckedContinuation { finishRenew = $0 }
            return on
        }))
        let renewal = Task { await model.renew() }
        while finishRenew == nil { await Task.yield() }
        let read = Task { await model.load() }
        while finishRead == nil { await Task.yield() }
        finishRenew?.resume()
        _ = await renewal.value
        finishRead?.resume()
        await read.value
        XCTAssertEqual(model.offer, on)
    }

    func testComingBackReadsAgainButNotTwiceForTheSameReturn() async {
        nonisolated(unsafe) var reads = 0
        let model = AddByEmailModel(client: client(read: { [none] in reads += 1; return none }))
        let start = Date(timeIntervalSince1970: 1_800_000_000)
        await model.reloadOnReturn(now: start)
        XCTAssertEqual(reads, 0, "Nothing to read again before the first read")
        await model.load(now: start)
        await model.reloadOnReturn(now: start.addingTimeInterval(1))
        XCTAssertEqual(reads, 1)
        await model.reloadOnReturn(now: start.addingTimeInterval(AddByEmailModel.rereadAfter))
        XCTAssertEqual(reads, 2)
    }

    // MARK: - Through the account

    func testTheStoreReadsTheAddressThroughTheAccountAndHidesItOn404() async throws {
        let transport = InboxTestURLProtocol.transport()
        defer { transport.invalidateAndCancel() }
        let configuration = InboxTestURLProtocol.configuration
        let user = AuthUser(id: UUID(), email: "test@example.test", isAnonymous: false)
        let signedIn = try JSONEncoder.deliveryTracker.encode(AuthSession(
            accessToken: "access", tokenType: "bearer", expiresIn: 3600,
            expiresAt: Int(Date().timeIntervalSince1970) + 3600, refreshToken: "refresh", user: user
        ))
        let address = #"{"inbox":{"address":"test@in.example.test","createdAt":"2026-11-01T09:00:00Z","usedToday":0,"lastMailAt":null,"lastResult":null,"lastAdded":null,"recent":[],"confirmation":null},"dailyLimit":50}"#
        nonisolated(unsafe) var offered = false
        nonisolated(unsafe) var requests: [(method: String, body: String, authorization: String?)] = []
        InboxTestURLProtocol.handler = { request in
            let path = request.url?.path ?? ""
            if path == "/auth/v1/verify" { return (200, signedIn) }
            guard path == "/api/account/inbox" else { return (404, Data(#"{"error":"Not found"}"#.utf8)) }
            let method = request.httpMethod ?? ""
            requests.append((method, String(decoding: InboxTestURLProtocol.body(of: request), as: UTF8.self),
                             request.value(forHTTPHeaderField: "Authorization")))
            guard offered else { return (404, Data(#"{"error":"Not found"}"#.utf8)) }
            switch method {
            case "GET": return (200, Data(#"{"inbox":null,"dailyLimit":50}"#.utf8))
            case "POST": return (201, Data(address.utf8))
            case "PATCH": return (200, Data(address.replacingOccurrences(of: #""confirmation":null}"#, with: #""confirmation":null,"notify":false}"#).utf8))
            case "DELETE": return (204, Data())
            default: return (405, Data())
            }
        }
        let session = SessionStore(configuration: configuration, persistence: InboxTestSessionPersistence(), transport: transport)
        defer { session.forceSignOut() }
        let store = ParcelStore(configuration: configuration, session: session, localizer: Localizer(), transport: transport,
                                device: DeviceParcels(client: .api(configuration: configuration, transport: transport), storage: InboxTestStorage()),
                                deviceNotifications: Self.quietNotifications)
        store.setDeliveryLiveActivitiesEnabled(false)
        XCTAssertNil(store.addByEmailClient(), "Signed out, there is no address to ask for")
        let asked = requests.count

        try await session.verifyCode(email: "test@example.test", code: "123456")
        XCTAssertEqual(requests.count, asked)
        let model = AddByEmailModel()
        model.configure(store.addByEmailClient())
        await model.load()
        XCTAssertNil(model.offer, "404: the row stays hidden")
        XCTAssertEqual(requests.last?.method, "GET")
        XCTAssertEqual(requests.last?.authorization, "Bearer access")
        XCTAssertTrue(session.isAuthenticated, "A 404 signs nobody out")

        offered = true
        await model.load()
        XCTAssertEqual(model.offer?.dailyLimit, 50)
        XCTAssertNil(model.offer?.inbox)

        await model.turnOn()
        XCTAssertEqual(requests.last?.method, "POST")
        XCTAssertEqual(requests.last?.body, "{}")
        XCTAssertEqual(model.offer?.inbox?.address, "test@in.example.test")
        await model.renew()
        XCTAssertEqual(requests.last?.body, #"{"renew":true}"#)
        XCTAssertEqual(model.offer?.inbox?.notifies, true)
        let switchedOff = await model.setNotify(false)
        XCTAssertTrue(switchedOff)
        XCTAssertEqual(requests.last?.method, "PATCH")
        XCTAssertEqual(requests.last?.body, #"{"notify":false}"#)
        XCTAssertEqual(requests.last?.authorization, "Bearer access")
        XCTAssertEqual(model.offer?.inbox?.notify, false)
        await model.turnOff()
        XCTAssertEqual(requests.last?.method, "DELETE")
        XCTAssertNil(model.offer?.inbox)
        XCTAssertNil(model.failure)

        // The demo's account is made up: it has no address, and asks nothing.
        let count = requests.count
        let suite = "AddByEmailTests.\(UUID().uuidString)"
        let preferences = try XCTUnwrap(UserDefaults(suiteName: suite))
        defer { preferences.removePersistentDomain(forName: suite) }
        let demo = AppConfiguration(mode: .demo, apiBaseURL: URL(string: "https://inbox.test")!, supabaseURL: nil, supabasePublishableKey: "",
                                    googleAuthEnabled: false, appleAuthEnabled: false, emailOTPEnabled: false, appGroupIdentifier: "inbox.test")
        let demoSession = SessionStore(configuration: demo, defaults: preferences, persistence: InboxTestSessionPersistence(), transport: transport)
        let demoStore = ParcelStore(configuration: demo, session: demoSession, localizer: Localizer(), transport: transport,
                                    device: DeviceParcels(client: nil, storage: InboxTestStorage()), deviceNotifications: Self.quietNotifications)
        demoStore.setDeliveryLiveActivitiesEnabled(false)
        demoSession.enterDemo()
        XCTAssertNil(demoStore.addByEmailClient())
        model.configure(demoStore.addByEmailClient())
        XCTAssertNil(model.offer)
        XCTAssertEqual(requests.count, count)
    }

    private static let quietNotifications = DeviceNotificationAccess(status: { .denied }, request: {}, register: {}, token: { nil })
}

private final class InboxTestStorage: DeviceParcelStorage {
    var parcels: [DeviceParcel] = []
    var keys: [String: String] = [:]
    func load() -> (parcels: [DeviceParcel], keys: [String: String]) { (parcels, keys) }
    func save(_ parcels: [DeviceParcel], keys: [String: String]) { self.parcels = parcels; self.keys = keys }
}

private final class InboxTestSessionPersistence: SessionPersistence {
    var data: Data?
    func save<T: Encodable>(_ value: T) throws { data = try JSONEncoder.deliveryTracker.encode(value) }
    func load<T: Decodable>() -> T? { data.flatMap { try? JSONDecoder.deliveryTracker.decode(T.self, from: $0) } }
    func delete() { data = nil }
}

private final class InboxTestURLProtocol: URLProtocol {
    nonisolated(unsafe) static var handler: ((URLRequest) -> (Int, Data))?

    @MainActor static var configuration: AppConfiguration {
        AppConfiguration(mode: .api, apiBaseURL: URL(string: "https://inbox.test")!, supabaseURL: URL(string: "https://inbox.test")!,
                         supabasePublishableKey: "public", googleAuthEnabled: false, appleAuthEnabled: false, emailOTPEnabled: true,
                         appGroupIdentifier: "inbox.test")
    }

    static func transport() -> URLSession {
        let configuration = URLSessionConfiguration.ephemeral
        configuration.protocolClasses = [InboxTestURLProtocol.self]
        return URLSession(configuration: configuration)
    }

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

    override class func canInit(with request: URLRequest) -> Bool { request.url?.host == "inbox.test" }
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
