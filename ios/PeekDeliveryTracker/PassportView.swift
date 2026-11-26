import SwiftUI

struct PassportView: View {
    @EnvironmentObject private var news: PassportNews
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dynamicTypeSize) private var dynamicTypeSize
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @Environment(\.horizontalSizeClass) private var horizontalSize
    @Environment(\.verticalSizeClass) private var verticalSize
    @ScaledMetric(relativeTo: .largeTitle) private var totalSize = 54
    @State private var showingAccount = false
    @State private var expandedCard: PassportSelection?
    @State private var touchCount = 0
    @State private var showAllStamps = false
    /// What was new when the passport opened stays new, and is pressed in, until the reader leaves it.
    @State private var fresh: Set<String> = []
    /// When each fresh seal's press began, and its place among the seals pressed together.
    @State private var presses: [String: (start: Date, index: Int)] = [:]
    @State private var pressing = false
    @State private var impacts = 0
    @State private var visible = false
    /// For the tests' pictures: the page alone, as it stands, on the day `now` says.
    private var picture = false
    private var now: Date?

    init() {}

    /// The page alone, for a picture: `fresh` are the stamps it shows as new.
    init(pictureAt now: Date, fresh: Set<String>) {
        picture = true
        self.now = now
        _fresh = State(initialValue: fresh)
    }

    private var copy: PassportCopy { PassportCopy(localizer: localizer) }
    private var stampCopy: PassportStampCopy { PassportStampCopy(localizer: localizer) }

    var body: some View {
        if picture {
            page {
                passport(news.book.stats)
                stamps(news.book)
                deliveryTimes(news.book.stats)
                YearCardRow(book: news.book, now: now)
                if !news.book.stats.originCountries.isEmpty { countries(news.book.stats) }
            }
            .frame(width: 402)
            .background(Brand.background)
        } else {
            passportPages
        }
    }

    private var passportPages: some View {
        let stats = news.book.stats
        let book = news.book
        return NavigationStack {
            GeometryReader { proxy in
                ScrollView {
                    // An iPhone Duo standing half open shows two pages, one each side of the fold: the passport and its
                    // stamps, then the times and the countries.
                    if let fold = proxy.fold, fold.height > fold.width {
                        HStack(alignment: .top, spacing: 0) {
                            page {
                                passport(stats)
                                stamps(book)
                            }
                            .frame(width: fold.minX)
                            Color.clear.frame(width: fold.width, height: 0)
                            page {
                                deliveryTimes(stats)
                                YearCardRow(book: book, now: now)
                                if !stats.originCountries.isEmpty { countries(stats) }
                            }
                            .frame(width: proxy.size.width - fold.maxX)
                        }
                    } else if PassportSpread.fits(width: proxy.size.width, regularWidth: horizontalSize == .regular,
                                                  regularHeight: verticalSize == .regular,
                                                  largeText: dynamicTypeSize.isAccessibilitySize) {
                        // The same two pages on a wide screen, either side of a seam.
                        HStack(alignment: .top, spacing: 0) {
                            page {
                                passport(stats)
                                stamps(book)
                            }
                            .frame(maxWidth: PassportSpread.widest)
                            Divider().padding(.vertical, 18)
                            page {
                                deliveryTimes(stats)
                                YearCardRow(book: book, now: now)
                                if !stats.originCountries.isEmpty { countries(stats) }
                            }
                            .frame(maxWidth: PassportSpread.widest)
                        }
                        .frame(maxWidth: .infinity)
                    } else {
                        page {
                            passport(stats)
                            stamps(book)
                            deliveryTimes(stats)
                            YearCardRow(book: book, now: now)
                            if !stats.originCountries.isEmpty { countries(stats) }
                        }
                        .frame(maxWidth: PassportSpread.widest)
                        .frame(maxWidth: .infinity)
                    }
                }
                .scrollIndicators(.hidden)
            }
            .background(Brand.background)
            .safeAreaInset(edge: .top, spacing: 0) { DemoModeBar() }
            .navigationTitle(copy.passport)
            .navigationBarTitleDisplayMode(.inline)
            .toolbar { AccountToolbarItem { showingAccount = true } }
        }
        .sheet(isPresented: $showingAccount) { AccountView().formOnPad() }
        .sensoryFeedback(.impact(weight: .light, intensity: 0.65), trigger: touchCount)
        // One light impact as the first new seal lands.
        .sensoryFeedback(.impact(weight: .light), trigger: impacts)
        .onAppear { visible = true; takeFresh() }
        // A stamp earned while the passport is open is pressed in there and then.
        .onChange(of: news.unseen.map(\.seen)) { if visible { takeFresh() } }
        // What was new stays pressed in until the reader leaves the passport.
        .onDisappear { visible = false; fresh = []; presses = [:]; pressing = false }
    }

    /// Opening the passport: what is new is pressed in, then everything earned counts as seen, so the dot and the
    /// lines on the cards go.
    private func takeFresh() {
        let new = news.unseen.map(\.seen).filter { !fresh.contains($0) }
        guard !new.isEmpty else { return }
        fresh.formUnion(new)
        news.markSeen()
        guard !reduceMotion else { return }
        // The seals press in the passport's order, once the page has settled.
        let order = news.book.stamps.map(\.seenKey).filter(new.contains)
        let start = Date()
        for (index, key) in order.enumerated() { presses[key] = (start, index) }
        pressing = true
        let first = PassportMotion.pressDelay(0) + PassportMotion.impact
        let last = PassportMotion.pressDelay(order.count - 1) + PassportMotion.pressEnd
        Task { @MainActor in
            try? await Task.sleep(for: .milliseconds(Int(first)))
            impacts += 1
            try? await Task.sleep(for: .milliseconds(Int(last - first) + 40))
            if presses.values.allSatisfy({ Date().timeIntervalSince($0.start) * 1000 > last }) { pressing = false }
        }
    }

    private func page(@ViewBuilder _ content: () -> some View) -> some View {
        VStack(alignment: .leading, spacing: 28, content: content)
            .padding(.horizontal, 20)
            .padding(.top, 18)
            .padding(.bottom, 36)
    }

    private func passport(_ stats: PassportStatistics) -> some View {
        VStack(alignment: .leading, spacing: 0) {
            Button { toggle(.cover) } label: {
                HStack(spacing: 20) {
                    VStack(alignment: .leading, spacing: 9) {
                        Text(stats.deliveredCount, format: .number)
                            .font(.system(size: totalSize, weight: .semibold))
                            .tracking(-2)
                            .contentTransition(.numericText())
                        Text(copy.delivered(stats.deliveredCount))
                            .font(.subheadline)
                            .foregroundStyle(Brand.onAccent.opacity(0.7))
                    }
                    Spacer(minLength: 0)
                    if !dynamicTypeSize.isAccessibilitySize {
                        PassportSeal.cover
                            .rotationEffect(.degrees(-7))
                    }
                }
                .frame(maxWidth: .infinity, minHeight: 118, alignment: .leading)
                .contentShape(Rectangle())
            }
            .buttonStyle(PassportPressStyle())
            .readsAsItsLabel()
            .accessibilityLabel("\(stats.deliveredCount) \(copy.delivered(stats.deliveredCount))")
            .accessibilityValue(expansionValue(.cover, explanation: copy.deliveredExplanation))
            .accessibilityHint(copy.expansionHint(expanded: expandedCard == .cover))
            .popover(isPresented: presentation(.cover)) {
                explanationBubble(title: copy.delivered(stats.deliveredCount), explanation: copy.deliveredExplanation)
            }
        }
        .padding(.vertical, 26)
        .padding(.horizontal, 24)
        .foregroundStyle(Brand.onAccent)
        .background(Brand.accent, in: RoundedRectangle(cornerRadius: 20))
    }

    private func stamps(_ book: PassportBook) -> some View {
        // Earned stamps first, in the passport's order, then the next three to reach; the rest behind "All stamps".
        let earned = book.stamps.filter(\.earned)
        let ahead = book.stamps.filter { !$0.earned }
        let visible = showAllStamps ? earned + ahead : earned + ahead.prefix(3)
        return VStack(alignment: .leading, spacing: 16) {
            Text(copy.stamps).font(.subheadline.weight(.semibold))
            // Fewer stamps to a row as the text grows, so their names have room.
            LazyVGrid(columns: Array(repeating: GridItem(.flexible(), spacing: 8, alignment: .top), count: dynamicTypeSize.isAccessibilitySize ? 2 : dynamicTypeSize >= .xxLarge ? 3 : 4), alignment: .leading, spacing: 16) {
                ForEach(visible) { stamp in
                    stampCell(stamp)
                }
            }
            if ahead.count > 3 {
                Button { showAllStamps.toggle() } label: {
                    Text(localizer.text(showAllStamps ? "passport.showLess" : "passport.showAll"))
                        .font(.caption)
                        .foregroundStyle(Brand.secondaryText)
                        .frame(minHeight: 44, alignment: .leading)
                        .contentShape(Rectangle())
                }
                .buttonStyle(PassportPressStyle())
                .padding(.vertical, -8)
            }
        }
    }

    private func stampCell(_ stamp: PassportBook.StampProgress) -> some View {
        let id = PassportSelection.milestone(stamp.key)
        let title = stampCopy.title(stamp.key)
        let isFresh = stamp.earned && fresh.contains(stamp.seenKey)
        return Button { toggle(id) } label: {
            VStack(spacing: 8) {
                pressedSeal(stamp, fresh: isFresh)
                VStack(spacing: 2) {
                    Text(title)
                        .font(.caption2)
                        .foregroundStyle(stamp.earned ? Brand.ink : Brand.secondaryText)
                    if let under = stampCopy.under(stamp, now: now ?? Date()) {
                        Text(under)
                            .font(.caption2.monospacedDigit())
                            .foregroundStyle(Brand.secondaryText)
                    }
                }
                .fixedSize(horizontal: false, vertical: true)
                .multilineTextAlignment(.center)
            }
            .padding(.top, 2)
            .frame(maxWidth: .infinity, minHeight: 96, alignment: .top)
            .contentShape(Rectangle())
        }
        .buttonStyle(PassportPressStyle())
        .readsAsItsLabel()
        .accessibilityLabel(stampCopy.accessibilityLabel(stamp, fresh: isFresh))
        .accessibilityValue(expansionValue(id, explanation: stampCopy.explanation(stamp.key)))
        .accessibilityHint(copy.expansionHint(expanded: expandedCard == id))
        .popover(isPresented: presentation(id)) {
            explanationBubble(title: title, explanation: stampCopy.details(stamp))
        }
    }

    /// A seal, pressed in while it is new, with its New pill.
    @ViewBuilder private func pressedSeal(_ stamp: PassportBook.StampProgress, fresh: Bool) -> some View {
        if fresh, pressing, let press = presses[stamp.seenKey] {
            TimelineView(.animation) { context in
                let elapsed = context.date.timeIntervalSince(press.start) * 1000 - PassportMotion.pressDelay(press.index)
                seal(stamp, fresh: true, press: elapsed < PassportMotion.pressEnd ? elapsed : nil)
            }
        } else {
            seal(stamp, fresh: fresh, press: nil)
        }
    }

    private func seal(_ stamp: PassportBook.StampProgress, fresh: Bool, press: Double?) -> some View {
        let pill = PassportMotion.Press(elapsed: press).pill
        return PassportSeal(stamp: stamp.stamp, earned: stamp.earned, press: press)
            .overlay(alignment: .topTrailing) {
                if fresh {
                    Text(localizer.text("passport.new"))
                        .font(.system(size: 9, weight: .semibold))
                        .foregroundStyle(Brand.onAccent)
                        .padding(.horizontal, 6)
                        .frame(minHeight: 15)
                        .background(Brand.accent, in: Capsule())
                        .fixedSize()
                        .scaleEffect(0.6 + 0.4 * pill)
                        .opacity(pill)
                        .offset(x: 11, y: -8)
                        .accessibilityHidden(true)
                }
            }
    }

    private func deliveryTimes(_ stats: PassportStatistics) -> some View {
        VStack(alignment: .leading, spacing: 0) {
            Divider().padding(.bottom, 21)
            if let average = stats.averageDeliveryDuration, let fastest = stats.fastestDelivery {
                let layout = dynamicTypeSize.isAccessibilitySize
                    ? AnyLayout(VStackLayout(alignment: .leading, spacing: 18))
                    : AnyLayout(HStackLayout(alignment: .top, spacing: 18))
                layout {
                    timeCard(id: .average, title: copy.average, duration: average,
                             explanation: "\(copy.timedJourneys(stats.durationSampleCount)). \(copy.timingExplanation)")
                    timeCard(id: .personalBest, title: copy.personalBest, duration: fastest.duration,
                             explanation: fastestExplanation(stats))
                }
            } else {
                Text(copy.waitingForTimes).font(.footnote).foregroundStyle(Brand.secondaryText)
            }
            Divider().padding(.top, 21)
        }
    }

    private func timeCard(id: PassportSelection, title: String, duration: TimeInterval, explanation: String) -> some View {
        Button { toggle(id) } label: {
            VStack(alignment: .leading, spacing: 6) {
                Text(formattedDuration(duration))
                    .font(.title2.weight(.medium).monospacedDigit())
                    .minimumScaleFactor(0.75)
                    .lineLimit(dynamicTypeSize.isAccessibilitySize ? nil : 1)
                Text(title)
                    .font(.caption)
                    .foregroundStyle(Brand.secondaryText)
                    .fixedSize(horizontal: false, vertical: true)
            }
            .frame(maxWidth: .infinity, minHeight: 48, alignment: .leading)
            .contentShape(Rectangle())
            .foregroundStyle(Brand.ink)
        }
        .buttonStyle(PassportPressStyle())
        .readsAsItsLabel()
        .accessibilityLabel("\(title), \(formattedDuration(duration, full: true))")
        .accessibilityValue(expansionValue(id, explanation: explanation))
        .accessibilityHint(copy.expansionHint(expanded: expandedCard == id))
        .popover(isPresented: presentation(id)) {
            explanationBubble(title: title, explanation: explanation)
        }
    }

    private func countries(_ stats: PassportStatistics) -> some View {
        VStack(alignment: .leading, spacing: 12) {
            Button { toggle(.countries) } label: {
                Text(copy.firstSeenIn)
                    .font(.subheadline.weight(.semibold))
                    .foregroundStyle(Brand.ink)
                    .fixedSize(horizontal: false, vertical: true)
                    .frame(minHeight: 44, alignment: .leading)
                    .contentShape(Rectangle())
            }
            .buttonStyle(PassportPressStyle())
            .padding(.vertical, -10)
            .accessibilityValue(copy.expansionState(expanded: expandedCard == .countries))
            .accessibilityHint(copy.expansionHint(expanded: expandedCard == .countries))
            .popover(isPresented: presentation(.countries)) {
                explanationBubble(title: copy.firstSeenIn, explanation: copy.countryExplanation)
            }
            VStack(spacing: 0) {
                ForEach(Array(stats.originCountries.prefix(3).enumerated()), id: \.element.id) { index, country in
                    let name = TrackingLocation.countryName(country.code, language: localizer.language)
                    if index > 0 { Divider() }
                    HStack(spacing: 11) {
                        Text(country.flag).font(.subheadline).accessibilityHidden(true)
                        Text(name).font(.subheadline).fixedSize(horizontal: false, vertical: true)
                        Spacer(minLength: 8)
                        Text(country.count, format: .number)
                            .font(.caption.monospacedDigit())
                            .foregroundStyle(Brand.secondaryText)
                    }
                    .frame(minHeight: 44)
                    .padding(.vertical, 2)
                    .accessibilityElement(children: .ignore)
                    .accessibilityLabel("\(name), \(copy.parcels(country.count))")
                }
            }
        }
    }

    private func fastestExplanation(_ stats: PassportStatistics) -> String {
        guard let record = stats.fastestDelivery else { return copy.noTimingExplanation }
        let title = record.label.nonEmpty ?? localizer.text("common.parcel")
        return "\(title) · \(localizer.shortDate(record.deliveredAt))\n\n\(copy.timingExplanation)"
    }

    private func formattedDuration(_ duration: TimeInterval, full: Bool = false) -> String {
        if duration < 60 { return full ? copy.lessThanOneMinute : copy.underOneMinute }
        let formatter = DateComponentsFormatter()
        var calendar = Calendar(identifier: .gregorian)
        calendar.locale = localizer.language.locale
        formatter.calendar = calendar
        formatter.allowedUnits = duration >= 86400 ? [.day, .hour] : [.hour, .minute]
        formatter.maximumUnitCount = 2
        formatter.unitsStyle = full ? .full : .abbreviated
        formatter.zeroFormattingBehavior = .dropAll
        return formatter.string(from: duration) ?? "—"
    }

    private func presentation(_ id: PassportSelection) -> Binding<Bool> {
        Binding(get: { expandedCard == id }, set: { presented in
            if presented { expandedCard = id }
            else if expandedCard == id { expandedCard = nil }
        })
    }

    private func explanationBubble(title: String, explanation: String) -> some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title).font(.headline).accessibilityAddTraits(.isHeader)
            Text(explanation).font(.subheadline).foregroundStyle(Brand.secondaryText)
        }
        .fixedSize(horizontal: false, vertical: true)
        .frame(idealWidth: 260, maxWidth: 280, alignment: .leading)
        .padding(20)
        .foregroundStyle(Brand.ink)
        // One step up from the page's paper, as a sheet's rows are.
        .presentationBackground(Brand.raised)
        .presentationCompactAdaptation(.popover)
    }

    private func expansionValue(_ id: PassportSelection, explanation: String) -> String {
        let expanded = expandedCard == id
        let state = copy.expansionState(expanded: expanded)
        return expanded ? "\(state). \(explanation)" : state
    }

    private func toggle(_ id: PassportSelection) {
        touchCount += 1
        // Only a stamp's details count as opening a stamp: never the cover, the times or the countries.
        if case .milestone = id, expandedCard != id { DeliveryAnalytics.shared.action("stamp-open") }
        withAnimation(reduceMotion ? nil : .spring(response: 0.42, dampingFraction: 0.86)) {
            expandedCard = expandedCard == id ? nil : id
        }
    }
}

private enum PassportSelection: Hashable {
    case cover
    case average
    case personalBest
    case milestone(String)
    case countries
}

struct PassportPressStyle: ButtonStyle {
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .scaleEffect(configuration.isPressed && !reduceMotion ? 0.975 : 1)
            .opacity(configuration.isPressed ? 0.94 : 1)
            .animation(reduceMotion ? nil : .spring(response: 0.3, dampingFraction: 0.75), value: configuration.isPressed)
    }
}

@MainActor
private struct PassportCopy {
    let localizer: Localizer

    var passport: String { localizer.text("passport.title") }
    func delivered(_ count: Int) -> String { localizer.text("passport.delivered", ["count": count]) }
    var deliveryTimes: String { localizer.text("passport.deliveryTimes") }
    var average: String { localizer.text("passport.average") }
    var personalBest: String { localizer.text("passport.personalBest") }
    var firstSeenIn: String { localizer.text("passport.firstSeenIn") }
    var stamps: String { localizer.text("passport.stamps") }
    var underOneMinute: String { localizer.text("passport.underOneMinute") }
    var lessThanOneMinute: String { localizer.text("passport.lessThanOneMinute") }
    var waitingForTimes: String { localizer.text("passport.waitingForTimes") }
    var deliveredExplanation: String { localizer.text("passport.deliveredExplanation") }
    var timingExplanation: String { localizer.text("passport.timingExplanation") }
    var noTimingExplanation: String { localizer.text("passport.noTimingExplanation") }
    var countryExplanation: String { localizer.text("passport.countryExplanation") }

    func expansionState(expanded: Bool) -> String {
        localizer.text(expanded ? "passport.expansionState.expanded" : "passport.expansionState.collapsed")
    }

    func expansionHint(expanded: Bool) -> String {
        localizer.text(expanded ? "passport.expansionHint" : "passport.detailsHint")
    }

    func timedJourneys(_ count: Int) -> String {
        localizer.text(count == 1 ? "passport.timedJourneys.one" : "passport.timedJourneys.many", ["count": count])
    }

    func parcels(_ count: Int) -> String {
        localizer.text(count == 1 ? "passport.parcels.one" : "passport.parcels.many", ["count": count])
    }

}
