package com.plhery.peekdeliverytracker.platform

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.core.net.toUri
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.WidgetCenter
import com.plhery.peekdeliverytracker.widget.onAndroid
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * The shortcuts the launcher shows on the app's icon, the iPhone's App Shortcuts (ShortcutIntents.swift): "Add a
 * parcel" ([ADD], res/xml/shortcuts.xml), there from install on, and the next delivery ([NEXT_DELIVERY]), which the
 * app publishes from what the widget shows. That one is named after the widget's first parcel on its way, as Siri's
 * answer picks it, and opens its page, as the widget's card does; it follows the widget's parcels each time they
 * change, a copy pinned to the home screen with it. With nothing on the way, the widget turned off or nobody signed in,
 * it goes, and a pinned copy stays greyed under the shortcut's own name, saying why it opens nothing.
 *
 * Each shortcut's intent names it ([ShortcutManagerCompat.EXTRA_SHORTCUT_ID]), and the window reports it used as it
 * opens on it ([used]), for the launcher's suggestions. The next delivery's is published with
 * [ShortcutManagerCompat.setDynamicShortcuts], which reports nothing used, rather than `pushDynamicShortcut`, which
 * would report each change as a use.
 */
object LauncherShortcuts {
    /** "Add a parcel", in res/xml/shortcuts.xml. */
    const val ADD = "add"

    /** The next delivery. */
    const val NEXT_DELIVERY = "next-delivery"

    private val lock = Mutex()

    /**
     * [widgets], and after them the next delivery's shortcut, each time what the widget shows changes: the store's
     * publication, which it skips when nothing the widget shows changed.
     */
    fun widgetCenter(context: Context, store: DeliveryWidgetSharedStore, scope: CoroutineScope, widgets: WidgetCenter): WidgetCenter =
        WidgetCenter { kind ->
            widgets.reloadTimelines(kind)
            if (kind == DeliveryWidgetSharedStore.kind) reload(context, store, scope)
        }

    /**
     * Brings the next delivery's shortcut up to date off the main thread, where the system may take its time over a
     * change, one change after another, each reading the store as it is then.
     */
    fun reload(context: Context, store: DeliveryWidgetSharedStore, scope: CoroutineScope): Job {
        val app = context.applicationContext
        return scope.launch(Dispatchers.IO) { lock.withLock { update(app, store) } }
    }

    /**
     * The next delivery's shortcut as the widget's store has it now. The system allows an app in the background only
     * so many changes to its shortcuts: one that shows the same parcel the same way is left as it is.
     */
    fun update(context: Context, store: DeliveryWidgetSharedStore) = quietly {
        val snapshot = store.snapshot?.takeIf { store.isEnabled }
        val next = snapshot?.parcels?.firstOrNull { it.deliveredAt == null }
        val published = ShortcutManagerCompat.getShortcuts(context, ShortcutManagerCompat.FLAG_MATCH_DYNAMIC or ShortcutManagerCompat.FLAG_MATCH_PINNED)
            .firstOrNull { it.id == NEXT_DELIVERY }
        if (next != null) {
            val shortcut = nextDelivery(context, next.label, next.deepLink.toUri())
            if (published != null && published.isDynamic && published.isEnabled && shows(published, shortcut)) return@quietly
            // A pinned copy greyed while nothing was on the way comes back with it.
            ShortcutManagerCompat.enableShortcuts(context, listOf(shortcut))
            ShortcutManagerCompat.setDynamicShortcuts(context, listOf(shortcut))
        } else {
            val copy = ExtensionLocalizer(store.languageCode, Device.preferredLanguages())
            if (published != null && published.isEnabled) {
                ShortcutManagerCompat.updateShortcuts(context, listOf(nextDelivery(context, copy.text("appIntents.nextDelivery"), null)))
                ShortcutManagerCompat.removeDynamicShortcuts(context, listOf(NEXT_DELIVERY))
            }
            ShortcutManagerCompat.disableShortcuts(context, listOf(NEXT_DELIVERY), greyed(store.isEnabled, snapshot != null, copy))
        }
    }

    /**
     * What a greyed copy says as it is tapped, as Siri answers without a next delivery: the widget's own words where its
     * switch is off ([enabled]), Siri's where the widget has no parcels from the app (nobody signed in, [parcels]), else
     * that nothing is on the way.
     */
    internal fun greyed(enabled: Boolean, parcels: Boolean, copy: ExtensionLocalizer): String = when {
        !enabled -> copy.onAndroid("widget.disabledDescription")
        !parcels -> copy.text("siri.openPeek")
        else -> copy.text("app.noneOnWay")
    }

    /** The shortcut that `intent` came from, by the id its intent names: one of the app's, or null. */
    fun shortcut(intent: Intent): String? {
        val id = try {
            intent.getStringExtra(ShortcutManagerCompat.EXTRA_SHORTCUT_ID)
        } catch (_: RuntimeException) {
            // Extras another app sent that don't unparcel.
            null
        }
        return id?.takeIf { it == ADD || it == NEXT_DELIVERY }
    }

    /** The window opened on the shortcut `id`. */
    fun used(context: Context, id: String) = quietly { ShortcutManagerCompat.reportShortcutUsed(context, id) }

    /** Whether the shortcut the system has shows `shortcut`'s name and opens its page. */
    private fun shows(published: ShortcutInfoCompat, shortcut: ShortcutInfoCompat): Boolean =
        published.shortLabel.toString() == shortcut.shortLabel.toString() &&
            published.intents.orEmpty().lastOrNull()?.data == shortcut.intent.data

    /** The next delivery's shortcut, named `label`, opening `parcel`'s page, or the app where it names no parcel. */
    private fun nextDelivery(context: Context, label: String, parcel: Uri?): ShortcutInfoCompat {
        val intent = if (parcel != null) {
            Intent(Intent.ACTION_VIEW, parcel, context, MainActivity::class.java)
        } else {
            Intent.makeMainActivity(ComponentName(context, MainActivity::class.java))
        }
        return ShortcutInfoCompat.Builder(context, NEXT_DELIVERY)
            .setShortLabel(label)
            .setLongLabel(label)
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_shortcut_next_delivery))
            .setIntent(intent.putExtra(ShortcutManagerCompat.EXTRA_SHORTCUT_ID, NEXT_DELIVERY))
            .build()
    }

    /** The shortcuts are the system's: what it turns down (its allowance spent, the phone still locked) leaves them as they are. */
    private inline fun quietly(body: () -> Unit) {
        try {
            body()
        } catch (cancelled: CancellationException) {
            throw cancelled
        } catch (_: Exception) {
        }
    }
}
