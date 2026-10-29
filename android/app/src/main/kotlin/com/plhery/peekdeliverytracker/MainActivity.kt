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
    private val container: AppContainer get() = (application as PeekApplication).container

    override fun onCreate(savedInstanceState: Bundle?) {
        // The launch window: iOS's blank LaunchBackground, no icon (themes.xml).
        installSplashScreen()
        val systemDark = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        systemBars(dark = AppearanceStore.of(this).appearance.value.isDark(systemDark))
        super.onCreate(savedInstanceState)
        // The stores start with the first window, as the iPhone's start with its app.
        container
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

    override fun onResume() {
        super.onResume()
        container.sceneActive(true)
    }

    /** Covered or left (`scenePhase` leaving `.active`); a rotation, which makes the window again, is neither. */
    override fun onPause() {
        super.onPause()
        if (!isChangingConfigurations) container.sceneActive(false)
    }

    /** Edge to edge with the bars' icons for the app's appearance, not the phone's, and no scrim, as on the iPhone. */
    private fun systemBars(dark: Boolean) {
        val style = if (dark) SystemBarStyle.dark(Color.TRANSPARENT) else SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        enableEdgeToEdge(statusBarStyle = style, navigationBarStyle = style)
    }
}
