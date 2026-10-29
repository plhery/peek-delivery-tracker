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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.plhery.peekdeliverytracker.ui.draw.PeekMark
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.appearanceSetting

class MainActivity : ComponentActivity() {
    private val app: PeekApplication get() = application as PeekApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        // The launch window: iOS's blank LaunchBackground, no icon (themes.xml), kept while the stores are made.
        val splash = installSplashScreen()
        val systemDark = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        systemBars(dark = AppearanceStore.of(this).appearance.value.isDark(systemDark))
        super.onCreate(savedInstanceState)
        // The stores are made with the first window, as the iPhone's are with its app.
        app.makeContainer()
        splash.setKeepOnScreenCondition { app.container.value == null }
        setContent {
            val appearance = appearanceSetting()
            val dark = appearance.isDark(isSystemInDarkTheme())
            DisposableEffect(dark) {
                systemBars(dark)
                onDispose {}
            }
            PeekTheme(appearance = appearance, dark = dark) {
                Box(Modifier.fillMaxSize().background(PeekTheme.colors.background), contentAlignment = Alignment.Center) {
                    PeekMark(78.dp)
                }
            }
        }
    }

    /** Edge to edge with the bars' icons for the app's appearance, not the phone's, and no scrim, as on the iPhone. */
    private fun systemBars(dark: Boolean) {
        val style = if (dark) SystemBarStyle.dark(Color.TRANSPARENT) else SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        enableEdgeToEdge(statusBarStyle = style, navigationBarStyle = style)
    }
}
