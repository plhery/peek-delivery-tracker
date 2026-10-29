package com.plhery.peekdeliverytracker.ui.deliveries

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorizedFiniteAnimationSpec
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.node.LayoutAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.core.DeliveredConfetti
import com.plhery.peekdeliverytracker.core.DeliveredFlightPose
import com.plhery.peekdeliverytracker.core.DeliveryCardKind
import com.plhery.peekdeliverytracker.core.DeliveryCardPlace
import com.plhery.peekdeliverytracker.core.MapPath
import com.plhery.peekdeliverytracker.core.PipGeometry
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.displayedCarrier
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.draw.ComposePathFiller
import com.plhery.peekdeliverytracker.ui.draw.toComposePath
import com.plhery.peekdeliverytracker.ui.map.LocalMapsAtOnce
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.gaussianBlurRadius
import java.util.UUID
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import com.plhery.peekdeliverytracker.core.Rect as CoreRect

// DeliveredCardFlight.swift's screen side: where the cards stand, the paper out of a delivered parcel's card and the
// cards flown to their new places, each drawn on one canvas, a frame at a time.

/**
 * `DeliveryCardPlaces`: where each card stands in the list, where Pip shows on one, and what each card shows. The
 * iPhone measures a card in the scroll content's space; a lazy list has none, so a place is measured from the list's
 * top left as it shows, and a card's flight follows the content as it moves ([DeliveriesState.contentShift]). A card no
 * longer laid out in the list has no place, where the iPhone keeps the last it had.
 */
@Stable
internal class DeliveryCardPlaces {
    /** Each card's node, as it stands in the list. */
    private val cards = mutableMapOf<UUID, LayoutCoordinates>()

    /** Each card's frame and the middle of its parcel's name, in points in its item of the list. */
    private val frames = mutableMapOf<UUID, Rect>()
    private val names = mutableMapOf<UUID, Float>()

    /** What each card shows, as it was last drawn: its menu's picture. */
    private val pictures = mutableMapOf<UUID, GraphicsLayer>()

    /** Where Pip shows in the window, in points, and on whose card. */
    private var pip: Pair<UUID, Rect>? = null

    /** The list's node, which the places are measured from. */
    var list: LayoutCoordinates? = null

    var density: Float = 1f

    fun stand(id: UUID, frame: Rect) {
        frames[id] = frame
    }

    fun name(id: UUID, middle: Float) {
        names[id] = middle
    }

    fun card(id: UUID, coordinates: LayoutCoordinates) {
        cards[id] = coordinates
    }

    /** The card's node has left the list, or shows another parcel. */
    fun leave(id: UUID, coordinates: LayoutCoordinates) {
        if (cards[id] === coordinates) cards.remove(id)
    }

    fun picture(id: UUID, picture: GraphicsLayer) {
        pictures[id] = picture
    }

    fun dropPicture(id: UUID, picture: GraphicsLayer) {
        if (pictures[id] === picture) pictures.remove(id)
    }

    fun see(pip: Rect, id: UUID) {
        this.pip = id to pip
    }

    /** A card that leaves its place is asked again where it stands once it has another. */
    fun forget(id: UUID) {
        cards.remove(id)
        frames.remove(id)
        names.remove(id)
    }

    private val shownList: LayoutCoordinates? get() = list?.takeIf { it.isAttached }

    fun place(id: UUID): DeliveryCardPlace? {
        val list = shownList ?: return null
        val card = cards[id]?.takeIf { it.isAttached } ?: return null
        val inItem = frames[id] ?: return null
        val box = list.localBoundingBoxOf(card, clipBounds = false)
        val scale = density.toDouble()
        val frame = CoreRect(box.left / scale, box.top / scale, box.width / scale, box.height / scale)
        return DeliveryCardPlace(frame, ((names[id] ?: inItem.center.y) - inItem.top).toDouble())
    }

    /** The part of the list that shows: all of it, from its top left. */
    val visible: CoreRect?
        get() = shownList?.size?.let { CoreRect(0.0, 0.0, it.width / density.toDouble(), it.height / density.toDouble()) }

    /**
     * Where in the window, in points, something comes out of a parcel's card: the mouth of Pip's box when he is on it,
     * else the end of the card where its route and stamp are. Null for a card whose place does not show.
     */
    fun mouth(id: UUID): Point? {
        val list = shownList ?: return null
        val frame = place(id)?.frame ?: return null
        val visible = visible ?: return null
        val corner = list.positionInWindow()
        val left = corner.x / density.toDouble()
        val top = corner.y / density.toDouble()
        val seen = pip
        if (seen != null && seen.first == id) {
            val mouth = Point(seen.second.center.x.toDouble(), seen.second.top + seen.second.height * 0.38)
            if (CoreRect(left, top, visible.width, visible.height).contains(mouth)) return mouth
        }
        val end = Point(frame.maxX - 52, frame.minY + min(frame.height / 2, 64.0))
        return if (visible.contains(end)) Point(left + end.x, top + end.y) else null
    }

    /**
     * `picture(of:)`: what the card shows, kept for its flight once the card has gone, in a layer of its own that draws
     * the card's picture. Null for a card without one, or with no size.
     */
    fun picture(id: UUID, graphics: GraphicsContext, density: Density, layoutDirection: LayoutDirection): GraphicsLayer? {
        val card = pictures[id] ?: return null
        if (card.size.width < density.density || card.size.height < density.density) return null
        val layer = graphics.createGraphicsLayer()
        layer.record(density, layoutDirection, card.size) { drawLayer(card) }
        return layer
    }
}

/** One card on its way to another place in the list (`DeliveredFlight`). */
@Stable
internal class DeliveredFlight(
    /** The parcel's. */
    val id: UUID,
    /** The parcel as it is now. */
    val parcel: Parcel,
    val was: DeliveryCardKind,
    val becomes: DeliveryCardKind,
    /** The card it was, as it showed. */
    val picture: GraphicsLayer,
    val from: DeliveryCardPlace,
    /** A delivered parcel's card is carried over the others; the one that takes its place is not. */
    val lifted: Boolean,
    /** How far the list's content had moved ([DeliveriesState.contentShift]) when `from` was measured. */
    val shift: Float,
) {
    /** Where the card it becomes stands, once the list has laid it out. */
    var to: DeliveryCardPlace? by mutableStateOf(null)

    /** The card it becomes, recorded as the flights lay it out ([FlightFace]), and the ground under it. */
    var face: GraphicsLayer? = null
    var ground = Color.Transparent
    var tint = Color.Transparent

    /** What drawing it keeps from one frame to the next. */
    val pose = DeliveredFlightPose()
    val outline = MapPath()
    val clip = ComposePathFiller()
}

/** A burst of paper as the screen shows it: in a card's colours, since its first frame (`DeliveredPaperBurst`). */
internal class DeliveredPaperBurst(val confetti: DeliveredConfetti, val colors: List<AdaptiveColor>) {
    /** The frame it was first drawn on, in the frame clock's nanoseconds; -1 before. */
    var started = -1L
}

/** The seeds of the bursts of paper, random but in the tests, which fix them to draw the same paper each time. */
internal val LocalPaperSeeds = staticCompositionLocalOf<(() -> ULong)?> { null }

/** Told the list's state as the tab is drawn: the tests read where the paper is thrown from and where the cards fly. */
internal val LocalDeliveriesState = staticCompositionLocalOf<((DeliveriesState) -> Unit)?> { null }

/**
 * The rest of `DeliveryCardPlace`: the card's node, which its place in the list is measured from, until it leaves the
 * list. Its frame in its item is [deliveryCardPlace]'s.
 */
internal fun Modifier.cardInList(id: UUID, places: DeliveryCardPlaces): Modifier = this then CardInListElement(id, places)

private data class CardInListElement(val id: UUID, val places: DeliveryCardPlaces) : ModifierNodeElement<CardInListNode>() {
    override fun create() = CardInListNode(id, places)

    override fun update(node: CardInListNode) {
        node.update(id, places)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "cardInList"
    }
}

private class CardInListNode(private var id: UUID, private var places: DeliveryCardPlaces) : Modifier.Node(), LayoutAwareModifierNode {
    private var coordinates: LayoutCoordinates? = null

    fun update(id: UUID, places: DeliveryCardPlaces) {
        if (id == this.id && places === this.places) return
        coordinates?.let { this.places.leave(this.id, it) }
        this.id = id
        this.places = places
        coordinates?.let { places.card(id, it) }
    }

    override fun onPlaced(coordinates: LayoutCoordinates) {
        this.coordinates = coordinates
        places.card(id, coordinates)
    }

    override fun onDetach() {
        coordinates?.let { places.leave(id, it) }
        coordinates = null
    }
}

/**
 * The list's motion for its items: SwiftUI's `.snappy(duration: 0.32)` as its parcels change, or the
 * `.spring(duration: 0.42)` the held parcels are let go with while they are ([DeliveriesState.releasing]), chosen as
 * each item's animation starts.
 */
internal class ListMotion<T>(private val releasing: () -> Boolean) : FiniteAnimationSpec<T> {
    private val changing = Motion.snappy<T>(LIST_MOTION)
    private val release = Motion.spring<T>(RELEASE_MOTION)

    override fun <V : AnimationVector> vectorize(converter: TwoWayConverter<T, V>): VectorizedFiniteAnimationSpec<V> =
        (if (releasing()) release else changing).vectorize(converter)
}

/**
 * `DeliveredPaper`: the paper over the list, which never takes a touch and which TalkBack doesn't reach. One canvas
 * draws every piece, a frame at a time, from the time since the burst; nothing is made anew for a frame.
 */
@Composable
internal fun DeliveredPaper(state: DeliveriesState) {
    val burst = state.paper ?: return
    val colors = PeekTheme.colors
    val tones = remember(burst, colors) { burst.colors.map(colors::resolve) }
    val seconds = remember(burst) { mutableFloatStateOf(0f) }
    LaunchedEffect(burst) {
        val duration = burst.confetti.duration
        do {
            withFrameNanos { now ->
                if (burst.started < 0) burst.started = now
                seconds.floatValue = ((now - burst.started) / 1e9).toFloat()
            }
        } while (seconds.floatValue < duration)
    }
    val glint = remember { PipGeometry.glint.toComposePath() }
    val pose = remember { DeliveredConfetti.Pose() }
    val corner = remember { WindowCorner() }
    Spacer(
        Modifier
            .fillMaxSize()
            .testTag(DELIVERED_PAPER)
            .clearAndSetSemantics {}
            .onGloballyPositioned { corner.offset = it.positionInWindow() }
            .drawBehind {
                val time = seconds.floatValue.toDouble()
                val confetti = burst.confetti
                // The burst is told where it is in the window.
                translate(-corner.offset.x, -corner.offset.y) {
                    scale(density, density, Offset.Zero) {
                        // By index, as every loop drawing a frame: no iterator is made for it.
                        for (index in confetti.pieces.indices) {
                            val piece = confetti.pieces[index]
                            if (!confetti.pose(piece, time, pose) || abs(pose.scaleY) <= 0.02) continue
                            val width = piece.width.toFloat()
                            val height = piece.height.toFloat()
                            val alpha = max(0f, pose.opacity.toFloat())
                            placed(pose) {
                                val topLeft = Offset(-width / 2, -height / 2)
                                if (piece.round) {
                                    drawOval(tones[piece.tone], topLeft, Size(width, height), alpha)
                                } else {
                                    drawRoundRect(tones[piece.tone], topLeft, Size(width, height), CornerRadius(PAPER_CORNER), alpha = alpha)
                                }
                            }
                        }
                        for (index in confetti.glints.indices) {
                            val star = confetti.glints[index]
                            if (!confetti.pose(star, time, pose) || pose.scaleX <= 0.02) continue
                            val half = (star.size / 2).toFloat()
                            val alpha = max(0f, pose.opacity.toFloat())
                            placed(pose) {
                                scale(half, half, Offset.Zero) { drawPath(glint, tones[star.tone], alpha) }
                            }
                        }
                    }
                }
            },
    )
}

/** Where the paper's canvas stands in the window, read as it is drawn. */
private class WindowCorner {
    var offset = Offset.Zero
}

/** `place(_:_:)`: a piece where its pose has it, turned and tumbling. */
private inline fun DrawScope.placed(pose: DeliveredConfetti.Pose, crossinline block: DrawScope.() -> Unit) {
    withTransform({
        translate(pose.centerX.toFloat(), pose.centerY.toFloat())
        rotate(pose.turn.toFloat(), Offset.Zero)
        scale(pose.scaleX.toFloat(), pose.scaleY.toFloat(), Offset.Zero)
    }) { block() }
}

/**
 * The cards on their way to another place, over the list and moving with it (`DeliveredFlightCard`): the picture of the
 * card each was and the card it becomes ride in one box, which changes size from its far edge, the picture sliding so
 * that both names stay on one line and the new card coming up over it. The cards they become are laid out here but
 * drawn only through their flights, all on one canvas, a frame at a time from the flights' progress. Decorative:
 * TalkBack doesn't reach them, and they take no touches.
 */
@Composable
internal fun DeliveredFlights(state: DeliveriesState, modifier: Modifier = Modifier) {
    val flights = state.flights
    if (flights.isEmpty()) return
    val progress = state.flightProgress
    val density = LocalDensity.current
    // `.shadow(color: .black.opacity(0.2 * shadow), radius: 14, y: 12)`, a Gaussian as SwiftUI's.
    val shadow = remember(density) {
        Paint().also {
            val radius = with(density) { gaussianBlurRadius(FLIGHT_SHADOW_RADIUS).toPx() }
            it.asFrameworkPaint().maskFilter = BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL)
        }
    }
    Box(
        modifier
            .fillMaxSize()
            .clipToBounds()
            .testTag(DELIVERED_FLIGHTS)
            .clearAndSetSemantics {}
            .drawWithContent {
                // The cards they become record themselves here, and show only through their flights.
                drawContent()
                val time = progress.value.toDouble()
                val shift = state.contentShift
                for (index in flights.indices) drawFlight(flights[index], time, shift, shadow)
            },
    ) {
        for (flight in flights) key(flight) { FlightFace(flight) }
    }
}

/**
 * The card a flight becomes, laid out at the size of its new place and recorded for the flight to draw: as the list
 * draws it, from the first frame it shows in, its map made then rather than coming in after ([LocalMapsAtOnce]).
 */
@Composable
private fun FlightFace(flight: DeliveredFlight) {
    val layer = rememberGraphicsLayer()
    val colors = PeekTheme.colors
    val identities = LocalCarrierIdentities.current
    val language = LocalLanguage.current
    val identity = remember(identities, flight, language) { identities?.of(flight.parcel.displayedCarrier, language) }
    val ground = colors.resolve(Brand.paper)
    // A paler card shows its own ground around it while the box is still tall.
    val tint = identity?.let { colors.resolve(it.surface.opacity(if (flight.becomes == DeliveryCardKind.Past) 0.35 else 1.0)) }
    SideEffect {
        flight.face = layer
        flight.ground = ground
        flight.tint = tint ?: Color.Transparent
    }
    val to = flight.to ?: flight.from
    val face = Modifier
        .layout { measurable, _ ->
            val width = to.frame.width.toFloat().dp.roundToPx()
            val height = to.frame.height.toFloat().dp.roundToPx()
            val placeable = measurable.measure(Constraints(minWidth = width, maxWidth = width))
            layout(width, height) { placeable.place(0, 0) }
        }
        // Its own layer, so that each frame of the flights draws it again without recording it again.
        .graphicsLayer()
        .drawWithContent { layer.record { this@drawWithContent.drawContent() } }
    CompositionLocalProvider(LocalMapsAtOnce provides true) {
        if (flight.becomes == DeliveryCardKind.Next) {
            NextDeliveryPass(flight.parcel, onOpen = {}, modifier = face, decorative = true)
        } else {
            ParcelPassCard(flight.parcel, notice = null, onOpen = {}, modifier = face, decorative = true)
        }
    }
}

/** One flight where its progress has it, in the list's pixels; `shift` is how far the list's content has moved in all. */
private fun DrawScope.drawFlight(flight: DeliveredFlight, progress: Double, shift: Float, shadow: Paint) {
    val to = flight.to ?: flight.from
    val pose = flight.pose.set(flight.from, to, flight.was, flight.becomes, flight.lifted, progress)
    val scale = density
    val left = (pose.minX * scale).toFloat()
    val top = (pose.minY * scale).toFloat() + shift - flight.shift
    val right = left + (pose.width * scale).toFloat()
    val bottom = top + (pose.height * scale).toFloat()
    flight.outline.rewind()
    flight.outline.addRoundedRect(left.toDouble(), top.toDouble(), right.toDouble(), bottom.toDouble(), pose.cornerRadius * scale)
    val outline = flight.clip.fill(flight.outline)
    if (pose.shadow > 0) {
        // The box's own shadow. Blurred this much, a rounded rectangle's circular corners look as the box's continuous
        // ones, and the renderer blurs a rounded rectangle from a small mask it keeps, where it would blur the whole of
        // a path's mask again each frame.
        shadow.color = Color.Black.copy(alpha = (FLIGHT_SHADOW_OPACITY * pose.shadow).toFloat())
        val drop = FLIGHT_SHADOW_DROP.toPx()
        val corner = (pose.cornerRadius * scale).toFloat()
        drawIntoCanvas { it.drawRoundRect(left, top + drop, right, bottom + drop, corner, corner, shadow) }
    }
    clipPath(outline) {
        translate(left, top + (pose.leaving * scale).toFloat()) { drawLayer(flight.picture) }
        val face = flight.face
        if (pose.arrival > 0 && face != null) {
            drawIntoCanvas { canvas ->
                val alpha = (pose.arrival * 255).roundToInt().coerceIn(0, 255)
                val count = canvas.nativeCanvas.saveLayerAlpha(left, top, right, bottom, alpha)
                drawRect(flight.ground, Offset(left, top), Size(right - left, bottom - top))
                drawRect(flight.tint, Offset(left, top), Size(right - left, bottom - top))
                translate(left, top + (pose.arriving * scale).toFloat()) { drawLayer(face) }
                canvas.nativeCanvas.restoreToCount(count)
            }
        }
    }
}

/** The paper's rounded corner, in points. */
private const val PAPER_CORNER = 1.5f

/** The flight's shadow: black at 20 %, a radius of 14 and 12 down. */
private val FLIGHT_SHADOW_RADIUS = 14.dp
private const val FLIGHT_SHADOW_OPACITY = 0.2
private val FLIGHT_SHADOW_DROP = 12.dp

/** The test tags of the paper and of the cards in flight, which TalkBack doesn't reach: there only while they show. */
const val DELIVERED_PAPER = "deliveries.paper"
const val DELIVERED_FLIGHTS = "deliveries.flights"

/** The list's own motion, and the release's, in seconds. */
internal const val LIST_MOTION = 0.32
private const val RELEASE_MOTION = 0.42
