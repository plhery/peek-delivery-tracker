package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequest

// From ParcelFeedbackViews.swift, the words the question's views say and the rules of its sheets' fields. The views
// are the app's.

val ParcelFeedbackReason.labelKey: String
    get() = when (this) {
        ParcelFeedbackReason.Arrived -> "feedback.reason.arrived"
        ParcelFeedbackReason.Status -> "feedback.reason.status"
        ParcelFeedbackReason.Steps -> "feedback.reason.steps"
        ParcelFeedbackReason.TimePlace -> "feedback.reason.timePlace"
        ParcelFeedbackReason.Carrier -> "feedback.reason.carrier"
        ParcelFeedbackReason.Other -> "feedback.reason.other"
    }

/** What the page says in place once an answer is given, as a localization key; nil while nothing is answered. */
val ParcelFeedbackModel.Moment.thanksKey: String?
    get() = when (this) {
        ParcelFeedbackModel.Moment.Right -> "feedback.pip.right"
        ParcelFeedbackModel.Moment.Sent, ParcelFeedbackModel.Moment.Noted -> "feedback.pip.sent"
        ParcelFeedbackModel.Moment.Early -> "feedback.unknown.early"
        ParcelFeedbackModel.Moment.Named -> "feedback.unknown.sent"
        ParcelFeedbackModel.Moment.Ask, ParcelFeedbackModel.Moment.Reasons -> null
    }

/** What a word at the bottom of the page says. */
fun Localizer.text(word: ParcelFeedbackModel.Word): String = when (word) {
    is ParcelFeedbackModel.Word.Back -> text("feedback.back.question", mapOf("site" to word.site))
    ParcelFeedbackModel.Word.BackRight -> text("feedback.back.right")
    ParcelFeedbackModel.Word.BackSent -> text("feedback.back.sent")
    ParcelFeedbackModel.Word.Failed -> text("feedback.failed")
}

// The sheets' rules for what is typed. A field keeps what is typed within what an answer may carry as it is typed (the
// sheets' `onChange`). Counts are of Unicode scalars, as the limits are, never UTF-16 units: an emoji is one or more
// scalars, as on the iPhone. A lone surrogate, which a Swift String cannot hold, is U+FFFD.

/** What is off: the reasons, several at once, and a note for what they do not say. */
object ParcelFeedbackWordsSheet {
    /** Nothing to send: no reason, and nothing in the note but space. */
    fun empty(reasons: Set<ParcelFeedbackReason>, note: String): Boolean =
        reasons.isEmpty() && ParcelFeedbackRequest.words(note, limit = ParcelFeedbackRequest.noteLimit) == null

    /** How many more Unicode scalars the note takes. */
    fun noteLeft(note: String): Int = ParcelFeedbackRequest.noteLimit - note.codePointCount(0, note.length)

    /** What the sheet says under the note once it nears its limit ("feedback.noteLeft.many"), nil before. */
    fun noteLeftText(note: String, localizer: Localizer): String? {
        val left = noteLeft(note)
        return if (left <= ParcelFeedbackRequest.noteWarning) localizer.text("feedback.noteLeft.many", mapOf("count" to left)) else null
    }

    /** The note as its field keeps it once `typed`. */
    fun note(typed: String): String = kept(typed, ParcelFeedbackRequest.noteLimit)
}

/** Who carries a parcel no carrier was found for, and where its own site shows it. */
object ParcelFeedbackCarrierSheet {
    /** Nothing to send: neither field has more than space. */
    fun empty(who: String, page: String): Boolean =
        ParcelFeedbackRequest.words(who, limit = ParcelFeedbackRequest.carrierNameLimit) == null &&
            ParcelFeedbackRequest.words(page, limit = ParcelFeedbackRequest.trackingPageLimit) == null

    /** The carrier's name as its field keeps it once `typed`. */
    fun who(typed: String): String = kept(typed, ParcelFeedbackRequest.carrierNameLimit)

    /** The carrier's page as its field keeps it once `typed`. */
    fun page(typed: String): String = kept(typed, ParcelFeedbackRequest.trackingPageLimit)
}

/** Whole Characters while their Unicode scalars fit (`ParcelFeedbackRequest.limited`). */
private fun kept(typed: String, limit: Int): String = ParcelFeedbackRequest.limited(typed.wellFormed(), to = limit)
