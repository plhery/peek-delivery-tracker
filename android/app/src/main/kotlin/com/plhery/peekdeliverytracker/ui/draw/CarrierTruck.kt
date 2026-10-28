package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.CarrierTruckGeometry
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.ui.theme.LocalPeekColors

/**
 * `CarrierTruckMark`: the little truck in a carrier's livery, 27 × 18, its 32 × 21 drawing stretched to fit as the
 * iPhone's canvas stretches it. Decorative: the carrier's name is written beside it (A-AX16).
 */
@Composable
fun CarrierTruck(identity: CarrierVisualIdentity, modifier: Modifier = Modifier) {
    val steps = remember(identity) { CarrierTruckGeometry.draw(identity) }
    val colors = LocalPeekColors.current
    Canvas(modifier.size(27.dp, 18.dp).clearAndSetSemantics {}) {
        val viewBox = CarrierTruckGeometry.viewBox
        scale((size.width / viewBox.width).toFloat(), (size.height / viewBox.height).toFloat(), pivot = Offset.Zero) {
            drawMapDrawings(steps, colors.dark, colors.highContrast)
        }
    }
}
