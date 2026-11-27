package com.plhery.peekdeliverytracker.ui.passport

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.captureRoboImage
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.PassportBook
import com.plhery.peekdeliverytracker.core.YearInParcels
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.screenshotOptions
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.UUID
import kotlin.math.roundToInt
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
 * "Your 2026 in parcels" (the web's YearCard.test.tsx): offered in December once a parcel was delivered that year, its
 * words only counts, countries and the fastest time, its country stamps laid out in rows, and its picture.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class YearCardTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** Order [index], scanned at midnight and delivered at noon of [at]'s day, both in [location]. */
    private fun located(index: Int, at: String, location: String): Parcel {
        val arrival = Instant.parse(at)
        return PassportFixtures.parcel(index, location, arrival.minus(Duration.ofHours(12)), arrival, to = location, label = "Order $index")
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun choose(label: String) {
        compose.onNode(hasContentDescription(label) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        settle()
    }

    private fun offered(): Boolean = compose.onAllNodes(hasTestTag(PASSPORT_YEAR_CARD), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** The day the passport opens decides: in December, with a parcel delivered this year; never for last year's alone. */
    @Test
    fun theCardIsOfferedInDecemberOnceAParcelWasDeliveredThatYear() {
        var now by mutableStateOf(Instant.parse("2026-11-30T11:00:00Z"))
        val source = FakeDeliveries(listOf(located(1, "2026-03-02T12:00:00Z", "Zürich, CH")), reader)
        compose.waitsForMaps()
        compose.setContent { RootHost(state = reader, source = source, now = { now }) }
        settle()
        choose("Passport")
        assertFalse(offered())

        now = Instant.parse("2026-12-01T08:00:00Z")
        choose("Deliveries")
        choose("Passport")
        assertTrue(offered())
        compose.onNodeWithTag("$PASSPORT_PAGE_LABEL.${PassportPage.NUMBERS}").performClick()
        settle()
        val row = compose.onNodeWithTag(PASSPORT_YEAR_CARD).performScrollTo().fetchSemanticsNode().config
        assertEquals(listOf("Your 2026 in parcels"), row.getOrNull(SemanticsProperties.Text)?.map { it.text })
        assertEquals(Role.Button, row[SemanticsProperties.Role])

        // Its sheet: the picture, read as what it shows.
        compose.onNodeWithTag(PASSPORT_YEAR_CARD).performClick()
        settle()
        val picture = compose.onNodeWithTag(PASSPORT_YEAR_CARD_PICTURE, useUnmergedTree = true).fetchSemanticsNode().config
        assertEquals(listOf("2026 in parcels. 1 delivered, 1 country: Switzerland. Fastest 12h."), picture[SemanticsProperties.ContentDescription])

        // Only last year's parcels: nothing to offer.
        compose.runOnUiThread { source.parcels.value = listOf(located(1, "2025-03-02T12:00:00Z", "Zürich, CH")) }
        settle()
        assertFalse(offered())
    }

    @Test
    fun theCardSaysOnlyCountsCountriesAndTheFastestTime() {
        val year = YearInParcels(PassportBook(listOf(located(1, "2026-03-02T12:00:00Z", "Zürich, CH")), ZoneOffset.UTC), 2026)
        val words = cardWords(year, localizer, AppLanguage.En, ZoneOffset.UTC)
        assertEquals("in parcels", words.inParcels)
        assertEquals("1 delivered · 1 country", words.lineOne)
        assertEquals("Fastest 12h · peektracker.com", words.lineTwo)
        assertEquals("Switzerland", words.onlyCountry)
        assertEquals("2026 in parcels. 1 delivered, 1 country: Switzerland. Fastest 12h.", words.description)
        val said = listOfNotNull(words.inParcels, words.lineOne, words.lineTwo, words.onlyCountry, words.description)
        assertFalse(said.toString(), said.any { "Order" in it })
        assertEquals("12 AUG 2026", inkDay(Instant.parse("2026-08-12T12:00:00Z"), AppLanguage.En, ZoneOffset.UTC))
    }

    @Test
    fun theCardLaysOutUpToSixCountryStampsRowsCentred() {
        val five = countryLayout(5, 430f, 900f)
        assertEquals(
            listOf(listOf(120, 461), listOf(416, 461), listOf(712, 461), listOf(268, 685), listOf(564, 685)),
            five.map { listOf(it.x.roundToInt(), it.y.roundToInt()) },
        )
        val one = countryLayout(1, 430f, 900f).single()
        assertEquals(listOf(330f, 420f, 312f), listOf(one.x, one.w, one.h))
        assertTrue(countryLayout(7, 430f, 900f).isEmpty())
    }

    /**
     * The picture at 1080 × 1350, on made-up parcels: 24 delivered this year through five countries, the seals of ten
     * stamps earned this year, six of them and "+4".
     */
    @Test
    fun theCardsPicture() {
        val zurich = ZoneId.of("Europe/Zurich")
        val year = YearInParcels(PassportBook(PassportFixtures.everyStamp(), zurich), 2026)
        assertEquals(24, year.deliveredCount)
        assertEquals(5, year.countryCount)
        val picture = renderYearCard(year, cardWords(year, localizer, AppLanguage.En, zurich))
        assertEquals(listOf(CARD_WIDTH, CARD_HEIGHT), listOf(picture.width, picture.height))
        picture.captureRoboImage("src/test/snapshots/passport_year_card.png", roborazziOptions = screenshotOptions)
    }
}
