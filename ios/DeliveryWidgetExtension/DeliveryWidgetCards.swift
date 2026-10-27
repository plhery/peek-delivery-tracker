import SwiftUI
import WidgetKit
#if os(iOS)
import UIKit
#else
import AppKit
#endif

/// A card's pictures as the widget reads them from the shared container, light and dark.
struct DeliveryWidgetCardArt {
    struct Look {
        let mark: Image
        /// A parcel without places has no route.
        let route: Image?
    }

    let light: Look
    let dark: Look

    /// The mark, and the route as `shape` shows it; nil while the mark isn't there.
    init?(_ card: DeliveryWidgetCard, route shape: DeliveryWidgetPicture, in folder: URL) {
        func image(_ picture: DeliveryWidgetPicture, dark: Bool) -> Image? {
            guard let data = try? Data(contentsOf: picture.file(of: card, dark: dark, in: folder)) else { return nil }
            #if os(iOS)
            return UIImage(data: data, scale: card.scale).map(Image.init(uiImage:))
            #else
            guard let image = NSImage(data: data), let pixels = image.representations.first else { return nil }
            image.size = CGSize(width: CGFloat(pixels.pixelsWide) / card.scale, height: CGFloat(pixels.pixelsHigh) / card.scale)
            return Image(nsImage: image)
            #endif
        }
        guard let lightMark = image(.mark, dark: false), let darkMark = image(.mark, dark: true) else { return nil }
        light = Look(mark: lightMark, route: image(shape, dark: false))
        dark = Look(mark: darkMark, route: image(shape, dark: true))
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
    static func page(_ scheme: ColorScheme) -> Color { color(["#F4F5F1", "#151915"], scheme) }
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
    /// What a whole medium card says at its top right, as Next up does in the app.
    var badge: String?
    /// The end of the top row is left to the widget's next button.
    var leavesNext = false

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
                if let badge {
                    Text(badge)
                        .font(.caption2)
                        .textCase(.uppercase)
                        .tracking(1.2)
                        .foregroundStyle(ink.opacity(0.75))
                        .lineLimit(1)
                        .minimumScaleFactor(0.7)
                }
                if leavesNext { Color.clear.frame(width: DeliveryWidgetNextMark.size, height: 1) }
            }
            Spacer(minLength: 4)
            VStack(alignment: .leading, spacing: shape == .half ? 2 : 3) {
                Text(parcel.label)
                    .font((shape == .half ? Font.subheadline : shape == .wide ? .title3 : .headline).weight(.semibold))
                    .foregroundStyle(Color.primary)
                    .lineLimit(2)
                    .minimumScaleFactor(0.82)
                Text(status)
                    .font(shape == .half ? .caption2 : .caption)
                    .foregroundStyle(ink)
                    .lineLimit(1)
                    .minimumScaleFactor(0.8)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
        .accessibilityElement(children: .combine)
    }
}

/// The widget's next button, as quiet as the card's words: a small chevron in the card's ink. It reaches past what it
/// shows, so that a finger finds it.
struct DeliveryWidgetNextMark: View {
    let ink: Color

    static let size: CGFloat = 18
    static let reach: CGFloat = 9

    var body: some View {
        Image(systemName: "chevron.forward")
            .font(.system(size: 8, weight: .bold))
            .foregroundStyle(ink.opacity(0.6))
            .frame(width: Self.size, height: Self.size)
            .background(ink.opacity(0.07), in: Circle())
            .padding(Self.reach)
            .contentShape(Rectangle())
    }
}

extension View {
    /// The widget's next button at the end of a card's top row, level with the card's mark.
    func deliveryWidgetNext(markHeight: CGFloat, @ViewBuilder _ button: () -> some View) -> some View {
        overlay(alignment: .topTrailing) {
            button().offset(x: DeliveryWidgetNextMark.reach, y: (markHeight - DeliveryWidgetNextMark.size) / 2 - DeliveryWidgetNextMark.reach)
        }
    }
}
