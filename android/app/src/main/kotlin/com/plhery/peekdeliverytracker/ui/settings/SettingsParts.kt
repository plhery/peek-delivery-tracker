package com.plhery.peekdeliverytracker.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.DistanceUnit
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalDistanceUnit
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.deliveries.FirstParcelPrimaryButton
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.Toggle
import com.plhery.peekdeliverytracker.ui.draw.stroke
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetMenus
import com.plhery.peekdeliverytracker.ui.sheet.MenuOption
import com.plhery.peekdeliverytracker.ui.sheet.SheetMenu
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.MinimumScale
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.ProvideDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.sfLines

// SettingsViews.swift's building blocks (C-S45): `SettingsGroup`, `SettingsRow`, `settingsDivider`, `settingsGreen`,
// `SettingsLanguageRow` and `SettingsAppearancePicker`, and AccountView's `account` and `signInOffer`.

/** `settingsGreen`: a switch's and a chosen appearance's green. */
internal val settingsGreen = Brand.color(light = "#688363", dark = "#97B084")

/**
 * `SettingsGroup`: its [title], if any, in caption 2 medium, upper case, tracked 1, in the opaque secondary grey, 2 in,
 * with a [badge] beside it (caption 2 bold, upper case, tracked 0.4, ochre on its surface), read by TalkBack as one; 9
 * under it, the rows raised with 16-point corners and a hairline of the sheet's line at a quarter.
 */
@Composable
internal fun SettingsGroup(
    title: String? = null,
    badge: String? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val locale = LocalLanguage.current.locale
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(9.dp)) {
        if (title != null) {
            Row(
                Modifier.padding(start = 2.dp).semantics(mergeDescendants = true) { heading() },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    title.uppercase(locale), color = colors.secondaryText,
                    style = type.tracking(type.style(IosTextStyle.Caption2, FontWeight.Medium), 1f).sfLines(),
                )
                if (badge != null) {
                    Text(
                        badge.uppercase(locale),
                        Modifier
                            .background(colors.resolve(ExperimentalPalette.ochreSurface), ContinuousRoundedShape(7.dp))
                            .padding(horizontal = 7.dp, vertical = 3.dp),
                        color = colors.resolve(ExperimentalPalette.ochre),
                        style = type.tracking(type.style(IosTextStyle.Caption2, FontWeight.Bold), 0.4f).sfLines(),
                    )
                }
            }
        }
        val shape = ContinuousRoundedShape(16.dp)
        Column(
            Modifier
                .fillMaxWidth()
                .background(colors.raised, shape)
                .stroke(0.5.dp, colors.sheetLine(0.25f), shape),
            content = content,
        )
    }
}

/** `settingsDivider`: a hairline of 0.5 in the sheet's line at a quarter, across the group. */
@Composable
internal fun SettingsDivider() {
    Box(Modifier.fillMaxWidth().height(0.5.dp).background(PeekTheme.colors.sheetLine(0.25f)))
}

/**
 * `SettingsRow`: the [symbol] in 15 light in the secondary grey, 17 wide, 11 from the [title] in subheadline in the
 * ink, the [detail] under it in caption secondary, then the [value] in caption at the trailing end and a chevron of 10
 * light, both in the opaque secondary grey; 15 in and 54 tall at least where [padded]. At the accessibility sizes the
 * value goes under the title, a value of one word made smaller down to half rather than broken, and the symbol gives
 * its room to the words.
 */
@Composable
internal fun SettingsRow(
    title: String,
    modifier: Modifier = Modifier,
    symbol: String? = null,
    detail: String? = null,
    value: String? = null,
    chevron: Boolean = false,
    padded: Boolean = true,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val stacked = PeekTheme.dynamicTypeSize.isAccessibilitySize
    Row(
        modifier
            .fillMaxWidth()
            .then(if (padded) Modifier.heightIn(min = ROW_HEIGHT).padding(ROW_PADDING) else Modifier),
        horizontalArrangement = Arrangement.spacedBy(11.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (symbol != null && !stacked) {
            Box(Modifier.width(17.dp), contentAlignment = Alignment.Center) {
                PeekSymbol(symbol, font = type.fixed(15f, FontWeight.Light), tint = colors.secondaryLabel)
            }
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(title, color = colors.ink, style = type.subheadline.sfLines())
            if (stacked && !value.isNullOrEmpty()) {
                val style = type.caption.sfLines().copy(color = colors.secondaryText)
                if (' ' !in value) {
                    BasicText(
                        value, style = style, maxLines = 1,
                        autoSize = MinimumScale(style.fontSize, 0.5f),
                    )
                } else {
                    Text(value, style = style)
                }
            }
            if (detail != null) Text(detail, color = colors.secondaryLabel, style = type.caption.sfLines())
        }
        if (!stacked && !value.isNullOrEmpty()) {
            Text(value, Modifier.padding(start = 4.dp), color = colors.secondaryText, style = type.caption.sfLines(), textAlign = TextAlign.End)
        }
        if (chevron) PeekSymbol("chevron.right", font = type.fixed(10f, FontWeight.Light), tint = colors.secondaryText)
    }
}

/**
 * A [SettingsRow] that is a plain button (`.buttonStyle(.plain)`): its words fade under the finger, TalkBack reads it as
 * one button, and it carries the iOS id as its [tag].
 */
@Composable
internal fun SettingsButtonRow(
    title: String,
    tag: String?,
    onClick: () -> Unit,
    symbol: String? = null,
    detail: String? = null,
    value: String? = null,
    chevron: Boolean = false,
) {
    SettingsRow(
        title,
        Modifier
            .then(if (tag != null) Modifier.testTag(tag) else Modifier)
            .button(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onClick)
            .semantics(mergeDescendants = true) {},
        symbol = symbol, detail = detail, value = value, chevron = chevron,
    )
}

/**
 * A `Toggle` labelled by an unpadded [SettingsRow], 15 in (`rowToggle()`, `.padding(15)`): the switch at the trailing end
 * in [settingsGreen] while on. TalkBack reads the row as one switch, its words and its state.
 */
@Composable
internal fun SettingsToggleRow(
    on: Boolean,
    onChange: (Boolean) -> Unit,
    title: String,
    tag: String?,
    symbol: String? = null,
    detail: String? = null,
    enabled: Boolean = true,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .then(if (tag != null) Modifier.testTag(tag) else Modifier)
            .semantics(mergeDescendants = true) {
                role = Role.Switch
                toggleableState = ToggleableState(on)
                if (enabled) {
                    onClick {
                        onChange(!on)
                        true
                    }
                } else {
                    disabled()
                }
            }
            .padding(ROW_PADDING),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.weight(1f)) { SettingsRow(title, symbol = symbol, detail = detail, padded = false) }
        Toggle(on, onChange, Modifier.clearAndSetSemantics {}, enabled = enabled, tint = PeekTheme.colors.resolve(settingsGreen))
    }
}

@Composable
internal fun SettingsLanguageRow() {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    SettingsPickerRow(
        label = localized("language.label"),
        options = AppLanguage.entries.map { MenuOption(it.nativeName, tag = settingsLanguageTag(it)) },
        selected = AppLanguage.entries.indexOf(language),
        rowTag = SETTINGS_LANGUAGE,
        pickerTag = SETTINGS_LANGUAGE_PICKER,
    ) { localizer.language = AppLanguage.entries[it] }
}

@Composable
internal fun SettingsDistanceRow() {
    val localizer = LocalLocalizer.current
    val unit = LocalDistanceUnit.current
    SettingsPickerRow(
        label = localized("settings.distanceUnits"),
        options = DistanceUnit.entries.map { MenuOption(localized(it.labelKey), tag = "$SETTINGS_DISTANCE.${it.code}") },
        selected = DistanceUnit.entries.indexOf(unit),
        rowTag = SETTINGS_DISTANCE,
        pickerTag = "$SETTINGS_DISTANCE.picker",
    ) { localizer.distanceUnit = DistanceUnit.entries[it] }
}

/** A picker row whose value sits under the label at the largest text sizes. */
@Composable
private fun SettingsPickerRow(
    label: String,
    options: List<MenuOption>,
    selected: Int,
    rowTag: String,
    pickerTag: String,
    onSelect: (Int) -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val menus = LocalSheetMenus.current
    val selectedName = options[selected].title
    var row by remember { mutableStateOf(Rect.Zero) }
    var value by remember { mutableStateOf(Rect.Zero) }
    val returnFocus = remember { AccessibilityFocus() }
    val behind = colors.raised
    val stacked = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val open = {
        menus?.content = {
            SheetMenu(
                options = options,
                selected = selected,
                row = row,
                trailing = value.right,
                leading = if (stacked) value.left else null,
                onClose = {
                    menus?.content = null
                    returnFocus.request()
                },
                behind = behind,
            ) { onSelect(it) }
        }
    }
    val picker = @Composable {
        // A menu Picker's button in a Form row: its up-down chevron at the subheadline size, semibold, 11 in from the
        // button's trailing end, which the menu lines up with.
        Row(
            Modifier
                .onGloballyPositioned { value = it.boundsInWindow() }
                .testTag(pickerTag)
                .button(interactionSource = null, indication = PlainPress, role = Role.DropdownList, onClick = open)
                .padding(end = PICKER_TRAILING),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(selectedName, color = colors.secondaryLabel, style = type.body.sfLines(), maxLines = 1, overflow = TextOverflow.Ellipsis)
            PeekSymbol(
                "chevron.up.chevron.down", Modifier.padding(start = PICKER_GAP),
                font = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold), tint = colors.secondaryLabel,
            )
        }
    }
    val frame = Modifier
        .fillMaxWidth()
        .testTag(rowTag)
        .onGloballyPositioned { row = it.boundsInWindow() }
        .accessibilityFocus(returnFocus)
        .clearAndSetSemantics {
            contentDescription = label
            stateDescription = selectedName
            role = Role.DropdownList
            onClick {
                open()
                true
            }
        }
    if (stacked) {
        Column(frame.padding(ROW_PADDING), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(label, color = colors.label, style = type.subheadline.sfLines())
            picker()
        }
    } else {
        Row(frame.heightIn(min = ROW_HEIGHT).padding(horizontal = ROW_PADDING), verticalAlignment = Alignment.CenterVertically) {
            Text(label, Modifier.weight(1f).padding(end = 8.dp), color = colors.label, style = type.subheadline.sfLines())
            picker()
        }
    }
}

/**
 * `SettingsAppearancePicker`: "native.appearance.title" in subheadline, 13 over the three appearances side by side, 8
 * apart, each a tile of its miniature (60 × 43) over its name in caption, the ink for the one chosen and the secondary
 * grey for the others, 10 above and below, on the sheet's ground with 12-point corners, outlined in [settingsGreen] at
 * 1.5 when chosen, else a hairline of the sheet's line at a quarter; 15 in. At the accessibility sizes they stand one under
 * the other without their pictures. A tap chooses one ([onChoose]); TalkBack reads each by its name, the chosen one
 * selected.
 */
@Composable
internal fun SettingsAppearancePicker(appearance: AppAppearance, onChoose: (AppAppearance) -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val stacked = PeekTheme.dynamicTypeSize.isAccessibilitySize
    Column(Modifier.fillMaxWidth().testTag(SETTINGS_APPEARANCE).padding(ROW_PADDING), verticalArrangement = Arrangement.spacedBy(13.dp)) {
        Text(localized("native.appearance.title"), color = colors.label, style = type.subheadline.sfLines())
        val tiles = @Composable { tile: Modifier ->
            for (option in AppAppearance.entries) {
                val chosen = option == appearance
                val name = localized("native.appearance.${option.rawValue}")
                val shape = ContinuousRoundedShape(12.dp)
                Column(
                    tile
                        .testTag(appearanceTag(option))
                        .button(interactionSource = null, indication = PlainPress, role = Role.Button) { onChoose(option) }
                        .clearAndSetSemantics {
                            contentDescription = name
                            role = Role.Button
                            selected = chosen
                            onClick {
                                onChoose(option)
                                true
                            }
                        }
                        .background(colors.sheet, shape)
                        .stroke(
                            if (chosen) 1.5.dp else 0.5.dp,
                            if (chosen) colors.resolve(settingsGreen) else colors.sheetLine(0.25f),
                            shape,
                        )
                        .padding(vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (!stacked) AppearanceMiniature(option)
                    Text(name, color = if (chosen) colors.ink else colors.secondaryLabel, style = type.caption.sfLines(), textAlign = TextAlign.Center)
                }
            }
        }
        if (stacked) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) { tiles(Modifier.fillMaxWidth()) }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { tiles(Modifier.weight(1f)) }
        }
    }
}

/**
 * `miniature(_:)`: a window of 60 × 43 with 5-point corners and a hairline of #69755C at 0.35, light or dark, or split
 * down the middle for the phone's own, two bars of 6 with 2-point corners in it, 4 apart, 7 in. The Dark and System
 * pictures show the kraft palette in either appearance, and the Dark one's hairline is #57524A at 0.35.
 */
@Composable
private fun AppearanceMiniature(option: AppAppearance) {
    val shape = ContinuousRoundedShape(5.dp)
    val edge = if (option == AppAppearance.Dark) MINI_DARK_EDGE else MINI_LIGHT_EDGE
    val leading = if (option == AppAppearance.Dark) MINI_DARK else MINI_LIGHT
    val trailing = if (option == AppAppearance.Light) MINI_LIGHT else MINI_DARK
    val leadingLine = if (option == AppAppearance.Dark) MINI_DARK_LINE else MINI_LIGHT_LINE
    val trailingLine = if (option == AppAppearance.Light) MINI_LIGHT_LINE else MINI_DARK_LINE
    Box(Modifier.size(60.dp, 43.dp).stroke(0.5.dp, edge.copy(alpha = 0.35f), shape).clip(shape)) {
        Row(Modifier.fillMaxWidth().fillMaxHeight()) {
            Box(Modifier.weight(1f).fillMaxHeight().background(leading))
            Box(Modifier.weight(1f).fillMaxHeight().background(trailing))
        }
        Column(Modifier.fillMaxWidth().padding(7.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(2) {
                Row(Modifier.fillMaxWidth().height(6.dp).clip(ContinuousRoundedShape(2.dp))) {
                    Box(Modifier.weight(1f).fillMaxHeight().background(leadingLine))
                    Box(Modifier.weight(1f).fillMaxHeight().background(trailingLine))
                }
            }
        }
    }
}

/**
 * AccountView's `account` (C-S40): the account's initial in a tile of 37 with 12-point corners, caption semibold, its
 * letter growing no further than at the first accessibility size; 11 from the address in subheadline medium, which can
 * be selected and copied, or "app.demo", over "account.signedIn" in caption secondary, but in the demo.
 */
@Composable
internal fun AccountRow(email: String?, demo: Boolean) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val initial = (email?.firstOrNull() ?: 'D').uppercase()
    Row(
        Modifier.fillMaxWidth().padding(bottom = 2.dp).testTag(SETTINGS_ACCOUNT),
        horizontalArrangement = Arrangement.spacedBy(11.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(37.dp).background(colors.resolve(TILE), ContinuousRoundedShape(12.dp)), contentAlignment = Alignment.Center) {
            ProvideDynamicTypeSize(PeekTheme.dynamicTypeSize.capped(DynamicTypeSize.Accessibility1)) {
                Text(initial, color = colors.resolve(TILE_LETTER), style = PeekTheme.type.style(IosTextStyle.Caption, FontWeight.SemiBold).sfLines())
            }
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            SelectionContainer {
                Text(email ?: localized("app.demo"), color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines())
            }
            if (!demo) Text(localized("account.signedIn"), color = colors.secondaryLabel, style = type.caption.sfLines())
        }
    }
}

/**
 * AccountView's `signInOffer` (C-S39): "link.account.title" in headline over "link.keep.body" in subheadline secondary,
 * 5 apart, then 14 under them the app's main button, "arrival.signInTitle" (`FirstParcelPrimaryLabel`), which gives
 * under the finger; 18 in, raised with 18-point continuous corners and a hairline of 0.75 in the sheet's line at 0.3.
 */
@Composable
internal fun SignInOffer(onSignIn: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val shape = ContinuousRoundedShape(18.dp)
    val focus = remember { AccessibilityFocus() }
    Column(
        Modifier
            .fillMaxWidth()
            .background(colors.raised, shape)
            .border(0.75.dp, colors.sheetLine(0.3f), shape)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(localized("link.account.title"), color = colors.label, style = type.headline.sfLines())
            Text(localized("link.keep.body"), color = colors.secondaryLabel, style = type.subheadline.sfLines())
        }
        FirstParcelPrimaryButton(localized("arrival.signInTitle"), null, false, SETTINGS_SIGN_IN, focus, Modifier.fillMaxWidth(), onSignIn)
    }
}

internal fun appearanceTag(option: AppAppearance) = "$SETTINGS_APPEARANCE.${option.rawValue}"

internal fun settingsLanguageTag(language: AppLanguage) = "$SETTINGS_LANGUAGE.${language.rawValue}"

private val ROW_PADDING = 15.dp
private val ROW_HEIGHT = 54.dp
/** The menu picker's room between its value and its chevron, and after the chevron. */
private val PICKER_GAP = 5.dp
private val PICKER_TRAILING = 11.dp
private val TILE = Brand.color(light = "#F7EFC9", dark = "#49452A")
private val TILE_LETTER = Brand.color(light = "#695824", dark = "#E2D08D")
/** The pictures' paper and bars, light and kraft, and their hairlines. */
private val MINI_LIGHT = Color(0xFFF2F4EB)
private val MINI_LIGHT_LINE = Color(0xFFDCE2D1)
private val MINI_LIGHT_EDGE = Color(0xFF69755C)
private val MINI_DARK = Color(0xFF1F1C17)
private val MINI_DARK_LINE = Color(0xFF433F38)
private val MINI_DARK_EDGE = Color(0xFF57524A)

internal const val SETTINGS_ACCOUNT = "$SETTINGS_SHEET.account"
internal const val SETTINGS_SIGN_IN = "$SETTINGS_SHEET.signIn"
internal const val SETTINGS_APPEARANCE = "$SETTINGS_SHEET.appearance"
internal const val SETTINGS_LANGUAGE = "$SETTINGS_SHEET.language"
internal const val SETTINGS_LANGUAGE_PICKER = "$SETTINGS_LANGUAGE.picker"
