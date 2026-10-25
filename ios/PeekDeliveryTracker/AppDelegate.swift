#if os(iOS)
import UIKit
typealias PlatformApplication = UIApplication
typealias PlatformApplicationDelegate = UIApplicationDelegate
#else
import AppKit
typealias PlatformApplication = NSApplication
typealias PlatformApplicationDelegate = NSApplicationDelegate
#endif
import UserNotifications

extension Notification.Name {
    static let didReceiveAPNSToken = Notification.Name("SDTDidReceiveAPNSToken")
    static let didFailAPNSRegistration = Notification.Name("SDTDidFailAPNSRegistration")
    static let didOpenParcelNotification = Notification.Name("SDTDidOpenParcelNotification")
    static let didOpenLinkNotification = Notification.Name("SDTDidOpenLinkNotification")
    static let didOpenFriendNotification = Notification.Name("SDTDidOpenFriendNotification")
    static let didReceiveFriendActivity = Notification.Name("SDTDidReceiveFriendActivity")
}

@MainActor
final class AppDelegate: NSObject, PlatformApplicationDelegate, UNUserNotificationCenterDelegate {
    static private(set) var currentDeviceToken: String?
    static private(set) var pendingParcelID: UUID?
    static private(set) var pendingFriendID: UUID?
    /// A notification about a parcel followed through its link without an account, tapped before the app could open it.
    static private(set) var pendingLinkID: String?

    static func consumePendingFriendID() -> UUID? {
        defer { pendingFriendID = nil }
        return pendingFriendID
    }

    static func clearDeviceToken() {
        currentDeviceToken = nil
    }

    static func consumePendingParcelID() -> UUID? {
        defer { pendingParcelID = nil }
        return pendingParcelID
    }

    static func consumePendingLinkID() -> String? {
        defer { pendingLinkID = nil }
        return pendingLinkID
    }

    /// Has the deliveries list open the parcel, now or once it shows.
    static func openParcel(_ parcelID: UUID) {
        pendingParcelID = parcelID
        NotificationCenter.default.post(name: .didOpenParcelNotification, object: parcelID)
    }

    #if os(iOS)
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        UNUserNotificationCenter.current().delegate = self
        UNUserNotificationCenter.current().setBadgeCount(0)
        return true
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        UNUserNotificationCenter.current().setBadgeCount(0)
    }
    #else
    func applicationWillFinishLaunching(_ notification: Notification) {
        // One window: no window tabs, and none of their items in the View and Window menus.
        NSWindow.allowsAutomaticWindowTabbing = false
    }

    func applicationDidFinishLaunching(_ notification: Notification) {
        UNUserNotificationCenter.current().delegate = self
        UNUserNotificationCenter.current().setBadgeCount(0)
        // Opened at login, Peek waits in the menu bar. Its window is made and hidden, so the deliveries load as at
        // any launch.
        if MenuBarExtraSettings.shown && Self.launchedAtLogin {
            DispatchQueue.main.async { NSApp.hide(nil) }
        }
    }

    func applicationDidBecomeActive(_ notification: Notification) {
        UNUserNotificationCenter.current().setBadgeCount(0)
    }

    /// With Peek in the menu bar, closing the window leaves it running there.
    func applicationShouldTerminateAfterLastWindowClosed(_ sender: NSApplication) -> Bool {
        !MenuBarExtraSettings.shown
    }

    /// Every notification that reaches the running app, in front or not: one about a parcel brings the menu bar's
    /// deliveries up to date.
    func application(_ application: NSApplication, didReceiveRemoteNotification userInfo: [String: Any]) {
        if case .parcel = NativeRoute(remoteNotification: userInfo) {
            NotificationCenter.default.post(name: .didReceiveParcelNotification, object: nil)
        }
    }

    /// Whether the system opened Peek as a login item.
    private static var launchedAtLogin: Bool {
        #if DEBUG
        if UserDefaults.standard.bool(forKey: "sdt.debug.launchedAtLogin") { return true }
        #endif
        guard let event = NSAppleEventManager.shared().currentAppleEvent else { return false }
        return event.eventID == AEEventID(kAEOpenApplication)
            && event.paramDescriptor(forKeyword: AEKeyword(keyAEPropData))?.enumCodeValue == OSType(keyAELaunchedAsLogInItem)
    }
    #endif

    func application(_ application: PlatformApplication, didRegisterForRemoteNotificationsWithDeviceToken token: Data) {
        let value = token.map { String(format: "%02x", $0) }.joined()
        // Apple can rotate this opaque value. Keep only the current launch's
        // token in memory and forward every value received to the server.
        Self.currentDeviceToken = value
        NotificationCenter.default.post(name: .didReceiveAPNSToken, object: value)
    }

    func application(_ application: PlatformApplication, didFailToRegisterForRemoteNotificationsWithError error: Error) {
        NotificationCenter.default.post(name: .didFailAPNSRegistration, object: error)
    }

    func userNotificationCenter(
        _ center: UNUserNotificationCenter,
        willPresent notification: UNNotification
    ) async -> UNNotificationPresentationOptions {
        if case .friend = NativeRoute(remoteNotification: notification.request.content.userInfo) {
            NotificationCenter.default.post(name: .didReceiveFriendActivity, object: nil)
            return [.list]
        }
        return [.banner, .list, .sound]
    }

    func userNotificationCenter(
        _ center: UNUserNotificationCenter,
        didReceive response: UNNotificationResponse
    ) async {
        let payload = response.notification.request.content.userInfo
        if case .friend(let friendID) = NativeRoute(remoteNotification: payload) {
            Self.pendingFriendID = friendID
            NotificationCenter.default.post(name: .didOpenFriendNotification, object: friendID)
        }
        if case .parcel(let parcelID) = NativeRoute(remoteNotification: payload) {
            Self.openParcel(parcelID)
        }
        if case .link(let linkID) = NativeRoute(remoteNotification: payload) {
            Self.pendingLinkID = linkID
            NotificationCenter.default.post(name: .didOpenLinkNotification, object: linkID)
        }
    }
}
