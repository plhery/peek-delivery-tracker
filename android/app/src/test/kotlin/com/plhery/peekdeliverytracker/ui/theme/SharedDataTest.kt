package com.plhery.peekdeliverytracker.ui.theme

import android.content.Context
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierCatalogRefreshResult
import com.plhery.peekdeliverytracker.core.SharedResources
import com.plhery.peekdeliverytracker.core.api.CarrierId
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** The data the iPhone app bundles, packaged as the app's assets and read through :core. */
@RunWith(AndroidJUnit4::class)
class SharedDataTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @get:Rule
    val compose = createComposeRule()

    @Test
    fun theAppCarriesEverySharedFileAsAnAsset() {
        val assets = context.assets.list("peek")!!.toSet()
        // A debug build's: a release build leaves out ContractFixtures.json, which only the debug previews read.
        val expected = setOf(
            "Analytics.json", "Brand.json", "CarrierCatalog.json", "ContractFixtures.json", "CountryNames.json",
            "DeliveryDemo.json", "FriendsDemo.json", "Localization.json", "PostcodeExamples.json", "Reading.json",
            "TrackingMessages.json", "World.json", "WorldDetail.pack",
        )
        assertEquals(expected, assets)
    }

    @Test
    fun coreReadsTheAssetsOnceTheApplicationStarts() {
        val asset = context.assets.open("peek/Brand.json").use { it.readBytes().decodeToString() }
        assertEquals(asset, SharedResources.text("Brand.json"))
        val read = mutableListOf<String>()
        SharedResources.install { name ->
            read += name
            context.assets.open("peek/$name")
        }
        SharedResources.text("Reading.json")
        assertEquals(listOf("Reading.json"), read)
    }

    @Test
    fun identitiesLoadOffTheMainThreadAndNameTheirCarrier() {
        val identities = runBlocking { CarrierIdentities.load(CarrierCatalog.shared) }
        val dhl = identities.of(CarrierId.Dhl, AppLanguage.En)
        assertEquals("dhl", dhl.family)
        assertEquals("DHL", dhl.name)
        assertEquals("dhl", dhl.decal)
        assertTrue(identities.of(CarrierId("c3x"), AppLanguage.En).decal == "c3x")
    }

    @Test
    fun theLiveriesFollowTheAppsCatalogAsItRefreshes() {
        val bundled = SharedResources.text("CarrierCatalog.json")
        val renamed = bundled.replace("\"displayName\": \"DHL\"", "\"displayName\": \"DHL Renamed\"")
        val catalog = CarrierCatalog(bundled.encodeToByteArray(), loader = { CarrierCatalog.Response(200, body = renamed.encodeToByteArray()) })
        var shown: String? = null
        compose.setContent {
            CompositionLocalProvider(LocalCarrierIdentities provides rememberCarrierIdentities(catalog)) {
                shown = carrierIdentity(CarrierId.Dhl, AppLanguage.En)?.fullName
            }
        }
        compose.waitUntil(10_000) { shown != null }
        assertEquals("DHL", shown)

        assertEquals(CarrierCatalogRefreshResult.Updated, runBlocking { catalog.refresh("https://delivery.example", force = true) })
        compose.waitUntil(10_000) { shown == "DHL Renamed" }
    }
}
