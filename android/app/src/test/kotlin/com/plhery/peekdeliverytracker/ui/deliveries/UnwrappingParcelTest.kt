package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.PipLabel
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.awaitFrame
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * `UnwrappingParcel` on its way open (B-AN20): a gift and a labelled parcel at a few points of the opening, the stars
 * at a few moments of their burst, and the sheet's timing of it: a quarter of a second, then the spring, which goes a
 * hair past open and settles, or open at once under Reduce Motion. The welcome's lean turns the box as SwiftUI's
 * `rotation3DEffect` does, the same way and with the same perspective.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w1200dp-h310dp-mdpi")
class UnwrappingParcelTest {
    @get:Rule
    val compose = createComposeRule()

    private val identities by lazy { runBlocking { CarrierIdentities.load(CarrierCatalog.shared) } }

    private fun theme(reduceMotion: Boolean = false, content: @Composable () -> Unit) = compose.setContent {
        PeekTheme(dark = false, highContrast = false, reduceMotion = reduceMotion, reduceTransparency = false, carrierIdentities = identities, content = content)
    }

    /**
     * Four of the parcel side by side: about to open, the flaps going up, past upright, and settling. Off the ground, the
     * box alone: the link sheet's shots show it standing on its shadow.
     */
    private fun opening(label: PipLabel?, ribbon: Boolean) {
        theme {
            Row(Modifier.testTag(SHOT).background(Color.White)) {
                for (open in OPENING) UnwrappingParcel(open, label, ribbon, grounded = false)
            }
        }
    }

    /**
     * Pip's 300 × 310 frame filled black, turned [degrees] about its x axis, or its y axis where [aboutY], with the
     * parcel's perspective, as the window draws it on white: how long its two opposite edges are drawn, the leading
     * then the trailing one (about y), or the top then the bottom one (about x), each a little in.
     */
    private fun turnedEdges(degrees: Float, aboutY: Boolean): Pair<Int, Int> {
        compose.setContent {
            Box(Modifier.testTag(SHOT).background(Color.White).padding(40.dp)) {
                Box(Modifier.size(300.dp, 310.dp).rotation3DEffect(degrees, aboutY, perspective = 0.35f, longerSide = 310.dp).background(Color.Black))
            }
        }
        compose.waitForIdle()
        compose.awaitFrame()
        val image = compose.onNodeWithTag(SHOT).captureToImage().asAndroidBitmap()
        fun dark(x: Int, y: Int) = Color(image.getPixel(x, y)).red < 0.5f
        val columns = (0 until image.width).filter { x -> (0 until image.height).any { dark(x, it) } }
        val rows = (0 until image.height).filter { y -> (0 until image.width).any { dark(it, y) } }
        return if (aboutY) {
            (0 until image.height).count { dark(columns.first() + 3, it) } to (0 until image.height).count { dark(columns.last() - 3, it) }
        } else {
            (0 until image.width).count { dark(it, rows.first() + 3) } to (0 until image.width).count { dark(it, rows.last() - 3) }
        }
    }

    // The eye 310 / 0.35 = 886 away: turned 20°, the frame's far edge goes 51 back (53 about x) and its near edge as
    // far forward, so the far one is drawn (886 - 51) / (886 + 51) times as long as the near one.

    @Test
    @Config(qualifiers = PHONE)
    fun turnedAboutItsUprightAxisTheRightSideGoesBackAsInSwiftUI() {
        val (leading, trailing) = turnedEdges(20f, aboutY = true)
        assertEquals("$trailing over $leading", 0.8905f, trailing.toFloat() / leading, 0.005f)
    }

    @Test
    @Config(qualifiers = PHONE)
    fun turnedAboutItsLevelAxisTheTopGoesBackAsInSwiftUI() {
        val (top, bottom) = turnedEdges(20f, aboutY = false)
        assertEquals("$top over $bottom", 0.8871f, top.toFloat() / bottom, 0.005f)
    }

    @Test
    fun aGiftOpening() {
        opening(label = null, ribbon = true)
        compose.screenshot("unwrapping_parcel_gift")
    }

    @Test
    fun aLabelledParcelOpening() {
        opening(PipLabel(identities.of(CarrierId("dhl"), AppLanguage.En), "PK 0000 0000 0001"), ribbon = false)
        compose.screenshot("unwrapping_parcel_label")
    }

    @Test
    fun itsStarsBursting() {
        // Open parcels told to celebrate 0.45 s apart, the first 1.35 s ago.
        compose.mainClock.autoAdvance = false
        val celebrating = List(3) { mutableStateOf(false) }
        theme {
            Row(Modifier.testTag(SHOT).background(Color.White)) {
                for (one in celebrating) UnwrappingParcel(1.0, null, ribbon = false, celebrating = one.value, grounded = false)
            }
        }
        compose.mainClock.advanceTimeByFrame()
        for (one in celebrating) {
            compose.runOnIdle {
                one.value = true
                Snapshot.sendApplyNotifications()
            }
            compose.mainClock.advanceTimeBy(450)
        }
        compose.screenshot("unwrapping_parcel_stars")
    }

    @Test
    fun theSheetOpensItAQuarterOfASecondInOnItsSpringAHairPastOpenThenSettled() {
        compose.mainClock.autoAdvance = false
        lateinit var open: State<Float>
        theme { open = rememberLinkOpening(opens = true) }
        compose.mainClock.advanceTimeBy(240)
        assertEquals(0f, open.value)
        // 0.45 s into `.spring(duration: 0.9, bounce: 0.2)`: 1 - e^(-ζω₀t)(cos ω_d t + ζω₀/ω_d sin ω_d t) = 0.92.
        compose.mainClock.advanceTimeBy(460)
        assertEquals(0.92f, open.value, 0.04f)
        // Its peak, half a period of ω_d in: 1.015.
        compose.mainClock.advanceTimeBy(300)
        assertTrue("${open.value}", open.value > 1.005f)
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(1f, open.value)
    }

    @Test
    fun underReduceMotionTheSheetShowsItOpenAtOnceAndAClosedOneStaysClosed() {
        compose.mainClock.autoAdvance = false
        lateinit var open: State<Float>
        lateinit var closed: State<Float>
        theme(reduceMotion = true) {
            open = rememberLinkOpening(opens = true)
            closed = rememberLinkOpening(opens = false)
        }
        compose.mainClock.advanceTimeByFrame()
        compose.waitForIdle()
        assertEquals(1f, open.value)
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(0f, closed.value)
    }

    private companion object {
        val OPENING = listOf(0.12, 0.3, 0.55, 0.8)
    }
}
