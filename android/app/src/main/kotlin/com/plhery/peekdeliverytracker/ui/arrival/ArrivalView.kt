package com.plhery.peekdeliverytracker.ui.arrival

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.currentStateAsState
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.ui.ARRIVAL
import com.plhery.peekdeliverytracker.ui.RootTransaction
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesSource
import com.plhery.peekdeliverytracker.ui.deliveries.LocalWindowArrangement
import com.plhery.peekdeliverytracker.ui.deliveries.UnwrappingParcel
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.sheet.REGULAR_HEIGHT
import com.plhery.peekdeliverytracker.ui.sheet.REGULAR_WIDTH
import com.plhery.peekdeliverytracker.ui.sheet.SheetMenus
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowHinge
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowLayers
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowOverlay
import com.plhery.peekdeliverytracker.ui.tabs.TabOverlay
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.KeepMotion
import com.plhery.peekdeliverytracker.ui.theme.LocalMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekKeyframes
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.delay
import com.plhery.peekdeliverytracker.ui.theme.keyframeAnimator
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/** `ArrivalScreen`: the arrival's two screens, which the parcel moves between. */
internal enum class ArrivalScreen { Welcome, SignIn }

/**
 * `ArrivalView` (C-S01): the arrival, where nobody is signed in and this phone follows nothing on its own: the welcome
 * while the session is on it, the sign-in otherwise, over the page's background. One parcel stays alive across both,
 * drawn over them at the anchor the screen shown leaves for it (`ArrivalParcelFrame`), clipped to the safe area,
 * untouchable and out of TalkBack's reach, and it springs from one anchor to the other as the screen changes
 * (`.spring(duration: 0.75, bounce: 0.08)`, at once under Reduce Motion), following the anchor as it scrolls.
 *
 * Without an invitation the root shows the arrival only for the sign-in: the welcome is the tabs' first open, and the
 * arrival is on it only while the root fades it out after the sign-in's back ([goBack], a SwiftUI transaction through
 * [transaction]). Then the sign-in has gone at once, and the parcel springs toward where WelcomeView would put its
 * anchor ([rememberWelcomeAnchor]), shutting, as it fades with the arrival. The welcome itself, its greeting, tilt,
 * press and unwrap, and the invitations come with WelcomeView. The sign-in fades in where it replaces the welcome in an
 * arrival already showing (`.opacity.animation(.easeOut(duration: 0.35).delay(0.25))`, 0.15 s without the delay under
 * Reduce Motion), not when the arrival itself comes in. Its sends and checks run in [work], which outlives it.
 *
 * Everything the arrival draws is the backdrop of the language menu's glass, as the iPhone's menu shows the parcel and
 * the card through it.
 */
@Composable
internal fun ArrivalView(state: AuthenticationState, session: SessionStore, transaction: RootTransaction, work: CoroutineScope) {
    val screen = if (state == AuthenticationState.Welcome) ArrivalScreen.Welcome else ArrivalScreen.SignIn
    val reduceMotion = PeekTheme.reduceMotion
    val scroll = rememberScrollState()
    val frames = remember { ArrivalParcelFrames() }
    val menus = remember { SheetMenus() }
    val backdrop = rememberMaterialBackdrop()
    val firstScreen = remember { screen }
    // `goBack`: the welcome, the arrival fading out with the root's switch.
    val goBack = {
        transaction.withAnimation(Motion.easeInOut(if (reduceMotion) 0.15 else 0.45)) {
            session.showWelcome()
            session.state
        }
    }
    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .background(PeekTheme.colors.background)
            .testTag(ARRIVAL)
            .onPlaced { frames.arrival = it },
    ) {
        val welcome = rememberWelcomeAnchor(constraints.maxWidth, constraints.maxHeight)
        Box(Modifier.fillMaxSize().materialBackdrop(backdrop), propagateMinConstraints = true) {
            Box(if (menus.content != null) Modifier.clearAndSetSemantics {} else Modifier, propagateMinConstraints = true) {
                if (screen == ArrivalScreen.SignIn) {
                    val appearance = remember { Animatable(if (firstScreen == ArrivalScreen.SignIn) 1f else 0f) }
                    LaunchedEffect(Unit) {
                        if (appearance.value >= 1f) return@LaunchedEffect
                        val fade = Motion.easeOut<Float>(if (reduceMotion) 0.15 else 0.35).delay(if (reduceMotion) 0.0 else 0.25)
                        withContext(KeepMotion) { appearance.animateTo(1f, fade) }
                    }
                    Box(Modifier.graphicsLayer { alpha = appearance.value }, propagateMinConstraints = true) {
                        SignInView(session, scroll, menus, work, onBack = goBack, onAnchor = { frames.place(ArrivalScreen.SignIn, it, scroll) })
                    }
                }
            }
            ArrivalParcel(screen, frames, { if (it == ArrivalScreen.Welcome) welcome else frames.anchor(it, scroll.value) })
        }
        CompositionLocalProvider(LocalMaterialBackdrop provides backdrop) { menus.content?.invoke() }
    }
}

/** The page the invitation's arrival shows: the welcome, the sign-in, or the acceptance for someone signed in. */
private enum class InvitationPage(val screen: ArrivalScreen) { Welcome(ArrivalScreen.Welcome), SignIn(ArrivalScreen.SignIn), Acceptance(ArrivalScreen.SignIn) }

/**
 * A change of the invitation's page: the page [from] fading out over [out] (gone at once without it), and [to] fading
 * in over [into] (there at once without it), untouchable and out of TalkBack's reach while it goes.
 */
@Stable
private class PageChange(val from: InvitationPage?, val to: InvitationPage, val out: FiniteAnimationSpec<Float>?, val into: FiniteAnimationSpec<Float>?) {
    val leaving = Animatable(1f)
    val coming = Animatable(if (into == null) 1f else 0f)
    var gone by mutableStateOf(from == null)
}

/**
 * `ArrivalView` (C-S01) with a friend's invitation, which RootView shows over everything while one is presented
 * (`ArrivalView().id(invitation.presentationID)`): the welcome ([InvitationWelcome], C-S02) until the parcel is
 * opened, then the sign-in with the invitation ([SignInView], `friends.signInToAccept`) for someone signed out, or the
 * acceptance ([FriendInvitationAcceptance], C-S13) for someone signed in; signing in from the sign-in keeps the
 * invitation and lands on the acceptance. One parcel stays alive across them as [ArrivalView]'s does, with the sender's
 * card inside.
 *
 * The welcome greets with its parcel (`ParcelGreeting`: up 9 and back, swaying, every 4.6 s from 250 ms, while it
 * shows, isn't opening, the app is in front and Reduce Motion is off), presses it in (`.spring(response: 0.3,
 * dampingFraction: 0.64)`) and, tapped, unwraps it (`unwrap()`: a soft tap, the parcel opening over 0.95 s) and 980 ms
 * later (150 under Reduce Motion) opens the invitation (`opened`, with a light tap), the welcome fading out over 0.45 s
 * (0.15) as the next page fades in (`.easeOut(duration: 0.35).delay(0.25)`, 0.15 without the delay). The back
 * (`goBack()`) shuts it again the same way, but once the receipt has come. ✕ (`dismissInvitation()`) forgets the
 * invitation, and shows the welcome where nobody was signed in or in the demo, as does the acceptance's Close.
 * Android's back on the welcome does what ✕ does.
 *
 * The sender is asked while the app is in front (`.task(id: invitation.isPresenting && scenePhase == .active)`).
 * Once accepted, the receipt's stamp lands on the parcel 250 ms later (`.spring(response: 0.32, dampingFraction: 0.62)`,
 * or 30 ms and `.easeOut(duration: 0.12)` under Reduce Motion) with a soft tap 210 ms (80) after, and 450 ms (180)
 * later the friend is revealed with the circle the acceptance answered while in front (`finishReceipt()`) and the
 * invitation finishes, which brings the friends tab forward; out of the front it finishes at once. The profile sheet
 * comes over the window ([TabOverlay]), a form where the passport's rule says. Device-motion tilt isn't drawn.
 */
@Composable
internal fun InvitationArrival(source: DeliveriesSource, state: AuthenticationState, session: SessionStore, work: CoroutineScope) {
    val invitation = source.invitation
    val reduceMotion = PeekTheme.reduceMotion
    val opened by invitation.openedFlow.collectAsState()
    val nickname by invitation.nicknameFlow.collectAsState()
    val errorKey by invitation.errorKeyFlow.collectAsState()
    val receipt by invitation.receiptFlow.collectAsState()
    val active = LocalLifecycleOwner.current.lifecycle.currentStateAsState().value.isAtLeast(Lifecycle.State.STARTED)
    val stateNow by rememberUpdatedState(state)
    val activeNow by rememberUpdatedState(active)
    val screen = if (opened) ArrivalScreen.SignIn else ArrivalScreen.Welcome
    val page = when {
        !opened -> InvitationPage.Welcome
        state is AuthenticationState.SignedIn -> InvitationPage.Acceptance
        else -> InvitationPage.SignIn
    }
    var opening by rememberSaveable { mutableStateOf(false) }
    var pressed by remember { mutableStateOf(false) }
    var stampImpact by remember { mutableStateOf(false) }
    var greeting by remember { mutableIntStateOf(0) }
    val landing = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val welcomeScroll = rememberScrollState()
    val signInScroll = rememberScrollState()
    val frames = remember { ArrivalParcelFrames() }
    val menus = remember { SheetMenus() }
    val backdrop = rememberMaterialBackdrop()
    val window = LocalWindowLayers.current
    val overlay = remember(backdrop, window) { TabOverlay(backdrop, window, "invitation") }
    // The animation the next change of page is made with (`withAnimation`), taken by the change that shows it.
    val pending = remember { arrayOfNulls<FiniteAnimationSpec<Float>>(1) }
    val switch = Motion.easeInOut<Float>(if (reduceMotion) 0.15 else 0.45)

    fun unwrap() {
        if (opening || screen != ArrivalScreen.Welcome) return
        pressed = false
        opening = true
    }

    fun goBack() {
        if (invitation.receipt != null) return
        pending[0] = switch
        opening = false
        invitation.opened = false
    }

    fun dismiss() {
        invitation.dismiss()
        val now = stateNow
        if (now !is AuthenticationState.SignedIn && now != AuthenticationState.Demo) session.showWelcome()
    }

    fun finishReceipt() {
        val answer = invitation.receipt ?: return
        answer.acceptedFriend?.let { source.friendsActivity.reveal(it.id, if (activeNow) answer.snapshot else null) }
        invitation.finish()
    }

    fun retry() {
        scope.launch { invitation.loadPreview(signedIn = stateNow is AuthenticationState.SignedIn) }
    }

    LaunchedEffect(active) {
        if (active) invitation.loadPreview(signedIn = stateNow is AuthenticationState.SignedIn)
    }
    LaunchedEffect(opening && screen == ArrivalScreen.Welcome) {
        if (!opening || screen != ArrivalScreen.Welcome) return@LaunchedEffect
        delay(if (reduceMotion) 150L else 980L)
        pending[0] = switch
        invitation.opened = true
    }
    LaunchedEffect(receipt != null, active) {
        if (invitation.receipt == null) return@LaunchedEffect
        if (!active) return@LaunchedEffect finishReceipt()
        delay(if (reduceMotion) 30L else 250L)
        val land = if (reduceMotion) Motion.easeOut<Float>(0.12) else Motion.springWithResponse(0.32, 0.62)
        scope.launch { withContext(KeepMotion) { landing.animateTo(1f, land) } }
        delay(if (reduceMotion) 80L else 210L)
        stampImpact = true
        delay(if (reduceMotion) 180L else 450L)
        finishReceipt()
    }
    val greetingActive = screen == ArrivalScreen.Welcome && !opening && !reduceMotion && active
    LaunchedEffect(greetingActive) {
        if (!greetingActive) return@LaunchedEffect
        delay(250)
        while (true) {
            greeting += 1
            delay(4_600)
        }
    }
    val pose by keyframeAnimator(PeekKeyframes.Greeting(), greeting, still = reduceMotion) { PeekKeyframes.greeting }
    val press by animateFloatAsState(if (pressed && !reduceMotion) 1f else 0f, Motion.springWithResponse(0.3, 0.64), label = "press")
    SensoryFeedbackEffect(SensoryFeedback.FlexibleImpact(SensoryFeedback.FlexibleImpact.Flexibility.Soft, 0.45), stampImpact) { _, new -> new }
    SensoryFeedbackEffect(SensoryFeedback.FlexibleImpact(SensoryFeedback.FlexibleImpact.Flexibility.Soft, 0.65), opening) { _, new -> new }
    SensoryFeedbackEffect(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light, 0.35), screen) { old, new ->
        old == ArrivalScreen.Welcome && new == ArrivalScreen.SignIn
    }

    // The welcome fades in and out on the change's animation (`.transition(.opacity)`), the sign-in and the acceptance
    // come in on their own (`.opacity.animation(...)`) and go on the change's; a change without one is at once.
    val shown = remember { arrayOf(page) }
    val change = remember(page) {
        val animation = pending[0]
        pending[0] = null
        val from = shown[0]
        shown[0] = page
        if (from == page) {
            null
        } else {
            val into = when {
                page != InvitationPage.Welcome -> Motion.easeOut<Float>(if (reduceMotion) 0.15 else 0.35).delay(if (reduceMotion) 0.0 else 0.25)
                else -> animation
            }
            PageChange(from.takeIf { animation != null }, page, animation, into)
        }
    }
    LaunchedEffect(change) {
        val fading = change ?: return@LaunchedEffect
        withContext(KeepMotion) {
            launch { fading.into?.let { fading.coming.animateTo(1f, it) } }
            fading.out?.let { fading.leaving.animateTo(0f, it) }
        }
        fading.gone = true
    }
    val under = change?.takeUnless { it.gone }?.from
    val largeText = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val hinge = LocalWindowHinge.current
    val windowArrangement = LocalWindowArrangement.current
    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .background(PeekTheme.colors.background)
            .testTag(ARRIVAL)
            .onPlaced { frames.arrival = it },
    ) {
        val regularWidth = maxWidth >= REGULAR_WIDTH
        val regularHeight = maxHeight >= REGULAR_HEIGHT
        // The passport's rule: the profile's sheet is a form where the deliveries would stand side by side.
        val asForm = DeliveriesArrangement.SideBySide == if (hinge != null && regularWidth && regularHeight) {
            DeliveriesArrangement.SideBySide
        } else {
            DeliveriesArrangement.resolve(
                maxWidth.value.toDouble(), regularWidth, regularHeight, largeText, windowArrangement?.last ?: DeliveriesArrangement.Stacked,
            )
        }
        val covered = menus.content != null || overlay.covered
        Box(Modifier.fillMaxSize().materialBackdrop(backdrop), propagateMinConstraints = true) {
            Box(if (covered) Modifier.clearAndSetSemantics {} else Modifier, propagateMinConstraints = true) {
                for (drawn in listOfNotNull(under, page)) {
                    key(drawn) {
                        val leaving = drawn == under
                        val fade = change
                        val modifier = when {
                            fade == null -> Modifier
                            leaving -> Modifier
                                .graphicsLayer { alpha = fade.leaving.value }
                                .clearAndSetSemantics {}
                                .pointerInput(Unit) { awaitPointerEventScope { while (true) awaitPointerEvent(PointerEventPass.Initial).changes.forEach { it.consume() } } }
                            else -> Modifier.graphicsLayer { alpha = fade.coming.value }
                        }
                        // `anchorPreference` goes with its view: a page gone takes its anchor (the sign-in's, as the
                        // acceptance replaces it, before the receipt puts its own there).
                        DisposableEffect(drawn) { onDispose { if (shown[0] != drawn) frames.forget(drawn.screen) } }
                        Box(modifier, propagateMinConstraints = true) {
                            CompositionLocalProvider(LocalTabOverlay provides overlay) {
                                when (drawn) {
                                    InvitationPage.Welcome -> {
                                        BackHandler(enabled = !leaving) { dismiss() }
                                        InvitationWelcome(
                                            invitation, welcomeScroll, menus, opening, onOpen = ::unwrap, onPress = { pressed = it },
                                            onDismiss = ::dismiss, onRetry = ::retry,
                                            onAnchor = { frames.place(ArrivalScreen.Welcome, it, welcomeScroll) },
                                        )
                                    }
                                    InvitationPage.SignIn -> SignInView(
                                        session, signInScroll, menus, work, onBack = ::goBack,
                                        onAnchor = { frames.place(ArrivalScreen.SignIn, it, signInScroll) },
                                        invitation = SignInInvitation(nickname, errorKey, retry = ::retry),
                                    )
                                    InvitationPage.Acceptance -> FriendInvitationAcceptance(
                                        source, signInScroll, menus, active, asForm, onBack = ::goBack,
                                        onAnchor = { frames.place(ArrivalScreen.SignIn, it, signInScroll) },
                                    )
                                }
                            }
                        }
                    }
                }
            }
            ArrivalParcel(
                screen, frames, { frames.anchor(it, if (it == ArrivalScreen.Welcome) welcomeScroll.value else signInScroll.value) },
                opening = opening, pressed = press, celebrating = opening, pose = if (reduceMotion) PeekKeyframes.Greeting() else pose,
                sender = nickname,
                stamp = if (receipt == null) null else { { ReceiptStampLanding({ landing.value }, reduceMotion) } },
            )
        }
        CompositionLocalProvider(LocalMaterialBackdrop provides backdrop) { menus.content?.invoke() }
        Box(if (menus.content != null) Modifier.clearAndSetSemantics {} else Modifier) {
            CompositionLocalProvider(LocalWindowOverlay provides overlay) { overlay.Content() }
        }
    }
}

/**
 * The receipt's stamp over the parcel's frame, centred, 28 right: [landing] of the way from where it is dropped
 * (22° anticlockwise, 1.7 times as large, 80 up, unseen) to where it lands (9° anticlockwise, its size, 34 down,
 * seen); under Reduce Motion it lands at its size and place, turning and coming into sight.
 */
@Composable
private fun BoxScope.ReceiptStampLanding(landing: () -> Float, reduceMotion: Boolean) {
    FriendshipReceiptStamp(
        Modifier.align(Alignment.Center).graphicsLayer {
            val land = landing()
            translationX = 28.dp.toPx()
            translationY = (if (reduceMotion) 34f else -80f + 114f * land).dp.toPx()
            rotationZ = -22f + 13f * land
            val scale = if (reduceMotion) 1f else 1.7f - 0.7f * land
            scaleX = scale
            scaleY = scale
            alpha = land.coerceIn(0f, 1f)
        },
    )
}

/**
 * Where the parcel stands: the arrival's own coordinates, each screen's anchor as it stands with its page scrolled to
 * its top (so that a scroll moves it without a pass of layout), and where it was last drawn.
 */
@Stable
private class ArrivalParcelFrames {
    var arrival: LayoutCoordinates? = null
    var signIn: Rect? by mutableStateOf(null)
    var welcome: Rect? by mutableStateOf(null)

    /** Where the parcel was drawn last, which it springs from as the screen changes; null where it wasn't drawn. */
    var placed: Rect? = null

    /** [screen]'s page put its anchor at [anchor], scrolled [scroll] down (`anchorPreference`). */
    fun place(screen: ArrivalScreen, anchor: LayoutCoordinates, scroll: ScrollState) {
        val arrival = arrival?.takeIf { it.isAttached && anchor.isAttached } ?: return
        val rect = arrival.localBoundingBoxOf(anchor, clipBounds = false).translate(Offset(0f, scroll.value.toFloat()))
        if (screen == ArrivalScreen.SignIn) signIn = rect else welcome = rect
    }

    /** [screen]'s page has gone, and its anchor with it. */
    fun forget(screen: ArrivalScreen) {
        if (screen == ArrivalScreen.SignIn) signIn = null else welcome = null
    }

    /** The anchor of [screen] with its page scrolled [scroll] down. */
    fun anchor(screen: ArrivalScreen, scroll: Int): Rect? =
        (if (screen == ArrivalScreen.SignIn) signIn else welcome)?.translate(Offset(0f, -scroll.toFloat()))
}

/** A spring of the parcel [from] where it stood toward the anchor of the screen shown; none from nowhere. */
private class ParcelMove(val from: Rect?) {
    val progress = Animatable(if (from == null) 1f else 0f)
}

/**
 * The overlay's parcel: `UnwrappingParcel` in its 300 × 310 frame scaled to the anchor's width about its centre, which
 * stands at the anchor's, clipped to the safe area as SwiftUI's overlay is to the arrival's. It moves with the page's
 * scroll in the same frame, and springs to the other anchor as the screen changes. It is open on the sign-in (`reveal`
 * is 1 there) and shut on the welcome (0), and goes from one to the other on that spring, as `open` is its animatable
 * data and the overlay's `.animation(_:value: screen)` puts the spring in place of the change's own animation: the
 * back shuts it as it moves while the arrival fades. Under Reduce Motion, where that animation is none, it moves and
 * shuts at once.
 *
 * With an invitation it opens on the welcome too as the parcel is unwrapped ([opening], `.easeInOut(duration: 0.95)`,
 * at once under Reduce Motion), and carries the welcome's press ([pressed]), its greeting ([pose]), its sender and the
 * receipt's [stamp] over it. Where the screen shown leaves no anchor (the acceptance before its receipt) it isn't drawn,
 * and comes in where the anchor next stands without a spring.
 */
@Composable
private fun ArrivalParcel(
    screen: ArrivalScreen,
    frames: ArrivalParcelFrames,
    anchor: (ArrivalScreen) -> Rect?,
    opening: Boolean = false,
    pressed: Float = 0f,
    celebrating: Boolean = false,
    pose: PeekKeyframes.Greeting = PeekKeyframes.Greeting(),
    sender: String? = null,
    stamp: (@Composable BoxScope.() -> Unit)? = null,
) {
    val reduceMotion = PeekTheme.reduceMotion
    // The spring starts in the frame the screen changes in, from where the parcel was last drawn: made as the new
    // screen is composed, so that frame draws it where it was rather than at the new anchor.
    val shown = remember { arrayOf(screen) }
    val move = remember(screen) {
        val from = frames.placed.takeIf { shown[0] != screen && !reduceMotion }
        shown[0] = screen
        ParcelMove(from)
    }
    // `reveal`, on the move's spring: `.animation(_:value: screen)` stands over UnwrappingParcel as over its position.
    val reveal = if (opening || screen == ArrivalScreen.SignIn) 1f else 0f
    val open = remember { Animatable(reveal) }
    val moved = remember { arrayOfNulls<ParcelMove>(1) }
    LaunchedEffect(move, reveal) {
        if (moved[0] !== move) {
            moved[0] = move
            if (move.from == null) return@LaunchedEffect open.snapTo(reveal)
            launch { open.animateTo(reveal, Motion.spring(duration = 0.75, bounce = 0.08)) }
            move.progress.animateTo(1f, Motion.spring(duration = 0.75, bounce = 0.08))
        } else {
            // `unwrap()`: the parcel opens where it stands, the screen unchanged.
            if (move.progress.value < 1f) launch { move.progress.animateTo(1f, Motion.spring(duration = 0.75, bounce = 0.08)) }
            if (reduceMotion) open.snapTo(reveal) else open.animateTo(reveal, Motion.easeInOut(0.95))
        }
    }
    val safe = WindowInsets.safeDrawing
    val direction = LocalLayoutDirection.current
    val probe = LocalArrivalParcel.current
    Box(
        Modifier
            .fillMaxSize()
            .drawWithContent {
                clipRect(
                    left = safe.getLeft(this, direction).toFloat(),
                    top = safe.getTop(this).toFloat(),
                    right = size.width - safe.getRight(this, direction),
                    bottom = size.height - safe.getBottom(this),
                ) { this@drawWithContent.drawContent() }
            }
            .layout { measurable, constraints ->
                val frame = measurable.measure(Constraints.fixed(PARCEL_WIDTH.dp.roundToPx(), PARCEL_HEIGHT.dp.roundToPx()))
                layout(constraints.maxWidth, constraints.maxHeight) {
                    val target = anchor(screen)
                    if (target == null) {
                        frames.placed = null
                        return@layout
                    }
                    val from = move.from
                    val rect = if (from != null) lerp(from, target, move.progress.value) else target
                    frames.placed = rect
                    probe?.invoke(rect, open.value)
                    val scale = rect.width / frame.width
                    frame.placeWithLayer((rect.center.x - frame.width / 2f).roundToInt(), (rect.center.y - frame.height / 2f).roundToInt()) {
                        scaleX = scale
                        scaleY = scale
                    }
                }
            },
    ) {
        Box(Modifier.testTag(ARRIVAL_PARCEL).clearAndSetSemantics {}) {
            UnwrappingParcel(
                open = open.value.coerceIn(0f, 1f).toDouble(), label = null, ribbon = false, celebrating = celebrating, pressed = pressed,
                sender = sender, lift = pose.lift.toFloat(), sway = pose.angle.toFloat(),
            )
            stamp?.invoke(this)
        }
    }
}

/**
 * Told where the parcel is drawn each time it is placed, in the arrival's pixels, and how far open it is: the tests
 * follow its move.
 */
internal val LocalArrivalParcel = staticCompositionLocalOf<((Rect, Float) -> Unit)?> { null }

internal const val ARRIVAL_PARCEL = "arrivalParcel"

/**
 * Where WelcomeView (C-S02, with no invitation) puts its parcel's anchor in an arrival [width] × [height] pixels, worked
 * out from its layout rather than drawn: in the safe area, a column 520 wide at most less its 24 a side, 16 from the
 * top, the top bar (the lockup, capped at accessibility 1, and the sign-in pill, at least 44), the room above the title
 * (36 at least), the title (large title, rounded, bold, tightened 1.2, 8 in on each side), the subtitle 18 under it
 * (subheadline), then 12 down the anchor, as wide as the window less 48 and 300 at most, and 31/30 as tall, over the
 * caption 8 under it (medium subheadline, 44 at least), the room under it (64 at least) and 16. The two rooms share
 * what the window leaves alike, as a VStack shares it between its spacers, each keeping its least; where nothing is
 * left the page scrolls, from its top.
 */
@Composable
private fun rememberWelcomeAnchor(width: Int, height: Int): Rect {
    val density = LocalDensity.current
    val direction = LocalLayoutDirection.current
    val type = PeekTheme.type
    val measurer = rememberTextMeasurer()
    val safe = WindowInsets.systemBars.union(WindowInsets.displayCutout)
    val left = safe.getLeft(density, direction)
    val top = safe.getTop(density)
    val right = safe.getRight(density, direction)
    val bottom = safe.getBottom(density)
    val title = localized("arrival.welcomeTitle")
    val subtitle = localized("arrival.welcomeSubtitle")
    val caption = localized("arrival.tapToOpen")
    val signIn = localized("arrival.signInTitle")
    val name = localized("app.title")
    val tagline = localized("native.tagline")
    return remember(width, height, left, top, right, bottom, type, title, subtitle, caption, signIn, name, tagline) {
        with(density) {
            fun tall(text: String, style: TextStyle, within: Float = Float.POSITIVE_INFINITY): Float {
                val constraints = if (within.isFinite()) Constraints(maxWidth = max(0, within.roundToInt())) else Constraints()
                return measurer.measure(text, style, constraints = constraints).size.height.toFloat()
            }
            val wide = (width - left - right).toFloat()
            val high = (height - top - bottom).toFloat()
            val column = min(520.dp.toPx(), wide) - 48.dp.toPx()
            val lockupType = PeekTypography(type.size.capped(DynamicTypeSize.Accessibility1), density, type.language)
            val lockup = max(
                28.dp.toPx(),
                tall(name, lockupType.tracking(lockupType.fixed(lockupType.scaled(16f, IosTextStyle.Subheadline), FontWeight.Bold), -0.4f)) +
                    tall(tagline, lockupType.fixed(lockupType.scaled(10f, IosTextStyle.Caption2))),
            )
            val bar = maxOf(44.dp.toPx(), tall(signIn, type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines()), lockup)
            val heading = tall(title, type.tracking(type.style(IosTextStyle.LargeTitle, FontWeight.Bold, FontDesign.Rounded), -1.2f).sfLines(), column - 16.dp.toPx())
            val lead = tall(subtitle, type.subheadline.sfLines(), column)
            val side = max(0f, min(300.dp.toPx(), wide - 48.dp.toPx()))
            val anchor = side * 31f / 30f
            val foot = max(44.dp.toPx(), tall(caption, type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines(), column))
            val fixed = 16.dp.toPx() + bar + heading + 18.dp.toPx() + lead + 12.dp.toPx() + anchor + 8.dp.toPx() + foot + 16.dp.toPx()
            val room = high - fixed
            val above = if (room / 2 >= 64.dp.toPx()) room / 2 else max(36.dp.toPx(), room - 64.dp.toPx())
            val y = top + 16.dp.toPx() + bar + above + heading + 18.dp.toPx() + lead + 12.dp.toPx()
            Rect(Offset(left + (wide - side) / 2, y), androidx.compose.ui.geometry.Size(side, anchor))
        }
    }
}

private const val PARCEL_WIDTH = 300
private const val PARCEL_HEIGHT = 310
