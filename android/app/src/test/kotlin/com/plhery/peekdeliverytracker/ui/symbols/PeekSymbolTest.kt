package com.plhery.peekdeliverytracker.ui.symbols

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertEquals
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
    fun theOutlineFillsTheSFGlyphsInk() {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = true, carrierIdentities = null) {
                Box(Modifier.testTag("box").background(Color.White)) {
                    PeekSymbol("xmark", font = TextStyle(fontSize = 50.sp), tint = Color.Black)
                }
            }
        }
        // Robolectric may keep a drawing from before the content: draw the window again first.
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.runOnUiThread { view.rootView.invalidate() }
        compose.waitForIdle()
        val bitmap = compose.onNodeWithTag("box").captureToImage().asAndroidBitmap()
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
        val names = peekSymbolNames.sorted()
        compose.setContent {
            PeekTheme(dark = dark, highContrast = false, reduceMotion = true, carrierIdentities = null) {
                val colors = PeekTheme.colors
                Column(Modifier.testTag(SHOT).background(colors.background).padding(6.dp)) {
                    for (line in names.chunked(6)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            for (name in line) {
                                Column(Modifier.size(61.dp, 56.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(Modifier.size(61.dp, 34.dp), contentAlignment = Alignment.Center) {
                                        PeekSymbol(name, font = PeekTheme.type.title3.copy(fontWeight = FontWeight.Normal), tint = colors.ink)
                                    }
                                    Text(name, style = PeekTheme.type.caption2.copy(fontSize = 7.sp), color = colors.inkSoft, textAlign = TextAlign.Center, maxLines = 2)
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
