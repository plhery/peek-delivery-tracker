package com.plhery.peekdeliverytracker.ui.deliveries

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.DemoNote
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NoteFileError
import com.plhery.peekdeliverytracker.core.NoteFileProblem
import com.plhery.peekdeliverytracker.core.NoteFileUpload
import com.plhery.peekdeliverytracker.core.ParcelNotes
import com.plhery.peekdeliverytracker.core.api.PackageFile
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.fileLabel
import com.plhery.peekdeliverytracker.core.fileProblemLine
import com.plhery.peekdeliverytracker.core.fileTitle
import com.plhery.peekdeliverytracker.core.hasNote
import com.plhery.peekdeliverytracker.core.hidingNote
import com.plhery.peekdeliverytracker.core.isPdf
import com.plhery.peekdeliverytracker.core.megabytes
import com.plhery.peekdeliverytracker.core.noteFileProblem
import com.plhery.peekdeliverytracker.core.notePieces
import com.plhery.peekdeliverytracker.platform.NoteFileHandOut
import com.plhery.peekdeliverytracker.platform.NoteFilePreparation
import com.plhery.peekdeliverytracker.ui.KeptTyping
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.keptFocus
import com.plhery.peekdeliverytracker.ui.sheet.FullScreenCover
import com.plhery.peekdeliverytracker.ui.sheet.KeyboardScrollColumn
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.sheetBottomInset
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.io.File
import java.util.UUID
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * The note sheet open over the window (a page's `showingNote`, the list's `prompts.noting`), kept with the window by
 * its [saved] form: its own id, its parcel's, and the page that asked, if one did.
 */
@Immutable
internal data class NoteRequest(val id: UUID, val parcelId: UUID, val pageId: UUID?) {
    val saved: String get() = listOf(id, parcelId, pageId ?: "-").joinToString(" ")

    companion object {
        fun from(saved: String): NoteRequest {
            val (id, parcelId, pageId) = saved.split(" ")
            return NoteRequest(UUID.fromString(id), UUID.fromString(parcelId), pageId.takeIf { it != "-" }?.let(UUID::fromString))
        }
    }
}

/** A file on its way to a note: the chosen photo's picture, a spinner while it goes, or what stopped it. */
@Immutable
internal data class NoteUpload(
    val key: Int,
    val parcelId: UUID,
    val name: String,
    val pdf: Boolean,
    val picture: ImageBitmap? = null,
    val problem: NoteFileProblem? = null,
    val size: Long? = null,
)

/** A problem said in a line of its own, with no file to show: another kind of file, too big, no room left. */
@Immutable
internal data class NoteLine(val key: Int, val parcelId: UUID, val problem: NoteFileProblem, val name: String, val size: Long?)

internal enum class NoteToastKind { File, Note, Failed }

/** The note's toast: a file removed or the note deleted, with Undo, or that the deletion failed. */
@Immutable
internal data class NoteToast(val key: Int, val kind: NoteToastKind, val parcelId: UUID, val pageId: UUID?, val name: String? = null)

/** A file chosen for a note: its name as the chooser gave it, whether it is a PDF, and how it is read and readied. */
internal class NotePick(val name: String, val pdf: Boolean, val read: suspend () -> NoteFileUpload)

/**
 * What one open note sheet keeps: the words typed, cut to 1,000 characters ([ParcelNotes.clip]), whether they are
 * being saved and whether the last save failed, and [done] once it has nothing left to do and closes. A card's sheet
 * keeps the parcel as its menu found it ([parcel]).
 */
@Stable
internal class NoteSheetWork(val request: NoteRequest, opened: String, val openedWithNote: Boolean, val parcel: Parcel?) {
    val textField = TextFieldState(opened)
    val text: String get() = textField.text.toString()

    var saving: Boolean by mutableStateOf(false)
        internal set

    var failed: Boolean by mutableStateOf(false)
        internal set

    var done: Boolean by mutableStateOf(false)
        internal set

    /** The three ways to add a file show under its button. */
    var choosing: Boolean by mutableStateOf(false)

    /** What is typed, cut to the limit as it comes, and the last save's failure forgotten once the words change. */
    val typing: InputTransformation = KeptTyping(ParcelNotes::clip).then(
        object : InputTransformation {
            override fun TextFieldBuffer.transformInput() {
                if (!asCharSequence().contentEquals(originalText)) failed = false
            }
        },
    )
}

/**
 * The notes' work besides their words, as the web's `useNoteController` does it, for the process: a window made again
 * finds it as it was. Files go up at once, each a tile in the sheet with a spinner, one another try may get through
 * kept with its retry, any other said in a line and gone; back online, those that waited for a connection go. A
 * removal (a file, or the whole note) hides at once and waits six seconds for its Undo ([toast]), counted while
 * TalkBack or Switch Access isn't on ([UndoClock]), then goes to the store, or at once as its page goes away or another
 * removal comes. A deletion that fails comes back, and says so.
 */
@Stable
class ParcelNoteWork(private val undoMillis: Long = ParcelNotes.UNDO_MILLIS) {
    private val uploadList = mutableStateListOf<NoteUpload>()
    private val lineList = mutableStateListOf<NoteLine>()
    private val hiddenFiles = mutableStateMapOf<UUID, Unit>()
    private val hiddenText = mutableStateMapOf<UUID, Unit>()
    private val sheets = mutableStateMapOf<UUID, NoteSheetWork>()
    private val picks = HashMap<Int, NotePick>()
    private val prepared = HashMap<Int, NoteFileUpload>()
    private val sending = HashMap<Int, Job>()
    private var waiting: Waiting? = null
    private var serial = 0

    private class Waiting(
        val key: Int,
        val kind: NoteToastKind,
        val parcelId: UUID,
        val pageId: UUID?,
        val clock: Job,
        val commit: suspend () -> Unit,
        val undo: () -> Unit,
    )

    internal var toast: NoteToast? by mutableStateOf(null)
        private set

    /** The photo shown full screen, from a page's note: its parcel, the file and the page. */
    internal var viewing: NoteViewing? by mutableStateOf(null)

    /** `parcel` as its note shows: without what waits for its Undo. */
    internal fun shown(parcel: Parcel): Parcel {
        val text = parcel.id in hiddenText
        if (!text && hiddenFiles.isEmpty()) return parcel
        return parcel.hidingNote(hiddenFiles.keys.toSet(), text)
    }

    internal fun sheet(id: UUID): NoteSheetWork? = sheets[id]

    internal fun uploads(parcelId: UUID): List<NoteUpload> = uploadList.filter { it.parcelId == parcelId }

    internal fun lines(parcelId: UUID): List<NoteLine> = lineList.filter { it.parcelId == parcelId }

    /** Whether a file waits for the phone to be back online. */
    internal val waitingOnline: Boolean get() = uploadList.any { it.problem == NoteFileProblem.Offline }

    /** Room for more files, counting those on their way. */
    internal fun room(parcel: Parcel): Int = ParcelNotes.FILES_PER_PARCEL - (shown(parcel).files?.size ?: 0) - uploads(parcel.id).size

    /** Opens the sheet asked for as `request` on the note `parcel` shows, its words as they are. */
    internal fun open(request: NoteRequest, parcel: Parcel, keepParcel: Boolean) {
        val shown = shown(parcel)
        sheets[request.id] = NoteSheetWork(request, shown.note.orEmpty(), shown.hasNote, parcel.takeIf { keepParcel })
        lineList.removeAll { it.parcelId == parcel.id }
    }

    /** The sheet asked for as `id` has closed; its files on their way still get there. */
    internal fun close(id: UUID) {
        val sheet = sheets.remove(id) ?: return
        lineList.removeAll { it.parcelId == sheet.request.parcelId }
    }

    /**
     * The sheet's Save: words with no files and nothing on its way left delete the note, with its Undo; words as they
     * were close it; others go to the store, and a failure says so in the sheet.
     */
    internal fun save(source: DeliveriesSource, sheet: NoteSheetWork, parcel: Parcel) {
        if (sheet.saving) return
        val shown = shown(parcel)
        val typed = ParcelNotes.saved(sheet.text)
        val current = ParcelNotes.saved(shown.note)
        val busy = uploads(parcel.id).count { it.problem == null }
        if (typed == null && shown.files.isNullOrEmpty() && busy == 0) {
            if (current != null) deleteNote(source, parcel, sheet.request.pageId)
            sheet.done = true
            return
        }
        if (typed == current) {
            sheet.done = true
            return
        }
        sheet.saving = true
        sheet.failed = false
        source.scope.launch {
            try {
                source.setNote(parcel, typed)
                source.action("parcel-note-save", DeliveryAnalytics.Outcome.Success)
                sheet.done = true
            } catch (error: CancellationException) {
                throw error
            } catch (error: Exception) {
                source.action("parcel-note-save", DeliveryAnalytics.Outcome.Error)
                sheet.failed = true
            } finally {
                sheet.saving = false
            }
        }
    }

    /** Adds the files chosen, each going up at once, as many as the note has room for; the rest are said too many. */
    internal fun add(source: DeliveriesSource, parcel: Parcel, chosen: List<NotePick>) {
        lineList.removeAll { it.parcelId == parcel.id }
        var left = room(parcel)
        for (pick in chosen) {
            if (left <= 0) {
                refuseFull(source, parcel.id)
                break
            }
            left -= 1
            val key = ++serial
            picks[key] = pick
            uploadList += NoteUpload(key, parcel.id, pick.name, pick.pdf)
            send(source, key)
        }
    }

    /** Says the note holds as many files as it can, for a chooser that would add none. */
    internal fun refuseFull(source: DeliveriesSource, parcelId: UUID) {
        source.action("parcel-file-add", DeliveryAnalytics.Outcome.Error)
        lineList.removeAll { it.parcelId == parcelId && it.problem == NoteFileProblem.ParcelFull }
        lineList += NoteLine(++serial, parcelId, NoteFileProblem.ParcelFull, "", null)
    }

    internal fun retry(source: DeliveriesSource, key: Int) = send(source, key)

    /** Back online: what waited for a connection goes. */
    internal fun online(source: DeliveriesSource) {
        uploadList.filter { it.problem == NoteFileProblem.Offline }.forEach { send(source, it.key) }
    }

    /** Lets a file that stopped go, its tile and its line with it. */
    internal fun dismiss(key: Int) {
        drop(key)
        lineList.removeAll { it.key == key }
    }

    private fun update(key: Int, change: (NoteUpload) -> NoteUpload) {
        val index = uploadList.indexOfFirst { it.key == key }
        if (index >= 0) uploadList[index] = change(uploadList[index])
    }

    private fun drop(key: Int, cancel: Boolean = true) {
        uploadList.removeAll { it.key == key }
        sending.remove(key)?.let { if (cancel) it.cancel() }
        picks.remove(key)
        prepared.remove(key)
    }

    private fun send(source: DeliveriesSource, key: Int) {
        val pick = picks[key] ?: return
        val parcelId = uploadList.firstOrNull { it.key == key }?.parcelId ?: return
        update(key) { it.copy(problem = null) }
        sending.remove(key)?.cancel()
        sending[key] = source.scope.launch {
            try {
                val upload = prepared[key] ?: pick.read().also { prepared[key] = it }
                val picture = if (upload.type.isPdf) null else withContext(Dispatchers.Default) { tilePicture(upload.bytes) }
                update(key) { it.copy(name = upload.name, pdf = upload.type.isPdf, size = upload.bytes.size.toLong(), picture = it.picture ?: picture) }
                val parcel = source.parcels.value.firstOrNull { it.id == parcelId } ?: throw DeliveryApiError.ParcelMissing()
                source.addNoteFile(parcel, upload)
                source.action("parcel-file-add", DeliveryAnalytics.Outcome.Success)
                uploadList.removeAll { it.key == key }
                picks.remove(key)
                prepared.remove(key)
            } catch (error: CancellationException) {
                throw error
            } catch (error: Exception) {
                fail(source, key, error)
            } finally {
                if (sending[key] === coroutineContext[Job]) sending.remove(key)
            }
        }
    }

    private fun fail(source: DeliveriesSource, key: Int, error: Exception) {
        val upload = uploadList.firstOrNull { it.key == key } ?: return
        val problem = noteFileProblem(error)
        source.action("parcel-file-add", DeliveryAnalytics.Outcome.Error)
        val size = (error as? NoteFileError)?.size ?: upload.size
        // A file another try may get through stays in the row with its retry; any other never joined the note.
        if (!problem.retriable) {
            drop(key, cancel = false)
            lineList.removeAll {
                it.parcelId == upload.parcelId && it.problem == problem && !(problem == NoteFileProblem.TooBig && it.name != upload.name)
            }
            lineList += NoteLine(key, upload.parcelId, problem, upload.name, size)
            return
        }
        update(key) { it.copy(problem = problem, size = size) }
    }

    /** Removes `file` from the note at once, and from the parcel once its Undo has passed. */
    internal fun removeFile(source: DeliveriesSource, parcel: Parcel, file: PackageFile, pageId: UUID?) {
        hiddenFiles[file.id] = Unit
        wait(source, NoteToastKind.File, parcel.id, pageId, fileTitle(file.name), commit = {
            source.removeNoteFile(parcel, file.id)
            source.action("parcel-file-remove", DeliveryAnalytics.Outcome.Success)
            hiddenFiles.remove(file.id)
        }) { hiddenFiles.remove(file.id) }
    }

    /** Deletes the words and every file at once, with one Undo for all of it. */
    internal fun deleteNote(source: DeliveriesSource, parcel: Parcel, pageId: UUID?) {
        val shown = shown(parcel)
        val ids = shown.files.orEmpty().map { it.id }
        val hadText = !shown.note.isNullOrBlank()
        hiddenText[parcel.id] = Unit
        ids.forEach { hiddenFiles[it] = Unit }
        val unhide = {
            hiddenText.remove(parcel.id)
            ids.forEach { hiddenFiles.remove(it) }
        }
        wait(source, NoteToastKind.Note, parcel.id, pageId, null, commit = {
            if (hadText) source.setNote(parcel, null)
            for (id in ids) source.removeNoteFile(parcel, id)
            source.action("parcel-note-delete", DeliveryAnalytics.Outcome.Success)
            unhide()
        }, undo = unhide)
    }

    private fun wait(
        source: DeliveriesSource,
        kind: NoteToastKind,
        parcelId: UUID,
        pageId: UUID?,
        name: String?,
        commit: suspend () -> Unit,
        undo: () -> Unit,
    ) {
        commitNow(source)
        val key = ++serial
        val clock = source.scope.launch {
            source.undoClock.held.collectLatest { held ->
                if (held) return@collectLatest
                delay(undoMillis)
                if (waiting?.key == key) commitNow(source)
            }
        }
        waiting = Waiting(key, kind, parcelId, pageId, clock, commit, undo)
        toast = NoteToast(key, kind, parcelId, pageId, name)
    }

    /** Sends what waits for its toast to end, now; one that fails comes back, and a deletion says so. */
    internal fun commitNow(source: DeliveriesSource) {
        val entry = waiting ?: return
        waiting = null
        if (toast?.key == entry.key) toast = null
        entry.clock.cancel()
        source.scope.launch {
            try {
                entry.commit()
            } catch (error: CancellationException) {
                throw error
            } catch (error: Exception) {
                entry.undo()
                if (entry.kind == NoteToastKind.Note) {
                    source.action("parcel-note-delete", DeliveryAnalytics.Outcome.Error)
                    failed(source, entry.parcelId, entry.pageId)
                } else {
                    source.action("parcel-file-remove", DeliveryAnalytics.Outcome.Error)
                }
            }
        }
    }

    private fun failed(source: DeliveriesSource, parcelId: UUID, pageId: UUID?) {
        val key = ++serial
        toast = NoteToast(key, NoteToastKind.Failed, parcelId, pageId)
        source.scope.launch {
            delay(undoMillis)
            if (toast?.key == key) toast = null
        }
    }

    /** The page with `pageId` has gone: what it left waiting goes now. */
    internal fun pageLeft(source: DeliveriesSource, pageId: UUID) {
        if (waiting?.pageId == pageId) commitNow(source)
    }

    /** The toast's Undo: what it removed comes back. */
    internal fun undo() {
        val entry = waiting ?: return
        waiting = null
        entry.clock.cancel()
        entry.undo()
        toast = null
    }

    /** A tile's picture of a photo chosen, about 240 pixels on its longer side. */
    private fun tilePicture(bytes: ByteArray): ImageBitmap? = NoteFilePreparation.picture(bytes, TILE_PIXELS)?.asImageBitmap()

    /** Pictures read once for the process, by file and size. */
    internal val pictures = android.util.LruCache<String, ImageBitmap>(24)
}

/** The photo the viewer shows: its parcel, its file's id and the page that opened it. */
@Immutable
internal data class NoteViewing(val parcelId: UUID, val fileId: UUID, val pageId: UUID?)

private const val TILE_PIXELS = 240
private const val VIEWER_PIXELS = 2_560
private const val COUNT_PAUSE = 900L

// MARK: - The card on the page

/**
 * "Your note" on the parcel's page (the web's `NoteCard`): its title in footnote semibold and its pen, the words in
 * body with web and e-mail addresses made links, four lines and then More; then its files, 56 across with 10-point
 * corners, a photo opening full screen and a PDF in the phone's viewer. Bordered as the carrier's note is, 18-point
 * continuous corners. Nothing where the note, as shown, has no words and no files.
 */
@Composable
internal fun ParcelNoteCard(state: DeliveriesState, parcel: Parcel, pageId: UUID) {
    val work = state.source.notes
    val shown = work.shown(parcel)
    if (!shown.hasNote) return
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Column(
        Modifier
            .fillMaxWidth()
            .testTag(PARCEL_NOTE_CARD)
            .border(1.dp, colors.secondaryLabel.copy(alpha = colors.secondaryLabel.alpha * 0.3f), ContinuousRoundedShape(NOTE_CARD_RADIUS))
            .padding(start = 16.dp, top = 6.dp, end = 4.dp, bottom = 14.dp)
            .semantics { isTraversalGroup = true },
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                localizer.text("note.title"),
                Modifier.weight(1f).semantics { heading() },
                color = colors.secondaryText,
                style = type.style(IosTextStyle.Footnote, FontWeight.SemiBold).sfLines(),
            )
            val edit = localizer.text("note.edit")
            Box(
                Modifier
                    .size(44.dp)
                    .testTag(PARCEL_NOTE_EDIT)
                    .button(interactionSource = null, indication = PlainPress, role = Role.Button, shape = CircleShape) { state.noteOnPage(pageId) }
                    .semantics { contentDescription = edit },
                contentAlignment = Alignment.Center,
            ) {
                PeekSymbol("pencil", font = type.fixed(17f), tint = colors.secondaryText)
            }
        }
        Column(Modifier.padding(end = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            val text = shown.note?.trim().orEmpty()
            if (text.isNotEmpty()) NoteText(text)
            val files = shown.files.orEmpty()
            if (files.isNotEmpty()) {
                Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    for (file in files) {
                        NoteFileTile(
                            state, parcel, file, CARD_TILE,
                            Modifier
                                .testTag(PARCEL_NOTE_FILE)
                                .button(interactionSource = null, indication = PlainPress, role = Role.Button, shape = ContinuousRoundedShape(10.dp)) {
                                    if (file.contentType.isPdf) {
                                        scope.launch { openPdf(context, state, parcel, file) }
                                    } else {
                                        work.viewing = NoteViewing(parcel.id, file.id, pageId)
                                    }
                                }
                                .semantics { contentDescription = localizer.fileLabel(file) },
                        )
                    }
                }
            }
        }
    }
}

/**
 * The note's words (the web's `NoteText`): web and e-mail addresses made links in the ink, underlined; four lines and
 * then More, which shows them all and becomes Less. TalkBack hears More as "note.moreLabel".
 */
@Composable
private fun NoteText(text: String) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    var open by rememberSaveable(text) { mutableStateOf(false) }
    var long by remember(text) { mutableStateOf(false) }
    val ink = colors.ink
    val words = remember(text, ink) { linked(text, ink) }
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            words,
            Modifier.testTag(PARCEL_NOTE_TEXT),
            color = colors.label,
            style = type.body.sfLines(),
            maxLines = if (open) Int.MAX_VALUE else NOTE_LINES,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { layout -> if (!open) long = layout.hasVisualOverflow },
        )
        if (long || open) {
            val label = localizer.text("note.moreLabel")
            Text(
                localizer.text(if (open) "note.less" else "note.more"),
                Modifier
                    .testTag(PARCEL_NOTE_MORE)
                    .heightIn(min = 32.dp)
                    .button(interactionSource = null, indication = PlainPress, role = Role.Button, onClickLabel = if (open) null else label) { open = !open }
                    .padding(vertical = 6.dp),
                color = ink,
                style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines(),
            )
        }
    }
}

/** The note's text with its addresses as links ([notePieces]). */
private fun linked(text: String, ink: Color): AnnotatedString = buildAnnotatedString {
    for (piece in notePieces(text)) {
        val href = piece.href
        if (href == null) {
            append(piece.text)
        } else {
            withLink(LinkAnnotation.Url(href, TextLinkStyles(SpanStyle(color = ink, textDecoration = TextDecoration.Underline)))) {
                append(piece.text)
            }
        }
    }
}

/**
 * One of the note's files as a tile `side` across with 10-point corners: a photo's thumbnail, read once for the process
 * ([ParcelNoteWork.pictures]); the demo's box drawn; a PDF's page with its "PDF" badge.
 */
@Composable
internal fun NoteFileTile(state: DeliveriesState, parcel: Parcel, file: PackageFile, side: Dp, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(10.dp)
    Box(
        modifier
            .size(side)
            .clip(shape)
            .background(colors.ink.copy(alpha = colors.ink.alpha * 0.06f), shape)
            .border(1.dp, colors.secondaryLabel.copy(alpha = colors.secondaryLabel.alpha * 0.25f), shape),
        contentAlignment = Alignment.Center,
    ) {
        when {
            file.contentType.isPdf -> PdfTile(side)
            file.url == DemoNote.BOX -> DemoBox(side)
            else -> {
                val picture = rememberNotePicture(state, parcel, file, thumbnail = true)
                if (picture != null) {
                    Image(picture, null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                } else {
                    PeekSymbol("photo.on.rectangle", font = PeekTheme.type.fixed(side.value * 0.32f), tint = colors.secondaryText)
                }
            }
        }
    }
}

/** A photo's picture, its thumbnail or the photo itself: read once for the process, null until read or where it can't be. */
@Composable
private fun rememberNotePicture(state: DeliveriesState, parcel: Parcel, file: PackageFile, thumbnail: Boolean): ImageBitmap? {
    val work = state.source.notes
    val key = "${file.id}:${if (thumbnail) "thumb" else "full"}"
    val picture by produceState(work.pictures.get(key), key) {
        if (value != null) return@produceState
        val read = try {
            state.source.noteFileBytes(parcel, file, thumbnail)?.let { bytes ->
                withContext(Dispatchers.Default) {
                    NoteFilePreparation.picture(bytes, if (thumbnail) TILE_PIXELS else VIEWER_PIXELS)?.asImageBitmap()
                }
            }
        } catch (error: CancellationException) {
            throw error
        } catch (_: Exception) {
            null
        }
        if (read != null) work.pictures.put(key, read)
        value = read
    }
    return picture
}

/** A PDF's tile: a page with lines on it, and "PDF" on a dark badge at its foot. */
@Composable
private fun PdfTile(side: Dp) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Box(Modifier.fillMaxSize().background(colors.paper), contentAlignment = Alignment.Center) {
        PeekSymbol("doc.text", font = type.fixed(side.value * 0.42f), tint = colors.secondaryText)
        Text(
            localizedPdf(),
            Modifier
                .align(Alignment.BottomStart)
                .padding(start = 6.dp, bottom = 6.dp)
                .background(colors.label.copy(alpha = 0.75f), ContinuousRoundedShape(4.dp))
                .padding(horizontal = 4.dp, vertical = 1.dp),
            color = colors.background,
            style = type.fixed(10f, FontWeight.Bold),
        )
    }
}

@Composable
private fun localizedPdf(): String = LocalLocalizer.current.text("note.files.pdf")

/** The demo's photo of its box, drawn: a parcel on cardboard brown. */
@Composable
private fun DemoBox(side: Dp) {
    Box(Modifier.fillMaxSize().background(DEMO_CARDBOARD), contentAlignment = Alignment.Center) {
        PeekSymbol("shippingbox.fill", font = PeekTheme.type.fixed(side.value * 0.46f), tint = DEMO_BOX)
    }
}

/** Opens a PDF in the phone's viewer, through the app's FileProvider; one the phone can't open is offered to share. */
private suspend fun openPdf(context: Context, state: DeliveriesState, parcel: Parcel, file: PackageFile) {
    val bytes = try {
        state.source.noteFileBytes(parcel, file, thumbnail = false)
    } catch (error: CancellationException) {
        throw error
    } catch (_: Exception) {
        null
    } ?: return
    val uri = NoteFileHandOut.write(context, file.name, bytes)
    if (!NoteFileHandOut.view(context, uri, file.contentType.rawValue)) NoteFileHandOut.share(context, uri, file.contentType.rawValue, file.name)
}

// MARK: - The list's mark

/** The list's quiet mark after a parcel's name: a note, or a paperclip once it holds a photo or a file. */
internal fun noteMarkSymbol(parcel: Parcel): String? = when {
    !parcel.hasNote -> null
    !parcel.files.isNullOrEmpty() -> "paperclip"
    else -> "note.text"
}

/**
 * A card's name with its note's quiet mark after it ([noteMarkSymbol]) in the secondary ink, held to the name's last
 * word by a no-break space so that it never wraps alone. TalkBack hears "has a note" in the card's label instead.
 */
@Composable
internal fun noteMarked(name: String, parcel: Parcel): Pair<AnnotatedString, Map<String, InlineTextContent>> {
    val symbol = noteMarkSymbol(parcel) ?: return AnnotatedString(name) to emptyMap()
    val colors = PeekTheme.colors
    val font = PeekTheme.type.footnote
    val text = remember(name) {
        buildAnnotatedString {
            append(name)
            append('\u00A0')
            appendInlineContent(LIST_NOTE_MARK, "\uFFFC")
        }
    }
    val content = mapOf(
        LIST_NOTE_MARK to InlineTextContent(Placeholder(1.2.em, 1.em, PlaceholderVerticalAlign.TextCenter)) {
            Box(Modifier.fillMaxSize().testTag(LIST_NOTE_MARK), contentAlignment = Alignment.Center) {
                PeekSymbol(symbol, font = font, tint = colors.secondaryText)
            }
        },
    )
    return text to content
}

/** What TalkBack hears of the mark, after the name. */
internal fun noteMarkLabel(parcel: Parcel, localizer: Localizer): String? = if (parcel.hasNote) localizer.text("note.hasNote") else null

// MARK: - The sheet

/** What the note sheet shows, kept as it goes down. */
private data class NotingShown(val request: NoteRequest, val work: NoteSheetWork)

/**
 * The note sheet (the web's `NoteSheet`, the iPhone's `ParcelNoteSheet`) over the whole window, where the tab shell
 * draws it: the app's [Sheet], as tall as what it holds and then the large detent. The page's goes with its page and
 * shows nothing once its parcel has gone; a card's shows the parcel as its menu found it. While its words are being
 * saved, nothing sends it away. Then the photo viewer, over the whole window, and the camera's permission and photo.
 */
@Composable
internal fun ParcelNoteHost(state: DeliveriesState) {
    val request = state.noting
    val notes = state.source.notes
    val unshown = request?.pageId != null && request.pageId != state.navigator.navigation.shown
    val work = request?.let { notes.sheet(it.id) }
    val current = request != null && work != null && !unshown
    var up by remember { mutableStateOf(false) }
    if (current) up = true
    LaunchedEffect(request?.id, current) {
        if (request == null || current) return@LaunchedEffect
        state.closeNote()
        if (!up) state.noteClosed()
    }
    var kept by remember { mutableStateOf<NotingShown?>(null) }
    if (current) kept = NotingShown(request, work)
    val shown = kept
    val parcel = shown?.let { state.notedParcel(it.request, it.work, state.parcels) }
    val done = work?.takeIf { current }?.done == true
    LaunchedEffect(done) { if (done) state.closeNote() }
    Sheet(
        visible = current,
        onDismiss = { state.closeNote() },
        paneTitle = LocalLocalizer.current.text("note.title"), id = "note",
        detents = listOf(SheetDetent.Fitted, SheetDetent.Large),
        onClosed = {
            up = false
            kept = null
            state.noteClosed()
        },
        dismissible = shown?.work?.saving != true,
        lit = true,
    ) {
        if (shown != null && parcel != null) ParcelNoteSheet(state, shown.work, parcel)
    }
    NoteViewerHost(state)
    // Back online, what waited for a connection goes.
    val waitingOnline = notes.waitingOnline
    val context = LocalContext.current
    DisposableEffect(waitingOnline) {
        if (!waitingOnline) return@DisposableEffect onDispose {}
        val connectivity = context.getSystemService(ConnectivityManager::class.java)
        val source = state.source
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                source.scope.launch { notes.online(source) }
            }
        }
        val registered = try {
            connectivity?.registerDefaultNetworkCallback(callback)
            connectivity != null
        } catch (_: Exception) {
            false
        }
        onDispose { if (registered) runCatching { connectivity?.unregisterNetworkCallback(callback) } }
    }
}

/**
 * The sheet's body, 22 in from the sides, 560 wide at most: the parcel's name over "Your note" with its ✕; the field,
 * five lines at least, its placeholder, its words cut at 1,000 characters; the lock line ("note.private", or where a
 * guest's note is kept) with the characters left for the last 100, said to TalkBack once typing pauses; the files and
 * those on their way, each with its ✕ or its retry; the problems; "Add a photo or file", which opens to the camera,
 * the photos and the files; why the save failed; Save; and Delete where it opened on a note. A file removed says so
 * with its Undo at the sheet's foot.
 */
@Composable
private fun ParcelNoteSheet(state: DeliveriesState, sheet: NoteSheetWork, parcel: Parcel) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val source = state.source
    val notes = source.notes
    val shown = notes.shown(parcel)
    val bottom = sheetBottomInset()
    val name = parcel.label.ifEmpty { localizer.text("common.parcel") }
    val left = ParcelNotes.leftText(sheet.text, localizer)
    // TalkBack hears the count once typing pauses, not on every key.
    LaunchedEffect(left) {
        if (left == null) return@LaunchedEffect
        delay(COUNT_PAUSE)
        state.announce(left)
    }
    val toast = notes.toast?.takeIf { it.kind == NoteToastKind.File && it.parcelId == parcel.id }
    Box(Modifier.fillMaxSize().background(colors.sheet)) {
        KeyboardScrollColumn(Modifier.fillMaxSize(), bottom = bottom, dismissesKeyboard = true) {
            Column(
                Modifier
                    .testTag(PARCEL_NOTE_SHEET)
                    .fillMaxWidth()
                    .wrapContentWidth()
                    .widthIn(max = NOTE_SHEET_WIDTH)
                    .fillMaxWidth()
                    .padding(start = NOTE_SHEET_SIDE, top = 22.dp, end = NOTE_SHEET_SIDE, bottom = 18.dp + bottom + if (toast != null) 72.dp else 0.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                NoteSheetHeader(name, sheet.saving) { state.closeNote() }
                NoteField(sheet, name)
                LockLine(localizer.text(if (source.isGuest) "note.guestApp" else "note.private"), left)
                val files = shown.files.orEmpty()
                val uploads = notes.uploads(parcel.id)
                if (files.isNotEmpty() || uploads.isNotEmpty()) {
                    Row(Modifier.horizontalScroll(rememberScrollState()).padding(top = 6.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        for (file in files) {
                            Box {
                                NoteFileTile(state, parcel, file, SHEET_TILE, Modifier.semantics { contentDescription = localizer.fileLabel(file) })
                                RemoveBadge(localizer.text("note.files.remove", mapOf("name" to fileTitle(file.name)))) {
                                    notes.removeFile(source, parcel, file, sheet.request.pageId)
                                }
                            }
                        }
                        for (upload in uploads) {
                            Box {
                                UploadTile(upload) { notes.retry(source, upload.key) }
                                RemoveBadge(localizer.text("note.files.remove", mapOf("name" to fileTitle(upload.name)))) { notes.dismiss(upload.key) }
                            }
                        }
                    }
                }
                ProblemLines(state, parcel, uploads)
                AddFiles(state, sheet, parcel)
                if (sheet.failed) {
                    Text(
                        localizer.text("note.failed"),
                        Modifier.testTag(PARCEL_NOTE_FAILED).semantics { liveRegion = LiveRegionMode.Polite },
                        color = colors.warning,
                        style = type.footnote.sfLines(),
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    SaveButton(sheet.saving) { notes.save(source, sheet, parcel) }
                    if (sheet.openedWithNote) {
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(
                                localizer.text("note.delete"),
                                Modifier
                                    .testTag(PARCEL_NOTE_DELETE)
                                    .heightIn(min = 44.dp)
                                    .button(interactionSource = null, indication = PlainPress, enabled = !sheet.saving, role = Role.Button) {
                                        notes.deleteNote(source, parcel, sheet.request.pageId)
                                        sheet.done = true
                                    }
                                    .plainDisabled(!sheet.saving)
                                    .padding(horizontal = 16.dp, vertical = 11.dp),
                                color = colors.red,
                                style = type.headline.sfLines(),
                            )
                        }
                    }
                }
            }
        }
        if (toast != null) {
            NoteUndoToast(
                state, toast, null,
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 16.dp, end = 16.dp, bottom = bottom + 12.dp)
                    .widthIn(max = NOTE_TOAST_WIDTH),
            )
        }
    }
}

/** The parcel's name in footnote secondary over "Your note" in title2 semibold, and the ✕, off while the words are saved. */
@Composable
private fun NoteSheetHeader(name: String, saving: Boolean, close: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val closeLabel = localizer.text("common.close")
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(name, color = colors.secondaryText, style = type.footnote.sfLines(), maxLines = 2, overflow = TextOverflow.Ellipsis)
            Text(
                localizer.text("note.title"),
                Modifier.testTag(PARCEL_NOTE_TITLE).semantics { heading() },
                color = colors.label,
                style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(),
            )
        }
        Box(
            Modifier
                .size(44.dp)
                .testTag(PARCEL_NOTE_CLOSE)
                .button(interactionSource = null, indication = PlainPress, enabled = !saving, role = Role.Button, shape = CircleShape, onClick = close)
                .plainDisabled(!saving)
                .semantics { contentDescription = closeLabel },
            contentAlignment = Alignment.Center,
        ) {
            Box(Modifier.size(36.dp).background(colors.sheetButton, CircleShape), contentAlignment = Alignment.Center) {
                PeekSymbol("xmark", font = type.fixed(13f, FontWeight.SemiBold), tint = colors.label)
            }
        }
    }
}

/**
 * The note's field: body on the paper, five lines tall at least and growing to twelve, 14 and 12 in, with 14-point
 * continuous corners and a line in the accent while it has the focus. TalkBack names it "note.fieldNamed".
 */
@Composable
private fun NoteField(sheet: NoteSheetWork, name: String) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val style = PeekTheme.type.body.sfLines()
    var focused by remember { mutableStateOf(false) }
    val label = localizer.text("note.fieldNamed", mapOf("name" to name))
    val placeholder = localizer.text("note.placeholder")
    val shape = ContinuousRoundedShape(14.dp)
    BasicTextField(
        sheet.textField,
        Modifier
            .fillMaxWidth()
            .testTag(PARCEL_NOTE_FIELD)
            .keptFocus(PARCEL_NOTE_FIELD)
            .onFocusChanged { focused = it.isFocused }
            .semantics { contentDescription = label },
        inputTransformation = sheet.typing,
        textStyle = style.copy(color = colors.label),
        lineLimits = TextFieldLineLimits.MultiLine(minHeightInLines = 5, maxHeightInLines = 12),
        cursorBrush = SolidColor(colors.accent),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
        decorator = { text ->
            Box(
                Modifier
                    .background(colors.paper, shape)
                    .border(if (focused) 1.5.dp else 1.dp, if (focused) colors.accent else colors.separator, shape)
                    .padding(horizontal = 14.dp, vertical = 12.dp),
            ) {
                if (sheet.text.isEmpty()) Text(placeholder, Modifier.clearAndSetSemantics {}, color = colors.tertiaryLabel, style = style)
                text()
            }
        },
    )
}

/** The lock and who can see the note, in footnote secondary; the characters left at the trailing end for the last 100. */
@Composable
private fun LockLine(text: String, left: String?) {
    val colors = PeekTheme.colors
    val style = PeekTheme.type.footnote
    Row(Modifier.testTag(PARCEL_NOTE_LOCK), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        PeekSymbol("lock", Modifier.alignByBaseline(), font = style, tint = colors.secondaryText)
        Text(text, Modifier.weight(1f).alignByBaseline(), color = colors.secondaryText, style = style.sfLines())
        if (left != null) {
            Text(
                left,
                Modifier.alignByBaseline().testTag(PARCEL_NOTE_COUNT),
                color = colors.secondaryText,
                style = style.sfLines(),
            )
        }
    }
}

/** A file on its way: its picture or a PDF's page, dimmed under a spinner, or under its retry once it stopped. */
@Composable
private fun UploadTile(upload: NoteUpload, retry: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(10.dp)
    val stopped = upload.problem != null
    val label = if (stopped) {
        "${localizer.text("note.files.retry")}: ${upload.name}"
    } else {
        localizer.text("note.files.uploading", mapOf("name" to upload.name))
    }
    Box(
        Modifier
            .size(SHEET_TILE)
            .testTag(if (stopped) PARCEL_NOTE_RETRY else PARCEL_NOTE_UPLOAD)
            .clip(shape)
            .background(colors.ink.copy(alpha = colors.ink.alpha * 0.06f), shape)
            .then(
                if (stopped) {
                    Modifier.button(interactionSource = null, indication = PlainPress, role = Role.Button, shape = shape, onClick = retry)
                } else {
                    Modifier
                },
            )
            .clearAndSetSemantics {
                contentDescription = label
                if (stopped) {
                    role = Role.Button
                    onClick {
                        retry()
                        true
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        val picture = upload.picture
        when {
            upload.pdf -> PdfTile(SHEET_TILE)
            picture != null -> Image(picture, null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop, alpha = 0.55f)
        }
        Box(Modifier.size(34.dp).background(colors.sheet.copy(alpha = 0.9f), CircleShape), contentAlignment = Alignment.Center) {
            if (stopped) {
                PeekSymbol(
                    if (upload.problem == NoteFileProblem.Offline) "wifi.exclamationmark" else "arrow.clockwise",
                    font = PeekTheme.type.fixed(15f, FontWeight.SemiBold), tint = colors.label,
                )
            } else {
                ActivityIndicator(size = 18.dp, tint = colors.label)
            }
        }
    }
}

/** A tile's ✕ at its top trailing corner, 24 across on the label at 80 %, in a touch 44 across. */
@Composable
private fun BoxScope.RemoveBadge(label: String, remove: () -> Unit) {
    val colors = PeekTheme.colors
    Box(
        Modifier
            .align(Alignment.TopEnd)
            .offset(x = 12.dp, y = (-12).dp)
            .size(44.dp)
            .testTag(PARCEL_NOTE_REMOVE)
            .button(interactionSource = null, indication = PlainPress, role = Role.Button, shape = CircleShape, onClick = remove)
            .semantics { contentDescription = label },
        contentAlignment = Alignment.Center,
    ) {
        Box(Modifier.size(24.dp).background(colors.label.copy(alpha = 0.8f), CircleShape), contentAlignment = Alignment.Center) {
            PeekSymbol("xmark", font = PeekTheme.type.fixed(10f, FontWeight.Bold), tint = colors.background)
        }
    }
}

/**
 * The problems, each a line with a warning in the warning colour: the files that never joined; that the phone is
 * offline, said once whatever the number of files waiting; and each file another try may get through, with its retry.
 */
@Composable
private fun ProblemLines(state: DeliveriesState, parcel: Parcel, uploads: List<NoteUpload>) {
    val localizer = LocalLocalizer.current
    val notes = state.source.notes
    val lines = notes.lines(parcel.id)
    val offline = uploads.any { it.problem == NoteFileProblem.Offline }
    val failed = uploads.filter { it.problem != null && it.problem != NoteFileProblem.Offline }
    if (lines.isEmpty() && !offline && failed.isEmpty()) return
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        for (line in lines) ProblemLine(localizer.fileProblemLine(line.problem, line.name, line.size), "exclamationmark.triangle")
        if (offline) ProblemLine(localizer.fileProblemLine(NoteFileProblem.Offline, ""), "wifi.exclamationmark")
        for (upload in failed) {
            ProblemLine(localizer.fileProblemLine(upload.problem ?: NoteFileProblem.Failed, upload.name, upload.size), "exclamationmark.triangle") {
                notes.retry(state.source, upload.key)
            }
        }
    }
}

@Composable
private fun ProblemLine(text: String, symbol: String, retry: (() -> Unit)? = null) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val style = PeekTheme.type.footnote
    Row(
        Modifier.fillMaxWidth().testTag(PARCEL_NOTE_PROBLEM).semantics(mergeDescendants = retry == null) { liveRegion = LiveRegionMode.Polite },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PeekSymbol(symbol, Modifier.alignByBaseline(), font = style, tint = colors.warning)
        Text(text, Modifier.weight(1f).alignByBaseline(), color = colors.warning, style = style.sfLines())
        if (retry != null) {
            Text(
                localizer.text("note.files.retry"),
                Modifier.alignByBaseline().button(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = retry),
                color = colors.ink,
                style = PeekTheme.type.style(IosTextStyle.Footnote, FontWeight.SemiBold).sfLines(),
            )
        }
    }
}

/**
 * "Add a photo or file" with its paperclip, an outlined button 44 tall at least; it opens to taking a photo, where
 * the phone has a camera, choosing photos ([ActivityResultContracts.PickMultipleVisualMedia]) and choosing a file
 * ([ActivityResultContracts.OpenMultipleDocuments], PDFs and photos). A note with no room says so instead.
 */
@Composable
private fun AddFiles(state: DeliveriesState, sheet: NoteSheetWork, parcel: Parcel) {
    val localizer = LocalLocalizer.current
    val context = LocalContext.current
    val source = state.source
    val notes = source.notes
    val parcelId = parcel.id
    fun chosen(uris: List<Uri>) {
        if (uris.isEmpty()) return
        val now = source.parcels.value.firstOrNull { it.id == parcelId } ?: sheet.parcel ?: parcel
        val picks = uris.map { uri ->
            val pdf = context.contentResolver.getType(uri) == "application/pdf"
            NotePick(uri.lastPathSegment?.substringAfterLast('/').orEmpty(), pdf) { NoteFilePreparation.prepare(context, uri) }
        }
        notes.add(source, now, picks)
    }
    val photos = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(ParcelNotes.FILES_PER_PARCEL)) { chosen(it) }
    val documents = rememberLauncherForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { chosen(it) }
    var cameraFile by rememberSaveable { mutableStateOf<String?>(null) }
    val camera = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { taken ->
        val path = cameraFile ?: return@rememberLauncherForActivityResult
        cameraFile = null
        val file = File(path)
        if (!taken) {
            file.delete()
            return@rememberLauncherForActivityResult
        }
        val now = source.parcels.value.firstOrNull { it.id == parcelId } ?: parcel
        val name = NoteFileHandOut.cameraName()
        notes.add(
            source, now,
            listOf(
                NotePick(name, pdf = false) {
                    withContext(Dispatchers.IO) {
                        val bytes = file.readBytes()
                        file.delete()
                        NoteFilePreparation.prepare(bytes, name, "image/jpeg")
                    }
                },
            ),
        )
    }
    fun takePhoto() {
        val (file, uri) = NoteFileHandOut.cameraTarget(context)
        cameraFile = file.path
        camera.launch(uri)
    }
    val permission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted -> if (granted) takePhoto() }
    val hasCamera = remember { NoteFileHandOut.hasCamera(context) }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedButton(localizer.text("note.files.add"), "paperclip", PARCEL_NOTE_ADD) {
            if (notes.room(parcel) <= 0) notes.refuseFull(source, parcelId) else sheet.choosing = !sheet.choosing
        }
        if (sheet.choosing) {
            Column(Modifier.padding(start = 12.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                if (hasCamera) {
                    ChoiceRow(localizer.text("note.files.camera"), "camera", PARCEL_NOTE_CAMERA) {
                        sheet.choosing = false
                        // A phone that has the camera permission in its manifest must be granted it to hand the camera a photo to take.
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            takePhoto()
                        } else {
                            permission.launch(Manifest.permission.CAMERA)
                        }
                    }
                }
                ChoiceRow(localizer.text("note.files.photos"), "photo.on.rectangle", PARCEL_NOTE_PHOTOS) {
                    sheet.choosing = false
                    photos.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
                ChoiceRow(localizer.text("note.files.documents"), "doc", PARCEL_NOTE_DOCUMENTS) {
                    sheet.choosing = false
                    documents.launch(ParcelNotes.pickerTypes.toTypedArray())
                }
            }
        }
    }
}

/** One of the ways to add a file: its symbol 10 before its words in body, 44 tall, a plain button. */
@Composable
private fun ChoiceRow(title: String, symbol: String, tag: String, action: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 44.dp)
            .testTag(tag)
            .button(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = action)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeekSymbol(symbol, font = type.body, tint = colors.ink)
        Text(title, color = colors.label, style = type.body.sfLines())
    }
}

/** An outlined button: its symbol 10 before its words in headline, 18 and 11 in, 44 tall at least, 14-point corners. */
@Composable
private fun OutlinedButton(title: String, symbol: String, tag: String, action: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val shape = ContinuousRoundedShape(14.dp)
    Row(
        Modifier
            .testTag(tag)
            .button(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button, shape = shape, onClick = action)
            .background(colors.paper, shape)
            .border(1.dp, colors.separator, shape)
            .heightIn(min = 44.dp)
            .padding(horizontal = 18.dp, vertical = 11.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeekSymbol(symbol, font = type.body, tint = colors.label)
        Text(title, color = colors.label, style = type.headline.sfLines())
    }
}

/** "Save note" on the accent across the sheet, 50 tall at least with 16-point corners; a spinner while it saves. */
@Composable
private fun SaveButton(saving: Boolean, save: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val shape = ContinuousRoundedShape(16.dp)
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(PARCEL_NOTE_SAVE)
            .button(interactionSource = null, indication = PressStyle.Tactile, enabled = !saving, role = Role.Button, shape = shape, onClick = save)
            .background(colors.accent, shape)
            .heightIn(min = 50.dp)
            .padding(horizontal = 20.dp, vertical = 13.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (saving) ActivityIndicator(Modifier.clearAndSetSemantics {}, size = REGULAR_SPINNER, tint = colors.onAccent)
        Text(localizer.text("note.save"), color = colors.onAccent, style = type.headline.sfLines(), textAlign = TextAlign.Center)
    }
}

// MARK: - The toast

/**
 * The note's toast ([ParcelNoteWork.toast]): "{{name}} removed" or "Note deleted." with Undo, in the label's
 * ink; or that the deletion failed, with no button. Drawn where its removal was asked: the sheet, the page, the list.
 */
@Composable
internal fun NoteUndoToast(state: DeliveriesState, toast: NoteToast, backdrop: MaterialBackdrop?, modifier: Modifier = Modifier) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val notes = state.source.notes
    val text = when (toast.kind) {
        NoteToastKind.File -> localizer.text("note.files.removed", mapOf("name" to toast.name.orEmpty()))
        NoteToastKind.Note -> localizer.text("note.deleted")
        NoteToastKind.Failed -> localizer.text("note.deleteFailed")
    }
    LaunchedEffect(toast.key) { state.announce(text) }
    Box(modifier.testTag(PARCEL_NOTE_TOAST)) {
        InlineToast(
            text = text,
            button = if (toast.kind == NoteToastKind.Failed) null else localizer.text("app.undo"),
            symbol = if (toast.kind == NoteToastKind.Failed) "exclamationmark.triangle.fill" else "trash",
            tint = if (toast.kind == NoteToastKind.Failed) colors.warning else colors.label,
            action = if (toast.kind == NoteToastKind.Failed) null else notes::undo,
            backdrop = backdrop,
        )
    }
}

// MARK: - The viewer

/**
 * A photo of the note full screen, on black (the web's `NoteFileViewer`): ✕ and its name over "Photo · 1.2 MB" at the
 * top; the photo fitted in the middle; Share, Save (to Downloads) and Remove at the foot. Remove takes it from the
 * note, with its Undo on the page, and closes the viewer.
 */
@Composable
private fun NoteViewerHost(state: DeliveriesState) {
    val notes = state.source.notes
    val viewing = notes.viewing
    val parcel = viewing?.let { v -> state.parcels.firstOrNull { it.id == v.parcelId } }
    val file = parcel?.let { notes.shown(it) }?.files?.firstOrNull { it.id == viewing.fileId }
    val unshown = viewing?.pageId != null && viewing.pageId != state.navigator.navigation.shown
    LaunchedEffect(viewing, file == null, unshown) { if (viewing != null && (file == null || unshown)) notes.viewing = null }
    var kept by remember { mutableStateOf<Pair<Parcel, PackageFile>?>(null) }
    if (parcel != null && file != null && !unshown) kept = parcel to file
    val localizer = LocalLocalizer.current
    FullScreenCover(
        visible = viewing != null && file != null && !unshown,
        onDismiss = { notes.viewing = null },
        paneTitle = kept?.second?.name?.let(::fileTitle) ?: localizer.text("note.files.photo"),
        id = "noteViewer",
        onClosed = { kept = null },
    ) {
        val shown = kept
        if (shown != null) NoteViewer(state, shown.first, shown.second, viewing?.pageId)
    }
}

@Composable
private fun NoteViewer(state: DeliveriesState, parcel: Parcel, file: PackageFile, pageId: UUID?) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val notes = state.source.notes
    val picture = rememberNotePicture(state, parcel, file, thumbnail = false)
    val white = Color.White
    Box(Modifier.fillMaxSize().background(Color.Black).testTag(PARCEL_NOTE_VIEWER)) {
        Box(Modifier.fillMaxSize().safeDrawingPadding().padding(top = 64.dp, bottom = 84.dp), contentAlignment = Alignment.Center) {
            if (picture != null) {
                Image(picture, fileTitle(file.name), Modifier.fillMaxSize(), contentScale = ContentScale.Fit)
            } else if (file.url == DemoNote.BOX) {
                Box(Modifier.fillMaxWidth().padding(24.dp).heightIn(min = 240.dp)) { DemoBox(240.dp) }
            } else {
                ActivityIndicator(tint = white)
            }
        }
        Column(Modifier.fillMaxWidth().safeDrawingPadding()) {
            Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                val close = localizer.text("common.close")
                Box(
                    Modifier
                        .size(44.dp)
                        .button(interactionSource = null, indication = PlainPress, role = Role.Button, shape = CircleShape) { notes.viewing = null }
                        .semantics { contentDescription = close },
                    contentAlignment = Alignment.Center,
                ) {
                    PeekSymbol("xmark", font = type.fixed(17f, FontWeight.SemiBold), tint = white)
                }
                Column(Modifier.weight(1f).padding(start = 6.dp)) {
                    Text(fileTitle(file.name), color = white, style = type.headline.sfLines(), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(
                        "${localizer.text("note.files.photo")} · ${localizer.megabytes(file.size.toLong())}",
                        color = white.copy(alpha = 0.7f), style = type.footnote.sfLines(),
                    )
                }
            }
        }
        Row(
            Modifier.align(Alignment.BottomCenter).fillMaxWidth().safeDrawingPadding().padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            ViewerAction(localizer.text("note.files.share"), "square.and.arrow.up") {
                scope.launch {
                    val bytes = fileBytes(state, parcel, file) ?: return@launch
                    NoteFileHandOut.share(context, NoteFileHandOut.write(context, file.name, bytes), file.contentType.rawValue, file.name)
                }
            }
            ViewerAction(localizer.text("note.files.save"), "square.and.arrow.down") {
                scope.launch {
                    val bytes = fileBytes(state, parcel, file) ?: return@launch
                    NoteFileHandOut.saveToDownloads(context, file.name, file.contentType.rawValue, bytes)
                }
            }
            ViewerAction(localizer.text("note.files.removeShort"), "trash") {
                notes.viewing = null
                notes.removeFile(state.source, parcel, file, pageId)
            }
        }
    }
}

private suspend fun fileBytes(state: DeliveriesState, parcel: Parcel, file: PackageFile): ByteArray? = try {
    state.source.noteFileBytes(parcel, file, thumbnail = false)
} catch (error: CancellationException) {
    throw error
} catch (_: Exception) {
    null
}

@Composable
private fun ViewerAction(title: String, symbol: String, action: () -> Unit) {
    val type = PeekTheme.type
    Column(
        Modifier
            .heightIn(min = 56.dp)
            .widthIn(min = 72.dp)
            .button(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = action)
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        PeekSymbol(symbol, font = type.fixed(20f), tint = Color.White)
        Text(title, color = Color.White, style = type.caption.sfLines())
    }
}

private val NOTE_CARD_RADIUS = 18.dp
private val CARD_TILE = 56.dp
private val SHEET_TILE = 72.dp
private val NOTE_SHEET_WIDTH = 560.dp
private val NOTE_SHEET_SIDE = 22.dp
internal val NOTE_TOAST_WIDTH = 420.dp
internal val NOTE_TOAST_SIDE = 16.dp
internal val NOTE_TOAST_BOTTOM = 12.dp
private const val NOTE_LINES = 4
private val DEMO_CARDBOARD = Color(0xFFD2A877)
private val DEMO_BOX = Color(0xFF8A5A2B)

internal const val PARCEL_NOTE_CARD = "parcelNote.card"
internal const val PARCEL_NOTE_EDIT = "parcelNote.edit"
internal const val PARCEL_NOTE_TEXT = "parcelNote.text"
internal const val PARCEL_NOTE_MORE = "parcelNote.more"
internal const val PARCEL_NOTE_FILE = "parcelNote.file"
internal const val PARCEL_NOTE_SHEET = "parcelNote.sheet"
internal const val PARCEL_NOTE_TITLE = "parcelNote.title"
internal const val PARCEL_NOTE_CLOSE = "parcelNote.close"
internal const val PARCEL_NOTE_FIELD = "parcelNote.field"
internal const val PARCEL_NOTE_LOCK = "parcelNote.lock"
internal const val PARCEL_NOTE_COUNT = "parcelNote.count"
internal const val PARCEL_NOTE_ADD = "parcelNote.add"
internal const val PARCEL_NOTE_CAMERA = "parcelNote.camera"
internal const val PARCEL_NOTE_PHOTOS = "parcelNote.photos"
internal const val PARCEL_NOTE_DOCUMENTS = "parcelNote.documents"
internal const val PARCEL_NOTE_UPLOAD = "parcelNote.upload"
internal const val PARCEL_NOTE_RETRY = "parcelNote.retry"
internal const val PARCEL_NOTE_REMOVE = "parcelNote.remove"
internal const val PARCEL_NOTE_PROBLEM = "parcelNote.problem"
internal const val PARCEL_NOTE_FAILED = "parcelNote.failed"
internal const val PARCEL_NOTE_SAVE = "parcelNote.save"
internal const val PARCEL_NOTE_DELETE = "parcelNote.delete"
internal const val PARCEL_NOTE_TOAST = "parcelNote.toast"
internal const val PARCEL_NOTE_VIEWER = "parcelNote.viewer"
internal const val LIST_NOTE_MARK = "list.noteMark"
