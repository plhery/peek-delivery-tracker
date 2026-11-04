import SwiftUI
import UIKit
import XCTest
@testable import PeekDeliveryTracker

/// Secondary words read at 4.5:1 or more, the ratio Apple asks of text for Sufficient Contrast, on everything they
/// stand on, in light and dark and with Increase Contrast.
final class ContrastTests: XCTestCase {
    private let appearances: [(name: String, traits: UITraitCollection)] = [
        ("light", UITraitCollection(userInterfaceStyle: .light)),
        ("dark", UITraitCollection(userInterfaceStyle: .dark)),
        ("light, increased contrast", UITraitCollection { $0.userInterfaceStyle = .light; $0.accessibilityContrast = .high }),
        ("dark, increased contrast", UITraitCollection { $0.userInterfaceStyle = .dark; $0.accessibilityContrast = .high }),
    ]

    private struct RGB {
        let red: CGFloat, green: CGFloat, blue: CGFloat

        /// WCAG's relative luminance.
        var luminance: CGFloat {
            func linear(_ channel: CGFloat) -> CGFloat {
                channel <= 0.04045 ? channel / 12.92 : pow((channel + 0.055) / 1.055, 2.4)
            }
            return 0.2126 * linear(red) + 0.7152 * linear(green) + 0.0722 * linear(blue)
        }
    }

    /// The color as it shows over `ground`, which is opaque.
    private func shown(_ color: Color, over ground: RGB? = nil, opacity: CGFloat = 1, _ traits: UITraitCollection) -> RGB {
        var red: CGFloat = 0, green: CGFloat = 0, blue: CGFloat = 0, alpha: CGFloat = 0
        UIColor(color).resolvedColor(with: traits).getRed(&red, green: &green, blue: &blue, alpha: &alpha)
        let cover = alpha * opacity
        guard let ground, cover < 1 else { return RGB(red: red, green: green, blue: blue) }
        return RGB(red: red * cover + ground.red * (1 - cover), green: green * cover + ground.green * (1 - cover),
                   blue: blue * cover + ground.blue * (1 - cover))
    }

    private func ratio(_ first: RGB, _ second: RGB) -> CGFloat {
        let (lighter, darker) = (max(first.luminance, second.luminance), min(first.luminance, second.luminance))
        return (lighter + 0.05) / (darker + 0.05)
    }

    func testSecondaryTextReadsOnThePagePaperCreamAndEveryCarriersCard() {
        // The bundled rules, without inheriting a previously cached live catalog.
        let catalog = CarrierCatalog(cacheURL: nil)
        let cards = catalog.definitions.map { id, definition in
            ("\(id.rawValue)'s card", CarrierVisualIdentity(id: id.rawValue, carrier: definition).surface)
        }
        XCTAssertGreaterThan(cards.count, 1000)
        // The colors resolve for the appearance asked, not the one the tests run in.
        XCTAssertLessThan(shown(Brand.background, appearances[1].traits).luminance,
                          shown(Brand.background, appearances[0].traits).luminance)
        for (appearance, traits) in appearances {
            let page = shown(Brand.background, traits)
            // The deliveries' counts stand in a capsule of the secondary label at 9% on the page.
            let grounds = [("page", page), ("paper", shown(Brand.paper, traits)), ("cream", shown(Brand.cream, traits)),
                           ("count's capsule", shown(Color(uiColor: .secondaryLabel), over: page, opacity: 0.09, traits))]
                + cards.map { ($0.0, shown($0.1, traits)) }
            for (name, ground) in grounds {
                let measured = ratio(shown(Brand.secondaryText, over: ground, traits), ground)
                XCTAssertGreaterThanOrEqual(measured, 4.5, "on the \(name), \(appearance)")
            }
        }
    }
}
