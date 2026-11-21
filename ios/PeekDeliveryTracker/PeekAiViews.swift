import SwiftUI

extension EnvironmentValues {
    /// Whether cards show Peek AI's estimates, as Settings has it.
    @Entry var showsPeekAi = true
}

extension Localizer {
    /// Peek AI's name, and how VoiceOver reads its words, in the app's language.
    var peekAiCopy: PeekAiCopy { PeekAiCopy(name: text("peekAi.name"), says: text("peekAi.says", ["words": "{{words}}"])) }
}

/// The parcel and the estimate Peek AI's sheet explains, kept as it was opened: switching Peek AI off there leaves it.
struct PeekAiSheetRequest: Identifiable {
    let parcel: Parcel
    let ai: PeekAi
    var id: UUID { parcel.id }
}

/// Peek AI on the parcel's page: a short card under the parcel's, "✦ Peek AI today is unlikely (i)" and why. The (i)
/// opens its sheet, which the page holds, so that switching Peek AI off there does not pull the sheet away.
struct PeekAiCard: View {
    let parcel: Parcel
    let ai: PeekAi
    /// The time it is drawn at, which the page moves on each minute: "5 hours ago" stays true.
    var now = Date()
    let open: (PeekAiSheetRequest) -> Void

    @EnvironmentObject private var localizer: Localizer
    @ObservedObject private var catalog = CarrierCatalog.shared

    var body: some View {
        let carrier = catalog.info(for: parcel.displayedCarrier, language: localizer.language).displayName
        let claim = localizer.peekAiClaim(ai, now: now)
        VStack(alignment: .leading, spacing: 3) {
            HStack(alignment: .center, spacing: 6) {
                HStack(alignment: .firstTextBaseline, spacing: 5) {
                    PeekAiPill(name: localizer.text("peekAi.name"))
                    Text(claim).fontWeight(.semibold)
                        .fixedSize(horizontal: false, vertical: true)
                }
                .foregroundStyle(Brand.ink)
                .frame(maxWidth: .infinity, alignment: .leading)
                .accessibilityElement(children: .ignore)
                .accessibilityLabel(localizer.text("peekAi.says", ["words": claim]))
                Button {
                    open(PeekAiSheetRequest(parcel: parcel, ai: ai))
                    DeliveryAnalytics.shared.action("peek-ai-open")
                } label: {
                    Image(systemName: "info.circle")
                        .font(.system(size: 15))
                        .foregroundStyle(Brand.secondaryText)
                        .frame(width: 44, height: 44)
                        .contentShape(Rectangle())
                }
                .buttonStyle(.plain)
                .padding(-12)
                .accessibilityLabel(localizer.text("peekAi.about"))
                .accessibilityIdentifier("parcel.peekAi.about")
            }
            Text(localizer.peekAiWhy(ai, carrier: carrier, now: now))
                .foregroundStyle(Brand.secondaryText)
                .fixedSize(horizontal: false, vertical: true)
        }
        .font(.footnote)
        .padding(.top, 11)
        .padding(.bottom, 12)
        .padding(.horizontal, 14)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color.secondary.opacity(0.08), in: RoundedRectangle(cornerRadius: 16))
        .accessibilityElement(children: .contain)
        .accessibilityIdentifier("parcel.peekAi")
    }
}

/// Draws a card again when what Peek AI says of its parcel can change (`Parcel.peekAiChanges`): on a list left open, "by
/// 16:00" goes at 16:00, and a day's estimate the midnight after it.
struct PeekAiClock<Content: View>: View {
    let parcel: Parcel
    let shown: Bool
    @ViewBuilder let content: (Date) -> Content

    var body: some View {
        let now = Date()
        TimelineView(.explicit([now] + (shown ? parcel.peekAiChanges(after: now) : []))) { context in
            content(context.date)
        }
    }
}

/// Peek AI on the parcel's page, looked at again each minute: the card goes once its hour or day has passed.
struct PeekAiPageCard: View {
    let parcel: Parcel
    let open: (PeekAiSheetRequest) -> Void

    var body: some View {
        TimelineView(.everyMinute) { context in
            if let ai = parcel.shownPeekAi(at: context.date) {
                PeekAiCard(parcel: parcel, ai: ai, now: context.date, open: open)
            }
        }
    }
}

/// The (i): what Peek AI expects on the parcel's own card, with the carrier's word under it, the days or hours, why, how
/// often it is right, the switch that hides it, and what it is.
struct PeekAiSheet: View {
    let request: PeekAiSheetRequest

    @EnvironmentObject private var store: ParcelStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dismiss) private var dismiss
    @Environment(\.dynamicTypeSize) private var typeSize
    @ObservedObject private var catalog = CarrierCatalog.shared
    @State private var contentHeight: CGFloat = 560
    @State private var detent = PresentationDetent.height(560)

    private var fitted: PresentationDetent { .height(contentHeight) }

    var body: some View {
        ScrollView {
            content
                .onGeometryChange(for: CGFloat.self) { $0.size.height } action: { contentHeight = $0 }
        }
        .scrollBounceBehavior(.basedOnSize)
        .sheetGround()
        .tint(Brand.ink)
        .environment(\.locale, localizer.language.locale)
        .fittedSheet(height: contentHeight, detent: $detent)
        .presentationDragIndicator(.visible)
        .presentationBackground(Brand.sheet)
        .onChange(of: fitted, initial: true) { _, fitted in
            if detent != .large { detent = fitted }
        }
        // It goes with the estimate, once its hour or day has passed.
        .task {
            guard let end = request.ai.endsAt() else { return }
            try? await Task.sleep(for: .seconds(max(0, end.timeIntervalSinceNow)))
            if !Task.isCancelled { dismiss() }
        }
    }

    private var content: some View {
        let parcel = request.parcel, ai = request.ai
        let identity = CarrierVisualIdentity.of(parcel.displayedCarrier, catalog: catalog, language: localizer.language)
        let carrier = catalog.info(for: parcel.displayedCarrier, language: localizer.language).displayName
        return VStack(alignment: .leading, spacing: 14) {
            header
            VStack(alignment: .leading, spacing: 8) {
                Text(localizer.text("peekAi.sheet.caption"))
                    .font(.caption)
                    .foregroundStyle(Brand.secondaryText)
                    .padding(.horizontal, 2)
                HStack(alignment: .center, spacing: 12) {
                    VStack(alignment: .leading, spacing: 3) {
                        CarrierFleetMark(identity: identity).padding(.bottom, 4)
                        Text(localizer.peekAiHeading(ai)).font(.title3.weight(.semibold))
                        Text(localizer.peekAiCarrierWord(ai, carrier: carrier, estimate: localizer.parcelDeliveryEstimate(parcel)))
                            .font(.footnote.weight(.medium))
                            .foregroundStyle(identity.ink)
                    }
                    .fixedSize(horizontal: false, vertical: true)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    if !typeSize.isAccessibilitySize {
                        let known = parcel.carrier != .unknown || parcel.hasCarrierUpdate
                        SmallPip(label: known ? PipLabel(identity: identity) : nil)
                            .frame(width: 78)
                            .accessibilityHidden(true)
                    }
                }
                .padding(.horizontal, 16)
                .padding(.vertical, 14)
                .background(identity.surface, in: RoundedRectangle(cornerRadius: 18, style: .continuous))
                .accessibilityElement(children: .combine)
            }
            if let strip = localizer.peekAiStrip(ai) {
                PeekAiStripView(strip: strip, carrier: carrier)
            }
            VStack(spacing: 7) {
                fact(symbol: "lightbulb", title: localizer.text("peekAi.sheet.why"), text: localizer.peekAiWhy(ai, carrier: carrier))
                fact(symbol: "chart.bar", title: localizer.text("peekAi.sheet.sure"), text: localizer.peekAiRecord(ai))
                Toggle(isOn: Binding(get: { store.showPeekAi }, set: { shown in
                    store.setShowPeekAi(shown)
                    DeliveryAnalytics.shared.action(shown ? "peek-ai-on" : "peek-ai-off")
                })) {
                    row(symbol: "eye", title: localizer.text("peekAi.sheet.show"), text: localizer.text("peekAi.sheet.showHint"))
                }
                .rowToggle()
                .tint(ExperimentalPalette.delivered)
                .padding(.horizontal, 14)
                .padding(.vertical, 12)
                .frame(minHeight: 60)
                .background(Brand.raised, in: RoundedRectangle(cornerRadius: 14, style: .continuous))
                .accessibilityIdentifier("peekAi.show")
            }
            Text(localizer.text("peekAi.sheet.trained", ["carrier": carrier]))
                .font(.caption)
                .foregroundStyle(Brand.secondaryText)
                .multilineTextAlignment(.center)
                .fixedSize(horizontal: false, vertical: true)
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 2)
        }
        .padding(.horizontal, 22)
        .padding(.top, 26)
        .padding(.bottom, 18)
        .frame(maxWidth: 560)
        .frame(maxWidth: .infinity)
    }

    private var header: some View {
        HStack(alignment: .center, spacing: 16) {
            Text(localizer.text("peekAi.name"))
                .font(.title2.weight(.semibold))
                .frame(maxWidth: .infinity, alignment: .leading)
                .accessibilityAddTraits(.isHeader)
            Button { dismiss() } label: {
                Image(systemName: "xmark")
                    .font(.system(size: 13, weight: .semibold))
                    .frame(width: 36, height: 36)
                    .background(Brand.sheetButton, in: Circle())
                    .frame(width: 44, height: 44)
                    .contentShape(Circle())
            }
            .buttonStyle(.plain)
            .accessibilityLabel(localizer.text("common.close"))
        }
    }

    private func fact(symbol: String, title: String, text: String) -> some View {
        row(symbol: symbol, title: title, text: text)
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(.horizontal, 14)
            .padding(.vertical, 13)
            .frame(minHeight: 60)
            .background(Brand.raised, in: RoundedRectangle(cornerRadius: 14, style: .continuous))
            .accessibilityElement(children: .combine)
    }

    private func row(symbol: String, title: String, text: String) -> some View {
        HStack(spacing: 12) {
            // At the largest type sizes the words need the room.
            if !typeSize.isAccessibilitySize {
                Image(systemName: symbol)
                    .font(.system(size: 16))
                    .foregroundStyle(Brand.secondaryText)
                    .frame(width: 22)
                    .accessibilityHidden(true)
            }
            VStack(alignment: .leading, spacing: 2) {
                Text(title).font(.subheadline.weight(.semibold))
                Text(text).font(.caption).foregroundStyle(Brand.secondaryText)
            }
            .fixedSize(horizontal: false, vertical: true)
        }
    }
}

/// The days Peek AI expects, the carrier's date ringed; or, out for delivery, the hours, the past ones faded.
private struct PeekAiStripView: View {
    let strip: PeekAiStrip
    let carrier: String

    @EnvironmentObject private var localizer: Localizer

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack(spacing: 4) {
                ForEach(strip.cells) { cell in
                    VStack(spacing: 1) {
                        if !cell.over.isEmpty {
                            Text(cell.over).font(.caption2).foregroundStyle(Brand.secondaryText)
                        }
                        Text(cell.main).font(.footnote.weight(.medium)).foregroundStyle(Brand.ink)
                    }
                    .lineLimit(1)
                    .minimumScaleFactor(0.7)
                    .padding(.top, 5)
                    .padding(.bottom, 6)
                    .frame(maxWidth: .infinity)
                    .background(Brand.ink.opacity(cell.likely ? 0.13 : cell.maybe ? 0.05 : 0), in: RoundedRectangle(cornerRadius: 9))
                    .overlay {
                        if cell.carrier { RoundedRectangle(cornerRadius: 9).strokeBorder(Brand.secondaryText, lineWidth: 1.2) }
                    }
                    .opacity(cell.past ? 0.45 : 1)
                }
            }
            .accessibilityHidden(true)
            HStack(spacing: 12) {
                key(localizer.text(strip.kind == .hours ? "peekAi.sheet.hours" : "peekAi.sheet.days"), ringed: false)
                if strip.cells.contains(where: \.carrier) {
                    key(localizer.text("peekAi.sheet.carrierDate", ["carrier": carrier]), ringed: true)
                }
            }
            .font(.caption2)
            .foregroundStyle(Brand.secondaryText)
        }
        .padding(.horizontal, 14)
        .padding(.vertical, 12)
        .background(Color.secondary.opacity(0.08), in: RoundedRectangle(cornerRadius: 16))
        .accessibilityElement(children: .combine)
        .accessibilityLabel(spoken)
    }

    private func key(_ text: String, ringed: Bool) -> some View {
        HStack(spacing: 5) {
            RoundedRectangle(cornerRadius: 3)
                .fill(ringed ? Color.clear : Brand.secondaryText.opacity(0.3))
                .overlay { if ringed { RoundedRectangle(cornerRadius: 3).strokeBorder(Brand.secondaryText, lineWidth: 1.2) } }
                .frame(width: 9, height: 9)
            Text(text).fixedSize(horizontal: false, vertical: true)
        }
    }

    /// The likely cells, then the carrier's, as VoiceOver reads the strip: "Most likely days: Wed 25, Thu 26".
    private var spoken: String {
        let likely = strip.cells.filter(\.likely).map { [$0.over, $0.main].filter { !$0.isEmpty }.joined(separator: " ") }
        var parts = [localizer.text(strip.kind == .hours ? "peekAi.sheet.hours" : "peekAi.sheet.days") + ": " + likely.joined(separator: ", ")]
        if let ringed = strip.cells.first(where: \.carrier) {
            parts.append(localizer.text("peekAi.sheet.carrierDate", ["carrier": carrier]) + ": " + "\(ringed.over) \(ringed.main)")
        }
        return parts.joined(separator: ". ")
    }
}
