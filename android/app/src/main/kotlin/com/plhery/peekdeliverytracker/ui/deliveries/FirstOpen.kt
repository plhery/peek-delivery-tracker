package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.FloatState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.currentStateAsState
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.EdgeInsets
import com.plhery.peekdeliverytracker.core.FirstOpenJourney
import com.plhery.peekdeliverytracker.core.FirstParcelRequest
import com.plhery.peekdeliverytracker.core.WorldAtlas
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.SystemPress
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.CarrierFleetMark
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.DeliveryPostageStamp
import com.plhery.peekdeliverytracker.ui.draw.PeekLockup
import com.plhery.peekdeliverytracker.ui.draw.UnevenContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.map.RouteEngraving
import com.plhery.peekdeliverytracker.ui.map.rememberParcelRoute
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowHinge
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowLevelHinge
import com.plhery.peekdeliverytracker.ui.theme.CappedDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.Radii
import com.plhery.peekdeliverytracker.ui.theme.carrierIdentity
import com.plhery.peekdeliverytracker.ui.zoom.ZoomStack
import com.plhery.peekdeliverytracker.ui.zoom.ZoomTransition
import com.plhery.peekdeliverytracker.ui.zoom.zoomSource
import java.util.UUID
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlinx.coroutines.delay

/**
 * What TabsView keeps of the first open over its tabs, each held for the launch ([rememberForLaunch]) where the iPhone
 * keeps it in `@State`: whether it stands over the tabs (`showsFirstOpen`), whether its sample parcel's page shows
 * (`showingSample`), and Add open over the deliveries (`showingAdd`), which is the deliveries' own ([adding],
 * [PromptStates.adding]). The first parcel it asks for (`firstParcel`, [ask]) chooses the deliveries' tab, where Add has
 * its host, and the deliveries open Add for it ([take]); nothing else it does changes the tab.
 */
@Stable
internal class FirstOpen(shownState: MutableState<Boolean>, sampleState: MutableState<Boolean>, val adding: MutableState<String?>) {
    /**
     * Whether it stands over the tabs, kept with the window: one shown while Add adds the first parcel stays until Add
     * has gone ([shows]).
     */
    var shown: Boolean by shownState

    /** Whether its sample parcel's page shows, in a stack of its own over it. */
    var sampleShown: Boolean by sampleState

    /** The zoom from its picture to its sample parcel's page. */
    val zoom = ZoomTransition()

    /** Chooses the deliveries' tab, as the shell's selection: Add is theirs. */
    var chooseDeliveries: () -> Unit = {}

    /** The first parcel it asked for, until the deliveries take it. */
    var asked: FirstParcelAsk? by mutableStateOf(null)
        private set

    /** Add is open over the deliveries. */
    val addOpen: Boolean get() = adding.value != null

    /**
     * Whether it stands over the tabs for a reader who is a `guest` with an `empty` list or not: TabsView's rule. It
     * comes back when the last parcel is forgotten; it leaves when the first parcel is in, once Add has gone, so that
     * the list is seen receiving it.
     */
    fun shows(guest: Boolean, empty: Boolean): Boolean = guest && (empty || (addOpen && shown))

    /**
     * `firstParcel = request`: the deliveries' tab is chosen and Add opens there for [request], TalkBack going back to
     * [focus] once it has gone. Nothing while Add is up.
     */
    fun ask(request: FirstParcelRequest, focus: AccessibilityFocus?) {
        if (addOpen) return
        chooseDeliveries()
        asked = FirstParcelAsk(request, focus)
    }

    /** The first parcel asked for, taken once (`firstParcel = nil`). */
    fun take(): FirstParcelAsk? = asked.also { asked = null }
}

/** A first parcel asked for from the first open, and where TalkBack goes back to once Add has gone. */
internal class FirstParcelAsk(val request: FirstParcelRequest, val focus: AccessibilityFocus?)

@Composable
internal fun rememberFirstOpen(launch: UUID): FirstOpen {
    val shown = rememberForLaunch(launch, false)
    val sample = rememberForLaunch(launch, false)
    val adding = rememberForLaunch<String?>(launch, null)
    return remember(shown, sample, adding) { FirstOpen(shown, sample, adding) }
}

/** The shell's first open, which the deliveries' tab opens Add for; null outside a shell. */
internal val LocalFirstOpen = staticCompositionLocalOf<FirstOpen?> { null }

/**
 * TabsView's `showsFirstOpen` for [session]: a guest with no parcels, or one whose first parcel Add is adding from it.
 * Kept with the window as it is composed ([FirstOpen.shown]).
 */
@Composable
internal fun FirstOpen.covers(source: DeliveriesSource, session: AuthenticationState): Boolean {
    val parcels by source.parcels.collectAsState()
    val shows = shows(guest = session == AuthenticationState.Welcome, empty = parcels.isEmpty())
    SideEffect { shown = shows }
    return shows
}

/**
 * TabsView's first open (A-S08): FirstOpenView over the tabs and their bar while it [shows], for a guest who follows
 * nothing (`FirstOpenPresence`), whichever tab is chosen. It stays while Add, opened from it, adds the first parcel, and
 * leaves once Add has gone, so that the list is seen receiving it; it comes back when the last parcel is forgotten. At
 * once on a launch; otherwise it comes in fading and leaves moving up off the top as it fades, over
 * `.smooth(duration: 0.55)`, and with neither under Reduce Motion. On its way out it takes no touch, as SwiftUI stops
 * hit-testing a view it removes.
 */
@Composable
internal fun FirstOpenHost(firstOpen: FirstOpen, source: DeliveriesSource, shows: Boolean) {
    val presence = remember { MutableTransitionState(shows) }
    presence.targetState = shows
    if (!presence.currentState && !presence.targetState) return
    val reduceMotion = PeekTheme.reduceMotion
    AnimatedVisibility(
        presence,
        enter = if (reduceMotion) EnterTransition.None else fadeIn(Motion.smooth(PRESENCE_DURATION)),
        exit = if (reduceMotion) {
            ExitTransition.None
        } else {
            slideOutVertically(Motion.smooth(PRESENCE_DURATION)) { -it } + fadeOut(Motion.smooth(PRESENCE_DURATION))
        },
    ) {
        val leaving = transition.targetState == EnterExitState.PostExit
        Box(if (leaving) Untouched else Modifier, propagateMinConstraints = true) { FirstOpenView(firstOpen, source) }
    }
}

/**
 * FirstOpenView (A-S09): the sample parcel's journey on its map and the question, laid out as the window has room for
 * ([FirstOpenLayouts]), text no larger than accessibility 1's. Its picture opens the sample parcel's page in the first
 * open's own stack (`navigationDestination`), zooming out of it, and the page goes back to the first open, not to the
 * deliveries under it. The page is drawn from a state of the first open's own, as FirstOpenView's stack has its own.
 *
 * The journey (`running`) moves on a scan at a time, each held `FirstOpenJourney.hold(step)`, changing over
 * `.easeInOut(duration: 0.5)`, while the app is in front, Add doesn't cover it, the atlas is read, no parcel link's
 * sheet is up and the sample's page isn't open; never under Reduce Motion, which shows its still step. Once the atlas
 * is read it starts from its first scan and everything comes in on a spring of 0.7 s with a bounce of 0.12 (at once
 * under Reduce Motion), the picture from above and the question from below.
 */
@Composable
private fun FirstOpenView(firstOpen: FirstOpen, source: DeliveriesSource) {
    CappedDynamicTypeSize(DynamicTypeSize.Accessibility1) {
        val state = rememberDeliveriesState(source, firstOpen, rememberForLaunch<String?>(source.launch, null))
        val reduceMotion = PeekTheme.reduceMotion
        var step by rememberSaveable { mutableIntStateOf(FirstOpenJourney.stillStep) }
        var appeared by rememberSaveable { mutableStateOf(false) }
        val appearance = remember { Animatable(if (appeared) 1f else 0f) }
        val atlas = rememberWorldAtlas()
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        val resumed = lifecycle.currentStateAsState().value.isAtLeast(Lifecycle.State.RESUMED)
        val linkOpen = LocalParcelLinkOpen.current
        val running = !reduceMotion && resumed && !firstOpen.addOpen && atlas != null && !linkOpen && !firstOpen.sampleShown
        LaunchedEffect(atlas != null) {
            if (atlas == null || appeared) return@LaunchedEffect
            // The story starts from its first scan; a still picture shows the parcel a day away.
            if (!reduceMotion) step = 1
            appeared = true
            if (reduceMotion) appearance.snapTo(1f) else appearance.animateTo(1f, Motion.spring(APPEAR_DURATION, APPEAR_BOUNCE))
        }
        LaunchedEffect(running) {
            if (!running) return@LaunchedEffect
            while (true) {
                delay((FirstOpenJourney.hold(step) * 1000).roundToLong())
                step = step % FirstOpenJourney.steps + 1
            }
        }
        LaunchedEffect(reduceMotion) { if (reduceMotion) step = FirstOpenJourney.stillStep }

        val language = LocalLanguage.current
        val identity = carrierIdentity(CarrierId.Dhl, language)
        val stack = remember(state) { PageStack(firstOpen.zoom, state::closeFirstSample) }
        Box(Modifier.fillMaxSize().background(PeekTheme.colors.background).semantics { journeyStep = step }) {
            if (identity == null) return@Box
            val scene = rememberScene(state, firstOpen, identity, step, atlas, appeared, appearance, running, language)
            ZoomStack(
                transition = firstOpen.zoom,
                top = if (firstOpen.sampleShown) FirstOpenJourney.parcelId else null,
                onBack = state::closeFirstSample,
                root = { FirstOpenLayouts(scene) },
                page = { CompositionLocalProvider(LocalPageStack provides stack) { ParcelPage(state, FirstOpenJourney.parcelId) } },
                bar = { CompositionLocalProvider(LocalPageStack provides stack) { ParcelPageBar(state, FirstOpenJourney.parcelId) } },
            )
        }
    }
}

/** What every part of the first open draws: the sample parcel at the journey's step, in DHL's colours. */
private class FirstOpenScene(
    /** The first open's own state, which its sample's page is drawn from. */
    val state: DeliveriesState,
    val firstOpen: FirstOpen,
    val identity: CarrierVisualIdentity,
    val step: Int,
    val parcel: Parcel,
    val atlas: WorldAtlas?,
    val appeared: Boolean,
    val appearance: Animatable<Float, *>,
    val running: Boolean,
    val language: AppLanguage,
    /** `store.tracksWithoutAccount`: the field and Paste and track, or the demo where the build has no server. */
    val tracks: Boolean,
    /** `status(_:)` at each step: the parcel's status and when it comes, " · " between them. */
    val status: (Int) -> String,
    /** `FirstOpenJourney.ping(step:)` at each step. */
    val ping: (Int) -> String?,
)

@Composable
private fun rememberScene(
    state: DeliveriesState,
    firstOpen: FirstOpen,
    identity: CarrierVisualIdentity,
    step: Int,
    atlas: WorldAtlas?,
    appeared: Boolean,
    appearance: Animatable<Float, *>,
    running: Boolean,
    language: AppLanguage,
): FirstOpenScene {
    val localizer = LocalLocalizer.current
    val now = LocalNow.current
    val name = remember(localizer, language) { FirstOpenJourney.name(localizer) }
    // Made once a step, so that its route stays the same object between steps.
    val parcels = remember(name) { HashMap<Int, Parcel>() }
    fun parcel(at: Int) = parcels.getOrPut(at) { FirstOpenJourney.parcel(at, name, now()) }
    val statuses = remember(parcels, localizer, language) { HashMap<Int, String>() }
    val status = { at: Int ->
        statuses.getOrPut(at) {
            val parcel = parcel(at)
            listOfNotNull(localizer.parcelStatus(parcel), localizer.parcelDeliveryEstimate(parcel, now())).joinToString(" · ")
        }
    }
    val ping = { at: Int -> FirstOpenJourney.ping(at, localizer) }
    return FirstOpenScene(
        state, firstOpen, identity, step, parcel(step), atlas, appeared, appearance, running, language, state.source.tracksWithoutAccount,
        status, ping,
    )
}

/**
 * The layout FirstOpenView's body picks. A device standing half open (`window.fold`) parts it at the fold
 * ([FirstOpenSpread]). Otherwise the first that fits the height, as `ViewThatFits(in: .vertical)` picks: the poster
 * ([FirstOpenPoster]) where the window is at least 600 wide and the screen 600 tall; the page held still, the picture
 * over the question; the page scrolling. The picture is as tall as the question and its field leave, between 300 and a
 * phone's 446, or half a screen 1000 tall or more (`heroHeight`); the question is roomier on a screen 800 tall or more.
 * It runs under the status bar.
 */
@Composable
private fun FirstOpenLayouts(scene: FirstOpenScene) {
    val density = LocalDensity.current
    val direction = LocalLayoutDirection.current
    val safe = WindowInsets.systemBars.union(WindowInsets.displayCutout)
    val insets = with(density) {
        Safe(
            top = safe.getTop(this).toDp(), bottom = safe.getBottom(this).toDp(),
            start = safe.getLeft(this, direction).toDp(), end = safe.getRight(this, direction).toDp(),
        )
    }
    val upright = LocalWindowHinge.current?.takeIf { it.halfOpen }
    val level = LocalWindowLevelHinge.current?.takeIf { it.halfOpen }
    BoxWithConstraints(Modifier.fillMaxSize().testTag(FIRST_OPEN)) {
        val screen = maxHeight
        val width = maxWidth - insets.start - insets.end
        val heroHeight = minOf(if (screen >= 1000.dp) screen / 2 else 446.dp, maxOf(300.dp, screen - 404.dp))
        val roomy = screen >= 800.dp
        when {
            upright != null -> with(density) {
                FirstOpenSpread(scene, insets, screen, beside = true, upright.start.toDp(), upright.end.toDp())
            }
            level != null -> with(density) {
                FirstOpenSpread(scene, insets, screen, beside = false, level.top.toDp(), level.bottom.toDp())
            }
            else -> FirstOpenFits(scene, insets, heroHeight, roomy, poster = minOf(width, screen) >= 600.dp, width, screen)
        }
    }
}

/** The window's safe insets: the status bar and a cutout at the top, the navigation's at the bottom, and the sides. */
private data class Safe(val top: Dp, val bottom: Dp, val start: Dp, val end: Dp)

/** Which of the three layouts that fit the height the first open shows. */
private enum class FirstOpenFit { Poster, Page, Scroll }

/**
 * `ViewThatFits(in: .vertical)`: each layout's height as it would lay itself out with all the height it wants (the
 * poster's spacer at its 24, the question's at none) against the window's above its safe bottom, the first that fits.
 */
@Composable
private fun FirstOpenFits(scene: FirstOpenScene, insets: Safe, heroHeight: Dp, roomy: Boolean, poster: Boolean, width: Dp, screen: Dp) {
    SubcomposeLayout(Modifier.fillMaxSize()) { constraints ->
        val room = constraints.maxHeight - insets.bottom.roundToPx()
        // Each at the width it is drawn at: the poster across the window, keeping clear of its safe sides itself, and
        // the question between them.
        val window = Constraints(maxWidth = constraints.maxWidth)
        val between = Constraints(maxWidth = (constraints.maxWidth - insets.start.roundToPx() - insets.end.roundToPx()).coerceAtLeast(0))
        fun height(slot: FirstOpenFit, within: Constraints, content: @Composable () -> Unit): Int =
            subcompose("probe-$slot") { Box(Modifier.clearAndSetSemantics {}) { content() } }.maxOfOrNull { it.measure(within).height } ?: 0
        val fit = when {
            // The poster's height holds its safe bottom already.
            poster && height(FirstOpenFit.Poster, window) { PosterForeground(scene, insets, width, screen, probe = true) } <= constraints.maxHeight ->
                FirstOpenFit.Poster
            heroHeight.roundToPx() + height(FirstOpenFit.Page, between) { FirstOpenAsk(scene, roomy, probe = true) } <= room -> FirstOpenFit.Page
            else -> FirstOpenFit.Scroll
        }
        val placeables = subcompose(fit) {
            when (fit) {
                FirstOpenFit.Poster -> FirstOpenPoster(scene, insets, width, screen)
                FirstOpenFit.Page -> FirstOpenPage(scene, insets, heroHeight, roomy, scrolls = false)
                FirstOpenFit.Scroll -> FirstOpenPage(scene, insets, heroHeight, roomy, scrolls = true)
            }
        }.map { it.measure(Constraints.fixed(constraints.maxWidth, constraints.maxHeight)) }
        layout(constraints.maxWidth, constraints.maxHeight) { placeables.forEach { it.place(0, 0) } }
    }
}

/**
 * `page`: the picture over the question, held still with the question taking the rest of the screen, or scrolling
 * ([scrolls]) where the screen is too short for both, as a phone on its side is.
 */
@Composable
private fun FirstOpenPage(scene: FirstOpenScene, insets: Safe, heroHeight: Dp, roomy: Boolean, scrolls: Boolean) {
    val appearance = scene.appearance
    Column(
        Modifier
            .fillMaxSize()
            .then(if (scrolls) Modifier.verticalScroll(rememberScrollState()) else Modifier)
            .padding(start = insets.start, end = insets.end, bottom = insets.bottom)
            .testTag(if (scrolls) FIRST_OPEN_SCROLL else FIRST_OPEN_PAGE),
    ) {
        FirstOpenHero(
            scene, insets.top, heroHeight, beside = false,
            Modifier.fillMaxWidth().height(heroHeight).appearing({ appearance.value }, dy = -APPEAR_RISE),
        )
        FirstOpenAsk(
            scene, roomy,
            modifier = (if (scrolls) Modifier else Modifier.weight(1f)).appearing({ appearance.value }, dy = APPEAR_FALL),
            fills = !scrolls,
        )
    }
}

/**
 * `spread(fold:)`: a device standing half open parts the screen at the fold, nothing across it. The picture takes the
 * part before it, as it leads a phone's screen, its corners on the fold's side rounded, under the status bar; the
 * question stands in the middle of the part after it, the carriers rolling at its foot. Beside an upright fold
 * ([beside]) the picture takes the whole height and comes in from the side.
 */
@Composable
private fun FirstOpenSpread(scene: FirstOpenScene, insets: Safe, screen: Dp, beside: Boolean, start: Dp, end: Dp) {
    val appearance = scene.appearance
    val ask = @Composable { modifier: Modifier ->
        FirstOpenAsk(scene, roomy = true, centred = true, fills = true, modifier = modifier.appearing({ appearance.value }, dy = APPEAR_FALL))
    }
    if (beside) {
        Row(Modifier.fillMaxSize().padding(start = insets.start, end = insets.end).testTag(FIRST_OPEN_SPREAD)) {
            FirstOpenHero(
                scene, insets.top, screen, beside = true,
                Modifier.width((start - insets.start).coerceAtLeast(0.dp)).fillMaxHeight().appearing({ appearance.value }, dx = -APPEAR_RISE),
            )
            Spacer(Modifier.width(end - start))
            ask(Modifier.weight(1f).fillMaxHeight().padding(top = insets.top, bottom = insets.bottom))
        }
    } else {
        Column(Modifier.fillMaxSize().padding(start = insets.start, end = insets.end).testTag(FIRST_OPEN_SPREAD)) {
            FirstOpenHero(
                scene, insets.top, start, beside = false,
                Modifier.fillMaxWidth().height(start).appearing({ appearance.value }, dy = -APPEAR_RISE),
            )
            Spacer(Modifier.height(end - start))
            ask(Modifier.weight(1f).fillMaxWidth().padding(bottom = insets.bottom))
        }
    }
}

/**
 * `hero(height:top:beside:)`: the sample parcel on its map in DHL's colours, its route below the masthead (112 under
 * the status bar) and Pip above its name, which stands at its foot with the carrier's mark, its status and the stamp.
 * Its bottom corners are rounded 38, or the corners on the fold's side ([beside]). Tapped, it opens the parcel's page,
 * zooming out of it, and gives a little under the finger (`FirstOpenHeroStyle`); TalkBack reads it as "Open a sample".
 * The masthead and, when a scan is worth one, the alert it would bring stand at its top.
 */
@Composable
private fun FirstOpenHero(scene: FirstOpenScene, top: Dp, height: Dp, beside: Boolean, modifier: Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val identity = scene.identity
    val shape = if (beside) {
        UnevenContinuousRoundedShape(bottomTrailing = Radii.heroBottom, topTrailing = Radii.heroBottom)
    } else {
        UnevenContinuousRoundedShape(bottomLeading = Radii.heroBottom, bottomTrailing = Radii.heroBottom)
    }
    val label = localized("landing.pip.open")
    Box(modifier) {
        Box(
            Modifier
                .fillMaxSize()
                .samplePicture(scene, label, shape)
                .clip(shape)
                .background(colors.resolve(identity.surface)),
            contentAlignment = Alignment.BottomStart,
        ) {
            Box(Modifier.fillMaxSize().clearAndSetSemantics {}) {
                SampleRoute(
                    scene, EdgeInsets(top = top.value + 112.0, leading = 88.0, bottom = 128.0, trailing = 54.0), floor = height.value - 118.0,
                )
            }
            Row(
                Modifier.fillMaxWidth().padding(start = 26.dp, end = 26.dp, bottom = 24.dp).clearAndSetSemantics {},
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                    CarrierFleetMark(identity)
                    Text(scene.parcel.label, color = colors.label, style = type.style(IosTextStyle.Title2, FontWeight.SemiBold))
                    StepStatus(scene, type.caption)
                }
                DeliveryPostageStamp(scene.parcel, identity, scene.appeared, width = 50.dp)
            }
        }
        Column(Modifier.fillMaxWidth().padding(start = 24.dp, top = top + 10.dp, end = 24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            FirstOpenMasthead(scene)
            StepPing(scene, rise = -PING_TRAVEL)
        }
    }
}

/**
 * The picture's button: it opens the sample parcel's page out of itself (`matchedTransitionSource`), its corners
 * [corners]'s (`.clipShape(corners)`), square where none are given, giving a little under the finger, read as [label]
 * alone.
 */
private fun Modifier.samplePicture(
    scene: FirstOpenScene,
    label: String,
    corners: UnevenContinuousRoundedShape = UnevenContinuousRoundedShape(),
): Modifier = this
    .zoomSource(FirstOpenJourney.parcelId, scene.firstOpen.zoom, corners)
    .testTag(FIRST_OPEN_PICTURE)
    .clickable(interactionSource = null, indication = PressStyle.FirstOpenHero, role = Role.Button, onClick = scene.state::openFirstSample)
    .semantics { contentDescription = label }

/**
 * `RouteEngraving` of the sample parcel at its step, once the atlas is read; no zoom under two fingers. Its halo pulses
 * only while the journey runs, as Core Animation's costs the iPhone no frame of its own.
 */
@Composable
private fun SampleRoute(scene: FirstOpenScene, insets: EdgeInsets, floor: Double) {
    val atlas = scene.atlas ?: return
    val route = rememberParcelRoute(scene.parcel, atlas, scene.language)
    RouteEngraving(
        atlas = atlas, route = route, stage = scene.parcel.currentStage, identity = scene.identity, language = scene.language,
        modifier = Modifier.fillMaxSize(), insets = insets, floor = floor, peek = false, running = scene.running,
    )
}

/**
 * The parcel's status at the journey's step, the next fading in over the last where they stand
 * (`.id(step).transition(.opacity)`), unclipped as its room changes over the step's `.easeInOut(duration: 0.5)`.
 */
@Composable
private fun StepStatus(scene: FirstOpenScene, style: TextStyle) {
    val ink = PeekTheme.colors.resolve(scene.identity.ink)
    val reduceMotion = PeekTheme.reduceMotion
    AnimatedContent(scene.step, transitionSpec = { stepStatusTransform(reduceMotion) }, label = "status") { step ->
        Text(scene.status(step), color = ink, style = style)
    }
}

/**
 * The alert a scan worth one would bring (`FirstOpenPing`), the next coming in from [rise] above or below as the last
 * leaves that way, fading, (`.transition(.offset(y:).combined(with: .opacity))`); nothing at a step without one. It
 * takes no touch: a tap on it reaches the picture. [bottom] is the room under it while it shows.
 */
@Composable
private fun StepPing(scene: FirstOpenScene, rise: Dp, bottom: Dp = 0.dp) {
    val reduceMotion = PeekTheme.reduceMotion
    val density = LocalDensity.current
    val travel = with(density) { rise.roundToPx() }
    AnimatedContent(scene.step, transitionSpec = { stepPingTransform(reduceMotion, travel) }, label = "ping") { step ->
        val ping = scene.ping(step) ?: return@AnimatedContent
        FirstOpenPing(ping, scene.identity, Modifier.padding(bottom = bottom))
    }
}

/**
 * The status's change from a step to the next: the next fades in over the last where they stand, over the step's
 * `.easeInOut(duration: 0.5)`, which their room changes over too, unclipped; at once under Reduce Motion.
 */
internal fun stepStatusTransform(reduceMotion: Boolean): ContentTransform = if (reduceMotion) {
    EnterTransition.None togetherWith ExitTransition.None
} else {
    ContentTransform(fadeIn(Motion.easeInOut(STEP_DURATION)), fadeOut(Motion.easeInOut(STEP_DURATION)), sizeTransform = stepSize())
}

/**
 * The alert's change from a step to the next: the next comes in from [travel] pixels away as the last leaves that way,
 * both fading, over the step's `.easeInOut(duration: 0.5)`, which their room changes over too, unclipped; at once under
 * Reduce Motion.
 */
internal fun stepPingTransform(reduceMotion: Boolean, travel: Int): ContentTransform = if (reduceMotion) {
    EnterTransition.None togetherWith ExitTransition.None
} else {
    val spec = Motion.easeInOut<Float>(STEP_DURATION)
    ContentTransform(
        fadeIn(spec) + slideInVertically(Motion.easeInOut(STEP_DURATION)) { travel },
        fadeOut(spec) + slideOutVertically(Motion.easeInOut(STEP_DURATION)) { travel },
        sizeTransform = stepSize(),
    )
}

/** A step's words take their new room over the step's curve, drawn whole meanwhile rather than cut to it. */
private fun stepSize() = SizeTransform(clip = false) { _, _ -> Motion.easeInOut(STEP_DURATION) }

/**
 * `FirstOpenPing`: what an alert would say, a bell 9 points tall and the words in caption medium on one line, shrinking
 * to 80 % rather than be cut, both in the carrier's ink on a capsule of it at 10 %, 26 tall. It belongs to the picture:
 * TalkBack doesn't read it.
 */
@Composable
private fun FirstOpenPing(detail: String, identity: CarrierVisualIdentity, modifier: Modifier = Modifier) {
    val type = PeekTheme.type
    val ink = PeekTheme.colors.resolve(identity.ink)
    Row(
        modifier
            .height(26.dp)
            .background(ink.copy(alpha = ink.alpha * 0.1f), CapsuleShape)
            .padding(horizontal = 10.dp)
            .clearAndSetSemantics {},
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeekSymbol("bell.fill", font = type.fixed(9f), tint = ink)
        val style = type.style(IosTextStyle.Caption, FontWeight.Medium)
        Text(
            detail, Modifier.weight(1f, fill = false), color = ink, style = style, maxLines = 1, softWrap = false,
            autoSize = TextAutoSize.StepBased(minFontSize = style.fontSize * PING_MINIMUM_SCALE, maxFontSize = style.fontSize),
        )
    }
}

/**
 * `masthead`: the lockup (`PeekLockup`), and at the other end Sign in in subheadline medium in the ink, at least 44
 * tall, 14 in from its ends, on the paper at 55 % with corners of 12, fading under the finger as a system button's
 * title does. It opens the sign-in screen.
 */
@Composable
private fun FirstOpenMasthead(scene: FirstOpenScene, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val source = scene.state.source
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        PeekLockup(Modifier.takesTaps())
        Spacer(Modifier.weight(1f).widthIn(min = 8.dp))
        Box(
            Modifier
                .heightIn(min = 44.dp)
                .background(colors.paper.copy(alpha = colors.paper.alpha * 0.55f), ContinuousRoundedShape(Radii.mastheadButton))
                .testTag(FIRST_OPEN_SIGN_IN)
                .clickable(interactionSource = null, indication = SystemPress, role = Role.Button) { source.showSignIn() }
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(localized("arrival.signInTitle"), color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium))
        }
    }
}

/**
 * `ask(roomy:centred:)`: Track any parcel. in large title rounded bold, tightened 1.2, in the ink, on at most two lines
 * shrinking to 70 % rather than be cut, a heading; under it the lead in subheadline; then the field over Paste and
 * track, or the demo where the build has no server. Roomy, it stands lower and further apart, the carriers rolling at
 * its foot. [centred] stands it in the middle of its room, and where it [fills] its room the carriers keep to its foot.
 * 24 in from the sides and no wider than 560, centred.
 */
@Composable
private fun FirstOpenAsk(
    scene: FirstOpenScene,
    roomy: Boolean,
    modifier: Modifier = Modifier,
    centred: Boolean = false,
    fills: Boolean = false,
    probe: Boolean = false,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(
        modifier.fillMaxWidth().wrapContentWidth().widthIn(max = 560.dp).padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (centred && fills) Spacer(Modifier.weight(1f))
        val title = type.tracking(type.style(IosTextStyle.LargeTitle, FontWeight.Bold, FontDesign.Rounded), -1.2f)
        Text(
            localized("peek.title"), Modifier.padding(top = if (roomy) 26.dp else 18.dp).semantics { heading() },
            color = colors.ink, style = title, textAlign = TextAlign.Center, maxLines = 2,
            autoSize = TextAutoSize.StepBased(minFontSize = title.fontSize * ASK_TITLE_MINIMUM_SCALE, maxFontSize = title.fontSize),
        )
        Text(
            localized(if (scene.tracks) "native.pasteLead" else "arrival.welcomeSubtitle"), Modifier.padding(top = 8.dp),
            color = colors.secondaryLabel, style = type.subheadline, textAlign = TextAlign.Center,
        )
        if (scene.tracks) {
            Column(Modifier.padding(top = if (roomy) 20.dp else 14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                FirstOpenField(scene, large = false)
                PasteButton(scene, large = false, Modifier.fillMaxWidth())
            }
        } else {
            DemoButton(scene, large = false, Modifier.padding(top = 20.dp).fillMaxWidth())
        }
        if (fills) Spacer(Modifier.weight(1f))
        if (roomy) CarrierFleet(rolling = scene.running && !probe, Modifier.padding(bottom = 14.dp))
    }
}

/**
 * `field(large:)`: reads as the field it leads to, Add, which takes a number, a link or a whole message. The
 * placeholder in the tertiary label, on up to two lines, in title 3 when [large] or body, across a row at least 64 or
 * 58 tall that opens Add to type, read as "Tracking number"; then, where the device scans, the viewfinder that opens
 * Add on the camera. Both dim a little under the finger (`.buttonStyle(.plain)`), on the paper with corners of 20 or
 * 18 and a hairline of the separator at 30 %. TalkBack goes back to the one tapped once Add has gone.
 */
@Composable
private fun FirstOpenField(scene: FirstOpenScene, large: Boolean, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val shape = ContinuousRoundedShape(if (large) Radii.firstOpenField else Radii.firstOpenFieldCompact)
    val fieldFocus = remember { AccessibilityFocus() }
    val scanFocus = remember { AccessibilityFocus() }
    val canScan = LocalLabelScanner.current.available()
    val fieldLabel = localized("add.tracking")
    val scanLabel = localized("add.scan")
    Row(
        modifier
            .background(colors.paper, shape)
            .border(1.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.3f), shape)
            .padding(start = if (large) 22.dp else 18.dp, end = if (large) 8.dp else 7.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .weight(1f)
                .heightIn(min = if (large) 64.dp else 58.dp)
                .testTag(FIRST_OPEN_FIELD)
                .accessibilityFocus(fieldFocus)
                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button) { scene.firstOpen.ask(FirstParcelRequest.Type, fieldFocus) }
                .semantics { contentDescription = fieldLabel },
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                localized("add.trackingPlaceholder"), Modifier.clearAndSetSemantics {},
                color = colors.tertiaryLabel, style = if (large) type.title3 else type.body, maxLines = 2,
            )
        }
        if (canScan) {
            val side = if (large) 48.dp else 44.dp
            Box(
                Modifier
                    .size(side)
                    .testTag(FIRST_OPEN_SCAN)
                    .accessibilityFocus(scanFocus)
                    .clickable(interactionSource = null, indication = PlainPress, role = Role.Button) { scene.firstOpen.ask(FirstParcelRequest.Scan, scanFocus) }
                    .semantics { contentDescription = scanLabel },
                contentAlignment = Alignment.Center,
            ) {
                PeekSymbol("barcode.viewfinder", font = if (large) type.title2 else type.title3, tint = colors.ink)
            }
        }
    }
}

/**
 * `pasteButton(large:)`: Paste and track opens Add with what the clipboard holds, read only then, and counts
 * `parcel-paste`; an empty clipboard opens it empty.
 */
@Composable
private fun PasteButton(scene: FirstOpenScene, large: Boolean, modifier: Modifier = Modifier) {
    val state = scene.state
    val focus = remember { AccessibilityFocus() }
    FirstParcelPrimaryButton(localized("landing.pasteAndTrack"), "doc.on.clipboard", large, FIRST_OPEN_PASTE, focus, modifier) {
        state.source.action("parcel-paste")
        scene.firstOpen.ask(FirstParcelRequest.Paste(state.readClipboard().orEmpty()), focus)
    }
}

/** `demoButton(large:)`: a build without a server has only its demo to show (`session.enterDemo()`). */
@Composable
private fun DemoButton(scene: FirstOpenScene, large: Boolean, modifier: Modifier = Modifier) {
    val focus = remember { AccessibilityFocus() }
    FirstParcelPrimaryButton(localized("welcome.demo"), null, large, FIRST_OPEN_DEMO, focus, modifier) { scene.state.source.enterDemo() }
}

/**
 * `FirstParcelPrimaryLabel`: the app's main button, postal yellow, the symbol in medium and the words in semibold, in
 * title 3 when [large] or body, 10 or 9 apart, in the ink on the yellow; at least 64 or 54 tall, with corners of 20 or
 * 16; [large], 30 in from its ends. It gives under the finger (`TactileButtonStyle`).
 */
@Composable
internal fun FirstParcelPrimaryButton(
    title: String,
    symbol: String?,
    large: Boolean,
    tag: String,
    focus: AccessibilityFocus,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val textStyle = if (large) IosTextStyle.Title3 else IosTextStyle.Body
    Row(
        modifier
            .testTag(tag)
            .accessibilityFocus(focus)
            .clickable(interactionSource = null, indication = PressStyle.Tactile, role = Role.Button, onClick = onClick)
            .heightIn(min = if (large) 64.dp else 54.dp)
            .background(colors.accent, ContinuousRoundedShape(if (large) Radii.primaryButton else Radii.primaryButtonCompact))
            .padding(horizontal = if (large) 30.dp else 0.dp),
        horizontalArrangement = Arrangement.spacedBy(if (large) 10.dp else 9.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (symbol != null) PeekSymbol(symbol, font = type.style(textStyle, FontWeight.Medium), tint = colors.onAccent)
        Text(title, color = colors.onAccent, style = type.style(textStyle, FontWeight.SemiBold), textAlign = TextAlign.Center)
    }
}

/**
 * `poster(size:top:)`: an iPad's, an open foldable's or a large window's. The journey fills the screen and the
 * question stands over it, its title as large as the screen allows up to 76, the field and Paste and track on one line
 * while the field keeps 320 for a number, one under the other otherwise; a quiet sky fades the route out under it.
 * The route keeps between the question and the parcel's name, named low in the corner of its map, over the carriers
 * rolling by. A tap anywhere on the map opens the sample, as on the phone's picture. It all fades in.
 */
@Composable
private fun FirstOpenPoster(scene: FirstOpenScene, insets: Safe, width: Dp, screen: Dp) {
    val surface = PeekTheme.colors.resolve(scene.identity.surface)
    val label = localized("landing.pip.open")
    val appearance = scene.appearance
    // Where the question ends and the parcel's name starts, which the route keeps between; until they are measured,
    // about what they will be.
    val frame = remember { arrayOfNulls<LayoutCoordinates>(1) }
    val bounds = remember { PosterBounds() }
    val routeTop = if (bounds.top > 0f) bounds.top + 40f else insets.top.value + 400f
    val routeFloor = if (bounds.floor > 0f) bounds.floor else screen.value - 280f
    Box(
        Modifier
            .fillMaxSize()
            .onPlaced { frame[0] = it }
            .appearing({ appearance.value })
            .testTag(FIRST_OPEN_POSTER),
    ) {
        Box(Modifier.fillMaxSize().samplePicture(scene, label).background(surface)) {
            Box(Modifier.fillMaxSize().clearAndSetSemantics {}) {
                SampleRoute(
                    scene,
                    EdgeInsets(top = routeTop.toDouble(), leading = 110.0, bottom = (screen.value - routeFloor + 40f).toDouble(), trailing = 100.0),
                    floor = (routeFloor - 12f).toDouble(),
                )
                // The question reads over a quiet sky: a route that leaves the screen fades out under it.
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(routeTop.dp)
                        .background(Brush.verticalGradient(listOf(surface.copy(alpha = 0.9f), surface.copy(alpha = 0.6f), surface.copy(alpha = 0f)))),
                )
            }
        }
        PosterForeground(scene, insets, width, screen, probe = false, frame = frame, bounds = bounds)
    }
}

/** Where the poster's question ends and its parcel's name starts, in points from the poster's top; 0 until measured. */
private class PosterBounds {
    var top by mutableFloatStateOf(0f)
    var floor by mutableFloatStateOf(0f)
}

/**
 * What stands over the poster's map: the masthead, the question, the parcel's name ([PosterCaption]) and the carriers.
 * A [probe] measures the height it wants, its spacer at its 24, and keeps still.
 */
@Composable
private fun PosterForeground(
    scene: FirstOpenScene,
    insets: Safe,
    width: Dp,
    screen: Dp,
    probe: Boolean,
    frame: Array<LayoutCoordinates?> = arrayOfNulls(1),
    bounds: PosterBounds? = null,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val appearance = scene.appearance
    val portrait = screen > width
    val titleSize = minOf(76f, width.value * 0.072f, screen.value * 0.085f)
    val density = LocalDensity.current
    fun measured(coordinates: LayoutCoordinates, edge: (Float) -> Float): Float? {
        val poster = frame[0]?.takeIf { it.isAttached } ?: return null
        return with(density) { edge(poster.localPositionOf(coordinates, Offset.Zero).y).toDp().value }
    }
    Column(Modifier.then(if (probe) Modifier.fillMaxWidth() else Modifier.fillMaxSize()).padding(start = insets.start, end = insets.end, bottom = insets.bottom)) {
        FirstOpenMasthead(scene, Modifier.padding(start = 28.dp, top = insets.top + 10.dp, end = 28.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                // `.frame(maxWidth: 780).padding(.horizontal, 40)`: 780 inside 860.
                .widthIn(max = POSTER_COLUMN + 80.dp)
                .padding(start = 40.dp, top = if (portrait) 72.dp else 28.dp, end = 40.dp)
                .then(
                    if (bounds == null) {
                        Modifier
                    } else {
                        Modifier.onGloballyPositioned { block -> measured(block) { it + block.size.height }?.let { bounds.top = it } }
                    },
                )
                .appearing({ appearance.value }, dy = APPEAR_FALL, fades = false),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val title = type.tracking(type.fixed(titleSize, FontWeight.Bold, FontDesign.Rounded), -titleSize * 0.035f)
            Text(
                localized("peek.title"), Modifier.takesTaps().semantics { heading() },
                color = colors.ink, style = title, textAlign = TextAlign.Center, maxLines = 2,
                autoSize = TextAutoSize.StepBased(minFontSize = title.fontSize * POSTER_TITLE_MINIMUM_SCALE, maxFontSize = title.fontSize),
            )
            Text(
                localized(if (scene.tracks) "native.pasteLead" else "arrival.welcomeSubtitle"), Modifier.padding(top = 10.dp).takesTaps(),
                color = colors.secondaryLabel, style = type.title3, textAlign = TextAlign.Center,
            )
            if (scene.tracks) {
                // On one line while the field keeps room for a number; one under the other at large type.
                FirstThatFits(
                    Modifier.padding(top = 30.dp).fillMaxWidth(),
                    {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            FirstOpenField(scene, large = true, Modifier.weight(1f).widthIn(min = 320.dp))
                            PasteButton(scene, large = true)
                        }
                    },
                    {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            FirstOpenField(scene, large = true, Modifier.fillMaxWidth())
                            PasteButton(scene, large = true, Modifier.fillMaxWidth())
                        }
                    },
                )
            } else {
                DemoButton(scene, large = true, Modifier.padding(top = 30.dp).widthIn(max = 420.dp).fillMaxWidth())
            }
        }
        Spacer(if (probe) Modifier.height(POSTER_GAP) else Modifier.weight(1f).heightIn(min = POSTER_GAP))
        PosterCaption(
            scene,
            Modifier
                .padding(start = 44.dp, end = 44.dp, bottom = 26.dp)
                .then(
                    if (bounds == null) {
                        Modifier
                    } else {
                        Modifier.onGloballyPositioned { caption -> measured(caption) { it }?.let { bounds.floor = it } }
                    },
                ),
        )
        CarrierFleet(rolling = scene.running && !probe, Modifier.padding(start = 44.dp, end = 44.dp, bottom = 20.dp).takesTaps())
    }
}

/**
 * `posterCaption(_:)`: the sample parcel named in the corner of its map, its mark, its name in large title semibold and
 * its status, with the alert a scan worth one brings above them, coming in from below; the stamp at the other end,
 * 64 wide. It belongs to the picture: a tap on it opens the parcel as one on the map does, and TalkBack doesn't read it.
 */
@Composable
private fun PosterCaption(scene: FirstOpenScene, modifier: Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Row(
        modifier.fillMaxWidth().clearAndSetSemantics {},
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        Column(Modifier.weight(1f)) {
            StepPing(scene, rise = PING_TRAVEL, bottom = 18.dp)
            CarrierFleetMark(scene.identity)
            Text(
                scene.parcel.label, Modifier.padding(top = 10.dp),
                color = colors.label, style = type.style(IosTextStyle.LargeTitle, FontWeight.SemiBold),
            )
            Box(Modifier.padding(top = 10.dp)) { StepStatus(scene, type.subheadline) }
        }
        DeliveryPostageStamp(scene.parcel, scene.identity, scene.appeared, width = 64.dp)
    }
}

/**
 * `CarrierFleet(rolling:)`: the carriers in their own liveries, rolling by at 16 points a second while [rolling], slow
 * enough to read a name as it passes, the line twice in its lane, the second taking over where the first leaves, the
 * lane's ends fading; standing at the start otherwise. Over it, in caption, the carriers from the first to the last.
 * TalkBack reads it as the first five carriers.
 */
@Composable
private fun CarrierFleet(rolling: Boolean, modifier: Modifier = Modifier) {
    val identities = LocalCarrierIdentities.current ?: return
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val type = PeekTheme.type
    val fleet = remember(identities, language) { FLEET.map { identities.of(it, language) } }
    val names = remember(identities, language) { FLEET.map { identities.displayName(it, language) } }
    val caption = remember(localizer, names) {
        localizer.text("landing.ribbon.caption", mapOf("first" to names.first(), "last" to names.last()))
    }
    val label = remember(localizer, names) { localizer.text("landing.ribbon.label", mapOf("carriers" to names.take(5).joinToString(", "))) }
    val travelled = remember { mutableFloatStateOf(0f) }
    // When it first rolled, which it rolls on from: it stands at the start while it doesn't.
    val started = remember { longArrayOf(-1L) }
    LaunchedEffect(rolling) {
        if (!rolling) {
            travelled.floatValue = 0f
            return@LaunchedEffect
        }
        while (true) {
            withFrameNanos { frame ->
                if (started[0] < 0) started[0] = frame
                travelled.floatValue = (frame - started[0]) / 1_000_000_000f * FLEET_SPEED
            }
        }
    }
    Column(
        modifier.fillMaxWidth().testTag(FIRST_OPEN_RIBBON).clearAndSetSemantics { contentDescription = label },
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(caption, color = PeekTheme.colors.secondaryLabel, style = type.caption, textAlign = TextAlign.Center)
        FleetLane(fleet, travelled)
    }
}

/**
 * The fleet's lane, 20 tall: the line twice, 26 apart, from 42 before the lane's start and [travelled] points on,
 * a lap at a time; clipped to the lane, whose tenth at each end fades out. The line is wider than the screen and
 * doesn't widen it.
 */
@Composable
private fun FleetLane(fleet: List<CarrierVisualIdentity>, travelled: FloatState) {
    val line = @Composable {
        Row(horizontalArrangement = Arrangement.spacedBy(FLEET_GAP), verticalAlignment = Alignment.CenterVertically) {
            fleet.forEach { CarrierFleetMark(it) }
        }
    }
    Layout(
        content = {
            line()
            line()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .clipToBounds()
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            .drawWithContent {
                drawContent()
                drawRect(FLEET_FADE, blendMode = BlendMode.DstIn)
            },
    ) { measurables, constraints ->
        val lines = measurables.map { it.measure(Constraints(maxHeight = constraints.maxHeight)) }
        val lap = (lines.firstOrNull()?.width ?: 0) + FLEET_GAP.roundToPx()
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Read as it is placed: the lane moves on without being composed again.
            val x = (-FLEET_LEAD.toPx() - travelled.floatValue.dp.toPx() % lap.coerceAtLeast(1)).roundToInt()
            lines.forEachIndexed { index, placeable -> placeable.placeRelative(x + index * lap, (constraints.maxHeight - placeable.height) / 2) }
        }
    }
}

/**
 * Comes in as [appearance] goes from 0 to 1: from [dx] and [dy] away, fading in unless it doesn't [fade]
 * (`.offset(appeared ? 0 : …)`, `.opacity(appeared ? 1 : 0)`).
 */
private fun Modifier.appearing(appearance: () -> Float, dx: Dp = 0.dp, dy: Dp = 0.dp, fades: Boolean = true): Modifier = graphicsLayer {
    val shown = appearance()
    if (fades) alpha = shown.coerceIn(0f, 1f)
    translationX = dx.toPx() * (1f - shown)
    translationY = dy.toPx() * (1f - shown)
}

/**
 * Takes the touches on it, as a view over the picture does on the iPhone: only what lets them through
 * (`allowsHitTesting(false)`) passes a tap to the picture under it.
 */
private fun Modifier.takesTaps(): Modifier = pointerInput(Unit) {}

/** The journey's step, for tests that watch it move on. */
internal val JourneyStep = SemanticsPropertyKey<Int>("FirstOpenJourneyStep")
private var SemanticsPropertyReceiver.journeyStep by JourneyStep

private const val PRESENCE_DURATION = 0.55
private const val APPEAR_DURATION = 0.7
private const val APPEAR_BOUNCE = 0.12
private const val STEP_DURATION = 0.5

/** How far the picture comes in from, and the question. */
private val APPEAR_RISE = 18.dp
private val APPEAR_FALL = 14.dp

/** How far the alert comes in from as the next takes the last's place. */
private val PING_TRAVEL = 6.dp
private const val PING_MINIMUM_SCALE = 0.8f
private const val ASK_TITLE_MINIMUM_SCALE = 0.7f
private const val POSTER_TITLE_MINIMUM_SCALE = 0.6f

/** The poster's question at its widest. */
private val POSTER_COLUMN = 780.dp

/** The least room between the poster's question and its parcel's name. */
private val POSTER_GAP = 24.dp

/** The fleet, as the site's ribbon has it. */
private val FLEET = listOf(
    CarrierId.SwissPost, CarrierId.Dhl, CarrierId.Ups, CarrierId.Dpd, CarrierId.GlsDe,
    CarrierId.Fedex, CarrierId.LaPoste, CarrierId.RoyalMail, CarrierId.Chronopost, CarrierId.Usps,
)
private val FLEET_GAP = 26.dp

/** Points a second. */
private const val FLEET_SPEED = 16f

/** How far before the lane's start the line stands. */
private val FLEET_LEAD = 42.dp
private val FLEET_FADE = Brush.horizontalGradient(0f to Color.Transparent, 0.1f to Color.Black, 0.9f to Color.Black, 1f to Color.Transparent)

const val FIRST_OPEN = "firstOpen"
const val FIRST_OPEN_POSTER = "firstOpen.poster"
const val FIRST_OPEN_PAGE = "firstOpen.page"
const val FIRST_OPEN_SCROLL = "firstOpen.scroll"
const val FIRST_OPEN_SPREAD = "firstOpen.spread"
const val FIRST_OPEN_PICTURE = "firstOpen.picture"
const val FIRST_OPEN_RIBBON = "firstOpen.ribbon"
const val FIRST_OPEN_FIELD = "welcome.field"
const val FIRST_OPEN_SCAN = "firstOpen.scan"
const val FIRST_OPEN_PASTE = "welcome.paste"
const val FIRST_OPEN_DEMO = "welcome.demo"
const val FIRST_OPEN_SIGN_IN = "welcome.signIn"
