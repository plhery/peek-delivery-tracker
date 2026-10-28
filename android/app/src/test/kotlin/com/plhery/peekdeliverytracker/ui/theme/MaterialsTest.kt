package com.plhery.peekdeliverytracker.ui.theme

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import kotlin.math.pow

/** The iPhone's materials from CoreMaterial's recipes, drawn over a backdrop: the filter's arithmetic and the blur. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w390dp-h844dp-mdpi")
class MaterialsTest {
    @get:Rule
    val compose = createComposeRule()

    private val recipes = PeekMaterial.entries.flatMap { listOf(it.recipe(dark = false), it.recipe(dark = true)) }

    @Test
    fun theLuminanceLineIsTheCurvesLeastSquaresFit() {
        for (recipe in recipes) {
            // The Bézier sampled finely, and the line fitted to the samples by the textbook formulas.
            val v = recipe.luminanceValues.map { it.toDouble() }
            val ts = (0..10_000).map { it / 10_000.0 }
            val ys = ts.map { t -> v[0] * (1 - t).pow(3) + 3 * v[1] * t * (1 - t).pow(2) + 3 * v[2] * t * t * (1 - t) + v[3] * t.pow(3) }
            val tMean = ts.average()
            val yMean = ys.average()
            val slope = ts.indices.sumOf { (ts[it] - tMean) * (ys[it] - yMean) } / ts.sumOf { (it - tMean).pow(2) }
            val (intercept, fitted) = recipe.luminanceLine
            assertEquals(recipe.source, slope, fitted.toDouble(), 1e-3)
            assertEquals(recipe.source, yMean - slope * tMean, intercept.toDouble(), 1e-3)
        }
    }

    @Test
    fun theUltraThinMaterialWorkedByHand() {
        // Light: the line 0.464 + 0.237 L, mixed in by half, saturation × 1.1, brightness + 0.12.
        val light = PeekMaterial.UltraThin.recipe(dark = false)
        assertGrey(0.9705f, light.over(Color.White))
        assertGrey(0.352f, light.over(Color.Black))
        // Pure red: (0.757, 0.257, 0.257) after the curve, luminance 0.3633, then saturated and brightened.
        val red = light.over(Color.Red)
        assertEquals(0.9164f, red.red, EIGHT_BITS)
        assertEquals(0.3664f, red.green, EIGHT_BITS)
        assertEquals(0.3664f, red.blue, EIGHT_BITS)
        // Dark: the line 0.216 + 0.153 L, no brightness.
        val dark = PeekMaterial.UltraThin.recipe(dark = true)
        assertGrey(0.6845f, dark.over(Color.White))
        assertGrey(0.108f, dark.over(Color.Black))
        // σ 22.5 pt is an Android radius of 22.5 √3.
        assertEquals(38.97f, light.androidBlurRadius.value, 0.01f)
    }

    private fun assertGrey(expected: Float, color: Color) {
        assertEquals(expected, color.red, EIGHT_BITS)
        assertEquals(expected, color.green, EIGHT_BITS)
        assertEquals(expected, color.blue, EIGHT_BITS)
        assertEquals(1f, color.alpha, 0f)
    }

    /** An sRGB Color holds eight bits a channel. */
    private val EIGHT_BITS = 0.5f / 255 + 1e-4f

    @Test
    fun theBlurSpreadsWhatIsBehindInPlace() {
        // A white backdrop with one dark bar 8 wide: behind the material it spreads into a soft dip centred on the bar,
        // and white elsewhere comes out as the filter makes it. Robolectric's hardware renderer draws the blur.
        val bitmap = capture {
            val backdrop = rememberMaterialBackdrop()
            Box(Modifier.testTag(SHOT).size(240.dp, 120.dp)) {
                Row(Modifier.fillMaxSize().materialBackdrop(backdrop)) {
                    repeat(30) { Box(Modifier.width(8.dp).fillMaxHeight().background(if (it == 13) Color.Black else Color.White)) }
                }
                MaterialSurface(PeekMaterial.UltraThin, ContinuousRoundedShape(16.dp), Modifier.align(Alignment.Center).size(160.dp, 60.dp), backdrop) {}
            }
        }
        val row = (44 until 196).map { x -> x to (bitmap.getPixel(x, 60) and 0xFF) }
        val darkest = row.minOf { it.second }
        assertEquals(108.0, row.filter { it.second == darkest }.map { it.first }.average(), 2.0)
        // Blurred, the bar is wide and pale.
        assertTrue("$darkest", darkest in 200..235)
        assertTrue(row.count { it.second < 240 } > 30)
        val white = PeekMaterial.UltraThin.recipe(dark = false).over(Color.White).red * 255
        assertEquals(white, (bitmap.getPixel(190, 60) and 0xFF).toFloat(), 2f)
    }

    @Test
    fun withoutTransparencyTheMaterialIsSolid() {
        // Reduce Transparency and a missing backdrop each draw the material over the page's colour; Increase Contrast
        // still blurs the backdrop, black here.
        val bitmap = capture {
            val backdrop = rememberMaterialBackdrop()
            Box(Modifier.testTag(SHOT).size(180.dp, 60.dp)) {
                Box(Modifier.fillMaxSize().materialBackdrop(backdrop).background(Color.Black))
                Row {
                    for (case in 0..2) {
                        PeekTheme(dark = false, highContrast = case == 1, reduceMotion = true, reduceTransparency = case == 0, carrierIdentities = null) {
                            MaterialSurface(PeekMaterial.Thick, ContinuousRoundedShape(8.dp), Modifier.size(60.dp), backdrop.takeIf { case != 2 }) {}
                        }
                    }
                }
            }
        }
        for (case in 0..2) {
            val behind = if (case == 1) Color.Black else PeekColors(dark = false, highContrast = false).background
            val expected = PeekMaterial.Thick.recipe(dark = false).over(behind)
            val pixel = bitmap.getPixel(case * 60 + 30, 30)
            assertEquals("case $case", expected.red * 255, ((pixel shr 16) and 0xFF).toFloat(), 1f)
            assertEquals("case $case", expected.green * 255, ((pixel shr 8) and 0xFF).toFloat(), 1f)
            assertEquals("case $case", expected.blue * 255, (pixel and 0xFF).toFloat(), 1f)
        }
    }

    private fun capture(content: @Composable () -> Unit): Bitmap {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = true, reduceTransparency = false, carrierIdentities = null) {
                content()
            }
        }
        // Robolectric may keep a drawing from before the content: draw the window again first.
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.runOnUiThread { view.rootView.invalidate() }
        compose.waitForIdle()
        return compose.onNodeWithTag(SHOT).captureToImage().asAndroidBitmap()
    }

    @Test
    fun sheetLight() = sheet(dark = false)

    @Test
    fun sheetDark() = sheet(dark = true)

    /** Every material over a busy backdrop, and the glass as Reduce Transparency and Increase Contrast draw it. */
    private fun sheet(dark: Boolean) {
        compose.setContent {
            PeekTheme(dark = dark, highContrast = false, reduceMotion = true, reduceTransparency = false, carrierIdentities = null) {
                val colors = PeekTheme.colors
                val backdrop = rememberMaterialBackdrop()
                Box(Modifier.testTag(SHOT).size(360.dp, 300.dp)) {
                    Box(Modifier.fillMaxSize().materialBackdrop(backdrop).background(colors.background)) {
                        Canvas(Modifier.fillMaxSize()) {
                            val bands = listOf(colors.accent, colors.paper, colors.warning, colors.ink, colors.accentBright, colors.cream)
                            rotate(-30f) {
                                for (i in -12..24) {
                                    drawRect(bands[(i + 12) % bands.size], Offset(i * 26f - 120f, -200f), size.copy(width = 13f, height = size.height + 400f))
                                }
                            }
                        }
                        Column(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(46.dp)) {
                            repeat(6) { Text("Peek · parcel on its way", style = PeekTheme.type.title2, color = colors.ink) }
                        }
                    }
                    CompositionLocalProvider(LocalMaterialBackdrop provides backdrop) {
                        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            val shape = ContinuousRoundedShape(Radii.inlineToast)
                            val cell = Modifier.size(158.dp, 56.dp)
                            for (pair in PeekMaterial.entries.chunked(2)) {
                                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    for (material in pair) {
                                        MaterialSurface(material, shape, cell) { Row(Modifier.fillMaxSize()) { Label(material.name) } }
                                    }
                                    if (pair.size == 1) GlassSurface(shape, cell) { Row(Modifier.fillMaxSize()) { Label("Glass") } }
                                }
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                CompositionLocalProvider(LocalReduceTransparency provides true) {
                                    GlassSurface(shape, cell) { Row(Modifier.fillMaxSize()) { Label("Solid") } }
                                }
                                PeekTheme(dark = dark, highContrast = true, reduceMotion = true, reduceTransparency = false, carrierIdentities = null) {
                                    CompositionLocalProvider(LocalMaterialBackdrop provides backdrop) {
                                        GlassSurface(shape, cell) { Row(Modifier.fillMaxSize()) { Label("Contrast") } }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        compose.screenshot("materials_" + if (dark) "dark" else "light")
    }

    @Composable
    private fun RowScope.Label(text: String) =
        Text(text, Modifier.align(Alignment.CenterVertically).padding(start = 12.dp), style = PeekTheme.type.subheadline, color = PeekTheme.colors.ink)
}
