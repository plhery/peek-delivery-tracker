package com.plhery.peekdeliverytracker.core

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.truncate

// The CoreGraphics, simd and SwiftUI geometry the map's maths uses, as small value types. CGFloat is a Double on a
// 64-bit iPhone, and the map's simd vectors are SIMD3<Double>, so everything here is a Double. Equality is Swift's:
// 0.0 equals -0.0, and NaN equals nothing.

private fun Double.hashKey(): Int = (if (this == 0.0) 0.0 else this).hashCode()

/**
 * Swift's `max(_:_:)`: the second value unless the first is larger. Unlike `kotlin.math.max`, a NaN first value is kept
 * and a NaN second one passed over, and of 0.0 and -0.0 the second is taken.
 */
internal fun swiftMax(a: Double, b: Double): Double = if (b >= a) b else a

internal fun swiftMax(a: Double, b: Double, c: Double): Double = swiftMax(swiftMax(a, b), c)

/** Swift's `min(_:_:)`: the first value unless the second is smaller. */
internal fun swiftMin(a: Double, b: Double): Double = if (b < a) b else a

internal fun swiftMin(a: Double, b: Double, c: Double): Double = swiftMin(swiftMin(a, b), c)

/** Swift's `rounded()`: to the nearest whole number, halves away from zero. */
internal fun Double.rounded(): Double {
    if (!(abs(this) < 4.503599627370496E15)) return this
    val whole = truncate(this)
    return if (abs(this - whole) >= 0.5) whole + sign(this) else whole
}

/** CGPoint. */
class Point(val x: Double, val y: Double) {
    /** CGPoint.applying(_:). */
    fun applying(t: AffineTransform): Point = Point(t.a * x + t.c * y + t.tx, t.b * x + t.d * y + t.ty)

    override fun equals(other: Any?): Boolean = other is Point && x == other.x && y == other.y
    override fun hashCode(): Int = 31 * x.hashKey() + y.hashKey()
    override fun toString(): String = "($x, $y)"

    companion object {
        val Zero = Point(0.0, 0.0)
    }
}

/** CGSize. */
class Size(val width: Double, val height: Double) {
    override fun equals(other: Any?): Boolean = other is Size && width == other.width && height == other.height
    override fun hashCode(): Int = 31 * width.hashKey() + height.hashKey()
    override fun toString(): String = "($width, $height)"

    companion object {
        val Zero = Size(0.0, 0.0)
    }
}

/**
 * CGRect, with CoreGraphics' rules: edges and sizes are those of the standardized rectangle, `insetBy` past nothing
 * and the intersection of rectangles apart give the null rectangle, `contains(point)` takes the minimum edges and not
 * the maximum ones, and rectangles that only touch do not intersect.
 */
class Rect(val x: Double, val y: Double, private val rawWidth: Double, private val rawHeight: Double) {
    constructor(origin: Point, size: Size) : this(origin.x, origin.y, size.width, size.height)

    val isNull: Boolean get() = x == Double.POSITIVE_INFINITY || y == Double.POSITIVE_INFINITY

    val origin: Point get() = Point(x, y)
    val width: Double get() = abs(rawWidth)
    val height: Double get() = abs(rawHeight)
    val minX: Double get() = if (rawWidth < 0) x + rawWidth else x
    val minY: Double get() = if (rawHeight < 0) y + rawHeight else y
    val maxX: Double get() = minX + width
    val maxY: Double get() = minY + height
    val midX: Double get() = minX + width / 2
    val midY: Double get() = minY + height / 2

    fun insetBy(dx: Double, dy: Double): Rect {
        if (isNull) return Null
        val width = width - dx * 2
        val height = height - dy * 2
        if (width < 0 || height < 0) return Null
        return Rect(minX + dx, minY + dy, width, height)
    }

    fun offsetBy(dx: Double, dy: Double): Rect = if (isNull) Null else Rect(minX + dx, minY + dy, width, height)

    fun contains(point: Point): Boolean =
        !isNull && point.x >= minX && point.x < maxX && point.y >= minY && point.y < maxY

    fun contains(rect: Rect): Boolean =
        !isNull && !rect.isNull && rect.minX >= minX && rect.maxX <= maxX && rect.minY >= minY && rect.maxY <= maxY

    fun intersects(other: Rect): Boolean {
        if (isNull || other.isNull) return false
        return !(maxX <= other.minX || other.maxX <= minX || maxY <= other.minY || other.maxY <= minY)
    }

    fun intersection(other: Rect): Rect {
        if (isNull || other.isNull) return Null
        val left = max(minX, other.minX)
        val right = min(maxX, other.maxX)
        val top = max(minY, other.minY)
        val bottom = min(maxY, other.maxY)
        if (left > right || top > bottom) return Null
        return Rect(left, top, right - left, bottom - top)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Rect) return false
        if (isNull || other.isNull) return isNull == other.isNull
        return minX == other.minX && minY == other.minY && width == other.width && height == other.height
    }

    override fun hashCode(): Int =
        if (isNull) 0 else ((minX.hashKey() * 31 + minY.hashKey()) * 31 + width.hashKey()) * 31 + height.hashKey()

    override fun toString(): String = "($x, $y, $rawWidth, $rawHeight)"

    companion object {
        val Zero = Rect(0.0, 0.0, 0.0, 0.0)
        val Null = Rect(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0, 0.0)
    }
}

/** SwiftUI's EdgeInsets. */
data class EdgeInsets(val top: Double = 0.0, val leading: Double = 0.0, val bottom: Double = 0.0, val trailing: Double = 0.0)

/** CGAffineTransform, which maps (x, y) to (a x + c y + tx, b x + d y + ty). */
data class AffineTransform(
    val a: Double,
    val b: Double,
    val c: Double,
    val d: Double,
    val tx: Double,
    val ty: Double,
) {
    /** `t.concatenating(other)`: this transform, then `other`. */
    fun concatenating(other: AffineTransform): AffineTransform = AffineTransform(
        a = a * other.a + b * other.c,
        b = a * other.b + b * other.d,
        c = c * other.a + d * other.c,
        d = c * other.b + d * other.d,
        tx = tx * other.a + ty * other.c + other.tx,
        ty = tx * other.b + ty * other.d + other.ty,
    )

    /** The transform that rotates, scales or translates first, then applies this one, as CoreGraphics prepends. */
    fun rotated(by: Double): AffineTransform = rotation(by).concatenating(this)
    fun scaledBy(x: Double, y: Double): AffineTransform = AffineTransform(x, 0.0, 0.0, y, 0.0, 0.0).concatenating(this)
    fun translatedBy(x: Double, y: Double): AffineTransform = translation(x, y).concatenating(this)

    override fun equals(other: Any?): Boolean = other is AffineTransform &&
        a == other.a && b == other.b && c == other.c && d == other.d && tx == other.tx && ty == other.ty
    override fun hashCode(): Int = listOf(a, b, c, d, tx, ty).fold(0) { hash, value -> hash * 31 + value.hashKey() }

    companion object {
        val Identity = AffineTransform(1.0, 0.0, 0.0, 1.0, 0.0, 0.0)

        fun translation(x: Double, y: Double): AffineTransform = AffineTransform(1.0, 0.0, 0.0, 1.0, x, y)

        fun rotation(angle: Double): AffineTransform {
            val cosine = cos(angle)
            val sine = sin(angle)
            return AffineTransform(cosine, sine, -sine, cosine, 0.0, 0.0)
        }
    }
}

/** SIMD3<Double>, with simd's dot, length, normalize and cross as the iPhone computes them. */
class Vector3(val x: Double, val y: Double, val z: Double) {
    operator fun plus(other: Vector3): Vector3 = Vector3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Vector3): Vector3 = Vector3(x - other.x, y - other.y, z - other.z)
    operator fun times(scalar: Double): Vector3 = Vector3(x * scalar, y * scalar, z * scalar)
    operator fun div(scalar: Double): Vector3 = Vector3(x / scalar, y / scalar, z / scalar)
    operator fun unaryMinus(): Vector3 = Vector3(-x, -y, -z)

    /** simd_dot, and Swift's `(a * b).sum()`: added in order. */
    fun dot(other: Vector3): Double = x * other.x + y * other.y + z * other.z

    /** simd_length. */
    val length: Double get() = sqrt(dot(this))

    /** simd_normalize, which multiplies by the reciprocal of the length. */
    fun normalized(): Vector3 = this * (1 / sqrt(dot(this)))

    /** simd_cross. Clang fuses each component's first product with the subtraction of the second. */
    fun cross(other: Vector3): Vector3 = Vector3(
        Math.fma(y, other.z, -(z * other.y)),
        Math.fma(z, other.x, -(x * other.z)),
        Math.fma(x, other.y, -(y * other.x)),
    )

    override fun equals(other: Any?): Boolean = other is Vector3 && x == other.x && y == other.y && z == other.z
    override fun hashCode(): Int = (x.hashKey() * 31 + y.hashKey()) * 31 + z.hashKey()
    override fun toString(): String = "($x, $y, $z)"

    companion object {
        val Zero = Vector3(0.0, 0.0, 0.0)
    }
}

/** SIMD2<Double>. */
class Vector2(val x: Double, val y: Double) {
    operator fun div(scalar: Double): Vector2 = Vector2(x / scalar, y / scalar)

    /** simd_dot. */
    fun dot(other: Vector2): Double = x * other.x + y * other.y

    override fun equals(other: Any?): Boolean = other is Vector2 && x == other.x && y == other.y
    override fun hashCode(): Int = x.hashKey() * 31 + y.hashKey()
}

/**
 * SwiftUI's Path, built the way the map builds one: moves, lines, quadratic curves, arcs of a circle and ellipses. It
 * is a builder, filled in place as Swift fills an `inout Path`; :app draws its elements. Arcs and ellipses are kept
 * whole rather than as CoreGraphics' Bézier pieces, so each platform draws them its own way.
 */
class MapPath {
    sealed interface Element {
        data class Move(val to: Point) : Element
        data class Line(val to: Point) : Element
        data class QuadCurve(val to: Point, val control: Point) : Element

        /**
         * `addRelativeArc(center:radius:startAngle:delta:)`: a line from the current point to the arc's start, then
         * the arc, `delta` radians from `startAngle`, positive toward screen y.
         */
        data class Arc(val center: Point, val radius: Double, val startAngle: Double, val delta: Double) : Element

        /** `Path(ellipseIn:)`: a closed subpath of its own, starting at the rectangle's right middle. */
        data class Ellipse(val rect: Rect) : Element
        data object Close : Element
    }

    private val list = mutableListOf<Element>()
    val elements: List<Element> get() = list

    val isEmpty: Boolean get() = list.isEmpty()

    fun move(to: Point) {
        list.add(Element.Move(to))
    }

    fun addLine(to: Point) {
        list.add(Element.Line(to))
    }

    fun addQuadCurve(to: Point, control: Point) {
        list.add(Element.QuadCurve(to, control))
    }

    fun addRelativeArc(center: Point, radius: Double, startAngle: Double, delta: Double) {
        list.add(Element.Arc(center, radius, startAngle, delta))
    }

    fun closeSubpath() {
        list.add(Element.Close)
    }

    fun addPath(path: MapPath) {
        list.addAll(path.list)
    }

    /** Where the next element would start; nil for an empty path. */
    val currentPoint: Point?
        get() {
            var start: Point? = null
            var current: Point? = null
            for (element in list) {
                when (element) {
                    is Element.Move -> { start = element.to; current = element.to }
                    is Element.Line -> current = element.to
                    is Element.QuadCurve -> current = element.to
                    is Element.Arc -> current = arcPoint(element, element.startAngle + element.delta)
                    is Element.Ellipse -> {
                        start = Point(element.rect.maxX, element.rect.midY)
                        current = start
                    }
                    Element.Close -> current = start
                }
            }
            return current
        }

    /** Whether a filled path covers a point, by the even-odd rule (`contains(_:eoFill: true)`). */
    fun containsEvenOdd(point: Point): Boolean {
        var inside = false
        for (polygon in polygons()) {
            for (index in polygon.indices) {
                val a = polygon[index]
                val b = polygon[(index + 1) % polygon.size]
                if ((a.y > point.y) != (b.y > point.y) && point.x < a.x + (point.y - a.y) / (b.y - a.y) * (b.x - a.x)) {
                    inside = !inside
                }
            }
        }
        return inside
    }

    /**
     * The end of `trimmedPath(from: 0, to: fraction)`: the point that far along the drawn length, where moves add
     * nothing. Nil for no length at all, as SwiftUI gives for an empty trimmed path.
     */
    fun trimmedEnd(fraction: Double): Point? {
        if (fraction <= 0) return null
        val runs = polylines()
        val total = runs.sumOf { run -> run.zipWithNext { a, b -> hypot(b.x - a.x, b.y - a.y) }.sum() }
        if (total <= 0) return null
        var left = min(1.0, fraction) * total
        var last: Point? = null
        for (run in runs) {
            for ((a, b) in run.zipWithNext()) {
                val length = hypot(b.x - a.x, b.y - a.y)
                if (length >= left && length > 0) {
                    val t = left / length
                    return Point(a.x + (b.x - a.x) * t, a.y + (b.y - a.y) * t)
                }
                left -= length
                last = b
            }
        }
        return last
    }

    /** Each subpath as points, curves flattened finely; a fill closes every subpath. */
    private fun polygons(): List<List<Point>> = polylines().filter { it.size >= 3 }

    private fun polylines(): List<List<Point>> {
        val runs = mutableListOf<MutableList<Point>>()
        var run: MutableList<Point>? = null
        var start: Point? = null
        fun begin(point: Point): MutableList<Point> {
            start = point
            return mutableListOf(point).also { run = it; runs.add(it) }
        }
        for (element in list) {
            when (element) {
                is Element.Move -> begin(element.to)
                is Element.Line -> (run ?: begin(element.to)).add(element.to)
                is Element.QuadCurve -> {
                    val points = run ?: continue
                    val from = points.last()
                    val steps = 32
                    for (step in 1..steps) {
                        val t = step.toDouble() / steps
                        val u = 1 - t
                        points.add(
                            Point(
                                u * u * from.x + 2 * u * t * element.control.x + t * t * element.to.x,
                                u * u * from.y + 2 * u * t * element.control.y + t * t * element.to.y,
                            ),
                        )
                    }
                }
                is Element.Arc -> {
                    val first = arcPoint(element, element.startAngle)
                    val points = run?.also { it.add(first) } ?: begin(first)
                    val steps = max(1, ceil(abs(element.delta) / (PI / 180)).toInt())
                    for (step in 1..steps) points.add(arcPoint(element, element.startAngle + element.delta * step / steps))
                }
                is Element.Ellipse -> {
                    val rect = element.rect
                    val points = begin(Point(rect.maxX, rect.midY))
                    val steps = 180
                    for (step in 1..steps) {
                        val angle = 2 * PI * step / steps
                        points.add(Point(rect.midX + rect.width / 2 * cos(angle), rect.midY + rect.height / 2 * sin(angle)))
                    }
                    begin(points.first())
                }
                // Closing draws back to the subpath's start, where whatever follows starts.
                Element.Close -> {
                    val back = start ?: continue
                    run?.add(back)
                    begin(back)
                }
            }
        }
        return runs
    }

    private fun arcPoint(arc: Element.Arc, angle: Double): Point =
        Point(arc.center.x + arc.radius * cos(angle), arc.center.y + arc.radius * sin(angle))

    companion object {
        fun ellipse(rect: Rect): MapPath = MapPath().also { it.list.add(Element.Ellipse(rect)) }
    }
}

/**
 * SwiftUI's UnitCurve for a cubic Bézier from (0, 0) to (1, 1): the curve's height where it is `progress` across,
 * found by Newton's method, then by halving where that does not settle.
 */
class UnitCurve private constructor(start: Point, end: Point) {
    private val cx = 3 * start.x
    private val bx = 3 * (end.x - start.x) - cx
    private val ax = 1 - cx - bx
    private val cy = 3 * start.y
    private val by = 3 * (end.y - start.y) - cy
    private val ay = 1 - cy - by

    private fun x(t: Double): Double = ((ax * t + bx) * t + cx) * t
    private fun y(t: Double): Double = ((ay * t + by) * t + cy) * t
    private fun slope(t: Double): Double = (3 * ax * t + 2 * bx) * t + cx

    fun value(progress: Double): Double {
        val x = swiftMin(1.0, swiftMax(0.0, progress))
        var t = x
        repeat(16) {
            val error = x(t) - x
            if (abs(error) < 1e-14) return y(t)
            val slope = slope(t)
            if (abs(slope) < 1e-12) return@repeat
            t -= error / slope
        }
        var low = 0.0
        var high = 1.0
        t = x
        repeat(200) {
            val error = x(t) - x
            if (abs(error) < 1e-14) return y(t)
            if (error > 0) high = t else low = t
            t = (low + high) / 2
        }
        return y(t)
    }

    companion object {
        fun bezier(startControlPoint: Point, endControlPoint: Point): UnitCurve = UnitCurve(startControlPoint, endControlPoint)

        val easeInOut = bezier(Point(0.42, 0.0), Point(0.58, 1.0))
    }
}
