package com.plhery.peekdeliverytracker.ui.theme

import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.node.requireGraphicsContext
import androidx.compose.ui.platform.InspectorInfo
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
import kotlin.math.abs
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
    /** Android's blur radius for the same Gaussian ([gaussianBlurRadius]). */
    val androidBlurRadius: Dp get() = gaussianBlurRadius(blurRadius.dp)

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
 * maps the backdrop's luminance L onto `black + (white − black) L`, held at most [maxLuma] by taking the excess off
 * every channel alike; multiplies its saturation by `saturation`; and lays a grey `fill` over the result at
 * `fillAlpha`. `lift` is what the iPhone draws on top of the matrix, its blurred fill (`inputBlurFill*`), measured on
 * its screenshots as a uniform lightening in light; dark's darkening of the same size, its mirror, is taken rather
 * than measured.
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
    /** The matrix, rows red, green, blue, alpha; its last column offsets in 0…1. The ceiling comes after it. */
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

    /** The most luminance the face comes out with on that glass: [maxLuma] filled and lifted as the rest. */
    fun cap(minDimension: Float): Float = (maxLuma(minDimension) * (1 - fillAlpha) + fillAlpha * fill + lift).coerceIn(0f, 1f)

    /** The face over a plain [behind] on a glass whose shorter side is [minDimension] points. */
    fun over(behind: Color, minDimension: Float): Color {
        val input = floatArrayOf(behind.red, behind.green, behind.blue)
        val out = FloatArray(3) { row ->
            (0..2).sumOf { column -> (matrix[row * 5 + column] * input[column]).toDouble() }.toFloat() + matrix[row * 5 + 4]
        }
        val excess = maxOf((0..2).sumOf { (LUMA[it] * out[it]).toDouble() }.toFloat() - cap(minDimension), 0f)
        return Color((out[0] - excess).coerceIn(0f, 1f), (out[1] - excess).coerceIn(0f, 1f), (out[2] - excess).coerceIn(0f, 1f))
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

/**
 * How far past its edge the glass sees what lies behind: its backdrop layer, the glass's own size, captures this much
 * more (`marginWidth`), and the blur holds the pixels at its edge beyond.
 */
private val GLASS_MARGIN = 1.dp

/** What the iPhone's glass lightens its face by in light, in 0…1 (and darkens it by in dark). */
private const val GLASS_LIFT = 0.035f

/**
 * The glass's lens (`inputInnerRefraction*`): along its edge, a band [height] points deep shows what lies further in
 * along the edge's normal, looking in by [amount] points at the edge and by evenly less across the band, by nothing at
 * its inner line. `amount` being at least twice `height`, the band shows the strip beyond it mirrored, squeezed on glass
 * of more than 80 points, twice from 120. Both grow with the glass's shorter side, as a quarter and a half of it, up
 * to 20 and 60 points.
 *
 * The glass sees nothing past its edge but [GLASS_MARGIN]. The list's tab bar on the iPhone shows it: the card that
 * starts 6.3 points inside its top edge gives the band its colour from 1.2 points in, and text just past its edges
 * doesn't show. That shows how far in the band looks, at least; the even fall across it is taken.
 */
@Immutable
data class GlassLens(val height: Float, val amount: Float) {
    /** How much further in the glass shows, [depth] points inside its edge. */
    fun look(depth: Float): Float = amount * (1 - depth / height).coerceIn(0f, 1f)

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
        radius = gaussianBlurRadius((6 + 6 * t).dp),
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
 * wide inside it, adding white at [GLASS_HIGHLIGHT_LIFT] cos²θ, brightest at the top and bottom. Inside, the glass is
 * shaded from above, as an inner shadow 11 points down (black at 1.7 %, spread 6, deviation 3) leaves its top and
 * sides darker and the middle of its bottom clear, and a glow under its top edge adds white at up to 8 % (deviation 4).
 * Dark takes the same, unmeasured, as CoreAnimation sets the light's amounts the same in both.
 *
 * The lens and the luminance ceiling are a runtime shader from Android 13. Android 12 draws the lens as the face
 * mirrored about the band's inner line along each side, the band split on the shape's diagonals, and holds each
 * channel to the ceiling, which dulls bright colours in dark.
 *
 * It is drawn solid, the face over `behind` (the page's background) with no lens, where there is no backdrop, and
 * under Reduce Transparency ([PeekTheme.reduceTransparency]), whose face is all but opaque on the iPhone too. Increase
 * Contrast ([PeekColors.highContrast]) takes its own face. Vibrancy, which iOS gives content on glass, is not drawn.
 */
@Composable
fun GlassSurface(
    shape: Shape,
    modifier: Modifier = Modifier,
    backdrop: MaterialBackdrop? = LocalMaterialBackdrop.current,
    behind: Color = PeekTheme.colors.background,
    content: @Composable BoxScope.() -> Unit,
) {
    val colors = PeekTheme.colors
    val reduceTransparency = PeekTheme.reduceTransparency
    val recipe = GlassFace.of(colors.highContrast, reduceTransparency).recipe(colors.dark)
    val blurred = backdrop?.takeUnless { reduceTransparency }
    Box(modifier) {
        val solid = behind.takeIf { blurred == null }
        Box(Modifier.matchParentSize().then(GlassElement(shape, recipe, solid))) {
            if (blurred != null) {
                val style = remember(recipe, behind) {
                    // From Android 13 the lens's shader filters the face, after the lens and before the ceiling.
                    val filter = recipe.colorMatrix.takeIf { Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU }
                    hazeStyle(gaussianBlurRadius(GLASS_BLUR.dp), filter, recipe.over(behind, 0f), behind)
                }
                // The face reaches GLASS_MARGIN past the shape; the drawing clips it to the shape. It blurs the marked
                // content: a backdrop input samples what lies behind where it is drawn, and Android 12 draws the face
                // again for the lens.
                Box(Modifier.pastTheEdge().hazeBlur(HazeInput.Sources(blurred.haze), style, expandLayerBounds = false))
            }
        }
        content()
    }
}

/**
 * The glass of a sheet or a popover, iOS 26's Liquid Glass at its largest: [GlassFace]'s face over what lies behind,
 * blurred as far as the thin material blurs ([LARGE_GLASS_BLUR]) rather than a control's few points, with no lens, lit
 * edge or shadow, which a sheet's glass doesn't show. It is drawn solid, the face over `behind`, where there is no
 * backdrop and under Reduce Transparency.
 */
@Composable
fun LargeGlassSurface(
    shape: Shape,
    modifier: Modifier = Modifier,
    backdrop: MaterialBackdrop? = LocalMaterialBackdrop.current,
    behind: Color = PeekTheme.colors.background,
) {
    val colors = PeekTheme.colors
    val reduceTransparency = PeekTheme.reduceTransparency
    val recipe = GlassFace.of(colors.highContrast, reduceTransparency).recipe(colors.dark)
    val blurred = backdrop?.takeUnless { reduceTransparency }
    val fill = modifier.clip(shape)
    if (blurred == null) {
        Box(fill.background(recipe.over(behind, LARGE_GLASS_SIDE)))
    } else {
        val style = remember(recipe, behind) {
            hazeStyle(gaussianBlurRadius(LARGE_GLASS_BLUR.dp), recipe.colorMatrix, recipe.over(behind, LARGE_GLASS_SIDE), behind)
        }
        Box(fill.hazeBlur(HazeInput.Backdrop(blurred.haze), style, expandLayerBounds = false))
    }
}

/** How far a sheet's glass blurs what lies behind it, in points: as far as the thin material does. */
private const val LARGE_GLASS_BLUR = 30f

/** A large glass's shorter side, in points, for its luminance ceiling in dark. */
private const val LARGE_GLASS_SIDE = 400f

/** The glass drawn behind its content: the shadow, the face (its content) or [solid] behind it, the lens, the edge. */
private data class GlassElement(val shape: Shape, val recipe: GlassRecipe, val solid: Color?) : ModifierNodeElement<GlassNode>() {
    override fun create() = GlassNode(shape, recipe, solid)

    override fun update(node: GlassNode) = node.update(shape, recipe, solid)

    override fun InspectorInfo.inspectableProperties() {
        name = "glass"
        properties["shape"] = shape
        properties["solid"] = solid
    }
}

/**
 * Draws the glass. What depends on its size (its outline, the lens, the shadow and the edge) is worked out and recorded
 * into layers once for each size, their blurs left to the renderer; a frame records the face and draws the layers.
 */
private class GlassNode(private var shape: Shape, private var recipe: GlassRecipe, private var solid: Color?) :
    Modifier.Node(), DrawModifierNode {
    private var layers: GlassLayers? = null
    private var geometry: GlassGeometry? = null

    fun update(shape: Shape, recipe: GlassRecipe, solid: Color?) {
        if (shape == this.shape && recipe === this.recipe && solid == this.solid) return
        this.shape = shape
        this.recipe = recipe
        this.solid = solid
        geometry = null
        invalidateDraw()
    }

    override fun onAttach() {
        layers = GlassLayers(requireGraphicsContext())
    }

    override fun onDetach() {
        layers?.release()
        layers = null
        geometry = null
    }

    override fun ContentDrawScope.draw() {
        val layers = layers
        if (layers == null || size.minDimension <= 0f) return drawContent()
        val geometry = geometry?.takeIf { it.size == size && it.density == density && it.fontScale == fontScale && it.layoutDirection == layoutDirection }
            ?: GlassGeometry(this, shape, recipe, solid, layers).also { geometry = it }
        drawLayer(layers.shadow)
        val face = geometry.solidFace
        if (face != null) {
            drawPath(geometry.path, face)
        } else {
            val margin = geometry.margin.toFloat()
            layers.face.record(geometry.faceSize) { translate(margin, margin) { this@draw.drawContent() } }
            clipPath(geometry.path) {
                drawLayer(layers.face)
                val bands = geometry.bands
                for (index in bands.indices) {
                    val band = bands[index]
                    clipPath(band.clip) { scale(band.scaleX, band.scaleY, band.pivot) { drawLayer(layers.face) } }
                }
                if (geometry.ceiling != null) drawRect(geometry.ceiling, blendMode = BlendMode.Darken)
            }
        }
        drawLayer(layers.edge)
    }
}

/** A glass's layers: the face, recorded each frame, and the rest, recorded once for each size. */
private class GlassLayers(private val context: GraphicsContext) {
    val face = context.createGraphicsLayer()
    val shadow = context.createGraphicsLayer()
    val shade = context.createGraphicsLayer()
    val glow = context.createGraphicsLayer().apply { blendMode = BlendMode.Plus }
    val edge = context.createGraphicsLayer()

    fun release() {
        for (layer in listOf(face, shadow, shade, glow, edge)) context.releaseGraphicsLayer(layer)
    }
}

/**
 * What a glass of one size draws: its outline, the face's size past it, the lens (a shader set on [GlassLayers.face],
 * or [bands] on Android 12), the ceiling Android 12 darkens to, or the face drawn solid; it records the shadow and the
 * edge into their layers.
 */
private class GlassGeometry(scope: DrawScope, shape: Shape, recipe: GlassRecipe, solid: Color?, layers: GlassLayers) {
    val size = scope.size
    val density = scope.density
    val fontScale = scope.fontScale
    val layoutDirection = scope.layoutDirection
    val path: Path
    val margin: Int
    val faceSize: IntSize
    val solidFace: Color?
    val ceiling: Color?
    val bands: List<GlassBand>

    init {
        val plain = Density(density, fontScale)
        val minDimension = with(plain) { size.minDimension.toDp().value }
        val outline = shape.createOutline(size, layoutDirection, plain)
        path = Path().apply { addOutline(outline) }
        val radius = plain.cornerRadius(shape, outline, size)
        margin = plain.glassMargin()
        faceSize = IntSize(size.width.roundToInt() + 2 * margin, size.height.roundToInt() + 2 * margin)
        solidFace = solid?.let { recipe.over(it, minDimension) }
        val lens = GlassLens.of(minDimension)
        val height = with(plain) { lens.height.dp.toPx() }
        val amount = with(plain) { lens.amount.dp.toPx() }
        val cap = recipe.cap(minDimension)
        layers.face.topLeft = IntOffset(-margin, -margin)
        when {
            solid != null -> {
                ceiling = null
                bands = emptyList()
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                layers.face.renderEffect = GlassLensShader.effect(size, margin, radius, height to amount, recipe, cap)
                ceiling = null
                bands = emptyList()
            }
            else -> {
                ceiling = Color(cap, cap, cap)
                bands = glassBands(path, size, radius, height, amount)
            }
        }
        recordShadow(layers.shadow, plain, glassShadow(minDimension))
        recordEdge(layers, plain, radius)
    }

    /** The shadow under the glass, blurred by the renderer. */
    private fun recordShadow(layer: GraphicsLayer, plain: Density, shadow: Shadow) = with(plain) {
        val blur = shadow.radius.toPx()
        val spread = shadow.spread.toPx()
        val offset = Offset(shadow.offset.x.toPx(), shadow.offset.y.toPx())
        val reach = ceil(3 * blur / sqrt(3f) + spread + maxOf(abs(offset.x), abs(offset.y))).toInt()
        layer.topLeft = IntOffset(-reach, -reach)
        layer.renderEffect = BlurEffect(blur, blur, TileMode.Decal)
        layer.record(plain, layoutDirection, IntSize(size.width.roundToInt() + 2 * reach, size.height.roundToInt() + 2 * reach)) {
            translate(reach + offset.x, reach + offset.y) {
                drawPath(path, shadow.color)
                if (spread > 0) drawPath(path, shadow.color, style = Stroke(2 * spread), blendMode = BlendMode.Src)
            }
        }
    }

    /**
     * The edge, once for each size: the shade and the glow, each an inner shadow (what lies outside the shape, moved and
     * shrunk, blurred by the renderer and clipped to the shape), the dark line outside and the light line inside.
     */
    private fun recordEdge(layers: GlassLayers, plain: Density, radius: Float) = with(plain) {
        val shadeBlur = gaussianBlurRadius(GLASS_SHADE_DEVIATION).toPx()
        val shadeReach = ceil(3 * GLASS_SHADE_DEVIATION.toPx()).toInt()
        val shadeColor = Color.Black.copy(alpha = GLASS_SHADE_ALPHA)
        layers.shade.topLeft = IntOffset(-shadeReach, -shadeReach)
        layers.shade.renderEffect = BlurEffect(shadeBlur, shadeBlur, TileMode.Clamp)
        layers.shade.record(plain, layoutDirection, IntSize(size.width.roundToInt() + 2 * shadeReach, size.height.roundToInt() + 2 * shadeReach)) {
            drawRect(shadeColor)
            translate(shadeReach.toFloat(), shadeReach + GLASS_SHADE_OFFSET.toPx()) {
                drawPath(path, Color.Black, blendMode = BlendMode.Clear)
                drawPath(path, shadeColor, style = Stroke(2 * GLASS_SHADE_SPREAD.toPx()), blendMode = BlendMode.Src)
            }
        }
        val glowBlur = gaussianBlurRadius(GLASS_GLOW_DEVIATION).toPx()
        val glowReach = ceil(3 * GLASS_GLOW_DEVIATION.toPx()).toInt()
        layers.glow.topLeft = IntOffset(-glowReach, -glowReach)
        layers.glow.renderEffect = BlurEffect(glowBlur, glowBlur, TileMode.Clamp)
        layers.glow.record(plain, layoutDirection, IntSize(size.width.roundToInt() + 2 * glowReach, size.height.roundToInt() + 2 * glowReach)) {
            translate(glowReach.toFloat(), glowReach.toFloat()) {
                // The brush blurs with the shadow, as it changes little across the blur.
                val brush = upward(radius) { c -> Color.White.copy(alpha = GLASS_GLOW_ALPHA * c * c) }
                drawRect(brush, Offset(-glowReach.toFloat(), -glowReach.toFloat()), Size(size.width + 2 * glowReach, size.height + 2 * glowReach))
                drawPath(path, Color.Black, blendMode = BlendMode.Clear)
            }
        }
        val rim = sideways(size.width, radius) { s -> Color.Black.copy(alpha = 0.10f + 0.165f * s * s) }
        val highlight = sideways(size.width, radius, GLASS_HIGHLIGHT.toPx()) { s -> Color.White.copy(alpha = GLASS_HIGHLIGHT_LIFT * (1 - s * s)) }
        layers.edge.record(plain, layoutDirection, size.toIntSize()) {
            clipPath(path) {
                drawLayer(layers.shade)
                drawLayer(layers.glow)
            }
            clipPath(path, ClipOp.Difference) { drawPath(path, rim, style = Stroke(2 * GLASS_RIM.toPx())) }
            clipPath(path) { drawPath(path, highlight, style = Stroke(2 * GLASS_HIGHLIGHT.toPx()), blendMode = BlendMode.Plus) }
        }
    }
}

/** The width of the dark line outside the glass's edge: 2 pixels on a 3× iPhone. */
private val GLASS_RIM = 0.67.dp

/** The width of the light line inside the glass's edge: 2 pixels and most of a third on a 3× iPhone. */
private val GLASS_HIGHLIGHT = 0.9.dp

/**
 * What the light line adds, white in 0…1 at the top: CoreAnimation's highlight, at the same amount in light and dark.
 * On the iPhone's close button it takes the face, 246 to 248, to 255 on the line's two whole pixels, and by 6 on the
 * third, which it covers by two thirds.
 */
private const val GLASS_HIGHLIGHT_LIFT = 0.045f

/** The shade inside the glass, cast from above: black at 1.7 %, spread 6, deviation 3, 11 points down. */
private const val GLASS_SHADE_ALPHA = 0.017f
private val GLASS_SHADE_SPREAD = 6.dp
private val GLASS_SHADE_DEVIATION = 3.dp
private val GLASS_SHADE_OFFSET = 11.dp

/** The glow under the glass's top edge: white at up to 8 %, deviation 4. */
private const val GLASS_GLOW_ALPHA = 0.08f
private val GLASS_GLOW_DEVIATION = 4.dp

/**
 * The face's filter, the lens and the ceiling as a runtime shader on the face's layer, which holds the blurred backdrop
 * [margin] pixels past the glass: each pixel takes the backdrop [GlassLens.look] further in along the normal of the
 * nearest edge, the corners taken as circles of the shape's radius, filters it by the recipe's matrix, takes any
 * luminance over the ceiling off every channel, and only then holds the channels to 0…1, as CoreAnimation does.
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private object GlassLensShader {
    private const val SOURCE = """
        uniform shader face;
        uniform float2 origin;
        uniform float2 size;
        uniform float radius;
        uniform float height;
        uniform float amount;
        uniform float margin;
        uniform float3 red;
        uniform float3 green;
        uniform float3 blue;
        uniform float3 offset;
        uniform float cap;

        half4 main(float2 position) {
            float2 middle = size * 0.5;
            float2 p = position - origin - middle;
            float2 q = abs(p) - (middle - radius);
            float depth;
            float2 outward;
            if (q.x > 0.0 && q.y > 0.0) {
                float corner = length(q);
                depth = radius - corner;
                outward = q / corner;
            } else if (q.x > q.y) {
                depth = radius - q.x;
                outward = float2(1.0, 0.0);
            } else {
                depth = radius - q.y;
                outward = float2(0.0, 1.0);
            }
            outward *= float2(p.x < 0.0 ? -1.0 : 1.0, p.y < 0.0 ? -1.0 : 1.0);
            float look = amount * clamp(1.0 - depth / height, 0.0, 1.0);
            float2 reach = middle + margin - 0.5;
            half4 color = face.eval(clamp(p - outward * look, -reach, reach) + origin + middle);
            float alpha = float(color.a);
            float3 behind = alpha > 0.0 ? float3(color.rgb) / alpha : float3(0.0);
            float3 filtered = float3(dot(red, behind), dot(green, behind), dot(blue, behind)) + offset;
            float excess = max(dot(filtered, float3(0.2126, 0.7152, 0.0722)) - cap, 0.0);
            return half4(half3(clamp(filtered - excess, 0.0, 1.0) * alpha), half(alpha));
        }
    """

    fun effect(size: Size, margin: Int, radius: Float, lens: Pair<Float, Float>, recipe: GlassRecipe, cap: Float): RenderEffect {
        val shader = RuntimeShader(SOURCE)
        shader.setFloatUniform("origin", margin.toFloat(), margin.toFloat())
        shader.setFloatUniform("size", size.width, size.height)
        shader.setFloatUniform("radius", radius.coerceIn(0f, size.minDimension / 2))
        shader.setFloatUniform("height", lens.first)
        shader.setFloatUniform("amount", lens.second)
        shader.setFloatUniform("margin", margin.toFloat())
        val matrix = recipe.matrix
        for ((row, name) in listOf("red", "green", "blue").withIndex()) {
            shader.setFloatUniform(name, matrix[row * 5], matrix[row * 5 + 1], matrix[row * 5 + 2])
        }
        shader.setFloatUniform("offset", matrix[4], matrix[9], matrix[14])
        shader.setFloatUniform("cap", cap)
        return android.graphics.RenderEffect.createRuntimeShaderEffect(shader, "face").asComposeRenderEffect()
    }
}

/** Part of the lens on Android 12: the face drawn within [clip] scaled by [scaleX] and [scaleY] about [pivot]. */
private class GlassBand(val clip: Path, val scaleX: Float, val scaleY: Float, val pivot: Offset)

/**
 * The lens along each side of a glass of [size] whose outline is [outline]: within the band, [height] pixels deep and
 * split on the shape's diagonals, a pixel `d` deep shows the face `amount + (1 − amount / height) d` deep, the face
 * mirrored about the band's inner line, along the side's normal. Across the corners it keeps its side's normal.
 */
private fun glassBands(outline: Path, size: Size, radius: Float, height: Float, amount: Float): List<GlassBand> {
    val (width, tall) = size
    val inner = Path().apply {
        addRoundRect(RoundRect(height, height, width - height, tall - height, CornerRadius(maxOf(radius - height, 0f))))
    }
    val band = Path.combine(PathOperation.Difference, outline, inner)
    val middle = size.minDimension / 2
    fun side(a: Offset, b: Offset, c: Offset, d: Offset) = Path.combine(
        PathOperation.Intersect,
        band,
        Path().apply {
            moveTo(a.x, a.y)
            lineTo(b.x, b.y)
            lineTo(c.x, c.y)
            lineTo(d.x, d.y)
            close()
        },
    )
    val scale = 1 / (1 - amount / height)
    return listOf(
        GlassBand(side(Offset(0f, 0f), Offset(width, 0f), Offset(width - middle, middle), Offset(middle, middle)), 1f, scale, Offset(0f, height)),
        GlassBand(side(Offset(0f, tall), Offset(width, tall), Offset(width - middle, tall - middle), Offset(middle, tall - middle)), 1f, scale, Offset(0f, tall - height)),
        GlassBand(side(Offset(0f, 0f), Offset(middle, middle), Offset(middle, tall - middle), Offset(0f, tall)), scale, 1f, Offset(height, 0f)),
        GlassBand(side(Offset(width, 0f), Offset(width - middle, middle), Offset(width - middle, tall - middle), Offset(width, tall)), scale, 1f, Offset(width - height, 0f)),
    )
}

/** Lays the face out past the glass's edge on each side by [GLASS_MARGIN], drawn from where the glass's edge is. */
private fun Modifier.pastTheEdge(): Modifier = layout { measurable, constraints ->
    val width = constraints.maxWidth
    val height = constraints.maxHeight
    val margin = glassMargin()
    val placeable = measurable.measure(Constraints.fixed(width + 2 * margin, height + 2 * margin))
    layout(width, height) { placeable.place(-margin, -margin) }
}

/** [GLASS_MARGIN] in whole pixels. */
private fun Density.glassMargin(): Int = ceil(GLASS_MARGIN.toPx()).toInt()

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
 * Haze's style for a filter: the blur, then the filter if any (the same as before it, the filter being linear), no
 * noise. [fallback] is the filter over [behind], drawn where Haze can't blur.
 */
private fun hazeStyle(blurRadius: Dp, filter: ColorMatrix?, fallback: Color, behind: Color) = HazeBlurStyle {
    blurRadius(blurRadius)
    noiseFactor(0f)
    // What the blur reaches past the backdrop's edge.
    backgroundColor(behind)
    colorEffects(listOfNotNull(filter?.let { HazeColorEffect.colorFilter(ColorFilter.colorMatrix(it)) }))
    fallbackColorEffect(HazeColorEffect.tint(fallback))
}
