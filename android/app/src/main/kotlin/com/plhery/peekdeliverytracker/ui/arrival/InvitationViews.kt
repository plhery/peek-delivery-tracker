package com.plhery.peekdeliverytracker.ui.arrival

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.FriendInvitationStore
import com.plhery.peekdeliverytracker.core.FriendsStore
import com.plhery.peekdeliverytracker.core.api.FriendProfile
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponseInvitationState
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesSource
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.deliveries.plainDisabled
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.DieCutStampShape
import com.plhery.peekdeliverytracker.ui.draw.PeekLockup
import com.plhery.peekdeliverytracker.ui.friends.FriendPostcard
import com.plhery.peekdeliverytracker.ui.friends.FriendSharingPreview
import com.plhery.peekdeliverytracker.ui.friends.FriendsProfileForm
import com.plhery.peekdeliverytracker.ui.friends.PanelPage
import com.plhery.peekdeliverytracker.ui.friends.ProminentButton
import com.plhery.peekdeliverytracker.ui.friends.SymbolLabel
import com.plhery.peekdeliverytracker.ui.settings.FORM_BAR_SIDE
import com.plhery.peekdeliverytracker.ui.settings.FORM_BAR_TOP
import com.plhery.peekdeliverytracker.ui.settings.FORM_TITLE_SIDE
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetInsets
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.SheetMenus
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import kotlin.math.max
import kotlinx.coroutines.launch

/**
 * `WelcomeView` (C-S02) with a friend's invitation: ✕ ([onDismiss], read as `common.close`) before the lockup and the
 * language menu at the trailing end; the room above (36 at least) and the invitation's heading 8 in on each side; 12
 * down, the parcel's anchor, as wide as the window less 48 and 300 at most and 31/30 as tall, which [onAnchor] is told
 * of. Once the sender is known it is a button with "arrival.tapToOpen" 8 under it (medium subheadline, secondary, 44
 * at least, gone as the parcel opens), read as that with `arrival.openHint` as its hint, which reports its press
 * ([onPress]) and opens the parcel ([onOpen]), off while it opens. Then a spinner 12 down while the sender is asked,
 * what went wrong (secondary footnote, centred) with Retry 8 under it but where the link leads nowhere, and the room
 * under it (64 at least). The two rooms share what the window leaves as a VStack's spacers do; where nothing is left
 * the page scrolls. A column 520 wide at most, 24 in from the sides and 16 from the safe area's top and bottom.
 */
@Composable
internal fun InvitationWelcome(
    invitation: FriendInvitationStore,
    scroll: ScrollState,
    menus: SheetMenus,
    opening: Boolean,
    onOpen: () -> Unit,
    onPress: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    onRetry: () -> Unit,
    onAnchor: (LayoutCoordinates) -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val nickname by invitation.nicknameFlow.collectAsState()
    val errorKey by invitation.errorKeyFlow.collectAsState()
    val loading by invitation.loadingFlow.collectAsState()
    val safe = WindowInsets.systemBars.union(WindowInsets.displayCutout)
    BoxWithConstraints(Modifier.fillMaxSize().windowInsetsPadding(safe).testTag(INVITATION_WELCOME)) {
        val side = (maxWidth - 48.dp).coerceIn(0.dp, 300.dp)
        val viewport = maxHeight
        Box(Modifier.fillMaxSize().verticalScroll(scroll)) {
            SpacedColumn(
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
                    .widthIn(max = WELCOME_WIDTH)
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                minHeight = viewport - 32.dp,
                top = {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        val close = localized("common.close")
                        Box(
                            Modifier
                                .size(44.dp)
                                .testTag(INVITATION_CLOSE)
                                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onDismiss)
                                .semantics { contentDescription = close },
                            contentAlignment = Alignment.Center,
                        ) {
                            PeekSymbol("xmark", font = type.body, tint = colors.ink)
                        }
                        PeekLockup()
                        Spacer(Modifier.weight(1f))
                        LanguageMenu(menus)
                    }
                },
            ) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    InvitationHeading(nickname, Modifier.padding(horizontal = 8.dp))
                    val anchor = Modifier.size(side, side * 31f / 30f).onGloballyPositioned(onAnchor)
                    if (nickname == null) {
                        Box(Modifier.padding(top = 12.dp).then(anchor))
                    } else {
                        OpenParcelButton(opening, onOpen, onPress) { Box(anchor) }
                    }
                    if (loading) ActivityIndicator(Modifier.padding(top = 12.dp).testTag(INVITATION_LOADING), size = 20.dp, tint = colors.secondaryLabel)
                    errorKey?.let { error ->
                        Text(
                            localized(error), Modifier.testTag(INVITATION_ERROR), color = colors.secondaryLabel, textAlign = TextAlign.Center,
                            style = type.footnote.sfLines(),
                        )
                        if (error != UNAVAILABLE) PlainTextButton(localized("common.retry"), INVITATION_RETRY, Modifier.padding(top = 8.dp), onClick = onRetry)
                    }
                }
            }
        }
    }
}

/**
 * The welcome's parcel as a button (`ParcelOpeningButtonStyle`): the anchor over "arrival.tapToOpen", the whole of it
 * tappable, its press told as it changes; TalkBack reads one button with the open hint.
 */
@Composable
private fun OpenParcelButton(opening: Boolean, onOpen: () -> Unit, onPress: (Boolean) -> Unit, anchor: @Composable () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    LaunchedEffect(pressed) { onPress(pressed) }
    val title = localized("arrival.tapToOpen")
    val hint = localized("arrival.openHint")
    val enabled = !opening
    Column(
        Modifier
            .padding(top = 12.dp)
            .testTag(WELCOME_OPEN_PARCEL)
            .clickable(interaction, indication = null, enabled = enabled, role = Role.Button, onClick = onOpen)
            .clearAndSetSemantics {
                contentDescription = title
                role = Role.Button
                if (enabled) {
                    onClick(label = hint) {
                        onOpen()
                        true
                    }
                } else {
                    disabled()
                }
            },
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        anchor()
        Box(Modifier.heightIn(min = 44.dp).alpha(if (opening) 0f else 1f), contentAlignment = Alignment.Center) {
            Text(title, color = colors.secondaryLabel, textAlign = TextAlign.Center, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines())
        }
    }
}

/**
 * A VStack's two spacers: [top], then the room above (36 at least), [content], and the room under (64 at least), at
 * least [minHeight] tall; the rooms share what is left alike, each keeping its least.
 */
@Composable
private fun SpacedColumn(modifier: Modifier, minHeight: Dp, top: @Composable () -> Unit, content: @Composable () -> Unit) {
    val density = LocalDensity.current
    Layout(content = {
        Box { top() }
        Box { content() }
    }, modifier = modifier) { measurables, constraints ->
        val loose = Constraints(maxWidth = constraints.maxWidth)
        val bar = measurables[0].measure(loose.copy(minWidth = constraints.maxWidth))
        val body = measurables[1].measure(loose.copy(minWidth = constraints.maxWidth))
        val least = with(density) { minHeight.roundToPx() }
        val above = with(density) { 36.dp.roundToPx() }
        val below = with(density) { 64.dp.roundToPx() }
        val room = least - bar.height - body.height
        val over = if (room / 2 >= below) room / 2 else max(above, room - below)
        val under = max(below, room - over)
        val height = bar.height + over + body.height + under
        layout(constraints.maxWidth, height) {
            bar.place(0, 0)
            body.place(0, bar.height + over)
        }
    }
}

/**
 * `InvitationHeading`: "friends.invitationTitle" with the sender's name in its `{{name}}` slot, the name in semibold
 * italic serif title, the rest in medium rounded title tightened 0.6, centred; the template alone where it has no slot,
 * and "friends.invitationGeneric" until the name is known. A heading for TalkBack.
 */
@Composable
internal fun InvitationHeading(nickname: String?, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val generic = localized("friends.invitationGeneric")
    val template = localized("friends.invitationTitle")
    val name = type.style(IosTextStyle.Title, FontWeight.SemiBold, FontDesign.Serif)
    val sentence = remember(nickname, generic, template, name) {
        buildAnnotatedString {
            val slot = template.indexOf(NAME_SLOT)
            when {
                nickname == null -> append(generic)
                slot < 0 -> append(template)
                else -> {
                    append(template.substring(0, slot))
                    withStyle(SpanStyle(fontFamily = name.fontFamily, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, fontSize = name.fontSize)) {
                        append(nickname)
                    }
                    append(template.substring(slot + NAME_SLOT.length))
                }
            }
        }
    }
    Text(
        sentence,
        modifier.testTag(INVITATION_HEADING).semantics { heading() },
        color = colors.label,
        textAlign = TextAlign.Center,
        style = type.tracking(type.style(IosTextStyle.Title, FontWeight.Medium, FontDesign.Rounded), -0.6f).sfLines(),
    )
}

/**
 * `FriendshipReceiptStamp`: a die-cut stamp in cream with a soft shadow (black at 18 %, radius 5, 3 down), and on it,
 * 9 apart in delivered, two people (medium 30) over a sealed check (23), 12 in, in a dashed frame (delivered at 45 %,
 * 1 wide, 2 on 2 off, corners of 2) 9 inside that. Drawn 100 × 116.
 */
@Composable
internal fun FriendshipReceiptStamp(modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val delivered = colors.resolve(ExperimentalPalette.delivered)
    val shape = remember { DieCutStampShape() }
    Box(modifier.size(100.dp, 116.dp).testTag(INVITATION_STAMP), contentAlignment = Alignment.Center) {
        Box(Modifier.fillMaxSize().dropShadow(shape, swiftUiShadow(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.18f), radius = 5.dp, y = 3.dp)).background(colors.cream, shape))
        Column(
            Modifier
                .padding(12.dp)
                .padding(9.dp)
                .drawBehindDashed(delivered.copy(alpha = delivered.alpha * 0.45f), 9.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PeekSymbol("person.2.fill", font = type.fixed(30f, FontWeight.Medium), tint = delivered)
            PeekSymbol("checkmark.seal.fill", font = type.fixed(23f), tint = delivered)
        }
    }
}

/**
 * The stamp's dashed frame: drawn around what the column holds plus its 12, [inset] in from that, so [inset] out from
 * the column's own content.
 */
private fun Modifier.drawBehindDashed(color: androidx.compose.ui.graphics.Color, inset: Dp): Modifier = this.then(
    Modifier.drawWithCache {
        val grow = 12.dp.toPx() - inset.toPx()
        val line = 1.dp.toPx()
        val stroke = Stroke(width = line, pathEffect = PathEffect.dashPathEffect(floatArrayOf(2.dp.toPx(), 2.dp.toPx())))
        onDrawBehind {
            drawRoundRect(
                color,
                topLeft = androidx.compose.ui.geometry.Offset(-grow + line / 2, -grow + line / 2),
                size = androidx.compose.ui.geometry.Size(size.width + 2 * grow - line, size.height + 2 * grow - line),
                cornerRadius = CornerRadius(2.dp.toPx()),
                style = stroke,
            )
        }
    },
)

/**
 * `FriendInvitationAcceptanceView` (C-S13), for the account signed in: in a scroll, 18 apart, 24 in, 520 wide at most,
 * the back (‹, 44 across, read as `welcome.back`, off while joining) and the language menu; once accepted, the
 * parcel's anchor (180 × 186, [onAnchor]) over "friends.friendshipDelivered"; where the invitation can't be accepted,
 * why (already accepted, already friends, your own) as a heading with Close; otherwise the sender's postcard. What went
 * wrong (secondary footnote) with Retry (but where the link leads nowhere), which asks who sent it and checks it again;
 * with a profile, the reader's sharing ("friends.yourSharing", Edit sharing, their card, the privacy line); then Accept
 * (or "friends.enableToAccept" without a profile, which opens the profile's sheet), its spinner while joining, or a
 * spinner while it is checked.
 *
 * Its own `FriendsStore()` ([FriendsStore], the account's) loads the circle and checks the invitation while the app is
 * in front, and forgets them as it leaves. Accepting saves the profile first where the sheet gave one; the answer,
 * heard even once stale, closes the sheet and either says why it can't be accepted or is the receipt
 * ([FriendInvitationStore.receive]). The sheet ([ProfileSheet]) is a page sheet, a form where the passport's rule says
 * ([asForm]). The system's back does what ‹ does, but while joining.
 */
@Composable
internal fun FriendInvitationAcceptance(
    source: DeliveriesSource,
    scroll: ScrollState,
    menus: SheetMenus,
    active: Boolean,
    asForm: Boolean,
    onBack: () -> Unit,
    onAnchor: (LayoutCoordinates) -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val invitation = source.invitation
    val model = remember { FriendsStore(source.friendsClient) }
    val snapshot by model.snapshotFlow.collectAsState()
    val modelError by model.errorKeyFlow.collectAsState()
    val nickname by invitation.nicknameFlow.collectAsState()
    val invitationError by invitation.errorKeyFlow.collectAsState()
    val receipt by invitation.receiptFlow.collectAsState()
    val parcels by source.parcels.collectAsState()
    var creatingProfile by rememberSaveable { mutableStateOf(false) }
    var editingProfile by rememberSaveable { mutableStateOf(false) }
    var joining by remember { mutableStateOf(false) }
    var checking by remember { mutableStateOf(true) }
    var invitationState by remember { mutableStateOf<FriendsActionResponseInvitationState?>(null) }
    val scope = rememberCoroutineScope()
    val outcome = when (invitationState) {
        FriendsActionResponseInvitationState.AlreadyAccepted -> "friends.inviteAlreadyAccepted"
        FriendsActionResponseInvitationState.AlreadyFriends -> "friends.alreadyFriends"
        null -> if (modelError == "friends.selfInvitation") "friends.selfInvitation" else null
    }

    /** `checkInvitation()`: the circle, then whether this invitation can still be accepted. */
    suspend fun checkInvitation() {
        checking = true
        invitationState = null
        try {
            model.load(source.parcels.value)
            val code = invitation.code
            if (model.snapshot == null || code == null) return
            invitationState = model.act(FriendsActionRequest(FriendsAction.PreviewInvite, code = code), source.parcels.value)?.invitationState
        } finally {
            checking = false
        }
    }

    /** `accept(profile:)`: the profile saved first where one is given, then the invitation accepted. */
    suspend fun accept(profile: FriendProfile? = null) {
        val code = invitation.code
        if (joining || code == null) return
        joining = true
        try {
            if (profile != null) {
                val request = FriendsActionRequest(FriendsAction.SaveProfile, profile.nickname, profile.shareStats, profile.shareArrival)
                val saved = model.act(request, source.parcels.value) ?: return
                if (saved.snapshot?.profile == null) {
                    model.errorKey = "friends.actionFailed"
                    return
                }
            }
            model.act(FriendsActionRequest(FriendsAction.AcceptInvite, code = code), source.parcels.value) { result ->
                if (invitation.isPresenting && invitation.code == code) {
                    creatingProfile = false
                    val state = result.invitationState
                    if (state != null) invitationState = state else invitation.receive(result)
                }
            }
        } finally {
            joining = false
            // Once enabled, a failed invitation can be retried without creating the profile again.
            if (model.snapshot?.profile != null) creatingProfile = false
        }
    }

    /** `saveSharing(_:)`: the sharing changed from the sheet, which closes once saved. */
    suspend fun saveSharing(profile: FriendProfile) {
        if (joining) return
        joining = true
        try {
            val request = FriendsActionRequest(FriendsAction.SaveProfile, profile.nickname, profile.shareStats, profile.shareArrival)
            if (model.act(request, source.parcels.value) != null) creatingProfile = false
        } finally {
            joining = false
        }
    }

    // `.task(id: scenePhase)` and `.onDisappear`: the account's circle while the app is in front, forgotten as it leaves.
    LaunchedEffect(active) {
        if (!active) {
            model.clear()
            return@LaunchedEffect
        }
        model.configure(demo = false)
        checkInvitation()
    }
    androidx.compose.runtime.DisposableEffect(model) { onDispose { model.clear() } }
    // The back is off while joining: the system's back then does nothing rather than leave the app.
    BackHandler { if (!joining) onBack() }

    val safe = WindowInsets.systemBars.union(WindowInsets.displayCutout)
    Box(Modifier.fillMaxSize().windowInsetsPadding(safe).testTag(INVITATION_ACCEPTANCE)) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(24.dp)
                .fillMaxWidth()
                .wrapContentWidth()
                .widthIn(max = WELCOME_WIDTH),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                val back = localized("welcome.back")
                Box(
                    Modifier
                        .size(44.dp)
                        .testTag(INVITATION_BACK)
                        .clickable(interactionSource = null, indication = PlainPress, enabled = !joining, role = Role.Button, onClick = onBack)
                        .plainDisabled(!joining)
                        .semantics { contentDescription = back },
                    contentAlignment = Alignment.Center,
                ) {
                    PeekSymbol("chevron.left", font = type.body, tint = colors.ink)
                }
                Spacer(Modifier.weight(1f))
                LanguageMenu(menus)
            }
            val title = type.style(IosTextStyle.Title, FontWeight.Medium, FontDesign.Rounded).sfLines()
            if (receipt != null) {
                Box(Modifier.size(180.dp, 186.dp).onGloballyPositioned(onAnchor))
                Text(localized("friends.friendshipDelivered"), Modifier.semantics { heading() }, color = colors.label, textAlign = TextAlign.Center, style = title)
            } else if (outcome != null) {
                Text(localized(outcome), Modifier.testTag(INVITATION_OUTCOME).semantics { heading() }, color = colors.label, textAlign = TextAlign.Center, style = title)
            } else {
                nickname?.let { FriendPostcard(it, arrivedThisWeek = null) }
            }
            val error = invitationError ?: modelError
            if (error != null && !creatingProfile && outcome == null && receipt == null) {
                Text(localized(error), Modifier.testTag(INVITATION_ERROR), color = colors.secondaryLabel, style = type.footnote.sfLines())
                if (error != UNAVAILABLE) {
                    PlainTextButton(localized("common.retry"), INVITATION_RETRY) {
                        scope.launch {
                            invitation.loadPreview(signedIn = true)
                            checkInvitation()
                        }
                    }
                }
            }
            val profile = snapshot?.profile
            if (outcome == null && receipt == null && profile != null) {
                Column(Modifier.fillMaxWidth().testTag(INVITATION_SHARING), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(localized("friends.yourSharing"), color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
                        Spacer(Modifier.weight(1f))
                        PlainTextButton(localized("friends.editSharing"), INVITATION_EDIT_SHARING, enabled = !joining, style = type.caption) {
                            editingProfile = true
                            creatingProfile = true
                            model.errorKey = null
                        }
                    }
                    FriendSharingPreview(snapshot?.ownCard ?: FriendsStore.ownCard(parcels, profile))
                    SymbolLabel(localized("friends.privacy"), "lock", type.caption2, colors.secondaryLabel)
                }
            }
            if (outcome != null) {
                ProminentButton(localized("common.close"), tag = INVITATION_OUTCOME_CLOSE) { invitation.dismiss() }
            } else if (!checking && receipt == null && nickname != null && snapshot != null) {
                ProminentButton(
                    localized(if (profile == null) "friends.enableToAccept" else "friends.accept"),
                    enabled = !joining && modelError != UNAVAILABLE, tag = INVITATION_ACCEPT, working = joining,
                ) {
                    if (model.snapshot?.profile == null) {
                        model.errorKey = null
                        editingProfile = false
                        creatingProfile = true
                    } else {
                        scope.launch { accept() }
                    }
                }
            } else if (receipt == null && invitationError == null && modelError == null) {
                ActivityIndicator(Modifier.testTag(INVITATION_LOADING), size = 20.dp, tint = colors.secondaryLabel)
            }
        }
    }
    ProfileSheet(
        visible = creatingProfile,
        editing = editingProfile,
        source = source,
        model = model,
        joining = joining,
        asForm = asForm,
        close = {
            creatingProfile = false
            model.errorKey = null
        },
        save = { profile -> scope.launch { if (editingProfile) saveSharing(profile) else accept(profile) } },
    )
}

/**
 * The acceptance's `.sheet(isPresented: $creatingProfile)`: in its `NavigationStack`, the title inline
 * ("friends.settings" while editing, "friends.enable" otherwise) and Close as its confirmation (off while joining,
 * clearing what went wrong), what went wrong (secondary footnote) over the profile's form, 16 apart in a column 520
 * wide at most, the form's button "friends.save" while editing and "friends.joinAndAccept" otherwise; at the large
 * detent with its drag indicator, and no drag, tap outside or back closes it while joining.
 */
@Composable
private fun ProfileSheet(
    visible: Boolean,
    editing: Boolean,
    source: DeliveriesSource,
    model: FriendsStore,
    joining: Boolean,
    asForm: Boolean,
    close: () -> Unit,
    save: (FriendProfile) -> Unit,
) {
    val errorKey by model.errorKeyFlow.collectAsState()
    val snapshot by model.snapshotFlow.collectAsState()
    val title = localized(if (editing) "friends.settings" else "friends.enable")
    Sheet(
        visible = visible,
        onDismiss = close,
        paneTitle = title,
        detents = listOf(SheetDetent.Large),
        dismissible = !joining,
        page = !asForm,
    ) {
        val insets = LocalSheetInsets.current
        val form = if (!asForm) insets else insets.copy(barTop = FORM_BAR_TOP, barSide = FORM_BAR_SIDE, formSide = FORM_TITLE_SIDE)
        CompositionLocalProvider(LocalSheetInsets provides form) {
            PanelPage(title, closeEnabled = !joining, close = close) {
                Column(
                    Modifier.fillMaxWidth().wrapContentWidth().widthIn(max = WELCOME_WIDTH).testTag(INVITATION_SHEET),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    errorKey?.let {
                        Text(localized(it), color = PeekTheme.colors.secondaryLabel, style = PeekTheme.type.footnote.sfLines())
                    }
                    androidx.compose.runtime.key(editing) {
                        FriendsProfileForm(
                            source, if (editing) snapshot?.profile else null, joining,
                            submitKey = if (editing) "friends.save" else "friends.joinAndAccept", save = save,
                        )
                    }
                }
            }
        }
    }
}

private const val NAME_SLOT = "{{name}}"
private const val UNAVAILABLE = "friends.inviteUnavailable"
private val WELCOME_WIDTH = 520.dp

/** The test tags of the invitation's screens: iOS's identifier, then what the tests need. */
const val WELCOME_OPEN_PARCEL = "welcome.openParcel"
const val INVITATION_WELCOME = "invitation.welcome"
const val INVITATION_CLOSE = "invitation.close"
const val INVITATION_HEADING = "invitation.heading"
const val INVITATION_LOADING = "invitation.loading"
const val INVITATION_ERROR = "invitation.error"
const val INVITATION_RETRY = "invitation.retry"
const val INVITATION_ACCEPTANCE = "invitation.acceptance"
const val INVITATION_BACK = "invitation.back"
const val INVITATION_OUTCOME = "invitation.outcome"
const val INVITATION_OUTCOME_CLOSE = "invitation.outcomeClose"
const val INVITATION_SHARING = "invitation.sharing"
const val INVITATION_EDIT_SHARING = "invitation.editSharing"
const val INVITATION_ACCEPT = "invitation.accept"
const val INVITATION_SHEET = "invitation.profileSheet"
const val INVITATION_STAMP = "invitation.receiptStamp"
