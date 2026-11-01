package com.plhery.peekdeliverytracker.ui.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.rememberTextMeasurer
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.deliveries.ALERT_BUTTONS_GAP
import com.plhery.peekdeliverytracker.ui.deliveries.ALERT_BUTTON_GAP
import com.plhery.peekdeliverytracker.ui.deliveries.ALERT_BUTTON_PADDING
import com.plhery.peekdeliverytracker.ui.deliveries.ALERT_BUTTON_SIDE
import com.plhery.peekdeliverytracker.ui.deliveries.ALERT_CAPSULE_HEIGHT
import com.plhery.peekdeliverytracker.ui.deliveries.ALERT_CAPSULE_PADDING
import com.plhery.peekdeliverytracker.ui.deliveries.ALERT_FIELD_GAP
import com.plhery.peekdeliverytracker.ui.deliveries.ALERT_SIDE
import com.plhery.peekdeliverytracker.ui.deliveries.ALERT_WIDTH
import com.plhery.peekdeliverytracker.ui.deliveries.AlertButton
import com.plhery.peekdeliverytracker.ui.deliveries.AlertCard
import com.plhery.peekdeliverytracker.ui.deliveries.AlertWords
import com.plhery.peekdeliverytracker.ui.deliveries.ALERT_WORDS_GAP
import com.plhery.peekdeliverytracker.ui.deliveries.DIM
import com.plhery.peekdeliverytracker.ui.deliveries.FIELD_INSET
import com.plhery.peekdeliverytracker.ui.deliveries.alertButtonFont
import com.plhery.peekdeliverytracker.ui.deliveries.alertFill
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlin.math.max

/** An alert's text field (`TextField(_:text:)` in `.alert`): its [placeholder], what is typed in it and its change. */
internal class AlertField(val placeholder: String, val text: String, val onChange: (String) -> Unit)

/**
 * `.alert(title, isPresented:) { … } message: { … }` as iOS 26 draws it ([AlertCard], iphone-03-reset-alert.png): the
 * title in the headline's weight and its [message] under it in subheadline secondary, both leading ([AlertWords]), the
 * [field], if any, which takes the focus and the keyboard as it opens, without capitals
 * (`.textInputAutocapitalization(.never)`), then Cancel and the [action] side by side, or the action over Cancel where
 * either doesn't fit its half, as at the accessibility text sizes. The action is red where [destructive], and off,
 * greyed, until [actionEnabled]. Cancel and the back close it. It stands in the middle of what the keyboard leaves of
 * the window, clear of the system's bars, and scrolls where it is taller. TalkBack starts at its title.
 */
@Composable
internal fun SettingsAlert(
    title: String,
    message: String?,
    action: String,
    actionTag: String,
    onCancel: () -> Unit,
    destructive: Boolean = false,
    actionEnabled: Boolean = true,
    field: AlertField? = null,
    onAction: () -> Unit,
) {
    val localizer = LocalLocalizer.current
    val heading = remember { AccessibilityFocus() }
    val focus = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        if (field != null) focus.requestFocus()
        heading.request()
    }
    BackHandler(onBack = onCancel)
    val bars = WindowInsets.systemBars.union(WindowInsets.displayCutout)
    val keyboard = WindowInsets.ime
    Box(
        Modifier
            .fillMaxSize()
            .testTag(SETTINGS_ALERT)
            .background(DIM)
            .pointerInput(Unit) { detectTapGestures { } }
            .imePadding(),
        contentAlignment = Alignment.Center,
    ) {
        AlertCard(
            title,
            Modifier.layout { measurable, constraints ->
                val clear = max(bars.getTop(this), bars.getBottom(this) - keyboard.getBottom(this))
                val placeable = measurable.measure(constraints.copy(minHeight = 0, maxHeight = max(0, constraints.maxHeight - 2 * clear)))
                layout(placeable.width, placeable.height) { placeable.place(0, 0) }
            },
        ) {
            Box(Modifier.padding(horizontal = ALERT_BUTTON_SIDE - ALERT_SIDE)) { AlertWords(title, message, heading) }
            if (field != null) {
                Spacer(Modifier.height(ALERT_FIELD_GAP))
                AlertTextField(field, focus)
            }
            Spacer(Modifier.height(if (field != null) ALERT_BUTTONS_GAP else ALERT_WORDS_GAP))
            AlertActions(localizer.text("common.cancel"), action, actionTag, destructive, actionEnabled, onCancel, onAction)
        }
    }
}

/** The alert's field: a capsule in the alert's fill, its placeholder in the tertiary grey while it is empty. */
@Composable
private fun AlertTextField(field: AlertField, focus: FocusRequester) {
    val colors = PeekTheme.colors
    val font = PeekTheme.type.body
    var value by remember { mutableStateOf(TextFieldValue(field.text, TextRange(field.text.length))) }
    if (value.text != field.text) value = TextFieldValue(field.text, TextRange(field.text.length))
    BasicTextField(
        value,
        onValueChange = {
            value = it
            field.onChange(it.text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .testTag(SETTINGS_ALERT_FIELD)
            .focusRequester(focus)
            .semantics { contentDescription = field.placeholder },
        textStyle = font.copy(color = colors.label),
        singleLine = true,
        cursorBrush = SolidColor(colors.accent),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.None, imeAction = ImeAction.Done),
        decorationBox = { text ->
            Box(
                Modifier
                    .heightIn(min = ALERT_CAPSULE_HEIGHT)
                    .background(alertFill(colors.dark), CapsuleShape)
                    .padding(horizontal = FIELD_INSET, vertical = ALERT_CAPSULE_PADDING),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (value.text.isEmpty()) {
                    Text(field.placeholder, Modifier.clearAndSetSemantics {}, color = colors.tertiaryLabel, style = font, maxLines = 1)
                }
                text()
            }
        },
    )
}

/**
 * Cancel and the action as iOS 26 lays out an alert's two: capsules side by side, Cancel first, while each title fits its
 * half; otherwise, and always at the accessibility text sizes, one under the other, Cancel last.
 */
@Composable
private fun AlertActions(
    cancel: String,
    action: String,
    actionTag: String,
    destructive: Boolean,
    enabled: Boolean,
    onCancel: () -> Unit,
    onAction: () -> Unit,
) {
    val measurer = rememberTextMeasurer()
    val half = with(LocalDensity.current) { ((ALERT_WIDTH - ALERT_BUTTON_SIDE * 2 - ALERT_BUTTON_GAP) / 2 - ALERT_BUTTON_PADDING * 2).roundToPx() }
    val font = alertButtonFont()
    val stacked = PeekTheme.dynamicTypeSize.isAccessibilitySize ||
        listOf(cancel, action).any { measurer.measure(it, font, maxLines = 1, softWrap = false).size.width > half }
    val side = Modifier.padding(horizontal = ALERT_BUTTON_SIDE - ALERT_SIDE)
    if (stacked) {
        Column(side.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(ALERT_BUTTON_GAP)) {
            AlertButton(action, actionTag, Modifier.fillMaxWidth(), onAction, destructive, enabled)
            AlertButton(cancel, SETTINGS_ALERT_CANCEL, Modifier.fillMaxWidth(), onCancel)
        }
    } else {
        Row(side.fillMaxWidth().height(IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(ALERT_BUTTON_GAP)) {
            AlertButton(cancel, SETTINGS_ALERT_CANCEL, Modifier.weight(1f).fillMaxHeight(), onCancel)
            AlertButton(action, actionTag, Modifier.weight(1f).fillMaxHeight(), onAction, destructive, enabled)
        }
    }
}

internal const val SETTINGS_ALERT = "$SETTINGS_SHEET.alert"
internal const val SETTINGS_ALERT_FIELD = "$SETTINGS_SHEET.alert.field"
internal const val SETTINGS_ALERT_CANCEL = "$SETTINGS_SHEET.alert.cancel"
