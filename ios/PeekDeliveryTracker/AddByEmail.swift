import Foundation

/// The answer of `/api/account/inbox`, read leniently: an email whose result this app doesn't know yet, which a newer
/// server may send, drops out of the list instead of failing the whole page, and so does a confirmation it can't read.
struct AddByEmailOffer: Decodable {
    let value: AddByEmailResponse

    static func decode(_ data: Data) throws -> AddByEmailResponse {
        do { return try JSONDecoder.deliveryTracker.decode(Self.self, from: data).value }
        catch { throw DeliveryAPIError.invalidResponse }
    }

    private enum Keys: String, CodingKey { case inbox, dailyLimit }

    init(from decoder: any Decoder) throws {
        let container = try decoder.container(keyedBy: Keys.self)
        value = AddByEmailResponse(
            inbox: try container.decodeIfPresent(Inbox.self, forKey: .inbox)?.value,
            dailyLimit: try container.decode(Int.self, forKey: .dailyLimit)
        )
    }

    private struct Inbox: Decodable {
        let value: AddByEmail

        private enum Keys: String, CodingKey {
            case address, createdAt, usedToday, lastMailAt, lastResult, lastAdded, recent, confirmation
        }

        init(from decoder: any Decoder) throws {
            let container = try decoder.container(keyedBy: Keys.self)
            value = AddByEmail(
                address: try container.decode(String.self, forKey: .address),
                createdAt: try container.decode(String.self, forKey: .createdAt),
                usedToday: try container.decode(Int.self, forKey: .usedToday),
                lastMailAt: try container.decodeIfPresent(String.self, forKey: .lastMailAt),
                lastResult: try? container.decodeIfPresent(AddByEmailResult.self, forKey: .lastResult),
                lastAdded: try container.decodeIfPresent(Int.self, forKey: .lastAdded),
                recent: (try container.decodeIfPresent([Known<AddByEmailEntry>].self, forKey: .recent) ?? []).compactMap(\.value),
                confirmation: try? container.decodeIfPresent(ForwardingConfirmation.self, forKey: .confirmation)
            )
        }
    }

    /// A value of a list that may be one this app can't read: nil, where the list would otherwise fail.
    private struct Known<Value: Decodable>: Decodable {
        let value: Value?
        init(from decoder: any Decoder) throws { value = try? Value(from: decoder) }
    }
}

extension AddByEmail {
    /// How many of the last emails the page lists.
    static let emailsShown = 5

    /// The last emails, newest first. A mailbox from before they were kept shows its one last email.
    var recentEmails: [AddByEmailEntry] {
        if !recent.isEmpty { return Array(recent.prefix(Self.emailsShown)) }
        guard let lastMailAt, let lastResult else { return [] }
        return [AddByEmailEntry(at: lastMailAt, sender: "", result: lastResult, added: lastAdded ?? 0)]
    }
}

extension AddByEmailEntry {
    /// What the email did, in a few words.
    @MainActor func resultText(_ localizer: Localizer) -> String {
        result == .added
            ? localizer.text("inbox.result.added.many", ["count": added])
            : localizer.text("inbox.result.\(result.rawValue)")
    }
}

extension AddByEmailResponse {
    /// Whether the page says how many emails today took: once half the day's allowance is used.
    var showsUsage: Bool {
        guard let inbox else { return false }
        return inbox.usedToday * 2 >= dailyLimit
    }
}

/// Whether the server offers the account a forwarding address, and the changes made to it from Settings. The row shows
/// only once the server says it may: a 404, an error or no account at all leave it hidden.
@MainActor
final class AddByEmailModel: ObservableObject {
    /// How the model reaches the account's address: `ParcelStore.addByEmailClient()`.
    struct Client {
        /// The offer, or nil when the server answers 404: the feature isn't set up or open to the account.
        var read: @MainActor () async throws -> AddByEmailResponse?
        /// Makes the address, or a new one in its place.
        var open: @MainActor (_ renew: Bool) async throws -> AddByEmailResponse
        /// Turns it off.
        var close: @MainActor () async throws -> Void
    }

    /// Nil hides the row.
    @Published private(set) var offer: AddByEmailResponse?
    @Published private(set) var working = false
    /// Why the last change failed, until the next one.
    @Published private(set) var failure: (any Error)?

    /// How soon a return to Peek reads the offer again: coming back to the app and its window tell of the same return.
    static let rereadAfter: TimeInterval = 2

    private var client: Client?
    private var readAt: Date?
    /// Counts the changes made here: a read that began before one would bring back what it replaced.
    private var changes = 0

    init(client: Client? = nil) { self.client = client }

    /// The account to read, or none: the row goes with it.
    func configure(_ client: Client?) {
        self.client = client
        changes += 1
        if client == nil { offer = nil; failure = nil; readAt = nil }
    }

    /// Reads the offer. A failure leaves what is shown, which before any answer is no row.
    func load(now: Date = .now) async {
        guard let client else { offer = nil; return }
        readAt = now
        let change = changes
        do {
            let next = try await client.read()
            guard change == changes, !Task.isCancelled else { return }
            offer = next
        } catch {
            // The row stays as the last answer left it: a moment offline doesn't take it away.
        }
    }

    /// Back in Peek: Gmail's confirmation, sent while the reader was in Gmail, shows without opening Settings again.
    func reloadOnReturn(now: Date = .now) async {
        guard let readAt, now.timeIntervalSince(readAt) >= Self.rereadAfter else { return }
        await load(now: now)
    }

    /// Gives the account its address.
    @discardableResult func turnOn() async -> Bool {
        await change { try await $0.open(false) }
    }

    /// A new address in place of the current one, which stops working at once.
    @discardableResult func renew() async -> Bool {
        await change { try await $0.open(true) }
    }

    @discardableResult func turnOff() async -> Bool {
        await change { [offer] client in
            try await client.close()
            return offer.map { AddByEmailResponse(inbox: nil, dailyLimit: $0.dailyLimit) }
        }
    }

    private func change(_ operation: (Client) async throws -> AddByEmailResponse?) async -> Bool {
        guard let client, !working else { return false }
        working = true
        failure = nil
        changes += 1
        defer { working = false }
        do {
            if let next = try await operation(client) { offer = next }
            return true
        } catch is CancellationError {
            return false
        } catch {
            failure = error
            return false
        }
    }
}

/// The words a shipping email's subject uses, by the language it is written in, from `shared/email-filter.json`, which
/// the web and Android read too: Settings builds the mail filter of the forwarding steps from them, as the web does
/// (`src/lib/emailFilter.ts`).
struct EmailFilter: Sendable {
    /// The languages a filter can take, in the order they are offered: the file's.
    let languages: [String]
    let words: [String: [String]]
    /// The few words of each language that most shipping subjects hold, the most telling first, for a mail service
    /// whose rule takes its words one at a time.
    let mainWords: [String: [String]]

    static let bundled = Bundle.main.url(forResource: "EmailFilter", withExtension: "json")
        .flatMap { try? Data(contentsOf: $0) }
        .flatMap(EmailFilter.init(data:))
        ?? EmailFilter(languages: [], words: [:], mainWords: [:])

    init(languages: [String], words: [String: [String]], mainWords: [String: [String]]) {
        self.languages = languages
        self.words = words
        self.mainWords = mainWords
    }

    init?(data: Data) {
        struct File: Decodable { let words: [String: [String]]; let mainWords: [String: [String]] }
        guard let file = try? JSONDecoder().decode(File.self, from: data) else { return nil }
        var ordered: [String] = []
        for language in Self.keys(ofObject: "words", in: data) where file.words[language] != nil && !ordered.contains(language) {
            ordered.append(language)
        }
        // A key the reading missed still comes, after the others.
        ordered += file.words.keys.filter { !ordered.contains($0) }.sorted()
        self.init(languages: ordered, words: file.words, mainWords: file.mainWords)
    }

    /// The languages a reader's filter starts with: the one Peek shows, those the device prefers, and English, which
    /// shops and carriers abroad write in. A language tag counts by its language alone, `pt-BR` as `pt`.
    func languages(for tags: [String]) -> [String] {
        var chosen = Set<String>()
        for tag in tags + ["en"] {
            let language = tag.lowercased().split(separator: "-", omittingEmptySubsequences: false).first.map(String.init) ?? ""
            if words[language] != nil { chosen.insert(language) }
        }
        return languages.filter(chosen.contains)
    }

    /// The search Gmail's filter takes for emails written in these languages: one of their words in the subject. A
    /// word two languages share is named once. Empty when no language is chosen.
    func gmailFilter(_ chosen: [String]) -> String {
        var seen = Set<String>()
        var terms: [String] = []
        for language in languages where chosen.contains(language) {
            for word in words[language] ?? [] where seen.insert(word.lowercased()).inserted {
                terms.append(word.contains(" ") ? "\"\(word)\"" : word)
            }
        }
        return terms.isEmpty ? "" : "subject:(\(terms.joined(separator: " OR ")))"
    }

    /// The main words of these languages, for a rule that asks "subject includes". Each language's first word comes
    /// before any second one, so that a reader who stops early has covered every language.
    func subjectWords(_ chosen: [String]) -> [String] {
        let lists = languages.filter(chosen.contains).map { mainWords[$0] ?? [] }
        var seen = Set<String>()
        var result: [String] = []
        var rank = 0
        while lists.contains(where: { rank < $0.count }) {
            for list in lists where rank < list.count {
                let word = list[rank]
                if !word.isEmpty, seen.insert(word.lowercased()).inserted { result.append(word) }
            }
            rank += 1
        }
        return result
    }

    /// The keys of the object `name` at the top of a JSON document, in the order written, which a dictionary doesn't
    /// keep. Only an object's keys are followed by a colon.
    static func keys(ofObject name: String, in data: Data) -> [String] {
        var keys: [String] = []
        var depth = 0
        var inString = false, escaped = false
        var string: [UInt8] = []
        var last: String?, top: String?
        for byte in data {
            if inString {
                if escaped { escaped = false }
                else if byte == UInt8(ascii: "\\") { escaped = true }
                else if byte == UInt8(ascii: "\"") { inString = false; last = String(decoding: string, as: UTF8.self); continue }
                string.append(byte)
                continue
            }
            switch byte {
            case UInt8(ascii: "\""): inString = true; string = []
            case UInt8(ascii: "{"), UInt8(ascii: "["): depth += 1
            case UInt8(ascii: "}"), UInt8(ascii: "]"): depth -= 1
            case UInt8(ascii: ":"):
                if depth == 1 { top = last } else if depth == 2, top == name, let last { keys.append(last) }
            default: break
            }
        }
        return keys
    }
}

/// The mail services Settings has forwarding steps for. `other` is any service whose rules can forward.
enum MailService: String, CaseIterable, Identifiable {
    case gmail, outlook, icloud, yahoo, other

    var id: String { rawValue }

    /// The service's own name, which no language translates; nil for `other`.
    var name: String? {
        switch self {
        case .gmail: "Gmail"
        case .outlook: "Outlook"
        case .icloud: "iCloud"
        case .yahoo: "Yahoo"
        case .other: nil
        }
    }

    /// The services' domains by their name, whatever the country ending: "hotmail.fr", "yahoo.co.uk".
    private static let domainNames: [String: MailService] = [
        "gmail": .gmail, "googlemail": .gmail,
        "outlook": .outlook, "hotmail": .outlook, "live": .outlook, "msn": .outlook,
        "icloud": .icloud,
        "yahoo": .yahoo, "ymail": .yahoo, "rocketmail": .yahoo,
    ]
    private static let iCloudDomains: Set<String> = ["me.com", "mac.com"]

    /// The service an address is most likely read in, by its domain. An address of another domain starts on Gmail,
    /// which carries most of them.
    static func of(_ email: String?) -> MailService {
        let parts = (email ?? "").trimmingCharacters(in: .whitespacesAndNewlines).lowercased()
            .split(separator: "@", omittingEmptySubsequences: false)
        let domain = parts.count > 1 ? String(parts[1]) : ""
        if iCloudDomains.contains(domain) { return .icloud }
        let name = domain.split(separator: ".", omittingEmptySubsequences: false).first.map(String.init) ?? ""
        return domainNames[name] ?? .gmail
    }

    /// Gmail's settings (Settings › See all settings), from which the reader opens Forwarding and POP/IMAP. A link
    /// straight to that tab, or a reload of it, can show the tab without its "Add a forwarding address" button.
    static let gmailSettings = URL(string: "https://mail.google.com/mail/#settings/general")!
    /// Where Outlook.com lists its rules, and iCloud's mail on the web.
    static let outlookRules = URL(string: "https://outlook.live.com/mail/0/options/mail/rules")!
    static let iCloudMail = URL(string: "https://www.icloud.com/mail")!
}
