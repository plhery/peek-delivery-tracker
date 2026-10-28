package com.plhery.peekdeliverytracker.ui.theme

import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.Accessibility1
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.Accessibility2
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.Accessibility3
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.Accessibility4
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.Accessibility5
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.Large
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.Medium
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.Small
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.XLarge
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.XSmall
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.XXLarge
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize.XXXLarge
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DynamicTypeSizeTest {
    @Test
    fun aPixelsFontSizesLandOnTheNearestCategories() {
        val steps = mapOf(
            0.85f to XSmall,
            1.0f to Large,
            1.15f to XLarge,
            1.3f to XXXLarge,
            1.5f to Accessibility1,
            1.8f to Accessibility2,
            2.0f to Accessibility2,
        )
        for ((scale, size) in steps) assertEquals("font scale $scale", size, DynamicTypeSize.of(scale))
    }

    @Test
    fun eachCategoryStartsHalfwayBetweenBodySizesExceptAtScootRadarsSwitches() {
        val starts = mapOf(
            Small to 14.5f / 17,
            Medium to 15.5f / 17,
            Large to 16.5f / 17,
            XLarge to 1.1f,
            XXLarge to 20f / 17,
            XXXLarge to 22f / 17,
            Accessibility1 to 1.5f,
            Accessibility2 to 30.5f / 17,
            Accessibility3 to 36.5f / 17,
            Accessibility4 to 43.5f / 17,
            Accessibility5 to 50f / 17,
        )
        for ((size, start) in starts) {
            assertEquals(size, DynamicTypeSize.of(start))
            assertEquals(DynamicTypeSize.entries[size.ordinal - 1], DynamicTypeSize.of(Math.nextDown(start)))
        }
        assertEquals(XSmall, DynamicTypeSize.of(0.5f))
        assertEquals(Accessibility5, DynamicTypeSize.of(4f))
    }

    @Test
    fun theAccessibilitySizesStartAtAccessibility1() {
        assertFalse(XXXLarge.isAccessibilitySize)
        assertTrue(Accessibility1.isAccessibilitySize)
        assertTrue(Accessibility5.isAccessibilitySize)
        assertTrue(DynamicTypeSize.of(1.5f).isAccessibilitySize)
        assertFalse(DynamicTypeSize.of(1.49f).isAccessibilitySize)
        // `dynamicTypeSize >= .xxLarge`, as PullToRefresh and the list ask.
        assertTrue(DynamicTypeSize.of(1.3f) >= XXLarge)
        assertFalse(DynamicTypeSize.of(1.15f) >= XXLarge)
    }

    @Test
    fun aCapLimitsOnlyLargerSizes() {
        assertEquals(Accessibility1, Accessibility3.capped(at = Accessibility1))
        assertEquals(Large, Large.capped(at = Accessibility1))
        assertEquals(XXLarge, Accessibility2.capped(at = XXLarge))
        assertEquals(XLarge, XLarge.capped(at = XXLarge))
    }

    @Test
    fun theBodyRowIsScootRadarsAndHeadlineIsBodyInSemibold() {
        val body = listOf(14, 15, 16, 17, 19, 21, 23, 28, 33, 40, 47, 53)
        assertEquals(body.map(Int::toFloat), DynamicTypeSize.entries.map(IosTextStyle.Body::pointSize))
        assertEquals(body.map(Int::toFloat), DynamicTypeSize.entries.map(IosTextStyle.Headline::pointSize))
        assertEquals(androidx.compose.ui.text.font.FontWeight.SemiBold, IosTextStyle.Headline.weight)
    }

    @Test
    fun theLargeColumnIsTheDefaultSizes() {
        val large = IosTextStyle.entries.associateWith { it.pointSize(Large) }
        assertEquals(
            mapOf(
                IosTextStyle.LargeTitle to 34f, IosTextStyle.Title to 28f, IosTextStyle.Title2 to 22f,
                IosTextStyle.Title3 to 20f, IosTextStyle.Headline to 17f, IosTextStyle.Body to 17f,
                IosTextStyle.Callout to 16f, IosTextStyle.Subheadline to 15f, IosTextStyle.Footnote to 13f,
                IosTextStyle.Caption to 12f, IosTextStyle.Caption2 to 11f,
            ),
            large,
        )
    }

    @Test
    fun noStyleShrinksAsTheCategoryGrows() {
        for (style in IosTextStyle.entries) {
            val sizes = DynamicTypeSize.entries.map(style::pointSize)
            assertEquals(style.name, sizes.sorted(), sizes)
        }
    }

    @Test
    fun stylesGrowByTheirOwnStepsRatherThanOneRatio() {
        // At accessibility1 body is 28/17 of Large, footnote 23/13 and caption2 20/11: no single scale gives all three.
        assertEquals(28f, IosTextStyle.Body.pointSize(Accessibility1))
        assertEquals(23f, IosTextStyle.Footnote.pointSize(Accessibility1))
        assertEquals(20f, IosTextStyle.Caption2.pointSize(Accessibility1))
        assertEquals(12f, IosTextStyle.Footnote.pointSize(XSmall))
        assertEquals(60f, IosTextStyle.LargeTitle.pointSize(Accessibility5))
    }
}
