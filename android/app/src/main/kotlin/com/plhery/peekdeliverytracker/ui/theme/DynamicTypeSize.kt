package com.plhery.peekdeliverytracker.ui.theme

/**
 * iOS's content size categories (SwiftUI's `DynamicTypeSize`), smallest first, so `>=` compares them as Swift does.
 * Android has one font scale instead; `of` gives the category whose body size is nearest to it.
 */
enum class DynamicTypeSize {
    XSmall,
    Small,
    Medium,
    Large,
    XLarge,
    XXLarge,
    XXXLarge,
    Accessibility1,
    Accessibility2,
    Accessibility3,
    Accessibility4,
    Accessibility5,
    ;

    /** `isAccessibilitySize`: the five accessibility sizes. */
    val isAccessibilitySize: Boolean get() = this >= Accessibility1

    /** `dynamicTypeSize(...cap)`: this size, or `cap` when this one is larger. */
    fun capped(at: DynamicTypeSize): DynamicTypeSize = minOf(this, at)

    companion object {
        /**
         * Where each category starts on Android's font scale (Large is the default 1.0). Each bound lies halfway
         * between the two body sizes it separates (14, 15, 16, 17, 19, 21, 23, 28, 33, 40, 47, 53 points, over 17),
         * except xLarge at 1.1 and accessibility1 at 1.5, ScootRadar's switches (UI-134). A Pixel's steps land on
         * xSmall (0.85), Large (1.0), xLarge (1.15), xxxLarge (1.3), accessibility1 (1.5) and accessibility2 (1.8, 2.0):
         * Android's two largest steps both read as accessibility2, its 2.0 falling short of accessibility3's 2.15, so
         * accessibility3 to 5 never occur and what iOS changes only from accessibility3 stays as at accessibility2.
         */
        private val lowerBounds = floatArrayOf(
            14.5f / 17,
            15.5f / 17,
            16.5f / 17,
            1.1f,
            20f / 17,
            22f / 17,
            1.5f,
            30.5f / 17,
            36.5f / 17,
            43.5f / 17,
            50f / 17,
        )

        /** The category for Android's font scale (`Configuration.fontScale`, `Density.fontScale`). */
        fun of(fontScale: Float): DynamicTypeSize = entries[lowerBounds.count { fontScale >= it }]
    }
}
