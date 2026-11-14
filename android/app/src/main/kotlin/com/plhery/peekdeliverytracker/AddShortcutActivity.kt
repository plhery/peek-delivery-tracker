package com.plhery.peekdeliverytracker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.pm.ShortcutManagerCompat

/**
 * Where the "Add a parcel" shortcut on the app's icon comes in (`res/xml/shortcuts.xml`). Android starts a manifest
 * shortcut's activity in a task made again (`FLAG_ACTIVITY_CLEAR_TASK`), which would close the app's window and an Add
 * left open in it, a number typed in it included. So the shortcut comes to this window instead, which shows nothing in
 * a task of its own that Recents neither shows nor keeps, and hands Add's link, with the shortcut's id, to the app's
 * window in its own task: the window already open comes forward as it was, and Add opens on it, or stays as it is when
 * it is up already ([com.plhery.peekdeliverytracker.ui.deliveries.AddLink.shortcut]), as the iPhone's App Shortcut
 * opens Add over the app as it was.
 */
class AddShortcutActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(window(this, intent))
        // Removed from Recents with its task, which it roots.
        finishAndRemoveTask()
    }

    companion object {
        /** The window's intent for the shortcut's `shortcut`: Add's link and the shortcut's id, in the window's task. */
        fun window(context: Context, shortcut: Intent?): Intent {
            val window = Intent(Intent.ACTION_VIEW, Uri.parse(ADD_LINK), context, MainActivity::class.java)
            shortcut?.getStringExtra(ShortcutManagerCompat.EXTRA_SHORTCUT_ID)?.let { window.putExtra(ShortcutManagerCompat.EXTRA_SHORTCUT_ID, it) }
            return window.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        private const val ADD_LINK = "peekdeliverytracker://add"
    }
}
