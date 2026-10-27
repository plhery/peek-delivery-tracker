import CryptoKit
import ImageIO
import SwiftUI
import UniformTypeIdentifiers
#if os(iOS)
import UIKit
#else
import AppKit
#endif

/// Draws the pictures of the widget's cards into the shared container: each parcel's carrier mark, and its route as
/// each size of widget shows it, light and dark, at the screen's scale. A widget has the memory to show them, not to
/// draw the map.
@MainActor
enum DeliveryWidgetPictures {
    /// Changes whenever the pictures are drawn differently, so that the ones already drawn are drawn again.
    private static let version = 2

    /// How a route is drawn for each size of widget. A route is no bigger than the smallest widget it goes in, which
    /// shows it as wide as the card: an image larger than its widget is not shown.
    struct Frame {
        let size: CGSize
        let insets: EdgeInsets
        /// Where the card's top row ends and its words start, which Pip stays between.
        let ceiling: CGFloat
        let floor: CGFloat
        /// Where the route fades in from under the top row, so that a line from far away does not cross the mark.
        let fade: ClosedRange<CGFloat>
    }

    static func frame(of picture: DeliveryWidgetPicture) -> Frame {
        switch picture {
        case .half:
            Frame(size: CGSize(width: 162, height: 76), insets: EdgeInsets(top: 30, leading: 12, bottom: 12, trailing: 12),
                  ceiling: 26, floor: 70, fade: 14...32)
        case .wide:
            Frame(size: CGSize(width: 364, height: 104), insets: EdgeInsets(top: 36, leading: 16, bottom: 22, trailing: 16),
                  ceiling: 34, floor: 96, fade: 18...40)
        default:
            Frame(size: CGSize(width: 170, height: 104), insets: EdgeInsets(top: 36, leading: 14, bottom: 22, trailing: 14),
                  ceiling: 34, floor: 84, fade: 18...40)
        }
    }

    static var scale: CGFloat {
        #if os(iOS)
        UIGraphicsImageRendererFormat.preferred().scale
        #else
        NSScreen.main?.backingScaleFactor ?? 2
        #endif
    }

    /// A parcel's card, its pictures named after everything they show, so that a change to any of it draws them afresh.
    static func card(for parcel: Parcel, localizer: Localizer) -> DeliveryWidgetCard {
        let identity = CarrierVisualIdentity.of(parcel.displayedCarrier, language: localizer.language)
        let shown = [
            "\(version)", parcel.id.uuidString, identity.name, identity.family, identity.colors.joined(separator: ","),
            localizer.language.rawValue, "\(scale)", parcel.currentStage?.rawValue ?? "", parcel.carrierData?.destinationCountry ?? "",
        ] + parcel.trackingEvents.map { "\($0.stage.rawValue) \($0.occurredAt) \($0.location ?? "") \($0.place.map { "\($0)" } ?? "")" }
        let digest = SHA256.hash(data: Data(shown.joined(separator: "\n").utf8)).prefix(8).map { String(format: "%02x", $0) }.joined()
        return DeliveryWidgetCard(
            surface: [identity.colors[0], identity.colors[1]], ink: [identity.colors[2], identity.colors[3]],
            status: localizer.parcelStatus(parcel), pictures: "\(parcel.id.uuidString.lowercased())-\(digest)", scale: scale
        )
    }

    /// The mark is drawn last, so a card whose mark is there has all its pictures.
    static func isDrawn(_ card: DeliveryWidgetCard, in folder: URL) -> Bool {
        [false, true].allSatisfy { FileManager.default.fileExists(atPath: DeliveryWidgetPicture.mark.file(of: card, dark: $0, in: folder).path) }
    }

    /// Draws a card's pictures; false when one could not be written.
    static func draw(_ parcel: Parcel, as card: DeliveryWidgetCard, atlas: WorldAtlas?, localizer: Localizer, in folder: URL) async -> Bool {
        guard (try? FileManager.default.createDirectory(at: folder, withIntermediateDirectories: true)) != nil else { return false }
        // The list's card: the carrier the parcel came with, after a handover too.
        let identity = CarrierVisualIdentity.of(parcel.displayedCarrier, language: localizer.language)
        let placed = parcel.hasMappedPlace
        for dark in [false, true] {
            for picture in DeliveryWidgetPicture.routes {
                let frame = frame(of: picture)
                let file = picture.file(of: card, dark: dark, in: folder)
                if placed, let atlas {
                    let route = RouteEngraving(
                        atlas: atlas, route: ParcelRoute(parcel: parcel, atlas: atlas, language: localizer.language),
                        stage: parcel.currentStage, identity: identity, peek: false, insets: frame.insets, ceiling: frame.ceiling,
                        floor: frame.floor
                    )
                    .frame(width: frame.size.width, height: frame.size.height)
                    .mask(LinearGradient(stops: [.init(color: .clear, location: frame.fade.lowerBound / frame.size.height),
                                                 .init(color: .black, location: frame.fade.upperBound / frame.size.height)],
                                         startPoint: .top, endPoint: .bottom))
                    guard write(route, dark: dark, localizer: localizer, to: file) else { return false }
                } else {
                    // With no route to draw, Pip still stands where it would be.
                    let pip = LonePip(mood: PipMood(stage: parcel.currentStage) ?? .look, identity: identity, frame: frame)
                    guard write(pip, dark: dark, localizer: localizer, to: file) else { return false }
                }
                await Task.yield()
            }
        }
        for dark in [false, true] {
            let mark = CarrierFleetMark(identity: identity).fixedSize()
            guard write(mark, dark: dark, localizer: localizer, to: DeliveryWidgetPicture.mark.file(of: card, dark: dark, in: folder)) else { return false }
        }
        return true
    }

    /// Removes the pictures of every card but `cards`.
    static func sweep(keeping cards: [DeliveryWidgetCard], in folder: URL) {
        let kept = Set(cards.map(\.pictures))
        for file in (try? FileManager.default.contentsOfDirectory(at: folder, includingPropertiesForKeys: nil)) ?? [] {
            let name = file.deletingPathExtension().lastPathComponent
            guard !kept.contains(where: { name.hasPrefix($0 + "-") }) else { continue }
            try? FileManager.default.removeItem(at: file)
        }
    }

    private static func write(_ view: some View, dark: Bool, localizer: Localizer, to file: URL) -> Bool {
        let renderer = ImageRenderer(
            content: view
                .environmentObject(localizer)
                .environment(\.locale, localizer.language.locale)
                .environment(\.colorScheme, dark ? .dark : .light)
                .environment(\.dynamicTypeSize, .large)
        )
        renderer.scale = scale
        renderer.isOpaque = false
        guard let image = renderer.cgImage,
              let destination = CGImageDestinationCreateWithURL(file as CFURL, UTType.png.identifier as CFString, 1, nil) else { return false }
        CGImageDestinationAddImage(destination, image, nil)
        return CGImageDestinationFinalize(destination)
    }
}

/// Pip across the top of a card whose parcel has no places: as big as he fits between the card's top row and its words,
/// at its trailing end, looking back at them.
private struct LonePip: View {
    let mood: PipMood
    let identity: CarrierVisualIdentity
    let frame: DeliveryWidgetPictures.Frame

    var body: some View {
        let side = -1
        let extents = PipGeometry.extents(mood, side: side)
        let room = frame.floor - frame.ceiling
        let width = mood.widths.first { extents.height * $0 / PipGeometry.frame.width <= room } ?? mood.widths[mood.widths.count - 1]
        let unit = width / PipGeometry.frame.width
        let origin = CGPoint(x: frame.size.width - frame.insets.trailing - extents.maxX * unit,
                             y: frame.ceiling + (room - extents.height * unit) / 2 - extents.minY * unit)
        InkPip(mood: mood, side: side, below: false, ink: identity.ink, surface: identity.surface)
            .frame(width: width, height: width * PipGeometry.frame.height / PipGeometry.frame.width)
            .offset(x: origin.x, y: origin.y)
            .frame(width: frame.size.width, height: frame.size.height, alignment: .topLeading)
    }
}
