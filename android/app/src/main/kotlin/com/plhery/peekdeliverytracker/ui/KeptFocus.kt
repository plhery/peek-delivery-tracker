package com.plhery.peekdeliverytracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged

/**
 * A text field's focus, and with it the keyboard, kept through the window made again (a rotation, a fold), as the
 * iPhone's first responder stays through a turn: focused as the old window saved what it showed, the field takes the
 * focus again in the new one. The new one's field, unfocused until then, leaves what was saved as it was.
 */
@Composable
internal fun Modifier.keptFocus(): Modifier {
    val focus = remember { FocusRequester() }
    // Saved as it is then: the old field giving the focus up as it goes, after the save, changes nothing saved.
    val focused = rememberSaveable(saver = FOCUSED_SAVER) { mutableStateOf(false) }
    val refocus = remember { mutableStateOf(focused.value) }
    LaunchedEffect(Unit) {
        if (refocus.value) focus.requestFocus()
        refocus.value = false
    }
    return focusRequester(focus).onFocusChanged { if (it.isFocused || !refocus.value) focused.value = it.isFocused }
}

private val FOCUSED_SAVER = Saver<MutableState<Boolean>, Boolean>(save = { it.value }, restore = { mutableStateOf(it) })
