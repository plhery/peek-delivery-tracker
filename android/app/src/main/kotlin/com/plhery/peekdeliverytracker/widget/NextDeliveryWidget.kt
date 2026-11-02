package com.plhery.peekdeliverytracker.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetProviderInfo
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.LruCache
import androidx.collection.intSetOf
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
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetEntry
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSample
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetTimeline
import com.plhery.peekdeliverytracker.core.DeliveryWidgetTurn
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.WidgetCenter
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import java.io.File
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * `NextDeliveryWidget`: the next deliveries on the home screen, in the app's language, as the app's cards once it has
 * drawn them. The iPhone's small and medium families are one widget here, whose layout follows its size: below
 * [MEDIUM]'s width it shows one parcel (small), from it two side by side or one across (medium); a widget between the
 * two sizes shows the small layout stretched to it. It reads what the app published (DeliveryWidgetSharedStore) each
 * time it is drawn, which [DeliveryWidgetUpdates] asks for.
 */
class NextDeliveryWidget : GlanceAppWidget() {
    override val sizeMode = SizeMode.Responsive(setOf(SMALL, MEDIUM))
    override val previewSizeMode = SizeMode.Responsive(setOf(SMALL, MEDIUM))

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val source = DeliveryWidgetSource.of(context)
        DeliveryWidgetUpdates.schedule(context, source.store)
        provideContent {
            val revision by DeliveryWidgetUpdates.revision.collectAsState()
            val medium = isMedium(LocalSize.current)
            val entry = remember(revision, medium) { source.current(medium, Instant.now()) }
            DeliveryWidgetContent(entry, medium)
        }
    }

    /**
     * `getSnapshot` for the gallery (Android 15's generated previews): the reader's own cards once the app has drawn
     * them, the demo's until then.
     */
    override suspend fun providePreview(context: Context, widgetCategory: Int) {
        val source = DeliveryWidgetSource.of(context)
        provideContent {
            val medium = isMedium(LocalSize.current)
            val entry = remember(medium) { source.preview(medium, Instant.now()) }
            DeliveryWidgetContent(entry, medium)
        }
    }

    companion object {
        /** The smallest widget, two cells by two, and the smallest that shows two parcels side by side. */
        val SMALL = DpSize(110.dp, 110.dp)
        val MEDIUM = DpSize(270.dp, 110.dp)

        fun isMedium(size: DpSize): Boolean = size.width >= MEDIUM.width
    }
}

/** The widget's receiver: the launcher's updates, and the refresh [DeliveryWidgetUpdates] sets an alarm for. */
class NextDeliveryWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = NextDeliveryWidget()

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == DeliveryWidgetUpdates.REFRESH) {
            val pending = goAsync()
            DeliveryWidgetUpdates.reload(context) { pending.finish() }
            return
        }
        super.onReceive(context, intent)
    }
}

/** `ShowNextParcelIntent`: turns the widgets of one size to the next parcels, then draws them again. */
class ShowNextParcelAction : ActionCallback {
    override suspend fun onAction(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        val parcel = parameters[PARCEL]?.let { runCatching { UUID.fromString(it) }.getOrNull() } ?: return
        val size = parameters[SIZE] ?: return
        DeliveryWidgetSource.of(context).store.setTurn(DeliveryWidgetTurn(parcel = parcel, at = Instant.now()), size = size)
        DeliveryWidgetUpdates.reload(context)
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
 * WidgetKit's reloads and timeline: the widgets drawn again when the app publishes ([GlanceWidgetCenter]), when the
 * next button turns them, and when their timeline would have WidgetKit ask again: 20 minutes on, or sooner when a
 * turn's spell ends. The alarm is inexact and doesn't wake the phone, as WidgetKit's timeline refreshes at its own
 * pace.
 */
object DeliveryWidgetUpdates {
    const val REFRESH = "com.plhery.peekdeliverytracker.widget.REFRESH"

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val revisionState = MutableStateFlow(0)

    /** Bumped at each reload: a widget being shown reads the store again. */
    val revision: StateFlow<Int> = revisionState.asStateFlow()

    /** Draws every widget again from the store, and sets the next refresh. */
    fun reload(context: Context, done: () -> Unit = {}) {
        val app = context.applicationContext
        revisionState.value += 1
        schedule(app, DeliveryWidgetSource.of(app).store)
        scope.launch {
            try {
                NextDeliveryWidget().updateAll(app)
            } finally {
                done()
            }
        }
    }

    /** The timeline's next entry: the refresh, or the end of a turn's spell where a widget was turned to other parcels. */
    fun schedule(context: Context, store: DeliveryWidgetSharedStore, now: Instant = Instant.now()) {
        val turns = listOf(false, true).map { store.turn(size = DeliveryWidgetTimeline.turnSize(it), now = now) }
        val at = DeliveryWidgetTimeline.next(now, turns)
        val alarms = context.getSystemService(AlarmManager::class.java) ?: return
        alarms.set(AlarmManager.RTC, at.toEpochMilli(), refresh(context))
    }

    private fun refresh(context: Context): PendingIntent = PendingIntent.getBroadcast(
        context, 0, Intent(context, NextDeliveryWidgetReceiver::class.java).setAction(REFRESH),
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
    )

    /**
     * The gallery's pictures of the widget, generated from its content (Android 15 and later) as WidgetKit asks
     * `getSnapshot` for them: in the app's language, with the reader's own cards once drawn. The system takes only a
     * few a day, so they are published again only when what they show changes ([previewsKey]); earlier Androids show
     * the provider's preview layout.
     */
    fun publishPreviews(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) return
        val app = context.applicationContext
        scope.launch {
            runCatching {
                val key = previewsKey(DeliveryWidgetSource.of(app), Instant.now())
                val defaults = PreferencesDefaults.standard(app)
                if (defaults.string(PREVIEWS_KEY) == key) return@runCatching
                val result = GlanceAppWidgetManager(app)
                    .setWidgetPreviews(NextDeliveryWidgetReceiver::class, intSetOf(AppWidgetProviderInfo.WIDGET_CATEGORY_HOME_SCREEN))
                if (result == GlanceAppWidgetManager.SET_WIDGET_PREVIEWS_RESULT_SUCCESS) defaults.set(key, forKey = PREVIEWS_KEY)
            }
        }
    }

    /** What the gallery's pictures show: the app's version, and each size's language and cards. */
    internal fun previewsKey(source: DeliveryWidgetSource, at: Instant): String =
        (listOf("${BuildConfig.VERSION_CODE}") + listOf(false, true).map { medium ->
            val entry = source.preview(medium, at)
            "${entry.languageCode} ${entry.isSample} " + entry.shown.joinToString(",") { it.card?.pictures ?: it.id.toString() }
        }).joinToString("\n")

    private const val PREVIEWS_KEY = "sdt.deliveryWidget.previews.v1"
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
