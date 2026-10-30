package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.time.Duration
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The parcel page's sync line on the page's paper, on the iPhone the captures were taken on, at the captures' moment:
 * with its refresh, light and dark, and in French at an accessibility size; archived, with neither the next check nor
 * the refresh; and checking, the arrow a quarter turned, light and dark, or half faded under Reduce Motion.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class SyncLineScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    /** Birthday gift, its next check at 15:05 in Zurich the day after the captures. */
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift").let {
        it.copy(carrierData = (it.carrierData ?: CarrierData()).copy(routing = CarrierDataRouting(nextCheckAt = "2026-10-30T14:05:00Z")))
    }

    /**
     * `parcel`'s sync line on the page's paper, 18 in from its sides, in `language` with the text `fontScale` times the
     * normal, with its refresh unless `refreshes` is false, checking while `working`, captured `after` milliseconds
     * into it.
     */
    private fun shot(
        name: String,
        parcel: Parcel = gift,
        dark: Boolean = false,
        refreshes: Boolean = true,
        working: Boolean = false,
        reduceMotion: Boolean = false,
        language: AppLanguage = AppLanguage.En,
        fontScale: Float = 1f,
        after: Long = 0,
    ) {
        compose.mainClock.autoAdvance = false
        compose.setContent {
            CardHost(language = language, dark = dark, fontScale = fontScale, reduceMotion = reduceMotion) {
                Box(Modifier.background(PeekTheme.colors.paper).padding(18.dp)) {
                    val ink = PeekTheme.colors.resolve(DemoCards.identities.of(parcel.carrier, AppLanguage.En).ink)
                    SyncLine(parcel, ink, working = working, onRefresh = if (refreshes) ({}) else null)
                }
            }
        }
        // The first frame starts the arrow's effect.
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeBy(after)
        compose.screenshot(name)
    }

    @Test
    fun theSyncLine() = shot("sync_light")

    @Test
    fun theSyncLineInDark() = shot("sync_dark", dark = true)

    /** Archived: no next check, and nothing to check again. */
    @Test
    fun anArchivedParcelsSyncLine() =
        shot("sync_archived_light", gift.copy(archivedAt = DateParser.isoString(CAPTURED.minus(Duration.ofDays(2)))), refreshes = false)

    /** Checking, a quarter of a turn on. */
    @Test
    fun checking() = shot("sync_checking_light", working = true, after = 256)

    @Test
    fun checkingInDark() = shot("sync_checking_dark", dark = true, working = true, after = 256)

    /** In French at the first accessibility size: the lines wrap beside the refresh, which keeps its 26. */
    @Test
    fun inFrenchAtAnAccessibilitySize() = shot("sync_french_accessibility_light", language = AppLanguage.Fr, fontScale = 1.5f)

    /** Checking under Reduce Motion, half faded. */
    @Test
    fun checkingUnderReduceMotion() = shot("sync_checking_reduce_motion_light", working = true, reduceMotion = true, after = 368)
}
