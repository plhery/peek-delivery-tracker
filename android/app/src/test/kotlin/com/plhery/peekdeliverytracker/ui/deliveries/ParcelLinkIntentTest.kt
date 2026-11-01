package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
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
 * or came to it, in the site's form or the app's scheme; the window made again keeps the sheet up without reading the
 * link again; an address that is no parcel's link goes on to the parcel it names. The app takes a link before its
 * stores are made.
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
