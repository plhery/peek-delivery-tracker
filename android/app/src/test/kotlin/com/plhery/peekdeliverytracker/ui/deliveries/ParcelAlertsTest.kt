package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.click
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.accessibility.AccessibilityManager
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.test.onRoot
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.util.UUID
import org.robolectric.Shadows.shadowOf
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.NotificationPreset
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A parcel's alerts (B-S66, B-S67, B-ST35, B-W02, B-W03, B-SE04–B-SE06): opened from its page's bell while the
 * account's email is on and its preferences have come, the two switches saving one at a time and showing where they
 * were flipped meanwhile, Done waiting for the save, the failure's alert over the sheet, the sheet closing itself once
 * the bell would no longer open it, the window made again, and what TalkBack reads.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelAlertsTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    private val gift get() = DemoCards.parcel("Birthday gift").id

    /** The account the tests sign in with: the demo has none, and so no delivery email. */
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** The demo's parcels in an account, its email on unless `prepare` says otherwise, made again by `recreation`. */
    private fun show(fontScale: Float = 1f, recreation: Recreation? = null, prepare: FakeDeliveries.() -> Unit = { signedInWithEmail(true) }) {
        source = FakeDeliveries(DemoCards.parcels(), reader).apply(prepare)
        compose.waitsForMaps()
        val content: @Composable () -> Unit = {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = reader, source = source, reduceMotion = false)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** The gift's page, then its bell. */
    private fun openAlerts() {
        compose.openCard("Birthday gift")
        settle()
        node(PAGE_BELL).performClick()
        settle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun state(tag: String): ToggleableState? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ToggleableState)

    /** A tap on the row's switch, at its trailing end. */
    private fun flip(tag: String) {
        node(tag).performTouchInput { click(Offset(width - 45.dp.toPx(), height / 2f)) }
        compose.waitForIdle()
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    private fun doneSpins(): Boolean = compose.onAllNodes(
        hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate) and hasAnyAncestor(hasTestTag(PARCEL_ALERTS_DONE)),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    @Test
    fun theBellOpensTheParcelsAlertsWithItsNameTheAccountsPresetAndTheAddress() {
        show { signedInWithEmail(true, address = "reader@example.com", stages = NotificationPreset.DeliveryDay.stages) }
        openAlerts()
        assertTrue(exists(PARCEL_ALERTS))
        compose.onNode(hasTestTag(PARCEL_ALERTS_TITLE) and hasText("Notifications for Birthday gift", substring = true)).assertExists()
        compose.onNode(hasTestTag(PARCEL_ALERTS_NOTIFICATIONS) and hasText("Delivery only, as in Settings")).assertExists()
        compose.onNode(hasTestTag(PARCEL_ALERTS_EMAIL) and hasText("To reader@example.com")).assertExists()
        compose.onNode(hasText("Switching one off here only mutes this parcel.", substring = true)).assertExists()
        assertEquals(ToggleableState.On, state(PARCEL_ALERTS_NOTIFICATIONS))
        assertEquals(ToggleableState.On, state(PARCEL_ALERTS_EMAIL))
        // Nothing is saved by opening them.
        assertEquals(emptyList<Any>(), source.mutes + source.emailMutes)
    }

    @Test
    fun withoutTheAccountsPreferencesTheBellOpensNothing() {
        show()
        compose.openCard("Birthday gift")
        settle()
        // The store has let go of the account's preferences (signing out); the email the page reads still says on for
        // the frame before it follows, so the bell still offers the alerts.
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { source.notificationPreferences.value = null }
        node(PAGE_BELL).performClick()
        assertEquals(null, deliveries.alerts)
        assertFalse(deliveries.modal)
        compose.runOnUiThread { source.deliveryEmail.value = null }
        compose.mainClock.autoAdvance = true
        settle()
        assertFalse(exists(PARCEL_ALERTS))
        assertFalse(deliveries.modal)
        assertEquals(emptyList<Any>(), source.mutes)
    }

    @Test
    fun eachSwitchSavesItsAlertForTheParcelOnceShowingWhereItWasFlippedWhileItSaves() {
        show()
        openAlerts()
        source.muting = CompletableDeferred()
        flip(PARCEL_ALERTS_NOTIFICATIONS)
        assertEquals(ToggleableState.Off, state(PARCEL_ALERTS_NOTIFICATIONS))
        node(PARCEL_ALERTS_NOTIFICATIONS).assertIsNotEnabled()
        node(PARCEL_ALERTS_EMAIL).assertIsNotEnabled()
        // One save at a time: the other switch waits.
        node(PARCEL_ALERTS_EMAIL).performSemanticsAction(SemanticsActions.OnClick)
        flip(PARCEL_ALERTS_EMAIL)
        settle()
        assertEquals(ToggleableState.On, state(PARCEL_ALERTS_EMAIL))
        source.muting?.complete(Unit)
        settle()
        assertEquals(listOf(gift to true), source.mutes)
        assertEquals(emptyList<Any>(), source.emailMutes)
        assertEquals(ToggleableState.Off, state(PARCEL_ALERTS_NOTIFICATIONS))
        node(PARCEL_ALERTS_EMAIL).assertIsEnabled()

        source.muting = null
        node(PARCEL_ALERTS_EMAIL).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        assertEquals(listOf(gift to true), source.emailMutes)
        assertEquals(ToggleableState.Off, state(PARCEL_ALERTS_EMAIL))
        flip(PARCEL_ALERTS_NOTIFICATIONS)
        settle()
        assertEquals(listOf(gift to true, gift to false), source.mutes)
        assertEquals(ToggleableState.On, state(PARCEL_ALERTS_NOTIFICATIONS))
        assertTrue(exists(PARCEL_ALERTS))
    }

    @Test
    fun doneWaitsForTheSaveInFlightAndNothingElseSendsTheSheetAwayMeanwhile() {
        show()
        openAlerts()
        source.muting = CompletableDeferred()
        flip(PARCEL_ALERTS_EMAIL)
        back()
        assertTrue(exists(PARCEL_ALERTS))
        node(PARCEL_ALERTS_DONE).performClick()
        settle()
        assertTrue(exists(PARCEL_ALERTS))
        assertTrue(doneSpins())
        source.muting?.complete(Unit)
        settle()
        assertEquals(listOf(gift to true), source.emailMutes)
        assertFalse(exists(PARCEL_ALERTS))
        assertFalse(deliveries.modal)
    }

    @Test
    fun withNothingSavingDoneTheCloseButtonAndTheBackCloseTheSheet() {
        show()
        openAlerts()
        assertFalse(doneSpins())
        node(PARCEL_ALERTS_DONE).performClick()
        settle()
        assertFalse(exists(PARCEL_ALERTS))
        node(PAGE_BELL).performClick()
        settle()
        node(PARCEL_ALERTS_CLOSE).performClick()
        settle()
        assertFalse(exists(PARCEL_ALERTS))
        node(PAGE_BELL).performClick()
        settle()
        back()
        assertFalse(exists(PARCEL_ALERTS))
        assertTrue(exists(PAGE_CONTENT))
    }

    @Test
    fun aFailedSaveShowsWhyOverTheSheetAndDoneNoLongerWaits() {
        show()
        openAlerts()
        source.muting = CompletableDeferred()
        source.failure = IllegalStateException("No connection")
        flip(PARCEL_ALERTS_NOTIFICATIONS)
        node(PARCEL_ALERTS_DONE).performClick()
        source.muting?.complete(Unit)
        settle()
        node(ACTION_ERROR).assertExists()
        compose.onNode(hasText("No connection")).assertExists()
        // The sheet stays under it, which TalkBack leaves for the alert; then the switch shows what was saved.
        assertTrue(exists(SHEET_PANEL))
        assertFalse(exists(PARCEL_ALERTS))
        node(ACTION_ERROR_CLOSE).performClick()
        settle()
        assertFalse(exists(ACTION_ERROR))
        assertTrue(exists(PARCEL_ALERTS))
        assertFalse(doneSpins())
        assertEquals(ToggleableState.On, state(PARCEL_ALERTS_NOTIFICATIONS))
        node(PARCEL_ALERTS_NOTIFICATIONS).assertIsEnabled()
        assertEquals(emptyList<Any>(), source.mutes)
    }

    @Test
    fun theSheetClosesItselfOnceTheBellNoLongerOpensIt() {
        show()
        openAlerts()
        // The email switched off elsewhere: the bell mutes the notifications again.
        source.signedInWithEmail(false)
        settle()
        assertFalse(exists(PARCEL_ALERTS))
        assertFalse(deliveries.modal)
        node(PAGE_BELL).performClick()
        settle()
        assertEquals(listOf(gift to true), source.mutes)
    }

    /** The gift delivered, as the store would have it once its carrier says so. */
    private fun deliverTheGift() {
        compose.runOnUiThread {
            source.parcels.value = source.parcels.value.map { parcel ->
                if (parcel.id != gift) parcel else parcel.copy(trackingEvents = parcel.trackingEvents.map { it.copy(stage = TrackingStage.Delivered) })
            }
        }
        settle()
    }

    @Test
    fun theSheetClosesItselfOnceTheParcelArrives() {
        show()
        openAlerts()
        deliverTheGift()
        assertFalse(exists(PARCEL_ALERTS))
        assertFalse(deliveries.modal)
        // A delivered parcel's bell mutes it.
        node(PAGE_BELL).performClick()
        settle()
        assertEquals(listOf(gift to true), source.mutes)
    }

    @Test
    fun theSheetClosesItselfOnceTheParcelArrivesEvenWhileASwitchSaves() {
        show()
        openAlerts()
        source.muting = CompletableDeferred()
        flip(PARCEL_ALERTS_EMAIL)
        node(PARCEL_ALERTS_DONE).performClick()
        deliverTheGift()
        assertFalse(exists(PARCEL_ALERTS))
        assertFalse(deliveries.modal)
        // The save goes on; what came of it is no longer the sheet's to say.
        source.failure = IllegalStateException("No connection")
        source.muting?.complete(Unit)
        settle()
        assertFalse(exists(ACTION_ERROR))
        assertFalse(exists(PARCEL_ALERTS))
        assertFalse(deliveries.modal)
    }

    /**
     * How much smaller the floating sheet draws what it holds: laid out across the window, and drawn scaled into the
     * panel, whose bounds give positions as drawn and sizes as laid out.
     */
    private fun sheetScale(): Float = node(SHEET_PANEL).getUnclippedBoundsInRoot().width / compose.onRoot().getUnclippedBoundsInRoot().width

    @Test
    fun onAPhoneTheSheetIsAsTallAsWhatItShows() {
        show()
        openAlerts()
        val panel = node(SHEET_PANEL).getUnclippedBoundsInRoot()
        val content = node(PARCEL_ALERTS).getUnclippedBoundsInRoot()
        assertEquals(content.top.value, panel.top.value, 1f)
        assertEquals(panel.height.value, content.height.value * sheetScale(), 1f)
        // Well short of the large detent.
        assertTrue("$panel", panel.top > 400.dp)
    }

    @Test
    fun talkBackGoesBackToTheBellOnceTheSheetCloses() {
        show()
        val root = compose.onRoot().fetchSemanticsNode().root as ViewRootForTest
        val manager = shadowOf(compose.activity.getSystemService(AccessibilityManager::class.java))
        compose.runOnUiThread {
            manager.setEnabled(true)
            manager.setTouchExplorationEnabled(true)
            root.forceAccessibilityForTesting(true)
        }
        openAlerts()
        node(PARCEL_ALERTS_DONE).performClick()
        settle()
        assertFalse(exists(PARCEL_ALERTS))
        val bell = node(PAGE_BELL).fetchSemanticsNode().id
        var focused = false
        compose.runOnUiThread { focused = root.view.accessibilityNodeProvider.createAccessibilityNodeInfo(bell)?.isAccessibilityFocused == true }
        assertTrue(focused)
    }

    /** How far in from the switch row's leading edge its title starts, as laid out. */
    private fun titleInset(tag: String, title: String): Float {
        val words = compose.onNode(hasText(title) and hasAnyAncestor(hasTestTag(tag)), useUnmergedTree = true).getUnclippedBoundsInRoot()
        return (words.left - node(tag).getUnclippedBoundsInRoot().left).value / sheetScale()
    }

    @Test
    fun eachSwitchsSymbolStandsBeforeItsWords() {
        show()
        openAlerts()
        // 14 in, the symbol's 22, then 12.
        assertEquals(48f, titleInset(PARCEL_ALERTS_NOTIFICATIONS, "Notifications"), 1f)
        assertEquals(48f, titleInset(PARCEL_ALERTS_EMAIL, "Email when it arrives"), 1f)
    }

    @Test
    fun atTheAccessibilitySizesTheSwitchesSymbolsAreLeftOut() {
        show(fontScale = 2f)
        openAlerts()
        assertEquals(14f, titleInset(PARCEL_ALERTS_NOTIFICATIONS, "Notifications"), 1f)
        assertEquals(14f, titleInset(PARCEL_ALERTS_EMAIL, "Email when it arrives"), 1f)
    }

    @Test
    fun theSheetAndTheSaveInFlightStayThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openAlerts()
        source.muting = CompletableDeferred()
        flip(PARCEL_ALERTS_NOTIFICATIONS)
        node(PARCEL_ALERTS_DONE).performClick()
        compose.waitForIdle()
        recreation.recreate()
        settle()
        assertTrue(exists(PARCEL_ALERTS))
        assertEquals(ToggleableState.Off, state(PARCEL_ALERTS_NOTIFICATIONS))
        node(PARCEL_ALERTS_EMAIL).assertIsNotEnabled()
        assertTrue(doneSpins())
        source.muting?.complete(Unit)
        settle()
        assertEquals(listOf(gift to true), source.mutes)
        // Done waited for it: the sheet closes once it is saved.
        assertFalse(exists(PARCEL_ALERTS))
    }

    @Test
    fun theOpenSheetStaysThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openAlerts()
        recreation.recreate()
        settle()
        assertTrue(exists(PARCEL_ALERTS))
        node(PARCEL_ALERTS_EMAIL).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        assertEquals(listOf(gift to true), source.emailMutes)
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun onATabletTheFormIsAsTallAsWhatItShows() {
        show()
        openAlerts()
        val panel = node(SHEET_PANEL).getUnclippedBoundsInRoot()
        val content = node(PARCEL_ALERTS).getUnclippedBoundsInRoot()
        assertEquals(content.bottom.value, panel.bottom.value, 1f)
        assertEquals(content.top.value, panel.top.value, 1f)
        assertTrue("${panel.bottom - panel.top}", panel.bottom - panel.top < 660.dp)
    }

    @Test
    fun talkBackReadsEachRowAsASwitchWithItsWordsAndStateAndTheTitleAsAHeading() {
        show()
        openAlerts()
        for ((tag, title) in listOf(PARCEL_ALERTS_NOTIFICATIONS to "Notifications", PARCEL_ALERTS_EMAIL to "Email when it arrives")) {
            val config = node(tag).fetchSemanticsNode().config
            assertEquals(Role.Switch, config.getOrNull(SemanticsProperties.Role))
            assertEquals(ToggleableState.On, config.getOrNull(SemanticsProperties.ToggleableState))
            assertTrue(config.getOrNull(SemanticsProperties.Text).orEmpty().any { it.text == title })
            assertTrue(SemanticsActions.OnClick in config)
        }
        // The switch itself is no other stop.
        assertEquals(2, compose.onAllNodes(hasAnyAncestor(hasTestTag(PARCEL_ALERTS)) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Switch)).fetchSemanticsNodes().size)
        assertTrue(SemanticsProperties.Heading in node(PARCEL_ALERTS_TITLE).fetchSemanticsNode().config)
        assertEquals(listOf("Close"), node(PARCEL_ALERTS_CLOSE).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription))
    }
}
