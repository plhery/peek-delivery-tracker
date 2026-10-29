package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.BackEventCompat
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** How a parcel's page comes over the list and goes, until it zooms from its card: the iPhone's push. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class DeliveriesTabTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private fun left(tag: String): Float = compose.onNodeWithTag(tag).getBoundsInRoot().left.value

    private fun width(): Float = compose.onRoot().getBoundsInRoot().right.value

    /**
     * Sends `events` to the back dispatcher as the system does, and lets the main thread act on them without moving
     * the clock: what they change reaches the composition from a message of its own.
     */
    private fun gesture(vararg events: BackEventCompat) {
        val back = compose.activity.onBackPressedDispatcher
        compose.runOnUiThread {
            back.dispatchOnBackStarted(events.first())
            for (event in events.drop(1)) back.dispatchOnBackProgressed(event)
        }
        compose.waitForIdle()
    }

    /** Back, pressed or a gesture let go. */
    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    /** The finger `progress` of the way across from the left edge. */
    private fun at(progress: Float) = BackEventCompat(480f * progress, 400f, progress, BackEventCompat.EDGE_LEFT)

    /** Partway, the page some way in from the trailing edge and the list a third as far out the other way. */
    private fun assertPartway() {
        val width = width()
        val page = left(PARCEL_PAGE)
        assertTrue("The page partway: $page of $width", page > 0.2f * width && page < 0.8f * width)
        assertEquals(-(width - page) / 3, left(PARCEL_LIST), 1f)
    }

    @Test
    fun theParcelsPageSlidesInOverTheListAndBackOut() {
        compose.setContent { RootHost(reduceMotion = false) }
        compose.mainClock.autoAdvance = false
        compose.onNodeWithText("Parcel 3").performClick()
        // 0.1 s into a spring of response 0.35 s, a little over half the way.
        compose.mainClock.advanceTimeBy(100)
        assertPartway()
        compose.mainClock.advanceTimeBy(600)
        assertEquals(0f, left(PARCEL_PAGE), 0.5f)
        compose.onNodeWithTag(PARCEL_LIST).assertDoesNotExist()

        back()
        compose.mainClock.advanceTimeBy(100)
        assertPartway()
        compose.mainClock.advanceTimeBy(600)
        compose.onNodeWithTag(PARCEL_PAGE).assertDoesNotExist()
        assertEquals(0f, left(PARCEL_LIST), 0.5f)
    }

    @Test
    fun thePredictiveBackGestureMovesThePageWithTheFinger() {
        compose.setContent { RootHost(reduceMotion = false) }
        compose.onNodeWithText("Parcel 3").performClick()
        compose.waitForIdle()
        gesture(at(0f), at(0.25f))
        val width = width()
        // A quarter of the way through the gesture, the page a quarter out and the list three quarters of its third.
        assertEquals(width / 4, left(PARCEL_PAGE), 1f)
        assertEquals(-width / 4, left(PARCEL_LIST), 1f)
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.dispatchOnBackProgressed(at(0.5f)) }
        compose.waitForIdle()
        assertEquals(width / 2, left(PARCEL_PAGE), 1f)
        assertEquals(-width / 6, left(PARCEL_LIST), 1f)

        // Let go, the page goes the rest of the way: half of 0.35 s at the same pace.
        compose.mainClock.autoAdvance = false
        back()
        compose.mainClock.advanceTimeBy(100)
        assertTrue(left(PARCEL_PAGE) > 0.6f * width)
        compose.mainClock.advanceTimeBy(150)
        compose.onNodeWithTag(PARCEL_PAGE).assertDoesNotExist()
        assertEquals(0f, left(PARCEL_LIST), 0.5f)
    }

    @Test
    fun underReduceMotionThePageComesAndGoesAtOnce() {
        compose.setContent { RootHost(reduceMotion = true) }
        compose.mainClock.autoAdvance = false
        compose.onNodeWithText("Parcel 3").performClick()
        compose.mainClock.advanceTimeBy(50)
        assertEquals(0f, left(PARCEL_PAGE), 0.5f)
        compose.onNodeWithTag(PARCEL_LIST).assertDoesNotExist()

        // The gesture moves nothing, and letting go shows the list.
        gesture(at(0f), at(0.5f))
        compose.mainClock.advanceTimeBy(50)
        assertEquals(0f, left(PARCEL_PAGE), 0.5f)
        back()
        compose.mainClock.advanceTimeBy(50)
        compose.onNodeWithTag(PARCEL_PAGE).assertDoesNotExist()
        assertEquals(0f, left(PARCEL_LIST), 0.5f)
    }
}
