package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.plhery.peekdeliverytracker.core.RgbaColor
import org.junit.Assert.assertEquals
import org.junit.Test

/** DesignSystem.swift's `Brand`, in each appearance. */
class PeekColorsTest {
    private fun hex(color: Color): String = "#%06X".format(color.toArgb() and 0xFFFFFF)

    private fun tokens(colors: PeekColors) = listOf(
        colors.accent, colors.accentBright, colors.onAccent, colors.ink, colors.inkSoft, colors.cream, colors.paper,
        colors.warning, colors.warningSoft, colors.background,
    ).map(::hex)

    @Test
    fun theBrandIsTheIPhonesInLightAndDark() {
        assertEquals(
            listOf("#F3CF48", "#F3CF48", "#20251E", "#20251E", "#657060", "#F0F0E9", "#FFFFFF", "#963E19", "#FFDDC7", "#F4F5F1"),
            tokens(PeekColors(dark = false)),
        )
        assertEquals(
            listOf("#F3CF48", "#F3CF48", "#20251E", "#F5F6F2", "#AFB9A9", "#292C28", "#242824", "#FFB184", "#4C3123", "#151915"),
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
}
