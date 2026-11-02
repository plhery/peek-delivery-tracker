package com.plhery.peekdeliverytracker.ui

import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetHostView
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.SizeF
import android.view.View
import android.view.ViewGroup
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
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.DeliveryWidgetEntry
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
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
import com.plhery.peekdeliverytracker.widget.NextDeliveryWidget
import com.plhery.peekdeliverytracker.widget.NextDeliveryWidgetReceiver
import com.plhery.peekdeliverytracker.widget.WidgetCardArt
import com.plhery.peekdeliverytracker.widget.publishDemo
import com.plhery.peekdeliverytracker.widget.widgetParcel
import com.plhery.peekdeliverytracker.widget.widgetSource
import com.plhery.peekdeliverytracker.widget.widgetViews
import com.plhery.peekdeliverytracker.widget.wordsEntry
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
 * The home screen widget on this device (C-S47, C-S48, C-W05…C-W10, C-W21, C-W24), shot to widget/ in the test
 * package's external files, which adb pulls:
 * - each state as a launcher draws it: the RemoteViews Glance writes, applied in the test's window at the iPhone's
 *   widget sizes, light and dark, with fakes (a store in memory, synthetic parcels, the demo's cards as this device
 *   draws them, the gallery's sample); and the cards and words at the sizes a launcher gives the widget, at the
 *   device's text size and at the largest;
 * - the widget itself, bound to a host in the test's window where the device lets the test bind it (`appwidget
 *   grantbind`): it reads what the test published to the app's store, and its next button turns it, through Glance's
 *   action and the app's reload. What the app's store held is put back once it ends.
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

    private fun shoot(name: String, small: DeliveryWidgetEntry<WidgetCardArt>, medium: DeliveryWidgetEntry<WidgetCardArt>) {
        for (night in listOf(false, true)) {
            val applied = mode(night)
            val widgets = runBlocking { listOf(IPHONE_SMALL to widgetViews(applied, IPHONE_SMALL, small), IPHONE_MEDIUM to widgetViews(applied, IPHONE_MEDIUM, medium)) }
            compose.runOnUiThread { page.value = Page(night, widgets) }
            shots.take("${name}_${if (night) "dark" else "light"}")
        }
    }

    private val parcels = listOf(
        widgetParcel(1, "Belgian chocolate 🍫", carrier = "FedEx", outForDelivery = true),
        widgetParcel(2, "Trail weekend kit 🏕️", carrier = "DPD Switzerland", detail = "tomorrow"),
        widgetParcel(3, "Desk lamp", detail = "Friday"),
    )

    /**
     * Each state as a launcher draws it: the gallery's sample with the iPhone's pictures, the demo's cards with this
     * device's, a card whose parcel has no places (Pip alone), words, nothing on its way, and switched off.
     */
    @Test
    fun widgetsAsALauncherDrawsThem() {
        open()
        val scale = context.resources.displayMetrics.density.toDouble()
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val source = widgetSource(context, File(folder, "cards"), store)
        shoot("sample", source.placeholder(false, CAPTURED), source.placeholder(true, CAPTURED))
        runBlocking { publishDemo(context, store, File(folder, "cards"), scale) }
        shoot("cards", source.current(false, CAPTURED), source.current(true, CAPTURED))
        val unplacedStore = DeliveryWidgetSharedStore(MemoryDefaults())
        runBlocking { publishDemo(context, unplacedStore, File(folder, "unplaced"), scale, drawn = 1) { all -> all.filter { parcel -> parcel.trackingEvents.all { it.place == null } } } }
        val unplaced = widgetSource(context, File(folder, "unplaced"), unplacedStore)
        shoot("pip", unplaced.current(false, CAPTURED), unplaced.current(true, CAPTURED))
        shoot("words", wordsEntry(parcels, medium = false), wordsEntry(parcels, medium = true))
        shoot("empty", wordsEntry(emptyList(), medium = false), wordsEntry(emptyList(), medium = true))
        shoot("disabled", wordsEntry(parcels, medium = false, enabled = false), wordsEntry(parcels, medium = true, enabled = false))
    }

    /**
     * At the sizes a launcher gives the widget (C-W10): a phone's two-by-two upright and its three-by-two, then its
     * two-by-two on its side and a tablet's; the demo's cards and words, at the device's text size and at the largest,
     * WidgetKit's xxxLarge, where the names and status lines shrink before they end.
     */
    @Test
    fun widgetsAtALaunchersSizes() {
        open()
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val source = widgetSource(context, File(folder, "cards"), store)
        runBlocking { publishDemo(context, store, File(folder, "cards"), context.resources.displayMetrics.density.toDouble()) }
        val pages = listOf("upright" to listOf(LAUNCHER_UPRIGHT, LAUNCHER_UPRIGHT_WIDE), "side" to listOf(LAUNCHER_ON_ITS_SIDE, LAUNCHER_TABLET))
        val kinds = listOf<Pair<String, (DpSize) -> DeliveryWidgetEntry<WidgetCardArt>>>(
            "cards" to { source.current(NextDeliveryWidget.isMedium(it), CAPTURED) },
            "words" to { wordsEntry(parcels, medium = NextDeliveryWidget.isMedium(it)) },
        )
        for (fontScale in listOf(null, LARGEST_TEXT)) {
            val composed = mode(night = false, fontScale)
            for ((kind, entry) in kinds) for ((name, sizes) in pages) {
                val widgets = runBlocking { sizes.map { it to widgetViews(composed, it, entry(it)) } }
                compose.runOnUiThread { page.value = Page(night = false, widgets, fontScale) }
                shots.take("sizes_${kind}_$name${if (fontScale != null) "_largest_text" else ""}")
            }
        }
    }

    /**
     * The widget itself in a host of the test's: what it reads from the app's store, published by the test, and its
     * next button turning it to the next parcel (C-W01, C-W03, C-W21).
     */
    @Test
    fun boundWidgetReadsTheStoreAndTurns() {
        val manager = AppWidgetManager.getInstance(context)
        val provider = ComponentName(context, NextDeliveryWidgetReceiver::class.java)
        val preferences = context.getSharedPreferences(BuildSettings.configuration.appGroupIdentifier, Context.MODE_PRIVATE)
        val kept = preferences.all.toMap()
        shell("appwidget grantbind --package ${context.packageName} --user current")
        val host = AppWidgetHost(context, HOST)
        val id = host.allocateAppWidgetId()
        try {
            assumeTrue("The device lets the test bind the widget", manager.bindAppWidgetIdIfAllowed(id, provider))
            val store = DeliveryWidgetSharedStore(PreferencesDefaults(preferences))
            store.setEnabled(true)
            // Not the sample's names, which the widget's first look (its preview layout) shows until it is drawn.
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
            shots.take("bound_small")

            val next = ExtensionLocalizer("en").text("app.nextParcel")
            compose.runOnUiThread { checkNotNull(view.described(next)) { "No next button" }.performClick() }
            awaitText(view, "Second parcel")
            shots.take("bound_next")
            assertEquals(published[1].id, store.turn(size = "small")?.parcel)
        } finally {
            host.deleteAppWidgetId(id)
            compose.runOnUiThread { host.stopListening() }
            preferences.edit().clear().also { editor -> kept.forEach { (key, value) -> editor.put(key, value) } }.commit()
            DeliveryWidgetUpdates.reload(context)
            shell("appwidget revokebind --package ${context.packageName} --user current")
        }
    }

    /** Waits until the widget's views show `text`, as the host gets them from the launcher's service. */
    private fun awaitText(view: View, text: String) = compose.waitUntil(15_000) { compose.runOnUiThread { view.shows(text) } }

    private fun View.shows(text: String): Boolean =
        (this is TextView && this.text?.toString() == text) || (this is ViewGroup && (0 until childCount).any { getChildAt(it).shows(text) })

    private fun View.described(description: String): View? {
        if (contentDescription?.toString() == description && isClickable) return this
        if (this !is ViewGroup) return null
        return (0 until childCount).firstNotNullOfOrNull { getChildAt(it).described(description) }
    }

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
