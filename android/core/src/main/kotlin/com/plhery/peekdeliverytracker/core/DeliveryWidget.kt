package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.Parcel
import java.io.File
import java.security.MessageDigest
import java.time.Instant
import java.util.Locale
import java.util.UUID

// DeliveryWidget.swift's provider, the part of it that is logic: what a widget shows at a time, where its next button
// turns it to and when it reads the store again. And DeliveryWidgetPictures.swift's naming of a card's pictures, which
// the app draws for the widget (:app's widget/WidgetCardPictures.kt draws them).

/**
 * `DeliveryWidgetEntry`: what a widget shows at `date`. `art` is the pictures of the cards it shows, in order, as the
 * widget reads them: none unless every one of them has its pictures.
 */
data class DeliveryWidgetEntry<A>(
    val date: Instant,
    val enabled: Boolean,
    val languageCode: String,
    val snapshot: DeliveryWidgetSnapshot?,
    /** The parcels the widget shows: its first ones, or those its next button turned it to. */
    val shown: List<DeliveryWidgetParcel> = emptyList(),
    val isFirstPage: Boolean = true,
    /** The parcel the next button turns the widget to, the first again from the last parcels; none when all fit at once. */
    val next: UUID? = null,
    val art: List<A> = emptyList(),
    /** The demo's cards, which the gallery shows as they are rather than redacted. */
    val isSample: Boolean = false,
) {
    /** The parcels the widget shows as the app's cards, with their pictures, once the app has drawn every one of them. */
    val cards: List<Triple<DeliveryWidgetParcel, DeliveryWidgetCard, A>>
        get() {
            if (!enabled) return emptyList()
            val cards = shown.zip(art).mapNotNull { (parcel, art) -> parcel.card?.let { Triple(parcel, it, art) } }
            return if (cards.size == art.size) cards else emptyList()
        }

    companion object {
        /**
         * `makeEntry`: the page the widget was turned to (the one holding `turn`), else its first, and the pictures of
         * its cards as `art` reads them: a small widget shows one parcel, a medium one two side by side, or one alone,
         * across it.
         */
        fun <A> make(
            date: Instant,
            enabled: Boolean,
            languageCode: String,
            snapshot: DeliveryWidgetSnapshot?,
            medium: Boolean,
            turn: UUID? = null,
            art: (DeliveryWidgetCard, DeliveryWidgetPicture) -> A?,
        ): DeliveryWidgetEntry<A> {
            val pages = DeliveryWidgetSelection.pages(snapshot?.parcels.orEmpty(), medium = medium, now = date)
            val index = turn?.let { id -> pages.indexOfFirst { page -> page.any { it.id == id } }.takeIf { it >= 0 } } ?: 0
            val shown = pages.getOrNull(index).orEmpty()
            val shape = if (medium) (if (shown.size > 1) DeliveryWidgetPicture.Half else DeliveryWidgetPicture.Wide) else DeliveryWidgetPicture.Small
            val drawn = shown.mapNotNull { parcel -> parcel.card?.let { art(it, shape) } }
            return DeliveryWidgetEntry(
                date = date, enabled = enabled, languageCode = languageCode, snapshot = snapshot, shown = shown, isFirstPage = index == 0,
                next = if (pages.size > 1) pages[(index + 1) % pages.size].firstOrNull()?.id else null,
                art = if (shown.isNotEmpty() && drawn.size == shown.size) drawn else emptyList(),
            )
        }
    }
}

/**
 * What the list widget ("Parcels") shows at `date`: the parcels the store published in the widgets' order, less those
 * delivered more than a day ago, worded for `date`.
 */
data class DeliveryWidgetList(
    val date: Instant,
    val enabled: Boolean,
    val languageCode: String,
    val parcels: List<DeliveryWidgetParcel>,
    /** The sample's parcels, which the gallery shows. */
    val isSample: Boolean = false,
) {
    /**
     * `Size`: the iPhone's three list widgets. `mostRows`: more rows than each size could hold in its smallest type,
     * which bounds the layouts the widget measures ([layouts]); above the iPhone's 6 and 12 for a small and a medium
     * one, which on Android stand up to 279 dp tall. Up to `cards` parcels it shows as cards ([Design]): one, two side
     * by side, three one above another.
     */
    enum class Size(val mostRows: Int, val cards: Int) {
        Small(8, 1),
        Medium(16, 2),
        Large(24, 3),
    }

    /**
     * `Layout`: how many rows the sections' design shows, and in which type, `compact` being the smaller; `oneColumn`,
     * in a medium widget, down one column with each row on one line, as in a large widget, and without the headings, as
     * in a small one, rather than in two columns.
     */
    data class Layout(val rows: Int, val compact: Boolean, val oneColumn: Boolean = false)

    /** `Fit`: what the widget shows its parcels as, every parcel's pass or rows. */
    sealed interface Fit {
        data object Passes : Fit

        data class Rows(val layout: Layout) : Fit
    }

    /**
     * How the list shows its parcels, by how many there are for its size: up to [Size.cards] of them as cards, up to
     * twice as many as passes, more as rows under the app list's sections. Each shows every parcel whole up to there:
     * a large widget holds three cards and six passes, one above another.
     */
    enum class Design { Cards, Passes, Sections }

    fun design(size: Size): Design = when {
        parcels.size <= size.cards -> Design.Cards
        parcels.size <= size.cards * 2 -> Design.Passes
        else -> Design.Sections
    }

    /** The parcels as its cards show them: the app list's Next up first, then the widget's order. */
    val cardOrder: List<DeliveryWidgetParcel>
        get() = parcels.sortedBy { if (it.nextUp == true) 0 else 1 }

    /** A section of the app's list and the parcels under it; none where the app didn't publish the sections. */
    data class Section(val kind: ParcelSectionKind?, val parcels: List<DeliveryWidgetParcel>)

    /**
     * The parcels under the app list's sections, in its order (Needs attention, Arriving today, On the way, Past
     * deliveries), each with its Next up parcel first, then the widget's order; under none, in the widget's order,
     * where one has no section, as from an app that didn't publish them.
     */
    val sections: List<Section>
        get() {
            val kinds = parcels.map { parcel -> ParcelSectionKind.entries.firstOrNull { it.rawValue == parcel.section } }
            if (kinds.any { it == null }) return if (parcels.isEmpty()) emptyList() else listOf(Section(null, parcels))
            return ParcelSectionKind.entries.mapNotNull { kind ->
                val under = parcels.filterIndexed { index, _ -> kinds[index] == kind }
                if (under.isEmpty()) null else Section(kind, under.sortedBy { if (it.nextUp == true) 0 else 1 })
            }
        }

    /** `Line`: a line of the sections' design. */
    sealed interface Line {
        /** About how tall the line is, against a row's. */
        val weight: Double

        /** A section's kind and how many parcels it has. */
        data class Heading(val kind: ParcelSectionKind, val count: Int) : Line {
            override val weight: Double get() = 0.6
        }

        data class Row(val parcel: DeliveryWidgetParcel) : Line {
            override val weight: Double get() = 1.0
        }

        /** How many parcels are left out. */
        data class More(val count: Int) : Line {
            override val weight: Double get() = 0.5
        }
    }

    companion object {
        /** `link`: the deliveries, which the last line opens ([NativeRoute.Parcels]). */
        const val link = "peekdeliverytracker://parcels"

        /**
         * `layouts(count:size:)`: the layouts a widget tries for `count` parcels, the first that fits being the one it
         * shows: the most rows first, each in the usual type, then in a small or medium widget in smaller type, as more
         * rows win over larger type. The rows then show every parcel, or the room under the last is shorter than another
         * row.
         */
        fun layouts(count: Int, size: Size): List<Layout> = (minOf(size.mostRows, count) downTo 1).flatMap { rows ->
            listOf(Layout(rows, compact = false)) + if (size == Size.Large) emptyList() else listOf(Layout(rows, compact = true))
        }

        /**
         * `fits(_:count:size:)`: what a widget of `design` tries for `count` parcels, the first that fits being the one
         * it shows: every parcel's pass, for the passes' design, then the most rows that fit ([layouts]). Passes show
         * every parcel or none: where two cannot stand one above the other, in a shorter widget or at larger text, the
         * parcels are rows. In a medium widget, where every parcel's row doesn't fit in two columns either, they try one
         * column in smaller type before leaving one out.
         */
        fun fits(design: Design, count: Int, size: Size): List<Fit> {
            val rows = layouts(count, size)
            if (design != Design.Passes) return rows.map(Fit::Rows)
            val every = rows.takeWhile { it.rows == count }
            val oneColumn = if (size == Size.Medium && every.isNotEmpty()) listOf(Layout(count, compact = true, oneColumn = true)) else emptyList()
            return listOf(Fit.Passes) + (every + oneColumn + rows.drop(every.size)).map(Fit::Rows)
        }

        /**
         * `lines(_:rows:headings:)`: the first `rows` parcels under their sections' headings, if the widget has room for
         * them, then a line that counts the others.
         */
        fun lines(sections: List<Section>, rows: Int, headings: Boolean): List<Line> {
            val lines = mutableListOf<Line>()
            var left = rows
            for (section in sections) {
                if (left <= 0) break
                if (headings && section.kind != null) lines += Line.Heading(section.kind, section.parcels.size)
                section.parcels.take(left).mapTo(lines) { Line.Row(it) }
                left -= minOf(left, section.parcels.size)
            }
            val more = sections.sumOf { it.parcels.size } - (rows - left)
            return if (more > 0) lines + Line.More(more) else lines
        }

        /**
         * `columns(_:)`: a medium widget's two columns, about as tall as each other, read down the first then the
         * second. A heading never ends the first; the line that counts the parcels left out always ends the second, as
         * they would follow its last row. A single row and its heading take one column.
         */
        fun columns(lines: List<Line>): List<List<Line>> {
            val total = lines.sumOf { it.weight }
            var split = 0
            var height = 0.0
            while (split < lines.size - 1 && height + lines[split].weight / 2 < total / 2) {
                height += lines[split].weight
                split++
            }
            if (split > 0 && lines[split - 1] is Line.Heading) split--
            return if (split == 0) listOf(lines) else listOf(lines.take(split), lines.drop(split))
        }

        fun make(date: Instant, enabled: Boolean, languageCode: String, snapshot: DeliveryWidgetSnapshot?, isSample: Boolean = false): DeliveryWidgetList {
            val shown = snapshot?.parcels.orEmpty().filter { DeliveryWidgetSelection.isShown(it, date) }.map { it.worded(date) }
            return DeliveryWidgetList(date, enabled, languageCode, shown, isSample = isSample)
        }
    }
}

/** `listName`: the name the list widget gives the parcel, its own, else its carrier's. */
val DeliveryWidgetParcel.listName: String
    get() = if (named == false) carrier else label

/**
 * `listLine.status`: the parcel's stage in the list's words; published by an app that didn't publish the stage, its
 * detail.
 */
val DeliveryWidgetParcel.listStatus: String
    get() = stage?.status ?: detail

/** `listLine.day`: the day the parcel is expected or arrived, when the app knows one. */
val DeliveryWidgetParcel.listDay: String?
    get() = stage?.let { detail.takeIf { it != stage.status } }

/** `ParcelListStatus.whole`: the stage, then the day. */
val DeliveryWidgetParcel.listLine: String
    get() = listDay?.let { "$listStatus · $it" } ?: listStatus

/** `openingLine(in:)`: the words as they open a line, where a day the app writes to follow a status, "tomorrow", takes a capital. */
fun String.openingLine(locale: Locale): String = take(1).uppercase(locale) + drop(1)

object DeliveryWidgetTimeline {
    /** `WidgetFamily.turnSize`: widgets of one size turn together; those of the other keep their own page. */
    fun turnSize(medium: Boolean): String = if (medium) "medium" else "small"

    /** The timeline's `.after(now + 20 min)`: the widget reads the store again that long after it last did. */
    const val refresh: Double = 20 * 60.0

    /**
     * When a widget is drawn again: the timeline's refresh, or sooner the end of a turn's spell, where a widget turned
     * to other parcels shows its first ones again, or a midnight where the parcels' days change ([DeliveryWidgetDays]),
     * as the timeline's later entries. `turns` are the turns of each size.
     */
    fun next(now: Instant, turns: List<DeliveryWidgetTurn?>, parcels: List<DeliveryWidgetParcel> = emptyList()): Instant {
        val refreshed = now.plusSeconds(refresh.toLong())
        val sooner = turns.filterNotNull().map { it.ends } + DeliveryWidgetDays.changes(parcels, after = now)
        return sooner.filter { it > now }.fold(refreshed) { soonest, at -> minOf(soonest, at) }
    }
}

/**
 * `DeliveryWidgetPictures`: how a card's pictures are named and kept. Each parcel's carrier mark, and its route as each
 * size of widget shows it, light and dark, at the screen's scale. A widget has the memory to show them, not to draw the
 * map.
 */
object DeliveryWidgetPictures {
    /** Changes whenever the pictures are drawn differently, so that the ones already drawn are drawn again. */
    const val version = 3

    /**
     * How a route is drawn for each size of widget. A route is no bigger than the smallest widget it goes in, which
     * shows it as wide as the card. `ceiling` and `floor` are where the card's top row ends and its words start, which
     * Pip stays between; `fade` is where the route fades in from under the top row, so that a line from far away does
     * not cross the mark.
     */
    data class Frame(val size: Size, val insets: EdgeInsets, val ceiling: Double, val floor: Double, val fade: ClosedFloatingPointRange<Double>)

    fun frame(of: DeliveryWidgetPicture): Frame = when (of) {
        DeliveryWidgetPicture.Half -> Frame(Size(162.0, 76.0), EdgeInsets(top = 30.0, leading = 12.0, bottom = 12.0, trailing = 12.0), 26.0, 70.0, 14.0..32.0)
        DeliveryWidgetPicture.Wide -> Frame(Size(364.0, 104.0), EdgeInsets(top = 36.0, leading = 16.0, bottom = 22.0, trailing = 16.0), 34.0, 96.0, 18.0..40.0)
        else -> Frame(Size(170.0, 104.0), EdgeInsets(top = 36.0, leading = 14.0, bottom = 22.0, trailing = 14.0), 34.0, 84.0, 18.0..40.0)
    }

    /**
     * A parcel's card, its pictures named after everything they show, so that a change to any of it draws them afresh:
     * the list's colours of the carrier it came with, after a handover too, and the parcel's status. `scale` is the
     * screen's pixels per point the pictures are drawn at.
     */
    fun card(parcel: Parcel, localizer: Localizer, scale: Double): DeliveryWidgetCard {
        val identity = CarrierVisualIdentity.of(parcel.displayedCarrier, language = localizer.language)
        val shown = listOf(
            "$version", parcel.id.uuidString, identity.name, identity.family, identity.colors.joinToString(","), localizer.language.rawValue,
            "$scale", parcel.currentStage?.rawValue ?: "", parcel.carrierData?.destinationCountry ?: "",
        ) + listOfNotNull(localizer.distanceUnit.takeIf { it == DistanceUnit.Miles }?.code) + parcel.trackingEvents.map { "${it.stage.rawValue} ${it.occurredAt} ${it.location ?: ""} ${it.place?.toString() ?: ""}" }
        val digest = MessageDigest.getInstance("SHA-256").digest(shown.joinToString("\n").toByteArray(Charsets.UTF_8))
            .take(8).joinToString("") { "%02x".format(it) }
        return DeliveryWidgetCard(
            surface = listOf(identity.colors[0], identity.colors[1]), ink = listOf(identity.colors[2], identity.colors[3]),
            status = localizer.parcelStatus(parcel), pictures = "${parcel.id.toString().lowercase()}-$digest", scale = scale,
        )
    }

    /** The mark is drawn last, so a card whose mark is there has all its pictures. */
    fun isDrawn(card: DeliveryWidgetCard, folder: File): Boolean =
        listOf(false, true).all { DeliveryWidgetPicture.Mark.file(card, it, folder).exists() }

    /** Removes the pictures of every card but `cards`. */
    fun sweep(keeping: List<DeliveryWidgetCard>, folder: File) {
        val kept = keeping.map { it.pictures }.toSet()
        for (file in folder.listFiles().orEmpty()) {
            val name = file.nameWithoutExtension
            if (kept.any { name.startsWith("$it-") }) continue
            file.delete()
        }
    }
}

/**
 * What draws the widget's cards (`DeliveryWidgetPictures.draw`), into the folder the widget reads them from
 * (`DeliveryWidgetPicture.folder`), at the screen's pixels per point (`DeliveryWidgetPictures.scale`). The store
 * names each card ([DeliveryWidgetPictures.card]) and has it drawn once; without one, the widget shows words alone.
 */
interface DeliveryWidgetArtist {
    val folder: File
    val scale: Double

    /** A card's pictures, the mark last; false when one could not be written. */
    suspend fun draw(parcel: Parcel, card: DeliveryWidgetCard, language: AppLanguage, distanceUnit: DistanceUnit = DistanceUnit.Kilometres): Boolean
}
