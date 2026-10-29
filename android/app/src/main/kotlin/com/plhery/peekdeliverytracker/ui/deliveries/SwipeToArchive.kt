package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.input.pointer.util.addPointerInputChange
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.ArchiveSwipeMotion
import com.plhery.peekdeliverytracker.core.ArchiveSwipePose
import com.plhery.peekdeliverytracker.core.ArchiveSwipeState
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.core.Size as SwipeSize
import com.plhery.peekdeliverytracker.core.SwipeSpring
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.ExperimentalSurfaceShadow
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.CappedDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.Haptics
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekKeyframes
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.keyframeAnimator
import com.plhery.peekdeliverytracker.ui.theme.rememberHaptics
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.util.UUID
import kotlin.math.abs
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** What [archivePanDecision] makes of a drag so far. */
internal enum class ArchivePan { Undecided, Began, Failed }

/**
 * ArchivePanGestureRecognizer's rule: a drag's direction is judged once it has travelled [ARCHIVE_DECISION_DISTANCE]
 * from where it went down (a fingertip's first points wobble), in the window, so a list that started scrolling doesn't
 * skew it. It is a swipe, either way, when it goes more than 1.25 times as far across as down; anything steeper, or
 * diagonal, is the list's.
 */
internal fun archivePanDecision(dx: Float, dy: Float, decisionDistance: Float): ArchivePan = when {
    hypot(dx, dy) < decisionDistance -> ArchivePan.Undecided
    abs(dx) <= abs(dy) * 1.25f -> ArchivePan.Failed
    else -> ArchivePan.Began
}

internal val ARCHIVE_DECISION_DISTANCE = 10.dp

/**
 * ExperimentalSwipeToArchiveModifier's state for one card (A-G01–G03, A-AN28–AN33): the swipe under the finger, the
 * pose at rest, the motion between gestures, and whether the action is armed or the card is on its way out. Each card
 * keeps its own, so several can stay open, and scrolling leaves them open. Positions are in points (dp).
 *
 * A motion is timed from its first frame; [frame] is the latest frame's time while one runs, read only where the card
 * is placed and drawn, so a moving card is laid out and drawn again each frame, not composed.
 */
@Stable
internal class ArchiveSwipe(
    private val scope: CoroutineScope,
    private val haptics: () -> Haptics?,
    reduceMotion: Boolean,
) {
    var reduceMotion: Boolean = reduceMotion
    var width: Double = 0.0

    /** Archives the parcel once `leaving` is over, and says whether it left the list. */
    var action: (leaving: Job) -> Deferred<Boolean> = { CompletableDeferred(false) }

    private var swipe = ArchiveSwipeState()
    private var gestureActive = false
    private var pose by mutableStateOf(ArchiveSwipePose())
    var motion: ArchiveSwipeMotion? by mutableStateOf(null)
        private set
    private var motionStart: Long? = null
    private var frame by mutableLongStateOf(0L)

    var armed by mutableStateOf(false)
        private set
    var committing by mutableStateOf(false)
        private set

    /** Bumped as the action is pressed, for its haptic. */
    var archiveFeedback by mutableIntStateOf(0)
        private set

    private val rowWidth: Double get() = max(width, ArchiveSwipeState.ACTION_WIDTH)

    private fun elapsed(): Double {
        // Read every time, so whatever places or draws the card is told of each frame, the first one included.
        val now = frame
        return motionStart?.let { (now - it) / 1e9 } ?: 0.0
    }

    /** Where the card is now. */
    fun shown(): ArchiveSwipePose = motion?.pose(at = elapsed()) ?: pose

    private fun speed(): ArchiveSwipePose = motion?.velocity(at = elapsed()) ?: ArchiveSwipePose()

    /** How much of the action shows. */
    fun visible(): Double = min(shown().reveal, rowWidth)

    /** Runs the motion frame by frame until it rests, as the iPhone's timeline does. */
    suspend fun run(current: ArchiveSwipeMotion) {
        motionStart = null
        while (motion === current) {
            withFrameNanos { tick(it) }
            if (motion === current && elapsed() >= current.duration) {
                pose = current.target
                motion = null
            }
        }
    }

    private fun tick(nanos: Long) {
        if (motionStart == null) motionStart = nanos
        frame = nanos
    }

    private fun start(next: ArchiveSwipeMotion?) {
        // A new motion starts where the last one is now; it is timed from its own first frame.
        motionStart = null
        motion = next
    }

    fun began() {
        gestureActive = true
        // Catch the card wherever it is on screen.
        swipe = swipe.settle(at = shown().reveal)
    }

    fun changed(translation: SwipeSize) {
        if (committing) return
        swipe = swipe.drag(translation, rowWidth)
        follow()
        arm(swipe.reveal >= ArchiveSwipeState.commitPoint(rowWidth))
    }

    /** `velocity` is the finger's horizontal speed in points per second. */
    fun ended(velocity: Double) {
        gestureActive = false
        val ended = swipe.release(velocity, rowWidth)
        swipe = ended.state
        val destination = ended.destination
        if (committing || destination == null) {
            arm(false)
            return
        }
        if (destination == ArchiveSwipeState.Destination.Archive) {
            trigger(velocity = -velocity, provideFeedback = false)
        } else {
            arm(false)
            settle(destination, velocity = -velocity)
        }
    }

    fun cancel() {
        gestureActive = false
        arm(false)
        if (committing) return
        val ended = swipe.cancel()
        swipe = ended.state
        ended.destination?.let { settle(it) }
    }

    /** Keep the card under the finger while the action leaps to or from its edge. */
    private fun follow() {
        val shown = shown()
        val speed = speed()
        val leapt = if (swipe.reveal >= ArchiveSwipeState.commitPoint(rowWidth)) 1.0 else 0.0
        val target = ArchiveSwipePose(reveal = swipe.reveal, spread = leapt)
        val start = ArchiveSwipePose(reveal = swipe.reveal, spread = shown.spread, land = shown.land)
        if (reduceMotion || (abs(start.spread - leapt) < 0.001 && abs(speed.spread) < 0.01 && abs(start.land) < 0.001)) {
            pose = target
            start(null)
        } else {
            start(ArchiveSwipeMotion(start, target, ArchiveSwipePose(spread = speed.spread, land = speed.land), SwipeSpring.snap, reduceMotion))
        }
    }

    /** Spring to the closed or revealed position, keeping the current speed unless the finger gave one. */
    private fun settle(destination: ArchiveSwipeState.Destination, velocity: Double? = null) {
        val shown = shown()
        val speed = speed()
        val reveal = if (destination == ArchiveSwipeState.Destination.Revealed) ArchiveSwipeState.ACTION_WIDTH else 0.0
        val flung = destination == ArchiveSwipeState.Destination.Revealed && (velocity ?: 0.0) >= ArchiveSwipeState.FLICK_SPEED
        swipe = swipe.settle(at = reveal)
        start(
            ArchiveSwipeMotion(
                shown, ArchiveSwipePose(reveal = reveal),
                ArchiveSwipePose(reveal = velocity ?: speed.reveal, spread = speed.spread, land = speed.land),
                if (flung) SwipeSpring.fling else SwipeSpring.snap, reduceMotion,
            ),
        )
    }

    /** A tap on the card: closes it while it is open (the action takes its own taps), opens the parcel otherwise. */
    fun tap(open: () -> Unit) {
        if (committing) return
        if (shown().reveal > 1) {
            settle(ArchiveSwipeState.Destination.Closed)
            return
        }
        open()
    }

    /**
     * The card leaves to the left, the label moving to the middle of the row, then the parcel is archived: in the app's
     * scope ([action]), so that leaving the tab or the window turning as the card goes cuts its way out short rather
     * than the archive.
     */
    fun trigger(velocity: Double? = null, provideFeedback: Boolean) {
        if (committing) return
        committing = true
        if (provideFeedback) {
            archiveFeedback += 1
            haptics()?.perform(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Medium, 0.8))
        }
        val shown = shown()
        val speed = speed()
        val width = rowWidth
        val exit = ArchiveSwipeMotion(
            shown, ArchiveSwipePose(reveal = width, spread = 1.0, land = 1.0),
            ArchiveSwipePose(reveal = max(0.0, velocity ?: speed.reveal), spread = speed.spread, land = speed.land),
            SwipeSpring.exit, reduceMotion, glued = true,
        )
        swipe = swipe.settle(at = width)
        start(exit)
        // The row leaves the list once the card is out of sight and the label has nearly centred.
        val leave = exit.time { it.reveal >= width - 2 && it.land >= 0.92 }
        val leaving = scope.launch {
            while (motion === exit && elapsed() < leave) withFrameNanos { tick(it) }
        }
        val archived = action(leaving)
        scope.launch {
            // The list removes the row at once; it comes back only when archiving failed.
            if (archived.await()) return@launch
            committing = false
            arm(false)
            settle(ArchiveSwipeState.Destination.Closed)
        }
    }

    private fun arm(value: Boolean) {
        if (armed == value) return
        armed = value
        if (value) haptics()?.perform(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Medium, 0.9))
    }

    /** The card's offset to the left, in points. */
    fun cardOffset(): Double = min(shown().reveal, rowWidth)
}

/** The swipe of a card that `state` archives, kept as long as the card is in the list. */
@Composable
internal fun rememberArchiveSwipe(state: DeliveriesState, parcelId: UUID, action: (leaving: Job) -> Deferred<Boolean>): ArchiveSwipe {
    val reduceMotion = PeekTheme.reduceMotion
    val haptics = rememberHaptics()
    val swipe = remember(state, parcelId) { ArchiveSwipe(state.scope, { haptics }, reduceMotion) }
    swipe.reduceMotion = reduceMotion
    swipe.action = action
    val motion = swipe.motion
    LaunchedEffect(motion) { if (motion != null) swipe.run(motion) }
    return swipe
}

/**
 * The swipe on a card (ExperimentalSwipeToArchiveModifier): the card in a row cut to `cornerRadius`, with `shadow` under
 * the row rather than the card, which moves left over the action drawn behind it. `card` draws the card with the
 * modifier given, which moves it; the card itself takes its taps through [ArchiveSwipe.tap] and offers the swipe's
 * action to TalkBack ([archiveAction]).
 */
@Composable
internal fun SwipeToArchive(
    swipe: ArchiveSwipe,
    title: String,
    cornerRadius: Dp,
    shadow: Boolean,
    modifier: Modifier = Modifier,
    card: @Composable (Modifier) -> Unit,
) {
    val shape = ContinuousRoundedShape(cornerRadius)
    val density = LocalDensity.current
    var origin by remember { mutableStateOf(Offset.Zero) }
    Box(
        modifier
            .then(if (shadow) Modifier.dropShadow(shape, ExperimentalSurfaceShadow) else Modifier)
            .clip(shape)
            .onSizeChanged { swipe.width = with(density) { it.width.toDp().value.toDouble() } }
            .onGloballyPositioned { origin = it.positionInWindow() }
            .pointerInput(swipe) { archivePan(swipe) { origin } },
    ) {
        ActionLayers(swipe, title, Modifier.matchParentSize())
        card(Modifier.offset { IntOffset(-(swipe.cardOffset() * density.density).toInt(), 0) })
    }
}

/** The swipe's action in TalkBack's actions on the card, named as on the card's layer. */
internal fun archiveAction(swipe: ArchiveSwipe, title: String): CustomAccessibilityAction =
    CustomAccessibilityAction(title) {
        swipe.trigger(provideFeedback = true)
        true
    }

/**
 * The drag on the row: undecided until it has gone [ARCHIVE_DECISION_DISTANCE] in the window, then the card's if it
 * goes across ([archivePanDecision]), which stops the list's scroll by taking every move after; the list's otherwise. One
 * finger only. Translations are measured from where the drag was decided, as iOS 27 reports a recognized pan from zero.
 */
private suspend fun androidx.compose.ui.input.pointer.PointerInputScope.archivePan(swipe: ArchiveSwipe, origin: () -> Offset) {
    val decision = ARCHIVE_DECISION_DISTANCE.toPx()
    awaitEachGesture {
        val down = awaitFirstDown(requireUnconsumed = false)
        if (swipe.committing) return@awaitEachGesture
        val startInWindow = down.position + origin()
        val tracker = VelocityTracker()
        tracker.addPointerInputChange(down)
        var decided = false
        var from = Offset.Zero
        while (!decided) {
            val event = awaitPointerEvent()
            if (event.changes.count { it.pressed } > 1) return@awaitEachGesture
            val change = event.changes.firstOrNull { it.id == down.id } ?: return@awaitEachGesture
            if (!change.pressed) return@awaitEachGesture
            tracker.addPointerInputChange(change)
            val travel = change.position + origin() - startInWindow
            when (archivePanDecision(travel.x, travel.y, decision)) {
                ArchivePan.Undecided -> Unit
                ArchivePan.Failed -> return@awaitEachGesture
                ArchivePan.Began -> {
                    decided = true
                    from = change.position
                    change.consume()
                }
            }
        }
        swipe.began()
        swipe.changed(SwipeSize(0.0, 0.0))
        var finished = false
        try {
            while (true) {
                val event = awaitPointerEvent()
                val change = event.changes.firstOrNull { it.id == down.id } ?: break
                // The lift too: a finger that stopped before it lifted throws nothing.
                tracker.addPointerInputChange(change)
                if (!change.pressed) {
                    change.consume()
                    finished = true
                    swipe.ended(tracker.calculateVelocity().x / density.toDouble())
                    return@awaitEachGesture
                }
                if (change.positionChange() != Offset.Zero) {
                    val translation = (change.position - from) / density
                    swipe.changed(SwipeSize(translation.x.toDouble(), translation.y.toDouble()))
                }
                change.consume()
            }
        } finally {
            if (!finished) swipe.cancel()
        }
    }
}

/**
 * A soft tray behind the card, and the action that rides on the card's edge until it is fully shown, then leaps to
 * that edge past the commit point; on the way out its label moves to the middle of the row. Hidden at rest, so no
 * colour shows through the card's corners.
 */
@Composable
private fun ActionLayers(swipe: ArchiveSwipe, title: String, modifier: Modifier) {
    val colors = PeekTheme.colors
    val warning = colors.warning
    val warningSoft = colors.warningSoft
    val density = LocalDensity.current
    val action = ArchiveSwipeState.ACTION_WIDTH
    Box(
        modifier
            .graphicsLayer { alpha = if (swipe.visible() > 0.25) 1f else 0f }
            .drawBehind {
                val shown = swipe.shown()
                val width = size.width / density.density
                val visible = min(shown.reveal, width.toDouble())
                val tray = max(0.0, action - visible)
                val shift = shown.spread * max(0.0, visible - action)
                val blockStart = tray + width - action - shift
                val gap = (1 - shown.spread) * max(0.0, visible - action)
                val unit = density.density
                drawRect(warningSoft, topLeft = Offset((tray * unit).toFloat(), 0f))
                drawRect(warning, topLeft = Offset((blockStart * unit).toFloat(), 0f), size = Size(width * unit, size.height))
                // Only while the action touches the card; otherwise the tray shows in the gap. It stays solid across
                // the last few points of a leap, which would leave a hairline of tray.
                val reach = (1 - (gap - 3) / 6).coerceIn(0.0, 1.0).toFloat()
                drawRect(
                    warning, topLeft = Offset(((blockStart - REACH) * unit).toFloat(), 0f), size = Size((REACH * unit).toFloat(), size.height),
                    alpha = reach,
                )
            },
    ) {
        ActionButton(swipe, title)
    }
}

@Composable
private fun ActionButton(swipe: ArchiveSwipe, title: String) {
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    val reduceMotion = PeekTheme.reduceMotion
    val reachable by remember(swipe) { derivedStateOf { swipe.visible() >= 8 } }
    val armed = swipe.armed && !reduceMotion
    val scale by animateFloatAsState(
        if (armed) 1.2f else 1f,
        if (reduceMotion) snap() else Motion.snappy(0.22, extraBounce = 0.16),
        label = "archive armed",
    )
    val bounce by keyframeAnimator(1.0, armed) { PeekKeyframes.symbolBounce }
    val ink = colors.resolve(Brand.color(light = "#FFFFFF", dark = "#292820"))
    Column(
        Modifier
            .width(ArchiveSwipeState.ACTION_WIDTH.dp)
            .fillMaxHeight()
            .offset {
                val shown = swipe.shown()
                val rowWidth = max(swipe.width, ArchiveSwipeState.ACTION_WIDTH)
                val visible = min(shown.reveal, rowWidth)
                val tray = max(0.0, ArchiveSwipeState.ACTION_WIDTH - visible)
                val shift = shown.spread * max(0.0, visible - ArchiveSwipeState.ACTION_WIDTH)
                val blockStart = tray + rowWidth - ArchiveSwipeState.ACTION_WIDTH - shift
                val x = blockStart + shown.land * (shift - (rowWidth - ArchiveSwipeState.ACTION_WIDTH) / 2)
                IntOffset((x * density).toInt(), 0)
            }
            .testTag(ARCHIVE_ACTION)
            .then(
                if (reachable) {
                    Modifier
                        .clearAndSetSemantics {
                            contentDescription = title
                            role = Role.Button
                            onClick { swipe.trigger(provideFeedback = true); true }
                        }
                        .pointerInput(swipe) { detectTapGestures { if (swipe.visible() >= 8) swipe.trigger(provideFeedback = true) } }
                } else {
                    Modifier.clearAndSetSemantics {}
                },
            ),
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // The action keeps its width: its word stops growing before it outgrows it.
        CappedDynamicTypeSize(DynamicTypeSize.Accessibility1) {
            PeekSymbol(
                "archivebox.fill",
                Modifier.graphicsLayer {
                    val s = scale * bounce.toFloat()
                    scaleX = s
                    scaleY = s
                },
                font = type.fixed(18f, FontWeight.Bold),
                tint = ink,
            )
            Text(title, color = ink, style = PeekTheme.type.style(IosTextStyle.Caption2, FontWeight.SemiBold).sfLines(), maxLines = 1)
        }
    }
}

/** While the action touches the card, it also fills under the card's round corners. */
private const val REACH = 24.0

const val ARCHIVE_ACTION = "deliveries.archiveAction"
