package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.ErrorResponseCode
import com.plhery.peekdeliverytracker.core.api.GiftWords
import com.plhery.peekdeliverytracker.core.api.PackageFile
import com.plhery.peekdeliverytracker.core.api.PackageFileType
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.ShareParcelRequest
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.nio.file.Files
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/** A photo of a note, as the service lists one. */
internal fun noteFile(name: String = "Receipt.pdf", type: PackageFileType = PackageFileType.ApplicationPdf): PackageFile = PackageFile(
    id = UUID.randomUUID(), name = name, contentType = type, size = 24_000, createdAt = "2026-11-20T10:00:00Z",
    url = "/api/files/x?v=full", thumbnailUrl = null, expiresAt = "2026-11-20T10:30:00Z",
)

/** As src/lib/parcelNote.test.ts and the server's limits: a parcel's private note and its files. */
class ParcelNoteTests {
    private val english = Localizer(AppLanguage.En)

    @Test
    fun theLimitCountsCodePointsAsTheServerDoes() {
        assertEquals(3, ParcelNotes.length("abc"))
        // An emoji is two UTF-16 units and one code point; an accent written apart is a code point of its own, as
        // Postgres's char_length counts them.
        assertEquals(1, ParcelNotes.length("😀"))
        assertEquals(2, ParcelNotes.length("é"))
        val emoji = "😀".repeat(1_001)
        assertEquals(1_000, ParcelNotes.length(ParcelNotes.clip(emoji)))
        assertEquals(2_000, ParcelNotes.clip(emoji).length)
        // Never through a character's two halves.
        assertEquals("a".repeat(999) + "😀", ParcelNotes.clip("a".repeat(999) + "😀😀"))
        assertEquals("short", ParcelNotes.clip("short"))
    }

    @Test
    fun aNoteIsSavedTrimmedAndBlankDeletesIt() {
        assertEquals("Door code 4512", ParcelNotes.saved("  Door code 4512 \n"))
        assertNull(ParcelNotes.saved(" \n\t "))
        assertNull(ParcelNotes.saved(null))
        assertEquals(1_000, ParcelNotes.length(ParcelNotes.saved("x".repeat(1_200))!!))
    }

    @Test
    fun theCountShowsForTheLastHundred() {
        assertNull(ParcelNotes.leftText("a".repeat(899), english))
        assertEquals("100 characters left", ParcelNotes.leftText("a".repeat(900), english))
        assertEquals("1 character left", ParcelNotes.leftText("a".repeat(999), english))
        assertEquals("0 characters left", ParcelNotes.leftText("😀".repeat(1_000), english))
        // Russian takes its few and many forms.
        assertEquals("Осталось 3 символа", ParcelNotes.leftText("a".repeat(997), Localizer(AppLanguage.Ru)))
    }

    @Test
    fun aParcelHasANoteWithWordsOrAFile() {
        val parcel = ParcelLogicTestFixtures.makeParcel()
        assertFalse(parcel.hasNote)
        assertFalse(parcel.copy(note = " \n").hasNote)
        assertTrue(parcel.copy(note = "Gift for Ana").hasNote)
        assertTrue(parcel.copy(files = listOf(noteFile())).hasNote)
        assertFalse(parcel.copy(files = emptyList()).hasNote)
        val file = noteFile()
        val noted = parcel.copy(note = "Gift", files = listOf(file, noteFile("Box.jpg", PackageFileType.ImageJpeg)))
        assertEquals(listOf("Box.jpg"), noted.withoutFile(file.id).files?.map { it.name })
        assertNull(noted.copy(files = listOf(file)).withoutFile(file.id).files)
        val hidden = noted.hidingNote(setOf(file.id), text = true)
        assertNull(hidden.note)
        assertEquals(listOf("Box.jpg"), hidden.files?.map { it.name })
    }

    @Test
    fun theListsSearchFindsTheNotesWordsAndItsFilesNames() {
        val parcel = ParcelLogicTestFixtures.makeParcel().copy(note = "Door code 4512, ask Amélie", files = listOf(noteFile("Return label.pdf")))
        assertTrue(parcel.searchText.contains("4512"))
        assertTrue(parcel.searchText.contains("amélie"))
        assertTrue(parcel.searchText.contains("return label"))
        assertFalse(ParcelLogicTestFixtures.makeParcel().searchText.contains("4512"))
    }

    @Test
    fun webAndEmailAddressesBecomeLinksAndNothingElse() {
        assertEquals(
            listOf(
                NotePiece("Returns at "),
                NotePiece("round-midnight.example/returns", "https://round-midnight.example/returns"),
                NotePiece(". Mail "),
                NotePiece("help@shop.example", "mailto:help@shop.example"),
                NotePiece(" or see "),
                NotePiece("https://shop.example/a(b)", "https://shop.example/a(b)"),
            ),
            notePieces("Returns at round-midnight.example/returns. Mail help@shop.example or see https://shop.example/a(b)"),
        )
        // A name with no known ending, an order number with a dot, or a version stays text.
        assertEquals(listOf(NotePiece("Order RM-48213, v1.2 from foo.bar")), notePieces("Order RM-48213, v1.2 from foo.bar"))
        assertEquals(listOf(NotePiece("(see "), NotePiece("www.shop.example", "https://www.shop.example"), NotePiece(")")), notePieces("(see www.shop.example)"))
    }

    @Test
    fun aFilesNameKeepsWithin120CharactersAndFollowsItsType() {
        assertEquals("Receipt.pdf", noteFileName("Receipt.pdf", PackageFileType.ApplicationPdf))
        assertEquals("IMG_0001.jpg", noteFileName("IMG_0001.HEIC", PackageFileType.ImageJpeg))
        assertEquals("photo.jpeg", noteFileName("photo.jpeg", PackageFileType.ImageJpeg))
        assertEquals("File.png", noteFileName("  /\\ ", PackageFileType.ImagePng))
        val long = noteFileName("😀".repeat(200) + ".pdf", PackageFileType.ApplicationPdf)
        assertEquals(ParcelNotes.FILE_NAME_MAX, ParcelNotes.length(long))
        assertTrue(long.endsWith(".pdf"))
        assertEquals("image/heic", chosenNoteFileType(null, "IMG_0001.HEIC"))
        assertEquals("application/pdf", chosenNoteFileType("application/octet-stream", "label.pdf"))
        assertEquals("image/png", chosenNoteFileType("image/png", "whatever"))
        assertEquals("Receipt", fileTitle("Receipt.pdf"))
        assertEquals(".pdf", fileTitle(".pdf"))
    }

    @Test
    fun eachProblemIsSaidInOnePlainLine() {
        assertEquals(NoteFileProblem.TooBig, NoteFileProblem.upload(413, null))
        assertEquals(NoteFileProblem.Type, NoteFileProblem.upload(415, ErrorResponseCode.FileType))
        assertEquals(NoteFileProblem.ParcelFull, NoteFileProblem.upload(409, ErrorResponseCode.ParcelFilesFull))
        assertEquals(NoteFileProblem.AccountFull, NoteFileProblem.upload(409, ErrorResponseCode.AccountFilesFull))
        assertEquals(NoteFileProblem.Failed, NoteFileProblem.upload(500, null))
        assertTrue(NoteFileProblem.Offline.retriable)
        assertFalse(NoteFileProblem.TooBig.retriable)
        assertEquals("Return label.pdf is 24 MB. Files can be up to 10 MB.", english.fileProblemLine(NoteFileProblem.TooBig, "Return label.pdf", 24L * 1_048_576))
        assertEquals("Big.pdf is 10.5 MB. Files can be up to 10 MB.", english.fileProblemLine(NoteFileProblem.TooBig, "Big.pdf", 11_010_048))
        assertEquals("A parcel holds up to 5 photos or files.", english.fileProblemLine(NoteFileProblem.ParcelFull, ""))
        assertEquals("Couldn’t add Receipt.jpg.", english.fileProblemLine(NoteFileProblem.Failed, "Receipt.jpg"))
        assertEquals("This device has no room left for photos and files.", english.fileProblemLine(NoteFileProblem.DeviceFull, ""))
        // A PDF says its name and kind; a photo is a photo.
        assertEquals("Receipt, PDF", english.fileLabel(noteFile()))
        assertEquals("Photo", english.fileLabel(noteFile("Box.jpg", PackageFileType.ImageJpeg)))
    }

    @Test
    fun aSignedAddressIsReadAgainOnceItHasPassed() {
        val file = noteFile()
        assertFalse(file.expired(Instant.parse("2026-11-20T10:28:00Z")))
        assertTrue(file.expired(Instant.parse("2026-11-20T10:29:30Z")))
        assertFalse(file.copy(url = NoteFileAddress.DEVICE + file.id).expired(Instant.parse("2027-01-01T00:00:00Z")))
    }

    @Test
    fun aGuestsFilesKeepToTheirLimitsAndGoWithTheirParcel() {
        val folder = Files.createTempDirectory("note-files").toFile()
        try {
            var free = Long.MAX_VALUE
            val files = DeviceNoteFiles(folder, freeSpace = { free })
            val photo = NoteFileUpload(ByteArray(1_000) { it.toByte() }, "Box.HEIC", PackageFileType.ImageJpeg, 400, 300)
            repeat(ParcelNotes.FILES_PER_PARCEL) { files.add("22222222222A", photo) }
            assertEquals(listOf("Box.jpg"), files.of("22222222222A").map { it.name }.distinct())
            try {
                files.add("22222222222A", photo)
                fail("A sixth file")
            } catch (error: NoteFileError) {
                assertEquals(NoteFileProblem.ParcelFull, error.problem)
            }
            try {
                files.add("22222222222B", NoteFileUpload(ByteArray((ParcelNotes.FILE_MAX_BYTES + 1).toInt()), "Big.pdf", PackageFileType.ApplicationPdf))
                fail("A file over 10 MB")
            } catch (error: NoteFileError) {
                assertEquals(NoteFileProblem.TooBig, error.problem)
                assertEquals(ParcelNotes.FILE_MAX_BYTES + 1, error.size)
            }
            free = 1_000
            try {
                files.add("22222222222B", photo)
                fail("A phone with no room")
            } catch (error: NoteFileError) {
                assertEquals(NoteFileProblem.DeviceFull, error.problem)
            }
            // Handed over to the account, a link's files wait to be sent and are no longer the link's to forget.
            val first = files.of("22222222222A").first()
            val packageId = UUID.randomUUID()
            assertEquals(5, files.handOver("22222222222A", packageId))
            files.forget("22222222222A")
            assertEquals(5, files.sending.size)
            assertTrue(files.sending.all { it.packageId == packageId })
            assertEquals(photo.bytes.toList(), files.bytes(first.id)?.toList())
            for (file in files.sending) files.remove(file.id)
            assertEquals(listOf<String>(), folder.list()!!.toList())
        } finally {
            folder.deleteRecursively()
        }
    }

    @Test
    fun theDemosNoteSpeaksTheDemosLanguageAndItsFilesAreDrawn() {
        val demo = DemoRepository(MemoryDefaults(), language = { AppLanguage.Fr })
        val parcel = demo.list().first { it.trackingNumber == DemoNote.PARCEL }
        assertEquals(DemoNote.text(AppLanguage.Fr), parcel.note)
        assertEquals(listOf("Receipt.pdf", "Photo of the box.jpg"), parcel.files?.map { it.name })
        assertTrue(DemoNote.receipt.decodeToString().startsWith("%PDF-1.4"))
        // Only the demo's one parcel has a note.
        assertEquals(1, demo.list().count { it.hasNote })
        // A note written in the demo stays as written.
        val written = demo.setNote(parcel.id, "Keep the box")
        assertEquals("Keep the box", written.note)
        assertEquals("Keep the box", demo.list().first { it.id == parcel.id }.note)
    }

    /**
     * SPEC "Where it never shows": the widget's snapshot, which the launcher's widgets and shortcuts read, carries no
     * note and no file's name.
     */
    @Test
    fun theWidgetsSnapshotCarriesNoNote() = runTest {
        val defaults = MemoryDefaults()
        val demo = AppConfiguration(
            mode = AppConfiguration.Mode.Demo, apiBaseUrl = "https://widget.test", supabaseUrl = null, supabasePublishableKey = "",
            googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = false, appGroupIdentifier = "widget.test",
        )
        val transport = RecordingTransport()
        val session = testSessionStore(demo, backgroundScope, transport = transport, defaults = defaults)
        val store = testParcelStore(demo, session, transport, backgroundScope, defaults)
        store.setDeliveryLiveActivitiesEnabled(false)
        session.enterDemo()
        runCurrent()
        // Words, not digits alone: a snapshot's times hold any run of digits by chance.
        val secret = "Door code BLUEGATE behind the blue gate"
        for (parcel in store.parcels) {
            store.setNote(parcel, secret)
            store.addFile(parcel, NoteFileUpload(byteArrayOf(1, 2, 3), "Hidden receipt.pdf", PackageFileType.ApplicationPdf))
        }
        store.refreshDeliverySurfaces()
        runCurrent()
        assertTrue(store.parcels.all { it.note == secret })
        val published = requireNotNull(DeliveryWidgetSharedStore(defaults).snapshot)
        assertTrue(published.parcels.isNotEmpty())
        // What the widget reads: its keys, beside the demo's own list, which keeps the note for the app.
        val widget = defaults.values.filterKeys { it.startsWith("sdt.deliveryWidget") }
        assertTrue(widget.isNotEmpty())
        for ((_, value) in widget) {
            val text = when (value) {
                is ByteArray -> value.decodeToString()
                else -> value.toString()
            }
            assertFalse(text, text.contains("BLUEGATE"))
            assertFalse(text, text.contains("Hidden receipt"))
        }
        assertTrue(store.nextDeliveries.none { it.toString().contains("BLUEGATE") })

    }

    @Serializable
    private class Fixture(val publicParcel: PublicParcelResponse)

    private fun publicParcel(): PublicParcelResponse =
        ApiJson.decodeFromString(Fixture.serializer(), SharedResources.text("ContractFixtures.json")).publicParcel

    /** As src/peek/ParcelPage.note.test.tsx: a gift's viewer reads its note before delivery only when its sender said so. */
    @Test
    fun aGiftsNoteShowsBeforeDeliveryOnlyWhenItsSenderShowsItEarly() {
        val answer = publicParcel()
        val wrapped = answer.copy(link = answer.link.copy(gift = true, role = com.plhery.peekdeliverytracker.core.api.ParcelLinkRole.Viewer))
        assertEquals(ParcelLinkGift.Wrapped, wrapped.gift)
        // As the service sends it before delivery: no name, and the note and its sender only when early is set.
        assertNull(wrapped.earlyGiftWords)
        assertNull(wrapped.copy(link = wrapped.link.copy(giftWords = GiftWords(note = "Happy birthday!", from = "Sam"))).earlyGiftWords)
        assertNull(wrapped.copy(link = wrapped.link.copy(giftWords = GiftWords(note = "Happy birthday!", from = "Sam", early = false))).earlyGiftWords)
        val early = wrapped.copy(link = wrapped.link.copy(giftWords = GiftWords(note = "Happy birthday!", from = "Sam", early = true)))
        assertEquals(ParcelLinkWords(note = "Happy birthday!", from = "Sam"), early.earlyGiftWords)
        // Delivered, the words are the opened gift's, which the page shows as always.
        val events = early.parcel.trackingEvents
        val delivered = early.copy(parcel = early.parcel.copy(trackingEvents = listOf(events[0].copy(stage = TrackingStage.Delivered)) + events.drop(1)))
        assertNull(delivered.earlyGiftWords)
        // Not a gift: nothing early.
        assertNull(early.copy(link = early.link.copy(gift = false)).earlyGiftWords)
    }

    @Test
    fun theSendersEarlySwitchGoesOutAsTrueAndOffAsNull() {
        val words = ParcelShareWords(name = true, note = " Happy birthday! ", from = "Sam")
        assertNull(words.shownEarly)
        assertEquals(ParcelLinkWords(note = "Happy birthday!", from = "Sam"), words.copy(early = true).shownEarly)
        assertFalse(words.equalsCanonically(words.copy(early = true)))
        assertTrue(words.copy(early = true).cleaned.early)
        assertTrue(words.copy(early = true).limited.early)
        val request = ParcelShareModel.Settings(gift = true, giftWords = GiftWords(note = "Enjoy", early = true)).request
        assertEquals(
            """{"gift":true,"giftWords":{"early":true,"from":null,"name":null,"note":"Enjoy"},"showNumber":false}""",
            JsonEncoder.deliveryTracker.encode(ShareParcelRequest.serializer(), request).decodeToString(),
        )
    }
}
