package com.plhery.peekdeliverytracker.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex

/**
 * A bar TalkBack reads as one part, before the rest of its group: before the content that goes on under it, which starts
 * at the window's top and would otherwise come first. VoiceOver reaches a navigation bar's items and a `safeAreaInset`
 * at the top first the same way.
 */
internal fun Modifier.readFirst(): Modifier = semantics { isTraversalGroup = true; traversalIndex = -1f }

/**
 * The tabs at the top, read before every bar of the tab under them ([readFirst]), whose room starts at the window's top
 * too, as VoiceOver reaches the iPad's tabs first.
 */
internal fun Modifier.readBeforeTheBars(): Modifier = semantics { isTraversalGroup = true; traversalIndex = -2f }
