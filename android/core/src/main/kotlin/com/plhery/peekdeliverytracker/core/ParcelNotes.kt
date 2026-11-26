package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ErrorResponseCode
import com.plhery.peekdeliverytracker.core.api.PackageFile
import com.plhery.peekdeliverytracker.core.api.PackageFileType
import com.plhery.peekdeliverytracker.core.api.Parcel
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.UUID
import java.util.regex.Pattern
import kotlin.math.max

// A parcel's private note, as the web's src/lib/parcelNote.ts keeps it: up to 1,000 characters of text and up to five
// photos or PDFs, seen by its owner alone, on the parcel's page, in the list and in its search.

/** The note's limits, as the server holds them. */
object ParcelNotes {
    /** Characters counted as Unicode code points, as the server counts them. */
    const val MAX_LENGTH = 1_000

    /** The count of what is left shows for the last hundred. */
    const val COUNT_FROM = 100

    const val FILES_PER_PARCEL = 5

    /** Each file as sent: 10 MB. */
    const val FILE_MAX_BYTES = 10_485_760L

    /** All of an account's files, or a guest phone's: 200 MB. */
    const val FILES_TOTAL_BYTES = 209_715_200L

    const val FILE_NAME_MAX = 120

    /** Photos are made to fit this many pixels on their longest side before they are sent, as JPEG at this quality. */
    const val PHOTO_MAX_SIDE = 2_560
    const val PHOTO_QUALITY = 85

    /** How long a removal can be undone; longer while TalkBack is on. */
    const val UNDO_MILLIS = 6_000L

    /** What the document picker offers: photos, including HEIC ones, and PDFs. */
    val pickerTypes: List<String> = listOf("image/jpeg", "image/png", "image/webp", "image/heic", "image/heif", "application/pdf")

    fun length(text: String): Int = text.codePointCount(0, text.length)

    /** The text cut to the limit, never through a character's code point. */
    fun clip(text: String): String =
        if (length(text) <= MAX_LENGTH) text else text.substring(0, text.offsetByCodePoints(0, MAX_LENGTH))

    /** The note as it is saved: without space around it, within the limit; null when nothing is left, which deletes it. */
    fun saved(text: String?): String? = text?.trimmingWhitespacesAndNewlines()?.let(::clip)?.nonEmpty

    /** What the sheet says under the field once a hundred or fewer are left ("note.left.many"), null before. */
    fun leftText(text: String, localizer: Localizer): String? {
        val left = MAX_LENGTH - length(text)
        return if (left <= COUNT_FROM) localizer.text("note.left.many", mapOf("count" to max(0, left))) else null
    }

    /** The words a note's search matches: its text and its files' names. */
    fun searchText(note: String?, fileNames: List<String>): String = (listOf(note ?: "") + fileNames).joinToString(" ")
}

/** A parcel has a note when it has words or at least one file. */
val Parcel.hasNote: Boolean
    get() = note?.trimmingWhitespacesAndNewlines()?.isNotEmpty() == true || !files.isNullOrEmpty()

/** The parcel without one of its note's files. */
fun Parcel.withoutFile(id: UUID): Parcel = copy(files = files?.filter { it.id != id }?.ifEmpty { null })

/** The parcel without the files `ids`, and without its text where `text` is false: a note's removal waiting on Undo. */
fun Parcel.hidingNote(ids: Set<UUID>, text: Boolean): Parcel =
    copy(note = if (text) null else note, files = files?.filter { it.id !in ids }?.ifEmpty { null })

val PackageFileType.isPdf: Boolean get() = this == PackageFileType.ApplicationPdf

/** Why a file could not join a note, each said in one plain line (`note.files.*`). */
enum class NoteFileProblem {
    TooBig, Type, ParcelFull, AccountFull, DeviceFull, Unreadable, Offline, Failed;

    /** Problems a second try may get past. */
    val retriable: Boolean get() = this == Failed || this == Offline

    companion object {
        /** The problem of an upload the server refused, by its status and code. */
        fun upload(status: Int, code: ErrorResponseCode?): NoteFileProblem = when {
            code == ErrorResponseCode.FileTooBig || status == 413 -> TooBig
            code == ErrorResponseCode.FileType || status == 415 -> Type
            code == ErrorResponseCode.ParcelFilesFull -> ParcelFull
            code == ErrorResponseCode.AccountFilesFull -> AccountFull
            else -> Failed
        }
    }
}

/** A file that did not join the note. `size` is the file's, for one too big; `status` the server's, for one it refused. */
class NoteFileError(val problem: NoteFileProblem, val size: Long? = null, val status: Int? = null) :
    Exception("The file was not added ($problem)")

/**
 * Why a file couldn't join the note: what the service or the phone said, no connection (a request that couldn't go out
 * while the phone is offline), or anything else.
 */
fun noteFileProblem(error: Throwable): NoteFileProblem = when (error) {
    is NoteFileError -> error.problem
    is java.io.IOException -> NoteFileProblem.Offline
    else -> NoteFileProblem.Failed
}

/** A size in megabytes as people read it: one decimal under 20, whole above, never under 0.1. */
fun Localizer.megabytes(bytes: Long): String {
    val value = max(0.1, bytes / 1_048_576.0)
    val format = NumberFormat.getNumberInstance(language.locale).apply {
        minimumFractionDigits = 0
        maximumFractionDigits = if (value < 20) 1 else 0
        roundingMode = RoundingMode.HALF_UP
    }
    return text("note.files.megabytes", mapOf("size" to format.format(value)))
}

/** The line that says why the file `name` did not join the note. */
fun Localizer.fileProblemLine(problem: NoteFileProblem, name: String, size: Long? = null): String = when (problem) {
    NoteFileProblem.TooBig -> text("note.files.tooBig", mapOf("name" to name, "size" to megabytes(size ?: (ParcelNotes.FILE_MAX_BYTES + 1))))
    NoteFileProblem.Failed -> text("note.files.failed", mapOf("name" to name))
    NoteFileProblem.Type -> text("note.files.type")
    NoteFileProblem.ParcelFull -> text("note.files.full")
    NoteFileProblem.AccountFull -> text("note.files.accountFull")
    NoteFileProblem.DeviceFull -> text("note.files.deviceFull")
    NoteFileProblem.Unreadable -> text("note.files.unreadable")
    NoteFileProblem.Offline -> text("note.files.offline")
}

/** The name people know a file by: without its extension. */
fun fileTitle(name: String): String = name.replace(extension, "").ifEmpty { name }

private val extension = Regex("\\.[a-z0-9]{2,5}$", RegexOption.IGNORE_CASE)

/** What TalkBack hears for a file: a PDF's name and kind ("Receipt, PDF"), and "Photo" for a photo, as the web says. */
fun Localizer.fileLabel(file: PackageFile): String = if (file.contentType.isPdf) {
    text("note.files.open", mapOf("name" to fileTitle(file.name), "kind" to text("note.files.pdf")))
} else {
    text("note.files.photo")
}

private val byExtension = mapOf(
    "jpg" to "image/jpeg", "jpeg" to "image/jpeg", "png" to "image/png", "webp" to "image/webp", "heic" to "image/heic",
    "heif" to "image/heif", "pdf" to "application/pdf",
)

/** What a chosen file is, by the type it came with or, where it came with none, by its name's extension. */
fun chosenNoteFileType(type: String?, name: String): String? {
    val given = type?.lowercase()
    if (!given.isNullOrEmpty() && given != "application/octet-stream") return given
    return byExtension[name.substringAfterLast('.', "").lowercase()] ?: given
}

private val controls = Regex("[\\u0000-\\u001f\\u007f/\\\\]")
private val spaces = Pattern.compile("\\s+", Pattern.UNICODE_CHARACTER_CLASS).toRegex()

/** The name kept for a file: within 120 characters with its extension, which follows a change of type. */
fun noteFileName(name: String, type: PackageFileType): String {
    val extension = when (type) {
        PackageFileType.ApplicationPdf -> "pdf"
        PackageFileType.ImagePng -> "png"
        PackageFileType.ImageWebp -> "webp"
        PackageFileType.ImageJpeg -> "jpg"
    }
    val cleaned = name.replace(controls, " ").replace(spaces, " ").trim()
    val dot = cleaned.lastIndexOf('.')
    val base = (if (dot >= 0) cleaned.substring(0, dot) else cleaned).trim().ifEmpty { "File" }
    val known = if (dot >= 0) cleaned.substring(dot + 1).lowercase() else ""
    val kept = if (known == extension || (extension == "jpg" && known == "jpeg")) known else extension
    val room = ParcelNotes.FILE_NAME_MAX - kept.length - 1
    val cut = if (ParcelNotes.length(base) > room) base.substring(0, base.offsetByCodePoints(0, room)) else base
    return "$cut.$kept"
}

/** A run of a note's text: plain, or a web or e-mail address to follow (`href`). */
data class NotePiece(val text: String, val href: String? = null)

/** Endings that make a bare name an address, as people write one ("shop.example/returns"). */
private val domainEndings = setOf(
    "com", "org", "net", "io", "co", "app", "dev", "shop", "store", "info", "biz", "eu", "uk", "de", "fr", "ch", "it", "es",
    "pt", "br", "pl", "ru", "cn", "nl", "be", "at", "ie", "lu", "se", "no", "dk", "fi", "cz", "us", "ca", "au", "jp", "mx",
    "example",
)

private const val NOT_IN_ADDRESS = "[^\\p{IsWhite_Space}\\ufeff<>\"]"
private val address = Pattern.compile(
    "([\\w.+-]+@[\\w-]+(?:\\.[\\w-]+)+)|(https?://$NOT_IN_ADDRESS+)|((?:www\\.)?(?:[\\p{L}\\p{N}-]+\\.)+(\\p{L}{2,})(?:/$NOT_IN_ADDRESS*)?)",
    Pattern.CASE_INSENSITIVE or Pattern.UNICODE_CASE,
)
private val glued = Regex("[\\p{L}\\p{N}@/]")
private val trailing = Regex("[.,;:!?'\"’”)\\]]$")

/**
 * The note's text with its web and e-mail addresses made into links; nothing else is formatted. A bare name counts as
 * an address with `www.` or a known ending; punctuation after an address stays text.
 */
fun notePieces(text: String): List<NotePiece> {
    val pieces = mutableListOf<NotePiece>()
    var last = 0
    val matcher = address.matcher(text)
    while (matcher.find()) {
        val start = matcher.start()
        if (start > 0 && glued.matches(String(Character.toChars(text.codePointBefore(start))))) continue
        val email = matcher.group(1)
        val web = matcher.group(2)
        val bare = matcher.group(3)
        if (bare != null && !bare.lowercase().startsWith("www.") && matcher.group(4).lowercase() !in domainEndings) continue
        var found = matcher.group()
        // A sentence's full stop, a comma or a closing bracket the address did not open stay outside it.
        while (trailing.containsMatchIn(found)) {
            if (found.endsWith(")") && found.count { it == '(' } >= found.count { it == ')' }) break
            found = found.dropLast(1)
        }
        if (!found.contains('.')) continue
        val href = when {
            email != null -> "mailto:$found"
            web != null -> found
            else -> "https://$found"
        }
        if (start > last) pieces += NotePiece(text.substring(last, start))
        pieces += NotePiece(found, href)
        last = start + found.length
    }
    if (last < text.length) pieces += NotePiece(text.substring(last))
    return pieces
}

/**
 * Where a note's file lives when it is not on the service: on this phone for a guest ([DEVICE]), or the demo's own
 * ([DEMO]). Their `url`s never leave the phone.
 */
object NoteFileAddress {
    const val DEVICE = "peek-device-file:"
    const val DEMO = "peek-demo-file:"

    /** A local file's `expires_at`: it never does. */
    const val NEVER = "9999-12-31T23:59:59.000Z"

    fun isLocal(url: String): Boolean = url.startsWith(DEVICE) || url.startsWith(DEMO)
}

/** Whether a file's signed address has passed, or passes within a minute: the parcel is read again for fresh ones. */
fun PackageFile.expired(now: java.time.Instant = AppClock.now()): Boolean {
    if (NoteFileAddress.isLocal(url)) return false
    val end = DateParser.date(expiresAt) ?: return true
    return !end.isAfter(now.plusSeconds(60))
}

/**
 * The demo's one private note, as the web's src/store/demoNote.ts writes it, on the record from Round Midnight
 * Records: made-up order details and a shop address under `.example`, which belongs to no one, in the demo's language,
 * with a receipt and a photo of the box, which the app draws.
 */
object DemoNote {
    const val PARCEL = "1ZDEMO202600000001"

    val texts: Map<String, String> = mapOf(
        "en" to "Order RM-48213, paid 39 €. Free returns until 24 Dec at round-midnight.example/returns",
        "de" to "Bestellung RM-48213, 39 € bezahlt. Kostenlose Rücksendung bis 24. Dez. unter round-midnight.example/returns",
        "fr" to "Commande RM-48213, payée 39 €. Retour gratuit jusqu’au 24 déc. sur round-midnight.example/returns",
        "it" to "Ordine RM-48213, pagato 39 €. Reso gratuito fino al 24 dic. su round-midnight.example/returns",
        "es" to "Pedido RM-48213, pagado 39 €. Devolución gratis hasta el 24 dic. en round-midnight.example/returns",
        "pt" to "Encomenda RM-48213, paga 39 €. Devolução grátis até 24 dez. em round-midnight.example/returns",
        "pt-br" to "Pedido RM-48213, pago 39 €. Devolução grátis até 24 dez. em round-midnight.example/returns",
        "pl" to "Zamówienie RM-48213, zapłacone 39 €. Darmowy zwrot do 24 gru. na round-midnight.example/returns",
        "ru" to "Заказ RM-48213, оплачено 39 €. Бесплатный возврат до 24 дек. на round-midnight.example/returns",
        "zh" to "订单 RM-48213，已付 39 €。12 月 24 日前可在 round-midnight.example/returns 免费退货",
    )

    fun text(language: AppLanguage): String = texts[language.rawValue] ?: texts.getValue("en")

    /** The demo's note in another language when it is the demo's own text; a note someone wrote stays as it is. */
    fun textIn(note: String?, language: AppLanguage): String? = if (note != null && note in texts.values) text(language) else note

    val receiptId: UUID = UUID.fromString("0f3c5d2e-6a71-4b8e-9d0c-1a2b3c4d5e01")
    val boxId: UUID = UUID.fromString("0f3c5d2e-6a71-4b8e-9d0c-1a2b3c4d5e02")

    /** The address of the demo's photo of the box, which the app draws, and of its receipt. */
    const val BOX = NoteFileAddress.DEMO + "box"
    const val RECEIPT = NoteFileAddress.DEMO + "receipt"

    fun files(createdAt: String): List<PackageFile> = listOf(
        PackageFile(
            id = receiptId, name = "Receipt.pdf", contentType = PackageFileType.ApplicationPdf, size = receipt.size,
            createdAt = createdAt, url = RECEIPT, thumbnailUrl = null, expiresAt = NoteFileAddress.NEVER,
        ),
        PackageFile(
            id = boxId, name = "Photo of the box.jpg", contentType = PackageFileType.ImageJpeg, size = 182_000, width = 400,
            height = 400, createdAt = createdAt, url = BOX, thumbnailUrl = BOX, expiresAt = NoteFileAddress.NEVER,
        ),
    )

    fun isOwn(id: UUID): Boolean = id == receiptId || id == boxId

    /** A one-page receipt, drawn as a PDF of grey bars. */
    val receipt: ByteArray by lazy {
        val page = "0.19 0.22 0.18 rg 60 700 150 22 re f 0.78 0.8 0.76 rg 60 650 280 10 re f 60 628 200 10 re f 60 580 280 10 re f" +
            " 60 558 240 10 re f 60 536 260 10 re f 60 514 180 10 re f 0.19 0.22 0.18 rg 200 450 140 14 re f"
        val objects = listOf(
            "<< /Type /Catalog /Pages 2 0 R >>",
            "<< /Type /Pages /Kids [3 0 R] /Count 1 >>",
            "<< /Type /Page /Parent 2 0 R /MediaBox [0 0 400 800] /Contents 4 0 R >>",
            "<< /Length ${page.length} >>\nstream\n$page\nendstream",
        )
        val pdf = StringBuilder("%PDF-1.4\n")
        val offsets = mutableListOf<Int>()
        objects.forEachIndexed { index, body ->
            offsets += pdf.length
            pdf.append("${index + 1} 0 obj\n$body\nendobj\n")
        }
        val table = pdf.length
        pdf.append("xref\n0 ${objects.size + 1}\n0000000000 65535 f \n")
        for (offset in offsets) pdf.append("${offset.toString().padStart(10, '0')} 00000 n \n")
        pdf.append("trailer\n<< /Size ${objects.size + 1} /Root 1 0 R >>\nstartxref\n$table\n%%EOF\n")
        pdf.toString().encodeToByteArray()
    }
}

/** A file ready to join a note: its bytes, name and kind, and a photo's size. */
class NoteFileUpload(
    val bytes: ByteArray,
    val name: String,
    val type: PackageFileType,
    val width: Int? = null,
    val height: Int? = null,
) {
    override fun toString(): String = "NoteFileUpload(type=$type, size=${bytes.size})"
}
