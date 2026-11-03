package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.Dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.NotificationPreset
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ACTION_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.ACTION_ERROR_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_BELL
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_NOT_NOW
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_ON
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_TURN_ON
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS_DONE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS_EMAIL
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS_NOTIFICATIONS
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS_TITLE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_EMAIL
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_UPDATES
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import java.io.IOException
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * A parcel's alerts and the delivery email on this device's own window (B-S66-B-S68, B-SE04-B-SE06, B-ST35,
 * B-W01-B-W03): the page's bell signed in with the account's delivery email on, opening the parcel's alerts with the
 * preset in its words, both switches flipped, saved and found so when the sheet opens again; a flip that fails offline,
 * its alert over the sheet; the bell without the email, muting the parcel and saying so to TalkBack, as it still does
 * once the page is opened again from the list's card; and on a delivered parcel of an account that never chose, the
 * email's offer: Turn on saving then accepted, and Settings' delivery email on after it with the offer gone for good;
 * Not now; a failure offline; the envelope left out at the largest text. Each moment saved as
 * stateswalk/<row>-<moment>.png, with -dark, and -largest at the largest text, in the test package's external files,
 * which adb pulls. Signed in on fake deliveries ([FakeDeliveries], [ParcelWalk]) whose stores answer every read in
 * memory: nothing goes to the network. Each test keeps Zurich's time and the captures' moment as the JVM's do, under
 * Reduce Motion; the largest text is the composition's own Density at font scale 2, Android's largest step, which the
 * app reads as iOS's accessibility2, not the phone's setting. The zone is put back after each test, and the
 * preferences files the fakes write go once its window has.
 */
@RunWith(AndroidJUnit4::class)
class ParcelAlertsDeviceShots {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    private val delivered: Parcel get() = DemoCards.parcels().first { it.currentStage == TrackingStage.Delivered && it.archivedAt == null }

    private fun show(prepare: FakeDeliveries.() -> Unit) = walk.show(DemoCards.parcels(), prepare = prepare)

    /** What TalkBack reads for the page's bell. */
    private fun bell(): String? = walk.node(PAGE_BELL).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull()

    private fun mutes(): List<Pair<UUID, Boolean>> = walk.onUi { walk.source.mutes.toList() }

    private fun emailMutes(): List<Pair<UUID, Boolean>> = walk.onUi { walk.source.emailMutes.toList() }

    private fun emailChoices(): List<Boolean> = walk.onUi { walk.source.emailChoices.toList() }

    private fun offers(): List<String> = walk.onUi { walk.source.actions.filter { it.startsWith("email-offer") } }

    /** The gift's alerts, from its page's bell. */
    private fun openAlerts() {
        walk.open(gift)
        walk.tap(PAGE_BELL)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_ALERTS) }
        walk.awaitStill()
    }

    /** The delivered parcel's page, scrolled to the email's offer. */
    private fun openOffer() {
        walk.open(delivered)
        walk.node(PAGE_EMAIL_OFFER).performScrollTo()
        walk.settle()
    }

    private fun titleInset(): Dp {
        val title = compose.onNode(hasText("An email when the next one arrives?"), useUnmergedTree = true).getUnclippedBoundsInRoot()
        return title.left - walk.node(PAGE_EMAIL_OFFER).getUnclippedBoundsInRoot().left
    }

    /**
     * B-S66, B-S67, B-SE05, B-ST35, B-W02: with the account's delivery email on the bell opens the parcel's alerts,
     * the preset and the address in their words; each switch saves its alert, and the sheet opened again shows them so.
     */
    @Test
    fun withTheEmailTheBellOpensTheAlertsAndBothSwitchesSave() {
        show { signedInWithEmail(true, stages = NotificationPreset.DeliveryDay.stages) }
        walk.open(gift)
        assertEquals(walk.text("email.parcel.open"), bell())
        openAlerts()
        assertTrue(walk.onUi { walk.deliveries.modal })
        assertTrue(walk.shows(PARCEL_ALERTS_TITLE, "Notifications for Birthday gift"))
        assertTrue(walk.shows(PARCEL_ALERTS_NOTIFICATIONS, "Delivery only, as in Settings"))
        assertTrue(walk.shows(PARCEL_ALERTS_EMAIL, "To reader@example.com"))
        assertEquals(ToggleableState.On, walk.toggle(PARCEL_ALERTS_NOTIFICATIONS))
        assertEquals(ToggleableState.On, walk.toggle(PARCEL_ALERTS_EMAIL))
        assertTrue(mutes().isEmpty() && emailMutes().isEmpty())
        walk.shot("B-S66-alerts")

        walk.flip(PARCEL_ALERTS_NOTIFICATIONS)
        compose.waitUntil(LONG_MILLIS) { mutes() == listOf(gift.id to true) }
        walk.settle()
        assertEquals(ToggleableState.Off, walk.toggle(PARCEL_ALERTS_NOTIFICATIONS))
        walk.node(PARCEL_ALERTS_EMAIL).assertIsEnabled()
        walk.flip(PARCEL_ALERTS_EMAIL)
        compose.waitUntil(LONG_MILLIS) { emailMutes() == listOf(gift.id to true) }
        walk.settle()
        assertEquals(ToggleableState.Off, walk.toggle(PARCEL_ALERTS_EMAIL))
        walk.shot("B-S67-both-off")

        walk.tap(PARCEL_ALERTS_DONE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(PARCEL_ALERTS) }
        assertFalse(walk.onUi { walk.deliveries.modal })
        walk.tap(PAGE_BELL)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_ALERTS) }
        walk.awaitStill()
        assertEquals(ToggleableState.Off, walk.toggle(PARCEL_ALERTS_NOTIFICATIONS))
        assertEquals(ToggleableState.Off, walk.toggle(PARCEL_ALERTS_EMAIL))
        walk.shot("B-SE05-reopened")

        walk.flip(PARCEL_ALERTS_NOTIFICATIONS)
        compose.waitUntil(LONG_MILLIS) { mutes() == listOf(gift.id to true, gift.id to false) }
        walk.settle()
        walk.flip(PARCEL_ALERTS_EMAIL)
        compose.waitUntil(LONG_MILLIS) { emailMutes() == listOf(gift.id to true, gift.id to false) }
        walk.settle()
        assertEquals(ToggleableState.On, walk.toggle(PARCEL_ALERTS_NOTIFICATIONS))
        assertEquals(ToggleableState.On, walk.toggle(PARCEL_ALERTS_EMAIL))
    }

    /** B-S67: a flip that fails offline says why in an alert over the sheet; the switch then shows what was saved. */
    @Test
    fun aFlipThatFailsOfflineSaysWhyOverTheSheet() {
        show { signedInWithEmail(true) }
        openAlerts()
        walk.onUi {
            walk.source.muting = CompletableDeferred()
            walk.source.failure = IOException("offline")
        }
        walk.flip(PARCEL_ALERTS_NOTIFICATIONS)
        walk.node(PARCEL_ALERTS_DONE).performClick()
        walk.onUi { walk.source.muting?.complete(Unit) }
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(ACTION_ERROR) }
        walk.awaitStill()
        assertTrue(walk.shows(ACTION_ERROR, walk.text("error.connection")))
        // The sheet stays under the alert, drawn but left out of what TalkBack reaches.
        assertTrue(walk.exists(SHEET_PANEL))
        assertFalse(walk.reaches(PARCEL_ALERTS))
        walk.shot("B-S67-failed")

        walk.tap(ACTION_ERROR_CLOSE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ACTION_ERROR) && walk.reaches(PARCEL_ALERTS) }
        assertEquals(ToggleableState.On, walk.toggle(PARCEL_ALERTS_NOTIFICATIONS))
        walk.node(PARCEL_ALERTS_NOTIFICATIONS).assertIsEnabled()
        assertTrue(mutes().isEmpty())
    }

    /**
     * B-S66, B-SE04, B-W01: without the delivery email the bell mutes the parcel, TalkBack told what it does next, and
     * the page opened again from the list's card agrees; a second tap turns the notifications back on.
     */
    @Test
    fun withoutTheEmailTheBellMutesTheParcelAndThePageOpenedAgainAgrees() {
        show { signedInWithEmail(false) }
        walk.open(gift)
        assertEquals(walk.text("detail.mute"), bell())
        walk.shot("B-W01-bell")

        walk.tap(PAGE_BELL)
        compose.waitUntil(LONG_MILLIS) { mutes() == listOf(gift.id to true) }
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { bell() == walk.text("detail.unmute") }
        assertFalse(walk.exists(PARCEL_ALERTS))
        assertFalse(walk.onUi { walk.deliveries.modal })
        walk.shot("B-SE04-muted")

        // Another parcel's page, then the gift's again from its card.
        walk.open(delivered)
        walk.open(gift)
        assertEquals(walk.text("detail.unmute"), bell())
        walk.tap(PAGE_BELL)
        compose.waitUntil(LONG_MILLIS) { mutes() == listOf(gift.id to true, gift.id to false) }
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { bell() == walk.text("detail.mute") }
        walk.shot("B-SE04-unmuted", alsoDark = false)
    }

    /**
     * B-S68, B-SE06, B-W03, B-ST35: on a delivered parcel of an account that never chose, Turn on saves (both answers
     * wait, Turn on spinning), then says the email is on, as TalkBack hears; Settings then has the delivery email on,
     * and the offer doesn't come back.
     */
    @Test
    fun turnOnSavesThenSettingsHasTheEmailOnAndTheOfferNeverReturns() {
        show {
            signedInWithEmail(null)
            choosing = CompletableDeferred()
        }
        openOffer()
        assertTrue(walk.shows(PAGE_EMAIL_OFFER, "An email when the next one arrives?"))
        assertTrue(walk.shows(PAGE_EMAIL_OFFER, "A short email to reader@example.com"))
        val title = compose.onNode(hasText("An email when the next one arrives?") and hasAnyAncestor(hasTestTag(PAGE_EMAIL_OFFER)), useUnmergedTree = true)
            .fetchSemanticsNode()
        assertTrue(SemanticsProperties.Heading in title.config)
        assertFalse(walk.exists(PAGE_EMAIL_OFFER_ERROR))
        walk.shot("B-S68-offer")

        walk.tap(PAGE_EMAIL_OFFER_TURN_ON)
        walk.settle()
        assertEquals(listOf("email-offer-accept"), offers())
        walk.node(PAGE_EMAIL_OFFER_TURN_ON).assertIsNotEnabled()
        walk.node(PAGE_EMAIL_OFFER_NOT_NOW).assertIsNotEnabled()
        assertTrue(walk.spins(PAGE_EMAIL_OFFER_TURN_ON))
        assertFalse(walk.spins(PAGE_EMAIL_OFFER_NOT_NOW))
        walk.shot("B-S68-saving")

        walk.onUi { walk.source.choosing?.complete(Unit) }
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_EMAIL_OFFER_ON) && !walk.exists(PAGE_EMAIL_OFFER) }
        assertEquals(listOf(true), emailChoices())
        assertTrue(walk.heard("Email is on. You can change it in Settings › Notifications."))
        walk.settle()
        walk.shot("B-S68-accepted")

        if (walk.stacked()) walk.home()
        compose.onAllNodes(hasTestTag(ACCOUNT_BUTTON)).onFirst().performClick()
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SHEET) }
        walk.awaitStill()
        walk.tap(SETTINGS_DELIVERY_UPDATES)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_DELIVERY_EMAIL) }
        walk.awaitStill()
        assertEquals(ToggleableState.On, walk.toggle(SETTINGS_DELIVERY_EMAIL))
        walk.shot("B-SE06-settings")

        repeat(3) { if (walk.exists(SETTINGS_SHEET)) walk.back() }
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SETTINGS_SHEET) }
        walk.open(delivered)
        assertFalse(walk.exists(PAGE_EMAIL_OFFER))
        walk.shot("B-SE06-no-offer", alsoDark = false)
    }

    /** B-S68: Not now is saved once and the offer leaves without the line. */
    @Test
    fun notNowIsSavedAndTheOfferLeaves() {
        show { signedInWithEmail(null) }
        openOffer()
        walk.tap(PAGE_EMAIL_OFFER_NOT_NOW)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(PAGE_EMAIL_OFFER) }
        assertEquals(listOf("email-offer-decline"), offers())
        assertEquals(listOf(false), emailChoices())
        assertFalse(walk.exists(PAGE_EMAIL_OFFER_ON))
        walk.shot("B-S68-not-now")
    }

    /** B-S68: offline, Turn on fails: the offer says so, as TalkBack hears, and can be answered again. */
    @Test
    fun aTurnOnThatFailsOfflineSaysSo() {
        show { signedInWithEmail(null) }
        openOffer()
        walk.onUi { walk.source.failure = IOException("offline") }
        walk.tap(PAGE_EMAIL_OFFER_TURN_ON)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_EMAIL_OFFER_ERROR) }
        assertTrue(walk.shows(PAGE_EMAIL_OFFER_ERROR, "Couldn’t save your email setting. Try again."))
        assertTrue(walk.heard("Couldn’t save your email setting. Try again."))
        walk.node(PAGE_EMAIL_OFFER_TURN_ON).assertIsEnabled()
        walk.node(PAGE_EMAIL_OFFER_NOT_NOW).assertIsEnabled()
        assertTrue(emailChoices().isEmpty())
        walk.shot("B-S68-failed")
    }

    /** B-S68: the envelope stands beside the offer's title, and is left out at the largest text. */
    @Test
    fun atTheLargestTextTheOffersEnvelopeIsLeftOut() {
        show { signedInWithEmail(null) }
        openOffer()
        // 16 in, the envelope's tile 40, then 12.
        assertEquals(68f, titleInset().value, 1f)
        walk.fontScale = 2f
        walk.recompose()
        walk.settle()
        walk.node(PAGE_EMAIL_OFFER).performScrollTo()
        walk.settle()
        assertEquals(16f, titleInset().value, 1f)
        walk.shot("B-S68-offer")
    }

    /** B-S66, B-ST35: the parcel's alerts at the largest text. */
    @Test
    fun theAlertsAtTheLargestText() {
        walk.fontScale = 2f
        show { signedInWithEmail(true, stages = NotificationPreset.DeliveryDay.stages) }
        openAlerts()
        assertTrue(walk.shows(PARCEL_ALERTS_NOTIFICATIONS, "Delivery only, as in Settings"))
        assertTrue(walk.shows(PARCEL_ALERTS_EMAIL, "To reader@example.com"))
        walk.shot("B-S66-alerts")
    }
}
