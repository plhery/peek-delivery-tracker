package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.JournalFixtures.parcel
import com.plhery.peekdeliverytracker.ui.deliveries.JournalFixtures.scan
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.util.TimeZone
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The history's days follow the phone's zone as its rows' times and the line naming the zone do. After the phone's zone
 * changes with the page open (Android resets the default zone on ACTION_TIMEZONE_CHANGED, with no configuration change,
 * so the page stays), closing and opening the history shows the new zone's days over its times and name.
 *
 * Seen at the captures' moment, Thu 29 Oct 2026 06:11 in Zurich (01:11 in New York).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class JournalZoneChangeTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    /** 03:30 UTC: 04:30 Thursday in Zurich, 23:30 Wednesday in New York. 20:15 UTC Wednesday: 21:15 / 16:15. */
    private val shown = parcel(
        listOf(
            scan(1, "2026-10-28T20:15:00Z", "Sorted at the parcel centre"),
            scan(2, "2026-10-29T03:30:00Z", "Left the parcel centre", TrackingStage.InTransit),
        ),
    )

    private fun texts(node: SemanticsNode): List<String> = node.config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text }

    private fun days(): List<String> = compose.onAllNodes(hasTestTag(JOURNAL_DAY)).fetchSemanticsNodes().flatMap(::texts)

    private fun rows(): List<List<String>> = compose.onAllNodes(hasTestTag(JOURNAL_ROW)).fetchSemanticsNodes().map(::texts)

    private fun zoneLine(): List<String> = texts(compose.onNodeWithTag(JOURNAL_TIME_ZONE).fetchSemanticsNode())

    private fun show() {
        compose.setContent {
            CardHost {
                Box(Modifier.background(PeekTheme.colors.paper).padding(18.dp)) {
                    ParcelJournal(shown, Color(0xFFD40000), Modifier.testTag(PAGE_JOURNAL))
                }
            }
        }
        compose.waitForIdle()
    }

    /** The phone moves to New York's zone, then the reader closes and opens the history. */
    private fun travelToNewYorkAndReopen() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"))
        compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        compose.waitForIdle()
    }

    /** Control: drawn in Zurich, the days, the times and the zone agree. */
    @Test
    fun controlInZurichTheDaysTimesAndZoneAgree() {
        show()
        assertEquals(listOf("today", "yesterday"), days())
        assertEquals(listOf(listOf("04:30", "Left the parcel centre"), listOf("21:15", "Sorted at the parcel centre")), rows())
        assertEquals(listOf("Times are in your time zone: Central European Time."), zoneLine())
    }

    /** Control: a page first drawn in New York puts both scans under "yesterday". */
    @Test
    fun controlAPageFirstDrawnInNewYorkHasOneDay() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"))
        show()
        assertEquals(listOf("yesterday"), days())
        assertEquals(listOf(listOf("23:30", "Left the parcel centre"), listOf("16:15", "Sorted at the parcel centre")), rows())
        assertEquals(listOf("Times are in your time zone: Eastern Time."), zoneLine())
    }

    @Test
    fun afterTheZoneChangesTheDaysFollowTheNewZonesTimes() {
        show()
        travelToNewYorkAndReopen()
        // The line and the times have moved to New York.
        assertEquals(listOf("Times are in your time zone: Eastern Time."), zoneLine())
        assertEquals(listOf(listOf("23:30", "Left the parcel centre"), listOf("16:15", "Sorted at the parcel centre")), rows())
        // In New York both scans are on Wednesday 28 Oct, yesterday: one day, "yesterday".
        assertEquals(
            "After the phone's zone changed to New York with the page open, the history names Eastern Time and shows " +
                "23:30 and 16:15 (both Wednesday, yesterday there), but its days are still Zurich's: 23:30 sits under " +
                "\"today\"",
            listOf("yesterday"),
            days(),
        )
    }
}
