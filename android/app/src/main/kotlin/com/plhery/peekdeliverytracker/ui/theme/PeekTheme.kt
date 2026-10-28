package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity

/**
 * The iPhone app's look for everything inside: its colours for the appearance, its fonts at the reader's text size,
 * Reduce Motion and Reduce Transparency, and the carriers' liveries once loaded. Material's own colours and type are filled from these only
 * so its components blend in; screens read `PeekTheme.colors` and `PeekTheme.type`. No dynamic colour (X-AD16).
 */
@Composable
fun PeekTheme(
    dark: Boolean = isSystemInDarkTheme(),
    highContrast: Boolean = increasedContrastSetting(),
    reduceMotion: Boolean = reduceMotionSetting(),
    reduceTransparency: Boolean = reduceTransparencySetting(),
    dynamicTypeSize: DynamicTypeSize = DynamicTypeSize.of(LocalDensity.current.fontScale),
    carrierIdentities: CarrierIdentities? = rememberCarrierIdentities(),
    content: @Composable () -> Unit,
) {
    val colors = remember(dark, highContrast) { PeekColors(dark, highContrast) }
    val density = LocalDensity.current
    val materialType = remember(dynamicTypeSize, density) { materialType(PeekTypography(dynamicTypeSize, density)) }
    CompositionLocalProvider(
        LocalPeekColors provides colors,
        LocalReduceMotion provides reduceMotion,
        LocalReduceTransparency provides reduceTransparency,
        LocalCarrierIdentities provides carrierIdentities,
    ) {
        MaterialTheme(colorScheme = remember(colors) { materialColors(colors) }, typography = materialType) {
            ProvideDynamicTypeSize(dynamicTypeSize, content)
        }
    }
}

object PeekTheme {
    val colors: PeekColors
        @Composable @ReadOnlyComposable get() = LocalPeekColors.current

    val type: PeekTypography
        @Composable @ReadOnlyComposable get() = LocalPeekTypography.current

    val dynamicTypeSize: DynamicTypeSize
        @Composable @ReadOnlyComposable get() = LocalDynamicTypeSize.current

    val reduceMotion: Boolean
        @Composable @ReadOnlyComposable get() = LocalReduceMotion.current

    val reduceTransparency: Boolean
        @Composable @ReadOnlyComposable get() = LocalReduceTransparency.current
}

private fun materialColors(colors: PeekColors): ColorScheme {
    val scheme = if (colors.dark) darkColorScheme() else lightColorScheme()
    return scheme.copy(
        primary = colors.accent,
        onPrimary = colors.onAccent,
        primaryContainer = colors.accent,
        onPrimaryContainer = colors.onAccent,
        secondary = colors.ink,
        onSecondary = colors.paper,
        tertiary = colors.inkSoft,
        onTertiary = colors.paper,
        background = colors.background,
        onBackground = colors.ink,
        surface = colors.paper,
        onSurface = colors.ink,
        surfaceVariant = colors.cream,
        onSurfaceVariant = colors.inkSoft,
        surfaceContainer = colors.paper,
        surfaceContainerLow = colors.paper,
        surfaceContainerHigh = colors.cream,
        outline = colors.inkSoft,
        outlineVariant = colors.separator,
        error = colors.warning,
        onError = colors.paper,
        errorContainer = colors.warningSoft,
        onErrorContainer = colors.ink,
    )
}

private fun materialType(type: PeekTypography): Typography = Typography(
    displayLarge = type.largeTitle,
    displayMedium = type.largeTitle,
    displaySmall = type.title,
    headlineLarge = type.largeTitle,
    headlineMedium = type.title,
    headlineSmall = type.title2,
    titleLarge = type.title3,
    titleMedium = type.headline,
    titleSmall = type.subheadline,
    bodyLarge = type.body,
    bodyMedium = type.callout,
    bodySmall = type.footnote,
    labelLarge = type.headline,
    labelMedium = type.caption,
    labelSmall = type.caption2,
)
