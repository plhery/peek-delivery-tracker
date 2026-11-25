import CryptoKit
import Foundation

/// What a parcel's page asks its reader. For a parcel a carrier answers for, Pip asks where the
/// history ends whether he got it right, and the page asks once more on the way back from the
/// carrier's own site. For a parcel no carrier was found for, a row asks whether the carrier's
/// site shows it.
enum ParcelFeedbackQuestion: Equatable, Sendable {
    case found
    case unknown

    /// Nil asks nothing: the parcel is archived, still waits for its first answer, or has a
    /// carrier whose history the page does not show.
    init?(parcel: Parcel, showsHistory: Bool) {
        guard !parcel.isArchived else { return nil }
        if !parcel.hasCarrierUpdate {
            // Asked about for the first time: there is nothing to judge yet.
            if parcel.syncStatus == .pending || parcel.syncStatus == .syncing { return nil }
            if parcel.carrier == .unknown { self = .unknown; return }
        }
        guard showsHistory else { return nil }
        self = .found
    }
}

extension Parcel {
    /// What the page ends on: its newest scan, and how many it has. Empty before the first one.
    var feedbackScan: String {
        currentEvent.map { "\(trackingEvents.count):\($0.occurredAt)" } ?? ""
    }
}

/// The next step for a reader who hasn't received the parcel (`missingStep` on the web): what the page says of it, sent
/// with the answer, the carrier the step names, and the help page of the carrier that has the parcel, else of the one
/// it is filed under (`CarrierCatalog.helpLink`).
struct ParcelFeedbackMissingStep: Equatable {
    /// A carrier's help page, as its link names it.
    struct Help: Equatable {
        var name: String
        var url: URL
    }

    var pageSaid: ParcelFeedbackPageSaid
    var carrier: String
    var help: Help?

    /// What the page says of the parcel: delivered by its carrier's scan; delivered or on its way by steps its carrier
    /// writes in advance (a delivery on a schedule, or a route written ahead); or not delivered yet. The step names the
    /// carrier that writes the steps, else the one whose help page it links, else the one the page names.
    static func of(_ parcel: Parcel, carrier: String, language: AppLanguage, catalog: CarrierCatalog = .shared) -> ParcelFeedbackMissingStep {
        let help = catalog.helpLink(for: parcel, language: language).map { Help(name: $0.name, url: $0.url) }
        let stage = parcel.currentStage
        let written = parcel.scheduledDelivery || parcel.writtenDelivery || (stage != .delivered && parcel.writtenAheadBy != nil)
        let writer = written ? parcel.writtenAheadBy.map { catalog.info(for: $0, language: language).displayName } : nil
        let said: ParcelFeedbackPageSaid = written ? .writtenAhead : stage == .delivered ? .delivered : .inTransit
        return ParcelFeedbackMissingStep(pageSaid: said, carrier: writer ?? help?.name ?? carrier, help: help)
    }
}

/// The parcel a question is about, as this iPhone remembers it.
enum ParcelFeedbackSubject: Equatable, Sendable {
    /// One of the account's parcels, or one this iPhone follows itself.
    case parcel(UUID)
    /// A parcel opened through someone's link.
    case link(String)

    /// A link's id opens its parcel, so only its digest is kept.
    var memoryKey: String {
        switch self {
        case .parcel(let id): id.uuidString
        case .link(let id): SHA256.hash(data: Data(id.utf8)).map { String(format: "%02x", $0) }.joined()
        }
    }
}

/// What this iPhone remembers of the question, one entry per parcel: when its reader last
/// answered and about which scan, the scan they last said was right, and the scan the way back
/// from a carrier's site last asked about. The question on the page stands on every visit, kept
/// quiet while the page ends on the scan its reader said was right; the way back asks about a
/// scan once, and about a parcel at most once a day. Nothing of it leaves the device.
struct ParcelFeedbackMemory {
    private static let key = "sdt.parcelFeedback.v1"
    /// Only the latest parcels can still matter.
    static let limit = 200
    /// How long an answer keeps the question on the way back away.
    static let quiet: TimeInterval = 24 * 60 * 60

    private struct Entry: Codable {
        var answeredAt: Date?
        /// What the page ended on when its reader answered.
        var scan: String?
        /// The scan its reader last said was right. Any other answer since forgets it.
        var right: String?
        /// The scan the question was asked about on the way back.
        var back: String?
        var savedAt: Date
    }

    private let defaults: UserDefaults
    private let now: () -> Date

    init(defaults: UserDefaults = .standard, now: @escaping () -> Date = Date.init) {
        self.defaults = defaults
        self.now = now
    }

    /// Whether the way back may ask about this scan: once, not about a scan its reader answered
    /// for, and not within a day of an answer. Only another scan and a day together bring it back.
    func asksOnReturn(_ subject: ParcelFeedbackSubject, scan: String) -> Bool {
        guard let entry = read()[subject.memoryKey] else { return true }
        if entry.back == scan { return false }
        guard let answeredAt = entry.answeredAt else { return true }
        if entry.scan == scan { return false }
        // A clock set back must not keep a new scan from being asked about.
        let age = now().timeIntervalSince(answeredAt)
        return age < 0 || age >= Self.quiet
    }

    /// Whether its reader said this scan was right, and has said nothing else since.
    func saidRight(_ subject: ParcelFeedbackSubject, scan: String) -> Bool {
        read()[subject.memoryKey]?.right == scan
    }

    /// An answer about what the page ends on: a "Yes" (`right`), or anything else.
    func rememberAnswer(_ subject: ParcelFeedbackSubject, scan: String, right: Bool = false) {
        update(subject) {
            $0.answeredAt = now()
            $0.scan = scan
            $0.right = right ? scan : nil
        }
    }

    func rememberAskedOnReturn(_ subject: ParcelFeedbackSubject, scan: String) {
        update(subject) { $0.back = scan }
    }

    func forgetAll() {
        defaults.removeObject(forKey: Self.key)
    }

    private func update(_ subject: ParcelFeedbackSubject, _ change: (inout Entry) -> Void) {
        var entries = read()
        var entry = entries[subject.memoryKey] ?? Entry(savedAt: now())
        change(&entry)
        entry.savedAt = now()
        entries[subject.memoryKey] = entry
        let kept = entries.sorted { $0.value.savedAt > $1.value.savedAt }.prefix(Self.limit)
        defaults.set(try? JSONEncoder().encode(Dictionary(uniqueKeysWithValues: kept.map { ($0.key, $0.value) })), forKey: Self.key)
    }

    private func read() -> [String: Entry] {
        defaults.data(forKey: Self.key).flatMap { try? JSONDecoder().decode([String: Entry].self, from: $0) } ?? [:]
    }
}

extension ParcelFeedbackRequest {
    static let noteLimit = 4_000
    /// A note this close to its limit shows how many characters are left, so nothing is cut off unseen.
    static let noteWarning = 200
    static let carrierNameLimit = 120
    static let trackingPageLimit = 500

    /// What is shown is right. It comes alone.
    static func right(id: UUID = UUID(), asked: ParcelFeedbackRequestAsked, language: AppLanguage) -> ParcelFeedbackRequest {
        ParcelFeedbackRequest(id: id, answer: .right, asked: asked, app: .ios, locale: locale(language))
    }

    /// What is off: reasons in the order the page offers them, a note, or both.
    static func wrong(id: UUID = UUID(), reasons: Set<ParcelFeedbackReason>, note: String = "",
                      asked: ParcelFeedbackRequestAsked, language: AppLanguage) -> ParcelFeedbackRequest {
        let ordered = ParcelFeedbackReason.allCases.filter(reasons.contains)
        return ParcelFeedbackRequest(id: id, answer: .wrong, reasons: ordered.isEmpty ? nil : ordered,
                                     note: words(note, limit: noteLimit), asked: asked, app: .ios, locale: locale(language))
    }

    /// The parcel hasn't come: what the page said of it, and maybe a note.
    static func notReceived(id: UUID = UUID(), pageSaid: ParcelFeedbackPageSaid, note: String = "", language: AppLanguage) -> ParcelFeedbackRequest {
        ParcelFeedbackRequest(id: id, answer: .notReceived, note: words(note, limit: noteLimit), pageSaid: pageSaid,
                              asked: .page, app: .ios, locale: locale(language))
    }

    /// Who carries a parcel no carrier was found for: a name, its tracking page as typed, or both.
    static func foundElsewhere(id: UUID = UUID(), carrierName: String, trackingPage: String, language: AppLanguage) -> ParcelFeedbackRequest {
        ParcelFeedbackRequest(id: id, answer: .foundElsewhere, carrierName: words(carrierName, limit: carrierNameLimit),
                              trackingPage: words(trackingPage, limit: trackingPageLimit), asked: .page, app: .ios, locale: locale(language))
    }

    /// Typed words as an answer carries them: without the space around them, within their limit, nil when nothing is left.
    static func words(_ text: String, limit: Int) -> String? {
        limited(text.trimmingCharacters(in: .whitespacesAndNewlines), to: limit).nonEmpty
    }

    /// What is being typed, kept within what an answer may carry. Whole characters only.
    static func limited(_ text: String, to limit: Int) -> String {
        guard text.unicodeScalars.count > limit else { return text }
        var kept = ""
        var used = 0
        for character in text {
            used += character.unicodeScalars.count
            guard used <= limit else { break }
            kept.append(character)
        }
        return kept
    }

    private static func locale(_ language: AppLanguage) -> NativePushLocale {
        NativePushLocale(rawValue: language.rawValue) ?? .en
    }
}

extension ParcelFeedbackModel.Page {
    /// What a parcel's page asks about, or nil while it asks nothing. A made-up parcel, as the
    /// demo tells its stories with, is not something to judge.
    init?(parcel: Parcel, subject: ParcelFeedbackSubject, showsHistory: Bool, madeUp: Bool, carrier: String, language: AppLanguage) {
        guard !madeUp, let question = ParcelFeedbackQuestion(parcel: parcel, showsHistory: showsHistory) else { return nil }
        self.init(subject: subject, question: question, scan: parcel.feedbackScan, carrier: carrier, language: language,
                  untroubled: parcel.untroubled(), scanned: parcel.hasCarrierUpdate,
                  missing: question == .found ? .of(parcel, carrier: carrier, language: language) : nil)
    }
}

/// How a page sends an answer: to the service as the account, through the parcel's link, or
/// nowhere in the demo.
struct ParcelFeedbackClient {
    var send: @MainActor (ParcelFeedbackRequest) async throws -> Void
}

/// The question on one page: what stands where it is asked, the sheets that take the words, and
/// the word said at the bottom of the page. An answer shows at once, and is taken back if it
/// could not be sent.
@MainActor
final class ParcelFeedbackModel: ObservableObject {
    /// What stands where the question is asked.
    enum Moment: Equatable {
        case ask
        /// Its reader said on an earlier visit that what the page ends on is right: only a way
        /// to report a problem stands, until the next scan.
        case quiet
        /// "Not quite": the reasons are offered, nothing is sent yet.
        case reasons
        case right
        /// A reason was given in place; a note may follow it.
        case sent
        /// The words were sent: there is nothing to add.
        case noted
        /// "I haven't received it": the next step is shown (`Page.missing`); a note may follow it.
        case missing
        /// The note about a parcel that hasn't come was sent.
        case missingNoted
        /// "Not yet": the carrier's own site does not show the parcel either.
        case early
        /// The reader named the carrier.
        case named
    }

    /// What a page shows of its parcel, as far as the question goes.
    struct Page: Equatable {
        var subject: ParcelFeedbackSubject
        var question: ParcelFeedbackQuestion
        /// What the page ends on (`Parcel.feedbackScan`).
        var scan: String
        /// The carrier's name, for the line that says what an answer carries.
        var carrier: String
        /// The language the page speaks, which an answer says too.
        var language: AppLanguage
        /// Nothing about the parcel is wrong (`Parcel.untroubled`): a "Yes" may be the moment to ask for a rating.
        var untroubled = false
        /// The carrier has scanned the parcel (`Parcel.hasCarrierUpdate`). Before that the page's only row is the
        /// app's own, with no time or place of the carrier's to be wrong.
        var scanned = true
        /// For a reader who hasn't received the parcel: what the page says and who to ask. Without it, that isn't offered.
        var missing: ParcelFeedbackMissingStep?

        /// What a reader may say is off.
        var reasons: [ParcelFeedbackReason] {
            ParcelFeedbackReason.allCases.filter { scanned || $0 != .timePlace }
        }
    }

    /// The sheet that takes what is off, or the note about a parcel that hasn't come (`pageSaid`).
    struct Words: Identifiable, Equatable {
        /// The answer's id: a note added to an answer already sent goes under the same one.
        let id: UUID
        var reasons: Set<ParcelFeedbackReason>
        let asked: ParcelFeedbackRequestAsked
        /// What the page said of a parcel its reader hasn't received: the sheet takes a note alone, and needs one.
        var pageSaid: ParcelFeedbackPageSaid? = nil
    }

    /// A word at the bottom of the page.
    enum Word: Equatable {
        /// Asked on the way back from a carrier's own site, named as its link names it.
        case back(site: String)
        case backRight
        case backSent
        case failed

        /// The question waits this long for an answer; a word of thanks stays this long.
        var stay: Duration {
            if case .back = self { .seconds(12) } else { .seconds(4) }
        }
    }

    /// A page left for less than this was not compared with the carrier's.
    static let away: TimeInterval = 1.5

    @Published private(set) var page: Page?
    @Published private(set) var moment = Moment.ask
    @Published var words: Words?
    /// The sheet that asks who carries the parcel is open.
    @Published var namingCarrier = false
    @Published private(set) var word: Word?
    @Published private(set) var sending = false
    /// A "Yes" the service took was the moment to ask for a rating (`ReviewPrompt`), and the page has not asked yet.
    @Published private(set) var reviewDue = false

    private let memory: ParcelFeedbackMemory
    private let now: () -> Date
    private let report: @MainActor (String, DeliveryAnalytics.Outcome) -> Void
    private let review: ReviewPrompt
    private var client: ParcelFeedbackClient?
    /// The answer given in place, a reason or "I haven't received it", for the note that may follow it.
    private var given: Words?
    /// The reader's leave for a carrier's own page, and when the app left the screen for it.
    private var visit: (site: String, left: Date?)?

    init(memory: ParcelFeedbackMemory = ParcelFeedbackMemory(), now: @escaping () -> Date = Date.init,
         report: @escaping @MainActor (String, DeliveryAnalytics.Outcome) -> Void = { DeliveryAnalytics.shared.action($0, $1) },
         review: ReviewPrompt = ReviewPrompt()) {
        self.memory = memory
        self.now = now
        self.report = report
        self.review = review
    }

    /// The question standing on the page, or nil while there is none to ask. It stands on every
    /// visit, for what its reader tells only later; Pip asks it until they say the scan the page
    /// ends on is right, then it keeps quiet until the next scan.
    var standing: ParcelFeedbackQuestion? { page?.question }

    /// Takes what the page shows now. Another parcel, or a carrier found while the page was open, starts over;
    /// a new scan brings back a question kept quiet.
    func show(_ page: Page?, through client: ParcelFeedbackClient?) {
        self.client = client
        guard page != self.page else { return }
        let before = self.page
        self.page = page
        if before?.subject != page?.subject {
            words = nil
            namingCarrier = false
            word = nil
            visit = nil
            reviewDue = false
        }
        if before?.subject != page?.subject || before?.question != page?.question {
            moment = start(page)
            given = nil
        } else if moment == .quiet, before?.scan != page?.scan {
            moment = .ask
        }
    }

    /// Where the question starts: kept quiet about the scan its reader said was right.
    private func start(_ page: Page?) -> Moment {
        guard let page, page.question == .found, memory.saidRight(page.subject, scan: page.scan) else { return .ask }
        return .quiet
    }

    // MARK: - On the way back

    /// The reader opens a carrier's own page, named as its link names it.
    func visited(_ site: String) {
        guard page?.question == .found else { return }
        visit = (site, nil)
    }

    /// The app leaves the screen.
    func left() {
        guard let visit, visit.left == nil else { return }
        self.visit = (visit.site, now())
    }

    /// The app is back: the reader has just seen what the carrier's own site says. While something
    /// else is said or asked over the page, the question waits for another visit.
    func returned(busy: Bool = false) {
        guard let visit, let left = visit.left else { return }
        self.visit = nil
        guard let page, page.question == .found, !busy, words == nil, !namingCarrier,
              moment == .ask || moment == .reasons,
              now().timeIntervalSince(left) >= Self.away,
              memory.asksOnReturn(page.subject, scan: page.scan) else { return }
        // Asked once about a scan, answered or not.
        memory.rememberAskedOnReturn(page.subject, scan: page.scan)
        word = .back(site: visit.site)
    }

    /// A word leaves by itself once it has stayed its time.
    func wordLeft(_ word: Word) {
        if self.word == word { self.word = nil }
    }

    // MARK: - Answers

    /// What is shown is right: said in place, or on the way back.
    func right(_ asked: ParcelFeedbackRequestAsked = .page) async {
        guard let page, page.question == .found else { return }
        let before = moment
        moment = .right
        word = asked == .back ? .backRight : nil
        if await deliver(.right(asked: asked, language: page.language), for: page) { return }
        guard self.page?.subject == page.subject else { return }
        moment = before
        word = .failed
    }

    /// "Not quite", or "Report a problem" while the question keeps quiet.
    func notQuite() {
        guard moment == .ask || moment == .quiet else { return }
        // Something is off: no rating is asked for today.
        review.noteBadMoment(at: now())
        moment = .reasons
    }

    /// One reason, given in place.
    func pick(_ reason: ParcelFeedbackReason) async {
        guard let page, moment == .reasons else { return }
        let answer = Words(id: UUID(), reasons: [reason], asked: .page)
        given = answer
        moment = .sent
        if await deliver(.wrong(id: answer.id, reasons: answer.reasons, asked: .page, language: page.language), for: page) { return }
        guard self.page?.subject == page.subject else { return }
        given = nil
        moment = .reasons
        word = .failed
    }

    /// "I haven't received it", given in place: the next step shows at once (`Page.missing`).
    func notReceived() async {
        guard let page, let missing = page.missing, moment == .reasons else { return }
        let answer = Words(id: UUID(), reasons: [], asked: .page, pageSaid: missing.pageSaid)
        given = answer
        moment = .missing
        if await deliver(.notReceived(id: answer.id, pageSaid: missing.pageSaid, language: page.language), for: page) { return }
        guard self.page?.subject == page.subject else { return }
        given = nil
        moment = .reasons
        word = .failed
    }

    /// More to say about the answer just given in place.
    func addNote() {
        guard moment == .sent || moment == .missing, let given else { return }
        words = given
    }

    /// The carrier's own site says something else.
    func wrongOnReturn() {
        review.noteBadMoment(at: now())
        word = nil
        words = Words(id: UUID(), reasons: [], asked: .back)
    }

    /// The carrier's own site shows the parcel: the reader says whose it is.
    func elsewhere() {
        review.noteBadMoment(at: now())
        namingCarrier = true
    }

    /// Nothing to send: tomorrow the carrier's site may show it, and the row asks again.
    func notYet() {
        guard page?.question == .unknown else { return }
        moment = .early
    }

    /// Sends what the open sheet holds. False keeps the sheet open with what was typed.
    func sendWords(_ reasons: Set<ParcelFeedbackReason>, note: String) async -> Bool {
        guard let page, let words, !sending else { return false }
        // The note about a parcel that hasn't come is all it says: it needs one.
        if words.pageSaid != nil, ParcelFeedbackRequest.words(note, limit: ParcelFeedbackRequest.noteLimit) == nil { return false }
        sending = true
        defer { sending = false }
        // A note added to the answer given in place is the same answer: it is not counted twice.
        let feedback = if let missing = words.pageSaid {
            ParcelFeedbackRequest.notReceived(id: words.id, pageSaid: missing, note: note, language: page.language)
        } else {
            ParcelFeedbackRequest.wrong(id: words.id, reasons: reasons, note: note, asked: words.asked, language: page.language)
        }
        guard await deliver(feedback, for: page, counted: words.asked == .back) else { return false }
        guard self.page?.subject == page.subject else { return true }
        self.words = nil
        if self.page?.question == .found { moment = words.pageSaid != nil ? .missingNoted : .noted }
        if words.asked == .back { word = .backSent }
        return true
    }

    /// Sends who carries the parcel. False keeps the sheet open with what was typed.
    func sendCarrier(name: String, page address: String) async -> Bool {
        guard let page, !sending else { return false }
        sending = true
        defer { sending = false }
        guard await deliver(.foundElsewhere(carrierName: name, trackingPage: address, language: page.language), for: page) else { return false }
        guard self.page?.subject == page.subject else { return true }
        namingCarrier = false
        if self.page?.question == .unknown { moment = .named }
        return true
    }

    /// Sends an answer, and remembers it once the service has it.
    private func deliver(_ feedback: ParcelFeedbackRequest, for page: Page, counted: Bool = true) async -> Bool {
        let action = switch feedback.answer {
        case .right: "parcel-feedback-right"
        case .wrong: "parcel-feedback-wrong"
        case .foundElsewhere: "parcel-feedback-carrier"
        case .notReceived: "parcel-feedback-not-received"
        }
        do {
            guard let client else { throw DeliveryAPIError.parcelMissing }
            try await client.send(feedback)
            if counted { report(action, .success) }
            memory.rememberAnswer(page.subject, scan: page.scan, right: feedback.answer == .right)
            // The reader found the page right: on a parcel with nothing wrong, the moment to ask for a rating.
            if feedback.answer == .right, review.isDue(at: now(), calm: page.untroubled) { reviewDue = true }
            return true
        } catch {
            if counted { report(action, .error) }
            review.noteBadMoment(at: now())
            return false
        }
    }

    /// The page asks for a rating now, a moment after the "Yes" that made it due: true once, with nothing said at its
    /// foot and the rule still holding for what it shows. The page says when nothing else is over it.
    func takeReviewMoment() -> Bool {
        guard reviewDue else { return false }
        reviewDue = false
        guard let page, word == nil else { return false }
        return review.take(at: now(), calm: page.untroubled)
    }
}

#if DEBUG
extension ParcelFeedbackModel {
    /// Puts the question in the state `ParcelFeedbackPreview` names, whatever was answered before.
    func preview(_ variant: String) {
        guard let page else { return }
        given = Words(id: UUID(), reasons: [.steps], asked: .page)
        switch variant {
        case "quiet": moment = .quiet
        case "reasons": moment = .reasons
        case "right": moment = .right
        case "sent": moment = .sent
        case "noted": moment = .noted
        case "missing", "missingNoted", "missingWords":
            guard let missing = page.missing else { return }
            let answer = Words(id: UUID(), reasons: [], asked: .page, pageSaid: missing.pageSaid)
            given = answer
            moment = variant == "missingNoted" ? .missingNoted : .missing
            if variant == "missingWords" { words = answer }
        case "early": moment = .early
        case "named": moment = .named
        case "back": word = .back(site: page.carrier)
        case "backSent": (moment, word) = (.noted, .backSent)
        case "failed": word = .failed
        case "words": words = given
        case "carrier": namingCarrier = true
        default: moment = .ask
        }
    }
}
#endif
