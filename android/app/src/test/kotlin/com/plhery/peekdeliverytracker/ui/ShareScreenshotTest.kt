package com.plhery.peekdeliverytracker.ui

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.SharedContent
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlinx.coroutines.awaitCancellation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The share card over the app that shared (B-S70, B-ST36): reading, ready, nothing found, saved and a save that failed
 * on a phone with the iPhone's bars; ready in dark, the same card on a darker dim; the form sheet in the iPad's window;
 * and the largest text. The app that shared is a plain page in the phone's appearance. On a synthetic number.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ShareScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    private val read = SharedContent.Read("Desk lamp", "Your parcel TESTLAMP0001 is on its way")
    private lateinit var view: View

    private fun show(phase: SharePhase, dark: Boolean = false, iPad: Boolean = false, fontScale: Float = 1f) {
        val card = ShareCard(phase, read.takeIf { phase != SharePhase.Reading && phase != SharePhase.NotFound })
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().background(if (dark) Color.Black else Color.White).testTag(SHOT)) {
                    PeekTheme(dark = dark, carrierIdentities = null) {
                        ShareSheet(ExtensionLocalizer("en"), read = { awaitCancellation() }, save = { true }, onClosed = {}, card = card)
                    }
                }
            }
        }
        compose.waitForIdle()
        if (iPad) bars(status = 32, bottom = 20) else bars(status = 62, bottom = 24)
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** The status bar `status` points tall and the bottom bar `bottom`: the iPhone's 62 and 24, the iPad's 32 and 20. */
    private fun bars(status: Int, bottom: Int) {
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            fun px(points: Int) = (points * scale).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(status), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, px(bottom)))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    @Test
    fun reading() {
        show(SharePhase.Reading)
        compose.screenshot("share_reading_phone_light")
    }

    @Test
    fun ready() {
        show(SharePhase.Ready)
        compose.screenshot("share_ready_phone_light")
    }

    @Test
    fun readyInDark() {
        show(SharePhase.Ready, dark = true)
        compose.screenshot("share_ready_phone_dark")
    }

    @Test
    fun notFound() {
        show(SharePhase.NotFound)
        compose.screenshot("share_not_found_phone_light")
    }

    @Test
    fun saved() {
        show(SharePhase.Saved)
        compose.screenshot("share_saved_phone_light")
    }

    @Test
    fun saveFailed() {
        show(SharePhase.SaveFailed)
        compose.screenshot("share_save_failed_phone_light")
    }

    @Test
    @Config(qualifiers = TABLET)
    fun readyInARegularWindow() {
        show(SharePhase.Ready, iPad = true)
        compose.screenshot("share_ready_tablet_light")
    }

    @Test
    fun readyAtTheLargestTextSize() {
        show(SharePhase.Ready, fontScale = 2f)
        compose.screenshot("share_ready_phone_text_largest")
    }
}

/** The iPad's window, 1032 × 1376 points upright, at twice the pixels. */
private const val TABLET = "w1032dp-h1376dp-xhdpi"
