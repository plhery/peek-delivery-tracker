import SwiftUI

struct PassportView: View {
    @EnvironmentObject private var news: PassportNews
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dynamicTypeSize) private var dynamicTypeSize
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @Environment(\.horizontalSizeClass) private var horizontalSize
    @Environment(\.verticalSizeClass) private var verticalSize
    @ScaledMetric(relativeTo: .largeTitle) private var totalSize = 54
    @ScaledMetric(relativeTo: .title2) private var numberSize = 24
    @ScaledMetric(relativeTo: .caption) private var countryNameSize = 12.5
    @State private var showingAccount = false
    @State private var expandedCard: PassportSelection?
    @State private var touchCount = 0
    @State private var showAllStamps = false
    /// What was new when the passport opened stays new, and is pressed in, until the reader leaves it.
    @State private var fresh: Set<String> = []
    /// When each fresh seal's press or country's landing began, and its place among those landing together.
    @State private var landings: [String: PassportLanding] = [:]
    @State private var moving = false
    @State private var impacts = 0
    @State private var visible = false
    /// The page chosen, and the pages shown since the passport opened: a page's news lands the first time it shows.
    @State private var page = PassportView.countries
    @State private var shownPages: Set<Int> = []
    /// Whether the book lies open on two pages, as the window last had it.
    @State private var open = false
    /// Each opening starts the pages afresh.
    @State private var opening = 0
    /// For the tests' pictures: the page alone, as it stands, on the day `now` says.
    private var picture = false
    private var now: Date?

    static let countries = 0, stamps = 1, numbers = 2

    init() {}

    /// The passport alone, for a picture, at `page`, on one page or lying `open`: `fresh` are what it shows as new.
    init(pictureAt now: Date, fresh: Set<String>, page: Int = PassportView.stamps, open: Bool = false) {
        picture = true
        self.now = now
        _fresh = State(initialValue: fresh)
        _page = State(initialValue: page)
        _open = State(initialValue: open)
        let layout = PassportPageLayout(open: open, width: 0)
        _shownPages = State(initialValue: layout.shown(at: layout.offset(for: page)))
    }

    private var copy: PassportCopy { PassportCopy(localizer: localizer) }
    private var stampCopy: PassportStampCopy { PassportStampCopy(localizer: localizer) }

    var body: some View {
        if picture {
            let width: CGFloat = open ? 1032 : 402
            pages(column: min(width - 40, open ? 880 : PassportSpread.widest), open: open)
                .padding(.top, 18)
                .padding(.bottom, 36)
                .frame(width: width)
                .background(Brand.background)
        } else {
            passport
        }
    }

    private var passport: some View {
        NavigationStack {
            GeometryReader { proxy in
                let wide = wide(proxy.size.width)
                ScrollView {
                    // An iPhone Duo standing half open: the cover and the labels on one side of the fold, the pages on
                    // the other.
                    if let fold = proxy.fold, fold.height > fold.width {
                        HStack(alignment: .top, spacing: 0) {
                            VStack(spacing: 28) {
                                cover(news.book.stats)
                                labels
                            }
                            .padding(.horizontal, 20)
                            .frame(width: fold.minX)
                            Color.clear.frame(width: fold.width, height: 0)
                            track(PassportPageLayout(open: false, width: max(0, proxy.size.width - fold.maxX - 2 * PassportPageLayout.margin)))
                                .frame(width: max(0, proxy.size.width - fold.maxX))
                        }
                        .padding(.top, 18)
                        .padding(.bottom, 36)
                    } else {
                        pages(column: max(0, min(proxy.size.width - 2 * PassportPageLayout.margin, wide ? 880 : PassportSpread.widest)), open: wide)
                            .frame(maxWidth: .infinity)
                            .padding(.top, 18)
                            .padding(.bottom, 36)
                    }
                }
                .scrollIndicators(.hidden)
                .onChange(of: wide && proxy.fold == nil, initial: true) { _, wide in
                    open = wide
                    showPages(after: 0)
                }
            }
            .background(Brand.background)
            .safeAreaInset(edge: .top, spacing: 0) { DemoModeBar() }
            .navigationTitle(copy.passport)
            .navigationBarTitleDisplayMode(.inline)
            .toolbar { AccountToolbarItem { showingAccount = true } }
        }
        .sheet(isPresented: $showingAccount) { AccountView().formOnPad() }
        .sensoryFeedback(.impact(weight: .light, intensity: 0.65), trigger: touchCount)
        // One light impact as the first new seal or country lands.
        .sensoryFeedback(.impact(weight: .light), trigger: impacts)
        .onAppear(perform: appear)
        // A stamp or a country earned while the passport is open lands there and then.
        .onChange(of: news.unseen.map(\.seen)) { if visible { takeFresh() } }
        // What was new stays new until the reader leaves the passport; the pages start afresh at the next opening.
        .onDisappear {
            visible = false
            fresh = []
            landings = [:]
            moving = false
            shownPages = []
        }
    }

    /// Wide and tall enough for the book to lie open on two pages: an iPad's regular width, a Mac's window.
    private func wide(_ width: CGFloat) -> Bool {
        #if os(macOS)
        PassportSpread.fits(width: width, regularWidth: true, regularHeight: true, largeText: dynamicTypeSize.isAccessibilitySize)
        #else
        PassportSpread.fits(width: width, regularWidth: horizontalSize == .regular, regularHeight: verticalSize == .regular,
                            largeText: dynamicTypeSize.isAccessibilitySize)
        #endif
    }

    /// The cover, the labels and the pages, in a column `column` wide, the track reaching into the margins.
    private func pages(column: CGFloat, open: Bool) -> some View {
        VStack(spacing: 0) {
            cover(news.book.stats)
                .frame(width: column)
            labels
                .frame(width: column)
                .padding(.top, 28)
            track(PassportPageLayout(open: open, width: column))
                .padding(.top, 12)
        }
    }

    private var labels: some View {
        let fresh = freshItems
        return PassportPageLabels(
            label: localizer.text("passport.pages.label"),
            titles: [localizer.text("passport.pages.countries"), copy.stamps, localizer.text("passport.pages.numbers")],
            dots: [fresh.countries && !shownPages.contains(Self.countries), fresh.stamps && !shownPages.contains(Self.stamps), false],
            page: page,
            onPage: choose
        )
    }

    private func track(_ layout: PassportPageLayout) -> some View {
        let book = news.book
        return PassportPageTrack(layout: layout, page: page, onPage: choose) { index in
            switch index {
            case Self.countries: countriesPage(book, wide: layout.open || layout.page >= 560)
            case Self.stamps: stampsPage(book)
            default: numbersPage(book)
            }
        }
        .id(opening)
    }

    /// Whether what is new is on the Countries page, on the Stamps page.
    private var freshItems: (countries: Bool, stamps: Bool) {
        (news.book.countries.contains { fresh.contains($0.seenKey) }, news.book.stamps.contains { $0.earned && fresh.contains($0.seenKey) })
    }

    // MARK: - Opening, turning and landing

    /// Opening the passport: it opens on the page with news, countries first, else Countries. What is new is pressed in
    /// as its page shows, and everything earned counts as seen at once, so the dot and the lines on the cards go.
    private func appear() {
        visible = true
        opening += 1
        page = Self.openingPage(news.unseen)
        shownPages = []
        showPages(after: 0)
        takeFresh()
    }

    /// The page the passport opens on: the one with news, countries first, else Countries.
    static func openingPage(_ unseen: [PassportNews.Item]) -> Int {
        unseen.contains { $0.country != nil } ? countries : unseen.contains { $0.stamp != nil } ? stamps : countries
    }

    private func takeFresh() {
        let new = news.unseen.map(\.seen).filter { !fresh.contains($0) }
        guard !new.isEmpty else { return }
        fresh.formUnion(new)
        news.markSeen()
        // What lands on a page in view lands now; the rest waits for its page.
        land(pages: shownPages, after: 0)
    }

    private func choose(_ index: Int) {
        guard index != page else { return }
        page = index
        showPages(after: PassportMotion.flipDuration)
    }

    /// Notes the pages now in view; the news of those shown for the first time lands once the turn has ended.
    private func showPages(after delay: Double) {
        guard visible || picture else { return }
        let layout = PassportPageLayout(open: open, width: 0)
        let new = layout.shown(at: layout.offset(for: page)).subtracting(shownPages)
        guard !new.isEmpty else { return }
        shownPages.formUnion(new)
        land(pages: new, after: delay)
    }

    /// Presses the fresh seals and drops the fresh countries of `pages`, once each, starting `delay` ms from now, with
    /// one light impact as the first lands.
    private func land(pages: Set<Int>, after delay: Double) {
        guard !reduceMotion, !picture, visible else { return }
        let start = Date().addingTimeInterval(delay / 1000)
        var impact = Double.infinity
        var end = 0.0
        if pages.contains(Self.stamps) {
            let keys = news.book.stamps.map(\.seenKey).filter { fresh.contains($0) && landings[$0] == nil }
            for (index, key) in keys.enumerated() {
                landings[key] = PassportLanding(start: start, delay: PassportMotion.pressDelay(index), index: index, end: PassportMotion.pressEnd)
            }
            if !keys.isEmpty {
                impact = min(impact, PassportMotion.pressDelay(0) + PassportMotion.impact)
                end = max(end, PassportMotion.pressDelay(keys.count - 1) + PassportMotion.pressEnd)
            }
        }
        if pages.contains(Self.countries) {
            let keys = news.book.countries.map(\.seenKey).filter { fresh.contains($0) && landings[$0] == nil }
            for (index, key) in keys.enumerated() {
                landings[key] = PassportLanding(start: start, delay: PassportMotion.dropDelay(index), index: index, end: PassportMotion.dropEnd)
            }
            if !keys.isEmpty {
                impact = min(impact, PassportMotion.dropDelay(0) + PassportMotion.dropImpact)
                end = max(end, PassportMotion.dropDelay(keys.count - 1) + PassportMotion.dropEnd)
            }
        }
        guard impact.isFinite else { return }
        moving = true
        Task { @MainActor in
            try? await Task.sleep(for: .milliseconds(Int(delay + impact)))
            impacts += 1
            try? await Task.sleep(for: .milliseconds(Int(end - impact) + 40))
            if landings.values.allSatisfy({ $0.ended(at: Date()) }) { moving = false }
        }
    }

    // MARK: - The cover

    private func cover(_ stats: PassportStatistics) -> some View {
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

    // MARK: - Countries

    private func countriesPage(_ book: PassportBook, wide: Bool) -> some View {
        Group {
            if book.countries.isEmpty {
                VStack(spacing: 18) {
                    RoundedRectangle(cornerRadius: 12)
                        .strokeBorder(Brand.separator, style: StrokeStyle(lineWidth: 1.5, dash: [5, 4]))
                        .frame(width: 124, height: 92)
                        .rotationEffect(.degrees(-4))
                        .accessibilityHidden(true)
                    Text(localizer.text("passport.countriesEmpty"))
                        .font(.subheadline)
                        .foregroundStyle(Brand.secondaryText)
                        .multilineTextAlignment(.center)
                        .fixedSize(horizontal: false, vertical: true)
                }
                .padding(.top, 22)
                .padding(.horizontal, 10)
                .padding(.bottom, 8)
                .frame(maxWidth: .infinity)
            } else {
                // Two to a row on a phone, three on a wider page; one at the largest text sizes on a phone.
                let columns = dynamicTypeSize.isAccessibilitySize ? (wide ? 2 : 1) : (wide ? 3 : 2)
                PassportGrid(items: book.countries, columns: columns, spacing: 12, rowSpacing: 22) { countryCell($0) }
            }
        }
    }

    private func countryCell(_ country: PassportBook.CountryStamp) -> some View {
        let id = PassportSelection.country(country.code)
        let isFresh = fresh.contains(country.seenKey)
        return Button { toggle(id) } label: {
            landingInk(country, fresh: isFresh)
                .contentShape(Rectangle())
        }
        .buttonStyle(PassportPressStyle())
        .readsAsItsLabel()
        .accessibilityLabel(stampCopy.accessibilityLabel(country, fresh: isFresh))
        .accessibilityValue(expansionValue(id, explanation: stampCopy.details(country)))
        .accessibilityHint(copy.expansionHint(expanded: expandedCard == id))
        .popover(isPresented: presentation(id)) {
            explanationBubble(title: stampCopy.countryName(country.code), explanation: stampCopy.details(country))
        }
    }

    /// A country's ink, dropping in while it is new and its page has just shown.
    @ViewBuilder private func landingInk(_ country: PassportBook.CountryStamp, fresh: Bool) -> some View {
        if fresh, moving, let landing = landings[country.seenKey] {
            TimelineView(.animation) { context in
                let elapsed = landing.elapsed(at: context.date)
                countryArt(country, fresh: true, drop: PassportMotion.Drop(elapsed: elapsed < landing.end ? elapsed : nil, drops: landing.index < 2))
            }
        } else {
            countryArt(country, fresh: fresh, drop: PassportMotion.Drop(elapsed: nil))
        }
    }

    private func countryArt(_ country: PassportBook.CountryStamp, fresh: Bool, drop: PassportMotion.Drop) -> some View {
        VStack(spacing: 3) {
            CountryInk(code: country.code, number: country.number,
                       day: PassportDates.inkDay(country.firstAt, language: localizer.language), outline: drop.outline, turn: drop.turn)
                .scaleEffect(drop.scale)
                .offset(y: drop.lift)
                .opacity(drop.opacity)
                .padding(.bottom, 6)
            VStack(spacing: 3) {
                Text("\(TrackingLocation.flag(country.code)) \(stampCopy.countryName(country.code))")
                    .font(.system(size: countryNameSize))
                    .foregroundStyle(Brand.ink)
                Text(stampCopy.parcels(country.count))
                    .font(.caption2)
                    .foregroundStyle(Brand.secondaryText)
            }
            .multilineTextAlignment(.center)
            .fixedSize(horizontal: false, vertical: true)
            .offset(y: 8 * (1 - drop.caption))
            .opacity(drop.caption)
        }
        .padding(.vertical, 4)
        .frame(maxWidth: .infinity)
        .overlay(alignment: .topTrailing) {
            if fresh { newPill(drop.pill).offset(x: -6, y: -6) }
        }
    }

    // MARK: - Stamps

    private func stampsPage(_ book: PassportBook) -> some View {
        // Earned stamps first, in the passport's order, then the next three to reach; the rest behind "All stamps".
        let earned = book.stamps.filter(\.earned)
        let ahead = book.stamps.filter { !$0.earned }
        let visible = showAllStamps ? earned + ahead : earned + ahead.prefix(3)
        return VStack(alignment: .leading, spacing: 16) {
            // Fewer stamps to a row as the text grows, so their names have room.
            PassportGrid(items: visible, columns: dynamicTypeSize.isAccessibilitySize ? 2 : dynamicTypeSize >= .xxLarge ? 3 : 4,
                         spacing: 8, rowSpacing: 16) { stampCell($0) }
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

    /// A seal, pressed in while it is new and its page has just shown, with its New pill.
    @ViewBuilder private func pressedSeal(_ stamp: PassportBook.StampProgress, fresh: Bool) -> some View {
        if fresh, moving, let landing = landings[stamp.seenKey] {
            TimelineView(.animation) { context in
                let elapsed = landing.elapsed(at: context.date)
                seal(stamp, fresh: true, press: elapsed < landing.end ? elapsed : nil)
            }
        } else {
            seal(stamp, fresh: fresh, press: nil)
        }
    }

    private func seal(_ stamp: PassportBook.StampProgress, fresh: Bool, press: Double?) -> some View {
        PassportSeal(stamp: stamp.stamp, earned: stamp.earned, press: press)
            .overlay(alignment: .topTrailing) {
                if fresh { newPill(PassportMotion.Press(elapsed: press).pill).offset(x: 11, y: -8) }
            }
    }

    /// The New pill, popping in as `shown` goes to 1.
    private func newPill(_ shown: Double) -> some View {
        Text(localizer.text("passport.new"))
            .font(.system(size: 9, weight: .semibold))
            .foregroundStyle(Brand.onAccent)
            .padding(.horizontal, 6)
            .frame(minHeight: 15)
            .background(Brand.accent, in: Capsule())
            .fixedSize()
            .scaleEffect(0.6 + 0.4 * shown)
            .opacity(shown)
            .accessibilityHidden(true)
    }

    // MARK: - Numbers

    private func numbersPage(_ book: PassportBook) -> some View {
        let stats = book.stats
        let times = stats.averageDeliveryDuration.flatMap { average in stats.fastestDelivery.map { (average, $0) } }
        let delivered = count(stats.deliveredCount, copy.delivered(stats.deliveredCount))
        let countries = count(book.countries.count, localizer.text("passport.countriesLabel", ["count": book.countries.count]))
        return VStack(alignment: .leading, spacing: 0) {
            if dynamicTypeSize.isAccessibilitySize {
                VStack(alignment: .leading, spacing: 20) {
                    delivered
                    countries
                    if let (average, fastest) = times {
                        averageCard(stats, average)
                        fastestCard(stats, fastest)
                    }
                }
            } else {
                Grid(alignment: .topLeading, horizontalSpacing: 18, verticalSpacing: 20) {
                    GridRow {
                        delivered
                        countries
                    }
                    if let (average, fastest) = times {
                        GridRow {
                            averageCard(stats, average)
                            fastestCard(stats, fastest)
                        }
                    }
                }
            }
            if times == nil {
                Text(copy.waitingForTimes)
                    .font(.footnote)
                    .foregroundStyle(Brand.secondaryText)
                    .fixedSize(horizontal: false, vertical: true)
                    .padding(.top, 16)
            }
            let rows = [(localizer.text("passport.busiestMonth"), stampCopy.busiestMonth(book.numbers)),
                        (localizer.text("passport.carriersUsed"), stampCopy.carriers(book.numbers))]
                .compactMap { title, value in value.map { (title: title, value: $0) } }
            if !rows.isEmpty {
                VStack(spacing: 0) {
                    Divider()
                    ForEach(rows, id: \.title) { row in
                        ViewThatFits(in: .horizontal) {
                            HStack(spacing: 12) {
                                Text(row.title).foregroundStyle(Brand.ink)
                                Spacer(minLength: 8)
                                Text(row.value).foregroundStyle(Brand.secondaryText).monospacedDigit()
                            }
                            VStack(alignment: .leading, spacing: 2) {
                                Text(row.title).foregroundStyle(Brand.ink)
                                Text(row.value).foregroundStyle(Brand.secondaryText).monospacedDigit()
                            }
                            .padding(.vertical, 10)
                        }
                        .font(.footnote)
                        .fixedSize(horizontal: false, vertical: true)
                        .frame(maxWidth: .infinity, minHeight: 46, alignment: .leading)
                        .accessibilityElement(children: .combine)
                        Divider()
                    }
                }
                .padding(.top, 20)
            }
            YearCardRow(book: book, now: now)
                .padding(.top, 16)
        }
    }

    /// A count on the Numbers page: the number, then what it counts.
    private func count(_ value: Int, _ title: String) -> some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(value, format: .number)
                .font(.system(size: numberSize, weight: .medium).monospacedDigit())
                .tracking(-0.5)
            Text(title)
                .font(.caption)
                .foregroundStyle(Brand.secondaryText)
                .fixedSize(horizontal: false, vertical: true)
        }
        .foregroundStyle(Brand.ink)
        .frame(maxWidth: .infinity, alignment: .leading)
        .accessibilityElement(children: .ignore)
        .accessibilityLabel("\(value) \(title)")
    }

    private func averageCard(_ stats: PassportStatistics, _ average: TimeInterval) -> some View {
        timeCard(id: .average, title: copy.average, duration: average,
                 explanation: "\(copy.timedJourneys(stats.durationSampleCount)). \(copy.timingExplanation)")
    }

    private func fastestCard(_ stats: PassportStatistics, _ fastest: PassportStatistics.DeliveryRecord) -> some View {
        timeCard(id: .personalBest, title: copy.personalBest, duration: fastest.duration, explanation: fastestExplanation(stats))
    }

    private func timeCard(id: PassportSelection, title: String, duration: TimeInterval, explanation: String) -> some View {
        Button { toggle(id) } label: {
            VStack(alignment: .leading, spacing: 4) {
                Text(formattedDuration(duration))
                    .font(.system(size: numberSize, weight: .medium).monospacedDigit())
                    .tracking(-0.5)
                    .minimumScaleFactor(0.75)
                    .lineLimit(dynamicTypeSize.isAccessibilitySize ? nil : 1)
                Text(title)
                    .font(.caption)
                    .foregroundStyle(Brand.secondaryText)
                    .fixedSize(horizontal: false, vertical: true)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
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
    case country(String)
}

/// A fresh seal's press or a fresh country's landing: when its page showed, how long it waits after that, its place
/// among those landing together, and when it rests, in ms from its own start.
private struct PassportLanding {
    let start: Date
    let delay: Double
    let index: Int
    let end: Double

    /// Milliseconds since its own start, below 0 while it waits.
    func elapsed(at date: Date) -> Double { date.timeIntervalSince(start) * 1000 - delay }
    func ended(at date: Date) -> Bool { elapsed(at: date) > end }
}

/// Items in rows of `columns` equal cells, the last row's empty cells left blank.
struct PassportGrid<Item: Identifiable, Cell: View>: View {
    let items: [Item]
    let columns: Int
    let spacing: CGFloat
    let rowSpacing: CGFloat
    @ViewBuilder let cell: (Item) -> Cell

    var body: some View {
        let rows = stride(from: 0, to: items.count, by: max(1, columns)).map { Array(items[$0..<min($0 + max(1, columns), items.count)]) }
        Grid(alignment: .top, horizontalSpacing: spacing, verticalSpacing: rowSpacing) {
            ForEach(rows.indices, id: \.self) { row in
                GridRow(alignment: .top) {
                    ForEach(rows[row]) { item in cell(item).frame(maxWidth: .infinity) }
                    ForEach(0..<max(0, columns - rows[row].count), id: \.self) { _ in
                        Color.clear.frame(maxWidth: .infinity, maxHeight: 0)
                    }
                }
            }
        }
    }
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
    var average: String { localizer.text("passport.average") }
    var personalBest: String { localizer.text("passport.personalBest") }
    var stamps: String { localizer.text("passport.stamps") }
    var underOneMinute: String { localizer.text("passport.underOneMinute") }
    var lessThanOneMinute: String { localizer.text("passport.lessThanOneMinute") }
    var waitingForTimes: String { localizer.text("passport.waitingForTimes") }
    var deliveredExplanation: String { localizer.text("passport.deliveredExplanation") }
    var timingExplanation: String { localizer.text("passport.timingExplanation") }
    var noTimingExplanation: String { localizer.text("passport.noTimingExplanation") }

    func expansionState(expanded: Bool) -> String {
        localizer.text(expanded ? "passport.expansionState.expanded" : "passport.expansionState.collapsed")
    }

    func expansionHint(expanded: Bool) -> String {
        localizer.text(expanded ? "passport.expansionHint" : "passport.detailsHint")
    }

    func timedJourneys(_ count: Int) -> String {
        localizer.text(count == 1 ? "passport.timedJourneys.one" : "passport.timedJourneys.many", ["count": count])
    }

}
