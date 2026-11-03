package com.plhery.peekdeliverytracker

import android.app.Activity
import android.app.UiModeManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Looper
import android.provider.Settings
import android.view.ContextThemeWrapper
import android.view.WindowInsetsController
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.SignInCallbackActivity
import com.plhery.peekdeliverytracker.platform.SignInTabActivity
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.phoneIsDark
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The app's appearance in what the platform draws (A-S71), as the iPhone's window override reaches its edit menu: the
 * app's night mode follows the appearance (System the phone's, Light, Dark), set as the store is made and each time it
 * is picked; the app's theme takes it, the platform's own pieces with it, while the launch's window and the share
 * card's stay light, the launch's bars dark over it. Every window survives a uiMode change, the night mode's or the
 * phone's under System, without starting again: the configuration changes here are built by hand, and a device shows
 * the real ones. The share sheet keeps to the phone's appearance where the phone says it. On the app's own stores, at a
 * made-up address, every request turned away before it leaves.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class NightModeTest {
    private val app = ApplicationProvider.getApplicationContext<PeekApplication>()
    private val store = AppearanceStore.of(app)
    private val modes = app.getSystemService(UiModeManager::class.java)
    private val catalog = CarrierCatalog.shared
    private var made: AppContainer? = null
    private val windows = mutableListOf<ActivityController<*>>()

    @Before
    fun offline() {
        Settings.Global.putFloat(app.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 0f)
        app.shareDefaults = { MemoryDefaults() }
        val configuration = BuildSettings.configuration.copy(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://peek.invalid", supabaseUrl = null, supabasePublishableKey = "",
        )
        val http = OkHttpClient.Builder().addInterceptor { throw IOException("No network in this test") }.build()
        app.makeStores = { application -> withContext(Dispatchers.IO) { AppContainer(application, configuration, http = http) }.also { made = it } }
    }

    @After
    fun stop() {
        windows.forEach { it.close() }
        idle()
        made?.scope?.cancel()
        CarrierCatalog.install(catalog)
        store.set(AppAppearance.DEFAULT)
        Settings.Global.putFloat(app.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f)
    }

    private fun idle() = shadowOf(Looper.getMainLooper()).idle()

    private val applicationNightMode: Int get() = shadowOf(modes).applicationNightMode

    /** The phone's dark mode as the configuration says it, the app's night mode applied. */
    private fun night(context: Context, dark: Boolean) = Configuration(context.resources.configuration).apply {
        uiMode = (uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or if (dark) Configuration.UI_MODE_NIGHT_YES else Configuration.UI_MODE_NIGHT_NO
    }

    private fun isLightTheme(context: Context): Boolean = flag(context, android.R.attr.isLightTheme)

    private fun flag(context: Context, attribute: Int): Boolean =
        context.theme.obtainStyledAttributes(intArrayOf(attribute)).run { getBoolean(0, false).also { recycle() } }

    /** Whether the window's configuration says night: what its theme, and the platform's pieces, take. */
    private fun configuredNight(window: Activity): Boolean = window.resources.configuration.isNightModeActive

    /** Whether the window's bars have dark icons, for a light app. */
    private fun lightBars(window: Activity): Boolean {
        val appearance = checkNotNull(window.window.insetsController).systemBarsAppearance
        return appearance and WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS != 0
    }

    private fun main(): ActivityController<MainActivity> =
        Robolectric.buildActivity(MainActivity::class.java, Intent.makeMainActivity(ComponentName(app, MainActivity::class.java)))
            .also { windows += it }.setup().also { idle() }

    @Test
    fun theAppsNightModeFollowsTheAppearanceAsItIsPicked() {
        assertEquals(UiModeManager.MODE_NIGHT_AUTO, AppAppearance.System.nightMode)
        assertEquals(UiModeManager.MODE_NIGHT_NO, AppAppearance.Light.nightMode)
        assertEquals(UiModeManager.MODE_NIGHT_YES, AppAppearance.Dark.nightMode)
        store.set(AppAppearance.Dark)
        assertEquals(UiModeManager.MODE_NIGHT_YES, applicationNightMode)
        store.set(AppAppearance.System)
        assertEquals(UiModeManager.MODE_NIGHT_AUTO, applicationNightMode)
        store.set(AppAppearance.Light)
        assertEquals(UiModeManager.MODE_NIGHT_NO, applicationNightMode)
    }

    /** A pick saved before the night mode followed it, or brought back from a backup, is set again as the store is made. */
    @Test
    fun theStoreSetsTheSavedPickAgainAsItIsMade() {
        val preferences = app.getSharedPreferences("night-mode-test", Context.MODE_PRIVATE)
        preferences.edit().putString(AppAppearance.STORAGE_KEY, "dark").commit()
        val heard = mutableListOf<AppAppearance>()
        val made = AppearanceStore(preferences) { heard += it }
        assertEquals(listOf(AppAppearance.Dark), heard)
        made.set(AppAppearance.System)
        assertEquals(listOf(AppAppearance.Dark, AppAppearance.System), heard)
    }

    /**
     * The launch window's bars have dark icons over its light background in either night: the next cold start's takes
     * the app's night, which may be dark on a light phone, as iOS's launch screen stays light.
     */
    @Test
    fun theLaunchWindowsBarsStayDarkOverItsLightBackground() {
        for (dark in listOf(false, true)) {
            RuntimeEnvironment.setQualifiers(if (dark) "+night" else "+notnight")
            val starting = ContextThemeWrapper(app, R.style.Theme_Peek_Starting)
            assertTrue("The status bar's icons dark, night: $dark", flag(starting, android.R.attr.windowLightStatusBar))
            assertTrue("The navigation bar's icons dark, night: $dark", flag(starting, android.R.attr.windowLightNavigationBar))
        }
    }

    /**
     * X-AD15: the splash is iOS's blank launch (UILaunchScreen's LaunchBackground alone, one colour in either
     * appearance): the system's splash attributes, which Theme.SplashScreen hands its own to, flat #F4F5F1 in either
     * night, with an icon that draws nothing and no branding image.
     */
    @Test
    fun theSplashIsTheLaunchBackgroundAloneWithNoIconInEitherNight() {
        for (dark in listOf(false, true)) {
            RuntimeEnvironment.setQualifiers(if (dark) "+night" else "+notnight")
            val starting = ContextThemeWrapper(app, R.style.Theme_Peek_Starting)
            val splash = intArrayOf(
                android.R.attr.windowSplashScreenBackground,
                android.R.attr.windowSplashScreenAnimatedIcon,
                android.R.attr.windowSplashScreenBrandingImage,
            )
            starting.theme.obtainStyledAttributes(splash).run {
                assertEquals("The splash's background, night: $dark", 0xFFF4F5F1.toInt(), getColor(0, 0))
                val icon = getDrawable(1)!!
                val drawn = Bitmap.createBitmap(288, 288, Bitmap.Config.ARGB_8888)
                icon.setBounds(0, 0, drawn.width, drawn.height)
                icon.draw(Canvas(drawn))
                val pixels = IntArray(drawn.width * drawn.height).also { drawn.getPixels(it, 0, drawn.width, 0, 0, drawn.width, drawn.height) }
                assertTrue("The splash's icon draws nothing, night: $dark", pixels.all { Color.alpha(it) == 0 })
                assertEquals("No branding image, night: $dark", null, getDrawable(2))
                recycle()
            }
        }
    }

    @Test
    fun theAppsThemeTakesTheNightModeAndTheLaunchAndShareWindowsStayLight() {
        val launch = ContextCompat.getColor(app, R.color.launch_background)
        for (dark in listOf(false, true)) {
            RuntimeEnvironment.setQualifiers(if (dark) "+night" else "+notnight")
            val peek = ContextThemeWrapper(app, R.style.Theme_Peek)
            assertEquals("The app's theme dark: $dark", !dark, isLightTheme(peek))
            val background = peek.theme.obtainStyledAttributes(intArrayOf(android.R.attr.windowBackground)).run { getColor(0, 0).also { recycle() } }
            assertEquals("The window behind the app as iOS's LaunchBackground", launch, background)
            assertEquals(0xFFF4F5F1.toInt(), launch)
            assertTrue("The share card's window", isLightTheme(ContextThemeWrapper(app, R.style.Theme_Peek_Share)))
        }
    }

    /**
     * Dark picked, then Light: the window survives the uiMode change the night mode's brings, its theme (the platform's
     * pieces) and its bars as its configuration says.
     */
    @Test
    fun theWindowSurvivesAUiModeChange() {
        val window = main()
        val shown = window.get()
        assertFalse(configuredNight(shown))
        assertTrue(isLightTheme(shown))
        assertTrue(lightBars(shown))

        for ((appearance, dark) in listOf(AppAppearance.Dark to true, AppAppearance.Light to false)) {
            store.set(appearance)
            window.configurationChange(night(shown, dark))
            idle()
            assertSame("Not made again", shown, window.get())
            assertEquals("$appearance", dark, configuredNight(shown))
            assertEquals("$appearance: the theme as configured", !configuredNight(shown), isLightTheme(shown))
            assertEquals("$appearance: the bars as configured", !configuredNight(shown), lightBars(shown))
        }
    }

    /** System picked: the night mode is the phone's, and the window follows the phone as it goes dark and light. */
    @Test
    fun underSystemTheWindowFollowsThePhone() {
        val window = main()
        val shown = window.get()
        store.set(AppAppearance.System)
        assertEquals(UiModeManager.MODE_NIGHT_AUTO, applicationNightMode)
        for (dark in listOf(true, false, true)) {
            window.configurationChange(night(shown, dark))
            idle()
            assertSame(shown, window.get())
            assertEquals("The phone dark: $dark", !dark, isLightTheme(shown))
            assertEquals(!dark, lightBars(shown))
        }
    }

    /**
     * The share sheet dims the app that shared in the phone's appearance: the configuration's under System; the phone's
     * dark theme, set on or off, under Peek's own light or dark, which the configuration says then; the configuration's
     * for a schedule, which can't be read.
     */
    @Test
    fun theShareSheetKeepsToThePhonesAppearance() {
        store.set(AppAppearance.System)
        modes.nightMode = UiModeManager.MODE_NIGHT_NO
        assertTrue(phoneIsDark(app, configured = true))
        assertFalse(phoneIsDark(app, configured = false))

        store.set(AppAppearance.Dark)
        assertFalse(phoneIsDark(app, configured = true))
        modes.nightMode = UiModeManager.MODE_NIGHT_YES
        store.set(AppAppearance.Light)
        assertTrue(phoneIsDark(app, configured = false))
        modes.nightMode = UiModeManager.MODE_NIGHT_AUTO
        assertFalse(phoneIsDark(app, configured = false))

        // Peek dark on a light phone: the sheet's bars for the light app that shared.
        store.set(AppAppearance.Dark)
        modes.nightMode = UiModeManager.MODE_NIGHT_NO
        RuntimeEnvironment.setQualifiers("+night")
        val card = Robolectric.buildActivity(
            ShareActivity::class.java,
            Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, "TESTLAMP0001"),
        ).also { windows += it }.setup()
        idle()
        assertTrue(lightBars(card.get()))
        assertTrue(isLightTheme(card.get()))
    }

    /**
     * The share card survives a uiMode change, as when making the appearance store there sets Peek's night before the
     * app's window ever did, or the phone goes dark under System: the same card on the same share, its bars set again
     * for the phone.
     */
    @Test
    fun theShareCardSurvivesAUiModeChange() {
        store.set(AppAppearance.System)
        val share = Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, "TESTLAMP0001")
        val card = Robolectric.buildActivity(ShareActivity::class.java, share).also { windows += it }.setup()
        idle()
        val shown = card.get()
        assertTrue(lightBars(shown))
        card.configurationChange(night(shown, dark = true))
        idle()
        assertSame("Not made again", shown, card.get())
        assertSame(share, shown.intent)
        assertFalse("The bars for the phone gone dark", lightBars(shown))
        assertTrue("Its window light whatever the night", isLightTheme(shown))
    }

    /**
     * Every window of the app's, the notifications' alias as the window it stands for, takes a uiMode change itself:
     * none starts again, flashes or loses its intent when the night mode is first set or changes while it is up.
     */
    @Test
    fun everyWindowTakesAUiModeChangeItself() {
        val activities = app.packageManager.getPackageInfo(app.packageName, PackageManager.GET_ACTIVITIES).activities.orEmpty()
            .filter { it.name.startsWith("${app.packageName}.") }
        val names = activities.map { it.name }
        for (window in listOf(MainActivity::class, ShareActivity::class, ParcelLinkActivity::class, SignInTabActivity::class, SignInCallbackActivity::class)) {
            assertTrue("${window.java.name} listed", window.java.name in names)
        }
        val alias = app.packageManager.getActivityInfo(ComponentName(app, "${app.packageName}.NotificationOpening"), 0)
        for (info in activities + alias) {
            assertTrue("${info.name} takes it itself", info.configChanges and ActivityInfo.CONFIG_UI_MODE != 0)
        }
    }
}
