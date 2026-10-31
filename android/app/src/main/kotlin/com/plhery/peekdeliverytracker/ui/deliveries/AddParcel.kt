package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.ClipData
import android.content.ClipDescription
import android.view.KeyEvent
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.Crossfade
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isAltPressed
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.isMetaPressed
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import com.plhery.peekdeliverytracker.core.AddParcelView as AddParcelLogic
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierRecognition
import com.plhery.peekdeliverytracker.core.CarrierRequirement
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.FirstParcelRequest
import com.plhery.peekdeliverytracker.core.NumberInText
import com.plhery.peekdeliverytracker.core.NumberRefusals
import com.plhery.peekdeliverytracker.core.OrderShop
import com.plhery.peekdeliverytracker.core.ParcelAddition
import com.plhery.peekdeliverytracker.core.PostcodeFill
import com.plhery.peekdeliverytracker.core.TrackingInputMatch
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponseAmazonShippingStatus
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.GivenPostcode
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.NoSuggestions
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.CarrierTruck
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.experimentalSurface
import com.plhery.peekdeliverytracker.ui.keptFocus
import com.plhery.peekdeliverytracker.ui.sheet.FullScreenCover
import com.plhery.peekdeliverytracker.ui.sheet.KeyboardScrollColumn
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetGround
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.CappedDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.ProvideDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import com.plhery.peekdeliverytracker.ui.theme.carrierIdentity
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlin.math.roundToInt
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive

/**
 * Add (`showingAdd`, ParcelListView's `addShown(asForm:)`): wherever the list isn't side by side, a full-screen cover
 * over the whole window (`.fullScreenCover`), which only its ✕, Escape and the back close; side by side, the app's sheet
 * as a form in the window's middle (`.sheet` with `.presentationSizing(.form)`), which a tap beside it closes too. None
 * closes it while it saves (`interactiveDismissDisabled(saving)`), and once saved it goes down still saying so. Once it
 * has gone the list shows the card it added (`addClosed`). What was typed, the carrier chosen and the picker open stay
 * through a window made again, the field with the focus keeping it and the keyboard.
 */
@Composable
internal fun AddParcelHost(state: DeliveriesState) {
    val request = state.addition
    // The request Add shows, kept while it goes down.
    var shown by remember { mutableStateOf(request) }
    if (request != null) shown = request
    val adding = shown
    val catalog = LocalCarrierIdentities.current?.catalog ?: CarrierCatalog.shared
    val form = rememberSaveable(adding?.id, saver = AddForm.Saver) { AddForm.start(adding?.draft, catalog) }
    val work = state.source.pageWork
    // Saved, it says so until it has gone, as `save()` leaves `saving` on.
    val saving = adding != null && (work.working(adding.id) || work.added(adding.id) != null)
    val title = localized("add.title")
    val cover = !state.sheetsAsForms
    val content: @Composable () -> Unit = {
        if (adding != null) {
            // Under the picker, TalkBack reaches only the picker.
            val hidden = if (form.showingPicker) Modifier.clearAndSetSemantics {} else Modifier
            AddParcelView(state, adding, form, saving, cover, hidden.testTag(ADD_PARCEL))
        }
    }
    if (cover) {
        FullScreenCover(
            visible = request != null, onDismiss = state::closeAdd, paneTitle = title,
            onClosed = { state.addClosed(adding) }, dismissible = !saving, content = content,
        )
    } else {
        Sheet(
            visible = request != null, onDismiss = state::closeAdd, paneTitle = title, detents = listOf(SheetDetent.Large),
            onClosed = { state.addClosed(adding) }, dismissible = !saving, content = content,
        )
    }
}

/**
 * What Add holds (`AddParcelView`'s `@State`): the name and the text typed, the number chosen among several, the carrier
 * chosen, the number to add as typed, recognition and the answers of the checks, the link and the postcode typed, the
 * postcode's country chosen and the postcode filled in from those given before, Amazon's check asked again, the picker
 * open, the keyboard opened once, and a paste that found nothing. All of it stays through a window made again, as the
 * iPhone's `@State` does, so no check is asked again for what it answered.
 */
@Stable
internal class AddForm(
    label: String,
    trackingInput: String,
    pick: String?,
    carrierOverride: CarrierId?,
    asTyped: String?,
    recognition: CarrierRecognition,
    refusedQuestion: NumberRefusalRequest?,
    verifiedCarrier: CarrierDetectionResponse?,
    lookedUp: String?,
    trackingUrl: String,
    deliveryPostcode: String,
    chosenPostcodeCountry: String?,
    postcodeFill: PostcodeFill,
    lookupAttempt: Int,
    showingPicker: Boolean,
    didFocus: Boolean,
    pasteFailed: Boolean,
    preparedFor: CarrierId?,
) {
    var label by mutableStateOf(label)
    var trackingInput by mutableStateOf(trackingInput)
    var pick by mutableStateOf(pick)
    var carrierOverride by mutableStateOf(carrierOverride)
    var asTyped by mutableStateOf(asTyped)
    var recognition by mutableStateOf(recognition)
    var refusedQuestion by mutableStateOf(refusedQuestion)
    var verifiedCarrier by mutableStateOf(verifiedCarrier)

    /** Amazon's check this opening last ran to its end, the number and its attempt (the `.task(id:)` it ran for). */
    var lookedUp = lookedUp
    var trackingUrl by mutableStateOf(trackingUrl)
    var deliveryPostcode by mutableStateOf(deliveryPostcode)
    var chosenPostcodeCountry by mutableStateOf(chosenPostcodeCountry)
    var postcodeFill by mutableStateOf(postcodeFill)
    var lookupAttempt by mutableIntStateOf(lookupAttempt)
    var showingPicker by mutableStateOf(showingPicker)
    var didFocus = didFocus
    var pasteFailed by mutableStateOf(pasteFailed)

    /** The carrier whose details were last prepared (`onChange(of: resolvedCarrier, initial: true)`). */
    var preparedFor = preparedFor

    fun addition(demo: Boolean, catalog: CarrierCatalog, region: String?) = ParcelAddition(
        trackingInput, pick, carrierOverride, asTyped, recognition, refusedQuestion, verifiedCarrier, trackingUrl,
        deliveryPostcode, chosenPostcodeCountry, demo, catalog, region,
    )

    /** Takes the text, the number chosen, the carrier chosen and recognition from `addition`. */
    fun take(addition: ParcelAddition) {
        trackingInput = addition.trackingInput
        pick = addition.pick
        carrierOverride = addition.carrierOverride
        recognition = addition.recognition
    }

    companion object {
        /** Add as it opens, with `draft` in its field, settled at once (`init(draft:)`). */
        fun start(draft: String?, catalog: CarrierCatalog) = AddForm(
            label = "", trackingInput = draft.orEmpty(), pick = null, carrierOverride = null, asTyped = null,
            recognition = CarrierRecognition(draft?.let { ParcelAddition.trackingNumber(it, catalog) }), refusedQuestion = null,
            verifiedCarrier = null, lookedUp = null, trackingUrl = "", deliveryPostcode = "",
            chosenPostcodeCountry = null, postcodeFill = PostcodeFill(), lookupAttempt = 0, showingPicker = false,
            didFocus = false, pasteFailed = false, preparedFor = null,
        )

        val Saver = listSaver<AddForm, Any>(
            save = {
                val recognition = it.recognition
                listOf(
                    it.label, it.trackingInput, it.pick.orEmpty(), it.carrierOverride?.rawValue.orEmpty(), it.asTyped.orEmpty(),
                    recognition.settledNumber ?: NONE, it.trackingUrl, it.deliveryPostcode, it.chosenPostcodeCountry.orEmpty(),
                    it.postcodeFill.postcode ?: NONE, it.postcodeFill.forgotten, it.lookupAttempt, it.showingPicker, it.didFocus,
                    it.pasteFailed, it.preparedFor?.rawValue ?: NONE, recognition.failedNumber ?: NONE,
                    recognition.answer?.let(ParcelAddition::keptAnswer) ?: NONE, it.verifiedCarrier?.let(ParcelAddition::keptAnswer) ?: NONE,
                    it.refusedQuestion?.let(ParcelAddition::keptQuestion) ?: NONE, it.lookedUp ?: NONE,
                )
            },
            restore = {
                fun text(index: Int) = (it[index] as String).ifEmpty { null }
                fun kept(index: Int) = (it[index] as String).takeIf { value -> value != NONE }
                AddForm(
                    it[0] as String, it[1] as String, text(2), text(3)?.let(::CarrierId), text(4),
                    CarrierRecognition(kept(5), kept(17)?.let(ParcelAddition::answer), kept(16)),
                    kept(19)?.let(ParcelAddition::question), kept(18)?.let(ParcelAddition::answer), kept(20),
                    it[6] as String, it[7] as String, text(8), PostcodeFill(kept(9), it[10] as Boolean), it[11] as Int,
                    it[12] as Boolean, it[13] as Boolean, it[14] as Boolean, kept(15)?.let(::CarrierId),
                )
            },
        )

        /** Kept for a value that is none, where an empty one means something else. */
        private const val NONE = "\u0000"
    }
}

/** The fields Add moves the focus between (`focusedField`), and the one that has it. */
@Stable
private class AddFocus {
    val requesters = ParcelAddition.Field.entries.associateWith { FocusRequester() }
    var focused by mutableStateOf<ParcelAddition.Field?>(null)

    fun request(field: ParcelAddition.Field) {
        runCatching { requesters.getValue(field).requestFocus() }
    }
}

/**
 * `AddParcelView`: the title and its ✕ over the form, with it at the accessibility sizes and where the window is too low
 * for the title, Add and the number's field together (a phone on its side, its keyboard up); the tracking field with what
 * it found under it, the details the carrier needs, the name, and why it failed; Add pinned at the foot, above the
 * keyboard. A number settles as the field is left, pasted, filled in or chosen, and then the carriers are asked about
 * it, Amazon checked, and a carrier picked by hand that refuses it pointed out; the keyboard opens on the field 300 ms
 * after Add has come up. A carrier found plays a tick (B-H05).
 */
@Composable
private fun AddParcelView(state: DeliveriesState, request: AddRequest, form: AddForm, saving: Boolean, cover: Boolean, modifier: Modifier) {
    val source = state.source
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val reduceMotion = PeekTheme.reduceMotion
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val catalog = LocalCarrierIdentities.current?.catalog ?: CarrierCatalog.shared
    val region = remember { CarrierCatalog.deviceCountry() }
    val demo = source.isDemo
    val addition = form.addition(demo, catalog, region)
    val current by rememberUpdatedState { form.addition(demo, catalog, region) }
    val work = source.pageWork
    val focusManager = LocalFocusManager.current
    val focus = remember { AddFocus() }
    val motion = remember { AddMotion() }

    // Saved: the list hears of the parcel, and Add goes down.
    val added = work.added(request.id)
    LaunchedEffect(added) {
        if (added == null) return@LaunchedEffect
        state.added(request, added)
        state.closeAdd()
    }
    // Once Add has come up, the keyboard on the field (the scanner comes with its step).
    LaunchedEffect(request.id) {
        if (form.didFocus) return@LaunchedEffect
        form.didFocus = true
        delay(FOCUS_DELAY)
        focus.request(ParcelAddition.Field.Tracking)
    }
    // A required postcode starts from the last one given that fits; an optional one is only offered.
    val resolved = addition.resolvedCarrier
    LaunchedEffect(resolved) {
        if (form.preparedFor == resolved) return@LaunchedEffect
        form.preparedFor = resolved
        prepareRequiredDetails(form, current(), source.givenPostcodes)
    }
    SensoryFeedbackEffect(SensoryFeedback.Selection, resolved) { old, new -> old != new && new != CarrierId.Unknown }
    // Amazon numbers are verified before they can be added, a moment after the number stops changing; once, through a
    // window made again.
    val lookup = addition.lookupTrackingNumber
    LaunchedEffect(lookup, form.lookupAttempt) {
        val key = lookup?.let { "$it:${form.lookupAttempt}" }
        if (key != null && key == form.lookedUp) return@LaunchedEffect
        form.lookedUp = null
        val number = lookup ?: return@LaunchedEffect
        try {
            delay(LOOKUP_DELAY)
            val result = source.detectCarrier(number)
            ensureActive()
            if (result.trackingNumber != number) throw DeliveryApiError.InvalidResponse()
            form.verifiedCarrier = result
        } catch (error: CancellationException) {
            throw error
        } catch (_: Exception) {
            form.verifiedCarrier = CarrierDetectionResponse(
                trackingNumber = number, amazonShippingStatus = CarrierDetectionResponseAmazonShippingStatus.Unavailable,
                carrier = CarrierId.AmazonLogistics,
            )
        }
        form.lookedUp = key
    }
    // The carriers asked about the settled number.
    val recognitionRequest = addition.recognitionRequest
    LaunchedEffect(recognitionRequest) {
        val number = recognitionRequest ?: return@LaunchedEffect
        form.recognition = form.recognition.copy(failedNumber = null, answer = null)
        try {
            val result = source.detectCarrier(number)
            ensureActive()
            if (result.trackingNumber != number) throw DeliveryApiError.InvalidResponse()
            form.recognition = form.recognition.copy(answer = result)
        } catch (error: CancellationException) {
            throw error
        } catch (_: Exception) {
            form.recognition = form.recognition.copy(failedNumber = number)
        }
    }
    // Asked again, the last question's answer is dropped.
    val question = addition.refusalQuestion
    LaunchedEffect(question) {
        val asked = question ?: return@LaunchedEffect
        val refuses = NumberRefusals.refuses(asked) { source.numberRefusal(it) }
        ensureActive()
        form.refusedQuestion = if (refuses) asked else null
    }
    // Escape closes Add, or the picker over it (its Cancel, `.cancellationAction`), which then leaves Add up.
    AddEscape(enabled = !saving) { if (form.showingPicker) form.showingPicker = false else state.closeAdd() }

    val actions = remember(form, focus, motion) { AddActions(state, request, form, focus, motion, focusManager) }
    actions.current = { current() }
    actions.reduceMotion = reduceMotion
    actions.saving = saving
    // A change's spring covers that change only, as `withAnimation` does: what comes later moves on the form's own.
    SideEffect { motion.duration = null }

    val error = if (form.pasteFailed) localizer.text("add.pasteFailed") else work.failure(request.id)
    val duplicate = work.duplicate(request.id)

    // What the title and Add leave the form, which the number's field needs.
    var viewHeight by remember { mutableIntStateOf(0) }
    var headingHeight by remember { mutableIntStateOf(0) }
    var buttonHeight by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val low by remember(density) {
        derivedStateOf { viewHeight > 0 && viewHeight - headingHeight - buttonHeight < with(density) { FIELD_ROOM.roundToPx() } }
    }
    val headingScrolls = large || low
    val heading = Modifier.onSizeChanged { headingHeight = it.height }

    Column(
        modifier
            .fillMaxSize()
            .background(colors.background)
            .then(if (cover) Modifier.windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)) else Modifier)
            .onSizeChanged { viewHeight = it.height },
    ) {
        if (!headingScrolls) AddHeading(saving, state::closeAdd, heading)
        KeyboardScrollColumn(
            Modifier.weight(1f).fillMaxWidth(),
            rememberScrollState(),
            dismissesKeyboard = true,
            contentModifier = Modifier.fillMaxWidth(),
        ) {
            if (headingScrolls) AddHeading(saving, state::closeAdd, heading)
            // While it saves, the form's controls are off and dim as they do (`.disabled(saving)`); TalkBack still
            // reads them, as off.
            CompositionLocalProvider(LocalFormEnabled provides !saving) {
                Column(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .widthIn(max = CONTENT_WIDTH)
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        TrackingSection(addition, form, focus, actions, saving)
                        val found = addition.parsed.trackingNumber.isNotEmpty()
                        val shownFeedback = remember { Kept(addition) }
                        if (found) shownFeedback.value = addition
                        AddRows(found, motion.duration ?: FEEDBACK_MOTION, reduceMotion, Modifier.testTag(ADD_FEEDBACK)) {
                            CarrierFeedback(state, shownFeedback.value, form, actions)
                        }
                    }
                    val details = addition.needsRequiredDetails
                    val shownDetails = remember { Kept(addition) }
                    if (details) shownDetails.value = addition
                    AddRows(details, motion.duration ?: STATE_MOTION, reduceMotion, Modifier.testTag(ADD_DETAILS)) {
                        RequiredDetails(state, shownDetails.value, form, focus, actions)
                    }
                    NameField(form, focus, actions, addition)
                    val shownError = remember { Kept(error to duplicate) }
                    if (error != null) shownError.value = error to duplicate
                    AddRows(error != null, STATE_MOTION, reduceMotion, fadeOnly = true) {
                        val (message, existing) = shownError.value
                        if (message != null) ErrorBanner(message, existing) { id -> state.openExisting(id) }
                    }
                }
            }
        }
        AddButton(addition, saving, cover, actions::save, Modifier.onSizeChanged { buttonHeight = it.height })
    }
    AddPicker(state, addition, form, actions)
}

/** Whether the form's controls are on (`.disabled(saving)`'s `isEnabled`). */
private val LocalFormEnabled = compositionLocalOf { true }

/** A value kept as it was last shown, for what goes out with a transition. */
private class Kept<T>(var value: T)

/** The spring a change asked for (`withAnimation`): a value filled in, a number chosen. */
@Stable
private class AddMotion {
    var duration: Double? = null
}

/**
 * What Add does, as its buttons and fields ask: Add's own (`paste`, `fill`, `selectCarrier`, `choose`, `use`,
 * `submitTracking`, `save`), on the latest of what it holds ([current]).
 */
private class AddActions(
    private val state: DeliveriesState,
    private val request: AddRequest,
    private val form: AddForm,
    private val focus: AddFocus,
    private val motion: AddMotion,
    private val focusManager: androidx.compose.ui.focus.FocusManager,
) {
    var current: () -> ParcelAddition = { error("Unset") }
    var reduceMotion = false
    var saving = false

    /**
     * The field's text changed (`onChange(of: trackingInput)`): no duplicate, no error, no carrier picked by hand. As
     * much of it as a paste takes, which the window can keep.
     */
    fun type(text: String) {
        motion.duration = null
        setInput(FirstParcelRequest.taken(text))
    }

    private fun setInput(text: String) {
        clearErrors()
        form.take(current().typing(text))
    }

    private fun clearErrors() {
        form.pasteFailed = false
        state.source.pageWork.dismiss(request.id)
    }

    /**
     * `paste()`: what the clipboard holds as text in the field, trimmed and settled, the keyboard on it; else why
     * nothing came. As much of it as the ways in take ([FirstParcelRequest.pasteLimit]), where the iPhone takes it all:
     * the window keeps what the field holds, and can't keep a few hundred thousand characters.
     */
    fun paste() {
        state.source.action("parcel-paste")
        val value = state.readClipboard()?.let { FirstParcelRequest.Paste(it).draft }
        if (value == null) {
            state.source.pageWork.dismiss(request.id)
            form.pasteFailed = true
            return
        }
        motion.duration = if (reduceMotion) null else FILL_MOTION
        setInput(value)
        focus.request(ParcelAddition.Field.Tracking)
        form.take(current().settling(value))
    }

    /** `selectCarrier(_:)`, from the picker. */
    fun selectCarrier(carrier: CarrierId?) {
        form.carrierOverride = carrier
        focusManager.clearFocus()
        clearErrors()
    }

    /** `choose(_:)`: one of several numbers, asked about at once, the keyboard leaving the list and the button in view. */
    fun choose(number: NumberInText) {
        motion.duration = if (reduceMotion) null else CHOOSE_MOTION
        form.pick = number.normalized
        focusManager.clearFocus()
        form.carrierOverride = null
        clearErrors()
        form.recognition = form.recognition.copy(settledNumber = number.normalized)
    }

    /** `use(_:)`: the number the check digit suggests in the field, as a paste would put it. */
    fun use(suggestion: String) {
        motion.duration = if (reduceMotion) null else FILL_MOTION
        setInput(suggestion)
        form.take(current().settling(suggestion))
    }

    /** `submitTracking()`: Next goes to the link, then the postcode, while either still wants something, else the name. */
    fun submitTracking() {
        focus.request(current().nextField)
    }

    /** The name's Done: Add, where it can, else back to the number. */
    fun submitName() {
        if (current().canSave) save() else focus.request(ParcelAddition.Field.Tracking)
    }

    /** The field the focus left (`onChange(of: focusedField)`): leaving the tracking field settles its number. */
    fun left(field: ParcelAddition.Field) {
        if (field != ParcelAddition.Field.Tracking || saving) return
        val addition = current()
        form.recognition = form.recognition.copy(settledNumber = addition.normalizedNumber)
    }

    /** "Track it as typed": the number added as it stands. */
    fun asTyped() {
        form.asTyped = current().normalizedNumber
        save()
    }

    /** `save()`: saving from before the field gives up the focus, as `saving = true` goes with `focusedField = nil`. */
    fun save() {
        val addition = current()
        if (!addition.canSave || saving) return
        // The field left to save settles nothing ([left]); what Add then shows follows the save.
        saving = true
        focusManager.clearFocus()
        clearErrors()
        state.saveAddition(request, addition, form.label)
    }

    fun openPicker() {
        // Leaving the field settles the number, so the check starts as the picker opens.
        focusManager.clearFocus()
        form.showingPicker = true
    }
}

/** `prepareRequiredDetails(for:)`. */
private fun prepareRequiredDetails(form: AddForm, addition: ParcelAddition, given: List<GivenPostcode>) {
    form.postcodeFill = form.postcodeFill.prepare()
    val requirement = addition.postcodeRequirement ?: return
    if (requirement.isOptional || form.deliveryPostcode.isNotEmpty()) return
    val suggestion = addition.previousPostcode(requirement, given) ?: return
    form.postcodeFill = form.postcodeFill.fill(suggestion)
    form.deliveryPostcode = suggestion
}

/**
 * Rows of the form coming and going (B-AN11, B-AN12): risen 6 and faded in on the snappy spring while the form makes
 * room, or faded only ([fadeOnly]); at once under Reduce Motion. On their way out they take no tap and TalkBack
 * doesn't reach them.
 */
@Composable
private fun ColumnScope.AddRows(
    visible: Boolean,
    duration: Double,
    reduceMotion: Boolean,
    modifier: Modifier = Modifier,
    fadeOnly: Boolean = false,
    content: @Composable () -> Unit,
) {
    val rise = with(LocalDensity.current) { ROW_RISE.roundToPx() }
    val fade = Motion.snappy<Float>(duration)
    val size = Motion.snappy<IntSize>(duration)
    val offset = Motion.snappy<IntOffset>(duration)
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = when {
            reduceMotion -> EnterTransition.None
            fadeOnly -> fadeIn(fade) + expandVertically(size, Alignment.Top, clip = false)
            else -> fadeIn(fade) + slideInVertically(offset) { rise } + expandVertically(size, Alignment.Top, clip = false)
        },
        exit = when {
            reduceMotion -> ExitTransition.None
            fadeOnly -> fadeOut(fade) + shrinkVertically(size, Alignment.Top, clip = false)
            else -> fadeOut(fade) + slideOutVertically(offset) { rise } + shrinkVertically(size, Alignment.Top, clip = false)
        },
    ) { Going { content() } }
}

/** What is going takes no tap, and TalkBack doesn't reach it. */
@Composable
private fun AnimatedVisibilityScope.Going(content: @Composable () -> Unit) {
    val leaving = transition.targetState == EnterExitState.PostExit
    Box(if (leaving) Modifier.clearAndSetSemantics {}.then(Untouched) else Modifier, propagateMinConstraints = true) { content() }
}

/**
 * `heading`: "Add a parcel" in title2 semibold, a heading, and the ✕ on a cream circle 36 across in a 44 square, read
 * "Close", off while Add saves.
 */
@Composable
private fun AddHeading(saving: Boolean, close: () -> Unit, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val closeLabel = localized("common.close")
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Row(
            Modifier
                .widthIn(max = CONTENT_WIDTH)
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                localized("add.title"),
                Modifier.weight(1f).testTag(ADD_TITLE).semantics { heading() },
                color = colors.ink,
                style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).copy(letterSpacing = (-0.5).sp).sfLines(),
            )
            Box(
                Modifier
                    .size(44.dp)
                    .testTag(ADD_CLOSE)
                    .clickable(interactionSource = null, indication = PlainPress, enabled = !saving, role = Role.Button, onClick = close)
                    .semantics { contentDescription = closeLabel },
                contentAlignment = Alignment.Center,
            ) {
                Box(Modifier.size(36.dp).background(colors.cream, CircleShape), contentAlignment = Alignment.Center) {
                    PeekSymbol("xmark", font = type.style(IosTextStyle.Body, FontWeight.Medium), tint = colors.ink.copy(alpha = if (saving) 0.4f else 1f))
                }
            }
        }
    }
}

/**
 * `trackingField`: its label with Paste beside it, under it where they don't fit on one line; the field, two to four
 * lines; and, once the number has settled, why it can't be added as it stands: no number found, a pickup code, a
 * placeholder, a shop's order number, or a check digit that doesn't add up, with the number it may be and the way to
 * add it as typed.
 */
@Composable
private fun TrackingSection(addition: ParcelAddition, form: AddForm, focus: AddFocus, actions: AddActions, saving: Boolean) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        FlowRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            itemVerticalAlignment = Alignment.CenterVertically,
        ) {
            Text(localized("add.tracking"), Modifier.padding(end = 4.dp), color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines())
            PasteButton(actions::paste)
        }
        AddTextField(
            value = form.trackingInput,
            onValueChange = actions::type,
            placeholder = localized("add.trackingPlaceholder"),
            label = localized("add.tracking"),
            tag = ADD_TRACKING,
            field = ParcelAddition.Field.Tracking,
            focus = focus,
            actions = actions,
            keyboard = KeyboardOptions(KeyboardCapitalization.None, autoCorrectEnabled = false, imeAction = ImeAction.Next),
            onSubmit = actions::submitTracking,
            minLines = 2,
            maxLines = 4,
            suggestions = false,
        )
        val settled = addition.settled
        if (addition.notFound) Note(localized("add.notFound"), ADD_NOT_FOUND)
        if (addition.pickupCode && settled) HeldBackNote(localized("add.pickupCode.title"), localized("add.pickupCode.body"), ADD_PICKUP_CODE)
        if (addition.placeholderNumber && settled) Note(localized("tracking.failure.placeholderNumber"), ADD_PLACEHOLDER)
        val order = addition.order
        if (order != null && settled) {
            HeldBackNote(localizer.text("door.order.${order.rawValue}.title"), localizer.text("door.order.${order.rawValue}.body"), ADD_ORDER)
            if (order == OrderShop.Amazon) AmazonOrdersLink("")
        }
        val typo = addition.typo
        if (typo != null && settled) {
            HeldBackNote(
                localized("door.typo.check"), localized(if (typo.suggestion == null) "door.typo.compare" else "door.typo.letter"), ADD_TYPO,
            )
            val suggestion = typo.suggestion
            if (suggestion != null) {
                val label = AddParcelLogic.suggestionLabel(suggestion, addition.normalizedNumber) {
                    localizer.text("door.typo.suggest", mapOf("number" to it))
                }
                val mono = type.style(IosTextStyle.Subheadline, design = FontDesign.Monospaced)
                val ochreSurface = colors.resolve(ExperimentalPalette.ochreSurface)
                val text = AnnotatedString.Builder(label.text).apply {
                    addStyle(SpanStyle(fontFamily = mono.fontFamily, fontFeatureSettings = mono.fontFeatureSettings), label.number.first, label.number.last + 1)
                    label.marked.forEach { addStyle(SpanStyle(background = ochreSurface), it.first, it.last + 1) }
                }.toAnnotatedString()
                TintedButton(
                    text, { actions.use(suggestion) }, ADD_SUGGESTION,
                    label = localizer.text("door.typo.suggest", mapOf("number" to suggestion)),
                )
            }
            TintedButton(AnnotatedString(localized("door.typo.asTyped")), actions::asTyped, ADD_AS_TYPED)
        }
    }
}

/**
 * Paste (`captureActions`): the clipboard symbol and "Paste" in caption, in the secondary grey, 44 tall; a plain button,
 * at half strength while off.
 */
@Composable
private fun PasteButton(paste: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val enabled = LocalFormEnabled.current
    Row(
        Modifier
            .heightIn(min = 44.dp)
            .testTag(ADD_PASTE)
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = paste)
            .plainDisabled(enabled),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeekSymbol("doc.on.clipboard", Modifier.clearAndSetSemantics {}, font = type.caption, tint = colors.secondaryLabel)
        Text(localized("add.paste"), color = colors.secondaryLabel, style = type.caption, maxLines = 1)
    }
}

/** A line under the field in caption, in the warning colour. */
@Composable
private fun Note(text: String, tag: String) {
    Text(text, Modifier.testTag(tag), color = PeekTheme.colors.warning, style = PeekTheme.type.caption.sfLines())
}

/** `heldBackNote`: why the field holds no number to add, its title in semibold over what to paste instead, read as one. */
@Composable
private fun HeldBackNote(title: String, body: String, tag: String) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(
        Modifier.testTag(tag).semantics(mergeDescendants = true) {},
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(title, color = colors.warning, style = type.style(IosTextStyle.Caption, FontWeight.SemiBold).sfLines())
        Text(body, color = colors.warning, style = type.caption.sfLines())
    }
}

/** "Open my Amazon orders", a link to the orders page of the store that sent the number. */
@Composable
private fun AmazonOrdersLink(number: String) {
    val uriHandler = LocalUriHandler.current
    TintedButton(AnnotatedString(localized("add.openAmazonOrders")), { uriHandler.openUri(CarrierCatalog.amazonOrdersUrl(number)) }, ADD_AMAZON_ORDERS)
}

/**
 * A button as SwiftUI draws one by default: its words in subheadline, in the accent, fading under the finger; grey while
 * off.
 */
@Composable
private fun TintedButton(text: AnnotatedString, onClick: () -> Unit, tag: String, label: String? = null) {
    val colors = PeekTheme.colors
    val enabled = LocalFormEnabled.current
    Text(
        text,
        Modifier
            .heightIn(min = 22.dp)
            .testTag(tag)
            .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = onClick)
            .then(if (label != null) Modifier.semantics { contentDescription = label } else Modifier),
        color = if (enabled) colors.accent else colors.tertiaryLabel,
        style = PeekTheme.type.subheadline.sfLines(),
    )
}

/**
 * `carrierFeedback`: the carrier line, and the number found in a link or a text, or the choice between several numbers;
 * how the carrier tracks, or what Amazon said; a carrier that knows the number better than the one picked by hand;
 * Amazon's check to ask again; and Amazon's orders where only an account follows the parcel.
 */
@Composable
private fun CarrierFeedback(state: DeliveriesState, addition: ParcelAddition, form: AddForm, actions: AddActions) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val catalog = addition.catalog
    Column(
        Modifier
            .fillMaxWidth()
            .then(if (reduceMotion) Modifier else Modifier.animateContentSize(Motion.snappy(STATE_MOTION))),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        if (addition.several.isEmpty()) {
            CarrierLine(addition, actions::openPicker)
            val parsed = addition.parsed
            if (parsed.source == TrackingInputMatch.Source.Link || parsed.source == TrackingInputMatch.Source.Text) {
                val number = CarrierCatalog.format(parsed.trackingNumber, addition.resolvedCarrier)
                val read = localizer.text(if (parsed.source == TrackingInputMatch.Source.Link) "add.foundLink" else "add.foundText", mapOf("number" to number))
                Row(
                    Modifier.testTag(ADD_FOUND).clearAndSetSemantics { contentDescription = read },
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PeekSymbol("barcode", font = type.caption, tint = colors.ink)
                    Text(number, color = colors.ink, style = type.style(IosTextStyle.Caption, design = FontDesign.Monospaced).sfLines())
                }
            }
        } else {
            NumberChoice(state, addition, actions)
        }
        addition.hint(localizer)?.let { Text(it, Modifier.testTag(ADD_HINT), color = colors.secondaryLabel, style = type.caption.sfLines()) }
        addition.knownElsewhere?.let { known ->
            val name = catalog.info(known, language).displayName
            Text(localizer.text("add.carrierKnownElsewhere", mapOf("carrier" to name)), color = colors.secondaryLabel, style = type.caption.sfLines())
            TintedButton(AnnotatedString(localizer.text("add.useCarrier", mapOf("carrier" to name))), { form.carrierOverride = known }, ADD_USE_CARRIER)
        }
        if (addition.amazonCheckUnavailable) {
            Text(localized("add.amazonCheckUnavailable"), Modifier.testTag(ADD_AMAZON_UNAVAILABLE), color = colors.secondaryLabel, style = type.caption.sfLines())
            TintedButton(
                AnnotatedString(localized("add.amazonRetry")),
                {
                    form.verifiedCarrier = null
                    form.lookupAttempt++
                },
                ADD_AMAZON_RETRY,
            )
        }
        if (addition.accountRequired) AmazonOrdersLink(addition.parsed.trackingNumber)
    }
}

/**
 * `carrierLine`: one quiet line under the number, which opens the picker: the carrier's truck once one is known, a
 * spinner while the carriers are asked, else the wand; its name and what the check found, beside each other where they
 * fit, else one over the other, and "Change" or "Choose" underlined at its end, or under them at the accessibility
 * sizes. TalkBack reads the name and the finding, and what a tap does. An Amazon number keeps its carrier.
 */
@Composable
private fun CarrierLine(addition: ParcelAddition, open: () -> Unit) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val ochre = colors.resolve(ExperimentalPalette.ochre)
    val carrier = addition.lineCarrier
    val name = addition.lineName(localizer)
    val detail = addition.lineDetail(localizer)
    val actionKey = if (addition.choosingCarrier) "add.line.choose" else "add.line.change"
    val action = localized(actionKey)
    val enabled = !addition.amazonNumber && LocalFormEnabled.current
    val description = listOfNotNull(name, detail).joinToString(", ")
    val nameStyle = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines()
    val detailColor = if (addition.choosingCarrier || addition.refused) ochre else colors.secondaryLabel
    val lineAction = @Composable {
        if (!addition.amazonNumber) {
            Text(action, color = colors.secondaryLabel, style = type.caption.copy(textDecoration = TextDecoration.Underline).sfLines())
        }
    }
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 44.dp)
            .testTag(ADD_CARRIER)
            .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = enabled, role = Role.Button, onClick = open)
            .clearAndSetSemantics {
                // The way to change it said once, as what a tap does (`accessibilityHint`).
                contentDescription = description
                role = Role.Button
                if (enabled) {
                    onClick(label = action) {
                        open()
                        true
                    }
                }
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.width(MARK_WIDTH).testTag(ADD_CARRIER_MARK), contentAlignment = Alignment.Center) {
            val identity = carrier?.let { carrierIdentity(it, language) }
            when {
                carrier != null && identity != null -> CarrierTruck(identity)
                carrier != null -> Unit
                addition.checking -> ActivityIndicator(Modifier.testTag(ADD_CHECKING), size = MINI_SPINNER, tint = colors.secondaryLabel)
                else -> PeekSymbol("wand.and.sparkles", font = type.caption, tint = ochre)
            }
        }
        if (large) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(name, color = colors.ink, style = nameStyle)
                if (detail != null) Text(detail, Modifier.testTag(ADD_LINE_DETAIL), color = detailColor, style = type.caption.sfLines())
                lineAction()
            }
        } else {
            // On their first baselines where they fit beside each other.
            FlowRow(
                Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(name, Modifier.alignByBaseline().testTag(ADD_LINE_NAME), color = colors.ink, style = nameStyle)
                if (detail != null) Text(detail, Modifier.alignByBaseline().testTag(ADD_LINE_DETAIL), color = detailColor, style = type.caption.sfLines())
            }
            lineAction()
        }
    }
}

/**
 * `numberChoice`: several numbers in one text, in the order written, the first added unless another is chosen; for a
 * guest, the question of following them all, and signing in to keep them together.
 */
@Composable
private fun NumberChoice(state: DeliveriesState, addition: ParcelAddition, actions: AddActions) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val several = addition.several
    val picked = addition.picked?.normalized
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            localizer.text("door.several.title.many", mapOf("count" to several.size)),
            Modifier.testTag(ADD_SEVERAL).semantics { heading() },
            color = colors.ink,
            style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines(),
        )
        for (number in several) NumberRow(addition, number, chosen = number.normalized == picked) { actions.choose(number) }
        // Someone signed in keeps them together already.
        if (state.source.isGuest) {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp), itemVerticalAlignment = Alignment.CenterVertically) {
                Text(localized("door.several.question"), color = colors.secondaryLabel, style = type.caption.sfLines())
                val enabled = LocalFormEnabled.current
                Text(
                    localized("door.several.signIn"),
                    Modifier
                        .heightIn(min = 44.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                        .testTag(ADD_SIGN_IN)
                        .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button) {
                            state.closeAdd()
                            state.source.showSignIn()
                        },
                    color = if (enabled) colors.accent else colors.tertiaryLabel,
                    style = type.style(IosTextStyle.Caption, FontWeight.Medium).copy(textDecoration = TextDecoration.Underline).sfLines(),
                )
            }
        }
    }
}

/**
 * `numberRow`: the carrier's truck, its name over the number as printed, and a ring, filled in ochre on the ochre
 * surface for the number chosen; read by its carrier, the number as its value, and selected.
 */
@Composable
private fun NumberRow(addition: ParcelAddition, number: NumberInText, chosen: Boolean, choose: () -> Unit) {
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val carrier = number.match.carrier
    val name = addition.catalog.info(carrier, language).displayName
    val printed = CarrierCatalog.format(number.match.trackingNumber, carrier)
    val ochre = colors.resolve(ExperimentalPalette.ochre)
    val shape = ContinuousRoundedShape(14.dp)
    val enabled = LocalFormEnabled.current
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .testTag(numberRowTag(number.normalized))
            .clickable(interactionSource = null, indication = PressStyle.tactile(0.98f), enabled = enabled, role = Role.Button, onClick = choose)
            .clearAndSetSemantics {
                contentDescription = name
                stateDescription = printed
                selected = chosen
                role = Role.Button
                if (enabled) {
                    onClick {
                        choose()
                        true
                    }
                }
            }
            .background(if (chosen) colors.resolve(ExperimentalPalette.ochreSurface) else colors.paper, shape)
            .border(if (chosen) 1.5.dp else 1.dp, if (chosen) ochre else colors.separator.copy(alpha = colors.separator.alpha * 0.3f), shape)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val identity = carrierIdentity(carrier, language)
        if (identity != null) CarrierTruck(identity) else Spacer(Modifier.width(MARK_WIDTH))
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(1.dp)) {
            Text(name, color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
            Text(printed, color = colors.inkSoft, style = type.style(IosTextStyle.Caption, FontWeight.Medium, FontDesign.Monospaced).sfLines())
        }
        PeekSymbol(
            if (chosen) "checkmark.circle.fill" else "circle", Modifier.padding(start = 8.dp),
            font = type.style(IosTextStyle.Title3), tint = if (chosen) ochre else colors.separator,
        )
    }
}

/**
 * `requiredDetails`: the complete link where the carrier needs one and the text had none, and the delivery postcode
 * with its country beside it where it reads several; an optional postcode offered from those given before, and, for a
 * guest, the one filled in to forget.
 */
@Composable
private fun RequiredDetails(state: DeliveriesState, addition: ParcelAddition, form: AddForm, focus: AddFocus, actions: AddActions) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val catalog = addition.catalog
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        val urlRequirement = addition.trackingUrlRequirement
        if (urlRequirement != null && addition.asksTrackingUrl) {
            RequirementField(localized("add.requirement.trackingUrl"), localized("add.requirement.trackingUrlHelp")) {
                AddTextField(
                    value = form.trackingUrl,
                    onValueChange = { form.trackingUrl = urlRequirement.normalizedValue(it) },
                    placeholder = urlRequirement.placeholder ?: "https://…",
                    label = localized("add.requirement.trackingUrl"),
                    tag = ADD_TRACKING_URL,
                    field = ParcelAddition.Field.TrackingUrl,
                    focus = focus,
                    actions = actions,
                    keyboard = KeyboardOptions(KeyboardCapitalization.None, autoCorrectEnabled = false, keyboardType = KeyboardType.Uri, imeAction = ImeAction.Next),
                    onSubmit = actions::submitTracking,
                    framed = false,
                    suggestions = false,
                    modifier = Modifier.weight(1f),
                )
            }
        }
        val requirement = addition.postcodeRequirement
        if (requirement != null) {
            val carrierName = catalog.info(addition.resolvedCarrier, language).displayName
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                RequirementField(
                    localized("add.requirement.dpdPostcode"),
                    if (requirement.isOptional) {
                        localizer.text("add.requirement.dpdPostcodeOptionalHelp", mapOf("carrier" to carrierName))
                    } else {
                        localized("add.requirement.dpdPostcodeHelp")
                    },
                    optional = requirement.isOptional,
                ) {
                    val country = addition.offeredPostcodeCountry
                    val carrierRequirement = addition.carrierPostcodeRequirement
                    if (country != null && carrierRequirement != null) {
                        PostcodeCountryMenu(
                            addition.resolvedCarrier, carrierRequirement, country, catalog,
                            Modifier.padding(start = 14.dp, top = 14.dp, bottom = 14.dp, end = 10.dp), ADD_COUNTRY,
                            enabled = LocalFormEnabled.current,
                        ) { form.chosenPostcodeCountry = it }
                        // `Divider().padding(.vertical, 12)`: the row's height, less 12 at each end.
                        Box(
                            Modifier
                                .fillMaxHeight()
                                .padding(vertical = 12.dp)
                                .width(1.dp / LocalDensity.current.density)
                                .testTag(ADD_COUNTRY_DIVIDER)
                                .background(PeekTheme.colors.separator),
                        )
                    }
                    AddTextField(
                        value = form.deliveryPostcode,
                        onValueChange = { form.deliveryPostcode = requirement.typedValue(it) },
                        placeholder = addition.postcodeExample(requirement).orEmpty(),
                        label = localized("add.requirement.dpdPostcode"),
                        tag = ADD_POSTCODE,
                        field = ParcelAddition.Field.DeliveryPostcode,
                        focus = focus,
                        actions = actions,
                        keyboard = KeyboardOptions(
                            KeyboardCapitalization.Characters, autoCorrectEnabled = false,
                            keyboardType = postcodeKeyboard(addition.postcodeKeys(requirement)), imeAction = ImeAction.Done,
                        ),
                        framed = false,
                        suggestions = false,
                        postalCode = true,
                        monospacedDigits = true,
                        modifier = Modifier.weight(1f),
                    )
                }
                // An optional postcode is offered, never filled in for the reader.
                if (requirement.isOptional && form.deliveryPostcode.isEmpty()) {
                    val suggestion = form.postcodeFill.offering(addition.previousPostcode(requirement, state.source.givenPostcodes))
                    if (suggestion != null) {
                        TintedButton(
                            AnnotatedString(localizer.text("add.usePostcode", mapOf("postcode" to suggestion))),
                            {
                                form.postcodeFill = form.postcodeFill.fill(suggestion)
                                form.deliveryPostcode = suggestion
                            },
                            ADD_USE_POSTCODE,
                        )
                    }
                }
                // Without an account every suggestion is this phone's own, so it can forget them.
                if (state.source.isGuest && form.postcodeFill.holds(form.deliveryPostcode)) {
                    TintedButton(
                        AnnotatedString(localized("add.forgetPostcode")),
                        {
                            form.postcodeFill.forget(form.deliveryPostcode)?.let { (postcode, fill) ->
                                state.source.forgetPostcode(postcode)
                                form.postcodeFill = fill
                            }
                            form.deliveryPostcode = ""
                        },
                        ADD_FORGET_POSTCODE,
                    )
                }
            }
        }
    }
}

/**
 * `requirementField`: its title in subheadline semibold, "Optional" beside it where it is, the field on the paper with
 * 12-point corners and a hairline, and what it is for under it.
 */
@Composable
private fun RequirementField(title: String, help: String, optional: Boolean = false, field: @Composable androidx.compose.foundation.layout.RowScope.() -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val shape = RoundedCornerShape(12.dp)
    Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(title, Modifier.alignByBaseline(), color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
            if (optional) Text(localized("add.optional"), Modifier.alignByBaseline(), color = colors.secondaryLabel, style = type.caption.sfLines())
        }
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(colors.paper, shape)
                .border(0.7.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.3f), shape),
            verticalAlignment = Alignment.CenterVertically,
            content = field,
        )
        Text(help, color = colors.secondaryLabel, style = type.caption.sfLines())
    }
}

/**
 * `nameField`: "Parcel name" with "Optional" beside it, and the field, which takes 80 characters; Done adds the parcel
 * where it can, else goes back to the number.
 */
@Composable
private fun NameField(form: AddForm, focus: AddFocus, actions: AddActions, addition: ParcelAddition) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(localized("design.parcelTitle"), Modifier.alignByBaseline(), color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines())
            Text(localized("add.optional"), Modifier.alignByBaseline(), color = colors.secondaryLabel, style = type.caption.sfLines())
        }
        AddTextField(
            value = form.label,
            onValueChange = { form.label = ParcelAddition.label(it) },
            placeholder = localized("add.contentsPlaceholder"),
            label = localized("design.parcelTitle"),
            tag = ADD_NAME,
            field = ParcelAddition.Field.Label,
            focus = focus,
            actions = actions,
            keyboard = KeyboardOptions(KeyboardCapitalization.Sentences, imeAction = ImeAction.Done),
            onSubmit = actions::submitName,
        )
    }
}

/**
 * A field of Add as `drawnField()` draws one: its placeholder in the tertiary grey while it is empty, the cursor in the
 * accent, its text grey while off; [framed], 14 in on the paper with 14-point corners, outlined in ochre while it has
 * the focus. TalkBack reads [label] as its name. Its submit key acts ([onSubmit]), as a hardware Return does. It keeps
 * the focus and the keyboard through a window made again. The keyboard offers no suggestions where the field is
 * `.autocorrectionDisabled()`.
 */
@Composable
private fun AddTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    label: String,
    tag: String,
    field: ParcelAddition.Field,
    focus: AddFocus,
    actions: AddActions,
    keyboard: KeyboardOptions,
    modifier: Modifier = Modifier,
    onSubmit: (() -> Unit)? = null,
    minLines: Int = 1,
    maxLines: Int = 1,
    framed: Boolean = true,
    suggestions: Boolean = true,
    postalCode: Boolean = false,
    monospacedDigits: Boolean = false,
) {
    val colors = PeekTheme.colors
    val base = PeekTheme.type.body.sfLines()
    val font = if (monospacedDigits) base.copy(fontFeatureSettings = "tnum") else base
    val enabled = LocalFormEnabled.current
    val focused = focus.focused == field
    val ochre = colors.resolve(ExperimentalPalette.ochre)
    val shape = RoundedCornerShape(14.dp)
    val leading = with(LocalDensity.current) { FIELD_LEADING.roundToPx().let { (it / 2).toDp() to (it - it / 2).toDp() } }
    val submit by rememberUpdatedState(onSubmit)
    // The cursor where the reader left it; at the end of what came otherwise (a paste, a suggestion, the window made again).
    var edited by remember { mutableStateOf(TextFieldValue(value, TextRange(value.length))) }
    val shown = if (edited.text == value) edited else TextFieldValue(value, TextRange(value.length))
    val textField = @Composable {
        BasicTextField(
            shown,
            { edit ->
                edited = edit
                if (edit.text != value) onValueChange(edit.text)
            },
            modifier
                .fillMaxWidth()
                .then(
                    if (framed) {
                        Modifier
                            .background(colors.paper, shape)
                            .border(1.dp, if (focused) ochre else colors.separator.copy(alpha = colors.separator.alpha * 0.3f), shape)
                    } else Modifier,
                )
                .padding(14.dp)
                .testTag(tag)
                .focusRequester(focus.requesters.getValue(field))
                .onFocusChanged { state ->
                    if (state.isFocused) {
                        focus.focused = field
                    } else if (focus.focused == field) {
                        focus.focused = null
                        actions.left(field)
                    }
                }
                .keptFocus()
                .onPreviewKeyEvent { event ->
                    val action = submit ?: return@onPreviewKeyEvent false
                    val enter = event.key == Key.Enter || event.key == Key.NumPadEnter
                    if (!enter || event.isShiftPressed || event.isAltPressed || event.isCtrlPressed || event.isMetaPressed) return@onPreviewKeyEvent false
                    if (event.type == KeyEventType.KeyDown) action()
                    true
                }
                .semantics {
                    contentDescription = label
                    if (postalCode) contentType = ContentType.PostalCode
                },
            textStyle = font.copy(color = if (enabled) colors.label else colors.tertiaryLabel),
            enabled = enabled,
            singleLine = maxLines == 1,
            minLines = minLines,
            maxLines = maxLines,
            cursorBrush = SolidColor(colors.accent),
            keyboardOptions = keyboard,
            keyboardActions = KeyboardActions(onAny = { submit?.invoke() ?: defaultKeyboardAction(keyboard.imeAction) }),
            decorationBox = { text ->
                Box(Modifier.padding(top = leading.first, bottom = leading.second), contentAlignment = Alignment.TopStart) {
                    if (value.isEmpty()) {
                        Text(placeholder, Modifier.clearAndSetSemantics {}, color = colors.tertiaryLabel, style = font, maxLines = maxLines, overflow = TextOverflow.Ellipsis)
                    }
                    text()
                }
            },
        )
    }
    if (suggestions) textField() else NoSuggestions(textField)
}

/**
 * `errorBanner`: the red triangle and why it failed in subheadline medium, on the rose surface with 20-point corners;
 * where the number is in the box already, "Open the existing parcel" underlined, which closes Add and opens it.
 */
@Composable
private fun ErrorBanner(message: String, existing: java.util.UUID?, open: (java.util.UUID) -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(
        Modifier
            .fillMaxWidth()
            .testTag(ADD_ERROR)
            .experimentalSurface(fill = colors.resolve(ExperimentalPalette.roseSurface), cornerRadius = 20.dp, shadow = false)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PeekSymbol("exclamationmark.triangle.fill", font = type.subheadline, tint = colors.red)
            Text(message, Modifier.weight(1f), color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines())
        }
        if (existing != null) {
            Text(
                localized("add.openExisting"),
                Modifier
                    .testTag(ADD_OPEN_EXISTING)
                    .clickable(interactionSource = null, indication = PlainPress, role = Role.Button) { open(existing) },
                color = colors.ink,
                style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).copy(textDecoration = TextDecoration.Underline).sfLines(),
            )
        }
    }
}

/**
 * `primaryAction`: Add across the foot, above the keyboard (`safeAreaInset(edge: .bottom)`), on the app's background: on
 * a cover above the keyboard and the navigation bar, in the form sheet above the part of the keyboard over its foot,
 * as the sheet stays where it is. "Add parcel", or "Track this one" among several, in headline on the accent, 50 tall
 * at least with 14-point corners, at 45 % while it can't add (easing out over 0.2 s); while it saves, a spinner 10
 * before "Adding…", faded in. At the accessibility sizes its words keep to one line, shrinking to 70 %, and grow no
 * further than accessibility2.
 */
@Composable
private fun AddButton(addition: ParcelAddition, saving: Boolean, cover: Boolean, save: () -> Unit, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val reduceMotion = PeekTheme.reduceMotion
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val canSave = addition.canSave
    val opacity by animateFloatAsState(if (canSave || saving) 1f else DISABLED_OPACITY, if (reduceMotion) snap() else Motion.easeOut(BUTTON_MOTION), label = "add button")
    val words = localized(if (addition.several.isEmpty()) "app.addParcel" else "door.several.track")
    val adding = localized("add.adding")
    val density = LocalDensity.current
    // The window's pixels under the sheet's foot, which a keyboard covers first.
    var below by remember { mutableIntStateOf(0) }
    val keyboard = if (cover) Modifier.windowInsetsPadding(WindowInsets.ime.union(WindowInsets.navigationBars).only(WindowInsetsSides.Bottom)) else {
        val lift = with(density) { (WindowInsets.ime.getBottom(this) - below).coerceAtLeast(0).toDp() }
        Modifier
            .onGloballyPositioned { foot ->
                val root = foot.findRootCoordinates()
                below = (root.size.height - root.localPositionOf(foot, Offset(0f, foot.size.height.toFloat())).y).roundToInt().coerceAtLeast(0)
            }
            .padding(bottom = lift)
    }
    Box(
        modifier
            .fillMaxWidth()
            .background(colors.background)
            .then(keyboard),
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            Modifier
                .widthIn(max = CONTENT_WIDTH)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .testTag(ADD_SAVE)
                .graphicsLayer { alpha = opacity }
                .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = canSave && !saving, role = Role.Button, onClick = save)
                .semantics { contentDescription = if (saving) adding else words }
                .heightIn(min = 50.dp)
                .background(colors.accent, RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            CappedDynamicTypeSize(DynamicTypeSize.Accessibility2) {
                val style = PeekTheme.type.headline.sfLines()
                Crossfade(saving, Modifier.clearAndSetSemantics {}, if (reduceMotion) snap() else Motion.easeOut(BUTTON_MOTION), label = "add label") { busy ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        if (busy) ActivityIndicator(size = REGULAR_SPINNER, tint = colors.onAccent)
                        ShrinkingText(if (busy) adding else words, style, colors.onAccent, oneLine = large)
                    }
                }
            }
        }
    }
}

/** Words on one line where [oneLine], shrinking to 70 % rather than wrap (`minimumScaleFactor(0.7)`). */
@Composable
private fun ShrinkingText(text: String, style: TextStyle, color: Color, oneLine: Boolean) {
    if (!oneLine) {
        Text(text, color = color, style = style, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        return
    }
    androidx.compose.foundation.text.BasicText(
        text,
        style = style.copy(color = color),
        maxLines = 1,
        autoSize = androidx.compose.foundation.text.TextAutoSize.StepBased(minFontSize = style.fontSize * MINIMUM_SCALE, maxFontSize = style.fontSize),
    )
}

/**
 * The carrier picker over Add (`.sheet(isPresented: $showingCarrierPicker)`): automatic detection first, with what the
 * check found, then the carriers that know the number, those it fits and those used before, tagged as the check found
 * them; Cancel at the bar's leading end. A form over a form side by side.
 */
@Composable
private fun AddPicker(state: DeliveriesState, addition: ParcelAddition, form: AddForm, actions: AddActions) {
    val localizer = LocalLocalizer.current
    val parcels = state.parcels
    val close = { form.showingPicker = false }
    Sheet(
        visible = form.showingPicker,
        onDismiss = close,
        paneTitle = localized("add.carrier"),
        detents = listOf(SheetDetent.Large),
    ) {
        // The sections it opened with stay as they were (CarrierPicker keeps them); the tags and the automatic row
        // follow the check.
        val sections = remember(addition.normalizedNumber, addition.recognitionStatus, parcels, localizer) {
            addition.pickerSections(parcels).map { CarrierPickerSection(it.id, localizer.text(it.titleKey), it.carriers) }
        }
        val tags = addition.pickerTags.mapValues { (_, key) -> CarrierPickerTag(localizer.text(key), found = true) }
        val automatic = CarrierPickerAutomatic(addition.automaticDescription(localizer), recommended = !addition.choosingCarrier, busy = addition.checking)
        Box(Modifier.fillMaxSize().testTag(ADD_PICKER)) {
            LocalSheetGround.current?.invoke(Modifier.fillMaxSize())
            CarrierPicker(
                selection = form.carrierOverride,
                sections = sections,
                onSelect = actions::selectCarrier,
                onLeave = close,
                automatic = automatic,
                tags = tags,
                leading = { PickerCancel(close) },
            )
        }
    }
}

/** Cancel at the picker's leading end, on its glass capsule, as the bar's `.cancellationAction` stands. */
@Composable
private fun PickerCancel(cancel: () -> Unit) {
    val colors = PeekTheme.colors
    ProvideDynamicTypeSize(DynamicTypeSize.Large) {
        GlassSurface(CapsuleShape, Modifier.heightIn(min = 44.dp), backdrop = null, behind = colors.groupedBackground) {
            Box(
                Modifier
                    .heightIn(min = 44.dp)
                    .testTag(ADD_PICKER_CANCEL)
                    .clickable(interactionSource = null, indication = PressStyle.tactile(0.94f), role = Role.Button, onClick = cancel)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(localized("common.cancel"), color = colors.label, style = PeekTheme.type.body, maxLines = 1)
            }
        }
    }
}

/** Escape, from a hardware keyboard, while [enabled]: what nothing in the window used. */
@Composable
private fun AddEscape(enabled: Boolean, close: () -> Unit) {
    val act by rememberUpdatedState(close)
    val on by rememberUpdatedState(enabled)
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewCompat.OnUnhandledKeyEventListenerCompat { _, event ->
            if (!on || event.keyCode != KeyEvent.KEYCODE_ESCAPE || !event.hasNoModifiers()) return@OnUnhandledKeyEventListenerCompat false
            if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0) act()
            true
        }
        ViewCompat.addOnUnhandledKeyEventListener(view, listener)
        onDispose { ViewCompat.removeOnUnhandledKeyEventListener(view, listener) }
    }
}

/**
 * Takes text or a link dragged from another app (`.dropDestination(for: DroppedText.self)`), on the list and on the +:
 * Add opens with it, as a paste would. What holds no number is turned away.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Modifier.parcelDrop(state: DeliveriesState): Modifier {
    val activity = LocalActivity.current
    val target = remember(state, activity) {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val drag = event.toAndroidDragEvent()
                val permissions = activity?.requestDragAndDropPermissions(drag)
                try {
                    return state.addDropped(droppedTexts(drag.clipData ?: return false))
                } finally {
                    permissions?.release()
                }
            }
        }
    }
    return dragAndDropTarget(
        shouldStartDragAndDrop = { event -> event.mimeTypes().any { it.startsWith("text/") || it == ClipDescription.MIMETYPE_TEXT_URILIST } },
        target = target,
    )
}

/** What a drop holds (`DroppedText`): each item's text, or a link as its whole address whatever its scheme (`url.absoluteString`). */
internal fun droppedTexts(clip: ClipData): List<String> = (0 until clip.itemCount).mapNotNull { index ->
    val item = clip.getItemAt(index)
    item.text?.toString() ?: item.uri?.toString()
}

internal fun numberRowTag(normalized: String) = "$ADD_NUMBER.$normalized"

internal const val ADD_PARCEL = "addParcel"
internal const val ADD_TITLE = "addParcel.title"
internal const val ADD_CLOSE = "addParcel.close"
internal const val ADD_TRACKING = "addParcel.tracking"
internal const val ADD_NAME = "addParcel.name"
internal const val ADD_PASTE = "addParcel.paste"
internal const val ADD_CARRIER = "addParcel.carrier"
internal const val ADD_CARRIER_MARK = "addParcel.carrier.mark"
internal const val ADD_CHECKING = "addParcel.carrier.checking"
internal const val ADD_LINE_NAME = "addParcel.carrier.name"
internal const val ADD_LINE_DETAIL = "addParcel.carrier.detail"
internal const val ADD_SAVE = "addParcel.save"
internal const val ADD_NUMBER = "addParcel.number"
internal const val ADD_SEVERAL = "addParcel.several"
internal const val ADD_SIGN_IN = "addParcel.signIn"
internal const val ADD_FOUND = "addParcel.found"
internal const val ADD_FEEDBACK = "addParcel.feedback"
internal const val ADD_DETAILS = "addParcel.details"
internal const val ADD_HINT = "addParcel.hint"
internal const val ADD_NOT_FOUND = "addParcel.notFound"
internal const val ADD_PICKUP_CODE = "addParcel.pickupCode"
internal const val ADD_PLACEHOLDER = "addParcel.placeholder"
internal const val ADD_ORDER = "addParcel.order"
internal const val ADD_TYPO = "addParcel.typo"
internal const val ADD_SUGGESTION = "addParcel.suggestion"
internal const val ADD_AS_TYPED = "addParcel.asTyped"
internal const val ADD_AMAZON_ORDERS = "addParcel.amazonOrders"
internal const val ADD_AMAZON_UNAVAILABLE = "addParcel.amazonUnavailable"
internal const val ADD_AMAZON_RETRY = "addParcel.amazonRetry"
internal const val ADD_USE_CARRIER = "addParcel.useCarrier"
internal const val ADD_TRACKING_URL = "addParcel.trackingUrl"
internal const val ADD_POSTCODE = "addParcel.postcode"
internal const val ADD_COUNTRY = "addParcel.country"
internal const val ADD_COUNTRY_DIVIDER = "addParcel.country.divider"
internal const val ADD_USE_POSTCODE = "addParcel.usePostcode"
internal const val ADD_FORGET_POSTCODE = "addParcel.forgetPostcode"
internal const val ADD_ERROR = "addParcel.error"
internal const val ADD_OPEN_EXISTING = "addParcel.openExisting"
internal const val ADD_PICKER = "addParcel.picker"
internal const val ADD_PICKER_CANCEL = "addParcel.picker.cancel"

/** How long after Add has come up the keyboard opens (`Task.sleep(for: .milliseconds(300))`). */
private const val FOCUS_DELAY = 300L

/** How long an Amazon number stands before Amazon is asked (`Task.sleep(for: .milliseconds(350))`). */
private const val LOOKUP_DELAY = 350L

/** `.snappy(duration: 0.28)` on whether a number was found. */
private const val FEEDBACK_MOTION = 0.28

/** `.snappy(duration: 0.3)` on the requirements, the error and the check. */
private const val STATE_MOTION = 0.3

/** `withAnimation(.snappy(duration: 0.34))`: a paste, a draft, a suggestion. */
private const val FILL_MOTION = 0.34

/** `withAnimation(.snappy(duration: 0.2))`: a number chosen. */
private const val CHOOSE_MOTION = 0.2

/** `.easeOut(duration: 0.2)` on whether Add can add. */
private const val BUTTON_MOTION = 0.2
private const val DISABLED_OPACITY = 0.45f
private const val MINIMUM_SCALE = 0.7f
private val CONTENT_WIDTH = 560.dp
/** The number's label and its field, two lines tall, which the title gives way to in a window too low for both. */
private val FIELD_ROOM = 96.dp
/**
 * What UIKit's field adds to its lines, half above and half below in whole pixels: the name's line of body is 22 tall
 * there and the number's two are 42.33, where SF's lines make 20.29 and 40.57.
 */
private val FIELD_LEADING = 1.72.dp
private val MARK_WIDTH = 27.dp
private val ROW_RISE = 6.dp

/** `ProgressView().controlSize(.mini)`. */
private val MINI_SPINNER: Dp = 12.dp
