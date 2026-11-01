package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelLinkClaim
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import java.io.IOException
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A window of the app as far as a parcel's link goes: its inbox hands a link to the sheet's store first, read as the
 * app reads one, against the build's site and link hosts; the sheet's stores outlive the window, as the app's do.
 */
class ParcelLinkWindow : ComponentActivity() {
    val views = mutableListOf<String>()
    lateinit var inbox: DeliveriesInbox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inbox = DeliveriesInbox.of(this) { url ->
            val configuration = BuildSettings.configuration
            val route = ParcelLinkRoute(url, configuration.apiBaseUrl, configuration.linkHosts)
            route?.let(links.links::open)
            route != null
        }
        setContent { RootHost(state = AuthenticationState.Demo, onView = { views += it }, inbox = inbox, links = links) }
    }

    companion object {
        lateinit var links: FakeParcelLinks
    }
}

/**
 * A parcel's link comes first in the window's routing (A-L02, B-L01): it opens its sheet whether it started the window
 * or came to it, in the site's form or the app's scheme, once; the window made again keeps the sheet up without reading
 * the link again, and a keep under way with it, and a pull's gap until its read is over; a window Recents starts again with the link doesn't follow it again,
 * and the window's intent forgets the link once read; an address that is no parcel's link goes on to the parcel it
 * names. The app takes a parcel's link, and nothing else, before its stores are made (ParcelLinkActivityTest opens it
 * once they are).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelLinkIntentTest {
    @get:Rule
    val compose = createEmptyComposeRule()

    private val id = LinkFixtures.viewer().link.id
    private val gift = LinkFixtures.wrappedGift().link.id

    /** The site's address for link `id`, on the build's first link host, else the app's scheme. */
    private fun siteLink(id: String): String {
        val host = BuildSettings.configuration.linkHosts.firstOrNull() ?: return "peekdeliverytracker://p/$id"
        return "https://$host/p/$id"
    }

    private fun intent(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    private fun window(intent: Intent? = null): ActivityController<ParcelLinkWindow> =
        (if (intent == null) Robolectric.buildActivity(ParcelLinkWindow::class.java) else Robolectric.buildActivity(ParcelLinkWindow::class.java, intent))
            .setup()
            .also { settle() }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun links(): FakeParcelLinks =
        FakeParcelLinks().apply { read = { id, _ -> if (id == gift) LinkFixtures.wrappedGift() else LinkFixtures.viewer() } }
            .also { ParcelLinkWindow.links = it }

    @Test
    fun aLinkThatStartsTheWindowOpensItsSheet() {
        val links = links()
        val window = window(intent(siteLink(id)))
        assertEquals(ParcelLinkRoute(id = id), links.links.route)
        assertTrue(exists(LINK_CARD))
        assertNull(window.get().inbox.parcel.value)
        assertEquals(emptyList<String>(), window.get().views.filter { it == "parcel" })
    }

    @Test
    fun aLinkThatComesToTheWindowOpensItsSheetAndAnotherReplacesIt() {
        val links = links()
        val window = window()
        assertFalse(exists(LINK_SHEET))
        window.newIntent(intent("peekdeliverytracker://p/$id"))
        settle()
        assertTrue(exists(LINK_CARD))
        window.newIntent(intent(siteLink(gift)))
        settle()
        assertEquals(gift, links.links.route?.id)
        assertTrue(exists(LINK_GIFT_CARD))
        assertFalse(exists(LINK_CARD))
    }

    @Test
    fun theWindowMadeAgainKeepsTheSheetWithoutReadingTheLinkAgain() {
        val links = links()
        val window = window(intent(siteLink(id)))
        assertEquals(1, links.reads.size)
        window.recreate()
        settle()
        assertTrue(exists(LINK_CARD))
        assertEquals(1, links.reads.size)
        assertEquals(ParcelLinkRoute(id = id), links.links.route)
    }

    @Test
    fun aKeepUnderWayOutlivesTheWindowMadeAgainWithCloseOffAndItsErrorShownThere() {
        val claim = CompletableDeferred<ParcelLinkClaim>()
        val links = FakeParcelLinks(answer = LinkFixtures.keepable(), session = FakeParcelLinks.READER)
            .apply { keeping = { claim.await() } }
            .also { ParcelLinkWindow.links = it }
        val window = window(intent(siteLink(id)))
        compose.onNodeWithTag(LINK_ADD).performClick()
        settle()
        compose.onNodeWithTag(LINK_CLOSE).assertIsNotEnabled()

        window.recreate()
        settle()
        compose.onNodeWithTag(LINK_CLOSE).assertIsNotEnabled()
        compose.onNodeWithTag(LINK_ADD).assertIsNotEnabled()
        compose.onNodeWithTag(LINK_ADD).performClick()
        window.get().onBackPressedDispatcher.onBackPressed()
        settle()
        assertTrue("The back keeps the sheet while keeping", exists(LINK_SHEET))
        assertEquals("One claim", 1, links.kept.size)

        claim.completeExceptionally(IOException("The keep failed"))
        settle()
        assertTrue(compose.onAllNodesWithText("The keep failed", substring = true, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty())
        compose.onNodeWithTag(LINK_CLOSE).assertIsEnabled()
        assertEquals(1, links.kept.size)
    }

    @Test
    fun thePullsGapStaysOpenInTheWindowMadeAgainUntilItsReadIsOverAndTheButtonsOpensNone() {
        val links = links()
        val window = window(intent(siteLink(id)))
        val rest = compose.onNodeWithTag(LINK_CARD).getBoundsInRoot().top
        fun gap() = (compose.onNodeWithTag(LINK_CARD).getBoundsInRoot().top - rest).value
        var answer = CompletableDeferred<PublicParcelResponse?>()
        links.read = { _, _ -> answer.await() }
        compose.onNodeWithTag(LINK_SCROLL).performTouchInput {
            down(center)
            repeat(28) { moveBy(Offset(0f, 25.dp.toPx())) }
            up()
        }
        settle()
        assertEquals("Pulled far enough, the link is read again", 2, links.reads.size)
        assertEquals("The gap the refresh control holds open", 60f, gap(), 1f)

        window.recreate()
        settle()
        assertTrue(links.sheet.refreshing)
        assertEquals("Still open in the window made again", 60f, gap(), 1f)
        answer.complete(LinkFixtures.viewer())
        settle()
        assertEquals("Closed once the read is over", 0f, gap(), 0.5f)
        assertEquals(2, links.reads.size)

        // The refresh button's read holds no gap open, in the window made again either.
        answer = CompletableDeferred()
        compose.onNodeWithTag(LINK_REFRESH).performClick()
        settle()
        window.recreate()
        settle()
        assertEquals(3, links.reads.size)
        assertTrue(links.sheet.refreshing)
        assertEquals(0f, gap(), 0.5f)
        answer.complete(LinkFixtures.viewer())
        settle()
        assertFalse(links.sheet.refreshing)
    }

    @Test
    fun aWindowRecentsStartsAgainWithTheLinkThatBeganItsTaskDoesNotFollowItAgain() {
        val links = links()
        val again = intent(siteLink(id) + "#k=${LinkFixtures.KEY}").addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY)
        val window = window(again)
        assertNull(links.links.route)
        assertEquals(emptyList<Pair<String, String?>>(), links.reads)
        assertFalse(exists(LINK_SHEET))
        assertNull("The window's intent forgets it", window.get().intent.data)
    }

    @Test
    fun theWindowsIntentForgetsTheLinkAndItsKeyOnceRead() {
        val links = links()
        val window = window(intent(siteLink(id) + "#k=${LinkFixtures.KEY}"))
        assertEquals("Read with its key", listOf(id to LinkFixtures.KEY), links.reads)
        assertNull(window.get().intent.data)
        assertFalse(window.get().intent.toUri(0).contains(LinkFixtures.KEY))
    }

    @Test
    fun anAddressThatIsNoParcelsLinkGoesOnToTheParcelItNames() {
        val links = links()
        val window = window(intent("peekdeliverytracker://parcel/${DemoCards.parcel("Kind of Blue").id}"))
        assertNull(links.links.route)
        assertFalse(exists(LINK_SHEET))
        assertEquals(listOf("parcel"), window.get().views.filter { it == "parcel" })
    }

    @Test
    fun theAppTakesAParcelsLinkBeforeItsStoresAreMadeAndNothingElse() {
        val app = ApplicationProvider.getApplicationContext<PeekApplication>()
        assertTrue(app.openParcelLink(siteLink(id)))
        assertTrue(app.openParcelLink("peekdeliverytracker://p/$id"))
        assertFalse(app.openParcelLink("peekdeliverytracker://parcel/${DemoCards.parcel("Kind of Blue").id}"))
        assertFalse(app.openParcelLink("https://elsewhere.example/p/$id"))
    }
}
