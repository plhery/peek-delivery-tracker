package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.JournalFixtures.harkingen
import com.plhery.peekdeliverytracker.ui.deliveries.JournalFixtures.id
import com.plhery.peekdeliverytracker.ui.deliveries.JournalFixtures.journey
import com.plhery.peekdeliverytracker.ui.deliveries.JournalFixtures.parcel
import com.plhery.peekdeliverytracker.ui.deliveries.JournalFixtures.scan
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The parcel page's journal (`ParcelJournal`, B-S14 to B-S16, B-ST16, B-AN10, B-G15, B-SE11, B-S78): its header's
 * count and toggle and what TalkBack hears of it, its empty states, its days' headings and order, its rows' times and
 * words, the syncing words, the copies' words, the places without their flags in what TalkBack reads, the same
 * carrier's repeat folded into its scan, the state kept through a window made again, and the days going and coming at
 * once under Reduce Motion or fading over 0.2 s otherwise. Seen from Zurich at the captures' moment.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelJournalTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val tint = Color(0xFFD40000)

    /** The parcel the journal shows, which a test may change once it shows. */
    private var shown by mutableStateOf(journey())

    /** The secondary grey the journal draws in. */
    private var secondary = Color.Unspecified

    private var started = false

    /** The journal of [shown] on the page's paper, tinted with [tint]. */
    private fun content(reduceMotion: Boolean, fontScale: Float = 1f): @Composable () -> Unit = {
        CardHost(reduceMotion = reduceMotion, fontScale = fontScale) {
            secondary = PeekTheme.colors.secondaryLabel
            Box(Modifier.background(PeekTheme.colors.paper).padding(18.dp)) {
                ParcelJournal(shown, tint, Modifier.testTag(PAGE_JOURNAL))
            }
        }
    }

    /** `parcel`'s journal; once it shows, the same journal shows `parcel` instead. */
    private fun show(parcel: Parcel = journey(), reduceMotion: Boolean = true, fontScale: Float = 1f) {
        shown = parcel
        if (!started) compose.setContent(content(reduceMotion, fontScale))
        started = true
        compose.waitForIdle()
    }

    private fun texts(node: SemanticsNode): List<String> = node.config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text }

    /** The days' headings, in their order. */
    private fun days(): List<String> = compose.onAllNodes(hasTestTag(JOURNAL_DAY)).fetchSemanticsNodes().flatMap(::texts)

    /** What TalkBack reads of each row, in their order. */
    private fun rows(): List<List<String>> = compose.onAllNodes(hasTestTag(JOURNAL_ROW)).fetchSemanticsNodes().map(::texts)

    private fun header(): SemanticsNode = compose.onNodeWithTag(JOURNAL_TOGGLE).fetchSemanticsNode()

    private fun state(): String? = header().config.getOrNull(SemanticsProperties.StateDescription)

    private fun textExists(text: String): Boolean =
        compose.onAllNodes(hasText(text), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** The colour `text` is drawn in. */
    private fun color(text: String): Color {
        val node = compose.onNode(hasText(text), useUnmergedTree = true).fetchSemanticsNode()
        val layouts = mutableListOf<TextLayoutResult>()
        node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(layouts)
        return layouts.single().layoutInput.style.color
    }

    // The header.

    @Test
    fun theHeaderCountsTheRowsOneUpdateOrHowMany() {
        show(parcel(listOf(scan(1, "2026-10-28T07:02:00Z", "Parcel accepted at the counter"))))
        assertEquals(listOf("Tracking history", "1 update"), texts(header()))
        show()
        // Six scans, two of them copies under their scans.
        assertEquals(listOf("Tracking history", "4 updates"), texts(header()))
        assertEquals(Role.Button, header().config[SemanticsProperties.Role])
    }

    @Test
    fun theHeaderHidesAndShowsTheDaysAndSaysWhatATapDoes() {
        show()
        assertEquals(4, rows().size)
        assertEquals("Show less", state())
        compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        compose.waitForIdle()
        assertEquals(emptyList<String>(), days())
        assertEquals(0, rows().size)
        assertEquals("Show full journey", state())
        // The header stays, with its count.
        assertEquals(listOf("Tracking history", "4 updates"), texts(header()))
        compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        compose.waitForIdle()
        assertEquals(4, rows().size)
        assertEquals("Show less", state())
    }

    @Test
    fun withoutARowTheJournalSaysTheCarrierIsBeingAskedWhileTheParcelSyncs() {
        show(parcel(emptyList(), SyncStatus.Syncing))
        assertTrue(textExists("Checking with the carrier now…"))
        assertFalse(textExists("No tracking updates yet."))
        assertEquals(listOf("Tracking history", "0 updates"), texts(header()))
    }

    @Test
    fun withoutARowTheJournalSaysThereIsNothingYetOtherwise() {
        show(parcel(emptyList()))
        assertTrue(textExists("No tracking updates yet."))
        assertFalse(textExists("Checking with the carrier now…"))
        compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        compose.waitForIdle()
        assertFalse(textExists("No tracking updates yet."))
    }

    // The days.

    @Test
    fun theDaysAreTodayYesterdayTheirShortDateOrThatWithTheYearNewestFirst() {
        show(
            parcel(
                listOf(
                    scan(1, "2025-12-31T10:00:00Z", "The sender created a shipping label", TrackingStage.Registered),
                    scan(2, "2026-10-20T09:00:00Z", "Parcel accepted at the counter", TrackingStage.Accepted),
                    scan(3, "2026-10-28T22:55:00Z", "Sorted at the parcel centre"),
                    scan(4, "2026-10-28T23:05:00Z", "Left the parcel centre"),
                    scan(5, "2026-10-29T04:40:00Z", "With the courier for delivery today", TrackingStage.OutForDelivery),
                ),
            ),
        )
        // 23:55 is yesterday in Zurich, 00:05 today.
        assertEquals(listOf("TODAY", "YESTERDAY", "TUE 20 OCT", "WED 31 DEC 2025"), days())
        assertEquals(
            listOf(
                listOf("05:40", "With the courier for delivery today"), listOf("00:05", "Left the parcel centre"),
                listOf("23:55", "Sorted at the parcel centre"), listOf("11:00", "Parcel accepted at the counter"),
                listOf("11:00", "The sender created a shipping label"),
            ),
            rows(),
        )
        val headings = compose.onAllNodes(hasTestTag(JOURNAL_DAY)).fetchSemanticsNodes()
        assertTrue(headings.all { SemanticsProperties.Heading in it.config })
    }

    @Test
    fun aScanDatedByItsDayAloneHasNoTimeAndSitsUnderThatDay() {
        show(
            parcel(
                listOf(
                    scan(1, "2026-10-26T20:00:00Z", "Sorted at the parcel centre"),
                    // Its carrier east of Zurich starts the 27th in the afternoon before here.
                    scan(2, "2026-10-26T15:00:00Z", "Delivery attempt scheduled", day = "2026-10-27"),
                ),
            ),
        )
        assertEquals(listOf("TUE 27 OCT", "MON 26 OCT"), days())
        assertEquals(listOf(listOf("Delivery attempt scheduled"), listOf("21:00", "Sorted at the parcel centre")), rows())
    }

    @Test
    fun aDateThatDoesNotReadShowsADashAndItsDayComesLastUnderWhatTheCarrierWrote() {
        show(
            parcel(
                listOf(
                    scan(1, "soon", "Parcel expected"),
                    scan(2, "2026-10-20T09:00:00Z", "Parcel accepted at the counter", TrackingStage.Accepted),
                ),
            ),
        )
        assertEquals(listOf("TUE 20 OCT", "SOON"), days())
        assertEquals(listOf("—", "Parcel expected"), rows().last())
    }

    // The rows.

    @Test
    fun theSyncingWordsAreOnlyForTheCurrentPendingScanWhileTheParcelSyncs() {
        val scans = listOf(
            scan(1, "2026-10-28T07:00:00Z", "Order details sent to the carrier", TrackingStage.Pending),
            scan(2, "2026-10-29T04:00:00Z", "Waiting for the carrier", TrackingStage.Pending),
        )
        show(parcel(scans, SyncStatus.Syncing))
        assertEquals(listOf(listOf("05:00", "Checking for updates…"), listOf("08:00", "Order details sent to the carrier")), rows())
        show(parcel(scans))
        assertEquals(listOf(listOf("05:00", "Waiting for the carrier"), listOf("08:00", "Order details sent to the carrier")), rows())
        // A scan past pending is no longer synced for.
        show(parcel(scans + scan(3, "2026-10-29T05:00:00Z", "Parcel accepted at the counter", TrackingStage.Accepted), SyncStatus.Syncing))
        assertFalse(textExists("Checking for updates…"))
    }

    @Test
    fun aScanWithoutWordsSaysItsStage() {
        show(parcel(listOf(scan(1, "2026-10-29T04:00:00Z", "", TrackingStage.OutForDelivery))))
        assertEquals(listOf(listOf("05:00", "Out for delivery")), rows())
    }

    @Test
    fun aCopysWordsShowOnceAndNotWhenTheyAreTheScans() {
        show(
            parcel(
                listOf(
                    scan(1, "2026-10-28T07:02:00Z", "Parcel accepted at the counter", TrackingStage.Accepted),
                    scan(2, "2026-10-28T07:03:00Z", "Shipment details received", TrackingStage.Accepted, relayOf = id(1)),
                    scan(3, "2026-10-28T07:04:00Z", "Parcel accepted at the counter", TrackingStage.Accepted, relayOf = id(1)),
                    scan(4, "2026-10-28T07:05:00Z", "Shipment details received", TrackingStage.Accepted, relayOf = id(1)),
                ),
            ),
        )
        assertEquals(listOf(listOf("08:02", "Parcel accepted at the counter", "Shipment details received")), rows())
        assertEquals(listOf("Tracking history", "1 update"), texts(header()))
    }

    /** B-S78: the carrier's own repeat of a scan is one step with it, current through it. */
    @Test
    fun theSameCarriersRepeatOfAScanIsOneStepWithItAndCurrentThroughIt() {
        show(
            parcel(
                listOf(
                    scan(1, "2026-10-28T07:02:00Z", "Parcel accepted at the counter", TrackingStage.Accepted),
                    scan(2, "2026-10-28T20:15:00Z", "Sorted at the parcel centre", location = "Härkingen, CH", place = harkingen),
                    scan(3, "2026-10-28T20:40:00Z", "Sorted at the parcel centre", location = "Härkingen, CH", place = harkingen, relayOf = id(2)),
                ),
            ),
        )
        assertEquals(
            listOf(listOf("21:15", "Sorted at the parcel centre", "Härkingen, Switzerland"), listOf("08:02", "Parcel accepted at the counter")),
            rows(),
        )
        assertEquals(listOf("Tracking history", "2 updates"), texts(header()))
        // The repeat is the current scan, so the row it folds into is current.
        assertEquals(tint, color("21:15"))
        assertEquals(secondary, color("08:02"))
    }

    @Test
    fun theCurrentRowsTimeIsInTheTint() {
        show()
        assertEquals(tint, color("05:40"))
        for (time in listOf("21:15", "08:02", "14:30")) assertEquals(time, secondary, color(time))
    }

    @Test
    fun aPlaceWithACountryIsReadWithoutItsFlagAndOneWithoutAsWritten() {
        val haiger = EventPlace(latitude = 50.74, longitude = 8.2, precision = EventPlacePrecision.City, country = "DE", name = "Haiger")
        show(
            parcel(
                listOf(
                    scan(1, "2026-10-27T07:00:00Z", "Parcel accepted at the counter", location = "  Haiger, DE ", place = haiger),
                    scan(2, "2026-10-27T08:00:00Z", "Handed to the next carrier", location = "Wilmington, DE"),
                    scan(3, "2026-10-27T09:00:00Z", "Arrived at the sorting centre", location = "Switzerland"),
                    scan(4, "2026-10-28T20:15:00Z", "Sorted at the parcel centre", location = "Härkingen, CH", place = harkingen),
                    scan(5, "2026-10-29T04:40:00Z", "With the courier for delivery today", location = " "),
                ),
            ),
        )
        assertEquals(
            listOf(
                listOf("05:40", "With the courier for delivery today"),
                listOf("21:15", "Sorted at the parcel centre", "Härkingen, Switzerland"),
                listOf("10:00", "Arrived at the sorting centre", "Switzerland"),
                // A final DE names Germany only where the scan was placed there.
                listOf("09:00", "Handed to the next carrier", "Wilmington, DE"),
                listOf("08:00", "Parcel accepted at the counter", "Haiger, Germany"),
            ),
            rows(),
        )
        assertFalse(rows().flatten().any { "🇨🇭" in it || "🇩🇪" in it })
    }

    @Test
    fun atAnAccessibilitySizeTheTimeGoesOverTheWords() {
        show(fontScale = 1.5f)
        val time = compose.onNode(hasText("05:40"), useUnmergedTree = true).getBoundsInRoot()
        val words = compose.onNode(hasText("With the courier for delivery today"), useUnmergedTree = true).getBoundsInRoot()
        assertTrue("$time over $words", time.bottom <= words.top)
        assertEquals(time.left.value, words.left.value, 0.5f)
    }

    @Test
    fun theTimesColumnIs38WideAtTheLargeSizeAndTheWords12On() {
        show()
        val words = compose.onNode(hasText("With the courier for delivery today"), useUnmergedTree = true).getBoundsInRoot()
        val row = compose.onAllNodes(hasTestTag(JOURNAL_ROW)).fetchSemanticsNodes().first().let { node ->
            with(compose.density) { node.boundsInRoot.left.toDp() }
        }
        assertEquals(38f + 12f, (words.left - row).value, 0.5f)
    }

    // The state and the motion.

    @Test
    fun theJournalStaysHiddenThroughAWindowMadeAgain() {
        val recreation = Recreation(compose)
        recreation.setContent(content(reduceMotion = true))
        compose.waitForIdle()
        compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        compose.waitForIdle()
        recreation.recreate()
        assertEquals("Show full journey", state())
        assertEquals(0, rows().size)
        compose.mainClock.autoAdvance = true
    }

    @Test
    fun underReduceMotionTheDaysGoAndComeAtOnce() {
        show(reduceMotion = true)
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        assertEquals(0, rows().size)
        compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        assertEquals(4, rows().size)
        compose.mainClock.autoAdvance = true
    }

    /** How much `pixels` stand out from the first one, the paper: each pixel's distance from it, summed. */
    private fun ink(pixels: IntArray): Long {
        val paper = pixels[0]
        fun channel(pixel: Int, shift: Int) = (pixel shr shift) and 0xFF
        return pixels.sumOf { pixel -> listOf(16, 8, 0).sumOf { abs(channel(pixel, it) - channel(paper, it)) }.toLong() }
    }

    /** The frame that takes the tap, then the one the animation starts on: 96 ms on is half way through its 0.2 s. */
    private fun startAnimation() {
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
    }

    private fun journalHeight(): Float = compose.onNodeWithTag(PAGE_JOURNAL).getBoundsInRoot().let { (it.bottom - it.top).value }

    @Test
    fun theDaysFadeWhereTheyStandAsTheJournalShrinksOver200Milliseconds() {
        show(reduceMotion = false)
        compose.mainClock.autoAdvance = false
        val full = journalHeight()
        val firstDay = compose.onAllNodes(hasTestTag(JOURNAL_DAY))[0]
        val dayTop = firstDay.getBoundsInRoot().top
        val rest = ink(compose.drawn(firstDay))
        compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        startAnimation()
        compose.mainClock.advanceTimeBy(96)
        val half = journalHeight()
        assertEquals(dayTop, firstDay.getBoundsInRoot().top)
        val faded = ink(compose.drawn(firstDay)).toDouble() / rest
        compose.mainClock.advanceTimeBy(200)
        val collapsed = journalHeight()
        assertEquals(0, rows().size)
        assertTrue("Collapsed $collapsed of $full", collapsed < full / 3)
        val progress = (full - half) / (full - collapsed)
        assertTrue("Shrunk by $progress at 100 ms", progress in 0.35f..0.65f)
        assertTrue("Faded to $faded at 100 ms", faded in 0.25..0.75)
        // And back, the same way.
        compose.onNodeWithTag(JOURNAL_TOGGLE).performClick()
        startAnimation()
        compose.mainClock.advanceTimeBy(96)
        val growing = (journalHeight() - collapsed) / (full - collapsed)
        assertTrue("Grown by $growing at 100 ms", growing in 0.35f..0.65f)
        compose.mainClock.advanceTimeBy(200)
        assertEquals(full, journalHeight(), 0.5f)
        compose.mainClock.autoAdvance = true
    }

    // On the page.

    private lateinit var deliveries: DeliveriesState

    /** The page of `parcel`, among the demo's parcels. */
    private fun openPage(parcel: Parcel) {
        val source = FakeDeliveries((DemoCards.parcels() + parcel).distinctBy { it.id }, AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = true)
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread { deliveries.navigator.open(parcel.id) }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    @Test
    fun thePageShowsTheJournalWhereACarrierHasAnswered() {
        openPage(DemoCards.parcel("Birthday gift"))
        assertTrue(exists(PAGE_JOURNAL))
        assertTrue(exists(JOURNAL_ROW))
    }

    @Test
    fun thePageHasNoJournalWhereNoCarrierIsAskedNorHasAnswered() {
        val manual = CarrierCatalog.shared.definitions.keys.first { it != CarrierId.Unknown && !CarrierCatalog.shared.tracksAutomatically(it) }
        openPage(parcel(emptyList()).copy(carrier = manual))
        assertTrue(exists(PAGE_SYNC_LINE))
        assertFalse(exists(PAGE_JOURNAL))
    }
}
