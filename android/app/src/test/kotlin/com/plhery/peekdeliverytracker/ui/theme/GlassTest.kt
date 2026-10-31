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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
import org.junit.Assert.assertSame
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
        // Light: luminance onto 0.4 + 0.56 L, saturation × 1.2, white over it at 18 %, lifted 0.035; its luminance held
        // to 0.94 filled and lifted alike, 0.9858.
        val light = GlassFace.Standard.recipe(dark = false)
        assertGrey(0.543f, light.over(Color.Black, 44f))
        assertGrey(0.9858f, light.over(Color.White, 44f))
        // Pure red, luminance 0.2126, well under the ceiling: red runs past 1 and is held there, green and blue
        // 0.543 − 0.5248 × 0.2126.
        val red = light.over(Color.Red, 44f)
        assertEquals(1f, red.red, EIGHT_BITS)
        assertEquals(0.4314f, red.green, EIGHT_BITS)
        assertEquals(0.4314f, red.blue, EIGHT_BITS)
        // Dark: 0.08 + 1.045 L, darkened 0.035, held to 0.6 on a small glass and to 0.36 from 120 points.
        val dark = GlassFace.Standard.recipe(dark = true)
        assertGrey(0.045f, dark.over(Color.Black, 44f))
        assertGrey(0.565f, dark.over(Color.White, 44f))
        assertGrey(0.325f, dark.over(Color.White, 120f))
        // Yellow (1, 0.8, 0) on a 100-point glass, held to 0.384: luminance 0.7848 maps to 0.9001, saturation × 1.3
        // gives (1.1799, 0.9199, −0.1201), 0.5161 over the ceiling comes off each channel, then 0.035: still orange.
        val yellow = dark.over(Color(1f, 0.8f, 0f), 100f)
        assertEquals(0.6288f, yellow.red, EIGHT_BITS)
        assertEquals(0.3688f, yellow.green, EIGHT_BITS)
        assertEquals(0f, yellow.blue, EIGHT_BITS)
        // Increase Contrast: white at 45 % over 0.5 + 0.46 L in light; grey 0.12 at 40 % over 0.1 + 0.2 L in dark.
        assertGrey(0.76f, GlassFace.IncreasedContrast.recipe(dark = false).over(Color.Black, 44f))
        assertGrey(0.073f, GlassFace.IncreasedContrast.recipe(dark = true).over(Color.Black, 44f))
        // Reduce Transparency: all but opaque, white in light and 0.057 in dark whatever lies behind.
        assertGrey(1f, GlassFace.ReducedTransparency.recipe(dark = false).over(Color.Black, 44f))
        assertGrey(0.057f, GlassFace.ReducedTransparency.recipe(dark = true).over(Color.White, 44f))
        assertGrey(0.057f, GlassFace.ReducedTransparency.recipe(dark = true).over(Color.Black, 44f))
    }

    @Test
    fun dimmedTheFaceIsTheFaceOfWhatLiesBehindDimmed() {
        // Black at a fifth under the glass, over colours that run past 1 and over the ceiling once filtered: the
        // dimmed face is the face of each colour at 80 %, held to 0…1 and to the ceiling only then, in every face. A
        // Color holds eight bits a channel, so the colour at 80 % is a step off at most.
        val colours = listOf(Color.White, Color.Red, YELLOW, Color(0.2f, 0.4f, 0.9f), PAGE)
        for (face in GlassFace.entries) {
            for (dark in listOf(false, true)) {
                val recipe = face.recipe(dark)
                for (colour in colours) {
                    val dimmed = recipe.dimmed(0.2f).over(colour, 100f)
                    val expected = recipe.over(Color(colour.red * 0.8f, colour.green * 0.8f, colour.blue * 0.8f), 100f)
                    assertEquals("$face $dark $colour", expected.red, dimmed.red, ONE_STEP)
                    assertEquals("$face $dark $colour", expected.green, dimmed.green, ONE_STEP)
                    assertEquals("$face $dark $colour", expected.blue, dimmed.blue, ONE_STEP)
                }
            }
        }
        assertSame(GlassFace.Standard.recipe(dark = false), GlassFace.Standard.recipe(dark = false).dimmed(0f))
    }

    @Test
    fun theLensAndTheShadowGrowWithTheGlass() {
        // As SwiftUI sets them: a quarter and a half of the shorter side, up to 20 and 60.
        assertEquals(GlassLens(11f, 22f), GlassLens.of(44f))
        assertEquals(GlassLens(15f, 30f), GlassLens.of(60f))
        assertEquals(GlassLens(20f, 60f), GlassLens.of(170f))
        // A 44-point circle's edge looks at its centre, 22 in; 5.5 points in, 11 further in; from 11 in, nowhere else.
        val circle = GlassLens.of(44f)
        assertEquals(22f, circle.look(0f), 1e-5f)
        assertEquals(11f, circle.look(5.5f), 1e-5f)
        assertEquals(0f, circle.look(11f), 1e-5f)
        assertEquals(0f, circle.look(30f), 1e-5f)
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
        // The light line inside it at the top, 255, 255 and 252 on the iPhone, wherever the edge falls between
        // pixels; none down the sides, which are shaded.
        val line = (71..75).map { green(bitmap, 138, it) }.sortedDescending()
        assertTrue("$line", line[1] >= 254)
        assertEquals("$line", 252.0, line[2].toDouble(), 3.0)
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
    fun theBarAsTheIPhoneDrawsIt() = bar()

    /** The bar where Android 12 draws the lens along each side, without a shader. */
    @Test
    @Config(sdk = [31])
    fun theBarOnAndroid12() = bar()

    /**
     * The map's summary bar, 416 × 170 points from y = 60, x = 22 to 438, with dark stripes over its left half: one
     * 1 to 3 points above its top edge, which the glass can't see, and one 25 to 35 points inside, which the band
     * along the top edge, looking 60 points in at the edge and nothing 20 points in, shows mirrored 12.5 to 17.5 points
     * in.
     */
    private fun bar() {
        val bitmap = capture(backdrop = BEHIND_THE_BUTTON, behind = {
            for ((top, bottom) in listOf(57f to 59f, 85f to 95f)) {
                drawRect(Color.Black, Offset(0f, top.dp.toPx()), Size(230.dp.toPx(), (bottom - top).dp.toPx()))
            }
        }) {
            GlassSurface(ContinuousRoundedShape(24.dp), Modifier.offset(22.dp, 60.dp).size(416.dp, 170.dp)) {}
        }
        val behind = (BEHIND_THE_BUTTON.green * 255).toDouble()
        // The face away from the edge is the circle's.
        assertRgb(listOf(243, 246, 239), bitmap.getPixel(1080, 435), 2)
        // 3 points in, the band is the same over the stripe outside as away from it, 160 points to the right.
        assertTrue(abs(green(bitmap, 300, 189) - green(bitmap, 1080, 189)) <= 1)
        // 15 points in, it shows the inner stripe as dark as the stripe itself shows 30 points in, where the band
        // doesn't reach; 20 points in, at the band's inner line, it shows the face 20 points in, lighter.
        val mirrored = green(bitmap, 300, 225)
        val seen = green(bitmap, 300, 270)
        assertTrue("$mirrored, $seen", green(bitmap, 1080, 225) - mirrored >= 40)
        assertEquals(seen.toDouble(), mirrored.toDouble(), 8.0)
        assertTrue(green(bitmap, 300, 240) - mirrored >= 20)
        // The shadow under it: 18 levels just below, on the iPhone.
        assertEquals(18.0, behind - green(bitmap, 1080, 690 + 5), 4.0)
    }

    @Test
    fun theTabBarAsTheIPhoneDrawsIt() {
        // The list's tab bar on the iPhone, unused in fitting the glass: a 273.7 × 62-point capsule from (60, 80) here
        // and (250, 2619) on the iPhone's 3× screenshot. Behind it the page, the card 6.3 points below its top edge,
        // the card above 4 points over it, text 1.7 to 5.3 points past its right end and on its bottom edge.
        val bitmap = capture(backdrop = PAGE, behind = {
            drawRect(BLUE_CARD, Offset.Zero, Size(size.width, 76.dp.toPx()))
            drawRect(GREEN_CARD, Offset(0f, 86.33.dp.toPx()), Size(size.width, size.height))
            drawRect(Color(0, 75, 48), Offset(335.33.dp.toPx(), 108.dp.toPx()), Size(3.6.dp.toPx(), 6.dp.toPx()))
            drawRect(Color.Black, Offset(130.dp.toPx(), 142.dp.toPx()), Size(30.dp.toPx(), 2.3.dp.toPx()))
        }) {
            GlassSurface(CapsuleShape, Modifier.offset(60.dp, 80.dp).size(273.67.dp, 62.dp)) {}
        }
        // The face over the card, (229, 251, 243) at (800, 2700) on the iPhone.
        assertRgb(listOf(229, 251, 243), bitmap.getPixel(730, 321), 3)
        // Just inside the top edge, over the page, the band shows the card further in, as the iPhone's does: blue
        // above red by 13 or 14 from 1 to 4 points in (2622 to 2631), where the face over the page would show −3.
        for (y in 243..252) {
            val pixel = bitmap.getPixel(730, y)
            assertTrue("$y", (pixel and 0xFF) - ((pixel shr 16) and 0xFF) >= 10)
        }
        // At the right end, the band beside the text is the band at the left end, where there is none, and the
        // iPhone's (223, 245, 237) 7 points in, shaded 6 below its face; here 4 below a face 2 to 3 greener.
        assertRgb(listOf(223, 245, 237), bitmap.getPixel(980, 333), 5)
        assertRgb(rgb(bitmap.getPixel(201, 333)), bitmap.getPixel(980, 333), 2)
        // 1 to 4 points above the bottom edge, the band over the text is the band away from it, (230, 250, 243) on
        // the iPhone.
        for (y in 414..422) assertRgb(rgb(bitmap.getPixel(1181 - 435, y)), bitmap.getPixel(435, y), 2)
        assertRgb(listOf(230, 250, 243), bitmap.getPixel(530, 418), 3)
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
    fun inDarkTheCeilingKeepsColour() {
        // A 100-point glass over yellow, as CoreAnimation's arithmetic gives it, worked through here.
        val bitmap = capture(dark = true, backdrop = YELLOW) {
            GlassSurface(ContinuousRoundedShape(24.dp), Modifier.offset(24.dp, 24.dp).size(100.dp)) {}
        }
        val expected = face(YELLOW, white = 1.125f, black = 0.08f, saturation = 1.3f, fill = 0.0125f, fillAlpha = 0f, lift = -0.035f, maxLuma = 0.384f)
        assertRgb(expected, bitmap.getPixel(222, 222), 2)
    }

    /** Android 12 holds each channel to the ceiling instead: yellow turns olive. */
    @Test
    @Config(sdk = [31])
    fun inDarkOnAndroid12TheCeilingHoldsEachChannel() {
        val bitmap = capture(dark = true, backdrop = YELLOW) {
            GlassSurface(ContinuousRoundedShape(24.dp), Modifier.offset(24.dp, 24.dp).size(100.dp)) {}
        }
        val ceiling = ((0.384f - 0.035f) * 255).roundToInt()
        assertRgb(listOf(ceiling, ceiling, 0), bitmap.getPixel(222, 222), 2)
    }

    @Test
    fun theLightLineAddsAsMuchInDark() {
        // A mid grey keeps the line short of white in light too: CoreAnimation sets its amount the same in both.
        val bitmap = capture(backdrop = Color(0.5f, 0.5f, 0.5f)) {
            for (dark in listOf(false, true)) {
                PeekTheme(dark = dark, highContrast = false, reduceMotion = true, reduceTransparency = false, carrierIdentities = null) {
                    GlassSurface(CircleShape, Modifier.offset(24.dp + if (dark) 100.dp else 0.dp, 24.dp).size(44.dp)) {}
                }
            }
        }
        val lifts = listOf(138, 438).map { x -> (71..75).maxOf { green(bitmap, x, it) } - green(bitmap, x, 78) }
        assertEquals("$lifts", lifts[0].toDouble(), lifts[1].toDouble(), 2.0)
        assertTrue("$lifts", lifts[1] in 6..20)
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
            face(page, white = 1.03f, black = 0.9f, saturation = 0.9f, fill = 1f, fillAlpha = 0.72f, lift = 0.035f, maxLuma = 0.94f),
            face(page, white = 0.96f, black = 0.4f, saturation = 1.2f, fill = 1f, fillAlpha = 0.18f, lift = 0.035f, maxLuma = 0.94f),
            face(Color.Black, white = 0.96f, black = 0.5f, saturation = 0.8f, fill = 1f, fillAlpha = 0.45f, lift = 0.035f, maxLuma = 0.94f),
        )
        for ((case, color) in expected.withIndex()) {
            assertRgb(color, bitmap.getPixel((24 + case * 76 + 30) * 3, (24 + 30) * 3), 1)
        }
    }

    /**
     * CoreAnimation's face over a plain [behind], worked from its inputs apart from [GlassRecipe]: luminance onto
     * `black + (white − black) L`, saturation scaled, luminance over [maxLuma] taken off every channel, the fill laid
     * over and the lift added, each channel then held to 0…255.
     */
    private fun face(behind: Color, white: Float, black: Float, saturation: Float, fill: Float, fillAlpha: Float, lift: Float, maxLuma: Float): List<Int> {
        val channels = listOf(behind.red, behind.green, behind.blue)
        val luminance = 0.2126f * channels[0] + 0.7152f * channels[1] + 0.0722f * channels[2]
        val grey = black + (white - black) * luminance
        val excess = maxOf(grey - maxLuma, 0f)
        return channels.map { channel ->
            val filtered = grey + saturation * (channel - luminance) - excess
            (((1 - fillAlpha) * filtered + fillAlpha * fill + lift).coerceIn(0f, 1f) * 255).roundToInt()
        }
    }

    private fun assertGrey(expected: Float, color: Color) {
        assertEquals(expected, color.red, EIGHT_BITS)
        assertEquals(expected, color.green, EIGHT_BITS)
        assertEquals(expected, color.blue, EIGHT_BITS)
    }

    private fun assertRgb(expected: List<Int>, pixel: Int, tolerance: Int) {
        val actual = rgb(pixel)
        for (channel in 0..2) assertTrue("$actual, not $expected", abs(actual[channel] - expected[channel]) <= tolerance)
    }

    private fun rgb(pixel: Int) = listOf((pixel shr 16) and 0xFF, (pixel shr 8) and 0xFF, pixel and 0xFF)

    private fun green(bitmap: Bitmap, x: Int, y: Int) = (bitmap.getPixel(x, y) shr 8) and 0xFF

    /** The map's land behind the close button in the iPhone's screenshot. */
    private val BEHIND_THE_BUTTON = Color(233, 236, 229)

    /** The list's page, its parcel card under the tab bar and the card above, in the iPhone's screenshot. */
    private val PAGE = Color(244, 245, 241)
    private val GREEN_CARD = Color(219, 243, 234)
    private val BLUE_CARD = Color(223, 235, 250)

    private val YELLOW = Color(1f, 0.8f, 0f)

    /** One eight-bit step, and a little. */
    private val ONE_STEP = 1.01f / 255

    /** An sRGB Color holds eight bits a channel; the recipes' figures are rounded to four places. */
    private val EIGHT_BITS = 0.5f / 255 + 1e-4f

    /**
     * The glass [content] over a plain backdrop, the page's background unless given, with [behind] drawn on it, captured
     * at 3×. Robolectric may keep a drawing from before the content and the glass draws only in hardware, so the window
     * is drawn again until two captures agree.
     */
    private fun capture(dark: Boolean = false, backdrop: Color? = null, behind: DrawScope.() -> Unit = {}, content: @Composable () -> Unit): Bitmap {
        compose.setContent {
            PeekTheme(dark = dark, highContrast = false, reduceMotion = true, reduceTransparency = false, carrierIdentities = null) {
                val source = rememberMaterialBackdrop()
                Box(Modifier.testTag(SHOT).size(460.dp, 300.dp)) {
                    val plain = backdrop ?: PeekTheme.colors.background
                    Canvas(Modifier.fillMaxSize().materialBackdrop(source).background(plain), onDraw = behind)
                    CompositionLocalProvider(LocalMaterialBackdrop provides source) { content() }
                }
            }
        }
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        var previous: Bitmap? = null
        repeat(6) {
            compose.runOnUiThread {
                view.rootView.invalidate()
                view.invalidate()
            }
            compose.waitForIdle()
            val bitmap = compose.onNodeWithTag(SHOT).captureToImage().asAndroidBitmap()
            if (previous?.sameAs(bitmap) == true) return bitmap
            previous = bitmap
        }
        error("Two captures in a row never agreed")
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
            Box(Modifier.fillMaxSize().materialBackdrop(source)) {
                Canvas(Modifier.fillMaxSize(), onDraw = backdrop)
                Column(Modifier.padding(6.dp), verticalArrangement = Arrangement.spacedBy(38.dp)) {
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

    @Composable
    private fun Glass(shape: Shape, width: Dp, height: Dp) =
        GlassSurface(shape, Modifier.size(width, height)) {}
}
