import Foundation
#if os(iOS)
import UIKit
#endif
import SwiftUI

/// One catalog shared with the web app; only these names can leave the device.
struct AnalyticsCatalog: Decodable {
    struct Operation: Decodable { let method: String; let path: String; let event: String }
    let screens: [String]
    let actions: [String]
    let operations: [Operation]
    let friendActions: [String: String]

    static let bundled: AnalyticsCatalog? = {
        guard let url = Bundle.main.url(forResource: "Analytics", withExtension: "json"),
              let data = try? Data(contentsOf: url) else { return nil }
        return try? JSONDecoder().decode(AnalyticsCatalog.self, from: data)
    }()

    func event(path: String, method: String, body: Data?) -> String? {
        let path = String(path.prefix { $0 != "?" && $0 != "#" })
        if path == "/api/friends", method == "POST" {
            guard let body, let value = try? JSONSerialization.jsonObject(with: body) as? [String: Any],
                  let action = value["action"] as? String else { return nil }
            return friendActions[action]
        }
        return operations.first { $0.method == method && path.range(of: $0.path, options: .regularExpression) != nil }?.event
    }
}

/// The counting id (docs/ANALYTICS.md): a random UUID this device keeps to tell installs that
/// come back from one-time ones. It is sent alone to /api/public/seen, at most once a UTC day and again once the reader
/// signs in, and a new one replaces it after 13 months.
struct CountingID: Codable, Equatable {
    static let storageKey = "sdt.counting.v1"
    static let lifetimeMonths = 13

    let id: String
    /// The UTC day the id was made.
    let made: String
    /// The UTC day it was last counted, and whether the reader was signed in then.
    var sent: String?
    var signedIn: Bool?

    /// The id to send now, or nil when today was already counted signed in, or signed out and the reader still is.
    /// One 13 months old gives way to a new id.
    static func due(_ stored: CountingID?, now: Date, signedIn: Bool, newID: () -> String = { UUID().uuidString }) -> CountingID? {
        let today = day(now)
        if let stored, stored.sent == today, stored.signedIn == true || !signedIn { return nil }
        if let stored, !stored.expired(at: now) { return stored }
        return CountingID(id: newID(), made: today)
    }

    /// What to keep once the service answered 204.
    func counted(now: Date, signedIn: Bool) -> CountingID {
        var counted = self
        counted.sent = Self.day(now)
        counted.signedIn = signedIn
        return counted
    }

    /// The id kept in `defaults`, unless it is unreadable.
    static func stored(in defaults: UserDefaults) -> CountingID? {
        guard let data = defaults.data(forKey: storageKey),
              let value = try? JSONDecoder().decode(CountingID.self, from: data),
              UUID(uuidString: value.id) != nil, date(value.made) != nil else { return nil }
        return value
    }

    func save(in defaults: UserDefaults) {
        if let data = try? JSONEncoder().encode(self) { defaults.set(data, forKey: Self.storageKey) }
    }

    /// The UTC day of `date`, as yyyy-MM-dd.
    static func day(_ date: Date) -> String {
        let parts = utc.dateComponents([.year, .month, .day], from: date)
        return String(format: "%04d-%02d-%02d", parts.year ?? 0, parts.month ?? 0, parts.day ?? 0)
    }

    private static let utc: Calendar = {
        var calendar = Calendar(identifier: .gregorian)
        calendar.timeZone = .gmt
        return calendar
    }()

    private static func date(_ day: String) -> Date? {
        let parts = day.split(separator: "-").compactMap { Int($0) }
        guard parts.count == 3, let date = utc.date(from: DateComponents(year: parts[0], month: parts[1], day: parts[2])),
              Self.day(date) == day else { return nil }
        return date
    }

    private func expired(at now: Date) -> Bool {
        guard let made = Self.date(made),
              let end = Self.utc.date(byAdding: .month, value: Self.lifetimeMonths, to: made) else { return true }
        return now >= end
    }
}

@MainActor
final class DeliveryAnalytics {
    static let shared = DeliveryAnalytics()
    /// An install whose reader once turned statistics off keeps them off. No screen sets it.
    static let preferenceKey = "sdt.analytics.enabled"
    private let defaults: UserDefaults
    var enabled: Bool { defaults.object(forKey: Self.preferenceKey) as? Bool ?? true }

    init(defaults: UserDefaults = .standard) { self.defaults = defaults }

    struct Configuration: Decodable {
        let endpoint: URL
        let hostname: String
        let iosWebsite: String

        func valid(for origin: URL) -> Bool {
            endpoint.scheme == "https" && endpoint.host != nil && endpoint.user == nil && endpoint.password == nil
                && endpoint.path == "/api/send" && endpoint.query == nil && endpoint.fragment == nil
                && origin.scheme == "https" && hostname == origin.host && UUID(uuidString: iosWebsite) != nil
        }
    }
    enum Outcome: String { case success, error, started, accepted }
    enum Mode: String { case anonymous, demo, account }
    /// The device the app runs on: a Mac can also run the iPad app, so iOS asks first.
    enum Platform: String {
        case iphone, ipad, mac
        @MainActor static var current: Platform {
            #if os(macOS)
            return .mac
            #else
            if ProcessInfo.processInfo.isiOSAppOnMac { return .mac }
            return UIDevice.current.userInterfaceIdiom == .pad ? .ipad : .iphone
            #endif
        }
        var device: String {
            switch self { case .iphone: "mobile"; case .ipad: "tablet"; case .mac: "desktop" }
        }
        /// The platform the counting id is sent with.
        var sighting: InstallSightingPlatform {
            switch self { case .iphone: .iphone; case .ipad: .ipad; case .mac: .mac }
        }
    }
    struct Event {
        let screen: String
        let name: String?
        let outcome: Outcome?
        let mode: Mode
    }
    private let transport = URLSession(configuration: .ephemeral)
    private var configuration: Configuration?
    private var starting = false
    private var disabled = false
    private var started = false
    private var sending = false
    private var cache: String?
    private var queue: [Event] = []
    private(set) var screen = "welcome"
    private var mode: Mode = .anonymous
    private var lastView = ""
    /// The service the configuration came from, which counts the install too.
    private var origin: URL?
    private var counting = false
    /// The parcels this device follows without an account, read when the install is counted.
    var followedHere: @MainActor () -> Int = { 0 }

    func start(configuration app: AppConfiguration = .current) async {
        guard enabled, !starting, !started else { return }
        // A Mac's Debug build is its simulator: test runs stay out of the counts.
        #if targetEnvironment(simulator) || (os(macOS) && DEBUG)
        disabled = true
        queue.removeAll()
        return
        #else
        guard app.mode == .api else { disabled = true; queue.removeAll(); return }
        starting = true
        defer { starting = false }
        do {
            let request = URLRequest(url: app.apiBaseURL.appending(path: "api/analytics/config"),
                                     cachePolicy: .reloadIgnoringLocalCacheData, timeoutInterval: 5)
            let (data, response) = try await transport.data(for: request)
            guard (response as? HTTPURLResponse)?.statusCode == 200,
                  let value = try JSONDecoder().decode(Configuration?.self, from: data),
                  value.valid(for: app.apiBaseURL) else { disabled = true; queue.removeAll(); return }
            configuration = value
            origin = app.apiBaseURL
            disabled = false
            started = true
            action("app-open")
            flush()
        } catch { queue.removeAll() /* Try configuration again on next foreground. */ }
        #endif
    }

    func view(_ next: String, mode nextMode: Mode? = nil) {
        guard AnalyticsCatalog.bundled?.screens.contains(next) == true else { return }
        screen = next
        if let nextMode { mode = nextMode }
        let key = "\(mode.rawValue):\(next)"
        guard key != lastView else { return }
        lastView = key
        enqueue(Event(screen: screen, name: nil, outcome: nil, mode: mode))
    }

    func action(_ name: String, _ outcome: Outcome? = nil) {
        guard AnalyticsCatalog.bundled?.actions.contains(name) == true else { return }
        enqueue(Event(screen: screen, name: name, outcome: outcome, mode: mode))
    }

    private func enqueue(_ event: Event) {
        guard enabled, !disabled, queue.count < 30 else { return }
        queue.append(event)
        flush()
    }

    /// No account IDs, device IDs, tokens, query strings, referrers or user-entered text.
    static func payload(_ event: Event, configuration: Configuration, platform: Platform? = nil) -> [String: Any] {
        let platform = platform ?? .current
        var properties = ["platform": platform.rawValue, "mode": event.mode.rawValue]
        if let outcome = event.outcome { properties["outcome"] = outcome.rawValue }
        var result: [String: Any] = [
            "website": configuration.iosWebsite, "hostname": configuration.hostname,
            "url": "/\(event.screen)", "title": event.screen,
            "language": Locale.preferredLanguages.first ?? "en",
            "browser": "Delivery iOS", "os": "iOS", "device": platform.device, "data": properties
        ]
        if let name = event.name { result["name"] = name }
        return result
    }

    private func flush() {
        guard !sending, configuration != nil else { return }
        sending = true
        Task {
            defer { sending = false }
            while enabled, !queue.isEmpty, let configuration {
                let event = queue.removeFirst()
                // A view knows whether the reader is signed in; the app-open queued before it may not.
                if event.name == nil, event.mode != .demo { count(signedIn: event.mode == .account) }
                do {
                    var request = URLRequest(url: configuration.endpoint, cachePolicy: .reloadIgnoringLocalCacheData, timeoutInterval: 5)
                    request.httpMethod = "POST"
                    request.setValue("application/json", forHTTPHeaderField: "Content-Type")
                    // Umami filters bare app user agents as bots. Keep the app suffix
                    // on a standard Safari agent; explicit payload fields identify native.
                    #if os(macOS)
                    // Safari on the Mac still names macOS 10_15_7, whatever the version.
                    request.setValue("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.0 Safari/605.1.15 DeliveryTracker/\(AppConfiguration.version)", forHTTPHeaderField: "User-Agent")
                    #else
                    let osVersion = UIDevice.current.systemVersion.replacingOccurrences(of: ".", with: "_")
                    request.setValue("Mozilla/5.0 (iPhone; CPU iPhone OS \(osVersion) like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.0 Mobile/15E148 Safari/604.1 DeliveryTracker/\(AppConfiguration.version)", forHTTPHeaderField: "User-Agent")
                    #endif
                    if let cache { request.setValue(cache, forHTTPHeaderField: "x-umami-cache") }
                    request.httpBody = try JSONSerialization.data(withJSONObject: ["type": "event", "payload": Self.payload(event, configuration: configuration)])
                    let (data, response) = try await transport.data(for: request)
                    if (response as? HTTPURLResponse)?.statusCode == 200,
                       let value = try? JSONSerialization.jsonObject(with: data) as? [String: Any] {
                        cache = value["cache"] as? String
                    }
                } catch { /* Drop failures; never block delivery actions or persist analytics. */ }
            }
        }
    }
}

extension DeliveryAnalytics {
    /// The request that counts this install: the sighting alone, without cookies or credentials.
    static func sightingRequest(_ sighting: InstallSighting, origin: URL) throws -> URLRequest {
        var request = URLRequest(url: origin.appending(path: "api/public/seen"), cachePolicy: .reloadIgnoringLocalCacheData, timeoutInterval: 5)
        request.httpMethod = "POST"
        request.httpShouldHandleCookies = false
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try JSONEncoder().encode(sighting)
        return request
    }

    /// Counts this install as seen today, unless it already was with the same sign-in state. A failed request leaves
    /// the day uncounted, for the next launch.
    private func count(signedIn: Bool, now: Date = Date()) {
        guard enabled, !counting, let origin,
              let due = CountingID.due(CountingID.stored(in: defaults), now: now, signedIn: signedIn) else { return }
        let sighting = InstallSighting(id: due.id, platform: Platform.current.sighting, signedIn: signedIn, parcels: followedHere())
        guard let request = try? Self.sightingRequest(sighting, origin: origin) else { return }
        counting = true
        // Keep the id even if the request fails, so the next attempt counts the same install.
        due.save(in: defaults)
        Task {
            defer { counting = false }
            guard let (_, response) = try? await transport.data(for: request),
                  (response as? HTTPURLResponse)?.statusCode == 204 else { return }
            due.counted(now: now, signedIn: signedIn).save(in: defaults)
        }
    }
}

/// Keep telemetry observation separate from the main app's lifecycle expressions.
struct AnalyticsLifecycleObserver: View {
    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var invitation: FriendInvitationStore
    @EnvironmentObject private var links: ParcelLinkStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.scenePhase) private var phase
    @EnvironmentObject private var parcels: ParcelStore

    private var mode: DeliveryAnalytics.Mode {
        if session.isDemo { return .demo }
        return session.user == nil ? .anonymous : .account
    }
    /// The screen a parcel link's sheet covers, to return to when it closes.
    @State private var beneathLink: String?

    private var screen: String {
        if invitation.isPresenting { return "invitation" }
        switch session.state {
        case .loading: return "welcome"
        // Nobody is signed in: the first screen, then the deliveries this iPhone follows itself.
        case .welcome: return parcels.parcels.isEmpty ? "front-door" : "deliveries"
        case .signedOut, .unconfigured: return "sign-in"
        case .demo, .signedIn: return "deliveries"
        }
    }
    var body: some View {
        Color.clear.frame(width: 0, height: 0)
            .task {
                DeliveryAnalytics.shared.followedHere = { [parcels] in parcels.followedHere }
                await DeliveryAnalytics.shared.start()
            }
            .onChange(of: screen + mode.rawValue, initial: true) { _, _ in
                if beneathLink == nil {
                    DeliveryAnalytics.shared.view(screen, mode: mode)
                } else {
                    beneathLink = screen
                    DeliveryAnalytics.shared.view("parcel-link", mode: mode)
                }
            }
            // A parcel link is one screen, whatever its address, over the screen it was opened on.
            .onChange(of: links.route != nil, initial: true) { _, open in
                if open {
                    beneathLink = DeliveryAnalytics.shared.screen
                    DeliveryAnalytics.shared.view("parcel-link", mode: mode)
                } else if let beneath = beneathLink {
                    beneathLink = nil
                    DeliveryAnalytics.shared.view(beneath, mode: mode)
                }
            }
            .onChange(of: phase) { _, phase in
                if phase == .active {
                    DeliveryAnalytics.shared.action("app-foreground")
                    Task { await DeliveryAnalytics.shared.start() }
                }
            }
            .onChange(of: localizer.language) { _, _ in DeliveryAnalytics.shared.action("language-change") }
            .onOpenURL { _ in DeliveryAnalytics.shared.action("deep-link-open") }
            .onReceive(NotificationCenter.default.publisher(for: .didOpenFriendNotification)) { _ in
                DeliveryAnalytics.shared.action("notification-open")
            }
    }
}
