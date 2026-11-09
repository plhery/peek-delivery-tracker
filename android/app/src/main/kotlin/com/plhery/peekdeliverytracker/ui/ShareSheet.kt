package com.plhery.peekdeliverytracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.MotionCurve
import com.plhery.peekdeliverytracker.core.SharedContent
import com.plhery.peekdeliverytracker.core.SharedParcelDraft
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.PeekMarkArtworkImage
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetInsets
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.sheetBottomInset
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.ControlHighlight
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.ProvideDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.pressEffect
import com.plhery.peekdeliverytracker.ui.theme.SystemColors
import com.plhery.peekdeliverytracker.ui.theme.TextLines
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.widestLine

/** What the share card says (`statusLabel`), which says whether Save is there and on. */
enum class SharePhase { Reading, Ready, NotFound, Saved, SaveFailed }

/**
 * The share card's state (`ShareViewController`'s): what it says, and what was read to save. Kept with the window, so a
 * turn of the phone keeps what was saved.
 */
@Stable
class ShareCard(phase: SharePhase = SharePhase.Reading, content: SharedContent.Read? = null) {
    var phase by mutableStateOf(phase)
        private set
    var content: SharedContent.Read? = content
        private set

    /** `loadSharedContent()`'s answer: ready with what was read, or nothing found. */
    fun read(content: SharedContent.Read?) {
        if (phase != SharePhase.Reading) return
        this.content = content
        phase = if (content == null) SharePhase.NotFound else SharePhase.Ready
    }

    /** `saveShare()`: what was read, saved by `save` and read back, or why it wasn't; once saved, nothing more. */
    fun save(save: (SharedParcelDraft) -> Boolean) {
        val content = content ?: return
        if (phase != SharePhase.Ready && phase != SharePhase.SaveFailed) return
        phase = if (save(SharedParcelDraft(label = content.label, trackingInput = content.trackingInput))) SharePhase.Saved else SharePhase.SaveFailed
    }

    companion object {
        val Saver = listSaver<ShareCard, String?>(
            save = { listOf(it.phase.name, it.content?.label, it.content?.trackingInput) },
            restore = { (phase, label, text) ->
                ShareCard(SharePhase.valueOf(phase!!), text?.let { SharedContent.Read(label.orEmpty(), it) })
            },
        )
    }
}

/**
 * The share extension's sheet over the app that shared (B-S70, B-ST36): on a phone the sheet at its large detent with
 * no grabber, as UIKit presents the extension; in a regular window the form sheet in its middle; the app that shared
 * dims under it, in the phone's appearance, as the iPhone's host does in its own. It says it reads what was shared
 * ([read], which the card waits for), then that it is ready to save or found nothing; saved (by [save], which reads the
 * draft back) Save goes and Cancel becomes Done, and a save that failed can be tried again. Cancel, Done, a drag down,
 * a tap beside it and the back take it down, and then [onClosed] hears whether it was saved, as the extension
 * completes its request or cancels it; a turn of the phone as it goes down doesn't bring it back. The card speaks
 * [copy]'s language.
 */
@Composable
fun ShareSheet(
    copy: ExtensionLocalizer,
    read: suspend () -> SharedContent.Read?,
    save: (SharedParcelDraft) -> Boolean,
    onClosed: (saved: Boolean) -> Unit,
    card: ShareCard = rememberSaveable(saver = ShareCard.Saver) { ShareCard() },
) {
    // Kept with the window: made again as it went down, it has gone, and the close goes on to its end.
    var visible by rememberSaveable { mutableStateOf(true) }
    val wentDown = remember { !visible }
    LaunchedEffect(Unit) {
        if (wentDown) onClosed(card.phase == SharePhase.Saved)
    }
    LaunchedEffect(card) {
        if (card.phase == SharePhase.Reading) card.read(read())
    }
    Sheet(
        visible = visible, onDismiss = { visible = false }, paneTitle = copy.text("shareExtension.title"),
        detents = listOf(SheetDetent.Large), onClosed = { onClosed(card.phase == SharePhase.Saved) }, grabber = false,
    ) {
        ShareCardView(card, copy, onSave = { if (visible) card.save(save) }, onClose = { visible = false })
    }
}

/**
 * The card (`configureInterface()`): Peek's mark, its name, what it found and Save over Cancel, a column in the middle
 * of the sheet, as wide as the sheet's margins leave it less 8 on each side; where it doesn't fit, at the largest text
 * sizes, it scrolls, 16 clear of the sheet's top and of the navigation bar. The iPhone's has a beige ground of its own
 * under the system's adaptive label colours, which in dark mode puts light text on it; this one is the iPhone's light
 * card, in either appearance.
 */
@Composable
private fun ShareCardView(card: ShareCard, copy: ExtensionLocalizer, onSave: () -> Unit, onClose: () -> Unit) {
    val language = AppLanguage.fromRawValue(copy.languageCode) ?: AppLanguage.En
    val theme = PeekTheme.colors
    val side = LocalSheetInsets.current.let { insets ->
        // A phone's sheet reaches the window's sides, and keeps to its safe area there.
        if (insets.reachesBottom) Modifier.windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)) else Modifier
    }.padding(horizontal = LocalSheetInsets.current.formSide + STACK_INSET)
    PeekTheme(
        dark = false, highContrast = theme.highContrast, reduceMotion = PeekTheme.reduceMotion,
        reduceTransparency = PeekTheme.reduceTransparency, dynamicTypeSize = PeekTheme.dynamicTypeSize, carrierIdentities = null,
    ) {
        ProvideDynamicTypeSize(PeekTheme.dynamicTypeSize, language) {
            val colors = PeekTheme.colors
            val type = PeekTheme.type
            val phase = card.phase
            // Clear of the sheet's top and of the navigation bar once it scrolls (`viewSafeAreaInsetsDidChange`).
            val margin = sheetBottomInset() + STACK_MARGIN
            BoxWithConstraints(Modifier.fillMaxSize().background(PAPER).testTag(SHARE_CARD)) {
                // Centred on the sheet while it fits; at the largest text sizes it scrolls instead of running off it.
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .heightIn(min = maxHeight)
                        .padding(vertical = margin)
                        .then(side),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    PeekMarkArtworkImage(MARK)
                    Spacer(Modifier.height(SPACING))
                    Text(
                        copy.text("shareExtension.title"), Modifier.testTag(SHARE_TITLE),
                        style = type.style(IosTextStyle.Title2, FontWeight.Bold).sfLines(), color = colors.label,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(Modifier.height(SPACING))
                    Text(
                        copy.text(phase.statusKey), Modifier.testTag(SHARE_STATUS),
                        style = type.subheadline.sfLines(), color = colors.secondaryLabel, textAlign = TextAlign.Center,
                    )
                    Spacer(Modifier.height(STATUS_SPACING))
                    // Saved, Save is hidden, and the stack closes up over it.
                    if (phase != SharePhase.Saved) {
                        SaveButton(copy.text("shareExtension.save"), enabled = phase == SharePhase.Ready || phase == SharePhase.SaveFailed, onSave)
                        Spacer(Modifier.height(SPACING))
                    }
                    Text(
                        copy.text(if (phase == SharePhase.Saved) "native.done" else "common.cancel"),
                        Modifier
                            .testTag(SHARE_CLOSE)
                            .button(interactionSource = null, indication = SystemPress, role = Role.Button, onClick = onClose)
                            .padding(vertical = CLOSE_PADDING),
                        style = type.fixed(type.scaled(CLOSE_SIZE)).sfLines(), color = colors.resolve(SystemColors.blue),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

/**
 * Save (`UIButton.Configuration.filled()`): the tray and its title on Peek's yellow across the column, 52 tall and
 * growing with its title from there, its large corners; off, the grey UIKit gives a filled button turned off. Pressed,
 * the yellow fades, and the tray and title fade over it on their own, so that they take on the yellow beneath rather
 * than the card's.
 */
@Composable
private fun SaveButton(title: String, enabled: Boolean, onClick: () -> Unit) {
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(SAVE_RADIUS)
    val content = if (enabled) INK else colors.tertiaryLabel
    val presses = remember { MutableInteractionSource() }
    val pressed by presses.collectIsPressedAsState()
    Box(
        Modifier
            .fillMaxWidth()
            .testTag(SHARE_SAVE)
            .button(interactionSource = presses, indication = ControlHighlight(shape), enabled = enabled, role = Role.Button, onClick = onClick),
    ) {
        Box(Modifier.matchParentSize().pressEffect(PlainPress, pressed).background(if (enabled) YELLOW else DISABLED_FILL, shape))
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = SAVE_HEIGHT)
                .pressEffect(PlainPress, pressed)
                .padding(horizontal = SAVE_PADDING, vertical = SAVE_VERTICAL_PADDING),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val font = PeekTheme.type.body.sfLines()
            val lines = remember { TextLines() }
            PeekSymbol("tray.and.arrow.down", font = font, tint = content)
            Spacer(Modifier.width(IMAGE_PADDING))
            // As wide as its widest line, so that the symbol stays beside it.
            Text(
                title, Modifier.weight(1f, fill = false).widestLine(lines),
                style = font, color = content, textAlign = TextAlign.Center, onTextLayout = { lines.result = it },
            )
        }
    }
}

private val SharePhase.statusKey: String
    get() = when (this) {
        SharePhase.Reading -> "shareExtension.reading"
        SharePhase.Ready -> "shareExtension.ready"
        SharePhase.NotFound -> "shareExtension.notFound"
        SharePhase.Saved -> "shareExtension.saved"
        SharePhase.SaveFailed -> "shareExtension.saveFailed"
    }

/** A system button pressed (UIButton's `.system` type): its title fades to a fifth. */
internal val SystemPress = PressStyle(opacity = 0.2f, animation = MotionCurve.EaseOut(0.12))

/** The card's ground, rgb(0.969, 0.953, 0.918). */
private val PAPER = Color(0.969f, 0.953f, 0.918f)

/** Save's ground and its title, rgb(1, 0.81, 0) and rgb(0.09, 0.09, 0.08). */
private val YELLOW = Color(1f, 0.81f, 0f)
private val INK = Color(0.09f, 0.09f, 0.08f)

/** `tertiarySystemFill`, under a filled button turned off. */
private val DISABLED_FILL = Color(118 / 255f, 118 / 255f, 128 / 255f, 0.12f)

private val MARK = 76.dp
private val SPACING = 18.dp
private val STATUS_SPACING = 26.dp

/** The column's inset from the sheet's layout margins. */
private val STACK_INSET = 8.dp
private val SAVE_HEIGHT = 52.dp

/** Clear of the sheet's ends, past the navigation bar, once the column scrolls. */
private val STACK_MARGIN = 16.dp

/** `cornerStyle = .large`. */
private val SAVE_RADIUS = 12.dp
private val SAVE_PADDING = 12.dp

/** Save's title 7 from its top and bottom, the filled configuration's insets, once it grows past 52. */
private val SAVE_VERTICAL_PADDING = 7.dp
private val IMAGE_PADDING = 8.dp

/** A system button's title, 15 points grown as the body grows (`UIFontMetrics`), and the 6 above and below it. */
private const val CLOSE_SIZE = 15f
private val CLOSE_PADDING = 6.dp

const val SHARE_CARD = "share-card"
const val SHARE_TITLE = "share-title"
const val SHARE_STATUS = "share-status"
const val SHARE_SAVE = "share-save"
const val SHARE_CLOSE = "share-close"
