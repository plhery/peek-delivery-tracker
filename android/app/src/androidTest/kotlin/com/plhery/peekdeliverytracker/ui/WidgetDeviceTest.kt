package com.plhery.peekdeliverytracker.ui

import android.app.PendingIntent
import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetHostView
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.os.Process
import android.util.SizeF
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.widget.DeliveryWidgetUpdates
import com.plhery.peekdeliverytracker.widget.IPHONE_MEDIUM
import com.plhery.peekdeliverytracker.widget.IPHONE_SMALL
import com.plhery.peekdeliverytracker.widget.LAUNCHER_ON_ITS_SIDE
import com.plhery.peekdeliverytracker.widget.LAUNCHER_TABLET
import com.plhery.peekdeliverytracker.widget.LAUNCHER_UPRIGHT
import com.plhery.peekdeliverytracker.widget.LAUNCHER_UPRIGHT_WIDE
import com.plhery.peekdeliverytracker.widget.NextDeliveryWidgetReceiver
import com.plhery.peekdeliverytracker.widget.WidgetCardArt
import com.plhery.peekdeliverytracker.widget.listIntent
import com.plhery.peekdeliverytracker.widget.listSize
import com.plhery.peekdeliverytracker.widget.listViews
import com.plhery.peekdeliverytracker.widget.publishDemo
import com.plhery.peekdeliverytracker.widget.widgetParcel
import com.plhery.peekdeliverytracker.widget.widgetSource
import java.io.File
import java.time.Instant
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The cards the Parcels widget shows for its fewest parcels, and its states, on this device (C-S48, C-W07, C-W09,
 * C-W10, C-W24), shot to widget/ in the test package's external files, which adb pulls:
 * - each state as a launcher draws it: the RemoteViews Glance writes, applied in the test's window at the iPhone's
 *   widget sizes, light and dark, with fakes (a store in memory, synthetic parcels, the demo's cards as this device
 *   draws them); and the cards at the sizes a launcher gives the widget, at the device's text size and at the largest;
 * - the widget itself, bound to a host in the test's window where the device lets the test bind it (`appwidget
 *   grantbind`): it shows what the test published to the app's store once the app reloads it. What the app's store
 *   held is put back once it ends.
 * - where the cards' taps lead (C-G13): the activities their RemoteViews, applied, start on a click, as the launcher
 *   starts them, read from each view's pending intent and not started: one card its parcel, two cards each theirs
 *   with the page between them the list; and the cards' carrier marks read as their carriers (C-AX16).
 * A test can't pin the widget to the launcher, whose dialog a person confirms: the launcher's own frame, its resizing
 * and its gallery aren't shown, nor a tap opening a parcel, which would open the app's window on its real stores.
 */
@RunWith(AndroidJUnit4::class)
class WidgetDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext
    private val shots = DeviceShots(compose, "widget")
    private val folder = File(context.cacheDir, "widget-device-test")

    @After
    fun clean() {
        folder.deleteRecursively()
    }

    private fun shell(command: String): String =
        ParcelFileDescriptor.AutoCloseInputStream(instrumentation.uiAutomation.executeShellCommand(command)).use { it.readBytes().decodeToString() }

    /** The app's context in `night`'s mode, at `fontScale` where one is given, else at the device's. */
    private fun mode(night: Boolean, fontScale: Float? = null): Context = context.createConfigurationContext(
        Configuration(context.resources.configuration).apply {
            uiMode = (uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or if (night) Configuration.UI_MODE_NIGHT_YES else Configuration.UI_MODE_NIGHT_NO
            if (fontScale != null) this.fontScale = fontScale
        },
    )

    /** Widgets applied as a launcher at `fontScale` would apply them: the text's sp are that launcher's. */
    private class Page(val night: Boolean, val widgets: List<Pair<DpSize, RemoteViews>>, val fontScale: Float? = null)

    private val page = mutableStateOf<Page?>(null)

    /**
     * The window shows one page of widgets at a time, applied in the page's mode, on a wallpaper of it; a widget wider
     * than the page (a tablet's on a phone) spills into its margins.
     */
    private fun open() = compose.setContent {
        val shown = page.value ?: return@setContent
        Column(
            Modifier.fillMaxSize().background(if (shown.night) Color(0xFF30343A) else Color(0xFFC9D3DD)).safeDrawingPadding().padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            val applied = mode(shown.night, shown.fontScale)
            for ((size, views) in shown.widgets) {
                key(views) {
                    AndroidView(
                        factory = { android.widget.FrameLayout(it).apply { addView(views.apply(applied, this)) } },
                        modifier = Modifier.requiredSize(size),
                    )
                }
            }
        }
    }

    private fun shoot(
        name: String,
        small: DeliveryWidgetList,
        medium: DeliveryWidgetList,
        art: (DeliveryWidgetCard, DeliveryWidgetPicture) -> WidgetCardArt? = { _, _ -> null },
    ) {
        for (night in listOf(false, true)) {
            val applied = mode(night)
            val widgets = runBlocking { listOf(IPHONE_SMALL to listViews(applied, IPHONE_SMALL, small, art), IPHONE_MEDIUM to listViews(applied, IPHONE_MEDIUM, medium, art)) }
            compose.runOnUiThread { page.value = Page(night, widgets) }
            shots.take("${name}_${if (night) "dark" else "light"}")
        }
    }

    private val parcels = listOf(
        widgetParcel(1, "Belgian chocolate 🍫", carrier = "FedEx", outForDelivery = true),
        widgetParcel(2, "Trail weekend kit 🏕️", carrier = "DPD Switzerland", detail = "tomorrow"),
        widgetParcel(3, "Desk lamp", detail = "Friday"),
    )

    /** The list of its first `count` parcels. */
    private fun DeliveryWidgetList.firstParcels(count: Int): DeliveryWidgetList = copy(parcels = parcels.take(count))

    /**
     * Each state as a launcher draws it: the demo's cards with this device's pictures, a card whose parcel has no
     * places (Pip alone), nothing on its way, and switched off.
     */
    @Test
    fun widgetsAsALauncherDrawsThem() {
        open()
        val scale = context.resources.displayMetrics.density.toDouble()
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val source = widgetSource(context, File(folder, "cards"), store)
        runBlocking { publishDemo(context, store, File(folder, "cards"), scale) }
        val demo = source.list(CAPTURED)
        shoot("cards", demo.firstParcels(1), demo.firstParcels(2), source::read)
        val unplacedStore = DeliveryWidgetSharedStore(MemoryDefaults())
        runBlocking { publishDemo(context, unplacedStore, File(folder, "unplaced"), scale, drawn = 1) { all -> all.filter { parcel -> parcel.trackingEvents.all { it.place == null } } } }
        val unplaced = widgetSource(context, File(folder, "unplaced"), unplacedStore)
        val alone = unplaced.list(CAPTURED).firstParcels(1)
        shoot("pip", alone, alone, unplaced::read)
        val none = DeliveryWidgetList.make(CAPTURED, true, "en", DeliveryWidgetSnapshot(CAPTURED, "en", emptyList()))
        shoot("empty", none, none)
        val off = DeliveryWidgetList.make(CAPTURED, false, "en", DeliveryWidgetSnapshot(CAPTURED, "en", parcels))
        shoot("disabled", off, off)
    }

    /**
     * At the sizes a launcher gives the widget (C-W10): a phone's two-by-two upright and its three-by-two, then its
     * two-by-two on its side and a tablet's; the demo's cards, one or two as the size shows them, at the device's text
     * size and at the largest, WidgetKit's xxLarge, where the names and status lines shrink before they end.
     */
    @Test
    fun widgetsAtALaunchersSizes() {
        open()
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val source = widgetSource(context, File(folder, "cards"), store)
        runBlocking { publishDemo(context, store, File(folder, "cards"), context.resources.displayMetrics.density.toDouble()) }
        val demo = source.list(CAPTURED)
        val pages = listOf("upright" to listOf(LAUNCHER_UPRIGHT, LAUNCHER_UPRIGHT_WIDE), "side" to listOf(LAUNCHER_ON_ITS_SIDE, LAUNCHER_TABLET))
        for (fontScale in listOf(null, LARGEST_TEXT)) {
            val composed = mode(night = false, fontScale)
            for ((name, sizes) in pages) {
                val widgets = runBlocking { sizes.map { it to listViews(composed, it, demo.firstParcels(listSize(it).cards), source::read) } }
                compose.runOnUiThread { page.value = Page(night = false, widgets, fontScale) }
                shots.take("sizes_cards_$name${if (fontScale != null) "_largest_text" else ""}")
            }
        }
    }

    /**
     * The widget itself in a host of the test's, bound to the receiver a widget placed as Next delivery is bound to:
     * the list of what the app's store holds, published by the test, once the app reloads it (C-W01, C-W03).
     */
    @Test
    fun boundWidgetReadsTheStore() {
        val manager = AppWidgetManager.getInstance(context)
        val provider = ComponentName(context, NextDeliveryWidgetReceiver::class.java)
        val preferences = context.getSharedPreferences(BuildSettings.configuration.appGroupIdentifier, Context.MODE_PRIVATE)
        val kept = preferences.all.toMap()
        shell("appwidget grantbind --package ${context.packageName} --user $user")
        val host = AppWidgetHost(context, HOST)
        val id = host.allocateAppWidgetId()
        try {
            assumeTrue("The device lets the test bind the widget", manager.bindAppWidgetIdIfAllowed(id, provider))
            val store = DeliveryWidgetSharedStore(PreferencesDefaults(preferences))
            store.setEnabled(true)
            val published = listOf(widgetParcel(11, "First parcel", outForDelivery = true), widgetParcel(12, "Second parcel"), widgetParcel(13, "Third parcel"))
            store.save(DeliveryWidgetSnapshot(Instant.now(), "en", published))
            lateinit var view: AppWidgetHostView
            compose.runOnUiThread { host.startListening() }
            compose.setContent {
                AndroidView(
                    factory = { host.createView(it, id, manager.getAppWidgetInfo(id)).also { made -> view = made } },
                    modifier = Modifier.safeDrawingPadding().padding(12.dp).size(IPHONE_SMALL),
                )
            }
            compose.waitForIdle()
            compose.runOnUiThread { view.updateAppWidgetSize(Bundle(), listOf(SizeF(IPHONE_SMALL.width.value, IPHONE_SMALL.height.value))) }
            DeliveryWidgetUpdates.reload(context)
            awaitText(view, "First parcel")
            awaitText(view, "Third parcel")
            shots.take("bound_small")
        } finally {
            host.deleteAppWidgetId(id)
            compose.runOnUiThread { host.stopListening() }
            preferences.edit().clear().also { editor -> kept.forEach { (key, value) -> editor.put(key, value) } }.commit()
            DeliveryWidgetUpdates.reload(context)
            shell("appwidget revokebind --package ${context.packageName} --user $user")
        }
    }

    /** `views` applied as a launcher applies them, in `applied`, in a frame of their own off the window. */
    private fun applied(views: RemoteViews, applied: Context): View =
        compose.runOnUiThread { views.apply(applied, FrameLayout(applied)) }

    /** This view and every view in it. */
    private fun View.all(): Sequence<View> = sequence {
        yield(this@all)
        if (this@all is ViewGroup) for (index in 0 until childCount) yieldAll(getChildAt(index).all())
    }

    /**
     * The activities a click on the applied views would start, as the views' pending intents (the framework's
     * `pending_intent_tag`), in the order the views stand.
     */
    private fun activityTaps(root: View): List<PendingIntent> {
        val tag = Resources.getSystem().getIdentifier("pending_intent_tag", "id", "android")
        check(tag != 0) { "The framework has no pending_intent_tag" }
        return compose.runOnUiThread { root.all().mapNotNull { it.getTag(tag) as? PendingIntent }.filter { it.isActivity }.toList() }
    }

    /** The pending intent that opens `parcel` on the deliveries, as the widget asked Glance for it; null if it never did. */
    private fun parcelTap(parcel: DeliveryWidgetParcel): PendingIntent? = PendingIntent.getActivity(
        context, 0, Intent(Intent.ACTION_VIEW, parcel.deepLink.toUri(), context, MainActivity::class.java),
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_NO_CREATE,
    )

    /** [parcelTap], which the widget must have asked for. */
    private fun tapOf(parcel: DeliveryWidgetParcel): PendingIntent = checkNotNull(parcelTap(parcel)) { "No tap for ${parcel.label}" }

    /** The pending intent the page between two cards opens the list with; null if none was made. */
    private fun pageTap(): PendingIntent? =
        PendingIntent.getActivity(context, 0, listIntent(context), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_NO_CREATE)

    /** The small and medium widgets of `small` and `medium` on the window, light, saved as `name`. */
    private fun shootLight(name: String, small: RemoteViews, medium: RemoteViews) {
        compose.runOnUiThread { page.value = Page(night = false, listOf(IPHONE_SMALL to small, IPHONE_MEDIUM to medium)) }
        shots.take(name)
    }

    /**
     * C-G13: one card opens its parcel; two cards each open theirs, and the page between them the list. C-AX16: the
     * cards' marks read as their carriers, nothing else pictured reads.
     */
    @Test
    fun eachWidgetsTapsLeadWhereTheyShould() {
        open()
        val applied = mode(night = false)
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val source = widgetSource(context, File(folder, "cards"), store)
        runBlocking { publishDemo(context, store, File(folder, "cards"), context.resources.displayMetrics.density.toDouble()) }

        val demo = source.list(CAPTURED)
        val one = demo.firstParcels(1)
        val two = demo.firstParcels(2)
        assertEquals(2, two.parcels.size)
        val oneViews = runBlocking { listViews(applied, IPHONE_SMALL, one, source::read) }
        val twoViews = runBlocking { listViews(applied, IPHONE_MEDIUM, two, source::read) }
        val oneRoot = applied(oneViews, applied)
        val twoRoot = applied(twoViews, applied)
        assertEquals(listOf(tapOf(one.parcels.single())), activityTaps(oneRoot))
        val cardTaps = two.parcels.map { tapOf(it) }
        val between = checkNotNull(pageTap()) { "No tap for the page between the cards" }
        val twoTaps = activityTaps(twoRoot)
        assertEquals(3, twoTaps.size)
        assertEquals((listOf(between) + cardTaps).toSet(), twoTaps.toSet())
        // C-AX16: the marks read as their carriers, nothing else pictured reads.
        assertEquals(one.parcels.map { it.carrier }, described(oneRoot))
        assertEquals(two.cardOrder.map { it.carrier }, described(twoRoot))
        shootLight("C-G13-cards", oneViews, twoViews)
    }

    /** What the pictures under `root` read as, in the order they stand: those that read as something. */
    private fun described(root: View): List<String> = compose.runOnUiThread {
        root.all().filterIsInstance<ImageView>().mapNotNull { it.contentDescription?.toString()?.takeIf(String::isNotEmpty) }.toList()
    }

    /** Waits until the widget's views show `text`, as the host gets them from the launcher's service. */
    private fun awaitText(view: View, text: String) = compose.waitUntil(15_000) { compose.runOnUiThread { view.shows(text) } }

    private fun View.shows(text: String): Boolean =
        (this is TextView && this.text?.toString() == text) || (this is ViewGroup && (0 until childCount).any { getChildAt(it).shows(text) })

    /**
     * This test's user, as a number: `appwidget`'s `--user current` fails on Android 12 ("User -2 must be unlocked"),
     * which left the widget unbound there and the test skipped. A uid is its user's id times 100 000 plus the app's.
     */
    private val user get() = Process.myUid() / 100_000

    @Suppress("UNCHECKED_CAST")
    private fun SharedPreferences.Editor.put(key: String, value: Any?) {
        when (value) {
            is Boolean -> putBoolean(key, value)
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            is Set<*> -> putStringSet(key, value as Set<String>)
        }
    }

    private companion object {
        /** The test's own host of widgets, apart from any launcher's. */
        const val HOST = 0x5EE

        /** Android's largest font scale. */
        const val LARGEST_TEXT = 2f
    }
}
