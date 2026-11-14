package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.JournalFixtures.parcel
import com.plhery.peekdeliverytracker.ui.deliveries.JournalFixtures.scan
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.time.Instant
import java.util.TimeZone
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The history's days on a page left open, as the iPhone's journal works them out each time it draws. Coming back to
 * the app reloads the parcels; the page is given the parcel again, newly read (same scans, a later sync), and its days
 * follow the day it has become and the zone the phone is in.
 *
 * The page is first drawn at Thu 29 Oct 2026 06:11 in Zurich, its scans at 04:30 Thursday and 21:15 Wednesday.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class JournalDaysAfterMidnightTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val first = parcel(
        listOf(
            scan(1, "2026-10-28T20:15:00Z", "Sorted at the parcel centre"),
            scan(2, "2026-10-29T03:30:00Z", "Left the parcel centre", TrackingStage.InTransit),
        ),
    )

    /** The same parcel read again from the server: the same scans, newly read, and a later sync. */
    private fun reread(at: String): Parcel = first.copy(lastSyncedAt = at, trackingEvents = first.trackingEvents.map { it.copy() })

    /** The app's clock, one function for the page's life as `AppClock::now` is. */
    private var clock: Instant = CAPTURED
    private val now: () -> Instant = { clock }

    private var shown by mutableStateOf(first)

    private fun texts(node: SemanticsNode): List<String> = node.config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text }

    private fun days(): List<String> = compose.onAllNodes(hasTestTag(JOURNAL_DAY)).fetchSemanticsNodes().flatMap(::texts)

    private fun rows(): List<List<String>> = compose.onAllNodes(hasTestTag(JOURNAL_ROW)).fetchSemanticsNodes().map(::texts)

    private fun zoneLine(): List<String> = texts(compose.onNodeWithTag(JOURNAL_TIME_ZONE).fetchSemanticsNode())

    private fun show() {
        compose.setContent {
            CardHost {
                CompositionLocalProvider(LocalNow provides now) {
                    Box(Modifier.background(PeekTheme.colors.paper).padding(18.dp)) {
                        ParcelJournal(shown, Color(0xFFD40000), Modifier.testTag(PAGE_JOURNAL))
                    }
                }
            }
        }
        compose.waitForIdle()
    }

    /** Control: drawn at first, Thursday's scan is today's and Wednesday's yesterday's. */
    @Test
    fun controlFirstDrawn() {
        show()
        assertEquals(listOf("today", "yesterday"), days())
        assertEquals(listOf(listOf("04:30", "Left the parcel centre"), listOf("21:15", "Sorted at the parcel centre")), rows())
    }

    /** Control: a page opened on Friday morning names Thursday yesterday and Wednesday by its date. */
    @Test
    fun controlAPageOpenedTheNextMorning() {
        clock = CAPTURED.plusSeconds(86_400)
        show()
        assertEquals(listOf("yesterday", "Wed 28 Oct"), days())
    }

    /**
     * The page is left open overnight; on Friday morning the app comes back and reloads the parcel, and the page draws
     * it again: Thursday's scan is still "today".
     */
    @Test
    fun aPageLeftOpenOvernightNamesItsDaysAfreshAfterTheReload() {
        show()
        clock = CAPTURED.plusSeconds(86_400)
        shown = reread("2026-10-30T05:00:00Z")
        compose.waitForIdle()
        assertEquals(
            "A page left open since Thursday, given Friday's reload, still heads Thursday's scan \"today\"",
            listOf("yesterday", "Wed 28 Oct"),
            days(),
        )
    }

    /**
     * The phone moves to New York's zone with the page open; the app comes back and reloads the parcel. Without the
     * history being closed and opened, the line under it names Eastern Time while the rows keep Zurich's times and days.
     */
    @Test
    fun afterTheZoneChangesAReloadNamesTheNewZoneOverItsOwnDays() {
        show()
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"))
        shown = reread("2026-10-29T05:20:00Z")
        compose.waitForIdle()
        val seen = listOf(zoneLine(), rows(), days())
        assertEquals(
            "After the zone change and a reload, the history's zone line, times and days should all be New York's",
            listOf(
                listOf("Times are in your time zone: Eastern Time."),
                listOf(listOf("23:30", "Left the parcel centre"), listOf("16:15", "Sorted at the parcel centre")),
                listOf("yesterday"),
            ),
            seen,
        )
    }
}
