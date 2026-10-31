package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow

/**
 * DesignSystem.swift's `InlineToast` (A-S68), with one button or none: `symbol` in `tint`, the text on up to two lines
 * in subheadline medium, and `button` in subheadline bold and the tint at its end, on glass cut to 16 with a shadow.
 * At the accessibility sizes the button goes under the text. The asking form, with answers, is the next one.
 *
 * It is read as one, its button the toast's own action. The list tells TalkBack its news as it appears
 * ([DeliveriesState.announce]), as the iPhone list posts it.
 */
@Composable
internal fun InlineToast(
    text: String,
    button: String?,
    symbol: String,
    tint: Color,
    action: (() -> Unit)?,
    backdrop: MaterialBackdrop?,
    modifier: Modifier = Modifier,
) {
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(TOAST_RADIUS)
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val textStyle = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines()
    val label = cardLabel(text, button)
    GlassSurface(
        shape,
        modifier
            .dropShadow(shape, TOAST_SHADOW)
            .testTag(INLINE_TOAST)
            .clearAndSetSemantics {
                contentDescription = label
                if (button != null && action != null) {
                    role = Role.Button
                    onClick(label = button) {
                        action()
                        true
                    }
                }
            },
        backdrop,
    ) {
        val lead = @Composable { lead: Modifier -> PeekSymbol(symbol, lead, font = type.body, tint = tint) }
        val press = @Composable {
            if (button != null && action != null) {
                Text(
                    button,
                    Modifier.clickable(interactionSource = null, indication = PlainPress, onClick = action),
                    color = tint,
                    style = type.style(IosTextStyle.Subheadline, FontWeight.Bold).sfLines(),
                )
            }
        }
        val padding = Modifier
            .heightIn(min = TOAST_HEIGHT)
            .padding(start = 17.dp, end = 17.dp)
        if (large) {
            Column(padding.padding(vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    lead(Modifier.alignByBaseline())
                    Text(text, Modifier.alignByBaseline().fillMaxWidth(), color = colors.label, style = textStyle)
                }
                press()
            }
        } else {
            Row(padding, horizontalArrangement = Arrangement.spacedBy(14.dp), verticalAlignment = Alignment.CenterVertically) {
                lead(Modifier)
                Text(text, Modifier.weight(1f), color = colors.label, style = textStyle, maxLines = 2, overflow = TextOverflow.Ellipsis)
                // `Spacer(minLength: 4)`, with the row's spacing on both its sides.
                if (button != null) Spacer(Modifier.width(4.dp))
                press()
            }
        }
    }
}

/** One of the answers an asking toast offers (`InlineToast.Answer`). */
internal class ToastAnswer(val title: String, val tag: String? = null, val action: () -> Unit)

/**
 * `InlineToast(text:answers:asker:)`: a toast that asks. `asker` stands where the symbol would, 10 before the text in
 * subheadline medium on up to three lines, then the answers 6 apart at its end, each in subheadline semibold 14 in on
 * the ink at 8 % with 12-point continuous corners, 36 tall in a touch 44 tall, giving under the finger at 0.96. 11 in
 * at the leading side with an asker and 9 at the trailing side with answers, on glass cut to 16 with a shadow. At the
 * accessibility sizes the text takes every line it needs beside the asker, its top at his, and the answers go under
 * them. TalkBack reaches the text and each answer one by one (`.contain`).
 */
@Composable
internal fun InlineToast(
    text: String,
    answers: List<ToastAnswer>,
    backdrop: MaterialBackdrop?,
    modifier: Modifier = Modifier,
    asker: @Composable () -> Unit,
) {
    val type = PeekTheme.type
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(TOAST_RADIUS)
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val textStyle = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines()
    GlassSurface(
        shape,
        modifier
            .dropShadow(shape, TOAST_SHADOW)
            .testTag(INLINE_TOAST)
            .semantics { isTraversalGroup = true },
        backdrop,
    ) {
        val buttons = @Composable {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                for (answer in answers) ToastAnswerButton(answer)
            }
        }
        val padding = Modifier
            .heightIn(min = TOAST_HEIGHT)
            .padding(start = 11.dp, end = if (answers.isEmpty()) 17.dp else 9.dp)
        if (large) {
            Column(padding.padding(vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
                    asker()
                    Text(text, Modifier.fillMaxWidth(), color = colors.label, style = textStyle)
                }
                if (answers.isNotEmpty()) buttons()
            }
        } else {
            Row(padding, horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                asker()
                Text(text, Modifier.weight(1f), color = colors.label, style = textStyle, maxLines = 3, overflow = TextOverflow.Ellipsis)
                // `Spacer(minLength: 4)`, with the row's spacing on both its sides.
                Spacer(Modifier.width(4.dp))
                buttons()
            }
        }
    }
}

/** An answer of an asking toast: its title on the ink at 8 %, 36 tall in a touch 44 tall. */
@Composable
private fun ToastAnswerButton(answer: ToastAnswer) {
    val colors = PeekTheme.colors
    Box(
        Modifier
            .then(if (answer.tag != null) Modifier.testTag(answer.tag) else Modifier)
            .clickable(interactionSource = null, indication = PressStyle.tactile(0.96f), role = Role.Button, onClick = answer.action)
            .heightIn(min = 44.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            Modifier
                .background(colors.ink.copy(alpha = colors.ink.alpha * 0.08f), ContinuousRoundedShape(12.dp))
                .heightIn(min = 36.dp)
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(answer.title, color = colors.label, style = PeekTheme.type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
        }
    }
}

private val TOAST_RADIUS = 16.dp
private val TOAST_HEIGHT = 54.dp
private val TOAST_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.13f), radius = 8.dp, y = 4.dp)

const val INLINE_TOAST = "deliveries.toast"
