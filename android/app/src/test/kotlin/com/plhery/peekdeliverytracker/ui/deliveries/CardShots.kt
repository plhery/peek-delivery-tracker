package com.plhery.peekdeliverytracker.ui.deliveries

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.Size
import com.plhery.peekdeliverytracker.core.WorldDetailPack
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.ProvideLocalizer
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.map.MapScene
import com.plhery.peekdeliverytracker.ui.map.WorldDetailPacks
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

/** The iPhone the list's captures were taken on: 440 points wide at three pixels to the point. */
const val CARD_PHONE = "w440dp-h956dp-xxhdpi"

/**
 * The pixels of `node`, drawn now in software, as the app's other pixel tests draw theirs: run with the whole suite,
 * `captureToImage` has given two different nodes the same picture.
 */
fun ComposeContentTestRule.drawn(node: SemanticsNodeInteraction): IntArray {
    val root = (onRoot().fetchSemanticsNode().root as ViewRootForTest).view
    val bounds = node.fetchSemanticsNode().boundsInRoot
    val whole = Bitmap.createBitmap(root.width, root.height, Bitmap.Config.ARGB_8888)
    runOnUiThread { root.draw(Canvas(whole)) }
    val pixels = IntArray(bounds.width.toInt() * bounds.height.toInt())
    whole.getPixels(pixels, 0, bounds.width.toInt(), bounds.left.toInt(), bounds.top.toInt(), bounds.width.toInt(), bounds.height.toInt())
    return pixels
}

/**
 * The opened map's last picture (`drawn`, heard through `LocalMapSceneWatch`), once the window is idle: it drew every
 * detail tile its camera shows, and some, so a shot of it close up never catches a tile on its way. Its span across
 * its shorter side, in kilometres.
 */
internal fun ComposeContentTestRule.everyTileDrawn(drawn: () -> MapScene?): Double {
    waitForIdle()
    val scene = checkNotNull(drawn())
    val pack = checkNotNull(runBlocking { WorldDetailPacks.load(ApplicationProvider.getApplicationContext()).await() })
    val shown = onNodeWithTag(MAP_COVER_MAP).fetchSemanticsNode().size
    val scale = density.density.toDouble()
    val size = Size(shown.width / scale, shown.height / scale)
    val keys = pack.keys(scene.camera, size)
    assertTrue("No tile in view", keys.isNotEmpty())
    assertEquals("Tiles drawn of $keys", keys.size, scene.tiles)
    return WorldDetailPack.spanKilometres(scene.camera, size)
}

/**
 * `content` as the list shows it, in the app's copy for `language` (or `localizer`'s, whose language a test may change),
 * on the list's background with 16 points either side: the iPhone's cards are 408 points wide on its 440. Under Reduce
 * Motion, so each shot shows the card at rest.
 */
@Composable
fun CardHost(
    language: AppLanguage = AppLanguage.En,
    dark: Boolean = false,
    fontScale: Float = 1f,
    highContrast: Boolean = false,
    reduceMotion: Boolean = true,
    localizer: Localizer = remember(language) { Localizer(language, variant = "android") },
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalNow provides { CAPTURED }) {
        PeekTheme(
            dark = dark, highContrast = highContrast, reduceMotion = reduceMotion, reduceTransparency = false,
            carrierIdentities = DemoCards.identities,
        ) {
            ProvideLocalizer(localizer) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(PeekTheme.colors.background)
                        .testTag(SHOT)
                        .padding(16.dp),
                ) {
                    content()
                }
            }
        }
    }
}
