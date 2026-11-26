package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DemoNote
import com.plhery.peekdeliverytracker.core.NoteFileError
import com.plhery.peekdeliverytracker.core.NoteFileProblem
import com.plhery.peekdeliverytracker.core.NoteFileUpload
import com.plhery.peekdeliverytracker.core.api.PackageFileType
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import java.io.IOException
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The demo's private note, on the record from Round Midnight Records, with the iPhone's bars: its card in the list with
 * the mark after its name, light and dark and at the largest Android text on a 360-wide phone; the card's menu; its
 * page with the note and its two files; its sheet, an account's and a guest's; and the sheet once a file was too big
 * and another waits for a connection.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelNoteScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private lateinit var source: FakeDeliveries

    private val record get() = DemoCards.parcels().first { it.trackingNumber == DemoNote.PARCEL }

    /** The demo's list with the iPhone's bars, in `session`, its stores as `prepare` leaves them. */
    private fun show(
        dark: Boolean = false,
        fontScale: Float = 1f,
        session: AuthenticationState = AuthenticationState.Demo,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        source = FakeDeliveries(DemoCards.parcels(), session).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = session, dark = dark, reduceMotion = false, source = source)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, (62 * scale).toInt(), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, (24 * scale).toInt()))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** Scrolls the list until the record's card begins `top` points down the window. */
    private fun scrollToTheRecord(top: Float) {
        val list = compose.onNodeWithTag(PARCEL_LIST)
        list.performScrollToNode(cardNamed(record.label))
        compose.waitForIdle()
        val now = compose.card(record.label).getBoundsInRoot().top.value
        val scale = view.resources.displayMetrics.density
        list.performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, (now - top) * scale) }
        compose.waitForIdle()
    }

    /** The record's page, landed, scrolled to its note: its card tapped clear of the demo's bar over the list. */
    private fun openThePage() {
        scrollToTheRecord(top = 300f)
        compose.card(record.label).performClick()
        settle()
        compose.onNodeWithTag(PARCEL_NOTE_CARD).performScrollTo()
        settle()
    }

    /** The record's note's sheet, from the pen on its card. */
    private fun openTheSheet() {
        openThePage()
        compose.onNodeWithTag(PARCEL_NOTE_EDIT).performSemanticsAction(SemanticsActions.OnClick)
        settle()
    }

    private fun theme(dark: Boolean) = if (dark) "dark" else "light"

    private fun list(dark: Boolean) {
        show(dark = dark)
        scrollToTheRecord(top = 300f)
        compose.screenshot("note_list_${theme(dark)}")
    }

    @Test
    fun theNotedCardInTheList() = list(dark = false)

    @Test
    fun theNotedCardInTheListInDark() = list(dark = true)

    /** On a 360-wide phone at Android's largest text, where the name wraps with its mark. */
    @Test
    @Config(qualifiers = "w360dp-h640dp-xhdpi")
    fun theNotedCardInTheListAtTheLargestTextOnASmallPhone() {
        show(fontScale = 2f)
        scrollToTheRecord(top = 190f)
        compose.screenshot("note_list_small_text2")
    }

    private fun menu(dark: Boolean) {
        show(dark = dark)
        scrollToTheRecord(top = 300f)
        compose.card(record.label).performTouchInput { longClick() }
        compose.waitForIdle()
        compose.screenshot("note_menu_${theme(dark)}")
    }

    @Test
    fun theNotedCardsMenu() = menu(dark = false)

    @Test
    fun theNotedCardsMenuInDark() = menu(dark = true)

    private fun page(dark: Boolean) {
        show(dark = dark)
        openThePage()
        compose.screenshot("note_page_${theme(dark)}")
    }

    @Test
    fun thePageWithTheNoteAndItsFiles() = page(dark = false)

    @Test
    fun thePageWithTheNoteAndItsFilesInDark() = page(dark = true)

    private fun sheet(dark: Boolean) {
        show(dark = dark)
        openTheSheet()
        compose.screenshot("note_sheet_${theme(dark)}")
    }

    @Test
    fun theSheet() = sheet(dark = false)

    @Test
    fun theSheetInDark() = sheet(dark = true)

    /** A guest's sheet, which says the note stays on this phone until they sign in. */
    private fun guestSheet(dark: Boolean) {
        show(dark = dark, session = AuthenticationState.Welcome) { isGuest = true }
        openTheSheet()
        compose.screenshot("note_sheet_guest_${theme(dark)}")
    }

    @Test
    fun aGuestsSheet() = guestSheet(dark = false)

    @Test
    fun aGuestsSheetInDark() = guestSheet(dark = true)

    /** A photo too big to take, said in a line of its own, and a PDF kept for when the phone is back online. */
    private fun problems(dark: Boolean) {
        show(dark = dark)
        openTheSheet()
        val parcel = source.parcels.value.first { it.trackingNumber == DemoNote.PARCEL }
        // Chosen together, as a new choice clears what the last one said: the photo refused as it is read, the PDF
        // stopped as it goes up.
        source.uploadFailure = IOException("offline")
        val picks = listOf(
            NotePick("Unboxing.jpg", pdf = false) { throw NoteFileError(NoteFileProblem.TooBig, size = 14_680_064) },
            NotePick("Delivery slip.pdf", pdf = true) { NoteFileUpload(DemoNote.receipt, "Delivery slip.pdf", PackageFileType.ApplicationPdf) },
        )
        compose.runOnUiThread { source.notes.add(source, parcel, picks) }
        settle()
        compose.screenshot("note_sheet_problems_${theme(dark)}")
    }

    @Test
    fun theSheetWithAFileTooBigAndOneWaitingForAConnection() = problems(dark = false)

    @Test
    fun theSheetWithAFileTooBigAndOneWaitingForAConnectionInDark() = problems(dark = true)
}
