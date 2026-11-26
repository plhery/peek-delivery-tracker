package com.plhery.peekdeliverytracker.ui.passport

import android.icu.text.DateFormat
import android.icu.util.TimeZone
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.PassportBook
import com.plhery.peekdeliverytracker.core.PassportNews
import com.plhery.peekdeliverytracker.core.PassportSeenRecord
import com.plhery.peekdeliverytracker.core.StampProgress
import com.plhery.peekdeliverytracker.core.showsYear
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesSource
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.time.Instant
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import java.util.UUID

// The web's `copy.ts`, `news.ts` and `NewStampLine`: each stamp's words, the days it is written with, what the passport
// holds that this device has not shown yet, the line a delivered card carries for it, and the dot on the tab.

/** `STAMP_COPY`: each stamp's name and what earns it, as `passport.*` keys. */
internal val STAMP_COPY: Map<String, Pair<String, String>> = mapOf(
    "first" to ("firstArrival" to "firstExplanation"),
    "rightNextDoor" to ("rightNextDoor" to "domesticExplanation"),
    "express" to ("expressArrival" to "expressExplanation"),
    "familiarFace" to ("familiarFace" to "familiarExplanation"),
    "pickedUp" to ("pickedUp" to "pickupExplanation"),
    "acrossBorders" to ("acrossBorders" to "acrossExplanation"),
    "connected" to ("wellConnected" to "carrierExplanation"),
    "secondTry" to ("secondTry" to "secondTryExplanation"),
    "worthTheWait" to ("worthTheWait" to "waitExplanation"),
    "aroundWorld" to ("aroundWorld" to "aroundExplanation"),
    "newHorizons" to ("newHorizons" to "horizonsExplanation"),
    "ten" to ("doubleDigits" to "tenExplanation"),
    "fourSeasons" to ("fourSeasons" to "seasonsExplanation"),
    "theRegular" to ("theRegular" to "regularExplanation"),
    "busyDoorstep" to ("busyDoorstep" to "busyExplanation"),
    "homeForHolidays" to ("homeForHolidays" to "holidayExplanation"),
    "tenCountries" to ("tenCountries" to "tenCountriesExplanation"),
    "firstAnniversary" to ("firstAnniversary" to "anniversaryExplanation"),
    "fifty" to ("fifty" to "fiftyExplanation"),
)

internal fun stampTitle(key: String, localizer: Localizer): String = localizer.text("passport.${STAMP_COPY.getValue(key).first}")

internal fun stampExplanation(key: String, localizer: Localizer): String = localizer.text("passport.${STAMP_COPY.getValue(key).second}")

/** The web's date language for each of the app's: Swiss days in the Swiss languages, as the web writes them. */
internal fun dateLocale(language: AppLanguage): Locale = Locale.forLanguageTag(
    when (language) {
        AppLanguage.En -> "en-CH"
        AppLanguage.De -> "de-CH"
        AppLanguage.Fr -> "fr-CH"
        AppLanguage.It -> "it-CH"
        AppLanguage.Es -> "es-ES"
        AppLanguage.Pt -> "pt-PT"
        AppLanguage.PtBr -> "pt-BR"
        AppLanguage.Pl -> "pl-PL"
        AppLanguage.Ru -> "ru-RU"
        AppLanguage.Zh -> "zh-CN"
    },
)

internal fun formatted(at: Instant, skeleton: String, language: AppLanguage, zone: ZoneId): String =
    DateFormat.getInstanceForSkeleton(skeleton, dateLocale(language)).apply { timeZone = TimeZone.getTimeZone(zone.id) }.format(Date.from(at))

/** `stampDay`: the day a stamp was earned, as it is written under it: "12 Aug", with the year when it isn't this one's. */
internal fun stampDay(at: Instant, language: AppLanguage, now: Instant, zone: ZoneId = ZoneId.systemDefault()): String =
    formatted(at, if (showsYear(at, now, zone)) "yMMMd" else "MMMd", language, zone)

/** `stampFullDay`: the day in full, for the stamp's details and TalkBack: "12 August 2026". */
internal fun stampFullDay(at: Instant, language: AppLanguage, zone: ZoneId = ZoneId.systemDefault()): String =
    formatted(at, "yMMMMd", language, zone)

/** `stampProgress`: how far an unearned stamp has come, "3 of 5", or "Not yet" for one a single parcel earns. */
internal fun stampProgress(stamp: StampProgress, localizer: Localizer): String =
    if (stamp.total > 1) {
        localizer.text("passport.progress", mapOf("count" to minOf(stamp.count, stamp.total), "total" to stamp.total))
    } else {
        localizer.text("passport.notYet")
    }

/** `Earned on 12 August 2026`, for a stamp earned on a known day. */
internal fun stampEarnedLine(stamp: StampProgress, localizer: Localizer, language: AppLanguage): String? =
    stamp.earnedAt?.let { localizer.text("passport.earnedOn", mapOf("date" to stampFullDay(it, language))) }

/**
 * `usePassportNews`, for the tabs to share: the passport as the parcels hold it now ([book]), and what it holds that this
 * device has not shown ([news]); [markSeen] records all it holds as shown, [openPassport] goes to the tab.
 */
@Immutable
class PassportNewsState internal constructor(
    val news: PassportNews,
    val loading: Boolean,
    val markSeen: () -> Unit,
    val openPassport: () -> Unit,
) {
    val book: PassportBook get() = news.book
}

/** The tabs' passport news, from [rememberPassportNews]; none outside the tabs. */
val LocalPassportNews = compositionLocalOf<PassportNewsState?> { null }

/**
 * The passport's news for the account (or guest) in: the seen list kept for it, read again at each write. With none
 * kept, once its parcels have loaded, everything the passport holds is recorded as seen: nothing is new on a device's
 * first read.
 */
@Composable
internal fun rememberPassportNews(source: DeliveriesSource, openPassport: () -> Unit): PassportNewsState {
    val parcels by source.parcels.collectAsState()
    val loading by source.loading.collectAsState()
    val session by source.session.collectAsState()
    val writes by source.passportSeen.changes.collectAsState()
    val scope = PassportSeenRecord.scope(session)
    val book = remember(parcels) { PassportBook(parcels) }
    val seen = remember(source, scope, writes) { source.passportSeen.seen(scope) }
    LaunchedEffect(source, scope, seen == null, loading, book) {
        if (seen == null && !loading) source.passportSeen.store(scope, PassportNews(book, emptySet()).earnedKeys)
    }
    val news = remember(book, seen) { PassportNews(book, seen) }
    val open by rememberUpdatedState(openPassport)
    return remember(news, loading, source, scope) {
        PassportNewsState(news, loading, markSeen = { source.passportSeen.store(scope, news.earnedKeys) }, openPassport = { open() })
    }
}

/**
 * `NewStampLine`: under a delivered or archived card whose parcel earned stamps not seen yet, one button that opens the
 * passport: the first one's mini seal, "New stamp" or "3 new stamps" over their names joined by " · ", and a chevron;
 * read as "<heading>: <names>". It opens under the card ([tucked] of it under the card's foot) 150 ms after it shows,
 * its words rising and its seal popping in after; under Reduce Motion it fades in at its height.
 */
@Composable
internal fun NewStampLine(parcelId: UUID, ground: Color, modifier: Modifier = Modifier, tucked: Boolean = true) {
    val state = LocalPassportNews.current ?: return
    val items = state.news.byParcel[parcelId] ?: return
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val names = remember(items, localizer, language) { items.map { stampTitle(it.stamp.key, localizer) } }
    val heading = remember(items.size, localizer, language) {
        if (items.size == 1) localizer.text("passport.newStamp") else localizer.text("passport.newStamps", mapOf("count" to items.size))
    }
    val label = "$heading: ${names.joinToString(", ")}"
    val clock = remember(reduceMotion) { Animatable(0f) }
    LaunchedEffect(clock) { clock.animateTo(if (reduceMotion) 200f else LINE_END, tween(if (reduceMotion) 200 else LINE_END.toInt(), easing = LinearEasing)) }
    val under = if (tucked) TUCK else 0.dp
    Box(
        modifier
            .fillMaxWidth()
            // Only what has opened shows, the tucked part under the card's foot included.
            .drawWithContent { clipRect(top = -under.toPx()) { this@drawWithContent.drawContent() } }
            .layout { measurable, constraints ->
                // The row opens from the card's foot to its height, 260 ms from 150 ms in; the tucked part stays under it.
                val placeable = measurable.measure(constraints)
                val tuck = under.roundToPx()
                val open = if (reduceMotion) 1f else phase(clock.value, 150f, 260f, PassportMove)
                val height = tuck + ((placeable.height - tuck) * open).toInt()
                layout(placeable.width, height - tuck) { placeable.place(0, -tuck) }
            }
            .graphicsLayer { if (reduceMotion) alpha = phase(clock.value, 0f, 200f) }
            .testTag("$PASSPORT_NEWS_LINE.$parcelId")
            .button(interactionSource = null, indication = PressStyle.Passport, role = Role.Button, onClick = state.openPassport)
            .clearAndSetSemantics {
                contentDescription = label
                role = Role.Button
                onClick {
                    state.openPassport()
                    true
                }
            }
            .background(ground, RoundedCornerShape(bottomStart = 18.dp, bottomEnd = 18.dp))
            .padding(top = under, start = 8.dp, end = 8.dp, bottom = 8.dp),
    ) {
        Row(
            Modifier.fillMaxWidth().background(colors.paper, RoundedCornerShape(12.dp)).padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StampSeal(
                items.first().stamp.definition, earned = true, size = SealSize.Mini,
                modifier = Modifier.padding(start = 8.dp).graphicsLayer {
                    if (!reduceMotion) {
                        val pop = phase(clock.value, 350f, 220f, PassportSettle)
                        scaleX = 0.6f + 0.4f * pop
                        scaleY = scaleX
                        alpha = pop
                    }
                },
            )
            Column(
                Modifier.weight(1f).padding(vertical = 7.dp).graphicsLayer {
                    if (!reduceMotion) {
                        val rise = phase(clock.value, 230f, 300f, PassportSettle)
                        translationY = 8.dp.toPx() * (1 - rise)
                        alpha = rise
                    }
                },
                verticalArrangement = Arrangement.spacedBy(1.dp),
            ) {
                Text(heading, color = colors.ink, style = type.style(IosTextStyle.Footnote, FontWeight.SemiBold).sfLines())
                Text(
                    names.joinToString(" · "), color = colors.secondaryText, style = type.caption.sfLines(), maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            PeekSymbol("chevron.right", Modifier.padding(end = 10.dp), font = type.style(IosTextStyle.Caption2, FontWeight.Bold), tint = colors.secondaryText)
        }
    }
}

/**
 * The dot on the Passport tab while something there is new: the orange ink, 7 across in a 1.5 ring of the bar's
 * [ring], popping in 600 ms after the card's line starts (0 to 1.25 in 190 ms, then 1), and fading out over 150 ms as
 * the passport opens. Under Reduce Motion it fades in and out alike. TalkBack hears "New" with the tab.
 */
@Composable
internal fun TabDot(visible: Boolean, ring: Color, modifier: Modifier = Modifier) {
    val reduceMotion = PeekTheme.reduceMotion
    AnimatedVisibility(visible, modifier, enter = fadeIn(tween(if (reduceMotion) 150 else 0, easing = LinearEasing)), exit = fadeOut(tween(150, easing = LinearEasing))) {
        // Read only while it shows: a bar drawn on its own, with nothing new, needs no words.
        val localizer = LocalLocalizer.current
        val language = LocalLanguage.current
        val new = remember(localizer, language) { localizer.text("passport.new") }
        val clock = remember { Animatable(if (reduceMotion) DOT_END else 0f) }
        LaunchedEffect(clock) { clock.animateTo(DOT_END, tween(DOT_END.toInt(), easing = LinearEasing)) }
        Box(
            Modifier
                .graphicsLayer {
                    if (!reduceMotion) {
                        val time = clock.value - DOT_DELAY
                        val grow = phase(time, 0f, 190f, PassportMove)
                        val settle = phase(time, 190f, 130f, PassportSettle)
                        val scale = if (time < 190f) 1.25f * grow else 1.25f - 0.25f * settle
                        scaleX = scale
                        scaleY = scale
                        alpha = phase(time, 0f, 120f)
                    }
                }
                .size(DOT + 3.dp)
                .border(1.5.dp, ring, CircleShape)
                .padding(1.5.dp)
                .background(PeekTheme.colors.orange, CircleShape)
                .clearAndSetSemantics { contentDescription = new },
        )
    }
}

/** The line's motion, all done: the open (150 + 260), the words (230 + 300) and the seal (350 + 220). */
private const val LINE_END = 570f
private val TUCK = 18.dp
private val DOT = 7.dp
private const val DOT_DELAY = 750f
private const val DOT_END = DOT_DELAY + 320f

/** The news line's test tag, then its parcel's id. */
const val PASSPORT_NEWS_LINE = "$PASSPORT.newsLine"
