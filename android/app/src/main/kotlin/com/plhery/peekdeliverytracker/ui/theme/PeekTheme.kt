package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.platform.LocalDensity

/**
 * The iPhone app's look for everything inside: its colours for the appearance, its fonts at the reader's text size,
 * Reduce Motion and Reduce Transparency, and the carriers' liveries once loaded. Material's own colours and type are
 * filled from these only so its components blend in; screens read `PeekTheme.colors` and `PeekTheme.type`. No dynamic
 * colour (X-AD16).
 *
 * The appearance is the one picked in Settings, light unless the reader picks another; the phone's dark theme counts
 * only for System. Text and icons take the label colour, text selection the tint, a press shows no ripple (buttons
 * take a `PressStyle`), and scrolling containers bounce at their ends ([RubberBandOverscroll]).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeekTheme(
    appearance: AppAppearance = appearanceSetting(),
    dark: Boolean = appearance.isDark(isSystemInDarkTheme()),
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
            // Inside MaterialTheme, which sets its own ripple and selection colours.
            CompositionLocalProvider(
                LocalContentColor provides colors.label,
                LocalTextSelectionColors provides remember(colors) { selectionColors(colors) },
                LocalIndication provides NoIndication,
                LocalRippleConfiguration provides null,
                LocalOverscrollFactory provides RubberBandOverscrollFactory,
            ) {
                ProvideDynamicTypeSize(dynamicTypeSize, content)
            }
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

/**
 * Material's every colour role from the app's colours, so no Material component shows its baseline purple: accent
 * roles take the yellow, secondary and tertiary the ink, surfaces paper and cream, errors the warning colour.
 */
internal fun materialColors(colors: PeekColors): ColorScheme = with(colors) {
    ColorScheme(
        primary = accent,
        onPrimary = onAccent,
        primaryContainer = accent,
        onPrimaryContainer = onAccent,
        inversePrimary = accent,
        secondary = ink,
        onSecondary = paper,
        secondaryContainer = cream,
        onSecondaryContainer = ink,
        tertiary = inkSoft,
        onTertiary = paper,
        tertiaryContainer = cream,
        onTertiaryContainer = inkSoft,
        background = background,
        onBackground = ink,
        surface = paper,
        onSurface = ink,
        surfaceVariant = cream,
        onSurfaceVariant = inkSoft,
        // Tonal elevation tints a surface with this: the surface itself leaves it as it is, as on the iPhone.
        surfaceTint = paper,
        inverseSurface = ink,
        inverseOnSurface = paper,
        error = warning,
        onError = paper,
        errorContainer = warningSoft,
        onErrorContainer = ink,
        outline = inkSoft,
        outlineVariant = separator,
        scrim = SCRIM,
        surfaceBright = paper,
        surfaceDim = cream,
        surfaceContainer = paper,
        surfaceContainerHigh = cream,
        surfaceContainerHighest = cream,
        surfaceContainerLow = paper,
        surfaceContainerLowest = paper,
        primaryFixed = accent,
        primaryFixedDim = accent,
        onPrimaryFixed = onAccent,
        onPrimaryFixedVariant = onAccent,
        secondaryFixed = cream,
        secondaryFixedDim = cream,
        onSecondaryFixed = ink,
        onSecondaryFixedVariant = inkSoft,
        tertiaryFixed = cream,
        tertiaryFixedDim = cream,
        onTertiaryFixed = ink,
        onTertiaryFixedVariant = inkSoft,
    )
}

/** Black, which iOS dims with; Material's sheets and drawers put their own opacity on it. */
internal val SCRIM = Color.Black.copy(alpha = 0.4f)

/**
 * iOS selects text in the tint, here the tab screens' ink (ParcelListView.swift `.tint(Brand.ink)`); the highlight is
 * the ink at 0.2.
 */
internal fun selectionColors(colors: PeekColors) =
    TextSelectionColors(handleColor = colors.ink, backgroundColor = colors.ink.copy(alpha = 0.2f))

/** No ripple, or anything else, where a press has no style of its own. */
internal object NoIndication : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode = object : Modifier.Node() {}

    override fun equals(other: Any?): Boolean = other === this

    override fun hashCode(): Int = javaClass.hashCode()
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
