package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.SharedResources
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CarrierPostcodeRefused
import com.plhery.peekdeliverytracker.core.api.ImportCharges
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.waitingAt
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.time.Duration
import java.util.UUID
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * What the parcel's page says in and under its pass, on the iPhone the captures were taken on, in the demo at the
 * captures' moment: the pickup card, at rest, dark, at twice the text size and just copied; what the carrier says under
 * the pass's flag, light and dark; the postcode notes; the made-up scans; the facts; the hints where no carrier is
 * asked; and why the last check failed, with its buttons, light and dark.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class PassNotesScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState

    /** `content` on the page's paper, 18 in from its sides, as the page lays its parts. */
    private fun shot(name: String, dark: Boolean = false, fontScale: Float = 1f, capture: () -> Unit = {}, content: @Composable () -> Unit) {
        compose.waitsForMaps()
        compose.setContent {
            CardHost(dark = dark, fontScale = fontScale) {
                Box(Modifier.background(PeekTheme.colors.paper).padding(18.dp)) { content() }
            }
        }
        compose.waitForIdle()
        capture()
        compose.screenshot(name)
    }

    /** `parcel`'s pass on the page's paper, with a bell that does nothing. */
    private fun pass(name: String, dark: Boolean = false, parcel: () -> Parcel) = shot(name, dark) {
        LivePassCard(
            parcel(), rememberWorldAtlas(), sample = false, readerLink = false,
            bell = PassBell(opensAlerts = false) { _, _ -> }, working = false, onMap = {}, onEditCarrier = { _, _ -> }, onRename = {},
        )
    }

    /** The demo's `parcels` as the page shots show them, with the iPhone's status bar 62 tall and gesture bar 24. */
    private fun show(parcels: List<Parcel>, dark: Boolean = false) {
        val source = FakeDeliveries(parcels, AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = AuthenticationState.Demo, dark = dark, reduceMotion = false, source = source)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, (62 * scale).toInt(), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, (24 * scale).toInt()))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    /** The page of the parcel named `name` in `parcels`, landed and scrolled to the part tagged `tag`. */
    private fun page(shot: String, parcels: List<Parcel>, name: String, tag: String, dark: Boolean = false) {
        show(parcels, dark)
        compose.runOnUiThread { deliveries.navigator.open(DemoCards.parcel(name).id) }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        compose.onNodeWithTag(tag).performScrollTo()
        compose.mainClock.advanceTimeBy(1_000)
        compose.screenshot(shot)
    }

    /** The demo, the parcel whose name starts with `name` changed by `change`. */
    private fun demo(name: String, change: (Parcel) -> Parcel): List<Parcel> {
        val id = DemoCards.parcel(name).id
        return DemoCards.parcels().map { if (it.id == id) change(it) else it }
    }

    // The pickup card.

    private val trainers get() = DemoCards.parcel("New trainers")

    @Composable
    private fun pickupCard() {
        PickupPointCard(trainers.waitingAt!!, DemoCards.identities.of(trainers.carrier, AppLanguage.En), onDirections = {}, onCopy = {})
    }

    @Test
    fun thePickupCard() = shot("notes_pickup_light") { pickupCard() }

    @Test
    fun thePickupCardInDark() = shot("notes_pickup_dark", dark = true) { pickupCard() }

    /** At twice the text size, an accessibility size: no storefront, and the buttons one under the other. */
    @Test
    fun thePickupCardAtLargeText() = shot("notes_pickup_light_text2", fontScale = 2f) { pickupCard() }

    /** Just copied: the button says so in the room of its own words. */
    @Test
    fun thePickupCardCopied() = shot(
        "notes_pickup_copied_light",
        capture = {
            compose.mainClock.autoAdvance = false
            compose.onNodeWithTag(PAGE_PICKUP_COPY).performClick()
            compose.mainClock.advanceTimeBy(100)
        },
    ) { pickupCard() }

    // What the carrier says.

    /** Birthday gift, which Chronopost couldn't deliver an hour before the captures, in its words. */
    private fun failedAttempt(): List<Parcel> = demo("Birthday gift") {
        it.copy(
            trackingEvents = it.trackingEvents + TrackingEvent(
                id = UUID.nameUUIDFromBytes("attempt".toByteArray()), packageId = it.id, stage = TrackingStage.FailedAttempt,
                description = "Nobody was home. A notice is in your letterbox.",
                occurredAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))),
            ),
        )
    }

    @Test
    fun whatTheCarrierSays() = page("page_carrier_says_light", failedAttempt(), "Birthday gift", PAGE_CARRIER_SAYS)

    @Test
    fun whatTheCarrierSaysInDark() = page("page_carrier_says_dark", failedAttempt(), "Birthday gift", PAGE_CARRIER_SAYS, dark = true)

    // The postcode notes.

    private fun refusedPostcode(): Parcel = DemoCards.parcel("Trail weekend kit").let {
        it.copy(
            dpdPostcode = "0000",
            carrierData = (it.carrierData ?: CarrierData()).copy(routing = CarrierDataRouting(postcodeRefused = CarrierPostcodeRefused(CarrierId.Dpd))),
        )
    }

    @Test
    fun aRefusedPostcode() = pass("pass_postcode_refused_light") { refusedPostcode() }

    @Test
    fun aRefusedPostcodeInDark() = pass("pass_postcode_refused_dark", dark = true) { refusedPostcode() }

    /** Without its postcode, DPD shows more with one. */
    @Test
    fun thePostcodeShowsMore() = pass("pass_postcode_more_light") { DemoCards.parcel("Trail weekend kit").copy(dpdPostcode = null) }

    // The made-up scans.

    @Test
    fun madeUpScans() = pass("pass_generated_light") {
        val lamp = DemoCards.parcel("Moon lamp")
        val start = CAPTURED.minus(Duration.ofDays(4))
        lamp.copy(
            trackingEvents = (0 until 4).map { step ->
                TrackingEvent(
                    id = UUID.nameUUIDFromBytes("move-$step".toByteArray()), packageId = lamp.id, stage = TrackingStage.InTransit,
                    description = "In transit", location = "Place $step", occurredAt = DateParser.isoString(start.plusSeconds(step * 86_111L)),
                )
            },
        )
    }

    // The facts.

    @Test
    fun theFacts() = page(
        "page_facts_light",
        demo("Moon lamp") {
            it.copy(
                carrierData = (it.carrierData ?: CarrierData()).copy(
                    importCharges = ImportCharges(currency = "EUR", customs = 23.41), pickupPoint = "Example Kiosk\nSample Street 1",
                    receiverName = "A. Reader", weightKg = 1.25, dimensionsText = "30 × 20 × 10 cm",
                ),
            )
        },
        "Moon lamp", PAGE_FACTS,
    )

    // Where no carrier is asked.

    @Test
    fun anAmazonLogisticsParcel() =
        page("page_amazon_hint_light", demo("Moon lamp") { it.copy(carrier = CarrierId.AmazonLogistics) }, "Moon lamp", PAGE_CARRIER_HINT)

    /** UPS made a carrier Peek doesn't ask, as only Amazon Logistics is: the hint and its change, in UPS's ink. */
    @Test
    fun aCarrierPeekDoesNotAsk() {
        val bundled = SharedResources.text("CarrierCatalog.json")
        val ups = Regex("\"ups\": \\{[^}]*?\"tracking\": \\{\\s*\"mode\": \"automatic\"").find(bundled)!!
        val catalog = CarrierCatalog(bundled.replaceRange(ups.range, ups.value.replace("\"automatic\"", "\"link-only\"")).encodeToByteArray())
        val identities = runBlocking { CarrierIdentities.load(catalog) }
        val vinyl = DemoCards.parcel("Kind of Blue")
        shot("notes_link_only_light") {
            CompositionLocalProvider(LocalCarrierIdentities provides identities) {
                Column {
                    TrackingNotes(
                        vinyl, editable = true, working = false, ink = PeekTheme.colors.resolve(identities.of(vinyl.carrier, AppLanguage.En).ink),
                        onEditCarrier = {}, onSwitch = { _, _ -> },
                    )
                }
            }
        }
    }

    // Why the last check failed.

    private fun refusedNumber(): List<Parcel> = demo("Birthday gift") {
        it.copy(trackingNumber = "1Z999AA10123456784", syncStatus = SyncStatus.Error, syncError = "carrier:refused_number")
    }

    @Test
    fun aRefusedNumber() = page("page_refused_number_light", refusedNumber(), "Birthday gift", PAGE_SYNC_ERROR)

    @Test
    fun aRefusedNumberInDark() = page("page_refused_number_dark", refusedNumber(), "Birthday gift", PAGE_SYNC_ERROR, dark = true)

    /** DPD asked for more: its details are updated in the editor. */
    @Test
    fun detailsTheCarrierAskedFor() {
        val kit = DemoCards.parcel("Trail weekend kit").copy(syncStatus = SyncStatus.Error, syncError = "carrier:input_required")
        // 12 apart, as the parts of the page.
        shot("notes_input_required_light") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TrackingNotes(
                    kit, editable = true, working = false, ink = PeekTheme.colors.resolve(DemoCards.identities.of(kit.carrier, AppLanguage.En).ink),
                    onEditCarrier = {}, onSwitch = { _, _ -> },
                )
            }
        }
    }
}
