import AppIntents
import Foundation

// Siri, Spotlight and Shortcuts read the titles, parameters and phrases below from Localizable.strings and
// AppShortcuts.strings, which `npm run ios:resources` writes from the `appIntents.` and `appShortcuts.` keys: change
// an English text here and in shared/locales together.

/// What Siri, Spotlight and Shortcuts offer as soon as Peek is installed.
struct PeekShortcuts: AppShortcutsProvider {
    static var appShortcuts: [AppShortcut] {
        AppShortcut(
            intent: NextDeliveryIntent(),
            phrases: ["Next delivery in \(.applicationName)", "When is my next delivery in \(.applicationName)"],
            shortTitle: "Next delivery",
            systemImageName: "box.truck"
        )
        AppShortcut(
            intent: AddParcelIntent(),
            phrases: ["Add a parcel to \(.applicationName)", "Track a parcel with \(.applicationName)"],
            shortTitle: "Add a parcel",
            systemImageName: "plus.circle"
        )
    }
}

/// Says which parcel comes next from what the widget last got, in the App Group: it opens nothing and asks no server.
struct NextDeliveryIntent: AppIntent {
    static let title: LocalizedStringResource = "Next delivery"
    static let description = IntentDescription("Tells you which parcel comes next, without opening Peek.")

    @MainActor
    func perform() async throws -> some IntentResult & ProvidesDialog {
        let store = DeliveryWidgetSharedStore(appGroupIdentifier: DeliveryWidgetSharedStore.appGroupIdentifier())
        let snapshot = store?.snapshot
        // The snapshot's language, which its parcels' details are written in, as the widget picks it; else the app's,
        // which the app's own defaults keep without an App Group too.
        let copy = ExtensionLocalizer(languageCode: snapshot?.languageCode ?? AppLanguage.current.rawValue)
        let answer = NextDeliveryAnswer.text(snapshot: snapshot, enabled: store?.isEnabled ?? true, copy: copy)
        return .result(dialog: "\(answer)")
    }
}

/// What Siri says for the next delivery: the first parcel on its way in the widget's order, its carrier and its detail,
/// and how many more are on the way. Without the widget's parcels, it asks for Peek.
@MainActor
enum NextDeliveryAnswer {
    static func text(snapshot: DeliveryWidgetSnapshot?, enabled: Bool, copy: ExtensionLocalizer) -> String {
        guard enabled, let snapshot else { return copy.text("siri.openPeek") }
        // The widget also keeps the parcels delivered in the last day, after those on their way.
        let onTheWay = snapshot.parcels.filter { $0.deliveredAt == nil }
        guard let next = onTheWay.first else { return copy.text("app.noneOnWay") }
        let more = onTheWay.count - 1
        let key: String
        if more == 0 {
            key = "siri.nextDelivery"
        } else {
            switch Localizer.countForm(Double(more), in: AppLanguage(rawValue: copy.languageCode) ?? .en) {
            case "one": key = "siri.nextDeliveryAndMore.one"
            case "few": key = "siri.nextDeliveryAndMore.few"
            default: key = "siri.nextDeliveryAndMore.many"
            }
        }
        return copy.text(key)
            .replacingOccurrences(of: "{{count}}", with: String(more))
            .replacingOccurrences(of: "{{carrier}}", with: next.carrier)
            .replacingOccurrences(of: "{{detail}}", with: next.detail)
            // Last, so that braces in a name the reader gave stay as written.
            .replacingOccurrences(of: "{{name}}", with: next.label)
    }
}

/// Opens Add with a tracking number, as the Share extension does: the number waits in the App Group and the deliveries
/// open Add with it. Nothing is added until the reader confirms there.
struct AddParcelIntent: AppIntent {
    static let title: LocalizedStringResource = "Add a parcel"
    static let description = IntentDescription(
        "Opens Peek with the tracking number ready to add. Nothing is added until you confirm."
    )

    /// Peek comes forward once the number is saved, at the end of `perform`.
    @available(iOS 26.0, macOS 26.0, *)
    static var supportedModes: IntentModes { .foreground(.deferred) }

    @Parameter(title: "Tracking number", requestValueDialog: "What’s the tracking number?")
    var trackingNumber: String

    @Parameter(title: "Name")
    var name: String?

    @MainActor
    func perform() async throws -> some IntentResult {
        guard !trackingNumber.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty else {
            throw $trackingNumber.needsValueError()
        }
        guard Self.save(trackingNumber: trackingNumber, name: name) else {
            throw ShortcutFailure(message: ExtensionLocalizer(languageCode: AppLanguage.current.rawValue).text("siri.addFailed"))
        }
        // Peek may be in front already, or come forward before `perform` runs where `openAppWhenRun` opens it.
        NotificationCenter.default.post(name: .didSaveSharedParcelDraft, object: nil)
        #if os(macOS)
        // With its window closed, Peek keeps running for the menu bar.
        TrackWithPeek.openWindow?(id: MacWindow.id)
        #endif
        return .result()
    }

    /// Leaves the number and its name for Add, without the spaces around them. False when the App Group can't keep it.
    static func save(
        trackingNumber: String, name: String?, defaults: UserDefaults? = ShareInbox.defaults, now: Date = Date()
    ) -> Bool {
        ShareInbox.save(SharedParcelDraft(
            label: name?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            trackingInput: trackingNumber.trimmingCharacters(in: .whitespacesAndNewlines),
            createdAt: now
        ), defaults: defaults)
    }
}

@available(*, deprecated)
extension AddParcelIntent {
    /// Before iOS 26 and macOS 26, which read `supportedModes` instead.
    static var openAppWhenRun: Bool { true }
}

/// A failure Siri and Shortcuts show as it is written.
struct ShortcutFailure: Error, CustomLocalizedStringResourceConvertible {
    let message: String

    var localizedStringResource: LocalizedStringResource { "\(message)" }
}

extension Notification.Name {
    /// An intent left a tracking number for Add while Peek runs.
    static let didSaveSharedParcelDraft = Notification.Name("SDTDidSaveSharedParcelDraft")
}
