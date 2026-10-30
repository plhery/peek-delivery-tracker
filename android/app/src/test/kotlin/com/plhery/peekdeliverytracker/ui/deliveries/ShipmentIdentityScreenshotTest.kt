package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.shownCarrierIdentity
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The parcel page's numbers and its carriers' pages on the page's paper, on the iPhone the captures were taken on:
 * one number with its page, light and dark; two, each with its own page; the pages of no number's own, saying what
 * they are to the parcel, light and dark; two numbers at twice the text size, their copies under them; and a number
 * just copied.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ShipmentIdentityScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val gift get() = DemoCards.parcel("Birthday gift")

    /** Birthday gift handed by Chronopost to Swiss Post under a made-up number of Swiss Post's. */
    private fun handedOver(): Parcel = gift.copy(
        carrierData = (gift.carrierData ?: CarrierData()).copy(
            activeTrackingCarrier = CarrierId.SwissPost, activeTrackingNumber = "990012345612345678", originalCarrier = gift.carrier,
            originalTrackingNumber = gift.trackingNumber,
        ),
    )

    /** Birthday gift handed over to Swiss Post under its own number: Chronopost's page is its earlier journey. */
    private fun earlierJourney(): Parcel = DemoCards.handover().let {
        it.copy(carrierData = it.carrierData!!.copy(originalTrackingNumber = it.trackingNumber))
    }

    /** An AliExpress number Swiss Post takes over in Switzerland, made up with its check digit: Swiss Post's page waits. */
    private fun waitingForSwissPost(): Parcel = gift.copy(carrier = CarrierId.Aliexpress, trackingNumber = "LX123456785CH", carrierData = null, trackingUrl = null)

    /** `parcels`' numbers one under the other, 20 apart, on the page's paper, 18 in from its sides, as the page lays them. */
    private fun shot(name: String, vararg parcels: Parcel, dark: Boolean = false, fontScale: Float = 1f, copied: String? = null) {
        compose.waitsForMaps()
        compose.setContent {
            CardHost(dark = dark, fontScale = fontScale) {
                Box(Modifier.background(PeekTheme.colors.paper).padding(18.dp)) {
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) { parcels.forEach { Numbers(it, copied) } }
                }
            }
        }
        compose.waitForIdle()
        compose.screenshot(name)
    }

    @Composable
    private fun Numbers(parcel: Parcel, copied: String?) {
        val identity = shownCarrierIdentity(parcel, LocalLocalizer.current, AppLanguage.En)!!
        ShipmentNumbers(
            parcel, CarrierCatalog.shared.trackingLinks(parcel, AppLanguage.En), PeekTheme.colors.resolve(identity.ink),
            copied = copied, onCopy = {}, onLink = {},
        )
    }

    @Test
    fun oneNumber() = shot("numbers_one_light", gift)

    @Test
    fun oneNumberInDark() = shot("numbers_one_dark", gift, dark = true)

    @Test
    fun twoNumbers() = shot("numbers_two_light", handedOver())

    @Test
    fun pagesOfNoNumbersOwn() = shot("numbers_roles_light", earlierJourney(), waitingForSwissPost())

    @Test
    fun pagesOfNoNumbersOwnInDark() = shot("numbers_roles_dark", earlierJourney(), waitingForSwissPost(), dark = true)

    /** At twice the text size, an accessibility size: each copy under its number, saying what it does. */
    @Test
    fun twoNumbersAtLargeText() = shot("numbers_two_light_text2", handedOver(), fontScale = 2f)

    /** Just copied: the copy is a check for 2 s. */
    @Test
    fun aNumberJustCopied() = shot("numbers_copied_light", gift, copied = gift.trackingNumber)
}
