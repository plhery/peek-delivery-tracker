package com.plhery.peekdeliverytracker.core

import kotlin.math.floor

/**
 * The passport's times as `DateComponentsFormatter` writes them in PassportView's `formattedDuration`: days and hours
 * from a day up, else hours and minutes, at most two units, the zero ones dropped, each unit truncated (119 s is one
 * minute), abbreviated ("1d 2h") on the page or spelled out ("1 day, 2 hours") for TalkBack. The words are
 * Foundation's in each app language, a small table here: ICU's MeasureFormat on a phone writes others ("1 day 2 hr",
 * "1 j 2 h"), and Foundation joins the spelled-out units with its own words ("und", "et", none in Russian).
 */
object FoundationDurations {
    /** A unit's abbreviated and spelled-out words, by the count's form (`Localizer.countForm`: one, few, many, other). */
    private class Unit(val short: (String) -> String, val full: (String) -> String)

    private class Words(
        val day: Unit,
        val hour: Unit,
        val minute: Unit,
        /** Between two abbreviated units. */
        val shortJoin: String = " ",
        /** Between two spelled-out units. */
        val fullJoin: String,
    )

    private fun unit(short: String, one: String, other: String, space: String = " ") =
        unit(short, short, one, other, other, space)

    /** A unit whose abbreviation has a form for one, and whose spelled-out words have one, few and many. */
    private fun unit(shortOne: String, shortOther: String, one: String, few: String, many: String, space: String = " ") =
        Unit(
            short = { form -> if (form == "one") shortOne else shortOther },
            full = { form -> space + when (form) { "one" -> one; "few" -> few; else -> many } },
        )

    /** French spells out hours and days after a no-break space, and minutes after a space. */
    private const val NBSP = "\u00A0"

    private val words: Map<AppLanguage, Words> = mapOf(
        AppLanguage.En to Words(
            day = unit("d", "day", "days"), hour = unit("h", "hour", "hours"), minute = unit("m", "minute", "minutes"),
            fullJoin = ", ",
        ),
        AppLanguage.De to Words(
            day = unit("d", "Tag", "Tage"), hour = unit("h", "Stunde", "Stunden"), minute = unit("min", "Minute", "Minuten"),
            fullJoin = " und ",
        ),
        AppLanguage.Fr to Words(
            day = unit("j", "jour", "jours", space = NBSP), hour = unit("h", "heure", "heures", space = NBSP),
            minute = unit("min", "minute", "minutes"),
            fullJoin = " et ",
        ),
        AppLanguage.It to Words(
            day = unit(" g", "giorno", "giorni"), hour = unit(" h", "ora", "ore"), minute = unit(" min", "minuto", "minuti"),
            fullJoin = " e ",
        ),
        AppLanguage.Es to Words(
            day = unit(" d", "día", "días"), hour = unit(" h", "hora", "horas"), minute = unit(" min", "minuto", "minutos"),
            fullJoin = " y ",
        ),
        AppLanguage.Pt to portuguese(),
        AppLanguage.PtBr to portuguese(),
        AppLanguage.Pl to Words(
            day = unit(" d.", " dn.", "dzień", "dni", "dni"), hour = unit("h", "h", "godzina", "godziny", "godzin"),
            minute = unit("min", "min", "minuta", "minuty", "minut"),
            fullJoin = " i ",
        ),
        AppLanguage.Ru to Words(
            day = unit(" д.", " д.", "день", "дня", "дней"), hour = unit(" ч", " ч", "час", "часа", "часов"),
            minute = unit(" мин", " мин", "минута", "минуты", "минут"),
            fullJoin = " ",
        ),
        AppLanguage.Zh to Words(
            day = Unit({ "天" }, { "天" }), hour = Unit({ "小时" }, { "小时" }), minute = Unit({ "分钟" }, { "分钟" }),
            shortJoin = "", fullJoin = "",
        ),
    )

    private fun portuguese() = Words(
        day = unit(" dia", " dias", "dia", "dias", "dias"), hour = unit("h", "hora", "horas"),
        minute = unit("min", "minuto", "minutos"),
        fullJoin = " e ",
    )

    /**
     * `formattedDuration`'s formatter for [seconds] of a minute or more in [language], spelled out when [full]. Under
     * a minute the passport writes its own copy (`passport.underOneMinute`, `passport.lessThanOneMinute`).
     */
    fun string(seconds: Double, language: AppLanguage, full: Boolean): String {
        val table = words.getValue(language)
        val whole = floor(seconds).toLong()
        val units = if (seconds >= 86_400) {
            listOf(table.day to whole / 86_400, table.hour to whole % 86_400 / 3_600)
        } else {
            listOf(table.hour to whole / 3_600, table.minute to whole % 3_600 / 60)
        }
        return units.filter { it.second > 0 }.joinToString(if (full) table.fullJoin else table.shortJoin) { (unit, count) ->
            val form = Localizer.countForm(count.toDouble(), language)
            "$count" + if (full) unit.full(form) else unit.short(form)
        }
    }
}
