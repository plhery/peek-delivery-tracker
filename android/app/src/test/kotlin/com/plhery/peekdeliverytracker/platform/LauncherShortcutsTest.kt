package com.plhery.peekdeliverytracker.platform

import android.app.Application
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.VectorDrawable
import android.net.Uri
import android.util.Xml
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.AddLinkWindow
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.widget.widgetParcel
import kotlin.math.hypot
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.xmlpull.v1.XmlPullParser

/**
 * The shortcuts on the app's icon, the iPhone's App Shortcuts: "Add a parcel", from the manifest, which opens Add with
 * its field empty, in each language the app speaks; and the next delivery, which names the widget's first parcel on
 * its way, opens its page, follows the widget's parcels and goes with nothing on the way. Each opens the window
 * reported used. On synthetic parcels.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class LauncherShortcutsTest {
    @get:Rule
    val compose = createEmptyComposeRule()

    private val app = ApplicationProvider.getApplicationContext<Application>()
    private val store = DeliveryWidgetSharedStore(MemoryDefaults())

    private val chocolate = widgetParcel(1, "Belgian chocolate", carrier = "FedEx", outForDelivery = true)
    private val lamp = widgetParcel(2, "Desk lamp", detail = "Friday")
    private val kit = widgetParcel(3, "Trail weekend kit", detail = "delivered", deliveredAt = CAPTURED)

    /** The widget's parcels as the store publishes them, and the shortcut brought up to date with them. */
    private fun publish(vararg parcels: DeliveryWidgetParcel) {
        check(store.save(DeliveryWidgetSnapshot(CAPTURED, "en", parcels.toList())))
        LauncherShortcuts.update(app, store)
    }

    private fun dynamic(): List<ShortcutInfoCompat> = ShortcutManagerCompat.getDynamicShortcuts(app)

    private val manager: ShortcutManager get() = app.getSystemService(ShortcutManager::class.java)

    /** The copies pinned to the home screen, greyed ones included. */
    private fun pinned(): List<ShortcutInfo> = manager.pinnedShortcuts

    /** Pinning refuses a shortcut whose pinned copy is greyed, as Android does. */
    private fun greyed(shortcut: ShortcutInfo): Boolean =
        runCatching { manager.requestPinShortcut(shortcut, null) }.exceptionOrNull() is IllegalArgumentException

    /** The manifest's shortcut, as the window's entry names its file: its attributes, and its intent as Android reads it. */
    private fun manifestShortcut(): Pair<Map<String, Int>, Intent> {
        val window = app.packageManager.getActivityInfo(ComponentName(app, MainActivity::class.java), PackageManager.GET_META_DATA)
        assertEquals(R.xml.shortcuts, window.metaData.getInt("android.app.shortcuts"))
        val parser = app.resources.getXml(R.xml.shortcuts)
        val attributes = mutableMapOf<String, Int>()
        var intent: Intent? = null
        var shortcuts = 0
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.eventType != XmlPullParser.START_TAG) continue
            when (parser.name) {
                "shortcut" -> {
                    shortcuts++
                    assertEquals(LauncherShortcuts.ADD, parser.getAttributeValue(ANDROID, "shortcutId"))
                    for (name in listOf("shortcutShortLabel", "shortcutLongLabel", "icon")) {
                        attributes[name] = parser.getAttributeResourceValue(ANDROID, name, 0)
                    }
                }
                "intent" -> intent = Intent.parseIntent(app.resources, parser, Xml.asAttributeSet(parser))
            }
        }
        assertEquals(1, shortcuts)
        return attributes to checkNotNull(intent)
    }

    @Test
    fun addAParcelIsTheManifestsShortcutNamedInEachLanguageTheAppSpeaks() {
        val (attributes, _) = manifestShortcut()
        assertEquals(R.string.shortcut_add_parcel, attributes["shortcutShortLabel"])
        assertEquals(R.string.shortcut_add_parcel, attributes["shortcutLongLabel"])
        assertEquals(R.drawable.ic_shortcut_add, attributes["icon"])
        for (language in AppLanguage.entries) {
            val configuration = Configuration(app.resources.configuration).apply { setLocale(language.locale) }
            val name = app.createConfigurationContext(configuration).getString(R.string.shortcut_add_parcel)
            assertEquals(language.rawValue, ExtensionLocalizer(language.rawValue).text("appIntents.addParcel"), name)
        }
        assertEquals("Add a parcel", app.getString(R.string.shortcut_add_parcel))
    }

    @Test
    fun addAParcelOpensAddWithItsFieldEmptyAndIsReportedUsed() {
        val (_, intent) = manifestShortcut()
        assertEquals(Intent.ACTION_VIEW, intent.action)
        assertEquals(Uri.parse("peekdeliverytracker://add"), intent.data)
        assertEquals(ComponentName(app, MainActivity::class.java), intent.component)
        assertEquals(LauncherShortcuts.ADD, LauncherShortcuts.shortcut(intent))

        AddLinkWindow.state.value = AuthenticationState.Demo
        AddLinkWindow.source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        val window = Robolectric.buildActivity(AddLinkWindow::class.java, Intent(intent).setClass(app, AddLinkWindow::class.java)).setup()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertTrue(compose.onAllNodesWithTag(ADD_PARCEL, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty())
        assertEquals("", compose.onNodeWithTag(ADD_TRACKING).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text)
        assertEquals(listOf(LauncherShortcuts.ADD), window.get().shortcuts)
        window.pause().stop().destroy()
    }

    @Test
    fun theNextDeliveryIsTheWidgetsFirstParcelOnItsWayNamedAsTheWidgetNamesItAndOpensItsPage() {
        publish(chocolate, lamp, kit)
        val shortcut = dynamic().single()
        assertEquals(LauncherShortcuts.NEXT_DELIVERY, shortcut.id)
        assertEquals("Belgian chocolate", shortcut.shortLabel.toString())
        assertEquals("Belgian chocolate", shortcut.longLabel.toString())
        val intent = shortcut.intent
        assertEquals(Intent.ACTION_VIEW, intent.action)
        assertEquals(Uri.parse(chocolate.deepLink), intent.data)
        assertEquals(ComponentName(app, MainActivity::class.java), intent.component)

        // The window's inbox opens the parcel's page, as the widget's card does, and reports the shortcut used.
        val used = mutableListOf<String>()
        val inbox = DeliveriesInbox(shortcutUsed = { used += it })
        inbox.follow(intent)
        assertEquals(chocolate.id, inbox.parcel.value)
        assertEquals(listOf(LauncherShortcuts.NEXT_DELIVERY), used)
    }

    @Test
    fun itIsRenamedAsTheNextDeliveryChangesAndGoesWithNothingOnTheWayOrTheWidgetOff() {
        publish(lamp)
        assertEquals("Desk lamp", dynamic().single().shortLabel.toString())
        publish(lamp.copy(label = "Reading lamp"))
        assertEquals("Reading lamp", dynamic().single().shortLabel.toString())
        publish(chocolate, lamp)
        assertEquals("Belgian chocolate", dynamic().single().shortLabel.toString())
        assertEquals(Uri.parse(chocolate.deepLink), dynamic().single().intent.data)

        // The widget keeps the parcels delivered in the last day, which aren't on their way.
        publish(kit)
        assertEquals(emptyList<ShortcutInfoCompat>(), dynamic())
        publish(lamp)
        assertEquals("Desk lamp", dynamic().single().shortLabel.toString())
        store.clearSnapshot()
        LauncherShortcuts.update(app, store)
        assertEquals(emptyList<ShortcutInfoCompat>(), dynamic())

        publish(lamp)
        store.setEnabled(false)
        LauncherShortcuts.update(app, store)
        assertEquals(emptyList<ShortcutInfoCompat>(), dynamic())
    }

    @Test
    fun theSameParcelShownTheSameWayLeavesTheShortcutAsItIs() {
        publish(lamp, kit)
        val published = manager.dynamicShortcuts.single()
        publish(lamp.copy(detail = "Saturday"), kit)
        assertSame(published, manager.dynamicShortcuts.single())
    }

    @Test
    fun aPinnedCopyFollowsTheNextDeliveryAndIsGreyedUnderTheShortcutsNameWithNothingOnTheWay() {
        publish(lamp)
        ShortcutManagerCompat.requestPinShortcut(app, dynamic().single(), null)
        publish(chocolate, lamp)
        assertEquals("Belgian chocolate", pinned().single().shortLabel.toString())
        assertEquals(Uri.parse(chocolate.deepLink), pinned().single().intent?.data)

        publish(kit)
        val greyed = pinned().single()
        assertEquals("Next delivery", greyed.shortLabel.toString())
        assertNull("It names no parcel's page", greyed.intent?.data)
        assertTrue(greyed(greyed))

        publish(lamp)
        assertEquals("Desk lamp", pinned().single().shortLabel.toString())
        assertFalse("Back", greyed(pinned().single()))
    }

    @Test
    fun aGreyedCopySaysWhyItOpensNothing() {
        val copy = ExtensionLocalizer("en")
        assertEquals("Nothing on the way right now", LauncherShortcuts.greyed(enabled = true, parcels = true, copy))
        assertEquals("Open Peek to see your next delivery.", LauncherShortcuts.greyed(enabled = true, parcels = false, copy))
        assertEquals(
            "Turn on home screen widgets in Settings to see your next delivery.",
            LauncherShortcuts.greyed(enabled = false, parcels = false, copy),
        )
        assertEquals("Aucun colis en route pour le moment", LauncherShortcuts.greyed(enabled = true, parcels = true, ExtensionLocalizer("fr")))
    }

    @Test
    fun theWindowReportsTheAppsOwnShortcutsUsedAndNoOther() {
        LauncherShortcuts.used(app, LauncherShortcuts.ADD)
        assertEquals(listOf(LauncherShortcuts.ADD), shadowOf(manager).reportedShortcutsUsed)

        val link = Intent(Intent.ACTION_VIEW, Uri.parse("peekdeliverytracker://add"))
        assertNull(LauncherShortcuts.shortcut(link))
        assertNull(LauncherShortcuts.shortcut(Intent(link).putExtra(ShortcutManagerCompat.EXTRA_SHORTCUT_ID, "another")))
        assertEquals(LauncherShortcuts.NEXT_DELIVERY, LauncherShortcuts.shortcut(Intent(link).putExtra(ShortcutManagerCompat.EXTRA_SHORTCUT_ID, "next-delivery")))
    }

    /**
     * Both icons are adaptive, as the app's is: the mark's yellow behind a glyph in its ink alone, inside the circle a
     * launcher's mask keeps whatever its shape, and the glyph for themed icons.
     */
    @Test
    fun theIconsAreAGlyphInTheMarksInkOnItsYellow() {
        val ink = app.getColor(R.color.mark_ink)
        for (resource in listOf(R.drawable.ic_shortcut_add, R.drawable.ic_shortcut_next_delivery)) {
            val name = app.resources.getResourceEntryName(resource)
            val icon = app.getDrawable(resource) as AdaptiveIconDrawable
            assertEquals(name, app.getColor(R.color.mark_yellow), (icon.background as ColorDrawable).color)
            assertTrue(name, icon.foreground is VectorDrawable)
            assertTrue(name, icon.monochrome is VectorDrawable)
            // Four pixels to the dp.
            val side = 432
            val bitmap = Bitmap.createBitmap(side, side, Bitmap.Config.ARGB_8888)
            icon.foreground.setBounds(0, 0, side, side)
            icon.foreground.draw(Canvas(bitmap))
            var inked = 0
            for (y in 0 until side) for (x in 0 until side) {
                val pixel = bitmap.getPixel(x, y)
                if (pixel ushr 24 == 0) continue
                // The safe zone, a circle 66 across in the middle of the 108.
                assertTrue("$name at $x, $y", hypot(x + 0.5 - side / 2.0, y + 0.5 - side / 2.0) <= 33 * 4)
                if (pixel ushr 24 == 255) {
                    assertEquals(name, ink and 0xFFFFFF, pixel and 0xFFFFFF)
                    inked++
                }
            }
            // A plus's arms 25.7 dp long and 3.7 thick, near 175 dp² of ink, or 2,800 pixels.
            assertTrue("$name: $inked", inked > 2_000)
        }
    }

    private companion object {
        const val ANDROID = "http://schemas.android.com/apk/res/android"
    }
}
