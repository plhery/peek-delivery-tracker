package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode

/** The card of the parcel whose name starts with `name`: a button read with its name. */
fun cardNamed(name: String): SemanticsMatcher = hasContentDescription(name, substring = true) and hasClickAction()

/** The card of the parcel named `name`, on screen. */
fun ComposeContentTestRule.card(name: String): SemanticsNodeInteraction = onNode(cardNamed(name))

/** Scrolls the list to the card of the parcel named `name` and taps it. */
fun ComposeContentTestRule.openCard(name: String) {
    onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed(name))
    card(name).performClick()
    waitForIdle()
}
