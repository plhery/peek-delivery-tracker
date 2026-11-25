package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ClockPreference
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.PeekAi
import com.plhery.peekdeliverytracker.core.displayedCarrier
import com.plhery.peekdeliverytracker.core.parcelPeekAi
import com.plhery.peekdeliverytracker.core.peekAiClaim
import com.plhery.peekdeliverytracker.core.peekAiReason
import com.plhery.peekdeliverytracker.core.peekAiStrip
import com.plhery.peekdeliverytracker.core.peekAiStripSpoken
import com.plhery.peekdeliverytracker.core.peekAiUntil
import com.plhery.peekdeliverytracker.core.peekAiWhy
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.ProvideLocalizer
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.time.Duration
import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Peek AI on a parcel's page, in the demo at the captures' moment: Birthday gift's card says what Peek AI expects, and
 * its (i) opens the sheet, counted, with the days, which TalkBack hears in words, likeliest first, without their key, and
 * what Peek AI goes by, where the card says why of the parcel; switching Peek AI off there keeps the
 * sheet up, counted, and the card is gone once it closes. Belgian chocolate's sheet has the hours, written again on the
 * reader's clock, each whole in its cell. Left open, a list lets the chocolate's "by 16:00" go at 16:00, or as the app
 * comes back after it, and a page lets its card and sheet go once their day has passed.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PeekAiPageTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private val copy = Localizer(AppLanguage.En, variant = "android")

    /** The app's clock, one function for the screens' life as `AppClock::now` is. */
    private var clock: Instant = CAPTURED

    private fun show() {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.setContent { RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false, now = { clock }) }
        compose.waitForIdle()
    }

    private fun open(name: String) {
        compose.openCard(DemoCards.parcel(name).label)
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun ai(name: String): PeekAi = requireNotNull(parcelPeekAi(DemoCards.parcel(name), CAPTURED))

    /** When the named parcel's estimate stops holding. */
    private fun ends(name: String): Instant = requireNotNull(peekAiUntil(ai(name)))

    /** Whether a card reads `words`, as TalkBack hears the card. */
    private fun says(words: String): Boolean =
        compose.onAllNodes(hasContentDescription(words, substring = true)).fetchSemanticsNodes().isNotEmpty()

    /** What TalkBack hears of the node tagged `tag`, or of the one in it that speaks for it. */
    private fun heard(tag: String): String? = compose.onAllNodes(hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag)))
        .fetchSemanticsNodes().firstNotNullOfOrNull { it.config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString() }

    /** Whether TalkBack reads `words` as a text on the screen, as it merges the nodes. */
    private fun shows(words: String): Boolean = compose.onAllNodes(hasText(words)).fetchSemanticsNodes().isNotEmpty()

    /** The carrier's name the page gives the named parcel. */
    private fun carrier(name: String): String = DemoCards.identities.displayName(DemoCards.parcel(name).displayedCarrier, AppLanguage.En)

    @Test
    fun theGiftsCardOpensPeekAisSheetAndItsSwitchTakesTheCardAway() {
        show()
        open("Birthday gift")
        // TalkBack hears it as Peek AI's: "Peek AI: likely by …".
        assertEquals(copy.text("peekAi.says", mapOf("words" to copy.peekAiClaim(ai("Birthday gift"), CAPTURED))), heard(PEEK_AI_CARD))
        assertTrue(shows(copy.peekAiWhy(ai("Birthday gift"), carrier("Birthday gift"), CAPTURED)))
        assertFalse(exists(PEEK_AI_SHEET))

        compose.onNodeWithTag(PEEK_AI_INFO).performClick()
        settle()
        assertEquals("peek-ai-open", source.actions.last())
        assertTrue(exists(PEEK_AI_SHEET))
        // The days' shades in words: the shaded ones, likeliest first, then the carrier's. The key under them stays silent.
        val strip = requireNotNull(copy.peekAiStrip(ai("Birthday gift"), CAPTURED))
        assertEquals(copy.peekAiStripSpoken(strip, carrier("Birthday gift")), heard(PEEK_AI_STRIP))
        assertTrue(heard(PEEK_AI_STRIP).orEmpty().startsWith(copy.text("peekAi.sheet.daysByChance") + ": "))
        assertFalse(shows(copy.text("peekAi.sheet.lessLikely")))
        assertFalse(shows(copy.text("peekAi.sheet.moreLikely")))
        // The sheet's Why is what Peek AI goes by, where the card says why of the parcel.
        assertTrue(shows(copy.peekAiReason(ai("Birthday gift"), carrier("Birthday gift"), CAPTURED)))
        val toggled = { compose.onNodeWithTag(PEEK_AI_SHOW).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ToggleableState) }
        assertEquals(ToggleableState.On, toggled())

        // The row flips as TalkBack flips it; a touch flips its switch.
        compose.onNodeWithTag(PEEK_AI_SHOW).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        assertFalse(source.showPeekAi.value)
        assertEquals("peek-ai-off", source.actions.last())
        assertEquals(ToggleableState.Off, toggled())
        assertTrue(exists(PEEK_AI_SHEET))

        compose.onNodeWithTag(PEEK_AI_CLOSE).performClick()
        settle()
        assertFalse(exists(PEEK_AI_SHEET))
        assertFalse(exists(PEEK_AI_CARD))
    }

    @Test
    fun theChocolatesSheetHasTheHours() {
        show()
        open("Belgian chocolate")
        assertTrue(exists(PEEK_AI_CARD))
        compose.onNodeWithTag(PEEK_AI_INFO).performClick()
        settle()
        assertTrue(heard(PEEK_AI_STRIP).orEmpty().startsWith(copy.text("peekAi.sheet.hoursByChance") + ": "))
    }

    /**
     * The chocolate's hours on a 12-hour clock: written again as the reader's clock changes, and each whole on one line
     * in its cell on a phone 360 wide, a little smaller where it takes that.
     */
    @Test
    fun theHoursAreWrittenOnTheReadersClockEachWholeInItsCell() {
        val reader = Localizer(AppLanguage.En, variant = "android")
        val cells = mutableMapOf<String, TextLayoutResult>()
        compose.setContent {
            PeekTheme(carrierIdentities = DemoCards.identities) {
                ProvideLocalizer(reader) {
                    CompositionLocalProvider(LocalPeekAiCellWords provides { cells[it.layoutInput.text.text] = it }) {
                        Box(Modifier.width(360.dp)) {
                            PeekAiSheet(
                                DemoCards.parcel("Belgian chocolate"), ai("Belgian chocolate"), carrier("Belgian chocolate"), shown = true, now = CAPTURED,
                                onShow = {}, close = {},
                            )
                        }
                    }
                }
            }
        }
        compose.waitForIdle()
        val twelve = Regex("""\d:\d\d\p{Z}?[AP]M""")
        assertFalse(twelve.containsMatchIn(heard(PEEK_AI_STRIP).orEmpty()))
        compose.runOnUiThread { reader.clockPreference = ClockPreference.TwelveHour }
        compose.waitForIdle()
        assertTrue(heard(PEEK_AI_STRIP).orEmpty(), twelve.containsMatchIn(heard(PEEK_AI_STRIP).orEmpty()))
        val hours = cells.filterKeys { twelve.containsMatchIn(it) }.values
        assertTrue(hours.isNotEmpty())
        for (hour in hours) {
            val text = hour.layoutInput.text.text
            assertEquals(text, 1, hour.lineCount)
            // Past 70 %, it would end in an ellipsis.
            assertFalse(text, hour.isLineEllipsized(0) || hour.didOverflowWidth)
        }
    }

    /**
     * At Android's largest text, the hours are written as at xxLarge, where the iPhone stops them growing: five hours
     * side by side have no room for more.
     */
    @Test
    fun atTheLargestTextTheHoursGrowNoFurtherThanXxLarge() {
        val reader = Localizer(AppLanguage.En, variant = "android").apply { clockPreference = ClockPreference.TwelveHour }
        val size = mutableStateOf(DynamicTypeSize.XXLarge)
        val cells = mutableMapOf<String, TextLayoutResult>()
        compose.setContent {
            PeekTheme(dynamicTypeSize = size.value, carrierIdentities = DemoCards.identities) {
                ProvideLocalizer(reader) {
                    CompositionLocalProvider(LocalPeekAiCellWords provides { cells[it.layoutInput.text.text] = it }) {
                        PeekAiSheet(
                            DemoCards.parcel("Belgian chocolate"), ai("Belgian chocolate"), carrier("Belgian chocolate"), shown = true, now = CAPTURED,
                            onShow = {}, close = {},
                        )
                    }
                }
            }
        }
        compose.waitForIdle()
        val atXxLarge = cells.mapValues { it.value.layoutInput.style.fontSize }
        assertTrue(atXxLarge.keys.any { Regex("""\d:\d\d\p{Z}?[AP]M""").containsMatchIn(it) })
        compose.runOnUiThread { size.value = DynamicTypeSize.Accessibility2 }
        compose.waitForIdle()
        assertEquals(atXxLarge, cells.mapValues { it.value.layoutInput.style.fontSize })
    }

    @Test
    fun aListLeftOpenLetsTheChocolatesHourGoAtThatHour() {
        show()
        assertTrue(says("Peek AI: by 16:00"))
        // The card's own wait for 16:00 runs on the test's clock, which the day moves on too.
        clock = ends("Belgian chocolate")
        compose.mainClock.advanceTimeBy(Duration.between(CAPTURED, clock).toMillis() + 1_000)
        compose.waitForIdle()
        assertFalse(says("Peek AI: by 16:00"))
    }

    @Test
    fun aListLooksAgainAsTheAppComesBack() {
        show()
        assertTrue(says("Peek AI: by 16:00"))
        // The phone slept past 16:00, which a wait doesn't count.
        clock = ends("Belgian chocolate").plusSeconds(600)
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        compose.waitForIdle()
        assertFalse(says("Peek AI: by 16:00"))
    }

    @Test
    fun aPageLeftOpenLetsTheCardAndItsSheetGoOnceTheirDayHasPassed() {
        show()
        open("Birthday gift")
        compose.onNodeWithTag(PEEK_AI_INFO).performClick()
        settle()
        assertTrue(exists(PEEK_AI_SHEET))
        // The page looks again each minute.
        clock = ends("Birthday gift")
        compose.mainClock.advanceTimeBy(61_000)
        settle()
        assertFalse(exists(PEEK_AI_SHEET))
        assertFalse(exists(PEEK_AI_CARD))
    }
}
