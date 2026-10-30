package com.plhery.peekdeliverytracker.ui.deliveries

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.PickupPoint
import com.plhery.peekdeliverytracker.core.ShipmentFact
import com.plhery.peekdeliverytracker.core.activeTrackingCarrier
import com.plhery.peekdeliverytracker.core.amazonShippingHistoryExpired
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlin.math.roundToInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// What `liveParcelPass` shows under the card: the pickup point's card, what the carrier says, the shipment's facts,
// and what the page says where no carrier is asked or the last check failed.

/**
 * `pickupPointCard` (B-S05): where the parcel waits for collection, with a way to get there, 16 in from a stroke in
 * the carrier's ink at 18 % with 16-point corners, its parts 14 apart. The storefront in the ink on the carrier's
 * surface, 40 square with 12-point corners (none at the accessibility sizes), 12 beside "Pickup point" in caption
 * secondary, the point's name in semibold callout and its address in footnote, which TalkBack reads as one.
 *
 * Under them the directions, in the surface on the ink, and the address's copy, in a stroke of the secondary grey at
 * 30 %, 12-point corners and 44 tall each, their symbol and words in subheadline, giving under the finger: side by side
 * in equal halves while each reads on one line in its half, otherwise one under the other ([PickupButtons]).
 * Directions (B-G16, B-L06) calls `onDirections` and opens [directionsUrl], "Show on map" with a search where the
 * carrier gives no address, which has no copy. The copy calls `onCopy`, which puts the address on the clipboard, and
 * says "Copied" for 2 s in the room of its own words (B-H02).
 */
@Composable
internal fun PickupPointCard(
    point: PickupPoint,
    identity: CarrierVisualIdentity,
    onDirections: () -> Unit,
    onCopy: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val ink = colors.resolve(identity.ink)
    val surface = colors.resolve(identity.surface)
    val secondary = colors.secondaryLabel
    val uriHandler = LocalUriHandler.current
    val scope = rememberCoroutineScope()
    var copied by remember { mutableStateOf(false) }
    val address = point.address
    Column(
        modifier
            .fillMaxWidth()
            .border(1.dp, ink.copy(alpha = ink.alpha * 0.18f), RoundedCornerShape(CARD_RADIUS))
            .padding(16.dp)
            .semantics { isTraversalGroup = true },
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Top) {
            if (!large) {
                Box(Modifier.size(40.dp).background(surface, RoundedCornerShape(BUTTON_RADIUS)), contentAlignment = Alignment.Center) {
                    PeekSymbol("storefront", font = type.fixed(17f), tint = ink)
                }
            }
            Column(
                Modifier.weight(1f, fill = false).semantics(mergeDescendants = true) {}.testTag(PAGE_PICKUP_POINT),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(localizer.text("detail.pickupPoint"), color = secondary, style = type.caption.sfLines())
                Text(point.name, color = colors.label, style = type.style(IosTextStyle.Callout, FontWeight.SemiBold).sfLines())
                if (address != null) Text(address, color = colors.label, style = type.footnote.sfLines())
            }
        }
        PickupButtons(stacked = large) {
            val directions = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold)
            Row(
                Modifier
                    .testTag(PAGE_PICKUP_DIRECTIONS)
                    .clickable(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button) {
                        onDirections()
                        try {
                            uriHandler.openUri(point.directionsUrl)
                        } catch (_: IllegalArgumentException) {
                            // Where nothing opens it, as Compose's handler says: nothing, as with the iPhone's openURL.
                        }
                    }
                    .background(ink, RoundedCornerShape(BUTTON_RADIUS))
                    .heightIn(min = BUTTON_HEIGHT)
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(LABEL_SPACING, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PeekSymbol(if (address == null) "magnifyingglass" else "location.fill", font = directions, tint = surface)
                Text(
                    localizer.text(if (address == null) "detail.pickupShowOnMap" else "detail.pickupDirections"),
                    Modifier.weight(1f, fill = false),
                    color = surface,
                    style = directions.sfLines(),
                )
            }
            if (address != null) {
                val copy = {
                    onCopy()
                    copied = true
                    // Each copy clears the word after its own 2 s, as the iPhone's task does.
                    scope.launch {
                        delay(COPIED_DURATION)
                        copied = false
                    }
                    Unit
                }
                val label = localizer.text(if (copied) "detail.copied" else "detail.pickupCopyAddress")
                Box(
                    Modifier
                        .testTag(PAGE_PICKUP_COPY)
                        .clearAndSetSemantics {
                            contentDescription = label
                            role = Role.Button
                            onClick {
                                copy()
                                true
                            }
                        }
                        .clickable(interactionSource = null, indication = PressStyle.Tactile, onClick = copy)
                        .border(1.dp, secondary.copy(alpha = secondary.alpha * 0.3f), RoundedCornerShape(BUTTON_RADIUS))
                        .heightIn(min = BUTTON_HEIGHT)
                        .padding(horizontal = 10.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    // It keeps the size of its own words while it says it copied them, so the buttons stay where they are.
                    PickupLabel("doc.on.doc", localizer.text("detail.pickupCopyAddress"), Modifier.alpha(if (copied) 0f else 1f))
                    if (copied) PickupLabel("checkmark", localizer.text("detail.copied"), Modifier.matchParentSize().wrapContentSize(unbounded = true))
                }
            }
        }
    }
}

/**
 * The copy button's symbol and words in subheadline, `Label`'s 6 apart (estimated, as the map's view buttons are): the
 * words wrap in what room the symbol leaves them, or keep to one line where the room is unbounded (`fixedSize`).
 */
@Composable
private fun PickupLabel(symbol: String, text: String, modifier: Modifier) {
    val style = PeekTheme.type.subheadline
    val tint = PeekTheme.colors.label
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(LABEL_SPACING), verticalAlignment = Alignment.CenterVertically) {
        PeekSymbol(symbol, font = style, tint = tint)
        Text(text, color = tint, style = style.sfLines())
    }
}

/**
 * `ViewThatFits { EqualWidthRow(spacing: 8); VStack(spacing: 8) }`: the buttons side by side in equal widths, 8 apart,
 * while the widest of them on one line fits in its share, as `EqualWidthRow` asks for no less; otherwise, and always
 * when `stacked` (the accessibility sizes), one under the other, 8 apart, each across the card. Side by side they stand
 * at the top of the row, as `EqualWidthRow` places them.
 */
@Composable
private fun PickupButtons(stacked: Boolean, content: @Composable () -> Unit) {
    Layout(content, Modifier.fillMaxWidth()) { measurables, constraints ->
        val gap = BUTTON_SPACING.toPx()
        val width = constraints.maxWidth
        val count = measurables.size
        val gaps = gap * (count - 1).coerceAtLeast(0)
        val widest = measurables.maxOfOrNull { it.maxIntrinsicWidth(Constraints.Infinity) } ?: 0
        if (!stacked && count > 0 && constraints.hasBoundedWidth && widest * count + gaps <= width) {
            val column = (width - gaps) / count
            val edges = (0..count).map { (it * (column + gap)).roundToInt() }
            val placeables = measurables.mapIndexed { index, measurable ->
                measurable.measure(Constraints.fixedWidth(((index + 1) * column + index * gap).roundToInt() - edges[index]))
            }
            layout(width, placeables.maxOf { it.height }) {
                placeables.forEachIndexed { index, placeable -> placeable.place(edges[index], 0) }
            }
        } else {
            val across = if (constraints.hasBoundedWidth) width else measurables.maxOfOrNull { it.maxIntrinsicWidth(Constraints.Infinity) } ?: 0
            val placeables = measurables.map { it.measure(Constraints.fixedWidth(across)) }
            val spacing = gap.roundToInt()
            val height = placeables.sumOf { it.height } + spacing * (count - 1).coerceAtLeast(0)
            layout(across, height) {
                var y = 0
                for (placeable in placeables) {
                    placeable.place(0, y)
                    y += placeable.height + spacing
                }
            }
        }
    }
}

/**
 * Where the directions go (`mapsURL`): Google Maps' directions to the point's name and address, or its search for the
 * point's name where the carrier gives no address. Unlike a `geo:` URI, either opens in a maps app or else in the
 * browser.
 */
internal val PickupPoint.directionsUrl: String
    get() = if (address == null) {
        "https://www.google.com/maps/search/?api=1&query=${Uri.encode(query)}"
    } else {
        "https://www.google.com/maps/dir/?api=1&destination=${Uri.encode(query)}"
    }

/**
 * `carrierSaysNote` (B-S04): what the carrier says the parcel waits on, quoted under "What {carrier} says", 16 in from
 * the sides and 14 from the top and bottom of a stroke in the secondary grey at 30 % with 16-point corners. The info
 * symbol 17 points in the secondary grey, 12 beside the words: the title in semibold footnote, 3 above the quote in
 * footnote secondary. TalkBack reads it as one.
 */
@Composable
internal fun CarrierSaysNote(words: String, carrier: String, modifier: Modifier = Modifier) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val secondary = colors.secondaryLabel
    Row(
        modifier
            .fillMaxWidth()
            .border(1.dp, secondary.copy(alpha = secondary.alpha * 0.3f), RoundedCornerShape(CARD_RADIUS))
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .semantics(mergeDescendants = true) {},
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        PeekSymbol("info.circle", font = type.fixed(17f), tint = secondary)
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(
                localizer.text("link.carrierSays", mapOf("carrier" to carrier)),
                color = colors.label,
                style = type.style(IosTextStyle.Footnote, FontWeight.SemiBold).sfLines(),
            )
            Text("“$words”", color = secondary, style = type.footnote.sfLines())
        }
    }
}

/**
 * The shipment's facts (`shipmentFact`), 10 apart: each its label in caption2 secondary, 3 above its value in footnote.
 * An empty list still takes its place among the page's parts, as the iPhone's empty stack does.
 */
@Composable
internal fun ShipmentFacts(facts: List<ShipmentFact>, modifier: Modifier = Modifier) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        for (fact in facts) {
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(localizer.text(fact.key), color = colors.secondaryLabel, style = type.caption2.sfLines())
                Text(fact.value, color = colors.label, style = type.footnote.sfLines())
            }
        }
    }
}

/**
 * What the page says of the parcel's carrier after its facts, in footnote:
 *
 * - Where no carrier is asked, or Amazon no longer gives the history (B-ST06): why, in secondary ("add.autoSync",
 *   "add.linkSync", "add.amazonAccount", `trackingHintKey`, or "add.amazonHistoryExpired"), and 10 under it, in
 *   `ink`, a way to change the carrier where the parcel is the reader's to change (`editable`), unless Amazon wants
 *   an account or no longer has the history.
 * - Otherwise why the last check failed (B-ST07), in the warning colour: that the number looks like one of the carrier
 *   it names, where the parcel's carrier refused it and one other claims it, or else
 *   `trackingFailureMessage`. Then, each a part of the page's own, in `ink` where the parcel is the reader's: a way to
 *   update the details its carrier asked for and still needs, the switch to the carrier that claims the number
 *   (semibold, `onSwitch`, waiting while a change is saved, `working`), and the carrier's change for a refused number.
 *
 * The carrier's changes open the editor (`onEditCarrier`), and the switch moves the parcel or opens it, TalkBack coming
 * back to the button that did.
 */
@Composable
internal fun TrackingNotes(
    parcel: Parcel,
    editable: Boolean,
    working: Boolean,
    ink: Color,
    onEditCarrier: (AccessibilityFocus) -> Unit,
    onSwitch: (CarrierId, AccessibilityFocus) -> Unit,
) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current ?: return
    val catalog = identities.catalog
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val active = parcel.activeTrackingCarrier
    val expired = parcel.amazonShippingHistoryExpired
    if (!catalog.tracksAutomatically(active) || expired) {
        Column(Modifier.testTag(PAGE_CARRIER_HINT), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                localizer.text(
                    if (expired) "add.amazonHistoryExpired" else catalog.trackingHintKey(active),
                    mapOf("carrier" to identities.displayName(active, language)),
                ),
                color = colors.secondaryLabel,
                style = type.footnote.sfLines(),
            )
            if (editable && !catalog.requiresAmazonAccount(active) && !expired) {
                NoteButton(localizer.text("detail.changeCarrier"), ink, action = onEditCarrier)
            }
        }
        return
    }
    val error = parcel.syncError ?: return
    // A number the parcel's carrier refuses, whose shape and check digit name one other carrier: the warning names it.
    val claiming = if (error == "carrier:refused_number") catalog.claimingCarrier(parcel.trackingNumber, besides = parcel.carrier) else null
    Text(
        claiming?.let { localizer.text("detail.refusedNumberLooksLike", mapOf("carrier" to identities.displayName(it, language))) }
            ?: localizer.trackingFailureMessage(error),
        Modifier.testTag(PAGE_SYNC_ERROR),
        color = colors.warning,
        style = type.footnote.sfLines(),
    )
    if (editable && error in INPUT_ERRORS && active == parcel.carrier && catalog.requirements(parcel.carrier, parcel.trackingNumber).isNotEmpty()) {
        NoteButton(localizer.text("detail.updateTrackingDetails"), ink, action = onEditCarrier)
    }
    if (editable && claiming != null) {
        NoteButton(
            localizer.text("detail.switchCarrier", mapOf("carrier" to identities.displayName(claiming, language))), ink,
            FontWeight.SemiBold, enabled = !working,
        ) { onSwitch(claiming, it) }
    }
    if (editable && error == "carrier:refused_number") NoteButton(localizer.text("detail.changeCarrier"), ink, action = onEditCarrier)
}

/**
 * A plain button of the page's notes: its words in footnote, in `tint`, as the iPhone's plain buttons dim while pressed.
 * Its `action` hears where TalkBack comes back to, the button itself.
 */
@Composable
private fun NoteButton(
    text: String,
    tint: Color,
    weight: FontWeight = FontWeight.Normal,
    enabled: Boolean = true,
    action: (AccessibilityFocus) -> Unit,
) {
    val focus = remember { AccessibilityFocus() }
    Text(
        text,
        Modifier
            .accessibilityFocus(focus)
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button) { action(focus) },
        color = tint,
        style = PeekTheme.type.style(IosTextStyle.Footnote, weight).sfLines(),
    )
}

/** The errors whose details the parcel's carrier asked for. */
private val INPUT_ERRORS = listOf("carrier:input_required", "carrier:invalid_input")

private val CARD_RADIUS = 16.dp
private val BUTTON_RADIUS = 12.dp
private val BUTTON_HEIGHT = 44.dp
private val BUTTON_SPACING = 8.dp
private val LABEL_SPACING = 6.dp
private const val COPIED_DURATION = 2_000L

/** The test tags of the page's notes, its pickup card and its buttons. */
const val PAGE_POSTCODE = "parcelPage.postcode"
const val PAGE_POSTCODE_MORE = "parcelPage.postcodeMore"
const val PAGE_PICKUP = "parcelPage.pickup"
const val PAGE_PICKUP_POINT = "parcelPage.pickupPoint"
const val PAGE_PICKUP_DIRECTIONS = "parcelPage.pickupDirections"
const val PAGE_PICKUP_COPY = "parcelPage.pickupCopy"
const val PAGE_CARRIER_SAYS = "parcelPage.carrierSays"
const val PAGE_FACTS = "parcelPage.facts"
const val PAGE_CARRIER_HINT = "parcelPage.carrierHint"
const val PAGE_SYNC_ERROR = "parcelPage.syncError"
