package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode

/** The card of the parcel whose name starts with `name`: a button read with its name. */
fun cardNamed(name: String): SemanticsMatcher = hasContentDescription(name, substring = true) and hasClickAction()

/** The card of the parcel named `name`, on screen. */
fun ComposeContentTestRule.card(name: String): SemanticsNodeInteraction = onNode(cardNamed(name))

/**
 * Does `act` and moves the clock a frame at a time until TalkBack is told `text` ([tag]'s news), within three seconds,
 * in the tree TalkBack reads, where what a page or a sheet covers is left out: the node is there before `act`, so a
 * node that only comes into that tree with the news, once what covered it goes, doesn't pass for it.
 */
fun ComposeContentTestRule.assertAnnounced(text: String, tag: String = ANNOUNCEMENT, act: () -> Unit = {}) {
    val advancing = mainClock.autoAdvance
    mainClock.autoAdvance = false
    try {
        if (onAllNodesWithTag(tag).fetchSemanticsNodes().isEmpty()) throw AssertionError("TalkBack can't reach $tag")
        act()
        repeat(180) {
            val said = onAllNodesWithTag(tag).fetchSemanticsNodes().map { it.config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull() }
            if (text in said) return
            mainClock.advanceTimeByFrame()
        }
        throw AssertionError("TalkBack wasn't told \"$text\" within 3 s")
    } finally {
        mainClock.autoAdvance = advancing
    }
}

/** Scrolls the list to the card of the parcel named `name` and taps it. */
fun ComposeContentTestRule.openCard(name: String) {
    onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed(name))
    card(name).performClick()
    waitForIdle()
}
