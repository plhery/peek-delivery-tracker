package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.NoteFileAddress
import com.plhery.peekdeliverytracker.core.NoteFileError
import com.plhery.peekdeliverytracker.core.NoteFileProblem
import com.plhery.peekdeliverytracker.core.NoteFileUpload
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.PackageFile
import com.plhery.peekdeliverytracker.core.api.PackageFileType
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import java.io.IOException
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A parcel's private note: its card on the page, its words made links and folded after four lines, the mark on its
 * card in the list; its sheet from the card's menu and the page's pen, saved, left as it was, failed, deleted with its
 * Undo; what is left to type, where a guest's note is kept; files going up, failing with their reason or kept for
 * another try, removed with their Undo, and no more than five.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelNoteTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    private val gift get() = DemoCards.parcel("Birthday gift").id

    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    private val receipt = PackageFile(
        id = UUID(0, 41), name = "receipt.pdf", contentType = PackageFileType.ApplicationPdf, size = 120_000,
        createdAt = "2026-11-20T10:00:00.000Z", url = NoteFileAddress.DEMO + "receipt", expiresAt = NoteFileAddress.NEVER,
    )

    private fun photo(index: Int) = PackageFile(
        id = UUID(0, 50L + index), name = "box-$index.jpg", contentType = PackageFileType.ImageJpeg, size = 800_000, width = 1200, height = 900,
        createdAt = "2026-11-20T10:00:00.000Z", url = NoteFileAddress.DEMO + "box-$index", expiresAt = NoteFileAddress.NEVER,
    )

    /** The demo's parcels, the gift with `note` and `files`, in an account or as a guest. */
    private fun show(note: String? = null, files: List<PackageFile>? = null, guest: Boolean = false, prepare: FakeDeliveries.() -> Unit = {}) {
        val parcels = DemoCards.parcels().map { if (it.label.startsWith("Birthday gift")) it.copy(note = note, files = files) else it }
        source = FakeDeliveries(parcels, reader).apply {
            isGuest = guest
            prepare()
        }
        compose.waitsForMaps()
        compose.setContent {
            androidx.compose.runtime.CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = reader, source = source, reduceMotion = false)
            }
        }
        compose.waitForIdle()
    }

    private fun settle(millis: Long = 1_000) {
        compose.mainClock.advanceTimeBy(millis)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun count(tag: String): Int = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().size

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun tap(tag: String) {
        node(tag).performSemanticsAction(SemanticsActions.OnClick)
        compose.waitForIdle()
    }

    private fun shows(tag: String, text: String): Boolean =
        compose.onAllNodes(hasText(text, substring = true) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag)))).fetchSemanticsNodes().isNotEmpty()

    /** The note's toast, which TalkBack reads as one: what it says. */
    private fun toast(): SemanticsNodeInteraction = compose.onNode(hasTestTag(INLINE_TOAST) and hasAnyAncestor(hasTestTag(PARCEL_NOTE_TOAST)))

    private fun toastSays(text: String): Boolean =
        compose.onAllNodes(hasTestTag(INLINE_TOAST) and hasAnyAncestor(hasTestTag(PARCEL_NOTE_TOAST)) and hasContentDescription(text, substring = true))
            .fetchSemanticsNodes().isNotEmpty()

    private fun editable(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun parcel(): Parcel = source.parcels.value.first { it.id == gift }

    private fun openPage() {
        compose.openCard("Birthday gift")
        settle()
    }

    /** The gift's page, then its note's pen. */
    private fun openSheetOnPage() {
        openPage()
        node(PARCEL_NOTE_CARD).performScrollTo()
        tap(PARCEL_NOTE_EDIT)
        settle()
    }

    private fun openMenu() {
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed("Birthday gift"))
        compose.card("Birthday gift").performTouchInput { longClick() }
        compose.waitForIdle()
    }

    private fun type(text: String) {
        node(PARCEL_NOTE_FIELD).performTextReplacement(text)
        compose.waitForIdle()
    }

    private fun outcomes(name: String): List<DeliveryAnalytics.Outcome?> = source.actions.indices.filter { source.actions[it] == name }.map { source.outcomes[it] }

    /**
     * Chooses the photos `names`, each read and its tile's picture made as this returns: the picture is made off the
     * main thread, which waitForIdle doesn't wait for, and the tile is given its size as the file then goes up.
     */
    private fun add(vararg names: String) {
        compose.runOnUiThread {
            source.notes.add(
                source, parcel(),
                names.map { name -> NotePick(name, pdf = false) { NoteFileUpload(byteArrayOf(1, 2, 3), name, PackageFileType.ImageJpeg, 10, 10) } },
            )
        }
        compose.waitUntil(10_000) { source.notes.uploads(gift).all { it.size != null } }
        compose.waitForIdle()
    }

    // The card and the mark.

    @Test
    fun aNotedParcelsPageShowsItsNoteWithLinksAndFoldsItAfterFourLines() {
        val long = (1..12).joinToString("\n") { "Line $it" } + "\nOrder at https://example.com/order"
        show(note = long)
        openPage()
        assertTrue(exists(PARCEL_NOTE_CARD))
        assertTrue(shows(PARCEL_NOTE_CARD, "Your note"))
        assertTrue(shows(PARCEL_NOTE_TEXT, "Line 1"))
        assertTrue(exists(PARCEL_NOTE_MORE))
        assertTrue(shows(PARCEL_NOTE_MORE, "More"))
        tap(PARCEL_NOTE_MORE)
        assertTrue(shows(PARCEL_NOTE_MORE, "Less"))
    }

    @Test
    fun aParcelWithoutANoteHasNoCard() {
        show()
        openPage()
        assertFalse(exists(PARCEL_NOTE_CARD))
    }

    @Test
    fun aNotedCardSaysSoToTalkBackAfterItsName() {
        show(note = "Door code 1234")
        val label = compose.card("Birthday gift").fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString()
        assertTrue(label.orEmpty(), label.orEmpty().contains("Birthday gift 🎁, has a note"))
        assertEquals("note.text", noteMarkSymbol(parcel()))
        assertEquals("paperclip", noteMarkSymbol(parcel().copy(files = listOf(receipt))))
        assertNull(noteMarkSymbol(parcel().copy(note = null)))
    }

    // The sheet.

    @Test
    fun theCardsMenuAddsANoteThroughTheSheet() {
        show()
        openMenu()
        tap(menuItemTag(ParcelMenuItem.AddNote))
        settle()
        assertTrue(exists(PARCEL_NOTE_SHEET))
        assertEquals(gift, deliveries.noting?.parcelId)
        assertNull(deliveries.noting?.pageId)
        assertTrue(shows(PARCEL_NOTE_LOCK, "Only you can see it"))
        assertFalse(exists(PARCEL_NOTE_DELETE))
        type("  Blue box, door code 1234  ")
        tap(PARCEL_NOTE_SAVE)
        settle()
        assertEquals(listOf(gift to "Blue box, door code 1234"), source.notesSaved)
        assertEquals(listOf<DeliveryAnalytics.Outcome?>(DeliveryAnalytics.Outcome.Success), outcomes("parcel-note-save"))
        assertFalse(exists(PARCEL_NOTE_SHEET))
        assertNull(deliveries.noting)
    }

    @Test
    fun thePenOpensThePagesNoteAndSavingItUnchangedSendsNothing() {
        show(note = "Door code 1234")
        openSheetOnPage()
        assertTrue(exists(PARCEL_NOTE_SHEET))
        assertEquals(gift, deliveries.noting?.pageId)
        assertEquals("Door code 1234", editable(PARCEL_NOTE_FIELD))
        assertTrue(exists(PARCEL_NOTE_DELETE))
        tap(PARCEL_NOTE_SAVE)
        settle()
        assertEquals(emptyList<Any>(), source.notesSaved)
        assertFalse(exists(PARCEL_NOTE_SHEET))
    }

    @Test
    fun aFailedSaveSaysSoAndKeepsTheSheetOpen() {
        show(note = "Door code 1234") { failure = IOException("offline") }
        openSheetOnPage()
        type("Door code 5678")
        tap(PARCEL_NOTE_SAVE)
        settle()
        assertTrue(exists(PARCEL_NOTE_SHEET))
        assertTrue(shows(PARCEL_NOTE_FAILED, "Couldn’t save the note."))
        assertEquals(listOf<DeliveryAnalytics.Outcome?>(DeliveryAnalytics.Outcome.Error), outcomes("parcel-note-save"))
        // Typing again clears it.
        type("Door code 56789")
        assertFalse(exists(PARCEL_NOTE_FAILED))
    }

    @Test
    fun theCountShowsForTheLastHundredCharactersAndTheFieldStopsAtAThousand() {
        show()
        openMenu()
        tap(menuItemTag(ParcelMenuItem.AddNote))
        settle()
        type("a".repeat(800))
        assertFalse(exists(PARCEL_NOTE_COUNT))
        type("a".repeat(950))
        assertTrue(shows(PARCEL_NOTE_COUNT, "50 characters left"))
        type("a".repeat(1_200))
        assertEquals(1_000, editable(PARCEL_NOTE_FIELD)?.length)
    }

    @Test
    fun aGuestIsToldTheirNoteStaysOnThisDevice() {
        show(guest = true)
        openMenu()
        tap(menuItemTag(ParcelMenuItem.AddNote))
        settle()
        assertTrue(shows(PARCEL_NOTE_LOCK, "Kept on this device only."))
    }

    // Deleting.

    @Test
    fun deletingTheNoteHidesItWithAnUndoThenDeletesItAfterSixSeconds() {
        show(note = "Door code 1234", files = listOf(receipt))
        openSheetOnPage()
        tap(PARCEL_NOTE_DELETE)
        settle()
        assertFalse(exists(PARCEL_NOTE_SHEET))
        assertFalse(exists(PARCEL_NOTE_CARD))
        assertTrue(toastSays("Note deleted."))
        // Undo brings it all back, and sends nothing.
        toast().performSemanticsAction(SemanticsActions.OnClick)
        compose.waitForIdle()
        assertTrue(exists(PARCEL_NOTE_CARD))
        assertFalse(exists(PARCEL_NOTE_TOAST))
        assertEquals(emptyList<Any>(), source.notesSaved)
        // Deleted again and left: after six seconds the words and the file go.
        tap(PARCEL_NOTE_EDIT)
        settle()
        tap(PARCEL_NOTE_DELETE)
        settle(6_500)
        assertEquals(listOf(gift to null), source.notesSaved)
        assertEquals(listOf(gift to receipt.id), source.filesRemoved)
        assertEquals(listOf<DeliveryAnalytics.Outcome?>(DeliveryAnalytics.Outcome.Success), outcomes("parcel-note-delete"))
        assertFalse(exists(PARCEL_NOTE_TOAST))
        assertFalse(exists(PARCEL_NOTE_CARD))
    }

    @Test
    fun emptyingTheWordsOfANoteWithoutFilesDeletesIt() {
        show(note = "Door code 1234")
        openSheetOnPage()
        type("   ")
        tap(PARCEL_NOTE_SAVE)
        settle()
        assertTrue(toastSays("Note deleted."))
        assertEquals(emptyList<Any>(), source.notesSaved)
        settle(6_500)
        assertEquals(listOf(gift to null), source.notesSaved)
    }

    @Test
    fun leavingThePageSendsTheWaitingDeletionAtOnce() {
        show(note = "Door code 1234")
        openSheetOnPage()
        tap(PARCEL_NOTE_DELETE)
        settle()
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
        assertEquals(listOf(gift to null), source.notesSaved)
    }

    @Test
    fun aDeletionThatFailsComesBackAndSaysSo() {
        show(note = "Door code 1234") { failure = IOException("offline") }
        openSheetOnPage()
        tap(PARCEL_NOTE_DELETE)
        settle(6_500)
        assertTrue(exists(PARCEL_NOTE_CARD))
        assertTrue(toastSays("Couldn’t delete the note."))
        assertEquals(listOf<DeliveryAnalytics.Outcome?>(DeliveryAnalytics.Outcome.Error), outcomes("parcel-note-delete"))
    }

    // Files.

    @Test
    fun aFileChosenGoesUpWithASpinnerAndJoinsTheNote() {
        val going = CompletableDeferred<Unit>()
        show(note = "Door code 1234") { uploading = going }
        openSheetOnPage()
        add("box.jpg")
        assertEquals(1, count(PARCEL_NOTE_UPLOAD))
        going.complete(Unit)
        settle()
        assertEquals(0, count(PARCEL_NOTE_UPLOAD))
        assertEquals(listOf(gift to "box.jpg"), source.filesAdded)
        assertEquals(listOf<DeliveryAnalytics.Outcome?>(DeliveryAnalytics.Outcome.Success), outcomes("parcel-file-add"))
        assertEquals(1, parcel().files?.size)
    }

    @Test
    fun aFileTooBigSaysWhyAndLeaves() {
        show(note = "Door code 1234") { uploadFailure = NoteFileError(NoteFileProblem.TooBig, size = 12_582_912) }
        openSheetOnPage()
        add("box.jpg")
        settle()
        assertEquals(0, count(PARCEL_NOTE_UPLOAD) + count(PARCEL_NOTE_RETRY))
        assertTrue(shows(PARCEL_NOTE_PROBLEM, "Files can be up to 10 MB."))
        assertEquals(listOf<DeliveryAnalytics.Outcome?>(DeliveryAnalytics.Outcome.Error), outcomes("parcel-file-add"))
    }

    @Test
    fun anOfflineFileKeepsItsTileAndGoesOnRetry() {
        show(note = "Door code 1234") { uploadFailure = IOException("offline") }
        openSheetOnPage()
        add("box.jpg", "label.jpg")
        settle()
        assertEquals(2, count(PARCEL_NOTE_RETRY))
        // Offline is said once, whatever the number of files waiting.
        assertEquals(1, count(PARCEL_NOTE_PROBLEM))
        assertTrue(shows(PARCEL_NOTE_PROBLEM, "You’re offline."))
        source.uploadFailure = null
        compose.runOnUiThread { source.notes.online(source) }
        // Each goes once its tile's picture is made again, off the main thread: there, or stopped again.
        compose.waitUntil(10_000) { source.notes.uploads(gift).all { it.problem != null } }
        settle()
        assertEquals(0, count(PARCEL_NOTE_RETRY))
        // Both go again at once, as they first went, so either may get there first.
        assertEquals(listOf(gift to "box.jpg", gift to "label.jpg"), source.filesAdded.sortedBy { it.second })
    }

    @Test
    fun aNoteHoldsFiveFilesAtMost() {
        show(note = "Door code 1234", files = (1..4).map(::photo))
        openSheetOnPage()
        add("a.jpg", "b.jpg")
        settle()
        assertEquals(listOf(gift to "a.jpg"), source.filesAdded)
        assertTrue(shows(PARCEL_NOTE_PROBLEM, "A parcel holds up to 5 photos or files."))
        // With no room left, the button says so too.
        tap(PARCEL_NOTE_ADD)
        assertFalse(exists(PARCEL_NOTE_PHOTOS))
        assertTrue(shows(PARCEL_NOTE_PROBLEM, "A parcel holds up to 5 photos or files."))
    }

    @Test
    fun theAddButtonOffersThePhotosAndTheFiles() {
        show(note = "Door code 1234")
        openSheetOnPage()
        tap(PARCEL_NOTE_ADD)
        assertTrue(exists(PARCEL_NOTE_PHOTOS))
        assertTrue(exists(PARCEL_NOTE_DOCUMENTS))
    }

    @Test
    fun removingAFileInTheSheetHidesItWithItsUndoThenRemovesIt() {
        show(note = "Door code 1234", files = listOf(receipt))
        openSheetOnPage()
        assertEquals(1, count(PARCEL_NOTE_REMOVE))
        tap(PARCEL_NOTE_REMOVE)
        assertEquals(0, count(PARCEL_NOTE_REMOVE))
        assertTrue(toastSays("receipt removed"))
        // The toast is the sheet's, not the page's under it.
        assertEquals(1, count(PARCEL_NOTE_TOAST))
        settle(6_500)
        assertEquals(listOf(gift to receipt.id), source.filesRemoved)
        assertEquals(listOf<DeliveryAnalytics.Outcome?>(DeliveryAnalytics.Outcome.Success), outcomes("parcel-file-remove"))
        assertTrue(exists(PARCEL_NOTE_SHEET))
    }
}
