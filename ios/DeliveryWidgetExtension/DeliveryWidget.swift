import AppIntents
#if os(iOS)
import ActivityKit
#endif
import SwiftUI
import WidgetKit

private struct DeliveryWidgetEntry: TimelineEntry {
    let date: Date
    let enabled: Bool
    let languageCode: String
    let snapshot: DeliveryWidgetSnapshot?
    /// The parcels the widget shows: its first ones, or those its next button turned it to.
    var shown: [DeliveryWidgetParcel] = []
    var isFirstPage = true
    /// The parcel the next button turns the widget to, the first again from the last parcels; none when all fit at once.
    var next: UUID?
    /// The pictures of the cards the widget shows, in order; none unless every one of them has its pictures.
    var art: [DeliveryWidgetCardArt] = []
    /// The demo's cards, which the gallery shows as they are rather than redacted.
    var isSample = false
}

private struct DeliveryWidgetProvider: TimelineProvider {
    /// The demo's next deliveries, as the widget shows them in the reader's language.
    func placeholder(in context: Context) -> DeliveryWidgetEntry {
        let store = DeliveryWidgetSharedStore(appGroupIdentifier: DeliveryWidgetSharedStore.appGroupIdentifier())
        let languageCode = ExtensionLocalizer(savedLanguageCode: store?.languageCode).languageCode
        let folder = Bundle.main.url(forResource: DeliveryWidgetSample.folderName, withExtension: nil)
        let snapshot = folder.flatMap { DeliveryWidgetSample.snapshot(languageCode: languageCode, in: $0) }
        var entry = makeEntry(at: Date(), enabled: true, languageCode: languageCode, snapshot: snapshot, folder: folder, family: context.family)
        entry.isSample = true
        return entry
    }

    func getSnapshot(in context: Context, completion: @escaping (DeliveryWidgetEntry) -> Void) {
        // The gallery shows the parcels' own cards once the app has drawn them, and the demo's until then.
        let entry = currentEntry(context.family, at: Date())
        completion(context.isPreview && entry.art.isEmpty ? placeholder(in: context) : entry)
    }

    func getTimeline(in context: Context, completion: @escaping (Timeline<DeliveryWidgetEntry>) -> Void) {
        let now = Date()
        let entry = currentEntry(context.family, at: now)
        var entries = [entry]
        // Turned to other parcels, the widget shows its first ones again once the turn's spell ends.
        if !entry.isFirstPage, let ends = turn(context.family, at: now)?.ends {
            entries.append(currentEntry(context.family, at: ends))
        }
        let refresh = Calendar.current.date(byAdding: .minute, value: 20, to: now) ?? now.addingTimeInterval(1_200)
        completion(Timeline(entries: entries, policy: .after(refresh)))
    }

    private func currentEntry(_ family: WidgetFamily, at date: Date) -> DeliveryWidgetEntry {
        let identifier = DeliveryWidgetSharedStore.appGroupIdentifier()
        let store = DeliveryWidgetSharedStore(appGroupIdentifier: identifier)
        let snapshot = store?.snapshot
        let languageCode = snapshot?.languageCode
            ?? ExtensionLocalizer(savedLanguageCode: store?.languageCode).languageCode
        return makeEntry(at: date, enabled: store?.isEnabled ?? true, languageCode: languageCode, snapshot: snapshot,
                         folder: DeliveryWidgetPicture.folder(appGroupIdentifier: identifier), family: family,
                         turn: turn(family, at: date)?.parcel)
    }

    private func turn(_ family: WidgetFamily, at date: Date) -> DeliveryWidgetTurn? {
        DeliveryWidgetSharedStore(appGroupIdentifier: DeliveryWidgetSharedStore.appGroupIdentifier())?.turn(size: family.turnSize, now: date)
    }

    /// The page the widget was turned to, else its first, and the pictures of its cards: a small widget shows one parcel,
    /// a medium one two side by side, or one alone, across it.
    private func makeEntry(
        at date: Date, enabled: Bool, languageCode: String, snapshot: DeliveryWidgetSnapshot?, folder: URL?, family: WidgetFamily,
        turn: UUID? = nil
    ) -> DeliveryWidgetEntry {
        let medium = family == .systemMedium
        let pages = DeliveryWidgetSelection.pages(of: snapshot?.parcels ?? [], medium: medium, now: date)
        let index = turn.flatMap { id in pages.firstIndex { $0.contains { $0.id == id } } } ?? 0
        let shown = pages.indices.contains(index) ? pages[index] : []
        let shape: DeliveryWidgetPicture = medium ? shown.count > 1 ? .half : .wide : .small
        let art = folder.map { folder in shown.compactMap { $0.card.flatMap { DeliveryWidgetCardArt($0, route: shape, in: folder) } } } ?? []
        return DeliveryWidgetEntry(
            date: date, enabled: enabled, languageCode: languageCode, snapshot: snapshot, shown: shown, isFirstPage: index == 0,
            next: pages.count > 1 ? pages[(index + 1) % pages.count].first?.id : nil,
            art: !shown.isEmpty && art.count == shown.count ? art : []
        )
    }
}

private extension WidgetFamily {
    /// Widgets of one size turn together; those of the other keep their own page.
    var turnSize: String { self == .systemMedium ? "medium" : "small" }
}

/// Turns the widgets of one size to the next parcels. WidgetKit draws them again once it has.
struct ShowNextParcelIntent: AppIntent {
    static let title: LocalizedStringResource = "Next parcel"
    static let isDiscoverable = false

    @Parameter(title: "Parcel") var parcel: String
    @Parameter(title: "Widget size") var size: String

    init() {}

    init(parcel: UUID, size: String) {
        self.parcel = parcel.uuidString
        self.size = size
    }

    func perform() async throws -> some IntentResult {
        if let id = UUID(uuidString: parcel) {
            DeliveryWidgetSharedStore(appGroupIdentifier: DeliveryWidgetSharedStore.appGroupIdentifier())?
                .setTurn(DeliveryWidgetTurn(parcel: id, at: Date()), size: size)
        }
        return .result()
    }
}

private struct DeliveryWidgetView: View {
    @Environment(\.widgetFamily) private var family
    @Environment(\.widgetRenderingMode) private var renderingMode
    @Environment(\.colorScheme) private var scheme
    let entry: DeliveryWidgetEntry

    private var copy: ExtensionLocalizer {
        ExtensionLocalizer(languageCode: entry.languageCode)
    }

    private var parcels: [DeliveryWidgetParcel] { entry.shown }

    /// The parcels the widget shows as the app's cards, once the app has drawn every one of them.
    private var cards: [(parcel: DeliveryWidgetParcel, card: DeliveryWidgetCard, art: DeliveryWidgetCardArt)] {
        guard entry.enabled else { return [] }
        let cards = zip(parcels, entry.art).compactMap { parcel, art in parcel.card.map { (parcel, $0, art) } }
        return cards.count == entry.art.count ? cards : []
    }

    var body: some View {
        if cards.isEmpty {
            words
        } else {
            let content = cardContent
                .environment(\.locale, ExtensionLocalizer.locale(for: entry.languageCode))
            // WidgetKit redacts a placeholder; the gallery's sample shows the widget as it will look.
            if entry.isSample { content.unredacted() } else { content }
        }
    }

    @ViewBuilder
    private var cardContent: some View {
        let cards = cards
        if let only = cards.first, cards.count == 1 {
            let shape: DeliveryWidgetPicture = family == .systemMedium ? .wide : .small
            DeliveryWidgetCardView(
                parcel: only.parcel, card: only.card, art: only.art, shape: shape, leavesNext: entry.next != nil
            )
            .deliveryWidgetNext(markHeight: DeliveryWidgetCardView.markHeight(shape)) { nextButton(on: only.card) }
            .containerBackground(for: .widget) { DeliveryWidgetCardBackground(card: only.card, art: only.art) }
            .widgetURL(only.parcel.deepLink)
        } else {
            HStack(spacing: 8) {
                ForEach(cards, id: \.parcel.id) { shown in
                    Link(destination: shown.parcel.deepLink) {
                        DeliveryWidgetCardView(parcel: shown.parcel, card: shown.card, art: shown.art, shape: .half,
                                               leavesNext: entry.next != nil && shown.parcel.id == cards.last?.parcel.id)
                            .padding(10)
                            .background { DeliveryWidgetCardBackground(card: shown.card, art: shown.art) }
                            .clipShape(RoundedRectangle(cornerRadius: 14, style: .continuous))
                    }
                    .buttonStyle(.plain)
                }
            }
            // Over the last card, outside its link.
            .deliveryWidgetNext(markHeight: DeliveryWidgetCardView.markHeight(.half)) {
                cards.last.map { nextButton(on: $0.card).padding([.top, .trailing], 10) }
            }
            .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        }
    }

    /// Shown only when there are more parcels than the widget shows at once.
    @ViewBuilder
    private func nextButton(on card: DeliveryWidgetCard?) -> some View {
        if let next = entry.next {
            Button(intent: ShowNextParcelIntent(parcel: next, size: family.turnSize)) {
                DeliveryWidgetNextMark(ink: card.map { DeliveryWidgetCardColors.color($0.ink, scheme) } ?? WidgetPalette.ink)
            }
            .buttonStyle(.plain)
            .accessibilityLabel(copy.text("app.nextParcel"))
        }
    }

    private var words: some View {
        Group {
            if !entry.enabled {
                stateView(
                    symbol: "rectangle.slash",
                    title: copy.text("widget.disabledTitle"),
                    description: copy.text("widget.disabledDescription")
                )
            } else if parcels.isEmpty {
                stateView(
                    symbol: "shippingbox",
                    title: copy.text("app.noneOnWay"),
                    description: copy.text("app.emptyDescription")
                )
            } else if family == .systemMedium {
                mediumContent
            } else {
                smallContent(parcels[0])
            }
        }
        .foregroundStyle(WidgetPalette.ink)
        .containerBackground(for: .widget) {
            LinearGradient(
                colors: [WidgetPalette.accentBright, WidgetPalette.accent],
                startPoint: .topLeading,
                endPoint: .bottomTrailing
            )
        }
        .widgetURL(family == .systemSmall ? parcels.first?.deepLink : nil)
        .environment(\.locale, ExtensionLocalizer.locale(for: entry.languageCode))
    }

    private func smallContent(_ parcel: DeliveryWidgetParcel) -> some View {
        // Inside the system margins, the smallest widgets (an iPad mini's) still fit a two-line name.
        VStack(alignment: .leading, spacing: 0) {
            widgetHeader
            Spacer(minLength: 6)
            VStack(alignment: .leading, spacing: 3) {
                parcelBadge(parcel)
                Text(parcel.label)
                    .font(.headline.weight(.bold))
                    .lineLimit(2)
                    .minimumScaleFactor(0.82)
            }
            Spacer(minLength: 6)
            VStack(alignment: .leading, spacing: 1) {
                Text(parcel.carrier)
                    .font(.caption.weight(.semibold))
                    .foregroundStyle(WidgetPalette.ink.opacity(0.68))
                    .lineLimit(1)
                Text(parcel.detail)
                    .font(.caption.weight(.bold))
                    .lineLimit(1)
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .accessibilityElement(children: .combine)
    }

    private var mediumContent: some View {
        VStack(alignment: .leading, spacing: 8) {
            widgetHeader
            HStack(spacing: 10) {
                ForEach(parcels.prefix(2)) { parcel in
                    Link(destination: parcel.deepLink) {
                        VStack(alignment: .leading, spacing: 3) {
                            parcelBadge(parcel)
                            Text(parcel.label)
                                .font(.subheadline.weight(.bold))
                                .lineLimit(1)
                                .minimumScaleFactor(0.85)
                            Text(parcel.carrier)
                                .font(.caption2.weight(.semibold))
                                .foregroundStyle(WidgetPalette.ink.opacity(0.64))
                                .lineLimit(1)
                            Text(parcel.detail)
                                .font(.caption.weight(.bold))
                                .lineLimit(1)
                        }
                        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
                        .padding(.horizontal, 10)
                        .padding(.vertical, 8)
                        .background(.white.opacity(0.36), in: RoundedRectangle(cornerRadius: 14))
                        .contentShape(Rectangle())
                    }
                    .buttonStyle(.plain)
                    .accessibilityElement(children: .combine)
                }
                if parcels.count == 1 { Spacer(minLength: 0) }
            }
        }
    }

    private var widgetHeader: some View {
        HStack(spacing: 6) {
            // A tinted or clear Home Screen flattens the mark's colours into a blank tile, so it keeps the symbol.
            if renderingMode == .fullColor {
                PeekMark(size: 16)
            } else {
                Image(systemName: "shippingbox.fill")
                    .font(.caption.weight(.bold))
            }
            Text(copy.text("app.title"))
                .font(.caption2.weight(.heavy))
                .textCase(.uppercase)
                .tracking(0.6)
                .lineLimit(1)
            Spacer(minLength: 0)
        }
        .foregroundStyle(WidgetPalette.ink.opacity(0.72))
        .deliveryWidgetNext(markHeight: 16) { nextButton(on: nil) }
    }

    private func parcelBadge(_ parcel: DeliveryWidgetParcel) -> some View {
        let delivered = parcel.deliveredAt != nil
        // Tighter than a Label, so "Out for delivery" fits a small widget's width.
        return HStack(spacing: 4) {
            Image(systemName: parcel.isOutForDelivery ? "bicycle" : delivered ? "checkmark.circle.fill" : "arrow.right.circle.fill")
                .imageScale(.small)
                .accessibilityHidden(true)
            Text(copy.text(parcel.isOutForDelivery ? "stage.out_for_delivery" : delivered ? "stage.delivered" : "app.onTheWaySection"))
                .minimumScaleFactor(0.7)
        }
        .font(.caption2.weight(.heavy))
        .textCase(.uppercase)
        .lineLimit(1)
    }

    private func stateView(symbol: String, title: String, description: String) -> some View {
        VStack(alignment: .leading, spacing: 6) {
            Image(systemName: symbol)
                .font(.title3.weight(.bold))
            // Where space runs out, the description gives up lines before the title does.
            Text(title)
                .font(.headline.weight(.bold))
                .lineLimit(2)
                .minimumScaleFactor(0.82)
                .layoutPriority(1)
            Text(description)
                .font(.caption)
                .foregroundStyle(WidgetPalette.ink.opacity(0.67))
                .lineLimit(family == .systemMedium ? 2 : 3)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
        .accessibilityElement(children: .combine)
    }
}

private enum WidgetPalette {
    static let accent = Color(red: 0.96, green: 0.73, blue: 0.13)
    static let accentBright = Color(red: 1.0, green: 0.84, blue: 0.34)
    static let ink = Color(red: 0.10, green: 0.10, blue: 0.09)
}

struct NextDeliveryWidget: Widget {
    var body: some WidgetConfiguration {
        let store = DeliveryWidgetSharedStore(
            appGroupIdentifier: DeliveryWidgetSharedStore.appGroupIdentifier()
        )
        // The app's language, else the device's: the gallery can open before the app has.
        let copy = ExtensionLocalizer(savedLanguageCode: store?.languageCode)
        return StaticConfiguration(
            kind: DeliveryWidgetSharedStore.kind,
            provider: DeliveryWidgetProvider()
        ) { entry in
            DeliveryWidgetView(entry: entry)
        }
        .configurationDisplayName(copy.text("widget.galleryName"))
        .description(copy.text("widget.galleryDescription"))
        .supportedFamilies([.systemSmall, .systemMedium])
    }
}

// A Mac has no Live Activities: its widget is the next deliveries alone.
#if os(iOS)
private struct DeliveryLiveActivityView: View {
    let state: DeliveryActivityAttributes.ContentState

    private var secondaryLine: String {
        state.parcel.detail == state.parcel.status
            ? state.parcel.carrier
            : "\(state.parcel.carrier) · \(state.parcel.detail)"
    }

    var body: some View {
        HStack(spacing: 13) {
            Image(systemName: state.parcel.phase.symbol)
                .font(.title3.weight(.bold))
                .frame(width: 42, height: 42)
                .background(state.parcel.phase.tint, in: Circle())
                .foregroundStyle(WidgetPalette.ink)
            VStack(alignment: .leading, spacing: 3) {
                Text(state.parcel.status)
                    .font(.caption2.weight(.heavy))
                    .textCase(.uppercase)
                    .foregroundStyle(.secondary)
                Text(state.parcel.label)
                    .font(.headline.weight(.bold))
                    .lineLimit(1)
                Text(secondaryLine)
                    .font(.caption.weight(.semibold))
                    .foregroundStyle(.secondary)
                    .lineLimit(1)
            }
            Spacer(minLength: 4)
            Image(systemName: "chevron.right")
                .font(.caption.weight(.bold))
                .foregroundStyle(.secondary)
        }
        .padding()
        .activityBackgroundTint(Color(red: 0.12, green: 0.12, blue: 0.11))
        .activitySystemActionForegroundColor(WidgetPalette.accentBright)
        .widgetURL(state.parcel.deepLink)
        .environment(\.locale, ExtensionLocalizer.locale(for: state.languageCode))
    }
}

struct DeliveryLiveActivityWidget: Widget {
    var body: some WidgetConfiguration {
        ActivityConfiguration(for: DeliveryActivityAttributes.self) { context in
            DeliveryLiveActivityView(state: context.state)
        } dynamicIsland: { context in
            let parcel = context.state.parcel
            return DynamicIsland {
                DynamicIslandExpandedRegion(.leading) {
                    Label(
                        parcel.status,
                        systemImage: parcel.phase.symbol
                    )
                    .font(.caption.weight(.bold))
                    .foregroundStyle(parcel.phase.tint)
                }
                DynamicIslandExpandedRegion(.trailing) {
                    Text(parcel.supplementalDetail)
                        .font(.caption.weight(.bold))
                        .lineLimit(1)
                }
                DynamicIslandExpandedRegion(.bottom) {
                    VStack(alignment: .leading, spacing: 3) {
                        Text(parcel.label)
                            .font(.headline.weight(.bold))
                            .lineLimit(1)
                        Text(parcel.detail == parcel.status
                            ? parcel.carrier
                            : "\(parcel.carrier) · \(parcel.detail)")
                            .font(.caption)
                            .foregroundStyle(.secondary)
                            .lineLimit(1)
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                }
            } compactLeading: {
                Image(systemName: parcel.phase.symbol)
                    .foregroundStyle(parcel.phase.tint)
            } compactTrailing: {
                Text(parcel.supplementalDetail)
                    .font(.caption2.weight(.bold))
                    .lineLimit(1)
                    .minimumScaleFactor(0.7)
                    .foregroundStyle(parcel.phase.tint)
            } minimal: {
                Image(systemName: parcel.phase.symbol)
                    .foregroundStyle(parcel.phase.tint)
            }
            .widgetURL(parcel.deepLink)
            .keylineTint(parcel.phase.tint)
        }
    }
}

private extension DeliveryActivityPhase {
    var symbol: String {
        switch self {
        case .outForDelivery: "bicycle"
        case .delivered: "checkmark.circle.fill"
        case .failedAttempt: "exclamationmark.triangle.fill"
        case .exception: "exclamationmark.circle.fill"
        case .readyForPickup: "storefront.fill"
        case .returned: "arrow.uturn.backward.circle.fill"
        case .ended: "shippingbox.fill"
        }
    }

    var tint: Color {
        switch self {
        case .delivered: .green
        case .failedAttempt, .exception, .readyForPickup, .returned: .orange
        case .outForDelivery, .ended: WidgetPalette.accentBright
        }
    }
}

private extension DeliveryActivityParcel {
    var supplementalDetail: String {
        detail == status ? carrier : detail
    }
}
#endif

@main
struct DeliveryTrackerWidgetBundle: WidgetBundle {
    var body: some Widget {
        NextDeliveryWidget()
        #if os(iOS)
        DeliveryLiveActivityWidget()
        #endif
    }
}
