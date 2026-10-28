package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.tan

/**
 * Pip's clocks and drawings against the iPhone's own code. pip-artwork.txt is what Pip.swift computes, printed on a Mac
 * by a swiftc harness: the file's model and drawing code as it is, run with a GraphicsContext that writes down each
 * thing it is asked to draw (its transform, its opacity, its colour resolved in sRGB, its stroke and its path) and with
 * text that measures 1000 wide, so each label line shows its start and its room. The carrier's truck is written down as
 * where it is drawn ("B"), its drawing being checked by BrandParityTests.
 */
class PipArtworkTests {
    private val golden: Map<String, List<String>> by lazy {
        val text = checkNotNull(PipArtworkTests::class.java.getResourceAsStream("/pip-artwork.txt")).bufferedReader().use { it.readText() }
        val sections = LinkedHashMap<String, MutableList<String>>()
        var section = ""
        for (line in text.lines()) {
            if (line.isBlank()) continue
            if (line.startsWith("# ")) section = line.removePrefix("# ").also { sections[it] = mutableListOf() } else sections.getValue(section) += line
        }
        sections
    }

    @Test
    fun theBoxOpensAsOnTheIPhone() {
        val actual = sample(-0.1, 1.2, 0.025).map { t ->
            val o = PipOpening.at(t)
            line("O", t, o.rear, o.front, o.under, o.tape, o.lift, o.faceBefore, o.face, o.glints, o.stance)
        }
        assertLines(golden.getValue("opening"), actual)
        val flaps = listOf(PipGeometry.backLeft, PipGeometry.backRight, PipGeometry.frontRight, PipGeometry.frontLeft)
        val points = flaps.withIndex().flatMap { (index, flap) ->
            listOf(-0.1, 0.0, 0.1, 0.25, 0.5, 0.75, 0.9, 1.0, 1.08).map { progress ->
                line("A", index, progress, *flap.points(progress).flatMap { listOf(it.x, it.y) }.toTypedArray())
            }
        }
        assertLines(golden.getValue("flaps"), points)
        assertLines(golden.getValue("settle"), sample(-0.2, 1.2, 0.05).map { line("Q", it, PipGeometry.settle(it)) })
    }

    @Test
    fun theEyesBlinkAsOnTheIPhone() {
        val times = sample(-0.5, 0.5, 0.05) + sample(4.0, 4.8, 0.005) + sample(8.9, 9.4, 0.01) + sample(100.0, 101.5, 0.01)
        val actual = times.map { t -> line("E", t, PipBlink.eyeScale(t), PipBlink.eyeScale(t, eye = 1), PipBlink.nextFrame(t)) }
        assertLines(golden.getValue("blink"), actual)
    }

    @Test
    fun theBlinksScheduleWakesAsOnTheIPhone() {
        // From the clock's start, from between two blinks, from inside one, from a later one, from before the start;
        // paused, and in low-frequency mode. The iPhone's times went through a Date, so they agree to a microsecond.
        val cases = listOf(Triple(0.0, false, false), Triple(2.5, false, false), Triple(4.4, false, false), Triple(9.1, false, false),
            Triple(-3.0, false, false), Triple(1.0, false, true), Triple(1.0, true, false))
        val actual = cases.map { (from, low, paused) ->
            val times = PipBlinkSchedule(paused).entries(from, lowFrequency = low).take(80).toList()
            line("K", from, if (low) "low" else "normal", paused, *times.toTypedArray())
        }
        assertLines(golden.getValue("schedule"), actual, tolerance = 1e-6)
    }

    @Test
    fun heMovesAsOnTheIPhone() {
        val actual = PipMood.entries.flatMap { mood ->
            val times = when (mood) {
                PipMood.Eager -> sample(0.0, 2.4, 0.04)
                PipMood.Joy -> sample(0.0, 4.6, 0.05)
                else -> listOf(0.0, 0.7)
            }
            times.map { t ->
                val m = PipArtwork.motion(mood, t)
                line("M", mood.name.lowercase(), t, m.a, m.b, m.c, m.d, m.tx, m.ty)
            }
        }
        // The bob's curve, a millionth off in single precision, turns him 1e-7 radians off: 3e-5 points about a pivot
        // 277 points down.
        assertLines(golden.getValue("motion"), actual, tolerance = 1e-4)
        val twinkles = (0 until 6).flatMap { index ->
            sample(0.0, 3.2, 0.1).map { t -> PipArtwork.twinkle(index, t).let { line("W", index, t, it.opacity, it.scale, it.degrees) } }
        }
        // The same curve, 18 degrees of turn to it.
        assertLines(golden.getValue("twinkle"), twinkles, tolerance = 1e-4)
    }

    @Test
    fun anEllipseIsCoreGraphicsFourCurves() {
        // The trace below writes :core's ellipses out as CoreGraphics makes them, as :app's renderer draws them.
        assertLines(golden.getValue("ellipse"), listOf("P ; " + elements(MapPath.ellipse(Rect(10.0, 20.0, 30.0, 8.0)))))
    }

    @Test
    fun everyPoseIsDrawnAsOnTheIPhone() {
        val ink = AdaptiveColor(RgbaColor.hex("#55654B"))
        val surface = AdaptiveColor(RgbaColor.hex("#F4F6F1"))
        val light = PipPalette.ink(ink, surface, dark = false)
        val dark = PipPalette.ink(AdaptiveColor(RgbaColor.hex("#C9D6BD")), AdaptiveColor(RgbaColor.hex("#263022")), dark = true)
        val dhl = PipLabel(identity("dhl"), number = "PK 0000 0000 0001")
        val ups = PipLabel(identity("ups"), number = "")
        val poses = linkedMapOf<String, (MapDrawingContext) -> Unit>(
            "look right" to { PipArtwork.ink(it, light, PipMood.Look, side = 1, below = false, time = null) },
            "look left" to { PipArtwork.ink(it, light, PipMood.Look, side = -1, below = false, time = null) },
            "look up" to { PipArtwork.ink(it, light, PipMood.Look, side = 0, below = true, time = null) },
            "look down" to { PipArtwork.ink(it, light, PipMood.Look, side = 0, below = false, time = null) },
            "eager moving" to { PipArtwork.ink(it, light, PipMood.Eager, side = 1, below = false, time = 0.3) },
            "eager left" to { PipArtwork.ink(it, light, PipMood.Eager, side = -1, below = false, time = null) },
            "wait" to { PipArtwork.ink(it, light, PipMood.Wait, side = 1, below = false, time = null) },
            "worry" to { PipArtwork.ink(it, light, PipMood.Worry, side = -1, below = false, time = null) },
            "joy moving" to { PipArtwork.ink(it, light, PipMood.Joy, side = 1, below = false, time = 0.5) },
            "joy still" to { PipArtwork.ink(it, light, PipMood.Joy, side = 1, below = false, time = null) },
            "joy dark" to { PipArtwork.ink(it, dark, PipMood.Joy, side = -1, below = false, time = 1.7) },
            "look dark" to { PipArtwork.ink(it, dark, PipMood.Look, side = 1, below = false, time = null) },
            "opening from look" to { PipArtwork.ink(it, light, PipMood.Joy, 1, false, 0.3, PipOpening.at(0.3), PipMood.Look) },
            "opening from eager" to { PipArtwork.ink(it, light, PipMood.Joy, -1, false, 0.6, PipOpening.at(0.6), PipMood.Eager) },
            "opening from worry" to { PipArtwork.ink(it, light, PipMood.Joy, 1, false, 0.0, PipOpening.at(0.0), PipMood.Worry) },
            "opening from wait" to { PipArtwork.ink(it, light, PipMood.Joy, 0, true, 0.95, PipOpening.at(0.95), PipMood.Wait) },
            "sticker" to { PipArtwork.sticker(it) },
            "sticker dhl" to { PipArtwork.sticker(it, label = dhl, happy = 0.4, blink = 4.4) },
            "sticker ups" to { PipArtwork.sticker(it, label = ups, happy = 1.0) },
            "kraft face" to { PipArtwork.kraftFace(it, k = 1.0, happy = 0.0) },
            "kraft face blinking" to { PipArtwork.kraftFace(it, k = 1.0, happy = 0.5, blink = 4.3, smile = 0.0) },
            "ribbon" to { PipArtwork.ribbon(it) },
        )
        for ((name, draw) in poses) {
            val context = MapDrawingContext()
            draw(context)
            assertLines(golden.getValue(name), trace(context.drawing), name = name)
        }
        // The truck goes on the label at half size, its own steps as CarrierTruckGeometry draws them.
        val label = MapDrawingContext().also { PipArtwork.label(it, dhl) }.drawing
        val truck = label.filterIsInstance<MapDrawing.Transformed>().single { it.steps.size > 1 }
        assertEquals(CarrierTruckGeometry.draw(dhl.identity).size, truck.steps.size)
    }

    @Test
    fun aCopiedContextDrawsIntoTheSameSteps() {
        // As `var layer = context`: its own transform and opacity, the same canvas. A transform given later applies first.
        val context = MapDrawingContext()
        context.translateBy(10.0, 0.0)
        val layer = context.copy()
        layer.scaleBy(2.0, 3.0)
        layer.opacity *= 0.5
        layer.fill(MapPath.rect(Rect(0.0, 0.0, 1.0, 1.0)), MapPainter.black)
        context.fill(MapPath.rect(Rect(0.0, 0.0, 1.0, 1.0)), MapPainter.black)
        val (first, second) = context.drawing.map { it as MapDrawing.Transformed }
        assertEquals(Point(12.0, 3.0), Point(1.0, 1.0).applying(first.transform))
        assertEquals(0.5, first.steps.single().opacity, 0.0)
        assertEquals(Point(11.0, 1.0), Point(1.0, 1.0).applying(second.transform))
        assertEquals(1.0, second.steps.single().opacity, 0.0)
        // Untransformed, a step is recorded as it is.
        val plain = MapDrawingContext().also { it.fill(MapPath(), MapPainter.black) }.drawing.single()
        assertTrue(plain is MapDrawing.Fill)
    }

    @Test
    fun theLabelWritesInItsOwnFonts() {
        val drawing = MapDrawingContext().also { PipArtwork.label(it, PipLabel(identity("dhl"), "PK 1")) }.drawing
        val lines = drawing.mapNotNull { (it as? MapDrawing.Transformed)?.steps?.singleOrNull() as? MapDrawing.TextLine }
        assertEquals(listOf("DHL", "PK 1"), lines.map { it.text })
        assertEquals(MapFont(7.0, MapFont.Weight.Heavy, italic = true), lines[0].font)
        assertEquals(MapFont(5.6, MapFont.Weight.Regular, monospaced = true), lines[1].font)
        assertEquals(PipGeometry.Label.nameRoom, lines[0].room, 0.0)
        // No number, no line for it; a carrier other than DHL writes upright.
        val ups = MapDrawingContext().also { PipArtwork.label(it, PipLabel(identity("ups"))) }.drawing
        val upsLines = ups.mapNotNull { (it as? MapDrawing.Transformed)?.steps?.singleOrNull() as? MapDrawing.TextLine }
        assertEquals(1, upsLines.size)
        assertFalse(upsLines[0].font.italic)
    }

    // The bundled rules, without inheriting a previously cached live catalog.
    private val catalog by lazy { CarrierCatalog(cacheFile = null) }

    private fun identity(id: String): CarrierVisualIdentity {
        val definition = catalog.definitions[CarrierId(id)]
        assertNotNull("$id is not in the catalog", definition)
        return CarrierVisualIdentity(id, definition!!)
    }

    /** The steps as the harness writes down what it is asked to draw. */
    private fun trace(drawing: List<MapDrawing>): List<String> = drawing.map { step ->
        when {
            step is MapDrawing.Transformed && step.steps.size == 1 && step.opacity == 1.0 -> traced(step.steps[0], step.transform)
            step is MapDrawing.Transformed -> "B ${head(step.transform, step.opacity)}"
            else -> traced(step, AffineTransform.Identity)
        }
    }

    private fun traced(step: MapDrawing, transform: AffineTransform): String = when (step) {
        is MapDrawing.Fill -> {
            assertFalse(step.evenOdd || step.cut)
            "F ${head(transform, step.opacity)} ${rgba(step.color)} ; ${elements(step.path)}"
        }
        is MapDrawing.Stroke -> {
            val style = step.style
            val dash = (listOf(style.dash.size.toDouble()) + style.dash).joinToString(" ")
            "S ${head(transform, step.opacity)} ${rgba(step.color)} ${style.lineWidth} ${style.lineCap.ordinal} ${style.lineJoin.ordinal} " +
                "${style.miterLimit} $dash ; ${elements(step.path)}"
        }
        is MapDrawing.TextLine -> {
            // The line from its baseline's start, squeezed into its room from the harness's 1000.
            val placed = AffineTransform(step.room / 1000, 0.0, 0.0, 1.0, 0.0, 0.0)
                .concatenating(AffineTransform.translation(step.start.x, step.start.y).concatenating(transform))
            val font = step.font
            "T ${head(placed, step.opacity)} ${rgba(step.color)} ${font.size} ${font.weight.name} ${font.italic} ${font.monospaced} ${step.tracking} ; ${step.text}"
        }
        else -> error("Pip draws no $step")
    }

    private fun head(t: AffineTransform, opacity: Double) = "${t.a} ${t.b} ${t.c} ${t.d} ${t.tx} ${t.ty} $opacity"

    private fun rgba(color: AdaptiveColor): String = color.resolve(dark = false).let { "${it.red} ${it.green} ${it.blue} ${it.opacity}" }

    /** A path's elements, an ellipse as CoreGraphics' four curves from its right middle, toward screen y. */
    private fun elements(path: MapPath): String {
        val out = mutableListOf<String>()
        path.replay(object : MapPath.Sink {
            override fun move(x: Double, y: Double) {
                out += "M $x $y"
            }

            override fun line(x: Double, y: Double) {
                out += "L $x $y"
            }

            override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) {
                out += "Q $controlX $controlY $x $y"
            }

            override fun curve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double) {
                out += "C $control1X $control1Y $control2X $control2Y $x $y"
            }

            override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) = error("arc")

            override fun ellipse(x: Double, y: Double, width: Double, height: Double) {
                val cx = x + width / 2
                val cy = y + height / 2
                val rx = width / 2
                val ry = height / 2
                val k = 4.0 / 3 * tan(PI / 8)
                out += "M ${x + width} $cy"
                out += "C ${cx + rx} ${cy + k * ry} ${cx + k * rx} ${cy + ry} $cx ${cy + ry}"
                out += "C ${cx - k * rx} ${cy + ry} ${cx - rx} ${cy + k * ry} ${cx - rx} $cy"
                out += "C ${cx - rx} ${cy - k * ry} ${cx - k * rx} ${cy - ry} $cx ${cy - ry}"
                out += "C ${cx + k * rx} ${cy - ry} ${cx + rx} ${cy - k * ry} ${cx + rx} $cy"
                out += "Z"
            }

            override fun close() {
                out += "Z"
            }
        })
        return out.joinToString(" ")
    }

    private fun sample(from: Double, to: Double, step: Double): List<Double> =
        generateSequence(0) { it + 1 }.map { from + it * step }.takeWhile { it <= to + step / 1000 }.toList()

    private fun line(kind: String, vararg values: Any): String = (listOf(kind) + values.map { it.toString() }).joinToString(" ")

    /**
     * Line for line, each number within `tolerance` of Swift's (relative beyond 1), each word the same; a label's text
     * exactly. Swift prints ten significant digits, and SwiftUI works its curves (the opening's ease, the blink's and
     * the bob's easeInOut, the jump's spring) out in single precision, as GeometryTests finds, hence the tolerance.
     */
    private fun assertLines(expected: List<String>, actual: List<String>, tolerance: Double = 2e-6, name: String = "") {
        assertEquals("$name: steps\n${actual.joinToString("\n")}", expected.size, actual.size)
        for ((index, pair) in expected.zip(actual).withIndex()) {
            val (want, got) = pair
            val message = "$name, line ${index + 1}:\nexpected $want\nactual   $got"
            val wantParts = want.split(" ; ", limit = 2)
            val gotParts = got.split(" ; ", limit = 2)
            assertEquals(message, wantParts.size, gotParts.size)
            if (want.startsWith("T ")) assertEquals(message, wantParts[1], gotParts[1])
            val wantTokens = wantParts.let { if (it.size == 2 && !want.startsWith("T ")) it[0] + " " + it[1] else it[0] }.split(" ")
            val gotTokens = gotParts.let { if (it.size == 2 && !got.startsWith("T ")) it[0] + " " + it[1] else it[0] }.split(" ")
            assertEquals(message, wantTokens.size, gotTokens.size)
            for ((w, g) in wantTokens.zip(gotTokens)) {
                val wn = w.toDoubleOrNull()
                val gn = g.toDoubleOrNull()
                if (wn != null && gn != null) {
                    assertTrue("$message\n$w ≠ $g", abs(wn - gn) <= tolerance * maxOf(1.0, abs(wn)))
                } else {
                    assertEquals(message, w, g)
                }
            }
        }
    }
}
