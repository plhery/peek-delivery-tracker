package com.plhery.peekdeliverytracker.widget

import android.os.SystemClock
import androidx.glance.appwidget.GlanceAppWidgetManager
import java.time.Duration
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * The widget gallery's pictures of the widget (Android 15 and later), which the system keeps until they are replaced
 * and takes only twice an hour (AppWidgetServiceImpl's limit on generated previews; a call it turns down doesn't
 * count). They show the reader's own cards once drawn, so they go at once when the store is cleared (signing out, an
 * account deleted, the widget switched off), and the sample follows. Otherwise a burst of the store's changes (the
 * snapshot, then each card's pictures as they are drawn) settles into one publication, one at a time, and only when
 * what they show changed. A publication turned down is tried again once the system's window has passed.
 */
internal class WidgetGalleryPreviews(
    private val scope: CoroutineScope,
    private val gallery: Gallery,
    private val settle: Duration = SETTLE,
    private val clock: () -> Long = SystemClock::elapsedRealtime,
) {
    /** What the pictures show (`key`), and whether that is the sample alone, nothing of the reader's. */
    data class Shows(val key: String, val sample: Boolean)

    /** The system's gallery as the app reaches it. */
    interface Gallery {
        /** What the pictures would show now. */
        fun shows(): Shows

        /** What the pictures the gallery holds show, as last published; null where it holds none of the app's. */
        var held: Shows?

        /** Publishes the pictures of what the widget shows now: one of GlanceAppWidgetManager's results. */
        suspend fun publish(): Int

        /** Takes the app's pictures out of the gallery, which shows the provider's preview layout again. */
        fun remove()

        /** Has [publish] called again `after` this long, should the app's process be gone by then. */
        fun retry(after: Duration)
    }

    private val lock = Mutex()
    private val queued = AtomicBoolean(false)

    /** The start of the system's window, as this process last saw it begin: the first publication it counted. */
    private var window: Long? = null

    /** What the widget shows changed. */
    fun request() {
        scope.launch {
            lock.withLock {
                quietly {
                    val held = gallery.held
                    if (held != null && !held.sample && gallery.shows().sample) {
                        gallery.remove()
                        gallery.held = null
                    }
                }
            }
        }
        if (!queued.compareAndSet(false, true)) return
        scope.launch {
            delay(settle.toMillis())
            queued.set(false)
            publish()
        }
    }

    /** Publishes the pictures where what they show changed. */
    suspend fun publish() = lock.withLock {
        quietly {
            val shows = gallery.shows()
            if (gallery.held != shows) published(shows, clock(), gallery.publish())
        }
    }

    private fun published(shows: Shows, now: Long, result: Int) {
        when (result) {
            GlanceAppWidgetManager.SET_WIDGET_PREVIEWS_RESULT_SUCCESS -> {
                gallery.held = shows
                if (window.let { it == null || now - it >= WINDOW.toMillis() }) window = now
            }
            GlanceAppWidgetManager.SET_WIDGET_PREVIEWS_RESULT_RATE_LIMITED -> {
                val ends = window?.let { Duration.ofMillis(it + WINDOW.toMillis() - now) }?.takeIf { !it.isNegative } ?: WINDOW
                gallery.retry(ends + MARGIN)
            }
        }
    }

    /** The gallery is the system's: what it turns down leaves the widget as it is. */
    private inline fun quietly(body: () -> Unit) {
        try {
            body()
        } catch (cancelled: CancellationException) {
            throw cancelled
        } catch (_: Exception) {
        }
    }

    companion object {
        /** How long the store's changes have to settle before the pictures are published. */
        val SETTLE: Duration = Duration.ofSeconds(10)

        /** The system's window, in which it takes two publications (`generated_preview_api_reset_interval_ms`). */
        val WINDOW: Duration = Duration.ofHours(1)

        /** Past the window's end, as the system starts it a moment after this process reads its clock. */
        val MARGIN: Duration = Duration.ofMinutes(1)
    }
}
