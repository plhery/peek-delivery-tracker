import Foundation
#if os(iOS)
import UIKit
#endif

/// The device's country preference helps order carrier queries; it does not select a carrier.
enum ParcelLookupCountry {
    private static let countries = Set(Locale.Region.isoRegions
        .filter { $0.identifier.utf8.count == 2 && $0.subRegions.isEmpty }
        .map(\.identifier))

    static func hint(locale: Locale = .current) -> String? {
        guard let country = locale.region?.identifier,
              country.utf8.allSatisfy({ (65...90).contains($0) }),
              countries.contains(country) else { return nil }
        return country
    }
}

enum DeliveryAPIError: LocalizedError {
    case authenticationExpired
    case duplicateTracking(UUID)
    case invalidResponse
    case labelTooLong
    case notificationsDenied
    case parcelMissing
    case pushTokenUnavailable
    case refreshFailed
    case refreshTimeout
    case rateLimited(TimeInterval)
    /// The account's tracking allowance for the day is used up: it lasts until tomorrow, not a moment.
    case allowanceUsed
    case service(String)
    case serviceFailed(Int)

    var errorDescription: String? {
        switch self {
        case .authenticationExpired: "Your sign-in expired. Please sign in again."
        case .duplicateTracking: "This tracking number is already in your delivery box."
        case .invalidResponse: "The delivery service returned an invalid response."
        case .labelTooLong: "Parcel names can be at most 80 characters."
        case .notificationsDenied: "Notifications were not allowed."
        case .parcelMissing: "This parcel is no longer in your delivery box."
        case .pushTokenUnavailable: "Apple did not return a notification token. Try again on a signed development build."
        case .refreshFailed: "Tracking refresh failed. Try again."
        case .refreshTimeout: "The tracking refresh is taking longer than expected."
        case .rateLimited: "Too many requests. Try again shortly."
        case .allowanceUsed: "Today’s tracking allowance is used up. Try again tomorrow."
        case .service(let message): message
        case .serviceFailed(let status): "Delivery service failed (\(status))."
        }
    }
}

extension HTTPURLResponse {
    /// The service's own answer that what was asked for isn't there: a 404 with its JSON error. Any other 404, such as
    /// the proxy's text while the server is up and the app isn't yet, is the server failing, and forgets nothing.
    func isServiceNotFound(_ data: Data) -> Bool {
        guard statusCode == 404, let payload = try? JSONSerialization.jsonObject(with: data) as? [String: Any] else { return false }
        return payload["error"] is String
    }
}

/// What the edge in front of the server says about a maintenance: announced or not, and the end it gave, if any.
struct MaintenanceNotice: Equatable, Sendable {
    var planned: Bool
    var backAround: Date?

    static let unannounced = MaintenanceNotice(planned: false, backAround: nil)
}

/// The server is down for a few minutes of maintenance: the machine restarting, or its database being upgraded.
/// Screens keep what they show, say so calmly and ask again by themselves; nothing signs out or forgets a parcel for it.
struct ServerDown: Error, Equatable {
    /// What the server asked for before asking again, in seconds; 0 when it said nothing.
    var retryAfter: TimeInterval = 0
    /// The edge's own answer says whether the maintenance was announced; a database's or a proxy's does not.
    var notice: MaintenanceNotice?
}

/// Tells the server down for maintenance from the server refusing or failing, as the web app does (docs/ARCHITECTURE.md).
enum Maintenance {
    /// Statuses a proxy or Cloudflare answers with for a server that does not answer itself.
    static let downStatuses: Set<Int> = [502, 503, 504, 520, 521, 522, 523, 524, 525, 526, 527, 530]
    /// The app's own 503s about one part it can't use now, which it words itself: not the server down.
    private static let partUnavailable = "not configured|amazon|carrier|verification"
    /// Errors that say this device has no network: it is offline, not the server down.
    private static let offlineCodes: Set<URLError.Code> = [.notConnectedToInternet, .dataNotAllowed, .internationalRoamingOff, .callIsActive]

    /// Whether an answer means the server is down rather than refusing: a proxy's or Cloudflare's error status (the
    /// database's 502 and the sign-in server's 503 too), or a 404 the app did not write, such as the proxy's
    /// "404 page not found" while the app starts.
    static func isServerDown(status: Int, data: Data) -> Bool {
        let payload = try? JSONSerialization.jsonObject(with: data) as? [String: Any]
        let message = payload?["error"] as? String
        if status == 404 { return message == nil }
        guard downStatuses.contains(status) else { return false }
        guard status == 503, let message, notice(in: data) == nil else { return true }
        return message.range(of: partUnavailable, options: [.regularExpression, .caseInsensitive]) == nil
    }

    /// The server down, with what it asked for and what the edge announced, or nil for any other answer.
    static func serverDown(_ data: Data, _ response: HTTPURLResponse) -> ServerDown? {
        guard isServerDown(status: response.statusCode, data: data) else { return nil }
        let retryAfter = DeliveryAPIClient.retryAfterSeconds(response.value(forHTTPHeaderField: "Retry-After"))
        return ServerDown(retryAfter: retryAfter, notice: notice(in: data))
    }

    /// The maintenance an answer's `maintenance` object announces, or nil.
    static func notice(in data: Data) -> MaintenanceNotice? {
        guard let payload = try? JSONSerialization.jsonObject(with: data) as? [String: Any],
              let maintenance = payload["maintenance"] as? [String: Any] else { return nil }
        return notice(maintenance)
    }

    private static func notice(_ value: [String: Any]) -> MaintenanceNotice {
        let planned = value["planned"] as? Bool == true
        return MaintenanceNotice(planned: planned, backAround: planned ? (value["backAround"] as? String).flatMap(DateParser.date) : nil)
    }

    /// Whether a request got no answer while the device is online: the network failed or timed out on the way to the
    /// server. Not one that was cancelled, and not a device that is offline, which keeps saying so.
    static func unanswered(_ error: Error) -> Bool {
        guard let error = error as? URLError, error.code != .cancelled else { return false }
        return !offlineCodes.contains(error.code)
    }

    /// The error to throw for a request that failed on its way: the server down when it got no answer.
    static func failure(_ error: Error) -> Error {
        unanswered(error) ? ServerDown() : error
    }

    /// How long to wait before asking again: what the server asked for and the backoff from 5 s, whichever is longer,
    /// at most a minute.
    static func retryDelay(attempt: Int, retryAfter: TimeInterval = 0) -> TimeInterval {
        let backoff = min(60, 5 * pow(2, Double(max(0, min(attempt, 10)))))
        return min(60, max(backoff, retryAfter))
    }

    /// What `/maintenance.json` says, asked only once something failed: nil when it is not there to say (the edge not
    /// in front yet answers the app's own 404 page) or says nothing readable. Never cached.
    static func read(configuration: AppConfiguration = .current, transport: URLSession = .shared) async -> MaintenanceNotice? {
        var request = URLRequest(url: configuration.apiBaseURL.appending(path: "maintenance").appendingPathExtension("json"),
                                 cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: 10)
        request.httpShouldHandleCookies = false
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        guard let (data, response) = try? await transport.data(for: request),
              let status = (response as? HTTPURLResponse)?.statusCode, status == 200 || status == 503,
              let payload = try? JSONSerialization.jsonObject(with: data) as? [String: Any] else { return nil }
        if let maintenance = payload["maintenance"] as? [String: Any] { return notice(maintenance) }
        return payload["planned"] is Bool ? notice(payload) : nil
    }
}

@MainActor
final class DeliveryAPIClient {
    private let configuration: AppConfiguration
    private let session: SessionStore
    private let transport: URLSession
    private let verification: NativeVerification

    init(configuration: AppConfiguration, session: SessionStore, transport: URLSession = .shared, verification: NativeVerification = .shared) {
        self.configuration = configuration
        self.session = session
        self.transport = transport
        self.verification = verification
    }

    func friendsSnapshot() async throws -> FriendsSnapshot {
        try await request("/api/friends")
    }

    func friendsActivity() async throws -> FriendsActivity {
        try await request("/api/friends/activity")
    }

    func friendsAction(_ value: FriendsActionRequest) async throws -> FriendsActionResponse {
        try await request("/api/friends", method: "POST", body: value)
    }

    static func invitationPreview(code: String, configuration: AppConfiguration = .current, transport: URLSession = .shared) async throws -> String {
        var request = URLRequest(url: configuration.apiBaseURL.appending(path: "api/friends/invite-preview"), cachePolicy: .reloadIgnoringLocalCacheData, timeoutInterval: 15)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try JSONSerialization.data(withJSONObject: ["code": code])
        let (data, response) = try await transport.data(for: request)
        guard let response = response as? HTTPURLResponse else { throw DeliveryAPIError.invalidResponse }
        if response.statusCode == 400 || response.statusCode == 404 { throw DeliveryAPIError.service("Invitation unavailable") }
        guard response.statusCode == 200 else { throw DeliveryAPIError.serviceFailed(response.statusCode) }
        let result = try JSONDecoder.deliveryTracker.decode(FriendsActionResponse.self, from: data)
        guard let name = result.previewNickname, !name.isEmpty, name.unicodeScalars.count <= 24 else { throw DeliveryAPIError.invalidResponse }
        return name
    }

    /// Whether a carrier picked by hand refuses the number as one it does not issue. The
    /// server runs the carrier's own check, which sends the number nowhere: no sign-in or cookies.
    static func numberRefusal(_ value: NumberRefusalRequest, configuration: AppConfiguration = .current, transport: URLSession = .shared) async throws -> Bool {
        var request = URLRequest(url: configuration.apiBaseURL.appending(path: "api/public/refusal"),
                                 cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: 15)
        request.httpMethod = "POST"
        request.httpShouldHandleCookies = false
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try JSONEncoder.deliveryTracker.encode(value)
        let (data, response) = try await transport.data(for: request)
        guard let response = response as? HTTPURLResponse else { throw DeliveryAPIError.invalidResponse }
        guard response.statusCode == 200 else { throw DeliveryAPIError.serviceFailed(response.statusCode) }
        do { return try JSONDecoder.deliveryTracker.decode(NumberRefusalResponse.self, from: data).refused }
        catch { throw DeliveryAPIError.invalidResponse }
    }

    /// Reads a parcel link: no sign-in and no cookies. Nil when the link leads nowhere, whether it
    /// was forgotten or never existed. A link whose owner stopped sharing it throws `ParcelLinkStopped`.
    /// `key`: the link's owner key, only when the site handed the parcel over to this iPhone. It goes in a header alone.
    static func publicParcel(linkID: String, key: String? = nil, configuration: AppConfiguration = .current, transport: URLSession = .shared) async throws -> PublicParcelResponse? {
        var request = URLRequest(url: configuration.apiBaseURL.appending(path: "api/public/parcels").appending(path: linkID),
                                 cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: 15)
        request.httpShouldHandleCookies = false
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        if let key { request.setValue(key, forHTTPHeaderField: "X-Parcel-Key") }
        let (data, response): (Data, URLResponse)
        do { (data, response) = try await transport.data(for: request) } catch { throw Maintenance.failure(error) }
        guard let response = response as? HTTPURLResponse else { throw DeliveryAPIError.invalidResponse }
        switch response.statusCode {
        case 200:
            do { return try JSONDecoder.deliveryTracker.decode(PublicParcelResponse.self, from: data) }
            catch { throw DeliveryAPIError.invalidResponse }
        case 404 where response.isServiceNotFound(data): return nil
        case 410: throw ParcelLinkStopped()
        case 429: throw DeliveryAPIError.rateLimited(retryAfterSeconds(response.value(forHTTPHeaderField: "Retry-After")))
        default: throw Maintenance.serverDown(data, response) ?? DeliveryAPIError.serviceFailed(response.statusCode)
        }
    }

    func claimParcels(_ value: ClaimParcelsRequest) async throws -> ClaimParcelsResponse {
        try await request("/api/packages/claim", method: "POST", body: value)
    }

    /// The link one of the account's parcels is shared through, or nil while it is not shared.
    func parcelShare(id: UUID) async throws -> ParcelShare? {
        let response: ParcelShareResponse = try await request("/api/packages/\(id.uuidString)/share")
        return response.link
    }

    /// Makes the parcel's link when it has none, else changes what the link shows.
    func shareParcel(id: UUID, _ value: ShareParcelRequest) async throws -> ParcelShare {
        let response: ParcelShareResponse = try await request("/api/packages/\(id.uuidString)/share", method: "PUT", body: value)
        guard let link = response.link, ParcelLinkRoute.validID(link.id) else { throw DeliveryAPIError.invalidResponse }
        return link
    }

    /// The link goes blank for good. The answer has no body.
    func stopSharingParcel(id: UUID) async throws {
        _ = try await rawRequest("/api/packages/\(id.uuidString)/share", method: "DELETE")
    }

    /// What its reader says of one of the account's parcels. The answer has no body.
    func sendFeedback(id: UUID, _ value: ParcelFeedbackRequest) async throws {
        _ = try await rawRequest("/api/packages/\(id.uuidString)/feedback", method: "POST", body: try JSONEncoder.deliveryTracker.encode(value))
    }

    /// `hidden`: read with no window on screen, as the menu bar reads it, so the service doesn't count it as a look.
    func listPackages(hidden: Bool = false) async throws -> [Parcel] {
        let response: PackageListResponse = try await request("/api/packages?includeArchived=true\(hidden ? "&hidden=true" : "")")
        return response.packages
    }

    /// `typedNumber`: the number as typed, from `CarrierDetectionRequest.typedNumber(_:for:)`.
    func detectCarrier(trackingNumber: String, typedNumber: String? = nil) async throws -> CarrierDetectionResponse {
        try await request("/api/carriers/detect", method: "POST",
                          body: CarrierDetectionRequest(trackingNumber: trackingNumber, lookupCountryHint: ParcelLookupCountry.hint(),
                                                        typedNumber: typedNumber))
    }

    func add(_ input: CreatePackageRequest) async throws -> CreatePackageResponse {
        try await request("/api/packages", method: "POST", body: input)
    }

    func rename(id: UUID, label: String) async throws -> Parcel {
        try await request(
            "/api/packages/\(id.uuidString)",
            method: "PATCH",
            body: RenamePackageRequest(label: label)
        )
    }

    func changeCarrier(
        id: UUID,
        carrier: CarrierID,
        trackingURL: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String?,
        phoneDigits: String? = nil,
        providerPostcode: String? = nil
    ) async throws -> ChangePackageCarrierResponse {
        try await request(
            "/api/packages/\(id.uuidString)/carrier",
            method: "PATCH",
            body: ChangePackageCarrierRequest(
                carrier: carrier,
                trackingURL: trackingURL,
                dpdPostcode: dpdPostcode,
                dpdPostcodeCountry: dpdPostcodeCountry,
                phoneDigits: phoneDigits,
                providerPostcode: providerPostcode
            )
        )
    }

    /// The parcel was opened, seen as of the news its page showed (`newsAt`), or of now without one: the moment the
    /// server kept, which its mark for new updates is measured from.
    func markSeen(id: UUID, newsAt: String?) async throws -> String {
        // The contract names the field `news_at`, and the generated requests go out under their Swift names.
        let response: PackageSeenResponse = try await request(
            "/api/packages/\(id.uuidString)/seen",
            method: "POST",
            body: newsAt.map { ["news_at": $0] }
        )
        return response.seenAt
    }

    func setMuted(id: UUID, muted: Bool) async throws -> Parcel {
        try await request(
            "/api/packages/\(id.uuidString)/notifications",
            method: "PATCH",
            body: PackageNotificationRequest(muted: muted)
        )
    }

    /// The parcel's delivery email alone: its notifications stay as they are.
    func setEmailMuted(id: UUID, muted: Bool) async throws -> Parcel {
        try await request(
            "/api/packages/\(id.uuidString)/notifications",
            method: "PATCH",
            body: PackageNotificationRequest(emailMuted: muted)
        )
    }

    func archive(id: UUID) async throws {
        let _: OKResponse = try await request("/api/packages/\(id.uuidString)", method: "DELETE")
    }

    func restore(id: UUID) async throws -> Parcel {
        try await request("/api/packages/\(id.uuidString)/restore", method: "POST")
    }

    func permanentlyDelete(id: UUID) async throws {
        let _: OKResponse = try await request(
            "/api/packages/\(id.uuidString)/permanent",
            method: "DELETE"
        )
    }

    /// Queues a check of every parcel; follow it with `waitForJobs`.
    func queueRefreshAll() async throws -> [UUID] {
        let queued: QueueResponse = try await request("/api/sync", method: "POST")
        return queued.jobIDs
    }

    func refresh(id: UUID) async throws {
        let generation = session.generation
        let queued: QueueResponse = try await request(
            "/api/packages/\(id.uuidString)/sync",
            method: "POST"
        )
        try session.checkGeneration(generation)
        try await waitForJobs(queued.jobIDs)
    }

    func waitForJobs(_ jobIds: [UUID]) async throws {
        let generation = session.generation
        var pending = Set(jobIds)
        let deadline = Date().addingTimeInterval(120)
        var interval: TimeInterval = 1
        while !pending.isEmpty && Date() < deadline {
            try session.checkGeneration(generation)
            var retryAfter: TimeInterval = 0
            do {
                let ids = Array(pending.prefix(20))
                let query = ids.map(\.uuidString).joined(separator: ",")
                let response: SyncJobListResponse = try await request("/api/sync/jobs?ids=\(query)")
                try session.checkGeneration(generation)
                guard Set(response.jobs.map(\.id)) == Set(ids) else {
                    throw DeliveryAPIError.invalidResponse
                }
                for job in response.jobs {
                    if job.status == .failed || (job.result?.errors ?? 0) > 0 {
                        if let error = job.error { throw DeliveryAPIError.service(error) }
                        throw DeliveryAPIError.refreshFailed
                    }
                    if job.status == .succeeded { pending.remove(job.id) }
                }
            } catch DeliveryAPIError.rateLimited(let seconds) {
                retryAfter = seconds
            }
            if pending.isEmpty { return }
            try await Task.sleep(for: .seconds(min(max(interval, retryAfter), max(0, deadline.timeIntervalSinceNow))))
            interval = min(interval * 1.5, 10)
        }
        if !pending.isEmpty { throw DeliveryAPIError.refreshTimeout }
    }

    func notificationPreferences() async throws -> NotificationPreferences {
        try await request("/api/push/preferences")
    }

    /// `markNewUpdates` is the list's mark on parcels with new updates when its switch is what is being saved, and
    /// `clock` and `distance` how this device writes a time and a distance when they are. Any other save leaves them
    /// out, and the server keeps the account's, sent from this device or another.
    func saveNotificationPreferences(
        _ value: NotificationPreferences, markNewUpdates: Bool? = nil,
        clock: NotificationPreferencesClock? = nil, distance: NotificationPreferencesDistance? = nil
    ) async throws -> NotificationPreferences {
        var sent = value
        // Whether the server can email the account is the server's to say.
        sent.emailAvailable = nil
        sent.markNewUpdates = markNewUpdates
        sent.clock = clock
        sent.distance = distance
        return try await request("/api/push/preferences", method: "PATCH", body: sent)
    }

    /// The name the account's device list shows for this one.
    private static var deviceName: String {
        #if os(macOS)
        Host.current().localizedName ?? "Mac"
        #else
        UIDevice.current.name
        #endif
    }

    func registerNativePushToken(
        _ token: String,
        installationID: UUID,
        language: AppLanguage,
        sendTest: Bool
    ) async throws -> Bool {
        let response: PushSubscriptionResponse = try await request(
            "/api/push/devices",
            method: "POST",
            body: NativePushDeviceRequest(
                token: token,
                installationID: installationID,
                environment: .current,
                locale: NativePushLocale(language),
                deviceName: Self.deviceName,
                sendTest: sendTest
            )
        )
        return response.testSent
    }

    func unregisterNativePushToken(_ token: String) async throws {
        let _: OKResponse = try await request(
            "/api/push/devices",
            method: "DELETE",
            body: DeleteNativePushDeviceRequest(token: token)
        )
    }

    func registerLiveActivityDevice(
        token: String,
        installationID: UUID,
        language: AppLanguage,
        revocationToken: String
    ) async throws {
        #if DEBUG
        let environment = NativePushEnvironment.development
        #else
        let environment = NativePushEnvironment.production
        #endif
        let _: OKResponse = try await request(
            "/api/live-activities/devices",
            method: "POST",
            body: LiveActivityDeviceRequest(
                installationID: installationID,
                token: token,
                environment: environment,
                locale: NativePushLocale(rawValue: language.rawValue) ?? .en,
                revocationToken: revocationToken
            )
        )
    }

    func unregisterLiveActivityDevice(installationID: UUID) async throws {
        let _: OKResponse = try await request(
            "/api/live-activities/devices",
            method: "DELETE",
            body: DeleteLiveActivityDeviceRequest(installationID: installationID)
        )
    }

    func registerLiveActivityUpdateToken(
        activityID: String,
        parcelID: UUID,
        token: String,
        installationID: UUID,
        language: AppLanguage
    ) async throws {
        #if DEBUG
        let environment = NativePushEnvironment.development
        #else
        let environment = NativePushEnvironment.production
        #endif
        let _: OKResponse = try await request(
            "/api/live-activities/activities",
            method: "POST",
            body: LiveActivityUpdateTokenRequest(
                installationID: installationID,
                activityID: activityID,
                parcelID: parcelID,
                token: token,
                environment: environment,
                locale: NativePushLocale(rawValue: language.rawValue) ?? .en
            )
        )
    }

    func unregisterLiveActivityUpdateToken(
        activityID: String,
        installationID: UUID
    ) async throws {
        let _: OKResponse = try await request(
            "/api/live-activities/activities",
            method: "DELETE",
            body: DeleteLiveActivityUpdateTokenRequest(
                installationID: installationID,
                activityID: activityID
            )
        )
    }

    func exportAccount() async throws -> Data {
        try await rawRequest("/api/account/export").0
    }

    /// The account's forwarding address for adding parcels by email. Nil when the server offers none to this account:
    /// it answers 404 until the feature is set up and open to it.
    func addByEmail() async throws -> AddByEmailResponse? {
        let (data, response) = try await rawRequest("/api/account/inbox", accepting: 404)
        return response.statusCode == 404 ? nil : try AddByEmailOffer.decode(data)
    }

    /// Makes the address, or a new one in its place when `renew` is set: the old one stops working at once.
    func openAddByEmail(renew: Bool) async throws -> AddByEmailResponse {
        let body = try JSONEncoder.deliveryTracker.encode(renew ? ["renew": true] : [String: Bool]())
        return try AddByEmailOffer.decode(try await rawRequest("/api/account/inbox", method: "POST", body: body).0)
    }

    /// Switches the notification of each parcel an email adds on or off. The server answers 404 when the account has no
    /// address.
    func setAddByEmailNotify(_ notify: Bool) async throws -> AddByEmailResponse {
        let body = try JSONEncoder.deliveryTracker.encode(["notify": notify])
        return try AddByEmailOffer.decode(try await rawRequest("/api/account/inbox", method: "PATCH", body: body).0)
    }

    /// Turns adding by email off: emails to the address are dropped. The answer has no body.
    func closeAddByEmail() async throws {
        _ = try await rawRequest("/api/account/inbox", method: "DELETE")
    }

    func deleteAccount(confirmation: String, appleAuthorizationCode: String? = nil) async throws {
        let _: OKResponse = try await request(
            "/api/account",
            method: "DELETE",
            body: DeleteAccountRequest(confirmation: confirmation, appleAuthorizationCode: appleAuthorizationCode)
        )
    }

    private func request<T: Decodable & Sendable, Body: Encodable>(
        _ path: String,
        method: String = "GET",
        body: Body?
    ) async throws -> T {
        let encoded = try body.map { try JSONEncoder.deliveryTracker.encode($0) }
        let (data, _) = try await rawRequest(path, method: method, body: encoded)
        do {
            return try await Self.decode(T.self, from: data)
        } catch {
            throw DeliveryAPIError.invalidResponse
        }
    }

    private func request<T: Decodable & Sendable>(
        _ path: String,
        method: String = "GET"
    ) async throws -> T {
        try await request(path, method: method, body: Optional<String>.none)
    }

    /// A full parcel list with its histories is large; decode it off the main thread.
    @concurrent
    private nonisolated static func decode<T: Decodable & Sendable>(_ type: T.Type, from data: Data) async throws -> T {
        try JSONDecoder.deliveryTracker.decode(type, from: data)
    }

    /// `accepted`: a status besides 2xx that the caller reads itself, such as a 404 that means "not offered".
    private func performRawRequest(
        _ path: String,
        method: String = "GET",
        body: Data? = nil,
        accepting accepted: Int? = nil
    ) async throws -> (Data, HTTPURLResponse) {
        let generation = session.generation
        try session.checkGeneration(generation)
        guard session.user != nil else { throw DeliveryAPIError.authenticationExpired }
        guard let url = URL(string: path, relativeTo: configuration.apiBaseURL)?.absoluteURL else {
            throw DeliveryAPIError.invalidResponse
        }

        let send: NativeVerification.Send = { [transport] request in
            let (data, response): (Data, URLResponse)
            do { (data, response) = try await transport.data(for: request) } catch { throw Maintenance.failure(error) }
            guard let http = response as? HTTPURLResponse else { throw DeliveryAPIError.invalidResponse }
            return (data, http)
        }
        let signs = Self.signsWithAppAttest(path, method: method)

        func perform(token: String?) async throws -> (Data, HTTPURLResponse) {
            try session.checkGeneration(generation)
            var request = URLRequest(url: url)
            request.httpMethod = method
            request.httpBody = body
            request.cachePolicy = .reloadIgnoringLocalAndRemoteCacheData
            request.timeoutInterval = 30
            request.setValue("XMLHttpRequest", forHTTPHeaderField: "X-Requested-With")
            request.setValue("application/json", forHTTPHeaderField: "Accept")
            if body != nil { request.setValue("application/json", forHTTPHeaderField: "Content-Type") }
            if let token { request.setValue("Bearer \(token)", forHTTPHeaderField: "Authorization") }
            let result = signs
                ? try await verification.sendSignedIn(request, baseURL: configuration.apiBaseURL, using: send)
                : try await send(request)
            try session.checkGeneration(generation)
            // An App Attest refusal says nothing about the session, so it never signs the user out.
            if NativeVerification.needsVerification(result.1) { throw NativeVerificationFailed() }
            return result
        }

        var token = try await session.accessToken()
        try session.checkGeneration(generation)
        var result = try await perform(token: token)
        // Only the service's 401 is about the session: its own 403s ask for the device's check, handled above, and a
        // 403 from the network in front of it, such as a firewall's page, says nothing about the sign-in.
        if result.1.statusCode == 401 {
            token = try await session.accessToken(forceRefresh: true)
            try session.checkGeneration(generation)
            if token != nil { result = try await perform(token: token) }
        }
        try session.checkGeneration(generation)
        if result.1.statusCode == 401 {
            session.forceSignOut()
            throw DeliveryAPIError.authenticationExpired
        }
        guard (200..<300).contains(result.1.statusCode) || result.1.statusCode == accepted else {
            if let down = Maintenance.serverDown(result.0, result.1) { throw down }
            let error = try? JSONDecoder().decode(ErrorResponse.self, from: result.0)
            if result.1.statusCode == 429 {
                if let error, Self.allowanceUsed(error) { throw DeliveryAPIError.allowanceUsed }
                throw DeliveryAPIError.rateLimited(Self.retryAfterSeconds(
                    result.1.value(forHTTPHeaderField: "Retry-After")
                ))
            }
            if result.1.statusCode == 409, let packageID = error?.packageID {
                throw DeliveryAPIError.duplicateTracking(packageID)
            }
            if let message = error?.error { throw DeliveryAPIError.service(message) }
            throw DeliveryAPIError.serviceFailed(result.1.statusCode)
        }
        return result
    }

    /// Whether a refusal is the day's allowance used up, which lasts until tomorrow, as the web's `userErrorKey` tells it.
    static func allowanceUsed(_ error: ErrorResponse) -> Bool {
        error.scope == .daily || error.error.range(of: "allowance is used up", options: .caseInsensitive) != nil
    }

    /// Requests that have carriers asked or checks queued; the server counts their App Attest signatures.
    static func signsWithAppAttest(_ path: String, method: String) -> Bool {
        guard method == "POST" else { return false }
        let parts = path.split(separator: "/")
        return ["/api/packages", "/api/carriers/detect", "/api/sync"].contains(path)
            || (parts.count == 4 && parts[0] == "api" && parts[1] == "packages" && UUID(uuidString: String(parts[2])) != nil && parts[3] == "sync")
    }

    private func rawRequest(_ path: String, method: String = "GET", body: Data? = nil, accepting accepted: Int? = nil) async throws -> (Data, HTTPURLResponse) {
        let event = AnalyticsCatalog.bundled?.event(path: path, method: method, body: body)
        do {
            let result = try await performRawRequest(path, method: method, body: body, accepting: accepted)
            if let event { DeliveryAnalytics.shared.action(event, result.1.statusCode == 202 ? .accepted : .success) }
            return result
        } catch {
            if let event, !error.isCancellation { DeliveryAnalytics.shared.action(event, .error) }
            throw error
        }
    }

    nonisolated static func retryAfterSeconds(_ value: String?, now: Date = Date()) -> TimeInterval {
        guard let value else { return 0 }
        if let seconds = TimeInterval(value), seconds.isFinite { return max(0, seconds) }
        let formatter = DateFormatter()
        formatter.locale = Locale(identifier: "en_US_POSIX")
        formatter.timeZone = TimeZone(secondsFromGMT: 0)
        formatter.dateFormat = "EEE, dd MMM yyyy HH:mm:ss zzz"
        return max(0, formatter.date(from: value)?.timeIntervalSince(now) ?? 0)
    }
}


/// Contains only a capability to DELETE one registration, never login credentials.
/// Persist before sending registration so even an interrupted response is recoverable.
@MainActor
final class LiveActivityRevocations {
    struct Registration: Codable, Equatable {
        let ownerID: UUID
        let installationID: UUID
        let revocationToken: String
        var pending: Bool
    }

    private let persistence: any SessionPersistence
    private let configuration: AppConfiguration
    private let transport: URLSession
    private var registrations: [Registration]
    private var retryTask: Task<Void, Never>?

    init(configuration: AppConfiguration, transport: URLSession = .shared,
         persistence: any SessionPersistence = KeychainStore(service: "com.plhery.PeekDeliveryTracker.liveActivityRevocations")) {
        self.configuration = configuration
        self.transport = transport
        self.persistence = persistence
        registrations = persistence.load() ?? []
    }

    func registration(ownerID: UUID, installationID: UUID) throws -> Registration {
        if let existing = registrations.first(where: { !$0.pending && $0.ownerID == ownerID && $0.installationID == installationID }) {
            return existing
        }
        let token = (UUID().uuidString + UUID().uuidString).replacingOccurrences(of: "-", with: "").lowercased()
        let value = Registration(ownerID: ownerID, installationID: installationID, revocationToken: token, pending: false)
        try persist(registrations + [value])
        return value
    }

    func queue(except ownerID: UUID? = nil) throws {
        let queued = registrations.map { value in
            var value = value
            if value.ownerID != ownerID { value.pending = true }
            return value
        }
        // Polls with Live Activities off ask again every time; skip the Keychain write.
        guard queued != registrations else { return }
        try persist(queued)
    }

    private func persist(_ values: [Registration]) throws {
        try persistence.save(values)
        registrations = values
    }

    func drain() async {
        for value in registrations where value.pending {
            if Task.isCancelled { return }
            do {
                var request = URLRequest(url: configuration.apiBaseURL.appending(path: "api/live-activities/revoke"))
                request.httpMethod = "POST"
                request.timeoutInterval = 10
                request.cachePolicy = .reloadIgnoringLocalAndRemoteCacheData
                request.setValue("application/json", forHTTPHeaderField: "Content-Type")
                request.httpBody = try JSONEncoder.deliveryTracker.encode(RevokeLiveActivityDeviceRequest(
                    installationID: value.installationID, revocationToken: value.revocationToken
                ))
                let (_, response) = try await transport.data(for: request)
                guard let response = response as? HTTPURLResponse, (200...299).contains(response.statusCode) else { continue }
                try persist(registrations.filter { $0.revocationToken != value.revocationToken })
            } catch { /* Keep the durable revocation for a later network opportunity. */ }
        }
    }

    func retry() {
        guard retryTask == nil, registrations.contains(where: \.pending) else { return }
        retryTask = Task { [weak self] in
            while let self, !Task.isCancelled {
                await self.drain()
                if !self.registrations.contains(where: \.pending) { break }
                do { try await Task.sleep(for: .seconds(30)) } catch { break }
            }
            self?.retryTask = nil
        }
    }
}

extension NativePushEnvironment {
    /// The APNs environment this build's tokens belong to.
    static var current: NativePushEnvironment {
        #if DEBUG
        .development
        #else
        .production
        #endif
    }
}

extension NativePushLocale {
    /// The language notifications are written in: the app's own.
    init(_ language: AppLanguage) {
        self = NativePushLocale(rawValue: language.rawValue) ?? .en
    }
}
