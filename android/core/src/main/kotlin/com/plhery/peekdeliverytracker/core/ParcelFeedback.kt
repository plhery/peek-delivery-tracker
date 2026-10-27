package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackAnswer
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequest
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestApp
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequestAsked
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import java.security.MessageDigest
import java.time.Instant
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer

// ParcelFeedback.swift. A page sends its answers through `ParcelFeedbackClient`, which the app makes of its API client,
// the device's parcels or the demo (`ParcelStore.feedbackClient(for:)` on iOS). An answer names the app it comes from,
// which the app supplies (`ParcelFeedbackModel.app`): the iPhone's say `ios`.

/**
 * What a parcel's page asks its reader. For a parcel a carrier answers for, Pip asks where the history ends whether he
 * got it right, and the page asks once more on the way back from the carrier's own site. For a parcel no carrier was
 * found for, a row asks whether the carrier's site shows it.
 */
enum class ParcelFeedbackQuestion {
    Found, Unknown;

    companion object {
        /**
         * Swift's failable `init?(parcel:showsHistory:)`. Nil asks nothing: the parcel is archived, still waits for its
         * first answer, or has a carrier whose history the page does not show.
         */
        operator fun invoke(parcel: Parcel, showsHistory: Boolean): ParcelFeedbackQuestion? {
            if (parcel.isArchived) return null
            if (!parcel.hasCarrierUpdate) {
                // Asked about for the first time: there is nothing to judge yet.
                if (parcel.syncStatus == SyncStatus.Pending || parcel.syncStatus == SyncStatus.Syncing) return null
                if (parcel.carrier == CarrierId.Unknown) return Unknown
            }
            if (!showsHistory) return null
            return Found
        }
    }
}

/** What the page ends on: its newest scan, and how many it has. Empty before the first one. */
val Parcel.feedbackScan: String get() = currentEvent?.let { "${trackingEvents.size}:${it.occurredAt}" } ?: ""

/** The parcel a question is about, as this phone remembers it. */
sealed class ParcelFeedbackSubject {
    /** One of the account's parcels, or one this phone follows itself. */
    data class Parcel(val id: UUID) : ParcelFeedbackSubject()

    /** A parcel opened through someone's link. Ids compare as Swift's Strings do: canonically equivalent ones are one. */
    data class Link(val id: String) : ParcelFeedbackSubject() {
        override fun equals(other: Any?): Boolean = other is Link && id.equalsCanonically(other.id)
        override fun hashCode(): Int = id.canonicalHashCode()
    }

    /** A link's id opens its parcel, so only its digest is kept: SHA-256 of its UTF-8, in lower-case hex. */
    val memoryKey: String
        get() = when (this) {
            is Parcel -> id.uuidString
            is Link -> MessageDigest.getInstance("SHA-256").digest(id.toByteArray(Charsets.UTF_8)).toHex()
        }

    private companion object {
        const val hexDigits = "0123456789abcdef"

        fun ByteArray.toHex(): String {
            val hex = StringBuilder(size * 2)
            for (byte in this) {
                val value = byte.toInt() and 0xFF
                hex.append(hexDigits[value shr 4]).append(hexDigits[value and 0x0F])
            }
            return hex.toString()
        }
    }
}

/**
 * What this phone remembers of the question, one entry per parcel: when its reader last answered and about which
 * scan, and the scan the way back from a carrier's site last asked about. The question on the page stands on every
 * visit; the way back asks about a scan once, and about a parcel at most once a day. Nothing of it leaves the device.
 */
class ParcelFeedbackMemory(private val defaults: UserDefaults, private val now: () -> Instant = Instant::now) {
    /** `answeredAt` and `savedAt` are Swift's `Date` in JSON: seconds since 2001. */
    @Serializable
    private data class Entry(
        val answeredAt: Double? = null,
        /** What the page ended on when its reader answered. */
        val scan: String? = null,
        /** The scan the question was asked about on the way back. */
        val back: String? = null,
        val savedAt: Double,
    )

    /**
     * Whether the way back may ask about this scan: once, not about a scan its reader answered for, and not within a
     * day of an answer. Only another scan and a day together bring it back.
     */
    fun asksOnReturn(subject: ParcelFeedbackSubject, scan: String): Boolean {
        val entry = read()[subject.memoryKey] ?: return true
        if (entry.back?.equalsCanonically(scan) == true) return false
        val answeredAt = entry.answeredAt ?: return true
        if (entry.scan?.equalsCanonically(scan) == true) return false
        // A clock set back must not keep a new scan from being asked about.
        val age = now().timeIntervalSinceReferenceDate - answeredAt
        return age < 0 || age >= quiet
    }

    /** An answer about what the page ends on. */
    fun rememberAnswer(subject: ParcelFeedbackSubject, scan: String) {
        update(subject) { it.copy(answeredAt = now().timeIntervalSinceReferenceDate, scan = scan) }
    }

    fun rememberAskedOnReturn(subject: ParcelFeedbackSubject, scan: String) {
        update(subject) { it.copy(back = scan) }
    }

    fun forgetAll() {
        defaults.removeObject(forKey = key)
    }

    private fun update(subject: ParcelFeedbackSubject, change: (Entry) -> Entry) {
        val entries = read().toMutableMap()
        val entry = change(entries[subject.memoryKey] ?: Entry(savedAt = now().timeIntervalSinceReferenceDate))
        entries[subject.memoryKey] = entry.copy(savedAt = now().timeIntervalSinceReferenceDate)
        val kept = entries.entries.sortedByDescending { it.value.savedAt }.take(limit)
        defaults.set(JsonEncoder().encode(format, kept.associate { it.key to it.value }), forKey = key)
    }

    private fun read(): Map<String, Entry> {
        val data = defaults.data(forKey = key) ?: return emptyMap()
        return runCatching { ApiJson.decodeFromString(format, data.decodeToString()) }.getOrNull() ?: emptyMap()
    }

    companion object {
        private const val key = "sdt.parcelFeedback.v1"

        /** Only the latest parcels can still matter. */
        const val limit = 200

        /** How long an answer keeps the question on the way back away, in seconds. */
        const val quiet: Double = 24 * 60 * 60.0

        private val format = MapSerializer(String.serializer(), Entry.serializer())
    }
}

val ParcelFeedbackRequest.Companion.noteLimit: Int get() = 4_000

/** A note this close to its limit shows how many characters are left, so nothing is cut off unseen. */
val ParcelFeedbackRequest.Companion.noteWarning: Int get() = 200
val ParcelFeedbackRequest.Companion.carrierNameLimit: Int get() = 120
val ParcelFeedbackRequest.Companion.trackingPageLimit: Int get() = 500

/** What is shown is right. It comes alone. */
fun ParcelFeedbackRequest.Companion.right(
    id: UUID = UUID.randomUUID(),
    asked: ParcelFeedbackRequestAsked,
    language: AppLanguage,
    app: ParcelFeedbackRequestApp,
): ParcelFeedbackRequest =
    ParcelFeedbackRequest(id = id, answer = ParcelFeedbackAnswer.Right, asked = asked, app = app, locale = locale(language))

/** What is off: reasons in the order the page offers them, a note, or both. */
fun ParcelFeedbackRequest.Companion.wrong(
    id: UUID = UUID.randomUUID(),
    reasons: Set<ParcelFeedbackReason>,
    note: String = "",
    asked: ParcelFeedbackRequestAsked,
    language: AppLanguage,
    app: ParcelFeedbackRequestApp,
): ParcelFeedbackRequest {
    val ordered = ParcelFeedbackReason.entries.filter { it in reasons }
    return ParcelFeedbackRequest(
        id = id, answer = ParcelFeedbackAnswer.Wrong, reasons = ordered.ifEmpty { null }, note = words(note, limit = noteLimit),
        asked = asked, app = app, locale = locale(language),
    )
}

/** Who carries a parcel no carrier was found for: a name, its tracking page as typed, or both. */
fun ParcelFeedbackRequest.Companion.foundElsewhere(
    id: UUID = UUID.randomUUID(),
    carrierName: String,
    trackingPage: String,
    language: AppLanguage,
    app: ParcelFeedbackRequestApp,
): ParcelFeedbackRequest = ParcelFeedbackRequest(
    id = id, answer = ParcelFeedbackAnswer.FoundElsewhere, carrierName = words(carrierName, limit = carrierNameLimit),
    trackingPage = words(trackingPage, limit = trackingPageLimit), asked = ParcelFeedbackRequestAsked.Page, app = app,
    locale = locale(language),
)

/** Typed words as an answer carries them: without the space around them, within their limit, nil when nothing is left. */
fun ParcelFeedbackRequest.Companion.words(text: String, limit: Int): String? =
    limited(text.trimmingWhitespacesAndNewlines(), to = limit).nonEmpty

/** What is being typed, kept within what an answer may carry: whole Characters while their Unicode scalars fit. */
fun ParcelFeedbackRequest.Companion.limited(text: String, to: Int): String {
    if (text.codePointCount(0, text.length) <= to) return text
    val kept = StringBuilder()
    var used = 0
    for (character in text.characters()) {
        used += character.codePointCount(0, character.length)
        if (used > to) break
        kept.append(character)
    }
    return kept.toString()
}

private fun locale(language: AppLanguage): NativePushLocale = NativePushLocale.fromRawValue(language.rawValue) ?: NativePushLocale.En

/**
 * How a page sends an answer: to the service as the account, through the parcel's link, or nowhere in the demo. It
 * throws when the answer could not be sent: a network failure, a timeout (an `IOException`, or the
 * `TimeoutCancellationException` of a `withTimeout`) or the service's refusal, each a failure as on iOS.
 */
class ParcelFeedbackClient(val send: suspend (ParcelFeedbackRequest) -> Unit)

/**
 * The question on one page: what stands where it is asked, the sheets that take the words, and the word said at the
 * bottom of the page. An answer shows at once, and is taken back if it could not be sent.
 *
 * Used from the main thread, as the iPhone's model is `@MainActor`. An answer runs in `scope`, as on iOS in a button's
 * `Task`, which nothing cancels: leaving the page, which cancels the screen's call, neither cuts it short nor makes it
 * a failure.
 */
class ParcelFeedbackModel(
    /**
     * The app's own scope, which outlives every screen, on the main thread and with a `SupervisorJob`, so that one
     * answer's failure leaves the others alone: `ProcessLifecycleOwner`'s `lifecycleScope`, or a `MainScope()` the
     * application keeps.
     */
    private val scope: CoroutineScope,
    private val memory: ParcelFeedbackMemory,
    private val now: () -> Instant = Instant::now,
    /** `DeliveryAnalytics.action`: an answer counted under its name, with how it went. */
    private val report: (String, DeliveryAnalytics.Outcome) -> Unit,
    private val review: ReviewPrompt,
    /**
     * The app an answer says it comes from, as the service counts it. The iPhone's say `ios` and the website's `web`,
     * the only two the contract accepts so far. Android's must say `android` once the contract accepts it (a planned
     * change to the service), and never `ios` or `web`.
     */
    private val app: ParcelFeedbackRequestApp,
) {
    /** What stands where the question is asked. */
    enum class Moment {
        Ask,

        /** "Not quite": the reasons are offered, nothing is sent yet. */
        Reasons,
        Right,

        /** A reason was given in place; a note may follow it. */
        Sent,

        /** The words were sent: there is nothing to add. */
        Noted,

        /** "Not yet": the carrier's own site does not show the parcel either. */
        Early,

        /** The reader named the carrier. */
        Named,
    }

    /** What a page shows of its parcel, as far as the question goes. Its words compare as Swift's Strings do. */
    data class Page(
        val subject: ParcelFeedbackSubject,
        val question: ParcelFeedbackQuestion,
        /** What the page ends on (`Parcel.feedbackScan`). */
        val scan: String,
        /** The carrier's name, for the line that says what an answer carries. */
        val carrier: String,
        /** The language the page speaks, which an answer says too. */
        val language: AppLanguage,
        /** Nothing about the parcel is wrong (`Parcel.untroubled`): a "Yes" may be the moment to ask for a rating. */
        val untroubled: Boolean = false,
    ) {
        override fun equals(other: Any?): Boolean =
            other is Page && subject == other.subject && question == other.question && scan.equalsCanonically(other.scan) &&
                carrier.equalsCanonically(other.carrier) && language == other.language && untroubled == other.untroubled

        override fun hashCode(): Int =
            listOf(subject, question, scan.canonicalHashCode(), carrier.canonicalHashCode(), language, untroubled).hashCode()

        companion object {
            /**
             * Swift's failable `init?(parcel:subject:showsHistory:madeUp:carrier:language:)`: what a parcel's page asks
             * about, or nil while it asks nothing. A made-up parcel, as the demo tells its stories with, is not
             * something to judge.
             */
            operator fun invoke(
                parcel: Parcel,
                subject: ParcelFeedbackSubject,
                showsHistory: Boolean,
                madeUp: Boolean,
                carrier: String,
                language: AppLanguage,
            ): Page? {
                if (madeUp) return null
                val question = ParcelFeedbackQuestion(parcel, showsHistory) ?: return null
                return Page(subject, question, parcel.feedbackScan, carrier, language, untroubled = parcel.untroubled())
            }
        }
    }

    /** The sheet that takes what is off. */
    data class Words(
        /** The answer's id: a note added to an answer already sent goes under the same one. */
        val id: UUID,
        val reasons: Set<ParcelFeedbackReason>,
        val asked: ParcelFeedbackRequestAsked,
    )

    /** A word at the bottom of the page. */
    sealed class Word {
        /** Asked on the way back from a carrier's own site, named as its link names it. */
        data class Back(val site: String) : Word() {
            override fun equals(other: Any?): Boolean = other is Back && site.equalsCanonically(other.site)
            override fun hashCode(): Int = site.canonicalHashCode()
        }

        data object BackRight : Word()

        data object BackSent : Word()

        data object Failed : Word()

        /** The question waits this long for an answer; a word of thanks stays this long. */
        val stay: Duration get() = if (this is Back) 12.seconds else 4.seconds
    }

    /** The reader's leave for a carrier's own page, and when the app left the screen for it. */
    private data class Visit(val site: String, val left: Instant?)

    private val pageState = MutableStateFlow<Page?>(null)
    private val momentState = MutableStateFlow(Moment.Ask)
    private val wordsState = MutableStateFlow<Words?>(null)
    private val namingCarrierState = MutableStateFlow(false)
    private val wordState = MutableStateFlow<Word?>(null)
    private val sendingState = MutableStateFlow(false)
    private val reviewDueState = MutableStateFlow(false)

    val pageFlow: StateFlow<Page?> = pageState.asStateFlow()
    val momentFlow: StateFlow<Moment> = momentState.asStateFlow()
    val wordsFlow: StateFlow<Words?> = wordsState.asStateFlow()
    val namingCarrierFlow: StateFlow<Boolean> = namingCarrierState.asStateFlow()
    val wordFlow: StateFlow<Word?> = wordState.asStateFlow()
    val sendingFlow: StateFlow<Boolean> = sendingState.asStateFlow()
    val reviewDueFlow: StateFlow<Boolean> = reviewDueState.asStateFlow()

    var page: Page?
        get() = pageState.value
        private set(value) {
            pageState.value = value
        }

    var moment: Moment
        get() = momentState.value
        private set(value) {
            momentState.value = value
        }

    /** The open sheet that takes what is off; the sheet sets it to nil as it closes. */
    var words: Words?
        get() = wordsState.value
        set(value) {
            wordsState.value = value
        }

    /** The sheet that asks who carries the parcel is open. */
    var namingCarrier: Boolean
        get() = namingCarrierState.value
        set(value) {
            namingCarrierState.value = value
        }

    var word: Word?
        get() = wordState.value
        private set(value) {
            wordState.value = value
        }

    var sending: Boolean
        get() = sendingState.value
        private set(value) {
            sendingState.value = value
        }

    /** A "Yes" the service took was the moment to ask for a rating (`ReviewPrompt`), and the page has not asked yet. */
    var reviewDue: Boolean
        get() = reviewDueState.value
        private set(value) {
            reviewDueState.value = value
        }

    private var client: ParcelFeedbackClient? = null

    /** The answer whose reason was given in place, for the note that may follow it. */
    private var given: Words? = null
    private var visit: Visit? = null

    /**
     * The question standing on the page, or nil while there is none to ask. It stands on every visit: something may
     * happen that its reader tells only later.
     */
    val standing: ParcelFeedbackQuestion? get() = page?.question

    /** Takes what the page shows now. Another parcel, or a carrier found while the page was open, starts over. */
    fun show(page: Page?, through: ParcelFeedbackClient?) {
        client = through
        if (page == this.page) return
        val before = this.page
        this.page = page
        if (before?.subject != page?.subject) {
            words = null
            namingCarrier = false
            word = null
            visit = null
            reviewDue = false
        }
        if (before?.subject != page?.subject || before?.question != page?.question) {
            moment = Moment.Ask
            given = null
        }
    }

    // On the way back

    /** The reader opens a carrier's own page, named as its link names it. */
    fun visited(site: String) {
        if (page?.question != ParcelFeedbackQuestion.Found) return
        visit = Visit(site, null)
    }

    /** The app leaves the screen. */
    fun left() {
        val visit = visit ?: return
        if (visit.left != null) return
        this.visit = Visit(visit.site, now())
    }

    /**
     * The app is back: the reader has just seen what the carrier's own site says. While something else is said or
     * asked over the page, the question waits for another visit.
     */
    fun returned(busy: Boolean = false) {
        val visit = visit ?: return
        val left = visit.left ?: return
        this.visit = null
        val page = page ?: return
        if (page.question != ParcelFeedbackQuestion.Found || busy || words != null || namingCarrier ||
            !(moment == Moment.Ask || moment == Moment.Reasons) ||
            now().timeIntervalSince(left) < away ||
            !memory.asksOnReturn(page.subject, scan = page.scan)
        ) {
            return
        }
        // Asked once about a scan, answered or not.
        memory.rememberAskedOnReturn(page.subject, scan = page.scan)
        word = Word.Back(site = visit.site)
    }

    /** A word leaves by itself once it has stayed its time. */
    fun wordLeft(word: Word) {
        if (this.word == word) this.word = null
    }

    // Answers

    /** What is shown is right: said in place, or on the way back. */
    suspend fun right(asked: ParcelFeedbackRequestAsked = ParcelFeedbackRequestAsked.Page) {
        val page = page ?: return
        if (page.question != ParcelFeedbackQuestion.Found) return
        answering {
            val before = moment
            moment = Moment.Right
            word = if (asked == ParcelFeedbackRequestAsked.Back) Word.BackRight else null
            if (!deliver(ParcelFeedbackRequest.right(asked = asked, language = page.language, app = app), page) &&
                this.page?.subject == page.subject
            ) {
                moment = before
                word = Word.Failed
            }
        }
    }

    fun notQuite() {
        if (moment != Moment.Ask) return
        // Something is off: no rating is asked for today.
        review.noteBadMoment(at = now())
        moment = Moment.Reasons
    }

    /** One reason, given in place. */
    suspend fun pick(reason: ParcelFeedbackReason) {
        val page = page ?: return
        if (moment != Moment.Reasons) return
        answering {
            val answer = Words(id = UUID.randomUUID(), reasons = setOf(reason), asked = ParcelFeedbackRequestAsked.Page)
            given = answer
            moment = Moment.Sent
            val feedback = ParcelFeedbackRequest.wrong(
                id = answer.id, reasons = answer.reasons, asked = ParcelFeedbackRequestAsked.Page, language = page.language, app = app,
            )
            if (!deliver(feedback, page) && this.page?.subject == page.subject) {
                given = null
                moment = Moment.Reasons
                word = Word.Failed
            }
        }
    }

    /** More to say about the reason just given. */
    fun addNote() {
        if (moment != Moment.Sent) return
        val given = given ?: return
        words = given
    }

    /** The carrier's own site says something else. */
    fun wrongOnReturn() {
        review.noteBadMoment(at = now())
        word = null
        words = Words(id = UUID.randomUUID(), reasons = emptySet(), asked = ParcelFeedbackRequestAsked.Back)
    }

    /** The carrier's own site shows the parcel: the reader says whose it is. */
    fun elsewhere() {
        review.noteBadMoment(at = now())
        namingCarrier = true
    }

    /** Nothing to send: tomorrow the carrier's site may show it, and the row asks again. */
    fun notYet() {
        if (page?.question != ParcelFeedbackQuestion.Unknown) return
        moment = Moment.Early
    }

    /** Sends what the open sheet holds. False keeps the sheet open with what was typed. */
    suspend fun sendWords(reasons: Set<ParcelFeedbackReason>, note: String): Boolean {
        val page = page ?: return false
        val words = words ?: return false
        if (sending) return false
        return answering {
            sending = true
            try {
                // A note added to the reason given in place is the same answer: it is not counted twice.
                val feedback = ParcelFeedbackRequest.wrong(
                    id = words.id, reasons = reasons, note = note, asked = words.asked, language = page.language, app = app,
                )
                val sent = deliver(feedback, page, counted = words.asked == ParcelFeedbackRequestAsked.Back)
                if (sent && this.page?.subject == page.subject) {
                    this.words = null
                    if (this.page?.question == ParcelFeedbackQuestion.Found) moment = Moment.Noted
                    if (words.asked == ParcelFeedbackRequestAsked.Back) word = Word.BackSent
                }
                sent
            } finally {
                sending = false
            }
        }
    }

    /** Sends who carries the parcel. False keeps the sheet open with what was typed. */
    suspend fun sendCarrier(name: String, page: String): Boolean {
        val address = page
        val shown = this.page ?: return false
        if (sending) return false
        return answering {
            sending = true
            try {
                val feedback = ParcelFeedbackRequest.foundElsewhere(carrierName = name, trackingPage = address, language = shown.language, app = app)
                val sent = deliver(feedback, shown)
                if (sent && this.page?.subject == shown.subject) {
                    namingCarrier = false
                    if (this.page?.question == ParcelFeedbackQuestion.Unknown) moment = Moment.Named
                }
                sent
            } finally {
                sending = false
            }
        }
    }

    /**
     * Runs an answer in the app's scope, at once and on the caller's thread until it first waits, so what it shows at
     * once is shown before the call returns. A caller cancelled meanwhile stops waiting; the answer goes on.
     */
    private suspend fun <T> answering(answer: suspend () -> T): T = scope.async(start = CoroutineStart.UNDISPATCHED) { answer() }.await()

    /**
     * Sends an answer, and remembers it once the service has it. A send that fails counts as a failure, as on iOS: the
     * answer is taken back and the day asks for no rating. Only the send can fail so; the app's scope cancelled, as
     * the app goes away, ends the answer there.
     */
    private suspend fun deliver(feedback: ParcelFeedbackRequest, page: Page, counted: Boolean = true): Boolean {
        val action = when (feedback.answer) {
            ParcelFeedbackAnswer.Right -> "parcel-feedback-right"
            ParcelFeedbackAnswer.Wrong -> "parcel-feedback-wrong"
            ParcelFeedbackAnswer.FoundElsewhere -> "parcel-feedback-carrier"
        }
        try {
            val client = client ?: throw DeliveryApiError.ParcelMissing()
            client.send(feedback)
        } catch (error: Exception) {
            currentCoroutineContext().ensureActive()
            if (counted) report(action, DeliveryAnalytics.Outcome.Error)
            review.noteBadMoment(at = now())
            return false
        }
        if (counted) report(action, DeliveryAnalytics.Outcome.Success)
        memory.rememberAnswer(page.subject, scan = page.scan)
        // The reader found the page right: on a parcel with nothing wrong, the moment to ask for a rating.
        if (feedback.answer == ParcelFeedbackAnswer.Right && review.isDue(at = now(), calm = page.untroubled)) reviewDue = true
        return true
    }

    /**
     * The page asks for a rating now, a moment after the "Yes" that made it due: true once, with nothing said at its
     * foot and the rule still holding for what it shows. The page says when nothing else is over it.
     */
    fun takeReviewMoment(): Boolean {
        if (!reviewDue) return false
        reviewDue = false
        val page = page ?: return false
        if (word != null) return false
        return review.take(at = now(), calm = page.untroubled)
    }

    /**
     * Puts the question in the state `ParcelFeedbackPreview` names, whatever was answered before (the iPhone's
     * `-sdt.debug.parcelFeedback`): `reasons`, `right`, `sent`, `noted`, `early`, `named`, `back`, `backSent`,
     * `failed`, `words` or `carrier`; anything else asks. Debug builds only.
     */
    fun preview(variant: String) {
        val page = page ?: return
        val given = Words(id = UUID.randomUUID(), reasons = setOf(ParcelFeedbackReason.Steps), asked = ParcelFeedbackRequestAsked.Page)
        this.given = given
        when (variant) {
            "reasons" -> moment = Moment.Reasons
            "right" -> moment = Moment.Right
            "sent" -> moment = Moment.Sent
            "noted" -> moment = Moment.Noted
            "early" -> moment = Moment.Early
            "named" -> moment = Moment.Named
            "back" -> word = Word.Back(site = page.carrier)
            "backSent" -> {
                moment = Moment.Noted
                word = Word.BackSent
            }
            "failed" -> word = Word.Failed
            "words" -> words = given
            "carrier" -> namingCarrier = true
            else -> moment = Moment.Ask
        }
    }

    companion object {
        /** A page left for less than this many seconds was not compared with the carrier's. */
        const val away: Double = 1.5
    }
}
