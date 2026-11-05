package com.plhery.peekdeliverytracker

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyboardShortcutGroup
import android.view.Menu
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ShareInbox
import com.plhery.peekdeliverytracker.platform.LauncherShortcuts
import com.plhery.peekdeliverytracker.ui.LAUNCH_SLOW_MILLIS
import com.plhery.peekdeliverytracker.ui.LocalWindowShortcuts
import com.plhery.peekdeliverytracker.ui.PeekRoot
import com.plhery.peekdeliverytracker.ui.WindowShortcuts
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.appearanceSetting
import com.plhery.peekdeliverytracker.ui.theme.rememberCarrierIdentities
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private val app: PeekApplication get() = application as PeekApplication

    /** The keys the window answers now, which its screens say as they are composed. */
    private val shortcuts = WindowShortcuts()

    /**
     * The window has been laid out once, the system's insets given to Compose before that: the screens are composed
     * from then on, and their first composition reads the insets as they are rather than none.
     */
    private var laidOut by mutableStateOf(false)

    /**
     * The window's first screen is composed as it is to be drawn: the one the session leads to (or the launch screen,
     * once a slow session read has it say so), with the window's insets and the carriers. The launch window stays until
     * then, as the iPhone's launch screen stays until its first frame, which is laid out as it stays.
     */
    private var composed = false

    /** When the launch window first found the stores made, in [SystemClock.uptimeMillis]; 0 until then. */
    private var madeAt = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        // The launch window: iOS's blank LaunchBackground, no icon (themes.xml), kept while the stores are made, then
        // until the first screen is composed ([composed]) for LAUNCH_HOLD_MILLIS at most.
        val splash = installSplashScreen()
        val systemDark = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        systemBars(dark = AppearanceStore.of(this).appearance.value.isDark(systemDark))
        super.onCreate(savedInstanceState)
        // Notifications are asked for from the window on screen, a window made again included, and web sign-in opens
        // over it.
        app.notificationPermission.attach(this)
        app.signInTab.attach(this)
        // The stores are made with the first window, as the iPhone's are with its app.
        app.makeContainer()
        // The links that come to this window. A parcel's link comes by ParcelLinkActivity, in memory; one aimed at the
        // window itself still goes first, to the app's stores. What another app shared waits where ShareActivity saved it.
        // A notification opened brings its push's data, counted as the iPhone counts it. A link the app has just
        // followed, here or by ParcelLinkActivity, isn't followed again. A shortcut on the app's icon is reported used.
        val inbox = DeliveriesInbox.of(
            this, parcelLink = app::openParcelLink, sharedDrafts = { ShareInbox.consume(app.shareDefaults()) }, invitationLink = app::openInvitation,
            opened = app::linkOpened, notificationOpened = app::notificationOpened, repeated = app::isRepeat,
            shortcutUsed = { LauncherShortcuts.used(this, it) },
        )
        splash.setKeepOnScreenCondition {
            val now = SystemClock.uptimeMillis()
            if (app.container.value != null && madeAt == 0L) madeAt = now
            madeAt == 0L || !composed && now - madeAt < LAUNCH_HOLD_MILLIS
        }
        setContent {
            // Compose's insets for this window, heard from its first layout on, under the launch window: a screen
            // composed after that has them from its first frame, where the first one to ask would go a frame without.
            WindowInsets.safeDrawing
            val appearance = appearanceSetting()
            val night = isSystemInDarkTheme()
            val dark = appearance.isDark(night)
            // Set again on a change of the night too, Dark picked included, where the app's own dark comes first: the
            // platform then lays the window theme's icons, as the window was made, over the bars.
            DisposableEffect(dark, night) {
                systemBars(dark)
                onDispose {}
            }
            val container = app.container.collectAsState().value
            // The liveries from the container's catalog, never from one read before it is installed.
            val catalog = container?.catalog?.collectAsState()?.value
            val identities = rememberCarrierIdentities(catalog)
            // The screens wait for the carriers too, which give each card its room: a list laid out without them would
            // move its cards to their places once they came, from a pile.
            val shows = container != null && identities != null && laidOut
            PeekTheme(appearance = appearance, dark = dark, carrierIdentities = identities) {
                if (shows) {
                    CompositionLocalProvider(LocalWindowShortcuts provides shortcuts) { PeekRoot(container, inbox) }
                } else {
                    // Under the launch window.
                    Box(Modifier.fillMaxSize().background(PeekTheme.colors.background))
                }
            }
            // The root shows the screen the session leads to once it is read, or the launch screen if that takes
            // longer than the launch screen waits before it says so (LaunchView).
            val session = container?.session?.stateFlow?.collectAsState()?.value
            var slow by remember { mutableStateOf(false) }
            if (shows) {
                LaunchedEffect(Unit) {
                    delay(LAUNCH_SLOW_MILLIS)
                    slow = true
                }
            }
            SideEffect { if (shows && (slow || session != AuthenticationState.Loading)) composed = true }
        }
        val content = findViewById<View>(android.R.id.content)
        content.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                view: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int,
            ) {
                view.removeOnLayoutChangeListener(this)
                laidOut = true
            }
        })
    }

    /**
     * The night mode changed (the app's appearance, or the phone's under System): the window takes it as it is, as the
     * iPhone's does, without starting again (`configChanges="uiMode"`), the composition from its configuration and the
     * platform's own pieces from the theme, its light or dark resolved again for it (values-night) over the one before.
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        theme.applyStyle(R.style.Theme_Peek, true)
    }

    /**
     * The app's keys the system lists over the window when asked (Meta+/), as an iPad lists them holding ⌘ or in its
     * menu bar: those the screens answer now, titled as the iPhone titles them, in the app's language.
     */
    override fun onProvideKeyboardShortcuts(data: MutableList<KeyboardShortcutGroup>, menu: Menu?, deviceId: Int) {
        super.onProvideKeyboardShortcuts(data, menu, deviceId)
        val localizer = app.container.value?.localizer ?: return
        data += shortcuts.groups({ localizer.text(it) }, getString(R.string.app_name))
    }

    /** Web sign-in opens over the window a tap last reached, the top resumed one where two of the app's share the screen. */
    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)
        if (isTopResumedActivity) app.signInTab.cameToTop(this)
    }

    /** Edge to edge with the bars' icons for the app's appearance, not the phone's, and no scrim, as on the iPhone. */
    private fun systemBars(dark: Boolean) {
        val style = if (dark) SystemBarStyle.dark(Color.TRANSPARENT) else SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        enableEdgeToEdge(statusBarStyle = style, navigationBarStyle = style)
    }

    companion object {
        /**
         * How long the launch window waits for the first screen once the stores are made, in milliseconds. The screen
         * comes well within it; should anything keep it from being composed, the window goes all the same rather than stay.
         */
        internal const val LAUNCH_HOLD_MILLIS = 2_000L
    }
}
