package com.plhery.peekdeliverytracker

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.plhery.peekdeliverytracker.core.ShareInbox
import com.plhery.peekdeliverytracker.ui.PeekRoot
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.appearanceSetting
import com.plhery.peekdeliverytracker.ui.theme.rememberCarrierIdentities

class MainActivity : ComponentActivity() {
    private val app: PeekApplication get() = application as PeekApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        // The launch window: iOS's blank LaunchBackground, no icon (themes.xml), kept while the stores are made.
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
        // followed, here or by ParcelLinkActivity, isn't followed again.
        val inbox = DeliveriesInbox.of(
            this, parcelLink = app::openParcelLink, sharedDrafts = { ShareInbox.consume(app.shareDefaults()) }, invitationLink = app::openInvitation,
            opened = app::linkOpened, notificationOpened = app::notificationOpened, repeated = app::isRepeat,
        )
        splash.setKeepOnScreenCondition { app.container.value == null }
        setContent {
            val appearance = appearanceSetting()
            val dark = appearance.isDark(isSystemInDarkTheme())
            DisposableEffect(dark) {
                systemBars(dark)
                onDispose {}
            }
            val container = app.container.collectAsState().value
            // The liveries from the container's catalog, never from one read before it is installed.
            val catalog = container?.catalog?.collectAsState()?.value
            PeekTheme(appearance = appearance, dark = dark, carrierIdentities = rememberCarrierIdentities(catalog)) {
                if (container != null) {
                    PeekRoot(container, inbox)
                } else {
                    // Under the launch window, which stays until the container is made.
                    Box(Modifier.fillMaxSize().background(PeekTheme.colors.background))
                }
            }
        }
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
}
