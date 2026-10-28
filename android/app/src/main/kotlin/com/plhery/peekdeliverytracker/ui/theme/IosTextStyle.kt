package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.ui.text.font.FontWeight

/**
 * SwiftUI's text styles (`Font.TextStyle`) with their point size in each content size category, xSmall to
 * accessibility5: the iOS Dynamic Type table of Apple's Human Interface Guidelines (Typography, "Dynamic Type
 * sizes"). The xSmall to xxxLarge columns agree with the iOS 11 edition of the same tables, and the body row with
 * ScootRadar's (UI-134). Each grows by its own steps, not by one ratio: footnote stays at 12 below Large.
 */
enum class IosTextStyle(private val sizes: IntArray, val weight: FontWeight = FontWeight.Normal) {
    LargeTitle(intArrayOf(31, 32, 33, 34, 36, 38, 40, 44, 48, 52, 56, 60)),
    Title(intArrayOf(25, 26, 27, 28, 30, 32, 34, 38, 43, 48, 53, 58)),
    Title2(intArrayOf(19, 20, 21, 22, 24, 26, 28, 34, 39, 44, 50, 56)),
    Title3(intArrayOf(17, 18, 19, 20, 22, 24, 26, 31, 37, 43, 49, 55)),
    Headline(intArrayOf(14, 15, 16, 17, 19, 21, 23, 28, 33, 40, 47, 53), FontWeight.SemiBold),
    Body(intArrayOf(14, 15, 16, 17, 19, 21, 23, 28, 33, 40, 47, 53)),
    Callout(intArrayOf(13, 14, 15, 16, 18, 20, 22, 26, 32, 38, 44, 51)),
    Subheadline(intArrayOf(12, 13, 14, 15, 17, 19, 21, 25, 30, 36, 42, 49)),
    Footnote(intArrayOf(12, 12, 12, 13, 15, 17, 19, 23, 27, 33, 38, 44)),
    Caption(intArrayOf(11, 11, 11, 12, 14, 16, 18, 22, 26, 32, 37, 43)),
    Caption2(intArrayOf(11, 11, 11, 11, 13, 15, 17, 20, 24, 29, 34, 40)),
    ;

    /** The style's size in points (dp) at a category. */
    fun pointSize(size: DynamicTypeSize): Float = sizes[size.ordinal].toFloat()
}
