package com.plhery.peekdeliverytracker.ui.theme

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.SharedResources
import com.plhery.peekdeliverytracker.core.api.CarrierId
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/** The data the iPhone app bundles, packaged as the app's assets and read through :core. */
@RunWith(AndroidJUnit4::class)
class SharedDataTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun theAppCarriesEverySharedFileAsAnAsset() {
        val assets = context.assets.list("peek")!!.toSet()
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
        val identities = runBlocking { CarrierIdentities.load() }
        val dhl = identities.of(CarrierId.Dhl, AppLanguage.En)
        assertEquals("dhl", dhl.family)
        assertEquals("DHL", dhl.name)
        assertEquals("dhl", dhl.decal)
        assertTrue(identities.of(CarrierId("c3x"), AppLanguage.En).decal == "c3x")
    }
}
