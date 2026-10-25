#if os(macOS)
import AppKit
import SwiftUI

/// Peek in the menu bar: the next deliveries and what to do with them, whether the window is open or not.
enum MenuBarExtraSettings {
    /// Whether the menu bar shows Peek: on until the person turns it off. While it does, closing the window leaves
    /// Peek running there.
    static let shownKey = "sdt.menuBar.shown.v1"
    static var shown: Bool { UserDefaults.standard.object(forKey: shownKey) as? Bool ?? true }

    /// The parcels it lists, the most pressing first.
    static let parcelLimit = 5

    /// How often it reads the deliveries again while nothing else does.
    static let refreshInterval: Duration = .seconds(15 * 60)

    /// The eyes of the mark, in the menu bar's colour.
    static let icon: NSImage = {
        let bounds = PeekMarkArtwork.eyesBounds
        let scale = 12 / bounds.height
        let size = CGSize(width: (bounds.width * scale).rounded(.up), height: 12)
        let image = NSImage(size: size, flipped: true) { _ in
            guard let context = NSGraphicsContext.current?.cgContext else { return false }
            context.scaleBy(x: scale, y: scale)
            context.translateBy(x: -bounds.minX, y: -bounds.minY)
            PeekMarkArtwork.drawEyes(in: context)
            return true
        }
        image.isTemplate = true
        image.accessibilityDescription = "Peek"
        return image
    }()
}

/// What the menu bar asks of the deliveries in the window: a parcel to show, or Add.
enum MenuBarRequest: Equatable {
    case parcel(UUID)
    case add(FirstParcelRequest)
}

extension Notification.Name {
    static let didMakeMenuBarRequest = Notification.Name("SDTDidMakeMenuBarRequest")
    static let didReceiveParcelNotification = Notification.Name("SDTDidReceiveParcelNotification")
}

/// The menu bar's request, kept until the deliveries take it: the window may have to open first.
@MainActor
enum MenuBarRequests {
    private static var pending: MenuBarRequest?

    static var isPending: Bool { pending != nil }

    static func send(_ request: MenuBarRequest) {
        pending = request
        NotificationCenter.default.post(name: .didMakeMenuBarRequest, object: nil)
    }

    static func consume() -> MenuBarRequest? {
        defer { pending = nil }
        return pending
    }
}

extension ParcelStore {
    /// Keeps the menu bar's deliveries current, with the window closed too: they are read again every 15 minutes and
    /// when a parcel's notification comes in, from the list the window reads. That asks no carrier, so it spends none
    /// of the signed checks.
    func keepMenuBarCurrent() {
        Task { [weak self] in
            while !Task.isCancelled {
                try? await Task.sleep(for: MenuBarExtraSettings.refreshInterval)
                guard let self else { return }
                if MenuBarExtraSettings.shown { await self.load() }
            }
        }
        Task { [weak self] in
            for await _ in NotificationCenter.default.notifications(named: .didReceiveParcelNotification) {
                guard let self else { return }
                if MenuBarExtraSettings.shown { await self.load() }
            }
        }
    }
}

/// What opens from Peek's place in the menu bar: up to five parcels on their way, as the widget orders them, then
/// Paste, Add and Refresh, then the window, Settings and Quit.
struct MenuBarPanel: View {
    @EnvironmentObject private var store: ParcelStore
    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.openWindow) private var openWindow
    @Environment(\.openSettings) private var openSettings
    @Environment(\.dismiss) private var dismiss
    @State private var refreshError: String?

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            deliveries
            Divider().padding(.vertical, 5).padding(.horizontal, 9)
            Group {
                command(localizer.text("menuBar.paste"), shortcut: KeyboardShortcut("v", modifiers: .command), action: paste)
                command(localizer.text("app.addParcel"), shortcut: MenuKey.add) { open(.add(.type)) }
            }
            .disabled(!followsParcels)
            command(localizer.text("app.refresh"), shortcut: MenuKey.refresh, busy: store.refreshing, action: refresh)
                .disabled(!followsParcels || store.refreshing)
            if let refreshError {
                Text(refreshError)
                    .font(.caption)
                    .foregroundStyle(Brand.warning)
                    .fixedSize(horizontal: false, vertical: true)
                    .padding(.horizontal, 9)
                    .padding(.vertical, 3)
            }
            Divider().padding(.vertical, 5).padding(.horizontal, 9)
            command(localizer.text("menuBar.open"), action: showWindow)
            command(localizer.text("menuBar.settings"), shortcut: MenuKey.account) {
                dismiss()
                NSApp.activate()
                openSettings()
            }
            command(localizer.text("menuBar.quit"), shortcut: KeyboardShortcut("q", modifiers: .command)) {
                NSApp.terminate(nil)
            }
        }
        // As tall as its lines: the panel's window follows it rather than squeezing them.
        .fixedSize(horizontal: false, vertical: true)
        .padding(5)
        .frame(width: 300)
        // Each time it opens: the list, as the window reads it.
        .onAppear { Task { await store.load() } }
    }

    /// The parcels it lists: those out for delivery first, as the widget picks them, then the others, the most
    /// pressing first.
    private var parcels: ArraySlice<DeliveryWidgetParcel> {
        let next = store.nextDeliveries
        return (next.filter(\.isOutForDelivery) + next.filter { !$0.isOutForDelivery }).prefix(MenuBarExtraSettings.parcelLimit)
    }

    /// Someone signed in, the demo, or the parcels this Mac follows without an account.
    private var followsParcels: Bool { session.isAuthenticated || session.isGuest }

    /// The window asks to sign in.
    private var signedOut: Bool {
        switch session.state {
        case .signedOut, .unconfigured: true
        default: false
        }
    }

    @ViewBuilder private var deliveries: some View {
        if followsParcels {
            Text(localizer.text("app.onTheWaySection"))
                .font(.system(size: 11, weight: .semibold))
                .foregroundStyle(.secondary)
                .padding(.horizontal, 9)
                .padding(.top, 3)
                .padding(.bottom, 4)
                .accessibilityAddTraits(.isHeader)
            if parcels.isEmpty {
                Text(localizer.text("app.noneOnWay"))
                    .foregroundStyle(.secondary)
                    .padding(.horizontal, 9)
                    .padding(.vertical, 3)
            }
            ForEach(parcels) { parcel in
                MenuBarItem(action: { open(.parcel(parcel.id)) }) {
                    HStack(spacing: 8) {
                        Image(systemName: parcel.isOutForDelivery ? "box.truck.fill" : "shippingbox")
                            .foregroundStyle(.secondary)
                            .frame(width: 20)
                        VStack(alignment: .leading, spacing: 1) {
                            Text(parcel.label).lineLimit(1)
                            Text([parcel.detail, parcel.carrier].joined(separator: " · "))
                                .font(.caption)
                                .foregroundStyle(.secondary)
                                .lineLimit(1)
                        }
                    }
                    .padding(.vertical, 2)
                }
            }
        } else if signedOut {
            command(localizer.text("arrival.signInTitle"), action: showWindow)
        }
    }

    private func command(
        _ title: String,
        shortcut: KeyboardShortcut? = nil,
        busy: Bool = false,
        action: @escaping () -> Void
    ) -> some View {
        MenuBarItem(shortcut: shortcut, action: action) {
            HStack {
                Text(title)
                Spacer(minLength: 16)
                if busy {
                    ProgressView().controlSize(.mini)
                } else if let shortcut {
                    Text(shortcut.glyphs).foregroundStyle(.secondary)
                }
            }
        }
    }

    /// Paste reads the clipboard once it is clicked, as the window's Paste does, and opens Add with what it holds.
    private func paste() {
        DeliveryAnalytics.shared.action("parcel-paste")
        open(.add(.paste(Clipboard.string ?? "")))
    }

    private func refresh() {
        refreshError = nil
        Task {
            do {
                if case .completed(.failed(let message)) = try await store.refreshAll() { refreshError = message }
            } catch is CancellationError {
            } catch {
                refreshError = localizer.errorMessage(error)
            }
        }
    }

    private func open(_ request: MenuBarRequest) {
        MenuBarRequests.send(request)
        showWindow()
    }

    /// Closes the panel, as a menu closes once something is chosen, and brings the window forward.
    private func showWindow() {
        dismiss()
        openWindow(id: MacWindow.id)
        NSApp.activate()
    }
}

/// A line of the panel, which highlights under the pointer as a menu's item does.
private struct MenuBarItem<Label: View>: View {
    var shortcut: KeyboardShortcut?
    let action: () -> Void
    @ViewBuilder let label: () -> Label
    @Environment(\.isEnabled) private var isEnabled
    @State private var hovering = false

    var body: some View {
        let highlighted = hovering && isEnabled
        Button(action: action) {
            label()
                .padding(.horizontal, 9)
                .padding(.vertical, 3)
                .frame(maxWidth: .infinity, minHeight: 22, alignment: .leading)
                .foregroundStyle(highlighted ? AnyShapeStyle(Color.white) : isEnabled ? AnyShapeStyle(.primary) : AnyShapeStyle(.tertiary))
                .background(
                    highlighted ? Color(nsColor: .selectedContentBackgroundColor) : .clear,
                    in: RoundedRectangle(cornerRadius: 5, style: .continuous)
                )
                .contentShape(Rectangle())
        }
        .buttonStyle(.plain)
        .onHover { hovering = $0 }
        .keyboardShortcut(shortcut)
    }
}

private extension KeyboardShortcut {
    /// The shortcut as a menu writes it, such as ⌘R.
    var glyphs: String {
        var text = ""
        if modifiers.contains(.control) { text += "⌃" }
        if modifiers.contains(.option) { text += "⌥" }
        if modifiers.contains(.shift) { text += "⇧" }
        if modifiers.contains(.command) { text += "⌘" }
        return text + String(key.character).uppercased()
    }
}
#endif
