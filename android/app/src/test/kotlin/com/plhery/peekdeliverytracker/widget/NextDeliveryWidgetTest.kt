package com.plhery.peekdeliverytracker.widget

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Looper
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.glance.GlanceId
import androidx.glance.appwidget.testing.unit.hasRunCallbackClickAction
import androidx.glance.appwidget.testing.unit.hasStartActivityClickAction
import androidx.glance.appwidget.testing.unit.runGlanceAppWidgetUnitTest
import androidx.glance.testing.unit.hasAnyDescendant
import androidx.glance.testing.unit.hasContentDescriptionEqualTo
import androidx.glance.testing.unit.hasTextEqualTo
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetEntry
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPictures
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.core.DeliveryWidgetTimeline
import com.plhery.peekdeliverytracker.core.DeliveryWidgetTurn
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import java.time.Duration
import java.time.Instant
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The home screen widget (C-S47, C-S48, C-W01…C-W10, C-W21…C-W24, C-L02, A-W09) as Glance composes it, without a
 * launcher: what each state shows, where a tap goes, the next button's turn, and what the widget reads from the store
 * the app publishes to; DeliveryWidget.swift and DeliveryWidgetCards.swift are the spec.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w390dp-h844dp-xhdpi")
class NextDeliveryWidgetTest {
    @get:Rule
    val zurich = ZurichTime()

    @get:Rule
    val files = TemporaryFolder()

    private val app = ApplicationProvider.getApplicationContext<Context>()
    private val english = ExtensionLocalizer("en")

    private fun opens(parcel: DeliveryWidgetParcel) =
        hasStartActivityClickAction(Intent(Intent.ACTION_VIEW, Uri.parse(parcel.deepLink), app, MainActivity::class.java))

    private val opensApp =
        hasStartActivityClickAction(Intent(app, MainActivity::class.java).setAction(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER))

    private fun show(entry: DeliveryWidgetEntry<WidgetCardArt>, size: DpSize, test: androidx.glance.appwidget.testing.unit.GlanceAppWidgetUnitTest.() -> Unit) =
        runGlanceAppWidgetUnitTest {
            setContext(app)
            setAppWidgetSize(size)
            provideComposable { DeliveryWidgetContent(entry, NextDeliveryWidget.isMedium(size)) }
            test()
        }

    /** A picture of nothing, which stands for a card's. */
    private val blank: Bitmap = Bitmap.createBitmap(4, 4, Bitmap.Config.ARGB_8888)
    private val art = WidgetCardArt(blank to blank, blank to blank)

    private fun card(name: String) = DeliveryWidgetCard(listOf("#e6deef", "#292235"), listOf("#23093f", "#c1add7"), "Out for delivery", name, 2.0)

    private fun cardsEntry(parcels: List<DeliveryWidgetParcel>, medium: Boolean): DeliveryWidgetEntry<WidgetCardArt> =
        DeliveryWidgetEntry.make(CAPTURED, true, "en", DeliveryWidgetSnapshot(CAPTURED, "en", parcels), medium) { _, _ -> art }

    private val three = listOf(
        widgetParcel(1, "Belgian chocolate", carrier = "FedEx", outForDelivery = true),
        widgetParcel(2, "Trail weekend kit", carrier = "DPD Switzerland", detail = "tomorrow"),
        widgetParcel(3, "Desk lamp", detail = "Friday"),
    )

    /** `testSmallWidgetShowsItsParcelInWords` (C-W05, C-W23): the header, the badge, the name, the carrier and when. */
    @Test
    fun smallWidgetShowsItsParcelInWordsAndOpensIt() = show(wordsEntry(three, medium = false), NextDeliveryWidget.SMALL) {
        onNode(hasTextEqualTo(english.text("app.title").uppercase())).assertExists()
        onNode(hasTextEqualTo(english.text("stage.out_for_delivery").uppercase())).assertExists()
        onNode(hasTextEqualTo("Belgian chocolate")).assertExists()
        onNode(hasTextEqualTo("FedEx")).assertExists()
        onNode(hasTextEqualTo("today")).assertExists()
        onNode(hasTextEqualTo("Trail weekend kit")).assertDoesNotExist()
        // `widgetURL`: the whole widget opens its parcel through the app's routing.
        onNode(opens(three[0]) and hasAnyDescendant(hasTextEqualTo("Belgian chocolate"))).assertExists()
    }

    /** C-W06: two parcels side by side, each its own link, the second's badge on its way. */
    @Test
    fun mediumWidgetShowsTwoParcelsEachOpeningItself() = show(wordsEntry(three, medium = true), NextDeliveryWidget.MEDIUM) {
        onNode(hasTextEqualTo("Belgian chocolate")).assertExists()
        onNode(hasTextEqualTo("Trail weekend kit")).assertExists()
        onNode(hasTextEqualTo("Desk lamp")).assertDoesNotExist()
        onNode(hasTextEqualTo(english.text("app.onTheWaySection").uppercase())).assertExists()
        onNode(opens(three[0]) and hasAnyDescendant(hasTextEqualTo("Belgian chocolate"))).assertExists()
        onNode(opens(three[1]) and hasAnyDescendant(hasTextEqualTo("Trail weekend kit"))).assertExists()
        onNode(opensApp).assertExists()
    }

    /** C-W22, C-W23: a parcel delivered within the day is still shown, with its badge. */
    @Test
    fun deliveredParcelIsShownWithItsBadge() {
        val delivered = widgetParcel(4, "Moon lamp", detail = "Delivered today", deliveredAt = CAPTURED.minus(Duration.ofHours(2)))
        show(wordsEntry(listOf(delivered), medium = false), NextDeliveryWidget.SMALL) {
            onNode(hasTextEqualTo(english.text("stage.delivered").uppercase())).assertExists()
            onNode(hasTextEqualTo("Moon lamp")).assertExists()
        }
    }

    /**
     * `ShowNextParcelIntent` (C-W21): with more parcels than fit, the next button turns the widgets of its size to the
     * next ones; with all of them shown at once there is none.
     */
    @Test
    fun nextButtonTurnsTheWidgetsOfItsSize() {
        val next = english.text("app.nextParcel")
        show(wordsEntry(three, medium = false), NextDeliveryWidget.SMALL) {
            onNode(hasContentDescriptionEqualTo(next))
                .assert(hasRunCallbackClickAction<ShowNextParcelAction>(ShowNextParcelAction.parameters(three[1].id, "small")))
        }
        show(wordsEntry(three, medium = true), NextDeliveryWidget.MEDIUM) {
            onNode(hasContentDescriptionEqualTo(next))
                .assert(hasRunCallbackClickAction<ShowNextParcelAction>(ShowNextParcelAction.parameters(three[2].id, "medium")))
        }
        // From the last page, back to the first.
        show(wordsEntry(three, medium = true, turn = three[2].id), NextDeliveryWidget.MEDIUM) {
            onNode(hasTextEqualTo("Desk lamp")).assertExists()
            onNode(hasContentDescriptionEqualTo(next))
                .assert(hasRunCallbackClickAction<ShowNextParcelAction>(ShowNextParcelAction.parameters(three[0].id, "medium")))
        }
        show(wordsEntry(three.take(2), medium = true), NextDeliveryWidget.MEDIUM) {
            onAllNodes(hasContentDescriptionEqualTo(next)).assertCountEquals(0)
        }
        show(cardsEntry(three, medium = false), NextDeliveryWidget.SMALL) {
            onNode(hasContentDescriptionEqualTo(next))
                .assert(hasRunCallbackClickAction<ShowNextParcelAction>(ShowNextParcelAction.parameters(three[1].id, "small")))
        }
    }

    /** `stateView` (C-W09): switched off in the app, or nothing on its way; the widget opens the app. */
    @Test
    fun emptyAndSwitchedOffWidgetsSayWhy() {
        show(wordsEntry(three, medium = false, enabled = false), NextDeliveryWidget.SMALL) {
            onNode(hasTextEqualTo(english.text("widget.disabledTitle"))).assertExists()
            // Android's words for the home screen.
            onNode(hasTextEqualTo(english.text("widget.disabledDescription.android"))).assertExists()
            onNode(hasTextEqualTo(english.text("widget.disabledDescription"))).assertDoesNotExist()
            onNode(hasTextEqualTo("Belgian chocolate")).assertDoesNotExist()
            onNode(opensApp).assertExists()
            onAllNodes(hasContentDescriptionEqualTo(english.text("app.nextParcel"))).assertCountEquals(0)
        }
        show(wordsEntry(emptyList(), medium = true), NextDeliveryWidget.MEDIUM) {
            onNode(hasTextEqualTo(english.text("app.noneOnWay"))).assertExists()
            onNode(hasTextEqualTo(english.text("app.emptyDescription"))).assertExists()
            onNode(opensApp).assertExists()
        }
        // A widget switched off shows no cards, drawn or not.
        val off = cardsEntry(three.map { it.copy(card = card(it.id.toString())) }, medium = false).copy(enabled = false)
        show(off, NextDeliveryWidget.SMALL) { onNode(hasTextEqualTo(english.text("widget.disabledTitle"))).assertExists() }
    }

    /**
     * `DeliveryWidgetCardView` (C-S48, C-W07): once every card shown has its pictures, the cards in place of words, the
     * name and the status line; one card opens its parcel, and of two each opens its own.
     */
    @Test
    fun cardsShowOnceTheirPicturesAreDrawn() {
        val carded = three.map { it.copy(card = card(it.id.toString())) }
        show(cardsEntry(carded, medium = false), NextDeliveryWidget.SMALL) {
            onNode(hasTextEqualTo(english.text("app.title").uppercase())).assertDoesNotExist()
            onNode(hasTextEqualTo("Belgian chocolate")).assertExists()
            onNode(hasTextEqualTo("Out for delivery · today")).assertExists()
            onNode(opens(carded[0]) and hasAnyDescendant(hasTextEqualTo("Belgian chocolate"))).assertExists()
        }
        show(cardsEntry(carded, medium = true), NextDeliveryWidget.MEDIUM) {
            onNode(opens(carded[0]) and hasAnyDescendant(hasTextEqualTo("Belgian chocolate"))).assertExists()
            onNode(opens(carded[1]) and hasAnyDescendant(hasTextEqualTo("Trail weekend kit"))).assertExists()
            onNode(hasTextEqualTo("Out for delivery · tomorrow")).assertExists()
        }
        // A status that says when already doesn't say it twice.
        val dated = listOf(carded[0].copy(detail = "Out for delivery"))
        show(cardsEntry(dated, medium = false), NextDeliveryWidget.SMALL) { onNode(hasTextEqualTo("Out for delivery")).assertExists() }
        // Words while one of them has none.
        show(cardsEntry(listOf(carded[0], three[1]), medium = true), NextDeliveryWidget.MEDIUM) {
            onNode(hasTextEqualTo(english.text("app.title").uppercase())).assertExists()
        }
    }

    /** `ExtensionLocalizer` (C-W10): the widget speaks the app's language, its upper case the language's. */
    @Test
    fun widgetSpeaksTheAppsLanguage() {
        val french = ExtensionLocalizer("fr")
        assertNotEquals(english.text("app.nextParcel"), french.text("app.nextParcel"))
        show(wordsEntry(three, medium = false, languageCode = "fr"), NextDeliveryWidget.SMALL) {
            onNode(hasTextEqualTo(french.text("stage.out_for_delivery").uppercase(java.util.Locale.FRENCH))).assertExists()
            onNode(hasContentDescriptionEqualTo(french.text("app.nextParcel"))).assertExists()
            onNode(hasContentDescriptionEqualTo(english.text("app.nextParcel"))).assertDoesNotExist()
        }
        show(wordsEntry(emptyList(), medium = false, languageCode = "fr"), NextDeliveryWidget.SMALL) {
            onNode(hasTextEqualTo(french.text("app.noneOnWay"))).assertExists()
        }
    }

    /** `DeliveryWidgetCardColors` and `WidgetPalette` (C-W10): a card's pair of colours, light and dark as the launcher's mode picks. */
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
        assertEquals(Color(0xFF151915), WidgetCardColors.page.getColor(mode(night = true)))
        // A pair with one colour is that colour in both.
        assertEquals(Color(0xFF23093F), WidgetCardColors.pair(listOf("#23093f")).getColor(mode(night = true)))
    }

    /**
     * `currentEntry` (C-W01, C-W04, A-W09): the widget reads what the app published, the cards once their pictures are
     * in the widget's folder, the page its size was turned to while the turn lasts, and whether it is switched on.
     */
    @Test
    fun widgetReadsWhatTheAppPublished() = runBlocking {
        val folder = files.newFolder("WidgetCards")
        val store = DeliveryWidgetSharedStore(com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults())
        val source = widgetSource(app, folder, store, languages = listOf("de-CH"))
        assertNull(source.current(medium = false, CAPTURED).snapshot)
        // Nothing published yet: the phone's language.
        assertEquals("de", source.current(medium = false, CAPTURED).languageCode)

        val published = publishDemo(app, store, folder, scale = 2.0)
        val small = source.current(medium = false, CAPTURED)
        assertEquals("en", small.languageCode)
        assertEquals(published.parcels.take(1), small.shown)
        assertEquals(1, small.cards.size)
        val medium = source.current(medium = true, CAPTURED)
        assertEquals(published.parcels.take(2), medium.shown)
        assertEquals(2, medium.cards.size)
        // A picture as wide as the shape at the pictures' scale.
        val half = DeliveryWidgetPicture.Half.file(medium.shown[0].card!!, false, folder)
        assertEquals((DeliveryWidgetPictures.frame(DeliveryWidgetPicture.Half).size.width * 2).toInt(), BitmapFactory.decodeFile(half.path).width)

        store.setTurn(DeliveryWidgetTurn(published.parcels[2].id, CAPTURED), size = "medium")
        val turned = source.current(medium = true, CAPTURED.plusSeconds(60))
        assertEquals(published.parcels[2].id, turned.shown.first().id)
        assertFalse(turned.isFirstPage)
        // The third parcel's card wasn't drawn: words.
        assertTrue(turned.cards.isEmpty())
        assertEquals(published.parcels.take(1), source.current(medium = false, CAPTURED.plusSeconds(60)).shown)
        // The turn's spell over, the first page again.
        assertTrue(source.current(medium = true, CAPTURED.plusSeconds(DeliveryWidgetTurn.spell.toLong() + 1)).isFirstPage)

        // A card whose pictures aren't in the folder (the mark, drawn last, isn't there) is words.
        store.save(published.copy(parcels = published.parcels.map { parcel -> parcel.copy(card = parcel.card?.copy(pictures = "undrawn")) }))
        assertTrue(source.current(medium = false, CAPTURED).cards.isEmpty())

        store.setEnabled(false)
        assertFalse(source.current(medium = false, CAPTURED).enabled)
    }

    /**
     * `placeholder` and `getSnapshot` (C-W08): the gallery's sample is the demo's next deliveries in the reader's
     * language, with the iPhone's own pictures of them; the reader's own cards once the app has drawn them.
     */
    @Test
    fun galleryShowsTheSampleUntilTheReadersCardsAreDrawn() = runBlocking {
        val folder = files.newFolder("WidgetCards")
        val store = DeliveryWidgetSharedStore(com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults())
        store.setLanguageCode("fr")
        val source = widgetSource(app, folder, store)
        val sample = source.placeholder(medium = true, CAPTURED)
        assertTrue(sample.isSample)
        assertEquals("fr", sample.languageCode)
        assertEquals(listOf("Chocolat belge 🍫", "Kit week-end trail 🏕️").map { it.take(8) }, sample.shown.map { it.label.take(8) })
        assertEquals(2, sample.cards.size)
        assertEquals(1, source.placeholder(medium = false, CAPTURED).cards.size)
        assertEquals(sample.shown, source.preview(medium = true, CAPTURED).shown)
        assertTrue(source.preview(medium = true, CAPTURED).isSample)

        publishDemo(app, store, folder, scale = 2.0, language = AppLanguage.Fr)
        val own = source.preview(medium = true, CAPTURED)
        assertFalse(own.isSample)
        assertEquals(2, own.cards.size)
    }

    /**
     * `ShowNextParcelIntent.perform` and the timeline (C-W03, C-W21): the turn is kept for the widgets of its size, they
     * are drawn again, and the widgets come back when the turn's spell ends, or 20 minutes on.
     */
    @Test
    fun nextActionTurnsTheWidgetsAndTheTimelineComesBack() = runBlocking {
        val store = DeliveryWidgetSharedStore(PreferencesDefaults.appGroup(app, BuildSettings.configuration.appGroupIdentifier))
        val alarms = shadowOf(app.getSystemService(AlarmManager::class.java))
        val now = Instant.now()

        DeliveryWidgetUpdates.schedule(app, store, now)
        assertEquals(now.plusSeconds(DeliveryWidgetTimeline.refresh.toLong()).toEpochMilli(), alarms.peekNextScheduledAlarm()!!.triggerAtMs)
        assertEquals(AlarmManager.RTC, alarms.peekNextScheduledAlarm()!!.getType())

        val revision = DeliveryWidgetUpdates.revision.value
        val parcel = three[1].id
        ShowNextParcelAction().onAction(app, object : GlanceId {}, ShowNextParcelAction.parameters(parcel, "small"))
        val turn = store.turn(size = "small")
        assertEquals(parcel, turn?.parcel)
        assertNull(store.turn(size = "medium"))
        assertEquals(revision + 1, DeliveryWidgetUpdates.revision.value)
        // The turn's spell ends before the refresh: the widgets come back then.
        assertEquals(turn!!.ends.toEpochMilli(), alarms.peekNextScheduledAlarm()!!.triggerAtMs)

        // A parameter that isn't a parcel turns nothing.
        ShowNextParcelAction().onAction(app, object : GlanceId {}, androidx.glance.action.actionParametersOf(ShowNextParcelAction.PARCEL to "not a parcel"))
        assertEquals(revision + 1, DeliveryWidgetUpdates.revision.value)
    }

    /** `WidgetCenter.reloadTimelines` (C-W01, A-W08): the app's publishing draws the widgets again, its own kind only; so does their alarm. */
    @Test
    fun widgetCenterAndTheAlarmReloadTheWidgets() {
        val center = GlanceWidgetCenter(app)
        val revision = DeliveryWidgetUpdates.revision.value
        center.reloadTimelines("SomeOtherWidget")
        assertEquals(revision, DeliveryWidgetUpdates.revision.value)
        center.reloadTimelines(DeliveryWidgetSharedStore.kind)
        assertEquals(revision + 1, DeliveryWidgetUpdates.revision.value)

        app.sendBroadcast(Intent(app, NextDeliveryWidgetReceiver::class.java).setAction(DeliveryWidgetUpdates.REFRESH))
        shadowOf(Looper.getMainLooper()).idle()
        assertEquals(revision + 2, DeliveryWidgetUpdates.revision.value)
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
