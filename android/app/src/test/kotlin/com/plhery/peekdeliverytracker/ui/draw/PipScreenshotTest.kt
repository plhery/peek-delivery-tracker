package com.plhery.peekdeliverytracker.ui.draw

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.PipArtwork
import com.plhery.peekdeliverytracker.core.PipLabel
import com.plhery.peekdeliverytracker.core.PipMood
import com.plhery.peekdeliverytracker.core.PipPlacement
import com.plhery.peekdeliverytracker.core.PipSpot
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.core.Rect
import com.plhery.peekdeliverytracker.core.Size
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Pip in each mood and as a sticker, his box opening on its own clock, and the face at full size against their
 * recorded baselines; his blink and his bob as they change what is drawn; and nothing of him said to accessibility.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w600dp-h844dp-mdpi")
class PipScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    private val identities by lazy { runBlocking { CarrierIdentities.load(CarrierCatalog.shared) } }

    /** A card's ink and surface, light and dark. */
    private fun card(dark: Boolean) = if (dark) Color(0xFFC9D6BD) to Color(0xFF263022) else Color(0xFF55654B) to Color(0xFFF4F6F1)

    private fun theme(dark: Boolean = false, reduceMotion: () -> Boolean, content: @Composable () -> Unit) = compose.setContent {
        PeekTheme(dark = dark, highContrast = false, reduceMotion = reduceMotion(), reduceTransparency = false, carrierIdentities = identities, content = content)
    }

    private fun change(write: () -> Unit) = compose.runOnIdle {
        write()
        Snapshot.sendApplyNotifications()
    }

    @Test
    fun posesLight() = poses(dark = false)

    @Test
    fun posesDark() = poses(dark = true)

    /** Each mood still, looking toward the dot on either side and from below; then the stickers, labelled and happy. */
    private fun poses(dark: Boolean) {
        val (ink, surface) = card(dark)
        val dhl = identities.of(CarrierId("dhl"), AppLanguage.En)
        val ups = identities.of(CarrierId("ups"), AppLanguage.En)
        theme(dark, reduceMotion = { true }) {
            Column(Modifier.testTag(SHOT).background(surface).padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    val poses = listOf(
                        Triple(PipMood.Look, 1, false), Triple(PipMood.Look, -1, false), Triple(PipMood.Look, 0, true),
                        Triple(PipMood.Eager, 1, false), Triple(PipMood.Eager, -1, false), Triple(PipMood.Wait, 1, false),
                        Triple(PipMood.Worry, -1, false), Triple(PipMood.Joy, 1, false),
                    )
                    for ((mood, side, below) in poses) InkPip(mood, side, below, ink, surface, Modifier.width(64.dp))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Bottom) {
                    SmallPip(Modifier.width(84.dp))
                    SmallPip(Modifier.width(84.dp), label = PipLabel(dhl, "PK 0000 0000 0001"))
                    SmallPip(Modifier.width(84.dp), label = PipLabel(ups), happy = 1.0)
                    SmallPip(Modifier.width(84.dp), happy = 0.5)
                    SmallPip(Modifier.width(34.dp))
                }
            }
        }
        compose.screenshot("pip_poses_" + if (dark) "dark" else "light")
    }

    @Test
    fun opening() {
        // Five Pips told of the arrival 0.23 s apart, the first 1.15 s ago: his box opens, then he jumps.
        compose.mainClock.autoAdvance = false
        val (ink, surface) = card(dark = false)
        val moods = List(5) { mutableStateOf(PipMood.Look) }
        theme(reduceMotion = { false }) {
            Row(Modifier.testTag(SHOT).background(surface).padding(8.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                for (mood in moods) InkPip(mood.value, 1, false, ink, surface, Modifier.width(96.dp))
            }
        }
        compose.mainClock.advanceTimeBy(2_000)
        for (mood in moods) {
            change { mood.value = PipMood.Joy }
            compose.mainClock.advanceTimeBy(230)
        }
        compose.screenshot("pip_opening")
    }

    @Test
    fun face() {
        // The kraft face full size, then with a gift's ribbon over its mouth, as the sign-in's gift draws them on its
        // kraft.
        theme(reduceMotion = { true }) {
            Row(Modifier.testTag(SHOT).background(Color(0xFFD1AE85))) {
                KraftPipFace(happy = 0.0)
                Box {
                    KraftPipFace(happy = 1.0, smile = 0.0)
                    PipCanvas(Modifier.size(300.dp, 310.dp)) { PipArtwork.ribbon(it) }
                }
            }
        }
        compose.screenshot("pip_face")
    }

    @Test
    fun hisEyesBlinkUnlessMotionIsReduced() {
        compose.mainClock.autoAdvance = false
        val reduced = mutableStateOf(false)
        theme(reduceMotion = { reduced.value }) {
            Box(Modifier.testTag(SHOT)) { KraftPipFace(happy = 0.0) }
        }
        compose.mainClock.advanceTimeBy(1_000)
        val open = capture()
        // About 4.33 s on its clock, from its first frame: the eyes are closing.
        compose.mainClock.advanceTimeBy(3_350)
        assertFalse(open.sameAs(capture()))
        // Open again once the blink is over.
        compose.mainClock.advanceTimeBy(1_000)
        assertTrue(open.sameAs(capture()))
        // About 8.93 s, inside the second blink, they stay open while motion is reduced.
        change { reduced.value = true }
        compose.mainClock.advanceTimeBy(3_600)
        assertTrue(open.sameAs(capture()))
    }

    @Test
    fun eagerHeBobsUnlessMotionIsReduced() {
        compose.mainClock.autoAdvance = false
        val (ink, surface) = card(dark = false)
        val reduced = mutableStateOf(false)
        theme(reduceMotion = { reduced.value }) {
            Box(Modifier.testTag(SHOT)) { InkPip(PipMood.Eager, 1, false, ink, surface, Modifier.width(120.dp)) }
        }
        compose.mainClock.advanceTimeBy(2_000)
        // His bob keeps the wall's time: two frames 0.2 s apart can match where it turns, never three.
        val bob = List(3) { capture().also { compose.mainClock.advanceTimeBy(200) } }
        assertFalse(bob[0].sameAs(bob[1]) && bob[1].sameAs(bob[2]))
        change { reduced.value = true }
        compose.mainClock.advanceTimeBy(100)
        val still = capture()
        compose.mainClock.advanceTimeBy(300)
        assertTrue(still.sameAs(capture()))
    }

    @Test
    fun nothingOfHimIsSaidToAccessibility() {
        val (ink, surface) = card(dark = false)
        val place = PipPlacement(
            origin = Point(40.0, 30.0), width = 48.0, mood = PipMood.Look, side = 1, below = false,
            box = Rect(40.0, 30.0, 48.0, 49.6), spot = PipSpot(Size(-60.0, -20.0), 48.0, 1, false),
        )
        theme(reduceMotion = { true }) {
            Box(Modifier.testTag(SHOT).size(200.dp)) {
                Column {
                    KraftPipFace(happy = 0.0, Modifier.size(30.dp))
                    SmallPip(Modifier.width(34.dp), blinks = true)
                    InkPip(PipMood.Joy, 1, false, ink, surface, Modifier.width(48.dp))
                    PipCanvas(Modifier.size(30.dp)) { PipArtwork.ribbon(it) }
                }
                MapPip(place, dot = Point(100.0, 50.0), ink = ink, surface = surface)
            }
        }
        // Not a word, a role or an action anywhere, only the test's own tag.
        val nodes = compose.onAllNodes(SemanticsMatcher("any node") { true }, useUnmergedTree = true).fetchSemanticsNodes()
        assertTrue(nodes.isNotEmpty())
        for (node in nodes) {
            val said = node.config.map { it.key }.filter { it != SemanticsProperties.TestTag }
            assertTrue("$said", said.isEmpty())
        }
    }

    /** The shot as the view draws it now, in software: a hardware capture can come back a frame behind, which a
     * test of what changes from one frame to the next can't have. */
    private fun capture(): Bitmap {
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.waitForIdle()
        val bounds = compose.onNodeWithTag(SHOT).fetchSemanticsNode().boundsInRoot
        val whole = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(Canvas(whole)) }
        return Bitmap.createBitmap(whole, bounds.left.toInt(), bounds.top.toInt(), bounds.width.toInt(), bounds.height.toInt())
    }

}
