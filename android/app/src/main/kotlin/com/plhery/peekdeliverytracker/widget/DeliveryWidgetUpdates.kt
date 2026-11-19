package com.plhery.peekdeliverytracker.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.SystemClock
import android.util.LruCache
import androidx.annotation.RequiresApi
import androidx.collection.intSetOf
import androidx.glance.appwidget.GlanceAppWidgetManager
import com.plhery.peekdeliverytracker.BuildConfig
import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetListSample
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetTimeline
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.WidgetCenter
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import java.io.File
import java.time.Duration
import java.time.Instant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * `DeliveryWidgetProvider`: what the widget shows from what the app published (`store`, the app group's defaults, which
 * the widget, part of the app, reads directly) and the cards' pictures in `folder`, or the gallery's sample.
 */
class DeliveryWidgetSource(
    private val context: Context,
    val store: DeliveryWidgetSharedStore,
    private val folder: File,
    private val preferredLanguages: () -> List<String> = Device::preferredLanguages,
) {
    /** What the list widget shows: the parcels the app published for it, in its order, those delivered over a day ago gone. */
    fun list(at: Instant): DeliveryWidgetList {
        val snapshot = store.snapshot
        val languageCode = snapshot?.languageCode ?: ExtensionLocalizer(store.languageCode, preferredLanguages()).languageCode
        return DeliveryWidgetList.make(at, languageCode, snapshot)
    }

    /** The list widget's sample (DeliveryWidgetListSample), in the reader's language, which its gallery picture shows. */
    fun listSample(at: Instant): DeliveryWidgetList {
        val languageCode = ExtensionLocalizer(store.languageCode, preferredLanguages()).languageCode
        val localizer = Localizer(AppLanguage.fromRawValue(languageCode) ?: AppLanguage.En)
        return DeliveryWidgetList.make(at, languageCode, DeliveryWidgetListSample.snapshot(localizer, at), isSample = true)
    }

    /** `DeliveryWidgetCardArt`: the mark, and the route as `shape` shows it; null while the mark isn't there. */
    internal fun read(card: DeliveryWidgetCard, shape: DeliveryWidgetPicture): WidgetCardArt? {
        fun image(picture: DeliveryWidgetPicture, dark: Boolean): Bitmap? = picture.file(card, dark, folder).let { file ->
            WidgetPictures.get(file.path) { if (file.exists()) BitmapFactory.decodeFile(file.path) else null }
        }
        return art(image(DeliveryWidgetPicture.Mark, false), image(DeliveryWidgetPicture.Mark, true), image(shape, false), image(shape, true))
    }

    private fun art(lightMark: Bitmap?, darkMark: Bitmap?, lightRoute: Bitmap?, darkRoute: Bitmap?): WidgetCardArt? {
        if (lightMark == null || darkMark == null) return null
        return WidgetCardArt(lightMark to darkMark, if (lightRoute != null && darkRoute != null) lightRoute to darkRoute else null)
    }

    companion object {
        fun of(context: Context): DeliveryWidgetSource = DeliveryWidgetSource(
            context.applicationContext,
            DeliveryWidgetSharedStore(PreferencesDefaults.appGroup(context.applicationContext, BuildSettings.configuration.appGroupIdentifier)),
            WidgetCardPictures.folder(context.applicationContext),
        )

        /**
         * A placed widget's source, its timeline's next entry set: away from the main thread, which Glance asks for a
         * widget's content on.
         */
        suspend fun placed(context: Context): DeliveryWidgetSource = withContext(Dispatchers.IO) {
            of(context).also { DeliveryWidgetUpdates.schedule(context, it.store) }
        }
    }
}

/**
 * The pictures the widget shows, decoded once and kept while the app runs: a picture's name changes with what it
 * shows, and the same picture is the same Bitmap in each size's layout, which the launcher then keeps once.
 */
internal object WidgetPictures {
    private val cache = object : LruCache<String, Bitmap>(16 * 1024 * 1024) {
        override fun sizeOf(key: String, value: Bitmap): Int = value.allocationByteCount
    }

    fun get(key: String, decode: () -> Bitmap?): Bitmap? = cache.get(key) ?: decode()?.also { cache.put(key, it) }
}

/**
 * WidgetKit's reloads and timeline: the widget drawn again when the app publishes ([GlanceWidgetCenter]) and when its
 * timeline would have WidgetKit ask again: 20 minutes on, or sooner when a parcel's day changes. The alarm is set only
 * while a widget is placed; it doesn't wake the phone, as WidgetKit's timeline refreshes at its own pace, and without
 * the exact alarm permission it comes in a window ([window]).
 */
object DeliveryWidgetUpdates {
    const val REFRESH = "com.plhery.peekdeliverytracker.widget.REFRESH"
    const val PREVIEWS = "com.plhery.peekdeliverytracker.widget.PREVIEWS"

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val revisionState = MutableStateFlow(0)
    private var previews: WidgetGalleryPreviews? = null

    /** Bumped at each reload: a widget being shown reads the store again. */
    val revision: StateFlow<Int> = revisionState.asStateFlow()

    /** Draws every widget again from the store, and sets the next refresh, once they are drawn. */
    suspend fun update(context: Context) {
        val app = context.applicationContext
        invalidate(app)
        ParcelsWidget.draw(app)
    }

    /** [update], from where nothing waits for it but `done`: off the main thread, which the app's store publishes on. */
    fun reload(context: Context, done: () -> Unit = {}) {
        val app = context.applicationContext
        scope.launch {
            try {
                update(app)
            } finally {
                done()
            }
        }
    }

    /** A widget shown reads the store again; and the next refresh. */
    private suspend fun invalidate(context: Context) {
        val source = DeliveryWidgetSource.of(context)
        val placed = isPlaced(context)
        revisionState.update { it + 1 }
        schedule(context, source.store, placed = placed)
    }

    /**
     * The timeline's next entry while a widget is `placed`: the refresh, or the midnight where a parcel's day changes
     * while the app stays closed. With none placed, there is none.
     */
    fun schedule(context: Context, store: DeliveryWidgetSharedStore, now: Instant = AppClock.now(), placed: Boolean = isPlaced(context)) {
        val alarms = context.getSystemService(AlarmManager::class.java) ?: return
        if (!placed) {
            alarms.cancel(refresh(context))
            return
        }
        val next = DeliveryWidgetTimeline.next(now, store.snapshot?.parcels.orEmpty())
        val wait = maxOf(Duration.between(now, next), MIN_FUTURITY)
        alarms.setWindow(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + wait.toMillis(), window(wait).toMillis(), refresh(context))
    }

    /**
     * The narrowest window an alarm `wait` away is given without the exact alarm permission (AlarmManagerService's
     * minimum since Android 12): three quarters of the wait, ten minutes at most. A midnight five minutes on comes up
     * to 3 min 45 s late, the refresh up to 10 minutes; and later still while the phone sleeps, as the alarm doesn't
     * wake it.
     */
    fun window(wait: Duration): Duration = minOf(wait.multipliedBy(3).dividedBy(4), MAX_WINDOW)

    /** No refresh once the last widget is gone. */
    fun cancel(context: Context) {
        context.getSystemService(AlarmManager::class.java)?.cancel(refresh(context))
    }

    /** Whether a widget is on a home screen. */
    fun isPlaced(context: Context): Boolean = placedIds(context).isNotEmpty()

    internal fun placedIds(context: Context, excluding: Class<*>? = null): List<Int> {
        val widgets = context.getSystemService(AppWidgetManager::class.java) ?: return emptyList()
        return listOf(NextDeliveryWidgetReceiver::class.java, ParcelsWidgetReceiver::class.java)
            .filter { it != excluding }
            .flatMap { widgets.getAppWidgetIds(ComponentName(context, it)).asList() }
    }

    private fun refresh(context: Context): PendingIntent = PendingIntent.getBroadcast(
        context, 0, Intent(context, NextDeliveryWidgetReceiver::class.java).setAction(REFRESH),
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
    )

    /**
     * The gallery's pictures of the widget, generated from its content (Android 15 and later) as WidgetKit asks
     * `getSnapshot` for them: the list's sample in the app's language ([WidgetGalleryPreviews]). Earlier Androids show
     * the provider's preview layout.
     */
    fun publishPreviews(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) return
        previews(context).request()
    }

    /** The gallery's pictures, once the system's limit lets them through. */
    fun retryPreviews(context: Context, done: () -> Unit) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) return done()
        scope.launch {
            try {
                previews(context).publish()
            } finally {
                done()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private fun previews(context: Context): WidgetGalleryPreviews = synchronized(this) {
        previews ?: WidgetGalleryPreviews(scope, SystemGallery(context.applicationContext)).also { previews = it }
    }

    /**
     * What the gallery's pictures show: the sample alone, whatever the reader tracks, so they change with the app's
     * version and the reader's language only.
     */
    internal fun previewsShow(source: DeliveryWidgetSource, at: Instant): WidgetGalleryPreviews.Shows =
        WidgetGalleryPreviews.Shows("${BuildConfig.VERSION_CODE}\n${source.listSample(at).languageCode}", sample = true)

    /** The system's gallery, the pictures it holds kept in the app's defaults. */
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private class SystemGallery(private val context: Context) : WidgetGalleryPreviews.Gallery {
        private val defaults = PreferencesDefaults.standard(context)
        private val provider = ComponentName(context, NextDeliveryWidgetReceiver::class.java)

        override fun shows(): WidgetGalleryPreviews.Shows = previewsShow(DeliveryWidgetSource.of(context), AppClock.now())

        override var held: WidgetGalleryPreviews.Shows?
            get() = defaults.string(PREVIEWS_KEY)?.let { WidgetGalleryPreviews.Shows(it, defaults.bool(PREVIEWS_SAMPLE_KEY)) }
            set(value) {
                if (value == null) {
                    defaults.removeObject(PREVIEWS_KEY)
                    defaults.removeObject(PREVIEWS_SAMPLE_KEY)
                } else {
                    defaults.set(value.key, forKey = PREVIEWS_KEY)
                    defaults.set(value.sample, forKey = PREVIEWS_SAMPLE_KEY)
                }
            }

        override suspend fun publish(): Int =
            GlanceAppWidgetManager(context).setWidgetPreviews(NextDeliveryWidgetReceiver::class, intSetOf(AppWidgetProviderInfo.WIDGET_CATEGORY_HOME_SCREEN))

        override fun remove() {
            context.getSystemService(AppWidgetManager::class.java)?.removeWidgetPreview(provider, AppWidgetProviderInfo.WIDGET_CATEGORY_HOME_SCREEN)
        }

        override fun retry(after: Duration) {
            val alarms = context.getSystemService(AlarmManager::class.java) ?: return
            val retry = PendingIntent.getBroadcast(
                context, 1, Intent(context, NextDeliveryWidgetReceiver::class.java).setAction(PREVIEWS),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
            )
            alarms.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + after.toMillis(), retry)
        }
    }

    /** AlarmManager's soonest for an app's alarm. */
    private val MIN_FUTURITY = Duration.ofSeconds(5)
    private val MAX_WINDOW = Duration.ofMinutes(10)
    private const val PREVIEWS_KEY = "sdt.deliveryWidget.previews.v1"
    private const val PREVIEWS_SAMPLE_KEY = "sdt.deliveryWidget.previewsSample.v1"
}

/**
 * WidgetKit's `WidgetCenter.shared`: what the app published changed, so the widgets read it again, and the gallery's
 * pictures follow it.
 */
class GlanceWidgetCenter(private val context: Context) : WidgetCenter {
    override fun reloadTimelines(ofKind: String) {
        if (ofKind != DeliveryWidgetSharedStore.kind) return
        DeliveryWidgetUpdates.reload(context)
        DeliveryWidgetUpdates.publishPreviews(context)
    }
}
