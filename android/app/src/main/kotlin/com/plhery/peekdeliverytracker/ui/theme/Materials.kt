package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.layer.CompositingStrategy
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.shadow.ShadowContext
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toIntSize
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import dev.chrisbanes.haze.HazeInput
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.blur.HazeBlurStyle
import dev.chrisbanes.haze.blur.HazeColorEffect
import dev.chrisbanes.haze.blur.hazeBlur
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * One of Apple's materials in one appearance, as CoreMaterial's recipe gives it (`source`, a `.materialrecipe` in
 * CoreMaterial.framework; `baseMaterial.materialFiltering`).
 *
 * A recipe filters what lies behind, then blurs it (`blurAtEnd`); it has no tint layer. The filter maps the backdrop's
 * luminance through a curve and mixes that grey in by `luminanceAmount`, multiplies its saturation by `saturation`, and
 * adds `brightness`. `blurRadius` is CoreAnimation's `gaussianBlur` inputRadius in points, the Gaussian's standard
 * deviation (WebKit sets it straight from CSS's blur deviation, PlatformCAFiltersCocoa.mm).
 */
@Immutable
class MaterialRecipe(
    val source: String,
    val blurRadius: Float,
    val luminanceValues: List<Float>,
    val luminanceAmount: Float,
    val saturation: Float,
    val brightness: Float,
) {
    /**
     * Android's blur radius for the same Gaussian. RenderEffect's radius r blurs with σ = 0.57735 r + 0.5 pixels
     * (hwui's Blur::convertRadiusToSigma), so r is √3 σ; the half pixel is left out.
     */
    val androidBlurRadius: Dp get() = (blurRadius * sqrt(3f)).dp

    /**
     * The luminance curve as the line a colour matrix can draw: the curve is taken as the cubic Bézier whose four
     * values stand at even steps of luminance, and the line is its least-squares fit over 0…1. Its intercept and slope.
     */
    val luminanceLine: Pair<Float, Float>
        get() {
            // ∫B = Σv/4 and ∫tB = Σ(i+1)v/20 over the Bernstein cubics; the normal equations of a + b t follow.
            val mean = luminanceValues.sum() / 4
            val moment = luminanceValues.withIndex().sumOf { (i, v) -> (i + 1.0) * v }.toFloat() / 20
            val slope = 12 * (moment - mean / 2)
            return Pair(mean - slope / 2, slope)
        }

    /** The whole filter as a 4 × 5 matrix, rows red, green, blue, alpha; its last column offsets in 0…1. */
    val matrix: FloatArray by lazy {
        val (intercept, slope) = luminanceLine
        val amount = luminanceAmount
        val luminance = affine { row, column -> (1 - amount) * same(row, column) + amount * slope * LUMA[column] }
        for (row in 0..2) luminance[row * 5 + 4] = amount * intercept
        val saturate = affine { row, column -> saturation * same(row, column) + (1 - saturation) * LUMA[column] }
        val brighten = affine { row, column -> same(row, column) }
        for (row in 0..2) brighten[row * 5 + 4] = brightness
        then(brighten, then(saturate, luminance))
    }

    /** The filter as Compose's colour matrix, whose offsets count in 0…255. */
    val colorMatrix: ColorMatrix
        get() = ColorMatrix(FloatArray(20) { if (it % 5 == 4) matrix[it] * 255 else matrix[it] })

    /** What the material shows over a plain `behind`: the filter applied to it, held to 0…1 as it is drawn. */
    fun over(behind: Color): Color {
        val input = floatArrayOf(behind.red, behind.green, behind.blue, behind.alpha)
        val out = FloatArray(4) { row ->
            (0..3).sumOf { column -> (matrix[row * 5 + column] * input[column]).toDouble() }.toFloat() + matrix[row * 5 + 4]
        }
        return Color(out[0].coerceIn(0f, 1f), out[1].coerceIn(0f, 1f), out[2].coerceIn(0f, 1f), out[3].coerceIn(0f, 1f))
    }

    private companion object {
        fun same(row: Int, column: Int) = if (row == column) 1f else 0f

        /** A matrix whose colour rows are `entry`, alpha passed through. */
        fun affine(entry: (Int, Int) -> Float) = FloatArray(20) { index ->
            val row = index / 5
            val column = index % 5
            when {
                column == 4 -> 0f
                row == 3 -> same(row, column)
                else -> entry(row, column)
            }
        }

        /** `second` after `first`. */
        fun then(second: FloatArray, first: FloatArray) = FloatArray(20) { index ->
            val row = index / 5
            val column = index % 5
            val product = (0..3).sumOf { k -> (second[row * 5 + k] * first[k * 5 + column]).toDouble() }.toFloat()
            if (column == 4) product + second[row * 5 + 4] else product
        }
    }
}

/** Rec. 709 luminance, as Core Image's colour controls and CoreAnimation's glass weigh it. */
private val LUMA = floatArrayOf(0.2126f, 0.7152f, 0.0722f, 0f)

/**
 * SwiftUI's materials, each in light and dark (CoreMaterial's platformContent* recipes; `bar` is platformChrome*).
 * The iPhone app uses only `ultraThin`, as its glass before iOS 26; [GlassSurface] draws iOS 26's glass, which has its
 * own recipe ([GlassRecipe]).
 */
enum class PeekMaterial(private val light: MaterialRecipe, private val dark: MaterialRecipe) {
    UltraThin(
        MaterialRecipe("platformContentUltraThinLight", 22.5f, listOf(0.45f, 0.55f, 0.65f, 0.68f), 0.5f, 1.1f, 0.12f),
        MaterialRecipe("platformContentUltraThinDark", 22.5f, listOf(0.24f, 0.24f, 0.3f, 0.39f), 0.5f, 1.1f, 0f),
    ),
    Thin(
        MaterialRecipe("platformContentThinLight", 30f, listOf(0.725f, 0.825f, 0.76f, 0.73f), 0.6f, 1.35f, 0.12f),
        MaterialRecipe("platformContentThinDark", 30f, listOf(0.2f, 0.21f, 0.1f, 0.15f), 0.6f, 1.35f, 0f),
    ),
    Regular(
        MaterialRecipe("platformContentLight", 30f, listOf(0.9f, 0.83f, 0.925f, 0.815f), 0.75f, 1.5f, 0.1f),
        MaterialRecipe("platformContentDark", 30f, listOf(0.16f, 0.26f, 0.1f, 0.1f), 0.75f, 1.5f, 0f),
    ),
    Thick(
        MaterialRecipe("platformContentThickLight", 45f, listOf(0.99f, 0.95f, 0.98f, 0.905f), 0.88f, 1.5f, 0.045f),
        MaterialRecipe("platformContentThickDark", 45f, listOf(0.14f, 0.16f, 0.1f, 0.03f), 0.88f, 1.5f, 0f),
    ),
    Bar(
        MaterialRecipe("platformChromeLight", 22.5f, listOf(0.8f, 0.9f, 1.1f, 0.825f), 0.75f, 1.1f, 0.1f),
        MaterialRecipe("platformChromeDark", 22.5f, listOf(0.23f, 0.52f, 0.27f, 0.255f), 0.75f, 2f, -0.1f),
    ),
    ;

    fun recipe(dark: Boolean): MaterialRecipe = if (dark) this.dark else light
}

/** What the materials on a screen blur: the content marked with [materialBackdrop]. */
@Stable
class MaterialBackdrop internal constructor(internal val haze: HazeState)

@Composable
fun rememberMaterialBackdrop(): MaterialBackdrop {
    val haze = rememberHazeState()
    return remember(haze) { MaterialBackdrop(haze) }
}

/** Marks the content the materials in front of it blur, as SwiftUI's blur whatever lies behind them. */
fun Modifier.materialBackdrop(backdrop: MaterialBackdrop): Modifier = hazeSource(backdrop.haze)

/** The backdrop of the screen being drawn, for the materials inside it. */
val LocalMaterialBackdrop = staticCompositionLocalOf<MaterialBackdrop?> { null }

/**
 * `background(material, in: shape)`: [backdrop] blurred and filtered by the material's recipe within `shape`, under
 * `content`, which is not clipped. It is drawn solid, as the material over the page's own background, where there is
 * no backdrop, and under Reduce Transparency ([PeekTheme.reduceTransparency], whose recipes trade the blur for the
 * backdrop's average colour). Increase Contrast leaves it as it is, as it leaves the iPhone's materials.
 *
 * Vibrancy, the blend iOS gives `.primary` and `.secondary` content on a material, is not drawn: content keeps its own
 * colours.
 */
@Composable
fun MaterialSurface(
    material: PeekMaterial,
    shape: Shape,
    modifier: Modifier = Modifier,
    backdrop: MaterialBackdrop? = LocalMaterialBackdrop.current,
    content: @Composable BoxScope.() -> Unit,
) {
    val colors = PeekTheme.colors
    val recipe = material.recipe(colors.dark)
    val solid = backdrop == null || PeekTheme.reduceTransparency
    Box(modifier) {
        val fill = Modifier.matchParentSize().clip(shape)
        if (solid) {
            Box(fill.background(recipe.over(colors.background)))
        } else {
            val style = remember(recipe, colors.background) {
                hazeStyle(recipe.androidBlurRadius, recipe.colorMatrix, recipe.over(colors.background), colors.background)
            }
            // Only what lies within the shape is blurred, its edge pixels held at the edge. Haze 2.0.1's wider capture
            // (expandLayerBounds) drew the blur shifted by its margin in Robolectric's renderer.
            Box(fill.hazeBlur(HazeInput.Backdrop(backdrop.haze), style, expandLayerBounds = false))
        }
        content()
    }
}


/**
 * Liquid Glass's face in one appearance: what `glassEffect(.regular)` makes of what lies behind it. It is
 * CoreAnimation's `glassBackground` filter on the glass's backdrop layer, whose inputs SwiftUI sets the same on macOS
 * 27 and in Mac Catalyst: a Gaussian blur of [GLASS_BLUR] points, then a colour matrix on the sRGB values. The matrix
 * maps the backdrop's luminance L onto `black + (white − black) L`, held at most [maxLuma]; multiplies its saturation by
 * `saturation`; and lays a grey `fill` over the result at `fillAlpha`. `lift` is what the iPhone draws on top of the
 * matrix, its blurred fill (`inputBlurFill*`), measured on its screenshots as a uniform lightening in light; dark's
 * darkening of the same size, its mirror, is taken rather than measured.
 */
@Immutable
class GlassRecipe(
    val white: Float,
    val black: Float,
    val saturation: Float,
    val fill: Float,
    val fillAlpha: Float,
    val lift: Float,
    private val maxLuma: Float,
    private val dimsWhenLarge: Boolean,
) {
    /** The matrix, rows red, green, blue, alpha; its last column offsets in 0…1. */
    val matrix: FloatArray by lazy {
        val keep = 1 - fillAlpha
        val chroma = saturation * keep
        val grey = (white - black) * keep
        val offset = black * keep + fillAlpha * fill + lift
        FloatArray(20) { index ->
            val row = index / 5
            val column = index % 5
            when {
                row == 3 -> if (column == 3) 1f else 0f
                column == 4 -> offset
                column == 3 -> 0f
                else -> (if (row == column) chroma else 0f) + (grey - chroma) * LUMA[column]
            }
        }
    }

    /** The matrix as Compose's colour matrix, whose offsets count in 0…255. */
    val colorMatrix: ColorMatrix
        get() = ColorMatrix(FloatArray(20) { if (it % 5 == 4) matrix[it] * 255 else matrix[it] })

    /**
     * The most luminance the matrix lets through on a glass whose shorter side is [minDimension] points: in dark it
     * falls from 64 points on, 0.006 a point, to 0.36.
     */
    fun maxLuma(minDimension: Float): Float =
        if (dimsWhenLarge) (maxLuma - 0.006f * (minDimension - 64)).coerceIn(0.36f, maxLuma) else maxLuma

    /**
     * The brightest the face comes out on that glass: [maxLuma] filled and lifted as the rest. It is drawn as a darken
     * blend, each channel held to it.
     */
    fun cap(minDimension: Float): Color {
        val grey = (maxLuma(minDimension) * (1 - fillAlpha) + fillAlpha * fill + lift).coerceIn(0f, 1f)
        return Color(grey, grey, grey)
    }

    /** The face over a plain [behind] on a glass whose shorter side is [minDimension] points. */
    fun over(behind: Color, minDimension: Float): Color {
        val input = floatArrayOf(behind.red, behind.green, behind.blue)
        val cap = cap(minDimension).red
        val out = FloatArray(3) { row ->
            val sum = (0..2).sumOf { column -> (matrix[row * 5 + column] * input[column]).toDouble() }.toFloat()
            (sum + matrix[row * 5 + 4]).coerceIn(0f, minOf(cap, 1f))
        }
        return Color(out[0], out[1], out[2])
    }
}

/**
 * The glass's face under each accessibility setting, in light and dark: the inputs SwiftUI gives `glassBackground`
 * for `glassEffect(.regular)`, with Increase Contrast and Reduce Transparency set in its environment. Increase Contrast
 * changes only the face, more opaque and less saturated, and adds no border; Reduce Transparency makes it all but
 * opaque, blurs 35 points and stops the lens.
 */
enum class GlassFace(private val light: GlassRecipe, private val dark: GlassRecipe) {
    Standard(
        GlassRecipe(0.96f, 0.4f, 1.2f, 1f, 0.18f, GLASS_LIFT, 0.94f, dimsWhenLarge = false),
        GlassRecipe(1.125f, 0.08f, 1.3f, 0.0125f, 0f, -GLASS_LIFT, 0.6f, dimsWhenLarge = true),
    ),
    IncreasedContrast(
        GlassRecipe(0.96f, 0.5f, 0.8f, 1f, 0.45f, GLASS_LIFT, 0.94f, dimsWhenLarge = false),
        GlassRecipe(0.3f, 0.1f, 0.8f, 0.12f, 0.4f, -GLASS_LIFT, 0.6f, dimsWhenLarge = true),
    ),
    ReducedTransparency(
        GlassRecipe(1.03f, 0.9f, 0.9f, 1f, 0.72f, GLASS_LIFT, 0.94f, dimsWhenLarge = false),
        GlassRecipe(0.2f, 0.2f, 0.7f, 0f, 0.54f, -GLASS_LIFT, 0.6f, dimsWhenLarge = true),
    ),
    ;

    fun recipe(dark: Boolean): GlassRecipe = if (dark) this.dark else light

    companion object {
        fun of(highContrast: Boolean, reduceTransparency: Boolean): GlassFace = when {
            reduceTransparency -> ReducedTransparency
            highContrast -> IncreasedContrast
            else -> Standard
        }
    }
}

/** The glass's blur, CoreAnimation's `inputBlurRadius`: the Gaussian's deviation in points. */
const val GLASS_BLUR = 5f

/** What the iPhone's glass lightens its face by in light, in 0…1 (and darkens it by in dark). */
private const val GLASS_LIFT = 0.035f

/**
 * The glass's lens (`inputInnerRefraction*`): along its edge, a band [height] points deep shows what lies past the edge,
 * looking out by up to [amount] points. Both grow with the glass's shorter side, as a quarter and a half of it, up to
 * 20 and 60 points.
 *
 * It is drawn as the face again, shrunk about the centre, in a band that fades in towards the edge. Along an axis
 * whose half is `half` points, the shrink makes the band's middle look out by half the amount: [reach] is the factor.
 * Where it looks past the backdrop's own edge, it finds the page's background.
 */
@Immutable
data class GlassLens(val height: Float, val amount: Float) {
    fun reach(half: Float): Float = 1 + amount / 2 / (half - height / 2)

    /** How far past the edge the lens looks, there. */
    fun beyond(half: Float): Float = half * (reach(half) - 1)

    companion object {
        fun of(minDimension: Float) = GlassLens(minOf(minDimension / 4, 20f), minOf(minDimension / 2, 60f))
    }
}

/**
 * The shadow under the glass, as the iPhone's screenshots show it under a 44-point circle (black at 2 %, deviation 6,
 * 3 points down) and a 170-point bar (9 %, deviation 12, spread 6, 10 points down), and in between for sizes between.
 * CoreAnimation's own glass shadow is off on the Mac.
 */
fun glassShadow(minDimension: Float): Shadow {
    val t = ((minDimension - 44) / (170 - 44)).coerceIn(0f, 1f)
    return Shadow(
        radius = ((6 + 6 * t) * sqrt(3f)).dp,
        color = Color.Black.copy(alpha = 0.02f + 0.07f * t),
        spread = (6 * t).dp,
        offset = DpOffset(0.dp, (3 + 7 * t).dp),
    )
}

/**
 * `glassSurface(in:)` as the iPhone draws it from iOS 26, `glassEffect(.regular, in: shape)`: [backdrop] blurred and
 * filtered by [GlassFace]'s recipe within `shape`, the lens along its edge ([GlassLens]), its edge lit and shaded, and
 * [glassShadow] under it, all behind `content`, which is not clipped.
 *
 * The light, measured on the iPhone's screenshots in light, follows θ, the angle the edge's normal makes with the
 * vertical: a dark line 0.67 point wide just outside the edge, black at 10 % + 16.5 % sin²θ, and a light line 0.9 point
 * wide inside it, white at cos²θ, brightest at the top and bottom. Inside, the glass is shaded from above, as an inner
 * shadow 11 points down (black at 1.7 %, spread 6, deviation 3) leaves its top and sides darker and the middle of its
 * bottom clear, and a glow under its top edge adds white at up to 8 % (deviation 4). Dark takes the same, unmeasured.
 *
 * It is drawn solid, the face over the page's background with no lens, where there is no backdrop, and under Reduce
 * Transparency ([PeekTheme.reduceTransparency]), whose face is all but opaque on the iPhone too. Increase Contrast
 * ([PeekColors.highContrast]) takes its own face. Vibrancy, which iOS gives content on glass, is not drawn.
 */
@Composable
fun GlassSurface(
    shape: Shape,
    modifier: Modifier = Modifier,
    backdrop: MaterialBackdrop? = LocalMaterialBackdrop.current,
    content: @Composable BoxScope.() -> Unit,
) {
    val colors = PeekTheme.colors
    val reduceTransparency = PeekTheme.reduceTransparency
    val recipe = GlassFace.of(colors.highContrast, reduceTransparency).recipe(colors.dark)
    val blurred = backdrop?.takeUnless { reduceTransparency }
    val shadows = LocalGraphicsContext.current.shadowContext
    Box(modifier) {
        val solid = colors.background.takeIf { blurred == null }
        Box(Modifier.matchParentSize().glass(shape, recipe, solid, shadows)) {
            if (blurred != null) {
                val style = remember(recipe, colors.background) {
                    hazeStyle((GLASS_BLUR * sqrt(3f)).dp, recipe.colorMatrix, recipe.over(colors.background, 0f), colors.background)
                }
                // The face reaches past the shape, for the lens to look out; the drawing clips it to the shape. It is
                // drawn twice, so it blurs the marked content: a backdrop input samples what lies behind where it is
                // drawn, which differs between the two.
                Box(Modifier.pastTheEdge().hazeBlur(HazeInput.Sources(blurred.haze), style, expandLayerBounds = false))
            }
        }
        content()
    }
}

/** The glass drawn behind its content: the shadow, the face (its content) or [solid] behind it, the lens, the edge. */
private fun Modifier.glass(shape: Shape, recipe: GlassRecipe, solid: Color?, shadows: ShadowContext): Modifier =
    drawWithCache {
        if (size.minDimension <= 0f) return@drawWithCache onDrawWithContent { drawContent() }
        val minDimension = size.minDimension.toDp().value
        val outline = shape.createOutline(size, layoutDirection, this)
        val path = Path().apply { addOutline(outline) }
        val radius = cornerRadius(shape, outline, size)
        val shadow = shadows.createDropShadowPainter(shape, glassShadow(minDimension))
        val shade = shadows.createInnerShadowPainter(shape, GLASS_SHADE)
        val glow = shadows.createInnerShadowPainter(
            shape,
            Shadow(radius = (4 * sqrt(3f)).dp, brush = upward(radius) { c -> Color.White.copy(alpha = 0.08f * c * c) }, blendMode = BlendMode.Plus),
        )
        val rim = sideways(size.width, radius) { s -> Color.Black.copy(alpha = 0.10f + 0.165f * s * s) }
        val highlight = sideways(size.width, radius, GLASS_HIGHLIGHT.toPx()) { s -> Color.White.copy(alpha = 1 - s * s) }
        val edge: DrawScope.() -> Unit = {
            with(shade) { draw(size) }
            with(glow) { draw(size) }
            clipPath(path, ClipOp.Difference) { drawPath(path, rim, style = Stroke(2 * GLASS_RIM.toPx())) }
            clipPath(path) { drawPath(path, highlight, style = Stroke(2 * GLASS_HIGHLIGHT.toPx())) }
        }
        if (solid != null) {
            val face = recipe.over(solid, minDimension)
            return@drawWithCache onDrawWithContent {
                with(shadow) { draw(size) }
                drawPath(path, face)
                edge()
            }
        }
        val lens = GlassLens.of(minDimension)
        val margin = glassMargin(size)
        val shrinkX = 1 / lens.reach(size.width.toDp().value / 2)
        val shrinkY = 1 / lens.reach(size.height.toDp().value / 2)
        // The band fades in from half its height to the edge, as an inner shadow does from its spread.
        val band = shadows.createInnerShadowPainter(
            shape,
            Shadow(radius = (lens.height / 4 * sqrt(3f)).dp, color = Color.Black, spread = (lens.height / 2).dp, blendMode = BlendMode.DstIn),
        )
        val faceLayer = obtainGraphicsLayer().apply { topLeft = IntOffset(-margin, -margin) }
        val bandLayer = obtainGraphicsLayer().apply { compositingStrategy = CompositingStrategy.Offscreen }
        val faceSize = IntSize(size.width.roundToInt() + 2 * margin, size.height.roundToInt() + 2 * margin)
        // Recording in the node's own draw scope leaves its density pointing at itself, so the band, which needs the
        // density, records with a plain one.
        val bandDensity = Density(density, fontScale)
        val cap = recipe.cap(minDimension)
        onDrawWithContent {
            with(shadow) { draw(size) }
            faceLayer.record(faceSize) { translate(margin.toFloat(), margin.toFloat()) { this@onDrawWithContent.drawContent() } }
            bandLayer.record(bandDensity, layoutDirection, size.toIntSize()) {
                scale(shrinkX, shrinkY, center) { drawLayer(faceLayer) }
                with(band) { draw(size) }
            }
            clipPath(path) {
                drawLayer(faceLayer)
                drawLayer(bandLayer)
                drawRect(cap, blendMode = BlendMode.Darken)
            }
            edge()
        }
    }

/** The width of the dark line outside the glass's edge: 2 pixels on a 3× iPhone. */
private val GLASS_RIM = 0.67.dp

/** The width of the light line inside the glass's edge: 2 pixels and most of a third on a 3× iPhone. */
private val GLASS_HIGHLIGHT = 0.9.dp

/** The shade inside the glass, cast from above. */
private val GLASS_SHADE = Shadow(
    radius = (3 * sqrt(3f)).dp,
    color = Color.Black.copy(alpha = 0.017f),
    spread = 6.dp,
    offset = DpOffset(0.dp, 11.dp),
)

/** Lays the face out past the glass's edge on each side by [glassMargin], drawn from where the glass's edge is. */
private fun Modifier.pastTheEdge(): Modifier = layout { measurable, constraints ->
    val width = constraints.maxWidth
    val height = constraints.maxHeight
    val margin = glassMargin(Size(width.toFloat(), height.toFloat()))
    val placeable = measurable.measure(Constraints.fixed(width + 2 * margin, height + 2 * margin))
    layout(width, height) { placeable.place(-margin, -margin) }
}

/** How far past the edge the glass blurs, in pixels: as far as the lens looks, and twice the blur, for the blur's edge. */
private fun Density.glassMargin(size: Size): Int {
    val width = size.width.toDp().value
    val height = size.height.toDp().value
    val lens = GlassLens.of(minOf(width, height))
    return ceil((maxOf(lens.beyond(width / 2), lens.beyond(height / 2)) + 2 * GLASS_BLUR).dp.toPx()).toInt()
}

/** The radius of the shape's corners, in pixels, taking continuous corners as circular ones. */
private fun Density.cornerRadius(shape: Shape, outline: Outline, size: Size): Float = when {
    shape is CapsuleShape -> size.minDimension / 2
    shape is ContinuousRoundedShape -> minOf(shape.cornerRadius.toPx(), size.minDimension / 2)
    outline is Outline.Rounded -> outline.roundRect.topLeftCornerRadius.x
    outline is Outline.Rectangle -> 0f
    else -> size.minDimension / 2
}

/**
 * A brush across a glass [width] wide whose colour follows how far its edge faces sideways: `color(s)`, s the sine of
 * the angle between the edge's normal and the vertical, 0 along the top and bottom and 1 down the sides, the corners
 * taken as quarter circles of [radius]. A line drawn [inset] deep inside the edge reads as facing straight sideways
 * all across its width down the sides.
 */
private fun sideways(width: Float, radius: Float, inset: Float = 0f, color: (Float) -> Color): Brush {
    val corner = radius.coerceIn(1f, width / 2)
    val side = inset.coerceIn(0f, corner / 2)
    val steps = 8
    val stops = buildList {
        for (i in steps downTo 0) add(corner - (corner - side) * i / steps to color(i / steps.toFloat()))
        for (i in 0..steps) add(width - corner + (corner - side) * i / steps to color(i / steps.toFloat()))
    }.map { (x, c) -> x / width to c }
    return Brush.horizontalGradient(*stops.toTypedArray(), startX = 0f, endX = width)
}

/**
 * A brush down a glass whose colour follows how far its edge faces up: `color(c)`, c the cosine of the angle between
 * the edge's normal and the vertical across the top corners, quarter circles of [radius], and 0 below them.
 */
private fun upward(radius: Float, color: (Float) -> Color): Brush {
    val corner = maxOf(radius, 1f)
    val steps = 8
    val stops = (0..steps).map { i -> i / steps.toFloat() to color(1 - i / steps.toFloat()) }
    return Brush.verticalGradient(*stops.toTypedArray(), startY = 0f, endY = corner)
}

/**
 * Haze's style for a filter: the blur, then the filter (the same as before it, the filter being linear), no noise.
 * [fallback] is the filter over [behind], drawn where Haze can't blur.
 */
private fun hazeStyle(blurRadius: Dp, filter: ColorMatrix, fallback: Color, behind: Color) = HazeBlurStyle {
    blurRadius(blurRadius)
    noiseFactor(0f)
    // What the blur reaches past the backdrop's edge.
    backgroundColor(behind)
    colorEffects(listOf(HazeColorEffect.colorFilter(ColorFilter.colorMatrix(filter))))
    fallbackColorEffect(HazeColorEffect.tint(fallback))
}
