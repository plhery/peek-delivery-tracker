package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.ShopWaitsOwner
import com.plhery.peekdeliverytracker.core.api.ShopWait
import com.plhery.peekdeliverytracker.core.isExpired
import com.plhery.peekdeliverytracker.core.waits
import com.plhery.peekdeliverytracker.core.words
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.Radii
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.util.UUID
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

// The web's `ShopWaitCards` and `useShopWaits`: the parcels the reader waits for a shop to ship, a card each under the
// cards on their way, read with the list.

/** A wait's card ([ShopWaitCard]): its words, read as one. */
const val SHOP_WAIT_CARD = "deliveries.shopWait"

/** A wait's Stop waiting, or Remove once expired. */
const val SHOP_WAIT_BUTTON = "deliveries.shopWait.remove"

/** How often the list reads its waits while it shows: the server asks the shops every few hours. */
private val READ_EVERY = 10.minutes

/**
 * The waits the list shows for [session]: the demo's one, an account's as last read, none for a guest. An account's
 * are read as the list opens and comes back to the foreground, as a check of every parcel starts (a pull, the
 * button) and every [READ_EVERY] while the list shows. A wait that went without the reader became parcels: the list
 * reads them again.
 */
@Composable
internal fun rememberShopWaits(source: DeliveriesSource, session: AuthenticationState): List<ShopWait> {
    val store = source.shopWaits
    val shown by store.shownFlow.collectAsState()
    val owner = ShopWaitsOwner.of(session)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(store, owner, lifecycle) {
        store.show(owner)
        if (owner !is ShopWaitsOwner.Account) return@LaunchedEffect
        suspend fun read() {
            if (store.read(owner)) source.scope.launch { source.reload() }
        }
        // From the lifecycle rather than a state the list reads: the window's frames stop with it, and a return to
        // the foreground would never be seen to change it.
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch { source.refreshing.drop(1).filter { it }.collect { read() } }
            while (true) {
                read()
                delay(READ_EVERY)
            }
        }
    }
    return shown.waits(owner)
}

/** Stops waiting for [id]: its card goes at once; the list reads its parcels again if a wait went meanwhile. */
internal fun DeliveriesSource.stopWaiting(id: UUID) {
    scope.launch {
        if (shopWaits.remove(ShopWaitsOwner.of(session.value), id)) reload()
    }
}

/**
 * A parcel a shop hasn't shipped yet, as the web and the iPhone draw it: a dashed edge and no fill on the list's page,
 * a parcel still to come. The shop and its platform where a card names its carrier, the name the parcel will take,
 * whom it waits for and until when, and at its end Stop waiting, or Remove once the 14 days passed. TalkBack reads its
 * words as one and its button on its own.
 */
@Composable
internal fun ShopWaitCard(wait: ShopWait, modifier: Modifier = Modifier, onRemove: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val words = wait.words(LocalLocalizer.current, LocalNow.current())
    val expired = wait.isExpired
    val via = type.style(IosTextStyle.Caption, FontWeight.SemiBold).sfLines()
    val caption = type.caption.sfLines()
    Column(
        modifier
            .fillMaxWidth()
            .dashedEdge(colors.separator, ContinuousRoundedShape(Radii.card - EDGE_WIDTH / 2))
            .padding(horizontal = 15.dp, vertical = 13.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Column(
            Modifier.fillMaxWidth().testTag(SHOP_WAIT_CARD).semantics(mergeDescendants = true) {},
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Row(Modifier.padding(bottom = 3.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                PeekSymbol("storefront", Modifier.alignByBaseline(), font = via, tint = colors.inkSoft)
                Text(words.via, Modifier.alignByBaseline(), color = colors.inkSoft, style = via)
            }
            Text(words.title, color = colors.ink, style = type.style(IosTextStyle.Headline, FontWeight.SemiBold).sfLines())
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                PeekSymbol(if (expired) "clock" else "hourglass", Modifier.alignByBaseline(), font = caption, tint = colors.secondaryText)
                Text(words.state, Modifier.alignByBaseline(), color = if (expired) colors.secondaryText else colors.ink, style = caption)
            }
            Text(words.detail, color = colors.secondaryText, style = caption)
        }
        // The words stand at the card's end, as the web's; the button's room reaches round them.
        Box(
            Modifier
                .align(Alignment.End)
                .overhang(end = BUTTON_PADDING, vertical = BUTTON_OVERHANG)
                .heightIn(min = BUTTON_HEIGHT)
                .testTag(SHOP_WAIT_BUTTON)
                .button(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button, onClick = onRemove)
                .padding(horizontal = BUTTON_PADDING),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                words.action,
                color = colors.resolve(ExperimentalPalette.delivered),
                style = type.style(IosTextStyle.Footnote, FontWeight.SemiBold).sfLines(),
                textAlign = TextAlign.End,
            )
        }
    }
}

/** The dashed edge's width, drawn inside the card. */
private val EDGE_WIDTH = 1.5.dp

/** Room on each side of the button's words, which reaches past the card's padding at its end. */
private val BUTTON_PADDING = 10.dp

/** The button's touch height, Android's least. */
private val BUTTON_HEIGHT = 48.dp

/** How far the button's room reaches above and below its row, as the iPhone's. */
private val BUTTON_OVERHANG = 10.dp

/** `strokeBorder(_, style: StrokeStyle(lineWidth: 1.5, dash: [6, 4]))`: a dashed line along [shape], inside the bounds. */
private fun Modifier.dashedEdge(color: Color, shape: Shape): Modifier = drawWithCache {
    val width = EDGE_WIDTH.toPx()
    val outline = shape.createOutline(Size(size.width - width, size.height - width), layoutDirection, this)
    val stroke = Stroke(width, pathEffect = PathEffect.dashPathEffect(floatArrayOf(6.dp.toPx(), 4.dp.toPx())))
    onDrawBehind { translate(width / 2, width / 2) { drawOutline(outline, color, style = stroke) } }
}

/** Negative padding (`.padding(.trailing, -10)`, `.padding(.vertical, -10)`): drawn and touched this far past its place. */
private fun Modifier.overhang(end: Dp, vertical: Dp): Modifier = layout { measurable, constraints ->
    val x = end.roundToPx()
    val y = vertical.roundToPx()
    val placeable = measurable.measure(constraints.offset(horizontal = x, vertical = 2 * y))
    val width = (placeable.width - x).coerceAtLeast(0)
    val height = (placeable.height - 2 * y).coerceAtLeast(0)
    layout(width, height) { placeable.placeRelative(0, -y) }
}
