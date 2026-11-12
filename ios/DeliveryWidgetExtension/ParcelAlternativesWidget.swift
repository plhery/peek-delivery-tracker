import SwiftUI
import WidgetKit
#if os(iOS)
import UIKit
#else
import AppKit
#endif

// Preview: the designs of the Parcels widget to choose from, each its own widget reading the same parcels. A is the
// list as built; B shows every parcel as a small pass in its carrier's colours; C is A in the app list's order, under its
// headings; D shows the app list's Next up as a card, and the rest as A's rows. All four give a few parcels the room
// the list leaves empty (`ParcelPreviewFew`).

extension EnvironmentValues {
    /// Rows marked with their carrier's truck rather than their stage's symbol.
    @Entry var parcelTrucks: [UUID: ParcelPreviewPicture] = [:]
    /// Rows of parcels that need the reader write their stage in the app's warning colour.
    @Entry var parcelListWarns = false
}

enum ParcelPreviewPalette {
    /// The app's warning colour (`Brand.warning`).
    static func warning(_ scheme: ColorScheme) -> Color { DeliveryWidgetCardColors.color(["#963E19", "#FFB184"], scheme) }
}

/// One of a card's pictures, light and dark.
struct ParcelPreviewPicture {
    let light: Image
    let dark: Image

    init?(_ picture: DeliveryWidgetPicture, of card: DeliveryWidgetCard, in folder: URL) {
        func image(dark: Bool) -> Image? {
            guard let data = try? Data(contentsOf: picture.file(of: card, dark: dark, in: folder)) else { return nil }
            #if os(iOS)
            return UIImage(data: data, scale: card.scale).map(Image.init(uiImage:))
            #else
            guard let image = NSImage(data: data), let pixels = image.representations.first else { return nil }
            image.size = CGSize(width: CGFloat(pixels.pixelsWide) / card.scale, height: CGFloat(pixels.pixelsHigh) / card.scale)
            return Image(nsImage: image)
            #endif
        }
        guard let light = image(dark: false), let dark = image(dark: true) else { return nil }
        self.light = light
        self.dark = dark
    }

    func image(_ scheme: ColorScheme) -> Image { scheme == .dark ? dark : light }
}

/// A few parcels, whatever the design: one takes the whole widget; two stand side by side in a medium widget and one
/// above the other in a large one; three fill a large widget as the app list's cards.
enum ParcelPreviewFew {
    case one, pair, three

    init?(count: Int, family: WidgetFamily) {
        switch (count, family) {
        case (1, _): self = .one
        case (2, .systemMedium), (2, .systemLarge): self = .pair
        case (3, .systemLarge): self = .three
        default: return nil
        }
    }

    /// The route each card shows.
    func route(_ family: WidgetFamily) -> DeliveryWidgetPicture {
        switch self {
        case .one: family == .systemSmall ? .small : .wide
        case .pair: family == .systemMedium ? .half : .wide
        case .three: .half
        }
    }
}

struct ParcelPreviewEntry: TimelineEntry {
    let date: Date
    let alternative: ParcelPreviewAlternative
    let languageCode: String
    let parcels: [DeliveryWidgetParcel]
    /// The pictures of the parcels shown as cards: a few parcels, or D's next one.
    var cards: [UUID: DeliveryWidgetCardArt] = [:]
    /// The marks of B's passes, or the trucks of C's rows.
    var marks: [UUID: ParcelPreviewPicture] = [:]
    var isSample = false
}

struct ParcelPreviewProvider: TimelineProvider {
    let alternative: ParcelPreviewAlternative

    func placeholder(in context: Context) -> ParcelPreviewEntry {
        let store = DeliveryWidgetSharedStore(appGroupIdentifier: DeliveryWidgetSharedStore.appGroupIdentifier())
        let languageCode = ExtensionLocalizer(savedLanguageCode: store?.languageCode).languageCode
        let folder = Bundle.main.url(forResource: DeliveryWidgetSample.folderName, withExtension: nil)
        let snapshot = folder.flatMap { DeliveryWidgetSample.snapshot(languageCode: languageCode, in: $0) }
        var entry = entry(at: Date(), languageCode: languageCode, parcels: snapshot?.parcels ?? [], folder: folder, family: context.family)
        entry.isSample = true
        return entry
    }

    func getSnapshot(in context: Context, completion: @escaping (ParcelPreviewEntry) -> Void) {
        let entry = currentEntry(context.family, at: Date())
        completion(context.isPreview && entry.parcels.isEmpty ? placeholder(in: context) : entry)
    }

    func getTimeline(in context: Context, completion: @escaping (Timeline<ParcelPreviewEntry>) -> Void) {
        let now = Date()
        let refresh = Calendar.current.date(byAdding: .minute, value: 20, to: now) ?? now.addingTimeInterval(1_200)
        completion(Timeline(entries: [currentEntry(context.family, at: now)], policy: .after(refresh)))
    }

    private func currentEntry(_ family: WidgetFamily, at date: Date) -> ParcelPreviewEntry {
        let identifier = DeliveryWidgetSharedStore.appGroupIdentifier()
        let store = DeliveryWidgetSharedStore(appGroupIdentifier: identifier)
        let snapshot = store?.snapshot
        let languageCode = snapshot?.languageCode ?? ExtensionLocalizer(savedLanguageCode: store?.languageCode).languageCode
        return entry(at: date, languageCode: languageCode, parcels: DeliveryWidgetSelection.listed(snapshot?.parcels ?? [], now: date),
                     folder: DeliveryWidgetPicture.folder(appGroupIdentifier: identifier), family: family)
    }

    /// Reads only the pictures the widget shows: a route weighs a few hundred kilobytes, and a widget has little memory.
    private func entry(
        at date: Date, languageCode: String, parcels: [DeliveryWidgetParcel], folder: URL?, family: WidgetFamily
    ) -> ParcelPreviewEntry {
        let identifier = DeliveryWidgetSharedStore.appGroupIdentifier()
        let alternative = ParcelPreviewAlternative.chosen(appGroupIdentifier: identifier) ?? alternative
        var entry = ParcelPreviewEntry(date: date, alternative: alternative, languageCode: languageCode, parcels: parcels)
        guard let folder else { return entry }
        func card(_ parcel: DeliveryWidgetParcel, _ route: DeliveryWidgetPicture) {
            entry.cards[parcel.id] = parcel.card.flatMap { DeliveryWidgetCardArt($0, route: route, in: folder) }
        }
        if let few = ParcelPreviewFew(count: parcels.count, family: family) {
            for parcel in parcels { card(parcel, few.route(family)) }
            if entry.cards.count == parcels.count { return entry }
            entry.cards = [:]
        }
        switch alternative {
        case .list:
            break
        case .passes:
            for parcel in parcels.prefix(8) {
                entry.marks[parcel.id] = parcel.card.flatMap { ParcelPreviewPicture(.mark, of: $0, in: folder) }
            }
        case .sections:
            for parcel in parcels.prefix(24) {
                entry.marks[parcel.id] = parcel.card.flatMap { ParcelPreviewPicture(.truck, of: $0, in: folder) }
            }
        case .nextUp:
            if let next = ParcelNextUpView.next(of: parcels) {
                card(next, family == .systemSmall ? .small : family == .systemMedium ? .half : .wide)
            }
        }
        return entry
    }
}

extension WidgetFamily {
    var previewSize: DeliveryWidgetList.Size {
        switch self {
        case .systemMedium: .medium
        case .systemLarge, .systemExtraLarge: .large
        default: .small
        }
    }
}

struct ParcelPreviewView: View {
    let entry: ParcelPreviewEntry

    @Environment(\.widgetFamily) private var family
    @Environment(\.widgetContentMargins) private var margins

    private var copy: ExtensionLocalizer { ExtensionLocalizer(languageCode: entry.languageCode) }

    var body: some View {
        let content = Group {
            if entry.parcels.isEmpty {
                ParcelListState(symbol: "shippingbox", title: copy.text("app.noneOnWay"), description: copy.text("app.emptyDescription"))
                    .padding(margins)
                    .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
            } else if let few = ParcelPreviewFew(count: entry.parcels.count, family: family), entry.cards.count == entry.parcels.count {
                ParcelPreviewFewView(few: few, parcels: fewOrder, cards: entry.cards, copy: copy)
            } else {
                design
            }
        }
        .environment(\.locale, ExtensionLocalizer.locale(for: entry.languageCode))
        if entry.isSample { content.unredacted() } else { content }
    }

    /// The few parcels in the design's own order, so the cards read as its rows do at the other sizes.
    private var fewOrder: [DeliveryWidgetParcel] {
        switch entry.alternative {
        case .list, .passes:
            entry.parcels
        case .sections:
            ParcelSectionsView.ordered(entry.parcels)
        case .nextUp:
            ParcelNextUpView.next(of: entry.parcels).map { next in [next] + entry.parcels.filter { $0.id != next.id } } ?? entry.parcels
        }
    }

    @ViewBuilder
    private var design: some View {
        switch entry.alternative {
        case .list:
            ParcelListRows(parcels: entry.parcels, size: family.previewSize, copy: copy)
                .padding(margins)
                .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
                .widgetURL(DeliveryWidgetList.link)
        case .passes:
            ParcelPassesView(parcels: entry.parcels, marks: entry.marks, copy: copy)
        case .sections:
            ParcelSectionsView(parcels: entry.parcels, copy: copy)
                .environment(\.parcelTrucks, entry.marks)
                .environment(\.parcelListWarns, true)
        case .nextUp:
            ParcelNextUpView(parcels: entry.parcels, cards: entry.cards, copy: copy)
        }
    }
}

// MARK: - A few parcels

struct ParcelPreviewFewView: View {
    let few: ParcelPreviewFew
    let parcels: [DeliveryWidgetParcel]
    let cards: [UUID: DeliveryWidgetCardArt]
    let copy: ExtensionLocalizer

    @Environment(\.widgetFamily) private var family
    @Environment(\.widgetContentMargins) private var margins

    struct Shown: Identifiable {
        let parcel: DeliveryWidgetParcel
        let card: DeliveryWidgetCard
        let art: DeliveryWidgetCardArt

        var id: UUID { parcel.id }
    }

    private var shown: [Shown] {
        parcels.compactMap { parcel in
            guard let card = parcel.card, let art = cards[parcel.id] else { return nil }
            return Shown(parcel: parcel, card: card, art: art)
        }
    }

    var body: some View {
        let shown = shown
        switch few {
        case .one:
            if let only = shown.first { one(only) }
        case .pair:
            pair(shown)
        case .three:
            three(shown)
        }
    }

    /// The parcel takes the whole widget, as the Next delivery widget shows it; a large one also writes its last steps.
    private func one(_ only: Shown) -> some View {
        let route = few.route(family)
        return VStack(alignment: .leading, spacing: 10) {
            DeliveryWidgetCardView(parcel: only.parcel, card: only.card, art: only.art, shape: route)
                .frame(height: family == .systemLarge ? 128 : nil)
            if family == .systemLarge {
                ParcelPreviewSteps(steps: only.parcel.steps ?? [], card: only.card)
            }
        }
        .padding(margins)
        .containerBackground(for: .widget) { DeliveryWidgetCardBackground(card: only.card, art: only.art) }
        .widgetURL(only.parcel.deepLink)
    }

    private func pair(_ shown: [Shown]) -> some View {
        let route = few.route(family)
        let layout = family == .systemLarge ? AnyLayout(VStackLayout(spacing: 8)) : AnyLayout(HStackLayout(spacing: 8))
        return layout {
            ForEach(shown) { item in
                Link(destination: item.parcel.deepLink) {
                    DeliveryWidgetCardView(parcel: item.parcel, card: item.card, art: item.art, shape: route)
                        .padding(route == .half ? 10 : 14)
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                        .background { DeliveryWidgetCardBackground(card: item.card, art: item.art) }
                        .clipShape(RoundedRectangle(cornerRadius: 14, style: .continuous))
                }
                .buttonStyle(.plain)
            }
        }
        .padding(margins)
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        .widgetURL(DeliveryWidgetList.link)
    }

    private func three(_ shown: [Shown]) -> some View {
        VStack(spacing: 8) {
            ForEach(shown) { item in
                Link(destination: item.parcel.deepLink) {
                    ParcelPreviewListCard(parcel: item.parcel, card: item.card, art: item.art)
                }
                .buttonStyle(.plain)
            }
        }
        .padding(margins)
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        .widgetURL(DeliveryWidgetList.link)
    }
}

/// A parcel as the app's list shows it, in its carrier's colours: the mark and the day at the top, the name and the
/// status under them, and the route at the end, which the words keep clear of.
struct ParcelPreviewListCard: View {
    let parcel: DeliveryWidgetParcel
    let card: DeliveryWidgetCard
    let art: DeliveryWidgetCardArt

    @Environment(\.colorScheme) private var scheme

    var body: some View {
        let ink = DeliveryWidgetCardColors.color(card.ink, scheme)
        let look = art.look(scheme)
        VStack(alignment: .leading, spacing: 3) {
            HStack(alignment: .top, spacing: 8) {
                look.mark.resizable().scaledToFit().frame(height: 14)
                    .accessibilityLabel(parcel.carrier)
                Spacer(minLength: 0)
                if card.status != parcel.detail {
                    Text(parcel.detail).font(.caption).foregroundStyle(ink).lineLimit(1)
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
            .padding(.trailing, look.route == nil ? 0 : 104)
        }
        .padding(.vertical, 10)
        .padding(.horizontal, 13)
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
        .background(alignment: .bottomTrailing) {
            look.route?.resizable().scaledToFit().frame(width: 162)
        }
        .background(DeliveryWidgetCardColors.color(card.surface, scheme))
        .clipShape(RoundedRectangle(cornerRadius: 14, style: .continuous))
        .accessibilityElement(children: .combine)
    }
}

/// A parcel's last steps, the latest first, as its history lists them: the step, its place, and when.
struct ParcelPreviewSteps: View {
    let steps: [DeliveryWidgetStep]
    let card: DeliveryWidgetCard

    @Environment(\.colorScheme) private var scheme
    @Environment(\.locale) private var locale

    var body: some View {
        let ink = DeliveryWidgetCardColors.color(card.ink, scheme)
        let shown = Array(steps.prefix(4))
        VStack(alignment: .leading, spacing: 0) {
            ForEach(Array(shown.enumerated()), id: \.offset) { index, step in
                HStack(alignment: .top, spacing: 10) {
                    // The steps' thread: the latest step filled in.
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
                            Text(when(step.at))
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

    /// The time today, else the day and the time.
    private func when(_ date: Date) -> String {
        Calendar.current.isDateInToday(date)
            ? date.formatted(.dateTime.hour().minute().locale(locale))
            : date.formatted(.dateTime.weekday(.abbreviated).hour().minute().locale(locale))
    }
}

/// The parcels left out, under the rows; it opens the list.
struct ParcelPreviewMore: View {
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

/// A row opens its parcel where the widget lets a row be tapped apart: not in a small widget.
struct ParcelPreviewLink<Content: View>: View {
    let parcel: DeliveryWidgetParcel
    @ViewBuilder let content: Content

    @Environment(\.widgetFamily) private var family

    var body: some View {
        if family == .systemSmall {
            content
        } else {
            Link(destination: parcel.deepLink) { content.contentShape(Rectangle()) }
                .buttonStyle(.plain)
        }
    }
}

// MARK: - B · Passes

/// Every parcel a small pass cut from the app list's card, in its carrier's colours: two in a small widget, two columns
/// of two in a medium one, and one column in a large one.
struct ParcelPassesView: View {
    let parcels: [DeliveryWidgetParcel]
    let marks: [UUID: ParcelPreviewPicture]
    let copy: ExtensionLocalizer

    @Environment(\.widgetFamily) private var family
    /// A pass's height, which grows with the text: on three lines, or on two in a large widget.
    @ScaledMetric(relativeTo: .subheadline) private var passHeight: CGFloat = 61
    @ScaledMetric(relativeTo: .subheadline) private var widePassHeight: CGFloat = 47
    @ScaledMetric(relativeTo: .caption) private var moreHeight: CGFloat = 16

    private var columns: Int { family == .systemMedium ? 2 : 1 }

    var body: some View {
        GeometryReader { proxy in
            let height = family == .systemLarge ? widePassHeight : passHeight
            let rows = max(1, Int((proxy.size.height - moreHeight) / (height + 5)))
            passes(min(rows * columns, parcels.count), height: height)
        }
        .padding(10)
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        .widgetURL(DeliveryWidgetList.link)
    }

    private func passes(_ count: Int, height: CGFloat) -> some View {
        let page = DeliveryWidgetList.page(of: parcels, rows: count)
        let perColumn = (page.shown.count + columns - 1) / columns
        let starts = Array(stride(from: 0, to: page.shown.count, by: max(perColumn, 1)))
        return VStack(alignment: .leading, spacing: 5) {
            HStack(alignment: .top, spacing: 6) {
                ForEach(starts, id: \.self) { start in
                    VStack(spacing: 5) {
                        ForEach(page.shown[start..<min(start + perColumn, page.shown.count)]) { parcel in
                            ParcelPreviewLink(parcel: parcel) {
                                ParcelPass(parcel: parcel, mark: marks[parcel.id], wide: family == .systemLarge, height: height)
                            }
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .top)
                }
            }
            if page.more > 0 {
                Spacer(minLength: 0)
                ParcelPreviewMore(count: page.more, copy: copy, leading: 4)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
    }
}

/// A pass: the carrier's mark, the parcel's name and its stage and day, on the carrier's colour. A large widget's pass
/// is wider and on two lines: the mark and the day, then the name and the stage.
struct ParcelPass: View {
    let parcel: DeliveryWidgetParcel
    let mark: ParcelPreviewPicture?
    let wide: Bool
    let height: CGFloat

    @Environment(\.colorScheme) private var scheme

    var body: some View {
        let surface = parcel.card.map { DeliveryWidgetCardColors.color($0.surface, scheme) } ?? Color.primary.opacity(0.08)
        let ink = parcel.card.map { DeliveryWidgetCardColors.color($0.ink, scheme) } ?? Color.secondary
        let line = parcel.listLine
        VStack(alignment: .leading, spacing: wide ? 3 : 1) {
            HStack(alignment: .center, spacing: 6) {
                markView(ink)
                Spacer(minLength: 0)
                if wide, let day = line.day {
                    Text(day).font(.caption2).foregroundStyle(ink).lineLimit(1)
                }
            }
            if wide {
                HStack(alignment: .firstTextBaseline, spacing: 6) {
                    name
                    Spacer(minLength: 4)
                    Text(line.status).font(.caption2).foregroundStyle(ink).lineLimit(1).fixedSize()
                }
            } else {
                name
                ViewThatFits(in: .horizontal) {
                    Text(ParcelListStatus.whole(parcel))
                    if let day = line.day {
                        Text(day)
                        Text(line.status)
                    }
                }
                .font(.caption2)
                .foregroundStyle(ink)
                .lineLimit(1)
            }
        }
        .padding(.horizontal, 9)
        .frame(maxWidth: .infinity, minHeight: height, maxHeight: height, alignment: .leading)
        .background(surface, in: RoundedRectangle(cornerRadius: 12, style: .continuous))
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
            mark.image(scheme).resizable().scaledToFit().frame(height: wide ? 14 : 13)
        } else {
            Text(parcel.carrier).font(.caption2.weight(.bold)).foregroundStyle(ink).lineLimit(1)
        }
    }
}

// MARK: - C · Sections

/// A's rows in the app list's order, under its headings, each marked with its carrier's truck. A small widget has no
/// room for headings; a medium one runs them down two columns.
struct ParcelSectionsView: View {
    let parcels: [DeliveryWidgetParcel]
    let copy: ExtensionLocalizer

    @Environment(\.widgetFamily) private var family
    @Environment(\.widgetContentMargins) private var margins
    @ScaledMetric(relativeTo: .footnote) private var markWidth: CGFloat = 20
    @ScaledMetric(relativeTo: .caption) private var compactMarkWidth: CGFloat = 18

    static let order = ["attention", "today", "active", "delivered"]
    static let titles = [
        "attention": "app.needsAttention", "today": "app.arrivingToday", "active": "app.onTheWaySection", "delivered": "app.pastDeliveries",
    ]

    static func section(of parcel: DeliveryWidgetParcel) -> String {
        parcel.section ?? (parcel.deliveredAt != nil ? "delivered" : "active")
    }

    private var ordered: [DeliveryWidgetParcel] { Self.ordered(parcels) }

    /// The parcels in the app list's order: by section, and in the widget's order within one.
    static func ordered(_ parcels: [DeliveryWidgetParcel]) -> [DeliveryWidgetParcel] {
        parcels.enumerated().sorted { left, right in
            let l = Self.order.firstIndex(of: Self.section(of: left.element)) ?? Self.order.count
            let r = Self.order.firstIndex(of: Self.section(of: right.element)) ?? Self.order.count
            return l != r ? l < r : left.offset < right.offset
        }
        .map(\.element)
    }

    private enum Item: Hashable {
        case heading(String)
        case row(DeliveryWidgetParcel)

        var weight: Double { if case .heading = self { 0.6 } else { 1 } }
    }

    private struct Layout: Hashable {
        let rows: Int
        let compact: Bool
    }

    var body: some View {
        let ordered = ordered
        Group {
            if family == .systemSmall {
                ParcelListRows(parcels: ordered, size: .small, copy: copy)
            } else {
                GeometryReader { proxy in
                    ViewThatFits(in: .vertical) {
                        ForEach(layouts(ordered.count), id: \.self) { layout in
                            list(ordered, layout: layout, width: proxy.size.width)
                        }
                    }
                }
            }
        }
        .padding(margins)
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        .widgetURL(DeliveryWidgetList.link)
    }

    private func layouts(_ count: Int) -> [Layout] {
        let most = min(family == .systemLarge ? DeliveryWidgetList.Size.large.rows : 8, count)
        return stride(from: most, through: 1, by: -1).flatMap { rows in
            family == .systemLarge ? [Layout(rows: rows, compact: false)]
                : [Layout(rows: rows, compact: false), Layout(rows: rows, compact: true)]
        }
    }

    private func items(_ shown: ArraySlice<DeliveryWidgetParcel>) -> [Item] {
        var items: [Item] = []
        var last: String?
        for parcel in shown {
            let section = Self.section(of: parcel)
            if section != last { items.append(.heading(section)) }
            last = section
            items.append(.row(parcel))
        }
        return items
    }

    /// A medium widget's two columns, about as tall as each other; a heading never ends the first.
    private func columns(_ items: [Item]) -> [[Item]] {
        let total = items.reduce(0) { $0 + $1.weight }
        var split = 0
        var height = 0.0
        while split < items.count, height + items[split].weight / 2 < total / 2 {
            height += items[split].weight
            split += 1
        }
        if split > 0, case .heading = items[split - 1] { split -= 1 }
        return [Array(items.prefix(split)), Array(items.dropFirst(split))]
    }

    private func list(_ ordered: [DeliveryWidgetParcel], layout: Layout, width: CGFloat) -> some View {
        let metrics = ParcelListMetrics(compact: layout.compact, markWidth: layout.compact ? compactMarkWidth : markWidth)
        let items = items(ordered.prefix(layout.rows))
        let more = ordered.count - layout.rows
        return VStack(alignment: .leading, spacing: metrics.rowSpacing) {
            if family == .systemLarge {
                column(items, metrics: metrics, width: width)
            } else {
                HStack(alignment: .top, spacing: 12) {
                    ForEach(Array(columns(items).enumerated()), id: \.offset) { column in
                        self.column(column.element, metrics: metrics, width: width)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                    }
                }
            }
            if more > 0 {
                Spacer(minLength: 0)
                ParcelPreviewMore(count: more, copy: copy, leading: metrics.markWidth + metrics.markSpacing)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
    }

    private func column(_ items: [Item], metrics: ParcelListMetrics, width: CGFloat) -> some View {
        VStack(alignment: .leading, spacing: metrics.rowSpacing) {
            ForEach(items, id: \.self) { item in
                switch item {
                case .heading(let section):
                    heading(section)
                case .row(let parcel):
                    ParcelPreviewLink(parcel: parcel) {
                        if family == .systemLarge {
                            ParcelListLineRow(parcel: parcel, metrics: metrics, width: width)
                        } else {
                            ParcelListStackedRow(parcel: parcel, metrics: metrics)
                        }
                    }
                }
            }
        }
    }

    /// The app list's heading, made small: its title and how many parcels it has.
    private func heading(_ section: String) -> some View {
        HStack(spacing: 5) {
            Text(copy.text(Self.titles[section] ?? "app.onTheWaySection"))
                .font(.caption.weight(.semibold))
                .foregroundStyle(Color.primary)
                .lineLimit(1)
            Text("\(parcels.filter { Self.section(of: $0) == section }.count)")
                .font(.caption2.monospacedDigit())
                .foregroundStyle(.secondary)
                .padding(.horizontal, 5)
                .padding(.vertical, 1)
                .background(Color.primary.opacity(0.09), in: Capsule())
        }
        .accessibilityElement(children: .combine)
    }
}

// MARK: - D · Next up

/// The app list's Next up on the Next delivery widget's card, and the others as A's rows: beside it in a medium widget,
/// under it in a large one, and counted at its foot in a small one.
struct ParcelNextUpView: View {
    let parcels: [DeliveryWidgetParcel]
    let cards: [UUID: DeliveryWidgetCardArt]
    let copy: ExtensionLocalizer

    @Environment(\.widgetFamily) private var family
    @Environment(\.widgetContentMargins) private var margins
    @Environment(\.colorScheme) private var scheme

    /// The app list's Next up, else the widget's first parcel.
    static func next(of parcels: [DeliveryWidgetParcel]) -> DeliveryWidgetParcel? {
        parcels.first { $0.nextUp == true } ?? parcels.first
    }

    var body: some View {
        if let next = Self.next(of: parcels), let card = next.card, let art = cards[next.id] {
            let rest = parcels.filter { $0.id != next.id }
            switch family {
            case .systemSmall: small(next, card: card, art: art, rest: rest)
            case .systemMedium: medium(next, card: card, art: art, rest: rest)
            default: large(next, card: card, art: art, rest: rest)
            }
        } else {
            // A's list, until the card's pictures are drawn.
            ParcelListRows(parcels: parcels, size: family.previewSize, copy: copy)
                .padding(margins)
                .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
                .widgetURL(DeliveryWidgetList.link)
        }
    }

    private func small(_ next: DeliveryWidgetParcel, card: DeliveryWidgetCard, art: DeliveryWidgetCardArt, rest: [DeliveryWidgetParcel]) -> some View {
        VStack(alignment: .leading, spacing: 3) {
            DeliveryWidgetCardView(parcel: next, card: card, art: art, shape: .small)
            if !rest.isEmpty {
                Text(copy.text("widget.listMore.many", count: rest.count))
                    .font(.caption2.weight(.semibold))
                    .foregroundStyle(DeliveryWidgetCardColors.color(card.ink, scheme).opacity(0.75))
                    .lineLimit(1)
            }
        }
        .padding(margins)
        .containerBackground(for: .widget) { DeliveryWidgetCardBackground(card: card, art: art) }
        .widgetURL(next.deepLink)
    }

    private func nextCard(_ next: DeliveryWidgetParcel, card: DeliveryWidgetCard, art: DeliveryWidgetCardArt, shape: DeliveryWidgetPicture) -> some View {
        Link(destination: next.deepLink) {
            DeliveryWidgetCardView(parcel: next, card: card, art: art, shape: shape)
                .padding(shape == .half ? 10 : 14)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background { DeliveryWidgetCardBackground(card: card, art: art) }
                .clipShape(RoundedRectangle(cornerRadius: 14, style: .continuous))
        }
        .buttonStyle(.plain)
    }

    private func medium(_ next: DeliveryWidgetParcel, card: DeliveryWidgetCard, art: DeliveryWidgetCardArt, rest: [DeliveryWidgetParcel]) -> some View {
        GeometryReader { proxy in
            HStack(spacing: 12) {
                nextCard(next, card: card, art: art, shape: .half)
                    .frame(width: (proxy.size.width - 12) / 2)
                ParcelListRows(parcels: rest, size: .small, copy: copy)
            }
        }
        .padding(margins)
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        .widgetURL(DeliveryWidgetList.link)
    }

    private func large(_ next: DeliveryWidgetParcel, card: DeliveryWidgetCard, art: DeliveryWidgetCardArt, rest: [DeliveryWidgetParcel]) -> some View {
        VStack(spacing: 10) {
            nextCard(next, card: card, art: art, shape: .wide)
                .frame(height: 148)
            ParcelListRows(parcels: rest, size: .large, copy: copy)
        }
        .padding(margins)
        .containerBackground(for: .widget) { DeliveryWidgetCardPage() }
        .widgetURL(DeliveryWidgetList.link)
    }
}

// MARK: - The widgets

enum ParcelPreviewConfiguration {
    static func make(_ alternative: ParcelPreviewAlternative) -> some WidgetConfiguration {
        StaticConfiguration(kind: alternative.kind, provider: ParcelPreviewProvider(alternative: alternative)) { entry in
            ParcelPreviewView(entry: entry)
        }
        .configurationDisplayName(alternative.name)
        .description("A design of the Parcels widget, to choose from.")
        .supportedFamilies([.systemSmall, .systemMedium, .systemLarge])
        .contentMarginsDisabled()
    }
}

struct ParcelPreviewListWidget: Widget {
    var body: some WidgetConfiguration { ParcelPreviewConfiguration.make(.list) }
}

struct ParcelPreviewPassesWidget: Widget {
    var body: some WidgetConfiguration { ParcelPreviewConfiguration.make(.passes) }
}

struct ParcelPreviewSectionsWidget: Widget {
    var body: some WidgetConfiguration { ParcelPreviewConfiguration.make(.sections) }
}

struct ParcelPreviewNextUpWidget: Widget {
    var body: some WidgetConfiguration { ParcelPreviewConfiguration.make(.nextUp) }
}
