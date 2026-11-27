import SwiftUI

extension EnvironmentValues {
    /// Whether cards show Peek AI's estimates, as Settings has it.
    @Entry var showsPeekAi = true
}

extension Localizer {
    /// Peek AI's name, and how VoiceOver reads its words, in the app's language.
    var peekAiCopy: PeekAiCopy { PeekAiCopy(name: text("peekAi.name"), says: text("peekAi.says", ["words": "{{words}}"])) }
}

/// The parcel whose estimate Peek AI's sheet explains. The sheet reads the parcel as it is now, from the page that holds
/// it, so that it goes once the estimate does and switching Peek AI off there leaves it.
struct PeekAiSheetRequest: Identifiable {
    let id: UUID
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
    @Environment(\.dynamicTypeSize) private var typeSize
    @ObservedObject private var catalog = CarrierCatalog.shared

    var body: some View {
        let carrier = catalog.info(for: parcel.displayedCarrier, language: localizer.language).displayName
        let claim = localizer.peekAiClaim(ai, now: now)
        // At the largest text sizes the claim goes under the name, which leaves it too little room beside it.
        let phrase = typeSize.isAccessibilitySize
            ? AnyLayout(VStackLayout(alignment: .leading, spacing: 3)) : AnyLayout(HStackLayout(alignment: .firstTextBaseline, spacing: 5))
        VStack(alignment: .leading, spacing: 3) {
            HStack(alignment: .center, spacing: 6) {
                phrase {
                    PeekAiPill(name: localizer.text("peekAi.name"))
                    Text(claim).fontWeight(.semibold)
                        .fixedSize(horizontal: false, vertical: true)
                }
                .foregroundStyle(Brand.ink)
                .frame(maxWidth: .infinity, alignment: .leading)
                .accessibilityElement(children: .ignore)
                .accessibilityLabel(localizer.text("peekAi.says", ["words": claim]))
                Button {
                    open(PeekAiSheetRequest(id: parcel.id))
                    DeliveryAnalytics.shared.action("peek-ai-open")
                } label: {
                    Image(systemName: "info.circle")
                        .font(.subheadline)
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

/// Peek AI on the parcel's page, drawn again each minute, so that "5 hours ago" stays true. The card goes, with its room
/// in the page, the moment its estimate ends.
struct PeekAiPageCard: View {
    let parcel: Parcel
    let open: (PeekAiSheetRequest) -> Void
    /// When the estimate shown last ended: the page draws again then, and the card is left out.
    @State private var ended: Date?

    var body: some View {
        let now = max(Date(), ended ?? .distantPast)
        if let ai = parcel.shownPeekAi(at: now) {
            TimelineView(.everyMinute) { context in
                PeekAiCard(parcel: parcel, ai: parcel.shownPeekAi(at: context.date) ?? ai, now: context.date, open: open)
            }
            .task(id: ai) {
                guard let end = ai.end(after: now) else { return }
                try? await Task.sleep(for: .seconds(max(0, end.timeIntervalSinceNow)))
                if !Task.isCancelled { ended = end }
            }
        }
    }
}

/// The (i): what Peek AI expects on the parcel's own card, with the carrier's word under it, the days or hours, why, how
/// often it is right, the switch that hides it, and what it is.
struct PeekAiSheet: View {
    /// The parcel as it is now, which the page holding the sheet looks up each time it draws: none once it has gone.
    let parcel: Parcel?

    @EnvironmentObject private var store: ParcelStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dismiss) private var dismiss
    @Environment(\.dynamicTypeSize) private var typeSize
    @ObservedObject private var catalog = CarrierCatalog.shared
    @State private var contentHeight: CGFloat = 560
    @State private var detent = PresentationDetent.height(560)
    /// What the sheet showed last, which stays while it goes.
    @State private var kept: Shown?

    private var fitted: PresentationDetent { .height(contentHeight) }

    private struct Shown: Equatable {
        let parcel: Parcel
        let ai: PeekAi
    }

    /// The parcel and its estimate while it holds.
    private func estimate(at now: Date) -> Shown? {
        parcel.flatMap { parcel in parcel.shownPeekAi(at: now).map { Shown(parcel: parcel, ai: $0) } }
    }

    var body: some View {
        let live = estimate(at: Date())
        ScrollView {
            // Drawn again each minute: the hours gone fade, and "5 hours ago" moves on.
            TimelineView(.everyMinute) { context in
                if let shown = estimate(at: context.date) ?? live ?? kept {
                    content(shown.parcel, shown.ai, now: context.date)
                }
            }
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
        // It goes with the estimate: as soon as the parcel is delivered or its estimate is withdrawn, else once it ends.
        .task(id: live) {
            guard let live else { return dismiss() }
            kept = live
            guard let end = live.ai.end(after: Date()) else { return }
            try? await Task.sleep(for: .seconds(max(0, end.timeIntervalSinceNow)))
            if !Task.isCancelled { dismiss() }
        }
    }

    private func content(_ parcel: Parcel, _ ai: PeekAi, now: Date) -> some View {
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
                        Text(localizer.peekAiHeading(ai, now: now)).font(.title3.weight(.semibold))
                        Text(localizer.peekAiCarrierWord(ai, carrier: carrier, estimate: localizer.parcelDeliveryEstimate(parcel, now: now)))
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
            if let strip = localizer.peekAiStrip(ai, now: now) {
                PeekAiStripView(strip: strip, carrier: carrier)
            }
            VStack(spacing: 7) {
                fact(symbol: "lightbulb", title: localizer.text("peekAi.sheet.why"), text: localizer.peekAiReason(ai, carrier: carrier, now: now))
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

/// How far a strip cell's words shrink to keep to their line.
private let peekAiCellShrink: CGFloat = 0.7

/// A strip cell's bold line, on one line that shrinks to 70 % where it must, as the cell's other words. A time that
/// still has no room, "10:00 AM" in bold at a large text size, stands over its "AM" on two lines instead of ending in
/// an ellipsis.
struct PeekAiCellMain: View {
    let words: String

    var body: some View {
        if let stacked = peekAiStackedClock(words) {
            ViewThatFits(in: .horizontal) {
                ShrinkingWidth(share: peekAiCellShrink) { Text(words) }
                Text(stacked).lineLimit(2).multilineTextAlignment(.center)
            }
        } else {
            Text(words)
        }
    }
}

/// Gives ViewThatFits its one view's ideal width at `share` of the view's own: the width it takes once shrunk as far
/// as its minimum scale lets it. Laid out, the view has the width it is offered.
private struct ShrinkingWidth: Layout {
    let share: CGFloat

    func sizeThatFits(proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) -> CGSize {
        guard let view = subviews.first else { return .zero }
        guard proposal.width == nil else { return view.sizeThatFits(proposal) }
        let ideal = view.sizeThatFits(ProposedViewSize(width: nil, height: proposal.height))
        // A point spare: a font shrunk to the last step still rounds its glyphs.
        return CGSize(width: (ideal.width * share).rounded(.up) + 1, height: ideal.height)
    }

    func placeSubviews(in bounds: CGRect, proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) {
        subviews.first?.place(at: CGPoint(x: bounds.midX, y: bounds.midY), anchor: .center, proposal: ProposedViewSize(bounds.size))
    }
}

/// The days Peek AI expects, each shaded by its chance, the carrier's date ringed; or, out for delivery, the hours, the
/// past ones faded.
private struct PeekAiStripView: View {
    let strip: PeekAiStrip
    let carrier: String

    @EnvironmentObject private var localizer: Localizer
    @Environment(\.colorSchemeContrast) private var contrast

    /// With more contrast asked for, the shades step further apart and every word takes the ink.
    private var more: Bool { contrast == .increased }

    /// Each shade's share of the ink, in four steps from 1 to 4 (the likeliest); none at 0.
    private func fill(_ level: Int) -> Color {
        let shares = more ? [0, 0.08, 0.15, 0.23, 0.32] : [0, 0.05, 0.11, 0.19, 0.30]
        return Brand.ink.opacity(shares[min(max(level, 0), shares.count - 1)])
    }

    /// The quieter words' grey, or the ink with more contrast.
    private var soft: Color { more ? Brand.ink : Brand.secondaryText }

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack(spacing: 4) {
                ForEach(strip.cells) { cell in
                    VStack(spacing: 1) {
                        // On the two strongest shades the weekday takes the ink, which the grey would lack contrast
                        // against; the likeliest is heavier, a mark that needs no colour.
                        if !cell.over.isEmpty {
                            Text(cell.over)
                                .font(.caption2.weight(cell.level == 4 ? .semibold : .regular))
                                .foregroundStyle(cell.level >= 3 ? Brand.ink : soft)
                        }
                        PeekAiCellMain(words: cell.main)
                            .font(.footnote.weight(cell.level == 4 ? .bold : .medium))
                            .foregroundStyle(Brand.ink)
                    }
                    .lineLimit(1)
                    .minimumScaleFactor(peekAiCellShrink)
                    .padding(.top, 5)
                    .padding(.bottom, 6)
                    // Every cell as tall as the tallest, which may hold a time over two lines.
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                    .background(fill(cell.level), in: RoundedRectangle(cornerRadius: 9))
                    .overlay {
                        if cell.carrier {
                            RoundedRectangle(cornerRadius: 9).strokeBorder(soft, lineWidth: more ? 1.5 : 1.2)
                        }
                    }
                    .opacity(cell.past ? 0.45 : 1)
                }
            }
            .fixedSize(horizontal: false, vertical: true)
            // Five hours side by side, "10:00 AM" among them, have no room for larger type: VoiceOver reads them in words.
            .dynamicTypeSize(...DynamicTypeSize.xxLarge)
            .accessibilityHidden(true)
            // The key: the four shades between their two ends, then the carrier's ring. Side by side where they fit.
            ViewThatFits(in: .horizontal) {
                HStack(spacing: 14) { scale; ring }
                VStack(alignment: .leading, spacing: 4) { scale; ring }
            }
            .font(.caption2)
            .foregroundStyle(soft)
            .accessibilityHidden(true)
        }
        .padding(.horizontal, 14)
        .padding(.vertical, 12)
        .background(Color.secondary.opacity(0.08), in: RoundedRectangle(cornerRadius: 16))
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(localizer.peekAiStripSpoken(strip, carrier: carrier))
    }

    private var scale: some View {
        HStack(spacing: 5) {
            Text(localizer.text("peekAi.sheet.lessLikely"))
            HStack(spacing: 2) {
                // Each swatch edged in a faint ink: the faintest shade alone would barely part from the panel.
                ForEach(1...4, id: \.self) { level in
                    RoundedRectangle(cornerRadius: 3).fill(fill(level))
                        .overlay(RoundedRectangle(cornerRadius: 3).strokeBorder(Brand.ink.opacity(0.25), lineWidth: 1))
                        .frame(width: 10, height: 10)
                }
            }
            Text(localizer.text("peekAi.sheet.moreLikely"))
        }
        .fixedSize(horizontal: false, vertical: true)
    }

    @ViewBuilder private var ring: some View {
        if strip.cells.contains(where: \.carrier) {
            HStack(spacing: 5) {
                RoundedRectangle(cornerRadius: 3).strokeBorder(soft, lineWidth: 1.2).frame(width: 10, height: 10)
                Text(localizer.text("peekAi.sheet.carrierDate", ["carrier": carrier])).fixedSize(horizontal: false, vertical: true)
            }
        }
    }
}
