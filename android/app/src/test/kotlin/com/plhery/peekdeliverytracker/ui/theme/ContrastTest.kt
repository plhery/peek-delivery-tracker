package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.api.CarrierId
import java.util.Locale
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * ContrastTests.swift: secondary words and the ink's quieter shade read at 4.5:1 or more, the ratio Apple asks of text
 * for Sufficient Contrast, on everything they stand on, in light and dark and with Increase Contrast.
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
            // The deliveries' counts stand in a capsule of the secondary label at 9 % on the page, and a notice is its
            // tint at 9 % on the page or the paper (NoticeBanner): the accent, the orange of the welcome's demo note
            // and the warning of the list's.
            val capsule = colors.secondaryLabel.let { it.copy(alpha = it.alpha * 0.09f) }.compositeOver(page)
            val notices = listOf("notice" to colors.accent, "orange notice" to colors.orange, "warning notice" to colors.warning)
                .flatMap { (name, tint) ->
                    listOf(page, colors.paper).map { under -> name to tint.copy(alpha = tint.alpha * 0.09f).compositeOver(under) }
                }
            val grounds = listOf(
                "page" to page, "paper" to colors.paper, "cream" to colors.cream, "count's capsule" to capsule,
                "sheet" to sheet, "sheet's row" to colors.raised, "sheet's control" to colors.sheetControl,
                "sheet's button" to colors.sheetButton.compositeOver(sheet),
            ) + notices + cards.map { (name, surface) -> name to colors.resolve(surface) }
            for ((name, ground) in grounds) {
                val measured = ratio(colors.secondaryText.compositeOver(ground), ground)
                if (measured < 4.5) short += String.format(Locale.ROOT, "%.2f:1 on the %s, %s", measured, name, appearance)
            }
        }
        assertTrue(short.joinToString("\n"), short.isEmpty())
    }

    /**
     * The ink's quieter shade, as in the name's tagline, which stands on the first open's DHL card as its globe turns
     * under it: land is the card's ink at 10 %, and a country on the route its ink at 17 % over that (MapPalette.tint).
     */
    @Test
    fun softInkReadsOnThePagePaperCreamSheetsAndTheFirstOpensCard() {
        val dhl = CarrierVisualIdentity.of(CarrierId.Dhl, CarrierCatalog(cacheFile = null), AppLanguage.En)
        val short = mutableListOf<String>()
        for ((appearance, colors) in appearances) {
            val card = colors.resolve(dhl.surface)
            val ink = colors.resolve(dhl.ink)
            val land = ink.copy(alpha = ink.alpha * 0.10f).compositeOver(card)
            val grounds = listOf(
                "page" to colors.background, "paper" to colors.paper, "cream" to colors.cream, "sheet" to colors.sheet,
                "sheet's row" to colors.raised, "first open's card" to card, "first open's land" to land,
                "first open's route" to ink.copy(alpha = ink.alpha * 0.17f).compositeOver(land),
            )
            for ((name, ground) in grounds) {
                val measured = ratio(colors.inkSoft, ground)
                if (measured < 4.5) short += String.format(Locale.ROOT, "%.2f:1 on the %s, %s", measured, name, appearance)
            }
        }
        assertTrue(short.joinToString("\n"), short.isEmpty())
    }
}
