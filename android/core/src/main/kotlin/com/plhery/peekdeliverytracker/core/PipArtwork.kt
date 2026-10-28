package com.plhery.peekdeliverytracker.core

import kotlin.math.PI

// Pip.swift's drawing (MARK: Drawing): PipArtwork's GraphicsContext code, recording MapDrawing steps for :app to draw.

/** Draws Pip in his 300 × 310 frame: scale the context to the size wanted first. */
object PipArtwork {
    private fun flap(
        context: MapDrawingContext,
        palette: PipPalette,
        paper: PipGeometry.Flap,
        color: AdaptiveColor,
        at: Double,
        opacity: Double = 1.0,
    ) {
        val progress = at
        if (!(opacity > 0)) return
        val layer = context.copy()
        layer.opacity *= opacity
        val path = PipGeometry.polygon(if (progress == 0.0) paper.closed else if (progress == 1.0) paper.opened else paper.points(progress))
        layer.fill(path, color)
        layer.stroke(path, palette.hairline.opacity(0.24), lineWidth = 0.7)
    }

    /** The box up to its sides: closed, or with its rear flaps folded open. The face goes on next, then `frontFlaps`. */
    fun box(context: MapDrawingContext, palette: PipPalette, open: Boolean) {
        box(context, palette, opening = if (open) PipOpening.open else PipOpening.closed)
    }

    /** The same box on its way open. */
    fun box(context: MapDrawingContext, palette: PipPalette, opening: PipOpening) {
        context.fill(PipGeometry.polygon(PipGeometry.inside), palette.inside)
        flap(context, palette, PipGeometry.backLeft, palette.backLeft, at = opening.rear, opacity = opening.under)
        flap(context, palette, PipGeometry.backRight, palette.backRight, at = opening.rear)
        context.fill(PipGeometry.polygon(PipGeometry.leftSide), palette.left)
        context.fill(PipGeometry.polygon(PipGeometry.rightSide), palette.right)
        context.fill(PipGeometry.polygon(PipGeometry.leftSide), palette.highlight.opacity(0.07))
        context.stroke(PipGeometry.hairlines, palette.hairline.opacity(0.25), lineWidth = 0.8)
        context.stroke(PipGeometry.edges, palette.highlight.opacity(0.4), lineWidth = 1.0)
    }

    /** The front flaps, over the face: closed on the lid, or open just above level, the left one a brim over the eyes. */
    fun frontFlaps(context: MapDrawingContext, palette: PipPalette, open: Boolean) {
        frontFlaps(context, palette, opening = if (open) PipOpening.open else PipOpening.closed)
    }

    /** The same flaps on their way open: up through upright, and out over the face. */
    fun frontFlaps(context: MapDrawingContext, palette: PipPalette, opening: PipOpening) {
        flap(context, palette, PipGeometry.frontRight, palette.frontRight, at = opening.front, opacity = opening.under)
        flap(context, palette, PipGeometry.frontLeft, palette.frontLeft, at = opening.front)
    }

    /**
     * The carrier's label on the box's right side: its truck, its name in its own colour, a barcode and the tracking
     * number. The label's paper is light in either appearance, so the name takes the brand's light colour.
     */
    fun label(context: MapDrawingContext, label: PipLabel) {
        val measures = PipGeometry.Label
        val ink = AdaptiveColor(measures.ink)
        val paper = context.copy()
        paper.concatenate(PipGeometry.labelPlane)
        paper.scaleBy(measures.SCALE, measures.SCALE)
        paper.fill(
            MapPath.roundedRect(Rect(0.0, 0.0, measures.size.width, measures.size.height), measures.CORNER_RADIUS),
            AdaptiveColor(measures.paper),
        )
        val truck = paper.copy()
        truck.translateBy(measures.truck.x, measures.truck.y)
        truck.scaleBy(0.5, 0.5)
        truck.draw(CarrierTruckGeometry.draw(label.identity))
        val name = MapFont(measures.NAME_SIZE, MapFont.Weight.Heavy, italic = label.identity.family == "dhl")
        paper.text(label.identity.name, name, AdaptiveColor(RgbaColor.hex(label.identity.colors[4])), measures.nameStart, measures.nameRoom)
        val bars = MapPath()
        for (x in measures.bars) {
            bars.move(x, measures.BARS_TOP)
            bars.addLine(x, measures.BARS_BOTTOM)
        }
        paper.stroke(bars, ink, lineWidth = measures.BAR_WIDTH)
        val number = label.number?.takeIf { it.isNotEmpty() } ?: return
        val digits = MapFont(measures.NUMBER_SIZE, MapFont.Weight.Regular, monospaced = true)
        paper.text(number, digits, ink, Point(measures.MARGIN, measures.NUMBER_BASELINE), measures.numberRoom, tracking = measures.NUMBER_SPACING)
    }

    /** The tape and its dashed seam, across a closed box. */
    fun tape(context: MapDrawingContext, palette: PipPalette) {
        context.fill(PipGeometry.polygon(PipGeometry.tape), palette.tape)
        val seam = MapPath()
        seam.move(PipGeometry.seam[0])
        for (point in PipGeometry.seam.drop(1)) seam.addLine(point)
        context.stroke(seam, palette.seam.opacity(0.6), StrokeStyle(lineWidth = 1.0, dash = listOf(3.0, 3.0)))
    }

    /** The tape as the box opens: it lifts off its seam and is gone. */
    private fun tape(context: MapDrawingContext, palette: PipPalette, opening: PipOpening) {
        if (!(opening.tape > 0)) return
        val lifted = context.copy()
        lifted.opacity *= opening.tape
        lifted.translateBy(-6 * opening.lift, -19 * opening.lift)
        lifted.concatenate(rotation(degrees = -8 * opening.lift, around = PipGeometry.seam[0]))
        tape(lifted, palette)
    }

    /** How a mood holds him: how far off the ground, how tilted, and how wide his shadow. */
    private class Stance(val lift: Double, val tilt: Double, val shadow: Double)

    private fun stance(mood: PipMood): Stance = when (mood) {
        PipMood.Eager -> Stance(PipGeometry.EAGER_LIFT, -5.0, 69.0)
        PipMood.Worry -> Stance(0.0, 3.0, 84.0)
        PipMood.Look, PipMood.Wait, PipMood.Joy -> Stance(0.0, 0.0, 84.0)
    }

    /**
     * Pip in a card's ink, with the face and the pose of his mood. `time` moves him; null keeps him still. `opening`
     * draws his box on its way open, from the mood he was in `before` the news.
     */
    fun ink(
        context: MapDrawingContext,
        palette: PipPalette,
        mood: PipMood,
        side: Int,
        below: Boolean,
        time: Double?,
        opening: PipOpening? = null,
        before: PipMood? = null,
    ) {
        val state = opening ?: if (mood == PipMood.Joy) PipOpening.open else PipOpening.closed
        val was = if (opening == null) mood else before ?: mood
        val from = stance(was)
        val to = stance(mood)
        val settled = if (was == mood) 1.0 else state.stance
        val reach = from.shadow + (to.shadow - from.shadow) * settled
        context.fill(MapPath.ellipse(Rect(150 - reach, 276.0, reach * 2, 20.0)), palette.shadow)
        // He hurries toward the dot, so his speed lines trail on the far side; they go once he is there.
        val hurry = if (mood == PipMood.Eager) 1.0 else if (was == PipMood.Eager) 1 - settled else 0.0
        if (hurry > 0) {
            val lines = MapPath()
            for ((start, end, y) in listOf(Triple(14.0, 40.0, 170.0), Triple(2.0, 36.0, 196.0), Triple(18.0, 40.0, 222.0))) {
                val mirrored = side < 0
                lines.move(if (mirrored) PipGeometry.frame.width - start else start, y)
                lines.addLine(if (mirrored) PipGeometry.frame.width - end else end, y)
            }
            context.stroke(lines, palette.speedLines.opacity(hurry), StrokeStyle(lineWidth = 7.0, lineCap = StrokeStyle.LineCap.Round))
        }

        val body = context.copy()
        // Lifted and tilted as the mood has it, then moved about the bottom of the box.
        body.translateBy(0.0, -(from.lift + (to.lift - from.lift) * settled))
        body.concatenate(rotation(degrees = from.tilt + (to.tilt - from.tilt) * settled, around = Point(150.0, 230.0)))
        if (time != null) body.concatenate(motion(mood, time))

        box(body, palette, opening = state)
        val face = body.copy()
        face.concatenate(PipGeometry.facePlane)
        if (was != mood && state.faceBefore > 0) {
            val gone = face.copy()
            gone.opacity *= state.faceBefore
            inkFace(gone, palette, was, side, below)
        }
        face.opacity *= if (was == mood) 1.0 else state.face
        inkFace(face, palette, mood, side, below)
        frontFlaps(body, palette, opening = state)
        tape(body, palette, opening = state)
        if (mood == PipMood.Joy) glints(body, palette, time, state.glints)
    }

    /** The glints around an open box, each twinkling on its own clock. */
    private fun glints(context: MapDrawingContext, palette: PipPalette, time: Double?, opacity: Double) {
        if (!(opacity > 0)) return
        for ((index, glint) in PipGeometry.glints.withIndex()) {
            val twinkle = if (time != null) twinkle(index, time) else Twinkle(1.0, 1.0, 0.0)
            val star = context.copy()
            star.opacity *= twinkle.opacity * opacity
            star.concatenate(
                AffineTransform.translation(glint.center.x, glint.center.y)
                    .rotated(twinkle.degrees * PI / 180)
                    .scaledBy(glint.size / 2 * twinkle.scale, glint.size / 2 * twinkle.scale),
            )
            star.fill(PipGeometry.glint, palette.glint(index))
        }
    }

    /**
     * The kraft parcel at sticker size: closed, with a face large enough to read, and its carrier's label when it has
     * one. `happy` and `blink` are the face's.
     */
    fun sticker(context: MapDrawingContext, label: PipLabel? = null, happy: Double = 0.0, blink: Double? = null) {
        box(context, PipPalette.kraft, open = false)
        if (label != null) label(context, label)
        kraftFace(context, k = 1.3, happy = happy, blink = blink)
        frontFlaps(context, PipPalette.kraft, open = false)
        tape(context, PipPalette.kraft)
    }

    /**
     * The kraft parcel's face. `k` scales the features: 1 at full size, larger on a small parcel. `happy` turns the
     * open eyes into arcs as the box opens. `blink` is the time on the blink's clock; null keeps the eyes open. `smile`
     * fades the mouth, which a gift's ribbon runs over.
     */
    fun kraftFace(
        context: MapDrawingContext,
        k: Double,
        look: Point = Point(0.5, -0.2),
        happy: Double,
        blink: Double? = null,
        smile: Double = 1.0,
    ) {
        val face = context.copy()
        face.concatenate(PipGeometry.facePlane)
        val palette = PipPalette.kraft
        for ((index, x) in listOf(48 - 15.5 * k, 48 + 15.5 * k).withIndex()) {
            if (happy < 1) {
                val eye = face.copy()
                eye.opacity = 1 - happy
                if (blink != null) {
                    // The white, the pupil and the catchlight close together, about the eye's own centre.
                    eye.translateBy(0.0, 36.0)
                    eye.scaleBy(1.0, PipBlink.eyeScale(blink, eye = index))
                    eye.translateBy(0.0, -36.0)
                }
                eye.fill(ellipse(x, 36.0, 10 * k, 11.5 * k), palette.eyeWhite)
                eye.fill(ellipse(x + look.x * 7 * k, 36 + look.y * 7 * k, 5.4 * k, 5.4 * k), palette.features)
                eye.fill(ellipse(x + (look.x * 7 - 1.8) * k, 36 + (look.y * 7 - 2) * k, 1.6 * k, 1.6 * k), palette.catchlight)
            }
            if (happy > 0) {
                val arc = MapPath()
                arc.move(x - 8 * k, 36 + 3 * k)
                arc.addQuadCurve(Point(x + 8 * k, 36 + 3 * k), control = Point(x, 36 - 8 * k))
                val eye = face.copy()
                eye.opacity = happy
                eye.stroke(arc, palette.features, StrokeStyle(lineWidth = 3 * k, lineCap = StrokeStyle.LineCap.Round))
            }
            face.fill(ellipse(x - 1, 36 + 15 * k, 6.5 * k, 3.2 * k), palette.blush.opacity(0.55))
        }
        if (!(smile > 0)) return
        val mouth = 47 + 4 * k
        val curve = MapPath()
        curve.move(48 - 6 * k, mouth)
        curve.addQuadCurve(Point(48 + 6 * k, mouth), control = Point(48.0, mouth + 7 * k))
        face.opacity = smile
        face.stroke(curve, palette.features, StrokeStyle(lineWidth = 2.4 * k, lineCap = StrokeStyle.LineCap.Round))
    }

    /** A gift's ribbon: over the lid and down both sides, between Pip's eyes, tied in a bow. */
    fun ribbon(context: MapDrawingContext) {
        val bands = MapPath()
        val sheen = MapPath()
        for ((from, to) in listOf(Point(102.5, 118.5) to Point(197.5, 166.0), Point(197.5, 118.5) to Point(102.5, 166.0))) {
            bands.move(from)
            bands.addLine(to)
            sheen.move(from)
            sheen.addLine(to)
        }
        for (x in listOf(102.5, 197.5)) {
            bands.move(x, 166.0)
            bands.addLine(x, 253.0)
        }
        context.stroke(bands, hex("#A286B5"), lineWidth = 9.0)
        context.stroke(sheen, hex("#CDB8DB").opacity(0.7), lineWidth = 2.0)

        val knot = Point(150.0, 142.0)
        val loops = MapPath()
        for (side in listOf(-1.0, 1.0)) {
            loops.move(knot)
            loops.addCurve(Point(150 + 26 * side, 142.0), control1 = Point(150 + 14 * side, 126.0), control2 = Point(150 + 34 * side, 130.0))
            loops.addCurve(knot, control1 = Point(150 + 21 * side, 149.0), control2 = Point(150 + 6 * side, 146.0))
            loops.closeSubpath()
        }
        context.fill(loops, hex("#B99BCB"))
        context.stroke(loops, hex("#7C6787").opacity(0.5), lineWidth = 1.0)
        val tails = MapPath()
        tails.move(knot)
        tails.addCurve(Point(130.0, 164.0), control1 = Point(144.0, 152.0), control2 = Point(138.0, 160.0))
        tails.move(knot)
        tails.addCurve(Point(172.0, 162.0), control1 = Point(156.0, 152.0), control2 = Point(163.0, 159.0))
        context.stroke(tails, hex("#A286B5"), StrokeStyle(lineWidth = 5.0, lineCap = StrokeStyle.LineCap.Round))
        context.fill(ellipse(knot.x, knot.y, 6.0, 4.5), hex("#8E6FA3"))
    }

    // The ink face is drawn at card size: eyes 21.75 either side of the middle, at 33 down the side.
    private val eyes = listOf(26.25, 69.75)
    private const val EYE_LEVEL = 33.0

    private class Tongue(val half: Double, val y: Double, val above: Double, val under: Double)

    private fun inkFace(face: MapDrawingContext, palette: PipPalette, mood: PipMood, side: Int, below: Boolean) {
        val features = palette.features
        fun round(width: Double) = StrokeStyle(width, StrokeStyle.LineCap.Round, StrokeStyle.LineJoin.Round)

        // In these moods he watches the dot: sideways, or straight up or down when he stands below or above it.
        fun toward(y: Double): Point = if (side == 0) Point(0.0, if (below) -0.45 else 0.3) else Point(0.44 * side, y)

        fun openEyes(rx: Double, ry: Double, pupil: Double, look: Point) {
            for (x in eyes) {
                val center = Point(x + look.x * 10.5, EYE_LEVEL + look.y * 10.5)
                face.fill(ellipse(x, EYE_LEVEL, rx, ry), palette.eyeWhite)
                face.fill(ellipse(center.x, center.y, pupil, pupil), features)
                face.fill(ellipse(center.x - 2.7, center.y - 3, 2.4, 2.4), palette.catchlight)
            }
        }

        fun blush() {
            for (x in eyes) face.fill(ellipse(x - 1, 55.5, 9.75, 4.8), palette.blush.opacity(0.55))
        }

        // An open mouth with its tongue: the corners, how deep it opens, and where the tongue sits.
        fun openMouth(half: Double, top: Double, depth: Double, line: Double, tongue: Tongue) {
            val mouth = MapPath()
            mouth.move(48 - half, top)
            mouth.addQuadCurve(Point(48 + half, top), control = Point(48.0, depth))
            mouth.closeSubpath()
            face.fill(mouth, features)
            face.stroke(mouth, features, round(line))
            val shape = MapPath()
            shape.move(48 - tongue.half, tongue.y)
            shape.addQuadCurve(Point(48 + tongue.half, tongue.y), control = Point(48.0, tongue.above))
            shape.addQuadCurve(Point(48 - tongue.half, tongue.y), control = Point(48.0, tongue.under))
            face.fill(shape, palette.blush)
        }

        fun curve(from: Point, control: Point, to: Point, width: Double) {
            val path = MapPath()
            path.move(from)
            path.addQuadCurve(to, control = control)
            face.stroke(path, features, round(width))
        }

        fun line(from: Point, to: Point) {
            val path = MapPath()
            path.move(from)
            path.addLine(to)
            face.stroke(path, features, round(3.6))
        }

        when (mood) {
            PipMood.Look -> {
                openEyes(15.0, 17.25, 8.1, toward(0.06))
                curve(Point(39.0, 57.0), Point(48.0, 67.5), Point(57.0, 57.0), 3.6)
            }
            PipMood.Eager -> {
                openEyes(17.25, 19.5, 6.3, toward(-0.12))
                blush()
                openMouth(10.5, 55.5, 72.0, 2.4, Tongue(5.4, 63.9, 60.3, 68.1))
            }
            PipMood.Wait -> {
                openEyes(15.0, 17.25, 8.1, Point(-0.24, -0.52))
                line(Point(41.25, 59.25), Point(54.75, 59.25))
            }
            PipMood.Worry -> {
                openEyes(15.0, 17.25, 8.1, Point(0.0, 0.16))
                line(Point(12.75, 9.0), Point(36.75, 8.5))
                line(Point(56.25, 7.0), Point(80.25, 4.5))
                curve(Point(39.0, 60.0), Point(48.0, 55.0), Point(57.0, 60.0), 3.6)
            }
            PipMood.Joy -> {
                for (x in eyes) {
                    curve(Point(x - 12, 37.5), Point(x, 21.0), Point(x + 12, 37.5), 4.5)
                }
                blush()
                openMouth(14.25, 53.25, 78.0, 2.7, Tongue(6.9, 66.0, 61.5, 71.4))
            }
        }
    }

    private fun ellipse(x: Double, y: Double, rx: Double, ry: Double): MapPath = MapPath.ellipse(Rect(x - rx, y - ry, rx * 2, ry * 2))

    private fun rotation(degrees: Double, around: Point): AffineTransform =
        AffineTransform.translation(around.x, around.y).rotated(degrees * PI / 180).translatedBy(-around.x, -around.y)

    private fun hex(hex: String): AdaptiveColor = AdaptiveColor(RgbaColor.hex(hex))

    private val spring = UnitCurve.bezier(Point(0.22, 1.0), Point(0.36, 1.0))

    /** How long one bob, or one jump, takes. */
    fun period(mood: PipMood): Double = if (mood == PipMood.Joy) 2.2 else 1.1

    private class Key(val at: Double, val rise: Double, val turn: Double, val across: Double, val up: Double)

    private val jump = listOf(
        Key(0.0, 0.0, 0.0, 1.0, 1.0), Key(0.12, 0.0, 0.0, 1.07, 0.9), Key(0.28, -10.0, -5.0, 0.96, 1.05),
        Key(0.44, 0.0, 2.0, 1.05, 0.95), Key(0.58, 0.0, 0.0, 1.0, 1.0), Key(1.0, 0.0, 0.0, 1.0, 1.0),
    )

    /** How the mood moves him, about the bottom of the box: an eager bob, or a joyful jump. */
    fun motion(mood: PipMood, at: Double): AffineTransform {
        val time = at
        // Rise, turn in degrees, and stretch across and up.
        val rise: Double
        val turn: Double
        val across: Double
        val up: Double
        when (mood) {
            PipMood.Eager -> {
                val phase = (time / period(PipMood.Eager)) % 1
                val swing = UnitCurve.easeInOut.value(if (phase < 0.5) phase * 2 else (1 - phase) * 2)
                rise = -3 * swing
                turn = -2 + 4.5 * swing
                across = 1.0
                up = 1.0
            }
            PipMood.Joy -> {
                // Within 0…1 for a time before the news too (the wall's clock set back while he held still), where the
                // iPhone's remainder keeps the sign and finds no key before it.
                val phase = ((time / period(PipMood.Joy)) % 1).let { if (it < 0) it + 1 else it }
                val next = jump.indexOfFirst { it.at > phase }.let { if (it < 0) jump.size - 1 else it }
                val from = jump[next - 1]
                val to = jump[next]
                val t = spring.value((phase - from.at) / (to.at - from.at))
                rise = from.rise + (to.rise - from.rise) * t
                turn = from.turn + (to.turn - from.turn) * t
                across = from.across + (to.across - from.across) * t
                up = from.up + (to.up - from.up) * t
            }
            PipMood.Look, PipMood.Wait, PipMood.Worry -> return AffineTransform.Identity
        }
        val pivot = Point(150.0, 277.0)
        return AffineTransform.translation(pivot.x, pivot.y + rise)
            .rotated(turn * PI / 180)
            .scaledBy(across, up)
            .translatedBy(-pivot.x, -pivot.y)
    }

    /** How a glint shows: its opacity, its size as a share of its own, and its turn in degrees. */
    data class Twinkle(val opacity: Double, val scale: Double, val degrees: Double)

    /** Each glint twinkles on its own clock: the one at `PipGeometry.glints[index]`, `at` seconds on it. */
    fun twinkle(index: Int, at: Double): Twinkle {
        val period = 2.2 + (index % 3).toDouble() * 0.5
        val phase = ((at - index.toDouble() * 0.37) / period) % 1
        val wrapped = if (phase < 0) phase + 1 else phase
        val glow = UnitCurve.easeInOut.value(if (wrapped < 0.5) wrapped * 2 else (1 - wrapped) * 2)
        return Twinkle(0.2 + 0.8 * glow, 0.6 + 0.5 * glow, -10 + 18 * glow)
    }
}
