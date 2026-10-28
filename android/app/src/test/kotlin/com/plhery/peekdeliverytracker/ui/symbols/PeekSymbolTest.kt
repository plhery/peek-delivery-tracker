package com.plhery.peekdeliverytracker.ui.symbols

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getAlignmentLinePosition
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/** The SF Symbols table (android/tools/material-symbols.tsv) against the iPhone's code, and the symbols as drawn. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PeekSymbolTest {
    @get:Rule
    val compose = createComposeRule()

    private class Row(val sf: String, val material: String, val fill: Boolean, val uses: List<String>)

    private val table = File("../tools/material-symbols.tsv").readLines().filter { it.isNotEmpty() && !it.startsWith("#") }.map {
        val columns = it.split("\t")
        Row(columns[0], columns[1], columns[2] == "1", columns[3].split("; "))
    }

    /** What Android draws: the rows with a Material icon and a use outside the Mac app. */
    private val drawn = table.filter { row -> row.material != "-" && row.uses.any { " mac" !in it && " none" !in it } }

    @Test
    fun everySymbolTheIPhoneDrawsHasItsMaterialIcon() {
        assertEquals(drawn.map { it.sf }.toSet(), peekSymbolNames)
        for (row in drawn) {
            val art = materialSymbols.getValue(row.sf)
            assertEquals(row.sf, row.material, art.material)
            assertEquals(row.sf, row.fill, art.fill)
            // Only the symbols iOS draws hierarchical have that form: two layers in every outline.
            if (row.uses.any { "hierarchical" in it.split(" ") }) {
                assertTrue(row.sf, art.lowerOpacity!! in 0.1f..0.9f)
                assertTrue(row.sf, art.outlines.all { it.layers.size == 2 })
            } else {
                assertNull(row.sf, art.lowerOpacity)
            }
        }
    }

    @Test
    fun everyNameTheIPhonesCodeGivesAnImageIsInTheTable() {
        // MaterialSymbols.swift checks every SF Symbol name in ios/ against the system's list; this checks, without a
        // Mac, every name written where an image is made.
        val ios = File("../../ios")
        if (!ios.isDirectory) return
        val image = Regex("""(systemName|systemImage):\s*([^)\n]*)""")
        val literal = Regex("\"([a-z0-9][a-z0-9.]*)\"")
        val names = ios.walkTopDown().filter { it.extension == "swift" && "Tests" !in it.path }.flatMap { file ->
            image.findAll(file.readText()).flatMap { use -> literal.findAll(use.groupValues[2]).map { it.groupValues[1] } }
        }.toSet()
        assertTrue(names.size > 50)
        val listed = table.map { it.sf }.toSet()
        assertEquals(emptySet<String>(), names - listed)
    }

    @Test
    fun aSymbolTakesItsSFImagesBoxAndBaseline() {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = true, carrierIdentities = null) {
                Box(Modifier.testTag("box")) { PeekSymbol("checkmark", font = TextStyle(fontSize = 17.sp)) }
            }
        }
        // AppKit's checkmark at 100 pt: 109 × 102, its baseline 85 down.
        val bounds = compose.onNodeWithTag("box").getUnclippedBoundsInRoot()
        assertEquals(18.53f, (bounds.right - bounds.left).value, 0.5f)
        assertEquals(17.34f, (bounds.bottom - bounds.top).value, 0.5f)
        assertEquals(14.45f, materialSymbols.getValue("checkmark").box(17f)[2], 1e-4f)
        val node = compose.onNodeWithTag("box").fetchSemanticsNode()
        assertTrue(node.config.none { it.key == SemanticsProperties.ContentDescription })
    }

    @Test
    fun anImageScaleKeepsTheSymbolOnTheCapHeightsMiddle() {
        // AppKit's circle at 100 pt: small 93 × 93, its baseline 82 down; medium 118, 94; large 150, 111. Scaled about
        // the baseline, the small one's would be 74 down.
        val expected = mapOf(SymbolScale.Small to floatArrayOf(93f, 82f), SymbolScale.Medium to floatArrayOf(118f, 94f), SymbolScale.Large to floatArrayOf(150f, 111f))
        for ((scale, image) in expected) {
            val box = materialSymbols.getValue("circle").box(100f, scale.factor)
            assertEquals("$scale", image[0], box[1], 1.2f)
            assertEquals("$scale", image[1], box[2], 1.2f)
        }
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = true, carrierIdentities = null) {
                Box(Modifier.testTag("box")) { PeekSymbol("circle", font = TextStyle(fontSize = 17.sp), scale = SymbolScale.Small) }
            }
        }
        val node = compose.onNodeWithTag("box")
        val bounds = node.getUnclippedBoundsInRoot()
        assertEquals(93 * 0.17f, (bounds.bottom - bounds.top).value, 0.5f)
        assertEquals(82 * 0.17f, node.getAlignmentLinePosition(FirstBaseline).value, 0.5f)
    }

    @Test
    fun aHierarchicalSymbolDrawsItsShapeFaintAndItsGlyphFull() {
        // iOS draws the demo bar's xmark.circle.fill hierarchical: the disc at 30 % of the tint, the cross over it at
        // full. Monochrome, the cross is a hole in a solid disc.
        var mode by mutableStateOf(SymbolRenderingMode.Hierarchical)
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = true, carrierIdentities = null) {
                Box(Modifier.testTag("box").background(Color.White)) {
                    PeekSymbol("xmark.circle.fill", font = TextStyle(fontSize = 50.sp), renderingMode = mode, tint = Color.Black)
                }
            }
        }
        val hierarchical = capture()
        mode = SymbolRenderingMode.Monochrome
        val monochrome = capture()
        val art = materialSymbols.getValue("xmark.circle.fill")
        // At 50 pt on a 2× screen, pixels are the points: the disc's middle, and a point on the disc off the cross.
        val centreX = (art.sf[2] + art.sf[4] / 2).toInt()
        val centreY = (art.sf[3] + art.sf[5] / 2).toInt()
        val discY = (art.sf[3] + art.sf[5] * 0.15f).toInt()
        assertEquals(0, hierarchical.getPixel(centreX, centreY) and 0xFF)
        assertEquals(255 * (1 - art.lowerOpacity!!), (hierarchical.getPixel(centreX, discY) and 0xFF).toFloat(), 3f)
        assertEquals(255, monochrome.getPixel(centreX, centreY) and 0xFF)
        assertEquals(0, monochrome.getPixel(centreX, discY) and 0xFF)
    }

    /** The node tagged box as drawn, the window drawn again first as Robolectric may keep an older drawing. */
    private fun capture(): Bitmap {
        compose.waitForIdle()
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.runOnUiThread { view.rootView.invalidate() }
        compose.waitForIdle()
        return compose.onNodeWithTag("box").captureToImage().asAndroidBitmap()
    }

    @Test
    fun theAppCarriesMaterialSymbolsLicence() {
        val assets = ApplicationProvider.getApplicationContext<Context>().assets
        val licence = assets.open("MATERIAL-SYMBOLS-LICENSE.txt").bufferedReader().use { it.readText() }
        assertTrue(licence.contains("Apache License") && licence.contains("Version 2.0"))
    }

    @Test
    fun theOutlineFillsTheSFGlyphsInk() {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = true, carrierIdentities = null) {
                Box(Modifier.testTag("box").background(Color.White)) {
                    PeekSymbol("xmark", font = TextStyle(fontSize = 50.sp), tint = Color.Black)
                }
            }
        }
        val bitmap = capture()
        var left = bitmap.width
        var top = bitmap.height
        var right = -1
        var bottom = -1
        for (y in 0 until bitmap.height) for (x in 0 until bitmap.width) {
            if ((bitmap.getPixel(x, y) and 0xFF) < 128) {
                left = minOf(left, x); right = maxOf(right, x); top = minOf(top, y); bottom = maxOf(bottom, y)
            }
        }
        // xmark's ink at 100 pt: 77.25 × 78.5 from (12.5, 9.25). At 50 pt on a 2× screen, pixels are the points.
        val art = materialSymbols.getValue("xmark")
        val fit = minOf(art.sf[4] / art.outline(400, 50f).bounds[2], art.sf[5] / art.outline(400, 50f).bounds[3])
        val outline = art.outline(400, 50f).bounds
        assertEquals(art.sf[2] + art.sf[4] / 2, (left + right + 1) / 2f, 1f)
        assertEquals(art.sf[3] + art.sf[5] / 2, (top + bottom + 1) / 2f, 1f)
        assertEquals(outline[2] * fit, (right + 1 - left).toFloat(), 1.5f)
        assertEquals(outline[3] * fit, (bottom + 1 - top).toFloat(), 1.5f)
    }

    // The sheets at one pixel a point, to keep them small.
    @Test
    @Config(qualifiers = "w390dp-h844dp-mdpi")
    fun sheetLight() = sheet(dark = false)

    @Test
    @Config(qualifiers = "w390dp-h844dp-mdpi")
    fun sheetDark() = sheet(dark = true)

    private fun sheet(dark: Boolean) {
        // Every symbol, then the one iOS also draws hierarchical, so drawn.
        val cells = peekSymbolNames.sorted().map { it to SymbolRenderingMode.Monochrome } + ("xmark.circle.fill" to SymbolRenderingMode.Hierarchical)
        compose.setContent {
            PeekTheme(dark = dark, highContrast = false, reduceMotion = true, carrierIdentities = null) {
                val colors = PeekTheme.colors
                Column(Modifier.testTag(SHOT).background(colors.background).padding(6.dp)) {
                    for (line in cells.chunked(6)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            for ((name, mode) in line) {
                                Column(Modifier.size(61.dp, 56.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(Modifier.size(61.dp, 34.dp), contentAlignment = Alignment.Center) {
                                        PeekSymbol(name, font = PeekTheme.type.title3.copy(fontWeight = FontWeight.Normal), renderingMode = mode, tint = colors.ink)
                                    }
                                    val label = if (mode == SymbolRenderingMode.Hierarchical) "$name hierarchical" else name
                                    Text(label, style = PeekTheme.type.caption2.copy(fontSize = 7.sp), color = colors.inkSoft, textAlign = TextAlign.Center, maxLines = 2)
                                }
                            }
                        }
                    }
                }
            }
        }
        compose.screenshot("symbols_" + if (dark) "dark" else "light")
    }
}
