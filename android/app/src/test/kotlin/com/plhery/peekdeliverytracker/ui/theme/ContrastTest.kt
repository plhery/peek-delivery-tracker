package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import java.util.Locale
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * ContrastTests.swift: secondary words read at 4.5:1 or more, the ratio Apple asks of text for Sufficient Contrast, on
 * everything they stand on, in light and dark and with Increase Contrast.
 */
class ContrastTest {
    private val appearances = listOf(
        "light" to PeekColors(dark = false),
        "dark" to PeekColors(dark = true),
        "light, increased contrast" to PeekColors(dark = false, highContrast = true),
        "dark, increased contrast" to PeekColors(dark = true, highContrast = true),
    )

    /** WCAG's ratio ([CarrierVisualIdentity.contrast]) of two opaque colours as the screen draws them, in 8 bits. */
    private fun ratio(first: Color, second: Color): Double = CarrierVisualIdentity.contrast(hex(first), hex(second))

    private fun hex(color: Color): String = "#%06X".format(color.toArgb() and 0xFFFFFF)

    @Test
    fun secondaryTextReadsOnThePagePaperCreamSheetsAndEveryCarriersCard() {
        // The bundled rules, without inheriting a previously cached live catalog.
        val cards = CarrierCatalog(cacheFile = null).definitions.map { (id, definition) ->
            "${id.rawValue}'s card" to CarrierVisualIdentity(id.rawValue, definition).surface
        }
        assertTrue(cards.size > 1000)
        val short = mutableListOf<String>()
        for ((appearance, colors) in appearances) {
            val page = colors.background
            val sheet = colors.sheet
            // The deliveries' counts stand in a capsule of the secondary label at 9 % on the page.
            val capsule = colors.secondaryLabel.let { it.copy(alpha = it.alpha * 0.09f) }.compositeOver(page)
            val grounds = listOf(
                "page" to page, "paper" to colors.paper, "cream" to colors.cream, "count's capsule" to capsule,
                "sheet" to sheet, "sheet's row" to colors.raised, "sheet's control" to colors.sheetControl,
                "sheet's button" to colors.sheetButton.compositeOver(sheet),
            ) + cards.map { (name, surface) -> name to colors.resolve(surface) }
            for ((name, ground) in grounds) {
                val measured = ratio(colors.secondaryText.compositeOver(ground), ground)
                if (measured < 4.5) short += String.format(Locale.ROOT, "%.2f:1 on the %s, %s", measured, name, appearance)
            }
        }
        assertTrue(short.joinToString("\n"), short.isEmpty())
    }
}
