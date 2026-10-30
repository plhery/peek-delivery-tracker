package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.deliveryUpdatesPlace
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.rememberHaptics
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.util.UUID

// ParcelAlerts.swift's `DeliveryEmailOffer` and ParcelDetailView's `answerEmailOffer(_:)`: the delivery email offered on a
// delivered parcel's page.

/** Where the answer to the delivery email's offer stands (`DeliveryEmailOffer.Phase`). */
@Immutable
sealed interface EmailOfferPhase {
    data object Open : EmailOfferPhase

    /** The answer is being saved: [accepted] for "Turn on". */
    data class Saving(val accepted: Boolean) : EmailOfferPhase

    data object Failed : EmailOfferPhase

    /** The email was switched on: the card becomes one line saying so. */
    data object Accepted : EmailOfferPhase
}

/**
 * Where the delivery email is offered in the pass's column, under the card (`if sample == nil, let email,
 * emailOffer == .accepted || email.isOffered(on: parcel)`): offered once on a delivered parcel to an account that has
 * never chosen, and, once accepted, one line saying so until the page goes. It stays while its answer is saved, and,
 * declined, fades out in 0.1 s on an ease out while the rows under it move up in 0.2 s (`leaving`); it comes without
 * a fade, as the iPhone's column animates only as the answer changes. Accepted, it gives the success tap and TalkBack
 * hears the email is on; failed, TalkBack hears so. Under Reduce Motion it changes at once.
 */
@Composable
internal fun EmailOfferSlot(state: DeliveriesState, parcel: Parcel, pageId: UUID, modifier: Modifier = Modifier) {
    val deliveryEmail by state.source.deliveryEmail.collectAsState()
    val work = state.source.pageWork
    val phase = work.emailOffer(pageId)
    val email = deliveryEmail
    val shows = state.sample(pageId) == null && email != null &&
        (phase == EmailOfferPhase.Accepted || phase is EmailOfferPhase.Saving || email.isOffered(parcel))
    val localizer = LocalLocalizer.current
    val haptics = rememberHaptics()
    val news = work.emailOfferNews(pageId)
    LaunchedEffect(news) {
        when (news) {
            EmailOfferPhase.Accepted -> {
                haptics.success()
                state.announce(localizer.text("email.offer.on", mapOf("place" to localizer.deliveryUpdatesPlace)))
            }
            EmailOfferPhase.Failed -> state.announce(localizer.text("email.setting.failed"))
            else -> return@LaunchedEffect
        }
        work.heard(pageId)
    }
    // What the offer last showed, as it leaves.
    var address by remember { mutableStateOf(email?.address.orEmpty()) }
    var shownPhase by remember { mutableStateOf(phase) }
    if (shows && email != null) {
        address = email.address
        shownPhase = phase
    }
    val offer = @Composable {
        DeliveryEmailOffer(
            address, shownPhase, turnOn = { state.answerEmailOffer(pageId, true) }, notNow = { state.answerEmailOffer(pageId, false) },
            modifier,
        )
    }
    if (PeekTheme.reduceMotion) {
        if (shows) offer()
    } else {
        AnimatedVisibility(
            shows,
            enter = EnterTransition.None,
            exit = fadeOut(Motion.easeOut(LEAVING_FADE)) + shrinkVertically(Motion.easeInOut(OFFER_MOTION), shrinkTowards = Alignment.Top),
        ) { offer() }
    }
}

/**
 * `DeliveryEmailOffer`: the card while the answer is open, saved or failed, and once accepted the line saying the email
 * is on, which fades in as the card fades out in 0.1 s on an ease out and the column takes its height in 0.2 s on an
 * ease in and out. Under Reduce Motion it changes at once.
 */
@Composable
internal fun DeliveryEmailOffer(address: String, phase: EmailOfferPhase, turnOn: () -> Unit, notNow: () -> Unit, modifier: Modifier = Modifier) {
    if (PeekTheme.reduceMotion) {
        Box(modifier) { OfferPhase(address, phase, turnOn, notNow) }
        return
    }
    AnimatedContent(
        phase,
        modifier,
        transitionSpec = {
            fadeIn(Motion.easeInOut(OFFER_MOTION)) togetherWith fadeOut(Motion.easeOut(LEAVING_FADE)) using
                SizeTransform(clip = false) { _, _ -> Motion.easeInOut(OFFER_MOTION) }
        },
        contentAlignment = Alignment.TopStart,
        contentKey = { it == EmailOfferPhase.Accepted },
        label = "emailOffer",
    ) { OfferPhase(address, it, turnOn, notNow) }
}

@Composable
private fun OfferPhase(address: String, phase: EmailOfferPhase, turnOn: () -> Unit, notNow: () -> Unit) {
    if (phase == EmailOfferPhase.Accepted) EmailOnLine() else OfferCard(address, phase, turnOn, notNow)
}

/**
 * The offer accepted: the delivered green's checkmark.circle.fill, 8 before "email.offer.on" in footnote, 12 in on the
 * ink at 5 % with 12-point continuous corners.
 */
@Composable
private fun EmailOnLine() {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val style = PeekTheme.type.footnote
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(PAGE_EMAIL_OFFER_ON)
            .background(colors.ink.copy(alpha = colors.ink.alpha * 0.05f), ContinuousRoundedShape(12.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PeekSymbol("checkmark.circle.fill", Modifier.alignByBaseline(), font = style, tint = colors.resolve(ExperimentalPalette.delivered))
        Text(
            localizer.text("email.offer.on", mapOf("place" to localizer.deliveryUpdatesPlace)),
            Modifier.weight(1f).alignByBaseline(),
            color = colors.label,
            style = style.sfLines(),
        )
    }
}

/**
 * The offer's card, 16 in from a stroke of the ink at 12 % with 16-point corners: the envelope in ochre on its surface,
 * 40 square with 12-point corners (none at the accessibility sizes), 12 beside the title in callout semibold, a heading,
 * the body naming the address in footnote secondary, why the answer couldn't be saved in caption in the warning colour
 * where it couldn't, fading in and out, and the two answers 8 under them ([OfferButtons]), 4 apart. TalkBack goes through its parts before
 * it leaves it (`.accessibilityElement(children: .contain)`).
 */
@Composable
private fun OfferCard(address: String, phase: EmailOfferPhase, turnOn: () -> Unit, notNow: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val answering = phase is EmailOfferPhase.Saving
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(PAGE_EMAIL_OFFER)
            .border(1.dp, colors.ink.copy(alpha = colors.ink.alpha * 0.12f), RoundedCornerShape(16.dp))
            .padding(16.dp)
            .semantics { isTraversalGroup = true },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        if (!PeekTheme.dynamicTypeSize.isAccessibilitySize) {
            Box(
                Modifier.size(40.dp).background(colors.resolve(ExperimentalPalette.ochreSurface), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center,
            ) {
                PeekSymbol("envelope", font = type.fixed(17f), tint = colors.resolve(ExperimentalPalette.ochre))
            }
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                localizer.text("email.offer.title"),
                Modifier.semantics { heading() },
                color = colors.label,
                style = type.style(IosTextStyle.Callout, FontWeight.SemiBold).sfLines(),
            )
            Text(localizer.text("email.offer.body", mapOf("email" to address)), color = colors.secondaryLabel, style = type.footnote.sfLines())
            val failed = phase == EmailOfferPhase.Failed
            val failure = @Composable {
                Text(
                    localizer.text("email.setting.failed"),
                    Modifier.padding(top = 2.dp).testTag(PAGE_EMAIL_OFFER_ERROR),
                    color = colors.warning,
                    style = type.caption.sfLines(),
                )
            }
            // It comes and goes with the column's 0.2 s, fading.
            if (PeekTheme.reduceMotion) {
                if (failed) failure()
            } else {
                AnimatedVisibility(
                    failed,
                    enter = fadeIn(Motion.easeInOut(OFFER_MOTION)) + expandVertically(Motion.easeInOut(OFFER_MOTION), expandFrom = Alignment.Top),
                    exit = fadeOut(Motion.easeInOut(OFFER_MOTION)) + shrinkVertically(Motion.easeInOut(OFFER_MOTION), shrinkTowards = Alignment.Top),
                ) { failure() }
            }
            OfferButtons(Modifier.padding(top = 8.dp)) {
                OfferButton(
                    localizer.text("alerts.turnOn"), PAGE_EMAIL_OFFER_TURN_ON, saving = phase == EmailOfferPhase.Saving(true),
                    enabled = !answering, colors.onAccent, colors.onAccent, FontWeight.SemiBold, colors.accent, turnOn,
                )
                OfferButton(
                    localizer.text("onboarding.notifications.notNow"), PAGE_EMAIL_OFFER_NOT_NOW, saving = phase == EmailOfferPhase.Saving(false),
                    enabled = !answering, colors.secondaryLabel, colors.secondaryLabel, FontWeight.Medium, null, notNow,
                )
            }
        }
    }
}

/**
 * An answer of the offer: its words in subheadline, 14 in from its sides, 44 tall at least, on `ground` with
 * 12-point corners where it has one, giving under the finger. While its answer is saved, its words give their place to
 * a spinner in `spinner`, staying where they are so the button keeps its size (`answer(_:saving:tint:)`), the two
 * fading across in the column's 0.2 s on an ease in and out (at once under Reduce Motion), and both answers are
 * disabled meanwhile. TalkBack then hears the button in progress, not its words, as VoiceOver leaves out a view at
 * opacity 0.
 */
@Composable
private fun OfferButton(
    label: String,
    tag: String,
    saving: Boolean,
    enabled: Boolean,
    ink: Color,
    spinner: Color,
    weight: FontWeight,
    ground: Color?,
    action: () -> Unit,
) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        Modifier
            .testTag(tag)
            .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = enabled, role = Role.Button, onClick = action)
            .semantics { if (saving) progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate }
            .then(if (ground != null) Modifier.background(ground, shape) else Modifier)
            .heightIn(min = 44.dp)
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        val reduceMotion = PeekTheme.reduceMotion
        val words by animateFloatAsState(if (saving) 0f else 1f, if (reduceMotion) Motion.linear(0.0) else Motion.easeInOut(OFFER_MOTION), label = "answer")
        Text(
            label,
            Modifier.alpha(words).then(if (saving) Modifier.clearAndSetSemantics {} else Modifier),
            color = ink,
            style = PeekTheme.type.style(IosTextStyle.Subheadline, weight).sfLines(),
        )
        // The button says it is in progress, as the iPhone's reads its `ProgressView`, rather than the spinner apart.
        val progress = @Composable { ActivityIndicator(Modifier.clearAndSetSemantics {}, size = REGULAR_SPINNER, tint = spinner) }
        if (reduceMotion) {
            if (saving) Box(Modifier.wrapContentSize(unbounded = true)) { progress() }
        } else {
            AnimatedVisibility(
                saving,
                Modifier.wrapContentSize(unbounded = true),
                enter = fadeIn(Motion.easeInOut(OFFER_MOTION)),
                exit = fadeOut(Motion.easeInOut(OFFER_MOTION)),
            ) { progress() }
        }
    }
}

/**
 * `ViewThatFits(in: .horizontal) { HStack(spacing: 8); VStack(alignment: .leading, spacing: 4) }`: the answers side
 * by side, 8 apart and each as wide as its words, where both fit on the line; otherwise one under the other, 4 apart,
 * at the leading edge.
 */
@Composable
private fun OfferButtons(modifier: Modifier, content: @Composable () -> Unit) {
    Layout(content, modifier.fillMaxWidth()) { measurables, constraints ->
        val ideal = measurables.map { it.maxIntrinsicWidth(Constraints.Infinity) }
        val row = ROW_SPACING.roundToPx()
        val fits = !constraints.hasBoundedWidth || ideal.sum() + row * (measurables.size - 1).coerceAtLeast(0) <= constraints.maxWidth
        val width = if (constraints.hasBoundedWidth) constraints.maxWidth else ideal.sum()
        val placeables = measurables.mapIndexed { index, measurable ->
            measurable.measure(Constraints(maxWidth = if (fits) ideal[index] else minOf(ideal[index], width)))
        }
        if (fits) {
            val height = placeables.maxOfOrNull { it.height } ?: 0
            layout(width, height) {
                var x = 0
                for (placeable in placeables) {
                    placeable.place(x, (height - placeable.height) / 2)
                    x += placeable.width + row
                }
            }
        } else {
            val column = COLUMN_SPACING.roundToPx()
            layout(width, placeables.sumOf { it.height } + column * (placeables.size - 1).coerceAtLeast(0)) {
                var y = 0
                for (placeable in placeables) {
                    placeable.place(0, y)
                    y += placeable.height + column
                }
            }
        }
    }
}

private val ROW_SPACING = 8.dp
private val COLUMN_SPACING = 4.dp

/** The column's `.easeInOut(duration: 0.2)` as the offer's answer changes. */
private const val OFFER_MOTION = 0.2

/** `leaving`'s removal: `.opacity.animation(.easeOut(duration: 0.1))`. */
private const val LEAVING_FADE = 0.1

const val PAGE_EMAIL_OFFER = "parcelPage.emailOffer"
const val PAGE_EMAIL_OFFER_ON = "parcelPage.emailOffer.on"
const val PAGE_EMAIL_OFFER_ERROR = "parcelPage.emailOffer.error"
const val PAGE_EMAIL_OFFER_TURN_ON = "parcelPage.emailOffer.turnOn"
const val PAGE_EMAIL_OFFER_NOT_NOW = "parcelPage.emailOffer.notNow"
