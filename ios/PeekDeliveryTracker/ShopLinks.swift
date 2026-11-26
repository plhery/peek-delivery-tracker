import Foundation

/// A shop's tracking-page link, as the scraper's `shopTrackingLinks` tells one (parcelLab's `<shop>.delivery-status.com`,
/// as H&M's "Follow package" opens, and the older `prtl.parcellab.com/trackings/…`). The link opens a page with the
/// reader's name and address: Add sends it to Peek only, to read or wait for it, and keeps it nowhere.
enum ShopLink {
    /// The most links one text is looked through for, as the scraper's: an email prints a few dozen.
    static let mostLinks = 200
    /// The most links Peek reads in one text (the server's `MOST_SHOP_LINKS`): only these are sent.
    static let mostSent = 3

    private static let candidate = try! NSRegularExpression(pattern: #"https?://[^\s<>"']+"#, options: [.caseInsensitive])
    private static let trailing = try! NSRegularExpression(pattern: #"[>'")\],;.!?]+$"#)

    /// The link `raw` is, without the punctuation a sentence puts after it, when it is a shop's tracking page.
    static func link(_ raw: String) -> String? {
        let trimmed = raw.trimmingCharacters(in: .whitespacesAndNewlines)
        let value = trailing.stringByReplacingMatches(in: trimmed, range: NSRange(trimmed.startIndex..., in: trimmed), withTemplate: "")
        // A browser reads a backslash as a slash, where this parser would take what follows for the address.
        guard !value.contains("\\"), let url = URL(string: value),
              let scheme = url.scheme?.lowercased(), scheme == "https" || scheme == "http",
              let host = url.host(percentEncoded: false)?.lowercased() else { return nil }
        let shopPage = host == "delivery-status.com" || host.hasSuffix(".delivery-status.com")
        let portal = host == "prtl.parcellab.com" && url.path(percentEncoded: true).lowercased().hasPrefix("/trackings/")
        return shopPage || portal ? value : nil
    }

    /// The shop tracking-page links a text holds, each once, in the order written, with HTML's `&amp;` read as `&`.
    static func links(in text: String) -> [String] {
        var found: [String] = []
        for match in candidate.matches(in: text, range: NSRange(text.startIndex..., in: text)).prefix(mostLinks) {
            guard let range = Range(match.range, in: text) else { continue }
            let raw = text[range].replacingOccurrences(of: "&amp;", with: "&", options: .caseInsensitive)
            if let link = link(raw), !found.contains(link) { found.append(link) }
        }
        return found
    }

    /// What Add sends to read the text's shop links or wait for them: the links alone, one to a line, and not the rest
    /// of a shared email. Nil when the text holds none.
    static func request(in text: String) -> String? {
        let links = links(in: text).prefix(mostSent)
        return links.isEmpty ? nil : links.joined(separator: "\n")
    }
}

/// What Peek answers about a text's shop links (`POST /api/shop-links`), read leniently: only what Add shows.
struct ShopLinkAnswer: Equatable, Decodable {
    /// A parcel a link names.
    struct Parcel: Equatable, Decodable {
        let trackingNumber: String
        /// A catalog id, `unknown` when the platform named none Peek knows.
        let carrier: String
    }

    /// A link that named no parcel: the shop hasn't shipped, or its page can't be read.
    struct Unresolved: Equatable, Decodable {
        var platformName: String?
        var shopName: String?
        /// Whether Peek can wait for the link's parcels: every reason but a page it can't read.
        var waitable: Bool
    }

    var parcels: [Parcel]
    var unresolved: [Unresolved]

    static func decode(_ data: Data) throws -> ShopLinkAnswer {
        do { return try JSONDecoder.deliveryTracker.decode(Self.self, from: data) }
        catch { throw DeliveryAPIError.invalidResponse }
    }
}

/// What Add does with an answer: fills the number in, says why there is none, or says the page isn't answering.
enum ShopLinkOutcome: Equatable {
    struct Found: Equatable {
        let trackingNumber: String
        /// The carrier, when the platform named one the catalog knows.
        let carrier: CarrierID?
    }

    case found(Found)
    case unresolved(ShopLinkAnswer.Unresolved)
    case failed

    /// The first parcel the links name, with its carrier when it is a catalog's other than `unknown`; else the first
    /// link that named none. An answer with neither is a page that didn't answer.
    init(_ answer: ShopLinkAnswer, knows: (CarrierID) -> Bool = { CarrierCatalog.shared.definitions[$0] != nil }) {
        if let parcel = answer.parcels.first {
            let carrier = CarrierID(rawValue: parcel.carrier)
            self = .found(Found(trackingNumber: parcel.trackingNumber, carrier: carrier != .unknown && knows(carrier) ? carrier : nil))
        } else if let link = answer.unresolved.first {
            self = .unresolved(link)
        } else {
            self = .failed
        }
    }
}

/// Where Add stands with the shop links in its field.
enum ShopLinkReading: Equatable {
    /// No shop link, or a number beside it.
    case none
    /// Asking the shop's platform, or waiting for typing to pause.
    case reading
    case unresolved(ShopLinkAnswer.Unresolved)
    /// The page didn't answer.
    case failed

    /// Peek can wait for the shop: signed in, for a link that named no parcel yet.
    func canWait(signedIn: Bool) -> Bool {
        guard signedIn, case .unresolved(let link) = self else { return false }
        return link.waitable
    }

    /// The shop the link names, for the wait's button.
    var shop: String? {
        guard case .unresolved(let link) = self else { return nil }
        return link.shopName?.nonEmpty
    }
}

/// What Add makes of the shop links in its field, as the web's `useShopLinkReading.ts`: a text that holds one and no
/// number is read once typing pauses, at once when it was pasted, shared or scanned. The answer is kept for the links it
/// was asked about, so that a read over other words doesn't ask again.
struct ShopLinkField: Equatable {
    /// How long typing rests before a typed link is read (the web's `TYPING_PAUSE_MS`).
    static let typingPause: Duration = .milliseconds(800)

    private struct Answered: Equatable {
        let links: String
        let reading: ShopLinkReading
    }

    /// The text as it arrived whole in the field: pasted, shared or scanned.
    private var whole: String?
    private var answered: Answered?
    /// The number a link named, with its carrier, which stays picked while the field holds that number.
    private(set) var filled: ShopLinkOutcome.Found?

    init(arriving whole: String? = nil) { self.whole = whole }

    /// The links Add reads: the text's shop links, when it holds one and no number of its own.
    static func question(in input: String, number: String) -> String? {
        number.isEmpty ? ShopLink.request(in: input) : nil
    }

    /// The text arrived whole: its links are read at once.
    mutating func arrived(_ input: String) { whole = input }

    /// Whether the read waits for typing to pause first.
    func waitsForTyping(_ input: String) -> Bool { whole != input }

    func reading(for question: String?) -> ShopLinkReading {
        guard let question else { return .none }
        guard let answered, answered.links == question else { return .reading }
        return answered.reading
    }

    /// Takes the answer about `question`. A parcel it names comes back, for the field to take its number.
    mutating func answer(_ outcome: ShopLinkOutcome, for question: String) -> ShopLinkOutcome.Found? {
        switch outcome {
        case .found(let found):
            answered = nil
            filled = found
            return found
        case .unresolved(let link):
            answered = Answered(links: question, reading: .unresolved(link))
        case .failed:
            answered = Answered(links: question, reading: .failed)
        }
        return nil
    }

    /// The carrier picked with a number a link named, while the field holds that number.
    func carrier(for input: String) -> CarrierID? {
        filled?.trackingNumber == input ? filled?.carrier : nil
    }
}

/// What Add says under its field while it reads a shop's link, or of one that named no parcel: neutral words, not an
/// error, as "not shipped yet" is none.
struct ShopLinkNoticeText: Equatable {
    /// "H&M hasn't named a parcel on this page yet", in bold.
    let title: String?
    let body: String

    /// What VoiceOver reads: the title, then the words.
    var spoken: String { [title, body].compactMap { $0 }.joined(separator: "\n") }

    init(title: String?, body: String) {
        self.title = title
        self.body = body
    }

    @MainActor init?(_ reading: ShopLinkReading, signedIn: Bool, localizer: Localizer) {
        switch reading {
        case .none:
            return nil
        case .reading:
            title = nil
            body = localizer.text("shopLink.checking")
        case .failed:
            title = nil
            body = localizer.text("shopLink.unavailable")
        case .unresolved(let link) where !link.waitable:
            title = nil
            body = localizer.text("shopLink.unreadable")
        case .unresolved(let link):
            title = link.shopName?.nonEmpty.map { localizer.text("shopLink.noParcelYet", ["shop": $0]) }
                ?? localizer.text("shopLink.noParcelYet.unnamed")
            body = localizer.text(signedIn ? "shopLink.waitHint" : "shopLink.signIn")
        }
    }

    /// The button that waits for the shop: "Wait for H&M".
    @MainActor static func waitAction(_ reading: ShopLinkReading, localizer: Localizer) -> String {
        reading.shop.map { localizer.text("shopLink.wait", ["shop": $0]) } ?? localizer.text("shopLink.wait.unnamed")
    }
}

#if DEBUG
/// Answers Add's shop links in the demo without asking anyone, to look at them in the simulator: start the demo
/// (`-sdt.native.experience.v1 demo`) with `-sdt.debug.shopLink waiting` (H&M hasn't shipped, and the demo waits as
/// an account does), `unnamed` (a shop the link doesn't name), `unreadable`, `unavailable` or `found` (a DHL parcel).
/// Paste any link to `<shop>.delivery-status.com`.
enum ShopLinkPreview {
    static var variant: String? { UserDefaults.standard.string(forKey: "sdt.debug.shopLink") }

    static func answer(_ variant: String) -> ShopLinkAnswer {
        let notYet = ShopLinkAnswer.Unresolved(platformName: "parcelLab", shopName: variant == "unnamed" ? nil : "H&M", waitable: true)
        switch variant {
        case "found": return ShopLinkAnswer(parcels: [.init(trackingNumber: "00340000000000000001", carrier: "dhl")], unresolved: [])
        case "unreadable": return ShopLinkAnswer(parcels: [], unresolved: [.init(platformName: "parcelLab", shopName: "H&M", waitable: false)])
        case "unavailable": return ShopLinkAnswer(parcels: [], unresolved: [])
        default: return ShopLinkAnswer(parcels: [], unresolved: [notYet])
        }
    }

    /// The demo's wait for the preview's shop, named `label`.
    static func wait(_ variant: String, label: String, now: Date = Date()) -> ShopWait {
        ShopWait(id: UUID(), platform: .parcellab, platformName: "parcelLab", shopName: answer(variant).unresolved.first?.shopName,
                 label: label, createdAt: DateParser.isoString(now), expiresAt: DateParser.isoString(now.addingTimeInterval(14 * 86_400)))
    }
}
#endif
