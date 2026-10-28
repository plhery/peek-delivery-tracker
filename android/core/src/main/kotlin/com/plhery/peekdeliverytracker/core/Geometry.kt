package com.plhery.peekdeliverytracker.core

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.floor
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

    /**
     * simd_cross. On the iPhone clang fuses each component's first product with the subtraction of the second, which
     * Math.fma would match exactly, but Android has Math.fma only from API 33. Plain products differ from it by at
     * most 2 ulp of the larger product, a few 1e-13 of the result: no drawn point moves.
     */
    fun cross(other: Vector3): Vector3 = Vector3(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x,
    )

    override fun equals(other: Any?): Boolean = other is Vector3 && x == other.x && y == other.y && z == other.z
    override fun hashCode(): Int = (x.hashKey() * 31 + y.hashKey()) * 31 + z.hashKey()
    override fun toString(): String = "($x, $y, $z)"

    companion object {
        val Zero = Vector3(0.0, 0.0, 0.0)
    }
}

/** SwiftUI's RectangleCornerRadii: a radius for each corner, leading and trailing as on a left-to-right screen. */
data class RectangleCornerRadii(
    val topLeading: Double = 0.0,
    val bottomLeading: Double = 0.0,
    val bottomTrailing: Double = 0.0,
    val topTrailing: Double = 0.0,
)

/**
 * SwiftUI's Path, built the way the map builds one: moves, lines, quadratic and cubic curves, arcs of a circle and
 * ellipses. It is a builder, filled in place as Swift fills an `inout Path`; :app draws it by replaying it into its own
 * path. Arcs and ellipses are kept whole rather than as CoreGraphics' Bézier pieces, so each platform draws them its
 * own way; a rounded rectangle is SwiftUI's own curves, as no platform has its continuous corners.
 * The map rebuilds its paths each frame, so they hold plain numbers rather than an object per point.
 */
class MapPath {
    /** What a path is made of, in order: `replay` gives each piece to the platform's path. */
    interface Sink {
        fun move(x: Double, y: Double)
        fun line(x: Double, y: Double)
        fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double)

        /** `addCurve(to:control1:control2:)`: a cubic Bézier curve from the current point. */
        fun curve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double)

        /**
         * `addRelativeArc(center:radius:startAngle:delta:)`: a line from the current point to the arc's start, then
         * the arc, `delta` radians from `startAngle`, positive toward screen y.
         */
        fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double)

        /** `Path(ellipseIn:)`: a closed subpath of its own, starting at the rectangle's right middle. */
        fun ellipse(x: Double, y: Double, width: Double, height: Double)

        fun close()
    }

    /** One verb per piece, and the numbers each takes, in order. */
    private var verbs = ByteArray(16)
    private var verbCount = 0
    private var numbers = DoubleArray(32)
    private var numberCount = 0

    val isEmpty: Boolean get() = verbCount == 0

    /** Empties the path but keeps its room, so one path can be filled again each frame. */
    fun rewind() {
        verbCount = 0
        numberCount = 0
    }

    private fun add(verb: Byte, count: Int) {
        if (verbCount == verbs.size) verbs = verbs.copyOf(verbs.size * 2)
        verbs[verbCount++] = verb
        if (numberCount + count > numbers.size) numbers = numbers.copyOf(max(numbers.size * 2, numberCount + count))
    }

    fun move(x: Double, y: Double) {
        add(MOVE, 2)
        numbers[numberCount++] = x
        numbers[numberCount++] = y
    }

    fun move(to: Point) = move(to.x, to.y)

    fun addLine(x: Double, y: Double) {
        add(LINE, 2)
        numbers[numberCount++] = x
        numbers[numberCount++] = y
    }

    fun addLine(to: Point) = addLine(to.x, to.y)

    fun addQuadCurve(x: Double, y: Double, controlX: Double, controlY: Double) {
        add(QUAD, 4)
        numbers[numberCount++] = x
        numbers[numberCount++] = y
        numbers[numberCount++] = controlX
        numbers[numberCount++] = controlY
    }

    fun addQuadCurve(to: Point, control: Point) = addQuadCurve(to.x, to.y, control.x, control.y)

    fun addCurve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double) {
        add(CURVE, 6)
        numbers[numberCount++] = x
        numbers[numberCount++] = y
        numbers[numberCount++] = control1X
        numbers[numberCount++] = control1Y
        numbers[numberCount++] = control2X
        numbers[numberCount++] = control2Y
    }

    fun addCurve(to: Point, control1: Point, control2: Point) =
        addCurve(to.x, to.y, control1.x, control1.y, control2.x, control2.y)

    fun addRelativeArc(center: Point, radius: Double, startAngle: Double, delta: Double) {
        add(ARC, 5)
        numbers[numberCount++] = center.x
        numbers[numberCount++] = center.y
        numbers[numberCount++] = radius
        numbers[numberCount++] = startAngle
        numbers[numberCount++] = delta
    }

    fun closeSubpath() = add(CLOSE, 0)

    fun addPath(path: MapPath) {
        if (verbCount + path.verbCount > verbs.size) verbs = verbs.copyOf(max(verbs.size * 2, verbCount + path.verbCount))
        path.verbs.copyInto(verbs, verbCount, 0, path.verbCount)
        verbCount += path.verbCount
        if (numberCount + path.numberCount > numbers.size) {
            numbers = numbers.copyOf(max(numbers.size * 2, numberCount + path.numberCount))
        }
        path.numbers.copyInto(numbers, numberCount, 0, path.numberCount)
        numberCount += path.numberCount
    }

    /** A path holding what this one holds now, whatever is later added to either. */
    fun copy(): MapPath = MapPath().also { it.addPath(this) }

    /** Whether `other` holds the same pieces with the same numbers, so either draws the same platform path. */
    fun sameAs(other: MapPath): Boolean {
        if (verbCount != other.verbCount || numberCount != other.numberCount) return false
        for (index in 0 until verbCount) if (verbs[index] != other.verbs[index]) return false
        for (index in 0 until numberCount) if (numbers[index] != other.numbers[index]) return false
        return true
    }

    /** A hash of the pieces and their numbers, equal for paths that are [sameAs] each other. */
    fun contentHash(): Int {
        var hash = verbCount
        for (index in 0 until verbCount) hash = 31 * hash + verbs[index]
        // Plus 0.0 makes -0.0 hash as 0.0, which it equals.
        for (index in 0 until numberCount) hash = 31 * hash + (numbers[index] + 0.0).hashCode()
        return hash
    }

    fun replay(sink: Sink) {
        var at = 0
        val n = numbers
        for (index in 0 until verbCount) {
            when (verbs[index]) {
                MOVE -> sink.move(n[at], n[at + 1])
                LINE -> sink.line(n[at], n[at + 1])
                QUAD -> sink.quadCurve(n[at], n[at + 1], n[at + 2], n[at + 3])
                ARC -> sink.relativeArc(n[at], n[at + 1], n[at + 2], n[at + 3], n[at + 4])
                ELLIPSE -> sink.ellipse(n[at], n[at + 1], n[at + 2], n[at + 3])
                CURVE -> sink.curve(n[at], n[at + 1], n[at + 2], n[at + 3], n[at + 4], n[at + 5])
                else -> sink.close()
            }
            at += COUNTS[verbs[index].toInt()]
        }
    }

    /** Where the next element would start; nil for an empty path. */
    val currentPoint: Point?
        get() {
            var start: Point? = null
            var current: Point? = null
            replay(object : Sink {
                override fun move(x: Double, y: Double) {
                    start = Point(x, y)
                    current = start
                }

                override fun line(x: Double, y: Double) {
                    current = Point(x, y)
                }

                override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) {
                    current = Point(x, y)
                }

                override fun curve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double) {
                    current = Point(x, y)
                }

                override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) {
                    current = arcPoint(centerX, centerY, radius, startAngle + delta)
                }

                override fun ellipse(x: Double, y: Double, width: Double, height: Double) {
                    start = Point(x + width, y + height / 2)
                    current = start
                }

                override fun close() {
                    current = start
                }
            })
            return current
        }

    /**
     * `boundingBoxOfPath` (SwiftUI's `boundingRect`): the smallest rectangle around every point the path passes
     * through, curves included but not their control points; the null rectangle for an empty path.
     */
    val boundingBoxOfPath: Rect
        get() {
            var minX = Double.POSITIVE_INFINITY
            var minY = Double.POSITIVE_INFINITY
            var maxX = Double.NEGATIVE_INFINITY
            var maxY = Double.NEGATIVE_INFINITY
            fun add(x: Double, y: Double) {
                minX = min(minX, x)
                minY = min(minY, y)
                maxX = max(maxX, x)
                maxY = max(maxY, y)
            }
            var start = Point.Zero
            var current = Point.Zero
            replay(object : Sink {
                override fun move(x: Double, y: Double) {
                    add(x, y)
                    start = Point(x, y)
                    current = start
                }

                override fun line(x: Double, y: Double) {
                    add(x, y)
                    current = Point(x, y)
                }

                // A quadratic curve turns back at most once on each axis.
                override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) {
                    add(x, y)
                    fun turn(from: Double, control: Double, to: Double): Double? {
                        val t = (from - control) / (from - 2 * control + to)
                        return if (t > 0 && t < 1) t else null
                    }
                    for (t in listOfNotNull(turn(current.x, controlX, x), turn(current.y, controlY, y))) {
                        val u = 1 - t
                        add(u * u * current.x + 2 * u * t * controlX + t * t * x, u * u * current.y + 2 * u * t * controlY + t * t * y)
                    }
                    current = Point(x, y)
                }

                // A cubic curve turns back at most twice on each axis, where its derivative, a quadratic, is zero.
                override fun curve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double) {
                    add(x, y)
                    fun turns(from: Double, control1: Double, control2: Double, to: Double): List<Double> {
                        val a = to - 3 * control2 + 3 * control1 - from
                        val b = 2 * (from - 2 * control1 + control2)
                        val c = control1 - from
                        val roots = if (a == 0.0) {
                            if (b == 0.0) emptyList() else listOf(-c / b)
                        } else {
                            val discriminant = b * b - 4 * a * c
                            if (discriminant < 0) emptyList() else sqrt(discriminant).let { listOf((-b + it) / (2 * a), (-b - it) / (2 * a)) }
                        }
                        return roots.filter { it > 0 && it < 1 }
                    }
                    for (t in turns(current.x, control1X, control2X, x) + turns(current.y, control1Y, control2Y, y)) {
                        val u = 1 - t
                        add(
                            u * u * u * current.x + 3 * u * u * t * control1X + 3 * u * t * t * control2X + t * t * t * x,
                            u * u * u * current.y + 3 * u * u * t * control1Y + 3 * u * t * t * control2Y + t * t * t * y,
                        )
                    }
                    current = Point(x, y)
                }

                // The arc's ends, and each quarter of the circle it passes, where it reaches furthest on an axis.
                override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) {
                    val first = arcPoint(centerX, centerY, radius, startAngle)
                    add(first.x, first.y)
                    current = arcPoint(centerX, centerY, radius, startAngle + delta)
                    add(current.x, current.y)
                    val quarter = PI / 2
                    val from = ceil(min(startAngle, startAngle + delta) / quarter).toLong()
                    val to = floor(max(startAngle, startAngle + delta) / quarter).toLong()
                    for (k in from..to) {
                        when (k.mod(4L)) {
                            0L -> add(centerX + radius, centerY)
                            1L -> add(centerX, centerY + radius)
                            2L -> add(centerX - radius, centerY)
                            else -> add(centerX, centerY - radius)
                        }
                    }
                }

                override fun ellipse(x: Double, y: Double, width: Double, height: Double) {
                    add(x, y)
                    add(x + width, y + height)
                    start = Point(x + width, y + height / 2)
                    current = start
                }

                override fun close() {
                    current = start
                }
            })
            if (minX > maxX) return Rect.Null
            return Rect(minX, minY, maxX - minX, maxY - minY)
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
        replay(object : Sink {
            override fun move(x: Double, y: Double) {
                begin(Point(x, y))
            }

            override fun line(x: Double, y: Double) {
                val to = Point(x, y)
                (run ?: begin(to)).add(to)
            }

            override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) {
                val points = run ?: return
                val from = points.last()
                val steps = 32
                for (step in 1..steps) {
                    val t = step.toDouble() / steps
                    val u = 1 - t
                    points.add(Point(u * u * from.x + 2 * u * t * controlX + t * t * x, u * u * from.y + 2 * u * t * controlY + t * t * y))
                }
            }

            override fun curve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double) {
                val points = run ?: return
                val from = points.last()
                val steps = 32
                for (step in 1..steps) {
                    val t = step.toDouble() / steps
                    val u = 1 - t
                    points.add(
                        Point(
                            u * u * u * from.x + 3 * u * u * t * control1X + 3 * u * t * t * control2X + t * t * t * x,
                            u * u * u * from.y + 3 * u * u * t * control1Y + 3 * u * t * t * control2Y + t * t * t * y,
                        ),
                    )
                }
            }

            override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) {
                val first = arcPoint(centerX, centerY, radius, startAngle)
                val points = run?.also { it.add(first) } ?: begin(first)
                val steps = max(1, ceil(abs(delta) / (PI / 180)).toInt())
                for (step in 1..steps) points.add(arcPoint(centerX, centerY, radius, startAngle + delta * step / steps))
            }

            override fun ellipse(x: Double, y: Double, width: Double, height: Double) {
                val rect = Rect(x, y, width, height)
                val points = begin(Point(rect.maxX, rect.midY))
                val steps = 180
                for (step in 1..steps) {
                    val angle = 2 * PI * step / steps
                    points.add(Point(rect.midX + rect.width / 2 * cos(angle), rect.midY + rect.height / 2 * sin(angle)))
                }
                begin(points.first())
            }

            // Closing draws back to the subpath's start, where whatever follows starts.
            override fun close() {
                val back = start ?: return
                run?.add(back)
                begin(back)
            }
        })
        return runs
    }

    private fun arcPoint(centerX: Double, centerY: Double, radius: Double, angle: Double): Point =
        Point(centerX + radius * cos(angle), centerY + radius * sin(angle))

    companion object {
        private const val MOVE: Byte = 0
        private const val LINE: Byte = 1
        private const val QUAD: Byte = 2
        private const val ARC: Byte = 3
        private const val ELLIPSE: Byte = 4
        private const val CLOSE: Byte = 5
        private const val CURVE: Byte = 6

        /** How many numbers each verb takes. */
        private val COUNTS = intArrayOf(2, 2, 4, 5, 4, 0, 6)

        // SwiftUI's continuous corner, in its own single-precision numbers and in radii: where the corner leaves a side
        // and its first two control points, on a side with room to spare (FULL) and on one with none (TIGHT), and the
        // ends and control points of its middle curve.
        private val CORNER_STRETCH = 1.528665f.toDouble() - 1
        private val FULL_CONTROL1 = 1.08849f.toDouble()
        private val FULL_CONTROL2 = 0.868407f.toDouble()
        private val TIGHT_CONTROL1 = 0.96f.toDouble()
        private val TIGHT_CONTROL2 = 0.82f.toDouble()
        private val MIDDLE_END1 = 0.0749114f.toDouble()
        private val MIDDLE_END2 = 0.631494f.toDouble()
        private val MIDDLE_CONTROL1 = 0.16906f.toDouble()
        private val MIDDLE_CONTROL2 = 0.372824f.toDouble()

        private fun single(value: Double): Double = value.toFloat().toDouble()

        /** A continuous corner's reach along one side, in radii, for a side `half` long from its middle to the corner. */
        private class CornerReach(half: Double, radius: Double) {
            // How much of its full length the side gives the corner, from 0 to 1, in Float as SwiftUI works it out.
            private val room = swiftMin(1.0, ((half.toFloat() - radius.toFloat()) / (radius.toFloat() * CORNER_STRETCH.toFloat())).toDouble())
            val start = single(1 + CORNER_STRETCH * room)
            val control1 = single(TIGHT_CONTROL1 + (FULL_CONTROL1 - TIGHT_CONTROL1) * room)
            val control2 = single(TIGHT_CONTROL2 + (FULL_CONTROL2 - TIGHT_CONTROL2) * room)
        }

        /** `Path(_: CGRect)`: from the top left corner, clockwise on screen. */
        fun rect(rect: Rect): MapPath = MapPath().also {
            it.move(rect.minX, rect.minY)
            it.addLine(rect.maxX, rect.minY)
            it.addLine(rect.maxX, rect.maxY)
            it.addLine(rect.minX, rect.maxY)
            it.closeSubpath()
        }

        /**
         * `Path(roundedRect:cornerRadius:)` and `RoundedRectangle(cornerRadius:)`, whose corners are continuous unless
         * Swift asks for `.circular`: each corner eases into its sides through three cubic curves rather than meeting
         * them as a quarter circle. The elements are SwiftUI's own, in its order: from the right middle, down, then
         * round clockwise on screen. The radius stops at half the shorter side, and on a side with less than about
         * 1.53 radii from its middle to a corner, that side's curves draw in toward a quarter circle. A radius of exactly
         * 0 gives `Path(_: CGRect)`; a negative one, or no width or height, the four sides from the right middle.
         */
        fun roundedRect(rect: Rect, cornerRadius: Double): MapPath {
            if (cornerRadius == 0.0) return MapPath.rect(rect)
            val minX = rect.minX
            val minY = rect.minY
            val maxX = rect.maxX
            val maxY = rect.maxY
            val midY = (minY + maxY) / 2
            val radius = swiftMin(cornerRadius, swiftMin(rect.width, rect.height) / 2)
            val path = MapPath()
            path.move(maxX, midY)
            if (!(radius > 0)) {
                path.addLine(maxX, maxY)
                path.addLine(minX, maxY)
                path.addLine(minX, minY)
                path.addLine(maxX, minY)
                path.closeSubpath()
                return path
            }
            val x = CornerReach(rect.width / 2, radius)
            val y = CornerReach(rect.height / 2, radius)
            fun right(reach: Double) = maxX - radius * reach
            fun left(reach: Double) = minX + radius * reach
            fun bottom(reach: Double) = maxY - radius * reach
            fun top(reach: Double) = minY + radius * reach
            val end1 = MIDDLE_END1
            val end2 = MIDDLE_END2
            val control1 = MIDDLE_CONTROL1
            val control2 = MIDDLE_CONTROL2
            path.addLine(maxX, bottom(y.start))
            path.addCurve(right(end1), bottom(end2), maxX, bottom(y.control1), maxX, bottom(y.control2))
            path.addCurve(right(end2), bottom(end1), right(control1), bottom(control2), right(control2), bottom(control1))
            path.addCurve(right(x.start), maxY, right(x.control2), maxY, right(x.control1), maxY)
            path.addLine(left(x.start), maxY)
            path.addCurve(left(end2), bottom(end1), left(x.control1), maxY, left(x.control2), maxY)
            path.addCurve(left(end1), bottom(end2), left(control2), bottom(control1), left(control1), bottom(control2))
            path.addCurve(minX, bottom(y.start), minX, bottom(y.control2), minX, bottom(y.control1))
            path.addLine(minX, top(y.start))
            path.addCurve(left(end1), top(end2), minX, top(y.control1), minX, top(y.control2))
            path.addCurve(left(end2), top(end1), left(control1), top(control2), left(control2), top(control1))
            path.addCurve(left(x.start), minY, left(x.control2), minY, left(x.control1), minY)
            path.addLine(right(x.start), minY)
            path.addCurve(right(end2), top(end1), right(x.control1), minY, right(x.control2), minY)
            path.addCurve(right(end1), top(end2), right(control2), top(control1), right(control1), top(control2))
            path.addCurve(maxX, top(y.start), maxX, top(y.control2), maxX, top(y.control1))
            path.closeSubpath()
            return path
        }

        /**
         * `UnevenRoundedRectangle(cornerRadii:)`, continuous as SwiftUI's default: [roundedRect]'s corners, each with
         * its own radius, from the right middle (halfway between the right side's two corners), down, then round
         * clockwise on screen. Leading and trailing are left and right. A negative radius counts as 0. Where a side's
         * two radii add up to more than the side, both stop at half the side when both pass it, else the larger
         * stops at what the smaller leaves: the top and bottom sides first, then the left and right ones with the
         * radii that leaves. Each corner then takes its radius's share of each side, and like [roundedRect]'s draws in
         * toward a quarter circle where that share is less than about 1.53 radii. Radii of exactly 0 give
         * `Path(_: CGRect)`; radii that all come to 0 otherwise, the four sides from the right middle. A corner of
         * radius 0 is still a line and three curves, all to the corner.
         */
        fun unevenRoundedRect(rect: Rect, radii: RectangleCornerRadii): MapPath {
            if (radii.topLeading == 0.0 && radii.bottomLeading == 0.0 && radii.bottomTrailing == 0.0 && radii.topTrailing == 0.0) {
                return rect(rect)
            }
            val minX = rect.minX
            val minY = rect.minY
            val maxX = rect.maxX
            val maxY = rect.maxY
            val width = rect.width
            val height = rect.height
            fun given(radius: Double) = swiftMax(0.0, single(radius))
            var topLeft = given(radii.topLeading)
            var bottomLeft = given(radii.bottomLeading)
            var bottomRight = given(radii.bottomTrailing)
            var topRight = given(radii.topTrailing)
            fun held(radius: Double, other: Double, side: Double) = swiftMin(radius, swiftMax(side / 2, side - other))
            held(topLeft, topRight, width).let { topRight = held(topRight, topLeft, width); topLeft = it }
            held(bottomRight, bottomLeft, width).let { bottomLeft = held(bottomLeft, bottomRight, width); bottomRight = it }
            held(topRight, bottomRight, height).let { bottomRight = held(bottomRight, topRight, height); topRight = it }
            held(bottomLeft, topLeft, height).let { topLeft = held(topLeft, bottomLeft, height); bottomLeft = it }
            val path = MapPath()
            if (topLeft == 0.0 && bottomLeft == 0.0 && bottomRight == 0.0 && topRight == 0.0) {
                path.move(maxX, (minY + maxY) / 2)
                path.addLine(maxX, maxY)
                path.addLine(minX, maxY)
                path.addLine(minX, minY)
                path.addLine(maxX, minY)
                path.closeSubpath()
                return path
            }
            fun share(side: Double, radius: Double, other: Double) = if (radius == 0.0) 0.0 else side * radius / (radius + other)
            path.move(maxX, (minY + topRight + maxY - bottomRight) / 2)
            path.addUnevenCorner(bottomRight, share(width, bottomRight, bottomLeft), share(height, bottomRight, topRight), maxX, maxY, -1.0, -1.0, downFirst = true)
            path.addUnevenCorner(bottomLeft, share(width, bottomLeft, bottomRight), share(height, bottomLeft, topLeft), minX, maxY, 1.0, -1.0, downFirst = false)
            path.addUnevenCorner(topLeft, share(width, topLeft, topRight), share(height, topLeft, bottomLeft), minX, minY, 1.0, 1.0, downFirst = true)
            path.addUnevenCorner(topRight, share(width, topRight, topLeft), share(height, topRight, bottomRight), maxX, minY, -1.0, 1.0, downFirst = false)
            path.closeSubpath()
            return path
        }

        /**
         * One of [unevenRoundedRect]'s corners at (`cornerX`, `cornerY`), reaching into the rectangle along
         * (`inwardX`, `inwardY`): from its side up or down when `downFirst` (the vertical side comes first), else from its
         * top or bottom side.
         */
        private fun MapPath.addUnevenCorner(
            radius: Double,
            acrossShare: Double,
            downShare: Double,
            cornerX: Double,
            cornerY: Double,
            inwardX: Double,
            inwardY: Double,
            downFirst: Boolean,
        ) {
            val across = UnevenReach(acrossShare, radius)
            val down = UnevenReach(downShare, radius)
            fun x(reach: Double) = cornerX + inwardX * radius * reach
            fun y(reach: Double) = cornerY + inwardY * radius * reach
            if (downFirst) {
                addLine(cornerX, y(down.start))
                addCurve(x(MIDDLE_END1), y(MIDDLE_END2), cornerX, y(down.control1), cornerX, y(down.control2))
                addCurve(x(MIDDLE_END2), y(MIDDLE_END1), x(MIDDLE_CONTROL1), y(MIDDLE_CONTROL2), x(MIDDLE_CONTROL2), y(MIDDLE_CONTROL1))
                addCurve(x(across.start), cornerY, x(across.control2), cornerY, x(across.control1), cornerY)
            } else {
                addLine(x(across.start), cornerY)
                addCurve(x(MIDDLE_END2), y(MIDDLE_END1), x(across.control1), cornerY, x(across.control2), cornerY)
                addCurve(x(MIDDLE_END1), y(MIDDLE_END2), x(MIDDLE_CONTROL2), y(MIDDLE_CONTROL1), x(MIDDLE_CONTROL1), y(MIDDLE_CONTROL2))
                addCurve(cornerX, y(down.start), cornerX, y(down.control2), cornerX, y(down.control1))
            }
        }

        /**
         * An uneven corner's reach along one side, in radii, for its `share` of that side: as [CornerReach], with the
         * room worked out in Double and then rounded to Float, as SwiftUI's uneven rectangle does.
         */
        private class UnevenReach(share: Double, radius: Double) {
            private val room = if (radius > 0) swiftMin(1.0, single((share - radius) / (radius * CORNER_STRETCH))) else 0.0
            val start = single(1 + CORNER_STRETCH * room)
            val control1 = single(TIGHT_CONTROL1 + (FULL_CONTROL1 - TIGHT_CONTROL1) * room)
            val control2 = single(TIGHT_CONTROL2 + (FULL_CONTROL2 - TIGHT_CONTROL2) * room)
        }

        fun ellipse(rect: Rect): MapPath = MapPath().also {
            it.add(ELLIPSE, 4)
            it.numbers[it.numberCount++] = rect.minX
            it.numbers[it.numberCount++] = rect.minY
            it.numbers[it.numberCount++] = rect.width
            it.numbers[it.numberCount++] = rect.height
        }
    }
}

/**
 * SwiftUI's UnitCurve for a cubic Bézier from (0, 0) to (1, 1): the curve's height where it is `progress` across,
 * found by Newton's method, then by halving where that does not settle.
 */
class UnitCurve private constructor(val startControlPoint: Point, val endControlPoint: Point) {
    private val cx = 3 * startControlPoint.x
    private val bx = 3 * (endControlPoint.x - startControlPoint.x) - cx
    private val ax = 1 - cx - bx
    private val cy = 3 * startControlPoint.y
    private val by = 3 * (endControlPoint.y - startControlPoint.y) - cy
    private val ay = 1 - cy - by

    private fun x(t: Double): Double = ((ax * t + bx) * t + cx) * t
    private fun y(t: Double): Double = ((ay * t + by) * t + cy) * t
    private fun slope(t: Double): Double = (3 * ax * t + 2 * bx) * t + cx

    private fun ySlope(t: Double): Double = (3 * ay * t + 2 * by) * t + cy

    fun value(progress: Double): Double = y(solve(swiftMin(1.0, swiftMax(0.0, progress))))

    /**
     * `velocity(at:)`: how fast the curve rises where it is `progress` across, its height per unit across. A curve
     * whose control points lie on the diagonal is the straight line, 1 everywhere. At an end whose control point sits
     * on that end the slope is 0 / 0, which SwiftUI reads as 1 at the start and 0 at the finish (`.easeOut` starts at
     * 1, `.easeIn` ends at 0); a slope with nowhere to go across there is infinite, as SwiftUI's.
     */
    fun velocity(progress: Double): Double {
        if (startControlPoint.x == startControlPoint.y && endControlPoint.x == endControlPoint.y) return 1.0
        val x = swiftMin(1.0, swiftMax(0.0, progress))
        val (across, up) = if (x == 0.0) {
            3 * startControlPoint.x to 3 * startControlPoint.y
        } else if (x == 1.0) {
            3 * (1 - endControlPoint.x) to 3 * (1 - endControlPoint.y)
        } else {
            solve(x).let { slope(it) to ySlope(it) }
        }
        if (across == 0.0 && up == 0.0) return if (x == 0.0) 1.0 else 0.0
        return up / across
    }

    /** The curve's parameter where it is `x` across: Newton's method, then halving where that does not settle. */
    private fun solve(x: Double): Double {
        var t = x
        repeat(16) {
            val error = x(t) - x
            if (abs(error) < 1e-14) return t
            val slope = slope(t)
            if (abs(slope) < 1e-12) return@repeat
            t -= error / slope
        }
        var low = 0.0
        var high = 1.0
        t = x
        repeat(200) {
            val error = x(t) - x
            if (abs(error) < 1e-14) return t
            if (error > 0) high = t else low = t
            t = (low + high) / 2
        }
        return t
    }

    companion object {
        fun bezier(startControlPoint: Point, endControlPoint: Point): UnitCurve = UnitCurve(startControlPoint, endControlPoint)

        val easeInOut = bezier(Point(0.42, 0.0), Point(0.58, 1.0))

        /** The curve of `.easeIn` animations. */
        val easeIn = bezier(Point(0.42, 0.0), Point(1.0, 1.0))

        /** The curve of `.easeOut` animations. */
        val easeOut = bezier(Point(0.0, 0.0), Point(0.58, 1.0))

        /** The curve of `.linear` animations: its height is how far across it is. */
        val linear = bezier(Point(0.0, 0.0), Point(1.0, 1.0))
    }
}

/**
 * SwiftUI's `Spring(duration:bounce:)`: a unit mass on a spring stiff and damped enough to take about `duration` seconds,
 * overshooting more the more it bounces, and creeping in more slowly the further its bounce is below zero
 * (overdamped). Compose's own spring is the same model: `spring(dampingRatio, stiffness)`.
 *
 * SwiftUI's `stiffness` of an overdamped spring reads (2 × dampingRatio² − 1) times the one here, but its own
 * `value(target:initialVelocity:time:)` moves with the one here, (2π / duration)², as Compose's spring does.
 */
data class Spring(val duration: Double = 0.5, val bounce: Double = 0.0) {
    init {
        require(bounce > -1 && bounce < 1) { "bounce $bounce" }
    }

    val mass: Double get() = 1.0
    val stiffness: Double get() = (2 * PI / duration).let { it * it }
    val damping: Double get() = if (bounce >= 0) 4 * PI * (1 - bounce) / duration else 4 * PI / (duration * (1 + bounce))
    val dampingRatio: Double get() = damping / (2 * sqrt(stiffness * mass))

    /**
     * `value(target:initialVelocity:time:)`: where the spring is `time` seconds after leaving zero toward `target`,
     * moving at `initialVelocity`; zero until it leaves, and the target once infinitely long has passed, where
     * SwiftUI's own formula gives NaN.
     */
    fun value(target: Double, initialVelocity: Double = 0.0, time: Double): Double {
        if (!(time > 0)) return 0.0
        if (time == Double.POSITIVE_INFINITY) return target
        val naturalFrequency = sqrt(stiffness / mass)
        val ratio = dampingRatio
        val start = -target
        if (bounce < 0) {
            val (fast, slow) = overdamped(naturalFrequency, ratio, start, initialVelocity)
            return target + fast.amount * exp(fast.rate * time) + slow.amount * exp(slow.rate * time)
        }
        if (bounce == 0.0) {
            val rate = exp(-naturalFrequency * time)
            return target + rate * (start + (naturalFrequency * start + initialVelocity) * time)
        }
        val frequency = naturalFrequency * sqrt(1 - ratio * ratio)
        val sine = (ratio * naturalFrequency * start + initialVelocity) / frequency
        return target + exp(-ratio * naturalFrequency * time) * (start * cos(frequency * time) + sine * sin(frequency * time))
    }

    /**
     * `velocity(target:initialVelocity:time:)`: how fast the spring of `value` moves at `time`, in units a second;
     * `initialVelocity` until it leaves, and still once infinitely long has passed. SwiftUI's own adds the target to
     * an overdamped spring's speed; this is the speed `value` moves at.
     */
    fun velocity(target: Double, initialVelocity: Double = 0.0, time: Double): Double {
        if (!(time > 0)) return initialVelocity
        if (time == Double.POSITIVE_INFINITY) return 0.0
        val naturalFrequency = sqrt(stiffness / mass)
        val ratio = dampingRatio
        val start = -target
        if (bounce < 0) {
            val (fast, slow) = overdamped(naturalFrequency, ratio, start, initialVelocity)
            return fast.amount * fast.rate * exp(fast.rate * time) + slow.amount * slow.rate * exp(slow.rate * time)
        }
        if (bounce == 0.0) {
            val pull = naturalFrequency * start + initialVelocity
            return exp(-naturalFrequency * time) * (initialVelocity - naturalFrequency * pull * time)
        }
        val decay = ratio * naturalFrequency
        val frequency = naturalFrequency * sqrt(1 - ratio * ratio)
        val sine = (decay * start + initialVelocity) / frequency
        return exp(-decay * time) *
            ((sine * frequency - decay * start) * cos(frequency * time) - (start * frequency + decay * sine) * sin(frequency * time))
    }

    /** An overdamped spring's two decaying parts, from `start` away from rest at `initialVelocity`. */
    private fun overdamped(naturalFrequency: Double, ratio: Double, start: Double, initialVelocity: Double): Pair<Decay, Decay> {
        val spread = naturalFrequency * sqrt(ratio * ratio - 1)
        val fastRate = -ratio * naturalFrequency - spread
        val slowRate = -ratio * naturalFrequency + spread
        val slowAmount = (initialVelocity - fastRate * start) / (slowRate - fastRate)
        return Decay(start - slowAmount, fastRate) to Decay(slowAmount, slowRate)
    }

    private data class Decay(val amount: Double, val rate: Double)

    companion object {
        /**
         * `Spring(response:dampingRatio:)`, as `.spring(response:dampingFraction:)` builds it: the duration is the
         * response, and a damping ratio above 1 is the overdamped bounce 1 / ratio − 1.
         */
        fun response(response: Double, dampingRatio: Double): Spring =
            Spring(response, if (dampingRatio <= 1) 1 - dampingRatio else 1 / dampingRatio - 1)
    }
}

/** A SwiftUI animation as numbers, for :app to run on its own clock. */
sealed interface MotionCurve {
    /** How far along the change is `time` seconds after it starts: 0 before, 1 once done, past 1 as a spring overshoots. */
    fun value(time: Double): Double

    /** `.easeOut(duration:)`, after `.delay(_:)`: Compose's `tween` with `CubicBezierEasing(0f, 0f, 0.58f, 1f)`. */
    data class EaseOut(val duration: Double, val delay: Double = 0.0) : MotionCurve {
        override fun value(time: Double): Double = UnitCurve.easeOut.value((time - delay) / duration)
    }

    /**
     * Any other timing curve over `duration` seconds after `delay`: `.linear`, `.easeIn`, `.easeInOut`,
     * `.timingCurve`. Compose's `tween` with the curve's easing.
     */
    data class Timing(val curve: UnitCurve, val duration: Double, val delay: Double = 0.0) : MotionCurve {
        override fun value(time: Double): Double = curve.value((time - delay) / duration)
    }

    /** `.spring(duration:bounce:)`, from rest: Compose's `spring(dampingRatio, stiffness)` with the spring's. */
    data class SpringCurve(val spring: Spring) : MotionCurve {
        override fun value(time: Double): Double = spring.value(1.0, time = time)
    }
}
