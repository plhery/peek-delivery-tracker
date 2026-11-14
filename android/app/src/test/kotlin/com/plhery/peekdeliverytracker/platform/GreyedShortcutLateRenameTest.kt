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
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.shadows.ShadowShortcutManager

/**
 * A greyed pinned "Next delivery" copy whose rename was turned down is renamed later. Android's ShortcutService
 * rate-limits updateShortcuts for an app in the background (it returns false) but not removeDynamicShortcuts or
 * disableShortcuts, so update() can grey the copy under a delivered parcel's name; the next update renames it. On
 * synthetic parcels.
 */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = PHONE, shadows = [GreyedShortcutLateRenameTest.ThrottledUpdates::class])
class GreyedShortcutLateRenameTest {
    /** Robolectric's ShortcutManager with updateShortcuts turned down while [throttled], as Android's rate limit does. */
    @Implements(value = ShortcutManager::class, minSdk = 25)
    class ThrottledUpdates : ShadowShortcutManager() {
        @Implementation
        override fun updateShortcuts(shortcutInfoList: MutableList<ShortcutInfo>): Boolean =
            if (throttled) false else super.updateShortcuts(shortcutInfoList)

        companion object {
            @JvmStatic var throttled = false
        }
    }

    private val app = ApplicationProvider.getApplicationContext<Application>()
    private val store = DeliveryWidgetSharedStore(MemoryDefaults())

    private val lamp = widgetParcel(2, "Desk lamp", detail = "Friday")
    private val kit = widgetParcel(3, "Trail weekend kit", detail = "delivered", deliveredAt = CAPTURED)
    private val lampDelivered = widgetParcel(2, "Desk lamp", detail = "delivered", deliveredAt = CAPTURED)

    private val manager: ShortcutManager get() = app.getSystemService(ShortcutManager::class.java)
    private fun pinned(): ShortcutInfo = manager.pinnedShortcuts.single()

    @After fun unthrottle() {
        ThrottledUpdates.throttled = false
    }

    private fun publish(vararg parcels: DeliveryWidgetParcel) {
        store.setLanguageCode("en")
        check(store.save(DeliveryWidgetSnapshot(CAPTURED, "en", parcels.toList())))
        LauncherShortcuts.update(app, store)
    }

    /** `ShortcutInfo.FLAG_DISABLED` (hidden), which Android's disableShortcuts sets and Robolectric's doesn't. */
    private fun asAndroidGreysIt(shortcut: ShortcutInfo) {
        ShortcutInfo::class.java.getMethod("addFlags", Int::class.javaPrimitiveType).invoke(shortcut, 1 shl 6)
        assertFalse(shortcut.isEnabled)
    }

    @Test
    fun aGreyedCopyWhoseRenameWasTurnedDownIsRenamedLater() {
        publish(lamp)
        ShortcutManagerCompat.requestPinShortcut(app, ShortcutManagerCompat.getDynamicShortcuts(app).single(), null)
        ThrottledUpdates.throttled = true
        publish(lampDelivered, kit)
        asAndroidGreysIt(pinned())
        assertEquals("Desk lamp", pinned().shortLabel.toString())
        ThrottledUpdates.throttled = false
        publish(lampDelivered, kit)
        assertEquals(
            "A greyed copy whose rename was turned down takes the shortcut's own name at the next update",
            ExtensionLocalizer("en").text("appIntents.nextDelivery"),
            pinned().shortLabel.toString(),
        )
    }

    /** Unthrottled, the copy greys under the shortcut's own name. */
    @Test
    fun anAllowedRenameGreysTheCopyUnderItsOwnName() {
        publish(lamp)
        ShortcutManagerCompat.requestPinShortcut(app, ShortcutManagerCompat.getDynamicShortcuts(app).single(), null)
        publish(lampDelivered, kit)
        assertEquals(ExtensionLocalizer("en").text("appIntents.nextDelivery"), pinned().shortLabel.toString())
    }
}
