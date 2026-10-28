package com.plhery.peekdeliverytracker.core

import kotlin.math.PI

// From PeekMarkArtwork.swift. Paths and colours only, so :app's views, widget and share sheet fill the same drawing.

/** The mark, two eyes on a postal-yellow tile, in a 512-unit frame with y pointing down. It is one drawing at every size. */
object PeekMarkArtwork {
    const val frame = 512.0
    const val cornerRadius = 116.0

    class Layer(val path: MapPath, val color: RgbaColor)

    /**
     * The rounded tile. CoreGraphics' rounded rectangle, unlike SwiftUI's, has quarter-circle corners; it starts at the
     * right middle and runs down the right side first.
     */
    val tile: MapPath get() = roundedRect(Rect(0.0, 0.0, frame, frame), cornerRadius)

    /**
     * The fills of the drawing, back to front: the tile, the whites of both eyes, then the pupil and the catchlight of
     * the left eye and of the right one. The whites overlap, so both lie under the pupils. New paths each time: :app
     * replays them and may keep them, but never adds to them.
     */
    val layers: List<Layer>
        get() = listOf(Layer(tile, yellow)) + eyes.map { Layer(ellipse(190 + it, 262.0, 70.0, 77.0), paper) } + eyes.flatMap(::pupil)

    /** `draw(in:size:)`: the layers' fills in order, once :app has scaled its canvas by `size / frame` from the origin. */
    fun draw(): List<MapDrawing> = layers.map { MapDrawing.Fill(it.path, AdaptiveColor(it.color)) }

    /** Where the eyes lie in the frame. */
    val eyesBounds = Rect(120.0, 185.0, 272.0, 154.0)

    /**
     * `drawEyes(in:)`: the eyes alone in one colour, the pupils cut out around their catchlights: a template image, such
     * as the one in a Mac's menu bar. In the frame's units, with y pointing down. The cut clears whatever is under it.
     */
    fun drawEyes(color: AdaptiveColor): List<MapDrawing> {
        fun both(x: Double, y: Double, rx: Double, ry: Double): MapPath =
            MapPath().also { path -> for (eye in eyes) path.addPath(ellipse(x + eye, y, rx, ry)) }
        return listOf(
            MapDrawing.Fill(both(190.0, 262.0, 70.0, 77.0), color),
            MapDrawing.Fill(both(219.4, 269.0, 35.0, 35.0), AdaptiveColor(RgbaColor(0.0, 0.0, 0.0)), cut = true),
            MapDrawing.Fill(both(207.5, 255.0, 10.5, 10.5), color),
        )
    }

    // MARK: Parts

    private val yellow = color(0xF3CF48u)
    private val paper = color(0xFFFAF0u)
    private val ink = color(0x171714u)
    private val white = color(0xFFFFFFu)

    /** How far right of the left eye each eye stands. */
    private val eyes = listOf(0.0, 132.0)

    /** The left eye's pupil, looking down to the right, and the catchlight at its upper left, moved right by a distance. */
    private fun pupil(movedRight: Double): List<Layer> = listOf(
        Layer(ellipse(219.4 + movedRight, 269.0, 35.0, 35.0), ink),
        Layer(ellipse(207.5 + movedRight, 255.0, 10.5, 10.5), white),
    )

    private fun ellipse(x: Double, y: Double, rx: Double, ry: Double): MapPath = MapPath.ellipse(Rect(x - rx, y - ry, rx * 2, ry * 2))

    private fun roundedRect(rect: Rect, radius: Double): MapPath = MapPath().also {
        it.move(rect.maxX, rect.midY)
        it.addRelativeArc(Point(rect.maxX - radius, rect.maxY - radius), radius, 0.0, PI / 2)
        it.addRelativeArc(Point(rect.minX + radius, rect.maxY - radius), radius, PI / 2, PI / 2)
        it.addRelativeArc(Point(rect.minX + radius, rect.minY + radius), radius, PI, PI / 2)
        it.addRelativeArc(Point(rect.maxX - radius, rect.minY + radius), radius, 3 * PI / 2, PI / 2)
        it.closeSubpath()
    }

    private fun color(hex: UInt): RgbaColor = RgbaColor(
        ((hex shr 16) and 0xFFu).toDouble() / 255,
        ((hex shr 8) and 0xFFu).toDouble() / 255,
        (hex and 0xFFu).toDouble() / 255,
        1.0,
    )
}
