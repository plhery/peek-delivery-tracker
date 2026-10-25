import SwiftUI
#if os(iOS)
import UIKit
#endif

/// The menu bar of an iPad and of the Mac. Its menus list the keys the screens answer, and the
/// screen in front says what it can do now, so an item is dimmed where its key would do nothing. The screens keep
/// their own key buttons (`KeyboardShortcuts`) as well: a phone has no menu bar, and where both answer a key they
/// do the same thing.
enum MenuBar {
    /// Whether the screens tell the menu bar what they can do. A phone's menus stay dimmed and out of the way.
    static let shown = DeviceIdiom.isPad
}

/// The keys the menu bar lists, as Mail has them, and the screens' buttons answer.
enum MenuKey {
    static let account = KeyboardShortcut(",", modifiers: .command)
    static let add = KeyboardShortcut("n", modifiers: .command)
    static let find = KeyboardShortcut("f", modifiers: .command)
    static let refresh = KeyboardShortcut("r", modifiers: .command)
    /// ⌘1, ⌘2 and ⌘3: Deliveries, Passport and Friends, in the tab bar's order.
    static func tab(_ tab: Int) -> KeyboardShortcut {
        KeyboardShortcut(KeyEquivalent(Character(String(tab + 1))), modifiers: .command)
    }
}

/// What the deliveries can do now, published while their keys reach them. A missing action dims its item.
struct DeliveriesMenu {
    var add: (() -> Void)?
    var find: (() -> Void)?
    var refresh: (() -> Void)?
}

/// The tabs, published while nothing stands over them.
struct TabsMenu {
    let choose: (Int) -> Void
}

/// The account, published by the screen in front.
struct AccountMenu {
    let open: () -> Void
}

/// The parcel the keys act on, beside the list or over it: its page's ••• items.
struct ParcelMenu {
    let items: [[ParcelMenuItem]]
    /// Delete waits while another change is saved.
    var busy = false
    let choose: (ParcelMenuItem) -> Void

    /// The card's items without Open, as the page in front is open already. A page shares with its own button.
    static func items(for parcel: Parcel, reader: Bool) -> [[ParcelMenuItem]] {
        ParcelMenuItem.menu(for: parcel, onCard: true, reader: reader).filter { $0 != [.open] }
    }

    /// What the Parcel menu shows, dimmed, while no parcel is in front.
    static let placeholder: [[ParcelMenuItem]] = [[.rename, .share, .copyTrackingNumber, .changeCarrier], [.archive], [.delete]]
}

extension FocusedValues {
    @Entry var tabsMenu: TabsMenu?
    @Entry var deliveriesMenu: DeliveriesMenu?
    @Entry var accountMenu: AccountMenu?
    @Entry var parcelMenu: ParcelMenu?
}

/// Account in the app's menu (the Settings window on a Mac), Add Parcel in File, Search in Edit, the tabs and Refresh
/// in View, and a Parcel menu with the page's ••• items. Undo, ↑, ↓, Return and Esc stay on the screens' buttons
/// everywhere.
struct PeekCommands: Commands {
    @ObservedObject var localizer: Localizer
    @FocusedValue(\.tabsMenu) private var tabs
    @FocusedValue(\.deliveriesMenu) private var deliveries
    @FocusedValue(\.accountMenu) private var account
    @FocusedValue(\.parcelMenu) private var parcel

    var body: some Commands {
        // A Mac keeps the system's Settings… item, which opens the Settings window.
        #if os(iOS)
        CommandGroup(replacing: .appSettings) {
            Button(localizer.text("native.account")) { account?.open() }
                .keyboardShortcut(MenuKey.account)
                .disabled(account == nil)
        }
        #endif
        CommandGroup(replacing: .newItem) {
            Button(localizer.text("app.addParcel")) { deliveries?.add?() }
                .keyboardShortcut(MenuKey.add)
                .disabled(deliveries?.add == nil)
        }
        CommandGroup(replacing: .textEditing) {
            Button(localizer.text("view.search")) { deliveries?.find?() }
                .keyboardShortcut(MenuKey.find)
                .disabled(deliveries?.find == nil)
        }
        CommandGroup(before: .toolbar) {
            ForEach(0..<3, id: \.self) { tab in
                Button(tabTitle(tab)) { tabs?.choose(tab) }
                    .keyboardShortcut(MenuKey.tab(tab))
                    .disabled(tabs == nil)
            }
            Divider()
            Button(localizer.text("app.refresh")) { deliveries?.refresh?() }
                .keyboardShortcut(MenuKey.refresh)
                .disabled(deliveries?.refresh == nil)
        }
        CommandMenu(localizer.text("common.parcel")) {
            ForEach(Array((parcel?.items ?? ParcelMenu.placeholder).enumerated()), id: \.offset) { index, group in
                if index > 0 { Divider() }
                ForEach(group, id: \.self) { item in
                    Button(localizer.text(item.titleKey)) { parcel?.choose(item) }
                        .keyboardShortcut(item.shortcut)
                        .disabled(parcel == nil || (parcel?.busy == true && item == .delete))
                }
            }
        }
    }

    private func tabTitle(_ tab: Int) -> String {
        switch tab {
        case 0: localizer.text("native.deliveries")
        case 1: ExperimentalCopy(localizer: localizer).passport
        default: localizer.text("friends.title")
        }
    }
}
