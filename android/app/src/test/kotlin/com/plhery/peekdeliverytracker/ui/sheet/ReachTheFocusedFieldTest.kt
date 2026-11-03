package com.plhery.peekdeliverytracker.ui.sheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The scroll toward the focused field goes the whole way, wherever the keyboard's room under the content comes in the
 * frames: laid out before the scroll's last frame hands back (the end has moved by the time it looks), after it, or
 * never, at the top.
 */
class ReachTheFocusedFieldTest {
    /** A scroll from 0 to [end], the field in its place at [target]; [layout] runs as a scroll's last frame ends. */
    private class Page(var target: Int, end: Int) {
        var value by mutableIntStateOf(0)
        var end by mutableIntStateOf(end)
        var layout: () -> Unit = {}

        fun scrollBy(delta: Float): Float {
            val next = (value + delta.toInt()).coerceIn(0, end)
            val moved = next - value
            value = next
            layout()
            return moved.toFloat()
        }

        suspend fun reach() = reachTheFocusedField({ (target - value).toFloat() }, ::scrollBy, { value < end })
    }

    @Test
    fun theRoomLaidOutBeforeTheScrollHandsBack() = runTest {
        val page = Page(target = 500, end = 86)
        page.layout = { page.end = 862 }
        withTimeout(1_000) { page.reach() }
        assertEquals(500, page.value)
    }

    @Test
    fun theRoomLaidOutAfter() = runTest {
        val page = Page(target = 500, end = 86)
        val job = launch { page.reach() }
        runCurrent()
        assertEquals("Stopped at the end, waiting", 86, page.value)
        assertTrue(job.isActive)
        page.end = 862
        Snapshot.sendApplyNotifications()
        runCurrent()
        assertEquals(500, page.value)
        assertFalse(job.isActive)
    }

    @Test
    fun upAtTheTopItStays() = runTest {
        val page = Page(target = -40, end = 862)
        withTimeout(1_000) { page.reach() }
        assertEquals(0, page.value)
    }

    @Test
    fun aFieldInItsPlaceDoesNotScroll() = runTest {
        val page = Page(target = 0, end = 862)
        var scrolls = 0
        page.layout = { scrolls++ }
        page.reach()
        assertEquals(0, scrolls)
    }
}
