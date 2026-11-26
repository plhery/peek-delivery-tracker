import ImageIO
import SwiftUI
import UniformTypeIdentifiers
import XCTest
@testable import PeekDeliveryTracker

/// A parcel's private note: its limit, links, files, the list's search, the demo's note and the counts.
@MainActor
final class ParcelNoteTests: XCTestCase {
    private var roots: [URL] = []

    override func tearDown() {
        for root in roots { try? FileManager.default.removeItem(at: root) }
        roots = []
        super.tearDown()
    }

    /// A file store of its own in a folder the test deletes.
    private func files(_ space: String = "test", freeSpace: (() -> Int64?)? = nil, root: URL? = nil) -> LocalNoteFiles {
        let root = root ?? FileManager.default.temporaryDirectory.appending(path: "NoteFilesTests-\(UUID().uuidString)", directoryHint: .isDirectory)
        roots.append(root)
        return LocalNoteFiles(space: "\(space)-\(root.lastPathComponent)", root: root, freeSpace: freeSpace)
    }

    private func parcel(label: String = "Records", note: String? = nil, files: [PackageFile]? = nil) -> Parcel {
        Parcel(id: UUID(), trackingNumber: "TESTNOTE0001", label: label, note: note, files: files, carrier: .dhl,
               createdAt: "2026-11-01T08:00:00Z", syncStatus: .ok, notificationsMuted: false)
    }

    private func pdf(_ count: Int = 600) -> PreparedNoteFile {
        var data = Data("%PDF-1.4\n".utf8)
        data.append(Data(repeating: 0x20, count: count))
        return PreparedNoteFile(data: data, name: "Receipt.pdf", type: .applicationPdf)
    }

    private func image(width: Int, height: Int, as type: UTType) throws -> Data {
        let context = try XCTUnwrap(CGContext(data: nil, width: width, height: height, bitsPerComponent: 8, bytesPerRow: 0,
                                              space: CGColorSpaceCreateDeviceRGB(), bitmapInfo: CGImageAlphaInfo.premultipliedLast.rawValue))
        context.setFillColor(CGColor(red: 0.8, green: 0.6, blue: 0.3, alpha: 1))
        context.fill(CGRect(x: 0, y: 0, width: width, height: height))
        let picture = try XCTUnwrap(context.makeImage())
        return try XCTUnwrap(NotePreparation.encode(picture, as: type))
    }

    // MARK: - The words

    func testTheLimitCountsUnicodeCodePointsAsTheServerDoes() {
        // "é" written as one code point and as "e" with its accent: one character, one and two code points.
        XCTAssertEqual(ParcelNoteText.length("\u{E9}"), 1)
        XCTAssertEqual(ParcelNoteText.length("e\u{301}"), 2)
        // A thumbs-up with a skin tone is two code points; a family of four, seven.
        XCTAssertEqual(ParcelNoteText.length("👍🏽"), 2)
        XCTAssertEqual(ParcelNoteText.length("👨‍👩‍👧‍👦"), 7)
        XCTAssertEqual(ParcelNoteText.left(String(repeating: "a", count: 960)), 40)

        // Cut to the limit, never through a character: a family that doesn't fit whole goes.
        let full = String(repeating: "a", count: 995) + "👨‍👩‍👧‍👦"
        XCTAssertEqual(ParcelNoteText.clip(full), String(repeating: "a", count: 995))
        XCTAssertEqual(ParcelNoteText.length(ParcelNoteText.clip(String(repeating: "ß", count: 1_200))), 1_000)
        XCTAssertEqual(ParcelNoteText.clip("short"), "short")
    }

    func testWebAndEmailAddressesBecomeLinksAndNothingElse() {
        let pieces = ParcelNoteText.pieces("Order RM-48213. Returns at round-midnight.example/returns, or write to help@shop.example.")
        XCTAssertEqual(pieces.compactMap(\.link?.absoluteString), ["https://round-midnight.example/returns", "mailto:help@shop.example"])
        // The comma and the full stop after an address stay text.
        XCTAssertEqual(pieces.map(\.text).joined(), "Order RM-48213. Returns at round-midnight.example/returns, or write to help@shop.example.")
        XCTAssertEqual(pieces.first { $0.link != nil }?.text, "round-midnight.example/returns")

        XCTAssertEqual(ParcelNoteText.pieces("See https://example.com/a?b=1 (tracking)").compactMap(\.link?.absoluteString), ["https://example.com/a?b=1"])
        XCTAssertEqual(ParcelNoteText.pieces("www.shop.zz").compactMap(\.link?.absoluteString), ["https://www.shop.zz"])
        // A version number, a file's name and a sentence that runs on are no addresses.
        XCTAssertEqual(ParcelNoteText.pieces("Firmware v1.2, photo.jpg, done.Next").compactMap(\.link), [])
        XCTAssertEqual(ParcelNoteText.pieces("plain words").count, 1)
    }

    func testANoteIsWordsOrAFile() {
        XCTAssertFalse(parcel().hasNote)
        XCTAssertFalse(parcel(note: "  \n ").hasNote)
        XCTAssertTrue(parcel(note: "Door code 4521").hasNote)
        let file = PackageFile(id: UUID(), name: "Receipt.pdf", contentType: .applicationPdf, size: 10, createdAt: "2026-11-01T08:00:00Z",
                               url: "/api/files/x", expiresAt: "2026-11-01T09:00:00Z")
        XCTAssertTrue(parcel(files: [file]).hasNote)
        XCTAssertTrue(parcel(files: [file]).files?.first?.isPDF == true)
    }

    func testTheListsSearchFindsTheNotesWordsAndItsFilesNames() {
        let file = PackageFile(id: UUID(), name: "Return label.pdf", contentType: .applicationPdf, size: 10, createdAt: "2026-11-01T08:00:00Z",
                               url: "/api/files/x", expiresAt: "2026-11-01T09:00:00Z")
        let noted = parcel(label: "Records", note: "Door code 4521, leave with the neighbour")
        let filed = parcel(label: "Shoes", files: [file])
        let plain = parcel(label: "Lamp")
        func found(_ query: String) -> [String] {
            ParcelOrganizer.visible([noted, filed, plain], query: query, status: .all, carrier: nil, sort: .newest).map(\.label)
        }
        XCTAssertEqual(found("neighbour"), ["Records"])
        XCTAssertEqual(found("DOOR CODE"), ["Records"])
        XCTAssertEqual(found("return label"), ["Shoes"])
        XCTAssertEqual(found("lamp"), ["Lamp"])
    }

    // MARK: - What is sent

    func testTheNotesChangeSendsNullToDeleteAndTrimmedWordsOtherwise() throws {
        let deleting = try JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode(NoteChange(note: nil))) as? [String: Any]
        XCTAssertEqual(deleting?.keys.sorted(), ["note"])
        XCTAssertTrue(deleting?["note"] is NSNull)
        let blank = try JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode(NoteChange(note: "  \n"))) as? [String: Any]
        XCTAssertTrue(blank?["note"] is NSNull)
        let words = try JSONSerialization.jsonObject(with: JSONEncoder.deliveryTracker.encode(NoteChange(note: " Door code 4521 \n"))) as? [String: Any]
        XCTAssertEqual(words?["note"] as? String, "Door code 4521")
    }

    func testAFilesNameTravelsPercentEncodedAsTheWebEncodesIt() {
        XCTAssertEqual(NoteChange.headerName("Reçu n°1 (copy).pdf"), "Re%C3%A7u%20n%C2%B01%20(copy).pdf")
        XCTAssertEqual(NoteChange.headerName("收据.jpg"), "%E6%94%B6%E6%8D%AE.jpg")
        XCTAssertEqual(NoteChange.headerName("a/b?c=d&e.png"), "a%2Fb%3Fc%3Dd%26e.png")
    }

    func testARefusedUploadSaysWhyByItsCodeOrElseItsStatus() {
        XCTAssertEqual(NoteFileProblem.upload(status: 413, code: .fileTooBig), .tooBig)
        XCTAssertEqual(NoteFileProblem.upload(status: 415, code: .fileType), .type)
        XCTAssertEqual(NoteFileProblem.upload(status: 409, code: .parcelFilesFull), .parcelFull)
        XCTAssertEqual(NoteFileProblem.upload(status: 409, code: .accountFilesFull), .accountFull)
        XCTAssertEqual(NoteFileProblem.upload(status: 413, code: nil), .tooBig)
        XCTAssertEqual(NoteFileProblem.upload(status: 415, code: nil), .type)
        XCTAssertEqual(NoteFileProblem.upload(status: 500, code: nil), .failed)
        XCTAssertTrue(NoteFileProblem.failed.retriable)
        XCTAssertTrue(NoteFileProblem.offline.retriable)
        XCTAssertFalse(NoteFileProblem.tooBig.retriable)
    }

    func testANoteAloneIsNoRenameForTheCountsAndAFileProblemSaysWhy() throws {
        let catalog = try XCTUnwrap(AnalyticsCatalog.bundled)
        let path = "/api/packages/\(UUID().uuidString)"
        XCTAssertNil(catalog.event(path: path, method: "PATCH", body: try JSONEncoder.deliveryTracker.encode(NoteChange(note: "Door code"))))
        XCTAssertEqual(catalog.event(path: path, method: "PATCH", body: Data(#"{"label":"Records"}"#.utf8)), "parcel-rename")
        XCTAssertNil(catalog.event(path: "\(path)/files", method: "POST", body: nil))
        XCTAssertTrue(Set(catalog.actions).isSuperset(of: ["parcel-note-save", "parcel-note-delete", "parcel-file-add", "parcel-file-remove"]))

        let configuration = DeliveryAnalytics.Configuration(endpoint: URL(string: "https://counts.test/api/send")!, hostname: "peek.test",
                                                            iosWebsite: UUID().uuidString)
        let payload = DeliveryAnalytics.payload(DeliveryAnalytics.Event(screen: "parcel", name: "parcel-file-add", outcome: .error,
                                                                        mode: .account, reason: NoteFileProblem.tooBig.rawValue),
                                                configuration: configuration, platform: .iphone)
        let data = try XCTUnwrap(payload["data"] as? [String: String])
        XCTAssertEqual(data["reason"], "too-big")
        XCTAssertEqual(data["outcome"], "error")
    }

    // MARK: - Files

    func testAFilesNameKeepsWithin120CharactersAndFollowsItsType() {
        XCTAssertEqual(NotePreparation.fileName("IMG_0001.HEIC", type: .imageJpeg), "IMG_0001.jpg")
        XCTAssertEqual(NotePreparation.fileName("photo.jpeg", type: .imageJpeg), "photo.jpeg")
        XCTAssertEqual(NotePreparation.fileName("Return label.PDF", type: .applicationPdf), "Return label.pdf")
        XCTAssertEqual(NotePreparation.fileName("a/b\u{0}c.png", type: .imagePng), "a b c.png")
        XCTAssertEqual(NotePreparation.fileName("", type: .applicationPdf), "File.pdf")
        let long = NotePreparation.fileName(String(repeating: "é", count: 200) + ".pdf", type: .applicationPdf)
        XCTAssertEqual(long.unicodeScalars.count, 120)
        XCTAssertTrue(long.hasSuffix(".pdf"))
    }

    func testAPDFGoesAsItIsAndAnythingElseThatIsNoPhotoIsRefused() throws {
        let receipt = pdf()
        XCTAssertEqual(try NotePreparation.prepare(receipt.data, name: "receipt.PDF"), PreparedNoteFile(data: receipt.data, name: "receipt.pdf", type: .applicationPdf))
        XCTAssertThrowsError(try NotePreparation.prepare(Data("just text".utf8), name: "notes.txt")) { error in
            XCTAssertEqual((error as? NoteFileError)?.problem, .type)
        }
        var big = Data("%PDF-1.4\n".utf8)
        big.append(Data(count: ParcelNoteLimits.fileMaxBytes))
        XCTAssertThrowsError(try NotePreparation.prepare(big, name: "big.pdf")) { error in
            XCTAssertEqual(error as? NoteFileError, NoteFileError(problem: .tooBig, size: big.count))
        }
    }

    func testAPhotoShrinksToFit2560PixelsAndAScreenshotStaysPNG() throws {
        let small = try image(width: 800, height: 600, as: .jpeg)
        let kept = try NotePreparation.prepare(small, name: "box.jpg")
        XCTAssertEqual(kept.data, small)
        XCTAssertEqual([kept.width, kept.height], [800, 600])

        let large = try NotePreparation.prepare(try image(width: 4_000, height: 3_000, as: .jpeg), name: "large.jpg")
        XCTAssertEqual(large.type, .imageJpeg)
        XCTAssertEqual([large.width, large.height], [2_560, 1_920])

        let screenshot = try NotePreparation.prepare(try image(width: 3_000, height: 1_000, as: .png), name: "Screenshot.png")
        XCTAssertEqual(screenshot.type, .imagePng)
        XCTAssertEqual(screenshot.name, "Screenshot.png")
        XCTAssertEqual(screenshot.width, 2_560)
    }

    func testAHEICPhotoBecomesAJPEGOnTheDevice() throws {
        let heic: Data
        do { heic = try image(width: 1_200, height: 900, as: .heic) } catch { throw XCTSkip("This simulator writes no HEIC") }
        let prepared = try NotePreparation.prepare(heic, name: "IMG_0042.HEIC")
        XCTAssertEqual(prepared.type, .imageJpeg)
        XCTAssertEqual(prepared.name, "IMG_0042.jpg")
        XCTAssertTrue(prepared.data.starts(with: [0xFF, 0xD8]))
        XCTAssertEqual([prepared.width, prepared.height], [1_200, 900])
    }

    func testTheDevicesFilesKeepTheLimitsOfAnAccount() throws {
        let store = files()
        for index in 0..<5 { try store.add(PreparedNoteFile(data: pdf().data, name: "Receipt \(index).pdf", type: .applicationPdf), to: "link-a") }
        XCTAssertThrowsError(try store.add(pdf(), to: "link-a")) { XCTAssertEqual(($0 as? NoteFileError)?.problem, .parcelFull) }
        // Another parcel has room of its own.
        XCTAssertNoThrow(try store.add(pdf(), to: "link-b"))

        var tooBig = pdf()
        tooBig.data.append(Data(count: ParcelNoteLimits.fileMaxBytes))
        XCTAssertThrowsError(try store.add(tooBig, to: "link-b")) { XCTAssertEqual(($0 as? NoteFileError)?.problem, .tooBig) }

        // A device short of room says so.
        let full = files(freeSpace: { 1_000 })
        XCTAssertThrowsError(try full.add(pdf(), to: "link-a")) { XCTAssertEqual(($0 as? NoteFileError)?.problem, .deviceFull) }
    }

    func testTheDevicesFilesSurviveARelaunchAndForgettingAParcelDeletesThem() throws {
        let root = FileManager.default.temporaryDirectory.appending(path: "NoteFilesTests-\(UUID().uuidString)", directoryHint: .isDirectory)
        let store = files("relaunch", root: root)
        let receipt = try store.add(pdf(), to: "link-a", now: Date(timeIntervalSince1970: 1_000))
        let photo = try store.add(PreparedNoteFile(data: try image(width: 40, height: 40, as: .jpeg), name: "Box.jpg", type: .imageJpeg,
                                                   width: 40, height: 40), to: "link-a", now: Date(timeIntervalSince1970: 2_000))
        try store.add(pdf(), to: "link-b")

        let again = LocalNoteFiles(space: store.space, root: root)
        XCTAssertEqual(again.of("link-a").map(\.id), [receipt.id, photo.id])
        XCTAssertEqual(again.data(of: receipt.id), pdf().data)
        // Each file has an address on this device, which opens wherever the app's container is now.
        let listed = try XCTUnwrap(again.packageFiles(of: "link-a"))
        XCTAssertNil(listed[0].thumbnailURL)
        XCTAssertEqual(listed[1].thumbnailURL, listed[1].url)
        let address = try XCTUnwrap(LocalNoteFiles.resolve(listed[0].url, apiBase: URL(string: "https://peek.test")!))
        XCTAssertEqual(try Data(contentsOf: address), pdf().data)
        XCTAssertEqual(LocalNoteFiles.resolve("/api/files/x?v=thumb", apiBase: URL(string: "https://peek.test")!)?.absoluteString,
                       "https://peek.test/api/files/x?v=thumb")
        XCTAssertNil(LocalNoteFiles.resolve("\(LocalNoteFiles.scheme):\(store.space)/../index.json", apiBase: URL(string: "https://peek.test")!))

        again.forget("link-a")
        XCTAssertEqual(again.of("link-a"), [])
        XCTAssertFalse(FileManager.default.fileExists(atPath: address.path))
        XCTAssertEqual(LocalNoteFiles(space: store.space, root: root).all.map(\.key), ["link-b"])
    }

    func testFilesHandedOverToAnAccountStayUntilTheyArriveEvenWhenTheLinkIsForgotten() throws {
        let store = files()
        try store.add(pdf(), to: "link-a")
        let packageID = UUID()
        XCTAssertEqual(store.handOver("link-a", to: packageID), 1)
        XCTAssertEqual(store.of("link-a"), [])
        XCTAssertEqual(store.kept.map(\.packageID), [packageID])
        store.forget("link-a")
        store.forgetAll()
        XCTAssertEqual(store.kept.count, 1)
        store.remove(Set(store.kept.map(\.id)))
        XCTAssertEqual(store.all, [])
    }

    // MARK: - The demo

    func testTheDemosRecordCarriesANoteWithAReceiptAndAPhotoInTheDemosLanguage() throws {
        let suite = "ParcelNoteTests.\(UUID().uuidString)"
        let defaults = try XCTUnwrap(UserDefaults(suiteName: suite))
        defer { defaults.removePersistentDomain(forName: suite) }
        var language = AppLanguage.en
        let demo = DemoRepository(defaults: defaults, language: { language }, noteFiles: files("demo"))

        let noted = try XCTUnwrap(demo.list().first { $0.trackingNumber == DemoNote.parcel })
        XCTAssertEqual(noted.note, DemoNote.text[.en])
        XCTAssertEqual(noted.files?.map(\.name), ["Receipt.pdf", "Photo of the box.jpg"])
        XCTAssertEqual(demo.list().filter(\.hasNote).count, 1)

        language = .fr
        XCTAssertEqual(demo.list().first { $0.id == noted.id }?.note, DemoNote.text[.fr])

        // Changed, it stays as written; a file removed is gone, and the demo's reset brings them all back.
        let changed = try demo.setNote(id: noted.id, note: "  Gift for Sam  ")
        XCTAssertEqual(changed.note, "Gift for Sam")
        let fewer = try demo.removeFile(id: noted.id, fileID: try XCTUnwrap(noted.files?.first?.id))
        XCTAssertEqual(fewer.files?.map(\.name), ["Photo of the box.jpg"])
        let more = try demo.addFile(id: noted.id, pdf())
        XCTAssertEqual(more.files?.count, 2)
        XCTAssertNil(try demo.setNote(id: noted.id, note: " ").note)

        demo.reset()
        language = .en
        let reset = try XCTUnwrap(demo.list().first { $0.trackingNumber == DemoNote.parcel })
        XCTAssertEqual(reset.note, DemoNote.text[.en])
        XCTAssertEqual(reset.files?.map(\.name), ["Receipt.pdf", "Photo of the box.jpg"])
        XCTAssertEqual(demo.noteFiles.all.count, 2)
    }

    func testTheDemosReceiptIsAPDFAndItsPhotoAJPEG() throws {
        let files = DemoNote.files()
        XCTAssertEqual(files.map(\.type), [.applicationPdf, .imageJpeg])
        XCTAssertNoThrow(try NotePreparation.prepare(files[0].data, name: files[0].name))
        let photo = try NotePreparation.prepare(files[1].data, name: files[1].name)
        XCTAssertEqual([photo.width, photo.height], [400, 400])
    }

    // MARK: - Drawn

    func testTheNoteMenuItemSitsRightUnderTheNameAndReadsEditOnceThereIsANote() {
        let plain = parcel(label: "Lamp")
        let noted = parcel(label: "Records", note: "Door code 4521")
        XCTAssertEqual(ParcelMenuItem.menu(for: plain, onCard: false, notes: true)[0].prefix(2), [.rename, .note(edit: false)])
        XCTAssertEqual(ParcelMenuItem.menu(for: noted, onCard: true, notes: true)[1].prefix(2), [.rename, .note(edit: true)])
        XCTAssertEqual(ParcelMenuItem.menu(for: noted, onCard: false, frozen: true, notes: true)[0].prefix(2), [.rename, .note(edit: true)])
        XCTAssertFalse(ParcelMenuItem.menu(for: noted, onCard: false).joined().contains(.note(edit: true)))
        XCTAssertEqual(ParcelMenuItem.note(edit: false).titleKey, "note.add")
        XCTAssertEqual(ParcelMenuItem.note(edit: true).titleKey, "note.edit")
        XCTAssertNil(ParcelMenuItem.note(edit: true).shortcut)
        XCTAssertTrue(ParcelMenu.items(for: noted, reader: false, notes: true).joined().contains(.note(edit: true)))
    }

    func testAFilesProblemsAndSizesReadAsTheWebSaysThem() {
        let localizer = Localizer()
        let previous = localizer.language
        defer { localizer.language = previous }
        localizer.language = .en
        XCTAssertEqual(localizer.noteFileLine(.tooBig, name: "Return label.pdf", size: 24 * 1_048_576),
                       "Return label.pdf is 24 MB. Files can be up to 10 MB.")
        XCTAssertEqual(localizer.megabytes(1_572_864), "1.5 MB")
        XCTAssertEqual(localizer.noteFileLine(.failed, name: "Receipt.jpg", size: nil), "Couldn’t add Receipt.jpg.")
        XCTAssertEqual(localizer.noteFileLine(.parcelFull, name: "", size: nil), "A parcel holds up to 5 photos or files.")
        XCTAssertEqual(NoteFileName.title("Receipt.pdf"), "Receipt")
        XCTAssertEqual(NoteFileName.title(".pdf"), ".pdf")
        let receipt = PackageFile(id: UUID(), name: "Receipt.pdf", contentType: .applicationPdf, size: 10, createdAt: "2026-11-01T08:00:00Z",
                                  url: "/api/files/x", expiresAt: "2026-11-01T09:00:00Z")
        XCTAssertEqual(localizer.noteFileLabel(receipt), "Receipt, PDF")
        XCTAssertEqual(localizer.text("note.left.many", ["count": 1]), "1 character left")
        XCTAssertEqual(localizer.text("note.left.many", ["count": 40]), "40 characters left")
        localizer.language = .fr
        XCTAssertTrue(localizer.megabytes(1_572_864).hasPrefix("1,5"))
    }

    /// The card, a list name with its mark and a whole list card, at a narrow and a wide phone, in light and dark, at the
    /// default and an accessibility text size: they fit their width, the note shows four lines and then More, and the mark is drawn.
    func testTheNoteCardAndAMarkedNameFitInLightAndDarkAtEveryTextSize() throws {
        let localizer = Localizer()
        let previous = localizer.language
        defer { localizer.language = previous }
        localizer.language = .en
        let short = "From Mamie, for Léa’s birthday on Saturday. Hide it in the top cupboard until then."
        let long = Array(repeating: short + " The receipt is at www.example.com/orders.", count: 8).joined(separator: " ")
        let files = [
            PackageFile(id: UUID(), name: "Receipt.pdf", contentType: .applicationPdf, size: 10, createdAt: "2026-11-01T08:00:00Z",
                        url: "/api/files/a", expiresAt: "2026-11-01T09:00:00Z"),
            PackageFile(id: UUID(), name: "Photo.jpg", contentType: .imageJpeg, size: 10, createdAt: "2026-11-01T08:00:00Z",
                        url: "/api/files/b", thumbnailURL: "/api/files/b", expiresAt: "2026-11-01T09:00:00Z"),
        ]
        for scheme in [ColorScheme.light, .dark] {
            for size in [DynamicTypeSize.large, .accessibility3] {
                for width in [CGFloat(288), 358] {
                    let label = "\(Int(width))-\(scheme == .dark ? "dark" : "light")-\(size == .large ? "default" : "ax3")"
                    func card(_ text: String) -> some View {
                        ParcelNoteCard(text: text, files: files, fileURL: { _ in nil }, edit: {}, open: { _ in })
                    }
                    let shortCard = try render(card(short), width: width, scheme: scheme, size: size, localizer: localizer, name: "note-card-\(label)")
                    let longCard = try render(card(long), width: width, scheme: scheme, size: size, localizer: localizer, name: "note-card-long-\(label)")
                    XCTAssertEqual(shortCard.width, Int(width) * 2, label)
                    XCTAssertEqual(longCard.width, Int(width) * 2, label)
                    // Four lines at the reader's size, then More: a long note's card is never much taller than four lines'.
                    let line = Double(try render(Text(verbatim: "Line").font(.body).fixedSize(), width: nil, scheme: scheme, size: size,
                                                 localizer: localizer, name: nil).height) / 2
                    let words = try render(NoteWords(text: long), width: width - 36, scheme: scheme, size: size, localizer: localizer, name: nil)
                    XCTAssertLessThanOrEqual(Double(words.height) / 2, line * 4 + 56, label)
                    XCTAssertGreaterThanOrEqual(Double(words.height) / 2, line * 3.5, label)
                    XCTAssertGreaterThanOrEqual(longCard.height, shortCard.height, label)

                    // The name of a card in the list: the mark is drawn after it, in the name's font, and the name is never cut.
                    func name(_ noted: Bool) -> some View {
                        ParcelNoteMark.name("Birthday gift for Léa and Sam", noted: noted, tint: .black)
                            .font(.headline.weight(.semibold))
                            .foregroundStyle(.black)
                            .fixedSize(horizontal: false, vertical: true)
                            .frame(maxWidth: .infinity, alignment: .leading)
                    }
                    let marked = try render(name(true), width: width - 30, scheme: scheme, size: size, localizer: localizer,
                                            name: "note-mark-\(label)", ground: .white)
                    let plain = try render(name(false), width: width - 30, scheme: scheme, size: size, localizer: localizer, name: nil, ground: .white)
                    XCTAssertGreaterThanOrEqual(marked.height, plain.height, label)
                    XCTAssertNotEqual(ink(marked), ink(plain), label)

                    // A whole card of the list, with and without its note: as wide as the list, and the noted one carries the mark.
                    let notedCard = try render(ParcelListCardPicture(parcel: parcel(label: "Birthday gift", note: short)), width: width,
                                               scheme: scheme, size: size, localizer: localizer, name: "list-card-\(label)")
                    let plainCard = try render(ParcelListCardPicture(parcel: parcel(label: "Birthday gift")), width: width,
                                               scheme: scheme, size: size, localizer: localizer, name: nil)
                    XCTAssertEqual(notedCard.width, Int(width) * 2, label)
                    XCTAssertGreaterThanOrEqual(notedCard.height, plainCard.height, label)
                    XCTAssertNotEqual(notedCard.pixels, plainCard.pixels, label)
                }
            }
        }
    }

    /// The mark keeps to the name's last word: where the name just fits one line, the word goes onto the next with it.
    func testTheMarkNeverWrapsOntoALineAlone() throws {
        let localizer = Localizer()
        func name(_ noted: Bool) -> some View {
            ParcelNoteMark.name("Birthday gift", noted: noted, tint: .black).font(.headline).foregroundStyle(.black)
        }
        let plain = try render(name(false).fixedSize(), width: nil, scheme: .light, size: .large, localizer: localizer, name: nil, ground: .white)
        let mark = try render(Text(Image(systemName: ParcelNoteMark.symbol)).font(.headline).foregroundStyle(.black).fixedSize(), width: nil,
                              scheme: .light, size: .large, localizer: localizer, name: nil, ground: .white)
        let width = CGFloat(plain.width) / 2 + 1
        let wrapped = try render(name(true).fixedSize(horizontal: false, vertical: true).frame(width: width, alignment: .leading), width: nil,
                                 scheme: .light, size: .large, localizer: localizer, name: "note-mark-wrapped", ground: .white)
        XCTAssertGreaterThan(wrapped.height, plain.height + plain.height / 2, "the marked name takes two lines")
        let secondLine = inkSpan(wrapped, rows: wrapped.height / 2 + wrapped.height / 8..<wrapped.height)
        XCTAssertGreaterThan(secondLine, inkSpan(mark, rows: 0..<mark.height) * 2, "the last word goes along with the mark")
    }

    private struct Drawn: Equatable {
        let width: Int
        let height: Int
        let pixels: [UInt8]
    }

    /// Draws a view at twice the scale, keeps the picture with the test's results, and returns its pixels.
    private func render(_ view: some View, width: CGFloat?, scheme: ColorScheme, size: DynamicTypeSize, localizer: Localizer,
                        name: String?, ground: Color = Brand.paper) throws -> Drawn {
        let content = view
            .frame(width: width)
            .background(ground)
            .environmentObject(localizer)
            .environment(\.dynamicTypeSize, size)
            .environment(\.colorScheme, scheme)
        let renderer = ImageRenderer(content: content)
        renderer.scale = 2
        // Drawn once, the note learns whether it runs past four lines; drawn again, it shows More.
        _ = renderer.cgImage
        RunLoop.main.run(until: Date().addingTimeInterval(0.05))
        renderer.proposedSize = ProposedViewSize(width: width, height: nil)
        let image = try XCTUnwrap(renderer.cgImage)
        if let name {
            let attachment = XCTAttachment(image: UIImage(cgImage: image))
            attachment.name = name
            attachment.lifetime = .keepAlways
            add(attachment)
        }
        var pixels = [UInt8](repeating: 0, count: image.width * image.height * 4)
        let context = try XCTUnwrap(CGContext(data: &pixels, width: image.width, height: image.height, bitsPerComponent: 8,
                                              bytesPerRow: image.width * 4, space: CGColorSpaceCreateDeviceRGB(),
                                              bitmapInfo: CGImageAlphaInfo.premultipliedLast.rawValue))
        context.draw(image, in: CGRect(x: 0, y: 0, width: image.width, height: image.height))
        return Drawn(width: image.width, height: image.height, pixels: pixels)
    }

    /// The dark pixels of a picture drawn in black on white.
    private func ink(_ drawn: Drawn) -> Int {
        stride(from: 0, to: drawn.pixels.count, by: 4).reduce(0) { $0 + (drawn.pixels[$1] < 128 ? 1 : 0) }
    }

    /// How wide the ink runs across the given rows, counted from the top.
    private func inkSpan(_ drawn: Drawn, rows: Range<Int>) -> Int {
        var first = Int.max, last = -1
        for row in rows where row >= 0 && row < drawn.height {
            for column in 0..<drawn.width where drawn.pixels[(row * drawn.width + column) * 4] < 128 {
                first = min(first, column)
                last = max(last, column)
            }
        }
        return last < first ? 0 : last - first
    }
}
