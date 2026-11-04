package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.MAP_BAR
import com.plhery.peekdeliverytracker.ui.deliveries.MAP_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.MAP_COVER_MAP
import com.plhery.peekdeliverytracker.ui.deliveries.MAP_VIEWS
import com.plhery.peekdeliverytracker.ui.deliveries.MapStates
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_GLOBE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The opened map's summary in each state of the journey (A-ST30) on this device's own window: delivered where it is
 * known, with the journey's length and no view switch; through several countries; scanned where it is now; one place
 * and nowhere to go; headed for a country, with how far it has to go; its newest scan nowhere said; and sent back. Each
 * saved as stateswalk/<row>-<moment>.png in the test package's external files, which adb pulls, the first with -dark.
 * Signed in on fake deliveries ([ParcelWalk]): nothing goes to the network. In Zurich's time at the captures' moment,
 * under Reduce Motion, as the other device tests are.
 */
@RunWith(AndroidJUnit4::class)
class ParcelMapStatesDeviceShots {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /** The demo, each parcel in `changed` in place of the demo's of the same id. */
    private fun demo(vararg changed: Parcel): List<Parcel> =
        DemoCards.parcels().map { parcel -> changed.firstOrNull { it.id == parcel.id } ?: parcel }

    private fun List<Parcel>.named(name: String): Parcel = first { it.label.startsWith(name) }

    /** `parcel`'s map, opened from the globe on its page and at rest. */
    private fun openMap(parcel: Parcel) {
        walk.open(parcel)
        walk.tap(PAGE_GLOBE)
        compose.waitUntil(LONG_MILLIS) { walk.exists(MAP_COVER_MAP) && walk.exists(MAP_BAR) }
        walk.settle()
    }

    private fun closeMap() {
        walk.tap(MAP_CLOSE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(MAP_COVER_MAP) }
        walk.settle()
    }

    /** The ends of the journey the summary shows, as TalkBack hears each: its title, the place, and the country. */
    private fun ends(): List<String> = compose.onAllNodes(hasAnyAncestor(hasTestTag(MAP_BAR)), useUnmergedTree = true)
        .fetchSemanticsNodes()
        .mapNotNull { it.config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull() }
        .filter { ENDS.any { title -> it.startsWith("$title, ") } }

    /** The summary's figures: how far, how far to go and through how many countries. */
    private fun figures(): List<String> = compose.onAllNodes(hasAnyAncestor(hasTestTag(MAP_BAR)), useUnmergedTree = true)
        .fetchSemanticsNodes()
        .flatMap { node -> node.config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text } }
        .filter { it.contains(" km") || it.contains("countr") }
        .distinct()

    private fun assertFigures(vararg patterns: String) {
        val figures = figures()
        assertEquals(figures.toString(), patterns.size, figures.size)
        patterns.zip(figures).forEach { (pattern, figure) -> assertTrue(figures.toString(), Regex(pattern).matches(figure)) }
    }

    /** Delivered, through several countries, scanned where it is now, and one place with nowhere to go. */
    @Test
    fun theDemosJourneys() {
        val parcels = demo()
        walk.show(parcels)

        openMap(parcels.named("Coffee beans"))
        assertEquals(listOf("From, Zürich, Switzerland", "Delivered, Zürich, Switzerland"), ends())
        assertFigures(KILOMETRES)
        assertFalse(walk.exists(MAP_VIEWS))
        walk.shot("A-ST30-delivered")
        closeMap()

        openMap(parcels.named("Matcha ritual"))
        assertEquals(listOf("From, Kyoto, Japan", "Delivered, Zürich, Switzerland"), ends())
        assertFigures(KILOMETRES, "5 countries")
        walk.shot("A-ST30-countries", alsoDark = false)
        closeMap()

        openMap(parcels.named("Moon lamp"))
        assertEquals(listOf("From, Berlin, Germany", "Now, Neuenstein, Germany"), ends())
        assertFigures("$KILOMETRES so far")
        assertTrue(walk.exists(MAP_VIEWS))
        walk.shot("A-ST30-now", alsoDark = false)
        closeMap()

        openMap(parcels.named("Trail weekend kit"))
        assertEquals(listOf("Now, Buchs, Switzerland"), ends())
        assertFigures()
        assertFalse(walk.exists(MAP_VIEWS))
        walk.shot("A-ST30-one-place", alsoDark = false)
    }

    /** Headed for a country its carrier names, and last seen where it was before a scan nowhere said. */
    @Test
    fun headedSomewhereAndLastSeen() {
        val parcels = demo(MapStates.headed(), MapStates.unplaced())
        walk.show(parcels)

        openMap(parcels.named("Moon lamp"))
        assertEquals(listOf("From, Berlin, Germany", "To, Switzerland"), ends())
        assertFigures("$KILOMETRES so far", "$KILOMETRES to go")
        walk.shot("A-ST30-to", alsoDark = false)
        closeMap()

        openMap(parcels.named("Kind of Blue"))
        assertEquals(listOf("From, Amsterdam, Netherlands", "Last seen, Eindhoven, Netherlands"), ends())
        assertFigures("$KILOMETRES so far")
        walk.shot("A-ST30-last-seen", alsoDark = false)
    }

    /** Sent back: the journey's length and nothing to go. */
    @Test
    fun returned() {
        val parcels = demo(MapStates.returned())
        walk.show(parcels)
        openMap(parcels.named("Moon lamp"))
        assertEquals(listOf("From, Berlin, Germany", "Now, Berlin, Germany"), ends())
        assertFigures(KILOMETRES)
        walk.shot("A-ST30-returned", alsoDark = false)
    }

    private companion object {
        /** The titles an end of the journey goes by, in English. */
        val ENDS = listOf("From", "To", "Now", "Last seen", "Delivered")

        /** A distance as the summary writes it. */
        const val KILOMETRES = "[\\d,]+ km"
    }
}
