import Foundation
import UserNotifications

enum NotificationPreset: String, CaseIterable, Identifiable {
    case all, important, deliveryDay

    var id: String { rawValue }

    var stages: [NotificationStage] {
        switch self {
        case .all:
            [.registered, .accepted, .inTransit, .customs, .exception, .outForDelivery,
             .failedAttempt, .readyForPickup, .delivered, .returned]
        case .important:
            [.customs, .exception, .outForDelivery, .failedAttempt, .readyForPickup, .delivered,
             .returned]
        case .deliveryDay:
            [.outForDelivery, .delivered]
        }
    }

    /// The website's words for the same three lists, as on a parcel link's alerts.
    var titleKey: String {
        switch self {
        case .all: "alerts.preset.all"
        case .important: "alerts.preset.important"
        case .deliveryDay: "alerts.preset.delivery"
        }
    }

    var descriptionKey: String {
        switch self {
        case .all: "notifications.preset.allDescription"
        case .important: "notifications.preset.importantDescription"
        case .deliveryDay: "notifications.preset.deliveryDayDescription"
        }
    }

    static func matching(_ stages: [NotificationStage]) -> NotificationPreset {
        let enabled = Set(stages)
        return allCases.first { Set($0.stages) == enabled } ?? .all
    }

    /// The same list as a link's alerts name it, which is what a guest's choice is sent as.
    var alertPreset: ParcelAlertPreset {
        switch self {
        case .all: .all
        case .important: .important
        case .deliveryDay: .delivery
        }
    }

    /// A guest's choice as Settings shows it: Important steps until one is saved.
    static func withoutAccount(_ chosen: ParcelAlertPreset?) -> NotificationPreset {
        switch chosen {
        case .all: .all
        case .delivery: .deliveryDay
        case .important, nil: .important
        }
    }
}

/// The events picked on the Notifications screen. The email switch saves on its own, so the draft holds no email.
struct NotificationPreferencesDraft: Equatable {
    var preset: NotificationPreset

    init(preferences: NotificationPreferences? = nil) {
        preset = preferences.map { NotificationPreset.matching($0.enabledStages) } ?? .all
    }

    func preferences(timezone: String) -> NotificationPreferences {
        NotificationPreferences(enabledStages: preset.stages, quietHoursStart: nil, quietHoursEnd: nil, timezone: timezone)
    }
}

/// The account's delivery email: a short email when a parcel is ready to collect and
/// when it is delivered, to the address it signs in with. There is none in the demo, or unless the
/// server says it can write to the account, and nothing about email shows then.
struct DeliveryEmail: Equatable {
    let address: String
    /// True when on, false when off or declined, nil while the account has never chosen.
    let choice: Bool?

    init?(isDemo: Bool, preferences: NotificationPreferences?, address: String?) {
        guard !isDemo, let preferences, preferences.emailAvailable == true,
              let address = address?.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty else { return nil }
        self.address = address
        choice = preferences.emailOnDelivery
    }

    var isOn: Bool { choice == true }

    /// While the email is on and the parcel is still on its way, the parcel's bell opens
    /// its alerts. Otherwise one tap mutes or unmutes its notifications. A held return is still
    /// on its way back, and keeps both, as the server keeps sending them, as on the web and Android.
    func bellOpensAlerts(for parcel: Parcel) -> Bool {
        isOn && (parcel.currentStage?.isFinal != true || parcel.heldReturn)
    }

    /// Offered once, on a delivered parcel, while the account has never chosen.
    func isOffered(on parcel: Parcel) -> Bool {
        choice == nil && parcel.isDelivered
    }

    /// What "See an example" opens: the example email in the app's language. Nil when the
    /// site is not a web address, which the in-app browser cannot open.
    static func exampleURL(site: URL, language: AppLanguage) -> URL? {
        guard let scheme = site.scheme?.lowercased(), scheme == "https" || scheme == "http", site.host != nil else { return nil }
        return site.appending(path: "email/example").appending(queryItems: [URLQueryItem(name: "lang", value: language.rawValue)])
    }
}

extension Parcel {
    /// Parcels saved or served before the email existed carry no mute for it.
    var isEmailMuted: Bool { emailMuted == true }

    /// The bell of a parcel whose alerts open in a sheet is struck through only when
    /// both its notifications and its email are off.
    var allAlertsMuted: Bool { notificationsMuted && isEmailMuted }
}

extension Localizer {
    /// Where the account's defaults are, as the alerts sheet and the offer name it:
    /// "Settings › Notifications".
    var deliveryUpdatesPlace: String {
        "\(text("settings.title")) › \(text("settings.deliveryUpdates"))"
    }
}

/// Where the device keeps its notification state.
enum NotificationDeviceKeys {
    /// The person turned this device's notifications off.
    static let optOut = "sdt.notificationsDeviceOptOut"
    /// The account that last turned them on (`NotificationDevicePolicy.belongs`).
    static let owner = "sdt.notificationsOwner.v1"
    /// A token the account that signed out could not take off the service (`ParcelStore.settleNativePushRevocation`).
    static let revocation = "sdt.notificationsRevocationPending.v1"
    /// The token last given to the account the notifications belong to, kept across launches: Apple may not have
    /// answered this launch yet when the app signs that account out on its own.
    static let forwarded = "sdt.notificationsForwardedToken.v1"
    static let demo = "sdt.demoNotificationsEnabled"
    static let optOutRepaired = "sdt.notificationsOptOutRepaired.v1"
    /// The person turned off the notifications of the parcels this device follows without an account, apart from an
    /// account's "off" (`optOut`).
    static let deviceAlertsOff = "sdt.deviceAlertsOff.v1"
    /// What every parcel followed without an account announces, chosen in Settings; none until one is.
    static let deviceAlertPreset = "sdt.deviceAlertPreset.v1"
}

enum NotificationDevicePolicy {
    static func isAuthorized(_ status: UNAuthorizationStatus) -> Bool {
        status == .authorized || status == .provisional
    }

    static func isEnabled(
        isDemo: Bool,
        status: UNAuthorizationStatus,
        optedOut: Bool,
        nativePushRegistered: Bool,
        demoNotificationsEnabled: Bool
    ) -> Bool {
        if isDemo { return demoNotificationsEnabled }
        return isAuthorized(status) && !optedOut && nativePushRegistered
    }

    static func shouldRegisterForRemoteNotifications(
        status: UNAuthorizationStatus,
        optedOut: Bool
    ) -> Bool {
        isAuthorized(status) && !optedOut
    }

    /// Whether the device's token may go to the account signed in: its notifications belong to the account that
    /// last turned them on here, or to nobody yet. Signing out, an expired session or a deleted account takes the
    /// token away but not that owner, so the same account signing in again gets its notifications back on its own,
    /// and another account asks for its own yes.
    static func belongs(to user: UUID?, owner: UUID?) -> Bool {
        guard let user else { return false }
        return owner == nil || owner == user
    }

    /// Whether the device's token is on its way to the account signed in, which leaves the invitation nothing to ask:
    /// the system lets it register, its notifications are this account's, and the last try to register it did not fail.
    static func isRegistering(status: UNAuthorizationStatus, user: UUID?, owner: UUID?, failed: Bool) -> Bool {
        isAuthorized(status) && belongs(to: user, owner: owner) && !failed
    }

    /// An owner no account has, so whoever signs in next is asked.
    static let nobody = UUID(uuid: (0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))

    /// Before the owner was kept, signing out, an expired session or a deleted account wrote the person's "off",
    /// and their next sign-in had no notifications and no prompt. Once, that "off" is taken back: the person's own
    /// off has always switched the demo's notifications off with it, which an account change never did, so a device
    /// without that mark gets its notifications back like a returning account, and one with it is asked again.
    static func repairAccountChangeOptOut(_ defaults: UserDefaults) {
        guard !defaults.bool(forKey: NotificationDeviceKeys.optOutRepaired) else { return }
        defaults.set(true, forKey: NotificationDeviceKeys.optOutRepaired)
        guard defaults.bool(forKey: NotificationDeviceKeys.optOut),
              defaults.string(forKey: NotificationDeviceKeys.owner) == nil else { return }
        defaults.set(false, forKey: NotificationDeviceKeys.optOut)
        if defaults.object(forKey: NotificationDeviceKeys.demo) as? Bool == false {
            defaults.set(nobody.uuidString, forKey: NotificationDeviceKeys.owner)
        }
    }
}

enum NotificationInvitationPolicy {
    /// Asked of an account and of a guest alike: a guest's parcels on their way get notifications too.
    static func shouldPresent(
        isDemo: Bool,
        hasParcels: Bool,
        status: UNAuthorizationStatus?,
        enabled: Bool,
        optedOut: Bool,
        dismissed: Bool,
        registering: Bool = false
    ) -> Bool {
        // `registering`: the device's notifications are this account's and its token is on its way, so there is nothing to ask.
        guard !isDemo, hasParcels, !enabled, !optedOut, !dismissed, !registering,
              let status else { return false }
        return status == .notDetermined || status == .authorized || status == .provisional
    }
}

/// "Not now" on the invitation, kept for each account, and for the parcels followed without one (nil).
enum NotificationInvitationPreference {
    private static func key(for userID: UUID?) -> String {
        "sdt.notificationInvitationDismissed.v1.\(userID?.uuidString ?? "device")"
    }

    static func isDismissed(for userID: UUID?, defaults: UserDefaults = .standard) -> Bool {
        // Preserve choices made in the previous full-screen onboarding.
        defaults.bool(forKey: "sdt.notificationOnboardingCompleted.v1")
            || defaults.bool(forKey: key(for: userID))
    }

    static func dismiss(for userID: UUID?, defaults: UserDefaults = .standard) {
        defaults.set(true, forKey: key(for: userID))
    }
}
