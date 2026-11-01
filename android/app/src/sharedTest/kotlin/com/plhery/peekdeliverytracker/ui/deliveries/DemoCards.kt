package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
import androidx.compose.ui.test.IdlingResource
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.test.core.app.ApplicationProvider
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.DemoRepository
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.LikelyCarrier
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.notice
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.map.MapScenes
import com.plhery.peekdeliverytracker.ui.map.WorldAtlases
import com.plhery.peekdeliverytracker.ui.map.WorldDetails
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import java.time.Duration
import java.time.Instant
import java.util.TimeZone
import java.util.UUID
import kotlinx.coroutines.runBlocking
import org.junit.rules.ExternalResource

/** The moment the iPhone's captures show: Thursday 29 October 2026, 06:11 in Zurich. */
val CAPTURED: Instant = Instant.parse("2026-10-29T05:11:00Z")

/** The demo's parcels as the captures show them, seen from Zurich at [CAPTURED]. */
object DemoCards {
    val identities: CarrierIdentities by lazy { runBlocking { CarrierIdentities.load(CarrierCatalog.shared) } }

    /** The demo's parcels in `language`, newest first. */
    fun parcels(language: AppLanguage = AppLanguage.En): List<Parcel> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val preferences = context.getSharedPreferences("cards-demo-${language.rawValue}", Context.MODE_PRIVATE)
        return DemoRepository(PreferencesDefaults(preferences), { language }, { CAPTURED }).list()
    }

    /** The demo parcel whose English name starts with `name`, in `language`. */
    fun parcel(name: String, language: AppLanguage = AppLanguage.En): Parcel {
        val number = parcels().first { it.label.startsWith(name) }.trackingNumber
        return parcels(language).first { it.trackingNumber == number }
    }

    /** The archived demo parcels, the most recently archived first, as the shelf lists them. */
    fun archived(language: AppLanguage = AppLanguage.En): List<Parcel> =
        parcels(language).filter { it.archivedAt != null }.sortedByDescending { it.archivedAt }

    /** What the list's attention section says `parcel` needs. */
    fun notice(parcel: Parcel, language: AppLanguage = AppLanguage.En): String? =
        parcel.notice(CAPTURED)?.let { Localizer(language, variant = "android").text(it.localizationKey) }

    /** Birthday gift handed over by its carrier to Swiss Post, which delivers it: its pass carries both marks. */
    fun handover(): Parcel {
        val gift = parcel("Birthday gift")
        return gift.copy(carrierData = (gift.carrierData ?: CarrierData()).copy(activeTrackingCarrier = CarrierId.SwissPost, originalCarrier = gift.carrier))
    }

    /** A parcel nobody has answered for yet, whose number points to GLS, which does not list it yet (A-S103). */
    fun probable(language: AppLanguage = AppLanguage.En): Parcel {
        val created = DateParser.isoString(CAPTURED.minus(Duration.ofHours(20)))
        return Parcel(
            id = UUID.fromString("00000000-0000-4000-8000-000000000103"), trackingNumber = "DEMOLIKELY0001",
            label = if (language == AppLanguage.De) "Schreibtischlampe 💡" else "Desk lamp 💡", carrier = CarrierId.Unknown,
            createdAt = created, syncStatus = SyncStatus.Ok, notificationsMuted = false,
            carrierData = CarrierData(routing = CarrierDataRouting(likelyCarrier = LikelyCarrier(CarrierId.GlsDe, created))),
        )
    }
}

/** Each test of the cards in Zurich's time, as the captures were taken, and the zone it found put back after. */
class ZurichTime : ExternalResource() {
    private var before: TimeZone? = null

    override fun before() {
        before = TimeZone.getDefault()
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Zurich"))
    }

    override fun after() {
        TimeZone.setDefault(before)
    }
}

/**
 * The maps' pictures are made on other threads, and an opened map's detail tiles read on them: the test waits until
 * each is drawn, and each tile a map asked for is in.
 */
fun ComposeContentTestRule.waitsForMaps() {
    runBlocking { WorldAtlases.load().await() }
    registerIdlingResource(object : IdlingResource {
        override val isIdleNow: Boolean get() = MapScenes.isIdle && WorldAtlases.isIdle && WorldDetails.isIdle
    })
}
