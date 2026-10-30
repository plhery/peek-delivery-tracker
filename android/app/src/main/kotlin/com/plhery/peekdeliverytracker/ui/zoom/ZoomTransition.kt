package com.plhery.peekdeliverytracker.ui.zoom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.GlobalPositionAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.requireGraphicsContext
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toIntSize
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.AccessibilityFocusNode
import kotlin.math.min

/**
 * A zoom's namespace (`@Namespace var transition`): the sources its pages zoom out of ([zoomSource],
 * `matchedTransitionSource(id:in:)`) and the stack that zooms them ([ZoomStack], `.navigationTransition(.zoom(...))`).
 * A page's id is its source's: a page opens out of the source with its id and goes back into it, wherever that is
 * by then, or from and to the middle of the window, fading, where none can be seen.
 *
 * What lies under the pages reads [behind]: as a page comes over it, the iPhone's list recedes and its bar's items
 * fade, its bars staying where they are ([recedingUnderZoom], [fadingUnderZoom]), the items whose places the page's own
 * bar takes crossfading with it ([givingWayToPageBar]).
 */
@Stable
class ZoomTransition {
    internal val sources = HashMap<Any, ZoomSourceNode>()

    /** The flight of the stack drawing the pages. */
    internal var flight: ZoomFlight? by mutableStateOf(null)
        private set

    /**
     * The page over the root, or on its way: one that has left the stack stays drawn until it has gone back into its
     * source. A stack drawing pages that zoom draws `top ?: covered`, so that a page that leaves stays drawn from the
     * frame it leaves in until it lands.
     */
    val covered: Any? get() = flight?.id

    /** The source unseen while its page is over it or on its way, as the iPhone's is; none in Reduce Motion's crossfade. */
    internal val hidden: Any? get() = flight?.hidden

    /** How far the page has come over what lies under it, from 0 to 1. */
    val behind: Float get() = flight?.behind() ?: 0f

    /** How much of the page's own bar shows at the window's top, from 0 to 1 ([ZoomStack]'s `bar`). */
    val pageBar: Float get() = flight?.barOpacity() ?: 0f

    /** Inside what recedes under the pages ([recedingUnderZoom]), as last placed. */
    internal var receding: LayoutCoordinates? = null

    /** The page to show at rest as it next comes on top ([showStill]). */
    internal var still: Any? = null

    /**
     * The page with `id` shows over the root at rest as it next comes on top, without a flight, as a page the iPhone
     * opens in a transaction that disables animations (`withTransaction(still)`): a parcel open again after the
     * system closed the app.
     */
    fun showStill(id: Any) {
        still = id
    }

    internal fun attach(flight: ZoomFlight) {
        this.flight = flight
    }

    /** Forgets the pages as the stack drawing them goes. */
    internal fun detach(flight: ZoomFlight) {
        if (this.flight !== flight) return
        this.flight = null
        still = null
    }
}

@Composable
fun rememberZoomTransition(): ZoomTransition = remember { ZoomTransition() }

/**
 * `matchedTransitionSource(id:in:)`: the page with `id` zooms out of this and back into it, corners `cornerRadius`
 * round, and while it is over it or on its way this stays unseen. Its picture is what the page grows out of. TalkBack
 * comes back here as the page goes, to `focus` where given (one the element marks already), to this otherwise.
 */
internal fun Modifier.zoomSource(id: Any, transition: ZoomTransition, cornerRadius: Dp = 0.dp, focus: AccessibilityFocus? = null): Modifier =
    this then ZoomSourceElement(id, transition, cornerRadius, focus)

/**
 * What lies under a zooming page and moves with the page: it recedes about its middle as the page comes over it.
 * The iPhone's list recedes, and its bars stay where they are. A page flying out of a source in it or back into one
 * finds the source as far receded as the page has come, from the same frame.
 */
fun Modifier.recedingUnderZoom(transition: ZoomTransition): Modifier = graphicsLayer {
    val scale = ZoomGeometry.receded(transition.behind)
    scaleX = scale
    scaleY = scale
}.onPlaced { transition.receding = it }

/** The bar's items under a zooming page, which fade where they are as the page comes over them. */
fun Modifier.fadingUnderZoom(transition: ZoomTransition): Modifier = graphicsLayer { alpha = 1f - transition.behind.coerceIn(0f, 1f) }

/**
 * The bar's items whose places the page's own bar takes, its ‹ and ••• at the window's top: they fade as the page
 * comes over them, as [fadingUnderZoom]'s do, and come back only as the page's bar goes, the two crossfading as the
 * iPhone's navigation bar crossfades its items.
 */
fun Modifier.givingWayToPageBar(transition: ZoomTransition): Modifier = graphicsLayer {
    alpha = min(1f - transition.behind.coerceIn(0f, 1f), 1f - transition.pageBar.coerceIn(0f, 1f))
}

private data class ZoomSourceElement(
    val id: Any,
    val transition: ZoomTransition,
    val cornerRadius: Dp,
    val focus: AccessibilityFocus?,
) : ModifierNodeElement<ZoomSourceNode>() {
    override fun create() = ZoomSourceNode(id, transition, cornerRadius, focus)

    override fun update(node: ZoomSourceNode) {
        node.update(id, transition, cornerRadius, focus)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "zoomSource"
        properties["id"] = id
        properties["cornerRadius"] = cornerRadius
    }
}

/**
 * A zoom's source: where it is, and its picture, drawn into a layer of its own that the page grows out of. It draws
 * the picture itself unless its page covers it.
 */
internal class ZoomSourceNode(
    var id: Any,
    var transition: ZoomTransition,
    var cornerRadius: Dp,
    private var givenFocus: AccessibilityFocus?,
) : DelegatingNode(), DrawModifierNode, GlobalPositionAwareModifierNode {
    var coordinates: LayoutCoordinates? = null
        private set

    var picture: GraphicsLayer? = null
        private set

    /** Where TalkBack goes back to. */
    val focus: AccessibilityFocus get() = givenFocus ?: ownFocus

    private val ownFocus = AccessibilityFocus()
    private var mark: AccessibilityFocusNode? = null

    init {
        if (givenFocus == null) mark = delegate(AccessibilityFocusNode(ownFocus))
    }

    fun update(id: Any, transition: ZoomTransition, cornerRadius: Dp, focus: AccessibilityFocus?) {
        if (isAttached && (id != this.id || transition !== this.transition)) unregister()
        this.id = id
        this.transition = transition
        this.cornerRadius = cornerRadius
        if ((givenFocus == null) != (focus == null)) {
            mark?.let { undelegate(it) }
            mark = if (focus == null) delegate(AccessibilityFocusNode(ownFocus)) else null
        }
        givenFocus = focus
        if (isAttached) register()
    }

    override fun onAttach() {
        picture = requireGraphicsContext().createGraphicsLayer()
        register()
    }

    override fun onDetach() {
        unregister()
        picture?.let { requireGraphicsContext().releaseGraphicsLayer(it) }
        picture = null
        coordinates = null
    }

    private fun register() {
        transition.sources[id] = this
    }

    private fun unregister() {
        if (transition.sources[id] === this) transition.sources.remove(id)
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        this.coordinates = coordinates
    }

    override fun ContentDrawScope.draw() {
        val layer = picture
        if (layer == null) {
            drawContent()
            return
        }
        layer.record(size.toIntSize()) { this@draw.drawContent() }
        if (transition.hidden != id) drawLayer(layer)
    }
}
