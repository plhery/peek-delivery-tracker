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
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.ui.awaitFrame
import java.util.UUID
import kotlin.math.abs
import kotlin.math.roundToInt
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

    /** The account the tests sign in with: the demo has none, and so no delivery email. */
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 6), email = "reader@example.com"))

    /** The demo's parcels in an account whose delivery email is as `choice` says, then the delivered parcel's page. */
    private fun show(
        choice: Boolean? = null,
        fontScale: Float = 1f,
        reduceMotion: Boolean = false,
        recreation: Recreation? = null,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        source = FakeDeliveries(DemoCards.parcels(), reader).apply {
            signedInWithEmail(choice, address = "reader@example.com")
            prepare()
        }
        compose.waitsForMaps()
        val content: @Composable () -> Unit = {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = reader, source = source, reduceMotion = reduceMotion)
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
        hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag))),
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

    /**
     * The answer `tag` saved, then followed a frame at a time: whenever the card shows, it waits, its answers disabled
     * and the spinner in `tag`'s place, until it has gone.
     */
    private fun assertTheCardNeverComesBackOpen(tag: String) {
        node(tag).performScrollTo()
        compose.waitForIdle()
        source.choosing = CompletableDeferred()
        node(tag).performClick()
        settle(300)
        compose.mainClock.autoAdvance = false
        source.choosing?.complete(Unit)
        repeat(30) { frame ->
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
            if (!exists(PAGE_EMAIL_OFFER)) return@repeat
            node(PAGE_EMAIL_OFFER_TURN_ON).assertIsNotEnabled()
            node(PAGE_EMAIL_OFFER_NOT_NOW).assertIsNotEnabled()
            assertTrue("Frame $frame", spins(tag))
        }
        assertFalse(exists(PAGE_EMAIL_OFFER))
    }

    @Test
    fun notNowSavedTheCardGoesWithoutComingBackOpen() {
        show()
        assertTheCardNeverComesBackOpen(PAGE_EMAIL_OFFER_NOT_NOW)
        assertEquals(listOf(false), source.emailChoices)
    }

    @Test
    fun turnedOnButLeftOffByTheServerTheCardGoesWithoutComingBackOpen() {
        show { emailRefused = true }
        assertTheCardNeverComesBackOpen(PAGE_EMAIL_OFFER_TURN_ON)
        assertFalse(exists(PAGE_EMAIL_OFFER_ON))
    }

    @Test
    fun whileAnAnswerSavesTalkBackHearsItInProgressRatherThanItsWords() {
        show { choosing = CompletableDeferred() }
        tap(PAGE_EMAIL_OFFER_TURN_ON)
        settle()
        val saving = node(PAGE_EMAIL_OFFER_TURN_ON).fetchSemanticsNode().config
        assertEquals(Role.Button, saving.getOrNull(SemanticsProperties.Role))
        assertTrue(SemanticsProperties.Disabled in saving)
        assertEquals(ProgressBarRangeInfo.Indeterminate, saving.getOrNull(SemanticsProperties.ProgressBarRangeInfo))
        assertNull(saving.getOrNull(SemanticsProperties.Text))
        // The other answer keeps its words.
        val other = node(PAGE_EMAIL_OFFER_NOT_NOW).fetchSemanticsNode().config
        assertEquals(listOf("Not now"), other.getOrNull(SemanticsProperties.Text)?.map { it.text })
        assertTrue(SemanticsProperties.Disabled in other)

        // Failed, the words are back.
        source.failure = IllegalStateException("No connection")
        source.choosing?.complete(Unit)
        settle()
        assertEquals(listOf("Turn on"), node(PAGE_EMAIL_OFFER_TURN_ON).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text)?.map { it.text })
    }

    /**
     * How much of the spinner is drawn in Turn on: its spokes' light over the button's yellow in the bands above and
     * under the button's words, where only the spinner draws.
     */
    private fun spinnerInk(): Long {
        val button = bounds(PAGE_EMAIL_OFFER_TURN_ON)
        val scale = compose.density.density
        val centreX = ((button.left + button.right) / 2).value * scale
        val centreY = ((button.top + button.bottom) / 2).value * scale
        compose.awaitFrame()
        val image = compose.onRoot().captureToImage().asAndroidBitmap()
        val ground = image.getPixel(((button.left + 6.dp).value * scale).roundToInt(), centreY.roundToInt())
        var ink = 0L
        for (dy in listOf(-10f..-7f, 7f..10f)) {
            for (y in (centreY + dy.start * scale).roundToInt()..(centreY + dy.endInclusive * scale).roundToInt()) {
                for (x in (centreX - 10 * scale).roundToInt()..(centreX + 10 * scale).roundToInt()) {
                    val pixel = image.getPixel(x, y)
                    ink += (0..2).sumOf { abs((pixel shr (it * 8) and 0xFF) - (ground shr (it * 8) and 0xFF)) }
                }
            }
        }
        return ink
    }

    /**
     * Turn on tapped, its spinner followed for `frames` frames, then one turn later, when its spokes stand as they did:
     * how much of it was drawn then, against all of it.
     */
    private fun spinnerShown(frames: Int, reduceMotion: Boolean): Float {
        show(reduceMotion = reduceMotion) { choosing = CompletableDeferred() }
        node(PAGE_EMAIL_OFFER_TURN_ON).performScrollTo()
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        node(PAGE_EMAIL_OFFER_TURN_ON).performClick()
        repeat(frames) {
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
        }
        val partway = spinnerInk()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        return partway / spinnerInk().toFloat()
    }

    @Test
    fun theSpinnerFadesInWithTheColumnsEaseInAndOut() {
        // About 100 ms into the 0.2 s: half drawn, a frame or two either way.
        val shown = spinnerShown(frames = 8, reduceMotion = false)
        assertTrue("$shown", shown in 0.15f..0.75f)
    }

    @Test
    fun underReduceMotionTheSpinnerComesAtOnce() {
        val shown = spinnerShown(frames = 2, reduceMotion = true)
        assertEquals(1f, shown, 0.1f)
    }

    @Test
    fun theOfferIsOneGroupForTalkBackWithTwoButtonsAndAcceptedItSaysTheEmailIsOn() {
        show()
        assertEquals(true, compose.onNodeWithTag(PAGE_EMAIL_OFFER, useUnmergedTree = true).fetchSemanticsNode().config.getOrNull(SemanticsProperties.IsTraversalGroup))
        for (tag in listOf(PAGE_EMAIL_OFFER_TURN_ON, PAGE_EMAIL_OFFER_NOT_NOW)) {
            assertEquals(Role.Button, node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Role))
        }
        tap(PAGE_EMAIL_OFFER_TURN_ON)
        settle()
        compose.onNode(
            hasText("Email is on. You can change it in Settings › Notifications.") and hasAnyAncestor(hasTestTag(PAGE_EMAIL_OFFER_ON)),
            useUnmergedTree = true,
        ).assertExists()
    }

    /** How far in from the card's leading edge its title starts. */
    private fun titleInset(): Dp {
        val title = compose.onNode(hasText("An email when the next one arrives?"), useUnmergedTree = true).getUnclippedBoundsInRoot()
        return title.left - bounds(PAGE_EMAIL_OFFER).left
    }

    @Test
    fun theEnvelopeStandsBesideTheTitle() {
        show()
        // 16 in, the tile's 40, then 12.
        assertEquals(68f, titleInset().value, 1f)
    }

    @Test
    fun atTheAccessibilitySizesTheEnvelopeIsLeftOut() {
        show(fontScale = 2f)
        assertEquals(16f, titleInset().value, 1f)
    }

    @Test
    fun aFailedAnswerStaysThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation) { failure = IllegalStateException("No connection") }
        tap(PAGE_EMAIL_OFFER_TURN_ON)
        settle()
        assertTrue(exists(PAGE_EMAIL_OFFER_ERROR))
        recreation.recreate()
        settle()
        assertTrue(exists(PAGE_EMAIL_OFFER_ERROR))
        node(PAGE_EMAIL_OFFER_TURN_ON).assertIsEnabled()
        node(PAGE_EMAIL_OFFER_NOT_NOW).assertIsEnabled()
        source.failure = null
        tap(PAGE_EMAIL_OFFER_NOT_NOW)
        settle()
        assertEquals(listOf(false), source.emailChoices)
        assertFalse(exists(PAGE_EMAIL_OFFER))
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

    /**
     * Where the row under the offer stands, and the envelope's tile and the page round the card at rest, in pixels of a
     * capture: the tile 16 in from the card's corner, 40 square, and the page 8 in from the card's side, under its text.
     */
    private class Rest(val top: Dp, val tile: Pair<Int, Int>, val page: Pair<Int, Int>, val tileBlue: Int, val pageBlue: Int)

    private fun blue(at: Pair<Int, Int>): Int {
        compose.awaitFrame()
        return compose.onRoot().captureToImage().asAndroidBitmap().getPixel(at.first, at.second) and 0xFF
    }

    private fun rest(): Rest {
        node(PAGE_EMAIL_OFFER).performScrollTo()
        compose.waitForIdle()
        val card = bounds(PAGE_EMAIL_OFFER)
        val scale = compose.density.density
        fun px(x: Dp, y: Dp) = (x.value * scale).roundToInt() to (y.value * scale).roundToInt()
        val tile = px(card.left + 26.dp, card.top + 46.dp)
        val page = px(card.left + 8.dp, card.top + 100.dp)
        return Rest(bounds(PAGE_IDENTITY).top, tile, page, blue(tile), blue(page))
    }

    /** How much of the card is drawn, from its envelope's tile: 1 at rest, 0 gone. */
    private fun Rest.cardAlpha(): Float = (blue(tile) - pageBlue) / (tileBlue - pageBlue).toFloat()

    /**
     * The card answered with `answer` and let go at once, its motion followed a frame at a time from the first frame the
     * row under it moves, 16 ms in: how far the row has moved of the way it goes at about 50, 100 and 200 ms, and how
     * much of the card is drawn at about 50 and 100 ms.
     */
    private fun followMotion(answer: String): Map<String, Float> {
        val rest = rest()
        compose.mainClock.autoAdvance = false
        node(answer).performClick()
        var frame = 0
        while (bounds(PAGE_IDENTITY).top >= rest.top - 0.5.dp) {
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
            assertTrue("The row never moved", ++frame < 30)
        }
        val tops = mutableMapOf<Int, Dp>()
        val alphas = mutableMapOf<Int, Float>()
        for (elapsed in 16..240 step 16) {
            tops[elapsed] = bounds(PAGE_IDENTITY).top
            if (elapsed == 48 || elapsed == 96) alphas[elapsed] = rest.cardAlpha()
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
        }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        val end = bounds(PAGE_IDENTITY).top
        fun moved(elapsed: Int) = (rest.top - tops.getValue(elapsed)) / (rest.top - end)
        return mapOf("50" to moved(48), "100" to moved(96), "200" to moved(208), "alpha50" to alphas.getValue(48), "alpha100" to alphas.getValue(96))
    }

    /**
     * The column's `.easeInOut(duration: 0.2)`: an eighth of the way at 50 ms, half at 100, there at 200, a frame either
     * way; and the card's `.easeOut(duration: 0.1)`: partly drawn at 50 ms, all but gone at 100 (a capture may show a
     * frame or two before).
     */
    private fun assertColumnAndFade(motion: Map<String, Float>) {
        assertTrue("$motion", motion.getValue("50") in 0.04f..0.25f)
        assertTrue("$motion", motion.getValue("100") in 0.35f..0.65f)
        assertEquals("$motion", 1f, motion.getValue("200"), 0.02f)
        assertTrue("$motion", motion.getValue("alpha50") in 0.15f..0.9f)
        assertTrue("$motion", motion.getValue("alpha100") <= 0.2f)
    }

    @Test
    fun declinedTheCardFadesWhileTheRowsUnderItMoveUp() {
        show()
        assertColumnAndFade(followMotion(PAGE_EMAIL_OFFER_NOT_NOW))
        assertFalse(exists(PAGE_EMAIL_OFFER))
    }

    @Test
    fun acceptedTheLineFadesInAsTheCardFadesOutAndTheRowsUnderItMoveUp() {
        show()
        assertColumnAndFade(followMotion(PAGE_EMAIL_OFFER_TURN_ON))
        assertFalse(exists(PAGE_EMAIL_OFFER))
        assertTrue(exists(PAGE_EMAIL_OFFER_ON))
    }

    /** The card answered with `answer` under Reduce Motion: where the row under it stands, a frame at a time. */
    private fun rowUnderReduceMotion(answer: String): List<Dp> {
        show(reduceMotion = true)
        val rest = rest()
        compose.mainClock.autoAdvance = false
        node(answer).performClick()
        val tops = mutableListOf(rest.top)
        repeat(20) {
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
            tops += bounds(PAGE_IDENTITY).top
        }
        return tops
    }

    @Test
    fun underReduceMotionTheDeclinedCardGoesAtOnce() {
        val tops = rowUnderReduceMotion(PAGE_EMAIL_OFFER_NOT_NOW)
        assertFalse(exists(PAGE_EMAIL_OFFER))
        // The row stands where it was, then where it goes, and nowhere in between.
        assertEquals("$tops", 2, tops.distinct().size)
        assertTrue("$tops", tops.last() < tops.first())
    }

    @Test
    fun underReduceMotionTheLineTakesTheCardsPlaceAtOnce() {
        val tops = rowUnderReduceMotion(PAGE_EMAIL_OFFER_TURN_ON)
        assertTrue(exists(PAGE_EMAIL_OFFER_ON))
        assertFalse(exists(PAGE_EMAIL_OFFER))
        assertEquals("$tops", 2, tops.distinct().size)
        assertTrue("$tops", tops.last() < tops.first())
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
