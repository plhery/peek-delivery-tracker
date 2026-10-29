package com.plhery.peekdeliverytracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import java.time.Instant

/**
 * The app's copy, the iPhone's `@EnvironmentObject var localizer`: AppContainer's localizer, with the `android`
 * variant, for everything inside [ProvideLocalizer].
 */
val LocalLocalizer = staticCompositionLocalOf<Localizer> { error("No Localizer: wrap the content in ProvideLocalizer") }

/** The localizer's language as it changes, so what is written in it is written again. */
val LocalLanguage = compositionLocalOf { AppLanguage.En }

/** `localizer` and its language for `content`. */
@Composable
fun ProvideLocalizer(localizer: Localizer, content: @Composable () -> Unit) {
    val language by localizer.languageFlow.collectAsState()
    CompositionLocalProvider(LocalLocalizer provides localizer, LocalLanguage provides language, content = content)
}

/**
 * The time a screen reads for what it says about now ("today", an automatic carrier change still shown): `Date()` on
 * the iPhone. Tests give a fixed one.
 */
val LocalNow = staticCompositionLocalOf<() -> Instant> { Instant::now }
