package com.plhery.peekdeliverytracker.widget

import androidx.glance.appwidget.GlanceAppWidgetManager
import java.time.Duration
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * The widget gallery's pictures (C-W08): published one at a time, a burst of the store's changes as one; the reader's
 * own cards out of the gallery at once when the store is cleared, the sample after; a publication the system turns
 * down tried again once its window allows.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class WidgetGalleryPreviewsTest {
    private val readers = WidgetGalleryPreviews.Shows("own cards", sample = false)
    private val sample = WidgetGalleryPreviews.Shows("sample", sample = true)

    /** The system's gallery: what the widget shows, what the gallery holds, and the calls made to it. */
    private class Gallery(
        var showing: WidgetGalleryPreviews.Shows,
        private val clock: () -> Long,
        private val results: ArrayDeque<Int> = ArrayDeque(),
    ) : WidgetGalleryPreviews.Gallery {
        override var held: WidgetGalleryPreviews.Shows? = null
        val published = mutableListOf<WidgetGalleryPreviews.Shows>()
        val calls = mutableListOf<Long>()
        val retries = mutableListOf<Duration>()
        var removals = 0
        var overlapped = false
        private var publishing = false

        override fun shows() = showing

        /** Takes a moment, as composing the pictures does. */
        override suspend fun publish(): Int {
            val shown = showing
            if (publishing) overlapped = true
            publishing = true
            calls += clock()
            delay(100)
            publishing = false
            published += shown
            return results.removeFirstOrNull() ?: GlanceAppWidgetManager.SET_WIDGET_PREVIEWS_RESULT_SUCCESS
        }

        override fun remove() {
            removals += 1
        }

        override fun retry(after: Duration) {
            retries += after
        }
    }

    private fun TestScope.gallery(showing: WidgetGalleryPreviews.Shows, vararg results: Int) = Gallery(showing, { currentTime }, ArrayDeque(results.toList()))

    private fun TestScope.previews(gallery: Gallery) =
        WidgetGalleryPreviews(this, gallery, settle = Duration.ofSeconds(10), clock = { currentTime })

    @Test
    fun burstOfChangesMakesOnePublication() = runTest {
        val gallery = gallery(readers)
        val previews = previews(gallery)
        previews.request()
        advanceTimeBy(3_000)
        previews.request()
        advanceUntilIdle()
        assertEquals(listOf(readers), gallery.published)
        assertEquals(readers, gallery.held)

        // Nothing new: no call.
        previews.request()
        advanceUntilIdle()
        assertEquals(1, gallery.calls.size)

        // One at a time: a publication asked for while one is under way waits for it.
        gallery.showing = readers.copy(key = "other cards")
        previews.request()
        advanceTimeBy(10_050)
        gallery.showing = readers.copy(key = "third cards")
        launch(start = CoroutineStart.UNDISPATCHED) { previews.publish() }
        advanceUntilIdle()
        assertEquals(listOf("own cards", "other cards", "third cards"), gallery.published.map { it.key })
        assertFalse(gallery.overlapped)
    }

    @Test
    fun clearedStoreTakesTheReadersCardsOutAtOnce() = runTest {
        val gallery = gallery(readers)
        val previews = previews(gallery)
        previews.request()
        advanceUntilIdle()
        assertEquals(readers, gallery.held)

        // Signed out: the store cleared, the sample shows; the reader's cards go before the changes settle.
        gallery.showing = sample
        previews.request()
        runCurrent()
        assertEquals(1, gallery.removals)
        assertNull(gallery.held)
        assertEquals(listOf(readers), gallery.published)
        // The sample follows.
        advanceUntilIdle()
        assertEquals(listOf(readers, sample), gallery.published)
        assertEquals(sample, gallery.held)

        // The sample isn't taken out.
        previews.request()
        advanceUntilIdle()
        assertEquals(1, gallery.removals)
    }

    @Test
    fun publicationTurnedDownIsTriedAgainOnceTheWindowAllows() = runTest {
        val limited = GlanceAppWidgetManager.SET_WIDGET_PREVIEWS_RESULT_RATE_LIMITED
        val gallery = gallery(readers, GlanceAppWidgetManager.SET_WIDGET_PREVIEWS_RESULT_SUCCESS, limited, limited)
        val previews = previews(gallery)
        previews.request()
        advanceUntilIdle()
        val opened = gallery.calls.single()

        // Turned down 20 minutes into the window this app's publication opened: tried again once it has passed.
        advanceTimeBy(Duration.ofMinutes(20).toMillis())
        gallery.showing = sample
        previews.publish()
        assertEquals(readers, gallery.held)
        assertEquals(listOf(Duration.ofMillis(opened + Duration.ofHours(1).toMillis() - gallery.calls[1]) + WidgetGalleryPreviews.MARGIN), gallery.retries)

        // Where the app's process doesn't know the window, a whole one on.
        val restarted = previews(gallery)
        restarted.publish()
        assertEquals(Duration.ofHours(1) + WidgetGalleryPreviews.MARGIN, gallery.retries.last())

        // The retry's alarm: published.
        advanceTimeBy(Duration.ofHours(1).toMillis())
        restarted.publish()
        assertEquals(sample, gallery.held)
        assertEquals(2, gallery.retries.size)
    }
}
