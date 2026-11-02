package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.widget.FrameLayout
import android.widget.RemoteViews
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import androidx.glance.appwidget.GlanceRemoteViews
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetEntry
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPictures
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import java.io.File
import java.time.Instant
import java.util.UUID

// The widget's fixtures, which the JVM tests and the device's draw it with: synthetic parcels, the demo's next
// deliveries with the cards this app draws for them, and the widget's RemoteViews on a host as a launcher applies them.

/** The iPhone's small and medium widgets on its 390-point screen, which its gallery's sample was drawn for. */
val IPHONE_SMALL = DpSize(170.dp, 170.dp)
val IPHONE_MEDIUM = DpSize(364.dp, 170.dp)

/** A parcel as the widget shows it, `number` its id. */
fun widgetParcel(
    number: Int,
    label: String = "Parcel $number",
    carrier: String = "DHL",
    detail: String = "today",
    outForDelivery: Boolean = false,
    deliveredAt: Instant? = null,
    card: DeliveryWidgetCard? = null,
): DeliveryWidgetParcel = DeliveryWidgetParcel(
    id = UUID(0x1000, number.toLong()), label = label, carrier = carrier, trackingNumber = "WIDGET$number", detail = detail,
    isOutForDelivery = outForDelivery, deliveredAt = deliveredAt, card = card,
)

/** What a widget of one size shows of `parcels`, in words: no card has pictures. */
fun wordsEntry(
    parcels: List<DeliveryWidgetParcel>,
    medium: Boolean,
    enabled: Boolean = true,
    languageCode: String = "en",
    turn: UUID? = null,
): DeliveryWidgetEntry<WidgetCardArt> =
    DeliveryWidgetEntry.make(CAPTURED, enabled, languageCode, DeliveryWidgetSnapshot(CAPTURED, languageCode, parcels), medium, turn) { _, _ -> null }

/** The widget's source on a store in memory, its pictures in `folder`, the phone's languages `languages`. */
fun widgetSource(
    context: Context,
    folder: File,
    store: DeliveryWidgetSharedStore = DeliveryWidgetSharedStore(MemoryDefaults()),
    languages: List<String> = listOf("en"),
): DeliveryWidgetSource = DeliveryWidgetSource(context, store, folder) { languages }

/**
 * The demo's next deliveries in `language` at [CAPTURED] (of `parcels`, the demo's unless a test picks some), as the
 * store publishes them into `store`: the first `drawn` of them with their cards, which this app draws into the
 * source's folder at `scale` as it does for the widget (WidgetCardPictures).
 */
suspend fun publishDemo(
    context: Context,
    store: DeliveryWidgetSharedStore,
    folder: File,
    scale: Double,
    language: AppLanguage = AppLanguage.En,
    drawn: Int = 2,
    parcels: (List<Parcel>) -> List<Parcel> = { it },
): DeliveryWidgetSnapshot {
    val localizer = Localizer(language)
    val all = parcels(DemoCards.parcels(language))
    val artist = WidgetCardPictures(context, folder, scale)
    val shown = ParcelStore.widgetDeliveries(all, localizer, CAPTURED).mapIndexed { index, widget ->
        if (index >= drawn) return@mapIndexed widget
        val parcel = all.first { it.id == widget.id }
        val card = DeliveryWidgetPictures.card(parcel, localizer, scale)
        check(artist.draw(parcel, card, language)) { "${parcel.label}'s card wasn't drawn" }
        widget.copy(card = card)
    }
    val snapshot = DeliveryWidgetSnapshot(CAPTURED, language.rawValue, shown)
    check(store.save(snapshot))
    return snapshot
}

/** The widget's RemoteViews for `entry` at `size`, as Glance writes them for a launcher. */
@OptIn(ExperimentalGlanceRemoteViewsApi::class)
suspend fun widgetViews(context: Context, size: DpSize, entry: DeliveryWidgetEntry<WidgetCardArt>): RemoteViews =
    GlanceRemoteViews().compose(context, size) { DeliveryWidgetContent(entry, NextDeliveryWidget.isMedium(size)) }.remoteViews

/** `views` as a launcher shows them: applied in the host's window, `size` big, as a home screen's cells hold a widget. */
@Composable
fun WidgetOnHome(views: RemoteViews, size: DpSize) {
    AndroidView(factory = { context -> FrameLayout(context).apply { addView(views.apply(context, this)) } }, modifier = Modifier.size(size))
}
