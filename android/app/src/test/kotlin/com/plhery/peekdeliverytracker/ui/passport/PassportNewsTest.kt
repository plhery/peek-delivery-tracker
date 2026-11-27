package com.plhery.peekdeliverytracker.ui.passport

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import java.time.Duration
import java.time.Instant
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The passport's news (the web's news.test.tsx): what a passport holds is seen on its first read on the device, and a
 * list kept from before countries counted takes the countries held as seen; each stamp earned and country reached after
 * it waits on a line under the delivered card whose parcel brought it, and the Passport tab carries a dot heard as "New",
 * until the passport opens, on the page with news, where the new stamps and countries are pressed in and read as new.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PassportNewsTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** A parcel first scanned in Switzerland four hours before [at], when [carrier] delivered it somewhere unnamed. */
    private fun delivered(index: Int, carrier: CarrierId, at: String): Parcel {
        val arrival = Instant.parse(at)
        return PassportFixtures.parcel(index, "CH", arrival.minus(Duration.ofHours(4)), arrival, carrier = carrier)
    }

    /** One still on its way, first scanned at [at]. */
    private fun onItsWay(index: Int, at: String): Parcel = PassportFixtures.parcel(index, "CH", Instant.parse(at), null, carrier = CarrierId.Dpd)

    /** The reader's deliveries on [parcels], loading while [loading], with the seen list [kept] where one is. */
    private fun show(parcels: List<Parcel>, loading: Boolean = false, kept: String? = null) {
        source = FakeDeliveries(parcels, reader)
        source.loading.value = loading
        if (kept != null) source.passportDefaults.set(kept, forKey = SEEN)
        compose.waitsForMaps()
        compose.setContent { RootHost(state = reader, source = source) }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun update(parcels: List<Parcel>) {
        compose.runOnUiThread { source.parcels.value = parcels }
        settle()
    }

    private fun tab(label: String) = compose.onNode(hasContentDescription(label) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab))

    private fun choose(label: String) {
        tab(label).performClick()
        settle()
    }

    /** What TalkBack hears of the Passport tab: its label, then "New" while its dot shows. */
    private fun passportTab(): List<String> = tab("Passport").fetchSemanticsNode().config[SemanticsProperties.ContentDescription]

    /** The seen list kept for the reader, as written. */
    private fun seen(): String? = source.passportDefaults.string(forKey = SEEN)

    private val SEEN get() = "peek-passport-seen:${reader.user.id}"

    /** The pages TalkBack reaches. */
    private fun pagesReached(): List<Int> = (0 until PassportPage.COUNT).filter { page ->
        compose.onAllNodes(hasTestTag("$PASSPORT_PAGE.$page"), useUnmergedTree = true).fetchSemanticsNodes()
            .any { it.config.contains(SemanticsProperties.IsTraversalGroup) }
    }

    /** What TalkBack hears of a page's label: its name, then "New" while its dot shows. */
    private fun pageLabel(page: Int): List<String> = compose.onNodeWithTag("$PASSPORT_PAGE_LABEL.$page").fetchSemanticsNode().config[SemanticsProperties.ContentDescription]

    /** The parcels whose cards carry a news line. */
    private fun lines(): List<UUID> = compose.onAllNodes(
        SemanticsMatcher("a news line") { it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith("$PASSPORT_NEWS_LINE.") == true },
        useUnmergedTree = true,
    ).fetchSemanticsNodes().map { UUID.fromString(it.config[SemanticsProperties.TestTag].removePrefix("$PASSPORT_NEWS_LINE.")) }

    private fun line(parcel: UUID) = compose.onNodeWithTag("$PASSPORT_NEWS_LINE.$parcel", useUnmergedTree = true)

    private fun label(tag: String): String =
        compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config[SemanticsProperties.ContentDescription].single()

    @Test
    fun whatAPassportHoldsIsSeenOnItsFirstReadThenEachNewStampWaitsOnItsCardUntilThePassportOpens() {
        val first = listOf(delivered(0, CarrierId.Dhl, "2026-09-03T12:00:00Z"), delivered(1, CarrierId.Ups, "2026-09-04T12:00:00Z"))
        val current = onItsWay(9, "2026-10-28T08:00:00Z")
        show(first + current)
        // The first read on the device: everything earned and every country is seen, so nothing is new.
        assertEquals("""["countries:on","country:CH","stamp:express","stamp:first"]""", seen())
        assertEquals(emptyList<UUID>(), lines())
        assertEquals(listOf("Passport"), passportTab())

        // A third carrier's delivery earns Well connected: a line under its card alone, none under a current parcel's.
        val third = delivered(2, CarrierId.Fedex, "2026-09-05T12:00:00Z")
        update(first + third + current)
        assertEquals(listOf(third.id), lines())
        assertEquals("New stamp: Well connected", label("$PASSPORT_NEWS_LINE.${third.id}"))
        assertEquals(Role.Button, line(third.id).fetchSemanticsNode().config[SemanticsProperties.Role])
        // The tab's dot, heard with its label.
        assertEquals(listOf("Passport", "New"), passportTab())
        assertEquals("""["countries:on","country:CH","stamp:express","stamp:first"]""", seen())

        // The line opens the passport on the Stamps page, where the stamp is new; everything it holds is then seen.
        line(third.id).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        assertEquals(listOf(PassportPage.STAMPS), pagesReached())
        assertEquals("Well connected, Earned on 5 September 2026, New", label("$PASSPORT_STAMP.connected"))
        assertEquals("First arrival, Earned on 3 September 2026", label("$PASSPORT_STAMP.first"))
        assertEquals("""["countries:on","country:CH","stamp:connected","stamp:express","stamp:first"]""", seen())

        // Back on the deliveries, the line and the dot have gone; the passport opened again has nothing new.
        choose("Deliveries")
        assertEquals(emptyList<UUID>(), lines())
        assertEquals(listOf("Passport"), passportTab())
        choose("Passport")
        assertEquals("Well connected, Earned on 5 September 2026", label("$PASSPORT_STAMP.connected"))
    }

    @Test
    fun oneDeliveryNamesEveryStampItBroughtOnOneLine() {
        show(emptyList())
        assertEquals("""["countries:on"]""", seen())
        val parcel = delivered(0, CarrierId.Dhl, "2025-12-03T12:00:00Z")
        update(listOf(parcel))
        assertEquals(listOf(parcel.id), lines())
        // The country it reached first, then its stamps; its line's mark is the country's code.
        assertEquals("4 new stamps: Switzerland, First arrival, Express arrival, Home for the holidays", label("$PASSPORT_NEWS_LINE.${parcel.id}"))
        assertEquals(listOf("Passport", "New"), passportTab())
        assertTrue(compose.onAllNodes(hasText("CH"), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty())
    }

    /**
     * A delivery that reaches a country and earns a stamp: the passport opens on the Countries page, where the country is
     * new; the Stamps page's label carries the dot until that page shows, its stamp new there.
     */
    @Test
    fun aNewCountryOpensThePassportOnItsPageAndTheStampsWaitForTheirs() {
        val first = listOf(delivered(0, CarrierId.Dhl, "2026-09-03T12:00:00Z"), delivered(1, CarrierId.Ups, "2026-09-04T12:00:00Z"))
        show(first)
        val arrival = Instant.parse("2026-09-05T12:00:00Z")
        val japan = PassportFixtures.parcel(2, "JP", arrival.minus(Duration.ofDays(3)), arrival, carrier = CarrierId.Fedex)
        update(first + japan)
        assertEquals("2 new stamps: Japan, Well connected", label("$PASSPORT_NEWS_LINE.${japan.id}"))
        choose("Passport")
        assertEquals(listOf(PassportPage.COUNTRIES), pagesReached())
        assertEquals("Japan, First reached on 2 September 2026, 1 parcel, New", label("$PASSPORT_COUNTRY.JP"))
        assertEquals("Switzerland, First reached on 3 September 2026, 2 parcels", label("$PASSPORT_COUNTRY.CH"))
        assertEquals(listOf("Countries"), pageLabel(PassportPage.COUNTRIES))
        assertEquals(listOf("Stamps", "New"), pageLabel(PassportPage.STAMPS))
        compose.onNodeWithTag("$PASSPORT_PAGE_LABEL.${PassportPage.STAMPS}").performClick()
        settle()
        assertEquals("Well connected, Earned on 5 September 2026, New", label("$PASSPORT_STAMP.connected"))
        assertEquals(listOf("Stamps"), pageLabel(PassportPage.STAMPS))
    }

    /**
     * A list kept before countries counted: every country held now is taken as seen, with the marker, and never shows
     * as new; a stamp it hadn't seen still does.
     */
    @Test
    fun aListKeptBeforeCountriesCountedTakesTheirCountriesAsSeen() {
        val parcels = listOf(delivered(0, CarrierId.Dhl, "2026-09-03T12:00:00Z"), delivered(1, CarrierId.Ups, "2026-09-04T12:00:00Z"))
        show(parcels, kept = """["stamp:first"]""")
        assertEquals("""["countries:on","country:CH","stamp:first"]""", seen())
        assertEquals(listOf(parcels[0].id), lines())
        assertEquals("New stamp: Express arrival", label("$PASSPORT_NEWS_LINE.${parcels[0].id}"))
        choose("Passport")
        assertEquals(listOf(PassportPage.STAMPS), pagesReached())
        assertEquals("Switzerland, First reached on 3 September 2026, 2 parcels", countryLabel("CH"))
    }

    /** With nothing new the passport opens on the Countries page. */
    @Test
    fun withNothingNewThePassportOpensOnTheCountries() {
        show(listOf(delivered(0, CarrierId.Dhl, "2026-09-03T12:00:00Z")))
        choose("Passport")
        assertEquals(listOf(PassportPage.COUNTRIES), pagesReached())
        assertEquals(listOf("Countries"), pageLabel(PassportPage.COUNTRIES))
        assertEquals(listOf("Stamps"), pageLabel(PassportPage.STAMPS))
    }

    /** A country's label wherever its page stands, in view or not: read from the passport's book. */
    private fun countryLabel(code: String): String {
        compose.onNodeWithTag("$PASSPORT_PAGE_LABEL.${PassportPage.COUNTRIES}").performClick()
        settle()
        return label("$PASSPORT_COUNTRY.$code")
    }

    /** Nothing is written while the parcels load: a list read before they are in would hide what they bring. */
    @Test
    fun theSeenListWaitsForTheParcelsToLoad() {
        show(listOf(delivered(0, CarrierId.Dhl, "2026-09-03T12:00:00Z")), loading = true)
        assertNull(seen())
        assertEquals(emptyList<UUID>(), lines())
        compose.runOnUiThread { source.loading.value = false }
        settle()
        assertEquals("""["countries:on","country:CH","stamp:express","stamp:first"]""", seen())
        assertEquals(emptyList<UUID>(), lines())
        assertEquals(listOf("Passport"), passportTab())
    }
}
