import PhotosUI
import QuickLook
import SwiftUI
import UniformTypeIdentifiers
#if os(iOS)
import UIKit
#endif

/// "Add a note" chosen on a card in the list: the parcel's page opens, and its sheet over it.
@MainActor
final class ParcelNoteRequests: ObservableObject {
    static let shared = ParcelNoteRequests()
    @Published var parcelID: UUID?
}

/// Everything a note does besides its words, as the web's note does: chosen files upload at once, and a removal (a file,
/// or the whole note) waits six seconds for Undo, twenty with VoiceOver, before it is sent; at once if the page goes.
@MainActor
final class ParcelNoteModel: ObservableObject {
    enum ToastKind: Equatable {
        case file(String), note, failed
    }

    struct Toast: Equatable, Identifiable {
        let id: Int
        let kind: ToastKind
    }

    /// A file on its way into the note, or one a second try may still get in.
    struct Upload: Identifiable {
        let id: Int
        var name: String
        var pdf: Bool
        var preview: CGImage?
        var problem: NoteFileProblem?
        var size: Int?
    }

    /// A problem said in a line of its own, with no file left to show.
    struct Line: Identifiable, Equatable {
        let id: Int
        let problem: NoteFileProblem
        let name: String
        let size: Int?
    }

    /// A file someone chose: what it is called, and how to read it.
    struct Pick {
        let name: String
        let pdf: Bool
        let read: () async throws -> Data
    }

    enum Saved { case saved, deleted, unchanged }

    @Published private(set) var hidesText = false
    @Published private(set) var hiddenFiles: Set<UUID> = []
    @Published private(set) var uploads: [Upload] = []
    @Published private(set) var lines: [Line] = []
    @Published private(set) var toast: Toast?
    /// VoiceOver is on: Undo waits longer.
    var assisted = false

    private struct Chosen {
        let pick: Pick
        var prepared: PreparedNoteFile?
        var task: Task<Void, Never>?
    }

    private struct Waiting {
        let id: Int
        let kind: ToastKind
        let timer: Task<Void, Never>
        let commit: () async throws -> Void
        let finish: () -> Void
    }

    private var chosen: [Int: Chosen] = [:]
    private var waiting: Waiting?
    private var serial = 0

    var undoDelay: Double { assisted ? ParcelNoteLimits.undoSecondsAssisted : ParcelNoteLimits.undoSeconds }

    func text(of parcel: Parcel) -> String { hidesText ? "" : parcel.note ?? "" }
    func files(of parcel: Parcel) -> [PackageFile] { (parcel.files ?? []).filter { !hiddenFiles.contains($0.id) } }
    /// The card shows: the note has words or files.
    func shown(_ parcel: Parcel) -> Bool {
        text(of: parcel).trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty != nil || !files(of: parcel).isEmpty
    }
    /// Files still on their way.
    var sending: Int { uploads.filter { $0.problem == nil }.count }
    /// Room for more files, counting those on their way.
    func room(in parcel: Parcel) -> Int { ParcelNoteLimits.filesPerParcel - files(of: parcel).count - uploads.count }

    // MARK: Removing

    func removeFile(_ file: PackageFile, of parcel: Parcel, in store: ParcelStore) {
        hiddenFiles.insert(file.id)
        wait(.file(NoteFileName.title(file.name))) {
            try await store.removeNoteFile(parcel, fileID: file.id)
            DeliveryAnalytics.shared.action("parcel-file-remove", .success)
        } finish: { [weak self] in
            self?.hiddenFiles.remove(file.id)
        }
    }

    /// Deletes the words and every file at once, with one Undo for all of it.
    func deleteNote(_ parcel: Parcel, in store: ParcelStore) {
        let ids = (parcel.files ?? []).map(\.id)
        let hadText = parcel.note?.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty != nil
        hidesText = true
        hiddenFiles.formUnion(ids)
        wait(.note) {
            if hadText { try await store.saveNote(parcel, text: nil) }
            for id in ids { try await store.removeNoteFile(parcel, fileID: id) }
            DeliveryAnalytics.shared.action("parcel-note-delete", .success)
        } finish: { [weak self] in
            self?.hidesText = false
            self?.hiddenFiles.subtract(ids)
        }
    }

    func undo() {
        guard let entry = waiting else { return }
        waiting = nil
        entry.timer.cancel()
        entry.finish()
        if toast?.id == entry.id { toast = nil }
    }

    /// Sends what waits for its toast to end, now: the page goes, or the sheet opens again.
    func commitNow() {
        guard let entry = waiting else { return }
        waiting = nil
        entry.timer.cancel()
        if toast?.id == entry.id { toast = nil }
        run(entry)
    }

    private func wait(_ kind: ToastKind, commit: @escaping () async throws -> Void, finish: @escaping () -> Void) {
        commitNow()
        serial += 1
        let id = serial
        let delay = undoDelay
        let timer = Task { [weak self] in
            try? await Task.sleep(for: .seconds(delay))
            guard !Task.isCancelled, let self, let entry = self.waiting, entry.id == id else { return }
            self.waiting = nil
            if self.toast?.id == id { self.toast = nil }
            self.run(entry)
        }
        waiting = Waiting(id: id, kind: kind, timer: timer, commit: commit, finish: finish)
        toast = Toast(id: id, kind: kind)
    }

    /// Once sent, what was hidden is gone from the parcel; if it failed, it comes back, and a note says so.
    private func run(_ entry: Waiting) {
        Task { [weak self] in
            do {
                try await entry.commit()
                entry.finish()
            } catch {
                entry.finish()
                guard let self, entry.kind == .note else { return }
                self.say(.failed)
            }
        }
    }

    private func say(_ kind: ToastKind) {
        serial += 1
        let shown = Toast(id: serial, kind: kind)
        toast = shown
        let delay = undoDelay
        Task { [weak self] in
            try? await Task.sleep(for: .seconds(delay))
            if self?.toast == shown { self?.toast = nil }
        }
    }

    // MARK: Adding

    /// Adds chosen files, each uploading at once, as many as the note has room for.
    func add(_ picks: [Pick], to parcel: Parcel, in store: ParcelStore) {
        lines = []
        var left = room(in: parcel)
        for pick in picks {
            guard left > 0 else {
                refuseFull()
                break
            }
            left -= 1
            serial += 1
            let key = serial
            chosen[key] = Chosen(pick: pick)
            uploads.append(Upload(id: key, name: pick.name, pdf: pick.pdf))
            send(key, to: parcel, in: store)
        }
    }

    func retry(_ key: Int, to parcel: Parcel, in store: ParcelStore) { send(key, to: parcel, in: store) }

    /// Lets go of a file on its way, or of a problem's line.
    func dismiss(_ key: Int) {
        drop(key)
        lines.removeAll { $0.id == key }
    }

    /// Says the note holds as many files as it can, for a chooser that would add none.
    func refuseFull() {
        DeliveryAnalytics.shared.action("parcel-file-add", .error, reason: NoteFileProblem.parcelFull.rawValue)
        serial += 1
        lines.removeAll { $0.problem == .parcelFull }
        lines.append(Line(id: serial, problem: .parcelFull, name: "", size: nil))
    }

    private func send(_ key: Int, to parcel: Parcel, in store: ParcelStore) {
        guard chosen[key] != nil else { return }
        update(key) { $0.problem = nil }
        chosen[key]?.task = Task { [weak self] in
            guard let self else { return }
            do {
                let prepared = try await self.prepared(key)
                try Task.checkCancellation()
                try await store.addNoteFile(parcel, prepared)
                DeliveryAnalytics.shared.action("parcel-file-add", .success)
                self.drop(key)
            } catch {
                if error.isCancellation || self.chosen[key] == nil {
                    self.drop(key)
                    return
                }
                self.fail(key, error)
            }
        }
    }

    private func prepared(_ key: Int) async throws -> PreparedNoteFile {
        guard let entry = chosen[key] else { throw CancellationError() }
        if let prepared = entry.prepared { return prepared }
        let data: Data
        do { data = try await entry.pick.read() } catch { throw NoteFileError(problem: .unreadable) }
        let name = entry.pick.name
        let made = try await Task.detached(priority: .userInitiated) { () throws -> (PreparedNoteFile, CGImage?) in
            let prepared = try NotePreparation.prepare(data, name: name)
            return (prepared, prepared.type == .applicationPdf ? nil : NotePreparation.preview(prepared.data))
        }.value
        chosen[key]?.prepared = made.0
        update(key) { upload in
            upload.name = made.0.name
            upload.pdf = made.0.type == .applicationPdf
            upload.preview = made.1
            upload.size = made.0.data.count
        }
        return made.0
    }

    /// A file another try may get through stays in the row with its retry; any other leaves a line and goes.
    private func fail(_ key: Int, _ error: Error) {
        let problem = Self.problem(of: error)
        DeliveryAnalytics.shared.action("parcel-file-add", .error, reason: problem.rawValue)
        let upload = uploads.first { $0.id == key }
        let size = (error as? NoteFileError)?.size ?? upload?.size
        let name = upload?.name ?? ""
        guard problem.retriable else {
            drop(key)
            lines.removeAll { $0.problem == problem && (problem != .tooBig || $0.name == name) }
            lines.append(Line(id: key, problem: problem, name: name, size: size))
            return
        }
        update(key) { upload in
            upload.problem = problem
            upload.size = size
        }
    }

    private func drop(_ key: Int) {
        chosen[key]?.task?.cancel()
        chosen[key] = nil
        uploads.removeAll { $0.id == key }
    }

    private func update(_ key: Int, _ change: (inout Upload) -> Void) {
        guard let index = uploads.firstIndex(where: { $0.id == key }) else { return }
        change(&uploads[index])
    }

    static func problem(of error: Error) -> NoteFileProblem {
        if let error = error as? NoteFileError { return error.problem }
        if let error = error as? URLError,
           [.notConnectedToInternet, .dataNotAllowed, .networkConnectionLost, .internationalRoamingOff].contains(error.code) {
            return .offline
        }
        return .failed
    }

    // MARK: Words

    /// Saves the words; a note left with no words and no files is deleted instead, with its Undo.
    func save(_ draft: String, of parcel: Parcel, in store: ParcelStore) async throws -> Saved {
        let trimmed = draft.trimmingCharacters(in: .whitespacesAndNewlines)
        let current = text(of: parcel).trimmingCharacters(in: .whitespacesAndNewlines)
        if trimmed.isEmpty, files(of: parcel).isEmpty, sending == 0 {
            if current.isEmpty { return .unchanged }
            deleteNote(parcel, in: store)
            return .deleted
        }
        if trimmed == current { return .unchanged }
        do {
            try await store.saveNote(parcel, text: trimmed.nonEmpty)
            DeliveryAnalytics.shared.action("parcel-note-save", .success)
            return .saved
        } catch {
            DeliveryAnalytics.shared.action("parcel-note-save", .error)
            throw error
        }
    }
}

/// The name people know a file by: without its extension.
enum NoteFileName {
    static func title(_ name: String) -> String {
        let pattern = /\.[A-Za-z0-9]{2,5}$/
        let stripped = name.replacing(pattern, with: "")
        return stripped.isEmpty ? name : stripped
    }
}

extension Localizer {
    /// A size in megabytes as people read it: one decimal under 20, whole above.
    func megabytes(_ bytes: Int) -> String {
        let value = max(0.1, Double(bytes) / 1_048_576)
        let size = value.formatted(.number.precision(.fractionLength(0...(value < 20 ? 1 : 0))).locale(language.locale))
        return text("note.files.megabytes", ["size": size])
    }

    /// The line that says why a file did not join the note.
    func noteFileLine(_ problem: NoteFileProblem, name: String, size: Int?) -> String {
        switch problem {
        case .tooBig: text("note.files.tooBig", ["name": name, "size": megabytes(size ?? ParcelNoteLimits.fileMaxBytes + 1)])
        case .failed: text("note.files.failed", ["name": name])
        case .type: text("note.files.type")
        case .parcelFull: text("note.files.full")
        case .accountFull: text("note.files.accountFull")
        case .deviceFull: text("note.files.deviceFull")
        case .unreadable: text("note.files.unreadable")
        case .offline: text("note.files.offline")
        }
    }

    /// What VoiceOver calls a file's button: "Receipt, PDF", or "Photo".
    func noteFileLabel(_ file: PackageFile) -> String {
        file.isPDF ? text("note.files.open", ["name": NoteFileName.title(file.name), "kind": text("note.files.pdf")])
            : text("note.files.photo")
    }

    /// The Undo toast's words.
    func noteToast(_ kind: ParcelNoteModel.ToastKind) -> String {
        switch kind {
        case .file(let name): text("note.files.removed", ["name": name])
        case .note: text("note.deleted")
        case .failed: text("note.deleteFailed")
        }
    }
}

// MARK: - The list's mark

extension EnvironmentValues {
    /// What the reader's own list is searching for, empty when it isn't. Only the deliveries list sets it: a card
    /// anywhere else marks nothing and shows no note.
    @Entry var listSearch = ""
}

/// A parcel's name with a quiet glyph after its last word when it has a note, a note's for words alone and a paperclip
/// once it has files: held to that word by a no-break space so it never wraps onto a line alone, and drawn in the name's
/// font so it grows with the text. While the list searches, the matches in the name are underlined in Peek's yellow.
enum ParcelNoteMark {
    static let symbol = "doc.plaintext"
    static let filesSymbol = "paperclip"

    static func name(_ name: String, noted: Bool, files: Bool = false, tint: Color, ranges: [Range<Int>] = []) -> Text {
        let shown = marked(name, ranges)
        guard noted else { return shown }
        let mark = Text(Image(systemName: files ? filesSymbol : symbol)).fontWeight(.regular).foregroundStyle(tint.opacity(0.55))
        return Text("\(shown)\u{00A0}\(mark)")
    }

    /// A row's name in the reader's own list: its mark, and the search's matches in the parcel's own name.
    static func name(of parcel: Parcel, _ name: String, search: String, tint: Color) -> Text {
        self.name(name, noted: parcel.hasNote, files: !(parcel.files ?? []).isEmpty, tint: tint,
                  ranges: NoteSearch.ranges(in: parcel.label, query: search))
    }

    /// Text with a search's matches underlined in Peek's yellow, in the text's own colour.
    static func marked(_ text: String, _ ranges: [Range<Int>]) -> Text {
        guard !ranges.isEmpty else { return Text(verbatim: text) }
        var result = AttributedString()
        for piece in NoteSearch.pieces(text, ranges) {
            var run = AttributedString(piece.text)
            if piece.matched { run.swiftUI.underlineStyle = Text.LineStyle(pattern: .solid, color: Brand.accent) }
            result += run
        }
        return Text(result)
    }

    /// What VoiceOver says for a parcel's name: with the note's words a search found, or else ", has a note".
    @MainActor
    static func spokenName(_ name: String, noted: Bool, snippet: NoteSearch.Snippet?, localizer: Localizer) -> String {
        if let snippet { return "\(name), \(localizer.text("note.title")): \(snippet.text)" }
        return noted ? "\(name), \(localizer.text("note.hasNote"))" : name
    }
}

/// The note's words a list search found the parcel by: one quiet line under the name, the matches marked. VoiceOver
/// hears them with the name (`noteMarkLabel`).
struct NoteMatchLine: View {
    let snippet: NoteSearch.Snippet

    var body: some View {
        ParcelNoteMark.marked(snippet.text, snippet.ranges)
            .font(.footnote)
            .foregroundStyle(Brand.secondaryText)
            .lineLimit(1)
            .truncationMode(.tail)
            .accessibilityHidden(true)
    }
}

extension View {
    /// What VoiceOver says for a parcel's name: with ", has a note" when it has one, or the note's words a search found.
    func noteMarkLabel(_ name: String, noted: Bool, snippet: NoteSearch.Snippet? = nil, localizer: Localizer) -> some View {
        accessibilityLabel(ParcelNoteMark.spokenName(name, noted: noted, snippet: snippet, localizer: localizer))
    }
}

// MARK: - The card

/// The note on the parcel's page: "Your note", its pen, its words and its files.
struct ParcelNoteCard: View {
    let text: String
    let files: [PackageFile]
    let fileURL: (String) -> URL?
    let edit: () -> Void
    let open: (PackageFile) -> Void
    var remove: ((PackageFile) -> Void)?

    @EnvironmentObject private var localizer: Localizer

    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            HStack(alignment: .center, spacing: 8) {
                Text(localizer.text("note.title"))
                    .font(.subheadline.weight(.semibold))
                    .foregroundStyle(Brand.secondaryText)
                    .accessibilityAddTraits(.isHeader)
                Spacer(minLength: 8)
                Button(action: edit) {
                    Image(systemName: "pencil")
                        .font(.body)
                        .frame(width: 44, height: 44)
                        .contentShape(Rectangle())
                }
                .buttonStyle(.plain)
                .foregroundStyle(Brand.inkSoft)
                .accessibilityLabel(localizer.text("note.edit"))
                // The pen keeps its 44 pt target without pushing the title down.
                .padding(.vertical, -12)
                .padding(.trailing, -12)
            }
            if let words = text.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty {
                NoteWords(text: words)
            }
            if !files.isEmpty {
                PillFlow(spacing: 10) {
                    ForEach(files, id: \.id) { file in
                        Button { open(file) } label: {
                            NoteThumbnail(file: file, url: fileURL(file.thumbnailURL ?? file.url), side: 60)
                        }
                        .buttonStyle(TactileButtonStyle(scale: 0.96))
                        .accessibilityLabel(localizer.noteFileLabel(file))
                        .contextMenu {
                            if let remove {
                                Button(localizer.text("note.files.removeShort"), systemImage: "trash", role: .destructive) { remove(file) }
                            }
                        }
                    }
                }
            }
        }
        .padding(.horizontal, 18)
        .padding(.vertical, 16)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Brand.paper, in: RoundedRectangle(cornerRadius: 16, style: .continuous))
        .overlay(RoundedRectangle(cornerRadius: 16, style: .continuous).strokeBorder(Color.secondary.opacity(0.3)))
        .accessibilityElement(children: .contain)
        .accessibilityIdentifier("parcel.note")
    }
}

/// The note's words, its web and e-mail addresses links: four lines at the reader's text size, then More.
struct NoteWords: View {
    let text: String

    @EnvironmentObject private var localizer: Localizer
    @State private var open = false
    @State private var fullHeight: CGFloat = 0
    @State private var shownHeight: CGFloat = 0

    private var long: Bool { open || fullHeight > shownHeight + 1 }

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(ParcelNoteText.attributed(text))
                .font(.body)
                .foregroundStyle(Brand.ink)
                .lineLimit(open ? nil : 4)
                .fixedSize(horizontal: false, vertical: true)
                .frame(maxWidth: .infinity, alignment: .leading)
                .onGeometryChange(for: CGFloat.self) { $0.size.height } action: { shownHeight = $0 }
                .background(alignment: .topLeading) {
                    Text(ParcelNoteText.attributed(text))
                        .font(.body)
                        .fixedSize(horizontal: false, vertical: true)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .hidden()
                        .onGeometryChange(for: CGFloat.self) { $0.size.height } action: { fullHeight = $0 }
                        .accessibilityHidden(true)
                }
                .tint(Brand.ink)
                .textSelection(.enabled)
            if long {
                Button(localizer.text(open ? "note.less" : "note.more")) { open.toggle() }
                    .font(.subheadline.weight(.semibold))
                    .foregroundStyle(Brand.ink)
                    .buttonStyle(.plain)
                    .frame(minHeight: 44, alignment: .leading)
                    .contentShape(Rectangle())
                    .accessibilityLabel(localizer.text(open ? "note.less" : "note.moreLabel"))
            }
        }
    }
}

// MARK: - Thumbnails

/// The small pictures of a note's photos, read once per file and kept while the app runs.
@MainActor
enum NotePictures {
    private static let cache = NSCache<NSString, CGImageBox>()

    final class CGImageBox {
        let image: CGImage
        init(_ image: CGImage) { self.image = image }
    }

    static func cached(_ file: PackageFile) -> CGImage? { cache.object(forKey: file.id.uuidString as NSString)?.image }

    static func load(_ file: PackageFile, from url: URL) async -> CGImage? {
        if let image = cached(file) { return image }
        let data: Data?
        if url.isFileURL {
            data = await Task.detached { try? Data(contentsOf: url) }.value
        } else {
            let answer = try? await URLSession.shared.data(from: url)
            data = (answer?.1 as? HTTPURLResponse)?.statusCode == 200 ? answer?.0 : nil
        }
        guard let data, let image = await Task.detached(operation: { NotePreparation.preview(data, side: 256) }).value else { return nil }
        cache.setObject(CGImageBox(image), forKey: file.id.uuidString as NSString)
        return image
    }
}

/// A note's file at the size its place gives it: the photo itself, or a PDF's page.
struct NoteThumbnail: View {
    let file: PackageFile
    let url: URL?
    let side: CGFloat

    @State private var image: CGImage?

    var body: some View {
        let shape = RoundedRectangle(cornerRadius: 12, style: .continuous)
        Group {
            if file.isPDF {
                NotePDFTile(side: side)
            } else if let image = image ?? NotePictures.cached(file) {
                Image(decorative: image, scale: 1).resizable().scaledToFill()
            } else {
                ZStack {
                    Brand.cream
                    Image(systemName: "photo").font(.system(size: side * 0.32)).foregroundStyle(Brand.secondaryText)
                }
            }
        }
        .frame(width: side, height: side)
        .clipShape(shape)
        .overlay(shape.strokeBorder(Color.secondary.opacity(0.25)))
        .contentShape(shape)
        .task(id: url) {
            guard !file.isPDF, image == nil, let url else { return }
            image = await NotePictures.load(file, from: url)
        }
    }
}

/// A PDF, drawn as a page with its badge.
struct NotePDFTile: View {
    let side: CGFloat

    var body: some View {
        ZStack(alignment: .bottomLeading) {
            Brand.cream
            VStack(alignment: .leading, spacing: side * 0.06) {
                ForEach(0..<5, id: \.self) { line in
                    Capsule().fill(Brand.secondaryText.opacity(line == 0 ? 0.8 : 0.35))
                        .frame(width: side * (line == 0 ? 0.28 : line == 4 ? 0.3 : 0.4), height: max(1.5, side * 0.035))
                }
            }
            .padding(side * 0.12)
            .frame(width: side * 0.62, height: side * 0.78, alignment: .topLeading)
            .background(Brand.paper, in: RoundedRectangle(cornerRadius: side * 0.05))
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            Text(verbatim: "PDF")
                .font(.system(size: max(9, side * 0.16), weight: .bold))
                .foregroundStyle(.white)
                .padding(.horizontal, side * 0.07)
                .padding(.vertical, side * 0.02)
                .background(Color(hex: "#4A4A4F"), in: RoundedRectangle(cornerRadius: side * 0.06))
                .padding(side * 0.1)
        }
        .frame(width: side, height: side)
    }
}

// MARK: - Opening a file

/// A note's file for Quick Look: copied under its own name to a folder of its own, from this device or from the
/// address the service signed.
enum NoteFileOpening {
    static func copy(_ file: PackageFile, from url: URL) async throws -> URL {
        let folder = FileManager.default.temporaryDirectory
            .appending(path: "NoteFiles", directoryHint: .isDirectory)
            .appending(path: file.id.uuidString, directoryHint: .isDirectory)
        try FileManager.default.createDirectory(at: folder, withIntermediateDirectories: true)
        let name = NotePreparation.fileName(file.name, type: file.contentType)
        let destination = folder.appending(path: name)
        let data: Data
        if url.isFileURL {
            data = try await Task.detached { try Data(contentsOf: url) }.value
        } else {
            let (body, response) = try await URLSession.shared.data(from: url)
            guard (response as? HTTPURLResponse)?.statusCode == 200 else { throw URLError(.badServerResponse) }
            data = body
        }
        try data.write(to: destination, options: .atomic)
        return destination
    }
}

// MARK: - The sheet

/// The note's sheet: the parcel's name over "Your note", the field, the lock line, the files, and Save note.
struct ParcelNoteSheet: View {
    let parcel: Parcel
    @ObservedObject var model: ParcelNoteModel
    /// Kept on this device, without an account.
    let guest: Bool
    /// Opened on a note that was there: it offers Delete note.
    let existing: Bool
    let open: (PackageFile) -> Void
    let saved: () -> Void
    let deleted: () -> Void

    @EnvironmentObject private var store: ParcelStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dismiss) private var dismiss
    @Environment(\.accessibilityVoiceOverEnabled) private var voiceOver
    @State private var draft: String
    @State private var saving = false
    @State private var failed = false
    @State private var dropping = false
    @State private var pickingPhotos = false
    @State private var photoItems: [PhotosPickerItem] = []
    @State private var importing = false
    @State private var takingPhoto = false
    @State private var contentHeight: CGFloat = 520
    @State private var detent = PresentationDetent.large
    @FocusState private var writing: Bool

    init(parcel: Parcel, model: ParcelNoteModel, guest: Bool, existing: Bool, open: @escaping (PackageFile) -> Void,
         saved: @escaping () -> Void, deleted: @escaping () -> Void) {
        self.parcel = parcel
        self.model = model
        self.guest = guest
        self.existing = existing
        self.open = open
        self.saved = saved
        self.deleted = deleted
        _draft = State(initialValue: model.text(of: parcel))
    }

    private var name: String { parcel.label.nonEmpty ?? localizer.text("common.parcel") }
    private var left: Int { ParcelNoteText.left(draft) }
    private var count: String? {
        left <= ParcelNoteLimits.countFrom ? localizer.text("note.left.many", ["count": left]) : nil
    }
    private var files: [PackageFile] { model.files(of: parcel) }

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 14) {
                heading
                field
                lockLine
                if !files.isEmpty || !model.uploads.isEmpty { fileRow }
                problems
                addButton
                if failed { failure }
                saveButton
                if existing { deleteButton }
            }
            .padding(.horizontal, 22)
            .padding(.top, 24)
            .padding(.bottom, 18)
            .frame(maxWidth: 560)
            .frame(maxWidth: .infinity)
            .onGeometryChange(for: CGFloat.self) { $0.size.height } action: { contentHeight = $0 }
        }
        .scrollBounceBehavior(.basedOnSize)
        .scrollDismissesKeyboard(.interactively)
        // The toast takes room of its own: the buttons under it scroll clear of its glass.
        .safeAreaInset(edge: .bottom, spacing: 0) { fileToast }
        .overlay { if dropping { dropHint } }
        .onDrop(of: [.image, .pdf], isTargeted: $dropping, perform: drop)
        .sheetGround()
        .tint(Brand.ink)
        .environment(\.locale, localizer.language.locale)
        .fittedSheet(height: contentHeight, detent: $detent)
        #if os(macOS)
        .frame(width: 480)
        .frame(minHeight: 460)
        #endif
        .presentationDragIndicator(.visible)
        .presentationBackground(Brand.sheet)
        .interactiveDismissDisabled(saving)
        .photosPicker(isPresented: $pickingPhotos, selection: $photoItems,
                      maxSelectionCount: max(1, model.room(in: parcel)), matching: .images, photoLibrary: .shared())
        .onChange(of: photoItems) { _, items in
            guard !items.isEmpty else { return }
            photoItems = []
            let photo = localizer.text("note.files.photo")
            model.add(items.map { item in
                ParcelNoteModel.Pick(name: photo, pdf: false) {
                    guard let data = try await item.loadTransferable(type: Data.self) else { throw NoteFileError(problem: .unreadable) }
                    return data
                }
            }, to: parcel, in: store)
        }
        .fileImporter(isPresented: $importing, allowedContentTypes: [.pdf, .image], allowsMultipleSelection: true) { result in
            guard case .success(let urls) = result, !urls.isEmpty else { return }
            model.add(urls.map { url in
                ParcelNoteModel.Pick(name: url.lastPathComponent, pdf: url.pathExtension.lowercased() == "pdf") {
                    let allowed = url.startAccessingSecurityScopedResource()
                    defer { if allowed { url.stopAccessingSecurityScopedResource() } }
                    return try Data(contentsOf: url)
                }
            }, to: parcel, in: store)
        }
        #if os(iOS)
        .fullScreenCover(isPresented: $takingPhoto) {
            NoteCamera { data in
                let photo = localizer.text("note.files.photo")
                model.add([ParcelNoteModel.Pick(name: photo, pdf: false) { data }], to: parcel, in: store)
            }
            .ignoresSafeArea()
        }
        #endif
        .onChange(of: draft) { _, typed in
            let kept = ParcelNoteText.clip(typed)
            if kept != typed { draft = kept }
            failed = false
        }
        .onChange(of: writing) { _, writing in
            if writing { detent = .large }
        }
        .onChange(of: voiceOver, initial: true) { _, on in model.assisted = on }
        .task {
            // The keyboard comes up with the cursor after the last word.
            try? await Task.sleep(for: .milliseconds(350))
            writing = true
        }
        .task(id: count) {
            // VoiceOver hears the count once typing pauses, not on every key.
            guard voiceOver, let count else { return }
            try? await Task.sleep(for: .milliseconds(900))
            guard !Task.isCancelled else { return }
            AccessibilityNotification.Announcement(count).post()
        }
    }

    private var heading: some View {
        HStack(alignment: .top, spacing: 16) {
            VStack(alignment: .leading, spacing: 2) {
                Text(name)
                    .font(.footnote)
                    .foregroundStyle(Brand.secondaryText)
                    .lineLimit(2)
                Text(localizer.text("note.title"))
                    .font(.title2.weight(.semibold))
                    .fixedSize(horizontal: false, vertical: true)
                    .accessibilityAddTraits(.isHeader)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            Button { dismiss() } label: {
                Image(systemName: "xmark")
                    .font(.system(size: 13, weight: .semibold))
                    .frame(width: 36, height: 36)
                    .background(Brand.sheetButton, in: Circle())
                    .frame(width: 44, height: 44)
                    .contentShape(Circle())
            }
            .buttonStyle(.plain)
            .keyboardShortcut(.cancelAction)
            .disabled(saving)
            .accessibilityLabel(localizer.text("common.close"))
        }
    }

    private var field: some View {
        let shape = RoundedRectangle(cornerRadius: 14, style: .continuous)
        return TextField(text: $draft, prompt: Text(localizer.text("note.placeholder")).foregroundStyle(Brand.secondaryText),
                         axis: .vertical) {
            Text(localizer.text("note.fieldNamed", ["name": name]))
        }
        .drawnField()
        .font(.body)
        .lineLimit(5...14)
        .focused($writing)
        .padding(.horizontal, 14)
        .padding(.vertical, 12)
        .frame(minHeight: 130, alignment: .topLeading)
        .background(Brand.raised, in: shape)
        .overlay(shape.strokeBorder(writing ? ExperimentalPalette.ochre : Brand.sheetLine(0.6), lineWidth: writing ? 1.5 : 1))
        .contentShape(shape)
        .onTapGesture { writing = true }
        .accessibilityHint(localizer.text(guest ? "note.guestApp" : "note.private"))
    }

    private var lockLine: some View {
        HStack(alignment: .firstTextBaseline, spacing: 8) {
            Image(systemName: guest ? "iphone" : "lock")
                .accessibilityHidden(true)
            Text(localizer.text(guest ? "note.guestApp" : "note.private"))
                .fixedSize(horizontal: false, vertical: true)
                .frame(maxWidth: .infinity, alignment: .leading)
            if let count {
                Text(count)
                    .monospacedDigit()
                    .foregroundStyle(left <= 0 ? Brand.warning : Brand.secondaryText)
                    .accessibilityHidden(true)
            }
        }
        .font(.footnote)
        .foregroundStyle(Brand.secondaryText)
        .padding(.horizontal, 2)
    }

    private var fileRow: some View {
        PillFlow(spacing: 12) {
            ForEach(files, id: \.id) { file in
                Button { open(file) } label: {
                    NoteThumbnail(file: file, url: store.noteFileURL(file.thumbnailURL ?? file.url), side: 64)
                }
                .buttonStyle(TactileButtonStyle(scale: 0.96))
                .accessibilityLabel(localizer.noteFileLabel(file))
                .overlay(alignment: .topTrailing) {
                    removeBadge(localizer.text("note.files.remove", ["name": NoteFileName.title(file.name)])) {
                        model.removeFile(file, of: parcel, in: store)
                    }
                }
            }
            ForEach(model.uploads) { upload in
                uploadTile(upload)
            }
        }
        .padding(.top, 6)
        .padding(.trailing, 8)
    }

    private func uploadTile(_ upload: ParcelNoteModel.Upload) -> some View {
        let shape = RoundedRectangle(cornerRadius: 12, style: .continuous)
        return ZStack {
            if upload.pdf || upload.preview == nil {
                if upload.pdf { NotePDFTile(side: 64) } else { Brand.cream }
            } else if let preview = upload.preview {
                Image(decorative: preview, scale: 1).resizable().scaledToFill()
            }
            Color.black.opacity(upload.problem == nil ? 0.25 : 0.4)
            if let problem = upload.problem {
                Button { model.retry(upload.id, to: parcel, in: store) } label: {
                    Image(systemName: problem == .offline ? "wifi.slash" : "arrow.clockwise")
                        .font(.system(size: 16, weight: .semibold))
                        .foregroundStyle(.white)
                        .frame(width: 44, height: 44)
                        .contentShape(Rectangle())
                }
                .buttonStyle(.plain)
                .accessibilityLabel("\(localizer.text("note.files.retry")): \(upload.name)")
            } else {
                ProgressView()
                    .tint(.white)
                    .accessibilityLabel(localizer.text("note.files.uploading", ["name": upload.name]))
            }
        }
        .frame(width: 64, height: 64)
        .clipShape(shape)
        .overlay(alignment: .topTrailing) {
            removeBadge(localizer.text("note.files.remove", ["name": NoteFileName.title(upload.name)])) {
                model.dismiss(upload.id)
            }
        }
    }

    /// The × on a file: a small round mark, with a target of 44 pt.
    private func removeBadge(_ label: String, action: @escaping () -> Void) -> some View {
        Button(action: action) {
            Image(systemName: "xmark")
                .font(.system(size: 10, weight: .bold))
                .foregroundStyle(Brand.paper)
                .frame(width: 22, height: 22)
                .background(Brand.ink, in: Circle())
                .overlay(Circle().strokeBorder(Brand.paper, lineWidth: 1.5))
                .frame(width: 44, height: 44)
                .contentShape(Rectangle())
        }
        .buttonStyle(.plain)
        .offset(x: 16, y: -16)
        .accessibilityLabel(label)
    }

    @ViewBuilder private var problems: some View {
        let stopped = model.uploads.filter { $0.problem != nil }
        let offline = stopped.contains { $0.problem == .offline }
        let failed = stopped.filter { $0.problem != .offline }
        if !model.lines.isEmpty || !stopped.isEmpty {
            VStack(alignment: .leading, spacing: 8) {
                ForEach(model.lines) { line in
                    problemLine(localizer.noteFileLine(line.problem, name: line.name, size: line.size), symbol: "exclamationmark.circle")
                }
                if offline {
                    problemLine(localizer.noteFileLine(.offline, name: "", size: nil), symbol: "wifi.slash")
                }
                ForEach(failed) { upload in
                    HStack(alignment: .firstTextBaseline, spacing: 10) {
                        problemLine(localizer.noteFileLine(upload.problem ?? .failed, name: upload.name, size: upload.size),
                                    symbol: "exclamationmark.circle")
                        Button(localizer.text("note.files.retry")) { model.retry(upload.id, to: parcel, in: store) }
                            .font(.footnote.weight(.semibold))
                            .foregroundStyle(Brand.ink)
                            .buttonStyle(.plain)
                            .frame(minHeight: 44)
                    }
                }
            }
        }
    }

    private func problemLine(_ text: String, symbol: String) -> some View {
        HStack(alignment: .firstTextBaseline, spacing: 8) {
            Image(systemName: symbol).accessibilityHidden(true)
            Text(text).fixedSize(horizontal: false, vertical: true)
            Spacer(minLength: 0)
        }
        .font(.footnote)
        .foregroundStyle(Brand.warning)
        .onAppear { AccessibilityNotification.Announcement(text).post() }
    }

    private var addButton: some View {
        Menu {
            #if os(iOS)
            if UIImagePickerController.isSourceTypeAvailable(.camera) {
                Button(localizer.text("note.files.camera"), systemImage: "camera") { choose { takingPhoto = true } }
            }
            #endif
            Button(localizer.text("note.files.photos"), systemImage: "photo.on.rectangle") { choose { pickingPhotos = true } }
            Button(localizer.text("note.files.documents"), systemImage: "folder") { choose { importing = true } }
        } label: {
            Label(localizer.text("note.files.add"), systemImage: "paperclip")
                .font(.body.weight(.medium))
                .foregroundStyle(Brand.ink)
                .padding(.horizontal, 18)
                .padding(.vertical, 12)
                .frame(minHeight: 48)
                .background(Brand.raised, in: Capsule())
                .overlay(Capsule().strokeBorder(Brand.sheetLine(0.6)))
                .contentShape(Capsule())
        }
        .menuIndicator(.hidden)
        .buttonStyle(.plain)
        .fixedSize()
        .accessibilityIdentifier("parcel.note.add")
    }

    /// A chooser opens while the note has room for another file; otherwise the line says it is full.
    private func choose(_ open: () -> Void) {
        guard model.room(in: parcel) > 0 else {
            model.refuseFull()
            return
        }
        open()
    }

    private var failure: some View {
        HStack(alignment: .firstTextBaseline, spacing: 8) {
            Image(systemName: "exclamationmark.circle").accessibilityHidden(true)
            Text(localizer.text("note.failed")).fixedSize(horizontal: false, vertical: true)
            Spacer(minLength: 0)
        }
        .font(.footnote)
        .foregroundStyle(Brand.warning)
        .padding(12)
        .background(Brand.warningSoft.opacity(0.6), in: RoundedRectangle(cornerRadius: 12, style: .continuous))
        .onAppear { AccessibilityNotification.Announcement(localizer.text("note.failed")).post() }
    }

    private var saveButton: some View {
        Button(action: save) {
            HStack(spacing: 9) {
                if saving { ProgressView().tint(Brand.onAccent) }
                Text(localizer.text("note.save")).multilineTextAlignment(.center)
            }
            .font(.headline)
            .foregroundStyle(Brand.onAccent)
            .padding(.horizontal, 20)
            .padding(.vertical, 13)
            .frame(maxWidth: .infinity, minHeight: 50)
            .background(Brand.accent, in: RoundedRectangle(cornerRadius: 16, style: .continuous))
            .contentShape(RoundedRectangle(cornerRadius: 16, style: .continuous))
        }
        .buttonStyle(TactileButtonStyle())
        .keyboardShortcut(.return, modifiers: .command)
        .disabled(saving)
        .accessibilityIdentifier("parcel.note.save")
    }

    private var deleteButton: some View {
        Button(role: .destructive) {
            model.deleteNote(parcel, in: store)
            deleted()
        } label: {
            Text(localizer.text("note.delete"))
                .font(.body.weight(.medium))
                .foregroundStyle(Brand.warning)
                .frame(maxWidth: .infinity, minHeight: 44)
                .contentShape(Rectangle())
        }
        .buttonStyle(.plain)
        .disabled(saving)
    }

    @ViewBuilder private var fileToast: some View {
        if let toast = model.toast, case .file = toast.kind {
            InlineToast(text: localizer.noteToast(toast.kind), button: localizer.text("app.undo"), symbol: "trash.fill",
                        tint: ExperimentalPalette.ochre) { model.undo() }
                .padding(.horizontal, 16)
                .padding(.bottom, 10)
                .transition(.move(edge: .bottom).combined(with: .opacity))
                .onAppear { AccessibilityNotification.Announcement(localizer.noteToast(toast.kind)).post() }
        }
    }

    private var dropHint: some View {
        let shape = RoundedRectangle(cornerRadius: 20, style: .continuous)
        return VStack(spacing: 10) {
            Image(systemName: "paperclip").font(.title)
            Text(localizer.text("note.files.drop")).font(.headline)
        }
        .foregroundStyle(Brand.ink)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Brand.yellowSoft.opacity(0.92), in: shape)
        .overlay(shape.strokeBorder(ExperimentalPalette.ochre, style: StrokeStyle(lineWidth: 2, dash: [8, 6])))
        .padding(12)
        .allowsHitTesting(false)
        .accessibilityHidden(true)
    }

    private func drop(_ providers: [NSItemProvider]) -> Bool {
        let picks = providers.compactMap { provider -> ParcelNoteModel.Pick? in
            let pdf = provider.hasItemConformingToTypeIdentifier(UTType.pdf.identifier)
            guard pdf || provider.hasItemConformingToTypeIdentifier(UTType.image.identifier) else { return nil }
            let type = pdf ? UTType.pdf : .image
            let name = provider.suggestedName.map { pdf && !$0.lowercased().hasSuffix(".pdf") ? "\($0).pdf" : $0 }
                ?? localizer.text(pdf ? "note.files.pdf" : "note.files.photo")
            return ParcelNoteModel.Pick(name: name, pdf: pdf) {
                try await withCheckedThrowingContinuation { continuation in
                    _ = provider.loadDataRepresentation(for: type) { data, error in
                        if let data { continuation.resume(returning: data) } else {
                            continuation.resume(throwing: error ?? NoteFileError(problem: .unreadable))
                        }
                    }
                }
            }
        }
        guard !picks.isEmpty else { return false }
        model.add(picks, to: parcel, in: store)
        return true
    }

    private func save() {
        guard !saving else { return }
        saving = true
        failed = false
        Task {
            do {
                let result = try await model.save(draft, of: parcel, in: store)
                saving = false
                if result == .deleted { deleted() } else { saved() }
            } catch {
                saving = false
                failed = true
            }
        }
    }
}

#if os(iOS)
/// The camera, for a photo of what came or of its label.
private struct NoteCamera: UIViewControllerRepresentable {
    let taken: (Data) -> Void
    @Environment(\.dismiss) private var dismiss

    func makeUIViewController(context: Context) -> UIImagePickerController {
        let picker = UIImagePickerController()
        picker.sourceType = .camera
        picker.delegate = context.coordinator
        return picker
    }

    func updateUIViewController(_ picker: UIImagePickerController, context: Context) {}

    func makeCoordinator() -> Coordinator { Coordinator(self) }

    final class Coordinator: NSObject, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
        let parent: NoteCamera
        init(_ parent: NoteCamera) { self.parent = parent }

        func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]) {
            if let image = info[.originalImage] as? UIImage, let data = image.jpegData(compressionQuality: ParcelNoteLimits.photoQuality) {
                parent.taken(data)
            }
            parent.dismiss()
        }

        func imagePickerControllerDidCancel(_ picker: UIImagePickerController) { parent.dismiss() }
    }
}
#endif
