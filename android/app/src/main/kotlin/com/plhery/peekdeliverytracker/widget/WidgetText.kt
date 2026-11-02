package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.text.LineBreaker
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils
import android.util.TypedValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.text.FontWeight
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import kotlin.math.ceil
import kotlin.math.roundToInt

/**
 * The widget's text as WidgetKit sets a widget's: iOS's text styles (`.headline`, `.caption2`...) at the phone's text
 * size, the app's mapping of Android's font scale to Dynamic Type's categories ([DynamicTypeSize.of]), held within the
 * categories a widget is drawn at ([sizes]); each style's point size from [IosTextStyle]'s table, drawn as that many dp
 * whatever Android's own font scaling (non-linear from Android 14) would make of it, as the app's own text is
 * (PeekTypography). And SwiftUI's `minimumScaleFactor`: a line too long for its room shrinks, in steps, before it ends
 * in an ellipsis ([fit]), measured with the font the launcher's text view draws it in (Glance's weights: Medium for
 * semibold, Bold for heavy) at the width the widget's layout gives it.
 */
internal class WidgetText(context: Context) {
    private val metrics = context.resources.displayMetrics
    private val fontScale = context.resources.configuration.fontScale

    /** Dynamic Type's category the widget's text is drawn at. */
    val category: DynamicTypeSize = DynamicTypeSize.of(fontScale).coerceIn(sizes)

    /** A style's size in points at the widget's category. */
    fun points(style: IosTextStyle): Float = style.pointSize(category)

    /** `points` as the sp a launcher's text view draws at that many dp, Android's font scaling undone. */
    fun sp(points: Float): TextUnit {
        val pixels = points * metrics.density
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            TypedValue.deriveDimension(TypedValue.COMPLEX_UNIT_SP, pixels, metrics).sp
        } else {
            (points / fontScale).sp
        }
    }

    /**
     * `minimumScaleFactor`: the size, in points, at which `text` fits `lines` lines `width` wide, `points` itself where
     * it fits at once, else the first of steps of 2% down to `minimumScale` of it; at the smallest, the text ends in an
     * ellipsis.
     */
    fun fit(text: String, points: Float, weight: FontWeight, width: Dp, lines: Int, minimumScale: Float): Float {
        val steps = ceil((1f - minimumScale) / STEP - 0.001f).toInt()
        for (step in 0..steps) {
            val size = points * maxOf(1f - step * STEP, minimumScale)
            if (lines(text, size, weight, width) <= lines) return size
        }
        return points * minimumScale
    }

    /** The lines `text` takes at `points` in a line `width` wide, as a text view breaks them. */
    fun lines(text: String, points: Float, weight: FontWeight, width: Dp): Int = layout(text, points, weight, width).lineCount

    /** How tall a text view of `text` is at `points` and `width`, at most `lines` lines, its font's padding included. */
    fun height(text: String, points: Float, weight: FontWeight, width: Dp, lines: Int): Dp =
        (layout(text, points, weight, width, lines).height / metrics.density).dp

    /** How tall a line of `points` is, its font's padding included. */
    fun lineHeight(points: Float, weight: FontWeight): Dp {
        val font = paint(points, weight).fontMetricsInt
        return ((font.bottom - font.top) / metrics.density).dp
    }

    private fun layout(text: String, points: Float, weight: FontWeight, width: Dp, lines: Int = Int.MAX_VALUE): StaticLayout {
        val pixels = (width.value * metrics.density).roundToInt().coerceAtLeast(1)
        return StaticLayout.Builder.obtain(text, 0, text.length, paint(points, weight), pixels)
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setIncludePad(true)
            .setUseLineSpacingFromFallbacks(true)
            .setBreakStrategy(LineBreaker.BREAK_STRATEGY_HIGH_QUALITY)
            .setHyphenationFrequency(Layout.HYPHENATION_FREQUENCY_NONE)
            .setMaxLines(lines)
            .setEllipsize(if (lines == Int.MAX_VALUE) null else TextUtils.TruncateAt.END)
            .build()
    }

    private fun paint(points: Float, weight: FontWeight) = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        typeface = Typeface.create(Typeface.DEFAULT, weights.getValue(weight), false)
        textSize = points * metrics.density
    }

    companion object {
        /**
         * The categories WidgetKit draws a Home Screen widget's text at. Apple's guidelines give Large to
         * accessibility5; iOS 27's simulator drew its widgets no larger than xxxLarge, which holds until the iPad
         * simulator's measurement says otherwise. The one place to change.
         */
        val sizes: ClosedRange<DynamicTypeSize> = DynamicTypeSize.Large..DynamicTypeSize.XXXLarge

        private const val STEP = 0.02f
        private val weights = mapOf(FontWeight.Normal to 400, FontWeight.Medium to 500, FontWeight.Bold to 700)
    }
}
