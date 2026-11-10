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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.LocalSize
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import com.plhery.peekdeliverytracker.BuildConfig
import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetEntry
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetListSample
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSample
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetTimeline
import com.plhery.peekdeliverytracker.core.DeliveryWidgetTurn
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.WidgetCenter
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import java.io.File
import java.time.Duration
import java.time.Instant
import java.util.UUID
import kotlin.math.sqrt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * `NextDeliveryWidget`: the next deliveries on the home screen, in the app's language, as the app's cards once it has
 * drawn them. The iPhone's small and medium families are one widget here, whose layout follows the size the launcher
 * gives it in each orientation ([SizeMode.Exact]): one parcel (small), or, where the widget is wide for its height as
 * the iPhone's medium is, two side by side or one across (medium, [isMedium]). It reads what the app published
 * (DeliveryWidgetSharedStore) each time it is drawn, which [DeliveryWidgetUpdates] asks for.
 */
class NextDeliveryWidget : GlanceAppWidget() {
    override val sizeMode = SizeMode.Exact

    /** The gallery's pictures take declared sizes only: a two-by-two widget's on a phone, upright and on its side, and on a tablet. */
    override val previewSizeMode = SizeMode.Responsive(PREVIEW_SIZES)

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val source = DeliveryWidgetSource.of(context)
        DeliveryWidgetUpdates.schedule(context, source.store)
        provideContent { NextDeliveryWidgetBody(source) }
    }

    /**
     * `getSnapshot` for the gallery (Android 15's generated previews): the reader's own cards once the app has drawn
     * them, the demo's until then.
     */
    override suspend fun providePreview(context: Context, widgetCategory: Int) {
        val source = DeliveryWidgetSource.of(context)
        provideContent {
            val size = LocalSize.current
            val medium = isMedium(size)
            val entry = remember(medium) { source.preview(medium, AppClock.now()) }
            DeliveryWidgetContent(entry, size)
        }
    }

    companion object {
        /**
         * The sizes the gallery's pictures are drawn at, of which the launcher shows the one closest to its two-by-two
         * frame among those that fit in it, else the smallest. A phone's, upright and on its side: Launcher3's cells on
         * its reference phone grid. A tablet's: square, inside a tablet's frame either way up (220 × 309 and 304 × 233
         * on the reference tablet) and wider than a phone's (179 × 208 on the reference phone), so a tablet's picture
         * shows the one parcel its widget is placed with rather than a phone's on its side.
         */
        val PREVIEW_SIZES = setOf(DpSize(130.dp, 220.dp), DpSize(269.dp, 117.dp), DpSize(210.dp, 210.dp))

        /** The narrowest widget that shows two parcels side by side, two cards on their side on a phone. */
        private val MEDIUM_WIDTH = 250.dp

        /**
         * How much wider than tall a widget is from which it is medium: midway, in proportion, between the iPhone's
         * small widget (170 × 170) and its medium one (364 × 170).
         */
        private val MEDIUM_SHAPE = sqrt(364f / 170f)

        /** Whether a widget `size` big shows the iPhone's medium layout: wide enough, and wide for its height. */
        fun isMedium(size: DpSize): Boolean = size.width >= MEDIUM_WIDTH && size.width.value >= size.height.value * MEDIUM_SHAPE
    }
}

/** What a placed widget shows, at the size the launcher gives it, read again from the store at each reload. */
@Composable
internal fun NextDeliveryWidgetBody(source: DeliveryWidgetSource) {
    val revision by DeliveryWidgetUpdates.revision.collectAsState()
    val size = LocalSize.current
    val medium = NextDeliveryWidget.isMedium(size)
    val entry = remember(revision, medium) { source.current(medium, AppClock.now()) }
    DeliveryWidgetContent(entry, size)
}

/**
 * The widget's receiver: the launcher's updates, the refresh [DeliveryWidgetUpdates] sets an alarm for, and the
 * gallery's pictures tried again once the system takes them ([WidgetGalleryPreviews]). The refresh ends with the last
 * widget.
 */
class NextDeliveryWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = NextDeliveryWidget()

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            DeliveryWidgetUpdates.REFRESH -> {
                val pending = goAsync()
                DeliveryWidgetUpdates.reload(context) { pending.finish() }
            }
            DeliveryWidgetUpdates.PREVIEWS -> {
                val pending = goAsync()
                DeliveryWidgetUpdates.retryPreviews(context) { pending.finish() }
            }
            else -> super.onReceive(context, intent)
        }
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        DeliveryWidgetUpdates.cancel(context, gone = NextDeliveryWidgetReceiver::class.java)
    }
}

/**
 * `ShowNextParcelIntent`: turns the widgets of one size to the next parcels, draws them again and sets the timeline's
 * next entry for the turn's end, before the tap's work is done, as `perform` returns once WidgetKit has reloaded.
 */
class ShowNextParcelAction : ActionCallback {
    override suspend fun onAction(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        val parcel = parameters[PARCEL]?.let { runCatching { UUID.fromString(it) }.getOrNull() } ?: return
        val size = parameters[SIZE] ?: return
        DeliveryWidgetSource.of(context).store.setTurn(DeliveryWidgetTurn(parcel = parcel, at = AppClock.now()), size = size)
        DeliveryWidgetUpdates.update(context)
    }

    companion object {
        val PARCEL = ActionParameters.Key<String>("parcel")
        val SIZE = ActionParameters.Key<String>("size")

        fun parameters(parcel: UUID, size: String): ActionParameters = actionParametersOf(PARCEL to parcel.toString().uppercase(), SIZE to size)
    }
}

/**
 * `DeliveryWidgetProvider`: what the widget shows from what the app published (`store`, the app group's defaults, which
 * the widget, part of the app, reads directly) and the cards' pictures in `folder`, or the gallery's sample from the
 * app's assets.
 */
class DeliveryWidgetSource(
    private val context: Context,
    val store: DeliveryWidgetSharedStore,
    private val folder: File,
    private val preferredLanguages: () -> List<String> = Device::preferredLanguages,
) {
    /** `currentEntry`: the page the widget's size was turned to, else its first. */
    fun current(medium: Boolean, at: Instant): DeliveryWidgetEntry<WidgetCardArt> {
        val snapshot = store.snapshot
        val languageCode = snapshot?.languageCode ?: ExtensionLocalizer(store.languageCode, preferredLanguages()).languageCode
        val turn = store.turn(size = DeliveryWidgetTimeline.turnSize(medium), now = at)?.parcel
        return DeliveryWidgetEntry.make(at, store.isEnabled, languageCode, snapshot, medium, turn) { card, shape -> read(card, shape) }
    }

    /** What the list widget shows: the parcels the app published for it, in its order, those delivered over a day ago gone. */
    fun list(at: Instant): DeliveryWidgetList {
        val snapshot = store.snapshot
        val languageCode = snapshot?.languageCode ?: ExtensionLocalizer(store.languageCode, preferredLanguages()).languageCode
        return DeliveryWidgetList.make(at, store.isEnabled, languageCode, snapshot)
    }

    /** The list widget's sample (DeliveryWidgetListSample), in the reader's language, which its gallery picture shows. */
    fun listSample(at: Instant): DeliveryWidgetList {
        val languageCode = ExtensionLocalizer(store.languageCode, preferredLanguages()).languageCode
        val localizer = Localizer(AppLanguage.fromRawValue(languageCode) ?: AppLanguage.En)
        return DeliveryWidgetList.make(at, true, languageCode, DeliveryWidgetListSample.snapshot(localizer, at), isSample = true)
    }

    /** `placeholder`: the demo's next deliveries, as the widget shows them in the reader's language. */
    fun placeholder(medium: Boolean, at: Instant): DeliveryWidgetEntry<WidgetCardArt> {
        val languageCode = ExtensionLocalizer(store.languageCode, preferredLanguages()).languageCode
        val snapshot = sampleSnapshots()?.let { DeliveryWidgetSample.snapshot(languageCode, it) }
        return DeliveryWidgetEntry.make(at, true, languageCode, snapshot, medium) { card, shape -> sample(card, shape) }.copy(isSample = true)
    }

    /** `getSnapshot` in the gallery: the parcels' own cards once the app has drawn them, and the demo's until then. */
    fun preview(medium: Boolean, at: Instant): DeliveryWidgetEntry<WidgetCardArt> =
        current(medium, at).takeIf { it.art.isNotEmpty() } ?: placeholder(medium, at)

    /** `DeliveryWidgetCardArt`: the mark, and the route as `shape` shows it; null while the mark isn't there. */
    private fun read(card: DeliveryWidgetCard, shape: DeliveryWidgetPicture): WidgetCardArt? {
        fun image(picture: DeliveryWidgetPicture, dark: Boolean): Bitmap? = picture.file(card, dark, folder).let { file ->
            WidgetPictures.get(file.path) { if (file.exists()) BitmapFactory.decodeFile(file.path) else null }
        }
        return art(image(DeliveryWidgetPicture.Mark, false), image(DeliveryWidgetPicture.Mark, true), image(shape, false), image(shape, true))
    }

    private fun sample(card: DeliveryWidgetCard, shape: DeliveryWidgetPicture): WidgetCardArt? {
        fun image(picture: DeliveryWidgetPicture, dark: Boolean): Bitmap? {
            val name = "${DeliveryWidgetSample.folderName}/${picture.file(card, dark, File("")).name}"
            return WidgetPictures.get("assets:$name") { runCatching { context.assets.open(name).use(BitmapFactory::decodeStream) }.getOrNull() }
        }
        return art(image(DeliveryWidgetPicture.Mark, false), image(DeliveryWidgetPicture.Mark, true), image(shape, false), image(shape, true))
    }

    private fun art(lightMark: Bitmap?, darkMark: Bitmap?, lightRoute: Bitmap?, darkRoute: Bitmap?): WidgetCardArt? {
        if (lightMark == null || darkMark == null) return null
        return WidgetCardArt(lightMark to darkMark, if (lightRoute != null && darkRoute != null) lightRoute to darkRoute else null)
    }

    private fun sampleSnapshots(): ByteArray? =
        runCatching { context.assets.open("${DeliveryWidgetSample.folderName}/${DeliveryWidgetSample.snapshotsFile}").use { it.readBytes() } }.getOrNull()

    companion object {
        fun of(context: Context): DeliveryWidgetSource = DeliveryWidgetSource(
            context.applicationContext,
            DeliveryWidgetSharedStore(PreferencesDefaults.appGroup(context.applicationContext, BuildSettings.configuration.appGroupIdentifier)),
            WidgetCardPictures.folder(context.applicationContext),
        )
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
 * WidgetKit's reloads and timeline: the widgets, both kinds, drawn again when the app publishes ([GlanceWidgetCenter]),
 * when the next button turns them, and when their timeline would have WidgetKit ask again: 20 minutes on, or sooner
 * when a turn's spell ends. The alarm is set only while a widget of either kind is placed; it doesn't wake the phone, as
 * WidgetKit's timeline refreshes at its own pace, and without the exact alarm permission it comes in a window
 * ([window]).
 */
object DeliveryWidgetUpdates {
    const val REFRESH = "com.plhery.peekdeliverytracker.widget.REFRESH"
    const val PREVIEWS = "com.plhery.peekdeliverytracker.widget.PREVIEWS"

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val revisionState = MutableStateFlow(0)
    private var previews: WidgetGalleryPreviews? = null
    private var listPreviews: WidgetGalleryPreviews? = null

    /** Bumped at each reload: a widget being shown reads the store again. */
    val revision: StateFlow<Int> = revisionState.asStateFlow()

    /** Draws every widget again from the store, and sets the next refresh, once they are drawn. */
    suspend fun update(context: Context) {
        val app = context.applicationContext
        invalidate(app)
        NextDeliveryWidget().updateAll(app)
        ParcelsWidget().updateAll(app)
    }

    /** [update], from where nothing waits for it but `done`. */
    fun reload(context: Context, done: () -> Unit = {}) {
        val app = context.applicationContext
        invalidate(app)
        scope.launch {
            try {
                NextDeliveryWidget().updateAll(app)
                ParcelsWidget().updateAll(app)
            } finally {
                done()
            }
        }
    }

    private fun invalidate(context: Context) {
        revisionState.update { it + 1 }
        schedule(context, DeliveryWidgetSource.of(context).store)
    }

    /**
     * The timeline's next entry while a widget is `placed`: the refresh, or the end of a turn's spell where a widget was
     * turned to other parcels. With none placed, there is none.
     */
    fun schedule(context: Context, store: DeliveryWidgetSharedStore, now: Instant = AppClock.now(), placed: Boolean = isPlaced(context)) {
        val alarms = context.getSystemService(AlarmManager::class.java) ?: return
        if (!placed) {
            alarms.cancel(refresh(context))
            return
        }
        val turns = listOf(false, true).map { store.turn(size = DeliveryWidgetTimeline.turnSize(it), now = now) }
        val wait = maxOf(Duration.between(now, DeliveryWidgetTimeline.next(now, turns)), MIN_FUTURITY)
        alarms.setWindow(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + wait.toMillis(), window(wait).toMillis(), refresh(context))
    }

    /**
     * The narrowest window an alarm `wait` away is given without the exact alarm permission (AlarmManagerService's
     * minimum since Android 12): three quarters of the wait, ten minutes at most. A turn's end five minutes on comes
     * up to 3 min 45 s late, the refresh up to 10 minutes; and later still while the phone sleeps, as the alarm doesn't
     * wake it.
     */
    fun window(wait: Duration): Duration = minOf(wait.multipliedBy(3).dividedBy(4), MAX_WINDOW)

    /** No refresh once the last widget is gone: the last of `gone`'s kind, with none of the other kind placed. */
    fun cancel(context: Context, gone: Class<out GlanceAppWidgetReceiver>) {
        if (receivers.any { it != gone && isPlaced(context, it) }) return
        context.getSystemService(AlarmManager::class.java)?.cancel(refresh(context))
    }

    /** Whether a widget of either kind is on a home screen. */
    fun isPlaced(context: Context): Boolean = receivers.any { isPlaced(context, it) }

    private fun isPlaced(context: Context, receiver: Class<out GlanceAppWidgetReceiver>): Boolean {
        val widgets = context.getSystemService(AppWidgetManager::class.java) ?: return false
        return widgets.getAppWidgetIds(ComponentName(context, receiver)).isNotEmpty()
    }

    private val receivers = listOf(NextDeliveryWidgetReceiver::class.java, ParcelsWidgetReceiver::class.java)

    private fun refresh(context: Context): PendingIntent = PendingIntent.getBroadcast(
        context, 0, Intent(context, NextDeliveryWidgetReceiver::class.java).setAction(REFRESH),
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
    )

    /**
     * The gallery's pictures of the widgets, generated from their content (Android 15 and later) as WidgetKit asks
     * `getSnapshot` for them: in the app's language, with the reader's own cards once drawn ([WidgetGalleryPreviews]),
     * and the list's sample. Earlier Androids show the providers' preview layouts.
     */
    fun publishPreviews(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) return
        previews(context, list = false).request()
        previews(context, list = true).request()
    }

    /** One kind's gallery pictures (the list's, or the next delivery's), once the system's limit lets them through. */
    fun retryPreviews(context: Context, list: Boolean = false, done: () -> Unit) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) return done()
        scope.launch {
            try {
                previews(context, list).publish()
            } finally {
                done()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private fun previews(context: Context, list: Boolean): WidgetGalleryPreviews = synchronized(this) {
        val app = context.applicationContext
        if (list) {
            listPreviews ?: WidgetGalleryPreviews(scope, SystemGallery.list(app)).also { listPreviews = it }
        } else {
            previews ?: WidgetGalleryPreviews(scope, SystemGallery.next(app)).also { previews = it }
        }
    }

    /** What the gallery's pictures show: the app's version, and each size's language and cards; the sample or the reader's. */
    internal fun previewsShow(source: DeliveryWidgetSource, at: Instant): WidgetGalleryPreviews.Shows {
        val entries = listOf(false, true).map { medium -> source.preview(medium, at) }
        val key = (listOf("${BuildConfig.VERSION_CODE}") + entries.map { entry ->
            "${entry.languageCode} ${entry.isSample} " + entry.shown.joinToString(",") { it.card?.pictures ?: it.id.toString() }
        }).joinToString("\n")
        return WidgetGalleryPreviews.Shows(key, sample = entries.all { it.isSample })
    }

    /**
     * What the list's gallery pictures show: the sample alone, whatever the reader tracks, so they change with the
     * app's version and the reader's language only.
     */
    internal fun listPreviewsShow(source: DeliveryWidgetSource, at: Instant): WidgetGalleryPreviews.Shows =
        WidgetGalleryPreviews.Shows("${BuildConfig.VERSION_CODE}\n${source.listSample(at).languageCode}", sample = true)

    /**
     * The system's gallery for one kind of widget (its `receiver`), the pictures it holds kept in the app's defaults
     * under `key`, tried again with the request code `retryCode`.
     */
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private class SystemGallery(
        private val context: Context,
        private val receiver: Class<out GlanceAppWidgetReceiver>,
        private val key: String,
        private val sampleKey: String,
        private val retryCode: Int,
        private val show: (DeliveryWidgetSource, Instant) -> WidgetGalleryPreviews.Shows,
    ) : WidgetGalleryPreviews.Gallery {
        private val defaults = PreferencesDefaults.standard(context)
        private val provider = ComponentName(context, receiver)

        override fun shows(): WidgetGalleryPreviews.Shows = show(DeliveryWidgetSource.of(context), AppClock.now())

        override var held: WidgetGalleryPreviews.Shows?
            get() = defaults.string(key)?.let { WidgetGalleryPreviews.Shows(it, defaults.bool(sampleKey)) }
            set(value) {
                if (value == null) {
                    defaults.removeObject(key)
                    defaults.removeObject(sampleKey)
                } else {
                    defaults.set(value.key, forKey = key)
                    defaults.set(value.sample, forKey = sampleKey)
                }
            }

        override suspend fun publish(): Int =
            GlanceAppWidgetManager(context).setWidgetPreviews(receiver.kotlin, intSetOf(AppWidgetProviderInfo.WIDGET_CATEGORY_HOME_SCREEN))

        override fun remove() {
            context.getSystemService(AppWidgetManager::class.java)?.removeWidgetPreview(provider, AppWidgetProviderInfo.WIDGET_CATEGORY_HOME_SCREEN)
        }

        override fun retry(after: Duration) {
            val alarms = context.getSystemService(AlarmManager::class.java) ?: return
            val retry = PendingIntent.getBroadcast(
                context, retryCode, Intent(context, receiver).setAction(PREVIEWS),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
            )
            alarms.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + after.toMillis(), retry)
        }

        companion object {
            fun next(context: Context) = SystemGallery(context, NextDeliveryWidgetReceiver::class.java, PREVIEWS_KEY, PREVIEWS_SAMPLE_KEY, 1, ::previewsShow)

            fun list(context: Context) = SystemGallery(context, ParcelsWidgetReceiver::class.java, LIST_PREVIEWS_KEY, LIST_PREVIEWS_SAMPLE_KEY, 2, ::listPreviewsShow)
        }
    }

    /** AlarmManager's soonest for an app's alarm. */
    private val MIN_FUTURITY = Duration.ofSeconds(5)
    private val MAX_WINDOW = Duration.ofMinutes(10)
    private const val PREVIEWS_KEY = "sdt.deliveryWidget.previews.v1"
    private const val PREVIEWS_SAMPLE_KEY = "sdt.deliveryWidget.previewsSample.v1"
    private const val LIST_PREVIEWS_KEY = "sdt.deliveryListWidget.previews.v1"
    private const val LIST_PREVIEWS_SAMPLE_KEY = "sdt.deliveryListWidget.previewsSample.v1"
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
