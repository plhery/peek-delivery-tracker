package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.plhery.peekdeliverytracker.core.MapPath
import com.plhery.peekdeliverytracker.core.Rect

/**
 * `RoundedRectangle(cornerRadius:style: .continuous)` (X-AD37): SwiftUI's own outline, from :core's
 * `MapPath.roundedRect`, where Compose's `RoundedCornerShape` has quarter circles. The radius stops at half the shorter
 * side, as SwiftUI's does.
 */
@Immutable
data class ContinuousRoundedShape(val cornerRadius: Dp) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline =
        continuousOutline(size, with(density) { cornerRadius.toPx() }.toDouble())
}

/** `Capsule()`: continuous corners whose radius is half the shorter side, as SwiftUI's own capsule. */
@Immutable
data object CapsuleShape : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline =
        continuousOutline(size, minOf(size.width, size.height) / 2.0)
}

private fun continuousOutline(size: Size, radius: Double): Outline =
    Outline.Generic(MapPath.roundedRect(Rect(0.0, 0.0, size.width.toDouble(), size.height.toDouble()), radius).toComposePath())
