package com.plhery.peekdeliverytracker.ui

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.net.Uri
import android.os.SystemClock
import android.view.DragEvent
import android.view.Gravity
import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_ADD
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Text and links dragged onto the deliveries from elsewhere (A-G08, A-L09), as the system carries a drag from another
 * app: a small view the test lays over the window starts a global drag of a clip (`startDragAndDrop`) as a finger comes
 * down on it, and the finger, its touches sent as the screen's are through UiAutomation (which reaches the system's
 * drag window, as the app's own touches can't), moves a frame at a time onto the list, its empty state or the + button,
 * rests and lifts. A link dropped there opens Add filled with its address before its text, and text with its words;
 * an image, which never starts a drop there, and text with no number in it are turned away, which the drag's end tells
 * the view it came from. Signed in as the synthetic reader@example.com on fake deliveries ([ParcelWalk]), which reach no
 * server; each test keeps Zurich's time and the captures' moment, and takes its view away once its drag has ended.
 */
@RunWith(AndroidJUnit4::class)
class DropDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val demo: List<Parcel> = DemoCards.parcels()

    /** A-G08, A-L09: a link dropped on the list, with words of its own, opens Add with its address. */
    @Test
    fun aLinkDroppedOnTheListOpensAddWithItsAddress() {
        walk.show(demo)
        assertTrue("The list took the link", drag(link(), onto = middle(PARCEL_LIST)))
        assertAddOpensWith(ADDRESS)
        walk.shot("A-G08-link-on-the-list")
        closeAdd()
    }

    /** A-G08, A-L09: text dropped on the + opens Add with it. */
    @Test
    fun textDroppedOnThePlusOpensAddWithIt() {
        walk.show(demo)
        assertTrue("The + took the text", drag(ClipData.newPlainText("", TEXT), onto = middle(ADD_BUTTON)))
        assertAddOpensWith(TEXT)
        walk.shot("A-G08-text-on-the-plus")
        closeAdd()
    }

    /** A-G08, A-L09: the empty list takes a drop too, where its Add a parcel stands. */
    @Test
    fun aLinkDroppedOnTheEmptyListOpensAddWithItsAddress() {
        walk.show(emptyList())
        assertTrue("The empty list took the link", drag(link(), onto = middle(EMPTY_ADD)))
        assertAddOpensWith(ADDRESS)
        walk.shot("A-L09-link-on-the-empty-list")
        closeAdd()
    }

    /**
     * A-G08: an image dropped on the + and text with no number in it dropped on the list are turned away: the drag
     * ends untaken, and Add stays shut.
     */
    @Test
    fun anImageOrTextWithoutANumberIsTurnedAway() {
        walk.show(demo)
        val image = ClipData("", arrayOf("image/png"), ClipData.Item(Uri.parse("content://example.provider/1Z999AA10123456784")))
        assertFalse("The + took an image", drag(image, onto = middle(ADD_BUTTON)))
        assertFalse(walk.exists(ADD_PARCEL))
        assertNull(walk.onUi { walk.deliveries.addition })
        assertFalse("The list took words with no number", drag(ClipData.newPlainText("", "See you on Friday"), onto = middle(PARCEL_LIST)))
        assertFalse(walk.exists(ADD_PARCEL))
        assertNull(walk.onUi { walk.deliveries.addition })
    }

    /** A link with words of its own, as a browser drags one: the address first, then the words. */
    private fun link() = ClipData(
        "",
        arrayOf(ClipDescription.MIMETYPE_TEXT_URILIST, ClipDescription.MIMETYPE_TEXT_PLAIN),
        ClipData.Item("Track your parcel", null, Uri.parse(ADDRESS)),
    )

    /** The middle of the first part tagged `tag`, on the screen. */
    private fun middle(tag: String): Offset {
        val bounds = compose.onAllNodes(hasTestTag(tag)).onFirst().fetchSemanticsNode().boundsInWindow
        return windowOrigin() + bounds.center
    }

    /** Where the window's top-left corner stands on the screen. */
    private fun windowOrigin(): Offset = walk.onUi {
        val at = IntArray(2).also { compose.activity.window.decorView.getLocationOnScreen(it) }
        Offset(at[0].toFloat(), at[1].toFloat())
    }

    /**
     * `clip` dragged from a view laid over the window onto `onto`, a point on the screen: a finger down on the view
     * starts a global drag of it there, moves in 20 steps a frame apart onto the point, rests there eight frames and
     * lifts. The view stands a quarter of the window's width from its left, in the half of its height away from the
     * point, clear of the screen's edges and their gestures. Whether the window took the drop, as the drag's end tells
     * the view, once it has gone.
     */
    private fun drag(clip: ClipData, onto: Offset): Boolean {
        val started = CountDownLatch(1)
        val ended = CountDownLatch(1)
        val dropped = AtomicReference<Boolean?>(null)
        val began = AtomicReference<Boolean?>(null)
        val origin = windowOrigin()
        val source = walk.onUi {
            val activity = compose.activity
            val root = activity.window.decorView
            val content = activity.findViewById<ViewGroup>(android.R.id.content)
            val at = IntArray(2).also { content.getLocationInWindow(it) }
            val top = if (onto.y - origin.y < root.height / 2f) root.height * 7 / 10 else root.height * 3 / 10
            val padding = (12 * activity.resources.displayMetrics.density).toInt()
            TextView(activity).apply {
                text = SOURCE
                setTextColor(Color.BLACK)
                setBackgroundColor(Color.LTGRAY)
                setPadding(padding, padding, padding, padding)
                setOnTouchListener { view, event ->
                    if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                        began.set(view.startDragAndDrop(clip, View.DragShadowBuilder(view), null, View.DRAG_FLAG_GLOBAL))
                    }
                    true
                }
                setOnDragListener { _, event ->
                    when (event.action) {
                        DragEvent.ACTION_DRAG_STARTED -> started.countDown()
                        DragEvent.ACTION_DRAG_ENDED -> {
                            dropped.set(event.result)
                            ended.countDown()
                        }
                    }
                    true
                }
                val place = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP or Gravity.START)
                place.leftMargin = root.width / 4 - at[0]
                place.topMargin = top - at[1]
                activity.addContentView(this, place)
            }
        }
        try {
            compose.waitUntil(LONG_MILLIS) { walk.onUi { source.isLaidOut && source.width > 0 } }
            val from = walk.onUi {
                val at = IntArray(2).also { source.getLocationOnScreen(it) }
                Offset(at[0] + source.width / 2f, at[1] + source.height / 2f)
            }
            val finger = Finger(from)
            finger.send(MotionEvent.ACTION_DOWN)
            check(started.await(DRAG_SECONDS, TimeUnit.SECONDS)) { "No drag started (startDragAndDrop answered ${began.get()})" }
            for (step in 1..STEPS) {
                SystemClock.sleep(FRAME_MILLIS)
                finger.at = from + (onto - from) * (step / STEPS.toFloat())
                finger.send(MotionEvent.ACTION_MOVE)
            }
            repeat(8) {
                SystemClock.sleep(FRAME_MILLIS)
                finger.send(MotionEvent.ACTION_MOVE)
            }
            finger.send(MotionEvent.ACTION_UP)
            check(ended.await(DRAG_SECONDS, TimeUnit.SECONDS)) { "The drag didn't end" }
        } finally {
            walk.onUi { (source.parent as? ViewGroup)?.removeView(source) }
        }
        walk.settle()
        return dropped.get() == true
    }

    /** A finger on the screen at [at], its touches sent as the screen's are, through UiAutomation. */
    private inner class Finger(var at: Offset) {
        private val down = SystemClock.uptimeMillis()
        private val properties = MotionEvent.PointerProperties().apply {
            id = 0
            toolType = MotionEvent.TOOL_TYPE_FINGER
        }

        fun send(action: Int) {
            val where = MotionEvent.PointerCoords().apply {
                x = at.x
                y = at.y
                pressure = 1f
                size = 1f
            }
            val time = if (action == MotionEvent.ACTION_DOWN) down else SystemClock.uptimeMillis()
            val event = MotionEvent.obtain(
                down, time, action, 1, arrayOf(properties), arrayOf(where), 0, 0, 1f, 1f, 0, 0, InputDevice.SOURCE_TOUCHSCREEN, 0,
            )
            try {
                check(instrumentation.uiAutomation.injectInputEvent(event, true)) { "The screen refused the touch $event" }
            } finally {
                event.recycle()
            }
        }
    }

    /** Add open over the window, its field holding `text`. */
    private fun assertAddOpensWith(text: String) {
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        assertEquals(text, walk.editable(ADD_TRACKING))
        walk.awaitKeyboard(required = false)
    }

    /** Add closed from its Cancel, and gone. */
    private fun closeAdd() {
        walk.tap(ADD_CLOSE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) }
        assertNull(walk.onUi { walk.deliveries.addition })
    }

    private companion object {
        const val FOLDER = "dropwalk"
        const val ADDRESS = "https://example.com/track/1Z999AA10123456784"
        const val TEXT = "UPS 1Z999AA10123456784"

        /** What the view a drag starts from says. */
        const val SOURCE = "Drag"

        const val STEPS = 20
        const val FRAME_MILLIS = 16L

        /** How long a drag is given to start, and to end once lifted: a drop turned away first flies back. */
        const val DRAG_SECONDS = 5L
    }
}
