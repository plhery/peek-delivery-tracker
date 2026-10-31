package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.LocalNow
import java.time.Duration
import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * `AutomaticCarrierNotice`'s clock (B-AN21): read again each minute, as `TimelineView(.periodic(from: .now, by: 60))`
 * reads it, so the notice goes in the minute its 12 hours are up, on the test's clock as on the phone's.
 */
@RunWith(AndroidJUnit4::class)
class AutomaticCarrierNoticeTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun theNoticeGoesTheMinuteItsTwelveHoursAreUp() {
        val changed = Instant.parse("2026-10-28T17:00:00Z")
        val gift = DemoCards.parcel("Birthday gift")
        val parcel = gift.copy(
            carrierData = (gift.carrierData ?: CarrierData()).copy(
                autoChangedFrom = CarrierId.SwissPost, autoChangedTo = gift.carrier, autoChangedAt = DateParser.isoString(changed),
            ),
        )
        val words = Localizer(AppLanguage.En, variant = "android")
            .text("parcel.autoChangedCarrier", mapOf("carrier" to DemoCards.identities.displayName(CarrierId.SwissPost, AppLanguage.En)))
        // The wall clock, which the notice reads only as its minute comes round.
        var now = changed.plus(Duration.ofHours(12)).minusSeconds(30)
        compose.mainClock.autoAdvance = false
        compose.setContent {
            CardHost {
                CompositionLocalProvider(LocalNow provides { now }) { AutomaticCarrierNotice(parcel) }
            }
        }
        compose.mainClock.advanceTimeByFrame()
        assertEquals(1, shown(words))
        now = now.plusSeconds(60)
        compose.mainClock.advanceTimeBy(59_000)
        assertEquals(1, shown(words))
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(0, shown(words))
    }

    private fun shown(words: String): Int = compose.onAllNodes(hasText(words)).fetchSemanticsNodes().size
}
