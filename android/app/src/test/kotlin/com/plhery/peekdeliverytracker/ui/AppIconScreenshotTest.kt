package com.plhery.peekdeliverytracker.ui

import android.content.Context
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.R
import kotlin.math.roundToInt
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The app's icon (X-AD14) at a launcher's size on a phone, as launchers mask it: under a circle, Android's own mask, and
 * a squircle; and the eyes alone tinted as a themed icon, in a light launcher and a dark one.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w390dp-h844dp-xxhdpi")
class AppIconScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    private val app = ApplicationProvider.getApplicationContext<Context>()
    private val icon = app.getDrawable(R.mipmap.ic_launcher) as AdaptiveIconDrawable

    private fun shoot(name: String, content: @Composable () -> Unit) {
        compose.setContent { Row(Modifier.testTag(SHOT)) { content() } }
        compose.screenshot("app_icon_$name")
    }

    @Test
    fun circle() = shoot("circle") { OnWallpaper(Color.White) { LauncherIcon(listOf(icon.background, icon.foreground), CircleShape) } }

    @Test
    fun squircle() = shoot("squircle") { OnWallpaper(Color.White) { LauncherIcon(listOf(icon.background, icon.foreground), SQUIRCLE) } }

    /**
     * The eyes alone, tinted as Launcher3 tints themed icons: system_neutral2_700 on system_accent1_100 in light,
     * system_accent1_100 on system_neutral1_800 in dark. Themed icons came with Android 13, which these tests run on or
     * after.
     */
    @Test
    fun themed() {
        val monochrome = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) icon.monochrome else null
        fun themed(background: Int, foreground: Int) =
            listOf(ColorDrawable(app.getColor(background)), monochrome!!.constantState!!.newDrawable().mutate().apply { setTint(app.getColor(foreground)) })
        val light = themed(android.R.color.system_accent1_100, android.R.color.system_neutral2_700)
        val dark = themed(android.R.color.system_neutral1_800, android.R.color.system_accent1_100)
        shoot("themed") {
            OnWallpaper(Color.White) { LauncherIcon(light, CircleShape) }
            OnWallpaper(Color.Black) { LauncherIcon(dark, CircleShape) }
        }
    }
}

@Composable
private fun OnWallpaper(color: Color, content: @Composable () -> Unit) {
    Box(Modifier.background(color).padding(12.dp)) { content() }
}

/**
 * [layers] 48 dp wide under [mask], as AdaptiveIconDrawable draws them: on a canvas half as wide again, of which the
 * mask shows the middle, 72 of the 108 dp.
 */
@Composable
private fun LauncherIcon(layers: List<Drawable>, mask: Shape) {
    Canvas(Modifier.size(48.dp).clip(mask)) {
        val inset = (size.width / 4).roundToInt()
        val side = size.width.roundToInt()
        drawIntoCanvas { canvas ->
            for (layer in layers) {
                layer.setBounds(-inset, -inset, side + inset, side + inset)
                layer.draw(canvas.nativeCanvas)
            }
        }
    }
}

/** AOSP's squircle icon shape (IconShapeSquircleOverlay), a 100-unit path. */
private val SQUIRCLE = GenericShape { size, _ ->
    val unit = size.width / 100
    moveTo(50 * unit, 0f)
    cubicTo(10 * unit, 0f, 0f, 10 * unit, 0f, 50 * unit)
    cubicTo(0f, 90 * unit, 10 * unit, 100 * unit, 50 * unit, 100 * unit)
    cubicTo(90 * unit, 100 * unit, 100 * unit, 90 * unit, 100 * unit, 50 * unit)
    cubicTo(100 * unit, 10 * unit, 90 * unit, 0f, 50 * unit, 0f)
    close()
}
