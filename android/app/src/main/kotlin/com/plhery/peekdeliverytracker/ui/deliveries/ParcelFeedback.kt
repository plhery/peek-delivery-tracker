package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.currentStateAsState
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.ParcelFeedbackCarrierSheet
import com.plhery.peekdeliverytracker.core.ParcelFeedbackClient
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel.Moment
import com.plhery.peekdeliverytracker.core.ParcelFeedbackModel.Word
import com.plhery.peekdeliverytracker.core.ParcelFeedbackQuestion
import com.plhery.peekdeliverytracker.core.ParcelFeedbackWordsSheet
import com.plhery.peekdeliverytracker.core.ReviewPrompt
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestAsked
import com.plhery.peekdeliverytracker.core.labelKey
import com.plhery.peekdeliverytracker.core.text
import com.plhery.peekdeliverytracker.core.thanksKey
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.NoSuggestions
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.SmallPip
import com.plhery.peekdeliverytracker.ui.draw.UnevenContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.keptFocus
import com.plhery.peekdeliverytracker.ui.sheet.KeyboardScrollColumn
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.sheetBottomInset
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekKeyframes
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.delay
import com.plhery.peekdeliverytracker.ui.theme.keyframeAnimator
import com.plhery.peekdeliverytracker.ui.theme.rememberHaptics
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.swiftUiShadow
import java.util.UUID
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch

// ParcelFeedbackViews.swift: the question under a parcel's page, Pip's or the row asked where no carrier was found,
// and what it lays over the page: the question asked on the way back from the carrier's site, the sheets that take the
// words, and the words that follow them. What it says and its fields' rules are :core's (ParcelFeedbackViews.kt).

/**
 * The questions of this process's pages, by page, each its [ParcelFeedbackModel] and what its views keep beside it
 * ([ParcelFeedbackWork]). The source holds them for the launch, as [PageWork] holds the pages' changes: a page drawn
 * again as the window is made again (a rotation, a fold) finds its moment, an open sheet with what was typed in it and
 * an answer on its way as they were, as the iPhone's `@StateObject` lasts through a rotation. Each opening of a page
 * has a question of its own ([open]), made as the page is first drawn; a page that goes forgets it ([forget]), and
 * nothing it draws on its way out makes another. An answer on its way still gets there.
 */
class ParcelFeedbacks {
    private val works = HashMap<UUID, ParcelFeedbackWork>()

    /** The pages open, whose question their drawing makes once; read as they are drawn, which a change draws again. */
    private var open by mutableStateOf(emptySet<UUID>())
    private var sheets by mutableStateOf(emptySet<UUID>())

    /**
     * A page's question has a sheet open (`feedback.words != nil || feedback.namingCarrier`): nothing else over the page
     * takes a tap or TalkBack meanwhile. The window that shows it keeps it covered while it goes down
     * ([DeliveriesState.feedbackSheetShown]).
     */
    val covering: Boolean get() = sheets.isNotEmpty()

    /** The page with `pageId` opens: its next drawing makes its question afresh, as the iPhone's push makes a `@StateObject`. */
    fun open(pageId: UUID) {
        forget(pageId)
        open += pageId
    }

    /**
     * The question of the open page with `pageId`, made by `make` the first time, working in `scope`; none for a page
     * that has gone, which keeps the one it had as it leaves.
     */
    internal fun work(pageId: UUID, scope: CoroutineScope, make: () -> ParcelFeedbackModel): ParcelFeedbackWork? {
        if (pageId !in open) return null
        return works.getOrPut(pageId) {
            ParcelFeedbackWork(make(), scope) { up -> sheets = if (up) sheets + pageId else sheets - pageId }.also { it.start() }
        }
    }

    /** The question of the page with `pageId`, while it has one. */
    internal fun of(pageId: UUID): ParcelFeedbackWork? = works[pageId]

    /** The page with `pageId` has gone: its question stops watching its model. */
    fun forget(pageId: UUID) {
        open -= pageId
        works.remove(pageId)?.close()
        sheets -= pageId
    }
}

/**
 * One page's [ParcelFeedbackModel], working in the app's `scope`, and what its views keep beside it: the model's state
 * as the page draws it, taken all at once whenever any of it changes, and the open sheet's own (its `@State`): the
 * reasons chosen, the words typed, whether the last send failed and the large detent, fresh for each sheet that opens.
 * A word at the page's foot leaves once it has stayed its time (`.task(id: model.word)`), on the app's clock.
 */
@Stable
internal class ParcelFeedbackWork(val model: ParcelFeedbackModel, private val scope: CoroutineScope, private val cover: (Boolean) -> Unit) {
    var page: ParcelFeedbackModel.Page? by mutableStateOf(null)
        private set
    var moment: Moment by mutableStateOf(Moment.Ask)
        private set
    var word: Word? by mutableStateOf(null)
        private set
    var sending: Boolean by mutableStateOf(false)
        private set

    /** The words sheet's answer while it is open (`model.words`), and the carrier sheet open (`model.namingCarrier`). */
    var words: ParcelFeedbackModel.Words? by mutableStateOf(null)
        private set
    var namingCarrier: Boolean by mutableStateOf(false)
        private set

    var reasons: Set<ParcelFeedbackReason> by mutableStateOf(emptySet())
        private set
    var note: String by mutableStateOf("")
        private set
    var who: String by mutableStateOf("")
        private set
    var address: String by mutableStateOf("")
        private set
    var failed: Boolean by mutableStateOf(false)
        private set

    /** A "Yes" the service took was the moment to ask for a rating, and the page hasn't asked yet (`reviewDue`). */
    var reviewDue: Boolean by mutableStateOf(false)
        private set

    /** Each time a send failed, for TalkBack to hear (`FeedbackFailure`'s announcement). */
    var failures: Int by mutableIntStateOf(0)
        private set

    /** The sheet stands at its large detent: dragged there, or opened fully to write (`detent`). */
    var large: Boolean by mutableStateOf(false)

    /** A sheet is open, as the page has heard last. */
    private var covered = false

    private val jobs = mutableListOf<Job>()

    val standing: ParcelFeedbackQuestion? get() = page?.question

    /** A sheet is open, the words' or the carrier's (`feedback.words != nil || feedback.namingCarrier`). */
    val open: Boolean get() = words != null || namingCarrier

    /** Nothing in the words sheet to send, nor in the carrier sheet. */
    val wordsEmpty: Boolean get() = ParcelFeedbackWordsSheet.empty(reasons, note)
    val carrierEmpty: Boolean get() = ParcelFeedbackCarrierSheet.empty(who, address)

    init {
        pull()
    }

    fun start() {
        val model = model
        jobs += scope.launch {
            merge(
                model.pageFlow, model.momentFlow, model.wordsFlow, model.namingCarrierFlow, model.wordFlow, model.sendingFlow, model.reviewDueFlow,
            ).collect { pull() }
        }
        jobs += scope.launch {
            model.wordFlow.collectLatest { word ->
                if (word == null) return@collectLatest
                delay(word.stay)
                model.wordLeft(word)
            }
        }
    }

    fun close() {
        jobs.forEach { it.cancel() }
    }

    /** The model as it is now, which may have changed in several ways at once. */
    private fun pull() {
        val words = model.words
        if (words != null && words != this.words) {
            reasons = words.reasons
            note = ""
            failed = false
            large = false
        }
        this.words = words
        val naming = model.namingCarrier
        if (naming && !namingCarrier) {
            who = ""
            address = ""
            failed = false
            large = false
        }
        namingCarrier = naming
        page = model.page
        moment = model.moment
        word = model.word
        sending = model.sending
        reviewDue = model.reviewDue
        covers()
    }

    private fun covers() {
        if (covered == open) return
        covered = open
        cover(covered)
    }

    /** `feedback.show(_:through:)`: what the page shows now. */
    fun show(page: ParcelFeedbackModel.Page?, through: ParcelFeedbackClient?) {
        model.show(page, through)
        pull()
    }

    fun visited(site: String) = model.visited(site)

    fun left() = model.left()

    fun returned(busy: Boolean) {
        model.returned(busy)
        pull()
    }

    /** An answer, run in the app's scope; what it shows at once is drawn at once. */
    private fun answer(run: suspend () -> Unit) {
        scope.launch(start = CoroutineStart.UNDISPATCHED) {
            run()
            pull()
        }
        pull()
    }

    fun right(asked: ParcelFeedbackRequestAsked = ParcelFeedbackRequestAsked.Page) = answer { model.right(asked) }

    fun pick(reason: ParcelFeedbackReason) = answer { model.pick(reason) }

    fun notQuite() = answer { model.notQuite() }

    fun addNote() = answer { model.addNote() }

    fun wrongOnReturn() = answer { model.wrongOnReturn() }

    fun elsewhere() = answer { model.elsewhere() }

    fun notYet() = answer { model.notYet() }

    fun toggle(reason: ParcelFeedbackReason) {
        reasons = if (reason in reasons) reasons - reason else reasons + reason
    }

    /** What is typed, each kept within its limit as it is typed (the sheets' `onChange`). */
    fun typeNote(typed: String) {
        note = ParcelFeedbackWordsSheet.note(typed)
    }

    fun typeWho(typed: String) {
        who = ParcelFeedbackCarrierSheet.who(typed)
    }

    fun typeAddress(typed: String) {
        address = ParcelFeedbackCarrierSheet.page(typed)
    }

    /** The sheet's `send()`: what it holds, and the failure said where it couldn't go. */
    fun sendWords() = send { model.sendWords(reasons, note) }

    fun sendCarrier() = send { model.sendCarrier(name = who, page = address) }

    private fun send(sent: suspend () -> Boolean) = answer {
        failed = false
        if (!sent()) {
            failed = true
            failures++
        }
    }

    /** `takeReviewMoment()`: the page asks for a rating now, true once while the rule still holds. */
    fun takeReviewMoment(): Boolean = model.takeReviewMoment().also { pull() }

    /** A sheet closes (its binding set to nil): its tap outside, its back, its drag down or ✕. */
    fun dismissWords() {
        model.words = null
        pull()
    }

    fun dismissCarrier() {
        model.namingCarrier = false
        pull()
    }
}

/**
 * ParcelFeedbackQuestionView (B-S58, B-S59): the question standing under the page while it has one, Pip's for a parcel
 * a carrier answers for, the row for one none was found for. It comes in once 20 % of it shows in the page's scroll,
 * whose `viewport` is where it is seen (`onScrollVisibilityChange(threshold: 0.2)`), whatever the page's zoom as it
 * opens; at once under Reduce Motion; and keeps having come through the window made again. Each change of moment
 * settles with `spring(response: 0.42, dampingFraction: 0.86)`, or eases in and out over 0.2 s under Reduce Motion;
 * and each answer taken makes Pip hop, except under Reduce Motion.
 */
@Composable
internal fun ParcelFeedbackQuestion(work: ParcelFeedbackWork, viewport: () -> LayoutCoordinates?, modifier: Modifier = Modifier) {
    val standing = work.standing ?: return
    var arrived by rememberSaveable { mutableStateOf(false) }
    var hops by rememberSaveable { mutableIntStateOf(0) }
    LaunchedEffect(work) {
        snapshotFlow { work.moment in HAPPY }.drop(1).collect { happy -> if (happy) hops++ }
    }
    val shown = arrived || PeekTheme.reduceMotion
    val arrival = Modifier.onGloballyPositioned { coordinates ->
        val scroll = viewport()
        if (arrived || scroll == null || !scroll.isAttached) return@onGloballyPositioned
        val whole = scroll.localBoundingBoxOf(coordinates, clipBounds = false)
        val seen = whole.intersect(Rect(Offset.Zero, scroll.size.toSize()))
        if (whole.height > 0f && seen.height >= whole.height * ARRIVAL) arrived = true
    }
    when (standing) {
        ParcelFeedbackQuestion.Found -> PipQuestion(work, shown, hops, modifier.fillMaxWidth().then(arrival))
        ParcelFeedbackQuestion.Unknown -> UnknownCarrierRow(work, shown, modifier.fillMaxWidth().then(arrival))
    }
}

/**
 * `pip`: Pip, 50 wide, 2 past the leading side and 3 under the bubble's foot, 8 before the bubble, their feet level;
 * at the accessibility sizes the words take his room. Smiling at an answer taken, blinking while he waits. He pops up
 * first (`spring(response: 0.46, dampingFraction: 0.58)`: from 10 lower, at 90 % about his feet, unseen), then the
 * bubble grows out of its tail 0.15 s after (`spring(response: 0.42, dampingFraction: 0.66)`, from 84 %). His hop
 * (`PeekKeyframes.hop`) rises and leans about his feet.
 */
@Composable
private fun PipQuestion(work: ParcelFeedbackWork, shown: Boolean, hops: Int, modifier: Modifier) {
    val reduceMotion = PeekTheme.reduceMotion
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val density = LocalDensity.current
    val rtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val happy by animateFloatAsState(if (work.moment in HAPPY) 1f else 0f, settle(reduceMotion), label = "pip happy")
    val pip by animateFloatAsState(if (shown) 1f else 0f, Motion.springWithResponse(0.46, 0.58), label = "pip arrival")
    val bubble by animateFloatAsState(if (shown) 1f else 0f, Motion.springWithResponse<Float>(0.42, 0.66).delay(0.15), label = "bubble arrival")
    val hop by keyframeAnimator(PeekKeyframes.Hop(), hops, still = reduceMotion) { PeekKeyframes.hop }
    Row(modifier.testTag(FEEDBACK_QUESTION), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.Bottom) {
        if (!large) {
            Box(
                Modifier
                    .overhang(start = 2.dp, bottom = 3.dp)
                    .graphicsLayer {
                        val drop = with(density) { ARRIVAL_DROP.toPx() }
                        alpha = pip.coerceIn(0f, 1f)
                        scaleX = 0.9f + 0.1f * pip
                        scaleY = 0.9f + 0.1f * pip
                        translationY = drop * (1f - pip)
                        transformOrigin = TransformOrigin(0.5f, 1f)
                    }
                    .graphicsLayer {
                        translationY = with(density) { hop.rise.dp.toPx() }
                        rotationZ = hop.turn.toFloat()
                        transformOrigin = TransformOrigin(0.5f, 1f)
                    }
                    .testTag(FEEDBACK_PIP)
                    .width(PIP_WIDTH),
            ) {
                SmallPip(Modifier.fillMaxWidth(), happy = happy.toDouble(), blinks = true)
            }
        }
        Bubble(
            work, large,
            Modifier
                .weight(1f)
                .graphicsLayer {
                    alpha = bubble.coerceIn(0f, 1f)
                    scaleX = 0.84f + 0.16f * bubble
                    scaleY = 0.84f + 0.16f * bubble
                    transformOrigin = TransformOrigin(if (rtl) 1f else 0f, 1f)
                },
        )
    }
}

/**
 * `bubble`: what Pip says, on the paper with 18-point continuous corners but 5 at its tail beside him (18 at the
 * accessibility sizes, with no Pip), a hairline of the separator at 60 % and a faint shadow; 14 in at its leading
 * side, and 8 elsewhere, or 12 while it offers the reasons; 52 tall at least, across what Pip leaves it. Asked, it
 * asks whether the page is right, "Yes" and "Not quite" beside or under the question; offering, the reasons; answered,
 * its thanks, with the way to add a note once a reason was given in place.
 */
@Composable
private fun Bubble(work: ParcelFeedbackWork, large: Boolean, modifier: Modifier) {
    val colors = PeekTheme.colors
    val localizer = LocalLocalizer.current
    val reduceMotion = PeekTheme.reduceMotion
    val shape = UnevenContinuousRoundedShape(topLeading = 18.dp, bottomLeading = if (large) 18.dp else 5.dp, bottomTrailing = 18.dp, topTrailing = 18.dp)
    val offering = work.moment == Moment.Reasons
    val trailing by animateDpAsState(if (offering) 12.dp else 8.dp, settle(reduceMotion), label = "bubble trailing")
    val vertical by animateDpAsState(if (offering) 12.dp else 8.dp, settle(reduceMotion), label = "bubble vertical")
    Box(
        modifier
            .testTag(FEEDBACK_BUBBLE)
            .dropShadow(shape, BUBBLE_SHADOW)
            .background(colors.paper, shape)
            .clip(shape)
            .border(1.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.6f), shape)
            .heightIn(min = 52.dp)
            .padding(start = 14.dp, end = trailing, top = vertical, bottom = vertical),
        contentAlignment = Alignment.CenterStart,
    ) {
        Turning(work.moment) { moment ->
            when (moment) {
                Moment.Right -> Said(localizer.text("feedback.pip.right"))
                Moment.Sent -> Beside({ Said(localizer.text("feedback.pip.sent")) }) { AddNote(work::addNote) }
                Moment.Noted -> Said(localizer.text("feedback.pip.sent"))
                Moment.Reasons -> Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Question(localizer.text("feedback.whatsOff"))
                    FeedbackFlow {
                        ParcelFeedbackReason.entries.forEachIndexed { order, reason ->
                            FeedbackPill(localizer.text(reason.labelKey), FEEDBACK_REASON + reason.name, Modifier.comesIn(order)) { work.pick(reason) }
                        }
                    }
                }
                Moment.Ask, Moment.Early, Moment.Named -> Beside({ Question(localizer.text("feedback.pip.question")) }) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        FeedbackPill(localizer.text("feedback.yes"), FEEDBACK_YES) { work.right() }
                        FeedbackPill(localizer.text("feedback.notQuite"), FEEDBACK_NOT_QUITE) { work.notQuite() }
                    }
                }
            }
        }
    }
}

/**
 * `row`: no Pip here, he found nothing to show. It asks whether the carrier's own site shows the parcel, "Yes" and
 * "Not yet" on paper with a hairline, or says its thanks; 16 in at its leading side and 8 elsewhere, 52 tall at least,
 * on the ink at 7 % with 16-point continuous corners. It comes in from 10 lower (`spring(response: 0.46,
 * dampingFraction: 0.86)`).
 */
@Composable
private fun UnknownCarrierRow(work: ParcelFeedbackWork, shown: Boolean, modifier: Modifier) {
    val colors = PeekTheme.colors
    val localizer = LocalLocalizer.current
    val density = LocalDensity.current
    val arrival by animateFloatAsState(if (shown) 1f else 0f, Motion.springWithResponse(0.46, 0.86), label = "row arrival")
    val shape = ContinuousRoundedShape(16.dp)
    Box(
        modifier
            .graphicsLayer {
                alpha = arrival.coerceIn(0f, 1f)
                translationY = with(density) { ARRIVAL_DROP.toPx() } * (1f - arrival)
            }
            .testTag(FEEDBACK_ROW)
            .background(colors.ink.copy(alpha = colors.ink.alpha * 0.07f), shape)
            .clip(shape)
            .heightIn(min = 52.dp)
            .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Turning(work.moment) { moment ->
            when (moment) {
                Moment.Early -> Said(localizer.text("feedback.unknown.early"))
                Moment.Named -> Said(localizer.text("feedback.unknown.sent"))
                Moment.Ask, Moment.Reasons, Moment.Right, Moment.Sent, Moment.Noted ->
                    Beside({ Question(localizer.text("feedback.unknown.question")) }) {
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            FeedbackPill(localizer.text("feedback.yes"), FEEDBACK_YES, onControl = true) { work.elsewhere() }
                            FeedbackPill(localizer.text("feedback.unknown.notYet"), FEEDBACK_NOT_YET, onControl = true) { work.notYet() }
                        }
                    }
            }
        }
    }
}

/**
 * `turning`: what an answer brings comes in after it, 0.06 s late, from 6 lower and unseen (`spring(response: 0.34,
 * dampingFraction: 0.8)`), as what it replaces folds away ([foldAway]): the bubble or the row grows or shrinks to it
 * from where it stood, with the moment's settle. Under Reduce Motion one fades into the other.
 */
@Composable
private fun Turning(moment: Moment, content: @Composable (Moment) -> Unit) {
    val reduceMotion = PeekTheme.reduceMotion
    val rise = with(LocalDensity.current) { TURN_RISE.roundToPx() }
    AnimatedContent(
        moment,
        Modifier.fillMaxWidth(),
        transitionSpec = {
            if (reduceMotion) {
                ContentTransform(fadeIn(Motion.easeInOut(0.2)), fadeOut(Motion.easeInOut(0.2)), sizeTransform = SizeTransform(false) { _, _ -> Motion.easeInOut(0.2) })
            } else {
                val comes = Motion.springWithResponse<Float>(0.34, 0.8).delay(0.06)
                val rises = Motion.springWithResponse<IntOffset>(0.34, 0.8).delay(0.06)
                (fadeIn(comes) + slideInVertically(rises) { rise }) togetherWith foldAway() using SizeTransform(false) { _, _ -> SETTLE }
            }
        },
        contentAlignment = Alignment.CenterStart,
        label = "feedback moment",
    ) { Staying { content(it) } }
}

/**
 * What is being replaced takes no tap on its way out, and TalkBack doesn't reach it, as SwiftUI hit-tests nothing it is
 * removing: an answer tapped again as it goes isn't given twice, nor another answer after it.
 */
@Composable
private fun AnimatedVisibilityScope.Staying(content: @Composable () -> Unit) {
    val leaving = transition.targetState == EnterExitState.PostExit
    Box(if (leaving) Modifier.clearAndSetSemantics {}.then(Untouched) else Modifier, propagateMinConstraints = true) { content() }
}

/** Takes every touch on its way to what it holds, which then sees none. */
internal val Untouched = Modifier.pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) awaitPointerEvent(PointerEventPass.Initial).changes.forEach { it.consume() }
    }
}

/**
 * `FoldAway`: what an answer replaces is gone at once (`easeOut(duration: 0.08)`), as it folds to nothing from its
 * foot, clipped, with the moment's settle, so that what stands in its place sets the size.
 */
private fun foldAway() = fadeOut(Motion.easeOut(0.08)) + shrinkVertically(SETTLE, shrinkTowards = Alignment.Top)

/**
 * `beside(_:then:)`: side by side, 12 apart at least and the second at the trailing end, while both fit on a line at
 * their widths (`ViewThatFits(in: .horizontal)`); else the second 8 under the first.
 */
@Composable
private fun Beside(first: @Composable () -> Unit, second: @Composable () -> Unit) {
    Layout(contents = listOf(first, second), modifier = Modifier.fillMaxWidth()) { (firsts, seconds), constraints ->
        val a = firsts.first()
        val b = seconds.first()
        val gap = BESIDE_GAP.roundToPx()
        val fits = a.maxIntrinsicWidth(Constraints.Infinity) + gap + b.maxIntrinsicWidth(Constraints.Infinity) <= constraints.maxWidth
        if (fits) {
            val first = a.measure(Constraints())
            val second = b.measure(Constraints())
            val height = max(max(first.height, second.height), constraints.minHeight)
            val width = constraints.maxWidth
            layout(width, height) {
                first.placeRelative(0, (height - first.height) / 2)
                second.placeRelative(width - second.width, (height - second.height) / 2)
            }
        } else {
            val loose = constraints.copy(minWidth = 0, minHeight = 0)
            val first = a.measure(loose)
            val second = b.measure(loose)
            val spacing = BESIDE_SPACING.roundToPx()
            layout(constraints.maxWidth, first.height + spacing + second.height) {
                first.placeRelative(0, 0)
                second.placeRelative(0, first.height + spacing)
            }
        }
    }
}

/** `question(_:)`: footnote semibold in the ink, on every line it needs. */
@Composable
private fun Question(text: String) {
    Text(text, color = PeekTheme.colors.ink, style = PeekTheme.type.style(IosTextStyle.Footnote, FontWeight.SemiBold).sfLines())
}

/** `said(_:)`: a word of thanks in footnote semibold, in the green of a parcel that arrived, its tick drawn as it comes, 7 before it. */
@Composable
private fun Said(text: String) {
    val tint = PeekTheme.colors.resolve(ExperimentalPalette.delivered)
    Row(
        Modifier.heightIn(min = 36.dp).padding(end = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DrawnTick(tint)
        Text(text, color = tint, style = PeekTheme.type.style(IosTextStyle.Footnote, FontWeight.SemiBold).sfLines())
    }
}

/** "feedback.addNote" in caption, in the soft ink and underlined in it at 45 %, 36 tall in a touch 44 tall, a plain button. */
@Composable
private fun AddNote(action: () -> Unit) {
    val colors = PeekTheme.colors
    val underline = colors.inkSoft.copy(alpha = colors.inkSoft.alpha * 0.45f)
    var layout by remember { mutableStateOf<TextLayoutResult?>(null) }
    Box(
        Modifier
            .overhang(vertical = 4.dp)
            .testTag(FEEDBACK_ADD_NOTE)
            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = action)
            .padding(vertical = 4.dp)
            .heightIn(min = 36.dp)
            .padding(end = 6.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            LocalLocalizer.current.text("feedback.addNote"),
            Modifier.drawBehind {
                val lines = layout ?: return@drawBehind
                val thickness = 1.dp.toPx()
                for (line in 0 until lines.lineCount) {
                    val y = lines.getLineBaseline(line) + UNDERLINE_GAP.toPx()
                    drawLine(underline, Offset(lines.getLineLeft(line), y), Offset(lines.getLineRight(line), y), thickness)
                }
            },
            color = colors.inkSoft,
            style = PeekTheme.type.caption.sfLines(),
            onTextLayout = { layout = it },
        )
    }
}

/**
 * `FeedbackPill`: a small answer in footnote medium, 14 and 6 in, 36 tall in a touch 44 tall, with 12-point continuous
 * corners, giving under the finger at 0.96. On paper it is the ink at 7 %; on the row's fill it is paper with a
 * hairline of the separator at 60 %. One wider than its line wraps its words.
 */
@Composable
private fun FeedbackPill(title: String, tag: String, modifier: Modifier = Modifier, onControl: Boolean = false, action: () -> Unit) {
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(12.dp)
    Box(
        modifier
            .overhang(vertical = 4.dp)
            .testTag(tag)
            .clickable(interactionSource = null, indication = PressStyle.tactile(0.96f), role = Role.Button, onClick = action)
            .padding(vertical = 4.dp)
            .heightIn(min = 36.dp)
            .background(if (onControl) colors.paper else colors.ink.copy(alpha = colors.ink.alpha * 0.07f), shape)
            .then(if (onControl) Modifier.border(1.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.6f), shape) else Modifier)
            .padding(horizontal = 14.dp, vertical = 6.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(title, color = colors.ink, style = PeekTheme.type.style(IosTextStyle.Footnote, FontWeight.Medium).sfLines())
    }
}

/**
 * `FeedbackFlow`: pills laid out like words, 6 apart either way, each line's pills centred on it. The height it tells
 * a fitted sheet is the height it lays out, its lines broken the same way: Compose's FlowRow can tell a line fewer
 * where a pill only just doesn't fit, and the sheet would cut off what is under it.
 */
@Composable
private fun FeedbackFlow(content: @Composable () -> Unit) {
    Layout(content, measurePolicy = FeedbackFlowPolicy)
}

private object FeedbackFlowPolicy : MeasurePolicy {
    override fun MeasureScope.measure(measurables: List<Measurable>, constraints: Constraints): MeasureResult {
        val spacing = FLOW_SPACING.roundToPx()
        val placeables = measurables.map { it.measure(Constraints(maxWidth = constraints.maxWidth)) }
        val lines = flowLines(placeables.map { it.width }, constraints.maxWidth, spacing)
        val heights = lines.map { line -> line.maxOf { placeables[it].height } }
        val widest = lines.maxOfOrNull { line -> line.sumOf { placeables[it].width } + spacing * (line.size - 1) } ?: 0
        val width = constraints.constrainWidth(widest)
        val height = constraints.constrainHeight(heights.sum() + spacing * max(0, lines.size - 1))
        return layout(width, height) {
            var y = 0
            lines.forEachIndexed { index, line ->
                var x = 0
                for (item in line) {
                    val placeable = placeables[item]
                    placeable.placeRelative(x, y + (heights[index] - placeable.height) / 2)
                    x += placeable.width + spacing
                }
                y += heights[index] + spacing
            }
        }
    }

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int =
        height(measurables, width) { maxIntrinsicHeight(it) }

    override fun IntrinsicMeasureScope.minIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int =
        height(measurables, width) { minIntrinsicHeight(it) }

    override fun IntrinsicMeasureScope.maxIntrinsicWidth(measurables: List<IntrinsicMeasurable>, height: Int): Int =
        measurables.sumOf { it.maxIntrinsicWidth(Constraints.Infinity) } + FLOW_SPACING.roundToPx() * max(0, measurables.size - 1)

    override fun IntrinsicMeasureScope.minIntrinsicWidth(measurables: List<IntrinsicMeasurable>, height: Int): Int =
        measurables.maxOfOrNull { it.minIntrinsicWidth(Constraints.Infinity) } ?: 0

    /** The lines' heights and the spacing between them, each pill as wide as it would be laid out. */
    private fun IntrinsicMeasureScope.height(measurables: List<IntrinsicMeasurable>, width: Int, tall: IntrinsicMeasurable.(Int) -> Int): Int {
        val spacing = FLOW_SPACING.roundToPx()
        val widths = measurables.map { min(it.maxIntrinsicWidth(Constraints.Infinity), width) }
        val lines = flowLines(widths, width, spacing)
        return lines.sumOf { line -> line.maxOf { measurables[it].tall(widths[it]) } } + spacing * max(0, lines.size - 1)
    }
}

/** The items on each line, a line broken where the next item would go past `room`. */
private fun flowLines(widths: List<Int>, room: Int, spacing: Int): List<List<Int>> {
    val lines = mutableListOf<MutableList<Int>>()
    var used = 0
    widths.forEachIndexed { index, width ->
        val line = lines.lastOrNull()
        if (line != null && used + spacing + width <= room) {
            line += index
            used += spacing + width
        } else {
            lines += mutableListOf(index)
            used = width
        }
    }
    return lines
}

/**
 * `ComesIn`: one of a row of answers, coming in 0.08 s late and 0.03 s after the one before it, from 6 lower and
 * unseen (`spring(response: 0.34, dampingFraction: 0.8)`); in place at once under Reduce Motion, and once it has come,
 * through the window made again.
 */
@Composable
private fun Modifier.comesIn(order: Int): Modifier {
    val reduceMotion = PeekTheme.reduceMotion
    val came = rememberSaveable { mutableStateOf(false) }
    val shown = remember { Animatable(if (came.value) 1f else 0f) }
    LaunchedEffect(Unit) {
        came.value = true
        shown.animateTo(1f, Motion.springWithResponse<Float>(0.34, 0.8).delay(0.08 + order * 0.03))
    }
    val rise = with(LocalDensity.current) { TURN_RISE.toPx() }
    return graphicsLayer {
        val progress = if (reduceMotion) 1f else shown.value
        alpha = progress.coerceIn(0f, 1f)
        translationY = rise * (1f - progress)
    }
}

/**
 * `DrawnTick`: the web's check on its 24-point grid, 16 across, stroked 2.2 with round ends, drawn in over 0.42 s
 * (`easeOut`) 0.16 s after it shows; drawn at once under Reduce Motion, and once it has been, through the window made
 * again. Decorative.
 */
@Composable
private fun DrawnTick(tint: Color) {
    val reduceMotion = PeekTheme.reduceMotion
    val drawnBefore = rememberSaveable { mutableStateOf(false) }
    val drawn = remember { Animatable(if (drawnBefore.value) 1f else 0f) }
    LaunchedEffect(Unit) {
        drawnBefore.value = true
        drawn.animateTo(1f, Motion.easeOut<Float>(0.42).delay(0.16))
    }
    Canvas(Modifier.size(TICK_SIDE).clearAndSetSemantics {}) {
        val trim = if (reduceMotion) 1f else drawn.value
        if (trim <= 0f) return@Canvas
        val unit = size.minDimension / 24f
        val tick = Path().apply {
            moveTo(5f * unit, 12f * unit)
            lineTo(9f * unit, 16f * unit)
            lineTo(19f * unit, 6f * unit)
        }
        val measure = PathMeasure().apply { setPath(tick, false) }
        val part = Path()
        measure.getSegment(0f, measure.length * trim.coerceAtMost(1f), part, true)
        drawPath(part, tint, style = Stroke(width = 2.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

/**
 * `.padding(-start)` and `.padding(-bottom)`, or `-vertical` on both ends: drawn and touched as large as it is, laid
 * out that much smaller, as a pill 44 tall to the touch stands in the room of 36.
 */
private fun Modifier.overhang(start: Dp = 0.dp, bottom: Dp = 0.dp, vertical: Dp = 0.dp): Modifier = layout { measurable, constraints ->
    val left = start.roundToPx()
    val top = vertical.roundToPx()
    val under = bottom.roundToPx() + top
    val placeable = measurable.measure(constraints.copy(maxHeight = if (constraints.hasBoundedHeight) constraints.maxHeight + top + under else constraints.maxHeight))
    layout(max(0, placeable.width - left), max(0, placeable.height - top - under)) { placeable.placeRelative(-left, -top) }
}

/**
 * `parcelFeedback(_:busy:)`: what the question lays over its page. At the page's foot, 16 in, 8 over its safe foot and
 * 560 wide at most, the word it says: the question asked on the way back from the carrier's site, Pip asking it beside
 * "Yes" and "No"; its thanks; or that an answer couldn't go. It moves up from under its place and fades in
 * (`spring(response: 0.4, dampingFraction: 0.86)`), and back as it goes, one word fading into the next in place, or
 * fades over 0.2 s under Reduce Motion ([FeedbackWord]). Then the sheets that take what is off and who carries the
 * parcel. TalkBack hears each word as it comes, the thanks said in place where no word says it, and each failure in a
 * sheet.
 *
 * The window leaving the screen and coming back (`didEnterBackground`, `didBecomeActive`) is the way to and back from
 * the carrier's site: back while something else is said or asked over the page (`busy`), the question waits.
 */
@Composable
internal fun ParcelFeedbackOverlays(state: DeliveriesState, work: ParcelFeedbackWork, backdrop: MaterialBackdrop?, modifier: Modifier) {
    FeedbackNews(state, work)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val activity = LocalActivity.current
    val busy by rememberUpdatedState(state.modal)
    DisposableEffect(lifecycle, work) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                // A window made again stays on the screen, as a rotated iPhone's scene does.
                Lifecycle.Event.ON_STOP -> if (activity?.isChangingConfigurations != true) work.left()
                Lifecycle.Event.ON_RESUME -> work.returned(busy)
                else -> Unit
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }
    FeedbackWord(work, backdrop, modifier)
    // A sheet up in this window covers the page until it has gone down, as the share sheet does; the window made again
    // once the question has closed it starts uncovered, as the iPhone's page reads `covered` from the model alone.
    val open = work.open
    SideEffect { if (open) state.feedbackSheetShown = true }
    DisposableEffect(state) { onDispose { state.feedbackSheetShown = false } }
    val gone = { if (!work.open) state.feedbackSheetShown = false }
    val localizer = LocalLocalizer.current
    val writing = { work.large = true }
    Sheet(
        visible = work.words != null,
        onDismiss = work::dismissWords,
        paneTitle = localizer.text("feedback.whatsOff"),
        detents = listOf(SheetDetent.Fitted, SheetDetent.Large),
        onClosed = gone,
        detent = if (work.large) SheetDetent.Large else SheetDetent.Fitted,
        onDetentChange = { work.large = it == SheetDetent.Large },
        dismissible = !work.sending,
    ) {
        WordsSheet(work, writing)
    }
    Sheet(
        visible = work.namingCarrier,
        onDismiss = work::dismissCarrier,
        paneTitle = localizer.text("feedback.unknown.title"),
        detents = listOf(SheetDetent.Fitted, SheetDetent.Large),
        onClosed = gone,
        detent = if (work.large) SheetDetent.Large else SheetDetent.Fitted,
        onDetentChange = { work.large = it == SheetDetent.Large },
        dismissible = !work.sending,
    ) {
        CarrierSheet(work, writing)
    }
}

/**
 * `.askingForReview(due: feedback.reviewDue, ready: reviewReady) { feedback.takeReviewMoment() }`: a "Yes" the service
 * took on a parcel with nothing wrong asks for a rating once the moment has settled (`ReviewPrompt.settle`), while the
 * page is in front of the reader (`reviewReady`): the window on the screen, nothing over the page or the list, nothing
 * said at the page's foot, no delivery email offered under its card, and the app settled. The sample asks nothing.
 * Play's prompt shows over the window's activity.
 */
@Composable
internal fun PageReviewing(state: DeliveriesState, work: ParcelFeedbackWork, parcel: Parcel?, pageId: UUID) {
    val source = state.source
    val settled by source.reviewSettled.collectAsState()
    val deliveryEmail by source.deliveryEmail.collectAsState()
    val resumed = LocalLifecycleOwner.current.lifecycle.currentStateAsState().value.isAtLeast(Lifecycle.State.RESUMED)
    val ready = parcel != null && state.sample(pageId) == null && resumed && !state.modal && !state.showingFilters &&
        work.word == null && !state.offersEmail(parcel, pageId, deliveryEmail) && settled
    val activity = LocalActivity.current
    val requester = remember(source, activity) { activity?.let(source::reviews) }
    LaunchedEffect(work.reviewDue, ready, requester) {
        if (requester == null) return@LaunchedEffect
        ReviewPrompt.ask(due = work.reviewDue, ready = ready, take = work::takeReviewMoment, requester = requester, report = source::action)
    }
}

/**
 * What TalkBack hears of the question (`AccessibilityNotification.Announcement`): each word as it comes; the thanks
 * said in place once an answer is given, where no word says it; and each failure in a sheet. Not again in the window
 * made again.
 */
@Composable
private fun FeedbackNews(state: DeliveriesState, work: ParcelFeedbackWork) {
    val localizer by rememberUpdatedState(LocalLocalizer.current)
    LaunchedEffect(work) {
        snapshotFlow { work.word }.drop(1).collect { word -> if (word != null) state.announce(localizer.text(word)) }
    }
    LaunchedEffect(work) {
        snapshotFlow { work.moment }.drop(1).collect { moment ->
            // An answer given on the way back is said by its own word.
            if (work.word == null) moment.thanksKey?.let { state.announce(localizer.text(it)) }
        }
    }
    LaunchedEffect(work) {
        snapshotFlow { work.failures }.drop(1).collect { state.announce(localizer.text("feedback.failed")) }
    }
}

/**
 * The word at the page's foot (`word`). It comes and goes as the iPhone's `if let word` does, and one word giving way
 * to another fades into it in place on the same spring, as the switch inside it does (`.opacity`, under the overlay's
 * `.animation(_:value: model.word)`); under Reduce Motion, each fades over 0.2 s.
 */
@Composable
private fun FeedbackWord(work: ParcelFeedbackWork, backdrop: MaterialBackdrop?, modifier: Modifier) {
    val reduceMotion = PeekTheme.reduceMotion
    AnimatedContent(
        work.word,
        modifier,
        transitionSpec = {
            if (reduceMotion) {
                ContentTransform(fadeIn(Motion.easeInOut(0.2)), fadeOut(Motion.easeInOut(0.2)), sizeTransform = null)
            } else {
                val fade = Motion.springWithResponse<Float>(0.4, 0.86)
                val move = Motion.springWithResponse<IntOffset>(0.4, 0.86)
                ContentTransform(
                    slideInVertically(move) { it } + fadeIn(fade), slideOutVertically(move) { it } + fadeOut(fade), sizeTransform = null,
                )
            }
        },
        contentAlignment = Alignment.BottomCenter,
        // Whether there is a word moves; which word it is only fades.
        contentKey = { it != null },
        label = "feedback word",
    ) { word ->
        if (word != null) Staying { WordsInPlace(work, word, backdrop) }
    }
}

/** One word fading into the next where it stands, on the word's spring, or over 0.2 s under Reduce Motion. */
@Composable
private fun WordsInPlace(work: ParcelFeedbackWork, word: Word, backdrop: MaterialBackdrop?) {
    val reduceMotion = PeekTheme.reduceMotion
    AnimatedContent(
        word,
        transitionSpec = {
            val fade: FiniteAnimationSpec<Float> = if (reduceMotion) Motion.easeInOut(0.2) else Motion.springWithResponse(0.4, 0.86)
            ContentTransform(fadeIn(fade), fadeOut(fade), sizeTransform = null)
        },
        contentAlignment = Alignment.BottomCenter,
        label = "feedback words",
    ) { Staying { WordToast(work, it, backdrop) } }
}

/** A word: asked with Pip, 34 wide and 2 under the toast's foot; or its thanks with a tick in green; or a warning. */
@Composable
private fun WordToast(work: ParcelFeedbackWork, word: Word, backdrop: MaterialBackdrop?) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val text = localizer.text(word)
    val place = Modifier
        .fillMaxWidth()
        .wrapContentWidth()
        .widthIn(max = TOAST_WIDTH)
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        .testTag(FEEDBACK_WORD)
    when (word) {
        is Word.Back -> InlineToast(
            text,
            listOf(
                ToastAnswer(localizer.text("feedback.yes"), FEEDBACK_BACK_YES) { work.right(ParcelFeedbackRequestAsked.Back) },
                ToastAnswer(localizer.text("feedback.no"), FEEDBACK_BACK_NO) { work.wrongOnReturn() },
            ),
            backdrop, place,
        ) {
            SmallPip(Modifier.width(ASKER_WIDTH).overhang(bottom = 2.dp), blinks = true)
        }
        Word.BackRight, Word.BackSent -> InlineToast(
            text, button = null, symbol = "checkmark.circle.fill", tint = colors.resolve(ExperimentalPalette.delivered), action = null,
            backdrop = backdrop, modifier = place,
        )
        Word.Failed -> InlineToast(
            text, button = null, symbol = "exclamationmark.triangle.fill", tint = colors.warning, action = null, backdrop = backdrop, modifier = place,
        )
    }
}

/**
 * ParcelFeedbackWordsSheet (B-S61): the reasons as chips, several at once, each toggled with the selection tap and
 * waiting while the words go; the note, two to six lines, the characters it has left said under it once 200 or fewer;
 * why the last send failed; Send, off while there is nothing to send; and what an answer carries, with the carrier's
 * name.
 */
@Composable
private fun WordsSheet(work: ParcelFeedbackWork, writing: () -> Unit) {
    val localizer = LocalLocalizer.current
    val haptics = rememberHaptics()
    FeedbackSheet(localizer.text("feedback.whatsOff"), FEEDBACK_WORDS_SHEET, work.sending, work::dismissWords) {
        FeedbackFlow {
            for (reason in ParcelFeedbackReason.entries) {
                ReasonChip(localizer.text(reason.labelKey), FEEDBACK_CHIP + reason.name, on = reason in work.reasons, enabled = !work.sending) {
                    work.toggle(reason)
                    haptics.selection()
                }
            }
        }
        val label = localizer.text("feedback.note")
        FeedbackField(label) { focused ->
            BasicTextField(
                work.note,
                work::typeNote,
                Modifier.feedbackField(FEEDBACK_NOTE, label, focused, writing),
                textStyle = fieldStyle(),
                minLines = 2,
                maxLines = 6,
                cursorBrush = SolidColor(PeekTheme.colors.ink),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                decorationBox = { FieldFrame(focused.value, it) },
            )
        }
        ParcelFeedbackWordsSheet.noteLeftText(work.note, localizer)?.let {
            Text(
                it,
                Modifier.fillMaxWidth().padding(horizontal = 2.dp).testTag(FEEDBACK_NOTE_LEFT),
                color = PeekTheme.colors.secondaryLabel,
                style = PeekTheme.type.caption.sfLines(),
                textAlign = TextAlign.End,
            )
        }
        if (work.failed) FeedbackFailure()
        SendButton(busy = work.sending, disabled = work.wordsEmpty, work::sendWords)
        Promise(localizer.text("feedback.promise", mapOf("carrier" to (work.page?.carrier ?: ""))))
    }
}

/**
 * ParcelFeedbackCarrierSheet (B-S62): who carries the parcel, without autocorrection or suggestions ([NoSuggestions]),
 * whose Next goes to its page; its page as its reader has it, on the address keyboard without capitals, autocorrection
 * or suggestions, whose Send sends where there is something to; why the last send failed; Send; and what an answer
 * carries.
 */
@Composable
private fun CarrierSheet(work: ParcelFeedbackWork, writing: () -> Unit) {
    val localizer = LocalLocalizer.current
    val next = remember { FocusRequester() }
    FeedbackSheet(localizer.text("feedback.unknown.title"), FEEDBACK_CARRIER_SHEET, work.sending, work::dismissCarrier) {
        val whoLabel = localizer.text("feedback.unknown.who")
        FeedbackField(whoLabel) { focused ->
            NoSuggestions {
                BasicTextField(
                    work.who,
                    work::typeWho,
                    Modifier.feedbackField(FEEDBACK_WHO, whoLabel, focused, writing),
                    textStyle = fieldStyle(),
                    singleLine = true,
                    cursorBrush = SolidColor(PeekTheme.colors.ink),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, autoCorrectEnabled = false, imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = { next.requestFocus() }),
                    decorationBox = { FieldFrame(focused.value, it) },
                )
            }
        }
        val whereLabel = localizer.text("feedback.unknown.where")
        FeedbackField(whereLabel) { focused ->
            // An address as its reader has it, with or without its scheme: a person reads it, nothing opens it.
            NoSuggestions {
                BasicTextField(
                    work.address,
                    work::typeAddress,
                    Modifier.feedbackField(FEEDBACK_WHERE, whereLabel, focused, writing).focusRequester(next),
                    textStyle = fieldStyle(),
                    singleLine = true,
                    cursorBrush = SolidColor(PeekTheme.colors.ink),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None, autoCorrectEnabled = false, keyboardType = KeyboardType.Uri, imeAction = ImeAction.Send,
                    ),
                    keyboardActions = KeyboardActions(onSend = { if (!work.carrierEmpty) work.sendCarrier() }),
                    decorationBox = { FieldFrame(focused.value, it) },
                )
            }
        }
        if (work.failed) FeedbackFailure()
        SendButton(busy = work.sending, disabled = work.carrierEmpty, work::sendCarrier)
        Promise(localizer.text("feedback.unknown.promise"))
    }
}

/**
 * `FeedbackSheet`: the sheet's body on Brand's background, 22 in from the sides, 26 under the top and 18 over the foot
 * and the navigation bar under a sheet from the window's bottom, 560 wide at most and 14 between its parts: the title
 * in title2 semibold, a heading, across what the close button leaves it, 16 from it; then what the sheet asks. ✕ is 13
 * semibold in a circle of the ink at 7 %, 36 across in 44, named "common.close", off and faded while the words go. It
 * scrolls clear of the keyboard, and a drag down takes the keyboard down ([KeyboardScrollColumn]).
 */
@Composable
private fun FeedbackSheet(title: String, tag: String, busy: Boolean, close: () -> Unit, content: @Composable () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val bottom = sheetBottomInset()
    val closeLabel = LocalLocalizer.current.text("common.close")
    KeyboardScrollColumn(Modifier.fillMaxSize().background(colors.background), bottom = bottom, dismissesKeyboard = true) {
        Column(
            Modifier
                .testTag(tag)
                .fillMaxWidth()
                .wrapContentWidth()
                .widthIn(max = SHEET_WIDTH)
                .fillMaxWidth()
                .padding(start = SHEET_SIDE, top = SHEET_TOP, end = SHEET_SIDE, bottom = SHEET_BOTTOM + bottom),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    title,
                    Modifier.weight(1f).semantics { heading() },
                    color = colors.label,
                    style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(),
                )
                Box(
                    Modifier
                        .size(44.dp)
                        .testTag(FEEDBACK_CLOSE)
                        .clickable(interactionSource = null, indication = PlainPress, enabled = !busy, role = Role.Button, onClick = close)
                        .plainDisabled(!busy)
                        .semantics { contentDescription = closeLabel },
                    contentAlignment = Alignment.Center,
                ) {
                    Box(Modifier.size(36.dp).background(colors.ink.copy(alpha = colors.ink.alpha * 0.07f), CircleShape), contentAlignment = Alignment.Center) {
                        PeekSymbol("xmark", font = type.fixed(13f, FontWeight.SemiBold), tint = colors.label)
                    }
                }
            }
            content()
        }
    }
}

/**
 * `chip(_:)`: a reason in footnote medium, 14 and 10 in, 44 tall at least, with 14-point continuous corners: on paper
 * with a hairline of the separator at 60 %, or chosen on ochre's surface with a stroke of ochre 1.5 wide. It gives
 * under the finger at 0.97, and TalkBack hears it selected once chosen.
 */
@Composable
private fun ReasonChip(title: String, tag: String, on: Boolean, enabled: Boolean, toggle: () -> Unit) {
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(14.dp)
    Box(
        Modifier
            .testTag(tag)
            .clickable(interactionSource = null, indication = PressStyle.tactile(0.97f), enabled = enabled, role = Role.Button, onClick = toggle)
            .semantics { selected = on }
            .heightIn(min = 44.dp)
            .background(if (on) colors.resolve(ExperimentalPalette.ochreSurface) else colors.paper, shape)
            .border(
                if (on) 1.5.dp else 1.dp,
                if (on) colors.resolve(ExperimentalPalette.ochre) else colors.separator.copy(alpha = colors.separator.alpha * 0.6f),
                shape,
            )
            .padding(horizontal = 14.dp, vertical = 10.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(title, color = colors.ink, style = PeekTheme.type.style(IosTextStyle.Footnote, FontWeight.Medium).sfLines())
    }
}

/**
 * `FeedbackField`: its label in caption secondary, 2 in, which TalkBack leaves for the field it names, 6 over the field
 * ([FieldFrame]). `input` is given whether the field has the focus.
 */
@Composable
private fun FeedbackField(label: String, input: @Composable (MutableState<Boolean>) -> Unit) {
    val focused = remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            label,
            Modifier.padding(horizontal = 2.dp).clearAndSetSemantics {},
            color = PeekTheme.colors.secondaryLabel,
            style = PeekTheme.type.caption.sfLines(),
        )
        input(focused)
    }
}

/**
 * A field's own: named for TalkBack by its label, keeping the focus and the keyboard through the window made again,
 * and opening the sheet fully as it takes the focus (`onChange(of: writing)`).
 */
@Composable
private fun Modifier.feedbackField(tag: String, label: String, focused: MutableState<Boolean>, writing: () -> Unit): Modifier =
    fillMaxWidth()
        .testTag(tag)
        .keptFocus()
        .onFocusChanged {
            focused.value = it.isFocused
            if (it.isFocused) writing()
        }
        .semantics { contentDescription = label }

@Composable
private fun fieldStyle() = PeekTheme.type.callout.sfLines().copy(color = PeekTheme.colors.label)

/**
 * The field's frame: what is typed in callout, 14 and 12 in, 50 tall at least, on the paper with 14-point continuous
 * corners and a hairline of the separator at 60 %, or a stroke of ochre 1.5 wide while it has the focus. A tap anywhere
 * in it starts writing.
 */
@Composable
private fun FieldFrame(focused: Boolean, text: @Composable () -> Unit) {
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(14.dp)
    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp)
            .background(colors.paper, shape)
            .border(
                if (focused) 1.5.dp else 1.dp,
                if (focused) colors.resolve(ExperimentalPalette.ochre) else colors.separator.copy(alpha = colors.separator.alpha * 0.6f),
                shape,
            )
            .padding(horizontal = 14.dp, vertical = 12.dp),
        contentAlignment = Alignment.CenterStart,
    ) { text() }
}

/**
 * `FeedbackSendButton`: "feedback.send", or a spinner 9 before "feedback.sending" while the words go, in headline on the
 * accent across the sheet, 20 and 13 in, 50 tall at least with 16-point continuous corners, giving under the finger;
 * off while sending or with nothing to send, and then at 45 %. TalkBack hears it in progress meanwhile.
 */
@Composable
private fun SendButton(busy: Boolean, disabled: Boolean, action: () -> Unit) {
    val colors = PeekTheme.colors
    val localizer = LocalLocalizer.current
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(FEEDBACK_SEND)
            .graphicsLayer { alpha = if (disabled && !busy) 0.45f else 1f }
            .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = !busy && !disabled, role = Role.Button, onClick = action)
            .semantics { if (busy) progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate }
            .background(colors.accent, ContinuousRoundedShape(16.dp))
            .heightIn(min = 50.dp)
            .padding(horizontal = 20.dp, vertical = 13.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (busy) ActivityIndicator(Modifier.clearAndSetSemantics {}, size = REGULAR_SPINNER, tint = colors.onAccent)
        Text(
            localizer.text(if (busy) "feedback.sending" else "feedback.send"),
            Modifier.weight(1f, fill = false),
            color = colors.onAccent,
            style = PeekTheme.type.headline.sfLines(),
            textAlign = TextAlign.Center,
        )
    }
}

/** `FeedbackFailure`: exclamationmark.circle 8 before "feedback.failed" in footnote, in the warning colour, 12 in on its soft colour at 60 % with 12-point corners. */
@Composable
private fun FeedbackFailure() {
    val colors = PeekTheme.colors
    val style = PeekTheme.type.footnote
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(FEEDBACK_FAILURE)
            .background(colors.warningSoft.copy(alpha = colors.warningSoft.alpha * 0.6f), ContinuousRoundedShape(12.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PeekSymbol("exclamationmark.circle", Modifier.alignByBaseline().clearAndSetSemantics {}, font = style, tint = colors.warning)
        Text(LocalLocalizer.current.text("feedback.failed"), Modifier.weight(1f).alignByBaseline(), color = colors.warning, style = style.sfLines())
    }
}

/** `FeedbackPromise`: what an answer carries, in caption secondary, centred under the button that sends it. */
@Composable
private fun Promise(text: String) {
    Text(
        text,
        Modifier.fillMaxWidth().testTag(FEEDBACK_PROMISE),
        color = PeekTheme.colors.secondaryLabel,
        style = PeekTheme.type.caption.sfLines(),
        textAlign = TextAlign.Center,
    )
}

/** The moment's settle, or `easeInOut(duration: 0.2)` under Reduce Motion. */
private fun <T> settle(reduceMotion: Boolean): FiniteAnimationSpec<T> = if (reduceMotion) Motion.easeInOut(0.2) else Motion.springWithResponse(0.42, 0.86)

/** The moments Pip smiles at. */
private val HAPPY = setOf(Moment.Right, Moment.Sent, Moment.Noted)

/** `spring(response: 0.42, dampingFraction: 0.86)`, for the question's size and what folds away. */
private val SETTLE = Motion.springWithResponse<IntSize>(0.42, 0.86)

/** How much of the question shows in the page's scroll as it comes in. */
private const val ARRIVAL = 0.2f
private val ARRIVAL_DROP = 10.dp
private val TURN_RISE = 6.dp
private val PIP_WIDTH = 50.dp
private val ASKER_WIDTH = 34.dp
private val TICK_SIDE = 16.dp
private val BESIDE_GAP = 12.dp
private val BESIDE_SPACING = 8.dp
private val FLOW_SPACING = 6.dp
private val UNDERLINE_GAP = 2.dp
private val TOAST_WIDTH = 560.dp
private val SHEET_WIDTH = 560.dp
private val SHEET_SIDE = 22.dp
private val SHEET_TOP = 26.dp
private val SHEET_BOTTOM = 18.dp
private val BUBBLE_SHADOW = swiftUiShadow(Color.Black.copy(alpha = 0.04f), radius = 1.dp, y = 1.dp)

/** The test tags of the question, its word and its sheets. */
const val FEEDBACK_QUESTION = "parcelFeedback.question"
const val FEEDBACK_PIP = "parcelFeedback.pip"
const val FEEDBACK_BUBBLE = "parcelFeedback.bubble"
const val FEEDBACK_ROW = "parcelFeedback.row"
const val FEEDBACK_YES = "parcelFeedback.yes"
const val FEEDBACK_NOT_QUITE = "parcelFeedback.notQuite"
const val FEEDBACK_NOT_YET = "parcelFeedback.notYet"
const val FEEDBACK_REASON = "parcelFeedback.reason."
const val FEEDBACK_ADD_NOTE = "parcelFeedback.addNote"
const val FEEDBACK_WORD = "parcelFeedback.word"
const val FEEDBACK_BACK_YES = "parcelFeedback.back.yes"
const val FEEDBACK_BACK_NO = "parcelFeedback.back.no"
const val FEEDBACK_WORDS_SHEET = "parcelFeedback.wordsSheet"
const val FEEDBACK_CARRIER_SHEET = "parcelFeedback.carrierSheet"
const val FEEDBACK_CLOSE = "parcelFeedback.close"
const val FEEDBACK_CHIP = "parcelFeedback.chip."
const val FEEDBACK_NOTE = "parcelFeedback.note"
const val FEEDBACK_NOTE_LEFT = "parcelFeedback.noteLeft"
const val FEEDBACK_WHO = "parcelFeedback.who"
const val FEEDBACK_WHERE = "parcelFeedback.where"
const val FEEDBACK_SEND = "parcelFeedback.send"
const val FEEDBACK_FAILURE = "parcelFeedback.failure"
const val FEEDBACK_PROMISE = "parcelFeedback.promise"
