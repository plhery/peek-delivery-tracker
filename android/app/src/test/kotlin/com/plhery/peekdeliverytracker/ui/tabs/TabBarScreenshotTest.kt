package com.plhery.peekdeliverytracker.ui.tabs

import android.app.Activity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.window.layout.FoldingFeature.Orientation
import androidx.window.layout.FoldingFeature.State
import androidx.window.testing.layout.FoldingFeature
import androidx.window.testing.layout.TestWindowLayoutInfo
import androidx.window.testing.layout.WindowLayoutInfoPublisherRule
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The tab bar over a page laid out as the iPhone's list is behind it on its screenshots: a card of the map above the
 * bar's middle, the page, and the next card from just above its bottom edge.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class TabBarScreenshotTest {
    private val compose = createComposeRule()
    private val fold = WindowLayoutInfoPublisherRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(fold).around(compose)

    @Test
    @Config(qualifiers = PHONE)
    fun englishLight() = bottom("tabs_en_light", AppLanguage.En, dark = false)

    @Test
    @Config(qualifiers = PHONE)
    fun englishDark() = bottom("tabs_en_dark", AppLanguage.En, dark = true)

    @Test
    @Config(qualifiers = PHONE)
    fun germanLight() = bottom("tabs_de_light", AppLanguage.De, dark = false)

    @Test
    @Config(qualifiers = PHONE)
    fun germanDark() = bottom("tabs_de_dark", AppLanguage.De, dark = true)

    /** At twice the text size the bar is as it is at the default, as UIKit keeps a tab bar's size; the page grows. */
    @Test
    @Config(qualifiers = PHONE)
    fun englishLightLargeText() = bottom("tabs_en_light_text2", AppLanguage.En, dark = false, fontScale = 2f)

    @Test
    @Config(qualifiers = PHONE)
    fun englishDarkLargeText() = bottom("tabs_en_dark_text2", AppLanguage.En, dark = true, fontScale = 2f)

    @Test
    @Config(qualifiers = PHONE)
    fun germanLightLargeText() = bottom("tabs_de_light_text2", AppLanguage.De, dark = false, fontScale = 2f)

    @Test
    @Config(qualifiers = PHONE)
    fun germanDarkLargeText() = bottom("tabs_de_dark_text2", AppLanguage.De, dark = true, fontScale = 2f)

    private fun bottom(name: String, language: AppLanguage, dark: Boolean, fontScale: Float = 1f) {
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                RootHost(language = language, dark = dark, deliveries = { Page(dark) })
            }
        }
        compose.screenshot(name)
    }

    /** The bar at the top of a 13-inch tablet's window, at the iPad's size and scale. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun tablet() {
        compose.setContent { RootHost(deliveries = { TopPage(1032) }) }
        compose.screenshot("tabs_top_tablet")
    }

    /** An unfolded foldable in book posture: the bar keeps to the half before the hinge. */
    @Test
    @Config(qualifiers = "w700dp-h840dp-xhdpi")
    fun foldable() {
        var activity: Activity? = null
        compose.setContent {
            activity = LocalContext.current as Activity
            RootHost(deliveries = { TopPage(700) })
        }
        compose.waitForIdle()
        val hinge = FoldingFeature(requireNotNull(activity), center = 700, size = 0, state = State.HALF_OPENED, orientation = Orientation.VERTICAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        compose.screenshot("tabs_top_foldable")
    }

    /** The page behind the bottom bar, as on the iPhone's screenshots, and the strip of it the shot keeps. */
    @Composable
    private fun Page(dark: Boolean) {
        val type = PeekTheme.type
        Box(Modifier.fillMaxSize()) {
            val barTop = 21f + 62f
            Canvas(Modifier.fillMaxSize()) {
                val top = size.height - barTop.dp.toPx()
                drawRect(if (dark) Color(0xFF293E57) else Color(0xFFDFEBFA), Offset.Zero, Size(size.width, top + 43.67.dp.toPx()))
                val next = top + 55.33.dp.toPx()
                drawRect(if (dark) Color(0xFF1A402E) else Color(0xFFDBF3EA), Offset(0f, next), Size(size.width, size.height - next))
            }
            val ink = PeekTheme.colors.ink
            BasicText(
                "Desk lamp",
                Modifier.align(Alignment.BottomStart).offset(x = 20.dp, y = (-(barTop - 6)).dp),
                style = type.title.copy(color = ink, fontWeight = FontWeight.Bold),
            )
            BasicText(
                "Swiss Post",
                Modifier.align(Alignment.BottomStart).offset(x = 20.dp, y = 8.dp),
                style = type.title3.copy(color = if (dark) Color(0xFF87D1B5) else Color(0xFF1D7A55), fontWeight = FontWeight.Bold),
            )
            Shot(Alignment.BottomCenter, 120)
        }
    }

    /** The page behind the top bar: a few rows of the list's text under it, and the strip the shot keeps. */
    @Composable
    private fun TopPage(width: Int) {
        val style = PeekTheme.type.body.copy(color = PeekTheme.colors.label)
        Box(Modifier.fillMaxSize()) {
            for (row in 0 until 4) {
                BasicText("Parcel ${row + 1} · on its way", Modifier.offset(x = (width / 3 - 40).dp, y = (14 + row * 22).dp), style = style)
            }
            Shot(Alignment.TopCenter, 72)
        }
    }

    @Composable
    private fun BoxScope.Shot(alignment: Alignment, height: Int) {
        Box(Modifier.align(alignment).fillMaxWidth().height(height.dp).testTag(SHOT))
    }
}
