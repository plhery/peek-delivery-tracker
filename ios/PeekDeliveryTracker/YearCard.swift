import ImageIO
import SwiftUI
import UniformTypeIdentifiers

/// "Your <year> in parcels": one page of paper on the passport's yellow, light only, drawn as the web and Android draw
/// it. It carries aggregates alone: counts, country stamps and earned seals; never labels, shops, carriers, tracking
/// numbers, places below a country or a nickname.
enum YearCard {
    static let size = CGSize(width: 1080, height: 1350)
    static let address = "peektracker.com"

    /// The year a card is offered for: this one, in December only.
    static func year(_ now: Date, calendar: Calendar = .current) -> Int? {
        calendar.component(.month, from: now) == 12 ? calendar.component(.year, from: now) : nil
    }

    /// Where each country stamp goes, by how many there are (at most six).
    static func countryLayout(_ count: Int, top: CGFloat, bottom: CGFloat) -> [CGRect] {
        let rows: [Int] = [1: [1], 2: [2], 3: [3], 4: [2, 2], 5: [3, 2], 6: [3, 3]][count] ?? []
        let (w, h, gap, rowGap): (CGFloat, CGFloat, CGFloat, CGFloat) = count == 1 ? (420, 312, 0, 0)
            : count == 2 ? (340, 252, 80, 0) : count == 4 ? (300, 223, 60, 60) : (248, 184, 48, 40)
        let blockHeight = CGFloat(rows.count) * h + CGFloat(max(0, rows.count - 1)) * rowGap + (count == 1 ? 72 : 0)
        var y = top + (bottom - top - blockHeight) / 2
        var boxes: [CGRect] = []
        for inRow in rows {
            let rowWidth = CGFloat(inRow) * w + CGFloat(inRow - 1) * gap
            var x = size.width / 2 - rowWidth / 2
            for _ in 0..<inRow {
                boxes.append(CGRect(x: x, y: y, width: w, height: h))
                x += w + gap
            }
            y += h + rowGap
        }
        return boxes
    }

    /// A journey's length as the passport's times write it: "12h", "1d 10h", "45m".
    static func journeyDuration(_ duration: TimeInterval, language: AppLanguage) -> String {
        let totalMinutes = max(1, Int(duration / 60))
        let days = totalMinutes / 1440, hours = totalMinutes % 1440 / 60, minutes = totalMinutes % 60
        let locale = PassportDates.locale(language)
        func unit(_ value: Int, _ unit: Duration.UnitsFormatStyle.Unit, seconds: Int) -> String {
            Duration.seconds(value * seconds).formatted(.units(allowed: [unit], width: .narrow).locale(locale))
        }
        if days > 0 { return [unit(days, .days, seconds: 86400), hours > 0 ? unit(hours, .hours, seconds: 3600) : nil].compactMap { $0 }.joined(separator: " ") }
        if hours > 0 { return [unit(hours, .hours, seconds: 3600), minutes > 0 ? unit(minutes, .minutes, seconds: 60) : nil].compactMap { $0 }.joined(separator: " ") }
        return unit(totalMinutes, .minutes, seconds: 60)
    }

    /// The card at 1080×1350 pixels.
    @MainActor static func render(_ year: YearInParcels, words: YearCardWords) -> CGImage? {
        let renderer = ImageRenderer(content: YearCardDrawing(year: year, words: words))
        renderer.scale = 1
        renderer.proposedSize = ProposedViewSize(size)
        return renderer.cgImage
    }

    static func png(_ image: CGImage) -> Data? {
        let data = NSMutableData()
        guard let destination = CGImageDestinationCreateWithData(data, UTType.png.identifier as CFString, 1, nil) else { return nil }
        CGImageDestinationAddImage(destination, image, nil)
        return CGImageDestinationFinalize(destination) ? data as Data : nil
    }
}

/// The card's words, in the reader's language.
struct YearCardWords: Equatable {
    let inParcels: String
    /// "13 delivered · 5 countries", or "13 delivered" alone with no country.
    let lineOne: String
    /// "Fastest 18h · peektracker.com", or the address alone.
    let lineTwo: String
    /// The one country's name, under its stamp when it is the only one.
    let onlyCountry: String?
    /// Each country stamp's day, as its ink carries it: "12 AUG 2026".
    let inkDays: [String: String]
    /// What the picture shows, for VoiceOver.
    let description: String

    @MainActor init(year: YearInParcels, localizer: Localizer, timeZone: TimeZone = .current) {
        let delivered = localizer.text("passport.card.delivered", ["count": year.deliveredCount])
        let countries = year.countryCount > 0 ? localizer.text("passport.card.countries", ["count": year.countryCount]) : nil
        let fastest = year.fastest.map { localizer.text("passport.card.fastest", ["time": YearCard.journeyDuration($0, language: localizer.language)]) }
        let names = year.countries.map { TrackingLocation.countryName($0.code, language: localizer.language) }
        inParcels = localizer.text("passport.card.inParcels")
        lineOne = countries.map { "\(delivered) · \($0)" } ?? delivered
        lineTwo = fastest.map { "\($0) · \(YearCard.address)" } ?? YearCard.address
        onlyCountry = year.countries.count == 1 ? names.first : nil
        inkDays = Dictionary(uniqueKeysWithValues: year.countries.map {
            ($0.code, PassportDates.inkDay($0.firstAt, language: localizer.language, timeZone: timeZone))
        })
        let place = countries.map { "\(delivered), \($0): \(names.joined(separator: ", "))" } ?? delivered
        description = [localizer.text("passport.card.alt", ["year": year.year]), place, fastest].compactMap { $0 }.joined(separator: ". ") + "."
    }
}

/// The card's 1080×1350 drawing, light whatever the screen's theme.
struct YearCardDrawing: View {
    let year: YearInParcels
    let words: YearCardWords

    private static let ink = Color(hex: "#20251E")
    private static let soft = Color(hex: "#657060")
    private static let paper = Color(hex: "#FFF9E8")
    private static let rule = Color(hex: "#E6DCC0")

    var body: some View {
        ZStack(alignment: .topLeading) {
            Canvas { context, _ in draw(&context) }
            PeekMark(size: 88).offset(x: 120, y: 1121)
        }
        .frame(width: YearCard.size.width, height: YearCard.size.height)
        .environment(\.colorScheme, .light)
        .accessibilityElement()
        .accessibilityLabel(words.description)
        .accessibilityAddTraits(.isImage)
    }

    private func draw(_ context: inout GraphicsContext) {
        context.fill(Path(CGRect(origin: .zero, size: YearCard.size)), with: .color(Brand.accent))
        context.fill(Path(roundedRect: CGRect(x: 48, y: 48, width: 984, height: 1254), cornerRadius: 56, style: .circular), with: .color(Self.paper))
        text(&context, Text(String(year.year)).font(.system(size: 200, weight: .semibold)).tracking(-7).foregroundStyle(Self.ink), x: 116, baseline: 300)
        text(&context, Text(words.inParcels).font(.system(size: 44)).foregroundStyle(Self.soft), x: 120, baseline: 368)

        let seals = year.stamps.count > 7 ? Array(year.stamps.prefix(6)) : year.stamps
        let zoneBottom: CGFloat = seals.isEmpty ? 1010 : 900
        if !year.countries.isEmpty {
            let boxes = YearCard.countryLayout(year.countries.count, top: 430, bottom: zoneBottom)
            for (country, box) in zip(year.countries, boxes) { inkStamp(&context, country, in: box) }
            if year.countries.count == 1, let name = words.onlyCountry, let box = boxes.first {
                text(&context, Text(name).font(.system(size: 40, weight: .semibold)).foregroundStyle(Self.ink),
                     x: YearCard.size.width / 2, baseline: box.maxY + 64, centred: true)
            }
        } else {
            let w: CGFloat = 220, h: CGFloat = 270
            seal(&context, in: CGRect(x: YearCard.size.width / 2 - w / 2, y: (430 + zoneBottom) / 2 - h / 2, width: w, height: h),
                 glyph: "parcel", numeral: nil, ink: Color(hex: "#745300"), paper: .white, tilt: -7)
        }
        for (index, stamp) in seals.enumerated() {
            let tone = stamp.stamp.tone.lightHex
            seal(&context, in: CGRect(x: 120 + CGFloat(index) * 96, y: 940, width: 72, height: 90),
                 glyph: stamp.stamp.glyph, numeral: stamp.stamp.numeral, ink: Color(hex: tone.ink), paper: Color(hex: tone.paper), tilt: 0)
        }
        if year.stamps.count > 7 {
            text(&context, Text("+\(year.stamps.count - 6)").font(.system(size: 34, weight: .medium)).foregroundStyle(Self.soft), x: 120 + 6 * 96, baseline: 997)
        }

        context.fill(Path(CGRect(x: 120, y: 1069, width: 840, height: 2)), with: .color(Self.rule))
        text(&context, Text(words.lineOne).font(.system(size: 44, weight: .semibold)).foregroundStyle(Self.ink), x: 232, baseline: 1158, maxWidth: 728)
        text(&context, Text(words.lineTwo).font(.system(size: 34)).foregroundStyle(Self.soft), x: 232, baseline: 1208, maxWidth: 728)
    }

    /// One line set on its baseline, as a canvas sets it; a line wider than `maxWidth` is condensed to fit.
    private func text(_ context: inout GraphicsContext, _ text: Text, x: CGFloat, baseline: CGFloat, centred: Bool = false, maxWidth: CGFloat? = nil) {
        let resolved = context.resolve(text)
        let size = resolved.measure(in: CGSize(width: CGFloat.greatestFiniteMagnitude, height: .greatestFiniteMagnitude))
        let top = baseline - resolved.firstBaseline(in: size)
        let fit = maxWidth.map { min(1, $0 / max(size.width, 1)) } ?? 1
        var line = context
        line.translateBy(x: centred ? x - size.width * fit / 2 : x, y: top)
        line.scaleBy(x: fit, y: 1)
        line.draw(resolved, in: CGRect(origin: .zero, size: size))
    }

    /// A country's ink stamp: its continent's outline, an inner line, its number, its code and its first day.
    private func inkStamp(_ context: inout GraphicsContext, _ country: PassportBook.CountryStamp, in box: CGRect) {
        let data = PassportData.shared
        let ink = data.ink(country.code)
        guard let layout = data.layout[ink.continent], let outlineData = data.outlines[ink.continent] else { return }
        let color = Color(hex: ink.tone.lightHex.ink)
        let outline = PassportPath.path(outlineData)
        var stamp = context
        stamp.translateBy(x: box.midX, y: box.midY)
        stamp.rotate(by: .degrees(ink.tilt))
        stamp.scaleBy(x: box.width / 124, y: box.width / 124)
        stamp.translateBy(x: -62, y: -46)
        stamp.stroke(outline, with: .color(color), style: StrokeStyle(lineWidth: 1.7, lineJoin: .round))
        var inner = stamp
        inner.opacity = 0.55
        inner.translateBy(x: 62, y: 46)
        inner.scaleBy(x: layout.inner, y: layout.inner)
        inner.translateBy(x: -62, y: -46)
        inner.stroke(outline, with: .color(color), style: StrokeStyle(lineWidth: 0.8 / layout.inner, lineJoin: .round))
        let number = String(format: "%02d", country.number)
        text(&stamp, Text("Nº \(number)").font(.system(size: layout.small, weight: .semibold)).tracking(1.3).foregroundStyle(color),
             x: 62, baseline: layout.number, centred: true)
        text(&stamp, Text(country.code).font(.system(size: layout.codeSize, weight: .bold)).tracking(2).foregroundStyle(color),
             x: 63, baseline: layout.code, centred: true)
        text(&stamp, Text(words.inkDays[country.code] ?? "").font(.system(size: layout.small, weight: .semibold)).tracking(layout.dateSpacing).foregroundStyle(color),
             x: 62, baseline: layout.date, centred: true)
    }

    /// A paper seal with its perforated edge, frame, glyph or numeral, and postmark.
    private func seal(_ context: inout GraphicsContext, in box: CGRect, glyph: String?, numeral: String?, ink: Color, paper: Color, tilt: Double) {
        let unit = box.width / 54
        var seal = context
        seal.translateBy(x: box.midX, y: box.midY)
        seal.rotate(by: .degrees(tilt))
        seal.translateBy(x: -box.width / 2, y: -box.height / 2)
        seal.fill(Path(CGRect(origin: .zero, size: box.size)), with: .color(paper))
        // The perforations: small bites of the page along every edge.
        let bite = 1.05 * unit, step = 3.6 * unit
        var bites = Path()
        for x in stride(from: step / 2, to: box.width, by: step) {
            for y in [0, box.height] { bites.addEllipse(in: CGRect(x: x - bite, y: y - bite, width: 2 * bite, height: 2 * bite)) }
        }
        for y in stride(from: step / 2, to: box.height, by: step) {
            for x in [0, box.width] { bites.addEllipse(in: CGRect(x: x - bite, y: y - bite, width: 2 * bite, height: 2 * bite)) }
        }
        seal.fill(bites, with: .color(Self.paper))
        let inset = 6 * unit
        seal.stroke(Path(CGRect(x: inset + unit / 2, y: inset + unit / 2, width: box.width - 2 * inset - unit, height: box.height - 2 * inset - unit)),
                    with: .color(ink.opacity(0.4)), lineWidth: unit)
        if let numeral {
            let resolved = seal.resolve(Text(numeral).font(.system(size: 20 * unit, weight: .light)).foregroundStyle(ink))
            seal.draw(resolved, at: CGPoint(x: box.width / 2, y: box.height / 2), anchor: .center)
        } else {
            let size = 24 * unit
            var drawing = seal
            drawing.translateBy(x: box.width / 2 - size / 2, y: box.height / 2 - size / 2)
            drawing.scaleBy(x: unit, y: unit)
            drawing.stroke(PassportPath.path(PassportData.shared.glyphs[glyph ?? "parcel"] ?? ""), with: .color(ink),
                           style: StrokeStyle(lineWidth: 1.1, lineCap: .round, lineJoin: .round))
        }
        // The postmark, over the seal's lower corner.
        let ring = 27 * unit
        var postmark = seal
        postmark.opacity = 0.34
        postmark.translateBy(x: box.width + 11 * unit - ring, y: box.height + 3 * unit - ring)
        postmark.scaleBy(x: ring / 32, y: ring / 32)
        for radius in [15.0, 10.5] {
            postmark.stroke(Path(ellipseIn: CGRect(x: 16 - radius, y: 16 - radius, width: 2 * radius, height: 2 * radius)), with: .color(ink), lineWidth: 1.05)
        }
    }
}

/// The card as a PNG file to share or save.
struct YearCardFile: Transferable {
    let png: Data
    let name: String

    static var transferRepresentation: some TransferRepresentation {
        DataRepresentation(exportedContentType: .png) { $0.png }
            .suggestedFileName { $0.name }
    }
}

/// The passport's row for the year's card, in December once a parcel was delivered that year.
struct YearCardRow: View {
    let book: PassportBook
    var now: Date?
    @EnvironmentObject private var localizer: Localizer
    /// The day the passport opened decides; a page left open over midnight keeps its card until it opens again.
    @State private var opened = Date()
    @State private var showing = false

    var body: some View {
        if let number = YearCard.year(now ?? opened) {
            let year = book.year(number)
            if year.deliveredCount > 0 {
                let title = localizer.text("passport.card.button", ["year": number])
                Button { showing = true } label: {
                    HStack(spacing: 8) {
                        Image(systemName: "square.and.arrow.up")
                            .font(.system(size: 15, weight: .semibold))
                            .accessibilityHidden(true)
                        Text(title).fixedSize(horizontal: false, vertical: true)
                    }
                    .font(.footnote.weight(.semibold))
                    .foregroundStyle(Brand.ink)
                    .padding(.horizontal, 16)
                    .padding(.vertical, 12)
                    .frame(maxWidth: .infinity, minHeight: 48)
                    .background(Brand.cream, in: RoundedRectangle(cornerRadius: 14))
                    .contentShape(RoundedRectangle(cornerRadius: 14))
                }
                .buttonStyle(PassportPressStyle())
                .accessibilityLabel(title)
                .sheet(isPresented: $showing) { YearCardSheet(year: year) }
            }
        }
    }
}

/// The card's picture, with a way to share or save it.
private struct YearCardSheet: View {
    let year: YearInParcels
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dismiss) private var dismiss
    @State private var picture: (image: CGImage, file: YearCardFile)?

    var body: some View {
        let words = YearCardWords(year: year, localizer: localizer)
        ScrollView {
            VStack(spacing: 14) {
                Group {
                    if let picture {
                        Image(decorative: picture.image, scale: 1).resizable()
                    } else {
                        Brand.cream.overlay(ProgressView())
                    }
                }
                .aspectRatio(YearCard.size.width / YearCard.size.height, contentMode: .fit)
                .clipShape(RoundedRectangle(cornerRadius: 14))
                .frame(maxWidth: 420)
                .accessibilityElement()
                .accessibilityLabel(words.description)
                .accessibilityAddTraits(.isImage)
                HStack(spacing: 8) {
                    if let picture {
                        ShareLink(item: picture.file, preview: SharePreview(localizer.text("passport.card.alt", ["year": year.year]),
                                                                           image: Image(decorative: picture.image, scale: 1))) {
                            Text(localizer.text("passport.card.share")).frame(maxWidth: .infinity, minHeight: 36)
                        }
                        .buttonStyle(.borderedProminent)
                        .tint(Brand.accent)
                        .foregroundStyle(Brand.onAccent)
                    }
                    Button { dismiss() } label: {
                        Text(localizer.text("common.close")).frame(maxWidth: .infinity, minHeight: 36)
                    }
                    .buttonStyle(.bordered)
                    .tint(Brand.ink)
                }
                .font(.subheadline.weight(.semibold))
                .frame(maxWidth: 420)
            }
            .padding(16)
            .frame(maxWidth: .infinity)
        }
        .background(Brand.background)
        .task {
            guard picture == nil, let image = YearCard.render(year, words: words), let png = YearCard.png(image) else { return }
            picture = (image, YearCardFile(png: png, name: "peek-\(year.year).png"))
        }
    }
}
