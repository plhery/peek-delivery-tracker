package com.plhery.peekdeliverytracker.ui

import android.os.Vibrator
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_LINE_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ADD_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_CHIP
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_NOT_QUITE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_REASON
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WORDS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_NOT_NOW
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_ON
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_TURN_ON
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelFeedbackWork
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What the phone's vibrator is asked to play on a parcel's page and in Add where the iPhone gives feedback (B-H04,
 * B-H05, B-H08): the delivery email turned on from the delivered parcel's offer, the carrier Add finds as a number is
 * typed, and a reason chosen and taken off in the feedback's words sheet. Read as HapticsDeviceTest reads them, from the
 * vibrator service's history ([Vibrations]); each step's records are saved as pagehaptics/<row>-<step>.txt in the test
 * package's external files, which adb pulls. Signed in as the synthetic reader@example.com on fake deliveries
 * ([ParcelWalk]), which reach no server; each test keeps Zurich's time, and the phone's touch feedback and the
 * preferences files the fakes write are put back after it. Skipped on a device without a vibrator.
 */
@RunWith(AndroidJUnit4::class)
class PageHapticsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val touchFeedback = TouchFeedback(compose)

    @get:Rule
    val rules: RuleChain =
        RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(touchFeedback).around(walk)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val vibrator: Vibrator = instrumentation.targetContext.getSystemService(VibratorManager::class.java).defaultVibrator
    private val vibrations = Vibrations(compose, DeviceShots(compose, FOLDER).folder, vibrator)

    private val delivered: Parcel get() = DemoCards.parcels().first { it.currentStage == TrackingStage.Delivered && it.archivedAt == null }

    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")

    @Before
    fun aVibrator() {
        assumeTrue("No vibrator to ask", vibrator.hasVibrator())
    }

    // B-H04: the delivery email.

    /**
     * B-H04: Turn on in the delivered parcel's offer, the server saying the email is on, plays one success (CONFIRM) as
     * the line saying so comes in (`Haptics.success()` once `emailOffer = .accepted`); nothing as the page opens.
     */
    @Test
    fun turnOnPlaysOneSuccessOnceTheServerSaysTheEmailIsOn() {
        openOffer()
        answer(PAGE_EMAIL_OFFER_TURN_ON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_EMAIL_OFFER_ON) }
        vibrations.assertOne("B-H04-turned-on", vibrations.success())
        assertEquals(listOf(true), walk.onUi { walk.source.emailChoices.toList() })
        walk.shot("B-H04-turned-on", alsoDark = false)
    }

    /** B-H04: Turn on that the server leaves off (`accepted && enabled` false) puts the offer back, no line, nothing played. */
    @Test
    fun turnOnTheServerLeavesOffPlaysNothing() {
        openOffer { emailRefused = true }
        answer(PAGE_EMAIL_OFFER_TURN_ON)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.emailChoices.toList() } == listOf(true) }
        walk.settle()
        assertFalse("No line where the server left the email off", walk.exists(PAGE_EMAIL_OFFER_ON))
        vibrations.assertNone("B-H04-left-off")
    }

    /** B-H04: Turn on whose save fails says so under the offer and plays nothing. */
    @Test
    fun turnOnThatFailsPlaysNothing() {
        openOffer()
        walk.onUi { walk.source.failure = IllegalStateException("Synthetic failure") }
        answer(PAGE_EMAIL_OFFER_TURN_ON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_EMAIL_OFFER_ERROR) }
        assertFalse(walk.exists(PAGE_EMAIL_OFFER_ON))
        vibrations.assertNone("B-H04-failed")
    }

    /** B-H04: Not now takes the offer away and plays nothing. */
    @Test
    fun notNowPlaysNothing() {
        openOffer()
        answer(PAGE_EMAIL_OFFER_NOT_NOW)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(PAGE_EMAIL_OFFER) }
        assertEquals(listOf(false), walk.onUi { walk.source.emailChoices.toList() })
        vibrations.assertNone("B-H04-not-now")
    }

    /** The delivered parcel's page, its offer scrolled to and at rest, the stores set up by `prepare`; then the mark. */
    private fun openOffer(prepare: FakeDeliveries.() -> Unit = {}) {
        walk.show(DemoCards.parcels()) {
            signedInWithEmail(null)
            prepare()
        }
        walk.open(delivered)
        walk.node(PAGE_EMAIL_OFFER).performScrollTo()
        walk.node(PAGE_EMAIL_OFFER_TURN_ON).performScrollTo()
        walk.settle()
        vibrations.mark()
    }

    private fun answer(button: String) {
        walk.node(button).performClick()
        walk.settle()
    }

    // B-H05: the carrier Add finds.

    /**
     * B-H05: `.sensoryFeedback(.selection, trigger: resolvedCarrier) { old, new in old != new && new != .unknown }`: a
     * UPS number typed into Add plays one selection as UPS is found; the same number spaced out, still UPS, nothing;
     * the field emptied, the carrier unknown, nothing; the number again, one; a J&T Mexico number in its place, another
     * carrier, one.
     */
    @Test
    fun eachCarrierAddFindsPlaysOneSelectionAndTheSameOneOrNoneNothing() {
        walk.show(listOf(lamp))
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        walk.awaitKeyboard(required = false)
        walk.settle()
        vibrations.mark()

        type(UPS_SAMPLE)
        val ups = checkNotNull(carrier()) { "A carrier found for the UPS sample" }
        vibrations.assertOne("B-H05-ups", vibrations.selection())

        type(UPS_SPACED)
        assertEquals("The spaced number is UPS's still", ups, carrier())
        vibrations.assertNone("B-H05-ups-spaced")

        type("")
        assertNull("No carrier for an empty field", carrier())
        vibrations.assertNone("B-H05-cleared")

        type(UPS_SAMPLE)
        assertEquals(ups, carrier())
        vibrations.assertOne("B-H05-ups-again", vibrations.selection())

        type(JT_MEXICO)
        val other = checkNotNull(carrier()) { "A carrier found for the J&T Mexico number" }
        assertNotEquals(ups, other)
        vibrations.assertOne("B-H05-another-carrier", vibrations.selection())
        walk.shot("B-H05-another-carrier", alsoDark = false)
    }

    private fun type(text: String) {
        walk.node(ADD_TRACKING).performTextReplacement(text)
        walk.settle()
    }

    /** The carrier's name on Add's carrier line, null where the line is not shown or offers to find one ("add.detect"). */
    private fun carrier(): String? = compose.onAllNodes(hasTestTag(ADD_LINE_NAME), useUnmergedTree = true).fetchSemanticsNodes()
        .firstOrNull()?.config?.getOrNull(SemanticsProperties.Text)?.joinToString { it.text }
        ?.takeIf { it.isNotEmpty() && it != walk.text("add.detect") }

    // B-H08: the feedback's reasons.

    /**
     * B-H08: in the feedback's words sheet a reason chip tapped on plays one selection (`Haptics.selection()` in
     * `chip(_:)`), and tapped off another; a reason chosen on the page's question before the sheet plays nothing, as
     * the iPhone's doesn't.
     */
    @Test
    fun aReasonChipTappedOnAndOffPlaysOneSelectionEach() {
        walk.reduceMotion = false
        walk.show(listOf(gift, lamp))
        walk.open(gift)
        walk.settle()
        press(FEEDBACK_NOT_QUITE)
        vibrations.mark()
        press(FEEDBACK_REASON + ParcelFeedbackReason.Steps.name)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_ADD_NOTE) }
        walk.settle()
        vibrations.assertNone("B-H08-question-reason")

        press(FEEDBACK_ADD_NOTE)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_WORDS_SHEET) }
        walk.settle()
        walk.awaitStill()
        assertEquals(setOf(ParcelFeedbackReason.Steps), chosen())
        val reason = checkNotNull(walk.onUi { work().page?.reasons }?.firstOrNull { it != ParcelFeedbackReason.Steps }) { "Another reason" }
        val chip = FEEDBACK_CHIP + reason.name
        vibrations.mark()

        press(chip)
        assertEquals(setOf(ParcelFeedbackReason.Steps, reason), chosen())
        vibrations.assertOne("B-H08-chip-on", vibrations.selection())

        press(chip)
        assertEquals(setOf(ParcelFeedbackReason.Steps), chosen())
        vibrations.assertOne("B-H08-chip-off", vibrations.selection())
        assertNotNull(walk.onUi { work().words })
        assertTrue(walk.exists(FEEDBACK_WORDS_SHEET))
    }

    private fun work(): ParcelFeedbackWork = checkNotNull(walk.source.feedbacks.of(gift.id)) { "No question on the gift" }

    private fun chosen(): Set<ParcelFeedbackReason> = walk.onUi { work().reasons }

    /** A tap on what has `tag` as TalkBack's does, scrolled to first. */
    private fun press(tag: String) {
        compose.reveal(tag)
        walk.node(tag).performClick()
        walk.settle()
    }

    private companion object {
        const val FOLDER = "pagehaptics"

        /** The public UPS sample. */
        const val UPS_SAMPLE = "1Z999AA10123456784"

        /** The same, spaced out as a label prints it. */
        const val UPS_SPACED = "1Z 999 AA1 01234 5678 4"

        /** A synthetic J&T Mexico number. */
        const val JT_MEXICO = "JMX000000000001"
    }
}
