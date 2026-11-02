package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.ParcelSectionKind
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import kotlin.math.roundToInt
import kotlinx.coroutines.flow.first

/** The overview's count of the parcels on their way, and each figure of a count, for the tests. */
internal const val ACTIVE_COUNT = "deliveries.activeCount"
internal const val COUNT_FIGURE = "deliveries.count.figure"

/** A section's count, for the tests. */
internal fun sectionCount(kind: ParcelSectionKind): String = "deliveries.sectionCount.${kind.name}"

/** The figure a [COUNT_FIGURE] node shows, for the tests: the count's text is the pill's alone. */
internal val CountFigure = SemanticsPropertyKey<String>("CountFigure")

/**
 * One change of the list that SwiftUI animates, as the counts see it ([DeliveriesState.listChange]): the held parcels
 * let go, with the release's spring ([releasing]), or the parcels on the list changing, with its snappy motion. Each is
 * a new one, told apart from the last by identity.
 */
class ListChange(val releasing: Boolean)

/**
 * A count in a faint capsule, beside a heading (`Text("\(count)").contentTransition(.numericText())`). A count that
 * changes with a new [change] of the list rolls as SwiftUI's numeric text does in that change's animation, the list's
 * snappy 0.32 s or the release's spring: each figure that changes goes up and fades as the new one comes up from under
 * it, whether the number went up or down (`.numericText()` counts up), and the others stay; figures are matched from
 * the right. A figure a longer count adds rolls in the same way from an empty place, and one a shorter count drops rolls
 * out, the capsule's width following in the same motion. Under Reduce Motion the count just changes: the list's changes
 * aren't animated then, and nothing is held for the release (`watching`). A count that changes otherwise (a search, a
 * filter, a parcel taking another stage) just changes, as SwiftUI's transaction has no animation for it. The count and
 * its change come together (the list's items carry both), or a change seen a composition before its count would be
 * taken for an old one. TalkBack reads the count as one number.
 */
@Composable
internal fun CountPill(count: Int, horizontalPadding: Dp, change: ListChange?, modifier: Modifier = Modifier) {
    val secondary = PeekTheme.colors.secondaryLabel
    val reduceMotion = PeekTheme.reduceMotion
    val seen = remember { SeenCount(count, change) }
    val rolls = count != seen.count && change != null && change !== seen.change && !reduceMotion
    // A count that just changes is drawn anew, any roll under way dropped, in the frame it changes.
    val epoch = if (!rolls && count != seen.count) seen.epoch + 1 else seen.epoch
    val releasing = if (rolls) change.releasing else seen.releasing
    SideEffect {
        seen.count = count
        seen.change = change
        seen.epoch = epoch
        seen.releasing = releasing
    }
    Box(
        modifier
            .heightIn(min = 20.dp)
            .background(secondary.copy(alpha = secondary.alpha * 0.09f), CapsuleShape)
            .padding(horizontal = horizontalPadding)
            .semantics { text = AnnotatedString("$count") },
        contentAlignment = Alignment.Center,
    ) {
        RollingFigures("$count", releasing, epoch, PeekTheme.type.caption2.monospacedDigit(), secondary)
    }
}

/**
 * The count and the list's change the pill last showed, how many times its count just changed, and whether its last
 * roll was the release's.
 */
private class SeenCount(var count: Int, var change: ListChange?, var epoch: Int = 0, var releasing: Boolean = false)

/**
 * The places a count's figures stand in: the count's own, and those of a longer count whose figures are still rolling
 * out ([shown]). A place made once the figures are drawn starts empty, and its figure rolls in.
 */
private class Places(count: Int) {
    var shown by mutableIntStateOf(count)
    var drawn = false
}

/**
 * The figures of [text], each taking a new one with the list's motion, or the release's spring when [releasing]: a
 * figure a longer count adds rolls in from an empty place that opens as it comes, one a shorter count drops rolls out
 * of a place that closes as it goes. All are made anew with each [epoch], as the count just changes.
 */
@Composable
private fun RollingFigures(text: String, releasing: Boolean, epoch: Int, style: TextStyle, color: Color) {
    val places = remember(epoch) { Places(text.length) }
    val shown = maxOf(places.shown, text.length)
    SideEffect {
        places.shown = shown
        places.drawn = true
    }
    Row(Modifier.clipToBounds()) {
        // From the left: the figures a longer count adds come in at its left.
        for (fromRight in shown - 1 downTo 0) {
            key(fromRight, epoch) {
                val figure = text.getOrNull(text.length - 1 - fromRight)
                val state = remember { MutableTransitionState(if (places.drawn) null else figure) }
                state.targetState = figure
                val transition = rememberTransition(state, label = "figure")
                val open = transition.animateFloat({ rollSpec(releasing) }, label = "place") { if (it == null) 0f else 1f }
                transition.AnimatedContent(
                    // The place as wide as it is open, its figure at its end: what stands beyond the count's left is
                    // cut off.
                    Modifier.layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        val width = (placeable.width * open.value).roundToInt().coerceAtLeast(0)
                        layout(width, placeable.height) { placeable.place(width - placeable.width, 0) }
                    },
                    transitionSpec = { roll(releasing) },
                ) { shownFigure ->
                    if (shownFigure != null) {
                        Text(
                            shownFigure.toString(),
                            Modifier.testTag(COUNT_FIGURE).clearAndSetSemantics { this[CountFigure] = shownFigure.toString() },
                            color = color,
                            style = style,
                            maxLines = 1,
                            softWrap = false,
                        )
                    }
                }
                // A place the count no longer has goes once its figure has rolled out, after those beyond it.
                if (figure == null) {
                    LaunchedEffect(state) {
                        snapshotFlow { places.shown == fromRight + 1 && state.isIdle && state.currentState == null }.first { it }
                        places.shown = fromRight
                    }
                }
            }
        }
    }
}

/** The list's snappy 0.32 s, or the release's spring. */
private fun <T> rollSpec(releasing: Boolean): FiniteAnimationSpec<T> =
    if (releasing) Motion.spring(RELEASE_MOTION) else Motion.snappy(LIST_MOTION)

/** The new figure up from under the old one as it goes up, both fading; an empty place takes part as nothing. */
private fun roll(releasing: Boolean): ContentTransform = ContentTransform(
    slideInVertically(rollSpec(releasing)) { it } + fadeIn(rollSpec(releasing)),
    slideOutVertically(rollSpec(releasing)) { -it } + fadeOut(rollSpec(releasing)),
    sizeTransform = null,
)
