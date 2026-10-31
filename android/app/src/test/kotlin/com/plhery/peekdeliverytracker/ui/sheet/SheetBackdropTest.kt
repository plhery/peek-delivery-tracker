package com.plhery.peekdeliverytracker.ui.sheet

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.TabOverlay
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import kotlin.math.abs

/**
 * What stands over a sheet, an alert in its menu layer, blurs the sheet and the tab under it, as an alert's glass shows
 * both on the iPhone; the sheet's own glass goes on blurring the tab alone, and so does the tab bar's, which the sheet
 * and its dim come over.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class SheetBackdropTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun anAlertOverTheSheetShowsTheSheetAndTheTabThroughItsGlass() {
        val shot = capture()
        val scale = shot.width / 390f
        val panel = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot()
        fun at(x: Float, y: Float) = rgb(shot.getPixel((x * scale).toInt(), (y * scale).toInt()))

        // Above the sheet, the alert's glass shows the tab, yellow under the sheet's dim, rather than a solid face.
        val overTheTab = at(100f, 400f)
        assertTrue("over the tab $overTheTab", overTheTab[0] - overTheTab[2] > 100)
        // Over the sheet it shows the sheet's blue, not the tab under the sheet.
        val overTheSheet = at(100f, panel.top.value + 200f)
        assertTrue("over the sheet $overTheSheet", overTheSheet[2] - overTheSheet[0] > 60)
        // Beside the alert, the sheet's own glass, above what it holds, shows the tab rather than itself.
        val sheetGlass = at(300f, panel.top.value + 50f)
        assertTrue("the sheet's glass $sheetGlass", sheetGlass[0] - sheetGlass[2] > 60)
        // The tab bar's glass is as the same glass over the tab alone: the sheet's dim lies over it once.
        val bar = at(95f, 130f)
        val alone = at(295f, 130f)
        assertTrue("the tab bar $bar, the tab alone $alone", bar.indices.all { abs(bar[it] - alone[it]) <= 3 })
    }

    /**
     * A yellow tab, a tab bar's glass and the same glass over a backdrop holding only the tab, then a sheet 300 high
     * holding blue under its own glass's top, and a glass in the sheet's menu layer over the lower left of the window.
     * The glass draws only in hardware, so the window is drawn again until two captures agree.
     */
    private fun capture(): Bitmap {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = true, reduceTransparency = false, carrierIdentities = null) {
                val backdrop = rememberMaterialBackdrop()
                val tabAlone = rememberMaterialBackdrop()
                val overlay = remember(backdrop) { TabOverlay(backdrop) }
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    Box(Modifier.fillMaxSize().materialBackdrop(backdrop).materialBackdrop(tabAlone).background(YELLOW)) {
                        CompositionLocalProvider(LocalTabOverlay provides overlay) {
                            Sheet(visible = true, onDismiss = {}, paneTitle = "Sheet", detents = listOf(SheetDetent.Height(300.dp))) {
                                Column(Modifier.fillMaxSize()) {
                                    Spacer(Modifier.height(100.dp))
                                    Box(Modifier.fillMaxWidth().weight(1f).background(BLUE))
                                }
                                val menus = LocalSheetMenus.current
                                DisposableEffect(menus) {
                                    menus?.content = {
                                        GlassSurface(RectangleShape, Modifier.offset(0.dp, 300.dp).size(195.dp, 544.dp)) {}
                                    }
                                    onDispose { menus?.content = null }
                                }
                            }
                        }
                    }
                    GlassSurface(RectangleShape, Modifier.offset(20.dp, 100.dp).size(150.dp, 60.dp), backdrop = backdrop) {}
                    GlassSurface(RectangleShape, Modifier.offset(220.dp, 100.dp).size(150.dp, 60.dp), backdrop = tabAlone) {}
                    overlay.Content()
                }
            }
        }
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        var previous: Bitmap? = null
        repeat(6) {
            compose.runOnUiThread {
                view.rootView.invalidate()
                view.invalidate()
            }
            compose.waitForIdle()
            val bitmap = compose.onNodeWithTag(SHOT).captureToImage().asAndroidBitmap()
            if (previous?.sameAs(bitmap) == true) return bitmap
            previous = bitmap
        }
        error("Two captures in a row never agreed")
    }

    private fun rgb(pixel: Int) = listOf((pixel shr 16) and 0xFF, (pixel shr 8) and 0xFF, pixel and 0xFF)

    private companion object {
        val YELLOW = Color(1f, 0.8f, 0f)
        val BLUE = Color(0f, 0.3f, 1f)
    }
}
