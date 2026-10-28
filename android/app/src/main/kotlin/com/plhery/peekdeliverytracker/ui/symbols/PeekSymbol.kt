package com.plhery.peekdeliverytracker.ui.symbols

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.isSpecified
import kotlin.math.abs
import kotlin.math.min

/** `.imageScale(_:)`: how large a symbol is against its font. SF scales it about the middle of the font's cap height. */
enum class SymbolScale(val factor: Float) {
    Small(SMALL_SCALE),
    Medium(1f),
    Large(LARGE_SCALE),
}

/**
 * `.symbolRenderingMode(_:)`: how a symbol's layers take its tint. Hierarchical draws a .fill symbol's shape at the
 * opacity SF gives it, with its inner glyph at full over it; only the symbols iOS draws so have that form.
 */
enum class SymbolRenderingMode {
    Monochrome,
    Hierarchical,
}

/**
 * `Image(systemName: name)` (X-AD38): the SF Symbol `name` drawn as its Material Symbols Rounded counterpart
 * (android/tools/material-symbols.tsv), in `font`'s size and weight as an SF Symbol takes its font's.
 *
 * Sizing: the box is the SF Symbol's own image at that point size (SwiftUI's frame for it), and its first and last
 * baselines are the symbol's, so it lines up with text as on the iPhone. Within the box the Material outline is scaled
 * evenly to fit the SF glyph's ink, measured at 100 pt, and centred on it: each symbol stands as tall and wide on
 * Android as on the iPhone, whatever room Material's grid leaves round it. The outline is the one drawn
 * nearest `font`'s weight (SF's weights, Material's 100 to 700) and then the point size (Material's optical sizes).
 * A `scale` other than medium grows or shrinks the symbol about the middle of the cap height, as SF's image scales do.
 *
 * It says nothing to accessibility, as most of the app's symbols are hidden from it; a symbol that stands alone as a
 * button's label takes the button's description.
 */
@Composable
fun PeekSymbol(
    name: String,
    modifier: Modifier = Modifier,
    font: TextStyle = LocalTextStyle.current,
    scale: SymbolScale = SymbolScale.Medium,
    renderingMode: SymbolRenderingMode = SymbolRenderingMode.Monochrome,
    tint: Color = font.color.takeOrElse { LocalContentColor.current },
) {
    val art = requireNotNull(materialSymbols[name]) { "No Material Symbol for SF Symbol $name" }
    require(renderingMode == SymbolRenderingMode.Monochrome || art.lowerOpacity != null) { "No hierarchical form for SF Symbol $name" }
    val weight = (font.fontWeight ?: FontWeight.Normal).weight
    Layout(modifier.drawBehind { drawSymbol(art, pointSize(font, this) * scale.factor, weight, tint, renderingMode) }) { _, _ ->
        val box = art.box(pointSize(font, this), scale.factor)
        val width = Dp(box[0]).roundToPx()
        val height = Dp(box[1]).roundToPx()
        val baseline = Dp(box[2]).roundToPx()
        layout(width, height, mapOf(FirstBaseline to baseline, LastBaseline to baseline)) {}
    }
}

/** The font's size in points (dp, as the app's fonts give them), SwiftUI's 17 where it gives none. */
private fun pointSize(font: TextStyle, density: Density): Float =
    if (font.fontSize.isSpecified) with(density) { font.fontSize.toDp().value } else 17f

/** Draws `art` at `points`, as PeekSymbol lays it out. */
internal fun DrawScope.drawSymbol(art: SymbolArt, points: Float, weight: Int, tint: Color, mode: SymbolRenderingMode) {
    val outline = art.outline(weight, points)
    val sf = art.sf
    val unit = points / 100f * density
    val bounds = outline.bounds
    val fit = min(sf[4] / bounds[2], sf[5] / bounds[3]) * unit
    val centreX = (sf[2] + sf[4] / 2) * unit
    val centreY = (sf[3] + sf[5] / 2) * unit
    translate(centreX - (bounds[0] + bounds[2] / 2) * fit, centreY - (bounds[1] + bounds[3] / 2) * fit) {
        withTransform({ scale(fit, fit, pivot = Offset.Zero) }) {
            when (mode) {
                SymbolRenderingMode.Monochrome -> drawPath(outline.path, tint)
                SymbolRenderingMode.Hierarchical -> {
                    val (shape, glyph) = outline.layers
                    drawPath(shape, tint, alpha = requireNotNull(art.lowerOpacity))
                    drawPath(glyph, tint)
                }
            }
        }
    }
}

/**
 * One SF Symbol's Material counterpart: `material` at `fill`, `sf` the SF Symbol as AppKit draws it at 100 pt (its
 * image's width and height, its ink's left, top, width and height, its baseline from the top), and the outlines drawn.
 * `lowerOpacity`, for a symbol iOS draws hierarchical, is the opacity SF gives the layer under its inner glyph.
 */
@Immutable
internal class SymbolArt(
    val material: String,
    val fill: Boolean,
    val sf: FloatArray,
    val outlines: List<MaterialOutline>,
    val lowerOpacity: Float? = null,
) {
    /** The outline for an SF `weight` (100 to 900) at `points`: the nearest weight, then the nearest optical size. */
    fun outline(weight: Int, points: Float): MaterialOutline {
        val target = weight.coerceAtMost(700)
        return outlines.minWith(compareBy({ abs(it.weight - target) }, { abs(it.opticalSize - points) }))
    }

    /**
     * The image's width, height and baseline in points for a font of `points`, at an image scale of `scale`: the image
     * grows by `scale` about the middle of the cap height, which stays where the medium image has it.
     */
    fun box(points: Float, scale: Float = 1f): FloatArray {
        val middle = sf[6] - CAP_HEIGHT / 2
        return floatArrayOf(sf[0] * scale * points / 100, sf[1] * scale * points / 100, (middle * scale + CAP_HEIGHT / 2) * points / 100)
    }
}

/**
 * A Material Symbols Rounded outline at a weight and optical size: its bounds (left, top, width, height) and path, and
 * for a hierarchical symbol its layers, back to front: the shape without its holes, then the holes filled.
 */
internal class MaterialOutline(
    val weight: Int,
    val opticalSize: Int,
    val bounds: FloatArray,
    private val pathData: String,
    private val layerData: List<String> = emptyList(),
) {
    val path: Path by lazy { PathParser().parsePathString(pathData).toPath() }
    val layers: List<Path> by lazy { layerData.map { PathParser().parsePathString(it).toPath() } }
}

/** Whether PeekSymbol draws the SF Symbol `name`. */
fun hasPeekSymbol(name: String): Boolean = name in materialSymbols

/** Every SF Symbol PeekSymbol draws. */
val peekSymbolNames: Set<String> get() = materialSymbols.keys
