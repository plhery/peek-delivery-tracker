package com.plhery.peekdeliverytracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.LocalSaveableStateRegistry
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged

/**
 * A text field's focus, and with it the keyboard, kept through the window made again (a rotation, a fold), as the
 * iPhone's first responder stays through a turn: focused as the old window saved what it showed, the field takes the
 * focus again in the new one. The new one's field, unfocused until then, leaves what was saved as it was. Saved under
 * [key], the field's own name, rather than where it is composed, which changes as a sheet goes from a form to a phone's
 * sheet when a foldable shuts.
 */
@Composable
internal fun Modifier.keptFocus(key: String): Modifier {
    val focus = remember { FocusRequester() }
    val registry = LocalSaveableStateRegistry.current
    val name = "keptFocus.$key"
    val focused = remember(registry, name) { mutableStateOf(registry?.consumeRestored(name) as? Boolean ?: false) }
    // Saved as it is then: the old field giving the focus up as it goes, after the save, changes nothing saved.
    DisposableEffect(registry, name) {
        val entry = registry?.registerProvider(name) { focused.value }
        onDispose { entry?.unregister() }
    }
    val refocus = remember { mutableStateOf(focused.value) }
    LaunchedEffect(Unit) {
        if (refocus.value) focus.requestFocus()
        refocus.value = false
    }
    return focusRequester(focus).onFocusChanged { if (it.isFocused || !refocus.value) focused.value = it.isFocused }
}
