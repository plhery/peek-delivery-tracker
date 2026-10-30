package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.ClipboardManager
import android.content.Intent
import android.view.accessibility.AccessibilityManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
import androidx.core.content.IntentCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.JsonEncoder
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.GiftWords
import com.plhery.peekdeliverytracker.core.api.ParcelShare
import com.plhery.peekdeliverytracker.core.api.ShareParcelRequest
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import java.io.IOException
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A parcel's share sheet (B-S51–B-S57, B-ST32, B-SE01–B-SE03, B-H07, B-L08): opened from its pass's share button, its
 * page's menu and its card's menu, a reader link's page sharing its address at once; the link read, made, changed and
 * stopped; the switches shown at once and put back when the service refuses; the gift's words within their limits;
 * the address copied and handed to the system's share sheet; the detents, the form on a tablet, the window made again,
 * and what TalkBack reads.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelShareTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    private val gift get() = DemoCards.parcel("Birthday gift").id

    /** The account the tests sign in with; the demo makes its links up. */
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** The gift's link, as the service would have it, showing nothing more than the journey. */
    private val giftLink = ParcelShare(id = "TestLink2345", showNumber = false, gift = false, createdAt = "2026-05-01T09:00:00Z")

    /** The demo's parcels in an account, or in the demo, made again by `recreation`. */
    private fun show(
        session: AuthenticationState = reader,
        fontScale: Float = 1f,
        recreation: Recreation? = null,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        source = FakeDeliveries(DemoCards.parcels(), session).apply(prepare)
        compose.waitsForMaps()
        val content: @Composable () -> Unit = {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = session, source = source, reduceMotion = false)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** The gift's page, then its pass's share button. */
    private fun openShare() {
        compose.openCard("Birthday gift")
        settle()
        node(PAGE_SHARE).performClick()
        settle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun state(tag: String): ToggleableState? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ToggleableState)

    /** Whether the part tagged `tag`, or something in it, shows `text`. */
    private fun shows(tag: String, text: String): Boolean =
        compose.onAllNodes(hasText(text, substring = true) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag)))).fetchSemanticsNodes().isNotEmpty()

    /** A tap on the row's switch, at its trailing end. */
    private fun flip(tag: String) {
        node(tag).performTouchInput { click(Offset(width - 45.dp.toPx(), height / 2f)) }
        compose.waitForIdle()
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    /** Whether the part tagged `tag`, or something in it, is in progress. */
    private fun spins(tag: String): Boolean = compose.onAllNodes(
        hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag))),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    /** The sheet's work, which the source keeps while it is open. */
    private fun work(): ParcelShareSheetWork = source.shares.sheet(deliveries.sharing!!.id)!!

    /** What the window last handed to the system's share sheet: the address, or null. */
    private fun handedOut(): String? {
        val started = shadowOf(compose.activity).nextStartedActivity ?: return null
        assertEquals(Intent.ACTION_CHOOSER, started.action)
        val send = IntentCompat.getParcelableExtra(started, Intent.EXTRA_INTENT, Intent::class.java)!!
        assertEquals(Intent.ACTION_SEND, send.action)
        assertEquals("text/plain", send.type)
        return send.getStringExtra(Intent.EXTRA_TEXT)
    }

    private fun shareActions(): List<Any?> = source.actions.indices.filter { source.actions[it] == "parcel-link-share" }.map { source.outcomes[it] }

    // Opening it.

    @Test
    fun thePassShareButtonOpensTheSheetOnTheParcelWithItsName() {
        show()
        openShare()
        assertTrue(exists(PARCEL_SHARE))
        assertTrue(shows(PARCEL_SHARE_TITLE, "Share “Birthday gift"))
        assertEquals(gift, deliveries.sharing?.parcelId)
        assertEquals(gift, deliveries.sharing?.pageId)
        assertTrue(deliveries.modal)
        // Opening it reads the link and counts nothing.
        assertEquals(emptyList<Any>(), shareActions())
        assertEquals(emptyList<Any>(), source.shareRequests)
        node(PARCEL_SHARE_CLOSE).performClick()
        settle()
        assertFalse(exists(PARCEL_SHARE))
        assertNull(deliveries.sharing)
        assertFalse(deliveries.modal)
    }

    @Test
    fun aCardsMenuOpensTheSheetOnItsParcel() {
        show()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed("Birthday gift"))
        compose.card("Birthday gift").performTouchInput { longClick() }
        compose.waitForIdle()
        node(menuItemTag(ParcelMenuItem.Share)).performClick()
        settle()
        assertTrue(exists(PARCEL_SHARE))
        assertTrue(shows(PARCEL_SHARE_TITLE, "Birthday gift"))
        assertEquals(gift, deliveries.sharing?.parcelId)
        assertNull(deliveries.sharing?.pageId)
        assertNull(deliveries.navigator.navigation.shown)
        back()
        assertFalse(exists(PARCEL_SHARE))
    }

    @Test
    fun thePagesMenuOpensTheSheetOnItsParcel() {
        show()
        compose.openCard("Birthday gift")
        settle()
        compose.runOnUiThread { deliveries.choseOnPage(gift, ParcelMenuItem.Share, DemoCards.parcel("Birthday gift")) }
        settle()
        assertTrue(exists(PARCEL_SHARE))
        assertEquals(gift, deliveries.sharing?.pageId)
    }

    @Test
    fun aParcelFollowedThroughItsLinkSharesThatLinkAtOnce() {
        show { viewerLinks = mapOf(gift to "TestLink6789") }
        openShare()
        assertFalse(exists(PARCEL_SHARE))
        assertNull(deliveries.sharing)
        assertEquals("https://example.com/p/TestLink6789", handedOut())
        // As ShareLink, which counts nothing.
        assertEquals(emptyList<Any>(), shareActions())
    }

    // Reading the link.

    @Test
    fun whileTheLinkIsReadASpinnerStandsForItAndTheSwitchesWait() {
        show {
            shareLinks[gift] = giftLink
            shareLoading = CompletableDeferred()
        }
        openShare()
        assertTrue(spins(PARCEL_SHARE_LINK))
        assertFalse(exists(PARCEL_SHARE_ADDRESS))
        for (tag in listOf(PARCEL_SHARE_NUMBER, PARCEL_SHARE_NAME, PARCEL_SHARE_GIFT, PARCEL_SHARE_COPY, PARCEL_SHARE_ACTION)) node(tag).assertIsNotEnabled()
        assertFalse(exists(PARCEL_SHARE_STOP))
        source.shareLoading?.complete(Unit)
        settle()
        assertFalse(spins(PARCEL_SHARE_LINK))
        assertTrue(shows(PARCEL_SHARE_ADDRESS, "example.com/p/TestLink2345"))
        assertFalse(shows(PARCEL_SHARE_ADDRESS, "https://"))
        for (tag in listOf(PARCEL_SHARE_NUMBER, PARCEL_SHARE_NAME, PARCEL_SHARE_GIFT, PARCEL_SHARE_COPY, PARCEL_SHARE_ACTION)) node(tag).assertIsEnabled()
        assertTrue(exists(PARCEL_SHARE_STOP))
        assertFalse(exists(PARCEL_SHARE_PENDING))
    }

    @Test
    fun withoutALinkTheRowSaysOneIsMadeOnSharingAndTheSwitchesStayOnThisPhone() {
        show()
        openShare()
        assertTrue(exists(PARCEL_SHARE_PENDING))
        assertFalse(exists(PARCEL_SHARE_STOP))
        flip(PARCEL_SHARE_NUMBER)
        settle()
        assertEquals(ToggleableState.On, state(PARCEL_SHARE_NUMBER))
        assertEquals(emptyList<Any>(), source.shareRequests)
        // Sharing makes the link with what the switches say, and hands it out.
        node(PARCEL_SHARE_ACTION).performClick()
        settle()
        assertEquals(1, source.shareRequests.size)
        assertEquals(gift, source.shareRequests.single().first)
        assertTrue(source.shareRequests.single().second.showNumber)
        assertEquals("https://example.com/p/TestLink0001", handedOut())
        assertTrue(shows(PARCEL_SHARE_ADDRESS, "example.com/p/TestLink0001"))
        assertTrue(exists(PARCEL_SHARE_STOP))
        // Android doesn't learn what came of it: counted started, as the iPhone counts a ShareLink.
        assertEquals(listOf(DeliveryAnalytics.Outcome.Started), shareActions())
    }

    @Test
    fun aLinkThatCouldNotBeReadLeavesSharingToMakeOneAndAFailedShareSaysWhy() {
        show { shareLoadFailure = IOException("offline") }
        openShare()
        // As the iPhone: nothing to say yet, sharing finds or makes the link.
        assertTrue(exists(PARCEL_SHARE_PENDING))
        assertFalse(exists(PARCEL_SHARE_FAILURE))
        node(PARCEL_SHARE_NUMBER).assertIsEnabled()
        source.shareFailure = IOException("offline")
        node(PARCEL_SHARE_ACTION).performClick()
        settle()
        assertTrue(shows(PARCEL_SHARE_FAILURE, "Check your internet connection"))
        assertNull(handedOut())
        assertEquals(emptyList<Any>(), shareActions())
    }

    @Test
    fun aShareSheetThatCannotBeShownSaysSoAndCountsAnError() {
        show { shareLinks[gift] = giftLink }
        openShare()
        shadowOf(compose.activity.application).checkActivities(true)
        node(PARCEL_SHARE_ACTION).performClick()
        settle()
        assertTrue(shows(PARCEL_SHARE_FAILURE, "Couldn’t copy the link"))
        assertEquals(listOf(DeliveryAnalytics.Outcome.Error), shareActions())
    }

    @Test
    fun stoppingShowsSharingStoppedAndSharingAgainMakesANewLink() {
        show { shareLinks[gift] = giftLink }
        openShare()
        node(PARCEL_SHARE_STOP).performClick()
        settle()
        assertEquals(listOf(gift), source.shareStops)
        assertTrue(shows(PARCEL_SHARE_STOPPED, "Sharing is stopped. Sharing again makes a new link."))
        assertFalse(exists(PARCEL_SHARE_PREVIEW))
        assertFalse(exists(PARCEL_SHARE_LINK))
        assertFalse(exists(PARCEL_SHARE_DEMO))
        node(PARCEL_SHARE_AGAIN).performClick()
        settle()
        assertEquals(1, source.shareRequests.size)
        assertFalse(exists(PARCEL_SHARE_STOPPED))
        assertTrue(shows(PARCEL_SHARE_ADDRESS, "example.com/p/TestLink0001"))
        // Sharing again hands nothing out by itself.
        assertNull(handedOut())
    }

    @Test
    fun theDemoSaysNothingIsSent() {
        show(session = AuthenticationState.Demo)
        openShare()
        assertTrue(shows(PARCEL_SHARE_DEMO, "This is the demo: nothing is sent."))
        node(PARCEL_SHARE_STOP).assertDoesNotExist()
        node(PARCEL_SHARE_ACTION).performClick()
        settle()
        assertTrue(exists(PARCEL_SHARE_STOP))
        node(PARCEL_SHARE_STOP).performClick()
        settle()
        assertTrue(shows(PARCEL_SHARE_DEMO, "This is the demo: nothing is sent."))
        assertTrue(exists(PARCEL_SHARE_AGAIN))
    }

    @Test
    fun anAccountsSheetHasNoDemoLine() {
        show()
        openShare()
        assertFalse(exists(PARCEL_SHARE_DEMO))
    }

    // The switches.

    @Test
    fun aSwitchShowsAtOnceIsSavedWithTheLinkAndCopyAndShareWaitForIt() {
        show {
            shareLinks[gift] = giftLink
            shareSaving = CompletableDeferred()
        }
        openShare()
        flip(PARCEL_SHARE_NUMBER)
        assertEquals(ToggleableState.On, state(PARCEL_SHARE_NUMBER))
        node(PARCEL_SHARE_COPY).assertIsNotEnabled()
        node(PARCEL_SHARE_ACTION).assertIsNotEnabled()
        // The switches themselves wait only for the link, as the iPhone's: the gift saves beside it.
        node(PARCEL_SHARE_GIFT).assertIsEnabled()
        source.shareSaving?.complete(Unit)
        settle()
        assertEquals(ToggleableState.On, state(PARCEL_SHARE_NUMBER))
        assertEquals(listOf(true), source.shareRequests.map { it.second.showNumber })
        assertTrue(source.shareLinks[gift]!!.showNumber)
        node(PARCEL_SHARE_COPY).assertIsEnabled()
        node(PARCEL_SHARE_ACTION).assertIsEnabled()
    }

    @Test
    fun aSwitchTheServiceRefusesGoesBackAndTheSheetSaysWhy() {
        show { shareLinks[gift] = giftLink }
        openShare()
        source.shareSaving = CompletableDeferred()
        source.shareFailure = IllegalStateException("refused")
        flip(PARCEL_SHARE_GIFT)
        assertEquals(ToggleableState.On, state(PARCEL_SHARE_GIFT))
        source.shareSaving?.complete(Unit)
        settle()
        assertEquals(ToggleableState.Off, state(PARCEL_SHARE_GIFT))
        assertTrue(shows(PARCEL_SHARE_FAILURE, "Couldn’t save that."))
        assertFalse(source.shareLinks[gift]!!.gift)
        assertFalse(exists(PARCEL_SHARE_NOTE))
    }

    @Test
    fun theNameSwitchPutsTheNameInTheAddressAndIsKeptForTheParcel() {
        show { shareLinks[gift] = giftLink }
        openShare()
        assertFalse(shows(PARCEL_SHARE_ADDRESS, "#n="))
        assertTrue(compose.onAllNodes(hasTestTag(PARCEL_SHARE_NAME) and hasText("Birthday gift", substring = true)).fetchSemanticsNodes().isNotEmpty())
        flip(PARCEL_SHARE_NAME)
        settle()
        assertEquals(ToggleableState.On, state(PARCEL_SHARE_NAME))
        assertTrue(shows(PARCEL_SHARE_ADDRESS, "example.com/p/TestLink2345#n=Birthday"))
        // The name goes in the address only: nothing is sent.
        assertEquals(emptyList<Any>(), source.shareRequests)
        assertEquals(true, source.shareNotes.words(gift)?.name)
        node(PARCEL_SHARE_CLOSE).performClick()
        settle()
        node(PAGE_SHARE).performClick()
        settle()
        assertEquals(ToggleableState.On, state(PARCEL_SHARE_NAME))
        assertTrue(shows(PARCEL_SHARE_ADDRESS, "#n=Birthday"))
        // Another parcel's link doesn't carry it.
        assertNull(source.shareNotes.words(DemoCards.parcel("Moon lamp").id))
    }

    @Test
    fun theGiftsWordsKeepWithinTheirLimitsAndGoWithTheLinkEmptyOnesAsNull() {
        show()
        openShare()
        assertFalse(exists(PARCEL_SHARE_NOTE))
        flip(PARCEL_SHARE_GIFT)
        settle()
        assertTrue(exists(PARCEL_SHARE_NOTE))
        assertTrue(compose.onAllNodes(hasText("Stored securely by Peek; shared only after delivery.")).fetchSemanticsNodes().isNotEmpty())
        node(PARCEL_SHARE_NOTE).performTextReplacement("n".repeat(300))
        node(PARCEL_SHARE_FROM).performTextReplacement("f".repeat(70))
        compose.waitForIdle()
        assertEquals("n".repeat(280), editable(PARCEL_SHARE_NOTE))
        assertEquals("f".repeat(60), editable(PARCEL_SHARE_FROM))
        node(PARCEL_SHARE_FROM).performTextReplacement("")
        compose.waitForIdle()
        node(PARCEL_SHARE_ACTION).performClick()
        settle()
        val sent = source.shareRequests.single().second
        assertTrue(sent.gift)
        assertEquals(GiftWords(name = null, note = "n".repeat(280), from = null), sent.giftWords)
        val body = JsonEncoder().encode(ShareParcelRequest.serializer(), sent.request).decodeToString()
        assertTrue(body, body.contains("\"name\":null"))
        assertTrue(body, body.contains("\"from\":null"))
        // A gift's address carries nothing of it.
        assertEquals("https://example.com/p/TestLink0001", handedOut())
    }

    private fun editable(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    // Copying.

    @Test
    fun copyPutsTheAddressOnTheClipboardWithTheSuccessTapAndSaysCopiedForTwoSeconds() {
        show { shareLinks[gift] = giftLink }
        openShare()
        compose.mainClock.autoAdvance = false
        node(PARCEL_SHARE_COPY).performClick()
        compose.mainClock.advanceTimeBy(100)
        val clipboard = compose.activity.getSystemService(ClipboardManager::class.java)
        assertEquals("https://example.com/p/TestLink2345", clipboard.primaryClip?.getItemAt(0)?.text?.toString())
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        assertEquals(hapticFeedbackConstant(SensoryFeedback.Success), shadowOf(view).lastHapticFeedbackPerformed())
        assertTrue(shows(PARCEL_SHARE_COPY, "Copied"))
        compose.onNodeWithTag(ANNOUNCEMENT, useUnmergedTree = true).assertContentDescriptionEquals("Link copied")
        assertEquals(listOf(DeliveryAnalytics.Outcome.Success), shareActions())
        compose.mainClock.advanceTimeBy(1_800)
        assertTrue(shows(PARCEL_SHARE_COPY, "Copied"))
        compose.mainClock.advanceTimeBy(200)
        assertTrue(shows(PARCEL_SHARE_COPY, "Copy"))
        assertFalse(shows(PARCEL_SHARE_COPY, "Copied"))
        // Nothing went to the system's share sheet.
        assertNull(handedOut())
    }

    // The sheet.

    /**
     * How much smaller the floating sheet draws what it holds: laid out across the window, and drawn scaled into the
     * panel, whose bounds give positions as drawn and sizes as laid out.
     */
    private fun sheetScale(): Float = node(SHEET_PANEL).getUnclippedBoundsInRoot().width / compose.onRoot().getUnclippedBoundsInRoot().width

    @Test
    fun onAPhoneTheSheetIsAsTallAsWhatItShowsThenOpensFullyForTheGiftsWords() {
        show()
        openShare()
        val panel = node(SHEET_PANEL).getUnclippedBoundsInRoot()
        val content = node(PARCEL_SHARE).getUnclippedBoundsInRoot()
        assertEquals(content.top.value, panel.top.value, 1f)
        assertEquals(panel.height.value, content.height.value * sheetScale(), 1f)
        assertTrue("$panel", panel.top > 150.dp)
        flip(PARCEL_SHARE_GIFT)
        settle()
        assertFalse(work().large)
        node(PARCEL_SHARE_NOTE).performClick()
        settle()
        assertTrue(work().large)
        val large = node(SHEET_PANEL).getUnclippedBoundsInRoot()
        assertTrue("$large", large.top < 80.dp)
        assertEquals(1f, sheetScale(), 0.01f)
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun onATabletTheFormIsAsTallAsWhatItShows() {
        show()
        openShare()
        val panel = node(SHEET_PANEL).getUnclippedBoundsInRoot()
        val content = node(PARCEL_SHARE).getUnclippedBoundsInRoot()
        assertEquals(content.bottom.value, panel.bottom.value, 1f)
        assertEquals(content.top.value, panel.top.value, 1f)
        assertTrue("${panel.bottom - panel.top}", panel.bottom - panel.top < 620.dp)
    }

    @Test
    fun whileSharingIsUnderWayNothingSendsTheSheetAway() {
        show { shareSaving = CompletableDeferred() }
        openShare()
        node(PARCEL_SHARE_ACTION).performClick()
        settle()
        assertTrue(spins(PARCEL_SHARE_ACTION))
        node(PARCEL_SHARE_CLOSE).assertIsNotEnabled()
        back()
        assertTrue(exists(PARCEL_SHARE))
        source.shareSaving?.complete(Unit)
        settle()
        assertFalse(spins(PARCEL_SHARE_ACTION))
        assertEquals("https://example.com/p/TestLink0001", handedOut())
        node(PARCEL_SHARE_CLOSE).assertIsEnabled()
        back()
        assertFalse(exists(PARCEL_SHARE))
        assertTrue(exists(PAGE_CONTENT))
    }

    @Test
    fun theSheetItsWordsAndItsDetentStayThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        openShare()
        flip(PARCEL_SHARE_GIFT)
        settle()
        node(PARCEL_SHARE_NOTE).performClick()
        node(PARCEL_SHARE_NOTE).performTextReplacement("Happy birthday")
        settle()
        recreation.recreate()
        settle()
        assertTrue(exists(PARCEL_SHARE))
        assertEquals(ToggleableState.On, state(PARCEL_SHARE_GIFT))
        assertEquals("Happy birthday", editable(PARCEL_SHARE_NOTE))
        assertTrue(node(SHEET_PANEL).getUnclippedBoundsInRoot().top < 80.dp)
    }

    @Test
    fun aShareUnderWayStaysThroughTheWindowMadeAgainAndTheNewWindowHandsItOut() {
        val recreation = Recreation(compose)
        show(recreation = recreation) { shareSaving = CompletableDeferred() }
        openShare()
        node(PARCEL_SHARE_ACTION).performClick()
        compose.waitForIdle()
        recreation.recreate()
        settle()
        assertTrue(exists(PARCEL_SHARE))
        assertTrue(spins(PARCEL_SHARE_ACTION))
        source.shareSaving?.complete(Unit)
        settle()
        assertEquals("https://example.com/p/TestLink0001", handedOut())
        assertEquals(1, source.shareRequests.size)
    }

    @Test
    fun aSwitchOnItsWayStaysThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation) {
            shareLinks[gift] = giftLink
            shareSaving = CompletableDeferred()
        }
        openShare()
        flip(PARCEL_SHARE_NUMBER)
        recreation.recreate()
        settle()
        assertEquals(ToggleableState.On, state(PARCEL_SHARE_NUMBER))
        node(PARCEL_SHARE_COPY).assertIsNotEnabled()
        source.shareSaving?.complete(Unit)
        settle()
        assertEquals(ToggleableState.On, state(PARCEL_SHARE_NUMBER))
        assertTrue(source.shareLinks[gift]!!.showNumber)
        node(PARCEL_SHARE_COPY).assertIsEnabled()
    }

    // TalkBack.

    @Test
    fun talkBackReadsTheHeadingTheSwitchesAndTheLinkWithItsWholeAddress() {
        show { shareLinks[gift] = giftLink }
        openShare()
        assertTrue(SemanticsProperties.Heading in node(PARCEL_SHARE_TITLE).fetchSemanticsNode().config)
        assertEquals(listOf("Close"), node(PARCEL_SHARE_CLOSE).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription))
        for ((tag, title) in listOf(
            PARCEL_SHARE_NUMBER to "Show the full number",
            PARCEL_SHARE_NAME to "Show its name",
            PARCEL_SHARE_GIFT to "Wrap as a gift",
        )) {
            val config = node(tag).fetchSemanticsNode().config
            assertEquals(Role.Switch, config.getOrNull(SemanticsProperties.Role))
            assertEquals(ToggleableState.Off, config.getOrNull(SemanticsProperties.ToggleableState))
            assertTrue(config.getOrNull(SemanticsProperties.Text).orEmpty().any { it.text == title })
            assertTrue(SemanticsActions.OnClick in config)
        }
        node(PARCEL_SHARE_NUMBER).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        assertEquals(ToggleableState.On, state(PARCEL_SHARE_NUMBER))
        // The switch itself is no other stop.
        assertEquals(
            3,
            compose.onAllNodes(hasAnyAncestor(hasTestTag(PARCEL_SHARE)) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Switch))
                .fetchSemanticsNodes().size,
        )
        val address = node(PARCEL_SHARE_ADDRESS).fetchSemanticsNode().config
        assertEquals(listOf("Parcel link"), address.getOrNull(SemanticsProperties.ContentDescription))
        assertEquals("https://example.com/p/TestLink2345", address.getOrNull(SemanticsProperties.StateDescription))
    }

    @Test
    fun talkBackGoesBackToTheShareButtonOnceTheSheetCloses() {
        show()
        val root = compose.onRoot().fetchSemanticsNode().root as ViewRootForTest
        val manager = shadowOf(compose.activity.getSystemService(AccessibilityManager::class.java))
        compose.runOnUiThread {
            manager.setEnabled(true)
            manager.setTouchExplorationEnabled(true)
            root.forceAccessibilityForTesting(true)
        }
        openShare()
        node(PARCEL_SHARE_CLOSE).performClick()
        settle()
        assertFalse(exists(PARCEL_SHARE))
        val share = node(PAGE_SHARE).fetchSemanticsNode().id
        var focused = false
        compose.runOnUiThread { focused = root.view.accessibilityNodeProvider.createAccessibilityNodeInfo(share)?.isAccessibilityFocused == true }
        assertTrue(focused)
    }

    @Test
    fun aClosedSheetStopsReadingItsLinkAndOpensAfreshNextTime() {
        show { shareLoading = CompletableDeferred() }
        openShare()
        val first = deliveries.sharing!!.id
        node(PARCEL_SHARE_CLOSE).performClick()
        settle()
        assertNull(source.shares.sheet(first))
        source.shareLoading = null
        node(PAGE_SHARE).performClick()
        settle()
        assertNotNull(deliveries.sharing)
        assertTrue(first != deliveries.sharing!!.id)
        assertFalse(spins(PARCEL_SHARE_LINK))
        assertTrue(exists(PARCEL_SHARE_PENDING))
    }
}
