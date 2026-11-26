import SwiftUI

/// The passport as the web, iPhone and Android draw and count it (`Passport.json`, from shared/passport.json): the
/// stamps in page order, their glyphs, each country's continent, ink and tilt, and the continents' outlines.
struct PassportData: Decodable {
    enum Tone: String, Decodable, CaseIterable {
        case green, lilac, blue, peach, ochre, rose
    }

    enum Continent: String, Decodable, CaseIterable {
        case europe, asia, northAmerica, southAmerica, africa, oceania
    }

    struct Stamp: Decodable, Equatable {
        let key: String
        let tone: Tone
        let total: Int
        var glyph: String?
        var numeral: String?
    }

    /// Where a country stamp's words sit in its 124×92 box.
    struct Layout: Decodable {
        let number: CGFloat
        let code: CGFloat
        let codeSize: CGFloat
        let date: CGFloat
        let small: CGFloat
        let inner: CGFloat
        let dateSpacing: CGFloat
    }

    struct CountryInk: Equatable {
        let continent: Continent
        let tone: Tone
        let tilt: Double
    }

    let stamps: [Stamp]
    let glyphs: [String: String]
    let outlines: [Continent: String]
    let layout: [Continent: Layout]
    private let countries: [String: CountryInk]

    private enum CodingKeys: String, CodingKey { case stamps, glyphs, outlines, layout, countries }

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        stamps = try container.decode([Stamp].self, forKey: .stamps)
        glyphs = try container.decode([String: String].self, forKey: .glyphs)
        let outlines = try container.decode([String: String].self, forKey: .outlines)
        self.outlines = Dictionary(uniqueKeysWithValues: outlines.compactMap { key, value in Continent(rawValue: key).map { ($0, value) } })
        let layout = try container.decode([String: Layout].self, forKey: .layout)
        self.layout = Dictionary(uniqueKeysWithValues: layout.compactMap { key, value in Continent(rawValue: key).map { ($0, value) } })
        // Each row is [continent, tone, tilt in degrees].
        let rows = try container.decode([String: [PassportCountryCell]].self, forKey: .countries)
        countries = rows.compactMap { code, row -> (String, CountryInk)? in
            guard row.count == 3, case .text(let continent) = row[0], case .text(let tone) = row[1], case .number(let tilt) = row[2],
                  let continent = Continent(rawValue: continent), let tone = Tone(rawValue: tone) else { return nil }
            return (code, CountryInk(continent: continent, tone: tone, tilt: tilt))
        }.reduce(into: [:]) { $0[$1.0] = $1.1 }
    }

    /// A country's continent, which gives its stamp's outline, and its ink and tilt: fixed per code, so a stamp never
    /// changes colour or jumps. A code the table lacks takes Europe's outline, as on the web.
    func ink(_ code: String) -> CountryInk {
        countries[code] ?? CountryInk(continent: .europe, tone: .blue, tilt: -3)
    }

    func stamp(_ key: String) -> Stamp? { stamps.first { $0.key == key } }

    static let shared: PassportData = {
        guard let url = Bundle.main.url(forResource: "Passport", withExtension: "json"),
              let data = try? Data(contentsOf: url) else { fatalError("Passport.json is missing from the app.") }
        do { return try JSONDecoder().decode(PassportData.self, from: data) } catch { fatalError("Passport.json: \(error)") }
    }()
}

private enum PassportCountryCell: Decodable {
    case text(String)
    case number(Double)

    init(from decoder: Decoder) throws {
        let value = try decoder.singleValueContainer()
        if let number = try? value.decode(Double.self) { self = .number(number) } else { self = .text(try value.decode(String.self)) }
    }
}

extension PassportData.Tone {
    /// The tone's ink: deep on light paper, pale on dark.
    var ink: Color {
        switch self {
        case .green: ExperimentalPalette.delivered
        case .lilac: ExperimentalPalette.lilac
        case .blue: ExperimentalPalette.transit
        case .peach: ExperimentalPalette.pickup
        case .ochre: ExperimentalPalette.ochre
        case .rose: ExperimentalPalette.rose
        }
    }

    /// The tone's paper, a seal's ground.
    var paper: Color {
        switch self {
        case .green: ExperimentalPalette.deliveredSurface
        case .lilac: ExperimentalPalette.lilacSurface
        case .blue: ExperimentalPalette.transitSurface
        case .peach: ExperimentalPalette.pickupSurface
        case .ochre: ExperimentalPalette.ochreSurface
        case .rose: ExperimentalPalette.roseSurface
        }
    }

    /// The light theme's ink and paper, which the year's card keeps on every screen.
    var lightHex: (ink: String, paper: String) {
        switch self {
        case .green: ("#24613E", "#DCF0D7")
        case .blue: ("#22588A", "#DAEAFE")
        case .peach: ("#963E19", "#FFDDC7")
        case .lilac: ("#654299", "#E9DEFF")
        case .ochre: ("#745300", "#FFEBAB")
        case .rose: ("#9A3D5C", "#FADDE7")
        }
    }
}

/// The passport's line drawings, written with absolute M, L, C and Z only, as a SwiftUI path in their own box.
enum PassportPath {
    private static var cache: [String: Path] = [:]
    private static let lock = NSLock()

    static func path(_ data: String) -> Path {
        lock.lock(); defer { lock.unlock() }
        if let path = cache[data] { return path }
        let path = parse(data)
        cache[data] = path
        return path
    }

    private static func parse(_ data: String) -> Path {
        var path = Path()
        var command: Character = "M"
        var numbers: [CGFloat] = []
        var token = ""
        func flushToken() {
            if let value = Double(token) { numbers.append(CGFloat(value)) }
            token = ""
        }
        func apply() {
            switch command {
            case "M":
                var index = 0
                while index + 1 < numbers.count {
                    let point = CGPoint(x: numbers[index], y: numbers[index + 1])
                    if index == 0 { path.move(to: point) } else { path.addLine(to: point) }
                    index += 2
                }
            case "L":
                var index = 0
                while index + 1 < numbers.count {
                    path.addLine(to: CGPoint(x: numbers[index], y: numbers[index + 1]))
                    index += 2
                }
            case "C":
                var index = 0
                while index + 5 < numbers.count {
                    path.addCurve(to: CGPoint(x: numbers[index + 4], y: numbers[index + 5]),
                                  control1: CGPoint(x: numbers[index], y: numbers[index + 1]),
                                  control2: CGPoint(x: numbers[index + 2], y: numbers[index + 3]))
                    index += 6
                }
            case "Z":
                path.closeSubpath()
            default:
                break
            }
            numbers = []
        }
        for character in data {
            if "MLCZ".contains(character) {
                flushToken()
                if command != "Z" { apply() }
                command = character
                if character == "Z" { apply() }
            } else if character == " " || character == "," {
                flushToken()
            } else if character == "-", !token.isEmpty {
                flushToken()
                token = "-"
            } else {
                token.append(character)
            }
        }
        flushToken()
        if !numbers.isEmpty { apply() }
        return path
    }
}
