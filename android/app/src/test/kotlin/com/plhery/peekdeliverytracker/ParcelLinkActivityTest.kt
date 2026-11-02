package com.plhery.peekdeliverytracker

import android.Manifest
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Looper
import android.provider.Settings
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.NativeRoute
import com.plhery.peekdeliverytracker.platform.AppNotifications
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.FakeAndroidKeyStore
import com.plhery.peekdeliverytracker.platform.MemoryCookieJar
import com.plhery.peekdeliverytracker.platform.PeekHttp
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_WELCOME
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_GIFT_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import java.io.IOException
import java.security.Security
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Where a parcel's link comes in (A-L02, B-L01): on the site's hosts and in the app's scheme it goes to
 * [ParcelLinkActivity], which shows nothing in a task Recents neither shows nor keeps, and the app's other addresses to
 * its window. The link reaches the app's stores in memory, owner key and all, and the window it opens carries none of
 * it: cold, the window opens the sheet once its stores are made; with the app running, the sheet opens over what is up
 * in the window brought forward. A friend's invitation (`/i/<id>`, `/invite#<code>`, the scheme's `invite`) comes in
 * the same way and stands over the window. Each link the app is opened with is counted once (`deep-link-open`), by
 * the trampoline or by the window, cold or running, and not again as the window turns; a notification opened as the
 * iPhone counts one (`notification-open`). On the app's own stores, made
 * offline with links read from memory, the analytics answered on the client itself.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelLinkActivityTest {
    @get:Rule
    val compose = createEmptyComposeRule()

    private val app = ApplicationProvider.getApplicationContext<PeekApplication>()
    private val configuration = BuildSettings.configuration
    private val site = checkNotNull(Uri.parse(configuration.apiBaseUrl).host)
    private val id = LinkFixtures.viewer().link.id
    private val gift = LinkFixtures.wrappedGift().link.id
    private val catalog = CarrierCatalog.shared
    private var made: AppContainer? = null

    @Before
    fun offline() {
        // Animations off: a guest with nothing followed sees the first open, whose carriers roll on frame after frame,
        // and Compose would never be idle.
        Settings.Global.putFloat(app.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 0f)
        app.makeStores = { application ->
            withContext(Dispatchers.IO) {
                AppContainer(
                    application, configuration.copy(mode = AppConfiguration.Mode.Demo),
                    http = OkHttpClient.Builder().addInterceptor { throw IOException("No network in this test") }.build(),
                    linkReader = { linkId, _ -> if (linkId == gift) LinkFixtures.wrappedGift() else LinkFixtures.viewer() },
                )
            }.also { made = it }
        }
    }

    @After
    fun stop() {
        made?.scope?.cancel()
        CarrierCatalog.install(catalog)
        Settings.Global.putFloat(app.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f)
        Security.removeProvider("AndroidKeyStore")
    }

    /** What Umami's collector received, answered on the client itself. */
    private val sent = mutableListOf<JSONObject>()

    /**
     * The stores made against the API instead, signed out, so that the analytics count: the server and Umami's
     * collector are answered on the client itself (synthetic hosts), so nothing leaves the machine.
     */
    private fun counting() {
        Security.addProvider(FakeAndroidKeyStore())
        val answers = Interceptor { chain ->
            val request = chain.request()
            val (code, body) = when {
                request.url.encodedPath == "/api/analytics/config" -> 200 to
                    """{"endpoint":"https://collector.example.test/api/send","hostname":"peek.example.test","androidWebsite":"00000000-0000-4000-8000-0000000000a1"}"""
                request.url.host == "collector.example.test" -> {
                    val event = JSONObject(Buffer().also { request.body!!.writeTo(it) }.readUtf8()).getJSONObject("payload")
                    synchronized(sent) { sent += event }
                    200 to "{}"
                }
                else -> 404 to "{}"
            }
            Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(code).message("").body(body.toResponseBody()).build()
        }
        val server = "https://peek.example.test"
        val api = AppConfiguration(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = server, supabaseUrl = server, supabasePublishableKey = "synthetic-publishable-key",
            googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "group.test.peek",
        )
        app.makeStores = { application ->
            withContext(Dispatchers.IO) {
                AppContainer(application, api, http = PeekHttp.client(MemoryCookieJar()).newBuilder().addInterceptor(answers).build())
            }.also { made = it }
        }
    }

    /** How many times the app was counted opened with a link, or as [event] says. */
    private fun opens(event: String = DEEP_LINK_OPEN): Int = synchronized(sent) { sent.count { it.optString("name") == event } }

    /** The app counted opened with a link (or as [event] says) [count] times, and no more once what it had to send has gone. */
    private fun assertOpens(count: Int, event: String = DEEP_LINK_OPEN) {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(30)
        while (opens(event) < count && System.nanoTime() < deadline) {
            shadowOf(Looper.getMainLooper()).idle()
            Thread.sleep(10)
        }
        repeat(20) {
            shadowOf(Looper.getMainLooper()).idle()
            Thread.sleep(10)
        }
        assertEquals(count, opens(event))
    }

    private fun link(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url)).addCategory(Intent.CATEGORY_BROWSABLE)

    /** What opening the notification of the push with data `data` starts, as the app posts it. */
    private fun notification(data: Map<String, String>): Intent {
        shadowOf(app).grantPermissions(Manifest.permission.POST_NOTIFICATIONS)
        AppNotifications(app).post(data)
        val tag = AppNotifications.tag(NativeRoute(remoteNotification = data))
        val shade = shadowOf(app.getSystemService(NotificationManager::class.java))
        return shadowOf(requireNotNull(shade.getNotification(tag, AppNotifications.ID)).contentIntent).savedIntent
    }

    /** The activities of this package a link to `url` aimed at it opens, as a browser sends one. */
    private fun takers(url: String): List<String> =
        app.packageManager.queryIntentActivities(link(url).setPackage(app.packageName), 0).map { it.activityInfo.name }

    /** The link `url` coming in: what the trampoline started, once it has gone. */
    private fun send(url: String): Intent {
        val trampoline = Robolectric.buildActivity(ParcelLinkActivity::class.java, link(url)).create()
        assertTrue("It finishes as it starts", trampoline.get().isFinishing)
        val started = checkNotNull(shadowOf(trampoline.get()).nextStartedActivity)
        assertEquals(ComponentName(app, MainActivity::class.java), started.component)
        assertEquals("The launcher's intent", Intent.ACTION_MAIN, started.action)
        assertNull("No link in the window's intent", started.data)
        assertTrue("The window in the app's own task", started.flags and Intent.FLAG_ACTIVITY_NEW_TASK != 0)
        return started
    }

    /** The stores are made on the IO dispatcher, then started on the main thread. */
    private fun awaitStores(): AppContainer {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(30)
        while (app.container.value == null && System.nanoTime() < deadline) {
            shadowOf(Looper.getMainLooper()).idle()
            Thread.sleep(10)
        }
        return checkNotNull(app.container.value)
    }

    /** The session read, on the IO threads, and the launch screen gone. */
    private fun awaitSession(stores: AppContainer) {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(30)
        while (stores.session.state == AuthenticationState.Loading && System.nanoTime() < deadline) {
            shadowOf(Looper.getMainLooper()).idle()
            Thread.sleep(10)
        }
        assertNotEquals(AuthenticationState.Loading, stores.session.state)
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    @Test
    fun theSitesHostsAndTheAppsSchemeGoToTheTrampolineAndTheAppsOtherAddressesToItsWindow() {
        val trampoline = listOf(ParcelLinkActivity::class.java.name)
        for (host in setOf(site) + configuration.linkHosts) assertEquals(host, trampoline, takers("https://$host/p/$id"))
        assertEquals(trampoline, takers("peekdeliverytracker://p/$id"))
        assertEquals(listOf(MainActivity::class.java.name), takers("peekdeliverytracker://parcel/${DemoCards.parcel("Kind of Blue").id}"))
        assertEquals(listOf(MainActivity::class.java.name), takers("peekdeliverytracker://friend/11000000-0000-4000-8000-000000000001"))
        assertEquals(emptyList<String>(), takers("https://$site/privacy.html"))
        assertEquals(emptyList<String>(), takers("https://elsewhere.example/p/$id"))
    }

    @Test
    fun theTrampolineShowsNothingInATaskOfItsOwnThatRecentsNeitherShowsNorKeeps() {
        val info = app.packageManager.getActivityInfo(ComponentName(app, ParcelLinkActivity::class.java), 0)
        assertEquals(android.R.style.Theme_NoDisplay, info.theme)
        assertTrue("Left out of Recents", info.flags and ActivityInfo.FLAG_EXCLUDE_FROM_RECENTS != 0)
        assertTrue("Gone once left", info.flags and ActivityInfo.FLAG_NO_HISTORY != 0)
        assertTrue("No affinity for the app's task: ${info.taskAffinity}", info.taskAffinity.isNullOrEmpty())
        assertNotEquals(info.taskAffinity, app.packageManager.getActivityInfo(ComponentName(app, MainActivity::class.java), 0).taskAffinity)
    }

    @Test
    fun aLinkThatStartsTheAppWaitsForItsStoresInMemoryAndTheWindowOpensItsSheetWithoutIt() {
        val started = send("https://$site/p/$id#k=${LinkFixtures.KEY}")
        assertFalse(started.toUri(0).contains(LinkFixtures.KEY))
        assertNull("Nothing made yet: the link waits in the process", app.container.value)

        val window = Robolectric.buildActivity(MainActivity::class.java, started).setup()
        val route = awaitStores().links.route
        assertEquals(id, route?.id)
        assertEquals("Its key, in memory", LinkFixtures.KEY, route?.key)
        settle()
        assertTrue(exists(LINK_CARD))
        assertNull(window.get().intent.data)
    }

    @Test
    fun aLinkWhileTheAppRunsBringsItsWindowForwardAndOpensInItsSheetThere() {
        val window = Robolectric.buildActivity(MainActivity::class.java, Intent.makeMainActivity(ComponentName(app, MainActivity::class.java))).setup()
        val links = awaitStores().links
        settle()
        assertFalse(exists(LINK_SHEET))

        window.newIntent(send("peekdeliverytracker://p/$id"))
        settle()
        assertEquals(id, links.route?.id)
        assertTrue(exists(LINK_CARD))

        window.newIntent(send("https://$site/p/$gift"))
        settle()
        assertEquals(gift, links.route?.id)
        assertTrue(exists(LINK_GIFT_CARD))
        assertFalse(exists(LINK_CARD))
    }

    @Test
    fun aFriendsInvitationOnTheSitesHostsAndTheAppsSchemeGoesToTheTrampolineToo() {
        val trampoline = listOf(ParcelLinkActivity::class.java.name)
        for (host in setOf(site) + configuration.linkHosts) {
            assertEquals(host, trampoline, takers("https://$host/i/$INVITATION"))
            assertEquals(host, trampoline, takers("https://$host/invite#$INVITATION"))
            assertEquals(host, trampoline, takers("https://$host/invite/#$INVITATION"))
        }
        assertEquals(trampoline, takers("peekdeliverytracker://invite#$INVITATION"))
        assertEquals(emptyList<String>(), takers("https://elsewhere.example/invite#$INVITATION"))
    }

    @Test
    fun anInvitationThatStartsTheAppWaitsForItsStoresAndStandsOverTheWindow() {
        val started = send("https://$site/invite#$INVITATION")
        assertFalse(started.toUri(0).contains(INVITATION))
        assertNull("Nothing made yet: the invitation waits in the process", app.container.value)

        Robolectric.buildActivity(MainActivity::class.java, started).setup()
        val stores = awaitStores()
        assertTrue(stores.invitation.isPresenting)
        assertEquals(INVITATION, stores.invitation.code)
        // Over everything but the launch screen, which stays while the session is read on the IO threads.
        awaitSession(stores)
        settle()
        assertTrue(exists(INVITATION_WELCOME))
    }

    @Test
    fun anInvitationWhileTheAppRunsStandsOverTheWindowBroughtForward() {
        val window = Robolectric.buildActivity(MainActivity::class.java, Intent.makeMainActivity(ComponentName(app, MainActivity::class.java))).setup()
        val invitation = awaitStores().invitation
        settle()
        assertFalse(exists(INVITATION_WELCOME))

        window.newIntent(send("peekdeliverytracker://invite#$INVITATION"))
        settle()
        assertTrue(invitation.isPresenting)
        assertEquals(INVITATION, invitation.code)
        assertTrue(exists(INVITATION_WELCOME))

        window.newIntent(send("https://$site/i/$OTHER_INVITATION"))
        settle()
        assertEquals(OTHER_INVITATION, invitation.code)
        assertTrue(exists(INVITATION_WELCOME))
    }

    @Test
    fun eachLinkThatComesByTheTrampolineOrToTheRunningWindowIsCountedOnce() {
        counting()
        // Cold: counted once the stores are made and the first screen counted.
        val window = Robolectric.buildActivity(MainActivity::class.java, send("https://$site/invite#$INVITATION")).setup()
        awaitStores()
        assertOpens(1)

        window.newIntent(send("https://$site/p/$id"))
        assertOpens(2)
        // Sent to the window itself: a friend to reveal.
        window.newIntent(link("peekdeliverytracker://friend/$FRIEND").setClass(app, MainActivity::class.java))
        assertOpens(3)
        window.recreate()
        assertOpens(3)
    }

    @Test
    fun aLinkThatStartsTheWindowItselfIsCountedOnceAndNotAgainAsItTurns() {
        counting()
        val window = Robolectric.buildActivity(MainActivity::class.java, link("peekdeliverytracker://friend/$FRIEND").setClass(app, MainActivity::class.java)).setup()
        awaitStores()
        assertOpens(1)
        window.recreate()
        assertOpens(1)
        // The launcher's own start is no link.
        window.newIntent(Intent.makeMainActivity(ComponentName(app, MainActivity::class.java)))
        assertOpens(1)
    }

    /**
     * A notification opened is counted as the iPhone counts it (`notification-open`), and as no link: a friend's once
     * the stores are made, even as it starts the app, and not again as the window turns; a parcel's while the
     * deliveries are there to hear it.
     */
    @Test
    fun aNotificationOpenedIsCountedAsTheIPhoneCountsIt() {
        counting()
        val friend = mapOf("title" to "Peek", "body" to "A friend accepted your invitation", "kind" to "friend_accepted", "friend_id" to FRIEND)
        val window = Robolectric.buildActivity(MainActivity::class.java, notification(friend)).setup()
        val stores = awaitStores()
        awaitSession(stores)
        assertEquals(AuthenticationState.Welcome, stores.session.state)
        assertOpens(1, NOTIFICATION_OPEN)
        window.recreate()
        assertOpens(1, NOTIFICATION_OPEN)
        window.newIntent(notification(mapOf("title" to "Books", "body" to "Out for delivery", "parcel_id" to PARCEL)))
        assertOpens(2, NOTIFICATION_OPEN)
        assertOpens(0)
    }

    /** ParcelListView's `.onAppear` takes the parcel of a notification that starts the app without counting it. */
    @Test
    fun aParcelsNotificationThatStartsTheAppIsNotCounted() {
        counting()
        Robolectric.buildActivity(MainActivity::class.java, notification(mapOf("title" to "Books", "body" to "Out for delivery", "parcel_id" to PARCEL))).setup()
        awaitSession(awaitStores())
        assertOpens(0, NOTIFICATION_OPEN)
    }

    private companion object {
        /** A made-up friend's id. */
        const val FRIEND = "11000000-0000-4000-8000-000000000001"

        /** A made-up parcel's id. */
        const val PARCEL = "12000000-0000-4000-8000-000000000002"

        const val DEEP_LINK_OPEN = "deep-link-open"
        const val NOTIFICATION_OPEN = "notification-open"

        /** Made-up invitation codes, in the 16-character form the service gives. */
        const val INVITATION = "Qa3vT8nL5wZ1pR7k"
        const val OTHER_INVITATION = "Zx4cV9bN2mL6kJ8h"
    }
}
