package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The parcel page's journal on the page's paper, on the iPhone the captures were taken on, at the captures' moment
 * ([JournalFixtures.journey]): three days, the current scan's time in Swiss Post's ink, a place with its flag, a copy's
 * words under its scan and the carrier's repeat folded into its own; light and dark, collapsed, and at the first
 * accessibility size, the times over the words. And the demo's vinyl, the line its carrier's predicted steps are folded
 * behind over its scans: closed in light, opened in dark.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelJournalScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    /** `parcel`'s journal on the page's paper, 18 in from its sides, with the text `fontScale` times the normal. */
    private fun shot(
        name: String,
        dark: Boolean = false,
        fontScale: Float = 1f,
        collapsed: Boolean = false,
        predictedOpen: Boolean = false,
        parcel: Parcel = JournalFixtures.journey(),
    ) {
        compose.setContent {
            CardHost(dark = dark, fontScale = fontScale) {
                Box(Modifier.background(PeekTheme.colors.paper).padding(18.dp)) {
                    val ink = PeekTheme.colors.resolve(DemoCards.identities.of(parcel.carrier, AppLanguage.En).ink)
                    ParcelJournal(parcel, ink)
                }
            }
        }
        if (collapsed) compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        if (predictedOpen) compose.onNodeWithTag(JOURNAL_PREDICTED).performClick()
        compose.screenshot(name)
    }

    @Test
    fun theJournal() = shot("journal_light")

    @Test
    fun theJournalInDark() = shot("journal_dark", dark = true)

    /** Its days hidden: the header alone, its chevron pointing down. */
    @Test
    fun collapsed() = shot("journal_collapsed_light", collapsed = true)

    /** At the first accessibility size: each time over its scan's words. */
    @Test
    fun atAnAccessibilitySize() = shot("journal_accessibility_light", fontScale = 1.5f)

    /** The steps the carrier planned, folded behind one line above the scans that says they're only predictions. */
    @Test
    fun withThePredictedLine() = shot("journal_planned_light", parcel = DemoCards.parcel("Kind of Blue"))

    /**
     * The line opened: the steps greyed on dashed marks joined by a dashed rail, newest first, each with the day it was
     * predicted for, then a dashed line and the scans.
     */
    @Test
    fun withThePredictedStepsShownInDark() =
        shot("journal_planned_dark", dark = true, predictedOpen = true, parcel = DemoCards.parcel("Kind of Blue"))

    /** At Android's largest font scale, in dark: the marks and their rail grow with the words, beside them. */
    @Test
    fun withThePredictedStepsShownAtTheLargestFontScaleInDark() = shot(
        "journal_planned_accessibility_dark", dark = true, fontScale = 2f, predictedOpen = true, parcel = DemoCards.parcel("Kind of Blue"),
    )
}
