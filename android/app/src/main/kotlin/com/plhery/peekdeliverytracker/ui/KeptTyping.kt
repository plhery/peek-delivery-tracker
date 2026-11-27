package com.plhery.peekdeliverytracker.ui

import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.placeCursorAtEnd

/**
 * A field's words kept as [keep] keeps them, whatever is typed or pasted: an end cut off goes, the cursor staying where
 * it was or at the new end; anything else changed takes the whole text, with the cursor at its end. What the app sets
 * itself doesn't come through here.
 */
internal class KeptTyping(private val keep: (String) -> String) : InputTransformation {
    override fun TextFieldBuffer.transformInput() {
        val typed = asCharSequence().toString()
        val kept = keep(typed)
        if (kept == typed) return
        if (typed.startsWith(kept)) {
            replace(kept.length, length, "")
        } else {
            replace(0, length, kept)
            placeCursorAtEnd()
        }
    }
}
