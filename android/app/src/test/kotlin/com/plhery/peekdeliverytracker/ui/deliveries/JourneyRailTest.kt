package com.plhery.peekdeliverytracker.ui.deliveries

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.localizationKey
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.ProvideLocalizer
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlin.math.roundToInt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * `ExperimentalJourneyRail`: only a new current step eases (`.animation(.easeOut(duration: 0.24), value: current)`,
 * A-AN39), a new tint alone showing at once; and TalkBack reads it as one element, "Delivery progress" and the step
 * (A-AX15).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class JourneyRailTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private var stage: TrackingStage? by mutableStateOf(TrackingStage.InTransit)
    private var tint by mutableStateOf(Color.Red)

    /** The rail, 300 points wide on white, its clock in the test's hands. */
    private fun rail() {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, carrierIdentities = null) {
                ProvideLocalizer(localizer) {
                    Box(Modifier.background(Color.White).padding(20.dp)) {
                        JourneyRail(stage, tint, Modifier.width(300.dp).testTag(RAIL))
                    }
                }
            }
        }
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
    }

    private fun set(stage: TrackingStage? = this.stage, tint: Color = this.tint) {
        compose.runOnUiThread {
            this.stage = stage
            this.tint = tint
            Snapshot.sendApplyNotifications()
        }
    }

    private fun frame() {
        compose.mainClock.advanceTimeByFrame()
        compose.waitForIdle()
    }

    /** The middle of the rail's step `index` of six, drawn now, as 0…255 red, green and blue. */
    private fun step(index: Int): Triple<Int, Int, Int> {
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.waitForIdle()
        val whole = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(Canvas(whole)) }
        val rail = compose.onNodeWithTag(RAIL).fetchSemanticsNode().boundsInRoot
        val pixel = whole.getPixel((rail.left + rail.width * (index + 0.5f) / 6).roundToInt(), rail.center.y.toInt())
        return Triple(pixel shr 16 and 0xFF, pixel shr 8 and 0xFF, pixel and 0xFF)
    }

    @Test
    fun aNewTintAloneShowsAtOnce() {
        rail()
        assertEquals(Triple(255, 0, 0), step(0))
        // The appearance changing, or the carrier's colour: the filled steps take it in the next frame.
        set(tint = Color.Blue)
        // The rail composed with it, the colour taken and drawn: what an animation would only have started.
        repeat(3) { frame() }
        assertEquals(Triple(0, 0, 255), step(0))
        assertEquals(Triple(0, 0, 255), step(3))
    }

    @Test
    fun aNewStepEasesIn() {
        rail()
        val rest = step(4)
        set(stage = TrackingStage.OutForDelivery)
        repeat(4) { frame() }
        val (red, green, blue) = step(4)
        assertTrue("on its way from $rest to red: ($red, $green, $blue)", green in 1 until rest.second && blue in 1 until rest.third)
        compose.mainClock.advanceTimeBy(500)
        assertEquals(Triple(255, 0, 0), step(4))
        // A tint changing with the step eases with it, as the transaction animates both.
        set(stage = TrackingStage.Delivered, tint = Color.Blue)
        repeat(4) { frame() }
        val (_, _, eased) = step(0)
        assertTrue("$eased", eased in 1..254)
    }

    @Test
    fun talkBackReadsTheRailAsOneElementWithItsStep() {
        rail()
        val node = compose.onNodeWithTag(RAIL).fetchSemanticsNode()
        assertEquals(listOf("Delivery progress"), node.config[SemanticsProperties.ContentDescription])
        val inTransit = localizer.text(TrackingStage.InTransit.localizationKey)
        assertEquals("Step 4 of 6: $inTransit", node.config[SemanticsProperties.StateDescription])
        assertTrue(node.children.isEmpty())

        set(stage = null)
        frame()
        assertEquals(
            "Step 1 of 6: Waiting for an update",
            compose.onNodeWithTag(RAIL).fetchSemanticsNode().config[SemanticsProperties.StateDescription],
        )
    }

    private companion object {
        const val RAIL = "rail"
    }
}
