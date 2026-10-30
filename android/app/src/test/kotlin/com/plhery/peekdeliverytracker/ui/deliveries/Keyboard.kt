package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration

/** `content` as on a device with a hardware keyboard connected and in use, where the menus show their keys. */
@Composable
fun WithKeyboard(connected: Boolean = true, content: @Composable () -> Unit) {
    if (!connected) return content()
    val configuration = Configuration(LocalConfiguration.current).apply {
        keyboard = Configuration.KEYBOARD_QWERTY
        hardKeyboardHidden = Configuration.HARDKEYBOARDHIDDEN_NO
    }
    CompositionLocalProvider(LocalConfiguration provides configuration, content = content)
}
