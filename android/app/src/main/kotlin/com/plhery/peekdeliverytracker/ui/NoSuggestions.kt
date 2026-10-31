package com.plhery.peekdeliverytracker.ui

import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.InterceptPlatformTextInput
import androidx.compose.ui.platform.PlatformTextInputInterceptor
import androidx.compose.ui.platform.PlatformTextInputMethodRequest
import androidx.compose.ui.platform.PlatformTextInputSession

/**
 * The text fields in [content] with no suggestions over the keyboard, as iOS's `.autocorrectionDisabled()` leaves the
 * QuickType bar off its field. `autoCorrectEnabled = false` only leaves out the corrections: Gboard keeps its strip of
 * suggestions until the field asks for none (TYPE_TEXT_FLAG_NO_SUGGESTIONS), which no keyboard option asks. A field
 * of numbers has no suggestions to leave out. The keyboard, its action and the focus stay as the field asks.
 */
@Composable
internal fun NoSuggestions(content: @Composable () -> Unit) = InterceptPlatformTextInput(NO_SUGGESTIONS, content)

private val NO_SUGGESTIONS = object : PlatformTextInputInterceptor {
    override suspend fun interceptStartInputMethod(
        request: PlatformTextInputMethodRequest,
        nextHandler: PlatformTextInputSession,
    ): Nothing = nextHandler.startInputMethod(
        object : PlatformTextInputMethodRequest {
            override fun createInputConnection(outAttributes: EditorInfo): InputConnection =
                request.createInputConnection(outAttributes).also {
                    if (outAttributes.inputType and InputType.TYPE_MASK_CLASS == InputType.TYPE_CLASS_TEXT) {
                        outAttributes.inputType = outAttributes.inputType or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                    }
                }
        },
    )
}
