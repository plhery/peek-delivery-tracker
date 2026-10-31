package com.plhery.peekdeliverytracker.ui.deliveries

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.theme.GlassFace
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.PeekColors
import kotlin.math.abs
import kotlin.math.roundToInt
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * What decides the material of the glass the menus and alerts stand on over the window, drawn as the shell's layers:
 * through [GlassOverlayLayer] it blurs the tab under it, as the iPhone's alerts show the screen through theirs; under
 * Reduce Transparency it is the solid face over the page's background. Either way it shows what lies behind dimmed, as
 * the alert's dim leaves it. A layer that hands its glass no backdrop ([OverlayLayer] alone) draws it solid: the
 * opaque alert the device showed.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class OverlayGlassTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun overTheTabTheGlassShowsItDimmed() = assertFaces(dark = false, reduceTransparency = false, glass = true)

    @Test
    fun overTheTabTheGlassShowsItDimmedInDark() = assertFaces(dark = true, reduceTransparency = false, glass = true)

    @Test
    fun underReduceTransparencyTheGlassIsSolid() = assertFaces(dark = false, reduceTransparency = true, glass = true)

    @Test
    fun underReduceTransparencyTheGlassIsSolidInDark() = assertFaces(dark = true, reduceTransparency = true, glass = true)

    @Test
    fun aLayerThatHandsItsGlassNoBackdropDrawsItSolid() = assertFaces(dark = false, reduceTransparency = false, glass = false)

    /**
     * A 300-point glass dimmed as an alert is, shown as a layer of the shell over a tab whose top half is red and
     * bottom half blue: its face over each half, 70 points from the halves' edge and from the glass's, out of the
     * blur's reach and the lens's band, is the face of that half dimmed where it blurs the tab, and otherwise the face
     * of the page's background dimmed, the same over both.
     */
    private fun assertFaces(dark: Boolean, reduceTransparency: Boolean, glass: Boolean) {
        val top = if (dark) Color(0.5f, 0.15f, 0.1f) else Color(0.9f, 0.3f, 0.2f)
        val bottom = if (dark) Color(0.1f, 0.2f, 0.5f) else Color(0.2f, 0.4f, 0.9f)
        compose.setContent {
            RootHost(dark = dark, reduceTransparency = reduceTransparency, deliveries = {
                Column(Modifier.fillMaxSize()) {
                    Box(Modifier.fillMaxWidth().weight(1f).background(top))
                    Box(Modifier.fillMaxWidth().weight(1f).background(bottom))
                }
                val host = LocalTabOverlay.current!!
                val layer = @Composable {
                    Box(Modifier.fillMaxSize()) {
                        GlassSurface(ContinuousRoundedShape(34.dp), Modifier.align(Alignment.Center).size(300.dp), dim = DIM_ALPHA) {}
                    }
                }
                if (glass) GlassOverlayLayer(host, "glass", layer) else OverlayLayer(host, "glass", layer)
            })
        }
        val bitmap = capture()
        val recipe = GlassFace.of(highContrast = false, reduceTransparency = reduceTransparency).recipe(dark)
        val page = PeekColors(dark = dark).background
        // The window is 844 points tall at 2×: the halves meet at 422, the glass spans 272 to 572.
        for ((y, half) in listOf(352 to top, 492 to bottom)) {
            val behind = if (glass && !reduceTransparency) half else page
            assertRgb("at $y", recipe.over(dimmed(behind), SIDE), bitmap.getPixel(195 * 2, y * 2))
        }
    }

    /** [color] under black at the dim's strength. */
    private fun dimmed(color: Color) = Color(color.red * (1 - DIM_ALPHA), color.green * (1 - DIM_ALPHA), color.blue * (1 - DIM_ALPHA))

    private fun assertRgb(message: String, expected: Color, pixel: Int) {
        val actual = listOf((pixel shr 16) and 0xFF, (pixel shr 8) and 0xFF, pixel and 0xFF)
        val wanted = listOf(expected.red, expected.green, expected.blue).map { (it * 255).roundToInt() }
        for (channel in 0..2) assertTrue("$message: $actual, not $wanted", abs(actual[channel] - wanted[channel]) <= 2)
    }

    /**
     * The tab and its layer, captured once two captures in a row agree: Robolectric may keep a drawing from before the
     * content, and the glass draws only in hardware.
     */
    private fun capture(): Bitmap {
        compose.waitForIdle()
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        var previous: Bitmap? = null
        repeat(6) {
            compose.runOnUiThread {
                view.rootView.invalidate()
                view.invalidate()
            }
            compose.waitForIdle()
            val bitmap = compose.onRoot().captureToImage().asAndroidBitmap()
            if (previous?.sameAs(bitmap) == true) return bitmap
            previous = bitmap
        }
        error("Two captures in a row never agreed")
    }

    private companion object {
        /** The dim under the alerts, black at a fifth. */
        const val DIM_ALPHA = 0.2f

        /** The glass's shorter side, in points. */
        const val SIDE = 300f
    }
}
