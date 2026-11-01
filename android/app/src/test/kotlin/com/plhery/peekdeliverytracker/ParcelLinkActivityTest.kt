package com.plhery.peekdeliverytracker

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Looper
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_GIFT_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
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
 * in the window brought forward. On the app's own stores, made offline with links read from memory.
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
    }

    private fun link(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url)).addCategory(Intent.CATEGORY_BROWSABLE)

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
}
