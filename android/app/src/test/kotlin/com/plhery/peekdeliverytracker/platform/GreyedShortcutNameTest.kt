package com.plhery.peekdeliverytracker.platform

import android.app.Application
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.widget.widgetParcel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * The greyed pinned "Next delivery" shortcut follows the app's language. On synthetic parcels.
 *
 * Robolectric's ShortcutManager moves a disabled pinned copy to a list of its own but leaves its ShortcutInfo reading
 * `isEnabled() == true`; Android's ShortcutService sets `ShortcutInfo.FLAG_DISABLED` on it, so `isEnabled()` reads
 * false. [asAndroidGreysIt] sets that flag on the very object the shadow hands back, as the device would.
 */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = PHONE)
class GreyedShortcutNameTest {
    private val app = ApplicationProvider.getApplicationContext<Application>()
    private val store = DeliveryWidgetSharedStore(MemoryDefaults())

    private val lamp = widgetParcel(2, "Desk lamp", detail = "Friday")
    private val kit = widgetParcel(3, "Trail weekend kit", detail = "delivered", deliveredAt = CAPTURED)

    private val manager: ShortcutManager get() = app.getSystemService(ShortcutManager::class.java)
    private fun pinned(): ShortcutInfo = manager.pinnedShortcuts.single()

    private fun publish(language: String, vararg parcels: DeliveryWidgetParcel) {
        store.setLanguageCode(language)
        check(store.save(DeliveryWidgetSnapshot(CAPTURED, language, parcels.toList())))
        LauncherShortcuts.update(app, store)
    }

    /** `ShortcutInfo.FLAG_DISABLED` (hidden), which `ShortcutManager.disableShortcuts` sets on a device. */
    private fun asAndroidGreysIt(shortcut: ShortcutInfo) {
        ShortcutInfo::class.java.getMethod("addFlags", Int::class.javaPrimitiveType).invoke(shortcut, FLAG_DISABLED)
        assertFalse(shortcut.isEnabled)
    }

    /** A copy pinned while a parcel was on its way, greyed in English once nothing is on the way. */
    private fun greyedInEnglish(): ShortcutInfo {
        publish("en", lamp)
        ShortcutManagerCompat.requestPinShortcut(app, ShortcutManagerCompat.getDynamicShortcuts(app).single(), null)
        publish("en", kit)
        assertEquals("Next delivery", pinned().shortLabel.toString())
        return pinned()
    }

    @Test
    fun aGreyedPinnedCopyIsRenamedWhenTheAppsLanguageChanges() {
        asAndroidGreysIt(greyedInEnglish())
        publish("fr", kit)
        assertEquals(
            "A greyed pinned copy takes the shortcut's name in the app's new language",
            ExtensionLocalizer("fr").text("appIntents.nextDelivery"),
            pinned().shortLabel.toString(),
        )
    }

    /** The same steps with the copy reading enabled, Robolectric's own model, rename it too. */
    @Test
    fun aCopyReadingEnabledIsRenamed() {
        greyedInEnglish()
        publish("fr", kit)
        assertEquals("Prochaine livraison", ExtensionLocalizer("fr").text("appIntents.nextDelivery"))
        assertEquals("Prochaine livraison", pinned().shortLabel.toString())
    }

    /** A parcel on its way renames a greyed copy, flag and all. */
    @Test
    fun aParcelOnItsWayRenamesAGreyedCopy() {
        asAndroidGreysIt(greyedInEnglish())
        publish("fr", lamp)
        assertEquals("Desk lamp", pinned().shortLabel.toString())
    }

    private companion object {
        const val FLAG_DISABLED = 1 shl 6
    }
}
