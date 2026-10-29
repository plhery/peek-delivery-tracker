package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.map.RouteEngraving
import com.plhery.peekdeliverytracker.ui.map.rememberParcelRoute
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.theme.shownCarrierIdentity
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A card's map drawn again when what it shows changes, as the iPhone's Canvas draws the new map at once: a refresh's
 * new route, or the app's language. Each test draws its maps at a size of its own, so the new picture is made while
 * the test waits rather than found made by another test, and compares the map with one drawn afresh in its place.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class CardMapsTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    /** `parcel`'s route as Next up engraves it, `height` tall, in the language around it. */
    @Composable
    private fun Engraving(parcel: Parcel, height: Dp) {
        val atlas = rememberWorldAtlas() ?: return
        val language = LocalLanguage.current
        val identity = shownCarrierIdentity(parcel, LocalLocalizer.current, language) ?: return
        RouteEngraving(
            atlas, rememberParcelRoute(parcel, atlas, language), parcel.currentStage, identity, language,
            Modifier.size(300.dp, height).testTag(MAP),
        )
    }

    private fun pixels(): IntArray = compose.drawn(compose.onNodeWithTag(MAP))

    @Test
    fun aMapWhoseParcelChangesShowsTheNewRoute() {
        compose.waitsForMaps()
        val trainers = DemoCards.parcel("New trainers")
        val lamp = DemoCards.parcel("Moon lamp")
        var shown by mutableStateOf(trainers)
        var drawing by mutableIntStateOf(0)
        compose.setContent {
            CardHost { key(drawing) { Engraving(shown, 161.dp) } }
        }
        compose.waitForIdle()
        val before = pixels()
        shown = lamp
        compose.waitForIdle()
        val after = pixels()
        drawing++
        compose.waitForIdle()
        val drawnAfresh = pixels()
        assertFalse("The two parcels' maps differ", before.contentEquals(drawnAfresh))
        assertTrue("After the change, the map is the new parcel's", after.contentEquals(drawnAfresh))
    }

    @Test
    fun aMapIsWrittenAgainInANewLanguage() {
        compose.waitsForMaps()
        // Handed over somewhere in France, which the map names by the country at the start of the route: "Frankreich"
        // in German.
        val gift = DemoCards.parcel("Birthday gift").let { parcel ->
            parcel.copy(
                trackingEvents = parcel.trackingEvents.map { event ->
                    val place = event.place
                    if (place?.country == "FR") event.copy(place = place.copy(precision = EventPlacePrecision.Country)) else event
                },
            )
        }
        val localizer = Localizer(AppLanguage.En, variant = "android")
        var drawing by mutableIntStateOf(0)
        compose.setContent {
            CardHost(localizer = localizer) { key(drawing) { Engraving(gift, 162.dp) } }
        }
        compose.waitForIdle()
        val english = pixels()
        localizer.language = AppLanguage.De
        compose.waitForIdle()
        val after = pixels()
        drawing++
        compose.waitForIdle()
        val german = pixels()
        assertFalse("The map reads differently in English and German", english.contentEquals(german))
        assertTrue("After the change, the map is the German one", after.contentEquals(german))
    }

    private companion object {
        const val MAP = "map"
    }
}
