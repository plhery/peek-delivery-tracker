#if os(macOS)
import AppKit
import SwiftUI

// The screens are shared with iOS and use a few modifiers and names only iOS has. On the Mac these stand in
// for them: most do nothing, the others take the Mac's nearest equivalent.

/// The main window's smallest size. Wider than the list beside a parcel needs, so it never turns into the phone's
/// stack, and so that the tabs, the account, Add and the parcel's menu fit over the parcel without folding into ».
enum MacWindow {
    static let minimumWidth: CGFloat = 860
    static let minimumHeight: CGFloat = 560
}

/// How a page's title shows in the bar. A Mac window shows it one way.
enum TitleDisplayMode { case automatic, inline, large }

/// The keyboard a field brings up. A Mac's keyboard is always the same.
enum KeyboardType { case `default`, URL, numberPad, numbersAndPunctuation, emailAddress, asciiCapable }

/// How a field capitalizes what is typed. A Mac leaves it as typed.
enum AutocapitalizationMode { case never, words, sentences, characters }

/// The bars a page shows or hides on iOS that a Mac window has none of.
enum IOSBar { case navigationBar, tabBar }

extension View {
    func navigationBarTitleDisplayMode(_ mode: TitleDisplayMode) -> some View { self }

    func keyboardType(_ type: KeyboardType) -> some View { self }

    func textInputAutocapitalization(_ mode: AutocapitalizationMode?) -> some View { self }

    func toolbar(_ visibility: Visibility, for bars: IOSBar...) -> some View { self }

    /// A large sheet where iOS covers the screen: what it covers with (the map) has no size of its own.
    func fullScreenCover<Content: View>(
        isPresented: Binding<Bool>,
        onDismiss: (() -> Void)? = nil,
        @ViewBuilder content: @escaping () -> Content
    ) -> some View {
        sheet(isPresented: isPresented, onDismiss: onDismiss) {
            content().frame(minWidth: 720, idealWidth: 1000, minHeight: 520, idealHeight: 720)
        }
    }
}

extension ToolbarItemPlacement {
    /// The start of the window's toolbar.
    static var topBarLeading: ToolbarItemPlacement { .navigation }
    /// The end of the window's toolbar.
    static var topBarTrailing: ToolbarItemPlacement { .automatic }
}

extension SearchFieldPlacement {
    /// A search field in the window's toolbar, where iOS has it under the bar.
    static func navigationBarDrawer(displayMode: SearchFieldDisplayMode) -> SearchFieldPlacement { .toolbar }
}

/// Whether iOS keeps a search field showing as the page scrolls. A Mac's toolbar always shows it.
enum SearchFieldDisplayMode { case always, automatic }

extension NavigationTransition where Self == AutomaticNavigationTransition {
    /// A page opens as a Mac's pages do, without zooming out of what was clicked.
    static func zoom(sourceID: some Hashable, in namespace: Namespace.ID) -> AutomaticNavigationTransition { .automatic }
}

extension NSApplication {
    /// The Mac's nearest to going to the background: another app comes forward.
    nonisolated static let didEnterBackgroundNotification = NSApplication.didResignActiveNotification
}

extension Color {
    /// A system colour, by the name the shared views give UIKit's.
    init(uiColor color: NSColor) { self.init(nsColor: color) }
}

extension NSColor {
    static var secondaryLabel: NSColor { .secondaryLabelColor }
    static var tertiaryLabel: NSColor { .tertiaryLabelColor }
    static var separator: NSColor { .separatorColor }
}

extension Image {
    init(uiImage image: NSImage) { self.init(nsImage: image) }
}

/// What the Mac shows for now where iOS opens its share sheet: the item, the system's share menu and Done.
struct MacShareSheet: View {
    let url: URL
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text(verbatim: url.isFileURL ? url.lastPathComponent : url.absoluteString)
                .textSelection(.enabled)
                .lineLimit(3)
            HStack {
                Spacer()
                Button(localizer.text("native.done")) { dismiss() }
                    .keyboardShortcut(.cancelAction)
                ShareLink(item: url)
                    .keyboardShortcut(.defaultAction)
            }
        }
        .padding(20)
        .frame(minWidth: 360)
    }
}
#endif
