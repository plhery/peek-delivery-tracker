package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import com.plhery.peekdeliverytracker.core.RgbaColor
import org.junit.Assert.assertEquals
import org.junit.Test

/** DesignSystem.swift's `Brand`, in each appearance. */
class PeekColorsTest {
    private fun hex(color: Color): String = "#%06X".format(color.toArgb() and 0xFFFFFF)

    private fun tokens(colors: PeekColors) = listOf(
        colors.accent, colors.accentBright, colors.onAccent, colors.ink, colors.inkSoft, colors.secondaryText, colors.cream,
        colors.paper, colors.raised, colors.warning, colors.warningSoft, colors.background,
    ).map(::hex)

    @Test
    fun theBrandIsTheIPhonesInLightAndDark() {
        assertEquals(
            listOf(
                "#F3CF48", "#F3CF48", "#20251E", "#20251E", "#657060", "#606065", "#F0F0E9", "#FFFFFF", "#FFFFFF", "#963E19",
                "#FFDDC7", "#F4F5F1",
            ),
            tokens(PeekColors(dark = false)),
        )
        assertEquals(
            listOf(
                "#F3CF48", "#F3CF48", "#20251E", "#FAF6EB", "#C2BAAA", "#BCBCC2", "#292620", "#1F1C17", "#292620", "#FFB184",
                "#341F14", "#13110E",
            ),
            tokens(PeekColors(dark = true)),
        )
    }

    @Test
    fun increasedContrastMovesOnlyTheAdaptiveColours() {
        val normal = PeekColors(dark = false)
        val contrasted = PeekColors(dark = false, highContrast = true)
        assertEquals(normal.accent, contrasted.accent)
        assertEquals(normal.onAccent, contrasted.onAccent)
        // Ink is dark, so it goes 0.06 darker in each channel.
        assertEquals(RgbaColor.hex("#20251E").red - 0.06, contrasted.ink.red.toDouble(), 0.002)
        assertEquals("#FFFFFF", hex(contrasted.paper))
    }

    @Test
    fun channelsOutsideTheRangeAreHeldToIt() {
        assertEquals(Color(1f, 0f, 0.5f, 1f), RgbaColor(1.2, -0.1, 0.5, 1.5).toColor())
    }

    @Test
    fun theSystemColoursAreIOSsWithAndWithoutIncreasedContrast() {
        val appearances = listOf(
            PeekColors(dark = false),
            PeekColors(dark = true),
            PeekColors(dark = false, highContrast = true),
            PeekColors(dark = true, highContrast = true),
        )
        assertEquals(listOf("#FF383C", "#FF4245", "#E9152D", "#FF6165"), appearances.map { hex(it.red) })
        assertEquals(listOf("#34C759", "#30D158", "#008932", "#4AD968"), appearances.map { hex(it.green) })
        assertEquals(listOf("#FF8D28", "#FF9230", "#C55300", "#FFA056"), appearances.map { hex(it.orange) })
        assertEquals(listOf("#000000", "#FFFFFF", "#000000", "#FFFFFF"), appearances.map { hex(it.label) })
        assertEquals(listOf("#3C3C43", "#EBEBF5", "#3C3C43", "#EBEBF5"), appearances.map { hex(it.secondaryLabel) })
        assertEquals(listOf("#3C3C43", "#EBEBF5", "#3C3C43", "#EBEBF5"), appearances.map { hex(it.tertiaryLabel) })
        assertEquals(listOf("#3C3C43", "#545458", "#3C3C43", "#545458"), appearances.map { hex(it.separator) })
        // Increase Contrast darkens the secondary and tertiary labels; the separator stays.
        assertOpacities(listOf(0.6, 0.6, 0.8, 0.698), appearances.map { it.secondaryLabel })
        assertOpacities(listOf(0.298, 0.298, 0.698, 0.549), appearances.map { it.tertiaryLabel })
        assertOpacities(listOf(0.12, 0.5, 0.12, 0.5), appearances.map { it.separator })
        // Over white and black, the separator is UIKit's opaqueSeparator.
        assertEquals("#E7E7E8", hex(appearances[0].separator.compositeOver(Color.White)))
        assertEquals("#2A2A2C", hex(appearances[1].separator.compositeOver(Color.Black)))
    }

    /**
     * In light a sheet is the page: each of its colours is, exactly, the page's colour it stands for, so Light draws as
     * before. In dark it stands up: its ground over the page, its rows over its ground, its controls over its rows.
     */
    @Test
    fun sheetsKeepThePagesColoursInLightAndStandUpInDark() {
        for (colors in listOf(PeekColors(dark = false), PeekColors(dark = false, highContrast = true))) {
            assertEquals(colors.background, colors.sheet)
            assertEquals(colors.paper, colors.raised)
            assertEquals(colors.cream, colors.sheetControl)
            assertEquals(colors.ink.copy(alpha = colors.ink.alpha * 0.07f), colors.sheetButton)
            for (opacity in listOf(0.25f, 0.3f, 0.5f, 0.6f)) {
                assertEquals(colors.separator.copy(alpha = colors.separator.alpha * opacity), colors.sheetLine(opacity))
            }
        }
        val dark = PeekColors(dark = true)
        assertEquals(
            listOf("#13110E", "#191612", "#292620", "#36322C", "#4A463E"),
            listOf(dark.background, dark.sheet, dark.raised, dark.sheetControl, dark.sheetLine(0.3f)).map(::hex),
        )
        assertEquals("#36322C", hex(dark.sheetButton.compositeOver(dark.sheet)))
        assertEquals("#36322C", hex(dark.sheetButton.compositeOver(dark.raised)))
        assertEquals(dark.sheetLine(0.25f), dark.sheetLine(0.6f))
    }

    /** Compose keeps an sRGB colour's opacity in 8 bits: within half a step, and a hair for the float. */
    private fun assertOpacities(expected: List<Double>, colors: List<Color>) {
        for ((opacity, color) in expected.zip(colors)) assertEquals(opacity, color.alpha.toDouble(), 0.501 / 255)
    }
}
