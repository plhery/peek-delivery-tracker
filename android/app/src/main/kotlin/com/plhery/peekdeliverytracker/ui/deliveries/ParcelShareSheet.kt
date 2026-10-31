package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.ParcelNumberHint
import com.plhery.peekdeliverytracker.core.ParcelShareModel
import com.plhery.peekdeliverytracker.core.ParcelShareWords
import com.plhery.peekdeliverytracker.core.PipGeometry
import com.plhery.peekdeliverytracker.core.PipLabel
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.core.deliveringCarrier
import com.plhery.peekdeliverytracker.core.displayedCarrier
import com.plhery.peekdeliverytracker.core.giftDetail
import com.plhery.peekdeliverytracker.core.hasCarrierUpdate
import com.plhery.peekdeliverytracker.core.masked
import com.plhery.peekdeliverytracker.core.sharedParcelDetail
import com.plhery.peekdeliverytracker.platform.ShareTargetChosen
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.CarrierFleetMark
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.SmallPip
import com.plhery.peekdeliverytracker.ui.draw.Toggle
import com.plhery.peekdeliverytracker.ui.sheet.KeyboardScrollColumn
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.sheetBottomInset
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.carrierIdentity
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.util.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

// ParcelShareSheet.swift: sharing one of the reader's parcels, from its page's pass or its card's menu. What the link
// shows, as the other person will see it, the switches that change it, the link, and the way to stop.

/**
 * A parcel's share sheet open on the parcel with `parcelId`, from the page with `pageId` (`showingShare`) or from a
 * card's menu without one (`prompts.sharing`), each opening another ([id]).
 */
@Immutable
internal data class ShareRequest(val id: UUID, val parcelId: UUID, val pageId: UUID?) {
    /** As a window keeps it: the ids, the page's or "-", a space between. */
    val saved: String get() = listOf(id, parcelId, pageId ?: "-").joinToString(" ")

    companion object {
        fun from(saved: String): ShareRequest {
            val (id, parcelId, pageId) = saved.split(" ")
            return ShareRequest(UUID.fromString(id), UUID.fromString(parcelId), pageId.takeIf { it != "-" }?.let(UUID::fromString))
        }
    }
}

/**
 * The share sheets open, by their request's id ([ShareRequest]), each with its model and what the sheet keeps with it
 * ([ParcelShareSheetWork]). The source holds them for the launch, as [PageWork] holds the pages' changes: a sheet drawn
 * again as the window is made again (a rotation, a fold) finds the link, the words typed and a switch on its way to the
 * service as they were, as the iPhone's `@StateObject` lasts through a rotation. A sheet that closes is forgotten
 * ([forget]), and opened again starts afresh.
 */
class ParcelShares {
    private val sheets = HashMap<UUID, ParcelShareSheetWork>()

    internal fun sheet(id: UUID): ParcelShareSheetWork? = sheets[id]

    /** The sheet asked for as `id`, which starts reading its link (`.task { await model.load() }`). */
    internal fun open(id: UUID, sheet: ParcelShareSheetWork) {
        sheets.remove(id)?.close()
        sheets[id] = sheet
        sheet.start()
    }

    /** The sheet asked for as `id` has closed: it stops reading its link; a switch on its way still gets there. */
    fun forget(id: UUID) {
        sheets.remove(id)?.close()
    }
}

/** The address made ready to hand out through the system's share sheet, or to copy ([copy]), each time another. */
internal class ShareNews(val address: String, val copy: Boolean)

/**
 * One share sheet's [ParcelShareModel], working in the app's `scope`, and what the sheet keeps beside it: the words as
 * the fields show them, the large detent, "Copied" for its two seconds, the address made ready, until the window
 * shown by then has handed it out or copied it ([heard]), and the system's share sheet on its way ([choosing]). `demo`
 * says the demo's links are made up on this phone. A card's sheet keeps the parcel as the menu found it ([parcel]),
 * through the window made again, even once it has left the list.
 */
@Stable
internal class ParcelShareSheetWork(
    val model: ParcelShareModel,
    val demo: Boolean,
    private val scope: CoroutineScope,
    val parcel: Parcel? = null,
) {
    /** `model.words`, as typed: set with each keystroke, so the fields never show a frame behind it. */
    var words: ParcelShareWords by mutableStateOf(model.words)
        private set

    /** The sheet stands at its large detent: dragged there, or opened fully to write a gift's note (`detent`). */
    var large: Boolean by mutableStateOf(false)

    /** "detail.copied" in place of "detail.copy" for two seconds after each copy (`copied`). */
    var copied: Boolean by mutableStateOf(false)
        private set

    var news: ShareNews? by mutableStateOf(null)
        private set

    /**
     * From a Share until the window is back from the system's share sheet, or it couldn't be shown ([chosen]): one share
     * sheet at a time, as the iPhone presents one, so a second tap meanwhile goes nowhere.
     */
    var choosing: Boolean by mutableStateOf(false)
        private set

    /** The window has started the system's share sheet, and waits to come back from it ([choosing]). */
    var chooserOpen: Boolean by mutableStateOf(false)
        private set

    /** The link's reading, and the words' watch, which the sheet's closing ends. */
    private val jobs = mutableListOf<Job>()

    fun start() {
        jobs += scope.launch { model.wordsFlow.collect { words = it } }
        jobs += scope.launch { model.load() }
    }

    fun close() {
        jobs.forEach { it.cancel() }
    }

    /** A switch flipped (`binding(_:)`): shown at once, saved where there is a link, put back if the service refuses. */
    fun set(field: ParcelShareModel.Field, on: Boolean) {
        scope.launch { model.set(field, on) }
    }

    /** The name switch, the note or who it is from changed (`$model.words`), each kept within its limit as typed. */
    fun type(words: ParcelShareWords) {
        model.words = words
        this.words = model.words
    }

    /**
     * The primary button: the address, with the link made now where there is none, for the system's share sheet; nothing
     * while one is on its way ([choosing]).
     */
    fun handOut() {
        if (choosing) return
        choosing = true
        ready(copy = false)
    }

    /** Copy (`copy()`): the address, with the link made now where there is none, for the clipboard. */
    fun copy() = ready(copy = true)

    private fun ready(copy: Boolean) {
        scope.launch {
            val address = model.addressToHandOut()
            if (address == null) {
                if (!copy) chosen()
                return@launch
            }
            news = ShareNews(address, copy)
        }
    }

    /** The window has started the system's share sheet. */
    fun chooserOpened() {
        chooserOpen = true
    }

    /** The window is back from the system's share sheet, or it couldn't be shown: Share hands the address out again. */
    fun chosen() {
        choosing = false
        chooserOpen = false
    }

    /** The window has handed out or copied `news`. */
    fun heard(news: ShareNews) {
        if (this.news === news) this.news = null
    }

    /** The address was copied: "Copied" for two seconds, each copy its own, as the iPhone's task does. */
    fun showCopied() {
        copied = true
        scope.launch {
            delay(COPIED_DURATION)
            copied = false
        }
    }

    /** "share.stop": the link goes blank for good, and the words kept for it with it. */
    fun stop() {
        scope.launch { model.stop() }
    }

    /** "share.again": a new link, showing what the switches say. */
    fun shareAgain() {
        scope.launch { model.shareAgain() }
    }
}

/** What the share sheet shows, kept as it goes down. */
private data class SharingShown(val request: ShareRequest, val work: ParcelShareSheetWork)

/**
 * `ParcelShareSheet` over the whole window, where the tab shell draws it: the app's [Sheet] as `fittedSheet`, as tall
 * as what it shows and then the large detent on a phone, which it opens to while a gift's words are typed; a form as
 * tall as what it shows on a tablet, scrolling where that is more than the window. The page's goes with its page and
 * draws nothing once its parcel has gone, as the iPhone's `if let parcel` does; a card's shows the parcel as the menu
 * found it ([ParcelShareSheetWork.parcel]). While sharing, copying or stopping is under way nothing sends it away
 * (`interactiveDismissDisabled(model.working)`). The address made ready goes to the clipboard, with the success tap,
 * "parcel-link-share" counted a success and TalkBack told ([DeliveriesState.copy]); or to the system's share sheet
 * (`LinkActivitySheet`), counted a success once the reader chooses an app for it, even after the sheet has closed,
 * and nothing where they back out; Android tells the app chosen, not whether it went on to send, as the iPhone's
 * completion does ([ShareTargetChosen]). A share sheet that couldn't be shown counts an error and says so. Until the
 * window has gone to the system's share sheet and come back, or [CHOOSER_GUARD] without it going, Share does nothing.
 */
@Composable
internal fun ParcelShareHost(state: DeliveriesState) {
    val request = state.sharing
    val unshown = request?.pageId != null && request.pageId != state.navigator.navigation.shown
    val work = request?.let { state.source.shares.sheet(it.id) }
    val current = request != null && work != null && !unshown
    // Whether the sheet has come up, so that a sheet closed before it did leaves nothing to wait for.
    var up by remember { mutableStateOf(false) }
    if (current) up = true
    LaunchedEffect(request?.id, current) {
        if (request == null || current) return@LaunchedEffect
        state.closeShare()
        if (!up) state.shareClosed()
    }
    var kept by remember { mutableStateOf<SharingShown?>(null) }
    if (request != null && work != null && !unshown) kept = SharingShown(request, work)
    val shown = kept
    val parcel = shown?.let { state.sharedParcel(it.request, it.work, state.parcels) }
    val working = shown?.work?.model?.workingFlow?.collectAsState()?.value == true
    val localizer = LocalLocalizer.current
    val focusManager = LocalFocusManager.current
    val news = work?.takeIf { current }?.news
    LaunchedEffect(news) {
        val ready = news ?: return@LaunchedEffect
        val work = work ?: return@LaunchedEffect
        if (ready.copy) {
            // The button says so for the eyes, as the system's own confirmation may; TalkBack hears of it either way.
            state.copy(ready.address)
            state.announce(localizer.text("link.copied"))
            state.source.action("parcel-link-share", DeliveryAnalytics.Outcome.Success)
            work.showCopied()
        } else {
            focusManager.clearFocus()
            // The source's, not the window's: the app chosen may come once the window has gone.
            val source = state.source
            if (state.handOut(ready.address) { source.action("parcel-link-share", DeliveryAnalytics.Outcome.Success) }) {
                work.chooserOpened()
            } else {
                state.source.action("parcel-link-share", DeliveryAnalytics.Outcome.Error)
                work.model.handOutFailed()
                work.chosen()
            }
        }
        work.heard(ready)
    }
    // Back from the system's share sheet: the window paused for it and resumed, or never paused within the guard.
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val chooserOpen = work?.takeIf { current }?.chooserOpen == true
    LaunchedEffect(work, chooserOpen, lifecycle) {
        val opened = work?.takeIf { chooserOpen } ?: return@LaunchedEffect
        val left = withTimeoutOrNull(CHOOSER_GUARD) { lifecycle.currentStateFlow.first { !it.isAtLeast(Lifecycle.State.RESUMED) } }
        if (left != null) lifecycle.currentStateFlow.first { it.isAtLeast(Lifecycle.State.RESUMED) }
        opened.chosen()
    }
    Sheet(
        visible = current,
        onDismiss = { state.closeShare() },
        paneTitle = localized("share.title"),
        detents = listOf(SheetDetent.Fitted, SheetDetent.Large),
        onClosed = {
            up = false
            kept = null
            state.shareClosed()
        },
        detent = if (shown?.work?.large == true) SheetDetent.Large else SheetDetent.Fitted,
        onDetentChange = { detent -> shown?.work?.large = detent == SheetDetent.Large },
        dismissible = !working,
    ) {
        if (shown != null && parcel != null) ParcelShareSheet(shown.work, parcel, close = { state.closeShare() })
    }
}

/**
 * `ParcelShareSheet`'s body on Brand's background, 22 in from the sides, 26 under the top and 18 over the foot and the
 * navigation bar under a sheet from the window's bottom, 560 wide at most and 14 between its parts: the heading with
 * the close button, then what sharing shows, or once sharing was stopped from here, that the link shows nothing now
 * and the way to share again. It scrolls clear of the keyboard ([KeyboardScrollColumn]).
 */
@Composable
private fun ParcelShareSheet(work: ParcelShareSheetWork, parcel: Parcel, close: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val model = work.model
    val stopped by model.stoppedFlow.collectAsState()
    val working by model.workingFlow.collectAsState()
    val failure by model.failureKeyFlow.collectAsState()
    val saving by model.savingFlow.collectAsState()
    val bottom = sheetBottomInset()
    // Clear of the keyboard, the field with the focus in view above it, and a drag down takes it down.
    KeyboardScrollColumn(Modifier.fillMaxSize().background(colors.background), bottom = bottom, dismissesKeyboard = true) {
        Column(
            Modifier
                .testTag(PARCEL_SHARE)
                .fillMaxWidth()
                .wrapContentWidth()
                .widthIn(max = SHARE_WIDTH)
                .fillMaxWidth()
                .padding(start = SHARE_SIDE, top = SHARE_TOP, end = SHARE_SIDE, bottom = SHARE_BOTTOM + bottom),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            ShareHeader(model.name, working, close)
            if (stopped) {
                // `stoppedState`
                ShareLine(localizer.text("share.stopped.account"), "info.circle", Modifier.testTag(PARCEL_SHARE_STOPPED))
                if (work.demo) ShareLine(localizer.text("alerts.demo"), "info.circle", Modifier.testTag(PARCEL_SHARE_DEMO))
                failure?.let { ShareWarning(localizer.text(it)) }
                PrimaryButton(localizer.text("share.again"), null, PARCEL_SHARE_AGAIN, enabled = !working && saving.isEmpty(), working) {
                    work.shareAgain()
                }
            } else {
                Sharing(work, parcel, working, failure, saving)
            }
        }
    }
}

/**
 * `sharing`, 12 between the preview, the switches, a gift's words and the link: what the link shows, as a card
 * ([SharePreviewCard]); its three switches in one card, the parcel's name only where it has one; a gift's note and who
 * it is from while the gift is on; the link, or what stands in its place. Then that the demo's links are made up, why
 * the last step failed, and the primary button, which hands the link out (off while the link is read), over "Stop
 * sharing" once there is a link, 2 apart.
 */
@Composable
private fun Sharing(work: ParcelShareSheetWork, parcel: Parcel, working: Boolean, failure: String?, saving: Set<ParcelShareModel.Field>) {
    val localizer = LocalLocalizer.current
    val model = work.model
    val link by model.linkFlow.collectAsState()
    val loading by model.loadingFlow.collectAsState()
    val shown by model.shownFlow.collectAsState()
    val words = work.words
    val idle = !working && saving.isEmpty()
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SharePreviewCard(parcel, name = model.carried.name, showNumber = shown.showNumber, gift = shown.gift)
        ShareOptions(work, shown, words, loading)
        if (shown.gift) GiftWords(work, words)
        LinkRow(loading, model.address, work.copied, enabled = !loading && idle, copy = work::copy)
    }
    if (work.demo) ShareLine(localizer.text("alerts.demo"), "info.circle", Modifier.testTag(PARCEL_SHARE_DEMO))
    failure?.let { ShareWarning(localizer.text(it)) }
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        PrimaryButton(localizer.text("share.action"), "square.and.arrow.up", PARCEL_SHARE_ACTION, enabled = !loading && idle, working, work::handOut)
        if (link != null) StopButton(enabled = !working, work::stop)
    }
}

/**
 * The heading, "share.titleNamed" with the parcel's name or "share.title", title2 semibold across what the close
 * button leaves it, 16 from it: an ✕ of 13 semibold in a circle of the ink at 7 %, 36 across in 44, a plain button
 * named "common.close", off while sharing, copying or stopping is under way.
 */
@Composable
private fun ShareHeader(name: String?, working: Boolean, close: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val title = name?.let { localizer.text("share.titleNamed", mapOf("name" to it)) } ?: localizer.text("share.title")
    val closeLabel = localizer.text("common.close")
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            title,
            Modifier.weight(1f).testTag(PARCEL_SHARE_TITLE).semantics { heading() },
            color = colors.label,
            style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(),
        )
        Box(
            Modifier
                .size(44.dp)
                .testTag(PARCEL_SHARE_CLOSE)
                .clickable(interactionSource = null, indication = PlainPress, enabled = !working, role = Role.Button, onClick = close)
                .semantics { contentDescription = closeLabel },
            contentAlignment = Alignment.Center,
        ) {
            Box(Modifier.size(36.dp).background(colors.ink.copy(alpha = colors.ink.alpha * 0.07f), CircleShape), contentAlignment = Alignment.Center) {
                PeekSymbol("xmark", font = type.fixed(13f, FontWeight.SemiBold), tint = colors.label)
            }
        }
    }
}

/**
 * `options`: showing the number, the parcel's name (with the name itself) where it has one, and the gift, in one card
 * on the paper with 14-point continuous corners, a hairline of the separator at half strength between them. The
 * switches wait while the link is read. A parcel without a name has no name to show.
 */
@Composable
private fun ShareOptions(work: ParcelShareSheetWork, shown: ParcelShareModel.Settings, words: ParcelShareWords, loading: Boolean) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val name = work.model.name
    Column(Modifier.fillMaxWidth().background(colors.paper, ContinuousRoundedShape(14.dp))) {
        ShareSwitch(PARCEL_SHARE_NUMBER, "receipt", localizer.text("share.number.title"), null, shown.showNumber, enabled = !loading) {
            work.set(ParcelShareModel.Field.ShowNumber, it)
        }
        if (name != null) {
            Hairline()
            ShareSwitch(PARCEL_SHARE_NAME, "pencil", localizer.text("share.name.title"), name, words.name, enabled = !loading) {
                work.type(work.words.copy(name = it))
            }
        }
        Hairline()
        ShareSwitch(PARCEL_SHARE_GIFT, "gift", localizer.text("share.gift.title"), null, shown.gift, enabled = !loading) {
            work.set(ParcelShareModel.Field.Gift, it)
        }
    }
}

@Composable
private fun Hairline() {
    val separator = PeekTheme.colors.separator
    Box(Modifier.fillMaxWidth().height(1.dp).background(separator.copy(alpha = separator.alpha * 0.5f)))
}

/**
 * `switchRow`: the symbol, 16 in the secondary grey 22 wide and 12 before the title in subheadline semibold, and what
 * it names (the parcel's name) in caption secondary on one line at the trailing end, at least 10 from the title, which
 * keeps its room first; at the accessibility sizes the title alone on its line, the value under it on two at most. The
 * switch at the trailing end, in the delivered green while on; 14 and 9 in, 50 tall at least. A tap on the switch
 * flips it (`flip`); TalkBack reads the row as one switch, its words and its state, as iOS's `Toggle` with its label.
 */
@Composable
private fun ShareSwitch(tag: String, symbol: String, title: String, value: String?, on: Boolean, enabled: Boolean, flip: (Boolean) -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val titleStyle = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines()
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(tag)
            .semantics(mergeDescendants = true) {
                role = Role.Switch
                toggleableState = ToggleableState(on)
                onClick {
                    if (enabled) flip(!on)
                    enabled
                }
                if (!enabled) disabled()
            }
            .heightIn(min = 50.dp)
            .padding(horizontal = 14.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (PeekTheme.dynamicTypeSize.isAccessibilitySize) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(title, color = colors.label, style = titleStyle)
                if (value != null) {
                    Text(value, color = colors.secondaryLabel, style = type.caption.sfLines(), maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
        } else {
            Box(Modifier.width(22.dp), contentAlignment = Alignment.Center) {
                PeekSymbol(symbol, font = type.fixed(16f), tint = colors.secondaryLabel)
            }
            Spacer(Modifier.width(12.dp))
            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Text(title, color = colors.label, style = titleStyle)
                if (value != null) {
                    Spacer(Modifier.weight(1f))
                    Text(
                        value,
                        Modifier.padding(start = 10.dp),
                        color = colors.secondaryLabel,
                        style = type.caption.sfLines(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
        Toggle(
            on, flip, Modifier.padding(start = 8.dp).clearAndSetSemantics {}, enabled = enabled,
            tint = colors.resolve(ExperimentalPalette.delivered),
        )
    }
}

/**
 * `giftWords`: a gift's note, two to five lines, and who it is from, with the name autofill and Done, which puts the
 * keyboard away; each under its label in caption secondary, 10 apart, then "share.gift.private" in caption secondary;
 * 14 in on the paper with 14-point continuous corners. Each keeps within what a link carries as it is typed (280 and 60).
 * Either field taking the focus opens the sheet fully (`onChange(of: writing)`).
 */
@Composable
private fun GiftWords(work: ParcelShareSheetWork, words: ParcelShareWords) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val writing = { work.large = true }
    Column(
        Modifier.fillMaxWidth().background(colors.paper, ContinuousRoundedShape(14.dp)).padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        WordsField(PARCEL_SHARE_NOTE, localizer.text("share.gift.note"), words.note, name = false, writing) {
            work.type(work.words.copy(note = it))
        }
        WordsField(PARCEL_SHARE_FROM, localizer.text("share.gift.from"), words.from, name = true, writing) {
            work.type(work.words.copy(from = it))
        }
        Text(localizer.text("share.gift.private"), color = colors.secondaryLabel, style = PeekTheme.type.caption.sfLines())
    }
}

/**
 * `wordsField`: its label in caption secondary, which TalkBack leaves for the field it names, 5 over the field in
 * subheadline, 12 and 10 in on Brand's background with 10-point continuous corners, 44 tall at least. The note grows
 * from two lines to five; who it is from is one line, the name autofill's, whose Done puts the keyboard away.
 */
@Composable
private fun WordsField(tag: String, label: String, value: String, name: Boolean, writing: () -> Unit, change: (String) -> Unit) {
    val colors = PeekTheme.colors
    val style = PeekTheme.type.subheadline.sfLines()
    val focusManager = LocalFocusManager.current
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(label, Modifier.clearAndSetSemantics {}, color = colors.secondaryLabel, style = PeekTheme.type.caption.sfLines())
        BasicTextField(
            value,
            change,
            Modifier
                .fillMaxWidth()
                .testTag(tag)
                .onFocusChanged { if (it.isFocused) writing() }
                .semantics {
                    contentDescription = label
                    if (name) contentType = ContentType.PersonFullName
                },
            textStyle = style.copy(color = colors.label),
            singleLine = name,
            minLines = if (name) 1 else 2,
            maxLines = if (name) 1 else 5,
            cursorBrush = SolidColor(colors.ink),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = if (name) ImeAction.Done else ImeAction.Default,
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            decorationBox = { text ->
                Box(
                    Modifier
                        .background(colors.background, ContinuousRoundedShape(10.dp))
                        .heightIn(min = 44.dp)
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    contentAlignment = Alignment.CenterStart,
                ) { text() }
            },
        )
    }
}

/**
 * `linkRow`: a small spinner while the link is read, the address without its scheme in monospaced footnote medium on
 * one line (three at the accessibility sizes), which TalkBack reads as "share.address" with the whole address, or
 * "share.pending" in footnote secondary while there is no link; then Copy, "detail.copied" for two seconds after it
 * copied, off while the link is read or something is on its way. Side by side 8 apart, or at the accessibility sizes
 * the button under the address, which then has the whole width. 16 in at the leading side, 6 elsewhere and 6 more over
 * a stack, 56 tall at least, on the paper with 16-point continuous corners and a stroke of the separator at 60 %.
 */
@Composable
private fun LinkRow(loading: Boolean, address: String?, copied: Boolean, enabled: Boolean, copy: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val stacked = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val shape = ContinuousRoundedShape(16.dp)
    val row = Modifier
        .fillMaxWidth()
        .testTag(PARCEL_SHARE_LINK)
        .heightIn(min = 56.dp)
        .background(colors.paper, shape)
        .border(1.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.6f), shape)
        .padding(start = 16.dp, end = 6.dp, top = if (stacked) 12.dp else 6.dp, bottom = 6.dp)
    val label = localizer.text("share.address")
    val shown = @Composable { modifier: Modifier ->
        Box(modifier, contentAlignment = Alignment.CenterStart) {
            when {
                loading -> ActivityIndicator(size = SMALL_SPINNER)
                address != null -> Text(
                    address.replace(SCHEME, ""),
                    // TalkBack reads the label for the words shown, then the whole address.
                    Modifier.testTag(PARCEL_SHARE_ADDRESS).semantics {
                        contentDescription = label
                        stateDescription = address
                    },
                    color = colors.label,
                    style = type.style(IosTextStyle.Footnote, FontWeight.Medium, FontDesign.Monospaced).sfLines(),
                    maxLines = if (stacked) 3 else 1,
                    overflow = TextOverflow.Ellipsis,
                )
                else -> Text(
                    localizer.text("share.pending"),
                    Modifier.testTag(PARCEL_SHARE_PENDING),
                    color = colors.secondaryLabel,
                    style = type.footnote.sfLines(),
                )
            }
        }
    }
    if (stacked) {
        Column(row, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            shown(Modifier.fillMaxWidth())
            CopyButton(copied, enabled, copy)
        }
    } else {
        Row(row, horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            shown(Modifier.weight(1f))
            CopyButton(copied, enabled, copy)
        }
    }
}

/**
 * Copy: doc.on.doc and "detail.copy", or a checkmark and "detail.copied", in footnote semibold, `Label`'s 6 apart
 * (estimated), 14 in on the ink at 7 % with 14-point continuous corners, 44 tall at least, giving under the finger.
 */
@Composable
private fun CopyButton(copied: Boolean, enabled: Boolean, copy: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val style = PeekTheme.type.style(IosTextStyle.Footnote, FontWeight.SemiBold)
    Row(
        Modifier
            .testTag(PARCEL_SHARE_COPY)
            .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = enabled, role = Role.Button, onClick = copy)
            .background(colors.ink.copy(alpha = colors.ink.alpha * 0.07f), ContinuousRoundedShape(14.dp))
            .heightIn(min = 44.dp)
            .padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(LABEL_SPACING),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeekSymbol(if (copied) "checkmark" else "doc.on.doc", font = style, tint = colors.label)
        Text(localizer.text(if (copied) "detail.copied" else "detail.copy"), color = colors.label, style = style.sfLines())
    }
}

/**
 * `primaryButton`: its symbol in body semibold, or a spinner while sharing, copying or stopping is under way, 9
 * before its words in headline, on the accent across the sheet, 20 and 13 in, 50 tall at least with 16-point continuous
 * corners, giving under the finger; off while something is on its way. TalkBack hears it in progress meanwhile.
 */
@Composable
private fun PrimaryButton(title: String, symbol: String?, tag: String, enabled: Boolean, working: Boolean, action: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(tag)
            .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = enabled, role = Role.Button, onClick = action)
            .semantics { if (working) progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate }
            .background(colors.accent, ContinuousRoundedShape(16.dp))
            .heightIn(min = 50.dp)
            .padding(horizontal = 20.dp, vertical = 13.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (working) {
            ActivityIndicator(Modifier.clearAndSetSemantics {}, size = REGULAR_SPINNER, tint = colors.onAccent)
        } else if (symbol != null) {
            PeekSymbol(symbol, font = type.style(IosTextStyle.Body, FontWeight.SemiBold), tint = colors.onAccent)
        }
        Text(title, Modifier.weight(1f, fill = false), color = colors.onAccent, style = type.headline.sfLines(), textAlign = TextAlign.Center)
    }
}

/** "share.stop" in footnote secondary, underlined, at the trailing end, 44 tall, a plain button; off while working. */
@Composable
private fun StopButton(enabled: Boolean, stop: () -> Unit) {
    val colors = PeekTheme.colors
    Box(Modifier.fillMaxWidth().padding(horizontal = 2.dp), contentAlignment = Alignment.CenterEnd) {
        Box(
            Modifier
                .testTag(PARCEL_SHARE_STOP)
                .clickable(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = stop)
                .heightIn(min = 44.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                localized("share.stop"),
                color = colors.secondaryLabel,
                style = PeekTheme.type.footnote.sfLines().copy(textDecoration = TextDecoration.Underline),
            )
        }
    }
}

/** `line(_:symbol:)`: the symbol 8 before the words in footnote, 12 in on the ink at 5 % with 12-point corners. */
@Composable
private fun ShareLine(text: String, symbol: String, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val style = PeekTheme.type.footnote
    Row(
        modifier.fillMaxWidth().background(colors.ink.copy(alpha = colors.ink.alpha * 0.05f), ContinuousRoundedShape(12.dp)).padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PeekSymbol(symbol, Modifier.alignByBaseline(), font = style, tint = colors.label)
        Text(text, Modifier.weight(1f).alignByBaseline(), color = colors.label, style = style.sfLines())
    }
}

/** `warning(_:)`: as [ShareLine], with exclamationmark.circle, in the warning colour on its soft colour at 60 %. */
@Composable
private fun ShareWarning(text: String) {
    val colors = PeekTheme.colors
    val style = PeekTheme.type.footnote
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(PARCEL_SHARE_FAILURE)
            .background(colors.warningSoft.copy(alpha = colors.warningSoft.alpha * 0.6f), ContinuousRoundedShape(12.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PeekSymbol("exclamationmark.circle", Modifier.alignByBaseline(), font = style, tint = colors.warning)
        Text(text, Modifier.weight(1f).alignByBaseline(), color = colors.warning, style = style.sfLines())
    }
}

/**
 * `SharePreviewCard`: what the link shows, as a small card. "share.preview.title" in caption secondary over it, or
 * "share.preview.wrapped" for a gift on its way. In it, 3 apart: the carrier's mark, and the one that delivers where
 * another does, 7 apart and 4 over the rest; the name the link carries, after "share.gift.inside" for a gift, in
 * footnote semibold; the headline in title3 semibold ("share.gift.headline" for a wrapped gift, "share.gift.here" for
 * one delivered, else the status); what the link says of it in footnote medium, in lilac for a gift or the carrier's
 * ink; and the number as the link shows it, whole or masked, in monospaced caption 3 under. Pip 12 beside them, except
 * at the accessibility sizes ([ShareFigure]). 16 and 14 in, on lilac's surface for a gift or the carrier's, with
 * 18-point continuous corners. A gift on its way is the wrapped parcel, with no number and no name. TalkBack reads it
 * as one.
 */
@Composable
private fun SharePreviewCard(parcel: Parcel, name: String?, showNumber: Boolean, gift: Boolean) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val now = LocalNow.current()
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val identity = carrierIdentity(parcel.displayedCarrier, language) ?: return
    val deliveringCarrier = parcel.deliveringCarrier
    val delivery = deliveringCarrier?.let { carrierIdentity(it, language) }
    val delivered = parcel.currentStage == TrackingStage.Delivered
    val wrapped = gift && !delivered
    val headline = when {
        wrapped -> localizer.text("share.gift.headline")
        gift -> localizer.text("share.gift.here")
        else -> localizer.parcelStatus(parcel)
    }
    val detail = if (gift) {
        localizer.giftDetail(parcel, opened = delivered, now) ?: if (delivered) localizer.sharedParcelDetail(parcel, now) else null
    } else {
        localizer.sharedParcelDetail(parcel, now)
    }
    val number = if (showNumber) CarrierCatalog.format(parcel.trackingNumber, parcel.carrier) else ParcelNumberHint(hiding = parcel.trackingNumber).masked
    Column(Modifier.fillMaxWidth().testTag(PARCEL_SHARE_PREVIEW).semantics(mergeDescendants = true) {}, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            localizer.text(if (wrapped) "share.preview.wrapped" else "share.preview.title"),
            Modifier.padding(horizontal = 2.dp),
            color = colors.secondaryLabel,
            style = type.caption.sfLines(),
        )
        Row(
            Modifier
                .fillMaxWidth()
                .background(colors.resolve(if (gift) ExperimentalPalette.lilacSurface else identity.surface), ContinuousRoundedShape(18.dp))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Column(Modifier.padding(bottom = 4.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    CarrierFleetMark(identity)
                    if (delivery != null) CarrierFleetMark(delivery)
                }
                if (name != null && !wrapped) {
                    Text(
                        if (gift) localizer.text("share.gift.inside") + " " + name else name,
                        color = colors.label,
                        style = type.style(IosTextStyle.Footnote, FontWeight.SemiBold).sfLines(),
                    )
                }
                Text(headline, color = colors.label, style = type.style(IosTextStyle.Title3, FontWeight.SemiBold).sfLines())
                if (detail != null) {
                    Text(
                        detail,
                        color = colors.resolve(if (gift) ExperimentalPalette.lilac else identity.ink),
                        style = type.style(IosTextStyle.Footnote, FontWeight.Medium).sfLines(),
                    )
                }
                if (!wrapped) {
                    Text(
                        number,
                        Modifier.padding(top = 3.dp),
                        color = colors.label,
                        style = type.style(IosTextStyle.Caption, design = FontDesign.Monospaced).sfLines(),
                    )
                }
            }
            if (!PeekTheme.dynamicTypeSize.isAccessibilitySize) {
                // Its side carries the carrier's label, with the number as the link shows it; a gift has none, nor has
                // a number no carrier knows.
                val known = parcel.carrier != CarrierId.Unknown || parcel.hasCarrierUpdate
                val label = if (known && !gift) PipLabel(identity, number) else null
                if (gift || delivered) {
                    ShareFigure(open = delivered, label, ribbon = gift)
                } else {
                    SmallPip(Modifier.width(BOX_WIDTH.dp), label)
                }
            }
        }
    }
}

/**
 * `figure(_:)` for a gift or a parcel that has arrived: `UnwrappingParcel(open:label:ribbon:grounded: false)` drawn at
 * the sticker's scale (78 for the closed box's 204) and cut to the box, closed or open with the card risen out of it
 * (`closedBox`, `openBox`), without the shadow under it; the open box reaches 8 past the row above and below.
 */
@Composable
private fun ShareFigure(open: Boolean, label: PipLabel?, ribbon: Boolean) {
    val crop = if (open) OPEN_BOX else CLOSED_BOX
    val scale = BOX_WIDTH / CLOSED_BOX.width
    val width = crop.width * scale
    val height = crop.height * scale
    val overhang = if (open) OPEN_OVERHANG else 0.dp
    Box(
        Modifier
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                val inset = overhang.roundToPx()
                layout(placeable.width, placeable.height - 2 * inset) { placeable.place(0, -inset) }
            }
            .size(width.dp, height.dp)
            .clipToBounds()
            .clearAndSetSemantics {}
            .layout { measurable, _ ->
                val frame = measurable.measure(Constraints.fixed(FRAME_WIDTH.dp.roundToPx(), FRAME_HEIGHT.dp.roundToPx()))
                layout(width.dp.roundToPx(), height.dp.roundToPx()) {
                    frame.placeWithLayer((-crop.left * scale).dp.roundToPx(), (-crop.top * scale).dp.roundToPx()) {
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
                }
            },
    ) {
        UnwrappingParcel(open, label, ribbon)
    }
}

private val SHARE_WIDTH = 560.dp
private val SHARE_SIDE = 22.dp
private val SHARE_TOP = 26.dp
private val SHARE_BOTTOM = 18.dp

/** `ProgressView().controlSize(.small)`, 16 across. */
private val SMALL_SPINNER = 16.dp

private val LABEL_SPACING = 6.dp

/** How long "Copied" shows after a copy. */
private const val COPIED_DURATION = 2_000L

/** How long Share waits for the window to go to the system's share sheet before it hands the address out again. */
private const val CHOOSER_GUARD = 1_000L

/** An address's scheme, which the link row leaves out. */
private val SCHEME = Regex("^https?://")

/** Pip's frame, the part of it the closed box fills and the part its open flaps and card reach into, and the box's width. */
private val FRAME_WIDTH = PipGeometry.frame.width.toFloat()
private val FRAME_HEIGHT = PipGeometry.frame.height.toFloat()
private val CLOSED_BOX = androidx.compose.ui.geometry.Rect(Offset(48f, 88f), androidx.compose.ui.geometry.Size(204f, 196f))
private val OPEN_BOX = androidx.compose.ui.geometry.Rect(Offset(10f, 28f), androidx.compose.ui.geometry.Size(280f, 256f))
private const val BOX_WIDTH = 78f

private val OPEN_OVERHANG = 8.dp

internal const val PARCEL_SHARE = "parcelShare"
internal const val PARCEL_SHARE_TITLE = "parcelShare.title"
internal const val PARCEL_SHARE_CLOSE = "parcelShare.close"
internal const val PARCEL_SHARE_PREVIEW = "parcelShare.preview"
internal const val PARCEL_SHARE_NUMBER = "parcelShare.number"
internal const val PARCEL_SHARE_NAME = "parcelShare.name"
internal const val PARCEL_SHARE_GIFT = "parcelShare.gift"
internal const val PARCEL_SHARE_NOTE = "parcelShare.note"
internal const val PARCEL_SHARE_FROM = "parcelShare.from"
internal const val PARCEL_SHARE_LINK = "parcelShare.link"
internal const val PARCEL_SHARE_ADDRESS = "parcelShare.address"
internal const val PARCEL_SHARE_PENDING = "parcelShare.pending"
internal const val PARCEL_SHARE_COPY = "parcelShare.copy"
internal const val PARCEL_SHARE_ACTION = "parcelShare.action"
internal const val PARCEL_SHARE_STOP = "parcelShare.stop"
internal const val PARCEL_SHARE_AGAIN = "parcelShare.again"
internal const val PARCEL_SHARE_STOPPED = "parcelShare.stopped"
internal const val PARCEL_SHARE_DEMO = "parcelShare.demo"
internal const val PARCEL_SHARE_FAILURE = "parcelShare.failure"
