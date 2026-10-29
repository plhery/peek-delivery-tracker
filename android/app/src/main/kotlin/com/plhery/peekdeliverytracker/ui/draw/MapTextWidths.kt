package com.plhery.peekdeliverytracker.ui.draw

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.plhery.peekdeliverytracker.core.MapFont

/**
 * :core's TextMeasurer, `(text as NSString).size(withAttributes:)` on the iPhone: how wide a map name is on one line,
 * in dp, as the renderer writes it (`measureMapText`) but without tracking, which the layout adds itself. The app
 * keeps one, as the layout keeps the widths it measured per measurer.
 */
class MapTextWidths(private val measurer: TextMeasurer) : com.plhery.peekdeliverytracker.core.TextMeasurer {
    @Synchronized
    override fun width(text: String, font: MapFont): Double {
        val layout = measureMapText(measurer, text, font, Color.Black, tracking = 0.0)
        return (layout.getLineRight(0) - layout.getLineLeft(0)).toDouble()
    }

    companion object {
        fun of(context: Context): MapTextWidths =
            MapTextWidths(TextMeasurer(createFontFamilyResolver(context), Density(1f), LayoutDirection.Ltr))
    }
}
