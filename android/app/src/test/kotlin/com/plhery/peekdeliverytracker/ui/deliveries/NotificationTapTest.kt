package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.NativeRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.platform.AppNotifications
import com.plhery.peekdeliverytracker.platform.notificationTap
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** A window of the app as far as a notification opened goes: its own inbox, the routes it counted, and its screens. */
class NotifiedWindow : ComponentActivity() {
    val views = mutableListOf<String>()
    val opened = mutableListOf<NativeRoute>()
    lateinit var inbox: DeliveriesInbox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inbox = DeliveriesInbox.of(this, notificationOpened = { opened += it })
        setContent { RootHost(state = state, onView = { views += it }, inbox = inbox, links = links) }
    }

    companion object {
        var state: AuthenticationState = AuthenticationState.Demo
        var links: FakeParcelLinks? = null
    }
}

/**
 * A notification opened (C-W15, A-W04, A-L10): the window opens with the push's data, whether it starts the window
 * or comes to it, and follows it once. A parcel's opens on the deliveries; a link's opens its sheet, or the parcel among
 * this phone's parcels when nobody is signed in and this phone follows it, once the session is read; the window made
 * again doesn't follow it again, and its intent forgets the push's data. A friend or a link still waiting is kept
 * through a turn and after Android ended the process. An intent any app could send, with a notification's action and
 * data, is followed by nothing. Each is counted once by the app ([NotifiedWindow.opened]); friends' are revealed in
 * FriendsTest, the app's counts in ParcelLinkActivityTest.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class NotificationTapTest {
    @get:Rule
    val compose = createEmptyComposeRule()

    private val parcel = DemoCards.parcel("Kind of Blue").id
    private val link = LinkFixtures.viewer().link.id
    private val friend = UUID(0, 303)

    @Before
    fun start() {
        NotifiedWindow.state = AuthenticationState.Demo
        NotifiedWindow.links = null
    }

    /**
     * What opening the notification of the push with data `data` starts, as the app posts it: the test's window takes
     * it as it is, the alias the app's window answers to included.
     */
    private fun tapped(data: Map<String, String>): Intent = notificationTap(data)

    private fun parcelPush() = mapOf("title" to "Kind of Blue", "body" to "Out for delivery", "parcel_id" to "$parcel")

    private fun linkPush() = mapOf("title" to "Books", "body" to "Delivered", "link_id" to link)

    private fun friendPush() = mapOf("title" to "Peek", "body" to "A friend accepted your invitation", "kind" to "friend_accepted", "friend_id" to "$friend")

    /**
     * Android ending the process while the window is in the background, and starting it again as it comes back: its
     * first intent, the push's data included, and what it saved.
     */
    private fun ActivityController<NotifiedWindow>.processEnded(intent: Intent): ActivityController<NotifiedWindow> {
        val saved = Bundle()
        pause().stop().saveInstanceState(saved)
        destroy()
        return Robolectric.buildActivity(NotifiedWindow::class.java, intent).setup(saved).also { settle() }
    }

    private fun window(intent: Intent? = null): ActivityController<NotifiedWindow> =
        (if (intent == null) Robolectric.buildActivity(NotifiedWindow::class.java) else Robolectric.buildActivity(NotifiedWindow::class.java, intent))
            .setup()
            .also { settle() }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun links(session: AuthenticationState): FakeParcelLinks = FakeParcelLinks(session = session).also { NotifiedWindow.links = it }

    @Test
    fun aParcelsNotificationThatStartsTheWindowOpensTheParcel() {
        val window = window(tapped(parcelPush()))
        assertEquals(listOf("parcel"), window.get().views)
        assertEquals(listOf<NativeRoute>(NativeRoute.Parcel(parcel)), window.get().opened)
        assertNull(window.get().inbox.parcel.value)
        assertNull("The push's data forgotten", AppNotifications.payload(window.get().intent))
    }

    @Test
    fun aParcelsNotificationThatComesToTheWindowOpensTheParcelThere() {
        val window = window()
        window.newIntent(tapped(parcelPush()))
        settle()
        assertEquals(listOf("parcel"), window.get().views)
        assertEquals(listOf<NativeRoute>(NativeRoute.Parcel(parcel)), window.get().opened)
    }

    /**
     * Made again (a turn), the window gets what it saved and the intent it held last, which Android hands over
     * (`Activity.setIntent`'s survives a relaunch; Robolectric's recreate hands the first intent again): that intent
     * no longer carries the push.
     */
    @Test
    fun theWindowMadeAgainDoesNotFollowItAgain() {
        val first = window(tapped(parcelPush()))
        first.get().onBackPressedDispatcher.onBackPressed()
        settle()
        val saved = Bundle()
        first.pause().stop().saveInstanceState(saved)
        val held = first.get().intent
        first.destroy()
        val again = Robolectric.buildActivity(NotifiedWindow::class.java, held).setup(saved).also { settle() }
        assertNull("The push's data forgotten", AppNotifications.payload(again.get().intent))
        assertEquals(emptyList<String>(), again.get().views)
        assertEquals(emptyList<NativeRoute>(), again.get().opened)
    }

    /** `pendingFriendID` while nobody is signed in: kept through a turn and after Android ended the process, counted once. */
    @Test
    fun aFriendWaitingIsKeptThroughATurnAndAfterTheProcessEnded() {
        NotifiedWindow.state = AuthenticationState.Welcome
        val intent = tapped(friendPush())
        val window = window(intent)
        assertEquals(friend, window.get().inbox.notifiedFriend.value)
        assertEquals(listOf<NativeRoute>(NativeRoute.Friend(friend)), window.get().opened)

        window.recreate()
        settle()
        assertEquals("Through a turn", friend, window.get().inbox.notifiedFriend.value)
        assertEquals(emptyList<NativeRoute>(), window.get().opened)

        val restored = window.processEnded(intent)
        assertEquals("After the process ended", friend, restored.get().inbox.notifiedFriend.value)
        assertEquals(emptyList<NativeRoute>(), restored.get().opened)
    }

    /** `pendingLinkID` while the session is read: kept through a turn and after Android ended the process, then opened. */
    @Test
    fun aLinkWaitingIsKeptThroughATurnAndAfterTheProcessEnded() {
        NotifiedWindow.state = AuthenticationState.Welcome
        val links = links(AuthenticationState.Loading)
        val intent = tapped(linkPush())
        val window = window(intent)
        assertEquals(link, window.get().inbox.notifiedLink.value)

        window.recreate()
        settle()
        assertEquals("Through a turn", link, window.get().inbox.notifiedLink.value)

        val restored = window.processEnded(intent)
        assertEquals("After the process ended", link, restored.get().inbox.notifiedLink.value)
        assertNull(links.links.route)
        compose.runOnIdle { links.session.value = AuthenticationState.Welcome }
        settle()
        assertEquals(ParcelLinkRoute(id = link), links.links.route)
        assertEquals(listOf("notification-open"), links.actions)
        assertEquals(emptyList<NativeRoute>(), restored.get().opened)
    }

    /** An intent any app can send the window, with a notification's action and data: nothing opens, nothing is counted. */
    @Test
    fun anIntentWithANotificationsDataFromElsewhereIsNotFollowed() {
        NotifiedWindow.state = AuthenticationState.Welcome
        val links = links(AuthenticationState.Welcome)
        val forged = Intent(tapped(parcelPush())).setClass(ApplicationProvider.getApplicationContext(), NotifiedWindow::class.java)
        val window = window(forged)
        window.newIntent(Intent(tapped(linkPush())).setComponent(forged.component))
        settle()
        assertEquals(emptyList<String>(), window.get().views)
        assertEquals(emptyList<NativeRoute>(), window.get().opened)
        assertNull(links.links.route)
        assertEquals(emptyList<String>(), links.actions)
    }

    @Test
    fun aLinksNotificationOpensItsSheetCounted() {
        NotifiedWindow.state = AuthenticationState.Welcome
        val links = links(AuthenticationState.Welcome)
        val window = window(tapped(linkPush()))
        assertEquals(ParcelLinkRoute(id = link), links.links.route)
        assertEquals(listOf("notification-open"), links.actions)
        assertEquals(0, links.deviceParcelsShown)
        assertEquals(listOf<NativeRoute>(NativeRoute.Link(link)), window.get().opened)
        assertNull(window.get().inbox.notifiedLink.value)
    }

    @Test
    fun aLinksNotificationForAParcelThisPhoneFollowsOpensItAmongThisPhonesParcels() {
        NotifiedWindow.state = AuthenticationState.Welcome
        val links = links(AuthenticationState.Welcome).apply { followedLinks[link] = parcel }
        val window = window(tapped(linkPush()))
        assertNull(links.links.route)
        assertEquals(1, links.deviceParcelsShown)
        assertEquals(listOf("notification-open"), links.actions)
        assertEquals(listOf("parcel"), window.get().views)
    }

    @Test
    fun signedInALinksNotificationAlwaysOpensItsSheet() {
        val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 5), email = "reader@example.com"))
        NotifiedWindow.state = reader
        val links = links(reader).apply { followedLinks[link] = parcel }
        window(tapped(linkPush()))
        assertEquals(ParcelLinkRoute(id = link), links.links.route)
        assertEquals(0, links.deviceParcelsShown)
        assertEquals(listOf("notification-open"), links.actions)
    }

    @Test
    fun aLinksNotificationWaitsForTheSession() {
        NotifiedWindow.state = AuthenticationState.Welcome
        val links = links(AuthenticationState.Loading)
        val window = window(tapped(linkPush()))
        assertNull(links.links.route)
        assertEquals(link, window.get().inbox.notifiedLink.value)
        compose.runOnIdle { links.session.value = AuthenticationState.Welcome }
        settle()
        assertEquals(ParcelLinkRoute(id = link), links.links.route)
        assertNull(window.get().inbox.notifiedLink.value)
    }
}
