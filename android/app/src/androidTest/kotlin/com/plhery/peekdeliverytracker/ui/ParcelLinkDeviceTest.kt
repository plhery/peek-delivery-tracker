package com.plhery.peekdeliverytracker.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.verify.domain.DomainVerificationManager
import android.net.Uri
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.ParcelNumberHint
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_GIFT_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import java.io.IOException
import java.time.Duration
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * A parcel's link on this device (A-L02, B-L01), which the JVM tests stand in for with Robolectric's window: the app's
 * window takes a link on both of the hosts the manifest claims and in the app's scheme; a link aimed at the package, as
 * a browser sends one, starts the app's window, which shows its sheet once the stores are made, and another link that
 * comes while it runs opens in the same window's sheet in place of the first. On the app's own stores, made offline
 * with links read from memory: nothing goes to the network. The stores are made once a process, so run the class on
 * its own (`-e class`), before any other test makes them.
 */
@RunWith(AndroidJUnit4::class)
class ParcelLinkDeviceTest {
    @get:Rule
    val compose = createEmptyComposeRule()

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext
    private val configuration = BuildSettings.configuration
    private val site = checkNotNull(Uri.parse(configuration.apiBaseUrl).host)

    @After
    fun close() {
        val open = windows()
        instrumentation.runOnMainSync { open.forEach { it.finishAndRemoveTask() } }
    }

    private fun link(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url)).addCategory(Intent.CATEGORY_BROWSABLE)

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** The activities of this package a link to `url` aimed at it opens. */
    private fun takers(url: String): List<String> =
        context.packageManager.queryIntentActivities(link(url).setPackage(context.packageName), PackageManager.ResolveInfoFlags.of(0))
            .map { it.activityInfo.name }

    /** As a browser sends a link: in a task of its own, aimed at the package. */
    private fun send(url: String, flags: Int = 0) =
        context.startActivity(link(url).setPackage(context.packageName).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or flags))

    /** The app's windows in front. */
    private fun windows(): List<MainActivity> {
        var found = emptyList<MainActivity>()
        instrumentation.runOnMainSync {
            found = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED).filterIsInstance<MainActivity>()
        }
        return found
    }

    @Test
    fun theAppsWindowTakesALinkOnBothHostsTheManifestClaimsAndInTheAppsScheme() {
        // The hosts of the App Link filter, as the system reads them for verification: the site's and the one it
        // answered on before, the hosts whose links the app reads as a parcel's.
        val claimed = context.getSystemService(DomainVerificationManager::class.java)
            .getDomainVerificationUserState(context.packageName)?.hostToStateMap?.keys.orEmpty()
        assertEquals(setOf(site) + configuration.linkHosts, claimed)
        val window = listOf(MainActivity::class.java.name)
        for (host in claimed) assertEquals(host, window, takers("https://$host/p/$PLAIN"))
        assertEquals(window, takers("peekdeliverytracker://p/$PLAIN"))
        assertEquals(window, takers("peekdeliverytracker://parcel/${UUID.nameUUIDFromBytes(PLAIN.toByteArray())}"))
        assertEquals(emptyList<String>(), takers("https://$site/privacy.html"))
        assertEquals(emptyList<String>(), takers("https://elsewhere.example/p/$PLAIN"))
    }

    @Test
    fun aLinkStartsTheAppsWindowWithItsSheetAndAnotherReplacesItWhileItRuns() {
        val app = context.applicationContext as PeekApplication
        assumeTrue("The app's stores were made before this test, on the network: run the class on its own", app.container.value == null)
        val reads = mutableListOf<String>()
        app.makeStores = { application ->
            withContext(Dispatchers.IO) {
                // Offline, in demo mode, which prepares no browser check: nothing leaves the device.
                AppContainer(
                    application, configuration.copy(mode = AppConfiguration.Mode.Demo),
                    http = OkHttpClient.Builder().addInterceptor { throw IOException("No network in this test") }.build(),
                    linkReader = { id, _ -> synchronized(reads) { reads += id }; response(id, gift = id == GIFT) },
                )
            }
        }

        // The app not running: the window starts with the link, which waits for the stores and then opens.
        send("https://$site/p/$PLAIN", Intent.FLAG_ACTIVITY_CLEAR_TASK)
        compose.waitUntil(20_000) { exists(LINK_CARD) }
        val first = windows().single()
        val links = checkNotNull(app.container.value).links
        assertEquals(PLAIN, links.route?.id)

        // While it runs: the window on top takes the next link (singleTop), and its sheet opens it in place.
        send("peekdeliverytracker://p/$GIFT")
        compose.waitUntil(10_000) { exists(LINK_GIFT_CARD) && !exists(LINK_CARD) }
        assertEquals(GIFT, links.route?.id)
        assertEquals(listOf(PLAIN, GIFT), synchronized(reads) { reads.toList() })
        assertSame(first, windows().single())
        check(exists(LINK_SHEET))
    }
}

/** A synthetic DPD parcel's link, on its way, and a synthetic gift's. */
private const val PLAIN = "k7Qm2xHd9RtW"
private const val GIFT = "g8Rn3yJe2SuX"

private fun ago(minutes: Long): String = DateParser.isoString(Instant.now().minus(Duration.ofMinutes(minutes)))

/** What the link `id` reads: a synthetic DPD parcel on its way, or a synthetic DHL gift. */
private fun response(id: String, gift: Boolean): PublicParcelResponse {
    val parcel = UUID.nameUUIDFromBytes(id.toByteArray())
    val scan = TrackingEvent(
        id = UUID.nameUUIDFromBytes("$id-scan".toByteArray()), packageId = parcel, stage = TrackingStage.InTransit,
        description = "In transit", location = "Zürich, CH", occurredAt = ago(95),
    )
    return PublicParcelResponse(
        link = ParcelLink(
            id = id, role = ParcelLinkRole.Viewer, kind = if (gift) ParcelLinkKind.Shared else ParcelLinkKind.Lookup, createdAt = ago(600),
            numberShown = false, canKeep = false, gift = gift, shared = gift,
        ),
        parcel = PublicPackage(
            id = parcel, numberHint = ParcelNumberHint(head = "", tail = if (gift) "6789" else "3456"), label = "",
            carrier = if (gift) CarrierId.Dhl else CarrierId.Dpd, createdAt = ago(600), lastSyncedAt = ago(2), syncStatus = SyncStatus.Ok,
            carrierData = PublicPackageCarrierData(senderName = if (gift) null else "Example Shop", destinationCountry = "CH"),
            notificationsMuted = false, trackingEvents = listOf(scan),
        ),
    )
}
