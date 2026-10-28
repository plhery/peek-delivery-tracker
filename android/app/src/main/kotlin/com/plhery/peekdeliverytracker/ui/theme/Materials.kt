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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeInput
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.blur.HazeBlurStyle
import dev.chrisbanes.haze.blur.HazeColorEffect
import dev.chrisbanes.haze.blur.hazeBlur
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
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
        /** Rec. 709 luminance, as Core Image's colour controls weigh it. */
        val LUMA = floatArrayOf(0.2126f, 0.7152f, 0.0722f, 0f)

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

/**
 * SwiftUI's materials, each in light and dark (CoreMaterial's platformContent* recipes; `bar` is platformChrome*).
 * The app itself uses only `ultraThin`, through [GlassSurface].
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
 * no backdrop, under Reduce Transparency ([PeekTheme.reduceTransparency], whose recipes trade the blur for the
 * backdrop's average colour), and under Increased Contrast (iOS 26 then turns its glass mostly black or white with a
 * contrasting edge).
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
    val solid = backdrop == null || PeekTheme.reduceTransparency || colors.highContrast
    Box(modifier) {
        val fill = Modifier.matchParentSize().clip(shape)
        if (solid) {
            Box(fill.background(recipe.over(colors.background)))
        } else {
            val style = remember(recipe, colors.background) { hazeStyle(recipe, colors.background) }
            // Only what lies within the shape is blurred, its edge pixels held at the edge. Haze 2.0.1's wider capture
            // (expandLayerBounds) drew the blur shifted by its margin in Robolectric's renderer.
            Box(fill.hazeBlur(HazeInput.Backdrop(backdrop.haze), style, expandLayerBounds = false))
        }
        content()
    }
}

/**
 * `glassSurface(in:)` as the iPhone draws it before iOS 26: [PeekMaterial.UltraThin] with a 0.7 pt hairline of the
 * separator at 72 %, stroked on the shape's edge over the content. iOS 26's Liquid Glass (`glassEffect(.regular)`)
 * is not recreated. Under Increased Contrast the hairline is the separator at full strength.
 */
@Composable
fun GlassSurface(
    shape: Shape,
    modifier: Modifier = Modifier,
    backdrop: MaterialBackdrop? = LocalMaterialBackdrop.current,
    content: @Composable BoxScope.() -> Unit,
) {
    val colors = PeekTheme.colors
    val hairline = if (colors.highContrast) colors.separator else colors.separator.copy(alpha = colors.separator.alpha * 0.72f)
    val edge = Modifier.drawWithContent {
        drawContent()
        drawOutline(shape.createOutline(size, layoutDirection, this), hairline, style = Stroke(GLASS_HAIRLINE.toPx()))
    }
    MaterialSurface(PeekMaterial.UltraThin, shape, modifier.then(edge), backdrop, content)
}

private val GLASS_HAIRLINE = 0.7.dp

/** Haze's style for a recipe: the blur, then the filter (the same as before it, the filter being linear), no noise. */
private fun hazeStyle(recipe: MaterialRecipe, behind: Color) = HazeBlurStyle {
    blurRadius(recipe.androidBlurRadius)
    noiseFactor(0f)
    // What the blur reaches past the backdrop's edge.
    backgroundColor(behind)
    colorEffects(listOf(HazeColorEffect.colorFilter(ColorFilter.colorMatrix(recipe.colorMatrix))))
    // Where Haze can't blur.
    fallbackColorEffect(HazeColorEffect.tint(recipe.over(behind)))
}
