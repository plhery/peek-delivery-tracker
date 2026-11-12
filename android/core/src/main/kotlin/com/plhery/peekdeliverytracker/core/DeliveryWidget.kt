package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.Parcel
import java.io.File
import java.security.MessageDigest
import java.time.Instant
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
 * delivered more than a day ago, and how many parcels there are in all, those past the ones published too ([total]).
 */
data class DeliveryWidgetList(
    val date: Instant,
    val enabled: Boolean,
    val languageCode: String,
    val parcels: List<DeliveryWidgetParcel>,
    val total: Int,
    /** The sample's parcels, which the gallery shows. */
    val isSample: Boolean = false,
) {
    /** The rows a widget shows, and how many parcels its last line counts beyond them ("+N more"). */
    data class Shown(val rows: List<DeliveryWidgetParcel>, val more: Int)

    /**
     * What a widget with room for `rows` rows shows, or for `rowsBesideMore` beside the line counting the rest: every
     * parcel where they all fit, else as many as fit beside that line, at least one.
     */
    fun shown(rows: Int, rowsBesideMore: Int): Shown {
        if (total <= rows) return Shown(parcels, 0)
        val fitting = minOf(maxOf(rowsBesideMore, 1), parcels.size)
        return Shown(parcels.take(fitting), total - fitting)
    }

    companion object {
        /** `link`: the deliveries, which the last line opens ([NativeRoute.Parcels]). */
        const val link = "peekdeliverytracker://parcels"

        fun make(date: Instant, enabled: Boolean, languageCode: String, snapshot: DeliveryWidgetSnapshot?, isSample: Boolean = false): DeliveryWidgetList {
            val published = snapshot?.parcels.orEmpty()
            val shown = published.filter { DeliveryWidgetSelection.isShown(it, date) }
            val beyond = maxOf(0, (snapshot?.total ?: published.size) - published.size)
            return DeliveryWidgetList(date, enabled, languageCode, shown, total = shown.size + beyond, isSample = isSample)
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

object DeliveryWidgetTimeline {
    /** `WidgetFamily.turnSize`: widgets of one size turn together; those of the other keep their own page. */
    fun turnSize(medium: Boolean): String = if (medium) "medium" else "small"

    /** The timeline's `.after(now + 20 min)`: the widget reads the store again that long after it last did. */
    const val refresh: Double = 20 * 60.0

    /**
     * When a widget is drawn again: the timeline's refresh, or sooner the end of a turn's spell, where a widget turned
     * to other parcels shows its first ones again (the timeline's second entry). `turns` are the turns of each size.
     */
    fun next(now: Instant, turns: List<DeliveryWidgetTurn?>): Instant {
        val refreshed = now.plusSeconds(refresh.toLong())
        return turns.filterNotNull().map { it.ends }.filter { it > now }.fold(refreshed) { soonest, ends -> minOf(soonest, ends) }
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
