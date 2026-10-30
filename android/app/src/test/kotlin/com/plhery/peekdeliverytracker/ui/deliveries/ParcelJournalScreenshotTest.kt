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
 * accessibility size, the times over the words.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelJournalScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    /** The journey's journal on the page's paper, 18 in from its sides, with the text `fontScale` times the normal. */
    private fun shot(name: String, dark: Boolean = false, fontScale: Float = 1f, collapsed: Boolean = false) {
        val parcel = JournalFixtures.journey()
        compose.setContent {
            CardHost(dark = dark, fontScale = fontScale) {
                Box(Modifier.background(PeekTheme.colors.paper).padding(18.dp)) {
                    val ink = PeekTheme.colors.resolve(DemoCards.identities.of(parcel.carrier, AppLanguage.En).ink)
                    ParcelJournal(parcel, ink)
                }
            }
        }
        if (collapsed) compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
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
}
