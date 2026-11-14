import SwiftUI
import WidgetKit

private struct ParcelListEntry: TimelineEntry {
    let date: Date
    let enabled: Bool
    let languageCode: String
    /// The widgets' parcels in their order, without those delivered more than a day ago.
    let parcels: [DeliveryWidgetParcel]
    /// The demo's parcels, which the gallery shows as they are rather than redacted.
    var isSample = false
}

private struct ParcelListProvider: TimelineProvider {
    /// The demo's next deliveries, in the reader's language.
    func placeholder(in context: Context) -> ParcelListEntry {
        let store = DeliveryWidgetSharedStore(appGroupIdentifier: DeliveryWidgetSharedStore.appGroupIdentifier())
        let languageCode = ExtensionLocalizer(savedLanguageCode: store?.languageCode).languageCode
        let folder = Bundle.main.url(forResource: DeliveryWidgetSample.folderName, withExtension: nil)
        let snapshot = folder.flatMap { DeliveryWidgetSample.snapshot(languageCode: languageCode, in: $0) }
        return ParcelListEntry(date: Date(), enabled: true, languageCode: languageCode, parcels: snapshot?.parcels ?? [], isSample: true)
    }

    func getSnapshot(in context: Context, completion: @escaping (ParcelListEntry) -> Void) {
        // The gallery shows the reader's parcels, and the demo's until there are some.
        let entry = currentEntry(at: Date())
        completion(context.isPreview && (!entry.enabled || entry.parcels.isEmpty) ? placeholder(in: context) : entry)
    }

    func getTimeline(in context: Context, completion: @escaping (Timeline<ParcelListEntry>) -> Void) {
        let now = Date()
        let entry = currentEntry(at: now)
        // The parcels' days change at midnight while the app stays closed.
        let entries = [entry] + DeliveryWidgetDays.changes(in: entry.parcels, after: now).map { currentEntry(at: $0) }
        let refresh = Calendar.current.date(byAdding: .minute, value: 20, to: now) ?? now.addingTimeInterval(1_200)
        completion(Timeline(entries: entries, policy: .after(refresh)))
    }

    private func currentEntry(at date: Date) -> ParcelListEntry {
        let store = DeliveryWidgetSharedStore(appGroupIdentifier: DeliveryWidgetSharedStore.appGroupIdentifier())
        let snapshot = store?.snapshot
        let languageCode = snapshot?.languageCode
            ?? ExtensionLocalizer(savedLanguageCode: store?.languageCode).languageCode
        return ParcelListEntry(
            date: date, enabled: store?.isEnabled ?? true, languageCode: languageCode,
            parcels: DeliveryWidgetSelection.listed(snapshot?.parcels ?? [], now: date)
        )
    }
}

private extension WidgetFamily {
    var listSize: DeliveryWidgetList.Size {
        switch self {
        case .systemMedium: .medium
        case .systemLarge, .systemExtraLarge: .large
        default: .small
        }
    }
}

private struct ParcelListWidgetView: View {
    @Environment(\.widgetFamily) private var family
    let entry: ParcelListEntry

    private var copy: ExtensionLocalizer { ExtensionLocalizer(languageCode: entry.languageCode) }

    var body: some View {
        let content = Group {
            if !entry.enabled {
                ParcelListState(symbol: "rectangle.slash", title: copy.text("widget.disabledTitle"),
                                description: copy.text("widget.disabledDescription"))
            } else if entry.parcels.isEmpty {
                ParcelListState(symbol: "shippingbox", title: copy.text("app.noneOnWay"),
                                description: copy.text("app.emptyDescription"))
            } else {
                ParcelListRows(parcels: entry.parcels, size: family.listSize, copy: copy)
            }
        }
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        // A small widget opens the list wherever it is tapped; a bigger one, from outside its rows.
        .widgetURL(DeliveryWidgetList.link)
        .environment(\.locale, ExtensionLocalizer.locale(for: entry.languageCode))
        // WidgetKit redacts a placeholder; the gallery's sample shows the widget as it will look.
        if entry.isSample { content.unredacted() } else { content }
    }
}

/// The rows, as many as the widget holds at the reader's text size: its size's number at the default size, fewer with
/// larger text, and a last line for the others. A small or medium widget tries its rows in smaller type before it
/// gives one up, so that the smallest, an iPhone SE's or an iPad mini's, still hold three to a column.
private struct ParcelListRows: View {
    let parcels: [DeliveryWidgetParcel]
    let size: DeliveryWidgetList.Size
    let copy: ExtensionLocalizer

    /// The width of the stage's symbol, which grows with the text.
    @ScaledMetric(relativeTo: .footnote) private var markWidth: CGFloat = 20
    @ScaledMetric(relativeTo: .caption) private var compactMarkWidth: CGFloat = 18

    private struct Layout: Hashable {
        /// The rows of a column; a medium widget has two.
        let perColumn: Int
        let compact: Bool
    }

    private var layouts: [Layout] {
        let most = min(size == .large ? size.rows : size.rows / (size == .medium ? 2 : 1), parcels.count)
        return stride(from: most, through: 1, by: -1).flatMap { rows in
            size == .large ? [Layout(perColumn: rows, compact: false)]
                : [Layout(perColumn: rows, compact: false), Layout(perColumn: rows, compact: true)]
        }
    }

    var body: some View {
        GeometryReader { proxy in
            ViewThatFits(in: .vertical) {
                ForEach(layouts, id: \.self) { layout in
                    list(layout, width: proxy.size.width)
                }
            }
        }
    }

    private func list(_ layout: Layout, width: CGFloat) -> some View {
        let page = DeliveryWidgetList.page(of: parcels, rows: layout.perColumn * (size == .medium ? 2 : 1))
        let metrics = ParcelListMetrics(compact: layout.compact, markWidth: layout.compact ? compactMarkWidth : markWidth)
        return VStack(alignment: .leading, spacing: metrics.rowSpacing) {
            switch size {
            case .large:
                ForEach(page.shown) { parcel in
                    link(parcel) { ParcelListLineRow(parcel: parcel, metrics: metrics, width: width) }
                }
            case .medium:
                HStack(alignment: .top, spacing: 12) {
                    ForEach(Array(DeliveryWidgetList.columns(of: page.shown, perColumn: layout.perColumn).enumerated()), id: \.offset) { column in
                        VStack(alignment: .leading, spacing: metrics.rowSpacing) {
                            ForEach(column.element) { parcel in
                                link(parcel) { ParcelListStackedRow(parcel: parcel, metrics: metrics) }
                            }
                        }
                        .frame(maxWidth: .infinity, alignment: .leading)
                    }
                }
            case .small:
                ForEach(page.shown) { ParcelListStackedRow(parcel: $0, metrics: metrics) }
            }
            if page.more > 0 {
                Spacer(minLength: 0)
                more(page.more, metrics: metrics)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
    }

    /// A row opens its parcel, where the widget lets a row be tapped apart.
    @ViewBuilder
    private func link(_ parcel: DeliveryWidgetParcel, @ViewBuilder row: () -> some View) -> some View {
        Link(destination: parcel.deepLink) { row().contentShape(Rectangle()) }
            .buttonStyle(.plain)
    }

    /// The parcels left out, level with the names; it opens the list.
    private func more(_ count: Int, metrics: ParcelListMetrics) -> some View {
        Link(destination: DeliveryWidgetList.link) {
            Text(copy.text("widget.listMore.many", count: count))
                .font(.caption.weight(.semibold))
                .foregroundStyle(.secondary)
                .lineLimit(1)
                .padding(.leading, metrics.markWidth + metrics.markSpacing)
                .frame(maxWidth: .infinity, alignment: .leading)
                .contentShape(Rectangle())
        }
        .buttonStyle(.plain)
    }
}

private struct ParcelListMetrics {
    let compact: Bool
    /// The box a stage's symbol fits in: wide ones, the bicycle and the truck, take its width, the others its height,
    /// so that the names line up.
    let markWidth: CGFloat

    var nameFont: Font { (compact ? Font.caption : .footnote).weight(.semibold) }
    var statusFont: Font { .caption2 }
    var markHeight: CGFloat { (markWidth * 0.75).rounded() }
    var markSpacing: CGFloat { compact ? 5 : 6 }
    var rowSpacing: CGFloat { compact ? 3 : 5 }
}

/// The symbol the app's list marks the parcel's stage with, in the stage's colour. A tinted Home Screen paints it
/// in its accent.
private struct ParcelListMark: View {
    let parcel: DeliveryWidgetParcel
    let metrics: ParcelListMetrics

    @Environment(\.colorScheme) private var scheme

    private var symbol: String {
        if let stage = parcel.stage { return stage.symbol }
        return parcel.isOutForDelivery ? "bicycle" : parcel.deliveredAt != nil ? "checkmark.circle.fill" : "shippingbox.fill"
    }

    var body: some View {
        Image(systemName: symbol)
            .resizable()
            .fontWeight(.semibold)
            .scaledToFit()
            .frame(width: metrics.markWidth, height: metrics.markHeight)
            .foregroundStyle(parcel.stage.map { DeliveryWidgetCardColors.color($0.tint, scheme) } ?? Color.secondary)
            .widgetAccentable()
            .accessibilityHidden(true)
    }
}

/// What VoiceOver reads for a row: the parcel's name, its carrier, its stage and its day.
private func listAccessibilityLabel(_ parcel: DeliveryWidgetParcel) -> String {
    let line = parcel.listLine
    return ([parcel.listName, parcel.named == false ? nil : parcel.carrier, line.status, line.day] as [String?])
        .compactMap { $0 }.joined(separator: ", ")
}

/// The stage, then the day. Where the line can't hold both, the day stays, as the symbol and its colour still mark the
/// stage; VoiceOver reads both.
private struct ParcelListStatus: View {
    let parcel: DeliveryWidgetParcel
    @Environment(\.locale) private var locale

    /// The stage and the day on one line.
    static func whole(_ parcel: DeliveryWidgetParcel, locale: Locale) -> String {
        let line = parcel.listLine
        return (line.day.map { "\(line.status) · \($0)" } ?? line.status).openingLine(in: locale)
    }

    var body: some View {
        let line = parcel.listLine
        ViewThatFits(in: .horizontal) {
            Text(Self.whole(parcel, locale: locale))
            if let day = line.day {
                Text(day.openingLine(in: locale))
                Text(line.status.openingLine(in: locale))
            }
        }
        .lineLimit(1)
        .foregroundStyle(.secondary)
    }
}

/// A row of a small or medium widget: the mark beside the name, over the stage and the day.
private struct ParcelListStackedRow: View {
    let parcel: DeliveryWidgetParcel
    let metrics: ParcelListMetrics

    var body: some View {
        HStack(spacing: metrics.markSpacing) {
            ParcelListMark(parcel: parcel, metrics: metrics)
            VStack(alignment: .leading, spacing: 0) {
                Text(parcel.listName)
                    .font(metrics.nameFont)
                    .foregroundStyle(.primary)
                    .lineLimit(1)
                ParcelListStatus(parcel: parcel)
                    .font(metrics.statusFont)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
        }
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(listAccessibilityLabel(parcel))
    }
}

/// A row of a large widget, on one line: the mark, the name, and at the end the stage and the day. Where the row can't
/// hold them whole, the name keeps at least two fifths of it.
private struct ParcelListLineRow: View {
    let parcel: DeliveryWidgetParcel
    let metrics: ParcelListMetrics
    /// The widget's width.
    let width: CGFloat
    @Environment(\.locale) private var locale

    var body: some View {
        ViewThatFits(in: .horizontal) {
            HStack(spacing: metrics.markSpacing) {
                ParcelListMark(parcel: parcel, metrics: metrics)
                name
                Spacer(minLength: 4)
                Text(ParcelListStatus.whole(parcel, locale: locale))
                    .font(.caption)
                    .foregroundStyle(.secondary)
                    .lineLimit(1)
            }
            HStack(spacing: metrics.markSpacing) {
                ParcelListMark(parcel: parcel, metrics: metrics)
                name
                    .frame(minWidth: width * 0.4, maxWidth: .infinity, alignment: .leading)
                ParcelListStatus(parcel: parcel)
                    .font(.caption)
                    .padding(.leading, 4)
                    .layoutPriority(1)
            }
        }
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(listAccessibilityLabel(parcel))
    }

    private var name: some View {
        Text(parcel.listName)
            .font(.footnote.weight(.semibold))
            .foregroundStyle(.primary)
            .lineLimit(1)
    }
}

/// Nothing to list, or the widgets turned off: in the words and the shape of the next-delivery widget's.
private struct ParcelListState: View {
    let symbol: String
    let title: String
    let description: String

    @Environment(\.widgetFamily) private var family

    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            Image(systemName: symbol)
                .font(.title3.weight(.bold))
                .widgetAccentable()
            // Where space runs out, the description gives up lines before the title does.
            Text(title)
                .font(.headline.weight(.bold))
                .lineLimit(2)
                .minimumScaleFactor(0.82)
                .layoutPriority(1)
            Text(description)
                .font(.caption)
                .foregroundStyle(.secondary)
                .lineLimit(family == .systemSmall ? 3 : 2)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
        .accessibilityElement(children: .combine)
    }
}

/// The widgets' parcels as a list: three in a small widget, six in a medium one, and up to fifteen in a large one.
struct ParcelListWidget: Widget {
    var body: some WidgetConfiguration {
        let store = DeliveryWidgetSharedStore(appGroupIdentifier: DeliveryWidgetSharedStore.appGroupIdentifier())
        // The app's language, else the device's: the gallery can open before the app has.
        let copy = ExtensionLocalizer(savedLanguageCode: store?.languageCode)
        return StaticConfiguration(kind: DeliveryWidgetSharedStore.listKind, provider: ParcelListProvider()) { entry in
            ParcelListWidgetView(entry: entry)
        }
        .configurationDisplayName(copy.text("widget.listGalleryName"))
        .description(copy.text("widget.listGalleryDescription"))
        .supportedFamilies([.systemSmall, .systemMedium, .systemLarge])
    }
}
