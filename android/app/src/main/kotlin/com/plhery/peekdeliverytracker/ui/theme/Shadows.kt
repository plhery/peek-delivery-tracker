package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/**
 * Android's blur radius for a Gaussian of standard deviation `deviation`, for Compose's shadows and blur effects alike.
 * Skia and hwui blur a radius r with σ = 0.57735 r + 0.5 pixels (`convertRadiusToSigma`), so r is √3 σ; the half pixel
 * is left out.
 */
fun gaussianBlurRadius(deviation: Dp): Dp = deviation * SQRT3

private const val SQRT3 = 1.7320508f

/**
 * SwiftUI's `.shadow(color:radius:x:y:)`: CoreAnimation's shadow, a Gaussian whose deviation is the radius. Measured on
 * the iPhone's captures, the Next up card's shadow (radius 10, black at 3.5 %) falls off beside the card as a Gaussian
 * of 30 pixels at three pixels to the point, and reaches its full 3.5 % under it.
 */
fun swiftUiShadow(color: Color, radius: Dp, x: Dp = 0.dp, y: Dp = 0.dp): Shadow =
    Shadow(radius = gaussianBlurRadius(radius), color = color, offset = DpOffset(x, y))
