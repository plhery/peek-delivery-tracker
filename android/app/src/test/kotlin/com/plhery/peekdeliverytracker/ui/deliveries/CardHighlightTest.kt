package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.KeyEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PixelMap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performMouseInput
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.awaitFrame
import com.plhery.peekdeliverytracker.ui.theme.PeekColors
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The list's cards and archived rows under a pointer and the keyboard (A-G05, Full Keyboard Access): a pointer over
 * one lights it up in its shape (`.hoverEffect(.highlight)`), and the keyboard's focus rings it just outside its edge.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class CardHighlightTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var view: View

    private fun show() {
        val source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.setContent {
            view = LocalView.current
            RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false)
        }
        compose.waitForIdle()
    }

    /** The window as it is now, once it has drawn a frame of it ([awaitFrame]). */
    private fun window(): PixelMap {
        compose.waitForIdle()
        compose.awaitFrame()
        return compose.onRoot().captureToImage().toPixelMap()
    }

    private fun bounds(name: String): Rect = compose.card(name).fetchSemanticsNode().boundsInRoot

    private val points: Float get() = view.resources.displayMetrics.density

    /** Inside the card named `name`, 6 points under the middle of its top edge, clear of what it writes. */
    private fun surface(name: String, map: PixelMap = window()): Color {
        val card = bounds(name)
        return map[card.center.x.toInt(), (card.top + 6 * points).toInt()]
    }

    /** A pointer over the card named `name` lights it up, and moving off puts it back as it was. */
    private fun assertLightsUnderAPointer(name: String) {
        val before = surface(name)
        compose.card(name).performMouseInput { moveTo(center) }
        val lit = surface(name)
        assertTrue("$name: $lit under the pointer, $before before", before.red - lit.red > 0.025f && before.blue - lit.blue > 0.02f)
        compose.card(name).performMouseInput { moveTo(Offset(-width.toFloat(), -height.toFloat())) }
        val after = surface(name)
        assertTrue("$name: $after after the pointer left, $before before", abs(after.red - before.red) < 0.01f)
    }

    @Test
    fun aPointerLightsUpACardAndAnArchivedRow() {
        show()
        assertLightsUnderAPointer("Birthday gift")

        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.onNodeWithContentDescription("Archived", substring = true).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed("Linen weekend bag"))
        compose.waitForIdle()
        assertLightsUnderAPointer("Linen weekend bag")
    }

    /** A key pressed and let go on a hardware keyboard, through the window as the system sends it. */
    private fun press(key: Int) {
        val window = view.rootView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, key, 0, 0))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, key, 0, 0))
        }
        compose.waitForIdle()
    }

    private fun focused(tag: String): Boolean =
        compose.onNodeWithTag(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Focused) == true

    /** The list's cards from the top, by what they are read as: its buttons but the filters' and search's over them. */
    private fun cards(): List<Pair<String, Rect>> =
        compose.onAllNodes(
            hasClickAction() and hasContentDescription("", substring = true) and hasAnyAncestor(hasTestTag(PARCEL_LIST)) and
                !hasTestTag(FILTERS_BUTTON) and !hasTestTag(SEARCH_BUTTON),
        )
            .fetchSemanticsNodes()
            .map { it.config[SemanticsProperties.ContentDescription].first() to it.boundsInRoot }
            .sortedBy { it.second.top }

    /** The cards drawn with the accent 1.5 points above the middle of their top edge, the middle of the ring. */
    private fun ringed(): List<String> {
        val map = window()
        val accent = PeekColors(dark = false).accent
        return cards().filter { (_, card) ->
            val pixel = map[card.center.x.toInt(), (card.top - 1.5f * points).toInt()]
            maxOf(abs(pixel.red - accent.red), abs(pixel.green - accent.green), abs(pixel.blue - accent.blue)) < 0.04f
        }.map { it.first }
    }

    /** Tab from the search button reaches the list's first card, which the ring goes round outside its row's cut. */
    @Test
    fun theKeyboardsFocusRingsTheFirstCardOutsideItsRow() {
        show()
        repeat(8) { if (!focused(SEARCH_BUTTON)) press(KeyEvent.KEYCODE_TAB) }
        assertTrue(focused(SEARCH_BUTTON))
        assertEquals(emptyList<String>(), ringed())

        press(KeyEvent.KEYCODE_TAB)
        val first = cards().first().first
        assertEquals(listOf(first), ringed())
    }
}
