import SwiftUI
import WidgetKit
#if os(iOS)
import UIKit
#else
import AppKit
#endif

/// A picture the app drew into the shared container, at its pixels per point.
enum DeliveryWidgetImage {
    static func load(_ file: URL, scale: Double) -> Image? {
        guard let data = try? Data(contentsOf: file) else { return nil }
        #if os(iOS)
        return UIImage(data: data, scale: scale).map(Image.init(uiImage:))
        #else
        guard let image = NSImage(data: data), let pixels = image.representations.first else { return nil }
        image.size = CGSize(width: CGFloat(pixels.pixelsWide) / scale, height: CGFloat(pixels.pixelsHigh) / scale)
        return Image(nsImage: image)
        #endif
    }
}

/// A card's pictures as the widget reads them from the shared container, light and dark.
struct DeliveryWidgetCardArt {
    struct Look {
        let mark: Image
        /// A parcel without places has no route.
        let route: Image?
    }

    let light: Look
    let dark: Look

    /// The mark, and the route as `shape` shows it, if any; nil while the mark isn't there.
    init?(_ card: DeliveryWidgetCard, route shape: DeliveryWidgetPicture?, in folder: URL) {
        func image(_ picture: DeliveryWidgetPicture, dark: Bool) -> Image? {
            DeliveryWidgetImage.load(picture.file(of: card, dark: dark, in: folder), scale: card.scale)
        }
        guard let lightMark = image(.mark, dark: false), let darkMark = image(.mark, dark: true) else { return nil }
        light = Look(mark: lightMark, route: shape.flatMap { image($0, dark: false) })
        dark = Look(mark: darkMark, route: shape.flatMap { image($0, dark: true) })
    }

    func look(_ scheme: ColorScheme) -> Look { scheme == .dark ? dark : light }
}

enum DeliveryWidgetCardColors {
    /// The light or the dark one of a pair of hex colours.
    static func color(_ pair: [String], _ scheme: ColorScheme) -> Color {
        let hex = scheme == .dark && pair.count > 1 ? pair[1] : pair.first ?? "#000000"
        let value = UInt32(hex.dropFirst(), radix: 16) ?? 0
        return Color(red: Double(value >> 16 & 255) / 255, green: Double(value >> 8 & 255) / 255, blue: Double(value & 255) / 255)
    }

    /// The page the app's list of cards stands on.
    static func page(_ scheme: ColorScheme) -> Color { color(["#F4F5F1", "#13110E"], scheme) }

    /// The app's warning colour (`Brand.warning`), which a parcel that needs the reader writes its stage in.
    static func warning(_ scheme: ColorScheme) -> Color { color(["#963E19", "#FFB184"], scheme) }
}

/// A card's colour, with its route drawn across the top as wide as the card. A tinted, clear or inactive desktop
/// widget has no colours, and the route would show there as a blank patch: the card keeps a plain plate.
struct DeliveryWidgetCardBackground: View {
    let card: DeliveryWidgetCard
    let art: DeliveryWidgetCardArt

    @Environment(\.colorScheme) private var scheme
    @Environment(\.widgetRenderingMode) private var renderingMode

    var body: some View {
        if renderingMode == .fullColor {
            DeliveryWidgetCardColors.color(card.surface, scheme)
                .overlay(alignment: .top) {
                    art.look(scheme).route?.resizable().scaledToFit()
                }
        } else {
            Color.primary.opacity(0.12)
        }
    }
}

/// What a medium widget's two cards stand on.
struct DeliveryWidgetCardPage: View {
    @Environment(\.colorScheme) private var scheme

    var body: some View { DeliveryWidgetCardColors.page(scheme) }
}

/// A parcel's card as the app's list shows it, in its carrier's colours: the mark at the top, over the route, and the
/// parcel's name and status at the bottom. Its background is drawn apart, as a widget's own is.
struct DeliveryWidgetCardView: View {
    let parcel: DeliveryWidgetParcel
    let card: DeliveryWidgetCard
    let art: DeliveryWidgetCardArt
    /// A small widget, half a medium one, or a whole medium one.
    let shape: DeliveryWidgetPicture
    /// The status line starts with the dot for new updates, which the Parcels widget shows.
    var marked = false

    @Environment(\.colorScheme) private var scheme

    private var ink: Color { DeliveryWidgetCardColors.color(card.ink, scheme) }
    private var status: String { card.status == parcel.detail ? card.status : "\(card.status) · \(parcel.detail)" }

    static func markHeight(_ shape: DeliveryWidgetPicture) -> CGFloat { shape == .half ? 13 : 15 }

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(alignment: .top, spacing: 8) {
                art.look(scheme).mark
                    .resizable()
                    .scaledToFit()
                    .frame(height: Self.markHeight(shape))
                    .accessibilityLabel(parcel.carrier)
                Spacer(minLength: 0)
            }
            Spacer(minLength: 4)
            VStack(alignment: .leading, spacing: shape == .half ? 2 : 3) {
                Text(parcel.label)
                    .font((shape == .half ? Font.subheadline : shape == .wide ? .title3 : .headline).weight(.semibold))
                    .foregroundStyle(Color.primary)
                    .lineLimit(2)
                    .minimumScaleFactor(0.82)
                HStack(spacing: 0) {
                    NewUpdateDot(shown: marked)
                    Text(status)
                }
                .font(shape == .half ? .caption2 : .caption)
                .foregroundStyle(ink)
                .lineLimit(1)
                .minimumScaleFactor(0.8)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
        .accessibilityElement(children: .combine)
        .widgetNewUpdateSaidFirst(marked)
    }
}

/// The dot before a parcel's status line, in the app's list and the Parcels widget, on a parcel with updates since it
/// was last opened, in the line's own colour. It brings the room between itself and the words. Off, it keeps its place
/// in the line without any width, so it fades and the words slide back as it clears.
struct NewUpdateDot: View {
    let shown: Bool
    @ScaledMetric(relativeTo: .caption) private var size: CGFloat = 5
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    var body: some View {
        Circle()
            .frame(width: shown ? size : 0, height: size)
            .opacity(shown ? 0.8 : 0)
            .padding(.trailing, shown ? size : 0)
            .animation(reduceMotion ? nil : .easeOut(duration: 0.3), value: shown)
            .accessibilityHidden(true)
    }
}

extension EnvironmentValues {
    /// "New update." in the widget's language, which VoiceOver says first of a parcel the Parcels widget marks.
    @Entry var widgetNewUpdateWords = ""
}

extension View {
    /// A widget's parcel, read as one, says "New update" before the rest while it is marked, as the app's list does.
    func widgetNewUpdateSaidFirst(_ marked: Bool) -> some View {
        modifier(WidgetNewUpdateSaidFirst(marked: marked))
    }
}

private struct WidgetNewUpdateSaidFirst: ViewModifier {
    let marked: Bool
    @Environment(\.widgetNewUpdateWords) private var words

    func body(content: Content) -> some View {
        content.accessibilityLabel { label in
            if marked { Text(words) }
            label
        }
    }
}
