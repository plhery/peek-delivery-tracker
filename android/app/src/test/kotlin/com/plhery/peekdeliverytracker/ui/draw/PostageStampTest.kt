package com.plhery.peekdeliverytracker.ui.draw

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The stamp's settle (A-AN22): from 1.12 and -10° to 1 and -2° once it has appeared, 0.08 s later on a spring of
 * response 0.48 s and damping 0.6 that overshoots a little; settled from the start under Reduce Motion. Read from how
 * wide its paper stands, turned and scaled, on black.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PostageStampTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private var appeared by mutableStateOf(false)
    private var reduceMotion by mutableStateOf(true)

    /** The stamp on black, drawn anew as Reduce Motion changes, the clock in the test's hands. */
    private fun stamp() {
        val parcel = DemoCards.parcel("New trainers")
        val identity = DemoCards.identities.of(parcel.carrier, AppLanguage.En)
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = reduceMotion, carrierIdentities = DemoCards.identities) {
                Box(Modifier.testTag(FRAME).background(Color.Black).padding(30.dp)) {
                    key(reduceMotion) { DeliveryPostageStamp(parcel, identity, appeared = appeared) }
                }
            }
        }
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
    }

    private fun set(reduceMotion: Boolean) {
        compose.runOnUiThread {
            this.reduceMotion = reduceMotion
            Snapshot.sendApplyNotifications()
        }
        compose.mainClock.advanceTimeBy(100)
    }

    /** How wide the paper stands now, in pixels: the light columns of the frame. */
    private fun width(): Int {
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.waitForIdle()
        val whole = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(Canvas(whole)) }
        val frame = compose.onNodeWithTag(FRAME).fetchSemanticsNode().boundsInRoot
        val columns = (frame.left.toInt() until frame.right.toInt()).filter { x ->
            (frame.top.toInt() until frame.bottom.toInt()).any { y -> whole.getPixel(x, y).let { (it shr 8 and 0xFF) > 128 } }
        }
        return columns.last() - columns.first() + 1
    }

    private fun appear() = compose.runOnUiThread {
        appeared = true
        Snapshot.sendApplyNotifications()
    }

    private fun frames(count: Int): List<Int> = List(count) {
        compose.mainClock.advanceTimeByFrame()
        width()
    }

    @Test
    fun itSettlesAMomentAfterItAppearsWithABounce() {
        stamp()
        val settled = width()
        set(reduceMotion = false)
        val askew = width()
        assertTrue("larger and turned: $askew against $settled", askew > settled + 16)
        appear()
        // The 0.08 s delay: four frames still.
        assertEquals(List(4) { askew }, frames(4))
        val widths = frames(60)
        assertTrue("on its way: $widths", widths.take(20).any { it in settled + 2 until askew - 2 })
        assertTrue("past its rest and back: $widths", widths.min() < settled - 1)
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(settled.toFloat(), width().toFloat(), 1f)
    }

    @Test
    fun underReduceMotionItStandsSettledFromTheStart() {
        stamp()
        val settled = width()
        appear()
        assertEquals(List(10) { settled }, frames(10))
    }

    private companion object {
        const val FRAME = "frame"
    }
}
