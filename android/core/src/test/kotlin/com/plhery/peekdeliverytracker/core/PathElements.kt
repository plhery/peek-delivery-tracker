package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals

/**
 * Asserts a path's elements against SwiftUI's, as a swiftc harness on the Mac prints `Path.forEach`: "M x y", "L x y",
 * "Q cx cy x y", "C c1x c1y c2x c2y x y" and "Z", each number to 17 significant digits, so equal means the same Double.
 * Arcs and ellipses, which SwiftUI gives as curves, fail.
 */
internal fun assertPathElements(expected: List<String>, path: MapPath) {
    fun parsed(element: String): List<Any> = element.split(" ").let { parts -> listOf<Any>(parts[0]) + parts.drop(1).map(String::toDouble) }
    val elements = mutableListOf<List<Any>>()
    path.replay(object : MapPath.Sink {
        override fun move(x: Double, y: Double) {
            elements.add(listOf("M", x, y))
        }

        override fun line(x: Double, y: Double) {
            elements.add(listOf("L", x, y))
        }

        override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) {
            elements.add(listOf("Q", controlX, controlY, x, y))
        }

        override fun curve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double) {
            elements.add(listOf("C", control1X, control1Y, control2X, control2Y, x, y))
        }

        override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) = error("arc")

        override fun ellipse(x: Double, y: Double, width: Double, height: Double) = error("ellipse")

        override fun close() {
            elements.add(listOf("Z"))
        }
    })
    assertEquals(expected.map(::parsed), elements)
}
