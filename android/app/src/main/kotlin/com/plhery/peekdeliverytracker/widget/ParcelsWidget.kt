package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import com.plhery.peekdeliverytracker.core.AppClock

/**
 * `ParcelsWidget`: the parcels on their way as a list on the home screen, next to [NextDeliveryWidget], in its order and
 * from what the app published for it (DeliveryWidgetSharedStore). One widget whose rows follow the size the launcher
 * gives it ([SizeMode.Exact]): two cells by two show three parcels, four by two six, four by four up to fifteen
 * ([ListLayout]). It is drawn again whenever the other one is ([DeliveryWidgetUpdates]).
 */
class ParcelsWidget : GlanceAppWidget() {
    override val sizeMode = SizeMode.Exact

    /** The gallery's pictures take declared sizes only: a four-by-two widget's on a phone, upright and on its side. */
    override val previewSizeMode = SizeMode.Responsive(PREVIEW_SIZES)

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val source = DeliveryWidgetSource.of(context)
        DeliveryWidgetUpdates.schedule(context, source.store)
        provideContent { ParcelsWidgetBody(source) }
    }

    /** The gallery's picture: the sample's parcels, never the reader's, in the reader's language. */
    override suspend fun providePreview(context: Context, widgetCategory: Int) {
        val source = DeliveryWidgetSource.of(context)
        provideContent {
            val list = remember { source.listSample(AppClock.now()) }
            ParcelsWidgetContent(list, LocalSize.current)
        }
    }

    companion object {
        val PREVIEW_SIZES = setOf(DpSize(300.dp, 200.dp), DpSize(420.dp, 160.dp))
    }
}

/** What a placed list shows, at the size the launcher gives it, read again from the store at each reload. */
@Composable
internal fun ParcelsWidgetBody(source: DeliveryWidgetSource) {
    val revision by DeliveryWidgetUpdates.revision.collectAsState()
    val list = remember(revision) { source.list(AppClock.now()) }
    ParcelsWidgetContent(list, LocalSize.current)
}

/** The list's receiver: the launcher's updates, and its gallery pictures tried again once the system takes them. */
class ParcelsWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ParcelsWidget()

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == DeliveryWidgetUpdates.PREVIEWS) {
            val pending = goAsync()
            DeliveryWidgetUpdates.retryPreviews(context, list = true) { pending.finish() }
        } else {
            super.onReceive(context, intent)
        }
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        DeliveryWidgetUpdates.cancel(context, gone = ParcelsWidgetReceiver::class.java)
    }
}
