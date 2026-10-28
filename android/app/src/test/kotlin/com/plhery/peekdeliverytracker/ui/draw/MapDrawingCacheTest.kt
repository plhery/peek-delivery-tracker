package com.plhery.peekdeliverytracker.ui.draw

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.MapPath
import com.plhery.peekdeliverytracker.core.StrokeStyle
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

/** A drawing drawn again at each frame finds its Compose paths again by what its paths hold. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class MapDrawingCacheTest {
    private fun square(side: Double) = MapPath().apply {
        move(0.0, 0.0)
        addLine(side, 0.0)
        addLine(side, side)
        addLine(0.0, side)
        closeSubpath()
    }

    @Test
    fun theNextFrameFindsItsShapesAgainAndLetsGoOfThoseItNoLongerDraws() {
        val cache = MapDrawingCache()
        val small = cache.path(square(2.0), evenOdd = false)
        val large = cache.path(square(3.0), evenOdd = false)
        val stroke = cache.stroke(StrokeStyle(1.0, dash = listOf(3.0, 3.0)))
        cache.endDrawing()
        // :core records each frame's paths anew.
        assertSame(small, cache.path(square(2.0), evenOdd = false))
        assertNotSame(small, cache.path(square(2.0), evenOdd = true))
        assertSame(stroke, cache.stroke(StrokeStyle(1.0, dash = listOf(3.0, 3.0))))
        cache.endDrawing()
        // The large square went unused a frame: drawn again, it is built again.
        assertNotSame(large, cache.path(square(3.0), evenOdd = false))
        assertSame(small, cache.path(square(2.0), evenOdd = false))
    }
}
