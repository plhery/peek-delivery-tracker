package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.GlobeCamera
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.map.LocalMapCameraWatch
import com.plhery.peekdeliverytracker.ui.map.RouteEngraving
import com.plhery.peekdeliverytracker.ui.map.rememberParcelRoute
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.theme.shownCarrierIdentity
import java.time.Duration
import java.util.UUID
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A card's map drawn again when what it shows changes, as the iPhone's Canvas draws the new map at once: a refresh's
 * new route, or the app's language; and Next up's camera flying to the new frame, as the iPhone's `onChange(of:
 * target)`, or there at once under Reduce Motion. Each test draws its maps at a size of its own, so the new picture is
 * made while the test waits rather than found made by another test, and compares the map with one drawn afresh in its
 * place.
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
            Modifier.size(300.dp, height).testTag(MAP), peek = false,
        )
    }

    /** `parcel` scanned again in Geneva, an hour before the captures. */
    private fun scannedInGeneva(parcel: Parcel): Parcel = parcel.copy(
        trackingEvents = parcel.trackingEvents + TrackingEvent(
            id = UUID.nameUUIDFromBytes("geneva".toByteArray()), packageId = parcel.id, stage = TrackingStage.InTransit,
            description = "Sorted.", occurredAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))),
            place = EventPlace(
                latitude = 46.204, longitude = 6.143, precision = EventPlacePrecision.City, country = "CH", name = "Genève",
            ),
        ),
    )

    /** Next up's map as New trainers is scanned somewhere new: what it looked from, frame by frame, and its pictures. */
    private class Scan(
        val start: GlobeCamera?, val frames: List<GlobeCamera?>, val end: GlobeCamera?, val midway: IntArray, val landed: IntArray,
    )

    private fun scan(reduceMotion: Boolean): Scan {
        compose.waitsForMaps()
        val trainers = DemoCards.parcel("New trainers")
        var shown by mutableStateOf(trainers)
        val cameras = mutableListOf<GlobeCamera>()
        compose.setContent {
            CompositionLocalProvider(LocalMapCameraWatch provides { _, _, camera -> cameras += camera }) {
                CardHost(reduceMotion = reduceMotion) { Engraving(shown, 163.dp) }
            }
        }
        compose.waitForIdle()
        val start = cameras.lastOrNull()
        // As it first shows, it takes its frame without flying there.
        assertTrue(cameras.all { it == start })
        compose.mainClock.autoAdvance = false
        shown = scannedInGeneva(trainers)
        var midway = IntArray(0)
        val frames = List(120) { frame ->
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
            if (frame == 30) midway = pixels()
            cameras.lastOrNull()
        }
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        return Scan(start, frames, cameras.lastOrNull(), midway, pixels())
    }

    @Test
    fun nextUpFliesToItsNewFrameHavingTakenItsFirstAtOnce() {
        val scan = scan(reduceMotion = false)
        assertFalse("Half a second on, still on its way", scan.midway.contentEquals(scan.landed))
        val start = checkNotNull(scan.start)
        val end = checkNotNull(scan.end)
        assertNotEquals(start, end)
        val setOff = scan.frames.indexOfFirst { it != start }
        val landed = scan.frames.indexOfFirst { it == end }
        // On its way for 0.7 to 1.5 s, a camera of its own nearly every frame: the flight's clock starts in the frame
        // after the one that sets it off.
        val between = scan.frames.subList(setOff, landed)
        assertTrue("${between.size} frames", between.size * 0.016 in 0.7..1.5)
        assertTrue("${between.distinct().size} cameras", between.distinct().size >= between.size - 2)
        assertTrue(scan.frames.drop(landed).all { it == end })
    }

    @Test
    fun underReduceMotionNextUpTakesItsNewFrameAtOnce() {
        val scan = scan(reduceMotion = true)
        val start = checkNotNull(scan.start)
        val end = checkNotNull(scan.end)
        assertNotEquals(start, end)
        assertTrue(scan.frames.all { it == start || it == end })
        assertTrue(scan.midway.contentEquals(scan.landed))
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
