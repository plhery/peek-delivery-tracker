package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

/**
 * `Modifier.stroke`, the centred line of `shape.stroke(...)`: the same line asked for again is the same modifier, and
 * its outline is made once for each size, however often its caller is composed and drawn anew (a card as it opens, the
 * sign-in art's tilt).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class StrokeTest {
    @get:Rule
    val compose = createComposeRule()

    /** A rectangle that counts the outlines it makes. */
    private class CountingShape : Shape {
        var made = 0

        override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
            made++
            return Outline.Rectangle(size.toRect())
        }
    }

    @Test
    fun theSameLineIsTheSameModifier() {
        val shape = ContinuousRoundedShape(22.dp)
        assertEquals(Modifier.stroke(1.dp, Color.Black, shape), Modifier.stroke(1.dp, Color.Black, ContinuousRoundedShape(22.dp)))
    }

    @Test
    fun theOutlineIsMadeOnceForEachSizeHoweverOftenItsCallerIsDrawnAnew() {
        val shape = CountingShape()
        var frame by mutableIntStateOf(0)
        var side by mutableStateOf(40.dp)
        compose.setContent {
            // Composed and drawn anew each frame, as a card is while it opens.
            Box(Modifier.size(side).background(Color(frame / 20f, 0f, 0f)).stroke(1.dp, Color.Black, shape))
        }
        compose.onRoot().captureToImage()
        assertEquals(1, shape.made)
        repeat(10) {
            frame++
            compose.onRoot().captureToImage()
        }
        assertEquals(1, shape.made)
        side = 60.dp
        compose.onRoot().captureToImage()
        assertEquals(2, shape.made)
    }
}
