package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason

// From ParcelFeedbackViews.swift, the words the question's views say. The views are the app's.

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
