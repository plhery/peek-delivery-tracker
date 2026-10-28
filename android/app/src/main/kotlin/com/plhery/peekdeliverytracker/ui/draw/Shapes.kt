package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.MapPath
import com.plhery.peekdeliverytracker.core.Rect
import com.plhery.peekdeliverytracker.core.RectangleCornerRadii

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

/**
 * `UnevenRoundedRectangle(topLeadingRadius:bottomLeadingRadius:bottomTrailingRadius:topTrailingRadius:)`, continuous as
 * SwiftUI's default: SwiftUI's own outline, from :core's `MapPath.unevenRoundedRect`. Leading and trailing follow the
 * layout direction, as SwiftUI's do.
 */
@Immutable
data class UnevenContinuousRoundedShape(
    val topLeading: Dp = 0.dp,
    val bottomLeading: Dp = 0.dp,
    val bottomTrailing: Dp = 0.dp,
    val topTrailing: Dp = 0.dp,
) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        fun px(radius: Dp) = with(density) { radius.toPx() }.toDouble()
        val leftToRight = layoutDirection == LayoutDirection.Ltr
        val radii = RectangleCornerRadii(
            topLeading = px(if (leftToRight) topLeading else topTrailing),
            bottomLeading = px(if (leftToRight) bottomLeading else bottomTrailing),
            bottomTrailing = px(if (leftToRight) bottomTrailing else bottomLeading),
            topTrailing = px(if (leftToRight) topTrailing else topLeading),
        )
        val rect = Rect(0.0, 0.0, size.width.toDouble(), size.height.toDouble())
        return Outline.Generic(MapPath.unevenRoundedRect(rect, radii).toComposePath())
    }
}

/** `Capsule()`: continuous corners whose radius is half the shorter side, as SwiftUI's own capsule. */
@Immutable
data object CapsuleShape : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline =
        continuousOutline(size, minOf(size.width, size.height) / 2.0)
}

private fun continuousOutline(size: Size, radius: Double): Outline =
    Outline.Generic(MapPath.roundedRect(Rect(0.0, 0.0, size.width.toDouble(), size.height.toDouble()), radius).toComposePath())
