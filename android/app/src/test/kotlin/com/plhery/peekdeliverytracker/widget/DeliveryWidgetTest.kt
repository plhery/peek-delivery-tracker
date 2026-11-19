package com.plhery.peekdeliverytracker.widget

import android.app.AlarmManager
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Looper
import android.os.SystemClock
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.ExperimentalGlanceApi
import androidx.glance.appwidget.runComposition
import androidx.glance.appwidget.testing.unit.hasStartActivityClickAction
import androidx.glance.appwidget.testing.unit.runGlanceAppWidgetUnitTest
import androidx.glance.testing.unit.hasAnyDescendant
import androidx.glance.testing.unit.hasTextEqualTo
import androidx.glance.text.FontWeight
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPictures
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.core.DeliveryWidgetWording
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import java.time.Duration
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The cards the Parcels widget shows for its fewest parcels (C-S48, C-W06, C-W07, C-W10), as Glance composes them,
 * without a launcher: what they show, where a tap goes and what TalkBack reads; the widget's refresh (C-W01, C-W03); and
 * the pictures the app draws for its cards (C-W04, C-W24, A-W09). DeliveryWidgetCards.swift is the spec.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w390dp-h844dp-xhdpi")
class DeliveryWidgetTest {
    @get:Rule
    val zurich = ZurichTime()

    @get:Rule
    val files = TemporaryFolder()

    private val app = ApplicationProvider.getApplicationContext<Context>()

    private fun opens(parcel: DeliveryWidgetParcel) =
        hasStartActivityClickAction(Intent(Intent.ACTION_VIEW, Uri.parse(parcel.deepLink), app, MainActivity::class.java))

    /** The Parcels widget showing `list` at `size`, every card's pictures drawn ([art]). */
    private fun show(list: DeliveryWidgetList, size: DpSize, test: androidx.glance.appwidget.testing.unit.GlanceAppWidgetUnitTest.() -> Unit) =
        runGlanceAppWidgetUnitTest {
            setContext(app)
            setAppWidgetSize(size)
            provideComposable { ParcelsWidgetContent(list, size) { _, _ -> art } }
            test()
        }

    /** A picture of nothing, which stands for a card's. */
    private val blank: Bitmap = Bitmap.createBitmap(4, 4, Bitmap.Config.ARGB_8888)
    private val art = WidgetCardArt(blank to blank, blank to blank)

    private fun card(name: String) = DeliveryWidgetCard(listOf("#e6deef", "#292235"), listOf("#23093f", "#c1add7"), "Out for delivery", name, 2.0)

    /** The list of `parcels`, as the app publishes them. */
    private fun cards(parcels: List<DeliveryWidgetParcel>): DeliveryWidgetList =
        DeliveryWidgetList.make(CAPTURED, "en", DeliveryWidgetSnapshot(CAPTURED, "en", parcels))

    private val three = listOf(
        widgetParcel(1, "Belgian chocolate", carrier = "FedEx", outForDelivery = true),
        widgetParcel(2, "Trail weekend kit", carrier = "DPD Switzerland", detail = "tomorrow"),
        widgetParcel(3, "Desk lamp", detail = "Friday"),
    )

    /**
     * `DeliveryWidgetCardView` (C-S48, C-W07): once every card shown has its pictures, the cards, the name and the status
     * line; one card opens its parcel, and of two each opens its own.
     */
    @Test
    fun cardsShowOnceTheirPicturesAreDrawn() {
        val carded = three.map { it.copy(card = card(it.id.toString())) }
        show(cards(carded.take(1)), IPHONE_SMALL) {
            onNode(hasTextEqualTo("Belgian chocolate")).assertExists()
            onNode(hasTextEqualTo("Out for delivery · today")).assertExists()
            onNode(opens(carded[0]) and hasAnyDescendant(hasTextEqualTo("Belgian chocolate"))).assertExists()
        }
        show(cards(carded.take(2)), IPHONE_MEDIUM) {
            onNode(opens(carded[0]) and hasAnyDescendant(hasTextEqualTo("Belgian chocolate"))).assertExists()
            onNode(opens(carded[1]) and hasAnyDescendant(hasTextEqualTo("Trail weekend kit"))).assertExists()
            onNode(hasTextEqualTo("Out for delivery · tomorrow")).assertExists()
        }
        // A status that says when already doesn't say it twice.
        val dated = listOf(carded[0].copy(detail = "Out for delivery"))
        show(cards(dated), IPHONE_SMALL) { onNode(hasTextEqualTo("Out for delivery")).assertExists() }
    }

    /** `DeliveryWidgetCardColors` (C-W10): a card's pair of colours, light and dark as the launcher's mode picks. */
    @Test
    fun cardsColoursFollowTheLaunchersMode() {
        fun mode(night: Boolean): Context = app.createConfigurationContext(Configuration(app.resources.configuration).apply {
            uiMode = (uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or if (night) Configuration.UI_MODE_NIGHT_YES else Configuration.UI_MODE_NIGHT_NO
        })
        val surface = WidgetCardColors.pair(listOf("#e6deef", "#292235"))
        assertEquals(Color(0xFFE6DEEF), surface.getColor(mode(night = false)))
        assertEquals(Color(0xFF292235), surface.getColor(mode(night = true)))
        assertEquals(Color.Black, WidgetCardColors.primary.getColor(mode(night = false)))
        assertEquals(Color.White, WidgetCardColors.primary.getColor(mode(night = true)))
        assertEquals(Color(0xFFF4F5F1), WidgetCardColors.page.getColor(mode(night = false)))
        assertEquals(Color(0xFF13110E), WidgetCardColors.page.getColor(mode(night = true)))
        // A pair with one colour is that colour in both.
        assertEquals(Color(0xFF23093F), WidgetCardColors.pair(listOf("#23093f")).getColor(mode(night = true)))
    }


    /** A widget on the home screen, as a launcher binds one. */
    private fun place() = shadowOf(AppWidgetManager.getInstance(app)).bindAppWidgetId(1, ComponentName(app, NextDeliveryWidgetReceiver::class.java))

    private val alarms get() = shadowOf(app.getSystemService(AlarmManager::class.java))

    /**
     * The timeline (C-W03): the refresh is set only while a widget is placed, 20 minutes on in elapsed time, not waking
     * the phone, in the narrowest window an app has without the exact alarm permission; it goes with the last widget,
     * and a reload with none placed sets none.
     */
    @Test
    fun refreshIsSetOnlyWhileAWidgetIsPlaced() {
        val store = DeliveryWidgetSharedStore(com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults())
        val now = AppClock.now()
        assertFalse(DeliveryWidgetUpdates.isPlaced(app))
        DeliveryWidgetUpdates.schedule(app, store, now)
        assertTrue(alarms.scheduledAlarms.isEmpty())

        place()
        assertTrue(DeliveryWidgetUpdates.isPlaced(app))
        DeliveryWidgetUpdates.schedule(app, store, now)
        val alarm = alarms.scheduledAlarms.single()
        assertEquals(AlarmManager.ELAPSED_REALTIME, alarm.type)
        assertEquals(SystemClock.elapsedRealtime() + Duration.ofMinutes(20).toMillis(), alarm.triggerAtMs)
        assertEquals(Duration.ofMinutes(10).toMillis(), alarm.windowLengthMs)
        // Set again, it replaces itself.
        DeliveryWidgetUpdates.schedule(app, store, now)
        assertEquals(1, alarms.scheduledAlarms.size)

        // The last widget removed: the system disables the receiver, and the alarm goes.
        NextDeliveryWidgetReceiver().onDisabled(app)
        assertTrue(alarms.scheduledAlarms.isEmpty())
        DeliveryWidgetUpdates.schedule(app, store, now, placed = true)
        DeliveryWidgetUpdates.schedule(app, store, now, placed = false)
        assertTrue(alarms.scheduledAlarms.isEmpty())
    }

    /**
     * A parcel's words changing (C-W03): the widget is drawn again then, sooner than the refresh, in a window of three
     * quarters of the wait, the narrowest without the exact alarm permission, so a change five minutes on comes up to
     * 3 min 45 s late; the refresh comes up to 10 minutes late.
     */
    @Test
    fun aChangeOfWordsIsAsCloseAsTheSystemAllows() {
        place()
        val store = DeliveryWidgetSharedStore(com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults())
        val now = AppClock.now()
        val changes = now.plus(Duration.ofMinutes(5))
        check(store.save(DeliveryWidgetSnapshot(now, "en", listOf(three[1].copy(later = listOf(DeliveryWidgetWording(changes, "today")))))))
        DeliveryWidgetUpdates.schedule(app, store, now)
        val alarm = alarms.scheduledAlarms.single()
        val wait = Duration.between(now, changes)
        assertEquals(SystemClock.elapsedRealtime() + wait.toMillis(), alarm.triggerAtMs)
        assertEquals(Duration.ofSeconds(225), DeliveryWidgetUpdates.window(wait))
        assertEquals(DeliveryWidgetUpdates.window(wait).toMillis(), alarm.windowLengthMs)
        assertEquals(Duration.ofMinutes(10), DeliveryWidgetUpdates.window(Duration.ofMinutes(20)))
        // A change about to come: no sooner than the system's own five seconds, which still have a window, as a window
        // of none would ask for an exact alarm.
        DeliveryWidgetUpdates.schedule(app, store, changes.minusMillis(1))
        assertEquals(SystemClock.elapsedRealtime() + 5_000, alarms.scheduledAlarms.single().triggerAtMs)
        assertTrue(alarms.scheduledAlarms.single().windowLengthMs > 0)
    }

    /**
     * `WidgetCenter.reloadTimelines` (C-W01, A-W08): a widget being shown, its composition running, shows what the app
     * published once the app reloads its own kind, and again when its alarm comes.
     */
    @OptIn(ExperimentalGlanceApi::class)
    @Test
    fun widgetCenterAndTheAlarmReloadTheWidgets() = runBlocking<Unit> {
        val store = DeliveryWidgetSharedStore(PreferencesDefaults.appGroup(app, BuildSettings.configuration.appGroupIdentifier))
        fun publish(parcel: DeliveryWidgetParcel) = check(store.save(DeliveryWidgetSnapshot(CAPTURED, "en", listOf(parcel))))
        publish(three[0])
        val shown = Channel<List<String>>(Channel.UNLIMITED)
        val session = launch {
            ParcelsWidget().runComposition(app, sizes = listOf(IPHONE_SMALL)).collect { views ->
                val host = FrameLayout(app)
                host.addView(views.apply(app, host))
                shown.send(viewsUnder(host).mapNotNull { (it as? TextView)?.text?.toString() })
            }
        }
        suspend fun showing(label: String): List<List<String>> = withTimeout(10_000) {
            val seen = mutableListOf<List<String>>()
            while (seen.lastOrNull()?.contains(label) != true) seen += shown.receive()
            seen
        }
        try {
            showing("Belgian chocolate")

            // Another kind's reload leaves the widget as it was: it shows the next publication only once its own comes.
            publish(three[1])
            GlanceWidgetCenter(app).reloadTimelines("SomeOtherWidget")
            publish(three[2])
            GlanceWidgetCenter(app).reloadTimelines(DeliveryWidgetSharedStore.kind)
            assertTrue(showing("Desk lamp").none { "Trail weekend kit" in it })

            publish(three[1])
            app.sendBroadcast(Intent(app, NextDeliveryWidgetReceiver::class.java).setAction(DeliveryWidgetUpdates.REFRESH))
            shadowOf(Looper.getMainLooper()).idle()
            showing("Trail weekend kit")
        } finally {
            session.cancel()
        }
    }

    /**
     * TalkBack (C-W06, C-W07): every item it stops at says what it opens. The page between two cards opens the list, as
     * WidgetKit's does, without being an item, as VoiceOver passes over the iPhone's: its cards are.
     */
    @Test
    fun talkBackStopsOnlyWhereThereIsSomethingToSay() = runBlocking<Unit> {
        val carded = three.map { it.copy(card = card(it.id.toString())) }
        for ((count, size) in listOf(2 to IPHONE_MEDIUM, 1 to IPHONE_SMALL)) {
            val clickable = appliedViews(app, listViews(app, size, cards(carded.take(count))) { _, _ -> art }).filter { it.isClickable }
            for (view in clickable.filter { it.isImportantForAccessibility }) assertTrue("$view says nothing", spoken(view).isNotBlank())
            val silent = clickable.filterNot { it.isImportantForAccessibility }
            if (count == 2) {
                assertEquals(listOf(R.id.widget_tap), silent.map { it.id })
                assertTrue(silent.single().hasOnClickListeners())
                // The two cards.
                assertEquals(2, clickable.size - silent.size)
            } else {
                assertTrue(silent.isEmpty())
            }
        }
    }

    /**
     * The new-update dot (#72): a card's status keeps to the room the dot leaves before it, at a smaller size where it
     * must, its words whole, and the card's mark, its first words, says "New update." to TalkBack before the carrier.
     * ParcelsWidgetTest has the cards that show it.
     */
    @Test
    fun aCardsStatusKeepsToTheRoomTheDotLeaves() {
        val type = WidgetText(app)
        val parcel = widgetParcel(5, "Desk lamp", detail = "Thursday 12 November")
        val caption = type.points(IosTextStyle.Caption)
        var smaller = false
        for (width in 100..240 step 10) {
            val room = DpSize(width.dp, 150.dp)
            fun words(parcel: DeliveryWidgetParcel) = CardWords(parcel, card("5"), DeliveryWidgetPicture.Small, room = room, type = type, languageCode = "en")
            val unmarked = words(parcel)
            val dotted = words(parcel.copy(newUpdate = true))
            assertEquals(unmarked.status, dotted.status)
            assertTrue("$width", dotted.statusPoints <= unmarked.statusPoints)
            smaller = smaller || dotted.statusPoints < unmarked.statusPoints
            val fits = type.lines(dotted.status, dotted.statusPoints, FontWeight.Normal, room.width - NEW_UPDATE_DOT - NEW_UPDATE_GAP) <= 1
            assertTrue("$width", fits || dotted.statusPoints <= caption * 0.8f + 0.01f)
            assertEquals("New update. ${parcel.carrier}", dotted.markDescription)
            assertEquals(parcel.carrier, unmarked.markDescription)
        }
        assertTrue(smaller)
    }

    /**
     * Sizes: the medium layout where the widget is wide for its height as the iPhone's medium is, and wide enough for
     * two cards; a two-by-two widget on a phone is small upright and medium on its side.
     */
    @Test
    fun layoutFollowsTheWidgetsShape() {
        assertFalse(isMedium(IPHONE_SMALL))
        assertTrue(isMedium(IPHONE_MEDIUM))
        assertFalse(isMedium(DpSize(130.dp, 220.dp)))
        assertFalse(isMedium(DpSize(203.dp, 220.dp)))
        assertTrue(isMedium(DpSize(269.dp, 117.dp)))
        // Wide but square: the small layout, larger.
        assertFalse(isMedium(DpSize(349.dp, 340.dp)))
        // Wide for its height but too narrow for two cards.
        assertFalse(isMedium(DpSize(200.dp, 110.dp)))
    }

    /**
     * `DeliveryWidgetPictures.draw` (C-W04, C-W24, A-W09): every picture of a card at the screen's scale, the mark last;
     * a parcel with no places has Pip alone where its route would be.
     */
    @Test
    fun appDrawsEachPictureOfACard() = runBlocking {
        val folder = files.newFolder("WidgetCards")
        val parcel = DemoCards.parcels().first { parcel -> parcel.trackingEvents.all { it.place == null } }
        val card = DeliveryWidgetPictures.card(parcel, com.plhery.peekdeliverytracker.core.Localizer(AppLanguage.En), 2.0)
        assertTrue(WidgetCardPictures(app, folder, scale = 2.0).draw(parcel, card, AppLanguage.En))
        assertTrue(DeliveryWidgetPictures.isDrawn(card, folder))
        for (picture in DeliveryWidgetPicture.routes) {
            for (dark in listOf(false, true)) {
                val drawn = BitmapFactory.decodeFile(picture.file(card, dark, folder).path)
                val size = DeliveryWidgetPictures.frame(picture).size
                assertEquals("$picture", (size.width * 2).toInt(), drawn.width)
                assertEquals("$picture", (size.height * 2).toInt(), drawn.height)
                // Pip, at the card's trailing end, in its ink.
                val inked = (0 until drawn.width).count { x -> (0 until drawn.height).any { y -> android.graphics.Color.alpha(drawn.getPixel(x, y)) > 0 } }
                assertTrue("$picture has Pip", inked > 0)
                assertEquals("$picture's Pip stands at its end", 0, (0 until drawn.width / 3).count { x -> (0 until drawn.height).any { y -> android.graphics.Color.alpha(drawn.getPixel(x, y)) > 0 } })
            }
        }
        val mark = BitmapFactory.decodeFile(DeliveryWidgetPicture.Mark.file(card, false, folder).path)
        assertEquals(36, mark.height)
        assertTrue(folder.listFiles().orEmpty().none { it.name.endsWith(".part") })
    }
}
