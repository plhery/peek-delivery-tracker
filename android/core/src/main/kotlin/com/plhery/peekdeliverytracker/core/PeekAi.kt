package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PeekAi
import com.plhery.peekdeliverytracker.core.api.PeekAiSays
import com.plhery.peekdeliverytracker.core.api.PeekAiStepKind
import com.plhery.peekdeliverytracker.core.api.PeekAiWhy
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.Locale
import kotlin.math.floor

// Peek AI: Peek's own estimate of when a parcel will come, which the server gives only on a strong hint (`peek_ai` in
// contracts/openapi.json). It is always named as Peek's, never the carrier's. The web's is src/lib/peekAi.ts.

/** Whether cards, pages and widgets show Peek AI ("Peek AI" in Settings), as an [AccountChoice]. */
class ShowPeekAiChoice(defaults: UserDefaults) :
    AccountChoice(defaults, key = "sdt.showPeekAi.v1", unsavedKey = "sdt.showPeekAiUnsaved.v1")

/** The day a time with its offset names, on its own clock: "2026-11-24T08:12:00+01:00" is the 24th. */
private fun dayOf(iso: String?): String? = iso?.take(10)

/** A day the server wrote ("2026-11-24"). */
private fun dateOf(day: String): LocalDate? = runCatching { LocalDate.parse(day.take(10)) }.getOrNull()

/** A day on this phone's calendar, as the server writes days. */
private fun isoDay(at: Instant, zone: ZoneId = ZoneId.systemDefault()): String = at.atZone(zone).toLocalDate().toString()

/**
 * Peek AI's estimate while it still holds on this phone's clock: none once its day has passed, or the hour it named. A
 * page left open, or kept for a while without a connection, would otherwise go on saying it.
 */
fun currentPeekAi(ai: PeekAi?, now: Instant = AppClock.now()): PeekAi? {
    ai ?: return null
    val today = isoDay(now)
    return when (ai.says) {
        PeekAiSays.NotToday -> ai.takeIf { (ai.carrierDay ?: dayOf(ai.outSince)) == today }
        PeekAiSays.ByTime -> {
            val time = ai.time ?: return null
            if (dayOf(ai.outSince) != today) return null
            val at = now.atZone(ZoneId.systemDefault())
            ai.takeIf { String.format(Locale.ROOT, "%02d:%02d", at.hour, at.minute) < time }
        }
        // Earlier than the carrier's date holds until that date; the other days, until their own.
        else -> {
            val until = if (ai.says == PeekAiSays.Sooner) ai.carrierDay else ai.day
            ai.takeIf { until == null || until >= today }
        }
    }
}

/**
 * When [currentPeekAi] stops holding, for a widget drawn while the app is closed: the end of its day, or the hour it
 * named. None while it holds all along.
 */
fun peekAiUntil(ai: PeekAi, zone: ZoneId = ZoneId.systemDefault()): Instant? {
    fun after(day: String?) = day?.let(::dateOf)?.plusDays(1)?.atStartOfDay(zone)?.toInstant()
    return when (ai.says) {
        PeekAiSays.NotToday -> after(ai.carrierDay ?: dayOf(ai.outSince))
        PeekAiSays.ByTime -> {
            val day = dayOf(ai.outSince)?.let(::dateOf) ?: return null
            val time = ai.time?.let { runCatching { LocalTime.parse(it) }.getOrNull() } ?: return null
            day.atTime(time).atZone(zone).toInstant()
        }
        PeekAiSays.Sooner -> after(ai.carrierDay)
        else -> after(ai.day)
    }
}

/** Stages after which a parcel is no longer on its way to the door, as the server has them. */
private val notDue = setOf(TrackingStage.Delivered, TrackingStage.Returned, TrackingStage.ReadyForPickup, TrackingStage.FailedAttempt)

/**
 * Peek AI's estimate for a parcel while it holds, and while the parcel is still on its way: a copy kept from before a
 * scan that delivered it, or an archived parcel, says none.
 */
fun parcelPeekAi(parcel: Parcel, now: Instant = AppClock.now()): PeekAi? {
    if (parcel.isArchived || parcel.currentStage in notDue) return null
    return currentPeekAi(parcel.peekAi, now)
}

/**
 * When what Peek AI says of the parcel on a card can next change, for a list left open: the hour or the midnight its
 * estimate ends ([peekAiUntil]), and each midnight before it, when "today" moves on, a week of them at most. None while
 * it says nothing.
 */
fun peekAiChanges(parcel: Parcel, now: Instant = AppClock.now()): List<Instant> {
    val ai = parcelPeekAi(parcel, now) ?: return emptyList()
    val zone = ZoneId.systemDefault()
    val end = peekAiUntil(ai, zone)?.takeIf { it > now }
    val midnights = generateSequence(now.atZone(zone).toLocalDate().plusDays(1)) { it.plusDays(1) }
        .map { it.atStartOfDay(zone).toInstant() }
        .takeWhile { end == null || it < end }
        .take(7)
        .toList()
    return midnights + listOfNotNull(end)
}

/** A wall clock Peek AI names ("15:30"), or the hour on a time it gives with its offset, as the reader writes times. */
private fun Localizer.peekAiClock(value: String): String =
    deliveryWallClock(if (value.length > 5) value.substring(11, 16) else value)

private fun Localizer.calendar(day: String): String =
    dateOf(day)?.let { shortDate(it.atStartOfDay(ZoneId.systemDefault()).toInstant()) } ?: day

/**
 * The few words after the name on a card: "unlikely" beside the carrier's date when it says today (`estimate`, the
 * carrier's estimate the card shows, as the server wrote it), "not today" where Peek AI stands alone, else what it
 * expects ("by 16:00", "by Thu 26 Nov").
 */
fun Localizer.peekAiCardWords(ai: PeekAi, estimate: String?, now: Instant = AppClock.now()): String = when (ai.says) {
    PeekAiSays.NotToday -> {
        val zone = ZoneId.systemDefault()
        val day = estimate?.let(DateParser::deliveryDate)?.atZone(zone)?.toLocalDate()
        text(if (day != null && day == now.atZone(zone).toLocalDate()) "peekAi.card.unlikely" else "peekAi.card.notToday")
    }
    PeekAiSays.ByTime -> text("peekAi.card.byTime", mapOf("time" to peekAiClock(ai.time ?: "")))
    PeekAiSays.ByDay -> ai.day?.let { text("peekAi.card.byDay", mapOf("date" to calendar(it))) } ?: text("peekAi.card.todayOrTomorrow")
    PeekAiSays.TodayOrTomorrow -> text("peekAi.card.todayOrTomorrow")
    PeekAiSays.ThisWeek -> text("peekAi.card.thisWeek")
    PeekAiSays.ByEndOfNextWeek -> text("peekAi.card.byEndOfNextWeek")
    PeekAiSays.Sooner -> text("peekAi.card.sooner")
}

/**
 * Peek AI's words on a parcel's card while `show` is on and its call holds: none once the parcel has arrived, nor while
 * Peek AI says nothing.
 */
fun Localizer.parcelPeekAiWords(parcel: Parcel, show: Boolean, now: Instant = AppClock.now()): String? {
    if (!show || parcelCompletionDate(parcel, now) != null) return null
    val ai = parcelPeekAi(parcel, now) ?: return null
    // The carrier's estimate as the card shows it, beside which Peek AI says "unlikely".
    val estimate = if (parcelDeliveryEstimate(parcel, now) != null) shownEstimate(parcel, now) else null
    return peekAiCardWords(ai, estimate, now)
}

/** What Peek AI says on the parcel's page, after its name: "today is unlikely", "maybe sooner, from tomorrow". */
fun Localizer.peekAiClaim(ai: PeekAi, now: Instant = AppClock.now()): String = when (ai.says) {
    PeekAiSays.NotToday -> text("peekAi.claim.notToday")
    PeekAiSays.ByTime -> text("peekAi.claim.byTime", mapOf("time" to peekAiClock(ai.time ?: "")))
    PeekAiSays.ByDay -> ai.day?.let { text("peekAi.claim.byDay", mapOf("date" to calendar(it))) } ?: text("peekAi.claim.todayOrTomorrow")
    PeekAiSays.TodayOrTomorrow -> text("peekAi.claim.todayOrTomorrow")
    PeekAiSays.ThisWeek -> text("peekAi.claim.thisWeek")
    PeekAiSays.ByEndOfNextWeek -> text("peekAi.claim.byEndOfNextWeek")
    PeekAiSays.Sooner -> when (val day = ai.day) {
        null -> text("peekAi.claim.sooner")
        now.atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1).toString() -> text("peekAi.claim.soonerTomorrow")
        else -> text("peekAi.claim.soonerFrom", mapOf("date" to calendar(day)))
    }
}

/** The claim as a heading: "Today is unlikely". */
fun Localizer.peekAiHeading(ai: PeekAi, now: Instant = AppClock.now()): String {
    val claim = peekAiClaim(ai, now)
    if (claim.isEmpty()) return claim
    val first = claim.codePointAt(0)
    val size = Character.charCount(first)
    return claim.substring(0, size).uppercase(language.locale) + claim.substring(size)
}

/**
 * What the carrier says, under Peek AI's heading in its sheet: "DPD says today", "UPS gives no date". `estimate` is the
 * carrier's estimate as the card words it.
 */
fun Localizer.peekAiCarrierWord(ai: PeekAi, carrier: String, estimate: String?): String {
    val named = mapOf("carrier" to carrier)
    return when {
        ai.why == PeekAiWhy.MissedDate -> text("peekAi.carrier.wasDue", named)
        ai.why == PeekAiWhy.OutForDelivery -> text("peekAi.carrier.noHour", named)
        ai.why == PeekAiWhy.NoOutForDelivery -> text("peekAi.carrier.saysToday", named)
        ai.why == PeekAiWhy.Ahead && ai.carrierDay != null -> text("peekAi.carrier.saysDate", named + ("date" to calendar(ai.carrierDay!!)))
        estimate != null -> text("peekAi.carrier.saysDate", named + ("date" to estimate))
        else -> text("peekAi.carrier.noDate", named)
    }
}

/**
 * How long ago, in words that sit in a sentence: "5 hours ago", "yesterday", "2 days ago", by the calendar on this
 * phone once a day has passed.
 */
private fun Localizer.ago(iso: String, now: Instant): String {
    val then = DateParser.date(iso) ?: return ""
    val elapsed = Duration.between(then, now)
    val zone = ZoneId.systemDefault()
    val days = ChronoUnit.DAYS.between(then.atZone(zone).toLocalDate(), now.atZone(zone).toLocalDate()).toInt()
    val (offset, unit) = when {
        elapsed.toHours() < 1 -> maxOf(1, elapsed.toMinutes().toInt()) to RelativeUnit.Minute
        elapsed.toHours() < 24 -> elapsed.toHours().toInt() to RelativeUnit.Hour
        days < 14 -> days to RelativeUnit.Day
        else -> days / 7 to RelativeUnit.Week
    }
    Icu.relativeTimes?.let { return it.format(-offset, unit, language.locale) }
    // A JVM without ICU: the app's own short words.
    return when (unit) {
        RelativeUnit.Minute -> text("time.minutesAgo", mapOf("count" to offset))
        RelativeUnit.Hour -> text("time.hoursAgo", mapOf("count" to offset))
        RelativeUnit.Day -> if (offset == 1) text("time.yesterday") else text("time.daysAgo", mapOf("count" to offset))
        RelativeUnit.Week -> text("time.daysAgo", mapOf("count" to days))
    }
}

/** Why Peek AI thinks so, about this parcel: one or two sentences. */
fun Localizer.peekAiWhy(ai: PeekAi, carrier: String, now: Instant = AppClock.now()): String {
    val named = mapOf("carrier" to carrier)
    return when (ai.why) {
        PeekAiWhy.NoOutForDelivery -> text("peekAi.why.noOutForDelivery", named)
        PeekAiWhy.OutForDelivery -> text("peekAi.why.outForDelivery", named + ("time" to peekAiClock(ai.outSince ?: "")))
        PeekAiWhy.LateOut -> text("peekAi.why.lateOut", named)
        PeekAiWhy.MissedDate -> text("peekAi.why.missedDate", named)
        PeekAiWhy.Ahead -> text("peekAi.why.ahead", named + ("date" to (ai.carrierDay?.let { calendar(it) } ?: "")))
        PeekAiWhy.NoDate -> {
            val first = text("peekAi.why.noDate", named)
            val last = ai.last ?: return first
            val key = when (last.kind) {
                PeekAiStepKind.Depot -> "peekAi.last.depot"
                PeekAiStepKind.Customs -> "peekAi.last.customs"
                PeekAiStepKind.Country -> "peekAi.last.country"
                PeekAiStepKind.Scan -> "peekAi.last.scan"
            }
            val step = text(key, mapOf("ago" to ago(last.at, now)))
            if (language == AppLanguage.Zh) "$first$step" else "$first $step"
        }
    }
}

/** How often Peek AI was right on parcels like this one: "Right 8 times in 10 at this hour." */
fun Localizer.peekAiRecord(ai: PeekAi): String {
    // Rounded down, so it never says more than it was.
    val n = floor(ai.sure * 10 + 1e-9).toInt().coerceIn(1, 9)
    return text(if (ai.says == PeekAiSays.NotToday) "peekAi.sure.hour" else "peekAi.sure.parcels", mapOf("n" to n))
}

/** One cell of the sheet's strip: a day or two hours, how likely, and whether it is the carrier's date or has passed. */
data class PeekAiCell(
    val key: String,
    /** The small line: a weekday, or nothing for hours. */
    val over: String,
    /** The bold line: the day of the month, or the hour. */
    val main: String,
    val likely: Boolean,
    val maybe: Boolean,
    val carrier: Boolean,
    val past: Boolean,
)

/** The sheet's strip: days, or hours two to a cell. */
data class PeekAiStrip(val kind: Kind, val cells: List<PeekAiCell>) {
    enum class Kind { Days, Hours }
}

/** Days with their chance, the carrier's date ringed; or, out for delivery, two hours to a cell. */
fun Localizer.peekAiStrip(ai: PeekAi, now: Instant = AppClock.now()): PeekAiStrip? {
    val hours = ai.hours.orEmpty()
    if (hours.isNotEmpty()) {
        val at = now.atZone(ZoneId.systemDefault())
        val hour = at.hour + at.minute / 60.0
        val cells = hours.chunked(2).map { pair ->
            val start = pair.first().time.take(2).toIntOrNull() ?: 0
            val share = pair.sumOf { it.share }
            PeekAiCell(
                key = pair.first().time, over = "", main = deliveryWallClock(pair.first().time),
                likely = share >= 0.15, maybe = share >= 0.06 && share < 0.15, carrier = false, past = start + pair.size <= hour,
            )
        }
        return PeekAiStrip(PeekAiStrip.Kind.Hours, cells)
    }
    val days = ai.days.orEmpty().ifEmpty { return null }
    val weekdays = FormatData.language(language).weekdays
    return PeekAiStrip(
        PeekAiStrip.Kind.Days,
        days.mapNotNull { entry ->
            val date = dateOf(entry.day) ?: return@mapNotNull null
            PeekAiCell(
                key = entry.day, over = weekdays[date.dayOfWeek.value % 7].replace(".", ""), main = date.dayOfMonth.toString(),
                likely = entry.p >= 0.25, maybe = entry.p >= 0.08 && entry.p < 0.25, carrier = entry.day == ai.carrierDay, past = false,
            )
        },
    )
}
