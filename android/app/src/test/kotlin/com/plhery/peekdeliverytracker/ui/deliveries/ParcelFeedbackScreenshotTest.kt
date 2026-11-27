package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import java.io.IOException
import java.util.UUID
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The question under a parcel's page, with the iPhone's bars: Pip asking, offering the reasons, thanking for a "Yes",
 * for a reason with the way to a note, and for a note; kept quiet on a later visit after a "Yes"; the row asked where
 * no carrier was found, as it asks, after "Not yet" and once a carrier was named; the words and carrier sheets with
 * what was written; the question asked at the page's foot on the way back from the carrier's site, and an answer that
 * couldn't go. Light and dark, and at the largest Android text size, under Reduce Motion so each stands at rest; Pip
 * asking, the reasons and the thanks for a "Yes" also with motion, once their springs, rows and tick have settled where
 * Reduce Motion puts them at once.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelFeedbackScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState
    private lateinit var source: FakeDeliveries

    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 10), email = "reader@example.com"))

    /** A parcel its carrier answers for: Pip asks about it. */
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    /** A parcel no carrier was found for: the row asks about it. */
    private val lamp: Parcel get() = DemoCards.probable()

    /** The page of `parcel` with the window's bars, scrolled to its end, where the question is, unless `top`. */
    private fun show(
        parcel: Parcel = gift,
        dark: Boolean = false,
        fontScale: Float = 1f,
        top: Boolean = false,
        reduceMotion: Boolean = true,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        source = FakeDeliveries(listOf(gift, lamp), reader).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = reader, dark = dark, reduceMotion = reduceMotion, source = source, identities = pinnedIdentities)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            fun px(points: Int) = (points * scale).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(62), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, px(24)))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
        compose.runOnUiThread { deliveries.navigator.open(parcel.id) }
        settle()
        if (!top) toTheEnd()
    }

    /** The page scrolled to its end, where the question stands, unless a sheet is over it. */
    private fun toTheEnd() {
        if (compose.onAllNodes(hasTestTag(PAGE_CONTENT)).fetchSemanticsNodes().isEmpty()) return
        compose.onNodeWithTag(PAGE_CONTENT).performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, 100_000f) }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        settle()
        toTheEnd()
    }

    private fun work(parcel: Parcel = gift): ParcelFeedbackWork = source.feedbacks.of(parcel.id)!!

    /** On `work`, then settled. */
    private fun doing(parcel: Parcel = gift, action: ParcelFeedbackWork.() -> Unit) {
        compose.runOnUiThread { work(parcel).action() }
        settle()
    }

    /** The reader opened the carrier's site from the page and came back to it two seconds later. */
    private fun backFromTheCarriersSite() = doing {
        visited("Chronopost")
        left()
        source.now = source.now.plusSeconds(2)
        returned(false)
    }

    // Pip's question.

    @Test
    fun pipAsks() {
        show()
        compose.screenshot("parcel_feedback_ask_light")
    }

    @Test
    fun pipAsksWithMotion() {
        show(reduceMotion = false)
        settle()
        compose.screenshot("parcel_feedback_ask_motion_light")
    }

    @Test
    fun pipAsksInDark() {
        show(dark = true)
        compose.screenshot("parcel_feedback_ask_dark")
    }

    @Test
    fun theReasons() {
        show()
        tap(FEEDBACK_NOT_QUITE)
        compose.screenshot("parcel_feedback_reasons_light")
    }

    @Test
    fun theReasonsWithMotion() {
        show(reduceMotion = false)
        tap(FEEDBACK_NOT_QUITE)
        settle()
        compose.screenshot("parcel_feedback_reasons_motion_light")
    }

    @Test
    fun theReasonsInDark() {
        show(dark = true)
        tap(FEEDBACK_NOT_QUITE)
        compose.screenshot("parcel_feedback_reasons_dark")
    }

    @Test
    fun theReasonsAtTheLargestTextSize() {
        show(fontScale = 2f)
        tap(FEEDBACK_NOT_QUITE)
        compose.screenshot("parcel_feedback_reasons_ax")
    }

    @Test
    fun thankedForAYes() {
        show()
        tap(FEEDBACK_YES)
        compose.screenshot("parcel_feedback_right_light")
    }

    /** The page opened again after a "Yes" about the scan it still ends on. */
    private fun quiet() {
        tap(FEEDBACK_YES)
        compose.runOnUiThread { deliveries.navigator.pop(emptyList()) }
        settle()
        compose.runOnUiThread { deliveries.navigator.open(gift.id) }
        settle()
        toTheEnd()
        check(work().moment == ParcelFeedbackModel.Moment.Quiet)
    }

    @Test
    fun keptQuietAfterAYes() {
        show()
        quiet()
        compose.screenshot("parcel_feedback_quiet_light")
    }

    @Test
    fun keptQuietAfterAYesInDark() {
        show(dark = true)
        quiet()
        compose.screenshot("parcel_feedback_quiet_dark")
    }

    @Test
    fun thankedForAYesWithMotion() {
        show(reduceMotion = false)
        tap(FEEDBACK_YES)
        settle()
        compose.screenshot("parcel_feedback_right_motion_light")
    }

    @Test
    fun thankedForAReasonWithTheWayToANote() {
        show()
        tap(FEEDBACK_NOT_QUITE)
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        compose.screenshot("parcel_feedback_sent_light")
    }

    @Test
    fun thankedForANote() {
        show()
        tap(FEEDBACK_NOT_QUITE)
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        tap(FEEDBACK_ADD_NOTE)
        doing {
            noteField.setTextAndPlaceCursorAtEnd("The last step came a day late.")
            sendWords()
        }
        compose.screenshot("parcel_feedback_noted_light")
    }

    /**
     * "I haven't received it", first among the reasons: the next step for a parcel still on its way (ask the seller or
     * contact Chronopost), Chronopost's help page and the way to a note.
     */
    @Test
    fun notReceivedGivesTheNextStep() {
        show()
        tap(FEEDBACK_NOT_QUITE)
        tap(FEEDBACK_NOT_RECEIVED)
        compose.screenshot("parcel_feedback_missing_light")
    }

    /** Its note: the sheet takes the note alone, and the step stays with a thanks in place of the way to it. */
    @Test
    fun notReceivedWithANote() {
        show(dark = true)
        tap(FEEDBACK_NOT_QUITE)
        tap(FEEDBACK_NOT_RECEIVED)
        tap(FEEDBACK_ADD_NOTE)
        doing { noteField.setTextAndPlaceCursorAtEnd("Nothing yet, and the seller hasn't answered.") }
        compose.screenshot("parcel_feedback_missing_sheet_dark")
        doing { sendWords() }
        toTheEnd()
        compose.screenshot("parcel_feedback_missing_noted_dark")
    }

    // The row asked where no carrier was found.

    @Test
    fun theRowAsks() {
        show(lamp)
        compose.screenshot("parcel_feedback_unknown_ask_light")
    }

    @Test
    fun theRowAsksInDark() {
        show(lamp, dark = true)
        compose.screenshot("parcel_feedback_unknown_ask_dark")
    }

    @Test
    fun theRowAfterNotYet() {
        show(lamp)
        tap(FEEDBACK_NOT_YET)
        compose.screenshot("parcel_feedback_unknown_early_light")
    }

    @Test
    fun theRowOnceACarrierIsNamed() {
        show(lamp)
        tap(FEEDBACK_YES)
        doing(lamp) {
            whoField.setTextAndPlaceCursorAtEnd("Parcel Line")
            addressField.setTextAndPlaceCursorAtEnd("https://example.com/track")
            sendCarrier()
        }
        compose.screenshot("parcel_feedback_unknown_named_light")
    }

    // The sheets.

    /** The words sheet opened from the thanks for a reason, with another chosen and a note written, the keyboard gone. */
    private fun wordsSheet() {
        tap(FEEDBACK_NOT_QUITE)
        tap(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        tap(FEEDBACK_ADD_NOTE)
        doing {
            toggle(ParcelFeedbackReason.Arrived)
            noteField.setTextAndPlaceCursorAtEnd("It says delivered, but nothing came.")
        }
    }

    @Test
    fun theWordsSheet() {
        show()
        wordsSheet()
        compose.screenshot("parcel_feedback_words_sheet_light")
    }

    @Test
    fun theWordsSheetInDark() {
        show(dark = true)
        wordsSheet()
        compose.screenshot("parcel_feedback_words_sheet_dark")
    }

    @Test
    fun theWordsSheetAtTheLargestTextSize() {
        show(fontScale = 2f)
        wordsSheet()
        compose.screenshot("parcel_feedback_words_sheet_ax")
    }

    /** The carrier sheet opened from the row's "Yes", with a carrier and its site written, the keyboard gone. */
    private fun carrierSheet() {
        tap(FEEDBACK_YES)
        doing(lamp) {
            whoField.setTextAndPlaceCursorAtEnd("Parcel Line")
            addressField.setTextAndPlaceCursorAtEnd("https://example.com/track")
        }
    }

    @Test
    fun theCarrierSheet() {
        show(lamp)
        carrierSheet()
        compose.screenshot("parcel_feedback_carrier_sheet_light")
    }

    @Test
    fun theCarrierSheetInDark() {
        show(lamp, dark = true)
        carrierSheet()
        compose.screenshot("parcel_feedback_carrier_sheet_dark")
    }

    @Test
    fun theCarrierSheetAtTheLargestTextSize() {
        show(lamp, fontScale = 2f)
        carrierSheet()
        compose.screenshot("parcel_feedback_carrier_sheet_ax")
    }

    // At the page's foot.

    @Test
    fun theWayBack() {
        show(top = true)
        backFromTheCarriersSite()
        compose.screenshot("parcel_feedback_toast_back_light")
    }

    @Test
    fun theWayBackInDark() {
        show(dark = true, top = true)
        backFromTheCarriersSite()
        compose.screenshot("parcel_feedback_toast_back_dark")
    }

    @Test
    fun theWayBackAtTheLargestTextSize() {
        show(fontScale = 2f, top = true)
        backFromTheCarriersSite()
        compose.screenshot("parcel_feedback_toast_back_ax")
    }

    @Test
    fun thankedOnTheWayBack() {
        show(top = true)
        backFromTheCarriersSite()
        compose.onNodeWithTag(FEEDBACK_BACK_YES).performClick()
        settle()
        compose.screenshot("parcel_feedback_toast_thanks_light")
    }

    @Test
    fun anAnswerThatCouldNotGo() {
        show { feedbackFailure = IOException("offline") }
        tap(FEEDBACK_YES)
        compose.screenshot("parcel_feedback_toast_failed_light")
    }
}
