package com.plhery.peekdeliverytracker.ui.map

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.awaitFrame
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.CardHost
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.NextDeliveryPass
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelPassCard
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A screenshot taken as soon as a card's map has come shows the map. The map comes a frame after its card, into an
 * offscreen layer (for its fades), and Robolectric's renderer now and then puts off drawing such a frame: a screenshot
 * taken before the renderer drew again showed the card without its map, and many of these cards came out so. A map
 * made at once, as a flight's card has it, is there in its card's first frame.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class MapScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    @Test
    fun aScreenshotTakenAsTheMapComesShowsIt() {
        compose.waitsForMaps()
        val parcels = listOf("Moon lamp", "Kind of Blue", "Trail weekend kit", "Belgian chocolate", "Birthday gift", "New trainers", "Coffee beans")
            .map { DemoCards.parcel(it) }
        var shown: Parcel? by mutableStateOf(null)
        var dark by mutableStateOf(false)
        var width by mutableIntStateOf(0)
        var generation by mutableIntStateOf(0)
        compose.setContent {
            CardHost(dark = dark) {
                shown?.let { key(generation) { ParcelPassCard(it, notice = null, onOpen = {}, modifier = Modifier.width(width.dp)) } }
            }
        }
        val unlike = mutableListOf<String>()
        // Each width asks for new maps, made off the main thread, so each map comes a frame after its card.
        for (cardWidth in listOf(300, 310, 320, 330)) for (inDark in listOf(false, true)) for (parcel in parcels) {
            width = cardWidth
            dark = inDark
            shown = parcel
            generation++
            compose.waitForIdle()
            val first = shot()
            // The same card again, whose map, made now, comes with it.
            generation++
            compose.waitForIdle()
            if (!shot().contentEquals(first)) unlike += "${parcel.label} at $cardWidth dp" + if (inDark) " in dark" else ""
        }
        assertEquals("Cards shot unlike themselves drawn again", emptyList<String>(), unlike)
    }

    @Test
    fun aMapMadeAtOnceIsThereInItsCardsFirstFrame() {
        // The atlas read, as the list has it once its maps show, but no map waited for.
        runBlocking { WorldAtlases.load().await() }
        val unlike = mutableListOf<String>()
        var next: Boolean? by mutableStateOf(null)
        var generation by mutableIntStateOf(0)
        compose.setContent {
            CardHost {
                // Widths no other map is drawn at, whose pictures have not been made.
                key(generation) {
                    CompositionLocalProvider(LocalMapsAtOnce provides true) {
                        if (next == true) {
                            NextDeliveryPass(DemoCards.parcel("Moon lamp"), onOpen = {}, modifier = Modifier.width(347.dp), decorative = true)
                        } else if (next == false) {
                            ParcelPassCard(DemoCards.parcel("Trail weekend kit"), notice = null, onOpen = {}, modifier = Modifier.width(343.dp), decorative = true)
                        }
                    }
                }
            }
        }
        compose.mainClock.autoAdvance = false
        // The card composed in the next frame, and nothing else changed: no map made elsewhere comes in meanwhile.
        fun frame(change: () -> Unit) {
            compose.runOnUiThread {
                change()
                Snapshot.sendApplyNotifications()
            }
            compose.mainClock.advanceTimeByFrame()
        }
        for (kind in listOf(true, false)) {
            frame {
                next = kind
                generation++
            }
            val first = shot()
            // The same card again, whose map was made before it.
            frame { generation++ }
            if (!shot().contentEquals(first)) unlike += if (kind) "Next up" else "a card"
        }
        assertEquals("Cards whose first frame lacks their map", emptyList<String>(), unlike)
    }

    private fun shot(): IntArray {
        compose.awaitFrame()
        val bitmap = compose.onNodeWithTag(SHOT).captureToImage().asAndroidBitmap()
        return IntArray(bitmap.width * bitmap.height).also { bitmap.getPixels(it, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height) }
    }
}
