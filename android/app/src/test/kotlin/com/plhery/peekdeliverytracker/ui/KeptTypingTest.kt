package com.plhery.peekdeliverytracker.ui

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextInputSelection
import androidx.compose.ui.text.TextRange
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** What a field keeps of what is typed ([KeptTyping]), and where its cursor goes. */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = PHONE)
class KeptTypingTest {
    @get:Rule
    val compose = createComposeRule()

    private val state = TextFieldState()

    private fun show(keep: (String) -> String) {
        compose.setContent { BasicTextField(state, Modifier.testTag("field"), inputTransformation = KeptTyping(keep)) }
    }

    private fun type(text: String, at: Int? = null) {
        val field = compose.onNodeWithTag("field")
        if (at != null) field.performTextInputSelection(TextRange(at))
        field.performTextInput(text)
        compose.waitForIdle()
    }

    @Test
    fun anEndCutOffLeavesTheCursorAtTheNewEnd() {
        show { it.take(3) }
        type("abcdef")
        assertEquals("abc", state.text.toString())
        assertEquals(TextRange(3), state.selection)
    }

    @Test
    fun aCapitalMadeInTheMiddleLeavesTheCursorAfterIt() {
        show { it.uppercase() }
        type("ac")
        type("b", at = 1)
        assertEquals("ABC", state.text.toString())
        assertEquals(TextRange(2), state.selection)
    }

    @Test
    fun aLetterLeftOutInTheMiddleLeavesTheCursorWhereItWas() {
        show { typed -> typed.filter(Char::isDigit) }
        type("1234")
        type("x", at = 2)
        assertEquals("1234", state.text.toString())
        assertEquals(TextRange(2), state.selection)
    }
}
