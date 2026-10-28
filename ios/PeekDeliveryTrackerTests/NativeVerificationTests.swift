import CryptoKit
import DeviceCheck
import Foundation
import XCTest
@testable import PeekDeliveryTracker

final class NativeVerificationTests: XCTestCase {
    private let base = URL(string: "https://peek.example")!
    private func lookup() -> URLRequest {
        var request = URLRequest(url: base.appending(path: "api/public/parcels"))
        request.httpMethod = "POST"
        request.httpBody = Data("{\"trackingNumber\":\"SYNTHETIC\"}".utf8)
        return request
    }
    private let unsupported = NativeAttestProvider(supported: { false }, generateKey: { "unused" }, attest: { _, _ in Data() }, assert: { _, _ in Data() })
    private func refresh() -> URLRequest {
        var request = URLRequest(url: base.appending(path: "api/sync"))
        request.httpMethod = "POST"
        return request
    }
    private var registered: [String: NativeAttestRecord] {
        [base.absoluteString: NativeAttestRecord(keyID: "synthetic-key", appID: "TESTTEAM01.com.example.Peek", registered: true)]
    }
    private let neverRegisters = NativeAttestProvider(supported: { true }, generateKey: { XCTFail("Unexpected new key"); return "" },
        attest: { _, _ in XCTFail("Unexpected registration"); return Data() }, assert: { _, _ in XCTFail("Unexpected assertion"); return Data() })

    func testFreeBuildExchangesTokenThroughNativeTransportAndReusesProof() async throws {
        let server = NativeTestServer()
        let verifier = NativeVerification(provider: unsupported, present: { url in
            await server.present(url)
            return "synthetic-turnstile-token"
        }, records: [:], save: { _ in })
        let first = try await verifier.send(lookup(), baseURL: base, using: { try await server.send($0) })
        let second = try await verifier.send(lookup(), baseURL: base, using: { try await server.send($0) })
        XCTAssertEqual(first.1.statusCode, 201)
        XCTAssertEqual(second.1.statusCode, 201)
        let requests = await server.requests
        XCTAssertEqual(requests.filter { $0.url?.path == "/api/public/verification" }.count, 1)
        let presented = await server.presented
        XCTAssertEqual(presented.count, 1)
        XCTAssertEqual(requests.last?.httpBody, lookup().httpBody)
        XCTAssertEqual(requests.last?.value(forHTTPHeaderField: "X-Native-Verification"), "1")
        XCTAssertEqual(requests.last?.value(forHTTPHeaderField: "X-Lookup-Proof"), "synthetic-proof")
    }

    func testCancellationDoesNotRetryOrExchangeToken() async throws {
        let server = NativeTestServer()
        let verifier = NativeVerification(provider: unsupported, present: { _ in throw CancellationError() }, records: [:], save: { _ in })
        do {
            _ = try await verifier.send(lookup(), baseURL: base, using: { try await server.send($0) })
            XCTFail("Expected cancellation")
        } catch is CancellationError { }
        let requests = await server.requests
        XCTAssertEqual(requests.filter { $0.url?.path == "/api/public/parcels" }.count, 1)
        XCTAssertFalse(requests.contains { $0.url?.path == "/api/public/verification" })
    }

    func testDisabledVerificationAndRateLimitsNeverPresent() async throws {
        for status in [201, 429, 503] {
            let server = NativeTestServer(initialStatus: status)
            let verifier = NativeVerification(provider: unsupported, present: { _ in XCTFail("Unexpected verification"); return "" }, records: [:], save: { _ in })
            let result = try await verifier.send(lookup(), baseURL: base, using: { try await server.send($0) })
            XCTAssertEqual(result.1.statusCode, status)
            let requests = await server.requests
            XCTAssertEqual(requests.count, 1)
        }
    }

    func testBackgroundDetectionNeverPresentsWhileTyping() async throws {
        let server = NativeTestServer()
        let verifier = NativeVerification(provider: unsupported, present: { _ in XCTFail("Unexpected check while typing"); return "" }, records: [:], save: { _ in })
        do {
            _ = try await verifier.send(lookup(), baseURL: base, allowPresentation: false, using: { try await server.send($0) })
            XCTFail("Expected verification requirement")
        } catch is NativeVerificationFailed { }
        let requests = await server.requests
        XCTAssertFalse(requests.contains { $0.url?.path == "/api/public/verification" })
    }

    func testFailedProofDoesNotLoop() async throws {
        let server = NativeTestServer(rejectProof: true)
        let verifier = NativeVerification(provider: unsupported, present: { _ in "token" }, records: [:], save: { _ in })
        do {
            _ = try await verifier.send(lookup(), baseURL: base, using: { try await server.send($0) })
            XCTFail("Expected rejection")
        } catch is NativeVerificationFailed { }
        let requests = await server.requests
        XCTAssertEqual(requests.filter { $0.url?.path == "/api/public/parcels" }.count, 2)
    }

    func testPaidBuildRegistersKeyAndSignsExactPayloadWithoutPresenting() async throws {
        let server = NativeTestServer(paid: true)
        let provider = NativeAttestProvider(supported: { true }, generateKey: { "synthetic-key" }, attest: { key, hash in
            XCTAssertEqual(key, "synthetic-key")
            XCTAssertEqual(hash, Data(SHA256.hash(data: Data("synthetic-attestation-challenge".utf8))))
            return Data("synthetic-attestation".utf8)
        }, assert: { key, hash in
            var req = URLRequest(url: URL(string: "https://peek.example/api/public/parcels")!)
            req.httpMethod = "POST"; req.httpBody = Data("{\"trackingNumber\":\"SYNTHETIC\"}".utf8)
            XCTAssertEqual(key, "synthetic-key")
            XCTAssertEqual(hash, Data(SHA256.hash(data: Data(NativeVerification.payload(req, challenge: "synthetic-assertion-challenge").utf8))))
            return Data("synthetic-assertion".utf8)
        })
        let verifier = NativeVerification(provider: provider, present: { _ in XCTFail("Unexpected browser check"); return "" }, records: [:], save: { _ in })
        for _ in 0..<2 {
            let result = try await verifier.send(lookup(), baseURL: base, using: { try await server.send($0) })
            XCTAssertEqual(result.1.statusCode, 201)
        }
        let requests = await server.requests
        XCTAssertEqual(requests.filter { $0.url?.path == "/api/public/native/verification" && $0.httpMethod == "POST" }.count, 1)
        XCTAssertEqual(requests.last?.value(forHTTPHeaderField: "X-App-Attest-Assertion"), Data("synthetic-assertion".utf8).base64EncodedString())
        XCTAssertNil(requests.last?.value(forHTTPHeaderField: "X-Lookup-Proof"))
    }

    func testInvalidKeyAfterReinstallIsRemovedBeforeFallback() async throws {
        let server = NativeTestServer(paid: true)
        let removed = expectation(description: "Invalid key removed")
        let assertions = NativeTestAssertions()
        let provider = NativeAttestProvider(supported: { true }, generateKey: { XCTFail("Unexpected new key during backoff"); return "new" },
            attest: { _, _ in Data() }, assert: { _, _ in
                if await assertions.next() == 1 { return Data("synthetic-assertion".utf8) }
                throw NSError(domain: DCError.errorDomain, code: DCError.Code.invalidKey.rawValue)
            })
        let verifier = NativeVerification(provider: provider, present: { _ in "token" },
            records: [base.absoluteString: NativeAttestRecord(keyID: "old", appID: "TESTTEAM01.com.example.Peek", registered: true)],
            save: { records in XCTAssertTrue(records.isEmpty); removed.fulfill() })
        // Populate the configuration, then invalidate the already registered key.
        _ = try await verifier.send(lookup(), baseURL: base, using: { try await server.send($0) })
        let result = try await verifier.send(lookup(), baseURL: base, using: { try await server.send($0) })
        XCTAssertEqual(result.1.statusCode, 201)
        await fulfillment(of: [removed], timeout: 1)
    }

    func testAppleOutageFallsBackAndConcurrentRequestsShareVerification() async throws {
        let server = NativeTestServer(paid: true)
        let provider = NativeAttestProvider(supported: { true }, generateKey: { throw NativeVerificationFailed() }, attest: { _, _ in Data() }, assert: { _, _ in Data() })
        let verifier = NativeVerification(provider: provider, present: { url in await server.present(url); return "token" }, records: [:], save: { _ in })
        async let first = verifier.send(lookup(), baseURL: base, using: { try await server.send($0) })
        async let second = verifier.send(lookup(), baseURL: base, using: { try await server.send($0) })
        let results = try await [first, second]
        XCTAssertTrue(results.allSatisfy { $0.1.statusCode == 201 })
        let presented = await server.presented
        XCTAssertEqual(presented.count, 1)
        let maximum = await server.maximumConcurrent
        XCTAssertEqual(maximum, 1)
    }
}

extension NativeVerificationTests {
    func testSignedInRequestSignsItsEmptyBodyWithTheRegisteredKey() async throws {
        let server = NativeTestServer(paid: true, initialStatus: 202)
        let expected = Data(SHA256.hash(data: Data(NativeVerification.payload(refresh(), challenge: "synthetic-assertion-challenge").utf8)))
        let provider = NativeAttestProvider(supported: { true }, generateKey: { XCTFail("Unexpected new key"); return "" },
            attest: { _, _ in XCTFail("Unexpected registration"); return Data() }, assert: { key, hash in
                XCTAssertEqual(key, "synthetic-key")
                XCTAssertEqual(hash, expected)
                return Data("synthetic-assertion".utf8)
            })
        let verifier = NativeVerification(provider: provider, present: { _ in XCTFail("Unexpected browser check"); return "" },
            records: registered, save: { _ in XCTFail("Unexpected key change") })
        let result = try await verifier.sendSignedIn(refresh(), baseURL: base, using: { try await server.send($0) })
        XCTAssertEqual(result.1.statusCode, 201)
        let requests = await server.requests
        let sent = requests.filter { $0.url?.path == "/api/sync" }
        XCTAssertEqual(sent.count, 1)
        XCTAssertEqual(sent.first?.value(forHTTPHeaderField: "X-App-Attest-Key-Id"), "synthetic-key")
        XCTAssertEqual(sent.first?.value(forHTTPHeaderField: "X-App-Attest-Challenge"), "synthetic-assertion-challenge")
        XCTAssertEqual(sent.first?.value(forHTTPHeaderField: "X-App-Attest-Assertion"), Data("synthetic-assertion".utf8).base64EncodedString())
        XCTAssertEqual(sent.first?.value(forHTTPHeaderField: "X-Native-Verification"), "1")
        XCTAssertNil(sent.first?.httpBody)
    }

    func testSignedInRequestGoesUnsignedWhenThisInstallationCannotSign() async throws {
        let unregistered = [base.absoluteString: NativeAttestRecord(keyID: "synthetic-key", appID: "TESTTEAM01.com.example.Peek", registered: false)]
        let otherApp = [base.absoluteString: NativeAttestRecord(keyID: "synthetic-key", appID: "TESTTEAM01.com.example.Other", registered: true)]
        let cases: [(NativeAttestProvider, [String: NativeAttestRecord], Bool)] = [
            (unsupported, registered, true), (neverRegisters, [:], true), (neverRegisters, unregistered, true),
            (neverRegisters, otherApp, true), (neverRegisters, registered, false),
        ]
        for (provider, records, paid) in cases {
            let server = NativeTestServer(paid: paid, initialStatus: 202)
            let verifier = NativeVerification(provider: provider, present: { _ in XCTFail("Unexpected browser check"); return "" },
                records: records, save: { _ in XCTFail("Unexpected key change") })
            let result = try await verifier.sendSignedIn(refresh(), baseURL: base, using: { try await server.send($0) })
            XCTAssertEqual(result.1.statusCode, 202)
            let requests = await server.requests
            XCTAssertEqual(requests.filter { $0.url?.path == "/api/sync" }.count, 1)
            XCTAssertNil(requests.last?.value(forHTTPHeaderField: "X-App-Attest-Assertion"))
            XCTAssertEqual(requests.last?.value(forHTTPHeaderField: "X-Native-Verification"), "1")
            XCTAssertFalse(requests.contains { $0.url?.path == "/api/public/native/challenge" })
            XCTAssertFalse(requests.contains { $0.url?.path == "/api/public/native/verification" && $0.httpMethod == "POST" })
        }
    }

    func testSlowChallengeSendsSignedInRequestUnsignedOnTime() async throws {
        let server = NativeTestServer(paid: true, initialStatus: 202, challengeDelay: .seconds(3))
        let provider = NativeAttestProvider(supported: { true }, generateKey: { "" }, attest: { _, _ in Data() }, assert: { _, _ in Data("late".utf8) })
        let verifier = NativeVerification(provider: provider, present: { _ in XCTFail("Unexpected browser check"); return "" },
            records: registered, save: { _ in })
        let clock = ContinuousClock()
        let started = clock.now
        let result = try await verifier.sendSignedIn(refresh(), baseURL: base, limit: .milliseconds(200), using: { try await server.send($0) })
        XCTAssertLessThan(clock.now - started, .seconds(2))
        XCTAssertEqual(result.1.statusCode, 202)
        let requests = await server.requests
        XCTAssertNil(requests.last { $0.url?.path == "/api/sync" }?.value(forHTTPHeaderField: "X-App-Attest-Assertion"))
    }

    func testChallengeFailureSendsSignedInRequestsUnsignedAndBacksOff() async throws {
        let server = NativeTestServer(paid: true, initialStatus: 202, challengeStatus: 503)
        let provider = NativeAttestProvider(supported: { true }, generateKey: { "" }, attest: { _, _ in Data() }, assert: { _, _ in
            XCTFail("Unexpected assertion"); return Data()
        })
        let verifier = NativeVerification(provider: provider, present: { _ in XCTFail("Unexpected browser check"); return "" },
            records: registered, save: { _ in XCTFail("Unexpected key change") })
        for _ in 0..<2 {
            let result = try await verifier.sendSignedIn(refresh(), baseURL: base, using: { try await server.send($0) })
            XCTAssertEqual(result.1.statusCode, 202)
        }
        let requests = await server.requests
        XCTAssertEqual(requests.filter { $0.url?.path == "/api/public/native/challenge" }.count, 1)
        XCTAssertTrue(requests.filter { $0.url?.path == "/api/sync" }.allSatisfy { $0.value(forHTTPHeaderField: "X-App-Attest-Assertion") == nil })
    }

    func testRefusedSignedInRequestIsSignedAgainOnceThenDropsTheKey() async throws {
        let server = NativeTestServer(paid: true, refuseAssertions: true)
        let assertions = NativeTestAssertions()
        let removed = expectation(description: "Refused key removed")
        let provider = NativeAttestProvider(supported: { true }, generateKey: { XCTFail("Unexpected new key"); return "" },
            attest: { _, _ in Data() }, assert: { _, _ in
                let count = await assertions.next()
                return Data("synthetic-assertion-\(count)".utf8)
            })
        let verifier = NativeVerification(provider: provider, present: { _ in XCTFail("Unexpected browser check"); return "" },
            records: registered, save: { records in XCTAssertTrue(records.isEmpty); removed.fulfill() })
        let result = try await verifier.sendSignedIn(refresh(), baseURL: base, using: { try await server.send($0) })
        XCTAssertEqual(result.1.statusCode, 403)
        await fulfillment(of: [removed], timeout: 1)
        let requests = await server.requests
        XCTAssertEqual(requests.filter { $0.url?.path == "/api/sync" }.compactMap { $0.value(forHTTPHeaderField: "X-App-Attest-Assertion") },
                       ["synthetic-assertion-1", "synthetic-assertion-2"].map { Data($0.utf8).base64EncodedString() })
        let presented = await server.presented
        XCTAssertTrue(presented.isEmpty)
    }

    func testConcurrentSignedInRequestsReachTheServerInSigningOrder() async throws {
        let server = NativeTestServer(paid: true, initialStatus: 202)
        let provider = NativeAttestProvider(supported: { true }, generateKey: { "" }, attest: { _, _ in Data() }, assert: { _, _ in Data("synthetic-assertion".utf8) })
        let verifier = NativeVerification(provider: provider, present: { _ in XCTFail("Unexpected browser check"); return "" },
            records: registered, save: { _ in })
        async let first = verifier.sendSignedIn(refresh(), baseURL: base, using: { try await server.send($0) })
        async let second = verifier.sendSignedIn(refresh(), baseURL: base, using: { try await server.send($0) })
        let results = try await [first, second]
        XCTAssertEqual(results.map { $0.1.statusCode }, [201, 201])
        let maximum = await server.maximumConcurrent
        XCTAssertEqual(maximum, 1)
    }
}

extension NativeVerificationTests {
    /// The provider of a build whose key is registered: it signs whatever it is given.
    private var signs: NativeAttestProvider {
        NativeAttestProvider(supported: { true }, generateKey: { XCTFail("Unexpected new key"); return "" },
                             attest: { _, _ in XCTFail("Unexpected registration"); return Data() }, assert: { _, _ in Data("synthetic-assertion".utf8) })
    }

    func testCancelledLookupLeavesAppAttestOn() async throws {
        let server = NativeTestServer(paid: true)
        let verifier = NativeVerification(provider: signs, present: { _ in XCTFail("Unexpected browser check"); return "" },
                                          records: registered, save: { _ in })
        let cancelChallenge = NativeTestSwitch()
        // URLSession ends a cancelled request with URLError.cancelled, not CancellationError.
        let send: NativeVerification.Send = { request in
            if request.url?.path == "/api/public/native/challenge", await cancelChallenge.take() { throw URLError(.cancelled) }
            return try await server.send(request)
        }
        // Cancelled first while signing after the server asked, then while signing ahead of it.
        for _ in 0..<2 {
            await cancelChallenge.set(true)
            do {
                _ = try await verifier.send(lookup(), baseURL: base, using: send)
                XCTFail("Expected cancellation")
            } catch is CancellationError {}
            let result = try await verifier.send(lookup(), baseURL: base, using: send)
            XCTAssertEqual(result.1.statusCode, 201)
        }
        let requests = await server.requests
        XCTAssertFalse(requests.contains { $0.url?.path == "/api/public/verification" })
    }

    func testCancelledPreparationLeavesAppAttestOn() async throws {
        let base = URL(string: "https://prepare.peek.example")!
        URLProtocol.registerClass(NativeHeldRequest.self)
        defer { URLProtocol.unregisterClass(NativeHeldRequest.self) }
        let started = expectation(description: "Preparation asked the server")
        NativeHeldRequest.started = { started.fulfill() }
        defer { NativeHeldRequest.started = nil }
        let server = NativeTestServer(paid: true)
        let verifier = NativeVerification(provider: signs, present: { _ in XCTFail("Unexpected browser check"); return "" },
            records: [base.absoluteString: NativeAttestRecord(keyID: "synthetic-key", appID: "TESTTEAM01.com.example.Peek", registered: true)],
            save: { _ in })
        let preparing = Task { await verifier.prepare(baseURL: base) }
        await fulfillment(of: [started], timeout: 2)
        preparing.cancel()
        await preparing.value
        var request = URLRequest(url: base.appending(path: "api/public/parcels"))
        request.httpMethod = "POST"
        request.httpBody = lookup().httpBody
        let result = try await verifier.send(request, baseURL: base, using: { try await server.send($0) })
        XCTAssertEqual(result.1.statusCode, 201)
    }
}

private actor NativeTestSwitch {
    private var on = false
    func set(_ value: Bool) { on = value }
    /// Whether it was on, which turns it off.
    func take() -> Bool {
        defer { on = false }
        return on
    }
}

/// A request through URLSession.shared to the preparation host that never gets an answer.
private final class NativeHeldRequest: URLProtocol {
    nonisolated(unsafe) static var started: (() -> Void)?
    override class func canInit(with request: URLRequest) -> Bool { request.url?.host == "prepare.peek.example" }
    override class func canonicalRequest(for request: URLRequest) -> URLRequest { request }
    override func startLoading() { Self.started?() }
    override func stopLoading() {}
}

private actor NativeTestServer {
    var requests: [URLRequest] = []
    var presented: [URL] = []
    var maximumConcurrent = 0
    private var concurrent = 0
    private let paid: Bool
    private let initialStatus: Int
    private let rejectProof: Bool
    private let refuseAssertions: Bool
    private let challengeStatus: Int
    private let challengeDelay: Duration
    init(paid: Bool = false, initialStatus: Int = 403, rejectProof: Bool = false, refuseAssertions: Bool = false,
         challengeStatus: Int = 200, challengeDelay: Duration = .zero) {
        self.paid = paid; self.initialStatus = initialStatus; self.rejectProof = rejectProof
        self.refuseAssertions = refuseAssertions; self.challengeStatus = challengeStatus; self.challengeDelay = challengeDelay
    }
    func present(_ url: URL) { presented.append(url) }
    func send(_ request: URLRequest) async throws -> (Data, HTTPURLResponse) {
        requests.append(request)
        concurrent += 1; maximumConcurrent = max(concurrent, maximumConcurrent)
        defer { concurrent -= 1 }
        try await Task.sleep(for: .milliseconds(10))
        var status = 200
        var body: [String: Any] = [:]
        var headers: [String: String] = [:]
        switch request.url!.path {
        case "/api/public/native/verification":
            if request.httpMethod == "POST" { status = 204 }
            else { body = ["appAttestAppId": paid ? "TESTTEAM01.com.example.Peek" as Any : NSNull(), "turnstile": true] }
        case "/api/public/native/challenge":
            try await Task.sleep(for: challengeDelay)
            status = challengeStatus
            let object = try JSONSerialization.jsonObject(with: request.httpBody!) as! [String: String]
            body = ["challenge": "synthetic-\(object["purpose"]!)-challenge"]
        case "/api/public/verification":
            body = ["proof": "synthetic-proof", "expiresAt": Date().addingTimeInterval(900).timeIntervalSince1970 * 1000]
        default:
            if (!refuseAssertions && request.value(forHTTPHeaderField: "X-App-Attest-Assertion") != nil) || (!rejectProof && request.value(forHTTPHeaderField: "X-Lookup-Proof") != nil) { status = 201 }
            else { status = initialStatus }
            if status == 403 { headers["X-Lookup-Verification"] = "required" }
        }
        return (try JSONSerialization.data(withJSONObject: body), HTTPURLResponse(url: request.url!, statusCode: status, httpVersion: nil, headerFields: headers)!)
    }
}

private actor NativeTestAssertions {
    private var count = 0
    func next() -> Int { count += 1; return count }
}
