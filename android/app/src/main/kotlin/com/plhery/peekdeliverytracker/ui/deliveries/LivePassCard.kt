package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
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
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NonTrackingShape
import com.plhery.peekdeliverytracker.core.ParcelAttention
import com.plhery.peekdeliverytracker.core.Rect
import com.plhery.peekdeliverytracker.core.WorldAtlas
import com.plhery.peekdeliverytracker.core.activeTrackingCarrier
import com.plhery.peekdeliverytracker.core.allAlertsMuted
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.attention
import com.plhery.peekdeliverytracker.core.awaitingHandover
import com.plhery.peekdeliverytracker.core.currentEvent
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.core.deliveringCarrier
import com.plhery.peekdeliverytracker.core.displayedCarrier
import com.plhery.peekdeliverytracker.core.hasMappedPlace
import com.plhery.peekdeliverytracker.core.inputNeededPrompt
import com.plhery.peekdeliverytracker.core.inputSuggestedPrompt
import com.plhery.peekdeliverytracker.core.isArchived
import com.plhery.peekdeliverytracker.core.likelyCarrierPrompt
import com.plhery.peekdeliverytracker.core.nonTrackingShape
import com.plhery.peekdeliverytracker.core.refusedPostcode
import com.plhery.peekdeliverytracker.core.showsMoreWithPostcode
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.draw.CarrierFleetMark
import com.plhery.peekdeliverytracker.ui.draw.DeliveryPostageStamp
import com.plhery.peekdeliverytracker.ui.draw.UnevenContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.map.RouteEngraving
import com.plhery.peekdeliverytracker.ui.map.RouteEngravingMetrics
import com.plhery.peekdeliverytracker.ui.map.rememberParcelRoute
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.rememberHaptics
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.shownCarrierIdentity
import java.time.Instant
import kotlin.math.max

/**
 * The page's bell, where the parcel has one: it opens the parcel's alerts while the account's email is on
 * (`opensAlerts`, `bellOpensAlerts`, `open`, TalkBack coming back to the bell), and otherwise switches the parcel's
 * notifications (`mute`), then calls `saved`.
 */
@Stable
internal class PassBell(
    val opensAlerts: Boolean,
    val open: (AccessibilityFocus) -> Unit = {},
    val mute: (muted: Boolean, saved: () -> Unit) -> Unit,
)

/**
 * The card of `liveParcelPass` (B-S02), in the carrier's colours with 18-point corners and 18 in, its parts 16 apart:
 *
 * - The carrier the card shows, a probable one named as such (B-S72), and under it the carrier that delivers where
 *   another does, which a tap offers to change (B-G03) on a parcel that is the reader's to change. At the trailing end,
 *   44 square each, the globe that opens the map (B-G02, where scans have places, once the route is drawn), the share
 *   button and the bell (B-S03). At the accessibility sizes the marks take the line, and the buttons go under them.
 * - The route engraved behind the card's top, 176 tall, and 40 more under a second mark, once what it keeps clear of
 *   is laid out; at the accessibility sizes it goes as much lower as the buttons' line adds. It fades in over 0.5 s
 *   once drawn (B-AN03, B-AN04), at once under Reduce Motion, and a tap on it opens the map. Decorative: the globe is
 *   TalkBack's way in.
 * - The carrier the app swapped from, and what the parcel's carrier needs (B-S73, B-S74) where it is the reader's to
 *   change. The name, which a tap renames (B-G01), beside the stamp; the sender and who delivers; the postcode the
 *   carrier refused or couldn't check (B-ST09), and that the carrier shows more with one; the status and its date;
 *   that the sender still has it; what the carrier waits on; that the scans are made up (B-ST11); the journey's steps.
 *
 * `atlas` is null until it is read, `sample` is the made-up parcel nothing on can change or share, `readerLink`
 * someone else's parcel followed through its link, and `working` a change being saved, which the bell waits for.
 * `onMap` opens the map, TalkBack going back to the given element once it closes, `onEditCarrier` the carrier
 * editor, on the carrier a prompt names, TalkBack going back to the mark or the button that opened it, and `onRename`
 * the rename alert, TalkBack going back to the name.
 */
@Composable
internal fun LivePassCard(
    parcel: Parcel,
    atlas: WorldAtlas?,
    sample: Boolean,
    readerLink: Boolean,
    bell: PassBell?,
    working: Boolean,
    onMap: (AccessibilityFocus?) -> Unit,
    onEditCarrier: (initialCarrier: CarrierId?, focus: AccessibilityFocus?) -> Unit,
    onRename: (AccessibilityFocus?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current ?: return
    val branding = shownCarrierIdentity(parcel, localizer, language) ?: return
    val now = LocalNow.current()
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val reduceMotion = PeekTheme.reduceMotion
    val density = LocalDensity.current
    val ink = colors.resolve(branding.ink)
    val secondary = colors.secondaryLabel
    // Handed from one carrier to another, or about to be, the card carries both marks and says who delivers.
    val delivering = parcel.deliveringCarrier
    val delivery = remember(identities, delivering, language) { delivering?.let { identities.of(it, language) } }
    val deliveryLine = delivering?.let {
        localizer.text("parcel.deliveryCarrier", mapOf("carrier" to identities.displayName(it, language)))
    }
    val placed = parcel.hasMappedPlace
    val route = if (placed && atlas != null) rememberParcelRoute(parcel, atlas, language) else null
    // Someone else's parcel, followed through its link: the carrier is theirs to change.
    val editable = !sample && !readerLink
    val frames = remember { PassFrames() }
    var headerHeight by remember { mutableFloatStateOf(0f) }
    val metrics = PassRouteMetrics.of(handover = delivery != null, largeText = large, headerHeight = headerHeight.toDouble())
    val globeFocus = remember { AccessibilityFocus() }
    val nameFocus = remember { AccessibilityFocus() }
    val marksFocus = remember { AccessibilityFocus() }
    val neededFocus = remember { AccessibilityFocus() }
    val open by rememberUpdatedState { onMap(globeFocus) }

    // A map that keeps clear of the second mark and the buttons waits to know where they stand, rather than moving
    // once it does.
    val buttons = frames.buttons
    val mark = frames.deliveryMark
    val engraved = route?.takeIf { buttons != null && (delivery == null || mark != null) && (!large || headerHeight > 0f) }

    Box(
        modifier
            .fillMaxWidth()
            .background(colors.resolve(branding.surface), RoundedCornerShape(CARD_RADIUS))
            .onGloballyPositioned {
                frames.card = it
                frames.measure(density)
            }
            .testTag(PAGE_PASS),
    ) {
        if (engraved != null && atlas != null) {
            Box(
                Modifier
                    .matchParentSize()
                    .wrapContentHeight(Alignment.Top, unbounded = true)
                    .fillMaxWidth()
                    .height(metrics.height.dp)
                    .clip(UnevenContinuousRoundedShape(topLeading = CARD_RADIUS, topTrailing = CARD_RADIUS))
                    .pointerInput(Unit) { detectTapGestures { open() } }
                    .testTag(PAGE_ROUTE),
            ) {
                // The title is written over the bottom of the map, so Pip stays above it.
                RouteEngraving(
                    atlas = atlas, route = engraved, stage = parcel.currentStage, identity = branding, language = language,
                    modifier = Modifier.fillMaxSize(), floor = metrics.floor, covered = if (delivery == null) null else mark,
                    buttons = buttons, room = metrics.routeRoom, entrance = if (reduceMotion) null else Motion.easeOut(0.5),
                )
            }
        }
        Column(Modifier.fillMaxWidth().padding(CARD_PADDING), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            val tint = ink.copy(alpha = ink.alpha * 0.75f)
            val marks = @Composable {
                val label = listOfNotNull(localizer.text("detail.changeCarrierFrom", mapOf("carrier" to branding.fullName)), deliveryLine)
                    .joinToString(". ")
                val change = { onEditCarrier(null, marksFocus) }
                PassMarks(
                    branding, delivery, frames, change.takeIf { editable },
                    Modifier
                        .testTag(PAGE_MARKS)
                        .then(
                            if (!editable) {
                                Modifier
                            } else {
                                Modifier.accessibilityFocus(marksFocus).clearAndSetSemantics {
                                    contentDescription = label
                                    role = Role.Button
                                    onClick {
                                        change()
                                        true
                                    }
                                }
                            },
                        ),
                )
            }
            val buttonRow = @Composable {
                Row(
                    Modifier.onGloballyPositioned {
                        frames.buttonsNode = it
                        frames.measure(density)
                    },
                ) {
                    if (placed) {
                        HeaderButton(localizer.text("map.open"), PAGE_GLOBE, enabled = route != null, Modifier.accessibilityFocus(globeFocus), open) {
                            PeekSymbol("globe", font = type.fixed(17f), tint = tint)
                        }
                    }
                    if (readerLink || !sample) {
                        // The share sheet, and the reader link's own share, come with their step.
                        HeaderButton(localizer.text("link.shareAria"), PAGE_SHARE, enabled = true, Modifier, {}) {
                            PeekSymbol("square.and.arrow.up", font = type.fixed(16f), tint = tint)
                        }
                    }
                    if (bell != null) BellButton(parcel, bell, working, tint, localizer)
                }
            }
            // The buttons sit a tap apart, which leaves a long carrier name its line. At the largest text sizes the
            // name takes the whole line, and the buttons go under it.
            val measured = Modifier.fillMaxWidth().onSizeChanged { headerHeight = it.height / density.density }
            if (large) {
                Column(measured, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    marks()
                    buttonRow()
                }
            } else {
                Row(measured, verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.weight(1f), contentAlignment = Alignment.CenterStart) { marks() }
                    Spacer(Modifier.width(8.dp))
                    buttonRow()
                }
            }
            // Room for the route engraved behind this part of the card.
            if (placed) Spacer(Modifier.height(metrics.spacer.dp))
            AutomaticCarrierNotice(parcel)
            val needed = parcel.carrierData?.routing?.providerInputNeeded
            if (editable && needed != null && !parcel.isArchived) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        localizer.text("detail.providerInputNeeded", mapOf("provider" to needed.provider)),
                        color = colors.label,
                        style = type.footnote.sfLines(),
                    )
                    Text(
                        localizer.text("detail.inputNeededAction"),
                        Modifier
                            .accessibilityFocus(neededFocus)
                            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button) { onEditCarrier(null, neededFocus) },
                        color = colors.accent,
                        style = type.footnote.sfLines(),
                    )
                }
            }
            if (editable) {
                carrierPrompt(parcel, now, localizer, identities, language)?.let { prompt ->
                    CarrierPrompt(prompt, ink) { onEditCarrier(prompt.carrier, it) }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(18.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.weight(1f)) {
                    // The name is tapped to change it, as the ••• menu's item does.
                    Text(
                        parcel.label.ifEmpty { localizer.text("common.parcel") },
                        Modifier
                            .testTag(PAGE_NAME)
                            .then(
                                if (sample) {
                                    Modifier
                                } else {
                                    Modifier.accessibilityFocus(nameFocus).clickable(
                                        interactionSource = null, indication = PlainPress, role = Role.Button,
                                        onClickLabel = localizer.text("detail.editTitle"),
                                    ) { onRename(nameFocus) }
                                },
                            ),
                        color = colors.label,
                        style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(),
                    )
                }
                DeliveryPostageStamp(parcel, branding, appeared = true, width = 50.dp)
            }
            val sender = parcel.carrierData?.senderName?.ifEmpty { null }
            if (sender != null || deliveryLine != null) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    if (sender != null) {
                        Text(localizer.text("parcel.sender", mapOf("sender" to sender)), color = secondary, style = type.caption.sfLines())
                    }
                    if (deliveryLine != null) Text(deliveryLine, color = secondary, style = type.caption.sfLines())
                }
            }
            val active = identities.displayName(parcel.activeTrackingCarrier, language)
            // The postcode the parcel's carrier refused, or the DPD postcode it couldn't check.
            val postcode = parcel.refusedPostcode
                ?: if (parcel.carrierData?.dpdPostcodeVerified == false) parcel.dpdPostcode?.ifEmpty { null } else null
            if (postcode != null) {
                PostcodeNote(
                    localizer.text(
                        if (parcel.refusedPostcode == null) "detail.postcodeNotVerified" else "detail.postcodeRefused",
                        mapOf("carrier" to active, "postcode" to postcode),
                    ),
                    localizer.text("detail.editPostcode").takeIf { parcel.archivedAt == null && editable },
                    Modifier.testTag(PAGE_POSTCODE),
                ) { onEditCarrier(null, it) }
            }
            if (editable && parcel.showsMoreWithPostcode(identities.catalog)) {
                PostcodeNote(
                    localizer.text("detail.postcodeShowsMore", mapOf("carrier" to identities.displayName(parcel.carrier, language))),
                    localizer.text("detail.inputNeededAction"),
                    Modifier.testTag(PAGE_POSTCODE_MORE),
                ) { onEditCarrier(null, it) }
            }
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(localizer.parcelStatus(parcel), color = ink, style = type.subheadline.sfLines())
                val date = localizer.parcelCompletionDate(parcel, now) ?: localizer.parcelDeliveryEstimate(parcel, now)
                if (date != null) Text(date, color = ink, style = type.caption.sfLines())
            }
            val displayed = identities.displayName(parcel.displayedCarrier, language)
            // A label a day old or more: the sender still has the parcel, and the carrier can tell nothing until then.
            if (!parcel.isArchived && parcel.awaitingHandover(now)) {
                Text(
                    localizer.text("parcel.awaitingHandover", mapOf("carrier" to displayed)),
                    color = secondary,
                    style = type.caption.sfLines(),
                )
            }
            carrierWaits(parcel)?.let { waits ->
                val key = waits.recipientAction?.let { ParcelAttention(it).localizationKey }
                    ?: if (waits.stage == TrackingStage.FailedAttempt) "attention.failed_attempt" else "attention.exception"
                ParcelFlag(localizer.text(key), "exclamationmark.triangle")
            }
            if (parcel.attention(now) == ParcelAttention.Generated) {
                ParcelFlag(localizer.text("attention.generated"), "exclamationmark.triangle")
                Text(localizer.text("link.generated", mapOf("carrier" to displayed)), color = secondary, style = type.caption.sfLines())
            }
            JourneyRail(parcel.currentStage, ink.copy(alpha = ink.alpha * 0.45f), compact = true)
        }
    }
}

/**
 * The card's carrier mark and, where another carrier delivers, that carrier's under it, 7 apart: the first stays where
 * every card has it, and the second hangs below it at its own width, taking no room of the card's (the route below
 * starts lower instead). A tap on either `change`s the carrier, both dimming while either is pressed, as the iPhone's
 * button holds both; without `change` they take a tap all the same, rather than pass it to the map behind them.
 */
@Composable
private fun PassMarks(
    branding: CarrierVisualIdentity,
    delivery: CarrierVisualIdentity?,
    frames: PassFrames,
    change: (() -> Unit)?,
    modifier: Modifier,
) {
    val density = LocalDensity.current
    val press = remember { MutableInteractionSource() }
    val pressed = if (change != null) Modifier.indication(press, PlainPress) else Modifier

    // The second mark hangs past the first's bounds, where the marks' taps don't reach, so it takes its own: one more
    // way to the same button, which TalkBack and the keyboard reach once.
    fun taps(focusable: Boolean): Modifier = if (change != null) {
        Modifier.focusProperties { canFocus = focusable }.clickable(interactionSource = press, indication = null, onClick = change)
    } else {
        Modifier.pointerInput(Unit) { detectTapGestures { } }
    }
    Layout(
        content = {
            CarrierFleetMark(branding, pressed)
            if (delivery != null) {
                CarrierFleetMark(
                    delivery,
                    Modifier
                        .then(pressed)
                        .then(taps(false))
                        .onGloballyPositioned {
                            frames.markNode = it
                            frames.measure(density)
                        },
                )
                DisposableEffect(Unit) {
                    onDispose {
                        frames.markNode = null
                        frames.deliveryMark = null
                    }
                }
            }
        },
        modifier = modifier.then(taps(true)),
    ) { measurables, constraints ->
        val first = measurables[0].measure(constraints.copy(minWidth = 0, minHeight = 0))
        val second = measurables.getOrNull(1)?.measure(Constraints())
        layout(first.width, first.height) {
            first.place(0, 0)
            second?.place(0, first.height + MARKS_SPACING.roundToPx())
        }
    }
}

/** A button of the card's top row, 44 square, as the iPhone's plain buttons dim while pressed. */
@Composable
private fun HeaderButton(
    label: String,
    tag: String,
    enabled: Boolean,
    modifier: Modifier,
    action: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        modifier
            .size(HEADER_BUTTON)
            .testTag(tag)
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = action)
            .semantics { contentDescription = label },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

/**
 * The bell (B-S03, B-W01): it switches the parcel's notifications off or back on, then rings and gives the selection
 * tick (B-H01), and waits while a change is saved. While the account's email is on it opens the parcel's alerts
 * instead, struck out while every alert of the parcel is.
 */
@Composable
private fun BellButton(parcel: Parcel, bell: PassBell, working: Boolean, tint: Color, localizer: Localizer) {
    var rings by remember { mutableIntStateOf(0) }
    val haptics = rememberHaptics()
    val focus = remember { AccessibilityFocus() }
    val label = localizer.text(
        if (bell.opensAlerts) "email.parcel.open" else if (parcel.notificationsMuted) "detail.unmute" else "detail.mute",
    )
    HeaderButton(label, PAGE_BELL, enabled = !working, Modifier.accessibilityFocus(focus), {
        if (bell.opensAlerts) {
            bell.open(focus)
            return@HeaderButton
        }
        bell.mute(!parcel.notificationsMuted) {
            rings++
            haptics.selection()
        }
    }) {
        ParcelNotificationBell(if (bell.opensAlerts) parcel.allAlertsMuted else parcel.notificationsMuted, rings, tint)
    }
}

/**
 * What the page asks of the parcel's carrier, the words of the button that answers it, if it has one, and the carrier
 * the editor that button opens starts on, where it names one.
 */
internal class CarrierPromptCopy(val text: String, val action: String?, val carrier: CarrierId? = null)

/**
 * The first of what the page asks of the parcel's carrier: the delivery postcode its carrier, or the one its number
 * points to, needs (`inputNeededPrompt`), or would like (`inputSuggestedPrompt`); then the carrier the number probably
 * travels with (B-S73), with a way to choose another once it has waited a week; then that the number looks like a
 * shop's order number or is too short (B-S74). Null when it asks nothing.
 */
internal fun carrierPrompt(
    parcel: Parcel,
    now: Instant,
    localizer: Localizer,
    identities: CarrierIdentities,
    language: AppLanguage,
): CarrierPromptCopy? {
    fun named(key: String, carrier: CarrierId) =
        localizer.text(key, mapOf("carrier" to identities.displayName(carrier, language)))
    val action = localizer.text("detail.inputNeededAction")
    parcel.inputNeededPrompt?.let {
        val key = if (it.carrier == parcel.carrier) "detail.carrierInputNeeded" else "detail.inputNeeded"
        return CarrierPromptCopy(named(key, it.carrier), action, it.carrier)
    }
    parcel.inputSuggestedPrompt?.let { return CarrierPromptCopy(named("detail.inputSuggested", it.carrier), action, it.carrier) }
    parcel.likelyCarrierPrompt(now)?.let {
        val text = named(if (it.waited) "link.likely.week" else "link.likely.body", it.carrier)
        return CarrierPromptCopy(text, if (it.waited) localizer.text("link.likely.choose") else null)
    }
    val shape = parcel.nonTrackingShape ?: return null
    val text = when (shape) {
        is NonTrackingShape.Order -> localizer.text("link.notTracking.order", mapOf("shop" to (NonTrackingShape.shopNames[shape.shop] ?: shape.shop)))
        NonTrackingShape.Short -> localizer.text("link.notTracking.short")
    }
    return CarrierPromptCopy(text, localizer.text("link.chooseCarrier"))
}

/**
 * What the page asks of the parcel's carrier in footnote, in the card's ink, and the button that answers it, semibold
 * and 44 tall, which gives under the finger and opens the carrier editor (`action`), TalkBack coming back to it.
 */
@Composable
private fun CarrierPrompt(prompt: CarrierPromptCopy, tint: Color, action: (AccessibilityFocus) -> Unit) {
    val type = PeekTheme.type
    val focus = remember { AccessibilityFocus() }
    Column(Modifier.testTag(PAGE_PROMPT).semantics { isTraversalGroup = true }, verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(prompt.text, color = tint, style = type.footnote.sfLines())
        if (prompt.action != null) {
            Box(
                Modifier
                    .heightIn(min = HEADER_BUTTON)
                    .accessibilityFocus(focus)
                    .clickable(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button) { action(focus) },
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(prompt.action, color = tint, style = type.style(IosTextStyle.Footnote, FontWeight.SemiBold).sfLines())
            }
        }
    }
}

/**
 * What a postcode note says, in caption secondary, and under it, 4 apart, the button that opens the carrier editor
 * (`action`) in caption semibold, as the iPhone's plain buttons dim while pressed, where it has one, TalkBack coming
 * back to it.
 */
@Composable
private fun PostcodeNote(text: String, button: String?, modifier: Modifier, action: (AccessibilityFocus) -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val focus = remember { AccessibilityFocus() }
    Column(modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text, color = colors.secondaryLabel, style = type.caption.sfLines())
        if (button != null) {
            Text(
                button,
                Modifier
                    .accessibilityFocus(focus)
                    .clickable(interactionSource = null, indication = PlainPress, role = Role.Button) { action(focus) },
                color = colors.accent,
                style = type.style(IosTextStyle.Caption, FontWeight.SemiBold).sfLines(),
            )
        }
    }
}

/** The scan after which the carrier waits on the reader ([waitsOnReader]), while the parcel isn't archived. */
internal fun carrierWaits(parcel: Parcel): TrackingEvent? =
    parcel.currentEvent?.takeIf { !parcel.isArchived && it.waitsOnReader() }

/**
 * Where the card's buttons and the delivering carrier's mark stand on it, in points from its top leading corner, which
 * its map keeps clear of (`buttonRow`, `deliveryMark`): null until laid out.
 */
@Stable
private class PassFrames {
    var card: LayoutCoordinates? = null
    var buttonsNode: LayoutCoordinates? = null
    var markNode: LayoutCoordinates? = null
    var buttons: Rect? by mutableStateOf(null)
    var deliveryMark: Rect? by mutableStateOf(null)

    fun measure(density: Density) {
        val card = card?.takeIf { it.isAttached } ?: return
        buttons = buttonsNode?.takeIf { it.isAttached }?.let { frame(card, it, density) }
        deliveryMark = markNode?.takeIf { it.isAttached }?.let { frame(card, it, density) }
    }

    private fun frame(card: LayoutCoordinates, node: LayoutCoordinates, density: Density): Rect {
        val box = card.localBoundingBoxOf(node, clipBounds = false)
        val scale = density.density.toDouble()
        return Rect(box.left / scale, box.top / scale, box.width / scale, box.height / scale)
    }
}

/**
 * The heights of the route behind the card's top, in points. A second carrier's mark adds `room`
 * ([RouteEngravingMetrics.HANDOVER_ROOM]) and, at the accessibility sizes, the buttons' line under the marks pushes the
 * map down by as much as it adds to the 44 of the top row (`lower`): the map is that much taller, Pip's floor that much
 * lower, the space left for the map under the top row grows by `room`, and the route starts `room + lower` lower.
 */
internal data class PassRouteMetrics(val room: Double, val lower: Double) {
    val height: Double get() = 176 + room + lower
    val floor: Double get() = 160 + room + lower
    val spacer: Double get() = 68 + room
    val routeRoom: Double get() = room + lower

    companion object {
        fun of(handover: Boolean, largeText: Boolean, headerHeight: Double): PassRouteMetrics = PassRouteMetrics(
            room = if (handover) RouteEngravingMetrics.HANDOVER_ROOM else 0.0,
            lower = if (largeText) max(0.0, headerHeight - 44) else 0.0,
        )
    }
}

private val CARD_RADIUS = 18.dp
private val CARD_PADDING = 18.dp
private val HEADER_BUTTON = 44.dp
private val MARKS_SPACING = 7.dp
