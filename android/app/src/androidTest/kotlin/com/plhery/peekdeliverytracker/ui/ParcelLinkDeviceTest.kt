package com.plhery.peekdeliverytracker.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelLinkClaim
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkStore
import com.plhery.peekdeliverytracker.core.UserDefaults
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
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
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_GIFT_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelLinkSource
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.time.Duration
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * A parcel's link on this device (A-L02, B-L01), which the JVM tests stand in for with Robolectric's window: the app's
 * window takes both forms of a link aimed at its package, the site's and the app's scheme; a window started by a link
 * shows its sheet over the tabs, and another link that comes to it while it runs opens in the sheet in place of the
 * first. The links read from memory, never the network.
 */
@RunWith(AndroidJUnit4::class)
class ParcelLinkDeviceTest {
    @get:Rule
    val compose = createEmptyComposeRule()

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext
    private var scenario: ActivityScenario<ComponentActivity>? = null

    @After
    fun close() {
        scenario?.close()
    }

    /** The site's address for link `id`, on the build's first link host, else the app's scheme. */
    private fun siteLink(id: String): String {
        val host = BuildSettings.configuration.linkHosts.firstOrNull() ?: return "peekdeliverytracker://p/$id"
        return "https://$host/p/$id"
    }

    private fun link(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url)).addCategory(Intent.CATEGORY_BROWSABLE)

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** The activities of this package a link to `url` aimed at it opens. */
    private fun takers(url: String): List<String> =
        context.packageManager.queryIntentActivities(link(url).setPackage(context.packageName), PackageManager.ResolveInfoFlags.of(0))
            .map { it.activityInfo.name }

    @Test
    fun theAppsWindowTakesALinkOnTheSiteAndInTheAppsScheme() {
        val hosts = BuildSettings.configuration.linkHosts
        assertEquals(listOf(MainActivity::class.java.name), takers("peekdeliverytracker://p/$PLAIN"))
        for (host in hosts) assertEquals(listOf(MainActivity::class.java.name), takers("https://$host/p/$PLAIN"))
        assertEquals(emptyList<String>(), takers("https://elsewhere.example/p/$PLAIN"))
    }

    @Test
    fun aLinkStartsTheWindowWithItsSheetAndAnotherReplacesItWhileItRuns() {
        val links = MemoryLinks()
        val start = link(siteLink(PLAIN)).setClassName(context, ComponentActivity::class.java.name)
        scenario = ActivityScenario.launch<ComponentActivity>(start).onActivity { activity ->
            // The window's inbox as the app's makes it: a parcel's link first, read against the build's site and hosts.
            val inbox = DeliveriesInbox.of(activity) { url ->
                val configuration = BuildSettings.configuration
                val route = ParcelLinkRoute(url, configuration.apiBaseUrl, configuration.linkHosts)
                route?.let(links.links::open)
                route != null
            }
            activity.setContent {
                PeekTheme(dark = false, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = null) {
                    PeekRoot(AuthenticationState.Welcome, Localizer(AppLanguage.En, variant = "android"), onView = {}, inbox = inbox, links = links) {
                        Box(Modifier.fillMaxSize())
                    }
                }
            }
        }
        compose.waitUntil(10_000) { exists(LINK_CARD) }
        assertEquals(PLAIN, links.links.route?.id)

        scenario?.onActivity { instrumentation.callActivityOnNewIntent(it, link("peekdeliverytracker://p/$GIFT")) }
        compose.waitUntil(10_000) { exists(LINK_GIFT_CARD) && !exists(LINK_CARD) }
        assertEquals(GIFT, links.links.route?.id)
        assertEquals(listOf(PLAIN, GIFT), links.reads)
        check(exists(LINK_SHEET))
    }
}

/** A synthetic DPD parcel's link, on its way, and a synthetic gift's. */
private const val PLAIN = "k7Qm2xHd9RtW"
private const val GIFT = "g8Rn3yJe2SuX"

/**
 * The link sheet's stores in memory: a real ParcelLinkStore whose reads answer a synthetic DPD parcel on its way, or a
 * gift for [GIFT]; nothing is kept nor followed.
 */
private class MemoryLinks : ParcelLinkSource {
    val reads = mutableListOf<String>()

    override val links = ParcelLinkStore(MemoryDefaults(), read = { id, _ ->
        reads += id
        response(id, gift = id == GIFT)
    })
    override val session = MutableStateFlow<AuthenticationState>(AuthenticationState.Welcome)
    override val parcels = MutableStateFlow<List<Parcel>>(emptyList())
    override val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    override val authenticationConfigured = true
    override val tracksWithoutAccount = true

    override suspend fun keep(link: ParcelLinkRoute): ParcelLinkClaim = ParcelLinkClaim.Unavailable

    override fun follow(link: ParcelLinkRoute, response: PublicParcelResponse): ParcelLinkClaim = ParcelLinkClaim.Unavailable

    override fun followedHere(response: PublicParcelResponse): UUID? = null

    override fun showDeviceParcels() = Unit

    override fun showSignIn() = Unit

    override fun errorMessage(error: Throwable): String = error.message.orEmpty()

    override fun action(name: String) = Unit

    private fun ago(minutes: Long): String = DateParser.isoString(Instant.now().minus(Duration.ofMinutes(minutes)))

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
}

/** A UserDefaults suite in memory, so that the test writes nothing on the device. */
private class MemoryDefaults : UserDefaults {
    private val values = mutableMapOf<String, Any>()

    override fun bool(forKey: String): Boolean = values[forKey] as? Boolean ?: false

    override fun set(value: Boolean, forKey: String) {
        values[forKey] = value
    }

    override fun data(forKey: String): ByteArray? = values[forKey] as? ByteArray

    override fun set(value: ByteArray, forKey: String) {
        values[forKey] = value
    }

    override fun string(forKey: String): String? = values[forKey] as? String

    override fun set(value: String, forKey: String) {
        values[forKey] = value
    }

    override fun removeObject(forKey: String) {
        values.remove(forKey)
    }

    override fun hasObject(forKey: String): Boolean = forKey in values
}
