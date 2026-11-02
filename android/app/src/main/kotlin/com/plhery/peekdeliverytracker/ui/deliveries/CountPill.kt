package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit

/** The overview's count of the parcels on their way, and each figure of a count, for the tests. */
internal const val ACTIVE_COUNT = "deliveries.activeCount"
internal const val COUNT_FIGURE = "deliveries.count.figure"

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
 * the right. Under Reduce Motion the list's changes aren't animated and the count just changes, but the release keeps
 * its spring on the iPhone, and the figures cross-fade then. A count that changes otherwise (a search, a filter, a
 * parcel taking another stage) just changes, as SwiftUI's transaction has no animation for it. The count and its
 * change come together (the list's items carry both), or a change seen a composition before its count would be taken
 * for an old one. TalkBack reads the count as one number.
 */
@Composable
internal fun CountPill(count: Int, horizontalPadding: Dp, change: ListChange?, modifier: Modifier = Modifier) {
    val secondary = PeekTheme.colors.secondaryLabel
    val reduceMotion = PeekTheme.reduceMotion
    val seen = remember { SeenCount(count, change) }
    val roll = when {
        count == seen.count || change == null || change === seen.change -> CountRoll.None
        change.releasing -> if (reduceMotion) CountRoll.Fade else CountRoll.Release
        reduceMotion -> CountRoll.None
        else -> CountRoll.Changing
    }
    // A count that just changes is drawn anew, any roll under way dropped, in the frame it changes.
    val epoch = if (roll == CountRoll.None && count != seen.count) seen.epoch + 1 else seen.epoch
    SideEffect {
        seen.count = count
        seen.change = change
        seen.epoch = epoch
    }
    Box(
        modifier
            .heightIn(min = 20.dp)
            .background(secondary.copy(alpha = secondary.alpha * 0.09f), CapsuleShape)
            .padding(horizontal = horizontalPadding)
            .semantics { text = AnnotatedString("$count") },
        contentAlignment = Alignment.Center,
    ) {
        RollingFigures("$count", roll, epoch, PeekTheme.type.caption2.monospacedDigit(), secondary)
    }
}

/** The count and the list's change the pill last showed, and how many times its count just changed. */
private class SeenCount(var count: Int, var change: ListChange?, var epoch: Int = 0)

/** How a count's figures take a new number. */
private enum class CountRoll { None, Changing, Release, Fade }

/**
 * The figures of [text], each taking a new one as [roll] has it; one a longer number adds comes in at once, and all are
 * made anew with each [epoch], as the count just changes.
 */
@Composable
private fun RollingFigures(text: String, roll: CountRoll, epoch: Int, style: TextStyle, color: Color) {
    Row(Modifier.clipToBounds()) {
        // From the left: the figures a longer count adds come in at its left.
        for (fromRight in text.length - 1 downTo 0) {
            key(fromRight, epoch) {
                AnimatedContent(targetState = text[text.length - 1 - fromRight], transitionSpec = { roll.transform() }, label = "figure") { figure ->
                    Text(
                        figure.toString(),
                        Modifier.testTag(COUNT_FIGURE).clearAndSetSemantics { this[CountFigure] = figure.toString() },
                        color = color,
                        style = style,
                        maxLines = 1,
                        softWrap = false,
                    )
                }
            }
        }
    }
}

/** The new figure up from under the old one as it goes up, both fading, or a cross-fade, or none. */
private fun CountRoll.transform(): ContentTransform = when (this) {
    CountRoll.None -> ContentTransform(EnterTransition.None, ExitTransition.None, sizeTransform = null)
    CountRoll.Fade -> ContentTransform(fadeIn(Motion.spring(RELEASE_MOTION)), fadeOut(Motion.spring(RELEASE_MOTION)), sizeTransform = null)
    CountRoll.Changing, CountRoll.Release -> {
        val releasing = this == CountRoll.Release
        val slide = if (releasing) Motion.spring<IntOffset>(RELEASE_MOTION) else Motion.snappy<IntOffset>(LIST_MOTION)
        val fade = if (releasing) Motion.spring<Float>(RELEASE_MOTION) else Motion.snappy<Float>(LIST_MOTION)
        ContentTransform(
            slideInVertically(slide) { it } + fadeIn(fade),
            slideOutVertically(slide) { -it } + fadeOut(fade),
            sizeTransform = null,
        )
    }
}
