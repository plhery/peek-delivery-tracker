package com.plhery.peekdeliverytracker.ui.friends

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collapse
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.expand
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.FriendInvitationLink
import com.plhery.peekdeliverytracker.core.FriendsStore
import com.plhery.peekdeliverytracker.core.PipGeometry
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendProfile
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesSource
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.UnwrappingParcel
import com.plhery.peekdeliverytracker.ui.deliveries.firstCharacter
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.deliveries.plainDisabled
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.DieCutStampShape
import com.plhery.peekdeliverytracker.ui.draw.Toggle
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.KeepMotion
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.io.Serializable
import java.util.UUID
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

// FriendsView.swift's parts: `FriendAvatarView`, `FriendCardView`, `FriendSharingPreviewView`, `FriendsProfileForm`,
// `FriendsInvitationView`, `FriendsInvitationParcel` and `FriendsPostagePair`.

/** `FriendCard.tone`: a friend keeps one colour, on their card and on their page, by their id's first byte. */
internal fun FriendCard.tone(): Pair<AdaptiveColor, AdaptiveColor> = friendTone(id)

/** The colour of the id's first byte, of four: transit, lilac, pickup and delivered, each with its surface. */
internal fun friendTone(id: UUID): Pair<AdaptiveColor, AdaptiveColor> {
    val index = ((id.mostSignificantBits ushr 56).toInt() and 0xFF) % 4
    return TONES[index] to SURFACES[index]
}

private val TONES = listOf(ExperimentalPalette.transit, ExperimentalPalette.lilac, ExperimentalPalette.pickup, ExperimentalPalette.delivered)
private val SURFACES = listOf(
    ExperimentalPalette.transitSurface, ExperimentalPalette.lilacSurface, ExperimentalPalette.pickupSurface, ExperimentalPalette.deliveredSurface,
)

/**
 * `FriendAvatarView`: the name's first character in medium, 47 % of the width, in [tint] on a die-cut stamp of
 * [surface] mixed with a fifth of the tint, a frame of the tint at 34 % inset 5, the whole turned -4°. TalkBack doesn't
 * read it.
 */
@Composable
internal fun FriendAvatar(name: String, modifier: Modifier, tint: AdaptiveColor = ExperimentalPalette.lilac, surface: AdaptiveColor = ExperimentalPalette.lilacSurface) {
    val colors = PeekTheme.colors
    val ink = colors.resolve(tint)
    val ground = colors.resolve(surface.mix(tint, 0.19))
    BoxWithConstraints(modifier.clearAndSetSemantics {}) {
        val width = maxWidth
        Box(
            Modifier
                .fillMaxSize()
                .rotate(-4f)
                .background(ground, DieCutStampShape())
                .drawWithContent {
                    drawContent()
                    val inset = 5.dp.toPx()
                    val line = 0.7.dp.toPx()
                    drawRect(
                        ink.copy(alpha = 0.34f), topLeft = Offset(inset, inset),
                        size = Size(size.width - 2 * inset, size.height - 2 * inset), style = Stroke(line),
                    )
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(name.firstCharacter(), color = ink, style = PeekTheme.type.fixed(width.value * 0.47f, FontWeight.Medium), maxLines = 1)
        }
    }
}

/**
 * `FriendsPostagePair`: two stamps overlapping by 8, "A" in transit turned -9° and "M" in lilac turned 12° and 12
 * lower, 70 × 85 each, in by 10. TalkBack doesn't read it.
 */
@Composable
internal fun FriendsPostagePair(modifier: Modifier = Modifier) {
    Row(modifier.padding(10.dp).clearAndSetSemantics {}, horizontalArrangement = Arrangement.spacedBy((-8).dp)) {
        FriendAvatar("A", Modifier.size(70.dp, 85.dp).rotate(-9f), ExperimentalPalette.transit, ExperimentalPalette.transitSurface)
        FriendAvatar("M", Modifier.offset(y = 12.dp).size(70.dp, 85.dp).rotate(12f))
    }
}

/**
 * `FriendCardView`: the friend's stamp 39 × 48 and, 12 beside it, the name in title3 semibold, the stamps they earned or
 * "friends.privateStats" in caption2 in their colour, and this week's arrival behind a dot of 4 where they share it; in
 * by 16 and 14 on their surface with 18-point corners, 80 tall at least.
 */
@Composable
internal fun FriendCardView(friend: FriendCard) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val (tone, surface) = friend.tone()
    val tint = colors.resolve(tone)
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp)
            .background(colors.resolve(surface), ContinuousRoundedShape(18.dp))
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FriendAvatar(friend.nickname, Modifier.size(39.dp, 48.dp), tone, surface)
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(friend.nickname, color = colors.ink, style = type.style(IosTextStyle.Title3, FontWeight.SemiBold).sfLines())
            val stats = friend.stats
            Text(
                if (stats != null) localizer.text("friends.stampCount", mapOf("count" to stats.stamps.size)) else localizer.text("friends.privateStats"),
                color = tint, style = type.caption2.sfLines(),
            )
            if (friend.arrivedThisWeek == true) ArrivalLine(tint, type.caption2, Modifier.padding(top = 1.dp))
        }
    }
}

/**
 * `FriendPostcardView`: the name in a fixed semibold 28 drawn 1 tighter, whatever the text size, and under it, 12 apart,
 * this week's arrival behind its dot in caption where they share it; the stamp 61 × 74 at the trailing side, 16 clear;
 * in by 22 on the surface with 21-point corners, 135 tall at least. In the friend's colours, lilac by default.
 */
@Composable
internal fun FriendPostcard(
    nickname: String,
    arrivedThisWeek: Boolean?,
    modifier: Modifier = Modifier,
    tint: AdaptiveColor = ExperimentalPalette.lilac,
    surface: AdaptiveColor = ExperimentalPalette.lilacSurface,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val density = LocalDensity.current
    Row(
        modifier
            .fillMaxWidth()
            .heightIn(min = 135.dp)
            .background(colors.resolve(surface), ContinuousRoundedShape(21.dp))
            .padding(22.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            // `.tracking(-1)`: a point less between the letters, in the text's own unit.
            val tracking = with(density) { (-1).dp.toSp() }
            Text(nickname, color = colors.label, style = type.fixed(28f, FontWeight.SemiBold).copy(letterSpacing = tracking).sfLines())
            if (arrivedThisWeek == true) ArrivalLine(colors.resolve(tint), type.caption)
        }
        FriendAvatar(nickname, Modifier.size(61.dp, 74.dp), tint, surface)
    }
}

/** "friends.arrived" behind a dot of 4 in [tint], 5 apart. */
@Composable
private fun ArrivalLine(tint: Color, style: TextStyle, modifier: Modifier = Modifier) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(4.dp).background(tint, CircleShape))
        Text(localized("friends.arrived"), color = tint, style = style.sfLines())
    }
}

/**
 * `FriendSharingPreviewView`: what friends see of the reader, in transit on its surface with 17-point corners, in by
 * 16: the name in headline over the stamp 33 × 41, what is shared in caption2 (36 tall at least) and this week's
 * arrival (18 tall at least), kept in its place but unseen and unread where it isn't shared.
 */
@Composable
internal fun FriendSharingPreview(friend: FriendCard) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val transit = colors.resolve(ExperimentalPalette.transit)
    val stats = friend.stats
    val summary = if (stats == null) {
        localizer.text("friends.privateStats")
    } else {
        val days = stats.averageDays?.let { localizer.text(if (it == 1) "friends.day" else "friends.days", mapOf("count" to it)) } ?: "—"
        "${stats.deliveredCount} ${localizer.text("passport.delivered", mapOf("count" to stats.deliveredCount))} · $days · " +
            localizer.text("friends.stampCount", mapOf("count" to stats.stamps.size))
    }
    Column(
        Modifier
            .fillMaxWidth()
            .testTag(FRIENDS_PREVIEW)
            .background(colors.resolve(ExperimentalPalette.transitSurface), ContinuousRoundedShape(17.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(friend.nickname, Modifier.weight(1f), color = colors.ink, style = type.headline.sfLines())
            Spacer(Modifier.width(8.dp))
            FriendAvatar(friend.nickname, Modifier.size(33.dp, 41.dp), ExperimentalPalette.transit, ExperimentalPalette.transitSurface)
        }
        Text(summary, Modifier.heightIn(min = 36.dp), color = transit, style = type.caption2.sfLines())
        val arrival = friend.arrivedThisWeek
        Text(
            localized(if (arrival == true) "friends.arrived" else "friends.noArrival"),
            Modifier
                .heightIn(min = 18.dp)
                .then(if (arrival == null) Modifier.alpha(0f).clearAndSetSemantics {} else Modifier),
            color = transit, style = type.caption2.sfLines(),
        )
    }
}

/** What a profile form leaves the reader to fill: the name, at most 24 Unicode scalars, and the two shares. */
private const val NICKNAME_SCALARS = 24

/** `name.unicodeScalars.prefix(24)`: the name cut to its first 24 scalars, an emoji's halves kept together. */
internal fun String.cutToScalars(limit: Int = NICKNAME_SCALARS): String =
    if (codePointCount(0, length) <= limit) this else substring(0, offsetByCodePoints(0, limit))

/**
 * `FriendsProfileForm`: "friends.nickname" over its field (raised with 14-point corners, in by 14, the nickname's
 * keyboard, Done taking the keyboard down, at most 24 scalars), the preview of the reader's card as friends will see
 * it, the two shares as switches in delivered between dividers (each 10 above and below, its detail read as its hint),
 * the privacy line and Save, or "friends.join" for a new profile, or [submitKey]'s words where it is given: off while
 * [busy] or without a name. All of it is off while [busy]. Each switch flipped plays a light impact at half strength. The name is saved trimmed.
 *
 * The iPhone's form keeps a settled name 900 ms after typing stops, for an attention cue it no longer shows: nothing
 * here needs it.
 */
@Composable
internal fun FriendsProfileForm(
    source: DeliveriesSource,
    profile: FriendProfile?,
    busy: Boolean,
    submitKey: String? = null,
    save: (FriendProfile) -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val focus = LocalFocusManager.current
    var name by rememberSaveable { mutableStateOf(profile?.nickname ?: "") }
    var stats by rememberSaveable { mutableStateOf(profile?.shareStats ?: true) }
    var arrival by rememberSaveable { mutableStateOf(profile?.shareArrival ?: false) }
    var touches by remember { mutableIntStateOf(0) }
    SensoryFeedbackEffect(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light, 0.5), touches)
    val trimmed = FriendsStore.nickname(name)
    val parcels by source.parcels.collectAsState()
    val preview = remember(parcels, trimmed, stats, arrival, localizer) {
        FriendsStore.ownCard(parcels, FriendProfile(trimmed.ifEmpty { localizer.text("friends.you") }, stats, arrival))
    }
    val enabled = !busy
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val label = localized("friends.nickname")
            Text(label, color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
            NicknameField(name, { name = it.cutToScalars() }, localized("friends.nicknamePlaceholder"), label, enabled, focus)
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(localized("friends.preview"), color = colors.secondaryLabel, style = type.subheadline.sfLines())
            FriendSharingPreview(preview)
        }
        Column {
            FormHairline()
            ShareToggle("friends.shareStats", "friends.sharedStatsSummary", stats, enabled, FRIENDS_SHARE_STATS, showsDetail = true) {
                stats = it
                touches++
            }
            FormHairline()
            ShareToggle("friends.shareArrival", "friends.shareArrivalDetail", arrival, enabled, FRIENDS_SHARE_ARRIVAL, showsDetail = false) {
                arrival = it
                touches++
            }
            FormHairline()
        }
        SymbolLabel(localized("friends.privacy"), "lock", type.caption, colors.secondaryLabel)
        ProminentButton(
            localized(submitKey ?: if (profile == null) "friends.join" else "friends.save"), enabled = enabled && trimmed.isNotEmpty(), tag = FRIENDS_SAVE,
        ) { save(FriendProfile(trimmed, stats, arrival)) }
    }
}

/**
 * The nickname's `TextField`: body raised with 14-point corners, in by 14, its placeholder in the tertiary grey and
 * the accent's cursor; the first letter capitalised, and Done takes the keyboard down (`submitLabel(.done)`).
 */
@Composable
private fun NicknameField(value: String, onValueChange: (String) -> Unit, placeholder: String, label: String, enabled: Boolean, focus: FocusManager) {
    val colors = PeekTheme.colors
    val font = PeekTheme.type.body.sfLines()
    BasicTextField(
        value,
        onValueChange,
        Modifier
            .fillMaxWidth()
            .testTag(FRIENDS_NICKNAME)
            .background(colors.raised, ContinuousRoundedShape(14.dp))
            .padding(14.dp)
            .semantics {
                contentDescription = label
                contentType = ContentType.PersonFirstName
            },
        enabled = enabled,
        textStyle = font.copy(color = if (enabled) colors.label else colors.tertiaryLabel),
        singleLine = true,
        cursorBrush = SolidColor(colors.accent),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focus.clearFocus() }),
        decorationBox = { text ->
            Box(contentAlignment = Alignment.CenterStart) {
                if (value.isEmpty()) Text(placeholder, Modifier.clearAndSetSemantics {}, color = colors.tertiaryLabel, style = font, maxLines = 1)
                text()
            }
        },
    )
}

/**
 * A share's `Toggle` (`.toggleStyle(.switch)`, tinted delivered): its title in footnote medium, and for the stats its
 * detail in caption2 under it, 3 apart; 10 above and below. TalkBack reads it as one switch, the words it shows and
 * its state, the detail as its hint.
 */
@Composable
private fun ShareToggle(title: String, detail: String, on: Boolean, enabled: Boolean, tag: String, showsDetail: Boolean, onChange: (Boolean) -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val titleText = localized(title)
    val detailText = localized(detail)
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(tag)
            .clearAndSetSemantics {
                // The words it shows as its label, the detail as what a tap does (`accessibilityHint`).
                contentDescription = if (showsDetail) "$titleText, $detailText" else titleText
                role = Role.Switch
                toggleableState = ToggleableState(on)
                if (enabled) {
                    onClick(label = detailText) {
                        onChange(!on)
                        true
                    }
                } else {
                    disabled()
                }
            }
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(titleText, color = colors.label, style = type.style(IosTextStyle.Footnote, FontWeight.Medium).sfLines())
            if (showsDetail) Text(detailText, color = colors.secondaryLabel, style = type.caption2.sfLines())
        }
        Toggle(on, onChange, enabled = enabled, tint = colors.resolve(ExperimentalPalette.delivered))
    }
}

/** SwiftUI's `Divider()`: a pixel's line in the system's divider colour. */
@Composable
internal fun FormHairline() {
    val hairline = with(LocalDensity.current) { 1.toDp() }
    Box(Modifier.fillMaxWidth().height(hairline).background(PeekTheme.colors.divider))
}

/** `Label(_:systemImage:)`: the symbol, then the words 8 after it, in one style and colour. */
@Composable
internal fun SymbolLabel(text: String, symbol: String, style: TextStyle, tint: Color, modifier: Modifier = Modifier) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        PeekSymbol(symbol, font = style, tint = tint)
        Text(text, color = tint, style = style.sfLines())
    }
}

/**
 * `.buttonStyle(.borderedProminent)` with its label `.frame(maxWidth: .infinity, minHeight:)`: the words in body,
 * after a symbol where there is one, or a spinner 8 before them while [working], on a capsule of [fill] across the
 * column, the label [minHeight] tall and 7 more above and below; it fades as it is pressed, and is greyed while off.
 */
@Composable
internal fun ProminentButton(
    title: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    symbol: String? = null,
    minHeight: Dp = 46.dp,
    tag: String? = null,
    wide: Boolean = true,
    fill: Color = PeekTheme.colors.accent,
    content: Color = PeekTheme.colors.onAccent,
    working: Boolean = false,
    action: () -> Unit,
) {
    Box(
        modifier
            .then(if (wide) Modifier.fillMaxWidth() else Modifier)
            .then(if (tag != null) Modifier.testTag(tag) else Modifier)
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = action)
            .plainDisabled(enabled)
            .background(fill, CapsuleShape)
            .padding(horizontal = 14.dp, vertical = 7.dp)
            .heightIn(min = minHeight),
        contentAlignment = Alignment.Center,
    ) {
        val style = PeekTheme.type.body
        if (symbol != null) {
            SymbolLabel(title, symbol, style, content)
        } else if (working) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                ActivityIndicator(Modifier.clearAndSetSemantics {}, size = 20.dp, tint = content)
                Text(title, color = content, style = style.sfLines(), textAlign = TextAlign.Center)
            }
        } else {
            Text(title, color = content, style = style.sfLines(), textAlign = TextAlign.Center)
        }
    }
}

/**
 * `FriendsInvitationView`: "friends.inviteExpiry", the parcel from the reader, then the link once made: a spinner
 * while it is made (read as "friends.link"), Retry where it failed; made, the link on one line cut in its middle,
 * which a long press selects, Share (the system's share sheet, counted as started), Copy (on the clipboard as it is,
 * counted, "friends.copied" with a check from then on), the previous links cancelled where they were, a divider and
 * "friends.manageLinks", which opens on cancelling this link (closing the panel once done, [completed]) and the
 * previous ones with their count. It asks for one invitation as it first shows ([act]); all of it is off while [busy].
 * [sharing] hears the link go to the share sheet.
 */
@Composable
internal fun FriendsInvitationView(
    source: DeliveriesSource,
    nickname: String,
    busy: Boolean,
    invitation: InvitationState,
    act: suspend (FriendsActionRequest) -> FriendsActionResponse?,
    completed: () -> Unit,
    sharing: () -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val create: suspend () -> Unit = {
        val result = act(FriendsActionRequest(action = FriendsAction.CreateInvite))
        if (currentCoroutineActive()) {
            invitation.previewId = result?.previewId
            invitation.code = result?.inviteCode ?: ""
            invitation.previousCount = result?.previousInviteCount ?: 0
            invitation.previousCancelled = false
        }
    }
    LaunchedEffect(invitation) {
        if (invitation.requested) return@LaunchedEffect
        invitation.requested = true
        create()
    }
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(localized("friends.inviteExpiry"), color = colors.secondaryLabel, style = type.caption2.sfLines())
        FriendsInvitationParcel(nickname)
        if (invitation.code.isEmpty()) {
            if (!invitation.requested || busy) {
                val label = localized("friends.link")
                Box(
                    Modifier.fillMaxWidth().heightIn(min = 44.dp).testTag(FRIENDS_LINK_LOADING).clearAndSetSemantics { contentDescription = label },
                    contentAlignment = Alignment.Center,
                ) { ActivityIndicator(size = 20.dp, tint = colors.secondaryLabel) }
            } else {
                // `.borderedProminent` in the tab's ink, as the panel takes the tab's tint.
                ProminentButton(
                    localized("common.retry"), tag = FRIENDS_INVITE_RETRY, minHeight = 0.dp, wide = false, fill = colors.ink, content = Color.White,
                    modifier = Modifier.align(Alignment.Start),
                ) { scope.launch { create() } }
            }
        } else {
            val link = FriendInvitationLink.url(invitation.code, invitation.previewId, source.linkBaseUrl)
            val linkLabel = localized("friends.link") + ": " + link
            SelectionContainer {
                Text(
                    link,
                    Modifier
                        .fillMaxWidth()
                        .testTag(FRIENDS_INVITATION_URL)
                        .background(colors.ink.copy(alpha = 0.04f), ContinuousRoundedShape(9.dp))
                        .padding(10.dp)
                        .semantics { contentDescription = linkLabel },
                    color = colors.label, style = type.caption2.sfLines(), maxLines = 1, overflow = TextOverflow.MiddleEllipsis,
                )
            }
            ProminentButton(localized("friends.shareLink"), symbol = "square.and.arrow.up", minHeight = 44.dp, tag = FRIENDS_SHARE, enabled = !busy) {
                source.action("friend-invite-share", DeliveryAnalytics.Outcome.Started)
                val send = Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, link)
                try {
                    context.startActivity(Intent.createChooser(send, null))
                    sharing()
                } catch (_: ActivityNotFoundException) {
                }
            }
            val copyLabel = localized(if (invitation.copied) "friends.copied" else "friends.copyLink")
            Box(
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 36.dp)
                    .testTag(FRIENDS_COPY)
                    .clickable(interactionSource = null, indication = PlainPress, enabled = !busy, role = Role.Button) {
                        context.getSystemService(ClipboardManager::class.java)?.setPrimaryClip(ClipData.newPlainText(link, link))
                        invitation.copied = true
                        source.action("friend-invite-copy", DeliveryAnalytics.Outcome.Success)
                    }
                    .plainDisabled(!busy),
                contentAlignment = Alignment.Center,
            ) {
                SymbolLabel(copyLabel, if (invitation.copied) "checkmark" else "doc.on.doc", type.footnote, colors.ink)
            }
            if (invitation.previousCancelled) {
                SymbolLabel(localized("friends.previousRevoked"), "checkmark", type.caption, colors.resolve(ExperimentalPalette.delivered))
            }
            FormHairline()
            Disclosure(localized("friends.manageLinks"), invitation.managing, { invitation.managing = it }, enabled = !busy) {
                Column(Modifier.fillMaxWidth()) {
                    LinkAction(localized("friends.revoke"), FRIENDS_REVOKE, !busy) {
                        source.scope.launch {
                            if (act(FriendsActionRequest(action = FriendsAction.RevokeInvite, code = invitation.code)) != null) completed()
                        }
                    }
                    if (invitation.previousCount > 0) {
                        LinkAction(localizer.text("friends.cancelPrevious", mapOf("count" to invitation.previousCount)), FRIENDS_CANCEL_PREVIOUS, !busy) {
                            source.scope.launch {
                                if (act(FriendsActionRequest(action = FriendsAction.RevokePreviousInvites, code = invitation.code)) != null) {
                                    invitation.previousCount = 0
                                    invitation.previousCancelled = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/** Whether the coroutine running is still wanted (`!Task.isCancelled`). */
private suspend fun currentCoroutineActive(): Boolean = currentCoroutineContext().isActive

/** What `FriendsInvitationView` keeps in `@State`: the link made and what was done to it. */
internal data class InvitationValue(
    val code: String = "",
    val previewId: String? = null,
    val copied: Boolean = false,
    val requested: Boolean = false,
    val previousCount: Int = 0,
    val previousCancelled: Boolean = false,
    val managing: Boolean = false,
) : Serializable

/** The invitation's `@State` ([InvitationValue]), kept with the window, so a rotation doesn't ask for another link. */
@Stable
internal class InvitationState(private val state: MutableState<InvitationValue>) {
    private var value: InvitationValue
        get() = state.value
        set(next) {
            state.value = next
        }

    var code: String
        get() = value.code
        set(next) {
            value = value.copy(code = next)
        }
    var previewId: String?
        get() = value.previewId
        set(next) {
            value = value.copy(previewId = next)
        }
    var copied: Boolean
        get() = value.copied
        set(next) {
            value = value.copy(copied = next)
        }
    var requested: Boolean
        get() = value.requested
        set(next) {
            value = value.copy(requested = next)
        }
    var previousCount: Int
        get() = value.previousCount
        set(next) {
            value = value.copy(previousCount = next)
        }
    var previousCancelled: Boolean
        get() = value.previousCancelled
        set(next) {
            value = value.copy(previousCancelled = next)
        }
    var managing: Boolean
        get() = value.managing
        set(next) {
            value = value.copy(managing = next)
        }
}

/** A plain button in the disclosure: caption in the secondary grey, 30 tall at least, at the leading side. */
@Composable
private fun LinkAction(title: String, tag: String, enabled: Boolean, action: () -> Unit) {
    Box(
        Modifier
            .heightIn(min = 30.dp)
            .testTag(tag)
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = action)
            .plainDisabled(enabled),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(title, color = PeekTheme.colors.secondaryLabel, style = PeekTheme.type.caption.sfLines())
    }
}

/**
 * `DisclosureGroup`: its label, and at the trailing side the chevron in the tab's ink, which turns down as it opens;
 * a tap opens or closes what it holds under it, which comes and goes with it. Its label's row, tagged [tag], is
 * [minHeight] tall at least: 30 in the invitation's panel, 23 on a friend's page, as each measures.
 */
@Composable
internal fun Disclosure(
    label: String,
    expanded: Boolean,
    onExpand: (Boolean) -> Unit,
    enabled: Boolean,
    tag: String = FRIENDS_MANAGE_LINKS,
    minHeight: Dp = 30.dp,
    content: @Composable () -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val turn by animateFloatAsState(if (expanded) 90f else 0f, Motion.smooth(0.35), label = "disclosure")
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight)
                .testTag(tag)
                .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button) { onExpand(!expanded) }
                // Its state in TalkBack's own words, as the system's disclosure says it.
                .semantics {
                    if (!enabled) return@semantics
                    if (expanded) {
                        collapse {
                            onExpand(false)
                            true
                        }
                    } else {
                        expand {
                            onExpand(true)
                            true
                        }
                    }
                }
                .plainDisabled(enabled),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(label, Modifier.weight(1f), color = colors.secondaryLabel, style = type.caption.sfLines())
            PeekSymbol("chevron.right", Modifier.rotate(turn), font = type.style(IosTextStyle.Caption, FontWeight.SemiBold), tint = colors.ink)
        }
        AnimatedVisibility(expanded, enter = expandVertically() + fadeIn(), exit = shrinkVertically() + fadeOut()) { content() }
    }
}

/**
 * `FriendsInvitationParcel`: "friends.from" and the name in delivered, beside the parcel opening with the reader's name
 * on its card (158 × 163), on the delivered surface with 16-point corners, 157 tall at least, in by 17 and 8; at the
 * accessibility sizes the name under the parcel. The parcel opens as it shows, easing out over 1.5 s (0.1 s linear
 * under Reduce Motion) with its stars, and again from closed at a tap ("friends.replayParcel").
 */
@Composable
internal fun FriendsInvitationParcel(nickname: String) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    var iteration by remember { mutableIntStateOf(0) }
    val open = remember { Animatable(0f) }
    var celebrating by remember { mutableStateOf(false) }
    LaunchedEffect(iteration) {
        open.snapTo(0f)
        celebrating = false
        delay(80)
        celebrating = true
        // Kept: iOS keeps the 0.1 s linear under Reduce Motion, which Remove animations would otherwise jump.
        withContext(KeepMotion) { open.animateTo(1f, if (reduceMotion) Motion.linear(0.1) else Motion.easeOut(1.5)) }
    }
    val delivered = colors.resolve(ExperimentalPalette.delivered)
    val sender = @Composable {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(localized("friends.from"), color = delivered, style = type.caption2.sfLines())
            Text(nickname, color = delivered, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
        }
    }
    val replay = localized("friends.replayParcel")
    val parcel = @Composable { modifier: Modifier ->
        Box(
            modifier
                .testTag(FRIENDS_REPLAY)
                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button) { iteration++ }
                .clearAndSetSemantics {
                    contentDescription = replay
                    role = Role.Button
                    onClick {
                        iteration++
                        true
                    }
                },
        ) {
            ScaledParcel(open.value.toDouble(), celebrating, nickname)
        }
    }
    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 157.dp)
            .background(colors.resolve(ExperimentalPalette.deliveredSurface), ContinuousRoundedShape(16.dp))
            .clipToBounds()
            .padding(start = 17.dp, end = 8.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        if (large) {
            Column(Modifier.fillMaxWidth().padding(bottom = 18.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                parcel(Modifier.align(Alignment.CenterHorizontally))
                sender()
            }
        } else {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.weight(1f)) { sender() }
                parcel(Modifier)
            }
        }
    }
}

/** `UnwrappingParcel(open:celebrating:senderName:)` framed 158 × 163: Pip's 300 × 310 frame made smaller to fit. */
@Composable
private fun ScaledParcel(open: Double, celebrating: Boolean, sender: String) {
    val frameWidth = PipGeometry.frame.width.toFloat()
    val frameHeight = PipGeometry.frame.height.toFloat()
    val scale = PARCEL_WIDTH / frameWidth
    Box(
        Modifier
            .size(PARCEL_WIDTH.dp, PARCEL_HEIGHT.dp)
            .layout { measurable, _ ->
                val frame = measurable.measure(Constraints.fixed(frameWidth.dp.roundToPx(), frameHeight.dp.roundToPx()))
                layout(PARCEL_WIDTH.dp.roundToPx(), PARCEL_HEIGHT.dp.roundToPx()) {
                    frame.placeWithLayer(0, 0) {
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
                }
            },
    ) {
        UnwrappingParcel(open, label = null, ribbon = false, celebrating = celebrating, sender = sender)
    }
}

private const val PARCEL_WIDTH = 158f
private const val PARCEL_HEIGHT = 163f

/**
 * `errorView(_:)`: what went wrong in subheadline, in the pickup colour on its surface with 16-point corners, in by 14,
 * across the column.
 */
@Composable
internal fun FriendsErrorBanner(key: String) {
    val colors = PeekTheme.colors
    Text(
        localized(key),
        Modifier
            .fillMaxWidth()
            .testTag(FRIENDS_ERROR)
            .background(colors.resolve(ExperimentalPalette.pickupSurface), ContinuousRoundedShape(16.dp))
            .padding(14.dp),
        color = colors.resolve(ExperimentalPalette.pickup),
        style = PeekTheme.type.subheadline.sfLines(),
    )
}

/** The test tags of the friends' parts: iOS's identifiers, then what the tests need. */
/** The tags' namespace: iOS's identifiers keep their own words, the tests' own are built on it. */
private const val FRIENDS = "friends"

const val FRIENDS_SIGN_IN = "friends.signIn"
const val FRIENDS_DEMO_SIGN_IN = "friends.demoSignIn"
const val FRIENDS_INVITATION_URL = "friends.invitationURL"
const val FRIENDS_PAGE = "$FRIENDS.page"
const val FRIENDS_SCROLL = "$FRIENDS.scroll"
const val FRIENDS_ERROR = "$FRIENDS.error"
const val FRIENDS_RETRY = "$FRIENDS.retry"
const val FRIENDS_LOADING = "$FRIENDS.loading"
const val FRIENDS_UNAVAILABLE_FRIEND = "$FRIENDS.friendUnavailable"
const val FRIENDS_JOIN = "$FRIENDS.join"
const val FRIENDS_SHARING = "$FRIENDS.sharing"
const val FRIENDS_INVITE = "$FRIENDS.invite"
const val FRIENDS_HEADER = "$FRIENDS.header"
const val FRIENDS_PANEL = "$FRIENDS.panel"
const val FRIENDS_CLOSE = "$FRIENDS.close"
const val FRIENDS_NICKNAME = "$FRIENDS.nickname"
const val FRIENDS_PREVIEW = "$FRIENDS.preview"
const val FRIENDS_SHARE_STATS = "$FRIENDS.shareStats"
const val FRIENDS_SHARE_ARRIVAL = "$FRIENDS.shareArrival"
const val FRIENDS_SAVE = "$FRIENDS.save"
const val FRIENDS_LINK_LOADING = "$FRIENDS.linkLoading"
const val FRIENDS_INVITE_RETRY = "$FRIENDS.inviteRetry"
const val FRIENDS_SHARE = "$FRIENDS.shareLink"
const val FRIENDS_COPY = "$FRIENDS.copyLink"
const val FRIENDS_MANAGE_LINKS = "$FRIENDS.manageLinks"
const val FRIENDS_REVOKE = "$FRIENDS.revoke"
const val FRIENDS_CANCEL_PREVIOUS = "$FRIENDS.cancelPrevious"
const val FRIENDS_REPLAY = "$FRIENDS.replayParcel"
const val FRIENDS_ACCOUNT_BUTTON = "$FRIENDS.accountButton"
const val FRIENDS_ANNOUNCEMENT = "$FRIENDS.announcement"

const val FRIENDS_PAGE_BAR = "$FRIENDS.pageBar"
const val FRIENDS_PAGE_BACK = "$FRIENDS.pageBack"
const val FRIENDS_PAGE_SCROLL = "$FRIENDS.pageScroll"
const val FRIENDS_POSTCARD = "$FRIENDS.postcard"
const val FRIENDS_METRICS = "$FRIENDS.metrics"
const val FRIENDS_PRIVATE_STATS = "$FRIENDS.privateStats"
const val FRIENDS_SHOW_ALL = "$FRIENDS.showAllStamps"
const val FRIENDS_POPOVER = "$FRIENDS.stampPopover"
const val FRIENDS_MANAGE_FRIENDSHIP = "$FRIENDS.manageFriendship"
const val FRIENDS_REMOVE = "$FRIENDS.remove"
const val FRIENDS_CONFIRM_REMOVE = "$FRIENDS.confirmRemove"
const val FRIENDS_CANCEL_REMOVE = "$FRIENDS.cancelRemove"
const val FRIENDS_NOTICE = "$FRIENDS.notice"
const val FRIENDS_NOTICE_OPEN = "$FRIENDS.noticeOpen"
const val FRIENDS_NOTICE_DISMISS = "$FRIENDS.noticeDismiss"

/** A friend's card, by their id. */
fun friendCardTag(id: UUID) = "$FRIENDS.card.$id"

/** A stamp on a friend's page. */
fun friendStampTag(stamp: FriendStamp) = "$FRIENDS.stamp.${stamp.rawValue}"
