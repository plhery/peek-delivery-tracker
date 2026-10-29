package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.GlobalPositionAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.TraversableNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.findNearestAncestor
import androidx.compose.ui.node.requireLayoutCoordinates
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.onLongClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.automaticallyChangedFrom
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.ExperimentalSurfaceShadow
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.delay

/**
 * How a card of the list is one thing to touch and to hear (A-AX06, A-AX07): [cardTouches], cut to its continuous
 * corners with the surfaces' shadow under it when `shadow`. A decorative card, drawn for its look alone over the list,
 * says nothing and takes no touches.
 *
 * The swipe to archive and the zoom into the parcel's page belong to the list, which puts them on the card through its
 * `modifier` and its `actions` for TalkBack; its menu comes up on `onLongClick`.
 */
internal fun Modifier.cardButton(
    decorative: Boolean,
    label: String,
    hint: String?,
    selected: Boolean,
    onOpen: () -> Unit,
    onLongClick: (() -> Unit)?,
    cornerRadius: Dp,
    shadow: Boolean,
    actions: List<CustomAccessibilityAction> = emptyList(),
): Modifier {
    if (decorative) return clearAndSetSemantics {}
    val shape = ContinuousRoundedShape(cornerRadius)
    return cardTouches(label, hint, selected, onOpen, onLongClick, actions)
        .then(if (shadow) Modifier.dropShadow(shape, ExperimentalSurfaceShadow) else Modifier)
        .clip(shape)
}

/**
 * A card or an archived row as one element: read as `label`, a button whose action opens the parcel (`hint` its name
 * where the iPhone gives one), selected while the parcel is shown beside the list, and giving under the finger as
 * ExperimentalSwipeToArchive's card does (the lift). A long press calls `onLongClick`, the list's card menu as the
 * iPhone's `.contextMenu` opens it, with the phone's long-press feel as the iPhone's menu comes with its own; the
 * finger lifting then opens nothing. TalkBack offers it as the element's long-press action, and `actions` (the swipe's
 * Archive) beside it.
 */
internal fun Modifier.cardTouches(
    label: String,
    hint: String?,
    selected: Boolean,
    onOpen: () -> Unit,
    onLongClick: (() -> Unit)?,
    actions: List<CustomAccessibilityAction> = emptyList(),
): Modifier = this
    .clearAndSetSemantics {
        contentDescription = label
        role = Role.Button
        this.selected = selected
        onClick(label = hint) {
            onOpen()
            true
        }
        if (onLongClick != null) {
            this.onLongClick {
                onLongClick()
                true
            }
        }
        if (actions.isNotEmpty()) customActions = actions
    }
    .combinedClickable(interactionSource = null, indication = PressStyle.ExperimentalLift, onLongClick = onLongClick, onClick = onOpen)

/** The texts a card writes, in the order VoiceOver reads them once combined, as one label. */
internal fun cardLabel(vararg parts: String?): String = parts.filterNot { it.isNullOrEmpty() }.joinToString(", ")

/**
 * `SelectedCardRing` (A-S36): a 2-point ring in the ink inside the edge of a card of `cornerRadius` (continuous, or
 * circular as `RoundedRectangle`'s default), shown with a snappy 0.2 s fade while the parcel is shown beside the list.
 * Decorative.
 */
@Composable
fun SelectedCardRing(shown: Boolean, cornerRadius: Dp, continuous: Boolean, modifier: Modifier = Modifier) {
    val reduceMotion = PeekTheme.reduceMotion
    val ink = PeekTheme.colors.ink
    val opacity by animateFloatAsState(if (shown) 1f else 0f, if (reduceMotion) snap() else Motion.snappy(0.2), label = "ring")
    Canvas(modifier.clearAndSetSemantics {}) {
        if (opacity <= 0f) return@Canvas
        // `strokeBorder`: the ring inside the shape, on the shape inset by half its width.
        val line = 2.dp.toPx()
        val inset = cornerRadius - 1.dp
        val shape: Shape = if (continuous) ContinuousRoundedShape(inset) else RoundedCornerShape(inset)
        translate(line / 2, line / 2) {
            val outline = shape.createOutline(Size(size.width - line, size.height - line), layoutDirection, this)
            drawOutline(outline, ink, alpha = opacity, style = Stroke(line))
        }
    }
}

/**
 * `ParcelFlag` (A-S37): a problem kept on the parcel's own card instead of replacing it, its symbol and words in the
 * warning colour on paper at 72 %.
 */
@Composable
fun ParcelFlag(text: String, symbol: String, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Row(
        modifier
            .background(colors.paper.copy(alpha = colors.paper.alpha * 0.72f), RoundedCornerShape(12.dp))
            .padding(start = 8.dp, top = 4.dp, end = 10.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeekSymbol(symbol, font = type.style(IosTextStyle.Caption2, FontWeight.SemiBold), tint = colors.warning)
        Text(text, color = colors.warning, style = type.style(IosTextStyle.Caption, FontWeight.Medium).sfLines())
    }
}

/**
 * `AutomaticCarrierNotice`: for 12 hours after the app swapped the parcel's carrier by itself, the carrier it was
 * swapped from, in caption secondary. Only a parcel with such a change keeps a clock, read again each minute.
 */
@Composable
fun AutomaticCarrierNotice(parcel: Parcel, modifier: Modifier = Modifier) {
    if (parcel.carrierData?.autoChangedFrom == null) return
    val text = automaticCarrierNotice(parcel) ?: return
    Text(text, modifier, color = PeekTheme.colors.secondaryLabel, style = PeekTheme.type.caption.sfLines())
}

/** What [AutomaticCarrierNotice] says now, for the card's label too; null when it says nothing. */
@Composable
internal fun automaticCarrierNotice(parcel: Parcel): String? {
    if (parcel.carrierData?.autoChangedFrom == null) return null
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current ?: return null
    val now = LocalNow.current
    val time by produceState(now(), parcel) {
        // Each minute while the notice may still show, as `TimelineView(.periodic(from: .now, by: 60))`.
        while (parcel.automaticallyChangedFrom(value) != null || stillAhead(parcel, value)) {
            delay(60_000)
            value = now()
        }
    }
    val from = parcel.automaticallyChangedFrom(time) ?: return null
    return localizer.text("parcel.autoChangedCarrier", mapOf("carrier" to identities.displayName(from, language)))
}

/** Whether the parcel's automatic change is still to come at `time`, as a clock set behind the server's can find it. */
private fun stillAhead(parcel: Parcel, time: Instant): Boolean {
    val changed = parcel.carrierData?.autoChangedAt?.let(DateParser::date) ?: return false
    return time.isBefore(changed)
}

/**
 * `.coordinateSpace(name: DeliveryListSpace.name)` as a lazy list can have it: the list puts this on each of its items,
 * and its cards say where they stand and where their names are written from the top of their item
 * ([reportsFrameInList]). The iPhone measures them in the scroll content's space, which scrolling doesn't move; a
 * lazy list has none, so they are measured in their item, which scrolling doesn't move either, and the list adds the
 * item's own place (`LazyListLayoutInfo.visibleItemsInfo`, its `offset` from the top of the list) where it needs one.
 */
fun Modifier.deliveryListItem(): Modifier = this then DeliveryListItemElement

private data object DeliveryListItemElement : ModifierNodeElement<DeliveryListItemNode>() {
    override fun create() = DeliveryListItemNode()

    override fun update(node: DeliveryListItemNode) = Unit

    override fun InspectorInfo.inspectableProperties() {
        name = "deliveryListItem"
    }
}

private class DeliveryListItemNode : Modifier.Node(), TraversableNode {
    override val traverseKey: Any get() = DeliveryListItemKey
}

private object DeliveryListItemKey

/**
 * `.onGeometryChange(for:) { $0.frame(in: .named(DeliveryListSpace.name)) }`: the content's frame in its item of the
 * list ([deliveryListItem]), in points (dp), each time it changes, which scrolling doesn't, and as the list draws another
 * card in the item; in the window's when there is no list, as SwiftUI falls back to the global space.
 */
internal fun Modifier.reportsFrameInList(onFrame: (Rect) -> Unit): Modifier = this then ListFrameElement(onFrame)

private data class ListFrameElement(val onFrame: (Rect) -> Unit) : ModifierNodeElement<ListFrameNode>() {
    override fun create() = ListFrameNode(onFrame)

    override fun update(node: ListFrameNode) {
        node.update(onFrame)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "reportsFrameInList"
    }
}

private class ListFrameNode(private var onFrame: (Rect) -> Unit) :
    Modifier.Node(), GlobalPositionAwareModifierNode, CompositionLocalConsumerModifierNode {
    /** The frame `onFrame` was last told. */
    private var last: Rect? = null

    /**
     * Another one to tell, as when the list gives this item to another card: told at once where the content stands,
     * which it may not leave again for a long while.
     */
    fun update(onFrame: (Rect) -> Unit) {
        if (onFrame == this.onFrame) return
        this.onFrame = onFrame
        last = null
        if (isAttached) requireLayoutCoordinates().takeIf { it.isAttached }?.let(::report)
    }

    /** An item the list kept aside to show again: whoever it tells next hasn't been told. */
    override fun onReset() {
        last = null
    }

    /** Each time the content moves on screen, a scroll too: told only when its frame in the item has changed. */
    override fun onGloballyPositioned(coordinates: LayoutCoordinates) = report(coordinates)

    private fun report(coordinates: LayoutCoordinates) {
        val density = currentValueOf(LocalDensity).density
        val item = findNearestAncestor(DeliveryListItemKey)?.requireLayoutCoordinates()?.takeIf { it.isAttached }
        val origin = item?.localPositionOf(coordinates, Offset.Zero) ?: coordinates.positionInRoot()
        val frame = Rect(origin / density, Size(coordinates.size.width / density, coordinates.size.height / density))
        if (frame == last) return
        last = frame
        onFrame(frame)
    }
}

/**
 * `DeliveryCardPlace`: a card tells the list where it stands (`stand`, its frame in its item), and stays
 * unseen while its picture flies to another place. A card about to become another leaves without fading: the list's
 * own item animation, which the list chooses. Made again for the same card and `stand`, it is the same modifier, so
 * the card isn't drawn again for it.
 */
fun Modifier.deliveryCardPlace(id: UUID, flying: Boolean, stand: (id: UUID, frame: Rect) -> Unit): Modifier =
    reportsFrameInList(CardStand(id, stand)).alpha(if (flying) 0f else 1f)

/** Tells `stand` where the card of `id` stands; equal for the same card and `stand`. */
private data class CardStand(val id: UUID, val stand: (id: UUID, frame: Rect) -> Unit) : (Rect) -> Unit {
    override fun invoke(frame: Rect) = stand(id, frame)
}
