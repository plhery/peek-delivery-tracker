package com.plhery.peekdeliverytracker.ui.arrival

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
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
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.ui.ARRIVAL
import com.plhery.peekdeliverytracker.ui.RootTransaction
import com.plhery.peekdeliverytracker.ui.deliveries.UnwrappingParcel
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.sheet.SheetMenus
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.KeepMotion
import com.plhery.peekdeliverytracker.ui.theme.LocalMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import com.plhery.peekdeliverytracker.ui.theme.delay
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlinx.coroutines.CoroutineScope
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
                        SignInView(session, scroll, menus, work, onBack = goBack, onAnchor = { frames.place(it, scroll) })
                    }
                }
            }
            ArrivalParcel(screen, frames, scroll, welcome)
        }
        CompositionLocalProvider(LocalMaterialBackdrop provides backdrop) { menus.content?.invoke() }
    }
}

/**
 * Where the parcel stands: the arrival's own coordinates, the sign-in's anchor as it stands with the page scrolled to
 * its top (so that a scroll moves it without a pass of layout), and where it was last drawn.
 */
@Stable
private class ArrivalParcelFrames {
    var arrival: LayoutCoordinates? = null
    var signIn: Rect? by mutableStateOf(null)

    /** Where the parcel was drawn last, which it springs from as the screen changes. */
    var placed: Rect? = null

    fun place(anchor: LayoutCoordinates, scroll: ScrollState) {
        val arrival = arrival?.takeIf { it.isAttached && anchor.isAttached } ?: return
        signIn = arrival.localBoundingBoxOf(anchor, clipBounds = false).translate(Offset(0f, scroll.value.toFloat()))
    }

    /** The anchor of [screen], the welcome's being [welcome], with the page scrolled [scroll] down. */
    fun anchor(screen: ArrivalScreen, scroll: Int, welcome: Rect): Rect? = when (screen) {
        ArrivalScreen.SignIn -> signIn?.translate(Offset(0f, -scroll.toFloat()))
        ArrivalScreen.Welcome -> welcome
    }
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
 */
@Composable
private fun ArrivalParcel(screen: ArrivalScreen, frames: ArrivalParcelFrames, scroll: ScrollState, welcome: Rect) {
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
    val reveal = if (screen == ArrivalScreen.SignIn) 1f else 0f
    val open = remember { Animatable(reveal) }
    LaunchedEffect(move) {
        if (move.from == null) return@LaunchedEffect open.snapTo(reveal)
        launch { open.animateTo(reveal, Motion.spring(duration = 0.75, bounce = 0.08)) }
        move.progress.animateTo(1f, Motion.spring(duration = 0.75, bounce = 0.08))
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
                    val target = frames.anchor(screen, scroll.value, welcome) ?: return@layout
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
            UnwrappingParcel(open = open.value.coerceIn(0f, 1f).toDouble(), label = null, ribbon = false)
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
