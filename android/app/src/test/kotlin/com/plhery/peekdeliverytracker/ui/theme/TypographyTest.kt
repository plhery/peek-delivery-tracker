package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment

@RunWith(AndroidJUnit4::class)
class TypographyTest {
    @get:Rule
    val compose = createComposeRule()

    @After
    fun resetFontScale() {
        RuntimeEnvironment.setFontScale(1f)
    }

    /** What the theme gives inside, at the phone's font scale. */
    private class Seen(var size: DynamicTypeSize? = null, var body: Float = 0f, var footnote: Float = 0f, var fixed: Float = 0f)

    @Composable
    private fun Record(seen: Seen) {
        val density = LocalDensity.current
        seen.size = PeekTheme.dynamicTypeSize
        seen.body = dp(PeekTheme.type.body, density)
        seen.footnote = dp(PeekTheme.type.footnote, density)
        seen.fixed = dp(PeekTheme.type.fixed(13f), density)
    }

    private fun dp(style: TextStyle, density: Density): Float = with(density) { style.fontSize.toDp().value }

    private fun themed(fontScale: Float, content: @Composable () -> Unit) {
        RuntimeEnvironment.setFontScale(fontScale)
        compose.setContent { PeekTheme(highContrast = false, reduceMotion = false, carrierIdentities = null, content = content) }
        compose.waitForIdle()
    }

    @Test
    fun atTheDefaultScaleTextIsTheIPhonesLargeSizesInDp() {
        val seen = Seen()
        themed(1f) { Record(seen) }
        assertEquals(DynamicTypeSize.Large, seen.size)
        assertEquals(17f, seen.body, 0.01f)
        assertEquals(13f, seen.footnote, 0.01f)
        assertEquals(13f, seen.fixed, 0.01f)
    }

    @Test
    fun atTwiceTheScaleTextIsAccessibility2NotTwiceLarge() {
        val seen = Seen()
        themed(2f) { Record(seen) }
        assertEquals(DynamicTypeSize.Accessibility2, seen.size)
        // Drawn at the iPhone's sizes in dp whatever Android 14's nonlinear scaling would make of the sp.
        assertEquals(33f, seen.body, 0.01f)
        assertEquals(27f, seen.footnote, 0.01f)
        // `.system(size:)` stays put.
        assertEquals(13f, seen.fixed, 0.01f)
    }

    @Test
    fun aCapHoldsItsContentAtTheCap() {
        val outside = Seen()
        val inside = Seen()
        themed(1.8f) {
            Record(outside)
            CappedDynamicTypeSize(DynamicTypeSize.Accessibility1) { Record(inside) }
        }
        assertEquals(DynamicTypeSize.Accessibility2, outside.size)
        assertEquals(DynamicTypeSize.Accessibility1, inside.size)
        assertEquals(28f, inside.body, 0.01f)
    }

    @Test
    fun aCapAboveTheCategoryChangesNothing() {
        val inside = Seen()
        themed(1.15f) { CappedDynamicTypeSize(DynamicTypeSize.XXLarge) { Record(inside) } }
        assertEquals(DynamicTypeSize.XLarge, inside.size)
        assertEquals(19f, inside.body, 0.01f)
    }

    @Test
    fun scaledMetricsGrowWithTheirStyle() {
        val type = PeekTypography(DynamicTypeSize.Accessibility2, Density(2f))
        assertEquals(26f * 43 / 28, type.scaled(26f, relativeTo = IosTextStyle.Title), 0.001f)
        assertEquals(16f * 30 / 15, type.scaled(16f, relativeTo = IosTextStyle.Subheadline), 0.001f)
        assertEquals(10f, PeekTypography(DynamicTypeSize.Large, Density(2f)).scaled(10f, IosTextStyle.Caption2), 0.001f)
    }

    @Test
    fun stylesCarryTheirWeightAndDesign() {
        val type = PeekTypography(DynamicTypeSize.Large, Density(1f))
        assertEquals(FontWeight.SemiBold, type.headline.fontWeight)
        assertEquals(FontWeight.Normal, type.body.fontWeight)
        val title = type.style(IosTextStyle.Title, weight = FontWeight.Bold, design = FontDesign.Rounded)
        assertEquals(FontWeight.Bold, title.fontWeight)
        assertEquals(FontFamily.Default, title.fontFamily)
        assertEquals(FontFamily.Monospace, type.style(IosTextStyle.Body, design = FontDesign.Monospaced).fontFamily)
    }

    @Test
    fun monospacedDigitsAskForTabularFigures() {
        assertEquals("tnum", TextStyle().monospacedDigit().fontFeatureSettings)
        assertEquals("smcp, tnum", TextStyle(fontFeatureSettings = "smcp").monospacedDigit().fontFeatureSettings)
    }
}
