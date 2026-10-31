package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.res.Configuration
import android.text.InputType
import android.view.inputmethod.EditorInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.isFocused
import org.junit.Assert.assertEquals

/** `content` as on a device with a hardware keyboard connected and in use, where the menus show their keys. */
@Composable
fun WithKeyboard(connected: Boolean = true, content: @Composable () -> Unit) {
    if (!connected) return content()
    val configuration = Configuration(LocalConfiguration.current).apply {
        keyboard = Configuration.KEYBOARD_QWERTY
        hardKeyboardHidden = Configuration.HARDKEYBOARDHIDDEN_NO
    }
    CompositionLocalProvider(LocalConfiguration provides configuration, content = content)
}

/** What the focused text field hands the keyboard as it opens it: its keys, their flags and its action. */
fun ComposeTestRule.editorInfo(): EditorInfo {
    waitForIdle()
    val view = (onNode(isFocused()).fetchSemanticsNode().root as ViewRootForTest).view
    return EditorInfo().also { info -> runOnUiThread { view.onCreateInputConnection(info) } }
}

/**
 * [info] as a field the iPhone marks `.autocorrectionDisabled()` hands it: the text keys of [variation], [capitals] the
 * only capitals, no corrections and no suggestions ([com.plhery.peekdeliverytracker.ui.NoSuggestions]), and [action].
 */
fun assertTextWithoutSuggestions(info: EditorInfo, action: Int, variation: Int = InputType.TYPE_TEXT_VARIATION_NORMAL, capitals: Int = 0) {
    val type = info.inputType
    assertEquals(InputType.TYPE_CLASS_TEXT, type and InputType.TYPE_MASK_CLASS)
    assertEquals(variation, type and InputType.TYPE_MASK_VARIATION)
    assertEquals(capitals, type and CAPITALS)
    assertEquals(0, type and InputType.TYPE_TEXT_FLAG_AUTO_CORRECT)
    assertEquals(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS, type and InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
    assertEquals(action, info.imeOptions and EditorInfo.IME_MASK_ACTION)
}

private const val CAPITALS =
    InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS or InputType.TYPE_TEXT_FLAG_CAP_WORDS or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
