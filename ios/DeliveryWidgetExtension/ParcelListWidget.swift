import SwiftUI
import WidgetKit

private struct ParcelListEntry: TimelineEntry {
    let date: Date
    let enabled: Bool
    let languageCode: String
    /// The widgets' parcels in their order, without those delivered more than a day ago.
    let parcels: [DeliveryWidgetParcel]
    /// How the widget shows them: by how many there are for its size, and as rows until their cards are drawn.
    var design = DeliveryWidgetList.Design.sections
    /// The cards' pictures, or the passes' marks.
    var art: [UUID: DeliveryWidgetCardArt] = [:]
    /// The trucks of the rows' carriers.
    var trucks: [String: ParcelListTruck] = [:]
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
        var entry = makeEntry(at: Date(), enabled: true, languageCode: languageCode, parcels: snapshot?.parcels ?? [],
                              pictures: folder, trucks: nil, size: context.family.listSize)
        entry.isSample = true
        return entry
    }

    func getSnapshot(in context: Context, completion: @escaping (ParcelListEntry) -> Void) {
        // The gallery shows the reader's parcels, and the demo's until there are some.
        let entry = currentEntry(context.family, at: Date())
        completion(context.isPreview && (!entry.enabled || entry.parcels.isEmpty) ? placeholder(in: context) : entry)
    }

    func getTimeline(in context: Context, completion: @escaping (Timeline<ParcelListEntry>) -> Void) {
        let now = Date()
        let entry = currentEntry(context.family, at: now)
        // The parcels' days and sections, and their steps' times, change at midnight while the app stays closed.
        let entries = [entry] + DeliveryWidgetDays.changes(in: entry.parcels, after: now).map { currentEntry(context.family, at: $0) }
        let refresh = Calendar.current.date(byAdding: .minute, value: 20, to: now) ?? now.addingTimeInterval(1_200)
        completion(Timeline(entries: entries, policy: .after(refresh)))
    }

    private func currentEntry(_ family: WidgetFamily, at date: Date) -> ParcelListEntry {
        let identifier = DeliveryWidgetSharedStore.appGroupIdentifier()
        let store = DeliveryWidgetSharedStore(appGroupIdentifier: identifier)
        let snapshot = store?.snapshot
        let languageCode = snapshot?.languageCode
            ?? ExtensionLocalizer(savedLanguageCode: store?.languageCode).languageCode
        return makeEntry(
            at: date, enabled: store?.isEnabled ?? true, languageCode: languageCode,
            parcels: DeliveryWidgetSelection.listed(snapshot?.parcels ?? [], now: date),
            pictures: DeliveryWidgetPicture.folder(appGroupIdentifier: identifier),
            trucks: DeliveryWidgetTruck.folder(appGroupIdentifier: identifier), size: family.listSize
        )
    }

    /// Reads only the pictures the design shows: a route weighs a few hundred kilobytes, and a widget has little memory.
    /// Cards and passes need every parcel's; until the app has drawn them, the parcels are rows.
    private func makeEntry(
        at date: Date, enabled: Bool, languageCode: String, parcels: [DeliveryWidgetParcel], pictures: URL?, trucks: URL?,
        size: DeliveryWidgetList.Size
    ) -> ParcelListEntry {
        var entry = ParcelListEntry(date: date, enabled: enabled, languageCode: languageCode, parcels: parcels)
        guard enabled, !parcels.isEmpty else { return entry }
        let design = DeliveryWidgetList.design(of: parcels.count, size: size)
        if design != .sections, let pictures {
            let route = design == .cards ? ParcelCards.route(count: parcels.count, size: size) : nil
            var art: [UUID: DeliveryWidgetCardArt] = [:]
            for parcel in parcels {
                guard let card = parcel.card, let drawn = DeliveryWidgetCardArt(card, route: route, in: pictures) else { break }
                art[parcel.id] = drawn
            }
            if art.count == parcels.count {
                entry.design = design
                entry.art = art
                return entry
            }
        }
        if let trucks {
            for carrier in Set(parcels.prefix(DeliveryWidgetList.Size.large.rows).compactMap(\.carrierId)) {
                entry.trucks[carrier] = ParcelListTruck(carrier: carrier, in: trucks)
            }
        }
        return entry
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

/// A carrier's truck as the app drew it, light and dark.
private struct ParcelListTruck {
    let light: Image
    let dark: Image

    init?(carrier: String, in folder: URL) {
        guard let light = DeliveryWidgetImage.load(DeliveryWidgetTruck.file(of: carrier, dark: false, in: folder), scale: DeliveryWidgetTruck.scale),
              let dark = DeliveryWidgetImage.load(DeliveryWidgetTruck.file(of: carrier, dark: true, in: folder), scale: DeliveryWidgetTruck.scale)
        else { return nil }
        self.light = light
        self.dark = dark
    }

    func image(_ scheme: ColorScheme) -> Image { scheme == .dark ? dark : light }
}

private struct ParcelListWidgetView: View {
    @Environment(\.widgetFamily) private var family
    let entry: ParcelListEntry

    private var copy: ExtensionLocalizer { ExtensionLocalizer(languageCode: entry.languageCode) }

    var body: some View {
        let content = design
            .environment(\.locale, ExtensionLocalizer.locale(for: entry.languageCode))
        // WidgetKit redacts a placeholder; the gallery's sample shows the widget as it will look.
        if entry.isSample { content.unredacted() } else { content }
    }

    @ViewBuilder
    private var design: some View {
        if !entry.enabled {
            state(symbol: "rectangle.slash", title: copy.text("widget.disabledTitle"), description: copy.text("widget.disabledDescription"))
        } else if entry.parcels.isEmpty {
            state(symbol: "shippingbox", title: copy.text("app.noneOnWay"), description: copy.text("app.emptyDescription"))
        } else {
            switch entry.design {
            case .cards:
                ParcelCards(parcels: DeliveryWidgetList.cardOrder(entry.parcels), art: entry.art, size: family.listSize, now: entry.date)
            case .passes:
                ParcelPasses(parcels: entry.parcels, art: entry.art, size: family.listSize, copy: copy)
            case .sections:
                ParcelSections(parcels: entry.parcels, trucks: entry.trucks, size: family.listSize, copy: copy)
            }
        }
    }

    private func state(symbol: String, title: String, description: String) -> some View {
        ParcelListState(symbol: symbol, title: title, description: description)
            .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
            .widgetURL(DeliveryWidgetList.link)
    }
}

// MARK: - Cards

/// A few parcels as the Next delivery widget's cards, the app list's Next up first. One fills the widget as Next delivery
/// shows it, and a large widget writes its last steps under it; two stand side by side in a medium widget and one above
/// the other in a large one; three fill a large widget as the app list's cards.
private struct ParcelCards: View {
    let parcels: [DeliveryWidgetParcel]
    let art: [UUID: DeliveryWidgetCardArt]
    let size: DeliveryWidgetList.Size
    let now: Date

    @Environment(\.widgetContentMargins) private var margins

    /// The route each card shows.
    static func route(count: Int, size: DeliveryWidgetList.Size) -> DeliveryWidgetPicture {
        switch (size, count) {
        case (.small, _): .small
        case (.medium, 1), (.large, 1), (.large, 2): .wide
        default: .half
        }
    }

    private struct Shown: Identifiable {
        let parcel: DeliveryWidgetParcel
        let card: DeliveryWidgetCard
        let art: DeliveryWidgetCardArt

        var id: UUID { parcel.id }
    }

    var body: some View {
        let shown = parcels.compactMap { parcel in
            parcel.card.flatMap { card in art[parcel.id].map { Shown(parcel: parcel, card: card, art: $0) } }
        }
        let route = Self.route(count: shown.count, size: size)
        if let only = shown.first, shown.count == 1 {
            one(only, route: route)
        } else if size == .large, shown.count > 2 {
            listCards(shown)
        } else {
            pair(shown, route: route)
        }
    }

    /// The parcel fills the widget. A large one shows the card across its top, as tall as the route over the words, and
    /// the parcel's last steps under it.
    private func one(_ only: Shown, route: DeliveryWidgetPicture) -> some View {
        Group {
            if size == .large {
                GeometryReader { proxy in
                    // The wide route is drawn 364 by 104 and fills the widget's width; the words start under it.
                    let width = proxy.size.width + margins.leading + margins.trailing
                    VStack(alignment: .leading, spacing: 12) {
                        DeliveryWidgetCardView(parcel: only.parcel, card: only.card, art: only.art, shape: .wide)
                            .frame(height: max(width * 104 / 364 + 40 - margins.top, 0))
                        ParcelListSteps(steps: only.parcel.steps ?? [], card: only.card, now: now)
                    }
                }
            } else {
                DeliveryWidgetCardView(parcel: only.parcel, card: only.card, art: only.art, shape: route)
            }
        }
        .containerBackground(for: .widget) { DeliveryWidgetCardBackground(card: only.card, art: only.art) }
        .widgetURL(only.parcel.deepLink)
    }

    /// Two cards, as the Next delivery widget shows a pair: side by side, or one above the other in a large widget.
    private func pair(_ shown: [Shown], route: DeliveryWidgetPicture) -> some View {
        let layout = size == .large ? AnyLayout(VStackLayout(spacing: 8)) : AnyLayout(HStackLayout(spacing: 8))
        return layout {
            ForEach(shown) { item in
                Link(destination: item.parcel.deepLink) {
                    DeliveryWidgetCardView(parcel: item.parcel, card: item.card, art: item.art, shape: route)
                        .padding(route == .half ? 10 : 14)
                        .background { DeliveryWidgetCardBackground(card: item.card, art: item.art) }
                        .clipShape(RoundedRectangle(cornerRadius: 14, style: .continuous))
                }
                .buttonStyle(.plain)
            }
        }
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        .widgetURL(DeliveryWidgetList.link)
    }

    private func listCards(_ shown: [Shown]) -> some View {
        VStack(spacing: 8) {
            ForEach(shown) { item in
                Link(destination: item.parcel.deepLink) {
                    ParcelListCard(parcel: item.parcel, card: item.card, art: item.art)
                }
                .buttonStyle(.plain)
            }
        }
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        .widgetURL(DeliveryWidgetList.link)
    }
}

/// A parcel as the app's list shows it, in its carrier's colours: the mark and the day at the top, the name and the
/// status at the bottom, and the route at the end, which the words keep clear of.
private struct ParcelListCard: View {
    let parcel: DeliveryWidgetParcel
    let card: DeliveryWidgetCard
    let art: DeliveryWidgetCardArt

    @Environment(\.colorScheme) private var scheme
    @Environment(\.widgetRenderingMode) private var renderingMode
    @Environment(\.locale) private var locale

    var body: some View {
        let ink = DeliveryWidgetCardColors.color(card.ink, scheme)
        let look = art.look(scheme)
        let colored = renderingMode == .fullColor
        VStack(alignment: .leading, spacing: 2) {
            HStack(alignment: .top, spacing: 8) {
                look.mark.resizable().scaledToFit().frame(height: 14)
                    .accessibilityLabel(parcel.carrier)
                Spacer(minLength: 0)
                if let day = parcel.listLine.day {
                    Text(day.openingLine(in: locale)).font(.caption).foregroundStyle(ink).lineLimit(1)
                }
            }
            Spacer(minLength: 2)
            Group {
                Text(parcel.listName)
                    .font(.headline.weight(.semibold))
                    .foregroundStyle(Color.primary)
                    .lineLimit(1)
                Text(card.status)
                    .font(.caption)
                    .foregroundStyle(ink)
                    .lineLimit(1)
            }
            .padding(.trailing, look.route != nil && colored ? 104 : 0)
        }
        .padding(.vertical, 10)
        .padding(.horizontal, 13)
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
        .background(alignment: .bottomTrailing) {
            if colored { look.route?.resizable().scaledToFit().frame(width: 162) }
        }
        .background(colored ? DeliveryWidgetCardColors.color(card.surface, scheme) : Color.primary.opacity(0.12))
        .clipShape(RoundedRectangle(cornerRadius: 14, style: .continuous))
        .accessibilityElement(children: .combine)
    }
}

/// A parcel's last steps, the latest first, joined by a thread: each step's words, its time, and its place.
private struct ParcelListSteps: View {
    let steps: [DeliveryWidgetStep]
    let card: DeliveryWidgetCard
    let now: Date

    @Environment(\.colorScheme) private var scheme
    @Environment(\.locale) private var locale

    var body: some View {
        let ink = DeliveryWidgetCardColors.color(card.ink, scheme)
        let shown = Array(steps.prefix(DeliveryWidgetSelection.steps))
        VStack(alignment: .leading, spacing: 0) {
            ForEach(Array(shown.enumerated()), id: \.offset) { index, step in
                HStack(alignment: .top, spacing: 10) {
                    // The thread: a dot for each step, the latest filled in.
                    VStack(spacing: 2) {
                        Circle()
                            .fill(index == 0 ? ink : ink.opacity(0.35))
                            .frame(width: 7, height: 7)
                            .padding(.top, 5)
                        if index < shown.count - 1 {
                            Capsule().fill(ink.opacity(0.22)).frame(width: 1.5).frame(maxHeight: .infinity)
                        }
                    }
                    .frame(width: 7)
                    VStack(alignment: .leading, spacing: 1) {
                        HStack(alignment: .firstTextBaseline, spacing: 8) {
                            Text(step.text)
                                .font(.footnote.weight(index == 0 ? .semibold : .regular))
                                .foregroundStyle(Color.primary)
                                .lineLimit(1)
                            Spacer(minLength: 4)
                            Text(step.time(now: now, locale: locale))
                                .font(.caption)
                                .foregroundStyle(ink)
                                .lineLimit(1)
                                .fixedSize()
                        }
                        if let place = step.place {
                            Text(place).font(.caption2).foregroundStyle(.secondary).lineLimit(1)
                        }
                    }
                    .padding(.bottom, index < shown.count - 1 ? 9 : 0)
                }
                .accessibilityElement(children: .combine)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
    }
}

// MARK: - Passes

/// Each parcel a pass cut from the app list's card, in its carrier's colours, the passes sharing the widget's height:
/// one above the other in a small or large widget, and two columns in a medium one, the first parcel alone in the first
/// of three. Where larger text leaves room for fewer, as many as fit, then a line for the others.
private struct ParcelPasses: View {
    let parcels: [DeliveryWidgetParcel]
    let art: [UUID: DeliveryWidgetCardArt]
    let size: DeliveryWidgetList.Size
    let copy: ExtensionLocalizer

    /// A pass's least height, which grows with the text: on three lines, or on two in a large widget.
    @ScaledMetric(relativeTo: .subheadline) private var passHeight: CGFloat = 54
    @ScaledMetric(relativeTo: .subheadline) private var widePassHeight: CGFloat = 44
    @ScaledMetric(relativeTo: .caption) private var moreHeight: CGFloat = 16

    private let spacing: CGFloat = 6

    var body: some View {
        GeometryReader { proxy in
            passes(height: proxy.size.height)
        }
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        .widgetURL(DeliveryWidgetList.link)
    }

    /// The passes in their columns, read down the first then the second, and how many are left out.
    private func columns(height: CGFloat) -> (columns: [[DeliveryWidgetParcel]], more: Int) {
        let least = size == .large ? widePassHeight : passHeight
        let rows = { (room: CGFloat) in max(1, Int((room + spacing) / (least + spacing))) }
        let medium = size == .medium
        // A medium widget's first column has the fewer.
        func split(_ shown: [DeliveryWidgetParcel]) -> [[DeliveryWidgetParcel]] {
            medium && shown.count > 1 ? [Array(shown.prefix(shown.count / 2)), Array(shown.dropFirst(shown.count / 2))] : [shown]
        }
        let needed = medium ? parcels.count - parcels.count / 2 : parcels.count
        if rows(height) >= needed { return (split(parcels), 0) }
        let shown = Array(parcels.prefix(rows(height - moreHeight - spacing) * (medium ? 2 : 1)))
        return (split(shown), parcels.count - shown.count)
    }

    private func passes(height: CGFloat) -> some View {
        let (columns, more) = columns(height: height)
        return VStack(alignment: .leading, spacing: spacing) {
            HStack(alignment: .top, spacing: spacing) {
                ForEach(Array(columns.enumerated()), id: \.offset) { _, column in
                    VStack(spacing: spacing) {
                        ForEach(column) { parcel in
                            ParcelListLink(parcel: parcel, size: size) {
                                ParcelPass(parcel: parcel, mark: art[parcel.id], wide: size == .large)
                            }
                        }
                    }
                }
            }
            if more > 0 {
                ParcelListMore(count: more, copy: copy, leading: 4)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
    }
}

/// A pass: the carrier's mark at the top, then the parcel's name, and its stage and day, on the carrier's colour. A large
/// widget's pass writes the day beside the mark, and the stage after the name.
private struct ParcelPass: View {
    let parcel: DeliveryWidgetParcel
    let mark: DeliveryWidgetCardArt?
    let wide: Bool

    @Environment(\.colorScheme) private var scheme
    @Environment(\.widgetRenderingMode) private var renderingMode
    @Environment(\.locale) private var locale

    var body: some View {
        let colored = renderingMode == .fullColor
        let surface = parcel.card.map { DeliveryWidgetCardColors.color($0.surface, scheme) } ?? Color.primary.opacity(0.08)
        let ink = parcel.card.map { DeliveryWidgetCardColors.color($0.ink, scheme) } ?? Color.secondary
        let line = parcel.listLine
        VStack(alignment: .leading, spacing: 1) {
            HStack(alignment: .center, spacing: 6) {
                markView(ink)
                Spacer(minLength: 0)
                if wide, let day = line.day {
                    Text(day.openingLine(in: locale)).font(.caption2).foregroundStyle(ink).lineLimit(1)
                }
            }
            Spacer(minLength: 2)
            if wide {
                HStack(alignment: .firstTextBaseline, spacing: 6) {
                    name
                    Spacer(minLength: 4)
                    Text(line.status.openingLine(in: locale)).font(.caption2).foregroundStyle(ink).lineLimit(1).fixedSize()
                }
            } else {
                name
                ViewThatFits(in: .horizontal) {
                    Text(ParcelListStatus.whole(parcel, locale: locale))
                    if let day = line.day {
                        Text(day.openingLine(in: locale))
                        Text(line.status.openingLine(in: locale))
                    }
                }
                .font(.caption2)
                .foregroundStyle(ink)
                .lineLimit(1)
            }
        }
        .padding(.horizontal, 10)
        .padding(.vertical, 8)
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
        .background(colored ? surface : Color.primary.opacity(0.12), in: RoundedRectangle(cornerRadius: 12, style: .continuous))
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(listAccessibilityLabel(parcel))
    }

    private var name: some View {
        Text(parcel.listName)
            .font(.subheadline.weight(.semibold))
            .foregroundStyle(Color.primary)
            .lineLimit(1)
    }

    @ViewBuilder
    private func markView(_ ink: Color) -> some View {
        if let mark {
            mark.look(scheme).mark.resizable().scaledToFit().frame(height: 13)
        } else {
            Text(parcel.carrier).font(.caption2.weight(.bold)).foregroundStyle(ink).lineLimit(1)
        }
    }
}

// MARK: - Sections

/// Rows under the app list's sections, each marked with its carrier's truck: in a small widget without the headings,
/// down two columns in a medium one, and on one line each in a large one. As many as the widget holds at the reader's
/// text size, then a line for the others. A small or medium widget tries its rows in smaller type before it gives one
/// up, so that the smallest, an iPhone SE's or an iPad mini's, still hold three to a column.
private struct ParcelSections: View {
    let parcels: [DeliveryWidgetParcel]
    let trucks: [String: ParcelListTruck]
    let size: DeliveryWidgetList.Size
    let copy: ExtensionLocalizer

    /// The width of a row's mark, which grows with the text.
    @ScaledMetric(relativeTo: .footnote) private var markWidth: CGFloat = 20
    @ScaledMetric(relativeTo: .caption) private var compactMarkWidth: CGFloat = 18

    static let titles = [
        "attention": "app.needsAttention", "today": "app.arrivingToday", "active": "app.onTheWaySection",
        "delivered": "app.pastDeliveries", "returned": "app.returned", "archived": "app.archived",
    ]

    private struct Layout: Hashable {
        let rows: Int
        let compact: Bool
    }

    var body: some View {
        let sections = DeliveryWidgetList.sections(parcels)
        GeometryReader { proxy in
            ViewThatFits(in: .vertical) {
                ForEach(layouts, id: \.self) { layout in
                    list(sections, layout: layout, width: proxy.size.width)
                }
            }
        }
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        // A small widget opens the list wherever it is tapped; a bigger one, from outside its rows.
        .widgetURL(DeliveryWidgetList.link)
    }

    private var layouts: [Layout] {
        let most = min(size == .medium ? 8 : size.rows, parcels.count)
        return stride(from: most, through: 1, by: -1).flatMap { rows in
            size == .large ? [Layout(rows: rows, compact: false)] : [Layout(rows: rows, compact: false), Layout(rows: rows, compact: true)]
        }
    }

    /// The rows and their headings, a small widget having no room for headings, down one column, or two in a medium
    /// widget (`DeliveryWidgetList.columns`); the line for the others at the foot of the column they would follow.
    private func list(_ sections: [DeliveryWidgetList.Section], layout: Layout, width: CGFloat) -> some View {
        let metrics = ParcelListMetrics(compact: layout.compact, markWidth: layout.compact ? compactMarkWidth : markWidth)
        let lines = DeliveryWidgetList.lines(sections, rows: layout.rows, headings: size != .small)
        return Group {
            if size == .medium {
                HStack(alignment: .top, spacing: 12) {
                    ForEach(Array(DeliveryWidgetList.columns(lines).enumerated()), id: \.offset) { _, column in
                        self.column(column, metrics: metrics, width: width)
                    }
                }
            } else {
                column(lines, metrics: metrics, width: width)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
    }

    private func column(_ lines: [DeliveryWidgetList.Line], metrics: ParcelListMetrics, width: CGFloat) -> some View {
        VStack(alignment: .leading, spacing: metrics.rowSpacing) {
            ForEach(lines, id: \.self) { line in
                switch line {
                case let .heading(kind, count):
                    heading(Self.titles[kind] ?? "app.onTheWaySection", count: count)
                case let .more(count):
                    Spacer(minLength: 0)
                    ParcelListMore(count: count, copy: copy, leading: metrics.markWidth + metrics.markSpacing)
                case let .row(parcel):
                    let truck = parcel.carrierId.flatMap { trucks[$0] }
                    ParcelListLink(parcel: parcel, size: size) {
                        if size == .large {
                            ParcelListLineRow(parcel: parcel, truck: truck, metrics: metrics, width: width)
                        } else {
                            ParcelListStackedRow(parcel: parcel, truck: truck, metrics: metrics)
                        }
                    }
                }
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
    }

    /// The app list's heading, made small: its title and how many parcels it has.
    private func heading(_ title: String, count: Int) -> some View {
        HStack(spacing: 5) {
            Text(copy.text(title))
                .font(.caption.weight(.semibold))
                .foregroundStyle(Color.primary)
                .lineLimit(1)
            Text("\(count)")
                .font(.caption2.monospacedDigit())
                .foregroundStyle(.secondary)
                .padding(.horizontal, 5)
                .padding(.vertical, 1)
                .background(Color.primary.opacity(0.09), in: Capsule())
        }
        .padding(.top, 2)
        .accessibilityElement(children: .combine)
        .accessibilityAddTraits(.isHeader)
    }
}

// MARK: - Rows

/// A row or a pass opens its parcel where the widget lets one be tapped apart: not in a small widget.
private struct ParcelListLink<Content: View>: View {
    let parcel: DeliveryWidgetParcel
    let size: DeliveryWidgetList.Size
    @ViewBuilder let content: Content

    var body: some View {
        if size == .small {
            content
        } else {
            Link(destination: parcel.deepLink) { content.contentShape(Rectangle()) }
                .buttonStyle(.plain)
        }
    }
}

/// The parcels left out, under the others; it opens the list.
private struct ParcelListMore: View {
    let count: Int
    let copy: ExtensionLocalizer
    var leading: CGFloat = 0

    var body: some View {
        Link(destination: DeliveryWidgetList.link) {
            Text(copy.text("widget.listMore.many", count: count))
                .font(.caption.weight(.semibold))
                .foregroundStyle(.secondary)
                .lineLimit(1)
                .padding(.leading, leading)
                .frame(maxWidth: .infinity, alignment: .leading)
                .contentShape(Rectangle())
        }
        .buttonStyle(.plain)
    }
}

private struct ParcelListMetrics {
    let compact: Bool
    /// The box a row's mark fits in: a truck, else a stage's symbol, the wide ones, the bicycle and the truck, taking its
    /// width and the others its height, so that the names line up.
    let markWidth: CGFloat

    var nameFont: Font { (compact ? Font.caption : .footnote).weight(.semibold) }
    var statusFont: Font { .caption2 }
    var markHeight: CGFloat { (markWidth * 0.75).rounded() }
    var markSpacing: CGFloat { compact ? 5 : 6 }
    var rowSpacing: CGFloat { compact ? 3 : 5 }
}

/// The carrier's truck, without its name; until the app has drawn it, the symbol the app's list marks the parcel's
/// stage with, in the stage's colour, which a tinted Home Screen paints in its accent.
private struct ParcelListMark: View {
    let parcel: DeliveryWidgetParcel
    let truck: ParcelListTruck?
    let metrics: ParcelListMetrics

    @Environment(\.colorScheme) private var scheme

    private var symbol: String {
        if let stage = parcel.stage { return stage.symbol }
        return parcel.isOutForDelivery ? "bicycle" : parcel.deliveredAt != nil ? "checkmark.circle.fill" : "shippingbox.fill"
    }

    var body: some View {
        Group {
            if let truck {
                truck.image(scheme)
                    .resizable()
                    .scaledToFit()
            } else {
                Image(systemName: symbol)
                    .resizable()
                    .fontWeight(.semibold)
                    .scaledToFit()
                    .foregroundStyle(parcel.stage.map { DeliveryWidgetCardColors.color($0.tint, scheme) } ?? Color.secondary)
                    .widgetAccentable()
            }
        }
        .frame(width: metrics.markWidth, height: metrics.markHeight)
        .accessibilityHidden(true)
    }
}

/// What VoiceOver reads for a row or a pass: the parcel's name, its carrier, its stage and its day.
private func listAccessibilityLabel(_ parcel: DeliveryWidgetParcel) -> String {
    let line = parcel.listLine
    return ([parcel.listName, parcel.named == false ? nil : parcel.carrier, line.status, line.day] as [String?])
        .compactMap { $0 }.joined(separator: ", ")
}

/// The stage, then the day, in the warning colour where the parcel needs the reader. Where the line can't hold both, the
/// day stays, as the mark still names the carrier; VoiceOver reads both.
private struct ParcelListStatus: View {
    let parcel: DeliveryWidgetParcel
    @Environment(\.locale) private var locale
    @Environment(\.colorScheme) private var scheme

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
        .foregroundStyle(parcel.section == "attention" ? AnyShapeStyle(DeliveryWidgetCardColors.warning(scheme)) : AnyShapeStyle(.secondary))
    }
}

/// A row of a small or medium widget: the mark beside the name, over the stage and the day.
private struct ParcelListStackedRow: View {
    let parcel: DeliveryWidgetParcel
    let truck: ParcelListTruck?
    let metrics: ParcelListMetrics

    var body: some View {
        HStack(spacing: metrics.markSpacing) {
            ParcelListMark(parcel: parcel, truck: truck, metrics: metrics)
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
    let truck: ParcelListTruck?
    let metrics: ParcelListMetrics
    /// The widget's width.
    let width: CGFloat

    var body: some View {
        ViewThatFits(in: .horizontal) {
            HStack(spacing: metrics.markSpacing) {
                ParcelListMark(parcel: parcel, truck: truck, metrics: metrics)
                name
                Spacer(minLength: 4)
                ParcelListStatus(parcel: parcel)
                    .font(.caption)
                    .fixedSize()
            }
            HStack(spacing: metrics.markSpacing) {
                ParcelListMark(parcel: parcel, truck: truck, metrics: metrics)
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

/// The widgets' parcels by how many there are: up to one, two or three for a small, medium or large widget as cards; up
/// to twice as many as passes; more as rows under the app list's sections.
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
