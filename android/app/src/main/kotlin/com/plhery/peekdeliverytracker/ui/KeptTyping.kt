package com.plhery.peekdeliverytracker.ui

import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.ui.text.TextRange

/**
 * A field's words kept as [keep] keeps them, whatever is typed or pasted: an end cut off goes, the cursor staying where
 * it was or at the new end; anything else changed (a letter left out, a capital made) takes the whole text, the cursor
 * as far from its end as it was. What the app sets itself doesn't come through here.
 */
internal class KeptTyping(private val keep: (String) -> String) : InputTransformation {
    override fun TextFieldBuffer.transformInput() {
        val typed = asCharSequence().toString()
        val kept = keep(typed)
        if (kept == typed) return
        if (typed.startsWith(kept)) {
            replace(kept.length, length, "")
        } else {
            val fromEnd = typed.length - selection.end
            replace(0, length, kept)
            selection = TextRange((kept.length - fromEnd).coerceIn(0, kept.length))
        }
    }
}

/** [text] in the field, the cursor at its end, unless the field already holds it, its cursor then left alone. */
internal fun TextFieldState.setTextIfChanged(text: String) {
    if (!this.text.contentEquals(text)) setTextAndPlaceCursorAtEnd(text)
}
