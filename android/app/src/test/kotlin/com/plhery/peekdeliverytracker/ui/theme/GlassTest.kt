package com.plhery.peekdeliverytracker.ui.theme

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * iOS 26's Liquid Glass: the face's arithmetic as SwiftUI sets CoreAnimation's glass, and the glass drawn at the
 * iPhone's 3× against what its screenshots measure.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w460dp-h844dp-xxhdpi")
class GlassTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun theFaceWorkedByHand() {
        // Light: luminance onto 0.4 + 0.56 L, saturation × 1.2, white over it at 18 %, lifted 0.035; held to 0.94
        // filled and lifted alike, 0.9858.
        val light = GlassFace.Standard.recipe(dark = false)
        assertGrey(0.543f, light.over(Color.Black, 44f))
        assertGrey(0.9858f, light.over(Color.White, 44f))
        // Pure red, luminance 0.2126: red held to the cap, green and blue 0.543 − 0.5248 × 0.2126.
        val red = light.over(Color.Red, 44f)
        assertEquals(0.9858f, red.red, EIGHT_BITS)
        assertEquals(0.4314f, red.green, EIGHT_BITS)
        assertEquals(0.4314f, red.blue, EIGHT_BITS)
        // Dark: 0.08 + 1.045 L, darkened 0.035, held to 0.6 on a small glass and to 0.36 from 120 points.
        val dark = GlassFace.Standard.recipe(dark = true)
        assertGrey(0.045f, dark.over(Color.Black, 44f))
        assertGrey(0.565f, dark.over(Color.White, 44f))
        assertGrey(0.325f, dark.over(Color.White, 120f))
        // Increase Contrast: white at 45 % over 0.5 + 0.46 L in light; grey 0.12 at 40 % over 0.1 + 0.2 L in dark.
        assertGrey(0.76f, GlassFace.IncreasedContrast.recipe(dark = false).over(Color.Black, 44f))
        assertGrey(0.073f, GlassFace.IncreasedContrast.recipe(dark = true).over(Color.Black, 44f))
        // Reduce Transparency: all but opaque, white in light and 0.057 in dark whatever lies behind.
        assertGrey(1f, GlassFace.ReducedTransparency.recipe(dark = false).over(Color.Black, 44f))
        assertGrey(0.057f, GlassFace.ReducedTransparency.recipe(dark = true).over(Color.White, 44f))
        assertGrey(0.057f, GlassFace.ReducedTransparency.recipe(dark = true).over(Color.Black, 44f))
    }

    @Test
    fun theLensAndTheShadowGrowWithTheGlass() {
        // As SwiftUI sets them: a quarter and a half of the shorter side, up to 20 and 60.
        assertEquals(GlassLens(11f, 22f), GlassLens.of(44f))
        assertEquals(GlassLens(15f, 30f), GlassLens.of(60f))
        assertEquals(GlassLens(20f, 60f), GlassLens.of(170f))
        // A 44-point circle's band middle, 16.5 from its centre, looks 11 further out.
        assertEquals(1 + 11 / 16.5f, GlassLens.of(44f).reach(22f), 1e-5f)
        // Dark's luminance ceiling, as SwiftUI sets it for 44 to 170 points.
        val dark = GlassFace.Standard.recipe(dark = true)
        for ((size, ceiling) in listOf(44f to 0.6f, 64f to 0.6f, 80f to 0.504f, 90f to 0.444f, 100f to 0.384f, 170f to 0.36f)) {
            assertEquals("$size", ceiling, dark.maxLuma(size), 1e-5f)
        }
        assertEquals(0.94f, GlassFace.Standard.recipe(dark = false).maxLuma(170f), 0f)
        // The shadow, from the circle's to the bar's.
        val small = glassShadow(44f)
        assertEquals(0.02f, small.color.alpha, 1e-3f)
        assertEquals(6 * sqrt(3f), small.radius.value, 1e-3f)
        assertEquals(3f, small.offset.y.value, 1e-3f)
        val large = glassShadow(170f)
        assertEquals(0.09f, large.color.alpha, 1e-3f)
        assertEquals(12 * sqrt(3f), large.radius.value, 1e-3f)
        assertEquals(6f, large.spread.value, 1e-3f)
        assertEquals(10f, large.offset.y.value, 1e-3f)
    }

    @Test
    fun theCircleAsTheIPhoneDrawsIt() {
        // The map's 44-point close button over what lies behind it in the iPhone's screenshot, at 3×: pixels 72 to
        // 204 each way.
        val bitmap = capture(backdrop = BEHIND_THE_BUTTON) { GlassSurface(CircleShape, Modifier.offset(24.dp, 24.dp).size(44.dp)) {} }
        // The face: the page lifted evenly, (243, 246, 239) on the iPhone.
        assertRgb(listOf(243, 246, 239), bitmap.getPixel(138, 138), 2)
        // The dark line outside the edge, 2 pixels: black at about 26 % down the sides (151 and 191 on the iPhone)
        // and 10 % at the top (208 and 218).
        assertEquals(171.0, (green(bitmap, 70, 138) + green(bitmap, 71, 138)) / 2.0, 8.0)
        assertEquals(213.0, (green(bitmap, 138, 70) + green(bitmap, 138, 71)) / 2.0, 6.0)
        // The light line inside it at the top, 255, 255 and 252 on the iPhone; none down the sides, which are shaded.
        assertTrue(green(bitmap, 138, 72) >= 254)
        assertTrue(green(bitmap, 138, 73) >= 254)
        assertEquals(252.0, green(bitmap, 138, 74).toDouble(), 3.0)
        // Shaded from above: 4 levels down the sides and across the top half (241 against 245 on the iPhone), none in
        // the middle of the bottom half (246).
        val centre = green(bitmap, 138, 138)
        val side = (75..84).map { green(bitmap, it, 138) }.average()
        assertEquals(4.0, centre - side, 1.5)
        assertEquals(5.0, (green(bitmap, 138, 174) - green(bitmap, 138, 102)).toDouble(), 2.0)
        // The shadow under it: 3 levels at most, gone 10 points down.
        val behind = (BEHIND_THE_BUTTON.green * 255).toDouble()
        assertEquals(3.0, behind - green(bitmap, 138, 209), 1.5)
        assertTrue(behind - green(bitmap, 138, 240) <= 1)
    }

    @Test
    fun theBarAsTheIPhoneDrawsIt() {
        // The map's summary bar, 416 × 170 points, with a dark stripe 25 to 35 points above its top: the lens shows
        // it inside the top edge, where the blur alone doesn't reach.
        val bitmap = capture(backdrop = BEHIND_THE_BUTTON, stripe = true) {
            GlassSurface(ContinuousRoundedShape(24.dp), Modifier.offset(22.dp, 60.dp).size(416.dp, 170.dp)) {}
        }
        val behind = (BEHIND_THE_BUTTON.green * 255).toDouble()
        // The face away from the edge is the circle's.
        assertRgb(listOf(243, 246, 239), bitmap.getPixel(690, 435), 2)
        val inside = green(bitmap, 690, 180 + 9)
        assertTrue("$inside", green(bitmap, 690, 435) - inside >= 30)
        // The shadow under it: 18 levels just below, on the iPhone.
        assertEquals(18.0, behind - green(bitmap, 690, 690 + 5), 4.0)
    }

    @Test
    fun inDarkLargerGlassIsDarker() {
        // Over white, a 44-point glass shows dark's ceiling 0.6, a 120-point one 0.36, each darkened 0.035.
        val bitmap = capture(dark = true, backdrop = Color.White) {
            GlassSurface(CircleShape, Modifier.offset(24.dp, 24.dp).size(44.dp)) {}
            GlassSurface(ContinuousRoundedShape(24.dp), Modifier.offset(100.dp, 24.dp).size(120.dp)) {}
        }
        assertEquals(0.565 * 255, green(bitmap, 138, 138).toDouble(), 2.0)
        assertEquals(0.325 * 255, green(bitmap, 480, 252).toDouble(), 2.0)
    }

    @Test
    fun withoutTransparencyTheGlassIsSolid() {
        // Over black: Reduce Transparency draws the face over the page, white in light; with no backdrop the standard
        // face is drawn over the page, as blurred over it; Increase Contrast blurs the black.
        val bitmap = capture(backdrop = Color.Black) {
            Row(Modifier.offset(24.dp, 24.dp)) {
                for (case in 0..2) {
                    PeekTheme(dark = false, highContrast = case == 2, reduceMotion = true, reduceTransparency = case == 0, carrierIdentities = null) {
                        GlassSurface(ContinuousRoundedShape(16.dp), Modifier.padding(end = 16.dp).size(60.dp), LocalMaterialBackdrop.current.takeIf { case != 1 }) {}
                    }
                }
            }
        }
        val page = PeekColors(dark = false).background
        val expected = listOf(
            GlassFace.ReducedTransparency.recipe(dark = false).over(page, 60f),
            GlassFace.Standard.recipe(dark = false).over(page, 60f),
            GlassFace.IncreasedContrast.recipe(dark = false).over(Color.Black, 60f),
        )
        for ((case, color) in expected.withIndex()) {
            val pixel = bitmap.getPixel((24 + case * 76 + 30) * 3, (24 + 30) * 3)
            assertRgb(listOf(color.red, color.green, color.blue).map { (it * 255).roundToInt() }, pixel, 1)
        }
    }

    private fun assertGrey(expected: Float, color: Color) {
        assertEquals(expected, color.red, EIGHT_BITS)
        assertEquals(expected, color.green, EIGHT_BITS)
        assertEquals(expected, color.blue, EIGHT_BITS)
    }

    private fun assertRgb(expected: List<Int>, pixel: Int, tolerance: Int) {
        val actual = listOf((pixel shr 16) and 0xFF, (pixel shr 8) and 0xFF, pixel and 0xFF)
        for (channel in 0..2) assertTrue("$actual, not $expected", abs(actual[channel] - expected[channel]) <= tolerance)
    }

    private fun green(bitmap: Bitmap, x: Int, y: Int) = (bitmap.getPixel(x, y) shr 8) and 0xFF

    /** The map's land behind the close button in the iPhone's screenshot. */
    private val BEHIND_THE_BUTTON = Color(233, 236, 229)

    /** An sRGB Color holds eight bits a channel; the recipes' figures are rounded to four places. */
    private val EIGHT_BITS = 0.5f / 255 + 1e-4f

    /**
     * The glass [content] over a plain backdrop, the page's background unless given, with a dark stripe 25 to 35
     * points above y = 60 if asked, captured at 3×.
     */
    private fun capture(dark: Boolean = false, backdrop: Color? = null, stripe: Boolean = false, content: @Composable () -> Unit): Bitmap {
        compose.setContent {
            PeekTheme(dark = dark, highContrast = false, reduceMotion = true, reduceTransparency = false, carrierIdentities = null) {
                val source = rememberMaterialBackdrop()
                Box(Modifier.testTag(SHOT).size(460.dp, 300.dp)) {
                    Box(Modifier.fillMaxSize().materialBackdrop(source).background(backdrop ?: PeekTheme.colors.background)) {
                        if (stripe) Box(Modifier.offset(0.dp, 25.dp).size(460.dp, 10.dp).background(Color.Black))
                    }
                    CompositionLocalProvider(LocalMaterialBackdrop provides source) { content() }
                }
            }
        }
        // Robolectric may keep a drawing from before the content: draw the window again first.
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.runOnUiThread { view.rootView.invalidate() }
        compose.waitForIdle()
        return compose.onNodeWithTag(SHOT).captureToImage().asAndroidBitmap()
    }

    @Test
    @Config(qualifiers = PHONE)
    fun sheetLight() = sheet(dark = false)

    @Test
    @Config(qualifiers = PHONE)
    fun sheetDark() = sheet(dark = true)

    /** A circle, a capsule and two rounded rectangles of glass over a light, a dark and a busy backdrop. */
    private fun sheet(dark: Boolean) {
        compose.setContent {
            PeekTheme(dark = dark, highContrast = false, reduceMotion = true, reduceTransparency = false, carrierIdentities = null) {
                Row(Modifier.testTag(SHOT).background(PeekTheme.colors.background).padding(6.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Panel { drawRect(PeekColors(dark = false).background) }
                    Panel { drawRect(PeekColors(dark = true).background) }
                    Panel {
                        val colors = PeekColors(dark = false)
                        val bands = listOf(colors.accent, colors.paper, colors.warning, colors.ink, colors.accentBright, colors.cream)
                        rotate(-30f) {
                            for (i in -24..36) {
                                drawRect(bands[(i + 24) % bands.size], Offset(i * 26f - 120f, -200f), size.copy(width = 13f, height = size.height + 400f))
                            }
                        }
                    }
                }
            }
        }
        compose.screenshot("glass_" + if (dark) "dark" else "light")
    }

    @Composable
    private fun Panel(backdrop: DrawScope.() -> Unit) {
        val source = rememberMaterialBackdrop()
        Box(Modifier.size(122.dp, 330.dp).clipToBounds()) {
            // The backdrop runs on past the panel, as the map runs on past the glass on the phone, for the lens to bend in.
            Box(Modifier.requiredSize(122.dp + SPILL * 2, 330.dp + SPILL * 2).materialBackdrop(source)) {
                Canvas(Modifier.fillMaxSize(), onDraw = backdrop)
                Column(Modifier.padding(SPILL + 6.dp), verticalArrangement = Arrangement.spacedBy(38.dp)) {
                    repeat(7) { Text("Peek · on its way", style = PeekTheme.type.footnote, color = Color(0xFF7A7A7A)) }
                }
            }
            CompositionLocalProvider(LocalMaterialBackdrop provides source) {
                Column(Modifier.padding(11.dp), verticalArrangement = Arrangement.spacedBy(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Glass(CircleShape, 44.dp, 44.dp)
                        Glass(CircleShape, 42.dp, 42.dp)
                    }
                    Glass(CapsuleShape, 100.dp, 44.dp)
                    Glass(ContinuousRoundedShape(Radii.inlineToast), 100.dp, 56.dp)
                    Glass(ContinuousRoundedShape(24.dp), 100.dp, 100.dp)
                }
            }
        }
    }

    /** How far a panel's backdrop runs on past it: the reach of the largest glass's lens and its blur. */
    private val SPILL = 44.dp

    @Composable
    private fun Glass(shape: Shape, width: Dp, height: Dp) =
        GlassSurface(shape, Modifier.size(width, height)) {}
}
