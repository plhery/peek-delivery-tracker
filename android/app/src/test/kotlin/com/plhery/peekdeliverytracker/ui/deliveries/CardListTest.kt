package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The cards in a lazy list, as the next step's list holds them: a card scrolled out and back shows as it was, as the
 * iPhone's lazy list keeps its cards' state, and a card says where it stands in its item, which scrolling leaves alone.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class CardListTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val buttons = SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button)

    @Test
    fun aCardScrolledBackIntoTheListShowsAtOnceWithoutFadingInAgain() {
        compose.waitsForMaps()
        // Next up without a map fades in and settles its stamp as it first shows; the card below fades its map in.
        val next = DemoCards.parcel("35mm film rolls")
        val card = DemoCards.parcel("Moon lamp")
        val state = LazyListState()
        compose.setContent {
            CardHost(reduceMotion = false) {
                LazyColumn(Modifier.height(700.dp).testTag("list"), state) {
                    item(key = next.id) { NextDeliveryPass(next, onOpen = {}) }
                    item(key = card.id) { ParcelPassCard(card, notice = null, onOpen = {}, modifier = Modifier.padding(top = 12.dp)) }
                    item(key = "below") { Spacer(Modifier.height(2000.dp)) }
                }
            }
        }
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(2000)
        compose.onAllNodes(buttons).assertCountEquals(2)
        val settled = compose.drawn(compose.onNodeWithTag("list"))

        compose.mainClock.autoAdvance = false
        try {
            compose.runOnUiThread { runBlocking { state.scrollToItem(2) } }
            compose.mainClock.advanceTimeByFrame()
            compose.onAllNodes(buttons).assertCountEquals(0)
            compose.runOnUiThread { runBlocking { state.scrollToItem(0) } }
            compose.mainClock.advanceTimeByFrame()
            compose.onAllNodes(buttons).assertCountEquals(2)
            val back = compose.drawn(compose.onNodeWithTag("list"))
            val changed = back.indices.count { back[it] != settled[it] }
            assertEquals("Pixels unlike the settled list in the first frame back", 0, changed)
        } finally {
            // The list settles before the test ends: one ended between two frames with the clock held left the later
            // cards' screenshots without their maps.
            compose.mainClock.autoAdvance = true
            compose.waitForIdle()
        }
    }

    @Test
    fun aCardSaysWhereItStandsInItsItemWhichScrollingLeavesAlone() {
        compose.waitsForMaps()
        val parcel = DemoCards.parcel("Moon lamp")
        val names = mutableListOf<Float>()
        val stands = mutableListOf<Rect>()
        val state = LazyListState()
        compose.setContent {
            CardHost {
                LazyColumn(Modifier.height(600.dp), state) {
                    item(key = "above") { Spacer(Modifier.height(300.dp)) }
                    item(key = parcel.id) {
                        Box(Modifier.deliveryListItem().padding(top = 12.dp)) {
                            ParcelPassCard(
                                parcel, notice = null, onOpen = {}, onName = { names += it },
                                modifier = Modifier.deliveryCardPlace(parcel.id, flying = false) { _, frame -> stands += frame },
                            )
                        }
                    }
                    item(key = "below") { Spacer(Modifier.height(2000.dp)) }
                }
            }
        }
        compose.waitForIdle()
        val top = compose.onAllNodes(buttons)[0].getBoundsInRoot().top
        assertEquals(1, stands.size)
        assertEquals(1, names.size)
        // 12 points down its item, the list's 408 points wide; its name as on a card alone (CardSemanticsTest).
        assertEquals(0f, stands[0].left, 0.01f)
        assertEquals(12f, stands[0].top, 0.01f)
        assertEquals(408f, stands[0].width, 0.01f)
        assertEquals(12f + 5 + 13 + 18 + 3 + 5 + 10, names[0], 1f)

        val density = compose.density.density
        compose.runOnUiThread { runBlocking { state.scrollBy(150 * density) } }
        compose.waitForIdle()
        assertEquals("The card moved up the screen", top.value - 150, compose.onAllNodes(buttons)[0].getBoundsInRoot().top.value, 0.5f)
        assertEquals("Nothing to tell: its place in its item is the same", 1, stands.size)
        assertEquals(1, names.size)
        assertTrue(stands.single().height > 0)
    }
}
