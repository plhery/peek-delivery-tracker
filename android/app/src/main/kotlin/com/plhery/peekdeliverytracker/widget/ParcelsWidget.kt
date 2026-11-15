package com.plhery.peekdeliverytracker.widget

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.SizeF
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.compose
import androidx.glance.appwidget.provideContent
import com.plhery.peekdeliverytracker.core.AppClock
import kotlin.math.floor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * `ParcelsWidget`: the parcels on their way on the home screen, next to [NextDeliveryWidget], in its order and from what
 * the app published for it (DeliveryWidgetSharedStore). One widget whose design follows how many parcels there are for
 * the size the launcher gives it ([SizeMode.Exact], [DeliveryWidgetList.design]): cards, passes, or rows under the app
 * list's sections, as many as fit at every size ([SectionsLayout]).
 * It is drawn again whenever the other one is ([DeliveryWidgetUpdates]), by [draw] rather than Glance's sessions.
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

        /** How much smaller than the launcher says, past its whole dp, each size is drawn ([fitting]). */
        const val SLACK = 2f

        /**
         * Draws each placed list (or `id` with `options`): Glance's [compose] at the launcher's sizes taken in by
         * [fitting], then the launcher's update. Glance's sessions key each layout by the size the launcher reports,
         * and RemoteViews shows the closest layout that fits the widget's frame; a launcher can frame a widget a little
         * smaller than it reports (a Pixel's two by two: reported 210.3 dp tall, framed 208.8 dp), and its layout then
         * gives way to another screen's, shorter one, which leaves a row's room blank.
         */
        suspend fun draw(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            for (id in manager.getAppWidgetIds(ComponentName(context, ParcelsWidgetReceiver::class.java))) {
                draw(context, id, manager.getAppWidgetOptions(id))
            }
        }

        /** A widget removed meanwhile is left alone: Glance no longer takes its id. */
        suspend fun draw(context: Context, id: Int, options: Bundle) {
            val glanceId = try {
                GlanceAppWidgetManager(context).getGlanceIdBy(id)
            } catch (_: IllegalArgumentException) {
                return
            }
            AppWidgetManager.getInstance(context).updateAppWidget(id, ParcelsWidget().compose(context, glanceId, fitting(options)))
        }

        /**
         * `options` with each size the launcher gives (and, where it gives none, its least and most width and height)
         * cut to its whole dp and [SLACK] under, so each layout fits the frame it stands for and RemoteViews shows it.
         */
        fun fitting(options: Bundle): Bundle = Bundle(options).apply {
            @Suppress("DEPRECATION")
            options.getParcelableArrayList<SizeF>(AppWidgetManager.OPTION_APPWIDGET_SIZES)?.let { sizes ->
                putParcelableArrayList(AppWidgetManager.OPTION_APPWIDGET_SIZES, ArrayList(sizes.map { SizeF(inside(it.width), inside(it.height)) }.distinct()))
            }
            for (key in listOf(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT, AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH, AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT)) {
                if (options.containsKey(key)) putInt(key, inside(options.getInt(key).toFloat()).toInt())
            }
        }

        private fun inside(dp: Float): Float = maxOf(floor(dp) - SLACK, 1f)
    }
}

/** What a placed list shows, at the size the launcher gives it, read again from the store at each reload. */
@Composable
internal fun ParcelsWidgetBody(source: DeliveryWidgetSource) {
    val revision by DeliveryWidgetUpdates.revision.collectAsState()
    val list = remember(revision) { source.list(AppClock.now()) }
    ParcelsWidgetContent(list, LocalSize.current, source::read)
}

/**
 * The list's receiver: the launcher's updates and new sizes, each list drawn by [ParcelsWidget.draw], and its gallery
 * pictures tried again once the system takes them. Glance's own handlers aren't called: they start the session that
 * draws at the sizes the launcher reports, which would draw over [ParcelsWidget.draw]'s; the list's taps open the app
 * ([openParcel], [openList]) and need no session.
 */
class ParcelsWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ParcelsWidget()

    @SuppressLint("MissingSuperCall")
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        drawn(context) { app -> appWidgetIds.forEach { ParcelsWidget.draw(app, it, appWidgetManager.getAppWidgetOptions(it)) } }
    }

    @SuppressLint("MissingSuperCall")
    override fun onAppWidgetOptionsChanged(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int, newOptions: Bundle) {
        drawn(context) { app -> ParcelsWidget.draw(app, appWidgetId, newOptions) }
    }

    private fun drawn(context: Context, draw: suspend (Context) -> Unit) {
        val pending: BroadcastReceiver.PendingResult? = goAsync()
        val app = context.applicationContext
        CoroutineScope(Dispatchers.Default).launch {
            try {
                draw(app)
            } finally {
                pending?.finish()
            }
        }
    }

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
