package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The delivery email offered on a delivered parcel's page (B-S68, B-AN01, B-AN05, B-H04): shown while the account
 * has never chosen, its answers saved once and counted, the line once accepted with the success tap and TalkBack told,
 * the failure line, the answers disabled while one is saved with its words kept in place under the spinner, the
 * answers in a column at large text, its motion with Reduce Motion and without, and the window made again.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class EmailOfferTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    private val delivered = DemoCards.parcels().first { it.currentStage == TrackingStage.Delivered && it.archivedAt == null }

    /** The demo's parcels, signed in as `choice` says, then the delivered parcel's page. */
    private fun show(
        choice: Boolean? = null,
        fontScale: Float = 1f,
        reduceMotion: Boolean = false,
        recreation: Recreation? = null,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo).apply {
            signedInWithEmail(choice, address = "reader@example.com")
            prepare()
        }
        compose.waitsForMaps()
        val content: @Composable () -> Unit = {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = reduceMotion)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
        compose.runOnUiThread { deliveries.navigator.open(delivered.id) }
        settle()
    }

    private fun settle(millis: Long = 1_000) {
        compose.mainClock.advanceTimeBy(millis)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String) = compose.onNodeWithTag(tag)

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag).getUnclippedBoundsInRoot()

    private fun tap(tag: String) {
        node(tag).performScrollTo().performClick()
    }

    private fun spins(tag: String): Boolean = compose.onAllNodes(
        hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate) and hasAnyAncestor(hasTestTag(tag)),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    /** What TalkBack was last told, while it is news. */
    private fun announced(): String? = compose.onNodeWithTag(ANNOUNCEMENT, useUnmergedTree = true).fetchSemanticsNode()
        .config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull()?.ifEmpty { null }

    private fun lastHaptic(): Int = shadowOf((compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view).lastHapticFeedbackPerformed()

    @Test
    fun itIsOfferedOnADeliveredParcelWhileTheAccountHasNeverChosen() {
        show()
        assertTrue(exists(PAGE_EMAIL_OFFER))
        compose.onNode(hasText("An email when the next one arrives?") and hasAnyAncestor(hasTestTag(PAGE_EMAIL_OFFER)), useUnmergedTree = true).assertExists()
        compose.onNode(hasText("A short email to reader@example.com", substring = true), useUnmergedTree = true).assertExists()
        val title = compose.onNode(hasText("An email when the next one arrives?"), useUnmergedTree = true).fetchSemanticsNode()
        assertTrue(SemanticsProperties.Heading in title.config)
        assertFalse(exists(PAGE_EMAIL_OFFER_ERROR))

        // Not on a parcel still on its way.
        compose.runOnUiThread { deliveries.navigator.open(DemoCards.parcel("Birthday gift").id) }
        settle()
        assertFalse(exists(PAGE_EMAIL_OFFER))
    }

    @Test
    fun anAccountWithTheEmailOnIsNotOffered() {
        show(choice = true)
        assertFalse(exists(PAGE_EMAIL_OFFER))
        assertFalse(exists(PAGE_EMAIL_OFFER_ON))
    }

    @Test
    fun anAccountThatSaidNoIsNotOffered() {
        show(choice = false)
        assertFalse(exists(PAGE_EMAIL_OFFER))
        assertFalse(exists(PAGE_EMAIL_OFFER_ON))
    }

    @Test
    fun turnOnSavesOnceCountsItAndSaysTheEmailIsOnWithTheSuccessTap() {
        show { choosing = CompletableDeferred() }
        node(PAGE_EMAIL_OFFER_TURN_ON).performScrollTo()
        compose.waitForIdle()
        val before = bounds(PAGE_EMAIL_OFFER_TURN_ON)
        tap(PAGE_EMAIL_OFFER_TURN_ON)
        settle()
        assertEquals(listOf("email-offer-accept"), source.actions.filter { it.startsWith("email-offer") })
        // Both answers wait while it saves, the spinner in Turn on's place, which keeps its size.
        node(PAGE_EMAIL_OFFER_TURN_ON).assertIsNotEnabled()
        node(PAGE_EMAIL_OFFER_NOT_NOW).assertIsNotEnabled()
        assertTrue(spins(PAGE_EMAIL_OFFER_TURN_ON))
        assertFalse(spins(PAGE_EMAIL_OFFER_NOT_NOW))
        assertEquals(before, bounds(PAGE_EMAIL_OFFER_TURN_ON))
        compose.onNode(hasText("Turn on") and hasAnyAncestor(hasTestTag(PAGE_EMAIL_OFFER_TURN_ON)), useUnmergedTree = true).assertExists()
        node(PAGE_EMAIL_OFFER_TURN_ON).performClick()
        node(PAGE_EMAIL_OFFER_NOT_NOW).performClick()
        settle()
        assertEquals(1, source.actions.count { it.startsWith("email-offer") })

        source.choosing?.complete(Unit)
        settle(300)
        assertEquals(listOf(true), source.emailChoices)
        assertEquals("Email is on. You can change it in Settings › Notifications.", announced())
        assertEquals(hapticFeedbackConstant(SensoryFeedback.Success), lastHaptic())
        settle()
        assertTrue(exists(PAGE_EMAIL_OFFER_ON))
        assertFalse(exists(PAGE_EMAIL_OFFER))
    }

    @Test
    fun notNowSavesOnceCountsItAndTheOfferLeaves() {
        show()
        tap(PAGE_EMAIL_OFFER_NOT_NOW)
        settle(300)
        assertEquals(listOf("email-offer-decline"), source.actions.filter { it.startsWith("email-offer") })
        assertEquals(listOf(false), source.emailChoices)
        assertNull(announced())
        settle()
        assertFalse(exists(PAGE_EMAIL_OFFER))
        assertFalse(exists(PAGE_EMAIL_OFFER_ON))
    }

    @Test
    fun turnedOnButLeftOffByTheServerTheOfferLeavesWithoutTheLine() {
        show { emailRefused = true }
        tap(PAGE_EMAIL_OFFER_TURN_ON)
        settle(300)
        assertEquals(listOf(true), source.emailChoices)
        assertNull(announced())
        settle()
        assertFalse(exists(PAGE_EMAIL_OFFER))
        assertFalse(exists(PAGE_EMAIL_OFFER_ON))
    }

    @Test
    fun aFailedAnswerSaysSoTellsTalkBackAndCanBeGivenAgain() {
        show()
        source.failure = IllegalStateException("No connection")
        tap(PAGE_EMAIL_OFFER_TURN_ON)
        settle(300)
        assertEquals("Couldn’t save your email setting. Try again.", announced())
        settle()
        assertTrue(exists(PAGE_EMAIL_OFFER_ERROR))
        node(PAGE_EMAIL_OFFER_TURN_ON).assertIsEnabled()
        node(PAGE_EMAIL_OFFER_NOT_NOW).assertIsEnabled()
        assertEquals(emptyList<Boolean>(), source.emailChoices)

        source.failure = null
        tap(PAGE_EMAIL_OFFER_TURN_ON)
        settle()
        assertEquals(listOf(true), source.emailChoices)
        assertTrue(exists(PAGE_EMAIL_OFFER_ON))
        assertEquals(2, source.actions.count { it == "email-offer-accept" })
    }

    @Test
    fun theAnswersStandSideBySide() {
        show()
        val turnOn = bounds(PAGE_EMAIL_OFFER_TURN_ON)
        val notNow = bounds(PAGE_EMAIL_OFFER_NOT_NOW)
        assertEquals(turnOn.top.value, notNow.top.value, 1f)
        assertTrue("$turnOn $notNow", notNow.left >= turnOn.right)
    }

    @Test
    @Config(qualifiers = "w320dp-h640dp-xhdpi")
    fun atTheLargestTextOnTheNarrowestPhoneTheAnswersStandOneUnderTheOther() {
        show(fontScale = 2f)
        val turnOn = bounds(PAGE_EMAIL_OFFER_TURN_ON)
        val notNow = bounds(PAGE_EMAIL_OFFER_NOT_NOW)
        assertTrue("$turnOn $notNow", notNow.top >= turnOn.bottom)
        assertEquals(turnOn.left.value, notNow.left.value, 1f)
    }

    @Test
    fun declinedTheCardFadesWhileTheRowsUnderItMoveUp() {
        show()
        node(PAGE_EMAIL_OFFER_NOT_NOW).performScrollTo()
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        node(PAGE_EMAIL_OFFER_NOT_NOW).performClick()
        compose.mainClock.advanceTimeBy(64)
        assertTrue(exists(PAGE_EMAIL_OFFER))
        compose.mainClock.advanceTimeBy(1_000)
        assertFalse(exists(PAGE_EMAIL_OFFER))
    }

    @Test
    fun underReduceMotionTheDeclinedCardGoesAtOnce() {
        show(reduceMotion = true)
        node(PAGE_EMAIL_OFFER_NOT_NOW).performScrollTo()
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        node(PAGE_EMAIL_OFFER_NOT_NOW).performClick()
        compose.mainClock.advanceTimeBy(64)
        assertFalse(exists(PAGE_EMAIL_OFFER))
    }

    @Test
    fun underReduceMotionTheLineTakesTheCardsPlaceAtOnce() {
        show(reduceMotion = true)
        node(PAGE_EMAIL_OFFER_TURN_ON).performScrollTo()
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        node(PAGE_EMAIL_OFFER_TURN_ON).performClick()
        compose.mainClock.advanceTimeBy(64)
        assertTrue(exists(PAGE_EMAIL_OFFER_ON))
        assertFalse(exists(PAGE_EMAIL_OFFER))
    }

    @Test
    fun acceptedTheLineFadesInAsTheCardFadesOut() {
        show()
        node(PAGE_EMAIL_OFFER_TURN_ON).performScrollTo()
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        node(PAGE_EMAIL_OFFER_TURN_ON).performClick()
        compose.mainClock.advanceTimeBy(64)
        assertTrue(exists(PAGE_EMAIL_OFFER_ON))
        assertTrue(exists(PAGE_EMAIL_OFFER))
        compose.mainClock.advanceTimeBy(1_000)
        assertFalse(exists(PAGE_EMAIL_OFFER))
    }

    @Test
    fun anAnswerBeingSavedStaysThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation) { choosing = CompletableDeferred() }
        tap(PAGE_EMAIL_OFFER_TURN_ON)
        settle()
        recreation.recreate()
        settle()
        assertTrue(spins(PAGE_EMAIL_OFFER_TURN_ON))
        node(PAGE_EMAIL_OFFER_NOT_NOW).assertIsNotEnabled()
        source.choosing?.complete(Unit)
        settle(300)
        assertEquals("Email is on. You can change it in Settings › Notifications.", announced())
        settle()
        assertTrue(exists(PAGE_EMAIL_OFFER_ON))
        assertEquals(listOf(true), source.emailChoices)
        assertEquals(hapticFeedbackConstant(SensoryFeedback.Success), lastHaptic())
    }
}
