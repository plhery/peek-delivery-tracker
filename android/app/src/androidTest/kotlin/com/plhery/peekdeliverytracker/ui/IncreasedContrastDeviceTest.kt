package com.plhery.peekdeliverytracker.ui

import android.app.Activity
import android.app.UiModeManager
import android.graphics.Rect
import android.os.Build
import android.os.ParcelFileDescriptor
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleCallback
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.theme.PeekColors
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.abs
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Increase Contrast (A-ST33): from Android 14 the system's contrast above standard is iOS's Increase Contrast, which
 * DesignSystem.swift's dynamic colours answer by moving each channel of the brand's colours 0.06 further from middle
 * grey (`Brand.color`). The app's theme reads UiModeManager's contrast as its window is made and listens to it after.
 * A change of contrast has the system lay its theme over every app again, which makes their windows anew, so each test
 * sets the contrast first, lets the window be made again, and then shows the list. Android 12 and 13 have no such
 * setting and keep the standard colours. Read from the window's own pixels: the ground beside the empty list
 * (`Brand.background`), light and dark, against [PeekColors] at each contrast; the colours themselves are
 * PeekColorsTest's. The contrast is set by the shell as Settings' Contrast sets it (`contrast_level`, medium at 0.5)
 * and always put back as it was. Signed in as the synthetic reader@example.com on fake deliveries ([ParcelWalk]) whose
 * stores answer every read in memory: nothing goes to the network. Each moment saved as contrast/A-ST33-<moment>.png
 * in the test package's external files, under Reduce Motion.
 */
@RunWith(AndroidJUnit4::class)
class IncreasedContrastDeviceTest {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /**
     * A-ST33: from Android 14, with the system's contrast at medium, the list's ground is Increase Contrast's, white in
     * light and nearer black in dark. Before Android 14 it is the standard's.
     */
    @Test
    fun atTheMediumContrastTheListTakesIncreaseContrastsColoursFromAndroid14() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            walk.followsContrast = true
            walk.show(emptyList())
            assertGround(highContrast = false, "Before Android 14", moment = "before-android-14")
            return
        }
        atContrast(MEDIUM) {
            walk.followsContrast = true
            walk.show(emptyList())
            assertGround(highContrast = true, "At the medium contrast", moment = "medium")
        }
    }

    /** A-ST33: at the standard contrast the list's ground is the brand's own, light and dark. */
    @Test
    fun atTheStandardContrastTheListKeepsTheBrandsColours() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE) return
        atContrast(STANDARD) {
            walk.followsContrast = true
            walk.show(emptyList())
            assertGround(highContrast = false, "At the standard contrast", moment = "standard")
        }
    }

    /** `check` run with the system's contrast at `level`, the window made again for it; the phone's put back after. */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun atContrast(level: String, check: () -> Unit) {
        val modes = checkNotNull(instrumentation.targetContext.getSystemService(UiModeManager::class.java))
        val was = shell("settings get secure $CONTRAST_LEVEL")
        try {
            contrast(modes, level)
            check()
        } finally {
            settled { restore(was) }
        }
    }

    /**
     * The system's contrast at `level`, as Settings' Contrast puts it, once UiModeManager reads it back and the window
     * made again for it stands still: a device that doesn't take it skips the test, its contrast put back all the same.
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun contrast(modes: UiModeManager, level: String) {
        val target = level.toFloat()
        settled {
            shell("settings put secure $CONTRAST_LEVEL $level")
            val taken = runCatching { compose.waitUntil(LONG_MILLIS) { abs(modes.contrast - target) < LEVEL_TOLERANCE } }.isSuccess
            assumeTrue("The system didn't take the contrast level $level the shell set", taken)
        }
    }

    /**
     * `change` made, then a wait until the test's activity is resumed and none has moved through its life for
     * [STILL_MILLIS]: the windows the system makes anew after a change of contrast are made by then.
     */
    private fun settled(change: () -> Unit) {
        val monitor = ActivityLifecycleMonitorRegistry.getInstance()
        val moved = AtomicLong(SystemClock.uptimeMillis())
        val callback = ActivityLifecycleCallback { _: Activity, _: Stage -> moved.set(SystemClock.uptimeMillis()) }
        instrumentation.runOnMainSync { monitor.addLifecycleCallback(callback) }
        try {
            change()
            moved.accumulateAndGet(SystemClock.uptimeMillis()) { one, other -> maxOf(one, other) }
            val deadline = SystemClock.uptimeMillis() + LONG_MILLIS
            while (SystemClock.uptimeMillis() < deadline) {
                val resumed = walk.onUi { monitor.getActivitiesInStage(Stage.RESUMED).any { it is ComponentActivity } }
                if (resumed && SystemClock.uptimeMillis() - moved.get() >= STILL_MILLIS) break
                Thread.sleep(POLL_MILLIS)
            }
        } finally {
            instrumentation.runOnMainSync { monitor.removeLifecycleCallback(callback) }
        }
    }

    /**
     * The list's ground, light then dark, within [TOLERANCE] of the brand's at `highContrast`; each saved as
     * A-ST33-`moment` where one is named. The walk's light is put back after.
     */
    private fun assertGround(highContrast: Boolean, what: String, moment: String? = null) {
        try {
            for (dark in listOf(false, true)) {
                walk.dark = dark
                walk.recompose()
                val appearance = if (dark) "dark" else "light"
                val expected = PeekColors(dark, highContrast).resolve(Brand.background).toArgb()
                val deadline = SystemClock.uptimeMillis() + LONG_MILLIS
                var drawn = ground()
                while (apart(drawn, expected) > TOLERANCE && SystemClock.uptimeMillis() < deadline) {
                    Thread.sleep(POLL_MILLIS)
                    drawn = ground()
                }
                assertTrue("$what, $appearance: the ground ${hex(drawn)}, ${hex(expected)} expected", apart(drawn, expected) <= TOLERANCE)
                if (moment != null) walk.shot(if (dark) "A-ST33-$moment-dark" else "A-ST33-$moment", alsoDark = false)
            }
        } finally {
            walk.dark = false
            walk.recompose()
        }
    }

    /**
     * The colour most of a strip of the list's ground shows in the window as drawn now: 2 to 10 in from the window's
     * safe leading edge, clear of the list's 24-point margin, down the middle fifth of its height, clear of its bars.
     */
    private fun ground(): Int {
        val image = compose.onRoot().captureToImage().asAndroidBitmap()
        val scale = compose.density.density
        val start = walk.onUi {
            val insets = ViewCompat.getRootWindowInsets(compose.activity.window.decorView)
            insets?.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())?.left ?: 0
        }
        val area = Rect(start + (2 * scale).toInt(), image.height * 2 / 5, start + (10 * scale).toInt(), image.height * 3 / 5)
        val pixels = IntArray(area.width() * area.height())
        image.getPixels(pixels, 0, area.width(), area.left, area.top, area.width(), area.height())
        return pixels.asList().groupingBy { it }.eachCount().maxBy { it.value }.key
    }

    /** How far apart two opaque pixels are, in the channel they differ most in, out of 255. */
    private fun apart(one: Int, other: Int): Int =
        listOf(16, 8, 0).maxOf { shift -> abs((one shr shift and 0xFF) - (other shr shift and 0xFF)) }

    private fun hex(color: Int): String = "#%06X".format(color and 0xFFFFFF)

    /** What the shell prints for `command`, run as the test's instrumentation runs it, once it has ended. */
    private fun shell(command: String): String =
        ParcelFileDescriptor.AutoCloseInputStream(instrumentation.uiAutomation.executeShellCommand(command)).use { it.readBytes().decodeToString().trim() }

    /** The contrast put back to `level`, as `settings get` printed it ("null" where it was unset). */
    private fun restore(level: String) {
        shell(if (level == "null" || level.isEmpty()) "settings delete secure $CONTRAST_LEVEL" else "settings put secure $CONTRAST_LEVEL $level")
    }

    private companion object {
        const val FOLDER = "contrast"

        /** `Settings.Secure.CONTRAST_LEVEL`, which the SDK keeps hidden: what UiModeManager's contrast reads. */
        const val CONTRAST_LEVEL = "contrast_level"

        /** Settings' Contrast: standard, and medium, the least above it. */
        const val STANDARD = "0.0"
        const val MEDIUM = "0.5"

        /** How near UiModeManager's contrast is to the level set once it has taken it. */
        const val LEVEL_TOLERANCE = 0.01f

        /**
         * How far a channel of the ground drawn may stray from the brand's, out of 255: Increase Contrast moves the
         * light ground's channels 10 to 14 (to white) and the dark one's 14 to 15.
         */
        const val TOLERANCE = 3

        /** How long between two looks at the ground, or at the activities' life. */
        const val POLL_MILLIS = 50L

        /** How long no activity moves through its life before the window counts as made for the contrast. */
        const val STILL_MILLIS = 1_500L
    }
}
