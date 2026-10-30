package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierChange
import com.plhery.peekdeliverytracker.core.CarrierRequirement
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.NumberRefusals
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.CarrierTruck
import com.plhery.peekdeliverytracker.ui.sheet.FormRowFrame
import com.plhery.peekdeliverytracker.ui.sheet.FormSection
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetGround
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetMenus
import com.plhery.peekdeliverytracker.ui.sheet.MenuOption
import com.plhery.peekdeliverytracker.ui.sheet.RowHighlight
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.SheetMenu
import com.plhery.peekdeliverytracker.ui.sheet.SheetPage
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.symbols.SymbolScale
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.carrierIdentity
import com.plhery.peekdeliverytracker.ui.theme.sfLines

/**
 * The carrier editor (`.sheet(item: $carrierEditor)` on a parcel's page, `.sheet(item: $prompts.changingCarrier)` on
 * the list), over the whole window: `ChangeCarrierView` on the sheet's medium and large detents, a form sheet in a
 * regular window (`formOnPad(phoneDetents: [.medium, .large], selection:)`). It opens at the large detent where the
 * carrier asks for something, and goes there for the picker. The page's goes with its page, and draws nothing once its
 * parcel has gone, as the iPhone's `if let parcel` does; the list's works on the parcel as the card's menu found it,
 * until it has gone down. While its change is saved, nothing but the change dismisses it
 * (`interactiveDismissDisabled(saving)`), and once saved it goes down still saying so. Its fields, the carrier chosen,
 * its detent and the picker pushed stay through a window made again.
 */
@Composable
internal fun CarrierEditorHost(state: DeliveriesState) {
    val request = state.carrierEditor
    val unshown = request?.pageId != null && request.pageId != state.navigator.navigation.shown
    val visible = request != null && !unshown
    // Whether the sheet has come up, so that an editor closed before it did leaves nothing to wait for.
    var up by remember { mutableStateOf(false) }
    if (visible) up = true
    LaunchedEffect(unshown) {
        if (!unshown) return@LaunchedEffect
        state.closeCarrierEditor()
        if (!up) state.carrierEditorClosed()
    }
    // The request the sheet shows, kept while it goes down.
    var shown by remember { mutableStateOf(request) }
    if (request != null) shown = request
    val editing = shown
    val catalog = LocalCarrierIdentities.current?.catalog ?: CarrierCatalog.shared
    val region = remember { CarrierCatalog.deviceCountry() }
    val parcel = editing?.let { state.carrierEditorParcel(it, state.parcels) }
    val form = rememberSaveable(editing?.id, saver = CarrierEditorForm.Saver) { CarrierEditorForm.start(editing, parcel, catalog, region) }
    val change = parcel?.let { form.change(it, catalog, region) }
    // Inputs and their help sit below the half-height sheet: open fully for them, each time they come
    // (`.onChange(of: requirements.isEmpty, initial: true)`), and not again for the window made again.
    val needsRoom = change?.requirements?.isNotEmpty() == true
    LaunchedEffect(editing?.id, needsRoom) {
        if (needsRoom && !form.roomy) form.large = true
        form.roomy = needsRoom
    }
    val work = state.source.pageWork
    // Saved, it says so until it has gone, as `save()` leaves `saving` on.
    val saving = editing != null && (work.working(editing.id) || editing.id in work.leaving || state.carrierEditorSaved == editing.id)
    Sheet(
        visible = visible,
        onDismiss = { state.closeCarrierEditor() },
        paneTitle = localized("detail.changeCarrier"),
        detents = listOf(SheetDetent.Medium, SheetDetent.Large),
        onClosed = {
            up = false
            state.carrierEditorClosed()
        },
        detent = if (form.large) SheetDetent.Large else SheetDetent.Medium,
        onDetentChange = { form.large = it == SheetDetent.Large },
        dismissible = !saving,
    ) {
        if (editing != null && change != null) {
            ChangeCarrierView(state, editing, form, change, saving, Modifier.testTag(CHANGE_CARRIER_SHEET))
        }
    }
}

/**
 * What the carrier editor holds (`ChangeCarrierView`'s `@State`): the carrier chosen, null for automatic detection, the
 * link and the postcodes typed, the postcode's country chosen, whether the picker is pushed, the detent asked for, and
 * whether the carrier asked for something when last seen ([roomy]), which took the sheet to the large detent.
 */
@Stable
internal class CarrierEditorForm(
    chosenCarrier: CarrierId?,
    trackingUrl: String,
    deliveryPostcode: String,
    chosenPostcodeCountry: String?,
    providerPostcode: String,
    pushed: Boolean,
    large: Boolean,
    roomy: Boolean = large,
) {
    var chosenCarrier by mutableStateOf(chosenCarrier)
    var trackingUrl by mutableStateOf(trackingUrl)
    var deliveryPostcode by mutableStateOf(deliveryPostcode)
    var chosenPostcodeCountry by mutableStateOf(chosenPostcodeCountry)
    var providerPostcode by mutableStateOf(providerPostcode)
    var pushed by mutableStateOf(pushed)
    var large by mutableStateOf(large)
    var roomy = roomy

    fun change(parcel: Parcel, catalog: CarrierCatalog, region: String?) = CarrierChange(
        parcel, chosenCarrier, trackingUrl, deliveryPostcode, chosenPostcodeCountry, providerPostcode, catalog, region,
    )

    /** Takes what `change` holds, as another carrier chosen leaves it ([CarrierChange.choosing]). */
    fun take(change: CarrierChange) {
        chosenCarrier = change.chosenCarrier
        trackingUrl = change.trackingUrl
        deliveryPostcode = change.deliveryPostcode
        chosenPostcodeCountry = change.chosenPostcodeCountry
        providerPostcode = change.providerPostcode
    }

    companion object {
        /** The editor as `request` opens it on `parcel` (`init(parcel:initialCarrier:)`): empty without one. */
        fun start(request: CarrierEditorRequest?, parcel: Parcel?, catalog: CarrierCatalog, region: String?): CarrierEditorForm {
            if (request == null || parcel == null) return CarrierEditorForm(null, "", "", null, "", pushed = false, large = false)
            val change = CarrierChange.start(parcel, request.initialCarrier, catalog, region)
            return CarrierEditorForm(
                change.chosenCarrier, change.trackingUrl, change.deliveryPostcode, null, "",
                pushed = false, large = change.requirements.isNotEmpty(),
            )
        }

        val Saver = listSaver<CarrierEditorForm, Any>(
            save = {
                listOf(
                    it.chosenCarrier?.rawValue.orEmpty(), it.trackingUrl, it.deliveryPostcode, it.chosenPostcodeCountry.orEmpty(),
                    it.providerPostcode, it.pushed, it.large, it.roomy,
                )
            },
            restore = {
                CarrierEditorForm(
                    (it[0] as String).ifEmpty { null }?.let(::CarrierId), it[1] as String, it[2] as String,
                    (it[3] as String).ifEmpty { null }, it[4] as String, it[5] as Boolean, it[6] as Boolean,
                    it[7] as Boolean,
                )
            },
        )
    }
}

/**
 * `ChangeCarrierView`'s `NavigationStack`: the form, and the carrier picker pushed over it from the carrier's row, the
 * form going out to the left and the picker coming in from the right as the system pushes, at once under Reduce Motion.
 * The bar's ‹ and the system's back take the picker away, as a choice in it does once it has set the carrier.
 */
@Composable
private fun ChangeCarrierView(
    state: DeliveriesState,
    request: CarrierEditorRequest,
    form: CarrierEditorForm,
    change: CarrierChange,
    saving: Boolean,
    modifier: Modifier,
) {
    val reduceMotion = PeekTheme.reduceMotion
    val focusManager = LocalFocusManager.current
    val work = state.source.pageWork
    val push = remember { Animatable(if (form.pushed) 1f else 0f) }
    LaunchedEffect(form.pushed, reduceMotion) {
        val target = if (form.pushed) 1f else 0f
        if (reduceMotion) push.snapTo(target) else push.animateTo(target, Motion.smooth(PUSH_DURATION))
    }
    val carrierRow = remember { AccessibilityFocus() }
    val pop = {
        form.pushed = false
        carrierRow.request()
    }
    // Before the picker's own, which ends its search first.
    BackHandler(enabled = form.pushed) { pop() }
    val pickerShown by remember { derivedStateOf { push.value > 0f } }
    val covered by remember { derivedStateOf { push.value >= 1f } }
    Layout(
        content = {
            Box(Modifier.graphicsLayer { translationX = -push.value * size.width * PUSH_PARALLAX }) {
                ChangeCarrierForm(
                    state, request, form, change, saving, carrierRow,
                    onPush = {
                        focusManager.clearFocus()
                        form.pushed = true
                        // The full list, and room for its letter rail.
                        form.large = true
                    },
                )
            }
            if (form.pushed || pickerShown) {
                Box(Modifier.graphicsLayer { translationX = (1 - push.value) * size.width }) {
                    PushedPicker(
                        state, change,
                        onChoose = { carrier ->
                            val next = change.choosing(carrier)
                            if (next.selectedCarrier != change.selectedCarrier) work.dismiss(request.id)
                            form.take(next)
                        },
                        onBack = pop,
                    )
                }
            }
        },
        modifier = modifier.fillMaxSize().clipToBounds(),
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Under the picker once it is in, the form is kept as it was, out of sight and of TalkBack's reach.
            if (!covered) placeables[0].place(0, 0)
            placeables.getOrNull(1)?.place(0, 0)
        }
    }
}

/**
 * `ChangeCarrierView`'s `Form`: the number and what changing its carrier does; the carrier, whose row pushes the picker,
 * and how it tracks; the fields the carrier asks for; the postcode a provider asks for; and why the change failed.
 * Cancel and Save in the bar, Save on only once the change is complete, and both off while it is saved. TalkBack starts
 * at the title.
 */
@Composable
private fun ChangeCarrierForm(
    state: DeliveriesState,
    request: CarrierEditorRequest,
    form: CarrierEditorForm,
    change: CarrierChange,
    saving: Boolean,
    carrierRow: AccessibilityFocus,
    onPush: () -> Unit,
) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val parcel = change.parcel
    val catalog = change.catalog
    val error = state.source.pageWork.failure(request.id)
    val titleFocus = remember { AccessibilityFocus() }
    LaunchedEffect(Unit) { titleFocus.request() }
    // `.task(id: refusalQuestion)`: asked again, the last question's answer is dropped.
    val question = change.refusalQuestion(state.source.isDemo)
    var refusedQuestion by remember { mutableStateOf<NumberRefusalRequest?>(null) }
    LaunchedEffect(question) {
        val asked = question ?: return@LaunchedEffect
        refusedQuestion = if (NumberRefusals.refuses(asked) { state.source.numberRefusal(it) }) asked else null
    }
    val refused = question != null && refusedQuestion == question
    val carrierName = catalog.info(change.selectedCarrier, language).displayName
    SheetPage(
        title = localized("detail.changeCarrier"),
        confirmation = localized(if (saving) "detail.changingCarrier" else "detail.saveCarrier"),
        onConfirm = { state.saveCarrier(request, change) },
        titleFocus = titleFocus,
        confirmTag = CHANGE_CARRIER_SAVE,
        confirmEnabled = change.canSave && !saving,
        cancellation = localized("common.cancel"),
        onCancel = { state.closeCarrierEditor() },
        cancelEnabled = !saving,
        cancelTag = CHANGE_CARRIER_CANCEL,
    ) {
        val number = CarrierCatalog.format(parcel.trackingNumber, parcel.carrier)
        FormSection(null) {
            FormRowFrame(Modifier) {
                Text(
                    number, Modifier.testTag(CHANGE_CARRIER_NUMBER), color = colors.label,
                    style = type.style(IosTextStyle.Body, FontWeight.SemiBold, FontDesign.Monospaced).sfLines(),
                )
            }
            FormRowFrame(Modifier) {
                Text(
                    localizer.text("detail.changeCarrierDescription", mapOf("number" to number)),
                    color = colors.secondaryLabel, style = type.footnote.sfLines(),
                )
            }
        }
        FormSection(localized("add.carrier")) {
            CarrierRow(change, refused, carrierRow, onPush)
            FormRowFrame(Modifier) {
                Text(change.hint(localizer), Modifier.testTag(CHANGE_CARRIER_HINT), color = colors.secondaryLabel, style = type.footnote.sfLines())
            }
        }
        val requirements = change.requirements
        if (requirements.isNotEmpty()) {
            val optional = change.postcodeRequirement?.isOptional == true
            FormSection(
                null,
                footer = if (optional) localizer.text("add.requirement.dpdPostcodeOptionalHelp", mapOf("carrier" to carrierName)) else null,
            ) {
                for (requirement in requirements) {
                    when (requirement.field) {
                        CarrierRequirement.Field.TrackingUrl -> FormRowFrame(Modifier) {
                            CarrierField(
                                form.trackingUrl, { form.trackingUrl = it }, localized("add.requirement.trackingUrl"), CHANGE_CARRIER_URL,
                                KeyboardOptions(KeyboardCapitalization.None, autoCorrectEnabled = false, keyboardType = KeyboardType.Uri),
                                singleLine = false,
                            )
                        }
                        CarrierRequirement.Field.DpdPostcode -> FormRowFrame(Modifier) {
                            val country = change.offeredPostcodeCountry
                            val carrierRequirement = change.carrierPostcodeRequirement
                            if (country != null && carrierRequirement != null) {
                                PostcodeCountryMenu(change.selectedCarrier, carrierRequirement, country, catalog) { form.chosenPostcodeCountry = it }
                                Spacer(Modifier.width(6.dp))
                            }
                            CarrierField(
                                form.deliveryPostcode, { form.deliveryPostcode = requirement.typedValue(it) },
                                localized("add.requirement.dpdPostcode"), CHANGE_CARRIER_POSTCODE,
                                KeyboardOptions(
                                    KeyboardCapitalization.Characters, autoCorrectEnabled = false,
                                    keyboardType = postcodeKeyboard(change.postcodeKeys(requirement)),
                                ),
                                postalCode = true,
                            )
                            if (requirement.isOptional) {
                                Text(
                                    localized("add.optional"), Modifier.padding(start = 6.dp), color = colors.secondaryLabel,
                                    style = type.caption.sfLines(),
                                )
                            }
                        }
                    }
                }
            }
        }
        if (change.needsProviderPostcode) {
            FormSection(null, footer = localizer.text("detail.providerInputNeeded", mapOf("provider" to change.inputProvider.orEmpty()))) {
                FormRowFrame(Modifier) {
                    CarrierField(
                        form.providerPostcode, { form.providerPostcode = it }, localized("add.requirement.dpdPostcode"), CHANGE_CARRIER_PROVIDER_POSTCODE,
                        KeyboardOptions(KeyboardCapitalization.Characters, autoCorrectEnabled = false),
                        postalCode = true,
                    )
                }
            }
        }
        if (error != null) {
            FormSection(null) {
                FormRowFrame(Modifier.testTag(CHANGE_CARRIER_ERROR)) {
                    PeekSymbol("exclamationmark.triangle.fill", font = type.body, tint = colors.red)
                    Spacer(Modifier.width(ERROR_GAP))
                    Text(error, Modifier.weight(1f), color = colors.red, style = type.body.sfLines())
                }
            }
        }
    }
}

/**
 * The carrier's row, a `NavigationLink` to the picker: the wand in ochre while automatic detection is chosen, else the
 * carrier's truck; its name, and under it in ochre that the carrier refuses the number; the chevron at its end.
 * TalkBack reads "Carrier", the name and the refusal as its value, and a button.
 */
@Composable
private fun CarrierRow(change: CarrierChange, refused: Boolean, focus: AccessibilityFocus, onPush: () -> Unit) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val ochre = colors.resolve(ExperimentalPalette.ochre)
    val name = change.carrierName(localizer)
    val refusal = localized("add.line.refused")
    val label = localized("add.carrier")
    FormRowFrame(
        Modifier
            .testTag(CHANGE_CARRIER_ROW)
            .accessibilityFocus(focus)
            .clickable(interactionSource = null, indication = RowHighlight.Row, role = Role.Button, onClick = onPush)
            .clearAndSetSemantics {
                contentDescription = label
                stateDescription = listOfNotNull(name, refusal.takeIf { refused }).joinToString(", ")
                role = Role.Button
                onClick {
                    onPush()
                    true
                }
            },
    ) {
        if (change.automatic) {
            Box(Modifier.width(MARK_WIDTH), contentAlignment = Alignment.Center) {
                PeekSymbol("wand.and.sparkles", font = type.body, tint = ochre)
            }
        } else {
            val identity = carrierIdentity(change.selectedCarrier, language)
            if (identity != null) CarrierTruck(identity) else Spacer(Modifier.width(MARK_WIDTH))
        }
        Spacer(Modifier.width(MARK_GAP))
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(name, color = colors.label, style = type.body.sfLines())
            if (refused) Text(refusal, Modifier.testTag(CHANGE_CARRIER_REFUSED), color = ochre, style = type.caption.sfLines())
        }
        PeekSymbol(
            "chevron.right", Modifier.padding(start = 8.dp),
            font = type.style(IosTextStyle.Footnote, FontWeight.SemiBold), tint = colors.tertiaryLabel,
        )
    }
}

/**
 * A field of the form as iOS draws one, its placeholder in the tertiary grey while it is empty, the cursor in the
 * accent. TalkBack reads the placeholder as its name. A postcode's ([postalCode]) offers the postcodes autofill knows.
 */
@Composable
private fun androidx.compose.foundation.layout.RowScope.CarrierField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    tag: String,
    keyboard: KeyboardOptions,
    singleLine: Boolean = true,
    postalCode: Boolean = false,
) {
    val colors = PeekTheme.colors
    val font = PeekTheme.type.body.sfLines()
    BasicTextField(
        value,
        onValueChange,
        Modifier
            .weight(1f)
            .testTag(tag)
            .semantics {
                contentDescription = placeholder
                if (postalCode) contentType = ContentType.PostalCode
            },
        textStyle = font.copy(color = colors.label),
        singleLine = singleLine,
        cursorBrush = SolidColor(colors.accent),
        keyboardOptions = keyboard,
        decorationBox = { text ->
            Box(contentAlignment = Alignment.CenterStart) {
                if (value.isEmpty()) Text(placeholder, Modifier.clearAndSetSemantics {}, color = colors.tertiaryLabel, style = font)
                text()
            }
        },
    )
}

/** The keyboard a postcode field opens with: Android has no letters that open on their numbers, so the letters. */
internal fun postcodeKeyboard(keys: CarrierChange.PostcodeKeys): KeyboardType = when (keys) {
    CarrierChange.PostcodeKeys.NumberPad -> KeyboardType.Number
    CarrierChange.PostcodeKeys.NumbersAndPunctuation, CarrierChange.PostcodeKeys.AsciiCapable -> KeyboardType.Ascii
}

/**
 * `PostcodeCountryMenu`: the postcode's country beside its field, its flag, its code and the up and down chevrons in
 * the ink, which opens the menu of the countries the field takes, the carrier's own first and the others after a
 * hairline, each with its flag and name. TalkBack reads "Delivery country" and the country's name.
 */
@Composable
private fun PostcodeCountryMenu(
    carrier: CarrierId,
    requirement: CarrierRequirement,
    country: String,
    catalog: CarrierCatalog,
    choose: (String) -> Unit,
) {
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val menus = LocalSheetMenus.current
    var bounds by remember { mutableStateOf(Rect.Zero) }
    val returnFocus = remember { AccessibilityFocus() }
    val label = localized("add.requirement.postcodeCountry")
    val name = TrackingLocation.countryName(country, language)
    val open = {
        val choices = catalog.postcodeCountryChoices(carrier, requirement, language)
        val codes = choices.own + choices.others
        val options = codes.mapIndexed { index, code ->
            MenuOption(
                "${TrackingLocation.flag(code)} ${TrackingLocation.countryName(code, language)}",
                tag = postcodeCountryTag(code),
                startsSection = index == choices.own.size && index > 0,
            )
        }
        menus?.content = {
            SheetMenu(
                options, codes.indexOf(country), bounds, bounds.right,
                onClose = {
                    menus.content = null
                    returnFocus.request()
                },
                leading = bounds.left,
                onSelect = { choose(codes[it]) },
            )
        }
    }
    Row(
        Modifier
            .testTag(CHANGE_CARRIER_COUNTRY)
            .onGloballyPositioned { bounds = it.boundsInWindow() }
            .accessibilityFocus(returnFocus)
            .clickable(interactionSource = null, indication = null, role = Role.Button, onClick = open)
            .clearAndSetSemantics {
                contentDescription = label
                stateDescription = name
                role = Role.Button
                onClick {
                    open()
                    true
                }
            },
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(TrackingLocation.flag(country), style = type.body)
        Text(country, color = colors.ink, style = type.style(IosTextStyle.Body, FontWeight.Medium))
        PeekSymbol("chevron.up.chevron.down", font = type.style(IosTextStyle.Caption2, FontWeight.SemiBold), tint = colors.secondaryLabel)
    }
}

/**
 * The picker pushed in the editor (`NavigationLink { CarrierPickerView(...) }`), on the sheet's ground: automatic
 * detection first where the number has an automatic carrier, then the parcel's own carrier, those its number fits and
 * those used before (`pickerSections`), without tags; the stack's ‹ at the bar's leading end.
 */
@Composable
private fun PushedPicker(state: DeliveriesState, change: CarrierChange, onChoose: (CarrierId?) -> Unit, onBack: () -> Unit) {
    val localizer = LocalLocalizer.current
    val parcels = state.parcels
    val sections = remember(change.parcel, change.catalog, parcels, localizer) {
        change.pickerSections(parcels).map { CarrierPickerSection(it.id, localizer.text(it.titleKey), it.carriers) }
    }
    val automatic = if (change.automaticCarrier == null) null else {
        CarrierPickerAutomatic(change.automaticDescription(localizer), recommended = false, busy = false)
    }
    Box(Modifier.fillMaxSize()) {
        LocalSheetGround.current?.invoke(Modifier.fillMaxSize())
        CarrierPicker(
            selection = change.chosenCarrier,
            sections = sections,
            onSelect = onChoose,
            onLeave = onBack,
            automatic = automatic,
            leading = { PickerBack(onBack) },
        )
    }
}

/** The stack's ‹ on its glass circle; TalkBack reads "Back", and goes there as the picker comes in. */
@Composable
private fun PickerBack(onBack: () -> Unit) {
    val colors = PeekTheme.colors
    val focus = remember { AccessibilityFocus() }
    LaunchedEffect(Unit) { focus.request() }
    val label = localized("detail.back")
    GlassSurface(CapsuleShape, Modifier.size(BACK_SIZE), backdrop = null, behind = colors.groupedBackground) {
        Box(
            Modifier
                .size(BACK_SIZE)
                .testTag(CHANGE_CARRIER_BACK)
                .accessibilityFocus(focus)
                .clickable(interactionSource = null, indication = PressStyle.tactile(0.94f), onClick = onBack)
                .semantics {
                    contentDescription = label
                    role = Role.Button
                },
            contentAlignment = Alignment.Center,
        ) {
            PeekSymbol("chevron.left", font = PeekTheme.type.fixed(BACK_SYMBOL, FontWeight.SemiBold), scale = SymbolScale.Large, tint = colors.ink)
        }
    }
}

internal fun postcodeCountryTag(code: String) = "$CHANGE_CARRIER_COUNTRY.$code"

internal const val CHANGE_CARRIER_SHEET = "changeCarrier"
internal const val CHANGE_CARRIER_SAVE = "changeCarrier.save"
internal const val CHANGE_CARRIER_CANCEL = "changeCarrier.cancel"
internal const val CHANGE_CARRIER_NUMBER = "changeCarrier.number"
internal const val CHANGE_CARRIER_ROW = "changeCarrier.carrier"
internal const val CHANGE_CARRIER_REFUSED = "changeCarrier.refused"
internal const val CHANGE_CARRIER_HINT = "changeCarrier.hint"
internal const val CHANGE_CARRIER_URL = "changeCarrier.trackingUrl"
internal const val CHANGE_CARRIER_POSTCODE = "changeCarrier.postcode"
internal const val CHANGE_CARRIER_COUNTRY = "changeCarrier.country"
internal const val CHANGE_CARRIER_PROVIDER_POSTCODE = "changeCarrier.providerPostcode"
internal const val CHANGE_CARRIER_ERROR = "changeCarrier.error"
internal const val CHANGE_CARRIER_BACK = "changeCarrier.back"

private const val PUSH_DURATION = 0.5
/** How far the form goes left as the picker comes in, of its width, as UIKit's push moves the page under. */
private const val PUSH_PARALLAX = 0.3f
private val MARK_WIDTH = 27.dp
private val MARK_GAP = 10.dp
private val ERROR_GAP = 12.dp
private val BACK_SIZE = 44.dp
private const val BACK_SYMBOL = 17f
