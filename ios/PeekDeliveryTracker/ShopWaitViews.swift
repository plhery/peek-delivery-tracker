import SwiftUI

/// The parcels the reader waits for a shop to ship, under the parcels on their way: a card each, as the web's
/// `ShopWaitCards` draws them. Nothing while the first read of the list is on its way.
struct ShopWaitCards: View {
    @EnvironmentObject private var store: ParcelStore
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    var body: some View {
        let waits = store.shopWaits.waits
        if !waits.isEmpty, !(store.loading && store.parcels.isEmpty) {
            VStack(alignment: .leading, spacing: 10) {
                ForEach(waits) { wait in
                    ShopWaitCard(wait: wait) { Task { await store.removeShopWait(wait.id) } }
                        .transition(.opacity.combined(with: .scale(scale: 0.97)))
                }
            }
            .animation(reduceMotion ? nil : .snappy(duration: 0.32), value: waits.map(\.id))
        }
    }
}

/// A parcel a shop hasn't shipped yet: drawn with a dashed edge and no fill on the list's page, a parcel still to
/// come. Its words read as one, and its button on its own.
struct ShopWaitCard: View {
    let wait: ShopWait
    let onRemove: () -> Void

    @EnvironmentObject private var localizer: Localizer

    private static let shape = RoundedRectangle(cornerRadius: 16, style: .continuous)

    var body: some View {
        let text = ShopWaitCardText(wait, localizer: localizer)
        VStack(alignment: .leading, spacing: 5) {
            VStack(alignment: .leading, spacing: 5) {
                HStack(alignment: .firstTextBaseline, spacing: 6) {
                    Image(systemName: "storefront").accessibilityHidden(true)
                    Text(text.via)
                }
                .font(.caption.weight(.semibold))
                .foregroundStyle(Brand.inkSoft)
                .padding(.bottom, 3)
                Text(text.title)
                    .font(.headline.weight(.semibold))
                    .foregroundStyle(Brand.ink)
                HStack(alignment: .firstTextBaseline, spacing: 5) {
                    Image(systemName: text.stateSymbol).foregroundStyle(Brand.secondaryText).accessibilityHidden(true)
                    Text(text.state).foregroundStyle(text.expired ? Brand.secondaryText : Brand.ink)
                }
                .font(.caption)
                if let hint = text.hint {
                    Text(hint).font(.caption).foregroundStyle(Brand.secondaryText)
                }
            }
            .fixedSize(horizontal: false, vertical: true)
            .frame(maxWidth: .infinity, alignment: .leading)
            .accessibilityElement(children: .combine)
            Button(action: onRemove) {
                Text(text.action)
                    .font(.footnote.weight(.semibold))
                    .foregroundStyle(ExperimentalPalette.delivered)
                    .multilineTextAlignment(.trailing)
                    .padding(.horizontal, 10)
                    .frame(minHeight: 44)
                    .contentShape(Rectangle())
            }
            .buttonStyle(TactileButtonStyle())
            // The words stay where the web puts them, at the card's end; the button's room goes round them.
            .padding(.trailing, -10)
            .padding(.vertical, -8)
            .frame(maxWidth: .infinity, alignment: .trailing)
            .accessibilityIdentifier("deliveries.shopWait.remove")
        }
        .padding(.vertical, 13)
        .padding(.horizontal, 15)
        .frame(maxWidth: .infinity, alignment: .leading)
        .overlay { Self.shape.strokeBorder(Brand.separator, style: StrokeStyle(lineWidth: 1.5, dash: [6, 4])) }
        .contentShape(Self.shape)
        .accessibilityElement(children: .contain)
        .accessibilityIdentifier("deliveries.shopWait")
    }
}
