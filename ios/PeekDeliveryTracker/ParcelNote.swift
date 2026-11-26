import CoreGraphics
import Foundation
import ImageIO
import UniformTypeIdentifiers

/// A parcel's private note: up to 1,000 characters and five photos or PDFs, seen by its owner alone. It shows on the
/// parcel's page, marks its row in the list and joins the list's search, and nowhere else: no widget, Live Activity,
/// notification, intent, menu bar item, share or link carries it. An account keeps it on the service; a guest keeps
/// its words in the device parcel and its files in Application Support (`LocalNoteFiles`), until signing in sends them.
enum ParcelNoteLimits {
    /// Counted in Unicode code points, as the server counts them.
    static let maxLength = 1_000
    /// The count of what is left shows for the last hundred.
    static let countFrom = 100
    static let filesPerParcel = 5
    /// Each file as sent: 10 MB.
    static let fileMaxBytes = 10_485_760
    /// All of an account's files, or a guest device's: 200 MB.
    static let filesTotalBytes = 209_715_200
    static let fileNameMax = 120
    /// Photos are made to fit this many pixels on their longest side before they are sent.
    static let photoMaxSide = 2_560
    static let photoQuality = 0.85
    /// How long a removal can be undone; with VoiceOver or Switch Control, longer.
    static let undoSeconds: Double = 6
    static let undoSecondsAssisted: Double = 20
}

enum ParcelNoteText {
    static func length(_ text: String) -> Int { text.unicodeScalars.count }

    static func left(_ text: String) -> Int { ParcelNoteLimits.maxLength - length(text) }

    /// The text cut to the limit, never through a character.
    static func clip(_ text: String) -> String {
        guard length(text) > ParcelNoteLimits.maxLength else { return text }
        var kept = ""
        var used = 0
        for character in text {
            used += character.unicodeScalars.count
            guard used <= ParcelNoteLimits.maxLength else { break }
            kept.append(character)
        }
        return kept
    }

    /// A run of a note's text: plain, or a web or e-mail address to follow.
    struct Piece: Equatable {
        let text: String
        var link: URL? = nil
    }

    /// Endings that make a bare name an address, as people write one ("shop.example/returns").
    private static let domainEndings: Set<String> = [
        "com", "org", "net", "io", "co", "app", "dev", "shop", "store", "info", "biz", "eu", "uk", "de", "fr", "ch", "it", "es",
        "pt", "br", "pl", "ru", "cn", "nl", "be", "at", "ie", "lu", "se", "no", "dk", "fi", "cz", "us", "ca", "au", "jp", "mx",
        "example",
    ]
    private static let address = try? NSRegularExpression(
        pattern: #"([\w.+-]+@[\w-]+(?:\.[\w-]+)+)|(https?://[^\s<>"]+)|((?:www\.)?(?:[\p{L}\p{N}-]+\.)+(\p{L}{2,})(?:/[^\s<>"]*)?)"#,
        options: [.caseInsensitive]
    )
    private static let trailing: Set<Character> = [".", ",", ";", ":", "!", "?", "'", "\"", "’", "”", ")", "]"]

    /// The note's text with its web and e-mail addresses made into links; nothing else is formatted. A bare name counts
    /// as an address with `www.` or a known ending; punctuation after an address stays text (the web's `notePieces`).
    static func pieces(_ text: String) -> [Piece] {
        guard let address else { return [Piece(text: text)] }
        let whole = text as NSString
        var pieces: [Piece] = []
        var last = 0
        for match in address.matches(in: text, range: NSRange(location: 0, length: whole.length)) {
            let start = match.range.location
            guard start >= last else { continue }
            if start > 0, let before = Range(NSRange(location: start - 1, length: 1), in: text).map({ text[$0] }),
               before.unicodeScalars.allSatisfy({ CharacterSet.letters.union(.decimalDigits).contains($0) || $0 == "@" || $0 == "/" }) {
                continue
            }
            let email = match.range(at: 1).location != NSNotFound
            let web = match.range(at: 2).location != NSNotFound
            if !email, !web {
                let ending = whole.substring(with: match.range(at: 4)).lowercased()
                let bare = whole.substring(with: match.range(at: 3)).lowercased()
                if !bare.hasPrefix("www."), !domainEndings.contains(ending) { continue }
            }
            var found = whole.substring(with: match.range)
            // A sentence's full stop, a comma or a closing bracket the address did not open stay outside it.
            while let end = found.last, trailing.contains(end) {
                if end == ")", found.filter({ $0 == "(" }).count >= found.filter({ $0 == ")" }).count { break }
                found.removeLast()
            }
            guard found.contains(".") else { continue }
            let target = email ? "mailto:\(found)" : web ? found : "https://\(found)"
            if start > last { pieces.append(Piece(text: whole.substring(with: NSRange(location: last, length: start - last)))) }
            pieces.append(Piece(text: found, link: URL(string: target)))
            last = start + (found as NSString).length
        }
        if last < whole.length { pieces.append(Piece(text: whole.substring(from: last))) }
        return pieces
    }

    /// The note as the page shows it, its addresses as links.
    static func attributed(_ text: String) -> AttributedString {
        var result = AttributedString()
        for piece in pieces(text) {
            var run = AttributedString(piece.text)
            if let link = piece.link {
                run.link = link
                run.underlineStyle = .single
            }
            result += run
        }
        return result
    }
}

extension Parcel {
    /// A parcel has a note when it has words or at least one file.
    var hasNote: Bool { note?.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty == false || !(files ?? []).isEmpty }

    /// The words a note's search matches: its text and its files' names.
    var noteSearchText: String { ([note ?? ""] + (files ?? []).map(\.name)).joined(separator: " ") }

    /// The few words of the note a list search found the parcel by; nil when nothing is searched, or when neither the
    /// note nor a file's name holds the search.
    func noteMatch(_ query: String) -> NoteSearch.Snippet? {
        NoteSearch.snippet(note: note, fileNames: (files ?? []).map(\.name), query: query)
    }
}

/// Where a list search matched, so a row of the reader's own list can mark it (the web's `searchMatch.ts`). Places are
/// counted in characters, which is how a row cuts its text.
enum NoteSearch {
    struct Piece: Equatable {
        let text: String
        let matched: Bool
    }

    struct Snippet: Equatable {
        /// The words around the match, with "…" where the note goes on.
        let text: String
        /// Every match within them.
        let ranges: [Range<Int>]
    }

    /// How much of a note a row shows around the first match: a few words before it, and one line in all.
    static let before = 24
    static let length = 90

    /// The search as the list reads it: trimmed, its spaces one, in lower case. Empty when nothing is searched.
    static func searched(_ query: String) -> String {
        query.split(whereSeparator: \.isWhitespace).joined(separator: " ").lowercased()
    }

    /// Every place `query` stands in `text`, ignoring case, one after the other and none overlapping.
    static func ranges(in text: String, query: String) -> [Range<Int>] {
        let wanted = Array(searched(query).unicodeScalars)
        guard !wanted.isEmpty else { return [] }
        // Each character in lower case on its own, so a place in the lowered text leads back to a character.
        var lowered: [Unicode.Scalar] = []
        var startOf: [Int] = []
        for character in text {
            startOf.append(lowered.count)
            lowered.append(contentsOf: String(character).lowercased().unicodeScalars)
        }
        func character(at offset: Int) -> Int { startOf.lastIndex { $0 <= offset } ?? 0 }
        var ranges: [Range<Int>] = []
        var at = 0
        while at + wanted.count <= lowered.count {
            if lowered[at..<at + wanted.count].elementsEqual(wanted) {
                ranges.append(character(at: at)..<character(at: at + wanted.count - 1) + 1)
                at += wanted.count
            } else {
                at += 1
            }
        }
        return ranges
    }

    /// Text cut into its matched and unmatched pieces, in order.
    static func pieces(_ text: String, _ ranges: [Range<Int>]) -> [Piece] {
        let characters = Array(text)
        var pieces: [Piece] = []
        var at = 0
        for range in ranges where range.lowerBound >= at && range.upperBound <= characters.count {
            if range.lowerBound > at { pieces.append(Piece(text: String(characters[at..<range.lowerBound]), matched: false)) }
            pieces.append(Piece(text: String(characters[range]), matched: true))
            at = range.upperBound
        }
        if at < characters.count { pieces.append(Piece(text: String(characters[at...]), matched: false)) }
        return pieces
    }

    /// The few words of a note (its spaces folded), or failing that the name of one of its files, that a search
    /// matched. Nil when neither holds the search: the row matched by its name, number or carrier.
    static func snippet(note: String?, fileNames: [String], query: String) -> Snippet? {
        let flat = (note ?? "").split(whereSeparator: \.isWhitespace).joined(separator: " ")
        let found = ranges(in: flat, query: query)
        if !found.isEmpty { return around(flat, found) }
        for name in fileNames {
            let named = ranges(in: name, query: query)
            if !named.isEmpty { return around(name, named) }
        }
        return nil
    }

    /// The window of `text` that starts a few words before the first match and fits a line, cut between words.
    private static func around(_ text: String, _ ranges: [Range<Int>]) -> Snippet {
        let characters = Array(text)
        let first = ranges[0]
        var start = max(0, first.lowerBound - before)
        // Start on a whole word: after the first space in the few characters before the match, if there is one.
        if start > 0, characters[start - 1] != " ", let space = characters[start..<first.lowerBound].firstIndex(of: " ") {
            start = space + 1
        }
        var end = min(characters.count, max(start + length, first.upperBound))
        // End on a word, never before the first match does.
        if end < characters.count, let space = characters[first.upperBound..<end].lastIndex(of: " ") { end = space }
        let shift = start > 0 ? start - 1 : 0
        let shown = ranges.filter { $0.lowerBound >= start && $0.upperBound <= end }
            .map { ($0.lowerBound - shift)..<($0.upperBound - shift) }
        var words = String(characters[start..<end])
        while words.last?.isWhitespace == true { words.removeLast() }
        return Snippet(text: (start > 0 ? "…" : "") + words + (end < characters.count ? "…" : ""), ranges: shown)
    }
}

extension PackageFile {
    var isPDF: Bool { contentType == .applicationPdf }
}

/// The `PATCH` that changes a parcel's note alone: null deletes it, as blank text does.
struct NoteChange: Encodable, Sendable {
    let note: String?

    init(note: String?) {
        self.note = note.flatMap { $0.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty }
    }

    private enum CodingKeys: String, CodingKey { case note }

    func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(note, forKey: .note)
    }

    private static let plain = CharacterSet(charactersIn: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.!~*'()")

    /// A file's name as `X-File-Name` carries it: percent-encoded UTF-8, as `encodeURIComponent` writes it.
    static func headerName(_ name: String) -> String {
        name.addingPercentEncoding(withAllowedCharacters: plain) ?? "File"
    }
}

/// Why a file could not join a note, each said in one plain line (`note.files.*`), and sent with `parcel-file-add`.
enum NoteFileProblem: String, Equatable, Sendable {
    case tooBig = "too-big", type, parcelFull = "parcel-full", accountFull = "account-full", deviceFull = "device-full"
    case unreadable, offline, failed

    /// Problems a second try may get past.
    var retriable: Bool { self == .failed || self == .offline }

    /// The problem of an upload the server refused, by its status and code.
    static func upload(status: Int, code: ErrorResponseCode?) -> NoteFileProblem {
        switch code {
        case .fileTooBig: return .tooBig
        case .fileType: return .type
        case .parcelFilesFull: return .parcelFull
        case .accountFilesFull: return .accountFull
        default: break
        }
        if status == 413 { return .tooBig }
        if status == 415 { return .type }
        return .failed
    }
}

struct NoteFileError: Error, Equatable {
    let problem: NoteFileProblem
    /// The file's size, for a file too big.
    var size: Int? = nil
    /// The server's status, for a file it refused.
    var status: Int? = nil
}

/// A file ready to be kept with a note.
struct PreparedNoteFile: Equatable, Sendable {
    var data: Data
    var name: String
    var type: PackageFileType
    var width: Int? = nil
    var height: Int? = nil
}

enum NotePreparation {
    /// The name kept for a file: within 120 characters with its extension, which follows a change of type.
    static func fileName(_ name: String, type: PackageFileType) -> String {
        let fileExtension = switch type {
        case .applicationPdf: "pdf"
        case .imagePng: "png"
        case .imageWebp: "webp"
        case .imageJpeg: "jpg"
        }
        var scalars = String.UnicodeScalarView()
        for scalar in name.unicodeScalars {
            let control = scalar.value < 0x20 || scalar.value == 0x7F || scalar == "/" || scalar == "\\"
            scalars.append(control ? " " : scalar)
        }
        let cleaned = String(scalars).components(separatedBy: .whitespacesAndNewlines).filter { !$0.isEmpty }.joined(separator: " ")
        let dot = cleaned.lastIndex(of: ".")
        let base = (dot.map { String(cleaned[..<$0]) } ?? cleaned).trimmingCharacters(in: .whitespaces).nonEmpty ?? "File"
        let known = dot.map { String(cleaned[cleaned.index(after: $0)...]).lowercased() } ?? ""
        let kept = known == fileExtension || (fileExtension == "jpg" && known == "jpeg") ? known : fileExtension
        let room = ParcelNoteLimits.fileNameMax - kept.unicodeScalars.count - 1
        var short = ""
        var used = 0
        for character in base {
            used += character.unicodeScalars.count
            guard used <= room else { break }
            short.append(character)
        }
        return "\(short).\(kept)"
    }

    /// Readies a chosen file for a note: a PDF as it is; a photo made to fit 2,560 px and re-encoded as JPEG when it is
    /// larger or HEIC; a PNG kept as PNG while it stays under 10 MB (a screenshot's text stays sharp). Refuses other
    /// kinds, and anything still over 10 MB.
    static func prepare(_ data: Data, name: String) throws -> PreparedNoteFile {
        if data.starts(with: Array("%PDF".utf8)) {
            guard data.count <= ParcelNoteLimits.fileMaxBytes else { throw NoteFileError(problem: .tooBig, size: data.count) }
            return PreparedNoteFile(data: data, name: fileName(name, type: .applicationPdf), type: .applicationPdf)
        }
        let heicNamed = ["heic", "heif"].contains((name as NSString).pathExtension.lowercased())
        guard let source = CGImageSourceCreateWithData(data as CFData, nil), let typeID = CGImageSourceGetType(source) as String? else {
            throw NoteFileError(problem: heicNamed ? .unreadable : .type)
        }
        let kind = UTType(typeID)
        let type: PackageFileType
        var heic = false
        if kind == .jpeg { type = .imageJpeg }
        else if kind == .png { type = .imagePng }
        else if kind == .webP { type = .imageWebp }
        else if let kind, kind.conforms(to: .heic) || kind.conforms(to: .heif) || kind.identifier == "public.avci" { type = .imageJpeg; heic = true }
        else { throw NoteFileError(problem: .type) }
        guard let properties = CGImageSourceCopyPropertiesAtIndex(source, 0, nil) as? [CFString: Any],
              let pixelWidth = properties[kCGImagePropertyPixelWidth] as? Int,
              let pixelHeight = properties[kCGImagePropertyPixelHeight] as? Int, pixelWidth > 0, pixelHeight > 0 else {
            throw NoteFileError(problem: .unreadable)
        }
        // Photos keep the way up their camera recorded.
        let turned = ((properties[kCGImagePropertyOrientation] as? Int) ?? 1) >= 5
        let (width, height) = turned ? (pixelHeight, pixelWidth) : (pixelWidth, pixelHeight)
        let longest = max(width, height)
        let scale = min(1, Double(ParcelNoteLimits.photoMaxSide) / Double(longest))
        if !heic, scale == 1, data.count <= ParcelNoteLimits.fileMaxBytes {
            return PreparedNoteFile(data: data, name: fileName(name, type: type), type: type, width: width, height: height)
        }
        let side = max(1, Int((Double(longest) * scale).rounded()))
        guard let image = CGImageSourceCreateThumbnailAtIndex(source, 0, [
            kCGImageSourceCreateThumbnailFromImageAlways: true,
            kCGImageSourceCreateThumbnailWithTransform: true,
            kCGImageSourceThumbnailMaxPixelSize: side,
            kCGImageSourceShouldCacheImmediately: true,
        ] as CFDictionary) else { throw NoteFileError(problem: .unreadable) }
        if type == .imagePng, !heic, let png = encode(image, as: .png), png.count <= ParcelNoteLimits.fileMaxBytes {
            return PreparedNoteFile(data: png, name: fileName(name, type: .imagePng), type: .imagePng, width: image.width, height: image.height)
        }
        guard let jpeg = encode(flattened(image), as: .jpeg) else { throw NoteFileError(problem: .unreadable) }
        guard jpeg.count <= ParcelNoteLimits.fileMaxBytes else { throw NoteFileError(problem: .tooBig, size: jpeg.count) }
        return PreparedNoteFile(data: jpeg, name: fileName(name, type: .imageJpeg), type: .imageJpeg, width: image.width, height: image.height)
    }

    static func encode(_ image: CGImage, as type: UTType) -> Data? {
        let data = NSMutableData()
        guard let destination = CGImageDestinationCreateWithData(data, type.identifier as CFString, 1, nil) else { return nil }
        let options: [CFString: Any] = type == .jpeg ? [kCGImageDestinationLossyCompressionQuality: ParcelNoteLimits.photoQuality] : [:]
        CGImageDestinationAddImage(destination, image, options as CFDictionary)
        return CGImageDestinationFinalize(destination) ? data as Data : nil
    }

    /// A JPEG has no transparency: what was clear stands on white.
    private static func flattened(_ image: CGImage) -> CGImage {
        switch image.alphaInfo {
        case .none, .noneSkipFirst, .noneSkipLast: return image
        default: break
        }
        guard let context = CGContext(data: nil, width: image.width, height: image.height, bitsPerComponent: 8, bytesPerRow: 0,
                                      space: CGColorSpace(name: CGColorSpace.sRGB) ?? CGColorSpaceCreateDeviceRGB(),
                                      bitmapInfo: CGImageAlphaInfo.noneSkipLast.rawValue) else { return image }
        let frame = CGRect(x: 0, y: 0, width: image.width, height: image.height)
        context.setFillColor(CGColor(red: 1, green: 1, blue: 1, alpha: 1))
        context.fill(frame)
        context.draw(image, in: frame)
        return context.makeImage() ?? image
    }

    /// A small picture of a photo, for its thumbnail while it is on its way.
    static func preview(_ data: Data, side: Int = 192) -> CGImage? {
        guard let source = CGImageSourceCreateWithData(data as CFData, nil) else { return nil }
        return CGImageSourceCreateThumbnailAtIndex(source, 0, [
            kCGImageSourceCreateThumbnailFromImageAlways: true,
            kCGImageSourceCreateThumbnailWithTransform: true,
            kCGImageSourceThumbnailMaxPixelSize: side,
        ] as CFDictionary)
    }
}

/// One file of a note kept on this device: a guest's, or the demo's.
struct LocalNoteFile: Codable, Equatable, Sendable {
    var id: UUID
    /// What it belongs to: a guest parcel's link, or a demo parcel's id.
    var key: String
    var name: String
    var type: PackageFileType
    var size: Int
    var width: Int? = nil
    var height: Int? = nil
    var created: String
    /// The account's parcel it goes to, once the parcel was kept there: it is sent there, and leaves the device once it
    /// arrived.
    var packageID: UUID? = nil
    /// When the account last had no room for it: it is offered again a day later.
    var refusedAt: String? = nil
}

/// The photos and PDFs of the notes kept on this device, in Application Support (never the App Group container,
/// which the widget reads): each file's bytes beside one index of what they are. A guest's are keyed by link, the
/// demo's by parcel. Files are addressed as `peek-local:<space>/<file>`, which `resolve` turns into the file's URL
/// wherever the app's container is now.
final class LocalNoteFiles {
    static let scheme = "peek-local"
    /// Long past any expiry: a file on the device needs no fresh address.
    static let never = "9999-12-31T23:59:59Z"

    nonisolated(unsafe) private static var roots: [String: URL] = [:]
    private static let lock = NSLock()

    let space: String
    private let directory: URL?
    private let freeSpace: () -> Int64?
    private(set) var all: [LocalNoteFile]

    /// A guest's files.
    nonisolated(unsafe) static let device = LocalNoteFiles(space: "device")
    /// The demo's.
    nonisolated(unsafe) static let demo = LocalNoteFiles(space: "demo")

    init(space: String, root: URL? = nil, freeSpace: (() -> Int64?)? = nil) {
        self.space = space
        let base = root ?? (try? FileManager.default.url(for: .applicationSupportDirectory, in: .userDomainMask, appropriateFor: nil, create: true))
            .map { $0.appending(path: "NoteFiles", directoryHint: .isDirectory) }
        let directory = base?.appending(path: space, directoryHint: .isDirectory)
        if let directory { try? FileManager.default.createDirectory(at: directory, withIntermediateDirectories: true) }
        self.directory = directory
        self.freeSpace = freeSpace ?? { Self.availableSpace(at: directory) }
        all = directory.flatMap { try? Data(contentsOf: $0.appending(path: "index.json")) }
            .flatMap { try? JSONDecoder.deliveryTracker.decode([LocalNoteFile].self, from: $0) } ?? []
        if let directory {
            Self.lock.lock()
            Self.roots[space] = directory
            Self.lock.unlock()
        }
    }

    /// The files of one parcel's note here, oldest first; those on their way to an account are no longer its.
    func of(_ key: String) -> [LocalNoteFile] {
        all.filter { $0.key == key && $0.packageID == nil }.sorted { $0.created < $1.created }
    }

    /// The files waiting to be sent to an account's parcel.
    var kept: [LocalNoteFile] { all.filter { $0.packageID != nil } }

    var totalSize: Int { all.reduce(0) { $0 + $1.size } }

    /// Keeps a file with a parcel's note here, within the same limits as an account: five per parcel, 10 MB each, and
    /// 200 MB for the device, or less where the device has less room.
    @discardableResult
    func add(_ file: PreparedNoteFile, to key: String, now: Date = Date()) throws -> LocalNoteFile {
        guard file.data.count <= ParcelNoteLimits.fileMaxBytes else { throw NoteFileError(problem: .tooBig, size: file.data.count) }
        guard of(key).count < ParcelNoteLimits.filesPerParcel else { throw NoteFileError(problem: .parcelFull) }
        guard totalSize + file.data.count <= ParcelNoteLimits.filesTotalBytes,
              freeSpace().map({ $0 > Int64(file.data.count) + 20_000_000 }) ?? true,
              let directory else { throw NoteFileError(problem: .deviceFull) }
        let stored = LocalNoteFile(id: UUID(), key: key, name: file.name, type: file.type, size: file.data.count,
                                   width: file.width, height: file.height, created: DateParser.isoString(now))
        do {
            try file.data.write(to: directory.appending(path: Self.fileName(stored)),
                                options: [.atomic, .completeFileProtectionUntilFirstUserAuthentication])
        } catch let error as CocoaError where error.code == .fileWriteOutOfSpace {
            throw NoteFileError(problem: .deviceFull)
        } catch {
            throw NoteFileError(problem: .failed)
        }
        all.append(stored)
        save()
        return stored
    }

    func data(of id: UUID) -> Data? {
        guard let file = all.first(where: { $0.id == id }), let directory else { return nil }
        return try? Data(contentsOf: directory.appending(path: Self.fileName(file)))
    }

    func remove(_ ids: Set<UUID>) {
        guard !ids.isEmpty else { return }
        for file in all where ids.contains(file.id) { delete(file) }
        all.removeAll { ids.contains($0.id) }
        save()
    }

    /// Deletes a forgotten parcel's files; those on their way to an account stay until they arrive.
    func forget(_ key: String) {
        remove(Set(all.filter { $0.key == key && $0.packageID == nil }.map(\.id)))
    }

    /// Deletes every file but those on their way to an account.
    func forgetAll() {
        remove(Set(all.filter { $0.packageID == nil }.map(\.id)))
    }

    /// Moves a parcel's files to the one that takes its place.
    @discardableResult
    func move(from old: String, to new: String) -> Int {
        relabel(old) { $0.key = new }
    }

    /// Marks a parcel's files for the account's parcel it was kept as: they are sent there, and forgetting the link keeps
    /// them.
    @discardableResult
    func handOver(_ key: String, to packageID: UUID) -> Int {
        relabel(key) { $0.packageID = packageID }
    }

    /// How long a file the account had no room for waits before it is offered again.
    static let refusedWait: TimeInterval = 24 * 60 * 60

    /// Sends the files waiting for an account's parcels, one at a time, while `going` holds. Each leaves the device once
    /// it arrived, or once the service says it never will: 404, the parcel has gone; 413 or 415, its size or type. One
    /// the account had no room for (409) waits a day, after a relaunch too; any other failure, the next start. `send`
    /// throws a `NoteFileError` with the service's status. Returns how many arrived.
    @MainActor
    func sendKept(now: Date = Date(), going: () -> Bool = { true },
                  _ send: (UUID, PreparedNoteFile) async throws -> Void) async -> Int {
        var sent = 0
        for file in kept {
            guard going(), let packageID = file.packageID else { return sent }
            // Not ten megabytes again at every start for an account that has no room.
            if let refused = file.refusedAt.flatMap(DateParser.date), now.timeIntervalSince(refused) < Self.refusedWait { continue }
            guard let data = data(of: file.id) else { remove([file.id]); continue }
            do {
                try await send(packageID, PreparedNoteFile(data: data, name: file.name, type: file.type, width: file.width, height: file.height))
                remove([file.id])
                sent += 1
            } catch let error as NoteFileError where [404, 413, 415].contains(error.status) {
                remove([file.id])
            } catch let error as NoteFileError where error.status == 409 {
                if let index = all.firstIndex(where: { $0.id == file.id }) {
                    all[index].refusedAt = DateParser.isoString(now)
                    save()
                }
            } catch {
                continue
            }
        }
        return sent
    }

    private func relabel(_ key: String, _ change: (inout LocalNoteFile) -> Void) -> Int {
        var changed = 0
        for index in all.indices where all[index].key == key && all[index].packageID == nil {
            change(&all[index])
            changed += 1
        }
        if changed > 0 { save() }
        return changed
    }

    /// The file as a parcel lists it, its address on this device.
    func packageFile(_ file: LocalNoteFile) -> PackageFile {
        let address = "\(Self.scheme):\(space)/\(Self.fileName(file))"
        return PackageFile(id: file.id, name: file.name, contentType: file.type, size: file.size, width: file.width,
                           height: file.height, createdAt: file.created, url: address,
                           thumbnailURL: file.type == .applicationPdf ? nil : address, expiresAt: Self.never)
    }

    func packageFiles(of key: String) -> [PackageFile]? {
        let files = of(key).map(packageFile)
        return files.isEmpty ? nil : files
    }

    /// A file's address: one of this device's, or the service's, which is relative to the API.
    static func resolve(_ address: String, apiBase: URL) -> URL? {
        let prefix = "\(scheme):"
        guard address.hasPrefix(prefix) else { return URL(string: address, relativeTo: apiBase)?.absoluteURL }
        let path = address.dropFirst(prefix.count).split(separator: "/", maxSplits: 1).map(String.init)
        guard path.count == 2, !path[1].contains("/"), !path[1].hasPrefix(".") else { return nil }
        lock.lock()
        defer { lock.unlock() }
        return roots[path[0]]?.appending(path: path[1])
    }

    static func isLocal(_ address: String) -> Bool { address.hasPrefix("\(scheme):") }

    private static func fileName(_ file: LocalNoteFile) -> String {
        let fileExtension = switch file.type {
        case .applicationPdf: "pdf"
        case .imagePng: "png"
        case .imageWebp: "webp"
        case .imageJpeg: "jpg"
        }
        return "\(file.id.uuidString.lowercased()).\(fileExtension)"
    }

    private func delete(_ file: LocalNoteFile) {
        guard let directory else { return }
        try? FileManager.default.removeItem(at: directory.appending(path: Self.fileName(file)))
    }

    private func save() {
        guard let directory, let data = try? JSONEncoder.deliveryTracker.encode(all) else { return }
        try? data.write(to: directory.appending(path: "index.json"), options: [.atomic, .completeFileProtectionUntilFirstUserAuthentication])
    }

    private static func availableSpace(at directory: URL?) -> Int64? {
        guard let directory, let values = try? directory.resourceValues(forKeys: [.volumeAvailableCapacityForImportantUsageKey]) else {
            return nil
        }
        return values.volumeAvailableCapacityForImportantUsage
    }
}

/// The demo's one private note, on the record from Round Midnight Records: made-up order details and a shop address
/// under `.example`, which belongs to no one, with a receipt and a photo of the box drawn on the device. It is written in
/// the demo's language (the web's `demoNote.ts`).
enum DemoNote {
    static let parcel = "1ZDEMO202600000001"

    static let text: [AppLanguage: String] = [
        .en: "Order RM-48213, paid 39 €. Free returns until 24 Dec at round-midnight.example/returns",
        .de: "Bestellung RM-48213, 39 € bezahlt. Kostenlose Rücksendung bis 24. Dez. unter round-midnight.example/returns",
        .fr: "Commande RM-48213, payée 39 €. Retour gratuit jusqu’au 24 déc. sur round-midnight.example/returns",
        .it: "Ordine RM-48213, pagato 39 €. Reso gratuito fino al 24 dic. su round-midnight.example/returns",
        .es: "Pedido RM-48213, pagado 39 €. Devolución gratis hasta el 24 dic. en round-midnight.example/returns",
        .pt: "Encomenda RM-48213, paga 39 €. Devolução grátis até 24 dez. em round-midnight.example/returns",
        .ptBR: "Pedido RM-48213, pago 39 €. Devolução grátis até 24 dez. em round-midnight.example/returns",
        .pl: "Zamówienie RM-48213, zapłacone 39 €. Darmowy zwrot do 24 gru. na round-midnight.example/returns",
        .ru: "Заказ RM-48213, оплачено 39 €. Бесплатный возврат до 24 дек. на round-midnight.example/returns",
        .zh: "订单 RM-48213，已付 39 €。12 月 24 日前可在 round-midnight.example/returns 免费退货",
    ]

    /// The demo's note in another language, when it is the demo's own text; a note someone wrote stays as it is.
    static func text(_ note: String?, in language: AppLanguage) -> String? {
        guard let note, text.values.contains(note) else { return note }
        return text[language] ?? note
    }

    /// The note's two files: a one-page receipt, and a cardboard box on a table.
    static func files() -> [PreparedNoteFile] {
        var files = [PreparedNoteFile(data: Data(receipt().utf8), name: "Receipt.pdf", type: .applicationPdf)]
        if let box = box() { files.append(PreparedNoteFile(data: box, name: "Photo of the box.jpg", type: .imageJpeg, width: 400, height: 400)) }
        return files
    }

    /// A one-page receipt, drawn as a PDF of grey bars.
    private static func receipt() -> String {
        let page = "0.19 0.22 0.18 rg 60 700 150 22 re f 0.78 0.8 0.76 rg 60 650 280 10 re f 60 628 200 10 re f 60 580 280 10 re f"
            + " 60 558 240 10 re f 60 536 260 10 re f 60 514 180 10 re f 0.19 0.22 0.18 rg 200 450 140 14 re f"
        let objects = [
            "<< /Type /Catalog /Pages 2 0 R >>",
            "<< /Type /Pages /Kids [3 0 R] /Count 1 >>",
            "<< /Type /Page /Parent 2 0 R /MediaBox [0 0 400 800] /Contents 4 0 R >>",
            "<< /Length \(page.utf8.count) >>\nstream\n\(page)\nendstream",
        ]
        var pdf = "%PDF-1.4\n"
        var offsets: [Int] = []
        for (index, body) in objects.enumerated() {
            offsets.append(pdf.utf8.count)
            pdf += "\(index + 1) 0 obj\n\(body)\nendobj\n"
        }
        let table = pdf.utf8.count
        pdf += "xref\n0 \(objects.count + 1)\n0000000000 65535 f \n"
        pdf += offsets.map { String(format: "%010d 00000 n \n", $0) }.joined()
        return pdf + "trailer\n<< /Size \(objects.count + 1) /Root 1 0 R >>\nstartxref\n\(table)\n%%EOF\n"
    }

    /// A cardboard box on a table, drawn as the web's demo draws it.
    private static func box() -> Data? {
        let side = 400
        guard let context = CGContext(data: nil, width: side, height: side, bitsPerComponent: 8, bytesPerRow: 0,
                                      space: CGColorSpace(name: CGColorSpace.sRGB) ?? CGColorSpaceCreateDeviceRGB(),
                                      bitmapInfo: CGImageAlphaInfo.noneSkipLast.rawValue) else { return nil }
        // Drawn top down, as the SVG is.
        context.translateBy(x: 0, y: CGFloat(side))
        context.scaleBy(x: 1, y: -1)
        func color(_ hex: UInt32) -> CGColor {
            CGColor(red: CGFloat((hex >> 16) & 0xFF) / 255, green: CGFloat((hex >> 8) & 0xFF) / 255, blue: CGFloat(hex & 0xFF) / 255, alpha: 1)
        }
        func shape(_ points: [(CGFloat, CGFloat)], fill: UInt32, stroke: UInt32? = nil, width: CGFloat = 6) {
            let path = CGMutablePath()
            path.addLines(between: points.map { CGPoint(x: $0.0, y: $0.1) })
            path.closeSubpath()
            context.addPath(path)
            context.setFillColor(color(fill))
            context.fillPath()
            if let stroke {
                context.addPath(path)
                context.setStrokeColor(color(stroke))
                context.setLineWidth(width)
                context.setLineJoin(.round)
                context.strokePath()
            }
        }
        context.setFillColor(color(0xB9A68D))
        context.fill(CGRect(x: 0, y: 0, width: 400, height: 400))
        context.setFillColor(color(0x806047))
        context.fill(CGRect(x: 0, y: 268, width: 400, height: 132))
        context.setStrokeColor(color(0x6C4F39))
        context.setLineWidth(3)
        for y in [300, 338, 376] {
            context.move(to: CGPoint(x: 0, y: y))
            context.addLine(to: CGPoint(x: 400, y: y))
        }
        context.strokePath()
        shape([(200, 96), (330, 154), (200, 212), (70, 154)], fill: 0xE2B47C, stroke: 0x4B3727)
        shape([(70, 154), (200, 212), (200, 330), (70, 272)], fill: 0xD09A5B, stroke: 0x4B3727)
        shape([(330, 154), (200, 212), (200, 330), (330, 272)], fill: 0xBB8447, stroke: 0x4B3727)
        shape([(134, 125), (264, 183), (250, 190), (120, 132)], fill: 0xF3E2C9)
        shape([(232, 244), (294, 216), (294, 250), (232, 278)], fill: 0xFAF6EE, stroke: 0x4B3727, width: 4)
        context.setStrokeColor(color(0x8C7A66))
        context.setLineWidth(4)
        context.setLineCap(.round)
        context.move(to: CGPoint(x: 244, y: 252))
        context.addLine(to: CGPoint(x: 282, y: 235))
        context.move(to: CGPoint(x: 244, y: 264))
        context.addLine(to: CGPoint(x: 270, y: 252))
        context.strokePath()
        guard let image = context.makeImage() else { return nil }
        return NotePreparation.encode(image, as: .jpeg)
    }
}
