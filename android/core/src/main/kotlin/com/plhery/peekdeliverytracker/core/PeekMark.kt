package com.plhery.peekdeliverytracker.core

// From PeekMark.swift.

/** The mark at a size in points. Decorative: the name is written beside it, so :app hides it from accessibility. */
data class PeekMark(val size: Double) {
    /** How much the artwork's frame is scaled: :app scales its canvas by this, then carries out `drawing`. */
    val scale: Double get() = size / PeekMarkArtwork.frame

    val drawing: List<MapDrawing> get() = PeekMarkArtwork.draw()

    /** What the drawing is clipped to: the size's square, with SwiftUI's continuous corners. */
    val clip: MapPath get() = MapPath.roundedRect(Rect(0.0, 0.0, size, size), size * PeekMarkArtwork.cornerRadius / PeekMarkArtwork.frame)
}
