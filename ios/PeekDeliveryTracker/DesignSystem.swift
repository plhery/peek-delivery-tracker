import GameController
import SwiftUI
#if os(iOS)
import UIKit
typealias PlatformColor = UIColor
typealias PlatformFont = UIFont
typealias PlatformImage = UIImage
#else
typealias PlatformColor = NSColor
typealias PlatformFont = NSFont
typealias PlatformImage = NSImage
#endif

enum Brand {
    // Postal yellow anchors the interface; content surfaces adapt to appearance.
    static let accent = Color(hex: "#F3CF48")
    static let accentBright = accent
    static let onAccent = Color(hex: "#20251E")
    static let ink = color(light: "#20251E", dark: "#FAF6EB")
    /// The ink's quieter shade, as in the name's tagline: 4.5:1 or more on the page, the paper, the cream and the first
    /// open's card (`ContrastTests`).
    static let inkSoft = color(light: "#485242", dark: "#CEC6B6")
    /// Every quieter word's grey, in place of the system's secondary and placeholder greys, which read under 4.5:1 in
    /// light: it reads at 4.5:1 or more on the page, the paper, the cream, a sheet's grounds and every carrier's card
    /// (`ContrastTests`).
    static let secondaryText = color(light: "#606065", dark: "#BCBCC2")
    static let cream = color(light: "#F0F0E9", dark: "#292620")
    static let paper = color(light: "#FFFFFF", dark: "#1F1C17")
    /// The level above the paper: rows inside a sheet, menus and toasts (the web's `--raised`).
    static let raised = color(light: "#FFFFFF", dark: "#292620")
    static let warning = color(light: "#963E19", dark: "#FFB184")
    /// The tray behind a swiped card, like the web's soft orange.
    static let warningSoft = color(light: "#FFDDC7", dark: "#341F14")
    /// Peek's soft yellow, under the ink: the calm notice while the server is down (the web's `--yellow-soft`).
    static let yellowSoft = color(light: "#FFEBAB", dark: "#413410")
    static let background = color(light: "#F4F5F1", dark: "#13110E")
    static let separator = Color(uiColor: .separator)

    // A sheet stands one step up from the page in dark: a ground of its own under raised rows, with its own controls
    // and lines (the web's `--sheet-*`). The ground is no brighter than the old dark page, so its words keep their
    // contrast. In light each is the page's own colour.
    static let sheet = color(light: "#F4F5F1", dark: "#191612")
    /// A control in a sheet, where the page has the cream.
    static let sheetControl = color(light: "#F0F0E9", dark: "#36322C")
    /// A sheet's round buttons and its copy button: the ink at 7% in light, as on the page, and the control in dark.
    static let sheetButton = keeping(PlatformColor(ink), opacity: 0.07, dark: "#36322C", name: "ink")

    /// A line in a sheet: the system's separator at `opacity` in light, as on the page, and one solid line in dark.
    static func sheetLine(_ opacity: Double) -> Color {
        keeping(.separator, opacity: opacity, dark: "#4A463E", name: "separator")
    }

    /// Cards ask for carrier colors on every render. Returning the same dynamic
    /// color each time lets SwiftUI see that nothing changed.
    private static let dynamicColors = NSCache<NSString, PlatformColor>()

    /// `light` at `opacity` in light, resolved as the page resolves it, and `dark` in dark: a sheet keeps the page's
    /// translucent fills and lines in light and has solid ones in dark.
    private static func keeping(_ light: PlatformColor, opacity: Double, dark: String, name: String) -> Color {
        let key = "\(name)@\(opacity)|\(dark)" as NSString
        if let cached = dynamicColors.object(forKey: key) { return Color(uiColor: cached) }
        let darkColor = PlatformColor(Color(hex: dark))
        #if os(macOS)
        let dynamic = NSColor(name: nil) { appearance in
            let match = appearance.bestMatch(from: [
                .aqua, .darkAqua, .accessibilityHighContrastAqua, .accessibilityHighContrastDarkAqua,
            ])
            if match == .darkAqua { return darkColor }
            if match == .accessibilityHighContrastDarkAqua { return darkColor.usingColorSpace(.sRGB).map(contrasted) ?? darkColor }
            var resolved = light
            appearance.performAsCurrentDrawingAppearance { resolved = light.usingColorSpace(.sRGB) ?? light }
            return resolved.withAlphaComponent(resolved.alphaComponent * opacity)
        }
        #else
        let dynamic = UIColor { traits in
            guard traits.userInterfaceStyle == .dark else {
                let resolved = light.resolvedColor(with: traits)
                return resolved.withAlphaComponent(resolved.cgColor.alpha * opacity)
            }
            return traits.accessibilityContrast == .high ? contrasted(darkColor) : darkColor
        }
        #endif
        dynamicColors.setObject(dynamic, forKey: key)
        return Color(uiColor: dynamic)
    }

    static func color(light: String, dark: String) -> Color {
        let key = "\(light)|\(dark)" as NSString
        if let cached = dynamicColors.object(forKey: key) { return Color(uiColor: cached) }
        let lightColor = PlatformColor(Color(hex: light))
        let darkColor = PlatformColor(Color(hex: dark))
        #if os(macOS)
        let dynamic = NSColor(name: nil) { appearance in
            let match = appearance.bestMatch(from: [
                .aqua, .darkAqua, .accessibilityHighContrastAqua, .accessibilityHighContrastDarkAqua,
            ])
            let dark = match == .darkAqua || match == .accessibilityHighContrastDarkAqua
            let color = dark ? darkColor : lightColor
            guard match == .accessibilityHighContrastAqua || match == .accessibilityHighContrastDarkAqua,
                  let rgb = color.usingColorSpace(.sRGB) else { return color }
            return contrasted(rgb)
        }
        #else
        let dynamic = UIColor { traits in
            let color = traits.userInterfaceStyle == .dark ? darkColor : lightColor
            guard traits.accessibilityContrast == .high else { return color }
            return contrasted(color)
        }
        #endif
        dynamicColors.setObject(dynamic, forKey: key)
        return Color(uiColor: dynamic)
    }

    /// `ink` (as `color(light:dark:)` resolves it) where it reads at 4.5:1 or more on everything a card's map puts under
    /// words (`MapPalette.tint`): the card's `surface`, land (the ink at `MapPalette.landInk` on the card) and a country
    /// the parcel passed through (at `MapPalette.visitedInk` over that land), each under the ink at `backing` where the
    /// words have a backing of their own. Where it doesn't, it moves toward the label's black or white in steps of 10%,
    /// channel by channel, until it does.
    static func readableOnMap(ink: (light: String, dark: String), surface: (light: String, dark: String), backing: CGFloat = 0) -> Color {
        let key = "map|\(ink.light)|\(ink.dark)|\(surface.light)|\(surface.dark)|\(backing)" as NSString
        if let cached = dynamicColors.object(forKey: key) { return Color(uiColor: cached) }
        func shade(dark: Bool, high: Bool) -> PlatformColor {
            func resolved(_ hex: String) -> [CGFloat] {
                var color = PlatformColor(Color(hex: hex))
                #if os(macOS)
                color = color.usingColorSpace(.sRGB) ?? color
                #endif
                return channels(high ? contrasted(color) : color)
            }
            let shown = readableOnMap(resolved(dark ? ink.dark : ink.light), on: resolved(dark ? surface.dark : surface.light),
                                      label: dark ? 1 : 0, backing: backing)
            return PlatformColor(red: shown[0], green: shown[1], blue: shown[2], alpha: 1)
        }
        let (light, dark) = (shade(dark: false, high: false), shade(dark: true, high: false))
        let (lightHigh, darkHigh) = (shade(dark: false, high: true), shade(dark: true, high: true))
        #if os(macOS)
        let dynamic = NSColor(name: nil) { appearance in
            switch appearance.bestMatch(from: [.aqua, .darkAqua, .accessibilityHighContrastAqua, .accessibilityHighContrastDarkAqua]) {
            case .darkAqua: dark
            case .accessibilityHighContrastAqua: lightHigh
            case .accessibilityHighContrastDarkAqua: darkHigh
            default: light
            }
        }
        #else
        let dynamic = UIColor { traits in
            let high = traits.accessibilityContrast == .high
            return traits.userInterfaceStyle == .dark ? (high ? darkHigh : dark) : (high ? lightHigh : light)
        }
        #endif
        dynamicColors.setObject(dynamic, forKey: key)
        return Color(uiColor: dynamic)
    }

    /// `ink`'s sRGB channels, mixed toward `label` (0 or 1) by the first tenth that reads at 4.5:1 on the card and on
    /// its map's land and visited countries, each under the ink at `backing`.
    static func readableOnMap(_ ink: [CGFloat], on surface: [CGFloat], label: CGFloat, backing: CGFloat = 0) -> [CGFloat] {
        func over(_ ground: [CGFloat], _ opacity: CGFloat) -> [CGFloat] {
            zip(ink, ground).map { $0 * opacity + $1 * (1 - opacity) }
        }
        let land = over(surface, MapPalette.landInk)
        let grounds = [surface, land, over(land, MapPalette.visitedInk)].map { over($0, backing) }
        for tenth in 0...10 {
            let mixed = ink.map { $0 + (label - $0) * CGFloat(tenth) / 10 }
            if grounds.allSatisfy({ contrast(mixed, $0) >= 4.5 }) { return mixed }
        }
        return [label, label, label]
    }

    /// WCAG 2's contrast ratio of two opaque sRGB colours.
    static func contrast(_ first: [CGFloat], _ second: [CGFloat]) -> CGFloat {
        func luminance(_ rgb: [CGFloat]) -> CGFloat {
            let linear = rgb.map { $0 <= 0.04045 ? $0 / 12.92 : pow(($0 + 0.055) / 1.055, 2.4) }
            return 0.2126 * linear[0] + 0.7152 * linear[1] + 0.0722 * linear[2]
        }
        let (one, other) = (luminance(first), luminance(second))
        return (max(one, other) + 0.05) / (min(one, other) + 0.05)
    }

    private static func channels(_ color: PlatformColor) -> [CGFloat] {
        var red: CGFloat = 0, green: CGFloat = 0, blue: CGFloat = 0, alpha: CGFloat = 0
        color.getRed(&red, green: &green, blue: &blue, alpha: &alpha)
        return [red, green, blue]
    }

    /// A little further from the middle grey, for increased contrast.
    private static func contrasted(_ color: PlatformColor) -> PlatformColor {
        var red: CGFloat = 0, green: CGFloat = 0, blue: CGFloat = 0, alpha: CGFloat = 0
        color.getRed(&red, green: &green, blue: &blue, alpha: &alpha)
        let luminance = red * 0.2126 + green * 0.7152 + blue * 0.0722
        let adjustment: CGFloat = luminance > 0.5 ? 0.06 : -0.06
        return PlatformColor(
            red: min(1, max(0, red + adjustment)),
            green: min(1, max(0, green + adjustment)),
            blue: min(1, max(0, blue + adjustment)),
            alpha: alpha
        )
    }
}

enum AppAppearance: String, CaseIterable, Identifiable {
    case system, light, dark

    static let storageKey = "sdt.appearance.v1"
    static let defaultValue = AppAppearance.light
    var id: String { rawValue }
    var titleKey: String { "native.appearance.\(rawValue)" }
    #if os(iOS)
    var interfaceStyle: UIUserInterfaceStyle {
        switch self {
        case .system: .unspecified
        case .light: .light
        case .dark: .dark
        }
    }
    #endif
}

#if os(macOS)
/// Apply the preference to the whole app, so its sheets and windows follow it too.
struct AppWindowAppearance: View {
    let appearance: AppAppearance

    var body: some View {
        Color.clear.frame(width: 0, height: 0)
            .onChange(of: appearance, initial: true) { _, appearance in
                switch appearance {
                case .system: NSApp.appearance = nil
                case .light: NSApp.appearance = NSAppearance(named: .aqua)
                case .dark: NSApp.appearance = NSAppearance(named: .darkAqua)
                }
            }
    }
}
#else
/// Apply the preference to the window so presented sheets follow it too.
/// Clearing a sheet's preferredColorScheme can leave its previous override active.
struct AppWindowAppearance: UIViewRepresentable {
    let appearance: AppAppearance

    func makeUIView(context: Context) -> AppearanceView {
        let view = AppearanceView()
        view.isUserInteractionEnabled = false
        return view
    }

    func updateUIView(_ view: AppearanceView, context: Context) {
        view.appearance = appearance
    }

    final class AppearanceView: UIView {
        var appearance = AppAppearance.defaultValue {
            didSet {
                guard appearance != oldValue else { return }
                // Propagate UIKit traits after SwiftUI finishes its current update.
                DispatchQueue.main.async { [weak self] in self?.applyAppearance() }
            }
        }

        override func didMoveToWindow() {
            super.didMoveToWindow()
            applyAppearance()
        }

        private func applyAppearance() {
            guard let window, window.overrideUserInterfaceStyle != appearance.interfaceStyle else { return }
            window.overrideUserInterfaceStyle = appearance.interfaceStyle
        }
    }
}
#endif

/// The account at a tab's trailing edge. On a Mac it stands on its own tile, off the toolbar's glass, as the mark does.
struct AccountToolbarItem: ToolbarContent {
    let action: () -> Void

    var body: some ToolbarContent {
        #if os(macOS)
        if #available(macOS 26.0, *) {
            ToolbarItem(placement: .topBarTrailing) { AccountToolbarButton(action: action) }
                .sharedBackgroundVisibility(.hidden)
        } else {
            ToolbarItem(placement: .topBarTrailing) { AccountToolbarButton(action: action) }
        }
        #else
        ToolbarItem(placement: .topBarTrailing) { AccountToolbarButton(action: action) }
        #endif
    }
}

extension TextField where Label == Text {
    /// A field whose placeholder is its title in the secondary grey, which reads at 4.5:1: the system's placeholder
    /// is fainter than that.
    init(placeholder title: String, text: Binding<String>, axis: Axis = .horizontal) {
        self.init(title, text: text, prompt: Text(title).foregroundStyle(Brand.secondaryText), axis: axis)
    }
}

extension View {
    /// A field the screen draws itself, as iOS leaves it. A Mac would draw its own bezel inside, and a sheet doesn't
    /// take a field style from the window under it, so each field asks.
    @ViewBuilder func drawnField() -> some View {
        #if os(macOS)
        textFieldStyle(.plain)
        #else
        self
        #endif
    }
}

extension View {
    /// A button read as its label alone. iOS reads it so once it ignores what the button shows; a Mac's button reads
    /// only its label anyway, and would lose its press with what it shows.
    @ViewBuilder func readsAsItsLabel() -> some View {
        #if os(macOS)
        self
        #else
        accessibilityElement(children: .ignore)
        #endif
    }
}

extension View {
    /// A short date's full form (`Localizer.fullDate`) on a Mac, where the pointer hovering it shows it. iOS shows no
    /// tooltip and would read it out as a hint, so the date stays as it is there.
    @ViewBuilder func fullDateHelp(_ full: String?) -> some View {
        #if os(macOS)
        if let full { help(full) } else { self }
        #else
        self
        #endif
    }
}

extension View {
    /// A button the screen draws itself, as iOS leaves it. A Mac would put a push button's bezel behind it, and take
    /// clicks only on its glyph.
    @ViewBuilder func drawnButton() -> some View {
        #if os(macOS)
        buttonStyle(DrawnButtonStyle())
        #else
        self
        #endif
    }
}

#if os(macOS)
private struct DrawnButtonStyle: ButtonStyle {
    @Environment(\.isEnabled) private var isEnabled

    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .contentShape(Rectangle())
            .opacity(isEnabled ? (configuration.isPressed ? 0.6 : 1) : 0.4)
    }
}
#endif

extension View {
    /// A toggle as an iOS row draws it: the label across the row and a switch at its end. A Mac would put a checkbox
    /// before the label, and a sheet doesn't take a toggle style from the window under it, so each toggle asks.
    @ViewBuilder func rowToggle() -> some View {
        #if os(macOS)
        toggleStyle(RowToggleStyle())
        #else
        self
        #endif
    }
}

#if os(macOS)
private struct RowToggleStyle: ToggleStyle {
    func makeBody(configuration: Configuration) -> some View {
        HStack(spacing: 12) {
            configuration.label
                .frame(maxWidth: .infinity, alignment: .leading)
            Toggle("", isOn: configuration.$isOn)
                .toggleStyle(.switch)
                .labelsHidden()
        }
        .accessibilityRepresentation { Toggle(isOn: configuration.$isOn) { configuration.label } }
    }
}
#endif

/// One account entry point, shared by the tabs. ⌘, opens it from the screen in front, as Settings opens elsewhere.
/// On a Mac the account is the Settings window, which ⌘, opens from the app's menu: the button opens it too.
struct AccountToolbarButton: View {
    let action: () -> Void
    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.keyboardInFront) private var keyboardInFront

    var body: some View {
        #if os(macOS)
        SettingsLink { badge }
            .buttonStyle(TactileButtonStyle(scale: 0.94))
            .accessibilityLabel(localizer.text("native.account"))
        #else
        Button(action: open) { badge }
            .buttonStyle(TactileButtonStyle(scale: 0.94))
            .keyboardShortcut(keyboardInFront ? MenuKey.account : nil)
            .focusedSceneValue(\.accountMenu, MenuBar.shown && keyboardInFront ? AccountMenu(open: open) : nil)
            .accessibilityLabel(localizer.text("native.account"))
        #endif
    }

    private var badge: some View {
        Group {
            if let initial = session.user?.email?.first {
                Text(String(initial).uppercased())
                    .font(.subheadline.weight(.bold))
            } else {
                Image(systemName: "person.fill")
                    .font(.subheadline.weight(.semibold))
            }
        }
        .foregroundStyle(Brand.onAccent)
        .frame(width: 34, height: 34)
        .background(Brand.accent, in: Circle())
        .frame(width: 44, height: 44)
        .contentShape(Circle())
    }

    /// ⌘, reaches the button under a sheet or a popover of its screen as well: it waits for them.
    private func open() {
        if !PlatformApplication.shared.windowCovered { action() }
    }
}

/// Buttons only a keyboard presses, for what has no button of its own on the screen or has it in a menu. Nothing
/// of them shows; their titles name them where the system lists the app's shortcuts. SwiftUI keeps every shortcut
/// in the window, a tab's behind the tab chosen since and a list's under the page over it, so a screen adds these
/// only while it is in front with nothing over it.
struct KeyboardShortcuts<Content: View>: View {
    @ViewBuilder let content: Content

    var body: some View {
        ZStack { content }.hidden()
    }
}

/// Whether a keyboard is connected. Menus show their items' keys only then: without one, a phone's stay as they were.
@MainActor final class ConnectedKeyboard: ObservableObject {
    static let shared = ConnectedKeyboard()
    @Published private(set) var isConnected = GCKeyboard.coalesced != nil

    private init() {
        NotificationCenter.default.publisher(for: .GCKeyboardDidConnect).map { _ in true }
            .merge(with: NotificationCenter.default.publisher(for: .GCKeyboardDidDisconnect).map { _ in false })
            .receive(on: RunLoop.main)
            .assign(to: &$isConnected)
    }
}

extension EnvironmentValues {
    /// Whether the keyboard acts on the screen: its tab is the one chosen, and nothing of the app's stands over it.
    @Entry var keyboardInFront = true
    /// In a sheet, cards stand on the raised level rather than the paper. Views the page shows too ask.
    @Entry var inSheet = false
    /// Whether a sheet's top edge is lit. Not where it covers the whole screen, as Add does on a phone, nor in a Mac's
    /// Settings window, under its toolbar.
    @Entry var sheetEdgeLit = true
}

/// The warm light that catches a sheet's or a card's top edge in dark.
private let lamplight = Color(red: 1, green: 244 / 255, blue: 220 / 255)

/// A sheet's ground. In dark a lamp catches its top edge: one point of warm light, as on the web's sheets.
struct SheetGround: View {
    @Environment(\.colorScheme) private var colorScheme
    @Environment(\.sheetEdgeLit) private var lit

    var body: some View {
        Brand.sheet
            .overlay(alignment: .top) {
                if lit && colorScheme == .dark {
                    lamplight.opacity(0.09).frame(height: 1)
                }
            }
            .ignoresSafeArea()
    }
}

/// A carrier card's top edge in dark: one point of the lamp's light along its top, under what the card shows, which
/// thins away round its corners as the web's inset `--card-edge` does. Nothing in light.
private struct CardEdge<Card: Shape>: ViewModifier {
    let shape: Card
    @Environment(\.colorScheme) private var colorScheme

    func body(content: Content) -> some View {
        content.background {
            if colorScheme == .dark {
                TopEdge(card: shape).fill(lamplight.opacity(0.08)).allowsHitTesting(false)
            }
        }
    }

    /// The card less itself one point lower.
    private struct TopEdge: Shape {
        let card: Card

        func path(in rect: CGRect) -> Path {
            let outline = card.path(in: rect)
            return outline.subtracting(outline.offsetBy(dx: 0, dy: 1))
        }
    }
}

extension View {
    /// Lights the top edge of a card shaped `shape` in dark. Put it under the card's own drawings and over its fill.
    func cardEdge(_ shape: some Shape) -> some View {
        modifier(CardEdge(shape: shape))
    }
}

#if os(macOS)
extension NSApplication {
    /// Whether anything stands over the app's window: a sheet or a modal panel. For now popovers don't count.
    var windowCovered: Bool {
        (mainWindow ?? keyWindow)?.attachedSheet != nil || modalWindow != nil
    }
}
#else
extension UIApplication {
    /// Whether anything stands over the app's window: a sheet, a cover, an alert or a popover. The screens under it
    /// keep their keyboard shortcuts, which wait for it.
    var windowCovered: Bool {
        connectedScenes.lazy.compactMap { ($0 as? UIWindowScene)?.keyWindow }.first?
            .rootViewController?.presentedViewController != nil
    }
}
#endif

extension Color {
    init(hex: String) {
        let cleaned = hex.trimmingCharacters(in: CharacterSet.alphanumerics.inverted)
        let value = UInt64(cleaned, radix: 16) ?? 0
        let red = Double((value >> 16) & 0xFF) / 255
        let green = Double((value >> 8) & 0xFF) / 255
        let blue = Double(value & 0xFF) / 255
        self.init(red: red, green: green, blue: blue)
    }
}

extension GeometryProxy {
    /// Where an iPhone Duo folds while it stands half open, in this reader's space: nil when it lies flat or closed,
    /// and on any other device.
    var fold: CGRect? {
        #if os(macOS)
        return nil
        #else
        guard #available(iOS 27.1, *) else { return nil }
        return reservedRegions(kind: .division).first?.frame
        #endif
    }
}

extension View {
    /// What scrolls up under the buttons at the top fades away, rather than passing under a bar that appears for it.
    @ViewBuilder
    func softTopEdge() -> some View {
        if #available(iOS 26.0, macOS 26.0, *) {
            scrollEdgeEffectStyle(.soft, for: .top)
        } else {
            self
        }
    }

    /// In an iPad window, steps aside for the window's buttons in its top leading corner.
    @ViewBuilder
    func clearOfWindowControls() -> some View {
        if #available(iOS 26.0, macOS 26.0, *) {
            containerCornerOffset(.leading)
        } else {
            self
        }
    }

    /// A sheet as tall as what it shows. On a phone, a detent that follows the content, with the room to open fully;
    /// on an iPad, a form fitted to the content, as iPadOS keeps a sheet's detent at the height it opened with.
    @ViewBuilder
    func fittedSheet(height: CGFloat, detent: Binding<PresentationDetent>) -> some View {
        if DeviceIdiom.isPad {
            presentationSizing(.form.fitted(horizontal: false, vertical: true))
        } else {
            presentationDetents([.height(height), .large], selection: detent)
        }
    }

    /// A sheet of choices or settings as a form centred over an iPad's window, rather than a page nearly as large as
    /// it; on a phone, as it always was. A Mac's is as tall as what it shows, as its sheets are, and never shorter than a
    /// list of choices needs to show some: a list has no height of its own.
    @ViewBuilder
    func formOnPad() -> some View {
        #if os(macOS)
        frame(minHeight: 400).presentationSizing(.form.fitted(horizontal: false, vertical: true))
        #else
        if DeviceIdiom.isPad {
            presentationSizing(.form)
        } else {
            self
        }
        #endif
    }

    /// The same form on an iPad; on a phone, these detents. iPadOS would pin a sheet with detents to the window's
    /// bottom edge at their height rather than centre it.
    @ViewBuilder
    func formOnPad(phoneDetents detents: Set<PresentationDetent>, selection: Binding<PresentationDetent>? = nil) -> some View {
        #if os(macOS)
        formOnPad()
        #else
        if DeviceIdiom.isPad {
            presentationSizing(.form)
        } else if let selection {
            presentationDetents(detents, selection: selection)
        } else {
            presentationDetents(detents)
        }
        #endif
    }

    /// Stands what a sheet shows on the sheet's ground, and tells the views in it that they are in a sheet.
    func sheetGround() -> some View {
        background { SheetGround() }.environment(\.inSheet, true)
    }

    @ViewBuilder
    func glassSurface<S: Shape>(in shape: S) -> some View {
        if #available(iOS 26.0, macOS 26.0, *) {
            glassEffect(.regular, in: shape)
        } else {
            background(.ultraThinMaterial, in: shape)
                .overlay(shape.stroke(Brand.separator.opacity(0.72), lineWidth: 0.7))
        }
    }

    func parcelCardSurface(tone: ParcelTone = .normal) -> some View {
        background(
            RoundedRectangle(cornerRadius: 16, style: .continuous)
                .fill(Brand.paper)
                .overlay(alignment: .top) {
                    tone.color.frame(height: 2)
                }
                .clipShape(RoundedRectangle(cornerRadius: 16, style: .continuous))
                .overlay(
                    RoundedRectangle(cornerRadius: 16, style: .continuous)
                        .stroke(Brand.separator.opacity(0.62), lineWidth: 0.7)
                )
        )
    }
}

struct TactileButtonStyle: ButtonStyle {
    var scale: CGFloat = 0.985
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .scaleEffect(configuration.isPressed && !reduceMotion ? scale : 1)
            .opacity(configuration.isPressed ? 0.94 : 1)
            .animation(reduceMotion ? nil : .easeOut(duration: 0.16), value: configuration.isPressed)
    }
}

struct StatusBadge: View {
    let status: ParcelDisplayStatus
    @EnvironmentObject private var localizer: Localizer

    var body: some View {
        HStack(spacing: 6) {
            if status.syncing {
                ProgressView().controlSize(.mini)
            } else {
                Circle().fill(status.tone.color).frame(width: 7, height: 7)
            }
            Text(localizer.text(status.key))
                .font(.caption.weight(.semibold))
                .lineLimit(1)
        }
        .foregroundStyle(status.tone == .warning ? Brand.ink : .primary)
        .padding(.horizontal, 10)
        .padding(.vertical, 6)
        .background(status.tone.color.opacity(0.13), in: Capsule())
    }
}

struct DeliveryProgress: View {
    let stage: TrackingStage?
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    var body: some View {
        let position = stage?.metadata.progress ?? -1
        HStack(spacing: 5) {
            ForEach(Array(TrackingStage.core.enumerated()), id: \.offset) { index, _ in
                Capsule()
                    .fill(index <= position ? (stage?.metadata.tone.color ?? Brand.accent) : Color.secondary.opacity(0.16))
                    .frame(height: index == position ? 5 : 3)
                    .animation(reduceMotion ? nil : .easeOut(duration: 0.22), value: position)
            }
        }
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(localizer.text("native.deliveryProgress"))
        .accessibilityValue(
            position >= 0
                ? localizer.text("progress.step", [
                    "step": position + 1,
                    "total": TrackingStage.core.count,
                    "stage": localizer.text(stage?.localizationKey ?? "status.pending"),
                ])
                : localizer.text("progress.empty")
        )
    }
}

struct CountPill: View {
    let count: Int
    var body: some View {
        Text("\(count)")
            .font(.caption2.weight(.bold).monospacedDigit())
            .foregroundStyle(Brand.secondaryText)
            .padding(.horizontal, 8)
            .padding(.vertical, 4)
            .background(.secondary.opacity(0.1), in: Capsule())
    }
}

/// The calm notice shown in place of an error while the server is down for maintenance, in Peek's soft yellow: what is
/// shown stays, and the app asks again by itself, so there is nothing to press.
struct MaintenanceBanner: View {
    let text: String

    var body: some View {
        HStack(alignment: .firstTextBaseline, spacing: 10) {
            Image(systemName: "clock").font(.subheadline.weight(.semibold)).accessibilityHidden(true)
            Text(text).font(.subheadline).fixedSize(horizontal: false, vertical: true)
            Spacer(minLength: 0)
        }
        .foregroundStyle(Brand.ink)
        .padding(.horizontal, 16)
        .padding(.vertical, 12)
        .background(Brand.yellowSoft, in: RoundedRectangle(cornerRadius: 14, style: .continuous))
        .accessibilityElement(children: .combine)
        .accessibilityIdentifier("deliveries.maintenance")
    }
}

struct NoticeBanner: View {
    let symbol: String
    let title: String
    let message: String
    var tint: Color = Brand.accent
    var actionTitle: String?
    var action: (() -> Void)?

    @Environment(\.dynamicTypeSize) private var dynamicTypeSize
    /// The symbol's circle grows with it.
    @ScaledMetric(relativeTo: .body) private var badge: CGFloat = 28

    var body: some View {
        // At the largest text sizes the symbol stands over the words, which keep the width.
        let layout = dynamicTypeSize.isAccessibilitySize
            ? AnyLayout(VStackLayout(alignment: .leading, spacing: 8)) : AnyLayout(HStackLayout(alignment: .top, spacing: 12))
        layout {
            Image(systemName: symbol)
                .font(.body.weight(.semibold))
                .foregroundStyle(tint)
                .frame(width: badge, height: badge)
                .background(tint.opacity(0.12), in: Circle())
            VStack(alignment: .leading, spacing: 3) {
                Text(title).font(.subheadline.weight(.semibold))
                if !message.isEmpty {
                    Text(message).font(.caption).foregroundStyle(Brand.secondaryText)
                }
                if let actionTitle, let action {
                    Button(actionTitle, action: action)
                        .font(.caption.weight(.semibold))
                        .buttonStyle(.plain)
                        .foregroundStyle(tint)
                        .padding(.top, 3)
                }
            }
            if !dynamicTypeSize.isAccessibilitySize { Spacer(minLength: 0) }
        }
        .frame(maxWidth: dynamicTypeSize.isAccessibilitySize ? .infinity : nil, alignment: .leading)
        .padding(14)
        .background(tint.opacity(0.09), in: RoundedRectangle(cornerRadius: 14, style: .continuous))
    }
}

struct InlineToast: View {
    /// One of the answers a toast that asks offers.
    struct Answer {
        let title: String
        let action: () -> Void
    }

    let text: String
    let button: String?
    let symbol: String
    let tint: Color
    let action: (() -> Void)?
    /// How many lines the words take at most beside the symbol; nil, as many as they need.
    let lines: Int?
    /// A question's answers, side by side where the one button stands.
    let answers: [Answer]
    /// Who asks the question, where the symbol stands.
    private let asker: AnyView?

    @Environment(\.dynamicTypeSize) private var dynamicTypeSize

    init(
        text: String,
        button: String?,
        symbol: String = "checkmark.circle.fill",
        tint: Color = .green,
        lines: Int? = 2,
        action: (() -> Void)?
    ) {
        self.text = text
        self.button = button
        self.symbol = symbol
        self.tint = tint
        self.lines = lines
        self.action = action
        answers = []
        asker = nil
    }

    /// A toast that asks: each answer is a button of its own, and `asker` stands before the words.
    init(text: String, answers: [Answer], @ViewBuilder asker: () -> some View) {
        self.text = text
        button = nil
        symbol = ""
        tint = .primary
        action = nil
        lines = 3
        self.answers = answers
        self.asker = AnyView(asker())
    }

    var body: some View {
        content
            .padding(.leading, asker == nil ? 17 : 11)
            .padding(.trailing, answers.isEmpty ? 17 : 9)
            .frame(minHeight: 54)
            .foregroundStyle(.primary)
            .glassSurface(in: RoundedRectangle(cornerRadius: 16, style: .continuous))
            .shadow(color: .black.opacity(0.13), radius: 8, y: 4)
            // One button is the toast's own action; two are reached one by one.
            .accessibilityElement(children: answers.isEmpty ? .combine : .contain)
    }

    /// At the largest text sizes the words take every line they need, and the buttons go under them.
    @ViewBuilder private var content: some View {
        if dynamicTypeSize.isAccessibilitySize {
            VStack(alignment: .leading, spacing: 10) {
                HStack(alignment: asker == nil ? .firstTextBaseline : .top, spacing: asker == nil ? 14 : 10) {
                    lead
                    Text(text).font(.subheadline.weight(.medium))
                        .fixedSize(horizontal: false, vertical: true)
                        .frame(maxWidth: .infinity, alignment: .leading)
                }
                actionButton
                if !answers.isEmpty {
                    HStack(spacing: 6) { answerButtons }
                }
            }
            .padding(.vertical, 12)
        } else {
            HStack(spacing: asker == nil ? 14 : 10) {
                lead
                Text(text).font(.subheadline.weight(.medium)).lineLimit(lines)
                Spacer(minLength: 4)
                actionButton
                if !answers.isEmpty {
                    HStack(spacing: 6) { answerButtons }
                        .fixedSize()
                }
            }
        }
    }

    @ViewBuilder private var lead: some View {
        if let asker { asker } else { Image(systemName: symbol).foregroundStyle(tint) }
    }

    @ViewBuilder private var actionButton: some View {
        if let button, let action {
            Button(button, action: action)
                .font(.subheadline.weight(.bold))
                .foregroundStyle(tint)
        }
    }

    private var answerButtons: some View {
        ForEach(Array(answers.enumerated()), id: \.offset) { _, answer in
            Button(action: answer.action) {
                Text(answer.title)
                    .font(.subheadline.weight(.semibold))
                    .padding(.horizontal, 14)
                    .frame(minHeight: 36)
                    .background(Brand.ink.opacity(0.08), in: RoundedRectangle(cornerRadius: 12, style: .continuous))
                    .frame(minHeight: 44)
                    .contentShape(Rectangle())
            }
            .buttonStyle(TactileButtonStyle(scale: 0.96))
        }
    }
}
